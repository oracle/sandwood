package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class ReductionTest1$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements ReductionTest1$CoreInterface {
	private int[][] ObsArr;
	private int T;
	private double[][] TimeFeat;
	private int[][] arr;
	private boolean[][] constrainedFlag$sample101;
	private boolean fixedFlag$sample101 = false;
	private boolean fixedProbFlag$sample101 = false;
	private boolean fixedProbFlag$sample165 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$arr;
	private double[][] logProbability$sample101;
	private double[][] logProbability$sample165;
	private double logProbability$sum_t;
	private double logProbability$time_coeff;
	private double logProbability$time_impact;
	private int n_ac;
	private double[][] sum_t;
	private boolean system$gibbsForward = true;
	private double[][] time_coeff;
	private int time_dim;
	private double[][][] time_impact;

	public ReductionTest1$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final int[][] get$ObsArr() {
		return ObsArr;
	}

	@Override
	public final void set$ObsArr(int[][] cv$value, boolean allocated$) {
		ObsArr = cv$value;
	}

	@Override
	public final int get$T() {
		return T;
	}

	@Override
	public final void set$T(int cv$value, boolean allocated$) {
		T = cv$value;
	}

	@Override
	public final double[][] get$TimeFeat() {
		return TimeFeat;
	}

	@Override
	public final void set$TimeFeat(double[][] cv$value, boolean allocated$) {
		TimeFeat = cv$value;
	}

	@Override
	public final int[][] get$arr() {
		return arr;
	}

	@Override
	public final boolean get$fixedFlag$sample101() {
		return fixedFlag$sample101;
	}

	@Override
	public final void set$fixedFlag$sample101(boolean cv$value, boolean allocated$) {
		fixedFlag$sample101 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample101$1 = 0; index$constrainedFlag$sample101$1 < constrainedFlag$sample101.length; index$constrainedFlag$sample101$1 += 1) {
				boolean[] cv$constrainedFlag$sample101$1 = constrainedFlag$sample101[index$constrainedFlag$sample101$1];
				for(int index$constrainedFlag$sample101$2 = 0; index$constrainedFlag$sample101$2 < cv$constrainedFlag$sample101$1.length; index$constrainedFlag$sample101$2 += 1)
					cv$constrainedFlag$sample101$1[index$constrainedFlag$sample101$2] = true;
			}
		}
		fixedProbFlag$sample101 = (cv$value && fixedProbFlag$sample101);
		fixedProbFlag$sample165 = (cv$value && fixedProbFlag$sample165);
	}

	@Override
	public final double get$logProbability$$evidence() {
		return logProbability$$evidence;
	}

	@Override
	public final double getCurrentLogProbability() {
		return logProbability$$model;
	}

	@Override
	public final double get$logProbability$arr() {
		return logProbability$arr;
	}

	@Override
	public final double get$logProbability$sum_t() {
		return logProbability$sum_t;
	}

	@Override
	public final double get$logProbability$time_coeff() {
		return logProbability$time_coeff;
	}

	@Override
	public final double get$logProbability$time_impact() {
		return logProbability$time_impact;
	}

	@Override
	public final int get$n_ac() {
		return n_ac;
	}

	@Override
	public final void set$n_ac(int cv$value, boolean allocated$) {
		n_ac = cv$value;
	}

	@Override
	public final double[][] get$sum_t() {
		return sum_t;
	}

	@Override
	public final double[][] get$time_coeff() {
		return time_coeff;
	}

	@Override
	public final void set$time_coeff(double[][] cv$value, boolean allocated$) {
		time_coeff = cv$value;
		fixedProbFlag$sample101 = false;
		fixedProbFlag$sample165 = false;
	}

	@Override
	public final int get$time_dim() {
		return time_dim;
	}

	@Override
	public final double[][][] get$time_impact() {
		return time_impact;
	}

	private final void drawValueSample101(int i$var80, int var95, int threadID$cv$i$var80, Rng RNG$) {
		time_coeff[i$var80][var95] = DistributionSampling.sampleGaussian(RNG$);
		for(int t = 1; t < T; t += 1)
			time_impact[t][i$var80][var95] = (TimeFeat[t][var95] * time_coeff[i$var80][var95]);
		for(int t = 1; t < T; t += 1) {
			double reduceVar$var151$13 = 0.0;
			for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1)
				reduceVar$var151$13 = (reduceVar$var151$13 + time_impact[t][i$var80][cv$reduction152Index]);
			sum_t[t][i$var80] = reduceVar$var151$13;
		}
	}

	private final void inferSample101(int i$var80, int var95, int threadID$cv$i$var80, Rng RNG$) {
		constrainedFlag$sample101[i$var80][var95] = false;
		double cv$originalValue = time_coeff[i$var80][var95];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$originalValue);
			for(int t = 1; t < T; t += 1) {
				double reduceVar$var151$11 = 0.0;
				for(int cv$reduction744Index = 0; cv$reduction744Index < var95; cv$reduction744Index += 1)
					reduceVar$var151$11 = (reduceVar$var151$11 + time_impact[t][i$var80][cv$reduction744Index]);
				for(int cv$reduction744Index = (var95 + 1); cv$reduction744Index < time_dim; cv$reduction744Index += 1)
					reduceVar$var151$11 = (reduceVar$var151$11 + time_impact[t][i$var80][cv$reduction744Index]);
				reduceVar$var151$11 = ((TimeFeat[t][var95] * cv$originalValue) + reduceVar$var151$11);
				constrainedFlag$sample101[i$var80][var95] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityPoisson(arr[t][i$var80], reduceVar$var151$11) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample101[i$var80][var95]) {
			time_coeff[i$var80][var95] = cv$proposedValue;
			for(int t = 1; t < T; t += 1)
				time_impact[t][i$var80][var95] = (TimeFeat[t][var95] * time_coeff[i$var80][var95]);
			for(int t = 1; t < T; t += 1) {
				double reduceVar$var151$10 = 0.0;
				for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1)
					reduceVar$var151$10 = (reduceVar$var151$10 + time_impact[t][i$var80][cv$reduction152Index]);
				sum_t[t][i$var80] = reduceVar$var151$10;
			}
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$proposedValue);
			for(int t = 1; t < T; t += 1) {
				double reduceVar$var151$11 = 0.0;
				for(int cv$reduction744Index = 0; cv$reduction744Index < var95; cv$reduction744Index += 1)
					reduceVar$var151$11 = (reduceVar$var151$11 + time_impact[t][i$var80][cv$reduction744Index]);
				for(int cv$reduction744Index = (var95 + 1); cv$reduction744Index < time_dim; cv$reduction744Index += 1)
					reduceVar$var151$11 = (reduceVar$var151$11 + time_impact[t][i$var80][cv$reduction744Index]);
				reduceVar$var151$11 = ((TimeFeat[t][var95] * cv$proposedValue) + reduceVar$var151$11);
				constrainedFlag$sample101[i$var80][var95] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityPoisson(arr[t][i$var80], reduceVar$var151$11) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio))) {
				time_coeff[i$var80][var95] = cv$originalValue;
				for(int t = 1; t < T; t += 1)
					time_impact[t][i$var80][var95] = (TimeFeat[t][var95] * time_coeff[i$var80][var95]);
				for(int t = 1; t < T; t += 1) {
					double reduceVar$var151$12 = 0.0;
					for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1)
						reduceVar$var151$12 = (reduceVar$var151$12 + time_impact[t][i$var80][cv$reduction152Index]);
					sum_t[t][i$var80] = reduceVar$var151$12;
				}
			}
		}
	}

	private final void logProbabilityValue$sample101() {
		if(!fixedProbFlag$sample101) {
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var95 = 0; var95 < time_dim; var95 += 1) {
					double cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(time_coeff[i$var80][var95]);
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
					logProbability$sample101[i$var80][var95] = cv$distributionAccumulator;
					if((1 < T)) {
						logProbability$time_impact = (logProbability$time_impact + cv$distributionAccumulator);
						logProbability$sum_t = (logProbability$sum_t + cv$distributionAccumulator);
					}
				}
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			}
			logProbability$time_coeff = (logProbability$time_coeff + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample101)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample101 = fixedFlag$sample101;
		} else {
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1) {
				double cv$rvAccumulator = 0.0;
				for(int var95 = 0; var95 < time_dim; var95 += 1) {
					double cv$sampleValue = logProbability$sample101[i$var80][var95];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					if((1 < T)) {
						logProbability$time_impact = (logProbability$time_impact + cv$sampleValue);
						logProbability$sum_t = (logProbability$sum_t + cv$sampleValue);
					}
				}
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			logProbability$time_coeff = (logProbability$time_coeff + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample101)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample165() {
		if(!fixedProbFlag$sample165) {
			double cv$accumulator = 0.0;
			for(int t = 1; t < T; t += 1) {
				for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1) {
					double cv$distributionAccumulator = DistributionSampling.logProbabilityPoisson(arr[t][i$var119], sum_t[t][i$var119]);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$sample165[(t - 1)][i$var119] = cv$distributionAccumulator;
				}
			}
			logProbability$arr = (logProbability$arr + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample165 = fixedFlag$sample101;
		} else {
			double cv$accumulator = 0.0;
			for(int t = 1; t < T; t += 1) {
				for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1)
					cv$accumulator = (cv$accumulator + logProbability$sample165[(t - 1)][i$var119]);
			}
			logProbability$arr = (logProbability$arr + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!fixedFlag$sample101) {
			time_coeff = new double[n_ac][];
			for(int var18 = 0; var18 < n_ac; var18 += 1)
				time_coeff[var18] = new double[TimeFeat[0].length];
			for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1)
				time_coeff[i$var80] = new double[TimeFeat[0].length];
		}
		sum_t = new double[T][];
		for(int var31 = 0; var31 < T; var31 += 1)
			sum_t[var31] = new double[n_ac];
		time_impact = new double[T][][];
		for(int var44 = 0; var44 < T; var44 += 1) {
			double[][] subarray$0 = new double[n_ac][];
			time_impact[var44] = subarray$0;
			for(int var54 = 0; var54 < n_ac; var54 += 1)
				subarray$0[var54] = new double[TimeFeat[0].length];
		}
		arr = new int[T][];
		for(int var68 = 0; var68 < T; var68 += 1)
			arr[var68] = new int[n_ac];
		constrainedFlag$sample101 = new boolean[n_ac][];
		for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1)
			constrainedFlag$sample101[i$var80] = new boolean[TimeFeat[0].length];
		logProbability$sample101 = new double[n_ac][];
		for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1)
			logProbability$sample101[i$var80] = new double[TimeFeat[0].length];
		logProbability$sample165 = new double[(T - 1)][];
		for(int t = 1; t < T; t += 1)
			logProbability$sample165[(t - 1)] = new double[n_ac];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample101)
			parallelFor(RNG$, 0, n_ac, 1,
				(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
							double[] var86 = time_coeff[i$var80];
							parallelFor(RNG$1, 0, time_dim, 1,
								(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
											var86[var95] = DistributionSampling.sampleGaussian(RNG$2);
								}
							);
						}
				}
			);

		parallelFor(RNG$, 1, T, 1,
			(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
						int t = index$t;
						int threadID$t = threadID$index$t;
						double[][] var129 = time_impact[t];
						double[] var139 = sum_t[t];
						int[] var154 = arr[t];
						parallelFor(RNG$1, 0, n_ac, 1,
							(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
										int i$var119 = index$i$var119;
										int threadID$i$var119 = threadID$index$i$var119;
										if(!fixedFlag$sample101) {
											parallelFor(RNG$2, 0, time_dim, 1,
												(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
													for(int j = forStart$j; j < forEnd$j; j += 1)
															var129[i$var119][j] = (TimeFeat[t][j] * time_coeff[i$var119][j]);
												}
											);
											double reduceVar$var151$14 = 0.0;
											for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1)
												reduceVar$var151$14 = (reduceVar$var151$14 + time_impact[t][i$var119][cv$reduction152Index]);
											var139[i$var119] = reduceVar$var151$14;
										}
										var154[i$var119] = DistributionSampling.samplePoisson(RNG$2, sum_t[t][i$var119]);
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample101)
			parallelFor(RNG$, 0, n_ac, 1,
				(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
							double[] var86 = time_coeff[i$var80];
							parallelFor(RNG$1, 0, time_dim, 1,
								(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
											var86[var95] = DistributionSampling.sampleGaussian(RNG$2);
								}
							);
						}
				}
			);

		parallelFor(RNG$, 1, T, 1,
			(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
						int t = index$t;
						int threadID$t = threadID$index$t;
						double[][] var129 = time_impact[t];
						double[] var139 = sum_t[t];
						parallelFor(RNG$1, 0, n_ac, 1,
							(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
										int i$var119 = index$i$var119;
										int threadID$i$var119 = threadID$index$i$var119;
										parallelFor(RNG$2, 0, time_dim, 1,
											(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
												for(int j = forStart$j; j < forEnd$j; j += 1)
														var129[i$var119][j] = (TimeFeat[t][j] * time_coeff[i$var119][j]);
											}
										);
										double reduceVar$var151$18 = 0.0;
										for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1)
											reduceVar$var151$18 = (reduceVar$var151$18 + time_impact[t][i$var119][cv$reduction152Index]);
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
		if(!fixedFlag$sample101)
			parallelFor(RNG$, 0, n_ac, 1,
				(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
							double[] var86 = time_coeff[i$var80];
							parallelFor(RNG$1, 0, time_dim, 1,
								(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
											var86[var95] = DistributionSampling.sampleGaussian(RNG$2);
								}
							);
						}
				}
			);

		parallelFor(RNG$, 1, T, 1,
			(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
						int t = index$t;
						int threadID$t = threadID$index$t;
						double[][] var129 = time_impact[t];
						double[] var139 = sum_t[t];
						int[] var154 = arr[t];
						parallelFor(RNG$1, 0, n_ac, 1,
							(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
										int i$var119 = index$i$var119;
										int threadID$i$var119 = threadID$index$i$var119;
										parallelFor(RNG$2, 0, time_dim, 1,
											(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
												for(int j = forStart$j; j < forEnd$j; j += 1)
														var129[i$var119][j] = (TimeFeat[t][j] * time_coeff[i$var119][j]);
											}
										);
										double reduceVar$var151$15 = 0.0;
										for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1)
											reduceVar$var151$15 = (reduceVar$var151$15 + time_impact[t][i$var119][cv$reduction152Index]);
										var139[i$var119] = reduceVar$var151$15;
										var154[i$var119] = DistributionSampling.samplePoisson(RNG$2, sum_t[t][i$var119]);
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample101) {
			parallelFor(RNG$, 0, n_ac, 1,
				(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
							double[] var86 = time_coeff[i$var80];
							parallelFor(RNG$1, 0, time_dim, 1,
								(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
											var86[var95] = DistributionSampling.sampleGaussian(RNG$2);
								}
							);
						}
				}
			);
			parallelFor(RNG$, 1, T, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							double[][] var129 = time_impact[t];
							double[] var139 = sum_t[t];
							parallelFor(RNG$1, 0, n_ac, 1,
								(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
											int i$var119 = index$i$var119;
											int threadID$i$var119 = threadID$index$i$var119;
											parallelFor(RNG$2, 0, time_dim, 1,
												(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
													for(int j = forStart$j; j < forEnd$j; j += 1)
															var129[i$var119][j] = (TimeFeat[t][j] * time_coeff[i$var119][j]);
												}
											);
											double reduceVar$var151$16 = 0.0;
											for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1)
												reduceVar$var151$16 = (reduceVar$var151$16 + time_impact[t][i$var119][cv$reduction152Index]);
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
		if(!fixedFlag$sample101)
			parallelFor(RNG$, 0, n_ac, 1,
				(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
							double[] var86 = time_coeff[i$var80];
							parallelFor(RNG$1, 0, time_dim, 1,
								(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
											var86[var95] = DistributionSampling.sampleGaussian(RNG$2);
								}
							);
						}
				}
			);

		parallelFor(RNG$, 1, T, 1,
			(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
						int t = index$t;
						int threadID$t = threadID$index$t;
						double[][] var129 = time_impact[t];
						double[] var139 = sum_t[t];
						parallelFor(RNG$1, 0, n_ac, 1,
							(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
										int i$var119 = index$i$var119;
										int threadID$i$var119 = threadID$index$i$var119;
										parallelFor(RNG$2, 0, time_dim, 1,
											(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
												for(int j = forStart$j; j < forEnd$j; j += 1)
														var129[i$var119][j] = (TimeFeat[t][j] * time_coeff[i$var119][j]);
											}
										);
										double reduceVar$var151$17 = 0.0;
										for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1)
											reduceVar$var151$17 = (reduceVar$var151$17 + time_impact[t][i$var119][cv$reduction152Index]);
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
		if(!fixedFlag$sample101) {
			if(system$gibbsForward)
				parallelFor(RNG$, 0, n_ac, 1,
					(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
								for(int var95 = 0; var95 < time_dim; var95 += 1)
									inferSample101(i$var80, var95, threadID$i$var80, RNG$1);
							}
					}
				);
			else
				parallelFor(RNG$, 0, n_ac, 1,
					(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
								for(int var95 = (time_dim - 1); var95 >= 0; var95 -= 1)
									inferSample101(i$var80, var95, threadID$i$var80, RNG$1);
							}
					}
				);
		}
		system$gibbsForward = !system$gibbsForward;
		parallelFor(RNG$, 0, n_ac, 1,
			(int forStart$i$var80, int forEnd$i$var80, int threadID$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var80 = forStart$i$var80; i$var80 < forEnd$i$var80; i$var80 += 1) {
						for(int var95 = 0; var95 < time_dim; var95 += 1) {
							if(!constrainedFlag$sample101[i$var80][var95])
								drawValueSample101(i$var80, var95, threadID$i$var80, RNG$1);
						}
					}
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$time_coeff = 0.0;
		logProbability$time_impact = 0.0;
		logProbability$sum_t = 0.0;
		if(!fixedProbFlag$sample101) {
			for(int i$var80 = 0; i$var80 < n_ac; i$var80 += 1) {
				for(int var95 = 0; var95 < time_dim; var95 += 1)
					logProbability$sample101[i$var80][var95] = Double.NaN;
			}
		}
		logProbability$arr = 0.0;
		if(!fixedProbFlag$sample165) {
			for(int t = 1; t < T; t += 1) {
				for(int i$var119 = 0; i$var119 < n_ac; i$var119 += 1)
					logProbability$sample165[(t - 1)][i$var119] = Double.NaN;
			}
		}
	}

	@Override
	public final void initializeModel() {
		time_dim = TimeFeat[0].length;
		for(int index$constrainedFlag$sample101$1 = 0; index$constrainedFlag$sample101$1 < constrainedFlag$sample101.length; index$constrainedFlag$sample101$1 += 1) {
			boolean[] cv$constrainedFlag$sample101$1 = constrainedFlag$sample101[index$constrainedFlag$sample101$1];
			for(int index$constrainedFlag$sample101$2 = 0; index$constrainedFlag$sample101$2 < cv$constrainedFlag$sample101$1.length; index$constrainedFlag$sample101$2 += 1)
				cv$constrainedFlag$sample101$1[index$constrainedFlag$sample101$2] = true;
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample101)
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
		int cv$length1 = arr.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = ObsArr[cv$index1];
			int[] cv$target2 = arr[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	@Override
	public final void setIntermediates() {
		parallelFor(RNG$, 1, T, 1,
			(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
						int t = index$t;
						int threadID$t = threadID$index$t;
						double[][] var129 = time_impact[t];
						double[] var139 = sum_t[t];
						parallelFor(RNG$1, 0, n_ac, 1,
							(int forStart$index$i$var119, int forEnd$index$i$var119, int threadID$index$i$var119, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int index$i$var119 = forStart$index$i$var119; index$i$var119 < forEnd$index$i$var119; index$i$var119 += 1) {
										int i$var119 = index$i$var119;
										int threadID$i$var119 = threadID$index$i$var119;
										parallelFor(RNG$2, 0, time_dim, 1,
											(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$3) -> { 
												for(int j = forStart$j; j < forEnd$j; j += 1)
														var129[i$var119][j] = (TimeFeat[t][j] * time_coeff[i$var119][j]);
											}
										);
										double reduceVar$var151$19 = 0.0;
										for(int cv$reduction152Index = 0; cv$reduction152Index < time_dim; cv$reduction152Index += 1)
											reduceVar$var151$19 = (reduceVar$var151$19 + time_impact[t][i$var119][cv$reduction152Index]);
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