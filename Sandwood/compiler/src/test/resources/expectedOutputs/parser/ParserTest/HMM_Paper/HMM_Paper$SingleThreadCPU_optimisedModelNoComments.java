package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMM_Paper$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMM_Paper$CoreInterface {
	private double[] bias;
	private boolean[] constrainedFlag$sample28;
	private boolean constrainedFlag$sample32 = true;
	private boolean[] constrainedFlag$sample47;
	private boolean constrainedFlag$sample53 = true;
	private boolean[] constrainedFlag$sample71;
	private double[] cv$var28$countGlobal;
	private double[] cv$var31$countGlobal;
	private double[] cv$var52$stateProbabilityGlobal;
	private double[] cv$var70$stateProbabilityGlobal;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedFlag$sample47 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedFlag$sample71 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean fixedProbFlag$sample47 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean fixedProbFlag$sample71 = false;
	private boolean fixedProbFlag$sample87 = false;
	private boolean[] flips;
	private double[] initialCoin;
	private int length$measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$initialCoin;
	private double logProbability$m;
	private double[] logProbability$sample71;
	private double[] logProbability$sample87;
	private double logProbability$st;
	private double logProbability$var28;
	private double logProbability$var46;
	private double logProbability$var52;
	private double[][] m;
	private boolean[] measured;
	private int nCoins;
	private int nFlips;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMM_Paper$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value, boolean allocated$) {
		bias = cv$value;
		fixedProbFlag$sample47 = false;
		fixedProbFlag$sample87 = false;
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
		fixedProbFlag$sample71 = (cv$value && fixedProbFlag$sample71);
	}

	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	@Override
	public final void set$fixedFlag$sample32(boolean cv$value, boolean allocated$) {
		fixedFlag$sample32 = cv$value;
		constrainedFlag$sample32 = (cv$value || constrainedFlag$sample32);
		fixedProbFlag$sample32 = (cv$value && fixedProbFlag$sample32);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$fixedFlag$sample47() {
		return fixedFlag$sample47;
	}

	@Override
	public final void set$fixedFlag$sample47(boolean cv$value, boolean allocated$) {
		fixedFlag$sample47 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample47$1 = 0; index$constrainedFlag$sample47$1 < constrainedFlag$sample47.length; index$constrainedFlag$sample47$1 += 1)
				constrainedFlag$sample47[index$constrainedFlag$sample47$1] = cv$value;
		}
		fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
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
	public final double[] get$initialCoin() {
		return initialCoin;
	}

	@Override
	public final void set$initialCoin(double[] cv$value, boolean allocated$) {
		initialCoin = cv$value;
		fixedProbFlag$sample32 = false;
		fixedProbFlag$sample53 = false;
	}

	@Override
	public final int get$length$measured() {
		return length$measured;
	}

	@Override
	public final void set$length$measured(int cv$value, boolean allocated$) {
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
	public final void set$m(double[][] cv$value, boolean allocated$) {
		m = cv$value;
		fixedProbFlag$sample28 = false;
		fixedProbFlag$sample71 = false;
	}

	@Override
	public final boolean[] get$measured() {
		return measured;
	}

	@Override
	public final void set$measured(boolean[] cv$value, boolean allocated$) {
		measured = cv$value;
	}

	@Override
	public final int get$nCoins() {
		return nCoins;
	}

	@Override
	public final void set$nCoins(int cv$value, boolean allocated$) {
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
	public final void set$st(int[] cv$value, boolean allocated$) {
		st = cv$value;
		fixedProbFlag$sample53 = false;
		fixedProbFlag$sample71 = false;
		fixedProbFlag$sample87 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void drawValueSample28(int var27) {
		DistributionSampling.sampleDirichlet(RNG$, v, nCoins, m[var27]);
	}

	private final void drawValueSample32() {
		DistributionSampling.sampleDirichlet(RNG$, v, nCoins, initialCoin);
	}

	private final void drawValueSample47(int var45) {
		bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	private final void drawValueSample53() {
		st[0] = DistributionSampling.sampleCategorical(RNG$, initialCoin, nCoins);
	}

	private final void drawValueSample71(int i) {
		st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]], nCoins);
	}

	private final void inferSample28(int var27) {
		constrainedFlag$sample28[var27] = false;
		for(int cv$loopIndex = 0; cv$loopIndex < nCoins; cv$loopIndex += 1)
			cv$var28$countGlobal[cv$loopIndex] = 0.0;
		for(int i = 1; i < nFlips; i += 1) {
			if(((var27 == st[(i - 1)]) && (fixedFlag$sample71 || constrainedFlag$sample71[(i - 1)]))) {
				constrainedFlag$sample28[var27] = true;
				cv$var28$countGlobal[st[i]] = (cv$var28$countGlobal[st[i]] + 1.0);
			}
		}
		if(constrainedFlag$sample28[var27])
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var28$countGlobal, m[var27], nCoins);
	}

	private final void inferSample32() {
		constrainedFlag$sample32 = false;
		for(int cv$loopIndex = 0; cv$loopIndex < nCoins; cv$loopIndex += 1)
			cv$var31$countGlobal[cv$loopIndex] = 0.0;
		if((fixedFlag$sample53 || constrainedFlag$sample53)) {
			constrainedFlag$sample32 = true;
			cv$var31$countGlobal[st[0]] = (cv$var31$countGlobal[st[0]] + 1.0);
		}
		if(constrainedFlag$sample32)
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var31$countGlobal, initialCoin, nCoins);
	}

	private final void inferSample47(int var45) {
		constrainedFlag$sample47[var45] = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int j = 0; j < nFlips; j += 1) {
			if((var45 == st[j])) {
				constrainedFlag$sample47[var45] = true;
				cv$count = (cv$count + 1);
				if(flips[j])
					cv$sum = (cv$sum + 1);
			}
		}
		if(constrainedFlag$sample47[var45])
			bias[var45] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void inferSample53() {
		constrainedFlag$sample53 = false;
		int cv$numStates = Math.max(0, nCoins);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			st[0] = cv$valuePos;
			double cv$accumulatedProbabilities = (((((cv$valuePos < nCoins) && (0 < nCoins)) && (0.0 <= initialCoin[cv$valuePos])) && (initialCoin[cv$valuePos] <= 1.0))?Math.log(initialCoin[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if(((1 < nFlips) && (fixedFlag$sample71 || constrainedFlag$sample71[0]))) {
				constrainedFlag$sample53 = true;
				double[] var68 = m[cv$valuePos];
				cv$accumulatedProbabilities = (((((((0.0 <= st[1]) && (st[1] < nCoins)) && (0 < nCoins)) && (0.0 <= var68[st[1]])) && (var68[st[1]] <= 1.0))?Math.log(var68[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if((0 < nFlips)) {
				constrainedFlag$sample53 = true;
				double var84 = bias[cv$valuePos];
				cv$accumulatedProbabilities = ((((0.0 <= var84) && (var84 <= 1.0))?Math.log((flips[0]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$var52$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample53) {
			double cv$logSum;
			double cv$lseMax = cv$var52$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$var52$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$var52$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$var52$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$var52$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var52$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var52$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var52$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			st[0] = DistributionSampling.sampleCategorical(RNG$, cv$var52$stateProbabilityGlobal, cv$numStates);
		}
	}

	private final void inferSample71(int i) {
		constrainedFlag$sample71[(i - 1)] = false;
		int cv$numStates = Math.max(0, nCoins);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			st[i] = cv$valuePos;
			double[] var68 = m[st[(i - 1)]];
			double cv$accumulatedProbabilities = (((((cv$valuePos < nCoins) && (0 < nCoins)) && (0.0 <= var68[cv$valuePos])) && (var68[cv$valuePos] <= 1.0))?Math.log(var68[cv$valuePos]):Double.NEGATIVE_INFINITY);
			int index$i$2_2 = (i + 1);
			if(((index$i$2_2 < nFlips) && (fixedFlag$sample71 || constrainedFlag$sample71[(index$i$2_2 - 1)]))) {
				double[] sc$var68$1 = m[cv$valuePos];
				cv$accumulatedProbabilities = (((((((0.0 <= st[index$i$2_2]) && (st[index$i$2_2] < nCoins)) && (0 < nCoins)) && (0.0 <= sc$var68$1[st[index$i$2_2]])) && (sc$var68$1[st[index$i$2_2]] <= 1.0))?Math.log(sc$var68$1[st[index$i$2_2]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			constrainedFlag$sample71[(i - 1)] = true;
			double var84 = bias[cv$valuePos];
			cv$accumulatedProbabilities = ((((0.0 <= var84) && (var84 <= 1.0))?Math.log((flips[i]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			cv$var70$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample71[(i - 1)]) {
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
					cv$var70$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$var70$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var70$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var70$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var70$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			st[i] = DistributionSampling.sampleCategorical(RNG$, cv$var70$stateProbabilityGlobal, cv$numStates);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$sampleAccumulator = 0.0;
			for(int var27 = 0; var27 < nCoins; var27 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var27], v, nCoins));
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

	private final void logProbabilityValue$sample32() {
		if(!fixedProbFlag$sample32) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(initialCoin, v, nCoins);
			logProbability$initialCoin = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample32 = fixedFlag$sample32;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$initialCoin);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialCoin);
		}
	}

	private final void logProbabilityValue$sample47() {
		if(!fixedProbFlag$sample47) {
			double cv$sampleAccumulator = 0.0;
			for(int var45 = 0; var45 < nCoins; var45 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(bias[var45], 1.0, 1.0));
			logProbability$var46 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample47 = fixedFlag$sample47;
		} else {
			logProbability$bias = (logProbability$bias + logProbability$var46);
			logProbability$$model = (logProbability$$model + logProbability$var46);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var46);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			int cv$sampleValue = st[0];
			double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < nCoins)) && (0 < nCoins)) && (0.0 <= initialCoin[cv$sampleValue])) && (initialCoin[cv$sampleValue] <= 1.0))?Math.log(initialCoin[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var52 = cv$distributionAccumulator;
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedFlag$sample32);
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
			for(int i = 1; i < nFlips; i += 1) {
				int cv$sampleValue = st[i];
				double[] var68 = m[st[(i - 1)]];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < nCoins)) && (0 < nCoins)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample71[(i - 1)] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample71 = ((fixedFlag$sample71 && fixedFlag$sample28) && fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 1; i < nFlips; i += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample71[(i - 1)]);
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample87() {
		if(!fixedProbFlag$sample87) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < nFlips; j += 1) {
				double var84 = bias[st[j]];
				double cv$distributionAccumulator = (((0.0 <= var84) && (var84 <= 1.0))?Math.log((flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample87[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample87 = ((fixedFlag$sample47 && fixedFlag$sample53) && fixedFlag$sample71);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < nFlips; j += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample87[j]);
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void allocateScratch() {
		cv$var28$countGlobal = new double[nCoins];
		cv$var31$countGlobal = new double[nCoins];
		cv$var52$stateProbabilityGlobal = new double[nCoins];
		cv$var70$stateProbabilityGlobal = new double[nCoins];
	}

	@Override
	public final void allocator() {
		v = new double[nCoins];
		if(!fixedFlag$sample28) {
			m = new double[nCoins][];
			for(int var27 = 0; var27 < nCoins; var27 += 1)
				m[var27] = new double[nCoins];
		}
		if(!fixedFlag$sample32)
			initialCoin = new double[nCoins];
		if(!fixedFlag$sample47)
			bias = new double[nCoins];
		if((!fixedFlag$sample53 || !fixedFlag$sample71))
			st = new int[length$measured];
		flips = new boolean[length$measured];
		constrainedFlag$sample47 = new boolean[nCoins];
		constrainedFlag$sample28 = new boolean[nCoins];
		constrainedFlag$sample71 = new boolean[(length$measured - 1)];
		logProbability$sample71 = new double[(length$measured - 1)];
		logProbability$sample87 = new double[length$measured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample28) {
			for(int var27 = 0; var27 < nCoins; var27 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, nCoins, m[var27]);
		}
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, nCoins, initialCoin);
		if(!fixedFlag$sample47) {
			for(int var45 = 0; var45 < nCoins; var45 += 1)
				bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialCoin, nCoins);
		if(!fixedFlag$sample71) {
			for(int i = 1; i < nFlips; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]], nCoins);
		}
		for(int j = 0; j < nFlips; j += 1)
			flips[j] = DistributionSampling.sampleBernoulli(RNG$, bias[st[j]]);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample28) {
			for(int var27 = 0; var27 < nCoins; var27 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, nCoins, m[var27]);
		}
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, nCoins, initialCoin);
		if(!fixedFlag$sample47) {
			for(int var45 = 0; var45 < nCoins; var45 += 1)
				bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialCoin, nCoins);
		if(!fixedFlag$sample71) {
			for(int i = 1; i < nFlips; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]], nCoins);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample28) {
			for(int var27 = 0; var27 < nCoins; var27 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, nCoins, m[var27]);
		}
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, nCoins, initialCoin);
		if(!fixedFlag$sample47) {
			for(int var45 = 0; var45 < nCoins; var45 += 1)
				bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialCoin, nCoins);
		if(!fixedFlag$sample71) {
			for(int i = 1; i < nFlips; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]], nCoins);
		}
		for(int j = 0; j < nFlips; j += 1)
			flips[j] = DistributionSampling.sampleBernoulli(RNG$, bias[st[j]]);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample28) {
			for(int var27 = 0; var27 < nCoins; var27 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, nCoins, m[var27]);
		}
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, nCoins, initialCoin);
		if(!fixedFlag$sample47) {
			for(int var45 = 0; var45 < nCoins; var45 += 1)
				bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialCoin, nCoins);
		if(!fixedFlag$sample71) {
			for(int i = 1; i < nFlips; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]], nCoins);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample28) {
			for(int var27 = 0; var27 < nCoins; var27 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, nCoins, m[var27]);
		}
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, nCoins, initialCoin);
		if(!fixedFlag$sample47) {
			for(int var45 = 0; var45 < nCoins; var45 += 1)
				bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialCoin, nCoins);
		if(!fixedFlag$sample71) {
			for(int i = 1; i < nFlips; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]], nCoins);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample28) {
				for(int var27 = 0; var27 < nCoins; var27 += 1)
					inferSample28(var27);
			}
			if(!fixedFlag$sample32)
				inferSample32();
			if(!fixedFlag$sample47) {
				for(int var45 = 0; var45 < nCoins; var45 += 1)
					inferSample47(var45);
			}
			if(!fixedFlag$sample53)
				inferSample53();
			if(!fixedFlag$sample71) {
				for(int i = 1; i < nFlips; i += 1)
					inferSample71(i);
			}
		} else {
			if(!fixedFlag$sample71) {
				for(int i = (nFlips - 1); i >= 1; i -= 1)
					inferSample71(i);
			}
			if(!fixedFlag$sample53)
				inferSample53();
			if(!fixedFlag$sample47) {
				for(int var45 = (nCoins - 1); var45 >= 0; var45 -= 1)
					inferSample47(var45);
			}
			if(!fixedFlag$sample32)
				inferSample32();
			if(!fixedFlag$sample28) {
				for(int var27 = (nCoins - 1); var27 >= 0; var27 -= 1)
					inferSample28(var27);
			}
		}
		system$gibbsForward = !system$gibbsForward;
		for(int var27 = 0; var27 < nCoins; var27 += 1) {
			if(!constrainedFlag$sample28[var27])
				drawValueSample28(var27);
		}
		if(!constrainedFlag$sample32)
			drawValueSample32();
		for(int var45 = 0; var45 < nCoins; var45 += 1) {
			if(!constrainedFlag$sample47[var45])
				drawValueSample47(var45);
		}
		if(!constrainedFlag$sample53)
			drawValueSample53();
		for(int i = 1; i < nFlips; i += 1) {
			if(!constrainedFlag$sample71[(i - 1)])
				drawValueSample71(i);
		}
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var28 = Double.NaN;
		if(!fixedProbFlag$sample32)
			logProbability$initialCoin = Double.NaN;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample47)
			logProbability$var46 = Double.NaN;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$var52 = Double.NaN;
		if(!fixedProbFlag$sample71) {
			for(int i = 1; i < nFlips; i += 1)
				logProbability$sample71[(i - 1)] = Double.NaN;
		}
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample87) {
			for(int j = 0; j < nFlips; j += 1)
				logProbability$sample87[j] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		for(int var13 = 0; var13 < nCoins; var13 += 1)
			v[var13] = 0.1;
		nFlips = length$measured;
		for(int index$constrainedFlag$sample47$1 = 0; index$constrainedFlag$sample47$1 < constrainedFlag$sample47.length; index$constrainedFlag$sample47$1 += 1)
			constrainedFlag$sample47[index$constrainedFlag$sample47$1] = true;
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
		if(fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(fixedFlag$sample47)
			logProbabilityValue$sample47();
		if(fixedFlag$sample53)
			logProbabilityValue$sample53();
		if(fixedFlag$sample71)
			logProbabilityValue$sample71();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample32();
		logProbabilityValue$sample47();
		logProbabilityValue$sample53();
		logProbabilityValue$sample71();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample32();
		logProbabilityValue$sample47();
		logProbabilityValue$sample53();
		logProbabilityValue$sample71();
		logProbabilityValue$sample87();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = measured[cv$index1];
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
		     + "model HMM_Paper(boolean[] measured, int nCoins) {\n"
		     + "  //Construct a transistion matrix m.\n"
		     + "  double[] v = new double[nCoins] <~ 0.1;\n"
		     + "  double[][] m = dirichlet(v).sample(nCoins);\n"
		     + "  \n"
		     + "  //Construct weighting for which coin to start at.\n"
		     + "  double[] initialCoin = dirichlet(v).sample;\n"
		     + "    \n"
		     + "  //Construct biases for each coin    \n"
		     + "  double[] bias = beta(1.0, 1.0).sample(nCoins);\n"
		     + "\n"
		     + "  //Allocate space to record which coin is flipped\n"
		     + "  int nFlips = measured.length;\n"
		     + "  int[] st = new int[nFlips];\n"
		     + "\n"
		     + "  //Calculate the movements between coins        \n"
		     + "  st[0] = categorical(initialCoin).sample();\n"
		     + "  for (int i: [1..nFlips) )\n"
		     + "    st[i] = categorical(m[st[i - 1]]).sample();\n"
		     + "\n"
		     + "  //Flip the coins\n"
		     + "  boolean[] flips = new boolean[nFlips];\n"
		     + "  for (int j: [0..nFlips) )\n"
		     + "    flips[j] = bernoulli(bias[st[j]]).sample();\n"
		     + "    \n"
		     + "  //Assert that the flips match the measured data.\n"
		     + "  flips.observe(measured);\n"
		     + "}\n"
		     + "";
	}
}