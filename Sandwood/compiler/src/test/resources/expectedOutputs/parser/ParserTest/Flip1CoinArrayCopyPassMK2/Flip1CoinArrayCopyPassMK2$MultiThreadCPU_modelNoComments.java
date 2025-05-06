package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinArrayCopyPassMK2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinArrayCopyPassMK2$CoreInterface {
	private double a;
	private double b;
	private double[] bias;
	private boolean fixedFlag$sample10 = false;
	private boolean fixedProbFlag$sample10 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample31;
	private double logProbability$var10;
	private double logProbability$var9;
	private int samples;
	private boolean system$gibbsForward = true;

	public Flip1CoinArrayCopyPassMK2$MultiThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample10 = false;
		fixedProbFlag$sample31 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample10() {
		return fixedFlag$sample10;
	}

	@Override
	public final void set$fixedFlag$sample10(boolean cv$value) {
		fixedFlag$sample10 = cv$value;
		fixedProbFlag$sample10 = (fixedFlag$sample10 && fixedProbFlag$sample10);
		fixedProbFlag$sample31 = (fixedFlag$sample10 && fixedProbFlag$sample31);
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

	private final void logProbabilityValue$sample10() {
		if(!fixedProbFlag$sample10) {
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
			logProbability$var9 = cv$sampleAccumulator;
			logProbability$var10 = cv$sampleProbability;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample10 = fixedFlag$sample10;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var10;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var9 = cv$rvAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!fixedProbFlag$sample31) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < samples; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[i];
					{
						{
							double var29 = bias[i];
							double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var29:(1.0 - var29))));
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
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$bernoulli[((i - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample31[((i - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample31 = fixedFlag$sample10;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < samples; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample31[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli[((i - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample10() {
		if(true) {
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
			double var10 = Conjugates.sampleConjugateBetaBinomial(RNG$, a, b, cv$sum, cv$count);
			{
				{
					bias[0] = var10;
				}
			}
			{
				if((0 == 0)) {
					for(int i = 0; i < samples; i += 1)
						bias[(i + 1)] = bias[0];
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!fixedFlag$sample10) {
			{
				bias = new double[(samples + 1)];
			}
		}
		{
			flips = new boolean[samples];
		}
		{
			logProbability$bernoulli = new double[((((samples - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample31 = new double[((((samples - 1) - 0) / 1) + 1)];
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample10)
			bias[0] = DistributionSampling.sampleBeta(RNG$, a, b);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample10)
							bias[(i + 1)] = bias[0];
						flips[i] = DistributionSampling.sampleBernoulli(RNG$1, bias[i]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample10)
			bias[0] = DistributionSampling.sampleBeta(RNG$, a, b);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1)
						bias[(i + 1)] = bias[0];
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample10)
			bias[0] = DistributionSampling.sampleBeta(RNG$, a, b);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						bias[(i + 1)] = bias[0];
						flips[i] = DistributionSampling.sampleBernoulli(RNG$1, bias[i]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample10)
			bias[0] = DistributionSampling.sampleBeta(RNG$, a, b);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample10)
							bias[(i + 1)] = bias[0];
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample10)
			bias[0] = DistributionSampling.sampleBeta(RNG$, a, b);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1)
						bias[(i + 1)] = bias[0];
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample10)
				sample10();
		} else {
			if(!fixedFlag$sample10)
				sample10();
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
		logProbability$var9 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample10)
			logProbability$var10 = Double.NaN;
		for(int i = 0; i < samples; i += 1)
			logProbability$bernoulli[((i - 0) / 1)] = Double.NaN;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample31) {
			for(int i = 0; i < samples; i += 1)
				logProbability$sample31[((i - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample10)
			logProbabilityValue$sample10();
		logProbabilityValue$sample31();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample31();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample31();
	}

	@Override
	public final void propagateObservedValues() {
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
				for(int i = forStart$i; i < forEnd$i; i += 1)
						bias[(i + 1)] = bias[0];
			}
		);
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
		     + "public model Flip1CoinArrayCopyPassMK2(int samples, boolean[] flipsMeasured) {\n"
		     + "    /*\n"
		     + "     * This is a bad example as there is a separation between the size of \n"
		     + "     * flips measured, and the size of noSamples.\n"
		     + "     */\n"
		     + "    double a = 1.0;\n"
		     + "    double b = 1.0;\n"
		     + "    double[] bias = new double[samples+1];\n"
		     + "    bias[0] = beta(a, b).sample();\n"
		     + "    boolean[] flips = new boolean[samples];\n"
		     + "    for(int i:[0..samples)) {\n"
		     + "        bias[i+1] = bias[0];\n"
		     + "        Bernoulli bernoulli = bernoulli(bias[i]);\n"
		     + "        flips[i] = bernoulli.sample();\n"
		     + "    }\n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}";
	}
}