package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Conditional1$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Conditional1.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Conditional1$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var4$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			cv$var4$stateProbabilityGlobal = new double[2];
		}
	}


	public Conditional1$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample4() {
		state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
	}

	private final void inferSample4() {
		state.constrainedFlag$sample4 = false;
		state.guard = false;
		scratch.cv$var4$stateProbabilityGlobal[0] = -0.6931471805599453;
		state.guard = true;
		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
		if((state.value == 1.0))
			cv$accumulatedConsumerProbabilities = 0.0;
		scratch.cv$var4$stateProbabilityGlobal[1] = (cv$accumulatedConsumerProbabilities - 0.6931471805599453);
	}

	private final void logProbabilityValue$sample16() {
		double cv$accumulator = 0.0;
		if(!state.guard) {
			double cv$distributionAccumulator = (((0.0 <= state.var14) && (state.var14 < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			cv$accumulator = cv$distributionAccumulator;
			state.logProbability$sample16 = cv$distributionAccumulator;
			state.logProbability$value = (state.logProbability$value + cv$distributionAccumulator);
		}
		state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
		state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
	}

	private final void logProbabilityValue$sample4() {
		if(!state.fixedProbFlag$sample4) {
			double cv$weightedProbability = -0.6931471805599453;
			if((state.guard && !(state.value == 1.0)))
				cv$weightedProbability = Double.NEGATIVE_INFINITY;
			state.logProbability$bernoulli = cv$weightedProbability;
			state.logProbability$guard = cv$weightedProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$weightedProbability);
			if(state.fixedFlag$sample4)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$weightedProbability);
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
				state.value = 1.0;
		} else {
			state.var14 = DistributionSampling.sampleUniform(state.RNG$);
			state.value = state.var14;
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(state.guard)
			state.value = 1.0;
		else {
			state.var14 = DistributionSampling.sampleUniform(state.RNG$);
			state.value = state.var14;
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample4)
			inferSample4();
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample4)
			drawValueSample4();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$bernoulli = 0.0;
		if(!state.fixedProbFlag$sample4)
			state.logProbability$guard = Double.NaN;
		state.logProbability$value = 0.0;
		state.logProbability$sample16 = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample4)
			logProbabilityValue$sample4();
		logProbabilityValue$sample16();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample4();
		logProbabilityValue$sample16();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample4();
		logProbabilityValue$sample16();
	}

	@Override
	public final void propogateObservedValues() {
		state.value = state.observedValue;
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
		     + "public model Conditional1(double observedValue)  {\n"
		     + "        \n"
		     + "    //Construct a bernoulli\n"
		     + "    Bernoulli bernoulli = bernoulli(0.5);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    boolean guard = bernoulli.sample();\n"
		     + "        \n"
		     + "    double value = guard?1.0:uniform(0.0, 1.0).sample();\n"
		     + "    \n"
		     + "    //Link the sampled value to the observed value\n"
		     + "    value.observe(observedValue);\n"
		     + "}";
	}
}