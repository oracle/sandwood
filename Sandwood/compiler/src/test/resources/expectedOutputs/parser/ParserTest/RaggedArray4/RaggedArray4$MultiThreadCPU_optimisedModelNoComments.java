package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray4$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements RaggedArray4$CoreInterface {
	private double[][] a;
	private double[] b;
	private double[] cv$var45$stateProbabilityGlobal;
	private double[] cv$var48$countGlobal;
	private double[] d;
	private boolean fixedFlag$sample47 = false;
	private boolean fixedFlag$sample50 = false;
	private boolean fixedProbFlag$sample47 = false;
	private boolean fixedProbFlag$sample50 = false;
	private boolean fixedProbFlag$sample64 = false;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$d;
	private double logProbability$obs;
	private double logProbability$var44;
	private double logProbability$var47;
	private double logProbability$var49;
	private double logProbability$var62;
	private double logProbability$y;
	private int[] obs;
	private int[] obs_measured;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray4$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$a() {
		return a;
	}

	@Override
	public final double[] get$b() {
		return b;
	}

	@Override
	public final double[] get$d() {
		return d;
	}

	@Override
	public final void set$d(double[] cv$value) {
		d = cv$value;
		fixedProbFlag$sample50 = false;
		fixedProbFlag$sample64 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample47() {
		return fixedFlag$sample47;
	}

	@Override
	public final void set$fixedFlag$sample47(boolean cv$value) {
		fixedFlag$sample47 = cv$value;
		fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
		fixedProbFlag$sample50 = (cv$value && fixedProbFlag$sample50);
	}

	@Override
	public final boolean get$fixedFlag$sample50() {
		return fixedFlag$sample50;
	}

	@Override
	public final void set$fixedFlag$sample50(boolean cv$value) {
		fixedFlag$sample50 = cv$value;
		fixedProbFlag$sample50 = (cv$value && fixedProbFlag$sample50);
		fixedProbFlag$sample64 = (cv$value && fixedProbFlag$sample64);
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
	public final double get$logProbability$y() {
		return logProbability$y;
	}

	@Override
	public final int[] get$obs() {
		return obs;
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
		fixedProbFlag$sample47 = false;
		fixedProbFlag$sample50 = false;
	}

	private final void logProbabilityValue$sample47() {
		if(!fixedProbFlag$sample47) {
			double cv$distributionAccumulator = (((0.0 <= y) && (y < 2))?Math.log(b[y]):Double.NEGATIVE_INFINITY);
			logProbability$var44 = cv$distributionAccumulator;
			logProbability$y = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample47 = fixedFlag$sample47;
		} else {
			logProbability$var44 = logProbability$y;
			logProbability$$model = (logProbability$$model + logProbability$y);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + logProbability$y);
		}
	}

	private final void logProbabilityValue$sample50() {
		if(!fixedProbFlag$sample50) {
			int lengthCV$a$48_15 = -1;
			if((1 == y))
				lengthCV$a$48_15 = 3;
			if((0 == y))
				lengthCV$a$48_15 = 2;
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(d, a[y], lengthCV$a$48_15);
			logProbability$var47 = cv$distributionAccumulator;
			logProbability$d = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample50 = (fixedFlag$sample50 && fixedFlag$sample47);
		} else {
			logProbability$var47 = logProbability$d;
			logProbability$$model = (logProbability$$model + logProbability$d);
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + logProbability$d);
		}
	}

	private final void logProbabilityValue$sample64() {
		if(!fixedProbFlag$sample64) {
			double cv$sampleAccumulator = 0.0;
			for(int var61 = 0; var61 < length$obs_measured; var61 += 1) {
				int cv$sampleValue = obs[var61];
				int lengthCV$a$48_16 = -1;
				if((1 == y))
					lengthCV$a$48_16 = 3;
				if((0 == y))
					lengthCV$a$48_16 = 2;
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$a$48_16))?Math.log(d[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			logProbability$var49 = cv$sampleAccumulator;
			logProbability$var62 = cv$sampleAccumulator;
			logProbability$obs = (logProbability$obs + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample64 = fixedFlag$sample50;
		} else {
			logProbability$var49 = logProbability$var62;
			logProbability$obs = (logProbability$obs + logProbability$var62);
			logProbability$$model = (logProbability$$model + logProbability$var62);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var62);
		}
	}

	private final void sample47() {
		y = 0;
		cv$var45$stateProbabilityGlobal[0] = (DistributionSampling.logProbabilityDirichlet(d, a[0], 2) + Math.log(b[0]));
		y = 1;
		cv$var45$stateProbabilityGlobal[1] = (DistributionSampling.logProbabilityDirichlet(d, a[1], 3) + Math.log(b[1]));
		double cv$logSum;
		double cv$lseMax = cv$var45$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var45$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var45$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var45$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			cv$var45$stateProbabilityGlobal[0] = 0.5;
			cv$var45$stateProbabilityGlobal[1] = 0.5;
		} else {
			cv$var45$stateProbabilityGlobal[0] = Math.exp((cv$var45$stateProbabilityGlobal[0] - cv$logSum));
			cv$var45$stateProbabilityGlobal[1] = Math.exp((cv$var45$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var45$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var45$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		y = DistributionSampling.sampleCategorical(RNG$, cv$var45$stateProbabilityGlobal, 2);
	}

	private final void sample50() {
		int lengthCV$a$48_13 = -1;
		if((1 == y))
			lengthCV$a$48_13 = 3;
		if((0 == y))
			lengthCV$a$48_13 = 2;
		for(int cv$loopIndex = 0; cv$loopIndex < lengthCV$a$48_13; cv$loopIndex += 1)
			cv$var48$countGlobal[cv$loopIndex] = 0.0;
		for(int var61 = 0; var61 < length$obs_measured; var61 += 1)
			cv$var48$countGlobal[obs[var61]] = (cv$var48$countGlobal[obs[var61]] + 1.0);
		int lengthCV$a$48_14 = -1;
		if((1 == y))
			lengthCV$a$48_14 = 3;
		if((0 == y))
			lengthCV$a$48_14 = 2;
		Conjugates.sampleConjugateDirichletCategorical(RNG$, a[y], cv$var48$countGlobal, d, lengthCV$a$48_14);
	}

	@Override
	public final void allocateScratch() {
		cv$var45$stateProbabilityGlobal = new double[2];
		cv$var48$countGlobal = new double[3];
	}

	@Override
	public final void allocator() {
		a = new double[2][];
		a[0] = new double[2];
		a[1] = new double[3];
		b = new double[2];
		if(!fixedFlag$sample50) {
			int lengthCV$a$48_11 = -1;
			if((1 == y))
				lengthCV$a$48_11 = 3;
			if((0 == y))
				lengthCV$a$48_11 = 2;
			d = new double[lengthCV$a$48_11];
		}
		obs = new int[length$obs_measured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample47)
			y = DistributionSampling.sampleCategorical(RNG$, b, 2);
		if(!fixedFlag$sample50) {
			int lengthCV$a$48_17 = -1;
			if((1 == y))
				lengthCV$a$48_17 = 3;
			if((0 == y))
				lengthCV$a$48_17 = 2;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$48_17, d);
		}
		int lengthCV$a$48_18 = -1;
		if((1 == y))
			lengthCV$a$48_18 = 3;
		if((0 == y))
			lengthCV$a$48_18 = 2;
		int lengthCV$a$48_18$1 = lengthCV$a$48_18;
		parallelFor(RNG$, 0, length$obs_measured, 1,
			(int forStart$var61, int forEnd$var61, int threadID$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var61 = forStart$var61; var61 < forEnd$var61; var61 += 1)
						obs[var61] = DistributionSampling.sampleCategorical(RNG$1, d, lengthCV$a$48_18$1);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample47)
			y = DistributionSampling.sampleCategorical(RNG$, b, 2);
		if(!fixedFlag$sample50) {
			int lengthCV$a$48_20 = -1;
			if((1 == y))
				lengthCV$a$48_20 = 3;
			if((0 == y))
				lengthCV$a$48_20 = 2;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$48_20, d);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample47)
			y = DistributionSampling.sampleCategorical(RNG$, b, 2);
		if(!fixedFlag$sample50) {
			int lengthCV$a$48_19 = -1;
			if((1 == y))
				lengthCV$a$48_19 = 3;
			if((0 == y))
				lengthCV$a$48_19 = 2;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$48_19, d);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample47)
				sample47();
			if(!fixedFlag$sample50)
				sample50();
		} else {
			if(!fixedFlag$sample50)
				sample50();
			if(!fixedFlag$sample47)
				sample47();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		double[] var5 = a[0];
		var5[0] = 0.4;
		var5[1] = 0.6;
		double[] var18 = a[1];
		var18[0] = 0.2;
		var18[1] = 0.3;
		var18[2] = 0.5;
		b[0] = 0.35;
		b[1] = 0.65;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var44 = 0.0;
		if(!fixedProbFlag$sample47)
			logProbability$y = 0.0;
		logProbability$var47 = 0.0;
		if(!fixedProbFlag$sample50)
			logProbability$d = 0.0;
		logProbability$var49 = 0.0;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample64)
			logProbability$var62 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample47)
			logProbabilityValue$sample47();
		if(fixedFlag$sample50)
			logProbabilityValue$sample50();
		logProbabilityValue$sample64();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		logProbabilityValue$sample64();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		logProbabilityValue$sample64();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample47)
			y = DistributionSampling.sampleCategorical(RNG$, b, 2);
		if(!fixedFlag$sample50) {
			int lengthCV$a$48_21 = -1;
			if((1 == y))
				lengthCV$a$48_21 = 3;
			if((0 == y))
				lengthCV$a$48_21 = 2;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$48_21, d);
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
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + " package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model RaggedArray4(int[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    double[] b = { 0.35, 0.65 };\n"
		     + "    int y = categorical(b).sample();\n"
		     + "    double[] d = dirichlet(a[y]).sample();\n"
		     + "    int[] obs = categorical(d).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}