package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Deterministic2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Deterministic2$CoreInterface {
	private int[] a;
	private int[] b;
	private double[] cv$distributionAccumulator$var53;
	private double[][] cv$var29$countGlobal;
	private double[] cv$var54$stateProbabilityGlobal;
	private double[][] distribution$sample55;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample55 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample55 = false;
	private boolean fixedProbFlag$sample75 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$a;
	private double logProbability$b;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample55;
	private double[] logProbability$sample75;
	private double logProbability$var17;
	private double logProbability$var29;
	private double[] logProbability$var53;
	private double[] logProbability$var73;
	private double[][] m;
	private int n;
	private boolean system$gibbsForward = true;
	private double[] v;

	public Deterministic2$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final int[] get$a() {
		return a;
	}

	@Override
	public final void set$a(int[] cv$value) {
		a = cv$value;
		fixedProbFlag$sample55 = false;
		fixedProbFlag$sample75 = false;
	}

	@Override
	public final int[] get$b() {
		return b;
	}

	@Override
	public final double[][] get$distribution$sample55() {
		return distribution$sample55;
	}

	@Override
	public final void set$distribution$sample55(double[][] cv$value) {
		distribution$sample55 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	@Override
	public final void set$fixedFlag$sample29(boolean cv$value) {
		fixedFlag$sample29 = cv$value;
		fixedProbFlag$sample29 = (cv$value && fixedProbFlag$sample29);
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
		fixedProbFlag$sample75 = (cv$value && fixedProbFlag$sample75);
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
	public final void set$flipsMeasured(boolean[] cv$value) {
		flipsMeasured = cv$value;
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
	public final double get$logProbability$a() {
		return logProbability$a;
	}

	@Override
	public final double get$logProbability$b() {
		return logProbability$b;
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
	public final double[][] get$m() {
		return m;
	}

	@Override
	public final void set$m(double[][] cv$value) {
		m = cv$value;
		fixedProbFlag$sample29 = false;
		fixedProbFlag$sample55 = false;
	}

	@Override
	public final int get$n() {
		return n;
	}

	@Override
	public final void set$n(int cv$value) {
		n = cv$value;
	}

	@Override
	public final int get$states() {
		return 5;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityDistribution$sample55() {
		if(!fixedProbFlag$sample55) {
			if(fixedFlag$sample55) {
				double cv$accumulator = 0.0;
				for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int cv$sampleValue = a[i$var46];
					if((1 == i$var46)) {
						int var28 = b[1];
						if(((0 <= var28) && (var28 < 5))) {
							cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 5))?Math.log(m[0][cv$sampleValue]):Double.NEGATIVE_INFINITY);
							cv$probabilityReached = 1.0;
						}
					}
					if((2 <= i$var46)) {
						int var28 = b[i$var46];
						if(((0 <= var28) && (var28 < 5))) {
							double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 5))?Math.log(m[a[(i$var46 - 1)]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
					logProbability$var53[(i$var46 - 1)] = cv$distributionAccumulator;
					logProbability$sample55[(i$var46 - 1)] = cv$distributionAccumulator;
					if((i$var46 < (n - 1)))
						logProbability$b = (logProbability$b + cv$distributionAccumulator);
				}
				logProbability$a = (logProbability$a + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample55 = fixedFlag$sample29;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				double cv$sampleValue = logProbability$sample55[(i$var46 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var53[(i$var46 - 1)] = cv$sampleValue;
				if((fixedFlag$sample55 && (i$var46 < (n - 1))))
					logProbability$b = (logProbability$b + cv$sampleValue);
			}
			if(fixedFlag$sample55)
				logProbability$a = (logProbability$a + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample75() {
		if(!fixedProbFlag$sample75) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				boolean cv$sampleValue = flips[j];
				if(fixedFlag$sample55) {
					if((j < (n - 1))) {
						cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, (1 / a[(j + 1)]));
						cv$probabilityReached = 1.0;
					}
				} else {
					int i$var46 = (j + 1);
					if((i$var46 < n)) {
						for(int index$sample55$5 = 0; index$sample55$5 < 5; index$sample55$5 += 1) {
							double cv$probabilitySample55Value6 = distribution$sample55[(i$var46 - 1)][index$sample55$5];
							double cv$weightedProbability = (Math.log(cv$probabilitySample55Value6) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, (1 / a[(j + 1)])));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample55Value6);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var73[j] = cv$distributionAccumulator;
				logProbability$sample75[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample75 = fixedFlag$sample55;
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$rvAccumulator = logProbability$sample75[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var73[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample29() {
		if(!fixedProbFlag$sample29) {
			double cv$sampleAccumulator = 0.0;
			for(int var28 = 0; var28 < 5; var28 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var28], v, 5));
			logProbability$var17 = cv$sampleAccumulator;
			logProbability$var29 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample29 = fixedFlag$sample29;
		} else {
			logProbability$var17 = logProbability$var29;
			logProbability$m = (logProbability$m + logProbability$var29);
			logProbability$$model = (logProbability$$model + logProbability$var29);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var29);
		}
	}

	private final void logProbabilityValue$sample55() {
		if(!fixedProbFlag$sample55) {
			double cv$accumulator = 0.0;
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				int cv$sampleValue = a[i$var46];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 5))?Math.log(m[b[i$var46]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var53[(i$var46 - 1)] = cv$distributionAccumulator;
				logProbability$sample55[(i$var46 - 1)] = cv$distributionAccumulator;
				if((i$var46 < (n - 1)))
					logProbability$b = (logProbability$b + cv$distributionAccumulator);
			}
			logProbability$a = (logProbability$a + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample55 = (fixedFlag$sample55 && fixedFlag$sample29);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				double cv$sampleValue = logProbability$sample55[(i$var46 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var53[(i$var46 - 1)] = cv$sampleValue;
				if((i$var46 < (n - 1)))
					logProbability$b = (logProbability$b + cv$sampleValue);
			}
			logProbability$a = (logProbability$a + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample75() {
		if(!fixedProbFlag$sample75) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[j], (1 / a[(j + 1)]));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var73[j] = cv$distributionAccumulator;
				logProbability$sample75[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample75 = fixedFlag$sample55;
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$rvAccumulator = logProbability$sample75[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var73[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample29(int var28, int threadID$cv$var28, Rng RNG$) {
		double[] cv$countLocal = cv$var29$countGlobal[threadID$cv$var28];
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample55) {
			if(((1 < n) && (var28 == b[1])))
				cv$countLocal[a[1]] = (cv$countLocal[a[1]] + 1.0);
			for(int i$var46 = 2; i$var46 < n; i$var46 += 1) {
				if((var28 == b[i$var46]))
					cv$countLocal[a[i$var46]] = (cv$countLocal[a[i$var46]] + 1.0);
			}
		} else {
			if(((1 < n) && (var28 == b[1]))) {
				for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
					cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample55[0][cv$loopIndex]);
			}
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				if((var28 == b[i$var46])) {
					int index$i$27 = (i$var46 - 1);
					if((1 <= index$i$27)) {
						for(int index$sample55$28 = 0; index$sample55$28 < 5; index$sample55$28 += 1) {
							double cv$distributionProbability = distribution$sample55[(index$i$27 - 1)][index$sample55$28];
							for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
								cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample55[(i$var46 - 1)][cv$loopIndex] * cv$distributionProbability));
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var28], 5);
	}

	private final void sample55(int i$var46) {
		int cv$numNumStates = 0;
		if((1 == i$var46)) {
			int var28 = b[1];
			if(((0 <= var28) && (var28 < 5)))
				cv$numNumStates = 5;
		}
		if(fixedFlag$sample55) {
			if((2 <= i$var46)) {
				int var28 = b[i$var46];
				if(((0 <= var28) && (var28 < 5)))
					cv$numNumStates = 5;
			}
		} else {
			int index$i$6 = (i$var46 - 1);
			if(((1 <= index$i$6) && !(index$i$6 == i$var46))) {
				int var28 = b[i$var46];
				if(((0 <= var28) && (var28 < 5)))
					cv$numNumStates = 5;
			}
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == i$var46)) {
				int var28 = b[1];
				if(((0 <= var28) && (var28 < 5))) {
					cv$reachedDistributionSourceRV = 1.0;
					cv$stateProbabilityValue = (DistributionSampling.logProbabilityBernoulli(flips[0], (1 / cv$valuePos)) + Math.log(m[0][cv$valuePos]));
				}
			}
			int index$i$18 = (i$var46 - 1);
			if(((1 <= index$i$18) && !(index$i$18 == i$var46))) {
				for(int index$sample55$19 = 0; index$sample55$19 < 5; index$sample55$19 += 1) {
					double cv$probabilitySample55Value20 = distribution$sample55[(index$i$18 - 1)][index$sample55$19];
					int var28 = b[i$var46];
					if(((0 <= var28) && (var28 < 5))) {
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample55Value20);
						double cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityBernoulli(flips[(i$var46 - 1)], (1 / index$sample55$19)) + Math.log(cv$probabilitySample55Value20)) + Math.log(m[cv$valuePos][cv$valuePos]));
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
			int index$i$37_2 = (i$var46 + 1);
			if((index$i$37_2 < n)) {
				for(int cv$i = 0; cv$i < 5; cv$i += 1)
					cv$distributionAccumulator$var53[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				int var28 = b[index$i$37_2];
				if(((0 <= var28) && (var28 < 5))) {
					double scopeVariable$reachedSourceProbability = 0.0;
					if((1 == i$var46)) {
						int index$var28$42_1 = b[1];
						if(((0 <= index$var28$42_1) && (index$var28$42_1 < 5)))
							scopeVariable$reachedSourceProbability = 1.0;
					}
					int index$i$44 = (i$var46 - 1);
					if((((1 <= index$i$44) && !(index$i$44 == i$var46)) && !(index$i$44 == index$i$37_2))) {
						for(int index$sample55$45 = 0; index$sample55$45 < 5; index$sample55$45 += 1) {
							int index$var28$50_1 = b[i$var46];
							if(((0 <= index$var28$50_1) && (index$var28$50_1 < 5)))
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample55[(index$i$44 - 1)][index$sample55$45]);
						}
					}
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var53, scopeVariable$reachedSourceProbability, m[cv$valuePos], 5);
				}
				double[] cv$sampleDistribution = distribution$sample55[(index$i$37_2 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < 5; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var53[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			cv$var54$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		double[] cv$localProbability = distribution$sample55[(i$var46 - 1)];
		double cv$logSum;
		double cv$lseMax = cv$var54$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var54$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var54$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$var54$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var54$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	@Override
	public final void allocateScratch() {
		int cv$threadCount = threadCount();
		cv$var29$countGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var29$countGlobal[cv$index] = new double[5];
		cv$distributionAccumulator$var53 = new double[5];
		cv$var54$stateProbabilityGlobal = new double[5];
	}

	@Override
	public final void allocator() {
		v = new double[5];
		if(!fixedFlag$sample29) {
			m = new double[5][];
			for(int var28 = 0; var28 < 5; var28 += 1)
				m[var28] = new double[5];
		}
		if(!fixedFlag$sample55)
			a = new int[n];
		b = new int[n];
		flips = new boolean[n];
		distribution$sample55 = new double[(n - 1)][];
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
			distribution$sample55[(i$var46 - 1)] = new double[5];
		logProbability$var53 = new double[(n - 1)];
		logProbability$sample55 = new double[(n - 1)];
		logProbability$var73 = new double[n];
		logProbability$sample75 = new double[n];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample29)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 5, m[var28]);
				}
			);

		if(!fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				b[i$var46] = a[(i$var46 - 1)];
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], 5);
			}
		}
		parallelFor(RNG$, 0, n, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						flips[j] = DistributionSampling.sampleBernoulli(RNG$1, (1 / a[(j + 1)]));
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample29)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 5, m[var28]);
				}
			);

		if(!fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				double[] cv$distribution$sample55 = distribution$sample55[(i$var46 - 1)];
				for(int index$var53 = 0; index$var53 < 5; index$var53 += 1)
					cv$distribution$sample55[index$var53] = 0.0;
				if((1 == i$var46)) {
					int var28 = b[1];
					if(((0 <= var28) && (var28 < 5))) {
						double[] var52 = m[0];
						for(int index$var53 = 0; index$var53 < 5; index$var53 += 1)
							cv$distribution$sample55[index$var53] = (cv$distribution$sample55[index$var53] + var52[index$var53]);
					}
				}
				int index$i$4 = (i$var46 - 1);
				if((1 <= index$i$4)) {
					for(int index$sample55$5 = 0; index$sample55$5 < 5; index$sample55$5 += 1) {
						double cv$probabilitySample55Value6 = distribution$sample55[(index$i$4 - 1)][index$sample55$5];
						int var28 = b[i$var46];
						if(((0 <= var28) && (var28 < 5))) {
							double[] var52 = m[a[(i$var46 - 1)]];
							for(int index$var53 = 0; index$var53 < 5; index$var53 += 1)
								cv$distribution$sample55[index$var53] = (cv$distribution$sample55[index$var53] + (cv$probabilitySample55Value6 * var52[index$var53]));
						}
					}
				}
				double cv$var53$sum = 0.0;
				for(int index$var53 = 0; index$var53 < 5; index$var53 += 1)
					cv$var53$sum = (cv$var53$sum + cv$distribution$sample55[index$var53]);
				for(int index$var53 = 0; index$var53 < 5; index$var53 += 1)
					cv$distribution$sample55[index$var53] = (cv$distribution$sample55[index$var53] / cv$var53$sum);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample29)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 5, m[var28]);
				}
			);

		if(!fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				b[i$var46] = a[(i$var46 - 1)];
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], 5);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample29)
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
								sample29(var28, threadID$var28, RNG$1);
					}
				);

			if(!fixedFlag$sample55) {
				for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
					sample55(i$var46);
			}
		} else {
			if(!fixedFlag$sample55) {
				for(int i$var46 = (n - 1); i$var46 >= 1; i$var46 -= 1)
					sample55(i$var46);
			}
			if(!fixedFlag$sample29)
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
								sample29(var28, threadID$var28, RNG$1);
					}
				);

		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var14, int forEnd$i$var14, int threadID$i$var14, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var14 = forStart$i$var14; i$var14 < forEnd$i$var14; i$var14 += 1)
						v[i$var14] = 0.1;
			}
		);
		a[0] = 0;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var17 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$var29 = 0.0;
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
			logProbability$var53[(i$var46 - 1)] = 0.0;
		logProbability$b = 0.0;
		logProbability$a = 0.0;
		if(!fixedProbFlag$sample55) {
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
				logProbability$sample55[(i$var46 - 1)] = 0.0;
		}
		for(int j = 0; j < n; j += 1)
			logProbability$var73[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample75) {
			for(int j = 0; j < n; j += 1)
				logProbability$sample75[j] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample29)
			logProbabilityValue$sample29();
		logProbabilityValue$sample75();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample29();
		logProbabilityDistribution$sample55();
		logProbabilityDistribution$sample75();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample29();
		logProbabilityValue$sample55();
		logProbabilityValue$sample75();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample29)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 5, m[var28]);
				}
			);

		if(!fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				b[i$var46] = a[(i$var46 - 1)];
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], 5);
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = flipsMeasured[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		if(fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
				b[i$var46] = a[(i$var46 - 1)];
		}
	}

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
		     + "/**\n"
		     + " * A model for the fairness work.\n"
		     + " */\n"
		     + "public model Deterministic2(int n, boolean[] flipsMeasured) {\n"
		     + "    int states = 5;\n"
		     + "\n"
		     + "    double[] v = new double[states];\n"
		     + "    for(int i:[0..states))\n"
		     + "        v[i] = 0.1;\n"
		     + "    \n"
		     + "    double[][] m = dirichlet(v).sample(states);\n"
		     + "\n"
		     + "    int[] a = new int[n];\n"
		     + "    int[] b = new int[n];\n"
		     + "    a[0] = 0;\n"
		     + "    for(int i=1; i<n; i++) {\n"
		     + "        b[i] = a[i-1];\n"
		     + "        a[i] = categorical(m[b[i]]).sampleDistribution();\n"
		     + "    }\n"
		     + "    \n"
		     + "    boolean[] flips = new boolean[n];\n"
		     + "            \n"
		     + "    for(int j:[0..n))\n"
		     + "        flips[j] = bernoulli(1/a[j+1]).sample();\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}";
	}
}