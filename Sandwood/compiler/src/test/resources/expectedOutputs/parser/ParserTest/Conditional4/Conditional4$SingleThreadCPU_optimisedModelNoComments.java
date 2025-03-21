package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Conditional4$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Conditional4$CoreInterface {
	private double[] bias;
	private double[] cv$var4$stateProbabilityGlobal;
	private boolean fixedFlag$sample21 = false;
	private boolean fixedFlag$sample4 = false;
	private boolean fixedProbFlag$sample21 = false;
	private boolean fixedProbFlag$sample27 = false;
	private boolean fixedProbFlag$sample4 = false;
	private boolean guard;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$guard;
	private double logProbability$sample21;
	private double logProbability$value;
	private double logProbability$var18;
	private double logProbability$var24;
	private double observedValue;
	private boolean setFlag$bias = false;
	private boolean system$gibbsForward = true;
	private double value;

	public Conditional4$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		setFlag$bias = true;
		fixedProbFlag$sample21 = false;
		fixedProbFlag$sample27 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample21() {
		return fixedFlag$sample21;
	}

	@Override
	public final void set$fixedFlag$sample21(boolean cv$value) {
		fixedFlag$sample21 = cv$value;
		fixedProbFlag$sample21 = (cv$value && fixedProbFlag$sample21);
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
	}

	@Override
	public final boolean get$fixedFlag$sample4() {
		return fixedFlag$sample4;
	}

	@Override
	public final void set$fixedFlag$sample4(boolean cv$value) {
		fixedFlag$sample4 = cv$value;
		fixedProbFlag$sample4 = (cv$value && fixedProbFlag$sample4);
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
	}

	@Override
	public final boolean get$guard() {
		return guard;
	}

	@Override
	public final void set$guard(boolean cv$value) {
		guard = cv$value;
		fixedProbFlag$sample4 = false;
		fixedProbFlag$sample27 = false;
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
	public final double get$logProbability$bias() {
		return logProbability$bias;
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

	private final void logProbabilityValue$sample21() {
		if(!fixedProbFlag$sample21) {
			double cv$accumulator = 0.0;
			if(!guard) {
				double cv$sampleValue = bias[0];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue <= 0.5))?0.6931471805599453:Double.NEGATIVE_INFINITY);
				cv$accumulator = cv$distributionAccumulator;
				logProbability$var18 = cv$distributionAccumulator;
				logProbability$sample21 = cv$distributionAccumulator;
			}
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample21 = fixedFlag$sample21;
		} else {
			double cv$accumulator = 0.0;
			if(!guard) {
				cv$accumulator = logProbability$sample21;
				logProbability$var18 = logProbability$sample21;
			}
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample27() {
		if(!fixedProbFlag$sample27) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(value, bias[0], 1.0);
			logProbability$var24 = cv$distributionAccumulator;
			logProbability$value = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample27 = (fixedFlag$sample4 && fixedFlag$sample21);
		} else {
			logProbability$var24 = logProbability$value;
			logProbability$$model = (logProbability$$model + logProbability$value);
			logProbability$$evidence = (logProbability$$evidence + logProbability$value);
		}
	}

	private final void logProbabilityValue$sample4() {
		if(!fixedProbFlag$sample4) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(guard, 0.5);
			logProbability$bernoulli = cv$distributionAccumulator;
			logProbability$guard = cv$distributionAccumulator;
			boolean cv$guard$bias = false;
			if(guard) {
				cv$guard$bias = true;
				logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			}
			if((!guard && !cv$guard$bias))
				logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample4)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample4 = fixedFlag$sample4;
		} else {
			logProbability$bernoulli = logProbability$guard;
			boolean cv$guard$bias = false;
			if(guard) {
				cv$guard$bias = true;
				logProbability$bias = (logProbability$bias + logProbability$guard);
			}
			if((!guard && !cv$guard$bias))
				logProbability$bias = (logProbability$bias + logProbability$guard);
			logProbability$$model = (logProbability$$model + logProbability$guard);
			if(fixedFlag$sample4)
				logProbability$$evidence = (logProbability$$evidence + logProbability$guard);
		}
	}

	private final void sample21() {
		double cv$originalValue = bias[0];
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$originalProbability = (DistributionSampling.logProbabilityBeta(value, cv$originalValue, 1.0) + (((0.0 <= cv$originalValue) && (cv$originalValue <= 0.5))?0.6931471805599453:Double.NEGATIVE_INFINITY));
		bias[0] = cv$proposedValue;
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBeta(value, cv$proposedValue, 1.0) + (((0.0 <= cv$proposedValue) && (cv$proposedValue <= 0.5))?0.6931471805599453:Double.NEGATIVE_INFINITY));
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			bias[0] = cv$originalValue;
	}

	private final void sample4() {
		guard = false;
		cv$var4$stateProbabilityGlobal[0] = (((((0.0 <= bias[0]) && (bias[0] <= 0.5))?0.6931471805599453:Double.NEGATIVE_INFINITY) + DistributionSampling.logProbabilityBeta(value, bias[0], 1.0)) + DistributionSampling.logProbabilityBernoulli(false, 0.5));
		guard = true;
		cv$var4$stateProbabilityGlobal[1] = (DistributionSampling.logProbabilityBeta(value, bias[0], 1.0) + DistributionSampling.logProbabilityBernoulli(true, 0.5));
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
		if(guard)
			bias[0] = 0.5;
		else
			bias[0] = (DistributionSampling.sampleUniform(RNG$) * 0.5);
	}

	@Override
	public final void allocateScratch() {
		cv$var4$stateProbabilityGlobal = new double[2];
	}

	@Override
	public final void allocator() {
		if(!setFlag$bias)
			bias = new double[1];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(guard) {
			if(!fixedFlag$sample4)
				bias[0] = 0.5;
		} else {
			if((!fixedFlag$sample4 || !fixedFlag$sample21))
				bias[0] = (DistributionSampling.sampleUniform(RNG$) * 0.5);
		}
		value = DistributionSampling.sampleBeta(RNG$, bias[0], 1.0);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(guard) {
			if(!fixedFlag$sample4)
				bias[0] = 0.5;
		} else {
			if((!fixedFlag$sample4 || !fixedFlag$sample21))
				bias[0] = (DistributionSampling.sampleUniform(RNG$) * 0.5);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(guard) {
			if(!fixedFlag$sample4)
				bias[0] = 0.5;
		} else {
			if((!fixedFlag$sample4 || !fixedFlag$sample21))
				bias[0] = (DistributionSampling.sampleUniform(RNG$) * 0.5);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample4)
				sample4();
			if((!guard && !fixedFlag$sample21))
				sample21();
		} else {
			if((!guard && !fixedFlag$sample21))
				sample21();
			if(!fixedFlag$sample4)
				sample4();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample4)
			logProbability$guard = 0.0;
		logProbability$var18 = 0.0;
		if(!fixedProbFlag$sample21)
			logProbability$sample21 = 0.0;
		logProbability$var24 = 0.0;
		if(!fixedProbFlag$sample27)
			logProbability$value = 0.0;
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
		if(fixedFlag$sample21)
			logProbabilityValue$sample21();
		logProbabilityValue$sample27();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample4();
		logProbabilityValue$sample21();
		logProbabilityValue$sample27();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample4();
		logProbabilityValue$sample21();
		logProbabilityValue$sample27();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(guard) {
			if(!fixedFlag$sample4)
				bias[0] = 0.5;
		} else {
			if((!fixedFlag$sample4 || !fixedFlag$sample21))
				bias[0] = (DistributionSampling.sampleUniform(RNG$) * 0.5);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		value = observedValue;
	}

	@Override
	public final void setIntermediates() {
		if(guard)
			bias[0] = 0.5;
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
		     + "public model Conditional4(double observedValue)  {\n"
		     + "        \n"
		     + "    //Construct a bernoulli\n"
		     + "    Bernoulli bernoulli = bernoulli(0.5);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    boolean guard = bernoulli.sample();\n"
		     + "    \n"
		     + "    double[] bias = new double[1];\n"
		     + "        \n"
		     + "    if(guard)\n"
		     + "        bias[0] = 0.5;\n"
		     + "    else\n"
		     + "        bias[0] = uniform(0.0, 0.5).sample();\n"
		     + "    \n"
		     + "    double value = beta(bias[0],1.0).sample();\n"
		     + "    \n"
		     + "    //Link the sampled value to the observed value\n"
		     + "    value.observe(observedValue);\n"
		     + "}";
	}
}