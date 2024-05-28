package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class GaussianMixtureTest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements GaussianMixtureTest$CoreInterface {
	private double[] alpha;
	private double[] cv$var19$countGlobal;
	private double[][] cv$var70$stateProbabilityGlobal;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample37 = false;
	private boolean fixedFlag$sample55 = false;
	private boolean fixedFlag$sample73 = false;
	private boolean fixedFlag$sample77 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample37 = false;
	private boolean fixedProbFlag$sample55 = false;
	private boolean fixedProbFlag$sample73 = false;
	private boolean fixedProbFlag$sample77 = false;
	private int k;
	private int length$xMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$mu;
	private double logProbability$phi;
	private double[] logProbability$sample73;
	private double[] logProbability$sample77;
	private double logProbability$sigma;
	private double logProbability$var18;
	private double logProbability$var24;
	private double logProbability$var36;
	private double logProbability$var42;
	private double logProbability$var54;
	private double[] logProbability$var69;
	private double[] logProbability$var73;
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

	public GaussianMixtureTest$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$alpha() {
		return alpha;
	}

	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	@Override
	public final void set$fixedFlag$sample20(boolean cv$value) {
		fixedFlag$sample20 = cv$value;
		fixedProbFlag$sample20 = (fixedFlag$sample20 && fixedProbFlag$sample20);
		fixedProbFlag$sample73 = (fixedFlag$sample20 && fixedProbFlag$sample73);
	}

	@Override
	public final boolean get$fixedFlag$sample37() {
		return fixedFlag$sample37;
	}

	@Override
	public final void set$fixedFlag$sample37(boolean cv$value) {
		fixedFlag$sample37 = cv$value;
		fixedProbFlag$sample37 = (fixedFlag$sample37 && fixedProbFlag$sample37);
		fixedProbFlag$sample77 = (fixedFlag$sample37 && fixedProbFlag$sample77);
	}

	@Override
	public final boolean get$fixedFlag$sample55() {
		return fixedFlag$sample55;
	}

	@Override
	public final void set$fixedFlag$sample55(boolean cv$value) {
		fixedFlag$sample55 = cv$value;
		fixedProbFlag$sample55 = (fixedFlag$sample55 && fixedProbFlag$sample55);
		fixedProbFlag$sample77 = (fixedFlag$sample55 && fixedProbFlag$sample77);
	}

	@Override
	public final boolean get$fixedFlag$sample73() {
		return fixedFlag$sample73;
	}

	@Override
	public final void set$fixedFlag$sample73(boolean cv$value) {
		fixedFlag$sample73 = cv$value;
		fixedProbFlag$sample73 = (fixedFlag$sample73 && fixedProbFlag$sample73);
		fixedProbFlag$sample77 = (fixedFlag$sample73 && fixedProbFlag$sample77);
	}

	@Override
	public final boolean get$fixedFlag$sample77() {
		return fixedFlag$sample77;
	}

	@Override
	public final void set$fixedFlag$sample77(boolean cv$value) {
		fixedFlag$sample77 = cv$value;
		fixedProbFlag$sample77 = (fixedFlag$sample77 && fixedProbFlag$sample77);
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
		fixedProbFlag$sample37 = false;
		fixedProbFlag$sample77 = false;
	}

	@Override
	public final double[] get$phi() {
		return phi;
	}

	@Override
	public final void set$phi(double[] cv$value) {
		phi = cv$value;
		setFlag$phi = true;
		fixedProbFlag$sample20 = false;
		fixedProbFlag$sample73 = false;
	}

	@Override
	public final double[] get$sigma() {
		return sigma;
	}

	@Override
	public final void set$sigma(double[] cv$value) {
		sigma = cv$value;
		setFlag$sigma = true;
		fixedProbFlag$sample55 = false;
		fixedProbFlag$sample77 = false;
	}

	@Override
	public final double[] get$x() {
		return x;
	}

	@Override
	public final void set$x(double[] cv$value) {
		x = cv$value;
		setFlag$x = true;
		fixedProbFlag$sample77 = false;
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

	private final void logProbabilityValue$sample20() {
		if(!fixedProbFlag$sample20) {
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
			logProbability$var18 = cv$sampleAccumulator;
			logProbability$phi = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample20 = fixedFlag$sample20;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$phi;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var18 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample37() {
		if(!fixedProbFlag$sample37) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var35 = 0; var35 < k; var35 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = mu[var35];
					{
						{
							double var22 = 0.0;
							double var23 = 20.0;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var22) / Math.sqrt(var23))) - (0.5 * Math.log(var23))));
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
			logProbability$var24 = cv$sampleAccumulator;
			logProbability$var36 = cv$sampleAccumulator;
			logProbability$mu = (logProbability$mu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample37)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample37 = fixedFlag$sample37;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var36;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var24 = cv$rvAccumulator;
			logProbability$mu = (logProbability$mu + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample37)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample55() {
		if(!fixedProbFlag$sample55) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var53 = 0; var53 < k; var53 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = sigma[var53];
					{
						{
							double var40 = 1.0;
							double var41 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var40, var41));
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
			logProbability$var42 = cv$sampleAccumulator;
			logProbability$var54 = cv$sampleAccumulator;
			logProbability$sigma = (logProbability$sigma + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample55 = fixedFlag$sample55;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var54;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var42 = cv$rvAccumulator;
			logProbability$sigma = (logProbability$sigma + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample73() {
		if(!fixedProbFlag$sample73) {
			double cv$accumulator = 0.0;
			for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = z[((i$var68 - 0) / 1)];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < phi.length))?Math.log(phi[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var69[((i$var68 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample73[((i$var68 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$z = (logProbability$z + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample73)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample73 = (fixedFlag$sample73 && fixedFlag$sample20);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample73[((i$var68 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var69[((i$var68 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$z = (logProbability$z + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample73)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample77() {
		if(!fixedProbFlag$sample77) {
			double cv$accumulator = 0.0;
			for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = x[i$var68];
					{
						{
							double var71 = mu[z[((i$var68 - 0) / 1)]];
							double var72 = sigma[z[((i$var68 - 0) / 1)]];
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var71) / Math.sqrt(var72))) - (0.5 * Math.log(var72))));
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
				logProbability$var73[((i$var68 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample77[((i$var68 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$x = (logProbability$x + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample77 = (((fixedFlag$sample77 && fixedFlag$sample37) && fixedFlag$sample55) && fixedFlag$sample73);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample77[((i$var68 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var73[((i$var68 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$x = (logProbability$x + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample20() {
		double[] cv$targetLocal = phi;
		double[] cv$countLocal = cv$var19$countGlobal;
		int cv$arrayLength = k;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1)
						cv$countLocal[z[((i$var68 - 0) / 1)]] = (cv$countLocal[z[((i$var68 - 0) / 1)]] + 1.0);
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, alpha, cv$countLocal, cv$targetLocal);
	}

	private final void sample37(int var35, int threadID$cv$var35, Rng RNG$) {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		{
			{
				{
					for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1) {
						if((var35 == z[((i$var68 - 0) / 1)])) {
							{
								{
									{
										{
											{
												double cv$denominator = 1.0;
												double cv$numerator = 0.0;
												cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
												cv$sum = (cv$sum + (cv$denominator * (x[i$var68] - cv$numerator)));
												if(cv$sigmaNotFound) {
													cv$sigmaValue = sigma[z[((i$var68 - 0) / 1)]];
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
		double var36 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 20.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
		mu[var35] = var36;
	}

	private final void sample55(int var53, int threadID$cv$var53, Rng RNG$) {
		double cv$sum = 0.0;
		int cv$count = 0;
		{
			{
				{
					for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1) {
						if((var53 == z[((i$var68 - 0) / 1)])) {
							{
								{
									{
										{
											{
												double cv$var73$mu = mu[z[((i$var68 - 0) / 1)]];
												double cv$var73$diff = (cv$var73$mu - x[i$var68]);
												cv$sum = (cv$sum + (cv$var73$diff * cv$var73$diff));
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
		double var54 = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 1.0, 1.0, cv$sum, cv$count);
		sigma[var53] = var54;
	}

	private final void sample73(int i$var68, int threadID$cv$i$var68, Rng RNG$) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, k);
		}
		double[] cv$stateProbabilityLocal = cv$var70$stateProbabilityGlobal[threadID$cv$i$var68];
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			z[((i$var68 - 0) / 1)] = cv$currentValue;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$phi;
				{
					cv$temp$0$phi = phi;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$phi.length))?Math.log(cv$temp$0$phi[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						boolean guard$sample73gaussian76 = false;
						if(!guard$sample73gaussian76) {
							guard$sample73gaussian76 = true;
							{
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								{
									{
										{
											{
												double cv$temp$1$var71;
												{
													double var71 = mu[cv$currentValue];
													cv$temp$1$var71 = var71;
												}
												double cv$temp$2$var72;
												{
													double var72 = sigma[cv$currentValue];
													cv$temp$2$var72 = var72;
												}
												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var68] - cv$temp$1$var71) / Math.sqrt(cv$temp$2$var72))) - (0.5 * Math.log(cv$temp$2$var72)))) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var68] - cv$temp$1$var71) / Math.sqrt(cv$temp$2$var72))) - (0.5 * Math.log(cv$temp$2$var72)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var68] - cv$temp$1$var71) / Math.sqrt(cv$temp$2$var72))) - (0.5 * Math.log(cv$temp$2$var72))));
													else
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var68] - cv$temp$1$var71) / Math.sqrt(cv$temp$2$var72))) - (0.5 * Math.log(cv$temp$2$var72)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var68] - cv$temp$1$var71) / Math.sqrt(cv$temp$2$var72))) - (0.5 * Math.log(cv$temp$2$var72)))));
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
						if(!guard$sample73gaussian76) {
							guard$sample73gaussian76 = true;
							{
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								{
									{
										{
											{
												double cv$temp$3$var71;
												{
													double var71 = mu[cv$currentValue];
													cv$temp$3$var71 = var71;
												}
												double cv$temp$4$var72;
												{
													double var72 = sigma[cv$currentValue];
													cv$temp$4$var72 = var72;
												}
												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var68] - cv$temp$3$var71) / Math.sqrt(cv$temp$4$var72))) - (0.5 * Math.log(cv$temp$4$var72)))) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var68] - cv$temp$3$var71) / Math.sqrt(cv$temp$4$var72))) - (0.5 * Math.log(cv$temp$4$var72)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var68] - cv$temp$3$var71) / Math.sqrt(cv$temp$4$var72))) - (0.5 * Math.log(cv$temp$4$var72))));
													else
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var68] - cv$temp$3$var71) / Math.sqrt(cv$temp$4$var72))) - (0.5 * Math.log(cv$temp$4$var72)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((x[i$var68] - cv$temp$3$var71) / Math.sqrt(cv$temp$4$var72))) - (0.5 * Math.log(cv$temp$4$var72)))));
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
			for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
		z[((i$var68 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			cv$max = Math.max(cv$max, 5);
			cv$var19$countGlobal = new double[cv$max];
		}
		{
			{
				int cv$threadCount = threadCount();
				cv$var70$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var70$stateProbabilityGlobal[cv$index] = new double[5];
			}
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
			logProbability$var69 = new double[((((length$xMeasured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample73 = new double[((((length$xMeasured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var73 = new double[((((length$xMeasured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample77 = new double[((((length$xMeasured - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1) {
						if(!fixedFlag$sample37)
							mu[var35] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1) {
						if(!fixedFlag$sample55)
							sigma[var53] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, length$xMeasured, 1,
			(int forStart$i$var68, int forEnd$i$var68, int threadID$i$var68, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var68 = forStart$i$var68; i$var68 < forEnd$i$var68; i$var68 += 1) {
						if(!fixedFlag$sample73)
							z[((i$var68 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, phi);
						if(!fixedFlag$sample77)
							x[i$var68] = ((Math.sqrt(sigma[z[((i$var68 - 0) / 1)]]) * DistributionSampling.sampleGaussian(RNG$1)) + mu[z[((i$var68 - 0) / 1)]]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1) {
						if(!fixedFlag$sample37)
							mu[var35] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1) {
						if(!fixedFlag$sample55)
							sigma[var53] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, length$xMeasured, 1,
			(int forStart$i$var68, int forEnd$i$var68, int threadID$i$var68, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var68 = forStart$i$var68; i$var68 < forEnd$i$var68; i$var68 += 1) {
						if(!fixedFlag$sample73)
							z[((i$var68 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, phi);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1) {
						if(!fixedFlag$sample37)
							mu[var35] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1) {
						if(!fixedFlag$sample55)
							sigma[var53] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, length$xMeasured, 1,
			(int forStart$i$var68, int forEnd$i$var68, int threadID$i$var68, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var68 = forStart$i$var68; i$var68 < forEnd$i$var68; i$var68 += 1) {
						if(!fixedFlag$sample73)
							z[((i$var68 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, phi);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample20)
				sample20();
			parallelFor(RNG$, 0, k, 1,
				(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1) {
							if(!fixedFlag$sample37)
								sample37(var35, threadID$var35, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, k, 1,
				(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1) {
							if(!fixedFlag$sample55)
								sample55(var53, threadID$var53, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, length$xMeasured, 1,
				(int forStart$i$var68, int forEnd$i$var68, int threadID$i$var68, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var68 = forStart$i$var68; i$var68 < forEnd$i$var68; i$var68 += 1) {
							if(!fixedFlag$sample73)
								sample73(i$var68, threadID$i$var68, RNG$1);
						}
				}
			);
		} else {
			parallelFor(RNG$, 0, length$xMeasured, 1,
				(int forStart$i$var68, int forEnd$i$var68, int threadID$i$var68, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var68 = forStart$i$var68; i$var68 < forEnd$i$var68; i$var68 += 1) {
							if(!fixedFlag$sample73)
								sample73(i$var68, threadID$i$var68, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, k, 1,
				(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1) {
							if(!fixedFlag$sample55)
								sample55(var53, threadID$var53, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, k, 1,
				(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1) {
							if(!fixedFlag$sample37)
								sample37(var35, threadID$var35, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample20)
				sample20();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		k = 5;
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var15, int forEnd$i$var15, int threadID$i$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var15 = forStart$i$var15; i$var15 < forEnd$i$var15; i$var15 += 1)
						alpha[i$var15] = 1.0;
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var18 = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$phi = 0.0;
		logProbability$var24 = 0.0;
		logProbability$mu = 0.0;
		if(!fixedProbFlag$sample37)
			logProbability$var36 = 0.0;
		logProbability$var42 = 0.0;
		logProbability$sigma = 0.0;
		if(!fixedProbFlag$sample55)
			logProbability$var54 = 0.0;
		for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1)
			logProbability$var69[((i$var68 - 0) / 1)] = 0.0;
		logProbability$z = 0.0;
		if(!fixedProbFlag$sample73) {
			for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1)
				logProbability$sample73[((i$var68 - 0) / 1)] = 0.0;
		}
		for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1)
			logProbability$var73[((i$var68 - 0) / 1)] = 0.0;
		logProbability$x = 0.0;
		if(!fixedProbFlag$sample77) {
			for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1)
				logProbability$sample77[((i$var68 - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		if(fixedFlag$sample37)
			logProbabilityValue$sample37();
		if(fixedFlag$sample55)
			logProbabilityValue$sample55();
		if(fixedFlag$sample73)
			logProbabilityValue$sample73();
		logProbabilityValue$sample77();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample37();
		logProbabilityValue$sample55();
		logProbabilityValue$sample73();
		logProbabilityValue$sample77();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample37();
		logProbabilityValue$sample55();
		logProbabilityValue$sample73();
		logProbabilityValue$sample77();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1) {
						if(!fixedFlag$sample37)
							mu[var35] = ((Math.sqrt(20.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var53, int forEnd$var53, int threadID$var53, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var53 = forStart$var53; var53 < forEnd$var53; var53 += 1) {
						if(!fixedFlag$sample55)
							sigma[var53] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, length$xMeasured, 1,
			(int forStart$i$var68, int forEnd$i$var68, int threadID$i$var68, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var68 = forStart$i$var68; i$var68 < forEnd$i$var68; i$var68 += 1) {
						if(!fixedFlag$sample73)
							z[((i$var68 - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$1, phi);
					}
			}
		);
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