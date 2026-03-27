package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class LDATest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements LDATest$CoreInterface {
	private double[] alpha;
	private double[] beta;
	private boolean[] constrainedFlag$sample42;
	private boolean[] constrainedFlag$sample58;
	private boolean[][] constrainedFlag$sample90;
	private double[][] cv$var42$countGlobal;
	private double[][] cv$var57$countGlobal;
	private double[][] cv$var88$stateProbabilityGlobal;
	private int[][] documents;
	private boolean fixedFlag$sample42 = false;
	private boolean fixedFlag$sample58 = false;
	private boolean fixedProbFlag$sample42 = false;
	private boolean fixedProbFlag$sample58 = false;
	private int[] length$documents;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$phi;
	private double[][] logProbability$sample90;
	private double[][] logProbability$sample93;
	private double logProbability$theta;
	private double logProbability$var42;
	private double logProbability$var57;
	private double logProbability$w;
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
	public final void set$documents(int[][] cv$value, boolean allocated$) {
		documents = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample42() {
		return fixedFlag$sample42;
	}

	@Override
	public final void set$fixedFlag$sample42(boolean cv$value, boolean allocated$) {
		fixedFlag$sample42 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample42$1 = 0; index$constrainedFlag$sample42$1 < constrainedFlag$sample42.length; index$constrainedFlag$sample42$1 += 1)
				constrainedFlag$sample42[index$constrainedFlag$sample42$1] = true;
		}
		fixedProbFlag$sample42 = (cv$value && fixedProbFlag$sample42);
	}

	@Override
	public final boolean get$fixedFlag$sample58() {
		return fixedFlag$sample58;
	}

	@Override
	public final void set$fixedFlag$sample58(boolean cv$value, boolean allocated$) {
		fixedFlag$sample58 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample58$1 = 0; index$constrainedFlag$sample58$1 < constrainedFlag$sample58.length; index$constrainedFlag$sample58$1 += 1)
				constrainedFlag$sample58[index$constrainedFlag$sample58$1] = true;
		}
		fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
	}

	@Override
	public final int[] get$length$documents() {
		return length$documents;
	}

	@Override
	public final void set$length$documents(int[] cv$value, boolean allocated$) {
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
	public final int get$noTopics() {
		return noTopics;
	}

	@Override
	public final void set$noTopics(int cv$value, boolean allocated$) {
		noTopics = cv$value;
	}

	@Override
	public final double[][] get$phi() {
		return phi;
	}

	@Override
	public final void set$phi(double[][] cv$value, boolean allocated$) {
		phi = cv$value;
		fixedProbFlag$sample42 = false;
	}

	@Override
	public final double[][] get$theta() {
		return theta;
	}

	@Override
	public final void set$theta(double[][] cv$value, boolean allocated$) {
		theta = cv$value;
		fixedProbFlag$sample58 = false;
	}

	@Override
	public final int get$vocabSize() {
		return vocabSize;
	}

	@Override
	public final void set$vocabSize(int cv$value, boolean allocated$) {
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
	public final void set$z(int[][] cv$value, boolean allocated$) {
		z = cv$value;
	}

	private final void drawValueSample42(int var41, int threadID$cv$var41, Rng RNG$) {
		DistributionSampling.sampleDirichlet(RNG$, beta, vocabSize, phi[var41]);
	}

	private final void drawValueSample58(int var56, int threadID$cv$var56, Rng RNG$) {
		DistributionSampling.sampleDirichlet(RNG$, alpha, noTopics, theta[var56]);
	}

	private final void drawValueSample90(int i$var71, int j, int threadID$cv$j, Rng RNG$) {
		z[i$var71][j] = DistributionSampling.sampleCategorical(RNG$, theta[i$var71], noTopics);
	}

	private final void inferSample42(int var41, int threadID$cv$var41, Rng RNG$) {
		constrainedFlag$sample42[var41] = false;
		double[] cv$countLocal = cv$var42$countGlobal[threadID$cv$var41];
		for(int cv$loopIndex = 0; cv$loopIndex < vocabSize; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
			for(int j = 0; j < length$documents[i$var71]; j += 1) {
				if((var41 == z[i$var71][j])) {
					constrainedFlag$sample42[var41] = true;
					cv$countLocal[w[i$var71][j]] = (cv$countLocal[w[i$var71][j]] + 1.0);
				}
			}
		}
		if(constrainedFlag$sample42[var41])
			Conjugates.sampleConjugateDirichletCategorical(RNG$, beta, cv$countLocal, phi[var41], vocabSize);
	}

	private final void inferSample58(int var56, int threadID$cv$var56, Rng RNG$) {
		constrainedFlag$sample58[var56] = false;
		double[] cv$countLocal = cv$var57$countGlobal[threadID$cv$var56];
		for(int cv$loopIndex = 0; cv$loopIndex < noTopics; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		for(int j = 0; j < length$documents[var56]; j += 1) {
			if(constrainedFlag$sample90[var56][j]) {
				constrainedFlag$sample58[var56] = true;
				cv$countLocal[z[var56][j]] = (cv$countLocal[z[var56][j]] + 1.0);
			}
		}
		if(constrainedFlag$sample58[var56])
			Conjugates.sampleConjugateDirichletCategorical(RNG$, alpha, cv$countLocal, theta[var56], noTopics);
	}

	private final void inferSample90(int i$var71, int j, int threadID$cv$j, Rng RNG$) {
		constrainedFlag$sample90[i$var71][j] = false;
		int cv$numStates = Math.max(0, noTopics);
		double[] cv$stateProbabilityLocal = cv$var88$stateProbabilityGlobal[threadID$cv$j];
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			z[i$var71][j] = cv$valuePos;
			double[] var86 = theta[i$var71];
			constrainedFlag$sample90[i$var71][j] = true;
			double[] var89 = phi[cv$valuePos];
			cv$stateProbabilityLocal[cv$valuePos] = (((((((0.0 <= w[i$var71][j]) && (w[i$var71][j] < vocabSize)) && (0 < vocabSize)) && (0.0 <= var89[w[i$var71][j]])) && (var89[w[i$var71][j]] <= 1.0))?Math.log(var89[w[i$var71][j]]):Double.NEGATIVE_INFINITY) + (((((cv$valuePos < noTopics) && (0 < noTopics)) && (0.0 <= var86[cv$valuePos])) && (var86[cv$valuePos] <= 1.0))?Math.log(var86[cv$valuePos]):Double.NEGATIVE_INFINITY));
		}
		if(constrainedFlag$sample90[i$var71][j]) {
			double cv$logSum;
			double cv$lseMax = cv$stateProbabilityLocal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
			z[i$var71][j] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates);
		}
	}

	private final void logProbabilityValue$sample42() {
		if(!fixedProbFlag$sample42) {
			double cv$sampleAccumulator = 0.0;
			for(int var41 = 0; var41 < noTopics; var41 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(phi[var41], beta, vocabSize));
			logProbability$var42 = cv$sampleAccumulator;
			logProbability$phi = (logProbability$phi + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample42 = fixedFlag$sample42;
		} else {
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
			logProbability$var57 = cv$sampleAccumulator;
			logProbability$theta = (logProbability$theta + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample58 = fixedFlag$sample58;
		} else {
			logProbability$theta = (logProbability$theta + logProbability$var57);
			logProbability$$model = (logProbability$$model + logProbability$var57);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var57);
		}
	}

	private final void logProbabilityValue$sample90() {
		double cv$accumulator = 0.0;
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
			for(int j = 0; j < length$documents[i$var71]; j += 1) {
				int cv$sampleValue = z[i$var71][j];
				double[] var86 = theta[i$var71];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noTopics)) && (0 < noTopics)) && (0.0 <= var86[cv$sampleValue])) && (var86[cv$sampleValue] <= 1.0))?Math.log(var86[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample90[i$var71][j] = cv$distributionAccumulator;
			}
		}
		logProbability$$model = (logProbability$$model + cv$accumulator);
	}

	private final void logProbabilityValue$sample93() {
		double cv$accumulator = 0.0;
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
			for(int j = 0; j < length$documents[i$var71]; j += 1) {
				int cv$sampleValue = w[i$var71][j];
				double[] var89 = phi[z[i$var71][j]];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < vocabSize)) && (0 < vocabSize)) && (0.0 <= var89[cv$sampleValue])) && (var89[cv$sampleValue] <= 1.0))?Math.log(var89[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample93[i$var71][j] = cv$distributionAccumulator;
			}
		}
		logProbability$w = (logProbability$w + cv$accumulator);
		logProbability$$model = (logProbability$$model + cv$accumulator);
		logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
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
		z = new int[length$documents.length][];
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1)
			z[i$var71] = new int[length$documents[i$var71]];
		constrainedFlag$sample90 = new boolean[length$documents.length][];
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1)
			constrainedFlag$sample90[i$var71] = new boolean[length$documents[i$var71]];
		constrainedFlag$sample42 = new boolean[noTopics];
		constrainedFlag$sample58 = new boolean[length$documents.length];
		logProbability$sample90 = new double[length$documents.length][];
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1)
			logProbability$sample90[i$var71] = new double[length$documents[i$var71]];
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
	public final void forwardGenerationDistributionsNoOutputsPrime() {
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
	public final void forwardGenerationPrime() {
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
	public final void forwardGenerationValuesNoOutputsPrime() {
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
								inferSample42(var41, threadID$var41, RNG$1);
					}
				);

			if(!fixedFlag$sample58)
				parallelFor(RNG$, 0, length$documents.length, 1,
					(int forStart$var56, int forEnd$var56, int threadID$var56, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var56 = forStart$var56; var56 < forEnd$var56; var56 += 1)
								inferSample58(var56, threadID$var56, RNG$1);
					}
				);

			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$index$i$var71, int forEnd$index$i$var71, int threadID$index$i$var71, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i$var71 = forStart$index$i$var71; index$i$var71 < forEnd$index$i$var71; index$i$var71 += 1) {
							int i$var71 = index$i$var71;
							int threadID$i$var71 = threadID$index$i$var71;
							parallelFor(RNG$1, 0, length$documents[i$var71], 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1)
											inferSample90(i$var71, j, threadID$j, RNG$2);
								}
							);
						}
				}
			);
		} else {
			parallelFor(RNG$, 0, length$documents.length, 1,
				(int forStart$index$i$var71, int forEnd$index$i$var71, int threadID$index$i$var71, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i$var71 = forStart$index$i$var71; index$i$var71 < forEnd$index$i$var71; index$i$var71 += 1) {
							int i$var71 = index$i$var71;
							int threadID$i$var71 = threadID$index$i$var71;
							parallelFor(RNG$1, 0, length$documents[i$var71], 1,
								(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j = forStart$j; j < forEnd$j; j += 1)
											inferSample90(i$var71, j, threadID$j, RNG$2);
								}
							);
						}
				}
			);
			if(!fixedFlag$sample58)
				parallelFor(RNG$, 0, length$documents.length, 1,
					(int forStart$var56, int forEnd$var56, int threadID$var56, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var56 = forStart$var56; var56 < forEnd$var56; var56 += 1)
								inferSample58(var56, threadID$var56, RNG$1);
					}
				);

			if(!fixedFlag$sample42)
				parallelFor(RNG$, 0, noTopics, 1,
					(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
								inferSample42(var41, threadID$var41, RNG$1);
					}
				);

		}
		system$gibbsForward = !system$gibbsForward;
		parallelFor(RNG$, 0, noTopics, 1,
			(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
						if(!constrainedFlag$sample42[var41])
							drawValueSample42(var41, threadID$var41, RNG$1);
					}
			}
		);
		parallelFor(RNG$, 0, length$documents.length, 1,
			(int forStart$var56, int forEnd$var56, int threadID$var56, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var56 = forStart$var56; var56 < forEnd$var56; var56 += 1) {
						if(!constrainedFlag$sample58[var56])
							drawValueSample58(var56, threadID$var56, RNG$1);
					}
			}
		);
		parallelFor(RNG$, 0, length$documents.length, 1,
			(int forStart$index$i$var71, int forEnd$index$i$var71, int threadID$index$i$var71, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var71 = forStart$index$i$var71; index$i$var71 < forEnd$index$i$var71; index$i$var71 += 1) {
						int i$var71 = index$i$var71;
						int threadID$i$var71 = threadID$index$i$var71;
						parallelFor(RNG$1, 0, length$documents[i$var71], 1,
							(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j = forStart$j; j < forEnd$j; j += 1) {
										if(!constrainedFlag$sample90[i$var71][j])
											drawValueSample90(i$var71, j, threadID$j, RNG$2);
									}
							}
						);
					}
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$phi = 0.0;
		if(!fixedProbFlag$sample42)
			logProbability$var42 = Double.NaN;
		logProbability$theta = 0.0;
		if(!fixedProbFlag$sample58)
			logProbability$var57 = Double.NaN;
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
			for(int j = 0; j < length$documents[i$var71]; j += 1)
				logProbability$sample90[i$var71][j] = Double.NaN;
		}
		logProbability$w = 0.0;
		for(int i$var71 = 0; i$var71 < length$documents.length; i$var71 += 1) {
			for(int j = 0; j < length$documents[i$var71]; j += 1)
				logProbability$sample93[i$var71][j] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		for(int i$var14 = 0; i$var14 < noTopics; i$var14 += 1)
			alpha[i$var14] = 0.1;
		for(int i$var27 = 0; i$var27 < vocabSize; i$var27 += 1)
			beta[i$var27] = 0.1;
		for(int index$constrainedFlag$sample90$1 = 0; index$constrainedFlag$sample90$1 < constrainedFlag$sample90.length; index$constrainedFlag$sample90$1 += 1) {
			boolean[] cv$constrainedFlag$sample90$1 = constrainedFlag$sample90[index$constrainedFlag$sample90$1];
			for(int index$constrainedFlag$sample90$2 = 0; index$constrainedFlag$sample90$2 < cv$constrainedFlag$sample90$1.length; index$constrainedFlag$sample90$2 += 1)
				cv$constrainedFlag$sample90$1[index$constrainedFlag$sample90$2] = true;
		}
		for(int index$constrainedFlag$sample42$1 = 0; index$constrainedFlag$sample42$1 < constrainedFlag$sample42.length; index$constrainedFlag$sample42$1 += 1)
			constrainedFlag$sample42[index$constrainedFlag$sample42$1] = true;
		for(int index$constrainedFlag$sample58$1 = 0; index$constrainedFlag$sample58$1 < constrainedFlag$sample58.length; index$constrainedFlag$sample58$1 += 1)
			constrainedFlag$sample58[index$constrainedFlag$sample58$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample42)
			logProbabilityValue$sample42();
		if(fixedFlag$sample58)
			logProbabilityValue$sample58();
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