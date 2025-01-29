package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DistributionTest2$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements DistributionTest2$CoreInterface {
	private double[] cv$var13$stateProbabilityGlobal;
	private double[] cv$var19$stateProbabilityGlobal;
	private double[] cv$var9$stateProbabilityGlobal;
	private double[] distribution$sample12;
	private double[] distribution$sample16;
	private double[][] distribution$sample22;
	private boolean fixedFlag$sample12 = false;
	private boolean fixedFlag$sample16 = false;
	private boolean fixedFlag$sample22 = false;
	private boolean fixedFlag$sample33 = false;
	private boolean fixedProbFlag$sample12 = false;
	private boolean fixedProbFlag$sample16 = false;
	private boolean fixedProbFlag$sample22 = false;
	private boolean fixedProbFlag$sample33 = false;
	private int length$value;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$sample22;
	private double[] logProbability$sample33;
	private double logProbability$v;
	private double logProbability$v1;
	private double logProbability$v2;
	private double logProbability$var12;
	private double logProbability$var13;
	private double[] logProbability$var18;
	private double[] logProbability$var29;
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

	public DistributionTest2$SingleThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
	}

	@Override
	public final boolean get$fixedFlag$sample16() {
		return fixedFlag$sample16;
	}

	@Override
	public final void set$fixedFlag$sample16(boolean cv$value) {
		fixedFlag$sample16 = cv$value;
		fixedProbFlag$sample16 = (cv$value && fixedProbFlag$sample16);
		fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
	}

	@Override
	public final boolean get$fixedFlag$sample22() {
		return fixedFlag$sample22;
	}

	@Override
	public final void set$fixedFlag$sample22(boolean cv$value) {
		fixedFlag$sample22 = cv$value;
		fixedProbFlag$sample22 = (cv$value && fixedProbFlag$sample22);
		fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
	}

	@Override
	public final boolean get$fixedFlag$sample33() {
		return fixedFlag$sample33;
	}

	@Override
	public final void set$fixedFlag$sample33(boolean cv$value) {
		fixedFlag$sample33 = cv$value;
		fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
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
		fixedProbFlag$sample33 = false;
	}

	@Override
	public final int get$v1() {
		return v1;
	}

	@Override
	public final void set$v1(int cv$value) {
		v1 = cv$value;
		fixedProbFlag$sample12 = false;
		fixedProbFlag$sample33 = false;
	}

	@Override
	public final int[] get$v2() {
		return v2;
	}

	@Override
	public final void set$v2(int[] cv$value) {
		v2 = cv$value;
		setFlag$v2 = true;
		fixedProbFlag$sample16 = false;
		fixedProbFlag$sample22 = false;
		fixedProbFlag$sample33 = false;
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

	private final void logProbabilityDistribution$sample16() {
		if(!fixedProbFlag$sample16) {
			if(fixedFlag$sample16) {
				int cv$sampleValue = v2[0];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				logProbability$var12 = cv$distributionAccumulator;
				logProbability$var13 = cv$distributionAccumulator;
				logProbability$v2 = (logProbability$v2 + cv$distributionAccumulator);
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample16 = true;
			}
		} else {
			logProbability$var12 = logProbability$var13;
			if(fixedFlag$sample16)
				logProbability$v2 = (logProbability$v2 + logProbability$var13);
			logProbability$$model = (logProbability$$model + logProbability$var13);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var13);
		}
	}

	private final void logProbabilityDistribution$sample22() {
		if(!fixedProbFlag$sample22) {
			if(fixedFlag$sample22) {
				double cv$accumulator = 0.0;
				for(int i = 1; i < size; i += 1) {
					int cv$sampleValue = v2[i];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var18[(i - 1)] = cv$distributionAccumulator;
					logProbability$sample22[(i - 1)] = cv$distributionAccumulator;
				}
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample22 = true;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i = 1; i < size; i += 1) {
				double cv$rvAccumulator = logProbability$sample22[(i - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var18[(i - 1)] = cv$rvAccumulator;
			}
			if(fixedFlag$sample22)
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample33() {
		if(!fixedProbFlag$sample33) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				boolean cv$sampleValue = v[j];
				if((0 == j)) {
					if(fixedFlag$sample12) {
						if(fixedFlag$sample16) {
							cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)v1 / v2[0]));
							cv$probabilityReached = 1.0;
						} else {
							for(int index$sample16$8 = 0; index$sample16$8 < weightings.length; index$sample16$8 += 1) {
								double cv$probabilitySample16Value9 = distribution$sample16[index$sample16$8];
								double cv$weightedProbability = (Math.log(cv$probabilitySample16Value9) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)v1 / index$sample16$8)));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample16Value9);
							}
						}
					} else {
						for(int index$sample12$3 = 0; index$sample12$3 < weightings.length; index$sample12$3 += 1) {
							double cv$probabilitySample12Value4 = distribution$sample12[index$sample12$3];
							if(fixedFlag$sample16) {
								double cv$weightedProbability = (Math.log(cv$probabilitySample12Value4) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)index$sample12$3 / v2[0])));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample12Value4);
							} else {
								for(int index$sample16$13 = 0; index$sample16$13 < weightings.length; index$sample16$13 += 1) {
									double cv$probabilitySample16Value14 = (cv$probabilitySample12Value4 * distribution$sample16[index$sample16$13]);
									double cv$weightedProbability = (Math.log(cv$probabilitySample16Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)index$sample12$3 / index$sample16$13)));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample16Value14);
								}
							}
						}
					}
				}
				if((1 <= j)) {
					if(fixedFlag$sample12) {
						if(fixedFlag$sample22) {
							double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)v1 / v2[j]));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						} else {
							for(int index$sample22$24 = 0; index$sample22$24 < weightings.length; index$sample22$24 += 1) {
								double cv$probabilitySample22Value25 = distribution$sample22[(j - 1)][index$sample22$24];
								double cv$weightedProbability = (Math.log(cv$probabilitySample22Value25) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)v1 / index$sample22$24)));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample22Value25);
							}
						}
					} else {
						for(int index$sample12$18 = 0; index$sample12$18 < weightings.length; index$sample12$18 += 1) {
							double cv$probabilitySample12Value19 = distribution$sample12[index$sample12$18];
							if(fixedFlag$sample22) {
								double cv$weightedProbability = (Math.log(cv$probabilitySample12Value19) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)index$sample12$18 / v2[j])));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample12Value19);
							} else {
								for(int index$sample22$30 = 0; index$sample22$30 < weightings.length; index$sample22$30 += 1) {
									double cv$probabilitySample22Value31 = (cv$probabilitySample12Value19 * distribution$sample22[(j - 1)][index$sample22$30]);
									double cv$weightedProbability = (Math.log(cv$probabilitySample22Value31) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)index$sample12$18 / index$sample22$30)));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample22Value31);
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
				logProbability$var29[j] = cv$distributionAccumulator;
				logProbability$sample33[j] = cv$distributionAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample33 = (((fixedFlag$sample33 && fixedFlag$sample12) && fixedFlag$sample16) && fixedFlag$sample22);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = logProbability$sample33[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var29[j] = cv$rvAccumulator;
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

	private final void logProbabilityValue$sample16() {
		if(!fixedProbFlag$sample16) {
			int cv$sampleValue = v2[0];
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var12 = cv$distributionAccumulator;
			logProbability$var13 = cv$distributionAccumulator;
			logProbability$v2 = (logProbability$v2 + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample16 = fixedFlag$sample16;
		} else {
			logProbability$var12 = logProbability$var13;
			logProbability$v2 = (logProbability$v2 + logProbability$var13);
			logProbability$$model = (logProbability$$model + logProbability$var13);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var13);
		}
	}

	private final void logProbabilityValue$sample22() {
		if(!fixedProbFlag$sample22) {
			double cv$accumulator = 0.0;
			for(int i = 1; i < size; i += 1) {
				int cv$sampleValue = v2[i];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var18[(i - 1)] = cv$distributionAccumulator;
				logProbability$sample22[(i - 1)] = cv$distributionAccumulator;
			}
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample22 = fixedFlag$sample22;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 1; i < size; i += 1) {
				double cv$rvAccumulator = logProbability$sample22[(i - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var18[(i - 1)] = cv$rvAccumulator;
			}
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample33() {
		if(!fixedProbFlag$sample33) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(v[j], ((double)v1 / v2[j]));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var29[j] = cv$distributionAccumulator;
				logProbability$sample33[j] = cv$distributionAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample33 = (((fixedFlag$sample33 && fixedFlag$sample12) && fixedFlag$sample16) && fixedFlag$sample22);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = logProbability$sample33[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var29[j] = cv$rvAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample12() {
		for(int cv$valuePos = 0; cv$valuePos < weightings.length; cv$valuePos += 1) {
			double cv$accumulatedProbabilities = Math.log(weightings[cv$valuePos]);
			for(int j = 0; j < size; j += 1) {
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((0 == j)) {
					if(fixedFlag$sample16) {
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)cv$valuePos / v2[0]));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						for(int index$sample16$4 = 0; index$sample16$4 < weightings.length; index$sample16$4 += 1) {
							double cv$probabilitySample16Value5 = distribution$sample16[index$sample16$4];
							double cv$temp$2$var28 = ((double)cv$valuePos / index$sample16$4);
							if(((Math.log(cv$probabilitySample16Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var28)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample16Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var28)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample16Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var28));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample16Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var28)))) + 1)) + Math.log(cv$probabilitySample16Value5)) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var28));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample16Value5);
						}
					}
				}
				if((1 <= j)) {
					if(fixedFlag$sample22) {
						double cv$temp$3$var28 = ((double)cv$valuePos / v2[j]);
						if((DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var28) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var28) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var28);
							else
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var28))) + 1)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var28));
						}
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
					} else {
						for(int index$sample22$10 = 0; index$sample22$10 < weightings.length; index$sample22$10 += 1) {
							double cv$probabilitySample22Value11 = distribution$sample22[(j - 1)][index$sample22$10];
							double cv$temp$4$var28 = ((double)cv$valuePos / index$sample22$10);
							if(((Math.log(cv$probabilitySample22Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var28)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample22Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var28)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample22Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var28));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample22Value11) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var28)))) + 1)) + Math.log(cv$probabilitySample22Value11)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var28));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample22Value11);
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
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var9$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var9$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var9$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var9$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var9$stateProbabilityGlobal.length; cv$indexName += 1)
				distribution$sample12[cv$indexName] = (1.0 / cv$var9$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var9$stateProbabilityGlobal.length; cv$indexName += 1)
				distribution$sample12[cv$indexName] = Math.exp((cv$var9$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
	}

	private final void sample16() {
		for(int cv$valuePos = 0; cv$valuePos < weightings.length; cv$valuePos += 1) {
			double cv$accumulatedProbabilities = Math.log(weightings[cv$valuePos]);
			if((0 < size)) {
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if(fixedFlag$sample12) {
					cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)v1 / cv$valuePos));
					cv$consumerDistributionProbabilityAccumulator = 0.0;
				} else {
					for(int index$sample12$4 = 0; index$sample12$4 < weightings.length; index$sample12$4 += 1) {
						double cv$probabilitySample12Value5 = distribution$sample12[index$sample12$4];
						double cv$temp$2$var28 = ((double)index$sample12$4 / cv$valuePos);
						if(((Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var28)) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var28)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var28));
							else
								cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var28)))) + 1)) + Math.log(cv$probabilitySample12Value5)) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var28));
						}
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value5);
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
			cv$var13$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double cv$logSum;
		double cv$lseMax = cv$var13$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var13$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var13$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var13$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var13$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var13$stateProbabilityGlobal.length; cv$indexName += 1)
				distribution$sample16[cv$indexName] = (1.0 / cv$var13$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var13$stateProbabilityGlobal.length; cv$indexName += 1)
				distribution$sample16[cv$indexName] = Math.exp((cv$var13$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
	}

	private final void sample22(int i) {
		for(int cv$valuePos = 0; cv$valuePos < weightings.length; cv$valuePos += 1) {
			double cv$accumulatedProbabilities = Math.log(weightings[cv$valuePos]);
			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
			double cv$consumerDistributionProbabilityAccumulator = 1.0;
			if(fixedFlag$sample12) {
				cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[i], ((double)v1 / cv$valuePos));
				cv$consumerDistributionProbabilityAccumulator = 0.0;
			} else {
				for(int index$sample12$5 = 0; index$sample12$5 < weightings.length; index$sample12$5 += 1) {
					double cv$probabilitySample12Value6 = distribution$sample12[index$sample12$5];
					double cv$temp$2$var28 = ((double)index$sample12$5 / cv$valuePos);
					if(((Math.log(cv$probabilitySample12Value6) + DistributionSampling.logProbabilityBernoulli(v[i], cv$temp$2$var28)) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value6) + DistributionSampling.logProbabilityBernoulli(v[i], cv$temp$2$var28)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value6) + DistributionSampling.logProbabilityBernoulli(v[i], cv$temp$2$var28));
						else
							cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value6) + DistributionSampling.logProbabilityBernoulli(v[i], cv$temp$2$var28)))) + 1)) + Math.log(cv$probabilitySample12Value6)) + DistributionSampling.logProbabilityBernoulli(v[i], cv$temp$2$var28));
					}
					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value6);
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
			cv$var19$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double[] cv$localProbability = distribution$sample22[(i - 1)];
		double cv$logSum;
		double cv$lseMax = cv$var19$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var19$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var19$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var19$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var19$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var19$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$var19$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var19$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$var19$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
	}

	@Override
	public final void allocateScratch() {
		cv$var9$stateProbabilityGlobal = new double[weightings.length];
		cv$var13$stateProbabilityGlobal = new double[weightings.length];
		cv$var19$stateProbabilityGlobal = new double[weightings.length];
	}

	@Override
	public final void allocator() {
		if(!setFlag$v2)
			v2 = new int[length$value];
		if(!setFlag$v)
			v = new boolean[length$value];
		distribution$sample22 = new double[(length$value - 1)][];
		for(int i = 1; i < length$value; i += 1)
			distribution$sample22[(i - 1)] = new double[weightings.length];
		distribution$sample12 = new double[weightings.length];
		distribution$sample16 = new double[weightings.length];
		logProbability$var18 = new double[(length$value - 1)];
		logProbability$sample22 = new double[(length$value - 1)];
		logProbability$var29 = new double[length$value];
		logProbability$sample33 = new double[length$value];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample16)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample22) {
			for(int i = 1; i < size; i += 1)
				v2[i] = DistributionSampling.sampleCategorical(RNG$, weightings);
		}
		if(!fixedFlag$sample33) {
			for(int j = 0; j < size; j += 1)
				v[j] = DistributionSampling.sampleBernoulli(RNG$, ((double)v1 / v2[j]));
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample12) {
			for(int index$var8 = 0; index$var8 < weightings.length; index$var8 += 1)
				distribution$sample12[index$var8] = weightings[index$var8];
		}
		if(!fixedFlag$sample16) {
			for(int index$var12 = 0; index$var12 < weightings.length; index$var12 += 1)
				distribution$sample16[index$var12] = weightings[index$var12];
		}
		if(!fixedFlag$sample22) {
			for(int i = 1; i < size; i += 1) {
				double[] cv$distribution$sample22 = distribution$sample22[(i - 1)];
				for(int index$var18 = 0; index$var18 < weightings.length; index$var18 += 1)
					cv$distribution$sample22[index$var18] = weightings[index$var18];
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample16)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample22) {
			for(int i = 1; i < size; i += 1)
				v2[i] = DistributionSampling.sampleCategorical(RNG$, weightings);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample12)
				sample12();
			if(!fixedFlag$sample16)
				sample16();
			if(!fixedFlag$sample22) {
				for(int i = 1; i < size; i += 1)
					sample22(i);
			}
		} else {
			if(!fixedFlag$sample22) {
				for(int i = (size - 1); i >= 1; i -= 1)
					sample22(i);
			}
			if(!fixedFlag$sample16)
				sample16();
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
		logProbability$var12 = 0.0;
		logProbability$v2 = 0.0;
		if(!fixedProbFlag$sample16)
			logProbability$var13 = 0.0;
		for(int i = 1; i < size; i += 1)
			logProbability$var18[(i - 1)] = 0.0;
		if(!fixedProbFlag$sample22) {
			for(int i = 1; i < size; i += 1)
				logProbability$sample22[(i - 1)] = 0.0;
		}
		for(int j = 0; j < size; j += 1)
			logProbability$var29[j] = 0.0;
		logProbability$v = 0.0;
		if(!fixedProbFlag$sample33) {
			for(int j = 0; j < size; j += 1)
				logProbability$sample33[j] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample33();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample12();
		logProbabilityDistribution$sample16();
		logProbabilityDistribution$sample22();
		logProbabilityDistribution$sample33();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample12();
		logProbabilityValue$sample16();
		logProbabilityValue$sample22();
		logProbabilityValue$sample33();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample16)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample22) {
			for(int i = 1; i < size; i += 1)
				v2[i] = DistributionSampling.sampleCategorical(RNG$, weightings);
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
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel DistributionTest2(double[] weightings, boolean[] value) {\n    int size = value.length;\n    \n    int v1 = categorical(weightings).sampleDistribution();\n    \n    int[] v2 = new int[size];\n    v2[0] = categorical(weightings).sampleDistribution();\n    for(int i:[1..size))\n        v2[i] = categorical(weightings).sampleDistribution();\n        \n    boolean[] v = new boolean[size];\n    for(int j:[0..size))\n        v[j] = bernoulli((1.0*v1)/v2[j]).sample();\n        \n    v.observe(value);\n}\n";
	}
}