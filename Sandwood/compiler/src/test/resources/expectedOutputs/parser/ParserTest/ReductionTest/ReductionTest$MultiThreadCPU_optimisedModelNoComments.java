package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ReductionTest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements ReductionTest$CoreInterface {
	private double[] bias;
	private double[][] cv$var18$countGlobal;
	private double[] cv$var35$stateProbabilityGlobal;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample37 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample37 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample37;
	private double[] logProbability$sample53;
	private double logProbability$st;
	private double logProbability$var13;
	private double logProbability$var18;
	private double logProbability$var22;
	private double logProbability$var27;
	private double[] logProbability$var34;
	private double[] logProbability$var49;
	private double[][] m;
	private int noCats;
	private int noFlips;
	private int noStates;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public ReductionTest$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		setFlag$bias = true;
	}

	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	@Override
	public final void set$fixedFlag$sample20(boolean cv$value) {
		fixedFlag$sample20 = cv$value;
		fixedProbFlag$sample20 = (cv$value && fixedProbFlag$sample20);
		fixedProbFlag$sample37 = (cv$value && fixedProbFlag$sample37);
	}

	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	@Override
	public final void set$fixedFlag$sample29(boolean cv$value) {
		fixedFlag$sample29 = cv$value;
		fixedProbFlag$sample29 = (cv$value && fixedProbFlag$sample29);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$fixedFlag$sample37() {
		return fixedFlag$sample37;
	}

	@Override
	public final void set$fixedFlag$sample37(boolean cv$value) {
		fixedFlag$sample37 = cv$value;
		fixedProbFlag$sample37 = (cv$value && fixedProbFlag$sample37);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	@Override
	public final void set$fixedFlag$sample53(boolean cv$value) {
		fixedFlag$sample53 = cv$value;
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
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
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int cv$value) {
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
	public final void set$m(double[][] cv$value) {
		m = cv$value;
		setFlag$m = true;
	}

	@Override
	public final int get$noCats() {
		return noCats;
	}

	@Override
	public final void set$noCats(int cv$value) {
		noCats = cv$value;
	}

	@Override
	public final int get$noFlips() {
		return noFlips;
	}

	@Override
	public final int get$noStates() {
		return noStates;
	}

	@Override
	public final int[] get$st() {
		return st;
	}

	@Override
	public final void set$st(int[] cv$value) {
		st = cv$value;
		setFlag$st = true;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample20() {
		if(!fixedProbFlag$sample20) {
			double cv$sampleAccumulator = 0.0;
			for(int var17 = 0; var17 < noCats; var17 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var17], v));
			logProbability$var13 = cv$sampleAccumulator;
			logProbability$var18 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample20 = fixedFlag$sample20;
		} else {
			logProbability$var13 = logProbability$var18;
			logProbability$m = (logProbability$m + logProbability$var18);
			logProbability$$model = (logProbability$$model + logProbability$var18);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var18);
		}
	}

	private final void logProbabilityValue$sample29() {
		if(!fixedProbFlag$sample29) {
			double cv$sampleAccumulator = 0.0;
			for(int var26 = 0; var26 < noFlips; var26 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(bias[var26], 1.0, 1.0));
			logProbability$var22 = cv$sampleAccumulator;
			logProbability$var27 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample29 = fixedFlag$sample29;
		} else {
			logProbability$var22 = logProbability$var27;
			logProbability$bias = (logProbability$bias + logProbability$var27);
			logProbability$$model = (logProbability$$model + logProbability$var27);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var27);
		}
	}

	private final void logProbabilityValue$sample37() {
		if(!fixedProbFlag$sample37) {
			double cv$accumulator = 0.0;
			for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(st[i$var32], m[i$var32]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var34[i$var32] = cv$distributionAccumulator;
				logProbability$sample37[i$var32] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample37)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample37 = (fixedFlag$sample37 && fixedFlag$sample20);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1) {
				double cv$rvAccumulator = logProbability$sample37[i$var32];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var34[i$var32] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample37)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$accumulator = 0.0;
			for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1) {
				int reduceVar$var47$6 = 0;
				for(int cv$reduction46Index = 0; cv$reduction46Index < noCats; cv$reduction46Index += 1)
					reduceVar$var47$6 = (reduceVar$var47$6 + st[cv$reduction46Index]);
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[j$var40], bias[reduceVar$var47$6]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var49[j$var40] = cv$distributionAccumulator;
				logProbability$sample53[j$var40] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample53 = ((fixedFlag$sample53 && fixedFlag$sample29) && fixedFlag$sample37);
		} else {
			double cv$accumulator = 0.0;
			for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1) {
				double cv$rvAccumulator = logProbability$sample53[j$var40];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var49[j$var40] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample20(int var17, int threadID$cv$var17, Rng RNG$) {
		double[] cv$countLocal = cv$var18$countGlobal[threadID$cv$var17];
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		cv$countLocal[st[var17]] = (cv$countLocal[st[var17]] + 1.0);
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var17]);
	}

	private final void sample29(int var26, int threadID$cv$var26, Rng RNG$) {
		int cv$sum = 0;
		int cv$count = 0;
		int reduceVar$var47$4 = 0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < noCats; cv$reduction46Index += 1)
			reduceVar$var47$4 = (reduceVar$var47$4 + st[cv$reduction46Index]);
		if((var26 == reduceVar$var47$4)) {
			for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1) {
				cv$count = (cv$count + 1);
				if(flips[j$var40])
					cv$sum = (cv$sum + 1);
			}
		}
		bias[var26] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample37(int i$var32) {
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			st[i$var32] = cv$valuePos;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityCategorical(cv$valuePos, m[i$var32]);
			int reduceVar$var47$5 = 0;
			for(int cv$reduction242Index = 0; cv$reduction242Index < i$var32; cv$reduction242Index += 1)
				reduceVar$var47$5 = (reduceVar$var47$5 + st[cv$reduction242Index]);
			for(int cv$reduction242Index = (i$var32 + 1); cv$reduction242Index < noCats; cv$reduction242Index += 1)
				reduceVar$var47$5 = (reduceVar$var47$5 + st[cv$reduction242Index]);
			reduceVar$var47$5 = (cv$valuePos + reduceVar$var47$5);
			for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[j$var40], bias[reduceVar$var47$5]) + cv$accumulatedProbabilities);
			cv$var35$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double cv$logSum;
		double cv$lseMax = cv$var35$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var35$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var35$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var35$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var35$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var35$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var35$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$var35$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var35$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var35$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var35$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		st[i$var32] = DistributionSampling.sampleCategorical(RNG$, cv$var35$stateProbabilityGlobal);
	}

	@Override
	public final void allocateScratch() {
		int cv$max = 0;
		if((0 < noCats))
			cv$max = (length$flipsMeasured / noCats);
		int cv$threadCount = threadCount();
		cv$var18$countGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var18$countGlobal[cv$index] = new double[cv$max];
		cv$var35$stateProbabilityGlobal = new double[((0.0 <= noCats)?(length$flipsMeasured / noCats):((noCats < 0.0)?(length$flipsMeasured / noCats):length$flipsMeasured))];
	}

	@Override
	public final void allocator() {
		v = new double[(length$flipsMeasured / noCats)];
		if(!setFlag$m) {
			m = new double[noCats][];
			for(int var17 = 0; var17 < noCats; var17 += 1)
				m[var17] = new double[(length$flipsMeasured / noCats)];
		}
		if(!setFlag$bias)
			bias = new double[length$flipsMeasured];
		if(!setFlag$st)
			st = new int[noCats];
		if(!setFlag$flips)
			flips = new boolean[length$flipsMeasured];
		logProbability$var34 = new double[noCats];
		logProbability$sample37 = new double[noCats];
		logProbability$var49 = new double[length$flipsMeasured];
		logProbability$sample53 = new double[length$flipsMeasured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample20)
			parallelFor(RNG$, 0, noCats, 1,
				(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var17]);
				}
			);

		if(!fixedFlag$sample29)
			parallelFor(RNG$, 0, noFlips, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample37)
			parallelFor(RNG$, 0, noCats, 1,
				(int forStart$i$var32, int forEnd$i$var32, int threadID$i$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var32 = forStart$i$var32; i$var32 < forEnd$i$var32; i$var32 += 1)
							st[i$var32] = DistributionSampling.sampleCategorical(RNG$1, m[i$var32]);
				}
			);

		if(!fixedFlag$sample53)
			parallelFor(RNG$, 0, noFlips, 1,
				(int forStart$j$var40, int forEnd$j$var40, int threadID$j$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var40 = forStart$j$var40; j$var40 < forEnd$j$var40; j$var40 += 1) {
							int reduceVar$var47$7 = 0;
							for(int cv$reduction46Index = 0; cv$reduction46Index < noCats; cv$reduction46Index += 1)
								reduceVar$var47$7 = (reduceVar$var47$7 + st[cv$reduction46Index]);
							flips[j$var40] = DistributionSampling.sampleBernoulli(RNG$1, bias[reduceVar$var47$7]);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample20)
			parallelFor(RNG$, 0, noCats, 1,
				(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var17]);
				}
			);

		if(!fixedFlag$sample29)
			parallelFor(RNG$, 0, noFlips, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample37)
			parallelFor(RNG$, 0, noCats, 1,
				(int forStart$i$var32, int forEnd$i$var32, int threadID$i$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var32 = forStart$i$var32; i$var32 < forEnd$i$var32; i$var32 += 1)
							st[i$var32] = DistributionSampling.sampleCategorical(RNG$1, m[i$var32]);
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample20)
			parallelFor(RNG$, 0, noCats, 1,
				(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var17]);
				}
			);

		if(!fixedFlag$sample29)
			parallelFor(RNG$, 0, noFlips, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample37)
			parallelFor(RNG$, 0, noCats, 1,
				(int forStart$i$var32, int forEnd$i$var32, int threadID$i$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var32 = forStart$i$var32; i$var32 < forEnd$i$var32; i$var32 += 1)
							st[i$var32] = DistributionSampling.sampleCategorical(RNG$1, m[i$var32]);
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample20)
				parallelFor(RNG$, 0, noCats, 1,
					(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
								sample20(var17, threadID$var17, RNG$1);
					}
				);

			if(!fixedFlag$sample29)
				parallelFor(RNG$, 0, noFlips, 1,
					(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
								sample29(var26, threadID$var26, RNG$1);
					}
				);

			if(!fixedFlag$sample37) {
				for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1)
					sample37(i$var32);
			}
		} else {
			if(!fixedFlag$sample37) {
				for(int i$var32 = (noCats - 1); i$var32 >= 0; i$var32 -= 1)
					sample37(i$var32);
			}
			if(!fixedFlag$sample29)
				parallelFor(RNG$, 0, noFlips, 1,
					(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
								sample29(var26, threadID$var26, RNG$1);
					}
				);

			if(!fixedFlag$sample20)
				parallelFor(RNG$, 0, noCats, 1,
					(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
								sample20(var17, threadID$var17, RNG$1);
					}
				);

		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		noFlips = length$flipsMeasured;
		noStates = (length$flipsMeasured / noCats);
		parallelFor(RNG$, 0, (length$flipsMeasured / noCats), 1,
			(int forStart$i$var10, int forEnd$i$var10, int threadID$i$var10, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var10 = forStart$i$var10; i$var10 < forEnd$i$var10; i$var10 += 1)
						v[i$var10] = 0.1;
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var13 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$var18 = 0.0;
		logProbability$var22 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$var27 = 0.0;
		for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1)
			logProbability$var34[i$var32] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample37) {
			for(int i$var32 = 0; i$var32 < noCats; i$var32 += 1)
				logProbability$sample37[i$var32] = 0.0;
		}
		for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1)
			logProbability$var49[j$var40] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample53) {
			for(int j$var40 = 0; j$var40 < noFlips; j$var40 += 1)
				logProbability$sample53[j$var40] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		if(fixedFlag$sample29)
			logProbabilityValue$sample29();
		if(fixedFlag$sample37)
			logProbabilityValue$sample37();
		logProbabilityValue$sample53();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample29();
		logProbabilityValue$sample37();
		logProbabilityValue$sample53();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample29();
		logProbabilityValue$sample37();
		logProbabilityValue$sample53();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample20)
			parallelFor(RNG$, 0, noCats, 1,
				(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var17]);
				}
			);

		if(!fixedFlag$sample29)
			parallelFor(RNG$, 0, noFlips, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample37)
			parallelFor(RNG$, 0, noCats, 1,
				(int forStart$i$var32, int forEnd$i$var32, int threadID$i$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var32 = forStart$i$var32; i$var32 < forEnd$i$var32; i$var32 += 1)
							st[i$var32] = DistributionSampling.sampleCategorical(RNG$1, m[i$var32]);
				}
			);

		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = flipsMeasured[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel ReductionTest(boolean[] flipsMeasured, int noCats) {\n    int noFlips = flipsMeasured.length;\n    int noStates = noFlips/noCats;\n    \n    double[] v = new double[noStates];\n    for(int i:[0..noStates))\n        v[i] = 0.1;\n    \n    double[][] m = dirichlet(v).sample(noCats);\n    \n    double[] bias = beta(1.0, 1.0).sample(noFlips);\n    \n    int[] st = new int[noCats];\n\n\n    for(int i:[0..noCats))\n        st[i] = categorical(m[i]).sample();\n            \n    boolean[] flips = new boolean[noFlips];\n            \n    for(int j:[0..noFlips))\n        flips[j] = bernoulli(bias[sum(st)]).sample();\n\n    flips.observe(flipsMeasured);\n    \n    private int sum(int[] a) {\n        return reduce(a, 0, (i,j) -> {\n            return i + j;\n        });\n    }\n}";
	}
}