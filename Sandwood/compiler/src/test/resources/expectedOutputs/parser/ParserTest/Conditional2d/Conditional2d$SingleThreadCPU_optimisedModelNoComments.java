package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Conditional2d$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Conditional2d.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Conditional2d$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var4$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			cv$var4$stateProbabilityGlobal = new double[2];
		}
	}


	public Conditional2d$SingleThreadCPU(State state, ExecutionTarget target) {
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
		if((state.value2[0] == 1.0))
			cv$accumulatedConsumerProbabilities = 0.0;
		scratch.cv$var4$stateProbabilityGlobal[1] = (cv$accumulatedConsumerProbabilities - 0.6931471805599453);
	}

	private final void logProbabilityValue$sample4() {
		if(!state.fixedProbFlag$sample4) {
			double cv$weightedProbability = -0.6931471805599453;
			if((state.guard && !(state.value2[0] == 1.0)))
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

	private final void logProbabilityValue$sample8() {
		if(!state.fixedProbFlag$sample8) {
			double cv$distributionAccumulator = (((0.0 <= state.u) && (state.u < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			state.logProbability$sample8 = cv$distributionAccumulator;
			state.logProbability$u = (state.logProbability$u + cv$distributionAccumulator);
			if(!state.guard) {
				state.logProbability$value = (state.logProbability$value + cv$distributionAccumulator);
				state.logProbability$value2 = (state.logProbability$value2 + cv$distributionAccumulator);
			}
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample8 = state.fixedFlag$sample8;
		} else {
			state.logProbability$u = (state.logProbability$u + state.logProbability$sample8);
			if(!state.guard) {
				state.logProbability$value = (state.logProbability$value + state.logProbability$sample8);
				state.logProbability$value2 = (state.logProbability$value2 + state.logProbability$sample8);
			}
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$sample8);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$sample8);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample4)
			state.guard = DistributionSampling.sampleBernoulli(state.RNG$, 0.5);
		if(!state.fixedFlag$sample8)
			state.u = DistributionSampling.sampleUniform(state.RNG$);
		if(state.guard) {
			if(!state.fixedFlag$sample4)
				state.value = 1.0;
		} else {
			if((!state.fixedFlag$sample4 || !state.fixedFlag$sample8))
				state.value = state.u;
		}
		if((!state.fixedFlag$sample4 || !state.fixedFlag$sample8))
			state.value2[0] = state.value;
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
		if(!state.fixedFlag$sample8)
			state.u = DistributionSampling.sampleUniform(state.RNG$);
		if(state.guard)
			state.value = 1.0;
		else {
			if((!state.fixedFlag$sample4 || !state.fixedFlag$sample8))
				state.value = state.u;
		}
		state.value2[0] = state.value;
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
		state.logProbability$u = 0.0;
		state.logProbability$value = 0.0;
		state.logProbability$value2 = 0.0;
		if(!state.fixedProbFlag$sample8)
			state.logProbability$sample8 = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample4)
			logProbabilityValue$sample4();
		logProbabilityValue$sample8();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample4();
		logProbabilityValue$sample8();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample4();
		logProbabilityValue$sample8();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = state.value2.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.value2[cv$index1] = state.observedValue[cv$index1];
		if(!state.guard)
			state.value = state.value2[0];
		state.value = state.value2[0];
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
		     + "public model Conditional2d(double[] observedValue)  {\n"
		     + "        \n"
		     + "    //Construct a bernoulli\n"
		     + "    Bernoulli bernoulli = bernoulli(0.5);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    boolean guard = bernoulli.sample();\n"
		     + "    \n"
		     + "    double value;\n"
		     + "    \n"
		     + "    double u;\n"
		     + "    u = uniform(0.0, 1.0).sample();\n"
		     + "    \n"
		     + "    if(guard)\n"
		     + "        value = 1.0;\n"
		     + "    else {\n"
		     + "        value = u;\n"
		     + "    }\n"
		     + "    \n"
		     + "    double[] value2 = new double[1];\n"
		     + "    \n"
		     + "    value2[0] = value;\n"
		     + "    \n"
		     + "    //Link the sampled value to the observed value\n"
		     + "    value2.observe(observedValue);\n"
		     + "}";
	}
}