package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DistributionTest1b$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DistributionTest1b$CoreInterface {
	private double[] cv$var6$stateProbabilityGlobal;
	private double[] cv$var8$stateProbabilityGlobal;
	private double[] cv$var9$stateProbabilityGlobal;
	private double[] distribution$sample7;
	private double[] distribution$sample9;
	private boolean fixedFlag$sample10 = false;
	private boolean fixedFlag$sample16 = false;
	private boolean fixedFlag$sample7 = false;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample10 = false;
	private boolean fixedProbFlag$sample16 = false;
	private boolean fixedProbFlag$sample7 = false;
	private boolean fixedProbFlag$sample9 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$c;
	private double logProbability$v;
	private double logProbability$v1;
	private double logProbability$v2;
	private double logProbability$v3;
	private double logProbability$var14;
	private double logProbability$var5;
	private boolean system$gibbsForward = true;
	private boolean v;
	private int v1;
	private int v2;
	private int v3;
	private boolean value;
	private double[] weightings;

	public DistributionTest1b$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$fixedFlag$sample10() {
		return fixedFlag$sample10;
	}

	@Override
	public final void set$fixedFlag$sample10(boolean cv$value) {
		fixedFlag$sample10 = cv$value;
		fixedProbFlag$sample10 = (cv$value && fixedProbFlag$sample10);
		fixedProbFlag$sample16 = (cv$value && fixedProbFlag$sample16);
	}

	@Override
	public final boolean get$fixedFlag$sample16() {
		return fixedFlag$sample16;
	}

	@Override
	public final void set$fixedFlag$sample16(boolean cv$value) {
		fixedFlag$sample16 = cv$value;
		fixedProbFlag$sample16 = (cv$value && fixedProbFlag$sample16);
	}

	@Override
	public final boolean get$fixedFlag$sample7() {
		return fixedFlag$sample7;
	}

	@Override
	public final void set$fixedFlag$sample7(boolean cv$value) {
		fixedFlag$sample7 = cv$value;
		fixedProbFlag$sample7 = (cv$value && fixedProbFlag$sample7);
		fixedProbFlag$sample16 = (cv$value && fixedProbFlag$sample16);
	}

	@Override
	public final boolean get$fixedFlag$sample9() {
		return fixedFlag$sample9;
	}

	@Override
	public final void set$fixedFlag$sample9(boolean cv$value) {
		fixedFlag$sample9 = cv$value;
		fixedProbFlag$sample9 = (cv$value && fixedProbFlag$sample9);
		fixedProbFlag$sample16 = (cv$value && fixedProbFlag$sample16);
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
	public final double get$logProbability$c() {
		return logProbability$c;
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
	public final boolean get$v() {
		return v;
	}

	@Override
	public final void set$v(boolean cv$value) {
		v = cv$value;
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
	public final int get$v2() {
		return v2;
	}

	@Override
	public final void set$v2(int cv$value) {
		v2 = cv$value;
	}

	@Override
	public final int get$v3() {
		return v3;
	}

	@Override
	public final void set$v3(int cv$value) {
		v3 = cv$value;
	}

	@Override
	public final boolean get$value() {
		return value;
	}

	@Override
	public final void set$value(boolean cv$value) {
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

	private final void logProbabilityDistribution$sample16() {
		if(!fixedProbFlag$sample16) {
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			if(fixedFlag$sample7) {
				if(fixedFlag$sample9) {
					cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(v, ((double)v1 / (v2 + v3)));
					cv$probabilityReached = 1.0;
				} else {
					for(int index$sample9$8 = 0; index$sample9$8 < weightings.length; index$sample9$8 += 1) {
						double cv$probabilitySample9Value9 = distribution$sample9[index$sample9$8];
						double cv$weightedProbability = (Math.log(cv$probabilitySample9Value9) + DistributionSampling.logProbabilityBernoulli(v, ((double)v1 / (index$sample9$8 + v3))));
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
								cv$distributionAccumulator = cv$weightedProbability;
							else
								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
						}
						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample9Value9);
					}
				}
			} else {
				for(int index$sample7$3 = 0; index$sample7$3 < weightings.length; index$sample7$3 += 1) {
					double cv$probabilitySample7Value4 = distribution$sample7[index$sample7$3];
					if(fixedFlag$sample9) {
						double cv$weightedProbability = (Math.log(cv$probabilitySample7Value4) + DistributionSampling.logProbabilityBernoulli(v, ((double)index$sample7$3 / (v2 + v3))));
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
								cv$distributionAccumulator = cv$weightedProbability;
							else
								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
						}
						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample7Value4);
					} else {
						for(int index$sample9$13 = 0; index$sample9$13 < weightings.length; index$sample9$13 += 1) {
							double cv$probabilitySample9Value14 = (cv$probabilitySample7Value4 * distribution$sample9[index$sample9$13]);
							double cv$weightedProbability = (Math.log(cv$probabilitySample9Value14) + DistributionSampling.logProbabilityBernoulli(v, ((double)index$sample7$3 / (index$sample9$13 + v3))));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample9Value14);
						}
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			logProbability$var14 = cv$distributionAccumulator;
			logProbability$v = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample16 = (((fixedFlag$sample16 && fixedFlag$sample7) && fixedFlag$sample9) && fixedFlag$sample10);
		} else {
			logProbability$var14 = logProbability$v;
			logProbability$$model = (logProbability$$model + logProbability$v);
			logProbability$$evidence = (logProbability$$evidence + logProbability$v);
		}
	}

	private final void logProbabilityDistribution$sample7() {
		if(!fixedProbFlag$sample7) {
			if(fixedFlag$sample7) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(v1, weightings);
				logProbability$var5 = cv$distributionAccumulator;
				logProbability$v1 = cv$distributionAccumulator;
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample7 = true;
			}
		} else {
			logProbability$var5 = logProbability$v1;
			logProbability$$model = (logProbability$$model + logProbability$v1);
			if(fixedFlag$sample7)
				logProbability$$evidence = (logProbability$$evidence + logProbability$v1);
		}
	}

	private final void logProbabilityDistribution$sample9() {
		if(!fixedProbFlag$sample9) {
			if(fixedFlag$sample9) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(v2, weightings);
				logProbability$c = (logProbability$c + cv$distributionAccumulator);
				logProbability$v2 = cv$distributionAccumulator;
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample9 = true;
			}
		} else {
			logProbability$c = (logProbability$c + logProbability$v2);
			logProbability$$model = (logProbability$$model + logProbability$v2);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + logProbability$v2);
		}
	}

	private final void logProbabilityValue$sample10() {
		if(!fixedProbFlag$sample10) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(v3, weightings);
			logProbability$c = (logProbability$c + cv$distributionAccumulator);
			logProbability$v3 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample10 = fixedFlag$sample10;
		} else {
			logProbability$c = (logProbability$c + logProbability$v3);
			logProbability$$model = (logProbability$$model + logProbability$v3);
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + logProbability$v3);
		}
	}

	private final void logProbabilityValue$sample16() {
		if(!fixedProbFlag$sample16) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(v, ((double)v1 / (v2 + v3)));
			logProbability$var14 = cv$distributionAccumulator;
			logProbability$v = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample16 = (((fixedFlag$sample16 && fixedFlag$sample7) && fixedFlag$sample9) && fixedFlag$sample10);
		} else {
			logProbability$var14 = logProbability$v;
			logProbability$$model = (logProbability$$model + logProbability$v);
			logProbability$$evidence = (logProbability$$evidence + logProbability$v);
		}
	}

	private final void logProbabilityValue$sample7() {
		if(!fixedProbFlag$sample7) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(v1, weightings);
			logProbability$var5 = cv$distributionAccumulator;
			logProbability$v1 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample7)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample7 = fixedFlag$sample7;
		} else {
			logProbability$var5 = logProbability$v1;
			logProbability$$model = (logProbability$$model + logProbability$v1);
			if(fixedFlag$sample7)
				logProbability$$evidence = (logProbability$$evidence + logProbability$v1);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!fixedProbFlag$sample9) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(v2, weightings);
			logProbability$c = (logProbability$c + cv$distributionAccumulator);
			logProbability$v2 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample9 = fixedFlag$sample9;
		} else {
			logProbability$c = (logProbability$c + logProbability$v2);
			logProbability$$model = (logProbability$$model + logProbability$v2);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + logProbability$v2);
		}
	}

	private final void sample10() {
		for(int cv$valuePos = 0; cv$valuePos < weightings.length; cv$valuePos += 1) {
			v3 = cv$valuePos;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityCategorical(cv$valuePos, weightings);
			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
			double cv$consumerDistributionProbabilityAccumulator = 1.0;
			if(fixedFlag$sample7) {
				if(fixedFlag$sample9) {
					cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v, ((double)v1 / (v2 + cv$valuePos)));
					cv$consumerDistributionProbabilityAccumulator = 0.0;
				} else {
					for(int index$sample9$9 = 0; index$sample9$9 < weightings.length; index$sample9$9 += 1) {
						double cv$probabilitySample9Value10 = distribution$sample9[index$sample9$9];
						double cv$temp$2$var13 = ((double)v1 / (index$sample9$9 + cv$valuePos));
						if(((Math.log(cv$probabilitySample9Value10) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$2$var13)) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample9Value10) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$2$var13)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample9Value10) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$2$var13));
							else
								cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample9Value10) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$2$var13)))) + 1)) + Math.log(cv$probabilitySample9Value10)) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$2$var13));
						}
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample9Value10);
					}
				}
			} else {
				for(int index$sample7$4 = 0; index$sample7$4 < weightings.length; index$sample7$4 += 1) {
					double cv$probabilitySample7Value5 = distribution$sample7[index$sample7$4];
					if(fixedFlag$sample9) {
						double cv$temp$3$var13 = ((double)index$sample7$4 / (v2 + cv$valuePos));
						if(((Math.log(cv$probabilitySample7Value5) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$3$var13)) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample7Value5) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$3$var13)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample7Value5) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$3$var13));
							else
								cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample7Value5) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$3$var13)))) + 1)) + Math.log(cv$probabilitySample7Value5)) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$3$var13));
						}
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample7Value5);
					} else {
						for(int index$sample9$14 = 0; index$sample9$14 < weightings.length; index$sample9$14 += 1) {
							double cv$probabilitySample9Value15 = (cv$probabilitySample7Value5 * distribution$sample9[index$sample9$14]);
							double cv$temp$4$var13 = ((double)index$sample7$4 / (index$sample9$14 + cv$valuePos));
							if(((Math.log(cv$probabilitySample9Value15) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$4$var13)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample9Value15) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$4$var13)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample9Value15) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$4$var13));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample9Value15) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$4$var13)))) + 1)) + Math.log(cv$probabilitySample9Value15)) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$4$var13));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample9Value15);
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
				cv$var9$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$var9$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var9$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var9$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var9$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		v3 = DistributionSampling.sampleCategorical(RNG$, cv$var9$stateProbabilityGlobal);
	}

	private final void sample7() {
		for(int cv$valuePos = 0; cv$valuePos < weightings.length; cv$valuePos += 1) {
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityCategorical(cv$valuePos, weightings);
			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
			double cv$consumerDistributionProbabilityAccumulator = 1.0;
			if(fixedFlag$sample9) {
				cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v, ((double)cv$valuePos / (v2 + v3)));
				cv$consumerDistributionProbabilityAccumulator = 0.0;
			} else {
				for(int index$sample9$4 = 0; index$sample9$4 < weightings.length; index$sample9$4 += 1) {
					double cv$probabilitySample9Value5 = distribution$sample9[index$sample9$4];
					double cv$temp$2$var13 = ((double)cv$valuePos / (index$sample9$4 + v3));
					if(((Math.log(cv$probabilitySample9Value5) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$2$var13)) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample9Value5) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$2$var13)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample9Value5) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$2$var13));
						else
							cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample9Value5) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$2$var13)))) + 1)) + Math.log(cv$probabilitySample9Value5)) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$2$var13));
					}
					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample9Value5);
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
			cv$var6$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double cv$logSum;
		double cv$lseMax = cv$var6$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var6$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var6$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var6$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var6$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var6$stateProbabilityGlobal.length; cv$indexName += 1)
				distribution$sample7[cv$indexName] = (1.0 / cv$var6$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var6$stateProbabilityGlobal.length; cv$indexName += 1)
				distribution$sample7[cv$indexName] = Math.exp((cv$var6$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
	}

	private final void sample9() {
		for(int cv$valuePos = 0; cv$valuePos < weightings.length; cv$valuePos += 1) {
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityCategorical(cv$valuePos, weightings);
			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
			double cv$consumerDistributionProbabilityAccumulator = 1.0;
			if(fixedFlag$sample7) {
				cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(v, ((double)v1 / (cv$valuePos + v3)));
				cv$consumerDistributionProbabilityAccumulator = 0.0;
			} else {
				for(int index$sample7$4 = 0; index$sample7$4 < weightings.length; index$sample7$4 += 1) {
					double cv$probabilitySample7Value5 = distribution$sample7[index$sample7$4];
					double cv$temp$2$var13 = ((double)index$sample7$4 / (cv$valuePos + v3));
					if(((Math.log(cv$probabilitySample7Value5) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$2$var13)) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample7Value5) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$2$var13)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample7Value5) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$2$var13));
						else
							cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample7Value5) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$2$var13)))) + 1)) + Math.log(cv$probabilitySample7Value5)) + DistributionSampling.logProbabilityBernoulli(v, cv$temp$2$var13));
					}
					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample7Value5);
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
			cv$var8$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double cv$logSum;
		double cv$lseMax = cv$var8$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var8$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var8$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var8$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var8$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var8$stateProbabilityGlobal.length; cv$indexName += 1)
				distribution$sample9[cv$indexName] = (1.0 / cv$var8$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var8$stateProbabilityGlobal.length; cv$indexName += 1)
				distribution$sample9[cv$indexName] = Math.exp((cv$var8$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
	}

	@Override
	public final void allocateScratch() {
		cv$var6$stateProbabilityGlobal = new double[weightings.length];
		cv$var8$stateProbabilityGlobal = new double[weightings.length];
		cv$var9$stateProbabilityGlobal = new double[weightings.length];
	}

	@Override
	public final void allocator() {
		distribution$sample7 = new double[weightings.length];
		distribution$sample9 = new double[weightings.length];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample7)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample9)
			v2 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample10)
			v3 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample16)
			v = DistributionSampling.sampleBernoulli(RNG$, ((double)v1 / (v2 + v3)));
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample7) {
			for(int index$var5 = 0; index$var5 < weightings.length; index$var5 += 1)
				distribution$sample7[index$var5] = DistributionSampling.probabilityCategorical(index$var5, weightings);
		}
		if((!fixedFlag$sample9 || !fixedFlag$sample10)) {
			for(int index$c = 0; index$c < weightings.length; index$c += 1)
				distribution$sample9[index$c] = DistributionSampling.probabilityCategorical(index$c, weightings);
		}
		if(!fixedFlag$sample10)
			v3 = DistributionSampling.sampleCategorical(RNG$, weightings);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample7)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample9)
			v2 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample10)
			v3 = DistributionSampling.sampleCategorical(RNG$, weightings);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample7)
				sample7();
			if(!fixedFlag$sample9)
				sample9();
			if(!fixedFlag$sample10)
				sample10();
		} else {
			if(!fixedFlag$sample10)
				sample10();
			if(!fixedFlag$sample9)
				sample9();
			if(!fixedFlag$sample7)
				sample7();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var5 = 0.0;
		if(!fixedProbFlag$sample7)
			logProbability$v1 = 0.0;
		logProbability$c = 0.0;
		if(!fixedProbFlag$sample9)
			logProbability$v2 = 0.0;
		if(!fixedProbFlag$sample10)
			logProbability$v3 = 0.0;
		logProbability$var14 = 0.0;
		if(!fixedProbFlag$sample16)
			logProbability$v = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample10)
			logProbabilityValue$sample10();
		logProbabilityValue$sample16();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample7();
		logProbabilityDistribution$sample9();
		logProbabilityValue$sample10();
		logProbabilityDistribution$sample16();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample7();
		logProbabilityValue$sample9();
		logProbabilityValue$sample10();
		logProbabilityValue$sample16();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample7)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample9)
			v2 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample10)
			v3 = DistributionSampling.sampleCategorical(RNG$, weightings);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		v = value;
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel DistributionTest1b(double[] weightings, boolean value) {\n    int v1 = categorical(weightings).sampleDistribution();\n    Categorical c = new Categorical(weightings);\n    int v2 = c.sampleDistribution();\n    int v3 = c.sample();\n    boolean v = bernoulli((1.0*v1)/(v2 + v3)).sample();\n    v.observe(value);\n}\n";
	}
}