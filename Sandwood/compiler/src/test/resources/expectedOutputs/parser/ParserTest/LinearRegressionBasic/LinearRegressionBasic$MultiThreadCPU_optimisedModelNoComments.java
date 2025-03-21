package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LinearRegressionBasic$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements LinearRegressionBasic$CoreInterface {
	private double b0;
	private double b1;
	private boolean fixedFlag$sample11 = false;
	private boolean fixedFlag$sample15 = false;
	private boolean fixedFlag$sample7 = false;
	private boolean fixedProbFlag$sample11 = false;
	private boolean fixedProbFlag$sample15 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample7 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b0;
	private double logProbability$b1;
	private double[] logProbability$sample31;
	private double logProbability$var10;
	private double logProbability$var14;
	private double[] logProbability$var30;
	private double logProbability$var6;
	private double logProbability$variance;
	private double logProbability$y;
	private int noSamples;
	private boolean setFlag$y = false;
	private boolean system$gibbsForward = true;
	private double variance;
	private double[] x;
	private double[] y;
	private double[] yMeasured;

	public LinearRegressionBasic$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$b0() {
		return b0;
	}

	@Override
	public final void set$b0(double cv$value) {
		b0 = cv$value;
		fixedProbFlag$sample7 = false;
		fixedProbFlag$sample31 = false;
	}

	@Override
	public final double get$b1() {
		return b1;
	}

	@Override
	public final void set$b1(double cv$value) {
		b1 = cv$value;
		fixedProbFlag$sample11 = false;
		fixedProbFlag$sample31 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample11() {
		return fixedFlag$sample11;
	}

	@Override
	public final void set$fixedFlag$sample11(boolean cv$value) {
		fixedFlag$sample11 = cv$value;
		fixedProbFlag$sample11 = (cv$value && fixedProbFlag$sample11);
		fixedProbFlag$sample31 = (cv$value && fixedProbFlag$sample31);
	}

	@Override
	public final boolean get$fixedFlag$sample15() {
		return fixedFlag$sample15;
	}

	@Override
	public final void set$fixedFlag$sample15(boolean cv$value) {
		fixedFlag$sample15 = cv$value;
		fixedProbFlag$sample15 = (cv$value && fixedProbFlag$sample15);
		fixedProbFlag$sample31 = (cv$value && fixedProbFlag$sample31);
	}

	@Override
	public final boolean get$fixedFlag$sample7() {
		return fixedFlag$sample7;
	}

	@Override
	public final void set$fixedFlag$sample7(boolean cv$value) {
		fixedFlag$sample7 = cv$value;
		fixedProbFlag$sample7 = (cv$value && fixedProbFlag$sample7);
		fixedProbFlag$sample31 = (cv$value && fixedProbFlag$sample31);
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
	public final double get$logProbability$b0() {
		return logProbability$b0;
	}

	@Override
	public final double get$logProbability$b1() {
		return logProbability$b1;
	}

	@Override
	public final double get$logProbability$variance() {
		return logProbability$variance;
	}

	@Override
	public final double get$logProbability$y() {
		return logProbability$y;
	}

	@Override
	public final int get$noSamples() {
		return noSamples;
	}

	@Override
	public final double get$variance() {
		return variance;
	}

	@Override
	public final void set$variance(double cv$value) {
		variance = cv$value;
		fixedProbFlag$sample15 = false;
		fixedProbFlag$sample31 = false;
	}

	@Override
	public final double[] get$x() {
		return x;
	}

	@Override
	public final void set$x(double[] cv$value) {
		x = cv$value;
	}

	@Override
	public final double[] get$y() {
		return y;
	}

	@Override
	public final double[] get$yMeasured() {
		return yMeasured;
	}

	@Override
	public final void set$yMeasured(double[] cv$value) {
		yMeasured = cv$value;
	}

	private final void logProbabilityValue$sample11() {
		if(!fixedProbFlag$sample11) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((b1 - 1.0) / 2.23606797749979)) - 0.8047189562170501);
			logProbability$var10 = cv$distributionAccumulator;
			logProbability$b1 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample11)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample11 = fixedFlag$sample11;
		} else {
			logProbability$var10 = logProbability$b1;
			logProbability$$model = (logProbability$$model + logProbability$b1);
			if(fixedFlag$sample11)
				logProbability$$evidence = (logProbability$$evidence + logProbability$b1);
		}
	}

	private final void logProbabilityValue$sample15() {
		if(!fixedProbFlag$sample15) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityInverseGamma(variance, 1.0, 1.0);
			logProbability$var14 = cv$distributionAccumulator;
			logProbability$variance = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample15 = fixedFlag$sample15;
		} else {
			logProbability$var14 = logProbability$variance;
			logProbability$$model = (logProbability$$model + logProbability$variance);
			if(fixedFlag$sample15)
				logProbability$$evidence = (logProbability$$evidence + logProbability$variance);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!fixedProbFlag$sample31) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noSamples; i += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((y[i] - (b0 + (b1 * x[i]))) / Math.sqrt(variance))) - (Math.log(variance) * 0.5));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var30[i] = cv$distributionAccumulator;
				logProbability$sample31[i] = cv$distributionAccumulator;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample31 = ((fixedFlag$sample7 && fixedFlag$sample11) && fixedFlag$sample15);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noSamples; i += 1) {
				double cv$rvAccumulator = logProbability$sample31[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var30[i] = cv$rvAccumulator;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample7() {
		if(!fixedProbFlag$sample7) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((b0 / 1.4142135623730951)) - 0.34657359027997264);
			logProbability$var6 = cv$distributionAccumulator;
			logProbability$b0 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample7)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample7 = fixedFlag$sample7;
		} else {
			logProbability$var6 = logProbability$b0;
			logProbability$$model = (logProbability$$model + logProbability$b0);
			if(fixedFlag$sample7)
				logProbability$$evidence = (logProbability$$evidence + logProbability$b0);
		}
	}

	private final void sample11() {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i = 0; i < noSamples; i += 1) {
			double cv$denominator = x[i];
			cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
			cv$sum = (cv$sum + (cv$denominator * (y[i] - b0)));
			if(cv$sigmaNotFound) {
				cv$sigmaValue = variance;
				cv$sigmaNotFound = false;
			}
		}
		b1 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 1.0, 5.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void sample15() {
		double cv$sum = 0.0;
		int cv$count = 0;
		for(int i = 0; i < noSamples; i += 1) {
			double cv$var30$diff = ((b0 + (b1 * x[i])) - y[i]);
			cv$sum = (cv$sum + (cv$var30$diff * cv$var30$diff));
			cv$count = (cv$count + 1);
		}
		variance = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample7() {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i = 0; i < noSamples; i += 1) {
			cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
			cv$sum = ((cv$sum + y[i]) - (b1 * x[i]));
			if(cv$sigmaNotFound) {
				cv$sigmaValue = variance;
				cv$sigmaNotFound = false;
			}
		}
		b0 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 2.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		y = new double[x.length];
		logProbability$var30 = new double[x.length];
		logProbability$sample31 = new double[x.length];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample7)
			b0 = (DistributionSampling.sampleGaussian(RNG$) * 1.4142135623730951);
		if(!fixedFlag$sample11)
			b1 = ((DistributionSampling.sampleGaussian(RNG$) * 2.23606797749979) + 1.0);
		if(!fixedFlag$sample15)
			variance = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1)
						y[i] = (((Math.sqrt(variance) * DistributionSampling.sampleGaussian(RNG$1)) + b0) + (b1 * x[i]));
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample7)
			b0 = (DistributionSampling.sampleGaussian(RNG$) * 1.4142135623730951);
		if(!fixedFlag$sample11)
			b1 = ((DistributionSampling.sampleGaussian(RNG$) * 2.23606797749979) + 1.0);
		if(!fixedFlag$sample15)
			variance = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample7)
			b0 = (DistributionSampling.sampleGaussian(RNG$) * 1.4142135623730951);
		if(!fixedFlag$sample11)
			b1 = ((DistributionSampling.sampleGaussian(RNG$) * 2.23606797749979) + 1.0);
		if(!fixedFlag$sample15)
			variance = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample7)
				sample7();
			if(!fixedFlag$sample11)
				sample11();
			if(!fixedFlag$sample15)
				sample15();
		} else {
			if(!fixedFlag$sample15)
				sample15();
			if(!fixedFlag$sample11)
				sample11();
			if(!fixedFlag$sample7)
				sample7();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		noSamples = x.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var6 = 0.0;
		if(!fixedProbFlag$sample7)
			logProbability$b0 = 0.0;
		logProbability$var10 = 0.0;
		if(!fixedProbFlag$sample11)
			logProbability$b1 = 0.0;
		logProbability$var14 = 0.0;
		if(!fixedProbFlag$sample15)
			logProbability$variance = 0.0;
		for(int i = 0; i < noSamples; i += 1)
			logProbability$var30[i] = 0.0;
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample31) {
			for(int i = 0; i < noSamples; i += 1)
				logProbability$sample31[i] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample7)
			logProbabilityValue$sample7();
		if(fixedFlag$sample11)
			logProbabilityValue$sample11();
		if(fixedFlag$sample15)
			logProbabilityValue$sample15();
		logProbabilityValue$sample31();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample7();
		logProbabilityValue$sample11();
		logProbabilityValue$sample15();
		logProbabilityValue$sample31();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample7();
		logProbabilityValue$sample11();
		logProbabilityValue$sample15();
		logProbabilityValue$sample31();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample7)
			b0 = (DistributionSampling.sampleGaussian(RNG$) * 1.4142135623730951);
		if(!fixedFlag$sample11)
			b1 = ((DistributionSampling.sampleGaussian(RNG$) * 2.23606797749979) + 1.0);
		if(!fixedFlag$sample15)
			variance = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = y.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			y[cv$index1] = yMeasured[cv$index1];
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
		     + "public model LinearRegressionBasic(double[] x, double[] yMeasured) {\n"
		     + "    \n"
		     + "        int noSamples = x.length;\n"
		     + "        double b0 = gaussian(0.0, 2.0).sample();\n"
		     + "        double b1 = gaussian(1.0, 5.0).sample();\n"
		     + "        double variance = inverseGamma(1.0, 1.0).sample();\n"
		     + "        double[] y = new double[noSamples];\n"
		     + "        for(int i:[0..noSamples)) {\n"
		     + "           y[i] = gaussian(b0 + b1 * x[i], variance).sample();\n"
		     + "        }\n"
		     + "        y.observe(yMeasured);\n"
		     + "}\n"
		     + "";
	}
}