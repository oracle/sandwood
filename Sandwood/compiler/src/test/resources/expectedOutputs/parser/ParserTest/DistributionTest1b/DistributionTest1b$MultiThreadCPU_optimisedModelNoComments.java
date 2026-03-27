package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DistributionTest1b$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DistributionTest1b$CoreInterface {
	private boolean constrainedFlag$sample4 = true;
	private boolean constrainedFlag$sample6 = true;
	private boolean constrainedFlag$sample7 = true;
	private double[] cv$var4$stateProbabilityGlobal;
	private double[] cv$var6$stateProbabilityGlobal;
	private double[] cv$var7$stateProbabilityGlobal;
	private double[] distribution$sample4;
	private double[] distribution$sample6;
	private boolean fixedFlag$sample4 = false;
	private boolean fixedFlag$sample6 = false;
	private boolean fixedFlag$sample7 = false;
	private boolean fixedProbFlag$sample13 = false;
	private boolean fixedProbFlag$sample4 = false;
	private boolean fixedProbFlag$sample6 = false;
	private boolean fixedProbFlag$sample7 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$c;
	private double logProbability$v;
	private double logProbability$v1;
	private double logProbability$v2;
	private double logProbability$v3;
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
	public final double[] get$distribution$sample4() {
		return distribution$sample4;
	}

	@Override
	public final void set$distribution$sample4(double[] cv$value, boolean allocated$) {
		distribution$sample4 = cv$value;
	}

	@Override
	public final double[] get$distribution$sample6() {
		return distribution$sample6;
	}

	@Override
	public final void set$distribution$sample6(double[] cv$value, boolean allocated$) {
		distribution$sample6 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample4() {
		return fixedFlag$sample4;
	}

	@Override
	public final void set$fixedFlag$sample4(boolean cv$value, boolean allocated$) {
		fixedFlag$sample4 = cv$value;
		constrainedFlag$sample4 = (cv$value || constrainedFlag$sample4);
		fixedProbFlag$sample4 = (cv$value && fixedProbFlag$sample4);
		fixedProbFlag$sample13 = (cv$value && fixedProbFlag$sample13);
	}

	@Override
	public final boolean get$fixedFlag$sample6() {
		return fixedFlag$sample6;
	}

	@Override
	public final void set$fixedFlag$sample6(boolean cv$value, boolean allocated$) {
		fixedFlag$sample6 = cv$value;
		constrainedFlag$sample6 = (cv$value || constrainedFlag$sample6);
		fixedProbFlag$sample6 = (cv$value && fixedProbFlag$sample6);
		fixedProbFlag$sample13 = (cv$value && fixedProbFlag$sample13);
	}

	@Override
	public final boolean get$fixedFlag$sample7() {
		return fixedFlag$sample7;
	}

	@Override
	public final void set$fixedFlag$sample7(boolean cv$value, boolean allocated$) {
		fixedFlag$sample7 = cv$value;
		constrainedFlag$sample7 = (cv$value || constrainedFlag$sample7);
		fixedProbFlag$sample7 = (cv$value && fixedProbFlag$sample7);
		fixedProbFlag$sample13 = (cv$value && fixedProbFlag$sample13);
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
	public final int get$v1() {
		return v1;
	}

	@Override
	public final void set$v1(int cv$value, boolean allocated$) {
		v1 = cv$value;
		fixedProbFlag$sample4 = false;
		fixedProbFlag$sample13 = false;
	}

	@Override
	public final int get$v2() {
		return v2;
	}

	@Override
	public final void set$v2(int cv$value, boolean allocated$) {
		v2 = cv$value;
		fixedProbFlag$sample6 = false;
		fixedProbFlag$sample13 = false;
	}

	@Override
	public final int get$v3() {
		return v3;
	}

	@Override
	public final void set$v3(int cv$value, boolean allocated$) {
		v3 = cv$value;
		fixedProbFlag$sample7 = false;
		fixedProbFlag$sample13 = false;
	}

	@Override
	public final boolean get$value() {
		return value;
	}

	@Override
	public final void set$value(boolean cv$value, boolean allocated$) {
		value = cv$value;
	}

	@Override
	public final double[] get$weightings() {
		return weightings;
	}

	@Override
	public final void set$weightings(double[] cv$value, boolean allocated$) {
		weightings = cv$value;
	}

	private final void drawValueSample4() {
		v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
	}

	private final void drawValueSample6() {
		v2 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
	}

	private final void drawValueSample7() {
		v3 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
	}

	private final void inferSample4() {
		constrainedFlag$sample4 = false;
		int cv$numStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			int $var174 = weightings.length;
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var174) && (0 < $var174)) && (0.0 <= weightings[cv$valuePos])) && (weightings[cv$valuePos] <= 1.0))?Math.log(weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			constrainedFlag$sample4 = true;
			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
			double cv$consumerDistributionProbabilityAccumulator = 1.0;
			if(fixedFlag$sample6) {
				double var11 = ((double)cv$valuePos / (v2 + v3));
				cv$accumulatedConsumerProbabilities = (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY);
				cv$consumerDistributionProbabilityAccumulator = 0.0;
			} else {
				for(int index$sample6$4 = 0; index$sample6$4 < weightings.length; index$sample6$4 += 1) {
					double cv$probabilitySample6Value5 = distribution$sample6[index$sample6$4];
					double var11 = ((double)cv$valuePos / (index$sample6$4 + v3));
					if(((Math.log(cv$probabilitySample6Value5) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample6Value5) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample6Value5) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY));
						else
							cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample6Value5) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample6Value5)) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY));
					}
					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample6Value5);
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
			cv$var4$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample4) {
			double cv$logSum;
			double cv$lseMax = cv$var4$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$var4$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$var4$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					distribution$sample4[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					distribution$sample4[cv$indexName] = Math.exp((cv$var4$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var4$stateProbabilityGlobal.length; cv$indexName += 1)
				distribution$sample4[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void inferSample6() {
		constrainedFlag$sample6 = false;
		int cv$numStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			int $var187 = weightings.length;
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var187) && (0 < $var187)) && (0.0 <= weightings[cv$valuePos])) && (weightings[cv$valuePos] <= 1.0))?Math.log(weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			constrainedFlag$sample6 = true;
			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
			double cv$consumerDistributionProbabilityAccumulator = 1.0;
			if(fixedFlag$sample4) {
				double var11 = ((double)v1 / (cv$valuePos + v3));
				cv$accumulatedConsumerProbabilities = (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY);
				cv$consumerDistributionProbabilityAccumulator = 0.0;
			} else {
				for(int index$sample4$4 = 0; index$sample4$4 < weightings.length; index$sample4$4 += 1) {
					double cv$probabilitySample4Value5 = distribution$sample4[index$sample4$4];
					double var11 = ((double)index$sample4$4 / (cv$valuePos + v3));
					if(((Math.log(cv$probabilitySample4Value5) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample4Value5) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample4Value5) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY));
						else
							cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample4Value5) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample4Value5)) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY));
					}
					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample4Value5);
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
		if(constrainedFlag$sample6) {
			double cv$logSum;
			double cv$lseMax = cv$var6$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$var6$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$var6$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					distribution$sample6[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					distribution$sample6[cv$indexName] = Math.exp((cv$var6$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var6$stateProbabilityGlobal.length; cv$indexName += 1)
				distribution$sample6[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void inferSample7() {
		constrainedFlag$sample7 = false;
		int cv$numStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			v3 = cv$valuePos;
			int $var200 = weightings.length;
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var200) && (0 < $var200)) && (0.0 <= weightings[cv$valuePos])) && (weightings[cv$valuePos] <= 1.0))?Math.log(weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			constrainedFlag$sample7 = true;
			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
			double cv$consumerDistributionProbabilityAccumulator = 1.0;
			if(fixedFlag$sample4) {
				if(fixedFlag$sample6) {
					double var11 = ((double)v1 / (v2 + cv$valuePos));
					cv$accumulatedConsumerProbabilities = (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY);
					cv$consumerDistributionProbabilityAccumulator = 0.0;
				} else {
					for(int index$sample6$9 = 0; index$sample6$9 < weightings.length; index$sample6$9 += 1) {
						double cv$probabilitySample6Value10 = distribution$sample6[index$sample6$9];
						double var11 = ((double)v1 / (index$sample6$9 + cv$valuePos));
						if(((Math.log(cv$probabilitySample6Value10) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample6Value10) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample6Value10) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY));
							else
								cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample6Value10) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample6Value10)) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY));
						}
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample6Value10);
					}
				}
			} else {
				for(int index$sample4$4 = 0; index$sample4$4 < weightings.length; index$sample4$4 += 1) {
					double cv$probabilitySample4Value5 = distribution$sample4[index$sample4$4];
					if(fixedFlag$sample6) {
						double var11 = ((double)index$sample4$4 / (v2 + cv$valuePos));
						if(((Math.log(cv$probabilitySample4Value5) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample4Value5) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample4Value5) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY));
							else
								cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample4Value5) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample4Value5)) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY));
						}
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample4Value5);
					} else {
						for(int index$sample6$14 = 0; index$sample6$14 < weightings.length; index$sample6$14 += 1) {
							double cv$probabilitySample6Value15 = (cv$probabilitySample4Value5 * distribution$sample6[index$sample6$14]);
							double var11 = ((double)index$sample4$4 / (index$sample6$14 + cv$valuePos));
							if(((Math.log(cv$probabilitySample6Value15) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample6Value15) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample6Value15) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample6Value15) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample6Value15)) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample6Value15);
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
			cv$var7$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample7) {
			double cv$logSum;
			double cv$lseMax = cv$var7$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$var7$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$var7$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$var7$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$var7$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var7$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var7$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var7$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			v3 = DistributionSampling.sampleCategorical(RNG$, cv$var7$stateProbabilityGlobal, cv$numStates);
		}
	}

	private final void logProbabilityDistribution$sample13() {
		if(!fixedProbFlag$sample13) {
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			if(fixedFlag$sample4) {
				if(fixedFlag$sample6) {
					double var11 = ((double)v1 / (v2 + v3));
					cv$distributionAccumulator = (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY);
					cv$probabilityReached = 1.0;
				} else {
					for(int index$sample6$8 = 0; index$sample6$8 < weightings.length; index$sample6$8 += 1) {
						double cv$probabilitySample6Value9 = distribution$sample6[index$sample6$8];
						double var11 = ((double)v1 / (index$sample6$8 + v3));
						double cv$weightedProbability = (Math.log(cv$probabilitySample6Value9) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY));
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
								cv$distributionAccumulator = cv$weightedProbability;
							else
								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
						}
						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample6Value9);
					}
				}
			} else {
				for(int index$sample4$3 = 0; index$sample4$3 < weightings.length; index$sample4$3 += 1) {
					double cv$probabilitySample4Value4 = distribution$sample4[index$sample4$3];
					if(fixedFlag$sample6) {
						double var11 = ((double)index$sample4$3 / (v2 + v3));
						double cv$weightedProbability = (Math.log(cv$probabilitySample4Value4) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY));
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
								cv$distributionAccumulator = cv$weightedProbability;
							else
								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
						}
						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample4Value4);
					} else {
						for(int index$sample6$13 = 0; index$sample6$13 < weightings.length; index$sample6$13 += 1) {
							double cv$probabilitySample6Value14 = (cv$probabilitySample4Value4 * distribution$sample6[index$sample6$13]);
							double var11 = ((double)index$sample4$3 / (index$sample6$13 + v3));
							double cv$weightedProbability = (Math.log(cv$probabilitySample6Value14) + (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample6Value14);
						}
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			logProbability$v = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample13 = ((fixedFlag$sample4 && fixedFlag$sample6) && fixedFlag$sample7);
		} else {
			logProbability$$model = (logProbability$$model + logProbability$v);
			logProbability$$evidence = (logProbability$$evidence + logProbability$v);
		}
	}

	private final void logProbabilityDistribution$sample4() {
		if(!fixedProbFlag$sample4) {
			if(fixedFlag$sample4) {
				double cv$distributionAccumulator = ((((((0.0 <= v1) && (v1 < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[v1])) && (weightings[v1] <= 1.0))?Math.log(weightings[v1]):Double.NEGATIVE_INFINITY);
				logProbability$v1 = cv$distributionAccumulator;
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample4 = true;
			}
		} else {
			logProbability$$model = (logProbability$$model + logProbability$v1);
			if(fixedFlag$sample4)
				logProbability$$evidence = (logProbability$$evidence + logProbability$v1);
		}
	}

	private final void logProbabilityDistribution$sample6() {
		if(!fixedProbFlag$sample6) {
			if(fixedFlag$sample6) {
				double cv$distributionAccumulator = ((((((0.0 <= v2) && (v2 < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[v2])) && (weightings[v2] <= 1.0))?Math.log(weightings[v2]):Double.NEGATIVE_INFINITY);
				logProbability$c = (logProbability$c + cv$distributionAccumulator);
				logProbability$v2 = cv$distributionAccumulator;
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample6 = true;
			}
		} else {
			logProbability$c = (logProbability$c + logProbability$v2);
			logProbability$$model = (logProbability$$model + logProbability$v2);
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + logProbability$v2);
		}
	}

	private final void logProbabilityValue$sample13() {
		if(!fixedProbFlag$sample13) {
			double var11 = ((double)v1 / (v2 + v3));
			double cv$distributionAccumulator = (((0.0 <= var11) && (var11 <= 1.0))?Math.log((v?var11:(1.0 - var11))):Double.NEGATIVE_INFINITY);
			logProbability$v = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample13 = ((fixedFlag$sample4 && fixedFlag$sample6) && fixedFlag$sample7);
		} else {
			logProbability$$model = (logProbability$$model + logProbability$v);
			logProbability$$evidence = (logProbability$$evidence + logProbability$v);
		}
	}

	private final void logProbabilityValue$sample4() {
		if(!fixedProbFlag$sample4) {
			double cv$distributionAccumulator = ((((((0.0 <= v1) && (v1 < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[v1])) && (weightings[v1] <= 1.0))?Math.log(weightings[v1]):Double.NEGATIVE_INFINITY);
			logProbability$v1 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample4)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample4 = fixedFlag$sample4;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$v1);
			if(fixedFlag$sample4)
				logProbability$$evidence = (logProbability$$evidence + logProbability$v1);
		}
	}

	private final void logProbabilityValue$sample6() {
		if(!fixedProbFlag$sample6) {
			double cv$distributionAccumulator = ((((((0.0 <= v2) && (v2 < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[v2])) && (weightings[v2] <= 1.0))?Math.log(weightings[v2]):Double.NEGATIVE_INFINITY);
			logProbability$c = (logProbability$c + cv$distributionAccumulator);
			logProbability$v2 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample6 = fixedFlag$sample6;
		} else {
			logProbability$c = (logProbability$c + logProbability$v2);
			logProbability$$model = (logProbability$$model + logProbability$v2);
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + logProbability$v2);
		}
	}

	private final void logProbabilityValue$sample7() {
		if(!fixedProbFlag$sample7) {
			double cv$distributionAccumulator = ((((((0.0 <= v3) && (v3 < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[v3])) && (weightings[v3] <= 1.0))?Math.log(weightings[v3]):Double.NEGATIVE_INFINITY);
			logProbability$c = (logProbability$c + cv$distributionAccumulator);
			logProbability$v3 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample7)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample7 = fixedFlag$sample7;
		} else {
			logProbability$c = (logProbability$c + logProbability$v3);
			logProbability$$model = (logProbability$$model + logProbability$v3);
			if(fixedFlag$sample7)
				logProbability$$evidence = (logProbability$$evidence + logProbability$v3);
		}
	}

	@Override
	public final void allocateScratch() {
		cv$var4$stateProbabilityGlobal = new double[weightings.length];
		cv$var6$stateProbabilityGlobal = new double[weightings.length];
		cv$var7$stateProbabilityGlobal = new double[weightings.length];
	}

	@Override
	public final void allocator() {
		distribution$sample4 = new double[weightings.length];
		distribution$sample6 = new double[weightings.length];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample4)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample6)
			v2 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample7)
			v3 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		v = DistributionSampling.sampleBernoulli(RNG$, ((double)v1 / (v2 + v3)));
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample4) {
			for(int index$var3 = 0; index$var3 < weightings.length; index$var3 += 1)
				distribution$sample4[index$var3] = ((((0 < weightings.length) && (0.0 <= weightings[index$var3])) && (weightings[index$var3] <= 1.0))?weightings[index$var3]:0.0);
		}
		if((!fixedFlag$sample6 || !fixedFlag$sample7)) {
			for(int index$c = 0; index$c < weightings.length; index$c += 1)
				distribution$sample6[index$c] = ((((0 < weightings.length) && (0.0 <= weightings[index$c])) && (weightings[index$c] <= 1.0))?weightings[index$c]:0.0);
		}
		if(!fixedFlag$sample7)
			v3 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample4)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample6)
			v2 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample7)
			v3 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		v = DistributionSampling.sampleBernoulli(RNG$, ((double)v1 / (v2 + v3)));
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample4)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample6)
			v2 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample7)
			v3 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample4)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample6)
			v2 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample7)
			v3 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample4)
				inferSample4();
			if(!fixedFlag$sample6)
				inferSample6();
			if(!fixedFlag$sample7)
				inferSample7();
		} else {
			if(!fixedFlag$sample7)
				inferSample7();
			if(!fixedFlag$sample6)
				inferSample6();
			if(!fixedFlag$sample4)
				inferSample4();
		}
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample4)
			drawValueSample4();
		if(!constrainedFlag$sample6)
			drawValueSample6();
		if(!constrainedFlag$sample7)
			drawValueSample7();
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		if(!fixedProbFlag$sample4)
			logProbability$v1 = Double.NaN;
		logProbability$c = 0.0;
		if(!fixedProbFlag$sample6)
			logProbability$v2 = Double.NaN;
		if(!fixedProbFlag$sample7)
			logProbability$v3 = Double.NaN;
		if(!fixedProbFlag$sample13)
			logProbability$v = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample7)
			logProbabilityValue$sample7();
		logProbabilityValue$sample13();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample4();
		logProbabilityDistribution$sample6();
		logProbabilityValue$sample7();
		logProbabilityDistribution$sample13();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample4();
		logProbabilityValue$sample6();
		logProbabilityValue$sample7();
		logProbabilityValue$sample13();
	}

	@Override
	public final void propagateObservedValues() {
		v = value;
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
		     + "model DistributionTest1b(double[] weightings, boolean value) {\n"
		     + "    int v1 = categorical(weightings).sampleDistribution();\n"
		     + "    Categorical c = new Categorical(weightings);\n"
		     + "    int v2 = c.sampleDistribution();\n"
		     + "    int v3 = c.sample();\n"
		     + "    boolean v = bernoulli((1.0*v1)/(v2 + v3)).sample();\n"
		     + "    v.observe(value);\n"
		     + "}\n"
		     + "";
	}
}