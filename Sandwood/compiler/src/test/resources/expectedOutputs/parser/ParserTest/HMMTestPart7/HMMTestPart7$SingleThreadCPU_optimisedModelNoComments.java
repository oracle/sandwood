package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMTestPart7$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMTestPart7$CoreInterface {
	private double[] bias;
	private boolean[] constrainedFlag$sample28;
	private boolean[] constrainedFlag$sample45;
	private boolean constrainedFlag$sample53 = true;
	private boolean[] constrainedFlag$sample71;
	private double[] cv$distributionAccumulator$var69;
	private double[] cv$var28$countGlobal;
	private double[] cv$var52$stateProbabilityGlobal;
	private double[] cv$var70$stateProbabilityGlobal;
	private double[] distribution$sample53;
	private double[][] distribution$sample71;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedFlag$sample71 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean fixedProbFlag$sample71 = false;
	private boolean fixedProbFlag$sample87 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample71;
	private double[] logProbability$sample87;
	private double logProbability$st;
	private double logProbability$var28;
	private double logProbability$var44;
	private double logProbability$var52;
	private double[][] m;
	private int samples;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart7$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value, boolean allocated$) {
		bias = cv$value;
		fixedProbFlag$sample45 = false;
		fixedProbFlag$sample87 = false;
	}

	@Override
	public final double[] get$distribution$sample53() {
		return distribution$sample53;
	}

	@Override
	public final void set$distribution$sample53(double[] cv$value, boolean allocated$) {
		distribution$sample53 = cv$value;
	}

	@Override
	public final double[][] get$distribution$sample71() {
		return distribution$sample71;
	}

	@Override
	public final void set$distribution$sample71(double[][] cv$value, boolean allocated$) {
		distribution$sample71 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value, boolean allocated$) {
		fixedFlag$sample28 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
				constrainedFlag$sample28[index$constrainedFlag$sample28$1] = cv$value;
		}
		fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
		fixedProbFlag$sample71 = (cv$value && fixedProbFlag$sample71);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value, boolean allocated$) {
		fixedFlag$sample45 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
				constrainedFlag$sample45[index$constrainedFlag$sample45$1] = cv$value;
		}
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
	}

	@Override
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	@Override
	public final void set$fixedFlag$sample53(boolean cv$value, boolean allocated$) {
		fixedFlag$sample53 = cv$value;
		constrainedFlag$sample53 = (cv$value || constrainedFlag$sample53);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
		fixedProbFlag$sample71 = (cv$value && fixedProbFlag$sample71);
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
	}

	@Override
	public final boolean get$fixedFlag$sample71() {
		return fixedFlag$sample71;
	}

	@Override
	public final void set$fixedFlag$sample71(boolean cv$value, boolean allocated$) {
		fixedFlag$sample71 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample71$1 = 0; index$constrainedFlag$sample71$1 < constrainedFlag$sample71.length; index$constrainedFlag$sample71$1 += 1)
				constrainedFlag$sample71[index$constrainedFlag$sample71$1] = cv$value;
		}
		fixedProbFlag$sample71 = (cv$value && fixedProbFlag$sample71);
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	@Override
	public final void set$flipsMeasured(boolean[] cv$value, boolean allocated$) {
		flipsMeasured = cv$value;
	}

	@Override
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int cv$value, boolean allocated$) {
		length$flipsMeasured = cv$value;
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
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	@Override
	public final double get$logProbability$m() {
		return logProbability$m;
	}

	@Override
	public final double get$logProbability$st() {
		return logProbability$st;
	}

	@Override
	public final double[][] get$m() {
		return m;
	}

	@Override
	public final void set$m(double[][] cv$value, boolean allocated$) {
		m = cv$value;
		fixedProbFlag$sample28 = false;
		fixedProbFlag$sample53 = false;
		fixedProbFlag$sample71 = false;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	@Override
	public final int[] get$st() {
		return st;
	}

	@Override
	public final void set$st(int[] cv$value, boolean allocated$) {
		st = cv$value;
		fixedProbFlag$sample53 = false;
		fixedProbFlag$sample71 = false;
		fixedProbFlag$sample87 = false;
	}

	@Override
	public final int get$states() {
		return 5;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void drawValueSample28(int var27) {
		DistributionSampling.sampleDirichlet(RNG$, v, 5, m[var27]);
	}

	private final void drawValueSample45(int var43) {
		bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	private final void drawValueSample53() {
		st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], 5);
	}

	private final void drawValueSample71(int i$var64) {
		st[i$var64] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], 5);
	}

	private final void inferSample28(int var27) {
		constrainedFlag$sample28[var27] = false;
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			cv$var28$countGlobal[cv$loopIndex] = 0.0;
		if(((var27 == 0) && fixedFlag$sample53)) {
			constrainedFlag$sample28[0] = true;
			cv$var28$countGlobal[st[0]] = (cv$var28$countGlobal[st[0]] + 1.0);
		}
		if(fixedFlag$sample71) {
			if((1 < samples)) {
				if(fixedFlag$sample53) {
					if((var27 == st[0])) {
						constrainedFlag$sample28[var27] = true;
						cv$var28$countGlobal[st[1]] = (cv$var28$countGlobal[st[1]] + 1.0);
					}
				} else {
					if((var27 < 5)) {
						constrainedFlag$sample28[var27] = true;
						cv$var28$countGlobal[st[1]] = (cv$var28$countGlobal[st[1]] + distribution$sample53[var27]);
					}
				}
			}
			for(int i$var64 = 2; i$var64 < samples; i$var64 += 1) {
				if((var27 == st[(i$var64 - 1)])) {
					constrainedFlag$sample28[var27] = true;
					cv$var28$countGlobal[st[i$var64]] = (cv$var28$countGlobal[st[i$var64]] + 1.0);
				}
			}
		}
		if(((var27 == 0) && !fixedFlag$sample53)) {
			for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
				cv$var28$countGlobal[cv$loopIndex] = (cv$var28$countGlobal[cv$loopIndex] + distribution$sample53[cv$loopIndex]);
		}
		if(!fixedFlag$sample71) {
			if((1 < samples)) {
				if(fixedFlag$sample53) {
					if((var27 == st[0])) {
						for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
							cv$var28$countGlobal[cv$loopIndex] = (cv$var28$countGlobal[cv$loopIndex] + distribution$sample71[0][cv$loopIndex]);
					}
				} else {
					if((var27 < 5)) {
						double cv$distributionProbability = distribution$sample53[var27];
						for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
							cv$var28$countGlobal[cv$loopIndex] = (cv$var28$countGlobal[cv$loopIndex] + (distribution$sample71[0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			if((var27 < 5)) {
				for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
					int index$i$45 = (i$var64 - 1);
					if((1 <= index$i$45)) {
						double cv$distributionProbability = distribution$sample71[(index$i$45 - 1)][var27];
						for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
							cv$var28$countGlobal[cv$loopIndex] = (cv$var28$countGlobal[cv$loopIndex] + (distribution$sample71[(i$var64 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		if(constrainedFlag$sample28[var27])
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var28$countGlobal, m[var27], 5);
	}

	private final void inferSample45(int var43) {
		constrainedFlag$sample45[var43] = false;
		double cv$sum = 0.0;
		double cv$count = 0.0;
		if((0 < samples)) {
			if(fixedFlag$sample53) {
				if((var43 == st[0])) {
					constrainedFlag$sample45[var43] = true;
					cv$count = 1.0;
					if(flips[0])
						cv$sum = 1.0;
				}
			} else {
				if((var43 < 5)) {
					double cv$probabilitySample53Value4 = distribution$sample53[var43];
					constrainedFlag$sample45[var43] = true;
					cv$count = cv$probabilitySample53Value4;
					if(flips[0])
						cv$sum = cv$probabilitySample53Value4;
				}
			}
		}
		for(int j = 1; j < samples; j += 1) {
			if(fixedFlag$sample71) {
				if((var43 == st[j])) {
					constrainedFlag$sample45[var43] = true;
					cv$count = (cv$count + 1.0);
					if(flips[j])
						cv$sum = (cv$sum + 1.0);
				}
			} else {
				if((var43 < 5)) {
					double cv$probabilitySample71Value13 = distribution$sample71[(j - 1)][var43];
					constrainedFlag$sample45[var43] = true;
					cv$count = (cv$count + cv$probabilitySample71Value13);
					if(flips[j])
						cv$sum = (cv$sum + cv$probabilitySample71Value13);
				}
			}
		}
		if(constrainedFlag$sample45[var43])
			bias[var43] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void inferSample53() {
		constrainedFlag$sample53 = false;
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double[] var50 = m[0];
			double cv$accumulatedProbabilities = (((0.0 <= var50[cv$valuePos]) && (var50[cv$valuePos] <= 1.0))?Math.log(var50[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((fixedFlag$sample71 && (1 < samples))) {
				constrainedFlag$sample53 = true;
				double[] var68 = m[cv$valuePos];
				cv$accumulatedProbabilities = ((((((0.0 <= st[1]) && (st[1] < 5)) && (0.0 <= var68[st[1]])) && (var68[st[1]] <= 1.0))?Math.log(var68[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if((0 < samples)) {
				constrainedFlag$sample53 = true;
				double var84 = bias[cv$valuePos];
				cv$accumulatedProbabilities = ((((0.0 <= var84) && (var84 <= 1.0))?Math.log((flips[0]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if((!fixedFlag$sample71 && (1 < samples))) {
				for(int cv$i = 0; cv$i < 5; cv$i += 1)
					cv$distributionAccumulator$var69[cv$i] = 0.0;
				DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var69, 1.0, m[cv$valuePos], 5);
				double[] cv$sampleDistribution = distribution$sample71[0];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < 5; cv$i += 1) {
					double cv$normalisedDistValue = cv$distributionAccumulator$var69[cv$i];
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log(cv$overlap);
			}
			cv$var52$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		if(constrainedFlag$sample53) {
			double cv$logSum;
			double cv$lseMax = cv$var52$stateProbabilityGlobal[0];
			{
				double cv$lseElementValue = cv$var52$stateProbabilityGlobal[1];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			{
				double cv$lseElementValue = cv$var52$stateProbabilityGlobal[2];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			{
				double cv$lseElementValue = cv$var52$stateProbabilityGlobal[3];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			double cv$lseElementValue = cv$var52$stateProbabilityGlobal[4];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < 5; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$var52$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
					distribution$sample53[cv$indexName] = 0.2;
			} else {
				for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
					distribution$sample53[cv$indexName] = Math.exp((cv$var52$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = 5; cv$indexName < cv$var52$stateProbabilityGlobal.length; cv$indexName += 1)
				distribution$sample53[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void inferSample71(int i$var64) {
		constrainedFlag$sample71[(i$var64 - 1)] = false;
		int cv$numStates = 0;
		if((1 == i$var64)) {
			if(fixedFlag$sample53) {
				int var27 = st[0];
				if(((0 <= var27) && (var27 < 5)))
					cv$numStates = 5;
			} else
				cv$numStates = 5;
		}
		int index$i$10 = (i$var64 - 1);
		if(((1 <= index$i$10) && !(index$i$10 == i$var64)))
			cv$numStates = 5;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == i$var64)) {
				if(fixedFlag$sample53) {
					int var27 = st[0];
					if(((0 <= var27) && (var27 < 5))) {
						cv$reachedDistributionSourceRV = 1.0;
						double[] var68 = m[st[0]];
						constrainedFlag$sample71[0] = true;
						double var84 = bias[cv$valuePos];
						cv$stateProbabilityValue = ((((0.0 <= var84) && (var84 <= 1.0))?Math.log((flips[1]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY) + (((0.0 <= var68[cv$valuePos]) && (var68[cv$valuePos] <= 1.0))?Math.log(var68[cv$valuePos]):Double.NEGATIVE_INFINITY));
					}
				} else {
					for(int index$sample53$18 = 0; index$sample53$18 < 5; index$sample53$18 += 1) {
						double cv$probabilitySample53Value19 = distribution$sample53[index$sample53$18];
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample53Value19);
						double[] var68 = m[index$sample53$18];
						constrainedFlag$sample71[0] = true;
						double var84 = bias[cv$valuePos];
						double cv$accumulatedProbabilities = (((((0.0 <= var84) && (var84 <= 1.0))?Math.log((flips[1]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY) + Math.log(cv$probabilitySample53Value19)) + (((0.0 <= var68[cv$valuePos]) && (var68[cv$valuePos] <= 1.0))?Math.log(var68[cv$valuePos]):Double.NEGATIVE_INFINITY));
						if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
						else {
							if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
								cv$stateProbabilityValue = cv$accumulatedProbabilities;
							else
								cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			int index$i$25 = (i$var64 - 1);
			if(((1 <= index$i$25) && !(index$i$25 == i$var64))) {
				for(int index$sample71$26 = 0; index$sample71$26 < 5; index$sample71$26 += 1) {
					double cv$probabilitySample71Value27 = distribution$sample71[(index$i$25 - 1)][index$sample71$26];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample71Value27);
					double[] var68 = m[index$sample71$26];
					constrainedFlag$sample71[(i$var64 - 1)] = true;
					double var84 = bias[index$sample71$26];
					double cv$accumulatedProbabilities = (((((0.0 <= var84) && (var84 <= 1.0))?Math.log((flips[i$var64]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY) + Math.log(cv$probabilitySample71Value27)) + (((0.0 <= var68[cv$valuePos]) && (var68[cv$valuePos] <= 1.0))?Math.log(var68[cv$valuePos]):Double.NEGATIVE_INFINITY));
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
			}
			int index$i$52_2 = (i$var64 + 1);
			if((index$i$52_2 < samples)) {
				for(int cv$i = 0; cv$i < 5; cv$i += 1)
					cv$distributionAccumulator$var69[cv$i] = 0.0;
				double scopeVariable$reachedSourceProbability = 0.0;
				if((1 == i$var64)) {
					if(fixedFlag$sample53) {
						int index$var27$61_1 = st[0];
						if(((0 <= index$var27$61_1) && (index$var27$61_1 < 5)))
							scopeVariable$reachedSourceProbability = 1.0;
					} else {
						for(int index$sample53$57 = 0; index$sample53$57 < 5; index$sample53$57 += 1)
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample53[index$sample53$57]);
					}
				}
				int index$i$64 = (i$var64 - 1);
				if((((1 <= index$i$64) && !(index$i$64 == i$var64)) && !(index$i$64 == index$i$52_2))) {
					for(int index$sample71$65 = 0; index$sample71$65 < 5; index$sample71$65 += 1)
						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample71[(index$i$64 - 1)][index$sample71$65]);
				}
				DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var69, scopeVariable$reachedSourceProbability, m[cv$valuePos], 5);
				double[] cv$sampleDistribution = distribution$sample71[(index$i$52_2 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < 5; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var69[cv$i] / scopeVariable$reachedSourceProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * scopeVariable$reachedSourceProbability) + 1.0) - Math.min(scopeVariable$reachedSourceProbability, 1.0)));
			}
			cv$var70$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		if(constrainedFlag$sample71[(i$var64 - 1)]) {
			double[] cv$localProbability = distribution$sample71[(i$var64 - 1)];
			double cv$logSum;
			double cv$lseMax = cv$var70$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$var70$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$var70$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$var70$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var70$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void logProbabilityDistribution$sample53() {
		if(!fixedProbFlag$sample53) {
			if(fixedFlag$sample53) {
				int cv$sampleValue = st[0];
				double[] var50 = m[0];
				double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var50[cv$sampleValue])) && (var50[cv$sampleValue] <= 1.0))?Math.log(var50[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				logProbability$var52 = cv$distributionAccumulator;
				logProbability$st = (logProbability$st + cv$distributionAccumulator);
				logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
				fixedProbFlag$sample53 = fixedFlag$sample28;
			}
		} else {
			if(fixedFlag$sample53)
				logProbability$st = (logProbability$st + logProbability$var52);
			logProbability$$model = (logProbability$$model + logProbability$var52);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var52);
		}
	}

	private final void logProbabilityDistribution$sample71() {
		if(!fixedProbFlag$sample71) {
			if(fixedFlag$sample71) {
				double cv$accumulator = 0.0;
				for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int cv$sampleValue = st[i$var64];
					if((1 == i$var64)) {
						if(fixedFlag$sample53) {
							int var27 = st[0];
							if(((0 <= var27) && (var27 < 5))) {
								double[] var68 = m[st[0]];
								cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY);
								cv$probabilityReached = 1.0;
							}
						} else {
							for(int index$sample53$4 = 0; index$sample53$4 < 5; index$sample53$4 += 1) {
								double cv$probabilitySample53Value5 = distribution$sample53[index$sample53$4];
								double[] var68 = m[index$sample53$4];
								double cv$weightedProbability = (Math.log(cv$probabilitySample53Value5) + (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample53Value5);
							}
						}
					}
					if((2 <= i$var64)) {
						int var27 = st[(i$var64 - 1)];
						if(((0 <= var27) && (var27 < 5))) {
							double[] var68 = m[st[(i$var64 - 1)]];
							double cv$weightedProbability = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$sample71[(i$var64 - 1)] = cv$distributionAccumulator;
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample71 = (fixedFlag$sample28 && fixedFlag$sample53);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample71[(i$var64 - 1)]);
			if(fixedFlag$sample71)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample87() {
		if(!fixedProbFlag$sample87) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				boolean cv$sampleValue = flips[j];
				if((0 == j)) {
					if(fixedFlag$sample53) {
						int var43 = st[0];
						if(((0 <= var43) && (var43 < 5))) {
							double var84 = bias[st[0]];
							cv$distributionAccumulator = (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY);
							cv$probabilityReached = 1.0;
						}
					} else {
						for(int index$sample53$3 = 0; index$sample53$3 < 5; index$sample53$3 += 1) {
							double cv$probabilitySample53Value4 = distribution$sample53[index$sample53$3];
							double var84 = bias[index$sample53$3];
							double cv$weightedProbability = (Math.log(cv$probabilitySample53Value4) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample53Value4);
						}
					}
				}
				if((1 <= j)) {
					if(fixedFlag$sample71) {
						int var43 = st[j];
						if(((0 <= var43) && (var43 < 5))) {
							double var84 = bias[st[j]];
							double cv$weightedProbability = (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY);
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
						for(int index$sample71$11 = 0; index$sample71$11 < 5; index$sample71$11 += 1) {
							double cv$probabilitySample71Value12 = distribution$sample71[(j - 1)][index$sample71$11];
							double var84 = bias[index$sample71$11];
							double cv$weightedProbability = (Math.log(cv$probabilitySample71Value12) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value12);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample87[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample87 = ((fixedFlag$sample45 && fixedFlag$sample53) && fixedFlag$sample71);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample87[j]);
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$sampleAccumulator = 0.0;
			for(int var27 = 0; var27 < 5; var27 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var27], v, 5));
			logProbability$var28 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			logProbability$m = (logProbability$m + logProbability$var28);
			logProbability$$model = (logProbability$$model + logProbability$var28);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var28);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!fixedProbFlag$sample45) {
			double cv$sampleAccumulator = 0.0;
			for(int var43 = 0; var43 < 5; var43 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(bias[var43], 1.0, 1.0));
			logProbability$var44 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample45 = fixedFlag$sample45;
		} else {
			logProbability$bias = (logProbability$bias + logProbability$var44);
			logProbability$$model = (logProbability$$model + logProbability$var44);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var44);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			int cv$sampleValue = st[0];
			double[] var50 = m[0];
			double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var50[cv$sampleValue])) && (var50[cv$sampleValue] <= 1.0))?Math.log(var50[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var52 = cv$distributionAccumulator;
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedFlag$sample28);
		} else {
			logProbability$st = (logProbability$st + logProbability$var52);
			logProbability$$model = (logProbability$$model + logProbability$var52);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var52);
		}
	}

	private final void logProbabilityValue$sample71() {
		if(!fixedProbFlag$sample71) {
			double cv$accumulator = 0.0;
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
				int cv$sampleValue = st[i$var64];
				double[] var68 = m[st[(i$var64 - 1)]];
				double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample71[(i$var64 - 1)] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample71 = ((fixedFlag$sample71 && fixedFlag$sample28) && fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample71[(i$var64 - 1)]);
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample87() {
		if(!fixedProbFlag$sample87) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double var84 = bias[st[j]];
				double cv$distributionAccumulator = (((0.0 <= var84) && (var84 <= 1.0))?Math.log((flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample87[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample87 = ((fixedFlag$sample45 && fixedFlag$sample53) && fixedFlag$sample71);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample87[j]);
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void allocateScratch() {
		cv$var28$countGlobal = new double[5];
		cv$distributionAccumulator$var69 = new double[5];
		cv$var52$stateProbabilityGlobal = new double[5];
		cv$var70$stateProbabilityGlobal = new double[5];
	}

	@Override
	public final void allocator() {
		v = new double[5];
		if(!fixedFlag$sample28) {
			m = new double[5][];
			for(int var27 = 0; var27 < 5; var27 += 1)
				m[var27] = new double[5];
		}
		if(!fixedFlag$sample45)
			bias = new double[5];
		if((!fixedFlag$sample53 || !fixedFlag$sample71))
			st = new int[length$flipsMeasured];
		flips = new boolean[length$flipsMeasured];
		distribution$sample53 = new double[5];
		distribution$sample71 = new double[(length$flipsMeasured - 1)][];
		for(int i$var64 = 1; i$var64 < length$flipsMeasured; i$var64 += 1)
			distribution$sample71[(i$var64 - 1)] = new double[5];
		constrainedFlag$sample45 = new boolean[5];
		constrainedFlag$sample28 = new boolean[5];
		constrainedFlag$sample71 = new boolean[(length$flipsMeasured - 1)];
		logProbability$sample71 = new double[(length$flipsMeasured - 1)];
		logProbability$sample87 = new double[length$flipsMeasured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample28) {
			for(int var27 = 0; var27 < 5; var27 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, 5, m[var27]);
		}
		if(!fixedFlag$sample45) {
			for(int var43 = 0; var43 < 5; var43 += 1)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], 5);
		if(!fixedFlag$sample71) {
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
				st[i$var64] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], 5);
		}
		for(int j = 0; j < samples; j += 1)
			flips[j] = DistributionSampling.sampleBernoulli(RNG$, bias[st[j]]);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample28) {
			for(int var27 = 0; var27 < 5; var27 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, 5, m[var27]);
		}
		if(!fixedFlag$sample45) {
			for(int var43 = 0; var43 < 5; var43 += 1)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53) {
			double[] var50 = m[0];
			for(int index$var51 = 0; index$var51 < 5; index$var51 += 1)
				distribution$sample53[index$var51] = (((0.0 <= var50[index$var51]) && (var50[index$var51] <= 1.0))?var50[index$var51]:0.0);
		}
		if(!fixedFlag$sample71) {
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
				double[] cv$distribution$sample71 = distribution$sample71[(i$var64 - 1)];
				for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
					cv$distribution$sample71[index$var69] = 0.0;
				if((1 == i$var64)) {
					if(fixedFlag$sample53) {
						int var27 = st[0];
						if(((0 <= var27) && (var27 < 5))) {
							double[] var68 = m[st[0]];
							for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
								cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (((0.0 <= var68[index$var69]) && (var68[index$var69] <= 1.0))?var68[index$var69]:0.0));
						}
					} else {
						for(int index$sample53$2 = 0; index$sample53$2 < 5; index$sample53$2 += 1) {
							double cv$probabilitySample53Value3 = distribution$sample53[index$sample53$2];
							double[] var68 = m[index$sample53$2];
							for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
								cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (cv$probabilitySample53Value3 * (((0.0 <= var68[index$var69]) && (var68[index$var69] <= 1.0))?var68[index$var69]:0.0)));
						}
					}
				}
				int index$i$9 = (i$var64 - 1);
				if((1 <= index$i$9)) {
					for(int index$sample71$10 = 0; index$sample71$10 < 5; index$sample71$10 += 1) {
						double cv$probabilitySample71Value11 = distribution$sample71[(index$i$9 - 1)][index$sample71$10];
						double[] var68 = m[index$sample71$10];
						for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
							cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (cv$probabilitySample71Value11 * (((0.0 <= var68[index$var69]) && (var68[index$var69] <= 1.0))?var68[index$var69]:0.0)));
					}
				}
				double cv$var69$sum = 0.0;
				for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
					cv$var69$sum = (cv$var69$sum + cv$distribution$sample71[index$var69]);
				for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
					cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] / cv$var69$sum);
			}
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample28) {
			for(int var27 = 0; var27 < 5; var27 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, 5, m[var27]);
		}
		if(!fixedFlag$sample45) {
			for(int var43 = 0; var43 < 5; var43 += 1)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], 5);
		if(!fixedFlag$sample71) {
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
				st[i$var64] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], 5);
		}
		for(int j = 0; j < samples; j += 1)
			flips[j] = DistributionSampling.sampleBernoulli(RNG$, bias[st[j]]);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample28) {
			for(int var27 = 0; var27 < 5; var27 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, 5, m[var27]);
		}
		if(!fixedFlag$sample45) {
			for(int var43 = 0; var43 < 5; var43 += 1)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], 5);
		if(!fixedFlag$sample71) {
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
				st[i$var64] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], 5);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample28) {
			for(int var27 = 0; var27 < 5; var27 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, 5, m[var27]);
		}
		if(!fixedFlag$sample45) {
			for(int var43 = 0; var43 < 5; var43 += 1)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], 5);
		if(!fixedFlag$sample71) {
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
				st[i$var64] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], 5);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample28) {
				for(int var27 = 0; var27 < 5; var27 += 1)
					inferSample28(var27);
			}
			if(!fixedFlag$sample45) {
				for(int var43 = 0; var43 < 5; var43 += 1)
					inferSample45(var43);
			}
			if(!fixedFlag$sample53)
				inferSample53();
			if(!fixedFlag$sample71) {
				for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
					inferSample71(i$var64);
			}
		} else {
			if(!fixedFlag$sample71) {
				for(int i$var64 = (samples - 1); i$var64 >= 1; i$var64 -= 1)
					inferSample71(i$var64);
			}
			if(!fixedFlag$sample53)
				inferSample53();
			if(!fixedFlag$sample45) {
				for(int var43 = 4; var43 >= 0; var43 -= 1)
					inferSample45(var43);
			}
			if(!fixedFlag$sample28) {
				for(int var27 = 4; var27 >= 0; var27 -= 1)
					inferSample28(var27);
			}
		}
		system$gibbsForward = !system$gibbsForward;
		for(int var27 = 0; var27 < 5; var27 += 1) {
			if(!constrainedFlag$sample28[var27])
				drawValueSample28(var27);
		}
		for(int var43 = 0; var43 < 5; var43 += 1) {
			if(!constrainedFlag$sample45[var43])
				drawValueSample45(var43);
		}
		if(!constrainedFlag$sample53)
			drawValueSample53();
		for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
			if(!constrainedFlag$sample71[(i$var64 - 1)])
				drawValueSample71(i$var64);
		}
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var28 = Double.NaN;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$var44 = Double.NaN;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$var52 = Double.NaN;
		if(!fixedProbFlag$sample71) {
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
				logProbability$sample71[(i$var64 - 1)] = Double.NaN;
		}
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample87) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample87[j] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		for(int i$var13 = 0; i$var13 < 5; i$var13 += 1)
			v[i$var13] = 0.1;
		samples = length$flipsMeasured;
		for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
			constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
		for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
			constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
		for(int index$constrainedFlag$sample71$1 = 0; index$constrainedFlag$sample71$1 < constrainedFlag$sample71.length; index$constrainedFlag$sample71$1 += 1)
			constrainedFlag$sample71[index$constrainedFlag$sample71$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityDistribution$sample53();
		logProbabilityDistribution$sample71();
		logProbabilityDistribution$sample87();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample71();
		logProbabilityValue$sample87();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = flipsMeasured[cv$index1];
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
		     + "model HMMTestPart7(boolean[] flipsMeasured) {\n"
		     + "        int states = 5;\n"
		     + "\n"
		     + "        double[] v = new double[states];\n"
		     + "        for(int i:[0..states))\n"
		     + "            v[i] = 0.1;\n"
		     + "        \n"
		     + "        double[][] m = dirichlet(v).sample(states);\n"
		     + "        double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "        int samples = flipsMeasured.length;\n"
		     + "        int[] st = new int[samples];\n"
		     + "\n"
		     + "        st[0] = categorical(m[0]).sampleDistribution();\n"
		     + "\n"
		     + "        for(int i:[1..samples))\n"
		     + "            st[i] = categorical(m[st[i-1]]).sampleDistribution();\n"
		     + "            \n"
		     + "        boolean[] flips = new boolean[samples];\n"
		     + "            \n"
		     + "        for(int j:[0..samples))\n"
		     + "            flips[j] = bernoulli(bias[st[j]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}