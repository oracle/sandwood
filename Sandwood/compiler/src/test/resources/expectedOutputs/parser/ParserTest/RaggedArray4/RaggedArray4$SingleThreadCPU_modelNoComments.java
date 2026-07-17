package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.RaggedArray4$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.RaggedArray4.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class RaggedArray4$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var45$stateProbabilityGlobal;
		double[] cv$var48$countGlobal;

		@Override
		public final void allocateScratch() {
			{
				cv$var45$stateProbabilityGlobal = new double[2];
			}
			{
				int cv$var33$max = 2;
				cv$var33$max = Math.max(cv$var33$max, 3);
				cv$var48$countGlobal = new double[cv$var33$max];
			}
		}
	}


	public RaggedArray4$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample47() {
		state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
	}

	private final void drawValueSample50() {
		int lengthCV$a$48_4 = -1;
		{
			{
				if((0 == state.y))
					lengthCV$a$48_4 = 2;
			}
		}
		{
			{
				if((1 == state.y))
					lengthCV$a$48_4 = 3;
			}
		}
		DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_4, state.d);
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
				state.y = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < 2)) && (0 < 2)) && (0.0 <= state.b[cv$currentValue])) && (state.b[cv$currentValue] <= 1.0))?Math.log(state.b[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$y$3_1 = cv$currentValue;
								{
									{
										boolean cv$sampleConstrained = (state.fixedFlag$sample50 || state.constrainedFlag$sample50);
										if(cv$sampleConstrained) {
											state.constrainedFlag$sample47 = true;
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															{
																double[] var46 = state.a[traceTempVariable$y$3_1];
																int lengthCV$a$48_1 = -1;
																{
																	{
																		if((0 == traceTempVariable$y$3_1))
																			lengthCV$a$48_1 = 2;
																	}
																}
																{
																	{
																		if((1 == traceTempVariable$y$3_1))
																			lengthCV$a$48_1 = 3;
																	}
																}
																if(((Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(state.d, var46, lengthCV$a$48_1)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(state.d, var46, lengthCV$a$48_1)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(state.d, var46, lengthCV$a$48_1));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(state.d, var46, lengthCV$a$48_1)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(state.d, var46, lengthCV$a$48_1)));
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
				state.y = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
			}
		}
	}

	private final void inferSample50() {
		if(true) {
			state.constrainedFlag$sample50 = false;
			double[] cv$targetLocal = state.d;
			double[] cv$countLocal = scratch.cv$var48$countGlobal;
			int lengthCV$a$48_2 = -1;
			{
				{
					if((0 == state.y))
						lengthCV$a$48_2 = 2;
				}
			}
			{
				{
					if((1 == state.y))
						lengthCV$a$48_2 = 3;
				}
			}
			int cv$arrayLength = lengthCV$a$48_2;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							{
								{
									for(int var61 = 0; var61 < state.length$obs_measured; var61 += 1) {
										boolean cv$sampleConstrained = true;
										if(cv$sampleConstrained) {
											state.constrainedFlag$sample50 = true;
											{
												{
													{
														{
															{
																cv$countLocal[state.obs[var61]] = (cv$countLocal[state.obs[var61]] + 1.0);
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
			if(state.constrainedFlag$sample50) {
				int lengthCV$a$48_3 = -1;
				{
					{
						if((0 == state.y))
							lengthCV$a$48_3 = 2;
					}
				}
				{
					{
						if((1 == state.y))
							lengthCV$a$48_3 = 3;
					}
				}
				Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.a[state.y], cv$countLocal, cv$targetLocal, lengthCV$a$48_3);
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
					int cv$sampleValue = state.y;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0 < 2)) && (0.0 <= state.b[cv$sampleValue])) && (state.b[cv$sampleValue] <= 1.0))?Math.log(state.b[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$y = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample47)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample47 = state.fixedFlag$sample47;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$y;
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
					double[] cv$sampleValue = state.d;
					{
						{
							double[] var46 = state.a[state.y];
							int lengthCV$a$48_5 = -1;
							{
								{
									if((0 == state.y))
										lengthCV$a$48_5 = 2;
								}
							}
							{
								{
									if((1 == state.y))
										lengthCV$a$48_5 = 3;
								}
							}
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, var46, lengthCV$a$48_5));
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
			state.logProbability$d = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample50)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample50 = (state.fixedFlag$sample50 && state.fixedFlag$sample47);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$d;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample50)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample64() {
		if(!state.fixedProbFlag$sample64) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var61 = 0; var61 < state.length$obs_measured; var61 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = state.obs[var61];
						{
							{
								int lengthCV$a$48_6 = -1;
								{
									{
										if((0 == state.y))
											lengthCV$a$48_6 = 2;
									}
								}
								{
									{
										if((1 == state.y))
											lengthCV$a$48_6 = 3;
									}
								}
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$a$48_6)) && (0 < lengthCV$a$48_6)) && (0.0 <= state.d[cv$sampleValue])) && (state.d[cv$sampleValue] <= 1.0))?Math.log(state.d[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$var62 = cv$sampleAccumulator;
			state.logProbability$obs = (state.logProbability$obs + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample64 = state.fixedFlag$sample50;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var61 = 0; var61 < state.length$obs_measured; var61 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var62;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$obs = (state.logProbability$obs + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample47)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
		int lengthCV$a$48_7 = -1;
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample50)
						lengthCV$a$48_7 = 2;
				}
			}
		}
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample50)
						lengthCV$a$48_7 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample50)
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_7, state.d);
		int lengthCV$a$48_8 = -1;
		{
			{
				if((0 == state.y))
					lengthCV$a$48_8 = 2;
			}
		}
		{
			{
				if((1 == state.y))
					lengthCV$a$48_8 = 3;
			}
		}
		for(int var61 = 0; var61 < state.length$obs_measured; var61 += 1)
			state.obs[var61] = DistributionSampling.sampleCategorical(state.RNG$, state.d, lengthCV$a$48_8);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample47)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
		int lengthCV$a$48_13 = -1;
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample50)
						lengthCV$a$48_13 = 2;
				}
			}
		}
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample50)
						lengthCV$a$48_13 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample50)
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_13, state.d);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample47)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
		int lengthCV$a$48_9 = -1;
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample50)
						lengthCV$a$48_9 = 2;
				}
			}
		}
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample50)
						lengthCV$a$48_9 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample50)
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_9, state.d);
		int lengthCV$a$48_10 = -1;
		{
			{
				if((0 == state.y))
					lengthCV$a$48_10 = 2;
			}
		}
		{
			{
				if((1 == state.y))
					lengthCV$a$48_10 = 3;
			}
		}
		for(int var61 = 0; var61 < state.length$obs_measured; var61 += 1)
			state.obs[var61] = DistributionSampling.sampleCategorical(state.RNG$, state.d, lengthCV$a$48_10);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample47)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
		int lengthCV$a$48_11 = -1;
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample50)
						lengthCV$a$48_11 = 2;
				}
			}
		}
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample50)
						lengthCV$a$48_11 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample50)
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_11, state.d);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample47)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
		int lengthCV$a$48_12 = -1;
		{
			{
				if((0 == state.y)) {
					if(!state.fixedFlag$sample50)
						lengthCV$a$48_12 = 2;
				}
			}
		}
		{
			{
				if((1 == state.y)) {
					if(!state.fixedFlag$sample50)
						lengthCV$a$48_12 = 3;
				}
			}
		}
		if(!state.fixedFlag$sample50)
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_12, state.d);
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample47)
				inferSample47();
			if(!state.fixedFlag$sample50)
				inferSample50();
		} else {
			if(!state.fixedFlag$sample50)
				inferSample50();
			if(!state.fixedFlag$sample47)
				inferSample47();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample47)
			drawValueSample47();
		if(!state.constrainedFlag$sample50)
			drawValueSample50();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample47)
			state.logProbability$y = Double.NaN;
		if(!state.fixedProbFlag$sample50)
			state.logProbability$d = Double.NaN;
		state.logProbability$obs = 0.0;
		if(!state.fixedProbFlag$sample64)
			state.logProbability$var62 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		double[] var5 = state.a[0];
		var5[0] = 0.4;
		var5[1] = 0.6;
		double[] var18 = state.a[1];
		var18[0] = 0.2;
		var18[1] = 0.3;
		var18[2] = 0.5;
		state.b[0] = 0.35;
		state.b[1] = 0.65;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample47)
			logProbabilityValue$sample47();
		if(state.fixedFlag$sample50)
			logProbabilityValue$sample50();
		logProbabilityValue$sample64();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		logProbabilityValue$sample64();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		logProbabilityValue$sample64();
	}

	@Override
	public final void propagateObservedValues() {
		int[] cv$source1 = state.obs_measured;
		int[] cv$target1 = state.obs;
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
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + " package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model RaggedArray4(int[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    double[] b = { 0.35, 0.65 };\n"
		     + "    int y = categorical(b).sample();\n"
		     + "    double[] d = dirichlet(a[y]).sample();\n"
		     + "    int[] obs = categorical(d).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}