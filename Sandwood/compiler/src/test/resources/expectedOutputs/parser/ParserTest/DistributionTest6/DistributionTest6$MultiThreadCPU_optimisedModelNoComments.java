package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.DistributionTest6$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.DistributionTest6.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DistributionTest6$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var11$stateProbabilityGlobal;
		double[][] cv$var27$stateProbabilityGlobal;
		double[] cv$var5$stateProbabilityGlobal;
		boolean[] guard$sample11bernoulli48$global;
		boolean[][] guard$sample27bernoulli48$global;

		@Override
		public final void allocateScratch() {
			cv$var5$stateProbabilityGlobal = new double[state.weightings.length];
			cv$var11$stateProbabilityGlobal = new double[state.weightings.length];
			guard$sample11bernoulli48$global = new boolean[state.length$value];
			{
				int cv$threadCount = threadCount();
				cv$var27$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var27$stateProbabilityGlobal[cv$index] = new double[state.weightings.length];
			}
			int cv$threadCount = threadCount();
			guard$sample27bernoulli48$global = new boolean[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				guard$sample27bernoulli48$global[cv$index] = new boolean[state.length$value];
		}
	}


	public DistributionTest6$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample11() {
		state.v2[0] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
	}

	private final void drawValueSample27(int i, int threadID$cv$i, Rng RNG$) {
		state.v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$, state.weightings, state.weightings.length);
	}

	private final void drawValueSample5() {
		state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
	}

	private final void inferSample11() {
		state.constrainedFlag$sample11 = false;
		int cv$numStates = state.weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			int $var2938 = state.weightings.length;
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var2938) && (0 < $var2938)) && (0.0 <= state.weightings[cv$valuePos])) && (state.weightings[cv$valuePos] <= 1.0))?Math.log(state.weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((0 < state.size)) {
				scratch.guard$sample11bernoulli48$global[0] = false;
				if(!scratch.guard$sample11bernoulli48$global[0]) {
					scratch.guard$sample11bernoulli48$global[0] = true;
					state.constrainedFlag$sample11 = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(state.fixedFlag$sample5) {
						double var47 = ((double)((state.v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
						cv$accumulatedConsumerProbabilities = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						for(int index$sample5$11 = 0; index$sample5$11 < state.weightings.length; index$sample5$11 += 1) {
							double cv$probabilitySample5Value12 = state.distribution$sample5[index$sample5$11];
							double var47 = ((double)((index$sample5$11 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample5Value12) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value12) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value12) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value12) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value12)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value12);
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
				if(!scratch.guard$sample11bernoulli48$global[0]) {
					scratch.guard$sample11bernoulli48$global[0] = true;
					state.constrainedFlag$sample11 = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(state.fixedFlag$sample5) {
						double var47 = ((double)((state.v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
						cv$accumulatedConsumerProbabilities = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						for(int index$sample5$155 = 0; index$sample5$155 < state.weightings.length; index$sample5$155 += 1) {
							double cv$probabilitySample5Value156 = state.distribution$sample5[index$sample5$155];
							double var47 = ((double)((index$sample5$155 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample5Value156) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value156) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value156) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value156) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value156)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value156);
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
				if(!scratch.guard$sample11bernoulli48$global[0]) {
					scratch.guard$sample11bernoulli48$global[0] = true;
					state.constrainedFlag$sample11 = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(state.fixedFlag$sample5) {
						double var47 = ((double)((state.v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
						cv$accumulatedConsumerProbabilities = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						for(int index$sample5$299 = 0; index$sample5$299 < state.weightings.length; index$sample5$299 += 1) {
							double cv$probabilitySample5Value300 = state.distribution$sample5[index$sample5$299];
							double var47 = ((double)((index$sample5$299 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample5Value300) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value300) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value300) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value300) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value300)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value300);
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

	private final void inferSample27(int i, int threadID$cv$i, Rng RNG$) {
		state.constrainedFlag$sample27[i] = false;
		int cv$numStates = state.weightings.length;
		double[] cv$stateProbabilityLocal = scratch.cv$var27$stateProbabilityGlobal[threadID$cv$i];
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			int $var3595 = state.weightings.length;
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var3595) && (0 < $var3595)) && (0.0 <= state.weightings[cv$valuePos])) && (state.weightings[cv$valuePos] <= 1.0))?Math.log(state.weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			boolean[] guard$sample27bernoulli48 = scratch.guard$sample27bernoulli48$global[threadID$cv$i];
			{
				int j = (i + 1);
				if((j < state.size))
					guard$sample27bernoulli48[j] = false;
			}
			{
				int j = (i + 1);
				if((j < state.size))
					guard$sample27bernoulli48[j] = false;
			}
			{
				int j = (i + 1);
				if((j < state.size))
					guard$sample27bernoulli48[j] = false;
			}
			{
				int j = (i + 1);
				if(((j < state.size) && !guard$sample27bernoulli48[j])) {
					guard$sample27bernoulli48[j] = true;
					state.constrainedFlag$sample27[i] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(state.fixedFlag$sample5) {
						{
							double var47 = ((double)((state.v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							cv$accumulatedConsumerProbabilities = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						int index$i$132 = (j - 1);
						if(!(index$i$132 == i)) {
							for(int index$sample27$133 = 0; index$sample27$133 < state.weightings.length; index$sample27$133 += 1) {
								double cv$probabilitySample27Value134 = state.distribution$sample27[index$i$132][index$sample27$133];
								double var47 = ((double)((state.v1 + cv$valuePos) + cv$valuePos) / index$sample27$133);
								if(((Math.log(cv$probabilitySample27Value134) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value134) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value134) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value134) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value134)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value134);
							}
						}
						int index$i$120 = (j - 1);
						if(!(index$i$120 == i)) {
							for(int index$sample27$121 = 0; index$sample27$121 < state.weightings.length; index$sample27$121 += 1) {
								double cv$probabilitySample27Value122 = state.distribution$sample27[index$i$120][index$sample27$121];
								{
									double var47 = ((double)((state.v1 + cv$valuePos) + index$sample27$121) / cv$valuePos);
									if(((Math.log(cv$probabilitySample27Value122) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value122) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value122) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value122) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value122)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value122);
								}
								double var47 = ((double)((state.v1 + cv$valuePos) + index$sample27$121) / index$sample27$121);
								if(((Math.log(cv$probabilitySample27Value122) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value122) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value122) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value122) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value122)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value122);
							}
						}
					} else {
						for(int index$sample5$115 = 0; index$sample5$115 < state.weightings.length; index$sample5$115 += 1) {
							double cv$probabilitySample5Value116 = state.distribution$sample5[index$sample5$115];
							{
								double var47 = ((double)((index$sample5$115 + cv$valuePos) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample5Value116) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value116) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value116) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value116) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value116)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value116);
							}
							int index$i$145 = (j - 1);
							if(!(index$i$145 == i)) {
								for(int index$sample27$146 = 0; index$sample27$146 < state.weightings.length; index$sample27$146 += 1) {
									double cv$probabilitySample27Value147 = (cv$probabilitySample5Value116 * state.distribution$sample27[index$i$145][index$sample27$146]);
									double var47 = ((double)((index$sample5$115 + cv$valuePos) + cv$valuePos) / index$sample27$146);
									if(((Math.log(cv$probabilitySample27Value147) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value147) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value147) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value147) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value147)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value147);
								}
							}
							int index$i$126 = (j - 1);
							if(!(index$i$126 == i)) {
								for(int index$sample27$127 = 0; index$sample27$127 < state.weightings.length; index$sample27$127 += 1) {
									double cv$probabilitySample27Value128 = (cv$probabilitySample5Value116 * state.distribution$sample27[index$i$126][index$sample27$127]);
									{
										double var47 = ((double)((index$sample5$115 + cv$valuePos) + index$sample27$127) / cv$valuePos);
										if(((Math.log(cv$probabilitySample27Value128) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value128) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value128) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
											else
												cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value128) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value128)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
										}
										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value128);
									}
									double var47 = ((double)((index$sample5$115 + cv$valuePos) + index$sample27$127) / index$sample27$127);
									if(((Math.log(cv$probabilitySample27Value128) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value128) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value128) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value128) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value128)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
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
			}
			{
				int j = (i + 1);
				if(((j < state.size) && !guard$sample27bernoulli48[j])) {
					guard$sample27bernoulli48[j] = true;
					state.constrainedFlag$sample27[i] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(state.fixedFlag$sample5) {
						{
							double var47 = ((double)((state.v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							cv$accumulatedConsumerProbabilities = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
						}
						int index$i$278 = (j - 1);
						if(!(index$i$278 == i)) {
							for(int index$sample27$279 = 0; index$sample27$279 < state.weightings.length; index$sample27$279 += 1) {
								double cv$probabilitySample27Value280 = state.distribution$sample27[index$i$278][index$sample27$279];
								double var47 = ((double)((state.v1 + cv$valuePos) + cv$valuePos) / index$sample27$279);
								if(((Math.log(cv$probabilitySample27Value280) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value280) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value280) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value280) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value280)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value280);
							}
						}
						int index$i$266 = (j - 1);
						if(!(index$i$266 == i)) {
							for(int index$sample27$267 = 0; index$sample27$267 < state.weightings.length; index$sample27$267 += 1) {
								double cv$probabilitySample27Value268 = state.distribution$sample27[index$i$266][index$sample27$267];
								{
									double var47 = ((double)((state.v1 + index$sample27$267) + cv$valuePos) / cv$valuePos);
									if(((Math.log(cv$probabilitySample27Value268) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value268) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value268) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value268) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value268)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value268);
								}
								double var47 = ((double)((state.v1 + index$sample27$267) + cv$valuePos) / index$sample27$267);
								if(((Math.log(cv$probabilitySample27Value268) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value268) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value268) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value268) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value268)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value268);
							}
						}
					} else {
						for(int index$sample5$261 = 0; index$sample5$261 < state.weightings.length; index$sample5$261 += 1) {
							double cv$probabilitySample5Value262 = state.distribution$sample5[index$sample5$261];
							{
								double var47 = ((double)((index$sample5$261 + cv$valuePos) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample5Value262) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value262) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value262) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value262) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value262)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value262);
							}
							int index$i$291 = (j - 1);
							if(!(index$i$291 == i)) {
								for(int index$sample27$292 = 0; index$sample27$292 < state.weightings.length; index$sample27$292 += 1) {
									double cv$probabilitySample27Value293 = (cv$probabilitySample5Value262 * state.distribution$sample27[index$i$291][index$sample27$292]);
									double var47 = ((double)((index$sample5$261 + cv$valuePos) + cv$valuePos) / index$sample27$292);
									if(((Math.log(cv$probabilitySample27Value293) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value293) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value293) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value293) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value293)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value293);
								}
							}
							int index$i$272 = (j - 1);
							if(!(index$i$272 == i)) {
								for(int index$sample27$273 = 0; index$sample27$273 < state.weightings.length; index$sample27$273 += 1) {
									double cv$probabilitySample27Value274 = (cv$probabilitySample5Value262 * state.distribution$sample27[index$i$272][index$sample27$273]);
									{
										double var47 = ((double)((index$sample5$261 + index$sample27$273) + cv$valuePos) / cv$valuePos);
										if(((Math.log(cv$probabilitySample27Value274) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value274) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value274) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
											else
												cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value274) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value274)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
										}
										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value274);
									}
									double var47 = ((double)((index$sample5$261 + index$sample27$273) + cv$valuePos) / index$sample27$273);
									if(((Math.log(cv$probabilitySample27Value274) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value274) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value274) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value274) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value274)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
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
			}
			int j = (i + 1);
			if(((j < state.size) && !guard$sample27bernoulli48[j])) {
				guard$sample27bernoulli48[j] = true;
				state.constrainedFlag$sample27[i] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if(state.fixedFlag$sample5) {
					{
						double var47 = ((double)((state.v1 + cv$valuePos) + cv$valuePos) / cv$valuePos);
						cv$accumulatedConsumerProbabilities = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					}
					int index$i$424 = (j - 1);
					if(!(index$i$424 == i)) {
						for(int index$sample27$425 = 0; index$sample27$425 < state.weightings.length; index$sample27$425 += 1) {
							double cv$probabilitySample27Value426 = state.distribution$sample27[index$i$424][index$sample27$425];
							double var47 = ((double)((state.v1 + cv$valuePos) + index$sample27$425) / cv$valuePos);
							if(((Math.log(cv$probabilitySample27Value426) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value426) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value426) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value426) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value426)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value426);
						}
					}
					int index$i$412 = (j - 1);
					if(!(index$i$412 == i)) {
						for(int index$sample27$413 = 0; index$sample27$413 < state.weightings.length; index$sample27$413 += 1) {
							double cv$probabilitySample27Value414 = state.distribution$sample27[index$i$412][index$sample27$413];
							{
								double var47 = ((double)((state.v1 + index$sample27$413) + cv$valuePos) / cv$valuePos);
								if(((Math.log(cv$probabilitySample27Value414) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value414) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value414) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value414) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value414)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value414);
							}
							double var47 = ((double)((state.v1 + index$sample27$413) + index$sample27$413) / cv$valuePos);
							if(((Math.log(cv$probabilitySample27Value414) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value414) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value414) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value414) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value414)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value414);
						}
					}
				} else {
					for(int index$sample5$407 = 0; index$sample5$407 < state.weightings.length; index$sample5$407 += 1) {
						double cv$probabilitySample5Value408 = state.distribution$sample5[index$sample5$407];
						{
							double var47 = ((double)((index$sample5$407 + cv$valuePos) + cv$valuePos) / cv$valuePos);
							if(((Math.log(cv$probabilitySample5Value408) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value408) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value408) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value408) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample5Value408)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value408);
						}
						int index$i$437 = (j - 1);
						if(!(index$i$437 == i)) {
							for(int index$sample27$438 = 0; index$sample27$438 < state.weightings.length; index$sample27$438 += 1) {
								double cv$probabilitySample27Value439 = (cv$probabilitySample5Value408 * state.distribution$sample27[index$i$437][index$sample27$438]);
								double var47 = ((double)((index$sample5$407 + cv$valuePos) + index$sample27$438) / cv$valuePos);
								if(((Math.log(cv$probabilitySample27Value439) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value439) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value439) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value439) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value439)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								}
								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value439);
							}
						}
						int index$i$418 = (j - 1);
						if(!(index$i$418 == i)) {
							for(int index$sample27$419 = 0; index$sample27$419 < state.weightings.length; index$sample27$419 += 1) {
								double cv$probabilitySample27Value420 = (cv$probabilitySample5Value408 * state.distribution$sample27[index$i$418][index$sample27$419]);
								{
									double var47 = ((double)((index$sample5$407 + index$sample27$419) + cv$valuePos) / cv$valuePos);
									if(((Math.log(cv$probabilitySample27Value420) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value420) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value420) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value420) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value420)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									}
									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value420);
								}
								double var47 = ((double)((index$sample5$407 + index$sample27$419) + index$sample27$419) / cv$valuePos);
								if(((Math.log(cv$probabilitySample27Value420) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value420) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value420) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value420) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value420)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
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
			cv$stateProbabilityLocal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample27[i]) {
			double[] cv$localProbability = state.distribution$sample27[i];
			double cv$logSum;
			double cv$lseMax = cv$stateProbabilityLocal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void inferSample5() {
		state.constrainedFlag$sample5 = false;
		int cv$numStates = state.weightings.length;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			int $var2608 = state.weightings.length;
			double cv$accumulatedProbabilities = (((((cv$valuePos < $var2608) && (0 < $var2608)) && (0.0 <= state.weightings[cv$valuePos])) && (state.weightings[cv$valuePos] <= 1.0))?Math.log(state.weightings[cv$valuePos]):Double.NEGATIVE_INFINITY);
			for(int j = 0; j < state.size; j += 1) {
				state.constrainedFlag$sample5 = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((0 == j)) {
					if(state.fixedFlag$sample11) {
						double var47 = ((double)((cv$valuePos + state.v2[0]) + state.v2[0]) / state.v2[0]);
						cv$accumulatedConsumerProbabilities = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
						cv$consumerDistributionProbabilityAccumulator = 0.0;
					} else {
						for(int index$sample11$4 = 0; index$sample11$4 < state.weightings.length; index$sample11$4 += 1) {
							double cv$probabilitySample11Value5 = state.distribution$sample11[index$sample11$4];
							double var47 = ((double)((cv$valuePos + index$sample11$4) + index$sample11$4) / index$sample11$4);
							if(((Math.log(cv$probabilitySample11Value5) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample11Value5) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample11Value5) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample11Value5) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample11Value5)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[0]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample11Value5);
						}
					}
				}
				if(state.fixedFlag$sample27) {
					if((1 <= j)) {
						double var47 = ((double)((cv$valuePos + state.v2[j]) + state.v2[j]) / state.v2[j]);
						if(((((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
							else
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY))) + 1)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
						}
						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
					}
				} else {
					int i = (j - 1);
					if((0 <= i)) {
						for(int index$sample27$203 = 0; index$sample27$203 < state.weightings.length; index$sample27$203 += 1) {
							double cv$probabilitySample27Value204 = state.distribution$sample27[i][index$sample27$203];
							double var47 = ((double)((cv$valuePos + index$sample27$203) + index$sample27$203) / index$sample27$203);
							if(((Math.log(cv$probabilitySample27Value204) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample27Value204) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample27Value204) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample27Value204) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY)))) + 1)) + Math.log(cv$probabilitySample27Value204)) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
							}
							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample27Value204);
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
				state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
				state.fixedProbFlag$sample11 = true;
			}
		} else {
			if(state.fixedFlag$sample11)
				state.logProbability$v2 = (state.logProbability$v2 + state.logProbability$var11);
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
				}
				state.logProbability$v2 = (state.logProbability$v2 + cv$accumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample27 = true;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.size; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample27[i]);
			if(state.fixedFlag$sample27)
				state.logProbability$v2 = (state.logProbability$v2 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample27)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample49() {
		if(!state.fixedProbFlag$sample49) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.size; j += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				boolean cv$sampleValue = state.v[j];
				if((0 == j)) {
					if(state.fixedFlag$sample5) {
						if(state.fixedFlag$sample11) {
							double var47 = ((double)((state.v1 + state.v2[0]) + state.v2[0]) / state.v2[0]);
							cv$distributionAccumulator = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((cv$sampleValue?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
							cv$probabilityReached = 1.0;
						} else {
							for(int index$sample11$8 = 0; index$sample11$8 < state.weightings.length; index$sample11$8 += 1) {
								double cv$probabilitySample11Value9 = state.distribution$sample11[index$sample11$8];
								double var47 = ((double)((state.v1 + index$sample11$8) + index$sample11$8) / index$sample11$8);
								double cv$weightedProbability = (Math.log(cv$probabilitySample11Value9) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((cv$sampleValue?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value9);
							}
						}
					} else {
						for(int index$sample5$3 = 0; index$sample5$3 < state.weightings.length; index$sample5$3 += 1) {
							double cv$probabilitySample5Value4 = state.distribution$sample5[index$sample5$3];
							if(state.fixedFlag$sample11) {
								double var47 = ((double)((index$sample5$3 + state.v2[0]) + state.v2[0]) / state.v2[0]);
								double cv$weightedProbability = (Math.log(cv$probabilitySample5Value4) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((cv$sampleValue?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value4);
							} else {
								for(int index$sample11$13 = 0; index$sample11$13 < state.weightings.length; index$sample11$13 += 1) {
									double cv$probabilitySample11Value14 = (cv$probabilitySample5Value4 * state.distribution$sample11[index$sample11$13]);
									double var47 = ((double)((index$sample5$3 + index$sample11$13) + index$sample11$13) / index$sample11$13);
									double cv$weightedProbability = (Math.log(cv$probabilitySample11Value14) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((cv$sampleValue?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample11Value14);
								}
							}
						}
					}
				}
				if(state.fixedFlag$sample5) {
					if(state.fixedFlag$sample27) {
						if((1 <= j)) {
							double var47 = ((double)((state.v1 + state.v2[j]) + state.v2[j]) / state.v2[j]);
							double cv$weightedProbability = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((cv$sampleValue?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
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
						int i = (j - 1);
						if((0 <= i)) {
							for(int index$sample27$440 = 0; index$sample27$440 < state.weightings.length; index$sample27$440 += 1) {
								double cv$probabilitySample27Value441 = state.distribution$sample27[i][index$sample27$440];
								double var47 = ((double)((state.v1 + index$sample27$440) + index$sample27$440) / index$sample27$440);
								double cv$weightedProbability = (Math.log(cv$probabilitySample27Value441) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((cv$sampleValue?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value441);
							}
						}
					}
				} else {
					for(int index$sample5$434 = 0; index$sample5$434 < state.weightings.length; index$sample5$434 += 1) {
						double cv$probabilitySample5Value435 = state.distribution$sample5[index$sample5$434];
						if(state.fixedFlag$sample27) {
							if((1 <= j)) {
								double var47 = ((double)((index$sample5$434 + state.v2[j]) + state.v2[j]) / state.v2[j]);
								double cv$weightedProbability = (Math.log(cv$probabilitySample5Value435) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((cv$sampleValue?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
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
						} else {
							int i = (j - 1);
							if((0 <= i)) {
								for(int index$sample27$446 = 0; index$sample27$446 < state.weightings.length; index$sample27$446 += 1) {
									double cv$probabilitySample27Value447 = (cv$probabilitySample5Value435 * state.distribution$sample27[i][index$sample27$446]);
									double var47 = ((double)((index$sample5$434 + index$sample27$446) + index$sample27$446) / index$sample27$446);
									double cv$weightedProbability = (Math.log(cv$probabilitySample27Value447) + (((0.0 <= var47) && (var47 <= 1.0))?Math.log((cv$sampleValue?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample27Value447);
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
				state.logProbability$sample49[j] = cv$distributionAccumulator;
			}
			state.logProbability$v = (state.logProbability$v + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample49 = ((state.fixedFlag$sample5 && state.fixedFlag$sample11) && state.fixedFlag$sample27);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.size; j += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample49[j]);
			state.logProbability$v = (state.logProbability$v + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
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

	private final void logProbabilityValue$sample11() {
		if(!state.fixedProbFlag$sample11) {
			int cv$sampleValue = state.v2[0];
			double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[cv$sampleValue])) && (state.weightings[cv$sampleValue] <= 1.0))?Math.log(state.weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			state.logProbability$var11 = cv$distributionAccumulator;
			state.logProbability$v2 = (state.logProbability$v2 + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample11)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample11 = state.fixedFlag$sample11;
		} else {
			state.logProbability$v2 = (state.logProbability$v2 + state.logProbability$var11);
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
			}
			state.logProbability$v2 = (state.logProbability$v2 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample27)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample27 = state.fixedFlag$sample27;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.size; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample27[i]);
			state.logProbability$v2 = (state.logProbability$v2 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample27)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample49() {
		if(!state.fixedProbFlag$sample49) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.size; j += 1) {
				double var47 = ((double)((state.v1 + state.v2[j]) + state.v2[j]) / state.v2[j]);
				double cv$distributionAccumulator = (((0.0 <= var47) && (var47 <= 1.0))?Math.log((state.v[j]?var47:(1.0 - var47))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample49[j] = cv$distributionAccumulator;
			}
			state.logProbability$v = (state.logProbability$v + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample49 = ((state.fixedFlag$sample5 && state.fixedFlag$sample11) && state.fixedFlag$sample27);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.size; j += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample49[j]);
			state.logProbability$v = (state.logProbability$v + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
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

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample5)
			state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample11)
			state.v2[0] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample27)
			parallelFor(state.RNG$, 0, state.size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							state.v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, state.weightings, state.weightings.length);
				}
			);

		parallelFor(state.RNG$, 0, state.size, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						state.v[j] = DistributionSampling.sampleBernoulli(RNG$1, ((double)((state.v1 + state.v2[j]) + state.v2[j]) / state.v2[j]));
			}
		);
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
		if(!state.fixedFlag$sample27)
			parallelFor(state.RNG$, 0, state.size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							double[] cv$distribution$sample27 = state.distribution$sample27[i];
							for(int index$var26 = 0; index$var26 < state.weightings.length; index$var26 += 1)
								cv$distribution$sample27[index$var26] = ((((0 < state.weightings.length) && (0.0 <= state.weightings[index$var26])) && (state.weightings[index$var26] <= 1.0))?state.weightings[index$var26]:0.0);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample5)
			state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample11)
			state.v2[0] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample27)
			parallelFor(state.RNG$, 0, state.size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							state.v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, state.weightings, state.weightings.length);
				}
			);

		parallelFor(state.RNG$, 0, state.size, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						state.v[j] = DistributionSampling.sampleBernoulli(RNG$1, ((double)((state.v1 + state.v2[j]) + state.v2[j]) / state.v2[j]));
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample5)
			state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample11)
			state.v2[0] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample27)
			parallelFor(state.RNG$, 0, state.size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							state.v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, state.weightings, state.weightings.length);
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample5)
			state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample11)
			state.v2[0] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample27)
			parallelFor(state.RNG$, 0, state.size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							state.v2[(i + 1)] = DistributionSampling.sampleCategorical(RNG$1, state.weightings, state.weightings.length);
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample5)
				inferSample5();
			if(!state.fixedFlag$sample11)
				inferSample11();
			if(!state.fixedFlag$sample27)
				parallelFor(state.RNG$, 0, state.size, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i = forStart$i; i < forEnd$i; i += 1)
								inferSample27(i, threadID$i, RNG$1);
					}
				);

		} else {
			if(!state.fixedFlag$sample27)
				parallelFor(state.RNG$, 0, state.size, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i = forStart$i; i < forEnd$i; i += 1)
								inferSample27(i, threadID$i, RNG$1);
					}
				);

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
		parallelFor(state.RNG$, 0, state.size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!state.constrainedFlag$sample27[i])
							drawValueSample27(i, threadID$i, RNG$1);
					}
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample5)
			state.logProbability$v1 = Double.NaN;
		state.logProbability$v2 = 0.0;
		if(!state.fixedProbFlag$sample11)
			state.logProbability$var11 = Double.NaN;
		if(!state.fixedProbFlag$sample27) {
			for(int i = 0; i < state.size; i += 1)
				state.logProbability$sample27[i] = Double.NaN;
		}
		state.logProbability$v = 0.0;
		if(!state.fixedProbFlag$sample49) {
			for(int j = 0; j < state.size; j += 1)
				state.logProbability$sample49[j] = Double.NaN;
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
		logProbabilityValue$sample49();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample5();
		logProbabilityDistribution$sample11();
		logProbabilityDistribution$sample27();
		logProbabilityDistribution$sample49();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample11();
		logProbabilityValue$sample27();
		logProbabilityValue$sample49();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.v.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.v[cv$index1] = state.value[cv$index1];
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
		     + "model DistributionTest6(double[] weightings, boolean[] value) {\n"
		     + "    int size = value.length;\n"
		     + "    \n"
		     + "    int v1 = categorical(weightings).sampleDistribution();\n"
		     + "    \n"
		     + "    int[] v2 = new int[size + 1];\n"
		     + "    v2[0] = categorical(weightings).sampleDistribution();\n"
		     + "    for(int i:[0..size))\n"
		     + "        v2[i + 1] = categorical(weightings).sampleDistribution();\n"
		     + "        \n"
		     + "    boolean[] v = new boolean[size];\n"
		     + "    for(int j:[0..size))\n"
		     + "        v[j] = bernoulli(((1.0*v1) + v2[j] + v2[j])/v2[j]).sample();\n"
		     + "        \n"
		     + "    v.observe(value);\n"
		     + "}\n"
		     + "";
	}
}