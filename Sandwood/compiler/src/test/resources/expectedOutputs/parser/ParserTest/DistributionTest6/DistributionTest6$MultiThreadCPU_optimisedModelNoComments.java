package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DistributionTest6$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DistributionTest6$CoreInterface {
	private double[] cv$var11$stateProbabilityGlobal;
	private double[][] cv$var27$stateProbabilityGlobal;
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
	private boolean[][] guard$sample27bernoulli48$global;
	private int length$value;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$sample27;
	private double[] logProbability$sample49;
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

	public DistributionTest6$MultiThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
	}

	@Override
	public final boolean get$fixedFlag$sample27() {
		return fixedFlag$sample27;
	}

	@Override
	public final void set$fixedFlag$sample27(boolean cv$value) {
		fixedFlag$sample27 = cv$value;
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
	}

	@Override
	public final boolean get$fixedFlag$sample5() {
		return fixedFlag$sample5;
	}

	@Override
	public final void set$fixedFlag$sample5(boolean cv$value) {
		fixedFlag$sample5 = cv$value;
		fixedProbFlag$sample5 = (cv$value && fixedProbFlag$sample5);
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
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
				int cv$sampleValue = v2[0];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				logProbability$var11 = cv$distributionAccumulator;
				logProbability$v2 = (logProbability$v2 + cv$distributionAccumulator);
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample11 = true;
			}
		} else {
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
					logProbability$sample27[i] = cv$distributionAccumulator;
				}
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample27 = true;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample27[i]);
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
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				boolean cv$sampleValue = v[j];
				if((0 == j)) {
					if(fixedFlag$sample5) {
						if(fixedFlag$sample11) {
							double var47 = ((double)((v1 + v2[0]) + v2[0]) / v2[0]);
							cv$distributionAccumulator = Math.log((cv$sampleValue?var47:(1.0 - var47)));
							cv$probabilityReached = 1.0;
						} else {
							for(int index$sample11$8 = 0; index$sample11$8 < weightings.length; index$sample11$8 += 1) {
								double cv$probabilitySample11Value9 = distribution$sample11[index$sample11$8];
								double var47 = ((double)((v1 + index$sample11$8) + index$sample11$8) / index$sample11$8);
								double cv$weightedProbability = (Math.log(cv$probabilitySample11Value9) + Math.log((cv$sampleValue?var47:(1.0 - var47))));
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
					} else {
						for(int index$sample5$3 = 0; index$sample5$3 < weightings.length; index$sample5$3 += 1) {
							double cv$probabilitySample5Value4 = distribution$sample5[index$sample5$3];
							if(fixedFlag$sample11) {
								double var47 = ((double)((index$sample5$3 + v2[0]) + v2[0]) / v2[0]);
								double cv$weightedProbability = (Math.log(cv$probabilitySample5Value4) + Math.log((cv$sampleValue?var47:(1.0 - var47))));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value4);
							} else {
								for(int index$sample11$13 = 0; index$sample11$13 < weightings.length; index$sample11$13 += 1) {
									double cv$probabilitySample11Value14 = (cv$probabilitySample5Value4 * distribution$sample11[index$sample11$13]);
									double var47 = ((double)((index$sample5$3 + index$sample11$13) + index$sample11$13) / index$sample11$13);
									double cv$weightedProbability = (Math.log(cv$probabilitySample11Value14) + Math.log((cv$sampleValue?var47:(1.0 - var47))));
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
					}
				}
				if(fixedFlag$sample5) {
					if(fixedFlag$sample27) {
						if((1 <= j)) {
							double var47 = ((double)((v1 + v2[j]) + v2[j]) / v2[j]);
							double cv$weightedProbability = Math.log((cv$sampleValue?var47:(1.0 - var47)));
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
								double var47 = ((double)((v1 + index$sample27$440) + index$sample27$440) / index$sample27$440);
								double cv$weightedProbability = (Math.log(cv$probabilitySample27Value441) + Math.log((cv$sampleValue?var47:(1.0 - var47))));
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
				} else {
					for(int index$sample5$434 = 0; index$sample5$434 < weightings.length; index$sample5$434 += 1) {
						double cv$probabilitySample5Value435 = distribution$sample5[index$sample5$434];
						if(fixedFlag$sample27) {
							if((1 <= j)) {
								double var47 = ((double)((index$sample5$434 + v2[j]) + v2[j]) / v2[j]);
								double cv$weightedProbability = (Math.log(cv$probabilitySample5Value435) + Math.log((cv$sampleValue?var47:(1.0 - var47))));
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
									double var47 = ((double)((index$sample5$434 + index$sample27$446) + index$sample27$446) / index$sample27$446);
									double cv$weightedProbability = (Math.log(cv$probabilitySample27Value447) + Math.log((cv$sampleValue?var47:(1.0 - var47))));
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
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample49[j] = cv$distributionAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample49 = ((fixedFlag$sample5 && fixedFlag$sample11) && fixedFlag$sample27);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample49[j]);
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample5() {
		if(!fixedProbFlag$sample5) {
			if(fixedFlag$sample5) {
				double cv$distributionAccumulator = (((0.0 <= v1) && (v1 < weightings.length))?Math.log(weightings[v1]):Double.NEGATIVE_INFINITY);
				logProbability$v1 = cv$distributionAccumulator;
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample5 = true;
			}
		} else {
			logProbability$$model = (logProbability$$model + logProbability$v1);
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence + logProbability$v1);
		}
	}

	private final void logProbabilityValue$sample11() {
		if(!fixedProbFlag$sample11) {
			int cv$sampleValue = v2[0];
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var11 = cv$distributionAccumulator;
			logProbability$v2 = (logProbability$v2 + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample11)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample11 = fixedFlag$sample11;
		} else {
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
				logProbability$sample27[i] = cv$distributionAccumulator;
			}
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample27 = fixedFlag$sample27;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < size; i += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample27[i]);
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
				double var47 = ((double)((v1 + v2[j]) + v2[j]) / v2[j]);
				double cv$distributionAccumulator = Math.log((v[j]?var47:(1.0 - var47)));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample49[j] = cv$distributionAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample49 = ((fixedFlag$sample5 && fixedFlag$sample11) && fixedFlag$sample27);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample49[j]);
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample5() {
		if(!fixedProbFlag$sample5) {
			double cv$distributionAccumulator = (((0.0 <= v1) && (v1 < weightings.length))?Math.log(weightings[v1]):Double.NEGATIVE_INFINITY);
			logProbability$v1 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample5 = fixedFlag$sample5;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$v1);
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence + logProbability$v1);
		}
	}

	private final void sample11() {
		int cv$numStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$accumulatedProbabilities = Math.log(weightings[cv$valuePos]);
			if((0 < size)) {
				guard$sample11bernoulli48$global[0] = false;
				if(!guard$sample11bernoulli48$global[0]) {
					guard$sample11bernoulli48$global[0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(fixedFlag$sample5) {
						double var47 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
						cv$accumulatedConsumerProbabilities = Math.log((v[0]?var47:(1.0 - var47)));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						for(int index$sample5$11 = 0; index$sample5$11 < weightings.length; index$sample5$11 += 1) {
							double cv$probabilitySample5Value12 = distribution$sample5[index$sample5$11];
							double var47 = ((double)((index$sample5$11 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample5Value12) + Math.log((v[0]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value12) + Math.log((v[0]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value12) + Math.log((v[0]?var47:(1.0 - var47))));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value12) + Math.log((v[0]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample5Value12)) + Math.log((v[0]?var47:(1.0 - var47))));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value12);
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
				if(!guard$sample11bernoulli48$global[0]) {
					guard$sample11bernoulli48$global[0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(fixedFlag$sample5) {
						double var47 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
						cv$accumulatedConsumerProbabilities = Math.log((v[0]?var47:(1.0 - var47)));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						for(int index$sample5$155 = 0; index$sample5$155 < weightings.length; index$sample5$155 += 1) {
							double cv$probabilitySample5Value156 = distribution$sample5[index$sample5$155];
							double var47 = ((double)((index$sample5$155 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample5Value156) + Math.log((v[0]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value156) + Math.log((v[0]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value156) + Math.log((v[0]?var47:(1.0 - var47))));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value156) + Math.log((v[0]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample5Value156)) + Math.log((v[0]?var47:(1.0 - var47))));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value156);
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
				if(!guard$sample11bernoulli48$global[0]) {
					guard$sample11bernoulli48$global[0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(fixedFlag$sample5) {
						double var47 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
						cv$accumulatedConsumerProbabilities = Math.log((v[0]?var47:(1.0 - var47)));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						for(int index$sample5$299 = 0; index$sample5$299 < weightings.length; index$sample5$299 += 1) {
							double cv$probabilitySample5Value300 = distribution$sample5[index$sample5$299];
							double var47 = ((double)((index$sample5$299 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample5Value300) + Math.log((v[0]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value300) + Math.log((v[0]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value300) + Math.log((v[0]?var47:(1.0 - var47))));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value300) + Math.log((v[0]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample5Value300)) + Math.log((v[0]?var47:(1.0 - var47))));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value300);
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
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var11$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var11$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
				distribution$sample11[cv$indexName] = (1.0 / cv$numStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
				distribution$sample11[cv$indexName] = Math.exp((cv$var11$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numStates; cv$indexName < cv$var11$stateProbabilityGlobal.length; cv$indexName += 1)
			distribution$sample11[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample27(int i, int threadID$cv$i, Rng RNG$) {
		int cv$numStates = weightings.length;
		double[] cv$stateProbabilityLocal = cv$var27$stateProbabilityGlobal[threadID$cv$i];
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$accumulatedProbabilities = Math.log(weightings[cv$valuePos]);
			boolean[] guard$sample27bernoulli48 = guard$sample27bernoulli48$global[threadID$cv$i];
			{
				int j = (i + 1);
				if((j < size))
					guard$sample27bernoulli48[j] = false;
			}
			{
				int j = (i + 1);
				if((j < size))
					guard$sample27bernoulli48[j] = false;
			}
			{
				int j = (i + 1);
				if((j < size))
					guard$sample27bernoulli48[j] = false;
			}
			{
				int j = (i + 1);
				if(((j < size) && !guard$sample27bernoulli48[j])) {
					guard$sample27bernoulli48[j] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(fixedFlag$sample5) {
						{
							double var47 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							cv$accumulatedConsumerProbabilities = Math.log((v[j]?var47:(1.0 - var47)));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						int index$i$132 = (j - 1);
						if(!(index$i$132 == i)) {
							for(int index$sample27$133 = 0; index$sample27$133 < weightings.length; index$sample27$133 += 1) {
								double cv$probabilitySample27Value134 = distribution$sample27[index$i$132][index$sample27$133];
								double var47 = ((double)((v1 + cv$valuePos) + cv$valuePos) / index$sample27$133);
								if(((Math.log(cv$probabilitySample27Value134) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value134) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value134) + Math.log((v[j]?var47:(1.0 - var47))));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value134) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample27Value134)) + Math.log((v[j]?var47:(1.0 - var47))));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value134);
							}
						}
						int index$i$120 = (j - 1);
						if(!(index$i$120 == i)) {
							for(int index$sample27$121 = 0; index$sample27$121 < weightings.length; index$sample27$121 += 1) {
								double cv$probabilitySample27Value122 = distribution$sample27[index$i$120][index$sample27$121];
								{
									double var47 = ((double)((v1 + cv$valuePos) + index$sample27$121) / cv$valuePos);
									if(((Math.log(cv$probabilitySample27Value122) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value122) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value122) + Math.log((v[j]?var47:(1.0 - var47))));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value122) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample27Value122)) + Math.log((v[j]?var47:(1.0 - var47))));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value122);
								}
								double var47 = ((double)((v1 + cv$valuePos) + index$sample27$121) / index$sample27$121);
								if(((Math.log(cv$probabilitySample27Value122) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value122) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value122) + Math.log((v[j]?var47:(1.0 - var47))));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value122) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample27Value122)) + Math.log((v[j]?var47:(1.0 - var47))));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value122);
							}
						}
					} else {
						for(int index$sample5$115 = 0; index$sample5$115 < weightings.length; index$sample5$115 += 1) {
							double cv$probabilitySample5Value116 = distribution$sample5[index$sample5$115];
							{
								double var47 = ((double)((index$sample5$115 + cv$valuePos) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample5Value116) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value116) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value116) + Math.log((v[j]?var47:(1.0 - var47))));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value116) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample5Value116)) + Math.log((v[j]?var47:(1.0 - var47))));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value116);
							}
							int index$i$145 = (j - 1);
							if(!(index$i$145 == i)) {
								for(int index$sample27$146 = 0; index$sample27$146 < weightings.length; index$sample27$146 += 1) {
									double cv$probabilitySample27Value147 = (cv$probabilitySample5Value116 * distribution$sample27[index$i$145][index$sample27$146]);
									double var47 = ((double)((index$sample5$115 + cv$valuePos) + cv$valuePos) / index$sample27$146);
									if(((Math.log(cv$probabilitySample27Value147) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value147) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value147) + Math.log((v[j]?var47:(1.0 - var47))));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value147) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample27Value147)) + Math.log((v[j]?var47:(1.0 - var47))));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value147);
								}
							}
							int index$i$126 = (j - 1);
							if(!(index$i$126 == i)) {
								for(int index$sample27$127 = 0; index$sample27$127 < weightings.length; index$sample27$127 += 1) {
									double cv$probabilitySample27Value128 = (cv$probabilitySample5Value116 * distribution$sample27[index$i$126][index$sample27$127]);
									{
										double var47 = ((double)((index$sample5$115 + cv$valuePos) + index$sample27$127) / cv$valuePos);
										if(((Math.log(cv$probabilitySample27Value128) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value128) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value128) + Math.log((v[j]?var47:(1.0 - var47))));
											else
												cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value128) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample27Value128)) + Math.log((v[j]?var47:(1.0 - var47))));
										}
										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value128);
									}
									double var47 = ((double)((index$sample5$115 + cv$valuePos) + index$sample27$127) / index$sample27$127);
									if(((Math.log(cv$probabilitySample27Value128) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value128) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value128) + Math.log((v[j]?var47:(1.0 - var47))));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value128) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample27Value128)) + Math.log((v[j]?var47:(1.0 - var47))));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value128);
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
				if(((j < size) && !guard$sample27bernoulli48[j])) {
					guard$sample27bernoulli48[j] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(fixedFlag$sample5) {
						{
							double var47 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							cv$accumulatedConsumerProbabilities = Math.log((v[j]?var47:(1.0 - var47)));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						int index$i$278 = (j - 1);
						if(!(index$i$278 == i)) {
							for(int index$sample27$279 = 0; index$sample27$279 < weightings.length; index$sample27$279 += 1) {
								double cv$probabilitySample27Value280 = distribution$sample27[index$i$278][index$sample27$279];
								double var47 = ((double)((v1 + cv$valuePos) + cv$valuePos) / index$sample27$279);
								if(((Math.log(cv$probabilitySample27Value280) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value280) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value280) + Math.log((v[j]?var47:(1.0 - var47))));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value280) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample27Value280)) + Math.log((v[j]?var47:(1.0 - var47))));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value280);
							}
						}
						int index$i$266 = (j - 1);
						if(!(index$i$266 == i)) {
							for(int index$sample27$267 = 0; index$sample27$267 < weightings.length; index$sample27$267 += 1) {
								double cv$probabilitySample27Value268 = distribution$sample27[index$i$266][index$sample27$267];
								{
									double var47 = ((double)((v1 + index$sample27$267) + cv$valuePos) / cv$valuePos);
									if(((Math.log(cv$probabilitySample27Value268) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value268) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value268) + Math.log((v[j]?var47:(1.0 - var47))));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value268) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample27Value268)) + Math.log((v[j]?var47:(1.0 - var47))));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value268);
								}
								double var47 = ((double)((v1 + index$sample27$267) + cv$valuePos) / index$sample27$267);
								if(((Math.log(cv$probabilitySample27Value268) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value268) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value268) + Math.log((v[j]?var47:(1.0 - var47))));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value268) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample27Value268)) + Math.log((v[j]?var47:(1.0 - var47))));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value268);
							}
						}
					} else {
						for(int index$sample5$261 = 0; index$sample5$261 < weightings.length; index$sample5$261 += 1) {
							double cv$probabilitySample5Value262 = distribution$sample5[index$sample5$261];
							{
								double var47 = ((double)((index$sample5$261 + cv$valuePos) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample5Value262) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value262) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value262) + Math.log((v[j]?var47:(1.0 - var47))));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value262) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample5Value262)) + Math.log((v[j]?var47:(1.0 - var47))));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value262);
							}
							int index$i$291 = (j - 1);
							if(!(index$i$291 == i)) {
								for(int index$sample27$292 = 0; index$sample27$292 < weightings.length; index$sample27$292 += 1) {
									double cv$probabilitySample27Value293 = (cv$probabilitySample5Value262 * distribution$sample27[index$i$291][index$sample27$292]);
									double var47 = ((double)((index$sample5$261 + cv$valuePos) + cv$valuePos) / index$sample27$292);
									if(((Math.log(cv$probabilitySample27Value293) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value293) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value293) + Math.log((v[j]?var47:(1.0 - var47))));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value293) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample27Value293)) + Math.log((v[j]?var47:(1.0 - var47))));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value293);
								}
							}
							int index$i$272 = (j - 1);
							if(!(index$i$272 == i)) {
								for(int index$sample27$273 = 0; index$sample27$273 < weightings.length; index$sample27$273 += 1) {
									double cv$probabilitySample27Value274 = (cv$probabilitySample5Value262 * distribution$sample27[index$i$272][index$sample27$273]);
									{
										double var47 = ((double)((index$sample5$261 + index$sample27$273) + cv$valuePos) / cv$valuePos);
										if(((Math.log(cv$probabilitySample27Value274) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value274) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value274) + Math.log((v[j]?var47:(1.0 - var47))));
											else
												cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value274) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample27Value274)) + Math.log((v[j]?var47:(1.0 - var47))));
										}
										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value274);
									}
									double var47 = ((double)((index$sample5$261 + index$sample27$273) + cv$valuePos) / index$sample27$273);
									if(((Math.log(cv$probabilitySample27Value274) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value274) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value274) + Math.log((v[j]?var47:(1.0 - var47))));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value274) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample27Value274)) + Math.log((v[j]?var47:(1.0 - var47))));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value274);
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
			if(((j < size) && !guard$sample27bernoulli48[j])) {
				guard$sample27bernoulli48[j] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if(fixedFlag$sample5) {
					{
						double var47 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
						cv$accumulatedConsumerProbabilities = Math.log((v[j]?var47:(1.0 - var47)));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					int index$i$424 = (j - 1);
					if(!(index$i$424 == i)) {
						for(int index$sample27$425 = 0; index$sample27$425 < weightings.length; index$sample27$425 += 1) {
							double cv$probabilitySample27Value426 = distribution$sample27[index$i$424][index$sample27$425];
							double var47 = ((double)((v1 + cv$valuePos) + index$sample27$425) / cv$valuePos);
							if(((Math.log(cv$probabilitySample27Value426) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value426) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value426) + Math.log((v[j]?var47:(1.0 - var47))));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value426) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample27Value426)) + Math.log((v[j]?var47:(1.0 - var47))));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value426);
						}
					}
					int index$i$412 = (j - 1);
					if(!(index$i$412 == i)) {
						for(int index$sample27$413 = 0; index$sample27$413 < weightings.length; index$sample27$413 += 1) {
							double cv$probabilitySample27Value414 = distribution$sample27[index$i$412][index$sample27$413];
							{
								double var47 = ((double)((v1 + index$sample27$413) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample27Value414) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value414) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value414) + Math.log((v[j]?var47:(1.0 - var47))));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value414) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample27Value414)) + Math.log((v[j]?var47:(1.0 - var47))));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value414);
							}
							double var47 = ((double)((v1 + index$sample27$413) + index$sample27$413) / cv$valuePos);
							if(((Math.log(cv$probabilitySample27Value414) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value414) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value414) + Math.log((v[j]?var47:(1.0 - var47))));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value414) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample27Value414)) + Math.log((v[j]?var47:(1.0 - var47))));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value414);
						}
					}
				} else {
					for(int index$sample5$407 = 0; index$sample5$407 < weightings.length; index$sample5$407 += 1) {
						double cv$probabilitySample5Value408 = distribution$sample5[index$sample5$407];
						{
							double var47 = ((double)((index$sample5$407 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample5Value408) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value408) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value408) + Math.log((v[j]?var47:(1.0 - var47))));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value408) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample5Value408)) + Math.log((v[j]?var47:(1.0 - var47))));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value408);
						}
						int index$i$437 = (j - 1);
						if(!(index$i$437 == i)) {
							for(int index$sample27$438 = 0; index$sample27$438 < weightings.length; index$sample27$438 += 1) {
								double cv$probabilitySample27Value439 = (cv$probabilitySample5Value408 * distribution$sample27[index$i$437][index$sample27$438]);
								double var47 = ((double)((index$sample5$407 + cv$valuePos) + index$sample27$438) / cv$valuePos);
								if(((Math.log(cv$probabilitySample27Value439) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value439) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value439) + Math.log((v[j]?var47:(1.0 - var47))));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value439) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample27Value439)) + Math.log((v[j]?var47:(1.0 - var47))));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value439);
							}
						}
						int index$i$418 = (j - 1);
						if(!(index$i$418 == i)) {
							for(int index$sample27$419 = 0; index$sample27$419 < weightings.length; index$sample27$419 += 1) {
								double cv$probabilitySample27Value420 = (cv$probabilitySample5Value408 * distribution$sample27[index$i$418][index$sample27$419]);
								{
									double var47 = ((double)((index$sample5$407 + index$sample27$419) + cv$valuePos) / cv$valuePos);
									if(((Math.log(cv$probabilitySample27Value420) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value420) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value420) + Math.log((v[j]?var47:(1.0 - var47))));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value420) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample27Value420)) + Math.log((v[j]?var47:(1.0 - var47))));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value420);
								}
								double var47 = ((double)((index$sample5$407 + index$sample27$419) + index$sample27$419) / cv$valuePos);
								if(((Math.log(cv$probabilitySample27Value420) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value420) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value420) + Math.log((v[j]?var47:(1.0 - var47))));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value420) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample27Value420)) + Math.log((v[j]?var47:(1.0 - var47))));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value420);
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
		double[] cv$localProbability = distribution$sample27[i];
		double cv$logSum;
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
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
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

	private final void sample5() {
		int cv$numStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$accumulatedProbabilities = Math.log(weightings[cv$valuePos]);
			for(int j = 0; j < size; j += 1) {
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((0 == j)) {
					if(fixedFlag$sample11) {
						double var47 = ((double)((cv$valuePos + v2[0]) + v2[0]) / v2[0]);
						cv$accumulatedConsumerProbabilities = Math.log((v[0]?var47:(1.0 - var47)));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						for(int index$sample11$4 = 0; index$sample11$4 < weightings.length; index$sample11$4 += 1) {
							double cv$probabilitySample11Value5 = distribution$sample11[index$sample11$4];
							double var47 = ((double)((cv$valuePos + index$sample11$4) + index$sample11$4) / index$sample11$4);
							if(((Math.log(cv$probabilitySample11Value5) + Math.log((v[0]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value5) + Math.log((v[0]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value5) + Math.log((v[0]?var47:(1.0 - var47))));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value5) + Math.log((v[0]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample11Value5)) + Math.log((v[0]?var47:(1.0 - var47))));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value5);
						}
					}
				}
				if(fixedFlag$sample27) {
					if((1 <= j)) {
						double var47 = ((double)((cv$valuePos + v2[j]) + v2[j]) / v2[j]);
						if((Math.log((v[j]?var47:(1.0 - var47))) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((Math.log((v[j]?var47:(1.0 - var47))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = Math.log((v[j]?var47:(1.0 - var47)));
							else
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log((v[j]?var47:(1.0 - var47))))) + 1)) + Math.log((v[j]?var47:(1.0 - var47))));
						}
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
					}
				} else {
					int i = (j - 1);
					if((0 <= i)) {
						for(int index$sample27$203 = 0; index$sample27$203 < weightings.length; index$sample27$203 += 1) {
							double cv$probabilitySample27Value204 = distribution$sample27[i][index$sample27$203];
							double var47 = ((double)((cv$valuePos + index$sample27$203) + index$sample27$203) / index$sample27$203);
							if(((Math.log(cv$probabilitySample27Value204) + Math.log((v[j]?var47:(1.0 - var47)))) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value204) + Math.log((v[j]?var47:(1.0 - var47)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value204) + Math.log((v[j]?var47:(1.0 - var47))));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value204) + Math.log((v[j]?var47:(1.0 - var47)))))) + 1)) + Math.log(cv$probabilitySample27Value204)) + Math.log((v[j]?var47:(1.0 - var47))));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value204);
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
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var5$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var5$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
				distribution$sample5[cv$indexName] = (1.0 / cv$numStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
				distribution$sample5[cv$indexName] = Math.exp((cv$var5$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numStates; cv$indexName < cv$var5$stateProbabilityGlobal.length; cv$indexName += 1)
			distribution$sample5[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	@Override
	public final void allocateScratch() {
		cv$var5$stateProbabilityGlobal = new double[weightings.length];
		cv$var11$stateProbabilityGlobal = new double[weightings.length];
		guard$sample11bernoulli48$global = new boolean[length$value];
		{
			int cv$threadCount = threadCount();
			cv$var27$stateProbabilityGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var27$stateProbabilityGlobal[cv$index] = new double[weightings.length];
		}
		int cv$threadCount = threadCount();
		guard$sample27bernoulli48$global = new boolean[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			guard$sample27bernoulli48$global[cv$index] = new boolean[length$value];
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
		logProbability$sample27 = new double[length$value];
		logProbability$sample49 = new double[length$value];
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
						v[j] = DistributionSampling.sampleBernoulli(RNG$1, ((double)((v1 + v2[j]) + v2[j]) / v2[j]));
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
						v[j] = DistributionSampling.sampleBernoulli(RNG$1, ((double)((v1 + v2[j]) + v2[j]) / v2[j]));
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
			if(!fixedFlag$sample27)
				parallelFor(RNG$, 0, size, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i = forStart$i; i < forEnd$i; i += 1)
								sample27(i, threadID$i, RNG$1);
					}
				);

		} else {
			if(!fixedFlag$sample27)
				parallelFor(RNG$, 0, size, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i = forStart$i; i < forEnd$i; i += 1)
								sample27(i, threadID$i, RNG$1);
					}
				);

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
				logProbability$sample27[i] = Double.NaN;
		}
		logProbability$v = 0.0;
		if(!fixedProbFlag$sample49) {
			for(int j = 0; j < size; j += 1)
				logProbability$sample49[j] = Double.NaN;
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
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