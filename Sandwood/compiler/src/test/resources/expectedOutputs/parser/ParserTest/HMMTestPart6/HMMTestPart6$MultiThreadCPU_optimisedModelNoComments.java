package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart6$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart6$CoreInterface {
	private double[] bias;
	private double[][] cv$var16$countGlobal;
	private double[] cv$var33$stateProbabilityGlobal;
	private double[] cv$var50$stateProbabilityGlobal;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample26 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample52 = false;
	private boolean fixedFlag$sample70 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample52 = false;
	private boolean fixedProbFlag$sample70 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample52;
	private double[] logProbability$sample70;
	private double logProbability$st;
	private double logProbability$var11;
	private double logProbability$var16;
	private double logProbability$var20;
	private double logProbability$var25;
	private double logProbability$var32;
	private double logProbability$var33;
	private double[] logProbability$var49;
	private double[] logProbability$var67;
	private double[][] m;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart6$MultiThreadCPU(ExecutionTarget target) {
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
	public final boolean get$fixedFlag$sample17() {
		return fixedFlag$sample17;
	}

	@Override
	public final void set$fixedFlag$sample17(boolean cv$value) {
		fixedFlag$sample17 = cv$value;
		fixedProbFlag$sample17 = (cv$value && fixedProbFlag$sample17);
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
	}

	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	@Override
	public final void set$fixedFlag$sample26(boolean cv$value) {
		fixedFlag$sample26 = cv$value;
		fixedProbFlag$sample26 = (cv$value && fixedProbFlag$sample26);
		fixedProbFlag$sample70 = (cv$value && fixedProbFlag$sample70);
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		fixedFlag$sample35 = cv$value;
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
		fixedProbFlag$sample70 = (cv$value && fixedProbFlag$sample70);
	}

	@Override
	public final boolean get$fixedFlag$sample52() {
		return fixedFlag$sample52;
	}

	@Override
	public final void set$fixedFlag$sample52(boolean cv$value) {
		fixedFlag$sample52 = cv$value;
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
		fixedProbFlag$sample70 = (cv$value && fixedProbFlag$sample70);
	}

	@Override
	public final boolean get$fixedFlag$sample70() {
		return fixedFlag$sample70;
	}

	@Override
	public final void set$fixedFlag$sample70(boolean cv$value) {
		fixedFlag$sample70 = cv$value;
		fixedProbFlag$sample70 = (cv$value && fixedProbFlag$sample70);
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
	public final int get$samples() {
		return samples;
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
	public final int get$states() {
		return 2;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample17() {
		if(!fixedProbFlag$sample17) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityDirichlet(m[0], v) + DistributionSampling.logProbabilityDirichlet(m[1], v));
			logProbability$var11 = cv$sampleAccumulator;
			logProbability$var16 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample17 = fixedFlag$sample17;
		} else {
			logProbability$var11 = logProbability$var16;
			logProbability$m = (logProbability$m + logProbability$var16);
			logProbability$$model = (logProbability$$model + logProbability$var16);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var16);
		}
	}

	private final void logProbabilityValue$sample26() {
		if(!fixedProbFlag$sample26) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityBeta(bias[0], 1.0, 1.0) + DistributionSampling.logProbabilityBeta(bias[1], 1.0, 1.0));
			logProbability$var20 = cv$sampleAccumulator;
			logProbability$var25 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample26 = fixedFlag$sample26;
		} else {
			logProbability$var20 = logProbability$var25;
			logProbability$bias = (logProbability$bias + logProbability$var25);
			logProbability$$model = (logProbability$$model + logProbability$var25);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var25);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			int cv$sampleValue = st[0];
			double[] var31 = m[0];
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var31.length))?Math.log(var31[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var32 = cv$distributionAccumulator;
			logProbability$var33 = cv$distributionAccumulator;
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedFlag$sample17);
		} else {
			logProbability$var32 = logProbability$var33;
			logProbability$st = (logProbability$st + logProbability$var33);
			logProbability$$model = (logProbability$$model + logProbability$var33);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var33);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!fixedProbFlag$sample52) {
			double cv$accumulator = 0.0;
			for(int i$var39 = 4; i$var39 < (samples + 3); i$var39 += 1) {
				int cv$sampleValue = st[(i$var39 - 3)];
				double[] var48 = m[(1 - st[(i$var39 - 4)])];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var48.length))?Math.log(var48[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var49[(i$var39 - 4)] = cv$distributionAccumulator;
				logProbability$sample52[(i$var39 - 4)] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample52 = ((fixedFlag$sample52 && fixedFlag$sample17) && fixedFlag$sample35);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var39 = 4; i$var39 < (samples + 3); i$var39 += 1) {
				double cv$rvAccumulator = logProbability$sample52[(i$var39 - 4)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var49[(i$var39 - 4)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample70() {
		if(!fixedProbFlag$sample70) {
			double cv$accumulator = 0.0;
			for(int j = 5; j < (samples + 5); j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[(j - 5)], bias[(1 - st[(j - 5)])]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var67[(j - 5)] = cv$distributionAccumulator;
				logProbability$sample70[(j - 5)] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample70 = (((fixedFlag$sample70 && fixedFlag$sample26) && fixedFlag$sample35) && fixedFlag$sample52);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 5; j < (samples + 5); j += 1) {
				double cv$rvAccumulator = logProbability$sample70[(j - 5)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var67[(j - 5)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample17(int var15, int threadID$cv$var15, Rng RNG$) {
		double[] cv$countLocal = cv$var16$countGlobal[threadID$cv$var15];
		cv$countLocal[0] = 0.0;
		cv$countLocal[1] = 0.0;
		if((var15 == 0))
			cv$countLocal[st[0]] = (cv$countLocal[st[0]] + 1.0);
		for(int i$var39 = 4; i$var39 < (samples + 3); i$var39 += 1) {
			if((var15 == (1 - st[(i$var39 - 4)])))
				cv$countLocal[st[(i$var39 - 3)]] = (cv$countLocal[st[(i$var39 - 3)]] + 1.0);
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var15]);
	}

	private final void sample26(int var24, int threadID$cv$var24, Rng RNG$) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int j = 5; j < (samples + 5); j += 1) {
			if((var24 == (1 - st[(j - 5)]))) {
				cv$count = (cv$count + 1);
				if(flips[(j - 5)])
					cv$sum = (cv$sum + 1);
			}
		}
		bias[var24] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample35() {
		{
			double[] cv$temp$0$var31 = m[0];
			double cv$accumulatedProbabilities = ((0 < cv$temp$0$var31.length)?Math.log(cv$temp$0$var31[0]):Double.NEGATIVE_INFINITY);
			if((1 < samples)) {
				double[] var48 = m[1];
				cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < var48.length))?Math.log(var48[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if((0 < samples))
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0], bias[1]) + cv$accumulatedProbabilities);
			cv$var33$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		double[] cv$temp$0$var31 = m[0];
		double cv$accumulatedProbabilities = ((1 < cv$temp$0$var31.length)?Math.log(cv$temp$0$var31[1]):Double.NEGATIVE_INFINITY);
		if((1 < samples)) {
			double[] var48 = m[0];
			cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < var48.length))?Math.log(var48[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		if((0 < samples))
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0], bias[0]) + cv$accumulatedProbabilities);
		cv$var33$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = cv$var33$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var33$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var33$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var33$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var33$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var33$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var33$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$var33$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var33$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var33$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var33$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		st[0] = DistributionSampling.sampleCategorical(RNG$, cv$var33$stateProbabilityGlobal);
	}

	private final void sample52(int i$var39) {
		{
			st[(i$var39 - 3)] = 0;
			double[] cv$temp$0$var48 = m[(1 - st[(i$var39 - 4)])];
			double cv$accumulatedProbabilities = ((0 < cv$temp$0$var48.length)?Math.log(cv$temp$0$var48[0]):Double.NEGATIVE_INFINITY);
			int index$i$1_2 = (i$var39 + 1);
			if((index$i$1_2 < (samples + 3))) {
				double[] var48 = m[1];
				cv$accumulatedProbabilities = ((((0.0 <= st[(index$i$1_2 - 3)]) && (st[(index$i$1_2 - 3)] < var48.length))?Math.log(var48[st[(index$i$1_2 - 3)]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[(i$var39 - 3)], bias[1]) + cv$accumulatedProbabilities);
			cv$var50$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		st[(i$var39 - 3)] = 1;
		double[] cv$temp$0$var48 = m[(1 - st[(i$var39 - 4)])];
		double cv$accumulatedProbabilities = ((1 < cv$temp$0$var48.length)?Math.log(cv$temp$0$var48[1]):Double.NEGATIVE_INFINITY);
		int index$i$1_2 = (i$var39 + 1);
		if((index$i$1_2 < (samples + 3))) {
			double[] var48 = m[0];
			cv$accumulatedProbabilities = ((((0.0 <= st[(index$i$1_2 - 3)]) && (st[(index$i$1_2 - 3)] < var48.length))?Math.log(var48[st[(index$i$1_2 - 3)]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[(i$var39 - 3)], bias[0]) + cv$accumulatedProbabilities);
		cv$var50$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = cv$var50$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var50$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var50$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var50$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var50$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var50$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var50$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$var50$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var50$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var50$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var50$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		st[(i$var39 - 3)] = DistributionSampling.sampleCategorical(RNG$, cv$var50$stateProbabilityGlobal);
	}

	@Override
	public final void allocateScratch() {
		int cv$threadCount = threadCount();
		cv$var16$countGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var16$countGlobal[cv$index] = new double[2];
		cv$var33$stateProbabilityGlobal = new double[2];
		cv$var50$stateProbabilityGlobal = new double[2];
	}

	@Override
	public final void allocator() {
		v = new double[2];
		if(!setFlag$m) {
			m = new double[2][];
			m[0] = new double[2];
			m[1] = new double[2];
		}
		if(!setFlag$bias)
			bias = new double[2];
		if(!setFlag$st)
			st = new int[length$flipsMeasured];
		if(!setFlag$flips)
			flips = new boolean[length$flipsMeasured];
		logProbability$var49 = new double[(length$flipsMeasured - 1)];
		logProbability$sample52 = new double[(length$flipsMeasured - 1)];
		logProbability$var67 = new double[length$flipsMeasured];
		logProbability$sample70 = new double[length$flipsMeasured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample17)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var15]);
				}
			);

		if(!fixedFlag$sample26)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
							bias[var24] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample35)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample52) {
			for(int i$var39 = 4; i$var39 < (samples + 3); i$var39 += 1)
				st[(i$var39 - 3)] = DistributionSampling.sampleCategorical(RNG$, m[(1 - st[(i$var39 - 4)])]);
		}
		if(!fixedFlag$sample70)
			parallelFor(RNG$, 5, (samples + 5), 1,
				(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j = forStart$j; j < forEnd$j; j += 1)
							flips[(j - 5)] = DistributionSampling.sampleBernoulli(RNG$1, bias[(1 - st[(j - 5)])]);
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample17)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var15]);
				}
			);

		if(!fixedFlag$sample26)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
							bias[var24] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample35)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample52) {
			for(int i$var39 = 4; i$var39 < (samples + 3); i$var39 += 1)
				st[(i$var39 - 3)] = DistributionSampling.sampleCategorical(RNG$, m[(1 - st[(i$var39 - 4)])]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample17)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var15]);
				}
			);

		if(!fixedFlag$sample26)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
							bias[var24] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample35)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample52) {
			for(int i$var39 = 4; i$var39 < (samples + 3); i$var39 += 1)
				st[(i$var39 - 3)] = DistributionSampling.sampleCategorical(RNG$, m[(1 - st[(i$var39 - 4)])]);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample17)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1)
								sample17(var15, threadID$var15, RNG$1);
					}
				);

			if(!fixedFlag$sample26)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
								sample26(var24, threadID$var24, RNG$1);
					}
				);

			if(!fixedFlag$sample35)
				sample35();
			if(!fixedFlag$sample52) {
				for(int i$var39 = 4; i$var39 < (samples + 3); i$var39 += 1)
					sample52(i$var39);
			}
		} else {
			if(!fixedFlag$sample52) {
				for(int i$var39 = (samples + 2); i$var39 >= 4; i$var39 -= 1)
					sample52(i$var39);
			}
			if(!fixedFlag$sample35)
				sample35();
			if(!fixedFlag$sample26)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
								sample26(var24, threadID$var24, RNG$1);
					}
				);

			if(!fixedFlag$sample17)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1)
								sample17(var15, threadID$var15, RNG$1);
					}
				);

		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$i$var8, int forEnd$i$var8, int threadID$i$var8, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var8 = forStart$i$var8; i$var8 < forEnd$i$var8; i$var8 += 1)
						v[i$var8] = 0.1;
			}
		);
		samples = length$flipsMeasured;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var11 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$var16 = 0.0;
		logProbability$var20 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample26)
			logProbability$var25 = 0.0;
		logProbability$var32 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$var33 = 0.0;
		for(int i$var39 = 4; i$var39 < (samples + 3); i$var39 += 1)
			logProbability$var49[(i$var39 - 4)] = 0.0;
		if(!fixedProbFlag$sample52) {
			for(int i$var39 = 4; i$var39 < (samples + 3); i$var39 += 1)
				logProbability$sample52[(i$var39 - 4)] = 0.0;
		}
		for(int j = 5; j < (samples + 5); j += 1)
			logProbability$var67[(j - 5)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample70) {
			for(int j = 5; j < (samples + 5); j += 1)
				logProbability$sample70[(j - 5)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample17)
			logProbabilityValue$sample17();
		if(fixedFlag$sample26)
			logProbabilityValue$sample26();
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		if(fixedFlag$sample52)
			logProbabilityValue$sample52();
		logProbabilityValue$sample70();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample26();
		logProbabilityValue$sample35();
		logProbabilityValue$sample52();
		logProbabilityValue$sample70();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample26();
		logProbabilityValue$sample35();
		logProbabilityValue$sample52();
		logProbabilityValue$sample70();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample17)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var15]);
				}
			);

		if(!fixedFlag$sample26)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
							bias[var24] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample35)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample52) {
			for(int i$var39 = 4; i$var39 < (samples + 3); i$var39 += 1)
				st[(i$var39 - 3)] = DistributionSampling.sampleCategorical(RNG$, m[(1 - st[(i$var39 - 4)])]);
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
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart6(boolean[] flipsMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n        \n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        int[] st = new int[samples];\n\n        st[0] = categorical(m[0]).sample();\n\n        for(int i:[4..samples + 3))\n            st[i-3] = categorical(m[(states - 1) - st[i-4]]).sample();\n            \n        boolean[] flips = new boolean[samples];\n            \n        for(int j:[5..samples+5))\n            flips[j-5] = bernoulli(bias[(states - 1) - st[j-5]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
	}
}