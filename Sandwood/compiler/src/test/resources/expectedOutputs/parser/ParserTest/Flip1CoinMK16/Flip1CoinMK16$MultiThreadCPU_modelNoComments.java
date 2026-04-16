package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK16$MultiThreadCPU extends CoreModelMultiThreadCPU implements Flip1CoinMK16$CoreInterface {
double bias;
	boolean constrainedFlag$sample14 = true;
	boolean flip;
	boolean flipMeasured;
	double guard;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$bernoulli;
	double logProbability$sample14;
	double logProbability$sample16;
	boolean system$gibbsForward = true;

	public Flip1CoinMK16$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double cv$value, boolean allocated$) {
		bias = cv$value;
	}

	@Override
	public final boolean get$flipMeasured() {
		return flipMeasured;
	}

	@Override
	public final void set$flipMeasured(boolean cv$value, boolean allocated$) {
		flipMeasured = cv$value;
	}

	@Override
	public final double get$guard() {
		return guard;
	}

	@Override
	public final void set$guard(double cv$value, boolean allocated$) {
		guard = cv$value;
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

	private final void drawValueSample14() {
		bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	private final void inferSample14() {
		if(true) {
			constrainedFlag$sample14 = false;
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						{
							{
								{
									boolean cv$sampleConstrained = true;
									if(cv$sampleConstrained) {
										constrainedFlag$sample14 = true;
										{
											{
												{
													{
														{
															cv$count = (cv$count + 1);
															if(flip)
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
			if(constrainedFlag$sample14)
				bias = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		}
	}

	private final void logProbabilityValue$sample14() {
		double cv$accumulator = 0.0;
		boolean cv$sampleReached = false;
		if(Double.isNaN(guard)) {
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = bias;
					{
						{
							double var9 = 1.0;
							double var10 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var9, var10));
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
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleReached = true;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$sample14 = cv$sampleProbability;
		}
		logProbability$$model = (logProbability$$model + cv$accumulator);
	}

	private final void logProbabilityValue$sample16() {
		double cv$accumulator = 0.0;
		boolean cv$sampleReached = false;
		if(Double.isNaN(guard)) {
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					boolean cv$sampleValue = flip;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= bias) && (bias <= 1.0))?Math.log((cv$sampleValue?bias:(1.0 - bias))):Double.NEGATIVE_INFINITY));
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
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleReached = true;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$sample16 = cv$sampleProbability;
		}
		logProbability$$model = (logProbability$$model + cv$accumulator);
		logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
	}

	@Override
	public final void allocate() {}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void forwardGeneration() {
		if(Double.isNaN(guard)) {
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			flip = DistributionSampling.sampleBernoulli(RNG$, bias);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(Double.isNaN(guard))
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(Double.isNaN(guard)) {
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			flip = DistributionSampling.sampleBernoulli(RNG$, bias);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(Double.isNaN(guard))
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(Double.isNaN(guard))
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(Double.isNaN(guard))
				inferSample14();
		} else {
			if(Double.isNaN(guard))
				inferSample14();
		}
		system$gibbsForward = !system$gibbsForward;
		if(Double.isNaN(guard)) {
			if(!constrainedFlag$sample14)
				drawValueSample14();
		}
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$sample14 = Double.NaN;
		logProbability$bernoulli = Double.NaN;
		logProbability$sample16 = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample16();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample14();
		logProbabilityValue$sample16();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample14();
		logProbabilityValue$sample16();
	}

	@Override
	public final void propagateObservedValues() {
		if(Double.isNaN(guard))
			flip = flipMeasured;
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
		     + "public model Flip1CoinMK16(boolean flipMeasured, double guard) {\n"
		     + "    if(isNaN(guard)) {\n"
		     + "        double bias = beta(1.0, 1.0).sample();\n"
		     + "        Bernoulli bernoulli = bernoulli(bias);\n"
		     + "        boolean flip = bernoulli.sample();\n"
		     + "        flip.observe(flipMeasured);\n"
		     + "    }\n"
		     + "}\n"
		     + "";
	}
}