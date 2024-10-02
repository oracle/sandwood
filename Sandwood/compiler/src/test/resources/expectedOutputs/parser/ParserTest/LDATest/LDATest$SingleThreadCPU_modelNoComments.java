package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LDATest$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements LDATest$CoreInterface {
	private double[] alpha;
	private double[] beta;
	private double[] cv$var46$countGlobal;
	private double[] cv$var61$countGlobal;
	private double[] cv$var94$stateProbabilityGlobal;
	private int[][] documents;
	private boolean fixedFlag$sample102 = false;
	private boolean fixedFlag$sample105 = false;
	private boolean fixedFlag$sample47 = false;
	private boolean fixedFlag$sample64 = false;
	private boolean fixedProbFlag$sample102 = false;
	private boolean fixedProbFlag$sample105 = false;
	private boolean fixedProbFlag$sample47 = false;
	private boolean fixedProbFlag$sample64 = false;
	private int[] length$documents;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$phi;
	private double[][] logProbability$sample102;
	private double[][] logProbability$sample105;
	private double logProbability$theta;
	private double logProbability$var34;
	private double logProbability$var46;
	private double logProbability$var48;
	private double logProbability$var61;
	private double[][] logProbability$var93;
	private double[][] logProbability$var96;
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
	public final boolean get$fixedFlag$sample102() {
		return fixedFlag$sample102;
	}

	@Override
	public final void set$fixedFlag$sample102(boolean cv$value) {
		fixedFlag$sample102 = cv$value;
		fixedProbFlag$sample102 = (fixedFlag$sample102 && fixedProbFlag$sample102);
		fixedProbFlag$sample105 = (fixedFlag$sample102 && fixedProbFlag$sample105);
	}

	@Override
	public final boolean get$fixedFlag$sample105() {
		return fixedFlag$sample105;
	}

	@Override
	public final void set$fixedFlag$sample105(boolean cv$value) {
		fixedFlag$sample105 = cv$value;
		fixedProbFlag$sample105 = (fixedFlag$sample105 && fixedProbFlag$sample105);
	}

	@Override
	public final boolean get$fixedFlag$sample47() {
		return fixedFlag$sample47;
	}

	@Override
	public final void set$fixedFlag$sample47(boolean cv$value) {
		fixedFlag$sample47 = cv$value;
		fixedProbFlag$sample47 = (fixedFlag$sample47 && fixedProbFlag$sample47);
		fixedProbFlag$sample105 = (fixedFlag$sample47 && fixedProbFlag$sample105);
	}

	@Override
	public final boolean get$fixedFlag$sample64() {
		return fixedFlag$sample64;
	}

	@Override
	public final void set$fixedFlag$sample64(boolean cv$value) {
		fixedFlag$sample64 = cv$value;
		fixedProbFlag$sample64 = (fixedFlag$sample64 && fixedProbFlag$sample64);
		fixedProbFlag$sample102 = (fixedFlag$sample64 && fixedProbFlag$sample102);
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
		fixedProbFlag$sample47 = false;
		fixedProbFlag$sample105 = false;
	}

	@Override
	public final double[][] get$theta() {
		return theta;
	}

	@Override
	public final void set$theta(double[][] cv$value) {
		theta = cv$value;
		setFlag$theta = true;
		fixedProbFlag$sample64 = false;
		fixedProbFlag$sample102 = false;
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
		fixedProbFlag$sample105 = false;
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

	private final void logProbabilityValue$sample102() {
		if(!fixedProbFlag$sample102) {
			double cv$accumulator = 0.0;
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
				for(int j = 0; j < length$documents[i$var75]; j += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						int cv$sampleValue = z[((i$var75 - 0) / 1)][((j - 0) / 1)];
						{
							{
								double[] var92 = theta[i$var75];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var92.length))?Math.log(var92[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var93[((i$var75 - 0) / 1)][((j - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample102[((i$var75 - 0) / 1)][((j - 0) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$z = (logProbability$z + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample102)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample102 = (fixedFlag$sample102 && fixedFlag$sample64);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
				for(int j = 0; j < length$documents[i$var75]; j += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample102[((i$var75 - 0) / 1)][((j - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var93[((i$var75 - 0) / 1)][((j - 0) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$z = (logProbability$z + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample102)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample105() {
		if(!fixedProbFlag$sample105) {
			double cv$accumulator = 0.0;
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
				for(int j = 0; j < length$documents[i$var75]; j += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						int cv$sampleValue = w[i$var75][j];
						{
							{
								double[] var95 = phi[z[((i$var75 - 0) / 1)][((j - 0) / 1)]];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var95.length))?Math.log(var95[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var96[((i$var75 - 0) / 1)][((j - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample105[((i$var75 - 0) / 1)][((j - 0) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$w = (logProbability$w + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample105 = ((fixedFlag$sample105 && fixedFlag$sample47) && fixedFlag$sample102);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
				for(int j = 0; j < length$documents[i$var75]; j += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample105[((i$var75 - 0) / 1)][((j - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var96[((i$var75 - 0) / 1)][((j - 0) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$w = (logProbability$w + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample47() {
		if(!fixedProbFlag$sample47) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var45 = 0; var45 < noTopics; var45 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = phi[var45];
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
			logProbability$var34 = cv$sampleAccumulator;
			logProbability$var46 = cv$sampleAccumulator;
			logProbability$phi = (logProbability$phi + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample47 = fixedFlag$sample47;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var46;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var34 = cv$rvAccumulator;
			logProbability$phi = (logProbability$phi + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample64() {
		if(!fixedProbFlag$sample64) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var60 = 0; var60 < length$documents.length; var60 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = theta[var60];
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
			logProbability$var48 = cv$sampleAccumulator;
			logProbability$var61 = cv$sampleAccumulator;
			logProbability$theta = (logProbability$theta + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample64)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample64 = fixedFlag$sample64;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var61;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var48 = cv$rvAccumulator;
			logProbability$theta = (logProbability$theta + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample64)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample102(int i$var75, int j) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, noTopics);
		}
		double[] cv$stateProbabilityLocal = cv$var94$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			z[((i$var75 - 0) / 1)][((j - 0) / 1)] = cv$currentValue;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var92;
				{
					double[] var92 = theta[i$var75];
					cv$temp$0$var92 = var92;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var92.length))?Math.log(cv$temp$0$var92[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						{
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							{
								{
									{
										{
											double[] cv$temp$1$var95;
											{
												double[] var95 = phi[cv$currentValue];
												cv$temp$1$var95 = var95;
											}
											if(((Math.log(1.0) + (((0.0 <= w[i$var75][j]) && (w[i$var75][j] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[w[i$var75][j]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= w[i$var75][j]) && (w[i$var75][j] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[w[i$var75][j]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
											else {
												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= w[i$var75][j]) && (w[i$var75][j] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[w[i$var75][j]]):Double.NEGATIVE_INFINITY));
												else
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= w[i$var75][j]) && (w[i$var75][j] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[w[i$var75][j]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= w[i$var75][j]) && (w[i$var75][j] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[w[i$var75][j]]):Double.NEGATIVE_INFINITY)));
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
		z[((i$var75 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
	}

	private final void sample47(int var45) {
		double[] cv$targetLocal = phi[var45];
		double[] cv$countLocal = cv$var46$countGlobal;
		int cv$arrayLength = vocabSize;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
						for(int j = 0; j < length$documents[i$var75]; j += 1) {
							if((var45 == z[((i$var75 - 0) / 1)][((j - 0) / 1)])) {
								{
									{
										{
											{
												{
													cv$countLocal[w[i$var75][j]] = (cv$countLocal[w[i$var75][j]] + 1.0);
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

	private final void sample64(int var60) {
		double[] cv$targetLocal = theta[var60];
		double[] cv$countLocal = cv$var61$countGlobal;
		int cv$arrayLength = noTopics;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
						if((var60 == i$var75)) {
							for(int j = 0; j < length$documents[i$var75]; j += 1)
								cv$countLocal[z[((i$var75 - 0) / 1)][((j - 0) / 1)]] = (cv$countLocal[z[((i$var75 - 0) / 1)][((j - 0) / 1)]] + 1.0);
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, alpha, cv$countLocal, cv$targetLocal);
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			for(int var45 = 0; var45 < noTopics; var45 += 1)
				cv$max = Math.max(cv$max, vocabSize);
			cv$var46$countGlobal = new double[cv$max];
		}
		{
			int cv$max = 0;
			for(int var60 = 0; var60 < length$documents.length; var60 += 1)
				cv$max = Math.max(cv$max, noTopics);
			cv$var61$countGlobal = new double[cv$max];
		}
		{
			int cv$var62$max = noTopics;
			cv$var94$stateProbabilityGlobal = new double[cv$var62$max];
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
				for(int var45 = 0; var45 < noTopics; var45 += 1)
					phi[var45] = new double[vocabSize];
			}
		}
		if(!setFlag$theta) {
			{
				theta = new double[length$documents.length][];
				for(int var60 = 0; var60 < length$documents.length; var60 += 1)
					theta[var60] = new double[noTopics];
			}
		}
		if(!setFlag$w) {
			{
				w = new int[length$documents.length][];
				for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1)
					w[i$var75] = new int[length$documents[i$var75]];
			}
		}
		if(!setFlag$z) {
			{
				z = new int[((((length$documents.length - 1) - 0) / 1) + 1)][];
				for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1)
					z[((i$var75 - 0) / 1)] = new int[((((length$documents[i$var75] - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$var93 = new double[((((length$documents.length - 1) - 0) / 1) + 1)][];
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1)
				logProbability$var93[((i$var75 - 0) / 1)] = new double[((((length$documents[i$var75] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample102 = new double[((((length$documents.length - 1) - 0) / 1) + 1)][];
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1)
				logProbability$sample102[((i$var75 - 0) / 1)] = new double[((((length$documents[i$var75] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var96 = new double[((((length$documents.length - 1) - 0) / 1) + 1)][];
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1)
				logProbability$var96[((i$var75 - 0) / 1)] = new double[((((length$documents[i$var75] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample105 = new double[((((length$documents.length - 1) - 0) / 1) + 1)][];
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1)
				logProbability$sample105[((i$var75 - 0) / 1)] = new double[((((length$documents[i$var75] - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int var45 = 0; var45 < noTopics; var45 += 1) {
			double[] var46 = phi[var45];
			if(!fixedFlag$sample47)
				DistributionSampling.sampleDirichlet(RNG$, beta, var46);
		}
		for(int var60 = 0; var60 < length$documents.length; var60 += 1) {
			double[] var61 = theta[var60];
			if(!fixedFlag$sample64)
				DistributionSampling.sampleDirichlet(RNG$, alpha, var61);
		}
		for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
			int[] t = w[i$var75];
			for(int j = 0; j < length$documents[i$var75]; j += 1) {
				if(!fixedFlag$sample102)
					z[((i$var75 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, theta[i$var75]);
				if(!fixedFlag$sample105)
					t[j] = DistributionSampling.sampleCategorical(RNG$, phi[z[((i$var75 - 0) / 1)][((j - 0) / 1)]]);
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var45 = 0; var45 < noTopics; var45 += 1) {
			double[] var46 = phi[var45];
			if(!fixedFlag$sample47)
				DistributionSampling.sampleDirichlet(RNG$, beta, var46);
		}
		for(int var60 = 0; var60 < length$documents.length; var60 += 1) {
			double[] var61 = theta[var60];
			if(!fixedFlag$sample64)
				DistributionSampling.sampleDirichlet(RNG$, alpha, var61);
		}
		for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
			for(int j = 0; j < length$documents[i$var75]; j += 1) {
				if(!fixedFlag$sample102)
					z[((i$var75 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, theta[i$var75]);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var45 = 0; var45 < noTopics; var45 += 1) {
			double[] var46 = phi[var45];
			if(!fixedFlag$sample47)
				DistributionSampling.sampleDirichlet(RNG$, beta, var46);
		}
		for(int var60 = 0; var60 < length$documents.length; var60 += 1) {
			double[] var61 = theta[var60];
			if(!fixedFlag$sample64)
				DistributionSampling.sampleDirichlet(RNG$, alpha, var61);
		}
		for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
			for(int j = 0; j < length$documents[i$var75]; j += 1) {
				if(!fixedFlag$sample102)
					z[((i$var75 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, theta[i$var75]);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var45 = 0; var45 < noTopics; var45 += 1) {
				if(!fixedFlag$sample47)
					sample47(var45);
			}
			for(int var60 = 0; var60 < length$documents.length; var60 += 1) {
				if(!fixedFlag$sample64)
					sample64(var60);
			}
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
				for(int j = 0; j < length$documents[i$var75]; j += 1) {
					if(!fixedFlag$sample102)
						sample102(i$var75, j);
				}
			}
		} else {
			for(int i$var75 = (length$documents.length - ((((length$documents.length - 1) - 0) % 1) + 1)); i$var75 >= ((0 - 1) + 1); i$var75 -= 1) {
				for(int j = (length$documents[i$var75] - ((((length$documents[i$var75] - 1) - 0) % 1) + 1)); j >= ((0 - 1) + 1); j -= 1) {
					if(!fixedFlag$sample102)
						sample102(i$var75, j);
				}
			}
			for(int var60 = (length$documents.length - ((((length$documents.length - 1) - 0) % 1) + 1)); var60 >= ((0 - 1) + 1); var60 -= 1) {
				if(!fixedFlag$sample64)
					sample64(var60);
			}
			for(int var45 = (noTopics - ((((noTopics - 1) - 0) % 1) + 1)); var45 >= ((0 - 1) + 1); var45 -= 1) {
				if(!fixedFlag$sample47)
					sample47(var45);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		for(int i$var18 = 0; i$var18 < noTopics; i$var18 += 1)
			alpha[i$var18] = 0.1;
		for(int i$var31 = 0; i$var31 < vocabSize; i$var31 += 1)
			beta[i$var31] = 0.1;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var34 = 0.0;
		logProbability$phi = 0.0;
		if(!fixedProbFlag$sample47)
			logProbability$var46 = 0.0;
		logProbability$var48 = 0.0;
		logProbability$theta = 0.0;
		if(!fixedProbFlag$sample64)
			logProbability$var61 = 0.0;
		for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
			for(int j = 0; j < length$documents[i$var75]; j += 1)
				logProbability$var93[((i$var75 - 0) / 1)][((j - 0) / 1)] = 0.0;
		}
		logProbability$z = 0.0;
		if(!fixedProbFlag$sample102) {
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
				for(int j = 0; j < length$documents[i$var75]; j += 1)
					logProbability$sample102[((i$var75 - 0) / 1)][((j - 0) / 1)] = 0.0;
			}
		}
		for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
			for(int j = 0; j < length$documents[i$var75]; j += 1)
				logProbability$var96[((i$var75 - 0) / 1)][((j - 0) / 1)] = 0.0;
		}
		logProbability$w = 0.0;
		if(!fixedProbFlag$sample105) {
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
				for(int j = 0; j < length$documents[i$var75]; j += 1)
					logProbability$sample105[((i$var75 - 0) / 1)][((j - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample47)
			logProbabilityValue$sample47();
		if(fixedFlag$sample64)
			logProbabilityValue$sample64();
		if(fixedFlag$sample102)
			logProbabilityValue$sample102();
		logProbabilityValue$sample105();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample47();
		logProbabilityValue$sample64();
		logProbabilityValue$sample102();
		logProbabilityValue$sample105();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample47();
		logProbabilityValue$sample64();
		logProbabilityValue$sample102();
		logProbabilityValue$sample105();
	}

	@Override
	public final void logProbabilityGeneration() {
		for(int var45 = 0; var45 < noTopics; var45 += 1) {
			double[] var46 = phi[var45];
			if(!fixedFlag$sample47)
				DistributionSampling.sampleDirichlet(RNG$, beta, var46);
		}
		for(int var60 = 0; var60 < length$documents.length; var60 += 1) {
			double[] var61 = theta[var60];
			if(!fixedFlag$sample64)
				DistributionSampling.sampleDirichlet(RNG$, alpha, var61);
		}
		for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
			for(int j = 0; j < length$documents[i$var75]; j += 1) {
				if(!fixedFlag$sample102)
					z[((i$var75 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, theta[i$var75]);
			}
		}
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