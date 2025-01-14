package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart1$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMTestPart1$CoreInterface {
	private double[] bias;
	private double[] cv$var14$countGlobal;
	private double[] cv$var28$stateProbabilityGlobal;
	private boolean fixedFlag$sample14 = false;
	private boolean fixedFlag$sample24 = false;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedProbFlag$sample14 = false;
	private boolean fixedProbFlag$sample24 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean flip;
	private boolean flipMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flip;
	private double logProbability$m;
	private double logProbability$st;
	private double logProbability$var14;
	private double logProbability$var18;
	private double logProbability$var23;
	private double logProbability$var27;
	private double logProbability$var30;
	private double logProbability$var9;
	private double[][] m;
	private boolean setFlag$bias = false;
	private boolean setFlag$m = false;
	private int st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart1$SingleThreadCPU(ExecutionTarget target) {
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
	}

	@Override
	public final boolean get$fixedFlag$sample14() {
		return fixedFlag$sample14;
	}

	@Override
	public final void set$fixedFlag$sample14(boolean cv$value) {
		fixedFlag$sample14 = cv$value;
		fixedProbFlag$sample14 = (cv$value && fixedProbFlag$sample14);
		fixedProbFlag$sample29 = (cv$value && fixedProbFlag$sample29);
	}

	@Override
	public final boolean get$fixedFlag$sample24() {
		return fixedFlag$sample24;
	}

	@Override
	public final void set$fixedFlag$sample24(boolean cv$value) {
		fixedFlag$sample24 = cv$value;
		fixedProbFlag$sample24 = (cv$value && fixedProbFlag$sample24);
		fixedProbFlag$sample32 = (cv$value && fixedProbFlag$sample32);
	}

	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	@Override
	public final void set$fixedFlag$sample29(boolean cv$value) {
		fixedFlag$sample29 = cv$value;
		fixedProbFlag$sample29 = (cv$value && fixedProbFlag$sample29);
		fixedProbFlag$sample32 = (cv$value && fixedProbFlag$sample32);
	}

	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	@Override
	public final void set$fixedFlag$sample32(boolean cv$value) {
		fixedFlag$sample32 = cv$value;
		fixedProbFlag$sample32 = (cv$value && fixedProbFlag$sample32);
	}

	@Override
	public final boolean get$flip() {
		return flip;
	}

	@Override
	public final void set$flip(boolean cv$value) {
		flip = cv$value;
	}

	@Override
	public final boolean get$flipMeasured() {
		return flipMeasured;
	}

	@Override
	public final void set$flipMeasured(boolean cv$value) {
		flipMeasured = cv$value;
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
	public final double get$logProbability$flip() {
		return logProbability$flip;
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
	}

	@Override
	public final int get$st() {
		return st;
	}

	@Override
	public final void set$st(int cv$value) {
		st = cv$value;
	}

	@Override
	public final int get$states() {
		return 2;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample14() {
		if(!fixedProbFlag$sample14) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityDirichlet(m[0], v) + DistributionSampling.logProbabilityDirichlet(m[1], v));
			logProbability$var9 = cv$sampleAccumulator;
			logProbability$var14 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample14)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample14 = fixedFlag$sample14;
		} else {
			logProbability$var9 = logProbability$var14;
			logProbability$m = (logProbability$m + logProbability$var14);
			logProbability$$model = (logProbability$$model + logProbability$var14);
			if(fixedFlag$sample14)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var14);
		}
	}

	private final void logProbabilityValue$sample24() {
		if(!fixedProbFlag$sample24) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityBeta(bias[0], 1.0, 1.0) + DistributionSampling.logProbabilityBeta(bias[1], 1.0, 1.0));
			logProbability$var18 = cv$sampleAccumulator;
			logProbability$var23 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample24 = fixedFlag$sample24;
		} else {
			logProbability$var18 = logProbability$var23;
			logProbability$bias = (logProbability$bias + logProbability$var23);
			logProbability$$model = (logProbability$$model + logProbability$var23);
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var23);
		}
	}

	private final void logProbabilityValue$sample29() {
		if(!fixedProbFlag$sample29) {
			double[] var26 = m[0];
			double cv$distributionAccumulator = (((0.0 <= st) && (st < var26.length))?Math.log(var26[st]):Double.NEGATIVE_INFINITY);
			logProbability$var27 = cv$distributionAccumulator;
			logProbability$st = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample29 = (fixedFlag$sample29 && fixedFlag$sample14);
		} else {
			logProbability$var27 = logProbability$st;
			logProbability$$model = (logProbability$$model + logProbability$st);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + logProbability$st);
		}
	}

	private final void logProbabilityValue$sample32() {
		if(!fixedProbFlag$sample32) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flip, bias[st]);
			logProbability$var30 = cv$distributionAccumulator;
			logProbability$flip = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample32 = ((fixedFlag$sample32 && fixedFlag$sample24) && fixedFlag$sample29);
		} else {
			logProbability$var30 = logProbability$flip;
			logProbability$$model = (logProbability$$model + logProbability$flip);
			logProbability$$evidence = (logProbability$$evidence + logProbability$flip);
		}
	}

	private final void sample14(int var13) {
		cv$var14$countGlobal[0] = 0.0;
		cv$var14$countGlobal[1] = 0.0;
		if((var13 == 0))
			cv$var14$countGlobal[st] = (cv$var14$countGlobal[st] + 1.0);
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var14$countGlobal, m[var13]);
	}

	private final void sample24(int var22) {
		int cv$sum = 0;
		int cv$count = 0;
		if((var22 == st)) {
			cv$count = 1;
			if(flip)
				cv$sum = 1;
		}
		bias[var22] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample29() {
		{
			st = 0;
			double[] cv$temp$0$var26 = m[0];
			cv$var28$stateProbabilityGlobal[0] = (DistributionSampling.logProbabilityBernoulli(flip, bias[0]) + ((0 < cv$temp$0$var26.length)?Math.log(cv$temp$0$var26[0]):Double.NEGATIVE_INFINITY));
		}
		st = 1;
		double[] cv$temp$0$var26 = m[0];
		cv$var28$stateProbabilityGlobal[1] = (DistributionSampling.logProbabilityBernoulli(flip, bias[1]) + ((1 < cv$temp$0$var26.length)?Math.log(cv$temp$0$var26[1]):Double.NEGATIVE_INFINITY));
		double cv$logSum;
		double cv$lseMax = cv$var28$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var28$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var28$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var28$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var28$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var28$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var28$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$var28$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var28$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var28$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var28$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		st = DistributionSampling.sampleCategorical(RNG$, cv$var28$stateProbabilityGlobal);
	}

	@Override
	public final void allocateScratch() {
		cv$var14$countGlobal = new double[2];
		cv$var28$stateProbabilityGlobal = new double[2];
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
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample14) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		if(!fixedFlag$sample24) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample29)
			st = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample32)
			flip = DistributionSampling.sampleBernoulli(RNG$, bias[st]);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample14) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		if(!fixedFlag$sample24) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample29)
			st = DistributionSampling.sampleCategorical(RNG$, m[0]);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample14) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		if(!fixedFlag$sample24) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample29)
			st = DistributionSampling.sampleCategorical(RNG$, m[0]);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample14) {
				sample14(0);
				sample14(1);
			}
			if(!fixedFlag$sample24) {
				sample24(0);
				sample24(1);
			}
			if(!fixedFlag$sample29)
				sample29();
		} else {
			if(!fixedFlag$sample29)
				sample29();
			if(!fixedFlag$sample24) {
				sample24(1);
				sample24(0);
			}
			if(!fixedFlag$sample14) {
				sample14(1);
				sample14(0);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		v[0] = 0.1;
		v[1] = 0.1;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var9 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample14)
			logProbability$var14 = 0.0;
		logProbability$var18 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample24)
			logProbability$var23 = 0.0;
		logProbability$var27 = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$st = 0.0;
		logProbability$var30 = 0.0;
		if(!fixedProbFlag$sample32)
			logProbability$flip = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample14)
			logProbabilityValue$sample14();
		if(fixedFlag$sample24)
			logProbabilityValue$sample24();
		if(fixedFlag$sample29)
			logProbabilityValue$sample29();
		logProbabilityValue$sample32();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample14();
		logProbabilityValue$sample24();
		logProbabilityValue$sample29();
		logProbabilityValue$sample32();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample14();
		logProbabilityValue$sample24();
		logProbabilityValue$sample29();
		logProbabilityValue$sample32();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample14) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		if(!fixedFlag$sample24) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample29)
			st = DistributionSampling.sampleCategorical(RNG$, m[0]);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		flip = flipMeasured;
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart1(boolean flipMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n        \n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int st = categorical(m[0]).sample();\n        boolean flip = bernoulli(bias[st]).sample();\n\n        flip.observe(flipMeasured);\n}\n";
	}
}