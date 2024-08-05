package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Deterministic2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Deterministic2$CoreInterface {
	private int[] a;
	private int[] b;
	private double[] cv$distributionAccumulator$var33;
	private double[][] cv$var17$countGlobal;
	private double[] cv$var34$stateProbabilityGlobal;
	private double[][] distribution$sample35;
	private boolean fixedFlag$sample18 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample48 = false;
	private boolean fixedProbFlag$sample18 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample48 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$a;
	private double logProbability$b;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample35;
	private double[] logProbability$sample48;
	private double logProbability$var12;
	private double logProbability$var17;
	private double[] logProbability$var33;
	private double[] logProbability$var46;
	private double[][] m;
	private int n;
	private boolean setFlag$a = false;
	private boolean setFlag$b = false;
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
	}

	@Override
	public final int[] get$b() {
		return b;
	}

	@Override
	public final void set$b(int[] cv$value) {
		b = cv$value;
		setFlag$b = true;
	}

	@Override
	public final boolean get$fixedFlag$sample18() {
		return fixedFlag$sample18;
	}

	@Override
	public final void set$fixedFlag$sample18(boolean cv$value) {
		fixedFlag$sample18 = cv$value;
		fixedProbFlag$sample18 = (cv$value && fixedProbFlag$sample18);
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		fixedFlag$sample35 = cv$value;
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
		fixedProbFlag$sample48 = (cv$value && fixedProbFlag$sample48);
	}

	@Override
	public final boolean get$fixedFlag$sample48() {
		return fixedFlag$sample48;
	}

	@Override
	public final void set$fixedFlag$sample48(boolean cv$value) {
		fixedFlag$sample48 = cv$value;
		fixedProbFlag$sample48 = (cv$value && fixedProbFlag$sample48);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
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

	private final void logProbabilityDistribution$sample35() {
		if(!fixedProbFlag$sample35) {
			if(fixedFlag$sample35) {
				double cv$accumulator = 0.0;
				for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int cv$sampleValue = a[i$var26];
					if((1 == i$var26)) {
						int var16 = b[1];
						if(((0 <= var16) && (var16 < 5))) {
							cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(cv$sampleValue, m[b[1]]);
							cv$probabilityReached = 1.0;
						}
					}
					if((2 <= i$var26)) {
						int var16 = b[i$var26];
						if(((0 <= var16) && (var16 < 5))) {
							double cv$weightedProbability = DistributionSampling.logProbabilityCategorical(cv$sampleValue, m[b[i$var26]]);
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
					logProbability$var33[(i$var26 - 1)] = cv$distributionAccumulator;
					logProbability$sample35[(i$var26 - 1)] = cv$distributionAccumulator;
					if((i$var26 < (n - 1)))
						logProbability$b = (logProbability$b + cv$distributionAccumulator);
				}
				logProbability$a = (logProbability$a + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample35 = fixedFlag$sample18;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				double cv$sampleValue = logProbability$sample35[(i$var26 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var33[(i$var26 - 1)] = cv$sampleValue;
				if((fixedFlag$sample35 && (i$var26 < (n - 1))))
					logProbability$b = (logProbability$b + cv$sampleValue);
			}
			if(fixedFlag$sample35)
				logProbability$a = (logProbability$a + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample48() {
		if(!fixedProbFlag$sample48) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				boolean cv$sampleValue = flips[j];
				if(fixedFlag$sample35) {
					if((j < (n - 1))) {
						cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, (1 / a[(j + 1)]));
						cv$probabilityReached = 1.0;
					}
				} else {
					int i$var26 = (j + 1);
					if((i$var26 < n)) {
						for(int index$sample35$5 = 0; index$sample35$5 < 5; index$sample35$5 += 1) {
							double cv$probabilitySample35Value6 = distribution$sample35[(i$var26 - 1)][index$sample35$5];
							double cv$weightedProbability = (Math.log(cv$probabilitySample35Value6) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, (1 / index$sample35$5)));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value6);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var46[j] = cv$distributionAccumulator;
				logProbability$sample48[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample48 = (fixedFlag$sample48 && fixedFlag$sample35);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$rvAccumulator = logProbability$sample48[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var46[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample18() {
		if(!fixedProbFlag$sample18) {
			double cv$sampleAccumulator = 0.0;
			for(int var16 = 0; var16 < 5; var16 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var16], v));
			logProbability$var12 = cv$sampleAccumulator;
			logProbability$var17 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample18 = fixedFlag$sample18;
		} else {
			logProbability$var12 = logProbability$var17;
			logProbability$m = (logProbability$m + logProbability$var17);
			logProbability$$model = (logProbability$$model + logProbability$var17);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var17);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double cv$accumulator = 0.0;
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(a[i$var26], m[b[i$var26]]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var33[(i$var26 - 1)] = cv$distributionAccumulator;
				logProbability$sample35[(i$var26 - 1)] = cv$distributionAccumulator;
				if((i$var26 < (n - 1)))
					logProbability$b = (logProbability$b + cv$distributionAccumulator);
			}
			logProbability$a = (logProbability$a + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedFlag$sample18);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				double cv$sampleValue = logProbability$sample35[(i$var26 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var33[(i$var26 - 1)] = cv$sampleValue;
				if((i$var26 < (n - 1)))
					logProbability$b = (logProbability$b + cv$sampleValue);
			}
			logProbability$a = (logProbability$a + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample48() {
		if(!fixedProbFlag$sample48) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[j], (1 / a[(j + 1)]));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var46[j] = cv$distributionAccumulator;
				logProbability$sample48[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample48 = (fixedFlag$sample48 && fixedFlag$sample35);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$rvAccumulator = logProbability$sample48[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var46[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample18(int var16, int threadID$cv$var16, Rng RNG$) {
		double[] cv$countLocal = cv$var17$countGlobal[threadID$cv$var16];
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample35) {
			if(((1 < n) && (var16 == b[1])))
				cv$countLocal[a[1]] = (cv$countLocal[a[1]] + 1.0);
			for(int i$var26 = 2; i$var26 < n; i$var26 += 1) {
				if((var16 == b[i$var26]))
					cv$countLocal[a[i$var26]] = (cv$countLocal[a[i$var26]] + 1.0);
			}
		} else {
			if(((1 < n) && (var16 == b[1]))) {
				for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
					cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample35[0][cv$loopIndex]);
			}
			if((var16 < 5)) {
				for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
					int index$i$27 = (i$var26 - 1);
					if((1 <= index$i$27)) {
						double cv$distributionProbability = distribution$sample35[(index$i$27 - 1)][var16];
						for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample35[(i$var26 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var16]);
	}

	private final void sample35(int i$var26) {
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == i$var26)) {
				int var16 = b[1];
				if(((0 <= var16) && (var16 < 5))) {
					cv$reachedDistributionSourceRV = 1.0;
					cv$stateProbabilityValue = (DistributionSampling.logProbabilityBernoulli(flips[0], (1 / cv$valuePos)) + DistributionSampling.logProbabilityCategorical(cv$valuePos, m[b[1]]));
				}
			}
			int index$i$5 = (i$var26 - 1);
			if(((1 <= index$i$5) && !(index$i$5 == i$var26))) {
				for(int index$sample35$6 = 0; index$sample35$6 < 5; index$sample35$6 += 1) {
					double cv$probabilitySample35Value7 = distribution$sample35[(index$i$5 - 1)][index$sample35$6];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample35Value7);
					double cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityBernoulli(flips[(i$var26 - 1)], (1 / cv$valuePos)) + Math.log(cv$probabilitySample35Value7)) + DistributionSampling.logProbabilityCategorical(cv$valuePos, m[index$sample35$6]));
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
			int index$i$24_2 = (i$var26 + 1);
			if((index$i$24_2 < n)) {
				for(int cv$i = 0; cv$i < 5; cv$i += 1)
					cv$distributionAccumulator$var33[cv$i] = 0.0;
				double scopeVariable$reachedSourceProbability = 0.0;
				if((1 == i$var26)) {
					int index$var16$29_1 = b[1];
					if(((0 <= index$var16$29_1) && (index$var16$29_1 < 5)))
						scopeVariable$reachedSourceProbability = 1.0;
				}
				int index$i$31 = (i$var26 - 1);
				if((((1 <= index$i$31) && !(index$i$31 == i$var26)) && !(index$i$31 == index$i$24_2))) {
					for(int index$sample35$32 = 0; index$sample35$32 < 5; index$sample35$32 += 1)
						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample35[(index$i$31 - 1)][index$sample35$32]);
				}
				DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var33, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				double[] cv$sampleDistribution = distribution$sample35[(index$i$24_2 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < 5; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var33[cv$i] / scopeVariable$reachedSourceProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * scopeVariable$reachedSourceProbability) + 1.0) - Math.min(scopeVariable$reachedSourceProbability, 1.0)));
			}
			cv$var34$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		double[] cv$localProbability = distribution$sample35[(i$var26 - 1)];
		double cv$logSum;
		double cv$lseMax = cv$var34$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var34$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var34$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var34$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var34$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var34$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$var34$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var34$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$var34$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
	}

	@Override
	public final void allocateScratch() {
		int cv$threadCount = threadCount();
		cv$var17$countGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var17$countGlobal[cv$index] = new double[5];
		cv$distributionAccumulator$var33 = new double[5];
		cv$var34$stateProbabilityGlobal = new double[5];
	}

	@Override
	public final void allocator() {
		v = new double[5];
		if(!setFlag$m) {
			m = new double[5][];
			for(int var16 = 0; var16 < 5; var16 += 1)
				m[var16] = new double[5];
		}
		if(!setFlag$a)
			a = new int[n];
		if(!setFlag$b)
			b = new int[n];
		if(!setFlag$flips)
			flips = new boolean[n];
		distribution$sample35 = new double[(n - 1)][];
		for(int i$var26 = 1; i$var26 < n; i$var26 += 1)
			distribution$sample35[(i$var26 - 1)] = new double[5];
		logProbability$var33 = new double[(n - 1)];
		logProbability$sample35 = new double[(n - 1)];
		logProbability$var46 = new double[n];
		logProbability$sample48 = new double[n];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample18)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var16]);
				}
			);

		if(!fixedFlag$sample35) {
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				b[i$var26] = a[(i$var26 - 1)];
				a[i$var26] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var26]]);
			}
		}
		if(!fixedFlag$sample48)
			parallelFor(RNG$, 0, n, 1,
				(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j = forStart$j; j < forEnd$j; j += 1)
							flips[j] = DistributionSampling.sampleBernoulli(RNG$1, (1 / a[(j + 1)]));
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample18)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var16]);
				}
			);

		if(!fixedFlag$sample35) {
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				double[] cv$distribution$sample35 = distribution$sample35[(i$var26 - 1)];
				for(int index$var33 = 0; index$var33 < 5; index$var33 += 1)
					cv$distribution$sample35[index$var33] = 0.0;
				if((1 == i$var26)) {
					int var16 = b[1];
					if(((0 <= var16) && (var16 < 5))) {
						double[] var32 = m[b[1]];
						for(int index$var33 = 0; index$var33 < 5; index$var33 += 1)
							cv$distribution$sample35[index$var33] = (cv$distribution$sample35[index$var33] + DistributionSampling.probabilityCategorical(index$var33, var32));
					}
				}
				int index$i$4 = (i$var26 - 1);
				if((1 <= index$i$4)) {
					for(int index$sample35$5 = 0; index$sample35$5 < 5; index$sample35$5 += 1) {
						double cv$probabilitySample35Value6 = distribution$sample35[(index$i$4 - 1)][index$sample35$5];
						double[] var32 = m[index$sample35$5];
						for(int index$var33 = 0; index$var33 < 5; index$var33 += 1)
							cv$distribution$sample35[index$var33] = (cv$distribution$sample35[index$var33] + (cv$probabilitySample35Value6 * DistributionSampling.probabilityCategorical(index$var33, var32)));
					}
				}
				double cv$var33$sum = 0.0;
				for(int index$var33 = 0; index$var33 < 5; index$var33 += 1)
					cv$var33$sum = (cv$var33$sum + cv$distribution$sample35[index$var33]);
				for(int index$var33 = 0; index$var33 < 5; index$var33 += 1)
					cv$distribution$sample35[index$var33] = (cv$distribution$sample35[index$var33] / cv$var33$sum);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample18)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var16]);
				}
			);

		if(!fixedFlag$sample35) {
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				b[i$var26] = a[(i$var26 - 1)];
				a[i$var26] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var26]]);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample18)
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1)
								sample18(var16, threadID$var16, RNG$1);
					}
				);

			if(!fixedFlag$sample35) {
				for(int i$var26 = 1; i$var26 < n; i$var26 += 1)
					sample35(i$var26);
			}
		} else {
			if(!fixedFlag$sample35) {
				for(int i$var26 = (n - 1); i$var26 >= 1; i$var26 -= 1)
					sample35(i$var26);
			}
			if(!fixedFlag$sample18)
				parallelFor(RNG$, 0, 5, 1,
					(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1)
								sample18(var16, threadID$var16, RNG$1);
					}
				);

		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var9, int forEnd$i$var9, int threadID$i$var9, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var9 = forStart$i$var9; i$var9 < forEnd$i$var9; i$var9 += 1)
						v[i$var9] = 0.1;
			}
		);
		a[0] = 0;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var12 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample18)
			logProbability$var17 = 0.0;
		for(int i$var26 = 1; i$var26 < n; i$var26 += 1)
			logProbability$var33[(i$var26 - 1)] = 0.0;
		logProbability$a = 0.0;
		logProbability$b = 0.0;
		if(!fixedProbFlag$sample35) {
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1)
				logProbability$sample35[(i$var26 - 1)] = 0.0;
		}
		for(int j = 0; j < n; j += 1)
			logProbability$var46[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample48) {
			for(int j = 0; j < n; j += 1)
				logProbability$sample48[j] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample18)
			logProbabilityValue$sample18();
		logProbabilityValue$sample48();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample18();
		logProbabilityDistribution$sample35();
		logProbabilityDistribution$sample48();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample18();
		logProbabilityValue$sample35();
		logProbabilityValue$sample48();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample18)
			parallelFor(RNG$, 0, 5, 1,
				(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var16]);
				}
			);

		if(!fixedFlag$sample35) {
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				b[i$var26] = a[(i$var26 - 1)];
				a[i$var26] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var26]]);
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
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1)
				b[i$var26] = a[(i$var26 - 1)];
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\n/**\n * A model for the fairness work.\n */\npublic model Deterministic2(int n, boolean[] flipsMeasured) {\n    int states = 5;\n\n    double[] v = new double[states];\n    for(int i:[0..states))\n        v[i] = 0.1;\n    \n    double[][] m = dirichlet(v).sample(states);\n\n    int[] a = new int[n];\n    int[] b = new int[n];\n    a[0] = 0;\n    for(int i=1; i<n; i++) {\n        b[i] = a[i-1];\n        a[i] = categorical(m[b[i]]).sampleDistribution();\n    }\n    \n    boolean[] flips = new boolean[n];\n            \n    for(int j:[0..n))\n        flips[j] = bernoulli(1/a[j+1]).sample();\n        flips.observe(flipsMeasured);\n}";
	}
}