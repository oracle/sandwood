package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinArrayCopyPass$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinArrayCopyPass$CoreInterface {
	private double a;
	private double b;
	private double[] bias;
	private boolean fixedFlag$sample13 = false;
	private boolean fixedFlag$sample21 = false;
	private boolean fixedProbFlag$sample13 = false;
	private boolean fixedProbFlag$sample21 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample21;
	private double logProbability$var11;
	private double logProbability$var12;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;

	public Flip1CoinArrayCopyPass$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$a() {
		return a;
	}

	@Override
	public final double get$b() {
		return b;
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		setFlag$bias = true;
	}

	@Override
	public final boolean get$fixedFlag$sample13() {
		return fixedFlag$sample13;
	}

	@Override
	public final void set$fixedFlag$sample13(boolean cv$value) {
		fixedFlag$sample13 = cv$value;
		fixedProbFlag$sample13 = (fixedFlag$sample13 && fixedProbFlag$sample13);
		fixedProbFlag$sample21 = (fixedFlag$sample13 && fixedProbFlag$sample21);
	}

	@Override
	public final boolean get$fixedFlag$sample21() {
		return fixedFlag$sample21;
	}

	@Override
	public final void set$fixedFlag$sample21(boolean cv$value) {
		fixedFlag$sample21 = cv$value;
		fixedProbFlag$sample21 = (fixedFlag$sample21 && fixedProbFlag$sample21);
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

	@Override
	public final int get$samples() {
		return samples;
	}

	@Override
	public final void set$samples(int cv$value) {
		samples = cv$value;
	}

	private final void logProbabilityValue$sample13() {
		if(!fixedProbFlag$sample13) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = bias[0];
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
			logProbability$var11 = cv$sampleAccumulator;
			logProbability$var12 = cv$sampleProbability;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample13)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample13 = fixedFlag$sample13;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var12;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var11 = cv$rvAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample13)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample21() {
		if(!fixedProbFlag$sample21) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < samples; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[i];
					{
						{
							double var18 = bias[i];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var18));
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
				logProbability$bernoulli[((i - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample21[((i - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample21 = (fixedFlag$sample21 && fixedFlag$sample13);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < samples; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample21[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli[((i - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample13() {
		int cv$sum = 0;
		int cv$count = 0;
		{
			{
				{
					for(int i = 0; i < samples; i += 1) {
						if((0 == i)) {
							{
								{
									{
										{
											{
												cv$count = (cv$count + 1);
												if(flips[i])
													cv$sum = (cv$sum + 1);
											}
										}
									}
								}
							}
						}
					}
					if((0 == 0)) {
						for(int i = 0; i < samples; i += 1) {
							for(int index$i$2_2 = 0; index$i$2_2 < samples; index$i$2_2 += 1) {
								if(((i + 1) == index$i$2_2)) {
									{
										{
											{
												{
													{
														cv$count = (cv$count + 1);
														if(flips[index$i$2_2])
															cv$sum = (cv$sum + 1);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		double var12 = Conjugates.sampleConjugateBetaBinomial(RNG$, a, b, cv$sum, cv$count);
		bias[0] = var12;
		{
			if((0 == 0)) {
				for(int i = 0; i < samples; i += 1)
					bias[(i + 1)] = bias[0];
			}
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!setFlag$bias) {
			{
				bias = new double[(samples + 1)];
			}
		}
		if(!setFlag$flips) {
			{
				flips = new boolean[samples];
			}
		}
		{
			logProbability$bernoulli = new double[((((samples - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample21 = new double[((((samples - 1) - 0) / 1) + 1)];
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample13)
			bias[0] = DistributionSampling.sampleBeta(RNG$, a, b);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample21)
							flips[i] = DistributionSampling.sampleBernoulli(RNG$1, bias[i]);
						if(!fixedFlag$sample13)
							bias[(i + 1)] = bias[0];
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample13)
			bias[0] = DistributionSampling.sampleBeta(RNG$, a, b);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample13)
			bias[0] = DistributionSampling.sampleBeta(RNG$, a, b);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample13)
				sample13();
		} else {
			if(!fixedFlag$sample13)
				sample13();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		a = 1.0;
		b = 1.0;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var11 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample13)
			logProbability$var12 = 0.0;
		for(int i = 0; i < samples; i += 1)
			logProbability$bernoulli[((i - 0) / 1)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample21) {
			for(int i = 0; i < samples; i += 1)
				logProbability$sample21[((i - 0) / 1)] = 0.0;
		}
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
		logProbabilityValue$sample21();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample13();
		logProbabilityValue$sample21();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample13();
		logProbabilityValue$sample21();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample13)
			bias[0] = DistributionSampling.sampleBeta(RNG$, a, b);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		boolean[] cv$source1 = flipsMeasured;
		boolean[] cv$target1 = flips;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(setFlag$bias)
							bias[(i + 1)] = bias[0];
					}
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinArrayCopyPass(int samples, boolean[] flipsMeasured) {\n    /*\n     * This is a bad example as there is a separation between the size of \n     * flips measured, and the size of noSamples.\n     */\n    double a = 1.0;\n    double b = 1.0;\n    double[] bias = new double[samples+1];\n    bias[0] = beta(a, b).sample();\n    boolean[] flips = new boolean[samples];\n    for(int i:[0..samples)) {\n        Bernoulli bernoulli = bernoulli(bias[i]);\n        flips[i] = bernoulli.sample();\n        bias[i+1] = bias[0];\n    }\n    flips.observe(flipsMeasured);\n}";
	}
}