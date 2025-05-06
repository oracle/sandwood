package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Conditional2b$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Conditional2b$CoreInterface {
	private double[] cv$var4$stateProbabilityGlobal;
	private boolean fixedFlag$sample10 = false;
	private boolean fixedFlag$sample4 = false;
	private boolean fixedProbFlag$sample10 = false;
	private boolean fixedProbFlag$sample4 = false;
	private boolean guard;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$guard;
	private double logProbability$u;
	private double logProbability$value;
	private double logProbability$value2;
	private double logProbability$var9;
	private double[] observedValue;
	private boolean system$gibbsForward = true;
	private double u;
	private double[] value;
	private double[] value2;

	public Conditional2b$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$fixedFlag$sample10() {
		return fixedFlag$sample10;
	}

	@Override
	public final void set$fixedFlag$sample10(boolean cv$value) {
		fixedFlag$sample10 = cv$value;
		fixedProbFlag$sample10 = (cv$value && fixedProbFlag$sample10);
	}

	@Override
	public final boolean get$fixedFlag$sample4() {
		return fixedFlag$sample4;
	}

	@Override
	public final void set$fixedFlag$sample4(boolean cv$value) {
		fixedFlag$sample4 = cv$value;
		fixedProbFlag$sample4 = (cv$value && fixedProbFlag$sample4);
	}

	@Override
	public final boolean get$guard() {
		return guard;
	}

	@Override
	public final void set$guard(boolean cv$value) {
		guard = cv$value;
		fixedProbFlag$sample4 = false;
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
	public final double get$logProbability$u() {
		return logProbability$u;
	}

	@Override
	public final double get$logProbability$value() {
		return logProbability$value;
	}

	@Override
	public final double get$logProbability$value2() {
		return logProbability$value2;
	}

	@Override
	public final double[] get$observedValue() {
		return observedValue;
	}

	@Override
	public final void set$observedValue(double[] cv$value) {
		observedValue = cv$value;
	}

	@Override
	public final double get$u() {
		return u;
	}

	@Override
	public final void set$u(double cv$value) {
		u = cv$value;
		fixedProbFlag$sample10 = false;
	}

	@Override
	public final double[] get$value() {
		return value;
	}

	@Override
	public final double[] get$value2() {
		return value2;
	}

	private final void logProbabilityValue$sample10() {
		if(!fixedProbFlag$sample10) {
			double cv$distributionAccumulator = (((0.0 <= u) && (u < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			logProbability$var9 = cv$distributionAccumulator;
			logProbability$u = cv$distributionAccumulator;
			if(!guard) {
				logProbability$value = (logProbability$value + cv$distributionAccumulator);
				logProbability$value2 = (logProbability$value2 + cv$distributionAccumulator);
			}
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample10 = fixedFlag$sample10;
		} else {
			logProbability$var9 = logProbability$u;
			if(!guard) {
				logProbability$value = (logProbability$value + logProbability$u);
				logProbability$value2 = (logProbability$value2 + logProbability$u);
			}
			logProbability$$model = (logProbability$$model + logProbability$u);
			logProbability$$evidence = (logProbability$$evidence + logProbability$u);
		}
	}

	private final void logProbabilityValue$sample4() {
		if(!fixedProbFlag$sample4) {
			double cv$weightedProbability = -0.6931471805599453;
			if((guard && !(value2[0] == 1.0)))
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
		if((value2[0] == 1.0))
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
		value = new double[1];
		value2 = new double[1];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(!fixedFlag$sample10)
			u = DistributionSampling.sampleUniform(RNG$);
		if(guard)
			value[0] = 1.0;
		else {
			if(!fixedFlag$sample10)
				value[0] = u;
		}
		if((!fixedFlag$sample4 || !fixedFlag$sample10))
			value2[0] = value[0];
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(!fixedFlag$sample10)
			u = DistributionSampling.sampleUniform(RNG$);
		if(guard)
			value[0] = 1.0;
		else
			value[0] = u;
		value2[0] = value[0];
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
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
		logProbability$var9 = 0.0;
		logProbability$value = 0.0;
		logProbability$value2 = 0.0;
		if(!fixedProbFlag$sample10)
			logProbability$u = Double.NaN;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample4)
			logProbabilityValue$sample4();
		logProbabilityValue$sample10();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample4();
		logProbabilityValue$sample10();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample4();
		logProbabilityValue$sample10();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = value2.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			value2[cv$index1] = observedValue[cv$index1];
		value[0] = value2[0];
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
		     + "public model Conditional2b(double[] observedValue)  {\n"
		     + "        \n"
		     + "    //Construct a bernoulli\n"
		     + "    Bernoulli bernoulli = bernoulli(0.5);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    boolean guard = bernoulli.sample();\n"
		     + "    \n"
		     + "    double[] value = new double[1];\n"
		     + "    \n"
		     + "    double u = uniform(0.0, 1.0).sample();\n"
		     + "\n"
		     + "    if(guard)\n"
		     + "        value[0] = 1.0;\n"
		     + "    else {\n"
		     + "        value[0] = u;\n"
		     + "    }\n"
		     + "    \n"
		     + "    double [] value2 = new double[1];\n"
		     + "    \n"
		     + "    value2[0] = value[0];\n"
		     + "    \n"
		     + "    //Link the sampled value to the observed value\n"
		     + "    value2.observe(observedValue);\n"
		     + "}";
	}
}