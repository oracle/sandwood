package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMMetrics4$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMMetrics4.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMMetrics4$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$distributionAccumulator$var73;
		double[] cv$var20$countGlobal;
		double[] cv$var33$countGlobal;
		double[] cv$var55$stateProbabilityGlobal;
		double[] cv$var74$stateProbabilityGlobal;
		boolean[][][] guard$sample57gaussian255$global;
		boolean[][][] guard$sample76gaussian255$global;

		@Override
		public final void allocateScratch() {
			cv$var20$countGlobal = new double[state.noStates];
			cv$var33$countGlobal = new double[state.noStates];
			cv$distributionAccumulator$var73 = new double[state.noStates];
			cv$var55$stateProbabilityGlobal = new double[state.noStates];
			{
				int cv$max_server = 0;
				int cv$max_timeStep$var226 = 0;
				for(int sample$var196 = 0; sample$var196 < state.length$metric.length; sample$var196 += 1) {
					if((0 < state.length$metric[0].length))
						cv$max_timeStep$var226 = Math.max(cv$max_timeStep$var226, state.length$metric[sample$var196][0]);
					cv$max_server = Math.max(cv$max_server, state.length$metric[0].length);
				}
				guard$sample57gaussian255$global = new boolean[state.length$metric.length][cv$max_server][cv$max_timeStep$var226];
			}
			cv$var74$stateProbabilityGlobal = new double[state.noStates];
			int cv$max_server = 0;
			int cv$max_timeStep$var226 = 0;
			for(int sample$var196 = 0; sample$var196 < state.length$metric.length; sample$var196 += 1) {
				if((0 < state.length$metric[0].length))
					cv$max_timeStep$var226 = Math.max(cv$max_timeStep$var226, state.length$metric[sample$var196][0]);
				cv$max_server = Math.max(cv$max_server, state.length$metric[0].length);
			}
			guard$sample76gaussian255$global = new boolean[state.length$metric.length][cv$max_server][cv$max_timeStep$var226];
		}
	}


	public HMMMetrics4$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample134(int var119, int var129) {
		state.current_metric_mean[var119][var129] = ((double)state.max_metric * DistributionSampling.sampleUniform(state.RNG$));
	}

	private final void drawValueSample162(int var146, int var156) {
		state.current_metric_var[var146][var156] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
	}

	private final void drawValueSample190(int var173, int var183) {
		state.current_metric_valid_bias[var173][var183] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void drawValueSample20() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
	}

	private final void drawValueSample33(int var32) {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var32]);
	}

	private final void drawValueSample57(int sample$var45) {
		state.st[sample$var45][0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
	}

	private final void drawValueSample76(int sample$var45, int timeStep$var66) {
		state.st[sample$var45][timeStep$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample$var45][(timeStep$var66 - 1)]], state.noStates);
	}

	private final void inferSample134(int var119, int var129) {
		state.constrainedFlag$sample134[var119][var129] = false;
		double cv$originalValue = state.current_metric_mean[var119][var129];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue < (double)state.max_metric))?(-Math.log(state.max_metric)):Double.NEGATIVE_INFINITY);
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				if(((0 < state.length$metric[sample$var196][0]) && state.metric_valid_g[sample$var196][var119][0])) {
					if(state.fixedFlag$sample57) {
						if((var129 == state.st[sample$var196][0])) {
							state.constrainedFlag$sample134[var119][var129] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var156 = state.st[sample$var196][0];
							if(((0 <= var156) && (var156 < state.noStates))) {
								double var243 = state.current_metric_var[var119][state.st[sample$var196][0]];
								cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var119][0] - cv$originalValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
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
						double cv$probabilitySample57Value8 = state.distribution$sample57[sample$var196][var129];
						state.constrainedFlag$sample134[var119][var129] = true;
						double var243 = state.current_metric_var[var119][var129];
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var119][0] - cv$originalValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value8), 0.0);
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
			}
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
					if(state.metric_valid_g[sample$var196][var119][timeStep$var226]) {
						if(state.fixedFlag$sample76) {
							if((var129 == state.st[sample$var196][timeStep$var226])) {
								state.constrainedFlag$sample134[var119][var129] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var156 = state.st[sample$var196][timeStep$var226];
								if(((0 <= var156) && (var156 < state.noStates))) {
									double var243 = state.current_metric_var[var119][state.st[sample$var196][timeStep$var226]];
									cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var119][timeStep$var226] - cv$originalValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
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
							double cv$probabilitySample76Value20 = state.distribution$sample76[sample$var196][(timeStep$var226 - 1)][var129];
							state.constrainedFlag$sample134[var119][var129] = true;
							double var243 = state.current_metric_var[var119][var129];
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var119][timeStep$var226] - cv$originalValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample76Value20), 0.0);
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
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample134[var119][var129]) {
			state.current_metric_mean[var119][var129] = cv$proposedValue;
			double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue < (double)state.max_metric))?(-Math.log(state.max_metric)):Double.NEGATIVE_INFINITY);
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				if(((0 < state.length$metric[sample$var196][0]) && state.metric_valid_g[sample$var196][var119][0])) {
					if(state.fixedFlag$sample57) {
						if((var129 == state.st[sample$var196][0])) {
							state.constrainedFlag$sample134[var119][var129] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var156 = state.st[sample$var196][0];
							if(((0 <= var156) && (var156 < state.noStates))) {
								double var243 = state.current_metric_var[var119][state.st[sample$var196][0]];
								cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var119][0] - cv$proposedValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
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
						double cv$probabilitySample57Value8 = state.distribution$sample57[sample$var196][var129];
						state.constrainedFlag$sample134[var119][var129] = true;
						double var243 = state.current_metric_var[var119][var129];
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var119][0] - cv$proposedValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value8), 0.0);
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
			}
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
					if(state.metric_valid_g[sample$var196][var119][timeStep$var226]) {
						if(state.fixedFlag$sample76) {
							if((var129 == state.st[sample$var196][timeStep$var226])) {
								state.constrainedFlag$sample134[var119][var129] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var156 = state.st[sample$var196][timeStep$var226];
								if(((0 <= var156) && (var156 < state.noStates))) {
									double var243 = state.current_metric_var[var119][state.st[sample$var196][timeStep$var226]];
									cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var119][timeStep$var226] - cv$proposedValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
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
							double cv$probabilitySample76Value20 = state.distribution$sample76[sample$var196][(timeStep$var226 - 1)][var129];
							state.constrainedFlag$sample134[var119][var129] = true;
							double var243 = state.current_metric_var[var119][var129];
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var119][timeStep$var226] - cv$proposedValue) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample76Value20), 0.0);
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
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio)))
				state.current_metric_mean[var119][var129] = cv$originalValue;
		}
	}

	private final void inferSample162(int var146, int var156) {
		state.constrainedFlag$sample162[var146][var156] = false;
		double cv$originalValue = state.current_metric_var[var146][var156];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 1.0, 1.0);
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				if(((0 < state.length$metric[sample$var196][0]) && state.metric_valid_g[sample$var196][var146][0])) {
					if(state.fixedFlag$sample57) {
						if((var156 == state.st[sample$var196][0])) {
							state.constrainedFlag$sample162[var146][var156] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var129 = state.st[sample$var196][0];
							if(((0 <= var129) && (var129 < state.noStates))) {
								cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var146][0] - state.current_metric_mean[var146][state.st[sample$var196][0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
						double cv$probabilitySample57Value8 = state.distribution$sample57[sample$var196][var156];
						state.constrainedFlag$sample162[var146][var156] = true;
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var146][0] - state.current_metric_mean[var146][var156]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value8), 0.0);
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
			}
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
					if(state.metric_valid_g[sample$var196][var146][timeStep$var226]) {
						if(state.fixedFlag$sample76) {
							if((var156 == state.st[sample$var196][timeStep$var226])) {
								state.constrainedFlag$sample162[var146][var156] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var129 = state.st[sample$var196][timeStep$var226];
								if(((0 <= var129) && (var129 < state.noStates))) {
									cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var146][timeStep$var226] - state.current_metric_mean[var146][state.st[sample$var196][timeStep$var226]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
							double cv$probabilitySample76Value20 = state.distribution$sample76[sample$var196][(timeStep$var226 - 1)][var156];
							state.constrainedFlag$sample162[var146][var156] = true;
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var146][timeStep$var226] - state.current_metric_mean[var146][var156]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample76Value20), 0.0);
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
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample162[var146][var156]) {
			state.current_metric_var[var146][var156] = cv$proposedValue;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 1.0, 1.0);
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				if(((0 < state.length$metric[sample$var196][0]) && state.metric_valid_g[sample$var196][var146][0])) {
					if(state.fixedFlag$sample57) {
						if((var156 == state.st[sample$var196][0])) {
							state.constrainedFlag$sample162[var146][var156] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var129 = state.st[sample$var196][0];
							if(((0 <= var129) && (var129 < state.noStates))) {
								cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var146][0] - state.current_metric_mean[var146][state.st[sample$var196][0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
						double cv$probabilitySample57Value8 = state.distribution$sample57[sample$var196][var156];
						state.constrainedFlag$sample162[var146][var156] = true;
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value8) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var146][0] - state.current_metric_mean[var146][var156]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample57Value8), 0.0);
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
			}
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
					if(state.metric_valid_g[sample$var196][var146][timeStep$var226]) {
						if(state.fixedFlag$sample76) {
							if((var156 == state.st[sample$var196][timeStep$var226])) {
								state.constrainedFlag$sample162[var146][var156] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var129 = state.st[sample$var196][timeStep$var226];
								if(((0 <= var129) && (var129 < state.noStates))) {
									cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var146][timeStep$var226] - state.current_metric_mean[var146][state.st[sample$var196][timeStep$var226]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
							double cv$probabilitySample76Value20 = state.distribution$sample76[sample$var196][(timeStep$var226 - 1)][var156];
							state.constrainedFlag$sample162[var146][var156] = true;
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value20) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][var146][timeStep$var226] - state.current_metric_mean[var146][var156]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample76Value20), 0.0);
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
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio)))
				state.current_metric_var[var146][var156] = cv$originalValue;
		}
	}

	private final void inferSample190(int var173, int var183) {
		state.constrainedFlag$sample190[var173][var183] = false;
		double cv$sum = 0.0;
		double cv$count = 0.0;
		for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
			if((0 < state.length$metric[sample$var196][0])) {
				if(state.fixedFlag$sample57) {
					if((var183 == state.st[sample$var196][0])) {
						state.constrainedFlag$sample190[var173][var183] = true;
						cv$count = (cv$count + 1.0);
						if(state.metric_valid_g[sample$var196][var173][0])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample57Value7 = state.distribution$sample57[sample$var196][var183];
					state.constrainedFlag$sample190[var173][var183] = true;
					cv$count = (cv$count + cv$probabilitySample57Value7);
					if(state.metric_valid_g[sample$var196][var173][0])
						cv$sum = (cv$sum + cv$probabilitySample57Value7);
				}
			}
		}
		for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
			for(int timeStep$var226 = 1; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
				if(state.fixedFlag$sample76) {
					if((var183 == state.st[sample$var196][timeStep$var226])) {
						state.constrainedFlag$sample190[var173][var183] = true;
						cv$count = (cv$count + 1.0);
						if(state.metric_valid_g[sample$var196][var173][timeStep$var226])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample76Value19 = state.distribution$sample76[sample$var196][(timeStep$var226 - 1)][var183];
					state.constrainedFlag$sample190[var173][var183] = true;
					cv$count = (cv$count + cv$probabilitySample76Value19);
					if(state.metric_valid_g[sample$var196][var173][timeStep$var226])
						cv$sum = (cv$sum + cv$probabilitySample76Value19);
				}
			}
		}
		if(state.constrainedFlag$sample190[var173][var183])
			state.current_metric_valid_bias[var173][var183] = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void inferSample20() {
		state.constrainedFlag$sample20 = false;
		for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
			scratch.cv$var20$countGlobal[cv$loopIndex] = 0.0;
		if(state.fixedFlag$sample57) {
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				state.constrainedFlag$sample20 = true;
				scratch.cv$var20$countGlobal[state.st[sample$var45][0]] = (scratch.cv$var20$countGlobal[state.st[sample$var45][0]] + 1.0);
			}
		} else {
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
					scratch.cv$var20$countGlobal[cv$loopIndex] = (scratch.cv$var20$countGlobal[cv$loopIndex] + state.distribution$sample57[sample$var45][cv$loopIndex]);
			}
		}
		if(state.constrainedFlag$sample20)
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, scratch.cv$var20$countGlobal, state.initialStateDistribution, state.noStates);
	}

	private final void inferSample33(int var32) {
		state.constrainedFlag$sample33[var32] = false;
		for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
			scratch.cv$var33$countGlobal[cv$loopIndex] = 0.0;
		if(state.fixedFlag$sample76) {
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				if((1 < state.length$metric[sample$var45][0])) {
					if(state.fixedFlag$sample57) {
						if((var32 == state.st[sample$var45][0])) {
							state.constrainedFlag$sample33[var32] = true;
							scratch.cv$var33$countGlobal[state.st[sample$var45][1]] = (scratch.cv$var33$countGlobal[state.st[sample$var45][1]] + 1.0);
						}
					} else {
						state.constrainedFlag$sample33[var32] = true;
						scratch.cv$var33$countGlobal[state.st[sample$var45][1]] = (scratch.cv$var33$countGlobal[state.st[sample$var45][1]] + state.distribution$sample57[sample$var45][var32]);
					}
				}
			}
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 2; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
					if((var32 == state.st[sample$var45][(timeStep$var66 - 1)])) {
						state.constrainedFlag$sample33[var32] = true;
						scratch.cv$var33$countGlobal[state.st[sample$var45][timeStep$var66]] = (scratch.cv$var33$countGlobal[state.st[sample$var45][timeStep$var66]] + 1.0);
					}
				}
			}
		} else {
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				if((1 < state.length$metric[sample$var45][0])) {
					if(state.fixedFlag$sample57) {
						if((var32 == state.st[sample$var45][0])) {
							for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
								scratch.cv$var33$countGlobal[cv$loopIndex] = (scratch.cv$var33$countGlobal[cv$loopIndex] + state.distribution$sample76[sample$var45][0][cv$loopIndex]);
						}
					} else {
						double cv$distributionProbability = state.distribution$sample57[sample$var45][var32];
						for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
							scratch.cv$var33$countGlobal[cv$loopIndex] = (scratch.cv$var33$countGlobal[cv$loopIndex] + (state.distribution$sample76[sample$var45][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
					int index$timeStep$52 = (timeStep$var66 - 1);
					if((1 <= index$timeStep$52)) {
						double cv$distributionProbability = state.distribution$sample76[sample$var45][(index$timeStep$52 - 1)][var32];
						for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
							scratch.cv$var33$countGlobal[cv$loopIndex] = (scratch.cv$var33$countGlobal[cv$loopIndex] + (state.distribution$sample76[sample$var45][(timeStep$var66 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		if(state.constrainedFlag$sample33[var32])
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, scratch.cv$var33$countGlobal, state.m[var32], state.noStates);
	}

	private final void inferSample57(int sample$var45) {
		state.constrainedFlag$sample57[sample$var45] = false;
		int cv$numStates = Math.max(0, state.noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$accumulatedProbabilities = (((((cv$valuePos < state.noStates) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[cv$valuePos])) && (state.initialStateDistribution[cv$valuePos] <= 1.0))?Math.log(state.initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((state.fixedFlag$sample76 && (1 < state.length$metric[sample$var45][0]))) {
				state.constrainedFlag$sample57[sample$var45] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((cv$valuePos < state.noStates)) {
					double[] var72 = state.m[cv$valuePos];
					cv$accumulatedConsumerProbabilities = (((((0.0 <= state.st[sample$var45][1]) && (state.st[sample$var45][1] < state.noStates)) && (0.0 <= var72[state.st[sample$var45][1]])) && (var72[state.st[sample$var45][1]] <= 1.0))?Math.log(var72[state.st[sample$var45][1]]):Double.NEGATIVE_INFINITY);
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
			if((0 < state.length$metric[sample$var45][0])) {
				for(int server = 0; server < state.noServers; server += 1) {
					state.constrainedFlag$sample57[sample$var45] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < state.noStates)) {
						double var230 = state.current_metric_valid_bias[server][cv$valuePos];
						cv$accumulatedConsumerProbabilities = (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var45][server][0]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY);
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
				for(int server = 0; server < state.noServers; server += 1) {
					if(state.metric_valid_g[sample$var45][server][0])
						scratch.guard$sample57gaussian255$global[sample$var45][server][0] = false;
				}
				for(int server = 0; server < state.noServers; server += 1) {
					if(state.metric_valid_g[sample$var45][server][0])
						scratch.guard$sample57gaussian255$global[sample$var45][server][0] = false;
				}
				for(int server = 0; server < state.noServers; server += 1) {
					if((state.metric_valid_g[sample$var45][server][0] && !scratch.guard$sample57gaussian255$global[sample$var45][server][0])) {
						scratch.guard$sample57gaussian255$global[sample$var45][server][0] = true;
						state.constrainedFlag$sample57[sample$var45] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < state.noStates)) {
							double var243 = state.current_metric_var[server][cv$valuePos];
							cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var45][server][0] - state.current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
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
				for(int server = 0; server < state.noServers; server += 1) {
					if((state.metric_valid_g[sample$var45][server][0] && !scratch.guard$sample57gaussian255$global[sample$var45][server][0])) {
						scratch.guard$sample57gaussian255$global[sample$var45][server][0] = true;
						state.constrainedFlag$sample57[sample$var45] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < state.noStates)) {
							double var243 = state.current_metric_var[server][cv$valuePos];
							cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var45][server][0] - state.current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
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
			}
			if((!state.fixedFlag$sample76 && (1 < state.length$metric[sample$var45][0]))) {
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1)
					scratch.cv$distributionAccumulator$var73[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < state.noStates)) {
					cv$reachedDistributionProbability = 1.0;
					DistributionSampling.addProbabilityDistributionCategorical(scratch.cv$distributionAccumulator$var73, 1.0, state.m[cv$valuePos], state.noStates);
				}
				double[] cv$sampleDistribution = state.distribution$sample76[sample$var45][0];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1) {
					double cv$normalisedDistValue = (scratch.cv$distributionAccumulator$var73[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			scratch.cv$var55$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		if(state.constrainedFlag$sample57[sample$var45]) {
			double[] cv$localProbability = state.distribution$sample57[sample$var45];
			double cv$logSum;
			double cv$lseMax = scratch.cv$var55$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = scratch.cv$var55$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var55$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((scratch.cv$var55$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var55$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void inferSample76(int sample$var45, int timeStep$var66) {
		state.constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)] = false;
		int cv$numStates = 0;
		if((1 == timeStep$var66)) {
			if(state.fixedFlag$sample57) {
				int var32 = state.st[sample$var45][0];
				if(((0 <= var32) && (var32 < state.noStates)))
					cv$numStates = Math.max(0, state.noStates);
			} else {
				if((0 < state.noStates))
					cv$numStates = state.noStates;
			}
		}
		if((0 < state.noStates)) {
			int index$timeStep$13 = (timeStep$var66 - 1);
			if(((1 <= index$timeStep$13) && !(index$timeStep$13 == timeStep$var66)))
				cv$numStates = state.noStates;
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == timeStep$var66)) {
				if(state.fixedFlag$sample57) {
					int var32 = state.st[sample$var45][0];
					if(((0 <= var32) && (var32 < state.noStates))) {
						cv$reachedDistributionSourceRV = 1.0;
						double[] var72 = state.m[state.st[sample$var45][0]];
						double cv$accumulatedProbabilities = ((((cv$valuePos < state.noStates) && (0.0 <= var72[cv$valuePos])) && (var72[cv$valuePos] <= 1.0))?Math.log(var72[cv$valuePos]):Double.NEGATIVE_INFINITY);
						if((1 < state.length$metric[sample$var45][0])) {
							for(int server = 0; server < state.noServers; server += 1) {
								state.constrainedFlag$sample76[sample$var45][0] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if((cv$valuePos < state.noStates)) {
									double var230 = state.current_metric_valid_bias[server][cv$valuePos];
									cv$accumulatedConsumerProbabilities = (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var45][server][1]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY);
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
							for(int server = 0; server < state.noServers; server += 1) {
								if(state.metric_valid_g[sample$var45][server][1])
									scratch.guard$sample76gaussian255$global[sample$var45][server][1] = false;
							}
							for(int server = 0; server < state.noServers; server += 1) {
								if(state.metric_valid_g[sample$var45][server][1])
									scratch.guard$sample76gaussian255$global[sample$var45][server][1] = false;
							}
							for(int server = 0; server < state.noServers; server += 1) {
								if((state.metric_valid_g[sample$var45][server][1] && !scratch.guard$sample76gaussian255$global[sample$var45][server][1])) {
									scratch.guard$sample76gaussian255$global[sample$var45][server][1] = true;
									state.constrainedFlag$sample76[sample$var45][0] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((cv$valuePos < state.noStates)) {
										double var243 = state.current_metric_var[server][cv$valuePos];
										cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var45][server][1] - state.current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
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
							for(int server = 0; server < state.noServers; server += 1) {
								if((state.metric_valid_g[sample$var45][server][1] && !scratch.guard$sample76gaussian255$global[sample$var45][server][1])) {
									scratch.guard$sample76gaussian255$global[sample$var45][server][1] = true;
									state.constrainedFlag$sample76[sample$var45][0] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((cv$valuePos < state.noStates)) {
										double var243 = state.current_metric_var[server][cv$valuePos];
										cv$accumulatedConsumerProbabilities = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var45][server][1] - state.current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
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
						}
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					for(int index$sample57$22 = 0; index$sample57$22 < state.noStates; index$sample57$22 += 1) {
						double cv$probabilitySample57Value23 = state.distribution$sample57[sample$var45][index$sample57$22];
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value23);
						double[] var72 = state.m[index$sample57$22];
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample57Value23) + (((0.0 <= var72[cv$valuePos]) && (var72[cv$valuePos] <= 1.0))?Math.log(var72[cv$valuePos]):Double.NEGATIVE_INFINITY));
						if((1 < state.length$metric[sample$var45][0])) {
							for(int server = 0; server < state.noServers; server += 1) {
								state.constrainedFlag$sample76[sample$var45][0] = true;
								double var230 = state.current_metric_valid_bias[server][cv$valuePos];
								cv$accumulatedProbabilities = ((((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var45][server][1]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
							}
							for(int server = 0; server < state.noServers; server += 1) {
								if(state.metric_valid_g[sample$var45][server][1])
									scratch.guard$sample76gaussian255$global[sample$var45][server][1] = false;
							}
							for(int server = 0; server < state.noServers; server += 1) {
								if(state.metric_valid_g[sample$var45][server][1])
									scratch.guard$sample76gaussian255$global[sample$var45][server][1] = false;
							}
							for(int server = 0; server < state.noServers; server += 1) {
								if((state.metric_valid_g[sample$var45][server][1] && !scratch.guard$sample76gaussian255$global[sample$var45][server][1])) {
									scratch.guard$sample76gaussian255$global[sample$var45][server][1] = true;
									state.constrainedFlag$sample76[sample$var45][0] = true;
									double var243 = state.current_metric_var[server][cv$valuePos];
									cv$accumulatedProbabilities = (((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var45][server][1] - state.current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
								}
							}
							for(int server = 0; server < state.noServers; server += 1) {
								if((state.metric_valid_g[sample$var45][server][1] && !scratch.guard$sample76gaussian255$global[sample$var45][server][1])) {
									scratch.guard$sample76gaussian255$global[sample$var45][server][1] = true;
									state.constrainedFlag$sample76[sample$var45][0] = true;
									double var243 = state.current_metric_var[server][cv$valuePos];
									cv$accumulatedProbabilities = (((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var45][server][1] - state.current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
								}
							}
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
			int index$timeStep$30 = (timeStep$var66 - 1);
			if(((1 <= index$timeStep$30) && !(index$timeStep$30 == timeStep$var66))) {
				for(int index$sample76$31 = 0; index$sample76$31 < state.noStates; index$sample76$31 += 1) {
					double cv$probabilitySample76Value32 = state.distribution$sample76[sample$var45][(index$timeStep$30 - 1)][index$sample76$31];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample76Value32);
					double[] var72 = state.m[index$sample76$31];
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample76Value32) + (((0.0 <= var72[cv$valuePos]) && (var72[cv$valuePos] <= 1.0))?Math.log(var72[cv$valuePos]):Double.NEGATIVE_INFINITY));
					for(int server = 0; server < state.noServers; server += 1) {
						state.constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)] = true;
						double var230 = state.current_metric_valid_bias[server][index$sample76$31];
						cv$accumulatedProbabilities = ((((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var45][server][timeStep$var66]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					for(int server = 0; server < state.noServers; server += 1) {
						if(state.metric_valid_g[sample$var45][server][timeStep$var66])
							scratch.guard$sample76gaussian255$global[sample$var45][server][timeStep$var66] = false;
					}
					for(int server = 0; server < state.noServers; server += 1) {
						if(state.metric_valid_g[sample$var45][server][timeStep$var66])
							scratch.guard$sample76gaussian255$global[sample$var45][server][timeStep$var66] = false;
					}
					for(int server = 0; server < state.noServers; server += 1) {
						if((state.metric_valid_g[sample$var45][server][timeStep$var66] && !scratch.guard$sample76gaussian255$global[sample$var45][server][timeStep$var66])) {
							scratch.guard$sample76gaussian255$global[sample$var45][server][timeStep$var66] = true;
							state.constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)] = true;
							double var243 = state.current_metric_var[server][cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var45][server][timeStep$var66] - state.current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					for(int server = 0; server < state.noServers; server += 1) {
						if((state.metric_valid_g[sample$var45][server][timeStep$var66] && !scratch.guard$sample76gaussian255$global[sample$var45][server][timeStep$var66])) {
							scratch.guard$sample76gaussian255$global[sample$var45][server][timeStep$var66] = true;
							state.constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)] = true;
							double var243 = state.current_metric_var[server][cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var45][server][timeStep$var66] - state.current_metric_mean[server][cv$valuePos]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
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
			int index$timeStep$265_3 = (timeStep$var66 + 1);
			if((index$timeStep$265_3 < state.length$metric[sample$var45][0])) {
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1)
					scratch.cv$distributionAccumulator$var73[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < state.noStates)) {
					double scopeVariable$reachedSourceProbability = 0.0;
					if((1 == timeStep$var66)) {
						if(state.fixedFlag$sample57) {
							int index$var32$276_1 = state.st[sample$var45][0];
							if(((0 <= index$var32$276_1) && (index$var32$276_1 < state.noStates)))
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							for(int index$sample57$272 = 0; index$sample57$272 < state.noStates; index$sample57$272 += 1)
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + state.distribution$sample57[sample$var45][index$sample57$272]);
						}
					}
					int index$timeStep$280 = (timeStep$var66 - 1);
					if((((1 <= index$timeStep$280) && !(index$timeStep$280 == timeStep$var66)) && !(index$timeStep$280 == index$timeStep$265_3))) {
						for(int index$sample76$281 = 0; index$sample76$281 < state.noStates; index$sample76$281 += 1)
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + state.distribution$sample76[sample$var45][(index$timeStep$280 - 1)][index$sample76$281]);
					}
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					DistributionSampling.addProbabilityDistributionCategorical(scratch.cv$distributionAccumulator$var73, scopeVariable$reachedSourceProbability, state.m[cv$valuePos], state.noStates);
				}
				double[] cv$sampleDistribution = state.distribution$sample76[sample$var45][(index$timeStep$265_3 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1) {
					double cv$normalisedDistValue = (scratch.cv$distributionAccumulator$var73[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			scratch.cv$var74$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		if(state.constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)]) {
			double[] cv$localProbability = state.distribution$sample76[sample$var45][(timeStep$var66 - 1)];
			double cv$logSum;
			double cv$lseMax = scratch.cv$var74$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = scratch.cv$var74$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var74$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((scratch.cv$var74$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var74$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void logProbabilityDistribution$sample241() {
		if(!state.fixedProbFlag$sample241) {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						boolean cv$sampleValue = state.metric_valid_g[sample$var196][server][timeStep$var226];
						if((0 == timeStep$var226)) {
							if(state.fixedFlag$sample57) {
								int var183 = state.st[sample$var196][0];
								if(((0 <= var183) && (var183 < state.noStates))) {
									double var230 = state.current_metric_valid_bias[server][state.st[sample$var196][0]];
									cv$distributionAccumulator = (((0.0 <= var230) && (var230 <= 1.0))?Math.log((cv$sampleValue?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY);
									cv$probabilityReached = 1.0;
								}
							} else {
								for(int index$sample57$4 = 0; index$sample57$4 < state.noStates; index$sample57$4 += 1) {
									double cv$probabilitySample57Value5 = state.distribution$sample57[sample$var196][index$sample57$4];
									double var230 = state.current_metric_valid_bias[server][index$sample57$4];
									double cv$weightedProbability = (Math.log(cv$probabilitySample57Value5) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((cv$sampleValue?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value5);
								}
							}
						}
						if((1 <= timeStep$var226)) {
							if(state.fixedFlag$sample76) {
								int var183 = state.st[sample$var196][timeStep$var226];
								if(((0 <= var183) && (var183 < state.noStates))) {
									double var230 = state.current_metric_valid_bias[server][state.st[sample$var196][timeStep$var226]];
									double cv$weightedProbability = (((0.0 <= var230) && (var230 <= 1.0))?Math.log((cv$sampleValue?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY);
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
								for(int index$sample76$13 = 0; index$sample76$13 < state.noStates; index$sample76$13 += 1) {
									double cv$probabilitySample76Value14 = state.distribution$sample76[sample$var196][(timeStep$var226 - 1)][index$sample76$13];
									double var230 = state.current_metric_valid_bias[server][index$sample76$13];
									double cv$weightedProbability = (Math.log(cv$probabilitySample76Value14) + (((0.0 <= var230) && (var230 <= 1.0))?Math.log((cv$sampleValue?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value14);
								}
							}
						}
						if((cv$probabilityReached == 0.0))
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						state.logProbability$sample241[sample$var196][server][timeStep$var226] = cv$distributionAccumulator;
					}
				}
			}
			state.logProbability$metric_valid_inner = (state.logProbability$metric_valid_inner + cv$accumulator);
			state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample241 = ((state.fixedFlag$sample57 && state.fixedFlag$sample76) && state.fixedFlag$sample190);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1)
						cv$accumulator = (cv$accumulator + state.logProbability$sample241[sample$var196][server][timeStep$var226]);
				}
			}
			state.logProbability$metric_valid_inner = (state.logProbability$metric_valid_inner + cv$accumulator);
			state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample256() {
		if(!state.fixedProbFlag$sample256) {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							double cv$probabilityReached = 0.0;
							double cv$sampleValue = state.var245[sample$var196][server][timeStep$var226];
							if((0 == timeStep$var226)) {
								if(state.fixedFlag$sample57) {
									if((0 <= state.st[sample$var196][0])) {
										int var129 = state.st[sample$var196][0];
										if(((0 <= var129) && (var129 < state.noStates))) {
											double var243 = state.current_metric_var[server][state.st[sample$var196][0]];
											cv$distributionAccumulator = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.current_metric_mean[server][state.st[sample$var196][0]]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
											cv$probabilityReached = 1.0;
										}
									}
								} else {
									for(int index$sample57$4 = 0; index$sample57$4 < state.noStates; index$sample57$4 += 1) {
										double cv$probabilitySample57Value5 = state.distribution$sample57[sample$var196][index$sample57$4];
										double var243 = state.current_metric_var[server][index$sample57$4];
										double cv$weightedProbability = (Math.log(cv$probabilitySample57Value5) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.current_metric_mean[server][index$sample57$4]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
										if((cv$weightedProbability < cv$distributionAccumulator))
											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
										else {
											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
												cv$distributionAccumulator = cv$weightedProbability;
											else
												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
										}
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value5);
									}
								}
							}
							if((1 <= timeStep$var226)) {
								if(state.fixedFlag$sample76) {
									if((0 <= state.st[sample$var196][timeStep$var226])) {
										int var129 = state.st[sample$var196][timeStep$var226];
										if(((0 <= var129) && (var129 < state.noStates))) {
											double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
											double cv$weightedProbability = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
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
									for(int index$sample76$49 = 0; index$sample76$49 < state.noStates; index$sample76$49 += 1) {
										double cv$probabilitySample76Value50 = state.distribution$sample76[sample$var196][(timeStep$var226 - 1)][index$sample76$49];
										double var243 = state.current_metric_var[server][index$sample76$49];
										double cv$weightedProbability = (Math.log(cv$probabilitySample76Value50) + ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.current_metric_mean[server][index$sample76$49]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY));
										if((cv$weightedProbability < cv$distributionAccumulator))
											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
										else {
											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
												cv$distributionAccumulator = cv$weightedProbability;
											else
												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
										}
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value50);
									}
								}
							}
							if((cv$probabilityReached == 0.0))
								cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							else
								cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
							cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
							state.logProbability$sample256[sample$var196][server][timeStep$var226] = cv$distributionAccumulator;
						}
					}
				}
			}
			state.logProbability$var245 = (state.logProbability$var245 + cv$accumulator);
			state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample256 = (((state.fixedFlag$sample57 && state.fixedFlag$sample76) && state.fixedFlag$sample134) && state.fixedFlag$sample162);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(state.metric_valid_g[sample$var196][server][timeStep$var226])
							cv$accumulator = (cv$accumulator + state.logProbability$sample256[sample$var196][server][timeStep$var226]);
					}
				}
			}
			state.logProbability$var245 = (state.logProbability$var245 + cv$accumulator);
			state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample57() {
		if(!state.fixedProbFlag$sample57) {
			if(state.fixedFlag$sample57) {
				double cv$accumulator = 0.0;
				for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
					int cv$sampleValue = state.st[sample$var45][0];
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[cv$sampleValue])) && (state.initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(state.initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					state.logProbability$sample57[sample$var45] = cv$distributionAccumulator;
				}
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample57 = state.fixedFlag$sample20;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample57[sample$var45]);
			if(state.fixedFlag$sample57)
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample76() {
		if(!state.fixedProbFlag$sample76) {
			if(state.fixedFlag$sample76) {
				double cv$accumulator = 0.0;
				for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
					for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int cv$sampleValue = state.st[sample$var45][timeStep$var66];
						if((1 == timeStep$var66)) {
							if(state.fixedFlag$sample57) {
								int var32 = state.st[sample$var45][0];
								if(((0 <= var32) && (var32 < state.noStates))) {
									double[] var72 = state.m[state.st[sample$var45][0]];
									cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY);
									cv$probabilityReached = 1.0;
								}
							} else {
								for(int index$sample57$6 = 0; index$sample57$6 < state.noStates; index$sample57$6 += 1) {
									double cv$probabilitySample57Value7 = state.distribution$sample57[sample$var45][index$sample57$6];
									double[] var72 = state.m[index$sample57$6];
									double cv$weightedProbability = (Math.log(cv$probabilitySample57Value7) + (((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value7);
								}
							}
						}
						if((2 <= timeStep$var66)) {
							int var32 = state.st[sample$var45][(timeStep$var66 - 1)];
							if(((0 <= var32) && (var32 < state.noStates))) {
								double[] var72 = state.m[state.st[sample$var45][(timeStep$var66 - 1)]];
								double cv$weightedProbability = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
						state.logProbability$sample76[sample$var45][(timeStep$var66 - 1)] = cv$distributionAccumulator;
					}
				}
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample76 = (state.fixedFlag$sample33 && state.fixedFlag$sample57);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1)
					cv$accumulator = (cv$accumulator + state.logProbability$sample76[sample$var45][(timeStep$var66 - 1)]);
			}
			if(state.fixedFlag$sample76)
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample76)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample134() {
		if(!state.fixedProbFlag$sample134) {
			double cv$sampleAccumulator = 0.0;
			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
				for(int var129 = 0; var129 < state.noStates; var129 += 1) {
					double cv$sampleValue = state.current_metric_mean[var119][var129];
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < (double)state.max_metric))?(-Math.log(state.max_metric)):Double.NEGATIVE_INFINITY));
				}
			}
			state.logProbability$var130 = cv$sampleAccumulator;
			state.logProbability$current_metric_mean = (state.logProbability$current_metric_mean + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample134)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample134 = state.fixedFlag$sample134;
		} else {
			state.logProbability$current_metric_mean = (state.logProbability$current_metric_mean + state.logProbability$var130);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var130);
			if(state.fixedFlag$sample134)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var130);
		}
	}

	private final void logProbabilityValue$sample162() {
		if(!state.fixedProbFlag$sample162) {
			double cv$sampleAccumulator = 0.0;
			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
				for(int var156 = 0; var156 < state.noStates; var156 += 1)
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(state.current_metric_var[var146][var156], 1.0, 1.0));
			}
			state.logProbability$var157 = cv$sampleAccumulator;
			state.logProbability$current_metric_var = (state.logProbability$current_metric_var + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample162)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample162 = state.fixedFlag$sample162;
		} else {
			state.logProbability$current_metric_var = (state.logProbability$current_metric_var + state.logProbability$var157);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var157);
			if(state.fixedFlag$sample162)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var157);
		}
	}

	private final void logProbabilityValue$sample190() {
		if(!state.fixedProbFlag$sample190) {
			double cv$sampleAccumulator = 0.0;
			for(int var173 = 0; var173 < state.noServers; var173 += 1) {
				for(int var183 = 0; var183 < state.noStates; var183 += 1)
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(state.current_metric_valid_bias[var173][var183], 1.0, 1.0));
			}
			state.logProbability$var184 = cv$sampleAccumulator;
			state.logProbability$current_metric_valid_bias = (state.logProbability$current_metric_valid_bias + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample190)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample190 = state.fixedFlag$sample190;
		} else {
			state.logProbability$current_metric_valid_bias = (state.logProbability$current_metric_valid_bias + state.logProbability$var184);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var184);
			if(state.fixedFlag$sample190)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var184);
		}
	}

	private final void logProbabilityValue$sample20() {
		if(!state.fixedProbFlag$sample20) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(state.initialStateDistribution, state.v, state.noStates);
			state.logProbability$initialStateDistribution = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample20)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample20 = state.fixedFlag$sample20;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$initialStateDistribution);
			if(state.fixedFlag$sample20)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$initialStateDistribution);
		}
	}

	private final void logProbabilityValue$sample241() {
		if(!state.fixedProbFlag$sample241) {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						double var230 = state.current_metric_valid_bias[server][state.st[sample$var196][timeStep$var226]];
						double cv$distributionAccumulator = (((0.0 <= var230) && (var230 <= 1.0))?Math.log((state.metric_valid_g[sample$var196][server][timeStep$var226]?var230:(1.0 - var230))):Double.NEGATIVE_INFINITY);
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						state.logProbability$sample241[sample$var196][server][timeStep$var226] = cv$distributionAccumulator;
					}
				}
			}
			state.logProbability$metric_valid_inner = (state.logProbability$metric_valid_inner + cv$accumulator);
			state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample241 = ((state.fixedFlag$sample57 && state.fixedFlag$sample76) && state.fixedFlag$sample190);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1)
						cv$accumulator = (cv$accumulator + state.logProbability$sample241[sample$var196][server][timeStep$var226]);
				}
			}
			state.logProbability$metric_valid_inner = (state.logProbability$metric_valid_inner + cv$accumulator);
			state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample256() {
		if(!state.fixedProbFlag$sample256) {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(state.metric_valid_g[sample$var196][server][timeStep$var226]) {
							double var243 = state.current_metric_var[server][state.st[sample$var196][timeStep$var226]];
							double cv$distributionAccumulator = ((0.0 < var243)?(DistributionSampling.logProbabilityGaussian(((state.var245[sample$var196][server][timeStep$var226] - state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5)):Double.NEGATIVE_INFINITY);
							cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
							state.logProbability$sample256[sample$var196][server][timeStep$var226] = cv$distributionAccumulator;
						}
					}
				}
			}
			state.logProbability$var245 = (state.logProbability$var245 + cv$accumulator);
			state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample256 = (((state.fixedFlag$sample57 && state.fixedFlag$sample76) && state.fixedFlag$sample134) && state.fixedFlag$sample162);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(state.metric_valid_g[sample$var196][server][timeStep$var226])
							cv$accumulator = (cv$accumulator + state.logProbability$sample256[sample$var196][server][timeStep$var226]);
					}
				}
			}
			state.logProbability$var245 = (state.logProbability$var245 + cv$accumulator);
			state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample33() {
		if(!state.fixedProbFlag$sample33) {
			double cv$sampleAccumulator = 0.0;
			for(int var32 = 0; var32 < state.noStates; var32 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(state.m[var32], state.v, state.noStates));
			state.logProbability$var33 = cv$sampleAccumulator;
			state.logProbability$m = (state.logProbability$m + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample33)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample33 = state.fixedFlag$sample33;
		} else {
			state.logProbability$m = (state.logProbability$m + state.logProbability$var33);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var33);
			if(state.fixedFlag$sample33)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var33);
		}
	}

	private final void logProbabilityValue$sample57() {
		if(!state.fixedProbFlag$sample57) {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				int cv$sampleValue = state.st[sample$var45][0];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[cv$sampleValue])) && (state.initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(state.initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample57[sample$var45] = cv$distributionAccumulator;
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample57 = (state.fixedFlag$sample57 && state.fixedFlag$sample20);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample57[sample$var45]);
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample76() {
		if(!state.fixedProbFlag$sample76) {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
					int cv$sampleValue = state.st[sample$var45][timeStep$var66];
					double[] var72 = state.m[state.st[sample$var45][(timeStep$var66 - 1)]];
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var72[cv$sampleValue])) && (var72[cv$sampleValue] <= 1.0))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					state.logProbability$sample76[sample$var45][(timeStep$var66 - 1)] = cv$distributionAccumulator;
				}
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample76)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample76 = ((state.fixedFlag$sample76 && state.fixedFlag$sample33) && state.fixedFlag$sample57);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1)
					cv$accumulator = (cv$accumulator + state.logProbability$sample76[sample$var45][(timeStep$var66 - 1)]);
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample76)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		if(!state.fixedFlag$sample33) {
			for(int var32 = 0; var32 < state.noStates; var32 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var32]);
		}
		for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
			if(!state.fixedFlag$sample57)
				state.st[sample$var45][0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
			if(!state.fixedFlag$sample76) {
				int[] var67 = state.st[sample$var45];
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample$var45][(timeStep$var66 - 1)]], state.noStates);
			}
		}
		if(!state.fixedFlag$sample134) {
			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
				double[] var120 = state.current_metric_mean[var119];
				for(int var129 = 0; var129 < state.noStates; var129 += 1)
					var120[var129] = ((double)state.max_metric * DistributionSampling.sampleUniform(state.RNG$));
			}
		}
		if(!state.fixedFlag$sample162) {
			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
				double[] var147 = state.current_metric_var[var146];
				for(int var156 = 0; var156 < state.noStates; var156 += 1)
					var147[var156] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
			}
		}
		if(!state.fixedFlag$sample190) {
			for(int var173 = 0; var173 < state.noServers; var173 += 1) {
				double[] var174 = state.current_metric_valid_bias[var173];
				for(int var183 = 0; var183 < state.noStates; var183 += 1)
					var174[var183] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			}
		}
		for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
			double[][] var211 = state.metric_g[sample$var196];
			for(int server = 0; server < state.noServers; server += 1) {
				boolean[] metric_valid_inner = state.metric_valid_g[sample$var196][server];
				double[] metric_inner = var211[server];
				for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
					metric_valid_inner[timeStep$var226] = DistributionSampling.sampleBernoulli(state.RNG$, state.current_metric_valid_bias[server][state.st[sample$var196][timeStep$var226]]);
					if(metric_valid_inner[timeStep$var226]) {
						state.var245[sample$var196][server][timeStep$var226] = ((Math.sqrt(state.current_metric_var[server][state.st[sample$var196][timeStep$var226]]) * DistributionSampling.sampleGaussian(state.RNG$)) + state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]]);
						metric_inner[timeStep$var226] = state.var245[sample$var196][server][timeStep$var226];
					}
				}
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		if(!state.fixedFlag$sample33) {
			for(int var32 = 0; var32 < state.noStates; var32 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var32]);
		}
		for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
			if(!state.fixedFlag$sample57) {
				double[] cv$distribution$sample57 = state.distribution$sample57[sample$var45];
				for(int index$var54 = 0; index$var54 < state.noStates; index$var54 += 1)
					cv$distribution$sample57[index$var54] = (((0.0 <= state.initialStateDistribution[index$var54]) && (state.initialStateDistribution[index$var54] <= 1.0))?state.initialStateDistribution[index$var54]:0.0);
			}
			if(!state.fixedFlag$sample76) {
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
					double[] cv$distribution$sample76 = state.distribution$sample76[sample$var45][(timeStep$var66 - 1)];
					for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1)
						cv$distribution$sample76[index$var73] = 0.0;
					if((1 == timeStep$var66)) {
						if(state.fixedFlag$sample57) {
							int var32 = state.st[sample$var45][0];
							if(((0 <= var32) && (var32 < state.noStates))) {
								double[] var72 = state.m[state.st[sample$var45][0]];
								for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1)
									cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (((0.0 <= var72[index$var73]) && (var72[index$var73] <= 1.0))?var72[index$var73]:0.0));
							}
						} else {
							for(int index$sample57$3 = 0; index$sample57$3 < state.noStates; index$sample57$3 += 1) {
								double cv$probabilitySample57Value4 = state.distribution$sample57[sample$var45][index$sample57$3];
								double[] var72 = state.m[index$sample57$3];
								for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1)
									cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (cv$probabilitySample57Value4 * (((0.0 <= var72[index$var73]) && (var72[index$var73] <= 1.0))?var72[index$var73]:0.0)));
							}
						}
					}
					int index$timeStep$11 = (timeStep$var66 - 1);
					if((1 <= index$timeStep$11)) {
						for(int index$sample76$12 = 0; index$sample76$12 < state.noStates; index$sample76$12 += 1) {
							double cv$probabilitySample76Value13 = state.distribution$sample76[sample$var45][(index$timeStep$11 - 1)][index$sample76$12];
							double[] var72 = state.m[index$sample76$12];
							for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1)
								cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (cv$probabilitySample76Value13 * (((0.0 <= var72[index$var73]) && (var72[index$var73] <= 1.0))?var72[index$var73]:0.0)));
						}
					}
					double cv$var73$sum = 0.0;
					for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1)
						cv$var73$sum = (cv$var73$sum + cv$distribution$sample76[index$var73]);
					for(int index$var73 = 0; index$var73 < state.noStates; index$var73 += 1)
						cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] / cv$var73$sum);
				}
			}
		}
		if(!state.fixedFlag$sample134) {
			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
				double[] var120 = state.current_metric_mean[var119];
				for(int var129 = 0; var129 < state.noStates; var129 += 1)
					var120[var129] = ((double)state.max_metric * DistributionSampling.sampleUniform(state.RNG$));
			}
		}
		if(!state.fixedFlag$sample162) {
			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
				double[] var147 = state.current_metric_var[var146];
				for(int var156 = 0; var156 < state.noStates; var156 += 1)
					var147[var156] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
			}
		}
		if(!state.fixedFlag$sample190) {
			for(int var173 = 0; var173 < state.noServers; var173 += 1) {
				double[] var174 = state.current_metric_valid_bias[var173];
				for(int var183 = 0; var183 < state.noStates; var183 += 1)
					var174[var183] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			}
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		if(!state.fixedFlag$sample33) {
			for(int var32 = 0; var32 < state.noStates; var32 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var32]);
		}
		for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
			if(!state.fixedFlag$sample57)
				state.st[sample$var45][0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
			if(!state.fixedFlag$sample76) {
				int[] var67 = state.st[sample$var45];
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample$var45][(timeStep$var66 - 1)]], state.noStates);
			}
		}
		if(!state.fixedFlag$sample134) {
			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
				double[] var120 = state.current_metric_mean[var119];
				for(int var129 = 0; var129 < state.noStates; var129 += 1)
					var120[var129] = ((double)state.max_metric * DistributionSampling.sampleUniform(state.RNG$));
			}
		}
		if(!state.fixedFlag$sample162) {
			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
				double[] var147 = state.current_metric_var[var146];
				for(int var156 = 0; var156 < state.noStates; var156 += 1)
					var147[var156] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
			}
		}
		if(!state.fixedFlag$sample190) {
			for(int var173 = 0; var173 < state.noServers; var173 += 1) {
				double[] var174 = state.current_metric_valid_bias[var173];
				for(int var183 = 0; var183 < state.noStates; var183 += 1)
					var174[var183] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			}
		}
		for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
			double[][] var211 = state.metric_g[sample$var196];
			for(int server = 0; server < state.noServers; server += 1) {
				boolean[] metric_valid_inner = state.metric_valid_g[sample$var196][server];
				double[] metric_inner = var211[server];
				for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1) {
					metric_valid_inner[timeStep$var226] = DistributionSampling.sampleBernoulli(state.RNG$, state.current_metric_valid_bias[server][state.st[sample$var196][timeStep$var226]]);
					if(metric_valid_inner[timeStep$var226]) {
						state.var245[sample$var196][server][timeStep$var226] = ((Math.sqrt(state.current_metric_var[server][state.st[sample$var196][timeStep$var226]]) * DistributionSampling.sampleGaussian(state.RNG$)) + state.current_metric_mean[server][state.st[sample$var196][timeStep$var226]]);
						metric_inner[timeStep$var226] = state.var245[sample$var196][server][timeStep$var226];
					}
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		if(!state.fixedFlag$sample33) {
			for(int var32 = 0; var32 < state.noStates; var32 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var32]);
		}
		for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
			if(!state.fixedFlag$sample57)
				state.st[sample$var45][0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
			if(!state.fixedFlag$sample76) {
				int[] var67 = state.st[sample$var45];
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample$var45][(timeStep$var66 - 1)]], state.noStates);
			}
		}
		if(!state.fixedFlag$sample134) {
			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
				double[] var120 = state.current_metric_mean[var119];
				for(int var129 = 0; var129 < state.noStates; var129 += 1)
					var120[var129] = ((double)state.max_metric * DistributionSampling.sampleUniform(state.RNG$));
			}
		}
		if(!state.fixedFlag$sample162) {
			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
				double[] var147 = state.current_metric_var[var146];
				for(int var156 = 0; var156 < state.noStates; var156 += 1)
					var147[var156] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
			}
		}
		if(!state.fixedFlag$sample190) {
			for(int var173 = 0; var173 < state.noServers; var173 += 1) {
				double[] var174 = state.current_metric_valid_bias[var173];
				for(int var183 = 0; var183 < state.noStates; var183 += 1)
					var174[var183] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		if(!state.fixedFlag$sample33) {
			for(int var32 = 0; var32 < state.noStates; var32 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var32]);
		}
		for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
			if(!state.fixedFlag$sample57)
				state.st[sample$var45][0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
			if(!state.fixedFlag$sample76) {
				int[] var67 = state.st[sample$var45];
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample$var45][(timeStep$var66 - 1)]], state.noStates);
			}
		}
		if(!state.fixedFlag$sample134) {
			for(int var119 = 0; var119 < state.noServers; var119 += 1) {
				double[] var120 = state.current_metric_mean[var119];
				for(int var129 = 0; var129 < state.noStates; var129 += 1)
					var120[var129] = ((double)state.max_metric * DistributionSampling.sampleUniform(state.RNG$));
			}
		}
		if(!state.fixedFlag$sample162) {
			for(int var146 = 0; var146 < state.noServers; var146 += 1) {
				double[] var147 = state.current_metric_var[var146];
				for(int var156 = 0; var156 < state.noStates; var156 += 1)
					var147[var156] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
			}
		}
		if(!state.fixedFlag$sample190) {
			for(int var173 = 0; var173 < state.noServers; var173 += 1) {
				double[] var174 = state.current_metric_valid_bias[var173];
				for(int var183 = 0; var183 < state.noStates; var183 += 1)
					var174[var183] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample20)
				inferSample20();
			if(!state.fixedFlag$sample33) {
				for(int var32 = 0; var32 < state.noStates; var32 += 1)
					inferSample33(var32);
			}
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				if(!state.fixedFlag$sample57)
					inferSample57(sample$var45);
				if(!state.fixedFlag$sample76) {
					for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1)
						inferSample76(sample$var45, timeStep$var66);
				}
			}
			if(!state.fixedFlag$sample134) {
				for(int var119 = 0; var119 < state.noServers; var119 += 1) {
					for(int var129 = 0; var129 < state.noStates; var129 += 1)
						inferSample134(var119, var129);
				}
			}
			if(!state.fixedFlag$sample162) {
				for(int var146 = 0; var146 < state.noServers; var146 += 1) {
					for(int var156 = 0; var156 < state.noStates; var156 += 1)
						inferSample162(var146, var156);
				}
			}
			if(!state.fixedFlag$sample190) {
				for(int var173 = 0; var173 < state.noServers; var173 += 1) {
					for(int var183 = 0; var183 < state.noStates; var183 += 1)
						inferSample190(var173, var183);
				}
			}
		} else {
			if(!state.fixedFlag$sample190) {
				for(int var173 = (state.noServers - 1); var173 >= 0; var173 -= 1) {
					for(int var183 = (state.noStates - 1); var183 >= 0; var183 -= 1)
						inferSample190(var173, var183);
				}
			}
			if(!state.fixedFlag$sample162) {
				for(int var146 = (state.noServers - 1); var146 >= 0; var146 -= 1) {
					for(int var156 = (state.noStates - 1); var156 >= 0; var156 -= 1)
						inferSample162(var146, var156);
				}
			}
			if(!state.fixedFlag$sample134) {
				for(int var119 = (state.noServers - 1); var119 >= 0; var119 -= 1) {
					for(int var129 = (state.noStates - 1); var129 >= 0; var129 -= 1)
						inferSample134(var119, var129);
				}
			}
			for(int sample$var45 = (state.noSamples - 1); sample$var45 >= 0; sample$var45 -= 1) {
				if(!state.fixedFlag$sample76) {
					for(int timeStep$var66 = (state.length$metric[sample$var45][0] - 1); timeStep$var66 >= 1; timeStep$var66 -= 1)
						inferSample76(sample$var45, timeStep$var66);
				}
				if(!state.fixedFlag$sample57)
					inferSample57(sample$var45);
			}
			if(!state.fixedFlag$sample33) {
				for(int var32 = (state.noStates - 1); var32 >= 0; var32 -= 1)
					inferSample33(var32);
			}
			if(!state.fixedFlag$sample20)
				inferSample20();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample20)
			drawValueSample20();
		for(int var32 = 0; var32 < state.noStates; var32 += 1) {
			if(!state.constrainedFlag$sample33[var32])
				drawValueSample33(var32);
		}
		for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
			if(!state.constrainedFlag$sample57[sample$var45])
				drawValueSample57(sample$var45);
			for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1) {
				if(!state.constrainedFlag$sample76[sample$var45][(timeStep$var66 - 1)])
					drawValueSample76(sample$var45, timeStep$var66);
			}
		}
		for(int var119 = 0; var119 < state.noServers; var119 += 1) {
			for(int var129 = 0; var129 < state.noStates; var129 += 1) {
				if(!state.constrainedFlag$sample134[var119][var129])
					drawValueSample134(var119, var129);
			}
		}
		for(int var146 = 0; var146 < state.noServers; var146 += 1) {
			for(int var156 = 0; var156 < state.noStates; var156 += 1) {
				if(!state.constrainedFlag$sample162[var146][var156])
					drawValueSample162(var146, var156);
			}
		}
		for(int var173 = 0; var173 < state.noServers; var173 += 1) {
			for(int var183 = 0; var183 < state.noStates; var183 += 1) {
				if(!state.constrainedFlag$sample190[var173][var183])
					drawValueSample190(var173, var183);
			}
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample20)
			state.logProbability$initialStateDistribution = Double.NaN;
		state.logProbability$m = 0.0;
		if(!state.fixedProbFlag$sample33)
			state.logProbability$var33 = Double.NaN;
		state.logProbability$st = 0.0;
		if(!state.fixedProbFlag$sample57) {
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1)
				state.logProbability$sample57[sample$var45] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample76) {
			for(int sample$var45 = 0; sample$var45 < state.noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < state.length$metric[sample$var45][0]; timeStep$var66 += 1)
					state.logProbability$sample76[sample$var45][(timeStep$var66 - 1)] = Double.NaN;
			}
		}
		state.logProbability$current_metric_mean = 0.0;
		if(!state.fixedProbFlag$sample134)
			state.logProbability$var130 = Double.NaN;
		state.logProbability$current_metric_var = 0.0;
		if(!state.fixedProbFlag$sample162)
			state.logProbability$var157 = Double.NaN;
		state.logProbability$current_metric_valid_bias = 0.0;
		if(!state.fixedProbFlag$sample190)
			state.logProbability$var184 = Double.NaN;
		state.logProbability$metric_valid_inner = 0.0;
		state.logProbability$metric_valid_g = 0.0;
		if(!state.fixedProbFlag$sample241) {
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1)
						state.logProbability$sample241[sample$var196][server][timeStep$var226] = Double.NaN;
				}
			}
		}
		state.logProbability$var245 = 0.0;
		state.logProbability$metric_g = 0.0;
		if(!state.fixedProbFlag$sample256) {
			for(int sample$var196 = 0; sample$var196 < state.noSamples; sample$var196 += 1) {
				for(int server = 0; server < state.noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < state.length$metric[sample$var196][0]; timeStep$var226 += 1)
						state.logProbability$sample256[sample$var196][server][timeStep$var226] = Double.NaN;
				}
			}
		}
	}

	@Override
	public final void initializeModel() {
		state.noSamples = state.length$metric.length;
		for(int var16 = 0; var16 < state.noStates; var16 += 1)
			state.v[var16] = 0.1;
		state.noServers = state.length$metric[0].length;
		for(int index$constrainedFlag$sample190$1 = 0; index$constrainedFlag$sample190$1 < state.constrainedFlag$sample190.length; index$constrainedFlag$sample190$1 += 1) {
			boolean[] cv$constrainedFlag$sample190$1 = state.constrainedFlag$sample190[index$constrainedFlag$sample190$1];
			for(int index$constrainedFlag$sample190$2 = 0; index$constrainedFlag$sample190$2 < cv$constrainedFlag$sample190$1.length; index$constrainedFlag$sample190$2 += 1)
				cv$constrainedFlag$sample190$1[index$constrainedFlag$sample190$2] = true;
		}
		for(int index$constrainedFlag$sample76$1 = 0; index$constrainedFlag$sample76$1 < state.constrainedFlag$sample76.length; index$constrainedFlag$sample76$1 += 1) {
			boolean[] cv$constrainedFlag$sample76$1 = state.constrainedFlag$sample76[index$constrainedFlag$sample76$1];
			for(int index$constrainedFlag$sample76$2 = 0; index$constrainedFlag$sample76$2 < cv$constrainedFlag$sample76$1.length; index$constrainedFlag$sample76$2 += 1)
				cv$constrainedFlag$sample76$1[index$constrainedFlag$sample76$2] = true;
		}
		for(int index$constrainedFlag$sample57$1 = 0; index$constrainedFlag$sample57$1 < state.constrainedFlag$sample57.length; index$constrainedFlag$sample57$1 += 1)
			state.constrainedFlag$sample57[index$constrainedFlag$sample57$1] = true;
		for(int index$constrainedFlag$sample134$1 = 0; index$constrainedFlag$sample134$1 < state.constrainedFlag$sample134.length; index$constrainedFlag$sample134$1 += 1) {
			boolean[] cv$constrainedFlag$sample134$1 = state.constrainedFlag$sample134[index$constrainedFlag$sample134$1];
			for(int index$constrainedFlag$sample134$2 = 0; index$constrainedFlag$sample134$2 < cv$constrainedFlag$sample134$1.length; index$constrainedFlag$sample134$2 += 1)
				cv$constrainedFlag$sample134$1[index$constrainedFlag$sample134$2] = true;
		}
		for(int index$constrainedFlag$sample162$1 = 0; index$constrainedFlag$sample162$1 < state.constrainedFlag$sample162.length; index$constrainedFlag$sample162$1 += 1) {
			boolean[] cv$constrainedFlag$sample162$1 = state.constrainedFlag$sample162[index$constrainedFlag$sample162$1];
			for(int index$constrainedFlag$sample162$2 = 0; index$constrainedFlag$sample162$2 < cv$constrainedFlag$sample162$1.length; index$constrainedFlag$sample162$2 += 1)
				cv$constrainedFlag$sample162$1[index$constrainedFlag$sample162$2] = true;
		}
		for(int index$constrainedFlag$sample33$1 = 0; index$constrainedFlag$sample33$1 < state.constrainedFlag$sample33.length; index$constrainedFlag$sample33$1 += 1)
			state.constrainedFlag$sample33[index$constrainedFlag$sample33$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample20)
			logProbabilityValue$sample20();
		if(state.fixedFlag$sample33)
			logProbabilityValue$sample33();
		if(state.fixedFlag$sample134)
			logProbabilityValue$sample134();
		if(state.fixedFlag$sample162)
			logProbabilityValue$sample162();
		if(state.fixedFlag$sample190)
			logProbabilityValue$sample190();
		logProbabilityValue$sample241();
		logProbabilityValue$sample256();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample33();
		logProbabilityDistribution$sample57();
		logProbabilityDistribution$sample76();
		logProbabilityValue$sample134();
		logProbabilityValue$sample162();
		logProbabilityValue$sample190();
		logProbabilityDistribution$sample241();
		logProbabilityDistribution$sample256();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample33();
		logProbabilityValue$sample57();
		logProbabilityValue$sample76();
		logProbabilityValue$sample134();
		logProbabilityValue$sample162();
		logProbabilityValue$sample190();
		logProbabilityValue$sample241();
		logProbabilityValue$sample256();
	}

	@Override
	public final void propogateObservedValues() {
		{
			int cv$length1 = state.metric_valid_g.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
				boolean[][] cv$source2 = state.metric_valid[cv$index1];
				boolean[][] cv$target2 = state.metric_valid_g[cv$index1];
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
		int cv$length1 = state.metric_g.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			double[][] cv$source2 = state.metric[cv$index1];
			double[][] cv$target2 = state.metric_g[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1) {
				double[] cv$source3 = cv$source2[cv$index2];
				double[] cv$target3 = cv$target2[cv$index2];
				int cv$length3 = cv$target3.length;
				for(int cv$index3 = 0; cv$index3 < cv$length3; cv$index3 += 1)
					cv$target3[cv$index3] = cv$source3[cv$index3];
			}
		}
		for(int sample$var196 = (state.noSamples - 1); sample$var196 >= 0; sample$var196 -= 1) {
			for(int server = (state.noServers - 1); server >= 0; server -= 1) {
				for(int timeStep$var226 = (state.length$metric[sample$var196][0] - 1); timeStep$var226 >= 0; timeStep$var226 -= 1) {
					if(state.metric_valid_g[sample$var196][server][timeStep$var226])
						state.var245[sample$var196][server][timeStep$var226] = state.metric_g[sample$var196][server][timeStep$var226];
				}
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
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model HMMMetrics4(\n"
		     + "               double[][][] metric,\n"
		     + "               boolean[][][] metric_valid, \n"
		     + "               int max_metric,\n"
		     + "               int noStates) {\n"
		     + "    \n"
		     + "    int noSamples = metric.length;\n"
		     + "\n"
		     + "    // Construct arrays describing the probability of a move from 1 state to another.\n"
		     + "    double[] v = new double[noStates] <~ 0.1;\n"
		     + "    double[] initialStateDistribution = dirichlet(v).sample();\n"
		     + "    double[][] m = dirichlet(v).sample(noStates);\n"
		     + "    \n"
		     + "    //Calculate all the state transitions\n"
		     + "    int[][] st = new int[noSamples][];\n"
		     + "    for(int sample = 0; sample < noSamples; sample++) {\n"
		     + "        int streamLength = metric[sample][0].length;\n"
		     + "        \n"
		     + "        // Allocate space for the state.\n"
		     + "        st[sample] = new int[streamLength];\n"
		     + "        \n"
		     + "        // Set the initial state by sampling from a categorical with learnt weightings.\n"
		     + "        st[sample][0] = categorical(initialStateDistribution).sampleDistribution();\n"
		     + "        \n"
		     + "        // Calculate the remaining weightings\n"
		     + "        for(int timeStep = 1; timeStep < streamLength; timeStep++)\n"
		     + "            st[sample][timeStep] = categorical(m[st[sample][timeStep-1]]).sampleDistribution();\n"
		     + "    }\n"
		     + "    \n"
		     + "    // Calculate the number of servers\n"
		     + "    int noServers = metric[0].length;    \n"
		     + "    \n"
		     + "    // Allocate space for each generated metric.    \n"
		     + "    double[][][] metric_g = new double[noSamples][noServers][];\n"
		     + "    boolean[][][] metric_valid_g = new boolean[noSamples][noServers][];\n"
		     + "\n"
		     + "    // Calculate metric parameters\n"
		     + "    double[][] current_metric_mean = uniform(0.0, (double) max_metric).sample(noServers, noStates);\n"
		     + "    double[][] current_metric_var = inverseGamma(1.0, 1.0).sample(noServers, noStates);\n"
		     + "    double[][] current_metric_valid_bias = beta(1.0, 1.0).sample(noServers, noStates);\n"
		     + "    \n"
		     + "    // Compute the values of each metric\n"
		     + "    for(int sample = 0; sample < noSamples; sample++) {\n"
		     + "        int streamLength = metric[sample][0].length;\n"
		     + "        for(int server = 0; server < noServers; server++) {\n"
		     + "            //Allocate space for the time series\n"
		     + "            double[] metric_inner = new double[streamLength];\n"
		     + "            metric_g[sample][server] = metric_inner;\n"
		     + "            \n"
		     + "            boolean[] metric_valid_inner = new boolean[streamLength];\n"
		     + "            metric_valid_g[sample][server] = metric_valid_inner;\n"
		     + "            \n"
		     + "            //Generate values.\n"
		     + "            for(int timeStep = 0; timeStep < streamLength; timeStep++){\n"
		     + "                int currentState = st[sample][timeStep];\n"
		     + "                \n"
		     + "                metric_valid_inner[timeStep] = bernoulli(current_metric_valid_bias[server][currentState]).sample();\n"
		     + "                if(metric_valid_inner[timeStep])\n"
		     + "                    metric_inner[timeStep] = gaussian(current_metric_mean[server][currentState], current_metric_var[server][currentState]).sample();\n"
		     + "            }\n"
		     + "        }\n"
		     + "    }\n"
		     + "\n"
		     + "    //Tie the values to the measured values.\n"
		     + "    metric_valid_g.observe(metric_valid);\n"
		     + "    metric_g.observe(metric);\n"
		     + "}";
	}
}