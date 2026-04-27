package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip2CoinsMK11$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip2CoinsMK11.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip2CoinsMK11$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip2CoinsMK11$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample22(int i$var21) {
		state.bias[i$var21] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void drawValueSample9() {
		state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void inferSample22(int i$var21) {
		if(true) {
			state.constrainedFlag$sample22[((i$var21 - 1) / 1)] = false;
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						{
							for(int j = 0; j < 1; j += 1) {
								if((i$var21 == j)) {
									{
										{
											for(int var48 = 0; var48 < state.length$flipsMeasured[j]; var48 += 1) {
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													state.constrainedFlag$sample22[((i$var21 - 1) / 1)] = true;
													{
														{
															{
																{
																	{
																		cv$count = (cv$count + 1);
																		if(state.flips[j][var48])
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
								if((i$var21 == k)) {
									{
										{
											for(int var75 = 0; var75 < state.length$flipsMeasured[k]; var75 += 1) {
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													state.constrainedFlag$sample22[((i$var21 - 1) / 1)] = true;
													{
														{
															{
																{
																	{
																		cv$count = (cv$count + 1);
																		if(state.flips[k][var75])
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
			if(state.constrainedFlag$sample22[((i$var21 - 1) / 1)]) {
				double var22 = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
				{
					{
						{
							state.bias[i$var21] = var22;
						}
					}
				}
			}
		}
	}

	private final void inferSample9() {
		if(true) {
			state.constrainedFlag$sample9 = false;
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
											for(int var48 = 0; var48 < state.length$flipsMeasured[j]; var48 += 1) {
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													state.constrainedFlag$sample9 = true;
													{
														{
															{
																{
																	{
																		cv$count = (cv$count + 1);
																		if(state.flips[j][var48])
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
											for(int var75 = 0; var75 < state.length$flipsMeasured[k]; var75 += 1) {
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													state.constrainedFlag$sample9 = true;
													{
														{
															{
																{
																	{
																		cv$count = (cv$count + 1);
																		if(state.flips[k][var75])
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
			if(state.constrainedFlag$sample9) {
				double var9 = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
				{
					{
						{
							state.bias[0] = var9;
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample22() {
		if(!state.fixedProbFlag$sample22) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var21 = 1; i$var21 < state.coins; i$var21 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.bias[i$var21];
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
			state.logProbability$var22 = cv$sampleAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample22)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample22 = state.fixedFlag$sample22;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var21 = 1; i$var21 < state.coins; i$var21 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var22;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$beta = (state.logProbability$beta + cv$rvAccumulator);
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample22)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample49() {
		if(!state.fixedProbFlag$sample49) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < 1; j += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var48 = 0; var48 < state.length$flipsMeasured[j]; var48 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							boolean cv$sampleValue = state.flips[j][var48];
							{
								{
									double var37 = state.bias[j];
									double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var37) && (var37 <= 1.0))?Math.log((cv$sampleValue?var37:(1.0 - var37))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample49[((j - 0) / 1)] = cv$sampleAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample49 = (state.fixedFlag$sample9 && state.fixedFlag$sample22);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < 1; j += 1) {
				double cv$rvAccumulator = 0.0;
				for(int var48 = 0; var48 < state.length$flipsMeasured[j]; var48 += 1)
					cv$sampleReached = true;
				double cv$sampleValue = state.logProbability$sample49[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				state.logProbability$bernoulli1[((j - 0) / 1)] = cv$rvAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample77() {
		if(!state.fixedProbFlag$sample77) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int k = 1; k < state.coins; k += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var75 = 0; var75 < state.length$flipsMeasured[k]; var75 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							boolean cv$sampleValue = state.flips[k][var75];
							{
								{
									double var64 = state.bias[k];
									double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var64) && (var64 <= 1.0))?Math.log((cv$sampleValue?var64:(1.0 - var64))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample77[((k - 1) / 1)] = cv$sampleAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample77 = (state.fixedFlag$sample9 && state.fixedFlag$sample22);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int k = 1; k < state.coins; k += 1) {
				double cv$rvAccumulator = 0.0;
				for(int var75 = 0; var75 < state.length$flipsMeasured[k]; var75 += 1)
					cv$sampleReached = true;
				double cv$sampleValue = state.logProbability$sample77[((k - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				state.logProbability$bernoulli2[((k - 1) / 1)] = cv$rvAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!state.fixedProbFlag$sample9) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = state.bias[0];
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
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			state.logProbability$beta = (state.logProbability$beta + cv$sampleAccumulator);
			state.logProbability$var9 = cv$sampleProbability;
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample9)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample9 = state.fixedFlag$sample9;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$var9;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$beta = (state.logProbability$beta + cv$rvAccumulator);
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample9)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample9)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int i$var21 = 1; i$var21 < state.coins; i$var21 += 1) {
			if(!state.fixedFlag$sample22)
				state.bias[i$var21] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		for(int j = 0; j < 1; j += 1) {
			boolean[] var39 = state.flips[j];
			for(int var48 = 0; var48 < state.length$flipsMeasured[j]; var48 += 1)
				var39[var48] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[j]);
		}
		for(int k = 1; k < state.coins; k += 1) {
			boolean[] var66 = state.flips[k];
			for(int var75 = 0; var75 < state.length$flipsMeasured[k]; var75 += 1)
				var66[var75] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[k]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample9)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int i$var21 = 1; i$var21 < state.coins; i$var21 += 1) {
			if(!state.fixedFlag$sample22)
				state.bias[i$var21] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample9)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int i$var21 = 1; i$var21 < state.coins; i$var21 += 1) {
			if(!state.fixedFlag$sample22)
				state.bias[i$var21] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		for(int j = 0; j < 1; j += 1) {
			boolean[] var39 = state.flips[j];
			for(int var48 = 0; var48 < state.length$flipsMeasured[j]; var48 += 1)
				var39[var48] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[j]);
		}
		for(int k = 1; k < state.coins; k += 1) {
			boolean[] var66 = state.flips[k];
			for(int var75 = 0; var75 < state.length$flipsMeasured[k]; var75 += 1)
				var66[var75] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[k]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample9)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int i$var21 = 1; i$var21 < state.coins; i$var21 += 1) {
			if(!state.fixedFlag$sample22)
				state.bias[i$var21] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample9)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int i$var21 = 1; i$var21 < state.coins; i$var21 += 1) {
			if(!state.fixedFlag$sample22)
				state.bias[i$var21] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample9)
				inferSample9();
			for(int i$var21 = 1; i$var21 < state.coins; i$var21 += 1) {
				if(!state.fixedFlag$sample22)
					inferSample22(i$var21);
			}
		} else {
			for(int i$var21 = (state.coins - ((((state.coins - 1) - 1) % 1) + 1)); i$var21 >= ((1 - 1) + 1); i$var21 -= 1) {
				if(!state.fixedFlag$sample22)
					inferSample22(i$var21);
			}
			if(!state.fixedFlag$sample9)
				inferSample9();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample9)
			drawValueSample9();
		for(int i$var21 = 1; i$var21 < state.coins; i$var21 += 1) {
			if(!state.constrainedFlag$sample22[((i$var21 - 1) / 1)])
				drawValueSample22(i$var21);
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$beta = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample9)
			state.logProbability$var9 = Double.NaN;
		if(!state.fixedProbFlag$sample22)
			state.logProbability$var22 = Double.NaN;
		for(int j = 0; j < 1; j += 1)
			state.logProbability$bernoulli1[((j - 0) / 1)] = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample49) {
			for(int j = 0; j < 1; j += 1)
				state.logProbability$sample49[((j - 0) / 1)] = Double.NaN;
		}
		for(int k = 1; k < state.coins; k += 1)
			state.logProbability$bernoulli2[((k - 1) / 1)] = Double.NaN;
		if(!state.fixedProbFlag$sample77) {
			for(int k = 1; k < state.coins; k += 1)
				state.logProbability$sample77[((k - 1) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.coins = state.length$flipsMeasured.length;
		for(int index$constrainedFlag$sample22$1 = 0; index$constrainedFlag$sample22$1 < state.constrainedFlag$sample22.length; index$constrainedFlag$sample22$1 += 1)
			state.constrainedFlag$sample22[index$constrainedFlag$sample22$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample9)
			logProbabilityValue$sample9();
		if(state.fixedFlag$sample22)
			logProbabilityValue$sample22();
		logProbabilityValue$sample49();
		logProbabilityValue$sample77();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample49();
		logProbabilityValue$sample77();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample49();
		logProbabilityValue$sample77();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i$var88 = (state.coins - ((((state.coins - 1) - 0) % 1) + 1)); i$var88 >= ((0 - 1) + 1); i$var88 -= 1) {
			boolean[] cv$source1 = state.flipsMeasured[(state.coins - (i$var88 + 1))];
			boolean[] cv$target1 = state.flips[i$var88];
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				cv$target1[cv$index1] = cv$source1[cv$index1];
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
		     + "public model Flip2CoinsMK11(boolean[][] flipsMeasured) {\n"
		     + "    int coins = flipsMeasured.length;\n"
		     + "         \n"
		     + "    boolean[][] flips = new boolean[coins][];\n"
		     + "    double [] bias = new double[coins];\n"
		     + "        \n"
		     + "    Beta beta = beta(1.0, 1.0);\n"
		     + "        \n"
		     + "    bias[0] = beta.sample();\n"
		     + "        \n"
		     + "    for(int i:[1..coins))\n"
		     + "        bias[i] = beta.sample();\n"
		     + "        \n"
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
		     + "    for(int i:[0..coins)) {\n"
		     + "        boolean[] f = flips[i];\n"
		     + "        boolean[] m = flipsMeasured[coins - (i+1)];\n"
		     + "        f.observe(m);\n"
		     + "    }\n"
		     + "}";
	}
}