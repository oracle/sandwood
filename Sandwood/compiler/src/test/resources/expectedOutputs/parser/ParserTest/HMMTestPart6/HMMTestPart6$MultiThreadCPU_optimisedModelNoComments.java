package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMTestPart6$MultiThreadCPU extends CoreModelMultiThreadCPU implements HMMTestPart6$CoreInterface {
double[] bias;
	boolean[] constrainedFlag$sample28;
	boolean[] constrainedFlag$sample45;
	boolean constrainedFlag$sample53 = true;
	boolean[] constrainedFlag$sample78;
	boolean fixedFlag$sample28 = false;
	boolean fixedFlag$sample45 = false;
	boolean fixedFlag$sample53 = false;
	boolean fixedFlag$sample78 = false;
	boolean fixedProbFlag$sample103 = false;
	boolean fixedProbFlag$sample28 = false;
	boolean fixedProbFlag$sample45 = false;
	boolean fixedProbFlag$sample53 = false;
	boolean fixedProbFlag$sample78 = false;
	boolean[] flips;
	boolean[] flipsMeasured;
	int length$flipsMeasured;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$bias;
	double logProbability$flips;
	double logProbability$m;
	double[] logProbability$sample103;
	double[] logProbability$sample78;
	double logProbability$st;
	double logProbability$var28;
	double logProbability$var44;
	double logProbability$var52;
	double[][] m;
	int samples;
	int[] st;
	int states;
	boolean system$gibbsForward = true;
	double[] v;
	double[][] cv$var28$countGlobal;
	double[] cv$var52$stateProbabilityGlobal;
	double[] cv$var77$stateProbabilityGlobal;

	public HMMTestPart6$MultiThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample103 = false;
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
				constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
		}
		fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
		fixedProbFlag$sample78 = (cv$value && fixedProbFlag$sample78);
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
				constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
		}
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
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
		fixedProbFlag$sample78 = (cv$value && fixedProbFlag$sample78);
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
	}

	@Override
	public final boolean get$fixedFlag$sample78() {
		return fixedFlag$sample78;
	}

	@Override
	public final void set$fixedFlag$sample78(boolean cv$value, boolean allocated$) {
		fixedFlag$sample78 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample78$1 = 0; index$constrainedFlag$sample78$1 < constrainedFlag$sample78.length; index$constrainedFlag$sample78$1 += 1)
				constrainedFlag$sample78[index$constrainedFlag$sample78$1] = true;
		}
		fixedProbFlag$sample78 = (cv$value && fixedProbFlag$sample78);
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
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
		fixedProbFlag$sample78 = false;
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
		fixedProbFlag$sample78 = false;
		fixedProbFlag$sample103 = false;
	}

	@Override
	public final int get$states() {
		return 2;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void drawValueSample28(int var27, int threadID$cv$var27, Rng RNG$) {
		DistributionSampling.sampleDirichlet(RNG$, v, 2, m[var27]);
	}

	private final void drawValueSample45(int var43, int threadID$cv$var43, Rng RNG$) {
		bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	private final void drawValueSample53() {
		st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], 2);
	}

	private final void drawValueSample78(int i$var66) {
		st[(i$var66 - 3)] = DistributionSampling.sampleCategorical(RNG$, m[(1 - st[(i$var66 - 4)])], 2);
	}

	private final void inferSample28(int var27, int threadID$cv$var27, Rng RNG$) {
		constrainedFlag$sample28[var27] = false;
		double[] cv$countLocal = cv$var28$countGlobal[threadID$cv$var27];
		cv$countLocal[0] = 0.0;
		cv$countLocal[1] = 0.0;
		if(((var27 == 0) && (fixedFlag$sample53 || constrainedFlag$sample53))) {
			constrainedFlag$sample28[0] = true;
			cv$countLocal[st[0]] = (cv$countLocal[st[0]] + 1.0);
		}
		for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1) {
			if(((var27 == (1 - st[(i$var66 - 4)])) && (fixedFlag$sample78 || constrainedFlag$sample78[(i$var66 - 4)]))) {
				constrainedFlag$sample28[var27] = true;
				cv$countLocal[st[(i$var66 - 3)]] = (cv$countLocal[st[(i$var66 - 3)]] + 1.0);
			}
		}
		if(constrainedFlag$sample28[var27])
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var27], 2);
	}

	private final void inferSample45(int var43, int threadID$cv$var43, Rng RNG$) {
		constrainedFlag$sample45[var43] = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int j = 5; j < (samples + 5); j += 1) {
			if((var43 == (1 - st[(j - 5)]))) {
				constrainedFlag$sample45[var43] = true;
				cv$count = (cv$count + 1);
				if(flips[(j - 5)])
					cv$sum = (cv$sum + 1);
			}
		}
		if(constrainedFlag$sample45[var43])
			bias[var43] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void inferSample53() {
		constrainedFlag$sample53 = false;
		{
			st[0] = 0;
			double[] var50 = m[0];
			double cv$accumulatedProbabilities = (((0.0 <= var50[0]) && (var50[0] <= 1.0))?Math.log(var50[0]):Double.NEGATIVE_INFINITY);
			if(((1 < samples) && (fixedFlag$sample78 || constrainedFlag$sample78[0]))) {
				constrainedFlag$sample53 = true;
				double[] var75 = m[1];
				cv$accumulatedProbabilities = ((((((0.0 <= st[1]) && (st[1] < 2)) && (0.0 <= var75[st[1]])) && (var75[st[1]] <= 1.0))?Math.log(var75[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if((0 < samples)) {
				constrainedFlag$sample53 = true;
				double var100 = bias[1];
				cv$accumulatedProbabilities = ((((0.0 <= var100) && (var100 <= 1.0))?Math.log((flips[0]?var100:(1.0 - var100))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$var52$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		st[0] = 1;
		double[] var50 = m[0];
		double cv$accumulatedProbabilities = (((0.0 <= var50[1]) && (var50[1] <= 1.0))?Math.log(var50[1]):Double.NEGATIVE_INFINITY);
		if(((1 < samples) && (fixedFlag$sample78 || constrainedFlag$sample78[0]))) {
			constrainedFlag$sample53 = true;
			double[] var75 = m[0];
			cv$accumulatedProbabilities = ((((((0.0 <= st[1]) && (st[1] < 2)) && (0.0 <= var75[st[1]])) && (var75[st[1]] <= 1.0))?Math.log(var75[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		if((0 < samples)) {
			constrainedFlag$sample53 = true;
			double var100 = bias[0];
			cv$accumulatedProbabilities = ((((0.0 <= var100) && (var100 <= 1.0))?Math.log((flips[0]?var100:(1.0 - var100))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		cv$var52$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample53) {
			double cv$logSum;
			double cv$lseMax = cv$var52$stateProbabilityGlobal[0];
			double cv$lseElementValue = cv$var52$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$var52$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var52$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$var52$stateProbabilityGlobal[0] = 0.5;
				cv$var52$stateProbabilityGlobal[1] = 0.5;
			} else {
				cv$var52$stateProbabilityGlobal[0] = Math.exp((cv$var52$stateProbabilityGlobal[0] - cv$logSum));
				cv$var52$stateProbabilityGlobal[1] = Math.exp((cv$var52$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$var52$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var52$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			st[0] = DistributionSampling.sampleCategorical(RNG$, cv$var52$stateProbabilityGlobal, 2);
		}
	}

	private final void inferSample78(int i$var66) {
		constrainedFlag$sample78[(i$var66 - 4)] = false;
		{
			st[(i$var66 - 3)] = 0;
			double[] var75 = m[(1 - st[(i$var66 - 4)])];
			double cv$accumulatedProbabilities = (((0.0 <= var75[0]) && (var75[0] <= 1.0))?Math.log(var75[0]):Double.NEGATIVE_INFINITY);
			int index$i$2_2 = (i$var66 + 1);
			if(((index$i$2_2 < (samples + 3)) && (fixedFlag$sample78 || constrainedFlag$sample78[(index$i$2_2 - 4)]))) {
				double[] sc$var75$1 = m[1];
				cv$accumulatedProbabilities = ((((((0.0 <= st[(index$i$2_2 - 3)]) && (st[(index$i$2_2 - 3)] < 2)) && (0.0 <= sc$var75$1[st[(index$i$2_2 - 3)]])) && (sc$var75$1[st[(index$i$2_2 - 3)]] <= 1.0))?Math.log(sc$var75$1[st[(index$i$2_2 - 3)]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			constrainedFlag$sample78[(i$var66 - 4)] = true;
			double var100 = bias[1];
			cv$accumulatedProbabilities = ((((0.0 <= var100) && (var100 <= 1.0))?Math.log((flips[(i$var66 - 3)]?var100:(1.0 - var100))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			cv$var77$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		st[(i$var66 - 3)] = 1;
		double[] var75 = m[(1 - st[(i$var66 - 4)])];
		double cv$accumulatedProbabilities = (((0.0 <= var75[1]) && (var75[1] <= 1.0))?Math.log(var75[1]):Double.NEGATIVE_INFINITY);
		int index$i$2_2 = (i$var66 + 1);
		if(((index$i$2_2 < (samples + 3)) && (fixedFlag$sample78 || constrainedFlag$sample78[(index$i$2_2 - 4)]))) {
			double[] sc$var75$1 = m[0];
			cv$accumulatedProbabilities = ((((((0.0 <= st[(index$i$2_2 - 3)]) && (st[(index$i$2_2 - 3)] < 2)) && (0.0 <= sc$var75$1[st[(index$i$2_2 - 3)]])) && (sc$var75$1[st[(index$i$2_2 - 3)]] <= 1.0))?Math.log(sc$var75$1[st[(index$i$2_2 - 3)]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		constrainedFlag$sample78[(i$var66 - 4)] = true;
		double var100 = bias[0];
		cv$accumulatedProbabilities = ((((0.0 <= var100) && (var100 <= 1.0))?Math.log((flips[(i$var66 - 3)]?var100:(1.0 - var100))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		cv$var77$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample78[(i$var66 - 4)]) {
			double cv$logSum;
			double cv$lseMax = cv$var77$stateProbabilityGlobal[0];
			double cv$lseElementValue = cv$var77$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$var77$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var77$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$var77$stateProbabilityGlobal[0] = 0.5;
				cv$var77$stateProbabilityGlobal[1] = 0.5;
			} else {
				cv$var77$stateProbabilityGlobal[0] = Math.exp((cv$var77$stateProbabilityGlobal[0] - cv$logSum));
				cv$var77$stateProbabilityGlobal[1] = Math.exp((cv$var77$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$var77$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var77$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			st[(i$var66 - 3)] = DistributionSampling.sampleCategorical(RNG$, cv$var77$stateProbabilityGlobal, 2);
		}
	}

	private final void logProbabilityValue$sample103() {
		if(!fixedProbFlag$sample103) {
			double cv$accumulator = 0.0;
			for(int j = 5; j < (samples + 5); j += 1) {
				double var100 = bias[(1 - st[(j - 5)])];
				double cv$distributionAccumulator = (((0.0 <= var100) && (var100 <= 1.0))?Math.log((flips[(j - 5)]?var100:(1.0 - var100))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample103[(j - 5)] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample103 = ((fixedFlag$sample45 && fixedFlag$sample53) && fixedFlag$sample78);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 5; j < (samples + 5); j += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample103[(j - 5)]);
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityDirichlet(m[0], v, 2) + DistributionSampling.logProbabilityDirichlet(m[1], v, 2));
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
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityBeta(bias[0], 1.0, 1.0) + DistributionSampling.logProbabilityBeta(bias[1], 1.0, 1.0));
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
			double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0.0 <= var50[cv$sampleValue])) && (var50[cv$sampleValue] <= 1.0))?Math.log(var50[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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

	private final void logProbabilityValue$sample78() {
		if(!fixedProbFlag$sample78) {
			double cv$accumulator = 0.0;
			for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1) {
				int cv$sampleValue = st[(i$var66 - 3)];
				double[] var75 = m[(1 - st[(i$var66 - 4)])];
				double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0.0 <= var75[cv$sampleValue])) && (var75[cv$sampleValue] <= 1.0))?Math.log(var75[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample78[(i$var66 - 4)] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample78)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample78 = ((fixedFlag$sample78 && fixedFlag$sample28) && fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample78[(i$var66 - 4)]);
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample78)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void allocate() {
		v = new double[2];
		if(!fixedFlag$sample28) {
			m = new double[2][];
			m[0] = new double[2];
			m[1] = new double[2];
		}
		if(!fixedFlag$sample45)
			bias = new double[2];
		if((!fixedFlag$sample53 || !fixedFlag$sample78))
			st = new int[length$flipsMeasured];
		flips = new boolean[length$flipsMeasured];
		constrainedFlag$sample78 = new boolean[(length$flipsMeasured - 1)];
		constrainedFlag$sample45 = new boolean[2];
		constrainedFlag$sample28 = new boolean[2];
		logProbability$sample78 = new double[(length$flipsMeasured - 1)];
		logProbability$sample103 = new double[length$flipsMeasured];
		allocateScratch();
	}

	@Override
	public final void allocateScratch() {
		int cv$threadCount = threadCount();
		cv$var28$countGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var28$countGlobal[cv$index] = new double[2];
		cv$var52$stateProbabilityGlobal = new double[2];
		cv$var77$stateProbabilityGlobal = new double[2];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 2, m[var27]);
				}
			);

		if(!fixedFlag$sample45)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], 2);
		if(!fixedFlag$sample78) {
			for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1)
				st[(i$var66 - 3)] = DistributionSampling.sampleCategorical(RNG$, m[(1 - st[(i$var66 - 4)])], 2);
		}
		parallelFor(RNG$, 5, (samples + 5), 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						flips[(j - 5)] = DistributionSampling.sampleBernoulli(RNG$1, bias[(1 - st[(j - 5)])]);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 2, m[var27]);
				}
			);

		if(!fixedFlag$sample45)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], 2);
		if(!fixedFlag$sample78) {
			for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1)
				st[(i$var66 - 3)] = DistributionSampling.sampleCategorical(RNG$, m[(1 - st[(i$var66 - 4)])], 2);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 2, m[var27]);
				}
			);

		if(!fixedFlag$sample45)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], 2);
		if(!fixedFlag$sample78) {
			for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1)
				st[(i$var66 - 3)] = DistributionSampling.sampleCategorical(RNG$, m[(1 - st[(i$var66 - 4)])], 2);
		}
		parallelFor(RNG$, 5, (samples + 5), 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						flips[(j - 5)] = DistributionSampling.sampleBernoulli(RNG$1, bias[(1 - st[(j - 5)])]);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 2, m[var27]);
				}
			);

		if(!fixedFlag$sample45)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], 2);
		if(!fixedFlag$sample78) {
			for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1)
				st[(i$var66 - 3)] = DistributionSampling.sampleCategorical(RNG$, m[(1 - st[(i$var66 - 4)])], 2);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 2, m[var27]);
				}
			);

		if(!fixedFlag$sample45)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], 2);
		if(!fixedFlag$sample78) {
			for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1)
				st[(i$var66 - 3)] = DistributionSampling.sampleCategorical(RNG$, m[(1 - st[(i$var66 - 4)])], 2);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample28)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								inferSample28(var27, threadID$var27, RNG$1);
					}
				);

			if(!fixedFlag$sample45)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
								inferSample45(var43, threadID$var43, RNG$1);
					}
				);

			if(!fixedFlag$sample53)
				inferSample53();
			if(!fixedFlag$sample78) {
				for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1)
					inferSample78(i$var66);
			}
		} else {
			if(!fixedFlag$sample78) {
				for(int i$var66 = (samples + 2); i$var66 >= 4; i$var66 -= 1)
					inferSample78(i$var66);
			}
			if(!fixedFlag$sample53)
				inferSample53();
			if(!fixedFlag$sample45)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
								inferSample45(var43, threadID$var43, RNG$1);
					}
				);

			if(!fixedFlag$sample28)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								inferSample28(var27, threadID$var27, RNG$1);
					}
				);

		}
		system$gibbsForward = !system$gibbsForward;
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						if(!constrainedFlag$sample28[var27])
							drawValueSample28(var27, threadID$var27, RNG$1);
					}
			}
		);
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!constrainedFlag$sample45[var43])
							drawValueSample45(var43, threadID$var43, RNG$1);
					}
			}
		);
		if(!constrainedFlag$sample53)
			drawValueSample53();
		for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1) {
			if(!constrainedFlag$sample78[(i$var66 - 4)])
				drawValueSample78(i$var66);
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
		if(!fixedProbFlag$sample78) {
			for(int i$var66 = 4; i$var66 < (samples + 3); i$var66 += 1)
				logProbability$sample78[(i$var66 - 4)] = Double.NaN;
		}
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample103) {
			for(int j = 5; j < (samples + 5); j += 1)
				logProbability$sample103[(j - 5)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		v[0] = 0.1;
		v[1] = 0.1;
		samples = length$flipsMeasured;
		for(int index$constrainedFlag$sample78$1 = 0; index$constrainedFlag$sample78$1 < constrainedFlag$sample78.length; index$constrainedFlag$sample78$1 += 1)
			constrainedFlag$sample78[index$constrainedFlag$sample78$1] = true;
		for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
			constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
		for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
			constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		if(fixedFlag$sample53)
			logProbabilityValue$sample53();
		if(fixedFlag$sample78)
			logProbabilityValue$sample78();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample78();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample78();
		logProbabilityValue$sample103();
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
		     + "model HMMTestPart6(boolean[] flipsMeasured) {\n"
		     + "        int states = 2;\n"
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
		     + "        st[0] = categorical(m[0]).sample();\n"
		     + "\n"
		     + "        for(int i:[4..samples + 3))\n"
		     + "            st[i-3] = categorical(m[(states - 1) - st[i-4]]).sample();\n"
		     + "            \n"
		     + "        boolean[] flips = new boolean[samples];\n"
		     + "            \n"
		     + "        for(int j:[5..samples+5))\n"
		     + "            flips[j-5] = bernoulli(bias[(states - 1) - st[j-5]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}