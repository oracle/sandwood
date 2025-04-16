package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart3d$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart3d$CoreInterface {
	private double[] bias;
	private double[][] cv$var28$countGlobal;
	private double[] cv$var53$stateProbabilityGlobal;
	private double[] cv$var78$stateProbabilityGlobal;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample54 = false;
	private boolean fixedFlag$sample79 = false;
	private boolean fixedProbFlag$sample119 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample54 = false;
	private boolean fixedProbFlag$sample79 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int[][] indirection;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample119;
	private double[] logProbability$sample79;
	private double logProbability$st;
	private double logProbability$st2;
	private double[] logProbability$var117;
	private double logProbability$var16;
	private double logProbability$var28;
	private double logProbability$var32;
	private double logProbability$var44;
	private double logProbability$var52;
	private double logProbability$var53;
	private double[] logProbability$var77;
	private double[][] m;
	private int samples;
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
		fixedProbFlag$sample45 = false;
		fixedProbFlag$sample119 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		fixedFlag$sample28 = cv$value;
		fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
		fixedProbFlag$sample54 = (cv$value && fixedProbFlag$sample54);
		fixedProbFlag$sample79 = (cv$value && fixedProbFlag$sample79);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		fixedFlag$sample45 = cv$value;
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
		fixedProbFlag$sample119 = (cv$value && fixedProbFlag$sample119);
	}

	@Override
	public final boolean get$fixedFlag$sample54() {
		return fixedFlag$sample54;
	}

	@Override
	public final void set$fixedFlag$sample54(boolean cv$value) {
		fixedFlag$sample54 = cv$value;
		fixedProbFlag$sample54 = (cv$value && fixedProbFlag$sample54);
		fixedProbFlag$sample79 = (cv$value && fixedProbFlag$sample79);
		fixedProbFlag$sample119 = (cv$value && fixedProbFlag$sample119);
	}

	@Override
	public final boolean get$fixedFlag$sample79() {
		return fixedFlag$sample79;
	}

	@Override
	public final void set$fixedFlag$sample79(boolean cv$value) {
		fixedFlag$sample79 = cv$value;
		fixedProbFlag$sample79 = (cv$value && fixedProbFlag$sample79);
		fixedProbFlag$sample119 = (cv$value && fixedProbFlag$sample119);
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
		fixedProbFlag$sample28 = false;
		fixedProbFlag$sample54 = false;
		fixedProbFlag$sample79 = false;
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
		fixedProbFlag$sample54 = false;
		fixedProbFlag$sample79 = false;
		fixedProbFlag$sample119 = false;
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

	private final void logProbabilityValue$sample119() {
		if(!fixedProbFlag$sample119) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double var116 = bias[(samples - st2[j])];
				double cv$distributionAccumulator = Math.log((flips[j]?var116:(1.0 - var116)));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var117[j] = cv$distributionAccumulator;
				logProbability$sample119[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample119 = ((fixedFlag$sample45 && fixedFlag$sample54) && fixedFlag$sample79);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = logProbability$sample119[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var117[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
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

	private final void logProbabilityValue$sample54() {
		if(!fixedProbFlag$sample54) {
			int cv$sampleValue = st[0];
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 2))?Math.log(m[0][cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var52 = cv$distributionAccumulator;
			logProbability$var53 = cv$distributionAccumulator;
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			boolean cv$guard$st2 = true;
			logProbability$st2 = (logProbability$st2 + cv$distributionAccumulator);
			for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
				if(((0 == (indirection[(i$var71 - 1)][i$var71] / i$var71)) && !cv$guard$st2)) {
					cv$guard$st2 = true;
					logProbability$st2 = (logProbability$st2 + cv$distributionAccumulator);
				}
			}
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample54)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample54 = (fixedFlag$sample54 && fixedFlag$sample28);
		} else {
			logProbability$var52 = logProbability$var53;
			logProbability$st = (logProbability$st + logProbability$var53);
			boolean cv$guard$st2 = true;
			logProbability$st2 = (logProbability$st2 + logProbability$var53);
			for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
				if(((0 == (indirection[(i$var71 - 1)][i$var71] / i$var71)) && !cv$guard$st2)) {
					cv$guard$st2 = true;
					logProbability$st2 = (logProbability$st2 + logProbability$var53);
				}
			}
			logProbability$$model = (logProbability$$model + logProbability$var53);
			if(fixedFlag$sample54)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var53);
		}
	}

	private final void logProbabilityValue$sample79() {
		if(!fixedProbFlag$sample79) {
			double cv$accumulator = 0.0;
			for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
				int cv$sampleValue = st[i$var71];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 2))?Math.log(m[(samples - st2[(i$var71 - 1)])][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var77[(i$var71 - 1)] = cv$distributionAccumulator;
				logProbability$sample79[(i$var71 - 1)] = cv$distributionAccumulator;
				boolean cv$guard$st2 = false;
				for(int index$i$2_1 = 1; index$i$2_1 < samples; index$i$2_1 += 1) {
					if(((i$var71 == (indirection[(index$i$2_1 - 1)][index$i$2_1] / index$i$2_1)) && !cv$guard$st2)) {
						cv$guard$st2 = true;
						logProbability$st2 = (logProbability$st2 + cv$distributionAccumulator);
					}
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample79)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample79 = ((fixedFlag$sample79 && fixedFlag$sample28) && fixedFlag$sample54);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
				double cv$sampleValue = logProbability$sample79[(i$var71 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var77[(i$var71 - 1)] = cv$sampleValue;
				boolean cv$guard$st2 = false;
				for(int index$i$3_1 = 1; index$i$3_1 < samples; index$i$3_1 += 1) {
					if(((i$var71 == (indirection[(index$i$3_1 - 1)][index$i$3_1] / index$i$3_1)) && !cv$guard$st2)) {
						cv$guard$st2 = true;
						logProbability$st2 = (logProbability$st2 + cv$sampleValue);
					}
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample79)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample28(int var27, int threadID$cv$var27, Rng RNG$) {
		double[] cv$countLocal = cv$var28$countGlobal[threadID$cv$var27];
		cv$countLocal[0] = 0.0;
		cv$countLocal[1] = 0.0;
		if((var27 == 0))
			cv$countLocal[st[0]] = (cv$countLocal[st[0]] + 1.0);
		for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
			if((var27 == (samples - st2[(i$var71 - 1)])))
				cv$countLocal[st[i$var71]] = (cv$countLocal[st[i$var71]] + 1.0);
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var27], 2);
	}

	private final void sample45(int var43, int threadID$cv$var43, Rng RNG$) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int j = 0; j < samples; j += 1) {
			if((var43 == (samples - st2[j]))) {
				cv$count = (cv$count + 1);
				if(flips[j])
					cv$sum = (cv$sum + 1);
			}
		}
		bias[var43] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample54() {
		{
			st[0] = 0;
			for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
				if((0 == (indirection[(i$var71 - 1)][i$var71] / i$var71)))
					st2[(indirection[(i$var71 - 1)][i$var71] / i$var71)] = (samples - st[(indirection[(i$var71 - 1)][i$var71] / i$var71)]);
			}
			double cv$accumulatedProbabilities = Math.log(m[0][0]);
			if((1 < samples))
				cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < 2))?Math.log(m[0][st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
				if((0 == (indirection[(i$var71 - 1)][i$var71] / i$var71))) {
					int index$i$5_4 = ((indirection[(i$var71 - 1)][i$var71] / i$var71) + 1);
					if(((1 <= index$i$5_4) && (index$i$5_4 < samples)))
						cv$accumulatedProbabilities = ((((0.0 <= st[index$i$5_4]) && (st[index$i$5_4] < 2))?Math.log(m[0][st[index$i$5_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			if((0 < samples)) {
				double cv$temp$6$var116 = bias[0];
				cv$accumulatedProbabilities = (Math.log((flips[0]?cv$temp$6$var116:(1.0 - cv$temp$6$var116))) + cv$accumulatedProbabilities);
			}
			for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
				if((0 == (indirection[(i$var71 - 1)][i$var71] / i$var71))) {
					int j = (indirection[(i$var71 - 1)][i$var71] / i$var71);
					if(((0 <= j) && (j < samples))) {
						double cv$temp$7$var116 = bias[0];
						cv$accumulatedProbabilities = (Math.log((flips[j]?cv$temp$7$var116:(1.0 - cv$temp$7$var116))) + cv$accumulatedProbabilities);
					}
				}
			}
			cv$var53$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		st[0] = 1;
		for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
			if((0 == (indirection[(i$var71 - 1)][i$var71] / i$var71)))
				st2[(indirection[(i$var71 - 1)][i$var71] / i$var71)] = (samples - st[(indirection[(i$var71 - 1)][i$var71] / i$var71)]);
		}
		double cv$accumulatedProbabilities = Math.log(m[0][1]);
		if((1 < samples))
			cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < 2))?Math.log(m[1][st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
			if((0 == (indirection[(i$var71 - 1)][i$var71] / i$var71))) {
				int index$i$5_4 = ((indirection[(i$var71 - 1)][i$var71] / i$var71) + 1);
				if(((1 <= index$i$5_4) && (index$i$5_4 < samples)))
					cv$accumulatedProbabilities = ((((0.0 <= st[index$i$5_4]) && (st[index$i$5_4] < 2))?Math.log(m[1][st[index$i$5_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		if((0 < samples)) {
			double cv$temp$6$var116 = bias[1];
			cv$accumulatedProbabilities = (Math.log((flips[0]?cv$temp$6$var116:(1.0 - cv$temp$6$var116))) + cv$accumulatedProbabilities);
		}
		for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
			if((0 == (indirection[(i$var71 - 1)][i$var71] / i$var71))) {
				int j = (indirection[(i$var71 - 1)][i$var71] / i$var71);
				if(((0 <= j) && (j < samples))) {
					double cv$temp$7$var116 = bias[1];
					cv$accumulatedProbabilities = (Math.log((flips[j]?cv$temp$7$var116:(1.0 - cv$temp$7$var116))) + cv$accumulatedProbabilities);
				}
			}
		}
		cv$var53$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = cv$var53$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var53$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var53$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var53$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			cv$var53$stateProbabilityGlobal[0] = 0.5;
			cv$var53$stateProbabilityGlobal[1] = 0.5;
		} else {
			cv$var53$stateProbabilityGlobal[0] = Math.exp((cv$var53$stateProbabilityGlobal[0] - cv$logSum));
			cv$var53$stateProbabilityGlobal[1] = Math.exp((cv$var53$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var53$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var53$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st[0] = DistributionSampling.sampleCategorical(RNG$, cv$var53$stateProbabilityGlobal, 2);
		st2[0] = (samples - st[0]);
		for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
			if((0 == (indirection[(i$var71 - 1)][i$var71] / i$var71)))
				st2[(indirection[(i$var71 - 1)][i$var71] / i$var71)] = (samples - st[(indirection[(i$var71 - 1)][i$var71] / i$var71)]);
		}
	}

	private final void sample79(int i$var71) {
		{
			st[i$var71] = 0;
			for(int index$i$2_1 = 1; index$i$2_1 < samples; index$i$2_1 += 1) {
				if((i$var71 == (indirection[(index$i$2_1 - 1)][index$i$2_1] / index$i$2_1)))
					st2[(indirection[(index$i$2_1 - 1)][index$i$2_1] / index$i$2_1)] = (samples - st[(indirection[(index$i$2_1 - 1)][index$i$2_1] / index$i$2_1)]);
			}
			double cv$accumulatedProbabilities = Math.log(m[(samples - st2[(i$var71 - 1)])][0]);
			for(int index$i$3_2 = 1; index$i$3_2 < samples; index$i$3_2 += 1) {
				if((i$var71 == (indirection[(index$i$3_2 - 1)][index$i$3_2] / index$i$3_2))) {
					int index$i$3_4 = ((indirection[(index$i$3_2 - 1)][index$i$3_2] / index$i$3_2) + 1);
					if(((1 <= index$i$3_4) && (index$i$3_4 < samples)))
						cv$accumulatedProbabilities = ((((0.0 <= st[index$i$3_4]) && (st[index$i$3_4] < 2))?Math.log(m[0][st[index$i$3_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			for(int index$i$6_2 = 1; index$i$6_2 < samples; index$i$6_2 += 1) {
				if((i$var71 == (indirection[(index$i$6_2 - 1)][index$i$6_2] / index$i$6_2))) {
					int j = (indirection[(index$i$6_2 - 1)][index$i$6_2] / index$i$6_2);
					if(((0 <= j) && (j < samples))) {
						double cv$temp$4$var116 = bias[0];
						cv$accumulatedProbabilities = (Math.log((flips[j]?cv$temp$4$var116:(1.0 - cv$temp$4$var116))) + cv$accumulatedProbabilities);
					}
				}
			}
			cv$var78$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		st[i$var71] = 1;
		for(int index$i$2_1 = 1; index$i$2_1 < samples; index$i$2_1 += 1) {
			if((i$var71 == (indirection[(index$i$2_1 - 1)][index$i$2_1] / index$i$2_1)))
				st2[(indirection[(index$i$2_1 - 1)][index$i$2_1] / index$i$2_1)] = (samples - st[(indirection[(index$i$2_1 - 1)][index$i$2_1] / index$i$2_1)]);
		}
		double cv$accumulatedProbabilities = Math.log(m[(samples - st2[(i$var71 - 1)])][1]);
		for(int index$i$3_2 = 1; index$i$3_2 < samples; index$i$3_2 += 1) {
			if((i$var71 == (indirection[(index$i$3_2 - 1)][index$i$3_2] / index$i$3_2))) {
				int index$i$3_4 = ((indirection[(index$i$3_2 - 1)][index$i$3_2] / index$i$3_2) + 1);
				if(((1 <= index$i$3_4) && (index$i$3_4 < samples)))
					cv$accumulatedProbabilities = ((((0.0 <= st[index$i$3_4]) && (st[index$i$3_4] < 2))?Math.log(m[1][st[index$i$3_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		for(int index$i$6_2 = 1; index$i$6_2 < samples; index$i$6_2 += 1) {
			if((i$var71 == (indirection[(index$i$6_2 - 1)][index$i$6_2] / index$i$6_2))) {
				int j = (indirection[(index$i$6_2 - 1)][index$i$6_2] / index$i$6_2);
				if(((0 <= j) && (j < samples))) {
					double cv$temp$4$var116 = bias[1];
					cv$accumulatedProbabilities = (Math.log((flips[j]?cv$temp$4$var116:(1.0 - cv$temp$4$var116))) + cv$accumulatedProbabilities);
				}
			}
		}
		cv$var78$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = cv$var78$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var78$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var78$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var78$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			cv$var78$stateProbabilityGlobal[0] = 0.5;
			cv$var78$stateProbabilityGlobal[1] = 0.5;
		} else {
			cv$var78$stateProbabilityGlobal[0] = Math.exp((cv$var78$stateProbabilityGlobal[0] - cv$logSum));
			cv$var78$stateProbabilityGlobal[1] = Math.exp((cv$var78$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var78$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var78$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st[i$var71] = DistributionSampling.sampleCategorical(RNG$, cv$var78$stateProbabilityGlobal, 2);
		for(int index$i$10_1 = 1; index$i$10_1 < samples; index$i$10_1 += 1) {
			if((i$var71 == (indirection[(index$i$10_1 - 1)][index$i$10_1] / index$i$10_1)))
				st2[(indirection[(index$i$10_1 - 1)][index$i$10_1] / index$i$10_1)] = (samples - st[(indirection[(index$i$10_1 - 1)][index$i$10_1] / index$i$10_1)]);
		}
	}

	@Override
	public final void allocateScratch() {
		int cv$threadCount = threadCount();
		cv$var28$countGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var28$countGlobal[cv$index] = new double[2];
		cv$var53$stateProbabilityGlobal = new double[2];
		cv$var78$stateProbabilityGlobal = new double[2];
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
		if((!fixedFlag$sample54 || !fixedFlag$sample79))
			st = new int[length$flipsMeasured];
		st2 = new int[length$flipsMeasured];
		indirection = new int[(length$flipsMeasured - 1)][];
		for(int i$var71 = 1; i$var71 < length$flipsMeasured; i$var71 += 1)
			indirection[(i$var71 - 1)] = new int[(i$var71 + 1)];
		flips = new boolean[length$flipsMeasured];
		logProbability$var77 = new double[(length$flipsMeasured - 1)];
		logProbability$sample79 = new double[(length$flipsMeasured - 1)];
		logProbability$var117 = new double[length$flipsMeasured];
		logProbability$sample119 = new double[length$flipsMeasured];
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

		if(!fixedFlag$sample54) {
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], 2);
			st2[0] = (samples - st[0]);
		}
		for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
			if(!fixedFlag$sample79)
				st[i$var71] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var71 - 1)])], 2);
			if((!fixedFlag$sample54 || !fixedFlag$sample79))
				st2[(indirection[(i$var71 - 1)][i$var71] / i$var71)] = (samples - st[(indirection[(i$var71 - 1)][i$var71] / i$var71)]);
		}
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[(samples - st2[j])]);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
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

		if(!fixedFlag$sample54) {
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], 2);
			st2[0] = (samples - st[0]);
		}
		for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
			if(!fixedFlag$sample79)
				st[i$var71] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var71 - 1)])], 2);
			if((!fixedFlag$sample54 || !fixedFlag$sample79))
				st2[(indirection[(i$var71 - 1)][i$var71] / i$var71)] = (samples - st[(indirection[(i$var71 - 1)][i$var71] / i$var71)]);
		}
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

		if(!fixedFlag$sample54) {
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], 2);
			st2[0] = (samples - st[0]);
		}
		for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
			if(!fixedFlag$sample79)
				st[i$var71] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var71 - 1)])], 2);
			if((!fixedFlag$sample54 || !fixedFlag$sample79))
				st2[(indirection[(i$var71 - 1)][i$var71] / i$var71)] = (samples - st[(indirection[(i$var71 - 1)][i$var71] / i$var71)]);
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

			if(!fixedFlag$sample54)
				sample54();
			if(!fixedFlag$sample79) {
				for(int i$var71 = 1; i$var71 < samples; i$var71 += 1)
					sample79(i$var71);
			}
		} else {
			if(!fixedFlag$sample79) {
				for(int i$var71 = (samples - 1); i$var71 >= 1; i$var71 -= 1)
					sample79(i$var71);
			}
			if(!fixedFlag$sample54)
				sample54();
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
		for(int i$var71 = 1; i$var71 < length$flipsMeasured; i$var71 += 1) {
			int i$var71$1 = i$var71;
			parallelFor(RNG$, 0, (i$var71$1 + 1), 1,
				(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int k = forStart$k; k < forEnd$k; k += 1)
							indirection[(i$var71$1 - 1)][k] = (k * i$var71$1);
				}
			);
		}
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var16 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var28 = 0.0;
		logProbability$var32 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$var44 = 0.0;
		logProbability$var52 = 0.0;
		logProbability$st = 0.0;
		logProbability$st2 = 0.0;
		if(!fixedProbFlag$sample54)
			logProbability$var53 = 0.0;
		for(int i$var71 = 1; i$var71 < samples; i$var71 += 1)
			logProbability$var77[(i$var71 - 1)] = 0.0;
		if(!fixedProbFlag$sample79) {
			for(int i$var71 = 1; i$var71 < samples; i$var71 += 1)
				logProbability$sample79[(i$var71 - 1)] = 0.0;
		}
		for(int j = 0; j < samples; j += 1)
			logProbability$var117[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample119) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample119[j] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		if(fixedFlag$sample54)
			logProbabilityValue$sample54();
		if(fixedFlag$sample79)
			logProbabilityValue$sample79();
		logProbabilityValue$sample119();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample54();
		logProbabilityValue$sample79();
		logProbabilityValue$sample119();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample54();
		logProbabilityValue$sample79();
		logProbabilityValue$sample119();
	}

	@Override
	public final void logProbabilityGeneration() {
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

		if(!fixedFlag$sample54) {
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], 2);
			st2[0] = (samples - st[0]);
		}
		for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
			if(!fixedFlag$sample79)
				st[i$var71] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var71 - 1)])], 2);
			if((!fixedFlag$sample54 || !fixedFlag$sample79))
				st2[(indirection[(i$var71 - 1)][i$var71] / i$var71)] = (samples - st[(indirection[(i$var71 - 1)][i$var71] / i$var71)]);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = flipsMeasured[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		if(fixedFlag$sample54) {
			st2[0] = (samples - st[0]);
			if(fixedFlag$sample79) {
				for(int i$var71 = 1; i$var71 < samples; i$var71 += 1)
					st2[(indirection[(i$var71 - 1)][i$var71] / i$var71)] = (samples - st[(indirection[(i$var71 - 1)][i$var71] / i$var71)]);
			}
		}
	}

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
		     + "model HMMTestPart3d(boolean[] flipsMeasured) {\n"
		     + "        int states = 2;\n"
		     + "\n"
		     + "        double[] v = new double[states];\n"
		     + "        for(int i:[0..states))\n"
		     + "            v[i] = 0.1;\n"
		     + "\n"
		     + "        double[][] m = dirichlet(v).sample(states);\n"
		     + "        double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "        int samples = flipsMeasured.length;\n"
		     + "        int[] st = new int[samples];\n"
		     + "        int[] st2 = new int[samples];\n"
		     + "\n"
		     + "        st[0] = categorical(m[0]).sample();\n"
		     + "        st2[0] = samples - st[0];\n"
		     + "\n"
		     + "        for(int i:[1..samples)) {\n"
		     + "            st[i] = categorical(m[samples - st2[i-1]]).sample();\n"
		     + "            \n"
		     + "            int[] indirection = new int[i+1];\n"
		     + "            for(int k:[0..i])\n"
		     + "                indirection[k] = k*i; \n"
		     + "                \n"
		     + "            st2[indirection[i]/i] = samples - st[indirection[i]/i];\n"
		     + "        }\n"
		     + "            \n"
		     + "        boolean[] flips = new boolean[samples];\n"
		     + "            \n"
		     + "        for(int j:[0..samples))\n"
		     + "            flips[j] = bernoulli(bias[samples - st2[j]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}