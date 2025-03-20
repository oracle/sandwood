package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK12$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK12$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample16 = false;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample52 = false;
	private boolean fixedProbFlag$sample16 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample52 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private boolean guard1;
	private int guard2;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$sample16;
	private double logProbability$sample28;
	private double logProbability$sample35;
	private double logProbability$var13;
	private double logProbability$var14;
	private double logProbability$var23;
	private double logProbability$var26;
	private double logProbability$var30;
	private double logProbability$var33;
	private double logProbability$var48;
	private int samples;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;
	private double var14;
	private double var26;
	private double var33;

	public Flip1CoinMK12$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final boolean get$fixedFlag$sample16() {
		return fixedFlag$sample16;
	}

	@Override
	public final void set$fixedFlag$sample16(boolean cv$value) {
		fixedFlag$sample16 = cv$value;
		fixedProbFlag$sample16 = (cv$value && fixedProbFlag$sample16);
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		fixedFlag$sample28 = cv$value;
		fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		fixedFlag$sample35 = cv$value;
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
	}

	@Override
	public final boolean get$fixedFlag$sample52() {
		return fixedFlag$sample52;
	}

	@Override
	public final void set$fixedFlag$sample52(boolean cv$value) {
		fixedFlag$sample52 = cv$value;
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample52 = false;
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
	public final int get$guard2() {
		return guard2;
	}

	@Override
	public final void set$guard2(int cv$value) {
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

	private final void logProbabilityValue$sample16() {
		if(!fixedProbFlag$sample16) {
			double cv$accumulator = 0.0;
			if(guard1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(var14, 1.0, 1.0);
				cv$accumulator = cv$distributionAccumulator;
				logProbability$var13 = cv$distributionAccumulator;
				logProbability$sample16 = cv$distributionAccumulator;
			}
			logProbability$var14 = (logProbability$var14 + cv$accumulator);
			if(guard1)
				logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample16 = fixedFlag$sample16;
		} else {
			double cv$accumulator = 0.0;
			if(guard1) {
				cv$accumulator = logProbability$sample16;
				logProbability$var13 = logProbability$sample16;
			}
			logProbability$var14 = (logProbability$var14 + cv$accumulator);
			if(guard1)
				logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$accumulator = 0.0;
			if((!guard1 && (guard2 <= 2))) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta((var26 * 2), 1.0, 1.0);
				cv$accumulator = cv$distributionAccumulator;
				logProbability$var23 = cv$distributionAccumulator;
				logProbability$sample28 = cv$distributionAccumulator;
			}
			logProbability$var26 = (logProbability$var26 + cv$accumulator);
			if((!guard1 && (guard2 <= 2)))
				logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			double cv$accumulator = 0.0;
			if((!guard1 && (guard2 <= 2))) {
				cv$accumulator = logProbability$sample28;
				logProbability$var23 = logProbability$sample28;
			}
			logProbability$var26 = (logProbability$var26 + cv$accumulator);
			if((!guard1 && (guard2 <= 2)))
				logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double cv$accumulator = 0.0;
			if((!guard1 && !(guard2 <= 2))) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta((var33 * 3), 1.0, 1.0);
				cv$accumulator = cv$distributionAccumulator;
				logProbability$var30 = cv$distributionAccumulator;
				logProbability$sample35 = cv$distributionAccumulator;
			}
			logProbability$var33 = (logProbability$var33 + cv$accumulator);
			if((!guard1 && !(guard2 <= 2)))
				logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample35 = fixedFlag$sample35;
		} else {
			double cv$accumulator = 0.0;
			if((!guard1 && !(guard2 <= 2))) {
				cv$accumulator = logProbability$sample35;
				logProbability$var30 = logProbability$sample35;
			}
			logProbability$var33 = (logProbability$var33 + cv$accumulator);
			if((!guard1 && !(guard2 <= 2)))
				logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!fixedProbFlag$sample52) {
			double cv$sampleAccumulator = 0.0;
			for(int var47 = 0; var47 < samples; var47 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[var47], bias));
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var48 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample52 = (((fixedFlag$sample52 && fixedFlag$sample16) && fixedFlag$sample28) && fixedFlag$sample35);
		} else {
			logProbability$bernoulli = logProbability$var48;
			logProbability$flips = (logProbability$flips + logProbability$var48);
			logProbability$$model = (logProbability$$model + logProbability$var48);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var48);
		}
	}

	private final void sample16() {
		int cv$sum = 0;
		int cv$count = 0;
		for(int var47 = 0; var47 < samples; var47 += 1) {
			cv$count = (cv$count + 1);
			if(flips[var47])
				cv$sum = (cv$sum + 1);
		}
		var14 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias = var14;
	}

	private final void sample28() {
		double cv$originalValue = (var26 * 2);
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$originalValue, 1.0, 1.0);
			for(int var47 = 0; var47 < samples; var47 += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var47], var26) + cv$accumulatedProbabilities);
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		var26 = (cv$proposedValue / 2);
		bias = var26;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
		for(int var47 = 0; var47 < samples; var47 += 1)
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var47], var26) + cv$accumulatedProbabilities);
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			var26 = (cv$originalValue / 2);
			bias = var26;
		}
	}

	private final void sample35() {
		double cv$originalValue = (var33 * 3);
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$originalValue, 1.0, 1.0);
			for(int var47 = 0; var47 < samples; var47 += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var47], var33) + cv$accumulatedProbabilities);
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		var33 = (cv$proposedValue / 3);
		bias = var33;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
		for(int var47 = 0; var47 < samples; var47 += 1)
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var47], var33) + cv$accumulatedProbabilities);
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			var33 = (cv$originalValue / 3);
			bias = var33;
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
		if(guard1) {
			if(!fixedFlag$sample16) {
				var14 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
				bias = var14;
			}
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample28) {
					var26 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
					bias = var26;
				}
			} else {
				if(!fixedFlag$sample35) {
					var33 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
					bias = var33;
				}
			}
		}
		if(!fixedFlag$sample52)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$var47, int forEnd$var47, int threadID$var47, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var47 = forStart$var47; var47 < forEnd$var47; var47 += 1)
							flips[var47] = DistributionSampling.sampleBernoulli(RNG$1, bias);
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(guard1) {
			if(!fixedFlag$sample16) {
				var14 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
				bias = var14;
			}
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample28) {
					var26 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
					bias = var26;
				}
			} else {
				if(!fixedFlag$sample35) {
					var33 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
					bias = var33;
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(guard1) {
			if(!fixedFlag$sample16) {
				var14 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
				bias = var14;
			}
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample28) {
					var26 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
					bias = var26;
				}
			} else {
				if(!fixedFlag$sample35) {
					var33 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
					bias = var33;
				}
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(guard1) {
			if(!fixedFlag$sample16)
				sample16();
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample28)
					sample28();
			} else {
				if(!fixedFlag$sample35)
					sample35();
			}
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
		logProbability$var13 = 0.0;
		logProbability$var14 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample16)
			logProbability$sample16 = 0.0;
		logProbability$var23 = 0.0;
		logProbability$var26 = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$sample28 = 0.0;
		logProbability$var30 = 0.0;
		logProbability$var33 = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$sample35 = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample52)
			logProbability$var48 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample16)
			logProbabilityValue$sample16();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		logProbabilityValue$sample52();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample16();
		logProbabilityValue$sample28();
		logProbabilityValue$sample35();
		logProbabilityValue$sample52();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample16();
		logProbabilityValue$sample28();
		logProbabilityValue$sample35();
		logProbabilityValue$sample52();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(guard1) {
			if(!fixedFlag$sample16) {
				var14 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
				bias = var14;
			}
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample28) {
					var26 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
					bias = var26;
				}
			} else {
				if(!fixedFlag$sample35) {
					var33 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
					bias = var33;
				}
			}
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
		if(guard1)
			bias = var14;
		else {
			double var34;
			if((guard2 <= 2))
				var34 = var26;
			else
				var34 = var33;
			bias = var34;
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
		     + "public model Flip1CoinMK12(boolean[] flipsMeasured, boolean guard1, int guard2) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    double bias;\n"
		     + "    if(guard1)\n"
		     + "      bias = beta(1.0, 1).sample();\n"
		     + "    else { \n"
		     + "        if(guard2 <= 2) {\n"
		     + "            bias = beta(1.0, 1).sample()/2;\n"
		     + "        } else\n"
		     + "            bias = beta(1.0, 1).sample()/3;\n"
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