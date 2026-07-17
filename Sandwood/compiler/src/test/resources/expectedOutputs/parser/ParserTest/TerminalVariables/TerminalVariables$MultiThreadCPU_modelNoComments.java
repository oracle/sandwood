package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.TerminalVariables$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.TerminalVariables.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class TerminalVariables$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var45$stateProbabilityGlobal;
		double[] cv$var50$stateProbabilityGlobal;
		double[] cv$var53$stateProbabilityGlobal;
		double[] cv$var55$stateProbabilityGlobal;
		double[] cv$var60$stateProbabilityGlobal;
		double[] cv$var65$stateProbabilityGlobal;
		double[] cv$var70$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			{
				cv$var45$stateProbabilityGlobal = new double[2];
			}
			{
				cv$var50$stateProbabilityGlobal = new double[2];
			}
			{
				int cv$var43$max = 2;
				cv$var43$max = Math.max(cv$var43$max, 2);
				cv$var53$stateProbabilityGlobal = new double[cv$var43$max];
			}
			{
				cv$var55$stateProbabilityGlobal = new double[2];
			}
			{
				cv$var60$stateProbabilityGlobal = new double[2];
			}
			{
				cv$var65$stateProbabilityGlobal = new double[2];
			}
			{
				cv$var70$stateProbabilityGlobal = new double[2];
			}
		}
	}


	public TerminalVariables$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample47() {
		state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	private final void drawValueSample52() {
		state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	private final void drawValueSample55() {
		int lengthCV$conditionals$53_13 = -1;
		{
			{
				if((0 == state.c3))
					lengthCV$conditionals$53_13 = 2;
			}
		}
		{
			{
				if((1 == state.c3))
					lengthCV$conditionals$53_13 = 2;
			}
		}
		state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_13);
	}

	private final void drawValueSample57() {
		state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	private final void drawValueSample60() {
		int lengthCV$conditionals$58_9 = -1;
		{
			{
				if((0 == state.c5))
					lengthCV$conditionals$58_9 = 2;
			}
		}
		{
			{
				if((1 == state.c5))
					lengthCV$conditionals$58_9 = 2;
			}
		}
		state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_9);
	}

	private final void drawValueSample62() {
		state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	private final void drawValueSample636() {
		int lengthCV$var601$634_15 = -1;
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4))
								lengthCV$var601$634_15 = 5;
						}
					}
				}
			}
		}
		state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_15);
	}

	private final void drawValueSample65() {
		int lengthCV$conditionals$63_9 = -1;
		{
			{
				if((0 == state.c7))
					lengthCV$conditionals$63_9 = 2;
			}
		}
		{
			{
				if((1 == state.c7))
					lengthCV$conditionals$63_9 = 2;
			}
		}
		state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_9);
	}

	private final void drawValueSample67() {
		state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	private final void drawValueSample70() {
		int lengthCV$conditionals$68_9 = -1;
		{
			{
				if((0 == state.c9))
					lengthCV$conditionals$68_9 = 2;
			}
		}
		{
			{
				if((1 == state.c9))
					lengthCV$conditionals$68_9 = 2;
			}
		}
		state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_9);
	}

	private final void drawValueSample72() {
		state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
	}

	private final void drawValueSample75() {
		int lengthCV$conditionals$73_9 = -1;
		{
			{
				if((0 == state.c11))
					lengthCV$conditionals$73_9 = 2;
			}
		}
		{
			{
				if((1 == state.c11))
					lengthCV$conditionals$73_9 = 2;
			}
		}
		state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_9);
	}

	private final void inferSample47() {
		if(true) {
			state.constrainedFlag$sample47 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var45$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				state.c1 = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$currentValue])) && (state.priors[cv$currentValue] <= 1.0))?Math.log(state.priors[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$c1$1_1 = cv$currentValue;
								{
									{
										boolean cv$sampleConstrained = true;
										if(cv$sampleConstrained) {
											state.constrainedFlag$sample47 = true;
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															{
																double[] var46 = state.conditionals[traceTempVariable$c1$1_1];
																int lengthCV$conditionals$48_4 = -1;
																{
																	{
																		if((0 == traceTempVariable$c1$1_1))
																			lengthCV$conditionals$48_4 = 2;
																	}
																}
																{
																	{
																		if((1 == traceTempVariable$c1$1_1))
																			lengthCV$conditionals$48_4 = 2;
																	}
																}
																if(((Math.log(1.0) + ((((((0.0 <= state.c2) && (state.c2 < lengthCV$conditionals$48_4)) && (0 < lengthCV$conditionals$48_4)) && (0.0 <= var46[state.c2])) && (var46[state.c2] <= 1.0))?Math.log(var46[state.c2]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.c2) && (state.c2 < lengthCV$conditionals$48_4)) && (0 < lengthCV$conditionals$48_4)) && (0.0 <= var46[state.c2])) && (var46[state.c2] <= 1.0))?Math.log(var46[state.c2]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.c2) && (state.c2 < lengthCV$conditionals$48_4)) && (0 < lengthCV$conditionals$48_4)) && (0.0 <= var46[state.c2])) && (var46[state.c2] <= 1.0))?Math.log(var46[state.c2]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.c2) && (state.c2 < lengthCV$conditionals$48_4)) && (0 < lengthCV$conditionals$48_4)) && (0.0 <= var46[state.c2])) && (var46[state.c2] <= 1.0))?Math.log(var46[state.c2]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.c2) && (state.c2 < lengthCV$conditionals$48_4)) && (0 < lengthCV$conditionals$48_4)) && (0.0 <= var46[state.c2])) && (var46[state.c2] <= 1.0))?Math.log(var46[state.c2]):Double.NEGATIVE_INFINITY)));
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
					{
						{
							{
								int traceTempVariable$c1$6_1 = cv$currentValue;
								{
									{
										boolean cv$sampleConstrained = state.fixedFlag$sample636;
										if(cv$sampleConstrained) {
											state.constrainedFlag$sample47 = true;
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															{
																double[] var602 = state.a[state.c5][state.c9][traceTempVariable$c1$6_1][state.c4];
																int lengthCV$var601$634_11 = -1;
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == state.c9)) {
																				if((0 == traceTempVariable$c1$6_1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == state.c9)) {
																				if((0 == traceTempVariable$c1$6_1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == state.c9)) {
																				if((1 == traceTempVariable$c1$6_1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == state.c9)) {
																				if((1 == traceTempVariable$c1$6_1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == state.c9)) {
																				if((0 == traceTempVariable$c1$6_1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == state.c9)) {
																				if((0 == traceTempVariable$c1$6_1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == state.c9)) {
																				if((1 == traceTempVariable$c1$6_1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == state.c9)) {
																				if((1 == traceTempVariable$c1$6_1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == state.c9)) {
																				if((0 == traceTempVariable$c1$6_1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == state.c9)) {
																				if((0 == traceTempVariable$c1$6_1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == state.c9)) {
																				if((1 == traceTempVariable$c1$6_1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == state.c9)) {
																				if((1 == traceTempVariable$c1$6_1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == state.c9)) {
																				if((0 == traceTempVariable$c1$6_1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == state.c9)) {
																				if((0 == traceTempVariable$c1$6_1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == state.c9)) {
																				if((1 == traceTempVariable$c1$6_1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == state.c9)) {
																				if((1 == traceTempVariable$c1$6_1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_11 = 5;
																				}
																			}
																		}
																	}
																}
																if(((Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_11)) && (0 < lengthCV$var601$634_11)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_11)) && (0 < lengthCV$var601$634_11)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_11)) && (0 < lengthCV$var601$634_11)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_11)) && (0 < lengthCV$var601$634_11)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_11)) && (0 < lengthCV$var601$634_11)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)));
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
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample47) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				state.c1 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
			}
		}
	}

	private final void inferSample52() {
		if(true) {
			state.constrainedFlag$sample52 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var50$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				state.c3 = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$currentValue])) && (state.priors[cv$currentValue] <= 1.0))?Math.log(state.priors[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$c3$1_1 = cv$currentValue;
								{
									{
										boolean cv$sampleConstrained = (state.fixedFlag$sample55 || state.constrainedFlag$sample55);
										if(cv$sampleConstrained) {
											state.constrainedFlag$sample52 = true;
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															{
																double[] var51 = state.conditionals[traceTempVariable$c3$1_1];
																int lengthCV$conditionals$53_10 = -1;
																{
																	{
																		if((0 == traceTempVariable$c3$1_1))
																			lengthCV$conditionals$53_10 = 2;
																	}
																}
																{
																	{
																		if((1 == traceTempVariable$c3$1_1))
																			lengthCV$conditionals$53_10 = 2;
																	}
																}
																if(((Math.log(1.0) + ((((((0.0 <= state.c4) && (state.c4 < lengthCV$conditionals$53_10)) && (0 < lengthCV$conditionals$53_10)) && (0.0 <= var51[state.c4])) && (var51[state.c4] <= 1.0))?Math.log(var51[state.c4]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.c4) && (state.c4 < lengthCV$conditionals$53_10)) && (0 < lengthCV$conditionals$53_10)) && (0.0 <= var51[state.c4])) && (var51[state.c4] <= 1.0))?Math.log(var51[state.c4]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.c4) && (state.c4 < lengthCV$conditionals$53_10)) && (0 < lengthCV$conditionals$53_10)) && (0.0 <= var51[state.c4])) && (var51[state.c4] <= 1.0))?Math.log(var51[state.c4]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.c4) && (state.c4 < lengthCV$conditionals$53_10)) && (0 < lengthCV$conditionals$53_10)) && (0.0 <= var51[state.c4])) && (var51[state.c4] <= 1.0))?Math.log(var51[state.c4]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.c4) && (state.c4 < lengthCV$conditionals$53_10)) && (0 < lengthCV$conditionals$53_10)) && (0.0 <= var51[state.c4])) && (var51[state.c4] <= 1.0))?Math.log(var51[state.c4]):Double.NEGATIVE_INFINITY)));
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
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample52) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				state.c3 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
			}
		}
	}

	private final void inferSample55() {
		if(true) {
			state.constrainedFlag$sample55 = false;
			int cv$numStates = 0;
			{
				int lengthCV$conditionals$53_11 = -1;
				{
					{
						if((0 == state.c3))
							lengthCV$conditionals$53_11 = 2;
					}
				}
				{
					{
						if((1 == state.c3))
							lengthCV$conditionals$53_11 = 2;
					}
				}
				cv$numStates = Math.max(cv$numStates, lengthCV$conditionals$53_11);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var53$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				state.c4 = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] var51 = state.conditionals[state.c3];
					int lengthCV$conditionals$53_12 = -1;
					{
						{
							if((0 == state.c3))
								lengthCV$conditionals$53_12 = 2;
						}
					}
					{
						{
							if((1 == state.c3))
								lengthCV$conditionals$53_12 = 2;
						}
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < lengthCV$conditionals$53_12)) && (0 < lengthCV$conditionals$53_12)) && (0.0 <= var51[cv$currentValue])) && (var51[cv$currentValue] <= 1.0))?Math.log(var51[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$c4$5_1 = cv$currentValue;
								{
									{
										boolean cv$sampleConstrained = state.fixedFlag$sample636;
										if(cv$sampleConstrained) {
											state.constrainedFlag$sample55 = true;
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															{
																double[] var602 = state.a[state.c5][state.c9][state.c1][traceTempVariable$c4$5_1];
																int lengthCV$var601$634_12 = -1;
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == state.c9)) {
																				if((0 == state.c1)) {
																					if((0 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == state.c9)) {
																				if((0 == state.c1)) {
																					if((1 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == state.c9)) {
																				if((1 == state.c1)) {
																					if((0 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == state.c9)) {
																				if((1 == state.c1)) {
																					if((1 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == state.c9)) {
																				if((0 == state.c1)) {
																					if((0 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == state.c9)) {
																				if((0 == state.c1)) {
																					if((1 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == state.c9)) {
																				if((1 == state.c1)) {
																					if((0 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == state.c9)) {
																				if((1 == state.c1)) {
																					if((1 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == state.c9)) {
																				if((0 == state.c1)) {
																					if((0 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == state.c9)) {
																				if((0 == state.c1)) {
																					if((1 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == state.c9)) {
																				if((1 == state.c1)) {
																					if((0 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == state.c9)) {
																				if((1 == state.c1)) {
																					if((1 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == state.c9)) {
																				if((0 == state.c1)) {
																					if((0 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == state.c9)) {
																				if((0 == state.c1)) {
																					if((1 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == state.c9)) {
																				if((1 == state.c1)) {
																					if((0 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == state.c9)) {
																				if((1 == state.c1)) {
																					if((1 == traceTempVariable$c4$5_1))
																						lengthCV$var601$634_12 = 5;
																				}
																			}
																		}
																	}
																}
																if(((Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_12)) && (0 < lengthCV$var601$634_12)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_12)) && (0 < lengthCV$var601$634_12)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_12)) && (0 < lengthCV$var601$634_12)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_12)) && (0 < lengthCV$var601$634_12)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_12)) && (0 < lengthCV$var601$634_12)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)));
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
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample55) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				state.c4 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
			}
		}
	}

	private final void inferSample57() {
		if(true) {
			state.constrainedFlag$sample57 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var55$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				state.c5 = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$currentValue])) && (state.priors[cv$currentValue] <= 1.0))?Math.log(state.priors[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$c5$1_1 = cv$currentValue;
								{
									{
										boolean cv$sampleConstrained = state.fixedFlag$sample60;
										if(cv$sampleConstrained) {
											state.constrainedFlag$sample57 = true;
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															{
																double[] var56 = state.conditionals[traceTempVariable$c5$1_1];
																int lengthCV$conditionals$58_8 = -1;
																{
																	{
																		if((0 == traceTempVariable$c5$1_1))
																			lengthCV$conditionals$58_8 = 2;
																	}
																}
																{
																	{
																		if((1 == traceTempVariable$c5$1_1))
																			lengthCV$conditionals$58_8 = 2;
																	}
																}
																if(((Math.log(1.0) + ((((((0.0 <= state.c6) && (state.c6 < lengthCV$conditionals$58_8)) && (0 < lengthCV$conditionals$58_8)) && (0.0 <= var56[state.c6])) && (var56[state.c6] <= 1.0))?Math.log(var56[state.c6]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.c6) && (state.c6 < lengthCV$conditionals$58_8)) && (0 < lengthCV$conditionals$58_8)) && (0.0 <= var56[state.c6])) && (var56[state.c6] <= 1.0))?Math.log(var56[state.c6]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.c6) && (state.c6 < lengthCV$conditionals$58_8)) && (0 < lengthCV$conditionals$58_8)) && (0.0 <= var56[state.c6])) && (var56[state.c6] <= 1.0))?Math.log(var56[state.c6]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.c6) && (state.c6 < lengthCV$conditionals$58_8)) && (0 < lengthCV$conditionals$58_8)) && (0.0 <= var56[state.c6])) && (var56[state.c6] <= 1.0))?Math.log(var56[state.c6]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.c6) && (state.c6 < lengthCV$conditionals$58_8)) && (0 < lengthCV$conditionals$58_8)) && (0.0 <= var56[state.c6])) && (var56[state.c6] <= 1.0))?Math.log(var56[state.c6]):Double.NEGATIVE_INFINITY)));
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
					{
						{
							{
								int traceTempVariable$c5$6_1 = cv$currentValue;
								{
									{
										boolean cv$sampleConstrained = state.fixedFlag$sample636;
										if(cv$sampleConstrained) {
											state.constrainedFlag$sample57 = true;
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															{
																double[] var602 = state.a[traceTempVariable$c5$6_1][state.c9][state.c1][state.c4];
																int lengthCV$var601$634_13 = -1;
																{
																	{
																		if((0 == traceTempVariable$c5$6_1)) {
																			if((0 == state.c9)) {
																				if((0 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == traceTempVariable$c5$6_1)) {
																			if((0 == state.c9)) {
																				if((0 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == traceTempVariable$c5$6_1)) {
																			if((0 == state.c9)) {
																				if((1 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == traceTempVariable$c5$6_1)) {
																			if((0 == state.c9)) {
																				if((1 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == traceTempVariable$c5$6_1)) {
																			if((1 == state.c9)) {
																				if((0 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == traceTempVariable$c5$6_1)) {
																			if((1 == state.c9)) {
																				if((0 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == traceTempVariable$c5$6_1)) {
																			if((1 == state.c9)) {
																				if((1 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == traceTempVariable$c5$6_1)) {
																			if((1 == state.c9)) {
																				if((1 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == traceTempVariable$c5$6_1)) {
																			if((0 == state.c9)) {
																				if((0 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == traceTempVariable$c5$6_1)) {
																			if((0 == state.c9)) {
																				if((0 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == traceTempVariable$c5$6_1)) {
																			if((0 == state.c9)) {
																				if((1 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == traceTempVariable$c5$6_1)) {
																			if((0 == state.c9)) {
																				if((1 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == traceTempVariable$c5$6_1)) {
																			if((1 == state.c9)) {
																				if((0 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == traceTempVariable$c5$6_1)) {
																			if((1 == state.c9)) {
																				if((0 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == traceTempVariable$c5$6_1)) {
																			if((1 == state.c9)) {
																				if((1 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == traceTempVariable$c5$6_1)) {
																			if((1 == state.c9)) {
																				if((1 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_13 = 5;
																				}
																			}
																		}
																	}
																}
																if(((Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_13)) && (0 < lengthCV$var601$634_13)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_13)) && (0 < lengthCV$var601$634_13)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_13)) && (0 < lengthCV$var601$634_13)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_13)) && (0 < lengthCV$var601$634_13)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_13)) && (0 < lengthCV$var601$634_13)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)));
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
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample57) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				state.c5 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
			}
		}
	}

	private final void inferSample62() {
		if(true) {
			state.constrainedFlag$sample62 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var60$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				state.c7 = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$currentValue])) && (state.priors[cv$currentValue] <= 1.0))?Math.log(state.priors[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$c7$1_1 = cv$currentValue;
								{
									{
										boolean cv$sampleConstrained = state.fixedFlag$sample65;
										if(cv$sampleConstrained) {
											state.constrainedFlag$sample62 = true;
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															{
																double[] var61 = state.conditionals[traceTempVariable$c7$1_1];
																int lengthCV$conditionals$63_8 = -1;
																{
																	{
																		if((0 == traceTempVariable$c7$1_1))
																			lengthCV$conditionals$63_8 = 2;
																	}
																}
																{
																	{
																		if((1 == traceTempVariable$c7$1_1))
																			lengthCV$conditionals$63_8 = 2;
																	}
																}
																if(((Math.log(1.0) + ((((((0.0 <= state.c8) && (state.c8 < lengthCV$conditionals$63_8)) && (0 < lengthCV$conditionals$63_8)) && (0.0 <= var61[state.c8])) && (var61[state.c8] <= 1.0))?Math.log(var61[state.c8]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.c8) && (state.c8 < lengthCV$conditionals$63_8)) && (0 < lengthCV$conditionals$63_8)) && (0.0 <= var61[state.c8])) && (var61[state.c8] <= 1.0))?Math.log(var61[state.c8]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.c8) && (state.c8 < lengthCV$conditionals$63_8)) && (0 < lengthCV$conditionals$63_8)) && (0.0 <= var61[state.c8])) && (var61[state.c8] <= 1.0))?Math.log(var61[state.c8]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.c8) && (state.c8 < lengthCV$conditionals$63_8)) && (0 < lengthCV$conditionals$63_8)) && (0.0 <= var61[state.c8])) && (var61[state.c8] <= 1.0))?Math.log(var61[state.c8]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.c8) && (state.c8 < lengthCV$conditionals$63_8)) && (0 < lengthCV$conditionals$63_8)) && (0.0 <= var61[state.c8])) && (var61[state.c8] <= 1.0))?Math.log(var61[state.c8]):Double.NEGATIVE_INFINITY)));
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
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample62) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				state.c7 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
			}
		}
	}

	private final void inferSample67() {
		if(true) {
			state.constrainedFlag$sample67 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var65$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				state.c9 = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$currentValue])) && (state.priors[cv$currentValue] <= 1.0))?Math.log(state.priors[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$c9$1_1 = cv$currentValue;
								{
									{
										boolean cv$sampleConstrained = state.fixedFlag$sample70;
										if(cv$sampleConstrained) {
											state.constrainedFlag$sample67 = true;
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															{
																double[] var66 = state.conditionals[traceTempVariable$c9$1_1];
																int lengthCV$conditionals$68_8 = -1;
																{
																	{
																		if((0 == traceTempVariable$c9$1_1))
																			lengthCV$conditionals$68_8 = 2;
																	}
																}
																{
																	{
																		if((1 == traceTempVariable$c9$1_1))
																			lengthCV$conditionals$68_8 = 2;
																	}
																}
																if(((Math.log(1.0) + ((((((0.0 <= state.c10) && (state.c10 < lengthCV$conditionals$68_8)) && (0 < lengthCV$conditionals$68_8)) && (0.0 <= var66[state.c10])) && (var66[state.c10] <= 1.0))?Math.log(var66[state.c10]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.c10) && (state.c10 < lengthCV$conditionals$68_8)) && (0 < lengthCV$conditionals$68_8)) && (0.0 <= var66[state.c10])) && (var66[state.c10] <= 1.0))?Math.log(var66[state.c10]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.c10) && (state.c10 < lengthCV$conditionals$68_8)) && (0 < lengthCV$conditionals$68_8)) && (0.0 <= var66[state.c10])) && (var66[state.c10] <= 1.0))?Math.log(var66[state.c10]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.c10) && (state.c10 < lengthCV$conditionals$68_8)) && (0 < lengthCV$conditionals$68_8)) && (0.0 <= var66[state.c10])) && (var66[state.c10] <= 1.0))?Math.log(var66[state.c10]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.c10) && (state.c10 < lengthCV$conditionals$68_8)) && (0 < lengthCV$conditionals$68_8)) && (0.0 <= var66[state.c10])) && (var66[state.c10] <= 1.0))?Math.log(var66[state.c10]):Double.NEGATIVE_INFINITY)));
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
					{
						{
							{
								int traceTempVariable$c9$6_1 = cv$currentValue;
								{
									{
										boolean cv$sampleConstrained = state.fixedFlag$sample636;
										if(cv$sampleConstrained) {
											state.constrainedFlag$sample67 = true;
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															{
																double[] var602 = state.a[state.c5][traceTempVariable$c9$6_1][state.c1][state.c4];
																int lengthCV$var601$634_14 = -1;
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == traceTempVariable$c9$6_1)) {
																				if((0 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == traceTempVariable$c9$6_1)) {
																				if((0 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == traceTempVariable$c9$6_1)) {
																				if((1 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == state.c5)) {
																			if((0 == traceTempVariable$c9$6_1)) {
																				if((1 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == traceTempVariable$c9$6_1)) {
																				if((0 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == traceTempVariable$c9$6_1)) {
																				if((0 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == traceTempVariable$c9$6_1)) {
																				if((1 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((0 == state.c5)) {
																			if((1 == traceTempVariable$c9$6_1)) {
																				if((1 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == traceTempVariable$c9$6_1)) {
																				if((0 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == traceTempVariable$c9$6_1)) {
																				if((0 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == traceTempVariable$c9$6_1)) {
																				if((1 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((0 == traceTempVariable$c9$6_1)) {
																				if((1 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == traceTempVariable$c9$6_1)) {
																				if((0 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == traceTempVariable$c9$6_1)) {
																				if((0 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == traceTempVariable$c9$6_1)) {
																				if((1 == state.c1)) {
																					if((0 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																{
																	{
																		if((1 == state.c5)) {
																			if((1 == traceTempVariable$c9$6_1)) {
																				if((1 == state.c1)) {
																					if((1 == state.c4))
																						lengthCV$var601$634_14 = 5;
																				}
																			}
																		}
																	}
																}
																if(((Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_14)) && (0 < lengthCV$var601$634_14)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_14)) && (0 < lengthCV$var601$634_14)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_14)) && (0 < lengthCV$var601$634_14)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_14)) && (0 < lengthCV$var601$634_14)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.terminalVariable) && (state.terminalVariable < lengthCV$var601$634_14)) && (0 < lengthCV$var601$634_14)) && (0.0 <= var602[state.terminalVariable])) && (var602[state.terminalVariable] <= 1.0))?Math.log(var602[state.terminalVariable]):Double.NEGATIVE_INFINITY)));
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
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample67) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				state.c9 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
			}
		}
	}

	private final void inferSample72() {
		if(true) {
			state.constrainedFlag$sample72 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var70$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				state.c11 = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$currentValue])) && (state.priors[cv$currentValue] <= 1.0))?Math.log(state.priors[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$c11$1_1 = cv$currentValue;
								{
									{
										boolean cv$sampleConstrained = state.fixedFlag$sample75;
										if(cv$sampleConstrained) {
											state.constrainedFlag$sample72 = true;
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															{
																double[] var71 = state.conditionals[traceTempVariable$c11$1_1];
																int lengthCV$conditionals$73_8 = -1;
																{
																	{
																		if((0 == traceTempVariable$c11$1_1))
																			lengthCV$conditionals$73_8 = 2;
																	}
																}
																{
																	{
																		if((1 == traceTempVariable$c11$1_1))
																			lengthCV$conditionals$73_8 = 2;
																	}
																}
																if(((Math.log(1.0) + ((((((0.0 <= state.c12) && (state.c12 < lengthCV$conditionals$73_8)) && (0 < lengthCV$conditionals$73_8)) && (0.0 <= var71[state.c12])) && (var71[state.c12] <= 1.0))?Math.log(var71[state.c12]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.c12) && (state.c12 < lengthCV$conditionals$73_8)) && (0 < lengthCV$conditionals$73_8)) && (0.0 <= var71[state.c12])) && (var71[state.c12] <= 1.0))?Math.log(var71[state.c12]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.c12) && (state.c12 < lengthCV$conditionals$73_8)) && (0 < lengthCV$conditionals$73_8)) && (0.0 <= var71[state.c12])) && (var71[state.c12] <= 1.0))?Math.log(var71[state.c12]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.c12) && (state.c12 < lengthCV$conditionals$73_8)) && (0 < lengthCV$conditionals$73_8)) && (0.0 <= var71[state.c12])) && (var71[state.c12] <= 1.0))?Math.log(var71[state.c12]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.c12) && (state.c12 < lengthCV$conditionals$73_8)) && (0 < lengthCV$conditionals$73_8)) && (0.0 <= var71[state.c12])) && (var71[state.c12] <= 1.0))?Math.log(var71[state.c12]):Double.NEGATIVE_INFINITY)));
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
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample72) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				state.c11 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
			}
		}
	}

	private final void logProbabilityValue$sample47() {
		if(!state.fixedProbFlag$sample47) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.c1;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$sampleValue])) && (state.priors[cv$sampleValue] <= 1.0))?Math.log(state.priors[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$c1 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample47)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample47 = state.fixedFlag$sample47;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c1;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample47)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample50() {
		if(!state.fixedProbFlag$sample50) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.c2;
					{
						{
							double[] var46 = state.conditionals[state.c1];
							int lengthCV$conditionals$48_5 = -1;
							{
								{
									if((0 == state.c1))
										lengthCV$conditionals$48_5 = 2;
								}
							}
							{
								{
									if((1 == state.c1))
										lengthCV$conditionals$48_5 = 2;
								}
							}
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$conditionals$48_5)) && (0 < lengthCV$conditionals$48_5)) && (0.0 <= var46[cv$sampleValue])) && (var46[cv$sampleValue] <= 1.0))?Math.log(var46[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$c2 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample50 = state.fixedFlag$sample47;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c2;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!state.fixedProbFlag$sample52) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.c3;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$sampleValue])) && (state.priors[cv$sampleValue] <= 1.0))?Math.log(state.priors[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$c3 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample52)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample52 = state.fixedFlag$sample52;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c3;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample52)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample55() {
		if(!state.fixedProbFlag$sample55) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.c4;
					{
						{
							double[] var51 = state.conditionals[state.c3];
							int lengthCV$conditionals$53_14 = -1;
							{
								{
									if((0 == state.c3))
										lengthCV$conditionals$53_14 = 2;
								}
							}
							{
								{
									if((1 == state.c3))
										lengthCV$conditionals$53_14 = 2;
								}
							}
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$conditionals$53_14)) && (0 < lengthCV$conditionals$53_14)) && (0.0 <= var51[cv$sampleValue])) && (var51[cv$sampleValue] <= 1.0))?Math.log(var51[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$c4 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample55)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample55 = (state.fixedFlag$sample55 && state.fixedFlag$sample52);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c4;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample55)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample57() {
		if(!state.fixedProbFlag$sample57) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.c5;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$sampleValue])) && (state.priors[cv$sampleValue] <= 1.0))?Math.log(state.priors[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$c5 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample57 = state.fixedFlag$sample57;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c5;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample60() {
		if(!state.fixedProbFlag$sample60) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.c6;
					{
						{
							double[] var56 = state.conditionals[state.c5];
							int lengthCV$conditionals$58_10 = -1;
							{
								{
									if((0 == state.c5))
										lengthCV$conditionals$58_10 = 2;
								}
							}
							{
								{
									if((1 == state.c5))
										lengthCV$conditionals$58_10 = 2;
								}
							}
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$conditionals$58_10)) && (0 < lengthCV$conditionals$58_10)) && (0.0 <= var56[cv$sampleValue])) && (var56[cv$sampleValue] <= 1.0))?Math.log(var56[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$c6 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample60)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample60 = (state.fixedFlag$sample60 && state.fixedFlag$sample57);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c6;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample60)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample62() {
		if(!state.fixedProbFlag$sample62) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.c7;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$sampleValue])) && (state.priors[cv$sampleValue] <= 1.0))?Math.log(state.priors[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$c7 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample62)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample62 = state.fixedFlag$sample62;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c7;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample62)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample636() {
		if(!state.fixedProbFlag$sample636) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.terminalVariable;
					{
						{
							double[] var602 = state.a[state.c5][state.c9][state.c1][state.c4];
							int lengthCV$var601$634_16 = -1;
							{
								{
									if((0 == state.c5)) {
										if((0 == state.c9)) {
											if((0 == state.c1)) {
												if((0 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							{
								{
									if((0 == state.c5)) {
										if((0 == state.c9)) {
											if((0 == state.c1)) {
												if((1 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							{
								{
									if((0 == state.c5)) {
										if((0 == state.c9)) {
											if((1 == state.c1)) {
												if((0 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							{
								{
									if((0 == state.c5)) {
										if((0 == state.c9)) {
											if((1 == state.c1)) {
												if((1 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							{
								{
									if((0 == state.c5)) {
										if((1 == state.c9)) {
											if((0 == state.c1)) {
												if((0 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							{
								{
									if((0 == state.c5)) {
										if((1 == state.c9)) {
											if((0 == state.c1)) {
												if((1 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							{
								{
									if((0 == state.c5)) {
										if((1 == state.c9)) {
											if((1 == state.c1)) {
												if((0 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							{
								{
									if((0 == state.c5)) {
										if((1 == state.c9)) {
											if((1 == state.c1)) {
												if((1 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							{
								{
									if((1 == state.c5)) {
										if((0 == state.c9)) {
											if((0 == state.c1)) {
												if((0 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							{
								{
									if((1 == state.c5)) {
										if((0 == state.c9)) {
											if((0 == state.c1)) {
												if((1 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							{
								{
									if((1 == state.c5)) {
										if((0 == state.c9)) {
											if((1 == state.c1)) {
												if((0 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							{
								{
									if((1 == state.c5)) {
										if((0 == state.c9)) {
											if((1 == state.c1)) {
												if((1 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							{
								{
									if((1 == state.c5)) {
										if((1 == state.c9)) {
											if((0 == state.c1)) {
												if((0 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							{
								{
									if((1 == state.c5)) {
										if((1 == state.c9)) {
											if((0 == state.c1)) {
												if((1 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							{
								{
									if((1 == state.c5)) {
										if((1 == state.c9)) {
											if((1 == state.c1)) {
												if((0 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							{
								{
									if((1 == state.c5)) {
										if((1 == state.c9)) {
											if((1 == state.c1)) {
												if((1 == state.c4))
													lengthCV$var601$634_16 = 5;
											}
										}
									}
								}
							}
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$var601$634_16)) && (0 < lengthCV$var601$634_16)) && (0.0 <= var602[cv$sampleValue])) && (var602[cv$sampleValue] <= 1.0))?Math.log(var602[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$terminalVariable = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample636)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample636 = ((((state.fixedFlag$sample636 && state.fixedFlag$sample47) && state.fixedFlag$sample55) && state.fixedFlag$sample57) && state.fixedFlag$sample67);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$terminalVariable;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample636)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample65() {
		if(!state.fixedProbFlag$sample65) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.c8;
					{
						{
							double[] var61 = state.conditionals[state.c7];
							int lengthCV$conditionals$63_10 = -1;
							{
								{
									if((0 == state.c7))
										lengthCV$conditionals$63_10 = 2;
								}
							}
							{
								{
									if((1 == state.c7))
										lengthCV$conditionals$63_10 = 2;
								}
							}
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$conditionals$63_10)) && (0 < lengthCV$conditionals$63_10)) && (0.0 <= var61[cv$sampleValue])) && (var61[cv$sampleValue] <= 1.0))?Math.log(var61[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$c8 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample65)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample65 = (state.fixedFlag$sample65 && state.fixedFlag$sample62);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c8;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample65)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample67() {
		if(!state.fixedProbFlag$sample67) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.c9;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$sampleValue])) && (state.priors[cv$sampleValue] <= 1.0))?Math.log(state.priors[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$c9 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample67)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample67 = state.fixedFlag$sample67;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c9;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample67)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample70() {
		if(!state.fixedProbFlag$sample70) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.c10;
					{
						{
							double[] var66 = state.conditionals[state.c9];
							int lengthCV$conditionals$68_10 = -1;
							{
								{
									if((0 == state.c9))
										lengthCV$conditionals$68_10 = 2;
								}
							}
							{
								{
									if((1 == state.c9))
										lengthCV$conditionals$68_10 = 2;
								}
							}
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$conditionals$68_10)) && (0 < lengthCV$conditionals$68_10)) && (0.0 <= var66[cv$sampleValue])) && (var66[cv$sampleValue] <= 1.0))?Math.log(var66[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$c10 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample70)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample70 = (state.fixedFlag$sample70 && state.fixedFlag$sample67);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c10;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample70)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample72() {
		if(!state.fixedProbFlag$sample72) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.c11;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0 < 2)) && (0.0 <= state.priors[cv$sampleValue])) && (state.priors[cv$sampleValue] <= 1.0))?Math.log(state.priors[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$c11 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample72)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample72 = state.fixedFlag$sample72;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c11;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample72)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample75() {
		if(!state.fixedProbFlag$sample75) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.c12;
					{
						{
							double[] var71 = state.conditionals[state.c11];
							int lengthCV$conditionals$73_10 = -1;
							{
								{
									if((0 == state.c11))
										lengthCV$conditionals$73_10 = 2;
								}
							}
							{
								{
									if((1 == state.c11))
										lengthCV$conditionals$73_10 = 2;
								}
							}
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$conditionals$73_10)) && (0 < lengthCV$conditionals$73_10)) && (0.0 <= var71[cv$sampleValue])) && (var71[cv$sampleValue] <= 1.0))?Math.log(var71[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$c12 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample75)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample75 = (state.fixedFlag$sample75 && state.fixedFlag$sample72);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$c12;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample75)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample47)
			state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$48_6 = -1;
		{
			{
				if((0 == state.c1))
					lengthCV$conditionals$48_6 = 2;
			}
		}
		{
			{
				if((1 == state.c1))
					lengthCV$conditionals$48_6 = 2;
			}
		}
		state.c2 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c1], lengthCV$conditionals$48_6);
		if(!state.fixedFlag$sample52)
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$53_15 = -1;
		{
			{
				if((0 == state.c3)) {
					if(!state.fixedFlag$sample55)
						lengthCV$conditionals$53_15 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c3)) {
					if(!state.fixedFlag$sample55)
						lengthCV$conditionals$53_15 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample55)
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_15);
		if(!state.fixedFlag$sample57)
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$58_11 = -1;
		{
			{
				if((0 == state.c5)) {
					if(!state.fixedFlag$sample60)
						lengthCV$conditionals$58_11 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if(!state.fixedFlag$sample60)
						lengthCV$conditionals$58_11 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample60)
			state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_11);
		if(!state.fixedFlag$sample62)
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$63_11 = -1;
		{
			{
				if((0 == state.c7)) {
					if(!state.fixedFlag$sample65)
						lengthCV$conditionals$63_11 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c7)) {
					if(!state.fixedFlag$sample65)
						lengthCV$conditionals$63_11 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample65)
			state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_11);
		if(!state.fixedFlag$sample67)
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$68_11 = -1;
		{
			{
				if((0 == state.c9)) {
					if(!state.fixedFlag$sample70)
						lengthCV$conditionals$68_11 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c9)) {
					if(!state.fixedFlag$sample70)
						lengthCV$conditionals$68_11 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample70)
			state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_11);
		if(!state.fixedFlag$sample72)
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$73_11 = -1;
		{
			{
				if((0 == state.c11)) {
					if(!state.fixedFlag$sample75)
						lengthCV$conditionals$73_11 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c11)) {
					if(!state.fixedFlag$sample75)
						lengthCV$conditionals$73_11 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample75)
			state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_11);
		int lengthCV$var601$634_17 = -1;
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_17 = 5;
							}
						}
					}
				}
			}
		}
		if(!state.fixedFlag$sample636)
			state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_17);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample47)
			state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample52)
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$53_19 = -1;
		{
			{
				if((0 == state.c3)) {
					if(!state.fixedFlag$sample55)
						lengthCV$conditionals$53_19 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c3)) {
					if(!state.fixedFlag$sample55)
						lengthCV$conditionals$53_19 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample55)
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_19);
		if(!state.fixedFlag$sample57)
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$58_15 = -1;
		{
			{
				if((0 == state.c5)) {
					if(!state.fixedFlag$sample60)
						lengthCV$conditionals$58_15 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if(!state.fixedFlag$sample60)
						lengthCV$conditionals$58_15 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample60)
			state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_15);
		if(!state.fixedFlag$sample62)
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$63_15 = -1;
		{
			{
				if((0 == state.c7)) {
					if(!state.fixedFlag$sample65)
						lengthCV$conditionals$63_15 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c7)) {
					if(!state.fixedFlag$sample65)
						lengthCV$conditionals$63_15 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample65)
			state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_15);
		if(!state.fixedFlag$sample67)
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$68_15 = -1;
		{
			{
				if((0 == state.c9)) {
					if(!state.fixedFlag$sample70)
						lengthCV$conditionals$68_15 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c9)) {
					if(!state.fixedFlag$sample70)
						lengthCV$conditionals$68_15 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample70)
			state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_15);
		if(!state.fixedFlag$sample72)
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$73_15 = -1;
		{
			{
				if((0 == state.c11)) {
					if(!state.fixedFlag$sample75)
						lengthCV$conditionals$73_15 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c11)) {
					if(!state.fixedFlag$sample75)
						lengthCV$conditionals$73_15 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample75)
			state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_15);
		int lengthCV$var601$634_21 = -1;
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_21 = 5;
							}
						}
					}
				}
			}
		}
		if(!state.fixedFlag$sample636)
			state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_21);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample47)
			state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$48_7 = -1;
		{
			{
				if((0 == state.c1))
					lengthCV$conditionals$48_7 = 2;
			}
		}
		{
			{
				if((1 == state.c1))
					lengthCV$conditionals$48_7 = 2;
			}
		}
		state.c2 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c1], lengthCV$conditionals$48_7);
		if(!state.fixedFlag$sample52)
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$53_16 = -1;
		{
			{
				if((0 == state.c3)) {
					if(!state.fixedFlag$sample55)
						lengthCV$conditionals$53_16 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c3)) {
					if(!state.fixedFlag$sample55)
						lengthCV$conditionals$53_16 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample55)
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_16);
		if(!state.fixedFlag$sample57)
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$58_12 = -1;
		{
			{
				if((0 == state.c5)) {
					if(!state.fixedFlag$sample60)
						lengthCV$conditionals$58_12 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if(!state.fixedFlag$sample60)
						lengthCV$conditionals$58_12 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample60)
			state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_12);
		if(!state.fixedFlag$sample62)
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$63_12 = -1;
		{
			{
				if((0 == state.c7)) {
					if(!state.fixedFlag$sample65)
						lengthCV$conditionals$63_12 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c7)) {
					if(!state.fixedFlag$sample65)
						lengthCV$conditionals$63_12 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample65)
			state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_12);
		if(!state.fixedFlag$sample67)
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$68_12 = -1;
		{
			{
				if((0 == state.c9)) {
					if(!state.fixedFlag$sample70)
						lengthCV$conditionals$68_12 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c9)) {
					if(!state.fixedFlag$sample70)
						lengthCV$conditionals$68_12 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample70)
			state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_12);
		if(!state.fixedFlag$sample72)
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$73_12 = -1;
		{
			{
				if((0 == state.c11)) {
					if(!state.fixedFlag$sample75)
						lengthCV$conditionals$73_12 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c11)) {
					if(!state.fixedFlag$sample75)
						lengthCV$conditionals$73_12 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample75)
			state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_12);
		int lengthCV$var601$634_18 = -1;
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_18 = 5;
							}
						}
					}
				}
			}
		}
		if(!state.fixedFlag$sample636)
			state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_18);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample47)
			state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample52)
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$53_17 = -1;
		{
			{
				if((0 == state.c3)) {
					if(!state.fixedFlag$sample55)
						lengthCV$conditionals$53_17 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c3)) {
					if(!state.fixedFlag$sample55)
						lengthCV$conditionals$53_17 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample55)
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_17);
		if(!state.fixedFlag$sample57)
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$58_13 = -1;
		{
			{
				if((0 == state.c5)) {
					if(!state.fixedFlag$sample60)
						lengthCV$conditionals$58_13 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if(!state.fixedFlag$sample60)
						lengthCV$conditionals$58_13 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample60)
			state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_13);
		if(!state.fixedFlag$sample62)
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$63_13 = -1;
		{
			{
				if((0 == state.c7)) {
					if(!state.fixedFlag$sample65)
						lengthCV$conditionals$63_13 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c7)) {
					if(!state.fixedFlag$sample65)
						lengthCV$conditionals$63_13 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample65)
			state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_13);
		if(!state.fixedFlag$sample67)
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$68_13 = -1;
		{
			{
				if((0 == state.c9)) {
					if(!state.fixedFlag$sample70)
						lengthCV$conditionals$68_13 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c9)) {
					if(!state.fixedFlag$sample70)
						lengthCV$conditionals$68_13 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample70)
			state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_13);
		if(!state.fixedFlag$sample72)
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$73_13 = -1;
		{
			{
				if((0 == state.c11)) {
					if(!state.fixedFlag$sample75)
						lengthCV$conditionals$73_13 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c11)) {
					if(!state.fixedFlag$sample75)
						lengthCV$conditionals$73_13 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample75)
			state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_13);
		int lengthCV$var601$634_19 = -1;
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_19 = 5;
							}
						}
					}
				}
			}
		}
		if(!state.fixedFlag$sample636)
			state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_19);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample47)
			state.c1 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		if(!state.fixedFlag$sample52)
			state.c3 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$53_18 = -1;
		{
			{
				if((0 == state.c3)) {
					if(!state.fixedFlag$sample55)
						lengthCV$conditionals$53_18 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c3)) {
					if(!state.fixedFlag$sample55)
						lengthCV$conditionals$53_18 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample55)
			state.c4 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c3], lengthCV$conditionals$53_18);
		if(!state.fixedFlag$sample57)
			state.c5 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$58_14 = -1;
		{
			{
				if((0 == state.c5)) {
					if(!state.fixedFlag$sample60)
						lengthCV$conditionals$58_14 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if(!state.fixedFlag$sample60)
						lengthCV$conditionals$58_14 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample60)
			state.c6 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c5], lengthCV$conditionals$58_14);
		if(!state.fixedFlag$sample62)
			state.c7 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$63_14 = -1;
		{
			{
				if((0 == state.c7)) {
					if(!state.fixedFlag$sample65)
						lengthCV$conditionals$63_14 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c7)) {
					if(!state.fixedFlag$sample65)
						lengthCV$conditionals$63_14 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample65)
			state.c8 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c7], lengthCV$conditionals$63_14);
		if(!state.fixedFlag$sample67)
			state.c9 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$68_14 = -1;
		{
			{
				if((0 == state.c9)) {
					if(!state.fixedFlag$sample70)
						lengthCV$conditionals$68_14 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c9)) {
					if(!state.fixedFlag$sample70)
						lengthCV$conditionals$68_14 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample70)
			state.c10 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c9], lengthCV$conditionals$68_14);
		if(!state.fixedFlag$sample72)
			state.c11 = DistributionSampling.sampleCategorical(state.RNG$, state.priors, 2);
		int lengthCV$conditionals$73_14 = -1;
		{
			{
				if((0 == state.c11)) {
					if(!state.fixedFlag$sample75)
						lengthCV$conditionals$73_14 = 2;
				}
			}
		}
		{
			{
				if((1 == state.c11)) {
					if(!state.fixedFlag$sample75)
						lengthCV$conditionals$73_14 = 2;
				}
			}
		}
		if(!state.fixedFlag$sample75)
			state.c12 = DistributionSampling.sampleCategorical(state.RNG$, state.conditionals[state.c11], lengthCV$conditionals$73_14);
		int lengthCV$var601$634_20 = -1;
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((0 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((0 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((0 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((0 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		{
			{
				if((1 == state.c5)) {
					if((1 == state.c9)) {
						if((1 == state.c1)) {
							if((1 == state.c4)) {
								if(!state.fixedFlag$sample636)
									lengthCV$var601$634_20 = 5;
							}
						}
					}
				}
			}
		}
		if(!state.fixedFlag$sample636)
			state.terminalVariable = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.c5][state.c9][state.c1][state.c4], lengthCV$var601$634_20);
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample47)
				inferSample47();
			if(!state.fixedFlag$sample52)
				inferSample52();
			if(!state.fixedFlag$sample55)
				inferSample55();
			if(!state.fixedFlag$sample57)
				inferSample57();
			if(!state.fixedFlag$sample62)
				inferSample62();
			if(!state.fixedFlag$sample67)
				inferSample67();
			if(!state.fixedFlag$sample72)
				inferSample72();
		} else {
			if(!state.fixedFlag$sample72)
				inferSample72();
			if(!state.fixedFlag$sample67)
				inferSample67();
			if(!state.fixedFlag$sample62)
				inferSample62();
			if(!state.fixedFlag$sample57)
				inferSample57();
			if(!state.fixedFlag$sample55)
				inferSample55();
			if(!state.fixedFlag$sample52)
				inferSample52();
			if(!state.fixedFlag$sample47)
				inferSample47();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample47)
			drawValueSample47();
		if(!state.constrainedFlag$sample52)
			drawValueSample52();
		if(!state.constrainedFlag$sample55)
			drawValueSample55();
		if(!state.constrainedFlag$sample57)
			drawValueSample57();
		if(!state.fixedFlag$sample60)
			drawValueSample60();
		if(!state.constrainedFlag$sample62)
			drawValueSample62();
		if(!state.fixedFlag$sample65)
			drawValueSample65();
		if(!state.constrainedFlag$sample67)
			drawValueSample67();
		if(!state.fixedFlag$sample70)
			drawValueSample70();
		if(!state.constrainedFlag$sample72)
			drawValueSample72();
		if(!state.fixedFlag$sample75)
			drawValueSample75();
		if(!state.fixedFlag$sample636)
			drawValueSample636();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample47)
			state.logProbability$c1 = Double.NaN;
		if(!state.fixedProbFlag$sample50)
			state.logProbability$c2 = Double.NaN;
		if(!state.fixedProbFlag$sample52)
			state.logProbability$c3 = Double.NaN;
		if(!state.fixedProbFlag$sample55)
			state.logProbability$c4 = Double.NaN;
		if(!state.fixedProbFlag$sample57)
			state.logProbability$c5 = Double.NaN;
		if(!state.fixedProbFlag$sample60)
			state.logProbability$c6 = Double.NaN;
		if(!state.fixedProbFlag$sample62)
			state.logProbability$c7 = Double.NaN;
		if(!state.fixedProbFlag$sample65)
			state.logProbability$c8 = Double.NaN;
		if(!state.fixedProbFlag$sample67)
			state.logProbability$c9 = Double.NaN;
		if(!state.fixedProbFlag$sample70)
			state.logProbability$c10 = Double.NaN;
		if(!state.fixedProbFlag$sample72)
			state.logProbability$c11 = Double.NaN;
		if(!state.fixedProbFlag$sample75)
			state.logProbability$c12 = Double.NaN;
		if(!state.fixedProbFlag$sample636)
			state.logProbability$terminalVariable = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.priors[0] = 0.01;
		state.priors[1] = 0.99;
		double[] var15 = state.conditionals[0];
		var15[0] = 1.0;
		var15[1] = 0.0;
		double[] var30 = state.conditionals[1];
		var30[0] = 0.0;
		var30[1] = 1.0;
		double[][][][] var77 = state.a[0];
		double[][][] var79 = var77[0];
		double[][] var81 = var79[0];
		double[] var83 = var81[0];
		var83[0] = 0.3333333333333334;
		var83[1] = 0.3333333333333334;
		var83[2] = 0.0;
		var83[3] = 0.3333333333333334;
		var83[4] = 0.0;
		double[] var110 = var81[1];
		var110[0] = 0.3333333333333334;
		var110[1] = 0.3333333333333334;
		var110[2] = 0.0;
		var110[3] = 0.3333333333333334;
		var110[4] = 0.0;
		double[][] var140 = var79[1];
		double[] var142 = var140[0];
		var142[0] = 0.3333333333333334;
		var142[1] = 0.3333333333333334;
		var142[2] = 0.0;
		var142[3] = 0.3333333333333334;
		var142[4] = 0.0;
		double[] var169 = var140[1];
		var169[0] = 0.5;
		var169[1] = 0.5;
		var169[2] = 0.0;
		var169[3] = 0.0;
		var169[4] = 0.0;
		double[][][] var203 = var77[1];
		double[][] var205 = var203[0];
		double[] var207 = var205[0];
		var207[0] = 0.0;
		var207[1] = 0.5;
		var207[2] = 0.0;
		var207[3] = 0.5;
		var207[4] = 0.0;
		double[] var235 = var205[1];
		var235[0] = 0.0;
		var235[1] = 0.5;
		var235[2] = 0.0;
		var235[3] = 0.5;
		var235[4] = 0.0;
		double[][] var266 = var203[1];
		double[] var268 = var266[0];
		var268[0] = 0.0;
		var268[1] = 0.5;
		var268[2] = 0.0;
		var268[3] = 0.5;
		var268[4] = 0.0;
		double[] var296 = var266[1];
		var296[0] = 0.0;
		var296[1] = 1.0;
		var296[2] = 0.0;
		var296[3] = 0.0;
		var296[4] = 0.0;
		double[][][][] var335 = state.a[1];
		double[][][] var337 = var335[0];
		double[][] var339 = var337[0];
		double[] var341 = var339[0];
		var341[0] = 0.3333333333333334;
		var341[1] = 0.3333333333333334;
		var341[2] = 0.0;
		var341[3] = 0.3333333333333334;
		var341[4] = 0.0;
		double[] var368 = var339[1];
		var368[0] = 0.5;
		var368[1] = 0.5;
		var368[2] = 0.0;
		var368[3] = 0.0;
		var368[4] = 0.0;
		double[][] var399 = var337[1];
		double[] var401 = var399[0];
		var401[0] = 0.3333333333333334;
		var401[1] = 0.3333333333333334;
		var401[2] = 0.0;
		var401[3] = 0.3333333333333334;
		var401[4] = 0.0;
		double[] var428 = var399[1];
		var428[0] = 0.5;
		var428[1] = 0.5;
		var428[2] = 0.0;
		var428[3] = 0.0;
		var428[4] = 0.0;
		double[][][] var462 = var335[1];
		double[][] var464 = var462[0];
		double[] var466 = var464[0];
		var466[0] = 0.0;
		var466[1] = 0.0;
		var466[2] = 0.0;
		var466[3] = 1.0;
		var466[4] = 0.0;
		double[] var496 = var464[1];
		var496[0] = 0.0;
		var496[1] = 0.0;
		var496[2] = 0.0;
		var496[3] = 1.0;
		var496[4] = 0.0;
		double[][] var529 = var462[1];
		double[] var531 = var529[0];
		var531[0] = 0.0;
		var531[1] = 0.0;
		var531[2] = 0.0;
		var531[3] = 1.0;
		var531[4] = 0.0;
		double[] var561 = var529[1];
		var561[0] = 0.0;
		var561[1] = 0.0;
		var561[2] = 0.0;
		var561[3] = 0.0;
		var561[4] = 1.0;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample47)
			logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		if(state.fixedFlag$sample52)
			logProbabilityValue$sample52();
		if(state.fixedFlag$sample55)
			logProbabilityValue$sample55();
		if(state.fixedFlag$sample57)
			logProbabilityValue$sample57();
		if(state.fixedFlag$sample60)
			logProbabilityValue$sample60();
		if(state.fixedFlag$sample62)
			logProbabilityValue$sample62();
		if(state.fixedFlag$sample65)
			logProbabilityValue$sample65();
		if(state.fixedFlag$sample67)
			logProbabilityValue$sample67();
		if(state.fixedFlag$sample70)
			logProbabilityValue$sample70();
		if(state.fixedFlag$sample72)
			logProbabilityValue$sample72();
		if(state.fixedFlag$sample75)
			logProbabilityValue$sample75();
		if(state.fixedFlag$sample636)
			logProbabilityValue$sample636();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		logProbabilityValue$sample52();
		logProbabilityValue$sample55();
		logProbabilityValue$sample57();
		logProbabilityValue$sample60();
		logProbabilityValue$sample62();
		logProbabilityValue$sample65();
		logProbabilityValue$sample67();
		logProbabilityValue$sample70();
		logProbabilityValue$sample72();
		logProbabilityValue$sample75();
		logProbabilityValue$sample636();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		logProbabilityValue$sample52();
		logProbabilityValue$sample55();
		logProbabilityValue$sample57();
		logProbabilityValue$sample60();
		logProbabilityValue$sample62();
		logProbabilityValue$sample65();
		logProbabilityValue$sample67();
		logProbabilityValue$sample70();
		logProbabilityValue$sample72();
		logProbabilityValue$sample75();
		logProbabilityValue$sample636();
	}

	@Override
	public final void propagateObservedValues() {
		state.c2 = state.evidence;
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model TerminalVariables(int evidence) {\n"
		     + "    double[] priors = {.01, 0.99};\n"
		     + "    double[][] conditionals = {{1, 0}, {0, 1}};\n"
		     + "\n"
		     + "    int c1 = categorical(priors).sample();\n"
		     + "    int c2 = categorical(conditionals[c1]).sample();\n"
		     + "    \n"
		     + "    int c3 = categorical(priors).sample();\n"
		     + "    int c4 = categorical(conditionals[c3]).sample();\n"
		     + "    \n"
		     + "    int c5 = categorical(priors).sample();\n"
		     + "    int c6 = categorical(conditionals[c5]).sample();\n"
		     + "\n"
		     + "    int c7 = categorical(priors).sample();\n"
		     + "    int c8 = categorical(conditionals[c7]).sample();\n"
		     + "\n"
		     + "    int c9 = categorical(priors).sample();\n"
		     + "    int c10 = categorical(conditionals[c9]).sample();\n"
		     + "    \n"
		     + "    int c11 = categorical(priors).sample();\n"
		     + "    int c12 = categorical(conditionals[c11]).sample();\n"
		     + "\n"
		     + "    double[][][][][] a = {\n"
		     + "       {\n"
		     + "        {{{0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}, {0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}},\n"
		     + "         {{0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}, {0.5, 0.5, 0, 0, 0}}},\n"
		     + "        {{{0, 0.5, 0, 0.5, 0}, {0, 0.5, 0, 0.5, 0}},\n"
		     + "         {{0, 0.5, 0, 0.5, 0}, {0, 1, 0, 0, 0}}}\n"
		     + "       },\n"
		     + "       {\n"
		     + "         {{{0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}, {0.5, 0.5, 0, 0, 0}},\n"
		     + "          {{0.3333333333333334, 0.3333333333333334, 0, 0.3333333333333334, 0}, {0.5, 0.5, 0, 0, 0}}},\n"
		     + "         {{{0, 0, 0, 1, 0}, {0, 0, 0, 1, 0}},\n"
		     + "          {{0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}}}\n"
		     + "        }\n"
		     + "    };\n"
		     + "    int terminalVariable = categorical(a[c5][c9][c1][c4]).sample();\n"
		     + "    \n"
		     + "    // assert\n"
		     + "    c2.observe(evidence);\n"
		     + "}";
	}
}