package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class GaussianMixtureTest$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements GaussianMixtureTest$CoreInterface {
	private double[] alpha;
	private double[] cv$var19$countGlobal;
	private double[] cv$var70$stateProbabilityGlobal;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample37 = false;
	private boolean fixedFlag$sample55 = false;
	private boolean fixedFlag$sample73 = false;
	private boolean fixedFlag$sample77 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample37 = false;
	private boolean fixedProbFlag$sample55 = false;
	private boolean fixedProbFlag$sample73 = false;
	private boolean fixedProbFlag$sample77 = false;
	private int length$xMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$mu;
	private double logProbability$phi;
	private double[] logProbability$sample73;
	private double[] logProbability$sample77;
	private double logProbability$sigma;
	private double logProbability$var18;
	private double logProbability$var24;
	private double logProbability$var36;
	private double logProbability$var42;
	private double logProbability$var54;
	private double[] logProbability$var69;
	private double[] logProbability$var73;
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
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	@Override
	public final void set$fixedFlag$sample20(boolean cv$value) {
		fixedFlag$sample20 = cv$value;
		fixedProbFlag$sample20 = (cv$value && fixedProbFlag$sample20);
		fixedProbFlag$sample73 = (cv$value && fixedProbFlag$sample73);
	}

	@Override
	public final boolean get$fixedFlag$sample37() {
		return fixedFlag$sample37;
	}

	@Override
	public final void set$fixedFlag$sample37(boolean cv$value) {
		fixedFlag$sample37 = cv$value;
		fixedProbFlag$sample37 = (cv$value && fixedProbFlag$sample37);
		fixedProbFlag$sample77 = (cv$value && fixedProbFlag$sample77);
	}

	@Override
	public final boolean get$fixedFlag$sample55() {
		return fixedFlag$sample55;
	}

	@Override
	public final void set$fixedFlag$sample55(boolean cv$value) {
		fixedFlag$sample55 = cv$value;
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
		fixedProbFlag$sample77 = (cv$value && fixedProbFlag$sample77);
	}

	@Override
	public final boolean get$fixedFlag$sample73() {
		return fixedFlag$sample73;
	}

	@Override
	public final void set$fixedFlag$sample73(boolean cv$value) {
		fixedFlag$sample73 = cv$value;
		fixedProbFlag$sample73 = (cv$value && fixedProbFlag$sample73);
		fixedProbFlag$sample77 = (cv$value && fixedProbFlag$sample77);
	}

	@Override
	public final boolean get$fixedFlag$sample77() {
		return fixedFlag$sample77;
	}

	@Override
	public final void set$fixedFlag$sample77(boolean cv$value) {
		fixedFlag$sample77 = cv$value;
		fixedProbFlag$sample77 = (cv$value && fixedProbFlag$sample77);
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
		fixedProbFlag$sample37 = false;
		fixedProbFlag$sample77 = false;
	}

	@Override
	public final double[] get$phi() {
		return phi;
	}

	@Override
	public final void set$phi(double[] cv$value) {
		phi = cv$value;
		setFlag$phi = true;
		fixedProbFlag$sample20 = false;
		fixedProbFlag$sample73 = false;
	}

	@Override
	public final double[] get$sigma() {
		return sigma;
	}

	@Override
	public final void set$sigma(double[] cv$value) {
		sigma = cv$value;
		setFlag$sigma = true;
		fixedProbFlag$sample55 = false;
		fixedProbFlag$sample77 = false;
	}

	@Override
	public final double[] get$x() {
		return x;
	}

	@Override
	public final void set$x(double[] cv$value) {
		x = cv$value;
		setFlag$x = true;
		fixedProbFlag$sample77 = false;
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

	private final void logProbabilityValue$sample20() {
		if(!fixedProbFlag$sample20) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(phi, alpha);
			logProbability$var18 = cv$distributionAccumulator;
			logProbability$phi = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample20 = fixedFlag$sample20;
		} else {
			logProbability$var18 = logProbability$phi;
			logProbability$$model = (logProbability$$model + logProbability$phi);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + logProbability$phi);
		}
	}

	private final void logProbabilityValue$sample37() {
		if(!fixedProbFlag$sample37) {
			double cv$sampleAccumulator = 0.0;
			for(int var35 = 0; var35 < 5; var35 += 1)
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian((mu[var35] / 4.47213595499958))) - 1.4978661367769954);
			logProbability$var24 = cv$sampleAccumulator;
			logProbability$var36 = cv$sampleAccumulator;
			logProbability$mu = (logProbability$mu + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample37)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample37 = fixedFlag$sample37;
		} else {
			logProbability$var24 = logProbability$var36;
			logProbability$mu = (logProbability$mu + logProbability$var36);
			logProbability$$model = (logProbability$$model + logProbability$var36);
			if(fixedFlag$sample37)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var36);
		}
	}

	private final void logProbabilityValue$sample55() {
		if(!fixedProbFlag$sample55) {
			double cv$sampleAccumulator = 0.0;
			for(int var53 = 0; var53 < 5; var53 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(sigma[var53], 1.0, 1.0));
			logProbability$var42 = cv$sampleAccumulator;
			logProbability$var54 = cv$sampleAccumulator;
			logProbability$sigma = (logProbability$sigma + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample55 = fixedFlag$sample55;
		} else {
			logProbability$var42 = logProbability$var54;
			logProbability$sigma = (logProbability$sigma + logProbability$var54);
			logProbability$$model = (logProbability$$model + logProbability$var54);
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var54);
		}
	}

	private final void logProbabilityValue$sample73() {
		if(!fixedProbFlag$sample73) {
			double cv$accumulator = 0.0;
			for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1) {
				int cv$sampleValue = z[i$var68];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < phi.length))?Math.log(phi[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var69[i$var68] = cv$distributionAccumulator;
				logProbability$sample73[i$var68] = cv$distributionAccumulator;
			}
			logProbability$z = (logProbability$z + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample73)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample73 = (fixedFlag$sample73 && fixedFlag$sample20);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1) {
				double cv$rvAccumulator = logProbability$sample73[i$var68];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var69[i$var68] = cv$rvAccumulator;
			}
			logProbability$z = (logProbability$z + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample73)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample77() {
		if(!fixedProbFlag$sample77) {
			double cv$accumulator = 0.0;
			for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1) {
				double var72 = sigma[z[i$var68]];
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((x[i$var68] - mu[z[i$var68]]) / Math.sqrt(var72))) - (Math.log(var72) * 0.5));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var73[i$var68] = cv$distributionAccumulator;
				logProbability$sample77[i$var68] = cv$distributionAccumulator;
			}
			logProbability$x = (logProbability$x + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample77 = (((fixedFlag$sample77 && fixedFlag$sample37) && fixedFlag$sample55) && fixedFlag$sample73);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1) {
				double cv$rvAccumulator = logProbability$sample77[i$var68];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var73[i$var68] = cv$rvAccumulator;
			}
			logProbability$x = (logProbability$x + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample20() {
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			cv$var19$countGlobal[cv$loopIndex] = 0.0;
		for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1)
			cv$var19$countGlobal[z[i$var68]] = (cv$var19$countGlobal[z[i$var68]] + 1.0);
		Conjugates.sampleConjugateDirichletCategorical(RNG$, alpha, cv$var19$countGlobal, phi);
	}

	private final void sample37(int var35) {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1) {
			if((var35 == z[i$var68])) {
				cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
				cv$sum = (cv$sum + x[i$var68]);
				if(cv$sigmaNotFound) {
					cv$sigmaValue = sigma[z[i$var68]];
					cv$sigmaNotFound = false;
				}
			}
		}
		mu[var35] = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 20.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void sample55(int var53) {
		double cv$sum = 0.0;
		int cv$count = 0;
		for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1) {
			if((var53 == z[i$var68])) {
				double cv$var73$diff = (mu[z[i$var68]] - x[i$var68]);
				cv$sum = (cv$sum + (cv$var73$diff * cv$var73$diff));
				cv$count = (cv$count + 1);
			}
		}
		sigma[var53] = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample73(int i$var68) {
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			z[i$var68] = cv$valuePos;
			double cv$temp$2$var72 = sigma[cv$valuePos];
			cv$var70$stateProbabilityGlobal[cv$valuePos] = ((DistributionSampling.logProbabilityGaussian(((x[i$var68] - mu[cv$valuePos]) / Math.sqrt(cv$temp$2$var72))) + ((cv$valuePos < phi.length)?Math.log(phi[cv$valuePos]):Double.NEGATIVE_INFINITY)) - (Math.log(cv$temp$2$var72) * 0.5));
		}
		double cv$logSum;
		double cv$lseMax = cv$var70$stateProbabilityGlobal[0];
		{
			double cv$lseElementValue = cv$var70$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		{
			double cv$lseElementValue = cv$var70$stateProbabilityGlobal[2];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		{
			double cv$lseElementValue = cv$var70$stateProbabilityGlobal[3];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		double cv$lseElementValue = cv$var70$stateProbabilityGlobal[4];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < 5; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var70$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
				cv$var70$stateProbabilityGlobal[cv$indexName] = 0.2;
		} else {
			for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
				cv$var70$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var70$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = 5; cv$indexName < cv$var70$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var70$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		z[i$var68] = DistributionSampling.sampleCategorical(RNG$, cv$var70$stateProbabilityGlobal);
	}

	@Override
	public final void allocateScratch() {
		cv$var19$countGlobal = new double[5];
		cv$var70$stateProbabilityGlobal = new double[5];
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
		logProbability$var69 = new double[length$xMeasured];
		logProbability$sample73 = new double[length$xMeasured];
		logProbability$var73 = new double[length$xMeasured];
		logProbability$sample77 = new double[length$xMeasured];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		if(!fixedFlag$sample37) {
			for(int var35 = 0; var35 < 5; var35 += 1)
				mu[var35] = (DistributionSampling.sampleGaussian(RNG$) * 4.47213595499958);
		}
		if(!fixedFlag$sample55) {
			for(int var53 = 0; var53 < 5; var53 += 1)
				sigma[var53] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1) {
			if(!fixedFlag$sample73)
				z[i$var68] = DistributionSampling.sampleCategorical(RNG$, phi);
			if(!fixedFlag$sample77)
				x[i$var68] = ((Math.sqrt(sigma[z[i$var68]]) * DistributionSampling.sampleGaussian(RNG$)) + mu[z[i$var68]]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		if(!fixedFlag$sample37) {
			for(int var35 = 0; var35 < 5; var35 += 1)
				mu[var35] = (DistributionSampling.sampleGaussian(RNG$) * 4.47213595499958);
		}
		if(!fixedFlag$sample55) {
			for(int var53 = 0; var53 < 5; var53 += 1)
				sigma[var53] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample73) {
			for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1)
				z[i$var68] = DistributionSampling.sampleCategorical(RNG$, phi);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		if(!fixedFlag$sample37) {
			for(int var35 = 0; var35 < 5; var35 += 1)
				mu[var35] = (DistributionSampling.sampleGaussian(RNG$) * 4.47213595499958);
		}
		if(!fixedFlag$sample55) {
			for(int var53 = 0; var53 < 5; var53 += 1)
				sigma[var53] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample73) {
			for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1)
				z[i$var68] = DistributionSampling.sampleCategorical(RNG$, phi);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample20)
				sample20();
			if(!fixedFlag$sample37) {
				for(int var35 = 0; var35 < 5; var35 += 1)
					sample37(var35);
			}
			if(!fixedFlag$sample55) {
				for(int var53 = 0; var53 < 5; var53 += 1)
					sample55(var53);
			}
			if(!fixedFlag$sample73) {
				for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1)
					sample73(i$var68);
			}
		} else {
			if(!fixedFlag$sample73) {
				for(int i$var68 = (length$xMeasured - 1); i$var68 >= 0; i$var68 -= 1)
					sample73(i$var68);
			}
			if(!fixedFlag$sample55) {
				for(int var53 = 4; var53 >= 0; var53 -= 1)
					sample55(var53);
			}
			if(!fixedFlag$sample37) {
				for(int var35 = 4; var35 >= 0; var35 -= 1)
					sample37(var35);
			}
			if(!fixedFlag$sample20)
				sample20();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		for(int i$var15 = 0; i$var15 < 5; i$var15 += 1)
			alpha[i$var15] = 1.0;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var18 = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$phi = 0.0;
		logProbability$var24 = 0.0;
		logProbability$mu = 0.0;
		if(!fixedProbFlag$sample37)
			logProbability$var36 = 0.0;
		logProbability$var42 = 0.0;
		logProbability$sigma = 0.0;
		if(!fixedProbFlag$sample55)
			logProbability$var54 = 0.0;
		for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1)
			logProbability$var69[i$var68] = 0.0;
		logProbability$z = 0.0;
		if(!fixedProbFlag$sample73) {
			for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1)
				logProbability$sample73[i$var68] = 0.0;
		}
		for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1)
			logProbability$var73[i$var68] = 0.0;
		logProbability$x = 0.0;
		if(!fixedProbFlag$sample77) {
			for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1)
				logProbability$sample77[i$var68] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		if(fixedFlag$sample37)
			logProbabilityValue$sample37();
		if(fixedFlag$sample55)
			logProbabilityValue$sample55();
		if(fixedFlag$sample73)
			logProbabilityValue$sample73();
		logProbabilityValue$sample77();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample37();
		logProbabilityValue$sample55();
		logProbabilityValue$sample73();
		logProbabilityValue$sample77();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample37();
		logProbabilityValue$sample55();
		logProbabilityValue$sample73();
		logProbabilityValue$sample77();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, alpha, phi);
		if(!fixedFlag$sample37) {
			for(int var35 = 0; var35 < 5; var35 += 1)
				mu[var35] = (DistributionSampling.sampleGaussian(RNG$) * 4.47213595499958);
		}
		if(!fixedFlag$sample55) {
			for(int var53 = 0; var53 < 5; var53 += 1)
				sigma[var53] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample73) {
			for(int i$var68 = 0; i$var68 < length$xMeasured; i$var68 += 1)
				z[i$var68] = DistributionSampling.sampleCategorical(RNG$, phi);
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