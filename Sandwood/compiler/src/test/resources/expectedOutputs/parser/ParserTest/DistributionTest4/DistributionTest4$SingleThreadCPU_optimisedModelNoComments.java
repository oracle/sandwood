package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DistributionTest4$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements DistributionTest4$CoreInterface {
	private boolean constrainedFlag$sample11 = true;
	private boolean[] constrainedFlag$sample27;
	private boolean constrainedFlag$sample5 = true;
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
	private double logProbability$var11;
	private int size;
	private boolean system$gibbsForward = true;
	private boolean[] v;
	private int v1;
	private int[] v2;
	private boolean[] value;
	private double[] weightings;

	public DistributionTest4$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$distribution$sample11() {
		return distribution$sample11;
	}

	@Override
	public final void set$distribution$sample11(double[] cv$value, boolean allocated$) {
		distribution$sample11 = cv$value;
	}

	@Override
	public final double[][] get$distribution$sample27() {
		return distribution$sample27;
	}

	@Override
	public final void set$distribution$sample27(double[][] cv$value, boolean allocated$) {
		distribution$sample27 = cv$value;
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
	public final boolean get$fixedFlag$sample11() {
		return fixedFlag$sample11;
	}

	@Override
	public final void set$fixedFlag$sample11(boolean cv$value, boolean allocated$) {
		fixedFlag$sample11 = cv$value;
		constrainedFlag$sample11 = (cv$value || constrainedFlag$sample11);
		fixedProbFlag$sample11 = (cv$value && fixedProbFlag$sample11);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$fixedFlag$sample27() {
		return fixedFlag$sample27;
	}

	@Override
	public final void set$fixedFlag$sample27(boolean cv$value, boolean allocated$) {
		fixedFlag$sample27 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample27$1 = 0; index$constrainedFlag$sample27$1 < constrainedFlag$sample27.length; index$constrainedFlag$sample27$1 += 1)
				constrainedFlag$sample27[index$constrainedFlag$sample27$1] = true;
		}
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
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
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
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
		fixedProbFlag$sample53 = false;
	}

	@Override
	public final int[] get$v2() {
		return v2;
	}

	@Override
	public final void set$v2(int[] cv$value, boolean allocated$) {
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

	private final void drawValueSample11() {
		v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
	}

	private final void drawValueSample27(int i) {
		v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
	}

	private final void drawValueSample5() {
		v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
	}

	private final void inferSample11() {
		constrainedFlag$sample11 = false;
		int cv$numStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			int $var468 = weightings.length;
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var468) && (0 < $var468)) && (0.0 <= weightings[cv$valuePos])) && (weightings[cv$valuePos] <= 1.0))?Math.log(weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((0 < size)) {
				guard$sample11bernoulli52$global[0] = false;
				if(!guard$sample11bernoulli52$global[0]) {
					guard$sample11bernoulli52$global[0] = true;
					constrainedFlag$sample11 = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(fixedFlag$sample5) {
						if(fixedFlag$sample27) {
							double var51 = ((double)((v1 + cv$valuePos) + v2[1]) / v2[1]);
							cv$accumulatedConsumerProbabilities = (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							for(int index$sample27$130 = 0; index$sample27$130 < weightings.length; index$sample27$130 += 1) {
								double cv$probabilitySample27Value131 = distribution$sample27[0][index$sample27$130];
								double var51 = ((double)((v1 + cv$valuePos) + index$sample27$130) / index$sample27$130);
								if(((Math.log(cv$probabilitySample27Value131) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value131) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value131) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value131) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value131)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value131);
							}
						}
					} else {
						for(int index$sample5$124 = 0; index$sample5$124 < weightings.length; index$sample5$124 += 1) {
							double cv$probabilitySample5Value125 = distribution$sample5[index$sample5$124];
							if(fixedFlag$sample27) {
								double var51 = ((double)((index$sample5$124 + cv$valuePos) + v2[1]) / v2[1]);
								if(((Math.log(cv$probabilitySample5Value125) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value125) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value125) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value125) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value125)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value125);
							} else {
								for(int index$sample27$136 = 0; index$sample27$136 < weightings.length; index$sample27$136 += 1) {
									double cv$probabilitySample27Value137 = (cv$probabilitySample5Value125 * distribution$sample27[0][index$sample27$136]);
									double var51 = ((double)((index$sample5$124 + cv$valuePos) + index$sample27$136) / index$sample27$136);
									if(((Math.log(cv$probabilitySample27Value137) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value137) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value137) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value137) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value137)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
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
		if(constrainedFlag$sample11) {
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
	}

	private final void inferSample27(int i) {
		constrainedFlag$sample27[i] = false;
		int cv$numStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			int $var1125 = weightings.length;
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var1125) && (0 < $var1125)) && (0.0 <= weightings[cv$valuePos])) && (weightings[cv$valuePos] <= 1.0))?Math.log(weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			{
				int j = (i + 1);
				if((j < size))
					guard$sample27bernoulli52$global[j] = false;
			}
			guard$sample27bernoulli52$global[i] = false;
			int j = (i + 1);
			if(((j < size) && !guard$sample27bernoulli52$global[j])) {
				guard$sample27bernoulli52$global[j] = true;
				constrainedFlag$sample27[i] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if(fixedFlag$sample5) {
					if((i == j)) {
						double var51 = ((double)((v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
						cv$accumulatedConsumerProbabilities = (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY);
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						for(int index$sample27$121 = 0; index$sample27$121 < weightings.length; index$sample27$121 += 1) {
							double cv$probabilitySample27Value122 = distribution$sample27[j][index$sample27$121];
							double var51 = ((double)((v1 + cv$valuePos) + index$sample27$121) / index$sample27$121);
							if(((Math.log(cv$probabilitySample27Value122) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value122) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value122) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value122) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value122)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value122);
						}
					}
				} else {
					for(int index$sample5$115 = 0; index$sample5$115 < weightings.length; index$sample5$115 += 1) {
						double cv$probabilitySample5Value116 = distribution$sample5[index$sample5$115];
						if((i == j)) {
							double var51 = ((double)((index$sample5$115 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample5Value116) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value116) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value116) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value116) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value116)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value116);
						} else {
							for(int index$sample27$127 = 0; index$sample27$127 < weightings.length; index$sample27$127 += 1) {
								double cv$probabilitySample27Value128 = (cv$probabilitySample5Value116 * distribution$sample27[j][index$sample27$127]);
								double var51 = ((double)((index$sample5$115 + cv$valuePos) + index$sample27$127) / index$sample27$127);
								if(((Math.log(cv$probabilitySample27Value128) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value128) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value128) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value128) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value128)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
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
			if(!guard$sample27bernoulli52$global[i]) {
				guard$sample27bernoulli52$global[i] = true;
				constrainedFlag$sample27[i] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((0 == i)) {
					if(fixedFlag$sample5) {
						if(fixedFlag$sample11) {
							double var51 = ((double)((v1 + v2[0]) + cv$valuePos) / cv$valuePos);
							cv$accumulatedConsumerProbabilities = (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							for(int index$sample11$227 = 0; index$sample11$227 < weightings.length; index$sample11$227 += 1) {
								double cv$probabilitySample11Value228 = distribution$sample11[index$sample11$227];
								double var51 = ((double)((v1 + index$sample11$227) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample11Value228) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value228) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value228) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value228) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample11Value228)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value228);
							}
						}
					} else {
						for(int index$sample5$222 = 0; index$sample5$222 < weightings.length; index$sample5$222 += 1) {
							double cv$probabilitySample5Value223 = distribution$sample5[index$sample5$222];
							if(fixedFlag$sample11) {
								double var51 = ((double)((index$sample5$222 + v2[0]) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample5Value223) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value223) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value223) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value223) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value223)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value223);
							} else {
								for(int index$sample11$232 = 0; index$sample11$232 < weightings.length; index$sample11$232 += 1) {
									double cv$probabilitySample11Value233 = (cv$probabilitySample5Value223 * distribution$sample11[index$sample11$232]);
									double var51 = ((double)((index$sample5$222 + index$sample11$232) + cv$valuePos) / cv$valuePos);
									if(((Math.log(cv$probabilitySample11Value233) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value233) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value233) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value233) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample11Value233)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value233);
								}
							}
						}
					}
				}
				if(fixedFlag$sample5) {
					int index$i$266 = (i - 1);
					if(((0 <= index$i$266) && !(index$i$266 == i))) {
						for(int index$sample27$267 = 0; index$sample27$267 < weightings.length; index$sample27$267 += 1) {
							double cv$probabilitySample27Value268 = distribution$sample27[index$i$266][index$sample27$267];
							double var51 = ((double)((v1 + index$sample27$267) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample27Value268) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value268) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value268) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value268) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value268)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value268);
						}
					}
				} else {
					for(int index$sample5$261 = 0; index$sample5$261 < weightings.length; index$sample5$261 += 1) {
						double cv$probabilitySample5Value262 = distribution$sample5[index$sample5$261];
						int index$i$272 = (i - 1);
						if(((0 <= index$i$272) && !(index$i$272 == i))) {
							for(int index$sample27$273 = 0; index$sample27$273 < weightings.length; index$sample27$273 += 1) {
								double cv$probabilitySample27Value274 = (cv$probabilitySample5Value262 * distribution$sample27[index$i$272][index$sample27$273]);
								double var51 = ((double)((index$sample5$261 + index$sample27$273) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample27Value274) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value274) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value274) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value274) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value274)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
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
			if(!guard$sample27bernoulli52$global[i]) {
				guard$sample27bernoulli52$global[i] = true;
				constrainedFlag$sample27[i] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((0 == i)) {
					if(fixedFlag$sample5) {
						if(fixedFlag$sample11) {
							double var51 = ((double)((v1 + v2[0]) + cv$valuePos) / cv$valuePos);
							cv$accumulatedConsumerProbabilities = (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							for(int index$sample11$373 = 0; index$sample11$373 < weightings.length; index$sample11$373 += 1) {
								double cv$probabilitySample11Value374 = distribution$sample11[index$sample11$373];
								double var51 = ((double)((v1 + index$sample11$373) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample11Value374) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value374) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value374) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value374) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample11Value374)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value374);
							}
						}
					} else {
						for(int index$sample5$368 = 0; index$sample5$368 < weightings.length; index$sample5$368 += 1) {
							double cv$probabilitySample5Value369 = distribution$sample5[index$sample5$368];
							if(fixedFlag$sample11) {
								double var51 = ((double)((index$sample5$368 + v2[0]) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample5Value369) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value369) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value369) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value369) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value369)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value369);
							} else {
								for(int index$sample11$378 = 0; index$sample11$378 < weightings.length; index$sample11$378 += 1) {
									double cv$probabilitySample11Value379 = (cv$probabilitySample5Value369 * distribution$sample11[index$sample11$378]);
									double var51 = ((double)((index$sample5$368 + index$sample11$378) + cv$valuePos) / cv$valuePos);
									if(((Math.log(cv$probabilitySample11Value379) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value379) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value379) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value379) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample11Value379)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value379);
								}
							}
						}
					}
				}
				if(fixedFlag$sample5) {
					int index$i$412 = (i - 1);
					if(((0 <= index$i$412) && !(index$i$412 == i))) {
						for(int index$sample27$413 = 0; index$sample27$413 < weightings.length; index$sample27$413 += 1) {
							double cv$probabilitySample27Value414 = distribution$sample27[index$i$412][index$sample27$413];
							double var51 = ((double)((v1 + index$sample27$413) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample27Value414) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value414) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value414) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value414) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value414)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value414);
						}
					}
				} else {
					for(int index$sample5$407 = 0; index$sample5$407 < weightings.length; index$sample5$407 += 1) {
						double cv$probabilitySample5Value408 = distribution$sample5[index$sample5$407];
						int index$i$418 = (i - 1);
						if(((0 <= index$i$418) && !(index$i$418 == i))) {
							for(int index$sample27$419 = 0; index$sample27$419 < weightings.length; index$sample27$419 += 1) {
								double cv$probabilitySample27Value420 = (cv$probabilitySample5Value408 * distribution$sample27[index$i$418][index$sample27$419]);
								double var51 = ((double)((index$sample5$407 + index$sample27$419) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample27Value420) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value420) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value420) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value420) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value420)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[i]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
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
			cv$var27$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample27[i]) {
			double[] cv$localProbability = distribution$sample27[i];
			double cv$logSum;
			double cv$lseMax = cv$var27$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$var27$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$var27$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$var27$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var27$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void inferSample5() {
		constrainedFlag$sample5 = false;
		int cv$numStates = weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			int $var138 = weightings.length;
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var138) && (0 < $var138)) && (0.0 <= weightings[cv$valuePos])) && (weightings[cv$valuePos] <= 1.0))?Math.log(weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			for(int j = 0; j < size; j += 1) {
				constrainedFlag$sample5 = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((0 == j)) {
					if(fixedFlag$sample11) {
						if(fixedFlag$sample27) {
							double var51 = ((double)((cv$valuePos + v2[0]) + v2[1]) / v2[1]);
							cv$accumulatedConsumerProbabilities = (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							for(int index$sample27$177 = 0; index$sample27$177 < weightings.length; index$sample27$177 += 1) {
								double cv$probabilitySample27Value178 = distribution$sample27[0][index$sample27$177];
								double var51 = ((double)((cv$valuePos + v2[0]) + index$sample27$177) / index$sample27$177);
								if(((Math.log(cv$probabilitySample27Value178) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value178) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value178) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value178) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value178)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value178);
							}
						}
					} else {
						for(int index$sample11$171 = 0; index$sample11$171 < weightings.length; index$sample11$171 += 1) {
							double cv$probabilitySample11Value172 = distribution$sample11[index$sample11$171];
							if(fixedFlag$sample27) {
								double var51 = ((double)((cv$valuePos + index$sample11$171) + v2[1]) / v2[1]);
								if(((Math.log(cv$probabilitySample11Value172) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value172) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value172) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value172) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample11Value172)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value172);
							} else {
								for(int index$sample27$183 = 0; index$sample27$183 < weightings.length; index$sample27$183 += 1) {
									double cv$probabilitySample27Value184 = (cv$probabilitySample11Value172 * distribution$sample27[0][index$sample27$183]);
									double var51 = ((double)((cv$valuePos + index$sample11$171) + index$sample27$183) / index$sample27$183);
									if(((Math.log(cv$probabilitySample27Value184) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value184) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value184) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value184) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value184)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[0]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value184);
								}
							}
						}
					}
				}
				if(fixedFlag$sample27) {
					if((1 <= j)) {
						double var51 = ((double)((cv$valuePos + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
						if(((((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY);
							else
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY))) + 1)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
						}
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
					}
				} else {
					int i = (j - 1);
					if((0 <= i)) {
						for(int index$sample27$203 = 0; index$sample27$203 < weightings.length; index$sample27$203 += 1) {
							double cv$probabilitySample27Value204 = distribution$sample27[i][index$sample27$203];
							if((i == j)) {
								double var51 = ((double)((cv$valuePos + index$sample27$203) + index$sample27$203) / index$sample27$203);
								if(((Math.log(cv$probabilitySample27Value204) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value204) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value204) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value204) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value204)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value204);
							} else {
								for(int index$sample27$210 = 0; index$sample27$210 < weightings.length; index$sample27$210 += 1) {
									double cv$probabilitySample27Value211 = (cv$probabilitySample27Value204 * distribution$sample27[j][index$sample27$210]);
									double var51 = ((double)((cv$valuePos + index$sample27$203) + index$sample27$210) / index$sample27$210);
									if(((Math.log(cv$probabilitySample27Value211) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value211) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value211) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value211) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value211)) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
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

	private final void logProbabilityDistribution$sample11() {
		if(!fixedProbFlag$sample11) {
			if(fixedFlag$sample11) {
				int cv$sampleValue = v2[0];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[cv$sampleValue])) && (weightings[cv$sampleValue] <= 1.0))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[cv$sampleValue])) && (weightings[cv$sampleValue] <= 1.0))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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

	private final void logProbabilityDistribution$sample5() {
		if(!fixedProbFlag$sample5) {
			if(fixedFlag$sample5) {
				double cv$distributionAccumulator = ((((((0.0 <= v1) && (v1 < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[v1])) && (weightings[v1] <= 1.0))?Math.log(weightings[v1]):Double.NEGATIVE_INFINITY);
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
								cv$distributionAccumulator = (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY);
								cv$probabilityReached = 1.0;
							} else {
								for(int index$sample27$383 = 0; index$sample27$383 < weightings.length; index$sample27$383 += 1) {
									double cv$probabilitySample27Value384 = distribution$sample27[0][index$sample27$383];
									double var51 = ((double)((v1 + v2[0]) + index$sample27$383) / index$sample27$383);
									double cv$weightedProbability = (Math.log(cv$probabilitySample27Value384) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
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
									double var51 = ((double)((v1 + index$sample11$372) + v2[1]) / v2[1]);
									double cv$weightedProbability = (Math.log(cv$probabilitySample11Value373) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
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
										double var51 = ((double)((v1 + index$sample11$372) + index$sample27$389) / index$sample27$389);
										double cv$weightedProbability = (Math.log(cv$probabilitySample27Value390) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
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
									double cv$weightedProbability = (Math.log(cv$probabilitySample5Value368) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
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
										double var51 = ((double)((index$sample5$367 + v2[0]) + index$sample27$395) / index$sample27$395);
										double cv$weightedProbability = (Math.log(cv$probabilitySample27Value396) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
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
										double var51 = ((double)((index$sample5$367 + index$sample11$377) + v2[1]) / v2[1]);
										double cv$weightedProbability = (Math.log(cv$probabilitySample11Value378) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
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
											double var51 = ((double)((index$sample5$367 + index$sample11$377) + index$sample27$401) / index$sample27$401);
											double cv$weightedProbability = (Math.log(cv$probabilitySample27Value402) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
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
							double cv$weightedProbability = (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY);
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
									double var51 = ((double)((v1 + index$sample27$440) + index$sample27$440) / index$sample27$440);
									double cv$weightedProbability = (Math.log(cv$probabilitySample27Value441) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
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
										double var51 = ((double)((v1 + index$sample27$440) + index$sample27$453) / index$sample27$453);
										double cv$weightedProbability = (Math.log(cv$probabilitySample27Value454) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
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
								double cv$weightedProbability = (Math.log(cv$probabilitySample5Value435) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
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
										double var51 = ((double)((index$sample5$434 + index$sample27$446) + index$sample27$446) / index$sample27$446);
										double cv$weightedProbability = (Math.log(cv$probabilitySample27Value447) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
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
											double var51 = ((double)((index$sample5$434 + index$sample27$446) + index$sample27$460) / index$sample27$460);
											double cv$weightedProbability = (Math.log(cv$probabilitySample27Value461) + (((0.0 <= var51) && (var51 <= 1.0))?Math.log((cv$sampleValue?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY));
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
				logProbability$sample53[j] = cv$distributionAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample53 = ((fixedFlag$sample5 && fixedFlag$sample11) && fixedFlag$sample27);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample53[j]);
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample11() {
		if(!fixedProbFlag$sample11) {
			int cv$sampleValue = v2[0];
			double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[cv$sampleValue])) && (weightings[cv$sampleValue] <= 1.0))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[cv$sampleValue])) && (weightings[cv$sampleValue] <= 1.0))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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

	private final void logProbabilityValue$sample5() {
		if(!fixedProbFlag$sample5) {
			double cv$distributionAccumulator = ((((((0.0 <= v1) && (v1 < weightings.length)) && (0 < weightings.length)) && (0.0 <= weightings[v1])) && (weightings[v1] <= 1.0))?Math.log(weightings[v1]):Double.NEGATIVE_INFINITY);
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

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double var51 = ((double)((v1 + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]);
				double cv$distributionAccumulator = (((0.0 <= var51) && (var51 <= 1.0))?Math.log((v[j]?var51:(1.0 - var51))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample53[j] = cv$distributionAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample53 = ((fixedFlag$sample5 && fixedFlag$sample11) && fixedFlag$sample27);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample53[j]);
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
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
		constrainedFlag$sample27 = new boolean[length$value];
		logProbability$sample27 = new double[length$value];
		logProbability$sample53 = new double[length$value];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample11)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample27) {
			for(int i = 0; i < size; i += 1)
				v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		}
		for(int j = 0; j < size; j += 1)
			v[j] = DistributionSampling.sampleBernoulli(RNG$, ((double)((v1 + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]));
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample5) {
			for(int index$var4 = 0; index$var4 < weightings.length; index$var4 += 1)
				distribution$sample5[index$var4] = ((((0 < weightings.length) && (0.0 <= weightings[index$var4])) && (weightings[index$var4] <= 1.0))?weightings[index$var4]:0.0);
		}
		if(!fixedFlag$sample11) {
			for(int index$var10 = 0; index$var10 < weightings.length; index$var10 += 1)
				distribution$sample11[index$var10] = ((((0 < weightings.length) && (0.0 <= weightings[index$var10])) && (weightings[index$var10] <= 1.0))?weightings[index$var10]:0.0);
		}
		if(!fixedFlag$sample27) {
			for(int i = 0; i < size; i += 1) {
				double[] cv$distribution$sample27 = distribution$sample27[i];
				for(int index$var26 = 0; index$var26 < weightings.length; index$var26 += 1)
					cv$distribution$sample27[index$var26] = ((((0 < weightings.length) && (0.0 <= weightings[index$var26])) && (weightings[index$var26] <= 1.0))?weightings[index$var26]:0.0);
			}
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample11)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample27) {
			for(int i = 0; i < size; i += 1)
				v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		}
		for(int j = 0; j < size; j += 1)
			v[j] = DistributionSampling.sampleBernoulli(RNG$, ((double)((v1 + v2[j]) + v2[(j + 1)]) / v2[(j + 1)]));
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample11)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample27) {
			for(int i = 0; i < size; i += 1)
				v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample5)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample11)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample27) {
			for(int i = 0; i < size; i += 1)
				v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample5)
				inferSample5();
			if(!fixedFlag$sample11)
				inferSample11();
			if(!fixedFlag$sample27) {
				for(int i = 0; i < size; i += 1)
					inferSample27(i);
			}
		} else {
			if(!fixedFlag$sample27) {
				for(int i = (size - 1); i >= 0; i -= 1)
					inferSample27(i);
			}
			if(!fixedFlag$sample11)
				inferSample11();
			if(!fixedFlag$sample5)
				inferSample5();
		}
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample5)
			drawValueSample5();
		if(!constrainedFlag$sample11)
			drawValueSample11();
		for(int i = 0; i < size; i += 1) {
			if(!constrainedFlag$sample27[i])
				drawValueSample27(i);
		}
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
		if(!fixedProbFlag$sample53) {
			for(int j = 0; j < size; j += 1)
				logProbability$sample53[j] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		size = length$value;
		for(int index$constrainedFlag$sample27$1 = 0; index$constrainedFlag$sample27$1 < constrainedFlag$sample27.length; index$constrainedFlag$sample27$1 += 1)
			constrainedFlag$sample27[index$constrainedFlag$sample27$1] = true;
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