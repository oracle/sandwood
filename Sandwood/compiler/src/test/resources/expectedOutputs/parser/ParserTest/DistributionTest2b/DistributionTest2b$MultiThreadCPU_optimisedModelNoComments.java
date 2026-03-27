package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DistributionTest2b$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DistributionTest2b$CoreInterface {
	private boolean[] constrainedFlag$sample23;
	private boolean[] constrainedFlag$sample36;
	private boolean constrainedFlag$sample5 = true;
	private boolean constrainedFlag$sample9 = true;
	private double[][] cv$var23$stateProbabilityGlobal;
	private double[] cv$var36$stateProbabilityGlobal;
	private double[] cv$var5$stateProbabilityGlobal;
	private double[] cv$var9$stateProbabilityGlobal;
	private double[][] distribution$sample23;
	private double[] distribution$sample5;
	private double[] distribution$sample9;
	private boolean fixedFlag$sample23 = false;
	private boolean fixedFlag$sample5 = false;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample23 = false;
	private boolean fixedProbFlag$sample5 = false;
	private boolean fixedProbFlag$sample9 = false;
	private int length$value;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$c;
	private double[] logProbability$sample23;
	private double[] logProbability$sample43;
	private double logProbability$v;
	private double logProbability$v1;
	private double logProbability$v2;
	private double logProbability$v3;
	private double logProbability$var9;
	private int size;
	private boolean system$gibbsForward = true;
	private boolean[] v;
	private int v1;
	private int[] v2;
	private int[] v3;
	private boolean[] value;
	private double[] weightings;

	public DistributionTest2b$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$distribution$sample23() {
		return distribution$sample23;
	}

	@Override
	public final void set$distribution$sample23(double[][] cv$value, boolean allocated$) {
		distribution$sample23 = cv$value;
	}

	@Override
	public final double[] get$distribution$sample5() {
		return distribution$sample5;
	}

	@Override
	public final void set$distribution$sample5(double[] cv$value, boolean allocated$) {
		distribution$sample5 = cv$value;
	}

	@Override
	public final double[] get$distribution$sample9() {
		return distribution$sample9;
	}

	@Override
	public final void set$distribution$sample9(double[] cv$value, boolean allocated$) {
		distribution$sample9 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample23() {
		return fixedFlag$sample23;
	}

	@Override
	public final void set$fixedFlag$sample23(boolean cv$value, boolean allocated$) {
		fixedFlag$sample23 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample23$1 = 0; index$constrainedFlag$sample23$1 < constrainedFlag$sample23.length; index$constrainedFlag$sample23$1 += 1)
				constrainedFlag$sample23[index$constrainedFlag$sample23$1] = true;
		}
		fixedProbFlag$sample23 = (cv$value && fixedProbFlag$sample23);
	}

	@Override
	public final boolean get$fixedFlag$sample5() {
		return fixedFlag$sample5;
	}

	@Override
	public final void set$fixedFlag$sample5(boolean cv$value, boolean allocated$) {
		fixedFlag$sample5 = cv$value;
		constrainedFlag$sample5 = (cv$value || constrainedFlag$sample5);
		fixedProbFlag$sample5 = (cv$value && fixedProbFlag$sample5);
	}

	@Override
	public final boolean get$fixedFlag$sample9() {
		return fixedFlag$sample9;
	}

	@Override
	public final void set$fixedFlag$sample9(boolean cv$value, boolean allocated$) {
		fixedFlag$sample9 = cv$value;
		constrainedFlag$sample9 = (cv$value || constrainedFlag$sample9);
		fixedProbFlag$sample9 = (cv$value && fixedProbFlag$sample9);
	}

	@Override
	public final int get$length$value() {
		return length$value;
	}

	@Override
	public final void set$length$value(int cv$value, boolean allocated$) {
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
	public final void set$v1(int cv$value, boolean allocated$) {
		v1 = cv$value;
		fixedProbFlag$sample5 = false;
	}

	@Override
	public final int[] get$v2() {
		return v2;
	}

	@Override
	public final void set$v2(int[] cv$value, boolean allocated$) {
		v2 = cv$value;
		fixedProbFlag$sample9 = false;
		fixedProbFlag$sample23 = false;
	}

	@Override
	public final int[] get$v3() {
		return v3;
	}

	@Override
	public final void set$v3(int[] cv$value, boolean allocated$) {
		v3 = cv$value;
	}

	@Override
	public final boolean[] get$value() {
		return value;
	}

	@Override
	public final void set$value(boolean[] cv$value, boolean allocated$) {
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

	private final void drawValueSample23(int i, int threadID$cv$i, Rng RNG$) {
		v2[i] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
	}

	private final void drawValueSample36(int j) {
		v3[j] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
	}

	private final void drawValueSample5() {
		v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
	}

	private final void drawValueSample9() {
		v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
	}

	private final void inferSample23(int i, int threadID$cv$i, Rng RNG$) {
		constrainedFlag$sample23[(i - 1)] = false;
		int cv$numStates = weightings.length;
		double[] cv$stateProbabilityLocal = cv$var23$stateProbabilityGlobal[threadID$cv$i];
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			int $var579 = weightings.length;
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var579) && (0 < $var579)) && (0.0 <= weightings[cv$valuePos])) && (weightings[cv$valuePos] <= 1.0))?Math.log(weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			constrainedFlag$sample23[(i - 1)] = true;
			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
			double cv$consumerDistributionProbabilityAccumulator = 1.0;
			if(fixedFlag$sample5) {
				double var41 = ((double)v1 / (cv$valuePos + v3[i]));
				cv$accumulatedConsumerProbabilities = (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[i]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY);
				cv$consumerDistributionProbabilityAccumulator = 0.0;
			} else {
				for(int index$sample5$5 = 0; index$sample5$5 < weightings.length; index$sample5$5 += 1) {
					double cv$probabilitySample5Value6 = distribution$sample5[index$sample5$5];
					double var41 = ((double)index$sample5$5 / (cv$valuePos + v3[i]));
					if(((Math.log(cv$probabilitySample5Value6) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[i]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value6) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[i]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value6) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[i]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
						else
							cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value6) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[i]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value6)) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[i]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
					}
					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value6);
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
			cv$stateProbabilityLocal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample23[(i - 1)]) {
			double[] cv$localProbability = distribution$sample23[(i - 1)];
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
	}

	private final void inferSample36(int j) {
		constrainedFlag$sample36[j] = false;
		int cv$numStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			v3[j] = cv$valuePos;
			int $var607 = weightings.length;
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var607) && (0 < $var607)) && (0.0 <= weightings[cv$valuePos])) && (weightings[cv$valuePos] <= 1.0))?Math.log(weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			constrainedFlag$sample36[j] = true;
			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
			double cv$consumerDistributionProbabilityAccumulator = 1.0;
			if((0 == j)) {
				if(fixedFlag$sample5) {
					if(fixedFlag$sample9) {
						double var41 = ((double)v1 / (v2[0] + cv$valuePos));
						cv$accumulatedConsumerProbabilities = (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY);
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						for(int index$sample9$9 = 0; index$sample9$9 < weightings.length; index$sample9$9 += 1) {
							double cv$probabilitySample9Value10 = distribution$sample9[index$sample9$9];
							double var41 = ((double)v1 / (index$sample9$9 + cv$valuePos));
							if(((Math.log(cv$probabilitySample9Value10) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample9Value10) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample9Value10) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample9Value10) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample9Value10)) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample9Value10);
						}
					}
				} else {
					for(int index$sample5$4 = 0; index$sample5$4 < weightings.length; index$sample5$4 += 1) {
						double cv$probabilitySample5Value5 = distribution$sample5[index$sample5$4];
						if(fixedFlag$sample9) {
							double var41 = ((double)index$sample5$4 / (v2[0] + cv$valuePos));
							if(((Math.log(cv$probabilitySample5Value5) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value5) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value5) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value5) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value5)) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value5);
						} else {
							for(int index$sample9$14 = 0; index$sample9$14 < weightings.length; index$sample9$14 += 1) {
								double cv$probabilitySample9Value15 = (cv$probabilitySample5Value5 * distribution$sample9[index$sample9$14]);
								double var41 = ((double)index$sample5$4 / (index$sample9$14 + cv$valuePos));
								if(((Math.log(cv$probabilitySample9Value15) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample9Value15) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample9Value15) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample9Value15) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample9Value15)) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample9Value15);
							}
						}
					}
				}
			}
			if((1 <= j)) {
				if(fixedFlag$sample5) {
					if(fixedFlag$sample23) {
						double var41 = ((double)v1 / (v2[j] + cv$valuePos));
						if(((((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY);
							else
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY))) + 1)) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
						}
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
					} else {
						for(int index$sample23$25 = 0; index$sample23$25 < weightings.length; index$sample23$25 += 1) {
							double cv$probabilitySample23Value26 = distribution$sample23[(j - 1)][index$sample23$25];
							double var41 = ((double)v1 / (index$sample23$25 + cv$valuePos));
							if(((Math.log(cv$probabilitySample23Value26) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample23Value26) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample23Value26) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample23Value26) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample23Value26)) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample23Value26);
						}
					}
				} else {
					for(int index$sample5$19 = 0; index$sample5$19 < weightings.length; index$sample5$19 += 1) {
						double cv$probabilitySample5Value20 = distribution$sample5[index$sample5$19];
						if(fixedFlag$sample23) {
							double var41 = ((double)index$sample5$19 / (v2[j] + cv$valuePos));
							if(((Math.log(cv$probabilitySample5Value20) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value20) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value20) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value20) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value20)) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value20);
						} else {
							for(int index$sample23$31 = 0; index$sample23$31 < weightings.length; index$sample23$31 += 1) {
								double cv$probabilitySample23Value32 = (cv$probabilitySample5Value20 * distribution$sample23[(j - 1)][index$sample23$31]);
								double var41 = ((double)index$sample5$19 / (index$sample23$31 + cv$valuePos));
								if(((Math.log(cv$probabilitySample23Value32) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample23Value32) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample23Value32) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample23Value32) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample23Value32)) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample23Value32);
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
			cv$var36$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample36[j]) {
			double cv$logSum;
			double cv$lseMax = cv$var36$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$var36$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$var36$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$var36$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$var36$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var36$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var36$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var36$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			v3[j] = DistributionSampling.sampleCategorical(RNG$, cv$var36$stateProbabilityGlobal, cv$numStates);
		}
	}

	private final void inferSample5() {
		constrainedFlag$sample5 = false;
		int cv$numStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			int $var525 = weightings.length;
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var525) && (0 < $var525)) && (0.0 <= weightings[cv$valuePos])) && (weightings[cv$valuePos] <= 1.0))?Math.log(weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			for(int j = 0; j < size; j += 1) {
				constrainedFlag$sample5 = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((0 == j)) {
					if(fixedFlag$sample9) {
						double var41 = ((double)cv$valuePos / (v2[0] + v3[0]));
						cv$accumulatedConsumerProbabilities = (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY);
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						for(int index$sample9$4 = 0; index$sample9$4 < weightings.length; index$sample9$4 += 1) {
							double cv$probabilitySample9Value5 = distribution$sample9[index$sample9$4];
							double var41 = ((double)cv$valuePos / (index$sample9$4 + v3[0]));
							if(((Math.log(cv$probabilitySample9Value5) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample9Value5) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample9Value5) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample9Value5) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample9Value5)) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample9Value5);
						}
					}
				}
				if((1 <= j)) {
					if(fixedFlag$sample23) {
						double var41 = ((double)cv$valuePos / (v2[j] + v3[j]));
						if(((((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY);
							else
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY))) + 1)) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
						}
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
					} else {
						for(int index$sample23$12 = 0; index$sample23$12 < weightings.length; index$sample23$12 += 1) {
							double cv$probabilitySample23Value13 = distribution$sample23[(j - 1)][index$sample23$12];
							double var41 = ((double)cv$valuePos / (index$sample23$12 + v3[j]));
							if(((Math.log(cv$probabilitySample23Value13) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample23Value13) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample23Value13) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample23Value13) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample23Value13)) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample23Value13);
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
		if(constrainedFlag$sample5) {
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
	}

	private final void inferSample9() {
		constrainedFlag$sample9 = false;
		int cv$numStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			int $var558 = weightings.length;
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var558) && (0 < $var558)) && (0.0 <= weightings[cv$valuePos])) && (weightings[cv$valuePos] <= 1.0))?Math.log(weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((0 < size)) {
				constrainedFlag$sample9 = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if(fixedFlag$sample5) {
					double var41 = ((double)v1 / (cv$valuePos + v3[0]));
					cv$accumulatedConsumerProbabilities = (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY);
					cv$consumerDistributionProbabilityAccumulator = 0.0;
				} else {
					for(int index$sample5$4 = 0; index$sample5$4 < weightings.length; index$sample5$4 += 1) {
						double cv$probabilitySample5Value5 = distribution$sample5[index$sample5$4];
						double var41 = ((double)index$sample5$4 / (cv$valuePos + v3[0]));
						if(((Math.log(cv$probabilitySample5Value5) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value5) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value5) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
							else
								cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value5) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value5)) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[0]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
						}
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value5);
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
		if(constrainedFlag$sample9) {
			double cv$logSum;
			double cv$lseMax = cv$var9$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$var9$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$var9$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					distribution$sample9[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					distribution$sample9[cv$indexName] = Math.exp((cv$var9$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var9$stateProbabilityGlobal.length; cv$indexName += 1)
				distribution$sample9[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void logProbabilityDistribution$sample23() {
		if(!fixedProbFlag$sample23) {
			if(fixedFlag$sample23) {
				double cv$accumulator = 0.0;
				for(int i = 1; i < size; i += 1) {
					int cv$sampleValue = v2[i];
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[cv$sampleValue])) && (weightings[cv$sampleValue] <= 1.0))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$sample23[(i - 1)] = cv$distributionAccumulator;
				}
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample23 = true;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i = 1; i < size; i += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample23[(i - 1)]);
			if(fixedFlag$sample23)
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample23)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample43() {
		double cv$accumulator = 0.0;
		for(int j = 0; j < size; j += 1) {
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			boolean cv$sampleValue = v[j];
			if((0 == j)) {
				if(fixedFlag$sample5) {
					if(fixedFlag$sample9) {
						double var41 = ((double)v1 / (v2[0] + v3[0]));
						cv$distributionAccumulator = (((0.0 <= var41) && (var41 <= 1.0))?Math.log((cv$sampleValue?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY);
						cv$probabilityReached = 1.0;
					} else {
						for(int index$sample9$8 = 0; index$sample9$8 < weightings.length; index$sample9$8 += 1) {
							double cv$probabilitySample9Value9 = distribution$sample9[index$sample9$8];
							double var41 = ((double)v1 / (index$sample9$8 + v3[0]));
							double cv$weightedProbability = (Math.log(cv$probabilitySample9Value9) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((cv$sampleValue?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
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
					for(int index$sample5$3 = 0; index$sample5$3 < weightings.length; index$sample5$3 += 1) {
						double cv$probabilitySample5Value4 = distribution$sample5[index$sample5$3];
						if(fixedFlag$sample9) {
							double var41 = ((double)index$sample5$3 / (v2[0] + v3[0]));
							double cv$weightedProbability = (Math.log(cv$probabilitySample5Value4) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((cv$sampleValue?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
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
							for(int index$sample9$13 = 0; index$sample9$13 < weightings.length; index$sample9$13 += 1) {
								double cv$probabilitySample9Value14 = (cv$probabilitySample5Value4 * distribution$sample9[index$sample9$13]);
								double var41 = ((double)index$sample5$3 / (index$sample9$13 + v3[0]));
								double cv$weightedProbability = (Math.log(cv$probabilitySample9Value14) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((cv$sampleValue?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
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
			}
			if((1 <= j)) {
				if(fixedFlag$sample5) {
					if(fixedFlag$sample23) {
						double var41 = ((double)v1 / (v2[j] + v3[j]));
						double cv$weightedProbability = (((0.0 <= var41) && (var41 <= 1.0))?Math.log((cv$sampleValue?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY);
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
						for(int index$sample23$28 = 0; index$sample23$28 < weightings.length; index$sample23$28 += 1) {
							double cv$probabilitySample23Value29 = distribution$sample23[(j - 1)][index$sample23$28];
							double var41 = ((double)v1 / (index$sample23$28 + v3[j]));
							double cv$weightedProbability = (Math.log(cv$probabilitySample23Value29) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((cv$sampleValue?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample23Value29);
						}
					}
				} else {
					for(int index$sample5$22 = 0; index$sample5$22 < weightings.length; index$sample5$22 += 1) {
						double cv$probabilitySample5Value23 = distribution$sample5[index$sample5$22];
						if(fixedFlag$sample23) {
							double var41 = ((double)index$sample5$22 / (v2[j] + v3[j]));
							double cv$weightedProbability = (Math.log(cv$probabilitySample5Value23) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((cv$sampleValue?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value23);
						} else {
							for(int index$sample23$34 = 0; index$sample23$34 < weightings.length; index$sample23$34 += 1) {
								double cv$probabilitySample23Value35 = (cv$probabilitySample5Value23 * distribution$sample23[(j - 1)][index$sample23$34]);
								double var41 = ((double)index$sample5$22 / (index$sample23$34 + v3[j]));
								double cv$weightedProbability = (Math.log(cv$probabilitySample23Value35) + (((0.0 <= var41) && (var41 <= 1.0))?Math.log((cv$sampleValue?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample23Value35);
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
			logProbability$sample43[j] = cv$distributionAccumulator;
		}
		logProbability$v = (logProbability$v + cv$accumulator);
		logProbability$$model = (logProbability$$model + cv$accumulator);
		logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
	}

	private final void logProbabilityDistribution$sample5() {
		if(!fixedProbFlag$sample5) {
			if(fixedFlag$sample5) {
				double cv$distributionAccumulator = ((((((0.0 <= v1) && (v1 < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[v1])) && (weightings[v1] <= 1.0))?Math.log(weightings[v1]):Double.NEGATIVE_INFINITY);
				logProbability$c = (logProbability$c + cv$distributionAccumulator);
				logProbability$v1 = cv$distributionAccumulator;
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample5 = true;
			}
		} else {
			logProbability$c = (logProbability$c + logProbability$v1);
			logProbability$$model = (logProbability$$model + logProbability$v1);
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence + logProbability$v1);
		}
	}

	private final void logProbabilityDistribution$sample9() {
		if(!fixedProbFlag$sample9) {
			if(fixedFlag$sample9) {
				int cv$sampleValue = v2[0];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[cv$sampleValue])) && (weightings[cv$sampleValue] <= 1.0))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				logProbability$var9 = cv$distributionAccumulator;
				logProbability$v2 = (logProbability$v2 + cv$distributionAccumulator);
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample9 = true;
			}
		} else {
			if(fixedFlag$sample9)
				logProbability$v2 = (logProbability$v2 + logProbability$var9);
			logProbability$$model = (logProbability$$model + logProbability$var9);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var9);
		}
	}

	private final void logProbabilityValue$sample23() {
		if(!fixedProbFlag$sample23) {
			double cv$accumulator = 0.0;
			for(int i = 1; i < size; i += 1) {
				int cv$sampleValue = v2[i];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[cv$sampleValue])) && (weightings[cv$sampleValue] <= 1.0))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample23[(i - 1)] = cv$distributionAccumulator;
			}
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample23)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample23 = fixedFlag$sample23;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 1; i < size; i += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample23[(i - 1)]);
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample23)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample36() {
		double cv$sampleAccumulator = 0.0;
		for(int j = 0; j < size; j += 1) {
			int cv$sampleValue = v3[j];
			cv$sampleAccumulator = (cv$sampleAccumulator + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[cv$sampleValue])) && (weightings[cv$sampleValue] <= 1.0))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
		}
		logProbability$c = (logProbability$c + cv$sampleAccumulator);
		logProbability$v3 = cv$sampleAccumulator;
		logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
	}

	private final void logProbabilityValue$sample43() {
		double cv$accumulator = 0.0;
		for(int j = 0; j < size; j += 1) {
			double var41 = ((double)v1 / (v2[j] + v3[j]));
			double cv$distributionAccumulator = (((0.0 <= var41) && (var41 <= 1.0))?Math.log((v[j]?var41:(1.0 - var41))):Double.NEGATIVE_INFINITY);
			cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
			logProbability$sample43[j] = cv$distributionAccumulator;
		}
		logProbability$v = (logProbability$v + cv$accumulator);
		logProbability$$model = (logProbability$$model + cv$accumulator);
		logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
	}

	private final void logProbabilityValue$sample5() {
		if(!fixedProbFlag$sample5) {
			double cv$distributionAccumulator = ((((((0.0 <= v1) && (v1 < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[v1])) && (weightings[v1] <= 1.0))?Math.log(weightings[v1]):Double.NEGATIVE_INFINITY);
			logProbability$c = (logProbability$c + cv$distributionAccumulator);
			logProbability$v1 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample5 = fixedFlag$sample5;
		} else {
			logProbability$c = (logProbability$c + logProbability$v1);
			logProbability$$model = (logProbability$$model + logProbability$v1);
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence + logProbability$v1);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!fixedProbFlag$sample9) {
			int cv$sampleValue = v2[0];
			double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[cv$sampleValue])) && (weightings[cv$sampleValue] <= 1.0))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var9 = cv$distributionAccumulator;
			logProbability$v2 = (logProbability$v2 + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample9 = fixedFlag$sample9;
		} else {
			logProbability$v2 = (logProbability$v2 + logProbability$var9);
			logProbability$$model = (logProbability$$model + logProbability$var9);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var9);
		}
	}

	@Override
	public final void allocateScratch() {
		cv$var5$stateProbabilityGlobal = new double[weightings.length];
		cv$var9$stateProbabilityGlobal = new double[weightings.length];
		int cv$threadCount = threadCount();
		cv$var23$stateProbabilityGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var23$stateProbabilityGlobal[cv$index] = new double[weightings.length];
		cv$var36$stateProbabilityGlobal = new double[weightings.length];
	}

	@Override
	public final void allocator() {
		if((!fixedFlag$sample9 || !fixedFlag$sample23))
			v2 = new int[length$value];
		v = new boolean[length$value];
		v3 = new int[length$value];
		distribution$sample5 = new double[weightings.length];
		distribution$sample9 = new double[weightings.length];
		distribution$sample23 = new double[(length$value - 1)][];
		for(int i = 1; i < length$value; i += 1)
			distribution$sample23[(i - 1)] = new double[weightings.length];
		constrainedFlag$sample23 = new boolean[(length$value - 1)];
		constrainedFlag$sample36 = new boolean[length$value];
		logProbability$sample23 = new double[(length$value - 1)];
		logProbability$sample43 = new double[length$value];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample9)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample23)
			parallelFor(RNG$, 1, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[i] = DistributionSampling.sampleCategorical(RNG$1, weightings, weightings.length);
				}
			);

		for(int j = 0; j < size; j += 1) {
			v3[j] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
			v[j] = DistributionSampling.sampleBernoulli(RNG$, ((double)v1 / (v2[j] + v3[j])));
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int index$c = 0; index$c < weightings.length; index$c += 1)
			distribution$sample5[index$c] = ((((0 < weightings.length) && (0.0 <= weightings[index$c])) && (weightings[index$c] <= 1.0))?weightings[index$c]:0.0);
		if(!fixedFlag$sample9) {
			for(int index$var8 = 0; index$var8 < weightings.length; index$var8 += 1)
				distribution$sample9[index$var8] = ((((0 < weightings.length) && (0.0 <= weightings[index$var8])) && (weightings[index$var8] <= 1.0))?weightings[index$var8]:0.0);
		}
		if(!fixedFlag$sample23)
			parallelFor(RNG$, 1, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							double[] cv$distribution$sample23 = distribution$sample23[(i - 1)];
							for(int index$var22 = 0; index$var22 < weightings.length; index$var22 += 1)
								cv$distribution$sample23[index$var22] = ((((0 < weightings.length) && (0.0 <= weightings[index$var22])) && (weightings[index$var22] <= 1.0))?weightings[index$var22]:0.0);
						}
				}
			);

		for(int j = 0; j < size; j += 1)
			v3[j] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample9)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample23)
			parallelFor(RNG$, 1, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[i] = DistributionSampling.sampleCategorical(RNG$1, weightings, weightings.length);
				}
			);

		for(int j = 0; j < size; j += 1) {
			v3[j] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
			v[j] = DistributionSampling.sampleBernoulli(RNG$, ((double)v1 / (v2[j] + v3[j])));
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample9)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample23)
			parallelFor(RNG$, 1, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[i] = DistributionSampling.sampleCategorical(RNG$1, weightings, weightings.length);
				}
			);

		for(int j = 0; j < size; j += 1)
			v3[j] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample9)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample23)
			parallelFor(RNG$, 1, size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							v2[i] = DistributionSampling.sampleCategorical(RNG$1, weightings, weightings.length);
				}
			);

		for(int j = 0; j < size; j += 1)
			v3[j] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample5)
				inferSample5();
			if(!fixedFlag$sample9)
				inferSample9();
			if(!fixedFlag$sample23)
				parallelFor(RNG$, 1, size, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i = forStart$i; i < forEnd$i; i += 1)
								inferSample23(i, threadID$i, RNG$1);
					}
				);

			for(int j = 0; j < size; j += 1)
				inferSample36(j);
		} else {
			for(int j = (size - 1); j >= 0; j -= 1)
				inferSample36(j);
			if(!fixedFlag$sample23)
				parallelFor(RNG$, 1, size, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i = forStart$i; i < forEnd$i; i += 1)
								inferSample23(i, threadID$i, RNG$1);
					}
				);

			if(!fixedFlag$sample9)
				inferSample9();
			if(!fixedFlag$sample5)
				inferSample5();
		}
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample5)
			drawValueSample5();
		if(!constrainedFlag$sample9)
			drawValueSample9();
		parallelFor(RNG$, 1, size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!constrainedFlag$sample23[(i - 1)])
							drawValueSample23(i, threadID$i, RNG$1);
					}
			}
		);
		for(int j = 0; j < size; j += 1) {
			if(!constrainedFlag$sample36[j])
				drawValueSample36(j);
		}
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$c = 0.0;
		if(!fixedProbFlag$sample5)
			logProbability$v1 = Double.NaN;
		logProbability$v2 = 0.0;
		if(!fixedProbFlag$sample9)
			logProbability$var9 = Double.NaN;
		if(!fixedProbFlag$sample23) {
			for(int i = 1; i < size; i += 1)
				logProbability$sample23[(i - 1)] = Double.NaN;
		}
		logProbability$v3 = Double.NaN;
		logProbability$v = 0.0;
		for(int j = 0; j < size; j += 1)
			logProbability$sample43[j] = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		size = length$value;
		for(int index$constrainedFlag$sample23$1 = 0; index$constrainedFlag$sample23$1 < constrainedFlag$sample23.length; index$constrainedFlag$sample23$1 += 1)
			constrainedFlag$sample23[index$constrainedFlag$sample23$1] = true;
		for(int index$constrainedFlag$sample36$1 = 0; index$constrainedFlag$sample36$1 < constrainedFlag$sample36.length; index$constrainedFlag$sample36$1 += 1)
			constrainedFlag$sample36[index$constrainedFlag$sample36$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample43();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample5();
		logProbabilityDistribution$sample9();
		logProbabilityDistribution$sample23();
		logProbabilityValue$sample36();
		logProbabilityDistribution$sample43();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample9();
		logProbabilityValue$sample23();
		logProbabilityValue$sample36();
		logProbabilityValue$sample43();
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
		     + "model DistributionTest2b(double[] weightings, boolean[] value) {\n"
		     + "    int size = value.length;\n"
		     + "    \n"
		     + "    Categorical c = new Categorical(weightings);\n"
		     + "    int v1 = c.sampleDistribution();\n"
		     + "    \n"
		     + "    int[] v2 = new int[size];\n"
		     + "    v2[0] = categorical(weightings).sampleDistribution();\n"
		     + "    for(int i:[1..size))\n"
		     + "        v2[i] = categorical(weightings).sampleDistribution();\n"
		     + "        \n"
		     + "    boolean[] v = new boolean[size];\n"
		     + "    for(int j:[0..size)) {\n"
		     + "        int v3 = c.sample();\n"
		     + "        v[j] = bernoulli((1.0*v1)/(v2[j] + v3)).sample();\n"
		     + "    }\n"
		     + "        \n"
		     + "    v.observe(value);\n"
		     + "}\n"
		     + "";
	}
}