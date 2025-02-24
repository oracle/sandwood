package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements RaggedArray2$CoreInterface {
	private double[][] a;
	private double[][] b;
	private double[] c;
	private double[] cv$var77$stateProbabilityGlobal;
	private double[] cv$var80$stateProbabilityGlobal;
	private boolean fixedFlag$sample100 = false;
	private boolean fixedFlag$sample81 = false;
	private boolean fixedFlag$sample84 = false;
	private boolean fixedProbFlag$sample100 = false;
	private boolean fixedProbFlag$sample81 = false;
	private boolean fixedProbFlag$sample84 = false;
	private int i;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$i;
	private double logProbability$obs;
	private double logProbability$p;
	private double logProbability$var76;
	private double logProbability$var79;
	private double logProbability$var83;
	private double logProbability$var96;
	private double logProbability$y;
	private boolean[] obs;
	private boolean[] obs_measured;
	private double p;
	private boolean setFlag$obs = false;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray2$MultiThreadCPU(ExecutionTarget target) {
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
	public final double[] get$c() {
		return c;
	}

	@Override
	public final boolean get$fixedFlag$sample100() {
		return fixedFlag$sample100;
	}

	@Override
	public final void set$fixedFlag$sample100(boolean cv$value) {
		fixedFlag$sample100 = cv$value;
		fixedProbFlag$sample100 = (cv$value && fixedProbFlag$sample100);
	}

	@Override
	public final boolean get$fixedFlag$sample81() {
		return fixedFlag$sample81;
	}

	@Override
	public final void set$fixedFlag$sample81(boolean cv$value) {
		fixedFlag$sample81 = cv$value;
		fixedProbFlag$sample81 = (cv$value && fixedProbFlag$sample81);
		fixedProbFlag$sample84 = (cv$value && fixedProbFlag$sample84);
		fixedProbFlag$sample100 = (cv$value && fixedProbFlag$sample100);
	}

	@Override
	public final boolean get$fixedFlag$sample84() {
		return fixedFlag$sample84;
	}

	@Override
	public final void set$fixedFlag$sample84(boolean cv$value) {
		fixedFlag$sample84 = cv$value;
		fixedProbFlag$sample84 = (cv$value && fixedProbFlag$sample84);
		fixedProbFlag$sample100 = (cv$value && fixedProbFlag$sample100);
	}

	@Override
	public final int get$i() {
		return i;
	}

	@Override
	public final void set$i(int cv$value) {
		i = cv$value;
		fixedProbFlag$sample84 = false;
		fixedProbFlag$sample100 = false;
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
	public final double get$logProbability$y() {
		return logProbability$y;
	}

	@Override
	public final boolean[] get$obs() {
		return obs;
	}

	@Override
	public final void set$obs(boolean[] cv$value) {
		obs = cv$value;
		setFlag$obs = true;
		fixedProbFlag$sample100 = false;
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
		fixedProbFlag$sample81 = false;
		fixedProbFlag$sample84 = false;
		fixedProbFlag$sample100 = false;
	}

	private final void logProbabilityValue$sample100() {
		if(!fixedProbFlag$sample100) {
			double cv$sampleAccumulator = 0.0;
			for(int var95 = 0; var95 < length$obs_measured; var95 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(obs[var95], p));
			logProbability$var83 = cv$sampleAccumulator;
			logProbability$var96 = cv$sampleAccumulator;
			logProbability$obs = (logProbability$obs + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample100 = ((fixedFlag$sample100 && fixedFlag$sample81) && fixedFlag$sample84);
		} else {
			logProbability$var83 = logProbability$var96;
			logProbability$obs = (logProbability$obs + logProbability$var96);
			logProbability$$model = (logProbability$$model + logProbability$var96);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var96);
		}
	}

	private final void logProbabilityValue$sample81() {
		if(!fixedProbFlag$sample81) {
			double cv$distributionAccumulator = (((0.0 <= y) && (y < 2))?Math.log(c[y]):Double.NEGATIVE_INFINITY);
			logProbability$var76 = cv$distributionAccumulator;
			logProbability$y = cv$distributionAccumulator;
			logProbability$p = (logProbability$p + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample81)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample81 = fixedFlag$sample81;
		} else {
			logProbability$var76 = logProbability$y;
			logProbability$p = (logProbability$p + logProbability$y);
			logProbability$$model = (logProbability$$model + logProbability$y);
			if(fixedFlag$sample81)
				logProbability$$evidence = (logProbability$$evidence + logProbability$y);
		}
	}

	private final void logProbabilityValue$sample84() {
		if(!fixedProbFlag$sample84) {
			int lengthCV$a$82_11 = -1;
			if((1 == y))
				lengthCV$a$82_11 = 3;
			if((0 == y))
				lengthCV$a$82_11 = 2;
			double cv$distributionAccumulator = (((0.0 <= i) && (i < lengthCV$a$82_11))?Math.log(a[y][i]):Double.NEGATIVE_INFINITY);
			logProbability$var79 = cv$distributionAccumulator;
			logProbability$i = cv$distributionAccumulator;
			logProbability$p = (logProbability$p + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample84 = (fixedFlag$sample84 && fixedFlag$sample81);
		} else {
			logProbability$var79 = logProbability$i;
			logProbability$p = (logProbability$p + logProbability$i);
			logProbability$$model = (logProbability$$model + logProbability$i);
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + logProbability$i);
		}
	}

	private final void sample81() {
		{
			y = 0;
			p = b[0][i];
			double cv$accumulatedProbabilities = ((((0.0 <= i) && (i < 2))?Math.log(a[0][i]):Double.NEGATIVE_INFINITY) + Math.log(c[0]));
			for(int var95 = 0; var95 < length$obs_measured; var95 += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var95], p) + cv$accumulatedProbabilities);
			cv$var77$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		y = 1;
		p = b[1][i];
		double cv$accumulatedProbabilities = ((((0.0 <= i) && (i < 3))?Math.log(a[1][i]):Double.NEGATIVE_INFINITY) + Math.log(c[1]));
		for(int var95 = 0; var95 < length$obs_measured; var95 += 1)
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var95], p) + cv$accumulatedProbabilities);
		cv$var77$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = cv$var77$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var77$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var77$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var77$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			cv$var77$stateProbabilityGlobal[0] = 0.5;
			cv$var77$stateProbabilityGlobal[1] = 0.5;
		} else {
			cv$var77$stateProbabilityGlobal[0] = Math.exp((cv$var77$stateProbabilityGlobal[0] - cv$logSum));
			cv$var77$stateProbabilityGlobal[1] = Math.exp((cv$var77$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var77$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var77$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		y = DistributionSampling.sampleCategorical(RNG$, cv$var77$stateProbabilityGlobal, 2);
		p = b[y][i];
	}

	private final void sample84() {
		int lengthCV$a$82_9 = -1;
		if((1 == y))
			lengthCV$a$82_9 = 3;
		if((0 == y))
			lengthCV$a$82_9 = 2;
		int cv$numNumStates = Math.max(0, lengthCV$a$82_9);
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			i = cv$valuePos;
			p = b[y][cv$valuePos];
			int lengthCV$a$82_10 = -1;
			if((1 == y))
				lengthCV$a$82_10 = 3;
			if((0 == y))
				lengthCV$a$82_10 = 2;
			double cv$accumulatedProbabilities = ((cv$valuePos < lengthCV$a$82_10)?Math.log(a[y][cv$valuePos]):Double.NEGATIVE_INFINITY);
			for(int var95 = 0; var95 < length$obs_measured; var95 += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(obs[var95], p) + cv$accumulatedProbabilities);
			cv$var80$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		double cv$logSum;
		double cv$lseMax = cv$var80$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var80$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var80$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$var80$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$var80$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var80$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var80$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var80$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		i = DistributionSampling.sampleCategorical(RNG$, cv$var80$stateProbabilityGlobal, cv$numNumStates);
		p = b[y][i];
	}

	@Override
	public final void allocateScratch() {
		cv$var77$stateProbabilityGlobal = new double[2];
		cv$var80$stateProbabilityGlobal = new double[3];
	}

	@Override
	public final void allocator() {
		a = new double[2][];
		a[0] = new double[2];
		a[1] = new double[3];
		b = new double[2][];
		b[0] = new double[2];
		b[1] = new double[3];
		c = new double[2];
		if(!setFlag$obs)
			obs = new boolean[length$obs_measured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample81)
			y = DistributionSampling.sampleCategorical(RNG$, c, 2);
		if(!fixedFlag$sample84) {
			int lengthCV$a$82_12 = -1;
			if((1 == y))
				lengthCV$a$82_12 = 3;
			if((0 == y))
				lengthCV$a$82_12 = 2;
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$82_12);
		}
		if((!fixedFlag$sample81 || !fixedFlag$sample84))
			p = b[y][i];
		if(!fixedFlag$sample100)
			parallelFor(RNG$, 0, length$obs_measured, 1,
				(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
							obs[var95] = DistributionSampling.sampleBernoulli(RNG$1, p);
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample81)
			y = DistributionSampling.sampleCategorical(RNG$, c, 2);
		if(!fixedFlag$sample84) {
			int lengthCV$a$82_14 = -1;
			if((1 == y))
				lengthCV$a$82_14 = 3;
			if((0 == y))
				lengthCV$a$82_14 = 2;
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$82_14);
		}
		if((!fixedFlag$sample81 || !fixedFlag$sample84))
			p = b[y][i];
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample81)
			y = DistributionSampling.sampleCategorical(RNG$, c, 2);
		if(!fixedFlag$sample84) {
			int lengthCV$a$82_13 = -1;
			if((1 == y))
				lengthCV$a$82_13 = 3;
			if((0 == y))
				lengthCV$a$82_13 = 2;
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$82_13);
		}
		if((!fixedFlag$sample81 || !fixedFlag$sample84))
			p = b[y][i];
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample81)
				sample81();
			if(!fixedFlag$sample84)
				sample84();
		} else {
			if(!fixedFlag$sample84)
				sample84();
			if(!fixedFlag$sample81)
				sample81();
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
		double[] var37 = b[0];
		var37[0] = 0.2;
		var37[1] = 0.8;
		double[] var50 = b[1];
		var50[0] = 0.4;
		var50[1] = 0.2;
		var50[2] = 0.6;
		c[0] = 0.35;
		c[1] = 0.65;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var76 = 0.0;
		logProbability$p = 0.0;
		if(!fixedProbFlag$sample81)
			logProbability$y = 0.0;
		logProbability$var79 = 0.0;
		if(!fixedProbFlag$sample84)
			logProbability$i = 0.0;
		logProbability$var83 = 0.0;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample100)
			logProbability$var96 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample81)
			logProbabilityValue$sample81();
		if(fixedFlag$sample84)
			logProbabilityValue$sample84();
		logProbabilityValue$sample100();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample81();
		logProbabilityValue$sample84();
		logProbabilityValue$sample100();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample81();
		logProbabilityValue$sample84();
		logProbabilityValue$sample100();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample81)
			y = DistributionSampling.sampleCategorical(RNG$, c, 2);
		if(!fixedFlag$sample84) {
			int lengthCV$a$82_15 = -1;
			if((1 == y))
				lengthCV$a$82_15 = 3;
			if((0 == y))
				lengthCV$a$82_15 = 2;
			i = DistributionSampling.sampleCategorical(RNG$, a[y], lengthCV$a$82_15);
		}
		if((!fixedFlag$sample81 || !fixedFlag$sample84))
			p = b[y][i];
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
		     + "public model RaggedArray2(boolean[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    double[][] b = {{0.2, 0.8}, {0.4, 0.2, 0.6}};\n"
		     + "    double[] c = { 0.35, 0.65 };\n"
		     + "    int y = categorical(c).sample();\n"
		     + "    int i = categorical(a[y]).sample();\n"
		     + "    double p = b[y][i];\n"
		     + "    boolean[] obs = bernoulli(p).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}