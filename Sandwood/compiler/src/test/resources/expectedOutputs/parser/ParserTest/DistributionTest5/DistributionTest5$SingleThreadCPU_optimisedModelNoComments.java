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
		fixedProbFlag$sample12 = (cv$value && fixedProbFlag$sample12);
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
	}

	@Override
	public final boolean get$fixedFlag$sample18() {
		return fixedFlag$sample18;
	}

	@Override
	public final void set$fixedFlag$sample18(boolean cv$value) {
		fixedFlag$sample18 = cv$value;
		fixedProbFlag$sample18 = (cv$value && fixedProbFlag$sample18);
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
	}

	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	@Override
	public final void set$fixedFlag$sample26(boolean cv$value) {
		fixedFlag$sample26 = cv$value;
		fixedProbFlag$sample26 = (cv$value && fixedProbFlag$sample26);
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
	}

	@Override
	public final boolean get$fixedFlag$sample55() {
		return fixedFlag$sample55;
	}

	@Override
	public final void set$fixedFlag$sample55(boolean cv$value) {
		fixedFlag$sample55 = cv$value;
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
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
		fixedProbFlag$sample55 = false;
	}

	@Override
	public final int get$v1() {
		return v1;
	}

	@Override
	public final void set$v1(int cv$value) {
		v1 = cv$value;
		fixedProbFlag$sample12 = false;
		fixedProbFlag$sample55 = false;
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
		fixedProbFlag$sample55 = false;
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

	private final void logProbabilityDistribution$sample12() {
		if(!fixedProbFlag$sample12) {
			if(fixedFlag$sample12) {
				double cv$distributionAccumulator = (((0.0 <= v1) && (v1 < weightings.length))?Math.log(weightings[v1]):Double.NEGATIVE_INFINITY);
				logProbability$var8 = cv$distributionAccumulator;
				logProbability$v1 = cv$distributionAccumulator;
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample12 = true;
			}
		} else {
			logProbability$var8 = logProbability$v1;
			logProbability$$model = (logProbability$$model + logProbability$v1);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + logProbability$v1);
		}
	}

	private final void logProbabilityDistribution$sample18() {
		if(!fixedProbFlag$sample18) {
			if(fixedFlag$sample18) {
				int cv$sampleValue = v2[0];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				logProbability$var14 = cv$distributionAccumulator;
				logProbability$var15 = cv$distributionAccumulator;
				logProbability$v2 = (logProbability$v2 + cv$distributionAccumulator);
				if((fixedFlag$sample26 && (0 <= size)))
					logProbability$v3 = (logProbability$v3 + cv$distributionAccumulator);
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample18 = true;
			}
		} else {
			logProbability$var14 = logProbability$var15;
			if(fixedFlag$sample18) {
				logProbability$v2 = (logProbability$v2 + logProbability$var15);
				if((fixedFlag$sample26 && (0 <= size)))
					logProbability$v3 = (logProbability$v3 + logProbability$var15);
			}
			logProbability$$model = (logProbability$$model + logProbability$var15);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var15);
		}
	}

	private final void logProbabilityDistribution$sample26() {
		if(!fixedProbFlag$sample26) {
			if(fixedFlag$sample26) {
				double cv$accumulator = 0.0;
				for(int i = 0; i < size; i += 1) {
					int cv$sampleValue = v2[(i + 1)];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var22[i] = cv$distributionAccumulator;
					logProbability$sample26[i] = cv$distributionAccumulator;
					if(fixedFlag$sample18)
						logProbability$v3 = (logProbability$v3 + cv$distributionAccumulator);
				}
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample26 = true;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				double cv$sampleValue = logProbability$sample26[i];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var22[i] = cv$sampleValue;
				if((fixedFlag$sample18 && fixedFlag$sample26))
					logProbability$v3 = (logProbability$v3 + cv$sampleValue);
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
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				boolean cv$sampleValue = v[j];
				if((0 == j)) {
					if(fixedFlag$sample12) {
						if(fixedFlag$sample18) {
							if(fixedFlag$sample26) {
								cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[0]) + v2[1]) / v2[1]));
								cv$probabilityReached = 1.0;
							} else {
								for(int index$sample26$318 = 0; index$sample26$318 < weightings.length; index$sample26$318 += 1) {
									double cv$probabilitySample26Value319 = distribution$sample26[0][index$sample26$318];
									double cv$weightedProbability = (Math.log(cv$probabilitySample26Value319) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[0]) + index$sample26$318) / index$sample26$318)));
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
						} else {
							for(int index$sample18$307 = 0; index$sample18$307 < weightings.length; index$sample18$307 += 1) {
								double cv$probabilitySample18Value308 = distribution$sample18[index$sample18$307];
								if(fixedFlag$sample26) {
									double cv$weightedProbability = (Math.log(cv$probabilitySample18Value308) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + index$sample18$307) + v2[1]) / v2[1])));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value308);
								} else {
									for(int index$sample26$324 = 0; index$sample26$324 < weightings.length; index$sample26$324 += 1) {
										double cv$probabilitySample26Value325 = (cv$probabilitySample18Value308 * distribution$sample26[0][index$sample26$324]);
										double cv$weightedProbability = (Math.log(cv$probabilitySample26Value325) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + index$sample18$307) + index$sample26$324) / index$sample26$324)));
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
							}
						}
					} else {
						for(int index$sample12$302 = 0; index$sample12$302 < weightings.length; index$sample12$302 += 1) {
							double cv$probabilitySample12Value303 = distribution$sample12[index$sample12$302];
							if(fixedFlag$sample18) {
								if(fixedFlag$sample26) {
									double cv$weightedProbability = (Math.log(cv$probabilitySample12Value303) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$302 + v2[0]) + v2[1]) / v2[1])));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample12Value303);
								} else {
									for(int index$sample26$330 = 0; index$sample26$330 < weightings.length; index$sample26$330 += 1) {
										double cv$probabilitySample26Value331 = (cv$probabilitySample12Value303 * distribution$sample26[0][index$sample26$330]);
										double cv$weightedProbability = (Math.log(cv$probabilitySample26Value331) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$302 + v2[0]) + index$sample26$330) / index$sample26$330)));
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
							} else {
								for(int index$sample18$312 = 0; index$sample18$312 < weightings.length; index$sample18$312 += 1) {
									double cv$probabilitySample18Value313 = (cv$probabilitySample12Value303 * distribution$sample18[index$sample18$312]);
									if(fixedFlag$sample26) {
										double cv$weightedProbability = (Math.log(cv$probabilitySample18Value313) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$302 + index$sample18$312) + v2[1]) / v2[1])));
										if((cv$weightedProbability < cv$distributionAccumulator))
											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
										else {
											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
												cv$distributionAccumulator = cv$weightedProbability;
											else
												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
										}
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample18Value313);
									} else {
										for(int index$sample26$336 = 0; index$sample26$336 < weightings.length; index$sample26$336 += 1) {
											double cv$probabilitySample26Value337 = (cv$probabilitySample18Value313 * distribution$sample26[0][index$sample26$336]);
											double cv$weightedProbability = (Math.log(cv$probabilitySample26Value337) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$302 + index$sample18$312) + index$sample26$336) / index$sample26$336)));
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
								}
							}
						}
					}
				}
				if(fixedFlag$sample12) {
					if(fixedFlag$sample26) {
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
							for(int index$sample26$440 = 0; index$sample26$440 < weightings.length; index$sample26$440 += 1) {
								double cv$probabilitySample26Value441 = distribution$sample26[i][index$sample26$440];
								if((i == j)) {
									double cv$weightedProbability = (Math.log(cv$probabilitySample26Value441) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + index$sample26$440) + index$sample26$440) / index$sample26$440)));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value441);
								} else {
									for(int index$sample26$453 = 0; index$sample26$453 < weightings.length; index$sample26$453 += 1) {
										double cv$probabilitySample26Value454 = (cv$probabilitySample26Value441 * distribution$sample26[j][index$sample26$453]);
										double cv$weightedProbability = (Math.log(cv$probabilitySample26Value454) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + index$sample26$440) + index$sample26$453) / index$sample26$453)));
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
							}
						}
					}
				} else {
					for(int index$sample12$434 = 0; index$sample12$434 < weightings.length; index$sample12$434 += 1) {
						double cv$probabilitySample12Value435 = distribution$sample12[index$sample12$434];
						if(fixedFlag$sample26) {
							if((1 <= j)) {
								for(int index$i$457_1 = 0; index$i$457_1 < size; index$i$457_1 += 1) {
									int k = (index$i$457_1 + 1);
									if((k == (j + 1))) {
										double cv$weightedProbability = (Math.log(cv$probabilitySample12Value435) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$434 + v2[j]) + v2[k]) / v2[(j + 1)])));
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
						} else {
							int i = (j - 1);
							if((0 <= i)) {
								for(int index$sample26$446 = 0; index$sample26$446 < weightings.length; index$sample26$446 += 1) {
									double cv$probabilitySample26Value447 = (cv$probabilitySample12Value435 * distribution$sample26[i][index$sample26$446]);
									if((i == j)) {
										double cv$weightedProbability = (Math.log(cv$probabilitySample26Value447) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$434 + index$sample26$446) + index$sample26$446) / index$sample26$446)));
										if((cv$weightedProbability < cv$distributionAccumulator))
											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
										else {
											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
												cv$distributionAccumulator = cv$weightedProbability;
											else
												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
										}
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample26Value447);
									} else {
										for(int index$sample26$460 = 0; index$sample26$460 < weightings.length; index$sample26$460 += 1) {
											double cv$probabilitySample26Value461 = (cv$probabilitySample26Value447 * distribution$sample26[j][index$sample26$460]);
											double cv$weightedProbability = (Math.log(cv$probabilitySample26Value461) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$434 + index$sample26$446) + index$sample26$460) / index$sample26$460)));
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
				logProbability$var51[j] = cv$distributionAccumulator;
				logProbability$sample55[j] = cv$distributionAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample55 = (((fixedFlag$sample55 && fixedFlag$sample12) && fixedFlag$sample18) && fixedFlag$sample26);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = logProbability$sample55[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var51[j] = cv$rvAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample12() {
		if(!fixedProbFlag$sample12) {
			double cv$distributionAccumulator = (((0.0 <= v1) && (v1 < weightings.length))?Math.log(weightings[v1]):Double.NEGATIVE_INFINITY);
			logProbability$var8 = cv$distributionAccumulator;
			logProbability$v1 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample12 = fixedFlag$sample12;
		} else {
			logProbability$var8 = logProbability$v1;
			logProbability$$model = (logProbability$$model + logProbability$v1);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + logProbability$v1);
		}
	}

	private final void logProbabilityValue$sample18() {
		if(!fixedProbFlag$sample18) {
			int cv$sampleValue = v2[0];
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var14 = cv$distributionAccumulator;
			logProbability$var15 = cv$distributionAccumulator;
			logProbability$v2 = (logProbability$v2 + cv$distributionAccumulator);
			if((0 <= size))
				logProbability$v3 = (logProbability$v3 + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample18 = fixedFlag$sample18;
		} else {
			logProbability$var14 = logProbability$var15;
			logProbability$v2 = (logProbability$v2 + logProbability$var15);
			if((0 <= size))
				logProbability$v3 = (logProbability$v3 + logProbability$var15);
			logProbability$$model = (logProbability$$model + logProbability$var15);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var15);
		}
	}

	private final void logProbabilityValue$sample26() {
		if(!fixedProbFlag$sample26) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				int cv$sampleValue = v2[(i + 1)];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var22[i] = cv$distributionAccumulator;
				logProbability$sample26[i] = cv$distributionAccumulator;
				logProbability$v3 = (logProbability$v3 + cv$distributionAccumulator);
			}
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample26 = fixedFlag$sample26;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				double cv$sampleValue = logProbability$sample26[i];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var22[i] = cv$sampleValue;
				logProbability$v3 = (logProbability$v3 + cv$sampleValue);
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
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(v[j], ((double)((v1 + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var51[j] = cv$distributionAccumulator;
				logProbability$sample55[j] = cv$distributionAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample55 = (((fixedFlag$sample55 && fixedFlag$sample12) && fixedFlag$sample18) && fixedFlag$sample26);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = logProbability$sample55[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var51[j] = cv$rvAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample12() {
		int cv$noStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$accumulatedProbabilities = Math.log(weightings[cv$valuePos]);
			for(int j = 0; j < size; j += 1) {
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((0 == j)) {
					if(fixedFlag$sample18) {
						if(fixedFlag$sample26) {
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((cv$valuePos + v2[0]) + v2[1]) / v2[1]));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							for(int index$sample26$147 = 0; index$sample26$147 < weightings.length; index$sample26$147 += 1) {
								double cv$probabilitySample26Value148 = distribution$sample26[0][index$sample26$147];
								double var50 = ((double)((cv$valuePos + v2[0]) + index$sample26$147) / index$sample26$147);
								if(((Math.log(cv$probabilitySample26Value148) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value148) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value148) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value148) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample26Value148)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value148);
							}
						}
					} else {
						for(int index$sample18$141 = 0; index$sample18$141 < weightings.length; index$sample18$141 += 1) {
							double cv$probabilitySample18Value142 = distribution$sample18[index$sample18$141];
							if(fixedFlag$sample26) {
								double var50 = ((double)((cv$valuePos + index$sample18$141) + v2[1]) / v2[1]);
								if(((Math.log(cv$probabilitySample18Value142) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value142) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value142) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value142) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample18Value142)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value142);
							} else {
								for(int index$sample26$153 = 0; index$sample26$153 < weightings.length; index$sample26$153 += 1) {
									double cv$probabilitySample26Value154 = (cv$probabilitySample18Value142 * distribution$sample26[0][index$sample26$153]);
									double var50 = ((double)((cv$valuePos + index$sample18$141) + index$sample26$153) / index$sample26$153);
									if(((Math.log(cv$probabilitySample26Value154) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value154) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value154) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value154) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample26Value154)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value154);
								}
							}
						}
					}
				}
				if(fixedFlag$sample26) {
					if((1 <= j)) {
						for(int index$i$207_1 = 0; index$i$207_1 < size; index$i$207_1 += 1) {
							int k = (index$i$207_1 + 1);
							if((k == (j + 1))) {
								double cv$temp$43$var50 = ((double)((cv$valuePos + v2[j]) + v2[k]) / v2[(j + 1)]);
								if((DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50);
									else
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50))) + 1)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var50));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
							}
						}
					}
				} else {
					int i = (j - 1);
					if((0 <= i)) {
						for(int index$sample26$203 = 0; index$sample26$203 < weightings.length; index$sample26$203 += 1) {
							double cv$probabilitySample26Value204 = distribution$sample26[i][index$sample26$203];
							if((i == j)) {
								double var50 = ((double)((cv$valuePos + index$sample26$203) + index$sample26$203) / index$sample26$203);
								if(((Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], var50)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], var50));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value204) + DistributionSampling.logProbabilityBernoulli(v[j], var50)))) + 1)) + Math.log(cv$probabilitySample26Value204)) + DistributionSampling.logProbabilityBernoulli(v[j], var50));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value204);
							} else {
								for(int index$sample26$210 = 0; index$sample26$210 < weightings.length; index$sample26$210 += 1) {
									double cv$probabilitySample26Value211 = (cv$probabilitySample26Value204 * distribution$sample26[j][index$sample26$210]);
									double var50 = ((double)((cv$valuePos + index$sample26$203) + index$sample26$210) / index$sample26$210);
									if(((Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], var50)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], var50));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value211) + DistributionSampling.logProbabilityBernoulli(v[j], var50)))) + 1)) + Math.log(cv$probabilitySample26Value211)) + DistributionSampling.logProbabilityBernoulli(v[j], var50));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value211);
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
			cv$var9$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double cv$logSum;
		double cv$lseMax = cv$var9$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var9$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var9$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				distribution$sample12[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				distribution$sample12[cv$indexName] = Math.exp((cv$var9$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var9$stateProbabilityGlobal.length; cv$indexName += 1)
			distribution$sample12[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample18() {
		int cv$noStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$accumulatedProbabilities = Math.log(weightings[cv$valuePos]);
			if((0 < size)) {
				guard$sample18bernoulli54$global[0] = false;
				if(!guard$sample18bernoulli54$global[0]) {
					guard$sample18bernoulli54$global[0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(fixedFlag$sample12) {
						if(fixedFlag$sample26) {
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((v1 + cv$valuePos) + v2[1]) / v2[1]));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							for(int index$sample26$130 = 0; index$sample26$130 < weightings.length; index$sample26$130 += 1) {
								double cv$probabilitySample26Value131 = distribution$sample26[0][index$sample26$130];
								double var50 = ((double)((v1 + cv$valuePos) + index$sample26$130) / index$sample26$130);
								if(((Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value131) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample26Value131)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value131);
							}
						}
					} else {
						for(int index$sample12$124 = 0; index$sample12$124 < weightings.length; index$sample12$124 += 1) {
							double cv$probabilitySample12Value125 = distribution$sample12[index$sample12$124];
							if(fixedFlag$sample26) {
								double var50 = ((double)((index$sample12$124 + cv$valuePos) + v2[1]) / v2[1]);
								if(((Math.log(cv$probabilitySample12Value125) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value125) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value125) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value125) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample12Value125)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value125);
							} else {
								for(int index$sample26$136 = 0; index$sample26$136 < weightings.length; index$sample26$136 += 1) {
									double cv$probabilitySample26Value137 = (cv$probabilitySample12Value125 * distribution$sample26[0][index$sample26$136]);
									double var50 = ((double)((index$sample12$124 + cv$valuePos) + index$sample26$136) / index$sample26$136);
									if(((Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value137) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample26Value137)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value137);
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
			cv$var15$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double cv$logSum;
		double cv$lseMax = cv$var15$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var15$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var15$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				distribution$sample18[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				distribution$sample18[cv$indexName] = Math.exp((cv$var15$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var15$stateProbabilityGlobal.length; cv$indexName += 1)
			distribution$sample18[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample26(int i) {
		int cv$noStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$accumulatedProbabilities = Math.log(weightings[cv$valuePos]);
			{
				int j = (i + 1);
				if((j < size))
					guard$sample26bernoulli54$global[j] = false;
			}
			guard$sample26bernoulli54$global[i] = false;
			int j = (i + 1);
			if(((j < size) && !guard$sample26bernoulli54$global[j])) {
				guard$sample26bernoulli54$global[j] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if(fixedFlag$sample12) {
					if((i == j)) {
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[i], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						for(int index$sample26$122 = 0; index$sample26$122 < weightings.length; index$sample26$122 += 1) {
							double cv$probabilitySample26Value123 = distribution$sample26[j][index$sample26$122];
							double var50 = ((double)((v1 + cv$valuePos) + index$sample26$122) / index$sample26$122);
							if(((Math.log(cv$probabilitySample26Value123) + DistributionSampling.logProbabilityBernoulli(v[j], var50)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value123) + DistributionSampling.logProbabilityBernoulli(v[j], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value123) + DistributionSampling.logProbabilityBernoulli(v[j], var50));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value123) + DistributionSampling.logProbabilityBernoulli(v[j], var50)))) + 1)) + Math.log(cv$probabilitySample26Value123)) + DistributionSampling.logProbabilityBernoulli(v[j], var50));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value123);
						}
					}
				} else {
					for(int index$sample12$116 = 0; index$sample12$116 < weightings.length; index$sample12$116 += 1) {
						double cv$probabilitySample12Value117 = distribution$sample12[index$sample12$116];
						if((i == j)) {
							double var50 = ((double)((index$sample12$116 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample12Value117) + DistributionSampling.logProbabilityBernoulli(v[i], var50)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value117) + DistributionSampling.logProbabilityBernoulli(v[i], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value117) + DistributionSampling.logProbabilityBernoulli(v[i], var50));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value117) + DistributionSampling.logProbabilityBernoulli(v[i], var50)))) + 1)) + Math.log(cv$probabilitySample12Value117)) + DistributionSampling.logProbabilityBernoulli(v[i], var50));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value117);
						} else {
							for(int index$sample26$128 = 0; index$sample26$128 < weightings.length; index$sample26$128 += 1) {
								double cv$probabilitySample26Value129 = (cv$probabilitySample12Value117 * distribution$sample26[j][index$sample26$128]);
								double var50 = ((double)((index$sample12$116 + cv$valuePos) + index$sample26$128) / index$sample26$128);
								if(((Math.log(cv$probabilitySample26Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var50)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var50));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var50)))) + 1)) + Math.log(cv$probabilitySample26Value129)) + DistributionSampling.logProbabilityBernoulli(v[j], var50));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value129);
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
			if(!guard$sample26bernoulli54$global[i]) {
				guard$sample26bernoulli54$global[i] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((0 == i)) {
					if(fixedFlag$sample12) {
						if(fixedFlag$sample18) {
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((v1 + v2[0]) + cv$valuePos) / cv$valuePos));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							for(int index$sample18$191 = 0; index$sample18$191 < weightings.length; index$sample18$191 += 1) {
								double cv$probabilitySample18Value192 = distribution$sample18[index$sample18$191];
								double var50 = ((double)((v1 + index$sample18$191) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample18Value192) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value192) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value192) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value192) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample18Value192)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value192);
							}
						}
					} else {
						for(int index$sample12$186 = 0; index$sample12$186 < weightings.length; index$sample12$186 += 1) {
							double cv$probabilitySample12Value187 = distribution$sample12[index$sample12$186];
							if(fixedFlag$sample18) {
								double var50 = ((double)((index$sample12$186 + v2[0]) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample12Value187) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value187) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value187) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value187) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample12Value187)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value187);
							} else {
								for(int index$sample18$196 = 0; index$sample18$196 < weightings.length; index$sample18$196 += 1) {
									double cv$probabilitySample18Value197 = (cv$probabilitySample12Value187 * distribution$sample18[index$sample18$196]);
									double var50 = ((double)((index$sample12$186 + index$sample18$196) + cv$valuePos) / cv$valuePos);
									if(((Math.log(cv$probabilitySample18Value197) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value197) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value197) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value197) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample18Value197)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value197);
								}
							}
						}
					}
				}
				if(fixedFlag$sample12) {
					int index$i$267 = (i - 1);
					if(((0 <= index$i$267) && !(index$i$267 == i))) {
						for(int index$sample26$268 = 0; index$sample26$268 < weightings.length; index$sample26$268 += 1) {
							double cv$probabilitySample26Value269 = distribution$sample26[index$i$267][index$sample26$268];
							double var50 = ((double)((v1 + index$sample26$268) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample26Value269) + DistributionSampling.logProbabilityBernoulli(v[i], var50)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value269) + DistributionSampling.logProbabilityBernoulli(v[i], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value269) + DistributionSampling.logProbabilityBernoulli(v[i], var50));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value269) + DistributionSampling.logProbabilityBernoulli(v[i], var50)))) + 1)) + Math.log(cv$probabilitySample26Value269)) + DistributionSampling.logProbabilityBernoulli(v[i], var50));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value269);
						}
					}
				} else {
					for(int index$sample12$262 = 0; index$sample12$262 < weightings.length; index$sample12$262 += 1) {
						double cv$probabilitySample12Value263 = distribution$sample12[index$sample12$262];
						int index$i$273 = (i - 1);
						if(((0 <= index$i$273) && !(index$i$273 == i))) {
							for(int index$sample26$274 = 0; index$sample26$274 < weightings.length; index$sample26$274 += 1) {
								double cv$probabilitySample26Value275 = (cv$probabilitySample12Value263 * distribution$sample26[index$i$273][index$sample26$274]);
								double var50 = ((double)((index$sample12$262 + index$sample26$274) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[i], var50)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[i], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[i], var50));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value275) + DistributionSampling.logProbabilityBernoulli(v[i], var50)))) + 1)) + Math.log(cv$probabilitySample26Value275)) + DistributionSampling.logProbabilityBernoulli(v[i], var50));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value275);
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
			if(!guard$sample26bernoulli54$global[i]) {
				guard$sample26bernoulli54$global[i] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((0 == i)) {
					if(fixedFlag$sample12) {
						if(fixedFlag$sample18) {
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((v1 + v2[0]) + cv$valuePos) / cv$valuePos));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							for(int index$sample18$374 = 0; index$sample18$374 < weightings.length; index$sample18$374 += 1) {
								double cv$probabilitySample18Value375 = distribution$sample18[index$sample18$374];
								double var50 = ((double)((v1 + index$sample18$374) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample18Value375) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value375) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value375) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value375) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample18Value375)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value375);
							}
						}
					} else {
						for(int index$sample12$369 = 0; index$sample12$369 < weightings.length; index$sample12$369 += 1) {
							double cv$probabilitySample12Value370 = distribution$sample12[index$sample12$369];
							if(fixedFlag$sample18) {
								double var50 = ((double)((index$sample12$369 + v2[0]) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample12Value370) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value370) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value370) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value370) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample12Value370)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value370);
							} else {
								for(int index$sample18$379 = 0; index$sample18$379 < weightings.length; index$sample18$379 += 1) {
									double cv$probabilitySample18Value380 = (cv$probabilitySample12Value370 * distribution$sample18[index$sample18$379]);
									double var50 = ((double)((index$sample12$369 + index$sample18$379) + cv$valuePos) / cv$valuePos);
									if(((Math.log(cv$probabilitySample18Value380) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value380) + DistributionSampling.logProbabilityBernoulli(v[0], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value380) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value380) + DistributionSampling.logProbabilityBernoulli(v[0], var50)))) + 1)) + Math.log(cv$probabilitySample18Value380)) + DistributionSampling.logProbabilityBernoulli(v[0], var50));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value380);
								}
							}
						}
					}
				}
				if(fixedFlag$sample12) {
					int index$i$413 = (i - 1);
					if(((0 <= index$i$413) && !(index$i$413 == i))) {
						for(int index$sample26$414 = 0; index$sample26$414 < weightings.length; index$sample26$414 += 1) {
							double cv$probabilitySample26Value415 = distribution$sample26[index$i$413][index$sample26$414];
							double var50 = ((double)((v1 + index$sample26$414) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample26Value415) + DistributionSampling.logProbabilityBernoulli(v[i], var50)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value415) + DistributionSampling.logProbabilityBernoulli(v[i], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value415) + DistributionSampling.logProbabilityBernoulli(v[i], var50));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value415) + DistributionSampling.logProbabilityBernoulli(v[i], var50)))) + 1)) + Math.log(cv$probabilitySample26Value415)) + DistributionSampling.logProbabilityBernoulli(v[i], var50));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value415);
						}
					}
				} else {
					for(int index$sample12$408 = 0; index$sample12$408 < weightings.length; index$sample12$408 += 1) {
						double cv$probabilitySample12Value409 = distribution$sample12[index$sample12$408];
						int index$i$419 = (i - 1);
						if(((0 <= index$i$419) && !(index$i$419 == i))) {
							for(int index$sample26$420 = 0; index$sample26$420 < weightings.length; index$sample26$420 += 1) {
								double cv$probabilitySample26Value421 = (cv$probabilitySample12Value409 * distribution$sample26[index$i$419][index$sample26$420]);
								double var50 = ((double)((index$sample12$408 + index$sample26$420) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample26Value421) + DistributionSampling.logProbabilityBernoulli(v[i], var50)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample26Value421) + DistributionSampling.logProbabilityBernoulli(v[i], var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample26Value421) + DistributionSampling.logProbabilityBernoulli(v[i], var50));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample26Value421) + DistributionSampling.logProbabilityBernoulli(v[i], var50)))) + 1)) + Math.log(cv$probabilitySample26Value421)) + DistributionSampling.logProbabilityBernoulli(v[i], var50));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample26Value421);
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
			cv$var23$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double[] cv$localProbability = distribution$sample26[i];
		double cv$logSum;
		double cv$lseMax = cv$var23$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var23$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var23$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$var23$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var23$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	@Override
	public final void allocateScratch() {
		cv$var9$stateProbabilityGlobal = new double[weightings.length];
		cv$var15$stateProbabilityGlobal = new double[weightings.length];
		guard$sample18bernoulli54$global = new boolean[length$value];
		cv$var23$stateProbabilityGlobal = new double[weightings.length];
		guard$sample26bernoulli54$global = new boolean[length$value];
	}

	@Override
	public final void allocator() {
		if(!setFlag$v2)
			v2 = new int[(length$value + 1)];
		v3 = new int[(length$value + 1)];
		if(!setFlag$v)
			v = new boolean[length$value];
		distribution$sample18 = new double[weightings.length];
		distribution$sample26 = new double[length$value][];
		for(int i = 0; i < length$value; i += 1)
			distribution$sample26[i] = new double[weightings.length];
		distribution$sample12 = new double[weightings.length];
		logProbability$var22 = new double[length$value];
		logProbability$sample26 = new double[length$value];
		logProbability$var51 = new double[length$value];
		logProbability$sample55 = new double[length$value];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample18)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample26) {
			for(int i = 0; i < size; i += 1)
				v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$, weightings);
		}
		if((!fixedFlag$sample18 || !fixedFlag$sample26)) {
			for(int k = 0; k <= size; k += 1)
				v3[k] = v2[k];
		}
		if(!fixedFlag$sample55) {
			for(int j = 0; j < size; j += 1)
				v[j] = DistributionSampling.sampleBernoulli(RNG$, ((double)((v1 + v2[j]) + v3[(j + 1)]) / v2[(j + 1)]));
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample12) {
			for(int index$var8 = 0; index$var8 < weightings.length; index$var8 += 1)
				distribution$sample12[index$var8] = weightings[index$var8];
		}
		if(!fixedFlag$sample18) {
			for(int index$var14 = 0; index$var14 < weightings.length; index$var14 += 1)
				distribution$sample18[index$var14] = weightings[index$var14];
		}
		if(!fixedFlag$sample26) {
			for(int i = 0; i < size; i += 1) {
				double[] cv$distribution$sample26 = distribution$sample26[i];
				for(int index$var22 = 0; index$var22 < weightings.length; index$var22 += 1)
					cv$distribution$sample26[index$var22] = weightings[index$var22];
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample18)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample26) {
			for(int i = 0; i < size; i += 1)
				v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$, weightings);
		}
		if((!fixedFlag$sample18 || !fixedFlag$sample26)) {
			for(int k = 0; k <= size; k += 1)
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
			if(!fixedFlag$sample26) {
				for(int i = 0; i < size; i += 1)
					sample26(i);
			}
		} else {
			if(!fixedFlag$sample26) {
				for(int i = (size - 1); i >= 0; i -= 1)
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
			logProbability$var22[i] = 0.0;
		if(!fixedProbFlag$sample26) {
			for(int i = 0; i < size; i += 1)
				logProbability$sample26[i] = 0.0;
		}
		for(int j = 0; j < size; j += 1)
			logProbability$var51[j] = 0.0;
		logProbability$v = 0.0;
		if(!fixedProbFlag$sample55) {
			for(int j = 0; j < size; j += 1)
				logProbability$sample55[j] = 0.0;
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
		if(!fixedFlag$sample26) {
			for(int i = 0; i < size; i += 1)
				v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$, weightings);
		}
		if((!fixedFlag$sample18 || !fixedFlag$sample26)) {
			for(int k = 0; k <= size; k += 1)
				v3[k] = v2[k];
		}
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
		if(setFlag$v2) {
			for(int k = 0; k <= size; k += 1)
				v3[k] = v2[k];
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel DistributionTest5(double[] weightings, boolean[] value) {\n    int size = value.length;\n    \n    int v1 = categorical(weightings).sampleDistribution();\n    \n    int[] v2 = new int[size + 1];\n    v2[0] = categorical(weightings).sampleDistribution();\n    for(int i:[0..size))\n        v2[i + 1] = categorical(weightings).sampleDistribution();\n        \n        \n    int[] v3 = new int[size + 1];\n    for(int k:[0..size]) \n        v3[k] = v2[k];\n        \n    boolean[] v = new boolean[size];\n    for(int j:[0..size))\n        v[j] = bernoulli(((1.0*v1) + v2[j] + v3[j+1])/v2[j+1]).sample();\n        \n    v.observe(value);\n}\n";
	}
}