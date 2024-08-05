package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LDATest$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements LDATest$CoreInterface {
	private double[] alpha;
	private double[] beta;
	private double[] cv$var25$countGlobal;
	private double[] cv$var33$countGlobal;
	private double[] cv$var53$stateProbabilityGlobal;
	private int[][] documents;
	private boolean fixedFlag$sample26 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample59 = false;
	private boolean fixedFlag$sample62 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample59 = false;
	private boolean fixedProbFlag$sample62 = false;
	private int[] length$documents;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$phi;
	private double[][] logProbability$sample59;
	private double[][] logProbability$sample62;
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
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	@Override
	public final void set$fixedFlag$sample26(boolean cv$value) {
		fixedFlag$sample26 = cv$value;
		fixedProbFlag$sample26 = (cv$value && fixedProbFlag$sample26);
		fixedProbFlag$sample62 = (cv$value && fixedProbFlag$sample62);
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		fixedFlag$sample35 = cv$value;
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
		fixedProbFlag$sample59 = (cv$value && fixedProbFlag$sample59);
	}

	@Override
	public final boolean get$fixedFlag$sample59() {
		return fixedFlag$sample59;
	}

	@Override
	public final void set$fixedFlag$sample59(boolean cv$value) {
		fixedFlag$sample59 = cv$value;
		fixedProbFlag$sample59 = (cv$value && fixedProbFlag$sample59);
		fixedProbFlag$sample62 = (cv$value && fixedProbFlag$sample62);
	}

	@Override
	public final boolean get$fixedFlag$sample62() {
		return fixedFlag$sample62;
	}

	@Override
	public final void set$fixedFlag$sample62(boolean cv$value) {
		fixedFlag$sample62 = cv$value;
		fixedProbFlag$sample62 = (cv$value && fixedProbFlag$sample62);
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
			double cv$sampleAccumulator = 0.0;
			for(int var24 = 0; var24 < noTopics; var24 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(phi[var24], beta));
			logProbability$var20 = cv$sampleAccumulator;
			logProbability$var25 = cv$sampleAccumulator;
			logProbability$phi = (logProbability$phi + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample26 = fixedFlag$sample26;
		} else {
			logProbability$var20 = logProbability$var25;
			logProbability$phi = (logProbability$phi + logProbability$var25);
			logProbability$$model = (logProbability$$model + logProbability$var25);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var25);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double cv$sampleAccumulator = 0.0;
			for(int var32 = 0; var32 < length$documents.length; var32 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(theta[var32], alpha));
			logProbability$var27 = cv$sampleAccumulator;
			logProbability$var33 = cv$sampleAccumulator;
			logProbability$theta = (logProbability$theta + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample35 = fixedFlag$sample35;
		} else {
			logProbability$var27 = logProbability$var33;
			logProbability$theta = (logProbability$theta + logProbability$var33);
			logProbability$$model = (logProbability$$model + logProbability$var33);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var33);
		}
	}

	private final void logProbabilityValue$sample59() {
		if(!fixedProbFlag$sample59) {
			double cv$accumulator = 0.0;
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
				for(int j = 0; j < length$documents[i$var40]; j += 1) {
					double cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(z[i$var40][j], theta[i$var40]);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var52[i$var40][j] = cv$distributionAccumulator;
					logProbability$sample59[i$var40][j] = cv$distributionAccumulator;
				}
			}
			logProbability$z = (logProbability$z + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample59)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample59 = (fixedFlag$sample59 && fixedFlag$sample35);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
				for(int j = 0; j < length$documents[i$var40]; j += 1) {
					double cv$rvAccumulator = logProbability$sample59[i$var40][j];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var52[i$var40][j] = cv$rvAccumulator;
				}
			}
			logProbability$z = (logProbability$z + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample59)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample62() {
		if(!fixedProbFlag$sample62) {
			double cv$accumulator = 0.0;
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
				for(int j = 0; j < length$documents[i$var40]; j += 1) {
					double cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(w[i$var40][j], phi[z[i$var40][j]]);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var55[i$var40][j] = cv$distributionAccumulator;
					logProbability$sample62[i$var40][j] = cv$distributionAccumulator;
				}
			}
			logProbability$w = (logProbability$w + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample62 = ((fixedFlag$sample62 && fixedFlag$sample26) && fixedFlag$sample59);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
				for(int j = 0; j < length$documents[i$var40]; j += 1) {
					double cv$rvAccumulator = logProbability$sample62[i$var40][j];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var55[i$var40][j] = cv$rvAccumulator;
				}
			}
			logProbability$w = (logProbability$w + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample26(int var24) {
		for(int cv$loopIndex = 0; cv$loopIndex < vocabSize; cv$loopIndex += 1)
			cv$var25$countGlobal[cv$loopIndex] = 0.0;
		for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
			for(int j = 0; j < length$documents[i$var40]; j += 1) {
				if((var24 == z[i$var40][j]))
					cv$var25$countGlobal[w[i$var40][j]] = (cv$var25$countGlobal[w[i$var40][j]] + 1.0);
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, beta, cv$var25$countGlobal, phi[var24]);
	}

	private final void sample35(int var32) {
		for(int cv$loopIndex = 0; cv$loopIndex < noTopics; cv$loopIndex += 1)
			cv$var33$countGlobal[cv$loopIndex] = 0.0;
		for(int j = 0; j < length$documents[var32]; j += 1)
			cv$var33$countGlobal[z[var32][j]] = (cv$var33$countGlobal[z[var32][j]] + 1.0);
		Conjugates.sampleConjugateDirichletCategorical(RNG$, alpha, cv$var33$countGlobal, theta[var32]);
	}

	private final void sample59(int i$var40, int j) {
		for(int cv$valuePos = 0; cv$valuePos < noTopics; cv$valuePos += 1) {
			z[i$var40][j] = cv$valuePos;
			cv$var53$stateProbabilityGlobal[cv$valuePos] = (DistributionSampling.logProbabilityCategorical(w[i$var40][j], phi[cv$valuePos]) + DistributionSampling.logProbabilityCategorical(cv$valuePos, theta[i$var40]));
		}
		double cv$logSum;
		double cv$lseMax = cv$var53$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var53$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var53$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var53$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var53$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var53$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var53$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$var53$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var53$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var53$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var53$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		z[i$var40][j] = DistributionSampling.sampleCategorical(RNG$, cv$var53$stateProbabilityGlobal);
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			if((0 < noTopics))
				cv$max = Math.max(0, vocabSize);
			cv$var25$countGlobal = new double[cv$max];
		}
		int cv$max = 0;
		if((0 < length$documents.length))
			cv$max = Math.max(0, noTopics);
		cv$var33$countGlobal = new double[cv$max];
		cv$var53$stateProbabilityGlobal = new double[noTopics];
	}

	@Override
	public final void allocator() {
		alpha = new double[noTopics];
		beta = new double[vocabSize];
		if(!setFlag$phi) {
			phi = new double[noTopics][];
			for(int var24 = 0; var24 < noTopics; var24 += 1)
				phi[var24] = new double[vocabSize];
		}
		if(!setFlag$theta) {
			theta = new double[length$documents.length][];
			for(int var32 = 0; var32 < length$documents.length; var32 += 1)
				theta[var32] = new double[noTopics];
		}
		if(!setFlag$w) {
			w = new int[length$documents.length][];
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1)
				w[i$var40] = new int[length$documents[i$var40]];
		}
		if(!setFlag$z) {
			z = new int[length$documents.length][];
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1)
				z[i$var40] = new int[length$documents[i$var40]];
		}
		logProbability$var52 = new double[length$documents.length][];
		for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1)
			logProbability$var52[i$var40] = new double[length$documents[i$var40]];
		logProbability$sample59 = new double[length$documents.length][];
		for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1)
			logProbability$sample59[i$var40] = new double[length$documents[i$var40]];
		logProbability$var55 = new double[length$documents.length][];
		for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1)
			logProbability$var55[i$var40] = new double[length$documents[i$var40]];
		logProbability$sample62 = new double[length$documents.length][];
		for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1)
			logProbability$sample62[i$var40] = new double[length$documents[i$var40]];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample26) {
			for(int var24 = 0; var24 < noTopics; var24 += 1)
				DistributionSampling.sampleDirichlet(RNG$, beta, phi[var24]);
		}
		if(!fixedFlag$sample35) {
			for(int var32 = 0; var32 < length$documents.length; var32 += 1)
				DistributionSampling.sampleDirichlet(RNG$, alpha, theta[var32]);
		}
		for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
			int[] t = w[i$var40];
			for(int j = 0; j < length$documents[i$var40]; j += 1) {
				if(!fixedFlag$sample59)
					z[i$var40][j] = DistributionSampling.sampleCategorical(RNG$, theta[i$var40]);
				if(!fixedFlag$sample62)
					t[j] = DistributionSampling.sampleCategorical(RNG$, phi[z[i$var40][j]]);
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample26) {
			for(int var24 = 0; var24 < noTopics; var24 += 1)
				DistributionSampling.sampleDirichlet(RNG$, beta, phi[var24]);
		}
		if(!fixedFlag$sample35) {
			for(int var32 = 0; var32 < length$documents.length; var32 += 1)
				DistributionSampling.sampleDirichlet(RNG$, alpha, theta[var32]);
		}
		if(!fixedFlag$sample59) {
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
				for(int j = 0; j < length$documents[i$var40]; j += 1)
					z[i$var40][j] = DistributionSampling.sampleCategorical(RNG$, theta[i$var40]);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample26) {
			for(int var24 = 0; var24 < noTopics; var24 += 1)
				DistributionSampling.sampleDirichlet(RNG$, beta, phi[var24]);
		}
		if(!fixedFlag$sample35) {
			for(int var32 = 0; var32 < length$documents.length; var32 += 1)
				DistributionSampling.sampleDirichlet(RNG$, alpha, theta[var32]);
		}
		if(!fixedFlag$sample59) {
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
				for(int j = 0; j < length$documents[i$var40]; j += 1)
					z[i$var40][j] = DistributionSampling.sampleCategorical(RNG$, theta[i$var40]);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample26) {
				for(int var24 = 0; var24 < noTopics; var24 += 1)
					sample26(var24);
			}
			if(!fixedFlag$sample35) {
				for(int var32 = 0; var32 < length$documents.length; var32 += 1)
					sample35(var32);
			}
			if(!fixedFlag$sample59) {
				for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
					for(int j = 0; j < length$documents[i$var40]; j += 1)
						sample59(i$var40, j);
				}
			}
		} else {
			if(!fixedFlag$sample59) {
				for(int i$var40 = (length$documents.length - 1); i$var40 >= 0; i$var40 -= 1) {
					for(int j = (length$documents[i$var40] - 1); j >= 0; j -= 1)
						sample59(i$var40, j);
				}
			}
			if(!fixedFlag$sample35) {
				for(int var32 = (length$documents.length - 1); var32 >= 0; var32 -= 1)
					sample35(var32);
			}
			if(!fixedFlag$sample26) {
				for(int var24 = (noTopics - 1); var24 >= 0; var24 -= 1)
					sample26(var24);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		for(int i$var11 = 0; i$var11 < noTopics; i$var11 += 1)
			alpha[i$var11] = 0.1;
		for(int i$var17 = 0; i$var17 < vocabSize; i$var17 += 1)
			beta[i$var17] = 0.1;
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
		if(!fixedProbFlag$sample35)
			logProbability$var33 = 0.0;
		for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
			for(int j = 0; j < length$documents[i$var40]; j += 1)
				logProbability$var52[i$var40][j] = 0.0;
		}
		logProbability$z = 0.0;
		if(!fixedProbFlag$sample59) {
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
				for(int j = 0; j < length$documents[i$var40]; j += 1)
					logProbability$sample59[i$var40][j] = 0.0;
			}
		}
		for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
			for(int j = 0; j < length$documents[i$var40]; j += 1)
				logProbability$var55[i$var40][j] = 0.0;
		}
		logProbability$w = 0.0;
		if(!fixedProbFlag$sample62) {
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
				for(int j = 0; j < length$documents[i$var40]; j += 1)
					logProbability$sample62[i$var40][j] = 0.0;
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
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		if(fixedFlag$sample59)
			logProbabilityValue$sample59();
		logProbabilityValue$sample62();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample35();
		logProbabilityValue$sample59();
		logProbabilityValue$sample62();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample35();
		logProbabilityValue$sample59();
		logProbabilityValue$sample62();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample26) {
			for(int var24 = 0; var24 < noTopics; var24 += 1)
				DistributionSampling.sampleDirichlet(RNG$, beta, phi[var24]);
		}
		if(!fixedFlag$sample35) {
			for(int var32 = 0; var32 < length$documents.length; var32 += 1)
				DistributionSampling.sampleDirichlet(RNG$, alpha, theta[var32]);
		}
		if(!fixedFlag$sample59) {
			for(int i$var40 = 0; i$var40 < length$documents.length; i$var40 += 1) {
				for(int j = 0; j < length$documents[i$var40]; j += 1)
					z[i$var40][j] = DistributionSampling.sampleCategorical(RNG$, theta[i$var40]);
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel LDATest(int noTopics, int vocabSize, int[][] documents) {\n\n    double[] alpha = new double[noTopics];\n    for(int i:[0..noTopics))\n        alpha[i] = 0.1;\n\n    double[] beta = new double[vocabSize];\n    for(int i:[0..vocabSize))\n        beta[i] = 0.1;\n\n    double[][] phi = dirichlet(beta).sample(noTopics);\n    double[][] theta = dirichlet(alpha).sample(documents.length);\n    int[][] w = new int[documents.length][];\n\n    for(int i:[0..documents.length)) {\n        int[] t = new int[documents[i].length];\n        for(int j:[0..documents[i].length)) {\n            int z = categorical(theta[i]).sample();\n            t[j] = categorical(phi[z]).sample();\n        }\n        w[i] = t;\n    }\n\n    w.observe(documents);\n}\n";
	}
}