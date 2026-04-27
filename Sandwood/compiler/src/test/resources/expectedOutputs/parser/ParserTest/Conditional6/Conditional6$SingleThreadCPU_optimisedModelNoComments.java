package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Conditional6$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Conditional6.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Conditional6$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var5$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			cv$var5$stateProbabilityGlobal = new double[2];
		}
	}


	public Conditional6$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample5() {
		state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
	}

	private final void inferSample5() {
		state.constrainedFlag$sample5 = false;
		state.guard = false;
		scratch.cv$var5$stateProbabilityGlobal[0] = -0.6931471805599453;
		state.guard = true;
		scratch.cv$var5$stateProbabilityGlobal[1] = -0.6931471805599453;
	}

	private final void logProbabilityValue$sample10() {
		if(!state.fixedProbFlag$sample10) {
			double cv$distributionAccumulator = (((0.0 <= state.b) && (state.b < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			state.logProbability$u = (state.logProbability$u + cv$distributionAccumulator);
			state.logProbability$sample10 = cv$distributionAccumulator;
			state.logProbability$b = (state.logProbability$b + cv$distributionAccumulator);
			if(!state.guard)
				state.logProbability$value = (state.logProbability$value + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample10 = state.fixedFlag$sample10;
		} else {
			state.logProbability$u = (state.logProbability$u + state.logProbability$sample10);
			state.logProbability$b = (state.logProbability$b + state.logProbability$sample10);
			if(!state.guard)
				state.logProbability$value = (state.logProbability$value + state.logProbability$sample10);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$sample10);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$sample10);
		}
	}

	private final void logProbabilityValue$sample5() {
		if(!state.fixedProbFlag$sample5) {
			state.logProbability$bernoulli = -0.6931471805599453;
			state.logProbability$guard = -0.6931471805599453;
			state.logProbability$$model = (state.logProbability$$model - 0.6931471805599453);
			if(state.fixedFlag$sample5)
				state.logProbability$$evidence = (state.logProbability$$evidence - 0.6931471805599453);
			state.fixedProbFlag$sample5 = state.fixedFlag$sample5;
		} else {
			state.logProbability$bernoulli = state.logProbability$guard;
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$guard);
			if(state.fixedFlag$sample5)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$guard);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!state.fixedProbFlag$sample9) {
			double cv$distributionAccumulator = (((0.0 <= state.a) && (state.a < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			state.logProbability$u = (state.logProbability$u + cv$distributionAccumulator);
			state.logProbability$sample9 = cv$distributionAccumulator;
			state.logProbability$a = (state.logProbability$a + cv$distributionAccumulator);
			if(state.guard)
				state.logProbability$value = (state.logProbability$value + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample9 = state.fixedFlag$sample9;
		} else {
			state.logProbability$u = (state.logProbability$u + state.logProbability$sample9);
			state.logProbability$a = (state.logProbability$a + state.logProbability$sample9);
			if(state.guard)
				state.logProbability$value = (state.logProbability$value + state.logProbability$sample9);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$sample9);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$sample9);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample5)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(!state.fixedFlag$sample9)
			state.a = DistributionSampling.sampleUniform(state.RNG$);
		if(!state.fixedFlag$sample10)
			state.b = DistributionSampling.sampleUniform(state.RNG$);
		if(state.guard) {
			if((!state.fixedFlag$sample5 || !state.fixedFlag$sample9))
				state.value = state.a;
		} else {
			if((!state.fixedFlag$sample5 || !state.fixedFlag$sample10))
				state.value = state.b;
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample5)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample5)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(!state.fixedFlag$sample9)
			state.a = DistributionSampling.sampleUniform(state.RNG$);
		if(!state.fixedFlag$sample10)
			state.b = DistributionSampling.sampleUniform(state.RNG$);
		if(state.guard) {
			if((!state.fixedFlag$sample5 || !state.fixedFlag$sample9))
				state.value = state.a;
		} else {
			if((!state.fixedFlag$sample5 || !state.fixedFlag$sample10))
				state.value = state.b;
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample5)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample5)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample5)
			inferSample5();
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample5)
			drawValueSample5();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$bernoulli = 0.0;
		if(!state.fixedProbFlag$sample5)
			state.logProbability$guard = Double.NaN;
		state.logProbability$u = 0.0;
		state.logProbability$a = 0.0;
		state.logProbability$value = 0.0;
		if(!state.fixedProbFlag$sample9)
			state.logProbability$sample9 = Double.NaN;
		state.logProbability$b = 0.0;
		if(!state.fixedProbFlag$sample10)
			state.logProbability$sample10 = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample5)
			logProbabilityValue$sample5();
		logProbabilityValue$sample9();
		logProbabilityValue$sample10();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample9();
		logProbabilityValue$sample10();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample9();
		logProbabilityValue$sample10();
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
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model Conditional6(double observedValue, boolean observedGuard)  {\n"
		     + "        \n"
		     + "    //Construct a bernoulli\n"
		     + "    Bernoulli bernoulli = bernoulli(0.5);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    boolean guard = bernoulli.sample();\n"
		     + "    Uniform u = uniform(0.0, 1.0);\n"
		     + "    double a = u.sample();\n"
		     + "    double b = u.sample();\n"
		     + "        \n"
		     + "    double value = guard?a:b;\n"
		     + "    \n"
		     + "    //Link the sampled value to the observed value\n"
		     + "    value.observe(observedValue);\n"
		     + "}";
	}
}