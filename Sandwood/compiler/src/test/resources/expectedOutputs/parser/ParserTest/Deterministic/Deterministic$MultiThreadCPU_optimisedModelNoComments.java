package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Deterministic$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Deterministic.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Deterministic$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[][] cv$var29$countGlobal;
		double[] cv$var54$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			int cv$threadCount = threadCount();
			cv$var29$countGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var29$countGlobal[cv$index] = new double[5];
			cv$var54$stateProbabilityGlobal = new double[5];
		}
	}


	public Deterministic$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample29(int var28, int threadID$cv$var28, Rng RNG$) {
		DistributionSampling.sampleDirichlet(RNG$, state.v, 5, state.m[var28]);
	}

	private final void drawValueSample55(int i$var46) {
		state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
		int index$i$1_1 = (i$var46 + 1);
		if((index$i$1_1 < state.n))
			state.b[index$i$1_1] = state.a[(index$i$1_1 - 1)];
	}

	private final void inferSample29(int var28, int threadID$cv$var28, Rng RNG$) {
		state.constrainedFlag$sample29[var28] = false;
		double[] cv$countLocal = scratch.cv$var29$countGlobal[threadID$cv$var28];
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			if(((var28 == state.b[i$var46]) && (state.fixedFlag$sample55 || state.constrainedFlag$sample55[(i$var46 - 1)]))) {
				state.constrainedFlag$sample29[var28] = true;
				cv$countLocal[state.a[i$var46]] = (cv$countLocal[state.a[i$var46]] + 1.0);
			}
		}
		if(state.constrainedFlag$sample29[var28])
			Conjugates.sampleConjugateDirichletCategorical(RNG$, state.v, cv$countLocal, state.m[var28], 5);
	}

	private final void inferSample55(int i$var46) {
		state.constrainedFlag$sample55[(i$var46 - 1)] = false;
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			state.a[i$var46] = cv$valuePos;
			int index$i$2_1 = (i$var46 + 1);
			if((index$i$2_1 < state.n))
				state.b[index$i$2_1] = state.a[(index$i$2_1 - 1)];
			double[] var52 = state.m[state.b[i$var46]];
			double cv$accumulatedProbabilities = (((0.0 <= var52[cv$valuePos]) && (var52[cv$valuePos] <= 1.0))?Math.log(var52[cv$valuePos]):Double.NEGATIVE_INFINITY);
			int index$i$3_2 = (i$var46 + 1);
			if(((index$i$3_2 < state.n) && (state.fixedFlag$sample55 || state.constrainedFlag$sample55[(index$i$3_2 - 1)]))) {
				double[] sc$var52$1 = state.m[cv$valuePos];
				cv$accumulatedProbabilities = ((((((0.0 <= state.a[index$i$3_2]) && (state.a[index$i$3_2] < 5)) && (0.0 <= sc$var52$1[state.a[index$i$3_2]])) && (sc$var52$1[state.a[index$i$3_2]] <= 1.0))?Math.log(sc$var52$1[state.a[index$i$3_2]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			state.constrainedFlag$sample55[(i$var46 - 1)] = true;
			double var72 = (double)(1 / cv$valuePos);
			cv$accumulatedProbabilities = ((((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[(i$var46 - 1)]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			scratch.cv$var54$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample55[(i$var46 - 1)]) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var54$stateProbabilityGlobal[0];
			{
				double cv$lseElementValue = scratch.cv$var54$stateProbabilityGlobal[1];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			{
				double cv$lseElementValue = scratch.cv$var54$stateProbabilityGlobal[2];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			{
				double cv$lseElementValue = scratch.cv$var54$stateProbabilityGlobal[3];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			double cv$lseElementValue = scratch.cv$var54$stateProbabilityGlobal[4];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < 5; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var54$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
					scratch.cv$var54$stateProbabilityGlobal[cv$indexName] = 0.2;
			} else {
				for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
					scratch.cv$var54$stateProbabilityGlobal[cv$indexName] = Math.exp((scratch.cv$var54$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = 5; cv$indexName < scratch.cv$var54$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var54$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var54$stateProbabilityGlobal, 5);
			int index$i$10_1 = (i$var46 + 1);
			if((index$i$10_1 < state.n))
				state.b[index$i$10_1] = state.a[(index$i$10_1 - 1)];
		}
	}

	private final void logProbabilityValue$sample29() {
		if(!state.fixedProbFlag$sample29) {
			double cv$sampleAccumulator = 0.0;
			for(int var28 = 0; var28 < 5; var28 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(state.m[var28], state.v, 5));
			state.logProbability$var29 = cv$sampleAccumulator;
			state.logProbability$m = (state.logProbability$m + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample29)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample29 = state.fixedFlag$sample29;
		} else {
			state.logProbability$m = (state.logProbability$m + state.logProbability$var29);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var29);
			if(state.fixedFlag$sample29)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var29);
		}
	}

	private final void logProbabilityValue$sample55() {
		if(!state.fixedProbFlag$sample55) {
			double cv$accumulator = 0.0;
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				int cv$sampleValue = state.a[i$var46];
				double[] var52 = state.m[state.b[i$var46]];
				double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var52[cv$sampleValue])) && (var52[cv$sampleValue] <= 1.0))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample55[(i$var46 - 1)] = cv$distributionAccumulator;
				if((i$var46 < (state.n - 1)))
					state.logProbability$b = (state.logProbability$b + cv$distributionAccumulator);
			}
			state.logProbability$a = (state.logProbability$a + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample55)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample55 = (state.fixedFlag$sample55 && state.fixedFlag$sample29);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				double cv$sampleValue = state.logProbability$sample55[(i$var46 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				if((i$var46 < (state.n - 1)))
					state.logProbability$b = (state.logProbability$b + cv$sampleValue);
			}
			state.logProbability$a = (state.logProbability$a + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample55)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample75() {
		if(!state.fixedProbFlag$sample75) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.n; j += 1) {
				double var72 = (double)(1 / state.a[(j + 1)]);
				double cv$distributionAccumulator = (((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample75[j] = cv$distributionAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample75 = state.fixedFlag$sample55;
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.n; j += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample75[j]);
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample29)
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var28]);
				}
			);

		if(!state.fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				state.b[i$var46] = state.a[(i$var46 - 1)];
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
			}
		}
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						state.flips[j] = DistributionSampling.sampleBernoulli(RNG$1, (1 / state.a[(j + 1)]));
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample29)
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var28]);
				}
			);

		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			state.b[i$var46] = state.a[(i$var46 - 1)];
			if(!state.fixedFlag$sample55)
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample29)
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var28]);
				}
			);

		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			state.b[i$var46] = state.a[(i$var46 - 1)];
			if(!state.fixedFlag$sample55)
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
		}
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						state.flips[j] = DistributionSampling.sampleBernoulli(RNG$1, (1 / state.a[(j + 1)]));
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample29)
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var28]);
				}
			);

		if(!state.fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				state.b[i$var46] = state.a[(i$var46 - 1)];
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample29)
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var28]);
				}
			);

		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			state.b[i$var46] = state.a[(i$var46 - 1)];
			if(!state.fixedFlag$sample55)
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample29)
				parallelFor(state.RNG$, 0, 5, 1,
					(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
								inferSample29(var28, threadID$var28, RNG$1);
					}
				);

			if(!state.fixedFlag$sample55) {
				for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1)
					inferSample55(i$var46);
			}
		} else {
			if(!state.fixedFlag$sample55) {
				for(int i$var46 = (state.n - 1); i$var46 >= 1; i$var46 -= 1)
					inferSample55(i$var46);
			}
			if(!state.fixedFlag$sample29)
				parallelFor(state.RNG$, 0, 5, 1,
					(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
								inferSample29(var28, threadID$var28, RNG$1);
					}
				);

		}
		state.system$gibbsForward = !state.system$gibbsForward;
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1) {
						if(!state.constrainedFlag$sample29[var28])
							drawValueSample29(var28, threadID$var28, RNG$1);
					}
			}
		);
		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			if(!state.constrainedFlag$sample55[(i$var46 - 1)])
				drawValueSample55(i$var46);
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$m = 0.0;
		if(!state.fixedProbFlag$sample29)
			state.logProbability$var29 = Double.NaN;
		state.logProbability$a = 0.0;
		state.logProbability$b = 0.0;
		if(!state.fixedProbFlag$sample55) {
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1)
				state.logProbability$sample55[(i$var46 - 1)] = Double.NaN;
		}
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample75) {
			for(int j = 0; j < state.n; j += 1)
				state.logProbability$sample75[j] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		for(int i$var14 = 0; i$var14 < 5; i$var14 += 1)
			state.v[i$var14] = 0.1;
		state.a[0] = 0;
		for(int index$constrainedFlag$sample29$1 = 0; index$constrainedFlag$sample29$1 < state.constrainedFlag$sample29.length; index$constrainedFlag$sample29$1 += 1)
			state.constrainedFlag$sample29[index$constrainedFlag$sample29$1] = true;
		for(int index$constrainedFlag$sample55$1 = 0; index$constrainedFlag$sample55$1 < state.constrainedFlag$sample55.length; index$constrainedFlag$sample55$1 += 1)
			state.constrainedFlag$sample55[index$constrainedFlag$sample55$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample29)
			logProbabilityValue$sample29();
		if(state.fixedFlag$sample55)
			logProbabilityValue$sample55();
		logProbabilityValue$sample75();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample29();
		logProbabilityValue$sample55();
		logProbabilityValue$sample75();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample29();
		logProbabilityValue$sample55();
		logProbabilityValue$sample75();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.flips[cv$index1] = state.flipsMeasured[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1)
			state.b[i$var46] = state.a[(i$var46 - 1)];
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
		     + "/**\n"
		     + " * A model for the fairness work.\n"
		     + " */\n"
		     + "public model Deterministic(int n, boolean[] flipsMeasured) {\n"
		     + "    int states = 5;\n"
		     + "\n"
		     + "    double[] v = new double[states];\n"
		     + "    for(int i:[0..states))\n"
		     + "        v[i] = 0.1;\n"
		     + "    \n"
		     + "    double[][] m = dirichlet(v).sample(states);\n"
		     + "\n"
		     + "    int[] a = new int[n];\n"
		     + "    int[] b = new int[n];\n"
		     + "    a[0] = 0;\n"
		     + "    for(int i=1; i<n; i++) {\n"
		     + "        b[i] = a[i-1];\n"
		     + "        a[i] = categorical(m[b[i]]).sample();\n"
		     + "    }\n"
		     + "    \n"
		     + "    boolean[] flips = new boolean[n];\n"
		     + "            \n"
		     + "    for(int j:[0..n))\n"
		     + "        flips[j] = bernoulli(1/a[j+1]).sample();\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}";
	}
}