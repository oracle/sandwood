package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DirichletBernoulli$MultiThreadCPU extends CoreModelMultiThreadCPU implements DirichletBernoulli$CoreInterface {
boolean constrainedFlag$sample17 = true;
	boolean fixedFlag$sample17 = false;
	boolean fixedProbFlag$sample17 = false;
	boolean fixedProbFlag$sample38 = false;
	boolean fixedProbFlag$sample51 = false;
	int length;
	int length$observed;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$b1;
	double logProbability$b2;
	double logProbability$output;
	double logProbability$prior;
	double logProbability$var38;
	double logProbability$var51;
	boolean[] observed;
	boolean[] output;
	double[] prior;
	boolean system$gibbsForward = true;
	double[] v;

	public DirichletBernoulli$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$fixedFlag$sample17() {
		return fixedFlag$sample17;
	}

	@Override
	public final void set$fixedFlag$sample17(boolean cv$value, boolean allocated$) {
		fixedFlag$sample17 = cv$value;
		constrainedFlag$sample17 = (cv$value || constrainedFlag$sample17);
		fixedProbFlag$sample17 = (cv$value && fixedProbFlag$sample17);
		fixedProbFlag$sample38 = (cv$value && fixedProbFlag$sample38);
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
	}

	@Override
	public final int get$length() {
		return length;
	}

	@Override
	public final int get$length$observed() {
		return length$observed;
	}

	@Override
	public final void set$length$observed(int cv$value, boolean allocated$) {
		length$observed = cv$value;
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
	public final double get$logProbability$b1() {
		return logProbability$b1;
	}

	@Override
	public final double get$logProbability$b2() {
		return logProbability$b2;
	}

	@Override
	public final double get$logProbability$output() {
		return logProbability$output;
	}

	@Override
	public final double get$logProbability$prior() {
		return logProbability$prior;
	}

	@Override
	public final boolean[] get$observed() {
		return observed;
	}

	@Override
	public final void set$observed(boolean[] cv$value, boolean allocated$) {
		observed = cv$value;
	}

	@Override
	public final boolean[] get$output() {
		return output;
	}

	@Override
	public final double[] get$prior() {
		return prior;
	}

	@Override
	public final void set$prior(double[] cv$value, boolean allocated$) {
		prior = cv$value;
		fixedProbFlag$sample17 = false;
		fixedProbFlag$sample38 = false;
		fixedProbFlag$sample51 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void drawValueSample17() {
		DistributionSampling.sampleDirichlet(RNG$, v, 2, prior);
	}

	private final void inferSample17() {
		constrainedFlag$sample17 = false;
		double cv$originalProbability;
		int cv$indexToChange = (int)(DistributionSampling.sampleUniform(RNG$) * 2.0);
		double cv$movementRatio = ((DistributionSampling.sampleBeta(RNG$, 5, 5) * 1.9999) - 1);
		double cv$proposedDifference;
		if((cv$movementRatio < 0))
			cv$proposedDifference = prior[cv$indexToChange];
		else {
			cv$proposedDifference = (1.0 - prior[cv$indexToChange]);
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1) {
				double cv$temp = prior[cv$loopIndex];
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < 2; cv$loopIndex += 1) {
				double cv$temp = prior[cv$loopIndex];
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
		}
		cv$proposedDifference = (cv$movementRatio * cv$proposedDifference);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(prior, v, 2);
			for(int i$var37 = 0; i$var37 < (length / 2); i$var37 += 1) {
				constrainedFlag$sample17 = true;
				double var19 = prior[0];
				cv$accumulatedProbabilities = ((((0.0 <= var19) && (var19 <= 1.0))?Math.log((output[i$var37]?var19:(1.0 - var19))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			for(int i$var50 = (length / 2); i$var50 < length; i$var50 += 1) {
				constrainedFlag$sample17 = true;
				double var22 = prior[1];
				cv$accumulatedProbabilities = ((((0.0 <= var22) && (var22 <= 1.0))?Math.log((output[i$var50]?var22:(1.0 - var22))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample17) {
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
				prior[cv$loopIndex] = (prior[cv$loopIndex] - cv$proposedDifference);
			prior[cv$indexToChange] = (prior[cv$indexToChange] + cv$proposedDifference);
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < 2; cv$loopIndex += 1)
				prior[cv$loopIndex] = (prior[cv$loopIndex] - cv$proposedDifference);
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(prior, v, 2);
			for(int i$var37 = 0; i$var37 < (length / 2); i$var37 += 1) {
				constrainedFlag$sample17 = true;
				double var19 = prior[0];
				cv$accumulatedProbabilities = ((((0.0 <= var19) && (var19 <= 1.0))?Math.log((output[i$var37]?var19:(1.0 - var19))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			for(int i$var50 = (length / 2); i$var50 < length; i$var50 += 1) {
				constrainedFlag$sample17 = true;
				double var22 = prior[1];
				cv$accumulatedProbabilities = ((((0.0 <= var22) && (var22 <= 1.0))?Math.log((output[i$var50]?var22:(1.0 - var22))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio))) {
				for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
					prior[cv$loopIndex] = (prior[cv$loopIndex] + cv$proposedDifference);
				prior[cv$indexToChange] = (prior[cv$indexToChange] - cv$proposedDifference);
				for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < 2; cv$loopIndex += 1)
					prior[cv$loopIndex] = (prior[cv$loopIndex] + cv$proposedDifference);
			}
		}
	}

	private final void logProbabilityValue$sample17() {
		if(!fixedProbFlag$sample17) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(prior, v, 2);
			logProbability$prior = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample17 = fixedFlag$sample17;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$prior);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + logProbability$prior);
		}
	}

	private final void logProbabilityValue$sample38() {
		if(!fixedProbFlag$sample38) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var37 = 0; i$var37 < (length / 2); i$var37 += 1) {
				double var19 = prior[0];
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var19) && (var19 <= 1.0))?Math.log((output[i$var37]?var19:(1.0 - var19))):Double.NEGATIVE_INFINITY));
			}
			logProbability$b1 = cv$sampleAccumulator;
			logProbability$var38 = cv$sampleAccumulator;
			logProbability$output = (logProbability$output + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample38 = fixedFlag$sample17;
		} else {
			logProbability$b1 = logProbability$var38;
			logProbability$output = (logProbability$output + logProbability$var38);
			logProbability$$model = (logProbability$$model + logProbability$var38);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var38);
		}
	}

	private final void logProbabilityValue$sample51() {
		if(!fixedProbFlag$sample51) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var50 = (length / 2); i$var50 < length; i$var50 += 1) {
				double var22 = prior[1];
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var22) && (var22 <= 1.0))?Math.log((output[i$var50]?var22:(1.0 - var22))):Double.NEGATIVE_INFINITY));
			}
			logProbability$b2 = cv$sampleAccumulator;
			logProbability$var51 = cv$sampleAccumulator;
			logProbability$output = (logProbability$output + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample51 = fixedFlag$sample17;
		} else {
			logProbability$b2 = logProbability$var51;
			logProbability$output = (logProbability$output + logProbability$var51);
			logProbability$$model = (logProbability$$model + logProbability$var51);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var51);
		}
	}

	@Override
	public final void allocate() {
		v = new double[2];
		if(!fixedFlag$sample17)
			prior = new double[2];
		output = new boolean[length$observed];
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, v, 2, prior);
		parallelFor(RNG$, 0, (length / 2), 1,
			(int forStart$i$var37, int forEnd$i$var37, int threadID$i$var37, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var37 = forStart$i$var37; i$var37 < forEnd$i$var37; i$var37 += 1)
						output[i$var37] = DistributionSampling.sampleBernoulli(RNG$1, prior[0]);
			}
		);
		parallelFor(RNG$, (length / 2), length, 1,
			(int forStart$i$var50, int forEnd$i$var50, int threadID$i$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var50 = forStart$i$var50; i$var50 < forEnd$i$var50; i$var50 += 1)
						output[i$var50] = DistributionSampling.sampleBernoulli(RNG$1, prior[1]);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, v, 2, prior);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, v, 2, prior);
		parallelFor(RNG$, 0, (length / 2), 1,
			(int forStart$i$var37, int forEnd$i$var37, int threadID$i$var37, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var37 = forStart$i$var37; i$var37 < forEnd$i$var37; i$var37 += 1)
						output[i$var37] = DistributionSampling.sampleBernoulli(RNG$1, prior[0]);
			}
		);
		parallelFor(RNG$, (length / 2), length, 1,
			(int forStart$i$var50, int forEnd$i$var50, int threadID$i$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var50 = forStart$i$var50; i$var50 < forEnd$i$var50; i$var50 += 1)
						output[i$var50] = DistributionSampling.sampleBernoulli(RNG$1, prior[1]);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, v, 2, prior);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, v, 2, prior);
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample17)
			inferSample17();
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample17)
			drawValueSample17();
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$prior = Double.NaN;
		logProbability$b1 = Double.NaN;
		logProbability$output = 0.0;
		if(!fixedProbFlag$sample38)
			logProbability$var38 = Double.NaN;
		logProbability$b2 = Double.NaN;
		if(!fixedProbFlag$sample51)
			logProbability$var51 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		v[0] = 0.1;
		v[1] = 0.1;
		length = length$observed;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample17)
			logProbabilityValue$sample17();
		logProbabilityValue$sample38();
		logProbabilityValue$sample51();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample38();
		logProbabilityValue$sample51();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample38();
		logProbabilityValue$sample51();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = output.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			output[cv$index1] = observed[cv$index1];
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
		     + "public model DirichletBernoulli(boolean[] observed) {\n"
		     + "    double[] v = new double[2] <~ 0.1;\n"
		     + "    double[] prior = dirichlet(v).sample();\n"
		     + "    Bernoulli b1 = new Bernoulli(prior[0]);\n"
		     + "    Bernoulli b2 = new Bernoulli(prior[1]);\n"
		     + "    int length = observed.length;\n"
		     + "    boolean[] output = new boolean[length];\n"
		     + "    for(int i=0; i<length/2; i++)\n"
		     + "        output[i] = b1.sample();\n"
		     + "    for(int i=length/2; i<length; i++)\n"
		     + "        output[i] = b2.sample();\n"
		     + "    output.observe(observed);\n"
		     + "}\n"
		     + "";
	}
}