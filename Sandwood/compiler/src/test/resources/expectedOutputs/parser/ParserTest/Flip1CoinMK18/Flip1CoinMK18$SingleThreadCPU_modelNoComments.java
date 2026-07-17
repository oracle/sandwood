package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK18$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK18.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK18$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK18$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample11() {
		state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		{
			{
				{
					double[][] var21 = state.bias[0];
					double[] var36 = var21[1];
					var36[0] = (1 - state.q);
				}
			}
		}
		{
			boolean guard$sample11put86 = false;
			{
				if(!guard$sample11put86) {
					guard$sample11put86 = true;
					{
						double[][] var52 = state.bias[1];
						double[] var54 = var52[0];
						var54[1] = (1 - state.q);
						double[] var67 = var52[1];
						var67[0] = (1 - state.q);
						var67[1] = state.q;
					}
				}
			}
			{
				if(!guard$sample11put86) {
					guard$sample11put86 = true;
					{
						double[][] var52 = state.bias[1];
						double[] var54 = var52[0];
						var54[1] = (1 - state.q);
						double[] var67 = var52[1];
						var67[0] = (1 - state.q);
						var67[1] = state.q;
					}
				}
			}
			{
				if(!guard$sample11put86) {
					guard$sample11put86 = true;
					{
						double[][] var52 = state.bias[1];
						double[] var54 = var52[0];
						var54[1] = (1 - state.q);
						double[] var67 = var52[1];
						var67[0] = (1 - state.q);
						var67[1] = state.q;
					}
				}
			}
		}
	}

	private final void drawValueSample17() {
		state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		{
			boolean guard$sample17put52 = false;
			{
				if(!guard$sample17put52) {
					guard$sample17put52 = true;
					{
						double[][] var21 = state.bias[0];
						double[] var23 = var21[0];
						var23[0] = state.t;
						var23[1] = (1 - state.t);
						double[] var36 = var21[1];
						var36[1] = state.t;
					}
				}
			}
			{
				if(!guard$sample17put52) {
					guard$sample17put52 = true;
					{
						double[][] var21 = state.bias[0];
						double[] var23 = var21[0];
						var23[0] = state.t;
						var23[1] = (1 - state.t);
						double[] var36 = var21[1];
						var36[1] = state.t;
					}
				}
			}
			{
				if(!guard$sample17put52) {
					guard$sample17put52 = true;
					{
						double[][] var21 = state.bias[0];
						double[] var23 = var21[0];
						var23[0] = state.t;
						var23[1] = (1 - state.t);
						double[] var36 = var21[1];
						var36[1] = state.t;
					}
				}
			}
		}
		{
			{
				{
					double[][] var52 = state.bias[1];
					double[] var54 = var52[0];
					var54[0] = state.t;
				}
			}
		}
	}

	private final void inferSample11() {
		if(true) {
			state.constrainedFlag$sample11 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = state.q;
			double cv$originalProbability = 0.0;
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample11 || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						state.q = cv$proposedValue;
						{
							{
								{
									double[][] var21 = state.bias[0];
									double[] var36 = var21[1];
									var36[0] = (1 - cv$currentValue);
								}
							}
						}
						{
							boolean guard$sample11put86 = false;
							{
								if(!guard$sample11put86) {
									guard$sample11put86 = true;
									{
										double[][] var52 = state.bias[1];
										double[] var54 = var52[0];
										var54[1] = (1 - cv$currentValue);
										double[] var67 = var52[1];
										var67[0] = (1 - cv$currentValue);
										var67[1] = cv$currentValue;
									}
								}
							}
							{
								if(!guard$sample11put86) {
									guard$sample11put86 = true;
									{
										double[][] var52 = state.bias[1];
										double[] var54 = var52[0];
										var54[1] = (1 - cv$currentValue);
										double[] var67 = var52[1];
										var67[0] = (1 - cv$currentValue);
										var67[1] = cv$currentValue;
									}
								}
							}
							{
								if(!guard$sample11put86) {
									guard$sample11put86 = true;
									{
										double[][] var52 = state.bias[1];
										double[] var54 = var52[0];
										var54[1] = (1 - cv$currentValue);
										double[] var67 = var52[1];
										var67[0] = (1 - cv$currentValue);
										var67[1] = cv$currentValue;
									}
								}
							}
						}
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, 1.0, 1.0));
						{
							{
								{
									double traceTempVariable$q$5_1 = cv$currentValue;
									double traceTempVariable$var84$5_2 = (1 - traceTempVariable$q$5_1);
									if((0 == state.a)) {
										if((1 == state.b)) {
											if((0 == state.c)) {
												{
													{
														for(int var96 = 0; var96 < state.samples; var96 += 1) {
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample11 = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var84$5_2) && (traceTempVariable$var84$5_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$5_2:(1.0 - traceTempVariable$var84$5_2))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var84$5_2) && (traceTempVariable$var84$5_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$5_2:(1.0 - traceTempVariable$var84$5_2))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$5_2) && (traceTempVariable$var84$5_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$5_2:(1.0 - traceTempVariable$var84$5_2))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$5_2) && (traceTempVariable$var84$5_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$5_2:(1.0 - traceTempVariable$var84$5_2))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$5_2) && (traceTempVariable$var84$5_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$5_2:(1.0 - traceTempVariable$var84$5_2))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
																cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
																	else
																		cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
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
									double traceTempVariable$q$6_1 = cv$currentValue;
									double traceTempVariable$var84$6_2 = (1 - traceTempVariable$q$6_1);
									if((1 == state.a)) {
										if((0 == state.b)) {
											if((1 == state.c)) {
												{
													{
														for(int var96 = 0; var96 < state.samples; var96 += 1) {
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample11 = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var84$6_2) && (traceTempVariable$var84$6_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var84$6_2) && (traceTempVariable$var84$6_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$6_2) && (traceTempVariable$var84$6_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$6_2) && (traceTempVariable$var84$6_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$6_2) && (traceTempVariable$var84$6_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
																cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
																	else
																		cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
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
									double traceTempVariable$q$7_1 = cv$currentValue;
									double traceTempVariable$var84$7_2 = (1 - traceTempVariable$q$7_1);
									if((1 == state.a)) {
										if((1 == state.b)) {
											if((0 == state.c)) {
												{
													{
														for(int var96 = 0; var96 < state.samples; var96 += 1) {
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample11 = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var84$7_2) && (traceTempVariable$var84$7_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$7_2:(1.0 - traceTempVariable$var84$7_2))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var84$7_2) && (traceTempVariable$var84$7_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$7_2:(1.0 - traceTempVariable$var84$7_2))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$7_2) && (traceTempVariable$var84$7_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$7_2:(1.0 - traceTempVariable$var84$7_2))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$7_2) && (traceTempVariable$var84$7_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$7_2:(1.0 - traceTempVariable$var84$7_2))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$7_2) && (traceTempVariable$var84$7_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$7_2:(1.0 - traceTempVariable$var84$7_2))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
																cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
																	else
																		cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
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
									double traceTempVariable$var84$8_1 = cv$currentValue;
									if((1 == state.a)) {
										if((1 == state.b)) {
											if((1 == state.c)) {
												{
													{
														for(int var96 = 0; var96 < state.samples; var96 += 1) {
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample11 = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var84$8_1) && (traceTempVariable$var84$8_1 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$8_1:(1.0 - traceTempVariable$var84$8_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var84$8_1) && (traceTempVariable$var84$8_1 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$8_1:(1.0 - traceTempVariable$var84$8_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$8_1) && (traceTempVariable$var84$8_1 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$8_1:(1.0 - traceTempVariable$var84$8_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$8_1) && (traceTempVariable$var84$8_1 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$8_1:(1.0 - traceTempVariable$var84$8_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$8_1) && (traceTempVariable$var84$8_1 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$8_1:(1.0 - traceTempVariable$var84$8_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
																cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
																	else
																		cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
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
						if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
						else {
							if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
								cv$stateProbabilityValue = cv$accumulatedProbabilities;
							else
								cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
						}
					}
					if((cv$valuePos == 0))
						cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
					else
						cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$))))) || Double.isNaN(cv$ratio))) {
							state.q = cv$originalValue;
							{
								{
									{
										double[][] var21 = state.bias[0];
										double[] var36 = var21[1];
										var36[0] = (1 - state.q);
									}
								}
							}
							{
								boolean guard$sample11put86 = false;
								{
									if(!guard$sample11put86) {
										guard$sample11put86 = true;
										{
											double[][] var52 = state.bias[1];
											double[] var54 = var52[0];
											var54[1] = (1 - state.q);
											double[] var67 = var52[1];
											var67[0] = (1 - state.q);
											var67[1] = state.q;
										}
									}
								}
								{
									if(!guard$sample11put86) {
										guard$sample11put86 = true;
										{
											double[][] var52 = state.bias[1];
											double[] var54 = var52[0];
											var54[1] = (1 - state.q);
											double[] var67 = var52[1];
											var67[0] = (1 - state.q);
											var67[1] = state.q;
										}
									}
								}
								{
									if(!guard$sample11put86) {
										guard$sample11put86 = true;
										{
											double[][] var52 = state.bias[1];
											double[] var54 = var52[0];
											var54[1] = (1 - state.q);
											double[] var67 = var52[1];
											var67[0] = (1 - state.q);
											var67[1] = state.q;
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

	private final void inferSample17() {
		if(true) {
			state.constrainedFlag$sample17 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = state.t;
			double cv$originalProbability = 0.0;
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample17 || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						state.t = cv$proposedValue;
						{
							boolean guard$sample17put52 = false;
							{
								if(!guard$sample17put52) {
									guard$sample17put52 = true;
									{
										double[][] var21 = state.bias[0];
										double[] var23 = var21[0];
										var23[0] = cv$currentValue;
										var23[1] = (1 - cv$currentValue);
										double[] var36 = var21[1];
										var36[1] = cv$currentValue;
									}
								}
							}
							{
								if(!guard$sample17put52) {
									guard$sample17put52 = true;
									{
										double[][] var21 = state.bias[0];
										double[] var23 = var21[0];
										var23[0] = cv$currentValue;
										var23[1] = (1 - cv$currentValue);
										double[] var36 = var21[1];
										var36[1] = cv$currentValue;
									}
								}
							}
							{
								if(!guard$sample17put52) {
									guard$sample17put52 = true;
									{
										double[][] var21 = state.bias[0];
										double[] var23 = var21[0];
										var23[0] = cv$currentValue;
										var23[1] = (1 - cv$currentValue);
										double[] var36 = var21[1];
										var36[1] = cv$currentValue;
									}
								}
							}
						}
						{
							{
								{
									double[][] var52 = state.bias[1];
									double[] var54 = var52[0];
									var54[0] = cv$currentValue;
								}
							}
						}
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, 1.0, 1.0));
						{
							{
								{
									double traceTempVariable$var84$5_1 = cv$currentValue;
									if((0 == state.a)) {
										if((0 == state.b)) {
											if((0 == state.c)) {
												{
													{
														for(int var96 = 0; var96 < state.samples; var96 += 1) {
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample17 = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var84$5_1) && (traceTempVariable$var84$5_1 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$5_1:(1.0 - traceTempVariable$var84$5_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var84$5_1) && (traceTempVariable$var84$5_1 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$5_1:(1.0 - traceTempVariable$var84$5_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$5_1) && (traceTempVariable$var84$5_1 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$5_1:(1.0 - traceTempVariable$var84$5_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$5_1) && (traceTempVariable$var84$5_1 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$5_1:(1.0 - traceTempVariable$var84$5_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$5_1) && (traceTempVariable$var84$5_1 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$5_1:(1.0 - traceTempVariable$var84$5_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
																cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
																	else
																		cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
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
									double traceTempVariable$t$6_1 = cv$currentValue;
									double traceTempVariable$var84$6_2 = (1 - traceTempVariable$t$6_1);
									if((0 == state.a)) {
										if((0 == state.b)) {
											if((1 == state.c)) {
												{
													{
														for(int var96 = 0; var96 < state.samples; var96 += 1) {
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample17 = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var84$6_2) && (traceTempVariable$var84$6_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var84$6_2) && (traceTempVariable$var84$6_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$6_2) && (traceTempVariable$var84$6_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$6_2) && (traceTempVariable$var84$6_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$6_2) && (traceTempVariable$var84$6_2 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$6_2:(1.0 - traceTempVariable$var84$6_2))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
																cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
																	else
																		cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
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
									double traceTempVariable$var84$7_1 = cv$currentValue;
									if((0 == state.a)) {
										if((1 == state.b)) {
											if((1 == state.c)) {
												{
													{
														for(int var96 = 0; var96 < state.samples; var96 += 1) {
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample17 = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var84$7_1) && (traceTempVariable$var84$7_1 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$7_1:(1.0 - traceTempVariable$var84$7_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var84$7_1) && (traceTempVariable$var84$7_1 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$7_1:(1.0 - traceTempVariable$var84$7_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$7_1) && (traceTempVariable$var84$7_1 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$7_1:(1.0 - traceTempVariable$var84$7_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$7_1) && (traceTempVariable$var84$7_1 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$7_1:(1.0 - traceTempVariable$var84$7_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$7_1) && (traceTempVariable$var84$7_1 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$7_1:(1.0 - traceTempVariable$var84$7_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
																cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
																	else
																		cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
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
									double traceTempVariable$var84$8_1 = cv$currentValue;
									if((1 == state.a)) {
										if((0 == state.b)) {
											if((0 == state.c)) {
												{
													{
														for(int var96 = 0; var96 < state.samples; var96 += 1) {
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample17 = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var84$8_1) && (traceTempVariable$var84$8_1 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$8_1:(1.0 - traceTempVariable$var84$8_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var84$8_1) && (traceTempVariable$var84$8_1 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$8_1:(1.0 - traceTempVariable$var84$8_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$8_1) && (traceTempVariable$var84$8_1 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$8_1:(1.0 - traceTempVariable$var84$8_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$8_1) && (traceTempVariable$var84$8_1 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$8_1:(1.0 - traceTempVariable$var84$8_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var84$8_1) && (traceTempVariable$var84$8_1 <= 1.0))?Math.log((state.flips[var96]?traceTempVariable$var84$8_1:(1.0 - traceTempVariable$var84$8_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
																			}
																		}
																	}
																}
																cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
																if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
																	else
																		cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
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
						if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
						else {
							if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
								cv$stateProbabilityValue = cv$accumulatedProbabilities;
							else
								cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
						}
					}
					if((cv$valuePos == 0))
						cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
					else
						cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$))))) || Double.isNaN(cv$ratio))) {
							state.t = cv$originalValue;
							{
								boolean guard$sample17put52 = false;
								{
									if(!guard$sample17put52) {
										guard$sample17put52 = true;
										{
											double[][] var21 = state.bias[0];
											double[] var23 = var21[0];
											var23[0] = state.t;
											var23[1] = (1 - state.t);
											double[] var36 = var21[1];
											var36[1] = state.t;
										}
									}
								}
								{
									if(!guard$sample17put52) {
										guard$sample17put52 = true;
										{
											double[][] var21 = state.bias[0];
											double[] var23 = var21[0];
											var23[0] = state.t;
											var23[1] = (1 - state.t);
											double[] var36 = var21[1];
											var36[1] = state.t;
										}
									}
								}
								{
									if(!guard$sample17put52) {
										guard$sample17put52 = true;
										{
											double[][] var21 = state.bias[0];
											double[] var23 = var21[0];
											var23[0] = state.t;
											var23[1] = (1 - state.t);
											double[] var36 = var21[1];
											var36[1] = state.t;
										}
									}
								}
							}
							{
								{
									{
										double[][] var52 = state.bias[1];
										double[] var54 = var52[0];
										var54[0] = state.t;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample103() {
		if(!state.fixedProbFlag$sample103) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var96 = 0; var96 < state.samples; var96 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.flips[var96];
						{
							{
								double var84 = state.bias[state.a][state.b][state.c];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$var97 = cv$sampleAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample103 = (state.fixedFlag$sample11 && state.fixedFlag$sample17);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var96 = 0; var96 < state.samples; var96 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var97;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$bernoulli = cv$rvAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
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
					double cv$sampleValue = state.q;
					{
						{
							double var8 = 1.0;
							double var9 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var8, var9));
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
			{
				{
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
					}
				}
				{
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
					}
				}
				{
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample11)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample11 = state.fixedFlag$sample11;
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
			{
				{
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
					}
				}
				{
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
					}
				}
				{
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample11)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample17() {
		if(!state.fixedProbFlag$sample17) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = state.t;
					{
						{
							double var14 = 1.0;
							double var15 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var14, var15));
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
				{
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
					}
				}
				{
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
					}
				}
			}
			{
				{
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample17)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample17 = state.fixedFlag$sample17;
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
				{
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
					}
				}
				{
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
					}
				}
			}
			{
				{
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample17)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample11)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample17)
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		double[][] var21 = state.bias[0];
		double[] var23 = var21[0];
		if(!state.fixedFlag$sample17)
			var23[0] = state.t;
		if(!state.fixedFlag$sample17)
			var23[1] = (1 - state.t);
		double[] var36 = var21[1];
		if(!state.fixedFlag$sample17)
			var36[0] = (1 - state.q);
		if(!state.fixedFlag$sample17)
			var36[1] = state.t;
		double[][] var52 = state.bias[1];
		double[] var54 = var52[0];
		if(!state.fixedFlag$sample11)
			var54[0] = state.t;
		if(!state.fixedFlag$sample11)
			var54[1] = (1 - state.q);
		double[] var67 = var52[1];
		if(!state.fixedFlag$sample11)
			var67[0] = (1 - state.q);
		if(!state.fixedFlag$sample11)
			var67[1] = state.q;
		for(int var96 = 0; var96 < state.samples; var96 += 1)
			state.flips[var96] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[state.a][state.b][state.c]);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample11)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample17)
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		double[][] var21 = state.bias[0];
		double[] var23 = var21[0];
		var23[0] = state.t;
		var23[1] = (1 - state.t);
		double[] var36 = var21[1];
		var36[0] = (1 - state.q);
		var36[1] = state.t;
		double[][] var52 = state.bias[1];
		double[] var54 = var52[0];
		var54[0] = state.t;
		var54[1] = (1 - state.q);
		double[] var67 = var52[1];
		var67[0] = (1 - state.q);
		var67[1] = state.q;
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample11)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample17)
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		double[][] var21 = state.bias[0];
		double[] var23 = var21[0];
		var23[0] = state.t;
		var23[1] = (1 - state.t);
		double[] var36 = var21[1];
		var36[0] = (1 - state.q);
		var36[1] = state.t;
		double[][] var52 = state.bias[1];
		double[] var54 = var52[0];
		var54[0] = state.t;
		var54[1] = (1 - state.q);
		double[] var67 = var52[1];
		var67[0] = (1 - state.q);
		var67[1] = state.q;
		for(int var96 = 0; var96 < state.samples; var96 += 1)
			state.flips[var96] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[state.a][state.b][state.c]);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample11)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample17)
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		double[][] var21 = state.bias[0];
		double[] var23 = var21[0];
		if(!state.fixedFlag$sample17)
			var23[0] = state.t;
		if(!state.fixedFlag$sample17)
			var23[1] = (1 - state.t);
		double[] var36 = var21[1];
		if(!state.fixedFlag$sample17)
			var36[0] = (1 - state.q);
		if(!state.fixedFlag$sample17)
			var36[1] = state.t;
		double[][] var52 = state.bias[1];
		double[] var54 = var52[0];
		if(!state.fixedFlag$sample11)
			var54[0] = state.t;
		if(!state.fixedFlag$sample11)
			var54[1] = (1 - state.q);
		double[] var67 = var52[1];
		if(!state.fixedFlag$sample11)
			var67[0] = (1 - state.q);
		if(!state.fixedFlag$sample11)
			var67[1] = state.q;
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample11)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample17)
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		double[][] var21 = state.bias[0];
		double[] var23 = var21[0];
		var23[0] = state.t;
		var23[1] = (1 - state.t);
		double[] var36 = var21[1];
		var36[0] = (1 - state.q);
		var36[1] = state.t;
		double[][] var52 = state.bias[1];
		double[] var54 = var52[0];
		var54[0] = state.t;
		var54[1] = (1 - state.q);
		double[] var67 = var52[1];
		var67[0] = (1 - state.q);
		var67[1] = state.q;
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample11)
				inferSample11();
			if(!state.fixedFlag$sample17)
				inferSample17();
		} else {
			if(!state.fixedFlag$sample17)
				inferSample17();
			if(!state.fixedFlag$sample11)
				inferSample11();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample11)
			drawValueSample11();
		if(!state.constrainedFlag$sample17)
			drawValueSample17();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample11)
			state.logProbability$q = Double.NaN;
		if(!state.fixedProbFlag$sample17)
			state.logProbability$t = Double.NaN;
		state.logProbability$bernoulli = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample103)
			state.logProbability$var97 = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample11)
			logProbabilityValue$sample11();
		if(state.fixedFlag$sample17)
			logProbabilityValue$sample17();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample11();
		logProbabilityValue$sample17();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample11();
		logProbabilityValue$sample17();
		logProbabilityValue$sample103();
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
		double[][] var21 = state.bias[0];
		double[] var23 = var21[0];
		var23[0] = state.t;
		var23[1] = (1 - state.t);
		double[] var36 = var21[1];
		var36[0] = (1 - state.q);
		var36[1] = state.t;
		double[][] var52 = state.bias[1];
		double[] var54 = var52[0];
		var54[0] = state.t;
		var54[1] = (1 - state.q);
		double[] var67 = var52[1];
		var67[0] = (1 - state.q);
		var67[1] = state.q;
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
		     + "public model Flip1CoinMK18(int samples, int a, int b, int c, boolean[] flipsMeasured) {\n"
		     + "    \n"
		     + "    double q = beta(1,1).sample();\n"
		     + "    double t = beta(1,1).sample();\n"
		     + "    double[][][] bias = {{{t, 1-t},{1-q, t}},{{t, 1-q},{1-q, q}}};\n"
		     + "    \n"
		     + "    Bernoulli bernoulli = bernoulli(bias[a][b][c]);\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "    \n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}