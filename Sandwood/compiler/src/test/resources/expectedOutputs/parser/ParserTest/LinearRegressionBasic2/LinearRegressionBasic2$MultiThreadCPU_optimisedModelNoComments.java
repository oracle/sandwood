package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LinearRegressionBasic2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements LinearRegressionBasic2$CoreInterface {
	private double b0;
	private double b1;
	private boolean fixedFlag$sample14 = false;
	private boolean fixedFlag$sample18 = false;
	private boolean fixedFlag$sample23 = false;
	private boolean fixedFlag$sample33 = false;
	private boolean fixedProbFlag$sample14 = false;
	private boolean fixedProbFlag$sample18 = false;
	private boolean fixedProbFlag$sample23 = false;
	private boolean fixedProbFlag$sample33 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b0;
	private double logProbability$b1;
	private double[] logProbability$sample33;
	private double logProbability$var10;
	private double logProbability$var14;
	private double logProbability$var19;
	private double logProbability$var20;
	private double[] logProbability$var29;
	private double logProbability$variance;
	private double logProbability$y;
	private int noSamples;
	private boolean setFlag$y = false;
	private boolean system$gibbsForward = true;
	private double variance;
	private double[] x;
	private double[] y;
	private double[] yMeasured;

	public LinearRegressionBasic2$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$b0() {
		return b0;
	}

	@Override
	public final void set$b0(double cv$value) {
		b0 = cv$value;
	}

	@Override
	public final double get$b1() {
		return b1;
	}

	@Override
	public final void set$b1(double cv$value) {
		b1 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample14() {
		return fixedFlag$sample14;
	}

	@Override
	public final void set$fixedFlag$sample14(boolean cv$value) {
		fixedFlag$sample14 = cv$value;
		fixedProbFlag$sample14 = (cv$value && fixedProbFlag$sample14);
		fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
	}

	@Override
	public final boolean get$fixedFlag$sample18() {
		return fixedFlag$sample18;
	}

	@Override
	public final void set$fixedFlag$sample18(boolean cv$value) {
		fixedFlag$sample18 = cv$value;
		fixedProbFlag$sample18 = (cv$value && fixedProbFlag$sample18);
		fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
	}

	@Override
	public final boolean get$fixedFlag$sample23() {
		return fixedFlag$sample23;
	}

	@Override
	public final void set$fixedFlag$sample23(boolean cv$value) {
		fixedFlag$sample23 = cv$value;
		fixedProbFlag$sample23 = (cv$value && fixedProbFlag$sample23);
		fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
	}

	@Override
	public final boolean get$fixedFlag$sample33() {
		return fixedFlag$sample33;
	}

	@Override
	public final void set$fixedFlag$sample33(boolean cv$value) {
		fixedFlag$sample33 = cv$value;
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
	public final void set$variance(double cv$value) {
		variance = cv$value;
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
	public final void set$y(double[] cv$value) {
		y = cv$value;
		setFlag$y = true;
	}

	@Override
	public final double[] get$yMeasured() {
		return yMeasured;
	}

	@Override
	public final void set$yMeasured(double[] cv$value) {
		yMeasured = cv$value;
	}

	private final void logProbabilityValue$sample14() {
		if(!fixedProbFlag$sample14) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(b0, 0.0, 2.0);
			logProbability$var10 = cv$distributionAccumulator;
			logProbability$b0 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample14)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample14 = fixedFlag$sample14;
		} else {
			logProbability$var10 = logProbability$b0;
			logProbability$$model = (logProbability$$model + logProbability$b0);
			if(fixedFlag$sample14)
				logProbability$$evidence = (logProbability$$evidence + logProbability$b0);
		}
	}

	private final void logProbabilityValue$sample18() {
		if(!fixedProbFlag$sample18) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(b1, 1.0, 5.0);
			logProbability$var14 = cv$distributionAccumulator;
			logProbability$b1 = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample18 = fixedFlag$sample18;
		} else {
			logProbability$var14 = logProbability$b1;
			logProbability$$model = (logProbability$$model + logProbability$b1);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + logProbability$b1);
		}
	}

	private final void logProbabilityValue$sample23() {
		if(!fixedProbFlag$sample23) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityGamma((1 / variance), 1.0, 1.0);
			logProbability$var19 = cv$distributionAccumulator;
			logProbability$var20 = cv$distributionAccumulator;
			logProbability$variance = (logProbability$variance + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample23)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample23 = fixedFlag$sample23;
		} else {
			logProbability$var19 = logProbability$var20;
			logProbability$variance = (logProbability$variance + logProbability$var20);
			logProbability$$model = (logProbability$$model + logProbability$var20);
			if(fixedFlag$sample23)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var20);
		}
	}

	private final void logProbabilityValue$sample33() {
		if(!fixedProbFlag$sample33) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noSamples; i += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(y[i], (b0 + (b1 * x[i])), variance);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var29[i] = cv$distributionAccumulator;
				logProbability$sample33[i] = cv$distributionAccumulator;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample33 = (((fixedFlag$sample33 && fixedFlag$sample14) && fixedFlag$sample18) && fixedFlag$sample23);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noSamples; i += 1) {
				double cv$rvAccumulator = logProbability$sample33[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var29[i] = cv$rvAccumulator;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample14() {
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

	private final void sample18() {
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

	private final void sample23() {
		double cv$sum = 0.0;
		int cv$count = 0;
		for(int i = 0; i < noSamples; i += 1) {
			double cv$var29$diff = ((b0 + (b1 * x[i])) - y[i]);
			cv$sum = (cv$sum + (cv$var29$diff * cv$var29$diff));
			cv$count = (cv$count + 1);
		}
		variance = (1 / Conjugates.sampleConjugateGammaGaussian(RNG$, 1.0, 1.0, cv$sum, cv$count));
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!setFlag$y)
			y = new double[x.length];
		logProbability$var29 = new double[x.length];
		logProbability$sample33 = new double[x.length];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample14)
			b0 = DistributionSampling.sampleGaussian(RNG$, 0.0, 2.0);
		if(!fixedFlag$sample18)
			b1 = DistributionSampling.sampleGaussian(RNG$, 1.0, 5.0);
		if(!fixedFlag$sample23)
			variance = (1 / DistributionSampling.sampleGamma(RNG$, 1.0, 1.0));
		if(!fixedFlag$sample33)
			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							y[i] = DistributionSampling.sampleGaussian(RNG$1, (b0 + (b1 * x[i])), variance);
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample14)
			b0 = DistributionSampling.sampleGaussian(RNG$, 0.0, 2.0);
		if(!fixedFlag$sample18)
			b1 = DistributionSampling.sampleGaussian(RNG$, 1.0, 5.0);
		if(!fixedFlag$sample23)
			variance = (1 / DistributionSampling.sampleGamma(RNG$, 1.0, 1.0));
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample14)
			b0 = DistributionSampling.sampleGaussian(RNG$, 0.0, 2.0);
		if(!fixedFlag$sample18)
			b1 = DistributionSampling.sampleGaussian(RNG$, 1.0, 5.0);
		if(!fixedFlag$sample23)
			variance = (1 / DistributionSampling.sampleGamma(RNG$, 1.0, 1.0));
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample14)
				sample14();
			if(!fixedFlag$sample18)
				sample18();
			if(!fixedFlag$sample23)
				sample23();
		} else {
			if(!fixedFlag$sample23)
				sample23();
			if(!fixedFlag$sample18)
				sample18();
			if(!fixedFlag$sample14)
				sample14();
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
		logProbability$var10 = 0.0;
		if(!fixedProbFlag$sample14)
			logProbability$b0 = 0.0;
		logProbability$var14 = 0.0;
		if(!fixedProbFlag$sample18)
			logProbability$b1 = 0.0;
		logProbability$var19 = 0.0;
		logProbability$variance = 0.0;
		if(!fixedProbFlag$sample23)
			logProbability$var20 = 0.0;
		for(int i = 0; i < noSamples; i += 1)
			logProbability$var29[i] = 0.0;
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample33) {
			for(int i = 0; i < noSamples; i += 1)
				logProbability$sample33[i] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample14)
			logProbabilityValue$sample14();
		if(fixedFlag$sample18)
			logProbabilityValue$sample18();
		if(fixedFlag$sample23)
			logProbabilityValue$sample23();
		logProbabilityValue$sample33();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample14();
		logProbabilityValue$sample18();
		logProbabilityValue$sample23();
		logProbabilityValue$sample33();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample14();
		logProbabilityValue$sample18();
		logProbabilityValue$sample23();
		logProbabilityValue$sample33();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample14)
			b0 = DistributionSampling.sampleGaussian(RNG$, 0.0, 2.0);
		if(!fixedFlag$sample18)
			b1 = DistributionSampling.sampleGaussian(RNG$, 1.0, 5.0);
		if(!fixedFlag$sample23)
			variance = (1 / DistributionSampling.sampleGamma(RNG$, 1.0, 1.0));
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model LinearRegressionBasic2(double[] x, double[] yMeasured) {\n    \n        int noSamples = x.length;\n        double b0 = gaussian(0.0, 2.0).sample();\n        double b1 = gaussian(1.0, 5.0).sample();\n        double variance = 1/gamma(1.0, 1.0).sample();\n        double[] y = new double[noSamples];\n        for(int i:[0..noSamples)) {\n           y[i] = gaussian(b0 + b1 * x[i], variance).sample();\n        }\n        y.observe(yMeasured);\n}\n";
	}
}