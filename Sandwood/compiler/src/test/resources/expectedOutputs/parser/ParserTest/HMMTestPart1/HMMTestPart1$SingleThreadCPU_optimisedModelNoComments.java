package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart1$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMTestPart1$CoreInterface {
	private double[] bias;
	private double[] cv$var28$countGlobal;
	private double[] cv$var49$stateProbabilityGlobal;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample50 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample50 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean flip;
	private boolean flipMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flip;
	private double logProbability$m;
	private double logProbability$st;
	private double logProbability$var16;
	private double logProbability$var28;
	private double logProbability$var32;
	private double logProbability$var44;
	private double logProbability$var48;
	private double logProbability$var51;
	private double[][] m;
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
		fixedProbFlag$sample45 = false;
		fixedProbFlag$sample53 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		fixedFlag$sample28 = cv$value;
		fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
		fixedProbFlag$sample50 = (cv$value && fixedProbFlag$sample50);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		fixedFlag$sample45 = cv$value;
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$fixedFlag$sample50() {
		return fixedFlag$sample50;
	}

	@Override
	public final void set$fixedFlag$sample50(boolean cv$value) {
		fixedFlag$sample50 = cv$value;
		fixedProbFlag$sample50 = (cv$value && fixedProbFlag$sample50);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$flip() {
		return flip;
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
		fixedProbFlag$sample28 = false;
		fixedProbFlag$sample50 = false;
	}

	@Override
	public final int get$st() {
		return st;
	}

	@Override
	public final void set$st(int cv$value) {
		st = cv$value;
		fixedProbFlag$sample50 = false;
		fixedProbFlag$sample53 = false;
	}

	@Override
	public final int get$states() {
		return 2;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityDirichlet(m[0], v, 2) + DistributionSampling.logProbabilityDirichlet(m[1], v, 2));
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
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityBeta(bias[0], 1.0, 1.0) + DistributionSampling.logProbabilityBeta(bias[1], 1.0, 1.0));
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

	private final void logProbabilityValue$sample50() {
		if(!fixedProbFlag$sample50) {
			double cv$distributionAccumulator = (((0.0 <= st) && (st < 2))?Math.log(m[0][st]):Double.NEGATIVE_INFINITY);
			logProbability$var48 = cv$distributionAccumulator;
			logProbability$st = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample50 = (fixedFlag$sample50 && fixedFlag$sample28);
		} else {
			logProbability$var48 = logProbability$st;
			logProbability$$model = (logProbability$$model + logProbability$st);
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + logProbability$st);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double var50 = bias[st];
			double cv$distributionAccumulator = Math.log((flip?var50:(1.0 - var50)));
			logProbability$var51 = cv$distributionAccumulator;
			logProbability$flip = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample53 = (fixedFlag$sample45 && fixedFlag$sample50);
		} else {
			logProbability$var51 = logProbability$flip;
			logProbability$$model = (logProbability$$model + logProbability$flip);
			logProbability$$evidence = (logProbability$$evidence + logProbability$flip);
		}
	}

	private final void sample28(int var27) {
		cv$var28$countGlobal[0] = 0.0;
		cv$var28$countGlobal[1] = 0.0;
		if((var27 == 0))
			cv$var28$countGlobal[st] = (cv$var28$countGlobal[st] + 1.0);
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var28$countGlobal, m[var27], 2);
	}

	private final void sample45(int var43) {
		int cv$sum = 0;
		int cv$count = 0;
		if((var43 == st)) {
			cv$count = 1;
			if(flip)
				cv$sum = 1;
		}
		bias[var43] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample50() {
		{
			st = 0;
			double cv$temp$2$var50 = bias[0];
			cv$var49$stateProbabilityGlobal[0] = (Math.log((flip?cv$temp$2$var50:(1.0 - cv$temp$2$var50))) + Math.log(m[0][0]));
		}
		st = 1;
		double cv$temp$2$var50 = bias[1];
		cv$var49$stateProbabilityGlobal[1] = (Math.log((flip?cv$temp$2$var50:(1.0 - cv$temp$2$var50))) + Math.log(m[0][1]));
		double cv$logSum;
		double cv$lseMax = cv$var49$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var49$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var49$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var49$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			cv$var49$stateProbabilityGlobal[0] = 0.5;
			cv$var49$stateProbabilityGlobal[1] = 0.5;
		} else {
			cv$var49$stateProbabilityGlobal[0] = Math.exp((cv$var49$stateProbabilityGlobal[0] - cv$logSum));
			cv$var49$stateProbabilityGlobal[1] = Math.exp((cv$var49$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var49$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var49$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st = DistributionSampling.sampleCategorical(RNG$, cv$var49$stateProbabilityGlobal, 2);
	}

	@Override
	public final void allocateScratch() {
		cv$var28$countGlobal = new double[2];
		cv$var49$stateProbabilityGlobal = new double[2];
	}

	@Override
	public final void allocator() {
		v = new double[2];
		if(!fixedFlag$sample28) {
			m = new double[2][];
			m[0] = new double[2];
			m[1] = new double[2];
		}
		if(!fixedFlag$sample45)
			bias = new double[2];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(RNG$, v, 2, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, 2, m[1]);
		}
		if(!fixedFlag$sample45) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample50)
			st = DistributionSampling.sampleCategorical(RNG$, m[0], 2);
		flip = DistributionSampling.sampleBernoulli(RNG$, bias[st]);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(RNG$, v, 2, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, 2, m[1]);
		}
		if(!fixedFlag$sample45) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample50)
			st = DistributionSampling.sampleCategorical(RNG$, m[0], 2);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(RNG$, v, 2, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, 2, m[1]);
		}
		if(!fixedFlag$sample45) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample50)
			st = DistributionSampling.sampleCategorical(RNG$, m[0], 2);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample28) {
				sample28(0);
				sample28(1);
			}
			if(!fixedFlag$sample45) {
				sample45(0);
				sample45(1);
			}
			if(!fixedFlag$sample50)
				sample50();
		} else {
			if(!fixedFlag$sample50)
				sample50();
			if(!fixedFlag$sample45) {
				sample45(1);
				sample45(0);
			}
			if(!fixedFlag$sample28) {
				sample28(1);
				sample28(0);
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
		logProbability$var16 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var28 = 0.0;
		logProbability$var32 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$var44 = 0.0;
		logProbability$var48 = 0.0;
		if(!fixedProbFlag$sample50)
			logProbability$st = 0.0;
		logProbability$var51 = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$flip = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		if(fixedFlag$sample50)
			logProbabilityValue$sample50();
		logProbabilityValue$sample53();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample50();
		logProbabilityValue$sample53();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample50();
		logProbabilityValue$sample53();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(RNG$, v, 2, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, 2, m[1]);
		}
		if(!fixedFlag$sample45) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample50)
			st = DistributionSampling.sampleCategorical(RNG$, m[0], 2);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		flip = flipMeasured;
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
		     + "model HMMTestPart1(boolean flipMeasured) {\n"
		     + "        int states = 2;\n"
		     + "\n"
		     + "        double[] v = new double[states];\n"
		     + "        for(int i:[0..states))\n"
		     + "            v[i] = 0.1;\n"
		     + "        \n"
		     + "        double[][] m = dirichlet(v).sample(states);\n"
		     + "        double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "        int st = categorical(m[0]).sample();\n"
		     + "        boolean flip = bernoulli(bias[st]).sample();\n"
		     + "\n"
		     + "        flip.observe(flipMeasured);\n"
		     + "}\n"
		     + "";
	}
}