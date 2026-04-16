package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class LinearRegressionBasic2$SingleThreadCPU extends CoreModelSingleThreadCPU implements LinearRegressionBasic2$CoreInterface {
double b0;
	double b1;
	boolean constrainedFlag$sample11 = true;
	boolean constrainedFlag$sample16 = true;
	boolean constrainedFlag$sample7 = true;
	boolean fixedFlag$sample11 = false;
	boolean fixedFlag$sample16 = false;
	boolean fixedFlag$sample7 = false;
	boolean fixedProbFlag$sample11 = false;
	boolean fixedProbFlag$sample16 = false;
	boolean fixedProbFlag$sample33 = false;
	boolean fixedProbFlag$sample7 = false;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$b0;
	double logProbability$b1;
	double[] logProbability$sample33;
	double logProbability$var16;
	double logProbability$variance;
	double logProbability$y;
	int noSamples;
	boolean system$gibbsForward = true;
	double variance;
	double[] x;
	double[] y;
	double[] yMeasured;

	public LinearRegressionBasic2$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$b0() {
		return b0;
	}

	@Override
	public final void set$b0(double cv$value, boolean allocated$) {
		b0 = cv$value;
		fixedProbFlag$sample7 = false;
		fixedProbFlag$sample33 = false;
	}

	@Override
	public final double get$b1() {
		return b1;
	}

	@Override
	public final void set$b1(double cv$value, boolean allocated$) {
		b1 = cv$value;
		fixedProbFlag$sample11 = false;
		fixedProbFlag$sample33 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample11() {
		return fixedFlag$sample11;
	}

	@Override
	public final void set$fixedFlag$sample11(boolean cv$value, boolean allocated$) {
		fixedFlag$sample11 = cv$value;
		constrainedFlag$sample11 = (cv$value || constrainedFlag$sample11);
		fixedProbFlag$sample11 = (cv$value && fixedProbFlag$sample11);
		fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
	}

	@Override
	public final boolean get$fixedFlag$sample16() {
		return fixedFlag$sample16;
	}

	@Override
	public final void set$fixedFlag$sample16(boolean cv$value, boolean allocated$) {
		fixedFlag$sample16 = cv$value;
		constrainedFlag$sample16 = (cv$value || constrainedFlag$sample16);
		fixedProbFlag$sample16 = (cv$value && fixedProbFlag$sample16);
		fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
	}

	@Override
	public final boolean get$fixedFlag$sample7() {
		return fixedFlag$sample7;
	}

	@Override
	public final void set$fixedFlag$sample7(boolean cv$value, boolean allocated$) {
		fixedFlag$sample7 = cv$value;
		constrainedFlag$sample7 = (cv$value || constrainedFlag$sample7);
		fixedProbFlag$sample7 = (cv$value && fixedProbFlag$sample7);
		fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
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
	public final void set$variance(double cv$value, boolean allocated$) {
		variance = cv$value;
		fixedProbFlag$sample16 = false;
		fixedProbFlag$sample33 = false;
	}

	@Override
	public final double[] get$x() {
		return x;
	}

	@Override
	public final void set$x(double[] cv$value, boolean allocated$) {
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
	public final void set$yMeasured(double[] cv$value, boolean allocated$) {
		yMeasured = cv$value;
	}

	private final void drawValueSample11() {
		b1 = ((DistributionSampling.sampleGaussian(RNG$) * 2.23606797749979) + 1.0);
	}

	private final void drawValueSample16() {
		variance = (1 / DistributionSampling.sampleGamma(RNG$, 1.0, 1.0));
	}

	private final void drawValueSample7() {
		b0 = (DistributionSampling.sampleGaussian(RNG$) * 1.4142135623730951);
	}

	private final void inferSample11() {
		constrainedFlag$sample11 = false;
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i = 0; i < noSamples; i += 1) {
			constrainedFlag$sample11 = true;
			double cv$denominator = x[i];
			cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
			cv$sum = (cv$sum + (cv$denominator * (y[i] - b0)));
			if(cv$sigmaNotFound) {
				cv$sigmaValue = variance;
				cv$sigmaNotFound = false;
			}
		}
		if(constrainedFlag$sample11)
			b1 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 1.0, 5.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void inferSample16() {
		constrainedFlag$sample16 = false;
		double cv$sum = 0.0;
		int cv$count = 0;
		for(int i = 0; i < noSamples; i += 1) {
			constrainedFlag$sample16 = true;
			double cv$var32$diff = ((b0 + (b1 * x[i])) - y[i]);
			cv$sum = (cv$sum + (cv$var32$diff * cv$var32$diff));
			cv$count = (cv$count + 1);
		}
		if(constrainedFlag$sample16)
			variance = (1 / Conjugates.sampleConjugateGammaGaussian(RNG$, 1.0, 1.0, cv$sum, cv$count));
	}

	private final void inferSample7() {
		constrainedFlag$sample7 = false;
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i = 0; i < noSamples; i += 1) {
			constrainedFlag$sample7 = true;
			cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
			cv$sum = ((cv$sum + y[i]) - (b1 * x[i]));
			if(cv$sigmaNotFound) {
				cv$sigmaValue = variance;
				cv$sigmaNotFound = false;
			}
		}
		if(constrainedFlag$sample7)
			b0 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 2.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void logProbabilityValue$sample11() {
		if(!fixedProbFlag$sample11) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((b1 - 1.0) / 2.23606797749979)) - 0.8047189562170501);
			logProbability$b1 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample11)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample11 = fixedFlag$sample11;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$b1);
			if(fixedFlag$sample11)
				logProbability$$evidence = (logProbability$$evidence + logProbability$b1);
		}
	}

	private final void logProbabilityValue$sample16() {
		if(!fixedProbFlag$sample16) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityGamma((1 / variance), 1.0, 1.0);
			logProbability$var16 = cv$distributionAccumulator;
			logProbability$variance = (logProbability$variance + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample16 = fixedFlag$sample16;
		} else {
			logProbability$variance = (logProbability$variance + logProbability$var16);
			logProbability$$model = (logProbability$$model + logProbability$var16);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var16);
		}
	}

	private final void logProbabilityValue$sample33() {
		if(!fixedProbFlag$sample33) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noSamples; i += 1) {
				double cv$distributionAccumulator = ((0.0 < variance)?(DistributionSampling.logProbabilityGaussian(((y[i] - (b0 + (b1 * x[i]))) / Math.sqrt(variance))) - (Math.log(variance) * 0.5)):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample33[i] = cv$distributionAccumulator;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample33 = ((fixedFlag$sample7 && fixedFlag$sample11) && fixedFlag$sample16);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noSamples; i += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample33[i]);
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample7() {
		if(!fixedProbFlag$sample7) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((b0 / 1.4142135623730951)) - 0.34657359027997264);
			logProbability$b0 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample7)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample7 = fixedFlag$sample7;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$b0);
			if(fixedFlag$sample7)
				logProbability$$evidence = (logProbability$$evidence + logProbability$b0);
		}
	}

	@Override
	public final void allocate() {
		y = new double[x.length];
		logProbability$sample33 = new double[x.length];
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample7)
			b0 = (DistributionSampling.sampleGaussian(RNG$) * 1.4142135623730951);
		if(!fixedFlag$sample11)
			b1 = ((DistributionSampling.sampleGaussian(RNG$) * 2.23606797749979) + 1.0);
		if(!fixedFlag$sample16)
			variance = (1 / DistributionSampling.sampleGamma(RNG$, 1.0, 1.0));
		for(int i = 0; i < noSamples; i += 1)
			y[i] = (((Math.sqrt(variance) * DistributionSampling.sampleGaussian(RNG$)) + b0) + (b1 * x[i]));
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample7)
			b0 = (DistributionSampling.sampleGaussian(RNG$) * 1.4142135623730951);
		if(!fixedFlag$sample11)
			b1 = ((DistributionSampling.sampleGaussian(RNG$) * 2.23606797749979) + 1.0);
		if(!fixedFlag$sample16)
			variance = (1 / DistributionSampling.sampleGamma(RNG$, 1.0, 1.0));
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample7)
			b0 = (DistributionSampling.sampleGaussian(RNG$) * 1.4142135623730951);
		if(!fixedFlag$sample11)
			b1 = ((DistributionSampling.sampleGaussian(RNG$) * 2.23606797749979) + 1.0);
		if(!fixedFlag$sample16)
			variance = (1 / DistributionSampling.sampleGamma(RNG$, 1.0, 1.0));
		for(int i = 0; i < noSamples; i += 1)
			y[i] = (((Math.sqrt(variance) * DistributionSampling.sampleGaussian(RNG$)) + b0) + (b1 * x[i]));
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample7)
			b0 = (DistributionSampling.sampleGaussian(RNG$) * 1.4142135623730951);
		if(!fixedFlag$sample11)
			b1 = ((DistributionSampling.sampleGaussian(RNG$) * 2.23606797749979) + 1.0);
		if(!fixedFlag$sample16)
			variance = (1 / DistributionSampling.sampleGamma(RNG$, 1.0, 1.0));
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample7)
			b0 = (DistributionSampling.sampleGaussian(RNG$) * 1.4142135623730951);
		if(!fixedFlag$sample11)
			b1 = ((DistributionSampling.sampleGaussian(RNG$) * 2.23606797749979) + 1.0);
		if(!fixedFlag$sample16)
			variance = (1 / DistributionSampling.sampleGamma(RNG$, 1.0, 1.0));
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample7)
				inferSample7();
			if(!fixedFlag$sample11)
				inferSample11();
			if(!fixedFlag$sample16)
				inferSample16();
		} else {
			if(!fixedFlag$sample16)
				inferSample16();
			if(!fixedFlag$sample11)
				inferSample11();
			if(!fixedFlag$sample7)
				inferSample7();
		}
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample7)
			drawValueSample7();
		if(!constrainedFlag$sample11)
			drawValueSample11();
		if(!constrainedFlag$sample16)
			drawValueSample16();
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		if(!fixedProbFlag$sample7)
			logProbability$b0 = Double.NaN;
		if(!fixedProbFlag$sample11)
			logProbability$b1 = Double.NaN;
		logProbability$variance = 0.0;
		if(!fixedProbFlag$sample16)
			logProbability$var16 = Double.NaN;
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample33) {
			for(int i = 0; i < noSamples; i += 1)
				logProbability$sample33[i] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		noSamples = x.length;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample7)
			logProbabilityValue$sample7();
		if(fixedFlag$sample11)
			logProbabilityValue$sample11();
		if(fixedFlag$sample16)
			logProbabilityValue$sample16();
		logProbabilityValue$sample33();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample7();
		logProbabilityValue$sample11();
		logProbabilityValue$sample16();
		logProbabilityValue$sample33();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample7();
		logProbabilityValue$sample11();
		logProbabilityValue$sample16();
		logProbabilityValue$sample33();
	}

	@Override
	public final void propagateObservedValues() {
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
		     + "public model LinearRegressionBasic2(double[] x, double[] yMeasured) {\n"
		     + "    \n"
		     + "        int noSamples = x.length;\n"
		     + "        double b0 = gaussian(0.0, 2.0).sample();\n"
		     + "        double b1 = gaussian(1.0, 5.0).sample();\n"
		     + "        double variance = 1/gamma(1.0, 1.0).sample();\n"
		     + "        double[] y = new double[noSamples];\n"
		     + "        for(int i:[0..noSamples)) {\n"
		     + "           y[i] = gaussian(b0 + b1 * x[i], variance).sample();\n"
		     + "        }\n"
		     + "        y.observe(yMeasured);\n"
		     + "}\n"
		     + "";
	}
}