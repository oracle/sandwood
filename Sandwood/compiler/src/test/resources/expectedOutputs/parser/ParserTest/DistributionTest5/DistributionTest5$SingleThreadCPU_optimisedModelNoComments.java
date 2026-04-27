package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.DistributionTest5$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.DistributionTest5.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DistributionTest5$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var11$stateProbabilityGlobal;
		double[] cv$var27$stateProbabilityGlobal;
		double[] cv$var5$stateProbabilityGlobal;
		boolean[] guard$sample11bernoulli69$global;
		boolean[] guard$sample27bernoulli69$global;

		@Override
		public final void allocateScratch() {
			cv$var5$stateProbabilityGlobal = new double[state.weightings.length];
			cv$var11$stateProbabilityGlobal = new double[state.weightings.length];
			guard$sample11bernoulli69$global = new boolean[state.length$value];
			cv$var27$stateProbabilityGlobal = new double[state.weightings.length];
			guard$sample27bernoulli69$global = new boolean[state.length$value];
		}
	}


	public DistributionTest5$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample11() {
		state.v2[0] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if((0 <= state.size))
			state.v3[0] = state.v2[0];
	}

	private final void drawValueSample27(int i) {
		state.v2[(i + 1)] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		int k = (i + 1);
		state.v3[k] = state.v2[k];
	}

	private final void drawValueSample5() {
		state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
	}

	private final void inferSample11() {
		state.constrainedFlag$sample11 = false;
		int cv$numStates = state.weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			int $var552 = state.weightings.length;
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var552) && (0 < $var552)) && (0.0 <= state.weightings[cv$valuePos])) && (state.weightings[cv$valuePos] <= 1.0))?Math.log(state.weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((0 < state.size)) {
				scratch.guard$sample11bernoulli69$global[0] = false;
				if(!scratch.guard$sample11bernoulli69$global[0]) {
					scratch.guard$sample11bernoulli69$global[0] = true;
					state.constrainedFlag$sample11 = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(state.fixedFlag$sample5) {
						if(state.fixedFlag$sample27) {
							double var68 = ((double)((state.v1 + cv$valuePos) + state.v2[1]) / state.v2[1]);
							cv$accumulatedConsumerProbabilities = (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							for(int index$sample27$130 = 0; index$sample27$130 < state.weightings.length; index$sample27$130 += 1) {
								double cv$probabilitySample27Value131 = state.distribution$sample27[0][index$sample27$130];
								double var68 = ((double)((state.v1 + cv$valuePos) + index$sample27$130) / index$sample27$130);
								if(((Math.log(cv$probabilitySample27Value131) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value131) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value131) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value131) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value131)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value131);
							}
						}
					} else {
						for(int index$sample5$124 = 0; index$sample5$124 < state.weightings.length; index$sample5$124 += 1) {
							double cv$probabilitySample5Value125 = state.distribution$sample5[index$sample5$124];
							if(state.fixedFlag$sample27) {
								double var68 = ((double)((index$sample5$124 + cv$valuePos) + state.v2[1]) / state.v2[1]);
								if(((Math.log(cv$probabilitySample5Value125) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value125) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value125) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value125) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value125)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value125);
							} else {
								for(int index$sample27$136 = 0; index$sample27$136 < state.weightings.length; index$sample27$136 += 1) {
									double cv$probabilitySample27Value137 = (cv$probabilitySample5Value125 * state.distribution$sample27[0][index$sample27$136]);
									double var68 = ((double)((index$sample5$124 + cv$valuePos) + index$sample27$136) / index$sample27$136);
									if(((Math.log(cv$probabilitySample27Value137) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value137) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value137) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value137) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value137)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value137);
								}
							}
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
				}
			}
			scratch.cv$var11$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample11) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var11$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = scratch.cv$var11$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var11$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					state.distribution$sample11[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					state.distribution$sample11[cv$indexName] = Math.exp((scratch.cv$var11$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var11$stateProbabilityGlobal.length; cv$indexName += 1)
				state.distribution$sample11[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void inferSample27(int i) {
		state.constrainedFlag$sample27[i] = false;
		int cv$numStates = state.weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			int $var1308 = state.weightings.length;
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var1308) && (0 < $var1308)) && (0.0 <= state.weightings[cv$valuePos])) && (state.weightings[cv$valuePos] <= 1.0))?Math.log(state.weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			{
				int j = (i + 1);
				if((j < state.size))
					scratch.guard$sample27bernoulli69$global[j] = false;
			}
			scratch.guard$sample27bernoulli69$global[i] = false;
			int j = (i + 1);
			if(((j < state.size) && !scratch.guard$sample27bernoulli69$global[j])) {
				scratch.guard$sample27bernoulli69$global[j] = true;
				state.constrainedFlag$sample27[i] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if(state.fixedFlag$sample5) {
					if((i == j)) {
						double var68 = ((double)((state.v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
						cv$accumulatedConsumerProbabilities = (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY);
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						for(int index$sample27$121 = 0; index$sample27$121 < state.weightings.length; index$sample27$121 += 1) {
							double cv$probabilitySample27Value122 = state.distribution$sample27[j][index$sample27$121];
							double var68 = ((double)((state.v1 + cv$valuePos) + index$sample27$121) / index$sample27$121);
							if(((Math.log(cv$probabilitySample27Value122) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value122) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value122) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value122) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value122)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value122);
						}
					}
				} else {
					for(int index$sample5$115 = 0; index$sample5$115 < state.weightings.length; index$sample5$115 += 1) {
						double cv$probabilitySample5Value116 = state.distribution$sample5[index$sample5$115];
						if((i == j)) {
							double var68 = ((double)((index$sample5$115 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample5Value116) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value116) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value116) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value116) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value116)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value116);
						} else {
							for(int index$sample27$127 = 0; index$sample27$127 < state.weightings.length; index$sample27$127 += 1) {
								double cv$probabilitySample27Value128 = (cv$probabilitySample5Value116 * state.distribution$sample27[j][index$sample27$127]);
								double var68 = ((double)((index$sample5$115 + cv$valuePos) + index$sample27$127) / index$sample27$127);
								if(((Math.log(cv$probabilitySample27Value128) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value128) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value128) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value128) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value128)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value128);
							}
						}
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
			}
			if(!scratch.guard$sample27bernoulli69$global[i]) {
				scratch.guard$sample27bernoulli69$global[i] = true;
				state.constrainedFlag$sample27[i] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((0 == i)) {
					if(state.fixedFlag$sample5) {
						if(state.fixedFlag$sample11) {
							double var68 = ((double)((state.v1 + state.v2[0]) + cv$valuePos) / cv$valuePos);
							cv$accumulatedConsumerProbabilities = (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							for(int index$sample11$190 = 0; index$sample11$190 < state.weightings.length; index$sample11$190 += 1) {
								double cv$probabilitySample11Value191 = state.distribution$sample11[index$sample11$190];
								double var68 = ((double)((state.v1 + index$sample11$190) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample11Value191) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value191) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value191) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value191) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample11Value191)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value191);
							}
						}
					} else {
						for(int index$sample5$185 = 0; index$sample5$185 < state.weightings.length; index$sample5$185 += 1) {
							double cv$probabilitySample5Value186 = state.distribution$sample5[index$sample5$185];
							if(state.fixedFlag$sample11) {
								double var68 = ((double)((index$sample5$185 + state.v2[0]) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample5Value186) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value186) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value186) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value186) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value186)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value186);
							} else {
								for(int index$sample11$195 = 0; index$sample11$195 < state.weightings.length; index$sample11$195 += 1) {
									double cv$probabilitySample11Value196 = (cv$probabilitySample5Value186 * state.distribution$sample11[index$sample11$195]);
									double var68 = ((double)((index$sample5$185 + index$sample11$195) + cv$valuePos) / cv$valuePos);
									if(((Math.log(cv$probabilitySample11Value196) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value196) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value196) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value196) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample11Value196)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value196);
								}
							}
						}
					}
				}
				if(state.fixedFlag$sample5) {
					int index$i$266 = (i - 1);
					if(((0 <= index$i$266) && !(index$i$266 == i))) {
						for(int index$sample27$267 = 0; index$sample27$267 < state.weightings.length; index$sample27$267 += 1) {
							double cv$probabilitySample27Value268 = state.distribution$sample27[index$i$266][index$sample27$267];
							double var68 = ((double)((state.v1 + index$sample27$267) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample27Value268) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value268) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value268) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value268) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value268)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value268);
						}
					}
				} else {
					for(int index$sample5$261 = 0; index$sample5$261 < state.weightings.length; index$sample5$261 += 1) {
						double cv$probabilitySample5Value262 = state.distribution$sample5[index$sample5$261];
						int index$i$272 = (i - 1);
						if(((0 <= index$i$272) && !(index$i$272 == i))) {
							for(int index$sample27$273 = 0; index$sample27$273 < state.weightings.length; index$sample27$273 += 1) {
								double cv$probabilitySample27Value274 = (cv$probabilitySample5Value262 * state.distribution$sample27[index$i$272][index$sample27$273]);
								double var68 = ((double)((index$sample5$261 + index$sample27$273) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample27Value274) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value274) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value274) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value274) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value274)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value274);
							}
						}
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
			}
			if(!scratch.guard$sample27bernoulli69$global[i]) {
				scratch.guard$sample27bernoulli69$global[i] = true;
				state.constrainedFlag$sample27[i] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((0 == i)) {
					if(state.fixedFlag$sample5) {
						if(state.fixedFlag$sample11) {
							double var68 = ((double)((state.v1 + state.v2[0]) + cv$valuePos) / cv$valuePos);
							cv$accumulatedConsumerProbabilities = (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							for(int index$sample11$373 = 0; index$sample11$373 < state.weightings.length; index$sample11$373 += 1) {
								double cv$probabilitySample11Value374 = state.distribution$sample11[index$sample11$373];
								double var68 = ((double)((state.v1 + index$sample11$373) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample11Value374) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value374) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value374) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value374) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample11Value374)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value374);
							}
						}
					} else {
						for(int index$sample5$368 = 0; index$sample5$368 < state.weightings.length; index$sample5$368 += 1) {
							double cv$probabilitySample5Value369 = state.distribution$sample5[index$sample5$368];
							if(state.fixedFlag$sample11) {
								double var68 = ((double)((index$sample5$368 + state.v2[0]) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample5Value369) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value369) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value369) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value369) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value369)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value369);
							} else {
								for(int index$sample11$378 = 0; index$sample11$378 < state.weightings.length; index$sample11$378 += 1) {
									double cv$probabilitySample11Value379 = (cv$probabilitySample5Value369 * state.distribution$sample11[index$sample11$378]);
									double var68 = ((double)((index$sample5$368 + index$sample11$378) + cv$valuePos) / cv$valuePos);
									if(((Math.log(cv$probabilitySample11Value379) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value379) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value379) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value379) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample11Value379)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value379);
								}
							}
						}
					}
				}
				if(state.fixedFlag$sample5) {
					int index$i$412 = (i - 1);
					if(((0 <= index$i$412) && !(index$i$412 == i))) {
						for(int index$sample27$413 = 0; index$sample27$413 < state.weightings.length; index$sample27$413 += 1) {
							double cv$probabilitySample27Value414 = state.distribution$sample27[index$i$412][index$sample27$413];
							double var68 = ((double)((state.v1 + index$sample27$413) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample27Value414) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value414) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value414) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value414) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value414)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value414);
						}
					}
				} else {
					for(int index$sample5$407 = 0; index$sample5$407 < state.weightings.length; index$sample5$407 += 1) {
						double cv$probabilitySample5Value408 = state.distribution$sample5[index$sample5$407];
						int index$i$418 = (i - 1);
						if(((0 <= index$i$418) && !(index$i$418 == i))) {
							for(int index$sample27$419 = 0; index$sample27$419 < state.weightings.length; index$sample27$419 += 1) {
								double cv$probabilitySample27Value420 = (cv$probabilitySample5Value408 * state.distribution$sample27[index$i$418][index$sample27$419]);
								double var68 = ((double)((index$sample5$407 + index$sample27$419) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample27Value420) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value420) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value420) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value420) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value420)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[i]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value420);
							}
						}
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
			}
			scratch.cv$var27$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample27[i]) {
			double[] cv$localProbability = state.distribution$sample27[i];
			double cv$logSum;
			double cv$lseMax = scratch.cv$var27$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = scratch.cv$var27$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var27$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((scratch.cv$var27$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var27$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void inferSample5() {
		state.constrainedFlag$sample5 = false;
		int cv$numStates = state.weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			int $var166 = state.weightings.length;
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var166) && (0 < $var166)) && (0.0 <= state.weightings[cv$valuePos])) && (state.weightings[cv$valuePos] <= 1.0))?Math.log(state.weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			for(int j = 0; j < state.size; j += 1) {
				state.constrainedFlag$sample5 = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((0 == j)) {
					if(state.fixedFlag$sample11) {
						if(state.fixedFlag$sample27) {
							double var68 = ((double)((cv$valuePos + state.v2[0]) + state.v2[1]) / state.v2[1]);
							cv$accumulatedConsumerProbabilities = (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						} else {
							for(int index$sample27$147 = 0; index$sample27$147 < state.weightings.length; index$sample27$147 += 1) {
								double cv$probabilitySample27Value148 = state.distribution$sample27[0][index$sample27$147];
								double var68 = ((double)((cv$valuePos + state.v2[0]) + index$sample27$147) / index$sample27$147);
								if(((Math.log(cv$probabilitySample27Value148) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value148) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value148) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value148) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value148)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value148);
							}
						}
					} else {
						for(int index$sample11$141 = 0; index$sample11$141 < state.weightings.length; index$sample11$141 += 1) {
							double cv$probabilitySample11Value142 = state.distribution$sample11[index$sample11$141];
							if(state.fixedFlag$sample27) {
								double var68 = ((double)((cv$valuePos + index$sample11$141) + state.v2[1]) / state.v2[1]);
								if(((Math.log(cv$probabilitySample11Value142) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value142) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value142) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value142) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample11Value142)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value142);
							} else {
								for(int index$sample27$153 = 0; index$sample27$153 < state.weightings.length; index$sample27$153 += 1) {
									double cv$probabilitySample27Value154 = (cv$probabilitySample11Value142 * state.distribution$sample27[0][index$sample27$153]);
									double var68 = ((double)((cv$valuePos + index$sample11$141) + index$sample27$153) / index$sample27$153);
									if(((Math.log(cv$probabilitySample27Value154) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value154) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value154) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value154) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value154)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[0]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value154);
								}
							}
						}
					}
				}
				if(state.fixedFlag$sample27) {
					if((1 <= j)) {
						for(int index$i$207_1 = 0; index$i$207_1 < state.size; index$i$207_1 += 1) {
							int k = (index$i$207_1 + 1);
							if((k == (j + 1))) {
								double var68 = ((double)((cv$valuePos + state.v2[j]) + state.v2[k]) / state.v2[(j + 1)]);
								if(((((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY);
									else
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY))) + 1)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
							}
						}
					}
				} else {
					int i = (j - 1);
					if((0 <= i)) {
						for(int index$sample27$203 = 0; index$sample27$203 < state.weightings.length; index$sample27$203 += 1) {
							double cv$probabilitySample27Value204 = state.distribution$sample27[i][index$sample27$203];
							if((i == j)) {
								double var68 = ((double)((cv$valuePos + index$sample27$203) + index$sample27$203) / index$sample27$203);
								if(((Math.log(cv$probabilitySample27Value204) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value204) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value204) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value204) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value204)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value204);
							} else {
								for(int index$sample27$210 = 0; index$sample27$210 < state.weightings.length; index$sample27$210 += 1) {
									double cv$probabilitySample27Value211 = (cv$probabilitySample27Value204 * state.distribution$sample27[j][index$sample27$210]);
									double var68 = ((double)((cv$valuePos + index$sample27$203) + index$sample27$210) / index$sample27$210);
									if(((Math.log(cv$probabilitySample27Value211) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value211) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value211) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value211) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value211)) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value211);
								}
							}
						}
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
			}
			scratch.cv$var5$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample5) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var5$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = scratch.cv$var5$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var5$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					state.distribution$sample5[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					state.distribution$sample5[cv$indexName] = Math.exp((scratch.cv$var5$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var5$stateProbabilityGlobal.length; cv$indexName += 1)
				state.distribution$sample5[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void logProbabilityDistribution$sample11() {
		if(!state.fixedProbFlag$sample11) {
			if(state.fixedFlag$sample11) {
				int cv$sampleValue = state.v2[0];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[cv$sampleValue])) && (state.weightings[cv$sampleValue] <= 1.0))?Math.log(state.weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				state.logProbability$var11 = cv$distributionAccumulator;
				state.logProbability$v2 = (state.logProbability$v2 + cv$distributionAccumulator);
				if((state.fixedFlag$sample27 && (0 <= state.size)))
					state.logProbability$v3 = (state.logProbability$v3 + cv$distributionAccumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
				state.fixedProbFlag$sample11 = true;
			}
		} else {
			if(state.fixedFlag$sample11) {
				state.logProbability$v2 = (state.logProbability$v2 + state.logProbability$var11);
				if((state.fixedFlag$sample27 && (0 <= state.size)))
					state.logProbability$v3 = (state.logProbability$v3 + state.logProbability$var11);
			}
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var11);
			if(state.fixedFlag$sample11)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var11);
		}
	}

	private final void logProbabilityDistribution$sample27() {
		if(!state.fixedProbFlag$sample27) {
			if(state.fixedFlag$sample27) {
				double cv$accumulator = 0.0;
				for(int i = 0; i < state.size; i += 1) {
					int cv$sampleValue = state.v2[(i + 1)];
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[cv$sampleValue])) && (state.weightings[cv$sampleValue] <= 1.0))?Math.log(state.weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					state.logProbability$sample27[i] = cv$distributionAccumulator;
					if(state.fixedFlag$sample11)
						state.logProbability$v3 = (state.logProbability$v3 + cv$distributionAccumulator);
				}
				state.logProbability$v2 = (state.logProbability$v2 + cv$accumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample27 = true;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.size; i += 1) {
				double cv$sampleValue = state.logProbability$sample27[i];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				if((state.fixedFlag$sample11 && state.fixedFlag$sample27))
					state.logProbability$v3 = (state.logProbability$v3 + cv$sampleValue);
			}
			if(state.fixedFlag$sample27)
				state.logProbability$v2 = (state.logProbability$v2 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample27)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample5() {
		if(!state.fixedProbFlag$sample5) {
			if(state.fixedFlag$sample5) {
				double cv$distributionAccumulator = ((((((0.0 <= state.v1) && (state.v1 < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[state.v1])) && (state.weightings[state.v1] <= 1.0))?Math.log(state.weightings[state.v1]):Double.NEGATIVE_INFINITY);
				state.logProbability$v1 = cv$distributionAccumulator;
				state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
				state.fixedProbFlag$sample5 = true;
			}
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$v1);
			if(state.fixedFlag$sample5)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$v1);
		}
	}

	private final void logProbabilityDistribution$sample70() {
		if(!state.fixedProbFlag$sample70) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.size; j += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				boolean cv$sampleValue = state.v[j];
				if((0 == j)) {
					if(state.fixedFlag$sample5) {
						if(state.fixedFlag$sample11) {
							if(state.fixedFlag$sample27) {
								double var68 = ((double)((state.v1 + state.v2[0]) + state.v2[1]) / state.v2[1]);
								cv$distributionAccumulator = (((0.0 <= var68) && (var68 <= 1.0))?Math.log((cv$sampleValue?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY);
								cv$probabilityReached = 1.0;
							} else {
								for(int index$sample27$318 = 0; index$sample27$318 < state.weightings.length; index$sample27$318 += 1) {
									double cv$probabilitySample27Value319 = state.distribution$sample27[0][index$sample27$318];
									double var68 = ((double)((state.v1 + state.v2[0]) + index$sample27$318) / index$sample27$318);
									double cv$weightedProbability = (Math.log(cv$probabilitySample27Value319) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((cv$sampleValue?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value319);
								}
							}
						} else {
							for(int index$sample11$307 = 0; index$sample11$307 < state.weightings.length; index$sample11$307 += 1) {
								double cv$probabilitySample11Value308 = state.distribution$sample11[index$sample11$307];
								if(state.fixedFlag$sample27) {
									double var68 = ((double)((state.v1 + index$sample11$307) + state.v2[1]) / state.v2[1]);
									double cv$weightedProbability = (Math.log(cv$probabilitySample11Value308) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((cv$sampleValue?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value308);
								} else {
									for(int index$sample27$324 = 0; index$sample27$324 < state.weightings.length; index$sample27$324 += 1) {
										double cv$probabilitySample27Value325 = (cv$probabilitySample11Value308 * state.distribution$sample27[0][index$sample27$324]);
										double var68 = ((double)((state.v1 + index$sample11$307) + index$sample27$324) / index$sample27$324);
										double cv$weightedProbability = (Math.log(cv$probabilitySample27Value325) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((cv$sampleValue?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
										if((cv$weightedProbability < cv$distributionAccumulator))
											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
										else {
											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
												cv$distributionAccumulator = cv$weightedProbability;
											else
												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
										}
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value325);
									}
								}
							}
						}
					} else {
						for(int index$sample5$302 = 0; index$sample5$302 < state.weightings.length; index$sample5$302 += 1) {
							double cv$probabilitySample5Value303 = state.distribution$sample5[index$sample5$302];
							if(state.fixedFlag$sample11) {
								if(state.fixedFlag$sample27) {
									double var68 = ((double)((index$sample5$302 + state.v2[0]) + state.v2[1]) / state.v2[1]);
									double cv$weightedProbability = (Math.log(cv$probabilitySample5Value303) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((cv$sampleValue?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value303);
								} else {
									for(int index$sample27$330 = 0; index$sample27$330 < state.weightings.length; index$sample27$330 += 1) {
										double cv$probabilitySample27Value331 = (cv$probabilitySample5Value303 * state.distribution$sample27[0][index$sample27$330]);
										double var68 = ((double)((index$sample5$302 + state.v2[0]) + index$sample27$330) / index$sample27$330);
										double cv$weightedProbability = (Math.log(cv$probabilitySample27Value331) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((cv$sampleValue?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
										if((cv$weightedProbability < cv$distributionAccumulator))
											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
										else {
											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
												cv$distributionAccumulator = cv$weightedProbability;
											else
												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
										}
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value331);
									}
								}
							} else {
								for(int index$sample11$312 = 0; index$sample11$312 < state.weightings.length; index$sample11$312 += 1) {
									double cv$probabilitySample11Value313 = (cv$probabilitySample5Value303 * state.distribution$sample11[index$sample11$312]);
									if(state.fixedFlag$sample27) {
										double var68 = ((double)((index$sample5$302 + index$sample11$312) + state.v2[1]) / state.v2[1]);
										double cv$weightedProbability = (Math.log(cv$probabilitySample11Value313) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((cv$sampleValue?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
										if((cv$weightedProbability < cv$distributionAccumulator))
											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
										else {
											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
												cv$distributionAccumulator = cv$weightedProbability;
											else
												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
										}
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value313);
									} else {
										for(int index$sample27$336 = 0; index$sample27$336 < state.weightings.length; index$sample27$336 += 1) {
											double cv$probabilitySample27Value337 = (cv$probabilitySample11Value313 * state.distribution$sample27[0][index$sample27$336]);
											double var68 = ((double)((index$sample5$302 + index$sample11$312) + index$sample27$336) / index$sample27$336);
											double cv$weightedProbability = (Math.log(cv$probabilitySample27Value337) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((cv$sampleValue?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
											if((cv$weightedProbability < cv$distributionAccumulator))
												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
											else {
												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
													cv$distributionAccumulator = cv$weightedProbability;
												else
													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
											}
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value337);
										}
									}
								}
							}
						}
					}
				}
				if(state.fixedFlag$sample5) {
					if(state.fixedFlag$sample27) {
						if((1 <= j)) {
							for(int index$i$450_1 = 0; index$i$450_1 < state.size; index$i$450_1 += 1) {
								int k = (index$i$450_1 + 1);
								if((k == (j + 1))) {
									double var68 = ((double)((state.v1 + state.v2[j]) + state.v2[k]) / state.v2[(j + 1)]);
									double cv$weightedProbability = (((0.0 <= var68) && (var68 <= 1.0))?Math.log((cv$sampleValue?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY);
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
							}
						}
					} else {
						int i = (j - 1);
						if((0 <= i)) {
							for(int index$sample27$440 = 0; index$sample27$440 < state.weightings.length; index$sample27$440 += 1) {
								double cv$probabilitySample27Value441 = state.distribution$sample27[i][index$sample27$440];
								if((i == j)) {
									double var68 = ((double)((state.v1 + index$sample27$440) + index$sample27$440) / index$sample27$440);
									double cv$weightedProbability = (Math.log(cv$probabilitySample27Value441) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((cv$sampleValue?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value441);
								} else {
									for(int index$sample27$453 = 0; index$sample27$453 < state.weightings.length; index$sample27$453 += 1) {
										double cv$probabilitySample27Value454 = (cv$probabilitySample27Value441 * state.distribution$sample27[j][index$sample27$453]);
										double var68 = ((double)((state.v1 + index$sample27$440) + index$sample27$453) / index$sample27$453);
										double cv$weightedProbability = (Math.log(cv$probabilitySample27Value454) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((cv$sampleValue?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
										if((cv$weightedProbability < cv$distributionAccumulator))
											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
										else {
											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
												cv$distributionAccumulator = cv$weightedProbability;
											else
												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
										}
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value454);
									}
								}
							}
						}
					}
				} else {
					for(int index$sample5$434 = 0; index$sample5$434 < state.weightings.length; index$sample5$434 += 1) {
						double cv$probabilitySample5Value435 = state.distribution$sample5[index$sample5$434];
						if(state.fixedFlag$sample27) {
							if((1 <= j)) {
								for(int index$i$457_1 = 0; index$i$457_1 < state.size; index$i$457_1 += 1) {
									int k = (index$i$457_1 + 1);
									if((k == (j + 1))) {
										double var68 = ((double)((index$sample5$434 + state.v2[j]) + state.v2[k]) / state.v2[(j + 1)]);
										double cv$weightedProbability = (Math.log(cv$probabilitySample5Value435) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((cv$sampleValue?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
										if((cv$weightedProbability < cv$distributionAccumulator))
											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
										else {
											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
												cv$distributionAccumulator = cv$weightedProbability;
											else
												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
										}
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value435);
									}
								}
							}
						} else {
							int i = (j - 1);
							if((0 <= i)) {
								for(int index$sample27$446 = 0; index$sample27$446 < state.weightings.length; index$sample27$446 += 1) {
									double cv$probabilitySample27Value447 = (cv$probabilitySample5Value435 * state.distribution$sample27[i][index$sample27$446]);
									if((i == j)) {
										double var68 = ((double)((index$sample5$434 + index$sample27$446) + index$sample27$446) / index$sample27$446);
										double cv$weightedProbability = (Math.log(cv$probabilitySample27Value447) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((cv$sampleValue?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
										if((cv$weightedProbability < cv$distributionAccumulator))
											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
										else {
											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
												cv$distributionAccumulator = cv$weightedProbability;
											else
												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
										}
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value447);
									} else {
										for(int index$sample27$460 = 0; index$sample27$460 < state.weightings.length; index$sample27$460 += 1) {
											double cv$probabilitySample27Value461 = (cv$probabilitySample27Value447 * state.distribution$sample27[j][index$sample27$460]);
											double var68 = ((double)((index$sample5$434 + index$sample27$446) + index$sample27$460) / index$sample27$460);
											double cv$weightedProbability = (Math.log(cv$probabilitySample27Value461) + (((0.0 <= var68) && (var68 <= 1.0))?Math.log((cv$sampleValue?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY));
											if((cv$weightedProbability < cv$distributionAccumulator))
												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
											else {
												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
													cv$distributionAccumulator = cv$weightedProbability;
												else
													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
											}
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value461);
										}
									}
								}
							}
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample70[j] = cv$distributionAccumulator;
			}
			state.logProbability$v = (state.logProbability$v + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample70 = ((state.fixedFlag$sample5 && state.fixedFlag$sample11) && state.fixedFlag$sample27);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.size; j += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample70[j]);
			state.logProbability$v = (state.logProbability$v + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample11() {
		if(!state.fixedProbFlag$sample11) {
			int cv$sampleValue = state.v2[0];
			double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[cv$sampleValue])) && (state.weightings[cv$sampleValue] <= 1.0))?Math.log(state.weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			state.logProbability$var11 = cv$distributionAccumulator;
			state.logProbability$v2 = (state.logProbability$v2 + cv$distributionAccumulator);
			if((0 <= state.size))
				state.logProbability$v3 = (state.logProbability$v3 + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample11)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample11 = state.fixedFlag$sample11;
		} else {
			state.logProbability$v2 = (state.logProbability$v2 + state.logProbability$var11);
			if((0 <= state.size))
				state.logProbability$v3 = (state.logProbability$v3 + state.logProbability$var11);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var11);
			if(state.fixedFlag$sample11)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var11);
		}
	}

	private final void logProbabilityValue$sample27() {
		if(!state.fixedProbFlag$sample27) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.size; i += 1) {
				int cv$sampleValue = state.v2[(i + 1)];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[cv$sampleValue])) && (state.weightings[cv$sampleValue] <= 1.0))?Math.log(state.weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample27[i] = cv$distributionAccumulator;
				state.logProbability$v3 = (state.logProbability$v3 + cv$distributionAccumulator);
			}
			state.logProbability$v2 = (state.logProbability$v2 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample27)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample27 = state.fixedFlag$sample27;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.size; i += 1) {
				double cv$sampleValue = state.logProbability$sample27[i];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				state.logProbability$v3 = (state.logProbability$v3 + cv$sampleValue);
			}
			state.logProbability$v2 = (state.logProbability$v2 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample27)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample5() {
		if(!state.fixedProbFlag$sample5) {
			double cv$distributionAccumulator = ((((((0.0 <= state.v1) && (state.v1 < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[state.v1])) && (state.weightings[state.v1] <= 1.0))?Math.log(state.weightings[state.v1]):Double.NEGATIVE_INFINITY);
			state.logProbability$v1 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample5)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample5 = state.fixedFlag$sample5;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$v1);
			if(state.fixedFlag$sample5)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$v1);
		}
	}

	private final void logProbabilityValue$sample70() {
		if(!state.fixedProbFlag$sample70) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.size; j += 1) {
				double var68 = ((double)((state.v1 + state.v2[j]) + state.v3[(j + 1)]) / state.v2[(j + 1)]);
				double cv$distributionAccumulator = (((0.0 <= var68) && (var68 <= 1.0))?Math.log((state.v[j]?var68:(1.0 - var68))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample70[j] = cv$distributionAccumulator;
			}
			state.logProbability$v = (state.logProbability$v + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample70 = ((state.fixedFlag$sample5 && state.fixedFlag$sample11) && state.fixedFlag$sample27);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.size; j += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample70[j]);
			state.logProbability$v = (state.logProbability$v + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample5)
			state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample11)
			state.v2[0] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample27) {
			for(int i = 0; i < state.size; i += 1)
				state.v2[(i + 1)] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		}
		if((!state.fixedFlag$sample11 || !state.fixedFlag$sample27)) {
			for(int k = 0; k <= state.size; k += 1)
				state.v3[k] = state.v2[k];
		}
		for(int j = 0; j < state.size; j += 1)
			state.v[j] = DistributionSampling.sampleBernoulli(state.RNG$, ((double)((state.v1 + state.v2[j]) + state.v3[(j + 1)]) / state.v2[(j + 1)]));
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample5) {
			for(int index$var4 = 0; index$var4 < state.weightings.length; index$var4 += 1)
				state.distribution$sample5[index$var4] = ((((0 < state.weightings.length) && (0.0 <= state.weightings[index$var4])) && (state.weightings[index$var4] <= 1.0))?state.weightings[index$var4]:0.0);
		}
		if(!state.fixedFlag$sample11) {
			for(int index$var10 = 0; index$var10 < state.weightings.length; index$var10 += 1)
				state.distribution$sample11[index$var10] = ((((0 < state.weightings.length) && (0.0 <= state.weightings[index$var10])) && (state.weightings[index$var10] <= 1.0))?state.weightings[index$var10]:0.0);
		}
		if(!state.fixedFlag$sample27) {
			for(int i = 0; i < state.size; i += 1) {
				double[] cv$distribution$sample27 = state.distribution$sample27[i];
				for(int index$var26 = 0; index$var26 < state.weightings.length; index$var26 += 1)
					cv$distribution$sample27[index$var26] = ((((0 < state.weightings.length) && (0.0 <= state.weightings[index$var26])) && (state.weightings[index$var26] <= 1.0))?state.weightings[index$var26]:0.0);
			}
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample5)
			state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample11)
			state.v2[0] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample27) {
			for(int i = 0; i < state.size; i += 1)
				state.v2[(i + 1)] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		}
		for(int k = 0; k <= state.size; k += 1)
			state.v3[k] = state.v2[k];
		for(int j = 0; j < state.size; j += 1)
			state.v[j] = DistributionSampling.sampleBernoulli(state.RNG$, ((double)((state.v1 + state.v2[j]) + state.v3[(j + 1)]) / state.v2[(j + 1)]));
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample5)
			state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample11)
			state.v2[0] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample27) {
			for(int i = 0; i < state.size; i += 1)
				state.v2[(i + 1)] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		}
		if((!state.fixedFlag$sample11 || !state.fixedFlag$sample27)) {
			for(int k = 0; k <= state.size; k += 1)
				state.v3[k] = state.v2[k];
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample5)
			state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample11)
			state.v2[0] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample27) {
			for(int i = 0; i < state.size; i += 1)
				state.v2[(i + 1)] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		}
		for(int k = 0; k <= state.size; k += 1)
			state.v3[k] = state.v2[k];
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample5)
				inferSample5();
			if(!state.fixedFlag$sample11)
				inferSample11();
			if(!state.fixedFlag$sample27) {
				for(int i = 0; i < state.size; i += 1)
					inferSample27(i);
			}
		} else {
			if(!state.fixedFlag$sample27) {
				for(int i = (state.size - 1); i >= 0; i -= 1)
					inferSample27(i);
			}
			if(!state.fixedFlag$sample11)
				inferSample11();
			if(!state.fixedFlag$sample5)
				inferSample5();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample5)
			drawValueSample5();
		if(!state.constrainedFlag$sample11)
			drawValueSample11();
		for(int i = 0; i < state.size; i += 1) {
			if(!state.constrainedFlag$sample27[i])
				drawValueSample27(i);
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample5)
			state.logProbability$v1 = Double.NaN;
		state.logProbability$v2 = 0.0;
		state.logProbability$v3 = 0.0;
		if(!state.fixedProbFlag$sample11)
			state.logProbability$var11 = Double.NaN;
		if(!state.fixedProbFlag$sample27) {
			for(int i = 0; i < state.size; i += 1)
				state.logProbability$sample27[i] = Double.NaN;
		}
		state.logProbability$v = 0.0;
		if(!state.fixedProbFlag$sample70) {
			for(int j = 0; j < state.size; j += 1)
				state.logProbability$sample70[j] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.size = state.length$value;
		for(int index$constrainedFlag$sample27$1 = 0; index$constrainedFlag$sample27$1 < state.constrainedFlag$sample27.length; index$constrainedFlag$sample27$1 += 1)
			state.constrainedFlag$sample27[index$constrainedFlag$sample27$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample70();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample5();
		logProbabilityDistribution$sample11();
		logProbabilityDistribution$sample27();
		logProbabilityDistribution$sample70();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample11();
		logProbabilityValue$sample27();
		logProbabilityValue$sample70();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.v.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.v[cv$index1] = state.value[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		for(int k = 0; k <= state.size; k += 1)
			state.v3[k] = state.v2[k];
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
		     + "model DistributionTest5(double[] weightings, boolean[] value) {\n"
		     + "    int size = value.length;\n"
		     + "    \n"
		     + "    int v1 = categorical(weightings).sampleDistribution();\n"
		     + "    \n"
		     + "    int[] v2 = new int[size + 1];\n"
		     + "    v2[0] = categorical(weightings).sampleDistribution();\n"
		     + "    for(int i:[0..size))\n"
		     + "        v2[i + 1] = categorical(weightings).sampleDistribution();\n"
		     + "        \n"
		     + "        \n"
		     + "    int[] v3 = new int[size + 1];\n"
		     + "    for(int k:[0..size]) \n"
		     + "        v3[k] = v2[k];\n"
		     + "        \n"
		     + "    boolean[] v = new boolean[size];\n"
		     + "    for(int j:[0..size))\n"
		     + "        v[j] = bernoulli(((1.0*v1) + v2[j] + v3[j+1])/v2[j+1]).sample();\n"
		     + "        \n"
		     + "    v.observe(value);\n"
		     + "}\n"
		     + "";
	}
}