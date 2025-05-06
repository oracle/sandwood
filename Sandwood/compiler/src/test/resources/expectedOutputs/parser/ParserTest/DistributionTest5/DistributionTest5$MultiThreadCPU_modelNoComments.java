package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DistributionTest5$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DistributionTest5$CoreInterface {
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
	private boolean fixedProbFlag$sample70 = false;
	private boolean[] guard$sample11bernoulli69$global;
	private boolean[] guard$sample27bernoulli69$global;
	private int length$value;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$sample27;
	private double[] logProbability$sample70;
	private double logProbability$v;
	private double logProbability$v1;
	private double logProbability$v2;
	private double logProbability$v3;
	private double logProbability$var10;
	private double logProbability$var11;
	private double[] logProbability$var26;
	private double logProbability$var4;
	private double[] logProbability$var69;
	private int size;
	private boolean system$gibbsForward = true;
	private boolean[] v;
	private int v1;
	private int[] v2;
	private int[] v3;
	private boolean[] value;
	private double[] weightings;

	public DistributionTest5$MultiThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample70 = (fixedFlag$sample11 && fixedProbFlag$sample70);
	}

	@Override
	public final boolean get$fixedFlag$sample27() {
		return fixedFlag$sample27;
	}

	@Override
	public final void set$fixedFlag$sample27(boolean cv$value) {
		fixedFlag$sample27 = cv$value;
		fixedProbFlag$sample27 = (fixedFlag$sample27 && fixedProbFlag$sample27);
		fixedProbFlag$sample70 = (fixedFlag$sample27 && fixedProbFlag$sample70);
	}

	@Override
	public final boolean get$fixedFlag$sample5() {
		return fixedFlag$sample5;
	}

	@Override
	public final void set$fixedFlag$sample5(boolean cv$value) {
		fixedFlag$sample5 = cv$value;
		fixedProbFlag$sample5 = (fixedFlag$sample5 && fixedProbFlag$sample5);
		fixedProbFlag$sample70 = (fixedFlag$sample5 && fixedProbFlag$sample70);
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
	public final double get$logProbability$v3() {
		return logProbability$v3;
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
		fixedProbFlag$sample70 = false;
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
		fixedProbFlag$sample70 = false;
	}

	@Override
	public final int[] get$v3() {
		return v3;
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
				logProbability$var10 = cv$sampleAccumulator;
				logProbability$var11 = cv$sampleProbability;
				boolean cv$guard$v3 = false;
				if(fixedFlag$sample11)
					logProbability$v2 = (logProbability$v2 + cv$accumulator);
				{
					for(int k = 0; k < (size + 1); k += 1) {
						if((0 == k)) {
							if((fixedFlag$sample11 && fixedFlag$sample27)) {
								if(!cv$guard$v3) {
									cv$guard$v3 = true;
									logProbability$v3 = (logProbability$v3 + cv$accumulator);
								}
							}
						}
					}
				}
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
			logProbability$var10 = cv$rvAccumulator;
			boolean cv$guard$v3 = false;
			if(fixedFlag$sample11)
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
			{
				for(int k = 0; k < (size + 1); k += 1) {
					if((0 == k)) {
						if((fixedFlag$sample11 && fixedFlag$sample27)) {
							if(!cv$guard$v3) {
								cv$guard$v3 = true;
								logProbability$v3 = (logProbability$v3 + cv$accumulator);
							}
						}
					}
				}
			}
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
					cv$sampleReached = true;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$var26[((i - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample27[((i - 0) / 1)] = cv$sampleProbability;
					boolean cv$guard$v3 = false;
					int index$i$3 = i;
					{
						for(int k = 0; k < (size + 1); k += 1) {
							if(((i + 1) == k)) {
								if((fixedFlag$sample11 && fixedFlag$sample27)) {
									if(!cv$guard$v3) {
										cv$guard$v3 = true;
										logProbability$v3 = (logProbability$v3 + cv$sampleProbability);
									}
								}
							}
						}
					}
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
				logProbability$var26[((i - 0) / 1)] = cv$rvAccumulator;
				boolean cv$guard$v3 = false;
				int index$i$5 = i;
				{
					for(int k = 0; k < (size + 1); k += 1) {
						if(((i + 1) == k)) {
							if((fixedFlag$sample11 && fixedFlag$sample27)) {
								if(!cv$guard$v3) {
									cv$guard$v3 = true;
									logProbability$v3 = (logProbability$v3 + cv$sampleValue);
								}
							}
						}
					}
				}
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
				logProbability$var4 = cv$sampleAccumulator;
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
			logProbability$var4 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample70() {
		if(!fixedProbFlag$sample70) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < size; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = v[j];
					if(fixedFlag$sample5) {
						if(fixedFlag$sample11) {
							if((0 == j)) {
								for(int k = 0; k < (size + 1); k += 1) {
									if((0 == k)) {
										int traceTempVariable$var63$17_2 = v2[k];
										if((k == (j + 1))) {
											if((0 == (j + 1))) {
												{
													double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$17_2) / v2[(j + 1)]);
													double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
								for(int index$sample11$8 = 0; index$sample11$8 < weightings.length; index$sample11$8 += 1) {
									int distributionTempVariable$var11$10 = index$sample11$8;
									double cv$probabilitySample11Value9 = (1.0 * distribution$sample11[index$sample11$8]);
									if((0 == j)) {
										for(int k = 0; k < (size + 1); k += 1) {
											if((0 == k)) {
												int traceTempVariable$var63$18_2 = v2[k];
												if((k == (j + 1))) {
													if((0 == (j + 1))) {
														{
															double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$18_2) / v2[(j + 1)]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample11Value9) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
													if(!true) {
														for(int index$sample11$31 = 0; index$sample11$31 < weightings.length; index$sample11$31 += 1) {
															int distributionTempVariable$var11$33 = index$sample11$31;
															double cv$probabilitySample11Value32 = (cv$probabilitySample11Value9 * distribution$sample11[index$sample11$31]);
															if((0 == (j + 1))) {
																{
																	double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$18_2) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample11Value32) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														int traceTempVariable$var63$22_2 = v2[k];
														if((k == (j + 1))) {
															if((0 == (j + 1))) {
																{
																	double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$22_2) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample11Value20) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
															if((0 == (j + 1))) {
																{
																	double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$22_2) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample11Value20) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
															if(!true) {
																for(int index$sample11$37 = 0; index$sample11$37 < weightings.length; index$sample11$37 += 1) {
																	int distributionTempVariable$var11$39 = index$sample11$37;
																	double cv$probabilitySample11Value38 = (cv$probabilitySample11Value20 * distribution$sample11[index$sample11$37]);
																	if((0 == (j + 1))) {
																		{
																			double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$22_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value38) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
					} else {
						if(true) {
							for(int index$sample5$3 = 0; index$sample5$3 < weightings.length; index$sample5$3 += 1) {
								int distributionTempVariable$v1$5 = index$sample5$3;
								double cv$probabilitySample5Value4 = (1.0 * distribution$sample5[index$sample5$3]);
								if(fixedFlag$sample11) {
									if((0 == j)) {
										for(int k = 0; k < (size + 1); k += 1) {
											if((0 == k)) {
												int traceTempVariable$var63$23_2 = v2[k];
												if((k == (j + 1))) {
													if((0 == (j + 1))) {
														{
															double var68 = ((((1.0 * distributionTempVariable$v1$5) + v2[j]) + traceTempVariable$var63$23_2) / v2[(j + 1)]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample5Value4) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
								} else {
									if(true) {
										for(int index$sample11$13 = 0; index$sample11$13 < weightings.length; index$sample11$13 += 1) {
											int distributionTempVariable$var11$15 = index$sample11$13;
											double cv$probabilitySample11Value14 = (cv$probabilitySample5Value4 * distribution$sample11[index$sample11$13]);
											if((0 == j)) {
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														int traceTempVariable$var63$24_2 = v2[k];
														if((k == (j + 1))) {
															if((0 == (j + 1))) {
																{
																	double var68 = ((((1.0 * distributionTempVariable$v1$5) + v2[j]) + traceTempVariable$var63$24_2) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample11Value14) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
															if(!true) {
																for(int index$sample11$43 = 0; index$sample11$43 < weightings.length; index$sample11$43 += 1) {
																	int distributionTempVariable$var11$45 = index$sample11$43;
																	double cv$probabilitySample11Value44 = (cv$probabilitySample11Value14 * distribution$sample11[index$sample11$43]);
																	if((0 == (j + 1))) {
																		{
																			double var68 = ((((1.0 * distributionTempVariable$v1$5) + v2[j]) + traceTempVariable$var63$24_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value44) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var63$28_2 = v2[k];
																if((k == (j + 1))) {
																	if((0 == (j + 1))) {
																		{
																			double var68 = ((((1.0 * distributionTempVariable$v1$5) + v2[j]) + traceTempVariable$var63$28_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value26) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
																	if((0 == (j + 1))) {
																		{
																			double var68 = ((((1.0 * distributionTempVariable$v1$5) + v2[j]) + traceTempVariable$var63$28_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value26) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
																	if(!true) {
																		for(int index$sample11$49 = 0; index$sample11$49 < weightings.length; index$sample11$49 += 1) {
																			int distributionTempVariable$var11$51 = index$sample11$49;
																			double cv$probabilitySample11Value50 = (cv$probabilitySample11Value26 * distribution$sample11[index$sample11$49]);
																			if((0 == (j + 1))) {
																				{
																					double var68 = ((((1.0 * distributionTempVariable$v1$5) + v2[j]) + traceTempVariable$var63$28_2) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample11Value50) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
					if(fixedFlag$sample5) {
						if(fixedFlag$sample11) {
							if((0 == j)) {
								if(fixedFlag$sample27) {
									for(int i = 0; i < size; i += 1) {
										for(int k = 0; k < (size + 1); k += 1) {
											if(((i + 1) == k)) {
												int traceTempVariable$var63$68_3 = v2[k];
												if((k == (j + 1))) {
													if((0 == (j + 1))) {
														{
															double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$68_3) / v2[(j + 1)]);
															double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
											for(int index$sample27$70 = 0; index$sample27$70 < weightings.length; index$sample27$70 += 1) {
												int distributionTempVariable$var27$72 = index$sample27$70;
												double cv$probabilitySample27Value71 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$70]);
												for(int k = 0; k < (size + 1); k += 1) {
													if(((i + 1) == k)) {
														int traceTempVariable$var63$73_2 = v2[k];
														if((k == (j + 1))) {
															if((0 == (j + 1))) {
																{
																	double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$73_2) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample27Value71) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value71);
																}
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
								for(int index$sample11$59 = 0; index$sample11$59 < weightings.length; index$sample11$59 += 1) {
									int distributionTempVariable$var11$61 = index$sample11$59;
									double cv$probabilitySample11Value60 = (1.0 * distribution$sample11[index$sample11$59]);
									if((0 == j)) {
										if(fixedFlag$sample27) {
											for(int i = 0; i < size; i += 1) {
												for(int k = 0; k < (size + 1); k += 1) {
													if(((i + 1) == k)) {
														int traceTempVariable$var63$74_3 = v2[k];
														if((k == (j + 1))) {
															if((0 == (j + 1))) {
																{
																	double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$74_3) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample11Value60) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value60);
																}
															}
															if(!true) {
																for(int index$sample11$95 = 0; index$sample11$95 < weightings.length; index$sample11$95 += 1) {
																	int distributionTempVariable$var11$97 = index$sample11$95;
																	double cv$probabilitySample11Value96 = (cv$probabilitySample11Value60 * distribution$sample11[index$sample11$95]);
																	if((0 == (j + 1))) {
																		{
																			double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$74_3) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value96) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value96);
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
													for(int index$sample27$76 = 0; index$sample27$76 < weightings.length; index$sample27$76 += 1) {
														int distributionTempVariable$var27$78 = index$sample27$76;
														double cv$probabilitySample27Value77 = (cv$probabilitySample11Value60 * distribution$sample27[((i - 0) / 1)][index$sample27$76]);
														for(int k = 0; k < (size + 1); k += 1) {
															if(((i + 1) == k)) {
																int traceTempVariable$var63$79_2 = v2[k];
																if((k == (j + 1))) {
																	if((0 == (j + 1))) {
																		{
																			double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$79_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample27Value77) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value77);
																		}
																	}
																	if(!true) {
																		for(int index$sample11$100 = 0; index$sample11$100 < weightings.length; index$sample11$100 += 1) {
																			int distributionTempVariable$var11$102 = index$sample11$100;
																			double cv$probabilitySample11Value101 = (cv$probabilitySample27Value77 * distribution$sample11[index$sample11$100]);
																			if((0 == (j + 1))) {
																				{
																					double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$79_2) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample11Value101) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value101);
																				}
																			}
																		}
																	}
																}
															}
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
								if(fixedFlag$sample11) {
									if((0 == j)) {
										if(fixedFlag$sample27) {
											for(int i = 0; i < size; i += 1) {
												for(int k = 0; k < (size + 1); k += 1) {
													if(((i + 1) == k)) {
														int traceTempVariable$var63$80_3 = v2[k];
														if((k == (j + 1))) {
															if((0 == (j + 1))) {
																{
																	double var68 = ((((1.0 * distributionTempVariable$v1$56) + v2[j]) + traceTempVariable$var63$80_3) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample5Value55) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
											}
										} else {
											for(int i = 0; i < size; i += 1) {
												if(true) {
													for(int index$sample27$82 = 0; index$sample27$82 < weightings.length; index$sample27$82 += 1) {
														int distributionTempVariable$var27$84 = index$sample27$82;
														double cv$probabilitySample27Value83 = (cv$probabilitySample5Value55 * distribution$sample27[((i - 0) / 1)][index$sample27$82]);
														for(int k = 0; k < (size + 1); k += 1) {
															if(((i + 1) == k)) {
																int traceTempVariable$var63$85_2 = v2[k];
																if((k == (j + 1))) {
																	if((0 == (j + 1))) {
																		{
																			double var68 = ((((1.0 * distributionTempVariable$v1$56) + v2[j]) + traceTempVariable$var63$85_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample27Value83) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value83);
																		}
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
										for(int index$sample11$64 = 0; index$sample11$64 < weightings.length; index$sample11$64 += 1) {
											int distributionTempVariable$var11$66 = index$sample11$64;
											double cv$probabilitySample11Value65 = (cv$probabilitySample5Value55 * distribution$sample11[index$sample11$64]);
											if((0 == j)) {
												if(fixedFlag$sample27) {
													for(int i = 0; i < size; i += 1) {
														for(int k = 0; k < (size + 1); k += 1) {
															if(((i + 1) == k)) {
																int traceTempVariable$var63$86_3 = v2[k];
																if((k == (j + 1))) {
																	if((0 == (j + 1))) {
																		{
																			double var68 = ((((1.0 * distributionTempVariable$v1$56) + v2[j]) + traceTempVariable$var63$86_3) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value65) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value65);
																		}
																	}
																	if(!true) {
																		for(int index$sample11$107 = 0; index$sample11$107 < weightings.length; index$sample11$107 += 1) {
																			int distributionTempVariable$var11$109 = index$sample11$107;
																			double cv$probabilitySample11Value108 = (cv$probabilitySample11Value65 * distribution$sample11[index$sample11$107]);
																			if((0 == (j + 1))) {
																				{
																					double var68 = ((((1.0 * distributionTempVariable$v1$56) + v2[j]) + traceTempVariable$var63$86_3) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample11Value108) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value108);
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
															for(int index$sample27$88 = 0; index$sample27$88 < weightings.length; index$sample27$88 += 1) {
																int distributionTempVariable$var27$90 = index$sample27$88;
																double cv$probabilitySample27Value89 = (cv$probabilitySample11Value65 * distribution$sample27[((i - 0) / 1)][index$sample27$88]);
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((i + 1) == k)) {
																		int traceTempVariable$var63$91_2 = v2[k];
																		if((k == (j + 1))) {
																			if((0 == (j + 1))) {
																				{
																					double var68 = ((((1.0 * distributionTempVariable$v1$56) + v2[j]) + traceTempVariable$var63$91_2) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample27Value89) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value89);
																				}
																			}
																			if(!true) {
																				for(int index$sample11$112 = 0; index$sample11$112 < weightings.length; index$sample11$112 += 1) {
																					int distributionTempVariable$var11$114 = index$sample11$112;
																					double cv$probabilitySample11Value113 = (cv$probabilitySample27Value89 * distribution$sample11[index$sample11$112]);
																					if((0 == (j + 1))) {
																						{
																							double var68 = ((((1.0 * distributionTempVariable$v1$56) + v2[j]) + traceTempVariable$var63$91_2) / v2[(j + 1)]);
																							double cv$weightedProbability = (Math.log(cv$probabilitySample11Value113) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																							if((cv$weightedProbability < cv$distributionAccumulator))
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																							else {
																								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																									cv$distributionAccumulator = cv$weightedProbability;
																								else
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																							}
																							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value113);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
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
						if(fixedFlag$sample27) {
							for(int i = 0; i < size; i += 1) {
								if(((i + 1) == j)) {
									if(fixedFlag$sample11) {
										for(int k = 0; k < (size + 1); k += 1) {
											if((0 == k)) {
												int traceTempVariable$var63$133_2 = v2[k];
												if((k == (j + 1))) {
													if((0 == (j + 1))) {
														{
															double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$133_2) / v2[(j + 1)]);
															double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
											for(int index$sample11$134 = 0; index$sample11$134 < weightings.length; index$sample11$134 += 1) {
												int distributionTempVariable$var11$136 = index$sample11$134;
												double cv$probabilitySample11Value135 = (1.0 * distribution$sample11[index$sample11$134]);
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														int traceTempVariable$var63$137_2 = v2[k];
														if((k == (j + 1))) {
															if((0 == (j + 1))) {
																{
																	double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$137_2) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample11Value135) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value135);
																}
															}
															if(!true) {
																for(int index$sample11$155 = 0; index$sample11$155 < weightings.length; index$sample11$155 += 1) {
																	int distributionTempVariable$var11$157 = index$sample11$155;
																	double cv$probabilitySample11Value156 = (cv$probabilitySample11Value135 * distribution$sample11[index$sample11$155]);
																	if((0 == (j + 1))) {
																		{
																			double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$137_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value156) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value156);
																		}
																	}
																}
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
									for(int index$sample27$123 = 0; index$sample27$123 < weightings.length; index$sample27$123 += 1) {
										int distributionTempVariable$var27$125 = index$sample27$123;
										double cv$probabilitySample27Value124 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$123]);
										if(((i + 1) == j)) {
											if(fixedFlag$sample11) {
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														int traceTempVariable$var63$138_2 = v2[k];
														if((k == (j + 1))) {
															if((0 == (j + 1))) {
																{
																	double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$138_2) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample27Value124) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value124);
																}
															}
														}
													}
												}
											} else {
												if(true) {
													for(int index$sample11$139 = 0; index$sample11$139 < weightings.length; index$sample11$139 += 1) {
														int distributionTempVariable$var11$141 = index$sample11$139;
														double cv$probabilitySample11Value140 = (cv$probabilitySample27Value124 * distribution$sample11[index$sample11$139]);
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var63$142_2 = v2[k];
																if((k == (j + 1))) {
																	if((0 == (j + 1))) {
																		{
																			double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$142_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value140) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value140);
																		}
																	}
																	if(!true) {
																		for(int index$sample11$161 = 0; index$sample11$161 < weightings.length; index$sample11$161 += 1) {
																			int distributionTempVariable$var11$163 = index$sample11$161;
																			double cv$probabilitySample11Value162 = (cv$probabilitySample11Value140 * distribution$sample11[index$sample11$161]);
																			if((0 == (j + 1))) {
																				{
																					double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$142_2) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample11Value162) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
					} else {
						if(true) {
							for(int index$sample5$117 = 0; index$sample5$117 < weightings.length; index$sample5$117 += 1) {
								int distributionTempVariable$v1$119 = index$sample5$117;
								double cv$probabilitySample5Value118 = (1.0 * distribution$sample5[index$sample5$117]);
								if(fixedFlag$sample27) {
									for(int i = 0; i < size; i += 1) {
										if(((i + 1) == j)) {
											if(fixedFlag$sample11) {
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														int traceTempVariable$var63$143_2 = v2[k];
														if((k == (j + 1))) {
															if((0 == (j + 1))) {
																{
																	double var68 = ((((1.0 * distributionTempVariable$v1$119) + v2[j]) + traceTempVariable$var63$143_2) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample5Value118) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value118);
																}
															}
														}
													}
												}
											} else {
												if(true) {
													for(int index$sample11$144 = 0; index$sample11$144 < weightings.length; index$sample11$144 += 1) {
														int distributionTempVariable$var11$146 = index$sample11$144;
														double cv$probabilitySample11Value145 = (cv$probabilitySample5Value118 * distribution$sample11[index$sample11$144]);
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var63$147_2 = v2[k];
																if((k == (j + 1))) {
																	if((0 == (j + 1))) {
																		{
																			double var68 = ((((1.0 * distributionTempVariable$v1$119) + v2[j]) + traceTempVariable$var63$147_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value145) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value145);
																		}
																	}
																	if(!true) {
																		for(int index$sample11$167 = 0; index$sample11$167 < weightings.length; index$sample11$167 += 1) {
																			int distributionTempVariable$var11$169 = index$sample11$167;
																			double cv$probabilitySample11Value168 = (cv$probabilitySample11Value145 * distribution$sample11[index$sample11$167]);
																			if((0 == (j + 1))) {
																				{
																					double var68 = ((((1.0 * distributionTempVariable$v1$119) + v2[j]) + traceTempVariable$var63$147_2) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample11Value168) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value168);
																				}
																			}
																		}
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
											for(int index$sample27$129 = 0; index$sample27$129 < weightings.length; index$sample27$129 += 1) {
												int distributionTempVariable$var27$131 = index$sample27$129;
												double cv$probabilitySample27Value130 = (cv$probabilitySample5Value118 * distribution$sample27[((i - 0) / 1)][index$sample27$129]);
												if(((i + 1) == j)) {
													if(fixedFlag$sample11) {
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var63$148_2 = v2[k];
																if((k == (j + 1))) {
																	if((0 == (j + 1))) {
																		{
																			double var68 = ((((1.0 * distributionTempVariable$v1$119) + v2[j]) + traceTempVariable$var63$148_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample27Value130) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value130);
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample11$149 = 0; index$sample11$149 < weightings.length; index$sample11$149 += 1) {
																int distributionTempVariable$var11$151 = index$sample11$149;
																double cv$probabilitySample11Value150 = (cv$probabilitySample27Value130 * distribution$sample11[index$sample11$149]);
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var63$152_2 = v2[k];
																		if((k == (j + 1))) {
																			if((0 == (j + 1))) {
																				{
																					double var68 = ((((1.0 * distributionTempVariable$v1$119) + v2[j]) + traceTempVariable$var63$152_2) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample11Value150) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value150);
																				}
																			}
																			if(!true) {
																				for(int index$sample11$173 = 0; index$sample11$173 < weightings.length; index$sample11$173 += 1) {
																					int distributionTempVariable$var11$175 = index$sample11$173;
																					double cv$probabilitySample11Value174 = (cv$probabilitySample11Value150 * distribution$sample11[index$sample11$173]);
																					if((0 == (j + 1))) {
																						{
																							double var68 = ((((1.0 * distributionTempVariable$v1$119) + v2[j]) + traceTempVariable$var63$152_2) / v2[(j + 1)]);
																							double cv$weightedProbability = (Math.log(cv$probabilitySample11Value174) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
					if(fixedFlag$sample5) {
						if(fixedFlag$sample27) {
							for(int i = 0; i < size; i += 1) {
								if(((i + 1) == j)) {
									for(int index$i$194_1 = 0; index$i$194_1 < size; index$i$194_1 += 1) {
										for(int k = 0; k < (size + 1); k += 1) {
											if(((index$i$194_1 + 1) == k)) {
												int traceTempVariable$var63$194_3 = v2[k];
												if((k == (j + 1))) {
													if(fixedFlag$sample11) {
														if((0 == (j + 1))) {
															{
																double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$194_3) / v2[(j + 1)]);
																double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
															for(int index$sample11$209 = 0; index$sample11$209 < weightings.length; index$sample11$209 += 1) {
																int distributionTempVariable$var11$211 = index$sample11$209;
																double cv$probabilitySample11Value210 = (1.0 * distribution$sample11[index$sample11$209]);
																if((0 == (j + 1))) {
																	{
																		double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$194_3) / v2[(j + 1)]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample11Value210) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
						} else {
							for(int i = 0; i < size; i += 1) {
								if(true) {
									for(int index$sample27$184 = 0; index$sample27$184 < weightings.length; index$sample27$184 += 1) {
										int distributionTempVariable$var27$186 = index$sample27$184;
										double cv$probabilitySample27Value185 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$184]);
										if(((i + 1) == j)) {
											for(int k = 0; k < (size + 1); k += 1) {
												if(((i + 1) == k)) {
													int traceTempVariable$var63$195_2 = v2[k];
													if((k == (j + 1))) {
														if(fixedFlag$sample11) {
															if((0 == (j + 1))) {
																{
																	double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$195_2) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample27Value185) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
														} else {
															if(true) {
																for(int index$sample11$214 = 0; index$sample11$214 < weightings.length; index$sample11$214 += 1) {
																	int distributionTempVariable$var11$216 = index$sample11$214;
																	double cv$probabilitySample11Value215 = (cv$probabilitySample27Value185 * distribution$sample11[index$sample11$214]);
																	if((0 == (j + 1))) {
																		{
																			double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$195_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value215) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
														for(int k = 0; k < (size + 1); k += 1) {
															if(((index$i$196 + 1) == k)) {
																int traceTempVariable$var63$200_2 = v2[k];
																if((k == (j + 1))) {
																	if(fixedFlag$sample11) {
																		if((0 == (j + 1))) {
																			{
																				double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$200_2) / v2[(j + 1)]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample27Value198) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
																	} else {
																		if(true) {
																			for(int index$sample11$219 = 0; index$sample11$219 < weightings.length; index$sample11$219 += 1) {
																				int distributionTempVariable$var11$221 = index$sample11$219;
																				double cv$probabilitySample11Value220 = (cv$probabilitySample27Value198 * distribution$sample11[index$sample11$219]);
																				if((0 == (j + 1))) {
																					{
																						double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$200_2) / v2[(j + 1)]);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample11Value220) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
					} else {
						if(true) {
							for(int index$sample5$178 = 0; index$sample5$178 < weightings.length; index$sample5$178 += 1) {
								int distributionTempVariable$v1$180 = index$sample5$178;
								double cv$probabilitySample5Value179 = (1.0 * distribution$sample5[index$sample5$178]);
								if(fixedFlag$sample27) {
									for(int i = 0; i < size; i += 1) {
										if(((i + 1) == j)) {
											for(int index$i$201_1 = 0; index$i$201_1 < size; index$i$201_1 += 1) {
												for(int k = 0; k < (size + 1); k += 1) {
													if(((index$i$201_1 + 1) == k)) {
														int traceTempVariable$var63$201_3 = v2[k];
														if((k == (j + 1))) {
															if(fixedFlag$sample11) {
																if((0 == (j + 1))) {
																	{
																		double var68 = ((((1.0 * distributionTempVariable$v1$180) + v2[j]) + traceTempVariable$var63$201_3) / v2[(j + 1)]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample5Value179) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
															} else {
																if(true) {
																	for(int index$sample11$224 = 0; index$sample11$224 < weightings.length; index$sample11$224 += 1) {
																		int distributionTempVariable$var11$226 = index$sample11$224;
																		double cv$probabilitySample11Value225 = (cv$probabilitySample5Value179 * distribution$sample11[index$sample11$224]);
																		if((0 == (j + 1))) {
																			{
																				double var68 = ((((1.0 * distributionTempVariable$v1$180) + v2[j]) + traceTempVariable$var63$201_3) / v2[(j + 1)]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample11Value225) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
								} else {
									for(int i = 0; i < size; i += 1) {
										if(true) {
											for(int index$sample27$190 = 0; index$sample27$190 < weightings.length; index$sample27$190 += 1) {
												int distributionTempVariable$var27$192 = index$sample27$190;
												double cv$probabilitySample27Value191 = (cv$probabilitySample5Value179 * distribution$sample27[((i - 0) / 1)][index$sample27$190]);
												if(((i + 1) == j)) {
													for(int k = 0; k < (size + 1); k += 1) {
														if(((i + 1) == k)) {
															int traceTempVariable$var63$202_2 = v2[k];
															if((k == (j + 1))) {
																if(fixedFlag$sample11) {
																	if((0 == (j + 1))) {
																		{
																			double var68 = ((((1.0 * distributionTempVariable$v1$180) + v2[j]) + traceTempVariable$var63$202_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample27Value191) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
																} else {
																	if(true) {
																		for(int index$sample11$229 = 0; index$sample11$229 < weightings.length; index$sample11$229 += 1) {
																			int distributionTempVariable$var11$231 = index$sample11$229;
																			double cv$probabilitySample11Value230 = (cv$probabilitySample27Value191 * distribution$sample11[index$sample11$229]);
																			if((0 == (j + 1))) {
																				{
																					double var68 = ((((1.0 * distributionTempVariable$v1$180) + v2[j]) + traceTempVariable$var63$202_2) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample11Value230) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((index$i$203 + 1) == k)) {
																		int traceTempVariable$var63$207_2 = v2[k];
																		if((k == (j + 1))) {
																			if(fixedFlag$sample11) {
																				if((0 == (j + 1))) {
																					{
																						double var68 = ((((1.0 * distributionTempVariable$v1$180) + v2[j]) + traceTempVariable$var63$207_2) / v2[(j + 1)]);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample27Value205) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
																			} else {
																				if(true) {
																					for(int index$sample11$234 = 0; index$sample11$234 < weightings.length; index$sample11$234 += 1) {
																						int distributionTempVariable$var11$236 = index$sample11$234;
																						double cv$probabilitySample11Value235 = (cv$probabilitySample27Value205 * distribution$sample11[index$sample11$234]);
																						if((0 == (j + 1))) {
																							{
																								double var68 = ((((1.0 * distributionTempVariable$v1$180) + v2[j]) + traceTempVariable$var63$207_2) / v2[(j + 1)]);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample11Value235) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
					if(fixedFlag$sample5) {
						if(fixedFlag$sample11) {
							if((0 == j)) {
								for(int k = 0; k < (size + 1); k += 1) {
									if((0 == k)) {
										int traceTempVariable$var63$253_2 = v2[k];
										if((k == (j + 1))) {
											if(fixedFlag$sample27) {
												for(int i = 0; i < size; i += 1) {
													if(((i + 1) == (j + 1))) {
														{
															double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$253_2) / v2[(j + 1)]);
															double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
														for(int index$sample27$267 = 0; index$sample27$267 < weightings.length; index$sample27$267 += 1) {
															int distributionTempVariable$var27$269 = index$sample27$267;
															double cv$probabilitySample27Value268 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$267]);
															if(((i + 1) == (j + 1))) {
																{
																	double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$253_2) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample27Value268) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
						} else {
							if(true) {
								for(int index$sample11$244 = 0; index$sample11$244 < weightings.length; index$sample11$244 += 1) {
									int distributionTempVariable$var11$246 = index$sample11$244;
									double cv$probabilitySample11Value245 = (1.0 * distribution$sample11[index$sample11$244]);
									if((0 == j)) {
										for(int k = 0; k < (size + 1); k += 1) {
											if((0 == k)) {
												int traceTempVariable$var63$254_2 = v2[k];
												if((k == (j + 1))) {
													if(fixedFlag$sample27) {
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == (j + 1))) {
																{
																	double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$254_2) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample11Value245) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
													} else {
														for(int i = 0; i < size; i += 1) {
															if(true) {
																for(int index$sample27$273 = 0; index$sample27$273 < weightings.length; index$sample27$273 += 1) {
																	int distributionTempVariable$var27$275 = index$sample27$273;
																	double cv$probabilitySample27Value274 = (cv$probabilitySample11Value245 * distribution$sample27[((i - 0) / 1)][index$sample27$273]);
																	if(((i + 1) == (j + 1))) {
																		{
																			double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$254_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample27Value274) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														int traceTempVariable$var63$258_2 = v2[k];
														if((k == (j + 1))) {
															if(fixedFlag$sample27) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == (j + 1))) {
																		{
																			double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$258_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value256) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
															} else {
																for(int i = 0; i < size; i += 1) {
																	if(true) {
																		for(int index$sample27$279 = 0; index$sample27$279 < weightings.length; index$sample27$279 += 1) {
																			int distributionTempVariable$var27$281 = index$sample27$279;
																			double cv$probabilitySample27Value280 = (cv$probabilitySample11Value256 * distribution$sample27[((i - 0) / 1)][index$sample27$279]);
																			if(((i + 1) == (j + 1))) {
																				{
																					double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$258_2) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample27Value280) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
					} else {
						if(true) {
							for(int index$sample5$239 = 0; index$sample5$239 < weightings.length; index$sample5$239 += 1) {
								int distributionTempVariable$v1$241 = index$sample5$239;
								double cv$probabilitySample5Value240 = (1.0 * distribution$sample5[index$sample5$239]);
								if(fixedFlag$sample11) {
									if((0 == j)) {
										for(int k = 0; k < (size + 1); k += 1) {
											if((0 == k)) {
												int traceTempVariable$var63$259_2 = v2[k];
												if((k == (j + 1))) {
													if(fixedFlag$sample27) {
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == (j + 1))) {
																{
																	double var68 = ((((1.0 * distributionTempVariable$v1$241) + v2[j]) + traceTempVariable$var63$259_2) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample5Value240) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
													} else {
														for(int i = 0; i < size; i += 1) {
															if(true) {
																for(int index$sample27$285 = 0; index$sample27$285 < weightings.length; index$sample27$285 += 1) {
																	int distributionTempVariable$var27$287 = index$sample27$285;
																	double cv$probabilitySample27Value286 = (cv$probabilitySample5Value240 * distribution$sample27[((i - 0) / 1)][index$sample27$285]);
																	if(((i + 1) == (j + 1))) {
																		{
																			double var68 = ((((1.0 * distributionTempVariable$v1$241) + v2[j]) + traceTempVariable$var63$259_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample27Value286) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
								} else {
									if(true) {
										for(int index$sample11$249 = 0; index$sample11$249 < weightings.length; index$sample11$249 += 1) {
											int distributionTempVariable$var11$251 = index$sample11$249;
											double cv$probabilitySample11Value250 = (cv$probabilitySample5Value240 * distribution$sample11[index$sample11$249]);
											if((0 == j)) {
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														int traceTempVariable$var63$260_2 = v2[k];
														if((k == (j + 1))) {
															if(fixedFlag$sample27) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == (j + 1))) {
																		{
																			double var68 = ((((1.0 * distributionTempVariable$v1$241) + v2[j]) + traceTempVariable$var63$260_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value250) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
															} else {
																for(int i = 0; i < size; i += 1) {
																	if(true) {
																		for(int index$sample27$291 = 0; index$sample27$291 < weightings.length; index$sample27$291 += 1) {
																			int distributionTempVariable$var27$293 = index$sample27$291;
																			double cv$probabilitySample27Value292 = (cv$probabilitySample11Value250 * distribution$sample27[((i - 0) / 1)][index$sample27$291]);
																			if(((i + 1) == (j + 1))) {
																				{
																					double var68 = ((((1.0 * distributionTempVariable$v1$241) + v2[j]) + traceTempVariable$var63$260_2) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample27Value292) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var63$264_2 = v2[k];
																if((k == (j + 1))) {
																	if(fixedFlag$sample27) {
																		for(int i = 0; i < size; i += 1) {
																			if(((i + 1) == (j + 1))) {
																				{
																					double var68 = ((((1.0 * distributionTempVariable$v1$241) + v2[j]) + traceTempVariable$var63$264_2) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample11Value262) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
																	} else {
																		for(int i = 0; i < size; i += 1) {
																			if(true) {
																				for(int index$sample27$297 = 0; index$sample27$297 < weightings.length; index$sample27$297 += 1) {
																					int distributionTempVariable$var27$299 = index$sample27$297;
																					double cv$probabilitySample27Value298 = (cv$probabilitySample11Value262 * distribution$sample27[((i - 0) / 1)][index$sample27$297]);
																					if(((i + 1) == (j + 1))) {
																						{
																							double var68 = ((((1.0 * distributionTempVariable$v1$241) + v2[j]) + traceTempVariable$var63$264_2) / v2[(j + 1)]);
																							double cv$weightedProbability = (Math.log(cv$probabilitySample27Value298) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
					if(fixedFlag$sample5) {
						if(fixedFlag$sample11) {
							if((0 == j)) {
								if(fixedFlag$sample27) {
									for(int i = 0; i < size; i += 1) {
										for(int k = 0; k < (size + 1); k += 1) {
											if(((i + 1) == k)) {
												int traceTempVariable$var63$316_3 = v2[k];
												if((k == (j + 1))) {
													for(int index$i$340_1 = 0; index$i$340_1 < size; index$i$340_1 += 1) {
														if(((index$i$340_1 + 1) == (j + 1))) {
															{
																double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$316_3) / v2[(j + 1)]);
																double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
											for(int index$sample27$318 = 0; index$sample27$318 < weightings.length; index$sample27$318 += 1) {
												int distributionTempVariable$var27$320 = index$sample27$318;
												double cv$probabilitySample27Value319 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$318]);
												for(int k = 0; k < (size + 1); k += 1) {
													if(((i + 1) == k)) {
														int traceTempVariable$var63$321_2 = v2[k];
														if((k == (j + 1))) {
															if(((i + 1) == (j + 1))) {
																{
																	double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$321_2) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample27Value319) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value319);
																}
															}
															for(int index$i$342 = 0; index$i$342 < size; index$i$342 += 1) {
																if(!(index$i$342 == i)) {
																	for(int index$sample27$343 = 0; index$sample27$343 < weightings.length; index$sample27$343 += 1) {
																		int distributionTempVariable$var27$345 = index$sample27$343;
																		double cv$probabilitySample27Value344 = (cv$probabilitySample27Value319 * distribution$sample27[((index$i$342 - 0) / 1)][index$sample27$343]);
																		if(((index$i$342 + 1) == (j + 1))) {
																			{
																				double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$321_2) / v2[(j + 1)]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample27Value344) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value344);
																			}
																		}
																	}
																}
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
								for(int index$sample11$307 = 0; index$sample11$307 < weightings.length; index$sample11$307 += 1) {
									int distributionTempVariable$var11$309 = index$sample11$307;
									double cv$probabilitySample11Value308 = (1.0 * distribution$sample11[index$sample11$307]);
									if((0 == j)) {
										if(fixedFlag$sample27) {
											for(int i = 0; i < size; i += 1) {
												for(int k = 0; k < (size + 1); k += 1) {
													if(((i + 1) == k)) {
														int traceTempVariable$var63$322_3 = v2[k];
														if((k == (j + 1))) {
															for(int index$i$347_1 = 0; index$i$347_1 < size; index$i$347_1 += 1) {
																if(((index$i$347_1 + 1) == (j + 1))) {
																	{
																		double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$322_3) / v2[(j + 1)]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample11Value308) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																		if((cv$weightedProbability < cv$distributionAccumulator))
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																		else {
																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																				cv$distributionAccumulator = cv$weightedProbability;
																			else
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																		}
																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value308);
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
													for(int index$sample27$324 = 0; index$sample27$324 < weightings.length; index$sample27$324 += 1) {
														int distributionTempVariable$var27$326 = index$sample27$324;
														double cv$probabilitySample27Value325 = (cv$probabilitySample11Value308 * distribution$sample27[((i - 0) / 1)][index$sample27$324]);
														for(int k = 0; k < (size + 1); k += 1) {
															if(((i + 1) == k)) {
																int traceTempVariable$var63$327_2 = v2[k];
																if((k == (j + 1))) {
																	if(((i + 1) == (j + 1))) {
																		{
																			double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$327_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample27Value325) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value325);
																		}
																	}
																	for(int index$i$349 = 0; index$i$349 < size; index$i$349 += 1) {
																		if(!(index$i$349 == i)) {
																			for(int index$sample27$350 = 0; index$sample27$350 < weightings.length; index$sample27$350 += 1) {
																				int distributionTempVariable$var27$352 = index$sample27$350;
																				double cv$probabilitySample27Value351 = (cv$probabilitySample27Value325 * distribution$sample27[((index$i$349 - 0) / 1)][index$sample27$350]);
																				if(((index$i$349 + 1) == (j + 1))) {
																					{
																						double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$327_2) / v2[(j + 1)]);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample27Value351) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value351);
																					}
																				}
																			}
																		}
																	}
																}
															}
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
								if(fixedFlag$sample11) {
									if((0 == j)) {
										if(fixedFlag$sample27) {
											for(int i = 0; i < size; i += 1) {
												for(int k = 0; k < (size + 1); k += 1) {
													if(((i + 1) == k)) {
														int traceTempVariable$var63$328_3 = v2[k];
														if((k == (j + 1))) {
															for(int index$i$354_1 = 0; index$i$354_1 < size; index$i$354_1 += 1) {
																if(((index$i$354_1 + 1) == (j + 1))) {
																	{
																		double var68 = ((((1.0 * distributionTempVariable$v1$304) + v2[j]) + traceTempVariable$var63$328_3) / v2[(j + 1)]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample5Value303) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
											}
										} else {
											for(int i = 0; i < size; i += 1) {
												if(true) {
													for(int index$sample27$330 = 0; index$sample27$330 < weightings.length; index$sample27$330 += 1) {
														int distributionTempVariable$var27$332 = index$sample27$330;
														double cv$probabilitySample27Value331 = (cv$probabilitySample5Value303 * distribution$sample27[((i - 0) / 1)][index$sample27$330]);
														for(int k = 0; k < (size + 1); k += 1) {
															if(((i + 1) == k)) {
																int traceTempVariable$var63$333_2 = v2[k];
																if((k == (j + 1))) {
																	if(((i + 1) == (j + 1))) {
																		{
																			double var68 = ((((1.0 * distributionTempVariable$v1$304) + v2[j]) + traceTempVariable$var63$333_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample27Value331) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value331);
																		}
																	}
																	for(int index$i$356 = 0; index$i$356 < size; index$i$356 += 1) {
																		if(!(index$i$356 == i)) {
																			for(int index$sample27$357 = 0; index$sample27$357 < weightings.length; index$sample27$357 += 1) {
																				int distributionTempVariable$var27$359 = index$sample27$357;
																				double cv$probabilitySample27Value358 = (cv$probabilitySample27Value331 * distribution$sample27[((index$i$356 - 0) / 1)][index$sample27$357]);
																				if(((index$i$356 + 1) == (j + 1))) {
																					{
																						double var68 = ((((1.0 * distributionTempVariable$v1$304) + v2[j]) + traceTempVariable$var63$333_2) / v2[(j + 1)]);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample27Value358) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value358);
																					}
																				}
																			}
																		}
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
										for(int index$sample11$312 = 0; index$sample11$312 < weightings.length; index$sample11$312 += 1) {
											int distributionTempVariable$var11$314 = index$sample11$312;
											double cv$probabilitySample11Value313 = (cv$probabilitySample5Value303 * distribution$sample11[index$sample11$312]);
											if((0 == j)) {
												if(fixedFlag$sample27) {
													for(int i = 0; i < size; i += 1) {
														for(int k = 0; k < (size + 1); k += 1) {
															if(((i + 1) == k)) {
																int traceTempVariable$var63$334_3 = v2[k];
																if((k == (j + 1))) {
																	for(int index$i$361_1 = 0; index$i$361_1 < size; index$i$361_1 += 1) {
																		if(((index$i$361_1 + 1) == (j + 1))) {
																			{
																				double var68 = ((((1.0 * distributionTempVariable$v1$304) + v2[j]) + traceTempVariable$var63$334_3) / v2[(j + 1)]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample11Value313) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value313);
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
															for(int index$sample27$336 = 0; index$sample27$336 < weightings.length; index$sample27$336 += 1) {
																int distributionTempVariable$var27$338 = index$sample27$336;
																double cv$probabilitySample27Value337 = (cv$probabilitySample11Value313 * distribution$sample27[((i - 0) / 1)][index$sample27$336]);
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((i + 1) == k)) {
																		int traceTempVariable$var63$339_2 = v2[k];
																		if((k == (j + 1))) {
																			if(((i + 1) == (j + 1))) {
																				{
																					double var68 = ((((1.0 * distributionTempVariable$v1$304) + v2[j]) + traceTempVariable$var63$339_2) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample27Value337) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value337);
																				}
																			}
																			for(int index$i$363 = 0; index$i$363 < size; index$i$363 += 1) {
																				if(!(index$i$363 == i)) {
																					for(int index$sample27$364 = 0; index$sample27$364 < weightings.length; index$sample27$364 += 1) {
																						int distributionTempVariable$var27$366 = index$sample27$364;
																						double cv$probabilitySample27Value365 = (cv$probabilitySample27Value337 * distribution$sample27[((index$i$363 - 0) / 1)][index$sample27$364]);
																						if(((index$i$363 + 1) == (j + 1))) {
																							{
																								double var68 = ((((1.0 * distributionTempVariable$v1$304) + v2[j]) + traceTempVariable$var63$339_2) / v2[(j + 1)]);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample27Value365) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value365);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
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
						if(fixedFlag$sample27) {
							for(int i = 0; i < size; i += 1) {
								if(((i + 1) == j)) {
									if(fixedFlag$sample11) {
										for(int k = 0; k < (size + 1); k += 1) {
											if((0 == k)) {
												int traceTempVariable$var63$385_2 = v2[k];
												if((k == (j + 1))) {
													for(int index$i$405_1 = 0; index$i$405_1 < size; index$i$405_1 += 1) {
														if(((index$i$405_1 + 1) == (j + 1))) {
															{
																double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$385_2) / v2[(j + 1)]);
																double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
											for(int index$sample11$386 = 0; index$sample11$386 < weightings.length; index$sample11$386 += 1) {
												int distributionTempVariable$var11$388 = index$sample11$386;
												double cv$probabilitySample11Value387 = (1.0 * distribution$sample11[index$sample11$386]);
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														int traceTempVariable$var63$389_2 = v2[k];
														if((k == (j + 1))) {
															for(int index$i$406_1 = 0; index$i$406_1 < size; index$i$406_1 += 1) {
																if(((index$i$406_1 + 1) == (j + 1))) {
																	{
																		double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$389_2) / v2[(j + 1)]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample11Value387) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																		if((cv$weightedProbability < cv$distributionAccumulator))
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																		else {
																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																				cv$distributionAccumulator = cv$weightedProbability;
																			else
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																		}
																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value387);
																	}
																}
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
									for(int index$sample27$375 = 0; index$sample27$375 < weightings.length; index$sample27$375 += 1) {
										int distributionTempVariable$var27$377 = index$sample27$375;
										double cv$probabilitySample27Value376 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$375]);
										if(((i + 1) == j)) {
											if(fixedFlag$sample11) {
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														int traceTempVariable$var63$390_2 = v2[k];
														if((k == (j + 1))) {
															if(((i + 1) == (j + 1))) {
																{
																	double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$390_2) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample27Value376) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value376);
																}
															}
															for(int index$i$408 = 0; index$i$408 < size; index$i$408 += 1) {
																if(!(index$i$408 == i)) {
																	for(int index$sample27$409 = 0; index$sample27$409 < weightings.length; index$sample27$409 += 1) {
																		int distributionTempVariable$var27$411 = index$sample27$409;
																		double cv$probabilitySample27Value410 = (cv$probabilitySample27Value376 * distribution$sample27[((index$i$408 - 0) / 1)][index$sample27$409]);
																		if(((index$i$408 + 1) == (j + 1))) {
																			{
																				double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$390_2) / v2[(j + 1)]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample27Value410) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value410);
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
													for(int index$sample11$391 = 0; index$sample11$391 < weightings.length; index$sample11$391 += 1) {
														int distributionTempVariable$var11$393 = index$sample11$391;
														double cv$probabilitySample11Value392 = (cv$probabilitySample27Value376 * distribution$sample11[index$sample11$391]);
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var63$394_2 = v2[k];
																if((k == (j + 1))) {
																	if(((i + 1) == (j + 1))) {
																		{
																			double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$394_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value392) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value392);
																		}
																	}
																	for(int index$i$414 = 0; index$i$414 < size; index$i$414 += 1) {
																		if(!(index$i$414 == i)) {
																			for(int index$sample27$415 = 0; index$sample27$415 < weightings.length; index$sample27$415 += 1) {
																				int distributionTempVariable$var27$417 = index$sample27$415;
																				double cv$probabilitySample27Value416 = (cv$probabilitySample11Value392 * distribution$sample27[((index$i$414 - 0) / 1)][index$sample27$415]);
																				if(((index$i$414 + 1) == (j + 1))) {
																					{
																						double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$394_2) / v2[(j + 1)]);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample27Value416) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
					} else {
						if(true) {
							for(int index$sample5$369 = 0; index$sample5$369 < weightings.length; index$sample5$369 += 1) {
								int distributionTempVariable$v1$371 = index$sample5$369;
								double cv$probabilitySample5Value370 = (1.0 * distribution$sample5[index$sample5$369]);
								if(fixedFlag$sample27) {
									for(int i = 0; i < size; i += 1) {
										if(((i + 1) == j)) {
											if(fixedFlag$sample11) {
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														int traceTempVariable$var63$395_2 = v2[k];
														if((k == (j + 1))) {
															for(int index$i$419_1 = 0; index$i$419_1 < size; index$i$419_1 += 1) {
																if(((index$i$419_1 + 1) == (j + 1))) {
																	{
																		double var68 = ((((1.0 * distributionTempVariable$v1$371) + v2[j]) + traceTempVariable$var63$395_2) / v2[(j + 1)]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample5Value370) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																		if((cv$weightedProbability < cv$distributionAccumulator))
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																		else {
																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																				cv$distributionAccumulator = cv$weightedProbability;
																			else
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																		}
																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value370);
																	}
																}
															}
														}
													}
												}
											} else {
												if(true) {
													for(int index$sample11$396 = 0; index$sample11$396 < weightings.length; index$sample11$396 += 1) {
														int distributionTempVariable$var11$398 = index$sample11$396;
														double cv$probabilitySample11Value397 = (cv$probabilitySample5Value370 * distribution$sample11[index$sample11$396]);
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var63$399_2 = v2[k];
																if((k == (j + 1))) {
																	for(int index$i$420_1 = 0; index$i$420_1 < size; index$i$420_1 += 1) {
																		if(((index$i$420_1 + 1) == (j + 1))) {
																			{
																				double var68 = ((((1.0 * distributionTempVariable$v1$371) + v2[j]) + traceTempVariable$var63$399_2) / v2[(j + 1)]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample11Value397) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value397);
																			}
																		}
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
											for(int index$sample27$381 = 0; index$sample27$381 < weightings.length; index$sample27$381 += 1) {
												int distributionTempVariable$var27$383 = index$sample27$381;
												double cv$probabilitySample27Value382 = (cv$probabilitySample5Value370 * distribution$sample27[((i - 0) / 1)][index$sample27$381]);
												if(((i + 1) == j)) {
													if(fixedFlag$sample11) {
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var63$400_2 = v2[k];
																if((k == (j + 1))) {
																	if(((i + 1) == (j + 1))) {
																		{
																			double var68 = ((((1.0 * distributionTempVariable$v1$371) + v2[j]) + traceTempVariable$var63$400_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample27Value382) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value382);
																		}
																	}
																	for(int index$i$422 = 0; index$i$422 < size; index$i$422 += 1) {
																		if(!(index$i$422 == i)) {
																			for(int index$sample27$423 = 0; index$sample27$423 < weightings.length; index$sample27$423 += 1) {
																				int distributionTempVariable$var27$425 = index$sample27$423;
																				double cv$probabilitySample27Value424 = (cv$probabilitySample27Value382 * distribution$sample27[((index$i$422 - 0) / 1)][index$sample27$423]);
																				if(((index$i$422 + 1) == (j + 1))) {
																					{
																						double var68 = ((((1.0 * distributionTempVariable$v1$371) + v2[j]) + traceTempVariable$var63$400_2) / v2[(j + 1)]);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample27Value424) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value424);
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
															for(int index$sample11$401 = 0; index$sample11$401 < weightings.length; index$sample11$401 += 1) {
																int distributionTempVariable$var11$403 = index$sample11$401;
																double cv$probabilitySample11Value402 = (cv$probabilitySample27Value382 * distribution$sample11[index$sample11$401]);
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var63$404_2 = v2[k];
																		if((k == (j + 1))) {
																			if(((i + 1) == (j + 1))) {
																				{
																					double var68 = ((((1.0 * distributionTempVariable$v1$371) + v2[j]) + traceTempVariable$var63$404_2) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample11Value402) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value402);
																				}
																			}
																			for(int index$i$428 = 0; index$i$428 < size; index$i$428 += 1) {
																				if(!(index$i$428 == i)) {
																					for(int index$sample27$429 = 0; index$sample27$429 < weightings.length; index$sample27$429 += 1) {
																						int distributionTempVariable$var27$431 = index$sample27$429;
																						double cv$probabilitySample27Value430 = (cv$probabilitySample11Value402 * distribution$sample27[((index$i$428 - 0) / 1)][index$sample27$429]);
																						if(((index$i$428 + 1) == (j + 1))) {
																							{
																								double var68 = ((((1.0 * distributionTempVariable$v1$371) + v2[j]) + traceTempVariable$var63$404_2) / v2[(j + 1)]);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample27Value430) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
					if(fixedFlag$sample5) {
						if(fixedFlag$sample27) {
							for(int i = 0; i < size; i += 1) {
								if(((i + 1) == j)) {
									for(int index$i$450_1 = 0; index$i$450_1 < size; index$i$450_1 += 1) {
										for(int k = 0; k < (size + 1); k += 1) {
											if(((index$i$450_1 + 1) == k)) {
												int traceTempVariable$var63$450_3 = v2[k];
												if((k == (j + 1))) {
													for(int index$i$464_1 = 0; index$i$464_1 < size; index$i$464_1 += 1) {
														if(((index$i$464_1 + 1) == (j + 1))) {
															{
																double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$450_3) / v2[(j + 1)]);
																double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
						} else {
							for(int i = 0; i < size; i += 1) {
								if(true) {
									for(int index$sample27$440 = 0; index$sample27$440 < weightings.length; index$sample27$440 += 1) {
										int distributionTempVariable$var27$442 = index$sample27$440;
										double cv$probabilitySample27Value441 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$440]);
										if(((i + 1) == j)) {
											for(int k = 0; k < (size + 1); k += 1) {
												if(((i + 1) == k)) {
													int traceTempVariable$var63$451_2 = v2[k];
													if((k == (j + 1))) {
														if(((i + 1) == (j + 1))) {
															{
																double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$451_2) / v2[(j + 1)]);
																double cv$weightedProbability = (Math.log(cv$probabilitySample27Value441) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
														for(int index$i$466 = 0; index$i$466 < size; index$i$466 += 1) {
															if(!(index$i$466 == i)) {
																for(int index$sample27$467 = 0; index$sample27$467 < weightings.length; index$sample27$467 += 1) {
																	int distributionTempVariable$var27$469 = index$sample27$467;
																	double cv$probabilitySample27Value468 = (cv$probabilitySample27Value441 * distribution$sample27[((index$i$466 - 0) / 1)][index$sample27$467]);
																	if(((index$i$466 + 1) == (j + 1))) {
																		{
																			double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$451_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample27Value468) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
														for(int k = 0; k < (size + 1); k += 1) {
															if(((index$i$452 + 1) == k)) {
																int traceTempVariable$var63$456_2 = v2[k];
																if((k == (j + 1))) {
																	if(((i + 1) == (j + 1))) {
																		{
																			double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$456_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample27Value454) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
																	if(((index$i$452 + 1) == (j + 1))) {
																		{
																			double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$456_2) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample27Value454) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
																	for(int index$i$473 = 0; index$i$473 < size; index$i$473 += 1) {
																		if((!(index$i$473 == i) && !(index$i$473 == index$i$452))) {
																			for(int index$sample27$474 = 0; index$sample27$474 < weightings.length; index$sample27$474 += 1) {
																				int distributionTempVariable$var27$476 = index$sample27$474;
																				double cv$probabilitySample27Value475 = (cv$probabilitySample27Value454 * distribution$sample27[((index$i$473 - 0) / 1)][index$sample27$474]);
																				if(((index$i$473 + 1) == (j + 1))) {
																					{
																						double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$456_2) / v2[(j + 1)]);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample27Value475) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
					} else {
						if(true) {
							for(int index$sample5$434 = 0; index$sample5$434 < weightings.length; index$sample5$434 += 1) {
								int distributionTempVariable$v1$436 = index$sample5$434;
								double cv$probabilitySample5Value435 = (1.0 * distribution$sample5[index$sample5$434]);
								if(fixedFlag$sample27) {
									for(int i = 0; i < size; i += 1) {
										if(((i + 1) == j)) {
											for(int index$i$457_1 = 0; index$i$457_1 < size; index$i$457_1 += 1) {
												for(int k = 0; k < (size + 1); k += 1) {
													if(((index$i$457_1 + 1) == k)) {
														int traceTempVariable$var63$457_3 = v2[k];
														if((k == (j + 1))) {
															for(int index$i$478_1 = 0; index$i$478_1 < size; index$i$478_1 += 1) {
																if(((index$i$478_1 + 1) == (j + 1))) {
																	{
																		double var68 = ((((1.0 * distributionTempVariable$v1$436) + v2[j]) + traceTempVariable$var63$457_3) / v2[(j + 1)]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample5Value435) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
								} else {
									for(int i = 0; i < size; i += 1) {
										if(true) {
											for(int index$sample27$446 = 0; index$sample27$446 < weightings.length; index$sample27$446 += 1) {
												int distributionTempVariable$var27$448 = index$sample27$446;
												double cv$probabilitySample27Value447 = (cv$probabilitySample5Value435 * distribution$sample27[((i - 0) / 1)][index$sample27$446]);
												if(((i + 1) == j)) {
													for(int k = 0; k < (size + 1); k += 1) {
														if(((i + 1) == k)) {
															int traceTempVariable$var63$458_2 = v2[k];
															if((k == (j + 1))) {
																if(((i + 1) == (j + 1))) {
																	{
																		double var68 = ((((1.0 * distributionTempVariable$v1$436) + v2[j]) + traceTempVariable$var63$458_2) / v2[(j + 1)]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample27Value447) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
																for(int index$i$480 = 0; index$i$480 < size; index$i$480 += 1) {
																	if(!(index$i$480 == i)) {
																		for(int index$sample27$481 = 0; index$sample27$481 < weightings.length; index$sample27$481 += 1) {
																			int distributionTempVariable$var27$483 = index$sample27$481;
																			double cv$probabilitySample27Value482 = (cv$probabilitySample27Value447 * distribution$sample27[((index$i$480 - 0) / 1)][index$sample27$481]);
																			if(((index$i$480 + 1) == (j + 1))) {
																				{
																					double var68 = ((((1.0 * distributionTempVariable$v1$436) + v2[j]) + traceTempVariable$var63$458_2) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample27Value482) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((index$i$459 + 1) == k)) {
																		int traceTempVariable$var63$463_2 = v2[k];
																		if((k == (j + 1))) {
																			if(((i + 1) == (j + 1))) {
																				{
																					double var68 = ((((1.0 * distributionTempVariable$v1$436) + v2[j]) + traceTempVariable$var63$463_2) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample27Value461) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
																			if(((index$i$459 + 1) == (j + 1))) {
																				{
																					double var68 = ((((1.0 * distributionTempVariable$v1$436) + v2[j]) + traceTempVariable$var63$463_2) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample27Value461) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
																			for(int index$i$487 = 0; index$i$487 < size; index$i$487 += 1) {
																				if((!(index$i$487 == i) && !(index$i$487 == index$i$459))) {
																					for(int index$sample27$488 = 0; index$sample27$488 < weightings.length; index$sample27$488 += 1) {
																						int distributionTempVariable$var27$490 = index$sample27$488;
																						double cv$probabilitySample27Value489 = (cv$probabilitySample27Value461 * distribution$sample27[((index$i$487 - 0) / 1)][index$sample27$488]);
																						if(((index$i$487 + 1) == (j + 1))) {
																							{
																								double var68 = ((((1.0 * distributionTempVariable$v1$436) + v2[j]) + traceTempVariable$var63$463_2) / v2[(j + 1)]);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample27Value489) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var69[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample70[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample70 = ((fixedFlag$sample5 && fixedFlag$sample11) && fixedFlag$sample27);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample70[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var69[((j - 0) / 1)] = cv$rvAccumulator;
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
			logProbability$var10 = cv$sampleAccumulator;
			logProbability$var11 = cv$sampleProbability;
			boolean cv$guard$v3 = false;
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			{
				for(int k = 0; k < (size + 1); k += 1) {
					if((0 == k)) {
						if(!cv$guard$v3) {
							cv$guard$v3 = true;
							logProbability$v3 = (logProbability$v3 + cv$accumulator);
						}
					}
				}
			}
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
			logProbability$var10 = cv$rvAccumulator;
			boolean cv$guard$v3 = false;
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			{
				for(int k = 0; k < (size + 1); k += 1) {
					if((0 == k)) {
						if(!cv$guard$v3) {
							cv$guard$v3 = true;
							logProbability$v3 = (logProbability$v3 + cv$accumulator);
						}
					}
				}
			}
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
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var26[((i - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample27[((i - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$v3 = false;
				int index$i$3 = i;
				{
					for(int k = 0; k < (size + 1); k += 1) {
						if(((i + 1) == k)) {
							if(!cv$guard$v3) {
								cv$guard$v3 = true;
								logProbability$v3 = (logProbability$v3 + cv$sampleProbability);
							}
						}
					}
				}
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
				logProbability$var26[((i - 0) / 1)] = cv$rvAccumulator;
				boolean cv$guard$v3 = false;
				int index$i$5 = i;
				{
					for(int k = 0; k < (size + 1); k += 1) {
						if(((i + 1) == k)) {
							if(!cv$guard$v3) {
								cv$guard$v3 = true;
								logProbability$v3 = (logProbability$v3 + cv$sampleValue);
							}
						}
					}
				}
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
			logProbability$var4 = cv$sampleAccumulator;
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
			logProbability$var4 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample70() {
		if(!fixedProbFlag$sample70) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < size; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = v[j];
					{
						{
							double var68 = ((((1.0 * v1) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
							double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var68:(1.0 - var68))));
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
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var69[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample70[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample70 = ((fixedFlag$sample5 && fixedFlag$sample11) && fixedFlag$sample27);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample70[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var69[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample11() {
		if(true) {
			int cv$numNumStates = 0;
			{
				cv$numNumStates = Math.max(cv$numNumStates, weightings.length);
			}
			double[] cv$stateProbabilityLocal = cv$var11$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
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
					int cv$temp$1$$var3280;
					{
						cv$temp$1$$var3280 = weightings.length;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var3280))?Math.log(cv$temp$0$weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							boolean[] guard$sample11bernoulli69 = guard$sample11bernoulli69$global;
							for(int j = 0; j < size; j += 1) {
								if((0 == j))
									guard$sample11bernoulli69[((j - 0) / 1)] = false;
							}
							for(int j = 0; j < size; j += 1) {
								if((0 == (j + 1)))
									guard$sample11bernoulli69[((j - 0) / 1)] = false;
							}
							for(int k = 0; k < (size + 1); k += 1) {
								if((0 == k)) {
									for(int j = 0; j < size; j += 1) {
										if((k == (j + 1)))
											guard$sample11bernoulli69[((j - 0) / 1)] = false;
									}
								}
							}
							int traceTempVariable$var59$4_1 = cv$currentValue;
							for(int j = 0; j < size; j += 1) {
								if((0 == j)) {
									if(!guard$sample11bernoulli69[((j - 0) / 1)]) {
										guard$sample11bernoulli69[((j - 0) / 1)] = true;
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if(fixedFlag$sample5) {
													int traceTempVariable$var44$15_1 = cv$currentValue;
													for(int k = 0; k < (size + 1); k += 1) {
														if((0 == k)) {
															int traceTempVariable$var63$15_3 = traceTempVariable$var44$15_1;
															if((k == (j + 1))) {
																int traceTempVariable$var67$25_1 = cv$currentValue;
																if((0 == (j + 1))) {
																	{
																		{
																			double cv$temp$2$var68;
																			{
																				double var68 = ((((1.0 * v1) + traceTempVariable$var59$4_1) + traceTempVariable$var63$15_3) / traceTempVariable$var67$25_1);
																				cv$temp$2$var68 = var68;
																			}
																			if(((Math.log(1.0) + Math.log((v[j]?cv$temp$2$var68:(1.0 - cv$temp$2$var68)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$2$var68:(1.0 - cv$temp$2$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$2$var68:(1.0 - cv$temp$2$var68))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$2$var68:(1.0 - cv$temp$2$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$2$var68:(1.0 - cv$temp$2$var68)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
																if(!true) {
																	for(int index$sample11$26 = 0; index$sample11$26 < weightings.length; index$sample11$26 += 1) {
																		int distributionTempVariable$var11$28 = index$sample11$26;
																		double cv$probabilitySample11Value27 = (1.0 * distribution$sample11[index$sample11$26]);
																		int traceTempVariable$var67$29_1 = cv$currentValue;
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$3$var68;
																					{
																						double var68 = ((((1.0 * v1) + traceTempVariable$var59$4_1) + traceTempVariable$var63$15_3) / traceTempVariable$var67$29_1);
																						cv$temp$3$var68 = var68;
																					}
																					if(((Math.log(cv$probabilitySample11Value27) + Math.log((v[j]?cv$temp$3$var68:(1.0 - cv$temp$3$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value27) + Math.log((v[j]?cv$temp$3$var68:(1.0 - cv$temp$3$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value27) + Math.log((v[j]?cv$temp$3$var68:(1.0 - cv$temp$3$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value27) + Math.log((v[j]?cv$temp$3$var68:(1.0 - cv$temp$3$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value27) + Math.log((v[j]?cv$temp$3$var68:(1.0 - cv$temp$3$var68)))));
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
													if(!true) {
														for(int index$sample11$16 = 0; index$sample11$16 < weightings.length; index$sample11$16 += 1) {
															int distributionTempVariable$var11$18 = index$sample11$16;
															double cv$probabilitySample11Value17 = (1.0 * distribution$sample11[index$sample11$16]);
															int traceTempVariable$var44$19_1 = cv$currentValue;
															for(int k = 0; k < (size + 1); k += 1) {
																if((0 == k)) {
																	int traceTempVariable$var63$19_3 = traceTempVariable$var44$19_1;
																	if((k == (j + 1))) {
																		int traceTempVariable$var67$30_1 = cv$currentValue;
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$4$var68;
																					{
																						double var68 = ((((1.0 * v1) + traceTempVariable$var59$4_1) + traceTempVariable$var63$19_3) / traceTempVariable$var67$30_1);
																						cv$temp$4$var68 = var68;
																					}
																					if(((Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?cv$temp$4$var68:(1.0 - cv$temp$4$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?cv$temp$4$var68:(1.0 - cv$temp$4$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?cv$temp$4$var68:(1.0 - cv$temp$4$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?cv$temp$4$var68:(1.0 - cv$temp$4$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?cv$temp$4$var68:(1.0 - cv$temp$4$var68)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value17);
																				}
																			}
																		}
																		int traceTempVariable$var67$31_1 = cv$currentValue;
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$5$var68;
																					{
																						double var68 = ((((1.0 * v1) + traceTempVariable$var59$4_1) + traceTempVariable$var63$19_3) / traceTempVariable$var67$31_1);
																						cv$temp$5$var68 = var68;
																					}
																					if(((Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?cv$temp$5$var68:(1.0 - cv$temp$5$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?cv$temp$5$var68:(1.0 - cv$temp$5$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?cv$temp$5$var68:(1.0 - cv$temp$5$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?cv$temp$5$var68:(1.0 - cv$temp$5$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?cv$temp$5$var68:(1.0 - cv$temp$5$var68)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value17);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$32 = 0; index$sample11$32 < weightings.length; index$sample11$32 += 1) {
																				int distributionTempVariable$var11$34 = index$sample11$32;
																				double cv$probabilitySample11Value33 = (cv$probabilitySample11Value17 * distribution$sample11[index$sample11$32]);
																				int traceTempVariable$var67$35_1 = cv$currentValue;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$6$var68;
																							{
																								double var68 = ((((1.0 * v1) + traceTempVariable$var59$4_1) + traceTempVariable$var63$19_3) / traceTempVariable$var67$35_1);
																								cv$temp$6$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample11Value33) + Math.log((v[j]?cv$temp$6$var68:(1.0 - cv$temp$6$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value33) + Math.log((v[j]?cv$temp$6$var68:(1.0 - cv$temp$6$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value33) + Math.log((v[j]?cv$temp$6$var68:(1.0 - cv$temp$6$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value33) + Math.log((v[j]?cv$temp$6$var68:(1.0 - cv$temp$6$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value33) + Math.log((v[j]?cv$temp$6$var68:(1.0 - cv$temp$6$var68)))));
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
												} else {
													if(true) {
														for(int index$sample5$11 = 0; index$sample5$11 < weightings.length; index$sample5$11 += 1) {
															int distributionTempVariable$v1$13 = index$sample5$11;
															double cv$probabilitySample5Value12 = (1.0 * distribution$sample5[index$sample5$11]);
															int traceTempVariable$var44$20_1 = cv$currentValue;
															for(int k = 0; k < (size + 1); k += 1) {
																if((0 == k)) {
																	int traceTempVariable$var63$20_3 = traceTempVariable$var44$20_1;
																	if((k == (j + 1))) {
																		int traceTempVariable$var67$36_1 = cv$currentValue;
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$7$var68;
																					{
																						double var68 = ((((1.0 * distributionTempVariable$v1$13) + traceTempVariable$var59$4_1) + traceTempVariable$var63$20_3) / traceTempVariable$var67$36_1);
																						cv$temp$7$var68 = var68;
																					}
																					if(((Math.log(cv$probabilitySample5Value12) + Math.log((v[j]?cv$temp$7$var68:(1.0 - cv$temp$7$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value12) + Math.log((v[j]?cv$temp$7$var68:(1.0 - cv$temp$7$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value12) + Math.log((v[j]?cv$temp$7$var68:(1.0 - cv$temp$7$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value12) + Math.log((v[j]?cv$temp$7$var68:(1.0 - cv$temp$7$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value12) + Math.log((v[j]?cv$temp$7$var68:(1.0 - cv$temp$7$var68)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value12);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$37 = 0; index$sample11$37 < weightings.length; index$sample11$37 += 1) {
																				int distributionTempVariable$var11$39 = index$sample11$37;
																				double cv$probabilitySample11Value38 = (cv$probabilitySample5Value12 * distribution$sample11[index$sample11$37]);
																				int traceTempVariable$var67$40_1 = cv$currentValue;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$8$var68;
																							{
																								double var68 = ((((1.0 * distributionTempVariable$v1$13) + traceTempVariable$var59$4_1) + traceTempVariable$var63$20_3) / traceTempVariable$var67$40_1);
																								cv$temp$8$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample11Value38) + Math.log((v[j]?cv$temp$8$var68:(1.0 - cv$temp$8$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value38) + Math.log((v[j]?cv$temp$8$var68:(1.0 - cv$temp$8$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value38) + Math.log((v[j]?cv$temp$8$var68:(1.0 - cv$temp$8$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value38) + Math.log((v[j]?cv$temp$8$var68:(1.0 - cv$temp$8$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value38) + Math.log((v[j]?cv$temp$8$var68:(1.0 - cv$temp$8$var68)))));
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
															if(!true) {
																for(int index$sample11$21 = 0; index$sample11$21 < weightings.length; index$sample11$21 += 1) {
																	int distributionTempVariable$var11$23 = index$sample11$21;
																	double cv$probabilitySample11Value22 = (cv$probabilitySample5Value12 * distribution$sample11[index$sample11$21]);
																	int traceTempVariable$var44$24_1 = cv$currentValue;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if((0 == k)) {
																			int traceTempVariable$var63$24_3 = traceTempVariable$var44$24_1;
																			if((k == (j + 1))) {
																				int traceTempVariable$var67$41_1 = cv$currentValue;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$9$var68;
																							{
																								double var68 = ((((1.0 * distributionTempVariable$v1$13) + traceTempVariable$var59$4_1) + traceTempVariable$var63$24_3) / traceTempVariable$var67$41_1);
																								cv$temp$9$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample11Value22) + Math.log((v[j]?cv$temp$9$var68:(1.0 - cv$temp$9$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value22) + Math.log((v[j]?cv$temp$9$var68:(1.0 - cv$temp$9$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value22) + Math.log((v[j]?cv$temp$9$var68:(1.0 - cv$temp$9$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value22) + Math.log((v[j]?cv$temp$9$var68:(1.0 - cv$temp$9$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value22) + Math.log((v[j]?cv$temp$9$var68:(1.0 - cv$temp$9$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value22);
																						}
																					}
																				}
																				int traceTempVariable$var67$42_1 = cv$currentValue;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$10$var68;
																							{
																								double var68 = ((((1.0 * distributionTempVariable$v1$13) + traceTempVariable$var59$4_1) + traceTempVariable$var63$24_3) / traceTempVariable$var67$42_1);
																								cv$temp$10$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample11Value22) + Math.log((v[j]?cv$temp$10$var68:(1.0 - cv$temp$10$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value22) + Math.log((v[j]?cv$temp$10$var68:(1.0 - cv$temp$10$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value22) + Math.log((v[j]?cv$temp$10$var68:(1.0 - cv$temp$10$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value22) + Math.log((v[j]?cv$temp$10$var68:(1.0 - cv$temp$10$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value22) + Math.log((v[j]?cv$temp$10$var68:(1.0 - cv$temp$10$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value22);
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample11$43 = 0; index$sample11$43 < weightings.length; index$sample11$43 += 1) {
																						int distributionTempVariable$var11$45 = index$sample11$43;
																						double cv$probabilitySample11Value44 = (cv$probabilitySample11Value22 * distribution$sample11[index$sample11$43]);
																						int traceTempVariable$var67$46_1 = cv$currentValue;
																						if((0 == (j + 1))) {
																							{
																								{
																									double cv$temp$11$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$13) + traceTempVariable$var59$4_1) + traceTempVariable$var63$24_3) / traceTempVariable$var67$46_1);
																										cv$temp$11$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample11Value44) + Math.log((v[j]?cv$temp$11$var68:(1.0 - cv$temp$11$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value44) + Math.log((v[j]?cv$temp$11$var68:(1.0 - cv$temp$11$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value44) + Math.log((v[j]?cv$temp$11$var68:(1.0 - cv$temp$11$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value44) + Math.log((v[j]?cv$temp$11$var68:(1.0 - cv$temp$11$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value44) + Math.log((v[j]?cv$temp$11$var68:(1.0 - cv$temp$11$var68)))));
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
												if(fixedFlag$sample5) {
													if(fixedFlag$sample27) {
														for(int i = 0; i < size; i += 1) {
															for(int k = 0; k < (size + 1); k += 1) {
																if(((i + 1) == k)) {
																	int traceTempVariable$var63$52_3 = v2[k];
																	if((k == (j + 1))) {
																		int traceTempVariable$var67$64_1 = cv$currentValue;
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$12$var68;
																					{
																						double var68 = ((((1.0 * v1) + traceTempVariable$var59$4_1) + traceTempVariable$var63$52_3) / traceTempVariable$var67$64_1);
																						cv$temp$12$var68 = var68;
																					}
																					if(((Math.log(1.0) + Math.log((v[j]?cv$temp$12$var68:(1.0 - cv$temp$12$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$12$var68:(1.0 - cv$temp$12$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$12$var68:(1.0 - cv$temp$12$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$12$var68:(1.0 - cv$temp$12$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$12$var68:(1.0 - cv$temp$12$var68)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$65 = 0; index$sample11$65 < weightings.length; index$sample11$65 += 1) {
																				int distributionTempVariable$var11$67 = index$sample11$65;
																				double cv$probabilitySample11Value66 = (1.0 * distribution$sample11[index$sample11$65]);
																				int traceTempVariable$var67$68_1 = cv$currentValue;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$13$var68;
																							{
																								double var68 = ((((1.0 * v1) + traceTempVariable$var59$4_1) + traceTempVariable$var63$52_3) / traceTempVariable$var67$68_1);
																								cv$temp$13$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample11Value66) + Math.log((v[j]?cv$temp$13$var68:(1.0 - cv$temp$13$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value66) + Math.log((v[j]?cv$temp$13$var68:(1.0 - cv$temp$13$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value66) + Math.log((v[j]?cv$temp$13$var68:(1.0 - cv$temp$13$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value66) + Math.log((v[j]?cv$temp$13$var68:(1.0 - cv$temp$13$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value66) + Math.log((v[j]?cv$temp$13$var68:(1.0 - cv$temp$13$var68)))));
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
													} else {
														for(int i = 0; i < size; i += 1) {
															if(true) {
																for(int index$sample27$54 = 0; index$sample27$54 < weightings.length; index$sample27$54 += 1) {
																	int distributionTempVariable$var27$56 = index$sample27$54;
																	double cv$probabilitySample27Value55 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$54]);
																	for(int k = 0; k < (size + 1); k += 1) {
																		if(((i + 1) == k)) {
																			int traceTempVariable$var63$57_2 = v2[k];
																			if((k == (j + 1))) {
																				int traceTempVariable$var67$69_1 = cv$currentValue;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$14$var68;
																							{
																								double var68 = ((((1.0 * v1) + traceTempVariable$var59$4_1) + traceTempVariable$var63$57_2) / traceTempVariable$var67$69_1);
																								cv$temp$14$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample27Value55) + Math.log((v[j]?cv$temp$14$var68:(1.0 - cv$temp$14$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value55) + Math.log((v[j]?cv$temp$14$var68:(1.0 - cv$temp$14$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value55) + Math.log((v[j]?cv$temp$14$var68:(1.0 - cv$temp$14$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value55) + Math.log((v[j]?cv$temp$14$var68:(1.0 - cv$temp$14$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value55) + Math.log((v[j]?cv$temp$14$var68:(1.0 - cv$temp$14$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value55);
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample11$70 = 0; index$sample11$70 < weightings.length; index$sample11$70 += 1) {
																						int distributionTempVariable$var11$72 = index$sample11$70;
																						double cv$probabilitySample11Value71 = (cv$probabilitySample27Value55 * distribution$sample11[index$sample11$70]);
																						int traceTempVariable$var67$73_1 = cv$currentValue;
																						if((0 == (j + 1))) {
																							{
																								{
																									double cv$temp$15$var68;
																									{
																										double var68 = ((((1.0 * v1) + traceTempVariable$var59$4_1) + traceTempVariable$var63$57_2) / traceTempVariable$var67$73_1);
																										cv$temp$15$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample11Value71) + Math.log((v[j]?cv$temp$15$var68:(1.0 - cv$temp$15$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value71) + Math.log((v[j]?cv$temp$15$var68:(1.0 - cv$temp$15$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value71) + Math.log((v[j]?cv$temp$15$var68:(1.0 - cv$temp$15$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value71) + Math.log((v[j]?cv$temp$15$var68:(1.0 - cv$temp$15$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value71) + Math.log((v[j]?cv$temp$15$var68:(1.0 - cv$temp$15$var68)))));
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
												} else {
													if(true) {
														for(int index$sample5$48 = 0; index$sample5$48 < weightings.length; index$sample5$48 += 1) {
															int distributionTempVariable$v1$50 = index$sample5$48;
															double cv$probabilitySample5Value49 = (1.0 * distribution$sample5[index$sample5$48]);
															if(fixedFlag$sample27) {
																for(int i = 0; i < size; i += 1) {
																	for(int k = 0; k < (size + 1); k += 1) {
																		if(((i + 1) == k)) {
																			int traceTempVariable$var63$58_3 = v2[k];
																			if((k == (j + 1))) {
																				int traceTempVariable$var67$74_1 = cv$currentValue;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$16$var68;
																							{
																								double var68 = ((((1.0 * distributionTempVariable$v1$50) + traceTempVariable$var59$4_1) + traceTempVariable$var63$58_3) / traceTempVariable$var67$74_1);
																								cv$temp$16$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample5Value49) + Math.log((v[j]?cv$temp$16$var68:(1.0 - cv$temp$16$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value49) + Math.log((v[j]?cv$temp$16$var68:(1.0 - cv$temp$16$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value49) + Math.log((v[j]?cv$temp$16$var68:(1.0 - cv$temp$16$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value49) + Math.log((v[j]?cv$temp$16$var68:(1.0 - cv$temp$16$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value49) + Math.log((v[j]?cv$temp$16$var68:(1.0 - cv$temp$16$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value49);
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample11$75 = 0; index$sample11$75 < weightings.length; index$sample11$75 += 1) {
																						int distributionTempVariable$var11$77 = index$sample11$75;
																						double cv$probabilitySample11Value76 = (cv$probabilitySample5Value49 * distribution$sample11[index$sample11$75]);
																						int traceTempVariable$var67$78_1 = cv$currentValue;
																						if((0 == (j + 1))) {
																							{
																								{
																									double cv$temp$17$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$50) + traceTempVariable$var59$4_1) + traceTempVariable$var63$58_3) / traceTempVariable$var67$78_1);
																										cv$temp$17$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample11Value76) + Math.log((v[j]?cv$temp$17$var68:(1.0 - cv$temp$17$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value76) + Math.log((v[j]?cv$temp$17$var68:(1.0 - cv$temp$17$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value76) + Math.log((v[j]?cv$temp$17$var68:(1.0 - cv$temp$17$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value76) + Math.log((v[j]?cv$temp$17$var68:(1.0 - cv$temp$17$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value76) + Math.log((v[j]?cv$temp$17$var68:(1.0 - cv$temp$17$var68)))));
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
															} else {
																for(int i = 0; i < size; i += 1) {
																	if(true) {
																		for(int index$sample27$60 = 0; index$sample27$60 < weightings.length; index$sample27$60 += 1) {
																			int distributionTempVariable$var27$62 = index$sample27$60;
																			double cv$probabilitySample27Value61 = (cv$probabilitySample5Value49 * distribution$sample27[((i - 0) / 1)][index$sample27$60]);
																			for(int k = 0; k < (size + 1); k += 1) {
																				if(((i + 1) == k)) {
																					int traceTempVariable$var63$63_2 = v2[k];
																					if((k == (j + 1))) {
																						int traceTempVariable$var67$79_1 = cv$currentValue;
																						if((0 == (j + 1))) {
																							{
																								{
																									double cv$temp$18$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$50) + traceTempVariable$var59$4_1) + traceTempVariable$var63$63_2) / traceTempVariable$var67$79_1);
																										cv$temp$18$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value61) + Math.log((v[j]?cv$temp$18$var68:(1.0 - cv$temp$18$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value61) + Math.log((v[j]?cv$temp$18$var68:(1.0 - cv$temp$18$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value61) + Math.log((v[j]?cv$temp$18$var68:(1.0 - cv$temp$18$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value61) + Math.log((v[j]?cv$temp$18$var68:(1.0 - cv$temp$18$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value61) + Math.log((v[j]?cv$temp$18$var68:(1.0 - cv$temp$18$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value61);
																								}
																							}
																						}
																						if(!true) {
																							for(int index$sample11$80 = 0; index$sample11$80 < weightings.length; index$sample11$80 += 1) {
																								int distributionTempVariable$var11$82 = index$sample11$80;
																								double cv$probabilitySample11Value81 = (cv$probabilitySample27Value61 * distribution$sample11[index$sample11$80]);
																								int traceTempVariable$var67$83_1 = cv$currentValue;
																								if((0 == (j + 1))) {
																									{
																										{
																											double cv$temp$19$var68;
																											{
																												double var68 = ((((1.0 * distributionTempVariable$v1$50) + traceTempVariable$var59$4_1) + traceTempVariable$var63$63_2) / traceTempVariable$var67$83_1);
																												cv$temp$19$var68 = var68;
																											}
																											if(((Math.log(cv$probabilitySample11Value81) + Math.log((v[j]?cv$temp$19$var68:(1.0 - cv$temp$19$var68)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value81) + Math.log((v[j]?cv$temp$19$var68:(1.0 - cv$temp$19$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value81) + Math.log((v[j]?cv$temp$19$var68:(1.0 - cv$temp$19$var68))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value81) + Math.log((v[j]?cv$temp$19$var68:(1.0 - cv$temp$19$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value81) + Math.log((v[j]?cv$temp$19$var68:(1.0 - cv$temp$19$var68)))));
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
												if(fixedFlag$sample5) {
													int traceTempVariable$var44$89_1 = cv$currentValue;
													for(int k = 0; k < (size + 1); k += 1) {
														if((0 == k)) {
															int traceTempVariable$var63$89_3 = traceTempVariable$var44$89_1;
															if((k == (j + 1))) {
																if(fixedFlag$sample27) {
																	for(int i = 0; i < size; i += 1) {
																		if(((i + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$20$var68;
																					{
																						double var68 = ((((1.0 * v1) + traceTempVariable$var59$4_1) + traceTempVariable$var63$89_3) / v2[(j + 1)]);
																						cv$temp$20$var68 = var68;
																					}
																					if(((Math.log(1.0) + Math.log((v[j]?cv$temp$20$var68:(1.0 - cv$temp$20$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$20$var68:(1.0 - cv$temp$20$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$20$var68:(1.0 - cv$temp$20$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$20$var68:(1.0 - cv$temp$20$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$20$var68:(1.0 - cv$temp$20$var68)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																				if(((i + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$21$var68;
																							{
																								double var68 = ((((1.0 * v1) + traceTempVariable$var59$4_1) + traceTempVariable$var63$89_3) / v2[(j + 1)]);
																								cv$temp$21$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample27Value102) + Math.log((v[j]?cv$temp$21$var68:(1.0 - cv$temp$21$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value102) + Math.log((v[j]?cv$temp$21$var68:(1.0 - cv$temp$21$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value102) + Math.log((v[j]?cv$temp$21$var68:(1.0 - cv$temp$21$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value102) + Math.log((v[j]?cv$temp$21$var68:(1.0 - cv$temp$21$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value102) + Math.log((v[j]?cv$temp$21$var68:(1.0 - cv$temp$21$var68)))));
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
													if(!true) {
														for(int index$sample11$90 = 0; index$sample11$90 < weightings.length; index$sample11$90 += 1) {
															int distributionTempVariable$var11$92 = index$sample11$90;
															double cv$probabilitySample11Value91 = (1.0 * distribution$sample11[index$sample11$90]);
															int traceTempVariable$var44$93_1 = cv$currentValue;
															for(int k = 0; k < (size + 1); k += 1) {
																if((0 == k)) {
																	int traceTempVariable$var63$93_3 = traceTempVariable$var44$93_1;
																	if((k == (j + 1))) {
																		if(fixedFlag$sample27) {
																			for(int i = 0; i < size; i += 1) {
																				if(((i + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$22$var68;
																							{
																								double var68 = ((((1.0 * v1) + traceTempVariable$var59$4_1) + traceTempVariable$var63$93_3) / v2[(j + 1)]);
																								cv$temp$22$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample11Value91) + Math.log((v[j]?cv$temp$22$var68:(1.0 - cv$temp$22$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value91) + Math.log((v[j]?cv$temp$22$var68:(1.0 - cv$temp$22$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value91) + Math.log((v[j]?cv$temp$22$var68:(1.0 - cv$temp$22$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value91) + Math.log((v[j]?cv$temp$22$var68:(1.0 - cv$temp$22$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value91) + Math.log((v[j]?cv$temp$22$var68:(1.0 - cv$temp$22$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value91);
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
																						if(((i + 1) == (j + 1))) {
																							{
																								{
																									double cv$temp$23$var68;
																									{
																										double var68 = ((((1.0 * v1) + traceTempVariable$var59$4_1) + traceTempVariable$var63$93_3) / v2[(j + 1)]);
																										cv$temp$23$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value108) + Math.log((v[j]?cv$temp$23$var68:(1.0 - cv$temp$23$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value108) + Math.log((v[j]?cv$temp$23$var68:(1.0 - cv$temp$23$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value108) + Math.log((v[j]?cv$temp$23$var68:(1.0 - cv$temp$23$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value108) + Math.log((v[j]?cv$temp$23$var68:(1.0 - cv$temp$23$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value108) + Math.log((v[j]?cv$temp$23$var68:(1.0 - cv$temp$23$var68)))));
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
												} else {
													if(true) {
														for(int index$sample5$85 = 0; index$sample5$85 < weightings.length; index$sample5$85 += 1) {
															int distributionTempVariable$v1$87 = index$sample5$85;
															double cv$probabilitySample5Value86 = (1.0 * distribution$sample5[index$sample5$85]);
															int traceTempVariable$var44$94_1 = cv$currentValue;
															for(int k = 0; k < (size + 1); k += 1) {
																if((0 == k)) {
																	int traceTempVariable$var63$94_3 = traceTempVariable$var44$94_1;
																	if((k == (j + 1))) {
																		if(fixedFlag$sample27) {
																			for(int i = 0; i < size; i += 1) {
																				if(((i + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$24$var68;
																							{
																								double var68 = ((((1.0 * distributionTempVariable$v1$87) + traceTempVariable$var59$4_1) + traceTempVariable$var63$94_3) / v2[(j + 1)]);
																								cv$temp$24$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample5Value86) + Math.log((v[j]?cv$temp$24$var68:(1.0 - cv$temp$24$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value86) + Math.log((v[j]?cv$temp$24$var68:(1.0 - cv$temp$24$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value86) + Math.log((v[j]?cv$temp$24$var68:(1.0 - cv$temp$24$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value86) + Math.log((v[j]?cv$temp$24$var68:(1.0 - cv$temp$24$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value86) + Math.log((v[j]?cv$temp$24$var68:(1.0 - cv$temp$24$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value86);
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
																						if(((i + 1) == (j + 1))) {
																							{
																								{
																									double cv$temp$25$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$87) + traceTempVariable$var59$4_1) + traceTempVariable$var63$94_3) / v2[(j + 1)]);
																										cv$temp$25$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value114) + Math.log((v[j]?cv$temp$25$var68:(1.0 - cv$temp$25$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value114) + Math.log((v[j]?cv$temp$25$var68:(1.0 - cv$temp$25$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value114) + Math.log((v[j]?cv$temp$25$var68:(1.0 - cv$temp$25$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value114) + Math.log((v[j]?cv$temp$25$var68:(1.0 - cv$temp$25$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value114) + Math.log((v[j]?cv$temp$25$var68:(1.0 - cv$temp$25$var68)))));
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
															if(!true) {
																for(int index$sample11$95 = 0; index$sample11$95 < weightings.length; index$sample11$95 += 1) {
																	int distributionTempVariable$var11$97 = index$sample11$95;
																	double cv$probabilitySample11Value96 = (cv$probabilitySample5Value86 * distribution$sample11[index$sample11$95]);
																	int traceTempVariable$var44$98_1 = cv$currentValue;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if((0 == k)) {
																			int traceTempVariable$var63$98_3 = traceTempVariable$var44$98_1;
																			if((k == (j + 1))) {
																				if(fixedFlag$sample27) {
																					for(int i = 0; i < size; i += 1) {
																						if(((i + 1) == (j + 1))) {
																							{
																								{
																									double cv$temp$26$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$87) + traceTempVariable$var59$4_1) + traceTempVariable$var63$98_3) / v2[(j + 1)]);
																										cv$temp$26$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample11Value96) + Math.log((v[j]?cv$temp$26$var68:(1.0 - cv$temp$26$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value96) + Math.log((v[j]?cv$temp$26$var68:(1.0 - cv$temp$26$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value96) + Math.log((v[j]?cv$temp$26$var68:(1.0 - cv$temp$26$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value96) + Math.log((v[j]?cv$temp$26$var68:(1.0 - cv$temp$26$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value96) + Math.log((v[j]?cv$temp$26$var68:(1.0 - cv$temp$26$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value96);
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
																								if(((i + 1) == (j + 1))) {
																									{
																										{
																											double cv$temp$27$var68;
																											{
																												double var68 = ((((1.0 * distributionTempVariable$v1$87) + traceTempVariable$var59$4_1) + traceTempVariable$var63$98_3) / v2[(j + 1)]);
																												cv$temp$27$var68 = var68;
																											}
																											if(((Math.log(cv$probabilitySample27Value120) + Math.log((v[j]?cv$temp$27$var68:(1.0 - cv$temp$27$var68)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value120) + Math.log((v[j]?cv$temp$27$var68:(1.0 - cv$temp$27$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value120) + Math.log((v[j]?cv$temp$27$var68:(1.0 - cv$temp$27$var68))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value120) + Math.log((v[j]?cv$temp$27$var68:(1.0 - cv$temp$27$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value120) + Math.log((v[j]?cv$temp$27$var68:(1.0 - cv$temp$27$var68)))));
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
												if(fixedFlag$sample5) {
													if(fixedFlag$sample27) {
														for(int i = 0; i < size; i += 1) {
															for(int k = 0; k < (size + 1); k += 1) {
																if(((i + 1) == k)) {
																	int traceTempVariable$var63$128_3 = v2[k];
																	if((k == (j + 1))) {
																		for(int index$i$140_1 = 0; index$i$140_1 < size; index$i$140_1 += 1) {
																			if(((index$i$140_1 + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$28$var68;
																						{
																							double var68 = ((((1.0 * v1) + traceTempVariable$var59$4_1) + traceTempVariable$var63$128_3) / v2[(j + 1)]);
																							cv$temp$28$var68 = var68;
																						}
																						if(((Math.log(1.0) + Math.log((v[j]?cv$temp$28$var68:(1.0 - cv$temp$28$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$28$var68:(1.0 - cv$temp$28$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$28$var68:(1.0 - cv$temp$28$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$28$var68:(1.0 - cv$temp$28$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$28$var68:(1.0 - cv$temp$28$var68)))));
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
																for(int index$sample27$130 = 0; index$sample27$130 < weightings.length; index$sample27$130 += 1) {
																	int distributionTempVariable$var27$132 = index$sample27$130;
																	double cv$probabilitySample27Value131 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$130]);
																	for(int k = 0; k < (size + 1); k += 1) {
																		if(((i + 1) == k)) {
																			int traceTempVariable$var63$133_2 = v2[k];
																			if((k == (j + 1))) {
																				if(((i + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$29$var68;
																							{
																								double var68 = ((((1.0 * v1) + traceTempVariable$var59$4_1) + traceTempVariable$var63$133_2) / v2[(j + 1)]);
																								cv$temp$29$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample27Value131) + Math.log((v[j]?cv$temp$29$var68:(1.0 - cv$temp$29$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value131) + Math.log((v[j]?cv$temp$29$var68:(1.0 - cv$temp$29$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value131) + Math.log((v[j]?cv$temp$29$var68:(1.0 - cv$temp$29$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value131) + Math.log((v[j]?cv$temp$29$var68:(1.0 - cv$temp$29$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value131) + Math.log((v[j]?cv$temp$29$var68:(1.0 - cv$temp$29$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value131);
																						}
																					}
																				}
																				for(int index$i$142 = 0; index$i$142 < size; index$i$142 += 1) {
																					if(!(index$i$142 == i)) {
																						for(int index$sample27$143 = 0; index$sample27$143 < weightings.length; index$sample27$143 += 1) {
																							int distributionTempVariable$var27$145 = index$sample27$143;
																							double cv$probabilitySample27Value144 = (cv$probabilitySample27Value131 * distribution$sample27[((index$i$142 - 0) / 1)][index$sample27$143]);
																							if(((index$i$142 + 1) == (j + 1))) {
																								{
																									{
																										double cv$temp$30$var68;
																										{
																											double var68 = ((((1.0 * v1) + traceTempVariable$var59$4_1) + traceTempVariable$var63$133_2) / v2[(j + 1)]);
																											cv$temp$30$var68 = var68;
																										}
																										if(((Math.log(cv$probabilitySample27Value144) + Math.log((v[j]?cv$temp$30$var68:(1.0 - cv$temp$30$var68)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value144) + Math.log((v[j]?cv$temp$30$var68:(1.0 - cv$temp$30$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value144) + Math.log((v[j]?cv$temp$30$var68:(1.0 - cv$temp$30$var68))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value144) + Math.log((v[j]?cv$temp$30$var68:(1.0 - cv$temp$30$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value144) + Math.log((v[j]?cv$temp$30$var68:(1.0 - cv$temp$30$var68)))));
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
												} else {
													if(true) {
														for(int index$sample5$124 = 0; index$sample5$124 < weightings.length; index$sample5$124 += 1) {
															int distributionTempVariable$v1$126 = index$sample5$124;
															double cv$probabilitySample5Value125 = (1.0 * distribution$sample5[index$sample5$124]);
															if(fixedFlag$sample27) {
																for(int i = 0; i < size; i += 1) {
																	for(int k = 0; k < (size + 1); k += 1) {
																		if(((i + 1) == k)) {
																			int traceTempVariable$var63$134_3 = v2[k];
																			if((k == (j + 1))) {
																				for(int index$i$147_1 = 0; index$i$147_1 < size; index$i$147_1 += 1) {
																					if(((index$i$147_1 + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$31$var68;
																								{
																									double var68 = ((((1.0 * distributionTempVariable$v1$126) + traceTempVariable$var59$4_1) + traceTempVariable$var63$134_3) / v2[(j + 1)]);
																									cv$temp$31$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample5Value125) + Math.log((v[j]?cv$temp$31$var68:(1.0 - cv$temp$31$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value125) + Math.log((v[j]?cv$temp$31$var68:(1.0 - cv$temp$31$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value125) + Math.log((v[j]?cv$temp$31$var68:(1.0 - cv$temp$31$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value125) + Math.log((v[j]?cv$temp$31$var68:(1.0 - cv$temp$31$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value125) + Math.log((v[j]?cv$temp$31$var68:(1.0 - cv$temp$31$var68)))));
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
															} else {
																for(int i = 0; i < size; i += 1) {
																	if(true) {
																		for(int index$sample27$136 = 0; index$sample27$136 < weightings.length; index$sample27$136 += 1) {
																			int distributionTempVariable$var27$138 = index$sample27$136;
																			double cv$probabilitySample27Value137 = (cv$probabilitySample5Value125 * distribution$sample27[((i - 0) / 1)][index$sample27$136]);
																			for(int k = 0; k < (size + 1); k += 1) {
																				if(((i + 1) == k)) {
																					int traceTempVariable$var63$139_2 = v2[k];
																					if((k == (j + 1))) {
																						if(((i + 1) == (j + 1))) {
																							{
																								{
																									double cv$temp$32$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$126) + traceTempVariable$var59$4_1) + traceTempVariable$var63$139_2) / v2[(j + 1)]);
																										cv$temp$32$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value137) + Math.log((v[j]?cv$temp$32$var68:(1.0 - cv$temp$32$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value137) + Math.log((v[j]?cv$temp$32$var68:(1.0 - cv$temp$32$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value137) + Math.log((v[j]?cv$temp$32$var68:(1.0 - cv$temp$32$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value137) + Math.log((v[j]?cv$temp$32$var68:(1.0 - cv$temp$32$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value137) + Math.log((v[j]?cv$temp$32$var68:(1.0 - cv$temp$32$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value137);
																								}
																							}
																						}
																						for(int index$i$149 = 0; index$i$149 < size; index$i$149 += 1) {
																							if(!(index$i$149 == i)) {
																								for(int index$sample27$150 = 0; index$sample27$150 < weightings.length; index$sample27$150 += 1) {
																									int distributionTempVariable$var27$152 = index$sample27$150;
																									double cv$probabilitySample27Value151 = (cv$probabilitySample27Value137 * distribution$sample27[((index$i$149 - 0) / 1)][index$sample27$150]);
																									if(((index$i$149 + 1) == (j + 1))) {
																										{
																											{
																												double cv$temp$33$var68;
																												{
																													double var68 = ((((1.0 * distributionTempVariable$v1$126) + traceTempVariable$var59$4_1) + traceTempVariable$var63$139_2) / v2[(j + 1)]);
																													cv$temp$33$var68 = var68;
																												}
																												if(((Math.log(cv$probabilitySample27Value151) + Math.log((v[j]?cv$temp$33$var68:(1.0 - cv$temp$33$var68)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value151) + Math.log((v[j]?cv$temp$33$var68:(1.0 - cv$temp$33$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value151) + Math.log((v[j]?cv$temp$33$var68:(1.0 - cv$temp$33$var68))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value151) + Math.log((v[j]?cv$temp$33$var68:(1.0 - cv$temp$33$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value151) + Math.log((v[j]?cv$temp$33$var68:(1.0 - cv$temp$33$var68)))));
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
							int traceTempVariable$var67$5_1 = cv$currentValue;
							for(int j = 0; j < size; j += 1) {
								if((0 == (j + 1))) {
									if(!guard$sample11bernoulli69[((j - 0) / 1)]) {
										guard$sample11bernoulli69[((j - 0) / 1)] = true;
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if(fixedFlag$sample5) {
													int traceTempVariable$var59$159_1 = cv$currentValue;
													if((0 == j)) {
														int traceTempVariable$var44$169_1 = cv$currentValue;
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var63$169_3 = traceTempVariable$var44$169_1;
																if((k == (j + 1))) {
																	{
																		{
																			double cv$temp$34$var68;
																			{
																				double var68 = ((((1.0 * v1) + traceTempVariable$var59$159_1) + traceTempVariable$var63$169_3) / traceTempVariable$var67$5_1);
																				cv$temp$34$var68 = var68;
																			}
																			if(((Math.log(1.0) + Math.log((v[j]?cv$temp$34$var68:(1.0 - cv$temp$34$var68)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$34$var68:(1.0 - cv$temp$34$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$34$var68:(1.0 - cv$temp$34$var68))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$34$var68:(1.0 - cv$temp$34$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$34$var68:(1.0 - cv$temp$34$var68)))));
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
																int traceTempVariable$var44$173_1 = cv$currentValue;
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var63$173_3 = traceTempVariable$var44$173_1;
																		if((k == (j + 1))) {
																			{
																				{
																					double cv$temp$35$var68;
																					{
																						double var68 = ((((1.0 * v1) + traceTempVariable$var59$159_1) + traceTempVariable$var63$173_3) / traceTempVariable$var67$5_1);
																						cv$temp$35$var68 = var68;
																					}
																					if(((Math.log(cv$probabilitySample11Value171) + Math.log((v[j]?cv$temp$35$var68:(1.0 - cv$temp$35$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value171) + Math.log((v[j]?cv$temp$35$var68:(1.0 - cv$temp$35$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value171) + Math.log((v[j]?cv$temp$35$var68:(1.0 - cv$temp$35$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value171) + Math.log((v[j]?cv$temp$35$var68:(1.0 - cv$temp$35$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value171) + Math.log((v[j]?cv$temp$35$var68:(1.0 - cv$temp$35$var68)))));
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
													if(!true) {
														for(int index$sample11$160 = 0; index$sample11$160 < weightings.length; index$sample11$160 += 1) {
															int distributionTempVariable$var11$162 = index$sample11$160;
															double cv$probabilitySample11Value161 = (1.0 * distribution$sample11[index$sample11$160]);
															int traceTempVariable$var59$163_1 = cv$currentValue;
															if((0 == j)) {
																int traceTempVariable$var44$174_1 = cv$currentValue;
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var63$174_3 = traceTempVariable$var44$174_1;
																		if((k == (j + 1))) {
																			{
																				{
																					double cv$temp$36$var68;
																					{
																						double var68 = ((((1.0 * v1) + traceTempVariable$var59$163_1) + traceTempVariable$var63$174_3) / traceTempVariable$var67$5_1);
																						cv$temp$36$var68 = var68;
																					}
																					if(((Math.log(cv$probabilitySample11Value161) + Math.log((v[j]?cv$temp$36$var68:(1.0 - cv$temp$36$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value161) + Math.log((v[j]?cv$temp$36$var68:(1.0 - cv$temp$36$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value161) + Math.log((v[j]?cv$temp$36$var68:(1.0 - cv$temp$36$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value161) + Math.log((v[j]?cv$temp$36$var68:(1.0 - cv$temp$36$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value161) + Math.log((v[j]?cv$temp$36$var68:(1.0 - cv$temp$36$var68)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value161);
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$var44$175_1 = cv$currentValue;
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var63$175_3 = traceTempVariable$var44$175_1;
																		if((k == (j + 1))) {
																			{
																				{
																					double cv$temp$37$var68;
																					{
																						double var68 = ((((1.0 * v1) + traceTempVariable$var59$163_1) + traceTempVariable$var63$175_3) / traceTempVariable$var67$5_1);
																						cv$temp$37$var68 = var68;
																					}
																					if(((Math.log(cv$probabilitySample11Value161) + Math.log((v[j]?cv$temp$37$var68:(1.0 - cv$temp$37$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value161) + Math.log((v[j]?cv$temp$37$var68:(1.0 - cv$temp$37$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value161) + Math.log((v[j]?cv$temp$37$var68:(1.0 - cv$temp$37$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value161) + Math.log((v[j]?cv$temp$37$var68:(1.0 - cv$temp$37$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value161) + Math.log((v[j]?cv$temp$37$var68:(1.0 - cv$temp$37$var68)))));
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
																		int traceTempVariable$var44$179_1 = cv$currentValue;
																		for(int k = 0; k < (size + 1); k += 1) {
																			if((0 == k)) {
																				int traceTempVariable$var63$179_3 = traceTempVariable$var44$179_1;
																				if((k == (j + 1))) {
																					{
																						{
																							double cv$temp$38$var68;
																							{
																								double var68 = ((((1.0 * v1) + traceTempVariable$var59$163_1) + traceTempVariable$var63$179_3) / traceTempVariable$var67$5_1);
																								cv$temp$38$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample11Value177) + Math.log((v[j]?cv$temp$38$var68:(1.0 - cv$temp$38$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value177) + Math.log((v[j]?cv$temp$38$var68:(1.0 - cv$temp$38$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value177) + Math.log((v[j]?cv$temp$38$var68:(1.0 - cv$temp$38$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value177) + Math.log((v[j]?cv$temp$38$var68:(1.0 - cv$temp$38$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value177) + Math.log((v[j]?cv$temp$38$var68:(1.0 - cv$temp$38$var68)))));
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
												} else {
													if(true) {
														for(int index$sample5$155 = 0; index$sample5$155 < weightings.length; index$sample5$155 += 1) {
															int distributionTempVariable$v1$157 = index$sample5$155;
															double cv$probabilitySample5Value156 = (1.0 * distribution$sample5[index$sample5$155]);
															int traceTempVariable$var59$164_1 = cv$currentValue;
															if((0 == j)) {
																int traceTempVariable$var44$180_1 = cv$currentValue;
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var63$180_3 = traceTempVariable$var44$180_1;
																		if((k == (j + 1))) {
																			{
																				{
																					double cv$temp$39$var68;
																					{
																						double var68 = ((((1.0 * distributionTempVariable$v1$157) + traceTempVariable$var59$164_1) + traceTempVariable$var63$180_3) / traceTempVariable$var67$5_1);
																						cv$temp$39$var68 = var68;
																					}
																					if(((Math.log(cv$probabilitySample5Value156) + Math.log((v[j]?cv$temp$39$var68:(1.0 - cv$temp$39$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value156) + Math.log((v[j]?cv$temp$39$var68:(1.0 - cv$temp$39$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value156) + Math.log((v[j]?cv$temp$39$var68:(1.0 - cv$temp$39$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value156) + Math.log((v[j]?cv$temp$39$var68:(1.0 - cv$temp$39$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value156) + Math.log((v[j]?cv$temp$39$var68:(1.0 - cv$temp$39$var68)))));
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
																		int traceTempVariable$var44$184_1 = cv$currentValue;
																		for(int k = 0; k < (size + 1); k += 1) {
																			if((0 == k)) {
																				int traceTempVariable$var63$184_3 = traceTempVariable$var44$184_1;
																				if((k == (j + 1))) {
																					{
																						{
																							double cv$temp$40$var68;
																							{
																								double var68 = ((((1.0 * distributionTempVariable$v1$157) + traceTempVariable$var59$164_1) + traceTempVariable$var63$184_3) / traceTempVariable$var67$5_1);
																								cv$temp$40$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample11Value182) + Math.log((v[j]?cv$temp$40$var68:(1.0 - cv$temp$40$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value182) + Math.log((v[j]?cv$temp$40$var68:(1.0 - cv$temp$40$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value182) + Math.log((v[j]?cv$temp$40$var68:(1.0 - cv$temp$40$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value182) + Math.log((v[j]?cv$temp$40$var68:(1.0 - cv$temp$40$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value182) + Math.log((v[j]?cv$temp$40$var68:(1.0 - cv$temp$40$var68)))));
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
															if(!true) {
																for(int index$sample11$165 = 0; index$sample11$165 < weightings.length; index$sample11$165 += 1) {
																	int distributionTempVariable$var11$167 = index$sample11$165;
																	double cv$probabilitySample11Value166 = (cv$probabilitySample5Value156 * distribution$sample11[index$sample11$165]);
																	int traceTempVariable$var59$168_1 = cv$currentValue;
																	if((0 == j)) {
																		int traceTempVariable$var44$185_1 = cv$currentValue;
																		for(int k = 0; k < (size + 1); k += 1) {
																			if((0 == k)) {
																				int traceTempVariable$var63$185_3 = traceTempVariable$var44$185_1;
																				if((k == (j + 1))) {
																					{
																						{
																							double cv$temp$41$var68;
																							{
																								double var68 = ((((1.0 * distributionTempVariable$v1$157) + traceTempVariable$var59$168_1) + traceTempVariable$var63$185_3) / traceTempVariable$var67$5_1);
																								cv$temp$41$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample11Value166) + Math.log((v[j]?cv$temp$41$var68:(1.0 - cv$temp$41$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value166) + Math.log((v[j]?cv$temp$41$var68:(1.0 - cv$temp$41$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value166) + Math.log((v[j]?cv$temp$41$var68:(1.0 - cv$temp$41$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value166) + Math.log((v[j]?cv$temp$41$var68:(1.0 - cv$temp$41$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value166) + Math.log((v[j]?cv$temp$41$var68:(1.0 - cv$temp$41$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value166);
																						}
																					}
																				}
																			}
																		}
																		int traceTempVariable$var44$186_1 = cv$currentValue;
																		for(int k = 0; k < (size + 1); k += 1) {
																			if((0 == k)) {
																				int traceTempVariable$var63$186_3 = traceTempVariable$var44$186_1;
																				if((k == (j + 1))) {
																					{
																						{
																							double cv$temp$42$var68;
																							{
																								double var68 = ((((1.0 * distributionTempVariable$v1$157) + traceTempVariable$var59$168_1) + traceTempVariable$var63$186_3) / traceTempVariable$var67$5_1);
																								cv$temp$42$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample11Value166) + Math.log((v[j]?cv$temp$42$var68:(1.0 - cv$temp$42$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value166) + Math.log((v[j]?cv$temp$42$var68:(1.0 - cv$temp$42$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value166) + Math.log((v[j]?cv$temp$42$var68:(1.0 - cv$temp$42$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value166) + Math.log((v[j]?cv$temp$42$var68:(1.0 - cv$temp$42$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value166) + Math.log((v[j]?cv$temp$42$var68:(1.0 - cv$temp$42$var68)))));
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
																				int traceTempVariable$var44$190_1 = cv$currentValue;
																				for(int k = 0; k < (size + 1); k += 1) {
																					if((0 == k)) {
																						int traceTempVariable$var63$190_3 = traceTempVariable$var44$190_1;
																						if((k == (j + 1))) {
																							{
																								{
																									double cv$temp$43$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$157) + traceTempVariable$var59$168_1) + traceTempVariable$var63$190_3) / traceTempVariable$var67$5_1);
																										cv$temp$43$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample11Value188) + Math.log((v[j]?cv$temp$43$var68:(1.0 - cv$temp$43$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value188) + Math.log((v[j]?cv$temp$43$var68:(1.0 - cv$temp$43$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value188) + Math.log((v[j]?cv$temp$43$var68:(1.0 - cv$temp$43$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value188) + Math.log((v[j]?cv$temp$43$var68:(1.0 - cv$temp$43$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value188) + Math.log((v[j]?cv$temp$43$var68:(1.0 - cv$temp$43$var68)))));
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
												if(fixedFlag$sample5) {
													int traceTempVariable$var59$196_1 = cv$currentValue;
													if((0 == j)) {
														if(fixedFlag$sample27) {
															for(int i = 0; i < size; i += 1) {
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((i + 1) == k)) {
																		int traceTempVariable$var63$206_3 = v2[k];
																		if((k == (j + 1))) {
																			{
																				{
																					double cv$temp$44$var68;
																					{
																						double var68 = ((((1.0 * v1) + traceTempVariable$var59$196_1) + traceTempVariable$var63$206_3) / traceTempVariable$var67$5_1);
																						cv$temp$44$var68 = var68;
																					}
																					if(((Math.log(1.0) + Math.log((v[j]?cv$temp$44$var68:(1.0 - cv$temp$44$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$44$var68:(1.0 - cv$temp$44$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$44$var68:(1.0 - cv$temp$44$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$44$var68:(1.0 - cv$temp$44$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$44$var68:(1.0 - cv$temp$44$var68)))));
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
																	for(int index$sample27$208 = 0; index$sample27$208 < weightings.length; index$sample27$208 += 1) {
																		int distributionTempVariable$var27$210 = index$sample27$208;
																		double cv$probabilitySample27Value209 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$208]);
																		for(int k = 0; k < (size + 1); k += 1) {
																			if(((i + 1) == k)) {
																				int traceTempVariable$var63$211_2 = v2[k];
																				if((k == (j + 1))) {
																					{
																						{
																							double cv$temp$45$var68;
																							{
																								double var68 = ((((1.0 * v1) + traceTempVariable$var59$196_1) + traceTempVariable$var63$211_2) / traceTempVariable$var67$5_1);
																								cv$temp$45$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample27Value209) + Math.log((v[j]?cv$temp$45$var68:(1.0 - cv$temp$45$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value209) + Math.log((v[j]?cv$temp$45$var68:(1.0 - cv$temp$45$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value209) + Math.log((v[j]?cv$temp$45$var68:(1.0 - cv$temp$45$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value209) + Math.log((v[j]?cv$temp$45$var68:(1.0 - cv$temp$45$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value209) + Math.log((v[j]?cv$temp$45$var68:(1.0 - cv$temp$45$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value209);
																						}
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
														for(int index$sample11$197 = 0; index$sample11$197 < weightings.length; index$sample11$197 += 1) {
															int distributionTempVariable$var11$199 = index$sample11$197;
															double cv$probabilitySample11Value198 = (1.0 * distribution$sample11[index$sample11$197]);
															int traceTempVariable$var59$200_1 = cv$currentValue;
															if((0 == j)) {
																if(fixedFlag$sample27) {
																	for(int i = 0; i < size; i += 1) {
																		for(int k = 0; k < (size + 1); k += 1) {
																			if(((i + 1) == k)) {
																				int traceTempVariable$var63$212_3 = v2[k];
																				if((k == (j + 1))) {
																					{
																						{
																							double cv$temp$46$var68;
																							{
																								double var68 = ((((1.0 * v1) + traceTempVariable$var59$200_1) + traceTempVariable$var63$212_3) / traceTempVariable$var67$5_1);
																								cv$temp$46$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample11Value198) + Math.log((v[j]?cv$temp$46$var68:(1.0 - cv$temp$46$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value198) + Math.log((v[j]?cv$temp$46$var68:(1.0 - cv$temp$46$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value198) + Math.log((v[j]?cv$temp$46$var68:(1.0 - cv$temp$46$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value198) + Math.log((v[j]?cv$temp$46$var68:(1.0 - cv$temp$46$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value198) + Math.log((v[j]?cv$temp$46$var68:(1.0 - cv$temp$46$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value198);
																						}
																					}
																				}
																			}
																		}
																	}
																} else {
																	for(int i = 0; i < size; i += 1) {
																		if(true) {
																			for(int index$sample27$214 = 0; index$sample27$214 < weightings.length; index$sample27$214 += 1) {
																				int distributionTempVariable$var27$216 = index$sample27$214;
																				double cv$probabilitySample27Value215 = (cv$probabilitySample11Value198 * distribution$sample27[((i - 0) / 1)][index$sample27$214]);
																				for(int k = 0; k < (size + 1); k += 1) {
																					if(((i + 1) == k)) {
																						int traceTempVariable$var63$217_2 = v2[k];
																						if((k == (j + 1))) {
																							{
																								{
																									double cv$temp$47$var68;
																									{
																										double var68 = ((((1.0 * v1) + traceTempVariable$var59$200_1) + traceTempVariable$var63$217_2) / traceTempVariable$var67$5_1);
																										cv$temp$47$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value215) + Math.log((v[j]?cv$temp$47$var68:(1.0 - cv$temp$47$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value215) + Math.log((v[j]?cv$temp$47$var68:(1.0 - cv$temp$47$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value215) + Math.log((v[j]?cv$temp$47$var68:(1.0 - cv$temp$47$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value215) + Math.log((v[j]?cv$temp$47$var68:(1.0 - cv$temp$47$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value215) + Math.log((v[j]?cv$temp$47$var68:(1.0 - cv$temp$47$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value215);
																								}
																							}
																						}
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
															int traceTempVariable$var59$201_1 = cv$currentValue;
															if((0 == j)) {
																if(fixedFlag$sample27) {
																	for(int i = 0; i < size; i += 1) {
																		for(int k = 0; k < (size + 1); k += 1) {
																			if(((i + 1) == k)) {
																				int traceTempVariable$var63$218_3 = v2[k];
																				if((k == (j + 1))) {
																					{
																						{
																							double cv$temp$48$var68;
																							{
																								double var68 = ((((1.0 * distributionTempVariable$v1$194) + traceTempVariable$var59$201_1) + traceTempVariable$var63$218_3) / traceTempVariable$var67$5_1);
																								cv$temp$48$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample5Value193) + Math.log((v[j]?cv$temp$48$var68:(1.0 - cv$temp$48$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value193) + Math.log((v[j]?cv$temp$48$var68:(1.0 - cv$temp$48$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value193) + Math.log((v[j]?cv$temp$48$var68:(1.0 - cv$temp$48$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value193) + Math.log((v[j]?cv$temp$48$var68:(1.0 - cv$temp$48$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value193) + Math.log((v[j]?cv$temp$48$var68:(1.0 - cv$temp$48$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value193);
																						}
																					}
																				}
																			}
																		}
																	}
																} else {
																	for(int i = 0; i < size; i += 1) {
																		if(true) {
																			for(int index$sample27$220 = 0; index$sample27$220 < weightings.length; index$sample27$220 += 1) {
																				int distributionTempVariable$var27$222 = index$sample27$220;
																				double cv$probabilitySample27Value221 = (cv$probabilitySample5Value193 * distribution$sample27[((i - 0) / 1)][index$sample27$220]);
																				for(int k = 0; k < (size + 1); k += 1) {
																					if(((i + 1) == k)) {
																						int traceTempVariable$var63$223_2 = v2[k];
																						if((k == (j + 1))) {
																							{
																								{
																									double cv$temp$49$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$194) + traceTempVariable$var59$201_1) + traceTempVariable$var63$223_2) / traceTempVariable$var67$5_1);
																										cv$temp$49$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value221) + Math.log((v[j]?cv$temp$49$var68:(1.0 - cv$temp$49$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value221) + Math.log((v[j]?cv$temp$49$var68:(1.0 - cv$temp$49$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value221) + Math.log((v[j]?cv$temp$49$var68:(1.0 - cv$temp$49$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value221) + Math.log((v[j]?cv$temp$49$var68:(1.0 - cv$temp$49$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value221) + Math.log((v[j]?cv$temp$49$var68:(1.0 - cv$temp$49$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value221);
																								}
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
																for(int index$sample11$202 = 0; index$sample11$202 < weightings.length; index$sample11$202 += 1) {
																	int distributionTempVariable$var11$204 = index$sample11$202;
																	double cv$probabilitySample11Value203 = (cv$probabilitySample5Value193 * distribution$sample11[index$sample11$202]);
																	int traceTempVariable$var59$205_1 = cv$currentValue;
																	if((0 == j)) {
																		if(fixedFlag$sample27) {
																			for(int i = 0; i < size; i += 1) {
																				for(int k = 0; k < (size + 1); k += 1) {
																					if(((i + 1) == k)) {
																						int traceTempVariable$var63$224_3 = v2[k];
																						if((k == (j + 1))) {
																							{
																								{
																									double cv$temp$50$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$194) + traceTempVariable$var59$205_1) + traceTempVariable$var63$224_3) / traceTempVariable$var67$5_1);
																										cv$temp$50$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample11Value203) + Math.log((v[j]?cv$temp$50$var68:(1.0 - cv$temp$50$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value203) + Math.log((v[j]?cv$temp$50$var68:(1.0 - cv$temp$50$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value203) + Math.log((v[j]?cv$temp$50$var68:(1.0 - cv$temp$50$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value203) + Math.log((v[j]?cv$temp$50$var68:(1.0 - cv$temp$50$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value203) + Math.log((v[j]?cv$temp$50$var68:(1.0 - cv$temp$50$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value203);
																								}
																							}
																						}
																					}
																				}
																			}
																		} else {
																			for(int i = 0; i < size; i += 1) {
																				if(true) {
																					for(int index$sample27$226 = 0; index$sample27$226 < weightings.length; index$sample27$226 += 1) {
																						int distributionTempVariable$var27$228 = index$sample27$226;
																						double cv$probabilitySample27Value227 = (cv$probabilitySample11Value203 * distribution$sample27[((i - 0) / 1)][index$sample27$226]);
																						for(int k = 0; k < (size + 1); k += 1) {
																							if(((i + 1) == k)) {
																								int traceTempVariable$var63$229_2 = v2[k];
																								if((k == (j + 1))) {
																									{
																										{
																											double cv$temp$51$var68;
																											{
																												double var68 = ((((1.0 * distributionTempVariable$v1$194) + traceTempVariable$var59$205_1) + traceTempVariable$var63$229_2) / traceTempVariable$var67$5_1);
																												cv$temp$51$var68 = var68;
																											}
																											if(((Math.log(cv$probabilitySample27Value227) + Math.log((v[j]?cv$temp$51$var68:(1.0 - cv$temp$51$var68)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value227) + Math.log((v[j]?cv$temp$51$var68:(1.0 - cv$temp$51$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value227) + Math.log((v[j]?cv$temp$51$var68:(1.0 - cv$temp$51$var68))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value227) + Math.log((v[j]?cv$temp$51$var68:(1.0 - cv$temp$51$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value227) + Math.log((v[j]?cv$temp$51$var68:(1.0 - cv$temp$51$var68)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value227);
																										}
																									}
																								}
																							}
																						}
																					}
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
													if(fixedFlag$sample27) {
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == j)) {
																int traceTempVariable$var44$247_1 = cv$currentValue;
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var63$247_3 = traceTempVariable$var44$247_1;
																		if((k == (j + 1))) {
																			{
																				{
																					double cv$temp$52$var68;
																					{
																						double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$247_3) / traceTempVariable$var67$5_1);
																						cv$temp$52$var68 = var68;
																					}
																					if(((Math.log(1.0) + Math.log((v[j]?cv$temp$52$var68:(1.0 - cv$temp$52$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$52$var68:(1.0 - cv$temp$52$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$52$var68:(1.0 - cv$temp$52$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$52$var68:(1.0 - cv$temp$52$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$52$var68:(1.0 - cv$temp$52$var68)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
																if(!true) {
																	for(int index$sample11$248 = 0; index$sample11$248 < weightings.length; index$sample11$248 += 1) {
																		int distributionTempVariable$var11$250 = index$sample11$248;
																		double cv$probabilitySample11Value249 = (1.0 * distribution$sample11[index$sample11$248]);
																		int traceTempVariable$var44$251_1 = cv$currentValue;
																		for(int k = 0; k < (size + 1); k += 1) {
																			if((0 == k)) {
																				int traceTempVariable$var63$251_3 = traceTempVariable$var44$251_1;
																				if((k == (j + 1))) {
																					{
																						{
																							double cv$temp$53$var68;
																							{
																								double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$251_3) / traceTempVariable$var67$5_1);
																								cv$temp$53$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample11Value249) + Math.log((v[j]?cv$temp$53$var68:(1.0 - cv$temp$53$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value249) + Math.log((v[j]?cv$temp$53$var68:(1.0 - cv$temp$53$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value249) + Math.log((v[j]?cv$temp$53$var68:(1.0 - cv$temp$53$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value249) + Math.log((v[j]?cv$temp$53$var68:(1.0 - cv$temp$53$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value249) + Math.log((v[j]?cv$temp$53$var68:(1.0 - cv$temp$53$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value249);
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
																for(int index$sample27$237 = 0; index$sample27$237 < weightings.length; index$sample27$237 += 1) {
																	int distributionTempVariable$var27$239 = index$sample27$237;
																	double cv$probabilitySample27Value238 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$237]);
																	if(((i + 1) == j)) {
																		int traceTempVariable$var44$252_1 = cv$currentValue;
																		for(int k = 0; k < (size + 1); k += 1) {
																			if((0 == k)) {
																				int traceTempVariable$var63$252_3 = traceTempVariable$var44$252_1;
																				if((k == (j + 1))) {
																					{
																						{
																							double cv$temp$54$var68;
																							{
																								double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$252_3) / traceTempVariable$var67$5_1);
																								cv$temp$54$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample27Value238) + Math.log((v[j]?cv$temp$54$var68:(1.0 - cv$temp$54$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value238) + Math.log((v[j]?cv$temp$54$var68:(1.0 - cv$temp$54$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value238) + Math.log((v[j]?cv$temp$54$var68:(1.0 - cv$temp$54$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value238) + Math.log((v[j]?cv$temp$54$var68:(1.0 - cv$temp$54$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value238) + Math.log((v[j]?cv$temp$54$var68:(1.0 - cv$temp$54$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value238);
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$253 = 0; index$sample11$253 < weightings.length; index$sample11$253 += 1) {
																				int distributionTempVariable$var11$255 = index$sample11$253;
																				double cv$probabilitySample11Value254 = (cv$probabilitySample27Value238 * distribution$sample11[index$sample11$253]);
																				int traceTempVariable$var44$256_1 = cv$currentValue;
																				for(int k = 0; k < (size + 1); k += 1) {
																					if((0 == k)) {
																						int traceTempVariable$var63$256_3 = traceTempVariable$var44$256_1;
																						if((k == (j + 1))) {
																							{
																								{
																									double cv$temp$55$var68;
																									{
																										double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$256_3) / traceTempVariable$var67$5_1);
																										cv$temp$55$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample11Value254) + Math.log((v[j]?cv$temp$55$var68:(1.0 - cv$temp$55$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value254) + Math.log((v[j]?cv$temp$55$var68:(1.0 - cv$temp$55$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value254) + Math.log((v[j]?cv$temp$55$var68:(1.0 - cv$temp$55$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value254) + Math.log((v[j]?cv$temp$55$var68:(1.0 - cv$temp$55$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value254) + Math.log((v[j]?cv$temp$55$var68:(1.0 - cv$temp$55$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value254);
																								}
																							}
																						}
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
														for(int index$sample5$231 = 0; index$sample5$231 < weightings.length; index$sample5$231 += 1) {
															int distributionTempVariable$v1$233 = index$sample5$231;
															double cv$probabilitySample5Value232 = (1.0 * distribution$sample5[index$sample5$231]);
															if(fixedFlag$sample27) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == j)) {
																		int traceTempVariable$var44$257_1 = cv$currentValue;
																		for(int k = 0; k < (size + 1); k += 1) {
																			if((0 == k)) {
																				int traceTempVariable$var63$257_3 = traceTempVariable$var44$257_1;
																				if((k == (j + 1))) {
																					{
																						{
																							double cv$temp$56$var68;
																							{
																								double var68 = ((((1.0 * distributionTempVariable$v1$233) + v2[j]) + traceTempVariable$var63$257_3) / traceTempVariable$var67$5_1);
																								cv$temp$56$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample5Value232) + Math.log((v[j]?cv$temp$56$var68:(1.0 - cv$temp$56$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value232) + Math.log((v[j]?cv$temp$56$var68:(1.0 - cv$temp$56$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value232) + Math.log((v[j]?cv$temp$56$var68:(1.0 - cv$temp$56$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value232) + Math.log((v[j]?cv$temp$56$var68:(1.0 - cv$temp$56$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value232) + Math.log((v[j]?cv$temp$56$var68:(1.0 - cv$temp$56$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value232);
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$258 = 0; index$sample11$258 < weightings.length; index$sample11$258 += 1) {
																				int distributionTempVariable$var11$260 = index$sample11$258;
																				double cv$probabilitySample11Value259 = (cv$probabilitySample5Value232 * distribution$sample11[index$sample11$258]);
																				int traceTempVariable$var44$261_1 = cv$currentValue;
																				for(int k = 0; k < (size + 1); k += 1) {
																					if((0 == k)) {
																						int traceTempVariable$var63$261_3 = traceTempVariable$var44$261_1;
																						if((k == (j + 1))) {
																							{
																								{
																									double cv$temp$57$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$233) + v2[j]) + traceTempVariable$var63$261_3) / traceTempVariable$var67$5_1);
																										cv$temp$57$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample11Value259) + Math.log((v[j]?cv$temp$57$var68:(1.0 - cv$temp$57$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value259) + Math.log((v[j]?cv$temp$57$var68:(1.0 - cv$temp$57$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value259) + Math.log((v[j]?cv$temp$57$var68:(1.0 - cv$temp$57$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value259) + Math.log((v[j]?cv$temp$57$var68:(1.0 - cv$temp$57$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value259) + Math.log((v[j]?cv$temp$57$var68:(1.0 - cv$temp$57$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value259);
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
																		for(int index$sample27$243 = 0; index$sample27$243 < weightings.length; index$sample27$243 += 1) {
																			int distributionTempVariable$var27$245 = index$sample27$243;
																			double cv$probabilitySample27Value244 = (cv$probabilitySample5Value232 * distribution$sample27[((i - 0) / 1)][index$sample27$243]);
																			if(((i + 1) == j)) {
																				int traceTempVariable$var44$262_1 = cv$currentValue;
																				for(int k = 0; k < (size + 1); k += 1) {
																					if((0 == k)) {
																						int traceTempVariable$var63$262_3 = traceTempVariable$var44$262_1;
																						if((k == (j + 1))) {
																							{
																								{
																									double cv$temp$58$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$233) + v2[j]) + traceTempVariable$var63$262_3) / traceTempVariable$var67$5_1);
																										cv$temp$58$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value244) + Math.log((v[j]?cv$temp$58$var68:(1.0 - cv$temp$58$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value244) + Math.log((v[j]?cv$temp$58$var68:(1.0 - cv$temp$58$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value244) + Math.log((v[j]?cv$temp$58$var68:(1.0 - cv$temp$58$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value244) + Math.log((v[j]?cv$temp$58$var68:(1.0 - cv$temp$58$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value244) + Math.log((v[j]?cv$temp$58$var68:(1.0 - cv$temp$58$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value244);
																								}
																							}
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample11$263 = 0; index$sample11$263 < weightings.length; index$sample11$263 += 1) {
																						int distributionTempVariable$var11$265 = index$sample11$263;
																						double cv$probabilitySample11Value264 = (cv$probabilitySample27Value244 * distribution$sample11[index$sample11$263]);
																						int traceTempVariable$var44$266_1 = cv$currentValue;
																						for(int k = 0; k < (size + 1); k += 1) {
																							if((0 == k)) {
																								int traceTempVariable$var63$266_3 = traceTempVariable$var44$266_1;
																								if((k == (j + 1))) {
																									{
																										{
																											double cv$temp$59$var68;
																											{
																												double var68 = ((((1.0 * distributionTempVariable$v1$233) + v2[j]) + traceTempVariable$var63$266_3) / traceTempVariable$var67$5_1);
																												cv$temp$59$var68 = var68;
																											}
																											if(((Math.log(cv$probabilitySample11Value264) + Math.log((v[j]?cv$temp$59$var68:(1.0 - cv$temp$59$var68)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value264) + Math.log((v[j]?cv$temp$59$var68:(1.0 - cv$temp$59$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value264) + Math.log((v[j]?cv$temp$59$var68:(1.0 - cv$temp$59$var68))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value264) + Math.log((v[j]?cv$temp$59$var68:(1.0 - cv$temp$59$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value264) + Math.log((v[j]?cv$temp$59$var68:(1.0 - cv$temp$59$var68)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value264);
																										}
																									}
																								}
																							}
																						}
																					}
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
													if(fixedFlag$sample27) {
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == j)) {
																for(int index$i$284_1 = 0; index$i$284_1 < size; index$i$284_1 += 1) {
																	for(int k = 0; k < (size + 1); k += 1) {
																		if(((index$i$284_1 + 1) == k)) {
																			int traceTempVariable$var63$284_3 = v2[k];
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$60$var68;
																						{
																							double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$284_3) / traceTempVariable$var67$5_1);
																							cv$temp$60$var68 = var68;
																						}
																						if(((Math.log(1.0) + Math.log((v[j]?cv$temp$60$var68:(1.0 - cv$temp$60$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$60$var68:(1.0 - cv$temp$60$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$60$var68:(1.0 - cv$temp$60$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$60$var68:(1.0 - cv$temp$60$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$60$var68:(1.0 - cv$temp$60$var68)))));
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
																for(int index$sample27$274 = 0; index$sample27$274 < weightings.length; index$sample27$274 += 1) {
																	int distributionTempVariable$var27$276 = index$sample27$274;
																	double cv$probabilitySample27Value275 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$274]);
																	if(((i + 1) == j)) {
																		for(int k = 0; k < (size + 1); k += 1) {
																			if(((i + 1) == k)) {
																				int traceTempVariable$var63$285_2 = v2[k];
																				if((k == (j + 1))) {
																					{
																						{
																							double cv$temp$61$var68;
																							{
																								double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$285_2) / traceTempVariable$var67$5_1);
																								cv$temp$61$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample27Value275) + Math.log((v[j]?cv$temp$61$var68:(1.0 - cv$temp$61$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value275) + Math.log((v[j]?cv$temp$61$var68:(1.0 - cv$temp$61$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value275) + Math.log((v[j]?cv$temp$61$var68:(1.0 - cv$temp$61$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value275) + Math.log((v[j]?cv$temp$61$var68:(1.0 - cv$temp$61$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value275) + Math.log((v[j]?cv$temp$61$var68:(1.0 - cv$temp$61$var68)))));
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
																					for(int k = 0; k < (size + 1); k += 1) {
																						if(((index$i$286 + 1) == k)) {
																							int traceTempVariable$var63$290_2 = v2[k];
																							if((k == (j + 1))) {
																								{
																									{
																										double cv$temp$62$var68;
																										{
																											double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$290_2) / traceTempVariable$var67$5_1);
																											cv$temp$62$var68 = var68;
																										}
																										if(((Math.log(cv$probabilitySample27Value288) + Math.log((v[j]?cv$temp$62$var68:(1.0 - cv$temp$62$var68)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value288) + Math.log((v[j]?cv$temp$62$var68:(1.0 - cv$temp$62$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value288) + Math.log((v[j]?cv$temp$62$var68:(1.0 - cv$temp$62$var68))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value288) + Math.log((v[j]?cv$temp$62$var68:(1.0 - cv$temp$62$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value288) + Math.log((v[j]?cv$temp$62$var68:(1.0 - cv$temp$62$var68)))));
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
												} else {
													if(true) {
														for(int index$sample5$268 = 0; index$sample5$268 < weightings.length; index$sample5$268 += 1) {
															int distributionTempVariable$v1$270 = index$sample5$268;
															double cv$probabilitySample5Value269 = (1.0 * distribution$sample5[index$sample5$268]);
															if(fixedFlag$sample27) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == j)) {
																		for(int index$i$291_1 = 0; index$i$291_1 < size; index$i$291_1 += 1) {
																			for(int k = 0; k < (size + 1); k += 1) {
																				if(((index$i$291_1 + 1) == k)) {
																					int traceTempVariable$var63$291_3 = v2[k];
																					if((k == (j + 1))) {
																						{
																							{
																								double cv$temp$63$var68;
																								{
																									double var68 = ((((1.0 * distributionTempVariable$v1$270) + v2[j]) + traceTempVariable$var63$291_3) / traceTempVariable$var67$5_1);
																									cv$temp$63$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample5Value269) + Math.log((v[j]?cv$temp$63$var68:(1.0 - cv$temp$63$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value269) + Math.log((v[j]?cv$temp$63$var68:(1.0 - cv$temp$63$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value269) + Math.log((v[j]?cv$temp$63$var68:(1.0 - cv$temp$63$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value269) + Math.log((v[j]?cv$temp$63$var68:(1.0 - cv$temp$63$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value269) + Math.log((v[j]?cv$temp$63$var68:(1.0 - cv$temp$63$var68)))));
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
															} else {
																for(int i = 0; i < size; i += 1) {
																	if(true) {
																		for(int index$sample27$280 = 0; index$sample27$280 < weightings.length; index$sample27$280 += 1) {
																			int distributionTempVariable$var27$282 = index$sample27$280;
																			double cv$probabilitySample27Value281 = (cv$probabilitySample5Value269 * distribution$sample27[((i - 0) / 1)][index$sample27$280]);
																			if(((i + 1) == j)) {
																				for(int k = 0; k < (size + 1); k += 1) {
																					if(((i + 1) == k)) {
																						int traceTempVariable$var63$292_2 = v2[k];
																						if((k == (j + 1))) {
																							{
																								{
																									double cv$temp$64$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$270) + v2[j]) + traceTempVariable$var63$292_2) / traceTempVariable$var67$5_1);
																										cv$temp$64$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value281) + Math.log((v[j]?cv$temp$64$var68:(1.0 - cv$temp$64$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value281) + Math.log((v[j]?cv$temp$64$var68:(1.0 - cv$temp$64$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value281) + Math.log((v[j]?cv$temp$64$var68:(1.0 - cv$temp$64$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value281) + Math.log((v[j]?cv$temp$64$var68:(1.0 - cv$temp$64$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value281) + Math.log((v[j]?cv$temp$64$var68:(1.0 - cv$temp$64$var68)))));
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
																							for(int k = 0; k < (size + 1); k += 1) {
																								if(((index$i$293 + 1) == k)) {
																									int traceTempVariable$var63$297_2 = v2[k];
																									if((k == (j + 1))) {
																										{
																											{
																												double cv$temp$65$var68;
																												{
																													double var68 = ((((1.0 * distributionTempVariable$v1$270) + v2[j]) + traceTempVariable$var63$297_2) / traceTempVariable$var67$5_1);
																													cv$temp$65$var68 = var68;
																												}
																												if(((Math.log(cv$probabilitySample27Value295) + Math.log((v[j]?cv$temp$65$var68:(1.0 - cv$temp$65$var68)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value295) + Math.log((v[j]?cv$temp$65$var68:(1.0 - cv$temp$65$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value295) + Math.log((v[j]?cv$temp$65$var68:(1.0 - cv$temp$65$var68))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value295) + Math.log((v[j]?cv$temp$65$var68:(1.0 - cv$temp$65$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value295) + Math.log((v[j]?cv$temp$65$var68:(1.0 - cv$temp$65$var68)))));
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
							int traceTempVariable$var44$6_1 = cv$currentValue;
							for(int k = 0; k < (size + 1); k += 1) {
								if((0 == k)) {
									int traceTempVariable$var63$6_3 = traceTempVariable$var44$6_1;
									for(int j = 0; j < size; j += 1) {
										if((k == (j + 1))) {
											if(!guard$sample11bernoulli69[((j - 0) / 1)]) {
												guard$sample11bernoulli69[((j - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if(fixedFlag$sample5) {
															int traceTempVariable$var59$303_1 = cv$currentValue;
															if((0 == j)) {
																int traceTempVariable$var67$313_1 = cv$currentValue;
																if((0 == (j + 1))) {
																	{
																		{
																			double cv$temp$66$var68;
																			{
																				double var68 = ((((1.0 * v1) + traceTempVariable$var59$303_1) + traceTempVariable$var63$6_3) / traceTempVariable$var67$313_1);
																				cv$temp$66$var68 = var68;
																			}
																			if(((Math.log(1.0) + Math.log((v[j]?cv$temp$66$var68:(1.0 - cv$temp$66$var68)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$66$var68:(1.0 - cv$temp$66$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$66$var68:(1.0 - cv$temp$66$var68))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$66$var68:(1.0 - cv$temp$66$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$66$var68:(1.0 - cv$temp$66$var68)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
																if(!true) {
																	for(int index$sample11$314 = 0; index$sample11$314 < weightings.length; index$sample11$314 += 1) {
																		int distributionTempVariable$var11$316 = index$sample11$314;
																		double cv$probabilitySample11Value315 = (1.0 * distribution$sample11[index$sample11$314]);
																		int traceTempVariable$var67$317_1 = cv$currentValue;
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$67$var68;
																					{
																						double var68 = ((((1.0 * v1) + traceTempVariable$var59$303_1) + traceTempVariable$var63$6_3) / traceTempVariable$var67$317_1);
																						cv$temp$67$var68 = var68;
																					}
																					if(((Math.log(cv$probabilitySample11Value315) + Math.log((v[j]?cv$temp$67$var68:(1.0 - cv$temp$67$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value315) + Math.log((v[j]?cv$temp$67$var68:(1.0 - cv$temp$67$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value315) + Math.log((v[j]?cv$temp$67$var68:(1.0 - cv$temp$67$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value315) + Math.log((v[j]?cv$temp$67$var68:(1.0 - cv$temp$67$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value315) + Math.log((v[j]?cv$temp$67$var68:(1.0 - cv$temp$67$var68)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value315);
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
																	int traceTempVariable$var59$307_1 = cv$currentValue;
																	if((0 == j)) {
																		int traceTempVariable$var67$318_1 = cv$currentValue;
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$68$var68;
																					{
																						double var68 = ((((1.0 * v1) + traceTempVariable$var59$307_1) + traceTempVariable$var63$6_3) / traceTempVariable$var67$318_1);
																						cv$temp$68$var68 = var68;
																					}
																					if(((Math.log(cv$probabilitySample11Value305) + Math.log((v[j]?cv$temp$68$var68:(1.0 - cv$temp$68$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value305) + Math.log((v[j]?cv$temp$68$var68:(1.0 - cv$temp$68$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value305) + Math.log((v[j]?cv$temp$68$var68:(1.0 - cv$temp$68$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value305) + Math.log((v[j]?cv$temp$68$var68:(1.0 - cv$temp$68$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value305) + Math.log((v[j]?cv$temp$68$var68:(1.0 - cv$temp$68$var68)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value305);
																				}
																			}
																		}
																		int traceTempVariable$var67$319_1 = cv$currentValue;
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$69$var68;
																					{
																						double var68 = ((((1.0 * v1) + traceTempVariable$var59$307_1) + traceTempVariable$var63$6_3) / traceTempVariable$var67$319_1);
																						cv$temp$69$var68 = var68;
																					}
																					if(((Math.log(cv$probabilitySample11Value305) + Math.log((v[j]?cv$temp$69$var68:(1.0 - cv$temp$69$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value305) + Math.log((v[j]?cv$temp$69$var68:(1.0 - cv$temp$69$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value305) + Math.log((v[j]?cv$temp$69$var68:(1.0 - cv$temp$69$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value305) + Math.log((v[j]?cv$temp$69$var68:(1.0 - cv$temp$69$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value305) + Math.log((v[j]?cv$temp$69$var68:(1.0 - cv$temp$69$var68)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value305);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$320 = 0; index$sample11$320 < weightings.length; index$sample11$320 += 1) {
																				int distributionTempVariable$var11$322 = index$sample11$320;
																				double cv$probabilitySample11Value321 = (cv$probabilitySample11Value305 * distribution$sample11[index$sample11$320]);
																				int traceTempVariable$var67$323_1 = cv$currentValue;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$70$var68;
																							{
																								double var68 = ((((1.0 * v1) + traceTempVariable$var59$307_1) + traceTempVariable$var63$6_3) / traceTempVariable$var67$323_1);
																								cv$temp$70$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample11Value321) + Math.log((v[j]?cv$temp$70$var68:(1.0 - cv$temp$70$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value321) + Math.log((v[j]?cv$temp$70$var68:(1.0 - cv$temp$70$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value321) + Math.log((v[j]?cv$temp$70$var68:(1.0 - cv$temp$70$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value321) + Math.log((v[j]?cv$temp$70$var68:(1.0 - cv$temp$70$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value321) + Math.log((v[j]?cv$temp$70$var68:(1.0 - cv$temp$70$var68)))));
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
														} else {
															if(true) {
																for(int index$sample5$299 = 0; index$sample5$299 < weightings.length; index$sample5$299 += 1) {
																	int distributionTempVariable$v1$301 = index$sample5$299;
																	double cv$probabilitySample5Value300 = (1.0 * distribution$sample5[index$sample5$299]);
																	int traceTempVariable$var59$308_1 = cv$currentValue;
																	if((0 == j)) {
																		int traceTempVariable$var67$324_1 = cv$currentValue;
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$71$var68;
																					{
																						double var68 = ((((1.0 * distributionTempVariable$v1$301) + traceTempVariable$var59$308_1) + traceTempVariable$var63$6_3) / traceTempVariable$var67$324_1);
																						cv$temp$71$var68 = var68;
																					}
																					if(((Math.log(cv$probabilitySample5Value300) + Math.log((v[j]?cv$temp$71$var68:(1.0 - cv$temp$71$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value300) + Math.log((v[j]?cv$temp$71$var68:(1.0 - cv$temp$71$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value300) + Math.log((v[j]?cv$temp$71$var68:(1.0 - cv$temp$71$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value300) + Math.log((v[j]?cv$temp$71$var68:(1.0 - cv$temp$71$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value300) + Math.log((v[j]?cv$temp$71$var68:(1.0 - cv$temp$71$var68)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value300);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$325 = 0; index$sample11$325 < weightings.length; index$sample11$325 += 1) {
																				int distributionTempVariable$var11$327 = index$sample11$325;
																				double cv$probabilitySample11Value326 = (cv$probabilitySample5Value300 * distribution$sample11[index$sample11$325]);
																				int traceTempVariable$var67$328_1 = cv$currentValue;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$72$var68;
																							{
																								double var68 = ((((1.0 * distributionTempVariable$v1$301) + traceTempVariable$var59$308_1) + traceTempVariable$var63$6_3) / traceTempVariable$var67$328_1);
																								cv$temp$72$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample11Value326) + Math.log((v[j]?cv$temp$72$var68:(1.0 - cv$temp$72$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value326) + Math.log((v[j]?cv$temp$72$var68:(1.0 - cv$temp$72$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value326) + Math.log((v[j]?cv$temp$72$var68:(1.0 - cv$temp$72$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value326) + Math.log((v[j]?cv$temp$72$var68:(1.0 - cv$temp$72$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value326) + Math.log((v[j]?cv$temp$72$var68:(1.0 - cv$temp$72$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value326);
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
																			int traceTempVariable$var59$312_1 = cv$currentValue;
																			if((0 == j)) {
																				int traceTempVariable$var67$329_1 = cv$currentValue;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$73$var68;
																							{
																								double var68 = ((((1.0 * distributionTempVariable$v1$301) + traceTempVariable$var59$312_1) + traceTempVariable$var63$6_3) / traceTempVariable$var67$329_1);
																								cv$temp$73$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?cv$temp$73$var68:(1.0 - cv$temp$73$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?cv$temp$73$var68:(1.0 - cv$temp$73$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?cv$temp$73$var68:(1.0 - cv$temp$73$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?cv$temp$73$var68:(1.0 - cv$temp$73$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?cv$temp$73$var68:(1.0 - cv$temp$73$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value310);
																						}
																					}
																				}
																				int traceTempVariable$var67$330_1 = cv$currentValue;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$74$var68;
																							{
																								double var68 = ((((1.0 * distributionTempVariable$v1$301) + traceTempVariable$var59$312_1) + traceTempVariable$var63$6_3) / traceTempVariable$var67$330_1);
																								cv$temp$74$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?cv$temp$74$var68:(1.0 - cv$temp$74$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?cv$temp$74$var68:(1.0 - cv$temp$74$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?cv$temp$74$var68:(1.0 - cv$temp$74$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?cv$temp$74$var68:(1.0 - cv$temp$74$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value310) + Math.log((v[j]?cv$temp$74$var68:(1.0 - cv$temp$74$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value310);
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample11$331 = 0; index$sample11$331 < weightings.length; index$sample11$331 += 1) {
																						int distributionTempVariable$var11$333 = index$sample11$331;
																						double cv$probabilitySample11Value332 = (cv$probabilitySample11Value310 * distribution$sample11[index$sample11$331]);
																						int traceTempVariable$var67$334_1 = cv$currentValue;
																						if((0 == (j + 1))) {
																							{
																								{
																									double cv$temp$75$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$301) + traceTempVariable$var59$312_1) + traceTempVariable$var63$6_3) / traceTempVariable$var67$334_1);
																										cv$temp$75$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample11Value332) + Math.log((v[j]?cv$temp$75$var68:(1.0 - cv$temp$75$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value332) + Math.log((v[j]?cv$temp$75$var68:(1.0 - cv$temp$75$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value332) + Math.log((v[j]?cv$temp$75$var68:(1.0 - cv$temp$75$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value332) + Math.log((v[j]?cv$temp$75$var68:(1.0 - cv$temp$75$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value332) + Math.log((v[j]?cv$temp$75$var68:(1.0 - cv$temp$75$var68)))));
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
														if(fixedFlag$sample5) {
															if(fixedFlag$sample27) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == j)) {
																		int traceTempVariable$var67$352_1 = cv$currentValue;
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$76$var68;
																					{
																						double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$6_3) / traceTempVariable$var67$352_1);
																						cv$temp$76$var68 = var68;
																					}
																					if(((Math.log(1.0) + Math.log((v[j]?cv$temp$76$var68:(1.0 - cv$temp$76$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$76$var68:(1.0 - cv$temp$76$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$76$var68:(1.0 - cv$temp$76$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$76$var68:(1.0 - cv$temp$76$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$76$var68:(1.0 - cv$temp$76$var68)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$353 = 0; index$sample11$353 < weightings.length; index$sample11$353 += 1) {
																				int distributionTempVariable$var11$355 = index$sample11$353;
																				double cv$probabilitySample11Value354 = (1.0 * distribution$sample11[index$sample11$353]);
																				int traceTempVariable$var67$356_1 = cv$currentValue;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$77$var68;
																							{
																								double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$6_3) / traceTempVariable$var67$356_1);
																								cv$temp$77$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample11Value354) + Math.log((v[j]?cv$temp$77$var68:(1.0 - cv$temp$77$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value354) + Math.log((v[j]?cv$temp$77$var68:(1.0 - cv$temp$77$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value354) + Math.log((v[j]?cv$temp$77$var68:(1.0 - cv$temp$77$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value354) + Math.log((v[j]?cv$temp$77$var68:(1.0 - cv$temp$77$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value354) + Math.log((v[j]?cv$temp$77$var68:(1.0 - cv$temp$77$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value354);
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
																			if(((i + 1) == j)) {
																				int traceTempVariable$var67$357_1 = cv$currentValue;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$78$var68;
																							{
																								double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$6_3) / traceTempVariable$var67$357_1);
																								cv$temp$78$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample27Value343) + Math.log((v[j]?cv$temp$78$var68:(1.0 - cv$temp$78$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value343) + Math.log((v[j]?cv$temp$78$var68:(1.0 - cv$temp$78$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value343) + Math.log((v[j]?cv$temp$78$var68:(1.0 - cv$temp$78$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value343) + Math.log((v[j]?cv$temp$78$var68:(1.0 - cv$temp$78$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value343) + Math.log((v[j]?cv$temp$78$var68:(1.0 - cv$temp$78$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value343);
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample11$358 = 0; index$sample11$358 < weightings.length; index$sample11$358 += 1) {
																						int distributionTempVariable$var11$360 = index$sample11$358;
																						double cv$probabilitySample11Value359 = (cv$probabilitySample27Value343 * distribution$sample11[index$sample11$358]);
																						int traceTempVariable$var67$361_1 = cv$currentValue;
																						if((0 == (j + 1))) {
																							{
																								{
																									double cv$temp$79$var68;
																									{
																										double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$6_3) / traceTempVariable$var67$361_1);
																										cv$temp$79$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample11Value359) + Math.log((v[j]?cv$temp$79$var68:(1.0 - cv$temp$79$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value359) + Math.log((v[j]?cv$temp$79$var68:(1.0 - cv$temp$79$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value359) + Math.log((v[j]?cv$temp$79$var68:(1.0 - cv$temp$79$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value359) + Math.log((v[j]?cv$temp$79$var68:(1.0 - cv$temp$79$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value359) + Math.log((v[j]?cv$temp$79$var68:(1.0 - cv$temp$79$var68)))));
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
														} else {
															if(true) {
																for(int index$sample5$336 = 0; index$sample5$336 < weightings.length; index$sample5$336 += 1) {
																	int distributionTempVariable$v1$338 = index$sample5$336;
																	double cv$probabilitySample5Value337 = (1.0 * distribution$sample5[index$sample5$336]);
																	if(fixedFlag$sample27) {
																		for(int i = 0; i < size; i += 1) {
																			if(((i + 1) == j)) {
																				int traceTempVariable$var67$362_1 = cv$currentValue;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$80$var68;
																							{
																								double var68 = ((((1.0 * distributionTempVariable$v1$338) + v2[j]) + traceTempVariable$var63$6_3) / traceTempVariable$var67$362_1);
																								cv$temp$80$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample5Value337) + Math.log((v[j]?cv$temp$80$var68:(1.0 - cv$temp$80$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value337) + Math.log((v[j]?cv$temp$80$var68:(1.0 - cv$temp$80$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value337) + Math.log((v[j]?cv$temp$80$var68:(1.0 - cv$temp$80$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value337) + Math.log((v[j]?cv$temp$80$var68:(1.0 - cv$temp$80$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value337) + Math.log((v[j]?cv$temp$80$var68:(1.0 - cv$temp$80$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value337);
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample11$363 = 0; index$sample11$363 < weightings.length; index$sample11$363 += 1) {
																						int distributionTempVariable$var11$365 = index$sample11$363;
																						double cv$probabilitySample11Value364 = (cv$probabilitySample5Value337 * distribution$sample11[index$sample11$363]);
																						int traceTempVariable$var67$366_1 = cv$currentValue;
																						if((0 == (j + 1))) {
																							{
																								{
																									double cv$temp$81$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$338) + v2[j]) + traceTempVariable$var63$6_3) / traceTempVariable$var67$366_1);
																										cv$temp$81$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample11Value364) + Math.log((v[j]?cv$temp$81$var68:(1.0 - cv$temp$81$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value364) + Math.log((v[j]?cv$temp$81$var68:(1.0 - cv$temp$81$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value364) + Math.log((v[j]?cv$temp$81$var68:(1.0 - cv$temp$81$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value364) + Math.log((v[j]?cv$temp$81$var68:(1.0 - cv$temp$81$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value364) + Math.log((v[j]?cv$temp$81$var68:(1.0 - cv$temp$81$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value364);
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
																					if(((i + 1) == j)) {
																						int traceTempVariable$var67$367_1 = cv$currentValue;
																						if((0 == (j + 1))) {
																							{
																								{
																									double cv$temp$82$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$338) + v2[j]) + traceTempVariable$var63$6_3) / traceTempVariable$var67$367_1);
																										cv$temp$82$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value349) + Math.log((v[j]?cv$temp$82$var68:(1.0 - cv$temp$82$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value349) + Math.log((v[j]?cv$temp$82$var68:(1.0 - cv$temp$82$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value349) + Math.log((v[j]?cv$temp$82$var68:(1.0 - cv$temp$82$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value349) + Math.log((v[j]?cv$temp$82$var68:(1.0 - cv$temp$82$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value349) + Math.log((v[j]?cv$temp$82$var68:(1.0 - cv$temp$82$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value349);
																								}
																							}
																						}
																						if(!true) {
																							for(int index$sample11$368 = 0; index$sample11$368 < weightings.length; index$sample11$368 += 1) {
																								int distributionTempVariable$var11$370 = index$sample11$368;
																								double cv$probabilitySample11Value369 = (cv$probabilitySample27Value349 * distribution$sample11[index$sample11$368]);
																								int traceTempVariable$var67$371_1 = cv$currentValue;
																								if((0 == (j + 1))) {
																									{
																										{
																											double cv$temp$83$var68;
																											{
																												double var68 = ((((1.0 * distributionTempVariable$v1$338) + v2[j]) + traceTempVariable$var63$6_3) / traceTempVariable$var67$371_1);
																												cv$temp$83$var68 = var68;
																											}
																											if(((Math.log(cv$probabilitySample11Value369) + Math.log((v[j]?cv$temp$83$var68:(1.0 - cv$temp$83$var68)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value369) + Math.log((v[j]?cv$temp$83$var68:(1.0 - cv$temp$83$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value369) + Math.log((v[j]?cv$temp$83$var68:(1.0 - cv$temp$83$var68))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value369) + Math.log((v[j]?cv$temp$83$var68:(1.0 - cv$temp$83$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value369) + Math.log((v[j]?cv$temp$83$var68:(1.0 - cv$temp$83$var68)))));
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
														if(fixedFlag$sample5) {
															int traceTempVariable$var59$377_1 = cv$currentValue;
															if((0 == j)) {
																if(fixedFlag$sample27) {
																	for(int i = 0; i < size; i += 1) {
																		if(((i + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$84$var68;
																					{
																						double var68 = ((((1.0 * v1) + traceTempVariable$var59$377_1) + traceTempVariable$var63$6_3) / v2[(j + 1)]);
																						cv$temp$84$var68 = var68;
																					}
																					if(((Math.log(1.0) + Math.log((v[j]?cv$temp$84$var68:(1.0 - cv$temp$84$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$84$var68:(1.0 - cv$temp$84$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$84$var68:(1.0 - cv$temp$84$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$84$var68:(1.0 - cv$temp$84$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$84$var68:(1.0 - cv$temp$84$var68)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																				if(((i + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$85$var68;
																							{
																								double var68 = ((((1.0 * v1) + traceTempVariable$var59$377_1) + traceTempVariable$var63$6_3) / v2[(j + 1)]);
																								cv$temp$85$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample27Value390) + Math.log((v[j]?cv$temp$85$var68:(1.0 - cv$temp$85$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value390) + Math.log((v[j]?cv$temp$85$var68:(1.0 - cv$temp$85$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value390) + Math.log((v[j]?cv$temp$85$var68:(1.0 - cv$temp$85$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value390) + Math.log((v[j]?cv$temp$85$var68:(1.0 - cv$temp$85$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value390) + Math.log((v[j]?cv$temp$85$var68:(1.0 - cv$temp$85$var68)))));
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
															if(!true) {
																for(int index$sample11$378 = 0; index$sample11$378 < weightings.length; index$sample11$378 += 1) {
																	int distributionTempVariable$var11$380 = index$sample11$378;
																	double cv$probabilitySample11Value379 = (1.0 * distribution$sample11[index$sample11$378]);
																	int traceTempVariable$var59$381_1 = cv$currentValue;
																	if((0 == j)) {
																		if(fixedFlag$sample27) {
																			for(int i = 0; i < size; i += 1) {
																				if(((i + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$86$var68;
																							{
																								double var68 = ((((1.0 * v1) + traceTempVariable$var59$381_1) + traceTempVariable$var63$6_3) / v2[(j + 1)]);
																								cv$temp$86$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample11Value379) + Math.log((v[j]?cv$temp$86$var68:(1.0 - cv$temp$86$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value379) + Math.log((v[j]?cv$temp$86$var68:(1.0 - cv$temp$86$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value379) + Math.log((v[j]?cv$temp$86$var68:(1.0 - cv$temp$86$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value379) + Math.log((v[j]?cv$temp$86$var68:(1.0 - cv$temp$86$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value379) + Math.log((v[j]?cv$temp$86$var68:(1.0 - cv$temp$86$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value379);
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
																						if(((i + 1) == (j + 1))) {
																							{
																								{
																									double cv$temp$87$var68;
																									{
																										double var68 = ((((1.0 * v1) + traceTempVariable$var59$381_1) + traceTempVariable$var63$6_3) / v2[(j + 1)]);
																										cv$temp$87$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value396) + Math.log((v[j]?cv$temp$87$var68:(1.0 - cv$temp$87$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value396) + Math.log((v[j]?cv$temp$87$var68:(1.0 - cv$temp$87$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value396) + Math.log((v[j]?cv$temp$87$var68:(1.0 - cv$temp$87$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value396) + Math.log((v[j]?cv$temp$87$var68:(1.0 - cv$temp$87$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value396) + Math.log((v[j]?cv$temp$87$var68:(1.0 - cv$temp$87$var68)))));
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
														} else {
															if(true) {
																for(int index$sample5$373 = 0; index$sample5$373 < weightings.length; index$sample5$373 += 1) {
																	int distributionTempVariable$v1$375 = index$sample5$373;
																	double cv$probabilitySample5Value374 = (1.0 * distribution$sample5[index$sample5$373]);
																	int traceTempVariable$var59$382_1 = cv$currentValue;
																	if((0 == j)) {
																		if(fixedFlag$sample27) {
																			for(int i = 0; i < size; i += 1) {
																				if(((i + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$88$var68;
																							{
																								double var68 = ((((1.0 * distributionTempVariable$v1$375) + traceTempVariable$var59$382_1) + traceTempVariable$var63$6_3) / v2[(j + 1)]);
																								cv$temp$88$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample5Value374) + Math.log((v[j]?cv$temp$88$var68:(1.0 - cv$temp$88$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value374) + Math.log((v[j]?cv$temp$88$var68:(1.0 - cv$temp$88$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value374) + Math.log((v[j]?cv$temp$88$var68:(1.0 - cv$temp$88$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value374) + Math.log((v[j]?cv$temp$88$var68:(1.0 - cv$temp$88$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value374) + Math.log((v[j]?cv$temp$88$var68:(1.0 - cv$temp$88$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value374);
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
																						if(((i + 1) == (j + 1))) {
																							{
																								{
																									double cv$temp$89$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$375) + traceTempVariable$var59$382_1) + traceTempVariable$var63$6_3) / v2[(j + 1)]);
																										cv$temp$89$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value402) + Math.log((v[j]?cv$temp$89$var68:(1.0 - cv$temp$89$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value402) + Math.log((v[j]?cv$temp$89$var68:(1.0 - cv$temp$89$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value402) + Math.log((v[j]?cv$temp$89$var68:(1.0 - cv$temp$89$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value402) + Math.log((v[j]?cv$temp$89$var68:(1.0 - cv$temp$89$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value402) + Math.log((v[j]?cv$temp$89$var68:(1.0 - cv$temp$89$var68)))));
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
																	if(!true) {
																		for(int index$sample11$383 = 0; index$sample11$383 < weightings.length; index$sample11$383 += 1) {
																			int distributionTempVariable$var11$385 = index$sample11$383;
																			double cv$probabilitySample11Value384 = (cv$probabilitySample5Value374 * distribution$sample11[index$sample11$383]);
																			int traceTempVariable$var59$386_1 = cv$currentValue;
																			if((0 == j)) {
																				if(fixedFlag$sample27) {
																					for(int i = 0; i < size; i += 1) {
																						if(((i + 1) == (j + 1))) {
																							{
																								{
																									double cv$temp$90$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$375) + traceTempVariable$var59$386_1) + traceTempVariable$var63$6_3) / v2[(j + 1)]);
																										cv$temp$90$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample11Value384) + Math.log((v[j]?cv$temp$90$var68:(1.0 - cv$temp$90$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value384) + Math.log((v[j]?cv$temp$90$var68:(1.0 - cv$temp$90$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value384) + Math.log((v[j]?cv$temp$90$var68:(1.0 - cv$temp$90$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value384) + Math.log((v[j]?cv$temp$90$var68:(1.0 - cv$temp$90$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value384) + Math.log((v[j]?cv$temp$90$var68:(1.0 - cv$temp$90$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value384);
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
																								if(((i + 1) == (j + 1))) {
																									{
																										{
																											double cv$temp$91$var68;
																											{
																												double var68 = ((((1.0 * distributionTempVariable$v1$375) + traceTempVariable$var59$386_1) + traceTempVariable$var63$6_3) / v2[(j + 1)]);
																												cv$temp$91$var68 = var68;
																											}
																											if(((Math.log(cv$probabilitySample27Value408) + Math.log((v[j]?cv$temp$91$var68:(1.0 - cv$temp$91$var68)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value408) + Math.log((v[j]?cv$temp$91$var68:(1.0 - cv$temp$91$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value408) + Math.log((v[j]?cv$temp$91$var68:(1.0 - cv$temp$91$var68))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value408) + Math.log((v[j]?cv$temp$91$var68:(1.0 - cv$temp$91$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value408) + Math.log((v[j]?cv$temp$91$var68:(1.0 - cv$temp$91$var68)))));
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
														if(fixedFlag$sample5) {
															if(fixedFlag$sample27) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == j)) {
																		for(int index$i$428_1 = 0; index$i$428_1 < size; index$i$428_1 += 1) {
																			if(((index$i$428_1 + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$92$var68;
																						{
																							double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$6_3) / v2[(j + 1)]);
																							cv$temp$92$var68 = var68;
																						}
																						if(((Math.log(1.0) + Math.log((v[j]?cv$temp$92$var68:(1.0 - cv$temp$92$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$92$var68:(1.0 - cv$temp$92$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$92$var68:(1.0 - cv$temp$92$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$92$var68:(1.0 - cv$temp$92$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$92$var68:(1.0 - cv$temp$92$var68)))));
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
																		for(int index$sample27$418 = 0; index$sample27$418 < weightings.length; index$sample27$418 += 1) {
																			int distributionTempVariable$var27$420 = index$sample27$418;
																			double cv$probabilitySample27Value419 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$418]);
																			if(((i + 1) == j)) {
																				if(((i + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$93$var68;
																							{
																								double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$6_3) / v2[(j + 1)]);
																								cv$temp$93$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample27Value419) + Math.log((v[j]?cv$temp$93$var68:(1.0 - cv$temp$93$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value419) + Math.log((v[j]?cv$temp$93$var68:(1.0 - cv$temp$93$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value419) + Math.log((v[j]?cv$temp$93$var68:(1.0 - cv$temp$93$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value419) + Math.log((v[j]?cv$temp$93$var68:(1.0 - cv$temp$93$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value419) + Math.log((v[j]?cv$temp$93$var68:(1.0 - cv$temp$93$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value419);
																						}
																					}
																				}
																				for(int index$i$430 = 0; index$i$430 < size; index$i$430 += 1) {
																					if(!(index$i$430 == i)) {
																						for(int index$sample27$431 = 0; index$sample27$431 < weightings.length; index$sample27$431 += 1) {
																							int distributionTempVariable$var27$433 = index$sample27$431;
																							double cv$probabilitySample27Value432 = (cv$probabilitySample27Value419 * distribution$sample27[((index$i$430 - 0) / 1)][index$sample27$431]);
																							if(((index$i$430 + 1) == (j + 1))) {
																								{
																									{
																										double cv$temp$94$var68;
																										{
																											double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$6_3) / v2[(j + 1)]);
																											cv$temp$94$var68 = var68;
																										}
																										if(((Math.log(cv$probabilitySample27Value432) + Math.log((v[j]?cv$temp$94$var68:(1.0 - cv$temp$94$var68)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value432) + Math.log((v[j]?cv$temp$94$var68:(1.0 - cv$temp$94$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value432) + Math.log((v[j]?cv$temp$94$var68:(1.0 - cv$temp$94$var68))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value432) + Math.log((v[j]?cv$temp$94$var68:(1.0 - cv$temp$94$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value432) + Math.log((v[j]?cv$temp$94$var68:(1.0 - cv$temp$94$var68)))));
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
														} else {
															if(true) {
																for(int index$sample5$412 = 0; index$sample5$412 < weightings.length; index$sample5$412 += 1) {
																	int distributionTempVariable$v1$414 = index$sample5$412;
																	double cv$probabilitySample5Value413 = (1.0 * distribution$sample5[index$sample5$412]);
																	if(fixedFlag$sample27) {
																		for(int i = 0; i < size; i += 1) {
																			if(((i + 1) == j)) {
																				for(int index$i$435_1 = 0; index$i$435_1 < size; index$i$435_1 += 1) {
																					if(((index$i$435_1 + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$95$var68;
																								{
																									double var68 = ((((1.0 * distributionTempVariable$v1$414) + v2[j]) + traceTempVariable$var63$6_3) / v2[(j + 1)]);
																									cv$temp$95$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample5Value413) + Math.log((v[j]?cv$temp$95$var68:(1.0 - cv$temp$95$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value413) + Math.log((v[j]?cv$temp$95$var68:(1.0 - cv$temp$95$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value413) + Math.log((v[j]?cv$temp$95$var68:(1.0 - cv$temp$95$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value413) + Math.log((v[j]?cv$temp$95$var68:(1.0 - cv$temp$95$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value413) + Math.log((v[j]?cv$temp$95$var68:(1.0 - cv$temp$95$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value413);
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
																					if(((i + 1) == j)) {
																						if(((i + 1) == (j + 1))) {
																							{
																								{
																									double cv$temp$96$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$414) + v2[j]) + traceTempVariable$var63$6_3) / v2[(j + 1)]);
																										cv$temp$96$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value425) + Math.log((v[j]?cv$temp$96$var68:(1.0 - cv$temp$96$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value425) + Math.log((v[j]?cv$temp$96$var68:(1.0 - cv$temp$96$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value425) + Math.log((v[j]?cv$temp$96$var68:(1.0 - cv$temp$96$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value425) + Math.log((v[j]?cv$temp$96$var68:(1.0 - cv$temp$96$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value425) + Math.log((v[j]?cv$temp$96$var68:(1.0 - cv$temp$96$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value425);
																								}
																							}
																						}
																						for(int index$i$437 = 0; index$i$437 < size; index$i$437 += 1) {
																							if(!(index$i$437 == i)) {
																								for(int index$sample27$438 = 0; index$sample27$438 < weightings.length; index$sample27$438 += 1) {
																									int distributionTempVariable$var27$440 = index$sample27$438;
																									double cv$probabilitySample27Value439 = (cv$probabilitySample27Value425 * distribution$sample27[((index$i$437 - 0) / 1)][index$sample27$438]);
																									if(((index$i$437 + 1) == (j + 1))) {
																										{
																											{
																												double cv$temp$97$var68;
																												{
																													double var68 = ((((1.0 * distributionTempVariable$v1$414) + v2[j]) + traceTempVariable$var63$6_3) / v2[(j + 1)]);
																													cv$temp$97$var68 = var68;
																												}
																												if(((Math.log(cv$probabilitySample27Value439) + Math.log((v[j]?cv$temp$97$var68:(1.0 - cv$temp$97$var68)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value439) + Math.log((v[j]?cv$temp$97$var68:(1.0 - cv$temp$97$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value439) + Math.log((v[j]?cv$temp$97$var68:(1.0 - cv$temp$97$var68))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value439) + Math.log((v[j]?cv$temp$97$var68:(1.0 - cv$temp$97$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value439) + Math.log((v[j]?cv$temp$97$var68:(1.0 - cv$temp$97$var68)))));
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

	private final void sample27(int i) {
		if(true) {
			int cv$numNumStates = 0;
			int index$i$1 = i;
			{
				cv$numNumStates = Math.max(cv$numNumStates, weightings.length);
			}
			double[] cv$stateProbabilityLocal = cv$var27$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
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
					int cv$temp$1$$var4042;
					{
						cv$temp$1$$var4042 = weightings.length;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var4042))?Math.log(cv$temp$0$weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							boolean[] guard$sample27bernoulli69 = guard$sample27bernoulli69$global;
							for(int j = 0; j < size; j += 1) {
								if(((i + 1) == j))
									guard$sample27bernoulli69[((j - 0) / 1)] = false;
							}
							for(int j = 0; j < size; j += 1) {
								if(((i + 1) == (j + 1)))
									guard$sample27bernoulli69[((j - 0) / 1)] = false;
							}
							for(int k = 0; k < (size + 1); k += 1) {
								if(((i + 1) == k)) {
									for(int j = 0; j < size; j += 1) {
										if((k == (j + 1)))
											guard$sample27bernoulli69[((j - 0) / 1)] = false;
									}
								}
							}
							int traceTempVariable$var59$6_1 = cv$currentValue;
							for(int j = 0; j < size; j += 1) {
								if(((i + 1) == j)) {
									if(!guard$sample27bernoulli69[((j - 0) / 1)]) {
										guard$sample27bernoulli69[((j - 0) / 1)] = true;
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if(fixedFlag$sample5) {
													if(fixedFlag$sample11) {
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var63$17_2 = v2[k];
																if((k == (j + 1))) {
																	if((0 == (j + 1))) {
																		{
																			{
																				double cv$temp$2$var68;
																				{
																					double var68 = ((((1.0 * v1) + traceTempVariable$var59$6_1) + traceTempVariable$var63$17_2) / v2[(j + 1)]);
																					cv$temp$2$var68 = var68;
																				}
																				if(((Math.log(1.0) + Math.log((v[j]?cv$temp$2$var68:(1.0 - cv$temp$2$var68)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$2$var68:(1.0 - cv$temp$2$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$2$var68:(1.0 - cv$temp$2$var68))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$2$var68:(1.0 - cv$temp$2$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$2$var68:(1.0 - cv$temp$2$var68)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample11$18 = 0; index$sample11$18 < weightings.length; index$sample11$18 += 1) {
																int distributionTempVariable$var11$20 = index$sample11$18;
																double cv$probabilitySample11Value19 = (1.0 * distribution$sample11[index$sample11$18]);
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var63$21_2 = v2[k];
																		if((k == (j + 1))) {
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$3$var68;
																						{
																							double var68 = ((((1.0 * v1) + traceTempVariable$var59$6_1) + traceTempVariable$var63$21_2) / v2[(j + 1)]);
																							cv$temp$3$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample11Value19) + Math.log((v[j]?cv$temp$3$var68:(1.0 - cv$temp$3$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value19) + Math.log((v[j]?cv$temp$3$var68:(1.0 - cv$temp$3$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value19) + Math.log((v[j]?cv$temp$3$var68:(1.0 - cv$temp$3$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value19) + Math.log((v[j]?cv$temp$3$var68:(1.0 - cv$temp$3$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value19) + Math.log((v[j]?cv$temp$3$var68:(1.0 - cv$temp$3$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value19);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample11$29 = 0; index$sample11$29 < weightings.length; index$sample11$29 += 1) {
																					int distributionTempVariable$var11$31 = index$sample11$29;
																					double cv$probabilitySample11Value30 = (cv$probabilitySample11Value19 * distribution$sample11[index$sample11$29]);
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$4$var68;
																								{
																									double var68 = ((((1.0 * v1) + traceTempVariable$var59$6_1) + traceTempVariable$var63$21_2) / v2[(j + 1)]);
																									cv$temp$4$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample11Value30) + Math.log((v[j]?cv$temp$4$var68:(1.0 - cv$temp$4$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value30) + Math.log((v[j]?cv$temp$4$var68:(1.0 - cv$temp$4$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value30) + Math.log((v[j]?cv$temp$4$var68:(1.0 - cv$temp$4$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value30) + Math.log((v[j]?cv$temp$4$var68:(1.0 - cv$temp$4$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value30) + Math.log((v[j]?cv$temp$4$var68:(1.0 - cv$temp$4$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value30);
																							}
																						}
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
														for(int index$sample5$13 = 0; index$sample5$13 < weightings.length; index$sample5$13 += 1) {
															int distributionTempVariable$v1$15 = index$sample5$13;
															double cv$probabilitySample5Value14 = (1.0 * distribution$sample5[index$sample5$13]);
															if(fixedFlag$sample11) {
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var63$22_2 = v2[k];
																		if((k == (j + 1))) {
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$5$var68;
																						{
																							double var68 = ((((1.0 * distributionTempVariable$v1$15) + traceTempVariable$var59$6_1) + traceTempVariable$var63$22_2) / v2[(j + 1)]);
																							cv$temp$5$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample5Value14) + Math.log((v[j]?cv$temp$5$var68:(1.0 - cv$temp$5$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value14) + Math.log((v[j]?cv$temp$5$var68:(1.0 - cv$temp$5$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value14) + Math.log((v[j]?cv$temp$5$var68:(1.0 - cv$temp$5$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value14) + Math.log((v[j]?cv$temp$5$var68:(1.0 - cv$temp$5$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value14) + Math.log((v[j]?cv$temp$5$var68:(1.0 - cv$temp$5$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value14);
																					}
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample11$23 = 0; index$sample11$23 < weightings.length; index$sample11$23 += 1) {
																		int distributionTempVariable$var11$25 = index$sample11$23;
																		double cv$probabilitySample11Value24 = (cv$probabilitySample5Value14 * distribution$sample11[index$sample11$23]);
																		for(int k = 0; k < (size + 1); k += 1) {
																			if((0 == k)) {
																				int traceTempVariable$var63$26_2 = v2[k];
																				if((k == (j + 1))) {
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$6$var68;
																								{
																									double var68 = ((((1.0 * distributionTempVariable$v1$15) + traceTempVariable$var59$6_1) + traceTempVariable$var63$26_2) / v2[(j + 1)]);
																									cv$temp$6$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample11Value24) + Math.log((v[j]?cv$temp$6$var68:(1.0 - cv$temp$6$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value24) + Math.log((v[j]?cv$temp$6$var68:(1.0 - cv$temp$6$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value24) + Math.log((v[j]?cv$temp$6$var68:(1.0 - cv$temp$6$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value24) + Math.log((v[j]?cv$temp$6$var68:(1.0 - cv$temp$6$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value24) + Math.log((v[j]?cv$temp$6$var68:(1.0 - cv$temp$6$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value24);
																							}
																						}
																					}
																					if(!true) {
																						for(int index$sample11$35 = 0; index$sample11$35 < weightings.length; index$sample11$35 += 1) {
																							int distributionTempVariable$var11$37 = index$sample11$35;
																							double cv$probabilitySample11Value36 = (cv$probabilitySample11Value24 * distribution$sample11[index$sample11$35]);
																							if((0 == (j + 1))) {
																								{
																									{
																										double cv$temp$7$var68;
																										{
																											double var68 = ((((1.0 * distributionTempVariable$v1$15) + traceTempVariable$var59$6_1) + traceTempVariable$var63$26_2) / v2[(j + 1)]);
																											cv$temp$7$var68 = var68;
																										}
																										if(((Math.log(cv$probabilitySample11Value36) + Math.log((v[j]?cv$temp$7$var68:(1.0 - cv$temp$7$var68)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value36) + Math.log((v[j]?cv$temp$7$var68:(1.0 - cv$temp$7$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value36) + Math.log((v[j]?cv$temp$7$var68:(1.0 - cv$temp$7$var68))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value36) + Math.log((v[j]?cv$temp$7$var68:(1.0 - cv$temp$7$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value36) + Math.log((v[j]?cv$temp$7$var68:(1.0 - cv$temp$7$var68)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value36);
																									}
																								}
																							}
																						}
																					}
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
													int traceTempVariable$var44$44_1 = cv$currentValue;
													for(int k = 0; k < (size + 1); k += 1) {
														if(((index$i$2 + 1) == k)) {
															int traceTempVariable$var63$44_3 = traceTempVariable$var44$44_1;
															if((k == (j + 1))) {
																if(fixedFlag$sample11) {
																	if((0 == (j + 1))) {
																		{
																			{
																				double cv$temp$8$var68;
																				{
																					double var68 = ((((1.0 * v1) + traceTempVariable$var59$6_1) + traceTempVariable$var63$44_3) / v2[(j + 1)]);
																					cv$temp$8$var68 = var68;
																				}
																				if(((Math.log(1.0) + Math.log((v[j]?cv$temp$8$var68:(1.0 - cv$temp$8$var68)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$8$var68:(1.0 - cv$temp$8$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$8$var68:(1.0 - cv$temp$8$var68))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$8$var68:(1.0 - cv$temp$8$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$8$var68:(1.0 - cv$temp$8$var68)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																} else {
																	if(true) {
																		for(int index$sample11$57 = 0; index$sample11$57 < weightings.length; index$sample11$57 += 1) {
																			int distributionTempVariable$var11$59 = index$sample11$57;
																			double cv$probabilitySample11Value58 = (1.0 * distribution$sample11[index$sample11$57]);
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$9$var68;
																						{
																							double var68 = ((((1.0 * v1) + traceTempVariable$var59$6_1) + traceTempVariable$var63$44_3) / v2[(j + 1)]);
																							cv$temp$9$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample11Value58) + Math.log((v[j]?cv$temp$9$var68:(1.0 - cv$temp$9$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value58) + Math.log((v[j]?cv$temp$9$var68:(1.0 - cv$temp$9$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value58) + Math.log((v[j]?cv$temp$9$var68:(1.0 - cv$temp$9$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value58) + Math.log((v[j]?cv$temp$9$var68:(1.0 - cv$temp$9$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value58) + Math.log((v[j]?cv$temp$9$var68:(1.0 - cv$temp$9$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value58);
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$45 = 0; index$i$45 < size; index$i$45 += 1) {
														if(!(index$i$45 == index$i$2)) {
															for(int index$sample27$46 = 0; index$sample27$46 < weightings.length; index$sample27$46 += 1) {
																int distributionTempVariable$var27$48 = index$sample27$46;
																double cv$probabilitySample27Value47 = (1.0 * distribution$sample27[((index$i$45 - 0) / 1)][index$sample27$46]);
																int traceTempVariable$var44$49_1 = cv$currentValue;
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((index$i$45 + 1) == k)) {
																		int traceTempVariable$var63$49_3 = traceTempVariable$var44$49_1;
																		if((k == (j + 1))) {
																			if(fixedFlag$sample11) {
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$10$var68;
																							{
																								double var68 = ((((1.0 * v1) + traceTempVariable$var59$6_1) + traceTempVariable$var63$49_3) / v2[(j + 1)]);
																								cv$temp$10$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample27Value47) + Math.log((v[j]?cv$temp$10$var68:(1.0 - cv$temp$10$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value47) + Math.log((v[j]?cv$temp$10$var68:(1.0 - cv$temp$10$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value47) + Math.log((v[j]?cv$temp$10$var68:(1.0 - cv$temp$10$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value47) + Math.log((v[j]?cv$temp$10$var68:(1.0 - cv$temp$10$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value47) + Math.log((v[j]?cv$temp$10$var68:(1.0 - cv$temp$10$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value47);
																						}
																					}
																				}
																			} else {
																				if(true) {
																					for(int index$sample11$62 = 0; index$sample11$62 < weightings.length; index$sample11$62 += 1) {
																						int distributionTempVariable$var11$64 = index$sample11$62;
																						double cv$probabilitySample11Value63 = (cv$probabilitySample27Value47 * distribution$sample11[index$sample11$62]);
																						if((0 == (j + 1))) {
																							{
																								{
																									double cv$temp$11$var68;
																									{
																										double var68 = ((((1.0 * v1) + traceTempVariable$var59$6_1) + traceTempVariable$var63$49_3) / v2[(j + 1)]);
																										cv$temp$11$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample11Value63) + Math.log((v[j]?cv$temp$11$var68:(1.0 - cv$temp$11$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value63) + Math.log((v[j]?cv$temp$11$var68:(1.0 - cv$temp$11$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value63) + Math.log((v[j]?cv$temp$11$var68:(1.0 - cv$temp$11$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value63) + Math.log((v[j]?cv$temp$11$var68:(1.0 - cv$temp$11$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value63) + Math.log((v[j]?cv$temp$11$var68:(1.0 - cv$temp$11$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value63);
																								}
																							}
																						}
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
														for(int index$sample5$40 = 0; index$sample5$40 < weightings.length; index$sample5$40 += 1) {
															int distributionTempVariable$v1$42 = index$sample5$40;
															double cv$probabilitySample5Value41 = (1.0 * distribution$sample5[index$sample5$40]);
															int traceTempVariable$var44$50_1 = cv$currentValue;
															for(int k = 0; k < (size + 1); k += 1) {
																if(((index$i$2 + 1) == k)) {
																	int traceTempVariable$var63$50_3 = traceTempVariable$var44$50_1;
																	if((k == (j + 1))) {
																		if(fixedFlag$sample11) {
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$12$var68;
																						{
																							double var68 = ((((1.0 * distributionTempVariable$v1$42) + traceTempVariable$var59$6_1) + traceTempVariable$var63$50_3) / v2[(j + 1)]);
																							cv$temp$12$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample5Value41) + Math.log((v[j]?cv$temp$12$var68:(1.0 - cv$temp$12$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value41) + Math.log((v[j]?cv$temp$12$var68:(1.0 - cv$temp$12$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value41) + Math.log((v[j]?cv$temp$12$var68:(1.0 - cv$temp$12$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value41) + Math.log((v[j]?cv$temp$12$var68:(1.0 - cv$temp$12$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value41) + Math.log((v[j]?cv$temp$12$var68:(1.0 - cv$temp$12$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value41);
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample11$67 = 0; index$sample11$67 < weightings.length; index$sample11$67 += 1) {
																					int distributionTempVariable$var11$69 = index$sample11$67;
																					double cv$probabilitySample11Value68 = (cv$probabilitySample5Value41 * distribution$sample11[index$sample11$67]);
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$13$var68;
																								{
																									double var68 = ((((1.0 * distributionTempVariable$v1$42) + traceTempVariable$var59$6_1) + traceTempVariable$var63$50_3) / v2[(j + 1)]);
																									cv$temp$13$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample11Value68) + Math.log((v[j]?cv$temp$13$var68:(1.0 - cv$temp$13$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value68) + Math.log((v[j]?cv$temp$13$var68:(1.0 - cv$temp$13$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value68) + Math.log((v[j]?cv$temp$13$var68:(1.0 - cv$temp$13$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value68) + Math.log((v[j]?cv$temp$13$var68:(1.0 - cv$temp$13$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value68) + Math.log((v[j]?cv$temp$13$var68:(1.0 - cv$temp$13$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value68);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															for(int index$i$51 = 0; index$i$51 < size; index$i$51 += 1) {
																if(!(index$i$51 == index$i$2)) {
																	for(int index$sample27$52 = 0; index$sample27$52 < weightings.length; index$sample27$52 += 1) {
																		int distributionTempVariable$var27$54 = index$sample27$52;
																		double cv$probabilitySample27Value53 = (cv$probabilitySample5Value41 * distribution$sample27[((index$i$51 - 0) / 1)][index$sample27$52]);
																		int traceTempVariable$var44$55_1 = cv$currentValue;
																		for(int k = 0; k < (size + 1); k += 1) {
																			if(((index$i$51 + 1) == k)) {
																				int traceTempVariable$var63$55_3 = traceTempVariable$var44$55_1;
																				if((k == (j + 1))) {
																					if(fixedFlag$sample11) {
																						if((0 == (j + 1))) {
																							{
																								{
																									double cv$temp$14$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$42) + traceTempVariable$var59$6_1) + traceTempVariable$var63$55_3) / v2[(j + 1)]);
																										cv$temp$14$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value53) + Math.log((v[j]?cv$temp$14$var68:(1.0 - cv$temp$14$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value53) + Math.log((v[j]?cv$temp$14$var68:(1.0 - cv$temp$14$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value53) + Math.log((v[j]?cv$temp$14$var68:(1.0 - cv$temp$14$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value53) + Math.log((v[j]?cv$temp$14$var68:(1.0 - cv$temp$14$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value53) + Math.log((v[j]?cv$temp$14$var68:(1.0 - cv$temp$14$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value53);
																								}
																							}
																						}
																					} else {
																						if(true) {
																							for(int index$sample11$72 = 0; index$sample11$72 < weightings.length; index$sample11$72 += 1) {
																								int distributionTempVariable$var11$74 = index$sample11$72;
																								double cv$probabilitySample11Value73 = (cv$probabilitySample27Value53 * distribution$sample11[index$sample11$72]);
																								if((0 == (j + 1))) {
																									{
																										{
																											double cv$temp$15$var68;
																											{
																												double var68 = ((((1.0 * distributionTempVariable$v1$42) + traceTempVariable$var59$6_1) + traceTempVariable$var63$55_3) / v2[(j + 1)]);
																												cv$temp$15$var68 = var68;
																											}
																											if(((Math.log(cv$probabilitySample11Value73) + Math.log((v[j]?cv$temp$15$var68:(1.0 - cv$temp$15$var68)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value73) + Math.log((v[j]?cv$temp$15$var68:(1.0 - cv$temp$15$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value73) + Math.log((v[j]?cv$temp$15$var68:(1.0 - cv$temp$15$var68))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value73) + Math.log((v[j]?cv$temp$15$var68:(1.0 - cv$temp$15$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value73) + Math.log((v[j]?cv$temp$15$var68:(1.0 - cv$temp$15$var68)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value73);
																										}
																									}
																								}
																							}
																						}
																					}
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
													if(fixedFlag$sample11) {
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var63$81_2 = v2[k];
																if((k == (j + 1))) {
																	int traceTempVariable$var67$91_1 = cv$currentValue;
																	if(((index$i$2 + 1) == (j + 1))) {
																		{
																			{
																				double cv$temp$16$var68;
																				{
																					double var68 = ((((1.0 * v1) + traceTempVariable$var59$6_1) + traceTempVariable$var63$81_2) / traceTempVariable$var67$91_1);
																					cv$temp$16$var68 = var68;
																				}
																				if(((Math.log(1.0) + Math.log((v[j]?cv$temp$16$var68:(1.0 - cv$temp$16$var68)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$16$var68:(1.0 - cv$temp$16$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$16$var68:(1.0 - cv$temp$16$var68))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$16$var68:(1.0 - cv$temp$16$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$16$var68:(1.0 - cv$temp$16$var68)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																	for(int index$i$92 = 0; index$i$92 < size; index$i$92 += 1) {
																		if(!(index$i$92 == index$i$2)) {
																			for(int index$sample27$93 = 0; index$sample27$93 < weightings.length; index$sample27$93 += 1) {
																				int distributionTempVariable$var27$95 = index$sample27$93;
																				double cv$probabilitySample27Value94 = (1.0 * distribution$sample27[((index$i$92 - 0) / 1)][index$sample27$93]);
																				int traceTempVariable$var67$96_1 = cv$currentValue;
																				if(((index$i$92 + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$17$var68;
																							{
																								double var68 = ((((1.0 * v1) + traceTempVariable$var59$6_1) + traceTempVariable$var63$81_2) / traceTempVariable$var67$96_1);
																								cv$temp$17$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample27Value94) + Math.log((v[j]?cv$temp$17$var68:(1.0 - cv$temp$17$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value94) + Math.log((v[j]?cv$temp$17$var68:(1.0 - cv$temp$17$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value94) + Math.log((v[j]?cv$temp$17$var68:(1.0 - cv$temp$17$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value94) + Math.log((v[j]?cv$temp$17$var68:(1.0 - cv$temp$17$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value94) + Math.log((v[j]?cv$temp$17$var68:(1.0 - cv$temp$17$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value94);
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
															for(int index$sample11$82 = 0; index$sample11$82 < weightings.length; index$sample11$82 += 1) {
																int distributionTempVariable$var11$84 = index$sample11$82;
																double cv$probabilitySample11Value83 = (1.0 * distribution$sample11[index$sample11$82]);
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var63$85_2 = v2[k];
																		if((k == (j + 1))) {
																			int traceTempVariable$var67$97_1 = cv$currentValue;
																			if(((index$i$2 + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$18$var68;
																						{
																							double var68 = ((((1.0 * v1) + traceTempVariable$var59$6_1) + traceTempVariable$var63$85_2) / traceTempVariable$var67$97_1);
																							cv$temp$18$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample11Value83) + Math.log((v[j]?cv$temp$18$var68:(1.0 - cv$temp$18$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value83) + Math.log((v[j]?cv$temp$18$var68:(1.0 - cv$temp$18$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value83) + Math.log((v[j]?cv$temp$18$var68:(1.0 - cv$temp$18$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value83) + Math.log((v[j]?cv$temp$18$var68:(1.0 - cv$temp$18$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value83) + Math.log((v[j]?cv$temp$18$var68:(1.0 - cv$temp$18$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value83);
																					}
																				}
																			}
																			for(int index$i$98 = 0; index$i$98 < size; index$i$98 += 1) {
																				if(!(index$i$98 == index$i$2)) {
																					for(int index$sample27$99 = 0; index$sample27$99 < weightings.length; index$sample27$99 += 1) {
																						int distributionTempVariable$var27$101 = index$sample27$99;
																						double cv$probabilitySample27Value100 = (cv$probabilitySample11Value83 * distribution$sample27[((index$i$98 - 0) / 1)][index$sample27$99]);
																						int traceTempVariable$var67$102_1 = cv$currentValue;
																						if(((index$i$98 + 1) == (j + 1))) {
																							{
																								{
																									double cv$temp$19$var68;
																									{
																										double var68 = ((((1.0 * v1) + traceTempVariable$var59$6_1) + traceTempVariable$var63$85_2) / traceTempVariable$var67$102_1);
																										cv$temp$19$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value100) + Math.log((v[j]?cv$temp$19$var68:(1.0 - cv$temp$19$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value100) + Math.log((v[j]?cv$temp$19$var68:(1.0 - cv$temp$19$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value100) + Math.log((v[j]?cv$temp$19$var68:(1.0 - cv$temp$19$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value100) + Math.log((v[j]?cv$temp$19$var68:(1.0 - cv$temp$19$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value100) + Math.log((v[j]?cv$temp$19$var68:(1.0 - cv$temp$19$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value100);
																								}
																							}
																						}
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
														for(int index$sample5$77 = 0; index$sample5$77 < weightings.length; index$sample5$77 += 1) {
															int distributionTempVariable$v1$79 = index$sample5$77;
															double cv$probabilitySample5Value78 = (1.0 * distribution$sample5[index$sample5$77]);
															if(fixedFlag$sample11) {
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var63$86_2 = v2[k];
																		if((k == (j + 1))) {
																			int traceTempVariable$var67$103_1 = cv$currentValue;
																			if(((index$i$2 + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$20$var68;
																						{
																							double var68 = ((((1.0 * distributionTempVariable$v1$79) + traceTempVariable$var59$6_1) + traceTempVariable$var63$86_2) / traceTempVariable$var67$103_1);
																							cv$temp$20$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample5Value78) + Math.log((v[j]?cv$temp$20$var68:(1.0 - cv$temp$20$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value78) + Math.log((v[j]?cv$temp$20$var68:(1.0 - cv$temp$20$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value78) + Math.log((v[j]?cv$temp$20$var68:(1.0 - cv$temp$20$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value78) + Math.log((v[j]?cv$temp$20$var68:(1.0 - cv$temp$20$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value78) + Math.log((v[j]?cv$temp$20$var68:(1.0 - cv$temp$20$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value78);
																					}
																				}
																			}
																			for(int index$i$104 = 0; index$i$104 < size; index$i$104 += 1) {
																				if(!(index$i$104 == index$i$2)) {
																					for(int index$sample27$105 = 0; index$sample27$105 < weightings.length; index$sample27$105 += 1) {
																						int distributionTempVariable$var27$107 = index$sample27$105;
																						double cv$probabilitySample27Value106 = (cv$probabilitySample5Value78 * distribution$sample27[((index$i$104 - 0) / 1)][index$sample27$105]);
																						int traceTempVariable$var67$108_1 = cv$currentValue;
																						if(((index$i$104 + 1) == (j + 1))) {
																							{
																								{
																									double cv$temp$21$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$79) + traceTempVariable$var59$6_1) + traceTempVariable$var63$86_2) / traceTempVariable$var67$108_1);
																										cv$temp$21$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value106) + Math.log((v[j]?cv$temp$21$var68:(1.0 - cv$temp$21$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value106) + Math.log((v[j]?cv$temp$21$var68:(1.0 - cv$temp$21$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value106) + Math.log((v[j]?cv$temp$21$var68:(1.0 - cv$temp$21$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value106) + Math.log((v[j]?cv$temp$21$var68:(1.0 - cv$temp$21$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value106) + Math.log((v[j]?cv$temp$21$var68:(1.0 - cv$temp$21$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value106);
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
																	for(int index$sample11$87 = 0; index$sample11$87 < weightings.length; index$sample11$87 += 1) {
																		int distributionTempVariable$var11$89 = index$sample11$87;
																		double cv$probabilitySample11Value88 = (cv$probabilitySample5Value78 * distribution$sample11[index$sample11$87]);
																		for(int k = 0; k < (size + 1); k += 1) {
																			if((0 == k)) {
																				int traceTempVariable$var63$90_2 = v2[k];
																				if((k == (j + 1))) {
																					int traceTempVariable$var67$109_1 = cv$currentValue;
																					if(((index$i$2 + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$22$var68;
																								{
																									double var68 = ((((1.0 * distributionTempVariable$v1$79) + traceTempVariable$var59$6_1) + traceTempVariable$var63$90_2) / traceTempVariable$var67$109_1);
																									cv$temp$22$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample11Value88) + Math.log((v[j]?cv$temp$22$var68:(1.0 - cv$temp$22$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value88) + Math.log((v[j]?cv$temp$22$var68:(1.0 - cv$temp$22$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value88) + Math.log((v[j]?cv$temp$22$var68:(1.0 - cv$temp$22$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value88) + Math.log((v[j]?cv$temp$22$var68:(1.0 - cv$temp$22$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value88) + Math.log((v[j]?cv$temp$22$var68:(1.0 - cv$temp$22$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value88);
																							}
																						}
																					}
																					for(int index$i$110 = 0; index$i$110 < size; index$i$110 += 1) {
																						if(!(index$i$110 == index$i$2)) {
																							for(int index$sample27$111 = 0; index$sample27$111 < weightings.length; index$sample27$111 += 1) {
																								int distributionTempVariable$var27$113 = index$sample27$111;
																								double cv$probabilitySample27Value112 = (cv$probabilitySample11Value88 * distribution$sample27[((index$i$110 - 0) / 1)][index$sample27$111]);
																								int traceTempVariable$var67$114_1 = cv$currentValue;
																								if(((index$i$110 + 1) == (j + 1))) {
																									{
																										{
																											double cv$temp$23$var68;
																											{
																												double var68 = ((((1.0 * distributionTempVariable$v1$79) + traceTempVariable$var59$6_1) + traceTempVariable$var63$90_2) / traceTempVariable$var67$114_1);
																												cv$temp$23$var68 = var68;
																											}
																											if(((Math.log(cv$probabilitySample27Value112) + Math.log((v[j]?cv$temp$23$var68:(1.0 - cv$temp$23$var68)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value112) + Math.log((v[j]?cv$temp$23$var68:(1.0 - cv$temp$23$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value112) + Math.log((v[j]?cv$temp$23$var68:(1.0 - cv$temp$23$var68))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value112) + Math.log((v[j]?cv$temp$23$var68:(1.0 - cv$temp$23$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value112) + Math.log((v[j]?cv$temp$23$var68:(1.0 - cv$temp$23$var68)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value112);
																										}
																									}
																								}
																							}
																						}
																					}
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
													int traceTempVariable$var44$120_1 = cv$currentValue;
													for(int k = 0; k < (size + 1); k += 1) {
														if(((index$i$2 + 1) == k)) {
															int traceTempVariable$var63$120_3 = traceTempVariable$var44$120_1;
															if((k == (j + 1))) {
																int traceTempVariable$var67$132_1 = cv$currentValue;
																if(((index$i$2 + 1) == (j + 1))) {
																	{
																		{
																			double cv$temp$24$var68;
																			{
																				double var68 = ((((1.0 * v1) + traceTempVariable$var59$6_1) + traceTempVariable$var63$120_3) / traceTempVariable$var67$132_1);
																				cv$temp$24$var68 = var68;
																			}
																			if(((Math.log(1.0) + Math.log((v[j]?cv$temp$24$var68:(1.0 - cv$temp$24$var68)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$24$var68:(1.0 - cv$temp$24$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$24$var68:(1.0 - cv$temp$24$var68))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$24$var68:(1.0 - cv$temp$24$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$24$var68:(1.0 - cv$temp$24$var68)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
																for(int index$i$133 = 0; index$i$133 < size; index$i$133 += 1) {
																	if(!(index$i$133 == index$i$2)) {
																		for(int index$sample27$134 = 0; index$sample27$134 < weightings.length; index$sample27$134 += 1) {
																			int distributionTempVariable$var27$136 = index$sample27$134;
																			double cv$probabilitySample27Value135 = (1.0 * distribution$sample27[((index$i$133 - 0) / 1)][index$sample27$134]);
																			int traceTempVariable$var67$137_1 = cv$currentValue;
																			if(((index$i$133 + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$25$var68;
																						{
																							double var68 = ((((1.0 * v1) + traceTempVariable$var59$6_1) + traceTempVariable$var63$120_3) / traceTempVariable$var67$137_1);
																							cv$temp$25$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample27Value135) + Math.log((v[j]?cv$temp$25$var68:(1.0 - cv$temp$25$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value135) + Math.log((v[j]?cv$temp$25$var68:(1.0 - cv$temp$25$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value135) + Math.log((v[j]?cv$temp$25$var68:(1.0 - cv$temp$25$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value135) + Math.log((v[j]?cv$temp$25$var68:(1.0 - cv$temp$25$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value135) + Math.log((v[j]?cv$temp$25$var68:(1.0 - cv$temp$25$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value135);
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$121 = 0; index$i$121 < size; index$i$121 += 1) {
														if(!(index$i$121 == index$i$2)) {
															for(int index$sample27$122 = 0; index$sample27$122 < weightings.length; index$sample27$122 += 1) {
																int distributionTempVariable$var27$124 = index$sample27$122;
																double cv$probabilitySample27Value123 = (1.0 * distribution$sample27[((index$i$121 - 0) / 1)][index$sample27$122]);
																int traceTempVariable$var44$125_1 = cv$currentValue;
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((index$i$121 + 1) == k)) {
																		int traceTempVariable$var63$125_3 = traceTempVariable$var44$125_1;
																		if((k == (j + 1))) {
																			int traceTempVariable$var67$138_1 = cv$currentValue;
																			if(((index$i$2 + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$26$var68;
																						{
																							double var68 = ((((1.0 * v1) + traceTempVariable$var59$6_1) + traceTempVariable$var63$125_3) / traceTempVariable$var67$138_1);
																							cv$temp$26$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample27Value123) + Math.log((v[j]?cv$temp$26$var68:(1.0 - cv$temp$26$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value123) + Math.log((v[j]?cv$temp$26$var68:(1.0 - cv$temp$26$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value123) + Math.log((v[j]?cv$temp$26$var68:(1.0 - cv$temp$26$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value123) + Math.log((v[j]?cv$temp$26$var68:(1.0 - cv$temp$26$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value123) + Math.log((v[j]?cv$temp$26$var68:(1.0 - cv$temp$26$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value123);
																					}
																				}
																			}
																			int traceTempVariable$var67$139_1 = cv$currentValue;
																			if(((index$i$121 + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$27$var68;
																						{
																							double var68 = ((((1.0 * v1) + traceTempVariable$var59$6_1) + traceTempVariable$var63$125_3) / traceTempVariable$var67$139_1);
																							cv$temp$27$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample27Value123) + Math.log((v[j]?cv$temp$27$var68:(1.0 - cv$temp$27$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value123) + Math.log((v[j]?cv$temp$27$var68:(1.0 - cv$temp$27$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value123) + Math.log((v[j]?cv$temp$27$var68:(1.0 - cv$temp$27$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value123) + Math.log((v[j]?cv$temp$27$var68:(1.0 - cv$temp$27$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value123) + Math.log((v[j]?cv$temp$27$var68:(1.0 - cv$temp$27$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value123);
																					}
																				}
																			}
																			for(int index$i$140 = 0; index$i$140 < size; index$i$140 += 1) {
																				if((!(index$i$140 == index$i$2) && !(index$i$140 == index$i$121))) {
																					for(int index$sample27$141 = 0; index$sample27$141 < weightings.length; index$sample27$141 += 1) {
																						int distributionTempVariable$var27$143 = index$sample27$141;
																						double cv$probabilitySample27Value142 = (cv$probabilitySample27Value123 * distribution$sample27[((index$i$140 - 0) / 1)][index$sample27$141]);
																						int traceTempVariable$var67$144_1 = cv$currentValue;
																						if(((index$i$140 + 1) == (j + 1))) {
																							{
																								{
																									double cv$temp$28$var68;
																									{
																										double var68 = ((((1.0 * v1) + traceTempVariable$var59$6_1) + traceTempVariable$var63$125_3) / traceTempVariable$var67$144_1);
																										cv$temp$28$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value142) + Math.log((v[j]?cv$temp$28$var68:(1.0 - cv$temp$28$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value142) + Math.log((v[j]?cv$temp$28$var68:(1.0 - cv$temp$28$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value142) + Math.log((v[j]?cv$temp$28$var68:(1.0 - cv$temp$28$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value142) + Math.log((v[j]?cv$temp$28$var68:(1.0 - cv$temp$28$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value142) + Math.log((v[j]?cv$temp$28$var68:(1.0 - cv$temp$28$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value142);
																								}
																							}
																						}
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
														for(int index$sample5$116 = 0; index$sample5$116 < weightings.length; index$sample5$116 += 1) {
															int distributionTempVariable$v1$118 = index$sample5$116;
															double cv$probabilitySample5Value117 = (1.0 * distribution$sample5[index$sample5$116]);
															int traceTempVariable$var44$126_1 = cv$currentValue;
															for(int k = 0; k < (size + 1); k += 1) {
																if(((index$i$2 + 1) == k)) {
																	int traceTempVariable$var63$126_3 = traceTempVariable$var44$126_1;
																	if((k == (j + 1))) {
																		int traceTempVariable$var67$145_1 = cv$currentValue;
																		if(((index$i$2 + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$29$var68;
																					{
																						double var68 = ((((1.0 * distributionTempVariable$v1$118) + traceTempVariable$var59$6_1) + traceTempVariable$var63$126_3) / traceTempVariable$var67$145_1);
																						cv$temp$29$var68 = var68;
																					}
																					if(((Math.log(cv$probabilitySample5Value117) + Math.log((v[j]?cv$temp$29$var68:(1.0 - cv$temp$29$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value117) + Math.log((v[j]?cv$temp$29$var68:(1.0 - cv$temp$29$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value117) + Math.log((v[j]?cv$temp$29$var68:(1.0 - cv$temp$29$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value117) + Math.log((v[j]?cv$temp$29$var68:(1.0 - cv$temp$29$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value117) + Math.log((v[j]?cv$temp$29$var68:(1.0 - cv$temp$29$var68)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value117);
																				}
																			}
																		}
																		for(int index$i$146 = 0; index$i$146 < size; index$i$146 += 1) {
																			if(!(index$i$146 == index$i$2)) {
																				for(int index$sample27$147 = 0; index$sample27$147 < weightings.length; index$sample27$147 += 1) {
																					int distributionTempVariable$var27$149 = index$sample27$147;
																					double cv$probabilitySample27Value148 = (cv$probabilitySample5Value117 * distribution$sample27[((index$i$146 - 0) / 1)][index$sample27$147]);
																					int traceTempVariable$var67$150_1 = cv$currentValue;
																					if(((index$i$146 + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$30$var68;
																								{
																									double var68 = ((((1.0 * distributionTempVariable$v1$118) + traceTempVariable$var59$6_1) + traceTempVariable$var63$126_3) / traceTempVariable$var67$150_1);
																									cv$temp$30$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample27Value148) + Math.log((v[j]?cv$temp$30$var68:(1.0 - cv$temp$30$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value148) + Math.log((v[j]?cv$temp$30$var68:(1.0 - cv$temp$30$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value148) + Math.log((v[j]?cv$temp$30$var68:(1.0 - cv$temp$30$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value148) + Math.log((v[j]?cv$temp$30$var68:(1.0 - cv$temp$30$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value148) + Math.log((v[j]?cv$temp$30$var68:(1.0 - cv$temp$30$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value148);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															for(int index$i$127 = 0; index$i$127 < size; index$i$127 += 1) {
																if(!(index$i$127 == index$i$2)) {
																	for(int index$sample27$128 = 0; index$sample27$128 < weightings.length; index$sample27$128 += 1) {
																		int distributionTempVariable$var27$130 = index$sample27$128;
																		double cv$probabilitySample27Value129 = (cv$probabilitySample5Value117 * distribution$sample27[((index$i$127 - 0) / 1)][index$sample27$128]);
																		int traceTempVariable$var44$131_1 = cv$currentValue;
																		for(int k = 0; k < (size + 1); k += 1) {
																			if(((index$i$127 + 1) == k)) {
																				int traceTempVariable$var63$131_3 = traceTempVariable$var44$131_1;
																				if((k == (j + 1))) {
																					int traceTempVariable$var67$151_1 = cv$currentValue;
																					if(((index$i$2 + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$31$var68;
																								{
																									double var68 = ((((1.0 * distributionTempVariable$v1$118) + traceTempVariable$var59$6_1) + traceTempVariable$var63$131_3) / traceTempVariable$var67$151_1);
																									cv$temp$31$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample27Value129) + Math.log((v[j]?cv$temp$31$var68:(1.0 - cv$temp$31$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value129) + Math.log((v[j]?cv$temp$31$var68:(1.0 - cv$temp$31$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value129) + Math.log((v[j]?cv$temp$31$var68:(1.0 - cv$temp$31$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value129) + Math.log((v[j]?cv$temp$31$var68:(1.0 - cv$temp$31$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value129) + Math.log((v[j]?cv$temp$31$var68:(1.0 - cv$temp$31$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value129);
																							}
																						}
																					}
																					int traceTempVariable$var67$152_1 = cv$currentValue;
																					if(((index$i$127 + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$32$var68;
																								{
																									double var68 = ((((1.0 * distributionTempVariable$v1$118) + traceTempVariable$var59$6_1) + traceTempVariable$var63$131_3) / traceTempVariable$var67$152_1);
																									cv$temp$32$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample27Value129) + Math.log((v[j]?cv$temp$32$var68:(1.0 - cv$temp$32$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value129) + Math.log((v[j]?cv$temp$32$var68:(1.0 - cv$temp$32$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value129) + Math.log((v[j]?cv$temp$32$var68:(1.0 - cv$temp$32$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value129) + Math.log((v[j]?cv$temp$32$var68:(1.0 - cv$temp$32$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value129) + Math.log((v[j]?cv$temp$32$var68:(1.0 - cv$temp$32$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value129);
																							}
																						}
																					}
																					for(int index$i$153 = 0; index$i$153 < size; index$i$153 += 1) {
																						if((!(index$i$153 == index$i$2) && !(index$i$153 == index$i$127))) {
																							for(int index$sample27$154 = 0; index$sample27$154 < weightings.length; index$sample27$154 += 1) {
																								int distributionTempVariable$var27$156 = index$sample27$154;
																								double cv$probabilitySample27Value155 = (cv$probabilitySample27Value129 * distribution$sample27[((index$i$153 - 0) / 1)][index$sample27$154]);
																								int traceTempVariable$var67$157_1 = cv$currentValue;
																								if(((index$i$153 + 1) == (j + 1))) {
																									{
																										{
																											double cv$temp$33$var68;
																											{
																												double var68 = ((((1.0 * distributionTempVariable$v1$118) + traceTempVariable$var59$6_1) + traceTempVariable$var63$131_3) / traceTempVariable$var67$157_1);
																												cv$temp$33$var68 = var68;
																											}
																											if(((Math.log(cv$probabilitySample27Value155) + Math.log((v[j]?cv$temp$33$var68:(1.0 - cv$temp$33$var68)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value155) + Math.log((v[j]?cv$temp$33$var68:(1.0 - cv$temp$33$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value155) + Math.log((v[j]?cv$temp$33$var68:(1.0 - cv$temp$33$var68))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value155) + Math.log((v[j]?cv$temp$33$var68:(1.0 - cv$temp$33$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value155) + Math.log((v[j]?cv$temp$33$var68:(1.0 - cv$temp$33$var68)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value155);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
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
							int traceTempVariable$var67$7_1 = cv$currentValue;
							for(int j = 0; j < size; j += 1) {
								if(((i + 1) == (j + 1))) {
									if(!guard$sample27bernoulli69[((j - 0) / 1)]) {
										guard$sample27bernoulli69[((j - 0) / 1)] = true;
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if(fixedFlag$sample5) {
													if(fixedFlag$sample11) {
														if((0 == j)) {
															for(int k = 0; k < (size + 1); k += 1) {
																if((0 == k)) {
																	int traceTempVariable$var63$173_2 = v2[k];
																	if((k == (j + 1))) {
																		{
																			{
																				double cv$temp$34$var68;
																				{
																					double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$173_2) / traceTempVariable$var67$7_1);
																					cv$temp$34$var68 = var68;
																				}
																				if(((Math.log(1.0) + Math.log((v[j]?cv$temp$34$var68:(1.0 - cv$temp$34$var68)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$34$var68:(1.0 - cv$temp$34$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$34$var68:(1.0 - cv$temp$34$var68))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$34$var68:(1.0 - cv$temp$34$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$34$var68:(1.0 - cv$temp$34$var68)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample11$164 = 0; index$sample11$164 < weightings.length; index$sample11$164 += 1) {
																int distributionTempVariable$var11$166 = index$sample11$164;
																double cv$probabilitySample11Value165 = (1.0 * distribution$sample11[index$sample11$164]);
																if((0 == j)) {
																	for(int k = 0; k < (size + 1); k += 1) {
																		if((0 == k)) {
																			int traceTempVariable$var63$174_2 = v2[k];
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$35$var68;
																						{
																							double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$174_2) / traceTempVariable$var67$7_1);
																							cv$temp$35$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample11Value165) + Math.log((v[j]?cv$temp$35$var68:(1.0 - cv$temp$35$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value165) + Math.log((v[j]?cv$temp$35$var68:(1.0 - cv$temp$35$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value165) + Math.log((v[j]?cv$temp$35$var68:(1.0 - cv$temp$35$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value165) + Math.log((v[j]?cv$temp$35$var68:(1.0 - cv$temp$35$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value165) + Math.log((v[j]?cv$temp$35$var68:(1.0 - cv$temp$35$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value165);
																					}
																				}
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample11$175 = 0; index$sample11$175 < weightings.length; index$sample11$175 += 1) {
																			int distributionTempVariable$var11$177 = index$sample11$175;
																			double cv$probabilitySample11Value176 = (cv$probabilitySample11Value165 * distribution$sample11[index$sample11$175]);
																			for(int k = 0; k < (size + 1); k += 1) {
																				if((0 == k)) {
																					int traceTempVariable$var63$178_2 = v2[k];
																					if((k == (j + 1))) {
																						{
																							{
																								double cv$temp$36$var68;
																								{
																									double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$178_2) / traceTempVariable$var67$7_1);
																									cv$temp$36$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample11Value176) + Math.log((v[j]?cv$temp$36$var68:(1.0 - cv$temp$36$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value176) + Math.log((v[j]?cv$temp$36$var68:(1.0 - cv$temp$36$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value176) + Math.log((v[j]?cv$temp$36$var68:(1.0 - cv$temp$36$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value176) + Math.log((v[j]?cv$temp$36$var68:(1.0 - cv$temp$36$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value176) + Math.log((v[j]?cv$temp$36$var68:(1.0 - cv$temp$36$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value176);
																							}
																						}
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
														for(int index$sample5$159 = 0; index$sample5$159 < weightings.length; index$sample5$159 += 1) {
															int distributionTempVariable$v1$161 = index$sample5$159;
															double cv$probabilitySample5Value160 = (1.0 * distribution$sample5[index$sample5$159]);
															if(fixedFlag$sample11) {
																if((0 == j)) {
																	for(int k = 0; k < (size + 1); k += 1) {
																		if((0 == k)) {
																			int traceTempVariable$var63$179_2 = v2[k];
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$37$var68;
																						{
																							double var68 = ((((1.0 * distributionTempVariable$v1$161) + v2[j]) + traceTempVariable$var63$179_2) / traceTempVariable$var67$7_1);
																							cv$temp$37$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample5Value160) + Math.log((v[j]?cv$temp$37$var68:(1.0 - cv$temp$37$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value160) + Math.log((v[j]?cv$temp$37$var68:(1.0 - cv$temp$37$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value160) + Math.log((v[j]?cv$temp$37$var68:(1.0 - cv$temp$37$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value160) + Math.log((v[j]?cv$temp$37$var68:(1.0 - cv$temp$37$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value160) + Math.log((v[j]?cv$temp$37$var68:(1.0 - cv$temp$37$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value160);
																					}
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample11$169 = 0; index$sample11$169 < weightings.length; index$sample11$169 += 1) {
																		int distributionTempVariable$var11$171 = index$sample11$169;
																		double cv$probabilitySample11Value170 = (cv$probabilitySample5Value160 * distribution$sample11[index$sample11$169]);
																		if((0 == j)) {
																			for(int k = 0; k < (size + 1); k += 1) {
																				if((0 == k)) {
																					int traceTempVariable$var63$180_2 = v2[k];
																					if((k == (j + 1))) {
																						{
																							{
																								double cv$temp$38$var68;
																								{
																									double var68 = ((((1.0 * distributionTempVariable$v1$161) + v2[j]) + traceTempVariable$var63$180_2) / traceTempVariable$var67$7_1);
																									cv$temp$38$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample11Value170) + Math.log((v[j]?cv$temp$38$var68:(1.0 - cv$temp$38$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value170) + Math.log((v[j]?cv$temp$38$var68:(1.0 - cv$temp$38$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value170) + Math.log((v[j]?cv$temp$38$var68:(1.0 - cv$temp$38$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value170) + Math.log((v[j]?cv$temp$38$var68:(1.0 - cv$temp$38$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value170) + Math.log((v[j]?cv$temp$38$var68:(1.0 - cv$temp$38$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value170);
																							}
																						}
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample11$181 = 0; index$sample11$181 < weightings.length; index$sample11$181 += 1) {
																					int distributionTempVariable$var11$183 = index$sample11$181;
																					double cv$probabilitySample11Value182 = (cv$probabilitySample11Value170 * distribution$sample11[index$sample11$181]);
																					for(int k = 0; k < (size + 1); k += 1) {
																						if((0 == k)) {
																							int traceTempVariable$var63$184_2 = v2[k];
																							if((k == (j + 1))) {
																								{
																									{
																										double cv$temp$39$var68;
																										{
																											double var68 = ((((1.0 * distributionTempVariable$v1$161) + v2[j]) + traceTempVariable$var63$184_2) / traceTempVariable$var67$7_1);
																											cv$temp$39$var68 = var68;
																										}
																										if(((Math.log(cv$probabilitySample11Value182) + Math.log((v[j]?cv$temp$39$var68:(1.0 - cv$temp$39$var68)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value182) + Math.log((v[j]?cv$temp$39$var68:(1.0 - cv$temp$39$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value182) + Math.log((v[j]?cv$temp$39$var68:(1.0 - cv$temp$39$var68))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value182) + Math.log((v[j]?cv$temp$39$var68:(1.0 - cv$temp$39$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value182) + Math.log((v[j]?cv$temp$39$var68:(1.0 - cv$temp$39$var68)))));
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
																}
															}
														}
													}
												}
												if(fixedFlag$sample5) {
													if(fixedFlag$sample11) {
														if((0 == j)) {
															int traceTempVariable$var44$200_1 = cv$currentValue;
															for(int k = 0; k < (size + 1); k += 1) {
																if(((index$i$2 + 1) == k)) {
																	int traceTempVariable$var63$200_3 = traceTempVariable$var44$200_1;
																	if((k == (j + 1))) {
																		{
																			{
																				double cv$temp$40$var68;
																				{
																					double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$200_3) / traceTempVariable$var67$7_1);
																					cv$temp$40$var68 = var68;
																				}
																				if(((Math.log(1.0) + Math.log((v[j]?cv$temp$40$var68:(1.0 - cv$temp$40$var68)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$40$var68:(1.0 - cv$temp$40$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$40$var68:(1.0 - cv$temp$40$var68))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$40$var68:(1.0 - cv$temp$40$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$40$var68:(1.0 - cv$temp$40$var68)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
															for(int index$i$201 = 0; index$i$201 < size; index$i$201 += 1) {
																if(!(index$i$201 == index$i$2)) {
																	for(int index$sample27$202 = 0; index$sample27$202 < weightings.length; index$sample27$202 += 1) {
																		int distributionTempVariable$var27$204 = index$sample27$202;
																		double cv$probabilitySample27Value203 = (1.0 * distribution$sample27[((index$i$201 - 0) / 1)][index$sample27$202]);
																		int traceTempVariable$var44$205_1 = cv$currentValue;
																		for(int k = 0; k < (size + 1); k += 1) {
																			if(((index$i$201 + 1) == k)) {
																				int traceTempVariable$var63$205_3 = traceTempVariable$var44$205_1;
																				if((k == (j + 1))) {
																					{
																						{
																							double cv$temp$41$var68;
																							{
																								double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$205_3) / traceTempVariable$var67$7_1);
																								cv$temp$41$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample27Value203) + Math.log((v[j]?cv$temp$41$var68:(1.0 - cv$temp$41$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value203) + Math.log((v[j]?cv$temp$41$var68:(1.0 - cv$temp$41$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value203) + Math.log((v[j]?cv$temp$41$var68:(1.0 - cv$temp$41$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value203) + Math.log((v[j]?cv$temp$41$var68:(1.0 - cv$temp$41$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value203) + Math.log((v[j]?cv$temp$41$var68:(1.0 - cv$temp$41$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value203);
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
															for(int index$sample11$191 = 0; index$sample11$191 < weightings.length; index$sample11$191 += 1) {
																int distributionTempVariable$var11$193 = index$sample11$191;
																double cv$probabilitySample11Value192 = (1.0 * distribution$sample11[index$sample11$191]);
																if((0 == j)) {
																	int traceTempVariable$var44$206_1 = cv$currentValue;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if(((index$i$2 + 1) == k)) {
																			int traceTempVariable$var63$206_3 = traceTempVariable$var44$206_1;
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$42$var68;
																						{
																							double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$206_3) / traceTempVariable$var67$7_1);
																							cv$temp$42$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample11Value192) + Math.log((v[j]?cv$temp$42$var68:(1.0 - cv$temp$42$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value192) + Math.log((v[j]?cv$temp$42$var68:(1.0 - cv$temp$42$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value192) + Math.log((v[j]?cv$temp$42$var68:(1.0 - cv$temp$42$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value192) + Math.log((v[j]?cv$temp$42$var68:(1.0 - cv$temp$42$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value192) + Math.log((v[j]?cv$temp$42$var68:(1.0 - cv$temp$42$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value192);
																					}
																				}
																			}
																		}
																	}
																	for(int index$i$207 = 0; index$i$207 < size; index$i$207 += 1) {
																		if(!(index$i$207 == index$i$2)) {
																			for(int index$sample27$208 = 0; index$sample27$208 < weightings.length; index$sample27$208 += 1) {
																				int distributionTempVariable$var27$210 = index$sample27$208;
																				double cv$probabilitySample27Value209 = (cv$probabilitySample11Value192 * distribution$sample27[((index$i$207 - 0) / 1)][index$sample27$208]);
																				int traceTempVariable$var44$211_1 = cv$currentValue;
																				for(int k = 0; k < (size + 1); k += 1) {
																					if(((index$i$207 + 1) == k)) {
																						int traceTempVariable$var63$211_3 = traceTempVariable$var44$211_1;
																						if((k == (j + 1))) {
																							{
																								{
																									double cv$temp$43$var68;
																									{
																										double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$211_3) / traceTempVariable$var67$7_1);
																										cv$temp$43$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value209) + Math.log((v[j]?cv$temp$43$var68:(1.0 - cv$temp$43$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value209) + Math.log((v[j]?cv$temp$43$var68:(1.0 - cv$temp$43$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value209) + Math.log((v[j]?cv$temp$43$var68:(1.0 - cv$temp$43$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value209) + Math.log((v[j]?cv$temp$43$var68:(1.0 - cv$temp$43$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value209) + Math.log((v[j]?cv$temp$43$var68:(1.0 - cv$temp$43$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value209);
																								}
																							}
																						}
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
														for(int index$sample5$186 = 0; index$sample5$186 < weightings.length; index$sample5$186 += 1) {
															int distributionTempVariable$v1$188 = index$sample5$186;
															double cv$probabilitySample5Value187 = (1.0 * distribution$sample5[index$sample5$186]);
															if(fixedFlag$sample11) {
																if((0 == j)) {
																	int traceTempVariable$var44$212_1 = cv$currentValue;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if(((index$i$2 + 1) == k)) {
																			int traceTempVariable$var63$212_3 = traceTempVariable$var44$212_1;
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$44$var68;
																						{
																							double var68 = ((((1.0 * distributionTempVariable$v1$188) + v2[j]) + traceTempVariable$var63$212_3) / traceTempVariable$var67$7_1);
																							cv$temp$44$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample5Value187) + Math.log((v[j]?cv$temp$44$var68:(1.0 - cv$temp$44$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value187) + Math.log((v[j]?cv$temp$44$var68:(1.0 - cv$temp$44$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value187) + Math.log((v[j]?cv$temp$44$var68:(1.0 - cv$temp$44$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value187) + Math.log((v[j]?cv$temp$44$var68:(1.0 - cv$temp$44$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value187) + Math.log((v[j]?cv$temp$44$var68:(1.0 - cv$temp$44$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value187);
																					}
																				}
																			}
																		}
																	}
																	for(int index$i$213 = 0; index$i$213 < size; index$i$213 += 1) {
																		if(!(index$i$213 == index$i$2)) {
																			for(int index$sample27$214 = 0; index$sample27$214 < weightings.length; index$sample27$214 += 1) {
																				int distributionTempVariable$var27$216 = index$sample27$214;
																				double cv$probabilitySample27Value215 = (cv$probabilitySample5Value187 * distribution$sample27[((index$i$213 - 0) / 1)][index$sample27$214]);
																				int traceTempVariable$var44$217_1 = cv$currentValue;
																				for(int k = 0; k < (size + 1); k += 1) {
																					if(((index$i$213 + 1) == k)) {
																						int traceTempVariable$var63$217_3 = traceTempVariable$var44$217_1;
																						if((k == (j + 1))) {
																							{
																								{
																									double cv$temp$45$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$188) + v2[j]) + traceTempVariable$var63$217_3) / traceTempVariable$var67$7_1);
																										cv$temp$45$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value215) + Math.log((v[j]?cv$temp$45$var68:(1.0 - cv$temp$45$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value215) + Math.log((v[j]?cv$temp$45$var68:(1.0 - cv$temp$45$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value215) + Math.log((v[j]?cv$temp$45$var68:(1.0 - cv$temp$45$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value215) + Math.log((v[j]?cv$temp$45$var68:(1.0 - cv$temp$45$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value215) + Math.log((v[j]?cv$temp$45$var68:(1.0 - cv$temp$45$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value215);
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
																	for(int index$sample11$196 = 0; index$sample11$196 < weightings.length; index$sample11$196 += 1) {
																		int distributionTempVariable$var11$198 = index$sample11$196;
																		double cv$probabilitySample11Value197 = (cv$probabilitySample5Value187 * distribution$sample11[index$sample11$196]);
																		if((0 == j)) {
																			int traceTempVariable$var44$218_1 = cv$currentValue;
																			for(int k = 0; k < (size + 1); k += 1) {
																				if(((index$i$2 + 1) == k)) {
																					int traceTempVariable$var63$218_3 = traceTempVariable$var44$218_1;
																					if((k == (j + 1))) {
																						{
																							{
																								double cv$temp$46$var68;
																								{
																									double var68 = ((((1.0 * distributionTempVariable$v1$188) + v2[j]) + traceTempVariable$var63$218_3) / traceTempVariable$var67$7_1);
																									cv$temp$46$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample11Value197) + Math.log((v[j]?cv$temp$46$var68:(1.0 - cv$temp$46$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value197) + Math.log((v[j]?cv$temp$46$var68:(1.0 - cv$temp$46$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value197) + Math.log((v[j]?cv$temp$46$var68:(1.0 - cv$temp$46$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value197) + Math.log((v[j]?cv$temp$46$var68:(1.0 - cv$temp$46$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value197) + Math.log((v[j]?cv$temp$46$var68:(1.0 - cv$temp$46$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value197);
																							}
																						}
																					}
																				}
																			}
																			for(int index$i$219 = 0; index$i$219 < size; index$i$219 += 1) {
																				if(!(index$i$219 == index$i$2)) {
																					for(int index$sample27$220 = 0; index$sample27$220 < weightings.length; index$sample27$220 += 1) {
																						int distributionTempVariable$var27$222 = index$sample27$220;
																						double cv$probabilitySample27Value221 = (cv$probabilitySample11Value197 * distribution$sample27[((index$i$219 - 0) / 1)][index$sample27$220]);
																						int traceTempVariable$var44$223_1 = cv$currentValue;
																						for(int k = 0; k < (size + 1); k += 1) {
																							if(((index$i$219 + 1) == k)) {
																								int traceTempVariable$var63$223_3 = traceTempVariable$var44$223_1;
																								if((k == (j + 1))) {
																									{
																										{
																											double cv$temp$47$var68;
																											{
																												double var68 = ((((1.0 * distributionTempVariable$v1$188) + v2[j]) + traceTempVariable$var63$223_3) / traceTempVariable$var67$7_1);
																												cv$temp$47$var68 = var68;
																											}
																											if(((Math.log(cv$probabilitySample27Value221) + Math.log((v[j]?cv$temp$47$var68:(1.0 - cv$temp$47$var68)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value221) + Math.log((v[j]?cv$temp$47$var68:(1.0 - cv$temp$47$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value221) + Math.log((v[j]?cv$temp$47$var68:(1.0 - cv$temp$47$var68))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value221) + Math.log((v[j]?cv$temp$47$var68:(1.0 - cv$temp$47$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value221) + Math.log((v[j]?cv$temp$47$var68:(1.0 - cv$temp$47$var68)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value221);
																										}
																									}
																								}
																							}
																						}
																					}
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
													int traceTempVariable$var59$229_1 = cv$currentValue;
													if(((index$i$2 + 1) == j)) {
														if(fixedFlag$sample11) {
															for(int k = 0; k < (size + 1); k += 1) {
																if((0 == k)) {
																	int traceTempVariable$var63$241_2 = v2[k];
																	if((k == (j + 1))) {
																		{
																			{
																				double cv$temp$48$var68;
																				{
																					double var68 = ((((1.0 * v1) + traceTempVariable$var59$229_1) + traceTempVariable$var63$241_2) / traceTempVariable$var67$7_1);
																					cv$temp$48$var68 = var68;
																				}
																				if(((Math.log(1.0) + Math.log((v[j]?cv$temp$48$var68:(1.0 - cv$temp$48$var68)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$48$var68:(1.0 - cv$temp$48$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$48$var68:(1.0 - cv$temp$48$var68))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$48$var68:(1.0 - cv$temp$48$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$48$var68:(1.0 - cv$temp$48$var68)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
														} else {
															if(true) {
																for(int index$sample11$242 = 0; index$sample11$242 < weightings.length; index$sample11$242 += 1) {
																	int distributionTempVariable$var11$244 = index$sample11$242;
																	double cv$probabilitySample11Value243 = (1.0 * distribution$sample11[index$sample11$242]);
																	for(int k = 0; k < (size + 1); k += 1) {
																		if((0 == k)) {
																			int traceTempVariable$var63$245_2 = v2[k];
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$49$var68;
																						{
																							double var68 = ((((1.0 * v1) + traceTempVariable$var59$229_1) + traceTempVariable$var63$245_2) / traceTempVariable$var67$7_1);
																							cv$temp$49$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample11Value243) + Math.log((v[j]?cv$temp$49$var68:(1.0 - cv$temp$49$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value243) + Math.log((v[j]?cv$temp$49$var68:(1.0 - cv$temp$49$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value243) + Math.log((v[j]?cv$temp$49$var68:(1.0 - cv$temp$49$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value243) + Math.log((v[j]?cv$temp$49$var68:(1.0 - cv$temp$49$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value243) + Math.log((v[j]?cv$temp$49$var68:(1.0 - cv$temp$49$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value243);
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$230 = 0; index$i$230 < size; index$i$230 += 1) {
														if(!(index$i$230 == index$i$2)) {
															for(int index$sample27$231 = 0; index$sample27$231 < weightings.length; index$sample27$231 += 1) {
																int distributionTempVariable$var27$233 = index$sample27$231;
																double cv$probabilitySample27Value232 = (1.0 * distribution$sample27[((index$i$230 - 0) / 1)][index$sample27$231]);
																int traceTempVariable$var59$234_1 = cv$currentValue;
																if(((index$i$230 + 1) == j)) {
																	if(fixedFlag$sample11) {
																		for(int k = 0; k < (size + 1); k += 1) {
																			if((0 == k)) {
																				int traceTempVariable$var63$246_2 = v2[k];
																				if((k == (j + 1))) {
																					{
																						{
																							double cv$temp$50$var68;
																							{
																								double var68 = ((((1.0 * v1) + traceTempVariable$var59$234_1) + traceTempVariable$var63$246_2) / traceTempVariable$var67$7_1);
																								cv$temp$50$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample27Value232) + Math.log((v[j]?cv$temp$50$var68:(1.0 - cv$temp$50$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value232) + Math.log((v[j]?cv$temp$50$var68:(1.0 - cv$temp$50$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value232) + Math.log((v[j]?cv$temp$50$var68:(1.0 - cv$temp$50$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value232) + Math.log((v[j]?cv$temp$50$var68:(1.0 - cv$temp$50$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value232) + Math.log((v[j]?cv$temp$50$var68:(1.0 - cv$temp$50$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value232);
																						}
																					}
																				}
																			}
																		}
																	} else {
																		if(true) {
																			for(int index$sample11$247 = 0; index$sample11$247 < weightings.length; index$sample11$247 += 1) {
																				int distributionTempVariable$var11$249 = index$sample11$247;
																				double cv$probabilitySample11Value248 = (cv$probabilitySample27Value232 * distribution$sample11[index$sample11$247]);
																				for(int k = 0; k < (size + 1); k += 1) {
																					if((0 == k)) {
																						int traceTempVariable$var63$250_2 = v2[k];
																						if((k == (j + 1))) {
																							{
																								{
																									double cv$temp$51$var68;
																									{
																										double var68 = ((((1.0 * v1) + traceTempVariable$var59$234_1) + traceTempVariable$var63$250_2) / traceTempVariable$var67$7_1);
																										cv$temp$51$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample11Value248) + Math.log((v[j]?cv$temp$51$var68:(1.0 - cv$temp$51$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value248) + Math.log((v[j]?cv$temp$51$var68:(1.0 - cv$temp$51$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value248) + Math.log((v[j]?cv$temp$51$var68:(1.0 - cv$temp$51$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value248) + Math.log((v[j]?cv$temp$51$var68:(1.0 - cv$temp$51$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value248) + Math.log((v[j]?cv$temp$51$var68:(1.0 - cv$temp$51$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value248);
																								}
																							}
																						}
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
														for(int index$sample5$225 = 0; index$sample5$225 < weightings.length; index$sample5$225 += 1) {
															int distributionTempVariable$v1$227 = index$sample5$225;
															double cv$probabilitySample5Value226 = (1.0 * distribution$sample5[index$sample5$225]);
															int traceTempVariable$var59$235_1 = cv$currentValue;
															if(((index$i$2 + 1) == j)) {
																if(fixedFlag$sample11) {
																	for(int k = 0; k < (size + 1); k += 1) {
																		if((0 == k)) {
																			int traceTempVariable$var63$251_2 = v2[k];
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$52$var68;
																						{
																							double var68 = ((((1.0 * distributionTempVariable$v1$227) + traceTempVariable$var59$235_1) + traceTempVariable$var63$251_2) / traceTempVariable$var67$7_1);
																							cv$temp$52$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample5Value226) + Math.log((v[j]?cv$temp$52$var68:(1.0 - cv$temp$52$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value226) + Math.log((v[j]?cv$temp$52$var68:(1.0 - cv$temp$52$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value226) + Math.log((v[j]?cv$temp$52$var68:(1.0 - cv$temp$52$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value226) + Math.log((v[j]?cv$temp$52$var68:(1.0 - cv$temp$52$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value226) + Math.log((v[j]?cv$temp$52$var68:(1.0 - cv$temp$52$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value226);
																					}
																				}
																			}
																		}
																	}
																} else {
																	if(true) {
																		for(int index$sample11$252 = 0; index$sample11$252 < weightings.length; index$sample11$252 += 1) {
																			int distributionTempVariable$var11$254 = index$sample11$252;
																			double cv$probabilitySample11Value253 = (cv$probabilitySample5Value226 * distribution$sample11[index$sample11$252]);
																			for(int k = 0; k < (size + 1); k += 1) {
																				if((0 == k)) {
																					int traceTempVariable$var63$255_2 = v2[k];
																					if((k == (j + 1))) {
																						{
																							{
																								double cv$temp$53$var68;
																								{
																									double var68 = ((((1.0 * distributionTempVariable$v1$227) + traceTempVariable$var59$235_1) + traceTempVariable$var63$255_2) / traceTempVariable$var67$7_1);
																									cv$temp$53$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample11Value253) + Math.log((v[j]?cv$temp$53$var68:(1.0 - cv$temp$53$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value253) + Math.log((v[j]?cv$temp$53$var68:(1.0 - cv$temp$53$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value253) + Math.log((v[j]?cv$temp$53$var68:(1.0 - cv$temp$53$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value253) + Math.log((v[j]?cv$temp$53$var68:(1.0 - cv$temp$53$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value253) + Math.log((v[j]?cv$temp$53$var68:(1.0 - cv$temp$53$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value253);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															for(int index$i$236 = 0; index$i$236 < size; index$i$236 += 1) {
																if(!(index$i$236 == index$i$2)) {
																	for(int index$sample27$237 = 0; index$sample27$237 < weightings.length; index$sample27$237 += 1) {
																		int distributionTempVariable$var27$239 = index$sample27$237;
																		double cv$probabilitySample27Value238 = (cv$probabilitySample5Value226 * distribution$sample27[((index$i$236 - 0) / 1)][index$sample27$237]);
																		int traceTempVariable$var59$240_1 = cv$currentValue;
																		if(((index$i$236 + 1) == j)) {
																			if(fixedFlag$sample11) {
																				for(int k = 0; k < (size + 1); k += 1) {
																					if((0 == k)) {
																						int traceTempVariable$var63$256_2 = v2[k];
																						if((k == (j + 1))) {
																							{
																								{
																									double cv$temp$54$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$227) + traceTempVariable$var59$240_1) + traceTempVariable$var63$256_2) / traceTempVariable$var67$7_1);
																										cv$temp$54$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value238) + Math.log((v[j]?cv$temp$54$var68:(1.0 - cv$temp$54$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value238) + Math.log((v[j]?cv$temp$54$var68:(1.0 - cv$temp$54$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value238) + Math.log((v[j]?cv$temp$54$var68:(1.0 - cv$temp$54$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value238) + Math.log((v[j]?cv$temp$54$var68:(1.0 - cv$temp$54$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value238) + Math.log((v[j]?cv$temp$54$var68:(1.0 - cv$temp$54$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value238);
																								}
																							}
																						}
																					}
																				}
																			} else {
																				if(true) {
																					for(int index$sample11$257 = 0; index$sample11$257 < weightings.length; index$sample11$257 += 1) {
																						int distributionTempVariable$var11$259 = index$sample11$257;
																						double cv$probabilitySample11Value258 = (cv$probabilitySample27Value238 * distribution$sample11[index$sample11$257]);
																						for(int k = 0; k < (size + 1); k += 1) {
																							if((0 == k)) {
																								int traceTempVariable$var63$260_2 = v2[k];
																								if((k == (j + 1))) {
																									{
																										{
																											double cv$temp$55$var68;
																											{
																												double var68 = ((((1.0 * distributionTempVariable$v1$227) + traceTempVariable$var59$240_1) + traceTempVariable$var63$260_2) / traceTempVariable$var67$7_1);
																												cv$temp$55$var68 = var68;
																											}
																											if(((Math.log(cv$probabilitySample11Value258) + Math.log((v[j]?cv$temp$55$var68:(1.0 - cv$temp$55$var68)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value258) + Math.log((v[j]?cv$temp$55$var68:(1.0 - cv$temp$55$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value258) + Math.log((v[j]?cv$temp$55$var68:(1.0 - cv$temp$55$var68))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value258) + Math.log((v[j]?cv$temp$55$var68:(1.0 - cv$temp$55$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value258) + Math.log((v[j]?cv$temp$55$var68:(1.0 - cv$temp$55$var68)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value258);
																										}
																									}
																								}
																							}
																						}
																					}
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
													int traceTempVariable$var59$266_1 = cv$currentValue;
													if(((index$i$2 + 1) == j)) {
														int traceTempVariable$var44$278_1 = cv$currentValue;
														for(int k = 0; k < (size + 1); k += 1) {
															if(((index$i$2 + 1) == k)) {
																int traceTempVariable$var63$278_3 = traceTempVariable$var44$278_1;
																if((k == (j + 1))) {
																	{
																		{
																			double cv$temp$56$var68;
																			{
																				double var68 = ((((1.0 * v1) + traceTempVariable$var59$266_1) + traceTempVariable$var63$278_3) / traceTempVariable$var67$7_1);
																				cv$temp$56$var68 = var68;
																			}
																			if(((Math.log(1.0) + Math.log((v[j]?cv$temp$56$var68:(1.0 - cv$temp$56$var68)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$56$var68:(1.0 - cv$temp$56$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$56$var68:(1.0 - cv$temp$56$var68))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$56$var68:(1.0 - cv$temp$56$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$56$var68:(1.0 - cv$temp$56$var68)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
														for(int index$i$279 = 0; index$i$279 < size; index$i$279 += 1) {
															if(!(index$i$279 == index$i$2)) {
																for(int index$sample27$280 = 0; index$sample27$280 < weightings.length; index$sample27$280 += 1) {
																	int distributionTempVariable$var27$282 = index$sample27$280;
																	double cv$probabilitySample27Value281 = (1.0 * distribution$sample27[((index$i$279 - 0) / 1)][index$sample27$280]);
																	int traceTempVariable$var44$283_1 = cv$currentValue;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if(((index$i$279 + 1) == k)) {
																			int traceTempVariable$var63$283_3 = traceTempVariable$var44$283_1;
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$57$var68;
																						{
																							double var68 = ((((1.0 * v1) + traceTempVariable$var59$266_1) + traceTempVariable$var63$283_3) / traceTempVariable$var67$7_1);
																							cv$temp$57$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample27Value281) + Math.log((v[j]?cv$temp$57$var68:(1.0 - cv$temp$57$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value281) + Math.log((v[j]?cv$temp$57$var68:(1.0 - cv$temp$57$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value281) + Math.log((v[j]?cv$temp$57$var68:(1.0 - cv$temp$57$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value281) + Math.log((v[j]?cv$temp$57$var68:(1.0 - cv$temp$57$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value281) + Math.log((v[j]?cv$temp$57$var68:(1.0 - cv$temp$57$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value281);
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$267 = 0; index$i$267 < size; index$i$267 += 1) {
														if(!(index$i$267 == index$i$2)) {
															for(int index$sample27$268 = 0; index$sample27$268 < weightings.length; index$sample27$268 += 1) {
																int distributionTempVariable$var27$270 = index$sample27$268;
																double cv$probabilitySample27Value269 = (1.0 * distribution$sample27[((index$i$267 - 0) / 1)][index$sample27$268]);
																int traceTempVariable$var59$271_1 = cv$currentValue;
																if(((index$i$267 + 1) == j)) {
																	int traceTempVariable$var44$284_1 = cv$currentValue;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if(((index$i$2 + 1) == k)) {
																			int traceTempVariable$var63$284_3 = traceTempVariable$var44$284_1;
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$58$var68;
																						{
																							double var68 = ((((1.0 * v1) + traceTempVariable$var59$271_1) + traceTempVariable$var63$284_3) / traceTempVariable$var67$7_1);
																							cv$temp$58$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample27Value269) + Math.log((v[j]?cv$temp$58$var68:(1.0 - cv$temp$58$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value269) + Math.log((v[j]?cv$temp$58$var68:(1.0 - cv$temp$58$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value269) + Math.log((v[j]?cv$temp$58$var68:(1.0 - cv$temp$58$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value269) + Math.log((v[j]?cv$temp$58$var68:(1.0 - cv$temp$58$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value269) + Math.log((v[j]?cv$temp$58$var68:(1.0 - cv$temp$58$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value269);
																					}
																				}
																			}
																		}
																	}
																	int traceTempVariable$var44$285_1 = cv$currentValue;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if(((index$i$267 + 1) == k)) {
																			int traceTempVariable$var63$285_3 = traceTempVariable$var44$285_1;
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$59$var68;
																						{
																							double var68 = ((((1.0 * v1) + traceTempVariable$var59$271_1) + traceTempVariable$var63$285_3) / traceTempVariable$var67$7_1);
																							cv$temp$59$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample27Value269) + Math.log((v[j]?cv$temp$59$var68:(1.0 - cv$temp$59$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value269) + Math.log((v[j]?cv$temp$59$var68:(1.0 - cv$temp$59$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value269) + Math.log((v[j]?cv$temp$59$var68:(1.0 - cv$temp$59$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value269) + Math.log((v[j]?cv$temp$59$var68:(1.0 - cv$temp$59$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value269) + Math.log((v[j]?cv$temp$59$var68:(1.0 - cv$temp$59$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value269);
																					}
																				}
																			}
																		}
																	}
																	for(int index$i$286 = 0; index$i$286 < size; index$i$286 += 1) {
																		if((!(index$i$286 == index$i$2) && !(index$i$286 == index$i$267))) {
																			for(int index$sample27$287 = 0; index$sample27$287 < weightings.length; index$sample27$287 += 1) {
																				int distributionTempVariable$var27$289 = index$sample27$287;
																				double cv$probabilitySample27Value288 = (cv$probabilitySample27Value269 * distribution$sample27[((index$i$286 - 0) / 1)][index$sample27$287]);
																				int traceTempVariable$var44$290_1 = cv$currentValue;
																				for(int k = 0; k < (size + 1); k += 1) {
																					if(((index$i$286 + 1) == k)) {
																						int traceTempVariable$var63$290_3 = traceTempVariable$var44$290_1;
																						if((k == (j + 1))) {
																							{
																								{
																									double cv$temp$60$var68;
																									{
																										double var68 = ((((1.0 * v1) + traceTempVariable$var59$271_1) + traceTempVariable$var63$290_3) / traceTempVariable$var67$7_1);
																										cv$temp$60$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value288) + Math.log((v[j]?cv$temp$60$var68:(1.0 - cv$temp$60$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value288) + Math.log((v[j]?cv$temp$60$var68:(1.0 - cv$temp$60$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value288) + Math.log((v[j]?cv$temp$60$var68:(1.0 - cv$temp$60$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value288) + Math.log((v[j]?cv$temp$60$var68:(1.0 - cv$temp$60$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value288) + Math.log((v[j]?cv$temp$60$var68:(1.0 - cv$temp$60$var68)))));
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
												} else {
													if(true) {
														for(int index$sample5$262 = 0; index$sample5$262 < weightings.length; index$sample5$262 += 1) {
															int distributionTempVariable$v1$264 = index$sample5$262;
															double cv$probabilitySample5Value263 = (1.0 * distribution$sample5[index$sample5$262]);
															int traceTempVariable$var59$272_1 = cv$currentValue;
															if(((index$i$2 + 1) == j)) {
																int traceTempVariable$var44$291_1 = cv$currentValue;
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((index$i$2 + 1) == k)) {
																		int traceTempVariable$var63$291_3 = traceTempVariable$var44$291_1;
																		if((k == (j + 1))) {
																			{
																				{
																					double cv$temp$61$var68;
																					{
																						double var68 = ((((1.0 * distributionTempVariable$v1$264) + traceTempVariable$var59$272_1) + traceTempVariable$var63$291_3) / traceTempVariable$var67$7_1);
																						cv$temp$61$var68 = var68;
																					}
																					if(((Math.log(cv$probabilitySample5Value263) + Math.log((v[j]?cv$temp$61$var68:(1.0 - cv$temp$61$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value263) + Math.log((v[j]?cv$temp$61$var68:(1.0 - cv$temp$61$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value263) + Math.log((v[j]?cv$temp$61$var68:(1.0 - cv$temp$61$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value263) + Math.log((v[j]?cv$temp$61$var68:(1.0 - cv$temp$61$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value263) + Math.log((v[j]?cv$temp$61$var68:(1.0 - cv$temp$61$var68)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value263);
																				}
																			}
																		}
																	}
																}
																for(int index$i$292 = 0; index$i$292 < size; index$i$292 += 1) {
																	if(!(index$i$292 == index$i$2)) {
																		for(int index$sample27$293 = 0; index$sample27$293 < weightings.length; index$sample27$293 += 1) {
																			int distributionTempVariable$var27$295 = index$sample27$293;
																			double cv$probabilitySample27Value294 = (cv$probabilitySample5Value263 * distribution$sample27[((index$i$292 - 0) / 1)][index$sample27$293]);
																			int traceTempVariable$var44$296_1 = cv$currentValue;
																			for(int k = 0; k < (size + 1); k += 1) {
																				if(((index$i$292 + 1) == k)) {
																					int traceTempVariable$var63$296_3 = traceTempVariable$var44$296_1;
																					if((k == (j + 1))) {
																						{
																							{
																								double cv$temp$62$var68;
																								{
																									double var68 = ((((1.0 * distributionTempVariable$v1$264) + traceTempVariable$var59$272_1) + traceTempVariable$var63$296_3) / traceTempVariable$var67$7_1);
																									cv$temp$62$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample27Value294) + Math.log((v[j]?cv$temp$62$var68:(1.0 - cv$temp$62$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value294) + Math.log((v[j]?cv$temp$62$var68:(1.0 - cv$temp$62$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value294) + Math.log((v[j]?cv$temp$62$var68:(1.0 - cv$temp$62$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value294) + Math.log((v[j]?cv$temp$62$var68:(1.0 - cv$temp$62$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value294) + Math.log((v[j]?cv$temp$62$var68:(1.0 - cv$temp$62$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value294);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															for(int index$i$273 = 0; index$i$273 < size; index$i$273 += 1) {
																if(!(index$i$273 == index$i$2)) {
																	for(int index$sample27$274 = 0; index$sample27$274 < weightings.length; index$sample27$274 += 1) {
																		int distributionTempVariable$var27$276 = index$sample27$274;
																		double cv$probabilitySample27Value275 = (cv$probabilitySample5Value263 * distribution$sample27[((index$i$273 - 0) / 1)][index$sample27$274]);
																		int traceTempVariable$var59$277_1 = cv$currentValue;
																		if(((index$i$273 + 1) == j)) {
																			int traceTempVariable$var44$297_1 = cv$currentValue;
																			for(int k = 0; k < (size + 1); k += 1) {
																				if(((index$i$2 + 1) == k)) {
																					int traceTempVariable$var63$297_3 = traceTempVariable$var44$297_1;
																					if((k == (j + 1))) {
																						{
																							{
																								double cv$temp$63$var68;
																								{
																									double var68 = ((((1.0 * distributionTempVariable$v1$264) + traceTempVariable$var59$277_1) + traceTempVariable$var63$297_3) / traceTempVariable$var67$7_1);
																									cv$temp$63$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample27Value275) + Math.log((v[j]?cv$temp$63$var68:(1.0 - cv$temp$63$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value275) + Math.log((v[j]?cv$temp$63$var68:(1.0 - cv$temp$63$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value275) + Math.log((v[j]?cv$temp$63$var68:(1.0 - cv$temp$63$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value275) + Math.log((v[j]?cv$temp$63$var68:(1.0 - cv$temp$63$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value275) + Math.log((v[j]?cv$temp$63$var68:(1.0 - cv$temp$63$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value275);
																							}
																						}
																					}
																				}
																			}
																			int traceTempVariable$var44$298_1 = cv$currentValue;
																			for(int k = 0; k < (size + 1); k += 1) {
																				if(((index$i$273 + 1) == k)) {
																					int traceTempVariable$var63$298_3 = traceTempVariable$var44$298_1;
																					if((k == (j + 1))) {
																						{
																							{
																								double cv$temp$64$var68;
																								{
																									double var68 = ((((1.0 * distributionTempVariable$v1$264) + traceTempVariable$var59$277_1) + traceTempVariable$var63$298_3) / traceTempVariable$var67$7_1);
																									cv$temp$64$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample27Value275) + Math.log((v[j]?cv$temp$64$var68:(1.0 - cv$temp$64$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value275) + Math.log((v[j]?cv$temp$64$var68:(1.0 - cv$temp$64$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value275) + Math.log((v[j]?cv$temp$64$var68:(1.0 - cv$temp$64$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value275) + Math.log((v[j]?cv$temp$64$var68:(1.0 - cv$temp$64$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value275) + Math.log((v[j]?cv$temp$64$var68:(1.0 - cv$temp$64$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value275);
																							}
																						}
																					}
																				}
																			}
																			for(int index$i$299 = 0; index$i$299 < size; index$i$299 += 1) {
																				if((!(index$i$299 == index$i$2) && !(index$i$299 == index$i$273))) {
																					for(int index$sample27$300 = 0; index$sample27$300 < weightings.length; index$sample27$300 += 1) {
																						int distributionTempVariable$var27$302 = index$sample27$300;
																						double cv$probabilitySample27Value301 = (cv$probabilitySample27Value275 * distribution$sample27[((index$i$299 - 0) / 1)][index$sample27$300]);
																						int traceTempVariable$var44$303_1 = cv$currentValue;
																						for(int k = 0; k < (size + 1); k += 1) {
																							if(((index$i$299 + 1) == k)) {
																								int traceTempVariable$var63$303_3 = traceTempVariable$var44$303_1;
																								if((k == (j + 1))) {
																									{
																										{
																											double cv$temp$65$var68;
																											{
																												double var68 = ((((1.0 * distributionTempVariable$v1$264) + traceTempVariable$var59$277_1) + traceTempVariable$var63$303_3) / traceTempVariable$var67$7_1);
																												cv$temp$65$var68 = var68;
																											}
																											if(((Math.log(cv$probabilitySample27Value301) + Math.log((v[j]?cv$temp$65$var68:(1.0 - cv$temp$65$var68)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value301) + Math.log((v[j]?cv$temp$65$var68:(1.0 - cv$temp$65$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value301) + Math.log((v[j]?cv$temp$65$var68:(1.0 - cv$temp$65$var68))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value301) + Math.log((v[j]?cv$temp$65$var68:(1.0 - cv$temp$65$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value301) + Math.log((v[j]?cv$temp$65$var68:(1.0 - cv$temp$65$var68)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value301);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
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
							int traceTempVariable$var44$8_1 = cv$currentValue;
							for(int k = 0; k < (size + 1); k += 1) {
								if(((i + 1) == k)) {
									int traceTempVariable$var63$8_3 = traceTempVariable$var44$8_1;
									for(int j = 0; j < size; j += 1) {
										if((k == (j + 1))) {
											if(!guard$sample27bernoulli69[((j - 0) / 1)]) {
												guard$sample27bernoulli69[((j - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if(fixedFlag$sample5) {
															if(fixedFlag$sample11) {
																if((0 == j)) {
																	if((0 == (j + 1))) {
																		{
																			{
																				double cv$temp$66$var68;
																				{
																					double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$8_3) / v2[(j + 1)]);
																					cv$temp$66$var68 = var68;
																				}
																				if(((Math.log(1.0) + Math.log((v[j]?cv$temp$66$var68:(1.0 - cv$temp$66$var68)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$66$var68:(1.0 - cv$temp$66$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$66$var68:(1.0 - cv$temp$66$var68))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$66$var68:(1.0 - cv$temp$66$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$66$var68:(1.0 - cv$temp$66$var68)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample11$310 = 0; index$sample11$310 < weightings.length; index$sample11$310 += 1) {
																		int distributionTempVariable$var11$312 = index$sample11$310;
																		double cv$probabilitySample11Value311 = (1.0 * distribution$sample11[index$sample11$310]);
																		if((0 == j)) {
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$67$var68;
																						{
																							double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$8_3) / v2[(j + 1)]);
																							cv$temp$67$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample11Value311) + Math.log((v[j]?cv$temp$67$var68:(1.0 - cv$temp$67$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value311) + Math.log((v[j]?cv$temp$67$var68:(1.0 - cv$temp$67$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value311) + Math.log((v[j]?cv$temp$67$var68:(1.0 - cv$temp$67$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value311) + Math.log((v[j]?cv$temp$67$var68:(1.0 - cv$temp$67$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value311) + Math.log((v[j]?cv$temp$67$var68:(1.0 - cv$temp$67$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value311);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample11$321 = 0; index$sample11$321 < weightings.length; index$sample11$321 += 1) {
																					int distributionTempVariable$var11$323 = index$sample11$321;
																					double cv$probabilitySample11Value322 = (cv$probabilitySample11Value311 * distribution$sample11[index$sample11$321]);
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$68$var68;
																								{
																									double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$8_3) / v2[(j + 1)]);
																									cv$temp$68$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample11Value322) + Math.log((v[j]?cv$temp$68$var68:(1.0 - cv$temp$68$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value322) + Math.log((v[j]?cv$temp$68$var68:(1.0 - cv$temp$68$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value322) + Math.log((v[j]?cv$temp$68$var68:(1.0 - cv$temp$68$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value322) + Math.log((v[j]?cv$temp$68$var68:(1.0 - cv$temp$68$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value322) + Math.log((v[j]?cv$temp$68$var68:(1.0 - cv$temp$68$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value322);
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
																for(int index$sample5$305 = 0; index$sample5$305 < weightings.length; index$sample5$305 += 1) {
																	int distributionTempVariable$v1$307 = index$sample5$305;
																	double cv$probabilitySample5Value306 = (1.0 * distribution$sample5[index$sample5$305]);
																	if(fixedFlag$sample11) {
																		if((0 == j)) {
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$69$var68;
																						{
																							double var68 = ((((1.0 * distributionTempVariable$v1$307) + v2[j]) + traceTempVariable$var63$8_3) / v2[(j + 1)]);
																							cv$temp$69$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample5Value306) + Math.log((v[j]?cv$temp$69$var68:(1.0 - cv$temp$69$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value306) + Math.log((v[j]?cv$temp$69$var68:(1.0 - cv$temp$69$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value306) + Math.log((v[j]?cv$temp$69$var68:(1.0 - cv$temp$69$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value306) + Math.log((v[j]?cv$temp$69$var68:(1.0 - cv$temp$69$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value306) + Math.log((v[j]?cv$temp$69$var68:(1.0 - cv$temp$69$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value306);
																					}
																				}
																			}
																		}
																	} else {
																		if(true) {
																			for(int index$sample11$315 = 0; index$sample11$315 < weightings.length; index$sample11$315 += 1) {
																				int distributionTempVariable$var11$317 = index$sample11$315;
																				double cv$probabilitySample11Value316 = (cv$probabilitySample5Value306 * distribution$sample11[index$sample11$315]);
																				if((0 == j)) {
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$70$var68;
																								{
																									double var68 = ((((1.0 * distributionTempVariable$v1$307) + v2[j]) + traceTempVariable$var63$8_3) / v2[(j + 1)]);
																									cv$temp$70$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample11Value316) + Math.log((v[j]?cv$temp$70$var68:(1.0 - cv$temp$70$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value316) + Math.log((v[j]?cv$temp$70$var68:(1.0 - cv$temp$70$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value316) + Math.log((v[j]?cv$temp$70$var68:(1.0 - cv$temp$70$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value316) + Math.log((v[j]?cv$temp$70$var68:(1.0 - cv$temp$70$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value316) + Math.log((v[j]?cv$temp$70$var68:(1.0 - cv$temp$70$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value316);
																							}
																						}
																					}
																					if(!true) {
																						for(int index$sample11$327 = 0; index$sample11$327 < weightings.length; index$sample11$327 += 1) {
																							int distributionTempVariable$var11$329 = index$sample11$327;
																							double cv$probabilitySample11Value328 = (cv$probabilitySample11Value316 * distribution$sample11[index$sample11$327]);
																							if((0 == (j + 1))) {
																								{
																									{
																										double cv$temp$71$var68;
																										{
																											double var68 = ((((1.0 * distributionTempVariable$v1$307) + v2[j]) + traceTempVariable$var63$8_3) / v2[(j + 1)]);
																											cv$temp$71$var68 = var68;
																										}
																										if(((Math.log(cv$probabilitySample11Value328) + Math.log((v[j]?cv$temp$71$var68:(1.0 - cv$temp$71$var68)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value328) + Math.log((v[j]?cv$temp$71$var68:(1.0 - cv$temp$71$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value328) + Math.log((v[j]?cv$temp$71$var68:(1.0 - cv$temp$71$var68))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value328) + Math.log((v[j]?cv$temp$71$var68:(1.0 - cv$temp$71$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value328) + Math.log((v[j]?cv$temp$71$var68:(1.0 - cv$temp$71$var68)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value328);
																									}
																								}
																							}
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
															int traceTempVariable$var59$336_1 = cv$currentValue;
															if(((index$i$2 + 1) == j)) {
																if(fixedFlag$sample11) {
																	if((0 == (j + 1))) {
																		{
																			{
																				double cv$temp$72$var68;
																				{
																					double var68 = ((((1.0 * v1) + traceTempVariable$var59$336_1) + traceTempVariable$var63$8_3) / v2[(j + 1)]);
																					cv$temp$72$var68 = var68;
																				}
																				if(((Math.log(1.0) + Math.log((v[j]?cv$temp$72$var68:(1.0 - cv$temp$72$var68)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$72$var68:(1.0 - cv$temp$72$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$72$var68:(1.0 - cv$temp$72$var68))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$72$var68:(1.0 - cv$temp$72$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$72$var68:(1.0 - cv$temp$72$var68)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																} else {
																	if(true) {
																		for(int index$sample11$349 = 0; index$sample11$349 < weightings.length; index$sample11$349 += 1) {
																			int distributionTempVariable$var11$351 = index$sample11$349;
																			double cv$probabilitySample11Value350 = (1.0 * distribution$sample11[index$sample11$349]);
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$73$var68;
																						{
																							double var68 = ((((1.0 * v1) + traceTempVariable$var59$336_1) + traceTempVariable$var63$8_3) / v2[(j + 1)]);
																							cv$temp$73$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample11Value350) + Math.log((v[j]?cv$temp$73$var68:(1.0 - cv$temp$73$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value350) + Math.log((v[j]?cv$temp$73$var68:(1.0 - cv$temp$73$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value350) + Math.log((v[j]?cv$temp$73$var68:(1.0 - cv$temp$73$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value350) + Math.log((v[j]?cv$temp$73$var68:(1.0 - cv$temp$73$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value350) + Math.log((v[j]?cv$temp$73$var68:(1.0 - cv$temp$73$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value350);
																					}
																				}
																			}
																		}
																	}
																}
															}
															for(int index$i$337 = 0; index$i$337 < size; index$i$337 += 1) {
																if(!(index$i$337 == index$i$2)) {
																	for(int index$sample27$338 = 0; index$sample27$338 < weightings.length; index$sample27$338 += 1) {
																		int distributionTempVariable$var27$340 = index$sample27$338;
																		double cv$probabilitySample27Value339 = (1.0 * distribution$sample27[((index$i$337 - 0) / 1)][index$sample27$338]);
																		int traceTempVariable$var59$341_1 = cv$currentValue;
																		if(((index$i$337 + 1) == j)) {
																			if(fixedFlag$sample11) {
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$74$var68;
																							{
																								double var68 = ((((1.0 * v1) + traceTempVariable$var59$341_1) + traceTempVariable$var63$8_3) / v2[(j + 1)]);
																								cv$temp$74$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample27Value339) + Math.log((v[j]?cv$temp$74$var68:(1.0 - cv$temp$74$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value339) + Math.log((v[j]?cv$temp$74$var68:(1.0 - cv$temp$74$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value339) + Math.log((v[j]?cv$temp$74$var68:(1.0 - cv$temp$74$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value339) + Math.log((v[j]?cv$temp$74$var68:(1.0 - cv$temp$74$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value339) + Math.log((v[j]?cv$temp$74$var68:(1.0 - cv$temp$74$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value339);
																						}
																					}
																				}
																			} else {
																				if(true) {
																					for(int index$sample11$354 = 0; index$sample11$354 < weightings.length; index$sample11$354 += 1) {
																						int distributionTempVariable$var11$356 = index$sample11$354;
																						double cv$probabilitySample11Value355 = (cv$probabilitySample27Value339 * distribution$sample11[index$sample11$354]);
																						if((0 == (j + 1))) {
																							{
																								{
																									double cv$temp$75$var68;
																									{
																										double var68 = ((((1.0 * v1) + traceTempVariable$var59$341_1) + traceTempVariable$var63$8_3) / v2[(j + 1)]);
																										cv$temp$75$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample11Value355) + Math.log((v[j]?cv$temp$75$var68:(1.0 - cv$temp$75$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value355) + Math.log((v[j]?cv$temp$75$var68:(1.0 - cv$temp$75$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value355) + Math.log((v[j]?cv$temp$75$var68:(1.0 - cv$temp$75$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value355) + Math.log((v[j]?cv$temp$75$var68:(1.0 - cv$temp$75$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value355) + Math.log((v[j]?cv$temp$75$var68:(1.0 - cv$temp$75$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value355);
																								}
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
																for(int index$sample5$332 = 0; index$sample5$332 < weightings.length; index$sample5$332 += 1) {
																	int distributionTempVariable$v1$334 = index$sample5$332;
																	double cv$probabilitySample5Value333 = (1.0 * distribution$sample5[index$sample5$332]);
																	int traceTempVariable$var59$342_1 = cv$currentValue;
																	if(((index$i$2 + 1) == j)) {
																		if(fixedFlag$sample11) {
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$76$var68;
																						{
																							double var68 = ((((1.0 * distributionTempVariable$v1$334) + traceTempVariable$var59$342_1) + traceTempVariable$var63$8_3) / v2[(j + 1)]);
																							cv$temp$76$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample5Value333) + Math.log((v[j]?cv$temp$76$var68:(1.0 - cv$temp$76$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value333) + Math.log((v[j]?cv$temp$76$var68:(1.0 - cv$temp$76$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value333) + Math.log((v[j]?cv$temp$76$var68:(1.0 - cv$temp$76$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value333) + Math.log((v[j]?cv$temp$76$var68:(1.0 - cv$temp$76$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value333) + Math.log((v[j]?cv$temp$76$var68:(1.0 - cv$temp$76$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value333);
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample11$359 = 0; index$sample11$359 < weightings.length; index$sample11$359 += 1) {
																					int distributionTempVariable$var11$361 = index$sample11$359;
																					double cv$probabilitySample11Value360 = (cv$probabilitySample5Value333 * distribution$sample11[index$sample11$359]);
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$77$var68;
																								{
																									double var68 = ((((1.0 * distributionTempVariable$v1$334) + traceTempVariable$var59$342_1) + traceTempVariable$var63$8_3) / v2[(j + 1)]);
																									cv$temp$77$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample11Value360) + Math.log((v[j]?cv$temp$77$var68:(1.0 - cv$temp$77$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value360) + Math.log((v[j]?cv$temp$77$var68:(1.0 - cv$temp$77$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value360) + Math.log((v[j]?cv$temp$77$var68:(1.0 - cv$temp$77$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value360) + Math.log((v[j]?cv$temp$77$var68:(1.0 - cv$temp$77$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value360) + Math.log((v[j]?cv$temp$77$var68:(1.0 - cv$temp$77$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value360);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																	for(int index$i$343 = 0; index$i$343 < size; index$i$343 += 1) {
																		if(!(index$i$343 == index$i$2)) {
																			for(int index$sample27$344 = 0; index$sample27$344 < weightings.length; index$sample27$344 += 1) {
																				int distributionTempVariable$var27$346 = index$sample27$344;
																				double cv$probabilitySample27Value345 = (cv$probabilitySample5Value333 * distribution$sample27[((index$i$343 - 0) / 1)][index$sample27$344]);
																				int traceTempVariable$var59$347_1 = cv$currentValue;
																				if(((index$i$343 + 1) == j)) {
																					if(fixedFlag$sample11) {
																						if((0 == (j + 1))) {
																							{
																								{
																									double cv$temp$78$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$334) + traceTempVariable$var59$347_1) + traceTempVariable$var63$8_3) / v2[(j + 1)]);
																										cv$temp$78$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value345) + Math.log((v[j]?cv$temp$78$var68:(1.0 - cv$temp$78$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value345) + Math.log((v[j]?cv$temp$78$var68:(1.0 - cv$temp$78$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value345) + Math.log((v[j]?cv$temp$78$var68:(1.0 - cv$temp$78$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value345) + Math.log((v[j]?cv$temp$78$var68:(1.0 - cv$temp$78$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value345) + Math.log((v[j]?cv$temp$78$var68:(1.0 - cv$temp$78$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value345);
																								}
																							}
																						}
																					} else {
																						if(true) {
																							for(int index$sample11$364 = 0; index$sample11$364 < weightings.length; index$sample11$364 += 1) {
																								int distributionTempVariable$var11$366 = index$sample11$364;
																								double cv$probabilitySample11Value365 = (cv$probabilitySample27Value345 * distribution$sample11[index$sample11$364]);
																								if((0 == (j + 1))) {
																									{
																										{
																											double cv$temp$79$var68;
																											{
																												double var68 = ((((1.0 * distributionTempVariable$v1$334) + traceTempVariable$var59$347_1) + traceTempVariable$var63$8_3) / v2[(j + 1)]);
																												cv$temp$79$var68 = var68;
																											}
																											if(((Math.log(cv$probabilitySample11Value365) + Math.log((v[j]?cv$temp$79$var68:(1.0 - cv$temp$79$var68)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value365) + Math.log((v[j]?cv$temp$79$var68:(1.0 - cv$temp$79$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value365) + Math.log((v[j]?cv$temp$79$var68:(1.0 - cv$temp$79$var68))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value365) + Math.log((v[j]?cv$temp$79$var68:(1.0 - cv$temp$79$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value365) + Math.log((v[j]?cv$temp$79$var68:(1.0 - cv$temp$79$var68)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value365);
																										}
																									}
																								}
																							}
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
															if(fixedFlag$sample11) {
																if((0 == j)) {
																	int traceTempVariable$var67$383_1 = cv$currentValue;
																	if(((index$i$2 + 1) == (j + 1))) {
																		{
																			{
																				double cv$temp$80$var68;
																				{
																					double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$8_3) / traceTempVariable$var67$383_1);
																					cv$temp$80$var68 = var68;
																				}
																				if(((Math.log(1.0) + Math.log((v[j]?cv$temp$80$var68:(1.0 - cv$temp$80$var68)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$80$var68:(1.0 - cv$temp$80$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$80$var68:(1.0 - cv$temp$80$var68))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$80$var68:(1.0 - cv$temp$80$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$80$var68:(1.0 - cv$temp$80$var68)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																	for(int index$i$384 = 0; index$i$384 < size; index$i$384 += 1) {
																		if(!(index$i$384 == index$i$2)) {
																			for(int index$sample27$385 = 0; index$sample27$385 < weightings.length; index$sample27$385 += 1) {
																				int distributionTempVariable$var27$387 = index$sample27$385;
																				double cv$probabilitySample27Value386 = (1.0 * distribution$sample27[((index$i$384 - 0) / 1)][index$sample27$385]);
																				int traceTempVariable$var67$388_1 = cv$currentValue;
																				if(((index$i$384 + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$81$var68;
																							{
																								double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$8_3) / traceTempVariable$var67$388_1);
																								cv$temp$81$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample27Value386) + Math.log((v[j]?cv$temp$81$var68:(1.0 - cv$temp$81$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value386) + Math.log((v[j]?cv$temp$81$var68:(1.0 - cv$temp$81$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value386) + Math.log((v[j]?cv$temp$81$var68:(1.0 - cv$temp$81$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value386) + Math.log((v[j]?cv$temp$81$var68:(1.0 - cv$temp$81$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value386) + Math.log((v[j]?cv$temp$81$var68:(1.0 - cv$temp$81$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value386);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample11$374 = 0; index$sample11$374 < weightings.length; index$sample11$374 += 1) {
																		int distributionTempVariable$var11$376 = index$sample11$374;
																		double cv$probabilitySample11Value375 = (1.0 * distribution$sample11[index$sample11$374]);
																		if((0 == j)) {
																			int traceTempVariable$var67$389_1 = cv$currentValue;
																			if(((index$i$2 + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$82$var68;
																						{
																							double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$8_3) / traceTempVariable$var67$389_1);
																							cv$temp$82$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample11Value375) + Math.log((v[j]?cv$temp$82$var68:(1.0 - cv$temp$82$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value375) + Math.log((v[j]?cv$temp$82$var68:(1.0 - cv$temp$82$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value375) + Math.log((v[j]?cv$temp$82$var68:(1.0 - cv$temp$82$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value375) + Math.log((v[j]?cv$temp$82$var68:(1.0 - cv$temp$82$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value375) + Math.log((v[j]?cv$temp$82$var68:(1.0 - cv$temp$82$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value375);
																					}
																				}
																			}
																			for(int index$i$390 = 0; index$i$390 < size; index$i$390 += 1) {
																				if(!(index$i$390 == index$i$2)) {
																					for(int index$sample27$391 = 0; index$sample27$391 < weightings.length; index$sample27$391 += 1) {
																						int distributionTempVariable$var27$393 = index$sample27$391;
																						double cv$probabilitySample27Value392 = (cv$probabilitySample11Value375 * distribution$sample27[((index$i$390 - 0) / 1)][index$sample27$391]);
																						int traceTempVariable$var67$394_1 = cv$currentValue;
																						if(((index$i$390 + 1) == (j + 1))) {
																							{
																								{
																									double cv$temp$83$var68;
																									{
																										double var68 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var63$8_3) / traceTempVariable$var67$394_1);
																										cv$temp$83$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value392) + Math.log((v[j]?cv$temp$83$var68:(1.0 - cv$temp$83$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value392) + Math.log((v[j]?cv$temp$83$var68:(1.0 - cv$temp$83$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value392) + Math.log((v[j]?cv$temp$83$var68:(1.0 - cv$temp$83$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value392) + Math.log((v[j]?cv$temp$83$var68:(1.0 - cv$temp$83$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value392) + Math.log((v[j]?cv$temp$83$var68:(1.0 - cv$temp$83$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value392);
																								}
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
																for(int index$sample5$369 = 0; index$sample5$369 < weightings.length; index$sample5$369 += 1) {
																	int distributionTempVariable$v1$371 = index$sample5$369;
																	double cv$probabilitySample5Value370 = (1.0 * distribution$sample5[index$sample5$369]);
																	if(fixedFlag$sample11) {
																		if((0 == j)) {
																			int traceTempVariable$var67$395_1 = cv$currentValue;
																			if(((index$i$2 + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$84$var68;
																						{
																							double var68 = ((((1.0 * distributionTempVariable$v1$371) + v2[j]) + traceTempVariable$var63$8_3) / traceTempVariable$var67$395_1);
																							cv$temp$84$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample5Value370) + Math.log((v[j]?cv$temp$84$var68:(1.0 - cv$temp$84$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value370) + Math.log((v[j]?cv$temp$84$var68:(1.0 - cv$temp$84$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value370) + Math.log((v[j]?cv$temp$84$var68:(1.0 - cv$temp$84$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value370) + Math.log((v[j]?cv$temp$84$var68:(1.0 - cv$temp$84$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value370) + Math.log((v[j]?cv$temp$84$var68:(1.0 - cv$temp$84$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value370);
																					}
																				}
																			}
																			for(int index$i$396 = 0; index$i$396 < size; index$i$396 += 1) {
																				if(!(index$i$396 == index$i$2)) {
																					for(int index$sample27$397 = 0; index$sample27$397 < weightings.length; index$sample27$397 += 1) {
																						int distributionTempVariable$var27$399 = index$sample27$397;
																						double cv$probabilitySample27Value398 = (cv$probabilitySample5Value370 * distribution$sample27[((index$i$396 - 0) / 1)][index$sample27$397]);
																						int traceTempVariable$var67$400_1 = cv$currentValue;
																						if(((index$i$396 + 1) == (j + 1))) {
																							{
																								{
																									double cv$temp$85$var68;
																									{
																										double var68 = ((((1.0 * distributionTempVariable$v1$371) + v2[j]) + traceTempVariable$var63$8_3) / traceTempVariable$var67$400_1);
																										cv$temp$85$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value398) + Math.log((v[j]?cv$temp$85$var68:(1.0 - cv$temp$85$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value398) + Math.log((v[j]?cv$temp$85$var68:(1.0 - cv$temp$85$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value398) + Math.log((v[j]?cv$temp$85$var68:(1.0 - cv$temp$85$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value398) + Math.log((v[j]?cv$temp$85$var68:(1.0 - cv$temp$85$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value398) + Math.log((v[j]?cv$temp$85$var68:(1.0 - cv$temp$85$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value398);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	} else {
																		if(true) {
																			for(int index$sample11$379 = 0; index$sample11$379 < weightings.length; index$sample11$379 += 1) {
																				int distributionTempVariable$var11$381 = index$sample11$379;
																				double cv$probabilitySample11Value380 = (cv$probabilitySample5Value370 * distribution$sample11[index$sample11$379]);
																				if((0 == j)) {
																					int traceTempVariable$var67$401_1 = cv$currentValue;
																					if(((index$i$2 + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$86$var68;
																								{
																									double var68 = ((((1.0 * distributionTempVariable$v1$371) + v2[j]) + traceTempVariable$var63$8_3) / traceTempVariable$var67$401_1);
																									cv$temp$86$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample11Value380) + Math.log((v[j]?cv$temp$86$var68:(1.0 - cv$temp$86$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value380) + Math.log((v[j]?cv$temp$86$var68:(1.0 - cv$temp$86$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value380) + Math.log((v[j]?cv$temp$86$var68:(1.0 - cv$temp$86$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value380) + Math.log((v[j]?cv$temp$86$var68:(1.0 - cv$temp$86$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value380) + Math.log((v[j]?cv$temp$86$var68:(1.0 - cv$temp$86$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value380);
																							}
																						}
																					}
																					for(int index$i$402 = 0; index$i$402 < size; index$i$402 += 1) {
																						if(!(index$i$402 == index$i$2)) {
																							for(int index$sample27$403 = 0; index$sample27$403 < weightings.length; index$sample27$403 += 1) {
																								int distributionTempVariable$var27$405 = index$sample27$403;
																								double cv$probabilitySample27Value404 = (cv$probabilitySample11Value380 * distribution$sample27[((index$i$402 - 0) / 1)][index$sample27$403]);
																								int traceTempVariable$var67$406_1 = cv$currentValue;
																								if(((index$i$402 + 1) == (j + 1))) {
																									{
																										{
																											double cv$temp$87$var68;
																											{
																												double var68 = ((((1.0 * distributionTempVariable$v1$371) + v2[j]) + traceTempVariable$var63$8_3) / traceTempVariable$var67$406_1);
																												cv$temp$87$var68 = var68;
																											}
																											if(((Math.log(cv$probabilitySample27Value404) + Math.log((v[j]?cv$temp$87$var68:(1.0 - cv$temp$87$var68)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value404) + Math.log((v[j]?cv$temp$87$var68:(1.0 - cv$temp$87$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value404) + Math.log((v[j]?cv$temp$87$var68:(1.0 - cv$temp$87$var68))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value404) + Math.log((v[j]?cv$temp$87$var68:(1.0 - cv$temp$87$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value404) + Math.log((v[j]?cv$temp$87$var68:(1.0 - cv$temp$87$var68)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value404);
																										}
																									}
																								}
																							}
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
															int traceTempVariable$var59$412_1 = cv$currentValue;
															if(((index$i$2 + 1) == j)) {
																int traceTempVariable$var67$424_1 = cv$currentValue;
																if(((index$i$2 + 1) == (j + 1))) {
																	{
																		{
																			double cv$temp$88$var68;
																			{
																				double var68 = ((((1.0 * v1) + traceTempVariable$var59$412_1) + traceTempVariable$var63$8_3) / traceTempVariable$var67$424_1);
																				cv$temp$88$var68 = var68;
																			}
																			if(((Math.log(1.0) + Math.log((v[j]?cv$temp$88$var68:(1.0 - cv$temp$88$var68)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$88$var68:(1.0 - cv$temp$88$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$88$var68:(1.0 - cv$temp$88$var68))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$88$var68:(1.0 - cv$temp$88$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$88$var68:(1.0 - cv$temp$88$var68)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
																for(int index$i$425 = 0; index$i$425 < size; index$i$425 += 1) {
																	if(!(index$i$425 == index$i$2)) {
																		for(int index$sample27$426 = 0; index$sample27$426 < weightings.length; index$sample27$426 += 1) {
																			int distributionTempVariable$var27$428 = index$sample27$426;
																			double cv$probabilitySample27Value427 = (1.0 * distribution$sample27[((index$i$425 - 0) / 1)][index$sample27$426]);
																			int traceTempVariable$var67$429_1 = cv$currentValue;
																			if(((index$i$425 + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$89$var68;
																						{
																							double var68 = ((((1.0 * v1) + traceTempVariable$var59$412_1) + traceTempVariable$var63$8_3) / traceTempVariable$var67$429_1);
																							cv$temp$89$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample27Value427) + Math.log((v[j]?cv$temp$89$var68:(1.0 - cv$temp$89$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value427) + Math.log((v[j]?cv$temp$89$var68:(1.0 - cv$temp$89$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value427) + Math.log((v[j]?cv$temp$89$var68:(1.0 - cv$temp$89$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value427) + Math.log((v[j]?cv$temp$89$var68:(1.0 - cv$temp$89$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value427) + Math.log((v[j]?cv$temp$89$var68:(1.0 - cv$temp$89$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value427);
																					}
																				}
																			}
																		}
																	}
																}
															}
															for(int index$i$413 = 0; index$i$413 < size; index$i$413 += 1) {
																if(!(index$i$413 == index$i$2)) {
																	for(int index$sample27$414 = 0; index$sample27$414 < weightings.length; index$sample27$414 += 1) {
																		int distributionTempVariable$var27$416 = index$sample27$414;
																		double cv$probabilitySample27Value415 = (1.0 * distribution$sample27[((index$i$413 - 0) / 1)][index$sample27$414]);
																		int traceTempVariable$var59$417_1 = cv$currentValue;
																		if(((index$i$413 + 1) == j)) {
																			int traceTempVariable$var67$430_1 = cv$currentValue;
																			if(((index$i$2 + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$90$var68;
																						{
																							double var68 = ((((1.0 * v1) + traceTempVariable$var59$417_1) + traceTempVariable$var63$8_3) / traceTempVariable$var67$430_1);
																							cv$temp$90$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample27Value415) + Math.log((v[j]?cv$temp$90$var68:(1.0 - cv$temp$90$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value415) + Math.log((v[j]?cv$temp$90$var68:(1.0 - cv$temp$90$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value415) + Math.log((v[j]?cv$temp$90$var68:(1.0 - cv$temp$90$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value415) + Math.log((v[j]?cv$temp$90$var68:(1.0 - cv$temp$90$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value415) + Math.log((v[j]?cv$temp$90$var68:(1.0 - cv$temp$90$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value415);
																					}
																				}
																			}
																			int traceTempVariable$var67$431_1 = cv$currentValue;
																			if(((index$i$413 + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$91$var68;
																						{
																							double var68 = ((((1.0 * v1) + traceTempVariable$var59$417_1) + traceTempVariable$var63$8_3) / traceTempVariable$var67$431_1);
																							cv$temp$91$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample27Value415) + Math.log((v[j]?cv$temp$91$var68:(1.0 - cv$temp$91$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value415) + Math.log((v[j]?cv$temp$91$var68:(1.0 - cv$temp$91$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value415) + Math.log((v[j]?cv$temp$91$var68:(1.0 - cv$temp$91$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value415) + Math.log((v[j]?cv$temp$91$var68:(1.0 - cv$temp$91$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value415) + Math.log((v[j]?cv$temp$91$var68:(1.0 - cv$temp$91$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value415);
																					}
																				}
																			}
																			for(int index$i$432 = 0; index$i$432 < size; index$i$432 += 1) {
																				if((!(index$i$432 == index$i$2) && !(index$i$432 == index$i$413))) {
																					for(int index$sample27$433 = 0; index$sample27$433 < weightings.length; index$sample27$433 += 1) {
																						int distributionTempVariable$var27$435 = index$sample27$433;
																						double cv$probabilitySample27Value434 = (cv$probabilitySample27Value415 * distribution$sample27[((index$i$432 - 0) / 1)][index$sample27$433]);
																						int traceTempVariable$var67$436_1 = cv$currentValue;
																						if(((index$i$432 + 1) == (j + 1))) {
																							{
																								{
																									double cv$temp$92$var68;
																									{
																										double var68 = ((((1.0 * v1) + traceTempVariable$var59$417_1) + traceTempVariable$var63$8_3) / traceTempVariable$var67$436_1);
																										cv$temp$92$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value434) + Math.log((v[j]?cv$temp$92$var68:(1.0 - cv$temp$92$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value434) + Math.log((v[j]?cv$temp$92$var68:(1.0 - cv$temp$92$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value434) + Math.log((v[j]?cv$temp$92$var68:(1.0 - cv$temp$92$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value434) + Math.log((v[j]?cv$temp$92$var68:(1.0 - cv$temp$92$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value434) + Math.log((v[j]?cv$temp$92$var68:(1.0 - cv$temp$92$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value434);
																								}
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
																for(int index$sample5$408 = 0; index$sample5$408 < weightings.length; index$sample5$408 += 1) {
																	int distributionTempVariable$v1$410 = index$sample5$408;
																	double cv$probabilitySample5Value409 = (1.0 * distribution$sample5[index$sample5$408]);
																	int traceTempVariable$var59$418_1 = cv$currentValue;
																	if(((index$i$2 + 1) == j)) {
																		int traceTempVariable$var67$437_1 = cv$currentValue;
																		if(((index$i$2 + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$93$var68;
																					{
																						double var68 = ((((1.0 * distributionTempVariable$v1$410) + traceTempVariable$var59$418_1) + traceTempVariable$var63$8_3) / traceTempVariable$var67$437_1);
																						cv$temp$93$var68 = var68;
																					}
																					if(((Math.log(cv$probabilitySample5Value409) + Math.log((v[j]?cv$temp$93$var68:(1.0 - cv$temp$93$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value409) + Math.log((v[j]?cv$temp$93$var68:(1.0 - cv$temp$93$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value409) + Math.log((v[j]?cv$temp$93$var68:(1.0 - cv$temp$93$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value409) + Math.log((v[j]?cv$temp$93$var68:(1.0 - cv$temp$93$var68)))))) + 1)) + (Math.log(cv$probabilitySample5Value409) + Math.log((v[j]?cv$temp$93$var68:(1.0 - cv$temp$93$var68)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value409);
																				}
																			}
																		}
																		for(int index$i$438 = 0; index$i$438 < size; index$i$438 += 1) {
																			if(!(index$i$438 == index$i$2)) {
																				for(int index$sample27$439 = 0; index$sample27$439 < weightings.length; index$sample27$439 += 1) {
																					int distributionTempVariable$var27$441 = index$sample27$439;
																					double cv$probabilitySample27Value440 = (cv$probabilitySample5Value409 * distribution$sample27[((index$i$438 - 0) / 1)][index$sample27$439]);
																					int traceTempVariable$var67$442_1 = cv$currentValue;
																					if(((index$i$438 + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$94$var68;
																								{
																									double var68 = ((((1.0 * distributionTempVariable$v1$410) + traceTempVariable$var59$418_1) + traceTempVariable$var63$8_3) / traceTempVariable$var67$442_1);
																									cv$temp$94$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample27Value440) + Math.log((v[j]?cv$temp$94$var68:(1.0 - cv$temp$94$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value440) + Math.log((v[j]?cv$temp$94$var68:(1.0 - cv$temp$94$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value440) + Math.log((v[j]?cv$temp$94$var68:(1.0 - cv$temp$94$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value440) + Math.log((v[j]?cv$temp$94$var68:(1.0 - cv$temp$94$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value440) + Math.log((v[j]?cv$temp$94$var68:(1.0 - cv$temp$94$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value440);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																	for(int index$i$419 = 0; index$i$419 < size; index$i$419 += 1) {
																		if(!(index$i$419 == index$i$2)) {
																			for(int index$sample27$420 = 0; index$sample27$420 < weightings.length; index$sample27$420 += 1) {
																				int distributionTempVariable$var27$422 = index$sample27$420;
																				double cv$probabilitySample27Value421 = (cv$probabilitySample5Value409 * distribution$sample27[((index$i$419 - 0) / 1)][index$sample27$420]);
																				int traceTempVariable$var59$423_1 = cv$currentValue;
																				if(((index$i$419 + 1) == j)) {
																					int traceTempVariable$var67$443_1 = cv$currentValue;
																					if(((index$i$2 + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$95$var68;
																								{
																									double var68 = ((((1.0 * distributionTempVariable$v1$410) + traceTempVariable$var59$423_1) + traceTempVariable$var63$8_3) / traceTempVariable$var67$443_1);
																									cv$temp$95$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample27Value421) + Math.log((v[j]?cv$temp$95$var68:(1.0 - cv$temp$95$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value421) + Math.log((v[j]?cv$temp$95$var68:(1.0 - cv$temp$95$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value421) + Math.log((v[j]?cv$temp$95$var68:(1.0 - cv$temp$95$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value421) + Math.log((v[j]?cv$temp$95$var68:(1.0 - cv$temp$95$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value421) + Math.log((v[j]?cv$temp$95$var68:(1.0 - cv$temp$95$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value421);
																							}
																						}
																					}
																					int traceTempVariable$var67$444_1 = cv$currentValue;
																					if(((index$i$419 + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$96$var68;
																								{
																									double var68 = ((((1.0 * distributionTempVariable$v1$410) + traceTempVariable$var59$423_1) + traceTempVariable$var63$8_3) / traceTempVariable$var67$444_1);
																									cv$temp$96$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample27Value421) + Math.log((v[j]?cv$temp$96$var68:(1.0 - cv$temp$96$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value421) + Math.log((v[j]?cv$temp$96$var68:(1.0 - cv$temp$96$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value421) + Math.log((v[j]?cv$temp$96$var68:(1.0 - cv$temp$96$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value421) + Math.log((v[j]?cv$temp$96$var68:(1.0 - cv$temp$96$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value421) + Math.log((v[j]?cv$temp$96$var68:(1.0 - cv$temp$96$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value421);
																							}
																						}
																					}
																					for(int index$i$445 = 0; index$i$445 < size; index$i$445 += 1) {
																						if((!(index$i$445 == index$i$2) && !(index$i$445 == index$i$419))) {
																							for(int index$sample27$446 = 0; index$sample27$446 < weightings.length; index$sample27$446 += 1) {
																								int distributionTempVariable$var27$448 = index$sample27$446;
																								double cv$probabilitySample27Value447 = (cv$probabilitySample27Value421 * distribution$sample27[((index$i$445 - 0) / 1)][index$sample27$446]);
																								int traceTempVariable$var67$449_1 = cv$currentValue;
																								if(((index$i$445 + 1) == (j + 1))) {
																									{
																										{
																											double cv$temp$97$var68;
																											{
																												double var68 = ((((1.0 * distributionTempVariable$v1$410) + traceTempVariable$var59$423_1) + traceTempVariable$var63$8_3) / traceTempVariable$var67$449_1);
																												cv$temp$97$var68 = var68;
																											}
																											if(((Math.log(cv$probabilitySample27Value447) + Math.log((v[j]?cv$temp$97$var68:(1.0 - cv$temp$97$var68)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value447) + Math.log((v[j]?cv$temp$97$var68:(1.0 - cv$temp$97$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value447) + Math.log((v[j]?cv$temp$97$var68:(1.0 - cv$temp$97$var68))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value447) + Math.log((v[j]?cv$temp$97$var68:(1.0 - cv$temp$97$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value447) + Math.log((v[j]?cv$temp$97$var68:(1.0 - cv$temp$97$var68)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value447);
																										}
																									}
																								}
																							}
																						}
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

	private final void sample5() {
		if(true) {
			int cv$numNumStates = 0;
			{
				cv$numNumStates = Math.max(cv$numNumStates, weightings.length);
			}
			double[] cv$stateProbabilityLocal = cv$var5$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
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
					int cv$temp$1$$var2937;
					{
						cv$temp$1$$var2937 = weightings.length;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var2937))?Math.log(cv$temp$0$weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							for(int j = 0; j < size; j += 1) {
								int traceTempVariable$v1$1_2 = cv$currentValue;
								{
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										if(fixedFlag$sample11) {
											if((0 == j)) {
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														int traceTempVariable$var63$8_2 = v2[k];
														if((k == (j + 1))) {
															if((0 == (j + 1))) {
																{
																	{
																		double cv$temp$2$var68;
																		{
																			double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$8_2) / v2[(j + 1)]);
																			cv$temp$2$var68 = var68;
																		}
																		if(((Math.log(1.0) + Math.log((v[j]?cv$temp$2$var68:(1.0 - cv$temp$2$var68)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$2$var68:(1.0 - cv$temp$2$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$2$var68:(1.0 - cv$temp$2$var68))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$2$var68:(1.0 - cv$temp$2$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$2$var68:(1.0 - cv$temp$2$var68)))));
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
												for(int index$sample11$4 = 0; index$sample11$4 < weightings.length; index$sample11$4 += 1) {
													int distributionTempVariable$var11$6 = index$sample11$4;
													double cv$probabilitySample11Value5 = (1.0 * distribution$sample11[index$sample11$4]);
													if((0 == j)) {
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var63$9_2 = v2[k];
																if((k == (j + 1))) {
																	if((0 == (j + 1))) {
																		{
																			{
																				double cv$temp$3$var68;
																				{
																					double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$9_2) / v2[(j + 1)]);
																					cv$temp$3$var68 = var68;
																				}
																				if(((Math.log(cv$probabilitySample11Value5) + Math.log((v[j]?cv$temp$3$var68:(1.0 - cv$temp$3$var68)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value5) + Math.log((v[j]?cv$temp$3$var68:(1.0 - cv$temp$3$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value5) + Math.log((v[j]?cv$temp$3$var68:(1.0 - cv$temp$3$var68))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value5) + Math.log((v[j]?cv$temp$3$var68:(1.0 - cv$temp$3$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value5) + Math.log((v[j]?cv$temp$3$var68:(1.0 - cv$temp$3$var68)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value5);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample11$16 = 0; index$sample11$16 < weightings.length; index$sample11$16 += 1) {
																			int distributionTempVariable$var11$18 = index$sample11$16;
																			double cv$probabilitySample11Value17 = (cv$probabilitySample11Value5 * distribution$sample11[index$sample11$16]);
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$4$var68;
																						{
																							double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$9_2) / v2[(j + 1)]);
																							cv$temp$4$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?cv$temp$4$var68:(1.0 - cv$temp$4$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?cv$temp$4$var68:(1.0 - cv$temp$4$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?cv$temp$4$var68:(1.0 - cv$temp$4$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?cv$temp$4$var68:(1.0 - cv$temp$4$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value17) + Math.log((v[j]?cv$temp$4$var68:(1.0 - cv$temp$4$var68)))));
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
														if(!true) {
															for(int index$sample11$10 = 0; index$sample11$10 < weightings.length; index$sample11$10 += 1) {
																int distributionTempVariable$var11$12 = index$sample11$10;
																double cv$probabilitySample11Value11 = (cv$probabilitySample11Value5 * distribution$sample11[index$sample11$10]);
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var63$13_2 = v2[k];
																		if((k == (j + 1))) {
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$5$var68;
																						{
																							double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$13_2) / v2[(j + 1)]);
																							cv$temp$5$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample11Value11) + Math.log((v[j]?cv$temp$5$var68:(1.0 - cv$temp$5$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value11) + Math.log((v[j]?cv$temp$5$var68:(1.0 - cv$temp$5$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value11) + Math.log((v[j]?cv$temp$5$var68:(1.0 - cv$temp$5$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value11) + Math.log((v[j]?cv$temp$5$var68:(1.0 - cv$temp$5$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value11) + Math.log((v[j]?cv$temp$5$var68:(1.0 - cv$temp$5$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value11);
																					}
																				}
																			}
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$6$var68;
																						{
																							double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$13_2) / v2[(j + 1)]);
																							cv$temp$6$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample11Value11) + Math.log((v[j]?cv$temp$6$var68:(1.0 - cv$temp$6$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value11) + Math.log((v[j]?cv$temp$6$var68:(1.0 - cv$temp$6$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value11) + Math.log((v[j]?cv$temp$6$var68:(1.0 - cv$temp$6$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value11) + Math.log((v[j]?cv$temp$6$var68:(1.0 - cv$temp$6$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value11) + Math.log((v[j]?cv$temp$6$var68:(1.0 - cv$temp$6$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value11);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample11$22 = 0; index$sample11$22 < weightings.length; index$sample11$22 += 1) {
																					int distributionTempVariable$var11$24 = index$sample11$22;
																					double cv$probabilitySample11Value23 = (cv$probabilitySample11Value11 * distribution$sample11[index$sample11$22]);
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$7$var68;
																								{
																									double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$13_2) / v2[(j + 1)]);
																									cv$temp$7$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample11Value23) + Math.log((v[j]?cv$temp$7$var68:(1.0 - cv$temp$7$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value23) + Math.log((v[j]?cv$temp$7$var68:(1.0 - cv$temp$7$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value23) + Math.log((v[j]?cv$temp$7$var68:(1.0 - cv$temp$7$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value23) + Math.log((v[j]?cv$temp$7$var68:(1.0 - cv$temp$7$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value23) + Math.log((v[j]?cv$temp$7$var68:(1.0 - cv$temp$7$var68)))));
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
										if(fixedFlag$sample11) {
											if((0 == j)) {
												if(fixedFlag$sample27) {
													for(int i = 0; i < size; i += 1) {
														for(int k = 0; k < (size + 1); k += 1) {
															if(((i + 1) == k)) {
																int traceTempVariable$var63$31_3 = v2[k];
																if((k == (j + 1))) {
																	if((0 == (j + 1))) {
																		{
																			{
																				double cv$temp$8$var68;
																				{
																					double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$31_3) / v2[(j + 1)]);
																					cv$temp$8$var68 = var68;
																				}
																				if(((Math.log(1.0) + Math.log((v[j]?cv$temp$8$var68:(1.0 - cv$temp$8$var68)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$8$var68:(1.0 - cv$temp$8$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$8$var68:(1.0 - cv$temp$8$var68))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$8$var68:(1.0 - cv$temp$8$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$8$var68:(1.0 - cv$temp$8$var68)))));
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
													for(int i = 0; i < size; i += 1) {
														if(true) {
															for(int index$sample27$33 = 0; index$sample27$33 < weightings.length; index$sample27$33 += 1) {
																int distributionTempVariable$var27$35 = index$sample27$33;
																double cv$probabilitySample27Value34 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$33]);
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((i + 1) == k)) {
																		int traceTempVariable$var63$36_2 = v2[k];
																		if((k == (j + 1))) {
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$9$var68;
																						{
																							double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$36_2) / v2[(j + 1)]);
																							cv$temp$9$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample27Value34) + Math.log((v[j]?cv$temp$9$var68:(1.0 - cv$temp$9$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value34) + Math.log((v[j]?cv$temp$9$var68:(1.0 - cv$temp$9$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value34) + Math.log((v[j]?cv$temp$9$var68:(1.0 - cv$temp$9$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value34) + Math.log((v[j]?cv$temp$9$var68:(1.0 - cv$temp$9$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value34) + Math.log((v[j]?cv$temp$9$var68:(1.0 - cv$temp$9$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value34);
																					}
																				}
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
												for(int index$sample11$27 = 0; index$sample11$27 < weightings.length; index$sample11$27 += 1) {
													int distributionTempVariable$var11$29 = index$sample11$27;
													double cv$probabilitySample11Value28 = (1.0 * distribution$sample11[index$sample11$27]);
													if((0 == j)) {
														if(fixedFlag$sample27) {
															for(int i = 0; i < size; i += 1) {
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((i + 1) == k)) {
																		int traceTempVariable$var63$37_3 = v2[k];
																		if((k == (j + 1))) {
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$10$var68;
																						{
																							double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$37_3) / v2[(j + 1)]);
																							cv$temp$10$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample11Value28) + Math.log((v[j]?cv$temp$10$var68:(1.0 - cv$temp$10$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value28) + Math.log((v[j]?cv$temp$10$var68:(1.0 - cv$temp$10$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value28) + Math.log((v[j]?cv$temp$10$var68:(1.0 - cv$temp$10$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value28) + Math.log((v[j]?cv$temp$10$var68:(1.0 - cv$temp$10$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value28) + Math.log((v[j]?cv$temp$10$var68:(1.0 - cv$temp$10$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value28);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample11$46 = 0; index$sample11$46 < weightings.length; index$sample11$46 += 1) {
																					int distributionTempVariable$var11$48 = index$sample11$46;
																					double cv$probabilitySample11Value47 = (cv$probabilitySample11Value28 * distribution$sample11[index$sample11$46]);
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$11$var68;
																								{
																									double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$37_3) / v2[(j + 1)]);
																									cv$temp$11$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample11Value47) + Math.log((v[j]?cv$temp$11$var68:(1.0 - cv$temp$11$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value47) + Math.log((v[j]?cv$temp$11$var68:(1.0 - cv$temp$11$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value47) + Math.log((v[j]?cv$temp$11$var68:(1.0 - cv$temp$11$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value47) + Math.log((v[j]?cv$temp$11$var68:(1.0 - cv$temp$11$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value47) + Math.log((v[j]?cv$temp$11$var68:(1.0 - cv$temp$11$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value47);
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
																	for(int index$sample27$39 = 0; index$sample27$39 < weightings.length; index$sample27$39 += 1) {
																		int distributionTempVariable$var27$41 = index$sample27$39;
																		double cv$probabilitySample27Value40 = (cv$probabilitySample11Value28 * distribution$sample27[((i - 0) / 1)][index$sample27$39]);
																		for(int k = 0; k < (size + 1); k += 1) {
																			if(((i + 1) == k)) {
																				int traceTempVariable$var63$42_2 = v2[k];
																				if((k == (j + 1))) {
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$12$var68;
																								{
																									double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$42_2) / v2[(j + 1)]);
																									cv$temp$12$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample27Value40) + Math.log((v[j]?cv$temp$12$var68:(1.0 - cv$temp$12$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value40) + Math.log((v[j]?cv$temp$12$var68:(1.0 - cv$temp$12$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value40) + Math.log((v[j]?cv$temp$12$var68:(1.0 - cv$temp$12$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value40) + Math.log((v[j]?cv$temp$12$var68:(1.0 - cv$temp$12$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value40) + Math.log((v[j]?cv$temp$12$var68:(1.0 - cv$temp$12$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value40);
																							}
																						}
																					}
																					if(!true) {
																						for(int index$sample11$51 = 0; index$sample11$51 < weightings.length; index$sample11$51 += 1) {
																							int distributionTempVariable$var11$53 = index$sample11$51;
																							double cv$probabilitySample11Value52 = (cv$probabilitySample27Value40 * distribution$sample11[index$sample11$51]);
																							if((0 == (j + 1))) {
																								{
																									{
																										double cv$temp$13$var68;
																										{
																											double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$42_2) / v2[(j + 1)]);
																											cv$temp$13$var68 = var68;
																										}
																										if(((Math.log(cv$probabilitySample11Value52) + Math.log((v[j]?cv$temp$13$var68:(1.0 - cv$temp$13$var68)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value52) + Math.log((v[j]?cv$temp$13$var68:(1.0 - cv$temp$13$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value52) + Math.log((v[j]?cv$temp$13$var68:(1.0 - cv$temp$13$var68))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value52) + Math.log((v[j]?cv$temp$13$var68:(1.0 - cv$temp$13$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value52) + Math.log((v[j]?cv$temp$13$var68:(1.0 - cv$temp$13$var68)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value52);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
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
											for(int i = 0; i < size; i += 1) {
												if(((i + 1) == j)) {
													if(fixedFlag$sample11) {
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var63$61_2 = v2[k];
																if((k == (j + 1))) {
																	if((0 == (j + 1))) {
																		{
																			{
																				double cv$temp$14$var68;
																				{
																					double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$61_2) / v2[(j + 1)]);
																					cv$temp$14$var68 = var68;
																				}
																				if(((Math.log(1.0) + Math.log((v[j]?cv$temp$14$var68:(1.0 - cv$temp$14$var68)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$14$var68:(1.0 - cv$temp$14$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$14$var68:(1.0 - cv$temp$14$var68))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$14$var68:(1.0 - cv$temp$14$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$14$var68:(1.0 - cv$temp$14$var68)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample11$62 = 0; index$sample11$62 < weightings.length; index$sample11$62 += 1) {
																int distributionTempVariable$var11$64 = index$sample11$62;
																double cv$probabilitySample11Value63 = (1.0 * distribution$sample11[index$sample11$62]);
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var63$65_2 = v2[k];
																		if((k == (j + 1))) {
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$15$var68;
																						{
																							double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$65_2) / v2[(j + 1)]);
																							cv$temp$15$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample11Value63) + Math.log((v[j]?cv$temp$15$var68:(1.0 - cv$temp$15$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value63) + Math.log((v[j]?cv$temp$15$var68:(1.0 - cv$temp$15$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value63) + Math.log((v[j]?cv$temp$15$var68:(1.0 - cv$temp$15$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value63) + Math.log((v[j]?cv$temp$15$var68:(1.0 - cv$temp$15$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value63) + Math.log((v[j]?cv$temp$15$var68:(1.0 - cv$temp$15$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value63);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample11$73 = 0; index$sample11$73 < weightings.length; index$sample11$73 += 1) {
																					int distributionTempVariable$var11$75 = index$sample11$73;
																					double cv$probabilitySample11Value74 = (cv$probabilitySample11Value63 * distribution$sample11[index$sample11$73]);
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$16$var68;
																								{
																									double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$65_2) / v2[(j + 1)]);
																									cv$temp$16$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample11Value74) + Math.log((v[j]?cv$temp$16$var68:(1.0 - cv$temp$16$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value74) + Math.log((v[j]?cv$temp$16$var68:(1.0 - cv$temp$16$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value74) + Math.log((v[j]?cv$temp$16$var68:(1.0 - cv$temp$16$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value74) + Math.log((v[j]?cv$temp$16$var68:(1.0 - cv$temp$16$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value74) + Math.log((v[j]?cv$temp$16$var68:(1.0 - cv$temp$16$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value74);
																							}
																						}
																					}
																				}
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
													for(int index$sample27$57 = 0; index$sample27$57 < weightings.length; index$sample27$57 += 1) {
														int distributionTempVariable$var27$59 = index$sample27$57;
														double cv$probabilitySample27Value58 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$57]);
														if(((i + 1) == j)) {
															if(fixedFlag$sample11) {
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var63$66_2 = v2[k];
																		if((k == (j + 1))) {
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$17$var68;
																						{
																							double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$66_2) / v2[(j + 1)]);
																							cv$temp$17$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample27Value58) + Math.log((v[j]?cv$temp$17$var68:(1.0 - cv$temp$17$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value58) + Math.log((v[j]?cv$temp$17$var68:(1.0 - cv$temp$17$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value58) + Math.log((v[j]?cv$temp$17$var68:(1.0 - cv$temp$17$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value58) + Math.log((v[j]?cv$temp$17$var68:(1.0 - cv$temp$17$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value58) + Math.log((v[j]?cv$temp$17$var68:(1.0 - cv$temp$17$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value58);
																					}
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample11$67 = 0; index$sample11$67 < weightings.length; index$sample11$67 += 1) {
																		int distributionTempVariable$var11$69 = index$sample11$67;
																		double cv$probabilitySample11Value68 = (cv$probabilitySample27Value58 * distribution$sample11[index$sample11$67]);
																		for(int k = 0; k < (size + 1); k += 1) {
																			if((0 == k)) {
																				int traceTempVariable$var63$70_2 = v2[k];
																				if((k == (j + 1))) {
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$18$var68;
																								{
																									double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$70_2) / v2[(j + 1)]);
																									cv$temp$18$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample11Value68) + Math.log((v[j]?cv$temp$18$var68:(1.0 - cv$temp$18$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value68) + Math.log((v[j]?cv$temp$18$var68:(1.0 - cv$temp$18$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value68) + Math.log((v[j]?cv$temp$18$var68:(1.0 - cv$temp$18$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value68) + Math.log((v[j]?cv$temp$18$var68:(1.0 - cv$temp$18$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value68) + Math.log((v[j]?cv$temp$18$var68:(1.0 - cv$temp$18$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value68);
																							}
																						}
																					}
																					if(!true) {
																						for(int index$sample11$79 = 0; index$sample11$79 < weightings.length; index$sample11$79 += 1) {
																							int distributionTempVariable$var11$81 = index$sample11$79;
																							double cv$probabilitySample11Value80 = (cv$probabilitySample11Value68 * distribution$sample11[index$sample11$79]);
																							if((0 == (j + 1))) {
																								{
																									{
																										double cv$temp$19$var68;
																										{
																											double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$70_2) / v2[(j + 1)]);
																											cv$temp$19$var68 = var68;
																										}
																										if(((Math.log(cv$probabilitySample11Value80) + Math.log((v[j]?cv$temp$19$var68:(1.0 - cv$temp$19$var68)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value80) + Math.log((v[j]?cv$temp$19$var68:(1.0 - cv$temp$19$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value80) + Math.log((v[j]?cv$temp$19$var68:(1.0 - cv$temp$19$var68))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value80) + Math.log((v[j]?cv$temp$19$var68:(1.0 - cv$temp$19$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value80) + Math.log((v[j]?cv$temp$19$var68:(1.0 - cv$temp$19$var68)))));
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
										if(fixedFlag$sample27) {
											for(int i = 0; i < size; i += 1) {
												if(((i + 1) == j)) {
													for(int index$i$89_1 = 0; index$i$89_1 < size; index$i$89_1 += 1) {
														for(int k = 0; k < (size + 1); k += 1) {
															if(((index$i$89_1 + 1) == k)) {
																int traceTempVariable$var63$89_3 = v2[k];
																if((k == (j + 1))) {
																	if(fixedFlag$sample11) {
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$20$var68;
																					{
																						double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$89_3) / v2[(j + 1)]);
																						cv$temp$20$var68 = var68;
																					}
																					if(((Math.log(1.0) + Math.log((v[j]?cv$temp$20$var68:(1.0 - cv$temp$20$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$20$var68:(1.0 - cv$temp$20$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$20$var68:(1.0 - cv$temp$20$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$20$var68:(1.0 - cv$temp$20$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$20$var68:(1.0 - cv$temp$20$var68)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	} else {
																		if(true) {
																			for(int index$sample11$97 = 0; index$sample11$97 < weightings.length; index$sample11$97 += 1) {
																				int distributionTempVariable$var11$99 = index$sample11$97;
																				double cv$probabilitySample11Value98 = (1.0 * distribution$sample11[index$sample11$97]);
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$21$var68;
																							{
																								double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$89_3) / v2[(j + 1)]);
																								cv$temp$21$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample11Value98) + Math.log((v[j]?cv$temp$21$var68:(1.0 - cv$temp$21$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value98) + Math.log((v[j]?cv$temp$21$var68:(1.0 - cv$temp$21$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value98) + Math.log((v[j]?cv$temp$21$var68:(1.0 - cv$temp$21$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value98) + Math.log((v[j]?cv$temp$21$var68:(1.0 - cv$temp$21$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value98) + Math.log((v[j]?cv$temp$21$var68:(1.0 - cv$temp$21$var68)))));
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
										} else {
											for(int i = 0; i < size; i += 1) {
												if(true) {
													for(int index$sample27$85 = 0; index$sample27$85 < weightings.length; index$sample27$85 += 1) {
														int distributionTempVariable$var27$87 = index$sample27$85;
														double cv$probabilitySample27Value86 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$85]);
														if(((i + 1) == j)) {
															for(int k = 0; k < (size + 1); k += 1) {
																if(((i + 1) == k)) {
																	int traceTempVariable$var63$90_2 = v2[k];
																	if((k == (j + 1))) {
																		if(fixedFlag$sample11) {
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$22$var68;
																						{
																							double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$90_2) / v2[(j + 1)]);
																							cv$temp$22$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample27Value86) + Math.log((v[j]?cv$temp$22$var68:(1.0 - cv$temp$22$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value86) + Math.log((v[j]?cv$temp$22$var68:(1.0 - cv$temp$22$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value86) + Math.log((v[j]?cv$temp$22$var68:(1.0 - cv$temp$22$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value86) + Math.log((v[j]?cv$temp$22$var68:(1.0 - cv$temp$22$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value86) + Math.log((v[j]?cv$temp$22$var68:(1.0 - cv$temp$22$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value86);
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample11$102 = 0; index$sample11$102 < weightings.length; index$sample11$102 += 1) {
																					int distributionTempVariable$var11$104 = index$sample11$102;
																					double cv$probabilitySample11Value103 = (cv$probabilitySample27Value86 * distribution$sample11[index$sample11$102]);
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$23$var68;
																								{
																									double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$90_2) / v2[(j + 1)]);
																									cv$temp$23$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample11Value103) + Math.log((v[j]?cv$temp$23$var68:(1.0 - cv$temp$23$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value103) + Math.log((v[j]?cv$temp$23$var68:(1.0 - cv$temp$23$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value103) + Math.log((v[j]?cv$temp$23$var68:(1.0 - cv$temp$23$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value103) + Math.log((v[j]?cv$temp$23$var68:(1.0 - cv$temp$23$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value103) + Math.log((v[j]?cv$temp$23$var68:(1.0 - cv$temp$23$var68)))));
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
															for(int index$i$91 = 0; index$i$91 < size; index$i$91 += 1) {
																if(!(index$i$91 == i)) {
																	for(int index$sample27$92 = 0; index$sample27$92 < weightings.length; index$sample27$92 += 1) {
																		int distributionTempVariable$var27$94 = index$sample27$92;
																		double cv$probabilitySample27Value93 = (cv$probabilitySample27Value86 * distribution$sample27[((index$i$91 - 0) / 1)][index$sample27$92]);
																		for(int k = 0; k < (size + 1); k += 1) {
																			if(((index$i$91 + 1) == k)) {
																				int traceTempVariable$var63$95_2 = v2[k];
																				if((k == (j + 1))) {
																					if(fixedFlag$sample11) {
																						if((0 == (j + 1))) {
																							{
																								{
																									double cv$temp$24$var68;
																									{
																										double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$95_2) / v2[(j + 1)]);
																										cv$temp$24$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value93) + Math.log((v[j]?cv$temp$24$var68:(1.0 - cv$temp$24$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value93) + Math.log((v[j]?cv$temp$24$var68:(1.0 - cv$temp$24$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value93) + Math.log((v[j]?cv$temp$24$var68:(1.0 - cv$temp$24$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value93) + Math.log((v[j]?cv$temp$24$var68:(1.0 - cv$temp$24$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value93) + Math.log((v[j]?cv$temp$24$var68:(1.0 - cv$temp$24$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value93);
																								}
																							}
																						}
																					} else {
																						if(true) {
																							for(int index$sample11$107 = 0; index$sample11$107 < weightings.length; index$sample11$107 += 1) {
																								int distributionTempVariable$var11$109 = index$sample11$107;
																								double cv$probabilitySample11Value108 = (cv$probabilitySample27Value93 * distribution$sample11[index$sample11$107]);
																								if((0 == (j + 1))) {
																									{
																										{
																											double cv$temp$25$var68;
																											{
																												double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$95_2) / v2[(j + 1)]);
																												cv$temp$25$var68 = var68;
																											}
																											if(((Math.log(cv$probabilitySample11Value108) + Math.log((v[j]?cv$temp$25$var68:(1.0 - cv$temp$25$var68)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value108) + Math.log((v[j]?cv$temp$25$var68:(1.0 - cv$temp$25$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value108) + Math.log((v[j]?cv$temp$25$var68:(1.0 - cv$temp$25$var68))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value108) + Math.log((v[j]?cv$temp$25$var68:(1.0 - cv$temp$25$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value108) + Math.log((v[j]?cv$temp$25$var68:(1.0 - cv$temp$25$var68)))));
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
										if(fixedFlag$sample11) {
											if((0 == j)) {
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														int traceTempVariable$var63$116_2 = v2[k];
														if((k == (j + 1))) {
															if(fixedFlag$sample27) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == (j + 1))) {
																		{
																			{
																				double cv$temp$26$var68;
																				{
																					double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$116_2) / v2[(j + 1)]);
																					cv$temp$26$var68 = var68;
																				}
																				if(((Math.log(1.0) + Math.log((v[j]?cv$temp$26$var68:(1.0 - cv$temp$26$var68)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$26$var68:(1.0 - cv$temp$26$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$26$var68:(1.0 - cv$temp$26$var68))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$26$var68:(1.0 - cv$temp$26$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$26$var68:(1.0 - cv$temp$26$var68)))));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																			if(((i + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$27$var68;
																						{
																							double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$116_2) / v2[(j + 1)]);
																							cv$temp$27$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample27Value125) + Math.log((v[j]?cv$temp$27$var68:(1.0 - cv$temp$27$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value125) + Math.log((v[j]?cv$temp$27$var68:(1.0 - cv$temp$27$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value125) + Math.log((v[j]?cv$temp$27$var68:(1.0 - cv$temp$27$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value125) + Math.log((v[j]?cv$temp$27$var68:(1.0 - cv$temp$27$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value125) + Math.log((v[j]?cv$temp$27$var68:(1.0 - cv$temp$27$var68)))));
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
										} else {
											if(true) {
												for(int index$sample11$112 = 0; index$sample11$112 < weightings.length; index$sample11$112 += 1) {
													int distributionTempVariable$var11$114 = index$sample11$112;
													double cv$probabilitySample11Value113 = (1.0 * distribution$sample11[index$sample11$112]);
													if((0 == j)) {
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var63$117_2 = v2[k];
																if((k == (j + 1))) {
																	if(fixedFlag$sample27) {
																		for(int i = 0; i < size; i += 1) {
																			if(((i + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$28$var68;
																						{
																							double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$117_2) / v2[(j + 1)]);
																							cv$temp$28$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample11Value113) + Math.log((v[j]?cv$temp$28$var68:(1.0 - cv$temp$28$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value113) + Math.log((v[j]?cv$temp$28$var68:(1.0 - cv$temp$28$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value113) + Math.log((v[j]?cv$temp$28$var68:(1.0 - cv$temp$28$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value113) + Math.log((v[j]?cv$temp$28$var68:(1.0 - cv$temp$28$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value113) + Math.log((v[j]?cv$temp$28$var68:(1.0 - cv$temp$28$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value113);
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
																					if(((i + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$29$var68;
																								{
																									double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$117_2) / v2[(j + 1)]);
																									cv$temp$29$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample27Value131) + Math.log((v[j]?cv$temp$29$var68:(1.0 - cv$temp$29$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value131) + Math.log((v[j]?cv$temp$29$var68:(1.0 - cv$temp$29$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value131) + Math.log((v[j]?cv$temp$29$var68:(1.0 - cv$temp$29$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value131) + Math.log((v[j]?cv$temp$29$var68:(1.0 - cv$temp$29$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value131) + Math.log((v[j]?cv$temp$29$var68:(1.0 - cv$temp$29$var68)))));
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
														if(!true) {
															for(int index$sample11$118 = 0; index$sample11$118 < weightings.length; index$sample11$118 += 1) {
																int distributionTempVariable$var11$120 = index$sample11$118;
																double cv$probabilitySample11Value119 = (cv$probabilitySample11Value113 * distribution$sample11[index$sample11$118]);
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var63$121_2 = v2[k];
																		if((k == (j + 1))) {
																			if(fixedFlag$sample27) {
																				for(int i = 0; i < size; i += 1) {
																					if(((i + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$30$var68;
																								{
																									double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$121_2) / v2[(j + 1)]);
																									cv$temp$30$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample11Value119) + Math.log((v[j]?cv$temp$30$var68:(1.0 - cv$temp$30$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value119) + Math.log((v[j]?cv$temp$30$var68:(1.0 - cv$temp$30$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value119) + Math.log((v[j]?cv$temp$30$var68:(1.0 - cv$temp$30$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value119) + Math.log((v[j]?cv$temp$30$var68:(1.0 - cv$temp$30$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value119) + Math.log((v[j]?cv$temp$30$var68:(1.0 - cv$temp$30$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value119);
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
																							if(((i + 1) == (j + 1))) {
																								{
																									{
																										double cv$temp$31$var68;
																										{
																											double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$121_2) / v2[(j + 1)]);
																											cv$temp$31$var68 = var68;
																										}
																										if(((Math.log(cv$probabilitySample27Value137) + Math.log((v[j]?cv$temp$31$var68:(1.0 - cv$temp$31$var68)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value137) + Math.log((v[j]?cv$temp$31$var68:(1.0 - cv$temp$31$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value137) + Math.log((v[j]?cv$temp$31$var68:(1.0 - cv$temp$31$var68))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value137) + Math.log((v[j]?cv$temp$31$var68:(1.0 - cv$temp$31$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value137) + Math.log((v[j]?cv$temp$31$var68:(1.0 - cv$temp$31$var68)))));
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
										if(fixedFlag$sample11) {
											if((0 == j)) {
												if(fixedFlag$sample27) {
													for(int i = 0; i < size; i += 1) {
														for(int k = 0; k < (size + 1); k += 1) {
															if(((i + 1) == k)) {
																int traceTempVariable$var63$145_3 = v2[k];
																if((k == (j + 1))) {
																	for(int index$i$157_1 = 0; index$i$157_1 < size; index$i$157_1 += 1) {
																		if(((index$i$157_1 + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$32$var68;
																					{
																						double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$145_3) / v2[(j + 1)]);
																						cv$temp$32$var68 = var68;
																					}
																					if(((Math.log(1.0) + Math.log((v[j]?cv$temp$32$var68:(1.0 - cv$temp$32$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$32$var68:(1.0 - cv$temp$32$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$32$var68:(1.0 - cv$temp$32$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$32$var68:(1.0 - cv$temp$32$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$32$var68:(1.0 - cv$temp$32$var68)))));
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
															for(int index$sample27$147 = 0; index$sample27$147 < weightings.length; index$sample27$147 += 1) {
																int distributionTempVariable$var27$149 = index$sample27$147;
																double cv$probabilitySample27Value148 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$147]);
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((i + 1) == k)) {
																		int traceTempVariable$var63$150_2 = v2[k];
																		if((k == (j + 1))) {
																			if(((i + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$33$var68;
																						{
																							double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$150_2) / v2[(j + 1)]);
																							cv$temp$33$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample27Value148) + Math.log((v[j]?cv$temp$33$var68:(1.0 - cv$temp$33$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value148) + Math.log((v[j]?cv$temp$33$var68:(1.0 - cv$temp$33$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value148) + Math.log((v[j]?cv$temp$33$var68:(1.0 - cv$temp$33$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value148) + Math.log((v[j]?cv$temp$33$var68:(1.0 - cv$temp$33$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value148) + Math.log((v[j]?cv$temp$33$var68:(1.0 - cv$temp$33$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value148);
																					}
																				}
																			}
																			for(int index$i$159 = 0; index$i$159 < size; index$i$159 += 1) {
																				if(!(index$i$159 == i)) {
																					for(int index$sample27$160 = 0; index$sample27$160 < weightings.length; index$sample27$160 += 1) {
																						int distributionTempVariable$var27$162 = index$sample27$160;
																						double cv$probabilitySample27Value161 = (cv$probabilitySample27Value148 * distribution$sample27[((index$i$159 - 0) / 1)][index$sample27$160]);
																						if(((index$i$159 + 1) == (j + 1))) {
																							{
																								{
																									double cv$temp$34$var68;
																									{
																										double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$150_2) / v2[(j + 1)]);
																										cv$temp$34$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value161) + Math.log((v[j]?cv$temp$34$var68:(1.0 - cv$temp$34$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value161) + Math.log((v[j]?cv$temp$34$var68:(1.0 - cv$temp$34$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value161) + Math.log((v[j]?cv$temp$34$var68:(1.0 - cv$temp$34$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value161) + Math.log((v[j]?cv$temp$34$var68:(1.0 - cv$temp$34$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value161) + Math.log((v[j]?cv$temp$34$var68:(1.0 - cv$temp$34$var68)))));
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
														}
													}
												}
											}
										} else {
											if(true) {
												for(int index$sample11$141 = 0; index$sample11$141 < weightings.length; index$sample11$141 += 1) {
													int distributionTempVariable$var11$143 = index$sample11$141;
													double cv$probabilitySample11Value142 = (1.0 * distribution$sample11[index$sample11$141]);
													if((0 == j)) {
														if(fixedFlag$sample27) {
															for(int i = 0; i < size; i += 1) {
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((i + 1) == k)) {
																		int traceTempVariable$var63$151_3 = v2[k];
																		if((k == (j + 1))) {
																			for(int index$i$164_1 = 0; index$i$164_1 < size; index$i$164_1 += 1) {
																				if(((index$i$164_1 + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$35$var68;
																							{
																								double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$151_3) / v2[(j + 1)]);
																								cv$temp$35$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample11Value142) + Math.log((v[j]?cv$temp$35$var68:(1.0 - cv$temp$35$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value142) + Math.log((v[j]?cv$temp$35$var68:(1.0 - cv$temp$35$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value142) + Math.log((v[j]?cv$temp$35$var68:(1.0 - cv$temp$35$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value142) + Math.log((v[j]?cv$temp$35$var68:(1.0 - cv$temp$35$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value142) + Math.log((v[j]?cv$temp$35$var68:(1.0 - cv$temp$35$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value142);
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
																	for(int index$sample27$153 = 0; index$sample27$153 < weightings.length; index$sample27$153 += 1) {
																		int distributionTempVariable$var27$155 = index$sample27$153;
																		double cv$probabilitySample27Value154 = (cv$probabilitySample11Value142 * distribution$sample27[((i - 0) / 1)][index$sample27$153]);
																		for(int k = 0; k < (size + 1); k += 1) {
																			if(((i + 1) == k)) {
																				int traceTempVariable$var63$156_2 = v2[k];
																				if((k == (j + 1))) {
																					if(((i + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$36$var68;
																								{
																									double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$156_2) / v2[(j + 1)]);
																									cv$temp$36$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample27Value154) + Math.log((v[j]?cv$temp$36$var68:(1.0 - cv$temp$36$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value154) + Math.log((v[j]?cv$temp$36$var68:(1.0 - cv$temp$36$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value154) + Math.log((v[j]?cv$temp$36$var68:(1.0 - cv$temp$36$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value154) + Math.log((v[j]?cv$temp$36$var68:(1.0 - cv$temp$36$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value154) + Math.log((v[j]?cv$temp$36$var68:(1.0 - cv$temp$36$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value154);
																							}
																						}
																					}
																					for(int index$i$166 = 0; index$i$166 < size; index$i$166 += 1) {
																						if(!(index$i$166 == i)) {
																							for(int index$sample27$167 = 0; index$sample27$167 < weightings.length; index$sample27$167 += 1) {
																								int distributionTempVariable$var27$169 = index$sample27$167;
																								double cv$probabilitySample27Value168 = (cv$probabilitySample27Value154 * distribution$sample27[((index$i$166 - 0) / 1)][index$sample27$167]);
																								if(((index$i$166 + 1) == (j + 1))) {
																									{
																										{
																											double cv$temp$37$var68;
																											{
																												double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$156_2) / v2[(j + 1)]);
																												cv$temp$37$var68 = var68;
																											}
																											if(((Math.log(cv$probabilitySample27Value168) + Math.log((v[j]?cv$temp$37$var68:(1.0 - cv$temp$37$var68)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value168) + Math.log((v[j]?cv$temp$37$var68:(1.0 - cv$temp$37$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value168) + Math.log((v[j]?cv$temp$37$var68:(1.0 - cv$temp$37$var68))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value168) + Math.log((v[j]?cv$temp$37$var68:(1.0 - cv$temp$37$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value168) + Math.log((v[j]?cv$temp$37$var68:(1.0 - cv$temp$37$var68)))));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value168);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
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
											for(int i = 0; i < size; i += 1) {
												if(((i + 1) == j)) {
													if(fixedFlag$sample11) {
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var63$177_2 = v2[k];
																if((k == (j + 1))) {
																	for(int index$i$187_1 = 0; index$i$187_1 < size; index$i$187_1 += 1) {
																		if(((index$i$187_1 + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$38$var68;
																					{
																						double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$177_2) / v2[(j + 1)]);
																						cv$temp$38$var68 = var68;
																					}
																					if(((Math.log(1.0) + Math.log((v[j]?cv$temp$38$var68:(1.0 - cv$temp$38$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$38$var68:(1.0 - cv$temp$38$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$38$var68:(1.0 - cv$temp$38$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$38$var68:(1.0 - cv$temp$38$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$38$var68:(1.0 - cv$temp$38$var68)))));
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
															for(int index$sample11$178 = 0; index$sample11$178 < weightings.length; index$sample11$178 += 1) {
																int distributionTempVariable$var11$180 = index$sample11$178;
																double cv$probabilitySample11Value179 = (1.0 * distribution$sample11[index$sample11$178]);
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var63$181_2 = v2[k];
																		if((k == (j + 1))) {
																			for(int index$i$188_1 = 0; index$i$188_1 < size; index$i$188_1 += 1) {
																				if(((index$i$188_1 + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$39$var68;
																							{
																								double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$181_2) / v2[(j + 1)]);
																								cv$temp$39$var68 = var68;
																							}
																							if(((Math.log(cv$probabilitySample11Value179) + Math.log((v[j]?cv$temp$39$var68:(1.0 - cv$temp$39$var68)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value179) + Math.log((v[j]?cv$temp$39$var68:(1.0 - cv$temp$39$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value179) + Math.log((v[j]?cv$temp$39$var68:(1.0 - cv$temp$39$var68))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value179) + Math.log((v[j]?cv$temp$39$var68:(1.0 - cv$temp$39$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value179) + Math.log((v[j]?cv$temp$39$var68:(1.0 - cv$temp$39$var68)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value179);
																						}
																					}
																				}
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
													for(int index$sample27$173 = 0; index$sample27$173 < weightings.length; index$sample27$173 += 1) {
														int distributionTempVariable$var27$175 = index$sample27$173;
														double cv$probabilitySample27Value174 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$173]);
														if(((i + 1) == j)) {
															if(fixedFlag$sample11) {
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var63$182_2 = v2[k];
																		if((k == (j + 1))) {
																			if(((i + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$40$var68;
																						{
																							double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$182_2) / v2[(j + 1)]);
																							cv$temp$40$var68 = var68;
																						}
																						if(((Math.log(cv$probabilitySample27Value174) + Math.log((v[j]?cv$temp$40$var68:(1.0 - cv$temp$40$var68)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value174) + Math.log((v[j]?cv$temp$40$var68:(1.0 - cv$temp$40$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value174) + Math.log((v[j]?cv$temp$40$var68:(1.0 - cv$temp$40$var68))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value174) + Math.log((v[j]?cv$temp$40$var68:(1.0 - cv$temp$40$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value174) + Math.log((v[j]?cv$temp$40$var68:(1.0 - cv$temp$40$var68)))));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value174);
																					}
																				}
																			}
																			for(int index$i$190 = 0; index$i$190 < size; index$i$190 += 1) {
																				if(!(index$i$190 == i)) {
																					for(int index$sample27$191 = 0; index$sample27$191 < weightings.length; index$sample27$191 += 1) {
																						int distributionTempVariable$var27$193 = index$sample27$191;
																						double cv$probabilitySample27Value192 = (cv$probabilitySample27Value174 * distribution$sample27[((index$i$190 - 0) / 1)][index$sample27$191]);
																						if(((index$i$190 + 1) == (j + 1))) {
																							{
																								{
																									double cv$temp$41$var68;
																									{
																										double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$182_2) / v2[(j + 1)]);
																										cv$temp$41$var68 = var68;
																									}
																									if(((Math.log(cv$probabilitySample27Value192) + Math.log((v[j]?cv$temp$41$var68:(1.0 - cv$temp$41$var68)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value192) + Math.log((v[j]?cv$temp$41$var68:(1.0 - cv$temp$41$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value192) + Math.log((v[j]?cv$temp$41$var68:(1.0 - cv$temp$41$var68))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value192) + Math.log((v[j]?cv$temp$41$var68:(1.0 - cv$temp$41$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value192) + Math.log((v[j]?cv$temp$41$var68:(1.0 - cv$temp$41$var68)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value192);
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
																	for(int index$sample11$183 = 0; index$sample11$183 < weightings.length; index$sample11$183 += 1) {
																		int distributionTempVariable$var11$185 = index$sample11$183;
																		double cv$probabilitySample11Value184 = (cv$probabilitySample27Value174 * distribution$sample11[index$sample11$183]);
																		for(int k = 0; k < (size + 1); k += 1) {
																			if((0 == k)) {
																				int traceTempVariable$var63$186_2 = v2[k];
																				if((k == (j + 1))) {
																					if(((i + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$42$var68;
																								{
																									double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$186_2) / v2[(j + 1)]);
																									cv$temp$42$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample11Value184) + Math.log((v[j]?cv$temp$42$var68:(1.0 - cv$temp$42$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value184) + Math.log((v[j]?cv$temp$42$var68:(1.0 - cv$temp$42$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value184) + Math.log((v[j]?cv$temp$42$var68:(1.0 - cv$temp$42$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value184) + Math.log((v[j]?cv$temp$42$var68:(1.0 - cv$temp$42$var68)))))) + 1)) + (Math.log(cv$probabilitySample11Value184) + Math.log((v[j]?cv$temp$42$var68:(1.0 - cv$temp$42$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value184);
																							}
																						}
																					}
																					for(int index$i$196 = 0; index$i$196 < size; index$i$196 += 1) {
																						if(!(index$i$196 == i)) {
																							for(int index$sample27$197 = 0; index$sample27$197 < weightings.length; index$sample27$197 += 1) {
																								int distributionTempVariable$var27$199 = index$sample27$197;
																								double cv$probabilitySample27Value198 = (cv$probabilitySample11Value184 * distribution$sample27[((index$i$196 - 0) / 1)][index$sample27$197]);
																								if(((index$i$196 + 1) == (j + 1))) {
																									{
																										{
																											double cv$temp$43$var68;
																											{
																												double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$186_2) / v2[(j + 1)]);
																												cv$temp$43$var68 = var68;
																											}
																											if(((Math.log(cv$probabilitySample27Value198) + Math.log((v[j]?cv$temp$43$var68:(1.0 - cv$temp$43$var68)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value198) + Math.log((v[j]?cv$temp$43$var68:(1.0 - cv$temp$43$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value198) + Math.log((v[j]?cv$temp$43$var68:(1.0 - cv$temp$43$var68))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value198) + Math.log((v[j]?cv$temp$43$var68:(1.0 - cv$temp$43$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value198) + Math.log((v[j]?cv$temp$43$var68:(1.0 - cv$temp$43$var68)))));
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
										if(fixedFlag$sample27) {
											for(int i = 0; i < size; i += 1) {
												if(((i + 1) == j)) {
													for(int index$i$207_1 = 0; index$i$207_1 < size; index$i$207_1 += 1) {
														for(int k = 0; k < (size + 1); k += 1) {
															if(((index$i$207_1 + 1) == k)) {
																int traceTempVariable$var63$207_3 = v2[k];
																if((k == (j + 1))) {
																	for(int index$i$214_1 = 0; index$i$214_1 < size; index$i$214_1 += 1) {
																		if(((index$i$214_1 + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$44$var68;
																					{
																						double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$207_3) / v2[(j + 1)]);
																						cv$temp$44$var68 = var68;
																					}
																					if(((Math.log(1.0) + Math.log((v[j]?cv$temp$44$var68:(1.0 - cv$temp$44$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v[j]?cv$temp$44$var68:(1.0 - cv$temp$44$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v[j]?cv$temp$44$var68:(1.0 - cv$temp$44$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v[j]?cv$temp$44$var68:(1.0 - cv$temp$44$var68)))))) + 1)) + (Math.log(1.0) + Math.log((v[j]?cv$temp$44$var68:(1.0 - cv$temp$44$var68)))));
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
										} else {
											for(int i = 0; i < size; i += 1) {
												if(true) {
													for(int index$sample27$203 = 0; index$sample27$203 < weightings.length; index$sample27$203 += 1) {
														int distributionTempVariable$var27$205 = index$sample27$203;
														double cv$probabilitySample27Value204 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$203]);
														if(((i + 1) == j)) {
															for(int k = 0; k < (size + 1); k += 1) {
																if(((i + 1) == k)) {
																	int traceTempVariable$var63$208_2 = v2[k];
																	if((k == (j + 1))) {
																		if(((i + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$45$var68;
																					{
																						double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$208_2) / v2[(j + 1)]);
																						cv$temp$45$var68 = var68;
																					}
																					if(((Math.log(cv$probabilitySample27Value204) + Math.log((v[j]?cv$temp$45$var68:(1.0 - cv$temp$45$var68)))) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value204) + Math.log((v[j]?cv$temp$45$var68:(1.0 - cv$temp$45$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value204) + Math.log((v[j]?cv$temp$45$var68:(1.0 - cv$temp$45$var68))));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value204) + Math.log((v[j]?cv$temp$45$var68:(1.0 - cv$temp$45$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value204) + Math.log((v[j]?cv$temp$45$var68:(1.0 - cv$temp$45$var68)))));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value204);
																				}
																			}
																		}
																		for(int index$i$216 = 0; index$i$216 < size; index$i$216 += 1) {
																			if(!(index$i$216 == i)) {
																				for(int index$sample27$217 = 0; index$sample27$217 < weightings.length; index$sample27$217 += 1) {
																					int distributionTempVariable$var27$219 = index$sample27$217;
																					double cv$probabilitySample27Value218 = (cv$probabilitySample27Value204 * distribution$sample27[((index$i$216 - 0) / 1)][index$sample27$217]);
																					if(((index$i$216 + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$46$var68;
																								{
																									double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$208_2) / v2[(j + 1)]);
																									cv$temp$46$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample27Value218) + Math.log((v[j]?cv$temp$46$var68:(1.0 - cv$temp$46$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value218) + Math.log((v[j]?cv$temp$46$var68:(1.0 - cv$temp$46$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value218) + Math.log((v[j]?cv$temp$46$var68:(1.0 - cv$temp$46$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value218) + Math.log((v[j]?cv$temp$46$var68:(1.0 - cv$temp$46$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value218) + Math.log((v[j]?cv$temp$46$var68:(1.0 - cv$temp$46$var68)))));
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
															for(int index$i$209 = 0; index$i$209 < size; index$i$209 += 1) {
																if(!(index$i$209 == i)) {
																	for(int index$sample27$210 = 0; index$sample27$210 < weightings.length; index$sample27$210 += 1) {
																		int distributionTempVariable$var27$212 = index$sample27$210;
																		double cv$probabilitySample27Value211 = (cv$probabilitySample27Value204 * distribution$sample27[((index$i$209 - 0) / 1)][index$sample27$210]);
																		for(int k = 0; k < (size + 1); k += 1) {
																			if(((index$i$209 + 1) == k)) {
																				int traceTempVariable$var63$213_2 = v2[k];
																				if((k == (j + 1))) {
																					if(((i + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$47$var68;
																								{
																									double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$213_2) / v2[(j + 1)]);
																									cv$temp$47$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?cv$temp$47$var68:(1.0 - cv$temp$47$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?cv$temp$47$var68:(1.0 - cv$temp$47$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?cv$temp$47$var68:(1.0 - cv$temp$47$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?cv$temp$47$var68:(1.0 - cv$temp$47$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?cv$temp$47$var68:(1.0 - cv$temp$47$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value211);
																							}
																						}
																					}
																					if(((index$i$209 + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$48$var68;
																								{
																									double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$213_2) / v2[(j + 1)]);
																									cv$temp$48$var68 = var68;
																								}
																								if(((Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?cv$temp$48$var68:(1.0 - cv$temp$48$var68)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?cv$temp$48$var68:(1.0 - cv$temp$48$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?cv$temp$48$var68:(1.0 - cv$temp$48$var68))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?cv$temp$48$var68:(1.0 - cv$temp$48$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?cv$temp$48$var68:(1.0 - cv$temp$48$var68)))));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value211);
																							}
																						}
																					}
																					for(int index$i$223 = 0; index$i$223 < size; index$i$223 += 1) {
																						if((!(index$i$223 == i) && !(index$i$223 == index$i$209))) {
																							for(int index$sample27$224 = 0; index$sample27$224 < weightings.length; index$sample27$224 += 1) {
																								int distributionTempVariable$var27$226 = index$sample27$224;
																								double cv$probabilitySample27Value225 = (cv$probabilitySample27Value211 * distribution$sample27[((index$i$223 - 0) / 1)][index$sample27$224]);
																								if(((index$i$223 + 1) == (j + 1))) {
																									{
																										{
																											double cv$temp$49$var68;
																											{
																												double var68 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var63$213_2) / v2[(j + 1)]);
																												cv$temp$49$var68 = var68;
																											}
																											if(((Math.log(cv$probabilitySample27Value225) + Math.log((v[j]?cv$temp$49$var68:(1.0 - cv$temp$49$var68)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value225) + Math.log((v[j]?cv$temp$49$var68:(1.0 - cv$temp$49$var68)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value225) + Math.log((v[j]?cv$temp$49$var68:(1.0 - cv$temp$49$var68))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value225) + Math.log((v[j]?cv$temp$49$var68:(1.0 - cv$temp$49$var68)))))) + 1)) + (Math.log(cv$probabilitySample27Value225) + Math.log((v[j]?cv$temp$49$var68:(1.0 - cv$temp$49$var68)))));
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
			double[] cv$localProbability = distribution$sample5;
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
			cv$var5$stateProbabilityGlobal = new double[weightings.length];
		}
		{
			cv$var11$stateProbabilityGlobal = new double[weightings.length];
		}
		{
			int cv$max_j = 0;
			cv$max_j = Math.max(cv$max_j, ((length$value - 0) / 1));
			guard$sample11bernoulli69$global = new boolean[cv$max_j];
		}
		{
			cv$var27$stateProbabilityGlobal = new double[weightings.length];
		}
		{
			int cv$max_j = 0;
			cv$max_j = Math.max(cv$max_j, ((length$value - 0) / 1));
			guard$sample27bernoulli69$global = new boolean[cv$max_j];
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
			v3 = new int[(length$value + 1)];
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
			logProbability$var26 = new double[((((length$value - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample27 = new double[((((length$value - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var69 = new double[((((length$value - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample70 = new double[((((length$value - 1) - 0) / 1) + 1)];
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
		parallelFor(RNG$, 0, (size + 1), 1,
			(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int k = forStart$k; k < forEnd$k; k += 1) {
						if(!(fixedFlag$sample11 && fixedFlag$sample27))
							v3[k] = v2[k];
					}
			}
		);
		parallelFor(RNG$, 0, size, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						v[j] = DistributionSampling.sampleBernoulli(RNG$1, ((((1.0 * v1) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]));
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
		parallelFor(RNG$, 0, (size + 1), 1,
			(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int k = forStart$k; k < forEnd$k; k += 1)
						v3[k] = v2[k];
			}
		);
		parallelFor(RNG$, 0, size, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						v[j] = DistributionSampling.sampleBernoulli(RNG$1, ((((1.0 * v1) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]));
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
		parallelFor(RNG$, 0, (size + 1), 1,
			(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int k = forStart$k; k < forEnd$k; k += 1) {
						if(!(fixedFlag$sample11 && fixedFlag$sample27))
							v3[k] = v2[k];
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
		parallelFor(RNG$, 0, (size + 1), 1,
			(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int k = forStart$k; k < forEnd$k; k += 1)
						v3[k] = v2[k];
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
		logProbability$var4 = 0.0;
		if(!fixedProbFlag$sample5)
			logProbability$v1 = Double.NaN;
		logProbability$var10 = 0.0;
		logProbability$v2 = 0.0;
		logProbability$v3 = 0.0;
		if(!fixedProbFlag$sample11)
			logProbability$var11 = Double.NaN;
		for(int i = 0; i < size; i += 1)
			logProbability$var26[((i - 0) / 1)] = Double.NaN;
		if(!fixedProbFlag$sample27) {
			for(int i = 0; i < size; i += 1)
				logProbability$sample27[((i - 0) / 1)] = Double.NaN;
		}
		for(int j = 0; j < size; j += 1)
			logProbability$var69[((j - 0) / 1)] = Double.NaN;
		logProbability$v = 0.0;
		if(!fixedProbFlag$sample70) {
			for(int j = 0; j < size; j += 1)
				logProbability$sample70[((j - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample70();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample5();
		logProbabilityDistribution$sample11();
		logProbabilityDistribution$sample27();
		logProbabilityDistribution$sample70();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample11();
		logProbabilityValue$sample27();
		logProbabilityValue$sample70();
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
	public final void setIntermediates() {
		parallelFor(RNG$, 0, (size + 1), 1,
			(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int k = forStart$k; k < forEnd$k; k += 1)
						v3[k] = v2[k];
			}
		);
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
		     + "model DistributionTest5(double[] weightings, boolean[] value) {\n"
		     + "    int size = value.length;\n"
		     + "    \n"
		     + "    int v1 = categorical(weightings).sampleDistribution();\n"
		     + "    \n"
		     + "    int[] v2 = new int[size + 1];\n"
		     + "    v2[0] = categorical(weightings).sampleDistribution();\n"
		     + "    for(int i:[0..size))\n"
		     + "        v2[i + 1] = categorical(weightings).sampleDistribution();\n"
		     + "        \n"
		     + "        \n"
		     + "    int[] v3 = new int[size + 1];\n"
		     + "    for(int k:[0..size]) \n"
		     + "        v3[k] = v2[k];\n"
		     + "        \n"
		     + "    boolean[] v = new boolean[size];\n"
		     + "    for(int j:[0..size))\n"
		     + "        v[j] = bernoulli(((1.0*v1) + v2[j] + v3[j+1])/v2[j+1]).sample();\n"
		     + "        \n"
		     + "    v.observe(value);\n"
		     + "}\n"
		     + "";
	}
}