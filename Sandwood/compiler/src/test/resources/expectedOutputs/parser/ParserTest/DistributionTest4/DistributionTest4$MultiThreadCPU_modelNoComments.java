package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DistributionTest4$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DistributionTest4$CoreInterface {
	private double[] cv$var11$stateProbabilityGlobal;
	private double[] cv$var27$stateProbabilityGlobal;
	private double[] cv$var5$stateProbabilityGlobal;
	private double[] distribution$sample11;
	private double[][] distribution$sample27;
	private double[] distribution$sample5;
	private boolean fixedFlag$sample11 = false;
	private boolean fixedFlag$sample27 = false;
	private boolean fixedFlag$sample5 = false;
	private boolean fixedProbFlag$sample11 = false;
	private boolean fixedProbFlag$sample27 = false;
	private boolean fixedProbFlag$sample5 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean[] guard$sample11bernoulli52$global;
	private boolean[] guard$sample27bernoulli52$global;
	private int length$value;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$sample27;
	private double[] logProbability$sample53;
	private double logProbability$v;
	private double logProbability$v1;
	private double logProbability$v2;
	private double logProbability$var11;
	private int size;
	private boolean system$gibbsForward = true;
	private boolean[] v;
	private int v1;
	private int[] v2;
	private boolean[] value;
	private double[] weightings;

	public DistributionTest4$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$distribution$sample11() {
		return distribution$sample11;
	}

	@Override
	public final void set$distribution$sample11(double[] cv$value) {
		distribution$sample11 = cv$value;
	}

	@Override
	public final double[][] get$distribution$sample27() {
		return distribution$sample27;
	}

	@Override
	public final void set$distribution$sample27(double[][] cv$value) {
		distribution$sample27 = cv$value;
	}

	@Override
	public final double[] get$distribution$sample5() {
		return distribution$sample5;
	}

	@Override
	public final void set$distribution$sample5(double[] cv$value) {
		distribution$sample5 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample11() {
		return fixedFlag$sample11;
	}

	@Override
	public final void set$fixedFlag$sample11(boolean cv$value) {
		fixedFlag$sample11 = cv$value;
		fixedProbFlag$sample11 = (fixedFlag$sample11 && fixedProbFlag$sample11);
		fixedProbFlag$sample53 = (fixedFlag$sample11 && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$fixedFlag$sample27() {
		return fixedFlag$sample27;
	}

	@Override
	public final void set$fixedFlag$sample27(boolean cv$value) {
		fixedFlag$sample27 = cv$value;
		fixedProbFlag$sample27 = (fixedFlag$sample27 && fixedProbFlag$sample27);
		fixedProbFlag$sample53 = (fixedFlag$sample27 && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$fixedFlag$sample5() {
		return fixedFlag$sample5;
	}

	@Override
	public final void set$fixedFlag$sample5(boolean cv$value) {
		fixedFlag$sample5 = cv$value;
		fixedProbFlag$sample5 = (fixedFlag$sample5 && fixedProbFlag$sample5);
		fixedProbFlag$sample53 = (fixedFlag$sample5 && fixedProbFlag$sample53);
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
	public final int get$v1() {
		return v1;
	}

	@Override
	public final void set$v1(int cv$value) {
		v1 = cv$value;
		fixedProbFlag$sample5 = false;
		fixedProbFlag$sample53 = false;
	}

	@Override
	public final int[] get$v2() {
		return v2;
	}

	@Override
	public final void set$v2(int[] cv$value) {
		v2 = cv$value;
		fixedProbFlag$sample11 = false;
		fixedProbFlag$sample27 = false;
		fixedProbFlag$sample53 = false;
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

	private final void logProbabilityDistribution$sample11() {
		if(!fixedProbFlag$sample11) {
			if(fixedFlag$sample11) {
				double cv$accumulator = 0.0;
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
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
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var11 = cv$sampleProbability;
				if(fixedFlag$sample11)
					logProbability$v2 = (logProbability$v2 + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample11)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample11 = fixedFlag$sample11;
			}
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var11;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(fixedFlag$sample11)
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample11)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample27() {
		if(!fixedProbFlag$sample27) {
			if(fixedFlag$sample27) {
				double cv$accumulator = 0.0;
				boolean cv$sampleReached = false;
				for(int i = 0; i < size; i += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$i$1 = i;
					{
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
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					cv$sampleReached = true;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$sample27[((i - 0) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample27)
					logProbability$v2 = (logProbability$v2 + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample27)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample27 = fixedFlag$sample27;
			}
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < size; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample27[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			if(fixedFlag$sample27)
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample5() {
		if(!fixedProbFlag$sample5) {
			if(fixedFlag$sample5) {
				double cv$accumulator = 0.0;
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
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
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$v1 = cv$sampleProbability;
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample5)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample5 = fixedFlag$sample5;
			}
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$v1;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < size; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = v[j];
						if(fixedFlag$sample5) {
							{
								if(fixedFlag$sample11) {
									{
										if((0 == j)) {
											{
												if((0 == (j + 1))) {
													{
														if((0 == (j + 1))) {
															{
																double var51 = ((((1.0 * v1) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
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
									if(true) {
										for(int index$sample11$8 = 0; index$sample11$8 < weightings.length; index$sample11$8 += 1) {
											int distributionTempVariable$var11$10 = index$sample11$8;
											double cv$probabilitySample11Value9 = (1.0 * distribution$sample11[index$sample11$8]);
											{
												int traceTempVariable$var42$11_1 = distributionTempVariable$var11$10;
												if((0 == j)) {
													{
														int traceTempVariable$var46$18_1 = distributionTempVariable$var11$10;
														if((0 == (j + 1))) {
															{
																int traceTempVariable$var50$30_1 = distributionTempVariable$var11$10;
																if((0 == (j + 1))) {
																	{
																		double var51 = ((((1.0 * v1) + traceTempVariable$var42$11_1) + traceTempVariable$var46$18_1) / traceTempVariable$var50$30_1);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample11Value9) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																		if((cv$weightedProbability < cv$distributionAccumulator))
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																		else {
																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																				cv$distributionAccumulator = cv$weightedProbability;
																			else
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																		}
																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value9);
																	}
																}
															}
															if(!true) {
																for(int index$sample11$31 = 0; index$sample11$31 < weightings.length; index$sample11$31 += 1) {
																	int distributionTempVariable$var11$33 = index$sample11$31;
																	double cv$probabilitySample11Value32 = (cv$probabilitySample11Value9 * distribution$sample11[index$sample11$31]);
																	{
																		int traceTempVariable$var50$34_1 = distributionTempVariable$var11$33;
																		if((0 == (j + 1))) {
																			{
																				double var51 = ((((1.0 * v1) + traceTempVariable$var42$11_1) + traceTempVariable$var46$18_1) / traceTempVariable$var50$34_1);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample11Value32) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value32);
																			}
																		}
																	}
																}
															}
														}
													}
													if(!true) {
														for(int index$sample11$19 = 0; index$sample11$19 < weightings.length; index$sample11$19 += 1) {
															int distributionTempVariable$var11$21 = index$sample11$19;
															double cv$probabilitySample11Value20 = (cv$probabilitySample11Value9 * distribution$sample11[index$sample11$19]);
															{
																int traceTempVariable$var46$22_1 = distributionTempVariable$var11$21;
																if((0 == (j + 1))) {
																	{
																		int traceTempVariable$var50$35_1 = distributionTempVariable$var11$10;
																		if((0 == (j + 1))) {
																			{
																				double var51 = ((((1.0 * v1) + traceTempVariable$var42$11_1) + traceTempVariable$var46$22_1) / traceTempVariable$var50$35_1);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample11Value20) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value20);
																			}
																		}
																	}
																	{
																		int traceTempVariable$var50$36_1 = distributionTempVariable$var11$21;
																		if((0 == (j + 1))) {
																			{
																				double var51 = ((((1.0 * v1) + traceTempVariable$var42$11_1) + traceTempVariable$var46$22_1) / traceTempVariable$var50$36_1);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample11Value20) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value20);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample11$37 = 0; index$sample11$37 < weightings.length; index$sample11$37 += 1) {
																			int distributionTempVariable$var11$39 = index$sample11$37;
																			double cv$probabilitySample11Value38 = (cv$probabilitySample11Value20 * distribution$sample11[index$sample11$37]);
																			{
																				int traceTempVariable$var50$40_1 = distributionTempVariable$var11$39;
																				if((0 == (j + 1))) {
																					{
																						double var51 = ((((1.0 * v1) + traceTempVariable$var42$11_1) + traceTempVariable$var46$22_1) / traceTempVariable$var50$40_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample11Value38) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value38);
																					}
																				}
																			}
																		}
																	}
																}
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
								for(int index$sample5$3 = 0; index$sample5$3 < weightings.length; index$sample5$3 += 1) {
									int distributionTempVariable$v1$5 = index$sample5$3;
									double cv$probabilitySample5Value4 = (1.0 * distribution$sample5[index$sample5$3]);
									{
										if(fixedFlag$sample11) {
											{
												if((0 == j)) {
													{
														if((0 == (j + 1))) {
															{
																if((0 == (j + 1))) {
																	{
																		double var51 = ((((1.0 * distributionTempVariable$v1$5) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample5Value4) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																		if((cv$weightedProbability < cv$distributionAccumulator))
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																		else {
																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																				cv$distributionAccumulator = cv$weightedProbability;
																			else
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																		}
																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value4);
																	}
																}
															}
														}
													}
												}
											}
										} else {
											if(true) {
												for(int index$sample11$13 = 0; index$sample11$13 < weightings.length; index$sample11$13 += 1) {
													int distributionTempVariable$var11$15 = index$sample11$13;
													double cv$probabilitySample11Value14 = (cv$probabilitySample5Value4 * distribution$sample11[index$sample11$13]);
													{
														int traceTempVariable$var42$16_1 = distributionTempVariable$var11$15;
														if((0 == j)) {
															{
																int traceTempVariable$var46$24_1 = distributionTempVariable$var11$15;
																if((0 == (j + 1))) {
																	{
																		int traceTempVariable$var50$42_1 = distributionTempVariable$var11$15;
																		if((0 == (j + 1))) {
																			{
																				double var51 = ((((1.0 * distributionTempVariable$v1$5) + traceTempVariable$var42$16_1) + traceTempVariable$var46$24_1) / traceTempVariable$var50$42_1);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample11Value14) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value14);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample11$43 = 0; index$sample11$43 < weightings.length; index$sample11$43 += 1) {
																			int distributionTempVariable$var11$45 = index$sample11$43;
																			double cv$probabilitySample11Value44 = (cv$probabilitySample11Value14 * distribution$sample11[index$sample11$43]);
																			{
																				int traceTempVariable$var50$46_1 = distributionTempVariable$var11$45;
																				if((0 == (j + 1))) {
																					{
																						double var51 = ((((1.0 * distributionTempVariable$v1$5) + traceTempVariable$var42$16_1) + traceTempVariable$var46$24_1) / traceTempVariable$var50$46_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample11Value44) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value44);
																					}
																				}
																			}
																		}
																	}
																}
															}
															if(!true) {
																for(int index$sample11$25 = 0; index$sample11$25 < weightings.length; index$sample11$25 += 1) {
																	int distributionTempVariable$var11$27 = index$sample11$25;
																	double cv$probabilitySample11Value26 = (cv$probabilitySample11Value14 * distribution$sample11[index$sample11$25]);
																	{
																		int traceTempVariable$var46$28_1 = distributionTempVariable$var11$27;
																		if((0 == (j + 1))) {
																			{
																				int traceTempVariable$var50$47_1 = distributionTempVariable$var11$15;
																				if((0 == (j + 1))) {
																					{
																						double var51 = ((((1.0 * distributionTempVariable$v1$5) + traceTempVariable$var42$16_1) + traceTempVariable$var46$28_1) / traceTempVariable$var50$47_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample11Value26) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value26);
																					}
																				}
																			}
																			{
																				int traceTempVariable$var50$48_1 = distributionTempVariable$var11$27;
																				if((0 == (j + 1))) {
																					{
																						double var51 = ((((1.0 * distributionTempVariable$v1$5) + traceTempVariable$var42$16_1) + traceTempVariable$var46$28_1) / traceTempVariable$var50$48_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample11Value26) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value26);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample11$49 = 0; index$sample11$49 < weightings.length; index$sample11$49 += 1) {
																					int distributionTempVariable$var11$51 = index$sample11$49;
																					double cv$probabilitySample11Value50 = (cv$probabilitySample11Value26 * distribution$sample11[index$sample11$49]);
																					{
																						int traceTempVariable$var50$52_1 = distributionTempVariable$var11$51;
																						if((0 == (j + 1))) {
																							{
																								double var51 = ((((1.0 * distributionTempVariable$v1$5) + traceTempVariable$var42$16_1) + traceTempVariable$var46$28_1) / traceTempVariable$var50$52_1);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample11Value50) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value50);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample5) {
							{
								if(fixedFlag$sample27) {
									{
										for(int i = 0; i < size; i += 1) {
											if(((i + 1) == j)) {
												if(fixedFlag$sample11) {
													{
														if((0 == (j + 1))) {
															{
																if((0 == (j + 1))) {
																	{
																		double var51 = ((((1.0 * v1) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																		double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
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
													if(true) {
														for(int index$sample11$71 = 0; index$sample11$71 < weightings.length; index$sample11$71 += 1) {
															int distributionTempVariable$var11$73 = index$sample11$71;
															double cv$probabilitySample11Value72 = (1.0 * distribution$sample11[index$sample11$71]);
															{
																int traceTempVariable$var46$74_1 = distributionTempVariable$var11$73;
																if((0 == (j + 1))) {
																	{
																		int traceTempVariable$var50$91_1 = distributionTempVariable$var11$73;
																		if((0 == (j + 1))) {
																			{
																				double var51 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var46$74_1) / traceTempVariable$var50$91_1);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample11Value72) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value72);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample11$92 = 0; index$sample11$92 < weightings.length; index$sample11$92 += 1) {
																			int distributionTempVariable$var11$94 = index$sample11$92;
																			double cv$probabilitySample11Value93 = (cv$probabilitySample11Value72 * distribution$sample11[index$sample11$92]);
																			{
																				int traceTempVariable$var50$95_1 = distributionTempVariable$var11$94;
																				if((0 == (j + 1))) {
																					{
																						double var51 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var46$74_1) / traceTempVariable$var50$95_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample11Value93) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value93);
																					}
																				}
																			}
																		}
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
											for(int index$sample27$60 = 0; index$sample27$60 < weightings.length; index$sample27$60 += 1) {
												int distributionTempVariable$var27$62 = index$sample27$60;
												double cv$probabilitySample27Value61 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$60]);
												{
													int traceTempVariable$var42$63_1 = distributionTempVariable$var27$62;
													if(((i + 1) == j)) {
														if(fixedFlag$sample11) {
															{
																if((0 == (j + 1))) {
																	{
																		if((0 == (j + 1))) {
																			{
																				double var51 = ((((1.0 * v1) + traceTempVariable$var42$63_1) + v2[(j + 1)]) / v2[(j + 1)]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample27Value61) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value61);
																			}
																		}
																	}
																}
															}
														} else {
															if(true) {
																for(int index$sample11$76 = 0; index$sample11$76 < weightings.length; index$sample11$76 += 1) {
																	int distributionTempVariable$var11$78 = index$sample11$76;
																	double cv$probabilitySample11Value77 = (cv$probabilitySample27Value61 * distribution$sample11[index$sample11$76]);
																	{
																		int traceTempVariable$var46$79_1 = distributionTempVariable$var11$78;
																		if((0 == (j + 1))) {
																			{
																				int traceTempVariable$var50$97_1 = distributionTempVariable$var11$78;
																				if((0 == (j + 1))) {
																					{
																						double var51 = ((((1.0 * v1) + traceTempVariable$var42$63_1) + traceTempVariable$var46$79_1) / traceTempVariable$var50$97_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample11Value77) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value77);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample11$98 = 0; index$sample11$98 < weightings.length; index$sample11$98 += 1) {
																					int distributionTempVariable$var11$100 = index$sample11$98;
																					double cv$probabilitySample11Value99 = (cv$probabilitySample11Value77 * distribution$sample11[index$sample11$98]);
																					{
																						int traceTempVariable$var50$101_1 = distributionTempVariable$var11$100;
																						if((0 == (j + 1))) {
																							{
																								double var51 = ((((1.0 * v1) + traceTempVariable$var42$63_1) + traceTempVariable$var46$79_1) / traceTempVariable$var50$101_1);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample11Value99) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value99);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
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
								for(int index$sample5$54 = 0; index$sample5$54 < weightings.length; index$sample5$54 += 1) {
									int distributionTempVariable$v1$56 = index$sample5$54;
									double cv$probabilitySample5Value55 = (1.0 * distribution$sample5[index$sample5$54]);
									{
										if(fixedFlag$sample27) {
											{
												for(int i = 0; i < size; i += 1) {
													if(((i + 1) == j)) {
														if(fixedFlag$sample11) {
															{
																if((0 == (j + 1))) {
																	{
																		if((0 == (j + 1))) {
																			{
																				double var51 = ((((1.0 * distributionTempVariable$v1$56) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample5Value55) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value55);
																			}
																		}
																	}
																}
															}
														} else {
															if(true) {
																for(int index$sample11$81 = 0; index$sample11$81 < weightings.length; index$sample11$81 += 1) {
																	int distributionTempVariable$var11$83 = index$sample11$81;
																	double cv$probabilitySample11Value82 = (cv$probabilitySample5Value55 * distribution$sample11[index$sample11$81]);
																	{
																		int traceTempVariable$var46$84_1 = distributionTempVariable$var11$83;
																		if((0 == (j + 1))) {
																			{
																				int traceTempVariable$var50$103_1 = distributionTempVariable$var11$83;
																				if((0 == (j + 1))) {
																					{
																						double var51 = ((((1.0 * distributionTempVariable$v1$56) + v2[j]) + traceTempVariable$var46$84_1) / traceTempVariable$var50$103_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample11Value82) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value82);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample11$104 = 0; index$sample11$104 < weightings.length; index$sample11$104 += 1) {
																					int distributionTempVariable$var11$106 = index$sample11$104;
																					double cv$probabilitySample11Value105 = (cv$probabilitySample11Value82 * distribution$sample11[index$sample11$104]);
																					{
																						int traceTempVariable$var50$107_1 = distributionTempVariable$var11$106;
																						if((0 == (j + 1))) {
																							{
																								double var51 = ((((1.0 * distributionTempVariable$v1$56) + v2[j]) + traceTempVariable$var46$84_1) / traceTempVariable$var50$107_1);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample11Value105) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value105);
																							}
																						}
																					}
																				}
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
													for(int index$sample27$66 = 0; index$sample27$66 < weightings.length; index$sample27$66 += 1) {
														int distributionTempVariable$var27$68 = index$sample27$66;
														double cv$probabilitySample27Value67 = (cv$probabilitySample5Value55 * distribution$sample27[((i - 0) / 1)][index$sample27$66]);
														{
															int traceTempVariable$var42$69_1 = distributionTempVariable$var27$68;
															if(((i + 1) == j)) {
																if(fixedFlag$sample11) {
																	{
																		if((0 == (j + 1))) {
																			{
																				if((0 == (j + 1))) {
																					{
																						double var51 = ((((1.0 * distributionTempVariable$v1$56) + traceTempVariable$var42$69_1) + v2[(j + 1)]) / v2[(j + 1)]);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample27Value67) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value67);
																					}
																				}
																			}
																		}
																	}
																} else {
																	if(true) {
																		for(int index$sample11$86 = 0; index$sample11$86 < weightings.length; index$sample11$86 += 1) {
																			int distributionTempVariable$var11$88 = index$sample11$86;
																			double cv$probabilitySample11Value87 = (cv$probabilitySample27Value67 * distribution$sample11[index$sample11$86]);
																			{
																				int traceTempVariable$var46$89_1 = distributionTempVariable$var11$88;
																				if((0 == (j + 1))) {
																					{
																						int traceTempVariable$var50$109_1 = distributionTempVariable$var11$88;
																						if((0 == (j + 1))) {
																							{
																								double var51 = ((((1.0 * distributionTempVariable$v1$56) + traceTempVariable$var42$69_1) + traceTempVariable$var46$89_1) / traceTempVariable$var50$109_1);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample11Value87) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value87);
																							}
																						}
																					}
																					if(!true) {
																						for(int index$sample11$110 = 0; index$sample11$110 < weightings.length; index$sample11$110 += 1) {
																							int distributionTempVariable$var11$112 = index$sample11$110;
																							double cv$probabilitySample11Value111 = (cv$probabilitySample11Value87 * distribution$sample11[index$sample11$110]);
																							{
																								int traceTempVariable$var50$113_1 = distributionTempVariable$var11$112;
																								if((0 == (j + 1))) {
																									{
																										double var51 = ((((1.0 * distributionTempVariable$v1$56) + traceTempVariable$var42$69_1) + traceTempVariable$var46$89_1) / traceTempVariable$var50$113_1);
																										double cv$weightedProbability = (Math.log(cv$probabilitySample11Value111) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																										if((cv$weightedProbability < cv$distributionAccumulator))
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																										else {
																											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																												cv$distributionAccumulator = cv$weightedProbability;
																											else
																												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																										}
																										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value111);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample5) {
							{
								if(fixedFlag$sample11) {
									{
										if((0 == j)) {
											if(fixedFlag$sample27) {
												{
													for(int i = 0; i < size; i += 1) {
														if(((i + 1) == (j + 1))) {
															{
																if((0 == (j + 1))) {
																	{
																		double var51 = ((((1.0 * v1) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																		double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
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
												for(int i = 0; i < size; i += 1) {
													if(true) {
														for(int index$sample27$131 = 0; index$sample27$131 < weightings.length; index$sample27$131 += 1) {
															int distributionTempVariable$var27$133 = index$sample27$131;
															double cv$probabilitySample27Value132 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$131]);
															{
																int traceTempVariable$var46$134_1 = distributionTempVariable$var27$133;
																if(((i + 1) == (j + 1))) {
																	{
																		if((0 == (j + 1))) {
																			{
																				double var51 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var46$134_1) / v2[(j + 1)]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample27Value132) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value132);
																			}
																		}
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
										for(int index$sample11$120 = 0; index$sample11$120 < weightings.length; index$sample11$120 += 1) {
											int distributionTempVariable$var11$122 = index$sample11$120;
											double cv$probabilitySample11Value121 = (1.0 * distribution$sample11[index$sample11$120]);
											{
												int traceTempVariable$var42$123_1 = distributionTempVariable$var11$122;
												if((0 == j)) {
													if(fixedFlag$sample27) {
														{
															for(int i = 0; i < size; i += 1) {
																if(((i + 1) == (j + 1))) {
																	{
																		int traceTempVariable$var50$155_1 = distributionTempVariable$var11$122;
																		if((0 == (j + 1))) {
																			{
																				double var51 = ((((1.0 * v1) + traceTempVariable$var42$123_1) + v2[(j + 1)]) / traceTempVariable$var50$155_1);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample11Value121) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value121);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample11$156 = 0; index$sample11$156 < weightings.length; index$sample11$156 += 1) {
																			int distributionTempVariable$var11$158 = index$sample11$156;
																			double cv$probabilitySample11Value157 = (cv$probabilitySample11Value121 * distribution$sample11[index$sample11$156]);
																			{
																				int traceTempVariable$var50$159_1 = distributionTempVariable$var11$158;
																				if((0 == (j + 1))) {
																					{
																						double var51 = ((((1.0 * v1) + traceTempVariable$var42$123_1) + v2[(j + 1)]) / traceTempVariable$var50$159_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample11Value157) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value157);
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
																for(int index$sample27$137 = 0; index$sample27$137 < weightings.length; index$sample27$137 += 1) {
																	int distributionTempVariable$var27$139 = index$sample27$137;
																	double cv$probabilitySample27Value138 = (cv$probabilitySample11Value121 * distribution$sample27[((i - 0) / 1)][index$sample27$137]);
																	{
																		int traceTempVariable$var46$140_1 = distributionTempVariable$var27$139;
																		if(((i + 1) == (j + 1))) {
																			{
																				int traceTempVariable$var50$160_1 = distributionTempVariable$var11$122;
																				if((0 == (j + 1))) {
																					{
																						double var51 = ((((1.0 * v1) + traceTempVariable$var42$123_1) + traceTempVariable$var46$140_1) / traceTempVariable$var50$160_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample27Value138) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value138);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample11$161 = 0; index$sample11$161 < weightings.length; index$sample11$161 += 1) {
																					int distributionTempVariable$var11$163 = index$sample11$161;
																					double cv$probabilitySample11Value162 = (cv$probabilitySample27Value138 * distribution$sample11[index$sample11$161]);
																					{
																						int traceTempVariable$var50$164_1 = distributionTempVariable$var11$163;
																						if((0 == (j + 1))) {
																							{
																								double var51 = ((((1.0 * v1) + traceTempVariable$var42$123_1) + traceTempVariable$var46$140_1) / traceTempVariable$var50$164_1);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample11Value162) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value162);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
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
								for(int index$sample5$115 = 0; index$sample5$115 < weightings.length; index$sample5$115 += 1) {
									int distributionTempVariable$v1$117 = index$sample5$115;
									double cv$probabilitySample5Value116 = (1.0 * distribution$sample5[index$sample5$115]);
									{
										if(fixedFlag$sample11) {
											{
												if((0 == j)) {
													if(fixedFlag$sample27) {
														{
															for(int i = 0; i < size; i += 1) {
																if(((i + 1) == (j + 1))) {
																	{
																		if((0 == (j + 1))) {
																			{
																				double var51 = ((((1.0 * distributionTempVariable$v1$117) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample5Value116) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value116);
																			}
																		}
																	}
																}
															}
														}
													} else {
														for(int i = 0; i < size; i += 1) {
															if(true) {
																for(int index$sample27$143 = 0; index$sample27$143 < weightings.length; index$sample27$143 += 1) {
																	int distributionTempVariable$var27$145 = index$sample27$143;
																	double cv$probabilitySample27Value144 = (cv$probabilitySample5Value116 * distribution$sample27[((i - 0) / 1)][index$sample27$143]);
																	{
																		int traceTempVariable$var46$146_1 = distributionTempVariable$var27$145;
																		if(((i + 1) == (j + 1))) {
																			{
																				if((0 == (j + 1))) {
																					{
																						double var51 = ((((1.0 * distributionTempVariable$v1$117) + v2[j]) + traceTempVariable$var46$146_1) / v2[(j + 1)]);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample27Value144) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value144);
																					}
																				}
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
												for(int index$sample11$125 = 0; index$sample11$125 < weightings.length; index$sample11$125 += 1) {
													int distributionTempVariable$var11$127 = index$sample11$125;
													double cv$probabilitySample11Value126 = (cv$probabilitySample5Value116 * distribution$sample11[index$sample11$125]);
													{
														int traceTempVariable$var42$128_1 = distributionTempVariable$var11$127;
														if((0 == j)) {
															if(fixedFlag$sample27) {
																{
																	for(int i = 0; i < size; i += 1) {
																		if(((i + 1) == (j + 1))) {
																			{
																				int traceTempVariable$var50$167_1 = distributionTempVariable$var11$127;
																				if((0 == (j + 1))) {
																					{
																						double var51 = ((((1.0 * distributionTempVariable$v1$117) + traceTempVariable$var42$128_1) + v2[(j + 1)]) / traceTempVariable$var50$167_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample11Value126) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value126);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample11$168 = 0; index$sample11$168 < weightings.length; index$sample11$168 += 1) {
																					int distributionTempVariable$var11$170 = index$sample11$168;
																					double cv$probabilitySample11Value169 = (cv$probabilitySample11Value126 * distribution$sample11[index$sample11$168]);
																					{
																						int traceTempVariable$var50$171_1 = distributionTempVariable$var11$170;
																						if((0 == (j + 1))) {
																							{
																								double var51 = ((((1.0 * distributionTempVariable$v1$117) + traceTempVariable$var42$128_1) + v2[(j + 1)]) / traceTempVariable$var50$171_1);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample11Value169) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value169);
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
																		for(int index$sample27$149 = 0; index$sample27$149 < weightings.length; index$sample27$149 += 1) {
																			int distributionTempVariable$var27$151 = index$sample27$149;
																			double cv$probabilitySample27Value150 = (cv$probabilitySample11Value126 * distribution$sample27[((i - 0) / 1)][index$sample27$149]);
																			{
																				int traceTempVariable$var46$152_1 = distributionTempVariable$var27$151;
																				if(((i + 1) == (j + 1))) {
																					{
																						int traceTempVariable$var50$172_1 = distributionTempVariable$var11$127;
																						if((0 == (j + 1))) {
																							{
																								double var51 = ((((1.0 * distributionTempVariable$v1$117) + traceTempVariable$var42$128_1) + traceTempVariable$var46$152_1) / traceTempVariable$var50$172_1);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample27Value150) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value150);
																							}
																						}
																					}
																					if(!true) {
																						for(int index$sample11$173 = 0; index$sample11$173 < weightings.length; index$sample11$173 += 1) {
																							int distributionTempVariable$var11$175 = index$sample11$173;
																							double cv$probabilitySample11Value174 = (cv$probabilitySample27Value150 * distribution$sample11[index$sample11$173]);
																							{
																								int traceTempVariable$var50$176_1 = distributionTempVariable$var11$175;
																								if((0 == (j + 1))) {
																									{
																										double var51 = ((((1.0 * distributionTempVariable$v1$117) + traceTempVariable$var42$128_1) + traceTempVariable$var46$152_1) / traceTempVariable$var50$176_1);
																										double cv$weightedProbability = (Math.log(cv$probabilitySample11Value174) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																										if((cv$weightedProbability < cv$distributionAccumulator))
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																										else {
																											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																												cv$distributionAccumulator = cv$weightedProbability;
																											else
																												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																										}
																										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value174);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample5) {
							{
								if(fixedFlag$sample27) {
									{
										for(int i = 0; i < size; i += 1) {
											if(((i + 1) == j)) {
												{
													for(int index$i$194_1 = 0; index$i$194_1 < size; index$i$194_1 += 1) {
														if(((index$i$194_1 + 1) == (j + 1))) {
															if(fixedFlag$sample11) {
																{
																	if((0 == (j + 1))) {
																		{
																			double var51 = ((((1.0 * v1) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
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
																	for(int index$sample11$209 = 0; index$sample11$209 < weightings.length; index$sample11$209 += 1) {
																		int distributionTempVariable$var11$211 = index$sample11$209;
																		double cv$probabilitySample11Value210 = (1.0 * distribution$sample11[index$sample11$209]);
																		{
																			int traceTempVariable$var50$212_1 = distributionTempVariable$var11$211;
																			if((0 == (j + 1))) {
																				{
																					double var51 = ((((1.0 * v1) + v2[j]) + v2[(j + 1)]) / traceTempVariable$var50$212_1);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample11Value210) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value210);
																				}
																			}
																		}
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
											for(int index$sample27$184 = 0; index$sample27$184 < weightings.length; index$sample27$184 += 1) {
												int distributionTempVariable$var27$186 = index$sample27$184;
												double cv$probabilitySample27Value185 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$184]);
												{
													int traceTempVariable$var42$187_1 = distributionTempVariable$var27$186;
													if(((i + 1) == j)) {
														{
															int traceTempVariable$var46$195_1 = distributionTempVariable$var27$186;
															if(((i + 1) == (j + 1))) {
																if(fixedFlag$sample11) {
																	{
																		if((0 == (j + 1))) {
																			{
																				double var51 = ((((1.0 * v1) + traceTempVariable$var42$187_1) + traceTempVariable$var46$195_1) / v2[(j + 1)]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample27Value185) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value185);
																			}
																		}
																	}
																} else {
																	if(true) {
																		for(int index$sample11$214 = 0; index$sample11$214 < weightings.length; index$sample11$214 += 1) {
																			int distributionTempVariable$var11$216 = index$sample11$214;
																			double cv$probabilitySample11Value215 = (cv$probabilitySample27Value185 * distribution$sample11[index$sample11$214]);
																			{
																				int traceTempVariable$var50$217_1 = distributionTempVariable$var11$216;
																				if((0 == (j + 1))) {
																					{
																						double var51 = ((((1.0 * v1) + traceTempVariable$var42$187_1) + traceTempVariable$var46$195_1) / traceTempVariable$var50$217_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample11Value215) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value215);
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int index$i$196 = 0; index$i$196 < size; index$i$196 += 1) {
															if(!(index$i$196 == i)) {
																for(int index$sample27$197 = 0; index$sample27$197 < weightings.length; index$sample27$197 += 1) {
																	int distributionTempVariable$var27$199 = index$sample27$197;
																	double cv$probabilitySample27Value198 = (cv$probabilitySample27Value185 * distribution$sample27[((index$i$196 - 0) / 1)][index$sample27$197]);
																	{
																		int traceTempVariable$var46$200_1 = distributionTempVariable$var27$199;
																		if(((index$i$196 + 1) == (j + 1))) {
																			if(fixedFlag$sample11) {
																				{
																					if((0 == (j + 1))) {
																						{
																							double var51 = ((((1.0 * v1) + traceTempVariable$var42$187_1) + traceTempVariable$var46$200_1) / v2[(j + 1)]);
																							double cv$weightedProbability = (Math.log(cv$probabilitySample27Value198) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																							if((cv$weightedProbability < cv$distributionAccumulator))
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																							else {
																								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																									cv$distributionAccumulator = cv$weightedProbability;
																								else
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																							}
																							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value198);
																						}
																					}
																				}
																			} else {
																				if(true) {
																					for(int index$sample11$219 = 0; index$sample11$219 < weightings.length; index$sample11$219 += 1) {
																						int distributionTempVariable$var11$221 = index$sample11$219;
																						double cv$probabilitySample11Value220 = (cv$probabilitySample27Value198 * distribution$sample11[index$sample11$219]);
																						{
																							int traceTempVariable$var50$222_1 = distributionTempVariable$var11$221;
																							if((0 == (j + 1))) {
																								{
																									double var51 = ((((1.0 * v1) + traceTempVariable$var42$187_1) + traceTempVariable$var46$200_1) / traceTempVariable$var50$222_1);
																									double cv$weightedProbability = (Math.log(cv$probabilitySample11Value220) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																									if((cv$weightedProbability < cv$distributionAccumulator))
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																									else {
																										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																											cv$distributionAccumulator = cv$weightedProbability;
																										else
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																									}
																									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value220);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
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
								for(int index$sample5$178 = 0; index$sample5$178 < weightings.length; index$sample5$178 += 1) {
									int distributionTempVariable$v1$180 = index$sample5$178;
									double cv$probabilitySample5Value179 = (1.0 * distribution$sample5[index$sample5$178]);
									{
										if(fixedFlag$sample27) {
											{
												for(int i = 0; i < size; i += 1) {
													if(((i + 1) == j)) {
														{
															for(int index$i$201_1 = 0; index$i$201_1 < size; index$i$201_1 += 1) {
																if(((index$i$201_1 + 1) == (j + 1))) {
																	if(fixedFlag$sample11) {
																		{
																			if((0 == (j + 1))) {
																				{
																					double var51 = ((((1.0 * distributionTempVariable$v1$180) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample5Value179) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value179);
																				}
																			}
																		}
																	} else {
																		if(true) {
																			for(int index$sample11$224 = 0; index$sample11$224 < weightings.length; index$sample11$224 += 1) {
																				int distributionTempVariable$var11$226 = index$sample11$224;
																				double cv$probabilitySample11Value225 = (cv$probabilitySample5Value179 * distribution$sample11[index$sample11$224]);
																				{
																					int traceTempVariable$var50$227_1 = distributionTempVariable$var11$226;
																					if((0 == (j + 1))) {
																						{
																							double var51 = ((((1.0 * distributionTempVariable$v1$180) + v2[j]) + v2[(j + 1)]) / traceTempVariable$var50$227_1);
																							double cv$weightedProbability = (Math.log(cv$probabilitySample11Value225) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																							if((cv$weightedProbability < cv$distributionAccumulator))
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																							else {
																								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																									cv$distributionAccumulator = cv$weightedProbability;
																								else
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																							}
																							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value225);
																						}
																					}
																				}
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
													for(int index$sample27$190 = 0; index$sample27$190 < weightings.length; index$sample27$190 += 1) {
														int distributionTempVariable$var27$192 = index$sample27$190;
														double cv$probabilitySample27Value191 = (cv$probabilitySample5Value179 * distribution$sample27[((i - 0) / 1)][index$sample27$190]);
														{
															int traceTempVariable$var42$193_1 = distributionTempVariable$var27$192;
															if(((i + 1) == j)) {
																{
																	int traceTempVariable$var46$202_1 = distributionTempVariable$var27$192;
																	if(((i + 1) == (j + 1))) {
																		if(fixedFlag$sample11) {
																			{
																				if((0 == (j + 1))) {
																					{
																						double var51 = ((((1.0 * distributionTempVariable$v1$180) + traceTempVariable$var42$193_1) + traceTempVariable$var46$202_1) / v2[(j + 1)]);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample27Value191) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value191);
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample11$229 = 0; index$sample11$229 < weightings.length; index$sample11$229 += 1) {
																					int distributionTempVariable$var11$231 = index$sample11$229;
																					double cv$probabilitySample11Value230 = (cv$probabilitySample27Value191 * distribution$sample11[index$sample11$229]);
																					{
																						int traceTempVariable$var50$232_1 = distributionTempVariable$var11$231;
																						if((0 == (j + 1))) {
																							{
																								double var51 = ((((1.0 * distributionTempVariable$v1$180) + traceTempVariable$var42$193_1) + traceTempVariable$var46$202_1) / traceTempVariable$var50$232_1);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample11Value230) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value230);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$203 = 0; index$i$203 < size; index$i$203 += 1) {
																	if(!(index$i$203 == i)) {
																		for(int index$sample27$204 = 0; index$sample27$204 < weightings.length; index$sample27$204 += 1) {
																			int distributionTempVariable$var27$206 = index$sample27$204;
																			double cv$probabilitySample27Value205 = (cv$probabilitySample27Value191 * distribution$sample27[((index$i$203 - 0) / 1)][index$sample27$204]);
																			{
																				int traceTempVariable$var46$207_1 = distributionTempVariable$var27$206;
																				if(((index$i$203 + 1) == (j + 1))) {
																					if(fixedFlag$sample11) {
																						{
																							if((0 == (j + 1))) {
																								{
																									double var51 = ((((1.0 * distributionTempVariable$v1$180) + traceTempVariable$var42$193_1) + traceTempVariable$var46$207_1) / v2[(j + 1)]);
																									double cv$weightedProbability = (Math.log(cv$probabilitySample27Value205) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																									if((cv$weightedProbability < cv$distributionAccumulator))
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																									else {
																										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																											cv$distributionAccumulator = cv$weightedProbability;
																										else
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																									}
																									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value205);
																								}
																							}
																						}
																					} else {
																						if(true) {
																							for(int index$sample11$234 = 0; index$sample11$234 < weightings.length; index$sample11$234 += 1) {
																								int distributionTempVariable$var11$236 = index$sample11$234;
																								double cv$probabilitySample11Value235 = (cv$probabilitySample27Value205 * distribution$sample11[index$sample11$234]);
																								{
																									int traceTempVariable$var50$237_1 = distributionTempVariable$var11$236;
																									if((0 == (j + 1))) {
																										{
																											double var51 = ((((1.0 * distributionTempVariable$v1$180) + traceTempVariable$var42$193_1) + traceTempVariable$var46$207_1) / traceTempVariable$var50$237_1);
																											double cv$weightedProbability = (Math.log(cv$probabilitySample11Value235) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																											if((cv$weightedProbability < cv$distributionAccumulator))
																												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																											else {
																												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																													cv$distributionAccumulator = cv$weightedProbability;
																												else
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																											}
																											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value235);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample5) {
							{
								if(fixedFlag$sample11) {
									{
										if((0 == j)) {
											{
												if((0 == (j + 1))) {
													if(fixedFlag$sample27) {
														{
															for(int i = 0; i < size; i += 1) {
																if(((i + 1) == (j + 1))) {
																	{
																		double var51 = ((((1.0 * v1) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																		double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
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
																for(int index$sample27$267 = 0; index$sample27$267 < weightings.length; index$sample27$267 += 1) {
																	int distributionTempVariable$var27$269 = index$sample27$267;
																	double cv$probabilitySample27Value268 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$267]);
																	{
																		int traceTempVariable$var50$270_1 = distributionTempVariable$var27$269;
																		if(((i + 1) == (j + 1))) {
																			{
																				double var51 = ((((1.0 * v1) + v2[j]) + v2[(j + 1)]) / traceTempVariable$var50$270_1);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample27Value268) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value268);
																			}
																		}
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
										for(int index$sample11$244 = 0; index$sample11$244 < weightings.length; index$sample11$244 += 1) {
											int distributionTempVariable$var11$246 = index$sample11$244;
											double cv$probabilitySample11Value245 = (1.0 * distribution$sample11[index$sample11$244]);
											{
												int traceTempVariable$var42$247_1 = distributionTempVariable$var11$246;
												if((0 == j)) {
													{
														int traceTempVariable$var46$254_1 = distributionTempVariable$var11$246;
														if((0 == (j + 1))) {
															if(fixedFlag$sample27) {
																{
																	for(int i = 0; i < size; i += 1) {
																		if(((i + 1) == (j + 1))) {
																			{
																				double var51 = ((((1.0 * v1) + traceTempVariable$var42$247_1) + traceTempVariable$var46$254_1) / v2[(j + 1)]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample11Value245) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value245);
																			}
																		}
																	}
																}
															} else {
																for(int i = 0; i < size; i += 1) {
																	if(true) {
																		for(int index$sample27$273 = 0; index$sample27$273 < weightings.length; index$sample27$273 += 1) {
																			int distributionTempVariable$var27$275 = index$sample27$273;
																			double cv$probabilitySample27Value274 = (cv$probabilitySample11Value245 * distribution$sample27[((i - 0) / 1)][index$sample27$273]);
																			{
																				int traceTempVariable$var50$276_1 = distributionTempVariable$var27$275;
																				if(((i + 1) == (j + 1))) {
																					{
																						double var51 = ((((1.0 * v1) + traceTempVariable$var42$247_1) + traceTempVariable$var46$254_1) / traceTempVariable$var50$276_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample27Value274) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value274);
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													if(!true) {
														for(int index$sample11$255 = 0; index$sample11$255 < weightings.length; index$sample11$255 += 1) {
															int distributionTempVariable$var11$257 = index$sample11$255;
															double cv$probabilitySample11Value256 = (cv$probabilitySample11Value245 * distribution$sample11[index$sample11$255]);
															{
																int traceTempVariable$var46$258_1 = distributionTempVariable$var11$257;
																if((0 == (j + 1))) {
																	if(fixedFlag$sample27) {
																		{
																			for(int i = 0; i < size; i += 1) {
																				if(((i + 1) == (j + 1))) {
																					{
																						double var51 = ((((1.0 * v1) + traceTempVariable$var42$247_1) + traceTempVariable$var46$258_1) / v2[(j + 1)]);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample11Value256) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value256);
																					}
																				}
																			}
																		}
																	} else {
																		for(int i = 0; i < size; i += 1) {
																			if(true) {
																				for(int index$sample27$279 = 0; index$sample27$279 < weightings.length; index$sample27$279 += 1) {
																					int distributionTempVariable$var27$281 = index$sample27$279;
																					double cv$probabilitySample27Value280 = (cv$probabilitySample11Value256 * distribution$sample27[((i - 0) / 1)][index$sample27$279]);
																					{
																						int traceTempVariable$var50$282_1 = distributionTempVariable$var27$281;
																						if(((i + 1) == (j + 1))) {
																							{
																								double var51 = ((((1.0 * v1) + traceTempVariable$var42$247_1) + traceTempVariable$var46$258_1) / traceTempVariable$var50$282_1);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample27Value280) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value280);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
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
								for(int index$sample5$239 = 0; index$sample5$239 < weightings.length; index$sample5$239 += 1) {
									int distributionTempVariable$v1$241 = index$sample5$239;
									double cv$probabilitySample5Value240 = (1.0 * distribution$sample5[index$sample5$239]);
									{
										if(fixedFlag$sample11) {
											{
												if((0 == j)) {
													{
														if((0 == (j + 1))) {
															if(fixedFlag$sample27) {
																{
																	for(int i = 0; i < size; i += 1) {
																		if(((i + 1) == (j + 1))) {
																			{
																				double var51 = ((((1.0 * distributionTempVariable$v1$241) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample5Value240) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value240);
																			}
																		}
																	}
																}
															} else {
																for(int i = 0; i < size; i += 1) {
																	if(true) {
																		for(int index$sample27$285 = 0; index$sample27$285 < weightings.length; index$sample27$285 += 1) {
																			int distributionTempVariable$var27$287 = index$sample27$285;
																			double cv$probabilitySample27Value286 = (cv$probabilitySample5Value240 * distribution$sample27[((i - 0) / 1)][index$sample27$285]);
																			{
																				int traceTempVariable$var50$288_1 = distributionTempVariable$var27$287;
																				if(((i + 1) == (j + 1))) {
																					{
																						double var51 = ((((1.0 * distributionTempVariable$v1$241) + v2[j]) + v2[(j + 1)]) / traceTempVariable$var50$288_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample27Value286) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value286);
																					}
																				}
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
												for(int index$sample11$249 = 0; index$sample11$249 < weightings.length; index$sample11$249 += 1) {
													int distributionTempVariable$var11$251 = index$sample11$249;
													double cv$probabilitySample11Value250 = (cv$probabilitySample5Value240 * distribution$sample11[index$sample11$249]);
													{
														int traceTempVariable$var42$252_1 = distributionTempVariable$var11$251;
														if((0 == j)) {
															{
																int traceTempVariable$var46$260_1 = distributionTempVariable$var11$251;
																if((0 == (j + 1))) {
																	if(fixedFlag$sample27) {
																		{
																			for(int i = 0; i < size; i += 1) {
																				if(((i + 1) == (j + 1))) {
																					{
																						double var51 = ((((1.0 * distributionTempVariable$v1$241) + traceTempVariable$var42$252_1) + traceTempVariable$var46$260_1) / v2[(j + 1)]);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample11Value250) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value250);
																					}
																				}
																			}
																		}
																	} else {
																		for(int i = 0; i < size; i += 1) {
																			if(true) {
																				for(int index$sample27$291 = 0; index$sample27$291 < weightings.length; index$sample27$291 += 1) {
																					int distributionTempVariable$var27$293 = index$sample27$291;
																					double cv$probabilitySample27Value292 = (cv$probabilitySample11Value250 * distribution$sample27[((i - 0) / 1)][index$sample27$291]);
																					{
																						int traceTempVariable$var50$294_1 = distributionTempVariable$var27$293;
																						if(((i + 1) == (j + 1))) {
																							{
																								double var51 = ((((1.0 * distributionTempVariable$v1$241) + traceTempVariable$var42$252_1) + traceTempVariable$var46$260_1) / traceTempVariable$var50$294_1);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample27Value292) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value292);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															if(!true) {
																for(int index$sample11$261 = 0; index$sample11$261 < weightings.length; index$sample11$261 += 1) {
																	int distributionTempVariable$var11$263 = index$sample11$261;
																	double cv$probabilitySample11Value262 = (cv$probabilitySample11Value250 * distribution$sample11[index$sample11$261]);
																	{
																		int traceTempVariable$var46$264_1 = distributionTempVariable$var11$263;
																		if((0 == (j + 1))) {
																			if(fixedFlag$sample27) {
																				{
																					for(int i = 0; i < size; i += 1) {
																						if(((i + 1) == (j + 1))) {
																							{
																								double var51 = ((((1.0 * distributionTempVariable$v1$241) + traceTempVariable$var42$252_1) + traceTempVariable$var46$264_1) / v2[(j + 1)]);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample11Value262) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value262);
																							}
																						}
																					}
																				}
																			} else {
																				for(int i = 0; i < size; i += 1) {
																					if(true) {
																						for(int index$sample27$297 = 0; index$sample27$297 < weightings.length; index$sample27$297 += 1) {
																							int distributionTempVariable$var27$299 = index$sample27$297;
																							double cv$probabilitySample27Value298 = (cv$probabilitySample11Value262 * distribution$sample27[((i - 0) / 1)][index$sample27$297]);
																							{
																								int traceTempVariable$var50$300_1 = distributionTempVariable$var27$299;
																								if(((i + 1) == (j + 1))) {
																									{
																										double var51 = ((((1.0 * distributionTempVariable$v1$241) + traceTempVariable$var42$252_1) + traceTempVariable$var46$264_1) / traceTempVariable$var50$300_1);
																										double cv$weightedProbability = (Math.log(cv$probabilitySample27Value298) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																										if((cv$weightedProbability < cv$distributionAccumulator))
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																										else {
																											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																												cv$distributionAccumulator = cv$weightedProbability;
																											else
																												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																										}
																										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value298);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample5) {
							{
								if(fixedFlag$sample27) {
									{
										for(int i = 0; i < size; i += 1) {
											if(((i + 1) == j)) {
												if(fixedFlag$sample11) {
													{
														if((0 == (j + 1))) {
															{
																for(int index$i$338_1 = 0; index$i$338_1 < size; index$i$338_1 += 1) {
																	if(((index$i$338_1 + 1) == (j + 1))) {
																		{
																			double var51 = ((((1.0 * v1) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
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
													if(true) {
														for(int index$sample11$319 = 0; index$sample11$319 < weightings.length; index$sample11$319 += 1) {
															int distributionTempVariable$var11$321 = index$sample11$319;
															double cv$probabilitySample11Value320 = (1.0 * distribution$sample11[index$sample11$319]);
															{
																int traceTempVariable$var46$322_1 = distributionTempVariable$var11$321;
																if((0 == (j + 1))) {
																	{
																		for(int index$i$339_1 = 0; index$i$339_1 < size; index$i$339_1 += 1) {
																			if(((index$i$339_1 + 1) == (j + 1))) {
																				{
																					double var51 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var46$322_1) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample11Value320) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value320);
																				}
																			}
																		}
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
											for(int index$sample27$308 = 0; index$sample27$308 < weightings.length; index$sample27$308 += 1) {
												int distributionTempVariable$var27$310 = index$sample27$308;
												double cv$probabilitySample27Value309 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$308]);
												{
													int traceTempVariable$var42$311_1 = distributionTempVariable$var27$310;
													if(((i + 1) == j)) {
														if(fixedFlag$sample11) {
															{
																if((0 == (j + 1))) {
																	{
																		int traceTempVariable$var50$340_1 = distributionTempVariable$var27$310;
																		if(((i + 1) == (j + 1))) {
																			{
																				double var51 = ((((1.0 * v1) + traceTempVariable$var42$311_1) + v2[(j + 1)]) / traceTempVariable$var50$340_1);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample27Value309) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value309);
																			}
																		}
																	}
																	for(int index$i$341 = 0; index$i$341 < size; index$i$341 += 1) {
																		if(!(index$i$341 == i)) {
																			for(int index$sample27$342 = 0; index$sample27$342 < weightings.length; index$sample27$342 += 1) {
																				int distributionTempVariable$var27$344 = index$sample27$342;
																				double cv$probabilitySample27Value343 = (cv$probabilitySample27Value309 * distribution$sample27[((index$i$341 - 0) / 1)][index$sample27$342]);
																				{
																					int traceTempVariable$var50$345_1 = distributionTempVariable$var27$344;
																					if(((index$i$341 + 1) == (j + 1))) {
																						{
																							double var51 = ((((1.0 * v1) + traceTempVariable$var42$311_1) + v2[(j + 1)]) / traceTempVariable$var50$345_1);
																							double cv$weightedProbability = (Math.log(cv$probabilitySample27Value343) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																							if((cv$weightedProbability < cv$distributionAccumulator))
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																							else {
																								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																									cv$distributionAccumulator = cv$weightedProbability;
																								else
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																							}
																							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value343);
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
																for(int index$sample11$324 = 0; index$sample11$324 < weightings.length; index$sample11$324 += 1) {
																	int distributionTempVariable$var11$326 = index$sample11$324;
																	double cv$probabilitySample11Value325 = (cv$probabilitySample27Value309 * distribution$sample11[index$sample11$324]);
																	{
																		int traceTempVariable$var46$327_1 = distributionTempVariable$var11$326;
																		if((0 == (j + 1))) {
																			{
																				int traceTempVariable$var50$346_1 = distributionTempVariable$var27$310;
																				if(((i + 1) == (j + 1))) {
																					{
																						double var51 = ((((1.0 * v1) + traceTempVariable$var42$311_1) + traceTempVariable$var46$327_1) / traceTempVariable$var50$346_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample11Value325) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value325);
																					}
																				}
																			}
																			for(int index$i$347 = 0; index$i$347 < size; index$i$347 += 1) {
																				if(!(index$i$347 == i)) {
																					for(int index$sample27$348 = 0; index$sample27$348 < weightings.length; index$sample27$348 += 1) {
																						int distributionTempVariable$var27$350 = index$sample27$348;
																						double cv$probabilitySample27Value349 = (cv$probabilitySample11Value325 * distribution$sample27[((index$i$347 - 0) / 1)][index$sample27$348]);
																						{
																							int traceTempVariable$var50$351_1 = distributionTempVariable$var27$350;
																							if(((index$i$347 + 1) == (j + 1))) {
																								{
																									double var51 = ((((1.0 * v1) + traceTempVariable$var42$311_1) + traceTempVariable$var46$327_1) / traceTempVariable$var50$351_1);
																									double cv$weightedProbability = (Math.log(cv$probabilitySample27Value349) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																									if((cv$weightedProbability < cv$distributionAccumulator))
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																									else {
																										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																											cv$distributionAccumulator = cv$weightedProbability;
																										else
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																									}
																									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value349);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
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
								for(int index$sample5$302 = 0; index$sample5$302 < weightings.length; index$sample5$302 += 1) {
									int distributionTempVariable$v1$304 = index$sample5$302;
									double cv$probabilitySample5Value303 = (1.0 * distribution$sample5[index$sample5$302]);
									{
										if(fixedFlag$sample27) {
											{
												for(int i = 0; i < size; i += 1) {
													if(((i + 1) == j)) {
														if(fixedFlag$sample11) {
															{
																if((0 == (j + 1))) {
																	{
																		for(int index$i$352_1 = 0; index$i$352_1 < size; index$i$352_1 += 1) {
																			if(((index$i$352_1 + 1) == (j + 1))) {
																				{
																					double var51 = ((((1.0 * distributionTempVariable$v1$304) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample5Value303) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value303);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															if(true) {
																for(int index$sample11$329 = 0; index$sample11$329 < weightings.length; index$sample11$329 += 1) {
																	int distributionTempVariable$var11$331 = index$sample11$329;
																	double cv$probabilitySample11Value330 = (cv$probabilitySample5Value303 * distribution$sample11[index$sample11$329]);
																	{
																		int traceTempVariable$var46$332_1 = distributionTempVariable$var11$331;
																		if((0 == (j + 1))) {
																			{
																				for(int index$i$353_1 = 0; index$i$353_1 < size; index$i$353_1 += 1) {
																					if(((index$i$353_1 + 1) == (j + 1))) {
																						{
																							double var51 = ((((1.0 * distributionTempVariable$v1$304) + v2[j]) + traceTempVariable$var46$332_1) / v2[(j + 1)]);
																							double cv$weightedProbability = (Math.log(cv$probabilitySample11Value330) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																							if((cv$weightedProbability < cv$distributionAccumulator))
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																							else {
																								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																									cv$distributionAccumulator = cv$weightedProbability;
																								else
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																							}
																							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value330);
																						}
																					}
																				}
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
													for(int index$sample27$314 = 0; index$sample27$314 < weightings.length; index$sample27$314 += 1) {
														int distributionTempVariable$var27$316 = index$sample27$314;
														double cv$probabilitySample27Value315 = (cv$probabilitySample5Value303 * distribution$sample27[((i - 0) / 1)][index$sample27$314]);
														{
															int traceTempVariable$var42$317_1 = distributionTempVariable$var27$316;
															if(((i + 1) == j)) {
																if(fixedFlag$sample11) {
																	{
																		if((0 == (j + 1))) {
																			{
																				int traceTempVariable$var50$354_1 = distributionTempVariable$var27$316;
																				if(((i + 1) == (j + 1))) {
																					{
																						double var51 = ((((1.0 * distributionTempVariable$v1$304) + traceTempVariable$var42$317_1) + v2[(j + 1)]) / traceTempVariable$var50$354_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample27Value315) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value315);
																					}
																				}
																			}
																			for(int index$i$355 = 0; index$i$355 < size; index$i$355 += 1) {
																				if(!(index$i$355 == i)) {
																					for(int index$sample27$356 = 0; index$sample27$356 < weightings.length; index$sample27$356 += 1) {
																						int distributionTempVariable$var27$358 = index$sample27$356;
																						double cv$probabilitySample27Value357 = (cv$probabilitySample27Value315 * distribution$sample27[((index$i$355 - 0) / 1)][index$sample27$356]);
																						{
																							int traceTempVariable$var50$359_1 = distributionTempVariable$var27$358;
																							if(((index$i$355 + 1) == (j + 1))) {
																								{
																									double var51 = ((((1.0 * distributionTempVariable$v1$304) + traceTempVariable$var42$317_1) + v2[(j + 1)]) / traceTempVariable$var50$359_1);
																									double cv$weightedProbability = (Math.log(cv$probabilitySample27Value357) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																									if((cv$weightedProbability < cv$distributionAccumulator))
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																									else {
																										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																											cv$distributionAccumulator = cv$weightedProbability;
																										else
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																									}
																									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value357);
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
																		for(int index$sample11$334 = 0; index$sample11$334 < weightings.length; index$sample11$334 += 1) {
																			int distributionTempVariable$var11$336 = index$sample11$334;
																			double cv$probabilitySample11Value335 = (cv$probabilitySample27Value315 * distribution$sample11[index$sample11$334]);
																			{
																				int traceTempVariable$var46$337_1 = distributionTempVariable$var11$336;
																				if((0 == (j + 1))) {
																					{
																						int traceTempVariable$var50$360_1 = distributionTempVariable$var27$316;
																						if(((i + 1) == (j + 1))) {
																							{
																								double var51 = ((((1.0 * distributionTempVariable$v1$304) + traceTempVariable$var42$317_1) + traceTempVariable$var46$337_1) / traceTempVariable$var50$360_1);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample11Value335) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value335);
																							}
																						}
																					}
																					for(int index$i$361 = 0; index$i$361 < size; index$i$361 += 1) {
																						if(!(index$i$361 == i)) {
																							for(int index$sample27$362 = 0; index$sample27$362 < weightings.length; index$sample27$362 += 1) {
																								int distributionTempVariable$var27$364 = index$sample27$362;
																								double cv$probabilitySample27Value363 = (cv$probabilitySample11Value335 * distribution$sample27[((index$i$361 - 0) / 1)][index$sample27$362]);
																								{
																									int traceTempVariable$var50$365_1 = distributionTempVariable$var27$364;
																									if(((index$i$361 + 1) == (j + 1))) {
																										{
																											double var51 = ((((1.0 * distributionTempVariable$v1$304) + traceTempVariable$var42$317_1) + traceTempVariable$var46$337_1) / traceTempVariable$var50$365_1);
																											double cv$weightedProbability = (Math.log(cv$probabilitySample27Value363) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																											if((cv$weightedProbability < cv$distributionAccumulator))
																												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																											else {
																												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																													cv$distributionAccumulator = cv$weightedProbability;
																												else
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																											}
																											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value363);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample5) {
							{
								if(fixedFlag$sample11) {
									{
										if((0 == j)) {
											if(fixedFlag$sample27) {
												{
													for(int i = 0; i < size; i += 1) {
														if(((i + 1) == (j + 1))) {
															{
																for(int index$i$405_1 = 0; index$i$405_1 < size; index$i$405_1 += 1) {
																	if(((index$i$405_1 + 1) == (j + 1))) {
																		{
																			double var51 = ((((1.0 * v1) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
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
														for(int index$sample27$383 = 0; index$sample27$383 < weightings.length; index$sample27$383 += 1) {
															int distributionTempVariable$var27$385 = index$sample27$383;
															double cv$probabilitySample27Value384 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$383]);
															{
																int traceTempVariable$var46$386_1 = distributionTempVariable$var27$385;
																if(((i + 1) == (j + 1))) {
																	{
																		int traceTempVariable$var50$406_1 = distributionTempVariable$var27$385;
																		if(((i + 1) == (j + 1))) {
																			{
																				double var51 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var46$386_1) / traceTempVariable$var50$406_1);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample27Value384) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value384);
																			}
																		}
																	}
																	for(int index$i$407 = 0; index$i$407 < size; index$i$407 += 1) {
																		if(!(index$i$407 == i)) {
																			for(int index$sample27$408 = 0; index$sample27$408 < weightings.length; index$sample27$408 += 1) {
																				int distributionTempVariable$var27$410 = index$sample27$408;
																				double cv$probabilitySample27Value409 = (cv$probabilitySample27Value384 * distribution$sample27[((index$i$407 - 0) / 1)][index$sample27$408]);
																				{
																					int traceTempVariable$var50$411_1 = distributionTempVariable$var27$410;
																					if(((index$i$407 + 1) == (j + 1))) {
																						{
																							double var51 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var46$386_1) / traceTempVariable$var50$411_1);
																							double cv$weightedProbability = (Math.log(cv$probabilitySample27Value409) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																							if((cv$weightedProbability < cv$distributionAccumulator))
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																							else {
																								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																									cv$distributionAccumulator = cv$weightedProbability;
																								else
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																							}
																							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value409);
																						}
																					}
																				}
																			}
																		}
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
										for(int index$sample11$372 = 0; index$sample11$372 < weightings.length; index$sample11$372 += 1) {
											int distributionTempVariable$var11$374 = index$sample11$372;
											double cv$probabilitySample11Value373 = (1.0 * distribution$sample11[index$sample11$372]);
											{
												int traceTempVariable$var42$375_1 = distributionTempVariable$var11$374;
												if((0 == j)) {
													if(fixedFlag$sample27) {
														{
															for(int i = 0; i < size; i += 1) {
																if(((i + 1) == (j + 1))) {
																	{
																		for(int index$i$412_1 = 0; index$i$412_1 < size; index$i$412_1 += 1) {
																			if(((index$i$412_1 + 1) == (j + 1))) {
																				{
																					double var51 = ((((1.0 * v1) + traceTempVariable$var42$375_1) + v2[(j + 1)]) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample11Value373) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value373);
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
																for(int index$sample27$389 = 0; index$sample27$389 < weightings.length; index$sample27$389 += 1) {
																	int distributionTempVariable$var27$391 = index$sample27$389;
																	double cv$probabilitySample27Value390 = (cv$probabilitySample11Value373 * distribution$sample27[((i - 0) / 1)][index$sample27$389]);
																	{
																		int traceTempVariable$var46$392_1 = distributionTempVariable$var27$391;
																		if(((i + 1) == (j + 1))) {
																			{
																				int traceTempVariable$var50$413_1 = distributionTempVariable$var27$391;
																				if(((i + 1) == (j + 1))) {
																					{
																						double var51 = ((((1.0 * v1) + traceTempVariable$var42$375_1) + traceTempVariable$var46$392_1) / traceTempVariable$var50$413_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample27Value390) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value390);
																					}
																				}
																			}
																			for(int index$i$414 = 0; index$i$414 < size; index$i$414 += 1) {
																				if(!(index$i$414 == i)) {
																					for(int index$sample27$415 = 0; index$sample27$415 < weightings.length; index$sample27$415 += 1) {
																						int distributionTempVariable$var27$417 = index$sample27$415;
																						double cv$probabilitySample27Value416 = (cv$probabilitySample27Value390 * distribution$sample27[((index$i$414 - 0) / 1)][index$sample27$415]);
																						{
																							int traceTempVariable$var50$418_1 = distributionTempVariable$var27$417;
																							if(((index$i$414 + 1) == (j + 1))) {
																								{
																									double var51 = ((((1.0 * v1) + traceTempVariable$var42$375_1) + traceTempVariable$var46$392_1) / traceTempVariable$var50$418_1);
																									double cv$weightedProbability = (Math.log(cv$probabilitySample27Value416) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																									if((cv$weightedProbability < cv$distributionAccumulator))
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																									else {
																										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																											cv$distributionAccumulator = cv$weightedProbability;
																										else
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																									}
																									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value416);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
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
								for(int index$sample5$367 = 0; index$sample5$367 < weightings.length; index$sample5$367 += 1) {
									int distributionTempVariable$v1$369 = index$sample5$367;
									double cv$probabilitySample5Value368 = (1.0 * distribution$sample5[index$sample5$367]);
									{
										if(fixedFlag$sample11) {
											{
												if((0 == j)) {
													if(fixedFlag$sample27) {
														{
															for(int i = 0; i < size; i += 1) {
																if(((i + 1) == (j + 1))) {
																	{
																		for(int index$i$419_1 = 0; index$i$419_1 < size; index$i$419_1 += 1) {
																			if(((index$i$419_1 + 1) == (j + 1))) {
																				{
																					double var51 = ((((1.0 * distributionTempVariable$v1$369) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample5Value368) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value368);
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
																for(int index$sample27$395 = 0; index$sample27$395 < weightings.length; index$sample27$395 += 1) {
																	int distributionTempVariable$var27$397 = index$sample27$395;
																	double cv$probabilitySample27Value396 = (cv$probabilitySample5Value368 * distribution$sample27[((i - 0) / 1)][index$sample27$395]);
																	{
																		int traceTempVariable$var46$398_1 = distributionTempVariable$var27$397;
																		if(((i + 1) == (j + 1))) {
																			{
																				int traceTempVariable$var50$420_1 = distributionTempVariable$var27$397;
																				if(((i + 1) == (j + 1))) {
																					{
																						double var51 = ((((1.0 * distributionTempVariable$v1$369) + v2[j]) + traceTempVariable$var46$398_1) / traceTempVariable$var50$420_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample27Value396) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value396);
																					}
																				}
																			}
																			for(int index$i$421 = 0; index$i$421 < size; index$i$421 += 1) {
																				if(!(index$i$421 == i)) {
																					for(int index$sample27$422 = 0; index$sample27$422 < weightings.length; index$sample27$422 += 1) {
																						int distributionTempVariable$var27$424 = index$sample27$422;
																						double cv$probabilitySample27Value423 = (cv$probabilitySample27Value396 * distribution$sample27[((index$i$421 - 0) / 1)][index$sample27$422]);
																						{
																							int traceTempVariable$var50$425_1 = distributionTempVariable$var27$424;
																							if(((index$i$421 + 1) == (j + 1))) {
																								{
																									double var51 = ((((1.0 * distributionTempVariable$v1$369) + v2[j]) + traceTempVariable$var46$398_1) / traceTempVariable$var50$425_1);
																									double cv$weightedProbability = (Math.log(cv$probabilitySample27Value423) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																									if((cv$weightedProbability < cv$distributionAccumulator))
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																									else {
																										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																											cv$distributionAccumulator = cv$weightedProbability;
																										else
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																									}
																									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value423);
																								}
																							}
																						}
																					}
																				}
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
												for(int index$sample11$377 = 0; index$sample11$377 < weightings.length; index$sample11$377 += 1) {
													int distributionTempVariable$var11$379 = index$sample11$377;
													double cv$probabilitySample11Value378 = (cv$probabilitySample5Value368 * distribution$sample11[index$sample11$377]);
													{
														int traceTempVariable$var42$380_1 = distributionTempVariable$var11$379;
														if((0 == j)) {
															if(fixedFlag$sample27) {
																{
																	for(int i = 0; i < size; i += 1) {
																		if(((i + 1) == (j + 1))) {
																			{
																				for(int index$i$426_1 = 0; index$i$426_1 < size; index$i$426_1 += 1) {
																					if(((index$i$426_1 + 1) == (j + 1))) {
																						{
																							double var51 = ((((1.0 * distributionTempVariable$v1$369) + traceTempVariable$var42$380_1) + v2[(j + 1)]) / v2[(j + 1)]);
																							double cv$weightedProbability = (Math.log(cv$probabilitySample11Value378) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																							if((cv$weightedProbability < cv$distributionAccumulator))
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																							else {
																								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																									cv$distributionAccumulator = cv$weightedProbability;
																								else
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																							}
																							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value378);
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
																		for(int index$sample27$401 = 0; index$sample27$401 < weightings.length; index$sample27$401 += 1) {
																			int distributionTempVariable$var27$403 = index$sample27$401;
																			double cv$probabilitySample27Value402 = (cv$probabilitySample11Value378 * distribution$sample27[((i - 0) / 1)][index$sample27$401]);
																			{
																				int traceTempVariable$var46$404_1 = distributionTempVariable$var27$403;
																				if(((i + 1) == (j + 1))) {
																					{
																						int traceTempVariable$var50$427_1 = distributionTempVariable$var27$403;
																						if(((i + 1) == (j + 1))) {
																							{
																								double var51 = ((((1.0 * distributionTempVariable$v1$369) + traceTempVariable$var42$380_1) + traceTempVariable$var46$404_1) / traceTempVariable$var50$427_1);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample27Value402) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value402);
																							}
																						}
																					}
																					for(int index$i$428 = 0; index$i$428 < size; index$i$428 += 1) {
																						if(!(index$i$428 == i)) {
																							for(int index$sample27$429 = 0; index$sample27$429 < weightings.length; index$sample27$429 += 1) {
																								int distributionTempVariable$var27$431 = index$sample27$429;
																								double cv$probabilitySample27Value430 = (cv$probabilitySample27Value402 * distribution$sample27[((index$i$428 - 0) / 1)][index$sample27$429]);
																								{
																									int traceTempVariable$var50$432_1 = distributionTempVariable$var27$431;
																									if(((index$i$428 + 1) == (j + 1))) {
																										{
																											double var51 = ((((1.0 * distributionTempVariable$v1$369) + traceTempVariable$var42$380_1) + traceTempVariable$var46$404_1) / traceTempVariable$var50$432_1);
																											double cv$weightedProbability = (Math.log(cv$probabilitySample27Value430) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																											if((cv$weightedProbability < cv$distributionAccumulator))
																												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																											else {
																												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																													cv$distributionAccumulator = cv$weightedProbability;
																												else
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																											}
																											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value430);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample5) {
							{
								if(fixedFlag$sample27) {
									{
										for(int i = 0; i < size; i += 1) {
											if(((i + 1) == j)) {
												{
													for(int index$i$450_1 = 0; index$i$450_1 < size; index$i$450_1 += 1) {
														if(((index$i$450_1 + 1) == (j + 1))) {
															{
																for(int index$i$464_1 = 0; index$i$464_1 < size; index$i$464_1 += 1) {
																	if(((index$i$464_1 + 1) == (j + 1))) {
																		{
																			double var51 = ((((1.0 * v1) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
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
											}
										}
									}
								} else {
									for(int i = 0; i < size; i += 1) {
										if(true) {
											for(int index$sample27$440 = 0; index$sample27$440 < weightings.length; index$sample27$440 += 1) {
												int distributionTempVariable$var27$442 = index$sample27$440;
												double cv$probabilitySample27Value441 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$440]);
												{
													int traceTempVariable$var42$443_1 = distributionTempVariable$var27$442;
													if(((i + 1) == j)) {
														{
															int traceTempVariable$var46$451_1 = distributionTempVariable$var27$442;
															if(((i + 1) == (j + 1))) {
																{
																	int traceTempVariable$var50$465_1 = distributionTempVariable$var27$442;
																	if(((i + 1) == (j + 1))) {
																		{
																			double var51 = ((((1.0 * v1) + traceTempVariable$var42$443_1) + traceTempVariable$var46$451_1) / traceTempVariable$var50$465_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample27Value441) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value441);
																		}
																	}
																}
																for(int index$i$466 = 0; index$i$466 < size; index$i$466 += 1) {
																	if(!(index$i$466 == i)) {
																		for(int index$sample27$467 = 0; index$sample27$467 < weightings.length; index$sample27$467 += 1) {
																			int distributionTempVariable$var27$469 = index$sample27$467;
																			double cv$probabilitySample27Value468 = (cv$probabilitySample27Value441 * distribution$sample27[((index$i$466 - 0) / 1)][index$sample27$467]);
																			{
																				int traceTempVariable$var50$470_1 = distributionTempVariable$var27$469;
																				if(((index$i$466 + 1) == (j + 1))) {
																					{
																						double var51 = ((((1.0 * v1) + traceTempVariable$var42$443_1) + traceTempVariable$var46$451_1) / traceTempVariable$var50$470_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample27Value468) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value468);
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int index$i$452 = 0; index$i$452 < size; index$i$452 += 1) {
															if(!(index$i$452 == i)) {
																for(int index$sample27$453 = 0; index$sample27$453 < weightings.length; index$sample27$453 += 1) {
																	int distributionTempVariable$var27$455 = index$sample27$453;
																	double cv$probabilitySample27Value454 = (cv$probabilitySample27Value441 * distribution$sample27[((index$i$452 - 0) / 1)][index$sample27$453]);
																	{
																		int traceTempVariable$var46$456_1 = distributionTempVariable$var27$455;
																		if(((index$i$452 + 1) == (j + 1))) {
																			{
																				int traceTempVariable$var50$471_1 = distributionTempVariable$var27$442;
																				if(((i + 1) == (j + 1))) {
																					{
																						double var51 = ((((1.0 * v1) + traceTempVariable$var42$443_1) + traceTempVariable$var46$456_1) / traceTempVariable$var50$471_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample27Value454) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value454);
																					}
																				}
																			}
																			{
																				int traceTempVariable$var50$472_1 = distributionTempVariable$var27$455;
																				if(((index$i$452 + 1) == (j + 1))) {
																					{
																						double var51 = ((((1.0 * v1) + traceTempVariable$var42$443_1) + traceTempVariable$var46$456_1) / traceTempVariable$var50$472_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample27Value454) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value454);
																					}
																				}
																			}
																			for(int index$i$473 = 0; index$i$473 < size; index$i$473 += 1) {
																				if((!(index$i$473 == i) && !(index$i$473 == index$i$452))) {
																					for(int index$sample27$474 = 0; index$sample27$474 < weightings.length; index$sample27$474 += 1) {
																						int distributionTempVariable$var27$476 = index$sample27$474;
																						double cv$probabilitySample27Value475 = (cv$probabilitySample27Value454 * distribution$sample27[((index$i$473 - 0) / 1)][index$sample27$474]);
																						{
																							int traceTempVariable$var50$477_1 = distributionTempVariable$var27$476;
																							if(((index$i$473 + 1) == (j + 1))) {
																								{
																									double var51 = ((((1.0 * v1) + traceTempVariable$var42$443_1) + traceTempVariable$var46$456_1) / traceTempVariable$var50$477_1);
																									double cv$weightedProbability = (Math.log(cv$probabilitySample27Value475) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																									if((cv$weightedProbability < cv$distributionAccumulator))
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																									else {
																										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																											cv$distributionAccumulator = cv$weightedProbability;
																										else
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																									}
																									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value475);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
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
								for(int index$sample5$434 = 0; index$sample5$434 < weightings.length; index$sample5$434 += 1) {
									int distributionTempVariable$v1$436 = index$sample5$434;
									double cv$probabilitySample5Value435 = (1.0 * distribution$sample5[index$sample5$434]);
									{
										if(fixedFlag$sample27) {
											{
												for(int i = 0; i < size; i += 1) {
													if(((i + 1) == j)) {
														{
															for(int index$i$457_1 = 0; index$i$457_1 < size; index$i$457_1 += 1) {
																if(((index$i$457_1 + 1) == (j + 1))) {
																	{
																		for(int index$i$478_1 = 0; index$i$478_1 < size; index$i$478_1 += 1) {
																			if(((index$i$478_1 + 1) == (j + 1))) {
																				{
																					double var51 = ((((1.0 * distributionTempVariable$v1$436) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample5Value435) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value435);
																				}
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
													for(int index$sample27$446 = 0; index$sample27$446 < weightings.length; index$sample27$446 += 1) {
														int distributionTempVariable$var27$448 = index$sample27$446;
														double cv$probabilitySample27Value447 = (cv$probabilitySample5Value435 * distribution$sample27[((i - 0) / 1)][index$sample27$446]);
														{
															int traceTempVariable$var42$449_1 = distributionTempVariable$var27$448;
															if(((i + 1) == j)) {
																{
																	int traceTempVariable$var46$458_1 = distributionTempVariable$var27$448;
																	if(((i + 1) == (j + 1))) {
																		{
																			int traceTempVariable$var50$479_1 = distributionTempVariable$var27$448;
																			if(((i + 1) == (j + 1))) {
																				{
																					double var51 = ((((1.0 * distributionTempVariable$v1$436) + traceTempVariable$var42$449_1) + traceTempVariable$var46$458_1) / traceTempVariable$var50$479_1);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample27Value447) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value447);
																				}
																			}
																		}
																		for(int index$i$480 = 0; index$i$480 < size; index$i$480 += 1) {
																			if(!(index$i$480 == i)) {
																				for(int index$sample27$481 = 0; index$sample27$481 < weightings.length; index$sample27$481 += 1) {
																					int distributionTempVariable$var27$483 = index$sample27$481;
																					double cv$probabilitySample27Value482 = (cv$probabilitySample27Value447 * distribution$sample27[((index$i$480 - 0) / 1)][index$sample27$481]);
																					{
																						int traceTempVariable$var50$484_1 = distributionTempVariable$var27$483;
																						if(((index$i$480 + 1) == (j + 1))) {
																							{
																								double var51 = ((((1.0 * distributionTempVariable$v1$436) + traceTempVariable$var42$449_1) + traceTempVariable$var46$458_1) / traceTempVariable$var50$484_1);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample27Value482) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value482);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$459 = 0; index$i$459 < size; index$i$459 += 1) {
																	if(!(index$i$459 == i)) {
																		for(int index$sample27$460 = 0; index$sample27$460 < weightings.length; index$sample27$460 += 1) {
																			int distributionTempVariable$var27$462 = index$sample27$460;
																			double cv$probabilitySample27Value461 = (cv$probabilitySample27Value447 * distribution$sample27[((index$i$459 - 0) / 1)][index$sample27$460]);
																			{
																				int traceTempVariable$var46$463_1 = distributionTempVariable$var27$462;
																				if(((index$i$459 + 1) == (j + 1))) {
																					{
																						int traceTempVariable$var50$485_1 = distributionTempVariable$var27$448;
																						if(((i + 1) == (j + 1))) {
																							{
																								double var51 = ((((1.0 * distributionTempVariable$v1$436) + traceTempVariable$var42$449_1) + traceTempVariable$var46$463_1) / traceTempVariable$var50$485_1);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample27Value461) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value461);
																							}
																						}
																					}
																					{
																						int traceTempVariable$var50$486_1 = distributionTempVariable$var27$462;
																						if(((index$i$459 + 1) == (j + 1))) {
																							{
																								double var51 = ((((1.0 * distributionTempVariable$v1$436) + traceTempVariable$var42$449_1) + traceTempVariable$var46$463_1) / traceTempVariable$var50$486_1);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample27Value461) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value461);
																							}
																						}
																					}
																					for(int index$i$487 = 0; index$i$487 < size; index$i$487 += 1) {
																						if((!(index$i$487 == i) && !(index$i$487 == index$i$459))) {
																							for(int index$sample27$488 = 0; index$sample27$488 < weightings.length; index$sample27$488 += 1) {
																								int distributionTempVariable$var27$490 = index$sample27$488;
																								double cv$probabilitySample27Value489 = (cv$probabilitySample27Value461 * distribution$sample27[((index$i$487 - 0) / 1)][index$sample27$488]);
																								{
																									int traceTempVariable$var50$491_1 = distributionTempVariable$var27$490;
																									if(((index$i$487 + 1) == (j + 1))) {
																										{
																											double var51 = ((((1.0 * distributionTempVariable$v1$436) + traceTempVariable$var42$449_1) + traceTempVariable$var46$463_1) / traceTempVariable$var50$491_1);
																											double cv$weightedProbability = (Math.log(cv$probabilitySample27Value489) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
																											if((cv$weightedProbability < cv$distributionAccumulator))
																												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																											else {
																												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																													cv$distributionAccumulator = cv$weightedProbability;
																												else
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																											}
																											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value489);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
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
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample53[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample53 = ((fixedFlag$sample5 && fixedFlag$sample11) && fixedFlag$sample27);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample53[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample11() {
		if(!fixedProbFlag$sample11) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
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
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var11 = cv$sampleProbability;
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample11)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample11 = fixedFlag$sample11;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var11;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample11)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample27() {
		if(!fixedProbFlag$sample27) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < size; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$i$1 = i;
				{
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
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample27[((i - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample27 = fixedFlag$sample27;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < size; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample27[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample5() {
		if(!fixedProbFlag$sample5) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
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
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$v1 = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample5 = fixedFlag$sample5;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$v1;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < size; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = v[j];
						{
							{
								double var51 = ((((1.0 * v1) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
								double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var51:(1.0 - var51))));
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
				logProbability$sample53[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample53 = ((fixedFlag$sample5 && fixedFlag$sample11) && fixedFlag$sample27);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample53[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample11() {
		if(true) {
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, weightings.length);
			}
			double[] cv$stateProbabilityLocal = cv$var11$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					int $var2915 = weightings.length;
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < $var2915))?Math.log(weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							boolean[] guard$sample11bernoulli52 = guard$sample11bernoulli52$global;
							{
								for(int j = 0; j < size; j += 1) {
									if((0 == j))
										guard$sample11bernoulli52[((j - 0) / 1)] = false;
								}
							}
							{
								for(int j = 0; j < size; j += 1) {
									if((0 == (j + 1)))
										guard$sample11bernoulli52[((j - 0) / 1)] = false;
								}
							}
							{
								for(int j = 0; j < size; j += 1) {
									if((0 == (j + 1)))
										guard$sample11bernoulli52[((j - 0) / 1)] = false;
								}
							}
							{
								int traceTempVariable$var42$4_1 = cv$currentValue;
								for(int j = 0; j < size; j += 1) {
									if((0 == j)) {
										if(!guard$sample11bernoulli52[((j - 0) / 1)]) {
											guard$sample11bernoulli52[((j - 0) / 1)] = true;
											{
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if(fixedFlag$sample5) {
															{
																{
																	int traceTempVariable$var46$15_1 = cv$currentValue;
																	if((0 == (j + 1))) {
																		{
																			int traceTempVariable$var50$25_1 = cv$currentValue;
																			if((0 == (j + 1))) {
																				{
																					{
																						{
																							double var51 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var46$15_1) / traceTempVariable$var50$25_1);
																							if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$26 = 0; index$sample11$26 < weightings.length; index$sample11$26 += 1) {
																				int distributionTempVariable$var11$28 = index$sample11$26;
																				double cv$probabilitySample11Value27 = (1.0 * distribution$sample11[index$sample11$26]);
																				{
																					int traceTempVariable$var50$29_1 = distributionTempVariable$var11$28;
																					if((0 == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var46$15_1) / traceTempVariable$var50$29_1);
																									if(((Math.log(cv$probabilitySample11Value27) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value27) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value27) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value27) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value27) + Math.log((v[j]?var51:(1.0 - var51)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value27);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																if(!true) {
																	for(int index$sample11$16 = 0; index$sample11$16 < weightings.length; index$sample11$16 += 1) {
																		int distributionTempVariable$var11$18 = index$sample11$16;
																		double cv$probabilitySample11Value17 = (1.0 * distribution$sample11[index$sample11$16]);
																		{
																			int traceTempVariable$var46$19_1 = distributionTempVariable$var11$18;
																			if((0 == (j + 1))) {
																				{
																					int traceTempVariable$var50$30_1 = cv$currentValue;
																					if((0 == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var46$19_1) / traceTempVariable$var50$30_1);
																									if(((Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?var51:(1.0 - var51)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value17);
																								}
																							}
																						}
																					}
																				}
																				{
																					int traceTempVariable$var50$31_1 = distributionTempVariable$var11$18;
																					if((0 == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var46$19_1) / traceTempVariable$var50$31_1);
																									if(((Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?var51:(1.0 - var51)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value17);
																								}
																							}
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample11$32 = 0; index$sample11$32 < weightings.length; index$sample11$32 += 1) {
																						int distributionTempVariable$var11$34 = index$sample11$32;
																						double cv$probabilitySample11Value33 = (cv$probabilitySample11Value17 * distribution$sample11[index$sample11$32]);
																						{
																							int traceTempVariable$var50$35_1 = distributionTempVariable$var11$34;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var46$19_1) / traceTempVariable$var50$35_1);
																											if(((Math.log(cv$probabilitySample11Value33) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value33) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value33) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value33) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value33) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value33);
																										}
																									}
																								}
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
																for(int index$sample5$11 = 0; index$sample5$11 < weightings.length; index$sample5$11 += 1) {
																	int distributionTempVariable$v1$13 = index$sample5$11;
																	double cv$probabilitySample5Value12 = (1.0 * distribution$sample5[index$sample5$11]);
																	{
																		{
																			int traceTempVariable$var46$20_1 = cv$currentValue;
																			if((0 == (j + 1))) {
																				{
																					int traceTempVariable$var50$36_1 = cv$currentValue;
																					if((0 == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * distributionTempVariable$v1$13) + traceTempVariable$var42$4_1) + traceTempVariable$var46$20_1) / traceTempVariable$var50$36_1);
																									if(((Math.log(cv$probabilitySample5Value12) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value12) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value12) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value12) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value12) + Math.log((v[j]?var51:(1.0 - var51)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value12);
																								}
																							}
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample11$37 = 0; index$sample11$37 < weightings.length; index$sample11$37 += 1) {
																						int distributionTempVariable$var11$39 = index$sample11$37;
																						double cv$probabilitySample11Value38 = (cv$probabilitySample5Value12 * distribution$sample11[index$sample11$37]);
																						{
																							int traceTempVariable$var50$40_1 = distributionTempVariable$var11$39;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * distributionTempVariable$v1$13) + traceTempVariable$var42$4_1) + traceTempVariable$var46$20_1) / traceTempVariable$var50$40_1);
																											if(((Math.log(cv$probabilitySample11Value38) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value38) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value38) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value38) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value38) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value38);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$21 = 0; index$sample11$21 < weightings.length; index$sample11$21 += 1) {
																				int distributionTempVariable$var11$23 = index$sample11$21;
																				double cv$probabilitySample11Value22 = (cv$probabilitySample5Value12 * distribution$sample11[index$sample11$21]);
																				{
																					int traceTempVariable$var46$24_1 = distributionTempVariable$var11$23;
																					if((0 == (j + 1))) {
																						{
																							int traceTempVariable$var50$41_1 = cv$currentValue;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * distributionTempVariable$v1$13) + traceTempVariable$var42$4_1) + traceTempVariable$var46$24_1) / traceTempVariable$var50$41_1);
																											if(((Math.log(cv$probabilitySample11Value22) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value22) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value22) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value22) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value22) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value22);
																										}
																									}
																								}
																							}
																						}
																						{
																							int traceTempVariable$var50$42_1 = distributionTempVariable$var11$23;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * distributionTempVariable$v1$13) + traceTempVariable$var42$4_1) + traceTempVariable$var46$24_1) / traceTempVariable$var50$42_1);
																											if(((Math.log(cv$probabilitySample11Value22) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value22) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value22) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value22) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value22) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value22);
																										}
																									}
																								}
																							}
																						}
																						if(!true) {
																							for(int index$sample11$43 = 0; index$sample11$43 < weightings.length; index$sample11$43 += 1) {
																								int distributionTempVariable$var11$45 = index$sample11$43;
																								double cv$probabilitySample11Value44 = (cv$probabilitySample11Value22 * distribution$sample11[index$sample11$43]);
																								{
																									int traceTempVariable$var50$46_1 = distributionTempVariable$var11$45;
																									if((0 == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$13) + traceTempVariable$var42$4_1) + traceTempVariable$var46$24_1) / traceTempVariable$var50$46_1);
																													if(((Math.log(cv$probabilitySample11Value44) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value44) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value44) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value44) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value44) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value44);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample5) {
															{
																if(fixedFlag$sample27) {
																	{
																		for(int i = 0; i < size; i += 1) {
																			if(((i + 1) == (j + 1))) {
																				{
																					int traceTempVariable$var50$64_1 = cv$currentValue;
																					if((0 == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + v2[(j + 1)]) / traceTempVariable$var50$64_1);
																									if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																								}
																							}
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample11$65 = 0; index$sample11$65 < weightings.length; index$sample11$65 += 1) {
																						int distributionTempVariable$var11$67 = index$sample11$65;
																						double cv$probabilitySample11Value66 = (1.0 * distribution$sample11[index$sample11$65]);
																						{
																							int traceTempVariable$var50$68_1 = distributionTempVariable$var11$67;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + v2[(j + 1)]) / traceTempVariable$var50$68_1);
																											if(((Math.log(cv$probabilitySample11Value66) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value66) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value66) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value66) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value66) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value66);
																										}
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
																			for(int index$sample27$54 = 0; index$sample27$54 < weightings.length; index$sample27$54 += 1) {
																				int distributionTempVariable$var27$56 = index$sample27$54;
																				double cv$probabilitySample27Value55 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$54]);
																				{
																					int traceTempVariable$var46$57_1 = distributionTempVariable$var27$56;
																					if(((i + 1) == (j + 1))) {
																						{
																							int traceTempVariable$var50$69_1 = cv$currentValue;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var46$57_1) / traceTempVariable$var50$69_1);
																											if(((Math.log(cv$probabilitySample27Value55) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value55) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value55) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value55) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value55) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value55);
																										}
																									}
																								}
																							}
																						}
																						if(!true) {
																							for(int index$sample11$70 = 0; index$sample11$70 < weightings.length; index$sample11$70 += 1) {
																								int distributionTempVariable$var11$72 = index$sample11$70;
																								double cv$probabilitySample11Value71 = (cv$probabilitySample27Value55 * distribution$sample11[index$sample11$70]);
																								{
																									int traceTempVariable$var50$73_1 = distributionTempVariable$var11$72;
																									if((0 == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var46$57_1) / traceTempVariable$var50$73_1);
																													if(((Math.log(cv$probabilitySample11Value71) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value71) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value71) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value71) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value71) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value71);
																												}
																											}
																										}
																									}
																								}
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
																for(int index$sample5$48 = 0; index$sample5$48 < weightings.length; index$sample5$48 += 1) {
																	int distributionTempVariable$v1$50 = index$sample5$48;
																	double cv$probabilitySample5Value49 = (1.0 * distribution$sample5[index$sample5$48]);
																	{
																		if(fixedFlag$sample27) {
																			{
																				for(int i = 0; i < size; i += 1) {
																					if(((i + 1) == (j + 1))) {
																						{
																							int traceTempVariable$var50$74_1 = cv$currentValue;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * distributionTempVariable$v1$50) + traceTempVariable$var42$4_1) + v2[(j + 1)]) / traceTempVariable$var50$74_1);
																											if(((Math.log(cv$probabilitySample5Value49) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value49) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value49) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value49) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value49) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value49);
																										}
																									}
																								}
																							}
																						}
																						if(!true) {
																							for(int index$sample11$75 = 0; index$sample11$75 < weightings.length; index$sample11$75 += 1) {
																								int distributionTempVariable$var11$77 = index$sample11$75;
																								double cv$probabilitySample11Value76 = (cv$probabilitySample5Value49 * distribution$sample11[index$sample11$75]);
																								{
																									int traceTempVariable$var50$78_1 = distributionTempVariable$var11$77;
																									if((0 == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$50) + traceTempVariable$var42$4_1) + v2[(j + 1)]) / traceTempVariable$var50$78_1);
																													if(((Math.log(cv$probabilitySample11Value76) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value76) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value76) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value76) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value76) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value76);
																												}
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
																					for(int index$sample27$60 = 0; index$sample27$60 < weightings.length; index$sample27$60 += 1) {
																						int distributionTempVariable$var27$62 = index$sample27$60;
																						double cv$probabilitySample27Value61 = (cv$probabilitySample5Value49 * distribution$sample27[((i - 0) / 1)][index$sample27$60]);
																						{
																							int traceTempVariable$var46$63_1 = distributionTempVariable$var27$62;
																							if(((i + 1) == (j + 1))) {
																								{
																									int traceTempVariable$var50$79_1 = cv$currentValue;
																									if((0 == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$50) + traceTempVariable$var42$4_1) + traceTempVariable$var46$63_1) / traceTempVariable$var50$79_1);
																													if(((Math.log(cv$probabilitySample27Value61) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value61) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value61) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value61) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value61) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value61);
																												}
																											}
																										}
																									}
																								}
																								if(!true) {
																									for(int index$sample11$80 = 0; index$sample11$80 < weightings.length; index$sample11$80 += 1) {
																										int distributionTempVariable$var11$82 = index$sample11$80;
																										double cv$probabilitySample11Value81 = (cv$probabilitySample27Value61 * distribution$sample11[index$sample11$80]);
																										{
																											int traceTempVariable$var50$83_1 = distributionTempVariable$var11$82;
																											if((0 == (j + 1))) {
																												{
																													{
																														{
																															double var51 = ((((1.0 * distributionTempVariable$v1$50) + traceTempVariable$var42$4_1) + traceTempVariable$var46$63_1) / traceTempVariable$var50$83_1);
																															if(((Math.log(cv$probabilitySample11Value81) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value81) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value81) + Math.log((v[j]?var51:(1.0 - var51))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value81) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value81) + Math.log((v[j]?var51:(1.0 - var51)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value81);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample5) {
															{
																{
																	int traceTempVariable$var46$89_1 = cv$currentValue;
																	if((0 == (j + 1))) {
																		if(fixedFlag$sample27) {
																			{
																				for(int i = 0; i < size; i += 1) {
																					if(((i + 1) == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var46$89_1) / v2[(j + 1)]);
																									if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
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
																					for(int index$sample27$101 = 0; index$sample27$101 < weightings.length; index$sample27$101 += 1) {
																						int distributionTempVariable$var27$103 = index$sample27$101;
																						double cv$probabilitySample27Value102 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$101]);
																						{
																							int traceTempVariable$var50$104_1 = distributionTempVariable$var27$103;
																							if(((i + 1) == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var46$89_1) / traceTempVariable$var50$104_1);
																											if(((Math.log(cv$probabilitySample27Value102) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value102) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value102) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value102) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value102) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value102);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																if(!true) {
																	for(int index$sample11$90 = 0; index$sample11$90 < weightings.length; index$sample11$90 += 1) {
																		int distributionTempVariable$var11$92 = index$sample11$90;
																		double cv$probabilitySample11Value91 = (1.0 * distribution$sample11[index$sample11$90]);
																		{
																			int traceTempVariable$var46$93_1 = distributionTempVariable$var11$92;
																			if((0 == (j + 1))) {
																				if(fixedFlag$sample27) {
																					{
																						for(int i = 0; i < size; i += 1) {
																							if(((i + 1) == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var46$93_1) / v2[(j + 1)]);
																											if(((Math.log(cv$probabilitySample11Value91) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value91) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value91) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value91) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value91) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value91);
																										}
																									}
																								}
																							}
																						}
																					}
																				} else {
																					for(int i = 0; i < size; i += 1) {
																						if(true) {
																							for(int index$sample27$107 = 0; index$sample27$107 < weightings.length; index$sample27$107 += 1) {
																								int distributionTempVariable$var27$109 = index$sample27$107;
																								double cv$probabilitySample27Value108 = (cv$probabilitySample11Value91 * distribution$sample27[((i - 0) / 1)][index$sample27$107]);
																								{
																									int traceTempVariable$var50$110_1 = distributionTempVariable$var27$109;
																									if(((i + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var46$93_1) / traceTempVariable$var50$110_1);
																													if(((Math.log(cv$probabilitySample27Value108) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value108) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value108) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value108) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value108) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value108);
																												}
																											}
																										}
																									}
																								}
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
																for(int index$sample5$85 = 0; index$sample5$85 < weightings.length; index$sample5$85 += 1) {
																	int distributionTempVariable$v1$87 = index$sample5$85;
																	double cv$probabilitySample5Value86 = (1.0 * distribution$sample5[index$sample5$85]);
																	{
																		{
																			int traceTempVariable$var46$94_1 = cv$currentValue;
																			if((0 == (j + 1))) {
																				if(fixedFlag$sample27) {
																					{
																						for(int i = 0; i < size; i += 1) {
																							if(((i + 1) == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * distributionTempVariable$v1$87) + traceTempVariable$var42$4_1) + traceTempVariable$var46$94_1) / v2[(j + 1)]);
																											if(((Math.log(cv$probabilitySample5Value86) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value86) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value86) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value86) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value86) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value86);
																										}
																									}
																								}
																							}
																						}
																					}
																				} else {
																					for(int i = 0; i < size; i += 1) {
																						if(true) {
																							for(int index$sample27$113 = 0; index$sample27$113 < weightings.length; index$sample27$113 += 1) {
																								int distributionTempVariable$var27$115 = index$sample27$113;
																								double cv$probabilitySample27Value114 = (cv$probabilitySample5Value86 * distribution$sample27[((i - 0) / 1)][index$sample27$113]);
																								{
																									int traceTempVariable$var50$116_1 = distributionTempVariable$var27$115;
																									if(((i + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$87) + traceTempVariable$var42$4_1) + traceTempVariable$var46$94_1) / traceTempVariable$var50$116_1);
																													if(((Math.log(cv$probabilitySample27Value114) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value114) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value114) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value114) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value114) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value114);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$95 = 0; index$sample11$95 < weightings.length; index$sample11$95 += 1) {
																				int distributionTempVariable$var11$97 = index$sample11$95;
																				double cv$probabilitySample11Value96 = (cv$probabilitySample5Value86 * distribution$sample11[index$sample11$95]);
																				{
																					int traceTempVariable$var46$98_1 = distributionTempVariable$var11$97;
																					if((0 == (j + 1))) {
																						if(fixedFlag$sample27) {
																							{
																								for(int i = 0; i < size; i += 1) {
																									if(((i + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$87) + traceTempVariable$var42$4_1) + traceTempVariable$var46$98_1) / v2[(j + 1)]);
																													if(((Math.log(cv$probabilitySample11Value96) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value96) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value96) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value96) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value96) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value96);
																												}
																											}
																										}
																									}
																								}
																							}
																						} else {
																							for(int i = 0; i < size; i += 1) {
																								if(true) {
																									for(int index$sample27$119 = 0; index$sample27$119 < weightings.length; index$sample27$119 += 1) {
																										int distributionTempVariable$var27$121 = index$sample27$119;
																										double cv$probabilitySample27Value120 = (cv$probabilitySample11Value96 * distribution$sample27[((i - 0) / 1)][index$sample27$119]);
																										{
																											int traceTempVariable$var50$122_1 = distributionTempVariable$var27$121;
																											if(((i + 1) == (j + 1))) {
																												{
																													{
																														{
																															double var51 = ((((1.0 * distributionTempVariable$v1$87) + traceTempVariable$var42$4_1) + traceTempVariable$var46$98_1) / traceTempVariable$var50$122_1);
																															if(((Math.log(cv$probabilitySample27Value120) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value120) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value120) + Math.log((v[j]?var51:(1.0 - var51))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value120) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value120) + Math.log((v[j]?var51:(1.0 - var51)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value120);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample5) {
															{
																if(fixedFlag$sample27) {
																	{
																		for(int i = 0; i < size; i += 1) {
																			if(((i + 1) == (j + 1))) {
																				{
																					for(int index$i$140_1 = 0; index$i$140_1 < size; index$i$140_1 += 1) {
																						if(((index$i$140_1 + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + v2[(j + 1)]) / v2[(j + 1)]);
																										if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
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
																	}
																} else {
																	for(int i = 0; i < size; i += 1) {
																		if(true) {
																			for(int index$sample27$130 = 0; index$sample27$130 < weightings.length; index$sample27$130 += 1) {
																				int distributionTempVariable$var27$132 = index$sample27$130;
																				double cv$probabilitySample27Value131 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$130]);
																				{
																					int traceTempVariable$var46$133_1 = distributionTempVariable$var27$132;
																					if(((i + 1) == (j + 1))) {
																						{
																							int traceTempVariable$var50$141_1 = distributionTempVariable$var27$132;
																							if(((i + 1) == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var46$133_1) / traceTempVariable$var50$141_1);
																											if(((Math.log(cv$probabilitySample27Value131) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value131) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value131) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value131) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value131) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value131);
																										}
																									}
																								}
																							}
																						}
																						for(int index$i$142 = 0; index$i$142 < size; index$i$142 += 1) {
																							if(!(index$i$142 == i)) {
																								for(int index$sample27$143 = 0; index$sample27$143 < weightings.length; index$sample27$143 += 1) {
																									int distributionTempVariable$var27$145 = index$sample27$143;
																									double cv$probabilitySample27Value144 = (cv$probabilitySample27Value131 * distribution$sample27[((index$i$142 - 0) / 1)][index$sample27$143]);
																									{
																										int traceTempVariable$var50$146_1 = distributionTempVariable$var27$145;
																										if(((index$i$142 + 1) == (j + 1))) {
																											{
																												{
																													{
																														double var51 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var46$133_1) / traceTempVariable$var50$146_1);
																														if(((Math.log(cv$probabilitySample27Value144) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value144) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value144) + Math.log((v[j]?var51:(1.0 - var51))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value144) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value144) + Math.log((v[j]?var51:(1.0 - var51)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value144);
																													}
																												}
																											}
																										}
																									}
																								}
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
																for(int index$sample5$124 = 0; index$sample5$124 < weightings.length; index$sample5$124 += 1) {
																	int distributionTempVariable$v1$126 = index$sample5$124;
																	double cv$probabilitySample5Value125 = (1.0 * distribution$sample5[index$sample5$124]);
																	{
																		if(fixedFlag$sample27) {
																			{
																				for(int i = 0; i < size; i += 1) {
																					if(((i + 1) == (j + 1))) {
																						{
																							for(int index$i$147_1 = 0; index$i$147_1 < size; index$i$147_1 += 1) {
																								if(((index$i$147_1 + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * distributionTempVariable$v1$126) + traceTempVariable$var42$4_1) + v2[(j + 1)]) / v2[(j + 1)]);
																												if(((Math.log(cv$probabilitySample5Value125) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value125) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value125) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value125) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value125) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value125);
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
																					for(int index$sample27$136 = 0; index$sample27$136 < weightings.length; index$sample27$136 += 1) {
																						int distributionTempVariable$var27$138 = index$sample27$136;
																						double cv$probabilitySample27Value137 = (cv$probabilitySample5Value125 * distribution$sample27[((i - 0) / 1)][index$sample27$136]);
																						{
																							int traceTempVariable$var46$139_1 = distributionTempVariable$var27$138;
																							if(((i + 1) == (j + 1))) {
																								{
																									int traceTempVariable$var50$148_1 = distributionTempVariable$var27$138;
																									if(((i + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$126) + traceTempVariable$var42$4_1) + traceTempVariable$var46$139_1) / traceTempVariable$var50$148_1);
																													if(((Math.log(cv$probabilitySample27Value137) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value137) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value137) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value137) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value137) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value137);
																												}
																											}
																										}
																									}
																								}
																								for(int index$i$149 = 0; index$i$149 < size; index$i$149 += 1) {
																									if(!(index$i$149 == i)) {
																										for(int index$sample27$150 = 0; index$sample27$150 < weightings.length; index$sample27$150 += 1) {
																											int distributionTempVariable$var27$152 = index$sample27$150;
																											double cv$probabilitySample27Value151 = (cv$probabilitySample27Value137 * distribution$sample27[((index$i$149 - 0) / 1)][index$sample27$150]);
																											{
																												int traceTempVariable$var50$153_1 = distributionTempVariable$var27$152;
																												if(((index$i$149 + 1) == (j + 1))) {
																													{
																														{
																															{
																																double var51 = ((((1.0 * distributionTempVariable$v1$126) + traceTempVariable$var42$4_1) + traceTempVariable$var46$139_1) / traceTempVariable$var50$153_1);
																																if(((Math.log(cv$probabilitySample27Value151) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value151) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value151) + Math.log((v[j]?var51:(1.0 - var51))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value151) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value151) + Math.log((v[j]?var51:(1.0 - var51)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value151);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
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
							{
								int traceTempVariable$var46$5_1 = cv$currentValue;
								for(int j = 0; j < size; j += 1) {
									if((0 == (j + 1))) {
										if(!guard$sample11bernoulli52[((j - 0) / 1)]) {
											guard$sample11bernoulli52[((j - 0) / 1)] = true;
											{
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if(fixedFlag$sample5) {
															{
																{
																	int traceTempVariable$var42$159_1 = cv$currentValue;
																	if((0 == j)) {
																		{
																			int traceTempVariable$var50$169_1 = cv$currentValue;
																			if((0 == (j + 1))) {
																				{
																					{
																						{
																							double var51 = ((((1.0 * v1) + traceTempVariable$var42$159_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$169_1);
																							if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$170 = 0; index$sample11$170 < weightings.length; index$sample11$170 += 1) {
																				int distributionTempVariable$var11$172 = index$sample11$170;
																				double cv$probabilitySample11Value171 = (1.0 * distribution$sample11[index$sample11$170]);
																				{
																					int traceTempVariable$var50$173_1 = distributionTempVariable$var11$172;
																					if((0 == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * v1) + traceTempVariable$var42$159_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$173_1);
																									if(((Math.log(cv$probabilitySample11Value171) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value171) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value171) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value171) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value171) + Math.log((v[j]?var51:(1.0 - var51)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value171);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																if(!true) {
																	for(int index$sample11$160 = 0; index$sample11$160 < weightings.length; index$sample11$160 += 1) {
																		int distributionTempVariable$var11$162 = index$sample11$160;
																		double cv$probabilitySample11Value161 = (1.0 * distribution$sample11[index$sample11$160]);
																		{
																			int traceTempVariable$var42$163_1 = distributionTempVariable$var11$162;
																			if((0 == j)) {
																				{
																					int traceTempVariable$var50$174_1 = cv$currentValue;
																					if((0 == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * v1) + traceTempVariable$var42$163_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$174_1);
																									if(((Math.log(cv$probabilitySample11Value161) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value161) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value161) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value161) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value161) + Math.log((v[j]?var51:(1.0 - var51)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value161);
																								}
																							}
																						}
																					}
																				}
																				{
																					int traceTempVariable$var50$175_1 = distributionTempVariable$var11$162;
																					if((0 == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * v1) + traceTempVariable$var42$163_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$175_1);
																									if(((Math.log(cv$probabilitySample11Value161) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value161) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value161) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value161) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value161) + Math.log((v[j]?var51:(1.0 - var51)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value161);
																								}
																							}
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample11$176 = 0; index$sample11$176 < weightings.length; index$sample11$176 += 1) {
																						int distributionTempVariable$var11$178 = index$sample11$176;
																						double cv$probabilitySample11Value177 = (cv$probabilitySample11Value161 * distribution$sample11[index$sample11$176]);
																						{
																							int traceTempVariable$var50$179_1 = distributionTempVariable$var11$178;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + traceTempVariable$var42$163_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$179_1);
																											if(((Math.log(cv$probabilitySample11Value177) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value177) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value177) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value177) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value177) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value177);
																										}
																									}
																								}
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
																for(int index$sample5$155 = 0; index$sample5$155 < weightings.length; index$sample5$155 += 1) {
																	int distributionTempVariable$v1$157 = index$sample5$155;
																	double cv$probabilitySample5Value156 = (1.0 * distribution$sample5[index$sample5$155]);
																	{
																		{
																			int traceTempVariable$var42$164_1 = cv$currentValue;
																			if((0 == j)) {
																				{
																					int traceTempVariable$var50$180_1 = cv$currentValue;
																					if((0 == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * distributionTempVariable$v1$157) + traceTempVariable$var42$164_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$180_1);
																									if(((Math.log(cv$probabilitySample5Value156) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value156) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value156) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value156) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value156) + Math.log((v[j]?var51:(1.0 - var51)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value156);
																								}
																							}
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample11$181 = 0; index$sample11$181 < weightings.length; index$sample11$181 += 1) {
																						int distributionTempVariable$var11$183 = index$sample11$181;
																						double cv$probabilitySample11Value182 = (cv$probabilitySample5Value156 * distribution$sample11[index$sample11$181]);
																						{
																							int traceTempVariable$var50$184_1 = distributionTempVariable$var11$183;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * distributionTempVariable$v1$157) + traceTempVariable$var42$164_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$184_1);
																											if(((Math.log(cv$probabilitySample11Value182) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value182) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value182) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value182) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value182) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value182);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$165 = 0; index$sample11$165 < weightings.length; index$sample11$165 += 1) {
																				int distributionTempVariable$var11$167 = index$sample11$165;
																				double cv$probabilitySample11Value166 = (cv$probabilitySample5Value156 * distribution$sample11[index$sample11$165]);
																				{
																					int traceTempVariable$var42$168_1 = distributionTempVariable$var11$167;
																					if((0 == j)) {
																						{
																							int traceTempVariable$var50$185_1 = cv$currentValue;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * distributionTempVariable$v1$157) + traceTempVariable$var42$168_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$185_1);
																											if(((Math.log(cv$probabilitySample11Value166) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value166) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value166) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value166) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value166) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value166);
																										}
																									}
																								}
																							}
																						}
																						{
																							int traceTempVariable$var50$186_1 = distributionTempVariable$var11$167;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * distributionTempVariable$v1$157) + traceTempVariable$var42$168_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$186_1);
																											if(((Math.log(cv$probabilitySample11Value166) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value166) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value166) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value166) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value166) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value166);
																										}
																									}
																								}
																							}
																						}
																						if(!true) {
																							for(int index$sample11$187 = 0; index$sample11$187 < weightings.length; index$sample11$187 += 1) {
																								int distributionTempVariable$var11$189 = index$sample11$187;
																								double cv$probabilitySample11Value188 = (cv$probabilitySample11Value166 * distribution$sample11[index$sample11$187]);
																								{
																									int traceTempVariable$var50$190_1 = distributionTempVariable$var11$189;
																									if((0 == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$157) + traceTempVariable$var42$168_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$190_1);
																													if(((Math.log(cv$probabilitySample11Value188) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value188) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value188) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value188) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value188) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value188);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample5) {
															{
																if(fixedFlag$sample27) {
																	{
																		for(int i = 0; i < size; i += 1) {
																			if(((i + 1) == j)) {
																				{
																					int traceTempVariable$var50$208_1 = cv$currentValue;
																					if((0 == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var46$5_1) / traceTempVariable$var50$208_1);
																									if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																								}
																							}
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample11$209 = 0; index$sample11$209 < weightings.length; index$sample11$209 += 1) {
																						int distributionTempVariable$var11$211 = index$sample11$209;
																						double cv$probabilitySample11Value210 = (1.0 * distribution$sample11[index$sample11$209]);
																						{
																							int traceTempVariable$var50$212_1 = distributionTempVariable$var11$211;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var46$5_1) / traceTempVariable$var50$212_1);
																											if(((Math.log(cv$probabilitySample11Value210) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value210) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value210) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value210) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value210) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value210);
																										}
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
																			for(int index$sample27$198 = 0; index$sample27$198 < weightings.length; index$sample27$198 += 1) {
																				int distributionTempVariable$var27$200 = index$sample27$198;
																				double cv$probabilitySample27Value199 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$198]);
																				{
																					int traceTempVariable$var42$201_1 = distributionTempVariable$var27$200;
																					if(((i + 1) == j)) {
																						{
																							int traceTempVariable$var50$213_1 = cv$currentValue;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + traceTempVariable$var42$201_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$213_1);
																											if(((Math.log(cv$probabilitySample27Value199) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value199) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value199) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value199) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value199) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value199);
																										}
																									}
																								}
																							}
																						}
																						if(!true) {
																							for(int index$sample11$214 = 0; index$sample11$214 < weightings.length; index$sample11$214 += 1) {
																								int distributionTempVariable$var11$216 = index$sample11$214;
																								double cv$probabilitySample11Value215 = (cv$probabilitySample27Value199 * distribution$sample11[index$sample11$214]);
																								{
																									int traceTempVariable$var50$217_1 = distributionTempVariable$var11$216;
																									if((0 == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * v1) + traceTempVariable$var42$201_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$217_1);
																													if(((Math.log(cv$probabilitySample11Value215) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value215) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value215) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value215) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value215) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value215);
																												}
																											}
																										}
																									}
																								}
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
																for(int index$sample5$192 = 0; index$sample5$192 < weightings.length; index$sample5$192 += 1) {
																	int distributionTempVariable$v1$194 = index$sample5$192;
																	double cv$probabilitySample5Value193 = (1.0 * distribution$sample5[index$sample5$192]);
																	{
																		if(fixedFlag$sample27) {
																			{
																				for(int i = 0; i < size; i += 1) {
																					if(((i + 1) == j)) {
																						{
																							int traceTempVariable$var50$218_1 = cv$currentValue;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * distributionTempVariable$v1$194) + v2[j]) + traceTempVariable$var46$5_1) / traceTempVariable$var50$218_1);
																											if(((Math.log(cv$probabilitySample5Value193) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value193) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value193) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value193) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value193) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value193);
																										}
																									}
																								}
																							}
																						}
																						if(!true) {
																							for(int index$sample11$219 = 0; index$sample11$219 < weightings.length; index$sample11$219 += 1) {
																								int distributionTempVariable$var11$221 = index$sample11$219;
																								double cv$probabilitySample11Value220 = (cv$probabilitySample5Value193 * distribution$sample11[index$sample11$219]);
																								{
																									int traceTempVariable$var50$222_1 = distributionTempVariable$var11$221;
																									if((0 == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$194) + v2[j]) + traceTempVariable$var46$5_1) / traceTempVariable$var50$222_1);
																													if(((Math.log(cv$probabilitySample11Value220) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value220) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value220) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value220) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value220) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value220);
																												}
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
																					for(int index$sample27$204 = 0; index$sample27$204 < weightings.length; index$sample27$204 += 1) {
																						int distributionTempVariable$var27$206 = index$sample27$204;
																						double cv$probabilitySample27Value205 = (cv$probabilitySample5Value193 * distribution$sample27[((i - 0) / 1)][index$sample27$204]);
																						{
																							int traceTempVariable$var42$207_1 = distributionTempVariable$var27$206;
																							if(((i + 1) == j)) {
																								{
																									int traceTempVariable$var50$223_1 = cv$currentValue;
																									if((0 == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$194) + traceTempVariable$var42$207_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$223_1);
																													if(((Math.log(cv$probabilitySample27Value205) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value205) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value205) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value205) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value205) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value205);
																												}
																											}
																										}
																									}
																								}
																								if(!true) {
																									for(int index$sample11$224 = 0; index$sample11$224 < weightings.length; index$sample11$224 += 1) {
																										int distributionTempVariable$var11$226 = index$sample11$224;
																										double cv$probabilitySample11Value225 = (cv$probabilitySample27Value205 * distribution$sample11[index$sample11$224]);
																										{
																											int traceTempVariable$var50$227_1 = distributionTempVariable$var11$226;
																											if((0 == (j + 1))) {
																												{
																													{
																														{
																															double var51 = ((((1.0 * distributionTempVariable$v1$194) + traceTempVariable$var42$207_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$227_1);
																															if(((Math.log(cv$probabilitySample11Value225) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value225) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value225) + Math.log((v[j]?var51:(1.0 - var51))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value225) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value225) + Math.log((v[j]?var51:(1.0 - var51)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value225);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample5) {
															{
																{
																	int traceTempVariable$var42$233_1 = cv$currentValue;
																	if((0 == j)) {
																		if(fixedFlag$sample27) {
																			{
																				for(int i = 0; i < size; i += 1) {
																					if(((i + 1) == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * v1) + traceTempVariable$var42$233_1) + traceTempVariable$var46$5_1) / v2[(j + 1)]);
																									if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
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
																					for(int index$sample27$245 = 0; index$sample27$245 < weightings.length; index$sample27$245 += 1) {
																						int distributionTempVariable$var27$247 = index$sample27$245;
																						double cv$probabilitySample27Value246 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$245]);
																						{
																							int traceTempVariable$var50$248_1 = distributionTempVariable$var27$247;
																							if(((i + 1) == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + traceTempVariable$var42$233_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$248_1);
																											if(((Math.log(cv$probabilitySample27Value246) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value246) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value246) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value246) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value246) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value246);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																if(!true) {
																	for(int index$sample11$234 = 0; index$sample11$234 < weightings.length; index$sample11$234 += 1) {
																		int distributionTempVariable$var11$236 = index$sample11$234;
																		double cv$probabilitySample11Value235 = (1.0 * distribution$sample11[index$sample11$234]);
																		{
																			int traceTempVariable$var42$237_1 = distributionTempVariable$var11$236;
																			if((0 == j)) {
																				if(fixedFlag$sample27) {
																					{
																						for(int i = 0; i < size; i += 1) {
																							if(((i + 1) == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + traceTempVariable$var42$237_1) + traceTempVariable$var46$5_1) / v2[(j + 1)]);
																											if(((Math.log(cv$probabilitySample11Value235) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value235) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value235) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value235) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value235) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value235);
																										}
																									}
																								}
																							}
																						}
																					}
																				} else {
																					for(int i = 0; i < size; i += 1) {
																						if(true) {
																							for(int index$sample27$251 = 0; index$sample27$251 < weightings.length; index$sample27$251 += 1) {
																								int distributionTempVariable$var27$253 = index$sample27$251;
																								double cv$probabilitySample27Value252 = (cv$probabilitySample11Value235 * distribution$sample27[((i - 0) / 1)][index$sample27$251]);
																								{
																									int traceTempVariable$var50$254_1 = distributionTempVariable$var27$253;
																									if(((i + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * v1) + traceTempVariable$var42$237_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$254_1);
																													if(((Math.log(cv$probabilitySample27Value252) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value252) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value252) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value252) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value252) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value252);
																												}
																											}
																										}
																									}
																								}
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
																for(int index$sample5$229 = 0; index$sample5$229 < weightings.length; index$sample5$229 += 1) {
																	int distributionTempVariable$v1$231 = index$sample5$229;
																	double cv$probabilitySample5Value230 = (1.0 * distribution$sample5[index$sample5$229]);
																	{
																		{
																			int traceTempVariable$var42$238_1 = cv$currentValue;
																			if((0 == j)) {
																				if(fixedFlag$sample27) {
																					{
																						for(int i = 0; i < size; i += 1) {
																							if(((i + 1) == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * distributionTempVariable$v1$231) + traceTempVariable$var42$238_1) + traceTempVariable$var46$5_1) / v2[(j + 1)]);
																											if(((Math.log(cv$probabilitySample5Value230) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value230) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value230) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value230) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value230) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value230);
																										}
																									}
																								}
																							}
																						}
																					}
																				} else {
																					for(int i = 0; i < size; i += 1) {
																						if(true) {
																							for(int index$sample27$257 = 0; index$sample27$257 < weightings.length; index$sample27$257 += 1) {
																								int distributionTempVariable$var27$259 = index$sample27$257;
																								double cv$probabilitySample27Value258 = (cv$probabilitySample5Value230 * distribution$sample27[((i - 0) / 1)][index$sample27$257]);
																								{
																									int traceTempVariable$var50$260_1 = distributionTempVariable$var27$259;
																									if(((i + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$231) + traceTempVariable$var42$238_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$260_1);
																													if(((Math.log(cv$probabilitySample27Value258) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value258) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value258) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value258) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value258) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value258);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$239 = 0; index$sample11$239 < weightings.length; index$sample11$239 += 1) {
																				int distributionTempVariable$var11$241 = index$sample11$239;
																				double cv$probabilitySample11Value240 = (cv$probabilitySample5Value230 * distribution$sample11[index$sample11$239]);
																				{
																					int traceTempVariable$var42$242_1 = distributionTempVariable$var11$241;
																					if((0 == j)) {
																						if(fixedFlag$sample27) {
																							{
																								for(int i = 0; i < size; i += 1) {
																									if(((i + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$231) + traceTempVariable$var42$242_1) + traceTempVariable$var46$5_1) / v2[(j + 1)]);
																													if(((Math.log(cv$probabilitySample11Value240) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value240) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value240) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value240) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value240) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value240);
																												}
																											}
																										}
																									}
																								}
																							}
																						} else {
																							for(int i = 0; i < size; i += 1) {
																								if(true) {
																									for(int index$sample27$263 = 0; index$sample27$263 < weightings.length; index$sample27$263 += 1) {
																										int distributionTempVariable$var27$265 = index$sample27$263;
																										double cv$probabilitySample27Value264 = (cv$probabilitySample11Value240 * distribution$sample27[((i - 0) / 1)][index$sample27$263]);
																										{
																											int traceTempVariable$var50$266_1 = distributionTempVariable$var27$265;
																											if(((i + 1) == (j + 1))) {
																												{
																													{
																														{
																															double var51 = ((((1.0 * distributionTempVariable$v1$231) + traceTempVariable$var42$242_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$266_1);
																															if(((Math.log(cv$probabilitySample27Value264) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value264) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value264) + Math.log((v[j]?var51:(1.0 - var51))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value264) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value264) + Math.log((v[j]?var51:(1.0 - var51)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value264);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample5) {
															{
																if(fixedFlag$sample27) {
																	{
																		for(int i = 0; i < size; i += 1) {
																			if(((i + 1) == j)) {
																				{
																					for(int index$i$284_1 = 0; index$i$284_1 < size; index$i$284_1 += 1) {
																						if(((index$i$284_1 + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var46$5_1) / v2[(j + 1)]);
																										if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
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
																	}
																} else {
																	for(int i = 0; i < size; i += 1) {
																		if(true) {
																			for(int index$sample27$274 = 0; index$sample27$274 < weightings.length; index$sample27$274 += 1) {
																				int distributionTempVariable$var27$276 = index$sample27$274;
																				double cv$probabilitySample27Value275 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$274]);
																				{
																					int traceTempVariable$var42$277_1 = distributionTempVariable$var27$276;
																					if(((i + 1) == j)) {
																						{
																							int traceTempVariable$var50$285_1 = distributionTempVariable$var27$276;
																							if(((i + 1) == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + traceTempVariable$var42$277_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$285_1);
																											if(((Math.log(cv$probabilitySample27Value275) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value275) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value275) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value275) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value275) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value275);
																										}
																									}
																								}
																							}
																						}
																						for(int index$i$286 = 0; index$i$286 < size; index$i$286 += 1) {
																							if(!(index$i$286 == i)) {
																								for(int index$sample27$287 = 0; index$sample27$287 < weightings.length; index$sample27$287 += 1) {
																									int distributionTempVariable$var27$289 = index$sample27$287;
																									double cv$probabilitySample27Value288 = (cv$probabilitySample27Value275 * distribution$sample27[((index$i$286 - 0) / 1)][index$sample27$287]);
																									{
																										int traceTempVariable$var50$290_1 = distributionTempVariable$var27$289;
																										if(((index$i$286 + 1) == (j + 1))) {
																											{
																												{
																													{
																														double var51 = ((((1.0 * v1) + traceTempVariable$var42$277_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$290_1);
																														if(((Math.log(cv$probabilitySample27Value288) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value288) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value288) + Math.log((v[j]?var51:(1.0 - var51))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value288) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value288) + Math.log((v[j]?var51:(1.0 - var51)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value288);
																													}
																												}
																											}
																										}
																									}
																								}
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
																for(int index$sample5$268 = 0; index$sample5$268 < weightings.length; index$sample5$268 += 1) {
																	int distributionTempVariable$v1$270 = index$sample5$268;
																	double cv$probabilitySample5Value269 = (1.0 * distribution$sample5[index$sample5$268]);
																	{
																		if(fixedFlag$sample27) {
																			{
																				for(int i = 0; i < size; i += 1) {
																					if(((i + 1) == j)) {
																						{
																							for(int index$i$291_1 = 0; index$i$291_1 < size; index$i$291_1 += 1) {
																								if(((index$i$291_1 + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * distributionTempVariable$v1$270) + v2[j]) + traceTempVariable$var46$5_1) / v2[(j + 1)]);
																												if(((Math.log(cv$probabilitySample5Value269) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value269) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value269) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value269) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value269) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value269);
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
																					for(int index$sample27$280 = 0; index$sample27$280 < weightings.length; index$sample27$280 += 1) {
																						int distributionTempVariable$var27$282 = index$sample27$280;
																						double cv$probabilitySample27Value281 = (cv$probabilitySample5Value269 * distribution$sample27[((i - 0) / 1)][index$sample27$280]);
																						{
																							int traceTempVariable$var42$283_1 = distributionTempVariable$var27$282;
																							if(((i + 1) == j)) {
																								{
																									int traceTempVariable$var50$292_1 = distributionTempVariable$var27$282;
																									if(((i + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$270) + traceTempVariable$var42$283_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$292_1);
																													if(((Math.log(cv$probabilitySample27Value281) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value281) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value281) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value281) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value281) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value281);
																												}
																											}
																										}
																									}
																								}
																								for(int index$i$293 = 0; index$i$293 < size; index$i$293 += 1) {
																									if(!(index$i$293 == i)) {
																										for(int index$sample27$294 = 0; index$sample27$294 < weightings.length; index$sample27$294 += 1) {
																											int distributionTempVariable$var27$296 = index$sample27$294;
																											double cv$probabilitySample27Value295 = (cv$probabilitySample27Value281 * distribution$sample27[((index$i$293 - 0) / 1)][index$sample27$294]);
																											{
																												int traceTempVariable$var50$297_1 = distributionTempVariable$var27$296;
																												if(((index$i$293 + 1) == (j + 1))) {
																													{
																														{
																															{
																																double var51 = ((((1.0 * distributionTempVariable$v1$270) + traceTempVariable$var42$283_1) + traceTempVariable$var46$5_1) / traceTempVariable$var50$297_1);
																																if(((Math.log(cv$probabilitySample27Value295) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value295) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value295) + Math.log((v[j]?var51:(1.0 - var51))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value295) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value295) + Math.log((v[j]?var51:(1.0 - var51)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value295);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
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
							{
								int traceTempVariable$var50$6_1 = cv$currentValue;
								for(int j = 0; j < size; j += 1) {
									if((0 == (j + 1))) {
										if(!guard$sample11bernoulli52[((j - 0) / 1)]) {
											guard$sample11bernoulli52[((j - 0) / 1)] = true;
											{
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if(fixedFlag$sample5) {
															{
																{
																	int traceTempVariable$var42$303_1 = cv$currentValue;
																	if((0 == j)) {
																		{
																			int traceTempVariable$var46$313_1 = cv$currentValue;
																			if((0 == (j + 1))) {
																				{
																					{
																						{
																							double var51 = ((((1.0 * v1) + traceTempVariable$var42$303_1) + traceTempVariable$var46$313_1) / traceTempVariable$var50$6_1);
																							if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$314 = 0; index$sample11$314 < weightings.length; index$sample11$314 += 1) {
																				int distributionTempVariable$var11$316 = index$sample11$314;
																				double cv$probabilitySample11Value315 = (1.0 * distribution$sample11[index$sample11$314]);
																				{
																					int traceTempVariable$var46$317_1 = distributionTempVariable$var11$316;
																					if((0 == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * v1) + traceTempVariable$var42$303_1) + traceTempVariable$var46$317_1) / traceTempVariable$var50$6_1);
																									if(((Math.log(cv$probabilitySample11Value315) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value315) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value315) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value315) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value315) + Math.log((v[j]?var51:(1.0 - var51)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value315);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																if(!true) {
																	for(int index$sample11$304 = 0; index$sample11$304 < weightings.length; index$sample11$304 += 1) {
																		int distributionTempVariable$var11$306 = index$sample11$304;
																		double cv$probabilitySample11Value305 = (1.0 * distribution$sample11[index$sample11$304]);
																		{
																			int traceTempVariable$var42$307_1 = distributionTempVariable$var11$306;
																			if((0 == j)) {
																				{
																					int traceTempVariable$var46$318_1 = cv$currentValue;
																					if((0 == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * v1) + traceTempVariable$var42$307_1) + traceTempVariable$var46$318_1) / traceTempVariable$var50$6_1);
																									if(((Math.log(cv$probabilitySample11Value305) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value305) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value305) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value305) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value305) + Math.log((v[j]?var51:(1.0 - var51)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value305);
																								}
																							}
																						}
																					}
																				}
																				{
																					int traceTempVariable$var46$319_1 = distributionTempVariable$var11$306;
																					if((0 == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * v1) + traceTempVariable$var42$307_1) + traceTempVariable$var46$319_1) / traceTempVariable$var50$6_1);
																									if(((Math.log(cv$probabilitySample11Value305) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value305) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value305) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value305) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value305) + Math.log((v[j]?var51:(1.0 - var51)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value305);
																								}
																							}
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample11$320 = 0; index$sample11$320 < weightings.length; index$sample11$320 += 1) {
																						int distributionTempVariable$var11$322 = index$sample11$320;
																						double cv$probabilitySample11Value321 = (cv$probabilitySample11Value305 * distribution$sample11[index$sample11$320]);
																						{
																							int traceTempVariable$var46$323_1 = distributionTempVariable$var11$322;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + traceTempVariable$var42$307_1) + traceTempVariable$var46$323_1) / traceTempVariable$var50$6_1);
																											if(((Math.log(cv$probabilitySample11Value321) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value321) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value321) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value321) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value321) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value321);
																										}
																									}
																								}
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
																for(int index$sample5$299 = 0; index$sample5$299 < weightings.length; index$sample5$299 += 1) {
																	int distributionTempVariable$v1$301 = index$sample5$299;
																	double cv$probabilitySample5Value300 = (1.0 * distribution$sample5[index$sample5$299]);
																	{
																		{
																			int traceTempVariable$var42$308_1 = cv$currentValue;
																			if((0 == j)) {
																				{
																					int traceTempVariable$var46$324_1 = cv$currentValue;
																					if((0 == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * distributionTempVariable$v1$301) + traceTempVariable$var42$308_1) + traceTempVariable$var46$324_1) / traceTempVariable$var50$6_1);
																									if(((Math.log(cv$probabilitySample5Value300) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value300) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value300) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value300) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value300) + Math.log((v[j]?var51:(1.0 - var51)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value300);
																								}
																							}
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample11$325 = 0; index$sample11$325 < weightings.length; index$sample11$325 += 1) {
																						int distributionTempVariable$var11$327 = index$sample11$325;
																						double cv$probabilitySample11Value326 = (cv$probabilitySample5Value300 * distribution$sample11[index$sample11$325]);
																						{
																							int traceTempVariable$var46$328_1 = distributionTempVariable$var11$327;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * distributionTempVariable$v1$301) + traceTempVariable$var42$308_1) + traceTempVariable$var46$328_1) / traceTempVariable$var50$6_1);
																											if(((Math.log(cv$probabilitySample11Value326) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value326) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value326) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value326) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value326) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value326);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$309 = 0; index$sample11$309 < weightings.length; index$sample11$309 += 1) {
																				int distributionTempVariable$var11$311 = index$sample11$309;
																				double cv$probabilitySample11Value310 = (cv$probabilitySample5Value300 * distribution$sample11[index$sample11$309]);
																				{
																					int traceTempVariable$var42$312_1 = distributionTempVariable$var11$311;
																					if((0 == j)) {
																						{
																							int traceTempVariable$var46$329_1 = cv$currentValue;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * distributionTempVariable$v1$301) + traceTempVariable$var42$312_1) + traceTempVariable$var46$329_1) / traceTempVariable$var50$6_1);
																											if(((Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value310);
																										}
																									}
																								}
																							}
																						}
																						{
																							int traceTempVariable$var46$330_1 = distributionTempVariable$var11$311;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * distributionTempVariable$v1$301) + traceTempVariable$var42$312_1) + traceTempVariable$var46$330_1) / traceTempVariable$var50$6_1);
																											if(((Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value310);
																										}
																									}
																								}
																							}
																						}
																						if(!true) {
																							for(int index$sample11$331 = 0; index$sample11$331 < weightings.length; index$sample11$331 += 1) {
																								int distributionTempVariable$var11$333 = index$sample11$331;
																								double cv$probabilitySample11Value332 = (cv$probabilitySample11Value310 * distribution$sample11[index$sample11$331]);
																								{
																									int traceTempVariable$var46$334_1 = distributionTempVariable$var11$333;
																									if((0 == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$301) + traceTempVariable$var42$312_1) + traceTempVariable$var46$334_1) / traceTempVariable$var50$6_1);
																													if(((Math.log(cv$probabilitySample11Value332) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value332) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value332) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value332) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value332) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value332);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample5) {
															{
																if(fixedFlag$sample27) {
																	{
																		for(int i = 0; i < size; i += 1) {
																			if(((i + 1) == j)) {
																				{
																					int traceTempVariable$var46$352_1 = cv$currentValue;
																					if((0 == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var46$352_1) / traceTempVariable$var50$6_1);
																									if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																								}
																							}
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample11$353 = 0; index$sample11$353 < weightings.length; index$sample11$353 += 1) {
																						int distributionTempVariable$var11$355 = index$sample11$353;
																						double cv$probabilitySample11Value354 = (1.0 * distribution$sample11[index$sample11$353]);
																						{
																							int traceTempVariable$var46$356_1 = distributionTempVariable$var11$355;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var46$356_1) / traceTempVariable$var50$6_1);
																											if(((Math.log(cv$probabilitySample11Value354) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value354) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value354) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value354) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value354) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value354);
																										}
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
																			for(int index$sample27$342 = 0; index$sample27$342 < weightings.length; index$sample27$342 += 1) {
																				int distributionTempVariable$var27$344 = index$sample27$342;
																				double cv$probabilitySample27Value343 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$342]);
																				{
																					int traceTempVariable$var42$345_1 = distributionTempVariable$var27$344;
																					if(((i + 1) == j)) {
																						{
																							int traceTempVariable$var46$357_1 = cv$currentValue;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + traceTempVariable$var42$345_1) + traceTempVariable$var46$357_1) / traceTempVariable$var50$6_1);
																											if(((Math.log(cv$probabilitySample27Value343) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value343) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value343) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value343) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value343) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value343);
																										}
																									}
																								}
																							}
																						}
																						if(!true) {
																							for(int index$sample11$358 = 0; index$sample11$358 < weightings.length; index$sample11$358 += 1) {
																								int distributionTempVariable$var11$360 = index$sample11$358;
																								double cv$probabilitySample11Value359 = (cv$probabilitySample27Value343 * distribution$sample11[index$sample11$358]);
																								{
																									int traceTempVariable$var46$361_1 = distributionTempVariable$var11$360;
																									if((0 == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * v1) + traceTempVariable$var42$345_1) + traceTempVariable$var46$361_1) / traceTempVariable$var50$6_1);
																													if(((Math.log(cv$probabilitySample11Value359) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value359) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value359) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value359) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value359) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value359);
																												}
																											}
																										}
																									}
																								}
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
																for(int index$sample5$336 = 0; index$sample5$336 < weightings.length; index$sample5$336 += 1) {
																	int distributionTempVariable$v1$338 = index$sample5$336;
																	double cv$probabilitySample5Value337 = (1.0 * distribution$sample5[index$sample5$336]);
																	{
																		if(fixedFlag$sample27) {
																			{
																				for(int i = 0; i < size; i += 1) {
																					if(((i + 1) == j)) {
																						{
																							int traceTempVariable$var46$362_1 = cv$currentValue;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * distributionTempVariable$v1$338) + v2[j]) + traceTempVariable$var46$362_1) / traceTempVariable$var50$6_1);
																											if(((Math.log(cv$probabilitySample5Value337) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value337) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value337) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value337) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value337) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value337);
																										}
																									}
																								}
																							}
																						}
																						if(!true) {
																							for(int index$sample11$363 = 0; index$sample11$363 < weightings.length; index$sample11$363 += 1) {
																								int distributionTempVariable$var11$365 = index$sample11$363;
																								double cv$probabilitySample11Value364 = (cv$probabilitySample5Value337 * distribution$sample11[index$sample11$363]);
																								{
																									int traceTempVariable$var46$366_1 = distributionTempVariable$var11$365;
																									if((0 == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$338) + v2[j]) + traceTempVariable$var46$366_1) / traceTempVariable$var50$6_1);
																													if(((Math.log(cv$probabilitySample11Value364) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value364) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value364) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value364) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value364) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value364);
																												}
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
																					for(int index$sample27$348 = 0; index$sample27$348 < weightings.length; index$sample27$348 += 1) {
																						int distributionTempVariable$var27$350 = index$sample27$348;
																						double cv$probabilitySample27Value349 = (cv$probabilitySample5Value337 * distribution$sample27[((i - 0) / 1)][index$sample27$348]);
																						{
																							int traceTempVariable$var42$351_1 = distributionTempVariable$var27$350;
																							if(((i + 1) == j)) {
																								{
																									int traceTempVariable$var46$367_1 = cv$currentValue;
																									if((0 == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$338) + traceTempVariable$var42$351_1) + traceTempVariable$var46$367_1) / traceTempVariable$var50$6_1);
																													if(((Math.log(cv$probabilitySample27Value349) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value349) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value349) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value349) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value349) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value349);
																												}
																											}
																										}
																									}
																								}
																								if(!true) {
																									for(int index$sample11$368 = 0; index$sample11$368 < weightings.length; index$sample11$368 += 1) {
																										int distributionTempVariable$var11$370 = index$sample11$368;
																										double cv$probabilitySample11Value369 = (cv$probabilitySample27Value349 * distribution$sample11[index$sample11$368]);
																										{
																											int traceTempVariable$var46$371_1 = distributionTempVariable$var11$370;
																											if((0 == (j + 1))) {
																												{
																													{
																														{
																															double var51 = ((((1.0 * distributionTempVariable$v1$338) + traceTempVariable$var42$351_1) + traceTempVariable$var46$371_1) / traceTempVariable$var50$6_1);
																															if(((Math.log(cv$probabilitySample11Value369) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value369) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value369) + Math.log((v[j]?var51:(1.0 - var51))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value369) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value369) + Math.log((v[j]?var51:(1.0 - var51)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value369);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample5) {
															{
																{
																	int traceTempVariable$var42$377_1 = cv$currentValue;
																	if((0 == j)) {
																		if(fixedFlag$sample27) {
																			{
																				for(int i = 0; i < size; i += 1) {
																					if(((i + 1) == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * v1) + traceTempVariable$var42$377_1) + v2[(j + 1)]) / traceTempVariable$var50$6_1);
																									if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
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
																					for(int index$sample27$389 = 0; index$sample27$389 < weightings.length; index$sample27$389 += 1) {
																						int distributionTempVariable$var27$391 = index$sample27$389;
																						double cv$probabilitySample27Value390 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$389]);
																						{
																							int traceTempVariable$var46$392_1 = distributionTempVariable$var27$391;
																							if(((i + 1) == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + traceTempVariable$var42$377_1) + traceTempVariable$var46$392_1) / traceTempVariable$var50$6_1);
																											if(((Math.log(cv$probabilitySample27Value390) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value390) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value390) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value390) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value390) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value390);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																if(!true) {
																	for(int index$sample11$378 = 0; index$sample11$378 < weightings.length; index$sample11$378 += 1) {
																		int distributionTempVariable$var11$380 = index$sample11$378;
																		double cv$probabilitySample11Value379 = (1.0 * distribution$sample11[index$sample11$378]);
																		{
																			int traceTempVariable$var42$381_1 = distributionTempVariable$var11$380;
																			if((0 == j)) {
																				if(fixedFlag$sample27) {
																					{
																						for(int i = 0; i < size; i += 1) {
																							if(((i + 1) == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + traceTempVariable$var42$381_1) + v2[(j + 1)]) / traceTempVariable$var50$6_1);
																											if(((Math.log(cv$probabilitySample11Value379) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value379) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value379) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value379) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value379) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value379);
																										}
																									}
																								}
																							}
																						}
																					}
																				} else {
																					for(int i = 0; i < size; i += 1) {
																						if(true) {
																							for(int index$sample27$395 = 0; index$sample27$395 < weightings.length; index$sample27$395 += 1) {
																								int distributionTempVariable$var27$397 = index$sample27$395;
																								double cv$probabilitySample27Value396 = (cv$probabilitySample11Value379 * distribution$sample27[((i - 0) / 1)][index$sample27$395]);
																								{
																									int traceTempVariable$var46$398_1 = distributionTempVariable$var27$397;
																									if(((i + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * v1) + traceTempVariable$var42$381_1) + traceTempVariable$var46$398_1) / traceTempVariable$var50$6_1);
																													if(((Math.log(cv$probabilitySample27Value396) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value396) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value396) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value396) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value396) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value396);
																												}
																											}
																										}
																									}
																								}
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
																for(int index$sample5$373 = 0; index$sample5$373 < weightings.length; index$sample5$373 += 1) {
																	int distributionTempVariable$v1$375 = index$sample5$373;
																	double cv$probabilitySample5Value374 = (1.0 * distribution$sample5[index$sample5$373]);
																	{
																		{
																			int traceTempVariable$var42$382_1 = cv$currentValue;
																			if((0 == j)) {
																				if(fixedFlag$sample27) {
																					{
																						for(int i = 0; i < size; i += 1) {
																							if(((i + 1) == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * distributionTempVariable$v1$375) + traceTempVariable$var42$382_1) + v2[(j + 1)]) / traceTempVariable$var50$6_1);
																											if(((Math.log(cv$probabilitySample5Value374) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value374) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value374) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value374) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value374) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value374);
																										}
																									}
																								}
																							}
																						}
																					}
																				} else {
																					for(int i = 0; i < size; i += 1) {
																						if(true) {
																							for(int index$sample27$401 = 0; index$sample27$401 < weightings.length; index$sample27$401 += 1) {
																								int distributionTempVariable$var27$403 = index$sample27$401;
																								double cv$probabilitySample27Value402 = (cv$probabilitySample5Value374 * distribution$sample27[((i - 0) / 1)][index$sample27$401]);
																								{
																									int traceTempVariable$var46$404_1 = distributionTempVariable$var27$403;
																									if(((i + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$375) + traceTempVariable$var42$382_1) + traceTempVariable$var46$404_1) / traceTempVariable$var50$6_1);
																													if(((Math.log(cv$probabilitySample27Value402) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value402) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value402) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value402) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value402) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value402);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$383 = 0; index$sample11$383 < weightings.length; index$sample11$383 += 1) {
																				int distributionTempVariable$var11$385 = index$sample11$383;
																				double cv$probabilitySample11Value384 = (cv$probabilitySample5Value374 * distribution$sample11[index$sample11$383]);
																				{
																					int traceTempVariable$var42$386_1 = distributionTempVariable$var11$385;
																					if((0 == j)) {
																						if(fixedFlag$sample27) {
																							{
																								for(int i = 0; i < size; i += 1) {
																									if(((i + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$375) + traceTempVariable$var42$386_1) + v2[(j + 1)]) / traceTempVariable$var50$6_1);
																													if(((Math.log(cv$probabilitySample11Value384) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value384) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value384) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value384) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value384) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value384);
																												}
																											}
																										}
																									}
																								}
																							}
																						} else {
																							for(int i = 0; i < size; i += 1) {
																								if(true) {
																									for(int index$sample27$407 = 0; index$sample27$407 < weightings.length; index$sample27$407 += 1) {
																										int distributionTempVariable$var27$409 = index$sample27$407;
																										double cv$probabilitySample27Value408 = (cv$probabilitySample11Value384 * distribution$sample27[((i - 0) / 1)][index$sample27$407]);
																										{
																											int traceTempVariable$var46$410_1 = distributionTempVariable$var27$409;
																											if(((i + 1) == (j + 1))) {
																												{
																													{
																														{
																															double var51 = ((((1.0 * distributionTempVariable$v1$375) + traceTempVariable$var42$386_1) + traceTempVariable$var46$410_1) / traceTempVariable$var50$6_1);
																															if(((Math.log(cv$probabilitySample27Value408) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value408) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value408) + Math.log((v[j]?var51:(1.0 - var51))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value408) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value408) + Math.log((v[j]?var51:(1.0 - var51)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value408);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample5) {
															{
																if(fixedFlag$sample27) {
																	{
																		for(int i = 0; i < size; i += 1) {
																			if(((i + 1) == j)) {
																				{
																					for(int index$i$428_1 = 0; index$i$428_1 < size; index$i$428_1 += 1) {
																						if(((index$i$428_1 + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * v1) + v2[j]) + v2[(j + 1)]) / traceTempVariable$var50$6_1);
																										if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
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
																	}
																} else {
																	for(int i = 0; i < size; i += 1) {
																		if(true) {
																			for(int index$sample27$418 = 0; index$sample27$418 < weightings.length; index$sample27$418 += 1) {
																				int distributionTempVariable$var27$420 = index$sample27$418;
																				double cv$probabilitySample27Value419 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$418]);
																				{
																					int traceTempVariable$var42$421_1 = distributionTempVariable$var27$420;
																					if(((i + 1) == j)) {
																						{
																							int traceTempVariable$var46$429_1 = distributionTempVariable$var27$420;
																							if(((i + 1) == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + traceTempVariable$var42$421_1) + traceTempVariable$var46$429_1) / traceTempVariable$var50$6_1);
																											if(((Math.log(cv$probabilitySample27Value419) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value419) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value419) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value419) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value419) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value419);
																										}
																									}
																								}
																							}
																						}
																						for(int index$i$430 = 0; index$i$430 < size; index$i$430 += 1) {
																							if(!(index$i$430 == i)) {
																								for(int index$sample27$431 = 0; index$sample27$431 < weightings.length; index$sample27$431 += 1) {
																									int distributionTempVariable$var27$433 = index$sample27$431;
																									double cv$probabilitySample27Value432 = (cv$probabilitySample27Value419 * distribution$sample27[((index$i$430 - 0) / 1)][index$sample27$431]);
																									{
																										int traceTempVariable$var46$434_1 = distributionTempVariable$var27$433;
																										if(((index$i$430 + 1) == (j + 1))) {
																											{
																												{
																													{
																														double var51 = ((((1.0 * v1) + traceTempVariable$var42$421_1) + traceTempVariable$var46$434_1) / traceTempVariable$var50$6_1);
																														if(((Math.log(cv$probabilitySample27Value432) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value432) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value432) + Math.log((v[j]?var51:(1.0 - var51))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value432) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value432) + Math.log((v[j]?var51:(1.0 - var51)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value432);
																													}
																												}
																											}
																										}
																									}
																								}
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
																for(int index$sample5$412 = 0; index$sample5$412 < weightings.length; index$sample5$412 += 1) {
																	int distributionTempVariable$v1$414 = index$sample5$412;
																	double cv$probabilitySample5Value413 = (1.0 * distribution$sample5[index$sample5$412]);
																	{
																		if(fixedFlag$sample27) {
																			{
																				for(int i = 0; i < size; i += 1) {
																					if(((i + 1) == j)) {
																						{
																							for(int index$i$435_1 = 0; index$i$435_1 < size; index$i$435_1 += 1) {
																								if(((index$i$435_1 + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * distributionTempVariable$v1$414) + v2[j]) + v2[(j + 1)]) / traceTempVariable$var50$6_1);
																												if(((Math.log(cv$probabilitySample5Value413) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value413) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value413) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value413) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value413) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value413);
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
																					for(int index$sample27$424 = 0; index$sample27$424 < weightings.length; index$sample27$424 += 1) {
																						int distributionTempVariable$var27$426 = index$sample27$424;
																						double cv$probabilitySample27Value425 = (cv$probabilitySample5Value413 * distribution$sample27[((i - 0) / 1)][index$sample27$424]);
																						{
																							int traceTempVariable$var42$427_1 = distributionTempVariable$var27$426;
																							if(((i + 1) == j)) {
																								{
																									int traceTempVariable$var46$436_1 = distributionTempVariable$var27$426;
																									if(((i + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$414) + traceTempVariable$var42$427_1) + traceTempVariable$var46$436_1) / traceTempVariable$var50$6_1);
																													if(((Math.log(cv$probabilitySample27Value425) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value425) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value425) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value425) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value425) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value425);
																												}
																											}
																										}
																									}
																								}
																								for(int index$i$437 = 0; index$i$437 < size; index$i$437 += 1) {
																									if(!(index$i$437 == i)) {
																										for(int index$sample27$438 = 0; index$sample27$438 < weightings.length; index$sample27$438 += 1) {
																											int distributionTempVariable$var27$440 = index$sample27$438;
																											double cv$probabilitySample27Value439 = (cv$probabilitySample27Value425 * distribution$sample27[((index$i$437 - 0) / 1)][index$sample27$438]);
																											{
																												int traceTempVariable$var46$441_1 = distributionTempVariable$var27$440;
																												if(((index$i$437 + 1) == (j + 1))) {
																													{
																														{
																															{
																																double var51 = ((((1.0 * distributionTempVariable$v1$414) + traceTempVariable$var42$427_1) + traceTempVariable$var46$441_1) / traceTempVariable$var50$6_1);
																																if(((Math.log(cv$probabilitySample27Value439) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value439) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value439) + Math.log((v[j]?var51:(1.0 - var51))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value439) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value439) + Math.log((v[j]?var51:(1.0 - var51)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value439);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
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
			double[] cv$localProbability = distribution$sample11;
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
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void sample27(int i) {
		int index$i$1 = i;
		if(true) {
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, weightings.length);
			}
			double[] cv$stateProbabilityLocal = cv$var27$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					int $var3570 = weightings.length;
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < $var3570))?Math.log(weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							boolean[] guard$sample27bernoulli52 = guard$sample27bernoulli52$global;
							{
								for(int j = 0; j < size; j += 1) {
									if(((i + 1) == j))
										guard$sample27bernoulli52[((j - 0) / 1)] = false;
								}
							}
							{
								for(int j = 0; j < size; j += 1) {
									if(((i + 1) == (j + 1)))
										guard$sample27bernoulli52[((j - 0) / 1)] = false;
								}
							}
							{
								for(int j = 0; j < size; j += 1) {
									if(((i + 1) == (j + 1)))
										guard$sample27bernoulli52[((j - 0) / 1)] = false;
								}
							}
							{
								int traceTempVariable$var42$5_1 = cv$currentValue;
								for(int j = 0; j < size; j += 1) {
									if(((i + 1) == j)) {
										if(!guard$sample27bernoulli52[((j - 0) / 1)]) {
											guard$sample27bernoulli52[((j - 0) / 1)] = true;
											{
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if(fixedFlag$sample5) {
															{
																if(fixedFlag$sample11) {
																	{
																		if((0 == (j + 1))) {
																			{
																				if((0 == (j + 1))) {
																					{
																						{
																							{
																								double var51 = ((((1.0 * v1) + traceTempVariable$var42$5_1) + v2[(j + 1)]) / v2[(j + 1)]);
																								if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																} else {
																	if(true) {
																		for(int index$sample11$17 = 0; index$sample11$17 < weightings.length; index$sample11$17 += 1) {
																			int distributionTempVariable$var11$19 = index$sample11$17;
																			double cv$probabilitySample11Value18 = (1.0 * distribution$sample11[index$sample11$17]);
																			{
																				int traceTempVariable$var46$20_1 = distributionTempVariable$var11$19;
																				if((0 == (j + 1))) {
																					{
																						int traceTempVariable$var50$27_1 = distributionTempVariable$var11$19;
																						if((0 == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * v1) + traceTempVariable$var42$5_1) + traceTempVariable$var46$20_1) / traceTempVariable$var50$27_1);
																										if(((Math.log(cv$probabilitySample11Value18) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value18) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value18) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value18) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value18) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value18);
																									}
																								}
																							}
																						}
																					}
																					if(!true) {
																						for(int index$sample11$28 = 0; index$sample11$28 < weightings.length; index$sample11$28 += 1) {
																							int distributionTempVariable$var11$30 = index$sample11$28;
																							double cv$probabilitySample11Value29 = (cv$probabilitySample11Value18 * distribution$sample11[index$sample11$28]);
																							{
																								int traceTempVariable$var50$31_1 = distributionTempVariable$var11$30;
																								if((0 == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * v1) + traceTempVariable$var42$5_1) + traceTempVariable$var46$20_1) / traceTempVariable$var50$31_1);
																												if(((Math.log(cv$probabilitySample11Value29) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value29) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value29) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value29) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value29) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value29);
																											}
																										}
																									}
																								}
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
																for(int index$sample5$12 = 0; index$sample5$12 < weightings.length; index$sample5$12 += 1) {
																	int distributionTempVariable$v1$14 = index$sample5$12;
																	double cv$probabilitySample5Value13 = (1.0 * distribution$sample5[index$sample5$12]);
																	{
																		if(fixedFlag$sample11) {
																			{
																				if((0 == (j + 1))) {
																					{
																						if((0 == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * distributionTempVariable$v1$14) + traceTempVariable$var42$5_1) + v2[(j + 1)]) / v2[(j + 1)]);
																										if(((Math.log(cv$probabilitySample5Value13) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value13) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value13) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value13) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value13) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value13);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample11$22 = 0; index$sample11$22 < weightings.length; index$sample11$22 += 1) {
																					int distributionTempVariable$var11$24 = index$sample11$22;
																					double cv$probabilitySample11Value23 = (cv$probabilitySample5Value13 * distribution$sample11[index$sample11$22]);
																					{
																						int traceTempVariable$var46$25_1 = distributionTempVariable$var11$24;
																						if((0 == (j + 1))) {
																							{
																								int traceTempVariable$var50$33_1 = distributionTempVariable$var11$24;
																								if((0 == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * distributionTempVariable$v1$14) + traceTempVariable$var42$5_1) + traceTempVariable$var46$25_1) / traceTempVariable$var50$33_1);
																												if(((Math.log(cv$probabilitySample11Value23) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value23) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value23) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value23) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value23) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value23);
																											}
																										}
																									}
																								}
																							}
																							if(!true) {
																								for(int index$sample11$34 = 0; index$sample11$34 < weightings.length; index$sample11$34 += 1) {
																									int distributionTempVariable$var11$36 = index$sample11$34;
																									double cv$probabilitySample11Value35 = (cv$probabilitySample11Value23 * distribution$sample11[index$sample11$34]);
																									{
																										int traceTempVariable$var50$37_1 = distributionTempVariable$var11$36;
																										if((0 == (j + 1))) {
																											{
																												{
																													{
																														double var51 = ((((1.0 * distributionTempVariable$v1$14) + traceTempVariable$var42$5_1) + traceTempVariable$var46$25_1) / traceTempVariable$var50$37_1);
																														if(((Math.log(cv$probabilitySample11Value35) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value35) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value35) + Math.log((v[j]?var51:(1.0 - var51))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value35) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value35) + Math.log((v[j]?var51:(1.0 - var51)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value35);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample5) {
															{
																{
																	int traceTempVariable$var46$43_1 = cv$currentValue;
																	if(((index$i$1 + 1) == (j + 1))) {
																		if(fixedFlag$sample11) {
																			{
																				if((0 == (j + 1))) {
																					{
																						{
																							{
																								double var51 = ((((1.0 * v1) + traceTempVariable$var42$5_1) + traceTempVariable$var46$43_1) / v2[(j + 1)]);
																								if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample11$56 = 0; index$sample11$56 < weightings.length; index$sample11$56 += 1) {
																					int distributionTempVariable$var11$58 = index$sample11$56;
																					double cv$probabilitySample11Value57 = (1.0 * distribution$sample11[index$sample11$56]);
																					{
																						int traceTempVariable$var50$59_1 = distributionTempVariable$var11$58;
																						if((0 == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * v1) + traceTempVariable$var42$5_1) + traceTempVariable$var46$43_1) / traceTempVariable$var50$59_1);
																										if(((Math.log(cv$probabilitySample11Value57) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value57) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value57) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value57) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value57) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value57);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$44 = 0; index$i$44 < size; index$i$44 += 1) {
																	if(!(index$i$44 == index$i$1)) {
																		for(int index$sample27$45 = 0; index$sample27$45 < weightings.length; index$sample27$45 += 1) {
																			int distributionTempVariable$var27$47 = index$sample27$45;
																			double cv$probabilitySample27Value46 = (1.0 * distribution$sample27[((index$i$44 - 0) / 1)][index$sample27$45]);
																			{
																				int traceTempVariable$var46$48_1 = distributionTempVariable$var27$47;
																				if(((index$i$44 + 1) == (j + 1))) {
																					if(fixedFlag$sample11) {
																						{
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + traceTempVariable$var42$5_1) + traceTempVariable$var46$48_1) / v2[(j + 1)]);
																											if(((Math.log(cv$probabilitySample27Value46) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value46) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value46) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value46) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value46) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value46);
																										}
																									}
																								}
																							}
																						}
																					} else {
																						if(true) {
																							for(int index$sample11$61 = 0; index$sample11$61 < weightings.length; index$sample11$61 += 1) {
																								int distributionTempVariable$var11$63 = index$sample11$61;
																								double cv$probabilitySample11Value62 = (cv$probabilitySample27Value46 * distribution$sample11[index$sample11$61]);
																								{
																									int traceTempVariable$var50$64_1 = distributionTempVariable$var11$63;
																									if((0 == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * v1) + traceTempVariable$var42$5_1) + traceTempVariable$var46$48_1) / traceTempVariable$var50$64_1);
																													if(((Math.log(cv$probabilitySample11Value62) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value62) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value62) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value62) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value62) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value62);
																												}
																											}
																										}
																									}
																								}
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
																for(int index$sample5$39 = 0; index$sample5$39 < weightings.length; index$sample5$39 += 1) {
																	int distributionTempVariable$v1$41 = index$sample5$39;
																	double cv$probabilitySample5Value40 = (1.0 * distribution$sample5[index$sample5$39]);
																	{
																		{
																			int traceTempVariable$var46$49_1 = cv$currentValue;
																			if(((index$i$1 + 1) == (j + 1))) {
																				if(fixedFlag$sample11) {
																					{
																						if((0 == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * distributionTempVariable$v1$41) + traceTempVariable$var42$5_1) + traceTempVariable$var46$49_1) / v2[(j + 1)]);
																										if(((Math.log(cv$probabilitySample5Value40) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value40) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value40) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value40) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value40) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value40);
																									}
																								}
																							}
																						}
																					}
																				} else {
																					if(true) {
																						for(int index$sample11$66 = 0; index$sample11$66 < weightings.length; index$sample11$66 += 1) {
																							int distributionTempVariable$var11$68 = index$sample11$66;
																							double cv$probabilitySample11Value67 = (cv$probabilitySample5Value40 * distribution$sample11[index$sample11$66]);
																							{
																								int traceTempVariable$var50$69_1 = distributionTempVariable$var11$68;
																								if((0 == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * distributionTempVariable$v1$41) + traceTempVariable$var42$5_1) + traceTempVariable$var46$49_1) / traceTempVariable$var50$69_1);
																												if(((Math.log(cv$probabilitySample11Value67) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value67) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value67) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value67) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value67) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value67);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$50 = 0; index$i$50 < size; index$i$50 += 1) {
																			if(!(index$i$50 == index$i$1)) {
																				for(int index$sample27$51 = 0; index$sample27$51 < weightings.length; index$sample27$51 += 1) {
																					int distributionTempVariable$var27$53 = index$sample27$51;
																					double cv$probabilitySample27Value52 = (cv$probabilitySample5Value40 * distribution$sample27[((index$i$50 - 0) / 1)][index$sample27$51]);
																					{
																						int traceTempVariable$var46$54_1 = distributionTempVariable$var27$53;
																						if(((index$i$50 + 1) == (j + 1))) {
																							if(fixedFlag$sample11) {
																								{
																									if((0 == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$41) + traceTempVariable$var42$5_1) + traceTempVariable$var46$54_1) / v2[(j + 1)]);
																													if(((Math.log(cv$probabilitySample27Value52) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value52) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value52) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value52) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value52) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value52);
																												}
																											}
																										}
																									}
																								}
																							} else {
																								if(true) {
																									for(int index$sample11$71 = 0; index$sample11$71 < weightings.length; index$sample11$71 += 1) {
																										int distributionTempVariable$var11$73 = index$sample11$71;
																										double cv$probabilitySample11Value72 = (cv$probabilitySample27Value52 * distribution$sample11[index$sample11$71]);
																										{
																											int traceTempVariable$var50$74_1 = distributionTempVariable$var11$73;
																											if((0 == (j + 1))) {
																												{
																													{
																														{
																															double var51 = ((((1.0 * distributionTempVariable$v1$41) + traceTempVariable$var42$5_1) + traceTempVariable$var46$54_1) / traceTempVariable$var50$74_1);
																															if(((Math.log(cv$probabilitySample11Value72) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value72) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value72) + Math.log((v[j]?var51:(1.0 - var51))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value72) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value72) + Math.log((v[j]?var51:(1.0 - var51)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value72);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample5) {
															{
																if(fixedFlag$sample11) {
																	{
																		if((0 == (j + 1))) {
																			{
																				int traceTempVariable$var50$90_1 = cv$currentValue;
																				if(((index$i$1 + 1) == (j + 1))) {
																					{
																						{
																							{
																								double var51 = ((((1.0 * v1) + traceTempVariable$var42$5_1) + v2[(j + 1)]) / traceTempVariable$var50$90_1);
																								if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																			for(int index$i$91 = 0; index$i$91 < size; index$i$91 += 1) {
																				if(!(index$i$91 == index$i$1)) {
																					for(int index$sample27$92 = 0; index$sample27$92 < weightings.length; index$sample27$92 += 1) {
																						int distributionTempVariable$var27$94 = index$sample27$92;
																						double cv$probabilitySample27Value93 = (1.0 * distribution$sample27[((index$i$91 - 0) / 1)][index$sample27$92]);
																						{
																							int traceTempVariable$var50$95_1 = distributionTempVariable$var27$94;
																							if(((index$i$91 + 1) == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + traceTempVariable$var42$5_1) + v2[(j + 1)]) / traceTempVariable$var50$95_1);
																											if(((Math.log(cv$probabilitySample27Value93) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value93) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value93) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value93) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value93) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value93);
																										}
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
																		for(int index$sample11$81 = 0; index$sample11$81 < weightings.length; index$sample11$81 += 1) {
																			int distributionTempVariable$var11$83 = index$sample11$81;
																			double cv$probabilitySample11Value82 = (1.0 * distribution$sample11[index$sample11$81]);
																			{
																				int traceTempVariable$var46$84_1 = distributionTempVariable$var11$83;
																				if((0 == (j + 1))) {
																					{
																						int traceTempVariable$var50$96_1 = cv$currentValue;
																						if(((index$i$1 + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * v1) + traceTempVariable$var42$5_1) + traceTempVariable$var46$84_1) / traceTempVariable$var50$96_1);
																										if(((Math.log(cv$probabilitySample11Value82) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value82) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value82) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value82) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value82) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value82);
																									}
																								}
																							}
																						}
																					}
																					for(int index$i$97 = 0; index$i$97 < size; index$i$97 += 1) {
																						if(!(index$i$97 == index$i$1)) {
																							for(int index$sample27$98 = 0; index$sample27$98 < weightings.length; index$sample27$98 += 1) {
																								int distributionTempVariable$var27$100 = index$sample27$98;
																								double cv$probabilitySample27Value99 = (cv$probabilitySample11Value82 * distribution$sample27[((index$i$97 - 0) / 1)][index$sample27$98]);
																								{
																									int traceTempVariable$var50$101_1 = distributionTempVariable$var27$100;
																									if(((index$i$97 + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * v1) + traceTempVariable$var42$5_1) + traceTempVariable$var46$84_1) / traceTempVariable$var50$101_1);
																													if(((Math.log(cv$probabilitySample27Value99) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value99) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value99) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value99) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value99) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value99);
																												}
																											}
																										}
																									}
																								}
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
																for(int index$sample5$76 = 0; index$sample5$76 < weightings.length; index$sample5$76 += 1) {
																	int distributionTempVariable$v1$78 = index$sample5$76;
																	double cv$probabilitySample5Value77 = (1.0 * distribution$sample5[index$sample5$76]);
																	{
																		if(fixedFlag$sample11) {
																			{
																				if((0 == (j + 1))) {
																					{
																						int traceTempVariable$var50$102_1 = cv$currentValue;
																						if(((index$i$1 + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * distributionTempVariable$v1$78) + traceTempVariable$var42$5_1) + v2[(j + 1)]) / traceTempVariable$var50$102_1);
																										if(((Math.log(cv$probabilitySample5Value77) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value77) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value77) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value77) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value77) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value77);
																									}
																								}
																							}
																						}
																					}
																					for(int index$i$103 = 0; index$i$103 < size; index$i$103 += 1) {
																						if(!(index$i$103 == index$i$1)) {
																							for(int index$sample27$104 = 0; index$sample27$104 < weightings.length; index$sample27$104 += 1) {
																								int distributionTempVariable$var27$106 = index$sample27$104;
																								double cv$probabilitySample27Value105 = (cv$probabilitySample5Value77 * distribution$sample27[((index$i$103 - 0) / 1)][index$sample27$104]);
																								{
																									int traceTempVariable$var50$107_1 = distributionTempVariable$var27$106;
																									if(((index$i$103 + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$78) + traceTempVariable$var42$5_1) + v2[(j + 1)]) / traceTempVariable$var50$107_1);
																													if(((Math.log(cv$probabilitySample27Value105) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value105) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value105) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value105) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value105) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value105);
																												}
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
																				for(int index$sample11$86 = 0; index$sample11$86 < weightings.length; index$sample11$86 += 1) {
																					int distributionTempVariable$var11$88 = index$sample11$86;
																					double cv$probabilitySample11Value87 = (cv$probabilitySample5Value77 * distribution$sample11[index$sample11$86]);
																					{
																						int traceTempVariable$var46$89_1 = distributionTempVariable$var11$88;
																						if((0 == (j + 1))) {
																							{
																								int traceTempVariable$var50$108_1 = cv$currentValue;
																								if(((index$i$1 + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * distributionTempVariable$v1$78) + traceTempVariable$var42$5_1) + traceTempVariable$var46$89_1) / traceTempVariable$var50$108_1);
																												if(((Math.log(cv$probabilitySample11Value87) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value87) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value87) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value87) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value87) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value87);
																											}
																										}
																									}
																								}
																							}
																							for(int index$i$109 = 0; index$i$109 < size; index$i$109 += 1) {
																								if(!(index$i$109 == index$i$1)) {
																									for(int index$sample27$110 = 0; index$sample27$110 < weightings.length; index$sample27$110 += 1) {
																										int distributionTempVariable$var27$112 = index$sample27$110;
																										double cv$probabilitySample27Value111 = (cv$probabilitySample11Value87 * distribution$sample27[((index$i$109 - 0) / 1)][index$sample27$110]);
																										{
																											int traceTempVariable$var50$113_1 = distributionTempVariable$var27$112;
																											if(((index$i$109 + 1) == (j + 1))) {
																												{
																													{
																														{
																															double var51 = ((((1.0 * distributionTempVariable$v1$78) + traceTempVariable$var42$5_1) + traceTempVariable$var46$89_1) / traceTempVariable$var50$113_1);
																															if(((Math.log(cv$probabilitySample27Value111) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value111) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value111) + Math.log((v[j]?var51:(1.0 - var51))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value111) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value111) + Math.log((v[j]?var51:(1.0 - var51)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value111);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample5) {
															{
																{
																	int traceTempVariable$var46$119_1 = cv$currentValue;
																	if(((index$i$1 + 1) == (j + 1))) {
																		{
																			int traceTempVariable$var50$131_1 = cv$currentValue;
																			if(((index$i$1 + 1) == (j + 1))) {
																				{
																					{
																						{
																							double var51 = ((((1.0 * v1) + traceTempVariable$var42$5_1) + traceTempVariable$var46$119_1) / traceTempVariable$var50$131_1);
																							if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$132 = 0; index$i$132 < size; index$i$132 += 1) {
																			if(!(index$i$132 == index$i$1)) {
																				for(int index$sample27$133 = 0; index$sample27$133 < weightings.length; index$sample27$133 += 1) {
																					int distributionTempVariable$var27$135 = index$sample27$133;
																					double cv$probabilitySample27Value134 = (1.0 * distribution$sample27[((index$i$132 - 0) / 1)][index$sample27$133]);
																					{
																						int traceTempVariable$var50$136_1 = distributionTempVariable$var27$135;
																						if(((index$i$132 + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * v1) + traceTempVariable$var42$5_1) + traceTempVariable$var46$119_1) / traceTempVariable$var50$136_1);
																										if(((Math.log(cv$probabilitySample27Value134) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value134) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value134) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value134) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value134) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value134);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$120 = 0; index$i$120 < size; index$i$120 += 1) {
																	if(!(index$i$120 == index$i$1)) {
																		for(int index$sample27$121 = 0; index$sample27$121 < weightings.length; index$sample27$121 += 1) {
																			int distributionTempVariable$var27$123 = index$sample27$121;
																			double cv$probabilitySample27Value122 = (1.0 * distribution$sample27[((index$i$120 - 0) / 1)][index$sample27$121]);
																			{
																				int traceTempVariable$var46$124_1 = distributionTempVariable$var27$123;
																				if(((index$i$120 + 1) == (j + 1))) {
																					{
																						int traceTempVariable$var50$137_1 = cv$currentValue;
																						if(((index$i$1 + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * v1) + traceTempVariable$var42$5_1) + traceTempVariable$var46$124_1) / traceTempVariable$var50$137_1);
																										if(((Math.log(cv$probabilitySample27Value122) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value122) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value122) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value122) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value122) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value122);
																									}
																								}
																							}
																						}
																					}
																					{
																						int traceTempVariable$var50$138_1 = distributionTempVariable$var27$123;
																						if(((index$i$120 + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * v1) + traceTempVariable$var42$5_1) + traceTempVariable$var46$124_1) / traceTempVariable$var50$138_1);
																										if(((Math.log(cv$probabilitySample27Value122) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value122) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value122) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value122) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value122) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value122);
																									}
																								}
																							}
																						}
																					}
																					for(int index$i$139 = 0; index$i$139 < size; index$i$139 += 1) {
																						if((!(index$i$139 == index$i$1) && !(index$i$139 == index$i$120))) {
																							for(int index$sample27$140 = 0; index$sample27$140 < weightings.length; index$sample27$140 += 1) {
																								int distributionTempVariable$var27$142 = index$sample27$140;
																								double cv$probabilitySample27Value141 = (cv$probabilitySample27Value122 * distribution$sample27[((index$i$139 - 0) / 1)][index$sample27$140]);
																								{
																									int traceTempVariable$var50$143_1 = distributionTempVariable$var27$142;
																									if(((index$i$139 + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * v1) + traceTempVariable$var42$5_1) + traceTempVariable$var46$124_1) / traceTempVariable$var50$143_1);
																													if(((Math.log(cv$probabilitySample27Value141) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value141) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value141) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value141) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value141) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value141);
																												}
																											}
																										}
																									}
																								}
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
																for(int index$sample5$115 = 0; index$sample5$115 < weightings.length; index$sample5$115 += 1) {
																	int distributionTempVariable$v1$117 = index$sample5$115;
																	double cv$probabilitySample5Value116 = (1.0 * distribution$sample5[index$sample5$115]);
																	{
																		{
																			int traceTempVariable$var46$125_1 = cv$currentValue;
																			if(((index$i$1 + 1) == (j + 1))) {
																				{
																					int traceTempVariable$var50$144_1 = cv$currentValue;
																					if(((index$i$1 + 1) == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * distributionTempVariable$v1$117) + traceTempVariable$var42$5_1) + traceTempVariable$var46$125_1) / traceTempVariable$var50$144_1);
																									if(((Math.log(cv$probabilitySample5Value116) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value116) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value116) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value116) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value116) + Math.log((v[j]?var51:(1.0 - var51)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value116);
																								}
																							}
																						}
																					}
																				}
																				for(int index$i$145 = 0; index$i$145 < size; index$i$145 += 1) {
																					if(!(index$i$145 == index$i$1)) {
																						for(int index$sample27$146 = 0; index$sample27$146 < weightings.length; index$sample27$146 += 1) {
																							int distributionTempVariable$var27$148 = index$sample27$146;
																							double cv$probabilitySample27Value147 = (cv$probabilitySample5Value116 * distribution$sample27[((index$i$145 - 0) / 1)][index$sample27$146]);
																							{
																								int traceTempVariable$var50$149_1 = distributionTempVariable$var27$148;
																								if(((index$i$145 + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * distributionTempVariable$v1$117) + traceTempVariable$var42$5_1) + traceTempVariable$var46$125_1) / traceTempVariable$var50$149_1);
																												if(((Math.log(cv$probabilitySample27Value147) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value147) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value147) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value147) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value147) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value147);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$126 = 0; index$i$126 < size; index$i$126 += 1) {
																			if(!(index$i$126 == index$i$1)) {
																				for(int index$sample27$127 = 0; index$sample27$127 < weightings.length; index$sample27$127 += 1) {
																					int distributionTempVariable$var27$129 = index$sample27$127;
																					double cv$probabilitySample27Value128 = (cv$probabilitySample5Value116 * distribution$sample27[((index$i$126 - 0) / 1)][index$sample27$127]);
																					{
																						int traceTempVariable$var46$130_1 = distributionTempVariable$var27$129;
																						if(((index$i$126 + 1) == (j + 1))) {
																							{
																								int traceTempVariable$var50$150_1 = cv$currentValue;
																								if(((index$i$1 + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * distributionTempVariable$v1$117) + traceTempVariable$var42$5_1) + traceTempVariable$var46$130_1) / traceTempVariable$var50$150_1);
																												if(((Math.log(cv$probabilitySample27Value128) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value128) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value128) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value128) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value128) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value128);
																											}
																										}
																									}
																								}
																							}
																							{
																								int traceTempVariable$var50$151_1 = distributionTempVariable$var27$129;
																								if(((index$i$126 + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * distributionTempVariable$v1$117) + traceTempVariable$var42$5_1) + traceTempVariable$var46$130_1) / traceTempVariable$var50$151_1);
																												if(((Math.log(cv$probabilitySample27Value128) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value128) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value128) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value128) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value128) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value128);
																											}
																										}
																									}
																								}
																							}
																							for(int index$i$152 = 0; index$i$152 < size; index$i$152 += 1) {
																								if((!(index$i$152 == index$i$1) && !(index$i$152 == index$i$126))) {
																									for(int index$sample27$153 = 0; index$sample27$153 < weightings.length; index$sample27$153 += 1) {
																										int distributionTempVariable$var27$155 = index$sample27$153;
																										double cv$probabilitySample27Value154 = (cv$probabilitySample27Value128 * distribution$sample27[((index$i$152 - 0) / 1)][index$sample27$153]);
																										{
																											int traceTempVariable$var50$156_1 = distributionTempVariable$var27$155;
																											if(((index$i$152 + 1) == (j + 1))) {
																												{
																													{
																														{
																															double var51 = ((((1.0 * distributionTempVariable$v1$117) + traceTempVariable$var42$5_1) + traceTempVariable$var46$130_1) / traceTempVariable$var50$156_1);
																															if(((Math.log(cv$probabilitySample27Value154) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value154) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value154) + Math.log((v[j]?var51:(1.0 - var51))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value154) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value154) + Math.log((v[j]?var51:(1.0 - var51)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value154);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
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
							{
								int traceTempVariable$var46$6_1 = cv$currentValue;
								for(int j = 0; j < size; j += 1) {
									if(((i + 1) == (j + 1))) {
										if(!guard$sample27bernoulli52[((j - 0) / 1)]) {
											guard$sample27bernoulli52[((j - 0) / 1)] = true;
											{
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if(fixedFlag$sample5) {
															{
																if(fixedFlag$sample11) {
																	{
																		if((0 == j)) {
																			{
																				if((0 == (j + 1))) {
																					{
																						{
																							{
																								double var51 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var46$6_1) / v2[(j + 1)]);
																								if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																} else {
																	if(true) {
																		for(int index$sample11$163 = 0; index$sample11$163 < weightings.length; index$sample11$163 += 1) {
																			int distributionTempVariable$var11$165 = index$sample11$163;
																			double cv$probabilitySample11Value164 = (1.0 * distribution$sample11[index$sample11$163]);
																			{
																				int traceTempVariable$var42$166_1 = distributionTempVariable$var11$165;
																				if((0 == j)) {
																					{
																						int traceTempVariable$var50$173_1 = distributionTempVariable$var11$165;
																						if((0 == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * v1) + traceTempVariable$var42$166_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$173_1);
																										if(((Math.log(cv$probabilitySample11Value164) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value164) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value164) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value164) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value164) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value164);
																									}
																								}
																							}
																						}
																					}
																					if(!true) {
																						for(int index$sample11$174 = 0; index$sample11$174 < weightings.length; index$sample11$174 += 1) {
																							int distributionTempVariable$var11$176 = index$sample11$174;
																							double cv$probabilitySample11Value175 = (cv$probabilitySample11Value164 * distribution$sample11[index$sample11$174]);
																							{
																								int traceTempVariable$var50$177_1 = distributionTempVariable$var11$176;
																								if((0 == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * v1) + traceTempVariable$var42$166_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$177_1);
																												if(((Math.log(cv$probabilitySample11Value175) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value175) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value175) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value175) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value175) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value175);
																											}
																										}
																									}
																								}
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
																for(int index$sample5$158 = 0; index$sample5$158 < weightings.length; index$sample5$158 += 1) {
																	int distributionTempVariable$v1$160 = index$sample5$158;
																	double cv$probabilitySample5Value159 = (1.0 * distribution$sample5[index$sample5$158]);
																	{
																		if(fixedFlag$sample11) {
																			{
																				if((0 == j)) {
																					{
																						if((0 == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * distributionTempVariable$v1$160) + v2[j]) + traceTempVariable$var46$6_1) / v2[(j + 1)]);
																										if(((Math.log(cv$probabilitySample5Value159) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value159) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value159) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value159) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value159) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value159);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample11$168 = 0; index$sample11$168 < weightings.length; index$sample11$168 += 1) {
																					int distributionTempVariable$var11$170 = index$sample11$168;
																					double cv$probabilitySample11Value169 = (cv$probabilitySample5Value159 * distribution$sample11[index$sample11$168]);
																					{
																						int traceTempVariable$var42$171_1 = distributionTempVariable$var11$170;
																						if((0 == j)) {
																							{
																								int traceTempVariable$var50$179_1 = distributionTempVariable$var11$170;
																								if((0 == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * distributionTempVariable$v1$160) + traceTempVariable$var42$171_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$179_1);
																												if(((Math.log(cv$probabilitySample11Value169) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value169) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value169) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value169) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value169) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value169);
																											}
																										}
																									}
																								}
																							}
																							if(!true) {
																								for(int index$sample11$180 = 0; index$sample11$180 < weightings.length; index$sample11$180 += 1) {
																									int distributionTempVariable$var11$182 = index$sample11$180;
																									double cv$probabilitySample11Value181 = (cv$probabilitySample11Value169 * distribution$sample11[index$sample11$180]);
																									{
																										int traceTempVariable$var50$183_1 = distributionTempVariable$var11$182;
																										if((0 == (j + 1))) {
																											{
																												{
																													{
																														double var51 = ((((1.0 * distributionTempVariable$v1$160) + traceTempVariable$var42$171_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$183_1);
																														if(((Math.log(cv$probabilitySample11Value181) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value181) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value181) + Math.log((v[j]?var51:(1.0 - var51))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value181) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value181) + Math.log((v[j]?var51:(1.0 - var51)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value181);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample5) {
															{
																{
																	int traceTempVariable$var42$189_1 = cv$currentValue;
																	if(((index$i$1 + 1) == j)) {
																		if(fixedFlag$sample11) {
																			{
																				if((0 == (j + 1))) {
																					{
																						{
																							{
																								double var51 = ((((1.0 * v1) + traceTempVariable$var42$189_1) + traceTempVariable$var46$6_1) / v2[(j + 1)]);
																								if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample11$202 = 0; index$sample11$202 < weightings.length; index$sample11$202 += 1) {
																					int distributionTempVariable$var11$204 = index$sample11$202;
																					double cv$probabilitySample11Value203 = (1.0 * distribution$sample11[index$sample11$202]);
																					{
																						int traceTempVariable$var50$205_1 = distributionTempVariable$var11$204;
																						if((0 == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * v1) + traceTempVariable$var42$189_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$205_1);
																										if(((Math.log(cv$probabilitySample11Value203) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value203) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value203) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value203) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value203) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value203);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$190 = 0; index$i$190 < size; index$i$190 += 1) {
																	if(!(index$i$190 == index$i$1)) {
																		for(int index$sample27$191 = 0; index$sample27$191 < weightings.length; index$sample27$191 += 1) {
																			int distributionTempVariable$var27$193 = index$sample27$191;
																			double cv$probabilitySample27Value192 = (1.0 * distribution$sample27[((index$i$190 - 0) / 1)][index$sample27$191]);
																			{
																				int traceTempVariable$var42$194_1 = distributionTempVariable$var27$193;
																				if(((index$i$190 + 1) == j)) {
																					if(fixedFlag$sample11) {
																						{
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + traceTempVariable$var42$194_1) + traceTempVariable$var46$6_1) / v2[(j + 1)]);
																											if(((Math.log(cv$probabilitySample27Value192) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value192) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value192) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value192) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value192) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value192);
																										}
																									}
																								}
																							}
																						}
																					} else {
																						if(true) {
																							for(int index$sample11$207 = 0; index$sample11$207 < weightings.length; index$sample11$207 += 1) {
																								int distributionTempVariable$var11$209 = index$sample11$207;
																								double cv$probabilitySample11Value208 = (cv$probabilitySample27Value192 * distribution$sample11[index$sample11$207]);
																								{
																									int traceTempVariable$var50$210_1 = distributionTempVariable$var11$209;
																									if((0 == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * v1) + traceTempVariable$var42$194_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$210_1);
																													if(((Math.log(cv$probabilitySample11Value208) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value208) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value208) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value208) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value208) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value208);
																												}
																											}
																										}
																									}
																								}
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
																for(int index$sample5$185 = 0; index$sample5$185 < weightings.length; index$sample5$185 += 1) {
																	int distributionTempVariable$v1$187 = index$sample5$185;
																	double cv$probabilitySample5Value186 = (1.0 * distribution$sample5[index$sample5$185]);
																	{
																		{
																			int traceTempVariable$var42$195_1 = cv$currentValue;
																			if(((index$i$1 + 1) == j)) {
																				if(fixedFlag$sample11) {
																					{
																						if((0 == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * distributionTempVariable$v1$187) + traceTempVariable$var42$195_1) + traceTempVariable$var46$6_1) / v2[(j + 1)]);
																										if(((Math.log(cv$probabilitySample5Value186) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value186) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value186) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value186) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value186) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value186);
																									}
																								}
																							}
																						}
																					}
																				} else {
																					if(true) {
																						for(int index$sample11$212 = 0; index$sample11$212 < weightings.length; index$sample11$212 += 1) {
																							int distributionTempVariable$var11$214 = index$sample11$212;
																							double cv$probabilitySample11Value213 = (cv$probabilitySample5Value186 * distribution$sample11[index$sample11$212]);
																							{
																								int traceTempVariable$var50$215_1 = distributionTempVariable$var11$214;
																								if((0 == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * distributionTempVariable$v1$187) + traceTempVariable$var42$195_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$215_1);
																												if(((Math.log(cv$probabilitySample11Value213) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value213) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value213) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value213) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value213) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value213);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$196 = 0; index$i$196 < size; index$i$196 += 1) {
																			if(!(index$i$196 == index$i$1)) {
																				for(int index$sample27$197 = 0; index$sample27$197 < weightings.length; index$sample27$197 += 1) {
																					int distributionTempVariable$var27$199 = index$sample27$197;
																					double cv$probabilitySample27Value198 = (cv$probabilitySample5Value186 * distribution$sample27[((index$i$196 - 0) / 1)][index$sample27$197]);
																					{
																						int traceTempVariable$var42$200_1 = distributionTempVariable$var27$199;
																						if(((index$i$196 + 1) == j)) {
																							if(fixedFlag$sample11) {
																								{
																									if((0 == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$187) + traceTempVariable$var42$200_1) + traceTempVariable$var46$6_1) / v2[(j + 1)]);
																													if(((Math.log(cv$probabilitySample27Value198) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value198) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value198) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value198) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value198) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value198);
																												}
																											}
																										}
																									}
																								}
																							} else {
																								if(true) {
																									for(int index$sample11$217 = 0; index$sample11$217 < weightings.length; index$sample11$217 += 1) {
																										int distributionTempVariable$var11$219 = index$sample11$217;
																										double cv$probabilitySample11Value218 = (cv$probabilitySample27Value198 * distribution$sample11[index$sample11$217]);
																										{
																											int traceTempVariable$var50$220_1 = distributionTempVariable$var11$219;
																											if((0 == (j + 1))) {
																												{
																													{
																														{
																															double var51 = ((((1.0 * distributionTempVariable$v1$187) + traceTempVariable$var42$200_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$220_1);
																															if(((Math.log(cv$probabilitySample11Value218) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value218) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value218) + Math.log((v[j]?var51:(1.0 - var51))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value218) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value218) + Math.log((v[j]?var51:(1.0 - var51)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value218);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample5) {
															{
																if(fixedFlag$sample11) {
																	{
																		if((0 == j)) {
																			{
																				int traceTempVariable$var50$236_1 = cv$currentValue;
																				if(((index$i$1 + 1) == (j + 1))) {
																					{
																						{
																							{
																								double var51 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var46$6_1) / traceTempVariable$var50$236_1);
																								if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																			for(int index$i$237 = 0; index$i$237 < size; index$i$237 += 1) {
																				if(!(index$i$237 == index$i$1)) {
																					for(int index$sample27$238 = 0; index$sample27$238 < weightings.length; index$sample27$238 += 1) {
																						int distributionTempVariable$var27$240 = index$sample27$238;
																						double cv$probabilitySample27Value239 = (1.0 * distribution$sample27[((index$i$237 - 0) / 1)][index$sample27$238]);
																						{
																							int traceTempVariable$var50$241_1 = distributionTempVariable$var27$240;
																							if(((index$i$237 + 1) == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var46$6_1) / traceTempVariable$var50$241_1);
																											if(((Math.log(cv$probabilitySample27Value239) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value239) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value239) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value239) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value239) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value239);
																										}
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
																		for(int index$sample11$227 = 0; index$sample11$227 < weightings.length; index$sample11$227 += 1) {
																			int distributionTempVariable$var11$229 = index$sample11$227;
																			double cv$probabilitySample11Value228 = (1.0 * distribution$sample11[index$sample11$227]);
																			{
																				int traceTempVariable$var42$230_1 = distributionTempVariable$var11$229;
																				if((0 == j)) {
																					{
																						int traceTempVariable$var50$242_1 = cv$currentValue;
																						if(((index$i$1 + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * v1) + traceTempVariable$var42$230_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$242_1);
																										if(((Math.log(cv$probabilitySample11Value228) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value228) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value228) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value228) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value228) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value228);
																									}
																								}
																							}
																						}
																					}
																					for(int index$i$243 = 0; index$i$243 < size; index$i$243 += 1) {
																						if(!(index$i$243 == index$i$1)) {
																							for(int index$sample27$244 = 0; index$sample27$244 < weightings.length; index$sample27$244 += 1) {
																								int distributionTempVariable$var27$246 = index$sample27$244;
																								double cv$probabilitySample27Value245 = (cv$probabilitySample11Value228 * distribution$sample27[((index$i$243 - 0) / 1)][index$sample27$244]);
																								{
																									int traceTempVariable$var50$247_1 = distributionTempVariable$var27$246;
																									if(((index$i$243 + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * v1) + traceTempVariable$var42$230_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$247_1);
																													if(((Math.log(cv$probabilitySample27Value245) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value245) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value245) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value245) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value245) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value245);
																												}
																											}
																										}
																									}
																								}
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
																for(int index$sample5$222 = 0; index$sample5$222 < weightings.length; index$sample5$222 += 1) {
																	int distributionTempVariable$v1$224 = index$sample5$222;
																	double cv$probabilitySample5Value223 = (1.0 * distribution$sample5[index$sample5$222]);
																	{
																		if(fixedFlag$sample11) {
																			{
																				if((0 == j)) {
																					{
																						int traceTempVariable$var50$248_1 = cv$currentValue;
																						if(((index$i$1 + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * distributionTempVariable$v1$224) + v2[j]) + traceTempVariable$var46$6_1) / traceTempVariable$var50$248_1);
																										if(((Math.log(cv$probabilitySample5Value223) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value223) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value223) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value223) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value223) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value223);
																									}
																								}
																							}
																						}
																					}
																					for(int index$i$249 = 0; index$i$249 < size; index$i$249 += 1) {
																						if(!(index$i$249 == index$i$1)) {
																							for(int index$sample27$250 = 0; index$sample27$250 < weightings.length; index$sample27$250 += 1) {
																								int distributionTempVariable$var27$252 = index$sample27$250;
																								double cv$probabilitySample27Value251 = (cv$probabilitySample5Value223 * distribution$sample27[((index$i$249 - 0) / 1)][index$sample27$250]);
																								{
																									int traceTempVariable$var50$253_1 = distributionTempVariable$var27$252;
																									if(((index$i$249 + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$224) + v2[j]) + traceTempVariable$var46$6_1) / traceTempVariable$var50$253_1);
																													if(((Math.log(cv$probabilitySample27Value251) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value251) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value251) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value251) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value251) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value251);
																												}
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
																				for(int index$sample11$232 = 0; index$sample11$232 < weightings.length; index$sample11$232 += 1) {
																					int distributionTempVariable$var11$234 = index$sample11$232;
																					double cv$probabilitySample11Value233 = (cv$probabilitySample5Value223 * distribution$sample11[index$sample11$232]);
																					{
																						int traceTempVariable$var42$235_1 = distributionTempVariable$var11$234;
																						if((0 == j)) {
																							{
																								int traceTempVariable$var50$254_1 = cv$currentValue;
																								if(((index$i$1 + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * distributionTempVariable$v1$224) + traceTempVariable$var42$235_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$254_1);
																												if(((Math.log(cv$probabilitySample11Value233) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value233) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value233) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value233) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value233) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value233);
																											}
																										}
																									}
																								}
																							}
																							for(int index$i$255 = 0; index$i$255 < size; index$i$255 += 1) {
																								if(!(index$i$255 == index$i$1)) {
																									for(int index$sample27$256 = 0; index$sample27$256 < weightings.length; index$sample27$256 += 1) {
																										int distributionTempVariable$var27$258 = index$sample27$256;
																										double cv$probabilitySample27Value257 = (cv$probabilitySample11Value233 * distribution$sample27[((index$i$255 - 0) / 1)][index$sample27$256]);
																										{
																											int traceTempVariable$var50$259_1 = distributionTempVariable$var27$258;
																											if(((index$i$255 + 1) == (j + 1))) {
																												{
																													{
																														{
																															double var51 = ((((1.0 * distributionTempVariable$v1$224) + traceTempVariable$var42$235_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$259_1);
																															if(((Math.log(cv$probabilitySample27Value257) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value257) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value257) + Math.log((v[j]?var51:(1.0 - var51))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value257) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value257) + Math.log((v[j]?var51:(1.0 - var51)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value257);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample5) {
															{
																{
																	int traceTempVariable$var42$265_1 = cv$currentValue;
																	if(((index$i$1 + 1) == j)) {
																		{
																			int traceTempVariable$var50$277_1 = cv$currentValue;
																			if(((index$i$1 + 1) == (j + 1))) {
																				{
																					{
																						{
																							double var51 = ((((1.0 * v1) + traceTempVariable$var42$265_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$277_1);
																							if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$278 = 0; index$i$278 < size; index$i$278 += 1) {
																			if(!(index$i$278 == index$i$1)) {
																				for(int index$sample27$279 = 0; index$sample27$279 < weightings.length; index$sample27$279 += 1) {
																					int distributionTempVariable$var27$281 = index$sample27$279;
																					double cv$probabilitySample27Value280 = (1.0 * distribution$sample27[((index$i$278 - 0) / 1)][index$sample27$279]);
																					{
																						int traceTempVariable$var50$282_1 = distributionTempVariable$var27$281;
																						if(((index$i$278 + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * v1) + traceTempVariable$var42$265_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$282_1);
																										if(((Math.log(cv$probabilitySample27Value280) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value280) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value280) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value280) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value280) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value280);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$266 = 0; index$i$266 < size; index$i$266 += 1) {
																	if(!(index$i$266 == index$i$1)) {
																		for(int index$sample27$267 = 0; index$sample27$267 < weightings.length; index$sample27$267 += 1) {
																			int distributionTempVariable$var27$269 = index$sample27$267;
																			double cv$probabilitySample27Value268 = (1.0 * distribution$sample27[((index$i$266 - 0) / 1)][index$sample27$267]);
																			{
																				int traceTempVariable$var42$270_1 = distributionTempVariable$var27$269;
																				if(((index$i$266 + 1) == j)) {
																					{
																						int traceTempVariable$var50$283_1 = cv$currentValue;
																						if(((index$i$1 + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * v1) + traceTempVariable$var42$270_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$283_1);
																										if(((Math.log(cv$probabilitySample27Value268) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value268) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value268) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value268) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value268) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value268);
																									}
																								}
																							}
																						}
																					}
																					{
																						int traceTempVariable$var50$284_1 = distributionTempVariable$var27$269;
																						if(((index$i$266 + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * v1) + traceTempVariable$var42$270_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$284_1);
																										if(((Math.log(cv$probabilitySample27Value268) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value268) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value268) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value268) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value268) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value268);
																									}
																								}
																							}
																						}
																					}
																					for(int index$i$285 = 0; index$i$285 < size; index$i$285 += 1) {
																						if((!(index$i$285 == index$i$1) && !(index$i$285 == index$i$266))) {
																							for(int index$sample27$286 = 0; index$sample27$286 < weightings.length; index$sample27$286 += 1) {
																								int distributionTempVariable$var27$288 = index$sample27$286;
																								double cv$probabilitySample27Value287 = (cv$probabilitySample27Value268 * distribution$sample27[((index$i$285 - 0) / 1)][index$sample27$286]);
																								{
																									int traceTempVariable$var50$289_1 = distributionTempVariable$var27$288;
																									if(((index$i$285 + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * v1) + traceTempVariable$var42$270_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$289_1);
																													if(((Math.log(cv$probabilitySample27Value287) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value287) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value287) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value287) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value287) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value287);
																												}
																											}
																										}
																									}
																								}
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
																for(int index$sample5$261 = 0; index$sample5$261 < weightings.length; index$sample5$261 += 1) {
																	int distributionTempVariable$v1$263 = index$sample5$261;
																	double cv$probabilitySample5Value262 = (1.0 * distribution$sample5[index$sample5$261]);
																	{
																		{
																			int traceTempVariable$var42$271_1 = cv$currentValue;
																			if(((index$i$1 + 1) == j)) {
																				{
																					int traceTempVariable$var50$290_1 = cv$currentValue;
																					if(((index$i$1 + 1) == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * distributionTempVariable$v1$263) + traceTempVariable$var42$271_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$290_1);
																									if(((Math.log(cv$probabilitySample5Value262) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value262) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value262) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value262) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value262) + Math.log((v[j]?var51:(1.0 - var51)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value262);
																								}
																							}
																						}
																					}
																				}
																				for(int index$i$291 = 0; index$i$291 < size; index$i$291 += 1) {
																					if(!(index$i$291 == index$i$1)) {
																						for(int index$sample27$292 = 0; index$sample27$292 < weightings.length; index$sample27$292 += 1) {
																							int distributionTempVariable$var27$294 = index$sample27$292;
																							double cv$probabilitySample27Value293 = (cv$probabilitySample5Value262 * distribution$sample27[((index$i$291 - 0) / 1)][index$sample27$292]);
																							{
																								int traceTempVariable$var50$295_1 = distributionTempVariable$var27$294;
																								if(((index$i$291 + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * distributionTempVariable$v1$263) + traceTempVariable$var42$271_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$295_1);
																												if(((Math.log(cv$probabilitySample27Value293) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value293) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value293) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value293) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value293) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value293);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$272 = 0; index$i$272 < size; index$i$272 += 1) {
																			if(!(index$i$272 == index$i$1)) {
																				for(int index$sample27$273 = 0; index$sample27$273 < weightings.length; index$sample27$273 += 1) {
																					int distributionTempVariable$var27$275 = index$sample27$273;
																					double cv$probabilitySample27Value274 = (cv$probabilitySample5Value262 * distribution$sample27[((index$i$272 - 0) / 1)][index$sample27$273]);
																					{
																						int traceTempVariable$var42$276_1 = distributionTempVariable$var27$275;
																						if(((index$i$272 + 1) == j)) {
																							{
																								int traceTempVariable$var50$296_1 = cv$currentValue;
																								if(((index$i$1 + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * distributionTempVariable$v1$263) + traceTempVariable$var42$276_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$296_1);
																												if(((Math.log(cv$probabilitySample27Value274) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value274) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value274) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value274) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value274) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value274);
																											}
																										}
																									}
																								}
																							}
																							{
																								int traceTempVariable$var50$297_1 = distributionTempVariable$var27$275;
																								if(((index$i$272 + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * distributionTempVariable$v1$263) + traceTempVariable$var42$276_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$297_1);
																												if(((Math.log(cv$probabilitySample27Value274) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value274) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value274) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value274) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value274) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value274);
																											}
																										}
																									}
																								}
																							}
																							for(int index$i$298 = 0; index$i$298 < size; index$i$298 += 1) {
																								if((!(index$i$298 == index$i$1) && !(index$i$298 == index$i$272))) {
																									for(int index$sample27$299 = 0; index$sample27$299 < weightings.length; index$sample27$299 += 1) {
																										int distributionTempVariable$var27$301 = index$sample27$299;
																										double cv$probabilitySample27Value300 = (cv$probabilitySample27Value274 * distribution$sample27[((index$i$298 - 0) / 1)][index$sample27$299]);
																										{
																											int traceTempVariable$var50$302_1 = distributionTempVariable$var27$301;
																											if(((index$i$298 + 1) == (j + 1))) {
																												{
																													{
																														{
																															double var51 = ((((1.0 * distributionTempVariable$v1$263) + traceTempVariable$var42$276_1) + traceTempVariable$var46$6_1) / traceTempVariable$var50$302_1);
																															if(((Math.log(cv$probabilitySample27Value300) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value300) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value300) + Math.log((v[j]?var51:(1.0 - var51))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value300) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value300) + Math.log((v[j]?var51:(1.0 - var51)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value300);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
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
							{
								int traceTempVariable$var50$7_1 = cv$currentValue;
								for(int j = 0; j < size; j += 1) {
									if(((i + 1) == (j + 1))) {
										if(!guard$sample27bernoulli52[((j - 0) / 1)]) {
											guard$sample27bernoulli52[((j - 0) / 1)] = true;
											{
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if(fixedFlag$sample5) {
															{
																if(fixedFlag$sample11) {
																	{
																		if((0 == j)) {
																			{
																				if((0 == (j + 1))) {
																					{
																						{
																							{
																								double var51 = ((((1.0 * v1) + v2[j]) + v2[(j + 1)]) / traceTempVariable$var50$7_1);
																								if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																} else {
																	if(true) {
																		for(int index$sample11$309 = 0; index$sample11$309 < weightings.length; index$sample11$309 += 1) {
																			int distributionTempVariable$var11$311 = index$sample11$309;
																			double cv$probabilitySample11Value310 = (1.0 * distribution$sample11[index$sample11$309]);
																			{
																				int traceTempVariable$var42$312_1 = distributionTempVariable$var11$311;
																				if((0 == j)) {
																					{
																						int traceTempVariable$var46$319_1 = distributionTempVariable$var11$311;
																						if((0 == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * v1) + traceTempVariable$var42$312_1) + traceTempVariable$var46$319_1) / traceTempVariable$var50$7_1);
																										if(((Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value310);
																									}
																								}
																							}
																						}
																					}
																					if(!true) {
																						for(int index$sample11$320 = 0; index$sample11$320 < weightings.length; index$sample11$320 += 1) {
																							int distributionTempVariable$var11$322 = index$sample11$320;
																							double cv$probabilitySample11Value321 = (cv$probabilitySample11Value310 * distribution$sample11[index$sample11$320]);
																							{
																								int traceTempVariable$var46$323_1 = distributionTempVariable$var11$322;
																								if((0 == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * v1) + traceTempVariable$var42$312_1) + traceTempVariable$var46$323_1) / traceTempVariable$var50$7_1);
																												if(((Math.log(cv$probabilitySample11Value321) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value321) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value321) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value321) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value321) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value321);
																											}
																										}
																									}
																								}
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
																for(int index$sample5$304 = 0; index$sample5$304 < weightings.length; index$sample5$304 += 1) {
																	int distributionTempVariable$v1$306 = index$sample5$304;
																	double cv$probabilitySample5Value305 = (1.0 * distribution$sample5[index$sample5$304]);
																	{
																		if(fixedFlag$sample11) {
																			{
																				if((0 == j)) {
																					{
																						if((0 == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * distributionTempVariable$v1$306) + v2[j]) + v2[(j + 1)]) / traceTempVariable$var50$7_1);
																										if(((Math.log(cv$probabilitySample5Value305) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value305) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value305) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value305) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value305) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value305);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample11$314 = 0; index$sample11$314 < weightings.length; index$sample11$314 += 1) {
																					int distributionTempVariable$var11$316 = index$sample11$314;
																					double cv$probabilitySample11Value315 = (cv$probabilitySample5Value305 * distribution$sample11[index$sample11$314]);
																					{
																						int traceTempVariable$var42$317_1 = distributionTempVariable$var11$316;
																						if((0 == j)) {
																							{
																								int traceTempVariable$var46$325_1 = distributionTempVariable$var11$316;
																								if((0 == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * distributionTempVariable$v1$306) + traceTempVariable$var42$317_1) + traceTempVariable$var46$325_1) / traceTempVariable$var50$7_1);
																												if(((Math.log(cv$probabilitySample11Value315) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value315) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value315) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value315) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value315) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value315);
																											}
																										}
																									}
																								}
																							}
																							if(!true) {
																								for(int index$sample11$326 = 0; index$sample11$326 < weightings.length; index$sample11$326 += 1) {
																									int distributionTempVariable$var11$328 = index$sample11$326;
																									double cv$probabilitySample11Value327 = (cv$probabilitySample11Value315 * distribution$sample11[index$sample11$326]);
																									{
																										int traceTempVariable$var46$329_1 = distributionTempVariable$var11$328;
																										if((0 == (j + 1))) {
																											{
																												{
																													{
																														double var51 = ((((1.0 * distributionTempVariable$v1$306) + traceTempVariable$var42$317_1) + traceTempVariable$var46$329_1) / traceTempVariable$var50$7_1);
																														if(((Math.log(cv$probabilitySample11Value327) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value327) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value327) + Math.log((v[j]?var51:(1.0 - var51))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value327) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value327) + Math.log((v[j]?var51:(1.0 - var51)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value327);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample5) {
															{
																{
																	int traceTempVariable$var42$335_1 = cv$currentValue;
																	if(((index$i$1 + 1) == j)) {
																		if(fixedFlag$sample11) {
																			{
																				if((0 == (j + 1))) {
																					{
																						{
																							{
																								double var51 = ((((1.0 * v1) + traceTempVariable$var42$335_1) + v2[(j + 1)]) / traceTempVariable$var50$7_1);
																								if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample11$348 = 0; index$sample11$348 < weightings.length; index$sample11$348 += 1) {
																					int distributionTempVariable$var11$350 = index$sample11$348;
																					double cv$probabilitySample11Value349 = (1.0 * distribution$sample11[index$sample11$348]);
																					{
																						int traceTempVariable$var46$351_1 = distributionTempVariable$var11$350;
																						if((0 == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * v1) + traceTempVariable$var42$335_1) + traceTempVariable$var46$351_1) / traceTempVariable$var50$7_1);
																										if(((Math.log(cv$probabilitySample11Value349) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value349) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value349) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value349) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value349) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value349);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$336 = 0; index$i$336 < size; index$i$336 += 1) {
																	if(!(index$i$336 == index$i$1)) {
																		for(int index$sample27$337 = 0; index$sample27$337 < weightings.length; index$sample27$337 += 1) {
																			int distributionTempVariable$var27$339 = index$sample27$337;
																			double cv$probabilitySample27Value338 = (1.0 * distribution$sample27[((index$i$336 - 0) / 1)][index$sample27$337]);
																			{
																				int traceTempVariable$var42$340_1 = distributionTempVariable$var27$339;
																				if(((index$i$336 + 1) == j)) {
																					if(fixedFlag$sample11) {
																						{
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + traceTempVariable$var42$340_1) + v2[(j + 1)]) / traceTempVariable$var50$7_1);
																											if(((Math.log(cv$probabilitySample27Value338) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value338) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value338) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value338) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value338) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value338);
																										}
																									}
																								}
																							}
																						}
																					} else {
																						if(true) {
																							for(int index$sample11$353 = 0; index$sample11$353 < weightings.length; index$sample11$353 += 1) {
																								int distributionTempVariable$var11$355 = index$sample11$353;
																								double cv$probabilitySample11Value354 = (cv$probabilitySample27Value338 * distribution$sample11[index$sample11$353]);
																								{
																									int traceTempVariable$var46$356_1 = distributionTempVariable$var11$355;
																									if((0 == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * v1) + traceTempVariable$var42$340_1) + traceTempVariable$var46$356_1) / traceTempVariable$var50$7_1);
																													if(((Math.log(cv$probabilitySample11Value354) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value354) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value354) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value354) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value354) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value354);
																												}
																											}
																										}
																									}
																								}
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
																for(int index$sample5$331 = 0; index$sample5$331 < weightings.length; index$sample5$331 += 1) {
																	int distributionTempVariable$v1$333 = index$sample5$331;
																	double cv$probabilitySample5Value332 = (1.0 * distribution$sample5[index$sample5$331]);
																	{
																		{
																			int traceTempVariable$var42$341_1 = cv$currentValue;
																			if(((index$i$1 + 1) == j)) {
																				if(fixedFlag$sample11) {
																					{
																						if((0 == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * distributionTempVariable$v1$333) + traceTempVariable$var42$341_1) + v2[(j + 1)]) / traceTempVariable$var50$7_1);
																										if(((Math.log(cv$probabilitySample5Value332) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value332) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value332) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value332) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value332) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value332);
																									}
																								}
																							}
																						}
																					}
																				} else {
																					if(true) {
																						for(int index$sample11$358 = 0; index$sample11$358 < weightings.length; index$sample11$358 += 1) {
																							int distributionTempVariable$var11$360 = index$sample11$358;
																							double cv$probabilitySample11Value359 = (cv$probabilitySample5Value332 * distribution$sample11[index$sample11$358]);
																							{
																								int traceTempVariable$var46$361_1 = distributionTempVariable$var11$360;
																								if((0 == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * distributionTempVariable$v1$333) + traceTempVariable$var42$341_1) + traceTempVariable$var46$361_1) / traceTempVariable$var50$7_1);
																												if(((Math.log(cv$probabilitySample11Value359) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value359) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value359) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value359) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value359) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value359);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$342 = 0; index$i$342 < size; index$i$342 += 1) {
																			if(!(index$i$342 == index$i$1)) {
																				for(int index$sample27$343 = 0; index$sample27$343 < weightings.length; index$sample27$343 += 1) {
																					int distributionTempVariable$var27$345 = index$sample27$343;
																					double cv$probabilitySample27Value344 = (cv$probabilitySample5Value332 * distribution$sample27[((index$i$342 - 0) / 1)][index$sample27$343]);
																					{
																						int traceTempVariable$var42$346_1 = distributionTempVariable$var27$345;
																						if(((index$i$342 + 1) == j)) {
																							if(fixedFlag$sample11) {
																								{
																									if((0 == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$333) + traceTempVariable$var42$346_1) + v2[(j + 1)]) / traceTempVariable$var50$7_1);
																													if(((Math.log(cv$probabilitySample27Value344) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value344) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value344) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value344) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value344) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value344);
																												}
																											}
																										}
																									}
																								}
																							} else {
																								if(true) {
																									for(int index$sample11$363 = 0; index$sample11$363 < weightings.length; index$sample11$363 += 1) {
																										int distributionTempVariable$var11$365 = index$sample11$363;
																										double cv$probabilitySample11Value364 = (cv$probabilitySample27Value344 * distribution$sample11[index$sample11$363]);
																										{
																											int traceTempVariable$var46$366_1 = distributionTempVariable$var11$365;
																											if((0 == (j + 1))) {
																												{
																													{
																														{
																															double var51 = ((((1.0 * distributionTempVariable$v1$333) + traceTempVariable$var42$346_1) + traceTempVariable$var46$366_1) / traceTempVariable$var50$7_1);
																															if(((Math.log(cv$probabilitySample11Value364) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value364) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value364) + Math.log((v[j]?var51:(1.0 - var51))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value364) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value364) + Math.log((v[j]?var51:(1.0 - var51)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value364);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample5) {
															{
																if(fixedFlag$sample11) {
																	{
																		if((0 == j)) {
																			{
																				int traceTempVariable$var46$382_1 = cv$currentValue;
																				if(((index$i$1 + 1) == (j + 1))) {
																					{
																						{
																							{
																								double var51 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var46$382_1) / traceTempVariable$var50$7_1);
																								if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																			for(int index$i$383 = 0; index$i$383 < size; index$i$383 += 1) {
																				if(!(index$i$383 == index$i$1)) {
																					for(int index$sample27$384 = 0; index$sample27$384 < weightings.length; index$sample27$384 += 1) {
																						int distributionTempVariable$var27$386 = index$sample27$384;
																						double cv$probabilitySample27Value385 = (1.0 * distribution$sample27[((index$i$383 - 0) / 1)][index$sample27$384]);
																						{
																							int traceTempVariable$var46$387_1 = distributionTempVariable$var27$386;
																							if(((index$i$383 + 1) == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var46$387_1) / traceTempVariable$var50$7_1);
																											if(((Math.log(cv$probabilitySample27Value385) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value385) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value385) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value385) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value385) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value385);
																										}
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
																		for(int index$sample11$373 = 0; index$sample11$373 < weightings.length; index$sample11$373 += 1) {
																			int distributionTempVariable$var11$375 = index$sample11$373;
																			double cv$probabilitySample11Value374 = (1.0 * distribution$sample11[index$sample11$373]);
																			{
																				int traceTempVariable$var42$376_1 = distributionTempVariable$var11$375;
																				if((0 == j)) {
																					{
																						int traceTempVariable$var46$388_1 = cv$currentValue;
																						if(((index$i$1 + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * v1) + traceTempVariable$var42$376_1) + traceTempVariable$var46$388_1) / traceTempVariable$var50$7_1);
																										if(((Math.log(cv$probabilitySample11Value374) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value374) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value374) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value374) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value374) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value374);
																									}
																								}
																							}
																						}
																					}
																					for(int index$i$389 = 0; index$i$389 < size; index$i$389 += 1) {
																						if(!(index$i$389 == index$i$1)) {
																							for(int index$sample27$390 = 0; index$sample27$390 < weightings.length; index$sample27$390 += 1) {
																								int distributionTempVariable$var27$392 = index$sample27$390;
																								double cv$probabilitySample27Value391 = (cv$probabilitySample11Value374 * distribution$sample27[((index$i$389 - 0) / 1)][index$sample27$390]);
																								{
																									int traceTempVariable$var46$393_1 = distributionTempVariable$var27$392;
																									if(((index$i$389 + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * v1) + traceTempVariable$var42$376_1) + traceTempVariable$var46$393_1) / traceTempVariable$var50$7_1);
																													if(((Math.log(cv$probabilitySample27Value391) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value391) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value391) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value391) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value391) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value391);
																												}
																											}
																										}
																									}
																								}
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
																for(int index$sample5$368 = 0; index$sample5$368 < weightings.length; index$sample5$368 += 1) {
																	int distributionTempVariable$v1$370 = index$sample5$368;
																	double cv$probabilitySample5Value369 = (1.0 * distribution$sample5[index$sample5$368]);
																	{
																		if(fixedFlag$sample11) {
																			{
																				if((0 == j)) {
																					{
																						int traceTempVariable$var46$394_1 = cv$currentValue;
																						if(((index$i$1 + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * distributionTempVariable$v1$370) + v2[j]) + traceTempVariable$var46$394_1) / traceTempVariable$var50$7_1);
																										if(((Math.log(cv$probabilitySample5Value369) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value369) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value369) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value369) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value369) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value369);
																									}
																								}
																							}
																						}
																					}
																					for(int index$i$395 = 0; index$i$395 < size; index$i$395 += 1) {
																						if(!(index$i$395 == index$i$1)) {
																							for(int index$sample27$396 = 0; index$sample27$396 < weightings.length; index$sample27$396 += 1) {
																								int distributionTempVariable$var27$398 = index$sample27$396;
																								double cv$probabilitySample27Value397 = (cv$probabilitySample5Value369 * distribution$sample27[((index$i$395 - 0) / 1)][index$sample27$396]);
																								{
																									int traceTempVariable$var46$399_1 = distributionTempVariable$var27$398;
																									if(((index$i$395 + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * distributionTempVariable$v1$370) + v2[j]) + traceTempVariable$var46$399_1) / traceTempVariable$var50$7_1);
																													if(((Math.log(cv$probabilitySample27Value397) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value397) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value397) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value397) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value397) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value397);
																												}
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
																				for(int index$sample11$378 = 0; index$sample11$378 < weightings.length; index$sample11$378 += 1) {
																					int distributionTempVariable$var11$380 = index$sample11$378;
																					double cv$probabilitySample11Value379 = (cv$probabilitySample5Value369 * distribution$sample11[index$sample11$378]);
																					{
																						int traceTempVariable$var42$381_1 = distributionTempVariable$var11$380;
																						if((0 == j)) {
																							{
																								int traceTempVariable$var46$400_1 = cv$currentValue;
																								if(((index$i$1 + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * distributionTempVariable$v1$370) + traceTempVariable$var42$381_1) + traceTempVariable$var46$400_1) / traceTempVariable$var50$7_1);
																												if(((Math.log(cv$probabilitySample11Value379) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value379) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value379) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value379) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value379) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value379);
																											}
																										}
																									}
																								}
																							}
																							for(int index$i$401 = 0; index$i$401 < size; index$i$401 += 1) {
																								if(!(index$i$401 == index$i$1)) {
																									for(int index$sample27$402 = 0; index$sample27$402 < weightings.length; index$sample27$402 += 1) {
																										int distributionTempVariable$var27$404 = index$sample27$402;
																										double cv$probabilitySample27Value403 = (cv$probabilitySample11Value379 * distribution$sample27[((index$i$401 - 0) / 1)][index$sample27$402]);
																										{
																											int traceTempVariable$var46$405_1 = distributionTempVariable$var27$404;
																											if(((index$i$401 + 1) == (j + 1))) {
																												{
																													{
																														{
																															double var51 = ((((1.0 * distributionTempVariable$v1$370) + traceTempVariable$var42$381_1) + traceTempVariable$var46$405_1) / traceTempVariable$var50$7_1);
																															if(((Math.log(cv$probabilitySample27Value403) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value403) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value403) + Math.log((v[j]?var51:(1.0 - var51))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value403) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value403) + Math.log((v[j]?var51:(1.0 - var51)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value403);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample5) {
															{
																{
																	int traceTempVariable$var42$411_1 = cv$currentValue;
																	if(((index$i$1 + 1) == j)) {
																		{
																			int traceTempVariable$var46$423_1 = cv$currentValue;
																			if(((index$i$1 + 1) == (j + 1))) {
																				{
																					{
																						{
																							double var51 = ((((1.0 * v1) + traceTempVariable$var42$411_1) + traceTempVariable$var46$423_1) / traceTempVariable$var50$7_1);
																							if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$424 = 0; index$i$424 < size; index$i$424 += 1) {
																			if(!(index$i$424 == index$i$1)) {
																				for(int index$sample27$425 = 0; index$sample27$425 < weightings.length; index$sample27$425 += 1) {
																					int distributionTempVariable$var27$427 = index$sample27$425;
																					double cv$probabilitySample27Value426 = (1.0 * distribution$sample27[((index$i$424 - 0) / 1)][index$sample27$425]);
																					{
																						int traceTempVariable$var46$428_1 = distributionTempVariable$var27$427;
																						if(((index$i$424 + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * v1) + traceTempVariable$var42$411_1) + traceTempVariable$var46$428_1) / traceTempVariable$var50$7_1);
																										if(((Math.log(cv$probabilitySample27Value426) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value426) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value426) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value426) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value426) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value426);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$412 = 0; index$i$412 < size; index$i$412 += 1) {
																	if(!(index$i$412 == index$i$1)) {
																		for(int index$sample27$413 = 0; index$sample27$413 < weightings.length; index$sample27$413 += 1) {
																			int distributionTempVariable$var27$415 = index$sample27$413;
																			double cv$probabilitySample27Value414 = (1.0 * distribution$sample27[((index$i$412 - 0) / 1)][index$sample27$413]);
																			{
																				int traceTempVariable$var42$416_1 = distributionTempVariable$var27$415;
																				if(((index$i$412 + 1) == j)) {
																					{
																						int traceTempVariable$var46$429_1 = cv$currentValue;
																						if(((index$i$1 + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * v1) + traceTempVariable$var42$416_1) + traceTempVariable$var46$429_1) / traceTempVariable$var50$7_1);
																										if(((Math.log(cv$probabilitySample27Value414) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value414) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value414) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value414) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value414) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value414);
																									}
																								}
																							}
																						}
																					}
																					{
																						int traceTempVariable$var46$430_1 = distributionTempVariable$var27$415;
																						if(((index$i$412 + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * v1) + traceTempVariable$var42$416_1) + traceTempVariable$var46$430_1) / traceTempVariable$var50$7_1);
																										if(((Math.log(cv$probabilitySample27Value414) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value414) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value414) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value414) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value414) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value414);
																									}
																								}
																							}
																						}
																					}
																					for(int index$i$431 = 0; index$i$431 < size; index$i$431 += 1) {
																						if((!(index$i$431 == index$i$1) && !(index$i$431 == index$i$412))) {
																							for(int index$sample27$432 = 0; index$sample27$432 < weightings.length; index$sample27$432 += 1) {
																								int distributionTempVariable$var27$434 = index$sample27$432;
																								double cv$probabilitySample27Value433 = (cv$probabilitySample27Value414 * distribution$sample27[((index$i$431 - 0) / 1)][index$sample27$432]);
																								{
																									int traceTempVariable$var46$435_1 = distributionTempVariable$var27$434;
																									if(((index$i$431 + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * v1) + traceTempVariable$var42$416_1) + traceTempVariable$var46$435_1) / traceTempVariable$var50$7_1);
																													if(((Math.log(cv$probabilitySample27Value433) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value433) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value433) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value433) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value433) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value433);
																												}
																											}
																										}
																									}
																								}
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
																for(int index$sample5$407 = 0; index$sample5$407 < weightings.length; index$sample5$407 += 1) {
																	int distributionTempVariable$v1$409 = index$sample5$407;
																	double cv$probabilitySample5Value408 = (1.0 * distribution$sample5[index$sample5$407]);
																	{
																		{
																			int traceTempVariable$var42$417_1 = cv$currentValue;
																			if(((index$i$1 + 1) == j)) {
																				{
																					int traceTempVariable$var46$436_1 = cv$currentValue;
																					if(((index$i$1 + 1) == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * distributionTempVariable$v1$409) + traceTempVariable$var42$417_1) + traceTempVariable$var46$436_1) / traceTempVariable$var50$7_1);
																									if(((Math.log(cv$probabilitySample5Value408) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value408) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value408) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value408) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample5Value408) + Math.log((v[j]?var51:(1.0 - var51)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value408);
																								}
																							}
																						}
																					}
																				}
																				for(int index$i$437 = 0; index$i$437 < size; index$i$437 += 1) {
																					if(!(index$i$437 == index$i$1)) {
																						for(int index$sample27$438 = 0; index$sample27$438 < weightings.length; index$sample27$438 += 1) {
																							int distributionTempVariable$var27$440 = index$sample27$438;
																							double cv$probabilitySample27Value439 = (cv$probabilitySample5Value408 * distribution$sample27[((index$i$437 - 0) / 1)][index$sample27$438]);
																							{
																								int traceTempVariable$var46$441_1 = distributionTempVariable$var27$440;
																								if(((index$i$437 + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * distributionTempVariable$v1$409) + traceTempVariable$var42$417_1) + traceTempVariable$var46$441_1) / traceTempVariable$var50$7_1);
																												if(((Math.log(cv$probabilitySample27Value439) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value439) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value439) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value439) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value439) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value439);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$418 = 0; index$i$418 < size; index$i$418 += 1) {
																			if(!(index$i$418 == index$i$1)) {
																				for(int index$sample27$419 = 0; index$sample27$419 < weightings.length; index$sample27$419 += 1) {
																					int distributionTempVariable$var27$421 = index$sample27$419;
																					double cv$probabilitySample27Value420 = (cv$probabilitySample5Value408 * distribution$sample27[((index$i$418 - 0) / 1)][index$sample27$419]);
																					{
																						int traceTempVariable$var42$422_1 = distributionTempVariable$var27$421;
																						if(((index$i$418 + 1) == j)) {
																							{
																								int traceTempVariable$var46$442_1 = cv$currentValue;
																								if(((index$i$1 + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * distributionTempVariable$v1$409) + traceTempVariable$var42$422_1) + traceTempVariable$var46$442_1) / traceTempVariable$var50$7_1);
																												if(((Math.log(cv$probabilitySample27Value420) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value420) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value420) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value420) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value420) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value420);
																											}
																										}
																									}
																								}
																							}
																							{
																								int traceTempVariable$var46$443_1 = distributionTempVariable$var27$421;
																								if(((index$i$418 + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * distributionTempVariable$v1$409) + traceTempVariable$var42$422_1) + traceTempVariable$var46$443_1) / traceTempVariable$var50$7_1);
																												if(((Math.log(cv$probabilitySample27Value420) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value420) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value420) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value420) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value420) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value420);
																											}
																										}
																									}
																								}
																							}
																							for(int index$i$444 = 0; index$i$444 < size; index$i$444 += 1) {
																								if((!(index$i$444 == index$i$1) && !(index$i$444 == index$i$418))) {
																									for(int index$sample27$445 = 0; index$sample27$445 < weightings.length; index$sample27$445 += 1) {
																										int distributionTempVariable$var27$447 = index$sample27$445;
																										double cv$probabilitySample27Value446 = (cv$probabilitySample27Value420 * distribution$sample27[((index$i$444 - 0) / 1)][index$sample27$445]);
																										{
																											int traceTempVariable$var46$448_1 = distributionTempVariable$var27$447;
																											if(((index$i$444 + 1) == (j + 1))) {
																												{
																													{
																														{
																															double var51 = ((((1.0 * distributionTempVariable$v1$409) + traceTempVariable$var42$422_1) + traceTempVariable$var46$448_1) / traceTempVariable$var50$7_1);
																															if(((Math.log(cv$probabilitySample27Value446) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value446) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value446) + Math.log((v[j]?var51:(1.0 - var51))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value446) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value446) + Math.log((v[j]?var51:(1.0 - var51)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value446);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
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
			double[] cv$localProbability = distribution$sample27[((i - 0) / 1)];
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
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void sample5() {
		if(true) {
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, weightings.length);
			}
			double[] cv$stateProbabilityLocal = cv$var5$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					int $var2585 = weightings.length;
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < $var2585))?Math.log(weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								for(int j = 0; j < size; j += 1) {
									int traceTempVariable$v1$1_2 = cv$currentValue;
									{
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if(fixedFlag$sample11) {
													{
														if((0 == j)) {
															{
																if((0 == (j + 1))) {
																	{
																		if((0 == (j + 1))) {
																			{
																				{
																					{
																						double var51 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																						if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
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
													}
												} else {
													if(true) {
														for(int index$sample11$4 = 0; index$sample11$4 < weightings.length; index$sample11$4 += 1) {
															int distributionTempVariable$var11$6 = index$sample11$4;
															double cv$probabilitySample11Value5 = (1.0 * distribution$sample11[index$sample11$4]);
															{
																int traceTempVariable$var42$7_1 = distributionTempVariable$var11$6;
																if((0 == j)) {
																	{
																		int traceTempVariable$var46$9_1 = distributionTempVariable$var11$6;
																		if((0 == (j + 1))) {
																			{
																				int traceTempVariable$var50$15_1 = distributionTempVariable$var11$6;
																				if((0 == (j + 1))) {
																					{
																						{
																							{
																								double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$7_1) + traceTempVariable$var46$9_1) / traceTempVariable$var50$15_1);
																								if(((Math.log(cv$probabilitySample11Value5) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value5) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value5) + Math.log((v[j]?var51:(1.0 - var51))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value5) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value5) + Math.log((v[j]?var51:(1.0 - var51)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value5);
																							}
																						}
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample11$16 = 0; index$sample11$16 < weightings.length; index$sample11$16 += 1) {
																					int distributionTempVariable$var11$18 = index$sample11$16;
																					double cv$probabilitySample11Value17 = (cv$probabilitySample11Value5 * distribution$sample11[index$sample11$16]);
																					{
																						int traceTempVariable$var50$19_1 = distributionTempVariable$var11$18;
																						if((0 == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$7_1) + traceTempVariable$var46$9_1) / traceTempVariable$var50$19_1);
																										if(((Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value17);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample11$10 = 0; index$sample11$10 < weightings.length; index$sample11$10 += 1) {
																			int distributionTempVariable$var11$12 = index$sample11$10;
																			double cv$probabilitySample11Value11 = (cv$probabilitySample11Value5 * distribution$sample11[index$sample11$10]);
																			{
																				int traceTempVariable$var46$13_1 = distributionTempVariable$var11$12;
																				if((0 == (j + 1))) {
																					{
																						int traceTempVariable$var50$20_1 = distributionTempVariable$var11$6;
																						if((0 == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$7_1) + traceTempVariable$var46$13_1) / traceTempVariable$var50$20_1);
																										if(((Math.log(cv$probabilitySample11Value11) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value11) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value11) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value11) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value11) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value11);
																									}
																								}
																							}
																						}
																					}
																					{
																						int traceTempVariable$var50$21_1 = distributionTempVariable$var11$12;
																						if((0 == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$7_1) + traceTempVariable$var46$13_1) / traceTempVariable$var50$21_1);
																										if(((Math.log(cv$probabilitySample11Value11) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value11) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value11) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value11) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value11) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value11);
																									}
																								}
																							}
																						}
																					}
																					if(!true) {
																						for(int index$sample11$22 = 0; index$sample11$22 < weightings.length; index$sample11$22 += 1) {
																							int distributionTempVariable$var11$24 = index$sample11$22;
																							double cv$probabilitySample11Value23 = (cv$probabilitySample11Value11 * distribution$sample11[index$sample11$22]);
																							{
																								int traceTempVariable$var50$25_1 = distributionTempVariable$var11$24;
																								if((0 == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$7_1) + traceTempVariable$var46$13_1) / traceTempVariable$var50$25_1);
																												if(((Math.log(cv$probabilitySample11Value23) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value23) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value23) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value23) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value23) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value23);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
												if(fixedFlag$sample27) {
													{
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == j)) {
																if(fixedFlag$sample11) {
																	{
																		if((0 == (j + 1))) {
																			{
																				if((0 == (j + 1))) {
																					{
																						{
																							{
																								double var51 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																								if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																} else {
																	if(true) {
																		for(int index$sample11$33 = 0; index$sample11$33 < weightings.length; index$sample11$33 += 1) {
																			int distributionTempVariable$var11$35 = index$sample11$33;
																			double cv$probabilitySample11Value34 = (1.0 * distribution$sample11[index$sample11$33]);
																			{
																				int traceTempVariable$var46$36_1 = distributionTempVariable$var11$35;
																				if((0 == (j + 1))) {
																					{
																						int traceTempVariable$var50$43_1 = distributionTempVariable$var11$35;
																						if((0 == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var46$36_1) / traceTempVariable$var50$43_1);
																										if(((Math.log(cv$probabilitySample11Value34) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value34) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value34) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value34) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value34) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value34);
																									}
																								}
																							}
																						}
																					}
																					if(!true) {
																						for(int index$sample11$44 = 0; index$sample11$44 < weightings.length; index$sample11$44 += 1) {
																							int distributionTempVariable$var11$46 = index$sample11$44;
																							double cv$probabilitySample11Value45 = (cv$probabilitySample11Value34 * distribution$sample11[index$sample11$44]);
																							{
																								int traceTempVariable$var50$47_1 = distributionTempVariable$var11$46;
																								if((0 == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var46$36_1) / traceTempVariable$var50$47_1);
																												if(((Math.log(cv$probabilitySample11Value45) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value45) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value45) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value45) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value45) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value45);
																											}
																										}
																									}
																								}
																							}
																						}
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
															for(int index$sample27$28 = 0; index$sample27$28 < weightings.length; index$sample27$28 += 1) {
																int distributionTempVariable$var27$30 = index$sample27$28;
																double cv$probabilitySample27Value29 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$28]);
																{
																	int traceTempVariable$var42$31_1 = distributionTempVariable$var27$30;
																	if(((i + 1) == j)) {
																		if(fixedFlag$sample11) {
																			{
																				if((0 == (j + 1))) {
																					{
																						if((0 == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$31_1) + v2[(j + 1)]) / v2[(j + 1)]);
																										if(((Math.log(cv$probabilitySample27Value29) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value29) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value29) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value29) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value29) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value29);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample11$38 = 0; index$sample11$38 < weightings.length; index$sample11$38 += 1) {
																					int distributionTempVariable$var11$40 = index$sample11$38;
																					double cv$probabilitySample11Value39 = (cv$probabilitySample27Value29 * distribution$sample11[index$sample11$38]);
																					{
																						int traceTempVariable$var46$41_1 = distributionTempVariable$var11$40;
																						if((0 == (j + 1))) {
																							{
																								int traceTempVariable$var50$49_1 = distributionTempVariable$var11$40;
																								if((0 == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$31_1) + traceTempVariable$var46$41_1) / traceTempVariable$var50$49_1);
																												if(((Math.log(cv$probabilitySample11Value39) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value39) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value39) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value39) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value39) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value39);
																											}
																										}
																									}
																								}
																							}
																							if(!true) {
																								for(int index$sample11$50 = 0; index$sample11$50 < weightings.length; index$sample11$50 += 1) {
																									int distributionTempVariable$var11$52 = index$sample11$50;
																									double cv$probabilitySample11Value51 = (cv$probabilitySample11Value39 * distribution$sample11[index$sample11$50]);
																									{
																										int traceTempVariable$var50$53_1 = distributionTempVariable$var11$52;
																										if((0 == (j + 1))) {
																											{
																												{
																													{
																														double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$31_1) + traceTempVariable$var46$41_1) / traceTempVariable$var50$53_1);
																														if(((Math.log(cv$probabilitySample11Value51) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value51) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value51) + Math.log((v[j]?var51:(1.0 - var51))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value51) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value51) + Math.log((v[j]?var51:(1.0 - var51)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value51);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
												if(fixedFlag$sample11) {
													{
														if((0 == j)) {
															if(fixedFlag$sample27) {
																{
																	for(int i = 0; i < size; i += 1) {
																		if(((i + 1) == (j + 1))) {
																			{
																				if((0 == (j + 1))) {
																					{
																						{
																							{
																								double var51 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																								if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
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
																		for(int index$sample27$61 = 0; index$sample27$61 < weightings.length; index$sample27$61 += 1) {
																			int distributionTempVariable$var27$63 = index$sample27$61;
																			double cv$probabilitySample27Value62 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$61]);
																			{
																				int traceTempVariable$var46$64_1 = distributionTempVariable$var27$63;
																				if(((i + 1) == (j + 1))) {
																					{
																						if((0 == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var46$64_1) / v2[(j + 1)]);
																										if(((Math.log(cv$probabilitySample27Value62) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value62) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value62) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value62) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value62) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value62);
																									}
																								}
																							}
																						}
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
														for(int index$sample11$55 = 0; index$sample11$55 < weightings.length; index$sample11$55 += 1) {
															int distributionTempVariable$var11$57 = index$sample11$55;
															double cv$probabilitySample11Value56 = (1.0 * distribution$sample11[index$sample11$55]);
															{
																int traceTempVariable$var42$58_1 = distributionTempVariable$var11$57;
																if((0 == j)) {
																	if(fixedFlag$sample27) {
																		{
																			for(int i = 0; i < size; i += 1) {
																				if(((i + 1) == (j + 1))) {
																					{
																						int traceTempVariable$var50$73_1 = distributionTempVariable$var11$57;
																						if((0 == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$58_1) + v2[(j + 1)]) / traceTempVariable$var50$73_1);
																										if(((Math.log(cv$probabilitySample11Value56) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value56) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value56) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value56) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value56) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value56);
																									}
																								}
																							}
																						}
																					}
																					if(!true) {
																						for(int index$sample11$74 = 0; index$sample11$74 < weightings.length; index$sample11$74 += 1) {
																							int distributionTempVariable$var11$76 = index$sample11$74;
																							double cv$probabilitySample11Value75 = (cv$probabilitySample11Value56 * distribution$sample11[index$sample11$74]);
																							{
																								int traceTempVariable$var50$77_1 = distributionTempVariable$var11$76;
																								if((0 == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$58_1) + v2[(j + 1)]) / traceTempVariable$var50$77_1);
																												if(((Math.log(cv$probabilitySample11Value75) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value75) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value75) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value75) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value75) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value75);
																											}
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
																				for(int index$sample27$67 = 0; index$sample27$67 < weightings.length; index$sample27$67 += 1) {
																					int distributionTempVariable$var27$69 = index$sample27$67;
																					double cv$probabilitySample27Value68 = (cv$probabilitySample11Value56 * distribution$sample27[((i - 0) / 1)][index$sample27$67]);
																					{
																						int traceTempVariable$var46$70_1 = distributionTempVariable$var27$69;
																						if(((i + 1) == (j + 1))) {
																							{
																								int traceTempVariable$var50$78_1 = distributionTempVariable$var11$57;
																								if((0 == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$58_1) + traceTempVariable$var46$70_1) / traceTempVariable$var50$78_1);
																												if(((Math.log(cv$probabilitySample27Value68) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value68) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value68) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value68) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value68) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value68);
																											}
																										}
																									}
																								}
																							}
																							if(!true) {
																								for(int index$sample11$79 = 0; index$sample11$79 < weightings.length; index$sample11$79 += 1) {
																									int distributionTempVariable$var11$81 = index$sample11$79;
																									double cv$probabilitySample11Value80 = (cv$probabilitySample27Value68 * distribution$sample11[index$sample11$79]);
																									{
																										int traceTempVariable$var50$82_1 = distributionTempVariable$var11$81;
																										if((0 == (j + 1))) {
																											{
																												{
																													{
																														double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$58_1) + traceTempVariable$var46$70_1) / traceTempVariable$var50$82_1);
																														if(((Math.log(cv$probabilitySample11Value80) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value80) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value80) + Math.log((v[j]?var51:(1.0 - var51))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value80) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value80) + Math.log((v[j]?var51:(1.0 - var51)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value80);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
												if(fixedFlag$sample27) {
													{
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == j)) {
																{
																	for(int index$i$89_1 = 0; index$i$89_1 < size; index$i$89_1 += 1) {
																		if(((index$i$89_1 + 1) == (j + 1))) {
																			if(fixedFlag$sample11) {
																				{
																					if((0 == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																									if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																								}
																							}
																						}
																					}
																				}
																			} else {
																				if(true) {
																					for(int index$sample11$97 = 0; index$sample11$97 < weightings.length; index$sample11$97 += 1) {
																						int distributionTempVariable$var11$99 = index$sample11$97;
																						double cv$probabilitySample11Value98 = (1.0 * distribution$sample11[index$sample11$97]);
																						{
																							int traceTempVariable$var50$100_1 = distributionTempVariable$var11$99;
																							if((0 == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[(j + 1)]) / traceTempVariable$var50$100_1);
																											if(((Math.log(cv$probabilitySample11Value98) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value98) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value98) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value98) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value98) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value98);
																										}
																									}
																								}
																							}
																						}
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
															for(int index$sample27$85 = 0; index$sample27$85 < weightings.length; index$sample27$85 += 1) {
																int distributionTempVariable$var27$87 = index$sample27$85;
																double cv$probabilitySample27Value86 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$85]);
																{
																	int traceTempVariable$var42$88_1 = distributionTempVariable$var27$87;
																	if(((i + 1) == j)) {
																		{
																			int traceTempVariable$var46$90_1 = distributionTempVariable$var27$87;
																			if(((i + 1) == (j + 1))) {
																				if(fixedFlag$sample11) {
																					{
																						if((0 == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$88_1) + traceTempVariable$var46$90_1) / v2[(j + 1)]);
																										if(((Math.log(cv$probabilitySample27Value86) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value86) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value86) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value86) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value86) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value86);
																									}
																								}
																							}
																						}
																					}
																				} else {
																					if(true) {
																						for(int index$sample11$102 = 0; index$sample11$102 < weightings.length; index$sample11$102 += 1) {
																							int distributionTempVariable$var11$104 = index$sample11$102;
																							double cv$probabilitySample11Value103 = (cv$probabilitySample27Value86 * distribution$sample11[index$sample11$102]);
																							{
																								int traceTempVariable$var50$105_1 = distributionTempVariable$var11$104;
																								if((0 == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$88_1) + traceTempVariable$var46$90_1) / traceTempVariable$var50$105_1);
																												if(((Math.log(cv$probabilitySample11Value103) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value103) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value103) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value103) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value103) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value103);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$91 = 0; index$i$91 < size; index$i$91 += 1) {
																			if(!(index$i$91 == i)) {
																				for(int index$sample27$92 = 0; index$sample27$92 < weightings.length; index$sample27$92 += 1) {
																					int distributionTempVariable$var27$94 = index$sample27$92;
																					double cv$probabilitySample27Value93 = (cv$probabilitySample27Value86 * distribution$sample27[((index$i$91 - 0) / 1)][index$sample27$92]);
																					{
																						int traceTempVariable$var46$95_1 = distributionTempVariable$var27$94;
																						if(((index$i$91 + 1) == (j + 1))) {
																							if(fixedFlag$sample11) {
																								{
																									if((0 == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$88_1) + traceTempVariable$var46$95_1) / v2[(j + 1)]);
																													if(((Math.log(cv$probabilitySample27Value93) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value93) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value93) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value93) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value93) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value93);
																												}
																											}
																										}
																									}
																								}
																							} else {
																								if(true) {
																									for(int index$sample11$107 = 0; index$sample11$107 < weightings.length; index$sample11$107 += 1) {
																										int distributionTempVariable$var11$109 = index$sample11$107;
																										double cv$probabilitySample11Value108 = (cv$probabilitySample27Value93 * distribution$sample11[index$sample11$107]);
																										{
																											int traceTempVariable$var50$110_1 = distributionTempVariable$var11$109;
																											if((0 == (j + 1))) {
																												{
																													{
																														{
																															double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$88_1) + traceTempVariable$var46$95_1) / traceTempVariable$var50$110_1);
																															if(((Math.log(cv$probabilitySample11Value108) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value108) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value108) + Math.log((v[j]?var51:(1.0 - var51))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value108) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value108) + Math.log((v[j]?var51:(1.0 - var51)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value108);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
												if(fixedFlag$sample11) {
													{
														if((0 == j)) {
															{
																if((0 == (j + 1))) {
																	if(fixedFlag$sample27) {
																		{
																			for(int i = 0; i < size; i += 1) {
																				if(((i + 1) == (j + 1))) {
																					{
																						{
																							{
																								double var51 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																								if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
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
																				for(int index$sample27$124 = 0; index$sample27$124 < weightings.length; index$sample27$124 += 1) {
																					int distributionTempVariable$var27$126 = index$sample27$124;
																					double cv$probabilitySample27Value125 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$124]);
																					{
																						int traceTempVariable$var50$127_1 = distributionTempVariable$var27$126;
																						if(((i + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[(j + 1)]) / traceTempVariable$var50$127_1);
																										if(((Math.log(cv$probabilitySample27Value125) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value125) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value125) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value125) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value125) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value125);
																									}
																								}
																							}
																						}
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
														for(int index$sample11$112 = 0; index$sample11$112 < weightings.length; index$sample11$112 += 1) {
															int distributionTempVariable$var11$114 = index$sample11$112;
															double cv$probabilitySample11Value113 = (1.0 * distribution$sample11[index$sample11$112]);
															{
																int traceTempVariable$var42$115_1 = distributionTempVariable$var11$114;
																if((0 == j)) {
																	{
																		int traceTempVariable$var46$117_1 = distributionTempVariable$var11$114;
																		if((0 == (j + 1))) {
																			if(fixedFlag$sample27) {
																				{
																					for(int i = 0; i < size; i += 1) {
																						if(((i + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$115_1) + traceTempVariable$var46$117_1) / v2[(j + 1)]);
																										if(((Math.log(cv$probabilitySample11Value113) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value113) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value113) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value113) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value113) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value113);
																									}
																								}
																							}
																						}
																					}
																				}
																			} else {
																				for(int i = 0; i < size; i += 1) {
																					if(true) {
																						for(int index$sample27$130 = 0; index$sample27$130 < weightings.length; index$sample27$130 += 1) {
																							int distributionTempVariable$var27$132 = index$sample27$130;
																							double cv$probabilitySample27Value131 = (cv$probabilitySample11Value113 * distribution$sample27[((i - 0) / 1)][index$sample27$130]);
																							{
																								int traceTempVariable$var50$133_1 = distributionTempVariable$var27$132;
																								if(((i + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$115_1) + traceTempVariable$var46$117_1) / traceTempVariable$var50$133_1);
																												if(((Math.log(cv$probabilitySample27Value131) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value131) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value131) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value131) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value131) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value131);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample11$118 = 0; index$sample11$118 < weightings.length; index$sample11$118 += 1) {
																			int distributionTempVariable$var11$120 = index$sample11$118;
																			double cv$probabilitySample11Value119 = (cv$probabilitySample11Value113 * distribution$sample11[index$sample11$118]);
																			{
																				int traceTempVariable$var46$121_1 = distributionTempVariable$var11$120;
																				if((0 == (j + 1))) {
																					if(fixedFlag$sample27) {
																						{
																							for(int i = 0; i < size; i += 1) {
																								if(((i + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$115_1) + traceTempVariable$var46$121_1) / v2[(j + 1)]);
																												if(((Math.log(cv$probabilitySample11Value119) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value119) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value119) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value119) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value119) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value119);
																											}
																										}
																									}
																								}
																							}
																						}
																					} else {
																						for(int i = 0; i < size; i += 1) {
																							if(true) {
																								for(int index$sample27$136 = 0; index$sample27$136 < weightings.length; index$sample27$136 += 1) {
																									int distributionTempVariable$var27$138 = index$sample27$136;
																									double cv$probabilitySample27Value137 = (cv$probabilitySample11Value119 * distribution$sample27[((i - 0) / 1)][index$sample27$136]);
																									{
																										int traceTempVariable$var50$139_1 = distributionTempVariable$var27$138;
																										if(((i + 1) == (j + 1))) {
																											{
																												{
																													{
																														double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$115_1) + traceTempVariable$var46$121_1) / traceTempVariable$var50$139_1);
																														if(((Math.log(cv$probabilitySample27Value137) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value137) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value137) + Math.log((v[j]?var51:(1.0 - var51))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value137) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value137) + Math.log((v[j]?var51:(1.0 - var51)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value137);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
												if(fixedFlag$sample27) {
													{
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == j)) {
																if(fixedFlag$sample11) {
																	{
																		if((0 == (j + 1))) {
																			{
																				for(int index$i$156_1 = 0; index$i$156_1 < size; index$i$156_1 += 1) {
																					if(((index$i$156_1 + 1) == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																									if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
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
																	if(true) {
																		for(int index$sample11$147 = 0; index$sample11$147 < weightings.length; index$sample11$147 += 1) {
																			int distributionTempVariable$var11$149 = index$sample11$147;
																			double cv$probabilitySample11Value148 = (1.0 * distribution$sample11[index$sample11$147]);
																			{
																				int traceTempVariable$var46$150_1 = distributionTempVariable$var11$149;
																				if((0 == (j + 1))) {
																					{
																						for(int index$i$157_1 = 0; index$i$157_1 < size; index$i$157_1 += 1) {
																							if(((index$i$157_1 + 1) == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var46$150_1) / v2[(j + 1)]);
																											if(((Math.log(cv$probabilitySample11Value148) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value148) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value148) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value148) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value148) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value148);
																										}
																									}
																								}
																							}
																						}
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
															for(int index$sample27$142 = 0; index$sample27$142 < weightings.length; index$sample27$142 += 1) {
																int distributionTempVariable$var27$144 = index$sample27$142;
																double cv$probabilitySample27Value143 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$142]);
																{
																	int traceTempVariable$var42$145_1 = distributionTempVariable$var27$144;
																	if(((i + 1) == j)) {
																		if(fixedFlag$sample11) {
																			{
																				if((0 == (j + 1))) {
																					{
																						int traceTempVariable$var50$158_1 = distributionTempVariable$var27$144;
																						if(((i + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$145_1) + v2[(j + 1)]) / traceTempVariable$var50$158_1);
																										if(((Math.log(cv$probabilitySample27Value143) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value143) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value143) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value143) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value143) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value143);
																									}
																								}
																							}
																						}
																					}
																					for(int index$i$159 = 0; index$i$159 < size; index$i$159 += 1) {
																						if(!(index$i$159 == i)) {
																							for(int index$sample27$160 = 0; index$sample27$160 < weightings.length; index$sample27$160 += 1) {
																								int distributionTempVariable$var27$162 = index$sample27$160;
																								double cv$probabilitySample27Value161 = (cv$probabilitySample27Value143 * distribution$sample27[((index$i$159 - 0) / 1)][index$sample27$160]);
																								{
																									int traceTempVariable$var50$163_1 = distributionTempVariable$var27$162;
																									if(((index$i$159 + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$145_1) + v2[(j + 1)]) / traceTempVariable$var50$163_1);
																													if(((Math.log(cv$probabilitySample27Value161) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value161) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value161) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value161) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value161) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value161);
																												}
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
																				for(int index$sample11$152 = 0; index$sample11$152 < weightings.length; index$sample11$152 += 1) {
																					int distributionTempVariable$var11$154 = index$sample11$152;
																					double cv$probabilitySample11Value153 = (cv$probabilitySample27Value143 * distribution$sample11[index$sample11$152]);
																					{
																						int traceTempVariable$var46$155_1 = distributionTempVariable$var11$154;
																						if((0 == (j + 1))) {
																							{
																								int traceTempVariable$var50$164_1 = distributionTempVariable$var27$144;
																								if(((i + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$145_1) + traceTempVariable$var46$155_1) / traceTempVariable$var50$164_1);
																												if(((Math.log(cv$probabilitySample11Value153) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value153) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value153) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value153) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value153) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value153);
																											}
																										}
																									}
																								}
																							}
																							for(int index$i$165 = 0; index$i$165 < size; index$i$165 += 1) {
																								if(!(index$i$165 == i)) {
																									for(int index$sample27$166 = 0; index$sample27$166 < weightings.length; index$sample27$166 += 1) {
																										int distributionTempVariable$var27$168 = index$sample27$166;
																										double cv$probabilitySample27Value167 = (cv$probabilitySample11Value153 * distribution$sample27[((index$i$165 - 0) / 1)][index$sample27$166]);
																										{
																											int traceTempVariable$var50$169_1 = distributionTempVariable$var27$168;
																											if(((index$i$165 + 1) == (j + 1))) {
																												{
																													{
																														{
																															double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$145_1) + traceTempVariable$var46$155_1) / traceTempVariable$var50$169_1);
																															if(((Math.log(cv$probabilitySample27Value167) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value167) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value167) + Math.log((v[j]?var51:(1.0 - var51))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value167) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value167) + Math.log((v[j]?var51:(1.0 - var51)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value167);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
												if(fixedFlag$sample11) {
													{
														if((0 == j)) {
															if(fixedFlag$sample27) {
																{
																	for(int i = 0; i < size; i += 1) {
																		if(((i + 1) == (j + 1))) {
																			{
																				for(int index$i$187_1 = 0; index$i$187_1 < size; index$i$187_1 += 1) {
																					if(((index$i$187_1 + 1) == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																									if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
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
																}
															} else {
																for(int i = 0; i < size; i += 1) {
																	if(true) {
																		for(int index$sample27$177 = 0; index$sample27$177 < weightings.length; index$sample27$177 += 1) {
																			int distributionTempVariable$var27$179 = index$sample27$177;
																			double cv$probabilitySample27Value178 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$177]);
																			{
																				int traceTempVariable$var46$180_1 = distributionTempVariable$var27$179;
																				if(((i + 1) == (j + 1))) {
																					{
																						int traceTempVariable$var50$188_1 = distributionTempVariable$var27$179;
																						if(((i + 1) == (j + 1))) {
																							{
																								{
																									{
																										double var51 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var46$180_1) / traceTempVariable$var50$188_1);
																										if(((Math.log(cv$probabilitySample27Value178) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value178) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value178) + Math.log((v[j]?var51:(1.0 - var51))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value178) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value178) + Math.log((v[j]?var51:(1.0 - var51)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value178);
																									}
																								}
																							}
																						}
																					}
																					for(int index$i$189 = 0; index$i$189 < size; index$i$189 += 1) {
																						if(!(index$i$189 == i)) {
																							for(int index$sample27$190 = 0; index$sample27$190 < weightings.length; index$sample27$190 += 1) {
																								int distributionTempVariable$var27$192 = index$sample27$190;
																								double cv$probabilitySample27Value191 = (cv$probabilitySample27Value178 * distribution$sample27[((index$i$189 - 0) / 1)][index$sample27$190]);
																								{
																									int traceTempVariable$var50$193_1 = distributionTempVariable$var27$192;
																									if(((index$i$189 + 1) == (j + 1))) {
																										{
																											{
																												{
																													double var51 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var46$180_1) / traceTempVariable$var50$193_1);
																													if(((Math.log(cv$probabilitySample27Value191) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value191) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value191) + Math.log((v[j]?var51:(1.0 - var51))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value191) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value191) + Math.log((v[j]?var51:(1.0 - var51)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value191);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
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
														for(int index$sample11$171 = 0; index$sample11$171 < weightings.length; index$sample11$171 += 1) {
															int distributionTempVariable$var11$173 = index$sample11$171;
															double cv$probabilitySample11Value172 = (1.0 * distribution$sample11[index$sample11$171]);
															{
																int traceTempVariable$var42$174_1 = distributionTempVariable$var11$173;
																if((0 == j)) {
																	if(fixedFlag$sample27) {
																		{
																			for(int i = 0; i < size; i += 1) {
																				if(((i + 1) == (j + 1))) {
																					{
																						for(int index$i$194_1 = 0; index$i$194_1 < size; index$i$194_1 += 1) {
																							if(((index$i$194_1 + 1) == (j + 1))) {
																								{
																									{
																										{
																											double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$174_1) + v2[(j + 1)]) / v2[(j + 1)]);
																											if(((Math.log(cv$probabilitySample11Value172) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value172) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value172) + Math.log((v[j]?var51:(1.0 - var51))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value172) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample11Value172) + Math.log((v[j]?var51:(1.0 - var51)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value172);
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
																				for(int index$sample27$183 = 0; index$sample27$183 < weightings.length; index$sample27$183 += 1) {
																					int distributionTempVariable$var27$185 = index$sample27$183;
																					double cv$probabilitySample27Value184 = (cv$probabilitySample11Value172 * distribution$sample27[((i - 0) / 1)][index$sample27$183]);
																					{
																						int traceTempVariable$var46$186_1 = distributionTempVariable$var27$185;
																						if(((i + 1) == (j + 1))) {
																							{
																								int traceTempVariable$var50$195_1 = distributionTempVariable$var27$185;
																								if(((i + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$174_1) + traceTempVariable$var46$186_1) / traceTempVariable$var50$195_1);
																												if(((Math.log(cv$probabilitySample27Value184) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value184) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value184) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value184) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value184) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value184);
																											}
																										}
																									}
																								}
																							}
																							for(int index$i$196 = 0; index$i$196 < size; index$i$196 += 1) {
																								if(!(index$i$196 == i)) {
																									for(int index$sample27$197 = 0; index$sample27$197 < weightings.length; index$sample27$197 += 1) {
																										int distributionTempVariable$var27$199 = index$sample27$197;
																										double cv$probabilitySample27Value198 = (cv$probabilitySample27Value184 * distribution$sample27[((index$i$196 - 0) / 1)][index$sample27$197]);
																										{
																											int traceTempVariable$var50$200_1 = distributionTempVariable$var27$199;
																											if(((index$i$196 + 1) == (j + 1))) {
																												{
																													{
																														{
																															double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$174_1) + traceTempVariable$var46$186_1) / traceTempVariable$var50$200_1);
																															if(((Math.log(cv$probabilitySample27Value198) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value198) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value198) + Math.log((v[j]?var51:(1.0 - var51))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value198) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value198) + Math.log((v[j]?var51:(1.0 - var51)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value198);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
												if(fixedFlag$sample27) {
													{
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == j)) {
																{
																	for(int index$i$207_1 = 0; index$i$207_1 < size; index$i$207_1 += 1) {
																		if(((index$i$207_1 + 1) == (j + 1))) {
																			{
																				for(int index$i$214_1 = 0; index$i$214_1 < size; index$i$214_1 += 1) {
																					if(((index$i$214_1 + 1) == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
																									if(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?var51:(1.0 - var51)))));
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
																}
															}
														}
													}
												} else {
													for(int i = 0; i < size; i += 1) {
														if(true) {
															for(int index$sample27$203 = 0; index$sample27$203 < weightings.length; index$sample27$203 += 1) {
																int distributionTempVariable$var27$205 = index$sample27$203;
																double cv$probabilitySample27Value204 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$203]);
																{
																	int traceTempVariable$var42$206_1 = distributionTempVariable$var27$205;
																	if(((i + 1) == j)) {
																		{
																			int traceTempVariable$var46$208_1 = distributionTempVariable$var27$205;
																			if(((i + 1) == (j + 1))) {
																				{
																					int traceTempVariable$var50$215_1 = distributionTempVariable$var27$205;
																					if(((i + 1) == (j + 1))) {
																						{
																							{
																								{
																									double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$206_1) + traceTempVariable$var46$208_1) / traceTempVariable$var50$215_1);
																									if(((Math.log(cv$probabilitySample27Value204) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value204) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value204) + Math.log((v[j]?var51:(1.0 - var51))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value204) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value204) + Math.log((v[j]?var51:(1.0 - var51)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value204);
																								}
																							}
																						}
																					}
																				}
																				for(int index$i$216 = 0; index$i$216 < size; index$i$216 += 1) {
																					if(!(index$i$216 == i)) {
																						for(int index$sample27$217 = 0; index$sample27$217 < weightings.length; index$sample27$217 += 1) {
																							int distributionTempVariable$var27$219 = index$sample27$217;
																							double cv$probabilitySample27Value218 = (cv$probabilitySample27Value204 * distribution$sample27[((index$i$216 - 0) / 1)][index$sample27$217]);
																							{
																								int traceTempVariable$var50$220_1 = distributionTempVariable$var27$219;
																								if(((index$i$216 + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$206_1) + traceTempVariable$var46$208_1) / traceTempVariable$var50$220_1);
																												if(((Math.log(cv$probabilitySample27Value218) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value218) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value218) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value218) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value218) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value218);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$209 = 0; index$i$209 < size; index$i$209 += 1) {
																			if(!(index$i$209 == i)) {
																				for(int index$sample27$210 = 0; index$sample27$210 < weightings.length; index$sample27$210 += 1) {
																					int distributionTempVariable$var27$212 = index$sample27$210;
																					double cv$probabilitySample27Value211 = (cv$probabilitySample27Value204 * distribution$sample27[((index$i$209 - 0) / 1)][index$sample27$210]);
																					{
																						int traceTempVariable$var46$213_1 = distributionTempVariable$var27$212;
																						if(((index$i$209 + 1) == (j + 1))) {
																							{
																								int traceTempVariable$var50$221_1 = distributionTempVariable$var27$205;
																								if(((i + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$206_1) + traceTempVariable$var46$213_1) / traceTempVariable$var50$221_1);
																												if(((Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value211);
																											}
																										}
																									}
																								}
																							}
																							{
																								int traceTempVariable$var50$222_1 = distributionTempVariable$var27$212;
																								if(((index$i$209 + 1) == (j + 1))) {
																									{
																										{
																											{
																												double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$206_1) + traceTempVariable$var46$213_1) / traceTempVariable$var50$222_1);
																												if(((Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?var51:(1.0 - var51))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?var51:(1.0 - var51)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value211);
																											}
																										}
																									}
																								}
																							}
																							for(int index$i$223 = 0; index$i$223 < size; index$i$223 += 1) {
																								if((!(index$i$223 == i) && !(index$i$223 == index$i$209))) {
																									for(int index$sample27$224 = 0; index$sample27$224 < weightings.length; index$sample27$224 += 1) {
																										int distributionTempVariable$var27$226 = index$sample27$224;
																										double cv$probabilitySample27Value225 = (cv$probabilitySample27Value211 * distribution$sample27[((index$i$223 - 0) / 1)][index$sample27$224]);
																										{
																											int traceTempVariable$var50$227_1 = distributionTempVariable$var27$226;
																											if(((index$i$223 + 1) == (j + 1))) {
																												{
																													{
																														{
																															double var51 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var42$206_1) + traceTempVariable$var46$213_1) / traceTempVariable$var50$227_1);
																															if(((Math.log(cv$probabilitySample27Value225) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value225) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value225) + Math.log((v[j]?var51:(1.0 - var51))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value225) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + (Math.log(cv$probabilitySample27Value225) + Math.log((v[j]?var51:(1.0 - var51)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value225);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
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
			double[] cv$localProbability = distribution$sample5;
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
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	@Override
	public final void allocateScratch() {
		{
			cv$var5$stateProbabilityGlobal = new double[weightings.length];
		}
		{
			cv$var11$stateProbabilityGlobal = new double[weightings.length];
		}
		{
			int cv$max_j = 0;
			cv$max_j = Math.max(cv$max_j, ((length$value - 0) / 1));
			guard$sample11bernoulli52$global = new boolean[cv$max_j];
		}
		{
			cv$var27$stateProbabilityGlobal = new double[weightings.length];
		}
		{
			int cv$max_j = 0;
			cv$max_j = Math.max(cv$max_j, ((length$value - 0) / 1));
			guard$sample27bernoulli52$global = new boolean[cv$max_j];
		}
	}

	@Override
	public final void allocator() {
		if((!fixedFlag$sample11 || !fixedFlag$sample27)) {
			{
				v2 = new int[(length$value + 1)];
			}
		}
		{
			v = new boolean[length$value];
		}
		{
			distribution$sample5 = new double[weightings.length];
		}
		{
			distribution$sample11 = new double[weightings.length];
		}
		{
			distribution$sample27 = new double[((((length$value - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < length$value; i += 1)
				distribution$sample27[((i - 0) / 1)] = new double[weightings.length];
		}
		{
			logProbability$sample27 = new double[((((length$value - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample53 = new double[((((length$value - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample11)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		parallelFor(RNG$, 0, size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample27)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings, weightings.length);
					}
			}
		);
		parallelFor(RNG$, 0, size, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						v[j] = DistributionSampling.sampleBernoulli(RNG$1, ((((1.0 * v1) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]));
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		double[] cv$distribution$sample5 = distribution$sample5;
		for(int index$var4 = 0; index$var4 < weightings.length; index$var4 += 1) {
			double cv$value = (((0.0 <= index$var4) && (index$var4 < weightings.length))?weightings[index$var4]:0.0);
			if(!fixedFlag$sample5)
				cv$distribution$sample5[index$var4] = cv$value;
		}
		double[] cv$distribution$sample11 = distribution$sample11;
		for(int index$var10 = 0; index$var10 < weightings.length; index$var10 += 1) {
			double cv$value = (((0.0 <= index$var10) && (index$var10 < weightings.length))?weightings[index$var10]:0.0);
			if(!fixedFlag$sample11)
				cv$distribution$sample11[index$var10] = cv$value;
		}
		parallelFor(RNG$, 0, size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						double[] cv$distribution$sample27 = distribution$sample27[((i - 0) / 1)];
						for(int index$var26 = 0; index$var26 < weightings.length; index$var26 += 1) {
							double cv$value = (((0.0 <= index$var26) && (index$var26 < weightings.length))?weightings[index$var26]:0.0);
							if(!fixedFlag$sample27)
								cv$distribution$sample27[index$var26] = cv$value;
						}
					}
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample11)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		parallelFor(RNG$, 0, size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample27)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings, weightings.length);
					}
			}
		);
		parallelFor(RNG$, 0, size, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						v[j] = DistributionSampling.sampleBernoulli(RNG$1, ((((1.0 * v1) + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]));
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample11)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		parallelFor(RNG$, 0, size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample27)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings, weightings.length);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample11)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		parallelFor(RNG$, 0, size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample27)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings, weightings.length);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample5)
				sample5();
			if(!fixedFlag$sample11)
				sample11();
			for(int i = 0; i < size; i += 1) {
				if(!fixedFlag$sample27)
					sample27(i);
			}
		} else {
			for(int i = (size - ((((size - 1) - 0) % 1) + 1)); i >= ((0 - 1) + 1); i -= 1) {
				if(!fixedFlag$sample27)
					sample27(i);
			}
			if(!fixedFlag$sample11)
				sample11();
			if(!fixedFlag$sample5)
				sample5();
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
		if(!fixedProbFlag$sample5)
			logProbability$v1 = Double.NaN;
		logProbability$v2 = 0.0;
		if(!fixedProbFlag$sample11)
			logProbability$var11 = Double.NaN;
		if(!fixedProbFlag$sample27) {
			for(int i = 0; i < size; i += 1)
				logProbability$sample27[((i - 0) / 1)] = Double.NaN;
		}
		logProbability$v = 0.0;
		if(!fixedProbFlag$sample53) {
			for(int j = 0; j < size; j += 1)
				logProbability$sample53[((j - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample53();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample5();
		logProbabilityDistribution$sample11();
		logProbabilityDistribution$sample27();
		logProbabilityDistribution$sample53();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample11();
		logProbabilityValue$sample27();
		logProbabilityValue$sample53();
	}

	@Override
	public final void propagateObservedValues() {
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
		     + "model DistributionTest4(double[] weightings, boolean[] value) {\n"
		     + "    int size = value.length;\n"
		     + "    \n"
		     + "    int v1 = categorical(weightings).sampleDistribution();\n"
		     + "    \n"
		     + "    int[] v2 = new int[size + 1];\n"
		     + "    v2[0] = categorical(weightings).sampleDistribution();\n"
		     + "    for(int i:[0..size))\n"
		     + "        v2[i + 1] = categorical(weightings).sampleDistribution();\n"
		     + "        \n"
		     + "    boolean[] v = new boolean[size];\n"
		     + "    for(int j:[0..size))\n"
		     + "        v[j] = bernoulli(((1.0*v1) + v2[j] + v2[j+1])/v2[j+1]).sample();\n"
		     + "        \n"
		     + "    v.observe(value);\n"
		     + "}\n"
		     + "";
	}
}