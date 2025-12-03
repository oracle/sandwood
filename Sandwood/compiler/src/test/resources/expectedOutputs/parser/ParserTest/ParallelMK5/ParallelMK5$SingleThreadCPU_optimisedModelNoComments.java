package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.ParallelMK5$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.ParallelMK5.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class ParallelMK5$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public ParallelMK5$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample61(int i, int j) {
		state.indirection1[i][j] = DistributionSampling.sampleUniform(state.RNG$);
		state.indirection2[j][i] = state.indirection1[i][j];
	}

	private final void inferSample61(int i, int j) {
		double cv$originalValue = state.indirection1[i][j];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
		{
			state.constrainedFlag$sample61[i][j] = true;
			double[] var99 = state.indirection2[j];
			cv$originalProbability = ((((((0.0 <= state.generated[j]) && (state.generated[j] < 10)) && (0.0 <= var99[state.generated[j]])) && (var99[state.generated[j]] <= 1.0))?Math.log(var99[state.generated[j]]):Double.NEGATIVE_INFINITY) + (((0.0 <= cv$originalValue) && (cv$originalValue < 1.0))?0.0:Double.NEGATIVE_INFINITY));
		}
		if(state.constrainedFlag$sample61[i][j]) {
			state.indirection1[i][j] = cv$proposedValue;
			state.indirection2[j][i] = state.indirection1[i][j];
			state.constrainedFlag$sample61[i][j] = true;
			double[] var99 = state.indirection2[j];
			double cv$ratio = (((((((0.0 <= state.generated[j]) && (state.generated[j] < 10)) && (0.0 <= var99[state.generated[j]])) && (var99[state.generated[j]] <= 1.0))?Math.log(var99[state.generated[j]]):Double.NEGATIVE_INFINITY) + (((0.0 <= cv$proposedValue) && (cv$proposedValue < 1.0))?0.0:Double.NEGATIVE_INFINITY)) - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				state.indirection1[i][j] = cv$originalValue;
				state.indirection2[j][i] = state.indirection1[i][j];
			}
		}
	}

	private final void logProbabilityValue$sample103() {
		if(!state.fixedProbFlag$sample103) {
			double cv$accumulator = 0.0;
			for(int m = 0; m < state.length$observed; m += 1) {
				int cv$sampleValue = state.generated[m];
				double[] var99 = state.indirection2[m];
				double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 10)) && (0.0 <= var99[cv$sampleValue])) && (var99[cv$sampleValue] <= 1.0))?Math.log(var99[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample103[m] = cv$distributionAccumulator;
			}
			state.logProbability$generated = (state.logProbability$generated + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample103 = state.fixedFlag$sample61;
		} else {
			double cv$accumulator = 0.0;
			for(int m = 0; m < state.length$observed; m += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample103[m]);
			state.logProbability$generated = (state.logProbability$generated + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample61() {
		if(!state.fixedProbFlag$sample61) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < 10; i += 1) {
				for(int j = 0; j < state.length$observed; j += 1) {
					double cv$sampleValue = state.indirection1[i][j];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 1.0))?0.0:Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					state.logProbability$sample61[i][j] = cv$distributionAccumulator;
					state.logProbability$indirection2 = (state.logProbability$indirection2 + cv$distributionAccumulator);
				}
			}
			state.logProbability$indirection1 = (state.logProbability$indirection1 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample61)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample61 = state.fixedFlag$sample61;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < 10; i += 1) {
				for(int j = 0; j < state.length$observed; j += 1) {
					double cv$sampleValue = state.logProbability$sample61[i][j];
					cv$accumulator = (cv$accumulator + cv$sampleValue);
					state.logProbability$indirection2 = (state.logProbability$indirection2 + cv$sampleValue);
				}
			}
			state.logProbability$indirection1 = (state.logProbability$indirection1 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample61)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample61) {
			for(int i = 0; i < 10; i += 1) {
				double[] var55 = state.indirection1[i];
				for(int j = 0; j < state.length$observed; j += 1)
					var55[j] = DistributionSampling.sampleUniform(state.RNG$);
			}
			for(int k = 0; k < state.length$observed; k += 1) {
				double[] var83 = state.indirection2[k];
				for(int l = 0; l < 10; l += 1)
					var83[l] = state.indirection1[l][k];
			}
		}
		for(int m = 0; m < state.length$observed; m += 1)
			state.generated[m] = DistributionSampling.sampleCategorical(state.RNG$, state.indirection2[m], 10);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample61) {
			for(int i = 0; i < 10; i += 1) {
				double[] var55 = state.indirection1[i];
				for(int j = 0; j < state.length$observed; j += 1)
					var55[j] = DistributionSampling.sampleUniform(state.RNG$);
			}
		}
		for(int k = 0; k < state.length$observed; k += 1) {
			double[] var83 = state.indirection2[k];
			for(int l = 0; l < 10; l += 1)
				var83[l] = state.indirection1[l][k];
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample61) {
			for(int i = 0; i < 10; i += 1) {
				double[] var55 = state.indirection1[i];
				for(int j = 0; j < state.length$observed; j += 1)
					var55[j] = DistributionSampling.sampleUniform(state.RNG$);
			}
		}
		for(int k = 0; k < state.length$observed; k += 1) {
			double[] var83 = state.indirection2[k];
			for(int l = 0; l < 10; l += 1)
				var83[l] = state.indirection1[l][k];
		}
		for(int m = 0; m < state.length$observed; m += 1)
			state.generated[m] = DistributionSampling.sampleCategorical(state.RNG$, state.indirection2[m], 10);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample61) {
			for(int i = 0; i < 10; i += 1) {
				double[] var55 = state.indirection1[i];
				for(int j = 0; j < state.length$observed; j += 1)
					var55[j] = DistributionSampling.sampleUniform(state.RNG$);
			}
			for(int k = 0; k < state.length$observed; k += 1) {
				double[] var83 = state.indirection2[k];
				for(int l = 0; l < 10; l += 1)
					var83[l] = state.indirection1[l][k];
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample61) {
			for(int i = 0; i < 10; i += 1) {
				double[] var55 = state.indirection1[i];
				for(int j = 0; j < state.length$observed; j += 1)
					var55[j] = DistributionSampling.sampleUniform(state.RNG$);
			}
		}
		for(int k = 0; k < state.length$observed; k += 1) {
			double[] var83 = state.indirection2[k];
			for(int l = 0; l < 10; l += 1)
				var83[l] = state.indirection1[l][k];
		}
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample61) {
			if(state.system$gibbsForward) {
				for(int i = 0; i < 10; i += 1) {
					for(int j = 0; j < state.length$observed; j += 1)
						inferSample61(i, j);
				}
			} else {
				for(int i = 9; i >= 0; i -= 1) {
					for(int j = (state.length$observed - 1); j >= 0; j -= 1)
						inferSample61(i, j);
				}
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int i = 0; i < 10; i += 1) {
			for(int j = 0; j < state.length$observed; j += 1) {
				if(!state.constrainedFlag$sample61[i][j])
					drawValueSample61(i, j);
			}
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$indirection1 = 0.0;
		state.logProbability$indirection2 = 0.0;
		if(!state.fixedProbFlag$sample61) {
			for(int i = 0; i < 10; i += 1) {
				for(int j = 0; j < state.length$observed; j += 1)
					state.logProbability$sample61[i][j] = Double.NaN;
			}
		}
		state.logProbability$generated = 0.0;
		if(!state.fixedProbFlag$sample103) {
			for(int m = 0; m < state.length$observed; m += 1)
				state.logProbability$sample103[m] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		for(int index$constrainedFlag$sample61$1 = 0; index$constrainedFlag$sample61$1 < state.constrainedFlag$sample61.length; index$constrainedFlag$sample61$1 += 1) {
			boolean[] cv$constrainedFlag$sample61$1 = state.constrainedFlag$sample61[index$constrainedFlag$sample61$1];
			for(int index$constrainedFlag$sample61$2 = 0; index$constrainedFlag$sample61$2 < cv$constrainedFlag$sample61$1.length; index$constrainedFlag$sample61$2 += 1)
				cv$constrainedFlag$sample61$1[index$constrainedFlag$sample61$2] = true;
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample61)
			logProbabilityValue$sample61();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample61();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample61();
		logProbabilityValue$sample103();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.generated.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.generated[cv$index1] = state.observed[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		for(int k = 0; k < state.length$observed; k += 1) {
			double[] var83 = state.indirection2[k];
			for(int l = 0; l < 10; l += 1)
				var83[l] = state.indirection1[l][k];
		}
	}

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
		     + "public model ParallelMK5(int[] observed) {\n"
		     + "    int[] generated = new int[observed.length];\n"
		     + "    double[][] indirection1 = new double[10][observed.length];\n"
		     + "    double[][] indirection2 = new double[observed.length][10];\n"
		     + "\n"
		     + "    for(int i=0; i<10; i++) {\n"
		     + "        for(int j=0; j<observed.length; j++) {\n"
		     + "            indirection1[i][j] = uniform(0.0, 1.0).sample();\n"
		     + "        }\n"
		     + "    }\n"
		     + "    \n"
		     + "    for(int k=0; k<observed.length; k++) {\n"
		     + "        for(int l=0; l<10; l++) {\n"
		     + "            indirection2[k][l] = indirection1[l][k];\n"
		     + "        }\n"
		     + "    }\n"
		     + "    \n"
		     + "    for(int m=0; m<observed.length; m++) {\n"
		     + "        generated[m] = categorical(indirection2[m]).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    generated.observe(observed);\n"
		     + "}";
	}
}