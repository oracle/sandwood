package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DistributionsTest$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements DistributionsTest$CoreInterface {
	private double b0;
	private double b1;
	private boolean fixedFlag$sample11 = false;
	private boolean fixedFlag$sample7 = false;
	private boolean fixedProbFlag$sample11 = false;
	private boolean fixedProbFlag$sample27 = false;
	private boolean fixedProbFlag$sample7 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b0;
	private double logProbability$b1;
	private double[] logProbability$sample27;
	private double logProbability$var10;
	private double[] logProbability$var26;
	private double logProbability$var6;
	private double logProbability$y;
	private int noSamples;
	private boolean system$gibbsForward = true;
	private double[] x;
	private double[] y;
	private double[] yMeasured;

	public DistributionsTest$SingleThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample27 = false;
	}

	@Override
	public final double get$b1() {
		return b1;
	}

	@Override
	public final void set$b1(double cv$value) {
		b1 = cv$value;
		fixedProbFlag$sample11 = false;
		fixedProbFlag$sample27 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample11() {
		return fixedFlag$sample11;
	}

	@Override
	public final void set$fixedFlag$sample11(boolean cv$value) {
		fixedFlag$sample11 = cv$value;
		fixedProbFlag$sample11 = (cv$value && fixedProbFlag$sample11);
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
	}

	@Override
	public final boolean get$fixedFlag$sample7() {
		return fixedFlag$sample7;
	}

	@Override
	public final void set$fixedFlag$sample7(boolean cv$value) {
		fixedFlag$sample7 = cv$value;
		fixedProbFlag$sample7 = (cv$value && fixedProbFlag$sample7);
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
	public final double[] get$yMeasured() {
		return yMeasured;
	}

	@Override
	public final void set$yMeasured(double[] cv$value) {
		yMeasured = cv$value;
	}

	private final void logProbabilityValue$sample11() {
		if(!fixedProbFlag$sample11) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityHalfCauchy(b1, 1.0, 5.0);
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

	private final void logProbabilityValue$sample27() {
		if(!fixedProbFlag$sample27) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noSamples; i += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityStudentT(y[i], (b0 + (b1 * x[i])));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var26[i] = cv$distributionAccumulator;
				logProbability$sample27[i] = cv$distributionAccumulator;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample27 = (fixedFlag$sample7 && fixedFlag$sample11);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noSamples; i += 1) {
				double cv$rvAccumulator = logProbability$sample27[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var26[i] = cv$rvAccumulator;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample7() {
		if(!fixedProbFlag$sample7) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityCauchy(b0, 0.0, 2.0);
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

	private final void sample7() {
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

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		y = new double[x.length];
		logProbability$var26 = new double[x.length];
		logProbability$sample27 = new double[x.length];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample7)
			b0 = DistributionSampling.sampleCauchy(RNG$, 0.0, 2.0);
		if(!fixedFlag$sample11)
			b1 = DistributionSampling.sampleHalfCauchy(RNG$, 1.0, 5.0);
		for(int i = 0; i < noSamples; i += 1)
			y[i] = DistributionSampling.sampleStudentT(RNG$, (b0 + (b1 * x[i])));
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample7)
			b0 = DistributionSampling.sampleCauchy(RNG$, 0.0, 2.0);
		if(!fixedFlag$sample11)
			b1 = DistributionSampling.sampleHalfCauchy(RNG$, 1.0, 5.0);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample7)
			b0 = DistributionSampling.sampleCauchy(RNG$, 0.0, 2.0);
		if(!fixedFlag$sample11)
			b1 = DistributionSampling.sampleHalfCauchy(RNG$, 1.0, 5.0);
		for(int i = 0; i < noSamples; i += 1)
			y[i] = DistributionSampling.sampleStudentT(RNG$, (b0 + (b1 * x[i])));
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample7)
			b0 = DistributionSampling.sampleCauchy(RNG$, 0.0, 2.0);
		if(!fixedFlag$sample11)
			b1 = DistributionSampling.sampleHalfCauchy(RNG$, 1.0, 5.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample7)
			b0 = DistributionSampling.sampleCauchy(RNG$, 0.0, 2.0);
		if(!fixedFlag$sample11)
			b1 = DistributionSampling.sampleHalfCauchy(RNG$, 1.0, 5.0);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample7)
				sample7();
			if(!fixedFlag$sample11)
				sample11();
		} else {
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
			logProbability$b0 = Double.NaN;
		logProbability$var10 = 0.0;
		if(!fixedProbFlag$sample11)
			logProbability$b1 = Double.NaN;
		for(int i = 0; i < noSamples; i += 1)
			logProbability$var26[i] = Double.NaN;
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample27) {
			for(int i = 0; i < noSamples; i += 1)
				logProbability$sample27[i] = Double.NaN;
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample7)
			logProbabilityValue$sample7();
		if(fixedFlag$sample11)
			logProbabilityValue$sample11();
		logProbabilityValue$sample27();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample7();
		logProbabilityValue$sample11();
		logProbabilityValue$sample27();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample7();
		logProbabilityValue$sample11();
		logProbabilityValue$sample27();
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
		     + "public model DistributionsTest(double[] x, double[] yMeasured) {\n"
		     + "    int noSamples = x.length;\n"
		     + "    double b0 = cauchy(0.0, 2.0).sample();\n"
		     + "    double b1 = halfCauchy(1.0, 5.0).sample();\n"
		     + "    double[] y = new double[noSamples];\n"
		     + "    for(int i:[0..noSamples)) {\n"
		     + "       y[i] = studentT(b0 + b1 * x[i]).sample();\n"
		     + "    }\n"
		     + "    y.observe(yMeasured);\n"
		     + "}\n"
		     + "";
	}
}