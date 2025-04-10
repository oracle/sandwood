package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LDATest$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements LDATest$CoreInterface {
	private double[] alpha;
	private double[] beta;
	private double[] cv$var42$countGlobal;
	private double[] cv$var57$countGlobal;
	private double[] cv$var88$stateProbabilityGlobal;
	private int[][] documents;
	private boolean fixedFlag$sample42 = false;
	private boolean fixedFlag$sample58 = false;
	private boolean fixedFlag$sample90 = false;
	private boolean fixedProbFlag$sample42 = false;
	private boolean fixedProbFlag$sample58 = false;
	private boolean fixedProbFlag$sample90 = false;
	private boolean fixedProbFlag$sample93 = false;
	private int[] length$documents;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$phi;
	private double[][] logProbability$sample90;
	private double[][] logProbability$sample93;
	private double logProbability$theta;
	private double logProbability$var30;
	private double logProbability$var42;
	private double logProbability$var44;
	private double logProbability$var57;
	private double[][] logProbability$var87;
	private double[][] logProbability$var90;
	private double logProbability$w;
	private double logProbability$z;
	private int noTopics;
	private double[][] phi;
	private boolean system$gibbsForward = true;
	private double[][] theta;
	private int vocabSize;
	private int[][] w;
	private int[][] z;

	public LDATest$SingleThreadCPU(ExecutionTarget target) {
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
	public final boolean get$fixedFlag$sample42() {
		return fixedFlag$sample42;
	}

	@Override
	public final void set$fixedFlag$sample42(boolean cv$value) {
		fixedFlag$sample42 = cv$value;
		fixedProbFlag$sample42 = (fixedFlag$sample42 && fixedProbFlag$sample42);
		fixedProbFlag$sample93 = (fixedFlag$sample42 && fixedProbFlag$sample93);
	}

	@Override
	public final boolean get$fixedFlag$sample58() {
		return fixedFlag$sample58;
	}

	@Override
	public final void set$fixedFlag$sample58(boolean cv$value) {
		fixedFlag$sample58 = cv$value;
		fixedProbFlag$sample58 = (fixedFlag$sample58 && fixedProbFlag$sample58);
		fixedProbFlag$sample90 = (fixedFlag$sample58 && fixedProbFlag$sample90);
	}

	@Override
	public final boolean get$fixedFlag$sample90() {
		return fixedFlag$sample90;
	}

	@Override
	public final void set$fixedFlag$sample90(boolean cv$value) {
		fixedFlag$sample90 = cv$value;
		fixedProbFlag$sample90 = (fixedFlag$sample90 && fixedProbFlag$sample90);
		fixedProbFlag$sample93 = (fixedFlag$sample90 && fixedProbFlag$sample93);
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
		fixedProbFlag$sample42 = false;
		fixedProbFlag$sample93 = false;
	}

	@Override
	public final double[][] get$theta() {
		return theta;
	}

	@Override
	public final void set$theta(double[][] cv$value) {
		theta = cv$value;
		fixedProbFlag$sample58 = false;
		fixedProbFlag$sample90 = false;
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
	public final int[][] get$z() {
		return z;
	}

	@Override
	public final void set$z(int[][] cv$value) {
		z = cv$value;
	}

	private final void logProbabilityValue$sample42() {
		if(!fixedProbFlag$sample42) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var41 = 0; var41 < noTopics; var41 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = phi[var41];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, beta, vocabSize));
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
			logProbability$var30 = cv$sampleAccumulator;
			logProbability$var42 = cv$sampleAccumulator;
			logProbability$phi = (logProbability$phi + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample42 = fixedFlag$sample42;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var42;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var30 = cv$rvAccumulator;
			logProbability$phi = (logProbability$phi + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample58() {
		if(!fixedProbFlag$sample58) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var56 = 0; var56 < length$documents.length; var56 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = theta[var56];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, alpha, noTopics));
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
			logProbability$var44 = cv$sampleAccumulator;
			logProbability$var57 = cv$sampleAccumulator;
			logProbability$theta = (logProbability$theta + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample58 = fixedFlag$sample58;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var57;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var44 = cv$rvAccumulator;
			logProbability$theta = (logProbability$theta + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample90() {
		if(!fixedProbFlag$sample90) {
			double cv$accumulator = 0.0;
			for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
				for(int j = 0; j < length$documents[i$var71]; j += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						int cv$sampleValue = z[((i$var71 - 0) / 1)][((j - 0) / 1)];
						{
							{
								double[] var86 = theta[i$var71];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noTopics))?Math.log(var86[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var87[((i$var71 - 0) / 1)][((j - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample90[((i$var71 - 0) / 1)][((j - 0) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$z = (logProbability$z + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample90)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample90 = (fixedFlag$sample90 && fixedFlag$sample58);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
				for(int j = 0; j < length$documents[i$var71]; j += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample90[((i$var71 - 0) / 1)][((j - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var87[((i$var71 - 0) / 1)][((j - 0) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$z = (logProbability$z + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample90)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample93() {
		if(!fixedProbFlag$sample93) {
			double cv$accumulator = 0.0;
			for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
				for(int j = 0; j < length$documents[i$var71]; j += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						int cv$sampleValue = w[i$var71][j];
						{
							{
								double[] var89 = phi[z[((i$var71 - 0) / 1)][((j - 0) / 1)]];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < vocabSize))?Math.log(var89[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var90[((i$var71 - 0) / 1)][((j - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample93[((i$var71 - 0) / 1)][((j - 0) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$w = (logProbability$w + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample93 = (fixedFlag$sample42 && fixedFlag$sample90);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
				for(int j = 0; j < length$documents[i$var71]; j += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample93[((i$var71 - 0) / 1)][((j - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var90[((i$var71 - 0) / 1)][((j - 0) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$w = (logProbability$w + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample42(int var41) {
		if(true) {
			double[] cv$targetLocal = phi[var41];
			double[] cv$countLocal = cv$var42$countGlobal;
			int cv$arrayLength = vocabSize;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
							for(int j = 0; j < length$documents[i$var71]; j += 1) {
								if((var41 == z[((i$var71 - 0) / 1)][((j - 0) / 1)])) {
									{
										{
											{
												{
													{
														cv$countLocal[w[i$var71][j]] = (cv$countLocal[w[i$var71][j]] + 1.0);
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
			Conjugates.sampleConjugateDirichletCategorical(RNG$, beta, cv$countLocal, cv$targetLocal, vocabSize);
		}
	}

	private final void sample58(int var56) {
		if(true) {
			double[] cv$targetLocal = theta[var56];
			double[] cv$countLocal = cv$var57$countGlobal;
			int cv$arrayLength = noTopics;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
							if((var56 == i$var71)) {
								for(int j = 0; j < length$documents[i$var71]; j += 1)
									cv$countLocal[z[((i$var71 - 0) / 1)][((j - 0) / 1)]] = (cv$countLocal[z[((i$var71 - 0) / 1)][((j - 0) / 1)]] + 1.0);
							}
						}
					}
				}
			}
			Conjugates.sampleConjugateDirichletCategorical(RNG$, alpha, cv$countLocal, cv$targetLocal, noTopics);
		}
	}

	private final void sample90(int i$var71, int j) {
		if(true) {
			int cv$numNumStates = 0;
			{
				cv$numNumStates = Math.max(cv$numNumStates, noTopics);
			}
			double[] cv$stateProbabilityLocal = cv$var88$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				z[((i$var71 - 0) / 1)][((j - 0) / 1)] = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] cv$temp$0$var86;
					{
						double[] var86 = theta[i$var71];
						cv$temp$0$var86 = var86;
					}
					int cv$temp$1$$var216;
					{
						int $var216 = noTopics;
						cv$temp$1$$var216 = $var216;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var216))?Math.log(cv$temp$0$var86[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								{
									{
										{
											{
												double[] cv$temp$2$var89;
												{
													double[] var89 = phi[cv$currentValue];
													cv$temp$2$var89 = var89;
												}
												int cv$temp$3$$var217;
												{
													int $var217 = vocabSize;
													cv$temp$3$$var217 = $var217;
												}
												if(((Math.log(1.0) + (((0.0 <= w[i$var71][j]) && (w[i$var71][j] < cv$temp$3$$var217))?Math.log(cv$temp$2$var89[w[i$var71][j]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= w[i$var71][j]) && (w[i$var71][j] < cv$temp$3$$var217))?Math.log(cv$temp$2$var89[w[i$var71][j]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= w[i$var71][j]) && (w[i$var71][j] < cv$temp$3$$var217))?Math.log(cv$temp$2$var89[w[i$var71][j]]):Double.NEGATIVE_INFINITY));
													else
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= w[i$var71][j]) && (w[i$var71][j] < cv$temp$3$$var217))?Math.log(cv$temp$2$var89[w[i$var71][j]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= w[i$var71][j]) && (w[i$var71][j] < cv$temp$3$$var217))?Math.log(cv$temp$2$var89[w[i$var71][j]]):Double.NEGATIVE_INFINITY)));
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
			z[((i$var71 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates);
		}
	}

	@Override
	public final void allocateScratch() {
		{
			cv$var42$countGlobal = new double[vocabSize];
		}
		{
			cv$var57$countGlobal = new double[noTopics];
		}
		{
			int cv$var58$max = noTopics;
			cv$var88$stateProbabilityGlobal = new double[cv$var58$max];
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
		if(!fixedFlag$sample42) {
			{
				phi = new double[noTopics][];
				for(int var41 = 0; var41 < noTopics; var41 += 1)
					phi[var41] = new double[vocabSize];
			}
		}
		if(!fixedFlag$sample58) {
			{
				theta = new double[length$documents.length][];
				for(int var56 = 0; var56 < length$documents.length; var56 += 1)
					theta[var56] = new double[noTopics];
			}
		}
		{
			w = new int[length$documents.length][];
			for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1)
				w[i$var71] = new int[length$documents[i$var71]];
		}
		if(!fixedFlag$sample90) {
			{
				z = new int[((((length$documents.length - 1) - 0) / 1) + 1)][];
				for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1)
					z[((i$var71 - 0) / 1)] = new int[((((length$documents[i$var71] - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$var87 = new double[((((length$documents.length - 1) - 0) / 1) + 1)][];
			for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1)
				logProbability$var87[((i$var71 - 0) / 1)] = new double[((((length$documents[i$var71] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample90 = new double[((((length$documents.length - 1) - 0) / 1) + 1)][];
			for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1)
				logProbability$sample90[((i$var71 - 0) / 1)] = new double[((((length$documents[i$var71] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var90 = new double[((((length$documents.length - 1) - 0) / 1) + 1)][];
			for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1)
				logProbability$var90[((i$var71 - 0) / 1)] = new double[((((length$documents[i$var71] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample93 = new double[((((length$documents.length - 1) - 0) / 1) + 1)][];
			for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1)
				logProbability$sample93[((i$var71 - 0) / 1)] = new double[((((length$documents[i$var71] - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int var41 = 0; var41 < noTopics; var41 += 1) {
			double[] var42 = phi[var41];
			if(!fixedFlag$sample42)
				DistributionSampling.sampleDirichlet(RNG$, beta, vocabSize, var42);
		}
		for(int var56 = 0; var56 < length$documents.length; var56 += 1) {
			double[] var57 = theta[var56];
			if(!fixedFlag$sample58)
				DistributionSampling.sampleDirichlet(RNG$, alpha, noTopics, var57);
		}
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
			int[] t = w[i$var71];
			for(int j = 0; j < length$documents[i$var71]; j += 1) {
				if(!fixedFlag$sample90)
					z[((i$var71 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, theta[i$var71], noTopics);
				t[j] = DistributionSampling.sampleCategorical(RNG$, phi[z[((i$var71 - 0) / 1)][((j - 0) / 1)]], vocabSize);
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var41 = 0; var41 < noTopics; var41 += 1) {
			double[] var42 = phi[var41];
			if(!fixedFlag$sample42)
				DistributionSampling.sampleDirichlet(RNG$, beta, vocabSize, var42);
		}
		for(int var56 = 0; var56 < length$documents.length; var56 += 1) {
			double[] var57 = theta[var56];
			if(!fixedFlag$sample58)
				DistributionSampling.sampleDirichlet(RNG$, alpha, noTopics, var57);
		}
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
			for(int j = 0; j < length$documents[i$var71]; j += 1) {
				if(!fixedFlag$sample90)
					z[((i$var71 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, theta[i$var71], noTopics);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var41 = 0; var41 < noTopics; var41 += 1) {
			double[] var42 = phi[var41];
			if(!fixedFlag$sample42)
				DistributionSampling.sampleDirichlet(RNG$, beta, vocabSize, var42);
		}
		for(int var56 = 0; var56 < length$documents.length; var56 += 1) {
			double[] var57 = theta[var56];
			if(!fixedFlag$sample58)
				DistributionSampling.sampleDirichlet(RNG$, alpha, noTopics, var57);
		}
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
			for(int j = 0; j < length$documents[i$var71]; j += 1) {
				if(!fixedFlag$sample90)
					z[((i$var71 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, theta[i$var71], noTopics);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var41 = 0; var41 < noTopics; var41 += 1) {
				if(!fixedFlag$sample42)
					sample42(var41);
			}
			for(int var56 = 0; var56 < length$documents.length; var56 += 1) {
				if(!fixedFlag$sample58)
					sample58(var56);
			}
			for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
				for(int j = 0; j < length$documents[i$var71]; j += 1) {
					if(!fixedFlag$sample90)
						sample90(i$var71, j);
				}
			}
		} else {
			for(int i$var71 = (length$documents.length - ((((length$documents.length - 1) - 0) % 1) + 1)); i$var71 >= ((0 - 1) + 1); i$var71 -= 1) {
				for(int j = (length$documents[i$var71] - ((((length$documents[i$var71] - 1) - 0) % 1) + 1)); j >= ((0 - 1) + 1); j -= 1) {
					if(!fixedFlag$sample90)
						sample90(i$var71, j);
				}
			}
			for(int var56 = (length$documents.length - ((((length$documents.length - 1) - 0) % 1) + 1)); var56 >= ((0 - 1) + 1); var56 -= 1) {
				if(!fixedFlag$sample58)
					sample58(var56);
			}
			for(int var41 = (noTopics - ((((noTopics - 1) - 0) % 1) + 1)); var41 >= ((0 - 1) + 1); var41 -= 1) {
				if(!fixedFlag$sample42)
					sample42(var41);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		for(int i$var14 = 0; i$var14 < noTopics; i$var14 += 1)
			alpha[i$var14] = 0.1;
		for(int i$var27 = 0; i$var27 < vocabSize; i$var27 += 1)
			beta[i$var27] = 0.1;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var30 = 0.0;
		logProbability$phi = 0.0;
		if(!fixedProbFlag$sample42)
			logProbability$var42 = 0.0;
		logProbability$var44 = 0.0;
		logProbability$theta = 0.0;
		if(!fixedProbFlag$sample58)
			logProbability$var57 = 0.0;
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
			for(int j = 0; j < length$documents[i$var71]; j += 1)
				logProbability$var87[((i$var71 - 0) / 1)][((j - 0) / 1)] = 0.0;
		}
		logProbability$z = 0.0;
		if(!fixedProbFlag$sample90) {
			for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
				for(int j = 0; j < length$documents[i$var71]; j += 1)
					logProbability$sample90[((i$var71 - 0) / 1)][((j - 0) / 1)] = 0.0;
			}
		}
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
			for(int j = 0; j < length$documents[i$var71]; j += 1)
				logProbability$var90[((i$var71 - 0) / 1)][((j - 0) / 1)] = 0.0;
		}
		logProbability$w = 0.0;
		if(!fixedProbFlag$sample93) {
			for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
				for(int j = 0; j < length$documents[i$var71]; j += 1)
					logProbability$sample93[((i$var71 - 0) / 1)][((j - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample42)
			logProbabilityValue$sample42();
		if(fixedFlag$sample58)
			logProbabilityValue$sample58();
		if(fixedFlag$sample90)
			logProbabilityValue$sample90();
		logProbabilityValue$sample93();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample42();
		logProbabilityValue$sample58();
		logProbabilityValue$sample90();
		logProbabilityValue$sample93();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample42();
		logProbabilityValue$sample58();
		logProbabilityValue$sample90();
		logProbabilityValue$sample93();
	}

	@Override
	public final void logProbabilityGeneration() {
		for(int var41 = 0; var41 < noTopics; var41 += 1) {
			double[] var42 = phi[var41];
			if(!fixedFlag$sample42)
				DistributionSampling.sampleDirichlet(RNG$, beta, vocabSize, var42);
		}
		for(int var56 = 0; var56 < length$documents.length; var56 += 1) {
			double[] var57 = theta[var56];
			if(!fixedFlag$sample58)
				DistributionSampling.sampleDirichlet(RNG$, alpha, noTopics, var57);
		}
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
			for(int j = 0; j < length$documents[i$var71]; j += 1) {
				if(!fixedFlag$sample90)
					z[((i$var71 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, theta[i$var71], noTopics);
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
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
		     + "model LDATest(int noTopics, int vocabSize, int[][] documents) {\n"
		     + "\n"
		     + "    double[] alpha = new double[noTopics];\n"
		     + "    for(int i:[0..noTopics))\n"
		     + "        alpha[i] = 0.1;\n"
		     + "\n"
		     + "    double[] beta = new double[vocabSize];\n"
		     + "    for(int i:[0..vocabSize))\n"
		     + "        beta[i] = 0.1;\n"
		     + "\n"
		     + "    double[][] phi = dirichlet(beta).sample(noTopics);\n"
		     + "    double[][] theta = dirichlet(alpha).sample(documents.length);\n"
		     + "    int[][] w = new int[documents.length][];\n"
		     + "\n"
		     + "    for(int i:[0..documents.length)) {\n"
		     + "        int[] t = new int[documents[i].length];\n"
		     + "        for(int j:[0..documents[i].length)) {\n"
		     + "            int z = categorical(theta[i]).sample();\n"
		     + "            t[j] = categorical(phi[z]).sample();\n"
		     + "        }\n"
		     + "        w[i] = t;\n"
		     + "    }\n"
		     + "\n"
		     + "    w.observe(documents);\n"
		     + "}\n"
		     + "";
	}
}