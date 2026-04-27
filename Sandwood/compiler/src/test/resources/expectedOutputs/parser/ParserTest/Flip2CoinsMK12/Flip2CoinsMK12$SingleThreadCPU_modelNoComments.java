package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip2CoinsMK12$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip2CoinsMK12.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip2CoinsMK12$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip2CoinsMK12$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample10() {
		state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void drawValueSample23(int i$var22) {
		state.bias[i$var22] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
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
							for(int j = 0; j < 1; j += 1) {
								if((0 == j)) {
									{
										{
											for(int var49 = 0; var49 < state.length$flipsMeasured[j]; var49 += 1) {
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													state.constrainedFlag$sample10 = true;
													{
														{
															{
																{
																	{
																		cv$count = (cv$count + 1);
																		if(state.flips[j][var49])
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
				{
					{
						{
							for(int k = 1; k < state.coins; k += 1) {
								if((0 == k)) {
									{
										{
											for(int var76 = 0; var76 < state.length$flipsMeasured[k]; var76 += 1) {
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													state.constrainedFlag$sample10 = true;
													{
														{
															{
																{
																	{
																		cv$count = (cv$count + 1);
																		if(state.flips[k][var76])
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
			if(state.constrainedFlag$sample10) {
				double var10 = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
				{
					{
						{
							state.bias[0] = var10;
						}
					}
				}
			}
		}
	}

	private final void inferSample23(int i$var22) {
		if(true) {
			state.constrainedFlag$sample23[((i$var22 - 1) / 1)] = false;
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						{
							for(int j = 0; j < 1; j += 1) {
								if((i$var22 == j)) {
									{
										{
											for(int var49 = 0; var49 < state.length$flipsMeasured[j]; var49 += 1) {
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													state.constrainedFlag$sample23[((i$var22 - 1) / 1)] = true;
													{
														{
															{
																{
																	{
																		cv$count = (cv$count + 1);
																		if(state.flips[j][var49])
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
				{
					{
						{
							for(int k = 1; k < state.coins; k += 1) {
								if((i$var22 == k)) {
									{
										{
											for(int var76 = 0; var76 < state.length$flipsMeasured[k]; var76 += 1) {
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													state.constrainedFlag$sample23[((i$var22 - 1) / 1)] = true;
													{
														{
															{
																{
																	{
																		cv$count = (cv$count + 1);
																		if(state.flips[k][var76])
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
			if(state.constrainedFlag$sample23[((i$var22 - 1) / 1)]) {
				double var23 = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
				{
					{
						{
							state.bias[i$var22] = var23;
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
					double cv$sampleValue = state.bias[0];
					{
						{
							double var6 = 1.0;
							double var7 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var6, var7));
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
			state.logProbability$beta = (state.logProbability$beta + cv$sampleAccumulator);
			state.logProbability$var10 = cv$sampleProbability;
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample10)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample10 = state.fixedFlag$sample10;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$var10;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$beta = (state.logProbability$beta + cv$rvAccumulator);
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample10)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample23() {
		if(!state.fixedProbFlag$sample23) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var22 = 1; i$var22 < state.coins; i$var22 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.bias[i$var22];
						{
							{
								double var6 = 1.0;
								double var7 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var6, var7));
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
			state.logProbability$beta = (state.logProbability$beta + cv$sampleAccumulator);
			state.logProbability$var23 = cv$sampleAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample23)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample23 = state.fixedFlag$sample23;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var22 = 1; i$var22 < state.coins; i$var22 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var23;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$beta = (state.logProbability$beta + cv$rvAccumulator);
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample23)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample50() {
		if(!state.fixedProbFlag$sample50) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < 1; j += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var49 = 0; var49 < state.length$flipsMeasured[j]; var49 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							boolean cv$sampleValue = state.flips[j][var49];
							{
								{
									double var38 = state.bias[j];
									double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var38) && (var38 <= 1.0))?Math.log((cv$sampleValue?var38:(1.0 - var38))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$bernoulli1[((j - 0) / 1)] = cv$sampleAccumulator;
				state.logProbability$sample50[((j - 0) / 1)] = cv$sampleAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample50 = (state.fixedFlag$sample10 && state.fixedFlag$sample23);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < 1; j += 1) {
				double cv$rvAccumulator = 0.0;
				for(int var49 = 0; var49 < state.length$flipsMeasured[j]; var49 += 1)
					cv$sampleReached = true;
				double cv$sampleValue = state.logProbability$sample50[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				state.logProbability$bernoulli1[((j - 0) / 1)] = cv$rvAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample78() {
		if(!state.fixedProbFlag$sample78) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int k = 1; k < state.coins; k += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var76 = 0; var76 < state.length$flipsMeasured[k]; var76 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							boolean cv$sampleValue = state.flips[k][var76];
							{
								{
									double var65 = state.bias[k];
									double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var65) && (var65 <= 1.0))?Math.log((cv$sampleValue?var65:(1.0 - var65))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$bernoulli2[((k - 1) / 1)] = cv$sampleAccumulator;
				state.logProbability$sample78[((k - 1) / 1)] = cv$sampleAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample78 = (state.fixedFlag$sample10 && state.fixedFlag$sample23);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int k = 1; k < state.coins; k += 1) {
				double cv$rvAccumulator = 0.0;
				for(int var76 = 0; var76 < state.length$flipsMeasured[k]; var76 += 1)
					cv$sampleReached = true;
				double cv$sampleValue = state.logProbability$sample78[((k - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				state.logProbability$bernoulli2[((k - 1) / 1)] = cv$rvAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int i$var22 = 1; i$var22 < state.coins; i$var22 += 1) {
			if(!state.fixedFlag$sample23)
				state.bias[i$var22] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		for(int j = 0; j < 1; j += 1) {
			boolean[] var40 = state.flips[j];
			for(int var49 = 0; var49 < state.length$flipsMeasured[j]; var49 += 1)
				var40[var49] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[j]);
		}
		for(int k = 1; k < state.coins; k += 1) {
			boolean[] var67 = state.flips[k];
			for(int var76 = 0; var76 < state.length$flipsMeasured[k]; var76 += 1)
				var67[var76] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[k]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int i$var22 = 1; i$var22 < state.coins; i$var22 += 1) {
			if(!state.fixedFlag$sample23)
				state.bias[i$var22] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int i$var22 = 1; i$var22 < state.coins; i$var22 += 1) {
			if(!state.fixedFlag$sample23)
				state.bias[i$var22] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		for(int j = 0; j < 1; j += 1) {
			boolean[] var40 = state.flips[j];
			for(int var49 = 0; var49 < state.length$flipsMeasured[j]; var49 += 1)
				var40[var49] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[j]);
		}
		for(int k = 1; k < state.coins; k += 1) {
			boolean[] var67 = state.flips[k];
			for(int var76 = 0; var76 < state.length$flipsMeasured[k]; var76 += 1)
				var67[var76] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[k]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int i$var22 = 1; i$var22 < state.coins; i$var22 += 1) {
			if(!state.fixedFlag$sample23)
				state.bias[i$var22] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int i$var22 = 1; i$var22 < state.coins; i$var22 += 1) {
			if(!state.fixedFlag$sample23)
				state.bias[i$var22] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample10)
				inferSample10();
			for(int i$var22 = 1; i$var22 < state.coins; i$var22 += 1) {
				if(!state.fixedFlag$sample23)
					inferSample23(i$var22);
			}
		} else {
			for(int i$var22 = (state.coins - ((((state.coins - 1) - 1) % 1) + 1)); i$var22 >= ((1 - 1) + 1); i$var22 -= 1) {
				if(!state.fixedFlag$sample23)
					inferSample23(i$var22);
			}
			if(!state.fixedFlag$sample10)
				inferSample10();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample10)
			drawValueSample10();
		for(int i$var22 = 1; i$var22 < state.coins; i$var22 += 1) {
			if(!state.constrainedFlag$sample23[((i$var22 - 1) / 1)])
				drawValueSample23(i$var22);
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$beta = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample10)
			state.logProbability$var10 = Double.NaN;
		if(!state.fixedProbFlag$sample23)
			state.logProbability$var23 = Double.NaN;
		for(int j = 0; j < 1; j += 1)
			state.logProbability$bernoulli1[((j - 0) / 1)] = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample50) {
			for(int j = 0; j < 1; j += 1)
				state.logProbability$sample50[((j - 0) / 1)] = Double.NaN;
		}
		for(int k = 1; k < state.coins; k += 1)
			state.logProbability$bernoulli2[((k - 1) / 1)] = Double.NaN;
		if(!state.fixedProbFlag$sample78) {
			for(int k = 1; k < state.coins; k += 1)
				state.logProbability$sample78[((k - 1) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.coins = state.length$flipsMeasured.length;
		for(int index$constrainedFlag$sample23$1 = 0; index$constrainedFlag$sample23$1 < state.constrainedFlag$sample23.length; index$constrainedFlag$sample23$1 += 1)
			state.constrainedFlag$sample23[index$constrainedFlag$sample23$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample10)
			logProbabilityValue$sample10();
		if(state.fixedFlag$sample23)
			logProbabilityValue$sample23();
		logProbabilityValue$sample50();
		logProbabilityValue$sample78();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample23();
		logProbabilityValue$sample50();
		logProbabilityValue$sample78();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample23();
		logProbabilityValue$sample50();
		logProbabilityValue$sample78();
	}

	@Override
	public final void propagateObservedValues() {
		{
			for(int l = 0; l < state.length$flipsMeasured.length; l += 1) {
				boolean[] target = state.intermediateFlips[l];
				for(int m$var102 = 0; m$var102 < state.length$flipsMeasured[l]; m$var102 += 1)
					target[m$var102] = state.flipsMeasured[l][m$var102];
			}
		}
		{
			for(int i$var115 = (state.coins - ((((state.coins - 1) - 0) % 1) + 1)); i$var115 >= ((0 - 1) + 1); i$var115 -= 1) {
				boolean[] cv$source1 = state.intermediateFlips[(state.coins - (i$var115 + 1))];
				boolean[] cv$target1 = state.flips[i$var115];
				int cv$length1 = cv$target1.length;
				for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
					cv$target1[cv$index1] = cv$source1[cv$index1];
			}
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
		     + "public model Flip2CoinsMK12(boolean[][] flipsMeasured) {\n"
		     + "    int coins = flipsMeasured.length;\n"
		     + "         \n"
		     + "    boolean[][] flips = new boolean[coins][];\n"
		     + "    boolean[][] intermediateFlips = new boolean[coins][];\n"
		     + "    double [] bias = new double[coins];\n"
		     + "        \n"
		     + "    Beta beta = beta(1.0, 1.0);\n"
		     + "        \n"
		     + "    bias[0] = beta.sample();\n"
		     + "        \n"
		     + "    for(int i:[1..coins))\n"
		     + "        bias[i] = beta.sample();\n"
		     + "    \n"
		     + "    for(int j:[0..1)) {\n"
		     + "        int samples = flipsMeasured[j].length;\n"
		     + "        Bernoulli bernoulli1 = bernoulli(bias[j]);\n"
		     + "        flips[j] = bernoulli1.sample(samples);\n"
		     + "    }\n"
		     + "                \n"
		     + "    for(int k:[1..coins)) {\n"
		     + "        int samples = flipsMeasured[k].length;\n"
		     + "        Bernoulli bernoulli2 = bernoulli(bias[k]);\n"
		     + "        flips[k] = bernoulli2.sample(samples);\n"
		     + "    }\n"
		     + "        \n"
		     + "    for(int l:[0..coins)) {\n"
		     + "        boolean[] source = flipsMeasured[l];\n"
		     + "        int noFlips = source.length;\n"
		     + "        boolean[] target = new boolean[noFlips];\n"
		     + "        intermediateFlips[l] = target;\n"
		     + "        \n"
		     + "        for(int m:[0..noFlips))\n"
		     + "            target[m] = source[m];\n"
		     + "    }\n"
		     + "        \n"
		     + "    for(int i:[0..coins)) {\n"
		     + "        boolean[] f = flips[i];\n"
		     + "        boolean[] m = intermediateFlips[coins - (i+1)];\n"
		     + "        f.observe(m);\n"
		     + "    }\n"
		     + "}";
	}
}