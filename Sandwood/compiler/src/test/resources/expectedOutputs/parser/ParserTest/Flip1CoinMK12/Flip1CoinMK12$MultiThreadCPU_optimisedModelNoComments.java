package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK12$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK12$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample15 = false;
	private boolean fixedFlag$sample24 = false;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample41 = false;
	private boolean fixedProbFlag$sample15 = false;
	private boolean fixedProbFlag$sample24 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample41 = false;
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
	private double logProbability$sample15;
	private double logProbability$sample24;
	private double logProbability$sample31;
	private double logProbability$var10;
	private double logProbability$var11;
	private double logProbability$var17;
	private double logProbability$var20;
	private double logProbability$var24;
	private double logProbability$var27;
	private double logProbability$var35;
	private int samples;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;
	private double var11;
	private double var20;
	private double var27;

	public Flip1CoinMK12$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double cv$value) {
		bias = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample15() {
		return fixedFlag$sample15;
	}

	@Override
	public final void set$fixedFlag$sample15(boolean cv$value) {
		fixedFlag$sample15 = cv$value;
		fixedProbFlag$sample15 = (cv$value && fixedProbFlag$sample15);
		fixedProbFlag$sample41 = (cv$value && fixedProbFlag$sample41);
	}

	@Override
	public final boolean get$fixedFlag$sample24() {
		return fixedFlag$sample24;
	}

	@Override
	public final void set$fixedFlag$sample24(boolean cv$value) {
		fixedFlag$sample24 = cv$value;
		fixedProbFlag$sample24 = (cv$value && fixedProbFlag$sample24);
		fixedProbFlag$sample41 = (cv$value && fixedProbFlag$sample41);
	}

	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	@Override
	public final void set$fixedFlag$sample31(boolean cv$value) {
		fixedFlag$sample31 = cv$value;
		fixedProbFlag$sample31 = (cv$value && fixedProbFlag$sample31);
		fixedProbFlag$sample41 = (cv$value && fixedProbFlag$sample41);
	}

	@Override
	public final boolean get$fixedFlag$sample41() {
		return fixedFlag$sample41;
	}

	@Override
	public final void set$fixedFlag$sample41(boolean cv$value) {
		fixedFlag$sample41 = cv$value;
		fixedProbFlag$sample41 = (cv$value && fixedProbFlag$sample41);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
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

	private final void logProbabilityValue$sample15() {
		if(!fixedProbFlag$sample15) {
			double cv$accumulator = 0.0;
			if(guard1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(var11, 1.0, 1.0);
				cv$accumulator = cv$distributionAccumulator;
				logProbability$var10 = cv$distributionAccumulator;
				logProbability$sample15 = cv$distributionAccumulator;
			}
			logProbability$var11 = (logProbability$var11 + cv$accumulator);
			if(guard1)
				logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample15 = fixedFlag$sample15;
		} else {
			double cv$accumulator = 0.0;
			if(guard1) {
				cv$accumulator = logProbability$sample15;
				logProbability$var10 = logProbability$sample15;
			}
			logProbability$var11 = (logProbability$var11 + cv$accumulator);
			if(guard1)
				logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample24() {
		if(!fixedProbFlag$sample24) {
			double cv$accumulator = 0.0;
			if((!guard1 && (guard2 <= 2))) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta((var20 * 2), 1.0, 1.0);
				cv$accumulator = cv$distributionAccumulator;
				logProbability$var17 = cv$distributionAccumulator;
				logProbability$sample24 = cv$distributionAccumulator;
			}
			logProbability$var20 = (logProbability$var20 + cv$accumulator);
			if((!guard1 && (guard2 <= 2)))
				logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample24 = fixedFlag$sample24;
		} else {
			double cv$accumulator = 0.0;
			if((!guard1 && (guard2 <= 2))) {
				cv$accumulator = logProbability$sample24;
				logProbability$var17 = logProbability$sample24;
			}
			logProbability$var20 = (logProbability$var20 + cv$accumulator);
			if((!guard1 && (guard2 <= 2)))
				logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!fixedProbFlag$sample31) {
			double cv$accumulator = 0.0;
			if((!guard1 && !(guard2 <= 2))) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta((var27 * 3), 1.0, 1.0);
				cv$accumulator = cv$distributionAccumulator;
				logProbability$var24 = cv$distributionAccumulator;
				logProbability$sample31 = cv$distributionAccumulator;
			}
			logProbability$var27 = (logProbability$var27 + cv$accumulator);
			if((!guard1 && !(guard2 <= 2)))
				logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample31 = fixedFlag$sample31;
		} else {
			double cv$accumulator = 0.0;
			if((!guard1 && !(guard2 <= 2))) {
				cv$accumulator = logProbability$sample31;
				logProbability$var24 = logProbability$sample31;
			}
			logProbability$var27 = (logProbability$var27 + cv$accumulator);
			if((!guard1 && !(guard2 <= 2)))
				logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample41() {
		if(!fixedProbFlag$sample41) {
			double cv$sampleAccumulator = 0.0;
			for(int var34 = 0; var34 < samples; var34 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[var34], bias));
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var35 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample41 = (((fixedFlag$sample41 && fixedFlag$sample15) && fixedFlag$sample24) && fixedFlag$sample31);
		} else {
			logProbability$bernoulli = logProbability$var35;
			logProbability$flips = (logProbability$flips + logProbability$var35);
			logProbability$$model = (logProbability$$model + logProbability$var35);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var35);
		}
	}

	private final void sample15() {
		int cv$sum = 0;
		int cv$count = 0;
		for(int var34 = 0; var34 < samples; var34 += 1) {
			cv$count = (cv$count + 1);
			if(flips[var34])
				cv$sum = (cv$sum + 1);
		}
		var11 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias = var11;
	}

	private final void sample24() {
		double cv$originalValue = (var20 * 2);
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$originalValue, 1.0, 1.0);
			for(int var34 = 0; var34 < samples; var34 += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var34], var20) + cv$accumulatedProbabilities);
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		var20 = (cv$proposedValue / 2);
		bias = var20;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
		for(int var34 = 0; var34 < samples; var34 += 1)
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var34], var20) + cv$accumulatedProbabilities);
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			var20 = (cv$originalValue / 2);
			bias = var20;
		}
	}

	private final void sample31() {
		double cv$originalValue = (var27 * 3);
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$originalValue, 1.0, 1.0);
			for(int var34 = 0; var34 < samples; var34 += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var34], var27) + cv$accumulatedProbabilities);
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		var27 = (cv$proposedValue / 3);
		bias = var27;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
		for(int var34 = 0; var34 < samples; var34 += 1)
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var34], var27) + cv$accumulatedProbabilities);
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			var27 = (cv$originalValue / 3);
			bias = var27;
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
			if(!fixedFlag$sample15) {
				var11 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
				bias = var11;
			}
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample24) {
					var20 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
					bias = var20;
				}
			} else {
				if(!fixedFlag$sample31) {
					var27 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
					bias = var27;
				}
			}
		}
		if(!fixedFlag$sample41)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$var34, int forEnd$var34, int threadID$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var34 = forStart$var34; var34 < forEnd$var34; var34 += 1)
							flips[var34] = DistributionSampling.sampleBernoulli(RNG$1, bias);
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(guard1) {
			if(!fixedFlag$sample15) {
				var11 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
				bias = var11;
			}
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample24) {
					var20 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
					bias = var20;
				}
			} else {
				if(!fixedFlag$sample31) {
					var27 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
					bias = var27;
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(guard1) {
			if(!fixedFlag$sample15) {
				var11 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
				bias = var11;
			}
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample24) {
					var20 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
					bias = var20;
				}
			} else {
				if(!fixedFlag$sample31) {
					var27 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
					bias = var27;
				}
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(guard1) {
			if(!fixedFlag$sample15)
				sample15();
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample24)
					sample24();
			} else {
				if(!fixedFlag$sample31)
					sample31();
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
		logProbability$var10 = 0.0;
		logProbability$var11 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample15)
			logProbability$sample15 = 0.0;
		logProbability$var17 = 0.0;
		logProbability$var20 = 0.0;
		if(!fixedProbFlag$sample24)
			logProbability$sample24 = 0.0;
		logProbability$var24 = 0.0;
		logProbability$var27 = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$sample31 = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample41)
			logProbability$var35 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample15)
			logProbabilityValue$sample15();
		if(fixedFlag$sample24)
			logProbabilityValue$sample24();
		if(fixedFlag$sample31)
			logProbabilityValue$sample31();
		logProbabilityValue$sample41();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample15();
		logProbabilityValue$sample24();
		logProbabilityValue$sample31();
		logProbabilityValue$sample41();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample15();
		logProbabilityValue$sample24();
		logProbabilityValue$sample31();
		logProbabilityValue$sample41();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(guard1) {
			if(!fixedFlag$sample15) {
				var11 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
				bias = var11;
			}
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample24) {
					var20 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
					bias = var20;
				}
			} else {
				if(!fixedFlag$sample31) {
					var27 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
					bias = var27;
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
			bias = var11;
		else {
			double var28;
			if((guard2 <= 2))
				var28 = var20;
			else
				var28 = var27;
			bias = var28;
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK12(boolean[] flipsMeasured, boolean guard1, int guard2) {\n    int samples = flipsMeasured.length;\n        \n    double bias;\n    if(guard1)\n      bias = beta(1.0, 1).sample();\n    else { \n        if(guard2 <= 2) {\n            bias = beta(1.0, 1).sample()/2;\n        } else\n            bias = beta(1.0, 1).sample()/3;\n    }\n        \n    Bernoulli bernoulli = bernoulli(bias);\n    boolean[] flips = bernoulli.sample(samples);\n\n    for(int i:[0..samples))\n        flips[i].observe(flipsMeasured[i]);\n}\n";
	}
}