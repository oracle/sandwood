package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart3d$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart3d$CoreInterface {
	private double[] bias;
	private double[][] cv$var30$countGlobal;
	private double[] cv$var55$stateProbabilityGlobal;
	private double[] cv$var80$stateProbabilityGlobal;
	private boolean fixedFlag$sample123 = false;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample48 = false;
	private boolean fixedFlag$sample58 = false;
	private boolean fixedFlag$sample83 = false;
	private boolean fixedProbFlag$sample123 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample48 = false;
	private boolean fixedProbFlag$sample58 = false;
	private boolean fixedProbFlag$sample83 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int[][] indirection;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample123;
	private double[] logProbability$sample83;
	private double logProbability$st;
	private double logProbability$st2;
	private double[] logProbability$var119;
	private double logProbability$var18;
	private double logProbability$var30;
	private double logProbability$var34;
	private double logProbability$var46;
	private double logProbability$var54;
	private double logProbability$var55;
	private double[] logProbability$var79;
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
		fixedProbFlag$sample48 = false;
		fixedProbFlag$sample123 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample123() {
		return fixedFlag$sample123;
	}

	@Override
	public final void set$fixedFlag$sample123(boolean cv$value) {
		fixedFlag$sample123 = cv$value;
		fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
	}

	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	@Override
	public final void set$fixedFlag$sample31(boolean cv$value) {
		fixedFlag$sample31 = cv$value;
		fixedProbFlag$sample31 = (cv$value && fixedProbFlag$sample31);
		fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
		fixedProbFlag$sample83 = (cv$value && fixedProbFlag$sample83);
	}

	@Override
	public final boolean get$fixedFlag$sample48() {
		return fixedFlag$sample48;
	}

	@Override
	public final void set$fixedFlag$sample48(boolean cv$value) {
		fixedFlag$sample48 = cv$value;
		fixedProbFlag$sample48 = (cv$value && fixedProbFlag$sample48);
		fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
	}

	@Override
	public final boolean get$fixedFlag$sample58() {
		return fixedFlag$sample58;
	}

	@Override
	public final void set$fixedFlag$sample58(boolean cv$value) {
		fixedFlag$sample58 = cv$value;
		fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
		fixedProbFlag$sample83 = (cv$value && fixedProbFlag$sample83);
		fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
	}

	@Override
	public final boolean get$fixedFlag$sample83() {
		return fixedFlag$sample83;
	}

	@Override
	public final void set$fixedFlag$sample83(boolean cv$value) {
		fixedFlag$sample83 = cv$value;
		fixedProbFlag$sample83 = (cv$value && fixedProbFlag$sample83);
		fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample123 = false;
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
		fixedProbFlag$sample31 = false;
		fixedProbFlag$sample58 = false;
		fixedProbFlag$sample83 = false;
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
		fixedProbFlag$sample58 = false;
		fixedProbFlag$sample83 = false;
		fixedProbFlag$sample123 = false;
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

	private final void logProbabilityValue$sample123() {
		if(!fixedProbFlag$sample123) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[j], bias[(samples - st2[j])]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var119[j] = cv$distributionAccumulator;
				logProbability$sample123[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample123 = (((fixedFlag$sample123 && fixedFlag$sample48) && fixedFlag$sample58) && fixedFlag$sample83);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = logProbability$sample123[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var119[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!fixedProbFlag$sample31) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityDirichlet(m[0], v) + DistributionSampling.logProbabilityDirichlet(m[1], v));
			logProbability$var18 = cv$sampleAccumulator;
			logProbability$var30 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample31 = fixedFlag$sample31;
		} else {
			logProbability$var18 = logProbability$var30;
			logProbability$m = (logProbability$m + logProbability$var30);
			logProbability$$model = (logProbability$$model + logProbability$var30);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var30);
		}
	}

	private final void logProbabilityValue$sample48() {
		if(!fixedProbFlag$sample48) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityBeta(bias[0], 1.0, 1.0) + DistributionSampling.logProbabilityBeta(bias[1], 1.0, 1.0));
			logProbability$var34 = cv$sampleAccumulator;
			logProbability$var46 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample48)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample48 = fixedFlag$sample48;
		} else {
			logProbability$var34 = logProbability$var46;
			logProbability$bias = (logProbability$bias + logProbability$var46);
			logProbability$$model = (logProbability$$model + logProbability$var46);
			if(fixedFlag$sample48)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var46);
		}
	}

	private final void logProbabilityValue$sample58() {
		if(!fixedProbFlag$sample58) {
			int cv$sampleValue = st[0];
			double[] var53 = m[0];
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var53.length))?Math.log(var53[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var54 = cv$distributionAccumulator;
			logProbability$var55 = cv$distributionAccumulator;
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			boolean cv$guard$st2 = true;
			logProbability$st2 = (logProbability$st2 + cv$distributionAccumulator);
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
				if(((0 == (indirection[(i$var73 - 1)][i$var73] / i$var73)) && !cv$guard$st2)) {
					cv$guard$st2 = true;
					logProbability$st2 = (logProbability$st2 + cv$distributionAccumulator);
				}
			}
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample58 = (fixedFlag$sample58 && fixedFlag$sample31);
		} else {
			logProbability$var54 = logProbability$var55;
			logProbability$st = (logProbability$st + logProbability$var55);
			boolean cv$guard$st2 = true;
			logProbability$st2 = (logProbability$st2 + logProbability$var55);
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
				if(((0 == (indirection[(i$var73 - 1)][i$var73] / i$var73)) && !cv$guard$st2)) {
					cv$guard$st2 = true;
					logProbability$st2 = (logProbability$st2 + logProbability$var55);
				}
			}
			logProbability$$model = (logProbability$$model + logProbability$var55);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var55);
		}
	}

	private final void logProbabilityValue$sample83() {
		if(!fixedProbFlag$sample83) {
			double cv$accumulator = 0.0;
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
				int cv$sampleValue = st[i$var73];
				double[] var78 = m[(samples - st2[(i$var73 - 1)])];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var78.length))?Math.log(var78[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var79[(i$var73 - 1)] = cv$distributionAccumulator;
				logProbability$sample83[(i$var73 - 1)] = cv$distributionAccumulator;
				boolean cv$guard$st2 = false;
				for(int index$i$2_1 = 1; index$i$2_1 < samples; index$i$2_1 += 1) {
					if(((i$var73 == (indirection[(index$i$2_1 - 1)][index$i$2_1] / index$i$2_1)) && !cv$guard$st2)) {
						cv$guard$st2 = true;
						logProbability$st2 = (logProbability$st2 + cv$distributionAccumulator);
					}
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample83)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample83 = ((fixedFlag$sample83 && fixedFlag$sample31) && fixedFlag$sample58);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
				double cv$sampleValue = logProbability$sample83[(i$var73 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var79[(i$var73 - 1)] = cv$sampleValue;
				boolean cv$guard$st2 = false;
				for(int index$i$3_1 = 1; index$i$3_1 < samples; index$i$3_1 += 1) {
					if(((i$var73 == (indirection[(index$i$3_1 - 1)][index$i$3_1] / index$i$3_1)) && !cv$guard$st2)) {
						cv$guard$st2 = true;
						logProbability$st2 = (logProbability$st2 + cv$sampleValue);
					}
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample83)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample31(int var29, int threadID$cv$var29, Rng RNG$) {
		double[] cv$countLocal = cv$var30$countGlobal[threadID$cv$var29];
		cv$countLocal[0] = 0.0;
		cv$countLocal[1] = 0.0;
		if((var29 == 0))
			cv$countLocal[st[0]] = (cv$countLocal[st[0]] + 1.0);
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if((var29 == (samples - st2[(i$var73 - 1)])))
				cv$countLocal[st[i$var73]] = (cv$countLocal[st[i$var73]] + 1.0);
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var29]);
	}

	private final void sample48(int var45, int threadID$cv$var45, Rng RNG$) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int j = 0; j < samples; j += 1) {
			if((var45 == (samples - st2[j]))) {
				cv$count = (cv$count + 1);
				if(flips[j])
					cv$sum = (cv$sum + 1);
			}
		}
		bias[var45] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample58() {
		{
			st[0] = 0;
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
				if((0 == (indirection[(i$var73 - 1)][i$var73] / i$var73)))
					st2[(indirection[(i$var73 - 1)][i$var73] / i$var73)] = (samples - st[(indirection[(i$var73 - 1)][i$var73] / i$var73)]);
			}
			double[] cv$temp$0$var53 = m[0];
			double cv$accumulatedProbabilities = ((0 < cv$temp$0$var53.length)?Math.log(cv$temp$0$var53[0]):Double.NEGATIVE_INFINITY);
			if((1 < samples)) {
				double[] cv$temp$1$var78 = m[0];
				cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < cv$temp$1$var78.length))?Math.log(cv$temp$1$var78[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
				if((0 == (indirection[(i$var73 - 1)][i$var73] / i$var73))) {
					int index$i$4_4 = ((indirection[(i$var73 - 1)][i$var73] / i$var73) + 1);
					if(((1 <= index$i$4_4) && (index$i$4_4 < samples))) {
						double[] cv$temp$2$var78 = m[0];
						cv$accumulatedProbabilities = ((((0.0 <= st[index$i$4_4]) && (st[index$i$4_4] < cv$temp$2$var78.length))?Math.log(cv$temp$2$var78[st[index$i$4_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((0 < samples))
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0], bias[0]) + cv$accumulatedProbabilities);
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
				if((0 == (indirection[(i$var73 - 1)][i$var73] / i$var73))) {
					int j = (indirection[(i$var73 - 1)][i$var73] / i$var73);
					if(((0 <= j) && (j < samples)))
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[j], bias[0]) + cv$accumulatedProbabilities);
				}
			}
			cv$var55$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		st[0] = 1;
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if((0 == (indirection[(i$var73 - 1)][i$var73] / i$var73)))
				st2[(indirection[(i$var73 - 1)][i$var73] / i$var73)] = (samples - st[(indirection[(i$var73 - 1)][i$var73] / i$var73)]);
		}
		double[] cv$temp$0$var53 = m[0];
		double cv$accumulatedProbabilities = ((1 < cv$temp$0$var53.length)?Math.log(cv$temp$0$var53[1]):Double.NEGATIVE_INFINITY);
		if((1 < samples)) {
			double[] cv$temp$1$var78 = m[1];
			cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < cv$temp$1$var78.length))?Math.log(cv$temp$1$var78[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if((0 == (indirection[(i$var73 - 1)][i$var73] / i$var73))) {
				int index$i$4_4 = ((indirection[(i$var73 - 1)][i$var73] / i$var73) + 1);
				if(((1 <= index$i$4_4) && (index$i$4_4 < samples))) {
					double[] cv$temp$2$var78 = m[1];
					cv$accumulatedProbabilities = ((((0.0 <= st[index$i$4_4]) && (st[index$i$4_4] < cv$temp$2$var78.length))?Math.log(cv$temp$2$var78[st[index$i$4_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
		}
		if((0 < samples))
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0], bias[1]) + cv$accumulatedProbabilities);
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if((0 == (indirection[(i$var73 - 1)][i$var73] / i$var73))) {
				int j = (indirection[(i$var73 - 1)][i$var73] / i$var73);
				if(((0 <= j) && (j < samples)))
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[j], bias[1]) + cv$accumulatedProbabilities);
			}
		}
		cv$var55$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = cv$var55$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var55$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var55$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var55$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			cv$var55$stateProbabilityGlobal[0] = 0.5;
			cv$var55$stateProbabilityGlobal[1] = 0.5;
		} else {
			cv$var55$stateProbabilityGlobal[0] = Math.exp((cv$var55$stateProbabilityGlobal[0] - cv$logSum));
			cv$var55$stateProbabilityGlobal[1] = Math.exp((cv$var55$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var55$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var55$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st[0] = DistributionSampling.sampleCategorical(RNG$, cv$var55$stateProbabilityGlobal);
		st2[0] = (samples - st[0]);
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if((0 == (indirection[(i$var73 - 1)][i$var73] / i$var73)))
				st2[(indirection[(i$var73 - 1)][i$var73] / i$var73)] = (samples - st[(indirection[(i$var73 - 1)][i$var73] / i$var73)]);
		}
	}

	private final void sample83(int i$var73) {
		{
			st[i$var73] = 0;
			for(int index$i$1_1 = 1; index$i$1_1 < samples; index$i$1_1 += 1) {
				if((i$var73 == (indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)))
					st2[(indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)] = (samples - st[(indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)]);
			}
			double[] cv$temp$0$var78 = m[(samples - st2[(i$var73 - 1)])];
			double cv$accumulatedProbabilities = ((0 < cv$temp$0$var78.length)?Math.log(cv$temp$0$var78[0]):Double.NEGATIVE_INFINITY);
			for(int index$i$2_2 = 1; index$i$2_2 < samples; index$i$2_2 += 1) {
				if((i$var73 == (indirection[(index$i$2_2 - 1)][index$i$2_2] / index$i$2_2))) {
					int index$i$2_4 = ((indirection[(index$i$2_2 - 1)][index$i$2_2] / index$i$2_2) + 1);
					if(((1 <= index$i$2_4) && (index$i$2_4 < samples))) {
						double[] cv$temp$1$var78 = m[0];
						cv$accumulatedProbabilities = ((((0.0 <= st[index$i$2_4]) && (st[index$i$2_4] < cv$temp$1$var78.length))?Math.log(cv$temp$1$var78[st[index$i$2_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int index$i$5_2 = 1; index$i$5_2 < samples; index$i$5_2 += 1) {
				if((i$var73 == (indirection[(index$i$5_2 - 1)][index$i$5_2] / index$i$5_2))) {
					int j = (indirection[(index$i$5_2 - 1)][index$i$5_2] / index$i$5_2);
					if(((0 <= j) && (j < samples)))
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[j], bias[0]) + cv$accumulatedProbabilities);
				}
			}
			cv$var80$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		st[i$var73] = 1;
		for(int index$i$1_1 = 1; index$i$1_1 < samples; index$i$1_1 += 1) {
			if((i$var73 == (indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)))
				st2[(indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)] = (samples - st[(indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)]);
		}
		double[] cv$temp$0$var78 = m[(samples - st2[(i$var73 - 1)])];
		double cv$accumulatedProbabilities = ((1 < cv$temp$0$var78.length)?Math.log(cv$temp$0$var78[1]):Double.NEGATIVE_INFINITY);
		for(int index$i$2_2 = 1; index$i$2_2 < samples; index$i$2_2 += 1) {
			if((i$var73 == (indirection[(index$i$2_2 - 1)][index$i$2_2] / index$i$2_2))) {
				int index$i$2_4 = ((indirection[(index$i$2_2 - 1)][index$i$2_2] / index$i$2_2) + 1);
				if(((1 <= index$i$2_4) && (index$i$2_4 < samples))) {
					double[] cv$temp$1$var78 = m[1];
					cv$accumulatedProbabilities = ((((0.0 <= st[index$i$2_4]) && (st[index$i$2_4] < cv$temp$1$var78.length))?Math.log(cv$temp$1$var78[st[index$i$2_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int index$i$5_2 = 1; index$i$5_2 < samples; index$i$5_2 += 1) {
			if((i$var73 == (indirection[(index$i$5_2 - 1)][index$i$5_2] / index$i$5_2))) {
				int j = (indirection[(index$i$5_2 - 1)][index$i$5_2] / index$i$5_2);
				if(((0 <= j) && (j < samples)))
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[j], bias[1]) + cv$accumulatedProbabilities);
			}
		}
		cv$var80$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = cv$var80$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var80$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var80$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var80$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			cv$var80$stateProbabilityGlobal[0] = 0.5;
			cv$var80$stateProbabilityGlobal[1] = 0.5;
		} else {
			cv$var80$stateProbabilityGlobal[0] = Math.exp((cv$var80$stateProbabilityGlobal[0] - cv$logSum));
			cv$var80$stateProbabilityGlobal[1] = Math.exp((cv$var80$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var80$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var80$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st[i$var73] = DistributionSampling.sampleCategorical(RNG$, cv$var80$stateProbabilityGlobal);
		for(int index$i$8_1 = 1; index$i$8_1 < samples; index$i$8_1 += 1) {
			if((i$var73 == (indirection[(index$i$8_1 - 1)][index$i$8_1] / index$i$8_1)))
				st2[(indirection[(index$i$8_1 - 1)][index$i$8_1] / index$i$8_1)] = (samples - st[(indirection[(index$i$8_1 - 1)][index$i$8_1] / index$i$8_1)]);
		}
	}

	@Override
	public final void allocateScratch() {
		int cv$threadCount = threadCount();
		cv$var30$countGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var30$countGlobal[cv$index] = new double[2];
		cv$var55$stateProbabilityGlobal = new double[2];
		cv$var80$stateProbabilityGlobal = new double[2];
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
		for(int i$var73 = 1; i$var73 < length$flipsMeasured; i$var73 += 1)
			indirection[(i$var73 - 1)] = new int[(i$var73 + 1)];
		if(!setFlag$flips)
			flips = new boolean[length$flipsMeasured];
		logProbability$var79 = new double[(length$flipsMeasured - 1)];
		logProbability$sample83 = new double[(length$flipsMeasured - 1)];
		logProbability$var119 = new double[length$flipsMeasured];
		logProbability$sample123 = new double[length$flipsMeasured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample31)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var29]);
				}
			);

		if(!fixedFlag$sample48)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample58) {
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
			st2[0] = (samples - st[0]);
		}
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if(!fixedFlag$sample83)
				st[i$var73] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var73 - 1)])]);
			if((!fixedFlag$sample58 || !fixedFlag$sample83))
				st2[(indirection[(i$var73 - 1)][i$var73] / i$var73)] = (samples - st[(indirection[(i$var73 - 1)][i$var73] / i$var73)]);
		}
		if(!fixedFlag$sample123)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j = forStart$j; j < forEnd$j; j += 1)
							flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[(samples - st2[j])]);
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample31)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var29]);
				}
			);

		if(!fixedFlag$sample48)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample58) {
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
			st2[0] = (samples - st[0]);
		}
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if(!fixedFlag$sample83)
				st[i$var73] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var73 - 1)])]);
			if((!fixedFlag$sample58 || !fixedFlag$sample83))
				st2[(indirection[(i$var73 - 1)][i$var73] / i$var73)] = (samples - st[(indirection[(i$var73 - 1)][i$var73] / i$var73)]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample31)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var29]);
				}
			);

		if(!fixedFlag$sample48)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample58) {
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
			st2[0] = (samples - st[0]);
		}
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if(!fixedFlag$sample83)
				st[i$var73] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var73 - 1)])]);
			if((!fixedFlag$sample58 || !fixedFlag$sample83))
				st2[(indirection[(i$var73 - 1)][i$var73] / i$var73)] = (samples - st[(indirection[(i$var73 - 1)][i$var73] / i$var73)]);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample31)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
								sample31(var29, threadID$var29, RNG$1);
					}
				);

			if(!fixedFlag$sample48)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
								sample48(var45, threadID$var45, RNG$1);
					}
				);

			if(!fixedFlag$sample58)
				sample58();
			if(!fixedFlag$sample83) {
				for(int i$var73 = 1; i$var73 < samples; i$var73 += 1)
					sample83(i$var73);
			}
		} else {
			if(!fixedFlag$sample83) {
				for(int i$var73 = (samples - 1); i$var73 >= 1; i$var73 -= 1)
					sample83(i$var73);
			}
			if(!fixedFlag$sample58)
				sample58();
			if(!fixedFlag$sample48)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
								sample48(var45, threadID$var45, RNG$1);
					}
				);

			if(!fixedFlag$sample31)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
								sample31(var29, threadID$var29, RNG$1);
					}
				);

		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$i$var15, int forEnd$i$var15, int threadID$i$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var15 = forStart$i$var15; i$var15 < forEnd$i$var15; i$var15 += 1)
						v[i$var15] = 0.1;
			}
		);
		samples = length$flipsMeasured;
		for(int i$var73 = 1; i$var73 < length$flipsMeasured; i$var73 += 1) {
			int i$var73$1 = i$var73;
			parallelFor(RNG$, 0, (i$var73$1 + 1), 1,
				(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int k = forStart$k; k < forEnd$k; k += 1)
							indirection[(i$var73$1 - 1)][k] = (k * i$var73$1);
				}
			);
		}
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var18 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$var30 = 0.0;
		logProbability$var34 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample48)
			logProbability$var46 = 0.0;
		logProbability$var54 = 0.0;
		logProbability$st2 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample58)
			logProbability$var55 = 0.0;
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1)
			logProbability$var79[(i$var73 - 1)] = 0.0;
		if(!fixedProbFlag$sample83) {
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1)
				logProbability$sample83[(i$var73 - 1)] = 0.0;
		}
		for(int j = 0; j < samples; j += 1)
			logProbability$var119[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample123) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample123[j] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample31)
			logProbabilityValue$sample31();
		if(fixedFlag$sample48)
			logProbabilityValue$sample48();
		if(fixedFlag$sample58)
			logProbabilityValue$sample58();
		if(fixedFlag$sample83)
			logProbabilityValue$sample83();
		logProbabilityValue$sample123();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample31();
		logProbabilityValue$sample48();
		logProbabilityValue$sample58();
		logProbabilityValue$sample83();
		logProbabilityValue$sample123();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample31();
		logProbabilityValue$sample48();
		logProbabilityValue$sample58();
		logProbabilityValue$sample83();
		logProbabilityValue$sample123();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample31)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var29]);
				}
			);

		if(!fixedFlag$sample48)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample58) {
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
			st2[0] = (samples - st[0]);
		}
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if(!fixedFlag$sample83)
				st[i$var73] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var73 - 1)])]);
			if((!fixedFlag$sample58 || !fixedFlag$sample83))
				st2[(indirection[(i$var73 - 1)][i$var73] / i$var73)] = (samples - st[(indirection[(i$var73 - 1)][i$var73] / i$var73)]);
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
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1)
				st2[(indirection[(i$var73 - 1)][i$var73] / i$var73)] = (samples - st[(indirection[(i$var73 - 1)][i$var73] / i$var73)]);
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart3d(boolean[] flipsMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n\n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        int[] st = new int[samples];\n        int[] st2 = new int[samples];\n\n        st[0] = categorical(m[0]).sample();\n        st2[0] = samples - st[0];\n\n        for(int i:[1..samples)) {\n            st[i] = categorical(m[samples - st2[i-1]]).sample();\n            \n            int[] indirection = new int[i+1];\n            for(int k:[0..i])\n                indirection[k] = k*i; \n                \n            st2[indirection[i]/i] = samples - st[indirection[i]/i];\n        }\n            \n        boolean[] flips = new boolean[samples];\n            \n        for(int j:[0..samples))\n            flips[j] = bernoulli(bias[samples - st2[j]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
	}
}