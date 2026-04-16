package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class RaggedArray5$MultiThreadCPU extends CoreModelMultiThreadCPU implements RaggedArray5$CoreInterface {
double[][] a;
	boolean constrainedFlag$sample39 = true;
	double[] d;
	boolean fixedFlag$sample39 = false;
	boolean fixedProbFlag$sample39 = false;
	boolean fixedProbFlag$sample54 = false;
	int length$obs_measured;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$d;
	double logProbability$obs;
	double logProbability$var52;
	boolean[] obs;
	boolean[] obs_measured;
	boolean system$gibbsForward = true;
	int y;

	public RaggedArray5$MultiThreadCPU(ExecutionTarget target) {
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
	public final void set$d(double[] cv$value, boolean allocated$) {
		d = cv$value;
		fixedProbFlag$sample39 = false;
		fixedProbFlag$sample54 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample39() {
		return fixedFlag$sample39;
	}

	@Override
	public final void set$fixedFlag$sample39(boolean cv$value, boolean allocated$) {
		fixedFlag$sample39 = cv$value;
		constrainedFlag$sample39 = (cv$value || constrainedFlag$sample39);
		fixedProbFlag$sample39 = (cv$value && fixedProbFlag$sample39);
		fixedProbFlag$sample54 = (cv$value && fixedProbFlag$sample54);
	}

	@Override
	public final int get$length$obs_measured() {
		return length$obs_measured;
	}

	@Override
	public final void set$length$obs_measured(int cv$value, boolean allocated$) {
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
	public final boolean[] get$obs() {
		return obs;
	}

	@Override
	public final boolean[] get$obs_measured() {
		return obs_measured;
	}

	@Override
	public final void set$obs_measured(boolean[] cv$value, boolean allocated$) {
		obs_measured = cv$value;
	}

	@Override
	public final int get$y() {
		return y;
	}

	@Override
	public final void set$y(int cv$value, boolean allocated$) {
		y = cv$value;
	}

	private final void drawValueSample39() {
		int lengthCV$a$37_13 = -1;
		if((0 == y))
			lengthCV$a$37_13 = 2;
		if((1 == y))
			lengthCV$a$37_13 = 3;
		DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_13, d);
	}

	private final void inferSample39() {
		constrainedFlag$sample39 = false;
		double cv$originalProbability;
		int lengthCV$a$37_11 = -1;
		if((0 == y))
			lengthCV$a$37_11 = 2;
		if((1 == y))
			lengthCV$a$37_11 = 3;
		int cv$indexToChange = (int)((double)lengthCV$a$37_11 * DistributionSampling.sampleUniform(RNG$));
		double cv$movementRatio = ((DistributionSampling.sampleBeta(RNG$, 5, 5) * 1.9999) - 1);
		double cv$proposedDifference;
		if((cv$movementRatio < 0))
			cv$proposedDifference = d[cv$indexToChange];
		else {
			cv$proposedDifference = (1.0 - d[cv$indexToChange]);
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1) {
				double cv$temp = (d[cv$loopIndex] * (lengthCV$a$37_11 - 1));
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < lengthCV$a$37_11; cv$loopIndex += 1) {
				double cv$temp = (d[cv$loopIndex] * (lengthCV$a$37_11 - 1));
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
		}
		cv$proposedDifference = (cv$movementRatio * cv$proposedDifference);
		double cv$rebalanceValue = (cv$proposedDifference / (lengthCV$a$37_11 - 1));
		{
			int lengthCV$a$37_12 = -1;
			if((0 == y))
				lengthCV$a$37_12 = 2;
			if((1 == y))
				lengthCV$a$37_12 = 3;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(d, a[y], lengthCV$a$37_12);
			for(int var51 = 0; var51 < length$obs_measured; var51 += 1) {
				constrainedFlag$sample39 = true;
				double var38 = d[y];
				cv$accumulatedProbabilities = ((((0.0 <= var38) && (var38 <= 1.0))?Math.log((obs[var51]?var38:(1.0 - var38))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample39) {
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
				d[cv$loopIndex] = (d[cv$loopIndex] - cv$rebalanceValue);
			d[cv$indexToChange] = (d[cv$indexToChange] + cv$proposedDifference);
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < lengthCV$a$37_11; cv$loopIndex += 1)
				d[cv$loopIndex] = (d[cv$loopIndex] - cv$rebalanceValue);
			int lengthCV$a$37_12 = -1;
			if((0 == y))
				lengthCV$a$37_12 = 2;
			if((1 == y))
				lengthCV$a$37_12 = 3;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(d, a[y], lengthCV$a$37_12);
			for(int var51 = 0; var51 < length$obs_measured; var51 += 1) {
				constrainedFlag$sample39 = true;
				double var38 = d[y];
				cv$accumulatedProbabilities = ((((0.0 <= var38) && (var38 <= 1.0))?Math.log((obs[var51]?var38:(1.0 - var38))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio))) {
				for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
					d[cv$loopIndex] = (d[cv$loopIndex] + cv$rebalanceValue);
				d[cv$indexToChange] = (d[cv$indexToChange] - cv$proposedDifference);
				for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < lengthCV$a$37_11; cv$loopIndex += 1)
					d[cv$loopIndex] = (d[cv$loopIndex] + cv$rebalanceValue);
			}
		}
	}

	private final void logProbabilityValue$sample39() {
		if(!fixedProbFlag$sample39) {
			int lengthCV$a$37_14 = -1;
			if((0 == y))
				lengthCV$a$37_14 = 2;
			if((1 == y))
				lengthCV$a$37_14 = 3;
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(d, a[y], lengthCV$a$37_14);
			logProbability$d = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample39 = fixedFlag$sample39;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$d);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + logProbability$d);
		}
	}

	private final void logProbabilityValue$sample54() {
		if(!fixedProbFlag$sample54) {
			double cv$sampleAccumulator = 0.0;
			for(int var51 = 0; var51 < length$obs_measured; var51 += 1) {
				double var38 = d[y];
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var38) && (var38 <= 1.0))?Math.log((obs[var51]?var38:(1.0 - var38))):Double.NEGATIVE_INFINITY));
			}
			logProbability$var52 = cv$sampleAccumulator;
			logProbability$obs = (logProbability$obs + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample54 = fixedFlag$sample39;
		} else {
			logProbability$obs = (logProbability$obs + logProbability$var52);
			logProbability$$model = (logProbability$$model + logProbability$var52);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var52);
		}
	}

	@Override
	public final void allocate() {
		a = new double[2][];
		a[0] = new double[2];
		a[1] = new double[3];
		if(!fixedFlag$sample39) {
			int lengthCV$a$37_10 = -1;
			if((0 == y))
				lengthCV$a$37_10 = 2;
			if((1 == y))
				lengthCV$a$37_10 = 3;
			d = new double[lengthCV$a$37_10];
		}
		obs = new boolean[length$obs_measured];
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample39) {
			int lengthCV$a$37_15 = -1;
			if((0 == y))
				lengthCV$a$37_15 = 2;
			if((1 == y))
				lengthCV$a$37_15 = 3;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_15, d);
		}
		parallelFor(RNG$, 0, length$obs_measured, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1)
						obs[var51] = DistributionSampling.sampleBernoulli(RNG$1, d[y]);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample39) {
			int lengthCV$a$37_19 = -1;
			if((0 == y))
				lengthCV$a$37_19 = 2;
			if((1 == y))
				lengthCV$a$37_19 = 3;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_19, d);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample39) {
			int lengthCV$a$37_16 = -1;
			if((0 == y))
				lengthCV$a$37_16 = 2;
			if((1 == y))
				lengthCV$a$37_16 = 3;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_16, d);
		}
		parallelFor(RNG$, 0, length$obs_measured, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1)
						obs[var51] = DistributionSampling.sampleBernoulli(RNG$1, d[y]);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample39) {
			int lengthCV$a$37_17 = -1;
			if((0 == y))
				lengthCV$a$37_17 = 2;
			if((1 == y))
				lengthCV$a$37_17 = 3;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_17, d);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample39) {
			int lengthCV$a$37_18 = -1;
			if((0 == y))
				lengthCV$a$37_18 = 2;
			if((1 == y))
				lengthCV$a$37_18 = 3;
			DistributionSampling.sampleDirichlet(RNG$, a[y], lengthCV$a$37_18, d);
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample39)
			inferSample39();
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample39)
			drawValueSample39();
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		if(!fixedProbFlag$sample39)
			logProbability$d = Double.NaN;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample54)
			logProbability$var52 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		double[] var6 = a[0];
		var6[0] = 0.4;
		var6[1] = 0.6;
		double[] var19 = a[1];
		var19[0] = 0.2;
		var19[1] = 0.3;
		var19[2] = 0.5;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample39)
			logProbabilityValue$sample39();
		logProbabilityValue$sample54();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample39();
		logProbabilityValue$sample54();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample39();
		logProbabilityValue$sample54();
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
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model RaggedArray5(int y, boolean[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    \n"
		     + "    double[] d = dirichlet(a[y]).sample();\n"
		     + "    boolean[] obs = bernoulli(d[y]).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}