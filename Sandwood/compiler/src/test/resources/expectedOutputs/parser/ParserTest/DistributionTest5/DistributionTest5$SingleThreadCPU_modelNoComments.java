package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DistributionTest5$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements DistributionTest5$CoreInterface {
	private double[] cv$var15$stateProbabilityGlobal;
	private double[] cv$var23$stateProbabilityGlobal;
	private double[] cv$var9$stateProbabilityGlobal;
	private double[] distribution$sample12;
	private double[] distribution$sample18;
	private double[][] distribution$sample26;
	private boolean fixedFlag$sample12 = false;
	private boolean fixedFlag$sample18 = false;
	private boolean fixedFlag$sample26 = false;
	private boolean fixedFlag$sample55 = false;
	private boolean fixedProbFlag$sample12 = false;
	private boolean fixedProbFlag$sample18 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean fixedProbFlag$sample55 = false;
	private boolean[] guard$sample18bernoulli54$global;
	private boolean[] guard$sample26bernoulli54$global;
	private int length$value;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$sample26;
	private double[] logProbability$sample55;
	private double logProbability$v;
	private double logProbability$v1;
	private double logProbability$v2;
	private double logProbability$v3;
	private double logProbability$var14;
	private double logProbability$var15;
	private double[] logProbability$var22;
	private double[] logProbability$var51;
	private double logProbability$var8;
	private boolean setFlag$v = false;
	private boolean setFlag$v2 = false;
	private boolean setFlag$v3 = false;
	private int size;
	private boolean system$gibbsForward = true;
	private boolean[] v;
	private int v1;
	private int[] v2;
	private int[] v3;
	private boolean[] value;
	private double[] weightings;

	public DistributionTest5$SingleThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample55 = (fixedFlag$sample12 && fixedProbFlag$sample55);
	}

	@Override
	public final boolean get$fixedFlag$sample18() {
		return fixedFlag$sample18;
	}

	@Override
	public final void set$fixedFlag$sample18(boolean cv$value) {
		fixedFlag$sample18 = cv$value;
		fixedProbFlag$sample18 = (fixedFlag$sample18 && fixedProbFlag$sample18);
		fixedProbFlag$sample55 = (fixedFlag$sample18 && fixedProbFlag$sample55);
	}

	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	@Override
	public final void set$fixedFlag$sample26(boolean cv$value) {
		fixedFlag$sample26 = cv$value;
		fixedProbFlag$sample26 = (fixedFlag$sample26 && fixedProbFlag$sample26);
		fixedProbFlag$sample55 = (fixedFlag$sample26 && fixedProbFlag$sample55);
	}

	@Override
	public final boolean get$fixedFlag$sample55() {
		return fixedFlag$sample55;
	}

	@Override
	public final void set$fixedFlag$sample55(boolean cv$value) {
		fixedFlag$sample55 = cv$value;
		fixedProbFlag$sample55 = (fixedFlag$sample55 && fixedProbFlag$sample55);
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
	public final void set$v(boolean[] cv$value) {
		v = cv$value;
		setFlag$v = true;
	}

	@Override
	public final int get$v1() {
		return v1;
	}

	@Override
	public final void set$v1(int cv$value) {
		v1 = cv$value;
	}

	@Override
	public final int[] get$v2() {
		return v2;
	}

	@Override
	public final void set$v2(int[] cv$value) {
		v2 = cv$value;
		setFlag$v2 = true;
	}

	@Override
	public final int[] get$v3() {
		return v3;
	}

	@Override
	public final void set$v3(int[] cv$value) {
		v3 = cv$value;
		setFlag$v3 = true;
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
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$sampleValue, weightings));
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
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$sampleValue, weightings));
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
				boolean cv$guard$v3 = false;
				if(fixedFlag$sample18)
					logProbability$v2 = (logProbability$v2 + cv$accumulator);
				{
					for(int k = 0; k < (size + 1); k += 1) {
						if((0 == k)) {
							if((fixedFlag$sample18 && fixedFlag$sample26)) {
								if(!cv$guard$v3) {
									cv$guard$v3 = true;
									logProbability$v3 = (logProbability$v3 + cv$accumulator);
								}
							}
						}
					}
				}
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
			boolean cv$guard$v3 = false;
			if(fixedFlag$sample18)
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
			{
				for(int k = 0; k < (size + 1); k += 1) {
					if((0 == k)) {
						if((fixedFlag$sample18 && fixedFlag$sample26)) {
							if(!cv$guard$v3) {
								cv$guard$v3 = true;
								logProbability$v3 = (logProbability$v3 + cv$accumulator);
							}
						}
					}
				}
			}
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
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$sampleValue, weightings));
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
					boolean cv$guard$v3 = false;
					int index$i$3 = i;
					{
						for(int k = 0; k < (size + 1); k += 1) {
							if(((i + 1) == k)) {
								if((fixedFlag$sample18 && fixedFlag$sample26)) {
									if(!cv$guard$v3) {
										cv$guard$v3 = true;
										logProbability$v3 = (logProbability$v3 + cv$sampleProbability);
									}
								}
							}
						}
					}
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
				boolean cv$guard$v3 = false;
				int index$i$5 = i;
				{
					for(int k = 0; k < (size + 1); k += 1) {
						if(((i + 1) == k)) {
							if((fixedFlag$sample18 && fixedFlag$sample26)) {
								if(!cv$guard$v3) {
									cv$guard$v3 = true;
									logProbability$v3 = (logProbability$v3 + cv$sampleValue);
								}
							}
						}
					}
				}
			}
			if(fixedFlag$sample26)
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample55() {
		if(!fixedProbFlag$sample55) {
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
								for(int k = 0; k < (size + 1); k += 1) {
									if((0 == k)) {
										if((k == (j + 1))) {
											if((0 == (j + 1))) {
												{
													double var50 = ((((1.0 * v1) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
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
							}
						} else {
							if(true) {
								for(int index$sample18$8 = 0; index$sample18$8 < weightings.length; index$sample18$8 += 1) {
									int distributionTempVariable$var15$10 = index$sample18$8;
									double cv$probabilitySample18Value9 = (1.0 * distribution$sample18[index$sample18$8]);
									int traceTempVariable$var41$11_1 = distributionTempVariable$var15$10;
									if((0 == j)) {
										int traceTempVariable$var33$18_1 = distributionTempVariable$var15$10;
										for(int k = 0; k < (size + 1); k += 1) {
											if((0 == k)) {
												int traceTempVariable$var45$18_3 = traceTempVariable$var33$18_1;
												if((k == (j + 1))) {
													int traceTempVariable$var49$30_1 = distributionTempVariable$var15$10;
													if((0 == (j + 1))) {
														{
															double var50 = ((((1.0 * v1) + traceTempVariable$var41$11_1) + traceTempVariable$var45$18_3) / traceTempVariable$var49$30_1);
															double cv$weightedProbability = (Math.log(cv$probabilitySample18Value9) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
															int traceTempVariable$var49$34_1 = distributionTempVariable$var15$33;
															if((0 == (j + 1))) {
																{
																	double var50 = ((((1.0 * v1) + traceTempVariable$var41$11_1) + traceTempVariable$var45$18_3) / traceTempVariable$var49$34_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample18Value32) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
											}
										}
										if(!true) {
											for(int index$sample18$19 = 0; index$sample18$19 < weightings.length; index$sample18$19 += 1) {
												int distributionTempVariable$var15$21 = index$sample18$19;
												double cv$probabilitySample18Value20 = (cv$probabilitySample18Value9 * distribution$sample18[index$sample18$19]);
												int traceTempVariable$var33$22_1 = distributionTempVariable$var15$21;
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														int traceTempVariable$var45$22_3 = traceTempVariable$var33$22_1;
														if((k == (j + 1))) {
															int traceTempVariable$var49$35_1 = distributionTempVariable$var15$10;
															if((0 == (j + 1))) {
																{
																	double var50 = ((((1.0 * v1) + traceTempVariable$var41$11_1) + traceTempVariable$var45$22_3) / traceTempVariable$var49$35_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample18Value20) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
															int traceTempVariable$var49$36_1 = distributionTempVariable$var15$21;
															if((0 == (j + 1))) {
																{
																	double var50 = ((((1.0 * v1) + traceTempVariable$var41$11_1) + traceTempVariable$var45$22_3) / traceTempVariable$var49$36_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample18Value20) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
																	int traceTempVariable$var49$40_1 = distributionTempVariable$var15$39;
																	if((0 == (j + 1))) {
																		{
																			double var50 = ((((1.0 * v1) + traceTempVariable$var41$11_1) + traceTempVariable$var45$22_3) / traceTempVariable$var49$40_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value38) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
							}
						}
					} else {
						if(true) {
							for(int index$sample12$3 = 0; index$sample12$3 < weightings.length; index$sample12$3 += 1) {
								int distributionTempVariable$v1$5 = index$sample12$3;
								double cv$probabilitySample12Value4 = (1.0 * distribution$sample12[index$sample12$3]);
								int traceTempVariable$v1$6_1 = distributionTempVariable$v1$5;
								if(fixedFlag$sample18) {
									if((0 == j)) {
										for(int k = 0; k < (size + 1); k += 1) {
											if((0 == k)) {
												if((k == (j + 1))) {
													if((0 == (j + 1))) {
														{
															double var50 = ((((1.0 * traceTempVariable$v1$6_1) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample12Value4) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
										}
									}
								} else {
									if(true) {
										for(int index$sample18$13 = 0; index$sample18$13 < weightings.length; index$sample18$13 += 1) {
											int distributionTempVariable$var15$15 = index$sample18$13;
											double cv$probabilitySample18Value14 = (cv$probabilitySample12Value4 * distribution$sample18[index$sample18$13]);
											int traceTempVariable$var41$16_1 = distributionTempVariable$var15$15;
											if((0 == j)) {
												int traceTempVariable$var33$24_1 = distributionTempVariable$var15$15;
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														int traceTempVariable$var45$24_3 = traceTempVariable$var33$24_1;
														if((k == (j + 1))) {
															int traceTempVariable$var49$42_1 = distributionTempVariable$var15$15;
															if((0 == (j + 1))) {
																{
																	double var50 = ((((1.0 * traceTempVariable$v1$6_1) + traceTempVariable$var41$16_1) + traceTempVariable$var45$24_3) / traceTempVariable$var49$42_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample18Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
																	int traceTempVariable$var49$46_1 = distributionTempVariable$var15$45;
																	if((0 == (j + 1))) {
																		{
																			double var50 = ((((1.0 * traceTempVariable$v1$6_1) + traceTempVariable$var41$16_1) + traceTempVariable$var45$24_3) / traceTempVariable$var49$46_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value44) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
													}
												}
												if(!true) {
													for(int index$sample18$25 = 0; index$sample18$25 < weightings.length; index$sample18$25 += 1) {
														int distributionTempVariable$var15$27 = index$sample18$25;
														double cv$probabilitySample18Value26 = (cv$probabilitySample18Value14 * distribution$sample18[index$sample18$25]);
														int traceTempVariable$var33$28_1 = distributionTempVariable$var15$27;
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var45$28_3 = traceTempVariable$var33$28_1;
																if((k == (j + 1))) {
																	int traceTempVariable$var49$47_1 = distributionTempVariable$var15$15;
																	if((0 == (j + 1))) {
																		{
																			double var50 = ((((1.0 * traceTempVariable$v1$6_1) + traceTempVariable$var41$16_1) + traceTempVariable$var45$28_3) / traceTempVariable$var49$47_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value26) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
																	int traceTempVariable$var49$48_1 = distributionTempVariable$var15$27;
																	if((0 == (j + 1))) {
																		{
																			double var50 = ((((1.0 * traceTempVariable$v1$6_1) + traceTempVariable$var41$16_1) + traceTempVariable$var45$28_3) / traceTempVariable$var49$48_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value26) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
																			int traceTempVariable$var49$52_1 = distributionTempVariable$var15$51;
																			if((0 == (j + 1))) {
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$6_1) + traceTempVariable$var41$16_1) + traceTempVariable$var45$28_3) / traceTempVariable$var49$52_1);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample18Value50) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
						}
					}
					if(fixedFlag$sample12) {
						if(fixedFlag$sample18) {
							if((0 == j)) {
								if(fixedFlag$sample26) {
									for(int i = 0; i < size; i += 1) {
										for(int k = 0; k < (size + 1); k += 1) {
											if(((i + 1) == k)) {
												if((k == (j + 1))) {
													if((0 == (j + 1))) {
														{
															double var50 = ((((1.0 * v1) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
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
									}
								} else {
									for(int i = 0; i < size; i += 1) {
										if(true) {
											for(int index$sample26$70 = 0; index$sample26$70 < weightings.length; index$sample26$70 += 1) {
												int distributionTempVariable$var23$72 = index$sample26$70;
												double cv$probabilitySample26Value71 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$70]);
												int traceTempVariable$var33$73_1 = distributionTempVariable$var23$72;
												for(int k = 0; k < (size + 1); k += 1) {
													if(((i + 1) == k)) {
														int traceTempVariable$var45$73_3 = traceTempVariable$var33$73_1;
														if((k == (j + 1))) {
															if((0 == (j + 1))) {
																{
																	double var50 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var45$73_3) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample26Value71) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value71);
																}
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
								for(int index$sample18$59 = 0; index$sample18$59 < weightings.length; index$sample18$59 += 1) {
									int distributionTempVariable$var15$61 = index$sample18$59;
									double cv$probabilitySample18Value60 = (1.0 * distribution$sample18[index$sample18$59]);
									int traceTempVariable$var41$62_1 = distributionTempVariable$var15$61;
									if((0 == j)) {
										if(fixedFlag$sample26) {
											for(int i = 0; i < size; i += 1) {
												for(int k = 0; k < (size + 1); k += 1) {
													if(((i + 1) == k)) {
														if((k == (j + 1))) {
															int traceTempVariable$var49$94_1 = distributionTempVariable$var15$61;
															if((0 == (j + 1))) {
																{
																	double var50 = ((((1.0 * v1) + traceTempVariable$var41$62_1) + v3[(j + 1)]) / traceTempVariable$var49$94_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample18Value60) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value60);
																}
															}
															if(!true) {
																for(int index$sample18$95 = 0; index$sample18$95 < weightings.length; index$sample18$95 += 1) {
																	int distributionTempVariable$var15$97 = index$sample18$95;
																	double cv$probabilitySample18Value96 = (cv$probabilitySample18Value60 * distribution$sample18[index$sample18$95]);
																	int traceTempVariable$var49$98_1 = distributionTempVariable$var15$97;
																	if((0 == (j + 1))) {
																		{
																			double var50 = ((((1.0 * v1) + traceTempVariable$var41$62_1) + v3[(j + 1)]) / traceTempVariable$var49$98_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value96) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value96);
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
													for(int index$sample26$76 = 0; index$sample26$76 < weightings.length; index$sample26$76 += 1) {
														int distributionTempVariable$var23$78 = index$sample26$76;
														double cv$probabilitySample26Value77 = (cv$probabilitySample18Value60 * distribution$sample26[((i - 0) / 1)][index$sample26$76]);
														int traceTempVariable$var33$79_1 = distributionTempVariable$var23$78;
														for(int k = 0; k < (size + 1); k += 1) {
															if(((i + 1) == k)) {
																int traceTempVariable$var45$79_3 = traceTempVariable$var33$79_1;
																if((k == (j + 1))) {
																	int traceTempVariable$var49$99_1 = distributionTempVariable$var15$61;
																	if((0 == (j + 1))) {
																		{
																			double var50 = ((((1.0 * v1) + traceTempVariable$var41$62_1) + traceTempVariable$var45$79_3) / traceTempVariable$var49$99_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample26Value77) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value77);
																		}
																	}
																	if(!true) {
																		for(int index$sample18$100 = 0; index$sample18$100 < weightings.length; index$sample18$100 += 1) {
																			int distributionTempVariable$var15$102 = index$sample18$100;
																			double cv$probabilitySample18Value101 = (cv$probabilitySample26Value77 * distribution$sample18[index$sample18$100]);
																			int traceTempVariable$var49$103_1 = distributionTempVariable$var15$102;
																			if((0 == (j + 1))) {
																				{
																					double var50 = ((((1.0 * v1) + traceTempVariable$var41$62_1) + traceTempVariable$var45$79_3) / traceTempVariable$var49$103_1);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample18Value101) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value101);
																				}
																			}
																		}
																	}
																}
															}
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
								int traceTempVariable$v1$57_1 = distributionTempVariable$v1$56;
								if(fixedFlag$sample18) {
									if((0 == j)) {
										if(fixedFlag$sample26) {
											for(int i = 0; i < size; i += 1) {
												for(int k = 0; k < (size + 1); k += 1) {
													if(((i + 1) == k)) {
														if((k == (j + 1))) {
															if((0 == (j + 1))) {
																{
																	double var50 = ((((1.0 * traceTempVariable$v1$57_1) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample12Value55) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
													}
												}
											}
										} else {
											for(int i = 0; i < size; i += 1) {
												if(true) {
													for(int index$sample26$82 = 0; index$sample26$82 < weightings.length; index$sample26$82 += 1) {
														int distributionTempVariable$var23$84 = index$sample26$82;
														double cv$probabilitySample26Value83 = (cv$probabilitySample12Value55 * distribution$sample26[((i - 0) / 1)][index$sample26$82]);
														int traceTempVariable$var33$85_1 = distributionTempVariable$var23$84;
														for(int k = 0; k < (size + 1); k += 1) {
															if(((i + 1) == k)) {
																int traceTempVariable$var45$85_3 = traceTempVariable$var33$85_1;
																if((k == (j + 1))) {
																	if((0 == (j + 1))) {
																		{
																			double var50 = ((((1.0 * traceTempVariable$v1$57_1) + v2[j]) + traceTempVariable$var45$85_3) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample26Value83) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value83);
																		}
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
										for(int index$sample18$64 = 0; index$sample18$64 < weightings.length; index$sample18$64 += 1) {
											int distributionTempVariable$var15$66 = index$sample18$64;
											double cv$probabilitySample18Value65 = (cv$probabilitySample12Value55 * distribution$sample18[index$sample18$64]);
											int traceTempVariable$var41$67_1 = distributionTempVariable$var15$66;
											if((0 == j)) {
												if(fixedFlag$sample26) {
													for(int i = 0; i < size; i += 1) {
														for(int k = 0; k < (size + 1); k += 1) {
															if(((i + 1) == k)) {
																if((k == (j + 1))) {
																	int traceTempVariable$var49$106_1 = distributionTempVariable$var15$66;
																	if((0 == (j + 1))) {
																		{
																			double var50 = ((((1.0 * traceTempVariable$v1$57_1) + traceTempVariable$var41$67_1) + v3[(j + 1)]) / traceTempVariable$var49$106_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value65) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value65);
																		}
																	}
																	if(!true) {
																		for(int index$sample18$107 = 0; index$sample18$107 < weightings.length; index$sample18$107 += 1) {
																			int distributionTempVariable$var15$109 = index$sample18$107;
																			double cv$probabilitySample18Value108 = (cv$probabilitySample18Value65 * distribution$sample18[index$sample18$107]);
																			int traceTempVariable$var49$110_1 = distributionTempVariable$var15$109;
																			if((0 == (j + 1))) {
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$57_1) + traceTempVariable$var41$67_1) + v3[(j + 1)]) / traceTempVariable$var49$110_1);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample18Value108) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value108);
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
															for(int index$sample26$88 = 0; index$sample26$88 < weightings.length; index$sample26$88 += 1) {
																int distributionTempVariable$var23$90 = index$sample26$88;
																double cv$probabilitySample26Value89 = (cv$probabilitySample18Value65 * distribution$sample26[((i - 0) / 1)][index$sample26$88]);
																int traceTempVariable$var33$91_1 = distributionTempVariable$var23$90;
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((i + 1) == k)) {
																		int traceTempVariable$var45$91_3 = traceTempVariable$var33$91_1;
																		if((k == (j + 1))) {
																			int traceTempVariable$var49$111_1 = distributionTempVariable$var15$66;
																			if((0 == (j + 1))) {
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$57_1) + traceTempVariable$var41$67_1) + traceTempVariable$var45$91_3) / traceTempVariable$var49$111_1);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample26Value89) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value89);
																				}
																			}
																			if(!true) {
																				for(int index$sample18$112 = 0; index$sample18$112 < weightings.length; index$sample18$112 += 1) {
																					int distributionTempVariable$var15$114 = index$sample18$112;
																					double cv$probabilitySample18Value113 = (cv$probabilitySample26Value89 * distribution$sample18[index$sample18$112]);
																					int traceTempVariable$var49$115_1 = distributionTempVariable$var15$114;
																					if((0 == (j + 1))) {
																						{
																							double var50 = ((((1.0 * traceTempVariable$v1$57_1) + traceTempVariable$var41$67_1) + traceTempVariable$var45$91_3) / traceTempVariable$var49$115_1);
																							double cv$weightedProbability = (Math.log(cv$probabilitySample18Value113) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																							if((cv$weightedProbability < cv$distributionAccumulator))
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																							else {
																								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																									cv$distributionAccumulator = cv$weightedProbability;
																								else
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																							}
																							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value113);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
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
										for(int k = 0; k < (size + 1); k += 1) {
											if((0 == k)) {
												if((k == (j + 1))) {
													if((0 == (j + 1))) {
														{
															double var50 = ((((1.0 * v1) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
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
										if(true) {
											for(int index$sample18$134 = 0; index$sample18$134 < weightings.length; index$sample18$134 += 1) {
												int distributionTempVariable$var15$136 = index$sample18$134;
												double cv$probabilitySample18Value135 = (1.0 * distribution$sample18[index$sample18$134]);
												int traceTempVariable$var33$137_1 = distributionTempVariable$var15$136;
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														int traceTempVariable$var45$137_3 = traceTempVariable$var33$137_1;
														if((k == (j + 1))) {
															int traceTempVariable$var49$154_1 = distributionTempVariable$var15$136;
															if((0 == (j + 1))) {
																{
																	double var50 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var45$137_3) / traceTempVariable$var49$154_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample18Value135) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value135);
																}
															}
															if(!true) {
																for(int index$sample18$155 = 0; index$sample18$155 < weightings.length; index$sample18$155 += 1) {
																	int distributionTempVariable$var15$157 = index$sample18$155;
																	double cv$probabilitySample18Value156 = (cv$probabilitySample18Value135 * distribution$sample18[index$sample18$155]);
																	int traceTempVariable$var49$158_1 = distributionTempVariable$var15$157;
																	if((0 == (j + 1))) {
																		{
																			double var50 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var45$137_3) / traceTempVariable$var49$158_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value156) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value156);
																		}
																	}
																}
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
									for(int index$sample26$123 = 0; index$sample26$123 < weightings.length; index$sample26$123 += 1) {
										int distributionTempVariable$var23$125 = index$sample26$123;
										double cv$probabilitySample26Value124 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$123]);
										int traceTempVariable$var41$126_1 = distributionTempVariable$var23$125;
										if(((i + 1) == j)) {
											if(fixedFlag$sample18) {
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														if((k == (j + 1))) {
															if((0 == (j + 1))) {
																{
																	double var50 = ((((1.0 * v1) + traceTempVariable$var41$126_1) + v3[(j + 1)]) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample26Value124) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value124);
																}
															}
														}
													}
												}
											} else {
												if(true) {
													for(int index$sample18$139 = 0; index$sample18$139 < weightings.length; index$sample18$139 += 1) {
														int distributionTempVariable$var15$141 = index$sample18$139;
														double cv$probabilitySample18Value140 = (cv$probabilitySample26Value124 * distribution$sample18[index$sample18$139]);
														int traceTempVariable$var33$142_1 = distributionTempVariable$var15$141;
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var45$142_3 = traceTempVariable$var33$142_1;
																if((k == (j + 1))) {
																	int traceTempVariable$var49$160_1 = distributionTempVariable$var15$141;
																	if((0 == (j + 1))) {
																		{
																			double var50 = ((((1.0 * v1) + traceTempVariable$var41$126_1) + traceTempVariable$var45$142_3) / traceTempVariable$var49$160_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value140) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value140);
																		}
																	}
																	if(!true) {
																		for(int index$sample18$161 = 0; index$sample18$161 < weightings.length; index$sample18$161 += 1) {
																			int distributionTempVariable$var15$163 = index$sample18$161;
																			double cv$probabilitySample18Value162 = (cv$probabilitySample18Value140 * distribution$sample18[index$sample18$161]);
																			int traceTempVariable$var49$164_1 = distributionTempVariable$var15$163;
																			if((0 == (j + 1))) {
																				{
																					double var50 = ((((1.0 * v1) + traceTempVariable$var41$126_1) + traceTempVariable$var45$142_3) / traceTempVariable$var49$164_1);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample18Value162) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
							}
						}
					} else {
						if(true) {
							for(int index$sample12$117 = 0; index$sample12$117 < weightings.length; index$sample12$117 += 1) {
								int distributionTempVariable$v1$119 = index$sample12$117;
								double cv$probabilitySample12Value118 = (1.0 * distribution$sample12[index$sample12$117]);
								int traceTempVariable$v1$120_1 = distributionTempVariable$v1$119;
								if(fixedFlag$sample26) {
									for(int i = 0; i < size; i += 1) {
										if(((i + 1) == j)) {
											if(fixedFlag$sample18) {
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														if((k == (j + 1))) {
															if((0 == (j + 1))) {
																{
																	double var50 = ((((1.0 * traceTempVariable$v1$120_1) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample12Value118) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample12Value118);
																}
															}
														}
													}
												}
											} else {
												if(true) {
													for(int index$sample18$144 = 0; index$sample18$144 < weightings.length; index$sample18$144 += 1) {
														int distributionTempVariable$var15$146 = index$sample18$144;
														double cv$probabilitySample18Value145 = (cv$probabilitySample12Value118 * distribution$sample18[index$sample18$144]);
														int traceTempVariable$var33$147_1 = distributionTempVariable$var15$146;
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var45$147_3 = traceTempVariable$var33$147_1;
																if((k == (j + 1))) {
																	int traceTempVariable$var49$166_1 = distributionTempVariable$var15$146;
																	if((0 == (j + 1))) {
																		{
																			double var50 = ((((1.0 * traceTempVariable$v1$120_1) + v2[j]) + traceTempVariable$var45$147_3) / traceTempVariable$var49$166_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value145) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value145);
																		}
																	}
																	if(!true) {
																		for(int index$sample18$167 = 0; index$sample18$167 < weightings.length; index$sample18$167 += 1) {
																			int distributionTempVariable$var15$169 = index$sample18$167;
																			double cv$probabilitySample18Value168 = (cv$probabilitySample18Value145 * distribution$sample18[index$sample18$167]);
																			int traceTempVariable$var49$170_1 = distributionTempVariable$var15$169;
																			if((0 == (j + 1))) {
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$120_1) + v2[j]) + traceTempVariable$var45$147_3) / traceTempVariable$var49$170_1);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample18Value168) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value168);
																				}
																			}
																		}
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
											for(int index$sample26$129 = 0; index$sample26$129 < weightings.length; index$sample26$129 += 1) {
												int distributionTempVariable$var23$131 = index$sample26$129;
												double cv$probabilitySample26Value130 = (cv$probabilitySample12Value118 * distribution$sample26[((i - 0) / 1)][index$sample26$129]);
												int traceTempVariable$var41$132_1 = distributionTempVariable$var23$131;
												if(((i + 1) == j)) {
													if(fixedFlag$sample18) {
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																if((k == (j + 1))) {
																	if((0 == (j + 1))) {
																		{
																			double var50 = ((((1.0 * traceTempVariable$v1$120_1) + traceTempVariable$var41$132_1) + v3[(j + 1)]) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample26Value130) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value130);
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample18$149 = 0; index$sample18$149 < weightings.length; index$sample18$149 += 1) {
																int distributionTempVariable$var15$151 = index$sample18$149;
																double cv$probabilitySample18Value150 = (cv$probabilitySample26Value130 * distribution$sample18[index$sample18$149]);
																int traceTempVariable$var33$152_1 = distributionTempVariable$var15$151;
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var45$152_3 = traceTempVariable$var33$152_1;
																		if((k == (j + 1))) {
																			int traceTempVariable$var49$172_1 = distributionTempVariable$var15$151;
																			if((0 == (j + 1))) {
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$120_1) + traceTempVariable$var41$132_1) + traceTempVariable$var45$152_3) / traceTempVariable$var49$172_1);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample18Value150) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value150);
																				}
																			}
																			if(!true) {
																				for(int index$sample18$173 = 0; index$sample18$173 < weightings.length; index$sample18$173 += 1) {
																					int distributionTempVariable$var15$175 = index$sample18$173;
																					double cv$probabilitySample18Value174 = (cv$probabilitySample18Value150 * distribution$sample18[index$sample18$173]);
																					int traceTempVariable$var49$176_1 = distributionTempVariable$var15$175;
																					if((0 == (j + 1))) {
																						{
																							double var50 = ((((1.0 * traceTempVariable$v1$120_1) + traceTempVariable$var41$132_1) + traceTempVariable$var45$152_3) / traceTempVariable$var49$176_1);
																							double cv$weightedProbability = (Math.log(cv$probabilitySample18Value174) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
						}
					}
					if(fixedFlag$sample12) {
						if(fixedFlag$sample26) {
							for(int i = 0; i < size; i += 1) {
								if(((i + 1) == j)) {
									for(int index$i$194_1 = 0; index$i$194_1 < size; index$i$194_1 += 1) {
										for(int k = 0; k < (size + 1); k += 1) {
											if(((index$i$194_1 + 1) == k)) {
												if((k == (j + 1))) {
													if(fixedFlag$sample18) {
														if((0 == (j + 1))) {
															{
																double var50 = ((((1.0 * v1) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
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
													} else {
														if(true) {
															for(int index$sample18$209 = 0; index$sample18$209 < weightings.length; index$sample18$209 += 1) {
																int distributionTempVariable$var15$211 = index$sample18$209;
																double cv$probabilitySample18Value210 = (1.0 * distribution$sample18[index$sample18$209]);
																int traceTempVariable$var49$212_1 = distributionTempVariable$var15$211;
																if((0 == (j + 1))) {
																	{
																		double var50 = ((((1.0 * v1) + v2[j]) + v3[(j + 1)]) / traceTempVariable$var49$212_1);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample18Value210) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
								}
							}
						} else {
							for(int i = 0; i < size; i += 1) {
								if(true) {
									for(int index$sample26$184 = 0; index$sample26$184 < weightings.length; index$sample26$184 += 1) {
										int distributionTempVariable$var23$186 = index$sample26$184;
										double cv$probabilitySample26Value185 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$184]);
										int traceTempVariable$var41$187_1 = distributionTempVariable$var23$186;
										if(((i + 1) == j)) {
											int traceTempVariable$var33$195_1 = distributionTempVariable$var23$186;
											for(int k = 0; k < (size + 1); k += 1) {
												if(((i + 1) == k)) {
													int traceTempVariable$var45$195_3 = traceTempVariable$var33$195_1;
													if((k == (j + 1))) {
														if(fixedFlag$sample18) {
															if((0 == (j + 1))) {
																{
																	double var50 = ((((1.0 * v1) + traceTempVariable$var41$187_1) + traceTempVariable$var45$195_3) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample26Value185) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
																	int traceTempVariable$var49$217_1 = distributionTempVariable$var15$216;
																	if((0 == (j + 1))) {
																		{
																			double var50 = ((((1.0 * v1) + traceTempVariable$var41$187_1) + traceTempVariable$var45$195_3) / traceTempVariable$var49$217_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value215) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
												}
											}
											for(int index$i$196 = 0; index$i$196 < size; index$i$196 += 1) {
												if(!(index$i$196 == i)) {
													for(int index$sample26$197 = 0; index$sample26$197 < weightings.length; index$sample26$197 += 1) {
														int distributionTempVariable$var23$199 = index$sample26$197;
														double cv$probabilitySample26Value198 = (cv$probabilitySample26Value185 * distribution$sample26[((index$i$196 - 0) / 1)][index$sample26$197]);
														int traceTempVariable$var33$200_1 = distributionTempVariable$var23$199;
														for(int k = 0; k < (size + 1); k += 1) {
															if(((index$i$196 + 1) == k)) {
																int traceTempVariable$var45$200_3 = traceTempVariable$var33$200_1;
																if((k == (j + 1))) {
																	if(fixedFlag$sample18) {
																		if((0 == (j + 1))) {
																			{
																				double var50 = ((((1.0 * v1) + traceTempVariable$var41$187_1) + traceTempVariable$var45$200_3) / v2[(j + 1)]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample26Value198) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
																				int traceTempVariable$var49$222_1 = distributionTempVariable$var15$221;
																				if((0 == (j + 1))) {
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$187_1) + traceTempVariable$var45$200_3) / traceTempVariable$var49$222_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample18Value220) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
							}
						}
					} else {
						if(true) {
							for(int index$sample12$178 = 0; index$sample12$178 < weightings.length; index$sample12$178 += 1) {
								int distributionTempVariable$v1$180 = index$sample12$178;
								double cv$probabilitySample12Value179 = (1.0 * distribution$sample12[index$sample12$178]);
								int traceTempVariable$v1$181_1 = distributionTempVariable$v1$180;
								if(fixedFlag$sample26) {
									for(int i = 0; i < size; i += 1) {
										if(((i + 1) == j)) {
											for(int index$i$201_1 = 0; index$i$201_1 < size; index$i$201_1 += 1) {
												for(int k = 0; k < (size + 1); k += 1) {
													if(((index$i$201_1 + 1) == k)) {
														if((k == (j + 1))) {
															if(fixedFlag$sample18) {
																if((0 == (j + 1))) {
																	{
																		double var50 = ((((1.0 * traceTempVariable$v1$181_1) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample12Value179) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
																		int traceTempVariable$var49$227_1 = distributionTempVariable$var15$226;
																		if((0 == (j + 1))) {
																			{
																				double var50 = ((((1.0 * traceTempVariable$v1$181_1) + v2[j]) + v3[(j + 1)]) / traceTempVariable$var49$227_1);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample18Value225) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
										}
									}
								} else {
									for(int i = 0; i < size; i += 1) {
										if(true) {
											for(int index$sample26$190 = 0; index$sample26$190 < weightings.length; index$sample26$190 += 1) {
												int distributionTempVariable$var23$192 = index$sample26$190;
												double cv$probabilitySample26Value191 = (cv$probabilitySample12Value179 * distribution$sample26[((i - 0) / 1)][index$sample26$190]);
												int traceTempVariable$var41$193_1 = distributionTempVariable$var23$192;
												if(((i + 1) == j)) {
													int traceTempVariable$var33$202_1 = distributionTempVariable$var23$192;
													for(int k = 0; k < (size + 1); k += 1) {
														if(((i + 1) == k)) {
															int traceTempVariable$var45$202_3 = traceTempVariable$var33$202_1;
															if((k == (j + 1))) {
																if(fixedFlag$sample18) {
																	if((0 == (j + 1))) {
																		{
																			double var50 = ((((1.0 * traceTempVariable$v1$181_1) + traceTempVariable$var41$193_1) + traceTempVariable$var45$202_3) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample26Value191) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
																			int traceTempVariable$var49$232_1 = distributionTempVariable$var15$231;
																			if((0 == (j + 1))) {
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$181_1) + traceTempVariable$var41$193_1) + traceTempVariable$var45$202_3) / traceTempVariable$var49$232_1);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample18Value230) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
														}
													}
													for(int index$i$203 = 0; index$i$203 < size; index$i$203 += 1) {
														if(!(index$i$203 == i)) {
															for(int index$sample26$204 = 0; index$sample26$204 < weightings.length; index$sample26$204 += 1) {
																int distributionTempVariable$var23$206 = index$sample26$204;
																double cv$probabilitySample26Value205 = (cv$probabilitySample26Value191 * distribution$sample26[((index$i$203 - 0) / 1)][index$sample26$204]);
																int traceTempVariable$var33$207_1 = distributionTempVariable$var23$206;
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((index$i$203 + 1) == k)) {
																		int traceTempVariable$var45$207_3 = traceTempVariable$var33$207_1;
																		if((k == (j + 1))) {
																			if(fixedFlag$sample18) {
																				if((0 == (j + 1))) {
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$181_1) + traceTempVariable$var41$193_1) + traceTempVariable$var45$207_3) / v2[(j + 1)]);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample26Value205) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
																						int traceTempVariable$var49$237_1 = distributionTempVariable$var15$236;
																						if((0 == (j + 1))) {
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$181_1) + traceTempVariable$var41$193_1) + traceTempVariable$var45$207_3) / traceTempVariable$var49$237_1);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample18Value235) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
						}
					}
					if(fixedFlag$sample12) {
						if(fixedFlag$sample18) {
							if((0 == j)) {
								for(int k = 0; k < (size + 1); k += 1) {
									if((0 == k)) {
										if((k == (j + 1))) {
											if(fixedFlag$sample26) {
												for(int i = 0; i < size; i += 1) {
													if(((i + 1) == (j + 1))) {
														{
															double var50 = ((((1.0 * v1) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
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
											} else {
												for(int i = 0; i < size; i += 1) {
													if(true) {
														for(int index$sample26$267 = 0; index$sample26$267 < weightings.length; index$sample26$267 += 1) {
															int distributionTempVariable$var23$269 = index$sample26$267;
															double cv$probabilitySample26Value268 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$267]);
															int traceTempVariable$var49$270_1 = distributionTempVariable$var23$269;
															if(((i + 1) == (j + 1))) {
																{
																	double var50 = ((((1.0 * v1) + v2[j]) + v3[(j + 1)]) / traceTempVariable$var49$270_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample26Value268) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
								}
							}
						} else {
							if(true) {
								for(int index$sample18$244 = 0; index$sample18$244 < weightings.length; index$sample18$244 += 1) {
									int distributionTempVariable$var15$246 = index$sample18$244;
									double cv$probabilitySample18Value245 = (1.0 * distribution$sample18[index$sample18$244]);
									int traceTempVariable$var41$247_1 = distributionTempVariable$var15$246;
									if((0 == j)) {
										int traceTempVariable$var33$254_1 = distributionTempVariable$var15$246;
										for(int k = 0; k < (size + 1); k += 1) {
											if((0 == k)) {
												int traceTempVariable$var45$254_3 = traceTempVariable$var33$254_1;
												if((k == (j + 1))) {
													if(fixedFlag$sample26) {
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == (j + 1))) {
																{
																	double var50 = ((((1.0 * v1) + traceTempVariable$var41$247_1) + traceTempVariable$var45$254_3) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample18Value245) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
																	int traceTempVariable$var49$276_1 = distributionTempVariable$var23$275;
																	if(((i + 1) == (j + 1))) {
																		{
																			double var50 = ((((1.0 * v1) + traceTempVariable$var41$247_1) + traceTempVariable$var45$254_3) / traceTempVariable$var49$276_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample26Value274) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
											}
										}
										if(!true) {
											for(int index$sample18$255 = 0; index$sample18$255 < weightings.length; index$sample18$255 += 1) {
												int distributionTempVariable$var15$257 = index$sample18$255;
												double cv$probabilitySample18Value256 = (cv$probabilitySample18Value245 * distribution$sample18[index$sample18$255]);
												int traceTempVariable$var33$258_1 = distributionTempVariable$var15$257;
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														int traceTempVariable$var45$258_3 = traceTempVariable$var33$258_1;
														if((k == (j + 1))) {
															if(fixedFlag$sample26) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == (j + 1))) {
																		{
																			double var50 = ((((1.0 * v1) + traceTempVariable$var41$247_1) + traceTempVariable$var45$258_3) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value256) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
																			int traceTempVariable$var49$282_1 = distributionTempVariable$var23$281;
																			if(((i + 1) == (j + 1))) {
																				{
																					double var50 = ((((1.0 * v1) + traceTempVariable$var41$247_1) + traceTempVariable$var45$258_3) / traceTempVariable$var49$282_1);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample26Value280) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
							}
						}
					} else {
						if(true) {
							for(int index$sample12$239 = 0; index$sample12$239 < weightings.length; index$sample12$239 += 1) {
								int distributionTempVariable$v1$241 = index$sample12$239;
								double cv$probabilitySample12Value240 = (1.0 * distribution$sample12[index$sample12$239]);
								int traceTempVariable$v1$242_1 = distributionTempVariable$v1$241;
								if(fixedFlag$sample18) {
									if((0 == j)) {
										for(int k = 0; k < (size + 1); k += 1) {
											if((0 == k)) {
												if((k == (j + 1))) {
													if(fixedFlag$sample26) {
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == (j + 1))) {
																{
																	double var50 = ((((1.0 * traceTempVariable$v1$242_1) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample12Value240) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
																	int traceTempVariable$var49$288_1 = distributionTempVariable$var23$287;
																	if(((i + 1) == (j + 1))) {
																		{
																			double var50 = ((((1.0 * traceTempVariable$v1$242_1) + v2[j]) + v3[(j + 1)]) / traceTempVariable$var49$288_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample26Value286) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
										}
									}
								} else {
									if(true) {
										for(int index$sample18$249 = 0; index$sample18$249 < weightings.length; index$sample18$249 += 1) {
											int distributionTempVariable$var15$251 = index$sample18$249;
											double cv$probabilitySample18Value250 = (cv$probabilitySample12Value240 * distribution$sample18[index$sample18$249]);
											int traceTempVariable$var41$252_1 = distributionTempVariable$var15$251;
											if((0 == j)) {
												int traceTempVariable$var33$260_1 = distributionTempVariable$var15$251;
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														int traceTempVariable$var45$260_3 = traceTempVariable$var33$260_1;
														if((k == (j + 1))) {
															if(fixedFlag$sample26) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == (j + 1))) {
																		{
																			double var50 = ((((1.0 * traceTempVariable$v1$242_1) + traceTempVariable$var41$252_1) + traceTempVariable$var45$260_3) / v2[(j + 1)]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value250) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
																			int traceTempVariable$var49$294_1 = distributionTempVariable$var23$293;
																			if(((i + 1) == (j + 1))) {
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$242_1) + traceTempVariable$var41$252_1) + traceTempVariable$var45$260_3) / traceTempVariable$var49$294_1);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample26Value292) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
													}
												}
												if(!true) {
													for(int index$sample18$261 = 0; index$sample18$261 < weightings.length; index$sample18$261 += 1) {
														int distributionTempVariable$var15$263 = index$sample18$261;
														double cv$probabilitySample18Value262 = (cv$probabilitySample18Value250 * distribution$sample18[index$sample18$261]);
														int traceTempVariable$var33$264_1 = distributionTempVariable$var15$263;
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var45$264_3 = traceTempVariable$var33$264_1;
																if((k == (j + 1))) {
																	if(fixedFlag$sample26) {
																		for(int i = 0; i < size; i += 1) {
																			if(((i + 1) == (j + 1))) {
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$242_1) + traceTempVariable$var41$252_1) + traceTempVariable$var45$264_3) / v2[(j + 1)]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample18Value262) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
																					int traceTempVariable$var49$300_1 = distributionTempVariable$var23$299;
																					if(((i + 1) == (j + 1))) {
																						{
																							double var50 = ((((1.0 * traceTempVariable$v1$242_1) + traceTempVariable$var41$252_1) + traceTempVariable$var45$264_3) / traceTempVariable$var49$300_1);
																							double cv$weightedProbability = (Math.log(cv$probabilitySample26Value298) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
						}
					}
					if(fixedFlag$sample12) {
						if(fixedFlag$sample18) {
							if((0 == j)) {
								if(fixedFlag$sample26) {
									for(int i = 0; i < size; i += 1) {
										for(int k = 0; k < (size + 1); k += 1) {
											if(((i + 1) == k)) {
												if((k == (j + 1))) {
													for(int index$i$340_1 = 0; index$i$340_1 < size; index$i$340_1 += 1) {
														if(((index$i$340_1 + 1) == (j + 1))) {
															{
																double var50 = ((((1.0 * v1) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
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
										}
									}
								} else {
									for(int i = 0; i < size; i += 1) {
										if(true) {
											for(int index$sample26$318 = 0; index$sample26$318 < weightings.length; index$sample26$318 += 1) {
												int distributionTempVariable$var23$320 = index$sample26$318;
												double cv$probabilitySample26Value319 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$318]);
												int traceTempVariable$var33$321_1 = distributionTempVariable$var23$320;
												for(int k = 0; k < (size + 1); k += 1) {
													if(((i + 1) == k)) {
														int traceTempVariable$var45$321_3 = traceTempVariable$var33$321_1;
														if((k == (j + 1))) {
															int traceTempVariable$var49$341_1 = distributionTempVariable$var23$320;
															if(((i + 1) == (j + 1))) {
																{
																	double var50 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var45$321_3) / traceTempVariable$var49$341_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample26Value319) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value319);
																}
															}
															for(int index$i$342 = 0; index$i$342 < size; index$i$342 += 1) {
																if(!(index$i$342 == i)) {
																	for(int index$sample26$343 = 0; index$sample26$343 < weightings.length; index$sample26$343 += 1) {
																		int distributionTempVariable$var23$345 = index$sample26$343;
																		double cv$probabilitySample26Value344 = (cv$probabilitySample26Value319 * distribution$sample26[((index$i$342 - 0) / 1)][index$sample26$343]);
																		int traceTempVariable$var49$346_1 = distributionTempVariable$var23$345;
																		if(((index$i$342 + 1) == (j + 1))) {
																			{
																				double var50 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var45$321_3) / traceTempVariable$var49$346_1);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample26Value344) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value344);
																			}
																		}
																	}
																}
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
								for(int index$sample18$307 = 0; index$sample18$307 < weightings.length; index$sample18$307 += 1) {
									int distributionTempVariable$var15$309 = index$sample18$307;
									double cv$probabilitySample18Value308 = (1.0 * distribution$sample18[index$sample18$307]);
									int traceTempVariable$var41$310_1 = distributionTempVariable$var15$309;
									if((0 == j)) {
										if(fixedFlag$sample26) {
											for(int i = 0; i < size; i += 1) {
												for(int k = 0; k < (size + 1); k += 1) {
													if(((i + 1) == k)) {
														if((k == (j + 1))) {
															for(int index$i$347_1 = 0; index$i$347_1 < size; index$i$347_1 += 1) {
																if(((index$i$347_1 + 1) == (j + 1))) {
																	{
																		double var50 = ((((1.0 * v1) + traceTempVariable$var41$310_1) + v3[(j + 1)]) / v2[(j + 1)]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample18Value308) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																		if((cv$weightedProbability < cv$distributionAccumulator))
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																		else {
																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																				cv$distributionAccumulator = cv$weightedProbability;
																			else
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																		}
																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value308);
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
													for(int index$sample26$324 = 0; index$sample26$324 < weightings.length; index$sample26$324 += 1) {
														int distributionTempVariable$var23$326 = index$sample26$324;
														double cv$probabilitySample26Value325 = (cv$probabilitySample18Value308 * distribution$sample26[((i - 0) / 1)][index$sample26$324]);
														int traceTempVariable$var33$327_1 = distributionTempVariable$var23$326;
														for(int k = 0; k < (size + 1); k += 1) {
															if(((i + 1) == k)) {
																int traceTempVariable$var45$327_3 = traceTempVariable$var33$327_1;
																if((k == (j + 1))) {
																	int traceTempVariable$var49$348_1 = distributionTempVariable$var23$326;
																	if(((i + 1) == (j + 1))) {
																		{
																			double var50 = ((((1.0 * v1) + traceTempVariable$var41$310_1) + traceTempVariable$var45$327_3) / traceTempVariable$var49$348_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample26Value325) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value325);
																		}
																	}
																	for(int index$i$349 = 0; index$i$349 < size; index$i$349 += 1) {
																		if(!(index$i$349 == i)) {
																			for(int index$sample26$350 = 0; index$sample26$350 < weightings.length; index$sample26$350 += 1) {
																				int distributionTempVariable$var23$352 = index$sample26$350;
																				double cv$probabilitySample26Value351 = (cv$probabilitySample26Value325 * distribution$sample26[((index$i$349 - 0) / 1)][index$sample26$350]);
																				int traceTempVariable$var49$353_1 = distributionTempVariable$var23$352;
																				if(((index$i$349 + 1) == (j + 1))) {
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$310_1) + traceTempVariable$var45$327_3) / traceTempVariable$var49$353_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample26Value351) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value351);
																					}
																				}
																			}
																		}
																	}
																}
															}
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
								int traceTempVariable$v1$305_1 = distributionTempVariable$v1$304;
								if(fixedFlag$sample18) {
									if((0 == j)) {
										if(fixedFlag$sample26) {
											for(int i = 0; i < size; i += 1) {
												for(int k = 0; k < (size + 1); k += 1) {
													if(((i + 1) == k)) {
														if((k == (j + 1))) {
															for(int index$i$354_1 = 0; index$i$354_1 < size; index$i$354_1 += 1) {
																if(((index$i$354_1 + 1) == (j + 1))) {
																	{
																		double var50 = ((((1.0 * traceTempVariable$v1$305_1) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample12Value303) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
													}
												}
											}
										} else {
											for(int i = 0; i < size; i += 1) {
												if(true) {
													for(int index$sample26$330 = 0; index$sample26$330 < weightings.length; index$sample26$330 += 1) {
														int distributionTempVariable$var23$332 = index$sample26$330;
														double cv$probabilitySample26Value331 = (cv$probabilitySample12Value303 * distribution$sample26[((i - 0) / 1)][index$sample26$330]);
														int traceTempVariable$var33$333_1 = distributionTempVariable$var23$332;
														for(int k = 0; k < (size + 1); k += 1) {
															if(((i + 1) == k)) {
																int traceTempVariable$var45$333_3 = traceTempVariable$var33$333_1;
																if((k == (j + 1))) {
																	int traceTempVariable$var49$355_1 = distributionTempVariable$var23$332;
																	if(((i + 1) == (j + 1))) {
																		{
																			double var50 = ((((1.0 * traceTempVariable$v1$305_1) + v2[j]) + traceTempVariable$var45$333_3) / traceTempVariable$var49$355_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample26Value331) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value331);
																		}
																	}
																	for(int index$i$356 = 0; index$i$356 < size; index$i$356 += 1) {
																		if(!(index$i$356 == i)) {
																			for(int index$sample26$357 = 0; index$sample26$357 < weightings.length; index$sample26$357 += 1) {
																				int distributionTempVariable$var23$359 = index$sample26$357;
																				double cv$probabilitySample26Value358 = (cv$probabilitySample26Value331 * distribution$sample26[((index$i$356 - 0) / 1)][index$sample26$357]);
																				int traceTempVariable$var49$360_1 = distributionTempVariable$var23$359;
																				if(((index$i$356 + 1) == (j + 1))) {
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$305_1) + v2[j]) + traceTempVariable$var45$333_3) / traceTempVariable$var49$360_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample26Value358) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value358);
																					}
																				}
																			}
																		}
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
										for(int index$sample18$312 = 0; index$sample18$312 < weightings.length; index$sample18$312 += 1) {
											int distributionTempVariable$var15$314 = index$sample18$312;
											double cv$probabilitySample18Value313 = (cv$probabilitySample12Value303 * distribution$sample18[index$sample18$312]);
											int traceTempVariable$var41$315_1 = distributionTempVariable$var15$314;
											if((0 == j)) {
												if(fixedFlag$sample26) {
													for(int i = 0; i < size; i += 1) {
														for(int k = 0; k < (size + 1); k += 1) {
															if(((i + 1) == k)) {
																if((k == (j + 1))) {
																	for(int index$i$361_1 = 0; index$i$361_1 < size; index$i$361_1 += 1) {
																		if(((index$i$361_1 + 1) == (j + 1))) {
																			{
																				double var50 = ((((1.0 * traceTempVariable$v1$305_1) + traceTempVariable$var41$315_1) + v3[(j + 1)]) / v2[(j + 1)]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample18Value313) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value313);
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
															for(int index$sample26$336 = 0; index$sample26$336 < weightings.length; index$sample26$336 += 1) {
																int distributionTempVariable$var23$338 = index$sample26$336;
																double cv$probabilitySample26Value337 = (cv$probabilitySample18Value313 * distribution$sample26[((i - 0) / 1)][index$sample26$336]);
																int traceTempVariable$var33$339_1 = distributionTempVariable$var23$338;
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((i + 1) == k)) {
																		int traceTempVariable$var45$339_3 = traceTempVariable$var33$339_1;
																		if((k == (j + 1))) {
																			int traceTempVariable$var49$362_1 = distributionTempVariable$var23$338;
																			if(((i + 1) == (j + 1))) {
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$305_1) + traceTempVariable$var41$315_1) + traceTempVariable$var45$339_3) / traceTempVariable$var49$362_1);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample26Value337) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value337);
																				}
																			}
																			for(int index$i$363 = 0; index$i$363 < size; index$i$363 += 1) {
																				if(!(index$i$363 == i)) {
																					for(int index$sample26$364 = 0; index$sample26$364 < weightings.length; index$sample26$364 += 1) {
																						int distributionTempVariable$var23$366 = index$sample26$364;
																						double cv$probabilitySample26Value365 = (cv$probabilitySample26Value337 * distribution$sample26[((index$i$363 - 0) / 1)][index$sample26$364]);
																						int traceTempVariable$var49$367_1 = distributionTempVariable$var23$366;
																						if(((index$i$363 + 1) == (j + 1))) {
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$305_1) + traceTempVariable$var41$315_1) + traceTempVariable$var45$339_3) / traceTempVariable$var49$367_1);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample26Value365) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																								if((cv$weightedProbability < cv$distributionAccumulator))
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																								else {
																									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																										cv$distributionAccumulator = cv$weightedProbability;
																									else
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																								}
																								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value365);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
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
										for(int k = 0; k < (size + 1); k += 1) {
											if((0 == k)) {
												if((k == (j + 1))) {
													for(int index$i$405_1 = 0; index$i$405_1 < size; index$i$405_1 += 1) {
														if(((index$i$405_1 + 1) == (j + 1))) {
															{
																double var50 = ((((1.0 * v1) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
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
										}
									} else {
										if(true) {
											for(int index$sample18$386 = 0; index$sample18$386 < weightings.length; index$sample18$386 += 1) {
												int distributionTempVariable$var15$388 = index$sample18$386;
												double cv$probabilitySample18Value387 = (1.0 * distribution$sample18[index$sample18$386]);
												int traceTempVariable$var33$389_1 = distributionTempVariable$var15$388;
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														int traceTempVariable$var45$389_3 = traceTempVariable$var33$389_1;
														if((k == (j + 1))) {
															for(int index$i$406_1 = 0; index$i$406_1 < size; index$i$406_1 += 1) {
																if(((index$i$406_1 + 1) == (j + 1))) {
																	{
																		double var50 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var45$389_3) / v2[(j + 1)]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample18Value387) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																		if((cv$weightedProbability < cv$distributionAccumulator))
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																		else {
																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																				cv$distributionAccumulator = cv$weightedProbability;
																			else
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																		}
																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value387);
																	}
																}
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
									for(int index$sample26$375 = 0; index$sample26$375 < weightings.length; index$sample26$375 += 1) {
										int distributionTempVariable$var23$377 = index$sample26$375;
										double cv$probabilitySample26Value376 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$375]);
										int traceTempVariable$var41$378_1 = distributionTempVariable$var23$377;
										if(((i + 1) == j)) {
											if(fixedFlag$sample18) {
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														if((k == (j + 1))) {
															int traceTempVariable$var49$407_1 = distributionTempVariable$var23$377;
															if(((i + 1) == (j + 1))) {
																{
																	double var50 = ((((1.0 * v1) + traceTempVariable$var41$378_1) + v3[(j + 1)]) / traceTempVariable$var49$407_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample26Value376) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value376);
																}
															}
															for(int index$i$408 = 0; index$i$408 < size; index$i$408 += 1) {
																if(!(index$i$408 == i)) {
																	for(int index$sample26$409 = 0; index$sample26$409 < weightings.length; index$sample26$409 += 1) {
																		int distributionTempVariable$var23$411 = index$sample26$409;
																		double cv$probabilitySample26Value410 = (cv$probabilitySample26Value376 * distribution$sample26[((index$i$408 - 0) / 1)][index$sample26$409]);
																		int traceTempVariable$var49$412_1 = distributionTempVariable$var23$411;
																		if(((index$i$408 + 1) == (j + 1))) {
																			{
																				double var50 = ((((1.0 * v1) + traceTempVariable$var41$378_1) + v3[(j + 1)]) / traceTempVariable$var49$412_1);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample26Value410) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value410);
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
													for(int index$sample18$391 = 0; index$sample18$391 < weightings.length; index$sample18$391 += 1) {
														int distributionTempVariable$var15$393 = index$sample18$391;
														double cv$probabilitySample18Value392 = (cv$probabilitySample26Value376 * distribution$sample18[index$sample18$391]);
														int traceTempVariable$var33$394_1 = distributionTempVariable$var15$393;
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var45$394_3 = traceTempVariable$var33$394_1;
																if((k == (j + 1))) {
																	int traceTempVariable$var49$413_1 = distributionTempVariable$var23$377;
																	if(((i + 1) == (j + 1))) {
																		{
																			double var50 = ((((1.0 * v1) + traceTempVariable$var41$378_1) + traceTempVariable$var45$394_3) / traceTempVariable$var49$413_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample18Value392) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value392);
																		}
																	}
																	for(int index$i$414 = 0; index$i$414 < size; index$i$414 += 1) {
																		if(!(index$i$414 == i)) {
																			for(int index$sample26$415 = 0; index$sample26$415 < weightings.length; index$sample26$415 += 1) {
																				int distributionTempVariable$var23$417 = index$sample26$415;
																				double cv$probabilitySample26Value416 = (cv$probabilitySample18Value392 * distribution$sample26[((index$i$414 - 0) / 1)][index$sample26$415]);
																				int traceTempVariable$var49$418_1 = distributionTempVariable$var23$417;
																				if(((index$i$414 + 1) == (j + 1))) {
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$378_1) + traceTempVariable$var45$394_3) / traceTempVariable$var49$418_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample26Value416) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
							}
						}
					} else {
						if(true) {
							for(int index$sample12$369 = 0; index$sample12$369 < weightings.length; index$sample12$369 += 1) {
								int distributionTempVariable$v1$371 = index$sample12$369;
								double cv$probabilitySample12Value370 = (1.0 * distribution$sample12[index$sample12$369]);
								int traceTempVariable$v1$372_1 = distributionTempVariable$v1$371;
								if(fixedFlag$sample26) {
									for(int i = 0; i < size; i += 1) {
										if(((i + 1) == j)) {
											if(fixedFlag$sample18) {
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														if((k == (j + 1))) {
															for(int index$i$419_1 = 0; index$i$419_1 < size; index$i$419_1 += 1) {
																if(((index$i$419_1 + 1) == (j + 1))) {
																	{
																		double var50 = ((((1.0 * traceTempVariable$v1$372_1) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample12Value370) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																		if((cv$weightedProbability < cv$distributionAccumulator))
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																		else {
																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																				cv$distributionAccumulator = cv$weightedProbability;
																			else
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																		}
																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample12Value370);
																	}
																}
															}
														}
													}
												}
											} else {
												if(true) {
													for(int index$sample18$396 = 0; index$sample18$396 < weightings.length; index$sample18$396 += 1) {
														int distributionTempVariable$var15$398 = index$sample18$396;
														double cv$probabilitySample18Value397 = (cv$probabilitySample12Value370 * distribution$sample18[index$sample18$396]);
														int traceTempVariable$var33$399_1 = distributionTempVariable$var15$398;
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var45$399_3 = traceTempVariable$var33$399_1;
																if((k == (j + 1))) {
																	for(int index$i$420_1 = 0; index$i$420_1 < size; index$i$420_1 += 1) {
																		if(((index$i$420_1 + 1) == (j + 1))) {
																			{
																				double var50 = ((((1.0 * traceTempVariable$v1$372_1) + v2[j]) + traceTempVariable$var45$399_3) / v2[(j + 1)]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample18Value397) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value397);
																			}
																		}
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
											for(int index$sample26$381 = 0; index$sample26$381 < weightings.length; index$sample26$381 += 1) {
												int distributionTempVariable$var23$383 = index$sample26$381;
												double cv$probabilitySample26Value382 = (cv$probabilitySample12Value370 * distribution$sample26[((i - 0) / 1)][index$sample26$381]);
												int traceTempVariable$var41$384_1 = distributionTempVariable$var23$383;
												if(((i + 1) == j)) {
													if(fixedFlag$sample18) {
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																if((k == (j + 1))) {
																	int traceTempVariable$var49$421_1 = distributionTempVariable$var23$383;
																	if(((i + 1) == (j + 1))) {
																		{
																			double var50 = ((((1.0 * traceTempVariable$v1$372_1) + traceTempVariable$var41$384_1) + v3[(j + 1)]) / traceTempVariable$var49$421_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample26Value382) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value382);
																		}
																	}
																	for(int index$i$422 = 0; index$i$422 < size; index$i$422 += 1) {
																		if(!(index$i$422 == i)) {
																			for(int index$sample26$423 = 0; index$sample26$423 < weightings.length; index$sample26$423 += 1) {
																				int distributionTempVariable$var23$425 = index$sample26$423;
																				double cv$probabilitySample26Value424 = (cv$probabilitySample26Value382 * distribution$sample26[((index$i$422 - 0) / 1)][index$sample26$423]);
																				int traceTempVariable$var49$426_1 = distributionTempVariable$var23$425;
																				if(((index$i$422 + 1) == (j + 1))) {
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$372_1) + traceTempVariable$var41$384_1) + v3[(j + 1)]) / traceTempVariable$var49$426_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample26Value424) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value424);
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
															for(int index$sample18$401 = 0; index$sample18$401 < weightings.length; index$sample18$401 += 1) {
																int distributionTempVariable$var15$403 = index$sample18$401;
																double cv$probabilitySample18Value402 = (cv$probabilitySample26Value382 * distribution$sample18[index$sample18$401]);
																int traceTempVariable$var33$404_1 = distributionTempVariable$var15$403;
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var45$404_3 = traceTempVariable$var33$404_1;
																		if((k == (j + 1))) {
																			int traceTempVariable$var49$427_1 = distributionTempVariable$var23$383;
																			if(((i + 1) == (j + 1))) {
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$372_1) + traceTempVariable$var41$384_1) + traceTempVariable$var45$404_3) / traceTempVariable$var49$427_1);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample18Value402) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
																					if((cv$weightedProbability < cv$distributionAccumulator))
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																					else {
																						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																							cv$distributionAccumulator = cv$weightedProbability;
																						else
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																					}
																					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value402);
																				}
																			}
																			for(int index$i$428 = 0; index$i$428 < size; index$i$428 += 1) {
																				if(!(index$i$428 == i)) {
																					for(int index$sample26$429 = 0; index$sample26$429 < weightings.length; index$sample26$429 += 1) {
																						int distributionTempVariable$var23$431 = index$sample26$429;
																						double cv$probabilitySample26Value430 = (cv$probabilitySample18Value402 * distribution$sample26[((index$i$428 - 0) / 1)][index$sample26$429]);
																						int traceTempVariable$var49$432_1 = distributionTempVariable$var23$431;
																						if(((index$i$428 + 1) == (j + 1))) {
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$372_1) + traceTempVariable$var41$384_1) + traceTempVariable$var45$404_3) / traceTempVariable$var49$432_1);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample26Value430) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
						}
					}
					if(fixedFlag$sample12) {
						if(fixedFlag$sample26) {
							for(int i = 0; i < size; i += 1) {
								if(((i + 1) == j)) {
									for(int index$i$450_1 = 0; index$i$450_1 < size; index$i$450_1 += 1) {
										for(int k = 0; k < (size + 1); k += 1) {
											if(((index$i$450_1 + 1) == k)) {
												if((k == (j + 1))) {
													for(int index$i$464_1 = 0; index$i$464_1 < size; index$i$464_1 += 1) {
														if(((index$i$464_1 + 1) == (j + 1))) {
															{
																double var50 = ((((1.0 * v1) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
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
										int traceTempVariable$var41$443_1 = distributionTempVariable$var23$442;
										if(((i + 1) == j)) {
											int traceTempVariable$var33$451_1 = distributionTempVariable$var23$442;
											for(int k = 0; k < (size + 1); k += 1) {
												if(((i + 1) == k)) {
													int traceTempVariable$var45$451_3 = traceTempVariable$var33$451_1;
													if((k == (j + 1))) {
														int traceTempVariable$var49$465_1 = distributionTempVariable$var23$442;
														if(((i + 1) == (j + 1))) {
															{
																double var50 = ((((1.0 * v1) + traceTempVariable$var41$443_1) + traceTempVariable$var45$451_3) / traceTempVariable$var49$465_1);
																double cv$weightedProbability = (Math.log(cv$probabilitySample26Value441) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
																	int traceTempVariable$var49$470_1 = distributionTempVariable$var23$469;
																	if(((index$i$466 + 1) == (j + 1))) {
																		{
																			double var50 = ((((1.0 * v1) + traceTempVariable$var41$443_1) + traceTempVariable$var45$451_3) / traceTempVariable$var49$470_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample26Value468) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
												}
											}
											for(int index$i$452 = 0; index$i$452 < size; index$i$452 += 1) {
												if(!(index$i$452 == i)) {
													for(int index$sample26$453 = 0; index$sample26$453 < weightings.length; index$sample26$453 += 1) {
														int distributionTempVariable$var23$455 = index$sample26$453;
														double cv$probabilitySample26Value454 = (cv$probabilitySample26Value441 * distribution$sample26[((index$i$452 - 0) / 1)][index$sample26$453]);
														int traceTempVariable$var33$456_1 = distributionTempVariable$var23$455;
														for(int k = 0; k < (size + 1); k += 1) {
															if(((index$i$452 + 1) == k)) {
																int traceTempVariable$var45$456_3 = traceTempVariable$var33$456_1;
																if((k == (j + 1))) {
																	int traceTempVariable$var49$471_1 = distributionTempVariable$var23$442;
																	if(((i + 1) == (j + 1))) {
																		{
																			double var50 = ((((1.0 * v1) + traceTempVariable$var41$443_1) + traceTempVariable$var45$456_3) / traceTempVariable$var49$471_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample26Value454) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
																	int traceTempVariable$var49$472_1 = distributionTempVariable$var23$455;
																	if(((index$i$452 + 1) == (j + 1))) {
																		{
																			double var50 = ((((1.0 * v1) + traceTempVariable$var41$443_1) + traceTempVariable$var45$456_3) / traceTempVariable$var49$472_1);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample26Value454) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
																				int traceTempVariable$var49$477_1 = distributionTempVariable$var23$476;
																				if(((index$i$473 + 1) == (j + 1))) {
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$443_1) + traceTempVariable$var45$456_3) / traceTempVariable$var49$477_1);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample26Value475) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
							}
						}
					} else {
						if(true) {
							for(int index$sample12$434 = 0; index$sample12$434 < weightings.length; index$sample12$434 += 1) {
								int distributionTempVariable$v1$436 = index$sample12$434;
								double cv$probabilitySample12Value435 = (1.0 * distribution$sample12[index$sample12$434]);
								int traceTempVariable$v1$437_1 = distributionTempVariable$v1$436;
								if(fixedFlag$sample26) {
									for(int i = 0; i < size; i += 1) {
										if(((i + 1) == j)) {
											for(int index$i$457_1 = 0; index$i$457_1 < size; index$i$457_1 += 1) {
												for(int k = 0; k < (size + 1); k += 1) {
													if(((index$i$457_1 + 1) == k)) {
														if((k == (j + 1))) {
															for(int index$i$478_1 = 0; index$i$478_1 < size; index$i$478_1 += 1) {
																if(((index$i$478_1 + 1) == (j + 1))) {
																	{
																		double var50 = ((((1.0 * traceTempVariable$v1$437_1) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample12Value435) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
										}
									}
								} else {
									for(int i = 0; i < size; i += 1) {
										if(true) {
											for(int index$sample26$446 = 0; index$sample26$446 < weightings.length; index$sample26$446 += 1) {
												int distributionTempVariable$var23$448 = index$sample26$446;
												double cv$probabilitySample26Value447 = (cv$probabilitySample12Value435 * distribution$sample26[((i - 0) / 1)][index$sample26$446]);
												int traceTempVariable$var41$449_1 = distributionTempVariable$var23$448;
												if(((i + 1) == j)) {
													int traceTempVariable$var33$458_1 = distributionTempVariable$var23$448;
													for(int k = 0; k < (size + 1); k += 1) {
														if(((i + 1) == k)) {
															int traceTempVariable$var45$458_3 = traceTempVariable$var33$458_1;
															if((k == (j + 1))) {
																int traceTempVariable$var49$479_1 = distributionTempVariable$var23$448;
																if(((i + 1) == (j + 1))) {
																	{
																		double var50 = ((((1.0 * traceTempVariable$v1$437_1) + traceTempVariable$var41$449_1) + traceTempVariable$var45$458_3) / traceTempVariable$var49$479_1);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample26Value447) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
																			int traceTempVariable$var49$484_1 = distributionTempVariable$var23$483;
																			if(((index$i$480 + 1) == (j + 1))) {
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$437_1) + traceTempVariable$var41$449_1) + traceTempVariable$var45$458_3) / traceTempVariable$var49$484_1);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample26Value482) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
														}
													}
													for(int index$i$459 = 0; index$i$459 < size; index$i$459 += 1) {
														if(!(index$i$459 == i)) {
															for(int index$sample26$460 = 0; index$sample26$460 < weightings.length; index$sample26$460 += 1) {
																int distributionTempVariable$var23$462 = index$sample26$460;
																double cv$probabilitySample26Value461 = (cv$probabilitySample26Value447 * distribution$sample26[((index$i$459 - 0) / 1)][index$sample26$460]);
																int traceTempVariable$var33$463_1 = distributionTempVariable$var23$462;
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((index$i$459 + 1) == k)) {
																		int traceTempVariable$var45$463_3 = traceTempVariable$var33$463_1;
																		if((k == (j + 1))) {
																			int traceTempVariable$var49$485_1 = distributionTempVariable$var23$448;
																			if(((i + 1) == (j + 1))) {
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$437_1) + traceTempVariable$var41$449_1) + traceTempVariable$var45$463_3) / traceTempVariable$var49$485_1);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample26Value461) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
																			int traceTempVariable$var49$486_1 = distributionTempVariable$var23$462;
																			if(((index$i$459 + 1) == (j + 1))) {
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$437_1) + traceTempVariable$var41$449_1) + traceTempVariable$var45$463_3) / traceTempVariable$var49$486_1);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample26Value461) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
																						int traceTempVariable$var49$491_1 = distributionTempVariable$var23$490;
																						if(((index$i$487 + 1) == (j + 1))) {
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$437_1) + traceTempVariable$var41$449_1) + traceTempVariable$var45$463_3) / traceTempVariable$var49$491_1);
																								double cv$weightedProbability = (Math.log(cv$probabilitySample26Value489) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
				logProbability$sample55[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample55 = (((fixedFlag$sample55 && fixedFlag$sample12) && fixedFlag$sample18) && fixedFlag$sample26);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample55[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var51[((j - 0) / 1)] = cv$rvAccumulator;
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
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$sampleValue, weightings));
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
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$sampleValue, weightings));
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
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$sampleValue, weightings));
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
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample55() {
		if(!fixedProbFlag$sample55) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = v[j];
					{
						{
							double var50 = ((((1.0 * v1) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
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
				logProbability$sample55[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample55 = (((fixedFlag$sample55 && fixedFlag$sample12) && fixedFlag$sample18) && fixedFlag$sample26);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample55[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var51[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample12() {
		double[] cv$stateProbabilityLocal = cv$var9$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < weightings.length; cv$valuePos += 1) {
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
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$currentValue, cv$temp$0$weightings));
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
											for(int k = 0; k < (size + 1); k += 1) {
												if((0 == k)) {
													if((k == (j + 1))) {
														if((0 == (j + 1))) {
															{
																{
																	double cv$temp$1$var50;
																	{
																		double var50 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
																		cv$temp$1$var50 = var50;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var50)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var50));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var50)));
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
											for(int index$sample18$4 = 0; index$sample18$4 < weightings.length; index$sample18$4 += 1) {
												int distributionTempVariable$var15$6 = index$sample18$4;
												double cv$probabilitySample18Value5 = (1.0 * distribution$sample18[index$sample18$4]);
												int traceTempVariable$var41$7_1 = distributionTempVariable$var15$6;
												if((0 == j)) {
													int traceTempVariable$var33$9_1 = distributionTempVariable$var15$6;
													for(int k = 0; k < (size + 1); k += 1) {
														if((0 == k)) {
															int traceTempVariable$var45$9_3 = traceTempVariable$var33$9_1;
															if((k == (j + 1))) {
																int traceTempVariable$var49$15_1 = distributionTempVariable$var15$6;
																if((0 == (j + 1))) {
																	{
																		{
																			double cv$temp$2$var50;
																			{
																				double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$7_1) + traceTempVariable$var45$9_3) / traceTempVariable$var49$15_1);
																				cv$temp$2$var50 = var50;
																			}
																			if(((Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var50)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var50));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var50)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value5);
																		}
																	}
																}
																if(!true) {
																	for(int index$sample18$16 = 0; index$sample18$16 < weightings.length; index$sample18$16 += 1) {
																		int distributionTempVariable$var15$18 = index$sample18$16;
																		double cv$probabilitySample18Value17 = (cv$probabilitySample18Value5 * distribution$sample18[index$sample18$16]);
																		int traceTempVariable$var49$19_1 = distributionTempVariable$var15$18;
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$3$var50;
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$7_1) + traceTempVariable$var45$9_3) / traceTempVariable$var49$19_1);
																						cv$temp$3$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value17);
																				}
																			}
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
															for(int k = 0; k < (size + 1); k += 1) {
																if((0 == k)) {
																	int traceTempVariable$var45$13_3 = traceTempVariable$var33$13_1;
																	if((k == (j + 1))) {
																		int traceTempVariable$var49$20_1 = distributionTempVariable$var15$6;
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$4$var50;
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$7_1) + traceTempVariable$var45$13_3) / traceTempVariable$var49$20_1);
																						cv$temp$4$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample18Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value11);
																				}
																			}
																		}
																		int traceTempVariable$var49$21_1 = distributionTempVariable$var15$12;
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$5$var50;
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$7_1) + traceTempVariable$var45$13_3) / traceTempVariable$var49$21_1);
																						cv$temp$5$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample18Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value11);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample18$22 = 0; index$sample18$22 < weightings.length; index$sample18$22 += 1) {
																				int distributionTempVariable$var15$24 = index$sample18$22;
																				double cv$probabilitySample18Value23 = (cv$probabilitySample18Value11 * distribution$sample18[index$sample18$22]);
																				int traceTempVariable$var49$25_1 = distributionTempVariable$var15$24;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$6$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$7_1) + traceTempVariable$var45$13_3) / traceTempVariable$var49$25_1);
																								cv$temp$6$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample18Value23) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value23) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value23) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value23) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value23) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var50)));
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
										}
									}
									if(fixedFlag$sample18) {
										if((0 == j)) {
											if(fixedFlag$sample26) {
												for(int i = 0; i < size; i += 1) {
													for(int k = 0; k < (size + 1); k += 1) {
														if(((i + 1) == k)) {
															if((k == (j + 1))) {
																if((0 == (j + 1))) {
																	{
																		{
																			double cv$temp$7$var50;
																			{
																				double var50 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
																				cv$temp$7$var50 = var50;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var50)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var50));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var50)));
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
														for(int index$sample26$33 = 0; index$sample26$33 < weightings.length; index$sample26$33 += 1) {
															int distributionTempVariable$var23$35 = index$sample26$33;
															double cv$probabilitySample26Value34 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$33]);
															int traceTempVariable$var33$36_1 = distributionTempVariable$var23$35;
															for(int k = 0; k < (size + 1); k += 1) {
																if(((i + 1) == k)) {
																	int traceTempVariable$var45$36_3 = traceTempVariable$var33$36_1;
																	if((k == (j + 1))) {
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$8$var50;
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var45$36_3) / v2[(j + 1)]);
																						cv$temp$8$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample26Value34) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value34) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value34) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value34) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value34) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value34);
																				}
																			}
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
											for(int index$sample18$27 = 0; index$sample18$27 < weightings.length; index$sample18$27 += 1) {
												int distributionTempVariable$var15$29 = index$sample18$27;
												double cv$probabilitySample18Value28 = (1.0 * distribution$sample18[index$sample18$27]);
												int traceTempVariable$var41$30_1 = distributionTempVariable$var15$29;
												if((0 == j)) {
													if(fixedFlag$sample26) {
														for(int i = 0; i < size; i += 1) {
															for(int k = 0; k < (size + 1); k += 1) {
																if(((i + 1) == k)) {
																	if((k == (j + 1))) {
																		int traceTempVariable$var49$45_1 = distributionTempVariable$var15$29;
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$9$var50;
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$30_1) + v3[(j + 1)]) / traceTempVariable$var49$45_1);
																						cv$temp$9$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample18Value28) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value28) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value28) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value28) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value28) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value28);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample18$46 = 0; index$sample18$46 < weightings.length; index$sample18$46 += 1) {
																				int distributionTempVariable$var15$48 = index$sample18$46;
																				double cv$probabilitySample18Value47 = (cv$probabilitySample18Value28 * distribution$sample18[index$sample18$46]);
																				int traceTempVariable$var49$49_1 = distributionTempVariable$var15$48;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$10$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$30_1) + v3[(j + 1)]) / traceTempVariable$var49$49_1);
																								cv$temp$10$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample18Value47) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value47) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value47) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value47) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value47) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value47);
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
																for(int index$sample26$39 = 0; index$sample26$39 < weightings.length; index$sample26$39 += 1) {
																	int distributionTempVariable$var23$41 = index$sample26$39;
																	double cv$probabilitySample26Value40 = (cv$probabilitySample18Value28 * distribution$sample26[((i - 0) / 1)][index$sample26$39]);
																	int traceTempVariable$var33$42_1 = distributionTempVariable$var23$41;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if(((i + 1) == k)) {
																			int traceTempVariable$var45$42_3 = traceTempVariable$var33$42_1;
																			if((k == (j + 1))) {
																				int traceTempVariable$var49$50_1 = distributionTempVariable$var15$29;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$11$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$30_1) + traceTempVariable$var45$42_3) / traceTempVariable$var49$50_1);
																								cv$temp$11$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample26Value40) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value40) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value40) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value40) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value40) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value40);
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample18$51 = 0; index$sample18$51 < weightings.length; index$sample18$51 += 1) {
																						int distributionTempVariable$var15$53 = index$sample18$51;
																						double cv$probabilitySample18Value52 = (cv$probabilitySample26Value40 * distribution$sample18[index$sample18$51]);
																						int traceTempVariable$var49$54_1 = distributionTempVariable$var15$53;
																						if((0 == (j + 1))) {
																							{
																								{
																									double cv$temp$12$var50;
																									{
																										double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$30_1) + traceTempVariable$var45$42_3) / traceTempVariable$var49$54_1);
																										cv$temp$12$var50 = var50;
																									}
																									if(((Math.log(cv$probabilitySample18Value52) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var50)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value52) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value52) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var50));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value52) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value52) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var50)));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value52);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
													for(int k = 0; k < (size + 1); k += 1) {
														if((0 == k)) {
															if((k == (j + 1))) {
																if((0 == (j + 1))) {
																	{
																		{
																			double cv$temp$13$var50;
																			{
																				double var50 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
																				cv$temp$13$var50 = var50;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var50)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var50));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var50)));
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
														for(int index$sample18$62 = 0; index$sample18$62 < weightings.length; index$sample18$62 += 1) {
															int distributionTempVariable$var15$64 = index$sample18$62;
															double cv$probabilitySample18Value63 = (1.0 * distribution$sample18[index$sample18$62]);
															int traceTempVariable$var33$65_1 = distributionTempVariable$var15$64;
															for(int k = 0; k < (size + 1); k += 1) {
																if((0 == k)) {
																	int traceTempVariable$var45$65_3 = traceTempVariable$var33$65_1;
																	if((k == (j + 1))) {
																		int traceTempVariable$var49$72_1 = distributionTempVariable$var15$64;
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$14$var50;
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var45$65_3) / traceTempVariable$var49$72_1);
																						cv$temp$14$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample18Value63) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value63) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value63) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value63) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value63) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value63);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample18$73 = 0; index$sample18$73 < weightings.length; index$sample18$73 += 1) {
																				int distributionTempVariable$var15$75 = index$sample18$73;
																				double cv$probabilitySample18Value74 = (cv$probabilitySample18Value63 * distribution$sample18[index$sample18$73]);
																				int traceTempVariable$var49$76_1 = distributionTempVariable$var15$75;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$15$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var45$65_3) / traceTempVariable$var49$76_1);
																								cv$temp$15$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample18Value74) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value74) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value74) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value74) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value74) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value74);
																						}
																					}
																				}
																			}
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
												for(int index$sample26$57 = 0; index$sample26$57 < weightings.length; index$sample26$57 += 1) {
													int distributionTempVariable$var23$59 = index$sample26$57;
													double cv$probabilitySample26Value58 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$57]);
													int traceTempVariable$var41$60_1 = distributionTempVariable$var23$59;
													if(((i + 1) == j)) {
														if(fixedFlag$sample18) {
															for(int k = 0; k < (size + 1); k += 1) {
																if((0 == k)) {
																	if((k == (j + 1))) {
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$16$var50;
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$60_1) + v3[(j + 1)]) / v2[(j + 1)]);
																						cv$temp$16$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample26Value58) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value58) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value58) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value58) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value58) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value58);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															if(true) {
																for(int index$sample18$67 = 0; index$sample18$67 < weightings.length; index$sample18$67 += 1) {
																	int distributionTempVariable$var15$69 = index$sample18$67;
																	double cv$probabilitySample18Value68 = (cv$probabilitySample26Value58 * distribution$sample18[index$sample18$67]);
																	int traceTempVariable$var33$70_1 = distributionTempVariable$var15$69;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if((0 == k)) {
																			int traceTempVariable$var45$70_3 = traceTempVariable$var33$70_1;
																			if((k == (j + 1))) {
																				int traceTempVariable$var49$78_1 = distributionTempVariable$var15$69;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$17$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$60_1) + traceTempVariable$var45$70_3) / traceTempVariable$var49$78_1);
																								cv$temp$17$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample18Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value68);
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample18$79 = 0; index$sample18$79 < weightings.length; index$sample18$79 += 1) {
																						int distributionTempVariable$var15$81 = index$sample18$79;
																						double cv$probabilitySample18Value80 = (cv$probabilitySample18Value68 * distribution$sample18[index$sample18$79]);
																						int traceTempVariable$var49$82_1 = distributionTempVariable$var15$81;
																						if((0 == (j + 1))) {
																							{
																								{
																									double cv$temp$18$var50;
																									{
																										double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$60_1) + traceTempVariable$var45$70_3) / traceTempVariable$var49$82_1);
																										cv$temp$18$var50 = var50;
																									}
																									if(((Math.log(cv$probabilitySample18Value80) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var50)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value80) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value80) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var50));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value80) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value80) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var50)));
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
										}
									}
									if(fixedFlag$sample26) {
										for(int i = 0; i < size; i += 1) {
											if(((i + 1) == j)) {
												for(int index$i$89_1 = 0; index$i$89_1 < size; index$i$89_1 += 1) {
													for(int k = 0; k < (size + 1); k += 1) {
														if(((index$i$89_1 + 1) == k)) {
															if((k == (j + 1))) {
																if(fixedFlag$sample18) {
																	if((0 == (j + 1))) {
																		{
																			{
																				double cv$temp$19$var50;
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
																					cv$temp$19$var50 = var50;
																				}
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var50)));
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
																			int traceTempVariable$var49$100_1 = distributionTempVariable$var15$99;
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$20$var50;
																						{
																							double var50 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v3[(j + 1)]) / traceTempVariable$var49$100_1);
																							cv$temp$20$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample18Value98) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value98) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value98) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value98) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value98) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var50)));
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
											}
										}
									} else {
										for(int i = 0; i < size; i += 1) {
											if(true) {
												for(int index$sample26$85 = 0; index$sample26$85 < weightings.length; index$sample26$85 += 1) {
													int distributionTempVariable$var23$87 = index$sample26$85;
													double cv$probabilitySample26Value86 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$85]);
													int traceTempVariable$var41$88_1 = distributionTempVariable$var23$87;
													if(((i + 1) == j)) {
														int traceTempVariable$var33$90_1 = distributionTempVariable$var23$87;
														for(int k = 0; k < (size + 1); k += 1) {
															if(((i + 1) == k)) {
																int traceTempVariable$var45$90_3 = traceTempVariable$var33$90_1;
																if((k == (j + 1))) {
																	if(fixedFlag$sample18) {
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$21$var50;
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$88_1) + traceTempVariable$var45$90_3) / v2[(j + 1)]);
																						cv$temp$21$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample26Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var50)));
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
																				int traceTempVariable$var49$105_1 = distributionTempVariable$var15$104;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$22$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$88_1) + traceTempVariable$var45$90_3) / traceTempVariable$var49$105_1);
																								cv$temp$22$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample18Value103) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value103) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value103) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value103) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value103) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value103);
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
																for(int index$sample26$92 = 0; index$sample26$92 < weightings.length; index$sample26$92 += 1) {
																	int distributionTempVariable$var23$94 = index$sample26$92;
																	double cv$probabilitySample26Value93 = (cv$probabilitySample26Value86 * distribution$sample26[((index$i$91 - 0) / 1)][index$sample26$92]);
																	int traceTempVariable$var33$95_1 = distributionTempVariable$var23$94;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if(((index$i$91 + 1) == k)) {
																			int traceTempVariable$var45$95_3 = traceTempVariable$var33$95_1;
																			if((k == (j + 1))) {
																				if(fixedFlag$sample18) {
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$23$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$88_1) + traceTempVariable$var45$95_3) / v2[(j + 1)]);
																									cv$temp$23$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value93) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value93) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value93) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value93) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value93) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var50)));
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
																							int traceTempVariable$var49$110_1 = distributionTempVariable$var15$109;
																							if((0 == (j + 1))) {
																								{
																									{
																										double cv$temp$24$var50;
																										{
																											double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$88_1) + traceTempVariable$var45$95_3) / traceTempVariable$var49$110_1);
																											cv$temp$24$var50 = var50;
																										}
																										if(((Math.log(cv$probabilitySample18Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var50)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var50));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var50)));
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
										}
									}
									if(fixedFlag$sample18) {
										if((0 == j)) {
											for(int k = 0; k < (size + 1); k += 1) {
												if((0 == k)) {
													if((k == (j + 1))) {
														if(fixedFlag$sample26) {
															for(int i = 0; i < size; i += 1) {
																if(((i + 1) == (j + 1))) {
																	{
																		{
																			double cv$temp$25$var50;
																			{
																				double var50 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
																				cv$temp$25$var50 = var50;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var50)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var50));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var50)));
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
																		int traceTempVariable$var49$127_1 = distributionTempVariable$var23$126;
																		if(((i + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$26$var50;
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v3[(j + 1)]) / traceTempVariable$var49$127_1);
																						cv$temp$26$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample26Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var50)));
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
											}
										}
									} else {
										if(true) {
											for(int index$sample18$112 = 0; index$sample18$112 < weightings.length; index$sample18$112 += 1) {
												int distributionTempVariable$var15$114 = index$sample18$112;
												double cv$probabilitySample18Value113 = (1.0 * distribution$sample18[index$sample18$112]);
												int traceTempVariable$var41$115_1 = distributionTempVariable$var15$114;
												if((0 == j)) {
													int traceTempVariable$var33$117_1 = distributionTempVariable$var15$114;
													for(int k = 0; k < (size + 1); k += 1) {
														if((0 == k)) {
															int traceTempVariable$var45$117_3 = traceTempVariable$var33$117_1;
															if((k == (j + 1))) {
																if(fixedFlag$sample26) {
																	for(int i = 0; i < size; i += 1) {
																		if(((i + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$27$var50;
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$115_1) + traceTempVariable$var45$117_3) / v2[(j + 1)]);
																						cv$temp$27$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample18Value113) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value113) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value113) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value113) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value113) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var50)));
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
																				int traceTempVariable$var49$133_1 = distributionTempVariable$var23$132;
																				if(((i + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$28$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$115_1) + traceTempVariable$var45$117_3) / traceTempVariable$var49$133_1);
																								cv$temp$28$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var50)));
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
														}
													}
													if(!true) {
														for(int index$sample18$118 = 0; index$sample18$118 < weightings.length; index$sample18$118 += 1) {
															int distributionTempVariable$var15$120 = index$sample18$118;
															double cv$probabilitySample18Value119 = (cv$probabilitySample18Value113 * distribution$sample18[index$sample18$118]);
															int traceTempVariable$var33$121_1 = distributionTempVariable$var15$120;
															for(int k = 0; k < (size + 1); k += 1) {
																if((0 == k)) {
																	int traceTempVariable$var45$121_3 = traceTempVariable$var33$121_1;
																	if((k == (j + 1))) {
																		if(fixedFlag$sample26) {
																			for(int i = 0; i < size; i += 1) {
																				if(((i + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$29$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$115_1) + traceTempVariable$var45$121_3) / v2[(j + 1)]);
																								cv$temp$29$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample18Value119) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value119) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value119) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value119) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value119) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var50)));
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
																						int traceTempVariable$var49$139_1 = distributionTempVariable$var23$138;
																						if(((i + 1) == (j + 1))) {
																							{
																								{
																									double cv$temp$30$var50;
																									{
																										double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$115_1) + traceTempVariable$var45$121_3) / traceTempVariable$var49$139_1);
																										cv$temp$30$var50 = var50;
																									}
																									if(((Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var50)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var50));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var50)));
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
										}
									}
									if(fixedFlag$sample18) {
										if((0 == j)) {
											if(fixedFlag$sample26) {
												for(int i = 0; i < size; i += 1) {
													for(int k = 0; k < (size + 1); k += 1) {
														if(((i + 1) == k)) {
															if((k == (j + 1))) {
																for(int index$i$157_1 = 0; index$i$157_1 < size; index$i$157_1 += 1) {
																	if(((index$i$157_1 + 1) == (j + 1))) {
																		{
																			{
																				double cv$temp$31$var50;
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
																					cv$temp$31$var50 = var50;
																				}
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var50)));
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
														for(int index$sample26$147 = 0; index$sample26$147 < weightings.length; index$sample26$147 += 1) {
															int distributionTempVariable$var23$149 = index$sample26$147;
															double cv$probabilitySample26Value148 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$147]);
															int traceTempVariable$var33$150_1 = distributionTempVariable$var23$149;
															for(int k = 0; k < (size + 1); k += 1) {
																if(((i + 1) == k)) {
																	int traceTempVariable$var45$150_3 = traceTempVariable$var33$150_1;
																	if((k == (j + 1))) {
																		int traceTempVariable$var49$158_1 = distributionTempVariable$var23$149;
																		if(((i + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$32$var50;
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var45$150_3) / traceTempVariable$var49$158_1);
																						cv$temp$32$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample26Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value148);
																				}
																			}
																		}
																		for(int index$i$159 = 0; index$i$159 < size; index$i$159 += 1) {
																			if(!(index$i$159 == i)) {
																				for(int index$sample26$160 = 0; index$sample26$160 < weightings.length; index$sample26$160 += 1) {
																					int distributionTempVariable$var23$162 = index$sample26$160;
																					double cv$probabilitySample26Value161 = (cv$probabilitySample26Value148 * distribution$sample26[((index$i$159 - 0) / 1)][index$sample26$160]);
																					int traceTempVariable$var49$163_1 = distributionTempVariable$var23$162;
																					if(((index$i$159 + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$33$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var45$150_3) / traceTempVariable$var49$163_1);
																									cv$temp$33$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value161);
																							}
																						}
																					}
																				}
																			}
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
											for(int index$sample18$141 = 0; index$sample18$141 < weightings.length; index$sample18$141 += 1) {
												int distributionTempVariable$var15$143 = index$sample18$141;
												double cv$probabilitySample18Value142 = (1.0 * distribution$sample18[index$sample18$141]);
												int traceTempVariable$var41$144_1 = distributionTempVariable$var15$143;
												if((0 == j)) {
													if(fixedFlag$sample26) {
														for(int i = 0; i < size; i += 1) {
															for(int k = 0; k < (size + 1); k += 1) {
																if(((i + 1) == k)) {
																	if((k == (j + 1))) {
																		for(int index$i$164_1 = 0; index$i$164_1 < size; index$i$164_1 += 1) {
																			if(((index$i$164_1 + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$34$var50;
																						{
																							double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$144_1) + v3[(j + 1)]) / v2[(j + 1)]);
																							cv$temp$34$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample18Value142) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value142) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value142) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value142) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value142) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value142);
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
																for(int index$sample26$153 = 0; index$sample26$153 < weightings.length; index$sample26$153 += 1) {
																	int distributionTempVariable$var23$155 = index$sample26$153;
																	double cv$probabilitySample26Value154 = (cv$probabilitySample18Value142 * distribution$sample26[((i - 0) / 1)][index$sample26$153]);
																	int traceTempVariable$var33$156_1 = distributionTempVariable$var23$155;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if(((i + 1) == k)) {
																			int traceTempVariable$var45$156_3 = traceTempVariable$var33$156_1;
																			if((k == (j + 1))) {
																				int traceTempVariable$var49$165_1 = distributionTempVariable$var23$155;
																				if(((i + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$35$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$144_1) + traceTempVariable$var45$156_3) / traceTempVariable$var49$165_1);
																								cv$temp$35$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample26Value154) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value154) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value154) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value154) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value154) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value154);
																						}
																					}
																				}
																				for(int index$i$166 = 0; index$i$166 < size; index$i$166 += 1) {
																					if(!(index$i$166 == i)) {
																						for(int index$sample26$167 = 0; index$sample26$167 < weightings.length; index$sample26$167 += 1) {
																							int distributionTempVariable$var23$169 = index$sample26$167;
																							double cv$probabilitySample26Value168 = (cv$probabilitySample26Value154 * distribution$sample26[((index$i$166 - 0) / 1)][index$sample26$167]);
																							int traceTempVariable$var49$170_1 = distributionTempVariable$var23$169;
																							if(((index$i$166 + 1) == (j + 1))) {
																								{
																									{
																										double cv$temp$36$var50;
																										{
																											double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$144_1) + traceTempVariable$var45$156_3) / traceTempVariable$var49$170_1);
																											cv$temp$36$var50 = var50;
																										}
																										if(((Math.log(cv$probabilitySample26Value168) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var50)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value168) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value168) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var50));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value168) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value168) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var50)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value168);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
													for(int k = 0; k < (size + 1); k += 1) {
														if((0 == k)) {
															if((k == (j + 1))) {
																for(int index$i$187_1 = 0; index$i$187_1 < size; index$i$187_1 += 1) {
																	if(((index$i$187_1 + 1) == (j + 1))) {
																		{
																			{
																				double cv$temp$37$var50;
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
																					cv$temp$37$var50 = var50;
																				}
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var50)));
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
														for(int index$sample18$178 = 0; index$sample18$178 < weightings.length; index$sample18$178 += 1) {
															int distributionTempVariable$var15$180 = index$sample18$178;
															double cv$probabilitySample18Value179 = (1.0 * distribution$sample18[index$sample18$178]);
															int traceTempVariable$var33$181_1 = distributionTempVariable$var15$180;
															for(int k = 0; k < (size + 1); k += 1) {
																if((0 == k)) {
																	int traceTempVariable$var45$181_3 = traceTempVariable$var33$181_1;
																	if((k == (j + 1))) {
																		for(int index$i$188_1 = 0; index$i$188_1 < size; index$i$188_1 += 1) {
																			if(((index$i$188_1 + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$38$var50;
																						{
																							double var50 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + traceTempVariable$var45$181_3) / v2[(j + 1)]);
																							cv$temp$38$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample18Value179) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value179) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value179) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value179) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value179) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value179);
																					}
																				}
																			}
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
												for(int index$sample26$173 = 0; index$sample26$173 < weightings.length; index$sample26$173 += 1) {
													int distributionTempVariable$var23$175 = index$sample26$173;
													double cv$probabilitySample26Value174 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$173]);
													int traceTempVariable$var41$176_1 = distributionTempVariable$var23$175;
													if(((i + 1) == j)) {
														if(fixedFlag$sample18) {
															for(int k = 0; k < (size + 1); k += 1) {
																if((0 == k)) {
																	if((k == (j + 1))) {
																		int traceTempVariable$var49$189_1 = distributionTempVariable$var23$175;
																		if(((i + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$39$var50;
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$176_1) + v3[(j + 1)]) / traceTempVariable$var49$189_1);
																						cv$temp$39$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample26Value174) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value174) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value174) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value174) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value174) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value174);
																				}
																			}
																		}
																		for(int index$i$190 = 0; index$i$190 < size; index$i$190 += 1) {
																			if(!(index$i$190 == i)) {
																				for(int index$sample26$191 = 0; index$sample26$191 < weightings.length; index$sample26$191 += 1) {
																					int distributionTempVariable$var23$193 = index$sample26$191;
																					double cv$probabilitySample26Value192 = (cv$probabilitySample26Value174 * distribution$sample26[((index$i$190 - 0) / 1)][index$sample26$191]);
																					int traceTempVariable$var49$194_1 = distributionTempVariable$var23$193;
																					if(((index$i$190 + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$40$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$176_1) + v3[(j + 1)]) / traceTempVariable$var49$194_1);
																									cv$temp$40$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value192) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value192) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value192) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value192) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value192) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value192);
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
																for(int index$sample18$183 = 0; index$sample18$183 < weightings.length; index$sample18$183 += 1) {
																	int distributionTempVariable$var15$185 = index$sample18$183;
																	double cv$probabilitySample18Value184 = (cv$probabilitySample26Value174 * distribution$sample18[index$sample18$183]);
																	int traceTempVariable$var33$186_1 = distributionTempVariable$var15$185;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if((0 == k)) {
																			int traceTempVariable$var45$186_3 = traceTempVariable$var33$186_1;
																			if((k == (j + 1))) {
																				int traceTempVariable$var49$195_1 = distributionTempVariable$var23$175;
																				if(((i + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$41$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$176_1) + traceTempVariable$var45$186_3) / traceTempVariable$var49$195_1);
																								cv$temp$41$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample18Value184) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value184) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value184) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value184) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value184) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value184);
																						}
																					}
																				}
																				for(int index$i$196 = 0; index$i$196 < size; index$i$196 += 1) {
																					if(!(index$i$196 == i)) {
																						for(int index$sample26$197 = 0; index$sample26$197 < weightings.length; index$sample26$197 += 1) {
																							int distributionTempVariable$var23$199 = index$sample26$197;
																							double cv$probabilitySample26Value198 = (cv$probabilitySample18Value184 * distribution$sample26[((index$i$196 - 0) / 1)][index$sample26$197]);
																							int traceTempVariable$var49$200_1 = distributionTempVariable$var23$199;
																							if(((index$i$196 + 1) == (j + 1))) {
																								{
																									{
																										double cv$temp$42$var50;
																										{
																											double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$176_1) + traceTempVariable$var45$186_3) / traceTempVariable$var49$200_1);
																											cv$temp$42$var50 = var50;
																										}
																										if(((Math.log(cv$probabilitySample26Value198) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var50)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value198) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value198) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var50));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value198) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value198) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var50)));
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
										}
									}
									if(fixedFlag$sample26) {
										for(int i = 0; i < size; i += 1) {
											if(((i + 1) == j)) {
												for(int index$i$207_1 = 0; index$i$207_1 < size; index$i$207_1 += 1) {
													for(int k = 0; k < (size + 1); k += 1) {
														if(((index$i$207_1 + 1) == k)) {
															if((k == (j + 1))) {
																for(int index$i$214_1 = 0; index$i$214_1 < size; index$i$214_1 += 1) {
																	if(((index$i$214_1 + 1) == (j + 1))) {
																		{
																			{
																				double cv$temp$43$var50;
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]);
																					cv$temp$43$var50 = var50;
																				}
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50)));
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
												for(int index$sample26$203 = 0; index$sample26$203 < weightings.length; index$sample26$203 += 1) {
													int distributionTempVariable$var23$205 = index$sample26$203;
													double cv$probabilitySample26Value204 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$203]);
													int traceTempVariable$var41$206_1 = distributionTempVariable$var23$205;
													if(((i + 1) == j)) {
														int traceTempVariable$var33$208_1 = distributionTempVariable$var23$205;
														for(int k = 0; k < (size + 1); k += 1) {
															if(((i + 1) == k)) {
																int traceTempVariable$var45$208_3 = traceTempVariable$var33$208_1;
																if((k == (j + 1))) {
																	int traceTempVariable$var49$215_1 = distributionTempVariable$var23$205;
																	if(((i + 1) == (j + 1))) {
																		{
																			{
																				double cv$temp$44$var50;
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$206_1) + traceTempVariable$var45$208_3) / traceTempVariable$var49$215_1);
																					cv$temp$44$var50 = var50;
																				}
																				if(((Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var50)));
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
																				int traceTempVariable$var49$220_1 = distributionTempVariable$var23$219;
																				if(((index$i$216 + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$45$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$206_1) + traceTempVariable$var45$208_3) / traceTempVariable$var49$220_1);
																								cv$temp$45$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample26Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value218);
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
																for(int index$sample26$210 = 0; index$sample26$210 < weightings.length; index$sample26$210 += 1) {
																	int distributionTempVariable$var23$212 = index$sample26$210;
																	double cv$probabilitySample26Value211 = (cv$probabilitySample26Value204 * distribution$sample26[((index$i$209 - 0) / 1)][index$sample26$210]);
																	int traceTempVariable$var33$213_1 = distributionTempVariable$var23$212;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if(((index$i$209 + 1) == k)) {
																			int traceTempVariable$var45$213_3 = traceTempVariable$var33$213_1;
																			if((k == (j + 1))) {
																				int traceTempVariable$var49$221_1 = distributionTempVariable$var23$205;
																				if(((i + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$46$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$206_1) + traceTempVariable$var45$213_3) / traceTempVariable$var49$221_1);
																								cv$temp$46$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value211);
																						}
																					}
																				}
																				int traceTempVariable$var49$222_1 = distributionTempVariable$var23$212;
																				if(((index$i$209 + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$47$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$206_1) + traceTempVariable$var45$213_3) / traceTempVariable$var49$222_1);
																								cv$temp$47$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var50)));
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
																							int traceTempVariable$var49$227_1 = distributionTempVariable$var23$226;
																							if(((index$i$223 + 1) == (j + 1))) {
																								{
																									{
																										double cv$temp$48$var50;
																										{
																											double var50 = ((((1.0 * traceTempVariable$v1$1_2) + traceTempVariable$var41$206_1) + traceTempVariable$var45$213_3) / traceTempVariable$var49$227_1);
																											cv$temp$48$var50 = var50;
																										}
																										if(((Math.log(cv$probabilitySample26Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var50)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var50));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var50)));
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

	private final void sample18() {
		double[] cv$stateProbabilityLocal = cv$var15$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < weightings.length; cv$valuePos += 1) {
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
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$currentValue, cv$temp$0$weightings));
				{
					{
						boolean[] guard$sample18bernoulli54 = guard$sample18bernoulli54$global;
						for(int j = 0; j < size; j += 1) {
							if((0 == j))
								guard$sample18bernoulli54[((j - 0) / 1)] = false;
						}
						for(int j = 0; j < size; j += 1) {
							if((0 == (j + 1)))
								guard$sample18bernoulli54[((j - 0) / 1)] = false;
						}
						for(int k = 0; k < (size + 1); k += 1) {
							if((0 == k)) {
								for(int j = 0; j < size; j += 1) {
									if((k == (j + 1)))
										guard$sample18bernoulli54[((j - 0) / 1)] = false;
								}
							}
						}
						int traceTempVariable$var41$4_1 = cv$currentValue;
						for(int j = 0; j < size; j += 1) {
							if((0 == j)) {
								if(!guard$sample18bernoulli54[((j - 0) / 1)]) {
									guard$sample18bernoulli54[((j - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											if(fixedFlag$sample12) {
												int traceTempVariable$var33$15_1 = cv$currentValue;
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														int traceTempVariable$var45$15_3 = traceTempVariable$var33$15_1;
														if((k == (j + 1))) {
															int traceTempVariable$var49$25_1 = cv$currentValue;
															if((0 == (j + 1))) {
																{
																	{
																		double cv$temp$1$var50;
																		{
																			double var50 = ((((1.0 * v1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$15_3) / traceTempVariable$var49$25_1);
																			cv$temp$1$var50 = var50;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var50)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var50));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var50)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
															if(!true) {
																for(int index$sample18$26 = 0; index$sample18$26 < weightings.length; index$sample18$26 += 1) {
																	int distributionTempVariable$var15$28 = index$sample18$26;
																	double cv$probabilitySample18Value27 = (1.0 * distribution$sample18[index$sample18$26]);
																	int traceTempVariable$var49$29_1 = distributionTempVariable$var15$28;
																	if((0 == (j + 1))) {
																		{
																			{
																				double cv$temp$2$var50;
																				{
																					double var50 = ((((1.0 * v1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$15_3) / traceTempVariable$var49$29_1);
																					cv$temp$2$var50 = var50;
																				}
																				if(((Math.log(cv$probabilitySample18Value27) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value27) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value27) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value27) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value27) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var50)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value27);
																			}
																		}
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
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var45$19_3 = traceTempVariable$var33$19_1;
																if((k == (j + 1))) {
																	int traceTempVariable$var49$30_1 = cv$currentValue;
																	if((0 == (j + 1))) {
																		{
																			{
																				double cv$temp$3$var50;
																				{
																					double var50 = ((((1.0 * v1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$19_3) / traceTempVariable$var49$30_1);
																					cv$temp$3$var50 = var50;
																				}
																				if(((Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var50)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value17);
																			}
																		}
																	}
																	int traceTempVariable$var49$31_1 = distributionTempVariable$var15$18;
																	if((0 == (j + 1))) {
																		{
																			{
																				double cv$temp$4$var50;
																				{
																					double var50 = ((((1.0 * v1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$19_3) / traceTempVariable$var49$31_1);
																					cv$temp$4$var50 = var50;
																				}
																				if(((Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var50)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value17);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample18$32 = 0; index$sample18$32 < weightings.length; index$sample18$32 += 1) {
																			int distributionTempVariable$var15$34 = index$sample18$32;
																			double cv$probabilitySample18Value33 = (cv$probabilitySample18Value17 * distribution$sample18[index$sample18$32]);
																			int traceTempVariable$var49$35_1 = distributionTempVariable$var15$34;
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$5$var50;
																						{
																							double var50 = ((((1.0 * v1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$19_3) / traceTempVariable$var49$35_1);
																							cv$temp$5$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample18Value33) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value33) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value33) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value33) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value33) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var50)));
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
													}
												}
											} else {
												if(true) {
													for(int index$sample12$11 = 0; index$sample12$11 < weightings.length; index$sample12$11 += 1) {
														int distributionTempVariable$v1$13 = index$sample12$11;
														double cv$probabilitySample12Value12 = (1.0 * distribution$sample12[index$sample12$11]);
														int traceTempVariable$v1$14_1 = distributionTempVariable$v1$13;
														int traceTempVariable$var33$20_1 = cv$currentValue;
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var45$20_3 = traceTempVariable$var33$20_1;
																if((k == (j + 1))) {
																	int traceTempVariable$var49$36_1 = cv$currentValue;
																	if((0 == (j + 1))) {
																		{
																			{
																				double cv$temp$6$var50;
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$14_1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$20_3) / traceTempVariable$var49$36_1);
																					cv$temp$6$var50 = var50;
																				}
																				if(((Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var50)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value12);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample18$37 = 0; index$sample18$37 < weightings.length; index$sample18$37 += 1) {
																			int distributionTempVariable$var15$39 = index$sample18$37;
																			double cv$probabilitySample18Value38 = (cv$probabilitySample12Value12 * distribution$sample18[index$sample18$37]);
																			int traceTempVariable$var49$40_1 = distributionTempVariable$var15$39;
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$7$var50;
																						{
																							double var50 = ((((1.0 * traceTempVariable$v1$14_1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$20_3) / traceTempVariable$var49$40_1);
																							cv$temp$7$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample18Value38) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value38) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value38) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value38) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value38) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value38);
																					}
																				}
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
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var45$24_3 = traceTempVariable$var33$24_1;
																		if((k == (j + 1))) {
																			int traceTempVariable$var49$41_1 = cv$currentValue;
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$8$var50;
																						{
																							double var50 = ((((1.0 * traceTempVariable$v1$14_1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$24_3) / traceTempVariable$var49$41_1);
																							cv$temp$8$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample18Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value22);
																					}
																				}
																			}
																			int traceTempVariable$var49$42_1 = distributionTempVariable$var15$23;
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$9$var50;
																						{
																							double var50 = ((((1.0 * traceTempVariable$v1$14_1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$24_3) / traceTempVariable$var49$42_1);
																							cv$temp$9$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample18Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value22);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample18$43 = 0; index$sample18$43 < weightings.length; index$sample18$43 += 1) {
																					int distributionTempVariable$var15$45 = index$sample18$43;
																					double cv$probabilitySample18Value44 = (cv$probabilitySample18Value22 * distribution$sample18[index$sample18$43]);
																					int traceTempVariable$var49$46_1 = distributionTempVariable$var15$45;
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$10$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$14_1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$24_3) / traceTempVariable$var49$46_1);
																									cv$temp$10$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample18Value44) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value44) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value44) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value44) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value44) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var50)));
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
												}
											}
											if(fixedFlag$sample12) {
												if(fixedFlag$sample26) {
													for(int i = 0; i < size; i += 1) {
														for(int k = 0; k < (size + 1); k += 1) {
															if(((i + 1) == k)) {
																if((k == (j + 1))) {
																	int traceTempVariable$var49$64_1 = cv$currentValue;
																	if((0 == (j + 1))) {
																		{
																			{
																				double cv$temp$11$var50;
																				{
																					double var50 = ((((1.0 * v1) + traceTempVariable$var41$4_1) + v3[(j + 1)]) / traceTempVariable$var49$64_1);
																					cv$temp$11$var50 = var50;
																				}
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var50)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample18$65 = 0; index$sample18$65 < weightings.length; index$sample18$65 += 1) {
																			int distributionTempVariable$var15$67 = index$sample18$65;
																			double cv$probabilitySample18Value66 = (1.0 * distribution$sample18[index$sample18$65]);
																			int traceTempVariable$var49$68_1 = distributionTempVariable$var15$67;
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$12$var50;
																						{
																							double var50 = ((((1.0 * v1) + traceTempVariable$var41$4_1) + v3[(j + 1)]) / traceTempVariable$var49$68_1);
																							cv$temp$12$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample18Value66) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value66) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value66) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value66) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value66) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value66);
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
															for(int index$sample26$54 = 0; index$sample26$54 < weightings.length; index$sample26$54 += 1) {
																int distributionTempVariable$var23$56 = index$sample26$54;
																double cv$probabilitySample26Value55 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$54]);
																int traceTempVariable$var33$57_1 = distributionTempVariable$var23$56;
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((i + 1) == k)) {
																		int traceTempVariable$var45$57_3 = traceTempVariable$var33$57_1;
																		if((k == (j + 1))) {
																			int traceTempVariable$var49$69_1 = cv$currentValue;
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$13$var50;
																						{
																							double var50 = ((((1.0 * v1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$57_3) / traceTempVariable$var49$69_1);
																							cv$temp$13$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample26Value55) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value55) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value55) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value55) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value55) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value55);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample18$70 = 0; index$sample18$70 < weightings.length; index$sample18$70 += 1) {
																					int distributionTempVariable$var15$72 = index$sample18$70;
																					double cv$probabilitySample18Value71 = (cv$probabilitySample26Value55 * distribution$sample18[index$sample18$70]);
																					int traceTempVariable$var49$73_1 = distributionTempVariable$var15$72;
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$14$var50;
																								{
																									double var50 = ((((1.0 * v1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$57_3) / traceTempVariable$var49$73_1);
																									cv$temp$14$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample18Value71) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value71) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value71) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value71) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value71) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var50)));
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
													}
												}
											} else {
												if(true) {
													for(int index$sample12$48 = 0; index$sample12$48 < weightings.length; index$sample12$48 += 1) {
														int distributionTempVariable$v1$50 = index$sample12$48;
														double cv$probabilitySample12Value49 = (1.0 * distribution$sample12[index$sample12$48]);
														int traceTempVariable$v1$51_1 = distributionTempVariable$v1$50;
														if(fixedFlag$sample26) {
															for(int i = 0; i < size; i += 1) {
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((i + 1) == k)) {
																		if((k == (j + 1))) {
																			int traceTempVariable$var49$74_1 = cv$currentValue;
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$15$var50;
																						{
																							double var50 = ((((1.0 * traceTempVariable$v1$51_1) + traceTempVariable$var41$4_1) + v3[(j + 1)]) / traceTempVariable$var49$74_1);
																							cv$temp$15$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample12Value49) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value49) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value49) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value49) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value49) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value49);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample18$75 = 0; index$sample18$75 < weightings.length; index$sample18$75 += 1) {
																					int distributionTempVariable$var15$77 = index$sample18$75;
																					double cv$probabilitySample18Value76 = (cv$probabilitySample12Value49 * distribution$sample18[index$sample18$75]);
																					int traceTempVariable$var49$78_1 = distributionTempVariable$var15$77;
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$16$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$51_1) + traceTempVariable$var41$4_1) + v3[(j + 1)]) / traceTempVariable$var49$78_1);
																									cv$temp$16$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample18Value76) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value76) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value76) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value76) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value76) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value76);
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
																		double cv$probabilitySample26Value61 = (cv$probabilitySample12Value49 * distribution$sample26[((i - 0) / 1)][index$sample26$60]);
																		int traceTempVariable$var33$63_1 = distributionTempVariable$var23$62;
																		for(int k = 0; k < (size + 1); k += 1) {
																			if(((i + 1) == k)) {
																				int traceTempVariable$var45$63_3 = traceTempVariable$var33$63_1;
																				if((k == (j + 1))) {
																					int traceTempVariable$var49$79_1 = cv$currentValue;
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$17$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$51_1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$63_3) / traceTempVariable$var49$79_1);
																									cv$temp$17$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value61) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value61) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value61) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value61) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value61) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value61);
																							}
																						}
																					}
																					if(!true) {
																						for(int index$sample18$80 = 0; index$sample18$80 < weightings.length; index$sample18$80 += 1) {
																							int distributionTempVariable$var15$82 = index$sample18$80;
																							double cv$probabilitySample18Value81 = (cv$probabilitySample26Value61 * distribution$sample18[index$sample18$80]);
																							int traceTempVariable$var49$83_1 = distributionTempVariable$var15$82;
																							if((0 == (j + 1))) {
																								{
																									{
																										double cv$temp$18$var50;
																										{
																											double var50 = ((((1.0 * traceTempVariable$v1$51_1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$63_3) / traceTempVariable$var49$83_1);
																											cv$temp$18$var50 = var50;
																										}
																										if(((Math.log(cv$probabilitySample18Value81) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var50)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value81) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value81) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var50));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value81) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value81) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var50)));
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
												}
											}
											if(fixedFlag$sample12) {
												int traceTempVariable$var33$89_1 = cv$currentValue;
												for(int k = 0; k < (size + 1); k += 1) {
													if((0 == k)) {
														int traceTempVariable$var45$89_3 = traceTempVariable$var33$89_1;
														if((k == (j + 1))) {
															if(fixedFlag$sample26) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == (j + 1))) {
																		{
																			{
																				double cv$temp$19$var50;
																				{
																					double var50 = ((((1.0 * v1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$89_3) / v2[(j + 1)]);
																					cv$temp$19$var50 = var50;
																				}
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var50)));
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
																			int traceTempVariable$var49$104_1 = distributionTempVariable$var23$103;
																			if(((i + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$20$var50;
																						{
																							double var50 = ((((1.0 * v1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$89_3) / traceTempVariable$var49$104_1);
																							cv$temp$20$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample26Value102) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value102) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value102) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value102) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value102) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var50)));
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
													}
												}
												if(!true) {
													for(int index$sample18$90 = 0; index$sample18$90 < weightings.length; index$sample18$90 += 1) {
														int distributionTempVariable$var15$92 = index$sample18$90;
														double cv$probabilitySample18Value91 = (1.0 * distribution$sample18[index$sample18$90]);
														int traceTempVariable$var33$93_1 = distributionTempVariable$var15$92;
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var45$93_3 = traceTempVariable$var33$93_1;
																if((k == (j + 1))) {
																	if(fixedFlag$sample26) {
																		for(int i = 0; i < size; i += 1) {
																			if(((i + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$21$var50;
																						{
																							double var50 = ((((1.0 * v1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$93_3) / v2[(j + 1)]);
																							cv$temp$21$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample18Value91) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value91) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value91) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value91) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value91) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var50)));
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
																					int traceTempVariable$var49$110_1 = distributionTempVariable$var23$109;
																					if(((i + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$22$var50;
																								{
																									double var50 = ((((1.0 * v1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$93_3) / traceTempVariable$var49$110_1);
																									cv$temp$22$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var50)));
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
													}
												}
											} else {
												if(true) {
													for(int index$sample12$85 = 0; index$sample12$85 < weightings.length; index$sample12$85 += 1) {
														int distributionTempVariable$v1$87 = index$sample12$85;
														double cv$probabilitySample12Value86 = (1.0 * distribution$sample12[index$sample12$85]);
														int traceTempVariable$v1$88_1 = distributionTempVariable$v1$87;
														int traceTempVariable$var33$94_1 = cv$currentValue;
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																int traceTempVariable$var45$94_3 = traceTempVariable$var33$94_1;
																if((k == (j + 1))) {
																	if(fixedFlag$sample26) {
																		for(int i = 0; i < size; i += 1) {
																			if(((i + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$23$var50;
																						{
																							double var50 = ((((1.0 * traceTempVariable$v1$88_1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$94_3) / v2[(j + 1)]);
																							cv$temp$23$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample12Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var50)));
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
																					int traceTempVariable$var49$116_1 = distributionTempVariable$var23$115;
																					if(((i + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$24$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$88_1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$94_3) / traceTempVariable$var49$116_1);
																									cv$temp$24$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value114) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value114) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value114) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value114) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value114) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var50)));
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
															}
														}
														if(!true) {
															for(int index$sample18$95 = 0; index$sample18$95 < weightings.length; index$sample18$95 += 1) {
																int distributionTempVariable$var15$97 = index$sample18$95;
																double cv$probabilitySample18Value96 = (cv$probabilitySample12Value86 * distribution$sample18[index$sample18$95]);
																int traceTempVariable$var33$98_1 = distributionTempVariable$var15$97;
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var45$98_3 = traceTempVariable$var33$98_1;
																		if((k == (j + 1))) {
																			if(fixedFlag$sample26) {
																				for(int i = 0; i < size; i += 1) {
																					if(((i + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$25$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$88_1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$98_3) / v2[(j + 1)]);
																									cv$temp$25$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample18Value96) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value96) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value96) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value96) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value96) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var50)));
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
																							int traceTempVariable$var49$122_1 = distributionTempVariable$var23$121;
																							if(((i + 1) == (j + 1))) {
																								{
																									{
																										double cv$temp$26$var50;
																										{
																											double var50 = ((((1.0 * traceTempVariable$v1$88_1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$98_3) / traceTempVariable$var49$122_1);
																											cv$temp$26$var50 = var50;
																										}
																										if(((Math.log(cv$probabilitySample26Value120) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var50)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value120) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value120) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var50));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value120) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value120) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var50)));
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
												}
											}
											if(fixedFlag$sample12) {
												if(fixedFlag$sample26) {
													for(int i = 0; i < size; i += 1) {
														for(int k = 0; k < (size + 1); k += 1) {
															if(((i + 1) == k)) {
																if((k == (j + 1))) {
																	for(int index$i$140_1 = 0; index$i$140_1 < size; index$i$140_1 += 1) {
																		if(((index$i$140_1 + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$27$var50;
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$4_1) + v3[(j + 1)]) / v2[(j + 1)]);
																						cv$temp$27$var50 = var50;
																					}
																					if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var50)));
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
															for(int index$sample26$130 = 0; index$sample26$130 < weightings.length; index$sample26$130 += 1) {
																int distributionTempVariable$var23$132 = index$sample26$130;
																double cv$probabilitySample26Value131 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$130]);
																int traceTempVariable$var33$133_1 = distributionTempVariable$var23$132;
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((i + 1) == k)) {
																		int traceTempVariable$var45$133_3 = traceTempVariable$var33$133_1;
																		if((k == (j + 1))) {
																			int traceTempVariable$var49$141_1 = distributionTempVariable$var23$132;
																			if(((i + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$28$var50;
																						{
																							double var50 = ((((1.0 * v1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$133_3) / traceTempVariable$var49$141_1);
																							cv$temp$28$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var50)));
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
																						int traceTempVariable$var49$146_1 = distributionTempVariable$var23$145;
																						if(((index$i$142 + 1) == (j + 1))) {
																							{
																								{
																									double cv$temp$29$var50;
																									{
																										double var50 = ((((1.0 * v1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$133_3) / traceTempVariable$var49$146_1);
																										cv$temp$29$var50 = var50;
																									}
																									if(((Math.log(cv$probabilitySample26Value144) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var50)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value144) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value144) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var50));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value144) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value144) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var50)));
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
													}
												}
											} else {
												if(true) {
													for(int index$sample12$124 = 0; index$sample12$124 < weightings.length; index$sample12$124 += 1) {
														int distributionTempVariable$v1$126 = index$sample12$124;
														double cv$probabilitySample12Value125 = (1.0 * distribution$sample12[index$sample12$124]);
														int traceTempVariable$v1$127_1 = distributionTempVariable$v1$126;
														if(fixedFlag$sample26) {
															for(int i = 0; i < size; i += 1) {
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((i + 1) == k)) {
																		if((k == (j + 1))) {
																			for(int index$i$147_1 = 0; index$i$147_1 < size; index$i$147_1 += 1) {
																				if(((index$i$147_1 + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$30$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$127_1) + traceTempVariable$var41$4_1) + v3[(j + 1)]) / v2[(j + 1)]);
																								cv$temp$30$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample12Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value125);
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
																	for(int index$sample26$136 = 0; index$sample26$136 < weightings.length; index$sample26$136 += 1) {
																		int distributionTempVariable$var23$138 = index$sample26$136;
																		double cv$probabilitySample26Value137 = (cv$probabilitySample12Value125 * distribution$sample26[((i - 0) / 1)][index$sample26$136]);
																		int traceTempVariable$var33$139_1 = distributionTempVariable$var23$138;
																		for(int k = 0; k < (size + 1); k += 1) {
																			if(((i + 1) == k)) {
																				int traceTempVariable$var45$139_3 = traceTempVariable$var33$139_1;
																				if((k == (j + 1))) {
																					int traceTempVariable$var49$148_1 = distributionTempVariable$var23$138;
																					if(((i + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$31$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$127_1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$139_3) / traceTempVariable$var49$148_1);
																									cv$temp$31$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var50)));
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
																								int traceTempVariable$var49$153_1 = distributionTempVariable$var23$152;
																								if(((index$i$149 + 1) == (j + 1))) {
																									{
																										{
																											double cv$temp$32$var50;
																											{
																												double var50 = ((((1.0 * traceTempVariable$v1$127_1) + traceTempVariable$var41$4_1) + traceTempVariable$var45$139_3) / traceTempVariable$var49$153_1);
																												cv$temp$32$var50 = var50;
																											}
																											if(((Math.log(cv$probabilitySample26Value151) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var50)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value151) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value151) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var50));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value151) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value151) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var50)));
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
						int traceTempVariable$var49$5_1 = cv$currentValue;
						for(int j = 0; j < size; j += 1) {
							if((0 == (j + 1))) {
								if(!guard$sample18bernoulli54[((j - 0) / 1)]) {
									guard$sample18bernoulli54[((j - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											if(fixedFlag$sample12) {
												int traceTempVariable$var41$159_1 = cv$currentValue;
												if((0 == j)) {
													int traceTempVariable$var33$169_1 = cv$currentValue;
													for(int k = 0; k < (size + 1); k += 1) {
														if((0 == k)) {
															int traceTempVariable$var45$169_3 = traceTempVariable$var33$169_1;
															if((k == (j + 1))) {
																{
																	{
																		double cv$temp$33$var50;
																		{
																			double var50 = ((((1.0 * v1) + traceTempVariable$var41$159_1) + traceTempVariable$var45$169_3) / traceTempVariable$var49$5_1);
																			cv$temp$33$var50 = var50;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var50)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var50));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var50)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													}
													if(!true) {
														for(int index$sample18$170 = 0; index$sample18$170 < weightings.length; index$sample18$170 += 1) {
															int distributionTempVariable$var15$172 = index$sample18$170;
															double cv$probabilitySample18Value171 = (1.0 * distribution$sample18[index$sample18$170]);
															int traceTempVariable$var33$173_1 = distributionTempVariable$var15$172;
															for(int k = 0; k < (size + 1); k += 1) {
																if((0 == k)) {
																	int traceTempVariable$var45$173_3 = traceTempVariable$var33$173_1;
																	if((k == (j + 1))) {
																		{
																			{
																				double cv$temp$34$var50;
																				{
																					double var50 = ((((1.0 * v1) + traceTempVariable$var41$159_1) + traceTempVariable$var45$173_3) / traceTempVariable$var49$5_1);
																					cv$temp$34$var50 = var50;
																				}
																				if(((Math.log(cv$probabilitySample18Value171) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value171) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value171) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value171) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value171) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var50)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value171);
																			}
																		}
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
														int traceTempVariable$var41$163_1 = distributionTempVariable$var15$162;
														if((0 == j)) {
															int traceTempVariable$var33$174_1 = cv$currentValue;
															for(int k = 0; k < (size + 1); k += 1) {
																if((0 == k)) {
																	int traceTempVariable$var45$174_3 = traceTempVariable$var33$174_1;
																	if((k == (j + 1))) {
																		{
																			{
																				double cv$temp$35$var50;
																				{
																					double var50 = ((((1.0 * v1) + traceTempVariable$var41$163_1) + traceTempVariable$var45$174_3) / traceTempVariable$var49$5_1);
																					cv$temp$35$var50 = var50;
																				}
																				if(((Math.log(cv$probabilitySample18Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var50)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value161);
																			}
																		}
																	}
																}
															}
															int traceTempVariable$var33$175_1 = distributionTempVariable$var15$162;
															for(int k = 0; k < (size + 1); k += 1) {
																if((0 == k)) {
																	int traceTempVariable$var45$175_3 = traceTempVariable$var33$175_1;
																	if((k == (j + 1))) {
																		{
																			{
																				double cv$temp$36$var50;
																				{
																					double var50 = ((((1.0 * v1) + traceTempVariable$var41$163_1) + traceTempVariable$var45$175_3) / traceTempVariable$var49$5_1);
																					cv$temp$36$var50 = var50;
																				}
																				if(((Math.log(cv$probabilitySample18Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var50)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value161);
																			}
																		}
																	}
																}
															}
															if(!true) {
																for(int index$sample18$176 = 0; index$sample18$176 < weightings.length; index$sample18$176 += 1) {
																	int distributionTempVariable$var15$178 = index$sample18$176;
																	double cv$probabilitySample18Value177 = (cv$probabilitySample18Value161 * distribution$sample18[index$sample18$176]);
																	int traceTempVariable$var33$179_1 = distributionTempVariable$var15$178;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if((0 == k)) {
																			int traceTempVariable$var45$179_3 = traceTempVariable$var33$179_1;
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$37$var50;
																						{
																							double var50 = ((((1.0 * v1) + traceTempVariable$var41$163_1) + traceTempVariable$var45$179_3) / traceTempVariable$var49$5_1);
																							cv$temp$37$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample18Value177) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value177) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value177) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value177) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value177) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var50)));
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
													}
												}
											} else {
												if(true) {
													for(int index$sample12$155 = 0; index$sample12$155 < weightings.length; index$sample12$155 += 1) {
														int distributionTempVariable$v1$157 = index$sample12$155;
														double cv$probabilitySample12Value156 = (1.0 * distribution$sample12[index$sample12$155]);
														int traceTempVariable$v1$158_1 = distributionTempVariable$v1$157;
														int traceTempVariable$var41$164_1 = cv$currentValue;
														if((0 == j)) {
															int traceTempVariable$var33$180_1 = cv$currentValue;
															for(int k = 0; k < (size + 1); k += 1) {
																if((0 == k)) {
																	int traceTempVariable$var45$180_3 = traceTempVariable$var33$180_1;
																	if((k == (j + 1))) {
																		{
																			{
																				double cv$temp$38$var50;
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$158_1) + traceTempVariable$var41$164_1) + traceTempVariable$var45$180_3) / traceTempVariable$var49$5_1);
																					cv$temp$38$var50 = var50;
																				}
																				if(((Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var50)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value156);
																			}
																		}
																	}
																}
															}
															if(!true) {
																for(int index$sample18$181 = 0; index$sample18$181 < weightings.length; index$sample18$181 += 1) {
																	int distributionTempVariable$var15$183 = index$sample18$181;
																	double cv$probabilitySample18Value182 = (cv$probabilitySample12Value156 * distribution$sample18[index$sample18$181]);
																	int traceTempVariable$var33$184_1 = distributionTempVariable$var15$183;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if((0 == k)) {
																			int traceTempVariable$var45$184_3 = traceTempVariable$var33$184_1;
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$39$var50;
																						{
																							double var50 = ((((1.0 * traceTempVariable$v1$158_1) + traceTempVariable$var41$164_1) + traceTempVariable$var45$184_3) / traceTempVariable$var49$5_1);
																							cv$temp$39$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample18Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var50)));
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
														if(!true) {
															for(int index$sample18$165 = 0; index$sample18$165 < weightings.length; index$sample18$165 += 1) {
																int distributionTempVariable$var15$167 = index$sample18$165;
																double cv$probabilitySample18Value166 = (cv$probabilitySample12Value156 * distribution$sample18[index$sample18$165]);
																int traceTempVariable$var41$168_1 = distributionTempVariable$var15$167;
																if((0 == j)) {
																	int traceTempVariable$var33$185_1 = cv$currentValue;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if((0 == k)) {
																			int traceTempVariable$var45$185_3 = traceTempVariable$var33$185_1;
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$40$var50;
																						{
																							double var50 = ((((1.0 * traceTempVariable$v1$158_1) + traceTempVariable$var41$168_1) + traceTempVariable$var45$185_3) / traceTempVariable$var49$5_1);
																							cv$temp$40$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample18Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value166);
																					}
																				}
																			}
																		}
																	}
																	int traceTempVariable$var33$186_1 = distributionTempVariable$var15$167;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if((0 == k)) {
																			int traceTempVariable$var45$186_3 = traceTempVariable$var33$186_1;
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$41$var50;
																						{
																							double var50 = ((((1.0 * traceTempVariable$v1$158_1) + traceTempVariable$var41$168_1) + traceTempVariable$var45$186_3) / traceTempVariable$var49$5_1);
																							cv$temp$41$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample18Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value166);
																					}
																				}
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample18$187 = 0; index$sample18$187 < weightings.length; index$sample18$187 += 1) {
																			int distributionTempVariable$var15$189 = index$sample18$187;
																			double cv$probabilitySample18Value188 = (cv$probabilitySample18Value166 * distribution$sample18[index$sample18$187]);
																			int traceTempVariable$var33$190_1 = distributionTempVariable$var15$189;
																			for(int k = 0; k < (size + 1); k += 1) {
																				if((0 == k)) {
																					int traceTempVariable$var45$190_3 = traceTempVariable$var33$190_1;
																					if((k == (j + 1))) {
																						{
																							{
																								double cv$temp$42$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$158_1) + traceTempVariable$var41$168_1) + traceTempVariable$var45$190_3) / traceTempVariable$var49$5_1);
																									cv$temp$42$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample18Value188) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value188) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value188) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value188) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value188) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var50)));
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
												}
											}
											if(fixedFlag$sample12) {
												int traceTempVariable$var41$196_1 = cv$currentValue;
												if((0 == j)) {
													if(fixedFlag$sample26) {
														for(int i = 0; i < size; i += 1) {
															for(int k = 0; k < (size + 1); k += 1) {
																if(((i + 1) == k)) {
																	if((k == (j + 1))) {
																		{
																			{
																				double cv$temp$43$var50;
																				{
																					double var50 = ((((1.0 * v1) + traceTempVariable$var41$196_1) + v3[(j + 1)]) / traceTempVariable$var49$5_1);
																					cv$temp$43$var50 = var50;
																				}
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50)));
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
																for(int index$sample26$208 = 0; index$sample26$208 < weightings.length; index$sample26$208 += 1) {
																	int distributionTempVariable$var23$210 = index$sample26$208;
																	double cv$probabilitySample26Value209 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$208]);
																	int traceTempVariable$var33$211_1 = distributionTempVariable$var23$210;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if(((i + 1) == k)) {
																			int traceTempVariable$var45$211_3 = traceTempVariable$var33$211_1;
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$44$var50;
																						{
																							double var50 = ((((1.0 * v1) + traceTempVariable$var41$196_1) + traceTempVariable$var45$211_3) / traceTempVariable$var49$5_1);
																							cv$temp$44$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample26Value209) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value209) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value209) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value209) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value209) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value209);
																					}
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
													for(int index$sample18$197 = 0; index$sample18$197 < weightings.length; index$sample18$197 += 1) {
														int distributionTempVariable$var15$199 = index$sample18$197;
														double cv$probabilitySample18Value198 = (1.0 * distribution$sample18[index$sample18$197]);
														int traceTempVariable$var41$200_1 = distributionTempVariable$var15$199;
														if((0 == j)) {
															if(fixedFlag$sample26) {
																for(int i = 0; i < size; i += 1) {
																	for(int k = 0; k < (size + 1); k += 1) {
																		if(((i + 1) == k)) {
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$45$var50;
																						{
																							double var50 = ((((1.0 * v1) + traceTempVariable$var41$200_1) + v3[(j + 1)]) / traceTempVariable$var49$5_1);
																							cv$temp$45$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample18Value198) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value198) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value198) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value198) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value198) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value198);
																					}
																				}
																			}
																		}
																	}
																}
															} else {
																for(int i = 0; i < size; i += 1) {
																	if(true) {
																		for(int index$sample26$214 = 0; index$sample26$214 < weightings.length; index$sample26$214 += 1) {
																			int distributionTempVariable$var23$216 = index$sample26$214;
																			double cv$probabilitySample26Value215 = (cv$probabilitySample18Value198 * distribution$sample26[((i - 0) / 1)][index$sample26$214]);
																			int traceTempVariable$var33$217_1 = distributionTempVariable$var23$216;
																			for(int k = 0; k < (size + 1); k += 1) {
																				if(((i + 1) == k)) {
																					int traceTempVariable$var45$217_3 = traceTempVariable$var33$217_1;
																					if((k == (j + 1))) {
																						{
																							{
																								double cv$temp$46$var50;
																								{
																									double var50 = ((((1.0 * v1) + traceTempVariable$var41$200_1) + traceTempVariable$var45$217_3) / traceTempVariable$var49$5_1);
																									cv$temp$46$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value215) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value215) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value215) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value215) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value215) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value215);
																							}
																						}
																					}
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
														int traceTempVariable$v1$195_1 = distributionTempVariable$v1$194;
														int traceTempVariable$var41$201_1 = cv$currentValue;
														if((0 == j)) {
															if(fixedFlag$sample26) {
																for(int i = 0; i < size; i += 1) {
																	for(int k = 0; k < (size + 1); k += 1) {
																		if(((i + 1) == k)) {
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$47$var50;
																						{
																							double var50 = ((((1.0 * traceTempVariable$v1$195_1) + traceTempVariable$var41$201_1) + v3[(j + 1)]) / traceTempVariable$var49$5_1);
																							cv$temp$47$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample12Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value193);
																					}
																				}
																			}
																		}
																	}
																}
															} else {
																for(int i = 0; i < size; i += 1) {
																	if(true) {
																		for(int index$sample26$220 = 0; index$sample26$220 < weightings.length; index$sample26$220 += 1) {
																			int distributionTempVariable$var23$222 = index$sample26$220;
																			double cv$probabilitySample26Value221 = (cv$probabilitySample12Value193 * distribution$sample26[((i - 0) / 1)][index$sample26$220]);
																			int traceTempVariable$var33$223_1 = distributionTempVariable$var23$222;
																			for(int k = 0; k < (size + 1); k += 1) {
																				if(((i + 1) == k)) {
																					int traceTempVariable$var45$223_3 = traceTempVariable$var33$223_1;
																					if((k == (j + 1))) {
																						{
																							{
																								double cv$temp$48$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$195_1) + traceTempVariable$var41$201_1) + traceTempVariable$var45$223_3) / traceTempVariable$var49$5_1);
																									cv$temp$48$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value221) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value221) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value221) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value221) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value221) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value221);
																							}
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
															for(int index$sample18$202 = 0; index$sample18$202 < weightings.length; index$sample18$202 += 1) {
																int distributionTempVariable$var15$204 = index$sample18$202;
																double cv$probabilitySample18Value203 = (cv$probabilitySample12Value193 * distribution$sample18[index$sample18$202]);
																int traceTempVariable$var41$205_1 = distributionTempVariable$var15$204;
																if((0 == j)) {
																	if(fixedFlag$sample26) {
																		for(int i = 0; i < size; i += 1) {
																			for(int k = 0; k < (size + 1); k += 1) {
																				if(((i + 1) == k)) {
																					if((k == (j + 1))) {
																						{
																							{
																								double cv$temp$49$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$195_1) + traceTempVariable$var41$205_1) + v3[(j + 1)]) / traceTempVariable$var49$5_1);
																									cv$temp$49$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample18Value203) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value203) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value203) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value203) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value203) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value203);
																							}
																						}
																					}
																				}
																			}
																		}
																	} else {
																		for(int i = 0; i < size; i += 1) {
																			if(true) {
																				for(int index$sample26$226 = 0; index$sample26$226 < weightings.length; index$sample26$226 += 1) {
																					int distributionTempVariable$var23$228 = index$sample26$226;
																					double cv$probabilitySample26Value227 = (cv$probabilitySample18Value203 * distribution$sample26[((i - 0) / 1)][index$sample26$226]);
																					int traceTempVariable$var33$229_1 = distributionTempVariable$var23$228;
																					for(int k = 0; k < (size + 1); k += 1) {
																						if(((i + 1) == k)) {
																							int traceTempVariable$var45$229_3 = traceTempVariable$var33$229_1;
																							if((k == (j + 1))) {
																								{
																									{
																										double cv$temp$50$var50;
																										{
																											double var50 = ((((1.0 * traceTempVariable$v1$195_1) + traceTempVariable$var41$205_1) + traceTempVariable$var45$229_3) / traceTempVariable$var49$5_1);
																											cv$temp$50$var50 = var50;
																										}
																										if(((Math.log(cv$probabilitySample26Value227) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var50)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value227) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value227) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var50));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value227) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value227) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var50)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value227);
																									}
																								}
																							}
																						}
																					}
																				}
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
															int traceTempVariable$var33$247_1 = cv$currentValue;
															for(int k = 0; k < (size + 1); k += 1) {
																if((0 == k)) {
																	int traceTempVariable$var45$247_3 = traceTempVariable$var33$247_1;
																	if((k == (j + 1))) {
																		{
																			{
																				double cv$temp$51$var50;
																				{
																					double var50 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var45$247_3) / traceTempVariable$var49$5_1);
																					cv$temp$51$var50 = var50;
																				}
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var50)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
															if(!true) {
																for(int index$sample18$248 = 0; index$sample18$248 < weightings.length; index$sample18$248 += 1) {
																	int distributionTempVariable$var15$250 = index$sample18$248;
																	double cv$probabilitySample18Value249 = (1.0 * distribution$sample18[index$sample18$248]);
																	int traceTempVariable$var33$251_1 = distributionTempVariable$var15$250;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if((0 == k)) {
																			int traceTempVariable$var45$251_3 = traceTempVariable$var33$251_1;
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$52$var50;
																						{
																							double var50 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var45$251_3) / traceTempVariable$var49$5_1);
																							cv$temp$52$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample18Value249) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value249) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value249) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value249) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value249) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value249);
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
															for(int index$sample26$237 = 0; index$sample26$237 < weightings.length; index$sample26$237 += 1) {
																int distributionTempVariable$var23$239 = index$sample26$237;
																double cv$probabilitySample26Value238 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$237]);
																int traceTempVariable$var41$240_1 = distributionTempVariable$var23$239;
																if(((i + 1) == j)) {
																	int traceTempVariable$var33$252_1 = cv$currentValue;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if((0 == k)) {
																			int traceTempVariable$var45$252_3 = traceTempVariable$var33$252_1;
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$53$var50;
																						{
																							double var50 = ((((1.0 * v1) + traceTempVariable$var41$240_1) + traceTempVariable$var45$252_3) / traceTempVariable$var49$5_1);
																							cv$temp$53$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample26Value238) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value238) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value238) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value238) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value238) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value238);
																					}
																				}
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample18$253 = 0; index$sample18$253 < weightings.length; index$sample18$253 += 1) {
																			int distributionTempVariable$var15$255 = index$sample18$253;
																			double cv$probabilitySample18Value254 = (cv$probabilitySample26Value238 * distribution$sample18[index$sample18$253]);
																			int traceTempVariable$var33$256_1 = distributionTempVariable$var15$255;
																			for(int k = 0; k < (size + 1); k += 1) {
																				if((0 == k)) {
																					int traceTempVariable$var45$256_3 = traceTempVariable$var33$256_1;
																					if((k == (j + 1))) {
																						{
																							{
																								double cv$temp$54$var50;
																								{
																									double var50 = ((((1.0 * v1) + traceTempVariable$var41$240_1) + traceTempVariable$var45$256_3) / traceTempVariable$var49$5_1);
																									cv$temp$54$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample18Value254) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value254) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value254) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value254) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value254) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value254);
																							}
																						}
																					}
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
													for(int index$sample12$231 = 0; index$sample12$231 < weightings.length; index$sample12$231 += 1) {
														int distributionTempVariable$v1$233 = index$sample12$231;
														double cv$probabilitySample12Value232 = (1.0 * distribution$sample12[index$sample12$231]);
														int traceTempVariable$v1$234_1 = distributionTempVariable$v1$233;
														if(fixedFlag$sample26) {
															for(int i = 0; i < size; i += 1) {
																if(((i + 1) == j)) {
																	int traceTempVariable$var33$257_1 = cv$currentValue;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if((0 == k)) {
																			int traceTempVariable$var45$257_3 = traceTempVariable$var33$257_1;
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$55$var50;
																						{
																							double var50 = ((((1.0 * traceTempVariable$v1$234_1) + v2[j]) + traceTempVariable$var45$257_3) / traceTempVariable$var49$5_1);
																							cv$temp$55$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample12Value232) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value232) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value232) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value232) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value232) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value232);
																					}
																				}
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample18$258 = 0; index$sample18$258 < weightings.length; index$sample18$258 += 1) {
																			int distributionTempVariable$var15$260 = index$sample18$258;
																			double cv$probabilitySample18Value259 = (cv$probabilitySample12Value232 * distribution$sample18[index$sample18$258]);
																			int traceTempVariable$var33$261_1 = distributionTempVariable$var15$260;
																			for(int k = 0; k < (size + 1); k += 1) {
																				if((0 == k)) {
																					int traceTempVariable$var45$261_3 = traceTempVariable$var33$261_1;
																					if((k == (j + 1))) {
																						{
																							{
																								double cv$temp$56$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$234_1) + v2[j]) + traceTempVariable$var45$261_3) / traceTempVariable$var49$5_1);
																									cv$temp$56$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample18Value259) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value259) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value259) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value259) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value259) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value259);
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
																	for(int index$sample26$243 = 0; index$sample26$243 < weightings.length; index$sample26$243 += 1) {
																		int distributionTempVariable$var23$245 = index$sample26$243;
																		double cv$probabilitySample26Value244 = (cv$probabilitySample12Value232 * distribution$sample26[((i - 0) / 1)][index$sample26$243]);
																		int traceTempVariable$var41$246_1 = distributionTempVariable$var23$245;
																		if(((i + 1) == j)) {
																			int traceTempVariable$var33$262_1 = cv$currentValue;
																			for(int k = 0; k < (size + 1); k += 1) {
																				if((0 == k)) {
																					int traceTempVariable$var45$262_3 = traceTempVariable$var33$262_1;
																					if((k == (j + 1))) {
																						{
																							{
																								double cv$temp$57$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$234_1) + traceTempVariable$var41$246_1) + traceTempVariable$var45$262_3) / traceTempVariable$var49$5_1);
																									cv$temp$57$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value244) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value244) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value244) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value244) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value244) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value244);
																							}
																						}
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample18$263 = 0; index$sample18$263 < weightings.length; index$sample18$263 += 1) {
																					int distributionTempVariable$var15$265 = index$sample18$263;
																					double cv$probabilitySample18Value264 = (cv$probabilitySample26Value244 * distribution$sample18[index$sample18$263]);
																					int traceTempVariable$var33$266_1 = distributionTempVariable$var15$265;
																					for(int k = 0; k < (size + 1); k += 1) {
																						if((0 == k)) {
																							int traceTempVariable$var45$266_3 = traceTempVariable$var33$266_1;
																							if((k == (j + 1))) {
																								{
																									{
																										double cv$temp$58$var50;
																										{
																											double var50 = ((((1.0 * traceTempVariable$v1$234_1) + traceTempVariable$var41$246_1) + traceTempVariable$var45$266_3) / traceTempVariable$var49$5_1);
																											cv$temp$58$var50 = var50;
																										}
																										if(((Math.log(cv$probabilitySample18Value264) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var50)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value264) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value264) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var50));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value264) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value264) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var50)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value264);
																									}
																								}
																							}
																						}
																					}
																				}
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
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((index$i$284_1 + 1) == k)) {
																		if((k == (j + 1))) {
																			{
																				{
																					double cv$temp$59$var50;
																					{
																						double var50 = ((((1.0 * v1) + v2[j]) + v3[(j + 1)]) / traceTempVariable$var49$5_1);
																						cv$temp$59$var50 = var50;
																					}
																					if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var50)));
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
															for(int index$sample26$274 = 0; index$sample26$274 < weightings.length; index$sample26$274 += 1) {
																int distributionTempVariable$var23$276 = index$sample26$274;
																double cv$probabilitySample26Value275 = (1.0 * distribution$sample26[((i - 0) / 1)][index$sample26$274]);
																int traceTempVariable$var41$277_1 = distributionTempVariable$var23$276;
																if(((i + 1) == j)) {
																	int traceTempVariable$var33$285_1 = distributionTempVariable$var23$276;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if(((i + 1) == k)) {
																			int traceTempVariable$var45$285_3 = traceTempVariable$var33$285_1;
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$60$var50;
																						{
																							double var50 = ((((1.0 * v1) + traceTempVariable$var41$277_1) + traceTempVariable$var45$285_3) / traceTempVariable$var49$5_1);
																							cv$temp$60$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value275);
																					}
																				}
																			}
																		}
																	}
																	for(int index$i$286 = 0; index$i$286 < size; index$i$286 += 1) {
																		if(!(index$i$286 == i)) {
																			for(int index$sample26$287 = 0; index$sample26$287 < weightings.length; index$sample26$287 += 1) {
																				int distributionTempVariable$var23$289 = index$sample26$287;
																				double cv$probabilitySample26Value288 = (cv$probabilitySample26Value275 * distribution$sample26[((index$i$286 - 0) / 1)][index$sample26$287]);
																				int traceTempVariable$var33$290_1 = distributionTempVariable$var23$289;
																				for(int k = 0; k < (size + 1); k += 1) {
																					if(((index$i$286 + 1) == k)) {
																						int traceTempVariable$var45$290_3 = traceTempVariable$var33$290_1;
																						if((k == (j + 1))) {
																							{
																								{
																									double cv$temp$61$var50;
																									{
																										double var50 = ((((1.0 * v1) + traceTempVariable$var41$277_1) + traceTempVariable$var45$290_3) / traceTempVariable$var49$5_1);
																										cv$temp$61$var50 = var50;
																									}
																									if(((Math.log(cv$probabilitySample26Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var50)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var50));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var50)));
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
													}
												}
											} else {
												if(true) {
													for(int index$sample12$268 = 0; index$sample12$268 < weightings.length; index$sample12$268 += 1) {
														int distributionTempVariable$v1$270 = index$sample12$268;
														double cv$probabilitySample12Value269 = (1.0 * distribution$sample12[index$sample12$268]);
														int traceTempVariable$v1$271_1 = distributionTempVariable$v1$270;
														if(fixedFlag$sample26) {
															for(int i = 0; i < size; i += 1) {
																if(((i + 1) == j)) {
																	for(int index$i$291_1 = 0; index$i$291_1 < size; index$i$291_1 += 1) {
																		for(int k = 0; k < (size + 1); k += 1) {
																			if(((index$i$291_1 + 1) == k)) {
																				if((k == (j + 1))) {
																					{
																						{
																							double cv$temp$62$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$271_1) + v2[j]) + v3[(j + 1)]) / traceTempVariable$var49$5_1);
																								cv$temp$62$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample12Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value269);
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
																	for(int index$sample26$280 = 0; index$sample26$280 < weightings.length; index$sample26$280 += 1) {
																		int distributionTempVariable$var23$282 = index$sample26$280;
																		double cv$probabilitySample26Value281 = (cv$probabilitySample12Value269 * distribution$sample26[((i - 0) / 1)][index$sample26$280]);
																		int traceTempVariable$var41$283_1 = distributionTempVariable$var23$282;
																		if(((i + 1) == j)) {
																			int traceTempVariable$var33$292_1 = distributionTempVariable$var23$282;
																			for(int k = 0; k < (size + 1); k += 1) {
																				if(((i + 1) == k)) {
																					int traceTempVariable$var45$292_3 = traceTempVariable$var33$292_1;
																					if((k == (j + 1))) {
																						{
																							{
																								double cv$temp$63$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$271_1) + traceTempVariable$var41$283_1) + traceTempVariable$var45$292_3) / traceTempVariable$var49$5_1);
																									cv$temp$63$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value281);
																							}
																						}
																					}
																				}
																			}
																			for(int index$i$293 = 0; index$i$293 < size; index$i$293 += 1) {
																				if(!(index$i$293 == i)) {
																					for(int index$sample26$294 = 0; index$sample26$294 < weightings.length; index$sample26$294 += 1) {
																						int distributionTempVariable$var23$296 = index$sample26$294;
																						double cv$probabilitySample26Value295 = (cv$probabilitySample26Value281 * distribution$sample26[((index$i$293 - 0) / 1)][index$sample26$294]);
																						int traceTempVariable$var33$297_1 = distributionTempVariable$var23$296;
																						for(int k = 0; k < (size + 1); k += 1) {
																							if(((index$i$293 + 1) == k)) {
																								int traceTempVariable$var45$297_3 = traceTempVariable$var33$297_1;
																								if((k == (j + 1))) {
																									{
																										{
																											double cv$temp$64$var50;
																											{
																												double var50 = ((((1.0 * traceTempVariable$v1$271_1) + traceTempVariable$var41$283_1) + traceTempVariable$var45$297_3) / traceTempVariable$var49$5_1);
																												cv$temp$64$var50 = var50;
																											}
																											if(((Math.log(cv$probabilitySample26Value295) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var50)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value295) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value295) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var50));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value295) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value295) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var50)));
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
						int traceTempVariable$var33$6_1 = cv$currentValue;
						for(int k = 0; k < (size + 1); k += 1) {
							if((0 == k)) {
								int traceTempVariable$var45$6_3 = traceTempVariable$var33$6_1;
								for(int j = 0; j < size; j += 1) {
									if((k == (j + 1))) {
										if(!guard$sample18bernoulli54[((j - 0) / 1)]) {
											guard$sample18bernoulli54[((j - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample12) {
														int traceTempVariable$var41$303_1 = cv$currentValue;
														if((0 == j)) {
															int traceTempVariable$var49$313_1 = cv$currentValue;
															if((0 == (j + 1))) {
																{
																	{
																		double cv$temp$65$var50;
																		{
																			double var50 = ((((1.0 * v1) + traceTempVariable$var41$303_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$313_1);
																			cv$temp$65$var50 = var50;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var50)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var50));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var50)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
															if(!true) {
																for(int index$sample18$314 = 0; index$sample18$314 < weightings.length; index$sample18$314 += 1) {
																	int distributionTempVariable$var15$316 = index$sample18$314;
																	double cv$probabilitySample18Value315 = (1.0 * distribution$sample18[index$sample18$314]);
																	int traceTempVariable$var49$317_1 = distributionTempVariable$var15$316;
																	if((0 == (j + 1))) {
																		{
																			{
																				double cv$temp$66$var50;
																				{
																					double var50 = ((((1.0 * v1) + traceTempVariable$var41$303_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$317_1);
																					cv$temp$66$var50 = var50;
																				}
																				if(((Math.log(cv$probabilitySample18Value315) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value315) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value315) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value315) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value315) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var50)));
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
																int traceTempVariable$var41$307_1 = distributionTempVariable$var15$306;
																if((0 == j)) {
																	int traceTempVariable$var49$318_1 = cv$currentValue;
																	if((0 == (j + 1))) {
																		{
																			{
																				double cv$temp$67$var50;
																				{
																					double var50 = ((((1.0 * v1) + traceTempVariable$var41$307_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$318_1);
																					cv$temp$67$var50 = var50;
																				}
																				if(((Math.log(cv$probabilitySample18Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var50)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value305);
																			}
																		}
																	}
																	int traceTempVariable$var49$319_1 = distributionTempVariable$var15$306;
																	if((0 == (j + 1))) {
																		{
																			{
																				double cv$temp$68$var50;
																				{
																					double var50 = ((((1.0 * v1) + traceTempVariable$var41$307_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$319_1);
																					cv$temp$68$var50 = var50;
																				}
																				if(((Math.log(cv$probabilitySample18Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var50)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value305);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample18$320 = 0; index$sample18$320 < weightings.length; index$sample18$320 += 1) {
																			int distributionTempVariable$var15$322 = index$sample18$320;
																			double cv$probabilitySample18Value321 = (cv$probabilitySample18Value305 * distribution$sample18[index$sample18$320]);
																			int traceTempVariable$var49$323_1 = distributionTempVariable$var15$322;
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$69$var50;
																						{
																							double var50 = ((((1.0 * v1) + traceTempVariable$var41$307_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$323_1);
																							cv$temp$69$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample18Value321) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value321) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value321) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value321) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value321) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var50)));
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
																int traceTempVariable$v1$302_1 = distributionTempVariable$v1$301;
																int traceTempVariable$var41$308_1 = cv$currentValue;
																if((0 == j)) {
																	int traceTempVariable$var49$324_1 = cv$currentValue;
																	if((0 == (j + 1))) {
																		{
																			{
																				double cv$temp$70$var50;
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$302_1) + traceTempVariable$var41$308_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$324_1);
																					cv$temp$70$var50 = var50;
																				}
																				if(((Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var50)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value300);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample18$325 = 0; index$sample18$325 < weightings.length; index$sample18$325 += 1) {
																			int distributionTempVariable$var15$327 = index$sample18$325;
																			double cv$probabilitySample18Value326 = (cv$probabilitySample12Value300 * distribution$sample18[index$sample18$325]);
																			int traceTempVariable$var49$328_1 = distributionTempVariable$var15$327;
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$71$var50;
																						{
																							double var50 = ((((1.0 * traceTempVariable$v1$302_1) + traceTempVariable$var41$308_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$328_1);
																							cv$temp$71$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample18Value326) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value326) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value326) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value326) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value326) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var50)));
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
																		int traceTempVariable$var41$312_1 = distributionTempVariable$var15$311;
																		if((0 == j)) {
																			int traceTempVariable$var49$329_1 = cv$currentValue;
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$72$var50;
																						{
																							double var50 = ((((1.0 * traceTempVariable$v1$302_1) + traceTempVariable$var41$312_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$329_1);
																							cv$temp$72$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value310);
																					}
																				}
																			}
																			int traceTempVariable$var49$330_1 = distributionTempVariable$var15$311;
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$73$var50;
																						{
																							double var50 = ((((1.0 * traceTempVariable$v1$302_1) + traceTempVariable$var41$312_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$330_1);
																							cv$temp$73$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value310);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample18$331 = 0; index$sample18$331 < weightings.length; index$sample18$331 += 1) {
																					int distributionTempVariable$var15$333 = index$sample18$331;
																					double cv$probabilitySample18Value332 = (cv$probabilitySample18Value310 * distribution$sample18[index$sample18$331]);
																					int traceTempVariable$var49$334_1 = distributionTempVariable$var15$333;
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$74$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$302_1) + traceTempVariable$var41$312_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$334_1);
																									cv$temp$74$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample18Value332) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value332) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value332) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value332) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value332) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var50)));
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
																	int traceTempVariable$var49$352_1 = cv$currentValue;
																	if((0 == (j + 1))) {
																		{
																			{
																				double cv$temp$75$var50;
																				{
																					double var50 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var45$6_3) / traceTempVariable$var49$352_1);
																					cv$temp$75$var50 = var50;
																				}
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var50)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample18$353 = 0; index$sample18$353 < weightings.length; index$sample18$353 += 1) {
																			int distributionTempVariable$var15$355 = index$sample18$353;
																			double cv$probabilitySample18Value354 = (1.0 * distribution$sample18[index$sample18$353]);
																			int traceTempVariable$var49$356_1 = distributionTempVariable$var15$355;
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$76$var50;
																						{
																							double var50 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var45$6_3) / traceTempVariable$var49$356_1);
																							cv$temp$76$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample18Value354) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value354) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value354) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value354) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value354) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var50)));
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
																		int traceTempVariable$var41$345_1 = distributionTempVariable$var23$344;
																		if(((i + 1) == j)) {
																			int traceTempVariable$var49$357_1 = cv$currentValue;
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$77$var50;
																						{
																							double var50 = ((((1.0 * v1) + traceTempVariable$var41$345_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$357_1);
																							cv$temp$77$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample26Value343) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value343) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value343) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value343) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value343) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value343);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample18$358 = 0; index$sample18$358 < weightings.length; index$sample18$358 += 1) {
																					int distributionTempVariable$var15$360 = index$sample18$358;
																					double cv$probabilitySample18Value359 = (cv$probabilitySample26Value343 * distribution$sample18[index$sample18$358]);
																					int traceTempVariable$var49$361_1 = distributionTempVariable$var15$360;
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$78$var50;
																								{
																									double var50 = ((((1.0 * v1) + traceTempVariable$var41$345_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$361_1);
																									cv$temp$78$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample18Value359) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value359) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value359) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value359) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value359) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var50)));
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
																int traceTempVariable$v1$339_1 = distributionTempVariable$v1$338;
																if(fixedFlag$sample26) {
																	for(int i = 0; i < size; i += 1) {
																		if(((i + 1) == j)) {
																			int traceTempVariable$var49$362_1 = cv$currentValue;
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$79$var50;
																						{
																							double var50 = ((((1.0 * traceTempVariable$v1$339_1) + v2[j]) + traceTempVariable$var45$6_3) / traceTempVariable$var49$362_1);
																							cv$temp$79$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample12Value337) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value337) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value337) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value337) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value337) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value337);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample18$363 = 0; index$sample18$363 < weightings.length; index$sample18$363 += 1) {
																					int distributionTempVariable$var15$365 = index$sample18$363;
																					double cv$probabilitySample18Value364 = (cv$probabilitySample12Value337 * distribution$sample18[index$sample18$363]);
																					int traceTempVariable$var49$366_1 = distributionTempVariable$var15$365;
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$80$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$339_1) + v2[j]) + traceTempVariable$var45$6_3) / traceTempVariable$var49$366_1);
																									cv$temp$80$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample18Value364) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value364) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value364) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value364) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value364) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var50)));
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
																				int traceTempVariable$var41$351_1 = distributionTempVariable$var23$350;
																				if(((i + 1) == j)) {
																					int traceTempVariable$var49$367_1 = cv$currentValue;
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$81$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$339_1) + traceTempVariable$var41$351_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$367_1);
																									cv$temp$81$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value349) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value349) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value349) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value349) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value349) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value349);
																							}
																						}
																					}
																					if(!true) {
																						for(int index$sample18$368 = 0; index$sample18$368 < weightings.length; index$sample18$368 += 1) {
																							int distributionTempVariable$var15$370 = index$sample18$368;
																							double cv$probabilitySample18Value369 = (cv$probabilitySample26Value349 * distribution$sample18[index$sample18$368]);
																							int traceTempVariable$var49$371_1 = distributionTempVariable$var15$370;
																							if((0 == (j + 1))) {
																								{
																									{
																										double cv$temp$82$var50;
																										{
																											double var50 = ((((1.0 * traceTempVariable$v1$339_1) + traceTempVariable$var41$351_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$371_1);
																											cv$temp$82$var50 = var50;
																										}
																										if(((Math.log(cv$probabilitySample18Value369) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var50)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value369) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value369) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var50));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value369) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value369) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var50)));
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
														int traceTempVariable$var41$377_1 = cv$currentValue;
														if((0 == j)) {
															if(fixedFlag$sample26) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == (j + 1))) {
																		{
																			{
																				double cv$temp$83$var50;
																				{
																					double var50 = ((((1.0 * v1) + traceTempVariable$var41$377_1) + traceTempVariable$var45$6_3) / v2[(j + 1)]);
																					cv$temp$83$var50 = var50;
																				}
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var50)));
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
																			int traceTempVariable$var49$392_1 = distributionTempVariable$var23$391;
																			if(((i + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$84$var50;
																						{
																							double var50 = ((((1.0 * v1) + traceTempVariable$var41$377_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$392_1);
																							cv$temp$84$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample26Value390) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value390) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value390) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value390) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value390) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var50)));
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
																int traceTempVariable$var41$381_1 = distributionTempVariable$var15$380;
																if((0 == j)) {
																	if(fixedFlag$sample26) {
																		for(int i = 0; i < size; i += 1) {
																			if(((i + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$85$var50;
																						{
																							double var50 = ((((1.0 * v1) + traceTempVariable$var41$381_1) + traceTempVariable$var45$6_3) / v2[(j + 1)]);
																							cv$temp$85$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample18Value379) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value379) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value379) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value379) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value379) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var50)));
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
																					int traceTempVariable$var49$398_1 = distributionTempVariable$var23$397;
																					if(((i + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$86$var50;
																								{
																									double var50 = ((((1.0 * v1) + traceTempVariable$var41$381_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$398_1);
																									cv$temp$86$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value396) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value396) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value396) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value396) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value396) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var50)));
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
																int traceTempVariable$v1$376_1 = distributionTempVariable$v1$375;
																int traceTempVariable$var41$382_1 = cv$currentValue;
																if((0 == j)) {
																	if(fixedFlag$sample26) {
																		for(int i = 0; i < size; i += 1) {
																			if(((i + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$87$var50;
																						{
																							double var50 = ((((1.0 * traceTempVariable$v1$376_1) + traceTempVariable$var41$382_1) + traceTempVariable$var45$6_3) / v2[(j + 1)]);
																							cv$temp$87$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample12Value374) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value374) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value374) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value374) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value374) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var50)));
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
																					int traceTempVariable$var49$404_1 = distributionTempVariable$var23$403;
																					if(((i + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$88$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$376_1) + traceTempVariable$var41$382_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$404_1);
																									cv$temp$88$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value402) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value402) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value402) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value402) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value402) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var50)));
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
																		int traceTempVariable$var41$386_1 = distributionTempVariable$var15$385;
																		if((0 == j)) {
																			if(fixedFlag$sample26) {
																				for(int i = 0; i < size; i += 1) {
																					if(((i + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$89$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$376_1) + traceTempVariable$var41$386_1) + traceTempVariable$var45$6_3) / v2[(j + 1)]);
																									cv$temp$89$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample18Value384) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value384) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value384) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value384) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value384) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var50)));
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
																							int traceTempVariable$var49$410_1 = distributionTempVariable$var23$409;
																							if(((i + 1) == (j + 1))) {
																								{
																									{
																										double cv$temp$90$var50;
																										{
																											double var50 = ((((1.0 * traceTempVariable$v1$376_1) + traceTempVariable$var41$386_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$410_1);
																											cv$temp$90$var50 = var50;
																										}
																										if(((Math.log(cv$probabilitySample26Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var50)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var50));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var50)));
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
																		if(((index$i$428_1 + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$91$var50;
																					{
																						double var50 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var45$6_3) / v2[(j + 1)]);
																						cv$temp$91$var50 = var50;
																					}
																					if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var50)));
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
																		int traceTempVariable$var41$421_1 = distributionTempVariable$var23$420;
																		if(((i + 1) == j)) {
																			int traceTempVariable$var49$429_1 = distributionTempVariable$var23$420;
																			if(((i + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$92$var50;
																						{
																							double var50 = ((((1.0 * v1) + traceTempVariable$var41$421_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$429_1);
																							cv$temp$92$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample26Value419) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value419) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value419) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value419) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value419) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var50)));
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
																						int traceTempVariable$var49$434_1 = distributionTempVariable$var23$433;
																						if(((index$i$430 + 1) == (j + 1))) {
																							{
																								{
																									double cv$temp$93$var50;
																									{
																										double var50 = ((((1.0 * v1) + traceTempVariable$var41$421_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$434_1);
																										cv$temp$93$var50 = var50;
																									}
																									if(((Math.log(cv$probabilitySample26Value432) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var50)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value432) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value432) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var50));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value432) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value432) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var50)));
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
																int traceTempVariable$v1$415_1 = distributionTempVariable$v1$414;
																if(fixedFlag$sample26) {
																	for(int i = 0; i < size; i += 1) {
																		if(((i + 1) == j)) {
																			for(int index$i$435_1 = 0; index$i$435_1 < size; index$i$435_1 += 1) {
																				if(((index$i$435_1 + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$94$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$415_1) + v2[j]) + traceTempVariable$var45$6_3) / v2[(j + 1)]);
																								cv$temp$94$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample12Value413) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value413) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value413) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value413) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value413) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var50)));
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
																				int traceTempVariable$var41$427_1 = distributionTempVariable$var23$426;
																				if(((i + 1) == j)) {
																					int traceTempVariable$var49$436_1 = distributionTempVariable$var23$426;
																					if(((i + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$95$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$415_1) + traceTempVariable$var41$427_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$436_1);
																									cv$temp$95$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value425) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value425) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value425) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value425) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value425) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var50)));
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
																								int traceTempVariable$var49$441_1 = distributionTempVariable$var23$440;
																								if(((index$i$437 + 1) == (j + 1))) {
																									{
																										{
																											double cv$temp$96$var50;
																											{
																												double var50 = ((((1.0 * traceTempVariable$v1$415_1) + traceTempVariable$var41$427_1) + traceTempVariable$var45$6_3) / traceTempVariable$var49$441_1);
																												cv$temp$96$var50 = var50;
																											}
																											if(((Math.log(cv$probabilitySample26Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var50)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var50));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var50)));
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

	private final void sample26(int i) {
		double[] cv$stateProbabilityLocal = cv$var23$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < weightings.length; cv$valuePos += 1) {
			int index$i$1 = i;
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
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$currentValue, cv$temp$0$weightings));
				{
					{
						boolean[] guard$sample26bernoulli54 = guard$sample26bernoulli54$global;
						for(int j = 0; j < size; j += 1) {
							if(((i + 1) == j))
								guard$sample26bernoulli54[((j - 0) / 1)] = false;
						}
						for(int j = 0; j < size; j += 1) {
							if(((i + 1) == (j + 1)))
								guard$sample26bernoulli54[((j - 0) / 1)] = false;
						}
						for(int k = 0; k < (size + 1); k += 1) {
							if(((i + 1) == k)) {
								for(int j = 0; j < size; j += 1) {
									if((k == (j + 1)))
										guard$sample26bernoulli54[((j - 0) / 1)] = false;
								}
							}
						}
						int traceTempVariable$var41$5_1 = cv$currentValue;
						for(int j = 0; j < size; j += 1) {
							if(((i + 1) == j)) {
								if(!guard$sample26bernoulli54[((j - 0) / 1)]) {
									guard$sample26bernoulli54[((j - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											if(fixedFlag$sample12) {
												if(fixedFlag$sample18) {
													for(int k = 0; k < (size + 1); k += 1) {
														if((0 == k)) {
															if((k == (j + 1))) {
																if((0 == (j + 1))) {
																	{
																		{
																			double cv$temp$1$var50;
																			{
																				double var50 = ((((1.0 * v1) + traceTempVariable$var41$5_1) + v3[(j + 1)]) / v2[(j + 1)]);
																				cv$temp$1$var50 = var50;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var50)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var50));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var50)));
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
														for(int index$sample18$17 = 0; index$sample18$17 < weightings.length; index$sample18$17 += 1) {
															int distributionTempVariable$var15$19 = index$sample18$17;
															double cv$probabilitySample18Value18 = (1.0 * distribution$sample18[index$sample18$17]);
															int traceTempVariable$var33$20_1 = distributionTempVariable$var15$19;
															for(int k = 0; k < (size + 1); k += 1) {
																if((0 == k)) {
																	int traceTempVariable$var45$20_3 = traceTempVariable$var33$20_1;
																	if((k == (j + 1))) {
																		int traceTempVariable$var49$27_1 = distributionTempVariable$var15$19;
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$2$var50;
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$20_3) / traceTempVariable$var49$27_1);
																						cv$temp$2$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample18Value18) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value18) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value18) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value18) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value18) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value18);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample18$28 = 0; index$sample18$28 < weightings.length; index$sample18$28 += 1) {
																				int distributionTempVariable$var15$30 = index$sample18$28;
																				double cv$probabilitySample18Value29 = (cv$probabilitySample18Value18 * distribution$sample18[index$sample18$28]);
																				int traceTempVariable$var49$31_1 = distributionTempVariable$var15$30;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$3$var50;
																							{
																								double var50 = ((((1.0 * v1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$20_3) / traceTempVariable$var49$31_1);
																								cv$temp$3$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample18Value29) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value29) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value29) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value29) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value29) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value29);
																						}
																					}
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
													for(int index$sample12$12 = 0; index$sample12$12 < weightings.length; index$sample12$12 += 1) {
														int distributionTempVariable$v1$14 = index$sample12$12;
														double cv$probabilitySample12Value13 = (1.0 * distribution$sample12[index$sample12$12]);
														int traceTempVariable$v1$15_1 = distributionTempVariable$v1$14;
														if(fixedFlag$sample18) {
															for(int k = 0; k < (size + 1); k += 1) {
																if((0 == k)) {
																	if((k == (j + 1))) {
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$4$var50;
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$15_1) + traceTempVariable$var41$5_1) + v3[(j + 1)]) / v2[(j + 1)]);
																						cv$temp$4$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample12Value13) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value13) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value13) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value13) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value13) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value13);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															if(true) {
																for(int index$sample18$22 = 0; index$sample18$22 < weightings.length; index$sample18$22 += 1) {
																	int distributionTempVariable$var15$24 = index$sample18$22;
																	double cv$probabilitySample18Value23 = (cv$probabilitySample12Value13 * distribution$sample18[index$sample18$22]);
																	int traceTempVariable$var33$25_1 = distributionTempVariable$var15$24;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if((0 == k)) {
																			int traceTempVariable$var45$25_3 = traceTempVariable$var33$25_1;
																			if((k == (j + 1))) {
																				int traceTempVariable$var49$33_1 = distributionTempVariable$var15$24;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$5$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$15_1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$25_3) / traceTempVariable$var49$33_1);
																								cv$temp$5$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample18Value23) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value23) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value23) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value23) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value23) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value23);
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample18$34 = 0; index$sample18$34 < weightings.length; index$sample18$34 += 1) {
																						int distributionTempVariable$var15$36 = index$sample18$34;
																						double cv$probabilitySample18Value35 = (cv$probabilitySample18Value23 * distribution$sample18[index$sample18$34]);
																						int traceTempVariable$var49$37_1 = distributionTempVariable$var15$36;
																						if((0 == (j + 1))) {
																							{
																								{
																									double cv$temp$6$var50;
																									{
																										double var50 = ((((1.0 * traceTempVariable$v1$15_1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$25_3) / traceTempVariable$var49$37_1);
																										cv$temp$6$var50 = var50;
																									}
																									if(((Math.log(cv$probabilitySample18Value35) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var50)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value35) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value35) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var50));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value35) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value35) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var50)));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value35);
																								}
																							}
																						}
																					}
																				}
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
												int traceTempVariable$var33$43_1 = cv$currentValue;
												for(int k = 0; k < (size + 1); k += 1) {
													if(((index$i$1 + 1) == k)) {
														int traceTempVariable$var45$43_3 = traceTempVariable$var33$43_1;
														if((k == (j + 1))) {
															if(fixedFlag$sample18) {
																if((0 == (j + 1))) {
																	{
																		{
																			double cv$temp$7$var50;
																			{
																				double var50 = ((((1.0 * v1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$43_3) / v2[(j + 1)]);
																				cv$temp$7$var50 = var50;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var50)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var50));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var50)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample18$56 = 0; index$sample18$56 < weightings.length; index$sample18$56 += 1) {
																		int distributionTempVariable$var15$58 = index$sample18$56;
																		double cv$probabilitySample18Value57 = (1.0 * distribution$sample18[index$sample18$56]);
																		int traceTempVariable$var49$59_1 = distributionTempVariable$var15$58;
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$8$var50;
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$43_3) / traceTempVariable$var49$59_1);
																						cv$temp$8$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample18Value57) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value57) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value57) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value57) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value57) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value57);
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
														for(int index$sample26$45 = 0; index$sample26$45 < weightings.length; index$sample26$45 += 1) {
															int distributionTempVariable$var23$47 = index$sample26$45;
															double cv$probabilitySample26Value46 = (1.0 * distribution$sample26[((index$i$44 - 0) / 1)][index$sample26$45]);
															int traceTempVariable$var33$48_1 = distributionTempVariable$var23$47;
															for(int k = 0; k < (size + 1); k += 1) {
																if(((index$i$44 + 1) == k)) {
																	int traceTempVariable$var45$48_3 = traceTempVariable$var33$48_1;
																	if((k == (j + 1))) {
																		if(fixedFlag$sample18) {
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$9$var50;
																						{
																							double var50 = ((((1.0 * v1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$48_3) / v2[(j + 1)]);
																							cv$temp$9$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample26Value46) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value46) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value46) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value46) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value46) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value46);
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample18$61 = 0; index$sample18$61 < weightings.length; index$sample18$61 += 1) {
																					int distributionTempVariable$var15$63 = index$sample18$61;
																					double cv$probabilitySample18Value62 = (cv$probabilitySample26Value46 * distribution$sample18[index$sample18$61]);
																					int traceTempVariable$var49$64_1 = distributionTempVariable$var15$63;
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$10$var50;
																								{
																									double var50 = ((((1.0 * v1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$48_3) / traceTempVariable$var49$64_1);
																									cv$temp$10$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample18Value62) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value62) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value62) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value62) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value62) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value62);
																							}
																						}
																					}
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
													for(int index$sample12$39 = 0; index$sample12$39 < weightings.length; index$sample12$39 += 1) {
														int distributionTempVariable$v1$41 = index$sample12$39;
														double cv$probabilitySample12Value40 = (1.0 * distribution$sample12[index$sample12$39]);
														int traceTempVariable$v1$42_1 = distributionTempVariable$v1$41;
														int traceTempVariable$var33$49_1 = cv$currentValue;
														for(int k = 0; k < (size + 1); k += 1) {
															if(((index$i$1 + 1) == k)) {
																int traceTempVariable$var45$49_3 = traceTempVariable$var33$49_1;
																if((k == (j + 1))) {
																	if(fixedFlag$sample18) {
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$11$var50;
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$42_1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$49_3) / v2[(j + 1)]);
																						cv$temp$11$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample12Value40) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value40) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value40) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value40) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value40) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value40);
																				}
																			}
																		}
																	} else {
																		if(true) {
																			for(int index$sample18$66 = 0; index$sample18$66 < weightings.length; index$sample18$66 += 1) {
																				int distributionTempVariable$var15$68 = index$sample18$66;
																				double cv$probabilitySample18Value67 = (cv$probabilitySample12Value40 * distribution$sample18[index$sample18$66]);
																				int traceTempVariable$var49$69_1 = distributionTempVariable$var15$68;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$12$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$42_1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$49_3) / traceTempVariable$var49$69_1);
																								cv$temp$12$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample18Value67) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value67) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value67) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value67) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value67) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value67);
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
																for(int index$sample26$51 = 0; index$sample26$51 < weightings.length; index$sample26$51 += 1) {
																	int distributionTempVariable$var23$53 = index$sample26$51;
																	double cv$probabilitySample26Value52 = (cv$probabilitySample12Value40 * distribution$sample26[((index$i$50 - 0) / 1)][index$sample26$51]);
																	int traceTempVariable$var33$54_1 = distributionTempVariable$var23$53;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if(((index$i$50 + 1) == k)) {
																			int traceTempVariable$var45$54_3 = traceTempVariable$var33$54_1;
																			if((k == (j + 1))) {
																				if(fixedFlag$sample18) {
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$13$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$42_1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$54_3) / v2[(j + 1)]);
																									cv$temp$13$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value52) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value52) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value52) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value52) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value52) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value52);
																							}
																						}
																					}
																				} else {
																					if(true) {
																						for(int index$sample18$71 = 0; index$sample18$71 < weightings.length; index$sample18$71 += 1) {
																							int distributionTempVariable$var15$73 = index$sample18$71;
																							double cv$probabilitySample18Value72 = (cv$probabilitySample26Value52 * distribution$sample18[index$sample18$71]);
																							int traceTempVariable$var49$74_1 = distributionTempVariable$var15$73;
																							if((0 == (j + 1))) {
																								{
																									{
																										double cv$temp$14$var50;
																										{
																											double var50 = ((((1.0 * traceTempVariable$v1$42_1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$54_3) / traceTempVariable$var49$74_1);
																											cv$temp$14$var50 = var50;
																										}
																										if(((Math.log(cv$probabilitySample18Value72) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var50)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value72) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value72) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var50));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value72) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value72) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var50)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value72);
																									}
																								}
																							}
																						}
																					}
																				}
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
													for(int k = 0; k < (size + 1); k += 1) {
														if((0 == k)) {
															if((k == (j + 1))) {
																int traceTempVariable$var49$90_1 = cv$currentValue;
																if(((index$i$1 + 1) == (j + 1))) {
																	{
																		{
																			double cv$temp$15$var50;
																			{
																				double var50 = ((((1.0 * v1) + traceTempVariable$var41$5_1) + v3[(j + 1)]) / traceTempVariable$var49$90_1);
																				cv$temp$15$var50 = var50;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var50)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var50));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var50)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
																for(int index$i$91 = 0; index$i$91 < size; index$i$91 += 1) {
																	if(!(index$i$91 == index$i$1)) {
																		for(int index$sample26$92 = 0; index$sample26$92 < weightings.length; index$sample26$92 += 1) {
																			int distributionTempVariable$var23$94 = index$sample26$92;
																			double cv$probabilitySample26Value93 = (1.0 * distribution$sample26[((index$i$91 - 0) / 1)][index$sample26$92]);
																			int traceTempVariable$var49$95_1 = distributionTempVariable$var23$94;
																			if(((index$i$91 + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$16$var50;
																						{
																							double var50 = ((((1.0 * v1) + traceTempVariable$var41$5_1) + v3[(j + 1)]) / traceTempVariable$var49$95_1);
																							cv$temp$16$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample26Value93) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value93) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value93) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value93) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value93) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value93);
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
														for(int index$sample18$81 = 0; index$sample18$81 < weightings.length; index$sample18$81 += 1) {
															int distributionTempVariable$var15$83 = index$sample18$81;
															double cv$probabilitySample18Value82 = (1.0 * distribution$sample18[index$sample18$81]);
															int traceTempVariable$var33$84_1 = distributionTempVariable$var15$83;
															for(int k = 0; k < (size + 1); k += 1) {
																if((0 == k)) {
																	int traceTempVariable$var45$84_3 = traceTempVariable$var33$84_1;
																	if((k == (j + 1))) {
																		int traceTempVariable$var49$96_1 = cv$currentValue;
																		if(((index$i$1 + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$17$var50;
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$84_3) / traceTempVariable$var49$96_1);
																						cv$temp$17$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample18Value82) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value82) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value82) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value82) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value82) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value82);
																				}
																			}
																		}
																		for(int index$i$97 = 0; index$i$97 < size; index$i$97 += 1) {
																			if(!(index$i$97 == index$i$1)) {
																				for(int index$sample26$98 = 0; index$sample26$98 < weightings.length; index$sample26$98 += 1) {
																					int distributionTempVariable$var23$100 = index$sample26$98;
																					double cv$probabilitySample26Value99 = (cv$probabilitySample18Value82 * distribution$sample26[((index$i$97 - 0) / 1)][index$sample26$98]);
																					int traceTempVariable$var49$101_1 = distributionTempVariable$var23$100;
																					if(((index$i$97 + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$18$var50;
																								{
																									double var50 = ((((1.0 * v1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$84_3) / traceTempVariable$var49$101_1);
																									cv$temp$18$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value99) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value99) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value99) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value99) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value99) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value99);
																							}
																						}
																					}
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
													for(int index$sample12$76 = 0; index$sample12$76 < weightings.length; index$sample12$76 += 1) {
														int distributionTempVariable$v1$78 = index$sample12$76;
														double cv$probabilitySample12Value77 = (1.0 * distribution$sample12[index$sample12$76]);
														int traceTempVariable$v1$79_1 = distributionTempVariable$v1$78;
														if(fixedFlag$sample18) {
															for(int k = 0; k < (size + 1); k += 1) {
																if((0 == k)) {
																	if((k == (j + 1))) {
																		int traceTempVariable$var49$102_1 = cv$currentValue;
																		if(((index$i$1 + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$19$var50;
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$79_1) + traceTempVariable$var41$5_1) + v3[(j + 1)]) / traceTempVariable$var49$102_1);
																						cv$temp$19$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample12Value77) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value77) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value77) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value77) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value77) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value77);
																				}
																			}
																		}
																		for(int index$i$103 = 0; index$i$103 < size; index$i$103 += 1) {
																			if(!(index$i$103 == index$i$1)) {
																				for(int index$sample26$104 = 0; index$sample26$104 < weightings.length; index$sample26$104 += 1) {
																					int distributionTempVariable$var23$106 = index$sample26$104;
																					double cv$probabilitySample26Value105 = (cv$probabilitySample12Value77 * distribution$sample26[((index$i$103 - 0) / 1)][index$sample26$104]);
																					int traceTempVariable$var49$107_1 = distributionTempVariable$var23$106;
																					if(((index$i$103 + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$20$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$79_1) + traceTempVariable$var41$5_1) + v3[(j + 1)]) / traceTempVariable$var49$107_1);
																									cv$temp$20$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value105) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value105) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value105) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value105) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value105) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value105);
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
																for(int index$sample18$86 = 0; index$sample18$86 < weightings.length; index$sample18$86 += 1) {
																	int distributionTempVariable$var15$88 = index$sample18$86;
																	double cv$probabilitySample18Value87 = (cv$probabilitySample12Value77 * distribution$sample18[index$sample18$86]);
																	int traceTempVariable$var33$89_1 = distributionTempVariable$var15$88;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if((0 == k)) {
																			int traceTempVariable$var45$89_3 = traceTempVariable$var33$89_1;
																			if((k == (j + 1))) {
																				int traceTempVariable$var49$108_1 = cv$currentValue;
																				if(((index$i$1 + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$21$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$79_1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$89_3) / traceTempVariable$var49$108_1);
																								cv$temp$21$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample18Value87) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value87) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value87) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value87) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value87) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value87);
																						}
																					}
																				}
																				for(int index$i$109 = 0; index$i$109 < size; index$i$109 += 1) {
																					if(!(index$i$109 == index$i$1)) {
																						for(int index$sample26$110 = 0; index$sample26$110 < weightings.length; index$sample26$110 += 1) {
																							int distributionTempVariable$var23$112 = index$sample26$110;
																							double cv$probabilitySample26Value111 = (cv$probabilitySample18Value87 * distribution$sample26[((index$i$109 - 0) / 1)][index$sample26$110]);
																							int traceTempVariable$var49$113_1 = distributionTempVariable$var23$112;
																							if(((index$i$109 + 1) == (j + 1))) {
																								{
																									{
																										double cv$temp$22$var50;
																										{
																											double var50 = ((((1.0 * traceTempVariable$v1$79_1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$89_3) / traceTempVariable$var49$113_1);
																											cv$temp$22$var50 = var50;
																										}
																										if(((Math.log(cv$probabilitySample26Value111) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var50)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value111) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value111) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var50));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value111) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value111) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var50)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value111);
																									}
																								}
																							}
																						}
																					}
																				}
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
												int traceTempVariable$var33$119_1 = cv$currentValue;
												for(int k = 0; k < (size + 1); k += 1) {
													if(((index$i$1 + 1) == k)) {
														int traceTempVariable$var45$119_3 = traceTempVariable$var33$119_1;
														if((k == (j + 1))) {
															int traceTempVariable$var49$131_1 = cv$currentValue;
															if(((index$i$1 + 1) == (j + 1))) {
																{
																	{
																		double cv$temp$23$var50;
																		{
																			double var50 = ((((1.0 * v1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$119_3) / traceTempVariable$var49$131_1);
																			cv$temp$23$var50 = var50;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var50)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var50));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var50)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
															for(int index$i$132 = 0; index$i$132 < size; index$i$132 += 1) {
																if(!(index$i$132 == index$i$1)) {
																	for(int index$sample26$133 = 0; index$sample26$133 < weightings.length; index$sample26$133 += 1) {
																		int distributionTempVariable$var23$135 = index$sample26$133;
																		double cv$probabilitySample26Value134 = (1.0 * distribution$sample26[((index$i$132 - 0) / 1)][index$sample26$133]);
																		int traceTempVariable$var49$136_1 = distributionTempVariable$var23$135;
																		if(((index$i$132 + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$24$var50;
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$119_3) / traceTempVariable$var49$136_1);
																						cv$temp$24$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample26Value134) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value134) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value134) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value134) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value134) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value134);
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
														for(int index$sample26$121 = 0; index$sample26$121 < weightings.length; index$sample26$121 += 1) {
															int distributionTempVariable$var23$123 = index$sample26$121;
															double cv$probabilitySample26Value122 = (1.0 * distribution$sample26[((index$i$120 - 0) / 1)][index$sample26$121]);
															int traceTempVariable$var33$124_1 = distributionTempVariable$var23$123;
															for(int k = 0; k < (size + 1); k += 1) {
																if(((index$i$120 + 1) == k)) {
																	int traceTempVariable$var45$124_3 = traceTempVariable$var33$124_1;
																	if((k == (j + 1))) {
																		int traceTempVariable$var49$137_1 = cv$currentValue;
																		if(((index$i$1 + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$25$var50;
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$124_3) / traceTempVariable$var49$137_1);
																						cv$temp$25$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample26Value122) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value122) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value122) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value122) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value122) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value122);
																				}
																			}
																		}
																		int traceTempVariable$var49$138_1 = distributionTempVariable$var23$123;
																		if(((index$i$120 + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$26$var50;
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$124_3) / traceTempVariable$var49$138_1);
																						cv$temp$26$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample26Value122) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value122) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value122) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value122) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value122) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value122);
																				}
																			}
																		}
																		for(int index$i$139 = 0; index$i$139 < size; index$i$139 += 1) {
																			if((!(index$i$139 == index$i$1) && !(index$i$139 == index$i$120))) {
																				for(int index$sample26$140 = 0; index$sample26$140 < weightings.length; index$sample26$140 += 1) {
																					int distributionTempVariable$var23$142 = index$sample26$140;
																					double cv$probabilitySample26Value141 = (cv$probabilitySample26Value122 * distribution$sample26[((index$i$139 - 0) / 1)][index$sample26$140]);
																					int traceTempVariable$var49$143_1 = distributionTempVariable$var23$142;
																					if(((index$i$139 + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$27$var50;
																								{
																									double var50 = ((((1.0 * v1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$124_3) / traceTempVariable$var49$143_1);
																									cv$temp$27$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value141) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value141) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value141) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value141) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value141) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value141);
																							}
																						}
																					}
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
														int traceTempVariable$v1$118_1 = distributionTempVariable$v1$117;
														int traceTempVariable$var33$125_1 = cv$currentValue;
														for(int k = 0; k < (size + 1); k += 1) {
															if(((index$i$1 + 1) == k)) {
																int traceTempVariable$var45$125_3 = traceTempVariable$var33$125_1;
																if((k == (j + 1))) {
																	int traceTempVariable$var49$144_1 = cv$currentValue;
																	if(((index$i$1 + 1) == (j + 1))) {
																		{
																			{
																				double cv$temp$28$var50;
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$118_1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$125_3) / traceTempVariable$var49$144_1);
																					cv$temp$28$var50 = var50;
																				}
																				if(((Math.log(cv$probabilitySample12Value116) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value116) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value116) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value116) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value116) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var50)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value116);
																			}
																		}
																	}
																	for(int index$i$145 = 0; index$i$145 < size; index$i$145 += 1) {
																		if(!(index$i$145 == index$i$1)) {
																			for(int index$sample26$146 = 0; index$sample26$146 < weightings.length; index$sample26$146 += 1) {
																				int distributionTempVariable$var23$148 = index$sample26$146;
																				double cv$probabilitySample26Value147 = (cv$probabilitySample12Value116 * distribution$sample26[((index$i$145 - 0) / 1)][index$sample26$146]);
																				int traceTempVariable$var49$149_1 = distributionTempVariable$var23$148;
																				if(((index$i$145 + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$29$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$118_1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$125_3) / traceTempVariable$var49$149_1);
																								cv$temp$29$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample26Value147) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value147) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value147) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value147) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value147) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value147);
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
																for(int index$sample26$127 = 0; index$sample26$127 < weightings.length; index$sample26$127 += 1) {
																	int distributionTempVariable$var23$129 = index$sample26$127;
																	double cv$probabilitySample26Value128 = (cv$probabilitySample12Value116 * distribution$sample26[((index$i$126 - 0) / 1)][index$sample26$127]);
																	int traceTempVariable$var33$130_1 = distributionTempVariable$var23$129;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if(((index$i$126 + 1) == k)) {
																			int traceTempVariable$var45$130_3 = traceTempVariable$var33$130_1;
																			if((k == (j + 1))) {
																				int traceTempVariable$var49$150_1 = cv$currentValue;
																				if(((index$i$1 + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$30$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$118_1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$130_3) / traceTempVariable$var49$150_1);
																								cv$temp$30$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample26Value128) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value128) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value128) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value128) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value128) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value128);
																						}
																					}
																				}
																				int traceTempVariable$var49$151_1 = distributionTempVariable$var23$129;
																				if(((index$i$126 + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$31$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$118_1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$130_3) / traceTempVariable$var49$151_1);
																								cv$temp$31$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample26Value128) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value128) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value128) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value128) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value128) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value128);
																						}
																					}
																				}
																				for(int index$i$152 = 0; index$i$152 < size; index$i$152 += 1) {
																					if((!(index$i$152 == index$i$1) && !(index$i$152 == index$i$126))) {
																						for(int index$sample26$153 = 0; index$sample26$153 < weightings.length; index$sample26$153 += 1) {
																							int distributionTempVariable$var23$155 = index$sample26$153;
																							double cv$probabilitySample26Value154 = (cv$probabilitySample26Value128 * distribution$sample26[((index$i$152 - 0) / 1)][index$sample26$153]);
																							int traceTempVariable$var49$156_1 = distributionTempVariable$var23$155;
																							if(((index$i$152 + 1) == (j + 1))) {
																								{
																									{
																										double cv$temp$32$var50;
																										{
																											double var50 = ((((1.0 * traceTempVariable$v1$118_1) + traceTempVariable$var41$5_1) + traceTempVariable$var45$130_3) / traceTempVariable$var49$156_1);
																											cv$temp$32$var50 = var50;
																										}
																										if(((Math.log(cv$probabilitySample26Value154) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var50)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value154) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value154) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var50));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value154) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value154) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var50)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value154);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
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
						int traceTempVariable$var49$6_1 = cv$currentValue;
						for(int j = 0; j < size; j += 1) {
							if(((i + 1) == (j + 1))) {
								if(!guard$sample26bernoulli54[((j - 0) / 1)]) {
									guard$sample26bernoulli54[((j - 0) / 1)] = true;
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											if(fixedFlag$sample12) {
												if(fixedFlag$sample18) {
													if((0 == j)) {
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																if((k == (j + 1))) {
																	{
																		{
																			double cv$temp$33$var50;
																			{
																				double var50 = ((((1.0 * v1) + v2[j]) + v3[(j + 1)]) / traceTempVariable$var49$6_1);
																				cv$temp$33$var50 = var50;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var50)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var50));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var50)));
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
														for(int index$sample18$163 = 0; index$sample18$163 < weightings.length; index$sample18$163 += 1) {
															int distributionTempVariable$var15$165 = index$sample18$163;
															double cv$probabilitySample18Value164 = (1.0 * distribution$sample18[index$sample18$163]);
															int traceTempVariable$var41$166_1 = distributionTempVariable$var15$165;
															if((0 == j)) {
																int traceTempVariable$var33$173_1 = distributionTempVariable$var15$165;
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var45$173_3 = traceTempVariable$var33$173_1;
																		if((k == (j + 1))) {
																			{
																				{
																					double cv$temp$34$var50;
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$166_1) + traceTempVariable$var45$173_3) / traceTempVariable$var49$6_1);
																						cv$temp$34$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample18Value164) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value164) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value164) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value164) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value164) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value164);
																				}
																			}
																		}
																	}
																}
																if(!true) {
																	for(int index$sample18$174 = 0; index$sample18$174 < weightings.length; index$sample18$174 += 1) {
																		int distributionTempVariable$var15$176 = index$sample18$174;
																		double cv$probabilitySample18Value175 = (cv$probabilitySample18Value164 * distribution$sample18[index$sample18$174]);
																		int traceTempVariable$var33$177_1 = distributionTempVariable$var15$176;
																		for(int k = 0; k < (size + 1); k += 1) {
																			if((0 == k)) {
																				int traceTempVariable$var45$177_3 = traceTempVariable$var33$177_1;
																				if((k == (j + 1))) {
																					{
																						{
																							double cv$temp$35$var50;
																							{
																								double var50 = ((((1.0 * v1) + traceTempVariable$var41$166_1) + traceTempVariable$var45$177_3) / traceTempVariable$var49$6_1);
																								cv$temp$35$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample18Value175) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value175) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value175) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value175) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value175) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value175);
																						}
																					}
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
													for(int index$sample12$158 = 0; index$sample12$158 < weightings.length; index$sample12$158 += 1) {
														int distributionTempVariable$v1$160 = index$sample12$158;
														double cv$probabilitySample12Value159 = (1.0 * distribution$sample12[index$sample12$158]);
														int traceTempVariable$v1$161_1 = distributionTempVariable$v1$160;
														if(fixedFlag$sample18) {
															if((0 == j)) {
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		if((k == (j + 1))) {
																			{
																				{
																					double cv$temp$36$var50;
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$161_1) + v2[j]) + v3[(j + 1)]) / traceTempVariable$var49$6_1);
																						cv$temp$36$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample12Value159) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value159) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value159) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value159) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value159) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value159);
																				}
																			}
																		}
																	}
																}
															}
														} else {
															if(true) {
																for(int index$sample18$168 = 0; index$sample18$168 < weightings.length; index$sample18$168 += 1) {
																	int distributionTempVariable$var15$170 = index$sample18$168;
																	double cv$probabilitySample18Value169 = (cv$probabilitySample12Value159 * distribution$sample18[index$sample18$168]);
																	int traceTempVariable$var41$171_1 = distributionTempVariable$var15$170;
																	if((0 == j)) {
																		int traceTempVariable$var33$179_1 = distributionTempVariable$var15$170;
																		for(int k = 0; k < (size + 1); k += 1) {
																			if((0 == k)) {
																				int traceTempVariable$var45$179_3 = traceTempVariable$var33$179_1;
																				if((k == (j + 1))) {
																					{
																						{
																							double cv$temp$37$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$161_1) + traceTempVariable$var41$171_1) + traceTempVariable$var45$179_3) / traceTempVariable$var49$6_1);
																								cv$temp$37$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample18Value169) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value169) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value169) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value169) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value169) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value169);
																						}
																					}
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample18$180 = 0; index$sample18$180 < weightings.length; index$sample18$180 += 1) {
																				int distributionTempVariable$var15$182 = index$sample18$180;
																				double cv$probabilitySample18Value181 = (cv$probabilitySample18Value169 * distribution$sample18[index$sample18$180]);
																				int traceTempVariable$var33$183_1 = distributionTempVariable$var15$182;
																				for(int k = 0; k < (size + 1); k += 1) {
																					if((0 == k)) {
																						int traceTempVariable$var45$183_3 = traceTempVariable$var33$183_1;
																						if((k == (j + 1))) {
																							{
																								{
																									double cv$temp$38$var50;
																									{
																										double var50 = ((((1.0 * traceTempVariable$v1$161_1) + traceTempVariable$var41$171_1) + traceTempVariable$var45$183_3) / traceTempVariable$var49$6_1);
																										cv$temp$38$var50 = var50;
																									}
																									if(((Math.log(cv$probabilitySample18Value181) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var50)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value181) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value181) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var50));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value181) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value181) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var50)));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value181);
																								}
																							}
																						}
																					}
																				}
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
														int traceTempVariable$var33$199_1 = cv$currentValue;
														for(int k = 0; k < (size + 1); k += 1) {
															if(((index$i$1 + 1) == k)) {
																int traceTempVariable$var45$199_3 = traceTempVariable$var33$199_1;
																if((k == (j + 1))) {
																	{
																		{
																			double cv$temp$39$var50;
																			{
																				double var50 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var45$199_3) / traceTempVariable$var49$6_1);
																				cv$temp$39$var50 = var50;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var50)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var50));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var50)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
														for(int index$i$200 = 0; index$i$200 < size; index$i$200 += 1) {
															if(!(index$i$200 == index$i$1)) {
																for(int index$sample26$201 = 0; index$sample26$201 < weightings.length; index$sample26$201 += 1) {
																	int distributionTempVariable$var23$203 = index$sample26$201;
																	double cv$probabilitySample26Value202 = (1.0 * distribution$sample26[((index$i$200 - 0) / 1)][index$sample26$201]);
																	int traceTempVariable$var33$204_1 = distributionTempVariable$var23$203;
																	for(int k = 0; k < (size + 1); k += 1) {
																		if(((index$i$200 + 1) == k)) {
																			int traceTempVariable$var45$204_3 = traceTempVariable$var33$204_1;
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$40$var50;
																						{
																							double var50 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var45$204_3) / traceTempVariable$var49$6_1);
																							cv$temp$40$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample26Value202) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value202) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value202) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value202) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value202) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value202);
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
														for(int index$sample18$190 = 0; index$sample18$190 < weightings.length; index$sample18$190 += 1) {
															int distributionTempVariable$var15$192 = index$sample18$190;
															double cv$probabilitySample18Value191 = (1.0 * distribution$sample18[index$sample18$190]);
															int traceTempVariable$var41$193_1 = distributionTempVariable$var15$192;
															if((0 == j)) {
																int traceTempVariable$var33$205_1 = cv$currentValue;
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((index$i$1 + 1) == k)) {
																		int traceTempVariable$var45$205_3 = traceTempVariable$var33$205_1;
																		if((k == (j + 1))) {
																			{
																				{
																					double cv$temp$41$var50;
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$193_1) + traceTempVariable$var45$205_3) / traceTempVariable$var49$6_1);
																						cv$temp$41$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample18Value191) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value191) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value191) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value191) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value191) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value191);
																				}
																			}
																		}
																	}
																}
																for(int index$i$206 = 0; index$i$206 < size; index$i$206 += 1) {
																	if(!(index$i$206 == index$i$1)) {
																		for(int index$sample26$207 = 0; index$sample26$207 < weightings.length; index$sample26$207 += 1) {
																			int distributionTempVariable$var23$209 = index$sample26$207;
																			double cv$probabilitySample26Value208 = (cv$probabilitySample18Value191 * distribution$sample26[((index$i$206 - 0) / 1)][index$sample26$207]);
																			int traceTempVariable$var33$210_1 = distributionTempVariable$var23$209;
																			for(int k = 0; k < (size + 1); k += 1) {
																				if(((index$i$206 + 1) == k)) {
																					int traceTempVariable$var45$210_3 = traceTempVariable$var33$210_1;
																					if((k == (j + 1))) {
																						{
																							{
																								double cv$temp$42$var50;
																								{
																									double var50 = ((((1.0 * v1) + traceTempVariable$var41$193_1) + traceTempVariable$var45$210_3) / traceTempVariable$var49$6_1);
																									cv$temp$42$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value208) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value208) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value208) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value208) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value208) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value208);
																							}
																						}
																					}
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
													for(int index$sample12$185 = 0; index$sample12$185 < weightings.length; index$sample12$185 += 1) {
														int distributionTempVariable$v1$187 = index$sample12$185;
														double cv$probabilitySample12Value186 = (1.0 * distribution$sample12[index$sample12$185]);
														int traceTempVariable$v1$188_1 = distributionTempVariable$v1$187;
														if(fixedFlag$sample18) {
															if((0 == j)) {
																int traceTempVariable$var33$211_1 = cv$currentValue;
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((index$i$1 + 1) == k)) {
																		int traceTempVariable$var45$211_3 = traceTempVariable$var33$211_1;
																		if((k == (j + 1))) {
																			{
																				{
																					double cv$temp$43$var50;
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$188_1) + v2[j]) + traceTempVariable$var45$211_3) / traceTempVariable$var49$6_1);
																						cv$temp$43$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample12Value186) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value186) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value186) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value186) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value186) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value186);
																				}
																			}
																		}
																	}
																}
																for(int index$i$212 = 0; index$i$212 < size; index$i$212 += 1) {
																	if(!(index$i$212 == index$i$1)) {
																		for(int index$sample26$213 = 0; index$sample26$213 < weightings.length; index$sample26$213 += 1) {
																			int distributionTempVariable$var23$215 = index$sample26$213;
																			double cv$probabilitySample26Value214 = (cv$probabilitySample12Value186 * distribution$sample26[((index$i$212 - 0) / 1)][index$sample26$213]);
																			int traceTempVariable$var33$216_1 = distributionTempVariable$var23$215;
																			for(int k = 0; k < (size + 1); k += 1) {
																				if(((index$i$212 + 1) == k)) {
																					int traceTempVariable$var45$216_3 = traceTempVariable$var33$216_1;
																					if((k == (j + 1))) {
																						{
																							{
																								double cv$temp$44$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$188_1) + v2[j]) + traceTempVariable$var45$216_3) / traceTempVariable$var49$6_1);
																									cv$temp$44$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value214) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value214) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value214) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value214) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value214) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value214);
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
																for(int index$sample18$195 = 0; index$sample18$195 < weightings.length; index$sample18$195 += 1) {
																	int distributionTempVariable$var15$197 = index$sample18$195;
																	double cv$probabilitySample18Value196 = (cv$probabilitySample12Value186 * distribution$sample18[index$sample18$195]);
																	int traceTempVariable$var41$198_1 = distributionTempVariable$var15$197;
																	if((0 == j)) {
																		int traceTempVariable$var33$217_1 = cv$currentValue;
																		for(int k = 0; k < (size + 1); k += 1) {
																			if(((index$i$1 + 1) == k)) {
																				int traceTempVariable$var45$217_3 = traceTempVariable$var33$217_1;
																				if((k == (j + 1))) {
																					{
																						{
																							double cv$temp$45$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$188_1) + traceTempVariable$var41$198_1) + traceTempVariable$var45$217_3) / traceTempVariable$var49$6_1);
																								cv$temp$45$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample18Value196) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value196) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value196) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value196) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value196) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value196);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$218 = 0; index$i$218 < size; index$i$218 += 1) {
																			if(!(index$i$218 == index$i$1)) {
																				for(int index$sample26$219 = 0; index$sample26$219 < weightings.length; index$sample26$219 += 1) {
																					int distributionTempVariable$var23$221 = index$sample26$219;
																					double cv$probabilitySample26Value220 = (cv$probabilitySample18Value196 * distribution$sample26[((index$i$218 - 0) / 1)][index$sample26$219]);
																					int traceTempVariable$var33$222_1 = distributionTempVariable$var23$221;
																					for(int k = 0; k < (size + 1); k += 1) {
																						if(((index$i$218 + 1) == k)) {
																							int traceTempVariable$var45$222_3 = traceTempVariable$var33$222_1;
																							if((k == (j + 1))) {
																								{
																									{
																										double cv$temp$46$var50;
																										{
																											double var50 = ((((1.0 * traceTempVariable$v1$188_1) + traceTempVariable$var41$198_1) + traceTempVariable$var45$222_3) / traceTempVariable$var49$6_1);
																											cv$temp$46$var50 = var50;
																										}
																										if(((Math.log(cv$probabilitySample26Value220) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var50)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value220) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value220) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var50));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value220) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value220) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var50)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value220);
																									}
																								}
																							}
																						}
																					}
																				}
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
												int traceTempVariable$var41$228_1 = cv$currentValue;
												if(((index$i$1 + 1) == j)) {
													if(fixedFlag$sample18) {
														for(int k = 0; k < (size + 1); k += 1) {
															if((0 == k)) {
																if((k == (j + 1))) {
																	{
																		{
																			double cv$temp$47$var50;
																			{
																				double var50 = ((((1.0 * v1) + traceTempVariable$var41$228_1) + v3[(j + 1)]) / traceTempVariable$var49$6_1);
																				cv$temp$47$var50 = var50;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var50)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var50));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var50)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample18$241 = 0; index$sample18$241 < weightings.length; index$sample18$241 += 1) {
																int distributionTempVariable$var15$243 = index$sample18$241;
																double cv$probabilitySample18Value242 = (1.0 * distribution$sample18[index$sample18$241]);
																int traceTempVariable$var33$244_1 = distributionTempVariable$var15$243;
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		int traceTempVariable$var45$244_3 = traceTempVariable$var33$244_1;
																		if((k == (j + 1))) {
																			{
																				{
																					double cv$temp$48$var50;
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$228_1) + traceTempVariable$var45$244_3) / traceTempVariable$var49$6_1);
																						cv$temp$48$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample18Value242) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value242) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value242) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value242) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value242) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value242);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
												for(int index$i$229 = 0; index$i$229 < size; index$i$229 += 1) {
													if(!(index$i$229 == index$i$1)) {
														for(int index$sample26$230 = 0; index$sample26$230 < weightings.length; index$sample26$230 += 1) {
															int distributionTempVariable$var23$232 = index$sample26$230;
															double cv$probabilitySample26Value231 = (1.0 * distribution$sample26[((index$i$229 - 0) / 1)][index$sample26$230]);
															int traceTempVariable$var41$233_1 = distributionTempVariable$var23$232;
															if(((index$i$229 + 1) == j)) {
																if(fixedFlag$sample18) {
																	for(int k = 0; k < (size + 1); k += 1) {
																		if((0 == k)) {
																			if((k == (j + 1))) {
																				{
																					{
																						double cv$temp$49$var50;
																						{
																							double var50 = ((((1.0 * v1) + traceTempVariable$var41$233_1) + v3[(j + 1)]) / traceTempVariable$var49$6_1);
																							cv$temp$49$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample26Value231) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value231) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value231) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value231) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value231) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value231);
																					}
																				}
																			}
																		}
																	}
																} else {
																	if(true) {
																		for(int index$sample18$246 = 0; index$sample18$246 < weightings.length; index$sample18$246 += 1) {
																			int distributionTempVariable$var15$248 = index$sample18$246;
																			double cv$probabilitySample18Value247 = (cv$probabilitySample26Value231 * distribution$sample18[index$sample18$246]);
																			int traceTempVariable$var33$249_1 = distributionTempVariable$var15$248;
																			for(int k = 0; k < (size + 1); k += 1) {
																				if((0 == k)) {
																					int traceTempVariable$var45$249_3 = traceTempVariable$var33$249_1;
																					if((k == (j + 1))) {
																						{
																							{
																								double cv$temp$50$var50;
																								{
																									double var50 = ((((1.0 * v1) + traceTempVariable$var41$233_1) + traceTempVariable$var45$249_3) / traceTempVariable$var49$6_1);
																									cv$temp$50$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample18Value247) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value247) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value247) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value247) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value247) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value247);
																							}
																						}
																					}
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
													for(int index$sample12$224 = 0; index$sample12$224 < weightings.length; index$sample12$224 += 1) {
														int distributionTempVariable$v1$226 = index$sample12$224;
														double cv$probabilitySample12Value225 = (1.0 * distribution$sample12[index$sample12$224]);
														int traceTempVariable$v1$227_1 = distributionTempVariable$v1$226;
														int traceTempVariable$var41$234_1 = cv$currentValue;
														if(((index$i$1 + 1) == j)) {
															if(fixedFlag$sample18) {
																for(int k = 0; k < (size + 1); k += 1) {
																	if((0 == k)) {
																		if((k == (j + 1))) {
																			{
																				{
																					double cv$temp$51$var50;
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$227_1) + traceTempVariable$var41$234_1) + v3[(j + 1)]) / traceTempVariable$var49$6_1);
																						cv$temp$51$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample12Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value225);
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample18$251 = 0; index$sample18$251 < weightings.length; index$sample18$251 += 1) {
																		int distributionTempVariable$var15$253 = index$sample18$251;
																		double cv$probabilitySample18Value252 = (cv$probabilitySample12Value225 * distribution$sample18[index$sample18$251]);
																		int traceTempVariable$var33$254_1 = distributionTempVariable$var15$253;
																		for(int k = 0; k < (size + 1); k += 1) {
																			if((0 == k)) {
																				int traceTempVariable$var45$254_3 = traceTempVariable$var33$254_1;
																				if((k == (j + 1))) {
																					{
																						{
																							double cv$temp$52$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$227_1) + traceTempVariable$var41$234_1) + traceTempVariable$var45$254_3) / traceTempVariable$var49$6_1);
																								cv$temp$52$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample18Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value252);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int index$i$235 = 0; index$i$235 < size; index$i$235 += 1) {
															if(!(index$i$235 == index$i$1)) {
																for(int index$sample26$236 = 0; index$sample26$236 < weightings.length; index$sample26$236 += 1) {
																	int distributionTempVariable$var23$238 = index$sample26$236;
																	double cv$probabilitySample26Value237 = (cv$probabilitySample12Value225 * distribution$sample26[((index$i$235 - 0) / 1)][index$sample26$236]);
																	int traceTempVariable$var41$239_1 = distributionTempVariable$var23$238;
																	if(((index$i$235 + 1) == j)) {
																		if(fixedFlag$sample18) {
																			for(int k = 0; k < (size + 1); k += 1) {
																				if((0 == k)) {
																					if((k == (j + 1))) {
																						{
																							{
																								double cv$temp$53$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$227_1) + traceTempVariable$var41$239_1) + v3[(j + 1)]) / traceTempVariable$var49$6_1);
																									cv$temp$53$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value237) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value237) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value237) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value237) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value237) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value237);
																							}
																						}
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample18$256 = 0; index$sample18$256 < weightings.length; index$sample18$256 += 1) {
																					int distributionTempVariable$var15$258 = index$sample18$256;
																					double cv$probabilitySample18Value257 = (cv$probabilitySample26Value237 * distribution$sample18[index$sample18$256]);
																					int traceTempVariable$var33$259_1 = distributionTempVariable$var15$258;
																					for(int k = 0; k < (size + 1); k += 1) {
																						if((0 == k)) {
																							int traceTempVariable$var45$259_3 = traceTempVariable$var33$259_1;
																							if((k == (j + 1))) {
																								{
																									{
																										double cv$temp$54$var50;
																										{
																											double var50 = ((((1.0 * traceTempVariable$v1$227_1) + traceTempVariable$var41$239_1) + traceTempVariable$var45$259_3) / traceTempVariable$var49$6_1);
																											cv$temp$54$var50 = var50;
																										}
																										if(((Math.log(cv$probabilitySample18Value257) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var50)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value257) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value257) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var50));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value257) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value257) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var50)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value257);
																									}
																								}
																							}
																						}
																					}
																				}
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
												int traceTempVariable$var41$265_1 = cv$currentValue;
												if(((index$i$1 + 1) == j)) {
													int traceTempVariable$var33$277_1 = cv$currentValue;
													for(int k = 0; k < (size + 1); k += 1) {
														if(((index$i$1 + 1) == k)) {
															int traceTempVariable$var45$277_3 = traceTempVariable$var33$277_1;
															if((k == (j + 1))) {
																{
																	{
																		double cv$temp$55$var50;
																		{
																			double var50 = ((((1.0 * v1) + traceTempVariable$var41$265_1) + traceTempVariable$var45$277_3) / traceTempVariable$var49$6_1);
																			cv$temp$55$var50 = var50;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var50)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var50));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var50)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													}
													for(int index$i$278 = 0; index$i$278 < size; index$i$278 += 1) {
														if(!(index$i$278 == index$i$1)) {
															for(int index$sample26$279 = 0; index$sample26$279 < weightings.length; index$sample26$279 += 1) {
																int distributionTempVariable$var23$281 = index$sample26$279;
																double cv$probabilitySample26Value280 = (1.0 * distribution$sample26[((index$i$278 - 0) / 1)][index$sample26$279]);
																int traceTempVariable$var33$282_1 = distributionTempVariable$var23$281;
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((index$i$278 + 1) == k)) {
																		int traceTempVariable$var45$282_3 = traceTempVariable$var33$282_1;
																		if((k == (j + 1))) {
																			{
																				{
																					double cv$temp$56$var50;
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$265_1) + traceTempVariable$var45$282_3) / traceTempVariable$var49$6_1);
																						cv$temp$56$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample26Value280) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value280) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value280) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value280) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value280) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value280);
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
														for(int index$sample26$267 = 0; index$sample26$267 < weightings.length; index$sample26$267 += 1) {
															int distributionTempVariable$var23$269 = index$sample26$267;
															double cv$probabilitySample26Value268 = (1.0 * distribution$sample26[((index$i$266 - 0) / 1)][index$sample26$267]);
															int traceTempVariable$var41$270_1 = distributionTempVariable$var23$269;
															if(((index$i$266 + 1) == j)) {
																int traceTempVariable$var33$283_1 = cv$currentValue;
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((index$i$1 + 1) == k)) {
																		int traceTempVariable$var45$283_3 = traceTempVariable$var33$283_1;
																		if((k == (j + 1))) {
																			{
																				{
																					double cv$temp$57$var50;
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$270_1) + traceTempVariable$var45$283_3) / traceTempVariable$var49$6_1);
																						cv$temp$57$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample26Value268) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value268) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value268) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value268) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value268) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value268);
																				}
																			}
																		}
																	}
																}
																int traceTempVariable$var33$284_1 = distributionTempVariable$var23$269;
																for(int k = 0; k < (size + 1); k += 1) {
																	if(((index$i$266 + 1) == k)) {
																		int traceTempVariable$var45$284_3 = traceTempVariable$var33$284_1;
																		if((k == (j + 1))) {
																			{
																				{
																					double cv$temp$58$var50;
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$270_1) + traceTempVariable$var45$284_3) / traceTempVariable$var49$6_1);
																						cv$temp$58$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample26Value268) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value268) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value268) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value268) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value268) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value268);
																				}
																			}
																		}
																	}
																}
																for(int index$i$285 = 0; index$i$285 < size; index$i$285 += 1) {
																	if((!(index$i$285 == index$i$1) && !(index$i$285 == index$i$266))) {
																		for(int index$sample26$286 = 0; index$sample26$286 < weightings.length; index$sample26$286 += 1) {
																			int distributionTempVariable$var23$288 = index$sample26$286;
																			double cv$probabilitySample26Value287 = (cv$probabilitySample26Value268 * distribution$sample26[((index$i$285 - 0) / 1)][index$sample26$286]);
																			int traceTempVariable$var33$289_1 = distributionTempVariable$var23$288;
																			for(int k = 0; k < (size + 1); k += 1) {
																				if(((index$i$285 + 1) == k)) {
																					int traceTempVariable$var45$289_3 = traceTempVariable$var33$289_1;
																					if((k == (j + 1))) {
																						{
																							{
																								double cv$temp$59$var50;
																								{
																									double var50 = ((((1.0 * v1) + traceTempVariable$var41$270_1) + traceTempVariable$var45$289_3) / traceTempVariable$var49$6_1);
																									cv$temp$59$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value287) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value287) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value287) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value287) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value287) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value287);
																							}
																						}
																					}
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
													for(int index$sample12$261 = 0; index$sample12$261 < weightings.length; index$sample12$261 += 1) {
														int distributionTempVariable$v1$263 = index$sample12$261;
														double cv$probabilitySample12Value262 = (1.0 * distribution$sample12[index$sample12$261]);
														int traceTempVariable$v1$264_1 = distributionTempVariable$v1$263;
														int traceTempVariable$var41$271_1 = cv$currentValue;
														if(((index$i$1 + 1) == j)) {
															int traceTempVariable$var33$290_1 = cv$currentValue;
															for(int k = 0; k < (size + 1); k += 1) {
																if(((index$i$1 + 1) == k)) {
																	int traceTempVariable$var45$290_3 = traceTempVariable$var33$290_1;
																	if((k == (j + 1))) {
																		{
																			{
																				double cv$temp$60$var50;
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$264_1) + traceTempVariable$var41$271_1) + traceTempVariable$var45$290_3) / traceTempVariable$var49$6_1);
																					cv$temp$60$var50 = var50;
																				}
																				if(((Math.log(cv$probabilitySample12Value262) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value262) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value262) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value262) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value262) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var50)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value262);
																			}
																		}
																	}
																}
															}
															for(int index$i$291 = 0; index$i$291 < size; index$i$291 += 1) {
																if(!(index$i$291 == index$i$1)) {
																	for(int index$sample26$292 = 0; index$sample26$292 < weightings.length; index$sample26$292 += 1) {
																		int distributionTempVariable$var23$294 = index$sample26$292;
																		double cv$probabilitySample26Value293 = (cv$probabilitySample12Value262 * distribution$sample26[((index$i$291 - 0) / 1)][index$sample26$292]);
																		int traceTempVariable$var33$295_1 = distributionTempVariable$var23$294;
																		for(int k = 0; k < (size + 1); k += 1) {
																			if(((index$i$291 + 1) == k)) {
																				int traceTempVariable$var45$295_3 = traceTempVariable$var33$295_1;
																				if((k == (j + 1))) {
																					{
																						{
																							double cv$temp$61$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$264_1) + traceTempVariable$var41$271_1) + traceTempVariable$var45$295_3) / traceTempVariable$var49$6_1);
																								cv$temp$61$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample26Value293) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value293) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value293) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value293) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value293) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value293);
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
																for(int index$sample26$273 = 0; index$sample26$273 < weightings.length; index$sample26$273 += 1) {
																	int distributionTempVariable$var23$275 = index$sample26$273;
																	double cv$probabilitySample26Value274 = (cv$probabilitySample12Value262 * distribution$sample26[((index$i$272 - 0) / 1)][index$sample26$273]);
																	int traceTempVariable$var41$276_1 = distributionTempVariable$var23$275;
																	if(((index$i$272 + 1) == j)) {
																		int traceTempVariable$var33$296_1 = cv$currentValue;
																		for(int k = 0; k < (size + 1); k += 1) {
																			if(((index$i$1 + 1) == k)) {
																				int traceTempVariable$var45$296_3 = traceTempVariable$var33$296_1;
																				if((k == (j + 1))) {
																					{
																						{
																							double cv$temp$62$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$264_1) + traceTempVariable$var41$276_1) + traceTempVariable$var45$296_3) / traceTempVariable$var49$6_1);
																								cv$temp$62$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample26Value274) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value274) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value274) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value274) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value274) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value274);
																						}
																					}
																				}
																			}
																		}
																		int traceTempVariable$var33$297_1 = distributionTempVariable$var23$275;
																		for(int k = 0; k < (size + 1); k += 1) {
																			if(((index$i$272 + 1) == k)) {
																				int traceTempVariable$var45$297_3 = traceTempVariable$var33$297_1;
																				if((k == (j + 1))) {
																					{
																						{
																							double cv$temp$63$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$264_1) + traceTempVariable$var41$276_1) + traceTempVariable$var45$297_3) / traceTempVariable$var49$6_1);
																								cv$temp$63$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample26Value274) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value274) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value274) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value274) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value274) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value274);
																						}
																					}
																				}
																			}
																		}
																		for(int index$i$298 = 0; index$i$298 < size; index$i$298 += 1) {
																			if((!(index$i$298 == index$i$1) && !(index$i$298 == index$i$272))) {
																				for(int index$sample26$299 = 0; index$sample26$299 < weightings.length; index$sample26$299 += 1) {
																					int distributionTempVariable$var23$301 = index$sample26$299;
																					double cv$probabilitySample26Value300 = (cv$probabilitySample26Value274 * distribution$sample26[((index$i$298 - 0) / 1)][index$sample26$299]);
																					int traceTempVariable$var33$302_1 = distributionTempVariable$var23$301;
																					for(int k = 0; k < (size + 1); k += 1) {
																						if(((index$i$298 + 1) == k)) {
																							int traceTempVariable$var45$302_3 = traceTempVariable$var33$302_1;
																							if((k == (j + 1))) {
																								{
																									{
																										double cv$temp$64$var50;
																										{
																											double var50 = ((((1.0 * traceTempVariable$v1$264_1) + traceTempVariable$var41$276_1) + traceTempVariable$var45$302_3) / traceTempVariable$var49$6_1);
																											cv$temp$64$var50 = var50;
																										}
																										if(((Math.log(cv$probabilitySample26Value300) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var50)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value300) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value300) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var50));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value300) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value300) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var50)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value300);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
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
						for(int k = 0; k < (size + 1); k += 1) {
							if(((i + 1) == k)) {
								int traceTempVariable$var45$7_3 = traceTempVariable$var33$7_1;
								for(int j = 0; j < size; j += 1) {
									if((k == (j + 1))) {
										if(!guard$sample26bernoulli54[((j - 0) / 1)]) {
											guard$sample26bernoulli54[((j - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(fixedFlag$sample12) {
														if(fixedFlag$sample18) {
															if((0 == j)) {
																if((0 == (j + 1))) {
																	{
																		{
																			double cv$temp$65$var50;
																			{
																				double var50 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var45$7_3) / v2[(j + 1)]);
																				cv$temp$65$var50 = var50;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var50)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var50));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var50)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														} else {
															if(true) {
																for(int index$sample18$309 = 0; index$sample18$309 < weightings.length; index$sample18$309 += 1) {
																	int distributionTempVariable$var15$311 = index$sample18$309;
																	double cv$probabilitySample18Value310 = (1.0 * distribution$sample18[index$sample18$309]);
																	int traceTempVariable$var41$312_1 = distributionTempVariable$var15$311;
																	if((0 == j)) {
																		int traceTempVariable$var49$319_1 = distributionTempVariable$var15$311;
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$66$var50;
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$312_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$319_1);
																						cv$temp$66$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value310);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample18$320 = 0; index$sample18$320 < weightings.length; index$sample18$320 += 1) {
																				int distributionTempVariable$var15$322 = index$sample18$320;
																				double cv$probabilitySample18Value321 = (cv$probabilitySample18Value310 * distribution$sample18[index$sample18$320]);
																				int traceTempVariable$var49$323_1 = distributionTempVariable$var15$322;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$67$var50;
																							{
																								double var50 = ((((1.0 * v1) + traceTempVariable$var41$312_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$323_1);
																								cv$temp$67$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample18Value321) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value321) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value321) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value321) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value321) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var50)));
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
														}
													} else {
														if(true) {
															for(int index$sample12$304 = 0; index$sample12$304 < weightings.length; index$sample12$304 += 1) {
																int distributionTempVariable$v1$306 = index$sample12$304;
																double cv$probabilitySample12Value305 = (1.0 * distribution$sample12[index$sample12$304]);
																int traceTempVariable$v1$307_1 = distributionTempVariable$v1$306;
																if(fixedFlag$sample18) {
																	if((0 == j)) {
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$68$var50;
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$307_1) + v2[j]) + traceTempVariable$var45$7_3) / v2[(j + 1)]);
																						cv$temp$68$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample12Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value305);
																				}
																			}
																		}
																	}
																} else {
																	if(true) {
																		for(int index$sample18$314 = 0; index$sample18$314 < weightings.length; index$sample18$314 += 1) {
																			int distributionTempVariable$var15$316 = index$sample18$314;
																			double cv$probabilitySample18Value315 = (cv$probabilitySample12Value305 * distribution$sample18[index$sample18$314]);
																			int traceTempVariable$var41$317_1 = distributionTempVariable$var15$316;
																			if((0 == j)) {
																				int traceTempVariable$var49$325_1 = distributionTempVariable$var15$316;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$69$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$307_1) + traceTempVariable$var41$317_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$325_1);
																								cv$temp$69$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample18Value315) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value315) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value315) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value315) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value315) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value315);
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample18$326 = 0; index$sample18$326 < weightings.length; index$sample18$326 += 1) {
																						int distributionTempVariable$var15$328 = index$sample18$326;
																						double cv$probabilitySample18Value327 = (cv$probabilitySample18Value315 * distribution$sample18[index$sample18$326]);
																						int traceTempVariable$var49$329_1 = distributionTempVariable$var15$328;
																						if((0 == (j + 1))) {
																							{
																								{
																									double cv$temp$70$var50;
																									{
																										double var50 = ((((1.0 * traceTempVariable$v1$307_1) + traceTempVariable$var41$317_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$329_1);
																										cv$temp$70$var50 = var50;
																									}
																									if(((Math.log(cv$probabilitySample18Value327) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var50)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value327) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value327) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var50));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value327) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value327) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var50)));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value327);
																								}
																							}
																						}
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
														int traceTempVariable$var41$335_1 = cv$currentValue;
														if(((index$i$1 + 1) == j)) {
															if(fixedFlag$sample18) {
																if((0 == (j + 1))) {
																	{
																		{
																			double cv$temp$71$var50;
																			{
																				double var50 = ((((1.0 * v1) + traceTempVariable$var41$335_1) + traceTempVariable$var45$7_3) / v2[(j + 1)]);
																				cv$temp$71$var50 = var50;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var50)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var50));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var50)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample18$348 = 0; index$sample18$348 < weightings.length; index$sample18$348 += 1) {
																		int distributionTempVariable$var15$350 = index$sample18$348;
																		double cv$probabilitySample18Value349 = (1.0 * distribution$sample18[index$sample18$348]);
																		int traceTempVariable$var49$351_1 = distributionTempVariable$var15$350;
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$72$var50;
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$335_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$351_1);
																						cv$temp$72$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample18Value349) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value349) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value349) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value349) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value349) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value349);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int index$i$336 = 0; index$i$336 < size; index$i$336 += 1) {
															if(!(index$i$336 == index$i$1)) {
																for(int index$sample26$337 = 0; index$sample26$337 < weightings.length; index$sample26$337 += 1) {
																	int distributionTempVariable$var23$339 = index$sample26$337;
																	double cv$probabilitySample26Value338 = (1.0 * distribution$sample26[((index$i$336 - 0) / 1)][index$sample26$337]);
																	int traceTempVariable$var41$340_1 = distributionTempVariable$var23$339;
																	if(((index$i$336 + 1) == j)) {
																		if(fixedFlag$sample18) {
																			if((0 == (j + 1))) {
																				{
																					{
																						double cv$temp$73$var50;
																						{
																							double var50 = ((((1.0 * v1) + traceTempVariable$var41$340_1) + traceTempVariable$var45$7_3) / v2[(j + 1)]);
																							cv$temp$73$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample26Value338) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value338) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value338) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value338) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value338) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value338);
																					}
																				}
																			}
																		} else {
																			if(true) {
																				for(int index$sample18$353 = 0; index$sample18$353 < weightings.length; index$sample18$353 += 1) {
																					int distributionTempVariable$var15$355 = index$sample18$353;
																					double cv$probabilitySample18Value354 = (cv$probabilitySample26Value338 * distribution$sample18[index$sample18$353]);
																					int traceTempVariable$var49$356_1 = distributionTempVariable$var15$355;
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$74$var50;
																								{
																									double var50 = ((((1.0 * v1) + traceTempVariable$var41$340_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$356_1);
																									cv$temp$74$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample18Value354) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value354) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value354) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value354) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value354) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value354);
																							}
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
															for(int index$sample12$331 = 0; index$sample12$331 < weightings.length; index$sample12$331 += 1) {
																int distributionTempVariable$v1$333 = index$sample12$331;
																double cv$probabilitySample12Value332 = (1.0 * distribution$sample12[index$sample12$331]);
																int traceTempVariable$v1$334_1 = distributionTempVariable$v1$333;
																int traceTempVariable$var41$341_1 = cv$currentValue;
																if(((index$i$1 + 1) == j)) {
																	if(fixedFlag$sample18) {
																		if((0 == (j + 1))) {
																			{
																				{
																					double cv$temp$75$var50;
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$334_1) + traceTempVariable$var41$341_1) + traceTempVariable$var45$7_3) / v2[(j + 1)]);
																						cv$temp$75$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample12Value332) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value332) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value332) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value332) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value332) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value332);
																				}
																			}
																		}
																	} else {
																		if(true) {
																			for(int index$sample18$358 = 0; index$sample18$358 < weightings.length; index$sample18$358 += 1) {
																				int distributionTempVariable$var15$360 = index$sample18$358;
																				double cv$probabilitySample18Value359 = (cv$probabilitySample12Value332 * distribution$sample18[index$sample18$358]);
																				int traceTempVariable$var49$361_1 = distributionTempVariable$var15$360;
																				if((0 == (j + 1))) {
																					{
																						{
																							double cv$temp$76$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$334_1) + traceTempVariable$var41$341_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$361_1);
																								cv$temp$76$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample18Value359) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value359) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value359) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value359) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value359) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value359);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$342 = 0; index$i$342 < size; index$i$342 += 1) {
																	if(!(index$i$342 == index$i$1)) {
																		for(int index$sample26$343 = 0; index$sample26$343 < weightings.length; index$sample26$343 += 1) {
																			int distributionTempVariable$var23$345 = index$sample26$343;
																			double cv$probabilitySample26Value344 = (cv$probabilitySample12Value332 * distribution$sample26[((index$i$342 - 0) / 1)][index$sample26$343]);
																			int traceTempVariable$var41$346_1 = distributionTempVariable$var23$345;
																			if(((index$i$342 + 1) == j)) {
																				if(fixedFlag$sample18) {
																					if((0 == (j + 1))) {
																						{
																							{
																								double cv$temp$77$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$334_1) + traceTempVariable$var41$346_1) + traceTempVariable$var45$7_3) / v2[(j + 1)]);
																									cv$temp$77$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value344) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value344) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value344) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value344) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value344) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value344);
																							}
																						}
																					}
																				} else {
																					if(true) {
																						for(int index$sample18$363 = 0; index$sample18$363 < weightings.length; index$sample18$363 += 1) {
																							int distributionTempVariable$var15$365 = index$sample18$363;
																							double cv$probabilitySample18Value364 = (cv$probabilitySample26Value344 * distribution$sample18[index$sample18$363]);
																							int traceTempVariable$var49$366_1 = distributionTempVariable$var15$365;
																							if((0 == (j + 1))) {
																								{
																									{
																										double cv$temp$78$var50;
																										{
																											double var50 = ((((1.0 * traceTempVariable$v1$334_1) + traceTempVariable$var41$346_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$366_1);
																											cv$temp$78$var50 = var50;
																										}
																										if(((Math.log(cv$probabilitySample18Value364) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var50)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value364) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value364) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var50));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value364) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value364) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var50)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value364);
																									}
																								}
																							}
																						}
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
																int traceTempVariable$var49$382_1 = cv$currentValue;
																if(((index$i$1 + 1) == (j + 1))) {
																	{
																		{
																			double cv$temp$79$var50;
																			{
																				double var50 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var45$7_3) / traceTempVariable$var49$382_1);
																				cv$temp$79$var50 = var50;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var50)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var50));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var50)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
																for(int index$i$383 = 0; index$i$383 < size; index$i$383 += 1) {
																	if(!(index$i$383 == index$i$1)) {
																		for(int index$sample26$384 = 0; index$sample26$384 < weightings.length; index$sample26$384 += 1) {
																			int distributionTempVariable$var23$386 = index$sample26$384;
																			double cv$probabilitySample26Value385 = (1.0 * distribution$sample26[((index$i$383 - 0) / 1)][index$sample26$384]);
																			int traceTempVariable$var49$387_1 = distributionTempVariable$var23$386;
																			if(((index$i$383 + 1) == (j + 1))) {
																				{
																					{
																						double cv$temp$80$var50;
																						{
																							double var50 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var45$7_3) / traceTempVariable$var49$387_1);
																							cv$temp$80$var50 = var50;
																						}
																						if(((Math.log(cv$probabilitySample26Value385) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var50)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value385) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value385) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var50));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value385) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value385) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var50)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value385);
																					}
																				}
																			}
																		}
																	}
																}
															}
														} else {
															if(true) {
																for(int index$sample18$373 = 0; index$sample18$373 < weightings.length; index$sample18$373 += 1) {
																	int distributionTempVariable$var15$375 = index$sample18$373;
																	double cv$probabilitySample18Value374 = (1.0 * distribution$sample18[index$sample18$373]);
																	int traceTempVariable$var41$376_1 = distributionTempVariable$var15$375;
																	if((0 == j)) {
																		int traceTempVariable$var49$388_1 = cv$currentValue;
																		if(((index$i$1 + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$81$var50;
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$376_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$388_1);
																						cv$temp$81$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample18Value374) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value374) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value374) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value374) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value374) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value374);
																				}
																			}
																		}
																		for(int index$i$389 = 0; index$i$389 < size; index$i$389 += 1) {
																			if(!(index$i$389 == index$i$1)) {
																				for(int index$sample26$390 = 0; index$sample26$390 < weightings.length; index$sample26$390 += 1) {
																					int distributionTempVariable$var23$392 = index$sample26$390;
																					double cv$probabilitySample26Value391 = (cv$probabilitySample18Value374 * distribution$sample26[((index$i$389 - 0) / 1)][index$sample26$390]);
																					int traceTempVariable$var49$393_1 = distributionTempVariable$var23$392;
																					if(((index$i$389 + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$82$var50;
																								{
																									double var50 = ((((1.0 * v1) + traceTempVariable$var41$376_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$393_1);
																									cv$temp$82$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value391) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value391) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value391) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value391) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value391) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value391);
																							}
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
															for(int index$sample12$368 = 0; index$sample12$368 < weightings.length; index$sample12$368 += 1) {
																int distributionTempVariable$v1$370 = index$sample12$368;
																double cv$probabilitySample12Value369 = (1.0 * distribution$sample12[index$sample12$368]);
																int traceTempVariable$v1$371_1 = distributionTempVariable$v1$370;
																if(fixedFlag$sample18) {
																	if((0 == j)) {
																		int traceTempVariable$var49$394_1 = cv$currentValue;
																		if(((index$i$1 + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$83$var50;
																					{
																						double var50 = ((((1.0 * traceTempVariable$v1$371_1) + v2[j]) + traceTempVariable$var45$7_3) / traceTempVariable$var49$394_1);
																						cv$temp$83$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample12Value369) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value369) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value369) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value369) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value369) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value369);
																				}
																			}
																		}
																		for(int index$i$395 = 0; index$i$395 < size; index$i$395 += 1) {
																			if(!(index$i$395 == index$i$1)) {
																				for(int index$sample26$396 = 0; index$sample26$396 < weightings.length; index$sample26$396 += 1) {
																					int distributionTempVariable$var23$398 = index$sample26$396;
																					double cv$probabilitySample26Value397 = (cv$probabilitySample12Value369 * distribution$sample26[((index$i$395 - 0) / 1)][index$sample26$396]);
																					int traceTempVariable$var49$399_1 = distributionTempVariable$var23$398;
																					if(((index$i$395 + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$84$var50;
																								{
																									double var50 = ((((1.0 * traceTempVariable$v1$371_1) + v2[j]) + traceTempVariable$var45$7_3) / traceTempVariable$var49$399_1);
																									cv$temp$84$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value397) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value397) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value397) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value397) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value397) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value397);
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																} else {
																	if(true) {
																		for(int index$sample18$378 = 0; index$sample18$378 < weightings.length; index$sample18$378 += 1) {
																			int distributionTempVariable$var15$380 = index$sample18$378;
																			double cv$probabilitySample18Value379 = (cv$probabilitySample12Value369 * distribution$sample18[index$sample18$378]);
																			int traceTempVariable$var41$381_1 = distributionTempVariable$var15$380;
																			if((0 == j)) {
																				int traceTempVariable$var49$400_1 = cv$currentValue;
																				if(((index$i$1 + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$85$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$371_1) + traceTempVariable$var41$381_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$400_1);
																								cv$temp$85$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample18Value379) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value379) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value379) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value379) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var50)))) + 1)) + (Math.log(cv$probabilitySample18Value379) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value379);
																						}
																					}
																				}
																				for(int index$i$401 = 0; index$i$401 < size; index$i$401 += 1) {
																					if(!(index$i$401 == index$i$1)) {
																						for(int index$sample26$402 = 0; index$sample26$402 < weightings.length; index$sample26$402 += 1) {
																							int distributionTempVariable$var23$404 = index$sample26$402;
																							double cv$probabilitySample26Value403 = (cv$probabilitySample18Value379 * distribution$sample26[((index$i$401 - 0) / 1)][index$sample26$402]);
																							int traceTempVariable$var49$405_1 = distributionTempVariable$var23$404;
																							if(((index$i$401 + 1) == (j + 1))) {
																								{
																									{
																										double cv$temp$86$var50;
																										{
																											double var50 = ((((1.0 * traceTempVariable$v1$371_1) + traceTempVariable$var41$381_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$405_1);
																											cv$temp$86$var50 = var50;
																										}
																										if(((Math.log(cv$probabilitySample26Value403) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var50)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value403) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value403) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var50));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value403) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value403) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var50)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value403);
																									}
																								}
																							}
																						}
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
														int traceTempVariable$var41$411_1 = cv$currentValue;
														if(((index$i$1 + 1) == j)) {
															int traceTempVariable$var49$423_1 = cv$currentValue;
															if(((index$i$1 + 1) == (j + 1))) {
																{
																	{
																		double cv$temp$87$var50;
																		{
																			double var50 = ((((1.0 * v1) + traceTempVariable$var41$411_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$423_1);
																			cv$temp$87$var50 = var50;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var50)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var50));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var50)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
															for(int index$i$424 = 0; index$i$424 < size; index$i$424 += 1) {
																if(!(index$i$424 == index$i$1)) {
																	for(int index$sample26$425 = 0; index$sample26$425 < weightings.length; index$sample26$425 += 1) {
																		int distributionTempVariable$var23$427 = index$sample26$425;
																		double cv$probabilitySample26Value426 = (1.0 * distribution$sample26[((index$i$424 - 0) / 1)][index$sample26$425]);
																		int traceTempVariable$var49$428_1 = distributionTempVariable$var23$427;
																		if(((index$i$424 + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$88$var50;
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$411_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$428_1);
																						cv$temp$88$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample26Value426) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value426) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value426) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value426) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value426) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value426);
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int index$i$412 = 0; index$i$412 < size; index$i$412 += 1) {
															if(!(index$i$412 == index$i$1)) {
																for(int index$sample26$413 = 0; index$sample26$413 < weightings.length; index$sample26$413 += 1) {
																	int distributionTempVariable$var23$415 = index$sample26$413;
																	double cv$probabilitySample26Value414 = (1.0 * distribution$sample26[((index$i$412 - 0) / 1)][index$sample26$413]);
																	int traceTempVariable$var41$416_1 = distributionTempVariable$var23$415;
																	if(((index$i$412 + 1) == j)) {
																		int traceTempVariable$var49$429_1 = cv$currentValue;
																		if(((index$i$1 + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$89$var50;
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$416_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$429_1);
																						cv$temp$89$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample26Value414) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value414) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value414) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value414) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value414) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value414);
																				}
																			}
																		}
																		int traceTempVariable$var49$430_1 = distributionTempVariable$var23$415;
																		if(((index$i$412 + 1) == (j + 1))) {
																			{
																				{
																					double cv$temp$90$var50;
																					{
																						double var50 = ((((1.0 * v1) + traceTempVariable$var41$416_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$430_1);
																						cv$temp$90$var50 = var50;
																					}
																					if(((Math.log(cv$probabilitySample26Value414) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var50)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value414) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value414) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var50));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value414) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value414) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var50)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value414);
																				}
																			}
																		}
																		for(int index$i$431 = 0; index$i$431 < size; index$i$431 += 1) {
																			if((!(index$i$431 == index$i$1) && !(index$i$431 == index$i$412))) {
																				for(int index$sample26$432 = 0; index$sample26$432 < weightings.length; index$sample26$432 += 1) {
																					int distributionTempVariable$var23$434 = index$sample26$432;
																					double cv$probabilitySample26Value433 = (cv$probabilitySample26Value414 * distribution$sample26[((index$i$431 - 0) / 1)][index$sample26$432]);
																					int traceTempVariable$var49$435_1 = distributionTempVariable$var23$434;
																					if(((index$i$431 + 1) == (j + 1))) {
																						{
																							{
																								double cv$temp$91$var50;
																								{
																									double var50 = ((((1.0 * v1) + traceTempVariable$var41$416_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$435_1);
																									cv$temp$91$var50 = var50;
																								}
																								if(((Math.log(cv$probabilitySample26Value433) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var50)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value433) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value433) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var50));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value433) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value433) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var50)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value433);
																							}
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
															for(int index$sample12$407 = 0; index$sample12$407 < weightings.length; index$sample12$407 += 1) {
																int distributionTempVariable$v1$409 = index$sample12$407;
																double cv$probabilitySample12Value408 = (1.0 * distribution$sample12[index$sample12$407]);
																int traceTempVariable$v1$410_1 = distributionTempVariable$v1$409;
																int traceTempVariable$var41$417_1 = cv$currentValue;
																if(((index$i$1 + 1) == j)) {
																	int traceTempVariable$var49$436_1 = cv$currentValue;
																	if(((index$i$1 + 1) == (j + 1))) {
																		{
																			{
																				double cv$temp$92$var50;
																				{
																					double var50 = ((((1.0 * traceTempVariable$v1$410_1) + traceTempVariable$var41$417_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$436_1);
																					cv$temp$92$var50 = var50;
																				}
																				if(((Math.log(cv$probabilitySample12Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var50)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var50));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var50)))) + 1)) + (Math.log(cv$probabilitySample12Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var50)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value408);
																			}
																		}
																	}
																	for(int index$i$437 = 0; index$i$437 < size; index$i$437 += 1) {
																		if(!(index$i$437 == index$i$1)) {
																			for(int index$sample26$438 = 0; index$sample26$438 < weightings.length; index$sample26$438 += 1) {
																				int distributionTempVariable$var23$440 = index$sample26$438;
																				double cv$probabilitySample26Value439 = (cv$probabilitySample12Value408 * distribution$sample26[((index$i$437 - 0) / 1)][index$sample26$438]);
																				int traceTempVariable$var49$441_1 = distributionTempVariable$var23$440;
																				if(((index$i$437 + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$93$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$410_1) + traceTempVariable$var41$417_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$441_1);
																								cv$temp$93$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample26Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value439);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$i$418 = 0; index$i$418 < size; index$i$418 += 1) {
																	if(!(index$i$418 == index$i$1)) {
																		for(int index$sample26$419 = 0; index$sample26$419 < weightings.length; index$sample26$419 += 1) {
																			int distributionTempVariable$var23$421 = index$sample26$419;
																			double cv$probabilitySample26Value420 = (cv$probabilitySample12Value408 * distribution$sample26[((index$i$418 - 0) / 1)][index$sample26$419]);
																			int traceTempVariable$var41$422_1 = distributionTempVariable$var23$421;
																			if(((index$i$418 + 1) == j)) {
																				int traceTempVariable$var49$442_1 = cv$currentValue;
																				if(((index$i$1 + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$94$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$410_1) + traceTempVariable$var41$422_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$442_1);
																								cv$temp$94$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample26Value420) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value420) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value420) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value420) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value420) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value420);
																						}
																					}
																				}
																				int traceTempVariable$var49$443_1 = distributionTempVariable$var23$421;
																				if(((index$i$418 + 1) == (j + 1))) {
																					{
																						{
																							double cv$temp$95$var50;
																							{
																								double var50 = ((((1.0 * traceTempVariable$v1$410_1) + traceTempVariable$var41$422_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$443_1);
																								cv$temp$95$var50 = var50;
																							}
																							if(((Math.log(cv$probabilitySample26Value420) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var50)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value420) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value420) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var50));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value420) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value420) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var50)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value420);
																						}
																					}
																				}
																				for(int index$i$444 = 0; index$i$444 < size; index$i$444 += 1) {
																					if((!(index$i$444 == index$i$1) && !(index$i$444 == index$i$418))) {
																						for(int index$sample26$445 = 0; index$sample26$445 < weightings.length; index$sample26$445 += 1) {
																							int distributionTempVariable$var23$447 = index$sample26$445;
																							double cv$probabilitySample26Value446 = (cv$probabilitySample26Value420 * distribution$sample26[((index$i$444 - 0) / 1)][index$sample26$445]);
																							int traceTempVariable$var49$448_1 = distributionTempVariable$var23$447;
																							if(((index$i$444 + 1) == (j + 1))) {
																								{
																									{
																										double cv$temp$96$var50;
																										{
																											double var50 = ((((1.0 * traceTempVariable$v1$410_1) + traceTempVariable$var41$422_1) + traceTempVariable$var45$7_3) / traceTempVariable$var49$448_1);
																											cv$temp$96$var50 = var50;
																										}
																										if(((Math.log(cv$probabilitySample26Value446) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var50)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value446) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value446) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var50));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value446) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var50)))) + 1)) + (Math.log(cv$probabilitySample26Value446) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var50)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value446);
																									}
																								}
																							}
																						}
																					}
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
		double[] cv$localProbability = distribution$sample26[((i - 0) / 1)];
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
			cv$var9$stateProbabilityGlobal = new double[weightings.length];
		}
		{
			cv$var15$stateProbabilityGlobal = new double[weightings.length];
		}
		{
			int cv$max_j = 0;
			cv$max_j = Math.max(cv$max_j, ((length$value - 0) / 1));
			guard$sample18bernoulli54$global = new boolean[cv$max_j];
		}
		{
			cv$var23$stateProbabilityGlobal = new double[weightings.length];
		}
		{
			int cv$max_j = 0;
			cv$max_j = Math.max(cv$max_j, ((length$value - 0) / 1));
			guard$sample26bernoulli54$global = new boolean[cv$max_j];
		}
	}

	@Override
	public final void allocator() {
		if(!setFlag$v2) {
			{
				v2 = new int[(length$value + 1)];
			}
		}
		if(!setFlag$v3) {
			{
				v3 = new int[(length$value + 1)];
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
			logProbability$var51 = new double[((((length$value - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample55 = new double[((((length$value - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample18)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		for(int i = 0; i < size; i += 1) {
			if(!fixedFlag$sample26)
				v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$, weightings);
		}
		for(int k = 0; k < (size + 1); k += 1) {
			if(!(fixedFlag$sample18 && fixedFlag$sample26))
				v3[k] = v2[k];
		}
		for(int j = 0; j < size; j += 1) {
			if(!fixedFlag$sample55)
				v[j] = DistributionSampling.sampleBernoulli(RNG$, ((((1.0 * v1) + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]));
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		double[] cv$distribution$sample12 = distribution$sample12;
		for(int index$var8 = 0; index$var8 < weightings.length; index$var8 += 1) {
			double cv$value = DistributionSampling.probabilityCategorical(index$var8, weightings);
			if(!fixedFlag$sample12)
				cv$distribution$sample12[index$var8] = cv$value;
		}
		double[] cv$distribution$sample18 = distribution$sample18;
		for(int index$var14 = 0; index$var14 < weightings.length; index$var14 += 1) {
			double cv$value = DistributionSampling.probabilityCategorical(index$var14, weightings);
			if(!fixedFlag$sample18)
				cv$distribution$sample18[index$var14] = cv$value;
		}
		for(int i = 0; i < size; i += 1) {
			double[] cv$distribution$sample26 = distribution$sample26[((i - 0) / 1)];
			for(int index$var22 = 0; index$var22 < weightings.length; index$var22 += 1) {
				double cv$value = DistributionSampling.probabilityCategorical(index$var22, weightings);
				if(!fixedFlag$sample26)
					cv$distribution$sample26[index$var22] = cv$value;
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample18)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		for(int i = 0; i < size; i += 1) {
			if(!fixedFlag$sample26)
				v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$, weightings);
		}
		for(int k = 0; k < (size + 1); k += 1) {
			if(!(fixedFlag$sample18 && fixedFlag$sample26))
				v3[k] = v2[k];
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample12)
				sample12();
			if(!fixedFlag$sample18)
				sample18();
			for(int i = 0; i < size; i += 1) {
				if(!fixedFlag$sample26)
					sample26(i);
			}
		} else {
			for(int i = (size - ((((size - 1) - 0) % 1) + 1)); i >= ((0 - 1) + 1); i -= 1) {
				if(!fixedFlag$sample26)
					sample26(i);
			}
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
		logProbability$v3 = 0.0;
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
			logProbability$var51[((j - 0) / 1)] = 0.0;
		logProbability$v = 0.0;
		if(!fixedProbFlag$sample55) {
			for(int j = 0; j < size; j += 1)
				logProbability$sample55[((j - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample55();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample12();
		logProbabilityDistribution$sample18();
		logProbabilityDistribution$sample26();
		logProbabilityDistribution$sample55();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample12();
		logProbabilityValue$sample18();
		logProbabilityValue$sample26();
		logProbabilityValue$sample55();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample18)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		for(int i = 0; i < size; i += 1) {
			if(!fixedFlag$sample26)
				v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$, weightings);
		}
		for(int k = 0; k < (size + 1); k += 1) {
			if(!(fixedFlag$sample18 && fixedFlag$sample26))
				v3[k] = v2[k];
		}
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
	public final void setIntermediates() {
		for(int k = 0; k < (size + 1); k += 1) {
			if(setFlag$v2)
				v3[k] = v2[k];
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel DistributionTest5(double[] weightings, boolean[] value) {\n    int size = value.length;\n    \n    int v1 = categorical(weightings).sampleDistribution();\n    \n    int[] v2 = new int[size + 1];\n    v2[0] = categorical(weightings).sampleDistribution();\n    for(int i:[0..size))\n        v2[i + 1] = categorical(weightings).sampleDistribution();\n        \n        \n    int[] v3 = new int[size + 1];\n    for(int k:[0..size]) \n        v3[k] = v2[k];\n        \n    boolean[] v = new boolean[size];\n    for(int j:[0..size))\n        v[j] = bernoulli(((1.0*v1) + v2[j] + v3[j+1])/v2[j+1]).sample();\n        \n    v.observe(value);\n}\n";
	}
}