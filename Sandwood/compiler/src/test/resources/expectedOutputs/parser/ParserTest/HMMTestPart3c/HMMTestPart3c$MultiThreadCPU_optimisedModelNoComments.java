package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMTestPart3c$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart3c$CoreInterface {
	private double[] bias;
	private boolean[] constrainedFlag$sample28;
	private boolean[] constrainedFlag$sample45;
	private boolean constrainedFlag$sample53 = true;
	private boolean[] constrainedFlag$sample74;
	private double[][] cv$var28$countGlobal;
	private double[] cv$var52$stateProbabilityGlobal;
	private double[] cv$var73$stateProbabilityGlobal;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedFlag$sample74 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean fixedProbFlag$sample74 = false;
	private boolean fixedProbFlag$sample90 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample74;
	private double[] logProbability$sample90;
	private double logProbability$st;
	private double logProbability$var28;
	private double logProbability$var44;
	private double logProbability$var52;
	private double[][] m;
	private int samples;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart3c$MultiThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample90 = false;
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
		fixedProbFlag$sample74 = (cv$value && fixedProbFlag$sample74);
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
		fixedProbFlag$sample90 = (cv$value && fixedProbFlag$sample90);
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
		fixedProbFlag$sample74 = (cv$value && fixedProbFlag$sample74);
		fixedProbFlag$sample90 = (cv$value && fixedProbFlag$sample90);
	}

	@Override
	public final boolean get$fixedFlag$sample74() {
		return fixedFlag$sample74;
	}

	@Override
	public final void set$fixedFlag$sample74(boolean cv$value, boolean allocated$) {
		fixedFlag$sample74 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample74$1 = 0; index$constrainedFlag$sample74$1 < constrainedFlag$sample74.length; index$constrainedFlag$sample74$1 += 1)
				constrainedFlag$sample74[index$constrainedFlag$sample74$1] = true;
		}
		fixedProbFlag$sample74 = (cv$value && fixedProbFlag$sample74);
		fixedProbFlag$sample90 = (cv$value && fixedProbFlag$sample90);
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
		fixedProbFlag$sample74 = false;
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
		fixedProbFlag$sample74 = false;
		fixedProbFlag$sample90 = false;
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

	private final void drawValueSample74(int i$var64) {
		st[((i$var64 + i$var64) / 2)] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], 2);
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
		for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
			if(((var27 == st[(i$var64 - 1)]) && (fixedFlag$sample74 || constrainedFlag$sample74[(i$var64 - 1)]))) {
				constrainedFlag$sample28[var27] = true;
				cv$countLocal[st[((i$var64 + i$var64) / 2)]] = (cv$countLocal[st[((i$var64 + i$var64) / 2)]] + 1.0);
			}
		}
		if(constrainedFlag$sample28[var27])
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var27], 2);
	}

	private final void inferSample45(int var43, int threadID$cv$var43, Rng RNG$) {
		constrainedFlag$sample45[var43] = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int j = 0; j < samples; j += 1) {
			if((var43 == st[j])) {
				constrainedFlag$sample45[var43] = true;
				cv$count = (cv$count + 1);
				if(flips[j])
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
			if(((1 < samples) && (fixedFlag$sample74 || constrainedFlag$sample74[0]))) {
				constrainedFlag$sample53 = true;
				double[] var71 = m[0];
				cv$accumulatedProbabilities = ((((((0.0 <= st[1]) && (st[1] < 2)) && (0.0 <= var71[st[1]])) && (var71[st[1]] <= 1.0))?Math.log(var71[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if((0 < samples)) {
				constrainedFlag$sample53 = true;
				double var87 = bias[0];
				cv$accumulatedProbabilities = ((((0.0 <= var87) && (var87 <= 1.0))?Math.log((flips[0]?var87:(1.0 - var87))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$var52$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		st[0] = 1;
		double[] var50 = m[0];
		double cv$accumulatedProbabilities = (((0.0 <= var50[1]) && (var50[1] <= 1.0))?Math.log(var50[1]):Double.NEGATIVE_INFINITY);
		if(((1 < samples) && (fixedFlag$sample74 || constrainedFlag$sample74[0]))) {
			constrainedFlag$sample53 = true;
			double[] var71 = m[1];
			cv$accumulatedProbabilities = ((((((0.0 <= st[1]) && (st[1] < 2)) && (0.0 <= var71[st[1]])) && (var71[st[1]] <= 1.0))?Math.log(var71[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		if((0 < samples)) {
			constrainedFlag$sample53 = true;
			double var87 = bias[1];
			cv$accumulatedProbabilities = ((((0.0 <= var87) && (var87 <= 1.0))?Math.log((flips[0]?var87:(1.0 - var87))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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

	private final void inferSample74(int i$var64) {
		constrainedFlag$sample74[(i$var64 - 1)] = false;
		{
			st[((i$var64 + i$var64) / 2)] = 0;
			double[] var71 = m[st[(i$var64 - 1)]];
			double cv$accumulatedProbabilities = (((0.0 <= var71[0]) && (var71[0] <= 1.0))?Math.log(var71[0]):Double.NEGATIVE_INFINITY);
			int index$i$2_2 = (((i$var64 + i$var64) / 2) + 1);
			if(((index$i$2_2 < samples) && (fixedFlag$sample74 || constrainedFlag$sample74[(index$i$2_2 - 1)]))) {
				constrainedFlag$sample74[(i$var64 - 1)] = true;
				double[] sc$var71$1 = m[0];
				cv$accumulatedProbabilities = ((((((0.0 <= st[((index$i$2_2 + index$i$2_2) / 2)]) && (st[((index$i$2_2 + index$i$2_2) / 2)] < 2)) && (0.0 <= sc$var71$1[st[((index$i$2_2 + index$i$2_2) / 2)]])) && (sc$var71$1[st[((index$i$2_2 + index$i$2_2) / 2)]] <= 1.0))?Math.log(sc$var71$1[st[((index$i$2_2 + index$i$2_2) / 2)]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			int j = ((i$var64 + i$var64) / 2);
			if((j < samples)) {
				constrainedFlag$sample74[(i$var64 - 1)] = true;
				double var87 = bias[0];
				cv$accumulatedProbabilities = ((((0.0 <= var87) && (var87 <= 1.0))?Math.log((flips[j]?var87:(1.0 - var87))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$var73$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		st[((i$var64 + i$var64) / 2)] = 1;
		double[] var71 = m[st[(i$var64 - 1)]];
		double cv$accumulatedProbabilities = (((0.0 <= var71[1]) && (var71[1] <= 1.0))?Math.log(var71[1]):Double.NEGATIVE_INFINITY);
		int index$i$2_2 = (((i$var64 + i$var64) / 2) + 1);
		if(((index$i$2_2 < samples) && (fixedFlag$sample74 || constrainedFlag$sample74[(index$i$2_2 - 1)]))) {
			constrainedFlag$sample74[(i$var64 - 1)] = true;
			double[] sc$var71$1 = m[1];
			cv$accumulatedProbabilities = ((((((0.0 <= st[((index$i$2_2 + index$i$2_2) / 2)]) && (st[((index$i$2_2 + index$i$2_2) / 2)] < 2)) && (0.0 <= sc$var71$1[st[((index$i$2_2 + index$i$2_2) / 2)]])) && (sc$var71$1[st[((index$i$2_2 + index$i$2_2) / 2)]] <= 1.0))?Math.log(sc$var71$1[st[((index$i$2_2 + index$i$2_2) / 2)]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		int j = ((i$var64 + i$var64) / 2);
		if((j < samples)) {
			constrainedFlag$sample74[(i$var64 - 1)] = true;
			double var87 = bias[1];
			cv$accumulatedProbabilities = ((((0.0 <= var87) && (var87 <= 1.0))?Math.log((flips[j]?var87:(1.0 - var87))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		cv$var73$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample74[(i$var64 - 1)]) {
			double cv$logSum;
			double cv$lseMax = cv$var73$stateProbabilityGlobal[0];
			double cv$lseElementValue = cv$var73$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$var73$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var73$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$var73$stateProbabilityGlobal[0] = 0.5;
				cv$var73$stateProbabilityGlobal[1] = 0.5;
			} else {
				cv$var73$stateProbabilityGlobal[0] = Math.exp((cv$var73$stateProbabilityGlobal[0] - cv$logSum));
				cv$var73$stateProbabilityGlobal[1] = Math.exp((cv$var73$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$var73$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var73$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			st[((i$var64 + i$var64) / 2)] = DistributionSampling.sampleCategorical(RNG$, cv$var73$stateProbabilityGlobal, 2);
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

	private final void logProbabilityValue$sample74() {
		if(!fixedProbFlag$sample74) {
			double cv$accumulator = 0.0;
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
				int cv$sampleValue = st[((i$var64 + i$var64) / 2)];
				double[] var71 = m[st[(i$var64 - 1)]];
				double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0.0 <= var71[cv$sampleValue])) && (var71[cv$sampleValue] <= 1.0))?Math.log(var71[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample74[(i$var64 - 1)] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample74)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample74 = ((fixedFlag$sample74 && fixedFlag$sample28) && fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample74[(i$var64 - 1)]);
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample74)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample90() {
		if(!fixedProbFlag$sample90) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double var87 = bias[st[j]];
				double cv$distributionAccumulator = (((0.0 <= var87) && (var87 <= 1.0))?Math.log((flips[j]?var87:(1.0 - var87))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample90[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample90 = ((fixedFlag$sample45 && fixedFlag$sample53) && fixedFlag$sample74);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample90[j]);
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void allocateScratch() {
		int cv$threadCount = threadCount();
		cv$var28$countGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var28$countGlobal[cv$index] = new double[2];
		cv$var52$stateProbabilityGlobal = new double[2];
		cv$var73$stateProbabilityGlobal = new double[2];
	}

	@Override
	public final void allocator() {
		v = new double[2];
		if(!fixedFlag$sample28) {
			m = new double[2][];
			m[0] = new double[2];
			m[1] = new double[2];
		}
		if(!fixedFlag$sample45)
			bias = new double[2];
		if((!fixedFlag$sample53 || !fixedFlag$sample74))
			st = new int[length$flipsMeasured];
		flips = new boolean[length$flipsMeasured];
		constrainedFlag$sample45 = new boolean[2];
		constrainedFlag$sample28 = new boolean[2];
		constrainedFlag$sample74 = new boolean[(length$flipsMeasured - 1)];
		logProbability$sample74 = new double[(length$flipsMeasured - 1)];
		logProbability$sample90 = new double[length$flipsMeasured];
		allocateScratch();
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
		if(!fixedFlag$sample74) {
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
				st[((i$var64 + i$var64) / 2)] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], 2);
		}
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[st[j]]);
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
		if(!fixedFlag$sample74) {
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
				st[((i$var64 + i$var64) / 2)] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], 2);
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
		if(!fixedFlag$sample74) {
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
				st[((i$var64 + i$var64) / 2)] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], 2);
		}
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[st[j]]);
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
		if(!fixedFlag$sample74) {
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
				st[((i$var64 + i$var64) / 2)] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], 2);
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
		if(!fixedFlag$sample74) {
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
				st[((i$var64 + i$var64) / 2)] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], 2);
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
			if(!fixedFlag$sample74) {
				for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
					inferSample74(i$var64);
			}
		} else {
			if(!fixedFlag$sample74) {
				for(int i$var64 = (samples - 1); i$var64 >= 1; i$var64 -= 1)
					inferSample74(i$var64);
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
		for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
			if(!constrainedFlag$sample74[(i$var64 - 1)])
				drawValueSample74(i$var64);
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
		if(!fixedProbFlag$sample74) {
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
				logProbability$sample74[(i$var64 - 1)] = Double.NaN;
		}
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample90) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample90[j] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		v[0] = 0.1;
		v[1] = 0.1;
		samples = length$flipsMeasured;
		for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
			constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
		for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
			constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
		for(int index$constrainedFlag$sample74$1 = 0; index$constrainedFlag$sample74$1 < constrainedFlag$sample74.length; index$constrainedFlag$sample74$1 += 1)
			constrainedFlag$sample74[index$constrainedFlag$sample74$1] = true;
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
		if(fixedFlag$sample74)
			logProbabilityValue$sample74();
		logProbabilityValue$sample90();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample74();
		logProbabilityValue$sample90();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample74();
		logProbabilityValue$sample90();
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
		     + "model HMMTestPart3c(boolean[] flipsMeasured) {\n"
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
		     + "        for(int i:[1..samples))\n"
		     + "            st[(i+i)/2] = categorical(m[st[i-1]]).sample();\n"
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