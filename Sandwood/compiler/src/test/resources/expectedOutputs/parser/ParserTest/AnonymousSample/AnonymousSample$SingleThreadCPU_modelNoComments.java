package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class AnonymousSample$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements AnonymousSample$CoreInterface {
	private double[] amounts1;
	private double[] amounts2;
	private boolean fixedFlag$sample15 = false;
	private boolean fixedFlag$sample21 = false;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample15 = false;
	private boolean fixedProbFlag$sample21 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample39 = false;
	private boolean fixedProbFlag$sample9 = false;
	private int length$obsAmounts1;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$amounts1;
	private double logProbability$amounts2;
	private double logProbability$mean1;
	private double logProbability$mean2;
	private double logProbability$priorSigma2;
	private double[] logProbability$sample35;
	private double[] logProbability$sample39;
	private double logProbability$var14;
	private double logProbability$var20;
	private double[] logProbability$var34;
	private double[] logProbability$var38;
	private double logProbability$var39;
	private double logProbability$var8;
	private double mean1;
	private double mean2;
	private int n;
	private double[] obsAmounts1;
	private double[] obsAmounts2;
	private double priorSigma2;
	private boolean system$gibbsForward = true;
	private double[] var39;

	public AnonymousSample$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$amounts1() {
		return amounts1;
	}

	@Override
	public final double[] get$amounts2() {
		return amounts2;
	}

	@Override
	public final boolean get$fixedFlag$sample15() {
		return fixedFlag$sample15;
	}

	@Override
	public final void set$fixedFlag$sample15(boolean cv$value) {
		fixedFlag$sample15 = cv$value;
		fixedProbFlag$sample15 = (fixedFlag$sample15 && fixedProbFlag$sample15);
		fixedProbFlag$sample35 = (fixedFlag$sample15 && fixedProbFlag$sample35);
	}

	@Override
	public final boolean get$fixedFlag$sample21() {
		return fixedFlag$sample21;
	}

	@Override
	public final void set$fixedFlag$sample21(boolean cv$value) {
		fixedFlag$sample21 = cv$value;
		fixedProbFlag$sample21 = (fixedFlag$sample21 && fixedProbFlag$sample21);
		fixedProbFlag$sample39 = (fixedFlag$sample21 && fixedProbFlag$sample39);
	}

	@Override
	public final boolean get$fixedFlag$sample9() {
		return fixedFlag$sample9;
	}

	@Override
	public final void set$fixedFlag$sample9(boolean cv$value) {
		fixedFlag$sample9 = cv$value;
		fixedProbFlag$sample9 = (fixedFlag$sample9 && fixedProbFlag$sample9);
		fixedProbFlag$sample35 = (fixedFlag$sample9 && fixedProbFlag$sample35);
		fixedProbFlag$sample39 = (fixedFlag$sample9 && fixedProbFlag$sample39);
	}

	@Override
	public final int get$length$obsAmounts1() {
		return length$obsAmounts1;
	}

	@Override
	public final void set$length$obsAmounts1(int cv$value) {
		length$obsAmounts1 = cv$value;
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
	public final double get$logProbability$amounts1() {
		return logProbability$amounts1;
	}

	@Override
	public final double get$logProbability$amounts2() {
		return logProbability$amounts2;
	}

	@Override
	public final double get$logProbability$mean1() {
		return logProbability$mean1;
	}

	@Override
	public final double get$logProbability$mean2() {
		return logProbability$mean2;
	}

	@Override
	public final double get$logProbability$priorSigma2() {
		return logProbability$priorSigma2;
	}

	@Override
	public final double get$mean1() {
		return mean1;
	}

	@Override
	public final void set$mean1(double cv$value) {
		mean1 = cv$value;
		fixedProbFlag$sample15 = false;
		fixedProbFlag$sample35 = false;
	}

	@Override
	public final double get$mean2() {
		return mean2;
	}

	@Override
	public final void set$mean2(double cv$value) {
		mean2 = cv$value;
		fixedProbFlag$sample21 = false;
		fixedProbFlag$sample39 = false;
	}

	@Override
	public final int get$n() {
		return n;
	}

	@Override
	public final double[] get$obsAmounts1() {
		return obsAmounts1;
	}

	@Override
	public final void set$obsAmounts1(double[] cv$value) {
		obsAmounts1 = cv$value;
	}

	@Override
	public final double[] get$obsAmounts2() {
		return obsAmounts2;
	}

	@Override
	public final void set$obsAmounts2(double[] cv$value) {
		obsAmounts2 = cv$value;
	}

	@Override
	public final double get$priorSigma2() {
		return priorSigma2;
	}

	@Override
	public final void set$priorSigma2(double cv$value) {
		priorSigma2 = cv$value;
		fixedProbFlag$sample9 = false;
		fixedProbFlag$sample35 = false;
		fixedProbFlag$sample39 = false;
	}

	private final void logProbabilityValue$sample15() {
		if(!fixedProbFlag$sample15) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = mean1;
				{
					{
						double var12 = 2000.0;
						double var13 = 10000.0;
						double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var12) / Math.sqrt(var13))) - (0.5 * Math.log(var13))));
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
			logProbability$var14 = cv$sampleAccumulator;
			logProbability$mean1 = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample15 = fixedFlag$sample15;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$mean1;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var14 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample21() {
		if(!fixedProbFlag$sample21) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = mean2;
				{
					{
						double var18 = 2000.0;
						double var19 = 10000.0;
						double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var18) / Math.sqrt(var19))) - (0.5 * Math.log(var19))));
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
			logProbability$var20 = cv$sampleAccumulator;
			logProbability$mean2 = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample21 = fixedFlag$sample21;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$mean2;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var20 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = amounts1[i];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - mean1) / Math.sqrt(priorSigma2))) - (0.5 * Math.log(priorSigma2))));
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
				logProbability$var34[((i - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample35[((i - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$amounts1 = (logProbability$amounts1 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample35 = (fixedFlag$sample9 && fixedFlag$sample15);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample35[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var34[((i - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$amounts1 = (logProbability$amounts1 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample39() {
		if(!fixedProbFlag$sample39) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = var39[((i - 0) / 1)];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - mean2) / Math.sqrt(priorSigma2))) - (0.5 * Math.log(priorSigma2))));
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
				logProbability$var38[((i - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample39[((i - 0) / 1)] = cv$sampleProbability;
			}
			boolean cv$guard$amounts2 = false;
			logProbability$var39 = (logProbability$var39 + cv$accumulator);
			{
				if(!cv$guard$amounts2) {
					cv$guard$amounts2 = true;
					logProbability$amounts2 = (logProbability$amounts2 + cv$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample39 = (fixedFlag$sample9 && fixedFlag$sample21);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample39[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var38[((i - 0) / 1)] = cv$rvAccumulator;
			}
			boolean cv$guard$amounts2 = false;
			logProbability$var39 = (logProbability$var39 + cv$accumulator);
			{
				if(!cv$guard$amounts2) {
					cv$guard$amounts2 = true;
					logProbability$amounts2 = (logProbability$amounts2 + cv$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!fixedProbFlag$sample9) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = priorSigma2;
				{
					{
						double var6 = 10000.0;
						double var7 = 900.0;
						double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var6) / Math.sqrt(var7))) - (0.5 * Math.log(var7))));
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
			logProbability$var8 = cv$sampleAccumulator;
			logProbability$priorSigma2 = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample9 = fixedFlag$sample9;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$priorSigma2;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var8 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample15() {
		if(true) {
			double cv$sum = 0.0;
			double cv$denominatorSquareSum = 0.0;
			boolean cv$sigmaNotFound = true;
			double cv$sigmaValue = 1.0;
			{
				{
					{
						for(int i = 0; i < n; i += 1) {
							double cv$denominator = 1.0;
							double cv$numerator = 0.0;
							cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
							cv$sum = (cv$sum + (cv$denominator * (amounts1[i] - cv$numerator)));
							if(cv$sigmaNotFound) {
								cv$sigmaValue = priorSigma2;
								cv$sigmaNotFound = false;
							}
						}
					}
				}
			}
			mean1 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 2000.0, 10000.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
		}
	}

	private final void sample21() {
		if(true) {
			double cv$sum = 0.0;
			double cv$denominatorSquareSum = 0.0;
			boolean cv$sigmaNotFound = true;
			double cv$sigmaValue = 1.0;
			{
				{
					{
						for(int i = 0; i < n; i += 1) {
							double cv$denominator = 1.0;
							double cv$numerator = 0.0;
							cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
							cv$sum = (cv$sum + (cv$denominator * (var39[((i - 0) / 1)] - cv$numerator)));
							if(cv$sigmaNotFound) {
								cv$sigmaValue = priorSigma2;
								cv$sigmaNotFound = false;
							}
						}
					}
				}
			}
			mean2 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 2000.0, 10000.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
		}
	}

	private final void sample9() {
		if(true) {
			int cv$numNumStates = 0;
			{
				cv$numNumStates = Math.max(cv$numNumStates, 2);
			}
			double cv$originalValue = priorSigma2;
			double cv$originalProbability = 0.0;
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				double cv$currentValue;
				if((cv$valuePos == 0))
					cv$currentValue = cv$originalValue;
				else {
					cv$currentValue = cv$proposedValue;
					{
						priorSigma2 = cv$proposedValue;
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$temp$0$var6;
					{
						cv$temp$0$var6 = 10000.0;
					}
					double cv$temp$1$var7;
					{
						cv$temp$1$var7 = 900.0;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var6) / Math.sqrt(cv$temp$1$var7))) - (0.5 * Math.log(cv$temp$1$var7))));
					{
						{
							for(int i = 0; i < n; i += 1) {
								double traceTempVariable$priorSigma2$1_2 = cv$currentValue;
								{
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double cv$temp$2$mean1;
													{
														cv$temp$2$mean1 = mean1;
													}
													double cv$temp$3$priorSigma2;
													{
														cv$temp$3$priorSigma2 = traceTempVariable$priorSigma2$1_2;
													}
													if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((amounts1[i] - cv$temp$2$mean1) / Math.sqrt(cv$temp$3$priorSigma2))) - (0.5 * Math.log(cv$temp$3$priorSigma2)))) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((amounts1[i] - cv$temp$2$mean1) / Math.sqrt(cv$temp$3$priorSigma2))) - (0.5 * Math.log(cv$temp$3$priorSigma2)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((amounts1[i] - cv$temp$2$mean1) / Math.sqrt(cv$temp$3$priorSigma2))) - (0.5 * Math.log(cv$temp$3$priorSigma2))));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((amounts1[i] - cv$temp$2$mean1) / Math.sqrt(cv$temp$3$priorSigma2))) - (0.5 * Math.log(cv$temp$3$priorSigma2)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((amounts1[i] - cv$temp$2$mean1) / Math.sqrt(cv$temp$3$priorSigma2))) - (0.5 * Math.log(cv$temp$3$priorSigma2)))));
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
					{
						{
							for(int i = 0; i < n; i += 1) {
								double traceTempVariable$priorSigma2$4_2 = cv$currentValue;
								{
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double cv$temp$4$mean2;
													{
														cv$temp$4$mean2 = mean2;
													}
													double cv$temp$5$priorSigma2;
													{
														cv$temp$5$priorSigma2 = traceTempVariable$priorSigma2$4_2;
													}
													if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var39[((i - 0) / 1)] - cv$temp$4$mean2) / Math.sqrt(cv$temp$5$priorSigma2))) - (0.5 * Math.log(cv$temp$5$priorSigma2)))) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var39[((i - 0) / 1)] - cv$temp$4$mean2) / Math.sqrt(cv$temp$5$priorSigma2))) - (0.5 * Math.log(cv$temp$5$priorSigma2)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var39[((i - 0) / 1)] - cv$temp$4$mean2) / Math.sqrt(cv$temp$5$priorSigma2))) - (0.5 * Math.log(cv$temp$5$priorSigma2))));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var39[((i - 0) / 1)] - cv$temp$4$mean2) / Math.sqrt(cv$temp$5$priorSigma2))) - (0.5 * Math.log(cv$temp$5$priorSigma2)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((var39[((i - 0) / 1)] - cv$temp$4$mean2) / Math.sqrt(cv$temp$5$priorSigma2))) - (0.5 * Math.log(cv$temp$5$priorSigma2)))));
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
				if((cv$valuePos == 0))
					cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
				else
					cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			double cv$ratio = (cv$proposedProbability - cv$originalProbability);
			if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio)))
				priorSigma2 = cv$originalValue;
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		{
			amounts1 = new double[length$obsAmounts1];
		}
		{
			amounts2 = new double[length$obsAmounts1];
		}
		{
			var39 = new double[((((length$obsAmounts1 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var34 = new double[((((length$obsAmounts1 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample35 = new double[((((length$obsAmounts1 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var38 = new double[((((length$obsAmounts1 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample39 = new double[((((length$obsAmounts1 - 1) - 0) / 1) + 1)];
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample9)
			priorSigma2 = ((Math.sqrt(900.0) * DistributionSampling.sampleGaussian(RNG$)) + 10000.0);
		if(!fixedFlag$sample15)
			mean1 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(RNG$)) + 2000.0);
		if(!fixedFlag$sample21)
			mean2 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(RNG$)) + 2000.0);
		for(int i = 0; i < n; i += 1) {
			amounts1[i] = ((Math.sqrt(priorSigma2) * DistributionSampling.sampleGaussian(RNG$)) + mean1);
			var39[((i - 0) / 1)] = ((Math.sqrt(priorSigma2) * DistributionSampling.sampleGaussian(RNG$)) + mean2);
			amounts2[i] = (amounts1[i] + var39[((i - 0) / 1)]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample9)
			priorSigma2 = ((Math.sqrt(900.0) * DistributionSampling.sampleGaussian(RNG$)) + 10000.0);
		if(!fixedFlag$sample15)
			mean1 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(RNG$)) + 2000.0);
		if(!fixedFlag$sample21)
			mean2 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(RNG$)) + 2000.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample9)
			priorSigma2 = ((Math.sqrt(900.0) * DistributionSampling.sampleGaussian(RNG$)) + 10000.0);
		if(!fixedFlag$sample15)
			mean1 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(RNG$)) + 2000.0);
		if(!fixedFlag$sample21)
			mean2 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(RNG$)) + 2000.0);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample9)
				sample9();
			if(!fixedFlag$sample15)
				sample15();
			if(!fixedFlag$sample21)
				sample21();
		} else {
			if(!fixedFlag$sample21)
				sample21();
			if(!fixedFlag$sample15)
				sample15();
			if(!fixedFlag$sample9)
				sample9();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		n = length$obsAmounts1;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var8 = 0.0;
		if(!fixedProbFlag$sample9)
			logProbability$priorSigma2 = 0.0;
		logProbability$var14 = 0.0;
		if(!fixedProbFlag$sample15)
			logProbability$mean1 = 0.0;
		logProbability$var20 = 0.0;
		if(!fixedProbFlag$sample21)
			logProbability$mean2 = 0.0;
		for(int i = 0; i < n; i += 1)
			logProbability$var34[((i - 0) / 1)] = 0.0;
		logProbability$amounts1 = 0.0;
		if(!fixedProbFlag$sample35) {
			for(int i = 0; i < n; i += 1)
				logProbability$sample35[((i - 0) / 1)] = 0.0;
		}
		for(int i = 0; i < n; i += 1)
			logProbability$var38[((i - 0) / 1)] = 0.0;
		logProbability$var39 = 0.0;
		logProbability$amounts2 = 0.0;
		if(!fixedProbFlag$sample39) {
			for(int i = 0; i < n; i += 1)
				logProbability$sample39[((i - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample9)
			logProbabilityValue$sample9();
		if(fixedFlag$sample15)
			logProbabilityValue$sample15();
		if(fixedFlag$sample21)
			logProbabilityValue$sample21();
		logProbabilityValue$sample35();
		logProbabilityValue$sample39();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample15();
		logProbabilityValue$sample21();
		logProbabilityValue$sample35();
		logProbabilityValue$sample39();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample15();
		logProbabilityValue$sample21();
		logProbabilityValue$sample35();
		logProbabilityValue$sample39();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample9)
			priorSigma2 = ((Math.sqrt(900.0) * DistributionSampling.sampleGaussian(RNG$)) + 10000.0);
		if(!fixedFlag$sample15)
			mean1 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(RNG$)) + 2000.0);
		if(!fixedFlag$sample21)
			mean2 = ((Math.sqrt(10000.0) * DistributionSampling.sampleGaussian(RNG$)) + 2000.0);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		{
			double[] cv$source1 = obsAmounts1;
			double[] cv$target1 = amounts1;
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				cv$target1[cv$index1] = cv$source1[cv$index1];
		}
		{
			double[] cv$source1 = obsAmounts2;
			double[] cv$target1 = amounts2;
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				cv$target1[cv$index1] = cv$source1[cv$index1];
		}
		for(int i = (n - ((((n - 1) - 0) % 1) + 1)); i >= ((0 - 1) + 1); i -= 1)
			var39[((i - 0) / 1)] = (amounts2[i] - amounts1[i]);
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
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model AnonymousSample(double[] obsAmounts1, double[] obsAmounts2) {\n"
		     + "    int n = obsAmounts1.length;\n"
		     + "\n"
		     + "    double priorSigma2 = gaussian(10000, 900).sample();   // can always use inverseGamma(1.5, 100)\n"
		     + "\n"
		     + "    double mean1 = gaussian(2000, 10000).sample();\n"
		     + "    double mean2 = gaussian(2000, 10000).sample();\n"
		     + "\n"
		     + "\n"
		     + "    double[] amounts1 = new double[n];\n"
		     + "    double[] amounts2 = new double[n];\n"
		     + "    for(int i : [0..n)) {\n"
		     + "        amounts1[i] = gaussian(mean1, priorSigma2).sample();\n"
		     + "        amounts2[i] = amounts1[i] + gaussian(mean2, priorSigma2).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    amounts1.observe(obsAmounts1);\n"
		     + "    amounts2.observe(obsAmounts2);\n"
		     + "}";
	}
}