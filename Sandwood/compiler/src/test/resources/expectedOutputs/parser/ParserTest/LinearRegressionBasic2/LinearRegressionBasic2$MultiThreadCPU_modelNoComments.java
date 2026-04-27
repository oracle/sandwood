package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.LinearRegressionBasic2$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.LinearRegressionBasic2.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class LinearRegressionBasic2$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public LinearRegressionBasic2$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample11() {
		state.b1 = ((Math.sqrt(5.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 1.0);
	}

	private final void drawValueSample16() {
		state.variance = (1 / DistributionSampling.sampleGamma(state.RNG$, 1.0, 1.0));
	}

	private final void drawValueSample7() {
		state.b0 = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
	}

	private final void inferSample11() {
		if(true) {
			state.constrainedFlag$sample11 = false;
			double cv$sum = 0.0;
			double cv$denominatorSquareSum = 0.0;
			boolean cv$sigmaNotFound = true;
			double cv$sigmaValue = 1.0;
			{
				{
					{
						{
							for(int i = 0; i < state.noSamples; i += 1) {
								boolean cv$sampleConstrained = true;
								if(cv$sampleConstrained) {
									state.constrainedFlag$sample11 = true;
									{
										{
											{
												{
													{
														double cv$denominator = 1.0;
														double cv$numerator = 0.0;
														cv$numerator = (cv$numerator * state.x[i]);
														cv$denominator = (cv$denominator * state.x[i]);
														cv$numerator = (state.b0 + cv$numerator);
														cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
														cv$sum = (cv$sum + (cv$denominator * (state.y[i] - cv$numerator)));
														if(cv$sigmaNotFound) {
															cv$sigmaValue = state.variance;
															cv$sigmaNotFound = false;
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
			if(state.constrainedFlag$sample11)
				state.b1 = Conjugates.sampleConjugateGaussianGaussian(state.RNG$, 1.0, 5.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
		}
	}

	private final void inferSample16() {
		if(true) {
			state.constrainedFlag$sample16 = false;
			double cv$sum = 0.0;
			int cv$count = 0;
			{
				{
					{
						{
							for(int i = 0; i < state.noSamples; i += 1) {
								boolean cv$sampleConstrained = true;
								if(cv$sampleConstrained) {
									state.constrainedFlag$sample16 = true;
									{
										{
											{
												{
													{
														double cv$var32$mu = (state.b0 + (state.b1 * state.x[i]));
														double cv$var32$diff = (cv$var32$mu - state.y[i]);
														cv$sum = (cv$sum + (cv$var32$diff * cv$var32$diff));
														cv$count = (cv$count + 1);
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
			if(state.constrainedFlag$sample16) {
				double var16 = Conjugates.sampleConjugateGammaGaussian(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
				{
					{
						{
							state.variance = (1 / var16);
						}
					}
				}
			}
		}
	}

	private final void inferSample7() {
		if(true) {
			state.constrainedFlag$sample7 = false;
			double cv$sum = 0.0;
			double cv$denominatorSquareSum = 0.0;
			boolean cv$sigmaNotFound = true;
			double cv$sigmaValue = 1.0;
			{
				{
					{
						{
							for(int i = 0; i < state.noSamples; i += 1) {
								boolean cv$sampleConstrained = true;
								if(cv$sampleConstrained) {
									state.constrainedFlag$sample7 = true;
									{
										{
											{
												{
													{
														double cv$denominator = 1.0;
														double cv$numerator = 0.0;
														cv$numerator = (cv$numerator + (state.b1 * state.x[i]));
														cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
														cv$sum = (cv$sum + (cv$denominator * (state.y[i] - cv$numerator)));
														if(cv$sigmaNotFound) {
															cv$sigmaValue = state.variance;
															cv$sigmaNotFound = false;
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
			if(state.constrainedFlag$sample7)
				state.b0 = Conjugates.sampleConjugateGaussianGaussian(state.RNG$, 0.0, 2.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
		}
	}

	private final void logProbabilityValue$sample11() {
		if(!state.fixedProbFlag$sample11) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = state.b1;
					{
						{
							double var8 = 1.0;
							double var9 = 5.0;
							double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var9)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var8) / Math.sqrt(var9))) - (0.5 * Math.log(var9))):Double.NEGATIVE_INFINITY));
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
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			state.logProbability$b1 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample11)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample11 = state.fixedFlag$sample11;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$b1;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample11)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample16() {
		if(!state.fixedProbFlag$sample16) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = (1 / state.variance);
					{
						{
							double var13 = 1.0;
							double var14 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGamma(cv$sampleValue, var13, var14));
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
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			state.logProbability$var16 = cv$sampleProbability;
			state.logProbability$variance = (state.logProbability$variance + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample16)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample16 = state.fixedFlag$sample16;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$var16;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$variance = (state.logProbability$variance + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample16)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample33() {
		if(!state.fixedProbFlag$sample33) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < state.noSamples; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.y[i];
						{
							{
								double var31 = (state.b0 + (state.b1 * state.x[i]));
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < state.variance)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var31) / Math.sqrt(state.variance))) - (0.5 * Math.log(state.variance))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample33[((i - 0) / 1)] = cv$sampleProbability;
			}
			state.logProbability$y = (state.logProbability$y + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample33 = ((state.fixedFlag$sample7 && state.fixedFlag$sample11) && state.fixedFlag$sample16);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < state.noSamples; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample33[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$y = (state.logProbability$y + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample7() {
		if(!state.fixedProbFlag$sample7) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = state.b0;
					{
						{
							double var4 = 0.0;
							double var5 = 2.0;
							double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var5)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var4) / Math.sqrt(var5))) - (0.5 * Math.log(var5))):Double.NEGATIVE_INFINITY));
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
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			state.logProbability$b0 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample7)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample7 = state.fixedFlag$sample7;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$b0;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample7)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample7)
			state.b0 = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample11)
			state.b1 = ((Math.sqrt(5.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 1.0);
		if(!state.fixedFlag$sample16)
			state.variance = (1 / DistributionSampling.sampleGamma(state.RNG$, 1.0, 1.0));
		parallelFor(state.RNG$, 0, state.noSamples, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1)
						state.y[i] = ((Math.sqrt(state.variance) * DistributionSampling.sampleGaussian(RNG$1)) + (state.b0 + (state.b1 * state.x[i])));
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample7)
			state.b0 = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample11)
			state.b1 = ((Math.sqrt(5.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 1.0);
		if(!state.fixedFlag$sample16)
			state.variance = (1 / DistributionSampling.sampleGamma(state.RNG$, 1.0, 1.0));
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample7)
			state.b0 = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample11)
			state.b1 = ((Math.sqrt(5.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 1.0);
		if(!state.fixedFlag$sample16)
			state.variance = (1 / DistributionSampling.sampleGamma(state.RNG$, 1.0, 1.0));
		parallelFor(state.RNG$, 0, state.noSamples, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1)
						state.y[i] = ((Math.sqrt(state.variance) * DistributionSampling.sampleGaussian(RNG$1)) + (state.b0 + (state.b1 * state.x[i])));
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample7)
			state.b0 = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample11)
			state.b1 = ((Math.sqrt(5.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 1.0);
		if(!state.fixedFlag$sample16)
			state.variance = (1 / DistributionSampling.sampleGamma(state.RNG$, 1.0, 1.0));
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample7)
			state.b0 = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample11)
			state.b1 = ((Math.sqrt(5.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 1.0);
		if(!state.fixedFlag$sample16)
			state.variance = (1 / DistributionSampling.sampleGamma(state.RNG$, 1.0, 1.0));
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample7)
				inferSample7();
			if(!state.fixedFlag$sample11)
				inferSample11();
			if(!state.fixedFlag$sample16)
				inferSample16();
		} else {
			if(!state.fixedFlag$sample16)
				inferSample16();
			if(!state.fixedFlag$sample11)
				inferSample11();
			if(!state.fixedFlag$sample7)
				inferSample7();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample7)
			drawValueSample7();
		if(!state.constrainedFlag$sample11)
			drawValueSample11();
		if(!state.constrainedFlag$sample16)
			drawValueSample16();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample7)
			state.logProbability$b0 = Double.NaN;
		if(!state.fixedProbFlag$sample11)
			state.logProbability$b1 = Double.NaN;
		state.logProbability$variance = 0.0;
		if(!state.fixedProbFlag$sample16)
			state.logProbability$var16 = Double.NaN;
		state.logProbability$y = 0.0;
		if(!state.fixedProbFlag$sample33) {
			for(int i = 0; i < state.noSamples; i += 1)
				state.logProbability$sample33[((i - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.noSamples = state.x.length;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample7)
			logProbabilityValue$sample7();
		if(state.fixedFlag$sample11)
			logProbabilityValue$sample11();
		if(state.fixedFlag$sample16)
			logProbabilityValue$sample16();
		logProbabilityValue$sample33();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample7();
		logProbabilityValue$sample11();
		logProbabilityValue$sample16();
		logProbabilityValue$sample33();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample7();
		logProbabilityValue$sample11();
		logProbabilityValue$sample16();
		logProbabilityValue$sample33();
	}

	@Override
	public final void propagateObservedValues() {
		double[] cv$source1 = state.yMeasured;
		double[] cv$target1 = state.y;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
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
		     + "public model LinearRegressionBasic2(double[] x, double[] yMeasured) {\n"
		     + "    \n"
		     + "        int noSamples = x.length;\n"
		     + "        double b0 = gaussian(0.0, 2.0).sample();\n"
		     + "        double b1 = gaussian(1.0, 5.0).sample();\n"
		     + "        double variance = 1/gamma(1.0, 1.0).sample();\n"
		     + "        double[] y = new double[noSamples];\n"
		     + "        for(int i:[0..noSamples)) {\n"
		     + "           y[i] = gaussian(b0 + b1 * x[i], variance).sample();\n"
		     + "        }\n"
		     + "        y.observe(yMeasured);\n"
		     + "}\n"
		     + "";
	}
}