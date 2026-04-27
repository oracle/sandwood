package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.AnonymousSample$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.AnonymousSample.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class AnonymousSample$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public AnonymousSample$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample15() {
		state.mean1 = ((DistributionSampling.sampleGaussian(state.RNG$) * 100.0) + 2000.0);
	}

	private final void drawValueSample21() {
		state.mean2 = ((DistributionSampling.sampleGaussian(state.RNG$) * 100.0) + 2000.0);
	}

	private final void drawValueSample9() {
		state.priorSigma2 = ((DistributionSampling.sampleGaussian(state.RNG$) * 30.0) + 10000.0);
	}

	private final void inferSample15() {
		state.constrainedFlag$sample15 = false;
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i = 0; i < state.n; i += 1) {
			state.constrainedFlag$sample15 = true;
			cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
			cv$sum = (cv$sum + state.amounts1[i]);
			if(cv$sigmaNotFound) {
				cv$sigmaValue = state.priorSigma2;
				cv$sigmaNotFound = false;
			}
		}
		if(state.constrainedFlag$sample15)
			state.mean1 = Conjugates.sampleConjugateGaussianGaussian(state.RNG$, 2000.0, 10000.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void inferSample21() {
		state.constrainedFlag$sample21 = false;
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i = 0; i < state.n; i += 1) {
			state.constrainedFlag$sample21 = true;
			cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
			cv$sum = (cv$sum + state.var39[i]);
			if(cv$sigmaNotFound) {
				cv$sigmaValue = state.priorSigma2;
				cv$sigmaNotFound = false;
			}
		}
		if(state.constrainedFlag$sample21)
			state.mean2 = Conjugates.sampleConjugateGaussianGaussian(state.RNG$, 2000.0, 10000.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void inferSample9() {
		state.constrainedFlag$sample9 = false;
		double cv$originalValue = state.priorSigma2;
		double cv$originalProbability;
		double cv$var = ((state.priorSigma2 * state.priorSigma2) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + state.priorSigma2);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((state.priorSigma2 - 10000.0) / 30.0)) - 3.4011973816621555);
			for(int i = 0; i < state.n; i += 1) {
				state.constrainedFlag$sample9 = true;
				cv$accumulatedProbabilities = (((0.0 < state.priorSigma2)?(DistributionSampling.logProbabilityGaussian(((state.amounts1[i] - state.mean1) / Math.sqrt(state.priorSigma2))) - (Math.log(state.priorSigma2) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			for(int i = 0; i < state.n; i += 1) {
				state.constrainedFlag$sample9 = true;
				cv$accumulatedProbabilities = (((0.0 < state.priorSigma2)?(DistributionSampling.logProbabilityGaussian(((state.var39[i] - state.mean2) / Math.sqrt(state.priorSigma2))) - (Math.log(state.priorSigma2) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample9) {
			state.priorSigma2 = cv$proposedValue;
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - 10000.0) / 30.0)) - 3.4011973816621555);
			for(int i = 0; i < state.n; i += 1) {
				state.constrainedFlag$sample9 = true;
				cv$accumulatedProbabilities = (((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.amounts1[i] - state.mean1) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			for(int i = 0; i < state.n; i += 1) {
				state.constrainedFlag$sample9 = true;
				cv$accumulatedProbabilities = (((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.var39[i] - state.mean2) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio)))
				state.priorSigma2 = cv$originalValue;
		}
	}

	private final void logProbabilityValue$sample15() {
		if(!state.fixedProbFlag$sample15) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((state.mean1 - 2000.0) / 100.0)) - 4.605170185988092);
			state.logProbability$mean1 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample15)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample15 = state.fixedFlag$sample15;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$mean1);
			if(state.fixedFlag$sample15)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$mean1);
		}
	}

	private final void logProbabilityValue$sample21() {
		if(!state.fixedProbFlag$sample21) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((state.mean2 - 2000.0) / 100.0)) - 4.605170185988092);
			state.logProbability$mean2 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample21)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample21 = state.fixedFlag$sample21;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$mean2);
			if(state.fixedFlag$sample21)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$mean2);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!state.fixedProbFlag$sample35) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.n; i += 1) {
				double cv$distributionAccumulator = ((0.0 < state.priorSigma2)?(DistributionSampling.logProbabilityGaussian(((state.amounts1[i] - state.mean1) / Math.sqrt(state.priorSigma2))) - (Math.log(state.priorSigma2) * 0.5)):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample35[i] = cv$distributionAccumulator;
			}
			state.logProbability$amounts1 = (state.logProbability$amounts1 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample35 = (state.fixedFlag$sample9 && state.fixedFlag$sample15);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.n; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample35[i]);
			state.logProbability$amounts1 = (state.logProbability$amounts1 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample39() {
		if(!state.fixedProbFlag$sample39) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.n; i += 1) {
				double cv$distributionAccumulator = ((0.0 < state.priorSigma2)?(DistributionSampling.logProbabilityGaussian(((state.var39[i] - state.mean2) / Math.sqrt(state.priorSigma2))) - (Math.log(state.priorSigma2) * 0.5)):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample39[i] = cv$distributionAccumulator;
			}
			state.logProbability$var39 = (state.logProbability$var39 + cv$accumulator);
			state.logProbability$amounts2 = (state.logProbability$amounts2 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample39 = (state.fixedFlag$sample9 && state.fixedFlag$sample21);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.n; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample39[i]);
			state.logProbability$var39 = (state.logProbability$var39 + cv$accumulator);
			state.logProbability$amounts2 = (state.logProbability$amounts2 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!state.fixedProbFlag$sample9) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((state.priorSigma2 - 10000.0) / 30.0)) - 3.4011973816621555);
			state.logProbability$priorSigma2 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample9)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample9 = state.fixedFlag$sample9;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$priorSigma2);
			if(state.fixedFlag$sample9)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$priorSigma2);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample9)
			state.priorSigma2 = ((DistributionSampling.sampleGaussian(state.RNG$) * 30.0) + 10000.0);
		if(!state.fixedFlag$sample15)
			state.mean1 = ((DistributionSampling.sampleGaussian(state.RNG$) * 100.0) + 2000.0);
		if(!state.fixedFlag$sample21)
			state.mean2 = ((DistributionSampling.sampleGaussian(state.RNG$) * 100.0) + 2000.0);
		for(int i = 0; i < state.n; i += 1) {
			state.amounts1[i] = ((Math.sqrt(state.priorSigma2) * DistributionSampling.sampleGaussian(state.RNG$)) + state.mean1);
			state.var39[i] = ((Math.sqrt(state.priorSigma2) * DistributionSampling.sampleGaussian(state.RNG$)) + state.mean2);
			state.amounts2[i] = (state.amounts1[i] + state.var39[i]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample9)
			state.priorSigma2 = ((DistributionSampling.sampleGaussian(state.RNG$) * 30.0) + 10000.0);
		if(!state.fixedFlag$sample15)
			state.mean1 = ((DistributionSampling.sampleGaussian(state.RNG$) * 100.0) + 2000.0);
		if(!state.fixedFlag$sample21)
			state.mean2 = ((DistributionSampling.sampleGaussian(state.RNG$) * 100.0) + 2000.0);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample9)
			state.priorSigma2 = ((DistributionSampling.sampleGaussian(state.RNG$) * 30.0) + 10000.0);
		if(!state.fixedFlag$sample15)
			state.mean1 = ((DistributionSampling.sampleGaussian(state.RNG$) * 100.0) + 2000.0);
		if(!state.fixedFlag$sample21)
			state.mean2 = ((DistributionSampling.sampleGaussian(state.RNG$) * 100.0) + 2000.0);
		for(int i = 0; i < state.n; i += 1) {
			state.amounts1[i] = ((Math.sqrt(state.priorSigma2) * DistributionSampling.sampleGaussian(state.RNG$)) + state.mean1);
			state.var39[i] = ((Math.sqrt(state.priorSigma2) * DistributionSampling.sampleGaussian(state.RNG$)) + state.mean2);
			state.amounts2[i] = (state.amounts1[i] + state.var39[i]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample9)
			state.priorSigma2 = ((DistributionSampling.sampleGaussian(state.RNG$) * 30.0) + 10000.0);
		if(!state.fixedFlag$sample15)
			state.mean1 = ((DistributionSampling.sampleGaussian(state.RNG$) * 100.0) + 2000.0);
		if(!state.fixedFlag$sample21)
			state.mean2 = ((DistributionSampling.sampleGaussian(state.RNG$) * 100.0) + 2000.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample9)
			state.priorSigma2 = ((DistributionSampling.sampleGaussian(state.RNG$) * 30.0) + 10000.0);
		if(!state.fixedFlag$sample15)
			state.mean1 = ((DistributionSampling.sampleGaussian(state.RNG$) * 100.0) + 2000.0);
		if(!state.fixedFlag$sample21)
			state.mean2 = ((DistributionSampling.sampleGaussian(state.RNG$) * 100.0) + 2000.0);
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample9)
				inferSample9();
			if(!state.fixedFlag$sample15)
				inferSample15();
			if(!state.fixedFlag$sample21)
				inferSample21();
		} else {
			if(!state.fixedFlag$sample21)
				inferSample21();
			if(!state.fixedFlag$sample15)
				inferSample15();
			if(!state.fixedFlag$sample9)
				inferSample9();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample9)
			drawValueSample9();
		if(!state.constrainedFlag$sample15)
			drawValueSample15();
		if(!state.constrainedFlag$sample21)
			drawValueSample21();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample9)
			state.logProbability$priorSigma2 = Double.NaN;
		if(!state.fixedProbFlag$sample15)
			state.logProbability$mean1 = Double.NaN;
		if(!state.fixedProbFlag$sample21)
			state.logProbability$mean2 = Double.NaN;
		state.logProbability$amounts1 = 0.0;
		if(!state.fixedProbFlag$sample35) {
			for(int i = 0; i < state.n; i += 1)
				state.logProbability$sample35[i] = Double.NaN;
		}
		state.logProbability$var39 = 0.0;
		state.logProbability$amounts2 = 0.0;
		if(!state.fixedProbFlag$sample39) {
			for(int i = 0; i < state.n; i += 1)
				state.logProbability$sample39[i] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.n = state.length$obsAmounts1;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample9)
			logProbabilityValue$sample9();
		if(state.fixedFlag$sample15)
			logProbabilityValue$sample15();
		if(state.fixedFlag$sample21)
			logProbabilityValue$sample21();
		logProbabilityValue$sample35();
		logProbabilityValue$sample39();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample15();
		logProbabilityValue$sample21();
		logProbabilityValue$sample35();
		logProbabilityValue$sample39();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample15();
		logProbabilityValue$sample21();
		logProbabilityValue$sample35();
		logProbabilityValue$sample39();
	}

	@Override
	public final void propagateObservedValues() {
		{
			int cv$length1 = state.amounts1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				state.amounts1[cv$index1] = state.obsAmounts1[cv$index1];
		}
		int cv$length1 = state.amounts2.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.amounts2[cv$index1] = state.obsAmounts2[cv$index1];
		for(int i = (state.n - 1); i >= 0; i -= 1)
			state.var39[i] = (state.amounts2[i] - state.amounts1[i]);
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model AnonymousSample(double[] obsAmounts1, double[] obsAmounts2) {\n"
		     + "    int n = obsAmounts1.length;\n"
		     + "\n"
		     + "    double priorSigma2 = gaussian(10000, 900).sample();   // can always use inverseGamma(1.5, 100)\n"
		     + "\n"
		     + "    double mean1 = gaussian(2000, 10000).sample();\n"
		     + "    double mean2 = gaussian(2000, 10000).sample();\n"
		     + "\n"
		     + "\n"
		     + "    double[] amounts1 = new double[n];\n"
		     + "    double[] amounts2 = new double[n];\n"
		     + "    for(int i : [0..n)) {\n"
		     + "        amounts1[i] = gaussian(mean1, priorSigma2).sample();\n"
		     + "        amounts2[i] = amounts1[i] + gaussian(mean2, priorSigma2).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    amounts1.observe(obsAmounts1);\n"
		     + "    amounts2.observe(obsAmounts2);\n"
		     + "}";
	}
}