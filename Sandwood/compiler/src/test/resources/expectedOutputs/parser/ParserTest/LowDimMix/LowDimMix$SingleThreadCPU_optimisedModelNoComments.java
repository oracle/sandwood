package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.LowDimMix$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.LowDimMix.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class LowDimMix$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var97$stateProbabilityGlobal;
		boolean[] guard$sample20if124$global;

		@Override
		public final void allocateScratch() {
			cv$var97$stateProbabilityGlobal = new double[2];
			guard$sample20if124$global = new boolean[2];
		}
	}


	public LowDimMix$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample101(int var96) {
		state.component[var96] = DistributionSampling.sampleBernoulli(state.RNG$, state.theta);
	}

	private final void drawValueSample20(int var19) {
		state.rawMu[var19] = (DistributionSampling.sampleGaussian(state.RNG$) * 2.0);
		boolean guard$sample20put43 = false;
		if((var19 == 0)) {
			guard$sample20put43 = true;
			double var39;
			if((state.rawMu[0] < state.rawMu[1]))
				var39 = state.rawMu[0];
			else
				var39 = state.rawMu[1];
			state.mu[0] = var39;
		}
		if(((var19 == 1) && !guard$sample20put43)) {
			guard$sample20put43 = true;
			double var39;
			if((state.rawMu[0] < state.rawMu[1]))
				var39 = state.rawMu[0];
			else
				var39 = state.rawMu[1];
			state.mu[0] = var39;
		}
		if((((state.rawMu[0] < state.rawMu[1]) && (var19 == 0)) && !guard$sample20put43)) {
			guard$sample20put43 = true;
			state.mu[0] = state.rawMu[0];
		}
		if(((!(state.rawMu[0] < state.rawMu[1]) && (var19 == 1)) && !guard$sample20put43))
			state.mu[0] = state.rawMu[1];
		boolean guard$sample20put63 = false;
		if((var19 == 0)) {
			guard$sample20put63 = true;
			double var57;
			if((state.rawMu[0] < state.rawMu[1]))
				var57 = state.rawMu[1];
			else
				var57 = state.rawMu[0];
			state.mu[1] = var57;
		}
		if((var19 == 1)) {
			if(!guard$sample20put63) {
				guard$sample20put63 = true;
				double var57;
				if((state.rawMu[0] < state.rawMu[1]))
					var57 = state.rawMu[1];
				else
					var57 = state.rawMu[0];
				state.mu[1] = var57;
			}
			if(((state.rawMu[0] < state.rawMu[1]) && !guard$sample20put63)) {
				guard$sample20put63 = true;
				state.mu[1] = state.rawMu[1];
			}
		}
		if(((!(state.rawMu[0] < state.rawMu[1]) && (var19 == 0)) && !guard$sample20put63))
			state.mu[1] = state.rawMu[0];
	}

	private final void drawValueSample83(int var78) {
		state.sigma[var78] = (DistributionSampling.sampleTruncatedGaussian(state.RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
	}

	private final void drawValueSample88() {
		state.theta = DistributionSampling.sampleBeta(state.RNG$, 5.0, 5.0);
	}

	private final void inferSample101(int var96) {
		{
			state.component[var96] = false;
			double cv$accumulatedProbabilities = (((0.0 <= state.theta) && (state.theta <= 1.0))?Math.log((1.0 - state.theta)):Double.NEGATIVE_INFINITY);
			if(state.component[var96]) {
				{
					double componentSigma = state.sigma[0];
					double var128 = (componentSigma * componentSigma);
					cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[var96] - state.mu[0]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				scratch.guard$sample20if124$global[0] = false;
				scratch.guard$sample20if124$global[1] = false;
				if((state.rawMu[0] < state.rawMu[1]))
					scratch.guard$sample20if124$global[0] = false;
				else
					scratch.guard$sample20if124$global[1] = false;
				if(!scratch.guard$sample20if124$global[0]) {
					scratch.guard$sample20if124$global[0] = true;
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				if(!scratch.guard$sample20if124$global[1]) {
					scratch.guard$sample20if124$global[1] = true;
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				if(((state.rawMu[0] < state.rawMu[1]) && !scratch.guard$sample20if124$global[0])) {
					scratch.guard$sample20if124$global[0] = true;
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				if((!(state.rawMu[0] < state.rawMu[1]) && !scratch.guard$sample20if124$global[1])) {
					scratch.guard$sample20if124$global[1] = true;
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				double traceTempVariable$componentSigma$58_1 = state.sigma[0];
				double var128 = (traceTempVariable$componentSigma$58_1 * traceTempVariable$componentSigma$58_1);
				cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[var96] - state.mu[0]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				cv$accumulatedProbabilities = ((((0.0 <= state.sigma[0]) && (state.sigma[0] <= 1.0E100))?DistributionSampling.logProbabilityGaussian((state.sigma[0] / 2.0)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			} else {
				{
					double componentSigma = state.sigma[1];
					double var128 = (componentSigma * componentSigma);
					cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[var96] - state.mu[1]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				scratch.guard$sample20if124$global[0] = false;
				scratch.guard$sample20if124$global[1] = false;
				if((state.rawMu[0] < state.rawMu[1]))
					scratch.guard$sample20if124$global[1] = false;
				else
					scratch.guard$sample20if124$global[0] = false;
				if(!scratch.guard$sample20if124$global[0]) {
					scratch.guard$sample20if124$global[0] = true;
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				if(!scratch.guard$sample20if124$global[1]) {
					scratch.guard$sample20if124$global[1] = true;
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				if(((state.rawMu[0] < state.rawMu[1]) && !scratch.guard$sample20if124$global[1])) {
					scratch.guard$sample20if124$global[1] = true;
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				if((!(state.rawMu[0] < state.rawMu[1]) && !scratch.guard$sample20if124$global[0])) {
					scratch.guard$sample20if124$global[0] = true;
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				double traceTempVariable$componentSigma$63_1 = state.sigma[1];
				double var128 = (traceTempVariable$componentSigma$63_1 * traceTempVariable$componentSigma$63_1);
				cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[var96] - state.mu[1]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				cv$accumulatedProbabilities = ((((0.0 <= state.sigma[1]) && (state.sigma[1] <= 1.0E100))?DistributionSampling.logProbabilityGaussian((state.sigma[1] / 2.0)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			scratch.cv$var97$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.component[var96] = true;
		double cv$accumulatedProbabilities = (((0.0 <= state.theta) && (state.theta <= 1.0))?Math.log(state.theta):Double.NEGATIVE_INFINITY);
		if(state.component[var96]) {
			{
				double componentSigma = state.sigma[0];
				double var128 = (componentSigma * componentSigma);
				cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[var96] - state.mu[0]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			scratch.guard$sample20if124$global[0] = false;
			scratch.guard$sample20if124$global[1] = false;
			if((state.rawMu[0] < state.rawMu[1]))
				scratch.guard$sample20if124$global[0] = false;
			else
				scratch.guard$sample20if124$global[1] = false;
			if(!scratch.guard$sample20if124$global[0]) {
				scratch.guard$sample20if124$global[0] = true;
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			if(!scratch.guard$sample20if124$global[1]) {
				scratch.guard$sample20if124$global[1] = true;
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			if(((state.rawMu[0] < state.rawMu[1]) && !scratch.guard$sample20if124$global[0])) {
				scratch.guard$sample20if124$global[0] = true;
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			if((!(state.rawMu[0] < state.rawMu[1]) && !scratch.guard$sample20if124$global[1])) {
				scratch.guard$sample20if124$global[1] = true;
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			double traceTempVariable$componentSigma$58_1 = state.sigma[0];
			state.constrainedFlag$sample101[var96] = true;
			double var128 = (traceTempVariable$componentSigma$58_1 * traceTempVariable$componentSigma$58_1);
			cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[var96] - state.mu[0]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			cv$accumulatedProbabilities = ((((0.0 <= state.sigma[0]) && (state.sigma[0] <= 1.0E100))?DistributionSampling.logProbabilityGaussian((state.sigma[0] / 2.0)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		} else {
			{
				double componentSigma = state.sigma[1];
				double var128 = (componentSigma * componentSigma);
				cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[var96] - state.mu[1]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			scratch.guard$sample20if124$global[0] = false;
			scratch.guard$sample20if124$global[1] = false;
			if((state.rawMu[0] < state.rawMu[1]))
				scratch.guard$sample20if124$global[1] = false;
			else
				scratch.guard$sample20if124$global[0] = false;
			if(!scratch.guard$sample20if124$global[0]) {
				scratch.guard$sample20if124$global[0] = true;
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			if(!scratch.guard$sample20if124$global[1]) {
				scratch.guard$sample20if124$global[1] = true;
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			if(((state.rawMu[0] < state.rawMu[1]) && !scratch.guard$sample20if124$global[1])) {
				scratch.guard$sample20if124$global[1] = true;
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			if((!(state.rawMu[0] < state.rawMu[1]) && !scratch.guard$sample20if124$global[0])) {
				scratch.guard$sample20if124$global[0] = true;
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			double traceTempVariable$componentSigma$63_1 = state.sigma[1];
			state.constrainedFlag$sample101[var96] = true;
			double var128 = (traceTempVariable$componentSigma$63_1 * traceTempVariable$componentSigma$63_1);
			cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[var96] - state.mu[1]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			cv$accumulatedProbabilities = ((((0.0 <= state.sigma[1]) && (state.sigma[1] <= 1.0E100))?DistributionSampling.logProbabilityGaussian((state.sigma[1] / 2.0)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		scratch.cv$var97$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample101[var96]) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var97$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var97$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var97$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var97$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var97$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var97$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var97$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var97$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var97$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var97$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var97$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var97$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.component[var96] = (DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var97$stateProbabilityGlobal, 2) == 1);
		}
	}

	private final void inferSample20(int var19) {
		state.constrainedFlag$sample20[var19] = false;
		double cv$originalValue = state.rawMu[var19];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 2.0)) - 0.6931471805599453);
			if(((state.rawMu[0] < state.rawMu[1]) && (var19 == 0))) {
				for(int n = 0; n < state.N; n += 1) {
					if(state.component[n]) {
						state.constrainedFlag$sample20[0] = true;
						double componentSigma = state.sigma[0];
						double var128 = (componentSigma * componentSigma);
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - cv$originalValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var19 == 1)) {
				if((state.rawMu[0] < state.rawMu[1])) {
					for(int n = 0; n < state.N; n += 1) {
						if(!state.component[n]) {
							state.constrainedFlag$sample20[1] = true;
							double componentSigma = state.sigma[1];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - cv$originalValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
				} else {
					for(int n = 0; n < state.N; n += 1) {
						if(state.component[n]) {
							state.constrainedFlag$sample20[1] = true;
							double componentSigma = state.sigma[0];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - cv$originalValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			if((!(state.rawMu[0] < state.rawMu[1]) && (var19 == 0))) {
				for(int n = 0; n < state.N; n += 1) {
					if(!state.component[n]) {
						state.constrainedFlag$sample20[0] = true;
						double componentSigma = state.sigma[1];
						double var128 = (componentSigma * componentSigma);
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - cv$originalValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			boolean guard$sample20if41 = false;
			if((var19 == 0)) {
				guard$sample20if41 = true;
				if((state.rawMu[0] < state.rawMu[1])) {
					double traceTempVariable$var115$36_2 = state.rawMu[0];
					for(int n = 0; n < state.N; n += 1) {
						if(state.component[n]) {
							state.constrainedFlag$sample20[0] = true;
							double componentSigma = state.sigma[0];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - traceTempVariable$var115$36_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				} else {
					double traceTempVariable$var115$52_2 = state.rawMu[1];
					for(int n = 0; n < state.N; n += 1) {
						if(state.component[n]) {
							state.constrainedFlag$sample20[0] = true;
							double componentSigma = state.sigma[0];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - traceTempVariable$var115$52_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			if(((var19 == 1) && !guard$sample20if41)) {
				if((state.rawMu[0] < state.rawMu[1])) {
					double traceTempVariable$var115$37_2 = state.rawMu[0];
					for(int n = 0; n < state.N; n += 1) {
						if(state.component[n]) {
							state.constrainedFlag$sample20[1] = true;
							double componentSigma = state.sigma[0];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - traceTempVariable$var115$37_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				} else {
					double traceTempVariable$var115$53_2 = state.rawMu[1];
					for(int n = 0; n < state.N; n += 1) {
						if(state.component[n]) {
							state.constrainedFlag$sample20[1] = true;
							double componentSigma = state.sigma[0];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - traceTempVariable$var115$53_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			boolean guard$sample20if61 = false;
			if((var19 == 0)) {
				guard$sample20if61 = true;
				if((state.rawMu[0] < state.rawMu[1])) {
					double traceTempVariable$var117$72_2 = state.rawMu[1];
					for(int n = 0; n < state.N; n += 1) {
						if(!state.component[n]) {
							state.constrainedFlag$sample20[0] = true;
							double componentSigma = state.sigma[1];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - traceTempVariable$var117$72_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				} else {
					double traceTempVariable$var117$88_2 = state.rawMu[0];
					for(int n = 0; n < state.N; n += 1) {
						if(!state.component[n]) {
							state.constrainedFlag$sample20[0] = true;
							double componentSigma = state.sigma[1];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - traceTempVariable$var117$88_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			if(((var19 == 1) && !guard$sample20if61)) {
				if((state.rawMu[0] < state.rawMu[1])) {
					double traceTempVariable$var117$73_2 = state.rawMu[1];
					for(int n = 0; n < state.N; n += 1) {
						if(!state.component[n]) {
							state.constrainedFlag$sample20[1] = true;
							double componentSigma = state.sigma[1];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - traceTempVariable$var117$73_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				} else {
					double traceTempVariable$var117$89_2 = state.rawMu[0];
					for(int n = 0; n < state.N; n += 1) {
						if(!state.component[n]) {
							state.constrainedFlag$sample20[1] = true;
							double componentSigma = state.sigma[1];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - traceTempVariable$var117$89_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample20[var19]) {
			{
				state.rawMu[var19] = cv$proposedValue;
				boolean guard$sample20put43 = false;
				if((var19 == 0)) {
					guard$sample20put43 = true;
					double var39;
					if((state.rawMu[0] < state.rawMu[1]))
						var39 = state.rawMu[0];
					else
						var39 = state.rawMu[1];
					state.mu[0] = var39;
				}
				if(((var19 == 1) && !guard$sample20put43)) {
					guard$sample20put43 = true;
					double var39;
					if((state.rawMu[0] < state.rawMu[1]))
						var39 = state.rawMu[0];
					else
						var39 = state.rawMu[1];
					state.mu[0] = var39;
				}
				if((((state.rawMu[0] < state.rawMu[1]) && (var19 == 0)) && !guard$sample20put43)) {
					guard$sample20put43 = true;
					state.mu[0] = state.rawMu[0];
				}
				if(((!(state.rawMu[0] < state.rawMu[1]) && (var19 == 1)) && !guard$sample20put43))
					state.mu[0] = state.rawMu[1];
				boolean guard$sample20put63 = false;
				if((var19 == 0)) {
					guard$sample20put63 = true;
					double var57;
					if((state.rawMu[0] < state.rawMu[1]))
						var57 = state.rawMu[1];
					else
						var57 = state.rawMu[0];
					state.mu[1] = var57;
				}
				if((var19 == 1)) {
					if(!guard$sample20put63) {
						guard$sample20put63 = true;
						double var57;
						if((state.rawMu[0] < state.rawMu[1]))
							var57 = state.rawMu[1];
						else
							var57 = state.rawMu[0];
						state.mu[1] = var57;
					}
					if(((state.rawMu[0] < state.rawMu[1]) && !guard$sample20put63)) {
						guard$sample20put63 = true;
						state.mu[1] = state.rawMu[1];
					}
				}
				if(((!(state.rawMu[0] < state.rawMu[1]) && (var19 == 0)) && !guard$sample20put63))
					state.mu[1] = state.rawMu[0];
			}
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 2.0)) - 0.6931471805599453);
			if(((state.rawMu[0] < state.rawMu[1]) && (var19 == 0))) {
				for(int n = 0; n < state.N; n += 1) {
					if(state.component[n]) {
						state.constrainedFlag$sample20[0] = true;
						double componentSigma = state.sigma[0];
						double var128 = (componentSigma * componentSigma);
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - cv$proposedValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var19 == 1)) {
				if((state.rawMu[0] < state.rawMu[1])) {
					for(int n = 0; n < state.N; n += 1) {
						if(!state.component[n]) {
							state.constrainedFlag$sample20[1] = true;
							double componentSigma = state.sigma[1];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - cv$proposedValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
				} else {
					for(int n = 0; n < state.N; n += 1) {
						if(state.component[n]) {
							state.constrainedFlag$sample20[1] = true;
							double componentSigma = state.sigma[0];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - cv$proposedValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			if((!(state.rawMu[0] < state.rawMu[1]) && (var19 == 0))) {
				for(int n = 0; n < state.N; n += 1) {
					if(!state.component[n]) {
						state.constrainedFlag$sample20[0] = true;
						double componentSigma = state.sigma[1];
						double var128 = (componentSigma * componentSigma);
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - cv$proposedValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			boolean guard$sample20if41 = false;
			if((var19 == 0)) {
				guard$sample20if41 = true;
				if((state.rawMu[0] < state.rawMu[1])) {
					double traceTempVariable$var115$36_2 = state.rawMu[0];
					for(int n = 0; n < state.N; n += 1) {
						if(state.component[n]) {
							state.constrainedFlag$sample20[0] = true;
							double componentSigma = state.sigma[0];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - traceTempVariable$var115$36_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				} else {
					double traceTempVariable$var115$52_2 = state.rawMu[1];
					for(int n = 0; n < state.N; n += 1) {
						if(state.component[n]) {
							state.constrainedFlag$sample20[0] = true;
							double componentSigma = state.sigma[0];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - traceTempVariable$var115$52_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			if(((var19 == 1) && !guard$sample20if41)) {
				if((state.rawMu[0] < state.rawMu[1])) {
					double traceTempVariable$var115$37_2 = state.rawMu[0];
					for(int n = 0; n < state.N; n += 1) {
						if(state.component[n]) {
							state.constrainedFlag$sample20[1] = true;
							double componentSigma = state.sigma[0];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - traceTempVariable$var115$37_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				} else {
					double traceTempVariable$var115$53_2 = state.rawMu[1];
					for(int n = 0; n < state.N; n += 1) {
						if(state.component[n]) {
							state.constrainedFlag$sample20[1] = true;
							double componentSigma = state.sigma[0];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - traceTempVariable$var115$53_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			boolean guard$sample20if61 = false;
			if((var19 == 0)) {
				guard$sample20if61 = true;
				if((state.rawMu[0] < state.rawMu[1])) {
					double traceTempVariable$var117$72_2 = state.rawMu[1];
					for(int n = 0; n < state.N; n += 1) {
						if(!state.component[n]) {
							state.constrainedFlag$sample20[0] = true;
							double componentSigma = state.sigma[1];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - traceTempVariable$var117$72_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				} else {
					double traceTempVariable$var117$88_2 = state.rawMu[0];
					for(int n = 0; n < state.N; n += 1) {
						if(!state.component[n]) {
							state.constrainedFlag$sample20[0] = true;
							double componentSigma = state.sigma[1];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - traceTempVariable$var117$88_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			if(((var19 == 1) && !guard$sample20if61)) {
				if((state.rawMu[0] < state.rawMu[1])) {
					double traceTempVariable$var117$73_2 = state.rawMu[1];
					for(int n = 0; n < state.N; n += 1) {
						if(!state.component[n]) {
							state.constrainedFlag$sample20[1] = true;
							double componentSigma = state.sigma[1];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - traceTempVariable$var117$73_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				} else {
					double traceTempVariable$var117$89_2 = state.rawMu[0];
					for(int n = 0; n < state.N; n += 1) {
						if(!state.component[n]) {
							state.constrainedFlag$sample20[1] = true;
							double componentSigma = state.sigma[1];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - traceTempVariable$var117$89_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((state.rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				state.rawMu[var19] = cv$originalValue;
				boolean guard$sample20put43 = false;
				if((var19 == 0)) {
					guard$sample20put43 = true;
					double var39;
					if((state.rawMu[0] < state.rawMu[1]))
						var39 = state.rawMu[0];
					else
						var39 = state.rawMu[1];
					state.mu[0] = var39;
				}
				if(((var19 == 1) && !guard$sample20put43)) {
					guard$sample20put43 = true;
					double var39;
					if((state.rawMu[0] < state.rawMu[1]))
						var39 = state.rawMu[0];
					else
						var39 = state.rawMu[1];
					state.mu[0] = var39;
				}
				if((((state.rawMu[0] < state.rawMu[1]) && (var19 == 0)) && !guard$sample20put43)) {
					guard$sample20put43 = true;
					state.mu[0] = state.rawMu[0];
				}
				if(((!(state.rawMu[0] < state.rawMu[1]) && (var19 == 1)) && !guard$sample20put43))
					state.mu[0] = state.rawMu[1];
				boolean guard$sample20put63 = false;
				if((var19 == 0)) {
					guard$sample20put63 = true;
					double var57;
					if((state.rawMu[0] < state.rawMu[1]))
						var57 = state.rawMu[1];
					else
						var57 = state.rawMu[0];
					state.mu[1] = var57;
				}
				if((var19 == 1)) {
					if(!guard$sample20put63) {
						guard$sample20put63 = true;
						double var57;
						if((state.rawMu[0] < state.rawMu[1]))
							var57 = state.rawMu[1];
						else
							var57 = state.rawMu[0];
						state.mu[1] = var57;
					}
					if(((state.rawMu[0] < state.rawMu[1]) && !guard$sample20put63)) {
						guard$sample20put63 = true;
						state.mu[1] = state.rawMu[1];
					}
				}
				if(((!(state.rawMu[0] < state.rawMu[1]) && (var19 == 0)) && !guard$sample20put63))
					state.mu[1] = state.rawMu[0];
			}
		}
	}

	private final void inferSample83(int var78) {
		state.constrainedFlag$sample83[var78] = false;
		double cv$originalValue = state.sigma[var78];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue <= 1.0E100))?DistributionSampling.logProbabilityGaussian((cv$originalValue / 2.0)):Double.NEGATIVE_INFINITY);
			if((var78 == 0)) {
				for(int n = 0; n < state.N; n += 1) {
					if(state.component[n]) {
						state.constrainedFlag$sample83[0] = true;
						double var128 = (cv$originalValue * cv$originalValue);
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - state.mu[0]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var78 == 1)) {
				for(int n = 0; n < state.N; n += 1) {
					if(!state.component[n]) {
						state.constrainedFlag$sample83[1] = true;
						double var128 = (cv$originalValue * cv$originalValue);
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - state.mu[1]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample83[var78]) {
			state.sigma[var78] = cv$proposedValue;
			double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0E100))?DistributionSampling.logProbabilityGaussian((cv$proposedValue / 2.0)):Double.NEGATIVE_INFINITY);
			if((var78 == 0)) {
				for(int n = 0; n < state.N; n += 1) {
					if(state.component[n]) {
						state.constrainedFlag$sample83[0] = true;
						double var128 = (cv$proposedValue * cv$proposedValue);
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - state.mu[0]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var78 == 1)) {
				for(int n = 0; n < state.N; n += 1) {
					if(!state.component[n]) {
						state.constrainedFlag$sample83[1] = true;
						double var128 = (cv$proposedValue * cv$proposedValue);
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - state.mu[1]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio)))
				state.sigma[var78] = cv$originalValue;
		}
	}

	private final void inferSample88() {
		state.constrainedFlag$sample88 = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int var96 = 0; var96 < state.N; var96 += 1) {
			if((state.fixedFlag$sample101 || state.constrainedFlag$sample101[var96])) {
				state.constrainedFlag$sample88 = true;
				cv$count = (cv$count + 1);
				if(state.component[var96])
					cv$sum = (cv$sum + 1);
			}
		}
		if(state.constrainedFlag$sample88)
			state.theta = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 5.0, 5.0, cv$sum, cv$count);
	}

	private final void logProbabilityValue$sample101() {
		if(!state.fixedProbFlag$sample101) {
			double cv$sampleAccumulator = 0.0;
			for(int var96 = 0; var96 < state.N; var96 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= state.theta) && (state.theta <= 1.0))?Math.log((state.component[var96]?state.theta:(1.0 - state.theta))):Double.NEGATIVE_INFINITY));
			state.logProbability$componentDistribution = cv$sampleAccumulator;
			state.logProbability$var97 = cv$sampleAccumulator;
			state.logProbability$component = (state.logProbability$component + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample101)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample101 = (state.fixedFlag$sample101 && state.fixedFlag$sample88);
		} else {
			state.logProbability$componentDistribution = state.logProbability$var97;
			state.logProbability$component = (state.logProbability$component + state.logProbability$var97);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var97);
			if(state.fixedFlag$sample101)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var97);
		}
	}

	private final void logProbabilityValue$sample138() {
		if(!state.fixedProbFlag$sample138) {
			double cv$accumulator = 0.0;
			for(int n = 0; n < state.N; n += 1) {
				double componentMu;
				if(state.component[n])
					componentMu = state.mu[0];
				else
					componentMu = state.mu[1];
				double componentSigma;
				if(state.component[n])
					componentSigma = state.sigma[0];
				else
					componentSigma = state.sigma[1];
				double var128 = (componentSigma * componentSigma);
				double cv$distributionAccumulator = ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((state.y[n] - componentMu) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample138[n] = cv$distributionAccumulator;
			}
			state.logProbability$y = (state.logProbability$y + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample138 = ((state.fixedFlag$sample20 && state.fixedFlag$sample83) && state.fixedFlag$sample101);
		} else {
			double cv$accumulator = 0.0;
			for(int n = 0; n < state.N; n += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample138[n]);
			state.logProbability$y = (state.logProbability$y + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample20() {
		if(!state.fixedProbFlag$sample20) {
			double cv$sampleAccumulator;
			{
				double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian((state.rawMu[0] / 2.0)) - 0.6931471805599453);
				cv$sampleAccumulator = cv$weightedProbability;
				state.logProbability$sample20[0] = cv$weightedProbability;
				boolean cv$guard$mu = false;
				if((state.rawMu[0] < state.rawMu[1])) {
					cv$guard$mu = true;
					state.logProbability$mu = (state.logProbability$mu + cv$weightedProbability);
				}
				if((!(state.rawMu[0] < state.rawMu[1]) && !cv$guard$mu))
					state.logProbability$mu = (state.logProbability$mu + cv$weightedProbability);
			}
			double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian((state.rawMu[1] / 2.0)) - 0.6931471805599453);
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$weightedProbability);
			state.logProbability$sample20[1] = cv$weightedProbability;
			boolean cv$guard$mu = false;
			if(!(state.rawMu[0] < state.rawMu[1])) {
				cv$guard$mu = true;
				state.logProbability$mu = (state.logProbability$mu + cv$weightedProbability);
			}
			if(((state.rawMu[0] < state.rawMu[1]) && !cv$guard$mu))
				state.logProbability$mu = (state.logProbability$mu + cv$weightedProbability);
			state.logProbability$rawMu = (state.logProbability$rawMu + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample20)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample20 = state.fixedFlag$sample20;
		} else {
			double cv$rvAccumulator;
			{
				double cv$sampleValue = state.logProbability$sample20[0];
				cv$rvAccumulator = cv$sampleValue;
				boolean cv$guard$mu = false;
				if((state.rawMu[0] < state.rawMu[1])) {
					cv$guard$mu = true;
					state.logProbability$mu = (state.logProbability$mu + cv$sampleValue);
				}
				if((!(state.rawMu[0] < state.rawMu[1]) && !cv$guard$mu))
					state.logProbability$mu = (state.logProbability$mu + cv$sampleValue);
			}
			double cv$sampleValue = state.logProbability$sample20[1];
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			boolean cv$guard$mu = false;
			if(!(state.rawMu[0] < state.rawMu[1])) {
				cv$guard$mu = true;
				state.logProbability$mu = (state.logProbability$mu + cv$sampleValue);
			}
			if(((state.rawMu[0] < state.rawMu[1]) && !cv$guard$mu))
				state.logProbability$mu = (state.logProbability$mu + cv$sampleValue);
			state.logProbability$rawMu = (state.logProbability$rawMu + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			if(state.fixedFlag$sample20)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample83() {
		if(!state.fixedProbFlag$sample83) {
			double cv$sampleAccumulator;
			{
				double cv$sampleValue = state.sigma[0];
				cv$sampleAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue <= 1.0E100))?DistributionSampling.logProbabilityGaussian((cv$sampleValue / 2.0)):Double.NEGATIVE_INFINITY);
			}
			double cv$sampleValue = state.sigma[1];
			cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue <= 1.0E100))?DistributionSampling.logProbabilityGaussian((cv$sampleValue / 2.0)):Double.NEGATIVE_INFINITY));
			state.logProbability$var79 = cv$sampleAccumulator;
			state.logProbability$sigma = (state.logProbability$sigma + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample83)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample83 = state.fixedFlag$sample83;
		} else {
			state.logProbability$sigma = (state.logProbability$sigma + state.logProbability$var79);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var79);
			if(state.fixedFlag$sample83)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var79);
		}
	}

	private final void logProbabilityValue$sample88() {
		if(!state.fixedProbFlag$sample88) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.theta, 5.0, 5.0);
			state.logProbability$theta = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample88)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample88 = state.fixedFlag$sample88;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$theta);
			if(state.fixedFlag$sample88)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$theta);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample20) {
			state.rawMu[0] = (DistributionSampling.sampleGaussian(state.RNG$) * 2.0);
			state.rawMu[1] = (DistributionSampling.sampleGaussian(state.RNG$) * 2.0);
			double var39;
			if((state.rawMu[0] < state.rawMu[1]))
				var39 = state.rawMu[0];
			else
				var39 = state.rawMu[1];
			state.mu[0] = var39;
			double var57;
			if((state.rawMu[0] < state.rawMu[1]))
				var57 = state.rawMu[1];
			else
				var57 = state.rawMu[0];
			state.mu[1] = var57;
		}
		if(!state.fixedFlag$sample83) {
			state.sigma[0] = (DistributionSampling.sampleTruncatedGaussian(state.RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
			state.sigma[1] = (DistributionSampling.sampleTruncatedGaussian(state.RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
		}
		if(!state.fixedFlag$sample88)
			state.theta = DistributionSampling.sampleBeta(state.RNG$, 5.0, 5.0);
		if(!state.fixedFlag$sample101) {
			for(int var96 = 0; var96 < state.N; var96 += 1)
				state.component[var96] = DistributionSampling.sampleBernoulli(state.RNG$, state.theta);
		}
		for(int n = 0; n < state.N; n += 1) {
			double componentMu;
			if(state.component[n])
				componentMu = state.mu[0];
			else
				componentMu = state.mu[1];
			double componentSigma;
			if(state.component[n])
				componentSigma = state.sigma[0];
			else
				componentSigma = state.sigma[1];
			state.y[n] = ((componentSigma * DistributionSampling.sampleGaussian(state.RNG$)) + componentMu);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample20) {
			state.rawMu[0] = (DistributionSampling.sampleGaussian(state.RNG$) * 2.0);
			state.rawMu[1] = (DistributionSampling.sampleGaussian(state.RNG$) * 2.0);
		}
		double var39;
		if((state.rawMu[0] < state.rawMu[1]))
			var39 = state.rawMu[0];
		else
			var39 = state.rawMu[1];
		state.mu[0] = var39;
		double var57;
		if((state.rawMu[0] < state.rawMu[1]))
			var57 = state.rawMu[1];
		else
			var57 = state.rawMu[0];
		state.mu[1] = var57;
		if(!state.fixedFlag$sample83) {
			state.sigma[0] = (DistributionSampling.sampleTruncatedGaussian(state.RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
			state.sigma[1] = (DistributionSampling.sampleTruncatedGaussian(state.RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
		}
		if(!state.fixedFlag$sample88)
			state.theta = DistributionSampling.sampleBeta(state.RNG$, 5.0, 5.0);
		if(!state.fixedFlag$sample101) {
			for(int var96 = 0; var96 < state.N; var96 += 1)
				state.component[var96] = DistributionSampling.sampleBernoulli(state.RNG$, state.theta);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample20) {
			state.rawMu[0] = (DistributionSampling.sampleGaussian(state.RNG$) * 2.0);
			state.rawMu[1] = (DistributionSampling.sampleGaussian(state.RNG$) * 2.0);
		}
		double var39;
		if((state.rawMu[0] < state.rawMu[1]))
			var39 = state.rawMu[0];
		else
			var39 = state.rawMu[1];
		state.mu[0] = var39;
		double var57;
		if((state.rawMu[0] < state.rawMu[1]))
			var57 = state.rawMu[1];
		else
			var57 = state.rawMu[0];
		state.mu[1] = var57;
		if(!state.fixedFlag$sample83) {
			state.sigma[0] = (DistributionSampling.sampleTruncatedGaussian(state.RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
			state.sigma[1] = (DistributionSampling.sampleTruncatedGaussian(state.RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
		}
		if(!state.fixedFlag$sample88)
			state.theta = DistributionSampling.sampleBeta(state.RNG$, 5.0, 5.0);
		if(!state.fixedFlag$sample101) {
			for(int var96 = 0; var96 < state.N; var96 += 1)
				state.component[var96] = DistributionSampling.sampleBernoulli(state.RNG$, state.theta);
		}
		for(int n = 0; n < state.N; n += 1) {
			double componentMu;
			if(state.component[n])
				componentMu = state.mu[0];
			else
				componentMu = state.mu[1];
			double componentSigma;
			if(state.component[n])
				componentSigma = state.sigma[0];
			else
				componentSigma = state.sigma[1];
			state.y[n] = ((componentSigma * DistributionSampling.sampleGaussian(state.RNG$)) + componentMu);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample20) {
			state.rawMu[0] = (DistributionSampling.sampleGaussian(state.RNG$) * 2.0);
			state.rawMu[1] = (DistributionSampling.sampleGaussian(state.RNG$) * 2.0);
			double var39;
			if((state.rawMu[0] < state.rawMu[1]))
				var39 = state.rawMu[0];
			else
				var39 = state.rawMu[1];
			state.mu[0] = var39;
			double var57;
			if((state.rawMu[0] < state.rawMu[1]))
				var57 = state.rawMu[1];
			else
				var57 = state.rawMu[0];
			state.mu[1] = var57;
		}
		if(!state.fixedFlag$sample83) {
			state.sigma[0] = (DistributionSampling.sampleTruncatedGaussian(state.RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
			state.sigma[1] = (DistributionSampling.sampleTruncatedGaussian(state.RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
		}
		if(!state.fixedFlag$sample88)
			state.theta = DistributionSampling.sampleBeta(state.RNG$, 5.0, 5.0);
		if(!state.fixedFlag$sample101) {
			for(int var96 = 0; var96 < state.N; var96 += 1)
				state.component[var96] = DistributionSampling.sampleBernoulli(state.RNG$, state.theta);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample20) {
			state.rawMu[0] = (DistributionSampling.sampleGaussian(state.RNG$) * 2.0);
			state.rawMu[1] = (DistributionSampling.sampleGaussian(state.RNG$) * 2.0);
		}
		double var39;
		if((state.rawMu[0] < state.rawMu[1]))
			var39 = state.rawMu[0];
		else
			var39 = state.rawMu[1];
		state.mu[0] = var39;
		double var57;
		if((state.rawMu[0] < state.rawMu[1]))
			var57 = state.rawMu[1];
		else
			var57 = state.rawMu[0];
		state.mu[1] = var57;
		if(!state.fixedFlag$sample83) {
			state.sigma[0] = (DistributionSampling.sampleTruncatedGaussian(state.RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
			state.sigma[1] = (DistributionSampling.sampleTruncatedGaussian(state.RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
		}
		if(!state.fixedFlag$sample88)
			state.theta = DistributionSampling.sampleBeta(state.RNG$, 5.0, 5.0);
		if(!state.fixedFlag$sample101) {
			for(int var96 = 0; var96 < state.N; var96 += 1)
				state.component[var96] = DistributionSampling.sampleBernoulli(state.RNG$, state.theta);
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample20) {
				inferSample20(0);
				inferSample20(1);
			}
			if(!state.fixedFlag$sample83) {
				inferSample83(0);
				inferSample83(1);
			}
			if(!state.fixedFlag$sample88)
				inferSample88();
			if(!state.fixedFlag$sample101) {
				for(int var96 = 0; var96 < state.N; var96 += 1)
					inferSample101(var96);
			}
		} else {
			if(!state.fixedFlag$sample101) {
				for(int var96 = (state.N - 1); var96 >= 0; var96 -= 1)
					inferSample101(var96);
			}
			if(!state.fixedFlag$sample88)
				inferSample88();
			if(!state.fixedFlag$sample83) {
				inferSample83(1);
				inferSample83(0);
			}
			if(!state.fixedFlag$sample20) {
				inferSample20(1);
				inferSample20(0);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample20[0])
			drawValueSample20(0);
		if(!state.constrainedFlag$sample20[1])
			drawValueSample20(1);
		if(!state.constrainedFlag$sample83[0])
			drawValueSample83(0);
		if(!state.constrainedFlag$sample83[1])
			drawValueSample83(1);
		if(!state.constrainedFlag$sample88)
			drawValueSample88();
		for(int var96 = 0; var96 < state.N; var96 += 1) {
			if(!state.constrainedFlag$sample101[var96])
				drawValueSample101(var96);
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$rawMu = 0.0;
		state.logProbability$mu = 0.0;
		if(!state.fixedProbFlag$sample20) {
			state.logProbability$sample20[0] = Double.NaN;
			state.logProbability$sample20[1] = Double.NaN;
		}
		state.logProbability$sigma = 0.0;
		if(!state.fixedProbFlag$sample83)
			state.logProbability$var79 = Double.NaN;
		if(!state.fixedProbFlag$sample88)
			state.logProbability$theta = Double.NaN;
		state.logProbability$componentDistribution = Double.NaN;
		state.logProbability$component = 0.0;
		if(!state.fixedProbFlag$sample101)
			state.logProbability$var97 = Double.NaN;
		state.logProbability$y = 0.0;
		if(!state.fixedProbFlag$sample138) {
			for(int n = 0; n < state.N; n += 1)
				state.logProbability$sample138[n] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.N = state.length$yObserved;
		for(int index$constrainedFlag$sample101$1 = 0; index$constrainedFlag$sample101$1 < state.constrainedFlag$sample101.length; index$constrainedFlag$sample101$1 += 1)
			state.constrainedFlag$sample101[index$constrainedFlag$sample101$1] = true;
		for(int index$constrainedFlag$sample20$1 = 0; index$constrainedFlag$sample20$1 < state.constrainedFlag$sample20.length; index$constrainedFlag$sample20$1 += 1)
			state.constrainedFlag$sample20[index$constrainedFlag$sample20$1] = true;
		for(int index$constrainedFlag$sample83$1 = 0; index$constrainedFlag$sample83$1 < state.constrainedFlag$sample83.length; index$constrainedFlag$sample83$1 += 1)
			state.constrainedFlag$sample83[index$constrainedFlag$sample83$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample20)
			logProbabilityValue$sample20();
		if(state.fixedFlag$sample83)
			logProbabilityValue$sample83();
		if(state.fixedFlag$sample88)
			logProbabilityValue$sample88();
		if(state.fixedFlag$sample101)
			logProbabilityValue$sample101();
		logProbabilityValue$sample138();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample83();
		logProbabilityValue$sample88();
		logProbabilityValue$sample101();
		logProbabilityValue$sample138();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample83();
		logProbabilityValue$sample88();
		logProbabilityValue$sample101();
		logProbabilityValue$sample138();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.y.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.y[cv$index1] = state.yObserved[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		double var39;
		if((state.rawMu[0] < state.rawMu[1]))
			var39 = state.rawMu[0];
		else
			var39 = state.rawMu[1];
		state.mu[0] = var39;
		double var57;
		if((state.rawMu[0] < state.rawMu[1]))
			var57 = state.rawMu[1];
		else
			var57 = state.rawMu[0];
		state.mu[1] = var57;
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2026, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model LowDimMix(double[] yObserved) {\n"
		     + "    int N = yObserved.length;\n"
		     + "\n"
		     + "    // Stan parameter: ordered[2] mu; prior: mu ~ normal(0, 2)\n"
		     + "    // Sampling two unconstrained normal values and sorting them gives the same ordered support up to\n"
		     + "    // the constant normalisation factor for the ordered constraint.\n"
		     + "    double[] rawMu = gaussian(0.0, 2.0 * 2.0).sample(2);\n"
		     + "    double[] mu = new double[2];\n"
		     + "    mu[0] = rawMu[0] < rawMu[1] ? rawMu[0] : rawMu[1];\n"
		     + "    mu[1] = rawMu[0] < rawMu[1] ? rawMu[1] : rawMu[0];\n"
		     + "\n"
		     + "    // Stan parameter: array[2] real<lower=0> sigma; prior: sigma ~ normal(0, 2)\n"
		     + "    double[] sigma = truncatedGaussian(0.0, 2.0 * 2.0, 0.0, 1.0e100).sample(2);\n"
		     + "\n"
		     + "    // Stan parameter: real<lower=0, upper=1> theta; prior: theta ~ beta(5, 5)\n"
		     + "    double theta = beta(5.0, 5.0).sample();\n"
		     + "\n"
		     + "    // Stan likelihood:\n"
		     + "    // target += log_mix(theta, normal_lpdf(y[n] | mu[1], sigma[1]),\n"
		     + "    //                   normal_lpdf(y[n] | mu[2], sigma[2]));\n"
		     + "    // In Sandwood, represent the same two-component mixture with explicit latent component indicators.\n"
		     + "    Bernoulli componentDistribution = bernoulli(theta);\n"
		     + "    boolean[] component = componentDistribution.sample(N);\n"
		     + "    double[] y = new double[N];\n"
		     + "\n"
		     + "    for(int n = 0; n < N; n++) {\n"
		     + "        double componentMu = component[n] ? mu[0] : mu[1];\n"
		     + "        double componentSigma = component[n] ? sigma[0] : sigma[1];\n"
		     + "        y[n] = gaussian(componentMu, componentSigma * componentSigma).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    y.observe(yObserved);\n"
		     + "}\n"
		     + "";
	}
}