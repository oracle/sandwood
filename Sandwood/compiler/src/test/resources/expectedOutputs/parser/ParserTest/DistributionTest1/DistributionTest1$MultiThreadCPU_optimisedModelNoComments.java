package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.DistributionTest1$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.DistributionTest1.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DistributionTest1$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var4$stateProbabilityGlobal;
		double[] cv$var6$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			cv$var4$stateProbabilityGlobal = new double[state.weightings.length];
			cv$var6$stateProbabilityGlobal = new double[state.weightings.length];
		}
	}


	public DistributionTest1$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample4() {
		state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
	}

	private final void drawValueSample6() {
		state.v2 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
	}

	private final void inferSample4() {
		state.constrainedFlag$sample4 = false;
		int cv$numStates = state.weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			int $var147 = state.weightings.length;
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var147) && (0 < $var147)) && (0.0 <= state.weightings[cv$valuePos])) && (state.weightings[cv$valuePos] <= 1.0))?Math.log(state.weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			state.constrainedFlag$sample4 = true;
			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
			double cv$consumerDistributionProbabilityAccumulator = 1.0;
			if(state.fixedFlag$sample6) {
				double var9 = ((double)cv$valuePos / state.v2);
				cv$accumulatedConsumerProbabilities = (((0.0 <= var9) && (var9 <= 1.0))?Math.log((state.v?var9:(1.0 - var9))):Double.NEGATIVE_INFINITY);
				cv$consumerDistributionProbabilityAccumulator = 0.0;
			} else {
				for(int index$sample6$4 = 0; index$sample6$4 < state.weightings.length; index$sample6$4 += 1) {
					double cv$probabilitySample6Value5 = state.distribution$sample6[index$sample6$4];
					double var9 = ((double)cv$valuePos / index$sample6$4);
					if(((Math.log(cv$probabilitySample6Value5) + (((0.0 <= var9) && (var9 <= 1.0))?Math.log((state.v?var9:(1.0 - var9))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample6Value5) + (((0.0 <= var9) && (var9 <= 1.0))?Math.log((state.v?var9:(1.0 - var9))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample6Value5) + (((0.0 <= var9) && (var9 <= 1.0))?Math.log((state.v?var9:(1.0 - var9))):Double.NEGATIVE_INFINITY));
						else
							cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample6Value5) + (((0.0 <= var9) && (var9 <= 1.0))?Math.log((state.v?var9:(1.0 - var9))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample6Value5)) + (((0.0 <= var9) && (var9 <= 1.0))?Math.log((state.v?var9:(1.0 - var9))):Double.NEGATIVE_INFINITY));
					}
					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample6Value5);
				}
			}
			cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
			if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
				cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
			else {
				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
					cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
				else
					cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
			}
			scratch.cv$var4$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample4) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var4$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = scratch.cv$var4$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var4$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					state.distribution$sample4[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					state.distribution$sample4[cv$indexName] = Math.exp((scratch.cv$var4$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var4$stateProbabilityGlobal.length; cv$indexName += 1)
				state.distribution$sample4[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void inferSample6() {
		state.constrainedFlag$sample6 = false;
		int cv$numStates = state.weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			int $var160 = state.weightings.length;
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var160) && (0 < $var160)) && (0.0 <= state.weightings[cv$valuePos])) && (state.weightings[cv$valuePos] <= 1.0))?Math.log(state.weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			state.constrainedFlag$sample6 = true;
			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
			double cv$consumerDistributionProbabilityAccumulator = 1.0;
			if(state.fixedFlag$sample4) {
				double var9 = ((double)state.v1 / cv$valuePos);
				cv$accumulatedConsumerProbabilities = (((0.0 <= var9) && (var9 <= 1.0))?Math.log((state.v?var9:(1.0 - var9))):Double.NEGATIVE_INFINITY);
				cv$consumerDistributionProbabilityAccumulator = 0.0;
			} else {
				for(int index$sample4$4 = 0; index$sample4$4 < state.weightings.length; index$sample4$4 += 1) {
					double cv$probabilitySample4Value5 = state.distribution$sample4[index$sample4$4];
					double var9 = ((double)index$sample4$4 / cv$valuePos);
					if(((Math.log(cv$probabilitySample4Value5) + (((0.0 <= var9) && (var9 <= 1.0))?Math.log((state.v?var9:(1.0 - var9))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample4Value5) + (((0.0 <= var9) && (var9 <= 1.0))?Math.log((state.v?var9:(1.0 - var9))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample4Value5) + (((0.0 <= var9) && (var9 <= 1.0))?Math.log((state.v?var9:(1.0 - var9))):Double.NEGATIVE_INFINITY));
						else
							cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample4Value5) + (((0.0 <= var9) && (var9 <= 1.0))?Math.log((state.v?var9:(1.0 - var9))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample4Value5)) + (((0.0 <= var9) && (var9 <= 1.0))?Math.log((state.v?var9:(1.0 - var9))):Double.NEGATIVE_INFINITY));
					}
					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample4Value5);
				}
			}
			cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
			if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
				cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
			else {
				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
					cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
				else
					cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
			}
			scratch.cv$var6$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample6) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var6$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = scratch.cv$var6$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var6$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					state.distribution$sample6[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					state.distribution$sample6[cv$indexName] = Math.exp((scratch.cv$var6$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var6$stateProbabilityGlobal.length; cv$indexName += 1)
				state.distribution$sample6[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void logProbabilityDistribution$sample11() {
		if(!state.fixedProbFlag$sample11) {
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			if(state.fixedFlag$sample4) {
				if(state.fixedFlag$sample6) {
					double var9 = ((double)state.v1 / state.v2);
					cv$distributionAccumulator = (((0.0 <= var9) && (var9 <= 1.0))?Math.log((state.v?var9:(1.0 - var9))):Double.NEGATIVE_INFINITY);
					cv$probabilityReached = 1.0;
				} else {
					for(int index$sample6$8 = 0; index$sample6$8 < state.weightings.length; index$sample6$8 += 1) {
						double cv$probabilitySample6Value9 = state.distribution$sample6[index$sample6$8];
						double var9 = ((double)state.v1 / index$sample6$8);
						double cv$weightedProbability = (Math.log(cv$probabilitySample6Value9) + (((0.0 <= var9) && (var9 <= 1.0))?Math.log((state.v?var9:(1.0 - var9))):Double.NEGATIVE_INFINITY));
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
								cv$distributionAccumulator = cv$weightedProbability;
							else
								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
						}
						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample6Value9);
					}
				}
			} else {
				for(int index$sample4$3 = 0; index$sample4$3 < state.weightings.length; index$sample4$3 += 1) {
					double cv$probabilitySample4Value4 = state.distribution$sample4[index$sample4$3];
					if(state.fixedFlag$sample6) {
						double var9 = ((double)index$sample4$3 / state.v2);
						double cv$weightedProbability = (Math.log(cv$probabilitySample4Value4) + (((0.0 <= var9) && (var9 <= 1.0))?Math.log((state.v?var9:(1.0 - var9))):Double.NEGATIVE_INFINITY));
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
								cv$distributionAccumulator = cv$weightedProbability;
							else
								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
						}
						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample4Value4);
					} else {
						for(int index$sample6$13 = 0; index$sample6$13 < state.weightings.length; index$sample6$13 += 1) {
							double cv$probabilitySample6Value14 = (cv$probabilitySample4Value4 * state.distribution$sample6[index$sample6$13]);
							double var9 = ((double)index$sample4$3 / index$sample6$13);
							double cv$weightedProbability = (Math.log(cv$probabilitySample6Value14) + (((0.0 <= var9) && (var9 <= 1.0))?Math.log((state.v?var9:(1.0 - var9))):Double.NEGATIVE_INFINITY));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample6Value14);
						}
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			state.logProbability$v = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample11 = (state.fixedFlag$sample4 && state.fixedFlag$sample6);
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$v);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$v);
		}
	}

	private final void logProbabilityDistribution$sample4() {
		if(!state.fixedProbFlag$sample4) {
			if(state.fixedFlag$sample4) {
				double cv$distributionAccumulator = ((((((0.0 <= state.v1) && (state.v1 < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[state.v1])) && (state.weightings[state.v1] <= 1.0))?Math.log(state.weightings[state.v1]):Double.NEGATIVE_INFINITY);
				state.logProbability$v1 = cv$distributionAccumulator;
				state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
				state.fixedProbFlag$sample4 = true;
			}
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$v1);
			if(state.fixedFlag$sample4)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$v1);
		}
	}

	private final void logProbabilityDistribution$sample6() {
		if(!state.fixedProbFlag$sample6) {
			if(state.fixedFlag$sample6) {
				double cv$distributionAccumulator = ((((((0.0 <= state.v2) && (state.v2 < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[state.v2])) && (state.weightings[state.v2] <= 1.0))?Math.log(state.weightings[state.v2]):Double.NEGATIVE_INFINITY);
				state.logProbability$v2 = cv$distributionAccumulator;
				state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
				state.fixedProbFlag$sample6 = true;
			}
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$v2);
			if(state.fixedFlag$sample6)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$v2);
		}
	}

	private final void logProbabilityValue$sample11() {
		if(!state.fixedProbFlag$sample11) {
			double var9 = ((double)state.v1 / state.v2);
			double cv$distributionAccumulator = (((0.0 <= var9) && (var9 <= 1.0))?Math.log((state.v?var9:(1.0 - var9))):Double.NEGATIVE_INFINITY);
			state.logProbability$v = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample11 = (state.fixedFlag$sample4 && state.fixedFlag$sample6);
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$v);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$v);
		}
	}

	private final void logProbabilityValue$sample4() {
		if(!state.fixedProbFlag$sample4) {
			double cv$distributionAccumulator = ((((((0.0 <= state.v1) && (state.v1 < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[state.v1])) && (state.weightings[state.v1] <= 1.0))?Math.log(state.weightings[state.v1]):Double.NEGATIVE_INFINITY);
			state.logProbability$v1 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample4)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample4 = state.fixedFlag$sample4;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$v1);
			if(state.fixedFlag$sample4)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$v1);
		}
	}

	private final void logProbabilityValue$sample6() {
		if(!state.fixedProbFlag$sample6) {
			double cv$distributionAccumulator = ((((((0.0 <= state.v2) && (state.v2 < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[state.v2])) && (state.weightings[state.v2] <= 1.0))?Math.log(state.weightings[state.v2]):Double.NEGATIVE_INFINITY);
			state.logProbability$v2 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample6)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample6 = state.fixedFlag$sample6;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$v2);
			if(state.fixedFlag$sample6)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$v2);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample4)
			state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample6)
			state.v2 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		state.v = DistributionSampling.sampleBernoulli(state.RNG$, ((double)state.v1 / state.v2));
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample4) {
			for(int index$var3 = 0; index$var3 < state.weightings.length; index$var3 += 1)
				state.distribution$sample4[index$var3] = ((((0 < state.weightings.length) && (0.0 <= state.weightings[index$var3])) && (state.weightings[index$var3] <= 1.0))?state.weightings[index$var3]:0.0);
		}
		if(!state.fixedFlag$sample6) {
			for(int index$var5 = 0; index$var5 < state.weightings.length; index$var5 += 1)
				state.distribution$sample6[index$var5] = ((((0 < state.weightings.length) && (0.0 <= state.weightings[index$var5])) && (state.weightings[index$var5] <= 1.0))?state.weightings[index$var5]:0.0);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample4)
			state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample6)
			state.v2 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		state.v = DistributionSampling.sampleBernoulli(state.RNG$, ((double)state.v1 / state.v2));
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample4)
			state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample6)
			state.v2 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample4)
			state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample6)
			state.v2 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample4)
				inferSample4();
			if(!state.fixedFlag$sample6)
				inferSample6();
		} else {
			if(!state.fixedFlag$sample6)
				inferSample6();
			if(!state.fixedFlag$sample4)
				inferSample4();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample4)
			drawValueSample4();
		if(!state.constrainedFlag$sample6)
			drawValueSample6();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample4)
			state.logProbability$v1 = Double.NaN;
		if(!state.fixedProbFlag$sample6)
			state.logProbability$v2 = Double.NaN;
		if(!state.fixedProbFlag$sample11)
			state.logProbability$v = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample11();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample4();
		logProbabilityDistribution$sample6();
		logProbabilityDistribution$sample11();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample4();
		logProbabilityValue$sample6();
		logProbabilityValue$sample11();
	}

	@Override
	public final void propagateObservedValues() {
		state.v = state.value;
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
		     + "model DistributionTest1(double[] weightings, boolean value) {\n"
		     + "    int v1 = categorical(weightings).sampleDistribution();\n"
		     + "    int v2 = categorical(weightings).sampleDistribution();\n"
		     + "    boolean v = bernoulli((1.0*v1)/v2).sample();\n"
		     + "    v.observe(value);\n"
		     + "}\n"
		     + "";
	}
}