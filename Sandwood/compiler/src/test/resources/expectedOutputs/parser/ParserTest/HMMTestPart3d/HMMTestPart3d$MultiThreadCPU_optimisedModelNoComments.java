package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart3d$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart3d$CoreInterface {
	private double[] bias;
	private double[][] cv$var16$countGlobal;
	private double[] cv$var34$stateProbabilityGlobal;
	private double[] cv$var50$stateProbabilityGlobal;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample27 = false;
	private boolean fixedFlag$sample37 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedFlag$sample80 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample27 = false;
	private boolean fixedProbFlag$sample37 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean fixedProbFlag$sample80 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int[][] indirection;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample53;
	private double[] logProbability$sample80;
	private double logProbability$st;
	private double logProbability$st2;
	private double logProbability$var11;
	private double logProbability$var16;
	private double logProbability$var20;
	private double logProbability$var25;
	private double logProbability$var33;
	private double logProbability$var34;
	private double[] logProbability$var49;
	private double[] logProbability$var76;
	private double[][] m;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[] st;
	private int[] st2;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart3d$MultiThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample37 = (cv$value && fixedProbFlag$sample37);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$fixedFlag$sample27() {
		return fixedFlag$sample27;
	}

	@Override
	public final void set$fixedFlag$sample27(boolean cv$value) {
		fixedFlag$sample27 = cv$value;
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
		fixedProbFlag$sample80 = (cv$value && fixedProbFlag$sample80);
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
		fixedProbFlag$sample80 = (cv$value && fixedProbFlag$sample80);
	}

	@Override
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	@Override
	public final void set$fixedFlag$sample53(boolean cv$value) {
		fixedFlag$sample53 = cv$value;
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
		fixedProbFlag$sample80 = (cv$value && fixedProbFlag$sample80);
	}

	@Override
	public final boolean get$fixedFlag$sample80() {
		return fixedFlag$sample80;
	}

	@Override
	public final void set$fixedFlag$sample80(boolean cv$value) {
		fixedFlag$sample80 = cv$value;
		fixedProbFlag$sample80 = (cv$value && fixedProbFlag$sample80);
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
	public final double get$logProbability$st2() {
		return logProbability$st2;
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
	public final int[] get$st2() {
		return st2;
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

	private final void logProbabilityValue$sample37() {
		if(!fixedProbFlag$sample37) {
			int cv$sampleValue = st[0];
			double[] var32 = m[0];
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var32.length))?Math.log(var32[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var33 = cv$distributionAccumulator;
			logProbability$var34 = cv$distributionAccumulator;
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			boolean cv$guard$st2 = true;
			logProbability$st2 = (logProbability$st2 + cv$distributionAccumulator);
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
				if(((0 == (indirection[(i$var43 - 1)][i$var43] / i$var43)) && !cv$guard$st2)) {
					cv$guard$st2 = true;
					logProbability$st2 = (logProbability$st2 + cv$distributionAccumulator);
				}
			}
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample37)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample37 = (fixedFlag$sample37 && fixedFlag$sample17);
		} else {
			logProbability$var33 = logProbability$var34;
			logProbability$st = (logProbability$st + logProbability$var34);
			boolean cv$guard$st2 = true;
			logProbability$st2 = (logProbability$st2 + logProbability$var34);
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
				if(((0 == (indirection[(i$var43 - 1)][i$var43] / i$var43)) && !cv$guard$st2)) {
					cv$guard$st2 = true;
					logProbability$st2 = (logProbability$st2 + logProbability$var34);
				}
			}
			logProbability$$model = (logProbability$$model + logProbability$var34);
			if(fixedFlag$sample37)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var34);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$accumulator = 0.0;
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
				int cv$sampleValue = st[i$var43];
				double[] var48 = m[(samples - st2[(i$var43 - 1)])];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var48.length))?Math.log(var48[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var49[(i$var43 - 1)] = cv$distributionAccumulator;
				logProbability$sample53[(i$var43 - 1)] = cv$distributionAccumulator;
				boolean cv$guard$st2 = false;
				for(int index$i$2_1 = 1; index$i$2_1 < samples; index$i$2_1 += 1) {
					if(((i$var43 == (indirection[(index$i$2_1 - 1)][index$i$2_1] / index$i$2_1)) && !cv$guard$st2)) {
						cv$guard$st2 = true;
						logProbability$st2 = (logProbability$st2 + cv$distributionAccumulator);
					}
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample53 = ((fixedFlag$sample53 && fixedFlag$sample17) && fixedFlag$sample37);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
				double cv$sampleValue = logProbability$sample53[(i$var43 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var49[(i$var43 - 1)] = cv$sampleValue;
				boolean cv$guard$st2 = false;
				for(int index$i$3_1 = 1; index$i$3_1 < samples; index$i$3_1 += 1) {
					if(((i$var43 == (indirection[(index$i$3_1 - 1)][index$i$3_1] / index$i$3_1)) && !cv$guard$st2)) {
						cv$guard$st2 = true;
						logProbability$st2 = (logProbability$st2 + cv$sampleValue);
					}
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample80() {
		if(!fixedProbFlag$sample80) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[j], bias[(samples - st2[j])]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var76[j] = cv$distributionAccumulator;
				logProbability$sample80[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample80 = (((fixedFlag$sample80 && fixedFlag$sample27) && fixedFlag$sample37) && fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = logProbability$sample80[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var76[j] = cv$rvAccumulator;
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
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if((var15 == (samples - st2[(i$var43 - 1)])))
				cv$countLocal[st[i$var43]] = (cv$countLocal[st[i$var43]] + 1.0);
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var15]);
	}

	private final void sample27(int var24, int threadID$cv$var24, Rng RNG$) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int j = 0; j < samples; j += 1) {
			if((var24 == (samples - st2[j]))) {
				cv$count = (cv$count + 1);
				if(flips[j])
					cv$sum = (cv$sum + 1);
			}
		}
		bias[var24] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample37() {
		{
			st[0] = 0;
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
				if((0 == (indirection[(i$var43 - 1)][i$var43] / i$var43)))
					st2[(indirection[(i$var43 - 1)][i$var43] / i$var43)] = (samples - st[(indirection[(i$var43 - 1)][i$var43] / i$var43)]);
			}
			double[] cv$temp$0$var32 = m[0];
			double cv$accumulatedProbabilities = ((0 < cv$temp$0$var32.length)?Math.log(cv$temp$0$var32[0]):Double.NEGATIVE_INFINITY);
			if((1 < samples)) {
				double[] cv$temp$1$var48 = m[0];
				cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < cv$temp$1$var48.length))?Math.log(cv$temp$1$var48[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
				if((0 == (indirection[(i$var43 - 1)][i$var43] / i$var43))) {
					int index$i$4_4 = ((indirection[(i$var43 - 1)][i$var43] / i$var43) + 1);
					if(((1 <= index$i$4_4) && (index$i$4_4 < samples))) {
						double[] cv$temp$2$var48 = m[0];
						cv$accumulatedProbabilities = ((((0.0 <= st[index$i$4_4]) && (st[index$i$4_4] < cv$temp$2$var48.length))?Math.log(cv$temp$2$var48[st[index$i$4_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((0 < samples))
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0], bias[0]) + cv$accumulatedProbabilities);
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
				if((0 == (indirection[(i$var43 - 1)][i$var43] / i$var43))) {
					int j = (indirection[(i$var43 - 1)][i$var43] / i$var43);
					if(((0 <= j) && (j < samples)))
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[j], bias[0]) + cv$accumulatedProbabilities);
				}
			}
			cv$var34$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		st[0] = 1;
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if((0 == (indirection[(i$var43 - 1)][i$var43] / i$var43)))
				st2[(indirection[(i$var43 - 1)][i$var43] / i$var43)] = (samples - st[(indirection[(i$var43 - 1)][i$var43] / i$var43)]);
		}
		double[] cv$temp$0$var32 = m[0];
		double cv$accumulatedProbabilities = ((1 < cv$temp$0$var32.length)?Math.log(cv$temp$0$var32[1]):Double.NEGATIVE_INFINITY);
		if((1 < samples)) {
			double[] cv$temp$1$var48 = m[1];
			cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < cv$temp$1$var48.length))?Math.log(cv$temp$1$var48[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if((0 == (indirection[(i$var43 - 1)][i$var43] / i$var43))) {
				int index$i$4_4 = ((indirection[(i$var43 - 1)][i$var43] / i$var43) + 1);
				if(((1 <= index$i$4_4) && (index$i$4_4 < samples))) {
					double[] cv$temp$2$var48 = m[1];
					cv$accumulatedProbabilities = ((((0.0 <= st[index$i$4_4]) && (st[index$i$4_4] < cv$temp$2$var48.length))?Math.log(cv$temp$2$var48[st[index$i$4_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
		}
		if((0 < samples))
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0], bias[1]) + cv$accumulatedProbabilities);
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if((0 == (indirection[(i$var43 - 1)][i$var43] / i$var43))) {
				int j = (indirection[(i$var43 - 1)][i$var43] / i$var43);
				if(((0 <= j) && (j < samples)))
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[j], bias[1]) + cv$accumulatedProbabilities);
			}
		}
		cv$var34$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = cv$var34$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var34$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var34$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var34$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var34$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var34$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var34$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$var34$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var34$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var34$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var34$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		st[0] = DistributionSampling.sampleCategorical(RNG$, cv$var34$stateProbabilityGlobal);
		st2[0] = (samples - st[0]);
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if((0 == (indirection[(i$var43 - 1)][i$var43] / i$var43)))
				st2[(indirection[(i$var43 - 1)][i$var43] / i$var43)] = (samples - st[(indirection[(i$var43 - 1)][i$var43] / i$var43)]);
		}
	}

	private final void sample53(int i$var43) {
		{
			st[i$var43] = 0;
			for(int index$i$1_1 = 1; index$i$1_1 < samples; index$i$1_1 += 1) {
				if((i$var43 == (indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)))
					st2[(indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)] = (samples - st[(indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)]);
			}
			double[] cv$temp$0$var48 = m[(samples - st2[(i$var43 - 1)])];
			double cv$accumulatedProbabilities = ((0 < cv$temp$0$var48.length)?Math.log(cv$temp$0$var48[0]):Double.NEGATIVE_INFINITY);
			for(int index$i$2_2 = 1; index$i$2_2 < samples; index$i$2_2 += 1) {
				if((i$var43 == (indirection[(index$i$2_2 - 1)][index$i$2_2] / index$i$2_2))) {
					int index$i$2_4 = ((indirection[(index$i$2_2 - 1)][index$i$2_2] / index$i$2_2) + 1);
					if(((1 <= index$i$2_4) && (index$i$2_4 < samples))) {
						double[] cv$temp$1$var48 = m[0];
						cv$accumulatedProbabilities = ((((0.0 <= st[index$i$2_4]) && (st[index$i$2_4] < cv$temp$1$var48.length))?Math.log(cv$temp$1$var48[st[index$i$2_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int index$i$5_2 = 1; index$i$5_2 < samples; index$i$5_2 += 1) {
				if((i$var43 == (indirection[(index$i$5_2 - 1)][index$i$5_2] / index$i$5_2))) {
					int j = (indirection[(index$i$5_2 - 1)][index$i$5_2] / index$i$5_2);
					if(((0 <= j) && (j < samples)))
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[j], bias[0]) + cv$accumulatedProbabilities);
				}
			}
			cv$var50$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		st[i$var43] = 1;
		for(int index$i$1_1 = 1; index$i$1_1 < samples; index$i$1_1 += 1) {
			if((i$var43 == (indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)))
				st2[(indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)] = (samples - st[(indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)]);
		}
		double[] cv$temp$0$var48 = m[(samples - st2[(i$var43 - 1)])];
		double cv$accumulatedProbabilities = ((1 < cv$temp$0$var48.length)?Math.log(cv$temp$0$var48[1]):Double.NEGATIVE_INFINITY);
		for(int index$i$2_2 = 1; index$i$2_2 < samples; index$i$2_2 += 1) {
			if((i$var43 == (indirection[(index$i$2_2 - 1)][index$i$2_2] / index$i$2_2))) {
				int index$i$2_4 = ((indirection[(index$i$2_2 - 1)][index$i$2_2] / index$i$2_2) + 1);
				if(((1 <= index$i$2_4) && (index$i$2_4 < samples))) {
					double[] cv$temp$1$var48 = m[1];
					cv$accumulatedProbabilities = ((((0.0 <= st[index$i$2_4]) && (st[index$i$2_4] < cv$temp$1$var48.length))?Math.log(cv$temp$1$var48[st[index$i$2_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int index$i$5_2 = 1; index$i$5_2 < samples; index$i$5_2 += 1) {
			if((i$var43 == (indirection[(index$i$5_2 - 1)][index$i$5_2] / index$i$5_2))) {
				int j = (indirection[(index$i$5_2 - 1)][index$i$5_2] / index$i$5_2);
				if(((0 <= j) && (j < samples)))
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[j], bias[1]) + cv$accumulatedProbabilities);
			}
		}
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
		st[i$var43] = DistributionSampling.sampleCategorical(RNG$, cv$var50$stateProbabilityGlobal);
		for(int index$i$8_1 = 1; index$i$8_1 < samples; index$i$8_1 += 1) {
			if((i$var43 == (indirection[(index$i$8_1 - 1)][index$i$8_1] / index$i$8_1)))
				st2[(indirection[(index$i$8_1 - 1)][index$i$8_1] / index$i$8_1)] = (samples - st[(indirection[(index$i$8_1 - 1)][index$i$8_1] / index$i$8_1)]);
		}
	}

	@Override
	public final void allocateScratch() {
		int cv$threadCount = threadCount();
		cv$var16$countGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var16$countGlobal[cv$index] = new double[2];
		cv$var34$stateProbabilityGlobal = new double[2];
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
		st2 = new int[length$flipsMeasured];
		indirection = new int[(length$flipsMeasured - 1)][];
		for(int i$var43 = 1; i$var43 < length$flipsMeasured; i$var43 += 1)
			indirection[(i$var43 - 1)] = new int[(i$var43 + 1)];
		if(!setFlag$flips)
			flips = new boolean[length$flipsMeasured];
		logProbability$var49 = new double[(length$flipsMeasured - 1)];
		logProbability$sample53 = new double[(length$flipsMeasured - 1)];
		logProbability$var76 = new double[length$flipsMeasured];
		logProbability$sample80 = new double[length$flipsMeasured];
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

		if(!fixedFlag$sample37) {
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
			st2[0] = (samples - st[0]);
		}
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if(!fixedFlag$sample53)
				st[i$var43] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var43 - 1)])]);
			if((!fixedFlag$sample37 || !fixedFlag$sample53))
				st2[(indirection[(i$var43 - 1)][i$var43] / i$var43)] = (samples - st[(indirection[(i$var43 - 1)][i$var43] / i$var43)]);
		}
		if(!fixedFlag$sample80)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j = forStart$j; j < forEnd$j; j += 1)
							flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[(samples - st2[j])]);
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

		if(!fixedFlag$sample37) {
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
			st2[0] = (samples - st[0]);
		}
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if(!fixedFlag$sample53)
				st[i$var43] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var43 - 1)])]);
			if((!fixedFlag$sample37 || !fixedFlag$sample53))
				st2[(indirection[(i$var43 - 1)][i$var43] / i$var43)] = (samples - st[(indirection[(i$var43 - 1)][i$var43] / i$var43)]);
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

		if(!fixedFlag$sample37) {
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
			st2[0] = (samples - st[0]);
		}
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if(!fixedFlag$sample53)
				st[i$var43] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var43 - 1)])]);
			if((!fixedFlag$sample37 || !fixedFlag$sample53))
				st2[(indirection[(i$var43 - 1)][i$var43] / i$var43)] = (samples - st[(indirection[(i$var43 - 1)][i$var43] / i$var43)]);
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

			if(!fixedFlag$sample37)
				sample37();
			if(!fixedFlag$sample53) {
				for(int i$var43 = 1; i$var43 < samples; i$var43 += 1)
					sample53(i$var43);
			}
		} else {
			if(!fixedFlag$sample53) {
				for(int i$var43 = (samples - 1); i$var43 >= 1; i$var43 -= 1)
					sample53(i$var43);
			}
			if(!fixedFlag$sample37)
				sample37();
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
		for(int index$i$var43 = 1; index$i$var43 < length$flipsMeasured; index$i$var43 += 1) {
			int i$var43 = index$i$var43;
			parallelFor(RNG$, 0, (i$var43 + 1), 1,
				(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int k = forStart$k; k < forEnd$k; k += 1)
							indirection[(i$var43 - 1)][k] = (k * i$var43);
				}
			);
		}
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
		logProbability$var33 = 0.0;
		logProbability$st = 0.0;
		logProbability$st2 = 0.0;
		if(!fixedProbFlag$sample37)
			logProbability$var34 = 0.0;
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1)
			logProbability$var49[(i$var43 - 1)] = 0.0;
		if(!fixedProbFlag$sample53) {
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1)
				logProbability$sample53[(i$var43 - 1)] = 0.0;
		}
		for(int j = 0; j < samples; j += 1)
			logProbability$var76[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample80) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample80[j] = 0.0;
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
		if(fixedFlag$sample37)
			logProbabilityValue$sample37();
		if(fixedFlag$sample53)
			logProbabilityValue$sample53();
		logProbabilityValue$sample80();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample27();
		logProbabilityValue$sample37();
		logProbabilityValue$sample53();
		logProbabilityValue$sample80();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample27();
		logProbabilityValue$sample37();
		logProbabilityValue$sample53();
		logProbabilityValue$sample80();
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

		if(!fixedFlag$sample37) {
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
			st2[0] = (samples - st[0]);
		}
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if(!fixedFlag$sample53)
				st[i$var43] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var43 - 1)])]);
			if((!fixedFlag$sample37 || !fixedFlag$sample53))
				st2[(indirection[(i$var43 - 1)][i$var43] / i$var43)] = (samples - st[(indirection[(i$var43 - 1)][i$var43] / i$var43)]);
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
	public final void setIntermediates() {
		if(setFlag$st) {
			st2[0] = (samples - st[0]);
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1)
				st2[(indirection[(i$var43 - 1)][i$var43] / i$var43)] = (samples - st[(indirection[(i$var43 - 1)][i$var43] / i$var43)]);
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart3d(boolean[] flipsMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n\n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        int[] st = new int[samples];\n        int[] st2 = new int[samples];\n\n        st[0] = categorical(m[0]).sample();\n        st2[0] = samples - st[0];\n\n        for(int i:[1..samples)) {\n            st[i] = categorical(m[samples - st2[i-1]]).sample();\n            \n            int[] indirection = new int[i+1];\n            for(int k:[0..i])\n                indirection[k] = k*i; \n                \n            st2[indirection[i]/i] = samples - st[indirection[i]/i];\n        }\n            \n        boolean[] flips = new boolean[samples];\n            \n        for(int j:[0..samples))\n            flips[j] = bernoulli(bias[samples - st2[j]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
	}
}