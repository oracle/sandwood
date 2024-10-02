package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DirichletBernoulli$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements DirichletBernoulli$CoreInterface {
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample42 = false;
	private boolean fixedFlag$sample55 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample42 = false;
	private boolean fixedProbFlag$sample55 = false;
	private int length;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b1;
	private double logProbability$b2;
	private double logProbability$output;
	private double logProbability$prior;
	private double logProbability$var18;
	private double logProbability$var40;
	private double logProbability$var53;
	private boolean[] observed;
	private boolean[] output;
	private double[] prior;
	private boolean setFlag$output = false;
	private boolean setFlag$prior = false;
	private boolean system$gibbsForward = true;
	private double[] v;

	public DirichletBernoulli$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	@Override
	public final void set$fixedFlag$sample20(boolean cv$value) {
		fixedFlag$sample20 = cv$value;
		fixedProbFlag$sample20 = (cv$value && fixedProbFlag$sample20);
		fixedProbFlag$sample42 = (cv$value && fixedProbFlag$sample42);
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
	}

	@Override
	public final boolean get$fixedFlag$sample42() {
		return fixedFlag$sample42;
	}

	@Override
	public final void set$fixedFlag$sample42(boolean cv$value) {
		fixedFlag$sample42 = cv$value;
		fixedProbFlag$sample42 = (cv$value && fixedProbFlag$sample42);
	}

	@Override
	public final boolean get$fixedFlag$sample55() {
		return fixedFlag$sample55;
	}

	@Override
	public final void set$fixedFlag$sample55(boolean cv$value) {
		fixedFlag$sample55 = cv$value;
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
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
	public final void set$length$observed(int cv$value) {
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
	public final void set$observed(boolean[] cv$value) {
		observed = cv$value;
	}

	@Override
	public final boolean[] get$output() {
		return output;
	}

	@Override
	public final void set$output(boolean[] cv$value) {
		output = cv$value;
		setFlag$output = true;
		fixedProbFlag$sample42 = false;
		fixedProbFlag$sample55 = false;
	}

	@Override
	public final double[] get$prior() {
		return prior;
	}

	@Override
	public final void set$prior(double[] cv$value) {
		prior = cv$value;
		setFlag$prior = true;
		fixedProbFlag$sample20 = false;
		fixedProbFlag$sample42 = false;
		fixedProbFlag$sample55 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample20() {
		if(!fixedProbFlag$sample20) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(prior, v);
			logProbability$var18 = cv$distributionAccumulator;
			logProbability$prior = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample20 = fixedFlag$sample20;
		} else {
			logProbability$var18 = logProbability$prior;
			logProbability$$model = (logProbability$$model + logProbability$prior);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + logProbability$prior);
		}
	}

	private final void logProbabilityValue$sample42() {
		if(!fixedProbFlag$sample42) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var39 = 0; i$var39 < (length / 2); i$var39 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(output[i$var39], prior[0]));
			logProbability$b1 = cv$sampleAccumulator;
			logProbability$var40 = cv$sampleAccumulator;
			logProbability$output = (logProbability$output + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample42 = (fixedFlag$sample42 && fixedFlag$sample20);
		} else {
			logProbability$b1 = logProbability$var40;
			logProbability$output = (logProbability$output + logProbability$var40);
			logProbability$$model = (logProbability$$model + logProbability$var40);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var40);
		}
	}

	private final void logProbabilityValue$sample55() {
		if(!fixedProbFlag$sample55) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var52 = (length / 2); i$var52 < length; i$var52 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(output[i$var52], prior[1]));
			logProbability$b2 = cv$sampleAccumulator;
			logProbability$var53 = cv$sampleAccumulator;
			logProbability$output = (logProbability$output + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample55 = (fixedFlag$sample55 && fixedFlag$sample20);
		} else {
			logProbability$b2 = logProbability$var53;
			logProbability$output = (logProbability$output + logProbability$var53);
			logProbability$$model = (logProbability$$model + logProbability$var53);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var53);
		}
	}

	private final void sample20() {
		double cv$originalProbability;
		int cv$arrayLength = prior.length;
		int cv$indexToChange = (int)((double)cv$arrayLength * DistributionSampling.sampleUniform(RNG$));
		double cv$movementRatio = ((DistributionSampling.sampleBeta(RNG$, 5, 5) * 1.9999) - 1);
		double cv$proposedDifference;
		if((cv$movementRatio < 0))
			cv$proposedDifference = prior[cv$indexToChange];
		else {
			cv$proposedDifference = (1.0 - prior[cv$indexToChange]);
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1) {
				double cv$temp = (prior[cv$loopIndex] * (cv$arrayLength - 1));
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1) {
				double cv$temp = (prior[cv$loopIndex] * (cv$arrayLength - 1));
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
		}
		cv$proposedDifference = (cv$movementRatio * cv$proposedDifference);
		double cv$rebalanceValue = (cv$proposedDifference / (cv$arrayLength - 1));
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(prior, v);
			for(int i$var39 = 0; i$var39 < (length / 2); i$var39 += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var39], prior[0]) + cv$accumulatedProbabilities);
			for(int i$var52 = (length / 2); i$var52 < length; i$var52 += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var52], prior[1]) + cv$accumulatedProbabilities);
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
			prior[cv$loopIndex] = (prior[cv$loopIndex] - cv$rebalanceValue);
		prior[cv$indexToChange] = (prior[cv$indexToChange] + cv$proposedDifference);
		for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			prior[cv$loopIndex] = (prior[cv$loopIndex] - cv$rebalanceValue);
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(prior, v);
		for(int i$var39 = 0; i$var39 < (length / 2); i$var39 += 1)
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var39], prior[0]) + cv$accumulatedProbabilities);
		for(int i$var52 = (length / 2); i$var52 < length; i$var52 += 1)
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[i$var52], prior[1]) + cv$accumulatedProbabilities);
		if(((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$)))) {
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
				prior[cv$loopIndex] = (prior[cv$loopIndex] + cv$rebalanceValue);
			prior[cv$indexToChange] = (prior[cv$indexToChange] - cv$proposedDifference);
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				prior[cv$loopIndex] = (prior[cv$loopIndex] + cv$rebalanceValue);
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		v = new double[2];
		if(!setFlag$prior)
			prior = new double[2];
		if(!setFlag$output)
			output = new boolean[length$observed];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, prior);
		if(!fixedFlag$sample42) {
			for(int i$var39 = 0; i$var39 < (length / 2); i$var39 += 1)
				output[i$var39] = DistributionSampling.sampleBernoulli(RNG$, prior[0]);
		}
		if(!fixedFlag$sample55) {
			for(int i$var52 = (length / 2); i$var52 < length; i$var52 += 1)
				output[i$var52] = DistributionSampling.sampleBernoulli(RNG$, prior[1]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, prior);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, prior);
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample20)
			sample20();
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		v[0] = 0.1;
		v[1] = 0.1;
		length = length$observed;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var18 = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$prior = 0.0;
		logProbability$b1 = 0.0;
		logProbability$output = 0.0;
		if(!fixedProbFlag$sample42)
			logProbability$var40 = 0.0;
		logProbability$b2 = 0.0;
		if(!fixedProbFlag$sample55)
			logProbability$var53 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		logProbabilityValue$sample42();
		logProbabilityValue$sample55();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample42();
		logProbabilityValue$sample55();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample42();
		logProbabilityValue$sample55();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, prior);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
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