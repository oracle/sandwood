package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class UniformBernoulli$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements UniformBernoulli$CoreInterface {
	private boolean fixedFlag$sample23 = false;
	private boolean fixedFlag$sample8 = false;
	private boolean fixedProbFlag$sample23 = false;
	private boolean fixedProbFlag$sample8 = false;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$output;
	private double logProbability$prior;
	private double logProbability$var21;
	private double logProbability$var6;
	private boolean[] observed;
	private boolean[] output;
	private double prior;
	private boolean setFlag$output = false;
	private boolean system$gibbsForward = true;

	public UniformBernoulli$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$a() {
		return 0.0;
	}

	@Override
	public final double get$b() {
		return 1.0;
	}

	@Override
	public final boolean get$fixedFlag$sample23() {
		return fixedFlag$sample23;
	}

	@Override
	public final void set$fixedFlag$sample23(boolean cv$value) {
		fixedFlag$sample23 = cv$value;
		fixedProbFlag$sample23 = (cv$value && fixedProbFlag$sample23);
	}

	@Override
	public final boolean get$fixedFlag$sample8() {
		return fixedFlag$sample8;
	}

	@Override
	public final void set$fixedFlag$sample8(boolean cv$value) {
		fixedFlag$sample8 = cv$value;
		fixedProbFlag$sample8 = (cv$value && fixedProbFlag$sample8);
		fixedProbFlag$sample23 = (cv$value && fixedProbFlag$sample23);
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
	public final double get$logProbability$bernoulli() {
		return logProbability$bernoulli;
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
		fixedProbFlag$sample23 = false;
	}

	@Override
	public final double get$prior() {
		return prior;
	}

	@Override
	public final void set$prior(double cv$value) {
		prior = cv$value;
		fixedProbFlag$sample8 = false;
		fixedProbFlag$sample23 = false;
	}

	private final void logProbabilityValue$sample23() {
		if(!fixedProbFlag$sample23) {
			double cv$sampleAccumulator = 0.0;
			for(int var20 = 0; var20 < length$observed; var20 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(output[var20], prior));
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var21 = cv$sampleAccumulator;
			logProbability$output = (logProbability$output + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample23 = (fixedFlag$sample23 && fixedFlag$sample8);
		} else {
			logProbability$bernoulli = logProbability$var21;
			logProbability$output = (logProbability$output + logProbability$var21);
			logProbability$$model = (logProbability$$model + logProbability$var21);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var21);
		}
	}

	private final void logProbabilityValue$sample8() {
		if(!fixedProbFlag$sample8) {
			double cv$distributionAccumulator = (((0.0 <= prior) && (prior <= 1.0))?0.0:Double.NEGATIVE_INFINITY);
			logProbability$var6 = cv$distributionAccumulator;
			logProbability$prior = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample8)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample8 = fixedFlag$sample8;
		} else {
			logProbability$var6 = logProbability$prior;
			logProbability$$model = (logProbability$$model + logProbability$prior);
			if(fixedFlag$sample8)
				logProbability$$evidence = (logProbability$$evidence + logProbability$prior);
		}
	}

	private final void sample8() {
		double cv$originalValue = prior;
		double cv$originalProbability;
		double cv$var = ((prior * prior) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + prior);
		{
			double cv$accumulatedProbabilities = (((0.0 <= prior) && (prior <= 1.0))?0.0:Double.NEGATIVE_INFINITY);
			for(int var20 = 0; var20 < length$observed; var20 += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[var20], prior) + cv$accumulatedProbabilities);
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		prior = cv$proposedValue;
		double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?0.0:Double.NEGATIVE_INFINITY);
		for(int var20 = 0; var20 < length$observed; var20 += 1)
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(output[var20], cv$proposedValue) + cv$accumulatedProbabilities);
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			prior = cv$originalValue;
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!setFlag$output)
			output = new boolean[length$observed];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample8)
			prior = DistributionSampling.sampleUniform(RNG$);
		if(!fixedFlag$sample23) {
			for(int var20 = 0; var20 < length$observed; var20 += 1)
				output[var20] = DistributionSampling.sampleBernoulli(RNG$, prior);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample8)
			prior = DistributionSampling.sampleUniform(RNG$);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample8)
			prior = DistributionSampling.sampleUniform(RNG$);
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample8)
			sample8();
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var6 = 0.0;
		if(!fixedProbFlag$sample8)
			logProbability$prior = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$output = 0.0;
		if(!fixedProbFlag$sample23)
			logProbability$var21 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample8)
			logProbabilityValue$sample8();
		logProbabilityValue$sample23();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample8();
		logProbabilityValue$sample23();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample8();
		logProbabilityValue$sample23();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample8)
			prior = DistributionSampling.sampleUniform(RNG$);
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model UniformBernoulli(boolean[] observed) {\n    double a = 0.0;\n    double b = 1.0;\n    double prior = uniform(a, b).sample();\n    Bernoulli bernoulli = bernoulli(prior);\n    boolean[] output = bernoulli.sample(observed.length);\n    output.observe(observed);\n}\n";
	}
}