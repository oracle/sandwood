package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK20$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK20$CoreInterface {
	private double bias;
	private int count1;
	private int count2;
	private boolean fixedFlag$sample11 = false;
	private boolean fixedFlag$sample12 = false;
	private boolean fixedFlag$sample8 = false;
	private boolean fixedProbFlag$sample11 = false;
	private boolean fixedProbFlag$sample12 = false;
	private boolean fixedProbFlag$sample8 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$binomial;
	private double logProbability$count1;
	private double logProbability$count2;
	private double logProbability$var7;
	private int obs1;
	private int obs2;
	private boolean system$gibbsForward = true;
	private int total;

	public Flip1CoinMK20$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double cv$value) {
		bias = cv$value;
		fixedProbFlag$sample8 = false;
		fixedProbFlag$sample11 = false;
		fixedProbFlag$sample12 = false;
	}

	@Override
	public final int get$count1() {
		return count1;
	}

	@Override
	public final int get$count2() {
		return count2;
	}

	@Override
	public final boolean get$fixedFlag$sample11() {
		return fixedFlag$sample11;
	}

	@Override
	public final void set$fixedFlag$sample11(boolean cv$value) {
		fixedFlag$sample11 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample12() {
		return fixedFlag$sample12;
	}

	@Override
	public final void set$fixedFlag$sample12(boolean cv$value) {
		fixedFlag$sample12 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample8() {
		return fixedFlag$sample8;
	}

	@Override
	public final void set$fixedFlag$sample8(boolean cv$value) {
		fixedFlag$sample8 = cv$value;
		fixedProbFlag$sample8 = (fixedFlag$sample8 && fixedProbFlag$sample8);
		fixedProbFlag$sample11 = (fixedFlag$sample8 && fixedProbFlag$sample11);
		fixedProbFlag$sample12 = (fixedFlag$sample8 && fixedProbFlag$sample12);
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
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$binomial() {
		return logProbability$binomial;
	}

	@Override
	public final double get$logProbability$count1() {
		return logProbability$count1;
	}

	@Override
	public final double get$logProbability$count2() {
		return logProbability$count2;
	}

	@Override
	public final int get$obs1() {
		return obs1;
	}

	@Override
	public final void set$obs1(int cv$value) {
		obs1 = cv$value;
	}

	@Override
	public final int get$obs2() {
		return obs2;
	}

	@Override
	public final void set$obs2(int cv$value) {
		obs2 = cv$value;
	}

	@Override
	public final int get$total() {
		return total;
	}

	private final void logProbabilityValue$sample11() {
		if(!fixedProbFlag$sample11) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = count1;
				{
					{
						int var9 = 100;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, bias, var9));
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
			logProbability$binomial = (logProbability$binomial + cv$sampleAccumulator);
			logProbability$count1 = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample11 = fixedFlag$sample8;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$count1;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$binomial = (logProbability$binomial + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample12() {
		if(!fixedProbFlag$sample12) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = count2;
				{
					{
						int var9 = 100;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, bias, var9));
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
			logProbability$binomial = (logProbability$binomial + cv$sampleAccumulator);
			logProbability$count2 = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample12 = fixedFlag$sample8;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$count2;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$binomial = (logProbability$binomial + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample8() {
		if(!fixedProbFlag$sample8) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = bias;
				{
					{
						double var5 = 1.0;
						double var6 = 1.0;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var5, var6));
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
			logProbability$var7 = cv$sampleAccumulator;
			logProbability$bias = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample8)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample8 = fixedFlag$sample8;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$bias;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var7 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample8)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample8() {
		if(true) {
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						{
							{
								{
									{
										{
											cv$count = (cv$count + 100);
											cv$sum = (cv$sum + count1);
										}
									}
								}
							}
						}
						{
							{
								{
									{
										{
											cv$count = (cv$count + 100);
											cv$sum = (cv$sum + count2);
										}
									}
								}
							}
						}
					}
				}
			}
			bias = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample8)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample11)
			count1 = DistributionSampling.sampleBinomial(RNG$, bias, 100);
		if(!fixedFlag$sample12)
			count2 = DistributionSampling.sampleBinomial(RNG$, bias, 100);
		if(!(fixedFlag$sample11 && fixedFlag$sample12))
			total = (count1 + count2);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample8)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample8)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample11)
			count1 = DistributionSampling.sampleBinomial(RNG$, bias, 100);
		if(!fixedFlag$sample12)
			count2 = DistributionSampling.sampleBinomial(RNG$, bias, 100);
		total = (count1 + count2);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample8)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample8)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample8)
				sample8();
		} else {
			if(!fixedFlag$sample8)
				sample8();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var7 = 0.0;
		if(!fixedProbFlag$sample8)
			logProbability$bias = Double.NaN;
		logProbability$binomial = 0.0;
		if(!fixedProbFlag$sample11)
			logProbability$count1 = Double.NaN;
		if(!fixedProbFlag$sample12)
			logProbability$count2 = Double.NaN;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample8)
			logProbabilityValue$sample8();
		logProbabilityValue$sample11();
		logProbabilityValue$sample12();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample8();
		logProbabilityValue$sample11();
		logProbabilityValue$sample12();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample8();
		logProbabilityValue$sample11();
		logProbabilityValue$sample12();
	}

	@Override
	public final void propagateObservedValues() {
		fixedFlag$sample11 = false;
		fixedFlag$sample12 = false;
		{
			count1 = obs1;
			count2 = obs2;
		}
		{
			total = (count1 + count2);
		}
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model Flip1CoinMK20(int obs1, int obs2) {\n"
		     + "    \n"
		     + "    double bias = beta(1,1).sample();\n"
		     + "    Binomial binomial = binomial(bias, 100);\n"
		     + "    int count1 = binomial.sample();\n"
		     + "    int count2 = binomial.sample();\n"
		     + "    int total = count1 + count2;\n"
		     + "    \n"
		     + "    count1.observe(obs1);\n"
		     + "    count2.observe(obs2);\n"
		     + "}\n"
		     + "";
	}
}