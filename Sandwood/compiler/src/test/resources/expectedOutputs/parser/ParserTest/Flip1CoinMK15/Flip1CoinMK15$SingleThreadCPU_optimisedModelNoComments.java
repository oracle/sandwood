package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK15$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip1CoinMK15$CoreInterface {
	private double b;
	private double bias;
	private double[] c;
	private boolean fixedFlag$sample12 = false;
	private boolean fixedFlag$sample40 = false;
	private boolean fixedProbFlag$sample12 = false;
	private boolean fixedProbFlag$sample40 = false;
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
	private double logProbability$var35;
	private double logProbability$var9;
	private int samples;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK15$SingleThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample40 = false;
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
		fixedProbFlag$sample40 = (cv$value && fixedProbFlag$sample40);
	}

	@Override
	public final boolean get$fixedFlag$sample40() {
		return fixedFlag$sample40;
	}

	@Override
	public final void set$fixedFlag$sample40(boolean cv$value) {
		fixedFlag$sample40 = cv$value;
		fixedProbFlag$sample40 = (cv$value && fixedProbFlag$sample40);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample40 = false;
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
			logProbability$c = (logProbability$c + cv$distributionAccumulator);
			logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample12 = fixedFlag$sample12;
		} else {
			logProbability$var9 = logProbability$b;
			logProbability$c = (logProbability$c + logProbability$b);
			logProbability$bias = (logProbability$bias + logProbability$b);
			logProbability$$model = (logProbability$$model + logProbability$b);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + logProbability$b);
		}
	}

	private final void logProbabilityValue$sample40() {
		if(!fixedProbFlag$sample40) {
			double cv$sampleAccumulator = 0.0;
			for(int var34 = 0; var34 < samples; var34 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[var34], bias));
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var35 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample40 = (fixedFlag$sample40 && fixedFlag$sample12);
		} else {
			logProbability$bernoulli = logProbability$var35;
			logProbability$flips = (logProbability$flips + logProbability$var35);
			logProbability$$model = (logProbability$$model + logProbability$var35);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var35);
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
			boolean guard$sample12bernoulli35 = false;
			if(guard1) {
				guard$sample12bernoulli35 = true;
				for(int var34 = 0; var34 < samples; var34 += 1)
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var34], b) + cv$accumulatedProbabilities);
			}
			if((!guard1 && (0 < c.length))) {
				double reduceVar$var28$2 = ((b / 2) + c[1]);
				if(!guard$sample12bernoulli35) {
					guard$sample12bernoulli35 = true;
					for(int var34 = 0; var34 < samples; var34 += 1)
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var34], reduceVar$var28$2) + cv$accumulatedProbabilities);
				}
				if(!guard$sample12bernoulli35) {
					double reduceVar$var28$3 = ((b / 2) + c[0]);
					for(int var34 = 0; var34 < samples; var34 += 1)
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var34], reduceVar$var28$3) + cv$accumulatedProbabilities);
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		b = cv$proposedValue;
		c[0] = (cv$proposedValue / 2);
		c[1] = (cv$proposedValue / 2);
		if(guard1)
			bias = cv$proposedValue;
		else
			bias = (c[0] + c[1]);
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
		boolean guard$sample12bernoulli35 = false;
		if(guard1) {
			guard$sample12bernoulli35 = true;
			for(int var34 = 0; var34 < samples; var34 += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var34], cv$proposedValue) + cv$accumulatedProbabilities);
		}
		if((!guard1 && (0 < c.length))) {
			double reduceVar$var28$2 = ((cv$proposedValue / 2) + c[1]);
			if(!guard$sample12bernoulli35) {
				guard$sample12bernoulli35 = true;
				for(int var34 = 0; var34 < samples; var34 += 1)
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var34], reduceVar$var28$2) + cv$accumulatedProbabilities);
			}
			if(!guard$sample12bernoulli35) {
				double reduceVar$var28$3 = ((cv$proposedValue / 2) + c[0]);
				for(int var34 = 0; var34 < samples; var34 += 1)
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[var34], reduceVar$var28$3) + cv$accumulatedProbabilities);
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			b = cv$originalValue;
			c[0] = (cv$originalValue / 2);
			c[1] = (cv$originalValue / 2);
			if(guard1)
				bias = cv$originalValue;
			else
				bias = (c[0] + c[1]);
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!guard1)
			c = new double[2];
		if(!setFlag$flips)
			flips = new boolean[length$flipsMeasured];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample12) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!guard1) {
				c[0] = (b / 2);
				c[1] = (b / 2);
				bias = (c[0] + c[1]);
			}
			bias = b;
		}
		if(!fixedFlag$sample40) {
			for(int var34 = 0; var34 < samples; var34 += 1)
				flips[var34] = DistributionSampling.sampleBernoulli(RNG$, bias);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample12) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!guard1) {
				c[0] = (b / 2);
				c[1] = (b / 2);
				bias = (c[0] + c[1]);
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
				c[1] = (b / 2);
				bias = (c[0] + c[1]);
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
		logProbability$c = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample12)
			logProbability$b = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample40)
			logProbability$var35 = 0.0;
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
		logProbabilityValue$sample40();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample12();
		logProbabilityValue$sample40();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample12();
		logProbabilityValue$sample40();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample12) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!guard1) {
				c[0] = (b / 2);
				c[1] = (b / 2);
				bias = (c[0] + c[1]);
			}
			bias = b;
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		for(int i$var39 = (samples - 1); i$var39 >= 0; i$var39 -= 1)
			flips[i$var39] = flipsMeasured[i$var39];
	}

	@Override
	public final void setIntermediates() {
		if(!guard1) {
			c[0] = (b / 2);
			c[1] = (b / 2);
			bias = (c[0] + c[1]);
		}
		bias = b;
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK15(boolean[] flipsMeasured, boolean guard1) {\n    int samples = flipsMeasured.length;\n        \n    double b = beta(1.0, 1).sample();\n    double bias;\n    if(guard1)\n      bias = b;\n    else {\n      double[] c = new double[2];\n      c[0] = b/2;\n      c[1] = b/2;\n      bias = reduce(c, 0, (i,j) -> {\n            return i + j;\n        });\n    }\n        \n    Bernoulli bernoulli = bernoulli(bias);\n    boolean[] flips = bernoulli.sample(samples);\n\n    for(int i:[0..samples))\n        flips[i].observe(flipsMeasured[i]);\n}\n";
	}
}