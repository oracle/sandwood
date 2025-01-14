package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart5b$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMTestPart5b$CoreInterface {
	private double[] bias;
	private double[] cv$var16$countGlobal;
	private double[] cv$var33$stateProbabilityGlobal;
	private double[] cv$var48$stateProbabilityGlobal;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample27 = false;
	private boolean fixedFlag$sample36 = false;
	private boolean fixedFlag$sample51 = false;
	private boolean fixedFlag$sample67 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample27 = false;
	private boolean fixedProbFlag$sample36 = false;
	private boolean fixedProbFlag$sample51 = false;
	private boolean fixedProbFlag$sample67 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample51;
	private double[] logProbability$sample67;
	private double logProbability$st;
	private double logProbability$var11;
	private double logProbability$var16;
	private double logProbability$var20;
	private double logProbability$var25;
	private double logProbability$var32;
	private double logProbability$var33;
	private double[] logProbability$var47;
	private double[] logProbability$var63;
	private double[][] m;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart5b$SingleThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample36 = (cv$value && fixedProbFlag$sample36);
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
	}

	@Override
	public final boolean get$fixedFlag$sample27() {
		return fixedFlag$sample27;
	}

	@Override
	public final void set$fixedFlag$sample27(boolean cv$value) {
		fixedFlag$sample27 = cv$value;
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
		fixedProbFlag$sample67 = (cv$value && fixedProbFlag$sample67);
	}

	@Override
	public final boolean get$fixedFlag$sample36() {
		return fixedFlag$sample36;
	}

	@Override
	public final void set$fixedFlag$sample36(boolean cv$value) {
		fixedFlag$sample36 = cv$value;
		fixedProbFlag$sample36 = (cv$value && fixedProbFlag$sample36);
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
		fixedProbFlag$sample67 = (cv$value && fixedProbFlag$sample67);
	}

	@Override
	public final boolean get$fixedFlag$sample51() {
		return fixedFlag$sample51;
	}

	@Override
	public final void set$fixedFlag$sample51(boolean cv$value) {
		fixedFlag$sample51 = cv$value;
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
		fixedProbFlag$sample67 = (cv$value && fixedProbFlag$sample67);
	}

	@Override
	public final boolean get$fixedFlag$sample67() {
		return fixedFlag$sample67;
	}

	@Override
	public final void set$fixedFlag$sample67(boolean cv$value) {
		fixedFlag$sample67 = cv$value;
		fixedProbFlag$sample67 = (cv$value && fixedProbFlag$sample67);
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
			int cv$sampleValue = (st[0] / 2);
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

	private final void logProbabilityValue$sample51() {
		if(!fixedProbFlag$sample51) {
			double cv$accumulator = 0.0;
			for(int i$var40 = 4; i$var40 < (samples + 3); i$var40 += 1) {
				int cv$sampleValue = (st[(i$var40 - 3)] / 2);
				double[] var46 = m[st[(i$var40 - 4)]];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var46.length))?Math.log(var46[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var47[(i$var40 - 4)] = cv$distributionAccumulator;
				logProbability$sample51[(i$var40 - 4)] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample51 = ((fixedFlag$sample51 && fixedFlag$sample17) && fixedFlag$sample36);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var40 = 4; i$var40 < (samples + 3); i$var40 += 1) {
				double cv$rvAccumulator = logProbability$sample51[(i$var40 - 4)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var47[(i$var40 - 4)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample67() {
		if(!fixedProbFlag$sample67) {
			double cv$accumulator = 0.0;
			for(int j = 5; j < (samples + 5); j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[(j - 5)], bias[st[(j - 5)]]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var63[(j - 5)] = cv$distributionAccumulator;
				logProbability$sample67[(j - 5)] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample67 = (((fixedFlag$sample67 && fixedFlag$sample27) && fixedFlag$sample36) && fixedFlag$sample51);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 5; j < (samples + 5); j += 1) {
				double cv$rvAccumulator = logProbability$sample67[(j - 5)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var63[(j - 5)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample17(int var15) {
		cv$var16$countGlobal[0] = 0.0;
		cv$var16$countGlobal[1] = 0.0;
		if((var15 == 0))
			cv$var16$countGlobal[(st[0] / 2)] = (cv$var16$countGlobal[(st[0] / 2)] + 1.0);
		for(int i$var40 = 4; i$var40 < (samples + 3); i$var40 += 1) {
			if((var15 == st[(i$var40 - 4)]))
				cv$var16$countGlobal[(st[(i$var40 - 3)] / 2)] = (cv$var16$countGlobal[(st[(i$var40 - 3)] / 2)] + 1.0);
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var16$countGlobal, m[var15]);
	}

	private final void sample27(int var24) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int j = 5; j < (samples + 5); j += 1) {
			if((var24 == st[(j - 5)])) {
				cv$count = (cv$count + 1);
				if(flips[(j - 5)])
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
				double[] cv$temp$1$var46 = m[0];
				cv$accumulatedProbabilities = ((((0.0 <= (st[1] / 2)) && ((st[1] / 2) < cv$temp$1$var46.length))?Math.log(cv$temp$1$var46[(st[1] / 2)]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if((0 < samples))
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0], bias[0]) + cv$accumulatedProbabilities);
			cv$var33$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		double[] cv$temp$0$var31 = m[0];
		double cv$accumulatedProbabilities = ((1 < cv$temp$0$var31.length)?Math.log(cv$temp$0$var31[1]):Double.NEGATIVE_INFINITY);
		if((1 < samples)) {
			double[] cv$temp$1$var46 = m[2];
			cv$accumulatedProbabilities = ((((0.0 <= (st[1] / 2)) && ((st[1] / 2) < cv$temp$1$var46.length))?Math.log(cv$temp$1$var46[(st[1] / 2)]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		if((0 < samples))
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0], bias[2]) + cv$accumulatedProbabilities);
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
		st[0] = (DistributionSampling.sampleCategorical(RNG$, cv$var33$stateProbabilityGlobal) * 2);
	}

	private final void sample51(int i$var40) {
		{
			st[(i$var40 - 3)] = 0;
			double[] cv$temp$0$var46 = m[st[(i$var40 - 4)]];
			double cv$accumulatedProbabilities = ((0 < cv$temp$0$var46.length)?Math.log(cv$temp$0$var46[0]):Double.NEGATIVE_INFINITY);
			int index$i$1_2 = (i$var40 + 1);
			if((index$i$1_2 < (samples + 3))) {
				double[] cv$temp$1$var46 = m[0];
				cv$accumulatedProbabilities = ((((0.0 <= (st[(index$i$1_2 - 3)] / 2)) && ((st[(index$i$1_2 - 3)] / 2) < cv$temp$1$var46.length))?Math.log(cv$temp$1$var46[(st[(index$i$1_2 - 3)] / 2)]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[(i$var40 - 3)], bias[0]) + cv$accumulatedProbabilities);
			cv$var48$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		st[(i$var40 - 3)] = 2;
		double[] cv$temp$0$var46 = m[st[(i$var40 - 4)]];
		double cv$accumulatedProbabilities = ((1 < cv$temp$0$var46.length)?Math.log(cv$temp$0$var46[1]):Double.NEGATIVE_INFINITY);
		int index$i$1_2 = (i$var40 + 1);
		if((index$i$1_2 < (samples + 3))) {
			double[] cv$temp$1$var46 = m[2];
			cv$accumulatedProbabilities = ((((0.0 <= (st[(index$i$1_2 - 3)] / 2)) && ((st[(index$i$1_2 - 3)] / 2) < cv$temp$1$var46.length))?Math.log(cv$temp$1$var46[(st[(index$i$1_2 - 3)] / 2)]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[(i$var40 - 3)], bias[2]) + cv$accumulatedProbabilities);
		cv$var48$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = cv$var48$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var48$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var48$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var48$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var48$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var48$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var48$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$var48$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var48$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var48$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var48$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		st[(i$var40 - 3)] = (DistributionSampling.sampleCategorical(RNG$, cv$var48$stateProbabilityGlobal) * 2);
	}

	@Override
	public final void allocateScratch() {
		cv$var16$countGlobal = new double[2];
		cv$var33$stateProbabilityGlobal = new double[2];
		cv$var48$stateProbabilityGlobal = new double[2];
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
		logProbability$var47 = new double[(length$flipsMeasured - 1)];
		logProbability$sample51 = new double[(length$flipsMeasured - 1)];
		logProbability$var63 = new double[length$flipsMeasured];
		logProbability$sample67 = new double[length$flipsMeasured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample17) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		if(!fixedFlag$sample27) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample36)
			st[0] = (DistributionSampling.sampleCategorical(RNG$, m[0]) * 2);
		if(!fixedFlag$sample51) {
			for(int i$var40 = 4; i$var40 < (samples + 3); i$var40 += 1)
				st[(i$var40 - 3)] = (DistributionSampling.sampleCategorical(RNG$, m[st[(i$var40 - 4)]]) * 2);
		}
		if(!fixedFlag$sample67) {
			for(int j = 5; j < (samples + 5); j += 1)
				flips[(j - 5)] = DistributionSampling.sampleBernoulli(RNG$, bias[st[(j - 5)]]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample17) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		if(!fixedFlag$sample27) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample36)
			st[0] = (DistributionSampling.sampleCategorical(RNG$, m[0]) * 2);
		if(!fixedFlag$sample51) {
			for(int i$var40 = 4; i$var40 < (samples + 3); i$var40 += 1)
				st[(i$var40 - 3)] = (DistributionSampling.sampleCategorical(RNG$, m[st[(i$var40 - 4)]]) * 2);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample17) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		if(!fixedFlag$sample27) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample36)
			st[0] = (DistributionSampling.sampleCategorical(RNG$, m[0]) * 2);
		if(!fixedFlag$sample51) {
			for(int i$var40 = 4; i$var40 < (samples + 3); i$var40 += 1)
				st[(i$var40 - 3)] = (DistributionSampling.sampleCategorical(RNG$, m[st[(i$var40 - 4)]]) * 2);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample17) {
				sample17(0);
				sample17(1);
			}
			if(!fixedFlag$sample27) {
				sample27(0);
				sample27(1);
			}
			if(!fixedFlag$sample36)
				sample36();
			if(!fixedFlag$sample51) {
				for(int i$var40 = 4; i$var40 < (samples + 3); i$var40 += 1)
					sample51(i$var40);
			}
		} else {
			if(!fixedFlag$sample51) {
				for(int i$var40 = (samples + 2); i$var40 >= 4; i$var40 -= 1)
					sample51(i$var40);
			}
			if(!fixedFlag$sample36)
				sample36();
			if(!fixedFlag$sample27) {
				sample27(1);
				sample27(0);
			}
			if(!fixedFlag$sample17) {
				sample17(1);
				sample17(0);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		v[0] = 0.1;
		v[1] = 0.1;
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
		for(int i$var40 = 4; i$var40 < (samples + 3); i$var40 += 1)
			logProbability$var47[(i$var40 - 4)] = 0.0;
		if(!fixedProbFlag$sample51) {
			for(int i$var40 = 4; i$var40 < (samples + 3); i$var40 += 1)
				logProbability$sample51[(i$var40 - 4)] = 0.0;
		}
		for(int j = 5; j < (samples + 5); j += 1)
			logProbability$var63[(j - 5)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample67) {
			for(int j = 5; j < (samples + 5); j += 1)
				logProbability$sample67[(j - 5)] = 0.0;
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
		if(fixedFlag$sample51)
			logProbabilityValue$sample51();
		logProbabilityValue$sample67();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample27();
		logProbabilityValue$sample36();
		logProbabilityValue$sample51();
		logProbabilityValue$sample67();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample27();
		logProbabilityValue$sample36();
		logProbabilityValue$sample51();
		logProbabilityValue$sample67();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample17) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		if(!fixedFlag$sample27) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample36)
			st[0] = (DistributionSampling.sampleCategorical(RNG$, m[0]) * 2);
		if(!fixedFlag$sample51) {
			for(int i$var40 = 4; i$var40 < (samples + 3); i$var40 += 1)
				st[(i$var40 - 3)] = (DistributionSampling.sampleCategorical(RNG$, m[st[(i$var40 - 4)]]) * 2);
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart5b(boolean[] flipsMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n        \n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        int[] st = new int[samples];\n\n        st[0] = states * categorical(m[0]).sample();\n\n        for(int i:[4..samples + 3))\n            st[i-3] = states * categorical(m[st[i-4]]).sample();\n            \n        boolean[] flips = new boolean[samples];\n            \n        for(int j:[5..samples+5))\n            flips[j-5] = bernoulli(bias[st[j-5]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
	}
}