package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class AlternativeModelMK2$SingleThreadCPU extends CoreModelSingleThreadCPU implements AlternativeModelMK2$CoreInterface {
double bias;
	boolean constrainedFlag$sample6 = true;
	boolean fixedFlag$sample6 = false;
	boolean fixedProbFlag$sample6 = false;
	boolean fixedProbFlag$sample8 = false;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$bias;
	double logProbability$binomial;
	double logProbability$positiveCount;
	int observedPositiveCount;
	int observedSampleCount;
	int positiveCount;
	boolean system$gibbsForward = true;

	public AlternativeModelMK2$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double cv$value, boolean allocated$) {
		bias = cv$value;
		fixedProbFlag$sample6 = false;
		fixedProbFlag$sample8 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample6() {
		return fixedFlag$sample6;
	}

	@Override
	public final void set$fixedFlag$sample6(boolean cv$value, boolean allocated$) {
		fixedFlag$sample6 = cv$value;
		constrainedFlag$sample6 = (cv$value || constrainedFlag$sample6);
		fixedProbFlag$sample6 = (cv$value && fixedProbFlag$sample6);
		fixedProbFlag$sample8 = (cv$value && fixedProbFlag$sample8);
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
	public final double get$logProbability$binomial() {
		return logProbability$binomial;
	}

	@Override
	public final double get$logProbability$positiveCount() {
		return logProbability$positiveCount;
	}

	@Override
	public final int get$observedPositiveCount() {
		return observedPositiveCount;
	}

	@Override
	public final void set$observedPositiveCount(int cv$value, boolean allocated$) {
		observedPositiveCount = cv$value;
	}

	@Override
	public final int get$observedSampleCount() {
		return observedSampleCount;
	}

	@Override
	public final void set$observedSampleCount(int cv$value, boolean allocated$) {
		observedSampleCount = cv$value;
	}

	@Override
	public final int get$positiveCount() {
		return positiveCount;
	}

	private final void drawValueSample6() {
		bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	private final void inferSample6() {
		constrainedFlag$sample6 = false;
		constrainedFlag$sample6 = true;
		bias = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, positiveCount, observedSampleCount);
	}

	private final void logProbabilityValue$sample6() {
		if(!fixedProbFlag$sample6) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(bias, 1.0, 1.0);
			logProbability$bias = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample6 = fixedFlag$sample6;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$bias);
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + logProbability$bias);
		}
	}

	private final void logProbabilityValue$sample8() {
		if(!fixedProbFlag$sample8) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBinomial(positiveCount, bias, observedSampleCount);
			logProbability$binomial = cv$distributionAccumulator;
			logProbability$positiveCount = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample8 = fixedFlag$sample6;
		} else {
			logProbability$binomial = logProbability$positiveCount;
			logProbability$$model = (logProbability$$model + logProbability$positiveCount);
			logProbability$$evidence = (logProbability$$evidence + logProbability$positiveCount);
		}
	}

	@Override
	public final void allocate() {}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample6)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		positiveCount = DistributionSampling.sampleBinomial(RNG$, bias, observedSampleCount);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample6)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample6)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		positiveCount = DistributionSampling.sampleBinomial(RNG$, bias, observedSampleCount);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample6)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample6)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample6)
			inferSample6();
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample6)
			drawValueSample6();
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		if(!fixedProbFlag$sample6)
			logProbability$bias = Double.NaN;
		logProbability$binomial = 0.0;
		if(!fixedProbFlag$sample8)
			logProbability$positiveCount = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample6)
			logProbabilityValue$sample6();
		logProbabilityValue$sample8();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample6();
		logProbabilityValue$sample8();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample6();
		logProbabilityValue$sample8();
	}

	@Override
	public final void propagateObservedValues() {
		positiveCount = observedPositiveCount;
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "/**\n"
		     + " * A model for the fairness work.\n"
		     + " */\n"
		     + "public model AlternativeModelMK2(/** The number of observed samples */int observedSampleCount, int observedPositiveCount)  {\n"
		     + "    /** A bias to see how likely values are to be collected. */    \n"
		     + "    double bias = beta(1.0, 1.0).sample();\n"
		     + "        \n"
		     + "    //Construct a binomial\n"
		     + "    /** A binomial distribution for the tests. */\n"
		     + "    Binomial binomial = binomial(bias, observedSampleCount);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    int positiveCount = binomial.sample();\n"
		     + "        \n"
		     + "    //Link the sampled values to the observed values\n"
		     + "    positiveCount.observe(observedPositiveCount);\n"
		     + "}";
	}
}