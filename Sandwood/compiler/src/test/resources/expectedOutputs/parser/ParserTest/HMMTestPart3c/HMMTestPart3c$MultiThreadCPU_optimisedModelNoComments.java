package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart3c$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart3c$CoreInterface {
	private double[] bias;
	private double[][] cv$var16$countGlobal;
	private double[] cv$var33$stateProbabilityGlobal;
	private double[] cv$var46$stateProbabilityGlobal;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample27 = false;
	private boolean fixedFlag$sample36 = false;
	private boolean fixedFlag$sample49 = false;
	private boolean fixedFlag$sample58 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample27 = false;
	private boolean fixedProbFlag$sample36 = false;
	private boolean fixedProbFlag$sample49 = false;
	private boolean fixedProbFlag$sample58 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample49;
	private double[] logProbability$sample58;
	private double logProbability$st;
	private double logProbability$var11;
	private double logProbability$var16;
	private double logProbability$var20;
	private double logProbability$var25;
	private double logProbability$var32;
	private double logProbability$var33;
	private double[] logProbability$var45;
	private double[] logProbability$var54;
	private double[][] m;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
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
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		setFlag$bias = true;
		fixedProbFlag$sample27 = false;
		fixedProbFlag$sample58 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample17() {
		return fixedFlag$sample17;
	}

	@Override
	public final void set$fixedFlag$sample17(boolean cv$value) {
		fixedFlag$sample17 = cv$value;
		fixedProbFlag$sample17 = (cv$value && fixedProbFlag$sample17);
		fixedProbFlag$sample36 = (cv$value && fixedProbFlag$sample36);
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
	}

	@Override
	public final boolean get$fixedFlag$sample27() {
		return fixedFlag$sample27;
	}

	@Override
	public final void set$fixedFlag$sample27(boolean cv$value) {
		fixedFlag$sample27 = cv$value;
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
		fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
	}

	@Override
	public final boolean get$fixedFlag$sample36() {
		return fixedFlag$sample36;
	}

	@Override
	public final void set$fixedFlag$sample36(boolean cv$value) {
		fixedFlag$sample36 = cv$value;
		fixedProbFlag$sample36 = (cv$value && fixedProbFlag$sample36);
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
		fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
	}

	@Override
	public final boolean get$fixedFlag$sample49() {
		return fixedFlag$sample49;
	}

	@Override
	public final void set$fixedFlag$sample49(boolean cv$value) {
		fixedFlag$sample49 = cv$value;
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
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
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample58 = false;
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
		fixedProbFlag$sample17 = false;
		fixedProbFlag$sample36 = false;
		fixedProbFlag$sample49 = false;
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
		fixedProbFlag$sample36 = false;
		fixedProbFlag$sample49 = false;
		fixedProbFlag$sample58 = false;
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

	private final void logProbabilityValue$sample27() {
		if(!fixedProbFlag$sample27) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityBeta(bias[0], 1.0, 1.0) + DistributionSampling.logProbabilityBeta(bias[1], 1.0, 1.0));
			logProbability$var20 = cv$sampleAccumulator;
			logProbability$var25 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample27 = fixedFlag$sample27;
		} else {
			logProbability$var20 = logProbability$var25;
			logProbability$bias = (logProbability$bias + logProbability$var25);
			logProbability$$model = (logProbability$$model + logProbability$var25);
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var25);
		}
	}

	private final void logProbabilityValue$sample36() {
		if(!fixedProbFlag$sample36) {
			int cv$sampleValue = st[0];
			double[] var31 = m[0];
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var31.length))?Math.log(var31[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var32 = cv$distributionAccumulator;
			logProbability$var33 = cv$distributionAccumulator;
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample36 = (fixedFlag$sample36 && fixedFlag$sample17);
		} else {
			logProbability$var32 = logProbability$var33;
			logProbability$st = (logProbability$st + logProbability$var33);
			logProbability$$model = (logProbability$$model + logProbability$var33);
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var33);
		}
	}

	private final void logProbabilityValue$sample49() {
		if(!fixedProbFlag$sample49) {
			double cv$accumulator = 0.0;
			for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
				int cv$sampleValue = st[((i$var37 + i$var37) / 2)];
				double[] var44 = m[st[(i$var37 - 1)]];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var44.length))?Math.log(var44[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var45[(i$var37 - 1)] = cv$distributionAccumulator;
				logProbability$sample49[(i$var37 - 1)] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample49)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample49 = ((fixedFlag$sample49 && fixedFlag$sample17) && fixedFlag$sample36);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
				double cv$rvAccumulator = logProbability$sample49[(i$var37 - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var45[(i$var37 - 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample49)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample58() {
		if(!fixedProbFlag$sample58) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[j], bias[st[j]]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var54[j] = cv$distributionAccumulator;
				logProbability$sample58[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample58 = (((fixedFlag$sample58 && fixedFlag$sample27) && fixedFlag$sample36) && fixedFlag$sample49);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = logProbability$sample58[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var54[j] = cv$rvAccumulator;
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
		for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
			if((var15 == st[(i$var37 - 1)]))
				cv$countLocal[st[((i$var37 + i$var37) / 2)]] = (cv$countLocal[st[((i$var37 + i$var37) / 2)]] + 1.0);
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var15]);
	}

	private final void sample27(int var24, int threadID$cv$var24, Rng RNG$) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int j = 0; j < samples; j += 1) {
			if((var24 == st[j])) {
				cv$count = (cv$count + 1);
				if(flips[j])
					cv$sum = (cv$sum + 1);
			}
		}
		bias[var24] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample36() {
		{
			double[] cv$temp$0$var31 = m[0];
			double cv$accumulatedProbabilities = ((0 < cv$temp$0$var31.length)?Math.log(cv$temp$0$var31[0]):Double.NEGATIVE_INFINITY);
			if((1 < samples)) {
				double[] cv$temp$1$var44 = m[0];
				cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < cv$temp$1$var44.length))?Math.log(cv$temp$1$var44[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if((0 < samples))
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0], bias[0]) + cv$accumulatedProbabilities);
			cv$var33$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		double[] cv$temp$0$var31 = m[0];
		double cv$accumulatedProbabilities = ((1 < cv$temp$0$var31.length)?Math.log(cv$temp$0$var31[1]):Double.NEGATIVE_INFINITY);
		if((1 < samples)) {
			double[] cv$temp$1$var44 = m[1];
			cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < cv$temp$1$var44.length))?Math.log(cv$temp$1$var44[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		if((0 < samples))
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0], bias[1]) + cv$accumulatedProbabilities);
		cv$var33$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = cv$var33$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var33$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var33$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var33$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			cv$var33$stateProbabilityGlobal[0] = 0.5;
			cv$var33$stateProbabilityGlobal[1] = 0.5;
		} else {
			cv$var33$stateProbabilityGlobal[0] = Math.exp((cv$var33$stateProbabilityGlobal[0] - cv$logSum));
			cv$var33$stateProbabilityGlobal[1] = Math.exp((cv$var33$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var33$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var33$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st[0] = DistributionSampling.sampleCategorical(RNG$, cv$var33$stateProbabilityGlobal);
	}

	private final void sample49(int i$var37) {
		{
			st[((i$var37 + i$var37) / 2)] = 0;
			double[] cv$temp$0$var44 = m[st[(i$var37 - 1)]];
			double cv$accumulatedProbabilities = ((0 < cv$temp$0$var44.length)?Math.log(cv$temp$0$var44[0]):Double.NEGATIVE_INFINITY);
			int index$i$1_2 = (((i$var37 + i$var37) / 2) + 1);
			if((index$i$1_2 < samples)) {
				double[] cv$temp$1$var44 = m[0];
				cv$accumulatedProbabilities = ((((0.0 <= st[((index$i$1_2 + index$i$1_2) / 2)]) && (st[((index$i$1_2 + index$i$1_2) / 2)] < cv$temp$1$var44.length))?Math.log(cv$temp$1$var44[st[((index$i$1_2 + index$i$1_2) / 2)]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			int j = ((i$var37 + i$var37) / 2);
			if((j < samples))
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[j], bias[0]) + cv$accumulatedProbabilities);
			cv$var46$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		st[((i$var37 + i$var37) / 2)] = 1;
		double[] cv$temp$0$var44 = m[st[(i$var37 - 1)]];
		double cv$accumulatedProbabilities = ((1 < cv$temp$0$var44.length)?Math.log(cv$temp$0$var44[1]):Double.NEGATIVE_INFINITY);
		int index$i$1_2 = (((i$var37 + i$var37) / 2) + 1);
		if((index$i$1_2 < samples)) {
			double[] cv$temp$1$var44 = m[1];
			cv$accumulatedProbabilities = ((((0.0 <= st[((index$i$1_2 + index$i$1_2) / 2)]) && (st[((index$i$1_2 + index$i$1_2) / 2)] < cv$temp$1$var44.length))?Math.log(cv$temp$1$var44[st[((index$i$1_2 + index$i$1_2) / 2)]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		int j = ((i$var37 + i$var37) / 2);
		if((j < samples))
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[j], bias[1]) + cv$accumulatedProbabilities);
		cv$var46$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = cv$var46$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var46$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var46$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var46$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			cv$var46$stateProbabilityGlobal[0] = 0.5;
			cv$var46$stateProbabilityGlobal[1] = 0.5;
		} else {
			cv$var46$stateProbabilityGlobal[0] = Math.exp((cv$var46$stateProbabilityGlobal[0] - cv$logSum));
			cv$var46$stateProbabilityGlobal[1] = Math.exp((cv$var46$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var46$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var46$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st[((i$var37 + i$var37) / 2)] = DistributionSampling.sampleCategorical(RNG$, cv$var46$stateProbabilityGlobal);
	}

	@Override
	public final void allocateScratch() {
		int cv$threadCount = threadCount();
		cv$var16$countGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var16$countGlobal[cv$index] = new double[2];
		cv$var33$stateProbabilityGlobal = new double[2];
		cv$var46$stateProbabilityGlobal = new double[2];
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
		logProbability$var45 = new double[(length$flipsMeasured - 1)];
		logProbability$sample49 = new double[(length$flipsMeasured - 1)];
		logProbability$var54 = new double[length$flipsMeasured];
		logProbability$sample58 = new double[length$flipsMeasured];
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

		if(!fixedFlag$sample27)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
							bias[var24] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample36)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample49) {
			for(int i$var37 = 1; i$var37 < samples; i$var37 += 1)
				st[((i$var37 + i$var37) / 2)] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var37 - 1)]]);
		}
		if(!fixedFlag$sample58)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j = forStart$j; j < forEnd$j; j += 1)
							flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[st[j]]);
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

		if(!fixedFlag$sample27)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
							bias[var24] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample36)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample49) {
			for(int i$var37 = 1; i$var37 < samples; i$var37 += 1)
				st[((i$var37 + i$var37) / 2)] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var37 - 1)]]);
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

		if(!fixedFlag$sample27)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
							bias[var24] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample36)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample49) {
			for(int i$var37 = 1; i$var37 < samples; i$var37 += 1)
				st[((i$var37 + i$var37) / 2)] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var37 - 1)]]);
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

			if(!fixedFlag$sample27)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
								sample27(var24, threadID$var24, RNG$1);
					}
				);

			if(!fixedFlag$sample36)
				sample36();
			if(!fixedFlag$sample49) {
				for(int i$var37 = 1; i$var37 < samples; i$var37 += 1)
					sample49(i$var37);
			}
		} else {
			if(!fixedFlag$sample49) {
				for(int i$var37 = (samples - 1); i$var37 >= 1; i$var37 -= 1)
					sample49(i$var37);
			}
			if(!fixedFlag$sample36)
				sample36();
			if(!fixedFlag$sample27)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
								sample27(var24, threadID$var24, RNG$1);
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
		if(!fixedProbFlag$sample27)
			logProbability$var25 = 0.0;
		logProbability$var32 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample36)
			logProbability$var33 = 0.0;
		for(int i$var37 = 1; i$var37 < samples; i$var37 += 1)
			logProbability$var45[(i$var37 - 1)] = 0.0;
		if(!fixedProbFlag$sample49) {
			for(int i$var37 = 1; i$var37 < samples; i$var37 += 1)
				logProbability$sample49[(i$var37 - 1)] = 0.0;
		}
		for(int j = 0; j < samples; j += 1)
			logProbability$var54[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample58) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample58[j] = 0.0;
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
		if(fixedFlag$sample27)
			logProbabilityValue$sample27();
		if(fixedFlag$sample36)
			logProbabilityValue$sample36();
		if(fixedFlag$sample49)
			logProbabilityValue$sample49();
		logProbabilityValue$sample58();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample27();
		logProbabilityValue$sample36();
		logProbabilityValue$sample49();
		logProbabilityValue$sample58();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample27();
		logProbabilityValue$sample36();
		logProbabilityValue$sample49();
		logProbabilityValue$sample58();
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

		if(!fixedFlag$sample27)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
							bias[var24] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample36)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample49) {
			for(int i$var37 = 1; i$var37 < samples; i$var37 += 1)
				st[((i$var37 + i$var37) / 2)] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var37 - 1)]]);
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart3c(boolean[] flipsMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n        \n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        int[] st = new int[samples];\n\n        st[0] = categorical(m[0]).sample();\n\n        for(int i:[1..samples))\n            st[(i+i)/2] = categorical(m[st[i-1]]).sample();\n            \n        boolean[] flips = new boolean[samples];\n            \n        for(int j:[0..samples))\n            flips[j] = bernoulli(bias[st[j]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
	}
}