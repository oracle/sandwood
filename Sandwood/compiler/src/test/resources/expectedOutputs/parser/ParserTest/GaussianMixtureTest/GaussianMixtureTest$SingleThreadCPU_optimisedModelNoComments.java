package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class GaussianMixtureTest$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements GaussianMixtureTest$CoreInterface {
	private double[] alpha;
	private double[] cv$var12$countGlobal;
	private double[] cv$var42$stateProbabilityGlobal;
	private boolean fixedFlag$sample13 = false;
	private boolean fixedFlag$sample23 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample49 = false;
	private boolean fixedProbFlag$sample13 = false;
	private boolean fixedProbFlag$sample23 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample49 = false;
	private int length$xMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$mu;
	private double logProbability$phi;
	private double[] logProbability$sample45;
	private double[] logProbability$sample49;
	private double logProbability$sigma;
	private double logProbability$var11;
	private double logProbability$var17;
	private double logProbability$var22;
	private double logProbability$var28;
	private double logProbability$var33;
	private double[] logProbability$var41;
	private double[] logProbability$var45;
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

	public GaussianMixtureTest$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$alpha() {
		return alpha;
	}

	@Override
	public final boolean get$fixedFlag$sample13() {
		return fixedFlag$sample13;
	}

	@Override
	public final void set$fixedFlag$sample13(boolean cv$value) {
		fixedFlag$sample13 = cv$value;
		fixedProbFlag$sample13 = (cv$value && fixedProbFlag$sample13);
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
	}

	@Override
	public final boolean get$fixedFlag$sample23() {
		return fixedFlag$sample23;
	}

	@Override
	public final void set$fixedFlag$sample23(boolean cv$value) {
		fixedFlag$sample23 = cv$value;
		fixedProbFlag$sample23 = (cv$value && fixedProbFlag$sample23);
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
	}

	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		fixedFlag$sample34 = cv$value;
		fixedProbFlag$sample34 = (cv$value && fixedProbFlag$sample34);
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		fixedFlag$sample45 = cv$value;
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
	}

	@Override
	public final boolean get$fixedFlag$sample49() {
		return fixedFlag$sample49;
	}

	@Override
	public final void set$fixedFlag$sample49(boolean cv$value) {
		fixedFlag$sample49 = cv$value;
		fixedProbFlag$sample49 = (cv$value && fixedProbFlag$sample49);
	}

	@Override
	public final int get$k() {
		return 5;
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
	}

	@Override
	public final double[] get$phi() {
		return phi;
	}

	@Override
	public final void set$phi(double[] cv$value) {
		phi = cv$value;
		setFlag$phi = true;
	}

	@Override
	public final double[] get$sigma() {
		return sigma;
	}

	@Override
	public final void set$sigma(double[] cv$value) {
		sigma = cv$value;
		setFlag$sigma = true;
	}

	@Override
	public final double[] get$x() {
		return x;
	}

	@Override
	public final void set$x(double[] cv$value) {
		x = cv$value;
		setFlag$x = true;
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

	private final void logProbabilityValue$sample13() {
		if(!fixedProbFlag$sample13) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(phi, alpha);
			logProbability$var11 = cv$distributionAccumulator;
			logProbability$phi = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample13)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample13 = fixedFlag$sample13;
		} else {
			logProbability$var11 = logProbability$phi;
			logProbability$$model = (logProbability$$model + logProbability$phi);
			if(fixedFlag$sample13)
				logProbability$$evidence = (logProbability$$evidence + logProbability$phi);
		}
	}

	private final void logProbabilityValue$sample23() {
		if(!fixedProbFlag$sample23) {
			double cv$sampleAccumulator = 0.0;
			for(int var21 = 0; var21 < 5; var21 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(mu[var21], 0.0, 20.0));
			logProbability$var17 = cv$sampleAccumulator;
			logProbability$var22 = cv$sampleAccumulator;
			logProbability$mu = (logProbability$mu + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample23)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample23 = fixedFlag$sample23;
		} else {
			logProbability$var17 = logProbability$var22;
			logProbability$mu = (logProbability$mu + logProbability$var22);
			logProbability$$model = (logProbability$$model + logProbability$var22);
			if(fixedFlag$sample23)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var22);
		}
	}

	private final void logProbabilityValue$sample34() {
		if(!fixedProbFlag$sample34) {
			double cv$sampleAccumulator = 0.0;
			for(int var32 = 0; var32 < 5; var32 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(sigma[var32], 1.0, 1.0));
			logProbability$var28 = cv$sampleAccumulator;
			logProbability$var33 = cv$sampleAccumulator;
			logProbability$sigma = (logProbability$sigma + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample34 = fixedFlag$sample34;
		} else {
			logProbability$var28 = logProbability$var33;
			logProbability$sigma = (logProbability$sigma + logProbability$var33);
			logProbability$$model = (logProbability$$model + logProbability$var33);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var33);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!fixedProbFlag$sample45) {
			double cv$accumulator = 0.0;
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(z[i$var40], phi);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var41[i$var40] = cv$distributionAccumulator;
				logProbability$sample45[i$var40] = cv$distributionAccumulator;
			}
			logProbability$z = (logProbability$z + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedFlag$sample13);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
				double cv$rvAccumulator = logProbability$sample45[i$var40];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var41[i$var40] = cv$rvAccumulator;
			}
			logProbability$z = (logProbability$z + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample49() {
		if(!fixedProbFlag$sample49) {
			double cv$accumulator = 0.0;
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(x[i$var40], mu[z[i$var40]], sigma[z[i$var40]]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var45[i$var40] = cv$distributionAccumulator;
				logProbability$sample49[i$var40] = cv$distributionAccumulator;
			}
			logProbability$x = (logProbability$x + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample49 = (((fixedFlag$sample49 && fixedFlag$sample23) && fixedFlag$sample34) && fixedFlag$sample45);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
				double cv$rvAccumulator = logProbability$sample49[i$var40];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var45[i$var40] = cv$rvAccumulator;
			}
			logProbability$x = (logProbability$x + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample13() {
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			cv$var12$countGlobal[cv$loopIndex] = 0.0;
		for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1)
			cv$var12$countGlobal[z[i$var40]] = (cv$var12$countGlobal[z[i$var40]] + 1.0);
		Conjugates.sampleConjugateDirichletCategorical(RNG$, alpha, cv$var12$countGlobal, phi);
	}

	private final void sample23(int var21) {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
			if((var21 == z[i$var40])) {
				cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
				cv$sum = (cv$sum + x[i$var40]);
				if(cv$sigmaNotFound) {
					cv$sigmaValue = sigma[z[i$var40]];
					cv$sigmaNotFound = false;
				}
			}
		}
		mu[var21] = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 20.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void sample34(int var32) {
		double cv$sum = 0.0;
		int cv$count = 0;
		for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
			if((var32 == z[i$var40])) {
				double cv$var45$diff = (mu[z[i$var40]] - x[i$var40]);
				cv$sum = (cv$sum + (cv$var45$diff * cv$var45$diff));
				cv$count = (cv$count + 1);
			}
		}
		sigma[var32] = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample45(int i$var40) {
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			z[i$var40] = cv$valuePos;
			cv$var42$stateProbabilityGlobal[cv$valuePos] = (DistributionSampling.logProbabilityGaussian(x[i$var40], mu[cv$valuePos], sigma[cv$valuePos]) + DistributionSampling.logProbabilityCategorical(cv$valuePos, phi));
		}
		double cv$logSum;
		double cv$lseMax = cv$var42$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var42$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var42$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var42$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var42$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var42$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var42$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$var42$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var42$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var42$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var42$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		z[i$var40] = DistributionSampling.sampleCategorical(RNG$, cv$var42$stateProbabilityGlobal);
	}

	@Override
	public final void allocateScratch() {
		cv$var12$countGlobal = new double[5];
		cv$var42$stateProbabilityGlobal = new double[5];
	}

	@Override
	public final void allocator() {
		alpha = new double[5];
		if(!setFlag$phi)
			phi = new double[5];
		if(!setFlag$mu)
			mu = new double[5];
		if(!setFlag$sigma)
			sigma = new double[5];
		if(!setFlag$x)
			x = new double[length$xMeasured];
		if(!setFlag$z)
			z = new int[length$xMeasured];
		logProbability$var41 = new double[length$xMeasured];
		logProbability$sample45 = new double[length$xMeasured];
		logProbability$var45 = new double[length$xMeasured];
		logProbability$sample49 = new double[length$xMeasured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample13)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		if(!fixedFlag$sample23) {
			for(int var21 = 0; var21 < 5; var21 += 1)
				mu[var21] = DistributionSampling.sampleGaussian(RNG$, 0.0, 20.0);
		}
		if(!fixedFlag$sample34) {
			for(int var32 = 0; var32 < 5; var32 += 1)
				sigma[var32] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1) {
			if(!fixedFlag$sample45)
				z[i$var40] = DistributionSampling.sampleCategorical(RNG$, phi);
			if(!fixedFlag$sample49)
				x[i$var40] = DistributionSampling.sampleGaussian(RNG$, mu[z[i$var40]], sigma[z[i$var40]]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample13)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		if(!fixedFlag$sample23) {
			for(int var21 = 0; var21 < 5; var21 += 1)
				mu[var21] = DistributionSampling.sampleGaussian(RNG$, 0.0, 20.0);
		}
		if(!fixedFlag$sample34) {
			for(int var32 = 0; var32 < 5; var32 += 1)
				sigma[var32] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample45) {
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1)
				z[i$var40] = DistributionSampling.sampleCategorical(RNG$, phi);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample13)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		if(!fixedFlag$sample23) {
			for(int var21 = 0; var21 < 5; var21 += 1)
				mu[var21] = DistributionSampling.sampleGaussian(RNG$, 0.0, 20.0);
		}
		if(!fixedFlag$sample34) {
			for(int var32 = 0; var32 < 5; var32 += 1)
				sigma[var32] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample45) {
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1)
				z[i$var40] = DistributionSampling.sampleCategorical(RNG$, phi);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample13)
				sample13();
			if(!fixedFlag$sample23) {
				for(int var21 = 0; var21 < 5; var21 += 1)
					sample23(var21);
			}
			if(!fixedFlag$sample34) {
				for(int var32 = 0; var32 < 5; var32 += 1)
					sample34(var32);
			}
			if(!fixedFlag$sample45) {
				for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1)
					sample45(i$var40);
			}
		} else {
			if(!fixedFlag$sample45) {
				for(int i$var40 = (length$xMeasured - 1); i$var40 >= 0; i$var40 -= 1)
					sample45(i$var40);
			}
			if(!fixedFlag$sample34) {
				for(int var32 = 4; var32 >= 0; var32 -= 1)
					sample34(var32);
			}
			if(!fixedFlag$sample23) {
				for(int var21 = 4; var21 >= 0; var21 -= 1)
					sample23(var21);
			}
			if(!fixedFlag$sample13)
				sample13();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		for(int i$var8 = 0; i$var8 < 5; i$var8 += 1)
			alpha[i$var8] = 1.0;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var11 = 0.0;
		if(!fixedProbFlag$sample13)
			logProbability$phi = 0.0;
		logProbability$var17 = 0.0;
		logProbability$mu = 0.0;
		if(!fixedProbFlag$sample23)
			logProbability$var22 = 0.0;
		logProbability$var28 = 0.0;
		logProbability$sigma = 0.0;
		if(!fixedProbFlag$sample34)
			logProbability$var33 = 0.0;
		for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1)
			logProbability$var41[i$var40] = 0.0;
		logProbability$z = 0.0;
		if(!fixedProbFlag$sample45) {
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1)
				logProbability$sample45[i$var40] = 0.0;
		}
		for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1)
			logProbability$var45[i$var40] = 0.0;
		logProbability$x = 0.0;
		if(!fixedProbFlag$sample49) {
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1)
				logProbability$sample49[i$var40] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample13)
			logProbabilityValue$sample13();
		if(fixedFlag$sample23)
			logProbabilityValue$sample23();
		if(fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		logProbabilityValue$sample49();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample13();
		logProbabilityValue$sample23();
		logProbabilityValue$sample34();
		logProbabilityValue$sample45();
		logProbabilityValue$sample49();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample13();
		logProbabilityValue$sample23();
		logProbabilityValue$sample34();
		logProbabilityValue$sample45();
		logProbabilityValue$sample49();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample13)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		if(!fixedFlag$sample23) {
			for(int var21 = 0; var21 < 5; var21 += 1)
				mu[var21] = DistributionSampling.sampleGaussian(RNG$, 0.0, 20.0);
		}
		if(!fixedFlag$sample34) {
			for(int var32 = 0; var32 < 5; var32 += 1)
				sigma[var32] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample45) {
			for(int i$var40 = 0; i$var40 < length$xMeasured; i$var40 += 1)
				z[i$var40] = DistributionSampling.sampleCategorical(RNG$, phi);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = x.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			x[cv$index1] = xMeasured[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel GaussianMixtureTest(double[] xMeasured) {\n\n        int k = 5;\n\n        double[] alpha = new double[k];\n        for(int i:[0..k)) \n            alpha[i] = 1.0;\n        \n        double[] phi = dirichlet(alpha).sample();\n        double[] mu = gaussian(0, 20).sample(k);\n        double[] sigma = inverseGamma(1, 1).sample(k);\n        \n        double[] x = new double[xMeasured.length];\n        for(int i:[0..xMeasured.length)) {\n            int z = categorical(phi).sample();\n            x[i] = gaussian(mu[z], sigma[z]).sample();\n        }\n        \n        x.observe(xMeasured);\n}\n";
	}
}