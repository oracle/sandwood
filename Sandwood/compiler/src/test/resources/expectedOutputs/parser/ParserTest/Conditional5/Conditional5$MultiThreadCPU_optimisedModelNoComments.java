package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Conditional5$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Conditional5$CoreInterface {
	private double a;
	private double b;
	private boolean fixedProbFlag$sample13 = false;
	private boolean fixedProbFlag$sample5 = false;
	private boolean fixedProbFlag$sample9 = false;
	private boolean guard;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$a;
	private double logProbability$b;
	private double logProbability$bernoulli;
	private double logProbability$guard;
	private double logProbability$sample13;
	private double logProbability$sample9;
	private double logProbability$value;
	private double logProbability$var12;
	private double logProbability$var8;
	private boolean observedGuard;
	private double observedValue;
	private boolean system$gibbsForward = true;
	private double value;

	public Conditional5$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$a() {
		return a;
	}

	@Override
	public final double get$b() {
		return b;
	}

	@Override
	public final boolean get$guard() {
		return guard;
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

	private final void logProbabilityValue$sample13() {
		if(!fixedProbFlag$sample13) {
			double cv$distributionAccumulator = (((0.0 <= b) && (b < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			logProbability$var12 = cv$distributionAccumulator;
			logProbability$sample13 = cv$distributionAccumulator;
			logProbability$b = (logProbability$b + cv$distributionAccumulator);
			if(!guard)
				logProbability$value = (logProbability$value + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample13 = true;
		} else {
			logProbability$var12 = logProbability$sample13;
			logProbability$b = (logProbability$b + logProbability$sample13);
			if(!guard)
				logProbability$value = (logProbability$value + logProbability$sample13);
			logProbability$$model = (logProbability$$model + logProbability$sample13);
			logProbability$$evidence = (logProbability$$evidence + logProbability$sample13);
		}
	}

	private final void logProbabilityValue$sample5() {
		if(!fixedProbFlag$sample5) {
			logProbability$bernoulli = -0.6931471805599453;
			logProbability$guard = -0.6931471805599453;
			logProbability$$model = (logProbability$$model - 0.6931471805599453);
			logProbability$$evidence = (logProbability$$evidence - 0.6931471805599453);
			fixedProbFlag$sample5 = true;
		} else {
			logProbability$bernoulli = logProbability$guard;
			logProbability$$model = (logProbability$$model + logProbability$guard);
			logProbability$$evidence = (logProbability$$evidence + logProbability$guard);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!fixedProbFlag$sample9) {
			double cv$distributionAccumulator = (((0.0 <= a) && (a < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			logProbability$var8 = cv$distributionAccumulator;
			logProbability$sample9 = cv$distributionAccumulator;
			logProbability$a = (logProbability$a + cv$distributionAccumulator);
			if(guard)
				logProbability$value = (logProbability$value + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample9 = true;
		} else {
			logProbability$var8 = logProbability$sample9;
			logProbability$a = (logProbability$a + logProbability$sample9);
			if(guard)
				logProbability$value = (logProbability$value + logProbability$sample9);
			logProbability$$model = (logProbability$$model + logProbability$sample9);
			logProbability$$evidence = (logProbability$$evidence + logProbability$sample9);
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {}

	@Override
	public final void forwardGeneration() {
		guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		a = DistributionSampling.sampleUniform(RNG$);
		b = DistributionSampling.sampleUniform(RNG$);
		if(guard)
			value = a;
		else
			value = b;
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {}

	@Override
	public final void forwardGenerationPrime() {
		guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		a = DistributionSampling.sampleUniform(RNG$);
		b = DistributionSampling.sampleUniform(RNG$);
		if(guard)
			value = a;
		else
			value = b;
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {}

	@Override
	public final void gibbsRound() {
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
		logProbability$var8 = 0.0;
		logProbability$a = 0.0;
		logProbability$value = 0.0;
		if(!fixedProbFlag$sample9)
			logProbability$sample9 = Double.NaN;
		logProbability$var12 = 0.0;
		logProbability$b = 0.0;
		if(!fixedProbFlag$sample13)
			logProbability$sample13 = Double.NaN;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample9();
		logProbabilityValue$sample13();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample9();
		logProbabilityValue$sample13();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample9();
		logProbabilityValue$sample13();
	}

	@Override
	public final void propagateObservedValues() {
		guard = observedGuard;
		value = observedValue;
		if(observedGuard)
			a = observedValue;
		else
			b = observedValue;
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
		     + "public model Conditional5(double observedValue, boolean observedGuard)  {\n"
		     + "        \n"
		     + "    //Construct a bernoulli\n"
		     + "    Bernoulli bernoulli = bernoulli(0.5);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    boolean guard = bernoulli.sample();\n"
		     + "    double a = uniform(0.0, 1.0).sample();\n"
		     + "    double b = uniform(0.0, 1.0).sample();\n"
		     + "        \n"
		     + "    double value = guard?a:b;\n"
		     + "    \n"
		     + "    guard.observe(observedGuard);\n"
		     + "    //Link the sampled value to the observed value\n"
		     + "    value.observe(observedValue);\n"
		     + "}";
	}
}