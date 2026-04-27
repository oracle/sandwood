package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK19$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK19.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK19$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK19$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample10() {
		state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		{
			{
				{
					double[] inner = state.bias[0];
					inner[0] = state.q;
				}
			}
		}
	}

	private final void drawValueSample16() {
		state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		{
			{
				{
					double[] var28 = state.bias[0];
					var28[1] = state.t;
				}
			}
		}
	}

	private final void inferSample10() {
		if(true) {
			state.constrainedFlag$sample10 = false;
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						{
							if((0 == state.b)) {
								{
									{
										for(int var46 = 0; var46 < state.samples; var46 += 1) {
											boolean cv$sampleConstrained = true;
											if(cv$sampleConstrained) {
												state.constrainedFlag$sample10 = true;
												{
													{
														{
															{
																{
																	cv$count = (cv$count + 1);
																	if(state.flips[var46])
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
				}
			}
			if(state.constrainedFlag$sample10) {
				state.q = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
				{
					{
						{
							double[] inner = state.bias[0];
							inner[0] = state.q;
						}
					}
				}
			}
		}
	}

	private final void inferSample16() {
		if(true) {
			state.constrainedFlag$sample16 = false;
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						{
							if((0 == 0)) {
								if((1 == state.b)) {
									{
										{
											for(int var46 = 0; var46 < state.samples; var46 += 1) {
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													state.constrainedFlag$sample16 = true;
													{
														{
															{
																{
																	{
																		cv$count = (cv$count + 1);
																		if(state.flips[var46])
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
					}
				}
			}
			if(state.constrainedFlag$sample16) {
				state.t = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
				{
					{
						{
							double[] var28 = state.bias[0];
							var28[1] = state.t;
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample10() {
		if(!state.fixedProbFlag$sample10) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = state.q;
					{
						{
							double var7 = 1.0;
							double var8 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var7, var8));
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
			state.logProbability$q = cv$sampleProbability;
			boolean cv$guard$bias = false;
			{
				{
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample10)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample10 = state.fixedFlag$sample10;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$q;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			boolean cv$guard$bias = false;
			{
				{
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample10)
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
					double cv$sampleValue = state.t;
					{
						{
							double var13 = 1.0;
							double var14 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var13, var14));
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
			state.logProbability$t = cv$sampleProbability;
			boolean cv$guard$bias = false;
			{
				{
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample16)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample16 = state.fixedFlag$sample16;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$t;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			boolean cv$guard$bias = false;
			{
				{
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample16)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample48() {
		if(!state.fixedProbFlag$sample48) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var46 = 0; var46 < state.samples; var46 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.flips[var46];
						{
							{
								double[] inner = state.bias[0];
								inner[0] = state.q;
								double var34 = inner[state.b];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var34) && (var34 <= 1.0))?Math.log((cv$sampleValue?var34:(1.0 - var34))):Double.NEGATIVE_INFINITY));
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
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			state.logProbability$bernoulli = cv$sampleAccumulator;
			state.logProbability$var47 = cv$sampleAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample48 = (state.fixedFlag$sample10 && state.fixedFlag$sample16);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var46 = 0; var46 < state.samples; var46 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var47;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$bernoulli = cv$rvAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample10)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample16)
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		double[] inner = state.bias[0];
		if(!state.fixedFlag$sample10)
			inner[0] = state.q;
		double[] var28 = state.bias[0];
		if(!state.fixedFlag$sample16)
			var28[1] = state.t;
		for(int var46 = 0; var46 < state.samples; var46 += 1)
			state.flips[var46] = DistributionSampling.sampleBernoulli(state.RNG$, inner[state.b]);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample10)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample16)
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		double[] inner = state.bias[0];
		inner[0] = state.q;
		double[] var28 = state.bias[0];
		var28[1] = state.t;
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample10)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample16)
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		double[] inner = state.bias[0];
		inner[0] = state.q;
		double[] var28 = state.bias[0];
		var28[1] = state.t;
		for(int var46 = 0; var46 < state.samples; var46 += 1)
			state.flips[var46] = DistributionSampling.sampleBernoulli(state.RNG$, inner[state.b]);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample10)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample16)
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		double[] inner = state.bias[0];
		if(!state.fixedFlag$sample10)
			inner[0] = state.q;
		double[] var28 = state.bias[0];
		if(!state.fixedFlag$sample16)
			var28[1] = state.t;
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample10)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample16)
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		double[] inner = state.bias[0];
		inner[0] = state.q;
		double[] var28 = state.bias[0];
		var28[1] = state.t;
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample10)
				inferSample10();
			if(!state.fixedFlag$sample16)
				inferSample16();
		} else {
			if(!state.fixedFlag$sample16)
				inferSample16();
			if(!state.fixedFlag$sample10)
				inferSample10();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample10)
			drawValueSample10();
		if(!state.constrainedFlag$sample16)
			drawValueSample16();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample10)
			state.logProbability$q = Double.NaN;
		if(!state.fixedProbFlag$sample16)
			state.logProbability$t = Double.NaN;
		state.logProbability$bernoulli = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample48)
			state.logProbability$var47 = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample10)
			logProbabilityValue$sample10();
		if(state.fixedFlag$sample16)
			logProbabilityValue$sample16();
		logProbabilityValue$sample48();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample16();
		logProbabilityValue$sample48();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample16();
		logProbabilityValue$sample48();
	}

	@Override
	public final void propagateObservedValues() {
		boolean[] cv$source1 = state.flipsMeasured;
		boolean[] cv$target1 = state.flips;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		double[] inner = state.bias[0];
		inner[0] = state.q;
		double[] var28 = state.bias[0];
		var28[1] = state.t;
	}

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
		     + "public model Flip1CoinMK19(int samples, int a, int b, boolean[] flipsMeasured) {\n"
		     + "    \n"
		     + "    double q = beta(1,1).sample();\n"
		     + "    double t = beta(1,1).sample();\n"
		     + "    \n"
		     + "    double[][] bias = new double[1][];\n"
		     + "    private double[] inner = new double[2];\n"
		     + "    inner[0] = q;\n"
		     + "    bias[0] = inner;\n"
		     + "    bias[0][1] = t;\n"
		     + "    \n"
		     + "    Bernoulli bernoulli = bernoulli(inner[b]);\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "    \n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}