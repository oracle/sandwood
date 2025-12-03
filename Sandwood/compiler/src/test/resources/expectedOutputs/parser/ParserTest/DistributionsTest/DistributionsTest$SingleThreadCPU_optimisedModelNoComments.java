package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.DistributionsTest$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.DistributionsTest.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DistributionsTest$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public DistributionsTest$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample11() {
		state.b1 = DistributionSampling.sampleHalfCauchy(state.RNG$, 1.0, 5.0);
	}

	private final void drawValueSample7() {
		state.b0 = DistributionSampling.sampleCauchy(state.RNG$, 0.0, 2.0);
	}

	private final void inferSample11() {
		state.constrainedFlag$sample11 = false;
		double cv$originalValue = state.b1;
		double cv$originalProbability;
		double cv$var = (((state.b1 < 0)?(-state.b1):state.b1) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + state.b1);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityHalfCauchy(state.b1, 1.0, 5.0);
			for(int i = 0; i < state.noSamples; i += 1) {
				state.constrainedFlag$sample11 = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityStudentT(state.y[i], (state.b0 + (state.b1 * state.x[i]))) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample11) {
			state.b1 = cv$proposedValue;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityHalfCauchy(cv$proposedValue, 1.0, 5.0);
			for(int i = 0; i < state.noSamples; i += 1) {
				state.constrainedFlag$sample11 = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityStudentT(state.y[i], (state.b0 + (cv$proposedValue * state.x[i]))) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio)))
				state.b1 = cv$originalValue;
		}
	}

	private final void inferSample7() {
		state.constrainedFlag$sample7 = false;
		double cv$originalValue = state.b0;
		double cv$originalProbability;
		double cv$var = (((state.b0 < 0)?(-state.b0):state.b0) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + state.b0);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityCauchy(state.b0, 0.0, 2.0);
			for(int i = 0; i < state.noSamples; i += 1) {
				state.constrainedFlag$sample7 = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityStudentT(state.y[i], (state.b0 + (state.b1 * state.x[i]))) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample7) {
			state.b0 = cv$proposedValue;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityCauchy(cv$proposedValue, 0.0, 2.0);
			for(int i = 0; i < state.noSamples; i += 1) {
				state.constrainedFlag$sample7 = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityStudentT(state.y[i], (cv$proposedValue + (state.b1 * state.x[i]))) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio)))
				state.b0 = cv$originalValue;
		}
	}

	private final void logProbabilityValue$sample11() {
		if(!state.fixedProbFlag$sample11) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityHalfCauchy(state.b1, 1.0, 5.0);
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

	private final void logProbabilityValue$sample27() {
		if(!state.fixedProbFlag$sample27) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.noSamples; i += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityStudentT(state.y[i], (state.b0 + (state.b1 * state.x[i])));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample27[i] = cv$distributionAccumulator;
			}
			state.logProbability$y = (state.logProbability$y + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample27 = (state.fixedFlag$sample7 && state.fixedFlag$sample11);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.noSamples; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample27[i]);
			state.logProbability$y = (state.logProbability$y + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample7() {
		if(!state.fixedProbFlag$sample7) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityCauchy(state.b0, 0.0, 2.0);
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
			state.b0 = DistributionSampling.sampleCauchy(state.RNG$, 0.0, 2.0);
		if(!state.fixedFlag$sample11)
			state.b1 = DistributionSampling.sampleHalfCauchy(state.RNG$, 1.0, 5.0);
		for(int i = 0; i < state.noSamples; i += 1)
			state.y[i] = DistributionSampling.sampleStudentT(state.RNG$, (state.b0 + (state.b1 * state.x[i])));
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample7)
			state.b0 = DistributionSampling.sampleCauchy(state.RNG$, 0.0, 2.0);
		if(!state.fixedFlag$sample11)
			state.b1 = DistributionSampling.sampleHalfCauchy(state.RNG$, 1.0, 5.0);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample7)
			state.b0 = DistributionSampling.sampleCauchy(state.RNG$, 0.0, 2.0);
		if(!state.fixedFlag$sample11)
			state.b1 = DistributionSampling.sampleHalfCauchy(state.RNG$, 1.0, 5.0);
		for(int i = 0; i < state.noSamples; i += 1)
			state.y[i] = DistributionSampling.sampleStudentT(state.RNG$, (state.b0 + (state.b1 * state.x[i])));
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample7)
			state.b0 = DistributionSampling.sampleCauchy(state.RNG$, 0.0, 2.0);
		if(!state.fixedFlag$sample11)
			state.b1 = DistributionSampling.sampleHalfCauchy(state.RNG$, 1.0, 5.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample7)
			state.b0 = DistributionSampling.sampleCauchy(state.RNG$, 0.0, 2.0);
		if(!state.fixedFlag$sample11)
			state.b1 = DistributionSampling.sampleHalfCauchy(state.RNG$, 1.0, 5.0);
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample7)
				inferSample7();
			if(!state.fixedFlag$sample11)
				inferSample11();
		} else {
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
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample7)
			state.logProbability$b0 = Double.NaN;
		if(!state.fixedProbFlag$sample11)
			state.logProbability$b1 = Double.NaN;
		state.logProbability$y = 0.0;
		if(!state.fixedProbFlag$sample27) {
			for(int i = 0; i < state.noSamples; i += 1)
				state.logProbability$sample27[i] = Double.NaN;
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