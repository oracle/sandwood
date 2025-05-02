package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Conditional1$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Conditional1$CoreInterface {
	private double[] cv$var4$stateProbabilityGlobal;
	private boolean fixedFlag$sample16 = false;
	private boolean fixedFlag$sample4 = false;
	private boolean fixedProbFlag$sample16 = false;
	private boolean fixedProbFlag$sample4 = false;
	private boolean guard;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$guard;
	private double logProbability$sample16;
	private double logProbability$value;
	private double logProbability$var13;
	private double logProbability$var14;
	private double observedValue;
	private boolean system$gibbsForward = true;
	private double value;
	private double var14;

	public Conditional1$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$fixedFlag$sample16() {
		return fixedFlag$sample16;
	}

	@Override
	public final void set$fixedFlag$sample16(boolean cv$value) {
		fixedFlag$sample16 = cv$value;
		fixedProbFlag$sample16 = (cv$value && fixedProbFlag$sample16);
	}

	@Override
	public final boolean get$fixedFlag$sample4() {
		return fixedFlag$sample4;
	}

	@Override
	public final void set$fixedFlag$sample4(boolean cv$value) {
		fixedFlag$sample4 = cv$value;
		fixedProbFlag$sample4 = (cv$value && fixedProbFlag$sample4);
		fixedProbFlag$sample16 = (cv$value && fixedProbFlag$sample16);
	}

	@Override
	public final boolean get$guard() {
		return guard;
	}

	@Override
	public final void set$guard(boolean cv$value) {
		guard = cv$value;
		fixedProbFlag$sample4 = false;
		fixedProbFlag$sample16 = false;
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
	public final double get$logProbability$guard() {
		return logProbability$guard;
	}

	@Override
	public final double get$logProbability$value() {
		return logProbability$value;
	}

	@Override
	public final double get$observedValue() {
		return observedValue;
	}

	@Override
	public final void set$observedValue(double cv$value) {
		observedValue = cv$value;
	}

	@Override
	public final double get$value() {
		return value;
	}

	@Override
	public final double get$var14() {
		return var14;
	}

	@Override
	public final void set$var14(double cv$value) {
		var14 = cv$value;
		fixedProbFlag$sample16 = false;
	}

	private final void logProbabilityValue$sample16() {
		if(!fixedProbFlag$sample16) {
			double cv$accumulator = 0.0;
			if(!guard) {
				double cv$distributionAccumulator = (((0.0 <= var14) && (var14 < 1.0))?0.0:Double.NEGATIVE_INFINITY);
				cv$accumulator = cv$distributionAccumulator;
				logProbability$var13 = cv$distributionAccumulator;
				logProbability$sample16 = cv$distributionAccumulator;
				logProbability$value = (logProbability$value + cv$distributionAccumulator);
			}
			logProbability$var14 = (logProbability$var14 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample16 = (fixedFlag$sample16 && fixedFlag$sample4);
		} else {
			double cv$accumulator = 0.0;
			if(!guard) {
				cv$accumulator = logProbability$sample16;
				logProbability$var13 = logProbability$sample16;
				logProbability$value = (logProbability$value + logProbability$sample16);
			}
			logProbability$var14 = (logProbability$var14 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample4() {
		if(!fixedProbFlag$sample4) {
			double cv$weightedProbability = -0.6931471805599453;
			if((guard && !(value == 1.0)))
				cv$weightedProbability = Double.NEGATIVE_INFINITY;
			logProbability$bernoulli = cv$weightedProbability;
			logProbability$guard = cv$weightedProbability;
			logProbability$$model = (logProbability$$model + cv$weightedProbability);
			if(fixedFlag$sample4)
				logProbability$$evidence = (logProbability$$evidence + cv$weightedProbability);
			fixedProbFlag$sample4 = fixedFlag$sample4;
		} else {
			logProbability$bernoulli = logProbability$guard;
			logProbability$$model = (logProbability$$model + logProbability$guard);
			if(fixedFlag$sample4)
				logProbability$$evidence = (logProbability$$evidence + logProbability$guard);
		}
	}

	private final void sample4() {
		guard = false;
		cv$var4$stateProbabilityGlobal[0] = -0.6931471805599453;
		guard = true;
		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
		if((value == 1.0))
			cv$accumulatedConsumerProbabilities = 0.0;
		cv$var4$stateProbabilityGlobal[1] = (cv$accumulatedConsumerProbabilities - 0.6931471805599453);
		double cv$logSum;
		double cv$lseMax = cv$var4$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var4$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var4$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var4$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			cv$var4$stateProbabilityGlobal[0] = 0.5;
			cv$var4$stateProbabilityGlobal[1] = 0.5;
		} else {
			cv$var4$stateProbabilityGlobal[0] = Math.exp((cv$var4$stateProbabilityGlobal[0] - cv$logSum));
			cv$var4$stateProbabilityGlobal[1] = Math.exp((cv$var4$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var4$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var4$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		guard = (DistributionSampling.sampleCategorical(RNG$, cv$var4$stateProbabilityGlobal, 2) == 1);
	}

	@Override
	public final void allocateScratch() {
		cv$var4$stateProbabilityGlobal = new double[2];
	}

	@Override
	public final void allocator() {
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(guard) {
			if(!fixedFlag$sample4)
				value = 1.0;
		} else {
			if(!fixedFlag$sample16)
				var14 = DistributionSampling.sampleUniform(RNG$);
			if((!fixedFlag$sample4 || !fixedFlag$sample16))
				value = var14;
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample4)
			sample4();
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$bernoulli = 0.0;
		if(!fixedProbFlag$sample4)
			logProbability$guard = Double.NaN;
		logProbability$var13 = Double.NaN;
		logProbability$var14 = 0.0;
		logProbability$value = 0.0;
		if(!fixedProbFlag$sample16)
			logProbability$sample16 = Double.NaN;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample4)
			logProbabilityValue$sample4();
		logProbabilityValue$sample16();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample4();
		logProbabilityValue$sample16();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample4();
		logProbabilityValue$sample16();
	}

	@Override
	public final void logProbabilityGeneration() {
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		value = observedValue;
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
		     + "public model Conditional1(double observedValue)  {\n"
		     + "        \n"
		     + "    //Construct a bernoulli\n"
		     + "    Bernoulli bernoulli = bernoulli(0.5);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    boolean guard = bernoulli.sample();\n"
		     + "        \n"
		     + "    double value = guard?1.0:uniform(0.0, 1.0).sample();\n"
		     + "    \n"
		     + "    //Link the sampled value to the observed value\n"
		     + "    value.observe(observedValue);\n"
		     + "}";
	}
}