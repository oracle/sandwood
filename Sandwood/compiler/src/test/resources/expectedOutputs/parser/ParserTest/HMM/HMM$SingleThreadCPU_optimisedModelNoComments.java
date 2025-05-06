package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMM$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMM$CoreInterface {
	private double[] bias;
	private double[] cv$var28$countGlobal;
	private double[] cv$var52$stateProbabilityGlobal;
	private double[] cv$var70$stateProbabilityGlobal;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedFlag$sample71 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean fixedProbFlag$sample71 = false;
	private boolean fixedProbFlag$sample87 = false;
	private boolean[] flips;
	private int length$measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample71;
	private double[] logProbability$sample87;
	private double logProbability$st;
	private double logProbability$var16;
	private double logProbability$var28;
	private double logProbability$var32;
	private double logProbability$var44;
	private double logProbability$var51;
	private double logProbability$var52;
	private double[] logProbability$var69;
	private double[] logProbability$var85;
	private double[][] m;
	private boolean[] measured;
	private int samples;
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
		fixedProbFlag$sample45 = false;
		fixedProbFlag$sample87 = false;
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
		fixedProbFlag$sample71 = (cv$value && fixedProbFlag$sample71);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		fixedFlag$sample45 = cv$value;
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
	}

	@Override
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	@Override
	public final void set$fixedFlag$sample53(boolean cv$value) {
		fixedFlag$sample53 = cv$value;
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
		fixedProbFlag$sample71 = (cv$value && fixedProbFlag$sample71);
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
	}

	@Override
	public final boolean get$fixedFlag$sample71() {
		return fixedFlag$sample71;
	}

	@Override
	public final void set$fixedFlag$sample71(boolean cv$value) {
		fixedFlag$sample71 = cv$value;
		fixedProbFlag$sample71 = (cv$value && fixedProbFlag$sample71);
		fixedProbFlag$sample87 = (cv$value && fixedProbFlag$sample87);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
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
		fixedProbFlag$sample28 = false;
		fixedProbFlag$sample53 = false;
		fixedProbFlag$sample71 = false;
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
		fixedProbFlag$sample53 = false;
		fixedProbFlag$sample71 = false;
		fixedProbFlag$sample87 = false;
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

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$sampleAccumulator = 0.0;
			for(int var27 = 0; var27 < states; var27 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var27], v, states));
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
			double cv$sampleAccumulator = 0.0;
			for(int var43 = 0; var43 < states; var43 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(bias[var43], 1.0, 1.0));
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
			int cv$sampleValue = st[0];
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(m[0][cv$sampleValue]):Double.NEGATIVE_INFINITY);
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

	private final void logProbabilityValue$sample71() {
		if(!fixedProbFlag$sample71) {
			double cv$accumulator = 0.0;
			for(int i = 1; i < samples; i += 1) {
				int cv$sampleValue = st[i];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(m[st[(i - 1)]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var69[(i - 1)] = cv$distributionAccumulator;
				logProbability$sample71[(i - 1)] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample71 = ((fixedFlag$sample71 && fixedFlag$sample28) && fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 1; i < samples; i += 1) {
				double cv$rvAccumulator = logProbability$sample71[(i - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var69[(i - 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample87() {
		if(!fixedProbFlag$sample87) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double var84 = bias[st[j]];
				double cv$distributionAccumulator = Math.log((flips[j]?var84:(1.0 - var84)));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var85[j] = cv$distributionAccumulator;
				logProbability$sample87[j] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample87 = ((fixedFlag$sample45 && fixedFlag$sample53) && fixedFlag$sample71);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = logProbability$sample87[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var85[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample28(int var27) {
		for(int cv$loopIndex = 0; cv$loopIndex < states; cv$loopIndex += 1)
			cv$var28$countGlobal[cv$loopIndex] = 0.0;
		if((var27 == 0))
			cv$var28$countGlobal[st[0]] = (cv$var28$countGlobal[st[0]] + 1.0);
		for(int i = 1; i < samples; i += 1) {
			if((var27 == st[(i - 1)]))
				cv$var28$countGlobal[st[i]] = (cv$var28$countGlobal[st[i]] + 1.0);
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var28$countGlobal, m[var27], states);
	}

	private final void sample45(int var43) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int j = 0; j < samples; j += 1) {
			if((var43 == st[j])) {
				cv$count = (cv$count + 1);
				if(flips[j])
					cv$sum = (cv$sum + 1);
			}
		}
		bias[var43] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample53() {
		int cv$numNumStates = Math.max(0, states);
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			st[0] = cv$valuePos;
			double cv$accumulatedProbabilities = ((cv$valuePos < states)?Math.log(m[0][cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((1 < samples))
				cv$accumulatedProbabilities = ((((0.0 <= st[1]) && (st[1] < states))?Math.log(m[cv$valuePos][st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			if((0 < samples)) {
				double cv$temp$4$var84 = bias[cv$valuePos];
				cv$accumulatedProbabilities = (Math.log((flips[0]?cv$temp$4$var84:(1.0 - cv$temp$4$var84))) + cv$accumulatedProbabilities);
			}
			cv$var52$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double cv$logSum;
		double cv$lseMax = cv$var52$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var52$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var52$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$var52$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$var52$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var52$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var52$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var52$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st[0] = DistributionSampling.sampleCategorical(RNG$, cv$var52$stateProbabilityGlobal, cv$numNumStates);
	}

	private final void sample71(int i) {
		int cv$numNumStates = Math.max(0, states);
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			st[i] = cv$valuePos;
			double cv$accumulatedProbabilities = ((cv$valuePos < states)?Math.log(m[st[(i - 1)]][cv$valuePos]):Double.NEGATIVE_INFINITY);
			int index$i$2_2 = (i + 1);
			if((index$i$2_2 < samples))
				cv$accumulatedProbabilities = ((((0.0 <= st[index$i$2_2]) && (st[index$i$2_2] < states))?Math.log(m[cv$valuePos][st[index$i$2_2]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			double cv$temp$4$var84 = bias[cv$valuePos];
			cv$accumulatedProbabilities = (Math.log((flips[i]?cv$temp$4$var84:(1.0 - cv$temp$4$var84))) + cv$accumulatedProbabilities);
			cv$var70$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double cv$logSum;
		double cv$lseMax = cv$var70$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var70$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var70$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$var70$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$var70$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var70$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var70$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var70$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st[i] = DistributionSampling.sampleCategorical(RNG$, cv$var70$stateProbabilityGlobal, cv$numNumStates);
	}

	@Override
	public final void allocateScratch() {
		cv$var28$countGlobal = new double[states];
		cv$var52$stateProbabilityGlobal = new double[states];
		cv$var70$stateProbabilityGlobal = new double[states];
	}

	@Override
	public final void allocator() {
		v = new double[states];
		if(!fixedFlag$sample28) {
			m = new double[states][];
			for(int var27 = 0; var27 < states; var27 += 1)
				m[var27] = new double[states];
		}
		if(!fixedFlag$sample45)
			bias = new double[states];
		if((!fixedFlag$sample53 || !fixedFlag$sample71))
			st = new int[length$measured];
		flips = new boolean[length$measured];
		logProbability$var69 = new double[(length$measured - 1)];
		logProbability$sample71 = new double[(length$measured - 1)];
		logProbability$var85 = new double[length$measured];
		logProbability$sample87 = new double[length$measured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample28) {
			for(int var27 = 0; var27 < states; var27 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, states, m[var27]);
		}
		if(!fixedFlag$sample45) {
			for(int var43 = 0; var43 < states; var43 += 1)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		if(!fixedFlag$sample71) {
			for(int i = 1; i < samples; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]], states);
		}
		for(int j = 0; j < samples; j += 1)
			flips[j] = DistributionSampling.sampleBernoulli(RNG$, bias[st[j]]);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample28) {
			for(int var27 = 0; var27 < states; var27 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, states, m[var27]);
		}
		if(!fixedFlag$sample45) {
			for(int var43 = 0; var43 < states; var43 += 1)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		if(!fixedFlag$sample71) {
			for(int i = 1; i < samples; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]], states);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample28) {
			for(int var27 = 0; var27 < states; var27 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, states, m[var27]);
		}
		if(!fixedFlag$sample45) {
			for(int var43 = 0; var43 < states; var43 += 1)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		if(!fixedFlag$sample71) {
			for(int i = 1; i < samples; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]], states);
		}
		for(int j = 0; j < samples; j += 1)
			flips[j] = DistributionSampling.sampleBernoulli(RNG$, bias[st[j]]);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample28) {
			for(int var27 = 0; var27 < states; var27 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, states, m[var27]);
		}
		if(!fixedFlag$sample45) {
			for(int var43 = 0; var43 < states; var43 += 1)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		if(!fixedFlag$sample71) {
			for(int i = 1; i < samples; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]], states);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample28) {
			for(int var27 = 0; var27 < states; var27 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, states, m[var27]);
		}
		if(!fixedFlag$sample45) {
			for(int var43 = 0; var43 < states; var43 += 1)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		if(!fixedFlag$sample71) {
			for(int i = 1; i < samples; i += 1)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]], states);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample28) {
				for(int var27 = 0; var27 < states; var27 += 1)
					sample28(var27);
			}
			if(!fixedFlag$sample45) {
				for(int var43 = 0; var43 < states; var43 += 1)
					sample45(var43);
			}
			if(!fixedFlag$sample53)
				sample53();
			if(!fixedFlag$sample71) {
				for(int i = 1; i < samples; i += 1)
					sample71(i);
			}
		} else {
			if(!fixedFlag$sample71) {
				for(int i = (samples - 1); i >= 1; i -= 1)
					sample71(i);
			}
			if(!fixedFlag$sample53)
				sample53();
			if(!fixedFlag$sample45) {
				for(int var43 = (states - 1); var43 >= 0; var43 -= 1)
					sample45(var43);
			}
			if(!fixedFlag$sample28) {
				for(int var27 = (states - 1); var27 >= 0; var27 -= 1)
					sample28(var27);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		for(int var13 = 0; var13 < states; var13 += 1)
			v[var13] = 0.1;
		samples = length$measured;
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
		for(int i = 1; i < samples; i += 1)
			logProbability$var69[(i - 1)] = Double.NaN;
		if(!fixedProbFlag$sample71) {
			for(int i = 1; i < samples; i += 1)
				logProbability$sample71[(i - 1)] = Double.NaN;
		}
		for(int j = 0; j < samples; j += 1)
			logProbability$var85[j] = Double.NaN;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample87) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample87[j] = Double.NaN;
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
		if(fixedFlag$sample71)
			logProbabilityValue$sample71();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample71();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample71();
		logProbabilityValue$sample87();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = measured[cv$index1];
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
		     + "model HMM(boolean[] measured, int states) {\n"
		     + "\n"
		     + "  double[] v = new double[states] <~ 0.1;\n"
		     + "  double[][] m = dirichlet(v).sample(states);\n"
		     + "    \n"
		     + "  double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "  int samples = measured.length;\n"
		     + "  int[] st = new int[samples];\n"
		     + "        \n"
		     + "  st[0] = categorical(m[0]).sample();\n"
		     + " \n"
		     + "  for(int i:[1..samples))\n"
		     + "    st[i] = categorical(m[st[i - 1]]).sample();\n"
		     + "\n"
		     + "  boolean[] flips = new boolean[samples];\n"
		     + "  for(int j:[0..samples))\n"
		     + "    flips[j] = bernoulli(bias[st[j]]).sample();\n"
		     + "\n"
		     + "  flips.observe(measured);\n"
		     + "}";
	}
}