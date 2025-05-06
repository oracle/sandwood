package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ExponentialDecayMK1$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements ExponentialDecayMK1$CoreInterface {
	private double a;
	private double b;
	private double[] decay;
	private double[] decayDetected;
	private boolean fixedFlag$sample6 = false;
	private boolean fixedProbFlag$sample19 = false;
	private boolean fixedProbFlag$sample6 = false;
	private int length$decayDetected;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$decay;
	private double logProbability$exponential;
	private double logProbability$rate;
	private double logProbability$var19;
	private double logProbability$var5;
	private double rate;
	private int samples;
	private boolean system$gibbsForward = true;

	public ExponentialDecayMK1$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$a() {
		return a;
	}

	@Override
	public final void set$a(double cv$value) {
		a = cv$value;
	}

	@Override
	public final double get$b() {
		return b;
	}

	@Override
	public final void set$b(double cv$value) {
		b = cv$value;
	}

	@Override
	public final double[] get$decay() {
		return decay;
	}

	@Override
	public final double[] get$decayDetected() {
		return decayDetected;
	}

	@Override
	public final void set$decayDetected(double[] cv$value) {
		decayDetected = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample6() {
		return fixedFlag$sample6;
	}

	@Override
	public final void set$fixedFlag$sample6(boolean cv$value) {
		fixedFlag$sample6 = cv$value;
		fixedProbFlag$sample6 = (cv$value && fixedProbFlag$sample6);
		fixedProbFlag$sample19 = (cv$value && fixedProbFlag$sample19);
	}

	@Override
	public final int get$length$decayDetected() {
		return length$decayDetected;
	}

	@Override
	public final void set$length$decayDetected(int cv$value) {
		length$decayDetected = cv$value;
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
	public final double get$logProbability$decay() {
		return logProbability$decay;
	}

	@Override
	public final double get$logProbability$exponential() {
		return logProbability$exponential;
	}

	@Override
	public final double get$logProbability$rate() {
		return logProbability$rate;
	}

	@Override
	public final double get$rate() {
		return rate;
	}

	@Override
	public final void set$rate(double cv$value) {
		rate = cv$value;
		fixedProbFlag$sample6 = false;
		fixedProbFlag$sample19 = false;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	private final void logProbabilityValue$sample19() {
		if(!fixedProbFlag$sample19) {
			double cv$sampleAccumulator = 0.0;
			for(int var18 = 0; var18 < samples; var18 += 1) {
				double cv$sampleValue = decay[var18];
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && !(cv$sampleValue == Double.POSITIVE_INFINITY))?(Math.log(rate) - (rate * cv$sampleValue)):Double.NEGATIVE_INFINITY));
			}
			logProbability$exponential = cv$sampleAccumulator;
			logProbability$var19 = cv$sampleAccumulator;
			logProbability$decay = (logProbability$decay + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample19 = fixedFlag$sample6;
		} else {
			logProbability$exponential = logProbability$var19;
			logProbability$decay = (logProbability$decay + logProbability$var19);
			logProbability$$model = (logProbability$$model + logProbability$var19);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var19);
		}
	}

	private final void logProbabilityValue$sample6() {
		if(!fixedProbFlag$sample6) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityGamma(rate, a, b);
			logProbability$var5 = cv$distributionAccumulator;
			logProbability$rate = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample6 = fixedFlag$sample6;
		} else {
			logProbability$var5 = logProbability$rate;
			logProbability$$model = (logProbability$$model + logProbability$rate);
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + logProbability$rate);
		}
	}

	private final void sample6() {
		double cv$sum = 0.0;
		int cv$count = 0;
		for(int var18 = 0; var18 < samples; var18 += 1) {
			cv$sum = (cv$sum + decay[var18]);
			cv$count = (cv$count + 1);
		}
		rate = Conjugates.sampleConjugateGammaExponential(RNG$, a, b, cv$sum, cv$count);
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		decay = new double[length$decayDetected];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample6)
			rate = DistributionSampling.sampleGamma(RNG$, a, b);
		for(int var18 = 0; var18 < samples; var18 += 1)
			decay[var18] = (DistributionSampling.sampleExponential(RNG$) / rate);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample6)
			rate = DistributionSampling.sampleGamma(RNG$, a, b);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample6)
			rate = DistributionSampling.sampleGamma(RNG$, a, b);
		for(int var18 = 0; var18 < samples; var18 += 1)
			decay[var18] = (DistributionSampling.sampleExponential(RNG$) / rate);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample6)
			rate = DistributionSampling.sampleGamma(RNG$, a, b);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample6)
			rate = DistributionSampling.sampleGamma(RNG$, a, b);
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample6)
			sample6();
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		samples = length$decayDetected;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var5 = 0.0;
		if(!fixedProbFlag$sample6)
			logProbability$rate = Double.NaN;
		logProbability$exponential = Double.NaN;
		logProbability$decay = 0.0;
		if(!fixedProbFlag$sample19)
			logProbability$var19 = Double.NaN;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample6)
			logProbabilityValue$sample6();
		logProbabilityValue$sample19();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample6();
		logProbabilityValue$sample19();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample6();
		logProbabilityValue$sample19();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = decay.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			decay[cv$index1] = decayDetected[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model ExponentialDecayMK1(double[] decayDetected, double a, double b) {\n"
		     + "    \n"
		     + "        int samples = decayDetected.length;\n"
		     + "        double rate = gamma(a, b).sample();\n"
		     + "        \n"
		     + "        Exponential exponential = exponential(rate);\n"
		     + "        double[] decay = exponential.sample(samples);\n"
		     + "        decay.observe(decayDetected);\n"
		     + "}";
	}
}