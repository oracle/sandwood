package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMTestPart4$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMTestPart4.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMTestPart4$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[][] cv$var121$stateProbabilityGlobal;
		double[][] cv$var28$countGlobal;
		double[] cv$var81$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			{
				int cv$threadCount = threadCount();
				cv$var28$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var28$countGlobal[cv$index] = new double[2];
			}
			cv$var81$stateProbabilityGlobal = new double[2];
			int cv$threadCount = threadCount();
			cv$var121$stateProbabilityGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var121$stateProbabilityGlobal[cv$index] = new double[2];
		}
	}


	public HMMTestPart4$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample124(int i1, int j1, int k1, int threadID$cv$k1, Rng RNG$) {
		state.st[i1][j1][k1] = DistributionSampling.sampleCategorical(RNG$, state.m[0], 2);
	}

	private final void drawValueSample28(int var27, int threadID$cv$var27, Rng RNG$) {
		DistributionSampling.sampleDirichlet(RNG$, state.v, 2, state.m[var27]);
	}

	private final void drawValueSample45(int var43, int threadID$cv$var43, Rng RNG$) {
		state.bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	private final void drawValueSample84() {
		state.st[0][0][0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
	}

	private final void inferSample124(int i1, int j1, int k1, int threadID$cv$k1, Rng RNG$) {
		double[] cv$stateProbabilityLocal = scratch.cv$var121$stateProbabilityGlobal[threadID$cv$k1];
		{
			double[] var119 = state.m[0];
			double var184 = state.bias[0];
			cv$stateProbabilityLocal[0] = ((((0.0 <= var184) && (var184 <= 1.0))?Math.log((state.flips[j1][k1][i1]?var184:(1.0 - var184))):Double.NEGATIVE_INFINITY) + (((0.0 <= var119[0]) && (var119[0] <= 1.0))?Math.log(var119[0]):Double.NEGATIVE_INFINITY));
		}
		state.st[i1][j1][k1] = 1;
		double[] var119 = state.m[0];
		state.constrainedFlag$sample124[(i1 - 1)][j1][k1] = true;
		double var184 = state.bias[1];
		cv$stateProbabilityLocal[1] = ((((0.0 <= var184) && (var184 <= 1.0))?Math.log((state.flips[j1][k1][i1]?var184:(1.0 - var184))):Double.NEGATIVE_INFINITY) + (((0.0 <= var119[1]) && (var119[1] <= 1.0))?Math.log(var119[1]):Double.NEGATIVE_INFINITY));
		if(state.constrainedFlag$sample124[(i1 - 1)][j1][k1]) {
			double cv$logSum;
			double cv$lseMax = cv$stateProbabilityLocal[0];
			double cv$lseElementValue = cv$stateProbabilityLocal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$stateProbabilityLocal[0] - cv$lseMax)) + Math.exp((cv$stateProbabilityLocal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$stateProbabilityLocal[0] = 0.5;
				cv$stateProbabilityLocal[1] = 0.5;
			} else {
				cv$stateProbabilityLocal[0] = Math.exp((cv$stateProbabilityLocal[0] - cv$logSum));
				cv$stateProbabilityLocal[1] = Math.exp((cv$stateProbabilityLocal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.st[i1][j1][k1] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2);
		}
	}

	private final void inferSample28(int var27, int threadID$cv$var27, Rng RNG$) {
		state.constrainedFlag$sample28[var27] = false;
		double[] cv$countLocal = scratch.cv$var28$countGlobal[threadID$cv$var27];
		cv$countLocal[0] = 0.0;
		cv$countLocal[1] = 0.0;
		if((var27 == 0)) {
			if((state.fixedFlag$sample84 || state.constrainedFlag$sample84)) {
				state.constrainedFlag$sample28[0] = true;
				cv$countLocal[state.st[0][0][0]] = (cv$countLocal[state.st[0][0][0]] + 1.0);
			}
			for(int i1 = 1; i1 < state.samples; i1 += 1) {
				for(int j1 = 0; j1 < state.samples; j1 += 1) {
					for(int k1 = 0; k1 < state.samples; k1 += 1) {
						if((state.fixedFlag$sample124 || state.constrainedFlag$sample124[(i1 - 1)][j1][k1])) {
							state.constrainedFlag$sample28[0] = true;
							cv$countLocal[state.st[i1][j1][k1]] = (cv$countLocal[state.st[i1][j1][k1]] + 1.0);
						}
					}
				}
			}
		}
		if(state.constrainedFlag$sample28[var27])
			Conjugates.sampleConjugateDirichletCategorical(RNG$, state.v, cv$countLocal, state.m[var27], 2);
	}

	private final void inferSample45(int var43, int threadID$cv$var43, Rng RNG$) {
		state.constrainedFlag$sample45[var43] = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int l = 0; l < state.samples; l += 1) {
			for(int p = 0; p < state.samples; p += 1) {
				for(int n = 0; n < state.samples; n += 1) {
					if((var43 == state.st[p][l][n])) {
						state.constrainedFlag$sample45[var43] = true;
						cv$count = (cv$count + 1);
						if(state.flips[l][n][p])
							cv$sum = (cv$sum + 1);
					}
				}
			}
		}
		if(state.constrainedFlag$sample45[var43])
			state.bias[var43] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void inferSample84() {
		state.constrainedFlag$sample84 = false;
		{
			double[] var79 = state.m[0];
			double cv$accumulatedProbabilities = (((0.0 <= var79[0]) && (var79[0] <= 1.0))?Math.log(var79[0]):Double.NEGATIVE_INFINITY);
			if((0 < state.samples)) {
				state.constrainedFlag$sample84 = true;
				double var184 = state.bias[0];
				cv$accumulatedProbabilities = ((((0.0 <= var184) && (var184 <= 1.0))?Math.log((state.flips[0][0][0]?var184:(1.0 - var184))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			scratch.cv$var81$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.st[0][0][0] = 1;
		double[] var79 = state.m[0];
		double cv$accumulatedProbabilities = (((0.0 <= var79[1]) && (var79[1] <= 1.0))?Math.log(var79[1]):Double.NEGATIVE_INFINITY);
		if((0 < state.samples)) {
			state.constrainedFlag$sample84 = true;
			double var184 = state.bias[1];
			cv$accumulatedProbabilities = ((((0.0 <= var184) && (var184 <= 1.0))?Math.log((state.flips[0][0][0]?var184:(1.0 - var184))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		scratch.cv$var81$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample84) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var81$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var81$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var81$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var81$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var81$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var81$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var81$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var81$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var81$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var81$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var81$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var81$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.st[0][0][0] = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var81$stateProbabilityGlobal, 2);
		}
	}

	private final void logProbabilityValue$sample124() {
		if(!state.fixedProbFlag$sample124) {
			double cv$accumulator = 0.0;
			for(int i1 = 1; i1 < state.samples; i1 += 1) {
				for(int j1 = 0; j1 < state.samples; j1 += 1) {
					for(int k1 = 0; k1 < state.samples; k1 += 1) {
						int cv$sampleValue = state.st[i1][j1][k1];
						double[] var119 = state.m[0];
						double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY);
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						state.logProbability$sample124[(i1 - 1)][j1][k1] = cv$distributionAccumulator;
					}
				}
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample124)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample124 = (state.fixedFlag$sample124 && state.fixedFlag$sample28);
		} else {
			double cv$accumulator = 0.0;
			for(int i1 = 1; i1 < state.samples; i1 += 1) {
				for(int j1 = 0; j1 < state.samples; j1 += 1) {
					for(int k1 = 0; k1 < state.samples; k1 += 1)
						cv$accumulator = (cv$accumulator + state.logProbability$sample124[(i1 - 1)][j1][k1]);
				}
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample124)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample191() {
		if(!state.fixedProbFlag$sample191) {
			double cv$accumulator = 0.0;
			for(int l = 0; l < state.samples; l += 1) {
				for(int p = 0; p < state.samples; p += 1) {
					for(int n = 0; n < state.samples; n += 1) {
						double var184 = state.bias[state.st[p][l][n]];
						double cv$distributionAccumulator = (((0.0 <= var184) && (var184 <= 1.0))?Math.log((state.flips[l][n][p]?var184:(1.0 - var184))):Double.NEGATIVE_INFINITY);
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						state.logProbability$sample191[l][p][n] = cv$distributionAccumulator;
					}
				}
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample191 = ((state.fixedFlag$sample45 && state.fixedFlag$sample84) && state.fixedFlag$sample124);
		} else {
			double cv$accumulator = 0.0;
			for(int l = 0; l < state.samples; l += 1) {
				for(int p = 0; p < state.samples; p += 1) {
					for(int n = 0; n < state.samples; n += 1)
						cv$accumulator = (cv$accumulator + state.logProbability$sample191[l][p][n]);
				}
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!state.fixedProbFlag$sample28) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityDirichlet(state.m[0], state.v, 2) + DistributionSampling.logProbabilityDirichlet(state.m[1], state.v, 2));
			state.logProbability$var28 = cv$sampleAccumulator;
			state.logProbability$m = (state.logProbability$m + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample28 = state.fixedFlag$sample28;
		} else {
			state.logProbability$m = (state.logProbability$m + state.logProbability$var28);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var28);
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var28);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!state.fixedProbFlag$sample45) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityBeta(state.bias[0], 1.0, 1.0) + DistributionSampling.logProbabilityBeta(state.bias[1], 1.0, 1.0));
			state.logProbability$var44 = cv$sampleAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample45)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample45 = state.fixedFlag$sample45;
		} else {
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$var44);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var44);
			if(state.fixedFlag$sample45)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var44);
		}
	}

	private final void logProbabilityValue$sample84() {
		if(!state.fixedProbFlag$sample84) {
			int cv$sampleValue = state.st[0][0][0];
			double[] var79 = state.m[0];
			double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0.0 <= var79[cv$sampleValue])) && (var79[cv$sampleValue] <= 1.0))?Math.log(var79[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			state.logProbability$var81 = cv$distributionAccumulator;
			state.logProbability$st = (state.logProbability$st + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample84)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample84 = (state.fixedFlag$sample84 && state.fixedFlag$sample28);
		} else {
			state.logProbability$st = (state.logProbability$st + state.logProbability$var81);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var81);
			if(state.fixedFlag$sample84)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var81);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample28)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 2, state.m[var27]);
				}
			);

		if(!state.fixedFlag$sample45)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!state.fixedFlag$sample84)
			state.st[0][0][0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		if(!state.fixedFlag$sample124)
			parallelFor(state.RNG$, 1, state.samples, 1,
				(int forStart$i1, int forEnd$i1, int threadID$i1, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i1 = forStart$i1; i1 < forEnd$i1; i1 += 1) {
							int[][] var116 = state.st[i1];
							parallelFor(RNG$1, 0, state.samples, 1,
								(int forStart$index$j1, int forEnd$index$j1, int threadID$index$j1, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int index$j1 = forStart$index$j1; index$j1 < forEnd$index$j1; index$j1 += 1) {
											int j1 = index$j1;
											int threadID$j1 = threadID$index$j1;
											parallelFor(RNG$2, 0, state.samples, 1,
												(int forStart$k1, int forEnd$k1, int threadID$k1, org.sandwood.random.internal.Rng RNG$3) -> { 
													for(int k1 = forStart$k1; k1 < forEnd$k1; k1 += 1)
															var116[j1][k1] = DistributionSampling.sampleCategorical(RNG$3, state.m[0], 2);
												}
											);
										}
								}
							);
						}
				}
			);

		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$index$l, int forEnd$index$l, int threadID$index$l, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$l = forStart$index$l; index$l < forEnd$index$l; index$l += 1) {
						int l = index$l;
						int threadID$l = threadID$index$l;
						boolean[][] var179 = state.flips[l];
						parallelFor(RNG$1, 0, state.samples, 1,
							(int forStart$index$p, int forEnd$index$p, int threadID$index$p, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int index$p = forStart$index$p; index$p < forEnd$index$p; index$p += 1) {
										int p = index$p;
										int threadID$p = threadID$index$p;
										parallelFor(RNG$2, 0, state.samples, 1,
											(int forStart$n, int forEnd$n, int threadID$n, org.sandwood.random.internal.Rng RNG$3) -> { 
												for(int n = forStart$n; n < forEnd$n; n += 1)
														var179[n][p] = DistributionSampling.sampleBernoulli(RNG$3, state.bias[state.st[p][l][n]]);
											}
										);
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample28)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 2, state.m[var27]);
				}
			);

		if(!state.fixedFlag$sample45)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!state.fixedFlag$sample84)
			state.st[0][0][0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		if(!state.fixedFlag$sample124)
			parallelFor(state.RNG$, 1, state.samples, 1,
				(int forStart$i1, int forEnd$i1, int threadID$i1, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i1 = forStart$i1; i1 < forEnd$i1; i1 += 1) {
							int[][] var116 = state.st[i1];
							parallelFor(RNG$1, 0, state.samples, 1,
								(int forStart$index$j1, int forEnd$index$j1, int threadID$index$j1, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int index$j1 = forStart$index$j1; index$j1 < forEnd$index$j1; index$j1 += 1) {
											int j1 = index$j1;
											int threadID$j1 = threadID$index$j1;
											parallelFor(RNG$2, 0, state.samples, 1,
												(int forStart$k1, int forEnd$k1, int threadID$k1, org.sandwood.random.internal.Rng RNG$3) -> { 
													for(int k1 = forStart$k1; k1 < forEnd$k1; k1 += 1)
															var116[j1][k1] = DistributionSampling.sampleCategorical(RNG$3, state.m[0], 2);
												}
											);
										}
								}
							);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample28)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 2, state.m[var27]);
				}
			);

		if(!state.fixedFlag$sample45)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!state.fixedFlag$sample84)
			state.st[0][0][0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		if(!state.fixedFlag$sample124)
			parallelFor(state.RNG$, 1, state.samples, 1,
				(int forStart$i1, int forEnd$i1, int threadID$i1, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i1 = forStart$i1; i1 < forEnd$i1; i1 += 1) {
							int[][] var116 = state.st[i1];
							parallelFor(RNG$1, 0, state.samples, 1,
								(int forStart$index$j1, int forEnd$index$j1, int threadID$index$j1, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int index$j1 = forStart$index$j1; index$j1 < forEnd$index$j1; index$j1 += 1) {
											int j1 = index$j1;
											int threadID$j1 = threadID$index$j1;
											parallelFor(RNG$2, 0, state.samples, 1,
												(int forStart$k1, int forEnd$k1, int threadID$k1, org.sandwood.random.internal.Rng RNG$3) -> { 
													for(int k1 = forStart$k1; k1 < forEnd$k1; k1 += 1)
															var116[j1][k1] = DistributionSampling.sampleCategorical(RNG$3, state.m[0], 2);
												}
											);
										}
								}
							);
						}
				}
			);

		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$index$l, int forEnd$index$l, int threadID$index$l, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$l = forStart$index$l; index$l < forEnd$index$l; index$l += 1) {
						int l = index$l;
						int threadID$l = threadID$index$l;
						boolean[][] var179 = state.flips[l];
						parallelFor(RNG$1, 0, state.samples, 1,
							(int forStart$index$p, int forEnd$index$p, int threadID$index$p, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int index$p = forStart$index$p; index$p < forEnd$index$p; index$p += 1) {
										int p = index$p;
										int threadID$p = threadID$index$p;
										parallelFor(RNG$2, 0, state.samples, 1,
											(int forStart$n, int forEnd$n, int threadID$n, org.sandwood.random.internal.Rng RNG$3) -> { 
												for(int n = forStart$n; n < forEnd$n; n += 1)
														var179[n][p] = DistributionSampling.sampleBernoulli(RNG$3, state.bias[state.st[p][l][n]]);
											}
										);
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample28)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 2, state.m[var27]);
				}
			);

		if(!state.fixedFlag$sample45)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!state.fixedFlag$sample84)
			state.st[0][0][0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		if(!state.fixedFlag$sample124)
			parallelFor(state.RNG$, 1, state.samples, 1,
				(int forStart$i1, int forEnd$i1, int threadID$i1, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i1 = forStart$i1; i1 < forEnd$i1; i1 += 1) {
							int[][] var116 = state.st[i1];
							parallelFor(RNG$1, 0, state.samples, 1,
								(int forStart$index$j1, int forEnd$index$j1, int threadID$index$j1, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int index$j1 = forStart$index$j1; index$j1 < forEnd$index$j1; index$j1 += 1) {
											int j1 = index$j1;
											int threadID$j1 = threadID$index$j1;
											parallelFor(RNG$2, 0, state.samples, 1,
												(int forStart$k1, int forEnd$k1, int threadID$k1, org.sandwood.random.internal.Rng RNG$3) -> { 
													for(int k1 = forStart$k1; k1 < forEnd$k1; k1 += 1)
															var116[j1][k1] = DistributionSampling.sampleCategorical(RNG$3, state.m[0], 2);
												}
											);
										}
								}
							);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample28)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 2, state.m[var27]);
				}
			);

		if(!state.fixedFlag$sample45)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!state.fixedFlag$sample84)
			state.st[0][0][0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		if(!state.fixedFlag$sample124)
			parallelFor(state.RNG$, 1, state.samples, 1,
				(int forStart$i1, int forEnd$i1, int threadID$i1, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i1 = forStart$i1; i1 < forEnd$i1; i1 += 1) {
							int[][] var116 = state.st[i1];
							parallelFor(RNG$1, 0, state.samples, 1,
								(int forStart$index$j1, int forEnd$index$j1, int threadID$index$j1, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int index$j1 = forStart$index$j1; index$j1 < forEnd$index$j1; index$j1 += 1) {
											int j1 = index$j1;
											int threadID$j1 = threadID$index$j1;
											parallelFor(RNG$2, 0, state.samples, 1,
												(int forStart$k1, int forEnd$k1, int threadID$k1, org.sandwood.random.internal.Rng RNG$3) -> { 
													for(int k1 = forStart$k1; k1 < forEnd$k1; k1 += 1)
															var116[j1][k1] = DistributionSampling.sampleCategorical(RNG$3, state.m[0], 2);
												}
											);
										}
								}
							);
						}
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample28)
				parallelFor(state.RNG$, 0, 2, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								inferSample28(var27, threadID$var27, RNG$1);
					}
				);

			if(!state.fixedFlag$sample45)
				parallelFor(state.RNG$, 0, 2, 1,
					(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
								inferSample45(var43, threadID$var43, RNG$1);
					}
				);

			if(!state.fixedFlag$sample84)
				inferSample84();
			if(!state.fixedFlag$sample124)
				parallelFor(state.RNG$, 1, state.samples, 1,
					(int forStart$index$i1, int forEnd$index$i1, int threadID$index$i1, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int index$i1 = forStart$index$i1; index$i1 < forEnd$index$i1; index$i1 += 1) {
								int i1 = index$i1;
								int threadID$i1 = threadID$index$i1;
								parallelFor(RNG$1, 0, state.samples, 1,
									(int forStart$index$j1, int forEnd$index$j1, int threadID$index$j1, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int index$j1 = forStart$index$j1; index$j1 < forEnd$index$j1; index$j1 += 1) {
												int j1 = index$j1;
												int threadID$j1 = threadID$index$j1;
												parallelFor(RNG$2, 0, state.samples, 1,
													(int forStart$k1, int forEnd$k1, int threadID$k1, org.sandwood.random.internal.Rng RNG$3) -> { 
														for(int k1 = forStart$k1; k1 < forEnd$k1; k1 += 1)
																inferSample124(i1, j1, k1, threadID$k1, RNG$3);
													}
												);
											}
									}
								);
							}
					}
				);

		} else {
			if(!state.fixedFlag$sample124)
				parallelFor(state.RNG$, 1, state.samples, 1,
					(int forStart$index$i1, int forEnd$index$i1, int threadID$index$i1, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int index$i1 = forStart$index$i1; index$i1 < forEnd$index$i1; index$i1 += 1) {
								int i1 = index$i1;
								int threadID$i1 = threadID$index$i1;
								parallelFor(RNG$1, 0, state.samples, 1,
									(int forStart$index$j1, int forEnd$index$j1, int threadID$index$j1, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int index$j1 = forStart$index$j1; index$j1 < forEnd$index$j1; index$j1 += 1) {
												int j1 = index$j1;
												int threadID$j1 = threadID$index$j1;
												parallelFor(RNG$2, 0, state.samples, 1,
													(int forStart$k1, int forEnd$k1, int threadID$k1, org.sandwood.random.internal.Rng RNG$3) -> { 
														for(int k1 = forStart$k1; k1 < forEnd$k1; k1 += 1)
																inferSample124(i1, j1, k1, threadID$k1, RNG$3);
													}
												);
											}
									}
								);
							}
					}
				);

			if(!state.fixedFlag$sample84)
				inferSample84();
			if(!state.fixedFlag$sample45)
				parallelFor(state.RNG$, 0, 2, 1,
					(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
								inferSample45(var43, threadID$var43, RNG$1);
					}
				);

			if(!state.fixedFlag$sample28)
				parallelFor(state.RNG$, 0, 2, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								inferSample28(var27, threadID$var27, RNG$1);
					}
				);

		}
		state.system$gibbsForward = !state.system$gibbsForward;
		parallelFor(state.RNG$, 0, 2, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						if(!state.constrainedFlag$sample28[var27])
							drawValueSample28(var27, threadID$var27, RNG$1);
					}
			}
		);
		parallelFor(state.RNG$, 0, 2, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!state.constrainedFlag$sample45[var43])
							drawValueSample45(var43, threadID$var43, RNG$1);
					}
			}
		);
		if(!state.constrainedFlag$sample84)
			drawValueSample84();
		parallelFor(state.RNG$, 1, state.samples, 1,
			(int forStart$index$i1, int forEnd$index$i1, int threadID$index$i1, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i1 = forStart$index$i1; index$i1 < forEnd$index$i1; index$i1 += 1) {
						int i1 = index$i1;
						int threadID$i1 = threadID$index$i1;
						parallelFor(RNG$1, 0, state.samples, 1,
							(int forStart$index$j1, int forEnd$index$j1, int threadID$index$j1, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int index$j1 = forStart$index$j1; index$j1 < forEnd$index$j1; index$j1 += 1) {
										int j1 = index$j1;
										int threadID$j1 = threadID$index$j1;
										parallelFor(RNG$2, 0, state.samples, 1,
											(int forStart$k1, int forEnd$k1, int threadID$k1, org.sandwood.random.internal.Rng RNG$3) -> { 
												for(int k1 = forStart$k1; k1 < forEnd$k1; k1 += 1) {
														if(!state.constrainedFlag$sample124[(i1 - 1)][j1][k1])
															drawValueSample124(i1, j1, k1, threadID$k1, RNG$3);
													}
											}
										);
									}
							}
						);
					}
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$m = 0.0;
		if(!state.fixedProbFlag$sample28)
			state.logProbability$var28 = Double.NaN;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample45)
			state.logProbability$var44 = Double.NaN;
		state.logProbability$st = 0.0;
		if(!state.fixedProbFlag$sample84)
			state.logProbability$var81 = Double.NaN;
		if(!state.fixedProbFlag$sample124) {
			for(int i1 = 1; i1 < state.samples; i1 += 1) {
				for(int j1 = 0; j1 < state.samples; j1 += 1) {
					for(int k1 = 0; k1 < state.samples; k1 += 1)
						state.logProbability$sample124[(i1 - 1)][j1][k1] = Double.NaN;
				}
			}
		}
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample191) {
			for(int l = 0; l < state.samples; l += 1) {
				for(int p = 0; p < state.samples; p += 1) {
					for(int n = 0; n < state.samples; n += 1)
						state.logProbability$sample191[l][p][n] = Double.NaN;
				}
			}
		}
	}

	@Override
	public final void initializeModel() {
		state.v[0] = 0.1;
		state.v[1] = 0.1;
		state.samples = state.length$flipsMeasured.length;
		for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < state.constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
			state.constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
		for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < state.constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
			state.constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
		for(int index$constrainedFlag$sample124$1 = 0; index$constrainedFlag$sample124$1 < state.constrainedFlag$sample124.length; index$constrainedFlag$sample124$1 += 1) {
			boolean[][] cv$constrainedFlag$sample124$1 = state.constrainedFlag$sample124[index$constrainedFlag$sample124$1];
			for(int index$constrainedFlag$sample124$2 = 0; index$constrainedFlag$sample124$2 < cv$constrainedFlag$sample124$1.length; index$constrainedFlag$sample124$2 += 1) {
				boolean[] cv$constrainedFlag$sample124$2 = cv$constrainedFlag$sample124$1[index$constrainedFlag$sample124$2];
				for(int index$constrainedFlag$sample124$3 = 0; index$constrainedFlag$sample124$3 < cv$constrainedFlag$sample124$2.length; index$constrainedFlag$sample124$3 += 1)
					cv$constrainedFlag$sample124$2[index$constrainedFlag$sample124$3] = true;
			}
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(state.fixedFlag$sample45)
			logProbabilityValue$sample45();
		if(state.fixedFlag$sample84)
			logProbabilityValue$sample84();
		if(state.fixedFlag$sample124)
			logProbabilityValue$sample124();
		logProbabilityValue$sample191();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample84();
		logProbabilityValue$sample124();
		logProbabilityValue$sample191();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample84();
		logProbabilityValue$sample124();
		logProbabilityValue$sample191();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[][] cv$source2 = state.flipsMeasured[cv$index1];
			boolean[][] cv$target2 = state.flips[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1) {
				boolean[] cv$source3 = cv$source2[cv$index2];
				boolean[] cv$target3 = cv$target2[cv$index2];
				int cv$length3 = cv$target3.length;
				for(int cv$index3 = 0; cv$index3 < cv$length3; cv$index3 += 1)
					cv$target3[cv$index3] = cv$source3[cv$index3];
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
		     + "model HMMTestPart4(boolean[][][] flipsMeasured) {\n"
		     + "        int states = 2;\n"
		     + "\n"
		     + "        double[] v = new double[states];\n"
		     + "        for(int i:[0..states))\n"
		     + "            v[i] = 0.1;\n"
		     + "        \n"
		     + "        double[][] m = dirichlet(v).sample(states);\n"
		     + "        double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "        int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "        \n"
		     + "        int[][][] st = new int[samples][][];\n"
		     + "        for(int i:[0..samples)) {\n"
		     + "            st[i] = new int[samples][];\n"
		     + "            for(int j:[0..samples))\n"
		     + "                st[i][j] = new int[samples];\n"
		     + "        }\n"
		     + "\n"
		     + "        st[0][0][0] = categorical(m[0]).sample();\n"
		     + "\n"
		     + "        for(int i1:[1..samples))\n"
		     + "            for(int j1:[0..samples))\n"
		     + "                for(int k1:[0..samples))\n"
		     + "                    st[i1][j1][k1] = categorical(m[0]).sample();\n"
		     + "            \n"
		     + "        boolean[][][] flips = new boolean[samples][][];\n"
		     + "        for(int i2:[0..samples)) {\n"
		     + "            flips[i2] = new boolean[samples][];\n"
		     + "            for(int j2:[0..samples))\n"
		     + "                flips[i2][j2] = new boolean[samples];\n"
		     + "        }\n"
		     + "            \n"
		     + "        for(int l:[0..samples))\n"
		     + "            for(int p:[0..samples))\n"
		     + "                for(int n:[0..samples))\n"
		     + "                    flips[l][n][p] = bernoulli(bias[st[p][l][n]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}