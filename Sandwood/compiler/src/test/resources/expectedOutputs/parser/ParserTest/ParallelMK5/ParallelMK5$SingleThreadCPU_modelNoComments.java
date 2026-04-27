package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.ParallelMK5$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.ParallelMK5.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class ParallelMK5$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public ParallelMK5$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample61(int i, int j) {
		double[] var55 = state.indirection1[i];
		var55[j] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
		{
			{
				for(int l = 0; l < 10; l += 1) {
					if((i == l)) {
						for(int k = 0; k < state.length$observed; k += 1) {
							if((j == k)) {
								{
									double[] var83 = state.indirection2[k];
									var83[l] = state.indirection1[l][k];
								}
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample61(int i, int j) {
		if(true) {
			state.constrainedFlag$sample61[((i - 0) / 1)][((j - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = state.indirection1[i][j];
			double cv$originalProbability = 0.0;
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			if((cv$var < 0.01))
				cv$var = 0.01;
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample61[((i - 0) / 1)][((j - 0) / 1)] || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var59 = cv$proposedValue;
						{
							{
								{
									double[] var55 = state.indirection1[i];
									var55[j] = cv$currentValue;
								}
							}
						}
						{
							{
								for(int l = 0; l < 10; l += 1) {
									if((i == l)) {
										for(int k = 0; k < state.length$observed; k += 1) {
											if((j == k)) {
												{
													double[] var83 = state.indirection2[k];
													var83[l] = state.indirection1[l][k];
												}
											}
										}
									}
								}
							}
						}
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < 1.0))?(-Math.log((1.0 - 0.0))):Double.NEGATIVE_INFINITY));
						{
							{
								{
									double traceTempVariable$var85$3_1 = cv$currentValue;
									for(int l = 0; l < 10; l += 1) {
										if((i == l)) {
											for(int k = 0; k < state.length$observed; k += 1) {
												if((j == k)) {
													for(int m = 0; m < state.length$observed; m += 1) {
														if((k == m)) {
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample61[((i - 0) / 1)][((j - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							double[] var99 = state.indirection2[m];
																							if(((Math.log(1.0) + ((((((0.0 <= state.generated[m]) && (state.generated[m] < 10)) && (0 < 10)) && (0.0 <= var99[state.generated[m]])) && (var99[state.generated[m]] <= 1.0))?Math.log(var99[state.generated[m]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.generated[m]) && (state.generated[m] < 10)) && (0 < 10)) && (0.0 <= var99[state.generated[m]])) && (var99[state.generated[m]] <= 1.0))?Math.log(var99[state.generated[m]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.generated[m]) && (state.generated[m] < 10)) && (0 < 10)) && (0.0 <= var99[state.generated[m]])) && (var99[state.generated[m]] <= 1.0))?Math.log(var99[state.generated[m]]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.generated[m]) && (state.generated[m] < 10)) && (0 < 10)) && (0.0 <= var99[state.generated[m]])) && (var99[state.generated[m]] <= 1.0))?Math.log(var99[state.generated[m]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.generated[m]) && (state.generated[m] < 10)) && (0 < 10)) && (0.0 <= var99[state.generated[m]])) && (var99[state.generated[m]] <= 1.0))?Math.log(var99[state.generated[m]]):Double.NEGATIVE_INFINITY)));
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
							double var59 = cv$originalValue;
							{
								{
									{
										double[] var55 = state.indirection1[i];
										var55[j] = var59;
									}
								}
							}
							{
								{
									for(int l = 0; l < 10; l += 1) {
										if((i == l)) {
											for(int k = 0; k < state.length$observed; k += 1) {
												if((j == k)) {
													{
														double[] var83 = state.indirection2[k];
														var83[l] = state.indirection1[l][k];
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

	private final void logProbabilityValue$sample103() {
		if(!state.fixedProbFlag$sample103) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int m = 0; m < state.length$observed; m += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = state.generated[m];
						{
							{
								double[] var99 = state.indirection2[m];
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < 10)) && (0 < 10)) && (0.0 <= var99[cv$sampleValue])) && (var99[cv$sampleValue] <= 1.0))?Math.log(var99[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample103[((m - 0) / 1)] = cv$sampleProbability;
			}
			state.logProbability$generated = (state.logProbability$generated + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample103 = state.fixedFlag$sample61;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int m = 0; m < state.length$observed; m += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample103[((m - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$generated = (state.logProbability$generated + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample61() {
		if(!state.fixedProbFlag$sample61) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < 10; i += 1) {
				for(int j = 0; j < state.length$observed; j += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							double cv$sampleValue = state.indirection1[i][j];
							{
								{
									double var56 = 0.0;
									double var57 = 1.0;
									double cv$weightedProbability = (Math.log(1.0) + (((var56 <= cv$sampleValue) && (cv$sampleValue < var57))?(-Math.log((var57 - var56))):Double.NEGATIVE_INFINITY));
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
					state.logProbability$sample61[((i - 0) / 1)][((j - 0) / 1)] = cv$sampleProbability;
					boolean cv$guard$indirection2 = false;
					{
						{
							for(int l = 0; l < 10; l += 1) {
								if((i == l)) {
									for(int k = 0; k < state.length$observed; k += 1) {
										if((j == k)) {
											if(!cv$guard$indirection2) {
												cv$guard$indirection2 = true;
												state.logProbability$indirection2 = (state.logProbability$indirection2 + cv$sampleProbability);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			state.logProbability$indirection1 = (state.logProbability$indirection1 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample61)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample61 = state.fixedFlag$sample61;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < 10; i += 1) {
				for(int j = 0; j < state.length$observed; j += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample61[((i - 0) / 1)][((j - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					boolean cv$guard$indirection2 = false;
					{
						{
							for(int l = 0; l < 10; l += 1) {
								if((i == l)) {
									for(int k = 0; k < state.length$observed; k += 1) {
										if((j == k)) {
											if(!cv$guard$indirection2) {
												cv$guard$indirection2 = true;
												state.logProbability$indirection2 = (state.logProbability$indirection2 + cv$sampleValue);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			state.logProbability$indirection1 = (state.logProbability$indirection1 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample61)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		for(int i = 0; i < 10; i += 1) {
			double[] var55 = state.indirection1[i];
			for(int j = 0; j < state.length$observed; j += 1) {
				if(!state.fixedFlag$sample61)
					var55[j] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			}
		}
		for(int k = 0; k < state.length$observed; k += 1) {
			double[] var83 = state.indirection2[k];
			for(int l = 0; l < 10; l += 1) {
				if(!state.fixedFlag$sample61)
					var83[l] = state.indirection1[l][k];
			}
		}
		for(int m = 0; m < state.length$observed; m += 1)
			state.generated[m] = DistributionSampling.sampleCategorical(state.RNG$, state.indirection2[m], 10);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int i = 0; i < 10; i += 1) {
			double[] var55 = state.indirection1[i];
			for(int j = 0; j < state.length$observed; j += 1) {
				if(!state.fixedFlag$sample61)
					var55[j] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			}
		}
		for(int k = 0; k < state.length$observed; k += 1) {
			double[] var83 = state.indirection2[k];
			for(int l = 0; l < 10; l += 1)
				var83[l] = state.indirection1[l][k];
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		for(int i = 0; i < 10; i += 1) {
			double[] var55 = state.indirection1[i];
			for(int j = 0; j < state.length$observed; j += 1) {
				if(!state.fixedFlag$sample61)
					var55[j] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			}
		}
		for(int k = 0; k < state.length$observed; k += 1) {
			double[] var83 = state.indirection2[k];
			for(int l = 0; l < 10; l += 1)
				var83[l] = state.indirection1[l][k];
		}
		for(int m = 0; m < state.length$observed; m += 1)
			state.generated[m] = DistributionSampling.sampleCategorical(state.RNG$, state.indirection2[m], 10);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int i = 0; i < 10; i += 1) {
			double[] var55 = state.indirection1[i];
			for(int j = 0; j < state.length$observed; j += 1) {
				if(!state.fixedFlag$sample61)
					var55[j] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			}
		}
		for(int k = 0; k < state.length$observed; k += 1) {
			double[] var83 = state.indirection2[k];
			for(int l = 0; l < 10; l += 1) {
				if(!state.fixedFlag$sample61)
					var83[l] = state.indirection1[l][k];
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		for(int i = 0; i < 10; i += 1) {
			double[] var55 = state.indirection1[i];
			for(int j = 0; j < state.length$observed; j += 1) {
				if(!state.fixedFlag$sample61)
					var55[j] = (0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			}
		}
		for(int k = 0; k < state.length$observed; k += 1) {
			double[] var83 = state.indirection2[k];
			for(int l = 0; l < 10; l += 1)
				var83[l] = state.indirection1[l][k];
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			for(int i = 0; i < 10; i += 1) {
				for(int j = 0; j < state.length$observed; j += 1) {
					if(!state.fixedFlag$sample61)
						inferSample61(i, j);
				}
			}
		} else {
			for(int i = (10 - ((((10 - 1) - 0) % 1) + 1)); i >= ((0 - 1) + 1); i -= 1) {
				for(int j = (state.length$observed - ((((state.length$observed - 1) - 0) % 1) + 1)); j >= ((0 - 1) + 1); j -= 1) {
					if(!state.fixedFlag$sample61)
						inferSample61(i, j);
				}
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int i = 0; i < 10; i += 1) {
			for(int j = 0; j < state.length$observed; j += 1) {
				if(!state.constrainedFlag$sample61[((i - 0) / 1)][((j - 0) / 1)])
					drawValueSample61(i, j);
			}
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$indirection1 = 0.0;
		state.logProbability$indirection2 = 0.0;
		if(!state.fixedProbFlag$sample61) {
			for(int i = 0; i < 10; i += 1) {
				for(int j = 0; j < state.length$observed; j += 1)
					state.logProbability$sample61[((i - 0) / 1)][((j - 0) / 1)] = Double.NaN;
			}
		}
		state.logProbability$generated = 0.0;
		if(!state.fixedProbFlag$sample103) {
			for(int m = 0; m < state.length$observed; m += 1)
				state.logProbability$sample103[((m - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		for(int index$constrainedFlag$sample61$1 = 0; index$constrainedFlag$sample61$1 < state.constrainedFlag$sample61.length; index$constrainedFlag$sample61$1 += 1) {
			boolean[] cv$constrainedFlag$sample61$1 = state.constrainedFlag$sample61[index$constrainedFlag$sample61$1];
			for(int index$constrainedFlag$sample61$2 = 0; index$constrainedFlag$sample61$2 < cv$constrainedFlag$sample61$1.length; index$constrainedFlag$sample61$2 += 1)
				cv$constrainedFlag$sample61$1[index$constrainedFlag$sample61$2] = true;
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample61)
			logProbabilityValue$sample61();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample61();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample61();
		logProbabilityValue$sample103();
	}

	@Override
	public final void propagateObservedValues() {
		int[] cv$source1 = state.observed;
		int[] cv$target1 = state.generated;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		for(int k = 0; k < state.length$observed; k += 1) {
			double[] var83 = state.indirection2[k];
			for(int l = 0; l < 10; l += 1)
				var83[l] = state.indirection1[l][k];
		}
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model ParallelMK5(int[] observed) {\n"
		     + "    int[] generated = new int[observed.length];\n"
		     + "    double[][] indirection1 = new double[10][observed.length];\n"
		     + "    double[][] indirection2 = new double[observed.length][10];\n"
		     + "\n"
		     + "    for(int i=0; i<10; i++) {\n"
		     + "        for(int j=0; j<observed.length; j++) {\n"
		     + "            indirection1[i][j] = uniform(0.0, 1.0).sample();\n"
		     + "        }\n"
		     + "    }\n"
		     + "    \n"
		     + "    for(int k=0; k<observed.length; k++) {\n"
		     + "        for(int l=0; l<10; l++) {\n"
		     + "            indirection2[k][l] = indirection1[l][k];\n"
		     + "        }\n"
		     + "    }\n"
		     + "    \n"
		     + "    for(int m=0; m<observed.length; m++) {\n"
		     + "        generated[m] = categorical(indirection2[m]).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    generated.observe(observed);\n"
		     + "}";
	}
}