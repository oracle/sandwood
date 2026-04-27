package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.LinearRegressionTest$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.LinearRegressionTest.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class LinearRegressionTest$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public LinearRegressionTest$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample24(int var23) {
		state.weights[var23] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1)
			state.phi[i$var45][var23] = (state.weights[var23] * state.x[i$var45][var23]);
	}

	private final void drawValueSample31() {
		state.bias = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
	}

	private final void drawValueSample35() {
		state.tau = DistributionSampling.sampleInverseGamma(state.RNG$, 3.0, 1.0);
	}

	private final void inferSample24(int var23) {
		state.constrainedFlag$sample24[var23] = false;
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
			state.constrainedFlag$sample24[var23] = true;
			double cv$denominator = state.x[i$var45][var23];
			double reduceVar$var70$6 = 0.0;
			for(int cv$reduction436Index = 0; cv$reduction436Index < var23; cv$reduction436Index += 1)
				reduceVar$var70$6 = (reduceVar$var70$6 + state.phi[i$var45][cv$reduction436Index]);
			for(int cv$reduction436Index = (var23 + 1); cv$reduction436Index < state.k; cv$reduction436Index += 1)
				reduceVar$var70$6 = (reduceVar$var70$6 + state.phi[i$var45][cv$reduction436Index]);
			cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
			cv$sum = (cv$sum + (cv$denominator * (state.y[i$var45] - (reduceVar$var70$6 + state.bias))));
			if(cv$sigmaNotFound) {
				cv$sigmaValue = state.tau;
				cv$sigmaNotFound = false;
			}
		}
		if(state.constrainedFlag$sample24[var23]) {
			state.weights[var23] = Conjugates.sampleConjugateGaussianGaussian(state.RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
			for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1)
				state.phi[i$var45][var23] = (state.weights[var23] * state.x[i$var45][var23]);
		}
	}

	private final void inferSample31() {
		state.constrainedFlag$sample31 = false;
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
			state.constrainedFlag$sample31 = true;
			double reduceVar$var70$7 = 0.0;
			for(int cv$reduction65Index = 0; cv$reduction65Index < state.k; cv$reduction65Index += 1)
				reduceVar$var70$7 = (reduceVar$var70$7 + state.phi[i$var45][cv$reduction65Index]);
			cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
			cv$sum = ((cv$sum + state.y[i$var45]) - reduceVar$var70$7);
			if(cv$sigmaNotFound) {
				cv$sigmaValue = state.tau;
				cv$sigmaNotFound = false;
			}
		}
		if(state.constrainedFlag$sample31)
			state.bias = Conjugates.sampleConjugateGaussianGaussian(state.RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void inferSample35() {
		state.constrainedFlag$sample35 = false;
		double cv$sum = 0.0;
		int cv$count = 0;
		for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
			state.constrainedFlag$sample35 = true;
			double reduceVar$var70$8 = 0.0;
			for(int cv$reduction65Index = 0; cv$reduction65Index < state.k; cv$reduction65Index += 1)
				reduceVar$var70$8 = (reduceVar$var70$8 + state.phi[i$var45][cv$reduction65Index]);
			double cv$var72$diff = ((reduceVar$var70$8 + state.bias) - state.y[i$var45]);
			cv$sum = (cv$sum + (cv$var72$diff * cv$var72$diff));
			cv$count = (cv$count + 1);
		}
		if(state.constrainedFlag$sample35)
			state.tau = Conjugates.sampleConjugateInverseGammaGaussian(state.RNG$, 3.0, 1.0, cv$sum, cv$count);
	}

	private final void logProbabilityValue$sample24() {
		if(!state.fixedProbFlag$sample24) {
			double cv$sampleAccumulator = 0.0;
			for(int var23 = 0; var23 < state.k; var23 += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((state.weights[var23] / 3.1622776601683795)) - 1.151292546497023);
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				state.logProbability$sample24[var23] = cv$distributionAccumulator;
			}
			state.logProbability$weights = (state.logProbability$weights + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample24)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample24 = state.fixedFlag$sample24;
		} else {
			double cv$rvAccumulator = 0.0;
			for(int var23 = 0; var23 < state.k; var23 += 1)
				cv$rvAccumulator = (cv$rvAccumulator + state.logProbability$sample24[var23]);
			state.logProbability$weights = (state.logProbability$weights + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			if(state.fixedFlag$sample24)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!state.fixedProbFlag$sample31) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((state.bias / 3.1622776601683795)) - 1.151292546497023);
			state.logProbability$bias = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample31)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample31 = state.fixedFlag$sample31;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$bias);
			if(state.fixedFlag$sample31)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$bias);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!state.fixedProbFlag$sample35) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityInverseGamma(state.tau, 3.0, 1.0);
			state.logProbability$tau = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample35)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample35 = state.fixedFlag$sample35;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$tau);
			if(state.fixedFlag$sample35)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$tau);
		}
	}

	private final void logProbabilityValue$sample74() {
		if(!state.fixedProbFlag$sample74) {
			double cv$accumulator = 0.0;
			for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1) {
				double reduceVar$var70$9 = 0.0;
				for(int cv$reduction65Index = 0; cv$reduction65Index < state.k; cv$reduction65Index += 1)
					reduceVar$var70$9 = (reduceVar$var70$9 + state.phi[i$var45][cv$reduction65Index]);
				double cv$distributionAccumulator = ((0.0 < state.tau)?(DistributionSampling.logProbabilityGaussian(((state.y[i$var45] - (reduceVar$var70$9 + state.bias)) / Math.sqrt(state.tau))) - (Math.log(state.tau) * 0.5)):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample74[i$var45] = cv$distributionAccumulator;
			}
			state.logProbability$y = (state.logProbability$y + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample74 = ((state.fixedFlag$sample24 && state.fixedFlag$sample31) && state.fixedFlag$sample35);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample74[i$var45]);
			state.logProbability$y = (state.logProbability$y + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample24)
			parallelFor(state.RNG$, 0, state.k, 1,
				(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1)
							state.weights[var23] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!state.fixedFlag$sample31)
			state.bias = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		if(!state.fixedFlag$sample35)
			state.tau = DistributionSampling.sampleInverseGamma(state.RNG$, 3.0, 1.0);
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$index$i$var45, int forEnd$index$i$var45, int threadID$index$i$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var45 = forStart$index$i$var45; index$i$var45 < forEnd$index$i$var45; index$i$var45 += 1) {
						int i$var45 = index$i$var45;
						int threadID$i$var45 = threadID$index$i$var45;
						if(!state.fixedFlag$sample24)
							parallelFor(RNG$1, 0, state.k, 1,
								(int forStart$j$var55, int forEnd$j$var55, int threadID$j$var55, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var55 = forStart$j$var55; j$var55 < forEnd$j$var55; j$var55 += 1)
											state.phi[i$var45][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
								}
							);

						double reduceVar$var70$10 = 0.0;
						for(int cv$reduction65Index = 0; cv$reduction65Index < state.k; cv$reduction65Index += 1)
							reduceVar$var70$10 = (reduceVar$var70$10 + state.phi[i$var45][cv$reduction65Index]);
						state.y[i$var45] = (((Math.sqrt(state.tau) * DistributionSampling.sampleGaussian(RNG$1)) + reduceVar$var70$10) + state.bias);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample24)
			parallelFor(state.RNG$, 0, state.k, 1,
				(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1)
							state.weights[var23] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!state.fixedFlag$sample31)
			state.bias = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		if(!state.fixedFlag$sample35)
			state.tau = DistributionSampling.sampleInverseGamma(state.RNG$, 3.0, 1.0);
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$index$i$var45, int forEnd$index$i$var45, int threadID$index$i$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var45 = forStart$index$i$var45; index$i$var45 < forEnd$index$i$var45; index$i$var45 += 1) {
						int i$var45 = index$i$var45;
						int threadID$i$var45 = threadID$index$i$var45;
						parallelFor(RNG$1, 0, state.k, 1,
							(int forStart$j$var55, int forEnd$j$var55, int threadID$j$var55, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var55 = forStart$j$var55; j$var55 < forEnd$j$var55; j$var55 += 1)
										state.phi[i$var45][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample24)
			parallelFor(state.RNG$, 0, state.k, 1,
				(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1)
							state.weights[var23] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!state.fixedFlag$sample31)
			state.bias = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		if(!state.fixedFlag$sample35)
			state.tau = DistributionSampling.sampleInverseGamma(state.RNG$, 3.0, 1.0);
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$index$i$var45, int forEnd$index$i$var45, int threadID$index$i$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var45 = forStart$index$i$var45; index$i$var45 < forEnd$index$i$var45; index$i$var45 += 1) {
						int i$var45 = index$i$var45;
						int threadID$i$var45 = threadID$index$i$var45;
						parallelFor(RNG$1, 0, state.k, 1,
							(int forStart$j$var55, int forEnd$j$var55, int threadID$j$var55, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var55 = forStart$j$var55; j$var55 < forEnd$j$var55; j$var55 += 1)
										state.phi[i$var45][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
							}
						);
						double reduceVar$var70$11 = 0.0;
						for(int cv$reduction65Index = 0; cv$reduction65Index < state.k; cv$reduction65Index += 1)
							reduceVar$var70$11 = (reduceVar$var70$11 + state.phi[i$var45][cv$reduction65Index]);
						state.y[i$var45] = (((Math.sqrt(state.tau) * DistributionSampling.sampleGaussian(RNG$1)) + reduceVar$var70$11) + state.bias);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample24)
			parallelFor(state.RNG$, 0, state.k, 1,
				(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1)
							state.weights[var23] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!state.fixedFlag$sample31)
			state.bias = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		if(!state.fixedFlag$sample35)
			state.tau = DistributionSampling.sampleInverseGamma(state.RNG$, 3.0, 1.0);
		if(!state.fixedFlag$sample24)
			parallelFor(state.RNG$, 0, state.n, 1,
				(int forStart$index$i$var45, int forEnd$index$i$var45, int threadID$index$i$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i$var45 = forStart$index$i$var45; index$i$var45 < forEnd$index$i$var45; index$i$var45 += 1) {
							int i$var45 = index$i$var45;
							int threadID$i$var45 = threadID$index$i$var45;
							parallelFor(RNG$1, 0, state.k, 1,
								(int forStart$j$var55, int forEnd$j$var55, int threadID$j$var55, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var55 = forStart$j$var55; j$var55 < forEnd$j$var55; j$var55 += 1)
											state.phi[i$var45][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
								}
							);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample24)
			parallelFor(state.RNG$, 0, state.k, 1,
				(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1)
							state.weights[var23] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!state.fixedFlag$sample31)
			state.bias = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		if(!state.fixedFlag$sample35)
			state.tau = DistributionSampling.sampleInverseGamma(state.RNG$, 3.0, 1.0);
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$index$i$var45, int forEnd$index$i$var45, int threadID$index$i$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var45 = forStart$index$i$var45; index$i$var45 < forEnd$index$i$var45; index$i$var45 += 1) {
						int i$var45 = index$i$var45;
						int threadID$i$var45 = threadID$index$i$var45;
						parallelFor(RNG$1, 0, state.k, 1,
							(int forStart$j$var55, int forEnd$j$var55, int threadID$j$var55, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var55 = forStart$j$var55; j$var55 < forEnd$j$var55; j$var55 += 1)
										state.phi[i$var45][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
							}
						);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample24) {
				for(int var23 = 0; var23 < state.k; var23 += 1)
					inferSample24(var23);
			}
			if(!state.fixedFlag$sample31)
				inferSample31();
			if(!state.fixedFlag$sample35)
				inferSample35();
		} else {
			if(!state.fixedFlag$sample35)
				inferSample35();
			if(!state.fixedFlag$sample31)
				inferSample31();
			if(!state.fixedFlag$sample24) {
				for(int var23 = (state.k - 1); var23 >= 0; var23 -= 1)
					inferSample24(var23);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int var23 = 0; var23 < state.k; var23 += 1) {
			if(!state.constrainedFlag$sample24[var23])
				drawValueSample24(var23);
		}
		if(!state.constrainedFlag$sample31)
			drawValueSample31();
		if(!state.constrainedFlag$sample35)
			drawValueSample35();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$weights = 0.0;
		if(!state.fixedProbFlag$sample24) {
			for(int var23 = 0; var23 < state.k; var23 += 1)
				state.logProbability$sample24[var23] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample31)
			state.logProbability$bias = Double.NaN;
		if(!state.fixedProbFlag$sample35)
			state.logProbability$tau = Double.NaN;
		state.logProbability$y = 0.0;
		if(!state.fixedProbFlag$sample74) {
			for(int i$var45 = 0; i$var45 < state.n; i$var45 += 1)
				state.logProbability$sample74[i$var45] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.n = state.x.length;
		state.k = state.x[0].length;
		for(int index$constrainedFlag$sample24$1 = 0; index$constrainedFlag$sample24$1 < state.constrainedFlag$sample24.length; index$constrainedFlag$sample24$1 += 1)
			state.constrainedFlag$sample24[index$constrainedFlag$sample24$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample24)
			logProbabilityValue$sample24();
		if(state.fixedFlag$sample31)
			logProbabilityValue$sample31();
		if(state.fixedFlag$sample35)
			logProbabilityValue$sample35();
		logProbabilityValue$sample74();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample24();
		logProbabilityValue$sample31();
		logProbabilityValue$sample35();
		logProbabilityValue$sample74();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample24();
		logProbabilityValue$sample31();
		logProbabilityValue$sample35();
		logProbabilityValue$sample74();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.y.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.y[cv$index1] = state.yMeasured[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$index$i$var45, int forEnd$index$i$var45, int threadID$index$i$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var45 = forStart$index$i$var45; index$i$var45 < forEnd$index$i$var45; index$i$var45 += 1) {
						int i$var45 = index$i$var45;
						int threadID$i$var45 = threadID$index$i$var45;
						parallelFor(RNG$1, 0, state.k, 1,
							(int forStart$j$var55, int forEnd$j$var55, int threadID$j$var55, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var55 = forStart$j$var55; j$var55 < forEnd$j$var55; j$var55 += 1)
										state.phi[i$var45][j$var55] = (state.weights[j$var55] * state.x[i$var45][j$var55]);
							}
						);
					}
			}
		);
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
		     + "model LinearRegressionTest(double[][] x, double[] yMeasured) {\n"
		     + "\n"
		     + "        int n = x.length;\n"
		     + "        int k = x[0].length;\n"
		     + "\n"
		     + "        double[] y = new double[n];\n"
		     + "\n"
		     + "        double[] weights = gaussian(0,10).sample(k);\n"
		     + "        double bias = gaussian(0,10).sample();\n"
		     + "        double tau = inverseGamma(3.0,1.0).sample();\n"
		     + "\n"
		     + "        for(int i:[0..n)) {\n"
		     + "            double[] phi = new double[k];\n"
		     + "            for(int j:[0..k,1))\n"
		     + "                phi[j] = weights[j] * x[i][j];\n"
		     + "            \n"
		     + "            y[i] = gaussian(sum(phi) + bias, tau).sample();\n"
		     + "        }\n"
		     + "        \n"
		     + "        y.observe(yMeasured);\n"
		     + "\n"
		     + "    private double sum(double[] a) {\n"
		     + "        return reduce(a, 0, (i,j) -> {\n"
		     + "            return i + j;\n"
		     + "        });\n"
		     + "    }\n"
		     + "}\n"
		     + "";
	}
}