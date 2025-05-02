package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LDATest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements LDATest$CoreInterface {
	private double[] alpha;
	private double[] beta;
	private double[][] cv$var42$countGlobal;
	private double[][] cv$var57$countGlobal;
	private double[][] cv$var88$stateProbabilityGlobal;
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
	public final boolean get$fixedFlag$sample42() {
		return fixedFlag$sample42;
	}

	@Override
	public final void set$fixedFlag$sample42(boolean cv$value) {
		fixedFlag$sample42 = cv$value;
		fixedProbFlag$sample42 = (cv$value && fixedProbFlag$sample42);
		fixedProbFlag$sample93 = (cv$value && fixedProbFlag$sample93);
	}

	@Override
	public final boolean get$fixedFlag$sample58() {
		return fixedFlag$sample58;
	}

	@Override
	public final void set$fixedFlag$sample58(boolean cv$value) {
		fixedFlag$sample58 = cv$value;
		fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
		fixedProbFlag$sample90 = (cv$value && fixedProbFlag$sample90);
	}

	@Override
	public final boolean get$fixedFlag$sample90() {
		return fixedFlag$sample90;
	}

	@Override
	public final void set$fixedFlag$sample90(boolean cv$value) {
		fixedFlag$sample90 = cv$value;
		fixedProbFlag$sample90 = (cv$value && fixedProbFlag$sample90);
		fixedProbFlag$sample93 = (cv$value && fixedProbFlag$sample93);
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
			double cv$sampleAccumulator = 0.0;
			for(int var41 = 0; var41 < noTopics; var41 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(phi[var41], beta, vocabSize));
			logProbability$var30 = cv$sampleAccumulator;
			logProbability$var42 = cv$sampleAccumulator;
			logProbability$phi = (logProbability$phi + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample42 = fixedFlag$sample42;
		} else {
			logProbability$var30 = logProbability$var42;
			logProbability$phi = (logProbability$phi + logProbability$var42);
			logProbability$$model = (logProbability$$model + logProbability$var42);
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var42);
		}
	}

	private final void logProbabilityValue$sample58() {
		if(!fixedProbFlag$sample58) {
			double cv$sampleAccumulator = 0.0;
			for(int var56 = 0; var56 < length$documents.length; var56 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(theta[var56], alpha, noTopics));
			logProbability$var44 = cv$sampleAccumulator;
			logProbability$var57 = cv$sampleAccumulator;
			logProbability$theta = (logProbability$theta + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample58 = fixedFlag$sample58;
		} else {
			logProbability$var44 = logProbability$var57;
			logProbability$theta = (logProbability$theta + logProbability$var57);
			logProbability$$model = (logProbability$$model + logProbability$var57);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var57);
		}
	}

	private final void logProbabilityValue$sample90() {
		if(!fixedProbFlag$sample90) {
			double cv$accumulator = 0.0;
			for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
				for(int j = 0; j < length$documents[i$var71]; j += 1) {
					int cv$sampleValue = z[i$var71][j];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noTopics))?Math.log(theta[i$var71][cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var87[i$var71][j] = cv$distributionAccumulator;
					logProbability$sample90[i$var71][j] = cv$distributionAccumulator;
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
					double cv$rvAccumulator = logProbability$sample90[i$var71][j];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var87[i$var71][j] = cv$rvAccumulator;
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
					int cv$sampleValue = w[i$var71][j];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < vocabSize))?Math.log(phi[z[i$var71][j]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var90[i$var71][j] = cv$distributionAccumulator;
					logProbability$sample93[i$var71][j] = cv$distributionAccumulator;
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
					double cv$rvAccumulator = logProbability$sample93[i$var71][j];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var90[i$var71][j] = cv$rvAccumulator;
				}
			}
			logProbability$w = (logProbability$w + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample42(int var41, int threadID$cv$var41, Rng RNG$) {
		double[] cv$countLocal = cv$var42$countGlobal[threadID$cv$var41];
		for(int cv$loopIndex = 0; cv$loopIndex < vocabSize; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
			for(int j = 0; j < length$documents[i$var71]; j += 1) {
				if((var41 == z[i$var71][j]))
					cv$countLocal[w[i$var71][j]] = (cv$countLocal[w[i$var71][j]] + 1.0);
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, beta, cv$countLocal, phi[var41], vocabSize);
	}

	private final void sample58(int var56, int threadID$cv$var56, Rng RNG$) {
		double[] cv$countLocal = cv$var57$countGlobal[threadID$cv$var56];
		for(int cv$loopIndex = 0; cv$loopIndex < noTopics; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		for(int j = 0; j < length$documents[var56]; j += 1)
			cv$countLocal[z[var56][j]] = (cv$countLocal[z[var56][j]] + 1.0);
		Conjugates.sampleConjugateDirichletCategorical(RNG$, alpha, cv$countLocal, theta[var56], noTopics);
	}

	private final void sample90(int i$var71, int j, int threadID$cv$j, Rng RNG$) {
		int cv$numNumStates = Math.max(0, noTopics);
		double[] cv$stateProbabilityLocal = cv$var88$stateProbabilityGlobal[threadID$cv$j];
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			z[i$var71][j] = cv$valuePos;
			cv$stateProbabilityLocal[cv$valuePos] = ((((0.0 <= w[i$var71][j]) && (w[i$var71][j] < vocabSize))?Math.log(phi[cv$valuePos][w[i$var71][j]]):Double.NEGATIVE_INFINITY) + ((cv$valuePos < noTopics)?Math.log(theta[i$var71][cv$valuePos]):Double.NEGATIVE_INFINITY));
		}
		double cv$logSum;
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
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
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
		z[i$var71][j] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates);
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$threadCount = threadCount();
			cv$var42$countGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var42$countGlobal[cv$index] = new double[vocabSize];
		}
		{
			int cv$threadCount = threadCount();
			cv$var57$countGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var57$countGlobal[cv$index] = new double[noTopics];
		}
		int cv$threadCount = threadCount();
		cv$var88$stateProbabilityGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var88$stateProbabilityGlobal[cv$index] = new double[noTopics];
	}

	@Override
	public final void allocator() {
		alpha = new double[noTopics];
		beta = new double[vocabSize];
		if(!fixedFlag$sample42) {
			phi = new double[noTopics][];
			for(int var41 = 0; var41 < noTopics; var41 += 1)
				phi[var41] = new double[vocabSize];
		}
		if(!fixedFlag$sample58) {
			theta = new double[length$documents.length][];
			for(int var56 = 0; var56 < length$documents.length; var56 += 1)
				theta[var56] = new double[noTopics];
		}
		w = new int[length$documents.length][];
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1)
			w[i$var71] = new int[length$documents[i$var71]];
		if(!fixedFlag$sample90) {
			z = new int[length$documents.length][];
			for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1)
				z[i$var71] = new int[length$documents[i$var71]];
		}
		logProbability$var87 = new double[length$documents.length][];
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1)
			logProbability$var87[i$var71] = new double[length$documents[i$var71]];
		logProbability$sample90 = new double[length$documents.length][];
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1)
			logProbability$sample90[i$var71] = new double[length$documents[i$var71]];
		logProbability$var90 = new double[length$documents.length][];
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1)
			logProbability$var90[i$var71] = new double[length$documents[i$var71]];
		logProbability$sample93 = new double[length$documents.length][];
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1)
			logProbability$sample93[i$var71] = new double[length$documents[i$var71]];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample42)
			parallelFor(RNG$, 0, noTopics, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, beta, vocabSize, phi[var41]);
				}
			);

		if(!fixedFlag$sample58)
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$var56, int forEnd$var56, int threadID$var56, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var56 = forStart$var56; var56 < forEnd$var56; var56 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, alpha, noTopics, theta[var56]);
				}
			);

		parallelFor(RNG$, 0, length$documents.length, 1,
			(int forStart$index$i$var71, int forEnd$index$i$var71, int threadID$index$i$var71, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var71 = forStart$index$i$var71; index$i$var71 < forEnd$index$i$var71; index$i$var71 += 1) {
						int i$var71 = index$i$var71;
						int threadID$i$var71 = threadID$index$i$var71;
						int[] t = w[i$var71];
						parallelFor(RNG$1, 0, length$documents[i$var71], 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j = forStart$j; j < forEnd$j; j += 1) {
										if(!fixedFlag$sample90)
											z[i$var71][j] = DistributionSampling.sampleCategorical(RNG$2, theta[i$var71], noTopics);
										t[j] = DistributionSampling.sampleCategorical(RNG$2, phi[z[i$var71][j]], vocabSize);
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample42)
			parallelFor(RNG$, 0, noTopics, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, beta, vocabSize, phi[var41]);
				}
			);

		if(!fixedFlag$sample58)
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$var56, int forEnd$var56, int threadID$var56, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var56 = forStart$var56; var56 < forEnd$var56; var56 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, alpha, noTopics, theta[var56]);
				}
			);

		if(!fixedFlag$sample90)
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$index$i$var71, int forEnd$index$i$var71, int threadID$index$i$var71, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i$var71 = forStart$index$i$var71; index$i$var71 < forEnd$index$i$var71; index$i$var71 += 1) {
							int i$var71 = index$i$var71;
							int threadID$i$var71 = threadID$index$i$var71;
							parallelFor(RNG$1, 0, length$documents[i$var71], 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1)
											z[i$var71][j] = DistributionSampling.sampleCategorical(RNG$2, theta[i$var71], noTopics);
								}
							);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample42)
			parallelFor(RNG$, 0, noTopics, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, beta, vocabSize, phi[var41]);
				}
			);

		if(!fixedFlag$sample58)
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$var56, int forEnd$var56, int threadID$var56, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var56 = forStart$var56; var56 < forEnd$var56; var56 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, alpha, noTopics, theta[var56]);
				}
			);

		if(!fixedFlag$sample90)
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$index$i$var71, int forEnd$index$i$var71, int threadID$index$i$var71, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i$var71 = forStart$index$i$var71; index$i$var71 < forEnd$index$i$var71; index$i$var71 += 1) {
							int i$var71 = index$i$var71;
							int threadID$i$var71 = threadID$index$i$var71;
							parallelFor(RNG$1, 0, length$documents[i$var71], 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1)
											z[i$var71][j] = DistributionSampling.sampleCategorical(RNG$2, theta[i$var71], noTopics);
								}
							);
						}
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample42)
				parallelFor(RNG$, 0, noTopics, 1,
					(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
								sample42(var41, threadID$var41, RNG$1);
					}
				);

			if(!fixedFlag$sample58)
				parallelFor(RNG$, 0, length$documents.length, 1,
					(int forStart$var56, int forEnd$var56, int threadID$var56, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var56 = forStart$var56; var56 < forEnd$var56; var56 += 1)
								sample58(var56, threadID$var56, RNG$1);
					}
				);

			if(!fixedFlag$sample90)
				parallelFor(RNG$, 0, length$documents.length, 1,
					(int forStart$index$i$var71, int forEnd$index$i$var71, int threadID$index$i$var71, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int index$i$var71 = forStart$index$i$var71; index$i$var71 < forEnd$index$i$var71; index$i$var71 += 1) {
								int i$var71 = index$i$var71;
								int threadID$i$var71 = threadID$index$i$var71;
								parallelFor(RNG$1, 0, length$documents[i$var71], 1,
									(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int j = forStart$j; j < forEnd$j; j += 1)
												sample90(i$var71, j, threadID$j, RNG$2);
									}
								);
							}
					}
				);

		} else {
			if(!fixedFlag$sample90)
				parallelFor(RNG$, 0, length$documents.length, 1,
					(int forStart$index$i$var71, int forEnd$index$i$var71, int threadID$index$i$var71, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int index$i$var71 = forStart$index$i$var71; index$i$var71 < forEnd$index$i$var71; index$i$var71 += 1) {
								int i$var71 = index$i$var71;
								int threadID$i$var71 = threadID$index$i$var71;
								parallelFor(RNG$1, 0, length$documents[i$var71], 1,
									(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int j = forStart$j; j < forEnd$j; j += 1)
												sample90(i$var71, j, threadID$j, RNG$2);
									}
								);
							}
					}
				);

			if(!fixedFlag$sample58)
				parallelFor(RNG$, 0, length$documents.length, 1,
					(int forStart$var56, int forEnd$var56, int threadID$var56, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var56 = forStart$var56; var56 < forEnd$var56; var56 += 1)
								sample58(var56, threadID$var56, RNG$1);
					}
				);

			if(!fixedFlag$sample42)
				parallelFor(RNG$, 0, noTopics, 1,
					(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
								sample42(var41, threadID$var41, RNG$1);
					}
				);

		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, noTopics, 1,
			(int forStart$i$var14, int forEnd$i$var14, int threadID$i$var14, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var14 = forStart$i$var14; i$var14 < forEnd$i$var14; i$var14 += 1)
						alpha[i$var14] = 0.1;
			}
		);
		parallelFor(RNG$, 0, vocabSize, 1,
			(int forStart$i$var27, int forEnd$i$var27, int threadID$i$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var27 = forStart$i$var27; i$var27 < forEnd$i$var27; i$var27 += 1)
						beta[i$var27] = 0.1;
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var30 = Double.NaN;
		logProbability$phi = 0.0;
		if(!fixedProbFlag$sample42)
			logProbability$var42 = Double.NaN;
		logProbability$var44 = Double.NaN;
		logProbability$theta = 0.0;
		if(!fixedProbFlag$sample58)
			logProbability$var57 = Double.NaN;
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
			for(int j = 0; j < length$documents[i$var71]; j += 1)
				logProbability$var87[i$var71][j] = Double.NaN;
		}
		logProbability$z = 0.0;
		if(!fixedProbFlag$sample90) {
			for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
				for(int j = 0; j < length$documents[i$var71]; j += 1)
					logProbability$sample90[i$var71][j] = Double.NaN;
			}
		}
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
			for(int j = 0; j < length$documents[i$var71]; j += 1)
				logProbability$var90[i$var71][j] = Double.NaN;
		}
		logProbability$w = 0.0;
		if(!fixedProbFlag$sample93) {
			for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
				for(int j = 0; j < length$documents[i$var71]; j += 1)
					logProbability$sample93[i$var71][j] = Double.NaN;
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
		if(!fixedFlag$sample42)
			parallelFor(RNG$, 0, noTopics, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, beta, vocabSize, phi[var41]);
				}
			);

		if(!fixedFlag$sample58)
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$var56, int forEnd$var56, int threadID$var56, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var56 = forStart$var56; var56 < forEnd$var56; var56 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, alpha, noTopics, theta[var56]);
				}
			);

		if(!fixedFlag$sample90)
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$index$i$var71, int forEnd$index$i$var71, int threadID$index$i$var71, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i$var71 = forStart$index$i$var71; index$i$var71 < forEnd$index$i$var71; index$i$var71 += 1) {
							int i$var71 = index$i$var71;
							int threadID$i$var71 = threadID$index$i$var71;
							parallelFor(RNG$1, 0, length$documents[i$var71], 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1)
											z[i$var71][j] = DistributionSampling.sampleCategorical(RNG$2, theta[i$var71], noTopics);
								}
							);
						}
				}
			);

		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = w.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = documents[cv$index1];
			int[] cv$target2 = w[cv$index1];
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