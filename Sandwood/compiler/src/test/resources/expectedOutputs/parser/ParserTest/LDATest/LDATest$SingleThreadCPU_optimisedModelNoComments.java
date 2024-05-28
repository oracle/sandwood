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
		fixedProbFlag$sample102 = (cv$value && fixedProbFlag$sample102);
		fixedProbFlag$sample105 = (cv$value && fixedProbFlag$sample105);
	}

	@Override
	public final boolean get$fixedFlag$sample105() {
		return fixedFlag$sample105;
	}

	@Override
	public final void set$fixedFlag$sample105(boolean cv$value) {
		fixedFlag$sample105 = cv$value;
		fixedProbFlag$sample105 = (cv$value && fixedProbFlag$sample105);
	}

	@Override
	public final boolean get$fixedFlag$sample47() {
		return fixedFlag$sample47;
	}

	@Override
	public final void set$fixedFlag$sample47(boolean cv$value) {
		fixedFlag$sample47 = cv$value;
		fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
		fixedProbFlag$sample105 = (cv$value && fixedProbFlag$sample105);
	}

	@Override
	public final boolean get$fixedFlag$sample64() {
		return fixedFlag$sample64;
	}

	@Override
	public final void set$fixedFlag$sample64(boolean cv$value) {
		fixedFlag$sample64 = cv$value;
		fixedProbFlag$sample64 = (cv$value && fixedProbFlag$sample64);
		fixedProbFlag$sample102 = (cv$value && fixedProbFlag$sample102);
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
					int cv$sampleValue = z[i$var75][j];
					double[] var92 = theta[i$var75];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var92.length))?Math.log(var92[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var93[i$var75][j] = cv$distributionAccumulator;
					logProbability$sample102[i$var75][j] = cv$distributionAccumulator;
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
					double cv$rvAccumulator = logProbability$sample102[i$var75][j];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var93[i$var75][j] = cv$rvAccumulator;
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
					int cv$sampleValue = w[i$var75][j];
					double[] var95 = phi[z[i$var75][j]];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var95.length))?Math.log(var95[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var96[i$var75][j] = cv$distributionAccumulator;
					logProbability$sample105[i$var75][j] = cv$distributionAccumulator;
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
					double cv$rvAccumulator = logProbability$sample105[i$var75][j];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var96[i$var75][j] = cv$rvAccumulator;
				}
			}
			logProbability$w = (logProbability$w + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample47() {
		if(!fixedProbFlag$sample47) {
			double cv$sampleAccumulator = 0.0;
			for(int var45 = 0; var45 < noTopics; var45 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(phi[var45], beta));
			logProbability$var34 = cv$sampleAccumulator;
			logProbability$var46 = cv$sampleAccumulator;
			logProbability$phi = (logProbability$phi + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample47 = fixedFlag$sample47;
		} else {
			logProbability$var34 = logProbability$var46;
			logProbability$phi = (logProbability$phi + logProbability$var46);
			logProbability$$model = (logProbability$$model + logProbability$var46);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var46);
		}
	}

	private final void logProbabilityValue$sample64() {
		if(!fixedProbFlag$sample64) {
			double cv$sampleAccumulator = 0.0;
			for(int var60 = 0; var60 < length$documents.length; var60 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(theta[var60], alpha));
			logProbability$var48 = cv$sampleAccumulator;
			logProbability$var61 = cv$sampleAccumulator;
			logProbability$theta = (logProbability$theta + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample64)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample64 = fixedFlag$sample64;
		} else {
			logProbability$var48 = logProbability$var61;
			logProbability$theta = (logProbability$theta + logProbability$var61);
			logProbability$$model = (logProbability$$model + logProbability$var61);
			if(fixedFlag$sample64)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var61);
		}
	}

	private final void sample102(int i$var75, int j) {
		int cv$noStates = Math.max(0, noTopics);
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			z[i$var75][j] = cv$valuePos;
			double[] cv$temp$0$var92 = theta[i$var75];
			double[] cv$temp$1$var95 = phi[cv$valuePos];
			cv$var94$stateProbabilityGlobal[cv$valuePos] = ((((0.0 <= w[i$var75][j]) && (w[i$var75][j] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[w[i$var75][j]]):Double.NEGATIVE_INFINITY) + ((cv$valuePos < cv$temp$0$var92.length)?Math.log(cv$temp$0$var92[cv$valuePos]):Double.NEGATIVE_INFINITY));
		}
		double cv$logSum;
		double cv$lseMax = cv$var94$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var94$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var94$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$var94$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$var94$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var94$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var94$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var94$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		z[i$var75][j] = DistributionSampling.sampleCategorical(RNG$, cv$var94$stateProbabilityGlobal);
	}

	private final void sample47(int var45) {
		for(int cv$loopIndex = 0; cv$loopIndex < vocabSize; cv$loopIndex += 1)
			cv$var46$countGlobal[cv$loopIndex] = 0.0;
		for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
			for(int j = 0; j < length$documents[i$var75]; j += 1) {
				if((var45 == z[i$var75][j]))
					cv$var46$countGlobal[w[i$var75][j]] = (cv$var46$countGlobal[w[i$var75][j]] + 1.0);
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, beta, cv$var46$countGlobal, phi[var45]);
	}

	private final void sample64(int var60) {
		for(int cv$loopIndex = 0; cv$loopIndex < noTopics; cv$loopIndex += 1)
			cv$var61$countGlobal[cv$loopIndex] = 0.0;
		for(int j = 0; j < length$documents[var60]; j += 1)
			cv$var61$countGlobal[z[var60][j]] = (cv$var61$countGlobal[z[var60][j]] + 1.0);
		Conjugates.sampleConjugateDirichletCategorical(RNG$, alpha, cv$var61$countGlobal, theta[var60]);
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			if((0 < noTopics))
				cv$max = Math.max(0, vocabSize);
			cv$var46$countGlobal = new double[cv$max];
		}
		int cv$max = 0;
		if((0 < length$documents.length))
			cv$max = Math.max(0, noTopics);
		cv$var61$countGlobal = new double[cv$max];
		cv$var94$stateProbabilityGlobal = new double[noTopics];
	}

	@Override
	public final void allocator() {
		alpha = new double[noTopics];
		beta = new double[vocabSize];
		if(!setFlag$phi) {
			phi = new double[noTopics][];
			for(int var45 = 0; var45 < noTopics; var45 += 1)
				phi[var45] = new double[vocabSize];
		}
		if(!setFlag$theta) {
			theta = new double[length$documents.length][];
			for(int var60 = 0; var60 < length$documents.length; var60 += 1)
				theta[var60] = new double[noTopics];
		}
		if(!setFlag$w) {
			w = new int[length$documents.length][];
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1)
				w[i$var75] = new int[length$documents[i$var75]];
		}
		if(!setFlag$z) {
			z = new int[length$documents.length][];
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1)
				z[i$var75] = new int[length$documents[i$var75]];
		}
		logProbability$var93 = new double[length$documents.length][];
		for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1)
			logProbability$var93[i$var75] = new double[length$documents[i$var75]];
		logProbability$sample102 = new double[length$documents.length][];
		for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1)
			logProbability$sample102[i$var75] = new double[length$documents[i$var75]];
		logProbability$var96 = new double[length$documents.length][];
		for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1)
			logProbability$var96[i$var75] = new double[length$documents[i$var75]];
		logProbability$sample105 = new double[length$documents.length][];
		for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1)
			logProbability$sample105[i$var75] = new double[length$documents[i$var75]];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample47) {
			for(int var45 = 0; var45 < noTopics; var45 += 1)
				DistributionSampling.sampleDirichlet(RNG$, beta, phi[var45]);
		}
		if(!fixedFlag$sample64) {
			for(int var60 = 0; var60 < length$documents.length; var60 += 1)
				DistributionSampling.sampleDirichlet(RNG$, alpha, theta[var60]);
		}
		for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
			int[] t = w[i$var75];
			for(int j = 0; j < length$documents[i$var75]; j += 1) {
				if(!fixedFlag$sample102)
					z[i$var75][j] = DistributionSampling.sampleCategorical(RNG$, theta[i$var75]);
				if(!fixedFlag$sample105)
					t[j] = DistributionSampling.sampleCategorical(RNG$, phi[z[i$var75][j]]);
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample47) {
			for(int var45 = 0; var45 < noTopics; var45 += 1)
				DistributionSampling.sampleDirichlet(RNG$, beta, phi[var45]);
		}
		if(!fixedFlag$sample64) {
			for(int var60 = 0; var60 < length$documents.length; var60 += 1)
				DistributionSampling.sampleDirichlet(RNG$, alpha, theta[var60]);
		}
		if(!fixedFlag$sample102) {
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
				for(int j = 0; j < length$documents[i$var75]; j += 1)
					z[i$var75][j] = DistributionSampling.sampleCategorical(RNG$, theta[i$var75]);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample47) {
			for(int var45 = 0; var45 < noTopics; var45 += 1)
				DistributionSampling.sampleDirichlet(RNG$, beta, phi[var45]);
		}
		if(!fixedFlag$sample64) {
			for(int var60 = 0; var60 < length$documents.length; var60 += 1)
				DistributionSampling.sampleDirichlet(RNG$, alpha, theta[var60]);
		}
		if(!fixedFlag$sample102) {
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
				for(int j = 0; j < length$documents[i$var75]; j += 1)
					z[i$var75][j] = DistributionSampling.sampleCategorical(RNG$, theta[i$var75]);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample47) {
				for(int var45 = 0; var45 < noTopics; var45 += 1)
					sample47(var45);
			}
			if(!fixedFlag$sample64) {
				for(int var60 = 0; var60 < length$documents.length; var60 += 1)
					sample64(var60);
			}
			if(!fixedFlag$sample102) {
				for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
					for(int j = 0; j < length$documents[i$var75]; j += 1)
						sample102(i$var75, j);
				}
			}
		} else {
			if(!fixedFlag$sample102) {
				for(int i$var75 = (length$documents.length - 1); i$var75 >= 0; i$var75 -= 1) {
					for(int j = (length$documents[i$var75] - 1); j >= 0; j -= 1)
						sample102(i$var75, j);
				}
			}
			if(!fixedFlag$sample64) {
				for(int var60 = (length$documents.length - 1); var60 >= 0; var60 -= 1)
					sample64(var60);
			}
			if(!fixedFlag$sample47) {
				for(int var45 = (noTopics - 1); var45 >= 0; var45 -= 1)
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
				logProbability$var93[i$var75][j] = 0.0;
		}
		logProbability$z = 0.0;
		if(!fixedProbFlag$sample102) {
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
				for(int j = 0; j < length$documents[i$var75]; j += 1)
					logProbability$sample102[i$var75][j] = 0.0;
			}
		}
		for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
			for(int j = 0; j < length$documents[i$var75]; j += 1)
				logProbability$var96[i$var75][j] = 0.0;
		}
		logProbability$w = 0.0;
		if(!fixedProbFlag$sample105) {
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
				for(int j = 0; j < length$documents[i$var75]; j += 1)
					logProbability$sample105[i$var75][j] = 0.0;
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
		if(!fixedFlag$sample47) {
			for(int var45 = 0; var45 < noTopics; var45 += 1)
				DistributionSampling.sampleDirichlet(RNG$, beta, phi[var45]);
		}
		if(!fixedFlag$sample64) {
			for(int var60 = 0; var60 < length$documents.length; var60 += 1)
				DistributionSampling.sampleDirichlet(RNG$, alpha, theta[var60]);
		}
		if(!fixedFlag$sample102) {
			for(int i$var75 = 0; i$var75 < length$documents.length; i$var75 += 1) {
				for(int j = 0; j < length$documents[i$var75]; j += 1)
					z[i$var75][j] = DistributionSampling.sampleCategorical(RNG$, theta[i$var75]);
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