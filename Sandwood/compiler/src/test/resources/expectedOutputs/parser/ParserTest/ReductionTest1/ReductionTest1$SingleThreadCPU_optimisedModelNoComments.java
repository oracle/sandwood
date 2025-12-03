package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.ReductionTest1$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.ReductionTest1.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class ReductionTest1$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public ReductionTest1$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample101(int i$var80, int var95) {
		state.time_coeff[i$var80][var95] = DistributionSampling.sampleGaussian(state.RNG$);
		for(int t = 1; t < state.T; t += 1)
			state.time_impact[t][i$var80][var95] = (state.TimeFeat[t][var95] * state.time_coeff[i$var80][var95]);
		for(int t = 1; t < state.T; t += 1) {
			double reduceVar$var151$3 = 0.0;
			for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
				reduceVar$var151$3 = (reduceVar$var151$3 + state.time_impact[t][i$var80][cv$reduction152Index]);
			state.sum_t[t][i$var80] = reduceVar$var151$3;
		}
	}

	private final void inferSample101(int i$var80, int var95) {
		state.constrainedFlag$sample101[i$var80][var95] = false;
		double cv$originalValue = state.time_coeff[i$var80][var95];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$originalValue);
			for(int t = 1; t < state.T; t += 1) {
				double reduceVar$var151$1 = 0.0;
				for(int cv$reduction332Index = 0; cv$reduction332Index < var95; cv$reduction332Index += 1)
					reduceVar$var151$1 = (reduceVar$var151$1 + state.time_impact[t][i$var80][cv$reduction332Index]);
				for(int cv$reduction332Index = (var95 + 1); cv$reduction332Index < state.time_dim; cv$reduction332Index += 1)
					reduceVar$var151$1 = (reduceVar$var151$1 + state.time_impact[t][i$var80][cv$reduction332Index]);
				reduceVar$var151$1 = ((state.TimeFeat[t][var95] * cv$originalValue) + reduceVar$var151$1);
				state.constrainedFlag$sample101[i$var80][var95] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityPoisson(state.arr[t][i$var80], reduceVar$var151$1) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample101[i$var80][var95]) {
			state.time_coeff[i$var80][var95] = cv$proposedValue;
			for(int t = 1; t < state.T; t += 1)
				state.time_impact[t][i$var80][var95] = (state.TimeFeat[t][var95] * state.time_coeff[i$var80][var95]);
			for(int t = 1; t < state.T; t += 1) {
				double reduceVar$var151$0 = 0.0;
				for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
					reduceVar$var151$0 = (reduceVar$var151$0 + state.time_impact[t][i$var80][cv$reduction152Index]);
				state.sum_t[t][i$var80] = reduceVar$var151$0;
			}
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian(cv$proposedValue);
			for(int t = 1; t < state.T; t += 1) {
				double reduceVar$var151$1 = 0.0;
				for(int cv$reduction332Index = 0; cv$reduction332Index < var95; cv$reduction332Index += 1)
					reduceVar$var151$1 = (reduceVar$var151$1 + state.time_impact[t][i$var80][cv$reduction332Index]);
				for(int cv$reduction332Index = (var95 + 1); cv$reduction332Index < state.time_dim; cv$reduction332Index += 1)
					reduceVar$var151$1 = (reduceVar$var151$1 + state.time_impact[t][i$var80][cv$reduction332Index]);
				reduceVar$var151$1 = ((state.TimeFeat[t][var95] * cv$proposedValue) + reduceVar$var151$1);
				state.constrainedFlag$sample101[i$var80][var95] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityPoisson(state.arr[t][i$var80], reduceVar$var151$1) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				state.time_coeff[i$var80][var95] = cv$originalValue;
				for(int t = 1; t < state.T; t += 1)
					state.time_impact[t][i$var80][var95] = (state.TimeFeat[t][var95] * state.time_coeff[i$var80][var95]);
				for(int t = 1; t < state.T; t += 1) {
					double reduceVar$var151$2 = 0.0;
					for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
						reduceVar$var151$2 = (reduceVar$var151$2 + state.time_impact[t][i$var80][cv$reduction152Index]);
					state.sum_t[t][i$var80] = reduceVar$var151$2;
				}
			}
		}
	}

	private final void logProbabilityValue$sample101() {
		if(!state.fixedProbFlag$sample101) {
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var95 = 0; var95 < state.time_dim; var95 += 1) {
					double cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(state.time_coeff[i$var80][var95]);
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
					state.logProbability$sample101[i$var80][var95] = cv$distributionAccumulator;
					if((1 < state.T)) {
						state.logProbability$time_impact = (state.logProbability$time_impact + cv$distributionAccumulator);
						state.logProbability$sum_t = (state.logProbability$sum_t + cv$distributionAccumulator);
					}
				}
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			}
			state.logProbability$time_coeff = (state.logProbability$time_coeff + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample101)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample101 = state.fixedFlag$sample101;
		} else {
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
				double cv$rvAccumulator = 0.0;
				for(int var95 = 0; var95 < state.time_dim; var95 += 1) {
					double cv$sampleValue = state.logProbability$sample101[i$var80][var95];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					if((1 < state.T)) {
						state.logProbability$time_impact = (state.logProbability$time_impact + cv$sampleValue);
						state.logProbability$sum_t = (state.logProbability$sum_t + cv$sampleValue);
					}
				}
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$time_coeff = (state.logProbability$time_coeff + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample101)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample165() {
		if(!state.fixedProbFlag$sample165) {
			double cv$accumulator = 0.0;
			for(int t = 1; t < state.T; t += 1) {
				for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
					double cv$distributionAccumulator = DistributionSampling.logProbabilityPoisson(state.arr[t][i$var119], state.sum_t[t][i$var119]);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					state.logProbability$sample165[(t - 1)][i$var119] = cv$distributionAccumulator;
				}
			}
			state.logProbability$arr = (state.logProbability$arr + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample165 = state.fixedFlag$sample101;
		} else {
			double cv$accumulator = 0.0;
			for(int t = 1; t < state.T; t += 1) {
				for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1)
					cv$accumulator = (cv$accumulator + state.logProbability$sample165[(t - 1)][i$var119]);
			}
			state.logProbability$arr = (state.logProbability$arr + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample101) {
			for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
				double[] var86 = state.time_coeff[i$var80];
				for(int var95 = 0; var95 < state.time_dim; var95 += 1)
					var86[var95] = DistributionSampling.sampleGaussian(state.RNG$);
			}
		}
		for(int t = 1; t < state.T; t += 1) {
			double[][] var129 = state.time_impact[t];
			double[] var139 = state.sum_t[t];
			int[] var154 = state.arr[t];
			for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
				if(!state.fixedFlag$sample101) {
					for(int j = 0; j < state.time_dim; j += 1)
						var129[i$var119][j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
					double reduceVar$var151$4 = 0.0;
					for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
						reduceVar$var151$4 = (reduceVar$var151$4 + state.time_impact[t][i$var119][cv$reduction152Index]);
					var139[i$var119] = reduceVar$var151$4;
				}
				var154[i$var119] = DistributionSampling.samplePoisson(state.RNG$, state.sum_t[t][i$var119]);
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample101) {
			for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
				double[] var86 = state.time_coeff[i$var80];
				for(int var95 = 0; var95 < state.time_dim; var95 += 1)
					var86[var95] = DistributionSampling.sampleGaussian(state.RNG$);
			}
		}
		for(int t = 1; t < state.T; t += 1) {
			double[][] var129 = state.time_impact[t];
			double[] var139 = state.sum_t[t];
			for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
				for(int j = 0; j < state.time_dim; j += 1)
					var129[i$var119][j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
				double reduceVar$var151$8 = 0.0;
				for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
					reduceVar$var151$8 = (reduceVar$var151$8 + state.time_impact[t][i$var119][cv$reduction152Index]);
				var139[i$var119] = reduceVar$var151$8;
			}
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample101) {
			for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
				double[] var86 = state.time_coeff[i$var80];
				for(int var95 = 0; var95 < state.time_dim; var95 += 1)
					var86[var95] = DistributionSampling.sampleGaussian(state.RNG$);
			}
		}
		for(int t = 1; t < state.T; t += 1) {
			double[][] var129 = state.time_impact[t];
			double[] var139 = state.sum_t[t];
			int[] var154 = state.arr[t];
			for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
				for(int j = 0; j < state.time_dim; j += 1)
					var129[i$var119][j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
				double reduceVar$var151$5 = 0.0;
				for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
					reduceVar$var151$5 = (reduceVar$var151$5 + state.time_impact[t][i$var119][cv$reduction152Index]);
				var139[i$var119] = reduceVar$var151$5;
				var154[i$var119] = DistributionSampling.samplePoisson(state.RNG$, state.sum_t[t][i$var119]);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample101) {
			for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
				double[] var86 = state.time_coeff[i$var80];
				for(int var95 = 0; var95 < state.time_dim; var95 += 1)
					var86[var95] = DistributionSampling.sampleGaussian(state.RNG$);
			}
			for(int t = 1; t < state.T; t += 1) {
				double[][] var129 = state.time_impact[t];
				double[] var139 = state.sum_t[t];
				for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
					for(int j = 0; j < state.time_dim; j += 1)
						var129[i$var119][j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
					double reduceVar$var151$6 = 0.0;
					for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
						reduceVar$var151$6 = (reduceVar$var151$6 + state.time_impact[t][i$var119][cv$reduction152Index]);
					var139[i$var119] = reduceVar$var151$6;
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample101) {
			for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
				double[] var86 = state.time_coeff[i$var80];
				for(int var95 = 0; var95 < state.time_dim; var95 += 1)
					var86[var95] = DistributionSampling.sampleGaussian(state.RNG$);
			}
		}
		for(int t = 1; t < state.T; t += 1) {
			double[][] var129 = state.time_impact[t];
			double[] var139 = state.sum_t[t];
			for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
				for(int j = 0; j < state.time_dim; j += 1)
					var129[i$var119][j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
				double reduceVar$var151$7 = 0.0;
				for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
					reduceVar$var151$7 = (reduceVar$var151$7 + state.time_impact[t][i$var119][cv$reduction152Index]);
				var139[i$var119] = reduceVar$var151$7;
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample101) {
			if(state.system$gibbsForward) {
				for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
					for(int var95 = 0; var95 < state.time_dim; var95 += 1)
						inferSample101(i$var80, var95);
				}
			} else {
				for(int i$var80 = (state.n_ac - 1); i$var80 >= 0; i$var80 -= 1) {
					for(int var95 = (state.time_dim - 1); var95 >= 0; var95 -= 1)
						inferSample101(i$var80, var95);
				}
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
			for(int var95 = 0; var95 < state.time_dim; var95 += 1) {
				if(!state.constrainedFlag$sample101[i$var80][var95])
					drawValueSample101(i$var80, var95);
			}
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$time_coeff = 0.0;
		state.logProbability$time_impact = 0.0;
		state.logProbability$sum_t = 0.0;
		if(!state.fixedProbFlag$sample101) {
			for(int i$var80 = 0; i$var80 < state.n_ac; i$var80 += 1) {
				for(int var95 = 0; var95 < state.time_dim; var95 += 1)
					state.logProbability$sample101[i$var80][var95] = Double.NaN;
			}
		}
		state.logProbability$arr = 0.0;
		if(!state.fixedProbFlag$sample165) {
			for(int t = 1; t < state.T; t += 1) {
				for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1)
					state.logProbability$sample165[(t - 1)][i$var119] = Double.NaN;
			}
		}
	}

	@Override
	public final void initializeModel() {
		state.time_dim = state.TimeFeat[0].length;
		for(int index$constrainedFlag$sample101$1 = 0; index$constrainedFlag$sample101$1 < state.constrainedFlag$sample101.length; index$constrainedFlag$sample101$1 += 1) {
			boolean[] cv$constrainedFlag$sample101$1 = state.constrainedFlag$sample101[index$constrainedFlag$sample101$1];
			for(int index$constrainedFlag$sample101$2 = 0; index$constrainedFlag$sample101$2 < cv$constrainedFlag$sample101$1.length; index$constrainedFlag$sample101$2 += 1)
				cv$constrainedFlag$sample101$1[index$constrainedFlag$sample101$2] = true;
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample101)
			logProbabilityValue$sample101();
		logProbabilityValue$sample165();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample101();
		logProbabilityValue$sample165();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample101();
		logProbabilityValue$sample165();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.arr.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = state.ObsArr[cv$index1];
			int[] cv$target2 = state.arr[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	@Override
	public final void setIntermediates() {
		for(int t = 1; t < state.T; t += 1) {
			double[][] var129 = state.time_impact[t];
			double[] var139 = state.sum_t[t];
			for(int i$var119 = 0; i$var119 < state.n_ac; i$var119 += 1) {
				for(int j = 0; j < state.time_dim; j += 1)
					var129[i$var119][j] = (state.TimeFeat[t][j] * state.time_coeff[i$var119][j]);
				double reduceVar$var151$9 = 0.0;
				for(int cv$reduction152Index = 0; cv$reduction152Index < state.time_dim; cv$reduction152Index += 1)
					reduceVar$var151$9 = (reduceVar$var151$9 + state.time_impact[t][i$var119][cv$reduction152Index]);
				var139[i$var119] = reduceVar$var151$9;
			}
		}
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model ReductionTest1(int T, int n_ac, int[][] ObsArr, double[][] TimeFeat) {\n"
		     + "    int time_dim = TimeFeat[0].length; //length of the feature vector\n"
		     + "\n"
		     + "\n"
		     + "    double[][] time_coeff = new double[n_ac][time_dim];\n"
		     + "    double[][] sum_t = new double[T][n_ac];\n"
		     + "    double[][][] time_impact = new double[T][n_ac][time_dim];\n"
		     + "    int[][] arr = new int[T][n_ac];\n"
		     + "    \n"
		     + "    for (int i : [0..n_ac))\n"
		     + "        time_coeff[i] = gaussian(0,1).sample(time_dim);\n"
		     + "\n"
		     + "    for (int t : (0..T)) {\n"
		     + "        for (int i : [0..n_ac)){\n"
		     + "            for (int j : [0..time_dim))\n"
		     + "                time_impact[t][i][j] = TimeFeat[t][j]*time_coeff[i][j];\n"
		     + "            //calculate sum\n"
		     + "            sum_t[t][i] = reduce(time_impact[t][i], 0, (x, y) -> { return x + y; });\n"
		     + "            arr[t][i] = poisson(sum_t[t][i]).sample();\n"
		     + "        }\n"
		     + "    }\n"
		     + "    arr.observe(ObsArr);\n"
		     + "}";
	}
}