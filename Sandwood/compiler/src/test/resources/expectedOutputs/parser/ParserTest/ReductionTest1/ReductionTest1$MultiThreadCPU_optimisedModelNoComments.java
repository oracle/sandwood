package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.ReductionTest1$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.ReductionTest1.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class ReductionTest1$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public ReductionTest1$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample101(int i$var80, int var95, int threadID$cv$i$var80, Rng RNG$) {
		state.time_coeff[i$var80][var95] = DistributionSampling.sampleGaussian(RNG$);
		for(int t = 1; t < state.T; t += 1)
			state.time_impact[t][i$var80][var95] = (state.TimeFeat[t][var95] * state.time_coeff[i$var80][var95]);
		for(int t = 1; t < state.T; t += 1) {
			double reduceVar$var151$13 = 0.0;
			for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
				reduceVar$var151$13 = (reduceVar$var151$13 + state.time_impact[t][i$var80][cv$reduction152Index]);
			state.sum_t[t][i$var80] = reduceVar$var151$13;
		}
	}

	private final void inferSample101(int i$var80, int var95, int threadID$cv$i$var80, Rng RNG$) {
		state.constrainedFlag$sample101[i$var80][var95] = false;
		double cv$originalValue = state.time_coeff[i$var80][var95];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$originalValue);
			for(int t = 1; t < state.T; t += 1) {
				double reduceVar$var151$11 = 0.0;
				for(int cv$reduction744Index = 0; cv$reduction744Index < var95; cv$reduction744Index += 1)
					reduceVar$var151$11 = (reduceVar$var151$11 + state.time_impact[t][i$var80][cv$reduction744Index]);
				for(int cv$reduction744Index = (var95 + 1); cv$reduction744Index < state.time_dim; cv$reduction744Index += 1)
					reduceVar$var151$11 = (reduceVar$var151$11 + state.time_impact[t][i$var80][cv$reduction744Index]);
				reduceVar$var151$11 = ((state.TimeFeat[t][var95] * cv$originalValue) + reduceVar$var151$11);
				state.constrainedFlag$sample101[i$var80][var95] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityPoisson(state.arr[t][i$var80], reduceVar$var151$11) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample101[i$var80][var95]) {
			state.time_coeff[i$var80][var95] = cv$proposedValue;
			for(int t = 1; t < state.T; t += 1)
				state.time_impact[t][i$var80][var95] = (state.TimeFeat[t][var95] * state.time_coeff[i$var80][var95]);
			for(int t = 1; t < state.T; t += 1) {
				double reduceVar$var151$10 = 0.0;
				for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
					reduceVar$var151$10 = (reduceVar$var151$10 + state.time_impact[t][i$var80][cv$reduction152Index]);
				state.sum_t[t][i$var80] = reduceVar$var151$10;
			}
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$proposedValue);
			for(int t = 1; t < state.T; t += 1) {
				double reduceVar$var151$11 = 0.0;
				for(int cv$reduction744Index = 0; cv$reduction744Index < var95; cv$reduction744Index += 1)
					reduceVar$var151$11 = (reduceVar$var151$11 + state.time_impact[t][i$var80][cv$reduction744Index]);
				for(int cv$reduction744Index = (var95 + 1); cv$reduction744Index < state.time_dim; cv$reduction744Index += 1)
					reduceVar$var151$11 = (reduceVar$var151$11 + state.time_impact[t][i$var80][cv$reduction744Index]);
				reduceVar$var151$11 = ((state.TimeFeat[t][var95] * cv$proposedValue) + reduceVar$var151$11);
				state.constrainedFlag$sample101[i$var80][var95] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityPoisson(state.arr[t][i$var80], reduceVar$var151$11) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio))) {
				state.time_coeff[i$var80][var95] = cv$originalValue;
				for(int t = 1; t < state.T; t += 1)
					state.time_impact[t][i$var80][var95] = (state.TimeFeat[t][var95] * state.time_coeff[i$var80][var95]);
				for(int t = 1; t < state.T; t += 1) {
					double reduceVar$var151$12 = 0.0;
					for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
						reduceVar$var151$12 = (reduceVar$var151$12 + state.time_impact[t][i$var80][cv$reduction152Index]);
					state.sum_t[t][i$var80] = reduceVar$var151$12;
				}
			}
		}
	}

	private final void logProbabilityValue$sample101() {
		if(!state.fixedProbFlag$sample101) {
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var95 = 0; var95 < state.time_dim; var95 += 1) {
					double cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(state.time_coeff[i$var80][var95]);
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
					state.logProbability$sample101[i$var80][var95] = cv$distributionAccumulator;
					if((1 < state.T)) {
						state.logProbability$time_impact = (state.logProbability$time_impact + cv$distributionAccumulator);
						state.logProbability$sum_t = (state.logProbability$sum_t + cv$distributionAccumulator);
					}
				}
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			}
			state.logProbability$time_coeff = (state.logProbability$time_coeff + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample101)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample101 = state.fixedFlag$sample101;
		} else {
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
				double cv$rvAccumulator = 0.0;
				for(int var95 = 0; var95 < state.time_dim; var95 += 1) {
					double cv$sampleValue = state.logProbability$sample101[i$var80][var95];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					if((1 < state.T)) {
						state.logProbability$time_impact = (state.logProbability$time_impact + cv$sampleValue);
						state.logProbability$sum_t = (state.logProbability$sum_t + cv$sampleValue);
					}
				}
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$time_coeff = (state.logProbability$time_coeff + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample101)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample165() {
		if(!state.fixedProbFlag$sample165) {
			double cv$accumulator = 0.0;
			for(int t = 1; t < state.T; t += 1) {
				for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
					double cv$distributionAccumulator = DistributionSampling.logProbabilityPoisson(state.arr[t][i$var119], state.sum_t[t][i$var119]);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					state.logProbability$sample165[(t - 1)][i$var119] = cv$distributionAccumulator;
				}
			}
			state.logProbability$arr = (state.logProbability$arr + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample165 = state.fixedFlag$sample101;
		} else {
			double cv$accumulator = 0.0;
			for(int t = 1; t < state.T; t += 1) {
				for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1)
					cv$accumulator = (cv$accumulator + state.logProbability$sample165[(t - 1)][i$var119]);
			}
			state.logProbability$arr = (state.logProbability$arr + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample101)
			parallelFor(state.RNG$, 0, state.n_ac, 1,
				(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
							double[] var86 = state.time_coeff[i$var80];
							parallelFor(RNG$1, 0, state.time_dim, 1,
								(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
											var86[var95] = DistributionSampling.sampleGaussian(RNG$2);
								}
							);
						}
				}
			);

		parallelFor(state.RNG$, 1, state.T, 1,
			(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
						int t = index$t;
						int threadID$t = threadID$index$t;
						double[][] var129 = state.time_impact[t];
						double[] var139 = state.sum_t[t];
						int[] var154 = state.arr[t];
						parallelFor(RNG$1, 0, state.n_ac, 1,
							(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
										int i$var119 = index$i$var119;
										int threadID$i$var119 = threadID$index$i$var119;
										if(!state.fixedFlag$sample101) {
											parallelFor(RNG$2, 0, state.time_dim, 1,
												(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
													for(int j = forStart$j; j < forEnd$j; j += 1)
															var129[i$var119][j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
												}
											);
											double reduceVar$var151$14 = 0.0;
											for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
												reduceVar$var151$14 = (reduceVar$var151$14 + state.time_impact[t][i$var119][cv$reduction152Index]);
											var139[i$var119] = reduceVar$var151$14;
										}
										var154[i$var119] = DistributionSampling.samplePoisson(RNG$2, state.sum_t[t][i$var119]);
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample101)
			parallelFor(state.RNG$, 0, state.n_ac, 1,
				(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
							double[] var86 = state.time_coeff[i$var80];
							parallelFor(RNG$1, 0, state.time_dim, 1,
								(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
											var86[var95] = DistributionSampling.sampleGaussian(RNG$2);
								}
							);
						}
				}
			);

		parallelFor(state.RNG$, 1, state.T, 1,
			(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
						int t = index$t;
						int threadID$t = threadID$index$t;
						double[][] var129 = state.time_impact[t];
						double[] var139 = state.sum_t[t];
						parallelFor(RNG$1, 0, state.n_ac, 1,
							(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
										int i$var119 = index$i$var119;
										int threadID$i$var119 = threadID$index$i$var119;
										parallelFor(RNG$2, 0, state.time_dim, 1,
											(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
												for(int j = forStart$j; j < forEnd$j; j += 1)
														var129[i$var119][j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
											}
										);
										double reduceVar$var151$18 = 0.0;
										for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
											reduceVar$var151$18 = (reduceVar$var151$18 + state.time_impact[t][i$var119][cv$reduction152Index]);
										var139[i$var119] = reduceVar$var151$18;
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample101)
			parallelFor(state.RNG$, 0, state.n_ac, 1,
				(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
							double[] var86 = state.time_coeff[i$var80];
							parallelFor(RNG$1, 0, state.time_dim, 1,
								(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
											var86[var95] = DistributionSampling.sampleGaussian(RNG$2);
								}
							);
						}
				}
			);

		parallelFor(state.RNG$, 1, state.T, 1,
			(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
						int t = index$t;
						int threadID$t = threadID$index$t;
						double[][] var129 = state.time_impact[t];
						double[] var139 = state.sum_t[t];
						int[] var154 = state.arr[t];
						parallelFor(RNG$1, 0, state.n_ac, 1,
							(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
										int i$var119 = index$i$var119;
										int threadID$i$var119 = threadID$index$i$var119;
										parallelFor(RNG$2, 0, state.time_dim, 1,
											(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
												for(int j = forStart$j; j < forEnd$j; j += 1)
														var129[i$var119][j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
											}
										);
										double reduceVar$var151$15 = 0.0;
										for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
											reduceVar$var151$15 = (reduceVar$var151$15 + state.time_impact[t][i$var119][cv$reduction152Index]);
										var139[i$var119] = reduceVar$var151$15;
										var154[i$var119] = DistributionSampling.samplePoisson(RNG$2, state.sum_t[t][i$var119]);
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample101) {
			parallelFor(state.RNG$, 0, state.n_ac, 1,
				(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
							double[] var86 = state.time_coeff[i$var80];
							parallelFor(RNG$1, 0, state.time_dim, 1,
								(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
											var86[var95] = DistributionSampling.sampleGaussian(RNG$2);
								}
							);
						}
				}
			);
			parallelFor(state.RNG$, 1, state.T, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							double[][] var129 = state.time_impact[t];
							double[] var139 = state.sum_t[t];
							parallelFor(RNG$1, 0, state.n_ac, 1,
								(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
											int i$var119 = index$i$var119;
											int threadID$i$var119 = threadID$index$i$var119;
											parallelFor(RNG$2, 0, state.time_dim, 1,
												(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
													for(int j = forStart$j; j < forEnd$j; j += 1)
															var129[i$var119][j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
												}
											);
											double reduceVar$var151$16 = 0.0;
											for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
												reduceVar$var151$16 = (reduceVar$var151$16 + state.time_impact[t][i$var119][cv$reduction152Index]);
											var139[i$var119] = reduceVar$var151$16;
										}
								}
							);
						}
				}
			);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample101)
			parallelFor(state.RNG$, 0, state.n_ac, 1,
				(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
							double[] var86 = state.time_coeff[i$var80];
							parallelFor(RNG$1, 0, state.time_dim, 1,
								(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
											var86[var95] = DistributionSampling.sampleGaussian(RNG$2);
								}
							);
						}
				}
			);

		parallelFor(state.RNG$, 1, state.T, 1,
			(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
						int t = index$t;
						int threadID$t = threadID$index$t;
						double[][] var129 = state.time_impact[t];
						double[] var139 = state.sum_t[t];
						parallelFor(RNG$1, 0, state.n_ac, 1,
							(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
										int i$var119 = index$i$var119;
										int threadID$i$var119 = threadID$index$i$var119;
										parallelFor(RNG$2, 0, state.time_dim, 1,
											(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
												for(int j = forStart$j; j < forEnd$j; j += 1)
														var129[i$var119][j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
											}
										);
										double reduceVar$var151$17 = 0.0;
										for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
											reduceVar$var151$17 = (reduceVar$var151$17 + state.time_impact[t][i$var119][cv$reduction152Index]);
										var139[i$var119] = reduceVar$var151$17;
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample101) {
			if(state.system$gibbsForward)
				parallelFor(state.RNG$, 0, state.n_ac, 1,
					(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
								for(int var95 = 0; var95 < state.time_dim; var95 += 1)
									inferSample101(i$var80, var95, threadID$i$var80, RNG$1);
							}
					}
				);
			else
				parallelFor(state.RNG$, 0, state.n_ac, 1,
					(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
								for(int var95 = (state.time_dim - 1); var95 >= 0; var95 -= 1)
									inferSample101(i$var80, var95, threadID$i$var80, RNG$1);
							}
					}
				);
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		parallelFor(state.RNG$, 0, state.n_ac, 1,
			(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
						for(int var95 = 0; var95 < state.time_dim; var95 += 1) {
							if(!state.constrainedFlag$sample101[i$var80][var95])
								drawValueSample101(i$var80, var95, threadID$i$var80, RNG$1);
						}
					}
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$time_coeff = 0.0;
		state.logProbability$time_impact = 0.0;
		state.logProbability$sum_t = 0.0;
		if(!state.fixedProbFlag$sample101) {
			for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
				for(int var95 = 0; var95 < state.time_dim; var95 += 1)
					state.logProbability$sample101[i$var80][var95] = Double.NaN;
			}
		}
		state.logProbability$arr = 0.0;
		if(!state.fixedProbFlag$sample165) {
			for(int t = 1; t < state.T; t += 1) {
				for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1)
					state.logProbability$sample165[(t - 1)][i$var119] = Double.NaN;
			}
		}
	}

	@Override
	public final void initializeModel() {
		state.time_dim = state.TimeFeat[0].length;
		for(int index$constrainedFlag$sample101$1 = 0; index$constrainedFlag$sample101$1 < state.constrainedFlag$sample101.length; index$constrainedFlag$sample101$1 += 1) {
			boolean[] cv$constrainedFlag$sample101$1 = state.constrainedFlag$sample101[index$constrainedFlag$sample101$1];
			for(int index$constrainedFlag$sample101$2 = 0; index$constrainedFlag$sample101$2 < cv$constrainedFlag$sample101$1.length; index$constrainedFlag$sample101$2 += 1)
				cv$constrainedFlag$sample101$1[index$constrainedFlag$sample101$2] = true;
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample101)
			logProbabilityValue$sample101();
		logProbabilityValue$sample165();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample101();
		logProbabilityValue$sample165();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample101();
		logProbabilityValue$sample165();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.arr.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = state.ObsArr[cv$index1];
			int[] cv$target2 = state.arr[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	@Override
	public final void setIntermediates() {
		parallelFor(state.RNG$, 1, state.T, 1,
			(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
						int t = index$t;
						int threadID$t = threadID$index$t;
						double[][] var129 = state.time_impact[t];
						double[] var139 = state.sum_t[t];
						parallelFor(RNG$1, 0, state.n_ac, 1,
							(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
										int i$var119 = index$i$var119;
										int threadID$i$var119 = threadID$index$i$var119;
										parallelFor(RNG$2, 0, state.time_dim, 1,
											(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
												for(int j = forStart$j; j < forEnd$j; j += 1)
														var129[i$var119][j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
											}
										);
										double reduceVar$var151$19 = 0.0;
										for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
											reduceVar$var151$19 = (reduceVar$var151$19 + state.time_impact[t][i$var119][cv$reduction152Index]);
										var139[i$var119] = reduceVar$var151$19;
									}
							}
						);
					}
			}
		);
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
		     + " \n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model ReductionTest1(int T, int n_ac, int[][] ObsArr, double[][] TimeFeat) {\n"
		     + "    int time_dim = TimeFeat[0].length; //length of the feature vector\n"
		     + "\n"
		     + "\n"
		     + "    double[][] time_coeff = new double[n_ac][time_dim];\n"
		     + "    double[][] sum_t = new double[T][n_ac];\n"
		     + "    double[][][] time_impact = new double[T][n_ac][time_dim];\n"
		     + "    int[][] arr = new int[T][n_ac];\n"
		     + "    \n"
		     + "    for (int i : [0..n_ac))\n"
		     + "        time_coeff[i] = gaussian(0,1).sample(time_dim);\n"
		     + "\n"
		     + "    for (int t : (0..T)) {\n"
		     + "        for (int i : [0..n_ac)){\n"
		     + "            for (int j : [0..time_dim))\n"
		     + "                time_impact[t][i][j] = TimeFeat[t][j]*time_coeff[i][j];\n"
		     + "            //calculate sum\n"
		     + "            sum_t[t][i] = reduce(time_impact[t][i], 0, (x, y) -> { return x + y; });\n"
		     + "            arr[t][i] = poisson(sum_t[t][i]).sample();\n"
		     + "        }\n"
		     + "    }\n"
		     + "    arr.observe(ObsArr);\n"
		     + "}";
	}
}