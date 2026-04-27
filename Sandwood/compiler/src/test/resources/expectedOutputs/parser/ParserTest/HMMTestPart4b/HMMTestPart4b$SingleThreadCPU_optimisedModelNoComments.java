package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMTestPart4b$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMTestPart4b.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMTestPart4b$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var119$stateProbabilityGlobal;
		double[] cv$var28$countGlobal;
		double[] cv$var79$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			cv$var28$countGlobal = new double[2];
			cv$var79$stateProbabilityGlobal = new double[2];
			cv$var119$stateProbabilityGlobal = new double[2];
		}
	}


	public HMMTestPart4b$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample122(int i$var95, int j$var104, int k) {
		state.st[i$var95][j$var104][k] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
	}

	private final void drawValueSample28(int var27) {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[var27]);
	}

	private final void drawValueSample45(int var43) {
		state.bias[var43] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void drawValueSample82() {
		state.st[0][0][0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
	}

	private final void inferSample122(int i$var95, int j$var104, int k) {
		{
			double[] var117 = state.m[0];
			double var182 = state.bias[0];
			scratch.cv$var119$stateProbabilityGlobal[0] = ((((0.0 <= var182) && (var182 <= 1.0))?Math.log((state.flips[j$var104][k][i$var95]?var182:(1.0 - var182))):Double.NEGATIVE_INFINITY) + (((0.0 <= var117[0]) && (var117[0] <= 1.0))?Math.log(var117[0]):Double.NEGATIVE_INFINITY));
		}
		state.st[i$var95][j$var104][k] = 1;
		double[] var117 = state.m[0];
		state.constrainedFlag$sample122[(i$var95 - 1)][j$var104][k] = true;
		double var182 = state.bias[1];
		scratch.cv$var119$stateProbabilityGlobal[1] = ((((0.0 <= var182) && (var182 <= 1.0))?Math.log((state.flips[j$var104][k][i$var95]?var182:(1.0 - var182))):Double.NEGATIVE_INFINITY) + (((0.0 <= var117[1]) && (var117[1] <= 1.0))?Math.log(var117[1]):Double.NEGATIVE_INFINITY));
		if(state.constrainedFlag$sample122[(i$var95 - 1)][j$var104][k]) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var119$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var119$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var119$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var119$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var119$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var119$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var119$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var119$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var119$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var119$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var119$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var119$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.st[i$var95][j$var104][k] = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var119$stateProbabilityGlobal, 2);
		}
	}

	private final void inferSample28(int var27) {
		state.constrainedFlag$sample28[var27] = false;
		scratch.cv$var28$countGlobal[0] = 0.0;
		scratch.cv$var28$countGlobal[1] = 0.0;
		if((var27 == 0)) {
			if((state.fixedFlag$sample82 || state.constrainedFlag$sample82)) {
				state.constrainedFlag$sample28[0] = true;
				scratch.cv$var28$countGlobal[state.st[0][0][0]] = (scratch.cv$var28$countGlobal[state.st[0][0][0]] + 1.0);
			}
			for(int i$var95 = 1; i$var95 < state.samples; i$var95 += 1) {
				for(int j$var104 = 0; j$var104 < state.samples; j$var104 += 1) {
					for(int k = 0; k < state.samples; k += 1) {
						if((state.fixedFlag$sample122 || state.constrainedFlag$sample122[(i$var95 - 1)][j$var104][k])) {
							state.constrainedFlag$sample28[0] = true;
							scratch.cv$var28$countGlobal[state.st[i$var95][j$var104][k]] = (scratch.cv$var28$countGlobal[state.st[i$var95][j$var104][k]] + 1.0);
						}
					}
				}
			}
		}
		if(state.constrainedFlag$sample28[var27])
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, scratch.cv$var28$countGlobal, state.m[var27], 2);
	}

	private final void inferSample45(int var43) {
		state.constrainedFlag$sample45[var43] = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int l = 0; l < state.samples; l += 1) {
			for(int p = 0; p < state.samples; p += 1) {
				for(int n = 0; n < state.samples; n += 1) {
					if((var43 == state.st[p][l][n])) {
						state.constrainedFlag$sample45[var43] = true;
						cv$count = (cv$count + 1);
						if(state.flips[l][n][p])
							cv$sum = (cv$sum + 1);
					}
				}
			}
		}
		if(state.constrainedFlag$sample45[var43])
			state.bias[var43] = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void inferSample82() {
		state.constrainedFlag$sample82 = false;
		{
			double[] var77 = state.m[0];
			double cv$accumulatedProbabilities = (((0.0 <= var77[0]) && (var77[0] <= 1.0))?Math.log(var77[0]):Double.NEGATIVE_INFINITY);
			if((0 < state.samples)) {
				state.constrainedFlag$sample82 = true;
				double var182 = state.bias[0];
				cv$accumulatedProbabilities = ((((0.0 <= var182) && (var182 <= 1.0))?Math.log((state.flips[0][0][0]?var182:(1.0 - var182))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			scratch.cv$var79$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.st[0][0][0] = 1;
		double[] var77 = state.m[0];
		double cv$accumulatedProbabilities = (((0.0 <= var77[1]) && (var77[1] <= 1.0))?Math.log(var77[1]):Double.NEGATIVE_INFINITY);
		if((0 < state.samples)) {
			state.constrainedFlag$sample82 = true;
			double var182 = state.bias[1];
			cv$accumulatedProbabilities = ((((0.0 <= var182) && (var182 <= 1.0))?Math.log((state.flips[0][0][0]?var182:(1.0 - var182))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		scratch.cv$var79$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample82) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var79$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var79$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var79$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var79$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var79$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var79$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var79$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var79$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var79$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var79$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var79$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var79$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.st[0][0][0] = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var79$stateProbabilityGlobal, 2);
		}
	}

	private final void logProbabilityValue$sample122() {
		if(!state.fixedProbFlag$sample122) {
			double cv$accumulator = 0.0;
			for(int i$var95 = 1; i$var95 < state.samples; i$var95 += 1) {
				for(int j$var104 = 0; j$var104 < state.samples; j$var104 += 1) {
					for(int k = 0; k < state.samples; k += 1) {
						int cv$sampleValue = state.st[i$var95][j$var104][k];
						double[] var117 = state.m[0];
						double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0.0 <= var117[cv$sampleValue])) && (var117[cv$sampleValue] <= 1.0))?Math.log(var117[cv$sampleValue]):Double.NEGATIVE_INFINITY);
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						state.logProbability$sample122[(i$var95 - 1)][j$var104][k] = cv$distributionAccumulator;
					}
				}
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample122)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample122 = (state.fixedFlag$sample122 && state.fixedFlag$sample28);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var95 = 1; i$var95 < state.samples; i$var95 += 1) {
				for(int j$var104 = 0; j$var104 < state.samples; j$var104 += 1) {
					for(int k = 0; k < state.samples; k += 1)
						cv$accumulator = (cv$accumulator + state.logProbability$sample122[(i$var95 - 1)][j$var104][k]);
				}
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample122)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample189() {
		if(!state.fixedProbFlag$sample189) {
			double cv$accumulator = 0.0;
			for(int l = 0; l < state.samples; l += 1) {
				for(int p = 0; p < state.samples; p += 1) {
					for(int n = 0; n < state.samples; n += 1) {
						double var182 = state.bias[state.st[p][l][n]];
						double cv$distributionAccumulator = (((0.0 <= var182) && (var182 <= 1.0))?Math.log((state.flips[l][n][p]?var182:(1.0 - var182))):Double.NEGATIVE_INFINITY);
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						state.logProbability$sample189[l][p][n] = cv$distributionAccumulator;
					}
				}
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample189 = ((state.fixedFlag$sample45 && state.fixedFlag$sample82) && state.fixedFlag$sample122);
		} else {
			double cv$accumulator = 0.0;
			for(int l = 0; l < state.samples; l += 1) {
				for(int p = 0; p < state.samples; p += 1) {
					for(int n = 0; n < state.samples; n += 1)
						cv$accumulator = (cv$accumulator + state.logProbability$sample189[l][p][n]);
				}
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!state.fixedProbFlag$sample28) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityDirichlet(state.m[0], state.v, 2) + DistributionSampling.logProbabilityDirichlet(state.m[1], state.v, 2));
			state.logProbability$var28 = cv$sampleAccumulator;
			state.logProbability$m = (state.logProbability$m + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample28 = state.fixedFlag$sample28;
		} else {
			state.logProbability$m = (state.logProbability$m + state.logProbability$var28);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var28);
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var28);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!state.fixedProbFlag$sample45) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityBeta(state.bias[0], 1.0, 1.0) + DistributionSampling.logProbabilityBeta(state.bias[1], 1.0, 1.0));
			state.logProbability$var44 = cv$sampleAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample45)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample45 = state.fixedFlag$sample45;
		} else {
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$var44);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var44);
			if(state.fixedFlag$sample45)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var44);
		}
	}

	private final void logProbabilityValue$sample82() {
		if(!state.fixedProbFlag$sample82) {
			int cv$sampleValue = state.st[0][0][0];
			double[] var77 = state.m[0];
			double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0.0 <= var77[cv$sampleValue])) && (var77[cv$sampleValue] <= 1.0))?Math.log(var77[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			state.logProbability$var79 = cv$distributionAccumulator;
			state.logProbability$st = (state.logProbability$st + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample82)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample82 = (state.fixedFlag$sample82 && state.fixedFlag$sample28);
		} else {
			state.logProbability$st = (state.logProbability$st + state.logProbability$var79);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var79);
			if(state.fixedFlag$sample82)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var79);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[0]);
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[1]);
		}
		if(!state.fixedFlag$sample45) {
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			state.bias[1] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample82)
			state.st[0][0][0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		if(!state.fixedFlag$sample122) {
			for(int i$var95 = 1; i$var95 < state.samples; i$var95 += 1) {
				int[][] var114 = state.st[i$var95];
				for(int j$var104 = 0; j$var104 < state.samples; j$var104 += 1) {
					for(int k = 0; k < state.samples; k += 1)
						var114[j$var104][k] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
				}
			}
		}
		for(int l = 0; l < state.samples; l += 1) {
			boolean[][] var177 = state.flips[l];
			for(int p = 0; p < state.samples; p += 1) {
				for(int n = 0; n < state.samples; n += 1)
					var177[n][p] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[state.st[p][l][n]]);
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[0]);
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[1]);
		}
		if(!state.fixedFlag$sample45) {
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			state.bias[1] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample82)
			state.st[0][0][0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		if(!state.fixedFlag$sample122) {
			for(int i$var95 = 1; i$var95 < state.samples; i$var95 += 1) {
				int[][] var114 = state.st[i$var95];
				for(int j$var104 = 0; j$var104 < state.samples; j$var104 += 1) {
					for(int k = 0; k < state.samples; k += 1)
						var114[j$var104][k] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[0]);
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[1]);
		}
		if(!state.fixedFlag$sample45) {
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			state.bias[1] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample82)
			state.st[0][0][0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		if(!state.fixedFlag$sample122) {
			for(int i$var95 = 1; i$var95 < state.samples; i$var95 += 1) {
				int[][] var114 = state.st[i$var95];
				for(int j$var104 = 0; j$var104 < state.samples; j$var104 += 1) {
					for(int k = 0; k < state.samples; k += 1)
						var114[j$var104][k] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
				}
			}
		}
		for(int l = 0; l < state.samples; l += 1) {
			boolean[][] var177 = state.flips[l];
			for(int p = 0; p < state.samples; p += 1) {
				for(int n = 0; n < state.samples; n += 1)
					var177[n][p] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[state.st[p][l][n]]);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[0]);
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[1]);
		}
		if(!state.fixedFlag$sample45) {
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			state.bias[1] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample82)
			state.st[0][0][0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		if(!state.fixedFlag$sample122) {
			for(int i$var95 = 1; i$var95 < state.samples; i$var95 += 1) {
				int[][] var114 = state.st[i$var95];
				for(int j$var104 = 0; j$var104 < state.samples; j$var104 += 1) {
					for(int k = 0; k < state.samples; k += 1)
						var114[j$var104][k] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[0]);
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[1]);
		}
		if(!state.fixedFlag$sample45) {
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			state.bias[1] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample82)
			state.st[0][0][0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		if(!state.fixedFlag$sample122) {
			for(int i$var95 = 1; i$var95 < state.samples; i$var95 += 1) {
				int[][] var114 = state.st[i$var95];
				for(int j$var104 = 0; j$var104 < state.samples; j$var104 += 1) {
					for(int k = 0; k < state.samples; k += 1)
						var114[j$var104][k] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
				}
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample28) {
				inferSample28(0);
				inferSample28(1);
			}
			if(!state.fixedFlag$sample45) {
				inferSample45(0);
				inferSample45(1);
			}
			if(!state.fixedFlag$sample82)
				inferSample82();
			if(!state.fixedFlag$sample122) {
				for(int i$var95 = 1; i$var95 < state.samples; i$var95 += 1) {
					for(int j$var104 = 0; j$var104 < state.samples; j$var104 += 1) {
						for(int k = 0; k < state.samples; k += 1)
							inferSample122(i$var95, j$var104, k);
					}
				}
			}
		} else {
			if(!state.fixedFlag$sample122) {
				for(int i$var95 = (state.samples - 1); i$var95 >= 1; i$var95 -= 1) {
					for(int j$var104 = (state.samples - 1); j$var104 >= 0; j$var104 -= 1) {
						for(int k = (state.samples - 1); k >= 0; k -= 1)
							inferSample122(i$var95, j$var104, k);
					}
				}
			}
			if(!state.fixedFlag$sample82)
				inferSample82();
			if(!state.fixedFlag$sample45) {
				inferSample45(1);
				inferSample45(0);
			}
			if(!state.fixedFlag$sample28) {
				inferSample28(1);
				inferSample28(0);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample28[0])
			drawValueSample28(0);
		if(!state.constrainedFlag$sample28[1])
			drawValueSample28(1);
		if(!state.constrainedFlag$sample45[0])
			drawValueSample45(0);
		if(!state.constrainedFlag$sample45[1])
			drawValueSample45(1);
		if(!state.constrainedFlag$sample82)
			drawValueSample82();
		for(int i$var95 = 1; i$var95 < state.samples; i$var95 += 1) {
			for(int j$var104 = 0; j$var104 < state.samples; j$var104 += 1) {
				for(int k = 0; k < state.samples; k += 1) {
					if(!state.constrainedFlag$sample122[(i$var95 - 1)][j$var104][k])
						drawValueSample122(i$var95, j$var104, k);
				}
			}
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$m = 0.0;
		if(!state.fixedProbFlag$sample28)
			state.logProbability$var28 = Double.NaN;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample45)
			state.logProbability$var44 = Double.NaN;
		state.logProbability$st = 0.0;
		if(!state.fixedProbFlag$sample82)
			state.logProbability$var79 = Double.NaN;
		if(!state.fixedProbFlag$sample122) {
			for(int i$var95 = 1; i$var95 < state.samples; i$var95 += 1) {
				for(int j$var104 = 0; j$var104 < state.samples; j$var104 += 1) {
					for(int k = 0; k < state.samples; k += 1)
						state.logProbability$sample122[(i$var95 - 1)][j$var104][k] = Double.NaN;
				}
			}
		}
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample189) {
			for(int l = 0; l < state.samples; l += 1) {
				for(int p = 0; p < state.samples; p += 1) {
					for(int n = 0; n < state.samples; n += 1)
						state.logProbability$sample189[l][p][n] = Double.NaN;
				}
			}
		}
	}

	@Override
	public final void initializeModel() {
		state.v[0] = 0.1;
		state.v[1] = 0.1;
		state.samples = state.length$flipsMeasured.length;
		for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < state.constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
			state.constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
		for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < state.constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
			state.constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
		for(int index$constrainedFlag$sample122$1 = 0; index$constrainedFlag$sample122$1 < state.constrainedFlag$sample122.length; index$constrainedFlag$sample122$1 += 1) {
			boolean[][] cv$constrainedFlag$sample122$1 = state.constrainedFlag$sample122[index$constrainedFlag$sample122$1];
			for(int index$constrainedFlag$sample122$2 = 0; index$constrainedFlag$sample122$2 < cv$constrainedFlag$sample122$1.length; index$constrainedFlag$sample122$2 += 1) {
				boolean[] cv$constrainedFlag$sample122$2 = cv$constrainedFlag$sample122$1[index$constrainedFlag$sample122$2];
				for(int index$constrainedFlag$sample122$3 = 0; index$constrainedFlag$sample122$3 < cv$constrainedFlag$sample122$2.length; index$constrainedFlag$sample122$3 += 1)
					cv$constrainedFlag$sample122$2[index$constrainedFlag$sample122$3] = true;
			}
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(state.fixedFlag$sample45)
			logProbabilityValue$sample45();
		if(state.fixedFlag$sample82)
			logProbabilityValue$sample82();
		if(state.fixedFlag$sample122)
			logProbabilityValue$sample122();
		logProbabilityValue$sample189();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample82();
		logProbabilityValue$sample122();
		logProbabilityValue$sample189();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample82();
		logProbabilityValue$sample122();
		logProbabilityValue$sample189();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[][] cv$source2 = state.flipsMeasured[cv$index1];
			boolean[][] cv$target2 = state.flips[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1) {
				boolean[] cv$source3 = cv$source2[cv$index2];
				boolean[] cv$target3 = cv$target2[cv$index2];
				int cv$length3 = cv$target3.length;
				for(int cv$index3 = 0; cv$index3 < cv$length3; cv$index3 += 1)
					cv$target3[cv$index3] = cv$source3[cv$index3];
			}
		}
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
		     + "model HMMTestPart4b(boolean[][][] flipsMeasured) {\n"
		     + "        int states = 2;\n"
		     + "\n"
		     + "        double[] v = new double[states];\n"
		     + "        for(int i:[0..states))\n"
		     + "            v[i] = 0.1;\n"
		     + "        \n"
		     + "        double[][] m = dirichlet(v).sample(states);\n"
		     + "        double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "        int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "        int[][][] st = new int[samples][samples][samples];\n"
		     + "\n"
		     + "        st[0][0][0] = categorical(m[0]).sample();\n"
		     + "\n"
		     + "        for(int i:[1..samples))\n"
		     + "            for(int j:[0..samples))\n"
		     + "                for(int k:[0..samples))\n"
		     + "                    st[i][j][k] = categorical(m[0]).sample();\n"
		     + "            \n"
		     + "        boolean[][][] flips = new boolean[samples][][];\n"
		     + "        for(int i:[0..samples)) {\n"
		     + "            flips[i] = new boolean[samples][];\n"
		     + "            for(int j:[0..samples))\n"
		     + "                flips[i][j] = new boolean[samples];\n"
		     + "        }\n"
		     + "            \n"
		     + "        for(int l:[0..samples))\n"
		     + "            for(int p:[0..samples))\n"
		     + "                for(int n:[0..samples))\n"
		     + "                    flips[l][n][p] = bernoulli(bias[st[p][l][n]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}