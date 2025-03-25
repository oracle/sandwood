package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class MultinomialBernoulli$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements MultinomialBernoulli$CoreInterface {
	private double[] beta;
	private double[] cv$var17$countGlobal;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample48 = false;
	private boolean fixedProbFlag$sample60 = false;
	private boolean fixedProbFlag$sample72 = false;
	private int length;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b1;
	private double logProbability$b2;
	private double logProbability$b3;
	private double logProbability$output;
	private double logProbability$p;
	private double logProbability$prior;
	private double logProbability$var16;
	private double logProbability$var19;
	private double logProbability$var48;
	private double logProbability$var60;
	private double logProbability$var72;
	private int n;
	private boolean[] observed;
	private boolean[] output;
	private double[] p;
	private int[] prior;
	private boolean system$gibbsForward = true;

	public MultinomialBernoulli$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$beta() {
		return beta;
	}

	@Override
	public final boolean get$fixedFlag$sample17() {
		return fixedFlag$sample17;
	}

	@Override
	public final void set$fixedFlag$sample17(boolean cv$value) {
		fixedFlag$sample17 = cv$value;
		fixedProbFlag$sample17 = (fixedFlag$sample17 && fixedProbFlag$sample17);
		fixedProbFlag$sample20 = (fixedFlag$sample17 && fixedProbFlag$sample20);
	}

	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	@Override
	public final void set$fixedFlag$sample20(boolean cv$value) {
		fixedFlag$sample20 = cv$value;
		fixedProbFlag$sample20 = (fixedFlag$sample20 && fixedProbFlag$sample20);
		fixedProbFlag$sample48 = (fixedFlag$sample20 && fixedProbFlag$sample48);
		fixedProbFlag$sample60 = (fixedFlag$sample20 && fixedProbFlag$sample60);
		fixedProbFlag$sample72 = (fixedFlag$sample20 && fixedProbFlag$sample72);
	}

	@Override
	public final int get$length() {
		return length;
	}

	@Override
	public final int get$length$observed() {
		return length$observed;
	}

	@Override
	public final void set$length$observed(int cv$value) {
		length$observed = cv$value;
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
	public final double get$logProbability$b1() {
		return logProbability$b1;
	}

	@Override
	public final double get$logProbability$b2() {
		return logProbability$b2;
	}

	@Override
	public final double get$logProbability$b3() {
		return logProbability$b3;
	}

	@Override
	public final double get$logProbability$output() {
		return logProbability$output;
	}

	@Override
	public final double get$logProbability$p() {
		return logProbability$p;
	}

	@Override
	public final double get$logProbability$prior() {
		return logProbability$prior;
	}

	@Override
	public final int get$n() {
		return n;
	}

	@Override
	public final boolean[] get$observed() {
		return observed;
	}

	@Override
	public final void set$observed(boolean[] cv$value) {
		observed = cv$value;
	}

	@Override
	public final boolean[] get$output() {
		return output;
	}

	@Override
	public final double[] get$p() {
		return p;
	}

	@Override
	public final void set$p(double[] cv$value) {
		p = cv$value;
		fixedProbFlag$sample17 = false;
		fixedProbFlag$sample20 = false;
	}

	@Override
	public final int[] get$prior() {
		return prior;
	}

	@Override
	public final void set$prior(int[] cv$value) {
		prior = cv$value;
		fixedProbFlag$sample20 = false;
		fixedProbFlag$sample48 = false;
		fixedProbFlag$sample60 = false;
		fixedProbFlag$sample72 = false;
	}

	private final void logProbabilityValue$sample17() {
		if(!fixedProbFlag$sample17) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double[] cv$sampleValue = p;
				{
					{
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, beta, 3));
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
			logProbability$p = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample17 = fixedFlag$sample17;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$p;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var16 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample20() {
		if(!fixedProbFlag$sample20) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int[] cv$sampleValue = prior;
				{
					{
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(cv$sampleValue, p, 3, n));
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
			logProbability$var19 = cv$sampleAccumulator;
			logProbability$prior = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample20 = (fixedFlag$sample20 && fixedFlag$sample17);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$prior;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var19 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample48() {
		if(!fixedProbFlag$sample48) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int i$var47 = 0; i$var47 < length; i$var47 += 3) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = output[i$var47];
					{
						{
							double var24 = (double)(prior[0] / n);
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var24));
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
			logProbability$b1 = cv$sampleAccumulator;
			logProbability$var48 = cv$sampleAccumulator;
			logProbability$output = (logProbability$output + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample48 = fixedFlag$sample20;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var48;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$b1 = cv$rvAccumulator;
			logProbability$output = (logProbability$output + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample60() {
		if(!fixedProbFlag$sample60) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int i$var59 = 1; i$var59 < length; i$var59 += 3) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = output[i$var59];
					{
						{
							double var29 = (double)(prior[1] / n);
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var29));
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
			logProbability$b2 = cv$sampleAccumulator;
			logProbability$var60 = cv$sampleAccumulator;
			logProbability$output = (logProbability$output + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample60 = fixedFlag$sample20;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var60;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$b2 = cv$rvAccumulator;
			logProbability$output = (logProbability$output + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample72() {
		if(!fixedProbFlag$sample72) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int i$var71 = 2; i$var71 < length; i$var71 += 3) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = output[i$var71];
					{
						{
							double var34 = (double)(prior[2] / n);
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var34));
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
			logProbability$b3 = cv$sampleAccumulator;
			logProbability$var72 = cv$sampleAccumulator;
			logProbability$output = (logProbability$output + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample72 = fixedFlag$sample20;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var72;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$b3 = cv$rvAccumulator;
			logProbability$output = (logProbability$output + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample17() {
		double[] cv$targetLocal = p;
		double[] cv$countLocal = cv$var17$countGlobal;
		int cv$arrayLength = 3;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					{
						{
							{
								{
									{
										int[] cv$sampleValue = prior;
										for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
											cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (1.0 * cv$sampleValue[cv$loopIndex]));
									}
								}
							}
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, beta, cv$countLocal, cv$targetLocal, 3);
	}

	private final void sample20() {
		int[] cv$targetLocal = prior;
		double cv$originalProbability = 0.0;
		double cv$proposedProbability = 0.0;
		int cv$arrayLength = 3;
		int cv$nonZeroCount = 0;
		for(int cv$loopIndex = 0; cv$loopIndex < 3; cv$loopIndex += 1) {
			if(!(cv$targetLocal[cv$loopIndex] == 0))
				cv$nonZeroCount = (cv$nonZeroCount + 1);
		}
		int cv$sourceIndex = (int)(0.0 + ((cv$nonZeroCount - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		for(int cv$loopIndex = 0; cv$loopIndex < (cv$sourceIndex + 1); cv$loopIndex += 1) {
			if((cv$targetLocal[cv$loopIndex] == 0))
				cv$sourceIndex = (cv$sourceIndex + 1);
		}
		int cv$changeValue = (int)(1.0 + (((cv$targetLocal[cv$sourceIndex] + 1.0) - 1.0) * DistributionSampling.sampleUniform(RNG$)));
		int cv$destinationIndex = (int)(0.0 + (((cv$arrayLength - 1) - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		if((cv$sourceIndex <= cv$destinationIndex))
			cv$destinationIndex = (cv$destinationIndex + 1);
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((cv$valuePos == 1)) {
				{
					cv$targetLocal[cv$sourceIndex] = (cv$targetLocal[cv$sourceIndex] - cv$changeValue);
					cv$targetLocal[cv$destinationIndex] = (cv$targetLocal[cv$destinationIndex] + cv$changeValue);
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$p;
				{
					cv$temp$0$p = p;
				}
				int cv$temp$1$$var131;
				{
					int $var131 = 3;
					cv$temp$1$$var131 = $var131;
				}
				int cv$temp$2$n;
				{
					cv$temp$2$n = n;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(cv$targetLocal, cv$temp$0$p, cv$temp$1$$var131, cv$temp$2$n));
				{
					{
						{
							for(int i$var47 = 0; i$var47 < length; i$var47 += 3) {
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								{
									{
										{
											{
												double cv$temp$3$var24;
												{
													double var24 = (double)(prior[0] / n);
													cv$temp$3$var24 = var24;
												}
												if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var47], cv$temp$3$var24)) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var47], cv$temp$3$var24)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var47], cv$temp$3$var24));
													else
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var47], cv$temp$3$var24)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var47], cv$temp$3$var24)));
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
						{
							for(int i$var59 = 1; i$var59 < length; i$var59 += 3) {
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								{
									{
										{
											{
												double cv$temp$4$var29;
												{
													double var29 = (double)(prior[1] / n);
													cv$temp$4$var29 = var29;
												}
												if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var59], cv$temp$4$var29)) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var59], cv$temp$4$var29)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var59], cv$temp$4$var29));
													else
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var59], cv$temp$4$var29)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var59], cv$temp$4$var29)));
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
						{
							for(int i$var71 = 2; i$var71 < length; i$var71 += 3) {
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								{
									{
										{
											{
												double cv$temp$5$var34;
												{
													double var34 = (double)(prior[2] / n);
													cv$temp$5$var34 = var34;
												}
												if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var71], cv$temp$5$var34)) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var71], cv$temp$5$var34)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var71], cv$temp$5$var34));
													else
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var71], cv$temp$5$var34)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(output[i$var71], cv$temp$5$var34)));
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
		if(((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)))))) {
			cv$targetLocal[cv$sourceIndex] = (cv$targetLocal[cv$sourceIndex] + cv$changeValue);
			cv$targetLocal[cv$destinationIndex] = (cv$targetLocal[cv$destinationIndex] - cv$changeValue);
		}
	}

	@Override
	public final void allocateScratch() {
		cv$var17$countGlobal = new double[3];
	}

	@Override
	public final void allocator() {
		{
			beta = new double[3];
		}
		if(!fixedFlag$sample17) {
			{
				p = new double[3];
			}
		}
		if(!fixedFlag$sample20) {
			{
				prior = new int[3];
			}
		}
		{
			output = new boolean[length$observed];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, beta, 3, p);
		if(!fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(RNG$, p, 3, n, prior);
		for(int i$var47 = 0; i$var47 < length; i$var47 += 3)
			output[i$var47] = DistributionSampling.sampleBernoulli(RNG$, (prior[0] / n));
		for(int i$var59 = 1; i$var59 < length; i$var59 += 3)
			output[i$var59] = DistributionSampling.sampleBernoulli(RNG$, (prior[1] / n));
		for(int i$var71 = 2; i$var71 < length; i$var71 += 3)
			output[i$var71] = DistributionSampling.sampleBernoulli(RNG$, (prior[2] / n));
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, beta, 3, p);
		if(!fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(RNG$, p, 3, n, prior);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, beta, 3, p);
		if(!fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(RNG$, p, 3, n, prior);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample17)
				sample17();
			if(!fixedFlag$sample20)
				sample20();
		} else {
			if(!fixedFlag$sample20)
				sample20();
			if(!fixedFlag$sample17)
				sample17();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		beta[0] = 0.1;
		beta[1] = 0.1;
		beta[2] = 0.1;
		n = 10;
		length = length$observed;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var16 = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$p = 0.0;
		logProbability$var19 = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$prior = 0.0;
		logProbability$b1 = 0.0;
		logProbability$output = 0.0;
		if(!fixedProbFlag$sample48)
			logProbability$var48 = 0.0;
		logProbability$b2 = 0.0;
		if(!fixedProbFlag$sample60)
			logProbability$var60 = 0.0;
		logProbability$b3 = 0.0;
		if(!fixedProbFlag$sample72)
			logProbability$var72 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample17)
			logProbabilityValue$sample17();
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		logProbabilityValue$sample48();
		logProbabilityValue$sample60();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample20();
		logProbabilityValue$sample48();
		logProbabilityValue$sample60();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample20();
		logProbabilityValue$sample48();
		logProbabilityValue$sample60();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, beta, 3, p);
		if(!fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(RNG$, p, 3, n, prior);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		boolean[] cv$source1 = observed;
		boolean[] cv$target1 = output;
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
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model MultinomialBernoulli(boolean[] observed) {\n"
		     + "    double[] beta = {0.1, 0.1, 0.1};\n"
		     + "    double[] p = dirichlet(beta).sample();\n"
		     + "    int n = 10;\n"
		     + "    int[] prior = multinomial(p, n).sample();\n"
		     + "    Bernoulli b1 = new Bernoulli(prior[0]/n);\n"
		     + "    Bernoulli b2 = new Bernoulli(prior[1]/n);\n"
		     + "    Bernoulli b3 = new Bernoulli(prior[2]/n);\n"
		     + "    int length = observed.length;\n"
		     + "    boolean[] output = new boolean[length];\n"
		     + "    for(int i=0; i<length; i+=3)\n"
		     + "        output[i] = b1.sample();\n"
		     + "    for(int i=1; i<length; i+=3)\n"
		     + "        output[i] = b2.sample();\n"
		     + "    for(int i=2; i<length; i+=3)\n"
		     + "        output[i] = b3.sample();\n"
		     + "    output.observe(observed);\n"
		     + "}\n"
		     + "";
	}
}