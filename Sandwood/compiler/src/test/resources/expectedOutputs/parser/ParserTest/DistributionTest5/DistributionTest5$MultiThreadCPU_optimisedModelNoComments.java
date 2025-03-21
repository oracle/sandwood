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
	private boolean setFlag$v = false;
	private boolean setFlag$v2 = false;
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
	public final boolean get$fixedFlag$sample11() {
		return fixedFlag$sample11;
	}

	@Override
	public final void set$fixedFlag$sample11(boolean cv$value) {
		fixedFlag$sample11 = cv$value;
		fixedProbFlag$sample11 = (cv$value && fixedProbFlag$sample11);
		fixedProbFlag$sample70 = (cv$value && fixedProbFlag$sample70);
	}

	@Override
	public final boolean get$fixedFlag$sample27() {
		return fixedFlag$sample27;
	}

	@Override
	public final void set$fixedFlag$sample27(boolean cv$value) {
		fixedFlag$sample27 = cv$value;
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
		fixedProbFlag$sample70 = (cv$value && fixedProbFlag$sample70);
	}

	@Override
	public final boolean get$fixedFlag$sample5() {
		return fixedFlag$sample5;
	}

	@Override
	public final void set$fixedFlag$sample5(boolean cv$value) {
		fixedFlag$sample5 = cv$value;
		fixedProbFlag$sample5 = (cv$value && fixedProbFlag$sample5);
		fixedProbFlag$sample70 = (cv$value && fixedProbFlag$sample70);
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
		setFlag$v2 = true;
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
				int cv$sampleValue = v2[0];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				logProbability$var10 = cv$distributionAccumulator;
				logProbability$var11 = cv$distributionAccumulator;
				logProbability$v2 = (logProbability$v2 + cv$distributionAccumulator);
				if((fixedFlag$sample27 && (0 <= size)))
					logProbability$v3 = (logProbability$v3 + cv$distributionAccumulator);
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample11 = true;
			}
		} else {
			logProbability$var10 = logProbability$var11;
			if(fixedFlag$sample11) {
				logProbability$v2 = (logProbability$v2 + logProbability$var11);
				if((fixedFlag$sample27 && (0 <= size)))
					logProbability$v3 = (logProbability$v3 + logProbability$var11);
			}
			logProbability$$model = (logProbability$$model + logProbability$var11);
			if(fixedFlag$sample11)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var11);
		}
	}

	private final void logProbabilityDistribution$sample27() {
		if(!fixedProbFlag$sample27) {
			if(fixedFlag$sample27) {
				double cv$accumulator = 0.0;
				for(int i = 0; i < size; i += 1) {
					int cv$sampleValue = v2[(i + 1)];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var26[i] = cv$distributionAccumulator;
					logProbability$sample27[i] = cv$distributionAccumulator;
					if(fixedFlag$sample11)
						logProbability$v3 = (logProbability$v3 + cv$distributionAccumulator);
				}
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample27 = true;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				double cv$sampleValue = logProbability$sample27[i];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var26[i] = cv$sampleValue;
				if((fixedFlag$sample11 && fixedFlag$sample27))
					logProbability$v3 = (logProbability$v3 + cv$sampleValue);
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
				double cv$distributionAccumulator = (((0.0 <= v1) && (v1 < weightings.length))?Math.log(weightings[v1]):Double.NEGATIVE_INFINITY);
				logProbability$var4 = cv$distributionAccumulator;
				logProbability$v1 = cv$distributionAccumulator;
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample5 = true;
			}
		} else {
			logProbability$var4 = logProbability$v1;
			logProbability$$model = (logProbability$$model + logProbability$v1);
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence + logProbability$v1);
		}
	}

	private final void logProbabilityDistribution$sample70() {
		if(!fixedProbFlag$sample70) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				boolean cv$sampleValue = v[j];
				if((0 == j)) {
					if(fixedFlag$sample5) {
						if(fixedFlag$sample11) {
							if(fixedFlag$sample27) {
								cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[0]) + v2[1]) / v2[1]));
								cv$probabilityReached = 1.0;
							} else {
								for(int index$sample27$318 = 0; index$sample27$318 < weightings.length; index$sample27$318 += 1) {
									double cv$probabilitySample27Value319 = distribution$sample27[0][index$sample27$318];
									double cv$weightedProbability = (Math.log(cv$probabilitySample27Value319) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[0]) + v2[1]) / v2[1])));
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
						} else {
							for(int index$sample11$307 = 0; index$sample11$307 < weightings.length; index$sample11$307 += 1) {
								double cv$probabilitySample11Value308 = distribution$sample11[index$sample11$307];
								if(fixedFlag$sample27) {
									double cv$weightedProbability = (Math.log(cv$probabilitySample11Value308) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[0]) + v2[1]) / v2[1])));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value308);
								} else {
									for(int index$sample27$324 = 0; index$sample27$324 < weightings.length; index$sample27$324 += 1) {
										double cv$probabilitySample27Value325 = (cv$probabilitySample11Value308 * distribution$sample27[0][index$sample27$324]);
										double cv$weightedProbability = (Math.log(cv$probabilitySample27Value325) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[0]) + v2[1]) / v2[1])));
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
							}
						}
					} else {
						for(int index$sample5$302 = 0; index$sample5$302 < weightings.length; index$sample5$302 += 1) {
							double cv$probabilitySample5Value303 = distribution$sample5[index$sample5$302];
							if(fixedFlag$sample11) {
								if(fixedFlag$sample27) {
									double cv$weightedProbability = (Math.log(cv$probabilitySample5Value303) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample5$302 + v2[0]) + v2[1]) / v2[1])));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value303);
								} else {
									for(int index$sample27$330 = 0; index$sample27$330 < weightings.length; index$sample27$330 += 1) {
										double cv$probabilitySample27Value331 = (cv$probabilitySample5Value303 * distribution$sample27[0][index$sample27$330]);
										double cv$weightedProbability = (Math.log(cv$probabilitySample27Value331) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample5$302 + v2[0]) + v2[1]) / v2[1])));
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
							} else {
								for(int index$sample11$312 = 0; index$sample11$312 < weightings.length; index$sample11$312 += 1) {
									double cv$probabilitySample11Value313 = (cv$probabilitySample5Value303 * distribution$sample11[index$sample11$312]);
									if(fixedFlag$sample27) {
										double cv$weightedProbability = (Math.log(cv$probabilitySample11Value313) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample5$302 + v2[0]) + v2[1]) / v2[1])));
										if((cv$weightedProbability < cv$distributionAccumulator))
											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
										else {
											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
												cv$distributionAccumulator = cv$weightedProbability;
											else
												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
										}
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value313);
									} else {
										for(int index$sample27$336 = 0; index$sample27$336 < weightings.length; index$sample27$336 += 1) {
											double cv$probabilitySample27Value337 = (cv$probabilitySample11Value313 * distribution$sample27[0][index$sample27$336]);
											double cv$weightedProbability = (Math.log(cv$probabilitySample27Value337) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample5$302 + v2[0]) + v2[1]) / v2[1])));
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
								}
							}
						}
					}
				}
				if(fixedFlag$sample5) {
					if(fixedFlag$sample27) {
						if((1 <= j)) {
							for(int index$i$450_1 = 0; index$i$450_1 < size; index$i$450_1 += 1) {
								int k = (index$i$450_1 + 1);
								if((k == (j + 1))) {
									double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[j]) + v2[k]) / v2[(j + 1)]));
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
						int i = (j - 1);
						if((0 <= i)) {
							for(int index$sample27$440 = 0; index$sample27$440 < weightings.length; index$sample27$440 += 1) {
								double cv$probabilitySample27Value441 = distribution$sample27[i][index$sample27$440];
								{
									int k = (i + 1);
									if((k == (j + 1))) {
										int traceTempVariable$var63$451_2 = v2[k];
										if((i == j)) {
											double cv$weightedProbability = (Math.log(cv$probabilitySample27Value441) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[j]) + traceTempVariable$var63$451_2) / v2[(j + 1)])));
											if((cv$weightedProbability < cv$distributionAccumulator))
												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
											else {
												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
													cv$distributionAccumulator = cv$weightedProbability;
												else
													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
											}
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value441);
										} else {
											for(int index$sample27$467 = 0; index$sample27$467 < weightings.length; index$sample27$467 += 1) {
												double cv$probabilitySample27Value468 = (cv$probabilitySample27Value441 * distribution$sample27[j][index$sample27$467]);
												double cv$weightedProbability = (Math.log(cv$probabilitySample27Value468) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[j]) + traceTempVariable$var63$451_2) / v2[(j + 1)])));
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
								for(int index$i$452 = 0; index$i$452 < size; index$i$452 += 1) {
									if(!(index$i$452 == i)) {
										for(int index$sample27$453 = 0; index$sample27$453 < weightings.length; index$sample27$453 += 1) {
											double cv$probabilitySample27Value454 = (cv$probabilitySample27Value441 * distribution$sample27[index$i$452][index$sample27$453]);
											int k = (index$i$452 + 1);
											if((k == (j + 1))) {
												int traceTempVariable$var63$456_2 = v2[k];
												if((i == j)) {
													double cv$weightedProbability = (Math.log(cv$probabilitySample27Value454) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[j]) + traceTempVariable$var63$456_2) / v2[(j + 1)])));
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
												if((index$i$452 == j)) {
													double cv$weightedProbability = (Math.log(cv$probabilitySample27Value454) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[j]) + traceTempVariable$var63$456_2) / v2[(j + 1)])));
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
												if((!(j == i) && !(j == index$i$452))) {
													for(int index$sample27$474 = 0; index$sample27$474 < weightings.length; index$sample27$474 += 1) {
														double cv$probabilitySample27Value475 = (cv$probabilitySample27Value454 * distribution$sample27[j][index$sample27$474]);
														double cv$weightedProbability = (Math.log(cv$probabilitySample27Value475) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[j]) + traceTempVariable$var63$456_2) / v2[(j + 1)])));
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
				} else {
					for(int index$sample5$434 = 0; index$sample5$434 < weightings.length; index$sample5$434 += 1) {
						double cv$probabilitySample5Value435 = distribution$sample5[index$sample5$434];
						if(fixedFlag$sample27) {
							if((1 <= j)) {
								for(int index$i$457_1 = 0; index$i$457_1 < size; index$i$457_1 += 1) {
									int k = (index$i$457_1 + 1);
									if((k == (j + 1))) {
										double cv$weightedProbability = (Math.log(cv$probabilitySample5Value435) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample5$434 + v2[j]) + v2[k]) / v2[(j + 1)])));
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
						} else {
							int i = (j - 1);
							if((0 <= i)) {
								for(int index$sample27$446 = 0; index$sample27$446 < weightings.length; index$sample27$446 += 1) {
									double cv$probabilitySample27Value447 = (cv$probabilitySample5Value435 * distribution$sample27[i][index$sample27$446]);
									{
										int k = (i + 1);
										if((k == (j + 1))) {
											int traceTempVariable$var63$458_2 = v2[k];
											if((i == j)) {
												double cv$weightedProbability = (Math.log(cv$probabilitySample27Value447) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample5$434 + v2[j]) + traceTempVariable$var63$458_2) / v2[(j + 1)])));
												if((cv$weightedProbability < cv$distributionAccumulator))
													cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
												else {
													if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
														cv$distributionAccumulator = cv$weightedProbability;
													else
														cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
												}
												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value447);
											} else {
												for(int index$sample27$481 = 0; index$sample27$481 < weightings.length; index$sample27$481 += 1) {
													double cv$probabilitySample27Value482 = (cv$probabilitySample27Value447 * distribution$sample27[j][index$sample27$481]);
													double cv$weightedProbability = (Math.log(cv$probabilitySample27Value482) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample5$434 + v2[j]) + traceTempVariable$var63$458_2) / v2[(j + 1)])));
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
									for(int index$i$459 = 0; index$i$459 < size; index$i$459 += 1) {
										if(!(index$i$459 == i)) {
											for(int index$sample27$460 = 0; index$sample27$460 < weightings.length; index$sample27$460 += 1) {
												double cv$probabilitySample27Value461 = (cv$probabilitySample27Value447 * distribution$sample27[index$i$459][index$sample27$460]);
												int k = (index$i$459 + 1);
												if((k == (j + 1))) {
													int traceTempVariable$var63$463_2 = v2[k];
													if((i == j)) {
														double cv$weightedProbability = (Math.log(cv$probabilitySample27Value461) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample5$434 + v2[j]) + traceTempVariable$var63$463_2) / v2[(j + 1)])));
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
													if((index$i$459 == j)) {
														double cv$weightedProbability = (Math.log(cv$probabilitySample27Value461) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample5$434 + v2[j]) + traceTempVariable$var63$463_2) / v2[(j + 1)])));
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
													if((!(j == i) && !(j == index$i$459))) {
														for(int index$sample27$488 = 0; index$sample27$488 < weightings.length; index$sample27$488 += 1) {
															double cv$probabilitySample27Value489 = (cv$probabilitySample27Value461 * distribution$sample27[j][index$sample27$488]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample27Value489) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample5$434 + v2[j]) + traceTempVariable$var63$463_2) / v2[(j + 1)])));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var69[j] = cv$distributionAccumulator;
				logProbability$sample70[j] = cv$distributionAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample70 = ((fixedFlag$sample5 && fixedFlag$sample11) && fixedFlag$sample27);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = logProbability$sample70[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var69[j] = cv$rvAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample11() {
		if(!fixedProbFlag$sample11) {
			int cv$sampleValue = v2[0];
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var10 = cv$distributionAccumulator;
			logProbability$var11 = cv$distributionAccumulator;
			logProbability$v2 = (logProbability$v2 + cv$distributionAccumulator);
			if((0 <= size))
				logProbability$v3 = (logProbability$v3 + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample11)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample11 = fixedFlag$sample11;
		} else {
			logProbability$var10 = logProbability$var11;
			logProbability$v2 = (logProbability$v2 + logProbability$var11);
			if((0 <= size))
				logProbability$v3 = (logProbability$v3 + logProbability$var11);
			logProbability$$model = (logProbability$$model + logProbability$var11);
			if(fixedFlag$sample11)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var11);
		}
	}

	private final void logProbabilityValue$sample27() {
		if(!fixedProbFlag$sample27) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				int cv$sampleValue = v2[(i + 1)];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var26[i] = cv$distributionAccumulator;
				logProbability$sample27[i] = cv$distributionAccumulator;
				logProbability$v3 = (logProbability$v3 + cv$distributionAccumulator);
			}
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample27 = fixedFlag$sample27;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				double cv$sampleValue = logProbability$sample27[i];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var26[i] = cv$sampleValue;
				logProbability$v3 = (logProbability$v3 + cv$sampleValue);
			}
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample5() {
		if(!fixedProbFlag$sample5) {
			double cv$distributionAccumulator = (((0.0 <= v1) && (v1 < weightings.length))?Math.log(weightings[v1]):Double.NEGATIVE_INFINITY);
			logProbability$var4 = cv$distributionAccumulator;
			logProbability$v1 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample5 = fixedFlag$sample5;
		} else {
			logProbability$var4 = logProbability$v1;
			logProbability$$model = (logProbability$$model + logProbability$v1);
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence + logProbability$v1);
		}
	}

	private final void logProbabilityValue$sample70() {
		if(!fixedProbFlag$sample70) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(v[j], ((double)((v1 + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var69[j] = cv$distributionAccumulator;
				logProbability$sample70[j] = cv$distributionAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample70 = ((fixedFlag$sample5 && fixedFlag$sample11) && fixedFlag$sample27);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = logProbability$sample70[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var69[j] = cv$rvAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample11() {
		int cv$numNumStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$accumulatedProbabilities = Math.log(weightings[cv$valuePos]);
			if((0 < size)) {
				guard$sample11bernoulli69$global[0] = false;
				if(!guard$sample11bernoulli69$global[0]) {
					guard$sample11bernoulli69$global[0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(fixedFlag$sample5) {
						if(fixedFlag$sample27) {
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((v1 + cv$valuePos) + v2[1]) / v2[1]));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							for(int index$sample27$130 = 0; index$sample27$130 < weightings.length; index$sample27$130 += 1) {
								double cv$probabilitySample27Value131 = distribution$sample27[0][index$sample27$130];
								double var68 = ((double)((v1 + cv$valuePos) + v2[1]) / v2[1]);
								if(((Math.log(cv$probabilitySample27Value131) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value131) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value131) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value131) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample27Value131)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value131);
							}
						}
					} else {
						for(int index$sample5$124 = 0; index$sample5$124 < weightings.length; index$sample5$124 += 1) {
							double cv$probabilitySample5Value125 = distribution$sample5[index$sample5$124];
							if(fixedFlag$sample27) {
								double var68 = ((double)((index$sample5$124 + cv$valuePos) + v2[1]) / v2[1]);
								if(((Math.log(cv$probabilitySample5Value125) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value125) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value125) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value125) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample5Value125)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value125);
							} else {
								for(int index$sample27$136 = 0; index$sample27$136 < weightings.length; index$sample27$136 += 1) {
									double cv$probabilitySample27Value137 = (cv$probabilitySample5Value125 * distribution$sample27[0][index$sample27$136]);
									double var68 = ((double)((index$sample5$124 + cv$valuePos) + v2[1]) / v2[1]);
									if(((Math.log(cv$probabilitySample27Value137) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value137) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value137) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value137) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample27Value137)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value137);
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
			cv$var11$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double cv$logSum;
		double cv$lseMax = cv$var11$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var11$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var11$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				distribution$sample11[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				distribution$sample11[cv$indexName] = Math.exp((cv$var11$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var11$stateProbabilityGlobal.length; cv$indexName += 1)
			distribution$sample11[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample27(int i) {
		int cv$numNumStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$accumulatedProbabilities = Math.log(weightings[cv$valuePos]);
			{
				int j = (i + 1);
				if((j < size))
					guard$sample27bernoulli69$global[j] = false;
			}
			guard$sample27bernoulli69$global[i] = false;
			int j = (i + 1);
			if(((j < size) && !guard$sample27bernoulli69$global[j])) {
				guard$sample27bernoulli69$global[j] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if(fixedFlag$sample5) {
					if((i == j)) {
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[i], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						for(int index$sample27$122 = 0; index$sample27$122 < weightings.length; index$sample27$122 += 1) {
							double cv$probabilitySample27Value123 = distribution$sample27[j][index$sample27$122];
							double var68 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample27Value123) + DistributionSampling.logProbabilityBernoulli(v[j], var68)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value123) + DistributionSampling.logProbabilityBernoulli(v[j], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value123) + DistributionSampling.logProbabilityBernoulli(v[j], var68));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value123) + DistributionSampling.logProbabilityBernoulli(v[j], var68)))) + 1)) + Math.log(cv$probabilitySample27Value123)) + DistributionSampling.logProbabilityBernoulli(v[j], var68));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value123);
						}
					}
				} else {
					for(int index$sample5$116 = 0; index$sample5$116 < weightings.length; index$sample5$116 += 1) {
						double cv$probabilitySample5Value117 = distribution$sample5[index$sample5$116];
						if((i == j)) {
							double var68 = ((double)((index$sample5$116 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample5Value117) + DistributionSampling.logProbabilityBernoulli(v[i], var68)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value117) + DistributionSampling.logProbabilityBernoulli(v[i], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value117) + DistributionSampling.logProbabilityBernoulli(v[i], var68));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value117) + DistributionSampling.logProbabilityBernoulli(v[i], var68)))) + 1)) + Math.log(cv$probabilitySample5Value117)) + DistributionSampling.logProbabilityBernoulli(v[i], var68));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value117);
						} else {
							for(int index$sample27$128 = 0; index$sample27$128 < weightings.length; index$sample27$128 += 1) {
								double cv$probabilitySample27Value129 = (cv$probabilitySample5Value117 * distribution$sample27[j][index$sample27$128]);
								double var68 = ((double)((index$sample5$116 + cv$valuePos) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample27Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var68)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var68));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var68)))) + 1)) + Math.log(cv$probabilitySample27Value129)) + DistributionSampling.logProbabilityBernoulli(v[j], var68));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value129);
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
			if(!guard$sample27bernoulli69$global[i]) {
				guard$sample27bernoulli69$global[i] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((0 == i)) {
					if(fixedFlag$sample5) {
						if(fixedFlag$sample11) {
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((v1 + v2[0]) + cv$valuePos) / cv$valuePos));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							for(int index$sample11$191 = 0; index$sample11$191 < weightings.length; index$sample11$191 += 1) {
								double cv$probabilitySample11Value192 = distribution$sample11[index$sample11$191];
								double var68 = ((double)((v1 + v2[0]) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample11Value192) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value192) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value192) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value192) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample11Value192)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value192);
							}
						}
					} else {
						for(int index$sample5$186 = 0; index$sample5$186 < weightings.length; index$sample5$186 += 1) {
							double cv$probabilitySample5Value187 = distribution$sample5[index$sample5$186];
							if(fixedFlag$sample11) {
								double var68 = ((double)((index$sample5$186 + v2[0]) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample5Value187) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value187) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value187) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value187) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample5Value187)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value187);
							} else {
								for(int index$sample11$196 = 0; index$sample11$196 < weightings.length; index$sample11$196 += 1) {
									double cv$probabilitySample11Value197 = (cv$probabilitySample5Value187 * distribution$sample11[index$sample11$196]);
									double var68 = ((double)((index$sample5$186 + v2[0]) + cv$valuePos) / cv$valuePos);
									if(((Math.log(cv$probabilitySample11Value197) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value197) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value197) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value197) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample11Value197)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value197);
								}
							}
						}
					}
				}
				if(fixedFlag$sample5) {
					int index$i$267 = (i - 1);
					if(((0 <= index$i$267) && !(index$i$267 == i))) {
						for(int index$sample27$268 = 0; index$sample27$268 < weightings.length; index$sample27$268 += 1) {
							double cv$probabilitySample27Value269 = distribution$sample27[index$i$267][index$sample27$268];
							double var68 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample27Value269) + DistributionSampling.logProbabilityBernoulli(v[i], var68)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value269) + DistributionSampling.logProbabilityBernoulli(v[i], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value269) + DistributionSampling.logProbabilityBernoulli(v[i], var68));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value269) + DistributionSampling.logProbabilityBernoulli(v[i], var68)))) + 1)) + Math.log(cv$probabilitySample27Value269)) + DistributionSampling.logProbabilityBernoulli(v[i], var68));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value269);
						}
					}
				} else {
					for(int index$sample5$262 = 0; index$sample5$262 < weightings.length; index$sample5$262 += 1) {
						double cv$probabilitySample5Value263 = distribution$sample5[index$sample5$262];
						int index$i$273 = (i - 1);
						if(((0 <= index$i$273) && !(index$i$273 == i))) {
							for(int index$sample27$274 = 0; index$sample27$274 < weightings.length; index$sample27$274 += 1) {
								double cv$probabilitySample27Value275 = (cv$probabilitySample5Value263 * distribution$sample27[index$i$273][index$sample27$274]);
								double var68 = ((double)((index$sample5$262 + cv$valuePos) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[i], var68)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[i], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[i], var68));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value275) + DistributionSampling.logProbabilityBernoulli(v[i], var68)))) + 1)) + Math.log(cv$probabilitySample27Value275)) + DistributionSampling.logProbabilityBernoulli(v[i], var68));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value275);
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
			if(!guard$sample27bernoulli69$global[i]) {
				guard$sample27bernoulli69$global[i] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((0 == i)) {
					if(fixedFlag$sample5) {
						if(fixedFlag$sample11) {
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((v1 + v2[0]) + cv$valuePos) / cv$valuePos));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							for(int index$sample11$374 = 0; index$sample11$374 < weightings.length; index$sample11$374 += 1) {
								double cv$probabilitySample11Value375 = distribution$sample11[index$sample11$374];
								double var68 = ((double)((v1 + v2[0]) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample11Value375) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value375) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value375) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value375) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample11Value375)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value375);
							}
						}
					} else {
						for(int index$sample5$369 = 0; index$sample5$369 < weightings.length; index$sample5$369 += 1) {
							double cv$probabilitySample5Value370 = distribution$sample5[index$sample5$369];
							if(fixedFlag$sample11) {
								double var68 = ((double)((index$sample5$369 + v2[0]) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample5Value370) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value370) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value370) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value370) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample5Value370)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value370);
							} else {
								for(int index$sample11$379 = 0; index$sample11$379 < weightings.length; index$sample11$379 += 1) {
									double cv$probabilitySample11Value380 = (cv$probabilitySample5Value370 * distribution$sample11[index$sample11$379]);
									double var68 = ((double)((index$sample5$369 + v2[0]) + cv$valuePos) / cv$valuePos);
									if(((Math.log(cv$probabilitySample11Value380) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value380) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value380) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value380) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample11Value380)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value380);
								}
							}
						}
					}
				}
				if(fixedFlag$sample5) {
					int index$i$413 = (i - 1);
					if(((0 <= index$i$413) && !(index$i$413 == i))) {
						for(int index$sample27$414 = 0; index$sample27$414 < weightings.length; index$sample27$414 += 1) {
							double cv$probabilitySample27Value415 = distribution$sample27[index$i$413][index$sample27$414];
							double var68 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample27Value415) + DistributionSampling.logProbabilityBernoulli(v[i], var68)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value415) + DistributionSampling.logProbabilityBernoulli(v[i], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value415) + DistributionSampling.logProbabilityBernoulli(v[i], var68));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value415) + DistributionSampling.logProbabilityBernoulli(v[i], var68)))) + 1)) + Math.log(cv$probabilitySample27Value415)) + DistributionSampling.logProbabilityBernoulli(v[i], var68));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value415);
						}
					}
				} else {
					for(int index$sample5$408 = 0; index$sample5$408 < weightings.length; index$sample5$408 += 1) {
						double cv$probabilitySample5Value409 = distribution$sample5[index$sample5$408];
						int index$i$419 = (i - 1);
						if(((0 <= index$i$419) && !(index$i$419 == i))) {
							for(int index$sample27$420 = 0; index$sample27$420 < weightings.length; index$sample27$420 += 1) {
								double cv$probabilitySample27Value421 = (cv$probabilitySample5Value409 * distribution$sample27[index$i$419][index$sample27$420]);
								double var68 = ((double)((index$sample5$408 + cv$valuePos) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample27Value421) + DistributionSampling.logProbabilityBernoulli(v[i], var68)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value421) + DistributionSampling.logProbabilityBernoulli(v[i], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value421) + DistributionSampling.logProbabilityBernoulli(v[i], var68));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value421) + DistributionSampling.logProbabilityBernoulli(v[i], var68)))) + 1)) + Math.log(cv$probabilitySample27Value421)) + DistributionSampling.logProbabilityBernoulli(v[i], var68));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value421);
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
			cv$var27$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double[] cv$localProbability = distribution$sample27[i];
		double cv$logSum;
		double cv$lseMax = cv$var27$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var27$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var27$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$var27$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var27$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample5() {
		int cv$numNumStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$accumulatedProbabilities = Math.log(weightings[cv$valuePos]);
			for(int j = 0; j < size; j += 1) {
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((0 == j)) {
					if(fixedFlag$sample11) {
						if(fixedFlag$sample27) {
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((cv$valuePos + v2[0]) + v2[1]) / v2[1]));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							for(int index$sample27$147 = 0; index$sample27$147 < weightings.length; index$sample27$147 += 1) {
								double cv$probabilitySample27Value148 = distribution$sample27[0][index$sample27$147];
								double var68 = ((double)((cv$valuePos + v2[0]) + v2[1]) / v2[1]);
								if(((Math.log(cv$probabilitySample27Value148) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value148) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value148) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value148) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample27Value148)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value148);
							}
						}
					} else {
						for(int index$sample11$141 = 0; index$sample11$141 < weightings.length; index$sample11$141 += 1) {
							double cv$probabilitySample11Value142 = distribution$sample11[index$sample11$141];
							if(fixedFlag$sample27) {
								double var68 = ((double)((cv$valuePos + v2[0]) + v2[1]) / v2[1]);
								if(((Math.log(cv$probabilitySample11Value142) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value142) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value142) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value142) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample11Value142)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value142);
							} else {
								for(int index$sample27$153 = 0; index$sample27$153 < weightings.length; index$sample27$153 += 1) {
									double cv$probabilitySample27Value154 = (cv$probabilitySample11Value142 * distribution$sample27[0][index$sample27$153]);
									double var68 = ((double)((cv$valuePos + v2[0]) + v2[1]) / v2[1]);
									if(((Math.log(cv$probabilitySample27Value154) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value154) + DistributionSampling.logProbabilityBernoulli(v[0], var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value154) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value154) + DistributionSampling.logProbabilityBernoulli(v[0], var68)))) + 1)) + Math.log(cv$probabilitySample27Value154)) + DistributionSampling.logProbabilityBernoulli(v[0], var68));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value154);
								}
							}
						}
					}
				}
				if(fixedFlag$sample27) {
					if((1 <= j)) {
						for(int index$i$207_1 = 0; index$i$207_1 < size; index$i$207_1 += 1) {
							int k = (index$i$207_1 + 1);
							if((k == (j + 1))) {
								double cv$temp$44$var68 = ((double)((cv$valuePos + v2[j]) + v2[k]) / v2[(j + 1)]);
								if((DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var68) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var68) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var68);
									else
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var68))) + 1)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var68));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
							}
						}
					}
				} else {
					int i = (j - 1);
					if((0 <= i)) {
						for(int index$sample27$203 = 0; index$sample27$203 < weightings.length; index$sample27$203 += 1) {
							double cv$probabilitySample27Value204 = distribution$sample27[i][index$sample27$203];
							{
								int k = (i + 1);
								if((k == (j + 1))) {
									int traceTempVariable$var63$208_2 = v2[k];
									if((i == j)) {
										double cv$temp$45$var68 = ((double)((cv$valuePos + v2[j]) + traceTempVariable$var63$208_2) / v2[(j + 1)]);
										if(((Math.log(cv$probabilitySample27Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var68)) < cv$accumulatedConsumerProbabilities))
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var68));
											else
												cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var68)))) + 1)) + Math.log(cv$probabilitySample27Value204)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$45$var68));
										}
										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value204);
									} else {
										for(int index$sample27$217 = 0; index$sample27$217 < weightings.length; index$sample27$217 += 1) {
											double cv$probabilitySample27Value218 = (cv$probabilitySample27Value204 * distribution$sample27[j][index$sample27$217]);
											double cv$temp$46$var68 = ((double)((cv$valuePos + v2[j]) + traceTempVariable$var63$208_2) / v2[(j + 1)]);
											if(((Math.log(cv$probabilitySample27Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var68)) < cv$accumulatedConsumerProbabilities))
												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
											else {
												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var68));
												else
													cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value218) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var68)))) + 1)) + Math.log(cv$probabilitySample27Value218)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$46$var68));
											}
											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value218);
										}
									}
								}
							}
							for(int index$i$209 = 0; index$i$209 < size; index$i$209 += 1) {
								if(!(index$i$209 == i)) {
									for(int index$sample27$210 = 0; index$sample27$210 < weightings.length; index$sample27$210 += 1) {
										double cv$probabilitySample27Value211 = (cv$probabilitySample27Value204 * distribution$sample27[index$i$209][index$sample27$210]);
										int k = (index$i$209 + 1);
										if((k == (j + 1))) {
											int traceTempVariable$var63$213_2 = v2[k];
											if((i == j)) {
												double cv$temp$47$var68 = ((double)((cv$valuePos + v2[j]) + traceTempVariable$var63$213_2) / v2[(j + 1)]);
												if(((Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var68)) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var68));
													else
														cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var68)))) + 1)) + Math.log(cv$probabilitySample27Value211)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$47$var68));
												}
												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value211);
											}
											if((index$i$209 == j)) {
												double cv$temp$48$var68 = ((double)((cv$valuePos + v2[j]) + traceTempVariable$var63$213_2) / v2[(j + 1)]);
												if(((Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var68)) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var68));
													else
														cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value211) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var68)))) + 1)) + Math.log(cv$probabilitySample27Value211)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$48$var68));
												}
												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value211);
											}
											if((!(j == i) && !(j == index$i$209))) {
												for(int index$sample27$224 = 0; index$sample27$224 < weightings.length; index$sample27$224 += 1) {
													double cv$probabilitySample27Value225 = (cv$probabilitySample27Value211 * distribution$sample27[j][index$sample27$224]);
													double cv$temp$49$var68 = ((double)((cv$valuePos + v2[j]) + traceTempVariable$var63$213_2) / v2[(j + 1)]);
													if(((Math.log(cv$probabilitySample27Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var68)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var68)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var68));
														else
															cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value225) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var68)))) + 1)) + Math.log(cv$probabilitySample27Value225)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$49$var68));
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
			cv$var5$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double cv$logSum;
		double cv$lseMax = cv$var5$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var5$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var5$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				distribution$sample5[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				distribution$sample5[cv$indexName] = Math.exp((cv$var5$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var5$stateProbabilityGlobal.length; cv$indexName += 1)
			distribution$sample5[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	@Override
	public final void allocateScratch() {
		cv$var5$stateProbabilityGlobal = new double[weightings.length];
		cv$var11$stateProbabilityGlobal = new double[weightings.length];
		guard$sample11bernoulli69$global = new boolean[length$value];
		cv$var27$stateProbabilityGlobal = new double[weightings.length];
		guard$sample27bernoulli69$global = new boolean[length$value];
	}

	@Override
	public final void allocator() {
		if(!setFlag$v2)
			v2 = new int[(length$value + 1)];
		v3 = new int[(length$value + 1)];
		v = new boolean[length$value];
		distribution$sample5 = new double[weightings.length];
		distribution$sample11 = new double[weightings.length];
		distribution$sample27 = new double[length$value][];
		for(int i = 0; i < length$value; i += 1)
			distribution$sample27[i] = new double[weightings.length];
		logProbability$var26 = new double[length$value];
		logProbability$sample27 = new double[length$value];
		logProbability$var69 = new double[length$value];
		logProbability$sample70 = new double[length$value];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample11)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample27)
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings, weightings.length);
				}
			);

		if((!fixedFlag$sample11 || !fixedFlag$sample27))
			parallelFor(RNG$, 0, (size + 1), 1,
				(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int k = forStart$k; k < forEnd$k; k += 1)
							v3[k] = v2[k];
				}
			);

		parallelFor(RNG$, 0, size, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						v[j] = DistributionSampling.sampleBernoulli(RNG$1, ((double)((v1 + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]));
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample5) {
			for(int index$var4 = 0; index$var4 < weightings.length; index$var4 += 1)
				distribution$sample5[index$var4] = weightings[index$var4];
		}
		if(!fixedFlag$sample11) {
			for(int index$var10 = 0; index$var10 < weightings.length; index$var10 += 1)
				distribution$sample11[index$var10] = weightings[index$var10];
		}
		if(!fixedFlag$sample27)
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							double[] cv$distribution$sample27 = distribution$sample27[i];
							for(int index$var26 = 0; index$var26 < weightings.length; index$var26 += 1)
								cv$distribution$sample27[index$var26] = weightings[index$var26];
						}
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample11)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample27)
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings, weightings.length);
				}
			);

		if((!fixedFlag$sample11 || !fixedFlag$sample27))
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
			if(!fixedFlag$sample27) {
				for(int i = 0; i < size; i += 1)
					sample27(i);
			}
		} else {
			if(!fixedFlag$sample27) {
				for(int i = (size - 1); i >= 0; i -= 1)
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
		logProbability$v3 = 0.0;
		if(!fixedProbFlag$sample11)
			logProbability$var11 = 0.0;
		for(int i = 0; i < size; i += 1)
			logProbability$var26[i] = 0.0;
		if(!fixedProbFlag$sample27) {
			for(int i = 0; i < size; i += 1)
				logProbability$sample27[i] = 0.0;
		}
		for(int j = 0; j < size; j += 1)
			logProbability$var69[j] = 0.0;
		logProbability$v = 0.0;
		if(!fixedProbFlag$sample70) {
			for(int j = 0; j < size; j += 1)
				logProbability$sample70[j] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
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
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample11)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample27)
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings, weightings.length);
				}
			);

		if((!fixedFlag$sample11 || !fixedFlag$sample27))
			parallelFor(RNG$, 0, (size + 1), 1,
				(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int k = forStart$k; k < forEnd$k; k += 1)
							v3[k] = v2[k];
				}
			);

		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = v.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			v[cv$index1] = value[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		if(setFlag$v2)
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