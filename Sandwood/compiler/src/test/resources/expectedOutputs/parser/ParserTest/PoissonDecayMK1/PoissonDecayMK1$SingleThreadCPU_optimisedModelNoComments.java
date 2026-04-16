package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class PoissonDecayMK1$SingleThreadCPU extends CoreModelSingleThreadCPU implements PoissonDecayMK1$CoreInterface {
double a;
	double b;
	boolean constrainedFlag$sample6 = true;
	int[] decay;
	int[] decayDetected;
	boolean fixedFlag$sample6 = false;
	boolean fixedProbFlag$sample19 = false;
	boolean fixedProbFlag$sample6 = false;
	int length$decayDetected;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$decay;
	double logProbability$poisson;
	double logProbability$rate;
	double logProbability$var19;
	double rate;
	int samples;
	boolean system$gibbsForward = true;

	public PoissonDecayMK1$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$a() {
		return a;
	}

	@Override
	public final void set$a(double cv$value, boolean allocated$) {
		a = cv$value;
	}

	@Override
	public final double get$b() {
		return b;
	}

	@Override
	public final void set$b(double cv$value, boolean allocated$) {
		b = cv$value;
	}

	@Override
	public final int[] get$decay() {
		return decay;
	}

	@Override
	public final int[] get$decayDetected() {
		return decayDetected;
	}

	@Override
	public final void set$decayDetected(int[] cv$value, boolean allocated$) {
		decayDetected = cv$value;
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
		fixedProbFlag$sample19 = (cv$value && fixedProbFlag$sample19);
	}

	@Override
	public final int get$length$decayDetected() {
		return length$decayDetected;
	}

	@Override
	public final void set$length$decayDetected(int cv$value, boolean allocated$) {
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
	public final double get$logProbability$poisson() {
		return logProbability$poisson;
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
	public final void set$rate(double cv$value, boolean allocated$) {
		rate = cv$value;
		fixedProbFlag$sample6 = false;
		fixedProbFlag$sample19 = false;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	private final void drawValueSample6() {
		rate = DistributionSampling.sampleGamma(RNG$, a, b);
	}

	private final void inferSample6() {
		constrainedFlag$sample6 = false;
		double cv$sum = 0.0;
		int cv$count = 0;
		for(int var18 = 0; var18 < samples; var18 += 1) {
			constrainedFlag$sample6 = true;
			cv$sum = (cv$sum + decay[var18]);
			cv$count = (cv$count + 1);
		}
		if(constrainedFlag$sample6)
			rate = Conjugates.sampleConjugateGammaPoisson(RNG$, a, b, cv$sum, cv$count);
	}

	private final void logProbabilityValue$sample19() {
		if(!fixedProbFlag$sample19) {
			double cv$sampleAccumulator = 0.0;
			for(int var18 = 0; var18 < samples; var18 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityPoisson(decay[var18], rate));
			logProbability$poisson = cv$sampleAccumulator;
			logProbability$var19 = cv$sampleAccumulator;
			logProbability$decay = (logProbability$decay + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample19 = fixedFlag$sample6;
		} else {
			logProbability$poisson = logProbability$var19;
			logProbability$decay = (logProbability$decay + logProbability$var19);
			logProbability$$model = (logProbability$$model + logProbability$var19);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var19);
		}
	}

	private final void logProbabilityValue$sample6() {
		if(!fixedProbFlag$sample6) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityGamma(rate, a, b);
			logProbability$rate = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample6 = fixedFlag$sample6;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$rate);
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + logProbability$rate);
		}
	}

	@Override
	public final void allocate() {
		decay = new int[length$decayDetected];
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample6)
			rate = DistributionSampling.sampleGamma(RNG$, a, b);
		for(int var18 = 0; var18 < samples; var18 += 1)
			decay[var18] = DistributionSampling.samplePoisson(RNG$, rate);
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
			decay[var18] = DistributionSampling.samplePoisson(RNG$, rate);
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
			inferSample6();
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample6)
			drawValueSample6();
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		if(!fixedProbFlag$sample6)
			logProbability$rate = Double.NaN;
		logProbability$poisson = Double.NaN;
		logProbability$decay = 0.0;
		if(!fixedProbFlag$sample19)
			logProbability$var19 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		samples = length$decayDetected;
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
		     + " * Copyright (c) 2019-2023, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model PoissonDecayMK1(int[] decayDetected, double a, double b) {\n"
		     + "    \n"
		     + "        int samples = decayDetected.length;\n"
		     + "        double rate = gamma(a, b).sample();\n"
		     + "        \n"
		     + "        Poisson poisson = poisson(rate);\n"
		     + "        int[] decay = poisson.sample(samples);\n"
		     + "        decay.observe(decayDetected);\n"
		     + "}";
	}
}