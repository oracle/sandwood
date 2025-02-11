package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DistributionTest6$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DistributionTest6$CoreInterface {
	private double[] cv$var15$stateProbabilityGlobal;
	private double[][] cv$var23$stateProbabilityGlobal;
	private double[] cv$var9$stateProbabilityGlobal;
	private double[] distribution$sample12;
	private double[] distribution$sample18;
	private double[][] distribution$sample26;
	private boolean fixedFlag$sample12 = false;
	private boolean fixedFlag$sample18 = false;
	private boolean fixedFlag$sample26 = false;
	private boolean fixedFlag$sample41 = false;
	private boolean fixedProbFlag$sample12 = false;
	private boolean fixedProbFlag$sample18 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean fixedProbFlag$sample41 = false;
	private boolean[] guard$sample18bernoulli40$global;
	private boolean[][] guard$sample26bernoulli40$global;
	private int length$value;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$sample26;
	private double[] logProbability$sample41;
	private double logProbability$v;
	private double logProbability$v1;
	private double logProbability$v2;
	private double logProbability$var14;
	private double logProbability$var15;
	private double[] logProbability$var22;
	private double[] logProbability$var37;
	private double logProbability$var8;
	private boolean setFlag$v = false;
	private boolean setFlag$v2 = false;
	private int size;
	private boolean system$gibbsForward = true;
	private boolean[] v;
	private int v1;
	private int[] v2;
	private boolean[] value;
	private double[] weightings;

	public DistributionTest6$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$fixedFlag$sample12() {
		return fixedFlag$sample12;
	}

	@Override
	public final void set$fixedFlag$sample12(boolean cv$value) {
		fixedFlag$sample12 = cv$value;
		fixedProbFlag$sample12 = (fixedFlag$sample12 && fixedProbFlag$sample12);
		fixedProbFlag$sample41 = (fixedFlag$sample12 && fixedProbFlag$sample41);
	}

	@Override
	public final boolean get$fixedFlag$sample18() {
		return fixedFlag$sample18;
	}

	@Override
	public final void set$fixedFlag$sample18(boolean cv$value) {
		fixedFlag$sample18 = cv$value;
		fixedProbFlag$sample18 = (fixedFlag$sample18 && fixedProbFlag$sample18);
		fixedProbFlag$sample41 = (fixedFlag$sample18 && fixedProbFlag$sample41);
	}

	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	@Override
	public final void set$fixedFlag$sample26(boolean cv$value) {
		fixedFlag$sample26 = cv$value;
		fixedProbFlag$sample26 = (fixedFlag$sample26 && fixedProbFlag$sample26);
		fixedProbFlag$sample41 = (fixedFlag$sample26 && fixedProbFlag$sample41);
	}

	@Override
	public final boolean get$fixedFlag$sample41() {
		return fixedFlag$sample41;
	}

	@Override
	public final void set$fixedFlag$sample41(boolean cv$value) {
		fixedFlag$sample41 = cv$value;
		fixedProbFlag$sample41 = (fixedFlag$sample41 && fixedProbFlag$sample41);
	}

	@Override
	public final int get$length$value() {
		return length$value;
	}

	@Override
	public final void set$length$value(int cv$value) {
		length$value = cv$value;
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
	public final double get$logProbability$v() {
		return logProbability$v;
	}

	@Override
	public final double get$logProbability$v1() {
		return logProbability$v1;
	}

	@Override
	public final double get$logProbability$v2() {
		return logProbability$v2;
	}

	@Override
	public final int get$size() {
		return size;
	}

	@Override
	public final boolean[] get$v() {
		return v;
	}

	@Override
	public final void set$v(boolean[] cv$value) {
		v = cv$value;
		setFlag$v = true;
		fixedProbFlag$sample41 = false;
	}

	@Override
	public final int get$v1() {
		return v1;
	}

	@Override
	public final void set$v1(int cv$value) {
		v1 = cv$value;
		fixedProbFlag$sample12 = false;
		fixedProbFlag$sample41 = false;
	}

	@Override
	public final int[] get$v2() {
		return v2;
	}

	@Override
	public final void set$v2(int[] cv$value) {
		v2 = cv$value;
		setFlag$v2 = true;
		fixedProbFlag$sample18 = false;
		fixedProbFlag$sample26 = false;
		fixedProbFlag$sample41 = false;
	}

	@Override
	public final boolean[] get$value() {
		return value;
	}

	@Override
	public final void set$value(boolean[] cv$value) {
		value = cv$value;
	}

	@Override
	public final double[] get$weightings() {
		return weightings;
	}

	@Override
	public final void set$weightings(double[] cv$value) {
		weightings = cv$value;
	}

	private final void logProbabilityDistribution$sample12() {
		if(!fixedProbFlag$sample12) {
			if(fixedFlag$sample12) {
				double cv$accumulator = 0.0;
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = v1;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var8 = cv$sampleAccumulator;
				logProbability$v1 = cv$sampleProbability;
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample12)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample12 = fixedFlag$sample12;
			}
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$v1;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var8 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample18() {
		if(!fixedProbFlag$sample18) {
			if(fixedFlag$sample18) {
				double cv$accumulator = 0.0;
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = v2[0];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var14 = cv$sampleAccumulator;
				logProbability$var15 = cv$sampleProbability;
				if(fixedFlag$sample18)
					logProbability$v2 = (logProbability$v2 + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample18)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample18 = fixedFlag$sample18;
			}
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var15;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var14 = cv$rvAccumulator;
			if(fixedFlag$sample18)
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample26() {
		if(!fixedProbFlag$sample26) {
			if(fixedFlag$sample26) {
				double cv$accumulator = 0.0;
				for(int i = 0; i < size; i += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$i$1 = i;
					{
						int cv$sampleValue = v2[(i + 1)];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var22[((i - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample26[((i - 0) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample26)
					logProbability$v2 = (logProbability$v2 + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample26)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample26 = fixedFlag$sample26;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample26[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var22[((i - 0) / 1)] = cv$rvAccumulator;
			}
			if(fixedFlag$sample26)
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample41() {
		if(!fixedProbFlag$sample41) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = v[j];
					if(fixedFlag$sample12) {
						if(fixedFlag$sample18) {
							if((0 == j)) {
								if((0 == j)) {
									if((0 == j)) {
										{
											double var36 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
											double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
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
								for(int index$sample18$8 = 0; index$sample18$8 < weightings.length; index$sample18$8 += 1) {
									int distributionTempVariable$var15$10 = index$sample18$8;
									double cv$probabilitySample18Value9 = (1.0 * distribution$sample18[index$sample18$8]);
									int traceTempVariable$var31$11_1 = distributionTempVariable$var15$10;
									if((0 == j)) {
										int traceTempVariable$var33$18_1 = distributionTempVariable$var15$10;
										if((0 == j)) {
											int traceTempVariable$var35$30_1 = distributionTempVariable$var15$10;
											if((0 == j)) {
												{
													double var36 = ((((1.0 * v1) + traceTempVariable$var31$11_1) + traceTempVariable$var33$18_1) / traceTempVariable$var35$30_1);
													double cv$weightedProbability = (Math.log(cv$probabilitySample18Value9) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value9);
												}
											}
											if(!true) {
												for(int index$sample18$31 = 0; index$sample18$31 < weightings.length; index$sample18$31 += 1) {
													int distributionTempVariable$var15$33 = index$sample18$31;
													double cv$probabilitySample18Value32 = (cv$probabilitySample18Value9 * distribution$sample18[index$sample18$31]);
													int traceTempVariable$var35$34_1 = distributionTempVariable$var15$33;
													if((0 == j)) {
														{
															double var36 = ((((1.0 * v1) + traceTempVariable$var31$11_1) + traceTempVariable$var33$18_1) / traceTempVariable$var35$34_1);
															double cv$weightedProbability = (Math.log(cv$probabilitySample18Value32) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value32);
														}
													}
												}
											}
										}
										if(!true) {
											for(int index$sample18$19 = 0; index$sample18$19 < weightings.length; index$sample18$19 += 1) {
												int distributionTempVariable$var15$21 = index$sample18$19;
												double cv$probabilitySample18Value20 = (cv$probabilitySample18Value9 * distribution$sample18[index$sample18$19]);
												int traceTempVariable$var33$22_1 = distributionTempVariable$var15$21;
												if((0 == j)) {
													int traceTempVariable$var35$35_1 = distributionTempVariable$var15$10;
													if((0 == j)) {
														{
															double var36 = ((((1.0 * v1) + traceTempVariable$var31$11_1) + traceTempVariable$var33$22_1) / traceTempVariable$var35$35_1);
															double cv$weightedProbability = (Math.log(cv$probabilitySample18Value20) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value20);
														}
													}
													int traceTempVariable$var35$36_1 = distributionTempVariable$var15$21;
													if((0 == j)) {
														{
															double var36 = ((((1.0 * v1) + traceTempVariable$var31$11_1) + traceTempVariable$var33$22_1) / traceTempVariable$var35$36_1);
															double cv$weightedProbability = (Math.log(cv$probabilitySample18Value20) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value20);
														}
													}
													if(!true) {
														for(int index$sample18$37 = 0; index$sample18$37 < weightings.length; index$sample18$37 += 1) {
															int distributionTempVariable$var15$39 = index$sample18$37;
															double cv$probabilitySample18Value38 = (cv$probabilitySample18Value20 * distribution$sample18[index$sample18$37]);
															int traceTempVariable$var35$40_1 = distributionTempVariable$var15$39;
															if((0 == j)) {
																{
																	double var36 = ((((1.0 * v1) + traceTempVariable$var31$11_1) + traceTempVariable$var33$22_1) / traceTempVariable$var35$40_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample18Value38) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value38);
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
						if(true) {
							for(int index$sample12$3 = 0; index$sample12$3 < weightings.length; index$sample12$3 += 1) {
								int distributionTempVariable$v1$5 = index$sample12$3;
								double cv$probabilitySample12Value4 = (1.0 * distribution$sample12[index$sample12$3]);
								if(fixedFlag$sample18) {
									if((0 == j)) {
										if((0 == j)) {
											if((0 == j)) {
												{
													double var36 = ((((1.0 * distributionTempVariable$v1$5) + v2[j]) + v2[j]) / v2[j]);
													double cv$weightedProbability = (Math.log(cv$probabilitySample12Value4) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample12Value4);
												}
											}
										}
									}
								} else {
									if(true) {
										for(int index$sample18$13 = 0; index$sample18$13 < weightings.length; index$sample18$13 += 1) {
											int distributionTempVariable$var15$15 = index$sample18$13;
											double cv$probabilitySample18Value14 = (cv$probabilitySample12Value4 * distribution$sample18[index$sample18$13]);
											int traceTempVariable$var31$16_1 = distributionTempVariable$var15$15;
											if((0 == j)) {
												int traceTempVariable$var33$24_1 = distributionTempVariable$var15$15;
												if((0 == j)) {
													int traceTempVariable$var35$42_1 = distributionTempVariable$var15$15;
													if((0 == j)) {
														{
															double var36 = ((((1.0 * distributionTempVariable$v1$5) + traceTempVariable$var31$16_1) + traceTempVariable$var33$24_1) / traceTempVariable$var35$42_1);
															double cv$weightedProbability = (Math.log(cv$probabilitySample18Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value14);
														}
													}
													if(!true) {
														for(int index$sample18$43 = 0; index$sample18$43 < weightings.length; index$sample18$43 += 1) {
															int distributionTempVariable$var15$45 = index$sample18$43;
															double cv$probabilitySample18Value44 = (cv$probabilitySample18Value14 * distribution$sample18[index$sample18$43]);
															int traceTempVariable$var35$46_1 = distributionTempVariable$var15$45;
															if((0 == j)) {
																{
																	double var36 = ((((1.0 * distributionTempVariable$v1$5) + traceTempVariable$var31$16_1) + traceTempVariable$var33$24_1) / traceTempVariable$var35$46_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample18Value44) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value44);
																}
															}
														}
													}
												}
												if(!true) {
													for(int index$sample18$25 = 0; index$sample18$25 < weightings.length; index$sample18$25 += 1) {
														int distributionTempVariable$var15$27 = index$sample18$25;
														double cv$probabilitySample18Value26 = (cv$probabilitySample18Value14 * distribution$sample18[index$sample18$25]);
														int traceTempVariable$var33$28_1 = distributionTempVariable$var15$27;
														if((0 == j)) {
															int traceTempVariable$var35$47_1 = distributionTempVariable$var15$15;
															if((0 == j)) {
																{
																	double var36 = ((((1.0 * distributionTempVariable$v1$5) + traceTempVariable$var31$16_1) + traceTempVariable$var33$28_1) / traceTempVariable$var35$47_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample18Value26) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value26);
																}
															}
															int traceTempVariable$var35$48_1 = distributionTempVariable$var15$27;
															if((0 == j)) {
																{
																	double var36 = ((((1.0 * distributionTempVariable$v1$5) + traceTempVariable$var31$16_1) + traceTempVariable$var33$28_1) / traceTempVariable$var35$48_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample18Value26) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value26);
																}
															}
															if(!true) {
																for(int index$sample18$49 = 0; index$sample18$49 < weightings.length; index$sample18$49 += 1) {
																	int distributionTempVariable$var15$51 = index$sample18$49;
																	double cv$probabilitySample18Value50 = (cv$probabilitySample18Value26 * distribution$sample18[index$sample18$49]);
																	int traceTempVariable$var35$52_1 = distributionTempVariable$var15$51;
																	if((0 == j)) {
																		{
																			double var36 = ((((1.0 * distributionTempVariable$v1$5) + traceTempVariable$var31$16_1) + traceTempVariable$var33$28_1) / traceTempVariable$var35$52_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value50) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value50);
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
					if(fixedFlag$sample12) {
						if(fixedFlag$sample26) {
							for(int i = 0; i < size; i += 1) {
								if(((i + 1) == j)) {
									if(fixedFlag$sample18) {
										if((0 == j)) {
											if((0 == j)) {
												{
													double var36 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
													double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
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
										if(true) {
											for(int index$sample18$71 = 0; index$sample18$71 < weightings.length; index$sample18$71 += 1) {
												int distributionTempVariable$var15$73 = index$sample18$71;
												double cv$probabilitySample18Value72 = (1.0 * distribution$sample18[index$sample18$71]);
												int traceTempVariable$var33$74_1 = distributionTempVariable$var15$73;
												if((0 == j)) {
													int traceTempVariable$var35$91_1 = distributionTempVariable$var15$73;
													if((0 == j)) {
														{
															double var36 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var33$74_1) / traceTempVariable$var35$91_1);
															double cv$weightedProbability = (Math.log(cv$probabilitySample18Value72) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value72);
														}
													}
													if(!true) {
														for(int index$sample18$92 = 0; index$sample18$92 < weightings.length; index$sample18$92 += 1) {
															int distributionTempVariable$var15$94 = index$sample18$92;
															double cv$probabilitySample18Value93 = (cv$probabilitySample18Value72 * distribution$sample18[index$sample18$92]);
															int traceTempVariable$var35$95_1 = distributionTempVariable$var15$94;
															if((0 == j)) {
																{
																	double var36 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var33$74_1) / traceTempVariable$var35$95_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample18Value93) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value93);
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
							for(int i = 0; i < size; i += 1) {
								if(true) {
									for(int index$sample26$60 = 0; index$sample26$60 < weightings.length; index$sample26$60 += 1) {
										int distributionTempVariable$var23$62 = index$sample26$60;
										double cv$probabilitySample26Value61 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$60]);
										int traceTempVariable$var31$63_1 = distributionTempVariable$var23$62;
										if(((i + 1) == j)) {
											if(fixedFlag$sample18) {
												if((0 == j)) {
													if((0 == j)) {
														{
															double var36 = ((((1.0 * v1) + traceTempVariable$var31$63_1) + v2[j]) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample26Value61) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value61);
														}
													}
												}
											} else {
												if(true) {
													for(int index$sample18$76 = 0; index$sample18$76 < weightings.length; index$sample18$76 += 1) {
														int distributionTempVariable$var15$78 = index$sample18$76;
														double cv$probabilitySample18Value77 = (cv$probabilitySample26Value61 * distribution$sample18[index$sample18$76]);
														int traceTempVariable$var33$79_1 = distributionTempVariable$var15$78;
														if((0 == j)) {
															int traceTempVariable$var35$97_1 = distributionTempVariable$var15$78;
															if((0 == j)) {
																{
																	double var36 = ((((1.0 * v1) + traceTempVariable$var31$63_1) + traceTempVariable$var33$79_1) / traceTempVariable$var35$97_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample18Value77) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value77);
																}
															}
															if(!true) {
																for(int index$sample18$98 = 0; index$sample18$98 < weightings.length; index$sample18$98 += 1) {
																	int distributionTempVariable$var15$100 = index$sample18$98;
																	double cv$probabilitySample18Value99 = (cv$probabilitySample18Value77 * distribution$sample18[index$sample18$98]);
																	int traceTempVariable$var35$101_1 = distributionTempVariable$var15$100;
																	if((0 == j)) {
																		{
																			double var36 = ((((1.0 * v1) + traceTempVariable$var31$63_1) + traceTempVariable$var33$79_1) / traceTempVariable$var35$101_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value99) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value99);
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
					} else {
						if(true) {
							for(int index$sample12$54 = 0; index$sample12$54 < weightings.length; index$sample12$54 += 1) {
								int distributionTempVariable$v1$56 = index$sample12$54;
								double cv$probabilitySample12Value55 = (1.0 * distribution$sample12[index$sample12$54]);
								if(fixedFlag$sample26) {
									for(int i = 0; i < size; i += 1) {
										if(((i + 1) == j)) {
											if(fixedFlag$sample18) {
												if((0 == j)) {
													if((0 == j)) {
														{
															double var36 = ((((1.0 * distributionTempVariable$v1$56) + v2[j]) + v2[j]) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample12Value55) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample12Value55);
														}
													}
												}
											} else {
												if(true) {
													for(int index$sample18$81 = 0; index$sample18$81 < weightings.length; index$sample18$81 += 1) {
														int distributionTempVariable$var15$83 = index$sample18$81;
														double cv$probabilitySample18Value82 = (cv$probabilitySample12Value55 * distribution$sample18[index$sample18$81]);
														int traceTempVariable$var33$84_1 = distributionTempVariable$var15$83;
														if((0 == j)) {
															int traceTempVariable$var35$103_1 = distributionTempVariable$var15$83;
															if((0 == j)) {
																{
																	double var36 = ((((1.0 * distributionTempVariable$v1$56) + v2[j]) + traceTempVariable$var33$84_1) / traceTempVariable$var35$103_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample18Value82) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value82);
																}
															}
															if(!true) {
																for(int index$sample18$104 = 0; index$sample18$104 < weightings.length; index$sample18$104 += 1) {
																	int distributionTempVariable$var15$106 = index$sample18$104;
																	double cv$probabilitySample18Value105 = (cv$probabilitySample18Value82 * distribution$sample18[index$sample18$104]);
																	int traceTempVariable$var35$107_1 = distributionTempVariable$var15$106;
																	if((0 == j)) {
																		{
																			double var36 = ((((1.0 * distributionTempVariable$v1$56) + v2[j]) + traceTempVariable$var33$84_1) / traceTempVariable$var35$107_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value105) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value105);
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
									for(int i = 0; i < size; i += 1) {
										if(true) {
											for(int index$sample26$66 = 0; index$sample26$66 < weightings.length; index$sample26$66 += 1) {
												int distributionTempVariable$var23$68 = index$sample26$66;
												double cv$probabilitySample26Value67 = (cv$probabilitySample12Value55 * distribution$sample26[((i - 0) / 1)][index$sample26$66]);
												int traceTempVariable$var31$69_1 = distributionTempVariable$var23$68;
												if(((i + 1) == j)) {
													if(fixedFlag$sample18) {
														if((0 == j)) {
															if((0 == j)) {
																{
																	double var36 = ((((1.0 * distributionTempVariable$v1$56) + traceTempVariable$var31$69_1) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample26Value67) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value67);
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample18$86 = 0; index$sample18$86 < weightings.length; index$sample18$86 += 1) {
																int distributionTempVariable$var15$88 = index$sample18$86;
																double cv$probabilitySample18Value87 = (cv$probabilitySample26Value67 * distribution$sample18[index$sample18$86]);
																int traceTempVariable$var33$89_1 = distributionTempVariable$var15$88;
																if((0 == j)) {
																	int traceTempVariable$var35$109_1 = distributionTempVariable$var15$88;
																	if((0 == j)) {
																		{
																			double var36 = ((((1.0 * distributionTempVariable$v1$56) + traceTempVariable$var31$69_1) + traceTempVariable$var33$89_1) / traceTempVariable$var35$109_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value87) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value87);
																		}
																	}
																	if(!true) {
																		for(int index$sample18$110 = 0; index$sample18$110 < weightings.length; index$sample18$110 += 1) {
																			int distributionTempVariable$var15$112 = index$sample18$110;
																			double cv$probabilitySample18Value111 = (cv$probabilitySample18Value87 * distribution$sample18[index$sample18$110]);
																			int traceTempVariable$var35$113_1 = distributionTempVariable$var15$112;
																			if((0 == j)) {
																				{
																					double var36 = ((((1.0 * distributionTempVariable$v1$56) + traceTempVariable$var31$69_1) + traceTempVariable$var33$89_1) / traceTempVariable$var35$113_1);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample18Value111) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value111);
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
					if(fixedFlag$sample12) {
						if(fixedFlag$sample18) {
							if((0 == j)) {
								if(fixedFlag$sample26) {
									for(int i = 0; i < size; i += 1) {
										if(((i + 1) == j)) {
											if((0 == j)) {
												{
													double var36 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
													double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
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
									for(int i = 0; i < size; i += 1) {
										if(true) {
											for(int index$sample26$131 = 0; index$sample26$131 < weightings.length; index$sample26$131 += 1) {
												int distributionTempVariable$var23$133 = index$sample26$131;
												double cv$probabilitySample26Value132 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$131]);
												int traceTempVariable$var33$134_1 = distributionTempVariable$var23$133;
												if(((i + 1) == j)) {
													if((0 == j)) {
														{
															double var36 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var33$134_1) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample26Value132) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value132);
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
								for(int index$sample18$120 = 0; index$sample18$120 < weightings.length; index$sample18$120 += 1) {
									int distributionTempVariable$var15$122 = index$sample18$120;
									double cv$probabilitySample18Value121 = (1.0 * distribution$sample18[index$sample18$120]);
									int traceTempVariable$var31$123_1 = distributionTempVariable$var15$122;
									if((0 == j)) {
										if(fixedFlag$sample26) {
											for(int i = 0; i < size; i += 1) {
												if(((i + 1) == j)) {
													int traceTempVariable$var35$155_1 = distributionTempVariable$var15$122;
													if((0 == j)) {
														{
															double var36 = ((((1.0 * v1) + traceTempVariable$var31$123_1) + v2[j]) / traceTempVariable$var35$155_1);
															double cv$weightedProbability = (Math.log(cv$probabilitySample18Value121) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value121);
														}
													}
													if(!true) {
														for(int index$sample18$156 = 0; index$sample18$156 < weightings.length; index$sample18$156 += 1) {
															int distributionTempVariable$var15$158 = index$sample18$156;
															double cv$probabilitySample18Value157 = (cv$probabilitySample18Value121 * distribution$sample18[index$sample18$156]);
															int traceTempVariable$var35$159_1 = distributionTempVariable$var15$158;
															if((0 == j)) {
																{
																	double var36 = ((((1.0 * v1) + traceTempVariable$var31$123_1) + v2[j]) / traceTempVariable$var35$159_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample18Value157) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value157);
																}
															}
														}
													}
												}
											}
										} else {
											for(int i = 0; i < size; i += 1) {
												if(true) {
													for(int index$sample26$137 = 0; index$sample26$137 < weightings.length; index$sample26$137 += 1) {
														int distributionTempVariable$var23$139 = index$sample26$137;
														double cv$probabilitySample26Value138 = (cv$probabilitySample18Value121 * distribution$sample26[((i - 0) / 1)][index$sample26$137]);
														int traceTempVariable$var33$140_1 = distributionTempVariable$var23$139;
														if(((i + 1) == j)) {
															int traceTempVariable$var35$160_1 = distributionTempVariable$var15$122;
															if((0 == j)) {
																{
																	double var36 = ((((1.0 * v1) + traceTempVariable$var31$123_1) + traceTempVariable$var33$140_1) / traceTempVariable$var35$160_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample26Value138) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value138);
																}
															}
															if(!true) {
																for(int index$sample18$161 = 0; index$sample18$161 < weightings.length; index$sample18$161 += 1) {
																	int distributionTempVariable$var15$163 = index$sample18$161;
																	double cv$probabilitySample18Value162 = (cv$probabilitySample26Value138 * distribution$sample18[index$sample18$161]);
																	int traceTempVariable$var35$164_1 = distributionTempVariable$var15$163;
																	if((0 == j)) {
																		{
																			double var36 = ((((1.0 * v1) + traceTempVariable$var31$123_1) + traceTempVariable$var33$140_1) / traceTempVariable$var35$164_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value162) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value162);
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
					} else {
						if(true) {
							for(int index$sample12$115 = 0; index$sample12$115 < weightings.length; index$sample12$115 += 1) {
								int distributionTempVariable$v1$117 = index$sample12$115;
								double cv$probabilitySample12Value116 = (1.0 * distribution$sample12[index$sample12$115]);
								if(fixedFlag$sample18) {
									if((0 == j)) {
										if(fixedFlag$sample26) {
											for(int i = 0; i < size; i += 1) {
												if(((i + 1) == j)) {
													if((0 == j)) {
														{
															double var36 = ((((1.0 * distributionTempVariable$v1$117) + v2[j]) + v2[j]) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample12Value116) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample12Value116);
														}
													}
												}
											}
										} else {
											for(int i = 0; i < size; i += 1) {
												if(true) {
													for(int index$sample26$143 = 0; index$sample26$143 < weightings.length; index$sample26$143 += 1) {
														int distributionTempVariable$var23$145 = index$sample26$143;
														double cv$probabilitySample26Value144 = (cv$probabilitySample12Value116 * distribution$sample26[((i - 0) / 1)][index$sample26$143]);
														int traceTempVariable$var33$146_1 = distributionTempVariable$var23$145;
														if(((i + 1) == j)) {
															if((0 == j)) {
																{
																	double var36 = ((((1.0 * distributionTempVariable$v1$117) + v2[j]) + traceTempVariable$var33$146_1) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample26Value144) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value144);
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
										for(int index$sample18$125 = 0; index$sample18$125 < weightings.length; index$sample18$125 += 1) {
											int distributionTempVariable$var15$127 = index$sample18$125;
											double cv$probabilitySample18Value126 = (cv$probabilitySample12Value116 * distribution$sample18[index$sample18$125]);
											int traceTempVariable$var31$128_1 = distributionTempVariable$var15$127;
											if((0 == j)) {
												if(fixedFlag$sample26) {
													for(int i = 0; i < size; i += 1) {
														if(((i + 1) == j)) {
															int traceTempVariable$var35$167_1 = distributionTempVariable$var15$127;
															if((0 == j)) {
																{
																	double var36 = ((((1.0 * distributionTempVariable$v1$117) + traceTempVariable$var31$128_1) + v2[j]) / traceTempVariable$var35$167_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample18Value126) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value126);
																}
															}
															if(!true) {
																for(int index$sample18$168 = 0; index$sample18$168 < weightings.length; index$sample18$168 += 1) {
																	int distributionTempVariable$var15$170 = index$sample18$168;
																	double cv$probabilitySample18Value169 = (cv$probabilitySample18Value126 * distribution$sample18[index$sample18$168]);
																	int traceTempVariable$var35$171_1 = distributionTempVariable$var15$170;
																	if((0 == j)) {
																		{
																			double var36 = ((((1.0 * distributionTempVariable$v1$117) + traceTempVariable$var31$128_1) + v2[j]) / traceTempVariable$var35$171_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value169) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value169);
																		}
																	}
																}
															}
														}
													}
												} else {
													for(int i = 0; i < size; i += 1) {
														if(true) {
															for(int index$sample26$149 = 0; index$sample26$149 < weightings.length; index$sample26$149 += 1) {
																int distributionTempVariable$var23$151 = index$sample26$149;
																double cv$probabilitySample26Value150 = (cv$probabilitySample18Value126 * distribution$sample26[((i - 0) / 1)][index$sample26$149]);
																int traceTempVariable$var33$152_1 = distributionTempVariable$var23$151;
																if(((i + 1) == j)) {
																	int traceTempVariable$var35$172_1 = distributionTempVariable$var15$127;
																	if((0 == j)) {
																		{
																			double var36 = ((((1.0 * distributionTempVariable$v1$117) + traceTempVariable$var31$128_1) + traceTempVariable$var33$152_1) / traceTempVariable$var35$172_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample26Value150) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value150);
																		}
																	}
																	if(!true) {
																		for(int index$sample18$173 = 0; index$sample18$173 < weightings.length; index$sample18$173 += 1) {
																			int distributionTempVariable$var15$175 = index$sample18$173;
																			double cv$probabilitySample18Value174 = (cv$probabilitySample26Value150 * distribution$sample18[index$sample18$173]);
																			int traceTempVariable$var35$176_1 = distributionTempVariable$var15$175;
																			if((0 == j)) {
																				{
																					double var36 = ((((1.0 * distributionTempVariable$v1$117) + traceTempVariable$var31$128_1) + traceTempVariable$var33$152_1) / traceTempVariable$var35$176_1);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample18Value174) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value174);
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
					if(fixedFlag$sample12) {
						if(fixedFlag$sample26) {
							for(int i = 0; i < size; i += 1) {
								if(((i + 1) == j)) {
									for(int index$i$194_1 = 0; index$i$194_1 < size; index$i$194_1 += 1) {
										if(((index$i$194_1 + 1) == j)) {
											if(fixedFlag$sample18) {
												if((0 == j)) {
													{
														double var36 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
														double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
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
											} else {
												if(true) {
													for(int index$sample18$209 = 0; index$sample18$209 < weightings.length; index$sample18$209 += 1) {
														int distributionTempVariable$var15$211 = index$sample18$209;
														double cv$probabilitySample18Value210 = (1.0 * distribution$sample18[index$sample18$209]);
														int traceTempVariable$var35$212_1 = distributionTempVariable$var15$211;
														if((0 == j)) {
															{
																double var36 = ((((1.0 * v1) + v2[j]) + v2[j]) / traceTempVariable$var35$212_1);
																double cv$weightedProbability = (Math.log(cv$probabilitySample18Value210) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value210);
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
							for(int i = 0; i < size; i += 1) {
								if(true) {
									for(int index$sample26$184 = 0; index$sample26$184 < weightings.length; index$sample26$184 += 1) {
										int distributionTempVariable$var23$186 = index$sample26$184;
										double cv$probabilitySample26Value185 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$184]);
										int traceTempVariable$var31$187_1 = distributionTempVariable$var23$186;
										if(((i + 1) == j)) {
											int traceTempVariable$var33$195_1 = distributionTempVariable$var23$186;
											if(((i + 1) == j)) {
												if(fixedFlag$sample18) {
													if((0 == j)) {
														{
															double var36 = ((((1.0 * v1) + traceTempVariable$var31$187_1) + traceTempVariable$var33$195_1) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample26Value185) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value185);
														}
													}
												} else {
													if(true) {
														for(int index$sample18$214 = 0; index$sample18$214 < weightings.length; index$sample18$214 += 1) {
															int distributionTempVariable$var15$216 = index$sample18$214;
															double cv$probabilitySample18Value215 = (cv$probabilitySample26Value185 * distribution$sample18[index$sample18$214]);
															int traceTempVariable$var35$217_1 = distributionTempVariable$var15$216;
															if((0 == j)) {
																{
																	double var36 = ((((1.0 * v1) + traceTempVariable$var31$187_1) + traceTempVariable$var33$195_1) / traceTempVariable$var35$217_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample18Value215) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value215);
																}
															}
														}
													}
												}
											}
											for(int index$i$196 = 0; index$i$196 < size; index$i$196 += 1) {
												if(!(index$i$196 == i)) {
													for(int index$sample26$197 = 0; index$sample26$197 < weightings.length; index$sample26$197 += 1) {
														int distributionTempVariable$var23$199 = index$sample26$197;
														double cv$probabilitySample26Value198 = (cv$probabilitySample26Value185 * distribution$sample26[((index$i$196 - 0) / 1)][index$sample26$197]);
														int traceTempVariable$var33$200_1 = distributionTempVariable$var23$199;
														if(((index$i$196 + 1) == j)) {
															if(fixedFlag$sample18) {
																if((0 == j)) {
																	{
																		double var36 = ((((1.0 * v1) + traceTempVariable$var31$187_1) + traceTempVariable$var33$200_1) / v2[j]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample26Value198) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																		if((cv$weightedProbability < cv$distributionAccumulator))
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																		else {
																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																				cv$distributionAccumulator = cv$weightedProbability;
																			else
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																		}
																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value198);
																	}
																}
															} else {
																if(true) {
																	for(int index$sample18$219 = 0; index$sample18$219 < weightings.length; index$sample18$219 += 1) {
																		int distributionTempVariable$var15$221 = index$sample18$219;
																		double cv$probabilitySample18Value220 = (cv$probabilitySample26Value198 * distribution$sample18[index$sample18$219]);
																		int traceTempVariable$var35$222_1 = distributionTempVariable$var15$221;
																		if((0 == j)) {
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$187_1) + traceTempVariable$var33$200_1) / traceTempVariable$var35$222_1);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample18Value220) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value220);
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
					} else {
						if(true) {
							for(int index$sample12$178 = 0; index$sample12$178 < weightings.length; index$sample12$178 += 1) {
								int distributionTempVariable$v1$180 = index$sample12$178;
								double cv$probabilitySample12Value179 = (1.0 * distribution$sample12[index$sample12$178]);
								if(fixedFlag$sample26) {
									for(int i = 0; i < size; i += 1) {
										if(((i + 1) == j)) {
											for(int index$i$201_1 = 0; index$i$201_1 < size; index$i$201_1 += 1) {
												if(((index$i$201_1 + 1) == j)) {
													if(fixedFlag$sample18) {
														if((0 == j)) {
															{
																double var36 = ((((1.0 * distributionTempVariable$v1$180) + v2[j]) + v2[j]) / v2[j]);
																double cv$weightedProbability = (Math.log(cv$probabilitySample12Value179) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample12Value179);
															}
														}
													} else {
														if(true) {
															for(int index$sample18$224 = 0; index$sample18$224 < weightings.length; index$sample18$224 += 1) {
																int distributionTempVariable$var15$226 = index$sample18$224;
																double cv$probabilitySample18Value225 = (cv$probabilitySample12Value179 * distribution$sample18[index$sample18$224]);
																int traceTempVariable$var35$227_1 = distributionTempVariable$var15$226;
																if((0 == j)) {
																	{
																		double var36 = ((((1.0 * distributionTempVariable$v1$180) + v2[j]) + v2[j]) / traceTempVariable$var35$227_1);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample18Value225) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																		if((cv$weightedProbability < cv$distributionAccumulator))
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																		else {
																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																				cv$distributionAccumulator = cv$weightedProbability;
																			else
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																		}
																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value225);
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
									for(int i = 0; i < size; i += 1) {
										if(true) {
											for(int index$sample26$190 = 0; index$sample26$190 < weightings.length; index$sample26$190 += 1) {
												int distributionTempVariable$var23$192 = index$sample26$190;
												double cv$probabilitySample26Value191 = (cv$probabilitySample12Value179 * distribution$sample26[((i - 0) / 1)][index$sample26$190]);
												int traceTempVariable$var31$193_1 = distributionTempVariable$var23$192;
												if(((i + 1) == j)) {
													int traceTempVariable$var33$202_1 = distributionTempVariable$var23$192;
													if(((i + 1) == j)) {
														if(fixedFlag$sample18) {
															if((0 == j)) {
																{
																	double var36 = ((((1.0 * distributionTempVariable$v1$180) + traceTempVariable$var31$193_1) + traceTempVariable$var33$202_1) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample26Value191) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value191);
																}
															}
														} else {
															if(true) {
																for(int index$sample18$229 = 0; index$sample18$229 < weightings.length; index$sample18$229 += 1) {
																	int distributionTempVariable$var15$231 = index$sample18$229;
																	double cv$probabilitySample18Value230 = (cv$probabilitySample26Value191 * distribution$sample18[index$sample18$229]);
																	int traceTempVariable$var35$232_1 = distributionTempVariable$var15$231;
																	if((0 == j)) {
																		{
																			double var36 = ((((1.0 * distributionTempVariable$v1$180) + traceTempVariable$var31$193_1) + traceTempVariable$var33$202_1) / traceTempVariable$var35$232_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value230) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value230);
																		}
																	}
																}
															}
														}
													}
													for(int index$i$203 = 0; index$i$203 < size; index$i$203 += 1) {
														if(!(index$i$203 == i)) {
															for(int index$sample26$204 = 0; index$sample26$204 < weightings.length; index$sample26$204 += 1) {
																int distributionTempVariable$var23$206 = index$sample26$204;
																double cv$probabilitySample26Value205 = (cv$probabilitySample26Value191 * distribution$sample26[((index$i$203 - 0) / 1)][index$sample26$204]);
																int traceTempVariable$var33$207_1 = distributionTempVariable$var23$206;
																if(((index$i$203 + 1) == j)) {
																	if(fixedFlag$sample18) {
																		if((0 == j)) {
																			{
																				double var36 = ((((1.0 * distributionTempVariable$v1$180) + traceTempVariable$var31$193_1) + traceTempVariable$var33$207_1) / v2[j]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample26Value205) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value205);
																			}
																		}
																	} else {
																		if(true) {
																			for(int index$sample18$234 = 0; index$sample18$234 < weightings.length; index$sample18$234 += 1) {
																				int distributionTempVariable$var15$236 = index$sample18$234;
																				double cv$probabilitySample18Value235 = (cv$probabilitySample26Value205 * distribution$sample18[index$sample18$234]);
																				int traceTempVariable$var35$237_1 = distributionTempVariable$var15$236;
																				if((0 == j)) {
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$180) + traceTempVariable$var31$193_1) + traceTempVariable$var33$207_1) / traceTempVariable$var35$237_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample18Value235) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value235);
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
					if(fixedFlag$sample12) {
						if(fixedFlag$sample18) {
							if((0 == j)) {
								if((0 == j)) {
									if(fixedFlag$sample26) {
										for(int i = 0; i < size; i += 1) {
											if(((i + 1) == j)) {
												{
													double var36 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
													double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
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
										for(int i = 0; i < size; i += 1) {
											if(true) {
												for(int index$sample26$267 = 0; index$sample26$267 < weightings.length; index$sample26$267 += 1) {
													int distributionTempVariable$var23$269 = index$sample26$267;
													double cv$probabilitySample26Value268 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$267]);
													int traceTempVariable$var35$270_1 = distributionTempVariable$var23$269;
													if(((i + 1) == j)) {
														{
															double var36 = ((((1.0 * v1) + v2[j]) + v2[j]) / traceTempVariable$var35$270_1);
															double cv$weightedProbability = (Math.log(cv$probabilitySample26Value268) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value268);
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
								for(int index$sample18$244 = 0; index$sample18$244 < weightings.length; index$sample18$244 += 1) {
									int distributionTempVariable$var15$246 = index$sample18$244;
									double cv$probabilitySample18Value245 = (1.0 * distribution$sample18[index$sample18$244]);
									int traceTempVariable$var31$247_1 = distributionTempVariable$var15$246;
									if((0 == j)) {
										int traceTempVariable$var33$254_1 = distributionTempVariable$var15$246;
										if((0 == j)) {
											if(fixedFlag$sample26) {
												for(int i = 0; i < size; i += 1) {
													if(((i + 1) == j)) {
														{
															double var36 = ((((1.0 * v1) + traceTempVariable$var31$247_1) + traceTempVariable$var33$254_1) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample18Value245) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value245);
														}
													}
												}
											} else {
												for(int i = 0; i < size; i += 1) {
													if(true) {
														for(int index$sample26$273 = 0; index$sample26$273 < weightings.length; index$sample26$273 += 1) {
															int distributionTempVariable$var23$275 = index$sample26$273;
															double cv$probabilitySample26Value274 = (cv$probabilitySample18Value245 * distribution$sample26[((i - 0) / 1)][index$sample26$273]);
															int traceTempVariable$var35$276_1 = distributionTempVariable$var23$275;
															if(((i + 1) == j)) {
																{
																	double var36 = ((((1.0 * v1) + traceTempVariable$var31$247_1) + traceTempVariable$var33$254_1) / traceTempVariable$var35$276_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample26Value274) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value274);
																}
															}
														}
													}
												}
											}
										}
										if(!true) {
											for(int index$sample18$255 = 0; index$sample18$255 < weightings.length; index$sample18$255 += 1) {
												int distributionTempVariable$var15$257 = index$sample18$255;
												double cv$probabilitySample18Value256 = (cv$probabilitySample18Value245 * distribution$sample18[index$sample18$255]);
												int traceTempVariable$var33$258_1 = distributionTempVariable$var15$257;
												if((0 == j)) {
													if(fixedFlag$sample26) {
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == j)) {
																{
																	double var36 = ((((1.0 * v1) + traceTempVariable$var31$247_1) + traceTempVariable$var33$258_1) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample18Value256) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value256);
																}
															}
														}
													} else {
														for(int i = 0; i < size; i += 1) {
															if(true) {
																for(int index$sample26$279 = 0; index$sample26$279 < weightings.length; index$sample26$279 += 1) {
																	int distributionTempVariable$var23$281 = index$sample26$279;
																	double cv$probabilitySample26Value280 = (cv$probabilitySample18Value256 * distribution$sample26[((i - 0) / 1)][index$sample26$279]);
																	int traceTempVariable$var35$282_1 = distributionTempVariable$var23$281;
																	if(((i + 1) == j)) {
																		{
																			double var36 = ((((1.0 * v1) + traceTempVariable$var31$247_1) + traceTempVariable$var33$258_1) / traceTempVariable$var35$282_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample26Value280) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value280);
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
					} else {
						if(true) {
							for(int index$sample12$239 = 0; index$sample12$239 < weightings.length; index$sample12$239 += 1) {
								int distributionTempVariable$v1$241 = index$sample12$239;
								double cv$probabilitySample12Value240 = (1.0 * distribution$sample12[index$sample12$239]);
								if(fixedFlag$sample18) {
									if((0 == j)) {
										if((0 == j)) {
											if(fixedFlag$sample26) {
												for(int i = 0; i < size; i += 1) {
													if(((i + 1) == j)) {
														{
															double var36 = ((((1.0 * distributionTempVariable$v1$241) + v2[j]) + v2[j]) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample12Value240) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample12Value240);
														}
													}
												}
											} else {
												for(int i = 0; i < size; i += 1) {
													if(true) {
														for(int index$sample26$285 = 0; index$sample26$285 < weightings.length; index$sample26$285 += 1) {
															int distributionTempVariable$var23$287 = index$sample26$285;
															double cv$probabilitySample26Value286 = (cv$probabilitySample12Value240 * distribution$sample26[((i - 0) / 1)][index$sample26$285]);
															int traceTempVariable$var35$288_1 = distributionTempVariable$var23$287;
															if(((i + 1) == j)) {
																{
																	double var36 = ((((1.0 * distributionTempVariable$v1$241) + v2[j]) + v2[j]) / traceTempVariable$var35$288_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample26Value286) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value286);
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
										for(int index$sample18$249 = 0; index$sample18$249 < weightings.length; index$sample18$249 += 1) {
											int distributionTempVariable$var15$251 = index$sample18$249;
											double cv$probabilitySample18Value250 = (cv$probabilitySample12Value240 * distribution$sample18[index$sample18$249]);
											int traceTempVariable$var31$252_1 = distributionTempVariable$var15$251;
											if((0 == j)) {
												int traceTempVariable$var33$260_1 = distributionTempVariable$var15$251;
												if((0 == j)) {
													if(fixedFlag$sample26) {
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == j)) {
																{
																	double var36 = ((((1.0 * distributionTempVariable$v1$241) + traceTempVariable$var31$252_1) + traceTempVariable$var33$260_1) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample18Value250) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value250);
																}
															}
														}
													} else {
														for(int i = 0; i < size; i += 1) {
															if(true) {
																for(int index$sample26$291 = 0; index$sample26$291 < weightings.length; index$sample26$291 += 1) {
																	int distributionTempVariable$var23$293 = index$sample26$291;
																	double cv$probabilitySample26Value292 = (cv$probabilitySample18Value250 * distribution$sample26[((i - 0) / 1)][index$sample26$291]);
																	int traceTempVariable$var35$294_1 = distributionTempVariable$var23$293;
																	if(((i + 1) == j)) {
																		{
																			double var36 = ((((1.0 * distributionTempVariable$v1$241) + traceTempVariable$var31$252_1) + traceTempVariable$var33$260_1) / traceTempVariable$var35$294_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample26Value292) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value292);
																		}
																	}
																}
															}
														}
													}
												}
												if(!true) {
													for(int index$sample18$261 = 0; index$sample18$261 < weightings.length; index$sample18$261 += 1) {
														int distributionTempVariable$var15$263 = index$sample18$261;
														double cv$probabilitySample18Value262 = (cv$probabilitySample18Value250 * distribution$sample18[index$sample18$261]);
														int traceTempVariable$var33$264_1 = distributionTempVariable$var15$263;
														if((0 == j)) {
															if(fixedFlag$sample26) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == j)) {
																		{
																			double var36 = ((((1.0 * distributionTempVariable$v1$241) + traceTempVariable$var31$252_1) + traceTempVariable$var33$264_1) / v2[j]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value262) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value262);
																		}
																	}
																}
															} else {
																for(int i = 0; i < size; i += 1) {
																	if(true) {
																		for(int index$sample26$297 = 0; index$sample26$297 < weightings.length; index$sample26$297 += 1) {
																			int distributionTempVariable$var23$299 = index$sample26$297;
																			double cv$probabilitySample26Value298 = (cv$probabilitySample18Value262 * distribution$sample26[((i - 0) / 1)][index$sample26$297]);
																			int traceTempVariable$var35$300_1 = distributionTempVariable$var23$299;
																			if(((i + 1) == j)) {
																				{
																					double var36 = ((((1.0 * distributionTempVariable$v1$241) + traceTempVariable$var31$252_1) + traceTempVariable$var33$264_1) / traceTempVariable$var35$300_1);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample26Value298) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value298);
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
					if(fixedFlag$sample12) {
						if(fixedFlag$sample26) {
							for(int i = 0; i < size; i += 1) {
								if(((i + 1) == j)) {
									if(fixedFlag$sample18) {
										if((0 == j)) {
											for(int index$i$338_1 = 0; index$i$338_1 < size; index$i$338_1 += 1) {
												if(((index$i$338_1 + 1) == j)) {
													{
														double var36 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
														double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
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
											for(int index$sample18$319 = 0; index$sample18$319 < weightings.length; index$sample18$319 += 1) {
												int distributionTempVariable$var15$321 = index$sample18$319;
												double cv$probabilitySample18Value320 = (1.0 * distribution$sample18[index$sample18$319]);
												int traceTempVariable$var33$322_1 = distributionTempVariable$var15$321;
												if((0 == j)) {
													for(int index$i$339_1 = 0; index$i$339_1 < size; index$i$339_1 += 1) {
														if(((index$i$339_1 + 1) == j)) {
															{
																double var36 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var33$322_1) / v2[j]);
																double cv$weightedProbability = (Math.log(cv$probabilitySample18Value320) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value320);
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
							for(int i = 0; i < size; i += 1) {
								if(true) {
									for(int index$sample26$308 = 0; index$sample26$308 < weightings.length; index$sample26$308 += 1) {
										int distributionTempVariable$var23$310 = index$sample26$308;
										double cv$probabilitySample26Value309 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$308]);
										int traceTempVariable$var31$311_1 = distributionTempVariable$var23$310;
										if(((i + 1) == j)) {
											if(fixedFlag$sample18) {
												if((0 == j)) {
													int traceTempVariable$var35$340_1 = distributionTempVariable$var23$310;
													if(((i + 1) == j)) {
														{
															double var36 = ((((1.0 * v1) + traceTempVariable$var31$311_1) + v2[j]) / traceTempVariable$var35$340_1);
															double cv$weightedProbability = (Math.log(cv$probabilitySample26Value309) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value309);
														}
													}
													for(int index$i$341 = 0; index$i$341 < size; index$i$341 += 1) {
														if(!(index$i$341 == i)) {
															for(int index$sample26$342 = 0; index$sample26$342 < weightings.length; index$sample26$342 += 1) {
																int distributionTempVariable$var23$344 = index$sample26$342;
																double cv$probabilitySample26Value343 = (cv$probabilitySample26Value309 * distribution$sample26[((index$i$341 - 0) / 1)][index$sample26$342]);
																int traceTempVariable$var35$345_1 = distributionTempVariable$var23$344;
																if(((index$i$341 + 1) == j)) {
																	{
																		double var36 = ((((1.0 * v1) + traceTempVariable$var31$311_1) + v2[j]) / traceTempVariable$var35$345_1);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample26Value343) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																		if((cv$weightedProbability < cv$distributionAccumulator))
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																		else {
																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																				cv$distributionAccumulator = cv$weightedProbability;
																			else
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																		}
																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value343);
																	}
																}
															}
														}
													}
												}
											} else {
												if(true) {
													for(int index$sample18$324 = 0; index$sample18$324 < weightings.length; index$sample18$324 += 1) {
														int distributionTempVariable$var15$326 = index$sample18$324;
														double cv$probabilitySample18Value325 = (cv$probabilitySample26Value309 * distribution$sample18[index$sample18$324]);
														int traceTempVariable$var33$327_1 = distributionTempVariable$var15$326;
														if((0 == j)) {
															int traceTempVariable$var35$346_1 = distributionTempVariable$var23$310;
															if(((i + 1) == j)) {
																{
																	double var36 = ((((1.0 * v1) + traceTempVariable$var31$311_1) + traceTempVariable$var33$327_1) / traceTempVariable$var35$346_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample18Value325) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value325);
																}
															}
															for(int index$i$347 = 0; index$i$347 < size; index$i$347 += 1) {
																if(!(index$i$347 == i)) {
																	for(int index$sample26$348 = 0; index$sample26$348 < weightings.length; index$sample26$348 += 1) {
																		int distributionTempVariable$var23$350 = index$sample26$348;
																		double cv$probabilitySample26Value349 = (cv$probabilitySample18Value325 * distribution$sample26[((index$i$347 - 0) / 1)][index$sample26$348]);
																		int traceTempVariable$var35$351_1 = distributionTempVariable$var23$350;
																		if(((index$i$347 + 1) == j)) {
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$311_1) + traceTempVariable$var33$327_1) / traceTempVariable$var35$351_1);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample26Value349) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value349);
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
					} else {
						if(true) {
							for(int index$sample12$302 = 0; index$sample12$302 < weightings.length; index$sample12$302 += 1) {
								int distributionTempVariable$v1$304 = index$sample12$302;
								double cv$probabilitySample12Value303 = (1.0 * distribution$sample12[index$sample12$302]);
								if(fixedFlag$sample26) {
									for(int i = 0; i < size; i += 1) {
										if(((i + 1) == j)) {
											if(fixedFlag$sample18) {
												if((0 == j)) {
													for(int index$i$352_1 = 0; index$i$352_1 < size; index$i$352_1 += 1) {
														if(((index$i$352_1 + 1) == j)) {
															{
																double var36 = ((((1.0 * distributionTempVariable$v1$304) + v2[j]) + v2[j]) / v2[j]);
																double cv$weightedProbability = (Math.log(cv$probabilitySample12Value303) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample12Value303);
															}
														}
													}
												}
											} else {
												if(true) {
													for(int index$sample18$329 = 0; index$sample18$329 < weightings.length; index$sample18$329 += 1) {
														int distributionTempVariable$var15$331 = index$sample18$329;
														double cv$probabilitySample18Value330 = (cv$probabilitySample12Value303 * distribution$sample18[index$sample18$329]);
														int traceTempVariable$var33$332_1 = distributionTempVariable$var15$331;
														if((0 == j)) {
															for(int index$i$353_1 = 0; index$i$353_1 < size; index$i$353_1 += 1) {
																if(((index$i$353_1 + 1) == j)) {
																	{
																		double var36 = ((((1.0 * distributionTempVariable$v1$304) + v2[j]) + traceTempVariable$var33$332_1) / v2[j]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample18Value330) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																		if((cv$weightedProbability < cv$distributionAccumulator))
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																		else {
																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																				cv$distributionAccumulator = cv$weightedProbability;
																			else
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																		}
																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value330);
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
									for(int i = 0; i < size; i += 1) {
										if(true) {
											for(int index$sample26$314 = 0; index$sample26$314 < weightings.length; index$sample26$314 += 1) {
												int distributionTempVariable$var23$316 = index$sample26$314;
												double cv$probabilitySample26Value315 = (cv$probabilitySample12Value303 * distribution$sample26[((i - 0) / 1)][index$sample26$314]);
												int traceTempVariable$var31$317_1 = distributionTempVariable$var23$316;
												if(((i + 1) == j)) {
													if(fixedFlag$sample18) {
														if((0 == j)) {
															int traceTempVariable$var35$354_1 = distributionTempVariable$var23$316;
															if(((i + 1) == j)) {
																{
																	double var36 = ((((1.0 * distributionTempVariable$v1$304) + traceTempVariable$var31$317_1) + v2[j]) / traceTempVariable$var35$354_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample26Value315) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value315);
																}
															}
															for(int index$i$355 = 0; index$i$355 < size; index$i$355 += 1) {
																if(!(index$i$355 == i)) {
																	for(int index$sample26$356 = 0; index$sample26$356 < weightings.length; index$sample26$356 += 1) {
																		int distributionTempVariable$var23$358 = index$sample26$356;
																		double cv$probabilitySample26Value357 = (cv$probabilitySample26Value315 * distribution$sample26[((index$i$355 - 0) / 1)][index$sample26$356]);
																		int traceTempVariable$var35$359_1 = distributionTempVariable$var23$358;
																		if(((index$i$355 + 1) == j)) {
																			{
																				double var36 = ((((1.0 * distributionTempVariable$v1$304) + traceTempVariable$var31$317_1) + v2[j]) / traceTempVariable$var35$359_1);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample26Value357) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value357);
																			}
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample18$334 = 0; index$sample18$334 < weightings.length; index$sample18$334 += 1) {
																int distributionTempVariable$var15$336 = index$sample18$334;
																double cv$probabilitySample18Value335 = (cv$probabilitySample26Value315 * distribution$sample18[index$sample18$334]);
																int traceTempVariable$var33$337_1 = distributionTempVariable$var15$336;
																if((0 == j)) {
																	int traceTempVariable$var35$360_1 = distributionTempVariable$var23$316;
																	if(((i + 1) == j)) {
																		{
																			double var36 = ((((1.0 * distributionTempVariable$v1$304) + traceTempVariable$var31$317_1) + traceTempVariable$var33$337_1) / traceTempVariable$var35$360_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value335) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value335);
																		}
																	}
																	for(int index$i$361 = 0; index$i$361 < size; index$i$361 += 1) {
																		if(!(index$i$361 == i)) {
																			for(int index$sample26$362 = 0; index$sample26$362 < weightings.length; index$sample26$362 += 1) {
																				int distributionTempVariable$var23$364 = index$sample26$362;
																				double cv$probabilitySample26Value363 = (cv$probabilitySample18Value335 * distribution$sample26[((index$i$361 - 0) / 1)][index$sample26$362]);
																				int traceTempVariable$var35$365_1 = distributionTempVariable$var23$364;
																				if(((index$i$361 + 1) == j)) {
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$304) + traceTempVariable$var31$317_1) + traceTempVariable$var33$337_1) / traceTempVariable$var35$365_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample26Value363) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value363);
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
					if(fixedFlag$sample12) {
						if(fixedFlag$sample18) {
							if((0 == j)) {
								if(fixedFlag$sample26) {
									for(int i = 0; i < size; i += 1) {
										if(((i + 1) == j)) {
											for(int index$i$405_1 = 0; index$i$405_1 < size; index$i$405_1 += 1) {
												if(((index$i$405_1 + 1) == j)) {
													{
														double var36 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
														double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
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
									for(int i = 0; i < size; i += 1) {
										if(true) {
											for(int index$sample26$383 = 0; index$sample26$383 < weightings.length; index$sample26$383 += 1) {
												int distributionTempVariable$var23$385 = index$sample26$383;
												double cv$probabilitySample26Value384 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$383]);
												int traceTempVariable$var33$386_1 = distributionTempVariable$var23$385;
												if(((i + 1) == j)) {
													int traceTempVariable$var35$406_1 = distributionTempVariable$var23$385;
													if(((i + 1) == j)) {
														{
															double var36 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var33$386_1) / traceTempVariable$var35$406_1);
															double cv$weightedProbability = (Math.log(cv$probabilitySample26Value384) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value384);
														}
													}
													for(int index$i$407 = 0; index$i$407 < size; index$i$407 += 1) {
														if(!(index$i$407 == i)) {
															for(int index$sample26$408 = 0; index$sample26$408 < weightings.length; index$sample26$408 += 1) {
																int distributionTempVariable$var23$410 = index$sample26$408;
																double cv$probabilitySample26Value409 = (cv$probabilitySample26Value384 * distribution$sample26[((index$i$407 - 0) / 1)][index$sample26$408]);
																int traceTempVariable$var35$411_1 = distributionTempVariable$var23$410;
																if(((index$i$407 + 1) == j)) {
																	{
																		double var36 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var33$386_1) / traceTempVariable$var35$411_1);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample26Value409) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																		if((cv$weightedProbability < cv$distributionAccumulator))
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																		else {
																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																				cv$distributionAccumulator = cv$weightedProbability;
																			else
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																		}
																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value409);
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
							if(true) {
								for(int index$sample18$372 = 0; index$sample18$372 < weightings.length; index$sample18$372 += 1) {
									int distributionTempVariable$var15$374 = index$sample18$372;
									double cv$probabilitySample18Value373 = (1.0 * distribution$sample18[index$sample18$372]);
									int traceTempVariable$var31$375_1 = distributionTempVariable$var15$374;
									if((0 == j)) {
										if(fixedFlag$sample26) {
											for(int i = 0; i < size; i += 1) {
												if(((i + 1) == j)) {
													for(int index$i$412_1 = 0; index$i$412_1 < size; index$i$412_1 += 1) {
														if(((index$i$412_1 + 1) == j)) {
															{
																double var36 = ((((1.0 * v1) + traceTempVariable$var31$375_1) + v2[j]) / v2[j]);
																double cv$weightedProbability = (Math.log(cv$probabilitySample18Value373) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value373);
															}
														}
													}
												}
											}
										} else {
											for(int i = 0; i < size; i += 1) {
												if(true) {
													for(int index$sample26$389 = 0; index$sample26$389 < weightings.length; index$sample26$389 += 1) {
														int distributionTempVariable$var23$391 = index$sample26$389;
														double cv$probabilitySample26Value390 = (cv$probabilitySample18Value373 * distribution$sample26[((i - 0) / 1)][index$sample26$389]);
														int traceTempVariable$var33$392_1 = distributionTempVariable$var23$391;
														if(((i + 1) == j)) {
															int traceTempVariable$var35$413_1 = distributionTempVariable$var23$391;
															if(((i + 1) == j)) {
																{
																	double var36 = ((((1.0 * v1) + traceTempVariable$var31$375_1) + traceTempVariable$var33$392_1) / traceTempVariable$var35$413_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample26Value390) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value390);
																}
															}
															for(int index$i$414 = 0; index$i$414 < size; index$i$414 += 1) {
																if(!(index$i$414 == i)) {
																	for(int index$sample26$415 = 0; index$sample26$415 < weightings.length; index$sample26$415 += 1) {
																		int distributionTempVariable$var23$417 = index$sample26$415;
																		double cv$probabilitySample26Value416 = (cv$probabilitySample26Value390 * distribution$sample26[((index$i$414 - 0) / 1)][index$sample26$415]);
																		int traceTempVariable$var35$418_1 = distributionTempVariable$var23$417;
																		if(((index$i$414 + 1) == j)) {
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$375_1) + traceTempVariable$var33$392_1) / traceTempVariable$var35$418_1);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample26Value416) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value416);
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
					} else {
						if(true) {
							for(int index$sample12$367 = 0; index$sample12$367 < weightings.length; index$sample12$367 += 1) {
								int distributionTempVariable$v1$369 = index$sample12$367;
								double cv$probabilitySample12Value368 = (1.0 * distribution$sample12[index$sample12$367]);
								if(fixedFlag$sample18) {
									if((0 == j)) {
										if(fixedFlag$sample26) {
											for(int i = 0; i < size; i += 1) {
												if(((i + 1) == j)) {
													for(int index$i$419_1 = 0; index$i$419_1 < size; index$i$419_1 += 1) {
														if(((index$i$419_1 + 1) == j)) {
															{
																double var36 = ((((1.0 * distributionTempVariable$v1$369) + v2[j]) + v2[j]) / v2[j]);
																double cv$weightedProbability = (Math.log(cv$probabilitySample12Value368) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample12Value368);
															}
														}
													}
												}
											}
										} else {
											for(int i = 0; i < size; i += 1) {
												if(true) {
													for(int index$sample26$395 = 0; index$sample26$395 < weightings.length; index$sample26$395 += 1) {
														int distributionTempVariable$var23$397 = index$sample26$395;
														double cv$probabilitySample26Value396 = (cv$probabilitySample12Value368 * distribution$sample26[((i - 0) / 1)][index$sample26$395]);
														int traceTempVariable$var33$398_1 = distributionTempVariable$var23$397;
														if(((i + 1) == j)) {
															int traceTempVariable$var35$420_1 = distributionTempVariable$var23$397;
															if(((i + 1) == j)) {
																{
																	double var36 = ((((1.0 * distributionTempVariable$v1$369) + v2[j]) + traceTempVariable$var33$398_1) / traceTempVariable$var35$420_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample26Value396) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value396);
																}
															}
															for(int index$i$421 = 0; index$i$421 < size; index$i$421 += 1) {
																if(!(index$i$421 == i)) {
																	for(int index$sample26$422 = 0; index$sample26$422 < weightings.length; index$sample26$422 += 1) {
																		int distributionTempVariable$var23$424 = index$sample26$422;
																		double cv$probabilitySample26Value423 = (cv$probabilitySample26Value396 * distribution$sample26[((index$i$421 - 0) / 1)][index$sample26$422]);
																		int traceTempVariable$var35$425_1 = distributionTempVariable$var23$424;
																		if(((index$i$421 + 1) == j)) {
																			{
																				double var36 = ((((1.0 * distributionTempVariable$v1$369) + v2[j]) + traceTempVariable$var33$398_1) / traceTempVariable$var35$425_1);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample26Value423) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value423);
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
									if(true) {
										for(int index$sample18$377 = 0; index$sample18$377 < weightings.length; index$sample18$377 += 1) {
											int distributionTempVariable$var15$379 = index$sample18$377;
											double cv$probabilitySample18Value378 = (cv$probabilitySample12Value368 * distribution$sample18[index$sample18$377]);
											int traceTempVariable$var31$380_1 = distributionTempVariable$var15$379;
											if((0 == j)) {
												if(fixedFlag$sample26) {
													for(int i = 0; i < size; i += 1) {
														if(((i + 1) == j)) {
															for(int index$i$426_1 = 0; index$i$426_1 < size; index$i$426_1 += 1) {
																if(((index$i$426_1 + 1) == j)) {
																	{
																		double var36 = ((((1.0 * distributionTempVariable$v1$369) + traceTempVariable$var31$380_1) + v2[j]) / v2[j]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample18Value378) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																		if((cv$weightedProbability < cv$distributionAccumulator))
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																		else {
																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																				cv$distributionAccumulator = cv$weightedProbability;
																			else
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																		}
																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value378);
																	}
																}
															}
														}
													}
												} else {
													for(int i = 0; i < size; i += 1) {
														if(true) {
															for(int index$sample26$401 = 0; index$sample26$401 < weightings.length; index$sample26$401 += 1) {
																int distributionTempVariable$var23$403 = index$sample26$401;
																double cv$probabilitySample26Value402 = (cv$probabilitySample18Value378 * distribution$sample26[((i - 0) / 1)][index$sample26$401]);
																int traceTempVariable$var33$404_1 = distributionTempVariable$var23$403;
																if(((i + 1) == j)) {
																	int traceTempVariable$var35$427_1 = distributionTempVariable$var23$403;
																	if(((i + 1) == j)) {
																		{
																			double var36 = ((((1.0 * distributionTempVariable$v1$369) + traceTempVariable$var31$380_1) + traceTempVariable$var33$404_1) / traceTempVariable$var35$427_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample26Value402) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value402);
																		}
																	}
																	for(int index$i$428 = 0; index$i$428 < size; index$i$428 += 1) {
																		if(!(index$i$428 == i)) {
																			for(int index$sample26$429 = 0; index$sample26$429 < weightings.length; index$sample26$429 += 1) {
																				int distributionTempVariable$var23$431 = index$sample26$429;
																				double cv$probabilitySample26Value430 = (cv$probabilitySample26Value402 * distribution$sample26[((index$i$428 - 0) / 1)][index$sample26$429]);
																				int traceTempVariable$var35$432_1 = distributionTempVariable$var23$431;
																				if(((index$i$428 + 1) == j)) {
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$369) + traceTempVariable$var31$380_1) + traceTempVariable$var33$404_1) / traceTempVariable$var35$432_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample26Value430) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value430);
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
					if(fixedFlag$sample12) {
						if(fixedFlag$sample26) {
							for(int i = 0; i < size; i += 1) {
								if(((i + 1) == j)) {
									for(int index$i$450_1 = 0; index$i$450_1 < size; index$i$450_1 += 1) {
										if(((index$i$450_1 + 1) == j)) {
											for(int index$i$464_1 = 0; index$i$464_1 < size; index$i$464_1 += 1) {
												if(((index$i$464_1 + 1) == j)) {
													{
														double var36 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
														double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
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
							for(int i = 0; i < size; i += 1) {
								if(true) {
									for(int index$sample26$440 = 0; index$sample26$440 < weightings.length; index$sample26$440 += 1) {
										int distributionTempVariable$var23$442 = index$sample26$440;
										double cv$probabilitySample26Value441 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$440]);
										int traceTempVariable$var31$443_1 = distributionTempVariable$var23$442;
										if(((i + 1) == j)) {
											int traceTempVariable$var33$451_1 = distributionTempVariable$var23$442;
											if(((i + 1) == j)) {
												int traceTempVariable$var35$465_1 = distributionTempVariable$var23$442;
												if(((i + 1) == j)) {
													{
														double var36 = ((((1.0 * v1) + traceTempVariable$var31$443_1) + traceTempVariable$var33$451_1) / traceTempVariable$var35$465_1);
														double cv$weightedProbability = (Math.log(cv$probabilitySample26Value441) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
														if((cv$weightedProbability < cv$distributionAccumulator))
															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
														else {
															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																cv$distributionAccumulator = cv$weightedProbability;
															else
																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
														}
														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value441);
													}
												}
												for(int index$i$466 = 0; index$i$466 < size; index$i$466 += 1) {
													if(!(index$i$466 == i)) {
														for(int index$sample26$467 = 0; index$sample26$467 < weightings.length; index$sample26$467 += 1) {
															int distributionTempVariable$var23$469 = index$sample26$467;
															double cv$probabilitySample26Value468 = (cv$probabilitySample26Value441 * distribution$sample26[((index$i$466 - 0) / 1)][index$sample26$467]);
															int traceTempVariable$var35$470_1 = distributionTempVariable$var23$469;
															if(((index$i$466 + 1) == j)) {
																{
																	double var36 = ((((1.0 * v1) + traceTempVariable$var31$443_1) + traceTempVariable$var33$451_1) / traceTempVariable$var35$470_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample26Value468) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value468);
																}
															}
														}
													}
												}
											}
											for(int index$i$452 = 0; index$i$452 < size; index$i$452 += 1) {
												if(!(index$i$452 == i)) {
													for(int index$sample26$453 = 0; index$sample26$453 < weightings.length; index$sample26$453 += 1) {
														int distributionTempVariable$var23$455 = index$sample26$453;
														double cv$probabilitySample26Value454 = (cv$probabilitySample26Value441 * distribution$sample26[((index$i$452 - 0) / 1)][index$sample26$453]);
														int traceTempVariable$var33$456_1 = distributionTempVariable$var23$455;
														if(((index$i$452 + 1) == j)) {
															int traceTempVariable$var35$471_1 = distributionTempVariable$var23$442;
															if(((i + 1) == j)) {
																{
																	double var36 = ((((1.0 * v1) + traceTempVariable$var31$443_1) + traceTempVariable$var33$456_1) / traceTempVariable$var35$471_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample26Value454) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value454);
																}
															}
															int traceTempVariable$var35$472_1 = distributionTempVariable$var23$455;
															if(((index$i$452 + 1) == j)) {
																{
																	double var36 = ((((1.0 * v1) + traceTempVariable$var31$443_1) + traceTempVariable$var33$456_1) / traceTempVariable$var35$472_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample26Value454) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value454);
																}
															}
															for(int index$i$473 = 0; index$i$473 < size; index$i$473 += 1) {
																if((!(index$i$473 == i) && !(index$i$473 == index$i$452))) {
																	for(int index$sample26$474 = 0; index$sample26$474 < weightings.length; index$sample26$474 += 1) {
																		int distributionTempVariable$var23$476 = index$sample26$474;
																		double cv$probabilitySample26Value475 = (cv$probabilitySample26Value454 * distribution$sample26[((index$i$473 - 0) / 1)][index$sample26$474]);
																		int traceTempVariable$var35$477_1 = distributionTempVariable$var23$476;
																		if(((index$i$473 + 1) == j)) {
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$443_1) + traceTempVariable$var33$456_1) / traceTempVariable$var35$477_1);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample26Value475) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value475);
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
					} else {
						if(true) {
							for(int index$sample12$434 = 0; index$sample12$434 < weightings.length; index$sample12$434 += 1) {
								int distributionTempVariable$v1$436 = index$sample12$434;
								double cv$probabilitySample12Value435 = (1.0 * distribution$sample12[index$sample12$434]);
								if(fixedFlag$sample26) {
									for(int i = 0; i < size; i += 1) {
										if(((i + 1) == j)) {
											for(int index$i$457_1 = 0; index$i$457_1 < size; index$i$457_1 += 1) {
												if(((index$i$457_1 + 1) == j)) {
													for(int index$i$478_1 = 0; index$i$478_1 < size; index$i$478_1 += 1) {
														if(((index$i$478_1 + 1) == j)) {
															{
																double var36 = ((((1.0 * distributionTempVariable$v1$436) + v2[j]) + v2[j]) / v2[j]);
																double cv$weightedProbability = (Math.log(cv$probabilitySample12Value435) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample12Value435);
															}
														}
													}
												}
											}
										}
									}
								} else {
									for(int i = 0; i < size; i += 1) {
										if(true) {
											for(int index$sample26$446 = 0; index$sample26$446 < weightings.length; index$sample26$446 += 1) {
												int distributionTempVariable$var23$448 = index$sample26$446;
												double cv$probabilitySample26Value447 = (cv$probabilitySample12Value435 * distribution$sample26[((i - 0) / 1)][index$sample26$446]);
												int traceTempVariable$var31$449_1 = distributionTempVariable$var23$448;
												if(((i + 1) == j)) {
													int traceTempVariable$var33$458_1 = distributionTempVariable$var23$448;
													if(((i + 1) == j)) {
														int traceTempVariable$var35$479_1 = distributionTempVariable$var23$448;
														if(((i + 1) == j)) {
															{
																double var36 = ((((1.0 * distributionTempVariable$v1$436) + traceTempVariable$var31$449_1) + traceTempVariable$var33$458_1) / traceTempVariable$var35$479_1);
																double cv$weightedProbability = (Math.log(cv$probabilitySample26Value447) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value447);
															}
														}
														for(int index$i$480 = 0; index$i$480 < size; index$i$480 += 1) {
															if(!(index$i$480 == i)) {
																for(int index$sample26$481 = 0; index$sample26$481 < weightings.length; index$sample26$481 += 1) {
																	int distributionTempVariable$var23$483 = index$sample26$481;
																	double cv$probabilitySample26Value482 = (cv$probabilitySample26Value447 * distribution$sample26[((index$i$480 - 0) / 1)][index$sample26$481]);
																	int traceTempVariable$var35$484_1 = distributionTempVariable$var23$483;
																	if(((index$i$480 + 1) == j)) {
																		{
																			double var36 = ((((1.0 * distributionTempVariable$v1$436) + traceTempVariable$var31$449_1) + traceTempVariable$var33$458_1) / traceTempVariable$var35$484_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample26Value482) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value482);
																		}
																	}
																}
															}
														}
													}
													for(int index$i$459 = 0; index$i$459 < size; index$i$459 += 1) {
														if(!(index$i$459 == i)) {
															for(int index$sample26$460 = 0; index$sample26$460 < weightings.length; index$sample26$460 += 1) {
																int distributionTempVariable$var23$462 = index$sample26$460;
																double cv$probabilitySample26Value461 = (cv$probabilitySample26Value447 * distribution$sample26[((index$i$459 - 0) / 1)][index$sample26$460]);
																int traceTempVariable$var33$463_1 = distributionTempVariable$var23$462;
																if(((index$i$459 + 1) == j)) {
																	int traceTempVariable$var35$485_1 = distributionTempVariable$var23$448;
																	if(((i + 1) == j)) {
																		{
																			double var36 = ((((1.0 * distributionTempVariable$v1$436) + traceTempVariable$var31$449_1) + traceTempVariable$var33$463_1) / traceTempVariable$var35$485_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample26Value461) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value461);
																		}
																	}
																	int traceTempVariable$var35$486_1 = distributionTempVariable$var23$462;
																	if(((index$i$459 + 1) == j)) {
																		{
																			double var36 = ((((1.0 * distributionTempVariable$v1$436) + traceTempVariable$var31$449_1) + traceTempVariable$var33$463_1) / traceTempVariable$var35$486_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample26Value461) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value461);
																		}
																	}
																	for(int index$i$487 = 0; index$i$487 < size; index$i$487 += 1) {
																		if((!(index$i$487 == i) && !(index$i$487 == index$i$459))) {
																			for(int index$sample26$488 = 0; index$sample26$488 < weightings.length; index$sample26$488 += 1) {
																				int distributionTempVariable$var23$490 = index$sample26$488;
																				double cv$probabilitySample26Value489 = (cv$probabilitySample26Value461 * distribution$sample26[((index$i$487 - 0) / 1)][index$sample26$488]);
																				int traceTempVariable$var35$491_1 = distributionTempVariable$var23$490;
																				if(((index$i$487 + 1) == j)) {
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$436) + traceTempVariable$var31$449_1) + traceTempVariable$var33$463_1) / traceTempVariable$var35$491_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample26Value489) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value489);
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var37[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample41[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample41 = (((fixedFlag$sample41 && fixedFlag$sample12) && fixedFlag$sample18) && fixedFlag$sample26);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample41[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var37[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample12() {
		if(!fixedProbFlag$sample12) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = v1;
				{
					{
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var8 = cv$sampleAccumulator;
			logProbability$v1 = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample12 = fixedFlag$sample12;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$v1;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var8 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample18() {
		if(!fixedProbFlag$sample18) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = v2[0];
				{
					{
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var14 = cv$sampleAccumulator;
			logProbability$var15 = cv$sampleProbability;
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample18 = fixedFlag$sample18;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var15;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var14 = cv$rvAccumulator;
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample26() {
		if(!fixedProbFlag$sample26) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$i$1 = i;
				{
					int cv$sampleValue = v2[(i + 1)];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var22[((i - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample26[((i - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample26 = fixedFlag$sample26;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample26[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var22[((i - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample41() {
		if(!fixedProbFlag$sample41) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = v[j];
					{
						{
							double var36 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var36));
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
				logProbability$var37[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample41[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample41 = (((fixedFlag$sample41 && fixedFlag$sample12) && fixedFlag$sample18) && fixedFlag$sample26);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample41[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var37[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample12() {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, weightings.length);
		}
		double[] cv$stateProbabilityLocal = cv$var9$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$weightings;
				{
					cv$temp$0$weightings = weightings;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$weightings.length))?Math.log(cv$temp$0$weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						for(int j = 0; j < size; j += 1) {
							int traceTempVariable$v1$1_2 = cv$currentValue;
							{
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								{
									if(fixedFlag$sample18) {
										if((0 == j)) {
											if((0 == j)) {
												if((0 == j)) {
													{
														{
															double cv$temp$1$var36;
															{
																double var36 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																cv$temp$1$var36 = var36;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var36)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var36));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var36)));
															}
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
														}
													}
												}
											}
										}
									} else {
										if(true) {
											for(int index$sample18$4 = 0; index$sample18$4 < weightings.length; index$sample18$4 += 1) {
												int distributionTempVariable$var15$6 = index$sample18$4;
												double cv$probabilitySample18Value5 = (1.0 * distribution$sample18[index$sample18$4]);
												int traceTempVariable$var31$7_1 = distributionTempVariable$var15$6;
												if((0 == j)) {
													int traceTempVariable$var33$9_1 = distributionTempVariable$var15$6;
													if((0 == j)) {
														int traceTempVariable$var35$15_1 = distributionTempVariable$var15$6;
														if((0 == j)) {
															{
																{
																	double cv$temp$2$var36;
																	{
																		double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$7_1) + traceTempVariable$var33$9_1) / traceTempVariable$var35$15_1);
																		cv$temp$2$var36 = var36;
																	}
																	if(((Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var36)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var36));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var36)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value5);
																}
															}
														}
														if(!true) {
															for(int index$sample18$16 = 0; index$sample18$16 < weightings.length; index$sample18$16 += 1) {
																int distributionTempVariable$var15$18 = index$sample18$16;
																double cv$probabilitySample18Value17 = (cv$probabilitySample18Value5 * distribution$sample18[index$sample18$16]);
																int traceTempVariable$var35$19_1 = distributionTempVariable$var15$18;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$3$var36;
																			{
																				double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$7_1) + traceTempVariable$var33$9_1) / traceTempVariable$var35$19_1);
																				cv$temp$3$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value17);
																		}
																	}
																}
															}
														}
													}
													if(!true) {
														for(int index$sample18$10 = 0; index$sample18$10 < weightings.length; index$sample18$10 += 1) {
															int distributionTempVariable$var15$12 = index$sample18$10;
															double cv$probabilitySample18Value11 = (cv$probabilitySample18Value5 * distribution$sample18[index$sample18$10]);
															int traceTempVariable$var33$13_1 = distributionTempVariable$var15$12;
															if((0 == j)) {
																int traceTempVariable$var35$20_1 = distributionTempVariable$var15$6;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$4$var36;
																			{
																				double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$7_1) + traceTempVariable$var33$13_1) / traceTempVariable$var35$20_1);
																				cv$temp$4$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample18Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value11);
																		}
																	}
																}
																int traceTempVariable$var35$21_1 = distributionTempVariable$var15$12;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$5$var36;
																			{
																				double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$7_1) + traceTempVariable$var33$13_1) / traceTempVariable$var35$21_1);
																				cv$temp$5$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample18Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value11);
																		}
																	}
																}
																if(!true) {
																	for(int index$sample18$22 = 0; index$sample18$22 < weightings.length; index$sample18$22 += 1) {
																		int distributionTempVariable$var15$24 = index$sample18$22;
																		double cv$probabilitySample18Value23 = (cv$probabilitySample18Value11 * distribution$sample18[index$sample18$22]);
																		int traceTempVariable$var35$25_1 = distributionTempVariable$var15$24;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$6$var36;
																					{
																						double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$7_1) + traceTempVariable$var33$13_1) / traceTempVariable$var35$25_1);
																						cv$temp$6$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample18Value23) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value23) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value23) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value23) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value23) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value23);
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
									if(fixedFlag$sample26) {
										for(int i = 0; i < size; i += 1) {
											if(((i + 1) == j)) {
												if(fixedFlag$sample18) {
													if((0 == j)) {
														if((0 == j)) {
															{
																{
																	double cv$temp$7$var36;
																	{
																		double var36 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																		cv$temp$7$var36 = var36;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var36)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var36));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var36)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												} else {
													if(true) {
														for(int index$sample18$33 = 0; index$sample18$33 < weightings.length; index$sample18$33 += 1) {
															int distributionTempVariable$var15$35 = index$sample18$33;
															double cv$probabilitySample18Value34 = (1.0 * distribution$sample18[index$sample18$33]);
															int traceTempVariable$var33$36_1 = distributionTempVariable$var15$35;
															if((0 == j)) {
																int traceTempVariable$var35$43_1 = distributionTempVariable$var15$35;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$8$var36;
																			{
																				double var36 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var33$36_1) / traceTempVariable$var35$43_1);
																				cv$temp$8$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample18Value34) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value34) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value34) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value34) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value34) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value34);
																		}
																	}
																}
																if(!true) {
																	for(int index$sample18$44 = 0; index$sample18$44 < weightings.length; index$sample18$44 += 1) {
																		int distributionTempVariable$var15$46 = index$sample18$44;
																		double cv$probabilitySample18Value45 = (cv$probabilitySample18Value34 * distribution$sample18[index$sample18$44]);
																		int traceTempVariable$var35$47_1 = distributionTempVariable$var15$46;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$9$var36;
																					{
																						double var36 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var33$36_1) / traceTempVariable$var35$47_1);
																						cv$temp$9$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample18Value45) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value45) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value45) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value45) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value45) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value45);
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
										for(int i = 0; i < size; i += 1) {
											if(true) {
												for(int index$sample26$28 = 0; index$sample26$28 < weightings.length; index$sample26$28 += 1) {
													int distributionTempVariable$var23$30 = index$sample26$28;
													double cv$probabilitySample26Value29 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$28]);
													int traceTempVariable$var31$31_1 = distributionTempVariable$var23$30;
													if(((i + 1) == j)) {
														if(fixedFlag$sample18) {
															if((0 == j)) {
																if((0 == j)) {
																	{
																		{
																			double cv$temp$10$var36;
																			{
																				double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$31_1) + v2[j]) / v2[j]);
																				cv$temp$10$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample26Value29) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value29) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value29) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value29) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value29) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value29);
																		}
																	}
																}
															}
														} else {
															if(true) {
																for(int index$sample18$38 = 0; index$sample18$38 < weightings.length; index$sample18$38 += 1) {
																	int distributionTempVariable$var15$40 = index$sample18$38;
																	double cv$probabilitySample18Value39 = (cv$probabilitySample26Value29 * distribution$sample18[index$sample18$38]);
																	int traceTempVariable$var33$41_1 = distributionTempVariable$var15$40;
																	if((0 == j)) {
																		int traceTempVariable$var35$49_1 = distributionTempVariable$var15$40;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$11$var36;
																					{
																						double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$31_1) + traceTempVariable$var33$41_1) / traceTempVariable$var35$49_1);
																						cv$temp$11$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample18Value39) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value39) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value39) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value39) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value39) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value39);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample18$50 = 0; index$sample18$50 < weightings.length; index$sample18$50 += 1) {
																				int distributionTempVariable$var15$52 = index$sample18$50;
																				double cv$probabilitySample18Value51 = (cv$probabilitySample18Value39 * distribution$sample18[index$sample18$50]);
																				int traceTempVariable$var35$53_1 = distributionTempVariable$var15$52;
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$12$var36;
																							{
																								double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$31_1) + traceTempVariable$var33$41_1) / traceTempVariable$var35$53_1);
																								cv$temp$12$var36 = var36;
																							}
																							if(((Math.log(cv$probabilitySample18Value51) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var36)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value51) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value51) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var36));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value51) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value51) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var36)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value51);
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
									if(fixedFlag$sample18) {
										if((0 == j)) {
											if(fixedFlag$sample26) {
												for(int i = 0; i < size; i += 1) {
													if(((i + 1) == j)) {
														if((0 == j)) {
															{
																{
																	double cv$temp$13$var36;
																	{
																		double var36 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																		cv$temp$13$var36 = var36;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var36)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var36));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var36)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
											} else {
												for(int i = 0; i < size; i += 1) {
													if(true) {
														for(int index$sample26$61 = 0; index$sample26$61 < weightings.length; index$sample26$61 += 1) {
															int distributionTempVariable$var23$63 = index$sample26$61;
															double cv$probabilitySample26Value62 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$61]);
															int traceTempVariable$var33$64_1 = distributionTempVariable$var23$63;
															if(((i + 1) == j)) {
																if((0 == j)) {
																	{
																		{
																			double cv$temp$14$var36;
																			{
																				double var36 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var33$64_1) / v2[j]);
																				cv$temp$14$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample26Value62) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value62) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value62) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value62) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value62) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value62);
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
										if(true) {
											for(int index$sample18$55 = 0; index$sample18$55 < weightings.length; index$sample18$55 += 1) {
												int distributionTempVariable$var15$57 = index$sample18$55;
												double cv$probabilitySample18Value56 = (1.0 * distribution$sample18[index$sample18$55]);
												int traceTempVariable$var31$58_1 = distributionTempVariable$var15$57;
												if((0 == j)) {
													if(fixedFlag$sample26) {
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == j)) {
																int traceTempVariable$var35$73_1 = distributionTempVariable$var15$57;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$15$var36;
																			{
																				double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$58_1) + v2[j]) / traceTempVariable$var35$73_1);
																				cv$temp$15$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample18Value56) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value56) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value56) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value56) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value56) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value56);
																		}
																	}
																}
																if(!true) {
																	for(int index$sample18$74 = 0; index$sample18$74 < weightings.length; index$sample18$74 += 1) {
																		int distributionTempVariable$var15$76 = index$sample18$74;
																		double cv$probabilitySample18Value75 = (cv$probabilitySample18Value56 * distribution$sample18[index$sample18$74]);
																		int traceTempVariable$var35$77_1 = distributionTempVariable$var15$76;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$16$var36;
																					{
																						double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$58_1) + v2[j]) / traceTempVariable$var35$77_1);
																						cv$temp$16$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample18Value75) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value75) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value75) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value75) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value75) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value75);
																				}
																			}
																		}
																	}
																}
															}
														}
													} else {
														for(int i = 0; i < size; i += 1) {
															if(true) {
																for(int index$sample26$67 = 0; index$sample26$67 < weightings.length; index$sample26$67 += 1) {
																	int distributionTempVariable$var23$69 = index$sample26$67;
																	double cv$probabilitySample26Value68 = (cv$probabilitySample18Value56 * distribution$sample26[((i - 0) / 1)][index$sample26$67]);
																	int traceTempVariable$var33$70_1 = distributionTempVariable$var23$69;
																	if(((i + 1) == j)) {
																		int traceTempVariable$var35$78_1 = distributionTempVariable$var15$57;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$17$var36;
																					{
																						double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$58_1) + traceTempVariable$var33$70_1) / traceTempVariable$var35$78_1);
																						cv$temp$17$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample26Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value68);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample18$79 = 0; index$sample18$79 < weightings.length; index$sample18$79 += 1) {
																				int distributionTempVariable$var15$81 = index$sample18$79;
																				double cv$probabilitySample18Value80 = (cv$probabilitySample26Value68 * distribution$sample18[index$sample18$79]);
																				int traceTempVariable$var35$82_1 = distributionTempVariable$var15$81;
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$18$var36;
																							{
																								double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$58_1) + traceTempVariable$var33$70_1) / traceTempVariable$var35$82_1);
																								cv$temp$18$var36 = var36;
																							}
																							if(((Math.log(cv$probabilitySample18Value80) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var36)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value80) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value80) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var36));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value80) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value80) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var36)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value80);
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
									if(fixedFlag$sample26) {
										for(int i = 0; i < size; i += 1) {
											if(((i + 1) == j)) {
												for(int index$i$89_1 = 0; index$i$89_1 < size; index$i$89_1 += 1) {
													if(((index$i$89_1 + 1) == j)) {
														if(fixedFlag$sample18) {
															if((0 == j)) {
																{
																	{
																		double cv$temp$19$var36;
																		{
																			double var36 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																			cv$temp$19$var36 = var36;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														} else {
															if(true) {
																for(int index$sample18$97 = 0; index$sample18$97 < weightings.length; index$sample18$97 += 1) {
																	int distributionTempVariable$var15$99 = index$sample18$97;
																	double cv$probabilitySample18Value98 = (1.0 * distribution$sample18[index$sample18$97]);
																	int traceTempVariable$var35$100_1 = distributionTempVariable$var15$99;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$20$var36;
																				{
																					double var36 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / traceTempVariable$var35$100_1);
																					cv$temp$20$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample18Value98) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value98) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value98) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value98) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value98) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value98);
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
										for(int i = 0; i < size; i += 1) {
											if(true) {
												for(int index$sample26$85 = 0; index$sample26$85 < weightings.length; index$sample26$85 += 1) {
													int distributionTempVariable$var23$87 = index$sample26$85;
													double cv$probabilitySample26Value86 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$85]);
													int traceTempVariable$var31$88_1 = distributionTempVariable$var23$87;
													if(((i + 1) == j)) {
														int traceTempVariable$var33$90_1 = distributionTempVariable$var23$87;
														if(((i + 1) == j)) {
															if(fixedFlag$sample18) {
																if((0 == j)) {
																	{
																		{
																			double cv$temp$21$var36;
																			{
																				double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$88_1) + traceTempVariable$var33$90_1) / v2[j]);
																				cv$temp$21$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample26Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value86);
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample18$102 = 0; index$sample18$102 < weightings.length; index$sample18$102 += 1) {
																		int distributionTempVariable$var15$104 = index$sample18$102;
																		double cv$probabilitySample18Value103 = (cv$probabilitySample26Value86 * distribution$sample18[index$sample18$102]);
																		int traceTempVariable$var35$105_1 = distributionTempVariable$var15$104;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$22$var36;
																					{
																						double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$88_1) + traceTempVariable$var33$90_1) / traceTempVariable$var35$105_1);
																						cv$temp$22$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample18Value103) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value103) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value103) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value103) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value103) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value103);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int index$i$91 = 0; index$i$91 < size; index$i$91 += 1) {
															if(!(index$i$91 == i)) {
																for(int index$sample26$92 = 0; index$sample26$92 < weightings.length; index$sample26$92 += 1) {
																	int distributionTempVariable$var23$94 = index$sample26$92;
																	double cv$probabilitySample26Value93 = (cv$probabilitySample26Value86 * distribution$sample26[((index$i$91 - 0) / 1)][index$sample26$92]);
																	int traceTempVariable$var33$95_1 = distributionTempVariable$var23$94;
																	if(((index$i$91 + 1) == j)) {
																		if(fixedFlag$sample18) {
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$23$var36;
																						{
																							double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$88_1) + traceTempVariable$var33$95_1) / v2[j]);
																							cv$temp$23$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value93) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value93) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value93) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value93) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value93) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value93);
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample18$107 = 0; index$sample18$107 < weightings.length; index$sample18$107 += 1) {
																					int distributionTempVariable$var15$109 = index$sample18$107;
																					double cv$probabilitySample18Value108 = (cv$probabilitySample26Value93 * distribution$sample18[index$sample18$107]);
																					int traceTempVariable$var35$110_1 = distributionTempVariable$var15$109;
																					if((0 == j)) {
																						{
																							{
																								double cv$temp$24$var36;
																								{
																									double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$88_1) + traceTempVariable$var33$95_1) / traceTempVariable$var35$110_1);
																									cv$temp$24$var36 = var36;
																								}
																								if(((Math.log(cv$probabilitySample18Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var36)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var36));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var36)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value108);
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
									if(fixedFlag$sample18) {
										if((0 == j)) {
											if((0 == j)) {
												if(fixedFlag$sample26) {
													for(int i = 0; i < size; i += 1) {
														if(((i + 1) == j)) {
															{
																{
																	double cv$temp$25$var36;
																	{
																		double var36 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																		cv$temp$25$var36 = var36;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var36)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var36));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var36)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												} else {
													for(int i = 0; i < size; i += 1) {
														if(true) {
															for(int index$sample26$124 = 0; index$sample26$124 < weightings.length; index$sample26$124 += 1) {
																int distributionTempVariable$var23$126 = index$sample26$124;
																double cv$probabilitySample26Value125 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$124]);
																int traceTempVariable$var35$127_1 = distributionTempVariable$var23$126;
																if(((i + 1) == j)) {
																	{
																		{
																			double cv$temp$26$var36;
																			{
																				double var36 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / traceTempVariable$var35$127_1);
																				cv$temp$26$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample26Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value125);
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
										if(true) {
											for(int index$sample18$112 = 0; index$sample18$112 < weightings.length; index$sample18$112 += 1) {
												int distributionTempVariable$var15$114 = index$sample18$112;
												double cv$probabilitySample18Value113 = (1.0 * distribution$sample18[index$sample18$112]);
												int traceTempVariable$var31$115_1 = distributionTempVariable$var15$114;
												if((0 == j)) {
													int traceTempVariable$var33$117_1 = distributionTempVariable$var15$114;
													if((0 == j)) {
														if(fixedFlag$sample26) {
															for(int i = 0; i < size; i += 1) {
																if(((i + 1) == j)) {
																	{
																		{
																			double cv$temp$27$var36;
																			{
																				double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$115_1) + traceTempVariable$var33$117_1) / v2[j]);
																				cv$temp$27$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample18Value113) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value113) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value113) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value113) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value113) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value113);
																		}
																	}
																}
															}
														} else {
															for(int i = 0; i < size; i += 1) {
																if(true) {
																	for(int index$sample26$130 = 0; index$sample26$130 < weightings.length; index$sample26$130 += 1) {
																		int distributionTempVariable$var23$132 = index$sample26$130;
																		double cv$probabilitySample26Value131 = (cv$probabilitySample18Value113 * distribution$sample26[((i - 0) / 1)][index$sample26$130]);
																		int traceTempVariable$var35$133_1 = distributionTempVariable$var23$132;
																		if(((i + 1) == j)) {
																			{
																				{
																					double cv$temp$28$var36;
																					{
																						double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$115_1) + traceTempVariable$var33$117_1) / traceTempVariable$var35$133_1);
																						cv$temp$28$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value131);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													if(!true) {
														for(int index$sample18$118 = 0; index$sample18$118 < weightings.length; index$sample18$118 += 1) {
															int distributionTempVariable$var15$120 = index$sample18$118;
															double cv$probabilitySample18Value119 = (cv$probabilitySample18Value113 * distribution$sample18[index$sample18$118]);
															int traceTempVariable$var33$121_1 = distributionTempVariable$var15$120;
															if((0 == j)) {
																if(fixedFlag$sample26) {
																	for(int i = 0; i < size; i += 1) {
																		if(((i + 1) == j)) {
																			{
																				{
																					double cv$temp$29$var36;
																					{
																						double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$115_1) + traceTempVariable$var33$121_1) / v2[j]);
																						cv$temp$29$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample18Value119) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value119) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value119) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value119) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value119) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value119);
																				}
																			}
																		}
																	}
																} else {
																	for(int i = 0; i < size; i += 1) {
																		if(true) {
																			for(int index$sample26$136 = 0; index$sample26$136 < weightings.length; index$sample26$136 += 1) {
																				int distributionTempVariable$var23$138 = index$sample26$136;
																				double cv$probabilitySample26Value137 = (cv$probabilitySample18Value119 * distribution$sample26[((i - 0) / 1)][index$sample26$136]);
																				int traceTempVariable$var35$139_1 = distributionTempVariable$var23$138;
																				if(((i + 1) == j)) {
																					{
																						{
																							double cv$temp$30$var36;
																							{
																								double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$115_1) + traceTempVariable$var33$121_1) / traceTempVariable$var35$139_1);
																								cv$temp$30$var36 = var36;
																							}
																							if(((Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var36)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var36));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var36)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value137);
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
									if(fixedFlag$sample26) {
										for(int i = 0; i < size; i += 1) {
											if(((i + 1) == j)) {
												if(fixedFlag$sample18) {
													if((0 == j)) {
														for(int index$i$156_1 = 0; index$i$156_1 < size; index$i$156_1 += 1) {
															if(((index$i$156_1 + 1) == j)) {
																{
																	{
																		double cv$temp$31$var36;
																		{
																			double var36 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																			cv$temp$31$var36 = var36;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													}
												} else {
													if(true) {
														for(int index$sample18$147 = 0; index$sample18$147 < weightings.length; index$sample18$147 += 1) {
															int distributionTempVariable$var15$149 = index$sample18$147;
															double cv$probabilitySample18Value148 = (1.0 * distribution$sample18[index$sample18$147]);
															int traceTempVariable$var33$150_1 = distributionTempVariable$var15$149;
															if((0 == j)) {
																for(int index$i$157_1 = 0; index$i$157_1 < size; index$i$157_1 += 1) {
																	if(((index$i$157_1 + 1) == j)) {
																		{
																			{
																				double cv$temp$32$var36;
																				{
																					double var36 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var33$150_1) / v2[j]);
																					cv$temp$32$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample18Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value148);
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
										for(int i = 0; i < size; i += 1) {
											if(true) {
												for(int index$sample26$142 = 0; index$sample26$142 < weightings.length; index$sample26$142 += 1) {
													int distributionTempVariable$var23$144 = index$sample26$142;
													double cv$probabilitySample26Value143 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$142]);
													int traceTempVariable$var31$145_1 = distributionTempVariable$var23$144;
													if(((i + 1) == j)) {
														if(fixedFlag$sample18) {
															if((0 == j)) {
																int traceTempVariable$var35$158_1 = distributionTempVariable$var23$144;
																if(((i + 1) == j)) {
																	{
																		{
																			double cv$temp$33$var36;
																			{
																				double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$145_1) + v2[j]) / traceTempVariable$var35$158_1);
																				cv$temp$33$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample26Value143) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value143) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value143) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value143) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value143) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value143);
																		}
																	}
																}
																for(int index$i$159 = 0; index$i$159 < size; index$i$159 += 1) {
																	if(!(index$i$159 == i)) {
																		for(int index$sample26$160 = 0; index$sample26$160 < weightings.length; index$sample26$160 += 1) {
																			int distributionTempVariable$var23$162 = index$sample26$160;
																			double cv$probabilitySample26Value161 = (cv$probabilitySample26Value143 * distribution$sample26[((index$i$159 - 0) / 1)][index$sample26$160]);
																			int traceTempVariable$var35$163_1 = distributionTempVariable$var23$162;
																			if(((index$i$159 + 1) == j)) {
																				{
																					{
																						double cv$temp$34$var36;
																						{
																							double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$145_1) + v2[j]) / traceTempVariable$var35$163_1);
																							cv$temp$34$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value161);
																					}
																				}
																			}
																		}
																	}
																}
															}
														} else {
															if(true) {
																for(int index$sample18$152 = 0; index$sample18$152 < weightings.length; index$sample18$152 += 1) {
																	int distributionTempVariable$var15$154 = index$sample18$152;
																	double cv$probabilitySample18Value153 = (cv$probabilitySample26Value143 * distribution$sample18[index$sample18$152]);
																	int traceTempVariable$var33$155_1 = distributionTempVariable$var15$154;
																	if((0 == j)) {
																		int traceTempVariable$var35$164_1 = distributionTempVariable$var23$144;
																		if(((i + 1) == j)) {
																			{
																				{
																					double cv$temp$35$var36;
																					{
																						double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$145_1) + traceTempVariable$var33$155_1) / traceTempVariable$var35$164_1);
																						cv$temp$35$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample18Value153) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value153) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value153) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value153) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value153) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value153);
																				}
																			}
																		}
																		for(int index$i$165 = 0; index$i$165 < size; index$i$165 += 1) {
																			if(!(index$i$165 == i)) {
																				for(int index$sample26$166 = 0; index$sample26$166 < weightings.length; index$sample26$166 += 1) {
																					int distributionTempVariable$var23$168 = index$sample26$166;
																					double cv$probabilitySample26Value167 = (cv$probabilitySample18Value153 * distribution$sample26[((index$i$165 - 0) / 1)][index$sample26$166]);
																					int traceTempVariable$var35$169_1 = distributionTempVariable$var23$168;
																					if(((index$i$165 + 1) == j)) {
																						{
																							{
																								double cv$temp$36$var36;
																								{
																									double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$145_1) + traceTempVariable$var33$155_1) / traceTempVariable$var35$169_1);
																									cv$temp$36$var36 = var36;
																								}
																								if(((Math.log(cv$probabilitySample26Value167) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var36)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value167) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value167) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var36));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value167) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value167) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var36)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value167);
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
									if(fixedFlag$sample18) {
										if((0 == j)) {
											if(fixedFlag$sample26) {
												for(int i = 0; i < size; i += 1) {
													if(((i + 1) == j)) {
														for(int index$i$187_1 = 0; index$i$187_1 < size; index$i$187_1 += 1) {
															if(((index$i$187_1 + 1) == j)) {
																{
																	{
																		double cv$temp$37$var36;
																		{
																			double var36 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																			cv$temp$37$var36 = var36;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													}
												}
											} else {
												for(int i = 0; i < size; i += 1) {
													if(true) {
														for(int index$sample26$177 = 0; index$sample26$177 < weightings.length; index$sample26$177 += 1) {
															int distributionTempVariable$var23$179 = index$sample26$177;
															double cv$probabilitySample26Value178 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$177]);
															int traceTempVariable$var33$180_1 = distributionTempVariable$var23$179;
															if(((i + 1) == j)) {
																int traceTempVariable$var35$188_1 = distributionTempVariable$var23$179;
																if(((i + 1) == j)) {
																	{
																		{
																			double cv$temp$38$var36;
																			{
																				double var36 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var33$180_1) / traceTempVariable$var35$188_1);
																				cv$temp$38$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample26Value178) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value178) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value178) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value178) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value178) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value178);
																		}
																	}
																}
																for(int index$i$189 = 0; index$i$189 < size; index$i$189 += 1) {
																	if(!(index$i$189 == i)) {
																		for(int index$sample26$190 = 0; index$sample26$190 < weightings.length; index$sample26$190 += 1) {
																			int distributionTempVariable$var23$192 = index$sample26$190;
																			double cv$probabilitySample26Value191 = (cv$probabilitySample26Value178 * distribution$sample26[((index$i$189 - 0) / 1)][index$sample26$190]);
																			int traceTempVariable$var35$193_1 = distributionTempVariable$var23$192;
																			if(((index$i$189 + 1) == j)) {
																				{
																					{
																						double cv$temp$39$var36;
																						{
																							double var36 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var33$180_1) / traceTempVariable$var35$193_1);
																							cv$temp$39$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value191) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value191) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value191) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value191) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value191) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value191);
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
									} else {
										if(true) {
											for(int index$sample18$171 = 0; index$sample18$171 < weightings.length; index$sample18$171 += 1) {
												int distributionTempVariable$var15$173 = index$sample18$171;
												double cv$probabilitySample18Value172 = (1.0 * distribution$sample18[index$sample18$171]);
												int traceTempVariable$var31$174_1 = distributionTempVariable$var15$173;
												if((0 == j)) {
													if(fixedFlag$sample26) {
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == j)) {
																for(int index$i$194_1 = 0; index$i$194_1 < size; index$i$194_1 += 1) {
																	if(((index$i$194_1 + 1) == j)) {
																		{
																			{
																				double cv$temp$40$var36;
																				{
																					double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$174_1) + v2[j]) / v2[j]);
																					cv$temp$40$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample18Value172) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value172) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value172) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value172) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value172) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value172);
																			}
																		}
																	}
																}
															}
														}
													} else {
														for(int i = 0; i < size; i += 1) {
															if(true) {
																for(int index$sample26$183 = 0; index$sample26$183 < weightings.length; index$sample26$183 += 1) {
																	int distributionTempVariable$var23$185 = index$sample26$183;
																	double cv$probabilitySample26Value184 = (cv$probabilitySample18Value172 * distribution$sample26[((i - 0) / 1)][index$sample26$183]);
																	int traceTempVariable$var33$186_1 = distributionTempVariable$var23$185;
																	if(((i + 1) == j)) {
																		int traceTempVariable$var35$195_1 = distributionTempVariable$var23$185;
																		if(((i + 1) == j)) {
																			{
																				{
																					double cv$temp$41$var36;
																					{
																						double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$174_1) + traceTempVariable$var33$186_1) / traceTempVariable$var35$195_1);
																						cv$temp$41$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample26Value184) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value184) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value184) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value184) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value184) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value184);
																				}
																			}
																		}
																		for(int index$i$196 = 0; index$i$196 < size; index$i$196 += 1) {
																			if(!(index$i$196 == i)) {
																				for(int index$sample26$197 = 0; index$sample26$197 < weightings.length; index$sample26$197 += 1) {
																					int distributionTempVariable$var23$199 = index$sample26$197;
																					double cv$probabilitySample26Value198 = (cv$probabilitySample26Value184 * distribution$sample26[((index$i$196 - 0) / 1)][index$sample26$197]);
																					int traceTempVariable$var35$200_1 = distributionTempVariable$var23$199;
																					if(((index$i$196 + 1) == j)) {
																						{
																							{
																								double cv$temp$42$var36;
																								{
																									double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$174_1) + traceTempVariable$var33$186_1) / traceTempVariable$var35$200_1);
																									cv$temp$42$var36 = var36;
																								}
																								if(((Math.log(cv$probabilitySample26Value198) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var36)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value198) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value198) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var36));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value198) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value198) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var36)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value198);
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
									if(fixedFlag$sample26) {
										for(int i = 0; i < size; i += 1) {
											if(((i + 1) == j)) {
												for(int index$i$207_1 = 0; index$i$207_1 < size; index$i$207_1 += 1) {
													if(((index$i$207_1 + 1) == j)) {
														for(int index$i$214_1 = 0; index$i$214_1 < size; index$i$214_1 += 1) {
															if(((index$i$214_1 + 1) == j)) {
																{
																	{
																		double cv$temp$43$var36;
																		{
																			double var36 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																			cv$temp$43$var36 = var36;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													}
												}
											}
										}
									} else {
										for(int i = 0; i < size; i += 1) {
											if(true) {
												for(int index$sample26$203 = 0; index$sample26$203 < weightings.length; index$sample26$203 += 1) {
													int distributionTempVariable$var23$205 = index$sample26$203;
													double cv$probabilitySample26Value204 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$203]);
													int traceTempVariable$var31$206_1 = distributionTempVariable$var23$205;
													if(((i + 1) == j)) {
														int traceTempVariable$var33$208_1 = distributionTempVariable$var23$205;
														if(((i + 1) == j)) {
															int traceTempVariable$var35$215_1 = distributionTempVariable$var23$205;
															if(((i + 1) == j)) {
																{
																	{
																		double cv$temp$44$var36;
																		{
																			double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$206_1) + traceTempVariable$var33$208_1) / traceTempVariable$var35$215_1);
																			cv$temp$44$var36 = var36;
																		}
																		if(((Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value204);
																	}
																}
															}
															for(int index$i$216 = 0; index$i$216 < size; index$i$216 += 1) {
																if(!(index$i$216 == i)) {
																	for(int index$sample26$217 = 0; index$sample26$217 < weightings.length; index$sample26$217 += 1) {
																		int distributionTempVariable$var23$219 = index$sample26$217;
																		double cv$probabilitySample26Value218 = (cv$probabilitySample26Value204 * distribution$sample26[((index$i$216 - 0) / 1)][index$sample26$217]);
																		int traceTempVariable$var35$220_1 = distributionTempVariable$var23$219;
																		if(((index$i$216 + 1) == j)) {
																			{
																				{
																					double cv$temp$45$var36;
																					{
																						double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$206_1) + traceTempVariable$var33$208_1) / traceTempVariable$var35$220_1);
																						cv$temp$45$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample26Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value218);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int index$i$209 = 0; index$i$209 < size; index$i$209 += 1) {
															if(!(index$i$209 == i)) {
																for(int index$sample26$210 = 0; index$sample26$210 < weightings.length; index$sample26$210 += 1) {
																	int distributionTempVariable$var23$212 = index$sample26$210;
																	double cv$probabilitySample26Value211 = (cv$probabilitySample26Value204 * distribution$sample26[((index$i$209 - 0) / 1)][index$sample26$210]);
																	int traceTempVariable$var33$213_1 = distributionTempVariable$var23$212;
																	if(((index$i$209 + 1) == j)) {
																		int traceTempVariable$var35$221_1 = distributionTempVariable$var23$205;
																		if(((i + 1) == j)) {
																			{
																				{
																					double cv$temp$46$var36;
																					{
																						double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$206_1) + traceTempVariable$var33$213_1) / traceTempVariable$var35$221_1);
																						cv$temp$46$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value211);
																				}
																			}
																		}
																		int traceTempVariable$var35$222_1 = distributionTempVariable$var23$212;
																		if(((index$i$209 + 1) == j)) {
																			{
																				{
																					double cv$temp$47$var36;
																					{
																						double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$206_1) + traceTempVariable$var33$213_1) / traceTempVariable$var35$222_1);
																						cv$temp$47$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value211);
																				}
																			}
																		}
																		for(int index$i$223 = 0; index$i$223 < size; index$i$223 += 1) {
																			if((!(index$i$223 == i) && !(index$i$223 == index$i$209))) {
																				for(int index$sample26$224 = 0; index$sample26$224 < weightings.length; index$sample26$224 += 1) {
																					int distributionTempVariable$var23$226 = index$sample26$224;
																					double cv$probabilitySample26Value225 = (cv$probabilitySample26Value211 * distribution$sample26[((index$i$223 - 0) / 1)][index$sample26$224]);
																					int traceTempVariable$var35$227_1 = distributionTempVariable$var23$226;
																					if(((index$i$223 + 1) == j)) {
																						{
																							{
																								double cv$temp$48$var36;
																								{
																									double var36 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var31$206_1) + traceTempVariable$var33$213_1) / traceTempVariable$var35$227_1);
																									cv$temp$48$var36 = var36;
																								}
																								if(((Math.log(cv$probabilitySample26Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var36)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var36));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var36)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value225);
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
		double[] cv$localProbability = distribution$sample12;
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

	private final void sample18() {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, weightings.length);
		}
		double[] cv$stateProbabilityLocal = cv$var15$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$weightings;
				{
					cv$temp$0$weightings = weightings;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$weightings.length))?Math.log(cv$temp$0$weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						boolean[] guard$sample18bernoulli40 = guard$sample18bernoulli40$global;
						for(int j = 0; j < size; j += 1) {
							if((0 == j))
								guard$sample18bernoulli40[((j - 0) / 1)] = false;
						}
						for(int j = 0; j < size; j += 1) {
							if((0 == j))
								guard$sample18bernoulli40[((j - 0) / 1)] = false;
						}
						for(int j = 0; j < size; j += 1) {
							if((0 == j))
								guard$sample18bernoulli40[((j - 0) / 1)] = false;
						}
						int traceTempVariable$var31$4_1 = cv$currentValue;
						for(int j = 0; j < size; j += 1) {
							if((0 == j)) {
								if(!guard$sample18bernoulli40[((j - 0) / 1)]) {
									guard$sample18bernoulli40[((j - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											if(fixedFlag$sample12) {
												int traceTempVariable$var33$15_1 = cv$currentValue;
												if((0 == j)) {
													int traceTempVariable$var35$25_1 = cv$currentValue;
													if((0 == j)) {
														{
															{
																double cv$temp$1$var36;
																{
																	double var36 = ((((1.0 * v1) + traceTempVariable$var31$4_1) + traceTempVariable$var33$15_1) / traceTempVariable$var35$25_1);
																	cv$temp$1$var36 = var36;
																}
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var36)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var36));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var36)));
																}
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
															}
														}
													}
													if(!true) {
														for(int index$sample18$26 = 0; index$sample18$26 < weightings.length; index$sample18$26 += 1) {
															int distributionTempVariable$var15$28 = index$sample18$26;
															double cv$probabilitySample18Value27 = (1.0 * distribution$sample18[index$sample18$26]);
															int traceTempVariable$var35$29_1 = distributionTempVariable$var15$28;
															if((0 == j)) {
																{
																	{
																		double cv$temp$2$var36;
																		{
																			double var36 = ((((1.0 * v1) + traceTempVariable$var31$4_1) + traceTempVariable$var33$15_1) / traceTempVariable$var35$29_1);
																			cv$temp$2$var36 = var36;
																		}
																		if(((Math.log(cv$probabilitySample18Value27) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value27) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value27) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value27) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value27) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value27);
																	}
																}
															}
														}
													}
												}
												if(!true) {
													for(int index$sample18$16 = 0; index$sample18$16 < weightings.length; index$sample18$16 += 1) {
														int distributionTempVariable$var15$18 = index$sample18$16;
														double cv$probabilitySample18Value17 = (1.0 * distribution$sample18[index$sample18$16]);
														int traceTempVariable$var33$19_1 = distributionTempVariable$var15$18;
														if((0 == j)) {
															int traceTempVariable$var35$30_1 = cv$currentValue;
															if((0 == j)) {
																{
																	{
																		double cv$temp$3$var36;
																		{
																			double var36 = ((((1.0 * v1) + traceTempVariable$var31$4_1) + traceTempVariable$var33$19_1) / traceTempVariable$var35$30_1);
																			cv$temp$3$var36 = var36;
																		}
																		if(((Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value17);
																	}
																}
															}
															int traceTempVariable$var35$31_1 = distributionTempVariable$var15$18;
															if((0 == j)) {
																{
																	{
																		double cv$temp$4$var36;
																		{
																			double var36 = ((((1.0 * v1) + traceTempVariable$var31$4_1) + traceTempVariable$var33$19_1) / traceTempVariable$var35$31_1);
																			cv$temp$4$var36 = var36;
																		}
																		if(((Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value17);
																	}
																}
															}
															if(!true) {
																for(int index$sample18$32 = 0; index$sample18$32 < weightings.length; index$sample18$32 += 1) {
																	int distributionTempVariable$var15$34 = index$sample18$32;
																	double cv$probabilitySample18Value33 = (cv$probabilitySample18Value17 * distribution$sample18[index$sample18$32]);
																	int traceTempVariable$var35$35_1 = distributionTempVariable$var15$34;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$5$var36;
																				{
																					double var36 = ((((1.0 * v1) + traceTempVariable$var31$4_1) + traceTempVariable$var33$19_1) / traceTempVariable$var35$35_1);
																					cv$temp$5$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample18Value33) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value33) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value33) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value33) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value33) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value33);
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
													for(int index$sample12$11 = 0; index$sample12$11 < weightings.length; index$sample12$11 += 1) {
														int distributionTempVariable$v1$13 = index$sample12$11;
														double cv$probabilitySample12Value12 = (1.0 * distribution$sample12[index$sample12$11]);
														int traceTempVariable$var33$20_1 = cv$currentValue;
														if((0 == j)) {
															int traceTempVariable$var35$36_1 = cv$currentValue;
															if((0 == j)) {
																{
																	{
																		double cv$temp$6$var36;
																		{
																			double var36 = ((((1.0 * distributionTempVariable$v1$13) + traceTempVariable$var31$4_1) + traceTempVariable$var33$20_1) / traceTempVariable$var35$36_1);
																			cv$temp$6$var36 = var36;
																		}
																		if(((Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value12);
																	}
																}
															}
															if(!true) {
																for(int index$sample18$37 = 0; index$sample18$37 < weightings.length; index$sample18$37 += 1) {
																	int distributionTempVariable$var15$39 = index$sample18$37;
																	double cv$probabilitySample18Value38 = (cv$probabilitySample12Value12 * distribution$sample18[index$sample18$37]);
																	int traceTempVariable$var35$40_1 = distributionTempVariable$var15$39;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$7$var36;
																				{
																					double var36 = ((((1.0 * distributionTempVariable$v1$13) + traceTempVariable$var31$4_1) + traceTempVariable$var33$20_1) / traceTempVariable$var35$40_1);
																					cv$temp$7$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample18Value38) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value38) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value38) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value38) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value38) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value38);
																			}
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample18$21 = 0; index$sample18$21 < weightings.length; index$sample18$21 += 1) {
																int distributionTempVariable$var15$23 = index$sample18$21;
																double cv$probabilitySample18Value22 = (cv$probabilitySample12Value12 * distribution$sample18[index$sample18$21]);
																int traceTempVariable$var33$24_1 = distributionTempVariable$var15$23;
																if((0 == j)) {
																	int traceTempVariable$var35$41_1 = cv$currentValue;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$8$var36;
																				{
																					double var36 = ((((1.0 * distributionTempVariable$v1$13) + traceTempVariable$var31$4_1) + traceTempVariable$var33$24_1) / traceTempVariable$var35$41_1);
																					cv$temp$8$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample18Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value22);
																			}
																		}
																	}
																	int traceTempVariable$var35$42_1 = distributionTempVariable$var15$23;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$9$var36;
																				{
																					double var36 = ((((1.0 * distributionTempVariable$v1$13) + traceTempVariable$var31$4_1) + traceTempVariable$var33$24_1) / traceTempVariable$var35$42_1);
																					cv$temp$9$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample18Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value22);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample18$43 = 0; index$sample18$43 < weightings.length; index$sample18$43 += 1) {
																			int distributionTempVariable$var15$45 = index$sample18$43;
																			double cv$probabilitySample18Value44 = (cv$probabilitySample18Value22 * distribution$sample18[index$sample18$43]);
																			int traceTempVariable$var35$46_1 = distributionTempVariable$var15$45;
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$10$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$13) + traceTempVariable$var31$4_1) + traceTempVariable$var33$24_1) / traceTempVariable$var35$46_1);
																							cv$temp$10$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample18Value44) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value44) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value44) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value44) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value44) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value44);
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
											if(fixedFlag$sample12) {
												if(fixedFlag$sample26) {
													for(int i = 0; i < size; i += 1) {
														if(((i + 1) == j)) {
															int traceTempVariable$var35$64_1 = cv$currentValue;
															if((0 == j)) {
																{
																	{
																		double cv$temp$11$var36;
																		{
																			double var36 = ((((1.0 * v1) + traceTempVariable$var31$4_1) + v2[j]) / traceTempVariable$var35$64_1);
																			cv$temp$11$var36 = var36;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
															if(!true) {
																for(int index$sample18$65 = 0; index$sample18$65 < weightings.length; index$sample18$65 += 1) {
																	int distributionTempVariable$var15$67 = index$sample18$65;
																	double cv$probabilitySample18Value66 = (1.0 * distribution$sample18[index$sample18$65]);
																	int traceTempVariable$var35$68_1 = distributionTempVariable$var15$67;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$12$var36;
																				{
																					double var36 = ((((1.0 * v1) + traceTempVariable$var31$4_1) + v2[j]) / traceTempVariable$var35$68_1);
																					cv$temp$12$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample18Value66) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value66) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value66) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value66) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value66) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value66);
																			}
																		}
																	}
																}
															}
														}
													}
												} else {
													for(int i = 0; i < size; i += 1) {
														if(true) {
															for(int index$sample26$54 = 0; index$sample26$54 < weightings.length; index$sample26$54 += 1) {
																int distributionTempVariable$var23$56 = index$sample26$54;
																double cv$probabilitySample26Value55 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$54]);
																int traceTempVariable$var33$57_1 = distributionTempVariable$var23$56;
																if(((i + 1) == j)) {
																	int traceTempVariable$var35$69_1 = cv$currentValue;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$13$var36;
																				{
																					double var36 = ((((1.0 * v1) + traceTempVariable$var31$4_1) + traceTempVariable$var33$57_1) / traceTempVariable$var35$69_1);
																					cv$temp$13$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample26Value55) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value55) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value55) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value55) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value55) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value55);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample18$70 = 0; index$sample18$70 < weightings.length; index$sample18$70 += 1) {
																			int distributionTempVariable$var15$72 = index$sample18$70;
																			double cv$probabilitySample18Value71 = (cv$probabilitySample26Value55 * distribution$sample18[index$sample18$70]);
																			int traceTempVariable$var35$73_1 = distributionTempVariable$var15$72;
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$14$var36;
																						{
																							double var36 = ((((1.0 * v1) + traceTempVariable$var31$4_1) + traceTempVariable$var33$57_1) / traceTempVariable$var35$73_1);
																							cv$temp$14$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample18Value71) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value71) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value71) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value71) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value71) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value71);
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
												if(true) {
													for(int index$sample12$48 = 0; index$sample12$48 < weightings.length; index$sample12$48 += 1) {
														int distributionTempVariable$v1$50 = index$sample12$48;
														double cv$probabilitySample12Value49 = (1.0 * distribution$sample12[index$sample12$48]);
														if(fixedFlag$sample26) {
															for(int i = 0; i < size; i += 1) {
																if(((i + 1) == j)) {
																	int traceTempVariable$var35$74_1 = cv$currentValue;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$15$var36;
																				{
																					double var36 = ((((1.0 * distributionTempVariable$v1$50) + traceTempVariable$var31$4_1) + v2[j]) / traceTempVariable$var35$74_1);
																					cv$temp$15$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample12Value49) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value49) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value49) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value49) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value49) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value49);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample18$75 = 0; index$sample18$75 < weightings.length; index$sample18$75 += 1) {
																			int distributionTempVariable$var15$77 = index$sample18$75;
																			double cv$probabilitySample18Value76 = (cv$probabilitySample12Value49 * distribution$sample18[index$sample18$75]);
																			int traceTempVariable$var35$78_1 = distributionTempVariable$var15$77;
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$16$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$50) + traceTempVariable$var31$4_1) + v2[j]) / traceTempVariable$var35$78_1);
																							cv$temp$16$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample18Value76) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value76) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value76) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value76) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value76) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value76);
																					}
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i = 0; i < size; i += 1) {
																if(true) {
																	for(int index$sample26$60 = 0; index$sample26$60 < weightings.length; index$sample26$60 += 1) {
																		int distributionTempVariable$var23$62 = index$sample26$60;
																		double cv$probabilitySample26Value61 = (cv$probabilitySample12Value49 * distribution$sample26[((i - 0) / 1)][index$sample26$60]);
																		int traceTempVariable$var33$63_1 = distributionTempVariable$var23$62;
																		if(((i + 1) == j)) {
																			int traceTempVariable$var35$79_1 = cv$currentValue;
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$17$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$50) + traceTempVariable$var31$4_1) + traceTempVariable$var33$63_1) / traceTempVariable$var35$79_1);
																							cv$temp$17$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value61) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value61) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value61) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value61) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value61) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value61);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample18$80 = 0; index$sample18$80 < weightings.length; index$sample18$80 += 1) {
																					int distributionTempVariable$var15$82 = index$sample18$80;
																					double cv$probabilitySample18Value81 = (cv$probabilitySample26Value61 * distribution$sample18[index$sample18$80]);
																					int traceTempVariable$var35$83_1 = distributionTempVariable$var15$82;
																					if((0 == j)) {
																						{
																							{
																								double cv$temp$18$var36;
																								{
																									double var36 = ((((1.0 * distributionTempVariable$v1$50) + traceTempVariable$var31$4_1) + traceTempVariable$var33$63_1) / traceTempVariable$var35$83_1);
																									cv$temp$18$var36 = var36;
																								}
																								if(((Math.log(cv$probabilitySample18Value81) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var36)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value81) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value81) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var36));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value81) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value81) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var36)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value81);
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
											if(fixedFlag$sample12) {
												int traceTempVariable$var33$89_1 = cv$currentValue;
												if((0 == j)) {
													if(fixedFlag$sample26) {
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == j)) {
																{
																	{
																		double cv$temp$19$var36;
																		{
																			double var36 = ((((1.0 * v1) + traceTempVariable$var31$4_1) + traceTempVariable$var33$89_1) / v2[j]);
																			cv$temp$19$var36 = var36;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													} else {
														for(int i = 0; i < size; i += 1) {
															if(true) {
																for(int index$sample26$101 = 0; index$sample26$101 < weightings.length; index$sample26$101 += 1) {
																	int distributionTempVariable$var23$103 = index$sample26$101;
																	double cv$probabilitySample26Value102 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$101]);
																	int traceTempVariable$var35$104_1 = distributionTempVariable$var23$103;
																	if(((i + 1) == j)) {
																		{
																			{
																				double cv$temp$20$var36;
																				{
																					double var36 = ((((1.0 * v1) + traceTempVariable$var31$4_1) + traceTempVariable$var33$89_1) / traceTempVariable$var35$104_1);
																					cv$temp$20$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample26Value102) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value102) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value102) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value102) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value102) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value102);
																			}
																		}
																	}
																}
															}
														}
													}
												}
												if(!true) {
													for(int index$sample18$90 = 0; index$sample18$90 < weightings.length; index$sample18$90 += 1) {
														int distributionTempVariable$var15$92 = index$sample18$90;
														double cv$probabilitySample18Value91 = (1.0 * distribution$sample18[index$sample18$90]);
														int traceTempVariable$var33$93_1 = distributionTempVariable$var15$92;
														if((0 == j)) {
															if(fixedFlag$sample26) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == j)) {
																		{
																			{
																				double cv$temp$21$var36;
																				{
																					double var36 = ((((1.0 * v1) + traceTempVariable$var31$4_1) + traceTempVariable$var33$93_1) / v2[j]);
																					cv$temp$21$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample18Value91) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value91) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value91) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value91) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value91) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value91);
																			}
																		}
																	}
																}
															} else {
																for(int i = 0; i < size; i += 1) {
																	if(true) {
																		for(int index$sample26$107 = 0; index$sample26$107 < weightings.length; index$sample26$107 += 1) {
																			int distributionTempVariable$var23$109 = index$sample26$107;
																			double cv$probabilitySample26Value108 = (cv$probabilitySample18Value91 * distribution$sample26[((i - 0) / 1)][index$sample26$107]);
																			int traceTempVariable$var35$110_1 = distributionTempVariable$var23$109;
																			if(((i + 1) == j)) {
																				{
																					{
																						double cv$temp$22$var36;
																						{
																							double var36 = ((((1.0 * v1) + traceTempVariable$var31$4_1) + traceTempVariable$var33$93_1) / traceTempVariable$var35$110_1);
																							cv$temp$22$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value108);
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
												if(true) {
													for(int index$sample12$85 = 0; index$sample12$85 < weightings.length; index$sample12$85 += 1) {
														int distributionTempVariable$v1$87 = index$sample12$85;
														double cv$probabilitySample12Value86 = (1.0 * distribution$sample12[index$sample12$85]);
														int traceTempVariable$var33$94_1 = cv$currentValue;
														if((0 == j)) {
															if(fixedFlag$sample26) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == j)) {
																		{
																			{
																				double cv$temp$23$var36;
																				{
																					double var36 = ((((1.0 * distributionTempVariable$v1$87) + traceTempVariable$var31$4_1) + traceTempVariable$var33$94_1) / v2[j]);
																					cv$temp$23$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample12Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value86);
																			}
																		}
																	}
																}
															} else {
																for(int i = 0; i < size; i += 1) {
																	if(true) {
																		for(int index$sample26$113 = 0; index$sample26$113 < weightings.length; index$sample26$113 += 1) {
																			int distributionTempVariable$var23$115 = index$sample26$113;
																			double cv$probabilitySample26Value114 = (cv$probabilitySample12Value86 * distribution$sample26[((i - 0) / 1)][index$sample26$113]);
																			int traceTempVariable$var35$116_1 = distributionTempVariable$var23$115;
																			if(((i + 1) == j)) {
																				{
																					{
																						double cv$temp$24$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$87) + traceTempVariable$var31$4_1) + traceTempVariable$var33$94_1) / traceTempVariable$var35$116_1);
																							cv$temp$24$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value114) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value114) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value114) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value114) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value114) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value114);
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample18$95 = 0; index$sample18$95 < weightings.length; index$sample18$95 += 1) {
																int distributionTempVariable$var15$97 = index$sample18$95;
																double cv$probabilitySample18Value96 = (cv$probabilitySample12Value86 * distribution$sample18[index$sample18$95]);
																int traceTempVariable$var33$98_1 = distributionTempVariable$var15$97;
																if((0 == j)) {
																	if(fixedFlag$sample26) {
																		for(int i = 0; i < size; i += 1) {
																			if(((i + 1) == j)) {
																				{
																					{
																						double cv$temp$25$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$87) + traceTempVariable$var31$4_1) + traceTempVariable$var33$98_1) / v2[j]);
																							cv$temp$25$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample18Value96) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value96) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value96) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value96) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value96) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value96);
																					}
																				}
																			}
																		}
																	} else {
																		for(int i = 0; i < size; i += 1) {
																			if(true) {
																				for(int index$sample26$119 = 0; index$sample26$119 < weightings.length; index$sample26$119 += 1) {
																					int distributionTempVariable$var23$121 = index$sample26$119;
																					double cv$probabilitySample26Value120 = (cv$probabilitySample18Value96 * distribution$sample26[((i - 0) / 1)][index$sample26$119]);
																					int traceTempVariable$var35$122_1 = distributionTempVariable$var23$121;
																					if(((i + 1) == j)) {
																						{
																							{
																								double cv$temp$26$var36;
																								{
																									double var36 = ((((1.0 * distributionTempVariable$v1$87) + traceTempVariable$var31$4_1) + traceTempVariable$var33$98_1) / traceTempVariable$var35$122_1);
																									cv$temp$26$var36 = var36;
																								}
																								if(((Math.log(cv$probabilitySample26Value120) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var36)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value120) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value120) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var36));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value120) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value120) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var36)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value120);
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
											if(fixedFlag$sample12) {
												if(fixedFlag$sample26) {
													for(int i = 0; i < size; i += 1) {
														if(((i + 1) == j)) {
															for(int index$i$140_1 = 0; index$i$140_1 < size; index$i$140_1 += 1) {
																if(((index$i$140_1 + 1) == j)) {
																	{
																		{
																			double cv$temp$27$var36;
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$4_1) + v2[j]) / v2[j]);
																				cv$temp$27$var36 = var36;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													}
												} else {
													for(int i = 0; i < size; i += 1) {
														if(true) {
															for(int index$sample26$130 = 0; index$sample26$130 < weightings.length; index$sample26$130 += 1) {
																int distributionTempVariable$var23$132 = index$sample26$130;
																double cv$probabilitySample26Value131 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$130]);
																int traceTempVariable$var33$133_1 = distributionTempVariable$var23$132;
																if(((i + 1) == j)) {
																	int traceTempVariable$var35$141_1 = distributionTempVariable$var23$132;
																	if(((i + 1) == j)) {
																		{
																			{
																				double cv$temp$28$var36;
																				{
																					double var36 = ((((1.0 * v1) + traceTempVariable$var31$4_1) + traceTempVariable$var33$133_1) / traceTempVariable$var35$141_1);
																					cv$temp$28$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value131);
																			}
																		}
																	}
																	for(int index$i$142 = 0; index$i$142 < size; index$i$142 += 1) {
																		if(!(index$i$142 == i)) {
																			for(int index$sample26$143 = 0; index$sample26$143 < weightings.length; index$sample26$143 += 1) {
																				int distributionTempVariable$var23$145 = index$sample26$143;
																				double cv$probabilitySample26Value144 = (cv$probabilitySample26Value131 * distribution$sample26[((index$i$142 - 0) / 1)][index$sample26$143]);
																				int traceTempVariable$var35$146_1 = distributionTempVariable$var23$145;
																				if(((index$i$142 + 1) == j)) {
																					{
																						{
																							double cv$temp$29$var36;
																							{
																								double var36 = ((((1.0 * v1) + traceTempVariable$var31$4_1) + traceTempVariable$var33$133_1) / traceTempVariable$var35$146_1);
																								cv$temp$29$var36 = var36;
																							}
																							if(((Math.log(cv$probabilitySample26Value144) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var36)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value144) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value144) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var36));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value144) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value144) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var36)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value144);
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
												if(true) {
													for(int index$sample12$124 = 0; index$sample12$124 < weightings.length; index$sample12$124 += 1) {
														int distributionTempVariable$v1$126 = index$sample12$124;
														double cv$probabilitySample12Value125 = (1.0 * distribution$sample12[index$sample12$124]);
														if(fixedFlag$sample26) {
															for(int i = 0; i < size; i += 1) {
																if(((i + 1) == j)) {
																	for(int index$i$147_1 = 0; index$i$147_1 < size; index$i$147_1 += 1) {
																		if(((index$i$147_1 + 1) == j)) {
																			{
																				{
																					double cv$temp$30$var36;
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$126) + traceTempVariable$var31$4_1) + v2[j]) / v2[j]);
																						cv$temp$30$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample12Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value125);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i = 0; i < size; i += 1) {
																if(true) {
																	for(int index$sample26$136 = 0; index$sample26$136 < weightings.length; index$sample26$136 += 1) {
																		int distributionTempVariable$var23$138 = index$sample26$136;
																		double cv$probabilitySample26Value137 = (cv$probabilitySample12Value125 * distribution$sample26[((i - 0) / 1)][index$sample26$136]);
																		int traceTempVariable$var33$139_1 = distributionTempVariable$var23$138;
																		if(((i + 1) == j)) {
																			int traceTempVariable$var35$148_1 = distributionTempVariable$var23$138;
																			if(((i + 1) == j)) {
																				{
																					{
																						double cv$temp$31$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$126) + traceTempVariable$var31$4_1) + traceTempVariable$var33$139_1) / traceTempVariable$var35$148_1);
																							cv$temp$31$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value137);
																					}
																				}
																			}
																			for(int index$i$149 = 0; index$i$149 < size; index$i$149 += 1) {
																				if(!(index$i$149 == i)) {
																					for(int index$sample26$150 = 0; index$sample26$150 < weightings.length; index$sample26$150 += 1) {
																						int distributionTempVariable$var23$152 = index$sample26$150;
																						double cv$probabilitySample26Value151 = (cv$probabilitySample26Value137 * distribution$sample26[((index$i$149 - 0) / 1)][index$sample26$150]);
																						int traceTempVariable$var35$153_1 = distributionTempVariable$var23$152;
																						if(((index$i$149 + 1) == j)) {
																							{
																								{
																									double cv$temp$32$var36;
																									{
																										double var36 = ((((1.0 * distributionTempVariable$v1$126) + traceTempVariable$var31$4_1) + traceTempVariable$var33$139_1) / traceTempVariable$var35$153_1);
																										cv$temp$32$var36 = var36;
																									}
																									if(((Math.log(cv$probabilitySample26Value151) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var36)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value151) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value151) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var36));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value151) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value151) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var36)));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value151);
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
						int traceTempVariable$var33$5_1 = cv$currentValue;
						for(int j = 0; j < size; j += 1) {
							if((0 == j)) {
								if(!guard$sample18bernoulli40[((j - 0) / 1)]) {
									guard$sample18bernoulli40[((j - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											if(fixedFlag$sample12) {
												int traceTempVariable$var31$159_1 = cv$currentValue;
												if((0 == j)) {
													int traceTempVariable$var35$169_1 = cv$currentValue;
													if((0 == j)) {
														{
															{
																double cv$temp$33$var36;
																{
																	double var36 = ((((1.0 * v1) + traceTempVariable$var31$159_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$169_1);
																	cv$temp$33$var36 = var36;
																}
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var36)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var36));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var36)));
																}
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
															}
														}
													}
													if(!true) {
														for(int index$sample18$170 = 0; index$sample18$170 < weightings.length; index$sample18$170 += 1) {
															int distributionTempVariable$var15$172 = index$sample18$170;
															double cv$probabilitySample18Value171 = (1.0 * distribution$sample18[index$sample18$170]);
															int traceTempVariable$var35$173_1 = distributionTempVariable$var15$172;
															if((0 == j)) {
																{
																	{
																		double cv$temp$34$var36;
																		{
																			double var36 = ((((1.0 * v1) + traceTempVariable$var31$159_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$173_1);
																			cv$temp$34$var36 = var36;
																		}
																		if(((Math.log(cv$probabilitySample18Value171) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value171) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value171) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value171) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value171) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value171);
																	}
																}
															}
														}
													}
												}
												if(!true) {
													for(int index$sample18$160 = 0; index$sample18$160 < weightings.length; index$sample18$160 += 1) {
														int distributionTempVariable$var15$162 = index$sample18$160;
														double cv$probabilitySample18Value161 = (1.0 * distribution$sample18[index$sample18$160]);
														int traceTempVariable$var31$163_1 = distributionTempVariable$var15$162;
														if((0 == j)) {
															int traceTempVariable$var35$174_1 = cv$currentValue;
															if((0 == j)) {
																{
																	{
																		double cv$temp$35$var36;
																		{
																			double var36 = ((((1.0 * v1) + traceTempVariable$var31$163_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$174_1);
																			cv$temp$35$var36 = var36;
																		}
																		if(((Math.log(cv$probabilitySample18Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value161);
																	}
																}
															}
															int traceTempVariable$var35$175_1 = distributionTempVariable$var15$162;
															if((0 == j)) {
																{
																	{
																		double cv$temp$36$var36;
																		{
																			double var36 = ((((1.0 * v1) + traceTempVariable$var31$163_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$175_1);
																			cv$temp$36$var36 = var36;
																		}
																		if(((Math.log(cv$probabilitySample18Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value161);
																	}
																}
															}
															if(!true) {
																for(int index$sample18$176 = 0; index$sample18$176 < weightings.length; index$sample18$176 += 1) {
																	int distributionTempVariable$var15$178 = index$sample18$176;
																	double cv$probabilitySample18Value177 = (cv$probabilitySample18Value161 * distribution$sample18[index$sample18$176]);
																	int traceTempVariable$var35$179_1 = distributionTempVariable$var15$178;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$37$var36;
																				{
																					double var36 = ((((1.0 * v1) + traceTempVariable$var31$163_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$179_1);
																					cv$temp$37$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample18Value177) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value177) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value177) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value177) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value177) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value177);
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
													for(int index$sample12$155 = 0; index$sample12$155 < weightings.length; index$sample12$155 += 1) {
														int distributionTempVariable$v1$157 = index$sample12$155;
														double cv$probabilitySample12Value156 = (1.0 * distribution$sample12[index$sample12$155]);
														int traceTempVariable$var31$164_1 = cv$currentValue;
														if((0 == j)) {
															int traceTempVariable$var35$180_1 = cv$currentValue;
															if((0 == j)) {
																{
																	{
																		double cv$temp$38$var36;
																		{
																			double var36 = ((((1.0 * distributionTempVariable$v1$157) + traceTempVariable$var31$164_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$180_1);
																			cv$temp$38$var36 = var36;
																		}
																		if(((Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value156);
																	}
																}
															}
															if(!true) {
																for(int index$sample18$181 = 0; index$sample18$181 < weightings.length; index$sample18$181 += 1) {
																	int distributionTempVariable$var15$183 = index$sample18$181;
																	double cv$probabilitySample18Value182 = (cv$probabilitySample12Value156 * distribution$sample18[index$sample18$181]);
																	int traceTempVariable$var35$184_1 = distributionTempVariable$var15$183;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$39$var36;
																				{
																					double var36 = ((((1.0 * distributionTempVariable$v1$157) + traceTempVariable$var31$164_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$184_1);
																					cv$temp$39$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample18Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value182);
																			}
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample18$165 = 0; index$sample18$165 < weightings.length; index$sample18$165 += 1) {
																int distributionTempVariable$var15$167 = index$sample18$165;
																double cv$probabilitySample18Value166 = (cv$probabilitySample12Value156 * distribution$sample18[index$sample18$165]);
																int traceTempVariable$var31$168_1 = distributionTempVariable$var15$167;
																if((0 == j)) {
																	int traceTempVariable$var35$185_1 = cv$currentValue;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$40$var36;
																				{
																					double var36 = ((((1.0 * distributionTempVariable$v1$157) + traceTempVariable$var31$168_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$185_1);
																					cv$temp$40$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample18Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value166);
																			}
																		}
																	}
																	int traceTempVariable$var35$186_1 = distributionTempVariable$var15$167;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$41$var36;
																				{
																					double var36 = ((((1.0 * distributionTempVariable$v1$157) + traceTempVariable$var31$168_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$186_1);
																					cv$temp$41$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample18Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value166);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample18$187 = 0; index$sample18$187 < weightings.length; index$sample18$187 += 1) {
																			int distributionTempVariable$var15$189 = index$sample18$187;
																			double cv$probabilitySample18Value188 = (cv$probabilitySample18Value166 * distribution$sample18[index$sample18$187]);
																			int traceTempVariable$var35$190_1 = distributionTempVariable$var15$189;
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$42$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$157) + traceTempVariable$var31$168_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$190_1);
																							cv$temp$42$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample18Value188) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value188) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value188) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value188) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value188) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value188);
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
											if(fixedFlag$sample12) {
												if(fixedFlag$sample26) {
													for(int i = 0; i < size; i += 1) {
														if(((i + 1) == j)) {
															int traceTempVariable$var35$208_1 = cv$currentValue;
															if((0 == j)) {
																{
																	{
																		double cv$temp$43$var36;
																		{
																			double var36 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var33$5_1) / traceTempVariable$var35$208_1);
																			cv$temp$43$var36 = var36;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
															if(!true) {
																for(int index$sample18$209 = 0; index$sample18$209 < weightings.length; index$sample18$209 += 1) {
																	int distributionTempVariable$var15$211 = index$sample18$209;
																	double cv$probabilitySample18Value210 = (1.0 * distribution$sample18[index$sample18$209]);
																	int traceTempVariable$var35$212_1 = distributionTempVariable$var15$211;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$44$var36;
																				{
																					double var36 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var33$5_1) / traceTempVariable$var35$212_1);
																					cv$temp$44$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample18Value210) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value210) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value210) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value210) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value210) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value210);
																			}
																		}
																	}
																}
															}
														}
													}
												} else {
													for(int i = 0; i < size; i += 1) {
														if(true) {
															for(int index$sample26$198 = 0; index$sample26$198 < weightings.length; index$sample26$198 += 1) {
																int distributionTempVariable$var23$200 = index$sample26$198;
																double cv$probabilitySample26Value199 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$198]);
																int traceTempVariable$var31$201_1 = distributionTempVariable$var23$200;
																if(((i + 1) == j)) {
																	int traceTempVariable$var35$213_1 = cv$currentValue;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$45$var36;
																				{
																					double var36 = ((((1.0 * v1) + traceTempVariable$var31$201_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$213_1);
																					cv$temp$45$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample26Value199) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value199) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value199) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value199) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value199) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value199);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample18$214 = 0; index$sample18$214 < weightings.length; index$sample18$214 += 1) {
																			int distributionTempVariable$var15$216 = index$sample18$214;
																			double cv$probabilitySample18Value215 = (cv$probabilitySample26Value199 * distribution$sample18[index$sample18$214]);
																			int traceTempVariable$var35$217_1 = distributionTempVariable$var15$216;
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$46$var36;
																						{
																							double var36 = ((((1.0 * v1) + traceTempVariable$var31$201_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$217_1);
																							cv$temp$46$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample18Value215) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value215) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value215) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value215) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value215) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value215);
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
												if(true) {
													for(int index$sample12$192 = 0; index$sample12$192 < weightings.length; index$sample12$192 += 1) {
														int distributionTempVariable$v1$194 = index$sample12$192;
														double cv$probabilitySample12Value193 = (1.0 * distribution$sample12[index$sample12$192]);
														if(fixedFlag$sample26) {
															for(int i = 0; i < size; i += 1) {
																if(((i + 1) == j)) {
																	int traceTempVariable$var35$218_1 = cv$currentValue;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$47$var36;
																				{
																					double var36 = ((((1.0 * distributionTempVariable$v1$194) + v2[j]) + traceTempVariable$var33$5_1) / traceTempVariable$var35$218_1);
																					cv$temp$47$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample12Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value193);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample18$219 = 0; index$sample18$219 < weightings.length; index$sample18$219 += 1) {
																			int distributionTempVariable$var15$221 = index$sample18$219;
																			double cv$probabilitySample18Value220 = (cv$probabilitySample12Value193 * distribution$sample18[index$sample18$219]);
																			int traceTempVariable$var35$222_1 = distributionTempVariable$var15$221;
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$48$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$194) + v2[j]) + traceTempVariable$var33$5_1) / traceTempVariable$var35$222_1);
																							cv$temp$48$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample18Value220) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value220) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value220) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value220) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value220) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value220);
																					}
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i = 0; i < size; i += 1) {
																if(true) {
																	for(int index$sample26$204 = 0; index$sample26$204 < weightings.length; index$sample26$204 += 1) {
																		int distributionTempVariable$var23$206 = index$sample26$204;
																		double cv$probabilitySample26Value205 = (cv$probabilitySample12Value193 * distribution$sample26[((i - 0) / 1)][index$sample26$204]);
																		int traceTempVariable$var31$207_1 = distributionTempVariable$var23$206;
																		if(((i + 1) == j)) {
																			int traceTempVariable$var35$223_1 = cv$currentValue;
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$49$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$194) + traceTempVariable$var31$207_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$223_1);
																							cv$temp$49$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value205) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value205) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value205) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value205) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value205) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value205);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample18$224 = 0; index$sample18$224 < weightings.length; index$sample18$224 += 1) {
																					int distributionTempVariable$var15$226 = index$sample18$224;
																					double cv$probabilitySample18Value225 = (cv$probabilitySample26Value205 * distribution$sample18[index$sample18$224]);
																					int traceTempVariable$var35$227_1 = distributionTempVariable$var15$226;
																					if((0 == j)) {
																						{
																							{
																								double cv$temp$50$var36;
																								{
																									double var36 = ((((1.0 * distributionTempVariable$v1$194) + traceTempVariable$var31$207_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$227_1);
																									cv$temp$50$var36 = var36;
																								}
																								if(((Math.log(cv$probabilitySample18Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var36)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var36));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var36)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value225);
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
											if(fixedFlag$sample12) {
												int traceTempVariable$var31$233_1 = cv$currentValue;
												if((0 == j)) {
													if(fixedFlag$sample26) {
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == j)) {
																{
																	{
																		double cv$temp$51$var36;
																		{
																			double var36 = ((((1.0 * v1) + traceTempVariable$var31$233_1) + traceTempVariable$var33$5_1) / v2[j]);
																			cv$temp$51$var36 = var36;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													} else {
														for(int i = 0; i < size; i += 1) {
															if(true) {
																for(int index$sample26$245 = 0; index$sample26$245 < weightings.length; index$sample26$245 += 1) {
																	int distributionTempVariable$var23$247 = index$sample26$245;
																	double cv$probabilitySample26Value246 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$245]);
																	int traceTempVariable$var35$248_1 = distributionTempVariable$var23$247;
																	if(((i + 1) == j)) {
																		{
																			{
																				double cv$temp$52$var36;
																				{
																					double var36 = ((((1.0 * v1) + traceTempVariable$var31$233_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$248_1);
																					cv$temp$52$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample26Value246) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value246) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value246) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value246) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value246) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value246);
																			}
																		}
																	}
																}
															}
														}
													}
												}
												if(!true) {
													for(int index$sample18$234 = 0; index$sample18$234 < weightings.length; index$sample18$234 += 1) {
														int distributionTempVariable$var15$236 = index$sample18$234;
														double cv$probabilitySample18Value235 = (1.0 * distribution$sample18[index$sample18$234]);
														int traceTempVariable$var31$237_1 = distributionTempVariable$var15$236;
														if((0 == j)) {
															if(fixedFlag$sample26) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == j)) {
																		{
																			{
																				double cv$temp$53$var36;
																				{
																					double var36 = ((((1.0 * v1) + traceTempVariable$var31$237_1) + traceTempVariable$var33$5_1) / v2[j]);
																					cv$temp$53$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample18Value235) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value235) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value235) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value235) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value235) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value235);
																			}
																		}
																	}
																}
															} else {
																for(int i = 0; i < size; i += 1) {
																	if(true) {
																		for(int index$sample26$251 = 0; index$sample26$251 < weightings.length; index$sample26$251 += 1) {
																			int distributionTempVariable$var23$253 = index$sample26$251;
																			double cv$probabilitySample26Value252 = (cv$probabilitySample18Value235 * distribution$sample26[((i - 0) / 1)][index$sample26$251]);
																			int traceTempVariable$var35$254_1 = distributionTempVariable$var23$253;
																			if(((i + 1) == j)) {
																				{
																					{
																						double cv$temp$54$var36;
																						{
																							double var36 = ((((1.0 * v1) + traceTempVariable$var31$237_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$254_1);
																							cv$temp$54$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value252);
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
												if(true) {
													for(int index$sample12$229 = 0; index$sample12$229 < weightings.length; index$sample12$229 += 1) {
														int distributionTempVariable$v1$231 = index$sample12$229;
														double cv$probabilitySample12Value230 = (1.0 * distribution$sample12[index$sample12$229]);
														int traceTempVariable$var31$238_1 = cv$currentValue;
														if((0 == j)) {
															if(fixedFlag$sample26) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == j)) {
																		{
																			{
																				double cv$temp$55$var36;
																				{
																					double var36 = ((((1.0 * distributionTempVariable$v1$231) + traceTempVariable$var31$238_1) + traceTempVariable$var33$5_1) / v2[j]);
																					cv$temp$55$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample12Value230) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value230) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value230) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value230) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value230) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value230);
																			}
																		}
																	}
																}
															} else {
																for(int i = 0; i < size; i += 1) {
																	if(true) {
																		for(int index$sample26$257 = 0; index$sample26$257 < weightings.length; index$sample26$257 += 1) {
																			int distributionTempVariable$var23$259 = index$sample26$257;
																			double cv$probabilitySample26Value258 = (cv$probabilitySample12Value230 * distribution$sample26[((i - 0) / 1)][index$sample26$257]);
																			int traceTempVariable$var35$260_1 = distributionTempVariable$var23$259;
																			if(((i + 1) == j)) {
																				{
																					{
																						double cv$temp$56$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$231) + traceTempVariable$var31$238_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$260_1);
																							cv$temp$56$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value258) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value258) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value258) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value258) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value258) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value258);
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample18$239 = 0; index$sample18$239 < weightings.length; index$sample18$239 += 1) {
																int distributionTempVariable$var15$241 = index$sample18$239;
																double cv$probabilitySample18Value240 = (cv$probabilitySample12Value230 * distribution$sample18[index$sample18$239]);
																int traceTempVariable$var31$242_1 = distributionTempVariable$var15$241;
																if((0 == j)) {
																	if(fixedFlag$sample26) {
																		for(int i = 0; i < size; i += 1) {
																			if(((i + 1) == j)) {
																				{
																					{
																						double cv$temp$57$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$231) + traceTempVariable$var31$242_1) + traceTempVariable$var33$5_1) / v2[j]);
																							cv$temp$57$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample18Value240) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value240) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value240) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value240) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value240) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value240);
																					}
																				}
																			}
																		}
																	} else {
																		for(int i = 0; i < size; i += 1) {
																			if(true) {
																				for(int index$sample26$263 = 0; index$sample26$263 < weightings.length; index$sample26$263 += 1) {
																					int distributionTempVariable$var23$265 = index$sample26$263;
																					double cv$probabilitySample26Value264 = (cv$probabilitySample18Value240 * distribution$sample26[((i - 0) / 1)][index$sample26$263]);
																					int traceTempVariable$var35$266_1 = distributionTempVariable$var23$265;
																					if(((i + 1) == j)) {
																						{
																							{
																								double cv$temp$58$var36;
																								{
																									double var36 = ((((1.0 * distributionTempVariable$v1$231) + traceTempVariable$var31$242_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$266_1);
																									cv$temp$58$var36 = var36;
																								}
																								if(((Math.log(cv$probabilitySample26Value264) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var36)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value264) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value264) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var36));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value264) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value264) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var36)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value264);
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
											if(fixedFlag$sample12) {
												if(fixedFlag$sample26) {
													for(int i = 0; i < size; i += 1) {
														if(((i + 1) == j)) {
															for(int index$i$284_1 = 0; index$i$284_1 < size; index$i$284_1 += 1) {
																if(((index$i$284_1 + 1) == j)) {
																	{
																		{
																			double cv$temp$59$var36;
																			{
																				double var36 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var33$5_1) / v2[j]);
																				cv$temp$59$var36 = var36;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													}
												} else {
													for(int i = 0; i < size; i += 1) {
														if(true) {
															for(int index$sample26$274 = 0; index$sample26$274 < weightings.length; index$sample26$274 += 1) {
																int distributionTempVariable$var23$276 = index$sample26$274;
																double cv$probabilitySample26Value275 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$274]);
																int traceTempVariable$var31$277_1 = distributionTempVariable$var23$276;
																if(((i + 1) == j)) {
																	int traceTempVariable$var35$285_1 = distributionTempVariable$var23$276;
																	if(((i + 1) == j)) {
																		{
																			{
																				double cv$temp$60$var36;
																				{
																					double var36 = ((((1.0 * v1) + traceTempVariable$var31$277_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$285_1);
																					cv$temp$60$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value275);
																			}
																		}
																	}
																	for(int index$i$286 = 0; index$i$286 < size; index$i$286 += 1) {
																		if(!(index$i$286 == i)) {
																			for(int index$sample26$287 = 0; index$sample26$287 < weightings.length; index$sample26$287 += 1) {
																				int distributionTempVariable$var23$289 = index$sample26$287;
																				double cv$probabilitySample26Value288 = (cv$probabilitySample26Value275 * distribution$sample26[((index$i$286 - 0) / 1)][index$sample26$287]);
																				int traceTempVariable$var35$290_1 = distributionTempVariable$var23$289;
																				if(((index$i$286 + 1) == j)) {
																					{
																						{
																							double cv$temp$61$var36;
																							{
																								double var36 = ((((1.0 * v1) + traceTempVariable$var31$277_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$290_1);
																								cv$temp$61$var36 = var36;
																							}
																							if(((Math.log(cv$probabilitySample26Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var36)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var36));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var36)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value288);
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
												if(true) {
													for(int index$sample12$268 = 0; index$sample12$268 < weightings.length; index$sample12$268 += 1) {
														int distributionTempVariable$v1$270 = index$sample12$268;
														double cv$probabilitySample12Value269 = (1.0 * distribution$sample12[index$sample12$268]);
														if(fixedFlag$sample26) {
															for(int i = 0; i < size; i += 1) {
																if(((i + 1) == j)) {
																	for(int index$i$291_1 = 0; index$i$291_1 < size; index$i$291_1 += 1) {
																		if(((index$i$291_1 + 1) == j)) {
																			{
																				{
																					double cv$temp$62$var36;
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$270) + v2[j]) + traceTempVariable$var33$5_1) / v2[j]);
																						cv$temp$62$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample12Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value269);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i = 0; i < size; i += 1) {
																if(true) {
																	for(int index$sample26$280 = 0; index$sample26$280 < weightings.length; index$sample26$280 += 1) {
																		int distributionTempVariable$var23$282 = index$sample26$280;
																		double cv$probabilitySample26Value281 = (cv$probabilitySample12Value269 * distribution$sample26[((i - 0) / 1)][index$sample26$280]);
																		int traceTempVariable$var31$283_1 = distributionTempVariable$var23$282;
																		if(((i + 1) == j)) {
																			int traceTempVariable$var35$292_1 = distributionTempVariable$var23$282;
																			if(((i + 1) == j)) {
																				{
																					{
																						double cv$temp$63$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$270) + traceTempVariable$var31$283_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$292_1);
																							cv$temp$63$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value281);
																					}
																				}
																			}
																			for(int index$i$293 = 0; index$i$293 < size; index$i$293 += 1) {
																				if(!(index$i$293 == i)) {
																					for(int index$sample26$294 = 0; index$sample26$294 < weightings.length; index$sample26$294 += 1) {
																						int distributionTempVariable$var23$296 = index$sample26$294;
																						double cv$probabilitySample26Value295 = (cv$probabilitySample26Value281 * distribution$sample26[((index$i$293 - 0) / 1)][index$sample26$294]);
																						int traceTempVariable$var35$297_1 = distributionTempVariable$var23$296;
																						if(((index$i$293 + 1) == j)) {
																							{
																								{
																									double cv$temp$64$var36;
																									{
																										double var36 = ((((1.0 * distributionTempVariable$v1$270) + traceTempVariable$var31$283_1) + traceTempVariable$var33$5_1) / traceTempVariable$var35$297_1);
																										cv$temp$64$var36 = var36;
																									}
																									if(((Math.log(cv$probabilitySample26Value295) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var36)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value295) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value295) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var36));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value295) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value295) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var36)));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value295);
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
						int traceTempVariable$var35$6_1 = cv$currentValue;
						for(int j = 0; j < size; j += 1) {
							if((0 == j)) {
								if(!guard$sample18bernoulli40[((j - 0) / 1)]) {
									guard$sample18bernoulli40[((j - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											if(fixedFlag$sample12) {
												int traceTempVariable$var31$303_1 = cv$currentValue;
												if((0 == j)) {
													int traceTempVariable$var33$313_1 = cv$currentValue;
													if((0 == j)) {
														{
															{
																double cv$temp$65$var36;
																{
																	double var36 = ((((1.0 * v1) + traceTempVariable$var31$303_1) + traceTempVariable$var33$313_1) / traceTempVariable$var35$6_1);
																	cv$temp$65$var36 = var36;
																}
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var36)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var36));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var36)));
																}
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
															}
														}
													}
													if(!true) {
														for(int index$sample18$314 = 0; index$sample18$314 < weightings.length; index$sample18$314 += 1) {
															int distributionTempVariable$var15$316 = index$sample18$314;
															double cv$probabilitySample18Value315 = (1.0 * distribution$sample18[index$sample18$314]);
															int traceTempVariable$var33$317_1 = distributionTempVariable$var15$316;
															if((0 == j)) {
																{
																	{
																		double cv$temp$66$var36;
																		{
																			double var36 = ((((1.0 * v1) + traceTempVariable$var31$303_1) + traceTempVariable$var33$317_1) / traceTempVariable$var35$6_1);
																			cv$temp$66$var36 = var36;
																		}
																		if(((Math.log(cv$probabilitySample18Value315) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value315) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value315) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value315) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value315) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value315);
																	}
																}
															}
														}
													}
												}
												if(!true) {
													for(int index$sample18$304 = 0; index$sample18$304 < weightings.length; index$sample18$304 += 1) {
														int distributionTempVariable$var15$306 = index$sample18$304;
														double cv$probabilitySample18Value305 = (1.0 * distribution$sample18[index$sample18$304]);
														int traceTempVariable$var31$307_1 = distributionTempVariable$var15$306;
														if((0 == j)) {
															int traceTempVariable$var33$318_1 = cv$currentValue;
															if((0 == j)) {
																{
																	{
																		double cv$temp$67$var36;
																		{
																			double var36 = ((((1.0 * v1) + traceTempVariable$var31$307_1) + traceTempVariable$var33$318_1) / traceTempVariable$var35$6_1);
																			cv$temp$67$var36 = var36;
																		}
																		if(((Math.log(cv$probabilitySample18Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value305);
																	}
																}
															}
															int traceTempVariable$var33$319_1 = distributionTempVariable$var15$306;
															if((0 == j)) {
																{
																	{
																		double cv$temp$68$var36;
																		{
																			double var36 = ((((1.0 * v1) + traceTempVariable$var31$307_1) + traceTempVariable$var33$319_1) / traceTempVariable$var35$6_1);
																			cv$temp$68$var36 = var36;
																		}
																		if(((Math.log(cv$probabilitySample18Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value305);
																	}
																}
															}
															if(!true) {
																for(int index$sample18$320 = 0; index$sample18$320 < weightings.length; index$sample18$320 += 1) {
																	int distributionTempVariable$var15$322 = index$sample18$320;
																	double cv$probabilitySample18Value321 = (cv$probabilitySample18Value305 * distribution$sample18[index$sample18$320]);
																	int traceTempVariable$var33$323_1 = distributionTempVariable$var15$322;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$69$var36;
																				{
																					double var36 = ((((1.0 * v1) + traceTempVariable$var31$307_1) + traceTempVariable$var33$323_1) / traceTempVariable$var35$6_1);
																					cv$temp$69$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample18Value321) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value321) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value321) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value321) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value321) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value321);
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
													for(int index$sample12$299 = 0; index$sample12$299 < weightings.length; index$sample12$299 += 1) {
														int distributionTempVariable$v1$301 = index$sample12$299;
														double cv$probabilitySample12Value300 = (1.0 * distribution$sample12[index$sample12$299]);
														int traceTempVariable$var31$308_1 = cv$currentValue;
														if((0 == j)) {
															int traceTempVariable$var33$324_1 = cv$currentValue;
															if((0 == j)) {
																{
																	{
																		double cv$temp$70$var36;
																		{
																			double var36 = ((((1.0 * distributionTempVariable$v1$301) + traceTempVariable$var31$308_1) + traceTempVariable$var33$324_1) / traceTempVariable$var35$6_1);
																			cv$temp$70$var36 = var36;
																		}
																		if(((Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value300);
																	}
																}
															}
															if(!true) {
																for(int index$sample18$325 = 0; index$sample18$325 < weightings.length; index$sample18$325 += 1) {
																	int distributionTempVariable$var15$327 = index$sample18$325;
																	double cv$probabilitySample18Value326 = (cv$probabilitySample12Value300 * distribution$sample18[index$sample18$325]);
																	int traceTempVariable$var33$328_1 = distributionTempVariable$var15$327;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$71$var36;
																				{
																					double var36 = ((((1.0 * distributionTempVariable$v1$301) + traceTempVariable$var31$308_1) + traceTempVariable$var33$328_1) / traceTempVariable$var35$6_1);
																					cv$temp$71$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample18Value326) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value326) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value326) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value326) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value326) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value326);
																			}
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample18$309 = 0; index$sample18$309 < weightings.length; index$sample18$309 += 1) {
																int distributionTempVariable$var15$311 = index$sample18$309;
																double cv$probabilitySample18Value310 = (cv$probabilitySample12Value300 * distribution$sample18[index$sample18$309]);
																int traceTempVariable$var31$312_1 = distributionTempVariable$var15$311;
																if((0 == j)) {
																	int traceTempVariable$var33$329_1 = cv$currentValue;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$72$var36;
																				{
																					double var36 = ((((1.0 * distributionTempVariable$v1$301) + traceTempVariable$var31$312_1) + traceTempVariable$var33$329_1) / traceTempVariable$var35$6_1);
																					cv$temp$72$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value310);
																			}
																		}
																	}
																	int traceTempVariable$var33$330_1 = distributionTempVariable$var15$311;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$73$var36;
																				{
																					double var36 = ((((1.0 * distributionTempVariable$v1$301) + traceTempVariable$var31$312_1) + traceTempVariable$var33$330_1) / traceTempVariable$var35$6_1);
																					cv$temp$73$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value310);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample18$331 = 0; index$sample18$331 < weightings.length; index$sample18$331 += 1) {
																			int distributionTempVariable$var15$333 = index$sample18$331;
																			double cv$probabilitySample18Value332 = (cv$probabilitySample18Value310 * distribution$sample18[index$sample18$331]);
																			int traceTempVariable$var33$334_1 = distributionTempVariable$var15$333;
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$74$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$301) + traceTempVariable$var31$312_1) + traceTempVariable$var33$334_1) / traceTempVariable$var35$6_1);
																							cv$temp$74$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample18Value332) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value332) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value332) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value332) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value332) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value332);
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
											if(fixedFlag$sample12) {
												if(fixedFlag$sample26) {
													for(int i = 0; i < size; i += 1) {
														if(((i + 1) == j)) {
															int traceTempVariable$var33$352_1 = cv$currentValue;
															if((0 == j)) {
																{
																	{
																		double cv$temp$75$var36;
																		{
																			double var36 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var33$352_1) / traceTempVariable$var35$6_1);
																			cv$temp$75$var36 = var36;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
															if(!true) {
																for(int index$sample18$353 = 0; index$sample18$353 < weightings.length; index$sample18$353 += 1) {
																	int distributionTempVariable$var15$355 = index$sample18$353;
																	double cv$probabilitySample18Value354 = (1.0 * distribution$sample18[index$sample18$353]);
																	int traceTempVariable$var33$356_1 = distributionTempVariable$var15$355;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$76$var36;
																				{
																					double var36 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var33$356_1) / traceTempVariable$var35$6_1);
																					cv$temp$76$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample18Value354) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value354) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value354) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value354) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value354) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value354);
																			}
																		}
																	}
																}
															}
														}
													}
												} else {
													for(int i = 0; i < size; i += 1) {
														if(true) {
															for(int index$sample26$342 = 0; index$sample26$342 < weightings.length; index$sample26$342 += 1) {
																int distributionTempVariable$var23$344 = index$sample26$342;
																double cv$probabilitySample26Value343 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$342]);
																int traceTempVariable$var31$345_1 = distributionTempVariable$var23$344;
																if(((i + 1) == j)) {
																	int traceTempVariable$var33$357_1 = cv$currentValue;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$77$var36;
																				{
																					double var36 = ((((1.0 * v1) + traceTempVariable$var31$345_1) + traceTempVariable$var33$357_1) / traceTempVariable$var35$6_1);
																					cv$temp$77$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample26Value343) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value343) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value343) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value343) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value343) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value343);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample18$358 = 0; index$sample18$358 < weightings.length; index$sample18$358 += 1) {
																			int distributionTempVariable$var15$360 = index$sample18$358;
																			double cv$probabilitySample18Value359 = (cv$probabilitySample26Value343 * distribution$sample18[index$sample18$358]);
																			int traceTempVariable$var33$361_1 = distributionTempVariable$var15$360;
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$78$var36;
																						{
																							double var36 = ((((1.0 * v1) + traceTempVariable$var31$345_1) + traceTempVariable$var33$361_1) / traceTempVariable$var35$6_1);
																							cv$temp$78$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample18Value359) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value359) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value359) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value359) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value359) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value359);
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
												if(true) {
													for(int index$sample12$336 = 0; index$sample12$336 < weightings.length; index$sample12$336 += 1) {
														int distributionTempVariable$v1$338 = index$sample12$336;
														double cv$probabilitySample12Value337 = (1.0 * distribution$sample12[index$sample12$336]);
														if(fixedFlag$sample26) {
															for(int i = 0; i < size; i += 1) {
																if(((i + 1) == j)) {
																	int traceTempVariable$var33$362_1 = cv$currentValue;
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$79$var36;
																				{
																					double var36 = ((((1.0 * distributionTempVariable$v1$338) + v2[j]) + traceTempVariable$var33$362_1) / traceTempVariable$var35$6_1);
																					cv$temp$79$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample12Value337) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value337) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value337) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value337) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value337) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value337);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample18$363 = 0; index$sample18$363 < weightings.length; index$sample18$363 += 1) {
																			int distributionTempVariable$var15$365 = index$sample18$363;
																			double cv$probabilitySample18Value364 = (cv$probabilitySample12Value337 * distribution$sample18[index$sample18$363]);
																			int traceTempVariable$var33$366_1 = distributionTempVariable$var15$365;
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$80$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$338) + v2[j]) + traceTempVariable$var33$366_1) / traceTempVariable$var35$6_1);
																							cv$temp$80$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample18Value364) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value364) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value364) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value364) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value364) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value364);
																					}
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i = 0; i < size; i += 1) {
																if(true) {
																	for(int index$sample26$348 = 0; index$sample26$348 < weightings.length; index$sample26$348 += 1) {
																		int distributionTempVariable$var23$350 = index$sample26$348;
																		double cv$probabilitySample26Value349 = (cv$probabilitySample12Value337 * distribution$sample26[((i - 0) / 1)][index$sample26$348]);
																		int traceTempVariable$var31$351_1 = distributionTempVariable$var23$350;
																		if(((i + 1) == j)) {
																			int traceTempVariable$var33$367_1 = cv$currentValue;
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$81$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$338) + traceTempVariable$var31$351_1) + traceTempVariable$var33$367_1) / traceTempVariable$var35$6_1);
																							cv$temp$81$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value349) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value349) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value349) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value349) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value349) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value349);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample18$368 = 0; index$sample18$368 < weightings.length; index$sample18$368 += 1) {
																					int distributionTempVariable$var15$370 = index$sample18$368;
																					double cv$probabilitySample18Value369 = (cv$probabilitySample26Value349 * distribution$sample18[index$sample18$368]);
																					int traceTempVariable$var33$371_1 = distributionTempVariable$var15$370;
																					if((0 == j)) {
																						{
																							{
																								double cv$temp$82$var36;
																								{
																									double var36 = ((((1.0 * distributionTempVariable$v1$338) + traceTempVariable$var31$351_1) + traceTempVariable$var33$371_1) / traceTempVariable$var35$6_1);
																									cv$temp$82$var36 = var36;
																								}
																								if(((Math.log(cv$probabilitySample18Value369) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var36)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value369) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value369) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var36));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value369) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value369) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var36)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value369);
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
											if(fixedFlag$sample12) {
												int traceTempVariable$var31$377_1 = cv$currentValue;
												if((0 == j)) {
													if(fixedFlag$sample26) {
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == j)) {
																{
																	{
																		double cv$temp$83$var36;
																		{
																			double var36 = ((((1.0 * v1) + traceTempVariable$var31$377_1) + v2[j]) / traceTempVariable$var35$6_1);
																			cv$temp$83$var36 = var36;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													} else {
														for(int i = 0; i < size; i += 1) {
															if(true) {
																for(int index$sample26$389 = 0; index$sample26$389 < weightings.length; index$sample26$389 += 1) {
																	int distributionTempVariable$var23$391 = index$sample26$389;
																	double cv$probabilitySample26Value390 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$389]);
																	int traceTempVariable$var33$392_1 = distributionTempVariable$var23$391;
																	if(((i + 1) == j)) {
																		{
																			{
																				double cv$temp$84$var36;
																				{
																					double var36 = ((((1.0 * v1) + traceTempVariable$var31$377_1) + traceTempVariable$var33$392_1) / traceTempVariable$var35$6_1);
																					cv$temp$84$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample26Value390) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value390) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value390) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value390) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value390) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value390);
																			}
																		}
																	}
																}
															}
														}
													}
												}
												if(!true) {
													for(int index$sample18$378 = 0; index$sample18$378 < weightings.length; index$sample18$378 += 1) {
														int distributionTempVariable$var15$380 = index$sample18$378;
														double cv$probabilitySample18Value379 = (1.0 * distribution$sample18[index$sample18$378]);
														int traceTempVariable$var31$381_1 = distributionTempVariable$var15$380;
														if((0 == j)) {
															if(fixedFlag$sample26) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == j)) {
																		{
																			{
																				double cv$temp$85$var36;
																				{
																					double var36 = ((((1.0 * v1) + traceTempVariable$var31$381_1) + v2[j]) / traceTempVariable$var35$6_1);
																					cv$temp$85$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample18Value379) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value379) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value379) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value379) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value379) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value379);
																			}
																		}
																	}
																}
															} else {
																for(int i = 0; i < size; i += 1) {
																	if(true) {
																		for(int index$sample26$395 = 0; index$sample26$395 < weightings.length; index$sample26$395 += 1) {
																			int distributionTempVariable$var23$397 = index$sample26$395;
																			double cv$probabilitySample26Value396 = (cv$probabilitySample18Value379 * distribution$sample26[((i - 0) / 1)][index$sample26$395]);
																			int traceTempVariable$var33$398_1 = distributionTempVariable$var23$397;
																			if(((i + 1) == j)) {
																				{
																					{
																						double cv$temp$86$var36;
																						{
																							double var36 = ((((1.0 * v1) + traceTempVariable$var31$381_1) + traceTempVariable$var33$398_1) / traceTempVariable$var35$6_1);
																							cv$temp$86$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value396) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value396) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value396) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value396) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value396) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value396);
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
												if(true) {
													for(int index$sample12$373 = 0; index$sample12$373 < weightings.length; index$sample12$373 += 1) {
														int distributionTempVariable$v1$375 = index$sample12$373;
														double cv$probabilitySample12Value374 = (1.0 * distribution$sample12[index$sample12$373]);
														int traceTempVariable$var31$382_1 = cv$currentValue;
														if((0 == j)) {
															if(fixedFlag$sample26) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == j)) {
																		{
																			{
																				double cv$temp$87$var36;
																				{
																					double var36 = ((((1.0 * distributionTempVariable$v1$375) + traceTempVariable$var31$382_1) + v2[j]) / traceTempVariable$var35$6_1);
																					cv$temp$87$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample12Value374) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value374) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value374) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value374) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value374) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value374);
																			}
																		}
																	}
																}
															} else {
																for(int i = 0; i < size; i += 1) {
																	if(true) {
																		for(int index$sample26$401 = 0; index$sample26$401 < weightings.length; index$sample26$401 += 1) {
																			int distributionTempVariable$var23$403 = index$sample26$401;
																			double cv$probabilitySample26Value402 = (cv$probabilitySample12Value374 * distribution$sample26[((i - 0) / 1)][index$sample26$401]);
																			int traceTempVariable$var33$404_1 = distributionTempVariable$var23$403;
																			if(((i + 1) == j)) {
																				{
																					{
																						double cv$temp$88$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$375) + traceTempVariable$var31$382_1) + traceTempVariable$var33$404_1) / traceTempVariable$var35$6_1);
																							cv$temp$88$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value402) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value402) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value402) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value402) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value402) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value402);
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample18$383 = 0; index$sample18$383 < weightings.length; index$sample18$383 += 1) {
																int distributionTempVariable$var15$385 = index$sample18$383;
																double cv$probabilitySample18Value384 = (cv$probabilitySample12Value374 * distribution$sample18[index$sample18$383]);
																int traceTempVariable$var31$386_1 = distributionTempVariable$var15$385;
																if((0 == j)) {
																	if(fixedFlag$sample26) {
																		for(int i = 0; i < size; i += 1) {
																			if(((i + 1) == j)) {
																				{
																					{
																						double cv$temp$89$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$375) + traceTempVariable$var31$386_1) + v2[j]) / traceTempVariable$var35$6_1);
																							cv$temp$89$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample18Value384) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value384) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value384) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value384) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value384) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value384);
																					}
																				}
																			}
																		}
																	} else {
																		for(int i = 0; i < size; i += 1) {
																			if(true) {
																				for(int index$sample26$407 = 0; index$sample26$407 < weightings.length; index$sample26$407 += 1) {
																					int distributionTempVariable$var23$409 = index$sample26$407;
																					double cv$probabilitySample26Value408 = (cv$probabilitySample18Value384 * distribution$sample26[((i - 0) / 1)][index$sample26$407]);
																					int traceTempVariable$var33$410_1 = distributionTempVariable$var23$409;
																					if(((i + 1) == j)) {
																						{
																							{
																								double cv$temp$90$var36;
																								{
																									double var36 = ((((1.0 * distributionTempVariable$v1$375) + traceTempVariable$var31$386_1) + traceTempVariable$var33$410_1) / traceTempVariable$var35$6_1);
																									cv$temp$90$var36 = var36;
																								}
																								if(((Math.log(cv$probabilitySample26Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var36)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var36));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var36)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value408);
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
											if(fixedFlag$sample12) {
												if(fixedFlag$sample26) {
													for(int i = 0; i < size; i += 1) {
														if(((i + 1) == j)) {
															for(int index$i$428_1 = 0; index$i$428_1 < size; index$i$428_1 += 1) {
																if(((index$i$428_1 + 1) == j)) {
																	{
																		{
																			double cv$temp$91$var36;
																			{
																				double var36 = ((((1.0 * v1) + v2[j]) + v2[j]) / traceTempVariable$var35$6_1);
																				cv$temp$91$var36 = var36;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													}
												} else {
													for(int i = 0; i < size; i += 1) {
														if(true) {
															for(int index$sample26$418 = 0; index$sample26$418 < weightings.length; index$sample26$418 += 1) {
																int distributionTempVariable$var23$420 = index$sample26$418;
																double cv$probabilitySample26Value419 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$418]);
																int traceTempVariable$var31$421_1 = distributionTempVariable$var23$420;
																if(((i + 1) == j)) {
																	int traceTempVariable$var33$429_1 = distributionTempVariable$var23$420;
																	if(((i + 1) == j)) {
																		{
																			{
																				double cv$temp$92$var36;
																				{
																					double var36 = ((((1.0 * v1) + traceTempVariable$var31$421_1) + traceTempVariable$var33$429_1) / traceTempVariable$var35$6_1);
																					cv$temp$92$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample26Value419) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value419) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value419) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value419) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value419) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value419);
																			}
																		}
																	}
																	for(int index$i$430 = 0; index$i$430 < size; index$i$430 += 1) {
																		if(!(index$i$430 == i)) {
																			for(int index$sample26$431 = 0; index$sample26$431 < weightings.length; index$sample26$431 += 1) {
																				int distributionTempVariable$var23$433 = index$sample26$431;
																				double cv$probabilitySample26Value432 = (cv$probabilitySample26Value419 * distribution$sample26[((index$i$430 - 0) / 1)][index$sample26$431]);
																				int traceTempVariable$var33$434_1 = distributionTempVariable$var23$433;
																				if(((index$i$430 + 1) == j)) {
																					{
																						{
																							double cv$temp$93$var36;
																							{
																								double var36 = ((((1.0 * v1) + traceTempVariable$var31$421_1) + traceTempVariable$var33$434_1) / traceTempVariable$var35$6_1);
																								cv$temp$93$var36 = var36;
																							}
																							if(((Math.log(cv$probabilitySample26Value432) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var36)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value432) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value432) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var36));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value432) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value432) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var36)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value432);
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
												if(true) {
													for(int index$sample12$412 = 0; index$sample12$412 < weightings.length; index$sample12$412 += 1) {
														int distributionTempVariable$v1$414 = index$sample12$412;
														double cv$probabilitySample12Value413 = (1.0 * distribution$sample12[index$sample12$412]);
														if(fixedFlag$sample26) {
															for(int i = 0; i < size; i += 1) {
																if(((i + 1) == j)) {
																	for(int index$i$435_1 = 0; index$i$435_1 < size; index$i$435_1 += 1) {
																		if(((index$i$435_1 + 1) == j)) {
																			{
																				{
																					double cv$temp$94$var36;
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$414) + v2[j]) + v2[j]) / traceTempVariable$var35$6_1);
																						cv$temp$94$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample12Value413) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value413) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value413) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value413) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value413) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value413);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															for(int i = 0; i < size; i += 1) {
																if(true) {
																	for(int index$sample26$424 = 0; index$sample26$424 < weightings.length; index$sample26$424 += 1) {
																		int distributionTempVariable$var23$426 = index$sample26$424;
																		double cv$probabilitySample26Value425 = (cv$probabilitySample12Value413 * distribution$sample26[((i - 0) / 1)][index$sample26$424]);
																		int traceTempVariable$var31$427_1 = distributionTempVariable$var23$426;
																		if(((i + 1) == j)) {
																			int traceTempVariable$var33$436_1 = distributionTempVariable$var23$426;
																			if(((i + 1) == j)) {
																				{
																					{
																						double cv$temp$95$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$414) + traceTempVariable$var31$427_1) + traceTempVariable$var33$436_1) / traceTempVariable$var35$6_1);
																							cv$temp$95$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value425) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value425) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value425) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value425) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value425) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value425);
																					}
																				}
																			}
																			for(int index$i$437 = 0; index$i$437 < size; index$i$437 += 1) {
																				if(!(index$i$437 == i)) {
																					for(int index$sample26$438 = 0; index$sample26$438 < weightings.length; index$sample26$438 += 1) {
																						int distributionTempVariable$var23$440 = index$sample26$438;
																						double cv$probabilitySample26Value439 = (cv$probabilitySample26Value425 * distribution$sample26[((index$i$437 - 0) / 1)][index$sample26$438]);
																						int traceTempVariable$var33$441_1 = distributionTempVariable$var23$440;
																						if(((index$i$437 + 1) == j)) {
																							{
																								{
																									double cv$temp$96$var36;
																									{
																										double var36 = ((((1.0 * distributionTempVariable$v1$414) + traceTempVariable$var31$427_1) + traceTempVariable$var33$441_1) / traceTempVariable$var35$6_1);
																										cv$temp$96$var36 = var36;
																									}
																									if(((Math.log(cv$probabilitySample26Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var36)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var36));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var36)));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value439);
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
		double[] cv$localProbability = distribution$sample18;
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

	private final void sample26(int i, int threadID$cv$i, Rng RNG$) {
		int cv$noStates = 0;
		int index$i$1 = i;
		{
			cv$noStates = Math.max(cv$noStates, weightings.length);
		}
		double[] cv$stateProbabilityLocal = cv$var23$stateProbabilityGlobal[threadID$cv$i];
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			int index$i$2 = i;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$weightings;
				{
					cv$temp$0$weightings = weightings;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$weightings.length))?Math.log(cv$temp$0$weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						boolean[] guard$sample26bernoulli40 = guard$sample26bernoulli40$global[threadID$cv$i];
						for(int j = 0; j < size; j += 1) {
							if(((i + 1) == j))
								guard$sample26bernoulli40[((j - 0) / 1)] = false;
						}
						for(int j = 0; j < size; j += 1) {
							if(((i + 1) == j))
								guard$sample26bernoulli40[((j - 0) / 1)] = false;
						}
						for(int j = 0; j < size; j += 1) {
							if(((i + 1) == j))
								guard$sample26bernoulli40[((j - 0) / 1)] = false;
						}
						int traceTempVariable$var31$6_1 = cv$currentValue;
						for(int j = 0; j < size; j += 1) {
							if(((i + 1) == j)) {
								if(!guard$sample26bernoulli40[((j - 0) / 1)]) {
									guard$sample26bernoulli40[((j - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											if(fixedFlag$sample12) {
												if(fixedFlag$sample18) {
													if((0 == j)) {
														if((0 == j)) {
															{
																{
																	double cv$temp$1$var36;
																	{
																		double var36 = ((((1.0 * v1) + traceTempVariable$var31$6_1) + v2[j]) / v2[j]);
																		cv$temp$1$var36 = var36;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var36)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var36));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var36)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												} else {
													if(true) {
														for(int index$sample18$18 = 0; index$sample18$18 < weightings.length; index$sample18$18 += 1) {
															int distributionTempVariable$var15$20 = index$sample18$18;
															double cv$probabilitySample18Value19 = (1.0 * distribution$sample18[index$sample18$18]);
															int traceTempVariable$var33$21_1 = distributionTempVariable$var15$20;
															if((0 == j)) {
																int traceTempVariable$var35$28_1 = distributionTempVariable$var15$20;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$2$var36;
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$6_1) + traceTempVariable$var33$21_1) / traceTempVariable$var35$28_1);
																				cv$temp$2$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample18Value19) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value19) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value19) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value19) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value19) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value19);
																		}
																	}
																}
																if(!true) {
																	for(int index$sample18$29 = 0; index$sample18$29 < weightings.length; index$sample18$29 += 1) {
																		int distributionTempVariable$var15$31 = index$sample18$29;
																		double cv$probabilitySample18Value30 = (cv$probabilitySample18Value19 * distribution$sample18[index$sample18$29]);
																		int traceTempVariable$var35$32_1 = distributionTempVariable$var15$31;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$3$var36;
																					{
																						double var36 = ((((1.0 * v1) + traceTempVariable$var31$6_1) + traceTempVariable$var33$21_1) / traceTempVariable$var35$32_1);
																						cv$temp$3$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample18Value30) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value30) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value30) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value30) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value30) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value30);
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
												if(true) {
													for(int index$sample12$13 = 0; index$sample12$13 < weightings.length; index$sample12$13 += 1) {
														int distributionTempVariable$v1$15 = index$sample12$13;
														double cv$probabilitySample12Value14 = (1.0 * distribution$sample12[index$sample12$13]);
														if(fixedFlag$sample18) {
															if((0 == j)) {
																if((0 == j)) {
																	{
																		{
																			double cv$temp$4$var36;
																			{
																				double var36 = ((((1.0 * distributionTempVariable$v1$15) + traceTempVariable$var31$6_1) + v2[j]) / v2[j]);
																				cv$temp$4$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample12Value14) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value14) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value14) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value14) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value14) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value14);
																		}
																	}
																}
															}
														} else {
															if(true) {
																for(int index$sample18$23 = 0; index$sample18$23 < weightings.length; index$sample18$23 += 1) {
																	int distributionTempVariable$var15$25 = index$sample18$23;
																	double cv$probabilitySample18Value24 = (cv$probabilitySample12Value14 * distribution$sample18[index$sample18$23]);
																	int traceTempVariable$var33$26_1 = distributionTempVariable$var15$25;
																	if((0 == j)) {
																		int traceTempVariable$var35$34_1 = distributionTempVariable$var15$25;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$5$var36;
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$15) + traceTempVariable$var31$6_1) + traceTempVariable$var33$26_1) / traceTempVariable$var35$34_1);
																						cv$temp$5$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample18Value24) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value24) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value24) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value24) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value24) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value24);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample18$35 = 0; index$sample18$35 < weightings.length; index$sample18$35 += 1) {
																				int distributionTempVariable$var15$37 = index$sample18$35;
																				double cv$probabilitySample18Value36 = (cv$probabilitySample18Value24 * distribution$sample18[index$sample18$35]);
																				int traceTempVariable$var35$38_1 = distributionTempVariable$var15$37;
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$6$var36;
																							{
																								double var36 = ((((1.0 * distributionTempVariable$v1$15) + traceTempVariable$var31$6_1) + traceTempVariable$var33$26_1) / traceTempVariable$var35$38_1);
																								cv$temp$6$var36 = var36;
																							}
																							if(((Math.log(cv$probabilitySample18Value36) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var36)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value36) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value36) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var36));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value36) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value36) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var36)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value36);
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
											if(fixedFlag$sample12) {
												int traceTempVariable$var33$44_1 = cv$currentValue;
												if(((index$i$2 + 1) == j)) {
													if(fixedFlag$sample18) {
														if((0 == j)) {
															{
																{
																	double cv$temp$7$var36;
																	{
																		double var36 = ((((1.0 * v1) + traceTempVariable$var31$6_1) + traceTempVariable$var33$44_1) / v2[j]);
																		cv$temp$7$var36 = var36;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var36)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var36));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var36)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample18$57 = 0; index$sample18$57 < weightings.length; index$sample18$57 += 1) {
																int distributionTempVariable$var15$59 = index$sample18$57;
																double cv$probabilitySample18Value58 = (1.0 * distribution$sample18[index$sample18$57]);
																int traceTempVariable$var35$60_1 = distributionTempVariable$var15$59;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$8$var36;
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$6_1) + traceTempVariable$var33$44_1) / traceTempVariable$var35$60_1);
																				cv$temp$8$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample18Value58) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value58) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value58) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value58) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value58) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value58);
																		}
																	}
																}
															}
														}
													}
												}
												for(int index$i$45 = 0; index$i$45 < size; index$i$45 += 1) {
													if(!(index$i$45 == index$i$2)) {
														for(int index$sample26$46 = 0; index$sample26$46 < weightings.length; index$sample26$46 += 1) {
															int distributionTempVariable$var23$48 = index$sample26$46;
															double cv$probabilitySample26Value47 = (1.0 * distribution$sample26[((index$i$45 - 0) / 1)][index$sample26$46]);
															int traceTempVariable$var33$49_1 = distributionTempVariable$var23$48;
															if(((index$i$45 + 1) == j)) {
																if(fixedFlag$sample18) {
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$9$var36;
																				{
																					double var36 = ((((1.0 * v1) + traceTempVariable$var31$6_1) + traceTempVariable$var33$49_1) / v2[j]);
																					cv$temp$9$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample26Value47) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value47) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value47) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value47) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value47) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value47);
																			}
																		}
																	}
																} else {
																	if(true) {
																		for(int index$sample18$62 = 0; index$sample18$62 < weightings.length; index$sample18$62 += 1) {
																			int distributionTempVariable$var15$64 = index$sample18$62;
																			double cv$probabilitySample18Value63 = (cv$probabilitySample26Value47 * distribution$sample18[index$sample18$62]);
																			int traceTempVariable$var35$65_1 = distributionTempVariable$var15$64;
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$10$var36;
																						{
																							double var36 = ((((1.0 * v1) + traceTempVariable$var31$6_1) + traceTempVariable$var33$49_1) / traceTempVariable$var35$65_1);
																							cv$temp$10$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample18Value63) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value63) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value63) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value63) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value63) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value63);
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
												if(true) {
													for(int index$sample12$40 = 0; index$sample12$40 < weightings.length; index$sample12$40 += 1) {
														int distributionTempVariable$v1$42 = index$sample12$40;
														double cv$probabilitySample12Value41 = (1.0 * distribution$sample12[index$sample12$40]);
														int traceTempVariable$var33$50_1 = cv$currentValue;
														if(((index$i$2 + 1) == j)) {
															if(fixedFlag$sample18) {
																if((0 == j)) {
																	{
																		{
																			double cv$temp$11$var36;
																			{
																				double var36 = ((((1.0 * distributionTempVariable$v1$42) + traceTempVariable$var31$6_1) + traceTempVariable$var33$50_1) / v2[j]);
																				cv$temp$11$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample12Value41) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value41) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value41) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value41) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value41) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value41);
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample18$67 = 0; index$sample18$67 < weightings.length; index$sample18$67 += 1) {
																		int distributionTempVariable$var15$69 = index$sample18$67;
																		double cv$probabilitySample18Value68 = (cv$probabilitySample12Value41 * distribution$sample18[index$sample18$67]);
																		int traceTempVariable$var35$70_1 = distributionTempVariable$var15$69;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$12$var36;
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$42) + traceTempVariable$var31$6_1) + traceTempVariable$var33$50_1) / traceTempVariable$var35$70_1);
																						cv$temp$12$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample18Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value68);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int index$i$51 = 0; index$i$51 < size; index$i$51 += 1) {
															if(!(index$i$51 == index$i$2)) {
																for(int index$sample26$52 = 0; index$sample26$52 < weightings.length; index$sample26$52 += 1) {
																	int distributionTempVariable$var23$54 = index$sample26$52;
																	double cv$probabilitySample26Value53 = (cv$probabilitySample12Value41 * distribution$sample26[((index$i$51 - 0) / 1)][index$sample26$52]);
																	int traceTempVariable$var33$55_1 = distributionTempVariable$var23$54;
																	if(((index$i$51 + 1) == j)) {
																		if(fixedFlag$sample18) {
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$13$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$42) + traceTempVariable$var31$6_1) + traceTempVariable$var33$55_1) / v2[j]);
																							cv$temp$13$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value53) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value53) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value53) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value53) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value53) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value53);
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample18$72 = 0; index$sample18$72 < weightings.length; index$sample18$72 += 1) {
																					int distributionTempVariable$var15$74 = index$sample18$72;
																					double cv$probabilitySample18Value73 = (cv$probabilitySample26Value53 * distribution$sample18[index$sample18$72]);
																					int traceTempVariable$var35$75_1 = distributionTempVariable$var15$74;
																					if((0 == j)) {
																						{
																							{
																								double cv$temp$14$var36;
																								{
																									double var36 = ((((1.0 * distributionTempVariable$v1$42) + traceTempVariable$var31$6_1) + traceTempVariable$var33$55_1) / traceTempVariable$var35$75_1);
																									cv$temp$14$var36 = var36;
																								}
																								if(((Math.log(cv$probabilitySample18Value73) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var36)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value73) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value73) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var36));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value73) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value73) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var36)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value73);
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
											if(fixedFlag$sample12) {
												if(fixedFlag$sample18) {
													if((0 == j)) {
														int traceTempVariable$var35$91_1 = cv$currentValue;
														if(((index$i$2 + 1) == j)) {
															{
																{
																	double cv$temp$15$var36;
																	{
																		double var36 = ((((1.0 * v1) + traceTempVariable$var31$6_1) + v2[j]) / traceTempVariable$var35$91_1);
																		cv$temp$15$var36 = var36;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var36)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var36));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var36)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
														for(int index$i$92 = 0; index$i$92 < size; index$i$92 += 1) {
															if(!(index$i$92 == index$i$2)) {
																for(int index$sample26$93 = 0; index$sample26$93 < weightings.length; index$sample26$93 += 1) {
																	int distributionTempVariable$var23$95 = index$sample26$93;
																	double cv$probabilitySample26Value94 = (1.0 * distribution$sample26[((index$i$92 - 0) / 1)][index$sample26$93]);
																	int traceTempVariable$var35$96_1 = distributionTempVariable$var23$95;
																	if(((index$i$92 + 1) == j)) {
																		{
																			{
																				double cv$temp$16$var36;
																				{
																					double var36 = ((((1.0 * v1) + traceTempVariable$var31$6_1) + v2[j]) / traceTempVariable$var35$96_1);
																					cv$temp$16$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample26Value94) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value94) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value94) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value94) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value94) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value94);
																			}
																		}
																	}
																}
															}
														}
													}
												} else {
													if(true) {
														for(int index$sample18$82 = 0; index$sample18$82 < weightings.length; index$sample18$82 += 1) {
															int distributionTempVariable$var15$84 = index$sample18$82;
															double cv$probabilitySample18Value83 = (1.0 * distribution$sample18[index$sample18$82]);
															int traceTempVariable$var33$85_1 = distributionTempVariable$var15$84;
															if((0 == j)) {
																int traceTempVariable$var35$97_1 = cv$currentValue;
																if(((index$i$2 + 1) == j)) {
																	{
																		{
																			double cv$temp$17$var36;
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$6_1) + traceTempVariable$var33$85_1) / traceTempVariable$var35$97_1);
																				cv$temp$17$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample18Value83) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value83) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value83) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value83) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value83) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value83);
																		}
																	}
																}
																for(int index$i$98 = 0; index$i$98 < size; index$i$98 += 1) {
																	if(!(index$i$98 == index$i$2)) {
																		for(int index$sample26$99 = 0; index$sample26$99 < weightings.length; index$sample26$99 += 1) {
																			int distributionTempVariable$var23$101 = index$sample26$99;
																			double cv$probabilitySample26Value100 = (cv$probabilitySample18Value83 * distribution$sample26[((index$i$98 - 0) / 1)][index$sample26$99]);
																			int traceTempVariable$var35$102_1 = distributionTempVariable$var23$101;
																			if(((index$i$98 + 1) == j)) {
																				{
																					{
																						double cv$temp$18$var36;
																						{
																							double var36 = ((((1.0 * v1) + traceTempVariable$var31$6_1) + traceTempVariable$var33$85_1) / traceTempVariable$var35$102_1);
																							cv$temp$18$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value100) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value100) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value100) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value100) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value100) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value100);
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
												if(true) {
													for(int index$sample12$77 = 0; index$sample12$77 < weightings.length; index$sample12$77 += 1) {
														int distributionTempVariable$v1$79 = index$sample12$77;
														double cv$probabilitySample12Value78 = (1.0 * distribution$sample12[index$sample12$77]);
														if(fixedFlag$sample18) {
															if((0 == j)) {
																int traceTempVariable$var35$103_1 = cv$currentValue;
																if(((index$i$2 + 1) == j)) {
																	{
																		{
																			double cv$temp$19$var36;
																			{
																				double var36 = ((((1.0 * distributionTempVariable$v1$79) + traceTempVariable$var31$6_1) + v2[j]) / traceTempVariable$var35$103_1);
																				cv$temp$19$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample12Value78) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value78) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value78) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value78) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value78) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value78);
																		}
																	}
																}
																for(int index$i$104 = 0; index$i$104 < size; index$i$104 += 1) {
																	if(!(index$i$104 == index$i$2)) {
																		for(int index$sample26$105 = 0; index$sample26$105 < weightings.length; index$sample26$105 += 1) {
																			int distributionTempVariable$var23$107 = index$sample26$105;
																			double cv$probabilitySample26Value106 = (cv$probabilitySample12Value78 * distribution$sample26[((index$i$104 - 0) / 1)][index$sample26$105]);
																			int traceTempVariable$var35$108_1 = distributionTempVariable$var23$107;
																			if(((index$i$104 + 1) == j)) {
																				{
																					{
																						double cv$temp$20$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$79) + traceTempVariable$var31$6_1) + v2[j]) / traceTempVariable$var35$108_1);
																							cv$temp$20$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value106) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value106) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value106) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value106) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value106) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value106);
																					}
																				}
																			}
																		}
																	}
																}
															}
														} else {
															if(true) {
																for(int index$sample18$87 = 0; index$sample18$87 < weightings.length; index$sample18$87 += 1) {
																	int distributionTempVariable$var15$89 = index$sample18$87;
																	double cv$probabilitySample18Value88 = (cv$probabilitySample12Value78 * distribution$sample18[index$sample18$87]);
																	int traceTempVariable$var33$90_1 = distributionTempVariable$var15$89;
																	if((0 == j)) {
																		int traceTempVariable$var35$109_1 = cv$currentValue;
																		if(((index$i$2 + 1) == j)) {
																			{
																				{
																					double cv$temp$21$var36;
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$79) + traceTempVariable$var31$6_1) + traceTempVariable$var33$90_1) / traceTempVariable$var35$109_1);
																						cv$temp$21$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample18Value88) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value88) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value88) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value88) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value88) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value88);
																				}
																			}
																		}
																		for(int index$i$110 = 0; index$i$110 < size; index$i$110 += 1) {
																			if(!(index$i$110 == index$i$2)) {
																				for(int index$sample26$111 = 0; index$sample26$111 < weightings.length; index$sample26$111 += 1) {
																					int distributionTempVariable$var23$113 = index$sample26$111;
																					double cv$probabilitySample26Value112 = (cv$probabilitySample18Value88 * distribution$sample26[((index$i$110 - 0) / 1)][index$sample26$111]);
																					int traceTempVariable$var35$114_1 = distributionTempVariable$var23$113;
																					if(((index$i$110 + 1) == j)) {
																						{
																							{
																								double cv$temp$22$var36;
																								{
																									double var36 = ((((1.0 * distributionTempVariable$v1$79) + traceTempVariable$var31$6_1) + traceTempVariable$var33$90_1) / traceTempVariable$var35$114_1);
																									cv$temp$22$var36 = var36;
																								}
																								if(((Math.log(cv$probabilitySample26Value112) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var36)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value112) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value112) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var36));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value112) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value112) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var36)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value112);
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
											if(fixedFlag$sample12) {
												int traceTempVariable$var33$120_1 = cv$currentValue;
												if(((index$i$2 + 1) == j)) {
													int traceTempVariable$var35$132_1 = cv$currentValue;
													if(((index$i$2 + 1) == j)) {
														{
															{
																double cv$temp$23$var36;
																{
																	double var36 = ((((1.0 * v1) + traceTempVariable$var31$6_1) + traceTempVariable$var33$120_1) / traceTempVariable$var35$132_1);
																	cv$temp$23$var36 = var36;
																}
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var36)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var36));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var36)));
																}
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
															}
														}
													}
													for(int index$i$133 = 0; index$i$133 < size; index$i$133 += 1) {
														if(!(index$i$133 == index$i$2)) {
															for(int index$sample26$134 = 0; index$sample26$134 < weightings.length; index$sample26$134 += 1) {
																int distributionTempVariable$var23$136 = index$sample26$134;
																double cv$probabilitySample26Value135 = (1.0 * distribution$sample26[((index$i$133 - 0) / 1)][index$sample26$134]);
																int traceTempVariable$var35$137_1 = distributionTempVariable$var23$136;
																if(((index$i$133 + 1) == j)) {
																	{
																		{
																			double cv$temp$24$var36;
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$6_1) + traceTempVariable$var33$120_1) / traceTempVariable$var35$137_1);
																				cv$temp$24$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample26Value135) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value135) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value135) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value135) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value135) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value135);
																		}
																	}
																}
															}
														}
													}
												}
												for(int index$i$121 = 0; index$i$121 < size; index$i$121 += 1) {
													if(!(index$i$121 == index$i$2)) {
														for(int index$sample26$122 = 0; index$sample26$122 < weightings.length; index$sample26$122 += 1) {
															int distributionTempVariable$var23$124 = index$sample26$122;
															double cv$probabilitySample26Value123 = (1.0 * distribution$sample26[((index$i$121 - 0) / 1)][index$sample26$122]);
															int traceTempVariable$var33$125_1 = distributionTempVariable$var23$124;
															if(((index$i$121 + 1) == j)) {
																int traceTempVariable$var35$138_1 = cv$currentValue;
																if(((index$i$2 + 1) == j)) {
																	{
																		{
																			double cv$temp$25$var36;
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$6_1) + traceTempVariable$var33$125_1) / traceTempVariable$var35$138_1);
																				cv$temp$25$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample26Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value123);
																		}
																	}
																}
																int traceTempVariable$var35$139_1 = distributionTempVariable$var23$124;
																if(((index$i$121 + 1) == j)) {
																	{
																		{
																			double cv$temp$26$var36;
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$6_1) + traceTempVariable$var33$125_1) / traceTempVariable$var35$139_1);
																				cv$temp$26$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample26Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value123);
																		}
																	}
																}
																for(int index$i$140 = 0; index$i$140 < size; index$i$140 += 1) {
																	if((!(index$i$140 == index$i$2) && !(index$i$140 == index$i$121))) {
																		for(int index$sample26$141 = 0; index$sample26$141 < weightings.length; index$sample26$141 += 1) {
																			int distributionTempVariable$var23$143 = index$sample26$141;
																			double cv$probabilitySample26Value142 = (cv$probabilitySample26Value123 * distribution$sample26[((index$i$140 - 0) / 1)][index$sample26$141]);
																			int traceTempVariable$var35$144_1 = distributionTempVariable$var23$143;
																			if(((index$i$140 + 1) == j)) {
																				{
																					{
																						double cv$temp$27$var36;
																						{
																							double var36 = ((((1.0 * v1) + traceTempVariable$var31$6_1) + traceTempVariable$var33$125_1) / traceTempVariable$var35$144_1);
																							cv$temp$27$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value142) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value142) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value142) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value142) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value142) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value142);
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
												if(true) {
													for(int index$sample12$116 = 0; index$sample12$116 < weightings.length; index$sample12$116 += 1) {
														int distributionTempVariable$v1$118 = index$sample12$116;
														double cv$probabilitySample12Value117 = (1.0 * distribution$sample12[index$sample12$116]);
														int traceTempVariable$var33$126_1 = cv$currentValue;
														if(((index$i$2 + 1) == j)) {
															int traceTempVariable$var35$145_1 = cv$currentValue;
															if(((index$i$2 + 1) == j)) {
																{
																	{
																		double cv$temp$28$var36;
																		{
																			double var36 = ((((1.0 * distributionTempVariable$v1$118) + traceTempVariable$var31$6_1) + traceTempVariable$var33$126_1) / traceTempVariable$var35$145_1);
																			cv$temp$28$var36 = var36;
																		}
																		if(((Math.log(cv$probabilitySample12Value117) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value117) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value117) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value117) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value117) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value117);
																	}
																}
															}
															for(int index$i$146 = 0; index$i$146 < size; index$i$146 += 1) {
																if(!(index$i$146 == index$i$2)) {
																	for(int index$sample26$147 = 0; index$sample26$147 < weightings.length; index$sample26$147 += 1) {
																		int distributionTempVariable$var23$149 = index$sample26$147;
																		double cv$probabilitySample26Value148 = (cv$probabilitySample12Value117 * distribution$sample26[((index$i$146 - 0) / 1)][index$sample26$147]);
																		int traceTempVariable$var35$150_1 = distributionTempVariable$var23$149;
																		if(((index$i$146 + 1) == j)) {
																			{
																				{
																					double cv$temp$29$var36;
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$118) + traceTempVariable$var31$6_1) + traceTempVariable$var33$126_1) / traceTempVariable$var35$150_1);
																						cv$temp$29$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample26Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value148);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int index$i$127 = 0; index$i$127 < size; index$i$127 += 1) {
															if(!(index$i$127 == index$i$2)) {
																for(int index$sample26$128 = 0; index$sample26$128 < weightings.length; index$sample26$128 += 1) {
																	int distributionTempVariable$var23$130 = index$sample26$128;
																	double cv$probabilitySample26Value129 = (cv$probabilitySample12Value117 * distribution$sample26[((index$i$127 - 0) / 1)][index$sample26$128]);
																	int traceTempVariable$var33$131_1 = distributionTempVariable$var23$130;
																	if(((index$i$127 + 1) == j)) {
																		int traceTempVariable$var35$151_1 = cv$currentValue;
																		if(((index$i$2 + 1) == j)) {
																			{
																				{
																					double cv$temp$30$var36;
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$118) + traceTempVariable$var31$6_1) + traceTempVariable$var33$131_1) / traceTempVariable$var35$151_1);
																						cv$temp$30$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample26Value129) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value129) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value129) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value129) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value129) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value129);
																				}
																			}
																		}
																		int traceTempVariable$var35$152_1 = distributionTempVariable$var23$130;
																		if(((index$i$127 + 1) == j)) {
																			{
																				{
																					double cv$temp$31$var36;
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$118) + traceTempVariable$var31$6_1) + traceTempVariable$var33$131_1) / traceTempVariable$var35$152_1);
																						cv$temp$31$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample26Value129) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value129) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value129) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value129) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value129) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value129);
																				}
																			}
																		}
																		for(int index$i$153 = 0; index$i$153 < size; index$i$153 += 1) {
																			if((!(index$i$153 == index$i$2) && !(index$i$153 == index$i$127))) {
																				for(int index$sample26$154 = 0; index$sample26$154 < weightings.length; index$sample26$154 += 1) {
																					int distributionTempVariable$var23$156 = index$sample26$154;
																					double cv$probabilitySample26Value155 = (cv$probabilitySample26Value129 * distribution$sample26[((index$i$153 - 0) / 1)][index$sample26$154]);
																					int traceTempVariable$var35$157_1 = distributionTempVariable$var23$156;
																					if(((index$i$153 + 1) == j)) {
																						{
																							{
																								double cv$temp$32$var36;
																								{
																									double var36 = ((((1.0 * distributionTempVariable$v1$118) + traceTempVariable$var31$6_1) + traceTempVariable$var33$131_1) / traceTempVariable$var35$157_1);
																									cv$temp$32$var36 = var36;
																								}
																								if(((Math.log(cv$probabilitySample26Value155) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var36)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value155) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value155) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var36));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value155) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value155) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var36)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value155);
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
						int traceTempVariable$var33$7_1 = cv$currentValue;
						for(int j = 0; j < size; j += 1) {
							if(((i + 1) == j)) {
								if(!guard$sample26bernoulli40[((j - 0) / 1)]) {
									guard$sample26bernoulli40[((j - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											if(fixedFlag$sample12) {
												if(fixedFlag$sample18) {
													if((0 == j)) {
														if((0 == j)) {
															{
																{
																	double cv$temp$33$var36;
																	{
																		double var36 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var33$7_1) / v2[j]);
																		cv$temp$33$var36 = var36;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var36)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var36));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var36)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												} else {
													if(true) {
														for(int index$sample18$164 = 0; index$sample18$164 < weightings.length; index$sample18$164 += 1) {
															int distributionTempVariable$var15$166 = index$sample18$164;
															double cv$probabilitySample18Value165 = (1.0 * distribution$sample18[index$sample18$164]);
															int traceTempVariable$var31$167_1 = distributionTempVariable$var15$166;
															if((0 == j)) {
																int traceTempVariable$var35$174_1 = distributionTempVariable$var15$166;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$34$var36;
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$167_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$174_1);
																				cv$temp$34$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample18Value165) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value165) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value165) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value165) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value165) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value165);
																		}
																	}
																}
																if(!true) {
																	for(int index$sample18$175 = 0; index$sample18$175 < weightings.length; index$sample18$175 += 1) {
																		int distributionTempVariable$var15$177 = index$sample18$175;
																		double cv$probabilitySample18Value176 = (cv$probabilitySample18Value165 * distribution$sample18[index$sample18$175]);
																		int traceTempVariable$var35$178_1 = distributionTempVariable$var15$177;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$35$var36;
																					{
																						double var36 = ((((1.0 * v1) + traceTempVariable$var31$167_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$178_1);
																						cv$temp$35$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample18Value176) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value176) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value176) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value176) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value176) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value176);
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
												if(true) {
													for(int index$sample12$159 = 0; index$sample12$159 < weightings.length; index$sample12$159 += 1) {
														int distributionTempVariable$v1$161 = index$sample12$159;
														double cv$probabilitySample12Value160 = (1.0 * distribution$sample12[index$sample12$159]);
														if(fixedFlag$sample18) {
															if((0 == j)) {
																if((0 == j)) {
																	{
																		{
																			double cv$temp$36$var36;
																			{
																				double var36 = ((((1.0 * distributionTempVariable$v1$161) + v2[j]) + traceTempVariable$var33$7_1) / v2[j]);
																				cv$temp$36$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample12Value160) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value160) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value160) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value160) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value160) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value160);
																		}
																	}
																}
															}
														} else {
															if(true) {
																for(int index$sample18$169 = 0; index$sample18$169 < weightings.length; index$sample18$169 += 1) {
																	int distributionTempVariable$var15$171 = index$sample18$169;
																	double cv$probabilitySample18Value170 = (cv$probabilitySample12Value160 * distribution$sample18[index$sample18$169]);
																	int traceTempVariable$var31$172_1 = distributionTempVariable$var15$171;
																	if((0 == j)) {
																		int traceTempVariable$var35$180_1 = distributionTempVariable$var15$171;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$37$var36;
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$161) + traceTempVariable$var31$172_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$180_1);
																						cv$temp$37$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample18Value170) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value170) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value170) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value170) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value170) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value170);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample18$181 = 0; index$sample18$181 < weightings.length; index$sample18$181 += 1) {
																				int distributionTempVariable$var15$183 = index$sample18$181;
																				double cv$probabilitySample18Value182 = (cv$probabilitySample18Value170 * distribution$sample18[index$sample18$181]);
																				int traceTempVariable$var35$184_1 = distributionTempVariable$var15$183;
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$38$var36;
																							{
																								double var36 = ((((1.0 * distributionTempVariable$v1$161) + traceTempVariable$var31$172_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$184_1);
																								cv$temp$38$var36 = var36;
																							}
																							if(((Math.log(cv$probabilitySample18Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var36)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var36));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var36)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value182);
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
											if(fixedFlag$sample12) {
												int traceTempVariable$var31$190_1 = cv$currentValue;
												if(((index$i$2 + 1) == j)) {
													if(fixedFlag$sample18) {
														if((0 == j)) {
															{
																{
																	double cv$temp$39$var36;
																	{
																		double var36 = ((((1.0 * v1) + traceTempVariable$var31$190_1) + traceTempVariable$var33$7_1) / v2[j]);
																		cv$temp$39$var36 = var36;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var36)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var36));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var36)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample18$203 = 0; index$sample18$203 < weightings.length; index$sample18$203 += 1) {
																int distributionTempVariable$var15$205 = index$sample18$203;
																double cv$probabilitySample18Value204 = (1.0 * distribution$sample18[index$sample18$203]);
																int traceTempVariable$var35$206_1 = distributionTempVariable$var15$205;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$40$var36;
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$190_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$206_1);
																				cv$temp$40$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample18Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value204);
																		}
																	}
																}
															}
														}
													}
												}
												for(int index$i$191 = 0; index$i$191 < size; index$i$191 += 1) {
													if(!(index$i$191 == index$i$2)) {
														for(int index$sample26$192 = 0; index$sample26$192 < weightings.length; index$sample26$192 += 1) {
															int distributionTempVariable$var23$194 = index$sample26$192;
															double cv$probabilitySample26Value193 = (1.0 * distribution$sample26[((index$i$191 - 0) / 1)][index$sample26$192]);
															int traceTempVariable$var31$195_1 = distributionTempVariable$var23$194;
															if(((index$i$191 + 1) == j)) {
																if(fixedFlag$sample18) {
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$41$var36;
																				{
																					double var36 = ((((1.0 * v1) + traceTempVariable$var31$195_1) + traceTempVariable$var33$7_1) / v2[j]);
																					cv$temp$41$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample26Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value193);
																			}
																		}
																	}
																} else {
																	if(true) {
																		for(int index$sample18$208 = 0; index$sample18$208 < weightings.length; index$sample18$208 += 1) {
																			int distributionTempVariable$var15$210 = index$sample18$208;
																			double cv$probabilitySample18Value209 = (cv$probabilitySample26Value193 * distribution$sample18[index$sample18$208]);
																			int traceTempVariable$var35$211_1 = distributionTempVariable$var15$210;
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$42$var36;
																						{
																							double var36 = ((((1.0 * v1) + traceTempVariable$var31$195_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$211_1);
																							cv$temp$42$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample18Value209) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value209) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value209) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value209) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value209) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value209);
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
												if(true) {
													for(int index$sample12$186 = 0; index$sample12$186 < weightings.length; index$sample12$186 += 1) {
														int distributionTempVariable$v1$188 = index$sample12$186;
														double cv$probabilitySample12Value187 = (1.0 * distribution$sample12[index$sample12$186]);
														int traceTempVariable$var31$196_1 = cv$currentValue;
														if(((index$i$2 + 1) == j)) {
															if(fixedFlag$sample18) {
																if((0 == j)) {
																	{
																		{
																			double cv$temp$43$var36;
																			{
																				double var36 = ((((1.0 * distributionTempVariable$v1$188) + traceTempVariable$var31$196_1) + traceTempVariable$var33$7_1) / v2[j]);
																				cv$temp$43$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample12Value187) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value187) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value187) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value187) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value187) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value187);
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample18$213 = 0; index$sample18$213 < weightings.length; index$sample18$213 += 1) {
																		int distributionTempVariable$var15$215 = index$sample18$213;
																		double cv$probabilitySample18Value214 = (cv$probabilitySample12Value187 * distribution$sample18[index$sample18$213]);
																		int traceTempVariable$var35$216_1 = distributionTempVariable$var15$215;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$44$var36;
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$188) + traceTempVariable$var31$196_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$216_1);
																						cv$temp$44$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample18Value214) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value214) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value214) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value214) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value214) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value214);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int index$i$197 = 0; index$i$197 < size; index$i$197 += 1) {
															if(!(index$i$197 == index$i$2)) {
																for(int index$sample26$198 = 0; index$sample26$198 < weightings.length; index$sample26$198 += 1) {
																	int distributionTempVariable$var23$200 = index$sample26$198;
																	double cv$probabilitySample26Value199 = (cv$probabilitySample12Value187 * distribution$sample26[((index$i$197 - 0) / 1)][index$sample26$198]);
																	int traceTempVariable$var31$201_1 = distributionTempVariable$var23$200;
																	if(((index$i$197 + 1) == j)) {
																		if(fixedFlag$sample18) {
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$45$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$188) + traceTempVariable$var31$201_1) + traceTempVariable$var33$7_1) / v2[j]);
																							cv$temp$45$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value199) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value199) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value199) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value199) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value199) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value199);
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample18$218 = 0; index$sample18$218 < weightings.length; index$sample18$218 += 1) {
																					int distributionTempVariable$var15$220 = index$sample18$218;
																					double cv$probabilitySample18Value219 = (cv$probabilitySample26Value199 * distribution$sample18[index$sample18$218]);
																					int traceTempVariable$var35$221_1 = distributionTempVariable$var15$220;
																					if((0 == j)) {
																						{
																							{
																								double cv$temp$46$var36;
																								{
																									double var36 = ((((1.0 * distributionTempVariable$v1$188) + traceTempVariable$var31$201_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$221_1);
																									cv$temp$46$var36 = var36;
																								}
																								if(((Math.log(cv$probabilitySample18Value219) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var36)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value219) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value219) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var36));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value219) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value219) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var36)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value219);
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
											if(fixedFlag$sample12) {
												if(fixedFlag$sample18) {
													if((0 == j)) {
														int traceTempVariable$var35$237_1 = cv$currentValue;
														if(((index$i$2 + 1) == j)) {
															{
																{
																	double cv$temp$47$var36;
																	{
																		double var36 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var33$7_1) / traceTempVariable$var35$237_1);
																		cv$temp$47$var36 = var36;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var36)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var36));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var36)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
														for(int index$i$238 = 0; index$i$238 < size; index$i$238 += 1) {
															if(!(index$i$238 == index$i$2)) {
																for(int index$sample26$239 = 0; index$sample26$239 < weightings.length; index$sample26$239 += 1) {
																	int distributionTempVariable$var23$241 = index$sample26$239;
																	double cv$probabilitySample26Value240 = (1.0 * distribution$sample26[((index$i$238 - 0) / 1)][index$sample26$239]);
																	int traceTempVariable$var35$242_1 = distributionTempVariable$var23$241;
																	if(((index$i$238 + 1) == j)) {
																		{
																			{
																				double cv$temp$48$var36;
																				{
																					double var36 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var33$7_1) / traceTempVariable$var35$242_1);
																					cv$temp$48$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample26Value240) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value240) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value240) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value240) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value240) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value240);
																			}
																		}
																	}
																}
															}
														}
													}
												} else {
													if(true) {
														for(int index$sample18$228 = 0; index$sample18$228 < weightings.length; index$sample18$228 += 1) {
															int distributionTempVariable$var15$230 = index$sample18$228;
															double cv$probabilitySample18Value229 = (1.0 * distribution$sample18[index$sample18$228]);
															int traceTempVariable$var31$231_1 = distributionTempVariable$var15$230;
															if((0 == j)) {
																int traceTempVariable$var35$243_1 = cv$currentValue;
																if(((index$i$2 + 1) == j)) {
																	{
																		{
																			double cv$temp$49$var36;
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$231_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$243_1);
																				cv$temp$49$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample18Value229) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value229) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value229) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value229) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value229) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value229);
																		}
																	}
																}
																for(int index$i$244 = 0; index$i$244 < size; index$i$244 += 1) {
																	if(!(index$i$244 == index$i$2)) {
																		for(int index$sample26$245 = 0; index$sample26$245 < weightings.length; index$sample26$245 += 1) {
																			int distributionTempVariable$var23$247 = index$sample26$245;
																			double cv$probabilitySample26Value246 = (cv$probabilitySample18Value229 * distribution$sample26[((index$i$244 - 0) / 1)][index$sample26$245]);
																			int traceTempVariable$var35$248_1 = distributionTempVariable$var23$247;
																			if(((index$i$244 + 1) == j)) {
																				{
																					{
																						double cv$temp$50$var36;
																						{
																							double var36 = ((((1.0 * v1) + traceTempVariable$var31$231_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$248_1);
																							cv$temp$50$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value246) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value246) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value246) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value246) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value246) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value246);
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
												if(true) {
													for(int index$sample12$223 = 0; index$sample12$223 < weightings.length; index$sample12$223 += 1) {
														int distributionTempVariable$v1$225 = index$sample12$223;
														double cv$probabilitySample12Value224 = (1.0 * distribution$sample12[index$sample12$223]);
														if(fixedFlag$sample18) {
															if((0 == j)) {
																int traceTempVariable$var35$249_1 = cv$currentValue;
																if(((index$i$2 + 1) == j)) {
																	{
																		{
																			double cv$temp$51$var36;
																			{
																				double var36 = ((((1.0 * distributionTempVariable$v1$225) + v2[j]) + traceTempVariable$var33$7_1) / traceTempVariable$var35$249_1);
																				cv$temp$51$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample12Value224) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value224) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value224) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value224) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value224) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value224);
																		}
																	}
																}
																for(int index$i$250 = 0; index$i$250 < size; index$i$250 += 1) {
																	if(!(index$i$250 == index$i$2)) {
																		for(int index$sample26$251 = 0; index$sample26$251 < weightings.length; index$sample26$251 += 1) {
																			int distributionTempVariable$var23$253 = index$sample26$251;
																			double cv$probabilitySample26Value252 = (cv$probabilitySample12Value224 * distribution$sample26[((index$i$250 - 0) / 1)][index$sample26$251]);
																			int traceTempVariable$var35$254_1 = distributionTempVariable$var23$253;
																			if(((index$i$250 + 1) == j)) {
																				{
																					{
																						double cv$temp$52$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$225) + v2[j]) + traceTempVariable$var33$7_1) / traceTempVariable$var35$254_1);
																							cv$temp$52$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value252);
																					}
																				}
																			}
																		}
																	}
																}
															}
														} else {
															if(true) {
																for(int index$sample18$233 = 0; index$sample18$233 < weightings.length; index$sample18$233 += 1) {
																	int distributionTempVariable$var15$235 = index$sample18$233;
																	double cv$probabilitySample18Value234 = (cv$probabilitySample12Value224 * distribution$sample18[index$sample18$233]);
																	int traceTempVariable$var31$236_1 = distributionTempVariable$var15$235;
																	if((0 == j)) {
																		int traceTempVariable$var35$255_1 = cv$currentValue;
																		if(((index$i$2 + 1) == j)) {
																			{
																				{
																					double cv$temp$53$var36;
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$225) + traceTempVariable$var31$236_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$255_1);
																						cv$temp$53$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample18Value234) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value234) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value234) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value234) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value234) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value234);
																				}
																			}
																		}
																		for(int index$i$256 = 0; index$i$256 < size; index$i$256 += 1) {
																			if(!(index$i$256 == index$i$2)) {
																				for(int index$sample26$257 = 0; index$sample26$257 < weightings.length; index$sample26$257 += 1) {
																					int distributionTempVariable$var23$259 = index$sample26$257;
																					double cv$probabilitySample26Value258 = (cv$probabilitySample18Value234 * distribution$sample26[((index$i$256 - 0) / 1)][index$sample26$257]);
																					int traceTempVariable$var35$260_1 = distributionTempVariable$var23$259;
																					if(((index$i$256 + 1) == j)) {
																						{
																							{
																								double cv$temp$54$var36;
																								{
																									double var36 = ((((1.0 * distributionTempVariable$v1$225) + traceTempVariable$var31$236_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$260_1);
																									cv$temp$54$var36 = var36;
																								}
																								if(((Math.log(cv$probabilitySample26Value258) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var36)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value258) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value258) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var36));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value258) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value258) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var36)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value258);
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
											if(fixedFlag$sample12) {
												int traceTempVariable$var31$266_1 = cv$currentValue;
												if(((index$i$2 + 1) == j)) {
													int traceTempVariable$var35$278_1 = cv$currentValue;
													if(((index$i$2 + 1) == j)) {
														{
															{
																double cv$temp$55$var36;
																{
																	double var36 = ((((1.0 * v1) + traceTempVariable$var31$266_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$278_1);
																	cv$temp$55$var36 = var36;
																}
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var36)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var36));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var36)));
																}
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
															}
														}
													}
													for(int index$i$279 = 0; index$i$279 < size; index$i$279 += 1) {
														if(!(index$i$279 == index$i$2)) {
															for(int index$sample26$280 = 0; index$sample26$280 < weightings.length; index$sample26$280 += 1) {
																int distributionTempVariable$var23$282 = index$sample26$280;
																double cv$probabilitySample26Value281 = (1.0 * distribution$sample26[((index$i$279 - 0) / 1)][index$sample26$280]);
																int traceTempVariable$var35$283_1 = distributionTempVariable$var23$282;
																if(((index$i$279 + 1) == j)) {
																	{
																		{
																			double cv$temp$56$var36;
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$266_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$283_1);
																				cv$temp$56$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample26Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value281);
																		}
																	}
																}
															}
														}
													}
												}
												for(int index$i$267 = 0; index$i$267 < size; index$i$267 += 1) {
													if(!(index$i$267 == index$i$2)) {
														for(int index$sample26$268 = 0; index$sample26$268 < weightings.length; index$sample26$268 += 1) {
															int distributionTempVariable$var23$270 = index$sample26$268;
															double cv$probabilitySample26Value269 = (1.0 * distribution$sample26[((index$i$267 - 0) / 1)][index$sample26$268]);
															int traceTempVariable$var31$271_1 = distributionTempVariable$var23$270;
															if(((index$i$267 + 1) == j)) {
																int traceTempVariable$var35$284_1 = cv$currentValue;
																if(((index$i$2 + 1) == j)) {
																	{
																		{
																			double cv$temp$57$var36;
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$271_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$284_1);
																				cv$temp$57$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample26Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value269);
																		}
																	}
																}
																int traceTempVariable$var35$285_1 = distributionTempVariable$var23$270;
																if(((index$i$267 + 1) == j)) {
																	{
																		{
																			double cv$temp$58$var36;
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$271_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$285_1);
																				cv$temp$58$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample26Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value269);
																		}
																	}
																}
																for(int index$i$286 = 0; index$i$286 < size; index$i$286 += 1) {
																	if((!(index$i$286 == index$i$2) && !(index$i$286 == index$i$267))) {
																		for(int index$sample26$287 = 0; index$sample26$287 < weightings.length; index$sample26$287 += 1) {
																			int distributionTempVariable$var23$289 = index$sample26$287;
																			double cv$probabilitySample26Value288 = (cv$probabilitySample26Value269 * distribution$sample26[((index$i$286 - 0) / 1)][index$sample26$287]);
																			int traceTempVariable$var35$290_1 = distributionTempVariable$var23$289;
																			if(((index$i$286 + 1) == j)) {
																				{
																					{
																						double cv$temp$59$var36;
																						{
																							double var36 = ((((1.0 * v1) + traceTempVariable$var31$271_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$290_1);
																							cv$temp$59$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value288);
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
												if(true) {
													for(int index$sample12$262 = 0; index$sample12$262 < weightings.length; index$sample12$262 += 1) {
														int distributionTempVariable$v1$264 = index$sample12$262;
														double cv$probabilitySample12Value263 = (1.0 * distribution$sample12[index$sample12$262]);
														int traceTempVariable$var31$272_1 = cv$currentValue;
														if(((index$i$2 + 1) == j)) {
															int traceTempVariable$var35$291_1 = cv$currentValue;
															if(((index$i$2 + 1) == j)) {
																{
																	{
																		double cv$temp$60$var36;
																		{
																			double var36 = ((((1.0 * distributionTempVariable$v1$264) + traceTempVariable$var31$272_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$291_1);
																			cv$temp$60$var36 = var36;
																		}
																		if(((Math.log(cv$probabilitySample12Value263) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value263) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value263) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value263) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value263) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value263);
																	}
																}
															}
															for(int index$i$292 = 0; index$i$292 < size; index$i$292 += 1) {
																if(!(index$i$292 == index$i$2)) {
																	for(int index$sample26$293 = 0; index$sample26$293 < weightings.length; index$sample26$293 += 1) {
																		int distributionTempVariable$var23$295 = index$sample26$293;
																		double cv$probabilitySample26Value294 = (cv$probabilitySample12Value263 * distribution$sample26[((index$i$292 - 0) / 1)][index$sample26$293]);
																		int traceTempVariable$var35$296_1 = distributionTempVariable$var23$295;
																		if(((index$i$292 + 1) == j)) {
																			{
																				{
																					double cv$temp$61$var36;
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$264) + traceTempVariable$var31$272_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$296_1);
																						cv$temp$61$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample26Value294) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value294) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value294) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value294) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value294) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value294);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int index$i$273 = 0; index$i$273 < size; index$i$273 += 1) {
															if(!(index$i$273 == index$i$2)) {
																for(int index$sample26$274 = 0; index$sample26$274 < weightings.length; index$sample26$274 += 1) {
																	int distributionTempVariable$var23$276 = index$sample26$274;
																	double cv$probabilitySample26Value275 = (cv$probabilitySample12Value263 * distribution$sample26[((index$i$273 - 0) / 1)][index$sample26$274]);
																	int traceTempVariable$var31$277_1 = distributionTempVariable$var23$276;
																	if(((index$i$273 + 1) == j)) {
																		int traceTempVariable$var35$297_1 = cv$currentValue;
																		if(((index$i$2 + 1) == j)) {
																			{
																				{
																					double cv$temp$62$var36;
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$264) + traceTempVariable$var31$277_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$297_1);
																						cv$temp$62$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value275);
																				}
																			}
																		}
																		int traceTempVariable$var35$298_1 = distributionTempVariable$var23$276;
																		if(((index$i$273 + 1) == j)) {
																			{
																				{
																					double cv$temp$63$var36;
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$264) + traceTempVariable$var31$277_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$298_1);
																						cv$temp$63$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value275);
																				}
																			}
																		}
																		for(int index$i$299 = 0; index$i$299 < size; index$i$299 += 1) {
																			if((!(index$i$299 == index$i$2) && !(index$i$299 == index$i$273))) {
																				for(int index$sample26$300 = 0; index$sample26$300 < weightings.length; index$sample26$300 += 1) {
																					int distributionTempVariable$var23$302 = index$sample26$300;
																					double cv$probabilitySample26Value301 = (cv$probabilitySample26Value275 * distribution$sample26[((index$i$299 - 0) / 1)][index$sample26$300]);
																					int traceTempVariable$var35$303_1 = distributionTempVariable$var23$302;
																					if(((index$i$299 + 1) == j)) {
																						{
																							{
																								double cv$temp$64$var36;
																								{
																									double var36 = ((((1.0 * distributionTempVariable$v1$264) + traceTempVariable$var31$277_1) + traceTempVariable$var33$7_1) / traceTempVariable$var35$303_1);
																									cv$temp$64$var36 = var36;
																								}
																								if(((Math.log(cv$probabilitySample26Value301) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var36)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value301) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value301) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var36));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value301) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value301) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var36)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value301);
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
						int traceTempVariable$var35$8_1 = cv$currentValue;
						for(int j = 0; j < size; j += 1) {
							if(((i + 1) == j)) {
								if(!guard$sample26bernoulli40[((j - 0) / 1)]) {
									guard$sample26bernoulli40[((j - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											if(fixedFlag$sample12) {
												if(fixedFlag$sample18) {
													if((0 == j)) {
														if((0 == j)) {
															{
																{
																	double cv$temp$65$var36;
																	{
																		double var36 = ((((1.0 * v1) + v2[j]) + v2[j]) / traceTempVariable$var35$8_1);
																		cv$temp$65$var36 = var36;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var36)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var36));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var36)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												} else {
													if(true) {
														for(int index$sample18$310 = 0; index$sample18$310 < weightings.length; index$sample18$310 += 1) {
															int distributionTempVariable$var15$312 = index$sample18$310;
															double cv$probabilitySample18Value311 = (1.0 * distribution$sample18[index$sample18$310]);
															int traceTempVariable$var31$313_1 = distributionTempVariable$var15$312;
															if((0 == j)) {
																int traceTempVariable$var33$320_1 = distributionTempVariable$var15$312;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$66$var36;
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$313_1) + traceTempVariable$var33$320_1) / traceTempVariable$var35$8_1);
																				cv$temp$66$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample18Value311) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value311) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value311) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value311) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value311) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value311);
																		}
																	}
																}
																if(!true) {
																	for(int index$sample18$321 = 0; index$sample18$321 < weightings.length; index$sample18$321 += 1) {
																		int distributionTempVariable$var15$323 = index$sample18$321;
																		double cv$probabilitySample18Value322 = (cv$probabilitySample18Value311 * distribution$sample18[index$sample18$321]);
																		int traceTempVariable$var33$324_1 = distributionTempVariable$var15$323;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$67$var36;
																					{
																						double var36 = ((((1.0 * v1) + traceTempVariable$var31$313_1) + traceTempVariable$var33$324_1) / traceTempVariable$var35$8_1);
																						cv$temp$67$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample18Value322) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value322) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value322) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value322) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value322) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value322);
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
												if(true) {
													for(int index$sample12$305 = 0; index$sample12$305 < weightings.length; index$sample12$305 += 1) {
														int distributionTempVariable$v1$307 = index$sample12$305;
														double cv$probabilitySample12Value306 = (1.0 * distribution$sample12[index$sample12$305]);
														if(fixedFlag$sample18) {
															if((0 == j)) {
																if((0 == j)) {
																	{
																		{
																			double cv$temp$68$var36;
																			{
																				double var36 = ((((1.0 * distributionTempVariable$v1$307) + v2[j]) + v2[j]) / traceTempVariable$var35$8_1);
																				cv$temp$68$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample12Value306) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value306) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value306) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value306) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value306) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value306);
																		}
																	}
																}
															}
														} else {
															if(true) {
																for(int index$sample18$315 = 0; index$sample18$315 < weightings.length; index$sample18$315 += 1) {
																	int distributionTempVariable$var15$317 = index$sample18$315;
																	double cv$probabilitySample18Value316 = (cv$probabilitySample12Value306 * distribution$sample18[index$sample18$315]);
																	int traceTempVariable$var31$318_1 = distributionTempVariable$var15$317;
																	if((0 == j)) {
																		int traceTempVariable$var33$326_1 = distributionTempVariable$var15$317;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$69$var36;
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$307) + traceTempVariable$var31$318_1) + traceTempVariable$var33$326_1) / traceTempVariable$var35$8_1);
																						cv$temp$69$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample18Value316) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value316) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value316) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value316) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value316) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value316);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample18$327 = 0; index$sample18$327 < weightings.length; index$sample18$327 += 1) {
																				int distributionTempVariable$var15$329 = index$sample18$327;
																				double cv$probabilitySample18Value328 = (cv$probabilitySample18Value316 * distribution$sample18[index$sample18$327]);
																				int traceTempVariable$var33$330_1 = distributionTempVariable$var15$329;
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$70$var36;
																							{
																								double var36 = ((((1.0 * distributionTempVariable$v1$307) + traceTempVariable$var31$318_1) + traceTempVariable$var33$330_1) / traceTempVariable$var35$8_1);
																								cv$temp$70$var36 = var36;
																							}
																							if(((Math.log(cv$probabilitySample18Value328) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var36)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value328) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value328) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var36));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value328) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value328) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var36)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value328);
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
											if(fixedFlag$sample12) {
												int traceTempVariable$var31$336_1 = cv$currentValue;
												if(((index$i$2 + 1) == j)) {
													if(fixedFlag$sample18) {
														if((0 == j)) {
															{
																{
																	double cv$temp$71$var36;
																	{
																		double var36 = ((((1.0 * v1) + traceTempVariable$var31$336_1) + v2[j]) / traceTempVariable$var35$8_1);
																		cv$temp$71$var36 = var36;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var36)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var36));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var36)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample18$349 = 0; index$sample18$349 < weightings.length; index$sample18$349 += 1) {
																int distributionTempVariable$var15$351 = index$sample18$349;
																double cv$probabilitySample18Value350 = (1.0 * distribution$sample18[index$sample18$349]);
																int traceTempVariable$var33$352_1 = distributionTempVariable$var15$351;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$72$var36;
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$336_1) + traceTempVariable$var33$352_1) / traceTempVariable$var35$8_1);
																				cv$temp$72$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample18Value350) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value350) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value350) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value350) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value350) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value350);
																		}
																	}
																}
															}
														}
													}
												}
												for(int index$i$337 = 0; index$i$337 < size; index$i$337 += 1) {
													if(!(index$i$337 == index$i$2)) {
														for(int index$sample26$338 = 0; index$sample26$338 < weightings.length; index$sample26$338 += 1) {
															int distributionTempVariable$var23$340 = index$sample26$338;
															double cv$probabilitySample26Value339 = (1.0 * distribution$sample26[((index$i$337 - 0) / 1)][index$sample26$338]);
															int traceTempVariable$var31$341_1 = distributionTempVariable$var23$340;
															if(((index$i$337 + 1) == j)) {
																if(fixedFlag$sample18) {
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$73$var36;
																				{
																					double var36 = ((((1.0 * v1) + traceTempVariable$var31$341_1) + v2[j]) / traceTempVariable$var35$8_1);
																					cv$temp$73$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample26Value339) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value339) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value339) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value339) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value339) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value339);
																			}
																		}
																	}
																} else {
																	if(true) {
																		for(int index$sample18$354 = 0; index$sample18$354 < weightings.length; index$sample18$354 += 1) {
																			int distributionTempVariable$var15$356 = index$sample18$354;
																			double cv$probabilitySample18Value355 = (cv$probabilitySample26Value339 * distribution$sample18[index$sample18$354]);
																			int traceTempVariable$var33$357_1 = distributionTempVariable$var15$356;
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$74$var36;
																						{
																							double var36 = ((((1.0 * v1) + traceTempVariable$var31$341_1) + traceTempVariable$var33$357_1) / traceTempVariable$var35$8_1);
																							cv$temp$74$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample18Value355) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value355) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value355) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value355) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value355) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value355);
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
												if(true) {
													for(int index$sample12$332 = 0; index$sample12$332 < weightings.length; index$sample12$332 += 1) {
														int distributionTempVariable$v1$334 = index$sample12$332;
														double cv$probabilitySample12Value333 = (1.0 * distribution$sample12[index$sample12$332]);
														int traceTempVariable$var31$342_1 = cv$currentValue;
														if(((index$i$2 + 1) == j)) {
															if(fixedFlag$sample18) {
																if((0 == j)) {
																	{
																		{
																			double cv$temp$75$var36;
																			{
																				double var36 = ((((1.0 * distributionTempVariable$v1$334) + traceTempVariable$var31$342_1) + v2[j]) / traceTempVariable$var35$8_1);
																				cv$temp$75$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample12Value333) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value333) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value333) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value333) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value333) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value333);
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample18$359 = 0; index$sample18$359 < weightings.length; index$sample18$359 += 1) {
																		int distributionTempVariable$var15$361 = index$sample18$359;
																		double cv$probabilitySample18Value360 = (cv$probabilitySample12Value333 * distribution$sample18[index$sample18$359]);
																		int traceTempVariable$var33$362_1 = distributionTempVariable$var15$361;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$76$var36;
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$334) + traceTempVariable$var31$342_1) + traceTempVariable$var33$362_1) / traceTempVariable$var35$8_1);
																						cv$temp$76$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample18Value360) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value360) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value360) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value360) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value360) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value360);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int index$i$343 = 0; index$i$343 < size; index$i$343 += 1) {
															if(!(index$i$343 == index$i$2)) {
																for(int index$sample26$344 = 0; index$sample26$344 < weightings.length; index$sample26$344 += 1) {
																	int distributionTempVariable$var23$346 = index$sample26$344;
																	double cv$probabilitySample26Value345 = (cv$probabilitySample12Value333 * distribution$sample26[((index$i$343 - 0) / 1)][index$sample26$344]);
																	int traceTempVariable$var31$347_1 = distributionTempVariable$var23$346;
																	if(((index$i$343 + 1) == j)) {
																		if(fixedFlag$sample18) {
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$77$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$334) + traceTempVariable$var31$347_1) + v2[j]) / traceTempVariable$var35$8_1);
																							cv$temp$77$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value345) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value345) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value345) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value345) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value345) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value345);
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample18$364 = 0; index$sample18$364 < weightings.length; index$sample18$364 += 1) {
																					int distributionTempVariable$var15$366 = index$sample18$364;
																					double cv$probabilitySample18Value365 = (cv$probabilitySample26Value345 * distribution$sample18[index$sample18$364]);
																					int traceTempVariable$var33$367_1 = distributionTempVariable$var15$366;
																					if((0 == j)) {
																						{
																							{
																								double cv$temp$78$var36;
																								{
																									double var36 = ((((1.0 * distributionTempVariable$v1$334) + traceTempVariable$var31$347_1) + traceTempVariable$var33$367_1) / traceTempVariable$var35$8_1);
																									cv$temp$78$var36 = var36;
																								}
																								if(((Math.log(cv$probabilitySample18Value365) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var36)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value365) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value365) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var36));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value365) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value365) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var36)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value365);
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
											if(fixedFlag$sample12) {
												if(fixedFlag$sample18) {
													if((0 == j)) {
														int traceTempVariable$var33$383_1 = cv$currentValue;
														if(((index$i$2 + 1) == j)) {
															{
																{
																	double cv$temp$79$var36;
																	{
																		double var36 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var33$383_1) / traceTempVariable$var35$8_1);
																		cv$temp$79$var36 = var36;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var36)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var36));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var36)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
														for(int index$i$384 = 0; index$i$384 < size; index$i$384 += 1) {
															if(!(index$i$384 == index$i$2)) {
																for(int index$sample26$385 = 0; index$sample26$385 < weightings.length; index$sample26$385 += 1) {
																	int distributionTempVariable$var23$387 = index$sample26$385;
																	double cv$probabilitySample26Value386 = (1.0 * distribution$sample26[((index$i$384 - 0) / 1)][index$sample26$385]);
																	int traceTempVariable$var33$388_1 = distributionTempVariable$var23$387;
																	if(((index$i$384 + 1) == j)) {
																		{
																			{
																				double cv$temp$80$var36;
																				{
																					double var36 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var33$388_1) / traceTempVariable$var35$8_1);
																					cv$temp$80$var36 = var36;
																				}
																				if(((Math.log(cv$probabilitySample26Value386) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var36)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value386) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value386) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var36));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value386) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value386) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var36)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value386);
																			}
																		}
																	}
																}
															}
														}
													}
												} else {
													if(true) {
														for(int index$sample18$374 = 0; index$sample18$374 < weightings.length; index$sample18$374 += 1) {
															int distributionTempVariable$var15$376 = index$sample18$374;
															double cv$probabilitySample18Value375 = (1.0 * distribution$sample18[index$sample18$374]);
															int traceTempVariable$var31$377_1 = distributionTempVariable$var15$376;
															if((0 == j)) {
																int traceTempVariable$var33$389_1 = cv$currentValue;
																if(((index$i$2 + 1) == j)) {
																	{
																		{
																			double cv$temp$81$var36;
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$377_1) + traceTempVariable$var33$389_1) / traceTempVariable$var35$8_1);
																				cv$temp$81$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample18Value375) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value375) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value375) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value375) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value375) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value375);
																		}
																	}
																}
																for(int index$i$390 = 0; index$i$390 < size; index$i$390 += 1) {
																	if(!(index$i$390 == index$i$2)) {
																		for(int index$sample26$391 = 0; index$sample26$391 < weightings.length; index$sample26$391 += 1) {
																			int distributionTempVariable$var23$393 = index$sample26$391;
																			double cv$probabilitySample26Value392 = (cv$probabilitySample18Value375 * distribution$sample26[((index$i$390 - 0) / 1)][index$sample26$391]);
																			int traceTempVariable$var33$394_1 = distributionTempVariable$var23$393;
																			if(((index$i$390 + 1) == j)) {
																				{
																					{
																						double cv$temp$82$var36;
																						{
																							double var36 = ((((1.0 * v1) + traceTempVariable$var31$377_1) + traceTempVariable$var33$394_1) / traceTempVariable$var35$8_1);
																							cv$temp$82$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value392) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value392) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value392) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value392) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value392) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value392);
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
												if(true) {
													for(int index$sample12$369 = 0; index$sample12$369 < weightings.length; index$sample12$369 += 1) {
														int distributionTempVariable$v1$371 = index$sample12$369;
														double cv$probabilitySample12Value370 = (1.0 * distribution$sample12[index$sample12$369]);
														if(fixedFlag$sample18) {
															if((0 == j)) {
																int traceTempVariable$var33$395_1 = cv$currentValue;
																if(((index$i$2 + 1) == j)) {
																	{
																		{
																			double cv$temp$83$var36;
																			{
																				double var36 = ((((1.0 * distributionTempVariable$v1$371) + v2[j]) + traceTempVariable$var33$395_1) / traceTempVariable$var35$8_1);
																				cv$temp$83$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample12Value370) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value370) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value370) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value370) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value370) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value370);
																		}
																	}
																}
																for(int index$i$396 = 0; index$i$396 < size; index$i$396 += 1) {
																	if(!(index$i$396 == index$i$2)) {
																		for(int index$sample26$397 = 0; index$sample26$397 < weightings.length; index$sample26$397 += 1) {
																			int distributionTempVariable$var23$399 = index$sample26$397;
																			double cv$probabilitySample26Value398 = (cv$probabilitySample12Value370 * distribution$sample26[((index$i$396 - 0) / 1)][index$sample26$397]);
																			int traceTempVariable$var33$400_1 = distributionTempVariable$var23$399;
																			if(((index$i$396 + 1) == j)) {
																				{
																					{
																						double cv$temp$84$var36;
																						{
																							double var36 = ((((1.0 * distributionTempVariable$v1$371) + v2[j]) + traceTempVariable$var33$400_1) / traceTempVariable$var35$8_1);
																							cv$temp$84$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value398) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value398) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value398) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value398) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value398) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value398);
																					}
																				}
																			}
																		}
																	}
																}
															}
														} else {
															if(true) {
																for(int index$sample18$379 = 0; index$sample18$379 < weightings.length; index$sample18$379 += 1) {
																	int distributionTempVariable$var15$381 = index$sample18$379;
																	double cv$probabilitySample18Value380 = (cv$probabilitySample12Value370 * distribution$sample18[index$sample18$379]);
																	int traceTempVariable$var31$382_1 = distributionTempVariable$var15$381;
																	if((0 == j)) {
																		int traceTempVariable$var33$401_1 = cv$currentValue;
																		if(((index$i$2 + 1) == j)) {
																			{
																				{
																					double cv$temp$85$var36;
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$371) + traceTempVariable$var31$382_1) + traceTempVariable$var33$401_1) / traceTempVariable$var35$8_1);
																						cv$temp$85$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample18Value380) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value380) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value380) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value380) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var36)))) + 1)) + (Math.log(cv$probabilitySample18Value380) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value380);
																				}
																			}
																		}
																		for(int index$i$402 = 0; index$i$402 < size; index$i$402 += 1) {
																			if(!(index$i$402 == index$i$2)) {
																				for(int index$sample26$403 = 0; index$sample26$403 < weightings.length; index$sample26$403 += 1) {
																					int distributionTempVariable$var23$405 = index$sample26$403;
																					double cv$probabilitySample26Value404 = (cv$probabilitySample18Value380 * distribution$sample26[((index$i$402 - 0) / 1)][index$sample26$403]);
																					int traceTempVariable$var33$406_1 = distributionTempVariable$var23$405;
																					if(((index$i$402 + 1) == j)) {
																						{
																							{
																								double cv$temp$86$var36;
																								{
																									double var36 = ((((1.0 * distributionTempVariable$v1$371) + traceTempVariable$var31$382_1) + traceTempVariable$var33$406_1) / traceTempVariable$var35$8_1);
																									cv$temp$86$var36 = var36;
																								}
																								if(((Math.log(cv$probabilitySample26Value404) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var36)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value404) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value404) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var36));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value404) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value404) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var36)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value404);
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
											if(fixedFlag$sample12) {
												int traceTempVariable$var31$412_1 = cv$currentValue;
												if(((index$i$2 + 1) == j)) {
													int traceTempVariable$var33$424_1 = cv$currentValue;
													if(((index$i$2 + 1) == j)) {
														{
															{
																double cv$temp$87$var36;
																{
																	double var36 = ((((1.0 * v1) + traceTempVariable$var31$412_1) + traceTempVariable$var33$424_1) / traceTempVariable$var35$8_1);
																	cv$temp$87$var36 = var36;
																}
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var36)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var36));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var36)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var36)));
																}
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
															}
														}
													}
													for(int index$i$425 = 0; index$i$425 < size; index$i$425 += 1) {
														if(!(index$i$425 == index$i$2)) {
															for(int index$sample26$426 = 0; index$sample26$426 < weightings.length; index$sample26$426 += 1) {
																int distributionTempVariable$var23$428 = index$sample26$426;
																double cv$probabilitySample26Value427 = (1.0 * distribution$sample26[((index$i$425 - 0) / 1)][index$sample26$426]);
																int traceTempVariable$var33$429_1 = distributionTempVariable$var23$428;
																if(((index$i$425 + 1) == j)) {
																	{
																		{
																			double cv$temp$88$var36;
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$412_1) + traceTempVariable$var33$429_1) / traceTempVariable$var35$8_1);
																				cv$temp$88$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample26Value427) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value427) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value427) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value427) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value427) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value427);
																		}
																	}
																}
															}
														}
													}
												}
												for(int index$i$413 = 0; index$i$413 < size; index$i$413 += 1) {
													if(!(index$i$413 == index$i$2)) {
														for(int index$sample26$414 = 0; index$sample26$414 < weightings.length; index$sample26$414 += 1) {
															int distributionTempVariable$var23$416 = index$sample26$414;
															double cv$probabilitySample26Value415 = (1.0 * distribution$sample26[((index$i$413 - 0) / 1)][index$sample26$414]);
															int traceTempVariable$var31$417_1 = distributionTempVariable$var23$416;
															if(((index$i$413 + 1) == j)) {
																int traceTempVariable$var33$430_1 = cv$currentValue;
																if(((index$i$2 + 1) == j)) {
																	{
																		{
																			double cv$temp$89$var36;
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$417_1) + traceTempVariable$var33$430_1) / traceTempVariable$var35$8_1);
																				cv$temp$89$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample26Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value415);
																		}
																	}
																}
																int traceTempVariable$var33$431_1 = distributionTempVariable$var23$416;
																if(((index$i$413 + 1) == j)) {
																	{
																		{
																			double cv$temp$90$var36;
																			{
																				double var36 = ((((1.0 * v1) + traceTempVariable$var31$417_1) + traceTempVariable$var33$431_1) / traceTempVariable$var35$8_1);
																				cv$temp$90$var36 = var36;
																			}
																			if(((Math.log(cv$probabilitySample26Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var36)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var36));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var36)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value415);
																		}
																	}
																}
																for(int index$i$432 = 0; index$i$432 < size; index$i$432 += 1) {
																	if((!(index$i$432 == index$i$2) && !(index$i$432 == index$i$413))) {
																		for(int index$sample26$433 = 0; index$sample26$433 < weightings.length; index$sample26$433 += 1) {
																			int distributionTempVariable$var23$435 = index$sample26$433;
																			double cv$probabilitySample26Value434 = (cv$probabilitySample26Value415 * distribution$sample26[((index$i$432 - 0) / 1)][index$sample26$433]);
																			int traceTempVariable$var33$436_1 = distributionTempVariable$var23$435;
																			if(((index$i$432 + 1) == j)) {
																				{
																					{
																						double cv$temp$91$var36;
																						{
																							double var36 = ((((1.0 * v1) + traceTempVariable$var31$417_1) + traceTempVariable$var33$436_1) / traceTempVariable$var35$8_1);
																							cv$temp$91$var36 = var36;
																						}
																						if(((Math.log(cv$probabilitySample26Value434) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var36)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value434) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value434) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var36));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value434) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value434) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var36)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value434);
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
												if(true) {
													for(int index$sample12$408 = 0; index$sample12$408 < weightings.length; index$sample12$408 += 1) {
														int distributionTempVariable$v1$410 = index$sample12$408;
														double cv$probabilitySample12Value409 = (1.0 * distribution$sample12[index$sample12$408]);
														int traceTempVariable$var31$418_1 = cv$currentValue;
														if(((index$i$2 + 1) == j)) {
															int traceTempVariable$var33$437_1 = cv$currentValue;
															if(((index$i$2 + 1) == j)) {
																{
																	{
																		double cv$temp$92$var36;
																		{
																			double var36 = ((((1.0 * distributionTempVariable$v1$410) + traceTempVariable$var31$418_1) + traceTempVariable$var33$437_1) / traceTempVariable$var35$8_1);
																			cv$temp$92$var36 = var36;
																		}
																		if(((Math.log(cv$probabilitySample12Value409) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var36)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value409) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value409) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var36));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value409) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var36)))) + 1)) + (Math.log(cv$probabilitySample12Value409) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var36)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value409);
																	}
																}
															}
															for(int index$i$438 = 0; index$i$438 < size; index$i$438 += 1) {
																if(!(index$i$438 == index$i$2)) {
																	for(int index$sample26$439 = 0; index$sample26$439 < weightings.length; index$sample26$439 += 1) {
																		int distributionTempVariable$var23$441 = index$sample26$439;
																		double cv$probabilitySample26Value440 = (cv$probabilitySample12Value409 * distribution$sample26[((index$i$438 - 0) / 1)][index$sample26$439]);
																		int traceTempVariable$var33$442_1 = distributionTempVariable$var23$441;
																		if(((index$i$438 + 1) == j)) {
																			{
																				{
																					double cv$temp$93$var36;
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$410) + traceTempVariable$var31$418_1) + traceTempVariable$var33$442_1) / traceTempVariable$var35$8_1);
																						cv$temp$93$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample26Value440) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value440) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value440) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value440) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value440) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value440);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int index$i$419 = 0; index$i$419 < size; index$i$419 += 1) {
															if(!(index$i$419 == index$i$2)) {
																for(int index$sample26$420 = 0; index$sample26$420 < weightings.length; index$sample26$420 += 1) {
																	int distributionTempVariable$var23$422 = index$sample26$420;
																	double cv$probabilitySample26Value421 = (cv$probabilitySample12Value409 * distribution$sample26[((index$i$419 - 0) / 1)][index$sample26$420]);
																	int traceTempVariable$var31$423_1 = distributionTempVariable$var23$422;
																	if(((index$i$419 + 1) == j)) {
																		int traceTempVariable$var33$443_1 = cv$currentValue;
																		if(((index$i$2 + 1) == j)) {
																			{
																				{
																					double cv$temp$94$var36;
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$410) + traceTempVariable$var31$423_1) + traceTempVariable$var33$443_1) / traceTempVariable$var35$8_1);
																						cv$temp$94$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample26Value421) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value421) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value421) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value421) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value421) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value421);
																				}
																			}
																		}
																		int traceTempVariable$var33$444_1 = distributionTempVariable$var23$422;
																		if(((index$i$419 + 1) == j)) {
																			{
																				{
																					double cv$temp$95$var36;
																					{
																						double var36 = ((((1.0 * distributionTempVariable$v1$410) + traceTempVariable$var31$423_1) + traceTempVariable$var33$444_1) / traceTempVariable$var35$8_1);
																						cv$temp$95$var36 = var36;
																					}
																					if(((Math.log(cv$probabilitySample26Value421) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var36)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value421) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value421) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var36));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value421) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value421) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var36)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value421);
																				}
																			}
																		}
																		for(int index$i$445 = 0; index$i$445 < size; index$i$445 += 1) {
																			if((!(index$i$445 == index$i$2) && !(index$i$445 == index$i$419))) {
																				for(int index$sample26$446 = 0; index$sample26$446 < weightings.length; index$sample26$446 += 1) {
																					int distributionTempVariable$var23$448 = index$sample26$446;
																					double cv$probabilitySample26Value447 = (cv$probabilitySample26Value421 * distribution$sample26[((index$i$445 - 0) / 1)][index$sample26$446]);
																					int traceTempVariable$var33$449_1 = distributionTempVariable$var23$448;
																					if(((index$i$445 + 1) == j)) {
																						{
																							{
																								double cv$temp$96$var36;
																								{
																									double var36 = ((((1.0 * distributionTempVariable$v1$410) + traceTempVariable$var31$423_1) + traceTempVariable$var33$449_1) / traceTempVariable$var35$8_1);
																									cv$temp$96$var36 = var36;
																								}
																								if(((Math.log(cv$probabilitySample26Value447) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var36)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value447) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var36)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value447) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var36));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value447) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var36)))) + 1)) + (Math.log(cv$probabilitySample26Value447) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var36)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value447);
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
		double[] cv$localProbability = distribution$sample26[((i - 0) / 1)];
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
			cv$var9$stateProbabilityGlobal = new double[weightings.length];
		}
		{
			cv$var15$stateProbabilityGlobal = new double[weightings.length];
		}
		{
			int cv$max_j = 0;
			cv$max_j = Math.max(cv$max_j, ((length$value - 0) / 1));
			guard$sample18bernoulli40$global = new boolean[cv$max_j];
		}
		{
			{
				int cv$threadCount = threadCount();
				cv$var23$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var23$stateProbabilityGlobal[cv$index] = new double[weightings.length];
			}
		}
		{
			int cv$max_j = 0;
			cv$max_j = Math.max(cv$max_j, ((length$value - 0) / 1));
			{
				int cv$threadCount = threadCount();
				guard$sample26bernoulli40$global = new boolean[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					guard$sample26bernoulli40$global[cv$index] = new boolean[cv$max_j];
			}
		}
	}

	@Override
	public final void allocator() {
		if(!setFlag$v2) {
			{
				v2 = new int[(length$value + 1)];
			}
		}
		if(!setFlag$v) {
			{
				v = new boolean[length$value];
			}
		}
		{
			distribution$sample18 = new double[weightings.length];
		}
		{
			distribution$sample26 = new double[((((length$value - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < length$value; i += 1)
				distribution$sample26[((i - 0) / 1)] = new double[weightings.length];
		}
		{
			distribution$sample12 = new double[weightings.length];
		}
		{
			logProbability$var22 = new double[((((length$value - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample26 = new double[((((length$value - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var37 = new double[((((length$value - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample41 = new double[((((length$value - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample18)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		parallelFor(RNG$, 0, size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample26)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings);
					}
			}
		);
		parallelFor(RNG$, 0, size, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1) {
						if(!fixedFlag$sample41)
							v[j] = DistributionSampling.sampleBernoulli(RNG$1, ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]));
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		double[] cv$distribution$sample12 = distribution$sample12;
		for(int index$var8 = 0; index$var8 < weightings.length; index$var8 += 1) {
			double cv$value = (((0.0 <= index$var8) && (index$var8 < weightings.length))?weightings[index$var8]:0.0);
			if(!fixedFlag$sample12)
				cv$distribution$sample12[index$var8] = cv$value;
		}
		double[] cv$distribution$sample18 = distribution$sample18;
		for(int index$var14 = 0; index$var14 < weightings.length; index$var14 += 1) {
			double cv$value = (((0.0 <= index$var14) && (index$var14 < weightings.length))?weightings[index$var14]:0.0);
			if(!fixedFlag$sample18)
				cv$distribution$sample18[index$var14] = cv$value;
		}
		parallelFor(RNG$, 0, size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						double[] cv$distribution$sample26 = distribution$sample26[((i - 0) / 1)];
						for(int index$var22 = 0; index$var22 < weightings.length; index$var22 += 1) {
							double cv$value = (((0.0 <= index$var22) && (index$var22 < weightings.length))?weightings[index$var22]:0.0);
							if(!fixedFlag$sample26)
								cv$distribution$sample26[index$var22] = cv$value;
						}
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample18)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		parallelFor(RNG$, 0, size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample26)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample12)
				sample12();
			if(!fixedFlag$sample18)
				sample18();
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							if(!fixedFlag$sample26)
								sample26(i, threadID$i, RNG$1);
						}
				}
			);
		} else {
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							if(!fixedFlag$sample26)
								sample26(i, threadID$i, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample18)
				sample18();
			if(!fixedFlag$sample12)
				sample12();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		size = length$value;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var8 = 0.0;
		if(!fixedProbFlag$sample12)
			logProbability$v1 = 0.0;
		logProbability$var14 = 0.0;
		logProbability$v2 = 0.0;
		if(!fixedProbFlag$sample18)
			logProbability$var15 = 0.0;
		for(int i = 0; i < size; i += 1)
			logProbability$var22[((i - 0) / 1)] = 0.0;
		if(!fixedProbFlag$sample26) {
			for(int i = 0; i < size; i += 1)
				logProbability$sample26[((i - 0) / 1)] = 0.0;
		}
		for(int j = 0; j < size; j += 1)
			logProbability$var37[((j - 0) / 1)] = 0.0;
		logProbability$v = 0.0;
		if(!fixedProbFlag$sample41) {
			for(int j = 0; j < size; j += 1)
				logProbability$sample41[((j - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample41();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample12();
		logProbabilityDistribution$sample18();
		logProbabilityDistribution$sample26();
		logProbabilityDistribution$sample41();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample12();
		logProbabilityValue$sample18();
		logProbabilityValue$sample26();
		logProbabilityValue$sample41();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample18)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		parallelFor(RNG$, 0, size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample26)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings);
					}
			}
		);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		boolean[] cv$source1 = value;
		boolean[] cv$target1 = v;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel DistributionTest6(double[] weightings, boolean[] value) {\n    int size = value.length;\n    \n    int v1 = categorical(weightings).sampleDistribution();\n    \n    int[] v2 = new int[size + 1];\n    v2[0] = categorical(weightings).sampleDistribution();\n    for(int i:[0..size))\n        v2[i + 1] = categorical(weightings).sampleDistribution();\n        \n    boolean[] v = new boolean[size];\n    for(int j:[0..size))\n        v[j] = bernoulli(((1.0*v1) + v2[j] + v2[j])/v2[j]).sample();\n        \n    v.observe(value);\n}\n";
	}
}