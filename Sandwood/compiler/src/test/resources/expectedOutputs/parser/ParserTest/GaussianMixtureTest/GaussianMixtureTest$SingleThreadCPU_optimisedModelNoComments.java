package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class GaussianMixtureTest$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements GaussianMixtureTest$CoreInterface {
	private double[] alpha;
	private double[] cv$var17$countGlobal;
	private double[] cv$var68$stateProbabilityGlobal;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample52 = false;
	private boolean fixedFlag$sample68 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample52 = false;
	private boolean fixedProbFlag$sample68 = false;
	private boolean fixedProbFlag$sample72 = false;
	private int length$xMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$mu;
	private double logProbability$phi;
	private double[] logProbability$sample68;
	private double[] logProbability$sample72;
	private double logProbability$sigma;
	private double logProbability$var16;
	private double logProbability$var22;
	private double logProbability$var34;
	private double logProbability$var40;
	private double logProbability$var52;
	private double[] logProbability$var67;
	private double[] logProbability$var71;
	private double logProbability$x;
	private double logProbability$z;
	private double[] mu;
	private double[] phi;
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
	public final boolean get$fixedFlag$sample17() {
		return fixedFlag$sample17;
	}

	@Override
	public final void set$fixedFlag$sample17(boolean cv$value) {
		fixedFlag$sample17 = cv$value;
		fixedProbFlag$sample17 = (cv$value && fixedProbFlag$sample17);
		fixedProbFlag$sample68 = (cv$value && fixedProbFlag$sample68);
	}

	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		fixedFlag$sample34 = cv$value;
		fixedProbFlag$sample34 = (cv$value && fixedProbFlag$sample34);
		fixedProbFlag$sample72 = (cv$value && fixedProbFlag$sample72);
	}

	@Override
	public final boolean get$fixedFlag$sample52() {
		return fixedFlag$sample52;
	}

	@Override
	public final void set$fixedFlag$sample52(boolean cv$value) {
		fixedFlag$sample52 = cv$value;
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
		fixedProbFlag$sample72 = (cv$value && fixedProbFlag$sample72);
	}

	@Override
	public final boolean get$fixedFlag$sample68() {
		return fixedFlag$sample68;
	}

	@Override
	public final void set$fixedFlag$sample68(boolean cv$value) {
		fixedFlag$sample68 = cv$value;
		fixedProbFlag$sample68 = (cv$value && fixedProbFlag$sample68);
		fixedProbFlag$sample72 = (cv$value && fixedProbFlag$sample72);
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
		fixedProbFlag$sample34 = false;
		fixedProbFlag$sample72 = false;
	}

	@Override
	public final double[] get$phi() {
		return phi;
	}

	@Override
	public final void set$phi(double[] cv$value) {
		phi = cv$value;
		fixedProbFlag$sample17 = false;
		fixedProbFlag$sample68 = false;
	}

	@Override
	public final double[] get$sigma() {
		return sigma;
	}

	@Override
	public final void set$sigma(double[] cv$value) {
		sigma = cv$value;
		fixedProbFlag$sample52 = false;
		fixedProbFlag$sample72 = false;
	}

	@Override
	public final double[] get$x() {
		return x;
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
	}

	private final void logProbabilityValue$sample17() {
		if(!fixedProbFlag$sample17) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(phi, alpha, 5);
			logProbability$var16 = cv$distributionAccumulator;
			logProbability$phi = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample17 = fixedFlag$sample17;
		} else {
			logProbability$var16 = logProbability$phi;
			logProbability$$model = (logProbability$$model + logProbability$phi);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + logProbability$phi);
		}
	}

	private final void logProbabilityValue$sample34() {
		if(!fixedProbFlag$sample34) {
			double cv$sampleAccumulator = 0.0;
			for(int var33 = 0; var33 < 5; var33 += 1)
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian((mu[var33] / 4.47213595499958))) - 1.4978661367769954);
			logProbability$var22 = cv$sampleAccumulator;
			logProbability$var34 = cv$sampleAccumulator;
			logProbability$mu = (logProbability$mu + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample34 = fixedFlag$sample34;
		} else {
			logProbability$var22 = logProbability$var34;
			logProbability$mu = (logProbability$mu + logProbability$var34);
			logProbability$$model = (logProbability$$model + logProbability$var34);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var34);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!fixedProbFlag$sample52) {
			double cv$sampleAccumulator = 0.0;
			for(int var51 = 0; var51 < 5; var51 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(sigma[var51], 1.0, 1.0));
			logProbability$var40 = cv$sampleAccumulator;
			logProbability$var52 = cv$sampleAccumulator;
			logProbability$sigma = (logProbability$sigma + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample52 = fixedFlag$sample52;
		} else {
			logProbability$var40 = logProbability$var52;
			logProbability$sigma = (logProbability$sigma + logProbability$var52);
			logProbability$$model = (logProbability$$model + logProbability$var52);
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var52);
		}
	}

	private final void logProbabilityValue$sample68() {
		if(!fixedProbFlag$sample68) {
			double cv$accumulator = 0.0;
			for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1) {
				int cv$sampleValue = z[i$var66];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 5))?Math.log(phi[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var67[i$var66] = cv$distributionAccumulator;
				logProbability$sample68[i$var66] = cv$distributionAccumulator;
			}
			logProbability$z = (logProbability$z + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample68)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample68 = (fixedFlag$sample68 && fixedFlag$sample17);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1) {
				double cv$rvAccumulator = logProbability$sample68[i$var66];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var67[i$var66] = cv$rvAccumulator;
			}
			logProbability$z = (logProbability$z + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample68)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample72() {
		if(!fixedProbFlag$sample72) {
			double cv$accumulator = 0.0;
			for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1) {
				double var70 = sigma[z[i$var66]];
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((x[i$var66] - mu[z[i$var66]]) / Math.sqrt(var70))) - (Math.log(var70) * 0.5));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var71[i$var66] = cv$distributionAccumulator;
				logProbability$sample72[i$var66] = cv$distributionAccumulator;
			}
			logProbability$x = (logProbability$x + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample72 = ((fixedFlag$sample34 && fixedFlag$sample52) && fixedFlag$sample68);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1) {
				double cv$rvAccumulator = logProbability$sample72[i$var66];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var71[i$var66] = cv$rvAccumulator;
			}
			logProbability$x = (logProbability$x + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample17() {
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			cv$var17$countGlobal[cv$loopIndex] = 0.0;
		for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1)
			cv$var17$countGlobal[z[i$var66]] = (cv$var17$countGlobal[z[i$var66]] + 1.0);
		Conjugates.sampleConjugateDirichletCategorical(RNG$, alpha, cv$var17$countGlobal, phi, 5);
	}

	private final void sample34(int var33) {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1) {
			if((var33 == z[i$var66])) {
				cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
				cv$sum = (cv$sum + x[i$var66]);
				if(cv$sigmaNotFound) {
					cv$sigmaValue = sigma[z[i$var66]];
					cv$sigmaNotFound = false;
				}
			}
		}
		mu[var33] = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 20.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void sample52(int var51) {
		double cv$sum = 0.0;
		int cv$count = 0;
		for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1) {
			if((var51 == z[i$var66])) {
				double cv$var71$diff = (mu[z[i$var66]] - x[i$var66]);
				cv$sum = (cv$sum + (cv$var71$diff * cv$var71$diff));
				cv$count = (cv$count + 1);
			}
		}
		sigma[var51] = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample68(int i$var66) {
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			z[i$var66] = cv$valuePos;
			double cv$temp$3$var70 = sigma[cv$valuePos];
			cv$var68$stateProbabilityGlobal[cv$valuePos] = ((DistributionSampling.logProbabilityGaussian(((x[i$var66] - mu[cv$valuePos]) / Math.sqrt(cv$temp$3$var70))) + Math.log(phi[cv$valuePos])) - (Math.log(cv$temp$3$var70) * 0.5));
		}
		double cv$logSum;
		double cv$lseMax = cv$var68$stateProbabilityGlobal[0];
		{
			double cv$lseElementValue = cv$var68$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		{
			double cv$lseElementValue = cv$var68$stateProbabilityGlobal[2];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		{
			double cv$lseElementValue = cv$var68$stateProbabilityGlobal[3];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		double cv$lseElementValue = cv$var68$stateProbabilityGlobal[4];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < 5; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var68$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
				cv$var68$stateProbabilityGlobal[cv$indexName] = 0.2;
		} else {
			for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
				cv$var68$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var68$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = 5; cv$indexName < cv$var68$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var68$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		z[i$var66] = DistributionSampling.sampleCategorical(RNG$, cv$var68$stateProbabilityGlobal, 5);
	}

	@Override
	public final void allocateScratch() {
		cv$var17$countGlobal = new double[5];
		cv$var68$stateProbabilityGlobal = new double[5];
	}

	@Override
	public final void allocator() {
		alpha = new double[5];
		if(!fixedFlag$sample17)
			phi = new double[5];
		if(!fixedFlag$sample34)
			mu = new double[5];
		if(!fixedFlag$sample52)
			sigma = new double[5];
		x = new double[length$xMeasured];
		if(!fixedFlag$sample68)
			z = new int[length$xMeasured];
		logProbability$var67 = new double[length$xMeasured];
		logProbability$sample68 = new double[length$xMeasured];
		logProbability$var71 = new double[length$xMeasured];
		logProbability$sample72 = new double[length$xMeasured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, alpha, 5, phi);
		if(!fixedFlag$sample34) {
			for(int var33 = 0; var33 < 5; var33 += 1)
				mu[var33] = (DistributionSampling.sampleGaussian(RNG$) * 4.47213595499958);
		}
		if(!fixedFlag$sample52) {
			for(int var51 = 0; var51 < 5; var51 += 1)
				sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1) {
			if(!fixedFlag$sample68)
				z[i$var66] = DistributionSampling.sampleCategorical(RNG$, phi, 5);
			x[i$var66] = ((Math.sqrt(sigma[z[i$var66]]) * DistributionSampling.sampleGaussian(RNG$)) + mu[z[i$var66]]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, alpha, 5, phi);
		if(!fixedFlag$sample34) {
			for(int var33 = 0; var33 < 5; var33 += 1)
				mu[var33] = (DistributionSampling.sampleGaussian(RNG$) * 4.47213595499958);
		}
		if(!fixedFlag$sample52) {
			for(int var51 = 0; var51 < 5; var51 += 1)
				sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample68) {
			for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1)
				z[i$var66] = DistributionSampling.sampleCategorical(RNG$, phi, 5);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, alpha, 5, phi);
		if(!fixedFlag$sample34) {
			for(int var33 = 0; var33 < 5; var33 += 1)
				mu[var33] = (DistributionSampling.sampleGaussian(RNG$) * 4.47213595499958);
		}
		if(!fixedFlag$sample52) {
			for(int var51 = 0; var51 < 5; var51 += 1)
				sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample68) {
			for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1)
				z[i$var66] = DistributionSampling.sampleCategorical(RNG$, phi, 5);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample17)
				sample17();
			if(!fixedFlag$sample34) {
				for(int var33 = 0; var33 < 5; var33 += 1)
					sample34(var33);
			}
			if(!fixedFlag$sample52) {
				for(int var51 = 0; var51 < 5; var51 += 1)
					sample52(var51);
			}
			if(!fixedFlag$sample68) {
				for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1)
					sample68(i$var66);
			}
		} else {
			if(!fixedFlag$sample68) {
				for(int i$var66 = (length$xMeasured - 1); i$var66 >= 0; i$var66 -= 1)
					sample68(i$var66);
			}
			if(!fixedFlag$sample52) {
				for(int var51 = 4; var51 >= 0; var51 -= 1)
					sample52(var51);
			}
			if(!fixedFlag$sample34) {
				for(int var33 = 4; var33 >= 0; var33 -= 1)
					sample34(var33);
			}
			if(!fixedFlag$sample17)
				sample17();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		for(int i$var13 = 0; i$var13 < 5; i$var13 += 1)
			alpha[i$var13] = 1.0;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var16 = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$phi = Double.NaN;
		logProbability$var22 = Double.NaN;
		logProbability$mu = 0.0;
		if(!fixedProbFlag$sample34)
			logProbability$var34 = Double.NaN;
		logProbability$var40 = Double.NaN;
		logProbability$sigma = 0.0;
		if(!fixedProbFlag$sample52)
			logProbability$var52 = Double.NaN;
		for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1)
			logProbability$var67[i$var66] = Double.NaN;
		logProbability$z = 0.0;
		if(!fixedProbFlag$sample68) {
			for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1)
				logProbability$sample68[i$var66] = Double.NaN;
		}
		for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1)
			logProbability$var71[i$var66] = Double.NaN;
		logProbability$x = 0.0;
		if(!fixedProbFlag$sample72) {
			for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1)
				logProbability$sample72[i$var66] = Double.NaN;
		}
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
		if(fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(fixedFlag$sample52)
			logProbabilityValue$sample52();
		if(fixedFlag$sample68)
			logProbabilityValue$sample68();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample34();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample34();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(RNG$, alpha, 5, phi);
		if(!fixedFlag$sample34) {
			for(int var33 = 0; var33 < 5; var33 += 1)
				mu[var33] = (DistributionSampling.sampleGaussian(RNG$) * 4.47213595499958);
		}
		if(!fixedFlag$sample52) {
			for(int var51 = 0; var51 < 5; var51 += 1)
				sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample68) {
			for(int i$var66 = 0; i$var66 < length$xMeasured; i$var66 += 1)
				z[i$var66] = DistributionSampling.sampleCategorical(RNG$, phi, 5);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = x.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			x[cv$index1] = xMeasured[cv$index1];
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
		     + "model GaussianMixtureTest(double[] xMeasured) {\n"
		     + "\n"
		     + "        int k = 5;\n"
		     + "\n"
		     + "        double[] alpha = new double[k];\n"
		     + "        for(int i:[0..k)) \n"
		     + "            alpha[i] = 1.0;\n"
		     + "        \n"
		     + "        double[] phi = dirichlet(alpha).sample();\n"
		     + "        double[] mu = gaussian(0, 20).sample(k);\n"
		     + "        double[] sigma = inverseGamma(1, 1).sample(k);\n"
		     + "        \n"
		     + "        double[] x = new double[xMeasured.length];\n"
		     + "        for(int i:[0..xMeasured.length)) {\n"
		     + "            int z = categorical(phi).sample();\n"
		     + "            x[i] = gaussian(mu[z], sigma[z]).sample();\n"
		     + "        }\n"
		     + "        \n"
		     + "        x.observe(xMeasured);\n"
		     + "}\n"
		     + "";
	}
}