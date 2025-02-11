package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray3$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements RaggedArray3$CoreInterface {
	private double[][] a;
	private double[] cv$var32$countGlobal;
	private double[] d;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample43 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample43 = false;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$d;
	private double logProbability$obs;
	private double logProbability$var31;
	private double logProbability$var33;
	private double logProbability$var39;
	private int[] obs;
	private int[] obs_measured;
	private boolean setFlag$d = false;
	private boolean setFlag$obs = false;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray3$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$a() {
		return a;
	}

	@Override
	public final double[] get$d() {
		return d;
	}

	@Override
	public final void set$d(double[] cv$value) {
		d = cv$value;
		setFlag$d = true;
		fixedProbFlag$sample35 = false;
		fixedProbFlag$sample43 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		fixedFlag$sample35 = cv$value;
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
		fixedProbFlag$sample43 = (cv$value && fixedProbFlag$sample43);
	}

	@Override
	public final boolean get$fixedFlag$sample43() {
		return fixedFlag$sample43;
	}

	@Override
	public final void set$fixedFlag$sample43(boolean cv$value) {
		fixedFlag$sample43 = cv$value;
		fixedProbFlag$sample43 = (cv$value && fixedProbFlag$sample43);
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
	public final double get$logProbability$d() {
		return logProbability$d;
	}

	@Override
	public final double get$logProbability$obs() {
		return logProbability$obs;
	}

	@Override
	public final int[] get$obs() {
		return obs;
	}

	@Override
	public final void set$obs(int[] cv$value) {
		obs = cv$value;
		setFlag$obs = true;
		fixedProbFlag$sample43 = false;
	}

	@Override
	public final int[] get$obs_measured() {
		return obs_measured;
	}

	@Override
	public final void set$obs_measured(int[] cv$value) {
		obs_measured = cv$value;
	}

	@Override
	public final int get$y() {
		return y;
	}

	@Override
	public final void set$y(int cv$value) {
		y = cv$value;
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(d, a[y]);
			logProbability$var31 = cv$distributionAccumulator;
			logProbability$d = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample35 = fixedFlag$sample35;
		} else {
			logProbability$var31 = logProbability$d;
			logProbability$$model = (logProbability$$model + logProbability$d);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + logProbability$d);
		}
	}

	private final void logProbabilityValue$sample43() {
		if(!fixedProbFlag$sample43) {
			double cv$sampleAccumulator = 0.0;
			for(int var38 = 0; var38 < length$obs_measured; var38 += 1) {
				int cv$sampleValue = obs[var38];
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < d.length))?Math.log(d[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			logProbability$var33 = cv$sampleAccumulator;
			logProbability$var39 = cv$sampleAccumulator;
			logProbability$obs = (logProbability$obs + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample43 = (fixedFlag$sample43 && fixedFlag$sample35);
		} else {
			logProbability$var33 = logProbability$var39;
			logProbability$obs = (logProbability$obs + logProbability$var39);
			logProbability$$model = (logProbability$$model + logProbability$var39);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var39);
		}
	}

	private final void sample35() {
		int cv$arrayLength = a[y].length;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$var32$countGlobal[cv$loopIndex] = 0.0;
		for(int var38 = 0; var38 < length$obs_measured; var38 += 1)
			cv$var32$countGlobal[obs[var38]] = (cv$var32$countGlobal[obs[var38]] + 1.0);
		Conjugates.sampleConjugateDirichletCategorical(RNG$, a[y], cv$var32$countGlobal, d);
	}

	@Override
	public final void allocateScratch() {
		cv$var32$countGlobal = new double[d.length];
	}

	@Override
	public final void allocator() {
		a = new double[2][];
		a[0] = new double[2];
		a[1] = new double[3];
		if(!setFlag$d)
			d = new double[d.length];
		if(!setFlag$obs)
			obs = new int[length$obs_measured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample35)
			DistributionSampling.sampleDirichlet(RNG$, a[y], d);
		if(!fixedFlag$sample43)
			parallelFor(RNG$, 0, length$obs_measured, 1,
				(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1)
							obs[var38] = DistributionSampling.sampleCategorical(RNG$1, d);
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample35)
			DistributionSampling.sampleDirichlet(RNG$, a[y], d);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample35)
			DistributionSampling.sampleDirichlet(RNG$, a[y], d);
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample35)
			sample35();
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
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var31 = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$d = 0.0;
		logProbability$var33 = 0.0;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample43)
			logProbability$var39 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		logProbabilityValue$sample43();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample35();
		logProbabilityValue$sample43();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample35();
		logProbabilityValue$sample43();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample35)
			DistributionSampling.sampleDirichlet(RNG$, a[y], d);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = obs.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			obs[cv$index1] = obs_measured[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2025, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model RaggedArray3(int y, int[] obs_measured) {\n    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n    \n    double[] d = dirichlet(a[y]).sample();\n    int[] obs = categorical(d).sample(obs_measured.length);\n    obs.observe(obs_measured);\n}";
	}
}