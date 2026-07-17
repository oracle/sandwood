package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.LinearRegressionBasic2$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.LinearRegressionBasic2.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class LinearRegressionBasic2$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public LinearRegressionBasic2$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample11() {
		state.b1 = ((DistributionSampling.sampleGaussian(state.RNG$) * 2.23606797749979) + 1.0);
	}

	private final void drawValueSample16() {
		state.variance = (1 / DistributionSampling.sampleGamma(state.RNG$, 1.0, 1.0));
	}

	private final void drawValueSample7() {
		state.b0 = (DistributionSampling.sampleGaussian(state.RNG$) * 1.4142135623730951);
	}

	private final void inferSample11() {
		state.constrainedFlag$sample11 = false;
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i = 0; i < state.noSamples; i += 1) {
			state.constrainedFlag$sample11 = true;
			double cv$denominator = state.x[i];
			cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
			cv$sum = (cv$sum + (cv$denominator * (state.y[i] - state.b0)));
			if(cv$sigmaNotFound) {
				cv$sigmaValue = state.variance;
				cv$sigmaNotFound = false;
			}
		}
		if(state.constrainedFlag$sample11)
			state.b1 = Conjugates.sampleConjugateGaussianGaussian(state.RNG$, 1.0, 5.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void inferSample16() {
		state.constrainedFlag$sample16 = false;
		double cv$sum = 0.0;
		int cv$count = 0;
		for(int i = 0; i < state.noSamples; i += 1) {
			state.constrainedFlag$sample16 = true;
			double cv$var32$diff = ((state.b0 + (state.b1 * state.x[i])) - state.y[i]);
			cv$sum = (cv$sum + (cv$var32$diff * cv$var32$diff));
			cv$count = (cv$count + 1);
		}
		if(state.constrainedFlag$sample16)
			state.variance = (1 / Conjugates.sampleConjugateGammaGaussian(state.RNG$, 1.0, 1.0, cv$sum, cv$count));
	}

	private final void inferSample7() {
		state.constrainedFlag$sample7 = false;
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i = 0; i < state.noSamples; i += 1) {
			state.constrainedFlag$sample7 = true;
			cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
			cv$sum = ((cv$sum + state.y[i]) - (state.b1 * state.x[i]));
			if(cv$sigmaNotFound) {
				cv$sigmaValue = state.variance;
				cv$sigmaNotFound = false;
			}
		}
		if(state.constrainedFlag$sample7)
			state.b0 = Conjugates.sampleConjugateGaussianGaussian(state.RNG$, 0.0, 2.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void logProbabilityValue$sample11() {
		if(!state.fixedProbFlag$sample11) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((state.b1 - 1.0) / 2.23606797749979)) - 0.8047189562170501);
			state.logProbability$b1 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample11)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample11 = state.fixedFlag$sample11;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$b1);
			if(state.fixedFlag$sample11)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$b1);
		}
	}

	private final void logProbabilityValue$sample16() {
		if(!state.fixedProbFlag$sample16) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityGamma((1 / state.variance), 1.0, 1.0);
			state.logProbability$var16 = cv$distributionAccumulator;
			state.logProbability$variance = (state.logProbability$variance + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample16)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample16 = state.fixedFlag$sample16;
		} else {
			state.logProbability$variance = (state.logProbability$variance + state.logProbability$var16);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var16);
			if(state.fixedFlag$sample16)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var16);
		}
	}

	private final void logProbabilityValue$sample33() {
		if(!state.fixedProbFlag$sample33) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.noSamples; i += 1) {
				double cv$distributionAccumulator = ((0.0 < state.variance)?(DistributionSampling.logProbabilityGaussian(((state.y[i] - (state.b0 + (state.b1 * state.x[i]))) / Math.sqrt(state.variance))) - (Math.log(state.variance) * 0.5)):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample33[i] = cv$distributionAccumulator;
			}
			state.logProbability$y = (state.logProbability$y + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample33 = ((state.fixedFlag$sample7 && state.fixedFlag$sample11) && state.fixedFlag$sample16);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.noSamples; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample33[i]);
			state.logProbability$y = (state.logProbability$y + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample7() {
		if(!state.fixedProbFlag$sample7) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((state.b0 / 1.4142135623730951)) - 0.34657359027997264);
			state.logProbability$b0 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample7)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample7 = state.fixedFlag$sample7;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$b0);
			if(state.fixedFlag$sample7)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$b0);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample7)
			state.b0 = (DistributionSampling.sampleGaussian(state.RNG$) * 1.4142135623730951);
		if(!state.fixedFlag$sample11)
			state.b1 = ((DistributionSampling.sampleGaussian(state.RNG$) * 2.23606797749979) + 1.0);
		if(!state.fixedFlag$sample16)
			state.variance = (1 / DistributionSampling.sampleGamma(state.RNG$, 1.0, 1.0));
		parallelFor(state.RNG$, 0, state.noSamples, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1)
						state.y[i] = (((Math.sqrt(state.variance) * DistributionSampling.sampleGaussian(RNG$1)) + state.b0) + (state.b1 * state.x[i]));
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample7)
			state.b0 = (DistributionSampling.sampleGaussian(state.RNG$) * 1.4142135623730951);
		if(!state.fixedFlag$sample11)
			state.b1 = ((DistributionSampling.sampleGaussian(state.RNG$) * 2.23606797749979) + 1.0);
		if(!state.fixedFlag$sample16)
			state.variance = (1 / DistributionSampling.sampleGamma(state.RNG$, 1.0, 1.0));
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample7)
			state.b0 = (DistributionSampling.sampleGaussian(state.RNG$) * 1.4142135623730951);
		if(!state.fixedFlag$sample11)
			state.b1 = ((DistributionSampling.sampleGaussian(state.RNG$) * 2.23606797749979) + 1.0);
		if(!state.fixedFlag$sample16)
			state.variance = (1 / DistributionSampling.sampleGamma(state.RNG$, 1.0, 1.0));
		parallelFor(state.RNG$, 0, state.noSamples, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1)
						state.y[i] = (((Math.sqrt(state.variance) * DistributionSampling.sampleGaussian(RNG$1)) + state.b0) + (state.b1 * state.x[i]));
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample7)
			state.b0 = (DistributionSampling.sampleGaussian(state.RNG$) * 1.4142135623730951);
		if(!state.fixedFlag$sample11)
			state.b1 = ((DistributionSampling.sampleGaussian(state.RNG$) * 2.23606797749979) + 1.0);
		if(!state.fixedFlag$sample16)
			state.variance = (1 / DistributionSampling.sampleGamma(state.RNG$, 1.0, 1.0));
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample7)
			state.b0 = (DistributionSampling.sampleGaussian(state.RNG$) * 1.4142135623730951);
		if(!state.fixedFlag$sample11)
			state.b1 = ((DistributionSampling.sampleGaussian(state.RNG$) * 2.23606797749979) + 1.0);
		if(!state.fixedFlag$sample16)
			state.variance = (1 / DistributionSampling.sampleGamma(state.RNG$, 1.0, 1.0));
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample7)
				inferSample7();
			if(!state.fixedFlag$sample11)
				inferSample11();
			if(!state.fixedFlag$sample16)
				inferSample16();
		} else {
			if(!state.fixedFlag$sample16)
				inferSample16();
			if(!state.fixedFlag$sample11)
				inferSample11();
			if(!state.fixedFlag$sample7)
				inferSample7();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample7)
			drawValueSample7();
		if(!state.constrainedFlag$sample11)
			drawValueSample11();
		if(!state.constrainedFlag$sample16)
			drawValueSample16();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample7)
			state.logProbability$b0 = Double.NaN;
		if(!state.fixedProbFlag$sample11)
			state.logProbability$b1 = Double.NaN;
		state.logProbability$variance = 0.0;
		if(!state.fixedProbFlag$sample16)
			state.logProbability$var16 = Double.NaN;
		state.logProbability$y = 0.0;
		if(!state.fixedProbFlag$sample33) {
			for(int i = 0; i < state.noSamples; i += 1)
				state.logProbability$sample33[i] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.noSamples = state.x.length;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample7)
			logProbabilityValue$sample7();
		if(state.fixedFlag$sample11)
			logProbabilityValue$sample11();
		if(state.fixedFlag$sample16)
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
		int cv$length1 = state.y.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.y[cv$index1] = state.yMeasured[cv$index1];
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