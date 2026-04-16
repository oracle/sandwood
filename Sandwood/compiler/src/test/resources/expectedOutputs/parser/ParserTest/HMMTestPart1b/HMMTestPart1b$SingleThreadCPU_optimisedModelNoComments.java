package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMTestPart1b$SingleThreadCPU extends CoreModelSingleThreadCPU implements HMMTestPart1b$CoreInterface {
double[] bias;
	boolean[] constrainedFlag$sample28;
	boolean[] constrainedFlag$sample45;
	boolean constrainedFlag$sample50 = true;
	boolean fixedFlag$sample28 = false;
	boolean fixedFlag$sample45 = false;
	boolean fixedFlag$sample50 = false;
	boolean fixedProbFlag$sample28 = false;
	boolean fixedProbFlag$sample45 = false;
	boolean fixedProbFlag$sample50 = false;
	boolean fixedProbFlag$sample53 = false;
	boolean flip;
	boolean flipMeasured;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$bias;
	double logProbability$flip;
	double logProbability$m;
	double logProbability$st;
	double logProbability$var28;
	double logProbability$var44;
	double[][] m;
	int st;
	int states;
	boolean system$gibbsForward = true;
	double[] v;
	double[] cv$var28$countGlobal;
	double[] cv$var49$stateProbabilityGlobal;

	public HMMTestPart1b$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value, boolean allocated$) {
		bias = cv$value;
		fixedProbFlag$sample45 = false;
		fixedProbFlag$sample53 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value, boolean allocated$) {
		fixedFlag$sample28 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
				constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
		}
		fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
		fixedProbFlag$sample50 = (cv$value && fixedProbFlag$sample50);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value, boolean allocated$) {
		fixedFlag$sample45 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
				constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
		}
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$fixedFlag$sample50() {
		return fixedFlag$sample50;
	}

	@Override
	public final void set$fixedFlag$sample50(boolean cv$value, boolean allocated$) {
		fixedFlag$sample50 = cv$value;
		constrainedFlag$sample50 = (cv$value || constrainedFlag$sample50);
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
	public final void set$flipMeasured(boolean cv$value, boolean allocated$) {
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
	public final void set$m(double[][] cv$value, boolean allocated$) {
		m = cv$value;
		fixedProbFlag$sample28 = false;
		fixedProbFlag$sample50 = false;
	}

	@Override
	public final int get$st() {
		return st;
	}

	@Override
	public final void set$st(int cv$value, boolean allocated$) {
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

	private final void drawValueSample28(int var27) {
		DistributionSampling.sampleDirichlet(RNG$, v, 2, m[var27]);
	}

	private final void drawValueSample45(int var43) {
		bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	private final void drawValueSample50() {
		st = DistributionSampling.sampleCategorical(RNG$, m[0], 2);
	}

	private final void inferSample28(int var27) {
		constrainedFlag$sample28[var27] = false;
		cv$var28$countGlobal[0] = 0.0;
		cv$var28$countGlobal[1] = 0.0;
		if(((var27 == 0) && (fixedFlag$sample50 || constrainedFlag$sample50))) {
			constrainedFlag$sample28[0] = true;
			cv$var28$countGlobal[st] = (cv$var28$countGlobal[st] + 1.0);
		}
		if(constrainedFlag$sample28[var27])
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var28$countGlobal, m[var27], 2);
	}

	private final void inferSample45(int var43) {
		constrainedFlag$sample45[var43] = false;
		int cv$sum = 0;
		int cv$count = 0;
		if((var43 == st)) {
			constrainedFlag$sample45[var43] = true;
			cv$count = 1;
			if(flip)
				cv$sum = 1;
		}
		if(constrainedFlag$sample45[var43])
			bias[var43] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void inferSample50() {
		constrainedFlag$sample50 = false;
		{
			st = 0;
			double[] var47 = m[0];
			constrainedFlag$sample50 = true;
			double var50 = bias[0];
			cv$var49$stateProbabilityGlobal[0] = ((((0.0 <= var50) && (var50 <= 1.0))?Math.log((flip?var50:(1.0 - var50))):Double.NEGATIVE_INFINITY) + (((0.0 <= var47[0]) && (var47[0] <= 1.0))?Math.log(var47[0]):Double.NEGATIVE_INFINITY));
		}
		st = 1;
		double[] var47 = m[0];
		constrainedFlag$sample50 = true;
		double var50 = bias[1];
		cv$var49$stateProbabilityGlobal[1] = ((((0.0 <= var50) && (var50 <= 1.0))?Math.log((flip?var50:(1.0 - var50))):Double.NEGATIVE_INFINITY) + (((0.0 <= var47[1]) && (var47[1] <= 1.0))?Math.log(var47[1]):Double.NEGATIVE_INFINITY));
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

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityDirichlet(m[0], v, 2) + DistributionSampling.logProbabilityDirichlet(m[1], v, 2));
			logProbability$var28 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			logProbability$m = (logProbability$m + logProbability$var28);
			logProbability$$model = (logProbability$$model + logProbability$var28);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var28);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!fixedProbFlag$sample45) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityBeta(bias[0], 1.0, 1.0) + DistributionSampling.logProbabilityBeta(bias[1], 1.0, 1.0));
			logProbability$var44 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample45 = fixedFlag$sample45;
		} else {
			logProbability$bias = (logProbability$bias + logProbability$var44);
			logProbability$$model = (logProbability$$model + logProbability$var44);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var44);
		}
	}

	private final void logProbabilityValue$sample50() {
		if(!fixedProbFlag$sample50) {
			double[] var47 = m[0];
			double cv$distributionAccumulator = (((((0.0 <= st) && (st < 2)) && (0.0 <= var47[st])) && (var47[st] <= 1.0))?Math.log(var47[st]):Double.NEGATIVE_INFINITY);
			logProbability$st = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample50 = (fixedFlag$sample50 && fixedFlag$sample28);
		} else {
			logProbability$$model = (logProbability$$model + logProbability$st);
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + logProbability$st);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double var50 = bias[st];
			double cv$distributionAccumulator = (((0.0 <= var50) && (var50 <= 1.0))?Math.log((flip?var50:(1.0 - var50))):Double.NEGATIVE_INFINITY);
			logProbability$flip = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample53 = (fixedFlag$sample45 && fixedFlag$sample50);
		} else {
			logProbability$$model = (logProbability$$model + logProbability$flip);
			logProbability$$evidence = (logProbability$$evidence + logProbability$flip);
		}
	}

	@Override
	public final void allocate() {
		v = new double[2];
		if(!fixedFlag$sample28) {
			m = new double[2][];
			m[0] = new double[2];
			m[1] = new double[2];
		}
		if(!fixedFlag$sample45)
			bias = new double[2];
		constrainedFlag$sample45 = new boolean[2];
		constrainedFlag$sample28 = new boolean[2];
		allocateScratch();
	}

	@Override
	public final void allocateScratch() {
		cv$var28$countGlobal = new double[2];
		cv$var49$stateProbabilityGlobal = new double[2];
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
	public final void forwardGenerationDistributionsNoOutputsPrime() {
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
	public final void forwardGenerationPrime() {
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
	public final void forwardGenerationValuesNoOutputsPrime() {
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
				inferSample28(0);
				inferSample28(1);
			}
			if(!fixedFlag$sample45) {
				inferSample45(0);
				inferSample45(1);
			}
			if(!fixedFlag$sample50)
				inferSample50();
		} else {
			if(!fixedFlag$sample50)
				inferSample50();
			if(!fixedFlag$sample45) {
				inferSample45(1);
				inferSample45(0);
			}
			if(!fixedFlag$sample28) {
				inferSample28(1);
				inferSample28(0);
			}
		}
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample28[0])
			drawValueSample28(0);
		if(!constrainedFlag$sample28[1])
			drawValueSample28(1);
		if(!constrainedFlag$sample45[0])
			drawValueSample45(0);
		if(!constrainedFlag$sample45[1])
			drawValueSample45(1);
		if(!constrainedFlag$sample50)
			drawValueSample50();
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var28 = Double.NaN;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$var44 = Double.NaN;
		if(!fixedProbFlag$sample50)
			logProbability$st = Double.NaN;
		if(!fixedProbFlag$sample53)
			logProbability$flip = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		v[0] = 0.1;
		v[1] = 0.1;
		for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
			constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
		for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
			constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
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
		     + "model HMMTestPart1b(boolean flipMeasured) {\n"
		     + "        int states = 2;\n"
		     + "\n"
		     + "        double[] v = new double[states] <~ 0.1;\n"
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