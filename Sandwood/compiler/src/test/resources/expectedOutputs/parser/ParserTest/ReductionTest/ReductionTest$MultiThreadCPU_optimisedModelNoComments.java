package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ReductionTest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements ReductionTest$CoreInterface {
	private double[] bias;
	private double[][] cv$var32$countGlobal;
	private double[] cv$var63$stateProbabilityGlobal;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample51 = false;
	private boolean fixedFlag$sample66 = false;
	private boolean fixedFlag$sample91 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample51 = false;
	private boolean fixedProbFlag$sample66 = false;
	private boolean fixedProbFlag$sample91 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample66;
	private double[] logProbability$sample91;
	private double logProbability$st;
	private double logProbability$var20;
	private double logProbability$var32;
	private double logProbability$var36;
	private double logProbability$var48;
	private double[] logProbability$var62;
	private double[] logProbability$var86;
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
		fixedProbFlag$sample51 = false;
		fixedProbFlag$sample91 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		fixedFlag$sample34 = cv$value;
		fixedProbFlag$sample34 = (cv$value && fixedProbFlag$sample34);
		fixedProbFlag$sample66 = (cv$value && fixedProbFlag$sample66);
	}

	@Override
	public final boolean get$fixedFlag$sample51() {
		return fixedFlag$sample51;
	}

	@Override
	public final void set$fixedFlag$sample51(boolean cv$value) {
		fixedFlag$sample51 = cv$value;
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
		fixedProbFlag$sample91 = (cv$value && fixedProbFlag$sample91);
	}

	@Override
	public final boolean get$fixedFlag$sample66() {
		return fixedFlag$sample66;
	}

	@Override
	public final void set$fixedFlag$sample66(boolean cv$value) {
		fixedFlag$sample66 = cv$value;
		fixedProbFlag$sample66 = (cv$value && fixedProbFlag$sample66);
		fixedProbFlag$sample91 = (cv$value && fixedProbFlag$sample91);
	}

	@Override
	public final boolean get$fixedFlag$sample91() {
		return fixedFlag$sample91;
	}

	@Override
	public final void set$fixedFlag$sample91(boolean cv$value) {
		fixedFlag$sample91 = cv$value;
		fixedProbFlag$sample91 = (cv$value && fixedProbFlag$sample91);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample91 = false;
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
		fixedProbFlag$sample34 = false;
		fixedProbFlag$sample66 = false;
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
		fixedProbFlag$sample66 = false;
		fixedProbFlag$sample91 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample34() {
		if(!fixedProbFlag$sample34) {
			double cv$sampleAccumulator = 0.0;
			for(int var31 = 0; var31 < noCats; var31 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var31], v));
			logProbability$var20 = cv$sampleAccumulator;
			logProbability$var32 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample34 = fixedFlag$sample34;
		} else {
			logProbability$var20 = logProbability$var32;
			logProbability$m = (logProbability$m + logProbability$var32);
			logProbability$$model = (logProbability$$model + logProbability$var32);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var32);
		}
	}

	private final void logProbabilityValue$sample51() {
		if(!fixedProbFlag$sample51) {
			double cv$sampleAccumulator = 0.0;
			for(int var47 = 0; var47 < noFlips; var47 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(bias[var47], 1.0, 1.0));
			logProbability$var36 = cv$sampleAccumulator;
			logProbability$var48 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample51 = fixedFlag$sample51;
		} else {
			logProbability$var36 = logProbability$var48;
			logProbability$bias = (logProbability$bias + logProbability$var48);
			logProbability$$model = (logProbability$$model + logProbability$var48);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var48);
		}
	}

	private final void logProbabilityValue$sample66() {
		if(!fixedProbFlag$sample66) {
			double cv$accumulator = 0.0;
			for(int i$var60 = 0; i$var60 < noCats; i$var60 += 1) {
				int cv$sampleValue = st[i$var60];
				double[] var61 = m[i$var60];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var61.length))?Math.log(var61[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var62[i$var60] = cv$distributionAccumulator;
				logProbability$sample66[i$var60] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample66)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample66 = (fixedFlag$sample66 && fixedFlag$sample34);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var60 = 0; i$var60 < noCats; i$var60 += 1) {
				double cv$rvAccumulator = logProbability$sample66[i$var60];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var62[i$var60] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample66)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample91() {
		if(!fixedProbFlag$sample91) {
			double cv$accumulator = 0.0;
			for(int j$var75 = 0; j$var75 < noFlips; j$var75 += 1) {
				int reduceVar$var84$6 = 0;
				for(int cv$reduction82Index = 0; cv$reduction82Index < noCats; cv$reduction82Index += 1)
					reduceVar$var84$6 = (reduceVar$var84$6 + st[cv$reduction82Index]);
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[j$var75], bias[reduceVar$var84$6]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var86[j$var75] = cv$distributionAccumulator;
				logProbability$sample91[j$var75] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample91 = ((fixedFlag$sample91 && fixedFlag$sample51) && fixedFlag$sample66);
		} else {
			double cv$accumulator = 0.0;
			for(int j$var75 = 0; j$var75 < noFlips; j$var75 += 1) {
				double cv$rvAccumulator = logProbability$sample91[j$var75];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var86[j$var75] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample34(int var31, int threadID$cv$var31, Rng RNG$) {
		double[] cv$countLocal = cv$var32$countGlobal[threadID$cv$var31];
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		cv$countLocal[st[var31]] = (cv$countLocal[st[var31]] + 1.0);
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var31]);
	}

	private final void sample51(int var47, int threadID$cv$var47, Rng RNG$) {
		int cv$sum = 0;
		int cv$count = 0;
		int reduceVar$var84$4 = 0;
		for(int cv$reduction82Index = 0; cv$reduction82Index < noCats; cv$reduction82Index += 1)
			reduceVar$var84$4 = (reduceVar$var84$4 + st[cv$reduction82Index]);
		if((var47 == reduceVar$var84$4)) {
			for(int j$var75 = 0; j$var75 < noFlips; j$var75 += 1) {
				cv$count = (cv$count + 1);
				if(flips[j$var75])
					cv$sum = (cv$sum + 1);
			}
		}
		bias[var47] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample66(int i$var60) {
		int cv$noStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			st[i$var60] = cv$valuePos;
			double[] cv$temp$0$var61 = m[i$var60];
			double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var61.length)?Math.log(cv$temp$0$var61[cv$valuePos]):Double.NEGATIVE_INFINITY);
			int reduceVar$var84$5 = 0;
			for(int cv$reduction382Index = 0; cv$reduction382Index < i$var60; cv$reduction382Index += 1)
				reduceVar$var84$5 = (reduceVar$var84$5 + st[cv$reduction382Index]);
			for(int cv$reduction382Index = (i$var60 + 1); cv$reduction382Index < noCats; cv$reduction382Index += 1)
				reduceVar$var84$5 = (reduceVar$var84$5 + st[cv$reduction382Index]);
			reduceVar$var84$5 = (cv$valuePos + reduceVar$var84$5);
			for(int j$var75 = 0; j$var75 < noFlips; j$var75 += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[j$var75], bias[reduceVar$var84$5]) + cv$accumulatedProbabilities);
			cv$var63$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double cv$logSum;
		double cv$lseMax = cv$var63$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var63$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var63$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$var63$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$var63$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var63$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var63$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var63$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st[i$var60] = DistributionSampling.sampleCategorical(RNG$, cv$var63$stateProbabilityGlobal);
	}

	@Override
	public final void allocateScratch() {
		int cv$max = 0;
		if((0 < noCats))
			cv$max = (length$flipsMeasured / noCats);
		int cv$threadCount = threadCount();
		cv$var32$countGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var32$countGlobal[cv$index] = new double[cv$max];
		cv$var63$stateProbabilityGlobal = new double[((0.0 <= noCats)?(length$flipsMeasured / noCats):((noCats < 0.0)?(length$flipsMeasured / noCats):length$flipsMeasured))];
	}

	@Override
	public final void allocator() {
		v = new double[(length$flipsMeasured / noCats)];
		if(!setFlag$m) {
			m = new double[noCats][];
			for(int var31 = 0; var31 < noCats; var31 += 1)
				m[var31] = new double[(length$flipsMeasured / noCats)];
		}
		if(!setFlag$bias)
			bias = new double[length$flipsMeasured];
		if(!setFlag$st)
			st = new int[noCats];
		if(!setFlag$flips)
			flips = new boolean[length$flipsMeasured];
		logProbability$var62 = new double[noCats];
		logProbability$sample66 = new double[noCats];
		logProbability$var86 = new double[length$flipsMeasured];
		logProbability$sample91 = new double[length$flipsMeasured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample34)
			parallelFor(RNG$, 0, noCats, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var31]);
				}
			);

		if(!fixedFlag$sample51)
			parallelFor(RNG$, 0, noFlips, 1,
				(int forStart$var47, int forEnd$var47, int threadID$var47, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var47 = forStart$var47; var47 < forEnd$var47; var47 += 1)
							bias[var47] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample66)
			parallelFor(RNG$, 0, noCats, 1,
				(int forStart$i$var60, int forEnd$i$var60, int threadID$i$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var60 = forStart$i$var60; i$var60 < forEnd$i$var60; i$var60 += 1)
							st[i$var60] = DistributionSampling.sampleCategorical(RNG$1, m[i$var60]);
				}
			);

		if(!fixedFlag$sample91)
			parallelFor(RNG$, 0, noFlips, 1,
				(int forStart$j$var75, int forEnd$j$var75, int threadID$j$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var75 = forStart$j$var75; j$var75 < forEnd$j$var75; j$var75 += 1) {
							int reduceVar$var84$7 = 0;
							for(int cv$reduction82Index = 0; cv$reduction82Index < noCats; cv$reduction82Index += 1)
								reduceVar$var84$7 = (reduceVar$var84$7 + st[cv$reduction82Index]);
							flips[j$var75] = DistributionSampling.sampleBernoulli(RNG$1, bias[reduceVar$var84$7]);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample34)
			parallelFor(RNG$, 0, noCats, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var31]);
				}
			);

		if(!fixedFlag$sample51)
			parallelFor(RNG$, 0, noFlips, 1,
				(int forStart$var47, int forEnd$var47, int threadID$var47, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var47 = forStart$var47; var47 < forEnd$var47; var47 += 1)
							bias[var47] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample66)
			parallelFor(RNG$, 0, noCats, 1,
				(int forStart$i$var60, int forEnd$i$var60, int threadID$i$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var60 = forStart$i$var60; i$var60 < forEnd$i$var60; i$var60 += 1)
							st[i$var60] = DistributionSampling.sampleCategorical(RNG$1, m[i$var60]);
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample34)
			parallelFor(RNG$, 0, noCats, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var31]);
				}
			);

		if(!fixedFlag$sample51)
			parallelFor(RNG$, 0, noFlips, 1,
				(int forStart$var47, int forEnd$var47, int threadID$var47, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var47 = forStart$var47; var47 < forEnd$var47; var47 += 1)
							bias[var47] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample66)
			parallelFor(RNG$, 0, noCats, 1,
				(int forStart$i$var60, int forEnd$i$var60, int threadID$i$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var60 = forStart$i$var60; i$var60 < forEnd$i$var60; i$var60 += 1)
							st[i$var60] = DistributionSampling.sampleCategorical(RNG$1, m[i$var60]);
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample34)
				parallelFor(RNG$, 0, noCats, 1,
					(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
								sample34(var31, threadID$var31, RNG$1);
					}
				);

			if(!fixedFlag$sample51)
				parallelFor(RNG$, 0, noFlips, 1,
					(int forStart$var47, int forEnd$var47, int threadID$var47, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var47 = forStart$var47; var47 < forEnd$var47; var47 += 1)
								sample51(var47, threadID$var47, RNG$1);
					}
				);

			if(!fixedFlag$sample66) {
				for(int i$var60 = 0; i$var60 < noCats; i$var60 += 1)
					sample66(i$var60);
			}
		} else {
			if(!fixedFlag$sample66) {
				for(int i$var60 = (noCats - 1); i$var60 >= 0; i$var60 -= 1)
					sample66(i$var60);
			}
			if(!fixedFlag$sample51)
				parallelFor(RNG$, 0, noFlips, 1,
					(int forStart$var47, int forEnd$var47, int threadID$var47, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var47 = forStart$var47; var47 < forEnd$var47; var47 += 1)
								sample51(var47, threadID$var47, RNG$1);
					}
				);

			if(!fixedFlag$sample34)
				parallelFor(RNG$, 0, noCats, 1,
					(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
								sample34(var31, threadID$var31, RNG$1);
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
			(int forStart$i$var17, int forEnd$i$var17, int threadID$i$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var17 = forStart$i$var17; i$var17 < forEnd$i$var17; i$var17 += 1)
						v[i$var17] = 0.1;
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var20 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample34)
			logProbability$var32 = 0.0;
		logProbability$var36 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample51)
			logProbability$var48 = 0.0;
		for(int i$var60 = 0; i$var60 < noCats; i$var60 += 1)
			logProbability$var62[i$var60] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample66) {
			for(int i$var60 = 0; i$var60 < noCats; i$var60 += 1)
				logProbability$sample66[i$var60] = 0.0;
		}
		for(int j$var75 = 0; j$var75 < noFlips; j$var75 += 1)
			logProbability$var86[j$var75] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample91) {
			for(int j$var75 = 0; j$var75 < noFlips; j$var75 += 1)
				logProbability$sample91[j$var75] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(fixedFlag$sample51)
			logProbabilityValue$sample51();
		if(fixedFlag$sample66)
			logProbabilityValue$sample66();
		logProbabilityValue$sample91();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample34();
		logProbabilityValue$sample51();
		logProbabilityValue$sample66();
		logProbabilityValue$sample91();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample34();
		logProbabilityValue$sample51();
		logProbabilityValue$sample66();
		logProbabilityValue$sample91();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample34)
			parallelFor(RNG$, 0, noCats, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var31]);
				}
			);

		if(!fixedFlag$sample51)
			parallelFor(RNG$, 0, noFlips, 1,
				(int forStart$var47, int forEnd$var47, int threadID$var47, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var47 = forStart$var47; var47 < forEnd$var47; var47 += 1)
							bias[var47] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample66)
			parallelFor(RNG$, 0, noCats, 1,
				(int forStart$i$var60, int forEnd$i$var60, int threadID$i$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var60 = forStart$i$var60; i$var60 < forEnd$i$var60; i$var60 += 1)
							st[i$var60] = DistributionSampling.sampleCategorical(RNG$1, m[i$var60]);
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
		     + "model ReductionTest(boolean[] flipsMeasured, int noCats) {\n"
		     + "    int noFlips = flipsMeasured.length;\n"
		     + "    int noStates = noFlips/noCats;\n"
		     + "    \n"
		     + "    double[] v = new double[noStates];\n"
		     + "    for(int i:[0..noStates))\n"
		     + "        v[i] = 0.1;\n"
		     + "    \n"
		     + "    double[][] m = dirichlet(v).sample(noCats);\n"
		     + "    \n"
		     + "    double[] bias = beta(1.0, 1.0).sample(noFlips);\n"
		     + "    \n"
		     + "    int[] st = new int[noCats];\n"
		     + "\n"
		     + "\n"
		     + "    for(int i:[0..noCats))\n"
		     + "        st[i] = categorical(m[i]).sample();\n"
		     + "            \n"
		     + "    boolean[] flips = new boolean[noFlips];\n"
		     + "            \n"
		     + "    for(int j:[0..noFlips))\n"
		     + "        flips[j] = bernoulli(bias[sum(st)]).sample();\n"
		     + "\n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "    \n"
		     + "    private int sum(int[] a) {\n"
		     + "        return reduce(a, 0, (i,j) -> {\n"
		     + "            return i + j;\n"
		     + "        });\n"
		     + "    }\n"
		     + "}";
	}
}