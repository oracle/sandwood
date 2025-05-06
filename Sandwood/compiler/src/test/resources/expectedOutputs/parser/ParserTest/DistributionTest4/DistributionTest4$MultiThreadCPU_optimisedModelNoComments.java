package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DistributionTest4$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DistributionTest4$CoreInterface {
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
	private double logProbability$var10;
	private double logProbability$var11;
	private double[] logProbability$var26;
	private double logProbability$var4;
	private double[] logProbability$var52;
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
		fixedProbFlag$sample11 = (cv$value && fixedProbFlag$sample11);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$fixedFlag$sample27() {
		return fixedFlag$sample27;
	}

	@Override
	public final void set$fixedFlag$sample27(boolean cv$value) {
		fixedFlag$sample27 = cv$value;
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$fixedFlag$sample5() {
		return fixedFlag$sample5;
	}

	@Override
	public final void set$fixedFlag$sample5(boolean cv$value) {
		fixedFlag$sample5 = cv$value;
		fixedProbFlag$sample5 = (cv$value && fixedProbFlag$sample5);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
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
				int cv$sampleValue = v2[0];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				logProbability$var10 = cv$distributionAccumulator;
				logProbability$var11 = cv$distributionAccumulator;
				logProbability$v2 = (logProbability$v2 + cv$distributionAccumulator);
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample11 = true;
			}
		} else {
			logProbability$var10 = logProbability$var11;
			if(fixedFlag$sample11)
				logProbability$v2 = (logProbability$v2 + logProbability$var11);
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
				}
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample27 = true;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				double cv$rvAccumulator = logProbability$sample27[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var26[i] = cv$rvAccumulator;
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

	private final void logProbabilityDistribution$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				boolean cv$sampleValue = v[j];
				if((0 == j)) {
					if(fixedFlag$sample5) {
						if(fixedFlag$sample11) {
							if(fixedFlag$sample27) {
								double var51 = ((double)((v1 + v2[0]) + v2[1]) / v2[1]);
								cv$distributionAccumulator = Math.log((cv$sampleValue?var51:(1.0 - var51)));
								cv$probabilityReached = 1.0;
							} else {
								for(int index$sample27$383 = 0; index$sample27$383 < weightings.length; index$sample27$383 += 1) {
									double cv$probabilitySample27Value384 = distribution$sample27[0][index$sample27$383];
									double var51 = ((double)((v1 + v2[0]) + v2[1]) / v2[1]);
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
						} else {
							for(int index$sample11$372 = 0; index$sample11$372 < weightings.length; index$sample11$372 += 1) {
								double cv$probabilitySample11Value373 = distribution$sample11[index$sample11$372];
								if(fixedFlag$sample27) {
									double var51 = ((double)((v1 + v2[0]) + v2[1]) / v2[1]);
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
								} else {
									for(int index$sample27$389 = 0; index$sample27$389 < weightings.length; index$sample27$389 += 1) {
										double cv$probabilitySample27Value390 = (cv$probabilitySample11Value373 * distribution$sample27[0][index$sample27$389]);
										double var51 = ((double)((v1 + v2[0]) + v2[1]) / v2[1]);
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
						}
					} else {
						for(int index$sample5$367 = 0; index$sample5$367 < weightings.length; index$sample5$367 += 1) {
							double cv$probabilitySample5Value368 = distribution$sample5[index$sample5$367];
							if(fixedFlag$sample11) {
								if(fixedFlag$sample27) {
									double var51 = ((double)((index$sample5$367 + v2[0]) + v2[1]) / v2[1]);
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
								} else {
									for(int index$sample27$395 = 0; index$sample27$395 < weightings.length; index$sample27$395 += 1) {
										double cv$probabilitySample27Value396 = (cv$probabilitySample5Value368 * distribution$sample27[0][index$sample27$395]);
										double var51 = ((double)((index$sample5$367 + v2[0]) + v2[1]) / v2[1]);
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
							} else {
								for(int index$sample11$377 = 0; index$sample11$377 < weightings.length; index$sample11$377 += 1) {
									double cv$probabilitySample11Value378 = (cv$probabilitySample5Value368 * distribution$sample11[index$sample11$377]);
									if(fixedFlag$sample27) {
										double var51 = ((double)((index$sample5$367 + v2[0]) + v2[1]) / v2[1]);
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
									} else {
										for(int index$sample27$401 = 0; index$sample27$401 < weightings.length; index$sample27$401 += 1) {
											double cv$probabilitySample27Value402 = (cv$probabilitySample11Value378 * distribution$sample27[0][index$sample27$401]);
											double var51 = ((double)((index$sample5$367 + v2[0]) + v2[1]) / v2[1]);
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
							}
						}
					}
				}
				if(fixedFlag$sample5) {
					if(fixedFlag$sample27) {
						if((1 <= j)) {
							double var51 = ((double)((v1 + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
							double cv$weightedProbability = Math.log((cv$sampleValue?var51:(1.0 - var51)));
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
					} else {
						int i = (j - 1);
						if((0 <= i)) {
							for(int index$sample27$440 = 0; index$sample27$440 < weightings.length; index$sample27$440 += 1) {
								double cv$probabilitySample27Value441 = distribution$sample27[i][index$sample27$440];
								if((i == j)) {
									double var51 = ((double)((v1 + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
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
								} else {
									for(int index$sample27$453 = 0; index$sample27$453 < weightings.length; index$sample27$453 += 1) {
										double cv$probabilitySample27Value454 = (cv$probabilitySample27Value441 * distribution$sample27[j][index$sample27$453]);
										double var51 = ((double)((v1 + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
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
						}
					}
				} else {
					for(int index$sample5$434 = 0; index$sample5$434 < weightings.length; index$sample5$434 += 1) {
						double cv$probabilitySample5Value435 = distribution$sample5[index$sample5$434];
						if(fixedFlag$sample27) {
							if((1 <= j)) {
								double var51 = ((double)((index$sample5$434 + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
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
						} else {
							int i = (j - 1);
							if((0 <= i)) {
								for(int index$sample27$446 = 0; index$sample27$446 < weightings.length; index$sample27$446 += 1) {
									double cv$probabilitySample27Value447 = (cv$probabilitySample5Value435 * distribution$sample27[i][index$sample27$446]);
									if((i == j)) {
										double var51 = ((double)((index$sample5$434 + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
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
									} else {
										for(int index$sample27$460 = 0; index$sample27$460 < weightings.length; index$sample27$460 += 1) {
											double cv$probabilitySample27Value461 = (cv$probabilitySample27Value447 * distribution$sample27[j][index$sample27$460]);
											double var51 = ((double)((index$sample5$434 + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
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
							}
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var52[j] = cv$distributionAccumulator;
				logProbability$sample53[j] = cv$distributionAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample53 = ((fixedFlag$sample5 && fixedFlag$sample11) && fixedFlag$sample27);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = logProbability$sample53[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var52[j] = cv$rvAccumulator;
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
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample11)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample11 = fixedFlag$sample11;
		} else {
			logProbability$var10 = logProbability$var11;
			logProbability$v2 = (logProbability$v2 + logProbability$var11);
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
			}
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample27 = fixedFlag$sample27;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				double cv$rvAccumulator = logProbability$sample27[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var26[i] = cv$rvAccumulator;
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

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double var51 = ((double)((v1 + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
				double cv$distributionAccumulator = Math.log((v[j]?var51:(1.0 - var51)));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var52[j] = cv$distributionAccumulator;
				logProbability$sample53[j] = cv$distributionAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample53 = ((fixedFlag$sample5 && fixedFlag$sample11) && fixedFlag$sample27);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = logProbability$sample53[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var52[j] = cv$rvAccumulator;
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
				guard$sample11bernoulli52$global[0] = false;
				if(!guard$sample11bernoulli52$global[0]) {
					guard$sample11bernoulli52$global[0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(fixedFlag$sample5) {
						if(fixedFlag$sample27) {
							double var51 = ((double)((v1 + cv$valuePos) + v2[1]) / v2[1]);
							cv$accumulatedConsumerProbabilities = Math.log((v[0]?var51:(1.0 - var51)));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							for(int index$sample27$130 = 0; index$sample27$130 < weightings.length; index$sample27$130 += 1) {
								double cv$probabilitySample27Value131 = distribution$sample27[0][index$sample27$130];
								double var51 = ((double)((v1 + cv$valuePos) + v2[1]) / v2[1]);
								if(((Math.log(cv$probabilitySample27Value131) + Math.log((v[0]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value131) + Math.log((v[0]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value131) + Math.log((v[0]?var51:(1.0 - var51))));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value131) + Math.log((v[0]?var51:(1.0 - var51)))))) + 1)) + Math.log(cv$probabilitySample27Value131)) + Math.log((v[0]?var51:(1.0 - var51))));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value131);
							}
						}
					} else {
						for(int index$sample5$124 = 0; index$sample5$124 < weightings.length; index$sample5$124 += 1) {
							double cv$probabilitySample5Value125 = distribution$sample5[index$sample5$124];
							if(fixedFlag$sample27) {
								double var51 = ((double)((index$sample5$124 + cv$valuePos) + v2[1]) / v2[1]);
								if(((Math.log(cv$probabilitySample5Value125) + Math.log((v[0]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value125) + Math.log((v[0]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value125) + Math.log((v[0]?var51:(1.0 - var51))));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value125) + Math.log((v[0]?var51:(1.0 - var51)))))) + 1)) + Math.log(cv$probabilitySample5Value125)) + Math.log((v[0]?var51:(1.0 - var51))));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value125);
							} else {
								for(int index$sample27$136 = 0; index$sample27$136 < weightings.length; index$sample27$136 += 1) {
									double cv$probabilitySample27Value137 = (cv$probabilitySample5Value125 * distribution$sample27[0][index$sample27$136]);
									double var51 = ((double)((index$sample5$124 + cv$valuePos) + v2[1]) / v2[1]);
									if(((Math.log(cv$probabilitySample27Value137) + Math.log((v[0]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value137) + Math.log((v[0]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value137) + Math.log((v[0]?var51:(1.0 - var51))));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value137) + Math.log((v[0]?var51:(1.0 - var51)))))) + 1)) + Math.log(cv$probabilitySample27Value137)) + Math.log((v[0]?var51:(1.0 - var51))));
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
					guard$sample27bernoulli52$global[j] = false;
			}
			guard$sample27bernoulli52$global[i] = false;
			int j = (i + 1);
			if(((j < size) && !guard$sample27bernoulli52$global[j])) {
				guard$sample27bernoulli52$global[j] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if(fixedFlag$sample5) {
					if((i == j)) {
						double cv$temp$24$var51 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
						cv$accumulatedConsumerProbabilities = Math.log((v[i]?cv$temp$24$var51:(1.0 - cv$temp$24$var51)));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						for(int index$sample27$122 = 0; index$sample27$122 < weightings.length; index$sample27$122 += 1) {
							double cv$probabilitySample27Value123 = distribution$sample27[j][index$sample27$122];
							double cv$temp$27$var51 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample27Value123) + Math.log((v[j]?cv$temp$27$var51:(1.0 - cv$temp$27$var51)))) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value123) + Math.log((v[j]?cv$temp$27$var51:(1.0 - cv$temp$27$var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value123) + Math.log((v[j]?cv$temp$27$var51:(1.0 - cv$temp$27$var51))));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value123) + Math.log((v[j]?cv$temp$27$var51:(1.0 - cv$temp$27$var51)))))) + 1)) + Math.log(cv$probabilitySample27Value123)) + Math.log((v[j]?cv$temp$27$var51:(1.0 - cv$temp$27$var51))));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value123);
						}
					}
				} else {
					for(int index$sample5$116 = 0; index$sample5$116 < weightings.length; index$sample5$116 += 1) {
						double cv$probabilitySample5Value117 = distribution$sample5[index$sample5$116];
						if((i == j)) {
							double var51 = ((double)((index$sample5$116 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample5Value117) + Math.log((v[i]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value117) + Math.log((v[i]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value117) + Math.log((v[i]?var51:(1.0 - var51))));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value117) + Math.log((v[i]?var51:(1.0 - var51)))))) + 1)) + Math.log(cv$probabilitySample5Value117)) + Math.log((v[i]?var51:(1.0 - var51))));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value117);
						} else {
							for(int index$sample27$128 = 0; index$sample27$128 < weightings.length; index$sample27$128 += 1) {
								double cv$probabilitySample27Value129 = (cv$probabilitySample5Value117 * distribution$sample27[j][index$sample27$128]);
								double var51 = ((double)((index$sample5$116 + cv$valuePos) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample27Value129) + Math.log((v[j]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value129) + Math.log((v[j]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value129) + Math.log((v[j]?var51:(1.0 - var51))));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value129) + Math.log((v[j]?var51:(1.0 - var51)))))) + 1)) + Math.log(cv$probabilitySample27Value129)) + Math.log((v[j]?var51:(1.0 - var51))));
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
			if(!guard$sample27bernoulli52$global[i]) {
				guard$sample27bernoulli52$global[i] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((0 == i)) {
					if(fixedFlag$sample5) {
						if(fixedFlag$sample11) {
							double var51 = ((double)((v1 + v2[0]) + cv$valuePos) / cv$valuePos);
							cv$accumulatedConsumerProbabilities = Math.log((v[0]?var51:(1.0 - var51)));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							for(int index$sample11$228 = 0; index$sample11$228 < weightings.length; index$sample11$228 += 1) {
								double cv$probabilitySample11Value229 = distribution$sample11[index$sample11$228];
								double var51 = ((double)((v1 + v2[0]) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample11Value229) + Math.log((v[0]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value229) + Math.log((v[0]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value229) + Math.log((v[0]?var51:(1.0 - var51))));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value229) + Math.log((v[0]?var51:(1.0 - var51)))))) + 1)) + Math.log(cv$probabilitySample11Value229)) + Math.log((v[0]?var51:(1.0 - var51))));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value229);
							}
						}
					} else {
						for(int index$sample5$223 = 0; index$sample5$223 < weightings.length; index$sample5$223 += 1) {
							double cv$probabilitySample5Value224 = distribution$sample5[index$sample5$223];
							if(fixedFlag$sample11) {
								double var51 = ((double)((index$sample5$223 + v2[0]) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample5Value224) + Math.log((v[0]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value224) + Math.log((v[0]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value224) + Math.log((v[0]?var51:(1.0 - var51))));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value224) + Math.log((v[0]?var51:(1.0 - var51)))))) + 1)) + Math.log(cv$probabilitySample5Value224)) + Math.log((v[0]?var51:(1.0 - var51))));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value224);
							} else {
								for(int index$sample11$233 = 0; index$sample11$233 < weightings.length; index$sample11$233 += 1) {
									double cv$probabilitySample11Value234 = (cv$probabilitySample5Value224 * distribution$sample11[index$sample11$233]);
									double var51 = ((double)((index$sample5$223 + v2[0]) + cv$valuePos) / cv$valuePos);
									if(((Math.log(cv$probabilitySample11Value234) + Math.log((v[0]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value234) + Math.log((v[0]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value234) + Math.log((v[0]?var51:(1.0 - var51))));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value234) + Math.log((v[0]?var51:(1.0 - var51)))))) + 1)) + Math.log(cv$probabilitySample11Value234)) + Math.log((v[0]?var51:(1.0 - var51))));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value234);
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
							double cv$temp$58$var51 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample27Value269) + Math.log((v[i]?cv$temp$58$var51:(1.0 - cv$temp$58$var51)))) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value269) + Math.log((v[i]?cv$temp$58$var51:(1.0 - cv$temp$58$var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value269) + Math.log((v[i]?cv$temp$58$var51:(1.0 - cv$temp$58$var51))));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value269) + Math.log((v[i]?cv$temp$58$var51:(1.0 - cv$temp$58$var51)))))) + 1)) + Math.log(cv$probabilitySample27Value269)) + Math.log((v[i]?cv$temp$58$var51:(1.0 - cv$temp$58$var51))));
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
								double var51 = ((double)((index$sample5$262 + cv$valuePos) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample27Value275) + Math.log((v[i]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value275) + Math.log((v[i]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value275) + Math.log((v[i]?var51:(1.0 - var51))));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value275) + Math.log((v[i]?var51:(1.0 - var51)))))) + 1)) + Math.log(cv$probabilitySample27Value275)) + Math.log((v[i]?var51:(1.0 - var51))));
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
			if(!guard$sample27bernoulli52$global[i]) {
				guard$sample27bernoulli52$global[i] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((0 == i)) {
					if(fixedFlag$sample5) {
						if(fixedFlag$sample11) {
							double var51 = ((double)((v1 + v2[0]) + cv$valuePos) / cv$valuePos);
							cv$accumulatedConsumerProbabilities = Math.log((v[0]?var51:(1.0 - var51)));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							for(int index$sample11$374 = 0; index$sample11$374 < weightings.length; index$sample11$374 += 1) {
								double cv$probabilitySample11Value375 = distribution$sample11[index$sample11$374];
								double var51 = ((double)((v1 + v2[0]) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample11Value375) + Math.log((v[0]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value375) + Math.log((v[0]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value375) + Math.log((v[0]?var51:(1.0 - var51))));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value375) + Math.log((v[0]?var51:(1.0 - var51)))))) + 1)) + Math.log(cv$probabilitySample11Value375)) + Math.log((v[0]?var51:(1.0 - var51))));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value375);
							}
						}
					} else {
						for(int index$sample5$369 = 0; index$sample5$369 < weightings.length; index$sample5$369 += 1) {
							double cv$probabilitySample5Value370 = distribution$sample5[index$sample5$369];
							if(fixedFlag$sample11) {
								double var51 = ((double)((index$sample5$369 + v2[0]) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample5Value370) + Math.log((v[0]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value370) + Math.log((v[0]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value370) + Math.log((v[0]?var51:(1.0 - var51))));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value370) + Math.log((v[0]?var51:(1.0 - var51)))))) + 1)) + Math.log(cv$probabilitySample5Value370)) + Math.log((v[0]?var51:(1.0 - var51))));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value370);
							} else {
								for(int index$sample11$379 = 0; index$sample11$379 < weightings.length; index$sample11$379 += 1) {
									double cv$probabilitySample11Value380 = (cv$probabilitySample5Value370 * distribution$sample11[index$sample11$379]);
									double var51 = ((double)((index$sample5$369 + v2[0]) + cv$valuePos) / cv$valuePos);
									if(((Math.log(cv$probabilitySample11Value380) + Math.log((v[0]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value380) + Math.log((v[0]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value380) + Math.log((v[0]?var51:(1.0 - var51))));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value380) + Math.log((v[0]?var51:(1.0 - var51)))))) + 1)) + Math.log(cv$probabilitySample11Value380)) + Math.log((v[0]?var51:(1.0 - var51))));
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
							double cv$temp$90$var51 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample27Value415) + Math.log((v[i]?cv$temp$90$var51:(1.0 - cv$temp$90$var51)))) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value415) + Math.log((v[i]?cv$temp$90$var51:(1.0 - cv$temp$90$var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value415) + Math.log((v[i]?cv$temp$90$var51:(1.0 - cv$temp$90$var51))));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value415) + Math.log((v[i]?cv$temp$90$var51:(1.0 - cv$temp$90$var51)))))) + 1)) + Math.log(cv$probabilitySample27Value415)) + Math.log((v[i]?cv$temp$90$var51:(1.0 - cv$temp$90$var51))));
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
								double var51 = ((double)((index$sample5$408 + cv$valuePos) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample27Value421) + Math.log((v[i]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value421) + Math.log((v[i]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value421) + Math.log((v[i]?var51:(1.0 - var51))));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value421) + Math.log((v[i]?var51:(1.0 - var51)))))) + 1)) + Math.log(cv$probabilitySample27Value421)) + Math.log((v[i]?var51:(1.0 - var51))));
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
							double var51 = ((double)((cv$valuePos + v2[0]) + v2[1]) / v2[1]);
							cv$accumulatedConsumerProbabilities = Math.log((v[0]?var51:(1.0 - var51)));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							for(int index$sample27$177 = 0; index$sample27$177 < weightings.length; index$sample27$177 += 1) {
								double cv$probabilitySample27Value178 = distribution$sample27[0][index$sample27$177];
								double var51 = ((double)((cv$valuePos + v2[0]) + v2[1]) / v2[1]);
								if(((Math.log(cv$probabilitySample27Value178) + Math.log((v[0]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value178) + Math.log((v[0]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value178) + Math.log((v[0]?var51:(1.0 - var51))));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value178) + Math.log((v[0]?var51:(1.0 - var51)))))) + 1)) + Math.log(cv$probabilitySample27Value178)) + Math.log((v[0]?var51:(1.0 - var51))));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value178);
							}
						}
					} else {
						for(int index$sample11$171 = 0; index$sample11$171 < weightings.length; index$sample11$171 += 1) {
							double cv$probabilitySample11Value172 = distribution$sample11[index$sample11$171];
							if(fixedFlag$sample27) {
								double var51 = ((double)((cv$valuePos + v2[0]) + v2[1]) / v2[1]);
								if(((Math.log(cv$probabilitySample11Value172) + Math.log((v[0]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value172) + Math.log((v[0]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value172) + Math.log((v[0]?var51:(1.0 - var51))));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value172) + Math.log((v[0]?var51:(1.0 - var51)))))) + 1)) + Math.log(cv$probabilitySample11Value172)) + Math.log((v[0]?var51:(1.0 - var51))));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value172);
							} else {
								for(int index$sample27$183 = 0; index$sample27$183 < weightings.length; index$sample27$183 += 1) {
									double cv$probabilitySample27Value184 = (cv$probabilitySample11Value172 * distribution$sample27[0][index$sample27$183]);
									double var51 = ((double)((cv$valuePos + v2[0]) + v2[1]) / v2[1]);
									if(((Math.log(cv$probabilitySample27Value184) + Math.log((v[0]?var51:(1.0 - var51)))) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value184) + Math.log((v[0]?var51:(1.0 - var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value184) + Math.log((v[0]?var51:(1.0 - var51))));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value184) + Math.log((v[0]?var51:(1.0 - var51)))))) + 1)) + Math.log(cv$probabilitySample27Value184)) + Math.log((v[0]?var51:(1.0 - var51))));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value184);
								}
							}
						}
					}
				}
				if(fixedFlag$sample27) {
					if((1 <= j)) {
						double cv$temp$44$var51 = ((double)((cv$valuePos + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
						if((Math.log((v[j]?cv$temp$44$var51:(1.0 - cv$temp$44$var51))) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((Math.log((v[j]?cv$temp$44$var51:(1.0 - cv$temp$44$var51))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = Math.log((v[j]?cv$temp$44$var51:(1.0 - cv$temp$44$var51)));
							else
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log((v[j]?cv$temp$44$var51:(1.0 - cv$temp$44$var51))))) + 1)) + Math.log((v[j]?cv$temp$44$var51:(1.0 - cv$temp$44$var51))));
						}
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
					}
				} else {
					int i = (j - 1);
					if((0 <= i)) {
						for(int index$sample27$203 = 0; index$sample27$203 < weightings.length; index$sample27$203 += 1) {
							double cv$probabilitySample27Value204 = distribution$sample27[i][index$sample27$203];
							if((i == j)) {
								double cv$temp$45$var51 = ((double)((cv$valuePos + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
								if(((Math.log(cv$probabilitySample27Value204) + Math.log((v[j]?cv$temp$45$var51:(1.0 - cv$temp$45$var51)))) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value204) + Math.log((v[j]?cv$temp$45$var51:(1.0 - cv$temp$45$var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value204) + Math.log((v[j]?cv$temp$45$var51:(1.0 - cv$temp$45$var51))));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value204) + Math.log((v[j]?cv$temp$45$var51:(1.0 - cv$temp$45$var51)))))) + 1)) + Math.log(cv$probabilitySample27Value204)) + Math.log((v[j]?cv$temp$45$var51:(1.0 - cv$temp$45$var51))));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value204);
							} else {
								for(int index$sample27$210 = 0; index$sample27$210 < weightings.length; index$sample27$210 += 1) {
									double cv$probabilitySample27Value211 = (cv$probabilitySample27Value204 * distribution$sample27[j][index$sample27$210]);
									double cv$temp$48$var51 = ((double)((cv$valuePos + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
									if(((Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?cv$temp$48$var51:(1.0 - cv$temp$48$var51)))) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?cv$temp$48$var51:(1.0 - cv$temp$48$var51)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?cv$temp$48$var51:(1.0 - cv$temp$48$var51))));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value211) + Math.log((v[j]?cv$temp$48$var51:(1.0 - cv$temp$48$var51)))))) + 1)) + Math.log(cv$probabilitySample27Value211)) + Math.log((v[j]?cv$temp$48$var51:(1.0 - cv$temp$48$var51))));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value211);
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
		guard$sample11bernoulli52$global = new boolean[length$value];
		cv$var27$stateProbabilityGlobal = new double[weightings.length];
		guard$sample27bernoulli52$global = new boolean[length$value];
	}

	@Override
	public final void allocator() {
		if((!fixedFlag$sample11 || !fixedFlag$sample27))
			v2 = new int[(length$value + 1)];
		v = new boolean[length$value];
		distribution$sample5 = new double[weightings.length];
		distribution$sample11 = new double[weightings.length];
		distribution$sample27 = new double[length$value][];
		for(int i = 0; i < length$value; i += 1)
			distribution$sample27[i] = new double[weightings.length];
		logProbability$var26 = new double[length$value];
		logProbability$sample27 = new double[length$value];
		logProbability$var52 = new double[length$value];
		logProbability$sample53 = new double[length$value];
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

		parallelFor(RNG$, 0, size, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						v[j] = DistributionSampling.sampleBernoulli(RNG$1, ((double)((v1 + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]));
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
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
	public final void forwardGenerationPrime() {
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

		parallelFor(RNG$, 0, size, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						v[j] = DistributionSampling.sampleBernoulli(RNG$1, ((double)((v1 + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]));
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

	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
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
			logProbability$v1 = Double.NaN;
		logProbability$var10 = 0.0;
		logProbability$v2 = 0.0;
		if(!fixedProbFlag$sample11)
			logProbability$var11 = Double.NaN;
		for(int i = 0; i < size; i += 1)
			logProbability$var26[i] = Double.NaN;
		if(!fixedProbFlag$sample27) {
			for(int i = 0; i < size; i += 1)
				logProbability$sample27[i] = Double.NaN;
		}
		for(int j = 0; j < size; j += 1)
			logProbability$var52[j] = Double.NaN;
		logProbability$v = 0.0;
		if(!fixedProbFlag$sample53) {
			for(int j = 0; j < size; j += 1)
				logProbability$sample53[j] = Double.NaN;
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
		int cv$length1 = v.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			v[cv$index1] = value[cv$index1];
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