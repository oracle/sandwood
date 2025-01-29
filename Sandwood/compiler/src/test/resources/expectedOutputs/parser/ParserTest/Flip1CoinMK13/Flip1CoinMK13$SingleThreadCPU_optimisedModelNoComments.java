package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK13$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip1CoinMK13$CoreInterface {
	private double b;
	private double bias;
	private boolean fixedFlag$sample13 = false;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedProbFlag$sample13 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private boolean guard1;
	private boolean guard2;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$var10;
	private double logProbability$var23;
	private int samples;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK13$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$b() {
		return b;
	}

	@Override
	public final void set$b(double cv$value) {
		b = cv$value;
		fixedProbFlag$sample13 = false;
		fixedProbFlag$sample29 = false;
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final boolean get$fixedFlag$sample13() {
		return fixedFlag$sample13;
	}

	@Override
	public final void set$fixedFlag$sample13(boolean cv$value) {
		fixedFlag$sample13 = cv$value;
		fixedProbFlag$sample13 = (cv$value && fixedProbFlag$sample13);
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
	public final boolean get$guard2() {
		return guard2;
	}

	@Override
	public final void set$guard2(boolean cv$value) {
		guard2 = cv$value;
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

	private final void logProbabilityValue$sample13() {
		if(!fixedProbFlag$sample13) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(b, 1.0, 1.0);
			logProbability$var10 = cv$distributionAccumulator;
			logProbability$b = cv$distributionAccumulator;
			boolean cv$guard$bias = false;
			if(guard1)
				logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			else {
				if(guard2) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
				}
				if((!guard2 && !cv$guard$bias))
					logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			}
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample13)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample13 = fixedFlag$sample13;
		} else {
			logProbability$var10 = logProbability$b;
			boolean cv$guard$bias = false;
			if(guard1)
				logProbability$bias = (logProbability$bias + logProbability$b);
			else {
				if(guard2) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + logProbability$b);
				}
				if((!guard2 && !cv$guard$bias))
					logProbability$bias = (logProbability$bias + logProbability$b);
			}
			logProbability$$model = (logProbability$$model + logProbability$b);
			if(fixedFlag$sample13)
				logProbability$$evidence = (logProbability$$evidence + logProbability$b);
		}
	}

	private final void logProbabilityValue$sample29() {
		if(!fixedProbFlag$sample29) {
			double cv$sampleAccumulator = 0.0;
			for(int var22 = 0; var22 < samples; var22 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[var22], bias));
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var23 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample29 = (fixedFlag$sample29 && fixedFlag$sample13);
		} else {
			logProbability$bernoulli = logProbability$var23;
			logProbability$flips = (logProbability$flips + logProbability$var23);
			logProbability$$model = (logProbability$$model + logProbability$var23);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var23);
		}
	}

	private final void sample13() {
		double cv$originalValue = b;
		double cv$originalProbability;
		double cv$var = ((b * b) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + b);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(b, 1.0, 1.0);
			if(guard1) {
				for(int var22 = 0; var22 < samples; var22 += 1)
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var22], b) + cv$accumulatedProbabilities);
			} else {
				if(guard2) {
					double traceTempVariable$bias$5_2 = (b / 2);
					for(int var22 = 0; var22 < samples; var22 += 1)
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var22], traceTempVariable$bias$5_2) + cv$accumulatedProbabilities);
				} else {
					double traceTempVariable$bias$6_2 = (b / 3);
					for(int var22 = 0; var22 < samples; var22 += 1)
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var22], traceTempVariable$bias$6_2) + cv$accumulatedProbabilities);
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		b = cv$proposedValue;
		if(guard1)
			bias = cv$proposedValue;
		else {
			if(guard2)
				bias = (cv$proposedValue / 2);
			else
				bias = (cv$proposedValue / 3);
		}
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
		if(guard1) {
			for(int var22 = 0; var22 < samples; var22 += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var22], cv$proposedValue) + cv$accumulatedProbabilities);
		} else {
			if(guard2) {
				double traceTempVariable$bias$5_2 = (cv$proposedValue / 2);
				for(int var22 = 0; var22 < samples; var22 += 1)
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var22], traceTempVariable$bias$5_2) + cv$accumulatedProbabilities);
			} else {
				double traceTempVariable$bias$6_2 = (cv$proposedValue / 3);
				for(int var22 = 0; var22 < samples; var22 += 1)
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var22], traceTempVariable$bias$6_2) + cv$accumulatedProbabilities);
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			b = cv$originalValue;
			if(guard1)
				bias = cv$originalValue;
			else {
				if(guard2)
					bias = (cv$originalValue / 2);
				else
					bias = (cv$originalValue / 3);
			}
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!setFlag$flips)
			flips = new boolean[length$flipsMeasured];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample13) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!guard1) {
				if(guard2)
					bias = (b / 2);
				else
					bias = (b / 3);
			}
			bias = b;
		}
		if(!fixedFlag$sample29) {
			for(int var22 = 0; var22 < samples; var22 += 1)
				flips[var22] = DistributionSampling.sampleBernoulli(RNG$, bias);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample13) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!guard1) {
				if(guard2)
					bias = (b / 2);
				else
					bias = (b / 3);
			}
			bias = b;
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample13) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!guard1) {
				if(guard2)
					bias = (b / 2);
				else
					bias = (b / 3);
			}
			bias = b;
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample13)
			sample13();
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		samples = length$flipsMeasured;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var10 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample13)
			logProbability$b = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$var23 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample13)
			logProbabilityValue$sample13();
		logProbabilityValue$sample29();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample13();
		logProbabilityValue$sample29();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample13();
		logProbabilityValue$sample29();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample13) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!guard1) {
				if(guard2)
					bias = (b / 2);
				else
					bias = (b / 3);
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
		bias = b;
		if(!guard1) {
			double var16;
			if(guard2)
				var16 = (b / 2);
			else
				var16 = (b / 3);
			bias = var16;
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK13(boolean[] flipsMeasured, boolean guard1, boolean guard2) {\n    int samples = flipsMeasured.length;\n        \n    double b = beta(1.0, 1).sample();\n    double bias;\n    if(guard1)\n      bias = b;\n    else { \n        if(guard2) {\n            bias = b/2;\n        } else\n            bias = b/3;\n    }\n        \n    Bernoulli bernoulli = bernoulli(bias);\n    boolean[] flips = bernoulli.sample(samples);\n\n    for(int i:[0..samples))\n        flips[i].observe(flipsMeasured[i]);\n}\n";
	}
}