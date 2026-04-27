package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.LinearRegressionTest$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.LinearRegressionTest.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class LinearRegressionTest$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public LinearRegressionTest$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample24(int var23) {
		state.weights[var23] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		{
			{
				for(int j$var55 = 0; j$var55 < state.k; j$var55 += 1) {
					if((var23 == j$var55)) {
						for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1)
							state.phi[((i$var45 - 0) / 1)][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
					}
				}
			}
		}
	}

	private final void drawValueSample31() {
		state.bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
	}

	private final void drawValueSample35() {
		state.tau = DistributionSampling.sampleInverseGamma(state.RNG$, 3.0, 1.0);
	}

	private final void inferSample24(int var23) {
		if(true) {
			state.constrainedFlag$sample24[((var23 - 0) / 1)] = false;
			double cv$sum = 0.0;
			double cv$denominatorSquareSum = 0.0;
			boolean cv$sigmaNotFound = true;
			double cv$sigmaValue = 1.0;
			{
				{
					{
						{
							for(int j$var55 = 0; j$var55 < state.k; j$var55 += 1) {
								if((var23 == j$var55)) {
									for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
										if(((0 <= j$var55) && (j$var55 < state.k))) {
											{
												{
													boolean cv$sampleConstrained = true;
													if(cv$sampleConstrained) {
														state.constrainedFlag$sample24[((var23 - 0) / 1)] = true;
														{
															{
																{
																	{
																		{
																			double cv$denominator = 1.0;
																			double cv$numerator = 0.0;
																			cv$numerator = (cv$numerator * state.x[i$var45][j$var55]);
																			cv$denominator = (cv$denominator * state.x[i$var45][j$var55]);
																			if((0 < state.k)) {
																				double reduceVar$var70$0 = 0.0;
																				for(int cv$reduction162Index = 0; cv$reduction162Index < j$var55; cv$reduction162Index += 1) {
																					double i$var67 = reduceVar$var70$0;
																					double j$var68 = state.phi[((i$var45 - 0) / 1)][cv$reduction162Index];
																					reduceVar$var70$0 = (i$var67 + j$var68);
																				}
																				for(int cv$reduction162Index = (j$var55 + 1); cv$reduction162Index < state.k; cv$reduction162Index += 1) {
																					double i$var67 = reduceVar$var70$0;
																					double j$var68 = state.phi[((i$var45 - 0) / 1)][cv$reduction162Index];
																					reduceVar$var70$0 = (i$var67 + j$var68);
																				}
																				double cv$reduced65 = reduceVar$var70$0;
																				cv$numerator = (cv$numerator + cv$reduced65);
																			}
																			cv$numerator = (cv$numerator + state.bias);
																			cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
																			cv$sum = (cv$sum + (cv$denominator * (state.y[i$var45] - cv$numerator)));
																			if(cv$sigmaNotFound) {
																				cv$sigmaValue = state.tau;
																				cv$sigmaNotFound = false;
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
				}
			}
			if(state.constrainedFlag$sample24[((var23 - 0) / 1)]) {
				double var24 = Conjugates.sampleConjugateGaussianGaussian(state.RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
				{
					{
						{
							state.weights[var23] = var24;
						}
					}
				}
				{
					{
						for(int j$var55 = 0; j$var55 < state.k; j$var55 += 1) {
							if((var23 == j$var55)) {
								for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1)
									state.phi[((i$var45 - 0) / 1)][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample31() {
		if(true) {
			state.constrainedFlag$sample31 = false;
			double cv$sum = 0.0;
			double cv$denominatorSquareSum = 0.0;
			boolean cv$sigmaNotFound = true;
			double cv$sigmaValue = 1.0;
			{
				{
					{
						{
							for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
								boolean cv$sampleConstrained = true;
								if(cv$sampleConstrained) {
									state.constrainedFlag$sample31 = true;
									{
										{
											{
												{
													{
														double cv$denominator = 1.0;
														double cv$numerator = 0.0;
														double reduceVar$var70$1 = 0.0;
														for(int cv$reduction65Index = 0; cv$reduction65Index < state.k; cv$reduction65Index += 1) {
															double i$var67 = reduceVar$var70$1;
															double j$var68 = state.phi[((i$var45 - 0) / 1)][cv$reduction65Index];
															reduceVar$var70$1 = (i$var67 + j$var68);
														}
														cv$numerator = (reduceVar$var70$1 + cv$numerator);
														cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
														cv$sum = (cv$sum + (cv$denominator * (state.y[i$var45] - cv$numerator)));
														if(cv$sigmaNotFound) {
															cv$sigmaValue = state.tau;
															cv$sigmaNotFound = false;
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
			if(state.constrainedFlag$sample31)
				state.bias = Conjugates.sampleConjugateGaussianGaussian(state.RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
		}
	}

	private final void inferSample35() {
		if(true) {
			state.constrainedFlag$sample35 = false;
			double cv$sum = 0.0;
			int cv$count = 0;
			{
				{
					{
						{
							for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
								boolean cv$sampleConstrained = true;
								if(cv$sampleConstrained) {
									state.constrainedFlag$sample35 = true;
									{
										{
											{
												{
													{
														double reduceVar$var70$2 = 0.0;
														for(int cv$reduction65Index = 0; cv$reduction65Index < state.k; cv$reduction65Index += 1) {
															double i$var67 = reduceVar$var70$2;
															double j$var68 = state.phi[((i$var45 - 0) / 1)][cv$reduction65Index];
															reduceVar$var70$2 = (i$var67 + j$var68);
														}
														double cv$var72$mu = (reduceVar$var70$2 + state.bias);
														double cv$var72$diff = (cv$var72$mu - state.y[i$var45]);
														cv$sum = (cv$sum + (cv$var72$diff * cv$var72$diff));
														cv$count = (cv$count + 1);
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
			if(state.constrainedFlag$sample35)
				state.tau = Conjugates.sampleConjugateInverseGammaGaussian(state.RNG$, 3.0, 1.0, cv$sum, cv$count);
		}
	}

	private final void logProbabilityValue$sample24() {
		if(!state.fixedProbFlag$sample24) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var23 = 0; var23 < state.k; var23 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.weights[var23];
						{
							{
								double var10 = 0.0;
								double var11 = 10.0;
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var11)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var10) / Math.sqrt(var11))) - (0.5 * Math.log(var11))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample24[((var23 - 0) / 1)] = cv$sampleProbability;
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			state.logProbability$weights = (state.logProbability$weights + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample24)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample24 = state.fixedFlag$sample24;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var23 = 0; var23 < state.k; var23 += 1) {
				double cv$sampleValue = state.logProbability$sample24[((var23 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$weights = (state.logProbability$weights + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample24)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!state.fixedProbFlag$sample31) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = state.bias;
					{
						{
							double var28 = 0.0;
							double var29 = 10.0;
							double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var29)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var28) / Math.sqrt(var29))) - (0.5 * Math.log(var29))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$bias = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample31)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample31 = state.fixedFlag$sample31;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$bias;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample31)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!state.fixedProbFlag$sample35) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = state.tau;
					{
						{
							double var32 = 3.0;
							double var33 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var32, var33));
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
			state.logProbability$tau = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample35)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample35 = state.fixedFlag$sample35;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$tau;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample35)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample74() {
		if(!state.fixedProbFlag$sample74) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.y[i$var45];
						{
							{
								double reduceVar$var70$3 = 0.0;
								for(int cv$reduction65Index = 0; cv$reduction65Index < state.k; cv$reduction65Index += 1) {
									double i$var67 = reduceVar$var70$3;
									double j$var68 = state.phi[((i$var45 - 0) / 1)][cv$reduction65Index];
									reduceVar$var70$3 = (i$var67 + j$var68);
								}
								double var71 = (reduceVar$var70$3 + state.bias);
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < state.tau)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var71) / Math.sqrt(state.tau))) - (0.5 * Math.log(state.tau))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample74[((i$var45 - 0) / 1)] = cv$sampleProbability;
			}
			state.logProbability$y = (state.logProbability$y + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample74 = ((state.fixedFlag$sample24 && state.fixedFlag$sample31) && state.fixedFlag$sample35);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample74[((i$var45 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$y = (state.logProbability$y + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		for(int var23 = 0; var23 < state.k; var23 += 1) {
			if(!state.fixedFlag$sample24)
				state.weights[var23] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		}
		if(!state.fixedFlag$sample31)
			state.bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample35)
			state.tau = DistributionSampling.sampleInverseGamma(state.RNG$, 3.0, 1.0);
		for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
			for(int j$var55 = 0; j$var55 < state.k; j$var55 += 1) {
				if(!state.fixedFlag$sample24)
					state.phi[((i$var45 - 0) / 1)][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
			}
			double reduceVar$var70$4 = 0.0;
			for(int cv$reduction65Index = 0; cv$reduction65Index < state.k; cv$reduction65Index += 1) {
				double i$var67 = reduceVar$var70$4;
				double j$var68 = state.phi[((i$var45 - 0) / 1)][cv$reduction65Index];
				reduceVar$var70$4 = (i$var67 + j$var68);
			}
			state.y[i$var45] = ((Math.sqrt(state.tau) * DistributionSampling.sampleGaussian(state.RNG$)) + (reduceVar$var70$4 + state.bias));
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int var23 = 0; var23 < state.k; var23 += 1) {
			if(!state.fixedFlag$sample24)
				state.weights[var23] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		}
		if(!state.fixedFlag$sample31)
			state.bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample35)
			state.tau = DistributionSampling.sampleInverseGamma(state.RNG$, 3.0, 1.0);
		for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
			for(int j$var55 = 0; j$var55 < state.k; j$var55 += 1)
				state.phi[((i$var45 - 0) / 1)][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		for(int var23 = 0; var23 < state.k; var23 += 1) {
			if(!state.fixedFlag$sample24)
				state.weights[var23] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		}
		if(!state.fixedFlag$sample31)
			state.bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample35)
			state.tau = DistributionSampling.sampleInverseGamma(state.RNG$, 3.0, 1.0);
		for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
			for(int j$var55 = 0; j$var55 < state.k; j$var55 += 1)
				state.phi[((i$var45 - 0) / 1)][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
			double reduceVar$var70$5 = 0.0;
			for(int cv$reduction65Index = 0; cv$reduction65Index < state.k; cv$reduction65Index += 1) {
				double i$var67 = reduceVar$var70$5;
				double j$var68 = state.phi[((i$var45 - 0) / 1)][cv$reduction65Index];
				reduceVar$var70$5 = (i$var67 + j$var68);
			}
			state.y[i$var45] = ((Math.sqrt(state.tau) * DistributionSampling.sampleGaussian(state.RNG$)) + (reduceVar$var70$5 + state.bias));
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var23 = 0; var23 < state.k; var23 += 1) {
			if(!state.fixedFlag$sample24)
				state.weights[var23] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		}
		if(!state.fixedFlag$sample31)
			state.bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample35)
			state.tau = DistributionSampling.sampleInverseGamma(state.RNG$, 3.0, 1.0);
		for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
			for(int j$var55 = 0; j$var55 < state.k; j$var55 += 1) {
				if(!state.fixedFlag$sample24)
					state.phi[((i$var45 - 0) / 1)][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		for(int var23 = 0; var23 < state.k; var23 += 1) {
			if(!state.fixedFlag$sample24)
				state.weights[var23] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		}
		if(!state.fixedFlag$sample31)
			state.bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(state.RNG$)) + 0.0);
		if(!state.fixedFlag$sample35)
			state.tau = DistributionSampling.sampleInverseGamma(state.RNG$, 3.0, 1.0);
		for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
			for(int j$var55 = 0; j$var55 < state.k; j$var55 += 1)
				state.phi[((i$var45 - 0) / 1)][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			for(int var23 = 0; var23 < state.k; var23 += 1) {
				if(!state.fixedFlag$sample24)
					inferSample24(var23);
			}
			if(!state.fixedFlag$sample31)
				inferSample31();
			if(!state.fixedFlag$sample35)
				inferSample35();
		} else {
			if(!state.fixedFlag$sample35)
				inferSample35();
			if(!state.fixedFlag$sample31)
				inferSample31();
			for(int var23 = (state.k - ((((state.k - 1) - 0) % 1) + 1)); var23 >= ((0 - 1) + 1); var23 -= 1) {
				if(!state.fixedFlag$sample24)
					inferSample24(var23);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int var23 = 0; var23 < state.k; var23 += 1) {
			if(!state.constrainedFlag$sample24[((var23 - 0) / 1)])
				drawValueSample24(var23);
		}
		if(!state.constrainedFlag$sample31)
			drawValueSample31();
		if(!state.constrainedFlag$sample35)
			drawValueSample35();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$weights = 0.0;
		if(!state.fixedProbFlag$sample24) {
			for(int var23 = 0; var23 < state.k; var23 += 1)
				state.logProbability$sample24[((var23 - 0) / 1)] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample31)
			state.logProbability$bias = Double.NaN;
		if(!state.fixedProbFlag$sample35)
			state.logProbability$tau = Double.NaN;
		state.logProbability$y = 0.0;
		if(!state.fixedProbFlag$sample74) {
			for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1)
				state.logProbability$sample74[((i$var45 - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.n = state.x.length;
		state.k = state.x[0].length;
		for(int index$constrainedFlag$sample24$1 = 0; index$constrainedFlag$sample24$1 < state.constrainedFlag$sample24.length; index$constrainedFlag$sample24$1 += 1)
			state.constrainedFlag$sample24[index$constrainedFlag$sample24$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample24)
			logProbabilityValue$sample24();
		if(state.fixedFlag$sample31)
			logProbabilityValue$sample31();
		if(state.fixedFlag$sample35)
			logProbabilityValue$sample35();
		logProbabilityValue$sample74();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample24();
		logProbabilityValue$sample31();
		logProbabilityValue$sample35();
		logProbabilityValue$sample74();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample24();
		logProbabilityValue$sample31();
		logProbabilityValue$sample35();
		logProbabilityValue$sample74();
	}

	@Override
	public final void propagateObservedValues() {
		double[] cv$source1 = state.yMeasured;
		double[] cv$target1 = state.y;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
			for(int j$var55 = 0; j$var55 < state.k; j$var55 += 1)
				state.phi[((i$var45 - 0) / 1)][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
		}
	}

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
		     + "model LinearRegressionTest(double[][] x, double[] yMeasured) {\n"
		     + "\n"
		     + "        int n = x.length;\n"
		     + "        int k = x[0].length;\n"
		     + "\n"
		     + "        double[] y = new double[n];\n"
		     + "\n"
		     + "        double[] weights = gaussian(0,10).sample(k);\n"
		     + "        double bias = gaussian(0,10).sample();\n"
		     + "        double tau = inverseGamma(3.0,1.0).sample();\n"
		     + "\n"
		     + "        for(int i:[0..n)) {\n"
		     + "            double[] phi = new double[k];\n"
		     + "            for(int j:[0..k,1))\n"
		     + "                phi[j] = weights[j] * x[i][j];\n"
		     + "            \n"
		     + "            y[i] = gaussian(sum(phi) + bias, tau).sample();\n"
		     + "        }\n"
		     + "        \n"
		     + "        y.observe(yMeasured);\n"
		     + "\n"
		     + "    private double sum(double[] a) {\n"
		     + "        return reduce(a, 0, (i,j) -> {\n"
		     + "            return i + j;\n"
		     + "        });\n"
		     + "    }\n"
		     + "}\n"
		     + "";
	}
}