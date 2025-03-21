package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray3$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements RaggedArray3$CoreInterface {
	private double[][] a;
	private double[] cv$var37$countGlobal;
	private double[] d;
	private boolean fixedFlag$sample39 = false;
	private boolean fixedProbFlag$sample39 = false;
	private boolean fixedProbFlag$sample53 = false;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$d;
	private double logProbability$obs;
	private double logProbability$var36;
	private double logProbability$var38;
	private double logProbability$var51;
	private int[] obs;
	private int[] obs_measured;
	private boolean setFlag$d = false;
	private boolean setFlag$obs = false;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray3$SingleThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample39 = false;
		fixedProbFlag$sample53 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample39() {
		return fixedFlag$sample39;
	}

	@Override
	public final void set$fixedFlag$sample39(boolean cv$value) {
		fixedFlag$sample39 = cv$value;
		fixedProbFlag$sample39 = (cv$value && fixedProbFlag$sample39);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
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

	private final void logProbabilityValue$sample39() {
		if(!fixedProbFlag$sample39) {
			int lengthCV$a$37_3 = -1;
			if((0 == y))
				lengthCV$a$37_3 = 2;
			if((1 == y))
				lengthCV$a$37_3 = 3;
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(d, a[y], lengthCV$a$37_3);
			logProbability$var36 = cv$distributionAccumulator;
			logProbability$d = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample39 = fixedFlag$sample39;
		} else {
			logProbability$var36 = logProbability$d;
			logProbability$$model = (logProbability$$model + logProbability$d);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + logProbability$d);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$sampleAccumulator = 0.0;
			for(int var50 = 0; var50 < length$obs_measured; var50 += 1) {
				int cv$sampleValue = obs[var50];
				int lengthCV$a$37_4 = -1;
				if((0 == y))
					lengthCV$a$37_4 = 2;
				if((1 == y))
					lengthCV$a$37_4 = 3;
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$a$37_4))?Math.log(d[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			logProbability$var38 = cv$sampleAccumulator;
			logProbability$var51 = cv$sampleAccumulator;
			logProbability$obs = (logProbability$obs + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample53 = fixedFlag$sample39;
		} else {
			logProbability$var38 = logProbability$var51;
			logProbability$obs = (logProbability$obs + logProbability$var51);
			logProbability$$model = (logProbability$$model + logProbability$var51);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var51);
		}
	}

	private final void sample39() {
		int lengthCV$a$37_1 = -1;
		if((0 == y))
			lengthCV$a$37_1 = 2;
		if((1 == y))
			lengthCV$a$37_1 = 3;
		for(int cv$loopIndex = 0; cv$loopIndex < lengthCV$a$37_1; cv$loopIndex += 1)
			cv$var37$countGlobal[cv$loopIndex] = 0.0;
		for(int var50 = 0; var50 < length$obs_measured; var50 += 1)
			cv$var37$countGlobal[obs[var50]] = (cv$var37$countGlobal[obs[var50]] + 1.0);
		int lengthCV$a$37_2 = -1;
		if((0 == y))
			lengthCV$a$37_2 = 2;
		if((1 == y))
			lengthCV$a$37_2 = 3;
		Conjugates.sampleConjugateDirichletCategorical(RNG$, a[y], cv$var37$countGlobal, d, lengthCV$a$37_2);
	}

	@Override
	public final void allocateScratch() {
		cv$var37$countGlobal = new double[3];
	}

	@Override
	public final void allocator() {
		a = new double[2][];
		a[0] = new double[2];
		a[1] = new double[3];
		if(!setFlag$d) {
			int lengthCV$a$37_0 = -1;
			if((0 == y))
				lengthCV$a$37_0 = 2;
			if((1 == y))
				lengthCV$a$37_0 = 3;
			d = new double[lengthCV$a$37_0];
		}
		obs = new int[length$obs_measured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample39) {
			int lengthCV$a$37_5 = -1;
			if((0 == y))
				lengthCV$a$37_5 = 2;
			if((1 == y))
				lengthCV$a$37_5 = 3;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_5, d);
		}
		int lengthCV$a$37_6 = -1;
		if((0 == y))
			lengthCV$a$37_6 = 2;
		if((1 == y))
			lengthCV$a$37_6 = 3;
		for(int var50 = 0; var50 < length$obs_measured; var50 += 1)
			obs[var50] = DistributionSampling.sampleCategorical(RNG$, d, lengthCV$a$37_6);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample39) {
			int lengthCV$a$37_8 = -1;
			if((0 == y))
				lengthCV$a$37_8 = 2;
			if((1 == y))
				lengthCV$a$37_8 = 3;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_8, d);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample39) {
			int lengthCV$a$37_7 = -1;
			if((0 == y))
				lengthCV$a$37_7 = 2;
			if((1 == y))
				lengthCV$a$37_7 = 3;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_7, d);
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample39)
			sample39();
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
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var36 = 0.0;
		if(!fixedProbFlag$sample39)
			logProbability$d = 0.0;
		logProbability$var38 = 0.0;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$var51 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample39)
			logProbabilityValue$sample39();
		logProbabilityValue$sample53();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample39();
		logProbabilityValue$sample53();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample39();
		logProbabilityValue$sample53();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample39) {
			int lengthCV$a$37_9 = -1;
			if((0 == y))
				lengthCV$a$37_9 = 2;
			if((1 == y))
				lengthCV$a$37_9 = 3;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_9, d);
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
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model RaggedArray3(int y, int[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    \n"
		     + "    double[] d = dirichlet(a[y]).sample();\n"
		     + "    int[] obs = categorical(d).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}