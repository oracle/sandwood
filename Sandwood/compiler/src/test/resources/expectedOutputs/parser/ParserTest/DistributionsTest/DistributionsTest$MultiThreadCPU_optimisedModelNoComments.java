package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DistributionsTest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DistributionsTest$CoreInterface {
	private double b0;
	private double b1;
	private boolean fixedFlag$sample14 = false;
	private boolean fixedFlag$sample18 = false;
	private boolean fixedFlag$sample27 = false;
	private boolean fixedProbFlag$sample14 = false;
	private boolean fixedProbFlag$sample18 = false;
	private boolean fixedProbFlag$sample27 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b0;
	private double logProbability$b1;
	private double[] logProbability$sample27;
	private double logProbability$var10;
	private double logProbability$var14;
	private double[] logProbability$var23;
	private double logProbability$y;
	private int noSamples;
	private boolean setFlag$y = false;
	private boolean system$gibbsForward = true;
	private double[] x;
	private double[] y;
	private double[] yMeasured;

	public DistributionsTest$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$b0() {
		return b0;
	}

	@Override
	public final void set$b0(double cv$value) {
		b0 = cv$value;
		fixedProbFlag$sample14 = false;
		fixedProbFlag$sample27 = false;
	}

	@Override
	public final double get$b1() {
		return b1;
	}

	@Override
	public final void set$b1(double cv$value) {
		b1 = cv$value;
		fixedProbFlag$sample18 = false;
		fixedProbFlag$sample27 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample14() {
		return fixedFlag$sample14;
	}

	@Override
	public final void set$fixedFlag$sample14(boolean cv$value) {
		fixedFlag$sample14 = cv$value;
		fixedProbFlag$sample14 = (cv$value && fixedProbFlag$sample14);
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
	}

	@Override
	public final boolean get$fixedFlag$sample18() {
		return fixedFlag$sample18;
	}

	@Override
	public final void set$fixedFlag$sample18(boolean cv$value) {
		fixedFlag$sample18 = cv$value;
		fixedProbFlag$sample18 = (cv$value && fixedProbFlag$sample18);
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
	}

	@Override
	public final boolean get$fixedFlag$sample27() {
		return fixedFlag$sample27;
	}

	@Override
	public final void set$fixedFlag$sample27(boolean cv$value) {
		fixedFlag$sample27 = cv$value;
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
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
	public final double get$logProbability$y() {
		return logProbability$y;
	}

	@Override
	public final int get$noSamples() {
		return noSamples;
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
		fixedProbFlag$sample27 = false;
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityCauchy(b0, 0.0, 2.0);
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityHalfCauchy(b1, 1.0, 5.0);
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

	private final void logProbabilityValue$sample27() {
		if(!fixedProbFlag$sample27) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noSamples; i += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityStudentT(y[i], (b0 + (b1 * x[i])));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var23[i] = cv$distributionAccumulator;
				logProbability$sample27[i] = cv$distributionAccumulator;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample27 = ((fixedFlag$sample27 && fixedFlag$sample14) && fixedFlag$sample18);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noSamples; i += 1) {
				double cv$rvAccumulator = logProbability$sample27[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var23[i] = cv$rvAccumulator;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample14() {
		double cv$originalValue = b0;
		double cv$originalProbability;
		double cv$var = ((b0 * b0) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + b0);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityCauchy(b0, 0.0, 2.0);
			for(int i = 0; i < noSamples; i += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityStudentT(y[i], (b0 + (b1 * x[i]))) + cv$accumulatedProbabilities);
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		b0 = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityCauchy(cv$proposedValue, 0.0, 2.0);
		for(int i = 0; i < noSamples; i += 1)
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityStudentT(y[i], (cv$proposedValue + (b1 * x[i]))) + cv$accumulatedProbabilities);
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			b0 = cv$originalValue;
	}

	private final void sample18() {
		double cv$originalValue = b1;
		double cv$originalProbability;
		double cv$var = ((b1 * b1) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + b1);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityHalfCauchy(b1, 1.0, 5.0);
			for(int i = 0; i < noSamples; i += 1)
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityStudentT(y[i], (b0 + (b1 * x[i]))) + cv$accumulatedProbabilities);
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		b1 = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityHalfCauchy(cv$proposedValue, 1.0, 5.0);
		for(int i = 0; i < noSamples; i += 1)
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityStudentT(y[i], (b0 + (cv$proposedValue * x[i]))) + cv$accumulatedProbabilities);
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			b1 = cv$originalValue;
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!setFlag$y)
			y = new double[x.length];
		logProbability$var23 = new double[x.length];
		logProbability$sample27 = new double[x.length];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample14)
			b0 = DistributionSampling.sampleCauchy(RNG$, 0.0, 2.0);
		if(!fixedFlag$sample18)
			b1 = DistributionSampling.sampleHalfCauchy(RNG$, 1.0, 5.0);
		if(!fixedFlag$sample27)
			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							y[i] = DistributionSampling.sampleStudentT(RNG$1, (b0 + (b1 * x[i])));
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample14)
			b0 = DistributionSampling.sampleCauchy(RNG$, 0.0, 2.0);
		if(!fixedFlag$sample18)
			b1 = DistributionSampling.sampleHalfCauchy(RNG$, 1.0, 5.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample14)
			b0 = DistributionSampling.sampleCauchy(RNG$, 0.0, 2.0);
		if(!fixedFlag$sample18)
			b1 = DistributionSampling.sampleHalfCauchy(RNG$, 1.0, 5.0);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample14)
				sample14();
			if(!fixedFlag$sample18)
				sample18();
		} else {
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
		for(int i = 0; i < noSamples; i += 1)
			logProbability$var23[i] = 0.0;
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample27) {
			for(int i = 0; i < noSamples; i += 1)
				logProbability$sample27[i] = 0.0;
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
		logProbabilityValue$sample27();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample14();
		logProbabilityValue$sample18();
		logProbabilityValue$sample27();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample14();
		logProbabilityValue$sample18();
		logProbabilityValue$sample27();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample14)
			b0 = DistributionSampling.sampleCauchy(RNG$, 0.0, 2.0);
		if(!fixedFlag$sample18)
			b1 = DistributionSampling.sampleHalfCauchy(RNG$, 1.0, 5.0);
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model DistributionsTest(double[] x, double[] yMeasured) {\n    int noSamples = x.length;\n    double b0 = cauchy(0.0, 2.0).sample();\n    double b1 = halfCauchy(1.0, 5.0).sample();\n    double[] y = new double[noSamples];\n    for(int i:[0..noSamples)) {\n       y[i] = studentT(b0 + b1 * x[i]).sample();\n    }\n    y.observe(yMeasured);\n}\n";
	}
}