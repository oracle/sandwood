package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK14$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK14$CoreInterface {
	private double b;
	private double bias;
	private double[] c;
	private boolean fixedFlag$sample12 = false;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedProbFlag$sample12 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private boolean guard1;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$c;
	private double logProbability$flips;
	private double logProbability$var25;
	private double logProbability$var9;
	private int samples;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK14$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$b() {
		return b;
	}

	@Override
	public final void set$b(double cv$value) {
		b = cv$value;
		fixedProbFlag$sample12 = false;
		fixedProbFlag$sample29 = false;
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final boolean get$fixedFlag$sample12() {
		return fixedFlag$sample12;
	}

	@Override
	public final void set$fixedFlag$sample12(boolean cv$value) {
		fixedFlag$sample12 = cv$value;
		fixedProbFlag$sample12 = (cv$value && fixedProbFlag$sample12);
		fixedProbFlag$sample29 = (cv$value && fixedProbFlag$sample29);
	}

	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	@Override
	public final void set$fixedFlag$sample29(boolean cv$value) {
		fixedFlag$sample29 = cv$value;
		fixedProbFlag$sample29 = (cv$value && fixedProbFlag$sample29);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample29 = false;
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
	public final boolean get$guard1() {
		return guard1;
	}

	@Override
	public final void set$guard1(boolean cv$value) {
		guard1 = cv$value;
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
	public final double get$logProbability$b() {
		return logProbability$b;
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
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	private final void logProbabilityValue$sample12() {
		if(!fixedProbFlag$sample12) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(b, 1.0, 1.0);
			logProbability$var9 = cv$distributionAccumulator;
			logProbability$b = cv$distributionAccumulator;
			boolean cv$guard$bias = false;
			logProbability$c = (logProbability$c + cv$distributionAccumulator);
			if(guard1) {
				cv$guard$bias = true;
				logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			}
			if((!guard1 && !cv$guard$bias))
				logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample12 = fixedFlag$sample12;
		} else {
			logProbability$var9 = logProbability$b;
			boolean cv$guard$bias = false;
			logProbability$c = (logProbability$c + logProbability$b);
			if(guard1) {
				cv$guard$bias = true;
				logProbability$bias = (logProbability$bias + logProbability$b);
			}
			if((!guard1 && !cv$guard$bias))
				logProbability$bias = (logProbability$bias + logProbability$b);
			logProbability$$model = (logProbability$$model + logProbability$b);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + logProbability$b);
		}
	}

	private final void logProbabilityValue$sample29() {
		if(!fixedProbFlag$sample29) {
			double cv$sampleAccumulator = 0.0;
			for(int var24 = 0; var24 < samples; var24 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[var24], bias));
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var25 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample29 = (fixedFlag$sample29 && fixedFlag$sample12);
		} else {
			logProbability$bernoulli = logProbability$var25;
			logProbability$flips = (logProbability$flips + logProbability$var25);
			logProbability$$model = (logProbability$$model + logProbability$var25);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var25);
		}
	}

	private final void sample12() {
		double cv$originalValue = b;
		double cv$originalProbability;
		double cv$var = ((b * b) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + b);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(b, 1.0, 1.0);
			if(guard1) {
				for(int var24 = 0; var24 < samples; var24 += 1)
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var24], b) + cv$accumulatedProbabilities);
			} else {
				double traceTempVariable$var18$5_2 = (b / 2);
				for(int var24 = 0; var24 < samples; var24 += 1)
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var24], traceTempVariable$var18$5_2) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		b = cv$proposedValue;
		c[0] = (cv$proposedValue / 2);
		if(guard1)
			bias = cv$proposedValue;
		else
			bias = c[0];
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
		if(guard1) {
			for(int var24 = 0; var24 < samples; var24 += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var24], cv$proposedValue) + cv$accumulatedProbabilities);
		} else {
			double traceTempVariable$var18$5_2 = (cv$proposedValue / 2);
			for(int var24 = 0; var24 < samples; var24 += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var24], traceTempVariable$var18$5_2) + cv$accumulatedProbabilities);
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			b = cv$originalValue;
			c[0] = (cv$originalValue / 2);
			if(guard1)
				bias = cv$originalValue;
			else
				bias = c[0];
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!guard1)
			c = new double[1];
		if(!setFlag$flips)
			flips = new boolean[length$flipsMeasured];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample12) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!guard1) {
				c[0] = (b / 2);
				bias = c[0];
			}
			bias = b;
		}
		if(!fixedFlag$sample29)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
							flips[var24] = DistributionSampling.sampleBernoulli(RNG$1, bias);
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample12) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!guard1) {
				c[0] = (b / 2);
				bias = c[0];
			}
			bias = b;
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample12) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!guard1) {
				c[0] = (b / 2);
				bias = c[0];
			}
			bias = b;
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample12)
			sample12();
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		samples = length$flipsMeasured;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var9 = 0.0;
		logProbability$bias = 0.0;
		logProbability$c = 0.0;
		if(!fixedProbFlag$sample12)
			logProbability$b = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$var25 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample12)
			logProbabilityValue$sample12();
		logProbabilityValue$sample29();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample12();
		logProbabilityValue$sample29();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample12();
		logProbabilityValue$sample29();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample12) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!guard1) {
				c[0] = (b / 2);
				bias = c[0];
			}
			bias = b;
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		for(int i = (samples - 1); i >= 0; i -= 1)
			flips[i] = flipsMeasured[i];
	}

	@Override
	public final void setIntermediates() {
		if(!guard1) {
			c[0] = (b / 2);
			bias = c[0];
		}
		bias = b;
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK14(boolean[] flipsMeasured, boolean guard1) {\n    int samples = flipsMeasured.length;\n        \n    double b = beta(1.0, 1).sample();\n    double bias;\n    if(guard1)\n      bias = b;\n    else {\n      double[] c = new double[1];\n      c[0] = b/2;\n      bias = c[0];\n    }\n        \n    Bernoulli bernoulli = bernoulli(bias);\n    boolean[] flips = bernoulli.sample(samples);\n\n    for(int i:[0..samples))\n        flips[i].observe(flipsMeasured[i]);\n}\n";
	}
}