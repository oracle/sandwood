package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip2CoinsMK7$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip2CoinsMK7$CoreInterface {
	private double a;
	private double b;
	private double[] bias;
	private int coins;
	private boolean fixedFlag$sample18 = false;
	private boolean fixedFlag$sample46 = false;
	private boolean fixedProbFlag$sample18 = false;
	private boolean fixedProbFlag$sample46 = false;
	private boolean[][] flips;
	private boolean[][] flipsMeasured;
	private int[] length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample18;
	private double[] logProbability$sample46;
	private double[] logProbability$var17;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;

	public Flip2CoinsMK7$SingleThreadCPU(ExecutionTarget target) {
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
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		setFlag$bias = true;
		fixedProbFlag$sample18 = false;
		fixedProbFlag$sample46 = false;
	}

	@Override
	public final int get$coins() {
		return coins;
	}

	@Override
	public final boolean get$fixedFlag$sample18() {
		return fixedFlag$sample18;
	}

	@Override
	public final void set$fixedFlag$sample18(boolean cv$value) {
		fixedFlag$sample18 = cv$value;
		fixedProbFlag$sample18 = (cv$value && fixedProbFlag$sample18);
		fixedProbFlag$sample46 = (cv$value && fixedProbFlag$sample46);
	}

	@Override
	public final boolean get$fixedFlag$sample46() {
		return fixedFlag$sample46;
	}

	@Override
	public final void set$fixedFlag$sample46(boolean cv$value) {
		fixedFlag$sample46 = cv$value;
		fixedProbFlag$sample46 = (cv$value && fixedProbFlag$sample46);
	}

	@Override
	public final boolean[][] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[][] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample46 = false;
	}

	@Override
	public final boolean[][] get$flipsMeasured() {
		return flipsMeasured;
	}

	@Override
	public final void set$flipsMeasured(boolean[][] cv$value) {
		flipsMeasured = cv$value;
	}

	@Override
	public final int[] get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int[] cv$value) {
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
	public final double[] get$logProbability$bernoulli() {
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

	private final void logProbabilityValue$sample18() {
		if(!fixedProbFlag$sample18) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < coins; i += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta((1 - bias[i]), a, b);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var17[i] = cv$distributionAccumulator;
				logProbability$sample18[i] = cv$distributionAccumulator;
			}
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample18 = fixedFlag$sample18;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < coins; i += 1) {
				double cv$rvAccumulator = logProbability$sample18[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var17[i] = cv$rvAccumulator;
			}
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample46() {
		if(!fixedProbFlag$sample46) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < coins; j += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var45 = 0; var45 < length$flipsMeasured[j]; var45 += 1)
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[j][var45], bias[j]));
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$bernoulli[j] = cv$sampleAccumulator;
				logProbability$sample46[j] = cv$sampleAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample46 = (fixedFlag$sample46 && fixedFlag$sample18);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < coins; j += 1) {
				double cv$rvAccumulator = logProbability$sample46[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample18(int i) {
		double cv$originalValue = (1 - bias[i]);
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$originalValue, a, b);
			double traceTempVariable$var34$1_1 = (1 - cv$originalValue);
			for(int var45 = 0; var45 < length$flipsMeasured[i]; var45 += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[i][var45], traceTempVariable$var34$1_1) + cv$accumulatedProbabilities);
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		bias[i] = (1 - cv$proposedValue);
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, a, b);
		double traceTempVariable$var34$1_1 = (1 - cv$proposedValue);
		for(int var45 = 0; var45 < length$flipsMeasured[i]; var45 += 1)
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[i][var45], traceTempVariable$var34$1_1) + cv$accumulatedProbabilities);
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			bias[i] = (1 - cv$originalValue);
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!setFlag$bias)
			bias = new double[length$flipsMeasured.length];
		if(!setFlag$flips) {
			flips = new boolean[length$flipsMeasured.length][];
			for(int j = 0; j < length$flipsMeasured.length; j += 1)
				flips[j] = new boolean[length$flipsMeasured[j]];
		}
		logProbability$var17 = new double[length$flipsMeasured.length];
		logProbability$sample18 = new double[length$flipsMeasured.length];
		logProbability$bernoulli = new double[length$flipsMeasured.length];
		logProbability$sample46 = new double[length$flipsMeasured.length];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample18) {
			for(int i = 0; i < coins; i += 1)
				bias[i] = (1 - DistributionSampling.sampleBeta(RNG$, a, b));
		}
		if(!fixedFlag$sample46) {
			for(int j = 0; j < coins; j += 1) {
				boolean[] var36 = flips[j];
				for(int var45 = 0; var45 < length$flipsMeasured[j]; var45 += 1)
					var36[var45] = DistributionSampling.sampleBernoulli(RNG$, bias[j]);
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample18) {
			for(int i = 0; i < coins; i += 1)
				bias[i] = (1 - DistributionSampling.sampleBeta(RNG$, a, b));
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample18) {
			for(int i = 0; i < coins; i += 1)
				bias[i] = (1 - DistributionSampling.sampleBeta(RNG$, a, b));
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample18) {
			if(system$gibbsForward) {
				for(int i = 0; i < coins; i += 1)
					sample18(i);
			} else {
				for(int i = (coins - 1); i >= 0; i -= 1)
					sample18(i);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		coins = length$flipsMeasured.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		for(int i = 0; i < coins; i += 1)
			logProbability$var17[i] = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample18) {
			for(int i = 0; i < coins; i += 1)
				logProbability$sample18[i] = 0.0;
		}
		for(int j = 0; j < coins; j += 1)
			logProbability$bernoulli[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample46) {
			for(int j = 0; j < coins; j += 1)
				logProbability$sample46[j] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample18)
			logProbabilityValue$sample18();
		logProbabilityValue$sample46();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample18();
		logProbabilityValue$sample46();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample18();
		logProbabilityValue$sample46();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample18) {
			for(int i = 0; i < coins; i += 1)
				bias[i] = (1 - DistributionSampling.sampleBeta(RNG$, a, b));
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[] cv$source2 = flipsMeasured[cv$index1];
			boolean[] cv$target2 = flips[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
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
		     + "public model Flip2CoinsMK7(double a, double b, boolean[][] flipsMeasured) {\n"
		     + "     \n"
		     + "    int coins = flipsMeasured.length;\n"
		     + "    double[] bias = new double[coins];\n"
		     + "    for(int i:[0..coins)) \n"
		     + "        bias[i] = 1 - beta(a, b).sample();\n"
		     + "                \n"
		     + "    boolean[][] flips = new boolean[coins][];\n"
		     + "        \n"
		     + "    for(int j:[0..coins)) {\n"
		     + "        int samples = flipsMeasured[j].length;\n"
		     + "        Bernoulli bernoulli = bernoulli(bias[j]);\n"
		     + "        flips[j] = bernoulli.sample(samples);\n"
		     + "    }\n"
		     + "\n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}