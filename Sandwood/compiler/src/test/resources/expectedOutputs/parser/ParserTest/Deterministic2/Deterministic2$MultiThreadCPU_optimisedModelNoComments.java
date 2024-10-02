package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Deterministic2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Deterministic2$CoreInterface {
	private int[] a;
	private int[] b;
	private double[] cv$distributionAccumulator$var55;
	private double[][] cv$var31$countGlobal;
	private double[] cv$var56$stateProbabilityGlobal;
	private double[][] distribution$sample58;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedFlag$sample58 = false;
	private boolean fixedFlag$sample78 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean fixedProbFlag$sample58 = false;
	private boolean fixedProbFlag$sample78 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$a;
	private double logProbability$b;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample58;
	private double[] logProbability$sample78;
	private double logProbability$var19;
	private double logProbability$var31;
	private double[] logProbability$var55;
	private double[] logProbability$var75;
	private double[][] m;
	private int n;
	private boolean setFlag$a = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
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
		setFlag$a = true;
		fixedProbFlag$sample58 = false;
		fixedProbFlag$sample78 = false;
	}

	@Override
	public final int[] get$b() {
		return b;
	}

	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	@Override
	public final void set$fixedFlag$sample32(boolean cv$value) {
		fixedFlag$sample32 = cv$value;
		fixedProbFlag$sample32 = (cv$value && fixedProbFlag$sample32);
		fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
	}

	@Override
	public final boolean get$fixedFlag$sample58() {
		return fixedFlag$sample58;
	}

	@Override
	public final void set$fixedFlag$sample58(boolean cv$value) {
		fixedFlag$sample58 = cv$value;
		fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
		fixedProbFlag$sample78 = (cv$value && fixedProbFlag$sample78);
	}

	@Override
	public final boolean get$fixedFlag$sample78() {
		return fixedFlag$sample78;
	}

	@Override
	public final void set$fixedFlag$sample78(boolean cv$value) {
		fixedFlag$sample78 = cv$value;
		fixedProbFlag$sample78 = (cv$value && fixedProbFlag$sample78);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample78 = false;
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
		setFlag$m = true;
		fixedProbFlag$sample32 = false;
		fixedProbFlag$sample58 = false;
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

	private final void logProbabilityDistribution$sample58() {
		if(!fixedProbFlag$sample58) {
			if(fixedFlag$sample58) {
				double cv$accumulator = 0.0;
				for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int cv$sampleValue = a[i$var48];
					if((1 == i$var48)) {
						double[] var54 = m[0];
						cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var54.length))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY);
						cv$probabilityReached = 1.0;
					}
					if((2 <= i$var48)) {
						int traceTempVariable$var53$6_3 = a[(i$var48 - 1)];
						if(((0 <= traceTempVariable$var53$6_3) && (traceTempVariable$var53$6_3 < 5))) {
							double[] var54 = m[traceTempVariable$var53$6_3];
							double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var54.length))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
					logProbability$var55[(i$var48 - 1)] = cv$distributionAccumulator;
					logProbability$sample58[(i$var48 - 1)] = cv$distributionAccumulator;
					if((i$var48 < (n - 1)))
						logProbability$b = (logProbability$b + cv$distributionAccumulator);
				}
				logProbability$a = (logProbability$a + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample58 = fixedFlag$sample32;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				double cv$sampleValue = logProbability$sample58[(i$var48 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var55[(i$var48 - 1)] = cv$sampleValue;
				if((fixedFlag$sample58 && (i$var48 < (n - 1))))
					logProbability$b = (logProbability$b + cv$sampleValue);
			}
			if(fixedFlag$sample58)
				logProbability$a = (logProbability$a + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample78() {
		if(!fixedProbFlag$sample78) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				boolean cv$sampleValue = flips[j];
				if(fixedFlag$sample58) {
					if((j < (n - 1))) {
						cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, (1 / a[(j + 1)]));
						cv$probabilityReached = 1.0;
					}
				} else {
					int i$var48 = (j + 1);
					if((i$var48 < n)) {
						for(int index$sample58$5 = 0; index$sample58$5 < 5; index$sample58$5 += 1) {
							double cv$probabilitySample58Value6 = distribution$sample58[(i$var48 - 1)][index$sample58$5];
							double cv$weightedProbability = (Math.log(cv$probabilitySample58Value6) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, (1 / index$sample58$5)));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample58Value6);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var75[j] = cv$distributionAccumulator;
				logProbability$sample78[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample78 = (fixedFlag$sample78 && fixedFlag$sample58);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$rvAccumulator = logProbability$sample78[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var75[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample32() {
		if(!fixedProbFlag$sample32) {
			double cv$sampleAccumulator = 0.0;
			for(int var30 = 0; var30 < 5; var30 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var30], v));
			logProbability$var19 = cv$sampleAccumulator;
			logProbability$var31 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample32 = fixedFlag$sample32;
		} else {
			logProbability$var19 = logProbability$var31;
			logProbability$m = (logProbability$m + logProbability$var31);
			logProbability$$model = (logProbability$$model + logProbability$var31);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var31);
		}
	}

	private final void logProbabilityValue$sample58() {
		if(!fixedProbFlag$sample58) {
			double cv$accumulator = 0.0;
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				int cv$sampleValue = a[i$var48];
				double[] var54 = m[b[i$var48]];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var54.length))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var55[(i$var48 - 1)] = cv$distributionAccumulator;
				logProbability$sample58[(i$var48 - 1)] = cv$distributionAccumulator;
				if((i$var48 < (n - 1)))
					logProbability$b = (logProbability$b + cv$distributionAccumulator);
			}
			logProbability$a = (logProbability$a + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample58 = (fixedFlag$sample58 && fixedFlag$sample32);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				double cv$sampleValue = logProbability$sample58[(i$var48 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var55[(i$var48 - 1)] = cv$sampleValue;
				if((i$var48 < (n - 1)))
					logProbability$b = (logProbability$b + cv$sampleValue);
			}
			logProbability$a = (logProbability$a + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample78() {
		if(!fixedProbFlag$sample78) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[j], (1 / a[(j + 1)]));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var75[j] = cv$distributionAccumulator;
				logProbability$sample78[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample78 = (fixedFlag$sample78 && fixedFlag$sample58);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$rvAccumulator = logProbability$sample78[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var75[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample32(int var30, int threadID$cv$var30, Rng RNG$) {
		double[] cv$countLocal = cv$var31$countGlobal[threadID$cv$var30];
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample58) {
			if(((1 < n) && (var30 == 0)))
				cv$countLocal[a[1]] = (cv$countLocal[a[1]] + 1.0);
			for(int i$var48 = 2; i$var48 < n; i$var48 += 1) {
				if((var30 == a[(i$var48 - 1)]))
					cv$countLocal[a[i$var48]] = (cv$countLocal[a[i$var48]] + 1.0);
			}
		} else {
			if(((1 < n) && (var30 == 0))) {
				for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
					cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample58[0][cv$loopIndex]);
			}
			if((var30 < 5)) {
				for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
					int index$i$27 = (i$var48 - 1);
					if((1 <= index$i$27)) {
						double cv$distributionProbability = distribution$sample58[(index$i$27 - 1)][var30];
						for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample58[(i$var48 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var30]);
	}

	private final void sample58(int i$var48) {
		int cv$noStates = 0;
		if((1 == i$var48))
			cv$noStates = 5;
		if(fixedFlag$sample58) {
			if((2 <= i$var48)) {
				int traceTempVariable$var53$5_3 = a[(i$var48 - 1)];
				if(((0 <= traceTempVariable$var53$5_3) && (traceTempVariable$var53$5_3 < 5)))
					cv$noStates = 5;
			}
		} else {
			int index$i$6 = (i$var48 - 1);
			if(((1 <= index$i$6) && !(index$i$6 == i$var48)))
				cv$noStates = 5;
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == i$var48)) {
				cv$reachedDistributionSourceRV = 1.0;
				double[] cv$temp$0$var54 = m[0];
				cv$stateProbabilityValue = (DistributionSampling.logProbabilityBernoulli(flips[0], (1 / cv$valuePos)) + ((cv$valuePos < cv$temp$0$var54.length)?Math.log(cv$temp$0$var54[cv$valuePos]):Double.NEGATIVE_INFINITY));
			}
			int index$i$18 = (i$var48 - 1);
			if(((1 <= index$i$18) && !(index$i$18 == i$var48))) {
				for(int index$sample58$19 = 0; index$sample58$19 < 5; index$sample58$19 += 1) {
					double cv$probabilitySample58Value20 = distribution$sample58[(index$i$18 - 1)][index$sample58$19];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample58Value20);
					double[] var54 = m[index$sample58$19];
					double cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityBernoulli(flips[(i$var48 - 1)], (1 / cv$valuePos)) + Math.log(cv$probabilitySample58Value20)) + ((cv$valuePos < var54.length)?Math.log(var54[cv$valuePos]):Double.NEGATIVE_INFINITY));
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
			int index$i$37_2 = (i$var48 + 1);
			if((index$i$37_2 < n)) {
				for(int cv$i = 0; cv$i < 5; cv$i += 1)
					cv$distributionAccumulator$var55[cv$i] = 0.0;
				double scopeVariable$reachedSourceProbability = 0.0;
				if((1 == i$var48))
					scopeVariable$reachedSourceProbability = 1.0;
				int index$i$44 = (i$var48 - 1);
				if((((1 <= index$i$44) && !(index$i$44 == i$var48)) && !(index$i$44 == index$i$37_2))) {
					for(int index$sample58$45 = 0; index$sample58$45 < 5; index$sample58$45 += 1)
						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample58[(index$i$44 - 1)][index$sample58$45]);
				}
				DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var55, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				double[] cv$sampleDistribution = distribution$sample58[(index$i$37_2 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < 5; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var55[cv$i] / scopeVariable$reachedSourceProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * scopeVariable$reachedSourceProbability) + 1.0) - Math.min(scopeVariable$reachedSourceProbability, 1.0)));
			}
			cv$var56$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		double[] cv$localProbability = distribution$sample58[(i$var48 - 1)];
		double cv$logSum;
		double cv$lseMax = cv$var56$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var56$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var56$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$var56$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var56$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	@Override
	public final void allocateScratch() {
		int cv$threadCount = threadCount();
		cv$var31$countGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var31$countGlobal[cv$index] = new double[5];
		cv$distributionAccumulator$var55 = new double[5];
		cv$var56$stateProbabilityGlobal = new double[5];
	}

	@Override
	public final void allocator() {
		v = new double[5];
		if(!setFlag$m) {
			m = new double[5][];
			for(int var30 = 0; var30 < 5; var30 += 1)
				m[var30] = new double[5];
		}
		if(!setFlag$a)
			a = new int[n];
		b = new int[n];
		if(!setFlag$flips)
			flips = new boolean[n];
		distribution$sample58 = new double[(n - 1)][];
		for(int i$var48 = 1; i$var48 < n; i$var48 += 1)
			distribution$sample58[(i$var48 - 1)] = new double[5];
		logProbability$var55 = new double[(n - 1)];
		logProbability$sample58 = new double[(n - 1)];
		logProbability$var75 = new double[n];
		logProbability$sample78 = new double[n];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample32)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var30, int forEnd$var30, int threadID$var30, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var30 = forStart$var30; var30 < forEnd$var30; var30 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var30]);
				}
			);

		if(!fixedFlag$sample58) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				b[i$var48] = a[(i$var48 - 1)];
				a[i$var48] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var48]]);
			}
		}
		if(!fixedFlag$sample78)
			parallelFor(RNG$, 0, n, 1,
				(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j = forStart$j; j < forEnd$j; j += 1)
							flips[j] = DistributionSampling.sampleBernoulli(RNG$1, (1 / a[(j + 1)]));
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample32)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var30, int forEnd$var30, int threadID$var30, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var30 = forStart$var30; var30 < forEnd$var30; var30 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var30]);
				}
			);

		if(!fixedFlag$sample58) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				double[] cv$distribution$sample58 = distribution$sample58[(i$var48 - 1)];
				for(int index$var55 = 0; index$var55 < 5; index$var55 += 1)
					cv$distribution$sample58[index$var55] = 0.0;
				if((1 == i$var48)) {
					double[] var54 = m[0];
					for(int index$var55 = 0; index$var55 < 5; index$var55 += 1)
						cv$distribution$sample58[index$var55] = (cv$distribution$sample58[index$var55] + ((index$var55 < var54.length)?var54[index$var55]:0.0));
				}
				int index$i$4 = (i$var48 - 1);
				if((1 <= index$i$4)) {
					for(int index$sample58$5 = 0; index$sample58$5 < 5; index$sample58$5 += 1) {
						double cv$probabilitySample58Value6 = distribution$sample58[(index$i$4 - 1)][index$sample58$5];
						double[] var54 = m[index$sample58$5];
						for(int index$var55 = 0; index$var55 < 5; index$var55 += 1)
							cv$distribution$sample58[index$var55] = (cv$distribution$sample58[index$var55] + (cv$probabilitySample58Value6 * ((index$var55 < var54.length)?var54[index$var55]:0.0)));
					}
				}
				double cv$var55$sum = 0.0;
				for(int index$var55 = 0; index$var55 < 5; index$var55 += 1)
					cv$var55$sum = (cv$var55$sum + cv$distribution$sample58[index$var55]);
				for(int index$var55 = 0; index$var55 < 5; index$var55 += 1)
					cv$distribution$sample58[index$var55] = (cv$distribution$sample58[index$var55] / cv$var55$sum);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample32)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var30, int forEnd$var30, int threadID$var30, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var30 = forStart$var30; var30 < forEnd$var30; var30 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var30]);
				}
			);

		if(!fixedFlag$sample58) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				b[i$var48] = a[(i$var48 - 1)];
				a[i$var48] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var48]]);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample32)
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var30, int forEnd$var30, int threadID$var30, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var30 = forStart$var30; var30 < forEnd$var30; var30 += 1)
								sample32(var30, threadID$var30, RNG$1);
					}
				);

			if(!fixedFlag$sample58) {
				for(int i$var48 = 1; i$var48 < n; i$var48 += 1)
					sample58(i$var48);
			}
		} else {
			if(!fixedFlag$sample58) {
				for(int i$var48 = (n - 1); i$var48 >= 1; i$var48 -= 1)
					sample58(i$var48);
			}
			if(!fixedFlag$sample32)
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var30, int forEnd$var30, int threadID$var30, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var30 = forStart$var30; var30 < forEnd$var30; var30 += 1)
								sample32(var30, threadID$var30, RNG$1);
					}
				);

		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var16, int forEnd$i$var16, int threadID$i$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var16 = forStart$i$var16; i$var16 < forEnd$i$var16; i$var16 += 1)
						v[i$var16] = 0.1;
			}
		);
		a[0] = 0;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var19 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample32)
			logProbability$var31 = 0.0;
		for(int i$var48 = 1; i$var48 < n; i$var48 += 1)
			logProbability$var55[(i$var48 - 1)] = 0.0;
		logProbability$b = 0.0;
		logProbability$a = 0.0;
		if(!fixedProbFlag$sample58) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1)
				logProbability$sample58[(i$var48 - 1)] = 0.0;
		}
		for(int j = 0; j < n; j += 1)
			logProbability$var75[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample78) {
			for(int j = 0; j < n; j += 1)
				logProbability$sample78[j] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample32)
			logProbabilityValue$sample32();
		logProbabilityValue$sample78();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample32();
		logProbabilityDistribution$sample58();
		logProbabilityDistribution$sample78();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample32();
		logProbabilityValue$sample58();
		logProbabilityValue$sample78();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample32)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var30, int forEnd$var30, int threadID$var30, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var30 = forStart$var30; var30 < forEnd$var30; var30 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var30]);
				}
			);

		if(!fixedFlag$sample58) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1) {
				b[i$var48] = a[(i$var48 - 1)];
				a[i$var48] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var48]]);
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = flipsMeasured[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		if(setFlag$a) {
			for(int i$var48 = 1; i$var48 < n; i$var48 += 1)
				b[i$var48] = a[(i$var48 - 1)];
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