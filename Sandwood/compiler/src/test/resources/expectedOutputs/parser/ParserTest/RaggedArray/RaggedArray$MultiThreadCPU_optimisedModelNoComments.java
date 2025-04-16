package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements RaggedArray$CoreInterface {
	private double[][] a;
	private double[][] b;
	private double[] cv$var69$stateProbabilityGlobal;
	private boolean fixedFlag$sample73 = false;
	private boolean fixedProbFlag$sample73 = false;
	private boolean fixedProbFlag$sample89 = false;
	private int i;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$i;
	private double logProbability$obs;
	private double logProbability$p;
	private double logProbability$var68;
	private double logProbability$var72;
	private double logProbability$var85;
	private boolean[] obs;
	private boolean[] obs_measured;
	private double p;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$a() {
		return a;
	}

	@Override
	public final double[][] get$b() {
		return b;
	}

	@Override
	public final boolean get$fixedFlag$sample73() {
		return fixedFlag$sample73;
	}

	@Override
	public final void set$fixedFlag$sample73(boolean cv$value) {
		fixedFlag$sample73 = cv$value;
		fixedProbFlag$sample73 = (cv$value && fixedProbFlag$sample73);
		fixedProbFlag$sample89 = (cv$value && fixedProbFlag$sample89);
	}

	@Override
	public final int get$i() {
		return i;
	}

	@Override
	public final void set$i(int cv$value) {
		i = cv$value;
		fixedProbFlag$sample73 = false;
		fixedProbFlag$sample89 = false;
	}

	@Override
	public final int get$length$obs_measured() {
		return length$obs_measured;
	}

	@Override
	public final void set$length$obs_measured(int cv$value) {
		length$obs_measured = cv$value;
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
	public final double get$logProbability$i() {
		return logProbability$i;
	}

	@Override
	public final double get$logProbability$obs() {
		return logProbability$obs;
	}

	@Override
	public final double get$logProbability$p() {
		return logProbability$p;
	}

	@Override
	public final boolean[] get$obs() {
		return obs;
	}

	@Override
	public final boolean[] get$obs_measured() {
		return obs_measured;
	}

	@Override
	public final void set$obs_measured(boolean[] cv$value) {
		obs_measured = cv$value;
	}

	@Override
	public final double get$p() {
		return p;
	}

	@Override
	public final int get$y() {
		return y;
	}

	@Override
	public final void set$y(int cv$value) {
		y = cv$value;
	}

	private final void logProbabilityValue$sample73() {
		if(!fixedProbFlag$sample73) {
			int lengthCV$a$71_9 = -1;
			if((0 == y))
				lengthCV$a$71_9 = 2;
			if((1 == y))
				lengthCV$a$71_9 = 3;
			double cv$distributionAccumulator = (((0.0 <= i) && (i < lengthCV$a$71_9))?Math.log(a[y][i]):Double.NEGATIVE_INFINITY);
			logProbability$var68 = cv$distributionAccumulator;
			logProbability$i = cv$distributionAccumulator;
			logProbability$p = (logProbability$p + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample73)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample73 = fixedFlag$sample73;
		} else {
			logProbability$var68 = logProbability$i;
			logProbability$p = (logProbability$p + logProbability$i);
			logProbability$$model = (logProbability$$model + logProbability$i);
			if(fixedFlag$sample73)
				logProbability$$evidence = (logProbability$$evidence + logProbability$i);
		}
	}

	private final void logProbabilityValue$sample89() {
		if(!fixedProbFlag$sample89) {
			double cv$sampleAccumulator = 0.0;
			for(int var84 = 0; var84 < length$obs_measured; var84 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((obs[var84]?p:(1.0 - p))));
			logProbability$var72 = cv$sampleAccumulator;
			logProbability$var85 = cv$sampleAccumulator;
			logProbability$obs = (logProbability$obs + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample89 = fixedFlag$sample73;
		} else {
			logProbability$var72 = logProbability$var85;
			logProbability$obs = (logProbability$obs + logProbability$var85);
			logProbability$$model = (logProbability$$model + logProbability$var85);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var85);
		}
	}

	private final void sample73() {
		int lengthCV$a$71_7 = -1;
		if((0 == y))
			lengthCV$a$71_7 = 2;
		if((1 == y))
			lengthCV$a$71_7 = 3;
		int cv$numNumStates = Math.max(0, lengthCV$a$71_7);
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			i = cv$valuePos;
			p = b[y][cv$valuePos];
			int lengthCV$a$71_8 = -1;
			if((0 == y))
				lengthCV$a$71_8 = 2;
			if((1 == y))
				lengthCV$a$71_8 = 3;
			double cv$accumulatedProbabilities = ((cv$valuePos < lengthCV$a$71_8)?Math.log(a[y][cv$valuePos]):Double.NEGATIVE_INFINITY);
			for(int var84 = 0; var84 < length$obs_measured; var84 += 1)
				cv$accumulatedProbabilities = (Math.log((obs[var84]?p:(1.0 - p))) + cv$accumulatedProbabilities);
			cv$var69$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double cv$logSum;
		double cv$lseMax = cv$var69$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var69$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var69$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$var69$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$var69$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var69$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var69$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var69$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		i = DistributionSampling.sampleCategorical(RNG$, cv$var69$stateProbabilityGlobal, cv$numNumStates);
		p = b[y][i];
	}

	@Override
	public final void allocateScratch() {
		cv$var69$stateProbabilityGlobal = new double[3];
	}

	@Override
	public final void allocator() {
		a = new double[2][];
		a[0] = new double[2];
		a[1] = new double[3];
		b = new double[2][];
		b[0] = new double[2];
		b[1] = new double[3];
		obs = new boolean[length$obs_measured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample73) {
			int lengthCV$a$71_10 = -1;
			if((0 == y))
				lengthCV$a$71_10 = 2;
			if((1 == y))
				lengthCV$a$71_10 = 3;
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$71_10);
			p = b[y][i];
		}
		parallelFor(RNG$, 0, length$obs_measured, 1,
			(int forStart$var84, int forEnd$var84, int threadID$var84, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var84 = forStart$var84; var84 < forEnd$var84; var84 += 1)
						obs[var84] = DistributionSampling.sampleBernoulli(RNG$1, p);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample73) {
			int lengthCV$a$71_12 = -1;
			if((0 == y))
				lengthCV$a$71_12 = 2;
			if((1 == y))
				lengthCV$a$71_12 = 3;
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$71_12);
			p = b[y][i];
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample73) {
			int lengthCV$a$71_11 = -1;
			if((0 == y))
				lengthCV$a$71_11 = 2;
			if((1 == y))
				lengthCV$a$71_11 = 3;
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$71_11);
			p = b[y][i];
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample73)
			sample73();
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		double[] var6 = a[0];
		var6[0] = 0.4;
		var6[1] = 0.6;
		double[] var19 = a[1];
		var19[0] = 0.2;
		var19[1] = 0.3;
		var19[2] = 0.5;
		double[] var38 = b[0];
		var38[0] = 0.2;
		var38[1] = 0.8;
		double[] var51 = b[1];
		var51[0] = 0.4;
		var51[1] = 0.2;
		var51[2] = 0.6;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var68 = 0.0;
		logProbability$p = 0.0;
		if(!fixedProbFlag$sample73)
			logProbability$i = 0.0;
		logProbability$var72 = 0.0;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample89)
			logProbability$var85 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample73)
			logProbabilityValue$sample73();
		logProbabilityValue$sample89();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample73();
		logProbabilityValue$sample89();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample73();
		logProbabilityValue$sample89();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample73) {
			int lengthCV$a$71_13 = -1;
			if((0 == y))
				lengthCV$a$71_13 = 2;
			if((1 == y))
				lengthCV$a$71_13 = 3;
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$71_13);
			p = b[y][i];
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = obs.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			obs[cv$index1] = obs_measured[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		if(fixedFlag$sample73)
			p = b[y][i];
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model RaggedArray(int y, boolean[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    double[][] b = {{0.2, 0.8}, {0.4, 0.2, 0.6}};\n"
		     + "    \n"
		     + "    int i = categorical(a[y]).sample();\n"
		     + "    double p = b[y][i];\n"
		     + "    boolean[] obs = bernoulli(p).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}