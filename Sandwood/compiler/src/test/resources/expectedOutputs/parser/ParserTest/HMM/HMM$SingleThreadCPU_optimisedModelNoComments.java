package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMM$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMM$CoreInterface {
	private double[] bias;
	private double[] cv$var16$countGlobal;
	private double[] cv$var33$stateProbabilityGlobal;
	private double[] cv$var43$stateProbabilityGlobal;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample27 = false;
	private boolean fixedFlag$sample36 = false;
	private boolean fixedFlag$sample46 = false;
	private boolean fixedFlag$sample55 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample27 = false;
	private boolean fixedProbFlag$sample36 = false;
	private boolean fixedProbFlag$sample46 = false;
	private boolean fixedProbFlag$sample55 = false;
	private boolean[] flips;
	private int length$measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample46;
	private double[] logProbability$sample55;
	private double logProbability$st;
	private double logProbability$var11;
	private double logProbability$var16;
	private double logProbability$var20;
	private double logProbability$var25;
	private double logProbability$var32;
	private double logProbability$var33;
	private double[] logProbability$var42;
	private double[] logProbability$var51;
	private double[][] m;
	private boolean[] measured;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[] st;
	private int states;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMM$SingleThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample55 = false;
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
		fixedProbFlag$sample46 = (cv$value && fixedProbFlag$sample46);
	}

	@Override
	public final boolean get$fixedFlag$sample27() {
		return fixedFlag$sample27;
	}

	@Override
	public final void set$fixedFlag$sample27(boolean cv$value) {
		fixedFlag$sample27 = cv$value;
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
	}

	@Override
	public final boolean get$fixedFlag$sample36() {
		return fixedFlag$sample36;
	}

	@Override
	public final void set$fixedFlag$sample36(boolean cv$value) {
		fixedFlag$sample36 = cv$value;
		fixedProbFlag$sample36 = (cv$value && fixedProbFlag$sample36);
		fixedProbFlag$sample46 = (cv$value && fixedProbFlag$sample46);
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
	}

	@Override
	public final boolean get$fixedFlag$sample46() {
		return fixedFlag$sample46;
	}

	@Override
	public final void set$fixedFlag$sample46(boolean cv$value) {
		fixedFlag$sample46 = cv$value;
		fixedProbFlag$sample46 = (cv$value && fixedProbFlag$sample46);
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
	}

	@Override
	public final boolean get$fixedFlag$sample55() {
		return fixedFlag$sample55;
	}

	@Override
	public final void set$fixedFlag$sample55(boolean cv$value) {
		fixedFlag$sample55 = cv$value;
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample55 = false;
	}

	@Override
	public final int get$length$measured() {
		return length$measured;
	}

	@Override
	public final void set$length$measured(int cv$value) {
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
		fixedProbFlag$sample46 = false;
	}

	@Override
	public final boolean[] get$measured() {
		return measured;
	}

	@Override
	public final void set$measured(boolean[] cv$value) {
		measured = cv$value;
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
		fixedProbFlag$sample46 = false;
		fixedProbFlag$sample55 = false;
	}

	@Override
	public final int get$states() {
		return states;
	}

	@Override
	public final void set$states(int cv$value) {
		states = cv$value;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample17() {
		if(!fixedProbFlag$sample17) {
			double cv$sampleAccumulator = 0.0;
			for(int var15 = 0; var15 < states; var15 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var15], v));
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
			double cv$sampleAccumulator = 0.0;
			for(int var24 = 0; var24 < states; var24 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(bias[var24], 1.0, 1.0));
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

	private final void logProbabilityValue$sample46() {
		if(!fixedProbFlag$sample46) {
			double cv$accumulator = 0.0;
			for(int i = 1; i < samples; i += 1) {
				int cv$sampleValue = st[i];
				double[] var41 = m[st[(i - 1)]];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var41.length))?Math.log(var41[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var42[(i - 1)] = cv$distributionAccumulator;
				logProbability$sample46[(i - 1)] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample46 = ((fixedFlag$sample46 && fixedFlag$sample17) && fixedFlag$sample36);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 1; i < samples; i += 1) {
				double cv$rvAccumulator = logProbability$sample46[(i - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var42[(i - 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample55() {
		if(!fixedProbFlag$sample55) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[j], bias[st[j]]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var51[j] = cv$distributionAccumulator;
				logProbability$sample55[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample55 = (((fixedFlag$sample55 && fixedFlag$sample27) && fixedFlag$sample36) && fixedFlag$sample46);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = logProbability$sample55[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var51[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample17(int var15) {
		for(int cv$loopIndex = 0; cv$loopIndex < states; cv$loopIndex += 1)
			cv$var16$countGlobal[cv$loopIndex] = 0.0;
		if((var15 == 0))
			cv$var16$countGlobal[st[0]] = (cv$var16$countGlobal[st[0]] + 1.0);
		for(int i = 1; i < samples; i += 1) {
			if((var15 == st[(i - 1)]))
				cv$var16$countGlobal[st[i]] = (cv$var16$countGlobal[st[i]] + 1.0);
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var16$countGlobal, m[var15]);
	}

	private final void sample27(int var24) {
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
		for(int cv$valuePos = 0; cv$valuePos < states; cv$valuePos += 1) {
			st[0] = cv$valuePos;
			double[] cv$temp$0$var31 = m[0];
			double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var31.length)?Math.log(cv$temp$0$var31[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((1 < samples)) {
				double[] cv$temp$1$var41 = m[cv$valuePos];
				cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < cv$temp$1$var41.length))?Math.log(cv$temp$1$var41[st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if((0 < samples))
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0], bias[cv$valuePos]) + cv$accumulatedProbabilities);
			cv$var33$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
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
		st[0] = DistributionSampling.sampleCategorical(RNG$, cv$var33$stateProbabilityGlobal);
	}

	private final void sample46(int i) {
		for(int cv$valuePos = 0; cv$valuePos < states; cv$valuePos += 1) {
			st[i] = cv$valuePos;
			double[] cv$temp$0$var41 = m[st[(i - 1)]];
			double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var41.length)?Math.log(cv$temp$0$var41[cv$valuePos]):Double.NEGATIVE_INFINITY);
			int index$i$1_2 = (i + 1);
			if((index$i$1_2 < samples)) {
				double[] cv$temp$1$var41 = m[cv$valuePos];
				cv$accumulatedProbabilities = ((((0.0 <= st[index$i$1_2]) && (st[index$i$1_2] < cv$temp$1$var41.length))?Math.log(cv$temp$1$var41[st[index$i$1_2]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[i], bias[cv$valuePos]) + cv$accumulatedProbabilities);
			cv$var43$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double cv$logSum;
		double cv$lseMax = cv$var43$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var43$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var43$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var43$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var43$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var43$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var43$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$var43$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var43$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var43$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var43$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		st[i] = DistributionSampling.sampleCategorical(RNG$, cv$var43$stateProbabilityGlobal);
	}

	@Override
	public final void allocateScratch() {
		int cv$max = 0;
		if((0 < states))
			cv$max = states;
		cv$var16$countGlobal = new double[cv$max];
		cv$var33$stateProbabilityGlobal = new double[states];
		cv$var43$stateProbabilityGlobal = new double[states];
	}

	@Override
	public final void allocator() {
		v = new double[states];
		if(!setFlag$m) {
			m = new double[states][];
			for(int var15 = 0; var15 < states; var15 += 1)
				m[var15] = new double[states];
		}
		if(!setFlag$bias)
			bias = new double[states];
		if(!setFlag$st)
			st = new int[length$measured];
		if(!setFlag$flips)
			flips = new boolean[length$measured];
		logProbability$var42 = new double[(length$measured - 1)];
		logProbability$sample46 = new double[(length$measured - 1)];
		logProbability$var51 = new double[length$measured];
		logProbability$sample55 = new double[length$measured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample17) {
			for(int var15 = 0; var15 < states; var15 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var15]);
		}
		if(!fixedFlag$sample27) {
			for(int var24 = 0; var24 < states; var24 += 1)
				bias[var24] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample36)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample46) {
			for(int i = 1; i < samples; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]]);
		}
		if(!fixedFlag$sample55) {
			for(int j = 0; j < samples; j += 1)
				flips[j] = DistributionSampling.sampleBernoulli(RNG$, bias[st[j]]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample17) {
			for(int var15 = 0; var15 < states; var15 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var15]);
		}
		if(!fixedFlag$sample27) {
			for(int var24 = 0; var24 < states; var24 += 1)
				bias[var24] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample36)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample46) {
			for(int i = 1; i < samples; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample17) {
			for(int var15 = 0; var15 < states; var15 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var15]);
		}
		if(!fixedFlag$sample27) {
			for(int var24 = 0; var24 < states; var24 += 1)
				bias[var24] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample36)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample46) {
			for(int i = 1; i < samples; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]]);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample17) {
				for(int var15 = 0; var15 < states; var15 += 1)
					sample17(var15);
			}
			if(!fixedFlag$sample27) {
				for(int var24 = 0; var24 < states; var24 += 1)
					sample27(var24);
			}
			if(!fixedFlag$sample36)
				sample36();
			if(!fixedFlag$sample46) {
				for(int i = 1; i < samples; i += 1)
					sample46(i);
			}
		} else {
			if(!fixedFlag$sample46) {
				for(int i = (samples - 1); i >= 1; i -= 1)
					sample46(i);
			}
			if(!fixedFlag$sample36)
				sample36();
			if(!fixedFlag$sample27) {
				for(int var24 = (states - 1); var24 >= 0; var24 -= 1)
					sample27(var24);
			}
			if(!fixedFlag$sample17) {
				for(int var15 = (states - 1); var15 >= 0; var15 -= 1)
					sample17(var15);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		for(int var8 = 0; var8 < states; var8 += 1)
			v[var8] = 0.1;
		samples = length$measured;
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
		for(int i = 1; i < samples; i += 1)
			logProbability$var42[(i - 1)] = 0.0;
		if(!fixedProbFlag$sample46) {
			for(int i = 1; i < samples; i += 1)
				logProbability$sample46[(i - 1)] = 0.0;
		}
		for(int j = 0; j < samples; j += 1)
			logProbability$var51[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample55) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample55[j] = 0.0;
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
		if(fixedFlag$sample46)
			logProbabilityValue$sample46();
		logProbabilityValue$sample55();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample27();
		logProbabilityValue$sample36();
		logProbabilityValue$sample46();
		logProbabilityValue$sample55();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample27();
		logProbabilityValue$sample36();
		logProbabilityValue$sample46();
		logProbabilityValue$sample55();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample17) {
			for(int var15 = 0; var15 < states; var15 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var15]);
		}
		if(!fixedFlag$sample27) {
			for(int var24 = 0; var24 < states; var24 += 1)
				bias[var24] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample36)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample46) {
			for(int i = 1; i < samples; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]]);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = measured[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMM(boolean[] measured, int states) {\n\n  double[] v = new double[states] <~ 0.1;\n  double[][] m = dirichlet(v).sample(states);\n    \n  double[] bias = beta(1.0, 1.0).sample(states);\n\n  int samples = measured.length;\n  int[] st = new int[samples];\n        \n  st[0] = categorical(m[0]).sample();\n \n  for(int i:[1..samples))\n    st[i] = categorical(m[st[i - 1]]).sample();\n\n  boolean[] flips = new boolean[samples];\n  for(int j:[0..samples))\n    flips[j] = bernoulli(bias[st[j]]).sample();\n\n  flips.observe(measured);\n}";
	}
}