package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class UniformBernoulli$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements UniformBernoulli$CoreInterface {
	private boolean fixedFlag$sample5 = false;
	private boolean fixedProbFlag$sample19 = false;
	private boolean fixedProbFlag$sample5 = false;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$output;
	private double logProbability$prior;
	private double logProbability$var19;
	private double logProbability$var4;
	private boolean[] observed;
	private boolean[] output;
	private double prior;
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
	public final boolean get$fixedFlag$sample5() {
		return fixedFlag$sample5;
	}

	@Override
	public final void set$fixedFlag$sample5(boolean cv$value) {
		fixedFlag$sample5 = cv$value;
		fixedProbFlag$sample5 = (cv$value && fixedProbFlag$sample5);
		fixedProbFlag$sample19 = (cv$value && fixedProbFlag$sample19);
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
	public final double get$prior() {
		return prior;
	}

	@Override
	public final void set$prior(double cv$value) {
		prior = cv$value;
		fixedProbFlag$sample5 = false;
		fixedProbFlag$sample19 = false;
	}

	private final void logProbabilityValue$sample19() {
		if(!fixedProbFlag$sample19) {
			double cv$sampleAccumulator = 0.0;
			for(int var18 = 0; var18 < length$observed; var18 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((output[var18]?prior:(1.0 - prior))));
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var19 = cv$sampleAccumulator;
			logProbability$output = (logProbability$output + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample19 = fixedFlag$sample5;
		} else {
			logProbability$bernoulli = logProbability$var19;
			logProbability$output = (logProbability$output + logProbability$var19);
			logProbability$$model = (logProbability$$model + logProbability$var19);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var19);
		}
	}

	private final void logProbabilityValue$sample5() {
		if(!fixedProbFlag$sample5) {
			double cv$distributionAccumulator = (((0.0 <= prior) && (prior < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			logProbability$var4 = cv$distributionAccumulator;
			logProbability$prior = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample5 = fixedFlag$sample5;
		} else {
			logProbability$var4 = logProbability$prior;
			logProbability$$model = (logProbability$$model + logProbability$prior);
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence + logProbability$prior);
		}
	}

	private final void sample5() {
		double cv$originalValue = prior;
		double cv$originalProbability;
		double cv$var = ((prior * prior) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + prior);
		{
			double cv$accumulatedProbabilities = (((0.0 <= prior) && (prior < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			for(int var18 = 0; var18 < length$observed; var18 += 1)
				cv$accumulatedProbabilities = (Math.log((output[var18]?prior:(1.0 - prior))) + cv$accumulatedProbabilities);
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		prior = cv$proposedValue;
		double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue < 1.0))?0.0:Double.NEGATIVE_INFINITY);
		for(int var18 = 0; var18 < length$observed; var18 += 1)
			cv$accumulatedProbabilities = (Math.log((output[var18]?cv$proposedValue:(1.0 - cv$proposedValue))) + cv$accumulatedProbabilities);
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			prior = cv$originalValue;
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		output = new boolean[length$observed];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample5)
			prior = DistributionSampling.sampleUniform(RNG$);
		for(int var18 = 0; var18 < length$observed; var18 += 1)
			output[var18] = DistributionSampling.sampleBernoulli(RNG$, prior);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample5)
			prior = DistributionSampling.sampleUniform(RNG$);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample5)
			prior = DistributionSampling.sampleUniform(RNG$);
		for(int var18 = 0; var18 < length$observed; var18 += 1)
			output[var18] = DistributionSampling.sampleBernoulli(RNG$, prior);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample5)
			prior = DistributionSampling.sampleUniform(RNG$);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample5)
			prior = DistributionSampling.sampleUniform(RNG$);
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample5)
			sample5();
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var4 = 0.0;
		if(!fixedProbFlag$sample5)
			logProbability$prior = Double.NaN;
		logProbability$bernoulli = Double.NaN;
		logProbability$output = 0.0;
		if(!fixedProbFlag$sample19)
			logProbability$var19 = Double.NaN;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample5)
			logProbabilityValue$sample5();
		logProbabilityValue$sample19();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample19();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample19();
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
		     + "public model UniformBernoulli(boolean[] observed) {\n"
		     + "    double a = 0.0;\n"
		     + "    double b = 1.0;\n"
		     + "    double prior = uniform(a, b).sample();\n"
		     + "    Bernoulli bernoulli = bernoulli(prior);\n"
		     + "    boolean[] output = bernoulli.sample(observed.length);\n"
		     + "    output.observe(observed);\n"
		     + "}\n"
		     + "";
	}
}