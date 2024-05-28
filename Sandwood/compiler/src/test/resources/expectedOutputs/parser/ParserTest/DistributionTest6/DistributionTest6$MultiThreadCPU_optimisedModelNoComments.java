package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DistributionTest6$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DistributionTest6$CoreInterface {
	private double[] cv$var15$stateProbabilityGlobal;
	private double[][] cv$var31$stateProbabilityGlobal;
	private double[] cv$var9$stateProbabilityGlobal;
	private double[] distribution$sample12;
	private double[] distribution$sample18;
	private double[][] distribution$sample34;
	private boolean fixedFlag$sample12 = false;
	private boolean fixedFlag$sample18 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample56 = false;
	private boolean fixedProbFlag$sample12 = false;
	private boolean fixedProbFlag$sample18 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample56 = false;
	private boolean[] guard$sample18bernoulli55$global;
	private boolean[][] guard$sample34bernoulli55$global;
	private int length$value;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$sample34;
	private double[] logProbability$sample56;
	private double logProbability$v;
	private double logProbability$v1;
	private double logProbability$v2;
	private double logProbability$var14;
	private double logProbability$var15;
	private double[] logProbability$var30;
	private double[] logProbability$var52;
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
		fixedProbFlag$sample12 = (cv$value && fixedProbFlag$sample12);
		fixedProbFlag$sample56 = (cv$value && fixedProbFlag$sample56);
	}

	@Override
	public final boolean get$fixedFlag$sample18() {
		return fixedFlag$sample18;
	}

	@Override
	public final void set$fixedFlag$sample18(boolean cv$value) {
		fixedFlag$sample18 = cv$value;
		fixedProbFlag$sample18 = (cv$value && fixedProbFlag$sample18);
		fixedProbFlag$sample56 = (cv$value && fixedProbFlag$sample56);
	}

	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		fixedFlag$sample34 = cv$value;
		fixedProbFlag$sample34 = (cv$value && fixedProbFlag$sample34);
		fixedProbFlag$sample56 = (cv$value && fixedProbFlag$sample56);
	}

	@Override
	public final boolean get$fixedFlag$sample56() {
		return fixedFlag$sample56;
	}

	@Override
	public final void set$fixedFlag$sample56(boolean cv$value) {
		fixedFlag$sample56 = cv$value;
		fixedProbFlag$sample56 = (cv$value && fixedProbFlag$sample56);
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
		fixedProbFlag$sample56 = false;
	}

	@Override
	public final int get$v1() {
		return v1;
	}

	@Override
	public final void set$v1(int cv$value) {
		v1 = cv$value;
		fixedProbFlag$sample12 = false;
		fixedProbFlag$sample56 = false;
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
		fixedProbFlag$sample34 = false;
		fixedProbFlag$sample56 = false;
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
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample18 = true;
			}
		} else {
			logProbability$var14 = logProbability$var15;
			if(fixedFlag$sample18)
				logProbability$v2 = (logProbability$v2 + logProbability$var15);
			logProbability$$model = (logProbability$$model + logProbability$var15);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var15);
		}
	}

	private final void logProbabilityDistribution$sample34() {
		if(!fixedProbFlag$sample34) {
			if(fixedFlag$sample34) {
				double cv$accumulator = 0.0;
				for(int i = 0; i < size; i += 1) {
					int cv$sampleValue = v2[(i + 1)];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var30[i] = cv$distributionAccumulator;
					logProbability$sample34[i] = cv$distributionAccumulator;
				}
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample34 = true;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				double cv$rvAccumulator = logProbability$sample34[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var30[i] = cv$rvAccumulator;
			}
			if(fixedFlag$sample34)
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample56() {
		if(!fixedProbFlag$sample56) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				boolean cv$sampleValue = v[j];
				if((0 == j)) {
					if(fixedFlag$sample12) {
						if(fixedFlag$sample18) {
							cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[0]) + v2[0]) / v2[0]));
							cv$probabilityReached = 1.0;
						} else {
							for(int index$sample18$8 = 0; index$sample18$8 < weightings.length; index$sample18$8 += 1) {
								double cv$probabilitySample18Value9 = distribution$sample18[index$sample18$8];
								double cv$weightedProbability = (Math.log(cv$probabilitySample18Value9) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + index$sample18$8) + index$sample18$8) / index$sample18$8)));
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
					} else {
						for(int index$sample12$3 = 0; index$sample12$3 < weightings.length; index$sample12$3 += 1) {
							double cv$probabilitySample12Value4 = distribution$sample12[index$sample12$3];
							if(fixedFlag$sample18) {
								double cv$weightedProbability = (Math.log(cv$probabilitySample12Value4) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$3 + v2[0]) + v2[0]) / v2[0])));
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
								for(int index$sample18$13 = 0; index$sample18$13 < weightings.length; index$sample18$13 += 1) {
									double cv$probabilitySample18Value14 = (cv$probabilitySample12Value4 * distribution$sample18[index$sample18$13]);
									double cv$weightedProbability = (Math.log(cv$probabilitySample18Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$3 + index$sample18$13) + index$sample18$13) / index$sample18$13)));
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
						}
					}
				}
				if(fixedFlag$sample12) {
					if(fixedFlag$sample34) {
						if((1 <= j)) {
							double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + v2[j]) + v2[j]) / v2[j]));
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
							for(int index$sample34$440 = 0; index$sample34$440 < weightings.length; index$sample34$440 += 1) {
								double cv$probabilitySample34Value441 = distribution$sample34[i][index$sample34$440];
								double cv$weightedProbability = (Math.log(cv$probabilitySample34Value441) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((v1 + index$sample34$440) + index$sample34$440) / index$sample34$440)));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value441);
							}
						}
					}
				} else {
					for(int index$sample12$434 = 0; index$sample12$434 < weightings.length; index$sample12$434 += 1) {
						double cv$probabilitySample12Value435 = distribution$sample12[index$sample12$434];
						if(fixedFlag$sample34) {
							if((1 <= j)) {
								double cv$weightedProbability = (Math.log(cv$probabilitySample12Value435) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$434 + v2[j]) + v2[j]) / v2[j])));
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
						} else {
							int i = (j - 1);
							if((0 <= i)) {
								for(int index$sample34$446 = 0; index$sample34$446 < weightings.length; index$sample34$446 += 1) {
									double cv$probabilitySample34Value447 = (cv$probabilitySample12Value435 * distribution$sample34[i][index$sample34$446]);
									double cv$weightedProbability = (Math.log(cv$probabilitySample34Value447) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, ((double)((index$sample12$434 + index$sample34$446) + index$sample34$446) / index$sample34$446)));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample34Value447);
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
				logProbability$sample56[j] = cv$distributionAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample56 = (((fixedFlag$sample56 && fixedFlag$sample12) && fixedFlag$sample18) && fixedFlag$sample34);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = logProbability$sample56[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var52[j] = cv$rvAccumulator;
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
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample18 = fixedFlag$sample18;
		} else {
			logProbability$var14 = logProbability$var15;
			logProbability$v2 = (logProbability$v2 + logProbability$var15);
			logProbability$$model = (logProbability$$model + logProbability$var15);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var15);
		}
	}

	private final void logProbabilityValue$sample34() {
		if(!fixedProbFlag$sample34) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				int cv$sampleValue = v2[(i + 1)];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var30[i] = cv$distributionAccumulator;
				logProbability$sample34[i] = cv$distributionAccumulator;
			}
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample34 = fixedFlag$sample34;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1) {
				double cv$rvAccumulator = logProbability$sample34[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var30[i] = cv$rvAccumulator;
			}
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample56() {
		if(!fixedProbFlag$sample56) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(v[j], ((double)((v1 + v2[j]) + v2[j]) / v2[j]));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var52[j] = cv$distributionAccumulator;
				logProbability$sample56[j] = cv$distributionAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample56 = (((fixedFlag$sample56 && fixedFlag$sample12) && fixedFlag$sample18) && fixedFlag$sample34);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = logProbability$sample56[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var52[j] = cv$rvAccumulator;
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
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((cv$valuePos + v2[0]) + v2[0]) / v2[0]));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						for(int index$sample18$4 = 0; index$sample18$4 < weightings.length; index$sample18$4 += 1) {
							double cv$probabilitySample18Value5 = distribution$sample18[index$sample18$4];
							double cv$temp$2$var51 = ((double)((cv$valuePos + index$sample18$4) + index$sample18$4) / index$sample18$4);
							if(((Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var51)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var51));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample18Value5) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var51)))) + 1)) + Math.log(cv$probabilitySample18Value5)) + DistributionSampling.logProbabilityBernoulli(v[0], cv$temp$2$var51));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample18Value5);
						}
					}
				}
				if(fixedFlag$sample34) {
					if((1 <= j)) {
						double cv$temp$43$var51 = ((double)((cv$valuePos + v2[j]) + v2[j]) / v2[j]);
						if((DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var51) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var51) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var51);
							else
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var51))) + 1)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$43$var51));
						}
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
					}
				} else {
					int i = (j - 1);
					if((0 <= i)) {
						for(int index$sample34$203 = 0; index$sample34$203 < weightings.length; index$sample34$203 += 1) {
							double cv$probabilitySample34Value204 = distribution$sample34[i][index$sample34$203];
							double cv$temp$44$var51 = ((double)((cv$valuePos + index$sample34$203) + index$sample34$203) / index$sample34$203);
							if(((Math.log(cv$probabilitySample34Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var51)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var51));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value204) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var51)))) + 1)) + Math.log(cv$probabilitySample34Value204)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$44$var51));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value204);
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
				guard$sample18bernoulli55$global[0] = false;
				if(!guard$sample18bernoulli55$global[0]) {
					guard$sample18bernoulli55$global[0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(fixedFlag$sample12) {
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						for(int index$sample12$11 = 0; index$sample12$11 < weightings.length; index$sample12$11 += 1) {
							double cv$probabilitySample12Value12 = distribution$sample12[index$sample12$11];
							double var51 = ((double)((index$sample12$11 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[0], var51)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[0], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[0], var51));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value12) + DistributionSampling.logProbabilityBernoulli(v[0], var51)))) + 1)) + Math.log(cv$probabilitySample12Value12)) + DistributionSampling.logProbabilityBernoulli(v[0], var51));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value12);
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
				if(!guard$sample18bernoulli55$global[0]) {
					guard$sample18bernoulli55$global[0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(fixedFlag$sample12) {
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						for(int index$sample12$155 = 0; index$sample12$155 < weightings.length; index$sample12$155 += 1) {
							double cv$probabilitySample12Value156 = distribution$sample12[index$sample12$155];
							double var51 = ((double)((index$sample12$155 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[0], var51)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[0], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[0], var51));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value156) + DistributionSampling.logProbabilityBernoulli(v[0], var51)))) + 1)) + Math.log(cv$probabilitySample12Value156)) + DistributionSampling.logProbabilityBernoulli(v[0], var51));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value156);
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
				if(!guard$sample18bernoulli55$global[0]) {
					guard$sample18bernoulli55$global[0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(fixedFlag$sample12) {
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[0], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						for(int index$sample12$299 = 0; index$sample12$299 < weightings.length; index$sample12$299 += 1) {
							double cv$probabilitySample12Value300 = distribution$sample12[index$sample12$299];
							double var51 = ((double)((index$sample12$299 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[0], var51)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[0], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[0], var51));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value300) + DistributionSampling.logProbabilityBernoulli(v[0], var51)))) + 1)) + Math.log(cv$probabilitySample12Value300)) + DistributionSampling.logProbabilityBernoulli(v[0], var51));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value300);
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

	private final void sample34(int i, int threadID$cv$i, Rng RNG$) {
		int cv$noStates = weightings.length;
		double[] cv$stateProbabilityLocal = cv$var31$stateProbabilityGlobal[threadID$cv$i];
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$accumulatedProbabilities = Math.log(weightings[cv$valuePos]);
			boolean[] guard$sample34bernoulli55 = guard$sample34bernoulli55$global[threadID$cv$i];
			{
				int j = (i + 1);
				if((j < size))
					guard$sample34bernoulli55[j] = false;
			}
			{
				int j = (i + 1);
				if((j < size))
					guard$sample34bernoulli55[j] = false;
			}
			{
				int j = (i + 1);
				if((j < size))
					guard$sample34bernoulli55[j] = false;
			}
			{
				int j = (i + 1);
				if(((j < size) && !guard$sample34bernoulli55[j])) {
					guard$sample34bernoulli55[j] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(fixedFlag$sample12) {
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[j], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
						int index$i$133 = (j - 1);
						if(!(index$i$133 == i)) {
							for(int index$sample34$134 = 0; index$sample34$134 < weightings.length; index$sample34$134 += 1) {
								double cv$probabilitySample34Value135 = distribution$sample34[index$i$133][index$sample34$134];
								double cv$temp$24$var51 = ((double)((v1 + cv$valuePos) + cv$valuePos) / index$sample34$134);
								if(((Math.log(cv$probabilitySample34Value135) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var51)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value135) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value135) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var51));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value135) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var51)))) + 1)) + Math.log(cv$probabilitySample34Value135)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$24$var51));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value135);
							}
						}
						int index$i$121 = (j - 1);
						if(!(index$i$121 == i)) {
							for(int index$sample34$122 = 0; index$sample34$122 < weightings.length; index$sample34$122 += 1) {
								double cv$probabilitySample34Value123 = distribution$sample34[index$i$121][index$sample34$122];
								double cv$temp$25$var51 = ((double)((v1 + cv$valuePos) + index$sample34$122) / cv$valuePos);
								if(((Math.log(cv$probabilitySample34Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var51)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var51));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var51)))) + 1)) + Math.log(cv$probabilitySample34Value123)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$25$var51));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value123);
								double cv$temp$26$var51 = ((double)((v1 + cv$valuePos) + index$sample34$122) / index$sample34$122);
								if(((Math.log(cv$probabilitySample34Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var51)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var51));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value123) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var51)))) + 1)) + Math.log(cv$probabilitySample34Value123)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$26$var51));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value123);
							}
						}
					} else {
						for(int index$sample12$116 = 0; index$sample12$116 < weightings.length; index$sample12$116 += 1) {
							double cv$probabilitySample12Value117 = distribution$sample12[index$sample12$116];
							{
								double var51 = ((double)((index$sample12$116 + cv$valuePos) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample12Value117) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value117) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value117) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value117) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample12Value117)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value117);
							}
							int index$i$146 = (j - 1);
							if(!(index$i$146 == i)) {
								for(int index$sample34$147 = 0; index$sample34$147 < weightings.length; index$sample34$147 += 1) {
									double cv$probabilitySample34Value148 = (cv$probabilitySample12Value117 * distribution$sample34[index$i$146][index$sample34$147]);
									double var51 = ((double)((index$sample12$116 + cv$valuePos) + cv$valuePos) / index$sample34$147);
									if(((Math.log(cv$probabilitySample34Value148) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value148) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value148) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value148) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample34Value148)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value148);
								}
							}
							int index$i$127 = (j - 1);
							if(!(index$i$127 == i)) {
								for(int index$sample34$128 = 0; index$sample34$128 < weightings.length; index$sample34$128 += 1) {
									double cv$probabilitySample34Value129 = (cv$probabilitySample12Value117 * distribution$sample34[index$i$127][index$sample34$128]);
									{
										double var51 = ((double)((index$sample12$116 + cv$valuePos) + index$sample34$128) / cv$valuePos);
										if(((Math.log(cv$probabilitySample34Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
											else
												cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample34Value129)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
										}
										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value129);
									}
									double var51 = ((double)((index$sample12$116 + cv$valuePos) + index$sample34$128) / index$sample34$128);
									if(((Math.log(cv$probabilitySample34Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value129) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample34Value129)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value129);
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
			{
				int j = (i + 1);
				if(((j < size) && !guard$sample34bernoulli55[j])) {
					guard$sample34bernoulli55[j] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(fixedFlag$sample12) {
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[j], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
						int index$i$279 = (j - 1);
						if(!(index$i$279 == i)) {
							for(int index$sample34$280 = 0; index$sample34$280 < weightings.length; index$sample34$280 += 1) {
								double cv$probabilitySample34Value281 = distribution$sample34[index$i$279][index$sample34$280];
								double cv$temp$56$var51 = ((double)((v1 + cv$valuePos) + cv$valuePos) / index$sample34$280);
								if(((Math.log(cv$probabilitySample34Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var51)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var51));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value281) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var51)))) + 1)) + Math.log(cv$probabilitySample34Value281)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$56$var51));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value281);
							}
						}
						int index$i$267 = (j - 1);
						if(!(index$i$267 == i)) {
							for(int index$sample34$268 = 0; index$sample34$268 < weightings.length; index$sample34$268 += 1) {
								double cv$probabilitySample34Value269 = distribution$sample34[index$i$267][index$sample34$268];
								double cv$temp$57$var51 = ((double)((v1 + index$sample34$268) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample34Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var51)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var51));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var51)))) + 1)) + Math.log(cv$probabilitySample34Value269)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$57$var51));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value269);
								double cv$temp$58$var51 = ((double)((v1 + index$sample34$268) + cv$valuePos) / index$sample34$268);
								if(((Math.log(cv$probabilitySample34Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var51)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var51));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value269) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var51)))) + 1)) + Math.log(cv$probabilitySample34Value269)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$58$var51));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value269);
							}
						}
					} else {
						for(int index$sample12$262 = 0; index$sample12$262 < weightings.length; index$sample12$262 += 1) {
							double cv$probabilitySample12Value263 = distribution$sample12[index$sample12$262];
							{
								double var51 = ((double)((index$sample12$262 + cv$valuePos) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample12Value263) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value263) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value263) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value263) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample12Value263)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value263);
							}
							int index$i$292 = (j - 1);
							if(!(index$i$292 == i)) {
								for(int index$sample34$293 = 0; index$sample34$293 < weightings.length; index$sample34$293 += 1) {
									double cv$probabilitySample34Value294 = (cv$probabilitySample12Value263 * distribution$sample34[index$i$292][index$sample34$293]);
									double var51 = ((double)((index$sample12$262 + cv$valuePos) + cv$valuePos) / index$sample34$293);
									if(((Math.log(cv$probabilitySample34Value294) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value294) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value294) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value294) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample34Value294)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value294);
								}
							}
							int index$i$273 = (j - 1);
							if(!(index$i$273 == i)) {
								for(int index$sample34$274 = 0; index$sample34$274 < weightings.length; index$sample34$274 += 1) {
									double cv$probabilitySample34Value275 = (cv$probabilitySample12Value263 * distribution$sample34[index$i$273][index$sample34$274]);
									{
										double var51 = ((double)((index$sample12$262 + index$sample34$274) + cv$valuePos) / cv$valuePos);
										if(((Math.log(cv$probabilitySample34Value275) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value275) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value275) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
											else
												cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value275) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample34Value275)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
										}
										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value275);
									}
									double var51 = ((double)((index$sample12$262 + index$sample34$274) + cv$valuePos) / index$sample34$274);
									if(((Math.log(cv$probabilitySample34Value275) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value275) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value275) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value275) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample34Value275)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value275);
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
			int j = (i + 1);
			if(((j < size) && !guard$sample34bernoulli55[j])) {
				guard$sample34bernoulli55[j] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if(fixedFlag$sample12) {
					cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v[j], ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos));
					cv$consumerDistributionProbabilityAccumulator = 0.0;
					int index$i$425 = (j - 1);
					if(!(index$i$425 == i)) {
						for(int index$sample34$426 = 0; index$sample34$426 < weightings.length; index$sample34$426 += 1) {
							double cv$probabilitySample34Value427 = distribution$sample34[index$i$425][index$sample34$426];
							double cv$temp$88$var51 = ((double)((v1 + cv$valuePos) + index$sample34$426) / cv$valuePos);
							if(((Math.log(cv$probabilitySample34Value427) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var51)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value427) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value427) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var51));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value427) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var51)))) + 1)) + Math.log(cv$probabilitySample34Value427)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$88$var51));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value427);
						}
					}
					int index$i$413 = (j - 1);
					if(!(index$i$413 == i)) {
						for(int index$sample34$414 = 0; index$sample34$414 < weightings.length; index$sample34$414 += 1) {
							double cv$probabilitySample34Value415 = distribution$sample34[index$i$413][index$sample34$414];
							double cv$temp$89$var51 = ((double)((v1 + index$sample34$414) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample34Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var51)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var51));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var51)))) + 1)) + Math.log(cv$probabilitySample34Value415)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$89$var51));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value415);
							double cv$temp$90$var51 = ((double)((v1 + index$sample34$414) + index$sample34$414) / cv$valuePos);
							if(((Math.log(cv$probabilitySample34Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var51)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var51));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value415) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var51)))) + 1)) + Math.log(cv$probabilitySample34Value415)) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$90$var51));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value415);
						}
					}
				} else {
					for(int index$sample12$408 = 0; index$sample12$408 < weightings.length; index$sample12$408 += 1) {
						double cv$probabilitySample12Value409 = distribution$sample12[index$sample12$408];
						{
							double var51 = ((double)((index$sample12$408 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample12Value409) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value409) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value409) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value409) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample12Value409)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value409);
						}
						int index$i$438 = (j - 1);
						if(!(index$i$438 == i)) {
							for(int index$sample34$439 = 0; index$sample34$439 < weightings.length; index$sample34$439 += 1) {
								double cv$probabilitySample34Value440 = (cv$probabilitySample12Value409 * distribution$sample34[index$i$438][index$sample34$439]);
								double var51 = ((double)((index$sample12$408 + cv$valuePos) + index$sample34$439) / cv$valuePos);
								if(((Math.log(cv$probabilitySample34Value440) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value440) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value440) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value440) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample34Value440)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value440);
							}
						}
						int index$i$419 = (j - 1);
						if(!(index$i$419 == i)) {
							for(int index$sample34$420 = 0; index$sample34$420 < weightings.length; index$sample34$420 += 1) {
								double cv$probabilitySample34Value421 = (cv$probabilitySample12Value409 * distribution$sample34[index$i$419][index$sample34$420]);
								{
									double var51 = ((double)((index$sample12$408 + index$sample34$420) + cv$valuePos) / cv$valuePos);
									if(((Math.log(cv$probabilitySample34Value421) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value421) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value421) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value421) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample34Value421)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value421);
								}
								double var51 = ((double)((index$sample12$408 + index$sample34$420) + index$sample34$420) / cv$valuePos);
								if(((Math.log(cv$probabilitySample34Value421) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample34Value421) + DistributionSampling.logProbabilityBernoulli(v[j], var51)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample34Value421) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample34Value421) + DistributionSampling.logProbabilityBernoulli(v[j], var51)))) + 1)) + Math.log(cv$probabilitySample34Value421)) + DistributionSampling.logProbabilityBernoulli(v[j], var51));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample34Value421);
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
			cv$stateProbabilityLocal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double[] cv$localProbability = distribution$sample34[i];
		double cv$logSum;
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
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
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
		cv$var9$stateProbabilityGlobal = new double[weightings.length];
		cv$var15$stateProbabilityGlobal = new double[weightings.length];
		guard$sample18bernoulli55$global = new boolean[length$value];
		{
			int cv$threadCount = threadCount();
			cv$var31$stateProbabilityGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var31$stateProbabilityGlobal[cv$index] = new double[weightings.length];
		}
		int cv$threadCount = threadCount();
		guard$sample34bernoulli55$global = new boolean[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			guard$sample34bernoulli55$global[cv$index] = new boolean[length$value];
	}

	@Override
	public final void allocator() {
		if(!setFlag$v2)
			v2 = new int[(length$value + 1)];
		if(!setFlag$v)
			v = new boolean[length$value];
		distribution$sample18 = new double[weightings.length];
		distribution$sample34 = new double[length$value][];
		for(int i = 0; i < length$value; i += 1)
			distribution$sample34[i] = new double[weightings.length];
		distribution$sample12 = new double[weightings.length];
		logProbability$var30 = new double[length$value];
		logProbability$sample34 = new double[length$value];
		logProbability$var52 = new double[length$value];
		logProbability$sample56 = new double[length$value];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample18)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample34)
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings);
				}
			);

		if(!fixedFlag$sample56)
			parallelFor(RNG$, 0, size, 1,
				(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j = forStart$j; j < forEnd$j; j += 1)
							v[j] = DistributionSampling.sampleBernoulli(RNG$1, ((double)((v1 + v2[j]) + v2[j]) / v2[j]));
				}
			);

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
		if(!fixedFlag$sample34)
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							double[] cv$distribution$sample34 = distribution$sample34[i];
							for(int index$var30 = 0; index$var30 < weightings.length; index$var30 += 1)
								cv$distribution$sample34[index$var30] = weightings[index$var30];
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
		if(!fixedFlag$sample34)
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings);
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
			if(!fixedFlag$sample34)
				parallelFor(RNG$, 0, size, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i = forStart$i; i < forEnd$i; i += 1)
								sample34(i, threadID$i, RNG$1);
					}
				);

		} else {
			if(!fixedFlag$sample34)
				parallelFor(RNG$, 0, size, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i = forStart$i; i < forEnd$i; i += 1)
								sample34(i, threadID$i, RNG$1);
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
			logProbability$var30[i] = 0.0;
		if(!fixedProbFlag$sample34) {
			for(int i = 0; i < size; i += 1)
				logProbability$sample34[i] = 0.0;
		}
		for(int j = 0; j < size; j += 1)
			logProbability$var52[j] = 0.0;
		logProbability$v = 0.0;
		if(!fixedProbFlag$sample56) {
			for(int j = 0; j < size; j += 1)
				logProbability$sample56[j] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample56();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample12();
		logProbabilityDistribution$sample18();
		logProbabilityDistribution$sample34();
		logProbabilityDistribution$sample56();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample12();
		logProbabilityValue$sample18();
		logProbabilityValue$sample34();
		logProbabilityValue$sample56();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample18)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample34)
			parallelFor(RNG$, 0, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, weightings);
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
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel DistributionTest6(double[] weightings, boolean[] value) {\n    int size = value.length;\n    \n    int v1 = categorical(weightings).sampleDistribution();\n    \n    int[] v2 = new int[size + 1];\n    v2[0] = categorical(weightings).sampleDistribution();\n    for(int i:[0..size))\n        v2[i + 1] = categorical(weightings).sampleDistribution();\n        \n    boolean[] v = new boolean[size];\n    for(int j:[0..size))\n        v[j] = bernoulli(((1.0*v1) + v2[j] + v2[j])/v2[j]).sample();\n        \n    v.observe(value);\n}\n";
	}
}