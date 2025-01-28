package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Conditional2d$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Conditional2d$CoreInterface {
	private double[] cv$var6$stateProbabilityGlobal;
	private boolean fixedFlag$sample11 = false;
	private boolean fixedFlag$sample7 = false;
	private boolean fixedProbFlag$sample11 = false;
	private boolean fixedProbFlag$sample7 = false;
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
	private double value;
	private double[] value2;

	public Conditional2d$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$fixedFlag$sample11() {
		return fixedFlag$sample11;
	}

	@Override
	public final void set$fixedFlag$sample11(boolean cv$value) {
		fixedFlag$sample11 = cv$value;
		fixedProbFlag$sample11 = (cv$value && fixedProbFlag$sample11);
	}

	@Override
	public final boolean get$fixedFlag$sample7() {
		return fixedFlag$sample7;
	}

	@Override
	public final void set$fixedFlag$sample7(boolean cv$value) {
		fixedFlag$sample7 = cv$value;
		fixedProbFlag$sample7 = (cv$value && fixedProbFlag$sample7);
	}

	@Override
	public final boolean get$guard() {
		return guard;
	}

	@Override
	public final void set$guard(boolean cv$value) {
		guard = cv$value;
		fixedProbFlag$sample7 = false;
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
		fixedProbFlag$sample11 = false;
	}

	@Override
	public final double get$value() {
		return value;
	}

	@Override
	public final double[] get$value2() {
		return value2;
	}

	private final void logProbabilityValue$sample11() {
		if(!fixedProbFlag$sample11) {
			double cv$distributionAccumulator = (((0.0 <= u) && (u <= 1.0))?0.0:Double.NEGATIVE_INFINITY);
			logProbability$var9 = cv$distributionAccumulator;
			logProbability$u = cv$distributionAccumulator;
			if(!guard) {
				logProbability$value = (logProbability$value + cv$distributionAccumulator);
				logProbability$value2 = (logProbability$value2 + cv$distributionAccumulator);
			}
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample11 = fixedFlag$sample11;
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

	private final void logProbabilityValue$sample7() {
		if(!fixedProbFlag$sample7) {
			double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(guard, 0.5);
			if((guard && !(value2[0] == 1.0)))
				cv$weightedProbability = Double.NEGATIVE_INFINITY;
			logProbability$bernoulli = cv$weightedProbability;
			logProbability$guard = cv$weightedProbability;
			logProbability$value = (logProbability$value + cv$weightedProbability);
			logProbability$value2 = (logProbability$value2 + cv$weightedProbability);
			logProbability$$model = (logProbability$$model + cv$weightedProbability);
			if(fixedFlag$sample7)
				logProbability$$evidence = (logProbability$$evidence + cv$weightedProbability);
			fixedProbFlag$sample7 = fixedFlag$sample7;
		} else {
			logProbability$bernoulli = logProbability$guard;
			logProbability$value = (logProbability$value + logProbability$guard);
			logProbability$value2 = (logProbability$value2 + logProbability$guard);
			logProbability$$model = (logProbability$$model + logProbability$guard);
			if(fixedFlag$sample7)
				logProbability$$evidence = (logProbability$$evidence + logProbability$guard);
		}
	}

	private final void sample7() {
		guard = false;
		cv$var6$stateProbabilityGlobal[0] = DistributionSampling.logProbabilityBernoulli(false, 0.5);
		guard = true;
		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
		if((value2[0] == 1.0))
			cv$accumulatedConsumerProbabilities = 0.0;
		cv$var6$stateProbabilityGlobal[1] = (cv$accumulatedConsumerProbabilities + DistributionSampling.logProbabilityBernoulli(true, 0.5));
		double cv$logSum;
		double cv$lseMax = cv$var6$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var6$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var6$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var6$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			cv$var6$stateProbabilityGlobal[0] = 0.5;
			cv$var6$stateProbabilityGlobal[1] = 0.5;
		} else {
			cv$var6$stateProbabilityGlobal[0] = Math.exp((cv$var6$stateProbabilityGlobal[0] - cv$logSum));
			cv$var6$stateProbabilityGlobal[1] = Math.exp((cv$var6$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var6$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var6$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		guard = (DistributionSampling.sampleCategorical(RNG$, cv$var6$stateProbabilityGlobal) == 1);
	}

	@Override
	public final void allocateScratch() {
		cv$var6$stateProbabilityGlobal = new double[2];
	}

	@Override
	public final void allocator() {
		value2 = new double[1];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample7)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(!fixedFlag$sample11)
			u = DistributionSampling.sampleUniform(RNG$);
		if(guard)
			value = 1.0;
		else {
			if(!fixedFlag$sample11)
				value = u;
		}
		if((!fixedFlag$sample7 || !fixedFlag$sample11))
			value2[0] = value;
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample7)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample7)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample7)
			sample7();
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$value = 0.0;
		logProbability$value2 = 0.0;
		if(!fixedProbFlag$sample7)
			logProbability$guard = 0.0;
		logProbability$var9 = 0.0;
		if(!fixedProbFlag$sample11)
			logProbability$u = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample7)
			logProbabilityValue$sample7();
		logProbabilityValue$sample11();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample7();
		logProbabilityValue$sample11();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample7();
		logProbabilityValue$sample11();
	}

	@Override
	public final void logProbabilityGeneration() {
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = value2.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			value2[cv$index1] = observedValue[cv$index1];
		value = value2[0];
		if(!guard) {
			value = value2[0];
			u = value;
		}
	}

	@Override
	public final void setIntermediates() {
		if(guard)
			value = 1.0;
		else
			value = u;
		value2[0] = value;
	}

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
		     + "public model Conditional2d(double[] observedValue)  {\n"
		     + "        \n"
		     + "    //Construct a bernoulli\n"
		     + "    Bernoulli bernoulli = bernoulli(0.5);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    boolean guard = bernoulli.sample();\n"
		     + "    \n"
		     + "    double value;\n"
		     + "    \n"
		     + "    double u;\n"
		     + "    u = uniform(0.0, 1.0).sample();\n"
		     + "    \n"
		     + "    if(guard)\n"
		     + "        value = 1.0;\n"
		     + "    else {\n"
		     + "        value = u;\n"
		     + "    }\n"
		     + "    \n"
		     + "    double[] value2 = new double[1];\n"
		     + "    \n"
		     + "    value2[0] = value;\n"
		     + "    \n"
		     + "    //Link the sampled value to the observed value\n"
		     + "    value2.observe(observedValue);\n"
		     + "}";
	}
}