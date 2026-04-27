package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Conditional3$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Conditional3.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Conditional3$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var4$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			cv$var4$stateProbabilityGlobal = new double[2];
		}
	}


	public Conditional3$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample16() {
		state.var14 = (DistributionSampling.sampleUniform(state.RNG$) * 0.5);
		state.bias = state.var14;
	}

	private final void drawValueSample4() {
		state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(state.guard)
			state.bias = 0.5;
		else
			state.bias = state.var14;
	}

	private final void inferSample16() {
		state.constrainedFlag$sample16 = false;
		double cv$originalValue = state.var14;
		double cv$var = ((state.var14 * state.var14) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + state.var14);
		state.constrainedFlag$sample16 = true;
		double cv$originalProbability = (DistributionSampling.logProbabilityBeta(state.value, state.var14, 1.0) + (((0.0 <= state.var14) && (state.var14 < 0.5))?0.6931471805599453:Double.NEGATIVE_INFINITY));
		state.var14 = cv$proposedValue;
		state.bias = cv$proposedValue;
		state.constrainedFlag$sample16 = true;
		double cv$ratio = ((DistributionSampling.logProbabilityBeta(state.value, cv$proposedValue, 1.0) + (((0.0 <= cv$proposedValue) && (cv$proposedValue < 0.5))?0.6931471805599453:Double.NEGATIVE_INFINITY)) - cv$originalProbability);
		if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
			state.var14 = cv$originalValue;
			state.bias = cv$originalValue;
		}
	}

	private final void inferSample4() {
		state.constrainedFlag$sample4 = false;
		state.guard = false;
		state.bias = state.var14;
		state.constrainedFlag$sample4 = true;
		scratch.cv$var4$stateProbabilityGlobal[0] = (((((0.0 <= state.var14) && (state.var14 < 0.5))?0.6931471805599453:Double.NEGATIVE_INFINITY) + DistributionSampling.logProbabilityBeta(state.value, state.var14, 1.0)) - 0.6931471805599453);
		state.guard = true;
		state.bias = 0.5;
		state.constrainedFlag$sample4 = true;
		scratch.cv$var4$stateProbabilityGlobal[1] = (DistributionSampling.logProbabilityBeta(state.value, 0.5, 1.0) - 0.6931471805599453);
		double cv$logSum;
		double cv$lseMax = scratch.cv$var4$stateProbabilityGlobal[0];
		double cv$lseElementValue = scratch.cv$var4$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((scratch.cv$var4$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var4$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			scratch.cv$var4$stateProbabilityGlobal[0] = 0.5;
			scratch.cv$var4$stateProbabilityGlobal[1] = 0.5;
		} else {
			scratch.cv$var4$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var4$stateProbabilityGlobal[0] - cv$logSum));
			scratch.cv$var4$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var4$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < scratch.cv$var4$stateProbabilityGlobal.length; cv$indexName += 1)
			scratch.cv$var4$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		state.guard = (DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var4$stateProbabilityGlobal, 2) == 1);
		if(state.guard)
			state.bias = 0.5;
		else
			state.bias = state.var14;
	}

	private final void logProbabilityValue$sample16() {
		if(!state.fixedProbFlag$sample16) {
			double cv$accumulator = 0.0;
			if(!state.guard) {
				double cv$distributionAccumulator = (((0.0 <= state.var14) && (state.var14 < 0.5))?0.6931471805599453:Double.NEGATIVE_INFINITY);
				cv$accumulator = cv$distributionAccumulator;
				state.logProbability$sample16 = cv$distributionAccumulator;
			}
			state.logProbability$var14 = (state.logProbability$var14 + cv$accumulator);
			if(!state.guard)
				state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample16)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample16 = (state.fixedFlag$sample16 && state.fixedFlag$sample4);
		} else {
			double cv$accumulator = 0.0;
			if(!state.guard)
				cv$accumulator = state.logProbability$sample16;
			state.logProbability$var14 = (state.logProbability$var14 + cv$accumulator);
			if(!state.guard)
				state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample16)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample20() {
		if(!state.fixedProbFlag$sample20) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.value, state.bias, 1.0);
			state.logProbability$value = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample20 = (state.fixedFlag$sample4 && state.fixedFlag$sample16);
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$value);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$value);
		}
	}

	private final void logProbabilityValue$sample4() {
		if(!state.fixedProbFlag$sample4) {
			state.logProbability$bernoulli = -0.6931471805599453;
			state.logProbability$guard = -0.6931471805599453;
			state.logProbability$$model = (state.logProbability$$model - 0.6931471805599453);
			if(state.fixedFlag$sample4)
				state.logProbability$$evidence = (state.logProbability$$evidence - 0.6931471805599453);
			state.fixedProbFlag$sample4 = state.fixedFlag$sample4;
		} else {
			state.logProbability$bernoulli = state.logProbability$guard;
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$guard);
			if(state.fixedFlag$sample4)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$guard);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(state.guard) {
			if(!state.fixedFlag$sample4)
				state.bias = 0.5;
		} else {
			if(!state.fixedFlag$sample16)
				state.var14 = (DistributionSampling.sampleUniform(state.RNG$) * 0.5);
			if((!state.fixedFlag$sample4 || !state.fixedFlag$sample16))
				state.bias = state.var14;
		}
		state.value = DistributionSampling.sampleBeta(state.RNG$, state.bias, 1.0);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(state.guard)
			state.bias = 0.5;
		else {
			if(!state.fixedFlag$sample16)
				state.var14 = (DistributionSampling.sampleUniform(state.RNG$) * 0.5);
			if((!state.fixedFlag$sample4 || !state.fixedFlag$sample16))
				state.bias = state.var14;
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(state.guard)
			state.bias = 0.5;
		else {
			if(!state.fixedFlag$sample16)
				state.var14 = (DistributionSampling.sampleUniform(state.RNG$) * 0.5);
			if((!state.fixedFlag$sample4 || !state.fixedFlag$sample16))
				state.bias = state.var14;
		}
		state.value = DistributionSampling.sampleBeta(state.RNG$, state.bias, 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(state.guard) {
			if(!state.fixedFlag$sample4)
				state.bias = 0.5;
		} else {
			if(!state.fixedFlag$sample16)
				state.var14 = (DistributionSampling.sampleUniform(state.RNG$) * 0.5);
			if((!state.fixedFlag$sample4 || !state.fixedFlag$sample16))
				state.bias = state.var14;
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(state.guard)
			state.bias = 0.5;
		else {
			if(!state.fixedFlag$sample16)
				state.var14 = (DistributionSampling.sampleUniform(state.RNG$) * 0.5);
			if((!state.fixedFlag$sample4 || !state.fixedFlag$sample16))
				state.bias = state.var14;
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample4)
				inferSample4();
			if((!state.guard && !state.fixedFlag$sample16))
				inferSample16();
		} else {
			if((!state.guard && !state.fixedFlag$sample16))
				inferSample16();
			if(!state.fixedFlag$sample4)
				inferSample4();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample4)
			drawValueSample4();
		if((!state.guard && !state.constrainedFlag$sample16))
			drawValueSample16();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$bernoulli = 0.0;
		if(!state.fixedProbFlag$sample4)
			state.logProbability$guard = Double.NaN;
		state.logProbability$var14 = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample16)
			state.logProbability$sample16 = Double.NaN;
		if(!state.fixedProbFlag$sample20)
			state.logProbability$value = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample4)
			logProbabilityValue$sample4();
		if(state.fixedFlag$sample16)
			logProbabilityValue$sample16();
		logProbabilityValue$sample20();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample4();
		logProbabilityValue$sample16();
		logProbabilityValue$sample20();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample4();
		logProbabilityValue$sample16();
		logProbabilityValue$sample20();
	}

	@Override
	public final void propagateObservedValues() {
		state.value = state.observedValue;
	}

	@Override
	public final void setIntermediates() {
		if(state.guard)
			state.bias = 0.5;
		else {
			if((state.fixedFlag$sample4 && state.fixedFlag$sample16))
				state.bias = state.var14;
		}
	}

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
		     + "public model Conditional3(double observedValue)  {\n"
		     + "        \n"
		     + "    //Construct a bernoulli\n"
		     + "    Bernoulli bernoulli = bernoulli(0.5);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    boolean guard = bernoulli.sample();\n"
		     + "        \n"
		     + "    double bias = guard?0.5:uniform(0.0, 0.5).sample();\n"
		     + "    \n"
		     + "    double value = beta(bias, 1.0).sample();\n"
		     + "    \n"
		     + "    //Link the sampled value to the observed value\n"
		     + "    value.observe(observedValue);\n"
		     + "}";
	}
}