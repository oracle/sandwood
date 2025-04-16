package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK15$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip1CoinMK15$CoreInterface {
	private double b;
	private double bias;
	private double[] c;
	private boolean fixedFlag$sample8 = false;
	private boolean fixedProbFlag$sample50 = false;
	private boolean fixedProbFlag$sample8 = false;
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
	private double logProbability$sample8;
	private double logProbability$var47;
	private double logProbability$var7;
	private int samples;
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
		fixedProbFlag$sample8 = false;
		fixedProbFlag$sample50 = false;
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final boolean get$fixedFlag$sample8() {
		return fixedFlag$sample8;
	}

	@Override
	public final void set$fixedFlag$sample8(boolean cv$value) {
		fixedFlag$sample8 = cv$value;
		fixedProbFlag$sample8 = (cv$value && fixedProbFlag$sample8);
		fixedProbFlag$sample50 = (cv$value && fixedProbFlag$sample50);
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

	private final void logProbabilityValue$sample50() {
		if(!fixedProbFlag$sample50) {
			double cv$sampleAccumulator = 0.0;
			for(int var46 = 0; var46 < samples; var46 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((flips[var46]?bias:(1.0 - bias))));
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var47 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample50 = fixedFlag$sample8;
		} else {
			logProbability$bernoulli = logProbability$var47;
			logProbability$flips = (logProbability$flips + logProbability$var47);
			logProbability$$model = (logProbability$$model + logProbability$var47);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var47);
		}
	}

	private final void logProbabilityValue$sample8() {
		if(!fixedProbFlag$sample8) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(b, 1.0, 1.0);
			logProbability$var7 = cv$distributionAccumulator;
			logProbability$sample8 = cv$distributionAccumulator;
			logProbability$b = (logProbability$b + cv$distributionAccumulator);
			if(guard1)
				logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			else {
				logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
				logProbability$c = (logProbability$c + cv$distributionAccumulator);
			}
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample8)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample8 = fixedFlag$sample8;
		} else {
			logProbability$var7 = logProbability$sample8;
			logProbability$b = (logProbability$b + logProbability$sample8);
			if(guard1)
				logProbability$bias = (logProbability$bias + logProbability$sample8);
			else {
				logProbability$bias = (logProbability$bias + logProbability$sample8);
				logProbability$c = (logProbability$c + logProbability$sample8);
			}
			logProbability$$model = (logProbability$$model + logProbability$sample8);
			if(fixedFlag$sample8)
				logProbability$$evidence = (logProbability$$evidence + logProbability$sample8);
		}
	}

	private final void sample8() {
		double cv$originalValue = b;
		double cv$originalProbability;
		double cv$var = ((b * b) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + b);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(b, 1.0, 1.0);
			if(guard1) {
				for(int var46 = 0; var46 < samples; var46 += 1)
					cv$accumulatedProbabilities = (Math.log((flips[var46]?b:(1.0 - b))) + cv$accumulatedProbabilities);
			} else {
				double reduceVar$var33$3 = ((b / 2) + c[1]);
				for(int var46 = 0; var46 < samples; var46 += 1)
					cv$accumulatedProbabilities = (Math.log((flips[var46]?reduceVar$var33$3:(1.0 - reduceVar$var33$3))) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		b = cv$proposedValue;
		if(guard1)
			bias = cv$proposedValue;
		else {
			c[0] = (cv$proposedValue / 2);
			c[1] = (cv$proposedValue / 2);
			bias = (c[0] + c[1]);
		}
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
		if(guard1) {
			for(int var46 = 0; var46 < samples; var46 += 1)
				cv$accumulatedProbabilities = (Math.log((flips[var46]?cv$proposedValue:(1.0 - cv$proposedValue))) + cv$accumulatedProbabilities);
		} else {
			double reduceVar$var33$3 = ((cv$proposedValue / 2) + c[1]);
			for(int var46 = 0; var46 < samples; var46 += 1)
				cv$accumulatedProbabilities = (Math.log((flips[var46]?reduceVar$var33$3:(1.0 - reduceVar$var33$3))) + cv$accumulatedProbabilities);
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			b = cv$originalValue;
			if(guard1)
				bias = cv$originalValue;
			else {
				c[0] = (cv$originalValue / 2);
				c[1] = (cv$originalValue / 2);
				bias = (c[0] + c[1]);
			}
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!guard1)
			c = new double[2];
		flips = new boolean[length$flipsMeasured];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample8) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(guard1)
				bias = b;
			else {
				c[0] = (b / 2);
				c[1] = (b / 2);
				bias = (c[0] + c[1]);
			}
		}
		for(int var46 = 0; var46 < samples; var46 += 1)
			flips[var46] = DistributionSampling.sampleBernoulli(RNG$, bias);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample8) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(guard1)
				bias = b;
			else {
				c[0] = (b / 2);
				c[1] = (b / 2);
				bias = (c[0] + c[1]);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample8) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(guard1)
				bias = b;
			else {
				c[0] = (b / 2);
				c[1] = (b / 2);
				bias = (c[0] + c[1]);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample8)
			sample8();
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		samples = length$flipsMeasured;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var7 = 0.0;
		logProbability$b = 0.0;
		logProbability$c = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample8)
			logProbability$sample8 = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample50)
			logProbability$var47 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample8)
			logProbabilityValue$sample8();
		logProbabilityValue$sample50();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample8();
		logProbabilityValue$sample50();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample8();
		logProbabilityValue$sample50();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample8) {
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(guard1)
				bias = b;
			else {
				c[0] = (b / 2);
				c[1] = (b / 2);
				bias = (c[0] + c[1]);
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i$var58 = (samples - 1); i$var58 >= 0; i$var58 -= 1)
			flips[i$var58] = flipsMeasured[i$var58];
	}

	@Override
	public final void setIntermediates() {
		if(fixedFlag$sample8) {
			if(guard1)
				bias = b;
			else {
				c[0] = (b / 2);
				c[1] = (b / 2);
				bias = (c[0] + c[1]);
			}
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
		     + "public model Flip1CoinMK15(boolean[] flipsMeasured, boolean guard1) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    double b = beta(1.0, 1).sample();\n"
		     + "    double bias;\n"
		     + "    if(guard1)\n"
		     + "      bias = b;\n"
		     + "    else {\n"
		     + "      double[] c = new double[2];\n"
		     + "      c[0] = b/2;\n"
		     + "      c[1] = b/2;\n"
		     + "      bias = reduce(c, 0, (i,j) -> {\n"
		     + "            return i + j;\n"
		     + "        });\n"
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