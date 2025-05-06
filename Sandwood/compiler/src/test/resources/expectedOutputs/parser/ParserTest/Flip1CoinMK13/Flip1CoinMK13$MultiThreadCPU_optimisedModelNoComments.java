package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK13$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK13$CoreInterface {
	private double b;
	private double bias;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample40 = false;
	private boolean fixedProbFlag$sample9 = false;
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
	private double logProbability$sample9;
	private double logProbability$var36;
	private double logProbability$var8;
	private int samples;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK13$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$b() {
		return b;
	}

	@Override
	public final void set$b(double cv$value) {
		b = cv$value;
		fixedProbFlag$sample9 = false;
		fixedProbFlag$sample40 = false;
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final boolean get$fixedFlag$sample9() {
		return fixedFlag$sample9;
	}

	@Override
	public final void set$fixedFlag$sample9(boolean cv$value) {
		fixedFlag$sample9 = cv$value;
		fixedProbFlag$sample9 = (cv$value && fixedProbFlag$sample9);
		fixedProbFlag$sample40 = (cv$value && fixedProbFlag$sample40);
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

	private final void logProbabilityValue$sample40() {
		if(!fixedProbFlag$sample40) {
			double cv$sampleAccumulator = 0.0;
			for(int var35 = 0; var35 < samples; var35 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((flips[var35]?bias:(1.0 - bias))));
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var36 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample40 = fixedFlag$sample9;
		} else {
			logProbability$bernoulli = logProbability$var36;
			logProbability$flips = (logProbability$flips + logProbability$var36);
			logProbability$$model = (logProbability$$model + logProbability$var36);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var36);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!fixedProbFlag$sample9) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(b, 1.0, 1.0);
			logProbability$var8 = cv$distributionAccumulator;
			logProbability$sample9 = cv$distributionAccumulator;
			boolean cv$guard$bias = false;
			logProbability$b = (logProbability$b + cv$distributionAccumulator);
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
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample9 = fixedFlag$sample9;
		} else {
			logProbability$var8 = logProbability$sample9;
			boolean cv$guard$bias = false;
			logProbability$b = (logProbability$b + logProbability$sample9);
			if(guard1)
				logProbability$bias = (logProbability$bias + logProbability$sample9);
			else {
				if(guard2) {
					cv$guard$bias = true;
					logProbability$bias = (logProbability$bias + logProbability$sample9);
				}
				if((!guard2 && !cv$guard$bias))
					logProbability$bias = (logProbability$bias + logProbability$sample9);
			}
			logProbability$$model = (logProbability$$model + logProbability$sample9);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + logProbability$sample9);
		}
	}

	private final void sample9() {
		double cv$originalValue = b;
		double cv$originalProbability;
		double cv$var = ((b * b) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + b);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(b, 1.0, 1.0);
			if(guard1) {
				for(int var35 = 0; var35 < samples; var35 += 1)
					cv$accumulatedProbabilities = (Math.log((flips[var35]?b:(1.0 - b))) + cv$accumulatedProbabilities);
			} else {
				if(guard2) {
					double traceTempVariable$bias$5_2 = (b / 2);
					for(int var35 = 0; var35 < samples; var35 += 1)
						cv$accumulatedProbabilities = (Math.log((flips[var35]?traceTempVariable$bias$5_2:(1.0 - traceTempVariable$bias$5_2))) + cv$accumulatedProbabilities);
				} else {
					double traceTempVariable$bias$6_2 = (b / 3);
					for(int var35 = 0; var35 < samples; var35 += 1)
						cv$accumulatedProbabilities = (Math.log((flips[var35]?traceTempVariable$bias$6_2:(1.0 - traceTempVariable$bias$6_2))) + cv$accumulatedProbabilities);
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
			for(int var35 = 0; var35 < samples; var35 += 1)
				cv$accumulatedProbabilities = (Math.log((flips[var35]?cv$proposedValue:(1.0 - cv$proposedValue))) + cv$accumulatedProbabilities);
		} else {
			if(guard2) {
				double traceTempVariable$bias$5_2 = (cv$proposedValue / 2);
				for(int var35 = 0; var35 < samples; var35 += 1)
					cv$accumulatedProbabilities = (Math.log((flips[var35]?traceTempVariable$bias$5_2:(1.0 - traceTempVariable$bias$5_2))) + cv$accumulatedProbabilities);
			} else {
				double traceTempVariable$bias$6_2 = (cv$proposedValue / 3);
				for(int var35 = 0; var35 < samples; var35 += 1)
					cv$accumulatedProbabilities = (Math.log((flips[var35]?traceTempVariable$bias$6_2:(1.0 - traceTempVariable$bias$6_2))) + cv$accumulatedProbabilities);
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
		flips = new boolean[length$flipsMeasured];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample9) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(guard1)
				bias = b;
			else {
				if(guard2)
					bias = (b / 2);
				else
					bias = (b / 3);
			}
		}
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1)
						flips[var35] = DistributionSampling.sampleBernoulli(RNG$1, bias);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample9)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(guard1) {
			if(!fixedFlag$sample9)
				bias = b;
		} else {
			if(guard2)
				bias = (b / 2);
			else
				bias = (b / 3);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample9)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(guard1) {
			if(!fixedFlag$sample9)
				bias = b;
		} else {
			if(guard2)
				bias = (b / 2);
			else
				bias = (b / 3);
		}
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1)
						flips[var35] = DistributionSampling.sampleBernoulli(RNG$1, bias);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample9) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(guard1)
				bias = b;
			else {
				if(guard2)
					bias = (b / 2);
				else
					bias = (b / 3);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample9)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(guard1) {
			if(!fixedFlag$sample9)
				bias = b;
		} else {
			if(guard2)
				bias = (b / 2);
			else
				bias = (b / 3);
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample9)
			sample9();
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		samples = length$flipsMeasured;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var8 = 0.0;
		logProbability$b = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample9)
			logProbability$sample9 = Double.NaN;
		logProbability$bernoulli = Double.NaN;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample40)
			logProbability$var36 = Double.NaN;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample9)
			logProbabilityValue$sample9();
		logProbabilityValue$sample40();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample40();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample40();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i = (samples - 1); i >= 0; i -= 1)
			flips[i] = flipsMeasured[i];
	}

	@Override
	public final void setIntermediates() {
		if(guard1) {
			if(fixedFlag$sample9)
				bias = b;
		} else {
			if(guard2)
				bias = (b / 2);
			else
				bias = (b / 3);
		}
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
		     + "public model Flip1CoinMK13(boolean[] flipsMeasured, boolean guard1, boolean guard2) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    double b = beta(1.0, 1).sample();\n"
		     + "    double bias;\n"
		     + "    if(guard1)\n"
		     + "      bias = b;\n"
		     + "    else { \n"
		     + "        if(guard2) {\n"
		     + "            bias = b/2;\n"
		     + "        } else\n"
		     + "            bias = b/3;\n"
		     + "    }\n"
		     + "        \n"
		     + "    Bernoulli bernoulli = bernoulli(bias);\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "\n"
		     + "    for(int i:[0..samples))\n"
		     + "        flips[i].observe(flipsMeasured[i]);\n"
		     + "}\n"
		     + "";
	}
}