package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DistributionTest6$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements DistributionTest6$CoreInterface {
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
	private boolean fixedProbFlag$sample49 = false;
	private boolean fixedProbFlag$sample5 = false;
	private boolean[] guard$sample11bernoulli48$global;
	private boolean[] guard$sample27bernoulli48$global;
	private int length$value;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$sample27;
	private double[] logProbability$sample49;
	private double logProbability$v;
	private double logProbability$v1;
	private double logProbability$v2;
	private double logProbability$var10;
	private double logProbability$var11;
	private double[] logProbability$var26;
	private double logProbability$var4;
	private double[] logProbability$var48;
	private int size;
	private boolean system$gibbsForward = true;
	private boolean[] v;
	private int v1;
	private int[] v2;
	private boolean[] value;
	private double[] weightings;

	public DistributionTest6$SingleThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample49 = (fixedFlag$sample11 && fixedProbFlag$sample49);
	}

	@Override
	public final boolean get$fixedFlag$sample27() {
		return fixedFlag$sample27;
	}

	@Override
	public final void set$fixedFlag$sample27(boolean cv$value) {
		fixedFlag$sample27 = cv$value;
		fixedProbFlag$sample27 = (fixedFlag$sample27 && fixedProbFlag$sample27);
		fixedProbFlag$sample49 = (fixedFlag$sample27 && fixedProbFlag$sample49);
	}

	@Override
	public final boolean get$fixedFlag$sample5() {
		return fixedFlag$sample5;
	}

	@Override
	public final void set$fixedFlag$sample5(boolean cv$value) {
		fixedFlag$sample5 = cv$value;
		fixedProbFlag$sample5 = (fixedFlag$sample5 && fixedProbFlag$sample5);
		fixedProbFlag$sample49 = (fixedFlag$sample5 && fixedProbFlag$sample49);
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
		fixedProbFlag$sample49 = false;
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
		fixedProbFlag$sample49 = false;
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
			logProbability$var10 = cv$rvAccumulator;
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
					logProbability$var26[((i - 0) / 1)] = cv$sampleAccumulator;
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
			for(int i = 0; i < size; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample27[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var26[((i - 0) / 1)] = cv$rvAccumulator;
			}
			if(fixedFlag$sample27)
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample49() {
		if(!fixedProbFlag$sample49) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = v[j];
					if(fixedFlag$sample5) {
						if(fixedFlag$sample11) {
							if((0 == j)) {
								if((0 == j)) {
									if((0 == j)) {
										{
											double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
											double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
								for(int index$sample11$8 = 0; index$sample11$8 < weightings.length; index$sample11$8 += 1) {
									int distributionTempVariable$var11$10 = index$sample11$8;
									double cv$probabilitySample11Value9 = (1.0 * distribution$sample11[index$sample11$8]);
									if((0 == j)) {
										if((0 == j)) {
											if((0 == j)) {
												{
													double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
													double cv$weightedProbability = (Math.log(cv$probabilitySample11Value9) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
													if((0 == j)) {
														{
															double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample11Value32) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
										if(!true) {
											for(int index$sample11$19 = 0; index$sample11$19 < weightings.length; index$sample11$19 += 1) {
												int distributionTempVariable$var11$21 = index$sample11$19;
												double cv$probabilitySample11Value20 = (cv$probabilitySample11Value9 * distribution$sample11[index$sample11$19]);
												if((0 == j)) {
													if((0 == j)) {
														{
															double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample11Value20) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
													if((0 == j)) {
														{
															double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample11Value20) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
															if((0 == j)) {
																{
																	double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample11Value38) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
					} else {
						if(true) {
							for(int index$sample5$3 = 0; index$sample5$3 < weightings.length; index$sample5$3 += 1) {
								int distributionTempVariable$v1$5 = index$sample5$3;
								double cv$probabilitySample5Value4 = (1.0 * distribution$sample5[index$sample5$3]);
								if(fixedFlag$sample11) {
									if((0 == j)) {
										if((0 == j)) {
											if((0 == j)) {
												{
													double var47 = ((((1.0 * distributionTempVariable$v1$5) + v2[j]) + v2[j]) / v2[j]);
													double cv$weightedProbability = (Math.log(cv$probabilitySample5Value4) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
								} else {
									if(true) {
										for(int index$sample11$13 = 0; index$sample11$13 < weightings.length; index$sample11$13 += 1) {
											int distributionTempVariable$var11$15 = index$sample11$13;
											double cv$probabilitySample11Value14 = (cv$probabilitySample5Value4 * distribution$sample11[index$sample11$13]);
											if((0 == j)) {
												if((0 == j)) {
													if((0 == j)) {
														{
															double var47 = ((((1.0 * distributionTempVariable$v1$5) + v2[j]) + v2[j]) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample11Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
															if((0 == j)) {
																{
																	double var47 = ((((1.0 * distributionTempVariable$v1$5) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample11Value44) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
												if(!true) {
													for(int index$sample11$25 = 0; index$sample11$25 < weightings.length; index$sample11$25 += 1) {
														int distributionTempVariable$var11$27 = index$sample11$25;
														double cv$probabilitySample11Value26 = (cv$probabilitySample11Value14 * distribution$sample11[index$sample11$25]);
														if((0 == j)) {
															if((0 == j)) {
																{
																	double var47 = ((((1.0 * distributionTempVariable$v1$5) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample11Value26) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
															if((0 == j)) {
																{
																	double var47 = ((((1.0 * distributionTempVariable$v1$5) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample11Value26) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
																	if((0 == j)) {
																		{
																			double var47 = ((((1.0 * distributionTempVariable$v1$5) + v2[j]) + v2[j]) / v2[j]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value50) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
					if(fixedFlag$sample5) {
						if(fixedFlag$sample27) {
							for(int i = 0; i < size; i += 1) {
								if(((i + 1) == j)) {
									if(fixedFlag$sample11) {
										if((0 == j)) {
											if((0 == j)) {
												{
													double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
													double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
											for(int index$sample11$71 = 0; index$sample11$71 < weightings.length; index$sample11$71 += 1) {
												int distributionTempVariable$var11$73 = index$sample11$71;
												double cv$probabilitySample11Value72 = (1.0 * distribution$sample11[index$sample11$71]);
												if((0 == j)) {
													if((0 == j)) {
														{
															double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample11Value72) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
													if(!true) {
														for(int index$sample11$92 = 0; index$sample11$92 < weightings.length; index$sample11$92 += 1) {
															int distributionTempVariable$var11$94 = index$sample11$92;
															double cv$probabilitySample11Value93 = (cv$probabilitySample11Value72 * distribution$sample11[index$sample11$92]);
															if((0 == j)) {
																{
																	double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample11Value93) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
						} else {
							for(int i = 0; i < size; i += 1) {
								if(true) {
									for(int index$sample27$60 = 0; index$sample27$60 < weightings.length; index$sample27$60 += 1) {
										int distributionTempVariable$var27$62 = index$sample27$60;
										double cv$probabilitySample27Value61 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$60]);
										if(((i + 1) == j)) {
											if(fixedFlag$sample11) {
												if((0 == j)) {
													if((0 == j)) {
														{
															double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample27Value61) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
											} else {
												if(true) {
													for(int index$sample11$76 = 0; index$sample11$76 < weightings.length; index$sample11$76 += 1) {
														int distributionTempVariable$var11$78 = index$sample11$76;
														double cv$probabilitySample11Value77 = (cv$probabilitySample27Value61 * distribution$sample11[index$sample11$76]);
														if((0 == j)) {
															if((0 == j)) {
																{
																	double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample11Value77) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
															if(!true) {
																for(int index$sample11$98 = 0; index$sample11$98 < weightings.length; index$sample11$98 += 1) {
																	int distributionTempVariable$var11$100 = index$sample11$98;
																	double cv$probabilitySample11Value99 = (cv$probabilitySample11Value77 * distribution$sample11[index$sample11$98]);
																	if((0 == j)) {
																		{
																			double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value99) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
					} else {
						if(true) {
							for(int index$sample5$54 = 0; index$sample5$54 < weightings.length; index$sample5$54 += 1) {
								int distributionTempVariable$v1$56 = index$sample5$54;
								double cv$probabilitySample5Value55 = (1.0 * distribution$sample5[index$sample5$54]);
								if(fixedFlag$sample27) {
									for(int i = 0; i < size; i += 1) {
										if(((i + 1) == j)) {
											if(fixedFlag$sample11) {
												if((0 == j)) {
													if((0 == j)) {
														{
															double var47 = ((((1.0 * distributionTempVariable$v1$56) + v2[j]) + v2[j]) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample5Value55) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
											} else {
												if(true) {
													for(int index$sample11$81 = 0; index$sample11$81 < weightings.length; index$sample11$81 += 1) {
														int distributionTempVariable$var11$83 = index$sample11$81;
														double cv$probabilitySample11Value82 = (cv$probabilitySample5Value55 * distribution$sample11[index$sample11$81]);
														if((0 == j)) {
															if((0 == j)) {
																{
																	double var47 = ((((1.0 * distributionTempVariable$v1$56) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample11Value82) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
															if(!true) {
																for(int index$sample11$104 = 0; index$sample11$104 < weightings.length; index$sample11$104 += 1) {
																	int distributionTempVariable$var11$106 = index$sample11$104;
																	double cv$probabilitySample11Value105 = (cv$probabilitySample11Value82 * distribution$sample11[index$sample11$104]);
																	if((0 == j)) {
																		{
																			double var47 = ((((1.0 * distributionTempVariable$v1$56) + v2[j]) + v2[j]) / v2[j]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value105) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
								} else {
									for(int i = 0; i < size; i += 1) {
										if(true) {
											for(int index$sample27$66 = 0; index$sample27$66 < weightings.length; index$sample27$66 += 1) {
												int distributionTempVariable$var27$68 = index$sample27$66;
												double cv$probabilitySample27Value67 = (cv$probabilitySample5Value55 * distribution$sample27[((i - 0) / 1)][index$sample27$66]);
												if(((i + 1) == j)) {
													if(fixedFlag$sample11) {
														if((0 == j)) {
															if((0 == j)) {
																{
																	double var47 = ((((1.0 * distributionTempVariable$v1$56) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample27Value67) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
													} else {
														if(true) {
															for(int index$sample11$86 = 0; index$sample11$86 < weightings.length; index$sample11$86 += 1) {
																int distributionTempVariable$var11$88 = index$sample11$86;
																double cv$probabilitySample11Value87 = (cv$probabilitySample27Value67 * distribution$sample11[index$sample11$86]);
																if((0 == j)) {
																	if((0 == j)) {
																		{
																			double var47 = ((((1.0 * distributionTempVariable$v1$56) + v2[j]) + v2[j]) / v2[j]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value87) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
																	if(!true) {
																		for(int index$sample11$110 = 0; index$sample11$110 < weightings.length; index$sample11$110 += 1) {
																			int distributionTempVariable$var11$112 = index$sample11$110;
																			double cv$probabilitySample11Value111 = (cv$probabilitySample11Value87 * distribution$sample11[index$sample11$110]);
																			if((0 == j)) {
																				{
																					double var47 = ((((1.0 * distributionTempVariable$v1$56) + v2[j]) + v2[j]) / v2[j]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample11Value111) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
					if(fixedFlag$sample5) {
						if(fixedFlag$sample11) {
							if((0 == j)) {
								if(fixedFlag$sample27) {
									for(int i = 0; i < size; i += 1) {
										if(((i + 1) == j)) {
											if((0 == j)) {
												{
													double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
													double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
											for(int index$sample27$131 = 0; index$sample27$131 < weightings.length; index$sample27$131 += 1) {
												int distributionTempVariable$var27$133 = index$sample27$131;
												double cv$probabilitySample27Value132 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$131]);
												if(((i + 1) == j)) {
													if((0 == j)) {
														{
															double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample27Value132) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
						} else {
							if(true) {
								for(int index$sample11$120 = 0; index$sample11$120 < weightings.length; index$sample11$120 += 1) {
									int distributionTempVariable$var11$122 = index$sample11$120;
									double cv$probabilitySample11Value121 = (1.0 * distribution$sample11[index$sample11$120]);
									if((0 == j)) {
										if(fixedFlag$sample27) {
											for(int i = 0; i < size; i += 1) {
												if(((i + 1) == j)) {
													if((0 == j)) {
														{
															double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample11Value121) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
													if(!true) {
														for(int index$sample11$156 = 0; index$sample11$156 < weightings.length; index$sample11$156 += 1) {
															int distributionTempVariable$var11$158 = index$sample11$156;
															double cv$probabilitySample11Value157 = (cv$probabilitySample11Value121 * distribution$sample11[index$sample11$156]);
															if((0 == j)) {
																{
																	double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample11Value157) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
										} else {
											for(int i = 0; i < size; i += 1) {
												if(true) {
													for(int index$sample27$137 = 0; index$sample27$137 < weightings.length; index$sample27$137 += 1) {
														int distributionTempVariable$var27$139 = index$sample27$137;
														double cv$probabilitySample27Value138 = (cv$probabilitySample11Value121 * distribution$sample27[((i - 0) / 1)][index$sample27$137]);
														if(((i + 1) == j)) {
															if((0 == j)) {
																{
																	double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample27Value138) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
															if(!true) {
																for(int index$sample11$161 = 0; index$sample11$161 < weightings.length; index$sample11$161 += 1) {
																	int distributionTempVariable$var11$163 = index$sample11$161;
																	double cv$probabilitySample11Value162 = (cv$probabilitySample27Value138 * distribution$sample11[index$sample11$161]);
																	if((0 == j)) {
																		{
																			double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value162) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
					} else {
						if(true) {
							for(int index$sample5$115 = 0; index$sample5$115 < weightings.length; index$sample5$115 += 1) {
								int distributionTempVariable$v1$117 = index$sample5$115;
								double cv$probabilitySample5Value116 = (1.0 * distribution$sample5[index$sample5$115]);
								if(fixedFlag$sample11) {
									if((0 == j)) {
										if(fixedFlag$sample27) {
											for(int i = 0; i < size; i += 1) {
												if(((i + 1) == j)) {
													if((0 == j)) {
														{
															double var47 = ((((1.0 * distributionTempVariable$v1$117) + v2[j]) + v2[j]) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample5Value116) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
										} else {
											for(int i = 0; i < size; i += 1) {
												if(true) {
													for(int index$sample27$143 = 0; index$sample27$143 < weightings.length; index$sample27$143 += 1) {
														int distributionTempVariable$var27$145 = index$sample27$143;
														double cv$probabilitySample27Value144 = (cv$probabilitySample5Value116 * distribution$sample27[((i - 0) / 1)][index$sample27$143]);
														if(((i + 1) == j)) {
															if((0 == j)) {
																{
																	double var47 = ((((1.0 * distributionTempVariable$v1$117) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample27Value144) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
								} else {
									if(true) {
										for(int index$sample11$125 = 0; index$sample11$125 < weightings.length; index$sample11$125 += 1) {
											int distributionTempVariable$var11$127 = index$sample11$125;
											double cv$probabilitySample11Value126 = (cv$probabilitySample5Value116 * distribution$sample11[index$sample11$125]);
											if((0 == j)) {
												if(fixedFlag$sample27) {
													for(int i = 0; i < size; i += 1) {
														if(((i + 1) == j)) {
															if((0 == j)) {
																{
																	double var47 = ((((1.0 * distributionTempVariable$v1$117) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample11Value126) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
															if(!true) {
																for(int index$sample11$168 = 0; index$sample11$168 < weightings.length; index$sample11$168 += 1) {
																	int distributionTempVariable$var11$170 = index$sample11$168;
																	double cv$probabilitySample11Value169 = (cv$probabilitySample11Value126 * distribution$sample11[index$sample11$168]);
																	if((0 == j)) {
																		{
																			double var47 = ((((1.0 * distributionTempVariable$v1$117) + v2[j]) + v2[j]) / v2[j]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value169) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
												} else {
													for(int i = 0; i < size; i += 1) {
														if(true) {
															for(int index$sample27$149 = 0; index$sample27$149 < weightings.length; index$sample27$149 += 1) {
																int distributionTempVariable$var27$151 = index$sample27$149;
																double cv$probabilitySample27Value150 = (cv$probabilitySample11Value126 * distribution$sample27[((i - 0) / 1)][index$sample27$149]);
																if(((i + 1) == j)) {
																	if((0 == j)) {
																		{
																			double var47 = ((((1.0 * distributionTempVariable$v1$117) + v2[j]) + v2[j]) / v2[j]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample27Value150) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
																	if(!true) {
																		for(int index$sample11$173 = 0; index$sample11$173 < weightings.length; index$sample11$173 += 1) {
																			int distributionTempVariable$var11$175 = index$sample11$173;
																			double cv$probabilitySample11Value174 = (cv$probabilitySample27Value150 * distribution$sample11[index$sample11$173]);
																			if((0 == j)) {
																				{
																					double var47 = ((((1.0 * distributionTempVariable$v1$117) + v2[j]) + v2[j]) / v2[j]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample11Value174) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
					if(fixedFlag$sample5) {
						if(fixedFlag$sample27) {
							for(int i = 0; i < size; i += 1) {
								if(((i + 1) == j)) {
									for(int index$i$194_1 = 0; index$i$194_1 < size; index$i$194_1 += 1) {
										if(((index$i$194_1 + 1) == j)) {
											if(fixedFlag$sample11) {
												if((0 == j)) {
													{
														double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
														double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
														if((0 == j)) {
															{
																double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																double cv$weightedProbability = (Math.log(cv$probabilitySample11Value210) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
						} else {
							for(int i = 0; i < size; i += 1) {
								if(true) {
									for(int index$sample27$184 = 0; index$sample27$184 < weightings.length; index$sample27$184 += 1) {
										int distributionTempVariable$var27$186 = index$sample27$184;
										double cv$probabilitySample27Value185 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$184]);
										if(((i + 1) == j)) {
											if(((i + 1) == j)) {
												if(fixedFlag$sample11) {
													if((0 == j)) {
														{
															double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample27Value185) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
															if((0 == j)) {
																{
																	double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample11Value215) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
											for(int index$i$196 = 0; index$i$196 < size; index$i$196 += 1) {
												if(!(index$i$196 == i)) {
													for(int index$sample27$197 = 0; index$sample27$197 < weightings.length; index$sample27$197 += 1) {
														int distributionTempVariable$var27$199 = index$sample27$197;
														double cv$probabilitySample27Value198 = (cv$probabilitySample27Value185 * distribution$sample27[((index$i$196 - 0) / 1)][index$sample27$197]);
														if(((index$i$196 + 1) == j)) {
															if(fixedFlag$sample11) {
																if((0 == j)) {
																	{
																		double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample27Value198) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
																		if((0 == j)) {
																			{
																				double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample11Value220) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
					} else {
						if(true) {
							for(int index$sample5$178 = 0; index$sample5$178 < weightings.length; index$sample5$178 += 1) {
								int distributionTempVariable$v1$180 = index$sample5$178;
								double cv$probabilitySample5Value179 = (1.0 * distribution$sample5[index$sample5$178]);
								if(fixedFlag$sample27) {
									for(int i = 0; i < size; i += 1) {
										if(((i + 1) == j)) {
											for(int index$i$201_1 = 0; index$i$201_1 < size; index$i$201_1 += 1) {
												if(((index$i$201_1 + 1) == j)) {
													if(fixedFlag$sample11) {
														if((0 == j)) {
															{
																double var47 = ((((1.0 * distributionTempVariable$v1$180) + v2[j]) + v2[j]) / v2[j]);
																double cv$weightedProbability = (Math.log(cv$probabilitySample5Value179) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
																if((0 == j)) {
																	{
																		double var47 = ((((1.0 * distributionTempVariable$v1$180) + v2[j]) + v2[j]) / v2[j]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample11Value225) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
								} else {
									for(int i = 0; i < size; i += 1) {
										if(true) {
											for(int index$sample27$190 = 0; index$sample27$190 < weightings.length; index$sample27$190 += 1) {
												int distributionTempVariable$var27$192 = index$sample27$190;
												double cv$probabilitySample27Value191 = (cv$probabilitySample5Value179 * distribution$sample27[((i - 0) / 1)][index$sample27$190]);
												if(((i + 1) == j)) {
													if(((i + 1) == j)) {
														if(fixedFlag$sample11) {
															if((0 == j)) {
																{
																	double var47 = ((((1.0 * distributionTempVariable$v1$180) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample27Value191) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
																	if((0 == j)) {
																		{
																			double var47 = ((((1.0 * distributionTempVariable$v1$180) + v2[j]) + v2[j]) / v2[j]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value230) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
													for(int index$i$203 = 0; index$i$203 < size; index$i$203 += 1) {
														if(!(index$i$203 == i)) {
															for(int index$sample27$204 = 0; index$sample27$204 < weightings.length; index$sample27$204 += 1) {
																int distributionTempVariable$var27$206 = index$sample27$204;
																double cv$probabilitySample27Value205 = (cv$probabilitySample27Value191 * distribution$sample27[((index$i$203 - 0) / 1)][index$sample27$204]);
																if(((index$i$203 + 1) == j)) {
																	if(fixedFlag$sample11) {
																		if((0 == j)) {
																			{
																				double var47 = ((((1.0 * distributionTempVariable$v1$180) + v2[j]) + v2[j]) / v2[j]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample27Value205) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
																				if((0 == j)) {
																					{
																						double var47 = ((((1.0 * distributionTempVariable$v1$180) + v2[j]) + v2[j]) / v2[j]);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample11Value235) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
					if(fixedFlag$sample5) {
						if(fixedFlag$sample11) {
							if((0 == j)) {
								if((0 == j)) {
									if(fixedFlag$sample27) {
										for(int i = 0; i < size; i += 1) {
											if(((i + 1) == j)) {
												{
													double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
													double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
													if(((i + 1) == j)) {
														{
															double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample27Value268) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
						} else {
							if(true) {
								for(int index$sample11$244 = 0; index$sample11$244 < weightings.length; index$sample11$244 += 1) {
									int distributionTempVariable$var11$246 = index$sample11$244;
									double cv$probabilitySample11Value245 = (1.0 * distribution$sample11[index$sample11$244]);
									if((0 == j)) {
										if((0 == j)) {
											if(fixedFlag$sample27) {
												for(int i = 0; i < size; i += 1) {
													if(((i + 1) == j)) {
														{
															double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample11Value245) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
															if(((i + 1) == j)) {
																{
																	double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample27Value274) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
										if(!true) {
											for(int index$sample11$255 = 0; index$sample11$255 < weightings.length; index$sample11$255 += 1) {
												int distributionTempVariable$var11$257 = index$sample11$255;
												double cv$probabilitySample11Value256 = (cv$probabilitySample11Value245 * distribution$sample11[index$sample11$255]);
												if((0 == j)) {
													if(fixedFlag$sample27) {
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == j)) {
																{
																	double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample11Value256) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
																	if(((i + 1) == j)) {
																		{
																			double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample27Value280) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
					} else {
						if(true) {
							for(int index$sample5$239 = 0; index$sample5$239 < weightings.length; index$sample5$239 += 1) {
								int distributionTempVariable$v1$241 = index$sample5$239;
								double cv$probabilitySample5Value240 = (1.0 * distribution$sample5[index$sample5$239]);
								if(fixedFlag$sample11) {
									if((0 == j)) {
										if((0 == j)) {
											if(fixedFlag$sample27) {
												for(int i = 0; i < size; i += 1) {
													if(((i + 1) == j)) {
														{
															double var47 = ((((1.0 * distributionTempVariable$v1$241) + v2[j]) + v2[j]) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample5Value240) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
															if(((i + 1) == j)) {
																{
																	double var47 = ((((1.0 * distributionTempVariable$v1$241) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample27Value286) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
								} else {
									if(true) {
										for(int index$sample11$249 = 0; index$sample11$249 < weightings.length; index$sample11$249 += 1) {
											int distributionTempVariable$var11$251 = index$sample11$249;
											double cv$probabilitySample11Value250 = (cv$probabilitySample5Value240 * distribution$sample11[index$sample11$249]);
											if((0 == j)) {
												if((0 == j)) {
													if(fixedFlag$sample27) {
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == j)) {
																{
																	double var47 = ((((1.0 * distributionTempVariable$v1$241) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample11Value250) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
																	if(((i + 1) == j)) {
																		{
																			double var47 = ((((1.0 * distributionTempVariable$v1$241) + v2[j]) + v2[j]) / v2[j]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample27Value292) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
												if(!true) {
													for(int index$sample11$261 = 0; index$sample11$261 < weightings.length; index$sample11$261 += 1) {
														int distributionTempVariable$var11$263 = index$sample11$261;
														double cv$probabilitySample11Value262 = (cv$probabilitySample11Value250 * distribution$sample11[index$sample11$261]);
														if((0 == j)) {
															if(fixedFlag$sample27) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == j)) {
																		{
																			double var47 = ((((1.0 * distributionTempVariable$v1$241) + v2[j]) + v2[j]) / v2[j]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value262) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
																			if(((i + 1) == j)) {
																				{
																					double var47 = ((((1.0 * distributionTempVariable$v1$241) + v2[j]) + v2[j]) / v2[j]);
																					double cv$weightedProbability = (Math.log(cv$probabilitySample27Value298) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
					if(fixedFlag$sample5) {
						if(fixedFlag$sample27) {
							for(int i = 0; i < size; i += 1) {
								if(((i + 1) == j)) {
									if(fixedFlag$sample11) {
										if((0 == j)) {
											for(int index$i$338_1 = 0; index$i$338_1 < size; index$i$338_1 += 1) {
												if(((index$i$338_1 + 1) == j)) {
													{
														double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
														double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
											for(int index$sample11$319 = 0; index$sample11$319 < weightings.length; index$sample11$319 += 1) {
												int distributionTempVariable$var11$321 = index$sample11$319;
												double cv$probabilitySample11Value320 = (1.0 * distribution$sample11[index$sample11$319]);
												if((0 == j)) {
													for(int index$i$339_1 = 0; index$i$339_1 < size; index$i$339_1 += 1) {
														if(((index$i$339_1 + 1) == j)) {
															{
																double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																double cv$weightedProbability = (Math.log(cv$probabilitySample11Value320) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
						} else {
							for(int i = 0; i < size; i += 1) {
								if(true) {
									for(int index$sample27$308 = 0; index$sample27$308 < weightings.length; index$sample27$308 += 1) {
										int distributionTempVariable$var27$310 = index$sample27$308;
										double cv$probabilitySample27Value309 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$308]);
										if(((i + 1) == j)) {
											if(fixedFlag$sample11) {
												if((0 == j)) {
													if(((i + 1) == j)) {
														{
															double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample27Value309) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
													for(int index$i$341 = 0; index$i$341 < size; index$i$341 += 1) {
														if(!(index$i$341 == i)) {
															for(int index$sample27$342 = 0; index$sample27$342 < weightings.length; index$sample27$342 += 1) {
																int distributionTempVariable$var27$344 = index$sample27$342;
																double cv$probabilitySample27Value343 = (cv$probabilitySample27Value309 * distribution$sample27[((index$i$341 - 0) / 1)][index$sample27$342]);
																if(((index$i$341 + 1) == j)) {
																	{
																		double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample27Value343) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
											} else {
												if(true) {
													for(int index$sample11$324 = 0; index$sample11$324 < weightings.length; index$sample11$324 += 1) {
														int distributionTempVariable$var11$326 = index$sample11$324;
														double cv$probabilitySample11Value325 = (cv$probabilitySample27Value309 * distribution$sample11[index$sample11$324]);
														if((0 == j)) {
															if(((i + 1) == j)) {
																{
																	double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample11Value325) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
															for(int index$i$347 = 0; index$i$347 < size; index$i$347 += 1) {
																if(!(index$i$347 == i)) {
																	for(int index$sample27$348 = 0; index$sample27$348 < weightings.length; index$sample27$348 += 1) {
																		int distributionTempVariable$var27$350 = index$sample27$348;
																		double cv$probabilitySample27Value349 = (cv$probabilitySample11Value325 * distribution$sample27[((index$i$347 - 0) / 1)][index$sample27$348]);
																		if(((index$i$347 + 1) == j)) {
																			{
																				double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample27Value349) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
					} else {
						if(true) {
							for(int index$sample5$302 = 0; index$sample5$302 < weightings.length; index$sample5$302 += 1) {
								int distributionTempVariable$v1$304 = index$sample5$302;
								double cv$probabilitySample5Value303 = (1.0 * distribution$sample5[index$sample5$302]);
								if(fixedFlag$sample27) {
									for(int i = 0; i < size; i += 1) {
										if(((i + 1) == j)) {
											if(fixedFlag$sample11) {
												if((0 == j)) {
													for(int index$i$352_1 = 0; index$i$352_1 < size; index$i$352_1 += 1) {
														if(((index$i$352_1 + 1) == j)) {
															{
																double var47 = ((((1.0 * distributionTempVariable$v1$304) + v2[j]) + v2[j]) / v2[j]);
																double cv$weightedProbability = (Math.log(cv$probabilitySample5Value303) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
											} else {
												if(true) {
													for(int index$sample11$329 = 0; index$sample11$329 < weightings.length; index$sample11$329 += 1) {
														int distributionTempVariable$var11$331 = index$sample11$329;
														double cv$probabilitySample11Value330 = (cv$probabilitySample5Value303 * distribution$sample11[index$sample11$329]);
														if((0 == j)) {
															for(int index$i$353_1 = 0; index$i$353_1 < size; index$i$353_1 += 1) {
																if(((index$i$353_1 + 1) == j)) {
																	{
																		double var47 = ((((1.0 * distributionTempVariable$v1$304) + v2[j]) + v2[j]) / v2[j]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample11Value330) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
								} else {
									for(int i = 0; i < size; i += 1) {
										if(true) {
											for(int index$sample27$314 = 0; index$sample27$314 < weightings.length; index$sample27$314 += 1) {
												int distributionTempVariable$var27$316 = index$sample27$314;
												double cv$probabilitySample27Value315 = (cv$probabilitySample5Value303 * distribution$sample27[((i - 0) / 1)][index$sample27$314]);
												if(((i + 1) == j)) {
													if(fixedFlag$sample11) {
														if((0 == j)) {
															if(((i + 1) == j)) {
																{
																	double var47 = ((((1.0 * distributionTempVariable$v1$304) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample27Value315) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
															for(int index$i$355 = 0; index$i$355 < size; index$i$355 += 1) {
																if(!(index$i$355 == i)) {
																	for(int index$sample27$356 = 0; index$sample27$356 < weightings.length; index$sample27$356 += 1) {
																		int distributionTempVariable$var27$358 = index$sample27$356;
																		double cv$probabilitySample27Value357 = (cv$probabilitySample27Value315 * distribution$sample27[((index$i$355 - 0) / 1)][index$sample27$356]);
																		if(((index$i$355 + 1) == j)) {
																			{
																				double var47 = ((((1.0 * distributionTempVariable$v1$304) + v2[j]) + v2[j]) / v2[j]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample27Value357) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
													} else {
														if(true) {
															for(int index$sample11$334 = 0; index$sample11$334 < weightings.length; index$sample11$334 += 1) {
																int distributionTempVariable$var11$336 = index$sample11$334;
																double cv$probabilitySample11Value335 = (cv$probabilitySample27Value315 * distribution$sample11[index$sample11$334]);
																if((0 == j)) {
																	if(((i + 1) == j)) {
																		{
																			double var47 = ((((1.0 * distributionTempVariable$v1$304) + v2[j]) + v2[j]) / v2[j]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample11Value335) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
																	for(int index$i$361 = 0; index$i$361 < size; index$i$361 += 1) {
																		if(!(index$i$361 == i)) {
																			for(int index$sample27$362 = 0; index$sample27$362 < weightings.length; index$sample27$362 += 1) {
																				int distributionTempVariable$var27$364 = index$sample27$362;
																				double cv$probabilitySample27Value363 = (cv$probabilitySample11Value335 * distribution$sample27[((index$i$361 - 0) / 1)][index$sample27$362]);
																				if(((index$i$361 + 1) == j)) {
																					{
																						double var47 = ((((1.0 * distributionTempVariable$v1$304) + v2[j]) + v2[j]) / v2[j]);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample27Value363) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
					if(fixedFlag$sample5) {
						if(fixedFlag$sample11) {
							if((0 == j)) {
								if(fixedFlag$sample27) {
									for(int i = 0; i < size; i += 1) {
										if(((i + 1) == j)) {
											for(int index$i$405_1 = 0; index$i$405_1 < size; index$i$405_1 += 1) {
												if(((index$i$405_1 + 1) == j)) {
													{
														double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
														double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
											for(int index$sample27$383 = 0; index$sample27$383 < weightings.length; index$sample27$383 += 1) {
												int distributionTempVariable$var27$385 = index$sample27$383;
												double cv$probabilitySample27Value384 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$383]);
												if(((i + 1) == j)) {
													if(((i + 1) == j)) {
														{
															double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample27Value384) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
													for(int index$i$407 = 0; index$i$407 < size; index$i$407 += 1) {
														if(!(index$i$407 == i)) {
															for(int index$sample27$408 = 0; index$sample27$408 < weightings.length; index$sample27$408 += 1) {
																int distributionTempVariable$var27$410 = index$sample27$408;
																double cv$probabilitySample27Value409 = (cv$probabilitySample27Value384 * distribution$sample27[((index$i$407 - 0) / 1)][index$sample27$408]);
																if(((index$i$407 + 1) == j)) {
																	{
																		double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample27Value409) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
						} else {
							if(true) {
								for(int index$sample11$372 = 0; index$sample11$372 < weightings.length; index$sample11$372 += 1) {
									int distributionTempVariable$var11$374 = index$sample11$372;
									double cv$probabilitySample11Value373 = (1.0 * distribution$sample11[index$sample11$372]);
									if((0 == j)) {
										if(fixedFlag$sample27) {
											for(int i = 0; i < size; i += 1) {
												if(((i + 1) == j)) {
													for(int index$i$412_1 = 0; index$i$412_1 < size; index$i$412_1 += 1) {
														if(((index$i$412_1 + 1) == j)) {
															{
																double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																double cv$weightedProbability = (Math.log(cv$probabilitySample11Value373) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
										} else {
											for(int i = 0; i < size; i += 1) {
												if(true) {
													for(int index$sample27$389 = 0; index$sample27$389 < weightings.length; index$sample27$389 += 1) {
														int distributionTempVariable$var27$391 = index$sample27$389;
														double cv$probabilitySample27Value390 = (cv$probabilitySample11Value373 * distribution$sample27[((i - 0) / 1)][index$sample27$389]);
														if(((i + 1) == j)) {
															if(((i + 1) == j)) {
																{
																	double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample27Value390) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
															for(int index$i$414 = 0; index$i$414 < size; index$i$414 += 1) {
																if(!(index$i$414 == i)) {
																	for(int index$sample27$415 = 0; index$sample27$415 < weightings.length; index$sample27$415 += 1) {
																		int distributionTempVariable$var27$417 = index$sample27$415;
																		double cv$probabilitySample27Value416 = (cv$probabilitySample27Value390 * distribution$sample27[((index$i$414 - 0) / 1)][index$sample27$415]);
																		if(((index$i$414 + 1) == j)) {
																			{
																				double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample27Value416) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
					} else {
						if(true) {
							for(int index$sample5$367 = 0; index$sample5$367 < weightings.length; index$sample5$367 += 1) {
								int distributionTempVariable$v1$369 = index$sample5$367;
								double cv$probabilitySample5Value368 = (1.0 * distribution$sample5[index$sample5$367]);
								if(fixedFlag$sample11) {
									if((0 == j)) {
										if(fixedFlag$sample27) {
											for(int i = 0; i < size; i += 1) {
												if(((i + 1) == j)) {
													for(int index$i$419_1 = 0; index$i$419_1 < size; index$i$419_1 += 1) {
														if(((index$i$419_1 + 1) == j)) {
															{
																double var47 = ((((1.0 * distributionTempVariable$v1$369) + v2[j]) + v2[j]) / v2[j]);
																double cv$weightedProbability = (Math.log(cv$probabilitySample5Value368) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
										} else {
											for(int i = 0; i < size; i += 1) {
												if(true) {
													for(int index$sample27$395 = 0; index$sample27$395 < weightings.length; index$sample27$395 += 1) {
														int distributionTempVariable$var27$397 = index$sample27$395;
														double cv$probabilitySample27Value396 = (cv$probabilitySample5Value368 * distribution$sample27[((i - 0) / 1)][index$sample27$395]);
														if(((i + 1) == j)) {
															if(((i + 1) == j)) {
																{
																	double var47 = ((((1.0 * distributionTempVariable$v1$369) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample27Value396) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
															for(int index$i$421 = 0; index$i$421 < size; index$i$421 += 1) {
																if(!(index$i$421 == i)) {
																	for(int index$sample27$422 = 0; index$sample27$422 < weightings.length; index$sample27$422 += 1) {
																		int distributionTempVariable$var27$424 = index$sample27$422;
																		double cv$probabilitySample27Value423 = (cv$probabilitySample27Value396 * distribution$sample27[((index$i$421 - 0) / 1)][index$sample27$422]);
																		if(((index$i$421 + 1) == j)) {
																			{
																				double var47 = ((((1.0 * distributionTempVariable$v1$369) + v2[j]) + v2[j]) / v2[j]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample27Value423) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
								} else {
									if(true) {
										for(int index$sample11$377 = 0; index$sample11$377 < weightings.length; index$sample11$377 += 1) {
											int distributionTempVariable$var11$379 = index$sample11$377;
											double cv$probabilitySample11Value378 = (cv$probabilitySample5Value368 * distribution$sample11[index$sample11$377]);
											if((0 == j)) {
												if(fixedFlag$sample27) {
													for(int i = 0; i < size; i += 1) {
														if(((i + 1) == j)) {
															for(int index$i$426_1 = 0; index$i$426_1 < size; index$i$426_1 += 1) {
																if(((index$i$426_1 + 1) == j)) {
																	{
																		double var47 = ((((1.0 * distributionTempVariable$v1$369) + v2[j]) + v2[j]) / v2[j]);
																		double cv$weightedProbability = (Math.log(cv$probabilitySample11Value378) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
												} else {
													for(int i = 0; i < size; i += 1) {
														if(true) {
															for(int index$sample27$401 = 0; index$sample27$401 < weightings.length; index$sample27$401 += 1) {
																int distributionTempVariable$var27$403 = index$sample27$401;
																double cv$probabilitySample27Value402 = (cv$probabilitySample11Value378 * distribution$sample27[((i - 0) / 1)][index$sample27$401]);
																if(((i + 1) == j)) {
																	if(((i + 1) == j)) {
																		{
																			double var47 = ((((1.0 * distributionTempVariable$v1$369) + v2[j]) + v2[j]) / v2[j]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample27Value402) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
																	for(int index$i$428 = 0; index$i$428 < size; index$i$428 += 1) {
																		if(!(index$i$428 == i)) {
																			for(int index$sample27$429 = 0; index$sample27$429 < weightings.length; index$sample27$429 += 1) {
																				int distributionTempVariable$var27$431 = index$sample27$429;
																				double cv$probabilitySample27Value430 = (cv$probabilitySample27Value402 * distribution$sample27[((index$i$428 - 0) / 1)][index$sample27$429]);
																				if(((index$i$428 + 1) == j)) {
																					{
																						double var47 = ((((1.0 * distributionTempVariable$v1$369) + v2[j]) + v2[j]) / v2[j]);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample27Value430) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
					if(fixedFlag$sample5) {
						if(fixedFlag$sample27) {
							for(int i = 0; i < size; i += 1) {
								if(((i + 1) == j)) {
									for(int index$i$450_1 = 0; index$i$450_1 < size; index$i$450_1 += 1) {
										if(((index$i$450_1 + 1) == j)) {
											for(int index$i$464_1 = 0; index$i$464_1 < size; index$i$464_1 += 1) {
												if(((index$i$464_1 + 1) == j)) {
													{
														double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
														double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
									for(int index$sample27$440 = 0; index$sample27$440 < weightings.length; index$sample27$440 += 1) {
										int distributionTempVariable$var27$442 = index$sample27$440;
										double cv$probabilitySample27Value441 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$440]);
										if(((i + 1) == j)) {
											if(((i + 1) == j)) {
												if(((i + 1) == j)) {
													{
														double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
														double cv$weightedProbability = (Math.log(cv$probabilitySample27Value441) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
															if(((index$i$466 + 1) == j)) {
																{
																	double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample27Value468) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
											for(int index$i$452 = 0; index$i$452 < size; index$i$452 += 1) {
												if(!(index$i$452 == i)) {
													for(int index$sample27$453 = 0; index$sample27$453 < weightings.length; index$sample27$453 += 1) {
														int distributionTempVariable$var27$455 = index$sample27$453;
														double cv$probabilitySample27Value454 = (cv$probabilitySample27Value441 * distribution$sample27[((index$i$452 - 0) / 1)][index$sample27$453]);
														if(((index$i$452 + 1) == j)) {
															if(((i + 1) == j)) {
																{
																	double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample27Value454) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
															if(((index$i$452 + 1) == j)) {
																{
																	double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample27Value454) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
																		if(((index$i$473 + 1) == j)) {
																			{
																				double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
																				double cv$weightedProbability = (Math.log(cv$probabilitySample27Value475) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
					} else {
						if(true) {
							for(int index$sample5$434 = 0; index$sample5$434 < weightings.length; index$sample5$434 += 1) {
								int distributionTempVariable$v1$436 = index$sample5$434;
								double cv$probabilitySample5Value435 = (1.0 * distribution$sample5[index$sample5$434]);
								if(fixedFlag$sample27) {
									for(int i = 0; i < size; i += 1) {
										if(((i + 1) == j)) {
											for(int index$i$457_1 = 0; index$i$457_1 < size; index$i$457_1 += 1) {
												if(((index$i$457_1 + 1) == j)) {
													for(int index$i$478_1 = 0; index$i$478_1 < size; index$i$478_1 += 1) {
														if(((index$i$478_1 + 1) == j)) {
															{
																double var47 = ((((1.0 * distributionTempVariable$v1$436) + v2[j]) + v2[j]) / v2[j]);
																double cv$weightedProbability = (Math.log(cv$probabilitySample5Value435) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
								} else {
									for(int i = 0; i < size; i += 1) {
										if(true) {
											for(int index$sample27$446 = 0; index$sample27$446 < weightings.length; index$sample27$446 += 1) {
												int distributionTempVariable$var27$448 = index$sample27$446;
												double cv$probabilitySample27Value447 = (cv$probabilitySample5Value435 * distribution$sample27[((i - 0) / 1)][index$sample27$446]);
												if(((i + 1) == j)) {
													if(((i + 1) == j)) {
														if(((i + 1) == j)) {
															{
																double var47 = ((((1.0 * distributionTempVariable$v1$436) + v2[j]) + v2[j]) / v2[j]);
																double cv$weightedProbability = (Math.log(cv$probabilitySample27Value447) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
																	if(((index$i$480 + 1) == j)) {
																		{
																			double var47 = ((((1.0 * distributionTempVariable$v1$436) + v2[j]) + v2[j]) / v2[j]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample27Value482) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
													for(int index$i$459 = 0; index$i$459 < size; index$i$459 += 1) {
														if(!(index$i$459 == i)) {
															for(int index$sample27$460 = 0; index$sample27$460 < weightings.length; index$sample27$460 += 1) {
																int distributionTempVariable$var27$462 = index$sample27$460;
																double cv$probabilitySample27Value461 = (cv$probabilitySample27Value447 * distribution$sample27[((index$i$459 - 0) / 1)][index$sample27$460]);
																if(((index$i$459 + 1) == j)) {
																	if(((i + 1) == j)) {
																		{
																			double var47 = ((((1.0 * distributionTempVariable$v1$436) + v2[j]) + v2[j]) / v2[j]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample27Value461) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
																	if(((index$i$459 + 1) == j)) {
																		{
																			double var47 = ((((1.0 * distributionTempVariable$v1$436) + v2[j]) + v2[j]) / v2[j]);
																			double cv$weightedProbability = (Math.log(cv$probabilitySample27Value461) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
																				if(((index$i$487 + 1) == j)) {
																					{
																						double var47 = ((((1.0 * distributionTempVariable$v1$436) + v2[j]) + v2[j]) / v2[j]);
																						double cv$weightedProbability = (Math.log(cv$probabilitySample27Value489) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var48[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample49[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample49 = ((fixedFlag$sample5 && fixedFlag$sample11) && fixedFlag$sample27);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample49[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var48[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
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
			logProbability$var10 = cv$rvAccumulator;
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample11)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample27() {
		if(!fixedProbFlag$sample27) {
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
				logProbability$var26[((i - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample27[((i - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample27 = fixedFlag$sample27;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample27[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var26[((i - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample49() {
		if(!fixedProbFlag$sample49) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = v[j];
					{
						{
							double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]);
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var47));
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
				logProbability$var48[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample49[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample49 = ((fixedFlag$sample5 && fixedFlag$sample11) && fixedFlag$sample27);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample49[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var48[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
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
					int cv$temp$1$$var420;
					{
						cv$temp$1$$var420 = weightings.length;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var420))?Math.log(cv$temp$0$weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							boolean[] guard$sample11bernoulli48 = guard$sample11bernoulli48$global;
							for(int j = 0; j < size; j += 1) {
								if((0 == j))
									guard$sample11bernoulli48[((j - 0) / 1)] = false;
							}
							for(int j = 0; j < size; j += 1) {
								if((0 == j))
									guard$sample11bernoulli48[((j - 0) / 1)] = false;
							}
							for(int j = 0; j < size; j += 1) {
								if((0 == j))
									guard$sample11bernoulli48[((j - 0) / 1)] = false;
							}
							int traceTempVariable$var42$4_1 = cv$currentValue;
							for(int j = 0; j < size; j += 1) {
								if((0 == j)) {
									if(!guard$sample11bernoulli48[((j - 0) / 1)]) {
										guard$sample11bernoulli48[((j - 0) / 1)] = true;
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if(fixedFlag$sample5) {
													int traceTempVariable$var44$15_1 = cv$currentValue;
													if((0 == j)) {
														int traceTempVariable$var46$25_1 = cv$currentValue;
														if((0 == j)) {
															{
																{
																	double cv$temp$2$var47;
																	{
																		double var47 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var44$15_1) / traceTempVariable$var46$25_1);
																		cv$temp$2$var47 = var47;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var47)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var47));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var47)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
														if(!true) {
															for(int index$sample11$26 = 0; index$sample11$26 < weightings.length; index$sample11$26 += 1) {
																int distributionTempVariable$var11$28 = index$sample11$26;
																double cv$probabilitySample11Value27 = (1.0 * distribution$sample11[index$sample11$26]);
																int traceTempVariable$var46$29_1 = cv$currentValue;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$3$var47;
																			{
																				double var47 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var44$15_1) / traceTempVariable$var46$29_1);
																				cv$temp$3$var47 = var47;
																			}
																			if(((Math.log(cv$probabilitySample11Value27) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value27) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value27) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value27) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value27) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var47)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value27);
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
															if((0 == j)) {
																int traceTempVariable$var46$30_1 = cv$currentValue;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$4$var47;
																			{
																				double var47 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var44$19_1) / traceTempVariable$var46$30_1);
																				cv$temp$4$var47 = var47;
																			}
																			if(((Math.log(cv$probabilitySample11Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var47)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value17);
																		}
																	}
																}
																int traceTempVariable$var46$31_1 = cv$currentValue;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$5$var47;
																			{
																				double var47 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var44$19_1) / traceTempVariable$var46$31_1);
																				cv$temp$5$var47 = var47;
																			}
																			if(((Math.log(cv$probabilitySample11Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var47)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value17);
																		}
																	}
																}
																if(!true) {
																	for(int index$sample11$32 = 0; index$sample11$32 < weightings.length; index$sample11$32 += 1) {
																		int distributionTempVariable$var11$34 = index$sample11$32;
																		double cv$probabilitySample11Value33 = (cv$probabilitySample11Value17 * distribution$sample11[index$sample11$32]);
																		int traceTempVariable$var46$35_1 = cv$currentValue;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$6$var47;
																					{
																						double var47 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var44$19_1) / traceTempVariable$var46$35_1);
																						cv$temp$6$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample11Value33) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value33) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value33) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value33) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value33) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var47)));
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
												} else {
													if(true) {
														for(int index$sample5$11 = 0; index$sample5$11 < weightings.length; index$sample5$11 += 1) {
															int distributionTempVariable$v1$13 = index$sample5$11;
															double cv$probabilitySample5Value12 = (1.0 * distribution$sample5[index$sample5$11]);
															int traceTempVariable$var44$20_1 = cv$currentValue;
															if((0 == j)) {
																int traceTempVariable$var46$36_1 = cv$currentValue;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$7$var47;
																			{
																				double var47 = ((((1.0 * distributionTempVariable$v1$13) + traceTempVariable$var42$4_1) + traceTempVariable$var44$20_1) / traceTempVariable$var46$36_1);
																				cv$temp$7$var47 = var47;
																			}
																			if(((Math.log(cv$probabilitySample5Value12) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value12) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value12) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value12) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value12) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var47)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value12);
																		}
																	}
																}
																if(!true) {
																	for(int index$sample11$37 = 0; index$sample11$37 < weightings.length; index$sample11$37 += 1) {
																		int distributionTempVariable$var11$39 = index$sample11$37;
																		double cv$probabilitySample11Value38 = (cv$probabilitySample5Value12 * distribution$sample11[index$sample11$37]);
																		int traceTempVariable$var46$40_1 = cv$currentValue;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$8$var47;
																					{
																						double var47 = ((((1.0 * distributionTempVariable$v1$13) + traceTempVariable$var42$4_1) + traceTempVariable$var44$20_1) / traceTempVariable$var46$40_1);
																						cv$temp$8$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample11Value38) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value38) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value38) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value38) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value38) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value38);
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
																	if((0 == j)) {
																		int traceTempVariable$var46$41_1 = cv$currentValue;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$9$var47;
																					{
																						double var47 = ((((1.0 * distributionTempVariable$v1$13) + traceTempVariable$var42$4_1) + traceTempVariable$var44$24_1) / traceTempVariable$var46$41_1);
																						cv$temp$9$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample11Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value22);
																				}
																			}
																		}
																		int traceTempVariable$var46$42_1 = cv$currentValue;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$10$var47;
																					{
																						double var47 = ((((1.0 * distributionTempVariable$v1$13) + traceTempVariable$var42$4_1) + traceTempVariable$var44$24_1) / traceTempVariable$var46$42_1);
																						cv$temp$10$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample11Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value22) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value22);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$43 = 0; index$sample11$43 < weightings.length; index$sample11$43 += 1) {
																				int distributionTempVariable$var11$45 = index$sample11$43;
																				double cv$probabilitySample11Value44 = (cv$probabilitySample11Value22 * distribution$sample11[index$sample11$43]);
																				int traceTempVariable$var46$46_1 = cv$currentValue;
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$11$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$13) + traceTempVariable$var42$4_1) + traceTempVariable$var44$24_1) / traceTempVariable$var46$46_1);
																								cv$temp$11$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample11Value44) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value44) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value44) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value44) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value44) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var47)));
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
												if(fixedFlag$sample5) {
													if(fixedFlag$sample27) {
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == j)) {
																int traceTempVariable$var46$64_1 = cv$currentValue;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$12$var47;
																			{
																				double var47 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + v2[j]) / traceTempVariable$var46$64_1);
																				cv$temp$12$var47 = var47;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var47)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
																if(!true) {
																	for(int index$sample11$65 = 0; index$sample11$65 < weightings.length; index$sample11$65 += 1) {
																		int distributionTempVariable$var11$67 = index$sample11$65;
																		double cv$probabilitySample11Value66 = (1.0 * distribution$sample11[index$sample11$65]);
																		int traceTempVariable$var46$68_1 = cv$currentValue;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$13$var47;
																					{
																						double var47 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + v2[j]) / traceTempVariable$var46$68_1);
																						cv$temp$13$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample11Value66) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value66) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value66) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value66) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value66) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value66);
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
																	if(((i + 1) == j)) {
																		int traceTempVariable$var46$69_1 = cv$currentValue;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$14$var47;
																					{
																						double var47 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + v2[j]) / traceTempVariable$var46$69_1);
																						cv$temp$14$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample27Value55) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value55) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value55) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value55) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value55) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value55);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$70 = 0; index$sample11$70 < weightings.length; index$sample11$70 += 1) {
																				int distributionTempVariable$var11$72 = index$sample11$70;
																				double cv$probabilitySample11Value71 = (cv$probabilitySample27Value55 * distribution$sample11[index$sample11$70]);
																				int traceTempVariable$var46$73_1 = cv$currentValue;
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$15$var47;
																							{
																								double var47 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + v2[j]) / traceTempVariable$var46$73_1);
																								cv$temp$15$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample11Value71) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value71) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value71) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value71) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value71) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var47)));
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
												} else {
													if(true) {
														for(int index$sample5$48 = 0; index$sample5$48 < weightings.length; index$sample5$48 += 1) {
															int distributionTempVariable$v1$50 = index$sample5$48;
															double cv$probabilitySample5Value49 = (1.0 * distribution$sample5[index$sample5$48]);
															if(fixedFlag$sample27) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == j)) {
																		int traceTempVariable$var46$74_1 = cv$currentValue;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$16$var47;
																					{
																						double var47 = ((((1.0 * distributionTempVariable$v1$50) + traceTempVariable$var42$4_1) + v2[j]) / traceTempVariable$var46$74_1);
																						cv$temp$16$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample5Value49) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value49) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value49) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value49) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value49) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value49);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$75 = 0; index$sample11$75 < weightings.length; index$sample11$75 += 1) {
																				int distributionTempVariable$var11$77 = index$sample11$75;
																				double cv$probabilitySample11Value76 = (cv$probabilitySample5Value49 * distribution$sample11[index$sample11$75]);
																				int traceTempVariable$var46$78_1 = cv$currentValue;
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$17$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$50) + traceTempVariable$var42$4_1) + v2[j]) / traceTempVariable$var46$78_1);
																								cv$temp$17$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample11Value76) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value76) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value76) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value76) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value76) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var47)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value76);
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
																			if(((i + 1) == j)) {
																				int traceTempVariable$var46$79_1 = cv$currentValue;
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$18$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$50) + traceTempVariable$var42$4_1) + v2[j]) / traceTempVariable$var46$79_1);
																								cv$temp$18$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value61) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value61) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value61) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value61) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value61) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var47)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value61);
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample11$80 = 0; index$sample11$80 < weightings.length; index$sample11$80 += 1) {
																						int distributionTempVariable$var11$82 = index$sample11$80;
																						double cv$probabilitySample11Value81 = (cv$probabilitySample27Value61 * distribution$sample11[index$sample11$80]);
																						int traceTempVariable$var46$83_1 = cv$currentValue;
																						if((0 == j)) {
																							{
																								{
																									double cv$temp$19$var47;
																									{
																										double var47 = ((((1.0 * distributionTempVariable$v1$50) + traceTempVariable$var42$4_1) + v2[j]) / traceTempVariable$var46$83_1);
																										cv$temp$19$var47 = var47;
																									}
																									if(((Math.log(cv$probabilitySample11Value81) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var47)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value81) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value81) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var47));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value81) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value81) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var47)));
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
												if(fixedFlag$sample5) {
													int traceTempVariable$var44$89_1 = cv$currentValue;
													if((0 == j)) {
														if(fixedFlag$sample27) {
															for(int i = 0; i < size; i += 1) {
																if(((i + 1) == j)) {
																	{
																		{
																			double cv$temp$20$var47;
																			{
																				double var47 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var44$89_1) / v2[j]);
																				cv$temp$20$var47 = var47;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var47)));
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
																		if(((i + 1) == j)) {
																			{
																				{
																					double cv$temp$21$var47;
																					{
																						double var47 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var44$89_1) / v2[j]);
																						cv$temp$21$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample27Value102) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value102) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value102) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value102) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value102) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var47)));
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
													if(!true) {
														for(int index$sample11$90 = 0; index$sample11$90 < weightings.length; index$sample11$90 += 1) {
															int distributionTempVariable$var11$92 = index$sample11$90;
															double cv$probabilitySample11Value91 = (1.0 * distribution$sample11[index$sample11$90]);
															int traceTempVariable$var44$93_1 = cv$currentValue;
															if((0 == j)) {
																if(fixedFlag$sample27) {
																	for(int i = 0; i < size; i += 1) {
																		if(((i + 1) == j)) {
																			{
																				{
																					double cv$temp$22$var47;
																					{
																						double var47 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var44$93_1) / v2[j]);
																						cv$temp$22$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample11Value91) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value91) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value91) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value91) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value91) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var47)));
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
																				if(((i + 1) == j)) {
																					{
																						{
																							double cv$temp$23$var47;
																							{
																								double var47 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + traceTempVariable$var44$93_1) / v2[j]);
																								cv$temp$23$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var47)));
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
												} else {
													if(true) {
														for(int index$sample5$85 = 0; index$sample5$85 < weightings.length; index$sample5$85 += 1) {
															int distributionTempVariable$v1$87 = index$sample5$85;
															double cv$probabilitySample5Value86 = (1.0 * distribution$sample5[index$sample5$85]);
															int traceTempVariable$var44$94_1 = cv$currentValue;
															if((0 == j)) {
																if(fixedFlag$sample27) {
																	for(int i = 0; i < size; i += 1) {
																		if(((i + 1) == j)) {
																			{
																				{
																					double cv$temp$24$var47;
																					{
																						double var47 = ((((1.0 * distributionTempVariable$v1$87) + traceTempVariable$var42$4_1) + traceTempVariable$var44$94_1) / v2[j]);
																						cv$temp$24$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample5Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var47)));
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
																				if(((i + 1) == j)) {
																					{
																						{
																							double cv$temp$25$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$87) + traceTempVariable$var42$4_1) + traceTempVariable$var44$94_1) / v2[j]);
																								cv$temp$25$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value114) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value114) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value114) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value114) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value114) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var47)));
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
															if(!true) {
																for(int index$sample11$95 = 0; index$sample11$95 < weightings.length; index$sample11$95 += 1) {
																	int distributionTempVariable$var11$97 = index$sample11$95;
																	double cv$probabilitySample11Value96 = (cv$probabilitySample5Value86 * distribution$sample11[index$sample11$95]);
																	int traceTempVariable$var44$98_1 = cv$currentValue;
																	if((0 == j)) {
																		if(fixedFlag$sample27) {
																			for(int i = 0; i < size; i += 1) {
																				if(((i + 1) == j)) {
																					{
																						{
																							double cv$temp$26$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$87) + traceTempVariable$var42$4_1) + traceTempVariable$var44$98_1) / v2[j]);
																								cv$temp$26$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample11Value96) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value96) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value96) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value96) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value96) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var47)));
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
																						if(((i + 1) == j)) {
																							{
																								{
																									double cv$temp$27$var47;
																									{
																										double var47 = ((((1.0 * distributionTempVariable$v1$87) + traceTempVariable$var42$4_1) + traceTempVariable$var44$98_1) / v2[j]);
																										cv$temp$27$var47 = var47;
																									}
																									if(((Math.log(cv$probabilitySample27Value120) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var47)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value120) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value120) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var47));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value120) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value120) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var47)));
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
												if(fixedFlag$sample5) {
													if(fixedFlag$sample27) {
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == j)) {
																for(int index$i$140_1 = 0; index$i$140_1 < size; index$i$140_1 += 1) {
																	if(((index$i$140_1 + 1) == j)) {
																		{
																			{
																				double cv$temp$28$var47;
																				{
																					double var47 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + v2[j]) / v2[j]);
																					cv$temp$28$var47 = var47;
																				}
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var47)));
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
																for(int index$sample27$130 = 0; index$sample27$130 < weightings.length; index$sample27$130 += 1) {
																	int distributionTempVariable$var27$132 = index$sample27$130;
																	double cv$probabilitySample27Value131 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$130]);
																	if(((i + 1) == j)) {
																		if(((i + 1) == j)) {
																			{
																				{
																					double cv$temp$29$var47;
																					{
																						double var47 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + v2[j]) / v2[j]);
																						cv$temp$29$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample27Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var47)));
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
																					if(((index$i$142 + 1) == j)) {
																						{
																							{
																								double cv$temp$30$var47;
																								{
																									double var47 = ((((1.0 * v1) + traceTempVariable$var42$4_1) + v2[j]) / v2[j]);
																									cv$temp$30$var47 = var47;
																								}
																								if(((Math.log(cv$probabilitySample27Value144) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var47)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value144) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value144) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var47));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value144) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value144) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var47)));
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
												} else {
													if(true) {
														for(int index$sample5$124 = 0; index$sample5$124 < weightings.length; index$sample5$124 += 1) {
															int distributionTempVariable$v1$126 = index$sample5$124;
															double cv$probabilitySample5Value125 = (1.0 * distribution$sample5[index$sample5$124]);
															if(fixedFlag$sample27) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == j)) {
																		for(int index$i$147_1 = 0; index$i$147_1 < size; index$i$147_1 += 1) {
																			if(((index$i$147_1 + 1) == j)) {
																				{
																					{
																						double cv$temp$31$var47;
																						{
																							double var47 = ((((1.0 * distributionTempVariable$v1$126) + traceTempVariable$var42$4_1) + v2[j]) / v2[j]);
																							cv$temp$31$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample5Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value125);
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
																			if(((i + 1) == j)) {
																				if(((i + 1) == j)) {
																					{
																						{
																							double cv$temp$32$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$126) + traceTempVariable$var42$4_1) + v2[j]) / v2[j]);
																								cv$temp$32$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var47)));
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
																							if(((index$i$149 + 1) == j)) {
																								{
																									{
																										double cv$temp$33$var47;
																										{
																											double var47 = ((((1.0 * distributionTempVariable$v1$126) + traceTempVariable$var42$4_1) + v2[j]) / v2[j]);
																											cv$temp$33$var47 = var47;
																										}
																										if(((Math.log(cv$probabilitySample27Value151) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var47)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value151) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value151) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var47));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value151) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value151) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var47)));
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
							int traceTempVariable$var44$5_1 = cv$currentValue;
							for(int j = 0; j < size; j += 1) {
								if((0 == j)) {
									if(!guard$sample11bernoulli48[((j - 0) / 1)]) {
										guard$sample11bernoulli48[((j - 0) / 1)] = true;
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if(fixedFlag$sample5) {
													int traceTempVariable$var42$159_1 = cv$currentValue;
													if((0 == j)) {
														int traceTempVariable$var46$169_1 = cv$currentValue;
														if((0 == j)) {
															{
																{
																	double cv$temp$34$var47;
																	{
																		double var47 = ((((1.0 * v1) + traceTempVariable$var42$159_1) + traceTempVariable$var44$5_1) / traceTempVariable$var46$169_1);
																		cv$temp$34$var47 = var47;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var47)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var47));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var47)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
														if(!true) {
															for(int index$sample11$170 = 0; index$sample11$170 < weightings.length; index$sample11$170 += 1) {
																int distributionTempVariable$var11$172 = index$sample11$170;
																double cv$probabilitySample11Value171 = (1.0 * distribution$sample11[index$sample11$170]);
																int traceTempVariable$var46$173_1 = cv$currentValue;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$35$var47;
																			{
																				double var47 = ((((1.0 * v1) + traceTempVariable$var42$159_1) + traceTempVariable$var44$5_1) / traceTempVariable$var46$173_1);
																				cv$temp$35$var47 = var47;
																			}
																			if(((Math.log(cv$probabilitySample11Value171) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value171) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value171) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value171) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value171) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var47)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value171);
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
															int traceTempVariable$var42$163_1 = cv$currentValue;
															if((0 == j)) {
																int traceTempVariable$var46$174_1 = cv$currentValue;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$36$var47;
																			{
																				double var47 = ((((1.0 * v1) + traceTempVariable$var42$163_1) + traceTempVariable$var44$5_1) / traceTempVariable$var46$174_1);
																				cv$temp$36$var47 = var47;
																			}
																			if(((Math.log(cv$probabilitySample11Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var47)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value161);
																		}
																	}
																}
																int traceTempVariable$var46$175_1 = cv$currentValue;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$37$var47;
																			{
																				double var47 = ((((1.0 * v1) + traceTempVariable$var42$163_1) + traceTempVariable$var44$5_1) / traceTempVariable$var46$175_1);
																				cv$temp$37$var47 = var47;
																			}
																			if(((Math.log(cv$probabilitySample11Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var47)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value161);
																		}
																	}
																}
																if(!true) {
																	for(int index$sample11$176 = 0; index$sample11$176 < weightings.length; index$sample11$176 += 1) {
																		int distributionTempVariable$var11$178 = index$sample11$176;
																		double cv$probabilitySample11Value177 = (cv$probabilitySample11Value161 * distribution$sample11[index$sample11$176]);
																		int traceTempVariable$var46$179_1 = cv$currentValue;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$38$var47;
																					{
																						double var47 = ((((1.0 * v1) + traceTempVariable$var42$163_1) + traceTempVariable$var44$5_1) / traceTempVariable$var46$179_1);
																						cv$temp$38$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample11Value177) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value177) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value177) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value177) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value177) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var47)));
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
												} else {
													if(true) {
														for(int index$sample5$155 = 0; index$sample5$155 < weightings.length; index$sample5$155 += 1) {
															int distributionTempVariable$v1$157 = index$sample5$155;
															double cv$probabilitySample5Value156 = (1.0 * distribution$sample5[index$sample5$155]);
															int traceTempVariable$var42$164_1 = cv$currentValue;
															if((0 == j)) {
																int traceTempVariable$var46$180_1 = cv$currentValue;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$39$var47;
																			{
																				double var47 = ((((1.0 * distributionTempVariable$v1$157) + traceTempVariable$var42$164_1) + traceTempVariable$var44$5_1) / traceTempVariable$var46$180_1);
																				cv$temp$39$var47 = var47;
																			}
																			if(((Math.log(cv$probabilitySample5Value156) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value156) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value156) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value156) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value156) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var47)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value156);
																		}
																	}
																}
																if(!true) {
																	for(int index$sample11$181 = 0; index$sample11$181 < weightings.length; index$sample11$181 += 1) {
																		int distributionTempVariable$var11$183 = index$sample11$181;
																		double cv$probabilitySample11Value182 = (cv$probabilitySample5Value156 * distribution$sample11[index$sample11$181]);
																		int traceTempVariable$var46$184_1 = cv$currentValue;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$40$var47;
																					{
																						double var47 = ((((1.0 * distributionTempVariable$v1$157) + traceTempVariable$var42$164_1) + traceTempVariable$var44$5_1) / traceTempVariable$var46$184_1);
																						cv$temp$40$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample11Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value182);
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
																	int traceTempVariable$var42$168_1 = cv$currentValue;
																	if((0 == j)) {
																		int traceTempVariable$var46$185_1 = cv$currentValue;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$41$var47;
																					{
																						double var47 = ((((1.0 * distributionTempVariable$v1$157) + traceTempVariable$var42$168_1) + traceTempVariable$var44$5_1) / traceTempVariable$var46$185_1);
																						cv$temp$41$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample11Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value166);
																				}
																			}
																		}
																		int traceTempVariable$var46$186_1 = cv$currentValue;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$42$var47;
																					{
																						double var47 = ((((1.0 * distributionTempVariable$v1$157) + traceTempVariable$var42$168_1) + traceTempVariable$var44$5_1) / traceTempVariable$var46$186_1);
																						cv$temp$42$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample11Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value166) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value166);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$187 = 0; index$sample11$187 < weightings.length; index$sample11$187 += 1) {
																				int distributionTempVariable$var11$189 = index$sample11$187;
																				double cv$probabilitySample11Value188 = (cv$probabilitySample11Value166 * distribution$sample11[index$sample11$187]);
																				int traceTempVariable$var46$190_1 = cv$currentValue;
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$43$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$157) + traceTempVariable$var42$168_1) + traceTempVariable$var44$5_1) / traceTempVariable$var46$190_1);
																								cv$temp$43$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample11Value188) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value188) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value188) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value188) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value188) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var47)));
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
												if(fixedFlag$sample5) {
													if(fixedFlag$sample27) {
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == j)) {
																int traceTempVariable$var46$208_1 = cv$currentValue;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$44$var47;
																			{
																				double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$5_1) / traceTempVariable$var46$208_1);
																				cv$temp$44$var47 = var47;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var47)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
																if(!true) {
																	for(int index$sample11$209 = 0; index$sample11$209 < weightings.length; index$sample11$209 += 1) {
																		int distributionTempVariable$var11$211 = index$sample11$209;
																		double cv$probabilitySample11Value210 = (1.0 * distribution$sample11[index$sample11$209]);
																		int traceTempVariable$var46$212_1 = cv$currentValue;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$45$var47;
																					{
																						double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$5_1) / traceTempVariable$var46$212_1);
																						cv$temp$45$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample11Value210) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value210) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value210) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value210) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value210) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value210);
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
																	if(((i + 1) == j)) {
																		int traceTempVariable$var46$213_1 = cv$currentValue;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$46$var47;
																					{
																						double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$5_1) / traceTempVariable$var46$213_1);
																						cv$temp$46$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample27Value199) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value199) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value199) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value199) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value199) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value199);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$214 = 0; index$sample11$214 < weightings.length; index$sample11$214 += 1) {
																				int distributionTempVariable$var11$216 = index$sample11$214;
																				double cv$probabilitySample11Value215 = (cv$probabilitySample27Value199 * distribution$sample11[index$sample11$214]);
																				int traceTempVariable$var46$217_1 = cv$currentValue;
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$47$var47;
																							{
																								double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$5_1) / traceTempVariable$var46$217_1);
																								cv$temp$47$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample11Value215) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value215) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value215) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value215) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value215) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var47)));
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
												} else {
													if(true) {
														for(int index$sample5$192 = 0; index$sample5$192 < weightings.length; index$sample5$192 += 1) {
															int distributionTempVariable$v1$194 = index$sample5$192;
															double cv$probabilitySample5Value193 = (1.0 * distribution$sample5[index$sample5$192]);
															if(fixedFlag$sample27) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == j)) {
																		int traceTempVariable$var46$218_1 = cv$currentValue;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$48$var47;
																					{
																						double var47 = ((((1.0 * distributionTempVariable$v1$194) + v2[j]) + traceTempVariable$var44$5_1) / traceTempVariable$var46$218_1);
																						cv$temp$48$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample5Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value193);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$219 = 0; index$sample11$219 < weightings.length; index$sample11$219 += 1) {
																				int distributionTempVariable$var11$221 = index$sample11$219;
																				double cv$probabilitySample11Value220 = (cv$probabilitySample5Value193 * distribution$sample11[index$sample11$219]);
																				int traceTempVariable$var46$222_1 = cv$currentValue;
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$49$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$194) + v2[j]) + traceTempVariable$var44$5_1) / traceTempVariable$var46$222_1);
																								cv$temp$49$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample11Value220) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value220) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value220) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value220) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value220) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var47)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value220);
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
																			if(((i + 1) == j)) {
																				int traceTempVariable$var46$223_1 = cv$currentValue;
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$50$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$194) + v2[j]) + traceTempVariable$var44$5_1) / traceTempVariable$var46$223_1);
																								cv$temp$50$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value205) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value205) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value205) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value205) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value205) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var47)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value205);
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample11$224 = 0; index$sample11$224 < weightings.length; index$sample11$224 += 1) {
																						int distributionTempVariable$var11$226 = index$sample11$224;
																						double cv$probabilitySample11Value225 = (cv$probabilitySample27Value205 * distribution$sample11[index$sample11$224]);
																						int traceTempVariable$var46$227_1 = cv$currentValue;
																						if((0 == j)) {
																							{
																								{
																									double cv$temp$51$var47;
																									{
																										double var47 = ((((1.0 * distributionTempVariable$v1$194) + v2[j]) + traceTempVariable$var44$5_1) / traceTempVariable$var46$227_1);
																										cv$temp$51$var47 = var47;
																									}
																									if(((Math.log(cv$probabilitySample11Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var47)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var47));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var47)));
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
												if(fixedFlag$sample5) {
													int traceTempVariable$var42$233_1 = cv$currentValue;
													if((0 == j)) {
														if(fixedFlag$sample27) {
															for(int i = 0; i < size; i += 1) {
																if(((i + 1) == j)) {
																	{
																		{
																			double cv$temp$52$var47;
																			{
																				double var47 = ((((1.0 * v1) + traceTempVariable$var42$233_1) + traceTempVariable$var44$5_1) / v2[j]);
																				cv$temp$52$var47 = var47;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var47)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																		if(((i + 1) == j)) {
																			{
																				{
																					double cv$temp$53$var47;
																					{
																						double var47 = ((((1.0 * v1) + traceTempVariable$var42$233_1) + traceTempVariable$var44$5_1) / v2[j]);
																						cv$temp$53$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample27Value246) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value246) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value246) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value246) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value246) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var47)));
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
													if(!true) {
														for(int index$sample11$234 = 0; index$sample11$234 < weightings.length; index$sample11$234 += 1) {
															int distributionTempVariable$var11$236 = index$sample11$234;
															double cv$probabilitySample11Value235 = (1.0 * distribution$sample11[index$sample11$234]);
															int traceTempVariable$var42$237_1 = cv$currentValue;
															if((0 == j)) {
																if(fixedFlag$sample27) {
																	for(int i = 0; i < size; i += 1) {
																		if(((i + 1) == j)) {
																			{
																				{
																					double cv$temp$54$var47;
																					{
																						double var47 = ((((1.0 * v1) + traceTempVariable$var42$237_1) + traceTempVariable$var44$5_1) / v2[j]);
																						cv$temp$54$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample11Value235) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value235) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value235) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value235) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value235) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value235);
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
																				if(((i + 1) == j)) {
																					{
																						{
																							double cv$temp$55$var47;
																							{
																								double var47 = ((((1.0 * v1) + traceTempVariable$var42$237_1) + traceTempVariable$var44$5_1) / v2[j]);
																								cv$temp$55$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var47)));
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
												} else {
													if(true) {
														for(int index$sample5$229 = 0; index$sample5$229 < weightings.length; index$sample5$229 += 1) {
															int distributionTempVariable$v1$231 = index$sample5$229;
															double cv$probabilitySample5Value230 = (1.0 * distribution$sample5[index$sample5$229]);
															int traceTempVariable$var42$238_1 = cv$currentValue;
															if((0 == j)) {
																if(fixedFlag$sample27) {
																	for(int i = 0; i < size; i += 1) {
																		if(((i + 1) == j)) {
																			{
																				{
																					double cv$temp$56$var47;
																					{
																						double var47 = ((((1.0 * distributionTempVariable$v1$231) + traceTempVariable$var42$238_1) + traceTempVariable$var44$5_1) / v2[j]);
																						cv$temp$56$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample5Value230) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value230) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value230) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value230) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value230) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value230);
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
																				if(((i + 1) == j)) {
																					{
																						{
																							double cv$temp$57$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$231) + traceTempVariable$var42$238_1) + traceTempVariable$var44$5_1) / v2[j]);
																								cv$temp$57$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value258) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value258) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value258) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value258) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value258) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var47)));
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
															if(!true) {
																for(int index$sample11$239 = 0; index$sample11$239 < weightings.length; index$sample11$239 += 1) {
																	int distributionTempVariable$var11$241 = index$sample11$239;
																	double cv$probabilitySample11Value240 = (cv$probabilitySample5Value230 * distribution$sample11[index$sample11$239]);
																	int traceTempVariable$var42$242_1 = cv$currentValue;
																	if((0 == j)) {
																		if(fixedFlag$sample27) {
																			for(int i = 0; i < size; i += 1) {
																				if(((i + 1) == j)) {
																					{
																						{
																							double cv$temp$58$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$231) + traceTempVariable$var42$242_1) + traceTempVariable$var44$5_1) / v2[j]);
																								cv$temp$58$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample11Value240) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value240) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value240) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value240) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value240) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var47)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value240);
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
																						if(((i + 1) == j)) {
																							{
																								{
																									double cv$temp$59$var47;
																									{
																										double var47 = ((((1.0 * distributionTempVariable$v1$231) + traceTempVariable$var42$242_1) + traceTempVariable$var44$5_1) / v2[j]);
																										cv$temp$59$var47 = var47;
																									}
																									if(((Math.log(cv$probabilitySample27Value264) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var47)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value264) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value264) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var47));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value264) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value264) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var47)));
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
												if(fixedFlag$sample5) {
													if(fixedFlag$sample27) {
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == j)) {
																for(int index$i$284_1 = 0; index$i$284_1 < size; index$i$284_1 += 1) {
																	if(((index$i$284_1 + 1) == j)) {
																		{
																			{
																				double cv$temp$60$var47;
																				{
																					double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$5_1) / v2[j]);
																					cv$temp$60$var47 = var47;
																				}
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var47)));
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
																for(int index$sample27$274 = 0; index$sample27$274 < weightings.length; index$sample27$274 += 1) {
																	int distributionTempVariable$var27$276 = index$sample27$274;
																	double cv$probabilitySample27Value275 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$274]);
																	if(((i + 1) == j)) {
																		if(((i + 1) == j)) {
																			{
																				{
																					double cv$temp$61$var47;
																					{
																						double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$5_1) / v2[j]);
																						cv$temp$61$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value275);
																				}
																			}
																		}
																		for(int index$i$286 = 0; index$i$286 < size; index$i$286 += 1) {
																			if(!(index$i$286 == i)) {
																				for(int index$sample27$287 = 0; index$sample27$287 < weightings.length; index$sample27$287 += 1) {
																					int distributionTempVariable$var27$289 = index$sample27$287;
																					double cv$probabilitySample27Value288 = (cv$probabilitySample27Value275 * distribution$sample27[((index$i$286 - 0) / 1)][index$sample27$287]);
																					if(((index$i$286 + 1) == j)) {
																						{
																							{
																								double cv$temp$62$var47;
																								{
																									double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$5_1) / v2[j]);
																									cv$temp$62$var47 = var47;
																								}
																								if(((Math.log(cv$probabilitySample27Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var47)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var47));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var47)));
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
												} else {
													if(true) {
														for(int index$sample5$268 = 0; index$sample5$268 < weightings.length; index$sample5$268 += 1) {
															int distributionTempVariable$v1$270 = index$sample5$268;
															double cv$probabilitySample5Value269 = (1.0 * distribution$sample5[index$sample5$268]);
															if(fixedFlag$sample27) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == j)) {
																		for(int index$i$291_1 = 0; index$i$291_1 < size; index$i$291_1 += 1) {
																			if(((index$i$291_1 + 1) == j)) {
																				{
																					{
																						double cv$temp$63$var47;
																						{
																							double var47 = ((((1.0 * distributionTempVariable$v1$270) + v2[j]) + traceTempVariable$var44$5_1) / v2[j]);
																							cv$temp$63$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample5Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value269);
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
																				if(((i + 1) == j)) {
																					{
																						{
																							double cv$temp$64$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$270) + v2[j]) + traceTempVariable$var44$5_1) / v2[j]);
																								cv$temp$64$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var47)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value281);
																						}
																					}
																				}
																				for(int index$i$293 = 0; index$i$293 < size; index$i$293 += 1) {
																					if(!(index$i$293 == i)) {
																						for(int index$sample27$294 = 0; index$sample27$294 < weightings.length; index$sample27$294 += 1) {
																							int distributionTempVariable$var27$296 = index$sample27$294;
																							double cv$probabilitySample27Value295 = (cv$probabilitySample27Value281 * distribution$sample27[((index$i$293 - 0) / 1)][index$sample27$294]);
																							if(((index$i$293 + 1) == j)) {
																								{
																									{
																										double cv$temp$65$var47;
																										{
																											double var47 = ((((1.0 * distributionTempVariable$v1$270) + v2[j]) + traceTempVariable$var44$5_1) / v2[j]);
																											cv$temp$65$var47 = var47;
																										}
																										if(((Math.log(cv$probabilitySample27Value295) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var47)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value295) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value295) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var47));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value295) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value295) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var47)));
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
							int traceTempVariable$var46$6_1 = cv$currentValue;
							for(int j = 0; j < size; j += 1) {
								if((0 == j)) {
									if(!guard$sample11bernoulli48[((j - 0) / 1)]) {
										guard$sample11bernoulli48[((j - 0) / 1)] = true;
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if(fixedFlag$sample5) {
													int traceTempVariable$var42$303_1 = cv$currentValue;
													if((0 == j)) {
														int traceTempVariable$var44$313_1 = cv$currentValue;
														if((0 == j)) {
															{
																{
																	double cv$temp$66$var47;
																	{
																		double var47 = ((((1.0 * v1) + traceTempVariable$var42$303_1) + traceTempVariable$var44$313_1) / traceTempVariable$var46$6_1);
																		cv$temp$66$var47 = var47;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var47)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var47));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var47)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
														if(!true) {
															for(int index$sample11$314 = 0; index$sample11$314 < weightings.length; index$sample11$314 += 1) {
																int distributionTempVariable$var11$316 = index$sample11$314;
																double cv$probabilitySample11Value315 = (1.0 * distribution$sample11[index$sample11$314]);
																int traceTempVariable$var44$317_1 = cv$currentValue;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$67$var47;
																			{
																				double var47 = ((((1.0 * v1) + traceTempVariable$var42$303_1) + traceTempVariable$var44$317_1) / traceTempVariable$var46$6_1);
																				cv$temp$67$var47 = var47;
																			}
																			if(((Math.log(cv$probabilitySample11Value315) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value315) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value315) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value315) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value315) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var47)));
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
															int traceTempVariable$var42$307_1 = cv$currentValue;
															if((0 == j)) {
																int traceTempVariable$var44$318_1 = cv$currentValue;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$68$var47;
																			{
																				double var47 = ((((1.0 * v1) + traceTempVariable$var42$307_1) + traceTempVariable$var44$318_1) / traceTempVariable$var46$6_1);
																				cv$temp$68$var47 = var47;
																			}
																			if(((Math.log(cv$probabilitySample11Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var47)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value305);
																		}
																	}
																}
																int traceTempVariable$var44$319_1 = cv$currentValue;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$69$var47;
																			{
																				double var47 = ((((1.0 * v1) + traceTempVariable$var42$307_1) + traceTempVariable$var44$319_1) / traceTempVariable$var46$6_1);
																				cv$temp$69$var47 = var47;
																			}
																			if(((Math.log(cv$probabilitySample11Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value305) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var47)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value305);
																		}
																	}
																}
																if(!true) {
																	for(int index$sample11$320 = 0; index$sample11$320 < weightings.length; index$sample11$320 += 1) {
																		int distributionTempVariable$var11$322 = index$sample11$320;
																		double cv$probabilitySample11Value321 = (cv$probabilitySample11Value305 * distribution$sample11[index$sample11$320]);
																		int traceTempVariable$var44$323_1 = cv$currentValue;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$70$var47;
																					{
																						double var47 = ((((1.0 * v1) + traceTempVariable$var42$307_1) + traceTempVariable$var44$323_1) / traceTempVariable$var46$6_1);
																						cv$temp$70$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample11Value321) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value321) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value321) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value321) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value321) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var47)));
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
															int traceTempVariable$var42$308_1 = cv$currentValue;
															if((0 == j)) {
																int traceTempVariable$var44$324_1 = cv$currentValue;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$71$var47;
																			{
																				double var47 = ((((1.0 * distributionTempVariable$v1$301) + traceTempVariable$var42$308_1) + traceTempVariable$var44$324_1) / traceTempVariable$var46$6_1);
																				cv$temp$71$var47 = var47;
																			}
																			if(((Math.log(cv$probabilitySample5Value300) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value300) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value300) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value300) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value300) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var47)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value300);
																		}
																	}
																}
																if(!true) {
																	for(int index$sample11$325 = 0; index$sample11$325 < weightings.length; index$sample11$325 += 1) {
																		int distributionTempVariable$var11$327 = index$sample11$325;
																		double cv$probabilitySample11Value326 = (cv$probabilitySample5Value300 * distribution$sample11[index$sample11$325]);
																		int traceTempVariable$var44$328_1 = cv$currentValue;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$72$var47;
																					{
																						double var47 = ((((1.0 * distributionTempVariable$v1$301) + traceTempVariable$var42$308_1) + traceTempVariable$var44$328_1) / traceTempVariable$var46$6_1);
																						cv$temp$72$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample11Value326) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value326) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value326) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value326) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value326) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var47)));
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
																	int traceTempVariable$var42$312_1 = cv$currentValue;
																	if((0 == j)) {
																		int traceTempVariable$var44$329_1 = cv$currentValue;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$73$var47;
																					{
																						double var47 = ((((1.0 * distributionTempVariable$v1$301) + traceTempVariable$var42$312_1) + traceTempVariable$var44$329_1) / traceTempVariable$var46$6_1);
																						cv$temp$73$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample11Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value310);
																				}
																			}
																		}
																		int traceTempVariable$var44$330_1 = cv$currentValue;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$74$var47;
																					{
																						double var47 = ((((1.0 * distributionTempVariable$v1$301) + traceTempVariable$var42$312_1) + traceTempVariable$var44$330_1) / traceTempVariable$var46$6_1);
																						cv$temp$74$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample11Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value310) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value310);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$331 = 0; index$sample11$331 < weightings.length; index$sample11$331 += 1) {
																				int distributionTempVariable$var11$333 = index$sample11$331;
																				double cv$probabilitySample11Value332 = (cv$probabilitySample11Value310 * distribution$sample11[index$sample11$331]);
																				int traceTempVariable$var44$334_1 = cv$currentValue;
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$75$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$301) + traceTempVariable$var42$312_1) + traceTempVariable$var44$334_1) / traceTempVariable$var46$6_1);
																								cv$temp$75$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample11Value332) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value332) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value332) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value332) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value332) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var47)));
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
																int traceTempVariable$var44$352_1 = cv$currentValue;
																if((0 == j)) {
																	{
																		{
																			double cv$temp$76$var47;
																			{
																				double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$352_1) / traceTempVariable$var46$6_1);
																				cv$temp$76$var47 = var47;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var47)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
																if(!true) {
																	for(int index$sample11$353 = 0; index$sample11$353 < weightings.length; index$sample11$353 += 1) {
																		int distributionTempVariable$var11$355 = index$sample11$353;
																		double cv$probabilitySample11Value354 = (1.0 * distribution$sample11[index$sample11$353]);
																		int traceTempVariable$var44$356_1 = cv$currentValue;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$77$var47;
																					{
																						double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$356_1) / traceTempVariable$var46$6_1);
																						cv$temp$77$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample11Value354) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value354) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value354) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value354) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value354) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var47)));
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
																		int traceTempVariable$var44$357_1 = cv$currentValue;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$78$var47;
																					{
																						double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$357_1) / traceTempVariable$var46$6_1);
																						cv$temp$78$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample27Value343) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value343) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value343) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value343) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value343) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value343);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$358 = 0; index$sample11$358 < weightings.length; index$sample11$358 += 1) {
																				int distributionTempVariable$var11$360 = index$sample11$358;
																				double cv$probabilitySample11Value359 = (cv$probabilitySample27Value343 * distribution$sample11[index$sample11$358]);
																				int traceTempVariable$var44$361_1 = cv$currentValue;
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$79$var47;
																							{
																								double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$361_1) / traceTempVariable$var46$6_1);
																								cv$temp$79$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample11Value359) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value359) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value359) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value359) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value359) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var47)));
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
																		int traceTempVariable$var44$362_1 = cv$currentValue;
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$80$var47;
																					{
																						double var47 = ((((1.0 * distributionTempVariable$v1$338) + v2[j]) + traceTempVariable$var44$362_1) / traceTempVariable$var46$6_1);
																						cv$temp$80$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample5Value337) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value337) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value337) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value337) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value337) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value337);
																				}
																			}
																		}
																		if(!true) {
																			for(int index$sample11$363 = 0; index$sample11$363 < weightings.length; index$sample11$363 += 1) {
																				int distributionTempVariable$var11$365 = index$sample11$363;
																				double cv$probabilitySample11Value364 = (cv$probabilitySample5Value337 * distribution$sample11[index$sample11$363]);
																				int traceTempVariable$var44$366_1 = cv$currentValue;
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$81$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$338) + v2[j]) + traceTempVariable$var44$366_1) / traceTempVariable$var46$6_1);
																								cv$temp$81$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample11Value364) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value364) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value364) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value364) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value364) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var47)));
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
																				int traceTempVariable$var44$367_1 = cv$currentValue;
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$82$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$338) + v2[j]) + traceTempVariable$var44$367_1) / traceTempVariable$var46$6_1);
																								cv$temp$82$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value349) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value349) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value349) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value349) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value349) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var47)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value349);
																						}
																					}
																				}
																				if(!true) {
																					for(int index$sample11$368 = 0; index$sample11$368 < weightings.length; index$sample11$368 += 1) {
																						int distributionTempVariable$var11$370 = index$sample11$368;
																						double cv$probabilitySample11Value369 = (cv$probabilitySample27Value349 * distribution$sample11[index$sample11$368]);
																						int traceTempVariable$var44$371_1 = cv$currentValue;
																						if((0 == j)) {
																							{
																								{
																									double cv$temp$83$var47;
																									{
																										double var47 = ((((1.0 * distributionTempVariable$v1$338) + v2[j]) + traceTempVariable$var44$371_1) / traceTempVariable$var46$6_1);
																										cv$temp$83$var47 = var47;
																									}
																									if(((Math.log(cv$probabilitySample11Value369) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var47)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value369) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value369) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var47));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value369) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value369) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var47)));
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
													int traceTempVariable$var42$377_1 = cv$currentValue;
													if((0 == j)) {
														if(fixedFlag$sample27) {
															for(int i = 0; i < size; i += 1) {
																if(((i + 1) == j)) {
																	{
																		{
																			double cv$temp$84$var47;
																			{
																				double var47 = ((((1.0 * v1) + traceTempVariable$var42$377_1) + v2[j]) / traceTempVariable$var46$6_1);
																				cv$temp$84$var47 = var47;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var47)));
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
																		if(((i + 1) == j)) {
																			{
																				{
																					double cv$temp$85$var47;
																					{
																						double var47 = ((((1.0 * v1) + traceTempVariable$var42$377_1) + v2[j]) / traceTempVariable$var46$6_1);
																						cv$temp$85$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample27Value390) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value390) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value390) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value390) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value390) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var47)));
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
															int traceTempVariable$var42$381_1 = cv$currentValue;
															if((0 == j)) {
																if(fixedFlag$sample27) {
																	for(int i = 0; i < size; i += 1) {
																		if(((i + 1) == j)) {
																			{
																				{
																					double cv$temp$86$var47;
																					{
																						double var47 = ((((1.0 * v1) + traceTempVariable$var42$381_1) + v2[j]) / traceTempVariable$var46$6_1);
																						cv$temp$86$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample11Value379) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value379) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value379) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value379) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value379) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var47)));
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
																				if(((i + 1) == j)) {
																					{
																						{
																							double cv$temp$87$var47;
																							{
																								double var47 = ((((1.0 * v1) + traceTempVariable$var42$381_1) + v2[j]) / traceTempVariable$var46$6_1);
																								cv$temp$87$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value396) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value396) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value396) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value396) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value396) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var47)));
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
															int traceTempVariable$var42$382_1 = cv$currentValue;
															if((0 == j)) {
																if(fixedFlag$sample27) {
																	for(int i = 0; i < size; i += 1) {
																		if(((i + 1) == j)) {
																			{
																				{
																					double cv$temp$88$var47;
																					{
																						double var47 = ((((1.0 * distributionTempVariable$v1$375) + traceTempVariable$var42$382_1) + v2[j]) / traceTempVariable$var46$6_1);
																						cv$temp$88$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample5Value374) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value374) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value374) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value374) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value374) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var47)));
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
																				if(((i + 1) == j)) {
																					{
																						{
																							double cv$temp$89$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$375) + traceTempVariable$var42$382_1) + v2[j]) / traceTempVariable$var46$6_1);
																								cv$temp$89$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value402) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value402) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value402) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value402) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value402) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var47)));
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
																	int traceTempVariable$var42$386_1 = cv$currentValue;
																	if((0 == j)) {
																		if(fixedFlag$sample27) {
																			for(int i = 0; i < size; i += 1) {
																				if(((i + 1) == j)) {
																					{
																						{
																							double cv$temp$90$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$375) + traceTempVariable$var42$386_1) + v2[j]) / traceTempVariable$var46$6_1);
																								cv$temp$90$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample11Value384) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value384) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value384) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value384) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value384) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var47)));
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
																						if(((i + 1) == j)) {
																							{
																								{
																									double cv$temp$91$var47;
																									{
																										double var47 = ((((1.0 * distributionTempVariable$v1$375) + traceTempVariable$var42$386_1) + v2[j]) / traceTempVariable$var46$6_1);
																										cv$temp$91$var47 = var47;
																									}
																									if(((Math.log(cv$probabilitySample27Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var47)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var47));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value408) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var47)));
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
																	if(((index$i$428_1 + 1) == j)) {
																		{
																			{
																				double cv$temp$92$var47;
																				{
																					double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / traceTempVariable$var46$6_1);
																					cv$temp$92$var47 = var47;
																				}
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var47)));
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
																		if(((i + 1) == j)) {
																			{
																				{
																					double cv$temp$93$var47;
																					{
																						double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / traceTempVariable$var46$6_1);
																						cv$temp$93$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample27Value419) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value419) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value419) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value419) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value419) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var47)));
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
																					if(((index$i$430 + 1) == j)) {
																						{
																							{
																								double cv$temp$94$var47;
																								{
																									double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / traceTempVariable$var46$6_1);
																									cv$temp$94$var47 = var47;
																								}
																								if(((Math.log(cv$probabilitySample27Value432) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var47)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value432) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value432) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var47));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value432) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value432) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var47)));
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
																			if(((index$i$435_1 + 1) == j)) {
																				{
																					{
																						double cv$temp$95$var47;
																						{
																							double var47 = ((((1.0 * distributionTempVariable$v1$414) + v2[j]) + v2[j]) / traceTempVariable$var46$6_1);
																							cv$temp$95$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample5Value413) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value413) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value413) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value413) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value413) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var47)));
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
																				if(((i + 1) == j)) {
																					{
																						{
																							double cv$temp$96$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$414) + v2[j]) + v2[j]) / traceTempVariable$var46$6_1);
																								cv$temp$96$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value425) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value425) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value425) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value425) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value425) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var47)));
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
																							if(((index$i$437 + 1) == j)) {
																								{
																									{
																										double cv$temp$97$var47;
																										{
																											double var47 = ((((1.0 * distributionTempVariable$v1$414) + v2[j]) + v2[j]) / traceTempVariable$var46$6_1);
																											cv$temp$97$var47 = var47;
																										}
																										if(((Math.log(cv$probabilitySample27Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$97$var47)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$97$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$97$var47));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$97$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value439) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$97$var47)));
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
					int cv$temp$1$$var1083;
					{
						cv$temp$1$$var1083 = weightings.length;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var1083))?Math.log(cv$temp$0$weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							boolean[] guard$sample27bernoulli48 = guard$sample27bernoulli48$global;
							for(int j = 0; j < size; j += 1) {
								if(((i + 1) == j))
									guard$sample27bernoulli48[((j - 0) / 1)] = false;
							}
							for(int j = 0; j < size; j += 1) {
								if(((i + 1) == j))
									guard$sample27bernoulli48[((j - 0) / 1)] = false;
							}
							for(int j = 0; j < size; j += 1) {
								if(((i + 1) == j))
									guard$sample27bernoulli48[((j - 0) / 1)] = false;
							}
							int traceTempVariable$var42$6_1 = cv$currentValue;
							for(int j = 0; j < size; j += 1) {
								if(((i + 1) == j)) {
									if(!guard$sample27bernoulli48[((j - 0) / 1)]) {
										guard$sample27bernoulli48[((j - 0) / 1)] = true;
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if(fixedFlag$sample5) {
													if(fixedFlag$sample11) {
														if((0 == j)) {
															if((0 == j)) {
																{
																	{
																		double cv$temp$2$var47;
																		{
																			double var47 = ((((1.0 * v1) + traceTempVariable$var42$6_1) + v2[j]) / v2[j]);
																			cv$temp$2$var47 = var47;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var47)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var47));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var47)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample11$18 = 0; index$sample11$18 < weightings.length; index$sample11$18 += 1) {
																int distributionTempVariable$var11$20 = index$sample11$18;
																double cv$probabilitySample11Value19 = (1.0 * distribution$sample11[index$sample11$18]);
																if((0 == j)) {
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$3$var47;
																				{
																					double var47 = ((((1.0 * v1) + traceTempVariable$var42$6_1) + v2[j]) / v2[j]);
																					cv$temp$3$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample11Value19) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value19) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value19) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value19) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value19) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value19);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample11$29 = 0; index$sample11$29 < weightings.length; index$sample11$29 += 1) {
																			int distributionTempVariable$var11$31 = index$sample11$29;
																			double cv$probabilitySample11Value30 = (cv$probabilitySample11Value19 * distribution$sample11[index$sample11$29]);
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$4$var47;
																						{
																							double var47 = ((((1.0 * v1) + traceTempVariable$var42$6_1) + v2[j]) / v2[j]);
																							cv$temp$4$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample11Value30) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value30) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value30) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value30) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value30) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var47)));
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
												} else {
													if(true) {
														for(int index$sample5$13 = 0; index$sample5$13 < weightings.length; index$sample5$13 += 1) {
															int distributionTempVariable$v1$15 = index$sample5$13;
															double cv$probabilitySample5Value14 = (1.0 * distribution$sample5[index$sample5$13]);
															if(fixedFlag$sample11) {
																if((0 == j)) {
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$5$var47;
																				{
																					double var47 = ((((1.0 * distributionTempVariable$v1$15) + traceTempVariable$var42$6_1) + v2[j]) / v2[j]);
																					cv$temp$5$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample5Value14) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value14) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value14) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value14) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value14) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value14);
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample11$23 = 0; index$sample11$23 < weightings.length; index$sample11$23 += 1) {
																		int distributionTempVariable$var11$25 = index$sample11$23;
																		double cv$probabilitySample11Value24 = (cv$probabilitySample5Value14 * distribution$sample11[index$sample11$23]);
																		if((0 == j)) {
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$6$var47;
																						{
																							double var47 = ((((1.0 * distributionTempVariable$v1$15) + traceTempVariable$var42$6_1) + v2[j]) / v2[j]);
																							cv$temp$6$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample11Value24) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value24) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value24) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value24) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value24) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value24);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample11$35 = 0; index$sample11$35 < weightings.length; index$sample11$35 += 1) {
																					int distributionTempVariable$var11$37 = index$sample11$35;
																					double cv$probabilitySample11Value36 = (cv$probabilitySample11Value24 * distribution$sample11[index$sample11$35]);
																					if((0 == j)) {
																						{
																							{
																								double cv$temp$7$var47;
																								{
																									double var47 = ((((1.0 * distributionTempVariable$v1$15) + traceTempVariable$var42$6_1) + v2[j]) / v2[j]);
																									cv$temp$7$var47 = var47;
																								}
																								if(((Math.log(cv$probabilitySample11Value36) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var47)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value36) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value36) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var47));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value36) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value36) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var47)));
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
												if(fixedFlag$sample5) {
													int traceTempVariable$var44$44_1 = cv$currentValue;
													if(((index$i$2 + 1) == j)) {
														if(fixedFlag$sample11) {
															if((0 == j)) {
																{
																	{
																		double cv$temp$8$var47;
																		{
																			double var47 = ((((1.0 * v1) + traceTempVariable$var42$6_1) + traceTempVariable$var44$44_1) / v2[j]);
																			cv$temp$8$var47 = var47;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var47)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var47));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var47)));
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
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$9$var47;
																				{
																					double var47 = ((((1.0 * v1) + traceTempVariable$var42$6_1) + traceTempVariable$var44$44_1) / v2[j]);
																					cv$temp$9$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample11Value58) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value58) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value58) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value58) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value58) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value58);
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
																if(((index$i$45 + 1) == j)) {
																	if(fixedFlag$sample11) {
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$10$var47;
																					{
																						double var47 = ((((1.0 * v1) + traceTempVariable$var42$6_1) + traceTempVariable$var44$49_1) / v2[j]);
																						cv$temp$10$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample27Value47) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value47) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value47) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value47) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value47) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var47)));
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
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$11$var47;
																							{
																								double var47 = ((((1.0 * v1) + traceTempVariable$var42$6_1) + traceTempVariable$var44$49_1) / v2[j]);
																								cv$temp$11$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample11Value63) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value63) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value63) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value63) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value63) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var47)));
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
												} else {
													if(true) {
														for(int index$sample5$40 = 0; index$sample5$40 < weightings.length; index$sample5$40 += 1) {
															int distributionTempVariable$v1$42 = index$sample5$40;
															double cv$probabilitySample5Value41 = (1.0 * distribution$sample5[index$sample5$40]);
															int traceTempVariable$var44$50_1 = cv$currentValue;
															if(((index$i$2 + 1) == j)) {
																if(fixedFlag$sample11) {
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$12$var47;
																				{
																					double var47 = ((((1.0 * distributionTempVariable$v1$42) + traceTempVariable$var42$6_1) + traceTempVariable$var44$50_1) / v2[j]);
																					cv$temp$12$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample5Value41) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value41) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value41) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value41) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value41) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var47)));
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
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$13$var47;
																						{
																							double var47 = ((((1.0 * distributionTempVariable$v1$42) + traceTempVariable$var42$6_1) + traceTempVariable$var44$50_1) / v2[j]);
																							cv$temp$13$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample11Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value68);
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
																		if(((index$i$51 + 1) == j)) {
																			if(fixedFlag$sample11) {
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$14$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$42) + traceTempVariable$var42$6_1) + traceTempVariable$var44$55_1) / v2[j]);
																								cv$temp$14$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value53) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value53) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value53) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value53) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value53) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var47)));
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
																						if((0 == j)) {
																							{
																								{
																									double cv$temp$15$var47;
																									{
																										double var47 = ((((1.0 * distributionTempVariable$v1$42) + traceTempVariable$var42$6_1) + traceTempVariable$var44$55_1) / v2[j]);
																										cv$temp$15$var47 = var47;
																									}
																									if(((Math.log(cv$probabilitySample11Value73) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var47)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value73) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value73) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var47));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value73) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value73) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var47)));
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
												if(fixedFlag$sample5) {
													if(fixedFlag$sample11) {
														if((0 == j)) {
															int traceTempVariable$var46$91_1 = cv$currentValue;
															if(((index$i$2 + 1) == j)) {
																{
																	{
																		double cv$temp$16$var47;
																		{
																			double var47 = ((((1.0 * v1) + traceTempVariable$var42$6_1) + v2[j]) / traceTempVariable$var46$91_1);
																			cv$temp$16$var47 = var47;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var47)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var47));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var47)));
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
																		int traceTempVariable$var46$96_1 = cv$currentValue;
																		if(((index$i$92 + 1) == j)) {
																			{
																				{
																					double cv$temp$17$var47;
																					{
																						double var47 = ((((1.0 * v1) + traceTempVariable$var42$6_1) + v2[j]) / traceTempVariable$var46$96_1);
																						cv$temp$17$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample27Value94) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value94) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value94) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value94) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value94) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value94);
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
																if((0 == j)) {
																	int traceTempVariable$var46$97_1 = cv$currentValue;
																	if(((index$i$2 + 1) == j)) {
																		{
																			{
																				double cv$temp$18$var47;
																				{
																					double var47 = ((((1.0 * v1) + traceTempVariable$var42$6_1) + v2[j]) / traceTempVariable$var46$97_1);
																					cv$temp$18$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample11Value83) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value83) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value83) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value83) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value83) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var47)));
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
																				int traceTempVariable$var46$102_1 = cv$currentValue;
																				if(((index$i$98 + 1) == j)) {
																					{
																						{
																							double cv$temp$19$var47;
																							{
																								double var47 = ((((1.0 * v1) + traceTempVariable$var42$6_1) + v2[j]) / traceTempVariable$var46$102_1);
																								cv$temp$19$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value100) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value100) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value100) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value100) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value100) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var47)));
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
												} else {
													if(true) {
														for(int index$sample5$77 = 0; index$sample5$77 < weightings.length; index$sample5$77 += 1) {
															int distributionTempVariable$v1$79 = index$sample5$77;
															double cv$probabilitySample5Value78 = (1.0 * distribution$sample5[index$sample5$77]);
															if(fixedFlag$sample11) {
																if((0 == j)) {
																	int traceTempVariable$var46$103_1 = cv$currentValue;
																	if(((index$i$2 + 1) == j)) {
																		{
																			{
																				double cv$temp$20$var47;
																				{
																					double var47 = ((((1.0 * distributionTempVariable$v1$79) + traceTempVariable$var42$6_1) + v2[j]) / traceTempVariable$var46$103_1);
																					cv$temp$20$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample5Value78) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value78) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value78) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value78) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value78) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var47)));
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
																				int traceTempVariable$var46$108_1 = cv$currentValue;
																				if(((index$i$104 + 1) == j)) {
																					{
																						{
																							double cv$temp$21$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$79) + traceTempVariable$var42$6_1) + v2[j]) / traceTempVariable$var46$108_1);
																								cv$temp$21$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value106) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value106) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value106) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value106) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value106) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var47)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value106);
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
																		if((0 == j)) {
																			int traceTempVariable$var46$109_1 = cv$currentValue;
																			if(((index$i$2 + 1) == j)) {
																				{
																					{
																						double cv$temp$22$var47;
																						{
																							double var47 = ((((1.0 * distributionTempVariable$v1$79) + traceTempVariable$var42$6_1) + v2[j]) / traceTempVariable$var46$109_1);
																							cv$temp$22$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample11Value88) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value88) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value88) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value88) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value88) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var47)));
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
																						int traceTempVariable$var46$114_1 = cv$currentValue;
																						if(((index$i$110 + 1) == j)) {
																							{
																								{
																									double cv$temp$23$var47;
																									{
																										double var47 = ((((1.0 * distributionTempVariable$v1$79) + traceTempVariable$var42$6_1) + v2[j]) / traceTempVariable$var46$114_1);
																										cv$temp$23$var47 = var47;
																									}
																									if(((Math.log(cv$probabilitySample27Value112) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var47)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value112) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value112) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var47));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value112) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value112) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var47)));
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
												if(fixedFlag$sample5) {
													int traceTempVariable$var44$120_1 = cv$currentValue;
													if(((index$i$2 + 1) == j)) {
														int traceTempVariable$var46$132_1 = cv$currentValue;
														if(((index$i$2 + 1) == j)) {
															{
																{
																	double cv$temp$24$var47;
																	{
																		double var47 = ((((1.0 * v1) + traceTempVariable$var42$6_1) + traceTempVariable$var44$120_1) / traceTempVariable$var46$132_1);
																		cv$temp$24$var47 = var47;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var47)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var47));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var47)));
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
																	int traceTempVariable$var46$137_1 = cv$currentValue;
																	if(((index$i$133 + 1) == j)) {
																		{
																			{
																				double cv$temp$25$var47;
																				{
																					double var47 = ((((1.0 * v1) + traceTempVariable$var42$6_1) + traceTempVariable$var44$120_1) / traceTempVariable$var46$137_1);
																					cv$temp$25$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample27Value135) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value135) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value135) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value135) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value135) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value135);
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
																if(((index$i$121 + 1) == j)) {
																	int traceTempVariable$var46$138_1 = cv$currentValue;
																	if(((index$i$2 + 1) == j)) {
																		{
																			{
																				double cv$temp$26$var47;
																				{
																					double var47 = ((((1.0 * v1) + traceTempVariable$var42$6_1) + traceTempVariable$var44$125_1) / traceTempVariable$var46$138_1);
																					cv$temp$26$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample27Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value123);
																			}
																		}
																	}
																	int traceTempVariable$var46$139_1 = cv$currentValue;
																	if(((index$i$121 + 1) == j)) {
																		{
																			{
																				double cv$temp$27$var47;
																				{
																					double var47 = ((((1.0 * v1) + traceTempVariable$var42$6_1) + traceTempVariable$var44$125_1) / traceTempVariable$var46$139_1);
																					cv$temp$27$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample27Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var47)));
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
																				int traceTempVariable$var46$144_1 = cv$currentValue;
																				if(((index$i$140 + 1) == j)) {
																					{
																						{
																							double cv$temp$28$var47;
																							{
																								double var47 = ((((1.0 * v1) + traceTempVariable$var42$6_1) + traceTempVariable$var44$125_1) / traceTempVariable$var46$144_1);
																								cv$temp$28$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value142) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value142) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value142) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value142) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value142) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var47)));
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
												} else {
													if(true) {
														for(int index$sample5$116 = 0; index$sample5$116 < weightings.length; index$sample5$116 += 1) {
															int distributionTempVariable$v1$118 = index$sample5$116;
															double cv$probabilitySample5Value117 = (1.0 * distribution$sample5[index$sample5$116]);
															int traceTempVariable$var44$126_1 = cv$currentValue;
															if(((index$i$2 + 1) == j)) {
																int traceTempVariable$var46$145_1 = cv$currentValue;
																if(((index$i$2 + 1) == j)) {
																	{
																		{
																			double cv$temp$29$var47;
																			{
																				double var47 = ((((1.0 * distributionTempVariable$v1$118) + traceTempVariable$var42$6_1) + traceTempVariable$var44$126_1) / traceTempVariable$var46$145_1);
																				cv$temp$29$var47 = var47;
																			}
																			if(((Math.log(cv$probabilitySample5Value117) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value117) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value117) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value117) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value117) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var47)));
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
																			int traceTempVariable$var46$150_1 = cv$currentValue;
																			if(((index$i$146 + 1) == j)) {
																				{
																					{
																						double cv$temp$30$var47;
																						{
																							double var47 = ((((1.0 * distributionTempVariable$v1$118) + traceTempVariable$var42$6_1) + traceTempVariable$var44$126_1) / traceTempVariable$var46$150_1);
																							cv$temp$30$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample27Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value148);
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
																		if(((index$i$127 + 1) == j)) {
																			int traceTempVariable$var46$151_1 = cv$currentValue;
																			if(((index$i$2 + 1) == j)) {
																				{
																					{
																						double cv$temp$31$var47;
																						{
																							double var47 = ((((1.0 * distributionTempVariable$v1$118) + traceTempVariable$var42$6_1) + traceTempVariable$var44$131_1) / traceTempVariable$var46$151_1);
																							cv$temp$31$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample27Value129) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value129) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value129) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value129) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value129) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value129);
																					}
																				}
																			}
																			int traceTempVariable$var46$152_1 = cv$currentValue;
																			if(((index$i$127 + 1) == j)) {
																				{
																					{
																						double cv$temp$32$var47;
																						{
																							double var47 = ((((1.0 * distributionTempVariable$v1$118) + traceTempVariable$var42$6_1) + traceTempVariable$var44$131_1) / traceTempVariable$var46$152_1);
																							cv$temp$32$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample27Value129) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value129) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value129) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value129) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value129) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var47)));
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
																						int traceTempVariable$var46$157_1 = cv$currentValue;
																						if(((index$i$153 + 1) == j)) {
																							{
																								{
																									double cv$temp$33$var47;
																									{
																										double var47 = ((((1.0 * distributionTempVariable$v1$118) + traceTempVariable$var42$6_1) + traceTempVariable$var44$131_1) / traceTempVariable$var46$157_1);
																										cv$temp$33$var47 = var47;
																									}
																									if(((Math.log(cv$probabilitySample27Value155) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var47)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value155) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value155) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var47));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value155) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value155) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var47)));
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
							int traceTempVariable$var44$7_1 = cv$currentValue;
							for(int j = 0; j < size; j += 1) {
								if(((i + 1) == j)) {
									if(!guard$sample27bernoulli48[((j - 0) / 1)]) {
										guard$sample27bernoulli48[((j - 0) / 1)] = true;
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if(fixedFlag$sample5) {
													if(fixedFlag$sample11) {
														if((0 == j)) {
															if((0 == j)) {
																{
																	{
																		double cv$temp$34$var47;
																		{
																			double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$7_1) / v2[j]);
																			cv$temp$34$var47 = var47;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var47)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var47));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var47)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$35$var47;
																				{
																					double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$7_1) / v2[j]);
																					cv$temp$35$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample11Value165) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value165) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value165) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value165) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value165) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value165);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample11$175 = 0; index$sample11$175 < weightings.length; index$sample11$175 += 1) {
																			int distributionTempVariable$var11$177 = index$sample11$175;
																			double cv$probabilitySample11Value176 = (cv$probabilitySample11Value165 * distribution$sample11[index$sample11$175]);
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$36$var47;
																						{
																							double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$7_1) / v2[j]);
																							cv$temp$36$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample11Value176) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value176) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value176) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value176) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value176) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var47)));
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
												} else {
													if(true) {
														for(int index$sample5$159 = 0; index$sample5$159 < weightings.length; index$sample5$159 += 1) {
															int distributionTempVariable$v1$161 = index$sample5$159;
															double cv$probabilitySample5Value160 = (1.0 * distribution$sample5[index$sample5$159]);
															if(fixedFlag$sample11) {
																if((0 == j)) {
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$37$var47;
																				{
																					double var47 = ((((1.0 * distributionTempVariable$v1$161) + v2[j]) + traceTempVariable$var44$7_1) / v2[j]);
																					cv$temp$37$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample5Value160) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value160) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value160) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value160) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value160) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value160);
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
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$38$var47;
																						{
																							double var47 = ((((1.0 * distributionTempVariable$v1$161) + v2[j]) + traceTempVariable$var44$7_1) / v2[j]);
																							cv$temp$38$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample11Value170) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value170) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value170) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value170) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value170) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value170);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample11$181 = 0; index$sample11$181 < weightings.length; index$sample11$181 += 1) {
																					int distributionTempVariable$var11$183 = index$sample11$181;
																					double cv$probabilitySample11Value182 = (cv$probabilitySample11Value170 * distribution$sample11[index$sample11$181]);
																					if((0 == j)) {
																						{
																							{
																								double cv$temp$39$var47;
																								{
																									double var47 = ((((1.0 * distributionTempVariable$v1$161) + v2[j]) + traceTempVariable$var44$7_1) / v2[j]);
																									cv$temp$39$var47 = var47;
																								}
																								if(((Math.log(cv$probabilitySample11Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var47)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var47));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value182) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var47)));
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
												if(fixedFlag$sample5) {
													int traceTempVariable$var42$190_1 = cv$currentValue;
													if(((index$i$2 + 1) == j)) {
														if(fixedFlag$sample11) {
															if((0 == j)) {
																{
																	{
																		double cv$temp$40$var47;
																		{
																			double var47 = ((((1.0 * v1) + traceTempVariable$var42$190_1) + traceTempVariable$var44$7_1) / v2[j]);
																			cv$temp$40$var47 = var47;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var47)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var47));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var47)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														} else {
															if(true) {
																for(int index$sample11$203 = 0; index$sample11$203 < weightings.length; index$sample11$203 += 1) {
																	int distributionTempVariable$var11$205 = index$sample11$203;
																	double cv$probabilitySample11Value204 = (1.0 * distribution$sample11[index$sample11$203]);
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$41$var47;
																				{
																					double var47 = ((((1.0 * v1) + traceTempVariable$var42$190_1) + traceTempVariable$var44$7_1) / v2[j]);
																					cv$temp$41$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample11Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value204);
																			}
																		}
																	}
																}
															}
														}
													}
													for(int index$i$191 = 0; index$i$191 < size; index$i$191 += 1) {
														if(!(index$i$191 == index$i$2)) {
															for(int index$sample27$192 = 0; index$sample27$192 < weightings.length; index$sample27$192 += 1) {
																int distributionTempVariable$var27$194 = index$sample27$192;
																double cv$probabilitySample27Value193 = (1.0 * distribution$sample27[((index$i$191 - 0) / 1)][index$sample27$192]);
																int traceTempVariable$var42$195_1 = cv$currentValue;
																if(((index$i$191 + 1) == j)) {
																	if(fixedFlag$sample11) {
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$42$var47;
																					{
																						double var47 = ((((1.0 * v1) + traceTempVariable$var42$195_1) + traceTempVariable$var44$7_1) / v2[j]);
																						cv$temp$42$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample27Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value193) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value193);
																				}
																			}
																		}
																	} else {
																		if(true) {
																			for(int index$sample11$208 = 0; index$sample11$208 < weightings.length; index$sample11$208 += 1) {
																				int distributionTempVariable$var11$210 = index$sample11$208;
																				double cv$probabilitySample11Value209 = (cv$probabilitySample27Value193 * distribution$sample11[index$sample11$208]);
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$43$var47;
																							{
																								double var47 = ((((1.0 * v1) + traceTempVariable$var42$195_1) + traceTempVariable$var44$7_1) / v2[j]);
																								cv$temp$43$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample11Value209) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value209) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value209) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value209) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value209) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var47)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value209);
																						}
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
															int traceTempVariable$var42$196_1 = cv$currentValue;
															if(((index$i$2 + 1) == j)) {
																if(fixedFlag$sample11) {
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$44$var47;
																				{
																					double var47 = ((((1.0 * distributionTempVariable$v1$188) + traceTempVariable$var42$196_1) + traceTempVariable$var44$7_1) / v2[j]);
																					cv$temp$44$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample5Value187) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value187) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value187) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value187) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value187) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value187);
																			}
																		}
																	}
																} else {
																	if(true) {
																		for(int index$sample11$213 = 0; index$sample11$213 < weightings.length; index$sample11$213 += 1) {
																			int distributionTempVariable$var11$215 = index$sample11$213;
																			double cv$probabilitySample11Value214 = (cv$probabilitySample5Value187 * distribution$sample11[index$sample11$213]);
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$45$var47;
																						{
																							double var47 = ((((1.0 * distributionTempVariable$v1$188) + traceTempVariable$var42$196_1) + traceTempVariable$var44$7_1) / v2[j]);
																							cv$temp$45$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample11Value214) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value214) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value214) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value214) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value214) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value214);
																					}
																				}
																			}
																		}
																	}
																}
															}
															for(int index$i$197 = 0; index$i$197 < size; index$i$197 += 1) {
																if(!(index$i$197 == index$i$2)) {
																	for(int index$sample27$198 = 0; index$sample27$198 < weightings.length; index$sample27$198 += 1) {
																		int distributionTempVariable$var27$200 = index$sample27$198;
																		double cv$probabilitySample27Value199 = (cv$probabilitySample5Value187 * distribution$sample27[((index$i$197 - 0) / 1)][index$sample27$198]);
																		int traceTempVariable$var42$201_1 = cv$currentValue;
																		if(((index$i$197 + 1) == j)) {
																			if(fixedFlag$sample11) {
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$46$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$188) + traceTempVariable$var42$201_1) + traceTempVariable$var44$7_1) / v2[j]);
																								cv$temp$46$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value199) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value199) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value199) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value199) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value199) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var47)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value199);
																						}
																					}
																				}
																			} else {
																				if(true) {
																					for(int index$sample11$218 = 0; index$sample11$218 < weightings.length; index$sample11$218 += 1) {
																						int distributionTempVariable$var11$220 = index$sample11$218;
																						double cv$probabilitySample11Value219 = (cv$probabilitySample27Value199 * distribution$sample11[index$sample11$218]);
																						if((0 == j)) {
																							{
																								{
																									double cv$temp$47$var47;
																									{
																										double var47 = ((((1.0 * distributionTempVariable$v1$188) + traceTempVariable$var42$201_1) + traceTempVariable$var44$7_1) / v2[j]);
																										cv$temp$47$var47 = var47;
																									}
																									if(((Math.log(cv$probabilitySample11Value219) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var47)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value219) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value219) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var47));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value219) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value219) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var47)));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value219);
																								}
																							}
																						}
																					}
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
															int traceTempVariable$var46$237_1 = cv$currentValue;
															if(((index$i$2 + 1) == j)) {
																{
																	{
																		double cv$temp$48$var47;
																		{
																			double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$7_1) / traceTempVariable$var46$237_1);
																			cv$temp$48$var47 = var47;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var47)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var47));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var47)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
															for(int index$i$238 = 0; index$i$238 < size; index$i$238 += 1) {
																if(!(index$i$238 == index$i$2)) {
																	for(int index$sample27$239 = 0; index$sample27$239 < weightings.length; index$sample27$239 += 1) {
																		int distributionTempVariable$var27$241 = index$sample27$239;
																		double cv$probabilitySample27Value240 = (1.0 * distribution$sample27[((index$i$238 - 0) / 1)][index$sample27$239]);
																		int traceTempVariable$var46$242_1 = cv$currentValue;
																		if(((index$i$238 + 1) == j)) {
																			{
																				{
																					double cv$temp$49$var47;
																					{
																						double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$7_1) / traceTempVariable$var46$242_1);
																						cv$temp$49$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample27Value240) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value240) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value240) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value240) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value240) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value240);
																				}
																			}
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample11$228 = 0; index$sample11$228 < weightings.length; index$sample11$228 += 1) {
																int distributionTempVariable$var11$230 = index$sample11$228;
																double cv$probabilitySample11Value229 = (1.0 * distribution$sample11[index$sample11$228]);
																if((0 == j)) {
																	int traceTempVariable$var46$243_1 = cv$currentValue;
																	if(((index$i$2 + 1) == j)) {
																		{
																			{
																				double cv$temp$50$var47;
																				{
																					double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$7_1) / traceTempVariable$var46$243_1);
																					cv$temp$50$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample11Value229) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value229) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value229) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value229) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value229) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$50$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value229);
																			}
																		}
																	}
																	for(int index$i$244 = 0; index$i$244 < size; index$i$244 += 1) {
																		if(!(index$i$244 == index$i$2)) {
																			for(int index$sample27$245 = 0; index$sample27$245 < weightings.length; index$sample27$245 += 1) {
																				int distributionTempVariable$var27$247 = index$sample27$245;
																				double cv$probabilitySample27Value246 = (cv$probabilitySample11Value229 * distribution$sample27[((index$i$244 - 0) / 1)][index$sample27$245]);
																				int traceTempVariable$var46$248_1 = cv$currentValue;
																				if(((index$i$244 + 1) == j)) {
																					{
																						{
																							double cv$temp$51$var47;
																							{
																								double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$7_1) / traceTempVariable$var46$248_1);
																								cv$temp$51$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value246) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value246) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value246) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value246) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value246) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$51$var47)));
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
												} else {
													if(true) {
														for(int index$sample5$223 = 0; index$sample5$223 < weightings.length; index$sample5$223 += 1) {
															int distributionTempVariable$v1$225 = index$sample5$223;
															double cv$probabilitySample5Value224 = (1.0 * distribution$sample5[index$sample5$223]);
															if(fixedFlag$sample11) {
																if((0 == j)) {
																	int traceTempVariable$var46$249_1 = cv$currentValue;
																	if(((index$i$2 + 1) == j)) {
																		{
																			{
																				double cv$temp$52$var47;
																				{
																					double var47 = ((((1.0 * distributionTempVariable$v1$225) + v2[j]) + traceTempVariable$var44$7_1) / traceTempVariable$var46$249_1);
																					cv$temp$52$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample5Value224) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value224) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value224) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value224) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value224) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$52$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value224);
																			}
																		}
																	}
																	for(int index$i$250 = 0; index$i$250 < size; index$i$250 += 1) {
																		if(!(index$i$250 == index$i$2)) {
																			for(int index$sample27$251 = 0; index$sample27$251 < weightings.length; index$sample27$251 += 1) {
																				int distributionTempVariable$var27$253 = index$sample27$251;
																				double cv$probabilitySample27Value252 = (cv$probabilitySample5Value224 * distribution$sample27[((index$i$250 - 0) / 1)][index$sample27$251]);
																				int traceTempVariable$var46$254_1 = cv$currentValue;
																				if(((index$i$250 + 1) == j)) {
																					{
																						{
																							double cv$temp$53$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$225) + v2[j]) + traceTempVariable$var44$7_1) / traceTempVariable$var46$254_1);
																								cv$temp$53$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value252) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$53$var47)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value252);
																						}
																					}
																				}
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample11$233 = 0; index$sample11$233 < weightings.length; index$sample11$233 += 1) {
																		int distributionTempVariable$var11$235 = index$sample11$233;
																		double cv$probabilitySample11Value234 = (cv$probabilitySample5Value224 * distribution$sample11[index$sample11$233]);
																		if((0 == j)) {
																			int traceTempVariable$var46$255_1 = cv$currentValue;
																			if(((index$i$2 + 1) == j)) {
																				{
																					{
																						double cv$temp$54$var47;
																						{
																							double var47 = ((((1.0 * distributionTempVariable$v1$225) + v2[j]) + traceTempVariable$var44$7_1) / traceTempVariable$var46$255_1);
																							cv$temp$54$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample11Value234) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value234) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value234) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value234) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value234) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$54$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value234);
																					}
																				}
																			}
																			for(int index$i$256 = 0; index$i$256 < size; index$i$256 += 1) {
																				if(!(index$i$256 == index$i$2)) {
																					for(int index$sample27$257 = 0; index$sample27$257 < weightings.length; index$sample27$257 += 1) {
																						int distributionTempVariable$var27$259 = index$sample27$257;
																						double cv$probabilitySample27Value258 = (cv$probabilitySample11Value234 * distribution$sample27[((index$i$256 - 0) / 1)][index$sample27$257]);
																						int traceTempVariable$var46$260_1 = cv$currentValue;
																						if(((index$i$256 + 1) == j)) {
																							{
																								{
																									double cv$temp$55$var47;
																									{
																										double var47 = ((((1.0 * distributionTempVariable$v1$225) + v2[j]) + traceTempVariable$var44$7_1) / traceTempVariable$var46$260_1);
																										cv$temp$55$var47 = var47;
																									}
																									if(((Math.log(cv$probabilitySample27Value258) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var47)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value258) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value258) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var47));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value258) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value258) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$55$var47)));
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
													}
												}
												if(fixedFlag$sample5) {
													int traceTempVariable$var42$266_1 = cv$currentValue;
													if(((index$i$2 + 1) == j)) {
														int traceTempVariable$var46$278_1 = cv$currentValue;
														if(((index$i$2 + 1) == j)) {
															{
																{
																	double cv$temp$56$var47;
																	{
																		double var47 = ((((1.0 * v1) + traceTempVariable$var42$266_1) + traceTempVariable$var44$7_1) / traceTempVariable$var46$278_1);
																		cv$temp$56$var47 = var47;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var47)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var47));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var47)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
														for(int index$i$279 = 0; index$i$279 < size; index$i$279 += 1) {
															if(!(index$i$279 == index$i$2)) {
																for(int index$sample27$280 = 0; index$sample27$280 < weightings.length; index$sample27$280 += 1) {
																	int distributionTempVariable$var27$282 = index$sample27$280;
																	double cv$probabilitySample27Value281 = (1.0 * distribution$sample27[((index$i$279 - 0) / 1)][index$sample27$280]);
																	int traceTempVariable$var46$283_1 = cv$currentValue;
																	if(((index$i$279 + 1) == j)) {
																		{
																			{
																				double cv$temp$57$var47;
																				{
																					double var47 = ((((1.0 * v1) + traceTempVariable$var42$266_1) + traceTempVariable$var44$7_1) / traceTempVariable$var46$283_1);
																					cv$temp$57$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample27Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value281);
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
																int traceTempVariable$var42$271_1 = cv$currentValue;
																if(((index$i$267 + 1) == j)) {
																	int traceTempVariable$var46$284_1 = cv$currentValue;
																	if(((index$i$2 + 1) == j)) {
																		{
																			{
																				double cv$temp$58$var47;
																				{
																					double var47 = ((((1.0 * v1) + traceTempVariable$var42$271_1) + traceTempVariable$var44$7_1) / traceTempVariable$var46$284_1);
																					cv$temp$58$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample27Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value269);
																			}
																		}
																	}
																	int traceTempVariable$var46$285_1 = cv$currentValue;
																	if(((index$i$267 + 1) == j)) {
																		{
																			{
																				double cv$temp$59$var47;
																				{
																					double var47 = ((((1.0 * v1) + traceTempVariable$var42$271_1) + traceTempVariable$var44$7_1) / traceTempVariable$var46$285_1);
																					cv$temp$59$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample27Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$59$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value269);
																			}
																		}
																	}
																	for(int index$i$286 = 0; index$i$286 < size; index$i$286 += 1) {
																		if((!(index$i$286 == index$i$2) && !(index$i$286 == index$i$267))) {
																			for(int index$sample27$287 = 0; index$sample27$287 < weightings.length; index$sample27$287 += 1) {
																				int distributionTempVariable$var27$289 = index$sample27$287;
																				double cv$probabilitySample27Value288 = (cv$probabilitySample27Value269 * distribution$sample27[((index$i$286 - 0) / 1)][index$sample27$287]);
																				int traceTempVariable$var46$290_1 = cv$currentValue;
																				if(((index$i$286 + 1) == j)) {
																					{
																						{
																							double cv$temp$60$var47;
																							{
																								double var47 = ((((1.0 * v1) + traceTempVariable$var42$271_1) + traceTempVariable$var44$7_1) / traceTempVariable$var46$290_1);
																								cv$temp$60$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value288) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$60$var47)));
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
												} else {
													if(true) {
														for(int index$sample5$262 = 0; index$sample5$262 < weightings.length; index$sample5$262 += 1) {
															int distributionTempVariable$v1$264 = index$sample5$262;
															double cv$probabilitySample5Value263 = (1.0 * distribution$sample5[index$sample5$262]);
															int traceTempVariable$var42$272_1 = cv$currentValue;
															if(((index$i$2 + 1) == j)) {
																int traceTempVariable$var46$291_1 = cv$currentValue;
																if(((index$i$2 + 1) == j)) {
																	{
																		{
																			double cv$temp$61$var47;
																			{
																				double var47 = ((((1.0 * distributionTempVariable$v1$264) + traceTempVariable$var42$272_1) + traceTempVariable$var44$7_1) / traceTempVariable$var46$291_1);
																				cv$temp$61$var47 = var47;
																			}
																			if(((Math.log(cv$probabilitySample5Value263) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value263) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value263) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value263) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value263) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$61$var47)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value263);
																		}
																	}
																}
																for(int index$i$292 = 0; index$i$292 < size; index$i$292 += 1) {
																	if(!(index$i$292 == index$i$2)) {
																		for(int index$sample27$293 = 0; index$sample27$293 < weightings.length; index$sample27$293 += 1) {
																			int distributionTempVariable$var27$295 = index$sample27$293;
																			double cv$probabilitySample27Value294 = (cv$probabilitySample5Value263 * distribution$sample27[((index$i$292 - 0) / 1)][index$sample27$293]);
																			int traceTempVariable$var46$296_1 = cv$currentValue;
																			if(((index$i$292 + 1) == j)) {
																				{
																					{
																						double cv$temp$62$var47;
																						{
																							double var47 = ((((1.0 * distributionTempVariable$v1$264) + traceTempVariable$var42$272_1) + traceTempVariable$var44$7_1) / traceTempVariable$var46$296_1);
																							cv$temp$62$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample27Value294) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value294) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value294) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value294) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value294) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$62$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value294);
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
																		int traceTempVariable$var42$277_1 = cv$currentValue;
																		if(((index$i$273 + 1) == j)) {
																			int traceTempVariable$var46$297_1 = cv$currentValue;
																			if(((index$i$2 + 1) == j)) {
																				{
																					{
																						double cv$temp$63$var47;
																						{
																							double var47 = ((((1.0 * distributionTempVariable$v1$264) + traceTempVariable$var42$277_1) + traceTempVariable$var44$7_1) / traceTempVariable$var46$297_1);
																							cv$temp$63$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$63$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value275);
																					}
																				}
																			}
																			int traceTempVariable$var46$298_1 = cv$currentValue;
																			if(((index$i$273 + 1) == j)) {
																				{
																					{
																						double cv$temp$64$var47;
																						{
																							double var47 = ((((1.0 * distributionTempVariable$v1$264) + traceTempVariable$var42$277_1) + traceTempVariable$var44$7_1) / traceTempVariable$var46$298_1);
																							cv$temp$64$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$64$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value275);
																					}
																				}
																			}
																			for(int index$i$299 = 0; index$i$299 < size; index$i$299 += 1) {
																				if((!(index$i$299 == index$i$2) && !(index$i$299 == index$i$273))) {
																					for(int index$sample27$300 = 0; index$sample27$300 < weightings.length; index$sample27$300 += 1) {
																						int distributionTempVariable$var27$302 = index$sample27$300;
																						double cv$probabilitySample27Value301 = (cv$probabilitySample27Value275 * distribution$sample27[((index$i$299 - 0) / 1)][index$sample27$300]);
																						int traceTempVariable$var46$303_1 = cv$currentValue;
																						if(((index$i$299 + 1) == j)) {
																							{
																								{
																									double cv$temp$65$var47;
																									{
																										double var47 = ((((1.0 * distributionTempVariable$v1$264) + traceTempVariable$var42$277_1) + traceTempVariable$var44$7_1) / traceTempVariable$var46$303_1);
																										cv$temp$65$var47 = var47;
																									}
																									if(((Math.log(cv$probabilitySample27Value301) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var47)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value301) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value301) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var47));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value301) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value301) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$65$var47)));
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
							int traceTempVariable$var46$8_1 = cv$currentValue;
							for(int j = 0; j < size; j += 1) {
								if(((i + 1) == j)) {
									if(!guard$sample27bernoulli48[((j - 0) / 1)]) {
										guard$sample27bernoulli48[((j - 0) / 1)] = true;
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												if(fixedFlag$sample5) {
													if(fixedFlag$sample11) {
														if((0 == j)) {
															if((0 == j)) {
																{
																	{
																		double cv$temp$66$var47;
																		{
																			double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / traceTempVariable$var46$8_1);
																			cv$temp$66$var47 = var47;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var47)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var47));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$66$var47)));
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
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$67$var47;
																				{
																					double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / traceTempVariable$var46$8_1);
																					cv$temp$67$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample11Value311) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value311) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value311) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value311) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value311) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$67$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value311);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample11$321 = 0; index$sample11$321 < weightings.length; index$sample11$321 += 1) {
																			int distributionTempVariable$var11$323 = index$sample11$321;
																			double cv$probabilitySample11Value322 = (cv$probabilitySample11Value311 * distribution$sample11[index$sample11$321]);
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$68$var47;
																						{
																							double var47 = ((((1.0 * v1) + v2[j]) + v2[j]) / traceTempVariable$var46$8_1);
																							cv$temp$68$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample11Value322) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value322) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value322) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value322) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value322) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$68$var47)));
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
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$69$var47;
																				{
																					double var47 = ((((1.0 * distributionTempVariable$v1$307) + v2[j]) + v2[j]) / traceTempVariable$var46$8_1);
																					cv$temp$69$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample5Value306) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value306) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value306) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value306) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value306) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$69$var47)));
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
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$70$var47;
																						{
																							double var47 = ((((1.0 * distributionTempVariable$v1$307) + v2[j]) + v2[j]) / traceTempVariable$var46$8_1);
																							cv$temp$70$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample11Value316) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value316) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value316) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value316) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value316) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$70$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value316);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample11$327 = 0; index$sample11$327 < weightings.length; index$sample11$327 += 1) {
																					int distributionTempVariable$var11$329 = index$sample11$327;
																					double cv$probabilitySample11Value328 = (cv$probabilitySample11Value316 * distribution$sample11[index$sample11$327]);
																					if((0 == j)) {
																						{
																							{
																								double cv$temp$71$var47;
																								{
																									double var47 = ((((1.0 * distributionTempVariable$v1$307) + v2[j]) + v2[j]) / traceTempVariable$var46$8_1);
																									cv$temp$71$var47 = var47;
																								}
																								if(((Math.log(cv$probabilitySample11Value328) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var47)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value328) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value328) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var47));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value328) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value328) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$71$var47)));
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
													int traceTempVariable$var42$336_1 = cv$currentValue;
													if(((index$i$2 + 1) == j)) {
														if(fixedFlag$sample11) {
															if((0 == j)) {
																{
																	{
																		double cv$temp$72$var47;
																		{
																			double var47 = ((((1.0 * v1) + traceTempVariable$var42$336_1) + v2[j]) / traceTempVariable$var46$8_1);
																			cv$temp$72$var47 = var47;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var47)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var47));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$72$var47)));
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
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$73$var47;
																				{
																					double var47 = ((((1.0 * v1) + traceTempVariable$var42$336_1) + v2[j]) / traceTempVariable$var46$8_1);
																					cv$temp$73$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample11Value350) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value350) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value350) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value350) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value350) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$73$var47)));
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
																int traceTempVariable$var42$341_1 = cv$currentValue;
																if(((index$i$337 + 1) == j)) {
																	if(fixedFlag$sample11) {
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$74$var47;
																					{
																						double var47 = ((((1.0 * v1) + traceTempVariable$var42$341_1) + v2[j]) / traceTempVariable$var46$8_1);
																						cv$temp$74$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample27Value339) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value339) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value339) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value339) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value339) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$74$var47)));
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
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$75$var47;
																							{
																								double var47 = ((((1.0 * v1) + traceTempVariable$var42$341_1) + v2[j]) / traceTempVariable$var46$8_1);
																								cv$temp$75$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample11Value355) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value355) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value355) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value355) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value355) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$75$var47)));
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
															int traceTempVariable$var42$342_1 = cv$currentValue;
															if(((index$i$2 + 1) == j)) {
																if(fixedFlag$sample11) {
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$76$var47;
																				{
																					double var47 = ((((1.0 * distributionTempVariable$v1$334) + traceTempVariable$var42$342_1) + v2[j]) / traceTempVariable$var46$8_1);
																					cv$temp$76$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample5Value333) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value333) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value333) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value333) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value333) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$76$var47)));
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
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$77$var47;
																						{
																							double var47 = ((((1.0 * distributionTempVariable$v1$334) + traceTempVariable$var42$342_1) + v2[j]) / traceTempVariable$var46$8_1);
																							cv$temp$77$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample11Value360) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value360) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value360) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value360) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value360) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$77$var47)));
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
																		int traceTempVariable$var42$347_1 = cv$currentValue;
																		if(((index$i$343 + 1) == j)) {
																			if(fixedFlag$sample11) {
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$78$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$334) + traceTempVariable$var42$347_1) + v2[j]) / traceTempVariable$var46$8_1);
																								cv$temp$78$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value345) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value345) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value345) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value345) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value345) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$78$var47)));
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
																						if((0 == j)) {
																							{
																								{
																									double cv$temp$79$var47;
																									{
																										double var47 = ((((1.0 * distributionTempVariable$v1$334) + traceTempVariable$var42$347_1) + v2[j]) / traceTempVariable$var46$8_1);
																										cv$temp$79$var47 = var47;
																									}
																									if(((Math.log(cv$probabilitySample11Value365) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var47)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value365) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value365) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var47));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value365) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value365) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$79$var47)));
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
															int traceTempVariable$var44$383_1 = cv$currentValue;
															if(((index$i$2 + 1) == j)) {
																{
																	{
																		double cv$temp$80$var47;
																		{
																			double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$383_1) / traceTempVariable$var46$8_1);
																			cv$temp$80$var47 = var47;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var47)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var47));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$80$var47)));
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
																		int traceTempVariable$var44$388_1 = cv$currentValue;
																		if(((index$i$384 + 1) == j)) {
																			{
																				{
																					double cv$temp$81$var47;
																					{
																						double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$388_1) / traceTempVariable$var46$8_1);
																						cv$temp$81$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample27Value386) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value386) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value386) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value386) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value386) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$81$var47)));
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
																	int traceTempVariable$var44$389_1 = cv$currentValue;
																	if(((index$i$2 + 1) == j)) {
																		{
																			{
																				double cv$temp$82$var47;
																				{
																					double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$389_1) / traceTempVariable$var46$8_1);
																					cv$temp$82$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample11Value375) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value375) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value375) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value375) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value375) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$82$var47)));
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
																				int traceTempVariable$var44$394_1 = cv$currentValue;
																				if(((index$i$390 + 1) == j)) {
																					{
																						{
																							double cv$temp$83$var47;
																							{
																								double var47 = ((((1.0 * v1) + v2[j]) + traceTempVariable$var44$394_1) / traceTempVariable$var46$8_1);
																								cv$temp$83$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value392) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value392) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value392) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value392) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value392) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$83$var47)));
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
																	int traceTempVariable$var44$395_1 = cv$currentValue;
																	if(((index$i$2 + 1) == j)) {
																		{
																			{
																				double cv$temp$84$var47;
																				{
																					double var47 = ((((1.0 * distributionTempVariable$v1$371) + v2[j]) + traceTempVariable$var44$395_1) / traceTempVariable$var46$8_1);
																					cv$temp$84$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample5Value370) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value370) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value370) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value370) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value370) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$84$var47)));
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
																				int traceTempVariable$var44$400_1 = cv$currentValue;
																				if(((index$i$396 + 1) == j)) {
																					{
																						{
																							double cv$temp$85$var47;
																							{
																								double var47 = ((((1.0 * distributionTempVariable$v1$371) + v2[j]) + traceTempVariable$var44$400_1) / traceTempVariable$var46$8_1);
																								cv$temp$85$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value398) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value398) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value398) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value398) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value398) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$85$var47)));
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
																			int traceTempVariable$var44$401_1 = cv$currentValue;
																			if(((index$i$2 + 1) == j)) {
																				{
																					{
																						double cv$temp$86$var47;
																						{
																							double var47 = ((((1.0 * distributionTempVariable$v1$371) + v2[j]) + traceTempVariable$var44$401_1) / traceTempVariable$var46$8_1);
																							cv$temp$86$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample11Value380) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value380) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value380) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value380) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value380) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$86$var47)));
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
																						int traceTempVariable$var44$406_1 = cv$currentValue;
																						if(((index$i$402 + 1) == j)) {
																							{
																								{
																									double cv$temp$87$var47;
																									{
																										double var47 = ((((1.0 * distributionTempVariable$v1$371) + v2[j]) + traceTempVariable$var44$406_1) / traceTempVariable$var46$8_1);
																										cv$temp$87$var47 = var47;
																									}
																									if(((Math.log(cv$probabilitySample27Value404) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var47)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value404) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value404) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var47));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value404) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value404) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$87$var47)));
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
													int traceTempVariable$var42$412_1 = cv$currentValue;
													if(((index$i$2 + 1) == j)) {
														int traceTempVariable$var44$424_1 = cv$currentValue;
														if(((index$i$2 + 1) == j)) {
															{
																{
																	double cv$temp$88$var47;
																	{
																		double var47 = ((((1.0 * v1) + traceTempVariable$var42$412_1) + traceTempVariable$var44$424_1) / traceTempVariable$var46$8_1);
																		cv$temp$88$var47 = var47;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var47)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var47));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var47)));
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
																	int traceTempVariable$var44$429_1 = cv$currentValue;
																	if(((index$i$425 + 1) == j)) {
																		{
																			{
																				double cv$temp$89$var47;
																				{
																					double var47 = ((((1.0 * v1) + traceTempVariable$var42$412_1) + traceTempVariable$var44$429_1) / traceTempVariable$var46$8_1);
																					cv$temp$89$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample27Value427) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value427) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value427) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value427) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value427) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var47)));
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
																int traceTempVariable$var42$417_1 = cv$currentValue;
																if(((index$i$413 + 1) == j)) {
																	int traceTempVariable$var44$430_1 = cv$currentValue;
																	if(((index$i$2 + 1) == j)) {
																		{
																			{
																				double cv$temp$90$var47;
																				{
																					double var47 = ((((1.0 * v1) + traceTempVariable$var42$417_1) + traceTempVariable$var44$430_1) / traceTempVariable$var46$8_1);
																					cv$temp$90$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample27Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value415);
																			}
																		}
																	}
																	int traceTempVariable$var44$431_1 = cv$currentValue;
																	if(((index$i$413 + 1) == j)) {
																		{
																			{
																				double cv$temp$91$var47;
																				{
																					double var47 = ((((1.0 * v1) + traceTempVariable$var42$417_1) + traceTempVariable$var44$431_1) / traceTempVariable$var46$8_1);
																					cv$temp$91$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample27Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$91$var47)));
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
																				int traceTempVariable$var44$436_1 = cv$currentValue;
																				if(((index$i$432 + 1) == j)) {
																					{
																						{
																							double cv$temp$92$var47;
																							{
																								double var47 = ((((1.0 * v1) + traceTempVariable$var42$417_1) + traceTempVariable$var44$436_1) / traceTempVariable$var46$8_1);
																								cv$temp$92$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value434) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value434) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value434) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value434) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value434) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$92$var47)));
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
															int traceTempVariable$var42$418_1 = cv$currentValue;
															if(((index$i$2 + 1) == j)) {
																int traceTempVariable$var44$437_1 = cv$currentValue;
																if(((index$i$2 + 1) == j)) {
																	{
																		{
																			double cv$temp$93$var47;
																			{
																				double var47 = ((((1.0 * distributionTempVariable$v1$410) + traceTempVariable$var42$418_1) + traceTempVariable$var44$437_1) / traceTempVariable$var46$8_1);
																				cv$temp$93$var47 = var47;
																			}
																			if(((Math.log(cv$probabilitySample5Value409) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value409) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value409) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value409) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var47)))) + 1)) + (Math.log(cv$probabilitySample5Value409) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$93$var47)));
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
																			int traceTempVariable$var44$442_1 = cv$currentValue;
																			if(((index$i$438 + 1) == j)) {
																				{
																					{
																						double cv$temp$94$var47;
																						{
																							double var47 = ((((1.0 * distributionTempVariable$v1$410) + traceTempVariable$var42$418_1) + traceTempVariable$var44$442_1) / traceTempVariable$var46$8_1);
																							cv$temp$94$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample27Value440) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value440) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value440) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value440) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value440) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$94$var47)));
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
																		int traceTempVariable$var42$423_1 = cv$currentValue;
																		if(((index$i$419 + 1) == j)) {
																			int traceTempVariable$var44$443_1 = cv$currentValue;
																			if(((index$i$2 + 1) == j)) {
																				{
																					{
																						double cv$temp$95$var47;
																						{
																							double var47 = ((((1.0 * distributionTempVariable$v1$410) + traceTempVariable$var42$423_1) + traceTempVariable$var44$443_1) / traceTempVariable$var46$8_1);
																							cv$temp$95$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample27Value421) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value421) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value421) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value421) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value421) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$95$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value421);
																					}
																				}
																			}
																			int traceTempVariable$var44$444_1 = cv$currentValue;
																			if(((index$i$419 + 1) == j)) {
																				{
																					{
																						double cv$temp$96$var47;
																						{
																							double var47 = ((((1.0 * distributionTempVariable$v1$410) + traceTempVariable$var42$423_1) + traceTempVariable$var44$444_1) / traceTempVariable$var46$8_1);
																							cv$temp$96$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample27Value421) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value421) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value421) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value421) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value421) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$96$var47)));
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
																						int traceTempVariable$var44$449_1 = cv$currentValue;
																						if(((index$i$445 + 1) == j)) {
																							{
																								{
																									double cv$temp$97$var47;
																									{
																										double var47 = ((((1.0 * distributionTempVariable$v1$410) + traceTempVariable$var42$423_1) + traceTempVariable$var44$449_1) / traceTempVariable$var46$8_1);
																										cv$temp$97$var47 = var47;
																									}
																									if(((Math.log(cv$probabilitySample27Value447) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$97$var47)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value447) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$97$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value447) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$97$var47));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value447) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$97$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value447) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$97$var47)));
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
					int cv$temp$1$$var133;
					{
						cv$temp$1$$var133 = weightings.length;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var133))?Math.log(cv$temp$0$weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
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
												if((0 == j)) {
													if((0 == j)) {
														{
															{
																double cv$temp$2$var47;
																{
																	double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																	cv$temp$2$var47 = var47;
																}
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var47)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var47));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var47)));
																}
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
														if((0 == j)) {
															if((0 == j)) {
																{
																	{
																		double cv$temp$3$var47;
																		{
																			double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																			cv$temp$3$var47 = var47;
																		}
																		if(((Math.log(cv$probabilitySample11Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var47)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var47));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var47)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value5);
																	}
																}
															}
															if(!true) {
																for(int index$sample11$16 = 0; index$sample11$16 < weightings.length; index$sample11$16 += 1) {
																	int distributionTempVariable$var11$18 = index$sample11$16;
																	double cv$probabilitySample11Value17 = (cv$probabilitySample11Value5 * distribution$sample11[index$sample11$16]);
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$4$var47;
																				{
																					double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																					cv$temp$4$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample11Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value17) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value17);
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
																if((0 == j)) {
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$5$var47;
																				{
																					double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																					cv$temp$5$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample11Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value11);
																			}
																		}
																	}
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$6$var47;
																				{
																					double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																					cv$temp$6$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample11Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value11);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample11$22 = 0; index$sample11$22 < weightings.length; index$sample11$22 += 1) {
																			int distributionTempVariable$var11$24 = index$sample11$22;
																			double cv$probabilitySample11Value23 = (cv$probabilitySample11Value11 * distribution$sample11[index$sample11$22]);
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$7$var47;
																						{
																							double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																							cv$temp$7$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample11Value23) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value23) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value23) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value23) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value23) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var47)));
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
										if(fixedFlag$sample27) {
											for(int i = 0; i < size; i += 1) {
												if(((i + 1) == j)) {
													if(fixedFlag$sample11) {
														if((0 == j)) {
															if((0 == j)) {
																{
																	{
																		double cv$temp$8$var47;
																		{
																			double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																			cv$temp$8$var47 = var47;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var47)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var47));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var47)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample11$33 = 0; index$sample11$33 < weightings.length; index$sample11$33 += 1) {
																int distributionTempVariable$var11$35 = index$sample11$33;
																double cv$probabilitySample11Value34 = (1.0 * distribution$sample11[index$sample11$33]);
																if((0 == j)) {
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$9$var47;
																				{
																					double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																					cv$temp$9$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample11Value34) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value34) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value34) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value34) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value34) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$9$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value34);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample11$44 = 0; index$sample11$44 < weightings.length; index$sample11$44 += 1) {
																			int distributionTempVariable$var11$46 = index$sample11$44;
																			double cv$probabilitySample11Value45 = (cv$probabilitySample11Value34 * distribution$sample11[index$sample11$44]);
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$10$var47;
																						{
																							double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																							cv$temp$10$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample11Value45) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value45) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value45) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value45) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value45) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$10$var47)));
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
										} else {
											for(int i = 0; i < size; i += 1) {
												if(true) {
													for(int index$sample27$28 = 0; index$sample27$28 < weightings.length; index$sample27$28 += 1) {
														int distributionTempVariable$var27$30 = index$sample27$28;
														double cv$probabilitySample27Value29 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$28]);
														if(((i + 1) == j)) {
															if(fixedFlag$sample11) {
																if((0 == j)) {
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$11$var47;
																				{
																					double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																					cv$temp$11$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample27Value29) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value29) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value29) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value29) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value29) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$11$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value29);
																			}
																		}
																	}
																}
															} else {
																if(true) {
																	for(int index$sample11$38 = 0; index$sample11$38 < weightings.length; index$sample11$38 += 1) {
																		int distributionTempVariable$var11$40 = index$sample11$38;
																		double cv$probabilitySample11Value39 = (cv$probabilitySample27Value29 * distribution$sample11[index$sample11$38]);
																		if((0 == j)) {
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$12$var47;
																						{
																							double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																							cv$temp$12$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample11Value39) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value39) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value39) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value39) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value39) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$12$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value39);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample11$50 = 0; index$sample11$50 < weightings.length; index$sample11$50 += 1) {
																					int distributionTempVariable$var11$52 = index$sample11$50;
																					double cv$probabilitySample11Value51 = (cv$probabilitySample11Value39 * distribution$sample11[index$sample11$50]);
																					if((0 == j)) {
																						{
																							{
																								double cv$temp$13$var47;
																								{
																									double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																									cv$temp$13$var47 = var47;
																								}
																								if(((Math.log(cv$probabilitySample11Value51) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var47)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value51) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value51) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var47));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value51) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value51) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$13$var47)));
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
										if(fixedFlag$sample11) {
											if((0 == j)) {
												if(fixedFlag$sample27) {
													for(int i = 0; i < size; i += 1) {
														if(((i + 1) == j)) {
															if((0 == j)) {
																{
																	{
																		double cv$temp$14$var47;
																		{
																			double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																			cv$temp$14$var47 = var47;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var47)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var47));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$14$var47)));
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
															for(int index$sample27$61 = 0; index$sample27$61 < weightings.length; index$sample27$61 += 1) {
																int distributionTempVariable$var27$63 = index$sample27$61;
																double cv$probabilitySample27Value62 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$61]);
																if(((i + 1) == j)) {
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$15$var47;
																				{
																					double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																					cv$temp$15$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample27Value62) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value62) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value62) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value62) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value62) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$15$var47)));
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
										} else {
											if(true) {
												for(int index$sample11$55 = 0; index$sample11$55 < weightings.length; index$sample11$55 += 1) {
													int distributionTempVariable$var11$57 = index$sample11$55;
													double cv$probabilitySample11Value56 = (1.0 * distribution$sample11[index$sample11$55]);
													if((0 == j)) {
														if(fixedFlag$sample27) {
															for(int i = 0; i < size; i += 1) {
																if(((i + 1) == j)) {
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$16$var47;
																				{
																					double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																					cv$temp$16$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample11Value56) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value56) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value56) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value56) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value56) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$16$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value56);
																			}
																		}
																	}
																	if(!true) {
																		for(int index$sample11$74 = 0; index$sample11$74 < weightings.length; index$sample11$74 += 1) {
																			int distributionTempVariable$var11$76 = index$sample11$74;
																			double cv$probabilitySample11Value75 = (cv$probabilitySample11Value56 * distribution$sample11[index$sample11$74]);
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$17$var47;
																						{
																							double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																							cv$temp$17$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample11Value75) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value75) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value75) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value75) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value75) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$17$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value75);
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
																		if(((i + 1) == j)) {
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$18$var47;
																						{
																							double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																							cv$temp$18$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample27Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value68) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$18$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value68);
																					}
																				}
																			}
																			if(!true) {
																				for(int index$sample11$79 = 0; index$sample11$79 < weightings.length; index$sample11$79 += 1) {
																					int distributionTempVariable$var11$81 = index$sample11$79;
																					double cv$probabilitySample11Value80 = (cv$probabilitySample27Value68 * distribution$sample11[index$sample11$79]);
																					if((0 == j)) {
																						{
																							{
																								double cv$temp$19$var47;
																								{
																									double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																									cv$temp$19$var47 = var47;
																								}
																								if(((Math.log(cv$probabilitySample11Value80) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var47)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value80) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value80) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var47));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value80) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value80) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$19$var47)));
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
										if(fixedFlag$sample27) {
											for(int i = 0; i < size; i += 1) {
												if(((i + 1) == j)) {
													for(int index$i$89_1 = 0; index$i$89_1 < size; index$i$89_1 += 1) {
														if(((index$i$89_1 + 1) == j)) {
															if(fixedFlag$sample11) {
																if((0 == j)) {
																	{
																		{
																			double cv$temp$20$var47;
																			{
																				double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																				cv$temp$20$var47 = var47;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$20$var47)));
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
																		if((0 == j)) {
																			{
																				{
																					double cv$temp$21$var47;
																					{
																						double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																						cv$temp$21$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample11Value98) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value98) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value98) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value98) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value98) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$21$var47)));
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
										} else {
											for(int i = 0; i < size; i += 1) {
												if(true) {
													for(int index$sample27$85 = 0; index$sample27$85 < weightings.length; index$sample27$85 += 1) {
														int distributionTempVariable$var27$87 = index$sample27$85;
														double cv$probabilitySample27Value86 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$85]);
														if(((i + 1) == j)) {
															if(((i + 1) == j)) {
																if(fixedFlag$sample11) {
																	if((0 == j)) {
																		{
																			{
																				double cv$temp$22$var47;
																				{
																					double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																					cv$temp$22$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample27Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value86) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$22$var47)));
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
																			if((0 == j)) {
																				{
																					{
																						double cv$temp$23$var47;
																						{
																							double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																							cv$temp$23$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample11Value103) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value103) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value103) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value103) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value103) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$23$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value103);
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
																		if(((index$i$91 + 1) == j)) {
																			if(fixedFlag$sample11) {
																				if((0 == j)) {
																					{
																						{
																							double cv$temp$24$var47;
																							{
																								double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																								cv$temp$24$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value93) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value93) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value93) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value93) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value93) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var47)));
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
																						if((0 == j)) {
																							{
																								{
																									double cv$temp$25$var47;
																									{
																										double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																										cv$temp$25$var47 = var47;
																									}
																									if(((Math.log(cv$probabilitySample11Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var47)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var47));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value108) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var47)));
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
										if(fixedFlag$sample11) {
											if((0 == j)) {
												if((0 == j)) {
													if(fixedFlag$sample27) {
														for(int i = 0; i < size; i += 1) {
															if(((i + 1) == j)) {
																{
																	{
																		double cv$temp$26$var47;
																		{
																			double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																			cv$temp$26$var47 = var47;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var47)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var47));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var47)));
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
																	if(((i + 1) == j)) {
																		{
																			{
																				double cv$temp$27$var47;
																				{
																					double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																					cv$temp$27$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample27Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value125) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$27$var47)));
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
										} else {
											if(true) {
												for(int index$sample11$112 = 0; index$sample11$112 < weightings.length; index$sample11$112 += 1) {
													int distributionTempVariable$var11$114 = index$sample11$112;
													double cv$probabilitySample11Value113 = (1.0 * distribution$sample11[index$sample11$112]);
													if((0 == j)) {
														if((0 == j)) {
															if(fixedFlag$sample27) {
																for(int i = 0; i < size; i += 1) {
																	if(((i + 1) == j)) {
																		{
																			{
																				double cv$temp$28$var47;
																				{
																					double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																					cv$temp$28$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample11Value113) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value113) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value113) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value113) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value113) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$28$var47)));
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
																			if(((i + 1) == j)) {
																				{
																					{
																						double cv$temp$29$var47;
																						{
																							double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																							cv$temp$29$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample27Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value131) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$29$var47)));
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
														if(!true) {
															for(int index$sample11$118 = 0; index$sample11$118 < weightings.length; index$sample11$118 += 1) {
																int distributionTempVariable$var11$120 = index$sample11$118;
																double cv$probabilitySample11Value119 = (cv$probabilitySample11Value113 * distribution$sample11[index$sample11$118]);
																if((0 == j)) {
																	if(fixedFlag$sample27) {
																		for(int i = 0; i < size; i += 1) {
																			if(((i + 1) == j)) {
																				{
																					{
																						double cv$temp$30$var47;
																						{
																							double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																							cv$temp$30$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample11Value119) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value119) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value119) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value119) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value119) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$30$var47)));
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
																					if(((i + 1) == j)) {
																						{
																							{
																								double cv$temp$31$var47;
																								{
																									double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																									cv$temp$31$var47 = var47;
																								}
																								if(((Math.log(cv$probabilitySample27Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var47)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var47));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value137) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$31$var47)));
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
										if(fixedFlag$sample27) {
											for(int i = 0; i < size; i += 1) {
												if(((i + 1) == j)) {
													if(fixedFlag$sample11) {
														if((0 == j)) {
															for(int index$i$156_1 = 0; index$i$156_1 < size; index$i$156_1 += 1) {
																if(((index$i$156_1 + 1) == j)) {
																	{
																		{
																			double cv$temp$32$var47;
																			{
																				double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																				cv$temp$32$var47 = var47;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$32$var47)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
																if((0 == j)) {
																	for(int index$i$157_1 = 0; index$i$157_1 < size; index$i$157_1 += 1) {
																		if(((index$i$157_1 + 1) == j)) {
																			{
																				{
																					double cv$temp$33$var47;
																					{
																						double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																						cv$temp$33$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample11Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value148) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$33$var47)));
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
										} else {
											for(int i = 0; i < size; i += 1) {
												if(true) {
													for(int index$sample27$142 = 0; index$sample27$142 < weightings.length; index$sample27$142 += 1) {
														int distributionTempVariable$var27$144 = index$sample27$142;
														double cv$probabilitySample27Value143 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$142]);
														if(((i + 1) == j)) {
															if(fixedFlag$sample11) {
																if((0 == j)) {
																	if(((i + 1) == j)) {
																		{
																			{
																				double cv$temp$34$var47;
																				{
																					double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																					cv$temp$34$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample27Value143) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value143) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value143) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value143) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value143) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$34$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value143);
																			}
																		}
																	}
																	for(int index$i$159 = 0; index$i$159 < size; index$i$159 += 1) {
																		if(!(index$i$159 == i)) {
																			for(int index$sample27$160 = 0; index$sample27$160 < weightings.length; index$sample27$160 += 1) {
																				int distributionTempVariable$var27$162 = index$sample27$160;
																				double cv$probabilitySample27Value161 = (cv$probabilitySample27Value143 * distribution$sample27[((index$i$159 - 0) / 1)][index$sample27$160]);
																				if(((index$i$159 + 1) == j)) {
																					{
																						{
																							double cv$temp$35$var47;
																							{
																								double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																								cv$temp$35$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value161) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$35$var47)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value161);
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
																		if((0 == j)) {
																			if(((i + 1) == j)) {
																				{
																					{
																						double cv$temp$36$var47;
																						{
																							double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																							cv$temp$36$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample11Value153) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value153) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value153) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value153) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value153) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$36$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value153);
																					}
																				}
																			}
																			for(int index$i$165 = 0; index$i$165 < size; index$i$165 += 1) {
																				if(!(index$i$165 == i)) {
																					for(int index$sample27$166 = 0; index$sample27$166 < weightings.length; index$sample27$166 += 1) {
																						int distributionTempVariable$var27$168 = index$sample27$166;
																						double cv$probabilitySample27Value167 = (cv$probabilitySample11Value153 * distribution$sample27[((index$i$165 - 0) / 1)][index$sample27$166]);
																						if(((index$i$165 + 1) == j)) {
																							{
																								{
																									double cv$temp$37$var47;
																									{
																										double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																										cv$temp$37$var47 = var47;
																									}
																									if(((Math.log(cv$probabilitySample27Value167) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var47)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value167) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value167) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var47));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value167) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value167) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$37$var47)));
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
										if(fixedFlag$sample11) {
											if((0 == j)) {
												if(fixedFlag$sample27) {
													for(int i = 0; i < size; i += 1) {
														if(((i + 1) == j)) {
															for(int index$i$187_1 = 0; index$i$187_1 < size; index$i$187_1 += 1) {
																if(((index$i$187_1 + 1) == j)) {
																	{
																		{
																			double cv$temp$38$var47;
																			{
																				double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																				cv$temp$38$var47 = var47;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$38$var47)));
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
															for(int index$sample27$177 = 0; index$sample27$177 < weightings.length; index$sample27$177 += 1) {
																int distributionTempVariable$var27$179 = index$sample27$177;
																double cv$probabilitySample27Value178 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$177]);
																if(((i + 1) == j)) {
																	if(((i + 1) == j)) {
																		{
																			{
																				double cv$temp$39$var47;
																				{
																					double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																					cv$temp$39$var47 = var47;
																				}
																				if(((Math.log(cv$probabilitySample27Value178) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var47)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value178) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value178) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var47));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value178) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value178) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$39$var47)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value178);
																			}
																		}
																	}
																	for(int index$i$189 = 0; index$i$189 < size; index$i$189 += 1) {
																		if(!(index$i$189 == i)) {
																			for(int index$sample27$190 = 0; index$sample27$190 < weightings.length; index$sample27$190 += 1) {
																				int distributionTempVariable$var27$192 = index$sample27$190;
																				double cv$probabilitySample27Value191 = (cv$probabilitySample27Value178 * distribution$sample27[((index$i$189 - 0) / 1)][index$sample27$190]);
																				if(((index$i$189 + 1) == j)) {
																					{
																						{
																							double cv$temp$40$var47;
																							{
																								double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																								cv$temp$40$var47 = var47;
																							}
																							if(((Math.log(cv$probabilitySample27Value191) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var47)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value191) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value191) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var47));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value191) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value191) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$40$var47)));
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
										} else {
											if(true) {
												for(int index$sample11$171 = 0; index$sample11$171 < weightings.length; index$sample11$171 += 1) {
													int distributionTempVariable$var11$173 = index$sample11$171;
													double cv$probabilitySample11Value172 = (1.0 * distribution$sample11[index$sample11$171]);
													if((0 == j)) {
														if(fixedFlag$sample27) {
															for(int i = 0; i < size; i += 1) {
																if(((i + 1) == j)) {
																	for(int index$i$194_1 = 0; index$i$194_1 < size; index$i$194_1 += 1) {
																		if(((index$i$194_1 + 1) == j)) {
																			{
																				{
																					double cv$temp$41$var47;
																					{
																						double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																						cv$temp$41$var47 = var47;
																					}
																					if(((Math.log(cv$probabilitySample11Value172) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var47)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value172) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value172) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var47));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value172) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var47)))) + 1)) + (Math.log(cv$probabilitySample11Value172) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$41$var47)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value172);
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
																		if(((i + 1) == j)) {
																			if(((i + 1) == j)) {
																				{
																					{
																						double cv$temp$42$var47;
																						{
																							double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																							cv$temp$42$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample27Value184) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value184) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value184) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value184) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value184) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$42$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value184);
																					}
																				}
																			}
																			for(int index$i$196 = 0; index$i$196 < size; index$i$196 += 1) {
																				if(!(index$i$196 == i)) {
																					for(int index$sample27$197 = 0; index$sample27$197 < weightings.length; index$sample27$197 += 1) {
																						int distributionTempVariable$var27$199 = index$sample27$197;
																						double cv$probabilitySample27Value198 = (cv$probabilitySample27Value184 * distribution$sample27[((index$i$196 - 0) / 1)][index$sample27$197]);
																						if(((index$i$196 + 1) == j)) {
																							{
																								{
																									double cv$temp$43$var47;
																									{
																										double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																										cv$temp$43$var47 = var47;
																									}
																									if(((Math.log(cv$probabilitySample27Value198) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var47)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value198) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value198) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var47));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value198) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value198) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var47)));
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
										if(fixedFlag$sample27) {
											for(int i = 0; i < size; i += 1) {
												if(((i + 1) == j)) {
													for(int index$i$207_1 = 0; index$i$207_1 < size; index$i$207_1 += 1) {
														if(((index$i$207_1 + 1) == j)) {
															for(int index$i$214_1 = 0; index$i$214_1 < size; index$i$214_1 += 1) {
																if(((index$i$214_1 + 1) == j)) {
																	{
																		{
																			double cv$temp$44$var47;
																			{
																				double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																				cv$temp$44$var47 = var47;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var47)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var47)));
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
													for(int index$sample27$203 = 0; index$sample27$203 < weightings.length; index$sample27$203 += 1) {
														int distributionTempVariable$var27$205 = index$sample27$203;
														double cv$probabilitySample27Value204 = (1.0 * distribution$sample27[((i - 0) / 1)][index$sample27$203]);
														if(((i + 1) == j)) {
															if(((i + 1) == j)) {
																if(((i + 1) == j)) {
																	{
																		{
																			double cv$temp$45$var47;
																			{
																				double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																				cv$temp$45$var47 = var47;
																			}
																			if(((Math.log(cv$probabilitySample27Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var47)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var47));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var47)));
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
																			if(((index$i$216 + 1) == j)) {
																				{
																					{
																						double cv$temp$46$var47;
																						{
																							double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																							cv$temp$46$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample27Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value218);
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
																		if(((index$i$209 + 1) == j)) {
																			if(((i + 1) == j)) {
																				{
																					{
																						double cv$temp$47$var47;
																						{
																							double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																							cv$temp$47$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var47)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value211);
																					}
																				}
																			}
																			if(((index$i$209 + 1) == j)) {
																				{
																					{
																						double cv$temp$48$var47;
																						{
																							double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																							cv$temp$48$var47 = var47;
																						}
																						if(((Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var47)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var47));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var47)));
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
																						if(((index$i$223 + 1) == j)) {
																							{
																								{
																									double cv$temp$49$var47;
																									{
																										double var47 = ((((1.0 * traceTempVariable$v1$1_2) + v2[j]) + v2[j]) / v2[j]);
																										cv$temp$49$var47 = var47;
																									}
																									if(((Math.log(cv$probabilitySample27Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var47)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var47)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var47));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var47)))) + 1)) + (Math.log(cv$probabilitySample27Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var47)));
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
			guard$sample11bernoulli48$global = new boolean[cv$max_j];
		}
		{
			cv$var27$stateProbabilityGlobal = new double[weightings.length];
		}
		{
			int cv$max_j = 0;
			cv$max_j = Math.max(cv$max_j, ((length$value - 0) / 1));
			guard$sample27bernoulli48$global = new boolean[cv$max_j];
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
			logProbability$var26 = new double[((((length$value - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample27 = new double[((((length$value - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var48 = new double[((((length$value - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample49 = new double[((((length$value - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample11)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		for(int i = 0; i < size; i += 1) {
			if(!fixedFlag$sample27)
				v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		}
		for(int j = 0; j < size; j += 1)
			v[j] = DistributionSampling.sampleBernoulli(RNG$, ((((1.0 * v1) + v2[j]) + v2[j]) / v2[j]));
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
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
		for(int i = 0; i < size; i += 1) {
			double[] cv$distribution$sample27 = distribution$sample27[((i - 0) / 1)];
			for(int index$var26 = 0; index$var26 < weightings.length; index$var26 += 1) {
				double cv$value = (((0.0 <= index$var26) && (index$var26 < weightings.length))?weightings[index$var26]:0.0);
				if(!fixedFlag$sample27)
					cv$distribution$sample27[index$var26] = cv$value;
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample11)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		for(int i = 0; i < size; i += 1) {
			if(!fixedFlag$sample27)
				v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		}
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
			logProbability$v1 = 0.0;
		logProbability$var10 = 0.0;
		logProbability$v2 = 0.0;
		if(!fixedProbFlag$sample11)
			logProbability$var11 = 0.0;
		for(int i = 0; i < size; i += 1)
			logProbability$var26[((i - 0) / 1)] = 0.0;
		if(!fixedProbFlag$sample27) {
			for(int i = 0; i < size; i += 1)
				logProbability$sample27[((i - 0) / 1)] = 0.0;
		}
		for(int j = 0; j < size; j += 1)
			logProbability$var48[((j - 0) / 1)] = 0.0;
		logProbability$v = 0.0;
		if(!fixedProbFlag$sample49) {
			for(int j = 0; j < size; j += 1)
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
		logProbabilityValue$sample49();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample5();
		logProbabilityDistribution$sample11();
		logProbabilityDistribution$sample27();
		logProbabilityDistribution$sample49();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample11();
		logProbabilityValue$sample27();
		logProbabilityValue$sample49();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample11)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		for(int i = 0; i < size; i += 1) {
			if(!fixedFlag$sample27)
				v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		}
		logModelProbabilitiesVal();
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
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model DistributionTest6(double[] weightings, boolean[] value) {\n"
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
		     + "        v[j] = bernoulli(((1.0*v1) + v2[j] + v2[j])/v2[j]).sample();\n"
		     + "        \n"
		     + "    v.observe(value);\n"
		     + "}\n"
		     + "";
	}
}