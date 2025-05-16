package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class GaussianMixtureTest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements GaussianMixtureTest$CoreInterface {
	private double[] alpha;
	private double[] cv$var17$countGlobal;
	private double[][] cv$var68$stateProbabilityGlobal;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample52 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample52 = false;
	private int k;
	private int length$xMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$mu;
	private double logProbability$phi;
	private double[] logProbability$sample68;
	private double[] logProbability$sample72;
	private double logProbability$sigma;
	private double logProbability$var16;
	private double logProbability$var22;
	private double logProbability$var34;
	private double logProbability$var40;
	private double logProbability$var52;
	private double[] logProbability$var67;
	private double[] logProbability$var71;
	private double logProbability$x;
	private double logProbability$z;
	private double[] mu;
	private double[] phi;
	private double[] sigma;
	private boolean system$gibbsForward = true;
	private double[] x;
	private double[] xMeasured;
	private int[] z;

	public GaussianMixtureTest$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$alpha() {
		return alpha;
	}

	@Override
	public final boolean get$fixedFlag$sample17() {
		return fixedFlag$sample17;
	}

	@Override
	public final void set$fixedFlag$sample17(boolean cv$value) {
		fixedFlag$sample17 = cv$value;
		fixedProbFlag$sample17 = (fixedFlag$sample17 && fixedProbFlag$sample17);
	}

	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		fixedFlag$sample34 = cv$value;
		fixedProbFlag$sample34 = (fixedFlag$sample34 && fixedProbFlag$sample34);
	}

	@Override
	public final boolean get$fixedFlag$sample52() {
		return fixedFlag$sample52;
	}

	@Override
	public final void set$fixedFlag$sample52(boolean cv$value) {
		fixedFlag$sample52 = cv$value;
		fixedProbFlag$sample52 = (fixedFlag$sample52 && fixedProbFlag$sample52);
	}

	@Override
	public final int get$k() {
		return k;
	}

	@Override
	public final int get$length$xMeasured() {
		return length$xMeasured;
	}

	@Override
	public final void set$length$xMeasured(int cv$value) {
		length$xMeasured = cv$value;
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
	public final double get$logProbability$mu() {
		return logProbability$mu;
	}

	@Override
	public final double get$logProbability$phi() {
		return logProbability$phi;
	}

	@Override
	public final double get$logProbability$sigma() {
		return logProbability$sigma;
	}

	@Override
	public final double get$logProbability$x() {
		return logProbability$x;
	}

	@Override
	public final double get$logProbability$z() {
		return logProbability$z;
	}

	@Override
	public final double[] get$mu() {
		return mu;
	}

	@Override
	public final void set$mu(double[] cv$value) {
		mu = cv$value;
		fixedProbFlag$sample34 = false;
	}

	@Override
	public final double[] get$phi() {
		return phi;
	}

	@Override
	public final void set$phi(double[] cv$value) {
		phi = cv$value;
		fixedProbFlag$sample17 = false;
	}

	@Override
	public final double[] get$sigma() {
		return sigma;
	}

	@Override
	public final void set$sigma(double[] cv$value) {
		sigma = cv$value;
		fixedProbFlag$sample52 = false;
	}

	@Override
	public final double[] get$x() {
		return x;
	}

	@Override
	public final double[] get$xMeasured() {
		return xMeasured;
	}

	@Override
	public final void set$xMeasured(double[] cv$value) {
		xMeasured = cv$value;
	}

	@Override
	public final int[] get$z() {
		return z;
	}

	@Override
	public final void set$z(int[] cv$value) {
		z = cv$value;
	}

	private final void logProbabilityValue$sample17() {
		if(!fixedProbFlag$sample17) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double[] cv$sampleValue = phi;
				{
					{
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, alpha, k));
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
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var16 = cv$sampleAccumulator;
			logProbability$phi = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample17 = fixedFlag$sample17;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$phi;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var16 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample34() {
		if(!fixedProbFlag$sample34) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var33 = 0; var33 < k; var33 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = mu[var33];
					{
						{
							double var20 = 0.0;
							double var21 = 20.0;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var20) / Math.sqrt(var21))) - (0.5 * Math.log(var21))));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var22 = cv$sampleAccumulator;
			logProbability$var34 = cv$sampleAccumulator;
			logProbability$mu = (logProbability$mu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample34 = fixedFlag$sample34;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var33 = 0; var33 < k; var33 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var34;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var22 = cv$rvAccumulator;
			logProbability$mu = (logProbability$mu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!fixedProbFlag$sample52) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var51 = 0; var51 < k; var51 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = sigma[var51];
					{
						{
							double var38 = 1.0;
							double var39 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var38, var39));
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var40 = cv$sampleAccumulator;
			logProbability$var52 = cv$sampleAccumulator;
			logProbability$sigma = (logProbability$sigma + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample52 = fixedFlag$sample52;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var51 = 0; var51 < k; var51 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var52;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var40 = cv$rvAccumulator;
			logProbability$sigma = (logProbability$sigma + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample68() {
		double cv$accumulator = 0.0;
		boolean cv$sampleReached = false;
		for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1) {
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = z[((i$var66 - 0) / 1)];
				{
					{
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < k))?Math.log(phi[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleReached = true;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var67[((i$var66 - 0) / 1)] = cv$sampleAccumulator;
			logProbability$sample68[((i$var66 - 0) / 1)] = cv$sampleProbability;
		}
		logProbability$z = (logProbability$z + cv$accumulator);
		logProbability$$model = (logProbability$$model + cv$accumulator);
	}

	private final void logProbabilityValue$sample72() {
		double cv$accumulator = 0.0;
		boolean cv$sampleReached = false;
		for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1) {
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = x[i$var66];
				{
					{
						double var69 = mu[z[((i$var66 - 0) / 1)]];
						double var70 = sigma[z[((i$var66 - 0) / 1)]];
						double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var69) / Math.sqrt(var70))) - (0.5 * Math.log(var70))));
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
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleReached = true;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var71[((i$var66 - 0) / 1)] = cv$sampleAccumulator;
			logProbability$sample72[((i$var66 - 0) / 1)] = cv$sampleProbability;
		}
		logProbability$x = (logProbability$x + cv$accumulator);
		logProbability$$model = (logProbability$$model + cv$accumulator);
		logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
	}

	private final void sample17() {
		if(true) {
			double[] cv$targetLocal = phi;
			double[] cv$countLocal = cv$var17$countGlobal;
			int cv$arrayLength = k;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1)
							cv$countLocal[z[((i$var66 - 0) / 1)]] = (cv$countLocal[z[((i$var66 - 0) / 1)]] + 1.0);
					}
				}
			}
			Conjugates.sampleConjugateDirichletCategorical(RNG$, alpha, cv$countLocal, cv$targetLocal, k);
		}
	}

	private final void sample34(int var33, int threadID$cv$var33, Rng RNG$) {
		if(true) {
			double cv$sum = 0.0;
			double cv$denominatorSquareSum = 0.0;
			boolean cv$sigmaNotFound = true;
			double cv$sigmaValue = 1.0;
			{
				{
					{
						for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1) {
							if((var33 == z[((i$var66 - 0) / 1)])) {
								{
									{
										{
											{
												{
													double cv$denominator = 1.0;
													double cv$numerator = 0.0;
													cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
													cv$sum = (cv$sum + (cv$denominator * (x[i$var66] - cv$numerator)));
													if(cv$sigmaNotFound) {
														cv$sigmaValue = sigma[z[((i$var66 - 0) / 1)]];
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
			double var34 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 20.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
			{
				{
					mu[var33] = var34;
				}
			}
		}
	}

	private final void sample52(int var51, int threadID$cv$var51, Rng RNG$) {
		if(true) {
			double cv$sum = 0.0;
			int cv$count = 0;
			{
				{
					{
						for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1) {
							if((var51 == z[((i$var66 - 0) / 1)])) {
								{
									{
										{
											{
												{
													double cv$var71$mu = mu[z[((i$var66 - 0) / 1)]];
													double cv$var71$diff = (cv$var71$mu - x[i$var66]);
													cv$sum = (cv$sum + (cv$var71$diff * cv$var71$diff));
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
			double var52 = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 1.0, 1.0, cv$sum, cv$count);
			{
				{
					sigma[var51] = var52;
				}
			}
		}
	}

	private final void sample68(int i$var66, int threadID$cv$i$var66, Rng RNG$) {
		if(true) {
			int cv$numNumStates = 0;
			{
				cv$numNumStates = Math.max(cv$numNumStates, k);
			}
			double[] cv$stateProbabilityLocal = cv$var68$stateProbabilityGlobal[threadID$cv$i$var66];
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				z[((i$var66 - 0) / 1)] = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] cv$temp$0$phi;
					{
						cv$temp$0$phi = phi;
					}
					int cv$temp$1$$var350;
					{
						int $var350 = k;
						cv$temp$1$$var350 = $var350;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var350))?Math.log(cv$temp$0$phi[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							boolean guard$sample68gaussian71 = false;
							if(!guard$sample68gaussian71) {
								guard$sample68gaussian71 = true;
								{
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double cv$temp$2$var69;
													{
														double var69 = mu[cv$currentValue];
														cv$temp$2$var69 = var69;
													}
													double cv$temp$3$var70;
													{
														double var70 = sigma[cv$currentValue];
														cv$temp$3$var70 = var70;
													}
													if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var66] - cv$temp$2$var69) / Math.sqrt(cv$temp$3$var70))) - (0.5 * Math.log(cv$temp$3$var70)))) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var66] - cv$temp$2$var69) / Math.sqrt(cv$temp$3$var70))) - (0.5 * Math.log(cv$temp$3$var70)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var66] - cv$temp$2$var69) / Math.sqrt(cv$temp$3$var70))) - (0.5 * Math.log(cv$temp$3$var70))));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var66] - cv$temp$2$var69) / Math.sqrt(cv$temp$3$var70))) - (0.5 * Math.log(cv$temp$3$var70)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var66] - cv$temp$2$var69) / Math.sqrt(cv$temp$3$var70))) - (0.5 * Math.log(cv$temp$3$var70)))));
													}
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
							if(!guard$sample68gaussian71) {
								guard$sample68gaussian71 = true;
								{
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double cv$temp$4$var69;
													{
														double var69 = mu[cv$currentValue];
														cv$temp$4$var69 = var69;
													}
													double cv$temp$5$var70;
													{
														double var70 = sigma[cv$currentValue];
														cv$temp$5$var70 = var70;
													}
													if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var66] - cv$temp$4$var69) / Math.sqrt(cv$temp$5$var70))) - (0.5 * Math.log(cv$temp$5$var70)))) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var66] - cv$temp$4$var69) / Math.sqrt(cv$temp$5$var70))) - (0.5 * Math.log(cv$temp$5$var70)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var66] - cv$temp$4$var69) / Math.sqrt(cv$temp$5$var70))) - (0.5 * Math.log(cv$temp$5$var70))));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var66] - cv$temp$4$var69) / Math.sqrt(cv$temp$5$var70))) - (0.5 * Math.log(cv$temp$5$var70)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var66] - cv$temp$4$var69) / Math.sqrt(cv$temp$5$var70))) - (0.5 * Math.log(cv$temp$5$var70)))));
													}
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
			double cv$logSum = 0.0;
			{
				double cv$lseMax = cv$stateProbabilityLocal[0];
				for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
					double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
					if((cv$lseMax < cv$lseElementValue))
						cv$lseMax = cv$lseElementValue;
				}
				if((cv$lseMax == Double.NEGATIVE_INFINITY))
					cv$logSum = Double.NEGATIVE_INFINITY;
				else {
					double cv$lseSum = 0.0;
					for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
						cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
					cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
				}
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numNumStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
			z[((i$var66 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates);
		}
	}

	@Override
	public final void allocateScratch() {
		{
			cv$var17$countGlobal = new double[5];
		}
		{
			{
				int cv$threadCount = threadCount();
				cv$var68$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var68$stateProbabilityGlobal[cv$index] = new double[5];
			}
		}
	}

	@Override
	public final void allocator() {
		{
			alpha = new double[5];
		}
		if(!fixedFlag$sample17) {
			{
				phi = new double[5];
			}
		}
		if(!fixedFlag$sample34) {
			{
				mu = new double[5];
			}
		}
		if(!fixedFlag$sample52) {
			{
				sigma = new double[5];
			}
		}
		{
			x = new double[length$xMeasured];
		}
		{
			z = new int[((((length$xMeasured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var67 = new double[((((length$xMeasured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample68 = new double[((((length$xMeasured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var71 = new double[((((length$xMeasured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample72 = new double[((((length$xMeasured - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, alpha, k, phi);
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
						if(!fixedFlag$sample34)
							mu[var33] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
						if(!fixedFlag$sample52)
							sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, length$xMeasured, 1,
			(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1) {
						z[((i$var66 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, phi, k);
						x[i$var66] = ((Math.sqrt(sigma[z[((i$var66 - 0) / 1)]]) * DistributionSampling.sampleGaussian(RNG$1)) + mu[z[((i$var66 - 0) / 1)]]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, alpha, k, phi);
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
						if(!fixedFlag$sample34)
							mu[var33] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
						if(!fixedFlag$sample52)
							sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, length$xMeasured, 1,
			(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1)
						z[((i$var66 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, phi, k);
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, alpha, k, phi);
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
						if(!fixedFlag$sample34)
							mu[var33] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
						if(!fixedFlag$sample52)
							sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, length$xMeasured, 1,
			(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1) {
						z[((i$var66 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, phi, k);
						x[i$var66] = ((Math.sqrt(sigma[z[((i$var66 - 0) / 1)]]) * DistributionSampling.sampleGaussian(RNG$1)) + mu[z[((i$var66 - 0) / 1)]]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, alpha, k, phi);
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
						if(!fixedFlag$sample34)
							mu[var33] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
						if(!fixedFlag$sample52)
							sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, length$xMeasured, 1,
			(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1)
						z[((i$var66 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, phi, k);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, alpha, k, phi);
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
						if(!fixedFlag$sample34)
							mu[var33] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
						if(!fixedFlag$sample52)
							sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, length$xMeasured, 1,
			(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1)
						z[((i$var66 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, phi, k);
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample17)
				sample17();
			parallelFor(RNG$, 0, k, 1,
				(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
							if(!fixedFlag$sample34)
								sample34(var33, threadID$var33, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, k, 1,
				(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
							if(!fixedFlag$sample52)
								sample52(var51, threadID$var51, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, length$xMeasured, 1,
				(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1)
							sample68(i$var66, threadID$i$var66, RNG$1);
				}
			);
		} else {
			parallelFor(RNG$, 0, length$xMeasured, 1,
				(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1)
							sample68(i$var66, threadID$i$var66, RNG$1);
				}
			);
			parallelFor(RNG$, 0, k, 1,
				(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
							if(!fixedFlag$sample52)
								sample52(var51, threadID$var51, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, k, 1,
				(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
							if(!fixedFlag$sample34)
								sample34(var33, threadID$var33, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample17)
				sample17();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		k = 5;
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var13, int forEnd$i$var13, int threadID$i$var13, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var13 = forStart$i$var13; i$var13 < forEnd$i$var13; i$var13 += 1)
						alpha[i$var13] = 1.0;
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var16 = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$phi = Double.NaN;
		logProbability$var22 = Double.NaN;
		logProbability$mu = 0.0;
		if(!fixedProbFlag$sample34)
			logProbability$var34 = Double.NaN;
		logProbability$var40 = Double.NaN;
		logProbability$sigma = 0.0;
		if(!fixedProbFlag$sample52)
			logProbability$var52 = Double.NaN;
		for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1)
			logProbability$var67[((i$var66 - 0) / 1)] = Double.NaN;
		logProbability$z = 0.0;
		for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1)
			logProbability$sample68[((i$var66 - 0) / 1)] = Double.NaN;
		for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1)
			logProbability$var71[((i$var66 - 0) / 1)] = Double.NaN;
		logProbability$x = 0.0;
		for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1)
			logProbability$sample72[((i$var66 - 0) / 1)] = Double.NaN;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample17)
			logProbabilityValue$sample17();
		if(fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(fixedFlag$sample52)
			logProbabilityValue$sample52();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample34();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample34();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample72();
	}

	@Override
	public final void propagateObservedValues() {
		double[] cv$source1 = xMeasured;
		double[] cv$target1 = x;
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
		     + " * Copyright (c) 2019-2023, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model GaussianMixtureTest(double[] xMeasured) {\n"
		     + "\n"
		     + "        int k = 5;\n"
		     + "\n"
		     + "        double[] alpha = new double[k];\n"
		     + "        for(int i:[0..k)) \n"
		     + "            alpha[i] = 1.0;\n"
		     + "        \n"
		     + "        double[] phi = dirichlet(alpha).sample();\n"
		     + "        double[] mu = gaussian(0, 20).sample(k);\n"
		     + "        double[] sigma = inverseGamma(1, 1).sample(k);\n"
		     + "        \n"
		     + "        double[] x = new double[xMeasured.length];\n"
		     + "        for(int i:[0..xMeasured.length)) {\n"
		     + "            int z = categorical(phi).sample();\n"
		     + "            x[i] = gaussian(mu[z], sigma[z]).sample();\n"
		     + "        }\n"
		     + "        \n"
		     + "        x.observe(xMeasured);\n"
		     + "}\n"
		     + "";
	}
}