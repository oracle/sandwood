package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK1c$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK1c$CoreInterface {
	private double a;
	private double b;
	private boolean fixedFlag$sample6 = false;
	private boolean fixedProbFlag$sample19 = false;
	private boolean fixedProbFlag$sample6 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$flips;
	private double logProbability$var19;
	private double logProbability$var5;
	private double logProbability$var6;
	private int samples;
	private boolean system$gibbsForward = true;
	private double var6;

	public Flip1CoinMK1c$MultiThreadCPU(ExecutionTarget target) {
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
	public final boolean get$fixedFlag$sample6() {
		return fixedFlag$sample6;
	}

	@Override
	public final void set$fixedFlag$sample6(boolean cv$value) {
		fixedFlag$sample6 = cv$value;
		fixedProbFlag$sample6 = (fixedFlag$sample6 && fixedProbFlag$sample6);
		fixedProbFlag$sample19 = (fixedFlag$sample6 && fixedProbFlag$sample19);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	@Override
	public final void set$flipsMeasured(boolean[] cv$value) {
		flipsMeasured = cv$value;
	}

	@Override
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int cv$value) {
		length$flipsMeasured = cv$value;
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
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	@Override
	public final double get$var6() {
		return var6;
	}

	@Override
	public final void set$var6(double cv$value) {
		var6 = cv$value;
		fixedProbFlag$sample6 = false;
		fixedProbFlag$sample19 = false;
	}

	private final void logProbabilityValue$sample19() {
		if(!fixedProbFlag$sample19) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var18 = 0; var18 < samples; var18 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[var18];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var6:(1.0 - var6))));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var19 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample19 = fixedFlag$sample6;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var18 = 0; var18 < samples; var18 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var19;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bernoulli = cv$rvAccumulator;
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample6() {
		if(!fixedProbFlag$sample6) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = var6;
				{
					{
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, a, b));
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
								cv$distributionAccumulator = cv$weightedProbability;
							else
								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
						}
						cv$probabilityReached = (cv$probabilityReached + 1.0);
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var5 = cv$sampleAccumulator;
			logProbability$var6 = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample6 = fixedFlag$sample6;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var6;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var5 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample6() {
		if(true) {
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						{
							for(int var18 = 0; var18 < samples; var18 += 1) {
								cv$count = (cv$count + 1);
								if(flips[var18])
									cv$sum = (cv$sum + 1);
							}
						}
					}
				}
			}
			var6 = Conjugates.sampleConjugateBetaBinomial(RNG$, a, b, cv$sum, cv$count);
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		flips = new boolean[length$flipsMeasured];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample6)
			var6 = DistributionSampling.sampleBeta(RNG$, a, b);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$var18, int forEnd$var18, int threadID$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var18 = forStart$var18; var18 < forEnd$var18; var18 += 1)
						flips[var18] = DistributionSampling.sampleBernoulli(RNG$1, var6);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample6)
			var6 = DistributionSampling.sampleBeta(RNG$, a, b);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample6)
			var6 = DistributionSampling.sampleBeta(RNG$, a, b);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$var18, int forEnd$var18, int threadID$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var18 = forStart$var18; var18 < forEnd$var18; var18 += 1)
						flips[var18] = DistributionSampling.sampleBernoulli(RNG$1, var6);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample6)
			var6 = DistributionSampling.sampleBeta(RNG$, a, b);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample6)
			var6 = DistributionSampling.sampleBeta(RNG$, a, b);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample6)
				sample6();
		} else {
			if(!fixedFlag$sample6)
				sample6();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		samples = length$flipsMeasured;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var5 = 0.0;
		if(!fixedProbFlag$sample6)
			logProbability$var6 = Double.NaN;
		logProbability$bernoulli = Double.NaN;
		logProbability$flips = 0.0;
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
		boolean[] cv$source1 = flipsMeasured;
		boolean[] cv$target1 = flips;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
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
		     + "public model Flip1CoinMK1c(boolean[] flipsMeasured, double a, double b) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    Bernoulli bernoulli = bernoulli(beta(a, b).sample());\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}