package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMM_Paper$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMM_Paper$CoreInterface {
	private double[] bias;
	private double[][] cv$var16$countGlobal;
	private double[] cv$var19$countGlobal;
	private double[] cv$var33$stateProbabilityGlobal;
	private double[] cv$var43$stateProbabilityGlobal;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample21 = false;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample36 = false;
	private boolean fixedFlag$sample46 = false;
	private boolean fixedFlag$sample55 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample21 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample36 = false;
	private boolean fixedProbFlag$sample46 = false;
	private boolean fixedProbFlag$sample55 = false;
	private boolean[] flips;
	private double[] initialCoin;
	private int length$measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$initialCoin;
	private double logProbability$m;
	private double[] logProbability$sample46;
	private double[] logProbability$sample55;
	private double logProbability$st;
	private double logProbability$var11;
	private double logProbability$var16;
	private double logProbability$var18;
	private double logProbability$var22;
	private double logProbability$var27;
	private double logProbability$var32;
	private double logProbability$var33;
	private double[] logProbability$var42;
	private double[] logProbability$var51;
	private double[][] m;
	private boolean[] measured;
	private int nCoins;
	private int nFlips;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$initialCoin = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMM_Paper$MultiThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample29 = false;
		fixedProbFlag$sample55 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample17() {
		return fixedFlag$sample17;
	}

	@Override
	public final void set$fixedFlag$sample17(boolean cv$value) {
		fixedFlag$sample17 = cv$value;
		fixedProbFlag$sample17 = (cv$value && fixedProbFlag$sample17);
		fixedProbFlag$sample46 = (cv$value && fixedProbFlag$sample46);
	}

	@Override
	public final boolean get$fixedFlag$sample21() {
		return fixedFlag$sample21;
	}

	@Override
	public final void set$fixedFlag$sample21(boolean cv$value) {
		fixedFlag$sample21 = cv$value;
		fixedProbFlag$sample21 = (cv$value && fixedProbFlag$sample21);
		fixedProbFlag$sample36 = (cv$value && fixedProbFlag$sample36);
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
	public final boolean get$fixedFlag$sample36() {
		return fixedFlag$sample36;
	}

	@Override
	public final void set$fixedFlag$sample36(boolean cv$value) {
		fixedFlag$sample36 = cv$value;
		fixedProbFlag$sample36 = (cv$value && fixedProbFlag$sample36);
		fixedProbFlag$sample46 = (cv$value && fixedProbFlag$sample46);
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
	}

	@Override
	public final boolean get$fixedFlag$sample46() {
		return fixedFlag$sample46;
	}

	@Override
	public final void set$fixedFlag$sample46(boolean cv$value) {
		fixedFlag$sample46 = cv$value;
		fixedProbFlag$sample46 = (cv$value && fixedProbFlag$sample46);
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
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample55 = false;
	}

	@Override
	public final double[] get$initialCoin() {
		return initialCoin;
	}

	@Override
	public final void set$initialCoin(double[] cv$value) {
		initialCoin = cv$value;
		setFlag$initialCoin = true;
		fixedProbFlag$sample21 = false;
		fixedProbFlag$sample36 = false;
	}

	@Override
	public final int get$length$measured() {
		return length$measured;
	}

	@Override
	public final void set$length$measured(int cv$value) {
		length$measured = cv$value;
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
	public final double get$logProbability$initialCoin() {
		return logProbability$initialCoin;
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
		fixedProbFlag$sample17 = false;
		fixedProbFlag$sample46 = false;
	}

	@Override
	public final boolean[] get$measured() {
		return measured;
	}

	@Override
	public final void set$measured(boolean[] cv$value) {
		measured = cv$value;
	}

	@Override
	public final int get$nCoins() {
		return nCoins;
	}

	@Override
	public final void set$nCoins(int cv$value) {
		nCoins = cv$value;
	}

	@Override
	public final int get$nFlips() {
		return nFlips;
	}

	@Override
	public final int[] get$st() {
		return st;
	}

	@Override
	public final void set$st(int[] cv$value) {
		st = cv$value;
		setFlag$st = true;
		fixedProbFlag$sample36 = false;
		fixedProbFlag$sample46 = false;
		fixedProbFlag$sample55 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample17() {
		if(!fixedProbFlag$sample17) {
			double cv$sampleAccumulator = 0.0;
			for(int var15 = 0; var15 < nCoins; var15 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var15], v));
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

	private final void logProbabilityValue$sample21() {
		if(!fixedProbFlag$sample21) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(initialCoin, v);
			logProbability$var18 = cv$distributionAccumulator;
			logProbability$initialCoin = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample21 = fixedFlag$sample21;
		} else {
			logProbability$var18 = logProbability$initialCoin;
			logProbability$$model = (logProbability$$model + logProbability$initialCoin);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialCoin);
		}
	}

	private final void logProbabilityValue$sample29() {
		if(!fixedProbFlag$sample29) {
			double cv$sampleAccumulator = 0.0;
			for(int var26 = 0; var26 < nCoins; var26 += 1)
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

	private final void logProbabilityValue$sample36() {
		if(!fixedProbFlag$sample36) {
			int cv$sampleValue = st[0];
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialCoin.length))?Math.log(initialCoin[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var32 = cv$distributionAccumulator;
			logProbability$var33 = cv$distributionAccumulator;
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample36 = (fixedFlag$sample36 && fixedFlag$sample21);
		} else {
			logProbability$var32 = logProbability$var33;
			logProbability$st = (logProbability$st + logProbability$var33);
			logProbability$$model = (logProbability$$model + logProbability$var33);
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var33);
		}
	}

	private final void logProbabilityValue$sample46() {
		if(!fixedProbFlag$sample46) {
			double cv$accumulator = 0.0;
			for(int i = 1; i < nFlips; i += 1) {
				int cv$sampleValue = st[i];
				double[] var41 = m[st[(i - 1)]];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var41.length))?Math.log(var41[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var42[(i - 1)] = cv$distributionAccumulator;
				logProbability$sample46[(i - 1)] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample46 = ((fixedFlag$sample46 && fixedFlag$sample17) && fixedFlag$sample36);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 1; i < nFlips; i += 1) {
				double cv$rvAccumulator = logProbability$sample46[(i - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var42[(i - 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample55() {
		if(!fixedProbFlag$sample55) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < nFlips; j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[j], bias[st[j]]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var51[j] = cv$distributionAccumulator;
				logProbability$sample55[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample55 = (((fixedFlag$sample55 && fixedFlag$sample29) && fixedFlag$sample36) && fixedFlag$sample46);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < nFlips; j += 1) {
				double cv$rvAccumulator = logProbability$sample55[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var51[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample17(int var15, int threadID$cv$var15, Rng RNG$) {
		double[] cv$countLocal = cv$var16$countGlobal[threadID$cv$var15];
		for(int cv$loopIndex = 0; cv$loopIndex < nCoins; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		for(int i = 1; i < nFlips; i += 1) {
			if((var15 == st[(i - 1)]))
				cv$countLocal[st[i]] = (cv$countLocal[st[i]] + 1.0);
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var15]);
	}

	private final void sample21() {
		for(int cv$loopIndex = 0; cv$loopIndex < nCoins; cv$loopIndex += 1)
			cv$var19$countGlobal[cv$loopIndex] = 0.0;
		cv$var19$countGlobal[st[0]] = (cv$var19$countGlobal[st[0]] + 1.0);
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var19$countGlobal, initialCoin);
	}

	private final void sample29(int var26, int threadID$cv$var26, Rng RNG$) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int j = 0; j < nFlips; j += 1) {
			if((var26 == st[j])) {
				cv$count = (cv$count + 1);
				if(flips[j])
					cv$sum = (cv$sum + 1);
			}
		}
		bias[var26] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample36() {
		int cv$noStates = Math.max(0, nCoins);
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			st[0] = cv$valuePos;
			double cv$accumulatedProbabilities = ((cv$valuePos < initialCoin.length)?Math.log(initialCoin[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((1 < nFlips)) {
				double[] cv$temp$1$var41 = m[cv$valuePos];
				cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < cv$temp$1$var41.length))?Math.log(cv$temp$1$var41[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if((0 < nFlips))
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0], bias[cv$valuePos]) + cv$accumulatedProbabilities);
			cv$var33$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double cv$logSum;
		double cv$lseMax = cv$var33$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var33$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var33$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$var33$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$var33$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var33$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var33$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var33$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st[0] = DistributionSampling.sampleCategorical(RNG$, cv$var33$stateProbabilityGlobal);
	}

	private final void sample46(int i) {
		int cv$noStates = Math.max(0, nCoins);
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			st[i] = cv$valuePos;
			double[] cv$temp$0$var41 = m[st[(i - 1)]];
			double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var41.length)?Math.log(cv$temp$0$var41[cv$valuePos]):Double.NEGATIVE_INFINITY);
			int index$i$1_2 = (i + 1);
			if((index$i$1_2 < nFlips)) {
				double[] cv$temp$1$var41 = m[cv$valuePos];
				cv$accumulatedProbabilities = ((((0.0 <= st[index$i$1_2]) && (st[index$i$1_2] < cv$temp$1$var41.length))?Math.log(cv$temp$1$var41[st[index$i$1_2]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[i], bias[cv$valuePos]) + cv$accumulatedProbabilities);
			cv$var43$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double cv$logSum;
		double cv$lseMax = cv$var43$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var43$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var43$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$var43$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$var43$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var43$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var43$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var43$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st[i] = DistributionSampling.sampleCategorical(RNG$, cv$var43$stateProbabilityGlobal);
	}

	@Override
	public final void allocateScratch() {
		int cv$max = 0;
		if((0 < nCoins))
			cv$max = nCoins;
		int cv$threadCount = threadCount();
		cv$var16$countGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var16$countGlobal[cv$index] = new double[cv$max];
		cv$var19$countGlobal = new double[Math.max(0, nCoins)];
		cv$var33$stateProbabilityGlobal = new double[nCoins];
		cv$var43$stateProbabilityGlobal = new double[nCoins];
	}

	@Override
	public final void allocator() {
		v = new double[nCoins];
		if(!setFlag$m) {
			m = new double[nCoins][];
			for(int var15 = 0; var15 < nCoins; var15 += 1)
				m[var15] = new double[nCoins];
		}
		if(!setFlag$initialCoin)
			initialCoin = new double[nCoins];
		if(!setFlag$bias)
			bias = new double[nCoins];
		if(!setFlag$st)
			st = new int[length$measured];
		if(!setFlag$flips)
			flips = new boolean[length$measured];
		logProbability$var42 = new double[(length$measured - 1)];
		logProbability$sample46 = new double[(length$measured - 1)];
		logProbability$var51 = new double[length$measured];
		logProbability$sample55 = new double[length$measured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample17)
			parallelFor(RNG$, 0, nCoins, 1,
				(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var15]);
				}
			);

		if(!fixedFlag$sample21)
			DistributionSampling.sampleDirichlet(RNG$, v, initialCoin);
		if(!fixedFlag$sample29)
			parallelFor(RNG$, 0, nCoins, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample36)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialCoin);
		if(!fixedFlag$sample46) {
			for(int i = 1; i < nFlips; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]]);
		}
		if(!fixedFlag$sample55)
			parallelFor(RNG$, 0, nFlips, 1,
				(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j = forStart$j; j < forEnd$j; j += 1)
							flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[st[j]]);
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample17)
			parallelFor(RNG$, 0, nCoins, 1,
				(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var15]);
				}
			);

		if(!fixedFlag$sample21)
			DistributionSampling.sampleDirichlet(RNG$, v, initialCoin);
		if(!fixedFlag$sample29)
			parallelFor(RNG$, 0, nCoins, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample36)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialCoin);
		if(!fixedFlag$sample46) {
			for(int i = 1; i < nFlips; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample17)
			parallelFor(RNG$, 0, nCoins, 1,
				(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var15]);
				}
			);

		if(!fixedFlag$sample21)
			DistributionSampling.sampleDirichlet(RNG$, v, initialCoin);
		if(!fixedFlag$sample29)
			parallelFor(RNG$, 0, nCoins, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample36)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialCoin);
		if(!fixedFlag$sample46) {
			for(int i = 1; i < nFlips; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]]);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample17)
				parallelFor(RNG$, 0, nCoins, 1,
					(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1)
								sample17(var15, threadID$var15, RNG$1);
					}
				);

			if(!fixedFlag$sample21)
				sample21();
			if(!fixedFlag$sample29)
				parallelFor(RNG$, 0, nCoins, 1,
					(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
								sample29(var26, threadID$var26, RNG$1);
					}
				);

			if(!fixedFlag$sample36)
				sample36();
			if(!fixedFlag$sample46) {
				for(int i = 1; i < nFlips; i += 1)
					sample46(i);
			}
		} else {
			if(!fixedFlag$sample46) {
				for(int i = (nFlips - 1); i >= 1; i -= 1)
					sample46(i);
			}
			if(!fixedFlag$sample36)
				sample36();
			if(!fixedFlag$sample29)
				parallelFor(RNG$, 0, nCoins, 1,
					(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
								sample29(var26, threadID$var26, RNG$1);
					}
				);

			if(!fixedFlag$sample21)
				sample21();
			if(!fixedFlag$sample17)
				parallelFor(RNG$, 0, nCoins, 1,
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
		parallelFor(RNG$, 0, nCoins, 1,
			(int forStart$var8, int forEnd$var8, int threadID$var8, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var8 = forStart$var8; var8 < forEnd$var8; var8 += 1)
						v[var8] = 0.1;
			}
		);
		nFlips = length$measured;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var11 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$var16 = 0.0;
		logProbability$var18 = 0.0;
		if(!fixedProbFlag$sample21)
			logProbability$initialCoin = 0.0;
		logProbability$var22 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$var27 = 0.0;
		logProbability$var32 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample36)
			logProbability$var33 = 0.0;
		for(int i = 1; i < nFlips; i += 1)
			logProbability$var42[(i - 1)] = 0.0;
		if(!fixedProbFlag$sample46) {
			for(int i = 1; i < nFlips; i += 1)
				logProbability$sample46[(i - 1)] = 0.0;
		}
		for(int j = 0; j < nFlips; j += 1)
			logProbability$var51[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample55) {
			for(int j = 0; j < nFlips; j += 1)
				logProbability$sample55[j] = 0.0;
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
		if(fixedFlag$sample21)
			logProbabilityValue$sample21();
		if(fixedFlag$sample29)
			logProbabilityValue$sample29();
		if(fixedFlag$sample36)
			logProbabilityValue$sample36();
		if(fixedFlag$sample46)
			logProbabilityValue$sample46();
		logProbabilityValue$sample55();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample21();
		logProbabilityValue$sample29();
		logProbabilityValue$sample36();
		logProbabilityValue$sample46();
		logProbabilityValue$sample55();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample21();
		logProbabilityValue$sample29();
		logProbabilityValue$sample36();
		logProbabilityValue$sample46();
		logProbabilityValue$sample55();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample17)
			parallelFor(RNG$, 0, nCoins, 1,
				(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var15]);
				}
			);

		if(!fixedFlag$sample21)
			DistributionSampling.sampleDirichlet(RNG$, v, initialCoin);
		if(!fixedFlag$sample29)
			parallelFor(RNG$, 0, nCoins, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample36)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialCoin);
		if(!fixedFlag$sample46) {
			for(int i = 1; i < nFlips; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]]);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = measured[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMM_Paper(boolean[] measured, int nCoins) {\n  //Construct a transistion matrix m.\n  double[] v = new double[nCoins] <~ 0.1;\n  double[][] m = dirichlet(v).sample(nCoins);\n  \n  //Construct weighting for which coin to start at.\n  double[] initialCoin = dirichlet(v).sample;\n    \n  //Construct biases for each coin    \n  double[] bias = beta(1.0, 1.0).sample(nCoins);\n\n  //Allocate space to record which coin is flipped\n  int nFlips = measured.length;\n  int[] st = new int[nFlips];\n\n  //Calculate the movements between coins        \n  st[0] = categorical(initialCoin).sample();\n  for (int i: [1..nFlips) )\n    st[i] = categorical(m[st[i - 1]]).sample();\n\n  //Flip the coins\n  boolean[] flips = new boolean[nFlips];\n  for (int j: [0..nFlips) )\n    flips[j] = bernoulli(bias[st[j]]).sample();\n    \n  //Assert that the flips match the measured data.\n  flips.observe(measured);\n}\n";
	}
}