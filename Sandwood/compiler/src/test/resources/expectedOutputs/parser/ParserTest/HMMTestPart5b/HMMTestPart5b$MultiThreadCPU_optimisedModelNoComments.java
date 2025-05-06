package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart5b$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart5b$CoreInterface {
	private double[] bias;
	private double[][] cv$var28$countGlobal;
	private double[] cv$var52$stateProbabilityGlobal;
	private double[] cv$var75$stateProbabilityGlobal;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedFlag$sample76 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean fixedProbFlag$sample76 = false;
	private boolean fixedProbFlag$sample99 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample76;
	private double[] logProbability$sample99;
	private double logProbability$st;
	private double logProbability$var16;
	private double logProbability$var28;
	private double logProbability$var32;
	private double logProbability$var44;
	private double logProbability$var51;
	private double logProbability$var52;
	private double[] logProbability$var74;
	private double[] logProbability$var97;
	private double[][] m;
	private int samples;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart5b$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		fixedProbFlag$sample45 = false;
		fixedProbFlag$sample99 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		fixedFlag$sample28 = cv$value;
		fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
		fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		fixedFlag$sample45 = cv$value;
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
		fixedProbFlag$sample99 = (cv$value && fixedProbFlag$sample99);
	}

	@Override
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	@Override
	public final void set$fixedFlag$sample53(boolean cv$value) {
		fixedFlag$sample53 = cv$value;
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
		fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
		fixedProbFlag$sample99 = (cv$value && fixedProbFlag$sample99);
	}

	@Override
	public final boolean get$fixedFlag$sample76() {
		return fixedFlag$sample76;
	}

	@Override
	public final void set$fixedFlag$sample76(boolean cv$value) {
		fixedFlag$sample76 = cv$value;
		fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
		fixedProbFlag$sample99 = (cv$value && fixedProbFlag$sample99);
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
		fixedProbFlag$sample28 = false;
		fixedProbFlag$sample53 = false;
		fixedProbFlag$sample76 = false;
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
		fixedProbFlag$sample53 = false;
		fixedProbFlag$sample76 = false;
		fixedProbFlag$sample99 = false;
	}

	@Override
	public final int get$states() {
		return 2;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityDirichlet(m[0], v, 2) + DistributionSampling.logProbabilityDirichlet(m[1], v, 2));
			logProbability$var16 = cv$sampleAccumulator;
			logProbability$var28 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			logProbability$var16 = logProbability$var28;
			logProbability$m = (logProbability$m + logProbability$var28);
			logProbability$$model = (logProbability$$model + logProbability$var28);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var28);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!fixedProbFlag$sample45) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityBeta(bias[0], 1.0, 1.0) + DistributionSampling.logProbabilityBeta(bias[1], 1.0, 1.0));
			logProbability$var32 = cv$sampleAccumulator;
			logProbability$var44 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample45 = fixedFlag$sample45;
		} else {
			logProbability$var32 = logProbability$var44;
			logProbability$bias = (logProbability$bias + logProbability$var44);
			logProbability$$model = (logProbability$$model + logProbability$var44);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var44);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			int cv$sampleValue = (st[0] / 2);
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 2))?Math.log(m[0][cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var51 = cv$distributionAccumulator;
			logProbability$var52 = cv$distributionAccumulator;
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedFlag$sample28);
		} else {
			logProbability$var51 = logProbability$var52;
			logProbability$st = (logProbability$st + logProbability$var52);
			logProbability$$model = (logProbability$$model + logProbability$var52);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var52);
		}
	}

	private final void logProbabilityValue$sample76() {
		if(!fixedProbFlag$sample76) {
			double cv$accumulator = 0.0;
			for(int i$var67 = 4; i$var67 < (samples + 3); i$var67 += 1) {
				int cv$sampleValue = (st[(i$var67 - 3)] / 2);
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 2))?Math.log(m[st[(i$var67 - 4)]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var74[(i$var67 - 4)] = cv$distributionAccumulator;
				logProbability$sample76[(i$var67 - 4)] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample76)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample76 = ((fixedFlag$sample76 && fixedFlag$sample28) && fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var67 = 4; i$var67 < (samples + 3); i$var67 += 1) {
				double cv$rvAccumulator = logProbability$sample76[(i$var67 - 4)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var74[(i$var67 - 4)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample76)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample99() {
		if(!fixedProbFlag$sample99) {
			double cv$accumulator = 0.0;
			for(int j = 5; j < (samples + 5); j += 1) {
				double var96 = bias[st[(j - 5)]];
				double cv$distributionAccumulator = Math.log((flips[(j - 5)]?var96:(1.0 - var96)));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var97[(j - 5)] = cv$distributionAccumulator;
				logProbability$sample99[(j - 5)] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample99 = ((fixedFlag$sample45 && fixedFlag$sample53) && fixedFlag$sample76);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 5; j < (samples + 5); j += 1) {
				double cv$rvAccumulator = logProbability$sample99[(j - 5)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var97[(j - 5)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample28(int var27, int threadID$cv$var27, Rng RNG$) {
		double[] cv$countLocal = cv$var28$countGlobal[threadID$cv$var27];
		cv$countLocal[0] = 0.0;
		cv$countLocal[1] = 0.0;
		if((var27 == 0))
			cv$countLocal[(st[0] / 2)] = (cv$countLocal[(st[0] / 2)] + 1.0);
		for(int i$var67 = 4; i$var67 < (samples + 3); i$var67 += 1) {
			if((var27 == st[(i$var67 - 4)]))
				cv$countLocal[(st[(i$var67 - 3)] / 2)] = (cv$countLocal[(st[(i$var67 - 3)] / 2)] + 1.0);
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var27], 2);
	}

	private final void sample45(int var43, int threadID$cv$var43, Rng RNG$) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int j = 5; j < (samples + 5); j += 1) {
			if((var43 == st[(j - 5)])) {
				cv$count = (cv$count + 1);
				if(flips[(j - 5)])
					cv$sum = (cv$sum + 1);
			}
		}
		bias[var43] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample53() {
		{
			double cv$accumulatedProbabilities = Math.log(m[0][0]);
			if((1 < samples))
				cv$accumulatedProbabilities = ((((0.0 <= (st[1] / 2)) && ((st[1] / 2) < 2))?Math.log(m[0][(st[1] / 2)]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			if((0 < samples)) {
				double cv$temp$4$var96 = bias[0];
				cv$accumulatedProbabilities = (Math.log((flips[0]?cv$temp$4$var96:(1.0 - cv$temp$4$var96))) + cv$accumulatedProbabilities);
			}
			cv$var52$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		double cv$accumulatedProbabilities = Math.log(m[0][1]);
		if((1 < samples))
			cv$accumulatedProbabilities = ((((0.0 <= (st[1] / 2)) && ((st[1] / 2) < 2))?Math.log(m[2][(st[1] / 2)]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		if((0 < samples)) {
			double cv$temp$4$var96 = bias[2];
			cv$accumulatedProbabilities = (Math.log((flips[0]?cv$temp$4$var96:(1.0 - cv$temp$4$var96))) + cv$accumulatedProbabilities);
		}
		cv$var52$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
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
		st[0] = (DistributionSampling.sampleCategorical(RNG$, cv$var52$stateProbabilityGlobal, 2) * 2);
	}

	private final void sample76(int i$var67) {
		{
			st[(i$var67 - 3)] = 0;
			double cv$accumulatedProbabilities = Math.log(m[st[(i$var67 - 4)]][0]);
			int index$i$2_2 = (i$var67 + 1);
			if((index$i$2_2 < (samples + 3)))
				cv$accumulatedProbabilities = ((((0.0 <= (st[(index$i$2_2 - 3)] / 2)) && ((st[(index$i$2_2 - 3)] / 2) < 2))?Math.log(m[0][(st[(index$i$2_2 - 3)] / 2)]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			double cv$temp$4$var96 = bias[0];
			cv$accumulatedProbabilities = (Math.log((flips[(i$var67 - 3)]?cv$temp$4$var96:(1.0 - cv$temp$4$var96))) + cv$accumulatedProbabilities);
			cv$var75$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		st[(i$var67 - 3)] = 2;
		double cv$accumulatedProbabilities = Math.log(m[st[(i$var67 - 4)]][1]);
		int index$i$2_2 = (i$var67 + 1);
		if((index$i$2_2 < (samples + 3)))
			cv$accumulatedProbabilities = ((((0.0 <= (st[(index$i$2_2 - 3)] / 2)) && ((st[(index$i$2_2 - 3)] / 2) < 2))?Math.log(m[2][(st[(index$i$2_2 - 3)] / 2)]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		double cv$temp$4$var96 = bias[2];
		cv$accumulatedProbabilities = (Math.log((flips[(i$var67 - 3)]?cv$temp$4$var96:(1.0 - cv$temp$4$var96))) + cv$accumulatedProbabilities);
		cv$var75$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = cv$var75$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var75$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var75$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var75$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			cv$var75$stateProbabilityGlobal[0] = 0.5;
			cv$var75$stateProbabilityGlobal[1] = 0.5;
		} else {
			cv$var75$stateProbabilityGlobal[0] = Math.exp((cv$var75$stateProbabilityGlobal[0] - cv$logSum));
			cv$var75$stateProbabilityGlobal[1] = Math.exp((cv$var75$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var75$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var75$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st[(i$var67 - 3)] = (DistributionSampling.sampleCategorical(RNG$, cv$var75$stateProbabilityGlobal, 2) * 2);
	}

	@Override
	public final void allocateScratch() {
		int cv$threadCount = threadCount();
		cv$var28$countGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var28$countGlobal[cv$index] = new double[2];
		cv$var52$stateProbabilityGlobal = new double[2];
		cv$var75$stateProbabilityGlobal = new double[2];
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
		if((!fixedFlag$sample53 || !fixedFlag$sample76))
			st = new int[length$flipsMeasured];
		flips = new boolean[length$flipsMeasured];
		logProbability$var74 = new double[(length$flipsMeasured - 1)];
		logProbability$sample76 = new double[(length$flipsMeasured - 1)];
		logProbability$var97 = new double[length$flipsMeasured];
		logProbability$sample99 = new double[length$flipsMeasured];
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
			st[0] = (DistributionSampling.sampleCategorical(RNG$, m[0], 2) * 2);
		if(!fixedFlag$sample76) {
			for(int i$var67 = 4; i$var67 < (samples + 3); i$var67 += 1)
				st[(i$var67 - 3)] = (DistributionSampling.sampleCategorical(RNG$, m[st[(i$var67 - 4)]], 2) * 2);
		}
		parallelFor(RNG$, 5, (samples + 5), 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						flips[(j - 5)] = DistributionSampling.sampleBernoulli(RNG$1, bias[st[(j - 5)]]);
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
			st[0] = (DistributionSampling.sampleCategorical(RNG$, m[0], 2) * 2);
		if(!fixedFlag$sample76) {
			for(int i$var67 = 4; i$var67 < (samples + 3); i$var67 += 1)
				st[(i$var67 - 3)] = (DistributionSampling.sampleCategorical(RNG$, m[st[(i$var67 - 4)]], 2) * 2);
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
			st[0] = (DistributionSampling.sampleCategorical(RNG$, m[0], 2) * 2);
		if(!fixedFlag$sample76) {
			for(int i$var67 = 4; i$var67 < (samples + 3); i$var67 += 1)
				st[(i$var67 - 3)] = (DistributionSampling.sampleCategorical(RNG$, m[st[(i$var67 - 4)]], 2) * 2);
		}
		parallelFor(RNG$, 5, (samples + 5), 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						flips[(j - 5)] = DistributionSampling.sampleBernoulli(RNG$1, bias[st[(j - 5)]]);
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
			st[0] = (DistributionSampling.sampleCategorical(RNG$, m[0], 2) * 2);
		if(!fixedFlag$sample76) {
			for(int i$var67 = 4; i$var67 < (samples + 3); i$var67 += 1)
				st[(i$var67 - 3)] = (DistributionSampling.sampleCategorical(RNG$, m[st[(i$var67 - 4)]], 2) * 2);
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
			st[0] = (DistributionSampling.sampleCategorical(RNG$, m[0], 2) * 2);
		if(!fixedFlag$sample76) {
			for(int i$var67 = 4; i$var67 < (samples + 3); i$var67 += 1)
				st[(i$var67 - 3)] = (DistributionSampling.sampleCategorical(RNG$, m[st[(i$var67 - 4)]], 2) * 2);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample28)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								sample28(var27, threadID$var27, RNG$1);
					}
				);

			if(!fixedFlag$sample45)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
								sample45(var43, threadID$var43, RNG$1);
					}
				);

			if(!fixedFlag$sample53)
				sample53();
			if(!fixedFlag$sample76) {
				for(int i$var67 = 4; i$var67 < (samples + 3); i$var67 += 1)
					sample76(i$var67);
			}
		} else {
			if(!fixedFlag$sample76) {
				for(int i$var67 = (samples + 2); i$var67 >= 4; i$var67 -= 1)
					sample76(i$var67);
			}
			if(!fixedFlag$sample53)
				sample53();
			if(!fixedFlag$sample45)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
								sample45(var43, threadID$var43, RNG$1);
					}
				);

			if(!fixedFlag$sample28)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								sample28(var27, threadID$var27, RNG$1);
					}
				);

		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$i$var13, int forEnd$i$var13, int threadID$i$var13, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var13 = forStart$i$var13; i$var13 < forEnd$i$var13; i$var13 += 1)
						v[i$var13] = 0.1;
			}
		);
		samples = length$flipsMeasured;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var16 = Double.NaN;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var28 = Double.NaN;
		logProbability$var32 = Double.NaN;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$var44 = Double.NaN;
		logProbability$var51 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$var52 = Double.NaN;
		for(int i$var67 = 4; i$var67 < (samples + 3); i$var67 += 1)
			logProbability$var74[(i$var67 - 4)] = Double.NaN;
		if(!fixedProbFlag$sample76) {
			for(int i$var67 = 4; i$var67 < (samples + 3); i$var67 += 1)
				logProbability$sample76[(i$var67 - 4)] = Double.NaN;
		}
		for(int j = 5; j < (samples + 5); j += 1)
			logProbability$var97[(j - 5)] = Double.NaN;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample99) {
			for(int j = 5; j < (samples + 5); j += 1)
				logProbability$sample99[(j - 5)] = Double.NaN;
		}
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
		if(fixedFlag$sample76)
			logProbabilityValue$sample76();
		logProbabilityValue$sample99();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample76();
		logProbabilityValue$sample99();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample76();
		logProbabilityValue$sample99();
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
		     + "model HMMTestPart5b(boolean[] flipsMeasured) {\n"
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
		     + "        st[0] = states * categorical(m[0]).sample();\n"
		     + "\n"
		     + "        for(int i:[4..samples + 3))\n"
		     + "            st[i-3] = states * categorical(m[st[i-4]]).sample();\n"
		     + "            \n"
		     + "        boolean[] flips = new boolean[samples];\n"
		     + "            \n"
		     + "        for(int j:[5..samples+5))\n"
		     + "            flips[j-5] = bernoulli(bias[st[j-5]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}