package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.MultinomialBernoulli$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.MultinomialBernoulli.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class MultinomialBernoulli$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var17$countGlobal;

		@Override
		public final void allocateScratch() {
			cv$var17$countGlobal = new double[3];
		}
	}


	public MultinomialBernoulli$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample17() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.beta, 3, state.p);
	}

	private final void drawValueSample20() {
		DistributionSampling.sampleMultinomial(state.RNG$, state.p, 3, 10, state.prior);
	}

	private final void inferSample17() {
		state.constrainedFlag$sample17 = false;
		scratch.cv$var17$countGlobal[0] = 0.0;
		scratch.cv$var17$countGlobal[1] = 0.0;
		scratch.cv$var17$countGlobal[2] = 0.0;
		if((state.fixedFlag$sample20 || state.constrainedFlag$sample20)) {
			state.constrainedFlag$sample17 = true;
			scratch.cv$var17$countGlobal[0] = (scratch.cv$var17$countGlobal[0] + state.prior[0]);
			scratch.cv$var17$countGlobal[1] = (scratch.cv$var17$countGlobal[1] + state.prior[1]);
			scratch.cv$var17$countGlobal[2] = (scratch.cv$var17$countGlobal[2] + state.prior[2]);
		}
		if(state.constrainedFlag$sample17)
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.beta, scratch.cv$var17$countGlobal, state.p, 3);
	}

	private final void inferSample20() {
		state.constrainedFlag$sample20 = false;
		double cv$originalProbability;
		int cv$nonZeroCount = 0;
		if(!(state.prior[0] == 0))
			cv$nonZeroCount = 1;
		if(!(state.prior[1] == 0))
			cv$nonZeroCount = (cv$nonZeroCount + 1);
		if(!(state.prior[2] == 0))
			cv$nonZeroCount = (cv$nonZeroCount + 1);
		int cv$sourceIndex = (int)((double)cv$nonZeroCount * DistributionSampling.sampleUniform(state.RNG$));
		for(int cv$loopIndex = 0; cv$loopIndex <= cv$sourceIndex; cv$loopIndex += 1) {
			if((state.prior[cv$loopIndex] == 0))
				cv$sourceIndex = (cv$sourceIndex + 1);
		}
		int cv$changeValue = (int)(((double)state.prior[cv$sourceIndex] * DistributionSampling.sampleUniform(state.RNG$)) + 1.0);
		int cv$destinationIndex = (int)(DistributionSampling.sampleUniform(state.RNG$) * 2.0);
		if((cv$sourceIndex <= cv$destinationIndex))
			cv$destinationIndex = (cv$destinationIndex + 1);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityMultinomial(state.prior, state.p, 3, 10);
			for(int i$var47 = 0; i$var47 < state.length; i$var47 += 3) {
				state.constrainedFlag$sample20 = true;
				double var24 = (double)(state.prior[0] / 10);
				cv$accumulatedProbabilities = ((((0.0 <= var24) && (var24 <= 1.0))?Math.log((state.output[i$var47]?var24:(1.0 - var24))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			for(int i$var59 = 1; i$var59 < state.length; i$var59 += 3) {
				state.constrainedFlag$sample20 = true;
				double var29 = (double)(state.prior[1] / 10);
				cv$accumulatedProbabilities = ((((0.0 <= var29) && (var29 <= 1.0))?Math.log((state.output[i$var59]?var29:(1.0 - var29))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			for(int i$var71 = 2; i$var71 < state.length; i$var71 += 3) {
				state.constrainedFlag$sample20 = true;
				double var34 = (double)(state.prior[2] / 10);
				cv$accumulatedProbabilities = ((((0.0 <= var34) && (var34 <= 1.0))?Math.log((state.output[i$var71]?var34:(1.0 - var34))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample20) {
			state.prior[cv$sourceIndex] = (state.prior[cv$sourceIndex] - cv$changeValue);
			state.prior[cv$destinationIndex] = (state.prior[cv$destinationIndex] + cv$changeValue);
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityMultinomial(state.prior, state.p, 3, 10);
			for(int i$var47 = 0; i$var47 < state.length; i$var47 += 3) {
				state.constrainedFlag$sample20 = true;
				double var24 = (double)(state.prior[0] / 10);
				cv$accumulatedProbabilities = ((((0.0 <= var24) && (var24 <= 1.0))?Math.log((state.output[i$var47]?var24:(1.0 - var24))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			for(int i$var59 = 1; i$var59 < state.length; i$var59 += 3) {
				state.constrainedFlag$sample20 = true;
				double var29 = (double)(state.prior[1] / 10);
				cv$accumulatedProbabilities = ((((0.0 <= var29) && (var29 <= 1.0))?Math.log((state.output[i$var59]?var29:(1.0 - var29))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			for(int i$var71 = 2; i$var71 < state.length; i$var71 += 3) {
				state.constrainedFlag$sample20 = true;
				double var34 = (double)(state.prior[2] / 10);
				cv$accumulatedProbabilities = ((((0.0 <= var34) && (var34 <= 1.0))?Math.log((state.output[i$var71]?var34:(1.0 - var34))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				state.prior[cv$sourceIndex] = (state.prior[cv$sourceIndex] + cv$changeValue);
				state.prior[cv$destinationIndex] = (state.prior[cv$destinationIndex] - cv$changeValue);
			}
		}
	}

	private final void logProbabilityValue$sample17() {
		if(!state.fixedProbFlag$sample17) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(state.p, state.beta, 3);
			state.logProbability$p = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample17)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample17 = state.fixedFlag$sample17;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$p);
			if(state.fixedFlag$sample17)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$p);
		}
	}

	private final void logProbabilityValue$sample20() {
		if(!state.fixedProbFlag$sample20) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityMultinomial(state.prior, state.p, 3, 10);
			state.logProbability$prior = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample20)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample20 = (state.fixedFlag$sample20 && state.fixedFlag$sample17);
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$prior);
			if(state.fixedFlag$sample20)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$prior);
		}
	}

	private final void logProbabilityValue$sample48() {
		if(!state.fixedProbFlag$sample48) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var47 = 0; i$var47 < state.length; i$var47 += 3) {
				double var24 = (double)(state.prior[0] / 10);
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var24) && (var24 <= 1.0))?Math.log((state.output[i$var47]?var24:(1.0 - var24))):Double.NEGATIVE_INFINITY));
			}
			state.logProbability$b1 = cv$sampleAccumulator;
			state.logProbability$var48 = cv$sampleAccumulator;
			state.logProbability$output = (state.logProbability$output + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample48 = state.fixedFlag$sample20;
		} else {
			state.logProbability$b1 = state.logProbability$var48;
			state.logProbability$output = (state.logProbability$output + state.logProbability$var48);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var48);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var48);
		}
	}

	private final void logProbabilityValue$sample60() {
		if(!state.fixedProbFlag$sample60) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var59 = 1; i$var59 < state.length; i$var59 += 3) {
				double var29 = (double)(state.prior[1] / 10);
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var29) && (var29 <= 1.0))?Math.log((state.output[i$var59]?var29:(1.0 - var29))):Double.NEGATIVE_INFINITY));
			}
			state.logProbability$b2 = cv$sampleAccumulator;
			state.logProbability$var60 = cv$sampleAccumulator;
			state.logProbability$output = (state.logProbability$output + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample60 = state.fixedFlag$sample20;
		} else {
			state.logProbability$b2 = state.logProbability$var60;
			state.logProbability$output = (state.logProbability$output + state.logProbability$var60);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var60);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var60);
		}
	}

	private final void logProbabilityValue$sample72() {
		if(!state.fixedProbFlag$sample72) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var71 = 2; i$var71 < state.length; i$var71 += 3) {
				double var34 = (double)(state.prior[2] / 10);
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var34) && (var34 <= 1.0))?Math.log((state.output[i$var71]?var34:(1.0 - var34))):Double.NEGATIVE_INFINITY));
			}
			state.logProbability$b3 = cv$sampleAccumulator;
			state.logProbability$var72 = cv$sampleAccumulator;
			state.logProbability$output = (state.logProbability$output + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample72 = state.fixedFlag$sample20;
		} else {
			state.logProbability$b3 = state.logProbability$var72;
			state.logProbability$output = (state.logProbability$output + state.logProbability$var72);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var72);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var72);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.beta, 3, state.p);
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(state.RNG$, state.p, 3, 10, state.prior);
		for(int i$var47 = 0; i$var47 < state.length; i$var47 += 3)
			state.output[i$var47] = DistributionSampling.sampleBernoulli(state.RNG$, (state.prior[0] / 10));
		for(int i$var59 = 1; i$var59 < state.length; i$var59 += 3)
			state.output[i$var59] = DistributionSampling.sampleBernoulli(state.RNG$, (state.prior[1] / 10));
		for(int i$var71 = 2; i$var71 < state.length; i$var71 += 3)
			state.output[i$var71] = DistributionSampling.sampleBernoulli(state.RNG$, (state.prior[2] / 10));
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.beta, 3, state.p);
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(state.RNG$, state.p, 3, 10, state.prior);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.beta, 3, state.p);
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(state.RNG$, state.p, 3, 10, state.prior);
		for(int i$var47 = 0; i$var47 < state.length; i$var47 += 3)
			state.output[i$var47] = DistributionSampling.sampleBernoulli(state.RNG$, (state.prior[0] / 10));
		for(int i$var59 = 1; i$var59 < state.length; i$var59 += 3)
			state.output[i$var59] = DistributionSampling.sampleBernoulli(state.RNG$, (state.prior[1] / 10));
		for(int i$var71 = 2; i$var71 < state.length; i$var71 += 3)
			state.output[i$var71] = DistributionSampling.sampleBernoulli(state.RNG$, (state.prior[2] / 10));
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.beta, 3, state.p);
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(state.RNG$, state.p, 3, 10, state.prior);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.beta, 3, state.p);
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleMultinomial(state.RNG$, state.p, 3, 10, state.prior);
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample17)
				inferSample17();
			if(!state.fixedFlag$sample20)
				inferSample20();
		} else {
			if(!state.fixedFlag$sample20)
				inferSample20();
			if(!state.fixedFlag$sample17)
				inferSample17();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample17)
			drawValueSample17();
		if(!state.constrainedFlag$sample20)
			drawValueSample20();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample17)
			state.logProbability$p = Double.NaN;
		if(!state.fixedProbFlag$sample20)
			state.logProbability$prior = Double.NaN;
		state.logProbability$b1 = Double.NaN;
		state.logProbability$output = 0.0;
		if(!state.fixedProbFlag$sample48)
			state.logProbability$var48 = Double.NaN;
		state.logProbability$b2 = Double.NaN;
		if(!state.fixedProbFlag$sample60)
			state.logProbability$var60 = Double.NaN;
		state.logProbability$b3 = Double.NaN;
		if(!state.fixedProbFlag$sample72)
			state.logProbability$var72 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.beta[0] = 0.1;
		state.beta[1] = 0.1;
		state.beta[2] = 0.1;
		state.length = state.length$observed;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample17)
			logProbabilityValue$sample17();
		if(state.fixedFlag$sample20)
			logProbabilityValue$sample20();
		logProbabilityValue$sample48();
		logProbabilityValue$sample60();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample20();
		logProbabilityValue$sample48();
		logProbabilityValue$sample60();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample20();
		logProbabilityValue$sample48();
		logProbabilityValue$sample60();
		logProbabilityValue$sample72();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.output.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.output[cv$index1] = state.observed[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model MultinomialBernoulli(boolean[] observed) {\n"
		     + "    double[] beta = {0.1, 0.1, 0.1};\n"
		     + "    double[] p = dirichlet(beta).sample();\n"
		     + "    int n = 10;\n"
		     + "    int[] prior = multinomial(p, n).sample();\n"
		     + "    Bernoulli b1 = new Bernoulli(prior[0]/n);\n"
		     + "    Bernoulli b2 = new Bernoulli(prior[1]/n);\n"
		     + "    Bernoulli b3 = new Bernoulli(prior[2]/n);\n"
		     + "    int length = observed.length;\n"
		     + "    boolean[] output = new boolean[length];\n"
		     + "    for(int i=0; i<length; i+=3)\n"
		     + "        output[i] = b1.sample();\n"
		     + "    for(int i=1; i<length; i+=3)\n"
		     + "        output[i] = b2.sample();\n"
		     + "    for(int i=2; i<length; i+=3)\n"
		     + "        output[i] = b3.sample();\n"
		     + "    output.observe(observed);\n"
		     + "}\n"
		     + "";
	}
}