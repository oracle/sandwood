package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.DistributionTest7$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.DistributionTest7.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DistributionTest7$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var31$stateProbabilityGlobal;
		double[] cv$var43$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			cv$var31$stateProbabilityGlobal = new double[3];
			cv$var43$stateProbabilityGlobal = new double[11];
		}
	}


	public DistributionTest7$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample31() {
		state.cat = DistributionSampling.sampleCategorical(state.RNG$, state.prob, 3);
		if((state.cat == 1))
			state.result = 5;
		else
			state.result = state.var43;
	}

	private final void drawValueSample45() {
		state.var43 = DistributionSampling.sampleBinomial(state.RNG$, state.bias[state.cat], 10);
		state.result = state.var43;
	}

	private final void inferSample31() {
		state.constrainedFlag$sample31 = false;
		if((state.fixedFlag$sample45 || state.constrainedFlag$sample45))
			state.constrainedFlag$sample31 = true;
		state.constrainedFlag$sample31 = true;
		state.constrainedFlag$sample31 = true;
		scratch.cv$var31$stateProbabilityGlobal[0] = Double.NEGATIVE_INFINITY;
		state.constrainedFlag$sample31 = true;
		state.constrainedFlag$sample31 = true;
		scratch.cv$var31$stateProbabilityGlobal[1] = (((0.0 <= state.prob[1]) && (state.prob[1] <= 1.0))?Math.log(state.prob[1]):Double.NEGATIVE_INFINITY);
		double cv$accumulatedProbabilities = (((0.0 <= state.prob[2]) && (state.prob[2] <= 1.0))?Math.log(state.prob[2]):Double.NEGATIVE_INFINITY);
		if((state.fixedFlag$sample45 || state.constrainedFlag$sample45)) {
			state.constrainedFlag$sample31 = true;
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBinomial(state.var43, 0.5, 10) + cv$accumulatedProbabilities);
		}
		state.constrainedFlag$sample31 = true;
		cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((state.data - state.var43) / 1.4142135623730951)) + cv$accumulatedProbabilities) - 0.34657359027997264);
		state.constrainedFlag$sample31 = true;
		cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((state.data - state.var43) / 1.4142135623730951)) + cv$accumulatedProbabilities) - 0.34657359027997264);
		cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBinomial(state.var43, state.bias[2], 10) + cv$accumulatedProbabilities);
		scratch.cv$var31$stateProbabilityGlobal[2] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = scratch.cv$var31$stateProbabilityGlobal[0];
		{
			double cv$lseElementValue = scratch.cv$var31$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		double cv$lseElementValue = scratch.cv$var31$stateProbabilityGlobal[2];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log(((Math.exp((scratch.cv$var31$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var31$stateProbabilityGlobal[1] - cv$lseMax))) + Math.exp((scratch.cv$var31$stateProbabilityGlobal[2] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			state.distribution$sample31[0] = 0.3333333333333333;
			state.distribution$sample31[1] = 0.3333333333333333;
			state.distribution$sample31[2] = 0.3333333333333333;
		} else {
			state.distribution$sample31[0] = Math.exp((scratch.cv$var31$stateProbabilityGlobal[0] - cv$logSum));
			state.distribution$sample31[1] = Math.exp((scratch.cv$var31$stateProbabilityGlobal[1] - cv$logSum));
			state.distribution$sample31[2] = Math.exp((scratch.cv$var31$stateProbabilityGlobal[2] - cv$logSum));
		}
		for(int cv$indexName = 3; cv$indexName < scratch.cv$var31$stateProbabilityGlobal.length; cv$indexName += 1)
			state.distribution$sample31[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void inferSample45() {
		state.constrainedFlag$sample45 = false;
		int cv$numStates = 0;
		if(state.fixedFlag$sample31) {
			if((0 == state.cat))
				cv$numStates = 11;
			if((2 == state.cat))
				cv$numStates = 11;
		} else
			cv$numStates = 11;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			state.var43 = cv$valuePos;
			state.result = cv$valuePos;
			if(state.fixedFlag$sample31) {
				if((0 == state.cat)) {
					cv$reachedDistributionSourceRV = 1.0;
					state.constrainedFlag$sample45 = true;
					cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				}
				if((2 == state.cat)) {
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					state.constrainedFlag$sample45 = true;
					cv$stateProbabilityValue = ((DistributionSampling.logProbabilityGaussian(((state.data - cv$valuePos) / 1.4142135623730951)) + DistributionSampling.logProbabilityBinomial(cv$valuePos, 0.5, 10)) - 0.34657359027997264);
				}
			} else {
				state.constrainedFlag$sample45 = true;
				double cv$probabilitySample31Value32 = state.distribution$sample31[1];
				cv$stateProbabilityValue = (Math.log(cv$probabilitySample31Value32) + DistributionSampling.logProbabilityBinomial(cv$valuePos, 0.3, 10));
				double cv$probabilitySample31Value39 = state.distribution$sample31[2];
				cv$reachedDistributionSourceRV = ((state.distribution$sample31[0] + cv$probabilitySample31Value32) + cv$probabilitySample31Value39);
				state.constrainedFlag$sample45 = true;
				double cv$accumulatedProbabilities = (((DistributionSampling.logProbabilityGaussian(((state.data - cv$valuePos) / 1.4142135623730951)) + Math.log(cv$probabilitySample31Value39)) + DistributionSampling.logProbabilityBinomial(cv$valuePos, 0.5, 10)) - 0.34657359027997264);
				if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
					cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
				else {
					if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					else
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
				}
			}
			scratch.cv$var43$stateProbabilityGlobal[cv$valuePos] = (cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV));
		}
		if(state.constrainedFlag$sample45) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var43$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = scratch.cv$var43$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var43$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					scratch.cv$var43$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					scratch.cv$var43$stateProbabilityGlobal[cv$indexName] = Math.exp((scratch.cv$var43$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var43$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var43$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.var43 = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var43$stateProbabilityGlobal, cv$numStates);
			state.result = state.var43;
		}
	}

	private final void logProbabilityDistribution$sample31() {
		if(!state.fixedProbFlag$sample31) {
			if(state.fixedFlag$sample31) {
				double cv$distributionAccumulator = (((((0.0 <= state.cat) && (state.cat < 3)) && (0.0 <= state.prob[state.cat])) && (state.prob[state.cat] <= 1.0))?Math.log(state.prob[state.cat]):Double.NEGATIVE_INFINITY);
				state.logProbability$cat = cv$distributionAccumulator;
				state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
				state.fixedProbFlag$sample31 = true;
			}
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$cat);
			if(state.fixedFlag$sample31)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$cat);
		}
	}

	private final void logProbabilityDistribution$sample45() {
		if(!state.fixedProbFlag$sample45) {
			double cv$accumulator = 0.0;
			if(!(state.cat == 1)) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				if(state.fixedFlag$sample31) {
					if((0 == state.cat)) {
						cv$distributionAccumulator = DistributionSampling.logProbabilityBinomial(state.var43, 0.2, 10);
						cv$probabilityReached = 1.0;
					}
					if((2 == state.cat)) {
						double cv$weightedProbability = DistributionSampling.logProbabilityBinomial(state.var43, 0.5, 10);
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
								cv$distributionAccumulator = cv$weightedProbability;
							else
								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
						}
						cv$probabilityReached = (cv$probabilityReached + 1.0);
					}
				} else {
					double cv$probabilitySample31Value4 = state.distribution$sample31[0];
					cv$distributionAccumulator = (Math.log(cv$probabilitySample31Value4) + DistributionSampling.logProbabilityBinomial(state.var43, 0.2, 10));
					{
						double cv$probabilitySample31Value11 = state.distribution$sample31[1];
						double cv$weightedProbability = (Math.log(cv$probabilitySample31Value11) + DistributionSampling.logProbabilityBinomial(state.var43, 0.3, 10));
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
								cv$distributionAccumulator = cv$weightedProbability;
							else
								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
						}
						cv$probabilityReached = (cv$probabilitySample31Value4 + cv$probabilitySample31Value11);
					}
					double cv$probabilitySample31Value18 = state.distribution$sample31[2];
					double cv$weightedProbability = (Math.log(cv$probabilitySample31Value18) + DistributionSampling.logProbabilityBinomial(state.var43, 0.5, 10));
					if((cv$weightedProbability < cv$distributionAccumulator))
						cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
					else {
						if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
							cv$distributionAccumulator = cv$weightedProbability;
						else
							cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
					}
					cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample31Value18);
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = cv$distributionAccumulator;
				state.logProbability$sample45 = cv$distributionAccumulator;
			}
			state.logProbability$var43 = (state.logProbability$var43 + cv$accumulator);
			if(((!(state.cat == 1) && state.fixedFlag$sample31) && state.fixedFlag$sample45))
				state.logProbability$result = (state.logProbability$result + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample45)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample45 = (state.fixedFlag$sample45 && state.fixedFlag$sample31);
		} else {
			double cv$accumulator = 0.0;
			if(!(state.cat == 1))
				cv$accumulator = state.logProbability$sample45;
			state.logProbability$var43 = (state.logProbability$var43 + cv$accumulator);
			if(((!(state.cat == 1) && state.fixedFlag$sample31) && state.fixedFlag$sample45))
				state.logProbability$result = (state.logProbability$result + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample45)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample51() {
		if(!state.fixedProbFlag$sample51) {
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			if(state.fixedFlag$sample31) {
				if(!(state.cat == 1)) {
					cv$distributionAccumulator = ((0.0 < (double)state.cat)?(DistributionSampling.logProbabilityGaussian(((state.data - state.var43) / Math.sqrt(state.cat))) - (Math.log(state.cat) * 0.5)):Double.NEGATIVE_INFINITY);
					cv$probabilityReached = 1.0;
				}
			} else {
				double cv$probabilitySample31Value4 = state.distribution$sample31[2];
				cv$distributionAccumulator = ((Math.log(cv$probabilitySample31Value4) + DistributionSampling.logProbabilityGaussian(((state.data - state.var43) / 1.4142135623730951))) - 0.34657359027997264);
				cv$probabilityReached = (state.distribution$sample31[0] + cv$probabilitySample31Value4);
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			state.logProbability$data = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample51 = (state.fixedFlag$sample31 && state.fixedFlag$sample45);
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$data);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$data);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!state.fixedProbFlag$sample31) {
			double cv$distributionAccumulator = (((((0.0 <= state.cat) && (state.cat < 3)) && (0.0 <= state.prob[state.cat])) && (state.prob[state.cat] <= 1.0))?Math.log(state.prob[state.cat]):Double.NEGATIVE_INFINITY);
			state.logProbability$cat = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample31)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample31 = state.fixedFlag$sample31;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$cat);
			if(state.fixedFlag$sample31)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$cat);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!state.fixedProbFlag$sample45) {
			double cv$accumulator = 0.0;
			if(!(state.cat == 1)) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBinomial(state.var43, state.bias[state.cat], 10);
				cv$accumulator = cv$distributionAccumulator;
				state.logProbability$sample45 = cv$distributionAccumulator;
			}
			state.logProbability$var43 = (state.logProbability$var43 + cv$accumulator);
			if(!(state.cat == 1))
				state.logProbability$result = (state.logProbability$result + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample45)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample45 = (state.fixedFlag$sample45 && state.fixedFlag$sample31);
		} else {
			double cv$accumulator = 0.0;
			if(!(state.cat == 1))
				cv$accumulator = state.logProbability$sample45;
			state.logProbability$var43 = (state.logProbability$var43 + cv$accumulator);
			if(!(state.cat == 1))
				state.logProbability$result = (state.logProbability$result + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample45)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample51() {
		if(!state.fixedProbFlag$sample51) {
			double cv$distributionAccumulator = ((0.0 < (double)state.cat)?(DistributionSampling.logProbabilityGaussian(((state.data - state.result) / Math.sqrt(state.cat))) - (Math.log(state.cat) * 0.5)):Double.NEGATIVE_INFINITY);
			state.logProbability$data = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample51 = (state.fixedFlag$sample31 && state.fixedFlag$sample45);
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$data);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$data);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample31)
			state.cat = DistributionSampling.sampleCategorical(state.RNG$, state.prob, 3);
		if((state.cat == 1)) {
			if(!state.fixedFlag$sample31)
				state.result = 5;
		} else {
			if(!state.fixedFlag$sample45)
				state.var43 = DistributionSampling.sampleBinomial(state.RNG$, state.bias[state.cat], 10);
			if((!state.fixedFlag$sample31 || !state.fixedFlag$sample45))
				state.result = state.var43;
		}
		state.data = ((Math.sqrt(state.cat) * DistributionSampling.sampleGaussian(state.RNG$)) + state.result);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample31) {
			state.distribution$sample31[0] = (((0.0 <= state.prob[0]) && (state.prob[0] <= 1.0))?state.prob[0]:0.0);
			state.distribution$sample31[1] = (((0.0 <= state.prob[1]) && (state.prob[1] <= 1.0))?state.prob[1]:0.0);
			state.distribution$sample31[2] = (((0.0 <= state.prob[2]) && (state.prob[2] <= 1.0))?state.prob[2]:0.0);
		}
		if((!(state.cat == 1) && !state.fixedFlag$sample45))
			state.var43 = DistributionSampling.sampleBinomial(state.RNG$, state.bias[state.cat], 10);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample31)
			state.cat = DistributionSampling.sampleCategorical(state.RNG$, state.prob, 3);
		if((state.cat == 1))
			state.result = 5;
		else {
			if(!state.fixedFlag$sample45)
				state.var43 = DistributionSampling.sampleBinomial(state.RNG$, state.bias[state.cat], 10);
			if((!state.fixedFlag$sample31 || !state.fixedFlag$sample45))
				state.result = state.var43;
		}
		state.data = ((Math.sqrt(state.cat) * DistributionSampling.sampleGaussian(state.RNG$)) + state.result);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample31)
			state.cat = DistributionSampling.sampleCategorical(state.RNG$, state.prob, 3);
		if((state.cat == 1)) {
			if(!state.fixedFlag$sample31)
				state.result = 5;
		} else {
			if(!state.fixedFlag$sample45)
				state.var43 = DistributionSampling.sampleBinomial(state.RNG$, state.bias[state.cat], 10);
			if((!state.fixedFlag$sample31 || !state.fixedFlag$sample45))
				state.result = state.var43;
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample31)
			state.cat = DistributionSampling.sampleCategorical(state.RNG$, state.prob, 3);
		if((state.cat == 1))
			state.result = 5;
		else {
			if(!state.fixedFlag$sample45)
				state.var43 = DistributionSampling.sampleBinomial(state.RNG$, state.bias[state.cat], 10);
			if((!state.fixedFlag$sample31 || !state.fixedFlag$sample45))
				state.result = state.var43;
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample31)
				inferSample31();
			if((!(state.cat == 1) && !state.fixedFlag$sample45))
				inferSample45();
		} else {
			if((!(state.cat == 1) && !state.fixedFlag$sample45))
				inferSample45();
			if(!state.fixedFlag$sample31)
				inferSample31();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample31)
			drawValueSample31();
		if((!(state.cat == 1) && !state.constrainedFlag$sample45))
			drawValueSample45();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample31)
			state.logProbability$cat = Double.NaN;
		state.logProbability$var43 = 0.0;
		state.logProbability$result = 0.0;
		if(!state.fixedProbFlag$sample45)
			state.logProbability$sample45 = Double.NaN;
		if(!state.fixedProbFlag$sample51)
			state.logProbability$data = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.bias[0] = 0.2;
		state.bias[1] = 0.3;
		state.bias[2] = 0.5;
		state.prob[0] = 0.2;
		state.prob[1] = 0.4;
		state.prob[2] = 0.4;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample45)
			logProbabilityValue$sample45();
		logProbabilityValue$sample51();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample31();
		logProbabilityDistribution$sample45();
		logProbabilityDistribution$sample51();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample31();
		logProbabilityValue$sample45();
		logProbabilityValue$sample51();
	}

	@Override
	public final void propagateObservedValues() {
		state.data = state.observedData;
	}

	@Override
	public final void setIntermediates() {
		if(!(state.cat == 1)) {
			if((state.fixedFlag$sample31 && state.fixedFlag$sample45))
				state.result = state.var43;
		} else
			state.result = 5;
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model DistributionTest7(double observedData ) {\n"
		     + "\n"
		     + "    double[] bias = {0.2, 0.3, 0.5};\n"
		     + "    double[] prob = {0.2, 0.4, 0.4};\n"
		     + "\n"
		     + "    int cat = categorical(prob).sampleDistribution();\n"
		     + "    int result;\n"
		     + "    if(cat != 1) {\n"
		     + "        result = binomial(bias[cat], 10).sample();\n"
		     + "    } else {\n"
		     + "        result = 5;\n"
		     + "    }\n"
		     + "    \n"
		     + "\n"
		     + "    double data = gaussian(result, (double) cat).sample();\n"
		     + "\n"
		     + "    data.observe(observedData);\n"
		     + "\n"
		     + "    }";
	}
}