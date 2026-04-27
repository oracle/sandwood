package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMMetrics$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMMetrics.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMMetrics$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$distributionAccumulator$var55;
		double[][] cv$var30$countGlobal;
		double[] cv$var35$countGlobal;
		double[] cv$var38$stateProbabilityGlobal;
		double[] cv$var56$stateProbabilityGlobal;
		boolean[] guard$sample39gaussian179$global;
		boolean[] guard$sample39gaussian184$global;
		boolean[] guard$sample39gaussian189$global;
		boolean[] guard$sample57gaussian179$global;
		boolean[] guard$sample57gaussian184$global;
		boolean[] guard$sample57gaussian189$global;

		@Override
		public final void allocateScratch() {
			int cv$threadCount = threadCount();
			cv$var30$countGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var30$countGlobal[cv$index] = new double[state.noStates];
			cv$var35$countGlobal = new double[state.noStates];
			cv$distributionAccumulator$var55 = new double[state.noStates];
			cv$var38$stateProbabilityGlobal = new double[state.noStates];
			guard$sample39gaussian179$global = new boolean[state.length$cpu_measured];
			guard$sample39gaussian184$global = new boolean[state.length$cpu_measured];
			guard$sample39gaussian189$global = new boolean[state.length$cpu_measured];
			cv$var56$stateProbabilityGlobal = new double[state.noStates];
			guard$sample57gaussian179$global = new boolean[state.length$cpu_measured];
			guard$sample57gaussian184$global = new boolean[state.length$cpu_measured];
			guard$sample57gaussian189$global = new boolean[state.length$cpu_measured];
		}
	}


	public HMMMetrics$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample113(int var111, int threadID$cv$var111, Rng RNG$) {
		state.pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$) * 579.2667779184303) + 814.0);
	}

	private final void drawValueSample130(int var128, int threadID$cv$var128, Rng RNG$) {
		state.cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
	}

	private final void drawValueSample147(int var145, int threadID$cv$var145, Rng RNG$) {
		state.memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
	}

	private final void drawValueSample164(int var162, int threadID$cv$var162, Rng RNG$) {
		state.pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$, 5.0, 0.5);
	}

	private final void drawValueSample30(int var29, int threadID$cv$var29, Rng RNG$) {
		DistributionSampling.sampleDirichlet(RNG$, state.v, state.noStates, state.m[var29]);
	}

	private final void drawValueSample36() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
	}

	private final void drawValueSample39() {
		state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
	}

	private final void drawValueSample57(int i$var50) {
		state.st[i$var50] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var50 - 1)]], state.noStates);
	}

	private final void drawValueSample77(int var75, int threadID$cv$var75, Rng RNG$) {
		state.cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$) * 2.932575659723036) + 16.0);
	}

	private final void drawValueSample95(int var93, int threadID$cv$var93, Rng RNG$) {
		state.memMean[var93] = (DistributionSampling.sampleGaussian(RNG$) + 94.0);
	}

	private final void inferSample113(int var111, int threadID$cv$var111, Rng RNG$) {
		state.constrainedFlag$sample113[var111] = false;
		double cv$originalValue = state.pageFaultsMean[var111];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$originalValue - 814.0) / 579.2667779184303)) - 6.361763127793193);
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					if((var111 == state.st[0])) {
						state.constrainedFlag$sample113[var111] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var162 = state.st[0];
						if(((0 <= var162) && (var162 < state.noStates))) {
							double var187 = state.pageFaultsVar[state.st[0]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[0] - cv$originalValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample39Value5 = state.distribution$sample39[var111];
					state.constrainedFlag$sample113[var111] = true;
					double var187 = state.pageFaultsVar[var111];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[0] - cv$originalValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
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
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					if((var111 == state.st[i$var174])) {
						state.constrainedFlag$sample113[var111] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var162 = state.st[i$var174];
						if(((0 <= var162) && (var162 < state.noStates))) {
							double var187 = state.pageFaultsVar[state.st[i$var174]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var174] - cv$originalValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var111];
					state.constrainedFlag$sample113[var111] = true;
					double var187 = state.pageFaultsVar[var111];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var174] - cv$originalValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample113[var111]) {
			state.pageFaultsMean[var111] = cv$proposedValue;
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - 814.0) / 579.2667779184303)) - 6.361763127793193);
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					if((var111 == state.st[0])) {
						state.constrainedFlag$sample113[var111] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var162 = state.st[0];
						if(((0 <= var162) && (var162 < state.noStates))) {
							double var187 = state.pageFaultsVar[state.st[0]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[0] - cv$proposedValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample39Value5 = state.distribution$sample39[var111];
					state.constrainedFlag$sample113[var111] = true;
					double var187 = state.pageFaultsVar[var111];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[0] - cv$proposedValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
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
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					if((var111 == state.st[i$var174])) {
						state.constrainedFlag$sample113[var111] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var162 = state.st[i$var174];
						if(((0 <= var162) && (var162 < state.noStates))) {
							double var187 = state.pageFaultsVar[state.st[i$var174]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var174] - cv$proposedValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var111];
					state.constrainedFlag$sample113[var111] = true;
					double var187 = state.pageFaultsVar[var111];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var174] - cv$proposedValue) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
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
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				state.pageFaultsMean[var111] = cv$originalValue;
		}
	}

	private final void inferSample130(int var128, int threadID$cv$var128, Rng RNG$) {
		state.constrainedFlag$sample130[var128] = false;
		double cv$originalValue = state.cpuVar[var128];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					if((var128 == state.st[0])) {
						state.constrainedFlag$sample130[var128] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var75 = state.st[0];
						if(((0 <= var75) && (var75 < state.noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.cpu[0] - state.cpuMean[state.st[0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample39Value5 = state.distribution$sample39[var128];
					state.constrainedFlag$sample130[var128] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.cpu[0] - state.cpuMean[var128]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
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
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					if((var128 == state.st[i$var174])) {
						state.constrainedFlag$sample130[var128] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var75 = state.st[i$var174];
						if(((0 <= var75) && (var75 < state.noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var174] - state.cpuMean[state.st[i$var174]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var128];
					state.constrainedFlag$sample130[var128] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var174] - state.cpuMean[var128]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample130[var128]) {
			state.cpuVar[var128] = cv$proposedValue;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					if((var128 == state.st[0])) {
						state.constrainedFlag$sample130[var128] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var75 = state.st[0];
						if(((0 <= var75) && (var75 < state.noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.cpu[0] - state.cpuMean[state.st[0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample39Value5 = state.distribution$sample39[var128];
					state.constrainedFlag$sample130[var128] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.cpu[0] - state.cpuMean[var128]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
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
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					if((var128 == state.st[i$var174])) {
						state.constrainedFlag$sample130[var128] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var75 = state.st[i$var174];
						if(((0 <= var75) && (var75 < state.noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var174] - state.cpuMean[state.st[i$var174]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var128];
					state.constrainedFlag$sample130[var128] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var174] - state.cpuMean[var128]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
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
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				state.cpuVar[var128] = cv$originalValue;
		}
	}

	private final void inferSample147(int var145, int threadID$cv$var145, Rng RNG$) {
		state.constrainedFlag$sample147[var145] = false;
		double cv$originalValue = state.memVar[var145];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					if((var145 == state.st[0])) {
						state.constrainedFlag$sample147[var145] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var93 = state.st[0];
						if(((0 <= var93) && (var93 < state.noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.mem[0] - state.memMean[state.st[0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample39Value5 = state.distribution$sample39[var145];
					state.constrainedFlag$sample147[var145] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.mem[0] - state.memMean[var145]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
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
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					if((var145 == state.st[i$var174])) {
						state.constrainedFlag$sample147[var145] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var93 = state.st[i$var174];
						if(((0 <= var93) && (var93 < state.noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var174] - state.memMean[state.st[i$var174]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var145];
					state.constrainedFlag$sample147[var145] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var174] - state.memMean[var145]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample147[var145]) {
			state.memVar[var145] = cv$proposedValue;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					if((var145 == state.st[0])) {
						state.constrainedFlag$sample147[var145] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var93 = state.st[0];
						if(((0 <= var93) && (var93 < state.noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.mem[0] - state.memMean[state.st[0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample39Value5 = state.distribution$sample39[var145];
					state.constrainedFlag$sample147[var145] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.mem[0] - state.memMean[var145]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
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
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					if((var145 == state.st[i$var174])) {
						state.constrainedFlag$sample147[var145] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var93 = state.st[i$var174];
						if(((0 <= var93) && (var93 < state.noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var174] - state.memMean[state.st[i$var174]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var145];
					state.constrainedFlag$sample147[var145] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var174] - state.memMean[var145]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
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
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				state.memVar[var145] = cv$originalValue;
		}
	}

	private final void inferSample164(int var162, int threadID$cv$var162, Rng RNG$) {
		state.constrainedFlag$sample164[var162] = false;
		double cv$originalValue = state.pageFaultsVar[var162];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 5.0, 0.5);
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					if((var162 == state.st[0])) {
						state.constrainedFlag$sample164[var162] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var111 = state.st[0];
						if(((0 <= var111) && (var111 < state.noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[0] - state.pageFaultsMean[state.st[0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample39Value5 = state.distribution$sample39[var162];
					state.constrainedFlag$sample164[var162] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[0] - state.pageFaultsMean[var162]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
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
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					if((var162 == state.st[i$var174])) {
						state.constrainedFlag$sample164[var162] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var111 = state.st[i$var174];
						if(((0 <= var111) && (var111 < state.noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var174] - state.pageFaultsMean[state.st[i$var174]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var162];
					state.constrainedFlag$sample164[var162] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var174] - state.pageFaultsMean[var162]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample164[var162]) {
			state.pageFaultsVar[var162] = cv$proposedValue;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 5.0, 0.5);
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					if((var162 == state.st[0])) {
						state.constrainedFlag$sample164[var162] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var111 = state.st[0];
						if(((0 <= var111) && (var111 < state.noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[0] - state.pageFaultsMean[state.st[0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample39Value5 = state.distribution$sample39[var162];
					state.constrainedFlag$sample164[var162] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[0] - state.pageFaultsMean[var162]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
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
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					if((var162 == state.st[i$var174])) {
						state.constrainedFlag$sample164[var162] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var111 = state.st[i$var174];
						if(((0 <= var111) && (var111 < state.noStates))) {
							cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var174] - state.pageFaultsMean[state.st[i$var174]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var162];
					state.constrainedFlag$sample164[var162] = true;
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var174] - state.pageFaultsMean[var162]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
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
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				state.pageFaultsVar[var162] = cv$originalValue;
		}
	}

	private final void inferSample30(int var29, int threadID$cv$var29, Rng RNG$) {
		state.constrainedFlag$sample30[var29] = false;
		double[] cv$countLocal = scratch.cv$var30$countGlobal[threadID$cv$var29];
		for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		if(state.fixedFlag$sample57) {
			if((1 < state.samples)) {
				if(state.fixedFlag$sample39) {
					if((var29 == state.st[0])) {
						state.constrainedFlag$sample30[var29] = true;
						cv$countLocal[state.st[1]] = (cv$countLocal[state.st[1]] + 1.0);
					}
				} else {
					state.constrainedFlag$sample30[var29] = true;
					cv$countLocal[state.st[1]] = (cv$countLocal[state.st[1]] + state.distribution$sample39[var29]);
				}
			}
			for(int i$var50 = 2; i$var50 < state.samples; i$var50 += 1) {
				if((var29 == state.st[(i$var50 - 1)])) {
					state.constrainedFlag$sample30[var29] = true;
					cv$countLocal[state.st[i$var50]] = (cv$countLocal[state.st[i$var50]] + 1.0);
				}
			}
		} else {
			if((1 < state.samples)) {
				if(state.fixedFlag$sample39) {
					if((var29 == state.st[0])) {
						for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + state.distribution$sample57[0][cv$loopIndex]);
					}
				} else {
					double cv$distributionProbability = state.distribution$sample39[var29];
					for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
						cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample57[0][cv$loopIndex] * cv$distributionProbability));
				}
			}
			for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1) {
				int index$i$40 = (i$var50 - 1);
				if((1 <= index$i$40)) {
					double cv$distributionProbability = state.distribution$sample57[(index$i$40 - 1)][var29];
					for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
						cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample57[(i$var50 - 1)][cv$loopIndex] * cv$distributionProbability));
				}
			}
		}
		if(state.constrainedFlag$sample30[var29])
			Conjugates.sampleConjugateDirichletCategorical(RNG$, state.v, cv$countLocal, state.m[var29], state.noStates);
	}

	private final void inferSample36() {
		state.constrainedFlag$sample36 = false;
		for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
			scratch.cv$var35$countGlobal[cv$loopIndex] = 0.0;
		if(state.fixedFlag$sample39) {
			state.constrainedFlag$sample36 = true;
			scratch.cv$var35$countGlobal[state.st[0]] = (scratch.cv$var35$countGlobal[state.st[0]] + 1.0);
		} else {
			for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
				scratch.cv$var35$countGlobal[cv$loopIndex] = (scratch.cv$var35$countGlobal[cv$loopIndex] + state.distribution$sample39[cv$loopIndex]);
		}
		if(state.constrainedFlag$sample36)
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, scratch.cv$var35$countGlobal, state.initialStateDistribution, state.noStates);
	}

	private final void inferSample39() {
		state.constrainedFlag$sample39 = false;
		int cv$numStates = Math.max(0, state.noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$accumulatedProbabilities = (((((cv$valuePos < state.noStates) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[cv$valuePos])) && (state.initialStateDistribution[cv$valuePos] <= 1.0))?Math.log(state.initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((state.fixedFlag$sample57 && (1 < state.samples))) {
				state.constrainedFlag$sample39 = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((cv$valuePos < state.noStates)) {
					double[] var54 = state.m[cv$valuePos];
					cv$accumulatedConsumerProbabilities = (((((0.0 <= state.st[1]) && (state.st[1] < state.noStates)) && (0.0 <= var54[state.st[1]])) && (var54[state.st[1]] <= 1.0))?Math.log(var54[state.st[1]]):Double.NEGATIVE_INFINITY);
					cv$consumerDistributionProbabilityAccumulator = 0.0;
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
			if((0 < state.samples)) {
				scratch.guard$sample39gaussian179$global[0] = false;
				if(!scratch.guard$sample39gaussian179$global[0]) {
					scratch.guard$sample39gaussian179$global[0] = true;
					state.constrainedFlag$sample39 = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < state.noStates)) {
						double var177 = state.cpuVar[cv$valuePos];
						cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[0] - state.cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
						cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				if(!scratch.guard$sample39gaussian179$global[0]) {
					scratch.guard$sample39gaussian179$global[0] = true;
					state.constrainedFlag$sample39 = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < state.noStates)) {
						double var177 = state.cpuVar[cv$valuePos];
						cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[0] - state.cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
						cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				scratch.guard$sample39gaussian184$global[0] = false;
				if(!scratch.guard$sample39gaussian184$global[0]) {
					scratch.guard$sample39gaussian184$global[0] = true;
					state.constrainedFlag$sample39 = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < state.noStates)) {
						double var182 = state.memVar[cv$valuePos];
						cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[0] - state.memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
						cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				if(!scratch.guard$sample39gaussian184$global[0]) {
					scratch.guard$sample39gaussian184$global[0] = true;
					state.constrainedFlag$sample39 = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < state.noStates)) {
						double var182 = state.memVar[cv$valuePos];
						cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[0] - state.memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
						cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				scratch.guard$sample39gaussian189$global[0] = false;
				if(!scratch.guard$sample39gaussian189$global[0]) {
					scratch.guard$sample39gaussian189$global[0] = true;
					state.constrainedFlag$sample39 = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < state.noStates)) {
						double var187 = state.pageFaultsVar[cv$valuePos];
						cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[0] - state.pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
						cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				if(!scratch.guard$sample39gaussian189$global[0]) {
					scratch.guard$sample39gaussian189$global[0] = true;
					state.constrainedFlag$sample39 = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < state.noStates)) {
						double var187 = state.pageFaultsVar[cv$valuePos];
						cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[0] - state.pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
						cv$consumerDistributionProbabilityAccumulator = 0.0;
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
			if((!state.fixedFlag$sample57 && (1 < state.samples))) {
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1)
					scratch.cv$distributionAccumulator$var55[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < state.noStates)) {
					cv$reachedDistributionProbability = 1.0;
					DistributionSampling.addProbabilityDistributionCategorical(scratch.cv$distributionAccumulator$var55, 1.0, state.m[cv$valuePos], state.noStates);
				}
				double[] cv$sampleDistribution = state.distribution$sample57[0];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1) {
					double cv$normalisedDistValue = (scratch.cv$distributionAccumulator$var55[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			scratch.cv$var38$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		if(state.constrainedFlag$sample39) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var38$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = scratch.cv$var38$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var38$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					state.distribution$sample39[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					state.distribution$sample39[cv$indexName] = Math.exp((scratch.cv$var38$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var38$stateProbabilityGlobal.length; cv$indexName += 1)
				state.distribution$sample39[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void inferSample57(int i$var50) {
		state.constrainedFlag$sample57[(i$var50 - 1)] = false;
		int cv$numStates = 0;
		if((1 == i$var50)) {
			if(state.fixedFlag$sample39) {
				int var29 = state.st[0];
				if(((0 <= var29) && (var29 < state.noStates)))
					cv$numStates = Math.max(0, state.noStates);
			} else {
				if((0 < state.noStates))
					cv$numStates = state.noStates;
			}
		}
		if((0 < state.noStates)) {
			int index$i$10 = (i$var50 - 1);
			if(((1 <= index$i$10) && !(index$i$10 == i$var50)))
				cv$numStates = state.noStates;
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == i$var50)) {
				if(state.fixedFlag$sample39) {
					int var29 = state.st[0];
					if(((0 <= var29) && (var29 < state.noStates))) {
						cv$reachedDistributionSourceRV = 1.0;
						double[] var54 = state.m[state.st[0]];
						double cv$accumulatedProbabilities = ((((cv$valuePos < state.noStates) && (0.0 <= var54[cv$valuePos])) && (var54[cv$valuePos] <= 1.0))?Math.log(var54[cv$valuePos]):Double.NEGATIVE_INFINITY);
						scratch.guard$sample57gaussian179$global[1] = false;
						if(!scratch.guard$sample57gaussian179$global[1]) {
							scratch.guard$sample57gaussian179$global[1] = true;
							state.constrainedFlag$sample57[0] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < state.noStates)) {
								double var177 = state.cpuVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[1] - state.cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						if(!scratch.guard$sample57gaussian179$global[1]) {
							scratch.guard$sample57gaussian179$global[1] = true;
							state.constrainedFlag$sample57[0] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < state.noStates)) {
								double var177 = state.cpuVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[1] - state.cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						scratch.guard$sample57gaussian184$global[1] = false;
						if(!scratch.guard$sample57gaussian184$global[1]) {
							scratch.guard$sample57gaussian184$global[1] = true;
							state.constrainedFlag$sample57[0] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < state.noStates)) {
								double var182 = state.memVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[1] - state.memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						if(!scratch.guard$sample57gaussian184$global[1]) {
							scratch.guard$sample57gaussian184$global[1] = true;
							state.constrainedFlag$sample57[0] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < state.noStates)) {
								double var182 = state.memVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[1] - state.memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						scratch.guard$sample57gaussian189$global[1] = false;
						if(!scratch.guard$sample57gaussian189$global[1]) {
							scratch.guard$sample57gaussian189$global[1] = true;
							state.constrainedFlag$sample57[0] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < state.noStates)) {
								double var187 = state.pageFaultsVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[1] - state.pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						if(!scratch.guard$sample57gaussian189$global[1]) {
							scratch.guard$sample57gaussian189$global[1] = true;
							state.constrainedFlag$sample57[0] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < state.noStates)) {
								double var187 = state.pageFaultsVar[cv$valuePos];
								cv$accumulatedConsumerProbabilities = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[1] - state.pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					for(int index$sample39$18 = 0; index$sample39$18 < state.noStates; index$sample39$18 += 1) {
						double cv$probabilitySample39Value19 = state.distribution$sample39[index$sample39$18];
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample39Value19);
						double[] var54 = state.m[index$sample39$18];
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample39Value19) + (((0.0 <= var54[cv$valuePos]) && (var54[cv$valuePos] <= 1.0))?Math.log(var54[cv$valuePos]):Double.NEGATIVE_INFINITY));
						scratch.guard$sample57gaussian179$global[1] = false;
						if(!scratch.guard$sample57gaussian179$global[1]) {
							scratch.guard$sample57gaussian179$global[1] = true;
							state.constrainedFlag$sample57[0] = true;
							double var177 = state.cpuVar[cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[1] - state.cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						if(!scratch.guard$sample57gaussian179$global[1]) {
							scratch.guard$sample57gaussian179$global[1] = true;
							state.constrainedFlag$sample57[0] = true;
							double var177 = state.cpuVar[cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[1] - state.cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						scratch.guard$sample57gaussian184$global[1] = false;
						if(!scratch.guard$sample57gaussian184$global[1]) {
							scratch.guard$sample57gaussian184$global[1] = true;
							state.constrainedFlag$sample57[0] = true;
							double var182 = state.memVar[cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[1] - state.memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						if(!scratch.guard$sample57gaussian184$global[1]) {
							scratch.guard$sample57gaussian184$global[1] = true;
							state.constrainedFlag$sample57[0] = true;
							double var182 = state.memVar[cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[1] - state.memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						scratch.guard$sample57gaussian189$global[1] = false;
						if(!scratch.guard$sample57gaussian189$global[1]) {
							scratch.guard$sample57gaussian189$global[1] = true;
							state.constrainedFlag$sample57[0] = true;
							double var187 = state.pageFaultsVar[cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[1] - state.pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						if(!scratch.guard$sample57gaussian189$global[1]) {
							scratch.guard$sample57gaussian189$global[1] = true;
							state.constrainedFlag$sample57[0] = true;
							double var187 = state.pageFaultsVar[cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[1] - state.pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
						else {
							if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
								cv$stateProbabilityValue = cv$accumulatedProbabilities;
							else
								cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			int index$i$25 = (i$var50 - 1);
			if(((1 <= index$i$25) && !(index$i$25 == i$var50))) {
				for(int index$sample57$26 = 0; index$sample57$26 < state.noStates; index$sample57$26 += 1) {
					double cv$probabilitySample57Value27 = state.distribution$sample57[(index$i$25 - 1)][index$sample57$26];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value27);
					double[] var54 = state.m[index$sample57$26];
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample57Value27) + (((0.0 <= var54[cv$valuePos]) && (var54[cv$valuePos] <= 1.0))?Math.log(var54[cv$valuePos]):Double.NEGATIVE_INFINITY));
					scratch.guard$sample57gaussian179$global[i$var50] = false;
					if(!scratch.guard$sample57gaussian179$global[i$var50]) {
						scratch.guard$sample57gaussian179$global[i$var50] = true;
						state.constrainedFlag$sample57[(i$var50 - 1)] = true;
						double var177 = state.cpuVar[cv$valuePos];
						cv$accumulatedProbabilities = (((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var50] - state.cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!scratch.guard$sample57gaussian179$global[i$var50]) {
						scratch.guard$sample57gaussian179$global[i$var50] = true;
						state.constrainedFlag$sample57[(i$var50 - 1)] = true;
						double var177 = state.cpuVar[cv$valuePos];
						cv$accumulatedProbabilities = (((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var50] - state.cpuMean[cv$valuePos]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					scratch.guard$sample57gaussian184$global[i$var50] = false;
					if(!scratch.guard$sample57gaussian184$global[i$var50]) {
						scratch.guard$sample57gaussian184$global[i$var50] = true;
						state.constrainedFlag$sample57[(i$var50 - 1)] = true;
						double var182 = state.memVar[cv$valuePos];
						cv$accumulatedProbabilities = (((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var50] - state.memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!scratch.guard$sample57gaussian184$global[i$var50]) {
						scratch.guard$sample57gaussian184$global[i$var50] = true;
						state.constrainedFlag$sample57[(i$var50 - 1)] = true;
						double var182 = state.memVar[cv$valuePos];
						cv$accumulatedProbabilities = (((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var50] - state.memMean[cv$valuePos]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					scratch.guard$sample57gaussian189$global[i$var50] = false;
					if(!scratch.guard$sample57gaussian189$global[i$var50]) {
						scratch.guard$sample57gaussian189$global[i$var50] = true;
						state.constrainedFlag$sample57[(i$var50 - 1)] = true;
						double var187 = state.pageFaultsVar[cv$valuePos];
						cv$accumulatedProbabilities = (((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var50] - state.pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!scratch.guard$sample57gaussian189$global[i$var50]) {
						scratch.guard$sample57gaussian189$global[i$var50] = true;
						state.constrainedFlag$sample57[(i$var50 - 1)] = true;
						double var187 = state.pageFaultsVar[cv$valuePos];
						cv$accumulatedProbabilities = (((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var50] - state.pageFaultsMean[cv$valuePos]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
			}
			int index$i$618_2 = (i$var50 + 1);
			if((index$i$618_2 < state.samples)) {
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1)
					scratch.cv$distributionAccumulator$var55[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < state.noStates)) {
					double scopeVariable$reachedSourceProbability = 0.0;
					if((1 == i$var50)) {
						if(state.fixedFlag$sample39) {
							int index$var29$627_1 = state.st[0];
							if(((0 <= index$var29$627_1) && (index$var29$627_1 < state.noStates)))
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							for(int index$sample39$623 = 0; index$sample39$623 < state.noStates; index$sample39$623 += 1)
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + state.distribution$sample39[index$sample39$623]);
						}
					}
					int index$i$630 = (i$var50 - 1);
					if((((1 <= index$i$630) && !(index$i$630 == i$var50)) && !(index$i$630 == index$i$618_2))) {
						for(int index$sample57$631 = 0; index$sample57$631 < state.noStates; index$sample57$631 += 1)
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + state.distribution$sample57[(index$i$630 - 1)][index$sample57$631]);
					}
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					DistributionSampling.addProbabilityDistributionCategorical(scratch.cv$distributionAccumulator$var55, scopeVariable$reachedSourceProbability, state.m[cv$valuePos], state.noStates);
				}
				double[] cv$sampleDistribution = state.distribution$sample57[(index$i$618_2 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1) {
					double cv$normalisedDistValue = (scratch.cv$distributionAccumulator$var55[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			scratch.cv$var56$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		if(state.constrainedFlag$sample57[(i$var50 - 1)]) {
			double[] cv$localProbability = state.distribution$sample57[(i$var50 - 1)];
			double cv$logSum;
			double cv$lseMax = scratch.cv$var56$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = scratch.cv$var56$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var56$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((scratch.cv$var56$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var56$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void inferSample77(int var75, int threadID$cv$var75, Rng RNG$) {
		state.constrainedFlag$sample77[var75] = false;
		double cv$originalValue = state.cpuMean[var75];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$originalValue - 16.0) / 2.932575659723036)) - 1.075881101629731);
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					if((var75 == state.st[0])) {
						state.constrainedFlag$sample77[var75] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var128 = state.st[0];
						if(((0 <= var128) && (var128 < state.noStates))) {
							double var177 = state.cpuVar[state.st[0]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[0] - cv$originalValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample39Value5 = state.distribution$sample39[var75];
					state.constrainedFlag$sample77[var75] = true;
					double var177 = state.cpuVar[var75];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[0] - cv$originalValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
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
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					if((var75 == state.st[i$var174])) {
						state.constrainedFlag$sample77[var75] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var128 = state.st[i$var174];
						if(((0 <= var128) && (var128 < state.noStates))) {
							double var177 = state.cpuVar[state.st[i$var174]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var174] - cv$originalValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var75];
					state.constrainedFlag$sample77[var75] = true;
					double var177 = state.cpuVar[var75];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var174] - cv$originalValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample77[var75]) {
			state.cpuMean[var75] = cv$proposedValue;
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - 16.0) / 2.932575659723036)) - 1.075881101629731);
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					if((var75 == state.st[0])) {
						state.constrainedFlag$sample77[var75] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var128 = state.st[0];
						if(((0 <= var128) && (var128 < state.noStates))) {
							double var177 = state.cpuVar[state.st[0]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[0] - cv$proposedValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample39Value5 = state.distribution$sample39[var75];
					state.constrainedFlag$sample77[var75] = true;
					double var177 = state.cpuVar[var75];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[0] - cv$proposedValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
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
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					if((var75 == state.st[i$var174])) {
						state.constrainedFlag$sample77[var75] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var128 = state.st[i$var174];
						if(((0 <= var128) && (var128 < state.noStates))) {
							double var177 = state.cpuVar[state.st[i$var174]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var174] - cv$proposedValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var75];
					state.constrainedFlag$sample77[var75] = true;
					double var177 = state.cpuVar[var75];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var174] - cv$proposedValue) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
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
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				state.cpuMean[var75] = cv$originalValue;
		}
	}

	private final void inferSample95(int var93, int threadID$cv$var93, Rng RNG$) {
		state.constrainedFlag$sample95[var93] = false;
		double cv$originalValue = state.memMean[var93];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian((cv$originalValue - 94.0));
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					if((var93 == state.st[0])) {
						state.constrainedFlag$sample95[var93] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var145 = state.st[0];
						if(((0 <= var145) && (var145 < state.noStates))) {
							double var182 = state.memVar[state.st[0]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[0] - cv$originalValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample39Value5 = state.distribution$sample39[var93];
					state.constrainedFlag$sample95[var93] = true;
					double var182 = state.memVar[var93];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[0] - cv$originalValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
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
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					if((var93 == state.st[i$var174])) {
						state.constrainedFlag$sample95[var93] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var145 = state.st[i$var174];
						if(((0 <= var145) && (var145 < state.noStates))) {
							double var182 = state.memVar[state.st[i$var174]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var174] - cv$originalValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var93];
					state.constrainedFlag$sample95[var93] = true;
					double var182 = state.memVar[var93];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var174] - cv$originalValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample95[var93]) {
			state.memMean[var93] = cv$proposedValue;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityGaussian((cv$proposedValue - 94.0));
			if((0 < state.samples)) {
				if(state.fixedFlag$sample39) {
					if((var93 == state.st[0])) {
						state.constrainedFlag$sample95[var93] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var145 = state.st[0];
						if(((0 <= var145) && (var145 < state.noStates))) {
							double var182 = state.memVar[state.st[0]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[0] - cv$proposedValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample39Value5 = state.distribution$sample39[var93];
					state.constrainedFlag$sample95[var93] = true;
					double var182 = state.memVar[var93];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample39Value5) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[0] - cv$proposedValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample39Value5), 0.0);
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
			for(int i$var174 = 1; i$var174 < state.samples; i$var174 += 1) {
				if(state.fixedFlag$sample57) {
					if((var93 == state.st[i$var174])) {
						state.constrainedFlag$sample95[var93] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var145 = state.st[i$var174];
						if(((0 <= var145) && (var145 < state.noStates))) {
							double var182 = state.memVar[state.st[i$var174]];
							cv$accumulatedConsumerProbabilities = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var174] - cv$proposedValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double cv$probabilitySample57Value14 = state.distribution$sample57[(i$var174 - 1)][var93];
					state.constrainedFlag$sample95[var93] = true;
					double var182 = state.memVar[var93];
					double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value14) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var174] - cv$proposedValue) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value14), 0.0);
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
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				state.memMean[var93] = cv$originalValue;
		}
	}

	private final void logProbabilityDistribution$sample180() {
		if(!state.fixedProbFlag$sample180) {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				double cv$sampleValue = state.cpu[i$var174];
				if((0 == i$var174)) {
					if(state.fixedFlag$sample39) {
						if((0 <= state.st[0])) {
							int var75 = state.st[0];
							if(((0 <= var75) && (var75 < state.noStates))) {
								double var177 = state.cpuVar[state.st[0]];
								cv$distributionAccumulator = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.cpuMean[state.st[0]]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						for(int index$sample39$3 = 0; index$sample39$3 < state.noStates; index$sample39$3 += 1) {
							double cv$probabilitySample39Value4 = state.distribution$sample39[index$sample39$3];
							double var177 = state.cpuVar[index$sample39$3];
							double cv$weightedProbability = (Math.log(cv$probabilitySample39Value4) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.cpuMean[index$sample39$3]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value4);
						}
					}
				}
				if((1 <= i$var174)) {
					if(state.fixedFlag$sample57) {
						if((0 <= state.st[i$var174])) {
							int var75 = state.st[i$var174];
							if(((0 <= var75) && (var75 < state.noStates))) {
								double var177 = state.cpuVar[state.st[i$var174]];
								double cv$weightedProbability = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.cpuMean[state.st[i$var174]]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
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
					} else {
						for(int index$sample57$43 = 0; index$sample57$43 < state.noStates; index$sample57$43 += 1) {
							double cv$probabilitySample57Value44 = state.distribution$sample57[(i$var174 - 1)][index$sample57$43];
							double var177 = state.cpuVar[index$sample57$43];
							double cv$weightedProbability = (Math.log(cv$probabilitySample57Value44) + ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.cpuMean[index$sample57$43]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value44);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample180[i$var174] = cv$distributionAccumulator;
			}
			state.logProbability$cpu = (state.logProbability$cpu + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample180 = (((state.fixedFlag$sample39 && state.fixedFlag$sample57) && state.fixedFlag$sample77) && state.fixedFlag$sample130);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample180[i$var174]);
			state.logProbability$cpu = (state.logProbability$cpu + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample185() {
		if(!state.fixedProbFlag$sample185) {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				double cv$sampleValue = state.mem[i$var174];
				if((0 == i$var174)) {
					if(state.fixedFlag$sample39) {
						if((0 <= state.st[0])) {
							int var93 = state.st[0];
							if(((0 <= var93) && (var93 < state.noStates))) {
								double var182 = state.memVar[state.st[0]];
								cv$distributionAccumulator = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.memMean[state.st[0]]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						for(int index$sample39$3 = 0; index$sample39$3 < state.noStates; index$sample39$3 += 1) {
							double cv$probabilitySample39Value4 = state.distribution$sample39[index$sample39$3];
							double var182 = state.memVar[index$sample39$3];
							double cv$weightedProbability = (Math.log(cv$probabilitySample39Value4) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.memMean[index$sample39$3]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value4);
						}
					}
				}
				if((1 <= i$var174)) {
					if(state.fixedFlag$sample57) {
						if((0 <= state.st[i$var174])) {
							int var93 = state.st[i$var174];
							if(((0 <= var93) && (var93 < state.noStates))) {
								double var182 = state.memVar[state.st[i$var174]];
								double cv$weightedProbability = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.memMean[state.st[i$var174]]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
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
					} else {
						for(int index$sample57$43 = 0; index$sample57$43 < state.noStates; index$sample57$43 += 1) {
							double cv$probabilitySample57Value44 = state.distribution$sample57[(i$var174 - 1)][index$sample57$43];
							double var182 = state.memVar[index$sample57$43];
							double cv$weightedProbability = (Math.log(cv$probabilitySample57Value44) + ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.memMean[index$sample57$43]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value44);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample185[i$var174] = cv$distributionAccumulator;
			}
			state.logProbability$mem = (state.logProbability$mem + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample185 = (((state.fixedFlag$sample39 && state.fixedFlag$sample57) && state.fixedFlag$sample95) && state.fixedFlag$sample147);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample185[i$var174]);
			state.logProbability$mem = (state.logProbability$mem + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample190() {
		if(!state.fixedProbFlag$sample190) {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				double cv$sampleValue = state.pageFaults[i$var174];
				if((0 == i$var174)) {
					if(state.fixedFlag$sample39) {
						if((0 <= state.st[0])) {
							int var111 = state.st[0];
							if(((0 <= var111) && (var111 < state.noStates))) {
								double var187 = state.pageFaultsVar[state.st[0]];
								cv$distributionAccumulator = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.pageFaultsMean[state.st[0]]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
								cv$probabilityReached = 1.0;
							}
						}
					} else {
						for(int index$sample39$3 = 0; index$sample39$3 < state.noStates; index$sample39$3 += 1) {
							double cv$probabilitySample39Value4 = state.distribution$sample39[index$sample39$3];
							double var187 = state.pageFaultsVar[index$sample39$3];
							double cv$weightedProbability = (Math.log(cv$probabilitySample39Value4) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.pageFaultsMean[index$sample39$3]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value4);
						}
					}
				}
				if((1 <= i$var174)) {
					if(state.fixedFlag$sample57) {
						if((0 <= state.st[i$var174])) {
							int var111 = state.st[i$var174];
							if(((0 <= var111) && (var111 < state.noStates))) {
								double var187 = state.pageFaultsVar[state.st[i$var174]];
								double cv$weightedProbability = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.pageFaultsMean[state.st[i$var174]]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
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
					} else {
						for(int index$sample57$43 = 0; index$sample57$43 < state.noStates; index$sample57$43 += 1) {
							double cv$probabilitySample57Value44 = state.distribution$sample57[(i$var174 - 1)][index$sample57$43];
							double var187 = state.pageFaultsVar[index$sample57$43];
							double cv$weightedProbability = (Math.log(cv$probabilitySample57Value44) + ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.pageFaultsMean[index$sample57$43]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value44);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample190[i$var174] = cv$distributionAccumulator;
			}
			state.logProbability$pageFaults = (state.logProbability$pageFaults + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample190 = (((state.fixedFlag$sample39 && state.fixedFlag$sample57) && state.fixedFlag$sample113) && state.fixedFlag$sample164);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample190[i$var174]);
			state.logProbability$pageFaults = (state.logProbability$pageFaults + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample39() {
		if(!state.fixedProbFlag$sample39) {
			if(state.fixedFlag$sample39) {
				int cv$sampleValue = state.st[0];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[cv$sampleValue])) && (state.initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(state.initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				state.logProbability$var38 = cv$distributionAccumulator;
				state.logProbability$st = (state.logProbability$st + cv$distributionAccumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
				state.fixedProbFlag$sample39 = state.fixedFlag$sample36;
			}
		} else {
			if(state.fixedFlag$sample39)
				state.logProbability$st = (state.logProbability$st + state.logProbability$var38);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var38);
			if(state.fixedFlag$sample39)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var38);
		}
	}

	private final void logProbabilityDistribution$sample57() {
		if(!state.fixedProbFlag$sample57) {
			if(state.fixedFlag$sample57) {
				double cv$accumulator = 0.0;
				for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int cv$sampleValue = state.st[i$var50];
					if((1 == i$var50)) {
						if(state.fixedFlag$sample39) {
							int var29 = state.st[0];
							if(((0 <= var29) && (var29 < state.noStates))) {
								double[] var54 = state.m[state.st[0]];
								cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY);
								cv$probabilityReached = 1.0;
							}
						} else {
							for(int index$sample39$4 = 0; index$sample39$4 < state.noStates; index$sample39$4 += 1) {
								double cv$probabilitySample39Value5 = state.distribution$sample39[index$sample39$4];
								double[] var54 = state.m[index$sample39$4];
								double cv$weightedProbability = (Math.log(cv$probabilitySample39Value5) + (((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample39Value5);
							}
						}
					}
					if((2 <= i$var50)) {
						int var29 = state.st[(i$var50 - 1)];
						if(((0 <= var29) && (var29 < state.noStates))) {
							double[] var54 = state.m[state.st[(i$var50 - 1)]];
							double cv$weightedProbability = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					state.logProbability$sample57[(i$var50 - 1)] = cv$distributionAccumulator;
				}
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample57 = (state.fixedFlag$sample30 && state.fixedFlag$sample39);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample57[(i$var50 - 1)]);
			if(state.fixedFlag$sample57)
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample113() {
		if(!state.fixedProbFlag$sample113) {
			double cv$sampleAccumulator = 0.0;
			for(int var111 = 0; var111 < state.noStates; var111 += 1)
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((state.pageFaultsMean[var111] - 814.0) / 579.2667779184303))) - 6.361763127793193);
			state.logProbability$var112 = cv$sampleAccumulator;
			state.logProbability$pageFaultsMean = (state.logProbability$pageFaultsMean + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample113)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample113 = state.fixedFlag$sample113;
		} else {
			state.logProbability$pageFaultsMean = (state.logProbability$pageFaultsMean + state.logProbability$var112);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var112);
			if(state.fixedFlag$sample113)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var112);
		}
	}

	private final void logProbabilityValue$sample130() {
		if(!state.fixedProbFlag$sample130) {
			double cv$sampleAccumulator = 0.0;
			for(int var128 = 0; var128 < state.noStates; var128 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(state.cpuVar[var128], 5.0, 0.5));
			state.logProbability$var129 = cv$sampleAccumulator;
			state.logProbability$cpuVar = (state.logProbability$cpuVar + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample130)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample130 = state.fixedFlag$sample130;
		} else {
			state.logProbability$cpuVar = (state.logProbability$cpuVar + state.logProbability$var129);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var129);
			if(state.fixedFlag$sample130)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var129);
		}
	}

	private final void logProbabilityValue$sample147() {
		if(!state.fixedProbFlag$sample147) {
			double cv$sampleAccumulator = 0.0;
			for(int var145 = 0; var145 < state.noStates; var145 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(state.memVar[var145], 5.0, 0.5));
			state.logProbability$var146 = cv$sampleAccumulator;
			state.logProbability$memVar = (state.logProbability$memVar + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample147)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample147 = state.fixedFlag$sample147;
		} else {
			state.logProbability$memVar = (state.logProbability$memVar + state.logProbability$var146);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var146);
			if(state.fixedFlag$sample147)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var146);
		}
	}

	private final void logProbabilityValue$sample164() {
		if(!state.fixedProbFlag$sample164) {
			double cv$sampleAccumulator = 0.0;
			for(int var162 = 0; var162 < state.noStates; var162 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(state.pageFaultsVar[var162], 5.0, 0.5));
			state.logProbability$var163 = cv$sampleAccumulator;
			state.logProbability$pageFaultsVar = (state.logProbability$pageFaultsVar + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample164)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample164 = state.fixedFlag$sample164;
		} else {
			state.logProbability$pageFaultsVar = (state.logProbability$pageFaultsVar + state.logProbability$var163);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var163);
			if(state.fixedFlag$sample164)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var163);
		}
	}

	private final void logProbabilityValue$sample180() {
		if(!state.fixedProbFlag$sample180) {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1) {
				double var177 = state.cpuVar[state.st[i$var174]];
				double cv$distributionAccumulator = ((0.0 < var177)?(DistributionSampling.logProbabilityGaussian(((state.cpu[i$var174] - state.cpuMean[state.st[i$var174]]) / Math.sqrt(var177))) - (Math.log(var177) * 0.5)):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample180[i$var174] = cv$distributionAccumulator;
			}
			state.logProbability$cpu = (state.logProbability$cpu + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample180 = (((state.fixedFlag$sample39 && state.fixedFlag$sample57) && state.fixedFlag$sample77) && state.fixedFlag$sample130);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample180[i$var174]);
			state.logProbability$cpu = (state.logProbability$cpu + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample185() {
		if(!state.fixedProbFlag$sample185) {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1) {
				double var182 = state.memVar[state.st[i$var174]];
				double cv$distributionAccumulator = ((0.0 < var182)?(DistributionSampling.logProbabilityGaussian(((state.mem[i$var174] - state.memMean[state.st[i$var174]]) / Math.sqrt(var182))) - (Math.log(var182) * 0.5)):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample185[i$var174] = cv$distributionAccumulator;
			}
			state.logProbability$mem = (state.logProbability$mem + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample185 = (((state.fixedFlag$sample39 && state.fixedFlag$sample57) && state.fixedFlag$sample95) && state.fixedFlag$sample147);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample185[i$var174]);
			state.logProbability$mem = (state.logProbability$mem + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample190() {
		if(!state.fixedProbFlag$sample190) {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1) {
				double var187 = state.pageFaultsVar[state.st[i$var174]];
				double cv$distributionAccumulator = ((0.0 < var187)?(DistributionSampling.logProbabilityGaussian(((state.pageFaults[i$var174] - state.pageFaultsMean[state.st[i$var174]]) / Math.sqrt(var187))) - (Math.log(var187) * 0.5)):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample190[i$var174] = cv$distributionAccumulator;
			}
			state.logProbability$pageFaults = (state.logProbability$pageFaults + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample190 = (((state.fixedFlag$sample39 && state.fixedFlag$sample57) && state.fixedFlag$sample113) && state.fixedFlag$sample164);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample190[i$var174]);
			state.logProbability$pageFaults = (state.logProbability$pageFaults + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample30() {
		if(!state.fixedProbFlag$sample30) {
			double cv$sampleAccumulator = 0.0;
			for(int var29 = 0; var29 < state.noStates; var29 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(state.m[var29], state.v, state.noStates));
			state.logProbability$var30 = cv$sampleAccumulator;
			state.logProbability$m = (state.logProbability$m + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample30)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample30 = state.fixedFlag$sample30;
		} else {
			state.logProbability$m = (state.logProbability$m + state.logProbability$var30);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var30);
			if(state.fixedFlag$sample30)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var30);
		}
	}

	private final void logProbabilityValue$sample36() {
		if(!state.fixedProbFlag$sample36) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(state.initialStateDistribution, state.v, state.noStates);
			state.logProbability$initialStateDistribution = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample36)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample36 = state.fixedFlag$sample36;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$initialStateDistribution);
			if(state.fixedFlag$sample36)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$initialStateDistribution);
		}
	}

	private final void logProbabilityValue$sample39() {
		if(!state.fixedProbFlag$sample39) {
			int cv$sampleValue = state.st[0];
			double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[cv$sampleValue])) && (state.initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(state.initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			state.logProbability$var38 = cv$distributionAccumulator;
			state.logProbability$st = (state.logProbability$st + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample39)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample39 = (state.fixedFlag$sample39 && state.fixedFlag$sample36);
		} else {
			state.logProbability$st = (state.logProbability$st + state.logProbability$var38);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var38);
			if(state.fixedFlag$sample39)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var38);
		}
	}

	private final void logProbabilityValue$sample57() {
		if(!state.fixedProbFlag$sample57) {
			double cv$accumulator = 0.0;
			for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1) {
				int cv$sampleValue = state.st[i$var50];
				double[] var54 = state.m[state.st[(i$var50 - 1)]];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var54[cv$sampleValue])) && (var54[cv$sampleValue] <= 1.0))?Math.log(var54[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample57[(i$var50 - 1)] = cv$distributionAccumulator;
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample57 = ((state.fixedFlag$sample57 && state.fixedFlag$sample30) && state.fixedFlag$sample39);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample57[(i$var50 - 1)]);
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample77() {
		if(!state.fixedProbFlag$sample77) {
			double cv$sampleAccumulator = 0.0;
			for(int var75 = 0; var75 < state.noStates; var75 += 1)
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian(((state.cpuMean[var75] - 16.0) / 2.932575659723036))) - 1.075881101629731);
			state.logProbability$var76 = cv$sampleAccumulator;
			state.logProbability$cpuMean = (state.logProbability$cpuMean + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample77)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample77 = state.fixedFlag$sample77;
		} else {
			state.logProbability$cpuMean = (state.logProbability$cpuMean + state.logProbability$var76);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var76);
			if(state.fixedFlag$sample77)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var76);
		}
	}

	private final void logProbabilityValue$sample95() {
		if(!state.fixedProbFlag$sample95) {
			double cv$sampleAccumulator = 0.0;
			for(int var93 = 0; var93 < state.noStates; var93 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian((state.memMean[var93] - 94.0)));
			state.logProbability$var94 = cv$sampleAccumulator;
			state.logProbability$memMean = (state.logProbability$memMean + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample95)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample95 = state.fixedFlag$sample95;
		} else {
			state.logProbability$memMean = (state.logProbability$memMean + state.logProbability$var94);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var94);
			if(state.fixedFlag$sample95)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var94);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample30)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, state.m[var29]);
				}
			);

		if(!state.fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		if(!state.fixedFlag$sample39)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
		if(!state.fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1)
				state.st[i$var50] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var50 - 1)]], state.noStates);
		}
		if(!state.fixedFlag$sample77)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
							state.cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		if(!state.fixedFlag$sample95)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
							state.memMean[var93] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		if(!state.fixedFlag$sample113)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
							state.pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		if(!state.fixedFlag$sample130)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
							state.cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		if(!state.fixedFlag$sample147)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
							state.memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		if(!state.fixedFlag$sample164)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
							state.pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$i$var174, int forEnd$i$var174, int threadID$i$var174, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var174 = forStart$i$var174; i$var174 < forEnd$i$var174; i$var174 += 1) {
						state.cpu[i$var174] = ((Math.sqrt(state.cpuVar[state.st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$1)) + state.cpuMean[state.st[i$var174]]);
						state.mem[i$var174] = ((Math.sqrt(state.memVar[state.st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$1)) + state.memMean[state.st[i$var174]]);
						state.pageFaults[i$var174] = ((Math.sqrt(state.pageFaultsVar[state.st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$1)) + state.pageFaultsMean[state.st[i$var174]]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample30)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, state.m[var29]);
				}
			);

		if(!state.fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		if(!state.fixedFlag$sample39) {
			for(int index$var37 = 0; index$var37 < state.noStates; index$var37 += 1)
				state.distribution$sample39[index$var37] = (((0.0 <= state.initialStateDistribution[index$var37]) && (state.initialStateDistribution[index$var37] <= 1.0))?state.initialStateDistribution[index$var37]:0.0);
		}
		if(!state.fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1) {
				double[] cv$distribution$sample57 = state.distribution$sample57[(i$var50 - 1)];
				for(int index$var55 = 0; index$var55 < state.noStates; index$var55 += 1)
					cv$distribution$sample57[index$var55] = 0.0;
				if((1 == i$var50)) {
					if(state.fixedFlag$sample39) {
						int var29 = state.st[0];
						if(((0 <= var29) && (var29 < state.noStates))) {
							double[] var54 = state.m[state.st[0]];
							for(int index$var55 = 0; index$var55 < state.noStates; index$var55 += 1)
								cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (((0.0 <= var54[index$var55]) && (var54[index$var55] <= 1.0))?var54[index$var55]:0.0));
						}
					} else {
						for(int index$sample39$2 = 0; index$sample39$2 < state.noStates; index$sample39$2 += 1) {
							double cv$probabilitySample39Value3 = state.distribution$sample39[index$sample39$2];
							double[] var54 = state.m[index$sample39$2];
							for(int index$var55 = 0; index$var55 < state.noStates; index$var55 += 1)
								cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (cv$probabilitySample39Value3 * (((0.0 <= var54[index$var55]) && (var54[index$var55] <= 1.0))?var54[index$var55]:0.0)));
						}
					}
				}
				int index$i$9 = (i$var50 - 1);
				if((1 <= index$i$9)) {
					for(int index$sample57$10 = 0; index$sample57$10 < state.noStates; index$sample57$10 += 1) {
						double cv$probabilitySample57Value11 = state.distribution$sample57[(index$i$9 - 1)][index$sample57$10];
						double[] var54 = state.m[index$sample57$10];
						for(int index$var55 = 0; index$var55 < state.noStates; index$var55 += 1)
							cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] + (cv$probabilitySample57Value11 * (((0.0 <= var54[index$var55]) && (var54[index$var55] <= 1.0))?var54[index$var55]:0.0)));
					}
				}
				double cv$var55$sum = 0.0;
				for(int index$var55 = 0; index$var55 < state.noStates; index$var55 += 1)
					cv$var55$sum = (cv$var55$sum + cv$distribution$sample57[index$var55]);
				for(int index$var55 = 0; index$var55 < state.noStates; index$var55 += 1)
					cv$distribution$sample57[index$var55] = (cv$distribution$sample57[index$var55] / cv$var55$sum);
			}
		}
		if(!state.fixedFlag$sample77)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
							state.cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		if(!state.fixedFlag$sample95)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
							state.memMean[var93] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		if(!state.fixedFlag$sample113)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
							state.pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		if(!state.fixedFlag$sample130)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
							state.cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		if(!state.fixedFlag$sample147)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
							state.memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		if(!state.fixedFlag$sample164)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
							state.pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample30)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, state.m[var29]);
				}
			);

		if(!state.fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		if(!state.fixedFlag$sample39)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
		if(!state.fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1)
				state.st[i$var50] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var50 - 1)]], state.noStates);
		}
		if(!state.fixedFlag$sample77)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
							state.cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		if(!state.fixedFlag$sample95)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
							state.memMean[var93] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		if(!state.fixedFlag$sample113)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
							state.pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		if(!state.fixedFlag$sample130)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
							state.cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		if(!state.fixedFlag$sample147)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
							state.memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		if(!state.fixedFlag$sample164)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
							state.pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$i$var174, int forEnd$i$var174, int threadID$i$var174, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var174 = forStart$i$var174; i$var174 < forEnd$i$var174; i$var174 += 1) {
						state.cpu[i$var174] = ((Math.sqrt(state.cpuVar[state.st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$1)) + state.cpuMean[state.st[i$var174]]);
						state.mem[i$var174] = ((Math.sqrt(state.memVar[state.st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$1)) + state.memMean[state.st[i$var174]]);
						state.pageFaults[i$var174] = ((Math.sqrt(state.pageFaultsVar[state.st[i$var174]]) * DistributionSampling.sampleGaussian(RNG$1)) + state.pageFaultsMean[state.st[i$var174]]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample30)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, state.m[var29]);
				}
			);

		if(!state.fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		if(!state.fixedFlag$sample39)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
		if(!state.fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1)
				state.st[i$var50] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var50 - 1)]], state.noStates);
		}
		if(!state.fixedFlag$sample77)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
							state.cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		if(!state.fixedFlag$sample95)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
							state.memMean[var93] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		if(!state.fixedFlag$sample113)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
							state.pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		if(!state.fixedFlag$sample130)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
							state.cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		if(!state.fixedFlag$sample147)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
							state.memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		if(!state.fixedFlag$sample164)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
							state.pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample30)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, state.m[var29]);
				}
			);

		if(!state.fixedFlag$sample36)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		if(!state.fixedFlag$sample39)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
		if(!state.fixedFlag$sample57) {
			for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1)
				state.st[i$var50] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var50 - 1)]], state.noStates);
		}
		if(!state.fixedFlag$sample77)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
							state.cpuMean[var75] = ((DistributionSampling.sampleGaussian(RNG$1) * 2.932575659723036) + 16.0);
				}
			);

		if(!state.fixedFlag$sample95)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
							state.memMean[var93] = (DistributionSampling.sampleGaussian(RNG$1) + 94.0);
				}
			);

		if(!state.fixedFlag$sample113)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
							state.pageFaultsMean[var111] = ((DistributionSampling.sampleGaussian(RNG$1) * 579.2667779184303) + 814.0);
				}
			);

		if(!state.fixedFlag$sample130)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
							state.cpuVar[var128] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		if(!state.fixedFlag$sample147)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
							state.memVar[var145] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

		if(!state.fixedFlag$sample164)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
							state.pageFaultsVar[var162] = DistributionSampling.sampleInverseGamma(RNG$1, 5.0, 0.5);
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample30)
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
								inferSample30(var29, threadID$var29, RNG$1);
					}
				);

			if(!state.fixedFlag$sample36)
				inferSample36();
			if(!state.fixedFlag$sample39)
				inferSample39();
			if(!state.fixedFlag$sample57) {
				for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1)
					inferSample57(i$var50);
			}
			if(!state.fixedFlag$sample77)
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
								inferSample77(var75, threadID$var75, RNG$1);
					}
				);

			if(!state.fixedFlag$sample95)
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
								inferSample95(var93, threadID$var93, RNG$1);
					}
				);

			if(!state.fixedFlag$sample113)
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
								inferSample113(var111, threadID$var111, RNG$1);
					}
				);

			if(!state.fixedFlag$sample130)
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
								inferSample130(var128, threadID$var128, RNG$1);
					}
				);

			if(!state.fixedFlag$sample147)
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
								inferSample147(var145, threadID$var145, RNG$1);
					}
				);

			if(!state.fixedFlag$sample164)
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
								inferSample164(var162, threadID$var162, RNG$1);
					}
				);

		} else {
			if(!state.fixedFlag$sample164)
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1)
								inferSample164(var162, threadID$var162, RNG$1);
					}
				);

			if(!state.fixedFlag$sample147)
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1)
								inferSample147(var145, threadID$var145, RNG$1);
					}
				);

			if(!state.fixedFlag$sample130)
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1)
								inferSample130(var128, threadID$var128, RNG$1);
					}
				);

			if(!state.fixedFlag$sample113)
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1)
								inferSample113(var111, threadID$var111, RNG$1);
					}
				);

			if(!state.fixedFlag$sample95)
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1)
								inferSample95(var93, threadID$var93, RNG$1);
					}
				);

			if(!state.fixedFlag$sample77)
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
								inferSample77(var75, threadID$var75, RNG$1);
					}
				);

			if(!state.fixedFlag$sample57) {
				for(int i$var50 = (state.samples - 1); i$var50 >= 1; i$var50 -= 1)
					inferSample57(i$var50);
			}
			if(!state.fixedFlag$sample39)
				inferSample39();
			if(!state.fixedFlag$sample36)
				inferSample36();
			if(!state.fixedFlag$sample30)
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
								inferSample30(var29, threadID$var29, RNG$1);
					}
				);

		}
		state.system$gibbsForward = !state.system$gibbsForward;
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						if(!state.constrainedFlag$sample30[var29])
							drawValueSample30(var29, threadID$var29, RNG$1);
					}
			}
		);
		if(!state.constrainedFlag$sample36)
			drawValueSample36();
		if(!state.constrainedFlag$sample39)
			drawValueSample39();
		for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1) {
			if(!state.constrainedFlag$sample57[(i$var50 - 1)])
				drawValueSample57(i$var50);
		}
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1) {
						if(!state.constrainedFlag$sample77[var75])
							drawValueSample77(var75, threadID$var75, RNG$1);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var93, int forEnd$var93, int threadID$var93, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var93 = forStart$var93; var93 < forEnd$var93; var93 += 1) {
						if(!state.constrainedFlag$sample95[var93])
							drawValueSample95(var93, threadID$var93, RNG$1);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var111, int forEnd$var111, int threadID$var111, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var111 = forStart$var111; var111 < forEnd$var111; var111 += 1) {
						if(!state.constrainedFlag$sample113[var111])
							drawValueSample113(var111, threadID$var111, RNG$1);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var128, int forEnd$var128, int threadID$var128, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var128 = forStart$var128; var128 < forEnd$var128; var128 += 1) {
						if(!state.constrainedFlag$sample130[var128])
							drawValueSample130(var128, threadID$var128, RNG$1);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var145, int forEnd$var145, int threadID$var145, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var145 = forStart$var145; var145 < forEnd$var145; var145 += 1) {
						if(!state.constrainedFlag$sample147[var145])
							drawValueSample147(var145, threadID$var145, RNG$1);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var162, int forEnd$var162, int threadID$var162, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var162 = forStart$var162; var162 < forEnd$var162; var162 += 1) {
						if(!state.constrainedFlag$sample164[var162])
							drawValueSample164(var162, threadID$var162, RNG$1);
					}
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$m = 0.0;
		if(!state.fixedProbFlag$sample30)
			state.logProbability$var30 = Double.NaN;
		if(!state.fixedProbFlag$sample36)
			state.logProbability$initialStateDistribution = Double.NaN;
		state.logProbability$st = 0.0;
		if(!state.fixedProbFlag$sample39)
			state.logProbability$var38 = Double.NaN;
		if(!state.fixedProbFlag$sample57) {
			for(int i$var50 = 1; i$var50 < state.samples; i$var50 += 1)
				state.logProbability$sample57[(i$var50 - 1)] = Double.NaN;
		}
		state.logProbability$cpuMean = 0.0;
		if(!state.fixedProbFlag$sample77)
			state.logProbability$var76 = Double.NaN;
		state.logProbability$memMean = 0.0;
		if(!state.fixedProbFlag$sample95)
			state.logProbability$var94 = Double.NaN;
		state.logProbability$pageFaultsMean = 0.0;
		if(!state.fixedProbFlag$sample113)
			state.logProbability$var112 = Double.NaN;
		state.logProbability$cpuVar = 0.0;
		if(!state.fixedProbFlag$sample130)
			state.logProbability$var129 = Double.NaN;
		state.logProbability$memVar = 0.0;
		if(!state.fixedProbFlag$sample147)
			state.logProbability$var146 = Double.NaN;
		state.logProbability$pageFaultsVar = 0.0;
		if(!state.fixedProbFlag$sample164)
			state.logProbability$var163 = Double.NaN;
		state.logProbability$cpu = 0.0;
		if(!state.fixedProbFlag$sample180) {
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1)
				state.logProbability$sample180[i$var174] = Double.NaN;
		}
		state.logProbability$mem = 0.0;
		if(!state.fixedProbFlag$sample185) {
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1)
				state.logProbability$sample185[i$var174] = Double.NaN;
		}
		state.logProbability$pageFaults = 0.0;
		if(!state.fixedProbFlag$sample190) {
			for(int i$var174 = 0; i$var174 < state.samples; i$var174 += 1)
				state.logProbability$sample190[i$var174] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		for(int var15 = 0; var15 < state.noStates; var15 += 1)
			state.v[var15] = 0.1;
		state.samples = state.length$cpu_measured;
		for(int index$constrainedFlag$sample95$1 = 0; index$constrainedFlag$sample95$1 < state.constrainedFlag$sample95.length; index$constrainedFlag$sample95$1 += 1)
			state.constrainedFlag$sample95[index$constrainedFlag$sample95$1] = true;
		for(int index$constrainedFlag$sample30$1 = 0; index$constrainedFlag$sample30$1 < state.constrainedFlag$sample30.length; index$constrainedFlag$sample30$1 += 1)
			state.constrainedFlag$sample30[index$constrainedFlag$sample30$1] = true;
		for(int index$constrainedFlag$sample77$1 = 0; index$constrainedFlag$sample77$1 < state.constrainedFlag$sample77.length; index$constrainedFlag$sample77$1 += 1)
			state.constrainedFlag$sample77[index$constrainedFlag$sample77$1] = true;
		for(int index$constrainedFlag$sample57$1 = 0; index$constrainedFlag$sample57$1 < state.constrainedFlag$sample57.length; index$constrainedFlag$sample57$1 += 1)
			state.constrainedFlag$sample57[index$constrainedFlag$sample57$1] = true;
		for(int index$constrainedFlag$sample164$1 = 0; index$constrainedFlag$sample164$1 < state.constrainedFlag$sample164.length; index$constrainedFlag$sample164$1 += 1)
			state.constrainedFlag$sample164[index$constrainedFlag$sample164$1] = true;
		for(int index$constrainedFlag$sample147$1 = 0; index$constrainedFlag$sample147$1 < state.constrainedFlag$sample147.length; index$constrainedFlag$sample147$1 += 1)
			state.constrainedFlag$sample147[index$constrainedFlag$sample147$1] = true;
		for(int index$constrainedFlag$sample130$1 = 0; index$constrainedFlag$sample130$1 < state.constrainedFlag$sample130.length; index$constrainedFlag$sample130$1 += 1)
			state.constrainedFlag$sample130[index$constrainedFlag$sample130$1] = true;
		for(int index$constrainedFlag$sample113$1 = 0; index$constrainedFlag$sample113$1 < state.constrainedFlag$sample113.length; index$constrainedFlag$sample113$1 += 1)
			state.constrainedFlag$sample113[index$constrainedFlag$sample113$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample30)
			logProbabilityValue$sample30();
		if(state.fixedFlag$sample36)
			logProbabilityValue$sample36();
		if(state.fixedFlag$sample77)
			logProbabilityValue$sample77();
		if(state.fixedFlag$sample95)
			logProbabilityValue$sample95();
		if(state.fixedFlag$sample113)
			logProbabilityValue$sample113();
		if(state.fixedFlag$sample130)
			logProbabilityValue$sample130();
		if(state.fixedFlag$sample147)
			logProbabilityValue$sample147();
		if(state.fixedFlag$sample164)
			logProbabilityValue$sample164();
		logProbabilityValue$sample180();
		logProbabilityValue$sample185();
		logProbabilityValue$sample190();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample30();
		logProbabilityValue$sample36();
		logProbabilityDistribution$sample39();
		logProbabilityDistribution$sample57();
		logProbabilityValue$sample77();
		logProbabilityValue$sample95();
		logProbabilityValue$sample113();
		logProbabilityValue$sample130();
		logProbabilityValue$sample147();
		logProbabilityValue$sample164();
		logProbabilityDistribution$sample180();
		logProbabilityDistribution$sample185();
		logProbabilityDistribution$sample190();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample30();
		logProbabilityValue$sample36();
		logProbabilityValue$sample39();
		logProbabilityValue$sample57();
		logProbabilityValue$sample77();
		logProbabilityValue$sample95();
		logProbabilityValue$sample113();
		logProbabilityValue$sample130();
		logProbabilityValue$sample147();
		logProbabilityValue$sample164();
		logProbabilityValue$sample180();
		logProbabilityValue$sample185();
		logProbabilityValue$sample190();
	}

	@Override
	public final void propagateObservedValues() {
		{
			int cv$length1 = state.cpu.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				state.cpu[cv$index1] = state.cpu_measured[cv$index1];
		}
		{
			int cv$length1 = state.mem.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				state.mem[cv$index1] = state.mem_measured[cv$index1];
		}
		int cv$length1 = state.pageFaults.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.pageFaults[cv$index1] = state.pageFaults_measured[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model HMMMetrics(double[] cpu_measured, double[] mem_measured, double[] pageFaults_measured, int noStates) {\n"
		     + "    \n"
		     + "    // Construct vectors describing the probability of a move from 1 state to another.\n"
		     + "    double[] v = new double[noStates] <~ 0.1;\n"
		     + "    double[][] m = dirichlet(v).sample(noStates);\n"
		     + "    \n"
		     + "    // Determine how many samples the model will need to produce.\n"
		     + "    int samples = cpu_measured.length;\n"
		     + "    \n"
		     + "    // Allocate space for the state.\n"
		     + "    int[] st = new int[samples];\n"
		     + "\n"
		     + "    // Set the initial state by sampling from a categorical with learnt weightings.\n"
		     + "    double[] initialStateDistribution = dirichlet(v).sample();\n"
		     + "    st[0] = categorical(initialStateDistribution).sampleDistribution();\n"
		     + "\n"
		     + "    //Determine the remaining states based on the previous state.\n"
		     + "    for(int i:[1 .. samples))\n"
		     + "        st[i] = categorical(m[st[i-1]]).sampleDistribution();\n"
		     + "        \n"
		     + "    //Generate each metric.\n"
		     + "    double[] cpu = new double[samples];\n"
		     + "    double[] mem = new double[samples];\n"
		     + "    double[] pageFaults = new double[samples];\n"
		     + "    \n"
		     + "    double[] cpuMean = gaussian(16, 8.6).sample(noStates);\n"
		     + "    double[] memMean = gaussian(94, 1).sample(noStates);\n"
		     + "    double[] pageFaultsMean = gaussian(814, 335550).sample(noStates);\n"
		     + "    \n"
		     + "    double[] cpuVar = inverseGamma(5, 0.5).sample(noStates);\n"
		     + "    double[] memVar = inverseGamma(5, 0.5).sample(noStates);\n"
		     + "    double[] pageFaultsVar = inverseGamma(5, 0.5).sample(noStates);\n"
		     + "    \n"
		     + "    for(int i:[0 .. samples)) {\n"
		     + "        int s = st[i];\n"
		     + "        cpu[i] = gaussian(cpuMean[s], cpuVar[s]).sample();\n"
		     + "        mem[i] = gaussian(memMean[s], memVar[s]).sample();\n"
		     + "        pageFaults[i] = gaussian(pageFaultsMean[s], pageFaultsVar[s]).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    //Tie the values to the values we have measured.\n"
		     + "    cpu.observe(cpu_measured);\n"
		     + "    mem.observe(mem_measured);\n"
		     + "    pageFaults.observe(pageFaults_measured);\n"
		     + "}\n"
		     + "";
	}
}