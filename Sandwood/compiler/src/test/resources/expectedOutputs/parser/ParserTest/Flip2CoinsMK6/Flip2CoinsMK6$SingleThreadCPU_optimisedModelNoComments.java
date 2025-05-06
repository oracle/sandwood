package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip2CoinsMK6$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip2CoinsMK6$CoreInterface {
	private double[] bias;
	private int coins;
	private boolean fixedFlag$sample18 = false;
	private boolean fixedProbFlag$sample18 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean[][] flips;
	private boolean[][] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli;
	private double[] logProbability$beta;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample18;
	private double[] logProbability$sample31;
	private int[] shape;
	private boolean system$gibbsForward = true;

	public Flip2CoinsMK6$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
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
		fixedProbFlag$sample31 = (cv$value && fixedProbFlag$sample31);
	}

	@Override
	public final boolean[][] get$flips() {
		return flips;
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
	public final double[] get$logProbability$beta() {
		return logProbability$beta;
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
	public final int[] get$shape() {
		return shape;
	}

	@Override
	public final void set$shape(int[] cv$value) {
		shape = cv$value;
	}

	private final void logProbabilityValue$sample18() {
		if(!fixedProbFlag$sample18) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < coins; j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(bias[j], 1.0, 1.0);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$beta[j] = cv$distributionAccumulator;
				logProbability$sample18[j] = cv$distributionAccumulator;
			}
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample18 = fixedFlag$sample18;
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < coins; j += 1) {
				double cv$rvAccumulator = logProbability$sample18[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$beta[j] = cv$rvAccumulator;
			}
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!fixedProbFlag$sample31) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < coins; j += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var30 = 0; var30 < shape[j]; var30 += 1)
					cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((flips[j][var30]?bias[j]:(1.0 - bias[j]))));
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$bernoulli[j] = cv$sampleAccumulator;
				logProbability$sample31[j] = cv$sampleAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample31 = fixedFlag$sample18;
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < coins; j += 1) {
				double cv$rvAccumulator = logProbability$sample31[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample18(int j) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int var30 = 0; var30 < shape[j]; var30 += 1) {
			cv$count = (cv$count + 1);
			if(flips[j][var30])
				cv$sum = (cv$sum + 1);
		}
		bias[j] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		flips = new boolean[shape.length][];
		for(int j = 0; j < shape.length; j += 1)
			flips[j] = new boolean[shape[j]];
		if(!fixedFlag$sample18)
			bias = new double[shape.length];
		logProbability$beta = new double[shape.length];
		logProbability$sample18 = new double[shape.length];
		logProbability$bernoulli = new double[shape.length];
		logProbability$sample31 = new double[shape.length];
	}

	@Override
	public final void forwardGeneration() {
		for(int j = 0; j < coins; j += 1) {
			if(!fixedFlag$sample18)
				bias[j] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			boolean[] var21 = flips[j];
			for(int var30 = 0; var30 < shape[j]; var30 += 1)
				var21[var30] = DistributionSampling.sampleBernoulli(RNG$, bias[j]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample18) {
			for(int j = 0; j < coins; j += 1)
				bias[j] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		for(int j = 0; j < coins; j += 1) {
			if(!fixedFlag$sample18)
				bias[j] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			boolean[] var21 = flips[j];
			for(int var30 = 0; var30 < shape[j]; var30 += 1)
				var21[var30] = DistributionSampling.sampleBernoulli(RNG$, bias[j]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample18) {
			for(int j = 0; j < coins; j += 1)
				bias[j] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample18) {
			for(int j = 0; j < coins; j += 1)
				bias[j] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample18) {
			if(system$gibbsForward) {
				for(int j = 0; j < coins; j += 1)
					sample18(j);
			} else {
				for(int j = (coins - 1); j >= 0; j -= 1)
					sample18(j);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		coins = shape.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		for(int j = 0; j < coins; j += 1)
			logProbability$beta[j] = Double.NaN;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample18) {
			for(int j = 0; j < coins; j += 1)
				logProbability$sample18[j] = Double.NaN;
		}
		for(int j = 0; j < coins; j += 1)
			logProbability$bernoulli[j] = Double.NaN;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample31) {
			for(int j = 0; j < coins; j += 1)
				logProbability$sample31[j] = Double.NaN;
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample18)
			logProbabilityValue$sample18();
		logProbabilityValue$sample31();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample18();
		logProbabilityValue$sample31();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample18();
		logProbabilityValue$sample31();
	}

	@Override
	public final void propagateObservedValues() {
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
		     + "public model Flip2CoinsMK6(boolean[][] flipsMeasured, int[] shape) {\n"
		     + "\n"
		     + "    int coins = shape.length;\n"
		     + "                 \n"
		     + "    boolean[][] flips = new boolean[coins][];\n"
		     + "        \n"
		     + "    for(int j:[0..coins)) {\n"
		     + "        Beta beta = beta(1.0, 1.0);\n"
		     + "        public double bias = beta.sample();\n"
		     + "        int samples = shape[j];\n"
		     + "        Bernoulli bernoulli = bernoulli(bias);\n"
		     + "        flips[j] = bernoulli.sample(samples);\n"
		     + "    }\n"
		     + "        \n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}";
	}
}