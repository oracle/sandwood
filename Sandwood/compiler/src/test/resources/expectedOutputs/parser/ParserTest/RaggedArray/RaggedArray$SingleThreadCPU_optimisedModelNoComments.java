package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements RaggedArray$CoreInterface {
	private double[][] a;
	private double[][] b;
	private double[] cv$var57$stateProbabilityGlobal;
	private boolean fixedFlag$sample62 = false;
	private boolean fixedFlag$sample72 = false;
	private boolean fixedProbFlag$sample62 = false;
	private boolean fixedProbFlag$sample72 = false;
	private int i;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$i;
	private double logProbability$obs;
	private double logProbability$p;
	private double logProbability$var56;
	private double logProbability$var60;
	private double logProbability$var66;
	private boolean[] obs;
	private boolean[] obs_measured;
	private double p;
	private boolean setFlag$obs = false;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray$SingleThreadCPU(ExecutionTarget target) {
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
	public final boolean get$fixedFlag$sample62() {
		return fixedFlag$sample62;
	}

	@Override
	public final void set$fixedFlag$sample62(boolean cv$value) {
		fixedFlag$sample62 = cv$value;
		fixedProbFlag$sample62 = (cv$value && fixedProbFlag$sample62);
		fixedProbFlag$sample72 = (cv$value && fixedProbFlag$sample72);
	}

	@Override
	public final boolean get$fixedFlag$sample72() {
		return fixedFlag$sample72;
	}

	@Override
	public final void set$fixedFlag$sample72(boolean cv$value) {
		fixedFlag$sample72 = cv$value;
		fixedProbFlag$sample72 = (cv$value && fixedProbFlag$sample72);
	}

	@Override
	public final int get$i() {
		return i;
	}

	@Override
	public final void set$i(int cv$value) {
		i = cv$value;
		fixedProbFlag$sample62 = false;
		fixedProbFlag$sample72 = false;
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
	public final void set$obs(boolean[] cv$value) {
		obs = cv$value;
		setFlag$obs = true;
		fixedProbFlag$sample72 = false;
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

	private final void logProbabilityValue$sample62() {
		if(!fixedProbFlag$sample62) {
			double[] var55 = a[y];
			double cv$distributionAccumulator = (((0.0 <= i) && (i < var55.length))?Math.log(var55[i]):Double.NEGATIVE_INFINITY);
			logProbability$var56 = cv$distributionAccumulator;
			logProbability$i = cv$distributionAccumulator;
			logProbability$p = (logProbability$p + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample62)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample62 = fixedFlag$sample62;
		} else {
			logProbability$var56 = logProbability$i;
			logProbability$p = (logProbability$p + logProbability$i);
			logProbability$$model = (logProbability$$model + logProbability$i);
			if(fixedFlag$sample62)
				logProbability$$evidence = (logProbability$$evidence + logProbability$i);
		}
	}

	private final void logProbabilityValue$sample72() {
		if(!fixedProbFlag$sample72) {
			double cv$sampleAccumulator = 0.0;
			for(int var65 = 0; var65 < length$obs_measured; var65 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(obs[var65], p));
			logProbability$var60 = cv$sampleAccumulator;
			logProbability$var66 = cv$sampleAccumulator;
			logProbability$obs = (logProbability$obs + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample72 = (fixedFlag$sample72 && fixedFlag$sample62);
		} else {
			logProbability$var60 = logProbability$var66;
			logProbability$obs = (logProbability$obs + logProbability$var66);
			logProbability$$model = (logProbability$$model + logProbability$var66);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var66);
		}
	}

	private final void sample62() {
		int cv$noStates = a[y].length;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			i = cv$valuePos;
			p = b[y][cv$valuePos];
			double[] cv$temp$0$var55 = a[y];
			double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var55.length)?Math.log(cv$temp$0$var55[cv$valuePos]):Double.NEGATIVE_INFINITY);
			for(int var65 = 0; var65 < length$obs_measured; var65 += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var65], p) + cv$accumulatedProbabilities);
			cv$var57$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double cv$logSum;
		double cv$lseMax = cv$var57$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var57$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var57$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$var57$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$var57$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var57$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var57$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var57$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		i = DistributionSampling.sampleCategorical(RNG$, cv$var57$stateProbabilityGlobal);
		p = b[y][i];
	}

	@Override
	public final void allocateScratch() {
		cv$var57$stateProbabilityGlobal = new double[3];
	}

	@Override
	public final void allocator() {
		a = new double[2][];
		a[0] = new double[2];
		a[1] = new double[3];
		b = new double[2][];
		b[0] = new double[2];
		b[1] = new double[3];
		if(!setFlag$obs)
			obs = new boolean[length$obs_measured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample62) {
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
			p = b[y][i];
		}
		if(!fixedFlag$sample72) {
			for(int var65 = 0; var65 < length$obs_measured; var65 += 1)
				obs[var65] = DistributionSampling.sampleBernoulli(RNG$, p);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample62) {
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
			p = b[y][i];
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample62) {
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
			p = b[y][i];
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample62)
			sample62();
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		double[] var8 = a[0];
		var8[0] = 0.4;
		var8[1] = 0.6;
		double[] var18 = a[1];
		var18[0] = 0.2;
		var18[1] = 0.3;
		var18[2] = 0.5;
		double[] var33 = b[0];
		var33[0] = 0.2;
		var33[1] = 0.8;
		double[] var43 = b[1];
		var43[0] = 0.4;
		var43[1] = 0.2;
		var43[2] = 0.6;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var56 = 0.0;
		logProbability$p = 0.0;
		if(!fixedProbFlag$sample62)
			logProbability$i = 0.0;
		logProbability$var60 = 0.0;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample72)
			logProbability$var66 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample62)
			logProbabilityValue$sample62();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample62();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample62();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample62) {
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
			p = b[y][i];
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = obs.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			obs[cv$index1] = obs_measured[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		p = b[y][i];
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2025, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model RaggedArray(int y, boolean[] obs_measured) {\n    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n    double[][] b = {{0.2, 0.8}, {0.4, 0.2, 0.6}};\n    \n    int i = categorical(a[y]).sample();\n    double p = b[y][i];\n    boolean[] obs = bernoulli(p).sample(obs_measured.length);\n    obs.observe(obs_measured);\n}";
	}
}