package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class GaussianMixtureTest$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements GaussianMixtureTest$CoreInterface {
	private double[] alpha;
	private double[] cv$var12$countGlobal;
	private double[] cv$var42$stateProbabilityGlobal;
	private boolean fixedFlag$sample13 = false;
	private boolean fixedFlag$sample23 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample49 = false;
	private boolean fixedProbFlag$sample13 = false;
	private boolean fixedProbFlag$sample23 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample49 = false;
	private int k;
	private int length$xMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$mu;
	private double logProbability$phi;
	private double[] logProbability$sample45;
	private double[] logProbability$sample49;
	private double logProbability$sigma;
	private double logProbability$var11;
	private double logProbability$var17;
	private double logProbability$var22;
	private double logProbability$var28;
	private double logProbability$var33;
	private double[] logProbability$var41;
	private double[] logProbability$var45;
	private double logProbability$x;
	private double logProbability$z;
	private double[] mu;
	private double[] phi;
	private boolean setFlag$mu = false;
	private boolean setFlag$phi = false;
	private boolean setFlag$sigma = false;
	private boolean setFlag$x = false;
	private boolean setFlag$z = false;
	private double[] sigma;
	private boolean system$gibbsForward = true;
	private double[] x;
	private double[] xMeasured;
	private int[] z;

	public GaussianMixtureTest$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$alpha() {
		return alpha;
	}

	@Override
	public final boolean get$fixedFlag$sample13() {
		return fixedFlag$sample13;
	}

	@Override
	public final void set$fixedFlag$sample13(boolean cv$value) {
		fixedFlag$sample13 = cv$value;
		fixedProbFlag$sample13 = (fixedFlag$sample13 && fixedProbFlag$sample13);
		fixedProbFlag$sample45 = (fixedFlag$sample13 && fixedProbFlag$sample45);
	}

	@Override
	public final boolean get$fixedFlag$sample23() {
		return fixedFlag$sample23;
	}

	@Override
	public final void set$fixedFlag$sample23(boolean cv$value) {
		fixedFlag$sample23 = cv$value;
		fixedProbFlag$sample23 = (fixedFlag$sample23 && fixedProbFlag$sample23);
		fixedProbFlag$sample49 = (fixedFlag$sample23 && fixedProbFlag$sample49);
	}

	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		fixedFlag$sample34 = cv$value;
		fixedProbFlag$sample34 = (fixedFlag$sample34 && fixedProbFlag$sample34);
		fixedProbFlag$sample49 = (fixedFlag$sample34 && fixedProbFlag$sample49);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		fixedFlag$sample45 = cv$value;
		fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedProbFlag$sample45);
		fixedProbFlag$sample49 = (fixedFlag$sample45 && fixedProbFlag$sample49);
	}

	@Override
	public final boolean get$fixedFlag$sample49() {
		return fixedFlag$sample49;
	}

	@Override
	public final void set$fixedFlag$sample49(boolean cv$value) {
		fixedFlag$sample49 = cv$value;
		fixedProbFlag$sample49 = (fixedFlag$sample49 && fixedProbFlag$sample49);
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
		setFlag$mu = true;
	}

	@Override
	public final double[] get$phi() {
		return phi;
	}

	@Override
	public final void set$phi(double[] cv$value) {
		phi = cv$value;
		setFlag$phi = true;
	}

	@Override
	public final double[] get$sigma() {
		return sigma;
	}

	@Override
	public final void set$sigma(double[] cv$value) {
		sigma = cv$value;
		setFlag$sigma = true;
	}

	@Override
	public final double[] get$x() {
		return x;
	}

	@Override
	public final void set$x(double[] cv$value) {
		x = cv$value;
		setFlag$x = true;
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
		setFlag$z = true;
	}

	private final void logProbabilityValue$sample13() {
		if(!fixedProbFlag$sample13) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double[] cv$sampleValue = phi;
				{
					{
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, alpha));
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
			logProbability$var11 = cv$sampleAccumulator;
			logProbability$phi = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample13)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample13 = fixedFlag$sample13;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$phi;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var11 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample13)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample23() {
		if(!fixedProbFlag$sample23) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var21 = 0; var21 < k; var21 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = mu[var21];
					{
						{
							double var15 = 0.0;
							double var16 = 20.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var15, var16));
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
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var17 = cv$sampleAccumulator;
			logProbability$var22 = cv$sampleAccumulator;
			logProbability$mu = (logProbability$mu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample23)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample23 = fixedFlag$sample23;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var22;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var17 = cv$rvAccumulator;
			logProbability$mu = (logProbability$mu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample23)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample34() {
		if(!fixedProbFlag$sample34) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var32 = 0; var32 < k; var32 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = sigma[var32];
					{
						{
							double var26 = 1.0;
							double var27 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var26, var27));
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
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var28 = cv$sampleAccumulator;
			logProbability$var33 = cv$sampleAccumulator;
			logProbability$sigma = (logProbability$sigma + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample34 = fixedFlag$sample34;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var33;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var28 = cv$rvAccumulator;
			logProbability$sigma = (logProbability$sigma + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!fixedProbFlag$sample45) {
			double cv$accumulator = 0.0;
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = z[((i$var40 - 0) / 1)];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$sampleValue, phi));
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
				logProbability$var41[((i$var40 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample45[((i$var40 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$z = (logProbability$z + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedFlag$sample13);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample45[((i$var40 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var41[((i$var40 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$z = (logProbability$z + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample49() {
		if(!fixedProbFlag$sample49) {
			double cv$accumulator = 0.0;
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = x[i$var40];
					{
						{
							double var43 = mu[z[((i$var40 - 0) / 1)]];
							double var44 = sigma[z[((i$var40 - 0) / 1)]];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var43, var44));
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
				logProbability$var45[((i$var40 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample49[((i$var40 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$x = (logProbability$x + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample49 = (((fixedFlag$sample49 && fixedFlag$sample23) && fixedFlag$sample34) && fixedFlag$sample45);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample49[((i$var40 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var45[((i$var40 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$x = (logProbability$x + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample13() {
		double[] cv$targetLocal = phi;
		double[] cv$countLocal = cv$var12$countGlobal;
		int cv$arrayLength = k;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1)
						cv$countLocal[z[((i$var40 - 0) / 1)]] = (cv$countLocal[z[((i$var40 - 0) / 1)]] + 1.0);
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, alpha, cv$countLocal, cv$targetLocal);
	}

	private final void sample23(int var21) {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		{
			{
				{
					for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
						if((var21 == z[((i$var40 - 0) / 1)])) {
							{
								{
									{
										{
											{
												double cv$denominator = 1.0;
												double cv$numerator = 0.0;
												cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
												cv$sum = (cv$sum + (cv$denominator * (x[i$var40] - cv$numerator)));
												if(cv$sigmaNotFound) {
													cv$sigmaValue = sigma[z[((i$var40 - 0) / 1)]];
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
		double var22 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 20.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
		mu[var21] = var22;
	}

	private final void sample34(int var32) {
		double cv$sum = 0.0;
		int cv$count = 0;
		{
			{
				{
					for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
						if((var32 == z[((i$var40 - 0) / 1)])) {
							{
								{
									{
										{
											{
												double cv$var45$mu = mu[z[((i$var40 - 0) / 1)]];
												double cv$var45$diff = (cv$var45$mu - x[i$var40]);
												cv$sum = (cv$sum + (cv$var45$diff * cv$var45$diff));
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
		double var33 = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 1.0, 1.0, cv$sum, cv$count);
		sigma[var32] = var33;
	}

	private final void sample45(int i$var40) {
		double[] cv$stateProbabilityLocal = cv$var42$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < k; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			z[((i$var40 - 0) / 1)] = cv$currentValue;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$phi;
				{
					cv$temp$0$phi = phi;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$currentValue, cv$temp$0$phi));
				{
					{
						boolean guard$sample45gaussian48 = false;
						if(!guard$sample45gaussian48) {
							guard$sample45gaussian48 = true;
							{
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								{
									{
										{
											{
												double cv$temp$1$var43;
												{
													double var43 = mu[cv$currentValue];
													cv$temp$1$var43 = var43;
												}
												double cv$temp$2$var44;
												{
													double var44 = sigma[cv$currentValue];
													cv$temp$2$var44 = var44;
												}
												if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(x[i$var40], cv$temp$1$var43, cv$temp$2$var44)) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(x[i$var40], cv$temp$1$var43, cv$temp$2$var44)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(x[i$var40], cv$temp$1$var43, cv$temp$2$var44));
													else
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(x[i$var40], cv$temp$1$var43, cv$temp$2$var44)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(x[i$var40], cv$temp$1$var43, cv$temp$2$var44)));
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
						if(!guard$sample45gaussian48) {
							guard$sample45gaussian48 = true;
							{
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								{
									{
										{
											{
												double cv$temp$3$var43;
												{
													double var43 = mu[cv$currentValue];
													cv$temp$3$var43 = var43;
												}
												double cv$temp$4$var44;
												{
													double var44 = sigma[cv$currentValue];
													cv$temp$4$var44 = var44;
												}
												if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(x[i$var40], cv$temp$3$var43, cv$temp$4$var44)) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(x[i$var40], cv$temp$3$var43, cv$temp$4$var44)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(x[i$var40], cv$temp$3$var43, cv$temp$4$var44));
													else
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(x[i$var40], cv$temp$3$var43, cv$temp$4$var44)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(x[i$var40], cv$temp$3$var43, cv$temp$4$var44)));
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
			for(int cv$lseIndex = 1; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$stateProbabilityLocal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		z[((i$var40 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			cv$max = Math.max(cv$max, 5);
			cv$var12$countGlobal = new double[cv$max];
		}
		{
			cv$var42$stateProbabilityGlobal = new double[5];
		}
	}

	@Override
	public final void allocator() {
		{
			alpha = new double[5];
		}
		if(!setFlag$phi) {
			{
				phi = new double[5];
			}
		}
		if(!setFlag$mu) {
			{
				mu = new double[5];
			}
		}
		if(!setFlag$sigma) {
			{
				sigma = new double[5];
			}
		}
		if(!setFlag$x) {
			{
				x = new double[length$xMeasured];
			}
		}
		if(!setFlag$z) {
			{
				z = new int[((((length$xMeasured - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$var41 = new double[((((length$xMeasured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample45 = new double[((((length$xMeasured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var45 = new double[((((length$xMeasured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample49 = new double[((((length$xMeasured - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample13)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		for(int var21 = 0; var21 < k; var21 += 1) {
			if(!fixedFlag$sample23)
				mu[var21] = DistributionSampling.sampleGaussian(RNG$, 0.0, 20.0);
		}
		for(int var32 = 0; var32 < k; var32 += 1) {
			if(!fixedFlag$sample34)
				sigma[var32] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
			if(!fixedFlag$sample45)
				z[((i$var40 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, phi);
			if(!fixedFlag$sample49)
				x[i$var40] = DistributionSampling.sampleGaussian(RNG$, mu[z[((i$var40 - 0) / 1)]], sigma[z[((i$var40 - 0) / 1)]]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample13)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		for(int var21 = 0; var21 < k; var21 += 1) {
			if(!fixedFlag$sample23)
				mu[var21] = DistributionSampling.sampleGaussian(RNG$, 0.0, 20.0);
		}
		for(int var32 = 0; var32 < k; var32 += 1) {
			if(!fixedFlag$sample34)
				sigma[var32] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
			if(!fixedFlag$sample45)
				z[((i$var40 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, phi);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample13)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		for(int var21 = 0; var21 < k; var21 += 1) {
			if(!fixedFlag$sample23)
				mu[var21] = DistributionSampling.sampleGaussian(RNG$, 0.0, 20.0);
		}
		for(int var32 = 0; var32 < k; var32 += 1) {
			if(!fixedFlag$sample34)
				sigma[var32] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
			if(!fixedFlag$sample45)
				z[((i$var40 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, phi);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample13)
				sample13();
			for(int var21 = 0; var21 < k; var21 += 1) {
				if(!fixedFlag$sample23)
					sample23(var21);
			}
			for(int var32 = 0; var32 < k; var32 += 1) {
				if(!fixedFlag$sample34)
					sample34(var32);
			}
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
				if(!fixedFlag$sample45)
					sample45(i$var40);
			}
		} else {
			for(int i$var40 = (length$xMeasured - ((((length$xMeasured - 1) - 0) % 1) + 1)); i$var40 >= ((0 - 1) + 1); i$var40 -= 1) {
				if(!fixedFlag$sample45)
					sample45(i$var40);
			}
			for(int var32 = (k - ((((k - 1) - 0) % 1) + 1)); var32 >= ((0 - 1) + 1); var32 -= 1) {
				if(!fixedFlag$sample34)
					sample34(var32);
			}
			for(int var21 = (k - ((((k - 1) - 0) % 1) + 1)); var21 >= ((0 - 1) + 1); var21 -= 1) {
				if(!fixedFlag$sample23)
					sample23(var21);
			}
			if(!fixedFlag$sample13)
				sample13();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		k = 5;
		for(int i$var8 = 0; i$var8 < 5; i$var8 += 1)
			alpha[i$var8] = 1.0;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var11 = 0.0;
		if(!fixedProbFlag$sample13)
			logProbability$phi = 0.0;
		logProbability$var17 = 0.0;
		logProbability$mu = 0.0;
		if(!fixedProbFlag$sample23)
			logProbability$var22 = 0.0;
		logProbability$var28 = 0.0;
		logProbability$sigma = 0.0;
		if(!fixedProbFlag$sample34)
			logProbability$var33 = 0.0;
		for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1)
			logProbability$var41[((i$var40 - 0) / 1)] = 0.0;
		logProbability$z = 0.0;
		if(!fixedProbFlag$sample45) {
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1)
				logProbability$sample45[((i$var40 - 0) / 1)] = 0.0;
		}
		for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1)
			logProbability$var45[((i$var40 - 0) / 1)] = 0.0;
		logProbability$x = 0.0;
		if(!fixedProbFlag$sample49) {
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1)
				logProbability$sample49[((i$var40 - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample13)
			logProbabilityValue$sample13();
		if(fixedFlag$sample23)
			logProbabilityValue$sample23();
		if(fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		logProbabilityValue$sample49();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample13();
		logProbabilityValue$sample23();
		logProbabilityValue$sample34();
		logProbabilityValue$sample45();
		logProbabilityValue$sample49();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample13();
		logProbabilityValue$sample23();
		logProbabilityValue$sample34();
		logProbabilityValue$sample45();
		logProbabilityValue$sample49();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample13)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		for(int var21 = 0; var21 < k; var21 += 1) {
			if(!fixedFlag$sample23)
				mu[var21] = DistributionSampling.sampleGaussian(RNG$, 0.0, 20.0);
		}
		for(int var32 = 0; var32 < k; var32 += 1) {
			if(!fixedFlag$sample34)
				sigma[var32] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
			if(!fixedFlag$sample45)
				z[((i$var40 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, phi);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel GaussianMixtureTest(double[] xMeasured) {\n\n        int k = 5;\n\n        double[] alpha = new double[k];\n        for(int i:[0..k)) \n            alpha[i] = 1.0;\n        \n        double[] phi = dirichlet(alpha).sample();\n        double[] mu = gaussian(0, 20).sample(k);\n        double[] sigma = inverseGamma(1, 1).sample(k);\n        \n        double[] x = new double[xMeasured.length];\n        for(int i:[0..xMeasured.length)) {\n            int z = categorical(phi).sample();\n            x[i] = gaussian(mu[z], sigma[z]).sample();\n        }\n        \n        x.observe(xMeasured);\n}\n";
	}
}