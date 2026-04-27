package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.NoisyOr$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.NoisyOr.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class NoisyOr$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var12$stateProbabilityGlobal;
		double[] cv$var15$stateProbabilityGlobal;
		double[] cv$var18$stateProbabilityGlobal;
		double[] cv$var225$stateProbabilityGlobal;
		double[] cv$var238$stateProbabilityGlobal;
		double[] cv$var251$stateProbabilityGlobal;
		double[] cv$var264$stateProbabilityGlobal;
		double[] cv$var277$stateProbabilityGlobal;
		double[] cv$var290$stateProbabilityGlobal;
		double[] cv$var3$stateProbabilityGlobal;
		double[] cv$var6$stateProbabilityGlobal;
		double[] cv$var9$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			{
				cv$var3$stateProbabilityGlobal = new double[2];
			}
			{
				cv$var6$stateProbabilityGlobal = new double[2];
			}
			{
				cv$var9$stateProbabilityGlobal = new double[2];
			}
			{
				cv$var12$stateProbabilityGlobal = new double[2];
			}
			{
				cv$var15$stateProbabilityGlobal = new double[2];
			}
			{
				cv$var18$stateProbabilityGlobal = new double[2];
			}
			{
				cv$var225$stateProbabilityGlobal = new double[2];
			}
			{
				cv$var238$stateProbabilityGlobal = new double[2];
			}
			{
				cv$var251$stateProbabilityGlobal = new double[2];
			}
			{
				cv$var264$stateProbabilityGlobal = new double[2];
			}
			{
				cv$var277$stateProbabilityGlobal = new double[2];
			}
			{
				cv$var290$stateProbabilityGlobal = new double[2];
			}
		}
	}


	public NoisyOr$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample12() {
		state.flag4 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior4);
	}

	private final void drawValueSample15() {
		state.flag5 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior5);
	}

	private final void drawValueSample18() {
		state.flag6 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior6);
	}

	private final void drawValueSample233(int i$var211) {
		double var223;
		if(state.flag1)
			var223 = state.p[0][i$var211];
		else
			var223 = 0.0;
		state.issues$var213[((i$var211 - 0) / 1)][0] = DistributionSampling.sampleBernoulli(state.RNG$, var223);
		{
			{
				if(((0 <= 0) && (0 < 6))) {
					{
						boolean reduceVar$var300$12 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							boolean x$var297 = reduceVar$var300$12;
							boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							reduceVar$var300$12 = (x$var297 || y$var298);
						}
						state.noisyOr[i$var211] = reduceVar$var300$12;
					}
				}
			}
		}
	}

	private final void drawValueSample248(int i$var211) {
		double var236;
		if(state.flag2)
			var236 = state.p[1][i$var211];
		else
			var236 = 0.0;
		state.issues$var213[((i$var211 - 0) / 1)][1] = DistributionSampling.sampleBernoulli(state.RNG$, var236);
		{
			{
				if(((0 <= 1) && (1 < 6))) {
					{
						boolean reduceVar$var300$13 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							boolean x$var297 = reduceVar$var300$13;
							boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							reduceVar$var300$13 = (x$var297 || y$var298);
						}
						state.noisyOr[i$var211] = reduceVar$var300$13;
					}
				}
			}
		}
	}

	private final void drawValueSample263(int i$var211) {
		double var249;
		if(state.flag3)
			var249 = state.p[2][i$var211];
		else
			var249 = 0.0;
		state.issues$var213[((i$var211 - 0) / 1)][2] = DistributionSampling.sampleBernoulli(state.RNG$, var249);
		{
			{
				if(((0 <= 2) && (2 < 6))) {
					{
						boolean reduceVar$var300$14 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							boolean x$var297 = reduceVar$var300$14;
							boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							reduceVar$var300$14 = (x$var297 || y$var298);
						}
						state.noisyOr[i$var211] = reduceVar$var300$14;
					}
				}
			}
		}
	}

	private final void drawValueSample278(int i$var211) {
		double var262;
		if(state.flag4)
			var262 = state.p[3][i$var211];
		else
			var262 = 0.0;
		state.issues$var213[((i$var211 - 0) / 1)][3] = DistributionSampling.sampleBernoulli(state.RNG$, var262);
		{
			{
				if(((0 <= 3) && (3 < 6))) {
					{
						boolean reduceVar$var300$15 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							boolean x$var297 = reduceVar$var300$15;
							boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							reduceVar$var300$15 = (x$var297 || y$var298);
						}
						state.noisyOr[i$var211] = reduceVar$var300$15;
					}
				}
			}
		}
	}

	private final void drawValueSample293(int i$var211) {
		double var275;
		if(state.flag5)
			var275 = state.p[4][i$var211];
		else
			var275 = 0.0;
		state.issues$var213[((i$var211 - 0) / 1)][4] = DistributionSampling.sampleBernoulli(state.RNG$, var275);
		{
			{
				if(((0 <= 4) && (4 < 6))) {
					{
						boolean reduceVar$var300$16 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							boolean x$var297 = reduceVar$var300$16;
							boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							reduceVar$var300$16 = (x$var297 || y$var298);
						}
						state.noisyOr[i$var211] = reduceVar$var300$16;
					}
				}
			}
		}
	}

	private final void drawValueSample3() {
		state.flag1 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior1);
	}

	private final void drawValueSample308(int i$var211) {
		double var288;
		if(state.flag6)
			var288 = state.p[5][i$var211];
		else
			var288 = 0.0;
		state.issues$var213[((i$var211 - 0) / 1)][5] = DistributionSampling.sampleBernoulli(state.RNG$, var288);
		{
			{
				if(((0 <= 5) && (5 < 6))) {
					{
						boolean reduceVar$var300$17 = false;
						for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
							boolean x$var297 = reduceVar$var300$17;
							boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
							reduceVar$var300$17 = (x$var297 || y$var298);
						}
						state.noisyOr[i$var211] = reduceVar$var300$17;
					}
				}
			}
		}
	}

	private final void drawValueSample430(int i$var381, int j) {
		double var402;
		if(state.noisyOr[j])
			var402 = state.p13[j][i$var381];
		else
			var402 = 0.0;
		state.issues$var383[((i$var381 - 0) / 1)][j] = DistributionSampling.sampleBernoulli(state.RNG$, var402);
		{
			{
				if(((0 <= j) && (j < 5))) {
					{
						boolean reduceVar$var414$0 = false;
						for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1) {
							boolean x$var411 = reduceVar$var414$0;
							boolean y$var412 = state.issues$var383[((i$var381 - 0) / 1)][cv$reduction435Index];
							reduceVar$var414$0 = (x$var411 || y$var412);
						}
						state.n13State[i$var381] = reduceVar$var414$0;
					}
				}
			}
		}
	}

	private final void drawValueSample6() {
		state.flag2 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior2);
	}

	private final void drawValueSample9() {
		state.flag3 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior3);
	}

	private final void inferSample12() {
		if(true) {
			state.constrainedFlag$sample12 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var12$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				state.flag4 = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= state.prior4) && (state.prior4 <= 1.0))?Math.log((cv$currentValue?state.prior4:(1.0 - state.prior4))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
									{
										{
											if(cv$currentValue) {
												double traceTempVariable$var262$2_1 = state.p[3][i$var211];
												{
													{
														boolean cv$sampleConstrained = (state.fixedFlag$sample278 || state.constrainedFlag$sample278[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample12 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var262$2_1) && (traceTempVariable$var262$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$2_1:(1.0 - traceTempVariable$var262$2_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var262$2_1) && (traceTempVariable$var262$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$2_1:(1.0 - traceTempVariable$var262$2_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var262$2_1) && (traceTempVariable$var262$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$2_1:(1.0 - traceTempVariable$var262$2_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var262$2_1) && (traceTempVariable$var262$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$2_1:(1.0 - traceTempVariable$var262$2_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var262$2_1) && (traceTempVariable$var262$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$2_1:(1.0 - traceTempVariable$var262$2_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
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
											if(!cv$currentValue) {
												double traceTempVariable$var262$5_1 = 0.0;
												{
													{
														boolean cv$sampleConstrained = (state.fixedFlag$sample278 || state.constrainedFlag$sample278[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample12 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var262$5_1) && (traceTempVariable$var262$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$5_1:(1.0 - traceTempVariable$var262$5_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var262$5_1) && (traceTempVariable$var262$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$5_1:(1.0 - traceTempVariable$var262$5_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var262$5_1) && (traceTempVariable$var262$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$5_1:(1.0 - traceTempVariable$var262$5_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var262$5_1) && (traceTempVariable$var262$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$5_1:(1.0 - traceTempVariable$var262$5_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var262$5_1) && (traceTempVariable$var262$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][3]?traceTempVariable$var262$5_1:(1.0 - traceTempVariable$var262$5_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
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
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample12) {
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
				state.flag4 = (DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
			}
		}
	}

	private final void inferSample15() {
		if(true) {
			state.constrainedFlag$sample15 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var15$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				state.flag5 = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= state.prior5) && (state.prior5 <= 1.0))?Math.log((cv$currentValue?state.prior5:(1.0 - state.prior5))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
									{
										{
											if(cv$currentValue) {
												double traceTempVariable$var275$2_1 = state.p[4][i$var211];
												{
													{
														boolean cv$sampleConstrained = (state.fixedFlag$sample293 || state.constrainedFlag$sample293[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample15 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var275$2_1) && (traceTempVariable$var275$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$2_1:(1.0 - traceTempVariable$var275$2_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var275$2_1) && (traceTempVariable$var275$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$2_1:(1.0 - traceTempVariable$var275$2_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var275$2_1) && (traceTempVariable$var275$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$2_1:(1.0 - traceTempVariable$var275$2_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var275$2_1) && (traceTempVariable$var275$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$2_1:(1.0 - traceTempVariable$var275$2_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var275$2_1) && (traceTempVariable$var275$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$2_1:(1.0 - traceTempVariable$var275$2_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
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
											if(!cv$currentValue) {
												double traceTempVariable$var275$5_1 = 0.0;
												{
													{
														boolean cv$sampleConstrained = (state.fixedFlag$sample293 || state.constrainedFlag$sample293[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample15 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var275$5_1) && (traceTempVariable$var275$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$5_1:(1.0 - traceTempVariable$var275$5_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var275$5_1) && (traceTempVariable$var275$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$5_1:(1.0 - traceTempVariable$var275$5_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var275$5_1) && (traceTempVariable$var275$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$5_1:(1.0 - traceTempVariable$var275$5_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var275$5_1) && (traceTempVariable$var275$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$5_1:(1.0 - traceTempVariable$var275$5_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var275$5_1) && (traceTempVariable$var275$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][4]?traceTempVariable$var275$5_1:(1.0 - traceTempVariable$var275$5_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
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
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample15) {
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
				state.flag5 = (DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
			}
		}
	}

	private final void inferSample18() {
		if(true) {
			state.constrainedFlag$sample18 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var18$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				state.flag6 = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= state.prior6) && (state.prior6 <= 1.0))?Math.log((cv$currentValue?state.prior6:(1.0 - state.prior6))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
									{
										{
											if(cv$currentValue) {
												double traceTempVariable$var288$2_1 = state.p[5][i$var211];
												{
													{
														boolean cv$sampleConstrained = (state.fixedFlag$sample308 || state.constrainedFlag$sample308[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample18 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var288$2_1) && (traceTempVariable$var288$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$2_1:(1.0 - traceTempVariable$var288$2_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var288$2_1) && (traceTempVariable$var288$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$2_1:(1.0 - traceTempVariable$var288$2_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var288$2_1) && (traceTempVariable$var288$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$2_1:(1.0 - traceTempVariable$var288$2_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var288$2_1) && (traceTempVariable$var288$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$2_1:(1.0 - traceTempVariable$var288$2_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var288$2_1) && (traceTempVariable$var288$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$2_1:(1.0 - traceTempVariable$var288$2_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
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
											if(!cv$currentValue) {
												double traceTempVariable$var288$5_1 = 0.0;
												{
													{
														boolean cv$sampleConstrained = (state.fixedFlag$sample308 || state.constrainedFlag$sample308[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample18 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var288$5_1) && (traceTempVariable$var288$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$5_1:(1.0 - traceTempVariable$var288$5_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var288$5_1) && (traceTempVariable$var288$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$5_1:(1.0 - traceTempVariable$var288$5_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var288$5_1) && (traceTempVariable$var288$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$5_1:(1.0 - traceTempVariable$var288$5_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var288$5_1) && (traceTempVariable$var288$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$5_1:(1.0 - traceTempVariable$var288$5_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var288$5_1) && (traceTempVariable$var288$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][5]?traceTempVariable$var288$5_1:(1.0 - traceTempVariable$var288$5_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
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
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample18) {
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
				state.flag6 = (DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
			}
		}
	}

	private final void inferSample233(int i$var211) {
		if(true) {
			state.constrainedFlag$sample233[((i$var211 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var225$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				boolean var225 = cv$currentValue;
				{
					{
						{
							state.issues$var213[((i$var211 - 0) / 1)][0] = cv$currentValue;
						}
					}
				}
				{
					{
						if(((0 <= 0) && (0 < 6))) {
							{
								boolean reduceVar$var300$0 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$0;
									boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$0 = (x$var297 || y$var298);
								}
								state.noisyOr[i$var211] = reduceVar$var300$0;
							}
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double var223;
					if(state.flag1)
						var223 = state.p[0][i$var211];
					else
						var223 = 0.0;
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= var223) && (var223 <= 1.0))?Math.log((cv$currentValue?var223:(1.0 - var223))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								if(((0 <= 0) && (0 < 6))) {
									for(int j = 0; j < 5; j += 1) {
										if((i$var211 == j)) {
											for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
												{
													{
														if(state.noisyOr[j]) {
															double traceTempVariable$var402$4_1 = state.p13[j][i$var381];
															{
																{
																	boolean cv$sampleConstrained = state.fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample233[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
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
														if(!state.noisyOr[j]) {
															double traceTempVariable$var402$7_1 = 0.0;
															{
																{
																	boolean cv$sampleConstrained = state.fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample233[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
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
			if(state.constrainedFlag$sample233[((i$var211 - 0) / 1)]) {
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
				boolean var225 = (DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
				{
					{
						{
							state.issues$var213[((i$var211 - 0) / 1)][0] = var225;
						}
					}
				}
				{
					{
						if(((0 <= 0) && (0 < 6))) {
							{
								boolean reduceVar$var300$1 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$1;
									boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$1 = (x$var297 || y$var298);
								}
								state.noisyOr[i$var211] = reduceVar$var300$1;
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample248(int i$var211) {
		if(true) {
			state.constrainedFlag$sample248[((i$var211 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var238$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				boolean var238 = cv$currentValue;
				{
					{
						{
							state.issues$var213[((i$var211 - 0) / 1)][1] = cv$currentValue;
						}
					}
				}
				{
					{
						if(((0 <= 1) && (1 < 6))) {
							{
								boolean reduceVar$var300$2 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$2;
									boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$2 = (x$var297 || y$var298);
								}
								state.noisyOr[i$var211] = reduceVar$var300$2;
							}
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double var236;
					if(state.flag2)
						var236 = state.p[1][i$var211];
					else
						var236 = 0.0;
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= var236) && (var236 <= 1.0))?Math.log((cv$currentValue?var236:(1.0 - var236))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								if(((0 <= 1) && (1 < 6))) {
									for(int j = 0; j < 5; j += 1) {
										if((i$var211 == j)) {
											for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
												{
													{
														if(state.noisyOr[j]) {
															double traceTempVariable$var402$4_1 = state.p13[j][i$var381];
															{
																{
																	boolean cv$sampleConstrained = state.fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample248[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
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
														if(!state.noisyOr[j]) {
															double traceTempVariable$var402$7_1 = 0.0;
															{
																{
																	boolean cv$sampleConstrained = state.fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample248[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
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
			if(state.constrainedFlag$sample248[((i$var211 - 0) / 1)]) {
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
				boolean var238 = (DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
				{
					{
						{
							state.issues$var213[((i$var211 - 0) / 1)][1] = var238;
						}
					}
				}
				{
					{
						if(((0 <= 1) && (1 < 6))) {
							{
								boolean reduceVar$var300$3 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$3;
									boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$3 = (x$var297 || y$var298);
								}
								state.noisyOr[i$var211] = reduceVar$var300$3;
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample263(int i$var211) {
		if(true) {
			state.constrainedFlag$sample263[((i$var211 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var251$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				boolean var251 = cv$currentValue;
				{
					{
						{
							state.issues$var213[((i$var211 - 0) / 1)][2] = cv$currentValue;
						}
					}
				}
				{
					{
						if(((0 <= 2) && (2 < 6))) {
							{
								boolean reduceVar$var300$4 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$4;
									boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$4 = (x$var297 || y$var298);
								}
								state.noisyOr[i$var211] = reduceVar$var300$4;
							}
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double var249;
					if(state.flag3)
						var249 = state.p[2][i$var211];
					else
						var249 = 0.0;
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= var249) && (var249 <= 1.0))?Math.log((cv$currentValue?var249:(1.0 - var249))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								if(((0 <= 2) && (2 < 6))) {
									for(int j = 0; j < 5; j += 1) {
										if((i$var211 == j)) {
											for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
												{
													{
														if(state.noisyOr[j]) {
															double traceTempVariable$var402$4_1 = state.p13[j][i$var381];
															{
																{
																	boolean cv$sampleConstrained = state.fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample263[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
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
														if(!state.noisyOr[j]) {
															double traceTempVariable$var402$7_1 = 0.0;
															{
																{
																	boolean cv$sampleConstrained = state.fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample263[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
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
			if(state.constrainedFlag$sample263[((i$var211 - 0) / 1)]) {
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
				boolean var251 = (DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
				{
					{
						{
							state.issues$var213[((i$var211 - 0) / 1)][2] = var251;
						}
					}
				}
				{
					{
						if(((0 <= 2) && (2 < 6))) {
							{
								boolean reduceVar$var300$5 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$5;
									boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$5 = (x$var297 || y$var298);
								}
								state.noisyOr[i$var211] = reduceVar$var300$5;
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample278(int i$var211) {
		if(true) {
			state.constrainedFlag$sample278[((i$var211 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var264$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				boolean var264 = cv$currentValue;
				{
					{
						{
							state.issues$var213[((i$var211 - 0) / 1)][3] = cv$currentValue;
						}
					}
				}
				{
					{
						if(((0 <= 3) && (3 < 6))) {
							{
								boolean reduceVar$var300$6 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$6;
									boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$6 = (x$var297 || y$var298);
								}
								state.noisyOr[i$var211] = reduceVar$var300$6;
							}
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double var262;
					if(state.flag4)
						var262 = state.p[3][i$var211];
					else
						var262 = 0.0;
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= var262) && (var262 <= 1.0))?Math.log((cv$currentValue?var262:(1.0 - var262))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								if(((0 <= 3) && (3 < 6))) {
									for(int j = 0; j < 5; j += 1) {
										if((i$var211 == j)) {
											for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
												{
													{
														if(state.noisyOr[j]) {
															double traceTempVariable$var402$4_1 = state.p13[j][i$var381];
															{
																{
																	boolean cv$sampleConstrained = state.fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample278[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
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
														if(!state.noisyOr[j]) {
															double traceTempVariable$var402$7_1 = 0.0;
															{
																{
																	boolean cv$sampleConstrained = state.fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample278[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
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
			if(state.constrainedFlag$sample278[((i$var211 - 0) / 1)]) {
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
				boolean var264 = (DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
				{
					{
						{
							state.issues$var213[((i$var211 - 0) / 1)][3] = var264;
						}
					}
				}
				{
					{
						if(((0 <= 3) && (3 < 6))) {
							{
								boolean reduceVar$var300$7 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$7;
									boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$7 = (x$var297 || y$var298);
								}
								state.noisyOr[i$var211] = reduceVar$var300$7;
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample293(int i$var211) {
		if(true) {
			state.constrainedFlag$sample293[((i$var211 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var277$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				boolean var277 = cv$currentValue;
				{
					{
						{
							state.issues$var213[((i$var211 - 0) / 1)][4] = cv$currentValue;
						}
					}
				}
				{
					{
						if(((0 <= 4) && (4 < 6))) {
							{
								boolean reduceVar$var300$8 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$8;
									boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$8 = (x$var297 || y$var298);
								}
								state.noisyOr[i$var211] = reduceVar$var300$8;
							}
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double var275;
					if(state.flag5)
						var275 = state.p[4][i$var211];
					else
						var275 = 0.0;
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= var275) && (var275 <= 1.0))?Math.log((cv$currentValue?var275:(1.0 - var275))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								if(((0 <= 4) && (4 < 6))) {
									for(int j = 0; j < 5; j += 1) {
										if((i$var211 == j)) {
											for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
												{
													{
														if(state.noisyOr[j]) {
															double traceTempVariable$var402$4_1 = state.p13[j][i$var381];
															{
																{
																	boolean cv$sampleConstrained = state.fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample293[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
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
														if(!state.noisyOr[j]) {
															double traceTempVariable$var402$7_1 = 0.0;
															{
																{
																	boolean cv$sampleConstrained = state.fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample293[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
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
			if(state.constrainedFlag$sample293[((i$var211 - 0) / 1)]) {
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
				boolean var277 = (DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
				{
					{
						{
							state.issues$var213[((i$var211 - 0) / 1)][4] = var277;
						}
					}
				}
				{
					{
						if(((0 <= 4) && (4 < 6))) {
							{
								boolean reduceVar$var300$9 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$9;
									boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$9 = (x$var297 || y$var298);
								}
								state.noisyOr[i$var211] = reduceVar$var300$9;
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample3() {
		if(true) {
			state.constrainedFlag$sample3 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var3$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				state.flag1 = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= state.prior1) && (state.prior1 <= 1.0))?Math.log((cv$currentValue?state.prior1:(1.0 - state.prior1))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
									{
										{
											if(cv$currentValue) {
												double traceTempVariable$var223$2_1 = state.p[0][i$var211];
												{
													{
														boolean cv$sampleConstrained = (state.fixedFlag$sample233 || state.constrainedFlag$sample233[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample3 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var223$2_1) && (traceTempVariable$var223$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$2_1:(1.0 - traceTempVariable$var223$2_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var223$2_1) && (traceTempVariable$var223$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$2_1:(1.0 - traceTempVariable$var223$2_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var223$2_1) && (traceTempVariable$var223$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$2_1:(1.0 - traceTempVariable$var223$2_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var223$2_1) && (traceTempVariable$var223$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$2_1:(1.0 - traceTempVariable$var223$2_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var223$2_1) && (traceTempVariable$var223$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$2_1:(1.0 - traceTempVariable$var223$2_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
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
											if(!cv$currentValue) {
												double traceTempVariable$var223$5_1 = 0.0;
												{
													{
														boolean cv$sampleConstrained = (state.fixedFlag$sample233 || state.constrainedFlag$sample233[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample3 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var223$5_1) && (traceTempVariable$var223$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$5_1:(1.0 - traceTempVariable$var223$5_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var223$5_1) && (traceTempVariable$var223$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$5_1:(1.0 - traceTempVariable$var223$5_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var223$5_1) && (traceTempVariable$var223$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$5_1:(1.0 - traceTempVariable$var223$5_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var223$5_1) && (traceTempVariable$var223$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$5_1:(1.0 - traceTempVariable$var223$5_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var223$5_1) && (traceTempVariable$var223$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][0]?traceTempVariable$var223$5_1:(1.0 - traceTempVariable$var223$5_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
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
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample3) {
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
				state.flag1 = (DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
			}
		}
	}

	private final void inferSample308(int i$var211) {
		if(true) {
			state.constrainedFlag$sample308[((i$var211 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var290$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				boolean var290 = cv$currentValue;
				{
					{
						{
							state.issues$var213[((i$var211 - 0) / 1)][5] = cv$currentValue;
						}
					}
				}
				{
					{
						if(((0 <= 5) && (5 < 6))) {
							{
								boolean reduceVar$var300$10 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$10;
									boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$10 = (x$var297 || y$var298);
								}
								state.noisyOr[i$var211] = reduceVar$var300$10;
							}
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double var288;
					if(state.flag6)
						var288 = state.p[5][i$var211];
					else
						var288 = 0.0;
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= var288) && (var288 <= 1.0))?Math.log((cv$currentValue?var288:(1.0 - var288))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								if(((0 <= 5) && (5 < 6))) {
									for(int j = 0; j < 5; j += 1) {
										if((i$var211 == j)) {
											for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
												{
													{
														if(state.noisyOr[j]) {
															double traceTempVariable$var402$4_1 = state.p13[j][i$var381];
															{
																{
																	boolean cv$sampleConstrained = state.fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample308[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$4_1) && (traceTempVariable$var402$4_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$4_1:(1.0 - traceTempVariable$var402$4_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
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
														if(!state.noisyOr[j]) {
															double traceTempVariable$var402$7_1 = 0.0;
															{
																{
																	boolean cv$sampleConstrained = state.fixedFlag$sample430;
																	if(cv$sampleConstrained) {
																		state.constrainedFlag$sample308[((i$var211 - 0) / 1)] = true;
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						{
																							{
																								if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var402$7_1) && (traceTempVariable$var402$7_1 <= 1.0))?Math.log((state.issues$var383[((i$var381 - 0) / 1)][j]?traceTempVariable$var402$7_1:(1.0 - traceTempVariable$var402$7_1))):Double.NEGATIVE_INFINITY)));
																								}
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																							}
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
			if(state.constrainedFlag$sample308[((i$var211 - 0) / 1)]) {
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
				boolean var290 = (DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
				{
					{
						{
							state.issues$var213[((i$var211 - 0) / 1)][5] = var290;
						}
					}
				}
				{
					{
						if(((0 <= 5) && (5 < 6))) {
							{
								boolean reduceVar$var300$11 = false;
								for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
									boolean x$var297 = reduceVar$var300$11;
									boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
									reduceVar$var300$11 = (x$var297 || y$var298);
								}
								state.noisyOr[i$var211] = reduceVar$var300$11;
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample6() {
		if(true) {
			state.constrainedFlag$sample6 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var6$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				state.flag2 = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= state.prior2) && (state.prior2 <= 1.0))?Math.log((cv$currentValue?state.prior2:(1.0 - state.prior2))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
									{
										{
											if(cv$currentValue) {
												double traceTempVariable$var236$2_1 = state.p[1][i$var211];
												{
													{
														boolean cv$sampleConstrained = (state.fixedFlag$sample248 || state.constrainedFlag$sample248[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample6 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var236$2_1) && (traceTempVariable$var236$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$2_1:(1.0 - traceTempVariable$var236$2_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var236$2_1) && (traceTempVariable$var236$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$2_1:(1.0 - traceTempVariable$var236$2_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var236$2_1) && (traceTempVariable$var236$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$2_1:(1.0 - traceTempVariable$var236$2_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var236$2_1) && (traceTempVariable$var236$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$2_1:(1.0 - traceTempVariable$var236$2_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var236$2_1) && (traceTempVariable$var236$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$2_1:(1.0 - traceTempVariable$var236$2_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
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
											if(!cv$currentValue) {
												double traceTempVariable$var236$5_1 = 0.0;
												{
													{
														boolean cv$sampleConstrained = (state.fixedFlag$sample248 || state.constrainedFlag$sample248[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample6 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var236$5_1) && (traceTempVariable$var236$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$5_1:(1.0 - traceTempVariable$var236$5_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var236$5_1) && (traceTempVariable$var236$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$5_1:(1.0 - traceTempVariable$var236$5_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var236$5_1) && (traceTempVariable$var236$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$5_1:(1.0 - traceTempVariable$var236$5_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var236$5_1) && (traceTempVariable$var236$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$5_1:(1.0 - traceTempVariable$var236$5_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var236$5_1) && (traceTempVariable$var236$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][1]?traceTempVariable$var236$5_1:(1.0 - traceTempVariable$var236$5_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
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
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample6) {
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
				state.flag2 = (DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
			}
		}
	}

	private final void inferSample9() {
		if(true) {
			state.constrainedFlag$sample9 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var9$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				state.flag3 = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= state.prior3) && (state.prior3 <= 1.0))?Math.log((cv$currentValue?state.prior3:(1.0 - state.prior3))):Double.NEGATIVE_INFINITY));
					{
						{
							{
								for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
									{
										{
											if(cv$currentValue) {
												double traceTempVariable$var249$2_1 = state.p[2][i$var211];
												{
													{
														boolean cv$sampleConstrained = (state.fixedFlag$sample263 || state.constrainedFlag$sample263[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample9 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var249$2_1) && (traceTempVariable$var249$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$2_1:(1.0 - traceTempVariable$var249$2_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var249$2_1) && (traceTempVariable$var249$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$2_1:(1.0 - traceTempVariable$var249$2_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var249$2_1) && (traceTempVariable$var249$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$2_1:(1.0 - traceTempVariable$var249$2_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var249$2_1) && (traceTempVariable$var249$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$2_1:(1.0 - traceTempVariable$var249$2_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var249$2_1) && (traceTempVariable$var249$2_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$2_1:(1.0 - traceTempVariable$var249$2_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
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
											if(!cv$currentValue) {
												double traceTempVariable$var249$5_1 = 0.0;
												{
													{
														boolean cv$sampleConstrained = (state.fixedFlag$sample263 || state.constrainedFlag$sample263[((i$var211 - 0) / 1)]);
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample9 = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				{
																					if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var249$5_1) && (traceTempVariable$var249$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$5_1:(1.0 - traceTempVariable$var249$5_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var249$5_1) && (traceTempVariable$var249$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$5_1:(1.0 - traceTempVariable$var249$5_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var249$5_1) && (traceTempVariable$var249$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$5_1:(1.0 - traceTempVariable$var249$5_1))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var249$5_1) && (traceTempVariable$var249$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$5_1:(1.0 - traceTempVariable$var249$5_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var249$5_1) && (traceTempVariable$var249$5_1 <= 1.0))?Math.log((state.issues$var213[((i$var211 - 0) / 1)][2]?traceTempVariable$var249$5_1:(1.0 - traceTempVariable$var249$5_1))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																				}
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
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample9) {
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
				state.flag3 = (DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates) == 1);
			}
		}
	}

	private final void logProbabilityValue$sample12() {
		if(!state.fixedProbFlag$sample12) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					boolean cv$sampleValue = state.flag4;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= state.prior4) && (state.prior4 <= 1.0))?Math.log((cv$sampleValue?state.prior4:(1.0 - state.prior4))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$flag4 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample12)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample12 = state.fixedFlag$sample12;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$flag4;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample12)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample15() {
		if(!state.fixedProbFlag$sample15) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					boolean cv$sampleValue = state.flag5;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= state.prior5) && (state.prior5 <= 1.0))?Math.log((cv$sampleValue?state.prior5:(1.0 - state.prior5))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$flag5 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample15)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample15 = state.fixedFlag$sample15;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$flag5;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample15)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample18() {
		if(!state.fixedProbFlag$sample18) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					boolean cv$sampleValue = state.flag6;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= state.prior6) && (state.prior6 <= 1.0))?Math.log((cv$sampleValue?state.prior6:(1.0 - state.prior6))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$flag6 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample18)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample18 = state.fixedFlag$sample18;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$flag6;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample18)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample233() {
		if(!state.fixedProbFlag$sample233) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.issues$var213[((i$var211 - 0) / 1)][0];
						{
							{
								double var223;
								if(state.flag1)
									var223 = state.p[0][i$var211];
								else
									var223 = 0.0;
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var223) && (var223 <= 1.0))?Math.log((cv$sampleValue?var223:(1.0 - var223))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample233[((i$var211 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 0) && (0 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleProbability);
							}
						}
					}
				}
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample233)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample233 = (state.fixedFlag$sample233 && state.fixedFlag$sample3);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample233[((i$var211 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 0) && (0 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleValue);
							}
						}
					}
				}
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample233)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample248() {
		if(!state.fixedProbFlag$sample248) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.issues$var213[((i$var211 - 0) / 1)][1];
						{
							{
								double var236;
								if(state.flag2)
									var236 = state.p[1][i$var211];
								else
									var236 = 0.0;
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var236) && (var236 <= 1.0))?Math.log((cv$sampleValue?var236:(1.0 - var236))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample248[((i$var211 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 1) && (1 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleProbability);
							}
						}
					}
				}
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample248)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample248 = (state.fixedFlag$sample248 && state.fixedFlag$sample6);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample248[((i$var211 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 1) && (1 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleValue);
							}
						}
					}
				}
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample248)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample263() {
		if(!state.fixedProbFlag$sample263) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.issues$var213[((i$var211 - 0) / 1)][2];
						{
							{
								double var249;
								if(state.flag3)
									var249 = state.p[2][i$var211];
								else
									var249 = 0.0;
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var249) && (var249 <= 1.0))?Math.log((cv$sampleValue?var249:(1.0 - var249))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample263[((i$var211 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 2) && (2 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleProbability);
							}
						}
					}
				}
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample263)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample263 = (state.fixedFlag$sample263 && state.fixedFlag$sample9);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample263[((i$var211 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 2) && (2 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleValue);
							}
						}
					}
				}
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample263)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample278() {
		if(!state.fixedProbFlag$sample278) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.issues$var213[((i$var211 - 0) / 1)][3];
						{
							{
								double var262;
								if(state.flag4)
									var262 = state.p[3][i$var211];
								else
									var262 = 0.0;
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var262) && (var262 <= 1.0))?Math.log((cv$sampleValue?var262:(1.0 - var262))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample278[((i$var211 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 3) && (3 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleProbability);
							}
						}
					}
				}
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample278)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample278 = (state.fixedFlag$sample278 && state.fixedFlag$sample12);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample278[((i$var211 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 3) && (3 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleValue);
							}
						}
					}
				}
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample278)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample293() {
		if(!state.fixedProbFlag$sample293) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.issues$var213[((i$var211 - 0) / 1)][4];
						{
							{
								double var275;
								if(state.flag5)
									var275 = state.p[4][i$var211];
								else
									var275 = 0.0;
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var275) && (var275 <= 1.0))?Math.log((cv$sampleValue?var275:(1.0 - var275))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample293[((i$var211 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 4) && (4 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleProbability);
							}
						}
					}
				}
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample293)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample293 = (state.fixedFlag$sample293 && state.fixedFlag$sample15);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample293[((i$var211 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 4) && (4 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleValue);
							}
						}
					}
				}
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample293)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample3() {
		if(!state.fixedProbFlag$sample3) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					boolean cv$sampleValue = state.flag1;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= state.prior1) && (state.prior1 <= 1.0))?Math.log((cv$sampleValue?state.prior1:(1.0 - state.prior1))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$flag1 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample3)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample3 = state.fixedFlag$sample3;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$flag1;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample3)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample308() {
		if(!state.fixedProbFlag$sample308) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.issues$var213[((i$var211 - 0) / 1)][5];
						{
							{
								double var288;
								if(state.flag6)
									var288 = state.p[5][i$var211];
								else
									var288 = 0.0;
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var288) && (var288 <= 1.0))?Math.log((cv$sampleValue?var288:(1.0 - var288))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample308[((i$var211 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 5) && (5 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleProbability);
							}
						}
					}
				}
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample308)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample308 = (state.fixedFlag$sample308 && state.fixedFlag$sample18);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample308[((i$var211 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				boolean cv$guard$noisyOr = false;
				{
					{
						if(((0 <= 5) && (5 < 6))) {
							if(!cv$guard$noisyOr) {
								cv$guard$noisyOr = true;
								state.logProbability$noisyOr = (state.logProbability$noisyOr + cv$sampleValue);
							}
						}
					}
				}
			}
			state.logProbability$issues$var213 = (state.logProbability$issues$var213 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample308)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample430() {
		if(!state.fixedProbFlag$sample430) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
				for(int j = 0; j < 5; j += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							boolean cv$sampleValue = state.issues$var383[((i$var381 - 0) / 1)][j];
							{
								{
									double var402;
									if(state.noisyOr[j])
										var402 = state.p13[j][i$var381];
									else
										var402 = 0.0;
									double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var402) && (var402 <= 1.0))?Math.log((cv$sampleValue?var402:(1.0 - var402))):Double.NEGATIVE_INFINITY));
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
					state.logProbability$sample430[((i$var381 - 0) / 1)][((j - 0) / 1)] = cv$sampleProbability;
					boolean cv$guard$n13State = false;
					{
						{
							if(((0 <= j) && (j < 5))) {
								if(!cv$guard$n13State) {
									cv$guard$n13State = true;
									state.logProbability$n13State = (state.logProbability$n13State + cv$sampleProbability);
								}
							}
						}
					}
				}
			}
			state.logProbability$issues$var383 = (state.logProbability$issues$var383 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample430)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample430 = ((((((state.fixedFlag$sample430 && state.fixedFlag$sample233) && state.fixedFlag$sample248) && state.fixedFlag$sample263) && state.fixedFlag$sample278) && state.fixedFlag$sample293) && state.fixedFlag$sample308);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
				for(int j = 0; j < 5; j += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample430[((i$var381 - 0) / 1)][((j - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					boolean cv$guard$n13State = false;
					{
						{
							if(((0 <= j) && (j < 5))) {
								if(!cv$guard$n13State) {
									cv$guard$n13State = true;
									state.logProbability$n13State = (state.logProbability$n13State + cv$sampleValue);
								}
							}
						}
					}
				}
			}
			state.logProbability$issues$var383 = (state.logProbability$issues$var383 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample430)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample6() {
		if(!state.fixedProbFlag$sample6) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					boolean cv$sampleValue = state.flag2;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= state.prior2) && (state.prior2 <= 1.0))?Math.log((cv$sampleValue?state.prior2:(1.0 - state.prior2))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$flag2 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample6)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample6 = state.fixedFlag$sample6;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$flag2;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample6)
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
					boolean cv$sampleValue = state.flag3;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= state.prior3) && (state.prior3 <= 1.0))?Math.log((cv$sampleValue?state.prior3:(1.0 - state.prior3))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$flag3 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample9)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample9 = state.fixedFlag$sample9;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$flag3;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample9)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample3)
			state.flag1 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior1);
		if(!state.fixedFlag$sample6)
			state.flag2 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior2);
		if(!state.fixedFlag$sample9)
			state.flag3 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior3);
		if(!state.fixedFlag$sample12)
			state.flag4 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior4);
		if(!state.fixedFlag$sample15)
			state.flag5 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior5);
		if(!state.fixedFlag$sample18)
			state.flag6 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior6);
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			double var223 = 0.0;
			if(state.flag1) {
				if(!state.fixedFlag$sample233)
					var223 = state.p[0][i$var211];
			} else {
				if(!state.fixedFlag$sample233)
					var223 = 0.0;
			}
			if(!state.fixedFlag$sample233)
				state.issues$var213[((i$var211 - 0) / 1)][0] = DistributionSampling.sampleBernoulli(state.RNG$, var223);
			double var236 = 0.0;
			if(state.flag2) {
				if(!state.fixedFlag$sample248)
					var236 = state.p[1][i$var211];
			} else {
				if(!state.fixedFlag$sample248)
					var236 = 0.0;
			}
			if(!state.fixedFlag$sample248)
				state.issues$var213[((i$var211 - 0) / 1)][1] = DistributionSampling.sampleBernoulli(state.RNG$, var236);
			double var249 = 0.0;
			if(state.flag3) {
				if(!state.fixedFlag$sample263)
					var249 = state.p[2][i$var211];
			} else {
				if(!state.fixedFlag$sample263)
					var249 = 0.0;
			}
			if(!state.fixedFlag$sample263)
				state.issues$var213[((i$var211 - 0) / 1)][2] = DistributionSampling.sampleBernoulli(state.RNG$, var249);
			double var262 = 0.0;
			if(state.flag4) {
				if(!state.fixedFlag$sample278)
					var262 = state.p[3][i$var211];
			} else {
				if(!state.fixedFlag$sample278)
					var262 = 0.0;
			}
			if(!state.fixedFlag$sample278)
				state.issues$var213[((i$var211 - 0) / 1)][3] = DistributionSampling.sampleBernoulli(state.RNG$, var262);
			double var275 = 0.0;
			if(state.flag5) {
				if(!state.fixedFlag$sample293)
					var275 = state.p[4][i$var211];
			} else {
				if(!state.fixedFlag$sample293)
					var275 = 0.0;
			}
			if(!state.fixedFlag$sample293)
				state.issues$var213[((i$var211 - 0) / 1)][4] = DistributionSampling.sampleBernoulli(state.RNG$, var275);
			double var288 = 0.0;
			if(state.flag6) {
				if(!state.fixedFlag$sample308)
					var288 = state.p[5][i$var211];
			} else {
				if(!state.fixedFlag$sample308)
					var288 = 0.0;
			}
			if(!state.fixedFlag$sample308)
				state.issues$var213[((i$var211 - 0) / 1)][5] = DistributionSampling.sampleBernoulli(state.RNG$, var288);
			boolean reduceVar$var300$18 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
				boolean x$var297 = reduceVar$var300$18;
				boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
				if(!(((((state.fixedFlag$sample233 && state.fixedFlag$sample248) && state.fixedFlag$sample263) && state.fixedFlag$sample278) && state.fixedFlag$sample293) && state.fixedFlag$sample308))
					reduceVar$var300$18 = (x$var297 || y$var298);
			}
			if(!(((((state.fixedFlag$sample233 && state.fixedFlag$sample248) && state.fixedFlag$sample263) && state.fixedFlag$sample278) && state.fixedFlag$sample293) && state.fixedFlag$sample308))
				state.noisyOr[i$var211] = reduceVar$var300$18;
		}
		for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
			for(int j = 0; j < 5; j += 1) {
				double var402 = 0.0;
				if(state.noisyOr[j]) {
					if(!state.fixedFlag$sample430)
						var402 = state.p13[j][i$var381];
				} else {
					if(!state.fixedFlag$sample430)
						var402 = 0.0;
				}
				if(!state.fixedFlag$sample430)
					state.issues$var383[((i$var381 - 0) / 1)][j] = DistributionSampling.sampleBernoulli(state.RNG$, var402);
			}
			boolean reduceVar$var414$1 = false;
			for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1) {
				boolean x$var411 = reduceVar$var414$1;
				boolean y$var412 = state.issues$var383[((i$var381 - 0) / 1)][cv$reduction435Index];
				if(!state.fixedFlag$sample430)
					reduceVar$var414$1 = (x$var411 || y$var412);
			}
			if(!state.fixedFlag$sample430)
				state.n13State[i$var381] = reduceVar$var414$1;
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample3)
			state.flag1 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior1);
		if(!state.fixedFlag$sample6)
			state.flag2 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior2);
		if(!state.fixedFlag$sample9)
			state.flag3 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior3);
		if(!state.fixedFlag$sample12)
			state.flag4 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior4);
		if(!state.fixedFlag$sample15)
			state.flag5 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior5);
		if(!state.fixedFlag$sample18)
			state.flag6 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior6);
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			double var223 = 0.0;
			if(state.flag1) {
				if(!state.fixedFlag$sample233)
					var223 = state.p[0][i$var211];
			} else {
				if(!state.fixedFlag$sample233)
					var223 = 0.0;
			}
			if(!state.fixedFlag$sample233)
				state.issues$var213[((i$var211 - 0) / 1)][0] = DistributionSampling.sampleBernoulli(state.RNG$, var223);
			double var236 = 0.0;
			if(state.flag2) {
				if(!state.fixedFlag$sample248)
					var236 = state.p[1][i$var211];
			} else {
				if(!state.fixedFlag$sample248)
					var236 = 0.0;
			}
			if(!state.fixedFlag$sample248)
				state.issues$var213[((i$var211 - 0) / 1)][1] = DistributionSampling.sampleBernoulli(state.RNG$, var236);
			double var249 = 0.0;
			if(state.flag3) {
				if(!state.fixedFlag$sample263)
					var249 = state.p[2][i$var211];
			} else {
				if(!state.fixedFlag$sample263)
					var249 = 0.0;
			}
			if(!state.fixedFlag$sample263)
				state.issues$var213[((i$var211 - 0) / 1)][2] = DistributionSampling.sampleBernoulli(state.RNG$, var249);
			double var262 = 0.0;
			if(state.flag4) {
				if(!state.fixedFlag$sample278)
					var262 = state.p[3][i$var211];
			} else {
				if(!state.fixedFlag$sample278)
					var262 = 0.0;
			}
			if(!state.fixedFlag$sample278)
				state.issues$var213[((i$var211 - 0) / 1)][3] = DistributionSampling.sampleBernoulli(state.RNG$, var262);
			double var275 = 0.0;
			if(state.flag5) {
				if(!state.fixedFlag$sample293)
					var275 = state.p[4][i$var211];
			} else {
				if(!state.fixedFlag$sample293)
					var275 = 0.0;
			}
			if(!state.fixedFlag$sample293)
				state.issues$var213[((i$var211 - 0) / 1)][4] = DistributionSampling.sampleBernoulli(state.RNG$, var275);
			double var288 = 0.0;
			if(state.flag6) {
				if(!state.fixedFlag$sample308)
					var288 = state.p[5][i$var211];
			} else {
				if(!state.fixedFlag$sample308)
					var288 = 0.0;
			}
			if(!state.fixedFlag$sample308)
				state.issues$var213[((i$var211 - 0) / 1)][5] = DistributionSampling.sampleBernoulli(state.RNG$, var288);
			boolean reduceVar$var300$22 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
				boolean x$var297 = reduceVar$var300$22;
				boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
				reduceVar$var300$22 = (x$var297 || y$var298);
			}
			state.noisyOr[i$var211] = reduceVar$var300$22;
		}
		for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
			for(int j = 0; j < 5; j += 1) {
				double var402 = 0.0;
				if(state.noisyOr[j]) {
					if(!state.fixedFlag$sample430)
						var402 = state.p13[j][i$var381];
				} else {
					if(!state.fixedFlag$sample430)
						var402 = 0.0;
				}
				if(!state.fixedFlag$sample430)
					state.issues$var383[((i$var381 - 0) / 1)][j] = DistributionSampling.sampleBernoulli(state.RNG$, var402);
			}
			boolean reduceVar$var414$5 = false;
			for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1) {
				boolean x$var411 = reduceVar$var414$5;
				boolean y$var412 = state.issues$var383[((i$var381 - 0) / 1)][cv$reduction435Index];
				reduceVar$var414$5 = (x$var411 || y$var412);
			}
			state.n13State[i$var381] = reduceVar$var414$5;
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample3)
			state.flag1 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior1);
		if(!state.fixedFlag$sample6)
			state.flag2 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior2);
		if(!state.fixedFlag$sample9)
			state.flag3 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior3);
		if(!state.fixedFlag$sample12)
			state.flag4 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior4);
		if(!state.fixedFlag$sample15)
			state.flag5 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior5);
		if(!state.fixedFlag$sample18)
			state.flag6 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior6);
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			double var223 = 0.0;
			if(state.flag1) {
				if(!state.fixedFlag$sample233)
					var223 = state.p[0][i$var211];
			} else {
				if(!state.fixedFlag$sample233)
					var223 = 0.0;
			}
			if(!state.fixedFlag$sample233)
				state.issues$var213[((i$var211 - 0) / 1)][0] = DistributionSampling.sampleBernoulli(state.RNG$, var223);
			double var236 = 0.0;
			if(state.flag2) {
				if(!state.fixedFlag$sample248)
					var236 = state.p[1][i$var211];
			} else {
				if(!state.fixedFlag$sample248)
					var236 = 0.0;
			}
			if(!state.fixedFlag$sample248)
				state.issues$var213[((i$var211 - 0) / 1)][1] = DistributionSampling.sampleBernoulli(state.RNG$, var236);
			double var249 = 0.0;
			if(state.flag3) {
				if(!state.fixedFlag$sample263)
					var249 = state.p[2][i$var211];
			} else {
				if(!state.fixedFlag$sample263)
					var249 = 0.0;
			}
			if(!state.fixedFlag$sample263)
				state.issues$var213[((i$var211 - 0) / 1)][2] = DistributionSampling.sampleBernoulli(state.RNG$, var249);
			double var262 = 0.0;
			if(state.flag4) {
				if(!state.fixedFlag$sample278)
					var262 = state.p[3][i$var211];
			} else {
				if(!state.fixedFlag$sample278)
					var262 = 0.0;
			}
			if(!state.fixedFlag$sample278)
				state.issues$var213[((i$var211 - 0) / 1)][3] = DistributionSampling.sampleBernoulli(state.RNG$, var262);
			double var275 = 0.0;
			if(state.flag5) {
				if(!state.fixedFlag$sample293)
					var275 = state.p[4][i$var211];
			} else {
				if(!state.fixedFlag$sample293)
					var275 = 0.0;
			}
			if(!state.fixedFlag$sample293)
				state.issues$var213[((i$var211 - 0) / 1)][4] = DistributionSampling.sampleBernoulli(state.RNG$, var275);
			double var288 = 0.0;
			if(state.flag6) {
				if(!state.fixedFlag$sample308)
					var288 = state.p[5][i$var211];
			} else {
				if(!state.fixedFlag$sample308)
					var288 = 0.0;
			}
			if(!state.fixedFlag$sample308)
				state.issues$var213[((i$var211 - 0) / 1)][5] = DistributionSampling.sampleBernoulli(state.RNG$, var288);
			boolean reduceVar$var300$19 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
				boolean x$var297 = reduceVar$var300$19;
				boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
				reduceVar$var300$19 = (x$var297 || y$var298);
			}
			state.noisyOr[i$var211] = reduceVar$var300$19;
		}
		for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
			for(int j = 0; j < 5; j += 1) {
				double var402 = 0.0;
				if(state.noisyOr[j]) {
					if(!state.fixedFlag$sample430)
						var402 = state.p13[j][i$var381];
				} else {
					if(!state.fixedFlag$sample430)
						var402 = 0.0;
				}
				if(!state.fixedFlag$sample430)
					state.issues$var383[((i$var381 - 0) / 1)][j] = DistributionSampling.sampleBernoulli(state.RNG$, var402);
			}
			boolean reduceVar$var414$2 = false;
			for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1) {
				boolean x$var411 = reduceVar$var414$2;
				boolean y$var412 = state.issues$var383[((i$var381 - 0) / 1)][cv$reduction435Index];
				reduceVar$var414$2 = (x$var411 || y$var412);
			}
			state.n13State[i$var381] = reduceVar$var414$2;
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample3)
			state.flag1 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior1);
		if(!state.fixedFlag$sample6)
			state.flag2 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior2);
		if(!state.fixedFlag$sample9)
			state.flag3 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior3);
		if(!state.fixedFlag$sample12)
			state.flag4 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior4);
		if(!state.fixedFlag$sample15)
			state.flag5 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior5);
		if(!state.fixedFlag$sample18)
			state.flag6 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior6);
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			double var223 = 0.0;
			if(state.flag1) {
				if(!state.fixedFlag$sample233)
					var223 = state.p[0][i$var211];
			} else {
				if(!state.fixedFlag$sample233)
					var223 = 0.0;
			}
			if(!state.fixedFlag$sample233)
				state.issues$var213[((i$var211 - 0) / 1)][0] = DistributionSampling.sampleBernoulli(state.RNG$, var223);
			double var236 = 0.0;
			if(state.flag2) {
				if(!state.fixedFlag$sample248)
					var236 = state.p[1][i$var211];
			} else {
				if(!state.fixedFlag$sample248)
					var236 = 0.0;
			}
			if(!state.fixedFlag$sample248)
				state.issues$var213[((i$var211 - 0) / 1)][1] = DistributionSampling.sampleBernoulli(state.RNG$, var236);
			double var249 = 0.0;
			if(state.flag3) {
				if(!state.fixedFlag$sample263)
					var249 = state.p[2][i$var211];
			} else {
				if(!state.fixedFlag$sample263)
					var249 = 0.0;
			}
			if(!state.fixedFlag$sample263)
				state.issues$var213[((i$var211 - 0) / 1)][2] = DistributionSampling.sampleBernoulli(state.RNG$, var249);
			double var262 = 0.0;
			if(state.flag4) {
				if(!state.fixedFlag$sample278)
					var262 = state.p[3][i$var211];
			} else {
				if(!state.fixedFlag$sample278)
					var262 = 0.0;
			}
			if(!state.fixedFlag$sample278)
				state.issues$var213[((i$var211 - 0) / 1)][3] = DistributionSampling.sampleBernoulli(state.RNG$, var262);
			double var275 = 0.0;
			if(state.flag5) {
				if(!state.fixedFlag$sample293)
					var275 = state.p[4][i$var211];
			} else {
				if(!state.fixedFlag$sample293)
					var275 = 0.0;
			}
			if(!state.fixedFlag$sample293)
				state.issues$var213[((i$var211 - 0) / 1)][4] = DistributionSampling.sampleBernoulli(state.RNG$, var275);
			double var288 = 0.0;
			if(state.flag6) {
				if(!state.fixedFlag$sample308)
					var288 = state.p[5][i$var211];
			} else {
				if(!state.fixedFlag$sample308)
					var288 = 0.0;
			}
			if(!state.fixedFlag$sample308)
				state.issues$var213[((i$var211 - 0) / 1)][5] = DistributionSampling.sampleBernoulli(state.RNG$, var288);
			boolean reduceVar$var300$20 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
				boolean x$var297 = reduceVar$var300$20;
				boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
				if(!(((((state.fixedFlag$sample233 && state.fixedFlag$sample248) && state.fixedFlag$sample263) && state.fixedFlag$sample278) && state.fixedFlag$sample293) && state.fixedFlag$sample308))
					reduceVar$var300$20 = (x$var297 || y$var298);
			}
			if(!(((((state.fixedFlag$sample233 && state.fixedFlag$sample248) && state.fixedFlag$sample263) && state.fixedFlag$sample278) && state.fixedFlag$sample293) && state.fixedFlag$sample308))
				state.noisyOr[i$var211] = reduceVar$var300$20;
		}
		for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
			for(int j = 0; j < 5; j += 1) {
				double var402 = 0.0;
				if(state.noisyOr[j]) {
					if(!state.fixedFlag$sample430)
						var402 = state.p13[j][i$var381];
				} else {
					if(!state.fixedFlag$sample430)
						var402 = 0.0;
				}
				if(!state.fixedFlag$sample430)
					state.issues$var383[((i$var381 - 0) / 1)][j] = DistributionSampling.sampleBernoulli(state.RNG$, var402);
			}
			boolean reduceVar$var414$3 = false;
			for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1) {
				boolean x$var411 = reduceVar$var414$3;
				boolean y$var412 = state.issues$var383[((i$var381 - 0) / 1)][cv$reduction435Index];
				if(!state.fixedFlag$sample430)
					reduceVar$var414$3 = (x$var411 || y$var412);
			}
			if(!state.fixedFlag$sample430)
				state.n13State[i$var381] = reduceVar$var414$3;
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample3)
			state.flag1 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior1);
		if(!state.fixedFlag$sample6)
			state.flag2 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior2);
		if(!state.fixedFlag$sample9)
			state.flag3 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior3);
		if(!state.fixedFlag$sample12)
			state.flag4 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior4);
		if(!state.fixedFlag$sample15)
			state.flag5 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior5);
		if(!state.fixedFlag$sample18)
			state.flag6 = DistributionSampling.sampleBernoulli(state.RNG$, state.prior6);
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			double var223 = 0.0;
			if(state.flag1) {
				if(!state.fixedFlag$sample233)
					var223 = state.p[0][i$var211];
			} else {
				if(!state.fixedFlag$sample233)
					var223 = 0.0;
			}
			if(!state.fixedFlag$sample233)
				state.issues$var213[((i$var211 - 0) / 1)][0] = DistributionSampling.sampleBernoulli(state.RNG$, var223);
			double var236 = 0.0;
			if(state.flag2) {
				if(!state.fixedFlag$sample248)
					var236 = state.p[1][i$var211];
			} else {
				if(!state.fixedFlag$sample248)
					var236 = 0.0;
			}
			if(!state.fixedFlag$sample248)
				state.issues$var213[((i$var211 - 0) / 1)][1] = DistributionSampling.sampleBernoulli(state.RNG$, var236);
			double var249 = 0.0;
			if(state.flag3) {
				if(!state.fixedFlag$sample263)
					var249 = state.p[2][i$var211];
			} else {
				if(!state.fixedFlag$sample263)
					var249 = 0.0;
			}
			if(!state.fixedFlag$sample263)
				state.issues$var213[((i$var211 - 0) / 1)][2] = DistributionSampling.sampleBernoulli(state.RNG$, var249);
			double var262 = 0.0;
			if(state.flag4) {
				if(!state.fixedFlag$sample278)
					var262 = state.p[3][i$var211];
			} else {
				if(!state.fixedFlag$sample278)
					var262 = 0.0;
			}
			if(!state.fixedFlag$sample278)
				state.issues$var213[((i$var211 - 0) / 1)][3] = DistributionSampling.sampleBernoulli(state.RNG$, var262);
			double var275 = 0.0;
			if(state.flag5) {
				if(!state.fixedFlag$sample293)
					var275 = state.p[4][i$var211];
			} else {
				if(!state.fixedFlag$sample293)
					var275 = 0.0;
			}
			if(!state.fixedFlag$sample293)
				state.issues$var213[((i$var211 - 0) / 1)][4] = DistributionSampling.sampleBernoulli(state.RNG$, var275);
			double var288 = 0.0;
			if(state.flag6) {
				if(!state.fixedFlag$sample308)
					var288 = state.p[5][i$var211];
			} else {
				if(!state.fixedFlag$sample308)
					var288 = 0.0;
			}
			if(!state.fixedFlag$sample308)
				state.issues$var213[((i$var211 - 0) / 1)][5] = DistributionSampling.sampleBernoulli(state.RNG$, var288);
			boolean reduceVar$var300$21 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
				boolean x$var297 = reduceVar$var300$21;
				boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
				reduceVar$var300$21 = (x$var297 || y$var298);
			}
			state.noisyOr[i$var211] = reduceVar$var300$21;
		}
		for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
			for(int j = 0; j < 5; j += 1) {
				double var402 = 0.0;
				if(state.noisyOr[j]) {
					if(!state.fixedFlag$sample430)
						var402 = state.p13[j][i$var381];
				} else {
					if(!state.fixedFlag$sample430)
						var402 = 0.0;
				}
				if(!state.fixedFlag$sample430)
					state.issues$var383[((i$var381 - 0) / 1)][j] = DistributionSampling.sampleBernoulli(state.RNG$, var402);
			}
			boolean reduceVar$var414$4 = false;
			for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1) {
				boolean x$var411 = reduceVar$var414$4;
				boolean y$var412 = state.issues$var383[((i$var381 - 0) / 1)][cv$reduction435Index];
				reduceVar$var414$4 = (x$var411 || y$var412);
			}
			state.n13State[i$var381] = reduceVar$var414$4;
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample3)
				inferSample3();
			if(!state.fixedFlag$sample6)
				inferSample6();
			if(!state.fixedFlag$sample9)
				inferSample9();
			if(!state.fixedFlag$sample12)
				inferSample12();
			if(!state.fixedFlag$sample15)
				inferSample15();
			if(!state.fixedFlag$sample18)
				inferSample18();
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
				if(!state.fixedFlag$sample233)
					inferSample233(i$var211);
				if(!state.fixedFlag$sample248)
					inferSample248(i$var211);
				if(!state.fixedFlag$sample263)
					inferSample263(i$var211);
				if(!state.fixedFlag$sample278)
					inferSample278(i$var211);
				if(!state.fixedFlag$sample293)
					inferSample293(i$var211);
				if(!state.fixedFlag$sample308)
					inferSample308(i$var211);
			}
		} else {
			for(int i$var211 = (5 - ((((5 - 1) - 0) % 1) + 1)); i$var211 >= ((0 - 1) + 1); i$var211 -= 1) {
				if(!state.fixedFlag$sample308)
					inferSample308(i$var211);
				if(!state.fixedFlag$sample293)
					inferSample293(i$var211);
				if(!state.fixedFlag$sample278)
					inferSample278(i$var211);
				if(!state.fixedFlag$sample263)
					inferSample263(i$var211);
				if(!state.fixedFlag$sample248)
					inferSample248(i$var211);
				if(!state.fixedFlag$sample233)
					inferSample233(i$var211);
			}
			if(!state.fixedFlag$sample18)
				inferSample18();
			if(!state.fixedFlag$sample15)
				inferSample15();
			if(!state.fixedFlag$sample12)
				inferSample12();
			if(!state.fixedFlag$sample9)
				inferSample9();
			if(!state.fixedFlag$sample6)
				inferSample6();
			if(!state.fixedFlag$sample3)
				inferSample3();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample3)
			drawValueSample3();
		if(!state.constrainedFlag$sample6)
			drawValueSample6();
		if(!state.constrainedFlag$sample9)
			drawValueSample9();
		if(!state.constrainedFlag$sample12)
			drawValueSample12();
		if(!state.constrainedFlag$sample15)
			drawValueSample15();
		if(!state.constrainedFlag$sample18)
			drawValueSample18();
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			if(!state.constrainedFlag$sample233[((i$var211 - 0) / 1)])
				drawValueSample233(i$var211);
			if(!state.constrainedFlag$sample248[((i$var211 - 0) / 1)])
				drawValueSample248(i$var211);
			if(!state.constrainedFlag$sample263[((i$var211 - 0) / 1)])
				drawValueSample263(i$var211);
			if(!state.constrainedFlag$sample278[((i$var211 - 0) / 1)])
				drawValueSample278(i$var211);
			if(!state.constrainedFlag$sample293[((i$var211 - 0) / 1)])
				drawValueSample293(i$var211);
			if(!state.constrainedFlag$sample308[((i$var211 - 0) / 1)])
				drawValueSample308(i$var211);
		}
		for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
			for(int j = 0; j < 5; j += 1) {
				if(!state.fixedFlag$sample430)
					drawValueSample430(i$var381, j);
			}
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample3)
			state.logProbability$flag1 = Double.NaN;
		if(!state.fixedProbFlag$sample6)
			state.logProbability$flag2 = Double.NaN;
		if(!state.fixedProbFlag$sample9)
			state.logProbability$flag3 = Double.NaN;
		if(!state.fixedProbFlag$sample12)
			state.logProbability$flag4 = Double.NaN;
		if(!state.fixedProbFlag$sample15)
			state.logProbability$flag5 = Double.NaN;
		if(!state.fixedProbFlag$sample18)
			state.logProbability$flag6 = Double.NaN;
		state.logProbability$issues$var213 = 0.0;
		state.logProbability$noisyOr = 0.0;
		if(!state.fixedProbFlag$sample233) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				state.logProbability$sample233[((i$var211 - 0) / 1)] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample248) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				state.logProbability$sample248[((i$var211 - 0) / 1)] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample263) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				state.logProbability$sample263[((i$var211 - 0) / 1)] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample278) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				state.logProbability$sample278[((i$var211 - 0) / 1)] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample293) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				state.logProbability$sample293[((i$var211 - 0) / 1)] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample308) {
			for(int i$var211 = 0; i$var211 < 5; i$var211 += 1)
				state.logProbability$sample308[((i$var211 - 0) / 1)] = Double.NaN;
		}
		state.logProbability$issues$var383 = 0.0;
		state.logProbability$n13State = 0.0;
		if(!state.fixedProbFlag$sample430) {
			for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
				for(int j = 0; j < 5; j += 1)
					state.logProbability$sample430[((i$var381 - 0) / 1)][((j - 0) / 1)] = Double.NaN;
			}
		}
	}

	@Override
	public final void initializeModel() {
		state.prior1 = 0.01;
		state.prior2 = 0.01;
		state.prior3 = 0.01;
		state.prior4 = 0.01;
		state.prior5 = 0.01;
		state.prior6 = 0.01;
		double[] var23 = state.p[0];
		var23[0] = 0.0;
		var23[1] = 1.0;
		var23[2] = 0.0;
		var23[3] = 0.0;
		var23[4] = 0.0;
		double[] var53 = state.p[1];
		var53[0] = 0.5;
		var53[1] = 0.5;
		var53[2] = 0.0;
		var53[3] = 0.0;
		var53[4] = 0.0;
		double[] var81 = state.p[2];
		var81[0] = 0.0;
		var81[1] = 0.0;
		var81[2] = 0.0;
		var81[3] = 1.0;
		var81[4] = 0.0;
		double[] var111 = state.p[3];
		var111[0] = 0.0;
		var111[1] = 0.0;
		var111[2] = 0.0;
		var111[3] = 1.0;
		var111[4] = 0.0;
		double[] var141 = state.p[4];
		var141[0] = 0.0;
		var141[1] = 0.0;
		var141[2] = 1.0;
		var141[3] = 0.0;
		var141[4] = 0.0;
		double[] var171 = state.p[5];
		var171[0] = 0.0;
		var171[1] = 0.0;
		var171[2] = 1.0;
		var171[3] = 0.0;
		var171[4] = 0.0;
		double[] var306 = state.p13[0];
		var306[0] = 0.1;
		var306[1] = 0.9;
		double[] var319 = state.p13[1];
		var319[0] = 1.0;
		var319[1] = 0.0;
		double[] var332 = state.p13[2];
		var332[0] = 0.5;
		var332[1] = 0.5;
		double[] var345 = state.p13[3];
		var345[0] = 0.5;
		var345[1] = 0.5;
		double[] var358 = state.p13[4];
		var358[0] = 0.0;
		var358[1] = 1.0;
		for(int index$constrainedFlag$sample233$1 = 0; index$constrainedFlag$sample233$1 < state.constrainedFlag$sample233.length; index$constrainedFlag$sample233$1 += 1)
			state.constrainedFlag$sample233[index$constrainedFlag$sample233$1] = true;
		for(int index$constrainedFlag$sample248$1 = 0; index$constrainedFlag$sample248$1 < state.constrainedFlag$sample248.length; index$constrainedFlag$sample248$1 += 1)
			state.constrainedFlag$sample248[index$constrainedFlag$sample248$1] = true;
		for(int index$constrainedFlag$sample263$1 = 0; index$constrainedFlag$sample263$1 < state.constrainedFlag$sample263.length; index$constrainedFlag$sample263$1 += 1)
			state.constrainedFlag$sample263[index$constrainedFlag$sample263$1] = true;
		for(int index$constrainedFlag$sample278$1 = 0; index$constrainedFlag$sample278$1 < state.constrainedFlag$sample278.length; index$constrainedFlag$sample278$1 += 1)
			state.constrainedFlag$sample278[index$constrainedFlag$sample278$1] = true;
		for(int index$constrainedFlag$sample293$1 = 0; index$constrainedFlag$sample293$1 < state.constrainedFlag$sample293.length; index$constrainedFlag$sample293$1 += 1)
			state.constrainedFlag$sample293[index$constrainedFlag$sample293$1] = true;
		for(int index$constrainedFlag$sample308$1 = 0; index$constrainedFlag$sample308$1 < state.constrainedFlag$sample308.length; index$constrainedFlag$sample308$1 += 1)
			state.constrainedFlag$sample308[index$constrainedFlag$sample308$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample3)
			logProbabilityValue$sample3();
		if(state.fixedFlag$sample6)
			logProbabilityValue$sample6();
		if(state.fixedFlag$sample9)
			logProbabilityValue$sample9();
		if(state.fixedFlag$sample12)
			logProbabilityValue$sample12();
		if(state.fixedFlag$sample15)
			logProbabilityValue$sample15();
		if(state.fixedFlag$sample18)
			logProbabilityValue$sample18();
		if(state.fixedFlag$sample233)
			logProbabilityValue$sample233();
		if(state.fixedFlag$sample248)
			logProbabilityValue$sample248();
		if(state.fixedFlag$sample263)
			logProbabilityValue$sample263();
		if(state.fixedFlag$sample278)
			logProbabilityValue$sample278();
		if(state.fixedFlag$sample293)
			logProbabilityValue$sample293();
		if(state.fixedFlag$sample308)
			logProbabilityValue$sample308();
		if(state.fixedFlag$sample430)
			logProbabilityValue$sample430();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample3();
		logProbabilityValue$sample6();
		logProbabilityValue$sample9();
		logProbabilityValue$sample12();
		logProbabilityValue$sample15();
		logProbabilityValue$sample18();
		logProbabilityValue$sample233();
		logProbabilityValue$sample248();
		logProbabilityValue$sample263();
		logProbabilityValue$sample278();
		logProbabilityValue$sample293();
		logProbabilityValue$sample308();
		logProbabilityValue$sample430();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample3();
		logProbabilityValue$sample6();
		logProbabilityValue$sample9();
		logProbabilityValue$sample12();
		logProbabilityValue$sample15();
		logProbabilityValue$sample18();
		logProbabilityValue$sample233();
		logProbabilityValue$sample248();
		logProbabilityValue$sample263();
		logProbabilityValue$sample278();
		logProbabilityValue$sample293();
		logProbabilityValue$sample308();
		logProbabilityValue$sample430();
	}

	@Override
	public final void propagateObservedValues() {}

	@Override
	public final void setIntermediates() {
		for(int i$var211 = 0; i$var211 < 5; i$var211 += 1) {
			boolean reduceVar$var300$23 = false;
			for(int cv$reduction313Index = 0; cv$reduction313Index < 6; cv$reduction313Index += 1) {
				boolean x$var297 = reduceVar$var300$23;
				boolean y$var298 = state.issues$var213[((i$var211 - 0) / 1)][cv$reduction313Index];
				reduceVar$var300$23 = (x$var297 || y$var298);
			}
			state.noisyOr[i$var211] = reduceVar$var300$23;
		}
		for(int i$var381 = 0; i$var381 < 2; i$var381 += 1) {
			boolean reduceVar$var414$6 = false;
			for(int cv$reduction435Index = 0; cv$reduction435Index < 5; cv$reduction435Index += 1) {
				boolean x$var411 = reduceVar$var414$6;
				boolean y$var412 = state.issues$var383[((i$var381 - 0) / 1)][cv$reduction435Index];
				reduceVar$var414$6 = (x$var411 || y$var412);
			}
			state.n13State[i$var381] = reduceVar$var414$6;
		}
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2026, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model NoisyOr() {\n"
		     + "\n"
		     + "\n"
		     + "    // 1)\n"
		     + "    double prior1 = 0.01;\n"
		     + "    boolean flag1 = bernoulli(prior1).sample();\n"
		     + "    \n"
		     + "    // 2)\n"
		     + "    double prior2 = 0.01;\n"
		     + "    boolean flag2 = bernoulli(prior2).sample();\n"
		     + "    \n"
		     + "    // 3)\n"
		     + "    double prior3 = 0.01;\n"
		     + "    boolean flag3 = bernoulli(prior3).sample();\n"
		     + "    \n"
		     + "    // 4)\n"
		     + "    double prior4 = 0.01;\n"
		     + "    boolean flag4 = bernoulli(prior4).sample();\n"
		     + "    \n"
		     + "    // 5)\n"
		     + "    double prior5 = 0.01;\n"
		     + "    boolean flag5 = bernoulli(prior5).sample();\n"
		     + "    \n"
		     + "    // 6)\n"
		     + "    double prior6 = 0.01;\n"
		     + "    boolean flag6 = bernoulli(prior6).sample();\n"
		     + "    \n"
		     + "    // Start n12\n"
		     + "    double[][] p = new double[6][];\n"
		     + "    p[0] = new double[] {0,1,0,0,0};\n"
		     + "    p[1] = new double[] {0.5,0.5,0,0,0};\n"
		     + "    p[2] = new double[] {0,0,0,1,0};\n"
		     + "    p[3] = new double[] {0,0,0,1,0};\n"
		     + "    p[4] = new double[] {0,0,1,0,0};\n"
		     + "    p[5] = new double[] {0,0,1,0,0};\n"
		     + "    \n"
		     + "    boolean[] noisyOr = new boolean[5];\n"
		     + "    \n"
		     + "    for(int i=0; i<5; i++) {\n"
		     + "        boolean[] issues = new boolean[6];\n"
		     + "        issues[0] = bernoulli(flag1?p[0][i]:0).sample();\n"
		     + "        issues[1] = bernoulli(flag2?p[1][i]:0).sample();\n"
		     + "        issues[2] = bernoulli(flag3?p[2][i]:0).sample();\n"
		     + "        issues[3] = bernoulli(flag4?p[3][i]:0).sample();\n"
		     + "        issues[4] = bernoulli(flag5?p[4][i]:0).sample();\n"
		     + "        issues[5] = bernoulli(flag6?p[5][i]:0).sample();\n"
		     + "        \n"
		     + "        noisyOr[i] = reduce(issues, false, (x, y) -> {\n"
		     + "            return x || y;\n"
		     + "        });\n"
		     + "    }\n"
		     + "    \n"
		     + "    // Starting n13\n"
		     + "    double[][] p13 = new double[5][];\n"
		     + "    p13[0] = new double[] {0.1, 0.9};\n"
		     + "    p13[1] = new double[] {1.0, 0.0};\n"
		     + "    p13[2] = new double[] {0.5, 0.5};\n"
		     + "    p13[3] = new double[] {0.5, 0.5};\n"
		     + "    p13[4] = new double[] {0.0, 1.0};\n"
		     + "    \n"
		     + "    boolean[] n13State = new boolean[2];\n"
		     + "    \n"
		     + "    for(int i=0; i<2; i++) {\n"
		     + "        boolean[] issues = new boolean[5];\n"
		     + "        for(int j=0; j<5; j++)\n"
		     + "            issues[j] = bernoulli(noisyOr[j]?p13[j][i]:0).sample();\n"
		     + "        \n"
		     + "        n13State[i] = reduce(issues, false, (x, y) -> {\n"
		     + "            return x || y;\n"
		     + "        });\n"
		     + "    }\n"
		     + "}";
	}
}