package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LDATest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements LDATest$CoreInterface {
	private double[] alpha;
	private double[] beta;
	private double[][] cv$var25$countGlobal;
	private double[][] cv$var33$countGlobal;
	private double[][] cv$var53$stateProbabilityGlobal;
	private int[][] documents;
	private boolean fixedFlag$sample26 = false;
	private boolean fixedFlag$sample36 = false;
	private boolean fixedFlag$sample61 = false;
	private boolean fixedFlag$sample64 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean fixedProbFlag$sample36 = false;
	private boolean fixedProbFlag$sample61 = false;
	private boolean fixedProbFlag$sample64 = false;
	private int[] length$documents;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$phi;
	private double[][] logProbability$sample61;
	private double[][] logProbability$sample64;
	private double logProbability$theta;
	private double logProbability$var20;
	private double logProbability$var25;
	private double logProbability$var27;
	private double logProbability$var33;
	private double[][] logProbability$var52;
	private double[][] logProbability$var55;
	private double logProbability$w;
	private double logProbability$z;
	private int noTopics;
	private double[][] phi;
	private boolean setFlag$phi = false;
	private boolean setFlag$theta = false;
	private boolean setFlag$w = false;
	private boolean setFlag$z = false;
	private boolean system$gibbsForward = true;
	private double[][] theta;
	private int vocabSize;
	private int[][] w;
	private int[][] z;

	public LDATest$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$alpha() {
		return alpha;
	}

	@Override
	public final double[] get$beta() {
		return beta;
	}

	@Override
	public final int[][] get$documents() {
		return documents;
	}

	@Override
	public final void set$documents(int[][] cv$value) {
		documents = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	@Override
	public final void set$fixedFlag$sample26(boolean cv$value) {
		fixedFlag$sample26 = cv$value;
		fixedProbFlag$sample26 = (fixedFlag$sample26 && fixedProbFlag$sample26);
		fixedProbFlag$sample64 = (fixedFlag$sample26 && fixedProbFlag$sample64);
	}

	@Override
	public final boolean get$fixedFlag$sample36() {
		return fixedFlag$sample36;
	}

	@Override
	public final void set$fixedFlag$sample36(boolean cv$value) {
		fixedFlag$sample36 = cv$value;
		fixedProbFlag$sample36 = (fixedFlag$sample36 && fixedProbFlag$sample36);
		fixedProbFlag$sample61 = (fixedFlag$sample36 && fixedProbFlag$sample61);
	}

	@Override
	public final boolean get$fixedFlag$sample61() {
		return fixedFlag$sample61;
	}

	@Override
	public final void set$fixedFlag$sample61(boolean cv$value) {
		fixedFlag$sample61 = cv$value;
		fixedProbFlag$sample61 = (fixedFlag$sample61 && fixedProbFlag$sample61);
		fixedProbFlag$sample64 = (fixedFlag$sample61 && fixedProbFlag$sample64);
	}

	@Override
	public final boolean get$fixedFlag$sample64() {
		return fixedFlag$sample64;
	}

	@Override
	public final void set$fixedFlag$sample64(boolean cv$value) {
		fixedFlag$sample64 = cv$value;
		fixedProbFlag$sample64 = (fixedFlag$sample64 && fixedProbFlag$sample64);
	}

	@Override
	public final int[] get$length$documents() {
		return length$documents;
	}

	@Override
	public final void set$length$documents(int[] cv$value) {
		length$documents = cv$value;
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
	public final double get$logProbability$phi() {
		return logProbability$phi;
	}

	@Override
	public final double get$logProbability$theta() {
		return logProbability$theta;
	}

	@Override
	public final double get$logProbability$w() {
		return logProbability$w;
	}

	@Override
	public final double get$logProbability$z() {
		return logProbability$z;
	}

	@Override
	public final int get$noTopics() {
		return noTopics;
	}

	@Override
	public final void set$noTopics(int cv$value) {
		noTopics = cv$value;
	}

	@Override
	public final double[][] get$phi() {
		return phi;
	}

	@Override
	public final void set$phi(double[][] cv$value) {
		phi = cv$value;
		setFlag$phi = true;
	}

	@Override
	public final double[][] get$theta() {
		return theta;
	}

	@Override
	public final void set$theta(double[][] cv$value) {
		theta = cv$value;
		setFlag$theta = true;
	}

	@Override
	public final int get$vocabSize() {
		return vocabSize;
	}

	@Override
	public final void set$vocabSize(int cv$value) {
		vocabSize = cv$value;
	}

	@Override
	public final int[][] get$w() {
		return w;
	}

	@Override
	public final void set$w(int[][] cv$value) {
		w = cv$value;
		setFlag$w = true;
	}

	@Override
	public final int[][] get$z() {
		return z;
	}

	@Override
	public final void set$z(int[][] cv$value) {
		z = cv$value;
		setFlag$z = true;
	}

	private final void logProbabilityValue$sample26() {
		if(!fixedProbFlag$sample26) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var24 = 0; var24 < noTopics; var24 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = phi[var24];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, beta));
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
			logProbability$var20 = cv$sampleAccumulator;
			logProbability$var25 = cv$sampleAccumulator;
			logProbability$phi = (logProbability$phi + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample26 = fixedFlag$sample26;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var25;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var20 = cv$rvAccumulator;
			logProbability$phi = (logProbability$phi + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample36() {
		if(!fixedProbFlag$sample36) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var32 = 0; var32 < length$documents.length; var32 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = theta[var32];
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
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var27 = cv$sampleAccumulator;
			logProbability$var33 = cv$sampleAccumulator;
			logProbability$theta = (logProbability$theta + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample36 = fixedFlag$sample36;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var33;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var27 = cv$rvAccumulator;
			logProbability$theta = (logProbability$theta + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample61() {
		if(!fixedProbFlag$sample61) {
			double cv$accumulator = 0.0;
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
				for(int j = 0; j < length$documents[i$var40]; j += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						int cv$sampleValue = z[((i$var40 - 0) / 1)][((j - 0) / 1)];
						{
							{
								double[] var51 = theta[i$var40];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var51.length))?Math.log(var51[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var52[((i$var40 - 0) / 1)][((j - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample61[((i$var40 - 0) / 1)][((j - 0) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$z = (logProbability$z + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample61 = (fixedFlag$sample61 && fixedFlag$sample36);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
				for(int j = 0; j < length$documents[i$var40]; j += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample61[((i$var40 - 0) / 1)][((j - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var52[((i$var40 - 0) / 1)][((j - 0) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$z = (logProbability$z + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample64() {
		if(!fixedProbFlag$sample64) {
			double cv$accumulator = 0.0;
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
				for(int j = 0; j < length$documents[i$var40]; j += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						int cv$sampleValue = w[i$var40][j];
						{
							{
								double[] var54 = phi[z[((i$var40 - 0) / 1)][((j - 0) / 1)]];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var54.length))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var55[((i$var40 - 0) / 1)][((j - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample64[((i$var40 - 0) / 1)][((j - 0) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$w = (logProbability$w + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample64 = ((fixedFlag$sample64 && fixedFlag$sample26) && fixedFlag$sample61);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
				for(int j = 0; j < length$documents[i$var40]; j += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample64[((i$var40 - 0) / 1)][((j - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var55[((i$var40 - 0) / 1)][((j - 0) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$w = (logProbability$w + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample26(int var24, int threadID$cv$var24, Rng RNG$) {
		double[] cv$targetLocal = phi[var24];
		double[] cv$countLocal = cv$var25$countGlobal[threadID$cv$var24];
		int cv$arrayLength = vocabSize;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
						for(int j = 0; j < length$documents[i$var40]; j += 1) {
							if((var24 == z[((i$var40 - 0) / 1)][((j - 0) / 1)])) {
								{
									{
										{
											{
												{
													cv$countLocal[w[i$var40][j]] = (cv$countLocal[w[i$var40][j]] + 1.0);
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
		Conjugates.sampleConjugateDirichletCategorical(RNG$, beta, cv$countLocal, cv$targetLocal);
	}

	private final void sample36(int var32, int threadID$cv$var32, Rng RNG$) {
		double[] cv$targetLocal = theta[var32];
		double[] cv$countLocal = cv$var33$countGlobal[threadID$cv$var32];
		int cv$arrayLength = noTopics;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
						if((var32 == i$var40)) {
							for(int j = 0; j < length$documents[i$var40]; j += 1)
								cv$countLocal[z[((i$var40 - 0) / 1)][((j - 0) / 1)]] = (cv$countLocal[z[((i$var40 - 0) / 1)][((j - 0) / 1)]] + 1.0);
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, alpha, cv$countLocal, cv$targetLocal);
	}

	private final void sample61(int i$var40, int j, int threadID$cv$j, Rng RNG$) {
		double[] cv$stateProbabilityLocal = cv$var53$stateProbabilityGlobal[threadID$cv$j];
		for(int cv$valuePos = 0; cv$valuePos < noTopics; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			z[((i$var40 - 0) / 1)][((j - 0) / 1)] = cv$currentValue;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var51;
				{
					double[] var51 = theta[i$var40];
					cv$temp$0$var51 = var51;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var51.length))?Math.log(cv$temp$0$var51[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						{
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							{
								{
									{
										{
											double[] cv$temp$1$var54;
											{
												double[] var54 = phi[cv$currentValue];
												cv$temp$1$var54 = var54;
											}
											if(((Math.log(1.0) + (((0.0 <= w[i$var40][j]) && (w[i$var40][j] < cv$temp$1$var54.length))?Math.log(cv$temp$1$var54[w[i$var40][j]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= w[i$var40][j]) && (w[i$var40][j] < cv$temp$1$var54.length))?Math.log(cv$temp$1$var54[w[i$var40][j]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
											else {
												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= w[i$var40][j]) && (w[i$var40][j] < cv$temp$1$var54.length))?Math.log(cv$temp$1$var54[w[i$var40][j]]):Double.NEGATIVE_INFINITY));
												else
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= w[i$var40][j]) && (w[i$var40][j] < cv$temp$1$var54.length))?Math.log(cv$temp$1$var54[w[i$var40][j]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= w[i$var40][j]) && (w[i$var40][j] < cv$temp$1$var54.length))?Math.log(cv$temp$1$var54[w[i$var40][j]]):Double.NEGATIVE_INFINITY)));
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
		z[((i$var40 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			for(int var24 = 0; var24 < noTopics; var24 += 1)
				cv$max = Math.max(cv$max, vocabSize);
			{
				int cv$threadCount = threadCount();
				cv$var25$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var25$countGlobal[cv$index] = new double[cv$max];
			}
		}
		{
			int cv$max = 0;
			for(int var32 = 0; var32 < length$documents.length; var32 += 1)
				cv$max = Math.max(cv$max, noTopics);
			{
				int cv$threadCount = threadCount();
				cv$var33$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var33$countGlobal[cv$index] = new double[cv$max];
			}
		}
		{
			int cv$var34$max = noTopics;
			{
				int cv$threadCount = threadCount();
				cv$var53$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var53$stateProbabilityGlobal[cv$index] = new double[cv$var34$max];
			}
		}
	}

	@Override
	public final void allocator() {
		{
			alpha = new double[noTopics];
		}
		{
			beta = new double[vocabSize];
		}
		if(!setFlag$phi) {
			{
				phi = new double[noTopics][];
				for(int var24 = 0; var24 < noTopics; var24 += 1)
					phi[var24] = new double[vocabSize];
			}
		}
		if(!setFlag$theta) {
			{
				theta = new double[length$documents.length][];
				for(int var32 = 0; var32 < length$documents.length; var32 += 1)
					theta[var32] = new double[noTopics];
			}
		}
		if(!setFlag$w) {
			{
				w = new int[length$documents.length][];
				for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1)
					w[i$var40] = new int[length$documents[i$var40]];
			}
		}
		if(!setFlag$z) {
			{
				z = new int[((((length$documents.length - 1) - 0) / 1) + 1)][];
				for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1)
					z[((i$var40 - 0) / 1)] = new int[((((length$documents[i$var40] - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$var52 = new double[((((length$documents.length - 1) - 0) / 1) + 1)][];
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1)
				logProbability$var52[((i$var40 - 0) / 1)] = new double[((((length$documents[i$var40] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample61 = new double[((((length$documents.length - 1) - 0) / 1) + 1)][];
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1)
				logProbability$sample61[((i$var40 - 0) / 1)] = new double[((((length$documents[i$var40] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var55 = new double[((((length$documents.length - 1) - 0) / 1) + 1)][];
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1)
				logProbability$var55[((i$var40 - 0) / 1)] = new double[((((length$documents[i$var40] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample64 = new double[((((length$documents.length - 1) - 0) / 1) + 1)][];
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1)
				logProbability$sample64[((i$var40 - 0) / 1)] = new double[((((length$documents[i$var40] - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, noTopics, 1,
			(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
						double[] var25 = phi[var24];
						if(!fixedFlag$sample26)
							DistributionSampling.sampleDirichlet(RNG$1, beta, var25);
					}
			}
		);
		parallelFor(RNG$, 0, length$documents.length, 1,
			(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1) {
						double[] var33 = theta[var32];
						if(!fixedFlag$sample36)
							DistributionSampling.sampleDirichlet(RNG$1, alpha, var33);
					}
			}
		);
		parallelFor(RNG$, 0, length$documents.length, 1,
			(int forStart$index$i$var40, int forEnd$index$i$var40, int threadID$index$i$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var40 = forStart$index$i$var40; index$i$var40 < forEnd$index$i$var40; index$i$var40 += 1) {
						int i$var40 = index$i$var40;
						int[] t = w[i$var40];
						parallelFor(RNG$1, 0, length$documents[i$var40], 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j = forStart$j; j < forEnd$j; j += 1) {
										if(!fixedFlag$sample61)
											z[((i$var40 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$2, theta[i$var40]);
										if(!fixedFlag$sample64)
											t[j] = DistributionSampling.sampleCategorical(RNG$2, phi[z[((i$var40 - 0) / 1)][((j - 0) / 1)]]);
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 0, noTopics, 1,
			(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
						double[] var25 = phi[var24];
						if(!fixedFlag$sample26)
							DistributionSampling.sampleDirichlet(RNG$1, beta, var25);
					}
			}
		);
		parallelFor(RNG$, 0, length$documents.length, 1,
			(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1) {
						double[] var33 = theta[var32];
						if(!fixedFlag$sample36)
							DistributionSampling.sampleDirichlet(RNG$1, alpha, var33);
					}
			}
		);
		parallelFor(RNG$, 0, length$documents.length, 1,
			(int forStart$index$i$var40, int forEnd$index$i$var40, int threadID$index$i$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var40 = forStart$index$i$var40; index$i$var40 < forEnd$index$i$var40; index$i$var40 += 1) {
						int i$var40 = index$i$var40;
						parallelFor(RNG$1, 0, length$documents[i$var40], 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j = forStart$j; j < forEnd$j; j += 1) {
										if(!fixedFlag$sample61)
											z[((i$var40 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$2, theta[i$var40]);
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 0, noTopics, 1,
			(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
						double[] var25 = phi[var24];
						if(!fixedFlag$sample26)
							DistributionSampling.sampleDirichlet(RNG$1, beta, var25);
					}
			}
		);
		parallelFor(RNG$, 0, length$documents.length, 1,
			(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1) {
						double[] var33 = theta[var32];
						if(!fixedFlag$sample36)
							DistributionSampling.sampleDirichlet(RNG$1, alpha, var33);
					}
			}
		);
		parallelFor(RNG$, 0, length$documents.length, 1,
			(int forStart$index$i$var40, int forEnd$index$i$var40, int threadID$index$i$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var40 = forStart$index$i$var40; index$i$var40 < forEnd$index$i$var40; index$i$var40 += 1) {
						int i$var40 = index$i$var40;
						parallelFor(RNG$1, 0, length$documents[i$var40], 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j = forStart$j; j < forEnd$j; j += 1) {
										if(!fixedFlag$sample61)
											z[((i$var40 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$2, theta[i$var40]);
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			parallelFor(RNG$, 0, noTopics, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
							if(!fixedFlag$sample26)
								sample26(var24, threadID$var24, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1) {
							if(!fixedFlag$sample36)
								sample36(var32, threadID$var32, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$index$i$var40, int forEnd$index$i$var40, int threadID$index$i$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i$var40 = forStart$index$i$var40; index$i$var40 < forEnd$index$i$var40; index$i$var40 += 1) {
							int i$var40 = index$i$var40;
							parallelFor(RNG$1, 0, length$documents[i$var40], 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1) {
											if(!fixedFlag$sample61)
												sample61(i$var40, j, threadID$j, RNG$2);
										}
								}
							);
						}
				}
			);
		} else {
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$index$i$var40, int forEnd$index$i$var40, int threadID$index$i$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i$var40 = forStart$index$i$var40; index$i$var40 < forEnd$index$i$var40; index$i$var40 += 1) {
							int i$var40 = index$i$var40;
							parallelFor(RNG$1, 0, length$documents[i$var40], 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1) {
											if(!fixedFlag$sample61)
												sample61(i$var40, j, threadID$j, RNG$2);
										}
								}
							);
						}
				}
			);
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1) {
							if(!fixedFlag$sample36)
								sample36(var32, threadID$var32, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noTopics, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
							if(!fixedFlag$sample26)
								sample26(var24, threadID$var24, RNG$1);
						}
				}
			);
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, noTopics, 1,
			(int forStart$i$var11, int forEnd$i$var11, int threadID$i$var11, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var11 = forStart$i$var11; i$var11 < forEnd$i$var11; i$var11 += 1)
						alpha[i$var11] = 0.1;
			}
		);
		parallelFor(RNG$, 0, vocabSize, 1,
			(int forStart$i$var17, int forEnd$i$var17, int threadID$i$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var17 = forStart$i$var17; i$var17 < forEnd$i$var17; i$var17 += 1)
						beta[i$var17] = 0.1;
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var20 = 0.0;
		logProbability$phi = 0.0;
		if(!fixedProbFlag$sample26)
			logProbability$var25 = 0.0;
		logProbability$var27 = 0.0;
		logProbability$theta = 0.0;
		if(!fixedProbFlag$sample36)
			logProbability$var33 = 0.0;
		for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
			for(int j = 0; j < length$documents[i$var40]; j += 1)
				logProbability$var52[((i$var40 - 0) / 1)][((j - 0) / 1)] = 0.0;
		}
		logProbability$z = 0.0;
		if(!fixedProbFlag$sample61) {
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
				for(int j = 0; j < length$documents[i$var40]; j += 1)
					logProbability$sample61[((i$var40 - 0) / 1)][((j - 0) / 1)] = 0.0;
			}
		}
		for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
			for(int j = 0; j < length$documents[i$var40]; j += 1)
				logProbability$var55[((i$var40 - 0) / 1)][((j - 0) / 1)] = 0.0;
		}
		logProbability$w = 0.0;
		if(!fixedProbFlag$sample64) {
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
				for(int j = 0; j < length$documents[i$var40]; j += 1)
					logProbability$sample64[((i$var40 - 0) / 1)][((j - 0) / 1)] = 0.0;
			}
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample26)
			logProbabilityValue$sample26();
		if(fixedFlag$sample36)
			logProbabilityValue$sample36();
		if(fixedFlag$sample61)
			logProbabilityValue$sample61();
		logProbabilityValue$sample64();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample36();
		logProbabilityValue$sample61();
		logProbabilityValue$sample64();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample36();
		logProbabilityValue$sample61();
		logProbabilityValue$sample64();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 0, noTopics, 1,
			(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
						double[] var25 = phi[var24];
						if(!fixedFlag$sample26)
							DistributionSampling.sampleDirichlet(RNG$1, beta, var25);
					}
			}
		);
		parallelFor(RNG$, 0, length$documents.length, 1,
			(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1) {
						double[] var33 = theta[var32];
						if(!fixedFlag$sample36)
							DistributionSampling.sampleDirichlet(RNG$1, alpha, var33);
					}
			}
		);
		parallelFor(RNG$, 0, length$documents.length, 1,
			(int forStart$index$i$var40, int forEnd$index$i$var40, int threadID$index$i$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var40 = forStart$index$i$var40; index$i$var40 < forEnd$index$i$var40; index$i$var40 += 1) {
						int i$var40 = index$i$var40;
						parallelFor(RNG$1, 0, length$documents[i$var40], 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j = forStart$j; j < forEnd$j; j += 1) {
										if(!fixedFlag$sample61)
											z[((i$var40 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$2, theta[i$var40]);
									}
							}
						);
					}
			}
		);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int[][] cv$source1 = documents;
		int[][] cv$target1 = w;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = cv$source1[cv$index1];
			int[] cv$target2 = cv$target1[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel LDATest(int noTopics, int vocabSize, int[][] documents) {\n\n    double[] alpha = new double[noTopics];\n    for(int i:[0..noTopics))\n        alpha[i] = 0.1;\n\n    double[] beta = new double[vocabSize];\n    for(int i:[0..vocabSize))\n        beta[i] = 0.1;\n\n    double[][] phi = dirichlet(beta).sample(noTopics);\n    double[][] theta = dirichlet(alpha).sample(documents.length);\n    int[][] w = new int[documents.length][];\n\n    for(int i:[0..documents.length)) {\n        int[] t = new int[documents[i].length];\n        for(int j:[0..documents[i].length)) {\n            int z = categorical(theta[i]).sample();\n            t[j] = categorical(phi[z]).sample();\n        }\n        w[i] = t;\n    }\n\n    w.observe(documents);\n}\n";
	}
}