package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart5$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMTestPart5$CoreInterface {
	private double[] bias;
	private double[] cv$var30$countGlobal;
	private double[] cv$var54$stateProbabilityGlobal;
	private double[] cv$var73$stateProbabilityGlobal;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample48 = false;
	private boolean fixedFlag$sample57 = false;
	private boolean fixedFlag$sample76 = false;
	private boolean fixedFlag$sample93 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample48 = false;
	private boolean fixedProbFlag$sample57 = false;
	private boolean fixedProbFlag$sample76 = false;
	private boolean fixedProbFlag$sample93 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample76;
	private double[] logProbability$sample93;
	private double logProbability$st;
	private double logProbability$var18;
	private double logProbability$var30;
	private double logProbability$var34;
	private double logProbability$var46;
	private double logProbability$var53;
	private double logProbability$var54;
	private double[] logProbability$var72;
	private double[] logProbability$var89;
	private double[][] m;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart5$SingleThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample93 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	@Override
	public final void set$fixedFlag$sample31(boolean cv$value) {
		fixedFlag$sample31 = cv$value;
		fixedProbFlag$sample31 = (cv$value && fixedProbFlag$sample31);
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
	}

	@Override
	public final boolean get$fixedFlag$sample48() {
		return fixedFlag$sample48;
	}

	@Override
	public final void set$fixedFlag$sample48(boolean cv$value) {
		fixedFlag$sample48 = cv$value;
		fixedProbFlag$sample48 = (cv$value && fixedProbFlag$sample48);
		fixedProbFlag$sample93 = (cv$value && fixedProbFlag$sample93);
	}

	@Override
	public final boolean get$fixedFlag$sample57() {
		return fixedFlag$sample57;
	}

	@Override
	public final void set$fixedFlag$sample57(boolean cv$value) {
		fixedFlag$sample57 = cv$value;
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
		fixedProbFlag$sample93 = (cv$value && fixedProbFlag$sample93);
	}

	@Override
	public final boolean get$fixedFlag$sample76() {
		return fixedFlag$sample76;
	}

	@Override
	public final void set$fixedFlag$sample76(boolean cv$value) {
		fixedFlag$sample76 = cv$value;
		fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
		fixedProbFlag$sample93 = (cv$value && fixedProbFlag$sample93);
	}

	@Override
	public final boolean get$fixedFlag$sample93() {
		return fixedFlag$sample93;
	}

	@Override
	public final void set$fixedFlag$sample93(boolean cv$value) {
		fixedFlag$sample93 = cv$value;
		fixedProbFlag$sample93 = (cv$value && fixedProbFlag$sample93);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample93 = false;
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
		fixedProbFlag$sample31 = false;
		fixedProbFlag$sample57 = false;
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
		setFlag$st = true;
		fixedProbFlag$sample57 = false;
		fixedProbFlag$sample76 = false;
		fixedProbFlag$sample93 = false;
	}

	@Override
	public final int get$states() {
		return 2;
	}

	@Override
	public final double[] get$v() {
		return v;
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

	private final void logProbabilityValue$sample57() {
		if(!fixedProbFlag$sample57) {
			int cv$sampleValue = (st[0] / 2);
			double[] var52 = m[0];
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var52.length))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var53 = cv$distributionAccumulator;
			logProbability$var54 = cv$distributionAccumulator;
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedFlag$sample31);
		} else {
			logProbability$var53 = logProbability$var54;
			logProbability$st = (logProbability$st + logProbability$var54);
			logProbability$$model = (logProbability$$model + logProbability$var54);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var54);
		}
	}

	private final void logProbabilityValue$sample76() {
		if(!fixedProbFlag$sample76) {
			double cv$accumulator = 0.0;
			for(int i$var67 = 1; i$var67 < samples; i$var67 += 1) {
				int cv$sampleValue = (st[i$var67] / 2);
				double[] var71 = m[st[(i$var67 - 1)]];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var71.length))?Math.log(var71[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var72[(i$var67 - 1)] = cv$distributionAccumulator;
				logProbability$sample76[(i$var67 - 1)] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample76)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample76 = ((fixedFlag$sample76 && fixedFlag$sample31) && fixedFlag$sample57);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var67 = 1; i$var67 < samples; i$var67 += 1) {
				double cv$rvAccumulator = logProbability$sample76[(i$var67 - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var72[(i$var67 - 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample76)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample93() {
		if(!fixedProbFlag$sample93) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[j], bias[st[j]]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var89[j] = cv$distributionAccumulator;
				logProbability$sample93[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample93 = (((fixedFlag$sample93 && fixedFlag$sample48) && fixedFlag$sample57) && fixedFlag$sample76);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = logProbability$sample93[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var89[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample31(int var29) {
		cv$var30$countGlobal[0] = 0.0;
		cv$var30$countGlobal[1] = 0.0;
		if((var29 == 0))
			cv$var30$countGlobal[(st[0] / 2)] = (cv$var30$countGlobal[(st[0] / 2)] + 1.0);
		for(int i$var67 = 1; i$var67 < samples; i$var67 += 1) {
			if((var29 == st[(i$var67 - 1)]))
				cv$var30$countGlobal[(st[i$var67] / 2)] = (cv$var30$countGlobal[(st[i$var67] / 2)] + 1.0);
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var30$countGlobal, m[var29]);
	}

	private final void sample48(int var45) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int j = 0; j < samples; j += 1) {
			if((var45 == st[j])) {
				cv$count = (cv$count + 1);
				if(flips[j])
					cv$sum = (cv$sum + 1);
			}
		}
		bias[var45] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample57() {
		{
			double[] cv$temp$0$var52 = m[0];
			double cv$accumulatedProbabilities = ((0 < cv$temp$0$var52.length)?Math.log(cv$temp$0$var52[0]):Double.NEGATIVE_INFINITY);
			if((1 < samples)) {
				double[] cv$temp$1$var71 = m[0];
				cv$accumulatedProbabilities = ((((0.0 <= (st[1] / 2)) && ((st[1] / 2) < cv$temp$1$var71.length))?Math.log(cv$temp$1$var71[(st[1] / 2)]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if((0 < samples))
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0], bias[0]) + cv$accumulatedProbabilities);
			cv$var54$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		double[] cv$temp$0$var52 = m[0];
		double cv$accumulatedProbabilities = ((1 < cv$temp$0$var52.length)?Math.log(cv$temp$0$var52[1]):Double.NEGATIVE_INFINITY);
		if((1 < samples)) {
			double[] cv$temp$1$var71 = m[2];
			cv$accumulatedProbabilities = ((((0.0 <= (st[1] / 2)) && ((st[1] / 2) < cv$temp$1$var71.length))?Math.log(cv$temp$1$var71[(st[1] / 2)]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		if((0 < samples))
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0], bias[2]) + cv$accumulatedProbabilities);
		cv$var54$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = cv$var54$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var54$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var54$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var54$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			cv$var54$stateProbabilityGlobal[0] = 0.5;
			cv$var54$stateProbabilityGlobal[1] = 0.5;
		} else {
			cv$var54$stateProbabilityGlobal[0] = Math.exp((cv$var54$stateProbabilityGlobal[0] - cv$logSum));
			cv$var54$stateProbabilityGlobal[1] = Math.exp((cv$var54$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var54$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var54$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st[0] = (DistributionSampling.sampleCategorical(RNG$, cv$var54$stateProbabilityGlobal) * 2);
	}

	private final void sample76(int i$var67) {
		{
			st[i$var67] = 0;
			double[] cv$temp$0$var71 = m[st[(i$var67 - 1)]];
			double cv$accumulatedProbabilities = ((0 < cv$temp$0$var71.length)?Math.log(cv$temp$0$var71[0]):Double.NEGATIVE_INFINITY);
			int index$i$1_2 = (i$var67 + 1);
			if((index$i$1_2 < samples)) {
				double[] cv$temp$1$var71 = m[0];
				cv$accumulatedProbabilities = ((((0.0 <= (st[index$i$1_2] / 2)) && ((st[index$i$1_2] / 2) < cv$temp$1$var71.length))?Math.log(cv$temp$1$var71[(st[index$i$1_2] / 2)]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[i$var67], bias[0]) + cv$accumulatedProbabilities);
			cv$var73$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		st[i$var67] = 2;
		double[] cv$temp$0$var71 = m[st[(i$var67 - 1)]];
		double cv$accumulatedProbabilities = ((1 < cv$temp$0$var71.length)?Math.log(cv$temp$0$var71[1]):Double.NEGATIVE_INFINITY);
		int index$i$1_2 = (i$var67 + 1);
		if((index$i$1_2 < samples)) {
			double[] cv$temp$1$var71 = m[2];
			cv$accumulatedProbabilities = ((((0.0 <= (st[index$i$1_2] / 2)) && ((st[index$i$1_2] / 2) < cv$temp$1$var71.length))?Math.log(cv$temp$1$var71[(st[index$i$1_2] / 2)]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[i$var67], bias[2]) + cv$accumulatedProbabilities);
		cv$var73$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
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
		st[i$var67] = (DistributionSampling.sampleCategorical(RNG$, cv$var73$stateProbabilityGlobal) * 2);
	}

	@Override
	public final void allocateScratch() {
		cv$var30$countGlobal = new double[2];
		cv$var54$stateProbabilityGlobal = new double[2];
		cv$var73$stateProbabilityGlobal = new double[2];
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
		logProbability$var72 = new double[(length$flipsMeasured - 1)];
		logProbability$sample76 = new double[(length$flipsMeasured - 1)];
		logProbability$var89 = new double[length$flipsMeasured];
		logProbability$sample93 = new double[length$flipsMeasured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample31) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		if(!fixedFlag$sample48) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample57)
			st[0] = (DistributionSampling.sampleCategorical(RNG$, m[0]) * 2);
		if(!fixedFlag$sample76) {
			for(int i$var67 = 1; i$var67 < samples; i$var67 += 1)
				st[i$var67] = (DistributionSampling.sampleCategorical(RNG$, m[st[(i$var67 - 1)]]) * 2);
		}
		if(!fixedFlag$sample93) {
			for(int j = 0; j < samples; j += 1)
				flips[j] = DistributionSampling.sampleBernoulli(RNG$, bias[st[j]]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample31) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		if(!fixedFlag$sample48) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample57)
			st[0] = (DistributionSampling.sampleCategorical(RNG$, m[0]) * 2);
		if(!fixedFlag$sample76) {
			for(int i$var67 = 1; i$var67 < samples; i$var67 += 1)
				st[i$var67] = (DistributionSampling.sampleCategorical(RNG$, m[st[(i$var67 - 1)]]) * 2);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample31) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		if(!fixedFlag$sample48) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample57)
			st[0] = (DistributionSampling.sampleCategorical(RNG$, m[0]) * 2);
		if(!fixedFlag$sample76) {
			for(int i$var67 = 1; i$var67 < samples; i$var67 += 1)
				st[i$var67] = (DistributionSampling.sampleCategorical(RNG$, m[st[(i$var67 - 1)]]) * 2);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample31) {
				sample31(0);
				sample31(1);
			}
			if(!fixedFlag$sample48) {
				sample48(0);
				sample48(1);
			}
			if(!fixedFlag$sample57)
				sample57();
			if(!fixedFlag$sample76) {
				for(int i$var67 = 1; i$var67 < samples; i$var67 += 1)
					sample76(i$var67);
			}
		} else {
			if(!fixedFlag$sample76) {
				for(int i$var67 = (samples - 1); i$var67 >= 1; i$var67 -= 1)
					sample76(i$var67);
			}
			if(!fixedFlag$sample57)
				sample57();
			if(!fixedFlag$sample48) {
				sample48(1);
				sample48(0);
			}
			if(!fixedFlag$sample31) {
				sample31(1);
				sample31(0);
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
		logProbability$var18 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$var30 = 0.0;
		logProbability$var34 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample48)
			logProbability$var46 = 0.0;
		logProbability$var53 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample57)
			logProbability$var54 = 0.0;
		for(int i$var67 = 1; i$var67 < samples; i$var67 += 1)
			logProbability$var72[(i$var67 - 1)] = 0.0;
		if(!fixedProbFlag$sample76) {
			for(int i$var67 = 1; i$var67 < samples; i$var67 += 1)
				logProbability$sample76[(i$var67 - 1)] = 0.0;
		}
		for(int j = 0; j < samples; j += 1)
			logProbability$var89[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample93) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample93[j] = 0.0;
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
		if(fixedFlag$sample57)
			logProbabilityValue$sample57();
		if(fixedFlag$sample76)
			logProbabilityValue$sample76();
		logProbabilityValue$sample93();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample31();
		logProbabilityValue$sample48();
		logProbabilityValue$sample57();
		logProbabilityValue$sample76();
		logProbabilityValue$sample93();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample31();
		logProbabilityValue$sample48();
		logProbabilityValue$sample57();
		logProbabilityValue$sample76();
		logProbabilityValue$sample93();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample31) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		if(!fixedFlag$sample48) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample57)
			st[0] = (DistributionSampling.sampleCategorical(RNG$, m[0]) * 2);
		if(!fixedFlag$sample76) {
			for(int i$var67 = 1; i$var67 < samples; i$var67 += 1)
				st[i$var67] = (DistributionSampling.sampleCategorical(RNG$, m[st[(i$var67 - 1)]]) * 2);
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
		     + "model HMMTestPart5(boolean[] flipsMeasured) {\n"
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
		     + "        for(int i:[1..samples))\n"
		     + "            st[i] = states * categorical(m[st[i-1]]).sample();\n"
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