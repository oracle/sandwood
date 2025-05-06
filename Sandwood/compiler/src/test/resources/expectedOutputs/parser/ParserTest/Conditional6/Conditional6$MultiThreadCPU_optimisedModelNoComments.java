package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Conditional6$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Conditional6$CoreInterface {
	private double a;
	private double b;
	private double[] cv$var5$stateProbabilityGlobal;
	private boolean fixedFlag$sample10 = false;
	private boolean fixedFlag$sample5 = false;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample10 = false;
	private boolean fixedProbFlag$sample5 = false;
	private boolean fixedProbFlag$sample9 = false;
	private boolean guard;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$a;
	private double logProbability$b;
	private double logProbability$bernoulli;
	private double logProbability$guard;
	private double logProbability$sample10;
	private double logProbability$sample9;
	private double logProbability$u;
	private double logProbability$value;
	private boolean observedGuard;
	private double observedValue;
	private boolean system$gibbsForward = true;
	private double value;

	public Conditional6$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$a() {
		return a;
	}

	@Override
	public final void set$a(double cv$value) {
		a = cv$value;
		fixedProbFlag$sample9 = false;
	}

	@Override
	public final double get$b() {
		return b;
	}

	@Override
	public final void set$b(double cv$value) {
		b = cv$value;
		fixedProbFlag$sample10 = false;
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
	public final boolean get$fixedFlag$sample5() {
		return fixedFlag$sample5;
	}

	@Override
	public final void set$fixedFlag$sample5(boolean cv$value) {
		fixedFlag$sample5 = cv$value;
		fixedProbFlag$sample5 = (cv$value && fixedProbFlag$sample5);
	}

	@Override
	public final boolean get$fixedFlag$sample9() {
		return fixedFlag$sample9;
	}

	@Override
	public final void set$fixedFlag$sample9(boolean cv$value) {
		fixedFlag$sample9 = cv$value;
		fixedProbFlag$sample9 = (cv$value && fixedProbFlag$sample9);
	}

	@Override
	public final boolean get$guard() {
		return guard;
	}

	@Override
	public final void set$guard(boolean cv$value) {
		guard = cv$value;
		fixedProbFlag$sample5 = false;
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
	public final double get$logProbability$a() {
		return logProbability$a;
	}

	@Override
	public final double get$logProbability$b() {
		return logProbability$b;
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
	public final boolean get$observedGuard() {
		return observedGuard;
	}

	@Override
	public final void set$observedGuard(boolean cv$value) {
		observedGuard = cv$value;
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

	private final void logProbabilityValue$sample10() {
		if(!fixedProbFlag$sample10) {
			double cv$distributionAccumulator = (((0.0 <= b) && (b < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			logProbability$u = (logProbability$u + cv$distributionAccumulator);
			logProbability$sample10 = cv$distributionAccumulator;
			logProbability$b = (logProbability$b + cv$distributionAccumulator);
			if(!guard)
				logProbability$value = (logProbability$value + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample10 = fixedFlag$sample10;
		} else {
			logProbability$u = (logProbability$u + logProbability$sample10);
			logProbability$b = (logProbability$b + logProbability$sample10);
			if(!guard)
				logProbability$value = (logProbability$value + logProbability$sample10);
			logProbability$$model = (logProbability$$model + logProbability$sample10);
			logProbability$$evidence = (logProbability$$evidence + logProbability$sample10);
		}
	}

	private final void logProbabilityValue$sample5() {
		if(!fixedProbFlag$sample5) {
			logProbability$bernoulli = -0.6931471805599453;
			logProbability$guard = -0.6931471805599453;
			logProbability$$model = (logProbability$$model - 0.6931471805599453);
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence - 0.6931471805599453);
			fixedProbFlag$sample5 = fixedFlag$sample5;
		} else {
			logProbability$bernoulli = logProbability$guard;
			logProbability$$model = (logProbability$$model + logProbability$guard);
			if(fixedFlag$sample5)
				logProbability$$evidence = (logProbability$$evidence + logProbability$guard);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!fixedProbFlag$sample9) {
			double cv$distributionAccumulator = (((0.0 <= a) && (a < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			logProbability$u = (logProbability$u + cv$distributionAccumulator);
			logProbability$sample9 = cv$distributionAccumulator;
			logProbability$a = (logProbability$a + cv$distributionAccumulator);
			if(guard)
				logProbability$value = (logProbability$value + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample9 = fixedFlag$sample9;
		} else {
			logProbability$u = (logProbability$u + logProbability$sample9);
			logProbability$a = (logProbability$a + logProbability$sample9);
			if(guard)
				logProbability$value = (logProbability$value + logProbability$sample9);
			logProbability$$model = (logProbability$$model + logProbability$sample9);
			logProbability$$evidence = (logProbability$$evidence + logProbability$sample9);
		}
	}

	private final void sample5() {
		guard = false;
		cv$var5$stateProbabilityGlobal[0] = -0.6931471805599453;
		guard = true;
		cv$var5$stateProbabilityGlobal[1] = -0.6931471805599453;
		double cv$logSum;
		double cv$lseMax = cv$var5$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var5$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var5$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var5$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			cv$var5$stateProbabilityGlobal[0] = 0.5;
			cv$var5$stateProbabilityGlobal[1] = 0.5;
		} else {
			cv$var5$stateProbabilityGlobal[0] = Math.exp((cv$var5$stateProbabilityGlobal[0] - cv$logSum));
			cv$var5$stateProbabilityGlobal[1] = Math.exp((cv$var5$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var5$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var5$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		guard = (DistributionSampling.sampleCategorical(RNG$, cv$var5$stateProbabilityGlobal, 2) == 1);
	}

	@Override
	public final void allocateScratch() {
		cv$var5$stateProbabilityGlobal = new double[2];
	}

	@Override
	public final void allocator() {
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample5)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(!fixedFlag$sample9)
			a = DistributionSampling.sampleUniform(RNG$);
		if(!fixedFlag$sample10)
			b = DistributionSampling.sampleUniform(RNG$);
		if(guard) {
			if((!fixedFlag$sample5 || !fixedFlag$sample9))
				value = a;
		} else {
			if((!fixedFlag$sample5 || !fixedFlag$sample10))
				value = b;
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample5)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample5)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(!fixedFlag$sample9)
			a = DistributionSampling.sampleUniform(RNG$);
		if(!fixedFlag$sample10)
			b = DistributionSampling.sampleUniform(RNG$);
		if(guard) {
			if((!fixedFlag$sample5 || !fixedFlag$sample9))
				value = a;
		} else {
			if((!fixedFlag$sample5 || !fixedFlag$sample10))
				value = b;
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample5)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample5)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
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
		logProbability$bernoulli = 0.0;
		if(!fixedProbFlag$sample5)
			logProbability$guard = Double.NaN;
		logProbability$u = 0.0;
		logProbability$a = 0.0;
		logProbability$value = 0.0;
		if(!fixedProbFlag$sample9)
			logProbability$sample9 = Double.NaN;
		logProbability$b = 0.0;
		if(!fixedProbFlag$sample10)
			logProbability$sample10 = Double.NaN;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample5)
			logProbabilityValue$sample5();
		logProbabilityValue$sample9();
		logProbabilityValue$sample10();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample9();
		logProbabilityValue$sample10();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample9();
		logProbabilityValue$sample10();
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
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model Conditional6(double observedValue, boolean observedGuard)  {\n"
		     + "        \n"
		     + "    //Construct a bernoulli\n"
		     + "    Bernoulli bernoulli = bernoulli(0.5);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    boolean guard = bernoulli.sample();\n"
		     + "    Uniform u = uniform(0.0, 1.0);\n"
		     + "    double a = u.sample();\n"
		     + "    double b = u.sample();\n"
		     + "        \n"
		     + "    double value = guard?a:b;\n"
		     + "    \n"
		     + "    //Link the sampled value to the observed value\n"
		     + "    value.observe(observedValue);\n"
		     + "}";
	}
}