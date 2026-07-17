package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.GaussianMixtureTest$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.GaussianMixtureTest.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class GaussianMixtureTest$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var17$countGlobal;
		double[][] cv$var68$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			cv$var17$countGlobal = new double[5];
			int cv$threadCount = threadCount();
			cv$var68$stateProbabilityGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var68$stateProbabilityGlobal[cv$index] = new double[5];
		}
	}


	public GaussianMixtureTest$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample17() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, 5, state.phi);
	}

	private final void drawValueSample34(int var33, int threadID$cv$var33, Rng RNG$) {
		state.mu[var33] = (DistributionSampling.sampleGaussian(RNG$) * 4.47213595499958);
	}

	private final void drawValueSample52(int var51, int threadID$cv$var51, Rng RNG$) {
		state.sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
	}

	private final void drawValueSample68(int i$var66, int threadID$cv$i$var66, Rng RNG$) {
		state.z[i$var66] = DistributionSampling.sampleCategorical(RNG$, state.phi, 5);
	}

	private final void inferSample17() {
		state.constrainedFlag$sample17 = false;
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			scratch.cv$var17$countGlobal[cv$loopIndex] = 0.0;
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1) {
			if(state.constrainedFlag$sample68[i$var66]) {
				state.constrainedFlag$sample17 = true;
				scratch.cv$var17$countGlobal[state.z[i$var66]] = (scratch.cv$var17$countGlobal[state.z[i$var66]] + 1.0);
			}
		}
		if(state.constrainedFlag$sample17)
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.alpha, scratch.cv$var17$countGlobal, state.phi, 5);
	}

	private final void inferSample34(int var33, int threadID$cv$var33, Rng RNG$) {
		state.constrainedFlag$sample34[var33] = false;
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1) {
			if((var33 == state.z[i$var66])) {
				state.constrainedFlag$sample34[var33] = true;
				cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
				cv$sum = (cv$sum + state.x[i$var66]);
				if(cv$sigmaNotFound) {
					cv$sigmaValue = state.sigma[state.z[i$var66]];
					cv$sigmaNotFound = false;
				}
			}
		}
		if(state.constrainedFlag$sample34[var33])
			state.mu[var33] = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 20.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void inferSample52(int var51, int threadID$cv$var51, Rng RNG$) {
		state.constrainedFlag$sample52[var51] = false;
		double cv$sum = 0.0;
		int cv$count = 0;
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1) {
			if((var51 == state.z[i$var66])) {
				state.constrainedFlag$sample52[var51] = true;
				double cv$var71$diff = (state.mu[state.z[i$var66]] - state.x[i$var66]);
				cv$sum = (cv$sum + (cv$var71$diff * cv$var71$diff));
				cv$count = (cv$count + 1);
			}
		}
		if(state.constrainedFlag$sample52[var51])
			state.sigma[var51] = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void inferSample68(int i$var66, int threadID$cv$i$var66, Rng RNG$) {
		state.constrainedFlag$sample68[i$var66] = false;
		double[] cv$stateProbabilityLocal = scratch.cv$var68$stateProbabilityGlobal[threadID$cv$i$var66];
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			state.z[i$var66] = cv$valuePos;
			state.constrainedFlag$sample68[i$var66] = true;
			double var70 = state.sigma[cv$valuePos];
			cv$stateProbabilityLocal[cv$valuePos] = (((0.0 < var70)?(DistributionSampling.logProbabilityGaussian(((state.x[i$var66] - state.mu[cv$valuePos]) / Math.sqrt(var70))) - (Math.log(var70) * 0.5)):Double.NEGATIVE_INFINITY) + (((0.0 <= state.phi[cv$valuePos]) && (state.phi[cv$valuePos] <= 1.0))?Math.log(state.phi[cv$valuePos]):Double.NEGATIVE_INFINITY));
		}
		if(state.constrainedFlag$sample68[i$var66]) {
			double cv$logSum;
			double cv$lseMax = cv$stateProbabilityLocal[0];
			{
				double cv$lseElementValue = cv$stateProbabilityLocal[1];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			{
				double cv$lseElementValue = cv$stateProbabilityLocal[2];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			{
				double cv$lseElementValue = cv$stateProbabilityLocal[3];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			double cv$lseElementValue = cv$stateProbabilityLocal[4];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < 5; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = 0.2;
			} else {
				for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = 5; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.z[i$var66] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 5);
		}
	}

	private final void logProbabilityValue$sample17() {
		if(!state.fixedProbFlag$sample17) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(state.phi, state.alpha, 5);
			state.logProbability$phi = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample17)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample17 = state.fixedFlag$sample17;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$phi);
			if(state.fixedFlag$sample17)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$phi);
		}
	}

	private final void logProbabilityValue$sample34() {
		if(!state.fixedProbFlag$sample34) {
			double cv$sampleAccumulator = 0.0;
			for(int var33 = 0; var33 < 5; var33 += 1)
				cv$sampleAccumulator = ((cv$sampleAccumulator + DistributionSampling.logProbabilityGaussian((state.mu[var33] / 4.47213595499958))) - 1.4978661367769954);
			state.logProbability$var34 = cv$sampleAccumulator;
			state.logProbability$mu = (state.logProbability$mu + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample34)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample34 = state.fixedFlag$sample34;
		} else {
			state.logProbability$mu = (state.logProbability$mu + state.logProbability$var34);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var34);
			if(state.fixedFlag$sample34)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var34);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!state.fixedProbFlag$sample52) {
			double cv$sampleAccumulator = 0.0;
			for(int var51 = 0; var51 < 5; var51 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(state.sigma[var51], 1.0, 1.0));
			state.logProbability$var52 = cv$sampleAccumulator;
			state.logProbability$sigma = (state.logProbability$sigma + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample52)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample52 = state.fixedFlag$sample52;
		} else {
			state.logProbability$sigma = (state.logProbability$sigma + state.logProbability$var52);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var52);
			if(state.fixedFlag$sample52)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var52);
		}
	}

	private final void logProbabilityValue$sample68() {
		double cv$accumulator = 0.0;
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1) {
			int cv$sampleValue = state.z[i$var66];
			double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= state.phi[cv$sampleValue])) && (state.phi[cv$sampleValue] <= 1.0))?Math.log(state.phi[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
			state.logProbability$sample68[i$var66] = cv$distributionAccumulator;
		}
		state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
	}

	private final void logProbabilityValue$sample72() {
		double cv$accumulator = 0.0;
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1) {
			double var70 = state.sigma[state.z[i$var66]];
			double cv$distributionAccumulator = ((0.0 < var70)?(DistributionSampling.logProbabilityGaussian(((state.x[i$var66] - state.mu[state.z[i$var66]]) / Math.sqrt(var70))) - (Math.log(var70) * 0.5)):Double.NEGATIVE_INFINITY);
			cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
			state.logProbability$sample72[i$var66] = cv$distributionAccumulator;
		}
		state.logProbability$x = (state.logProbability$x + cv$accumulator);
		state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
		state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, 5, state.phi);
		if(!state.fixedFlag$sample34)
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1)
							state.mu[var33] = (DistributionSampling.sampleGaussian(RNG$1) * 4.47213595499958);
				}
			);

		if(!state.fixedFlag$sample52)
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1)
							state.sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		parallelFor(state.RNG$, 0, state.length$xMeasured, 1,
			(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1) {
						state.z[i$var66] = DistributionSampling.sampleCategorical(RNG$1, state.phi, 5);
						state.x[i$var66] = ((Math.sqrt(state.sigma[state.z[i$var66]]) * DistributionSampling.sampleGaussian(RNG$1)) + state.mu[state.z[i$var66]]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, 5, state.phi);
		if(!state.fixedFlag$sample34)
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1)
							state.mu[var33] = (DistributionSampling.sampleGaussian(RNG$1) * 4.47213595499958);
				}
			);

		if(!state.fixedFlag$sample52)
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1)
							state.sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		parallelFor(state.RNG$, 0, state.length$xMeasured, 1,
			(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1)
						state.z[i$var66] = DistributionSampling.sampleCategorical(RNG$1, state.phi, 5);
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, 5, state.phi);
		if(!state.fixedFlag$sample34)
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1)
							state.mu[var33] = (DistributionSampling.sampleGaussian(RNG$1) * 4.47213595499958);
				}
			);

		if(!state.fixedFlag$sample52)
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1)
							state.sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		parallelFor(state.RNG$, 0, state.length$xMeasured, 1,
			(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1) {
						state.z[i$var66] = DistributionSampling.sampleCategorical(RNG$1, state.phi, 5);
						state.x[i$var66] = ((Math.sqrt(state.sigma[state.z[i$var66]]) * DistributionSampling.sampleGaussian(RNG$1)) + state.mu[state.z[i$var66]]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, 5, state.phi);
		if(!state.fixedFlag$sample34)
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1)
							state.mu[var33] = (DistributionSampling.sampleGaussian(RNG$1) * 4.47213595499958);
				}
			);

		if(!state.fixedFlag$sample52)
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1)
							state.sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		parallelFor(state.RNG$, 0, state.length$xMeasured, 1,
			(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1)
						state.z[i$var66] = DistributionSampling.sampleCategorical(RNG$1, state.phi, 5);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, 5, state.phi);
		if(!state.fixedFlag$sample34)
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1)
							state.mu[var33] = (DistributionSampling.sampleGaussian(RNG$1) * 4.47213595499958);
				}
			);

		if(!state.fixedFlag$sample52)
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1)
							state.sigma[var51] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		parallelFor(state.RNG$, 0, state.length$xMeasured, 1,
			(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1)
						state.z[i$var66] = DistributionSampling.sampleCategorical(RNG$1, state.phi, 5);
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample17)
				inferSample17();
			if(!state.fixedFlag$sample34)
				parallelFor(state.RNG$, 0, 5, 1,
					(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1)
								inferSample34(var33, threadID$var33, RNG$1);
					}
				);

			if(!state.fixedFlag$sample52)
				parallelFor(state.RNG$, 0, 5, 1,
					(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1)
								inferSample52(var51, threadID$var51, RNG$1);
					}
				);

			parallelFor(state.RNG$, 0, state.length$xMeasured, 1,
				(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1)
							inferSample68(i$var66, threadID$i$var66, RNG$1);
				}
			);
		} else {
			parallelFor(state.RNG$, 0, state.length$xMeasured, 1,
				(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1)
							inferSample68(i$var66, threadID$i$var66, RNG$1);
				}
			);
			if(!state.fixedFlag$sample52)
				parallelFor(state.RNG$, 0, 5, 1,
					(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1)
								inferSample52(var51, threadID$var51, RNG$1);
					}
				);

			if(!state.fixedFlag$sample34)
				parallelFor(state.RNG$, 0, 5, 1,
					(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1)
								inferSample34(var33, threadID$var33, RNG$1);
					}
				);

			if(!state.fixedFlag$sample17)
				inferSample17();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample17)
			drawValueSample17();
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$var33, int forEnd$var33, int threadID$var33, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var33 = forStart$var33; var33 < forEnd$var33; var33 += 1) {
						if(!state.constrainedFlag$sample34[var33])
							drawValueSample34(var33, threadID$var33, RNG$1);
					}
			}
		);
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1) {
						if(!state.constrainedFlag$sample52[var51])
							drawValueSample52(var51, threadID$var51, RNG$1);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.length$xMeasured, 1,
			(int forStart$i$var66, int forEnd$i$var66, int threadID$i$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var66 = forStart$i$var66; i$var66 < forEnd$i$var66; i$var66 += 1) {
						if(!state.constrainedFlag$sample68[i$var66])
							drawValueSample68(i$var66, threadID$i$var66, RNG$1);
					}
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample17)
			state.logProbability$phi = Double.NaN;
		state.logProbability$mu = 0.0;
		if(!state.fixedProbFlag$sample34)
			state.logProbability$var34 = Double.NaN;
		state.logProbability$sigma = 0.0;
		if(!state.fixedProbFlag$sample52)
			state.logProbability$var52 = Double.NaN;
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1)
			state.logProbability$sample68[i$var66] = Double.NaN;
		state.logProbability$x = 0.0;
		for(int i$var66 = 0; i$var66 < state.length$xMeasured; i$var66 += 1)
			state.logProbability$sample72[i$var66] = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		for(int i$var13 = 0; i$var13 < 5; i$var13 += 1)
			state.alpha[i$var13] = 1.0;
		for(int index$constrainedFlag$sample52$1 = 0; index$constrainedFlag$sample52$1 < state.constrainedFlag$sample52.length; index$constrainedFlag$sample52$1 += 1)
			state.constrainedFlag$sample52[index$constrainedFlag$sample52$1] = true;
		for(int index$constrainedFlag$sample68$1 = 0; index$constrainedFlag$sample68$1 < state.constrainedFlag$sample68.length; index$constrainedFlag$sample68$1 += 1)
			state.constrainedFlag$sample68[index$constrainedFlag$sample68$1] = true;
		for(int index$constrainedFlag$sample34$1 = 0; index$constrainedFlag$sample34$1 < state.constrainedFlag$sample34.length; index$constrainedFlag$sample34$1 += 1)
			state.constrainedFlag$sample34[index$constrainedFlag$sample34$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample17)
			logProbabilityValue$sample17();
		if(state.fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(state.fixedFlag$sample52)
			logProbabilityValue$sample52();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample34();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample34();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample72();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.x.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.x[cv$index1] = state.xMeasured[cv$index1];
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
		     + "model GaussianMixtureTest(double[] xMeasured) {\n"
		     + "\n"
		     + "        int k = 5;\n"
		     + "\n"
		     + "        double[] alpha = new double[k];\n"
		     + "        for(int i:[0..k)) \n"
		     + "            alpha[i] = 1.0;\n"
		     + "        \n"
		     + "        double[] phi = dirichlet(alpha).sample();\n"
		     + "        double[] mu = gaussian(0, 20).sample(k);\n"
		     + "        double[] sigma = inverseGamma(1, 1).sample(k);\n"
		     + "        \n"
		     + "        double[] x = new double[xMeasured.length];\n"
		     + "        for(int i:[0..xMeasured.length)) {\n"
		     + "            int z = categorical(phi).sample();\n"
		     + "            x[i] = gaussian(mu[z], sigma[z]).sample();\n"
		     + "        }\n"
		     + "        \n"
		     + "        x.observe(xMeasured);\n"
		     + "}\n"
		     + "";
	}
}