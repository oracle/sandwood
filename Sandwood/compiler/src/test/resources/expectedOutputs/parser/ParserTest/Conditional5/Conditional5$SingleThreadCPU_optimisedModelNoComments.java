package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Conditional5$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Conditional5.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Conditional5$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Conditional5$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void logProbabilityValue$sample13() {
		if(!state.fixedProbFlag$sample13) {
			double cv$distributionAccumulator = (((0.0 <= state.b) && (state.b < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			state.logProbability$b = cv$distributionAccumulator;
			if(!state.guard)
				state.logProbability$value = (state.logProbability$value + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample13 = true;
		} else {
			if(!state.guard)
				state.logProbability$value = (state.logProbability$value + state.logProbability$b);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$b);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$b);
		}
	}

	private final void logProbabilityValue$sample5() {
		if(!state.fixedProbFlag$sample5) {
			state.logProbability$bernoulli = -0.6931471805599453;
			state.logProbability$guard = -0.6931471805599453;
			state.logProbability$$model = (state.logProbability$$model - 0.6931471805599453);
			state.logProbability$$evidence = (state.logProbability$$evidence - 0.6931471805599453);
			state.fixedProbFlag$sample5 = true;
		} else {
			state.logProbability$bernoulli = state.logProbability$guard;
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$guard);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$guard);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!state.fixedProbFlag$sample9) {
			double cv$distributionAccumulator = (((0.0 <= state.a) && (state.a < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			state.logProbability$a = cv$distributionAccumulator;
			if(state.guard)
				state.logProbability$value = (state.logProbability$value + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample9 = true;
		} else {
			if(state.guard)
				state.logProbability$value = (state.logProbability$value + state.logProbability$a);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$a);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$a);
		}
	}

	@Override
	public final void forwardGeneration() {
		state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		state.a = DistributionSampling.sampleUniform(state.RNG$);
		state.b = DistributionSampling.sampleUniform(state.RNG$);
		if(state.guard)
			state.value = state.a;
		else
			state.value = state.b;
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {}

	@Override
	public final void forwardGenerationPrime() {
		state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		state.a = DistributionSampling.sampleUniform(state.RNG$);
		state.b = DistributionSampling.sampleUniform(state.RNG$);
		if(state.guard)
			state.value = state.a;
		else
			state.value = state.b;
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {}

	@Override
	public final void gibbsRound() {
		state.system$gibbsForward = !state.system$gibbsForward;
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$bernoulli = 0.0;
		if(!state.fixedProbFlag$sample5)
			state.logProbability$guard = Double.NaN;
		state.logProbability$value = 0.0;
		if(!state.fixedProbFlag$sample9)
			state.logProbability$a = Double.NaN;
		if(!state.fixedProbFlag$sample13)
			state.logProbability$b = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample9();
		logProbabilityValue$sample13();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample9();
		logProbabilityValue$sample13();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample9();
		logProbabilityValue$sample13();
	}

	@Override
	public final void propagateObservedValues() {
		state.guard = state.observedGuard;
		state.value = state.observedValue;
		if(state.observedGuard)
			state.a = state.observedValue;
		else
			state.b = state.observedValue;
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
		     + "public model Conditional5(double observedValue, boolean observedGuard)  {\n"
		     + "        \n"
		     + "    //Construct a bernoulli\n"
		     + "    Bernoulli bernoulli = bernoulli(0.5);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    boolean guard = bernoulli.sample();\n"
		     + "    double a = uniform(0.0, 1.0).sample();\n"
		     + "    double b = uniform(0.0, 1.0).sample();\n"
		     + "        \n"
		     + "    double value = guard?a:b;\n"
		     + "    \n"
		     + "    guard.observe(observedGuard);\n"
		     + "    //Link the sampled value to the observed value\n"
		     + "    value.observe(observedValue);\n"
		     + "}";
	}
}