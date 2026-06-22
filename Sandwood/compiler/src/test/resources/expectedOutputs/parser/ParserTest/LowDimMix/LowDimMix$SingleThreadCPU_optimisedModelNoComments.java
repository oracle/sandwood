package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class LowDimMix$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements LowDimMix$CoreInterface {
	private int N;
	private boolean[] component;
	private boolean[] constrainedFlag$sample101;
	private boolean[] constrainedFlag$sample20;
	private boolean[] constrainedFlag$sample83;
	private boolean constrainedFlag$sample88 = true;
	private double[] cv$var97$stateProbabilityGlobal;
	private boolean fixedFlag$sample101 = false;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample83 = false;
	private boolean fixedFlag$sample88 = false;
	private boolean fixedProbFlag$sample101 = false;
	private boolean fixedProbFlag$sample138 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample83 = false;
	private boolean fixedProbFlag$sample88 = false;
	private boolean[] guard$sample20if124$global;
	private int length$yObserved;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$component;
	private double logProbability$componentDistribution;
	private double logProbability$mu;
	private double logProbability$rawMu;
	private double[] logProbability$sample138;
	private double[] logProbability$sample20;
	private double logProbability$sigma;
	private double logProbability$theta;
	private double logProbability$var79;
	private double logProbability$var97;
	private double logProbability$y;
	private double[] mu;
	private double[] rawMu;
	private double[] sigma;
	private boolean system$gibbsForward = true;
	private double theta;
	private double[] y;
	private double[] yObserved;

	public LowDimMix$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final int get$N() {
		return N;
	}

	@Override
	public final boolean[] get$component() {
		return component;
	}

	@Override
	public final void set$component(boolean[] cv$value, boolean allocated$) {
		component = cv$value;
		fixedProbFlag$sample101 = false;
		fixedProbFlag$sample138 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample101() {
		return fixedFlag$sample101;
	}

	@Override
	public final void set$fixedFlag$sample101(boolean cv$value, boolean allocated$) {
		fixedFlag$sample101 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample101$1 = 0; index$constrainedFlag$sample101$1 < constrainedFlag$sample101.length; index$constrainedFlag$sample101$1 += 1)
				constrainedFlag$sample101[index$constrainedFlag$sample101$1] = cv$value;
		}
		fixedProbFlag$sample101 = (cv$value && fixedProbFlag$sample101);
		fixedProbFlag$sample138 = (cv$value && fixedProbFlag$sample138);
	}

	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	@Override
	public final void set$fixedFlag$sample20(boolean cv$value, boolean allocated$) {
		fixedFlag$sample20 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample20$1 = 0; index$constrainedFlag$sample20$1 < constrainedFlag$sample20.length; index$constrainedFlag$sample20$1 += 1)
				constrainedFlag$sample20[index$constrainedFlag$sample20$1] = cv$value;
		}
		fixedProbFlag$sample20 = (cv$value && fixedProbFlag$sample20);
		fixedProbFlag$sample138 = (cv$value && fixedProbFlag$sample138);
	}

	@Override
	public final boolean get$fixedFlag$sample83() {
		return fixedFlag$sample83;
	}

	@Override
	public final void set$fixedFlag$sample83(boolean cv$value, boolean allocated$) {
		fixedFlag$sample83 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample83$1 = 0; index$constrainedFlag$sample83$1 < constrainedFlag$sample83.length; index$constrainedFlag$sample83$1 += 1)
				constrainedFlag$sample83[index$constrainedFlag$sample83$1] = cv$value;
		}
		fixedProbFlag$sample83 = (cv$value && fixedProbFlag$sample83);
		fixedProbFlag$sample138 = (cv$value && fixedProbFlag$sample138);
	}

	@Override
	public final boolean get$fixedFlag$sample88() {
		return fixedFlag$sample88;
	}

	@Override
	public final void set$fixedFlag$sample88(boolean cv$value, boolean allocated$) {
		fixedFlag$sample88 = cv$value;
		constrainedFlag$sample88 = (cv$value || constrainedFlag$sample88);
		fixedProbFlag$sample88 = (cv$value && fixedProbFlag$sample88);
		fixedProbFlag$sample101 = (cv$value && fixedProbFlag$sample101);
	}

	@Override
	public final int get$length$yObserved() {
		return length$yObserved;
	}

	@Override
	public final void set$length$yObserved(int cv$value, boolean allocated$) {
		length$yObserved = cv$value;
	}

	@Override
	public final double get$logProbability$$evidence() {
		return logProbability$$evidence;
	}

	@Override
	public final double getCurrentLogProbability() {
		return logProbability$$model;
	}

	@Override
	public final double get$logProbability$component() {
		return logProbability$component;
	}

	@Override
	public final double get$logProbability$componentDistribution() {
		return logProbability$componentDistribution;
	}

	@Override
	public final double get$logProbability$mu() {
		return logProbability$mu;
	}

	@Override
	public final double get$logProbability$rawMu() {
		return logProbability$rawMu;
	}

	@Override
	public final double get$logProbability$sigma() {
		return logProbability$sigma;
	}

	@Override
	public final double get$logProbability$theta() {
		return logProbability$theta;
	}

	@Override
	public final double get$logProbability$y() {
		return logProbability$y;
	}

	@Override
	public final double[] get$mu() {
		return mu;
	}

	@Override
	public final double[] get$rawMu() {
		return rawMu;
	}

	@Override
	public final void set$rawMu(double[] cv$value, boolean allocated$) {
		rawMu = cv$value;
		fixedProbFlag$sample20 = false;
		fixedProbFlag$sample138 = false;
	}

	@Override
	public final double[] get$sigma() {
		return sigma;
	}

	@Override
	public final void set$sigma(double[] cv$value, boolean allocated$) {
		sigma = cv$value;
		fixedProbFlag$sample83 = false;
		fixedProbFlag$sample138 = false;
	}

	@Override
	public final double get$theta() {
		return theta;
	}

	@Override
	public final void set$theta(double cv$value, boolean allocated$) {
		theta = cv$value;
		fixedProbFlag$sample88 = false;
		fixedProbFlag$sample101 = false;
	}

	@Override
	public final double[] get$y() {
		return y;
	}

	@Override
	public final double[] get$yObserved() {
		return yObserved;
	}

	@Override
	public final void set$yObserved(double[] cv$value, boolean allocated$) {
		yObserved = cv$value;
	}

	private final void drawValueSample101(int var96) {
		component[var96] = DistributionSampling.sampleBernoulli(RNG$, theta);
	}

	private final void drawValueSample20(int var19) {
		rawMu[var19] = (DistributionSampling.sampleGaussian(RNG$) * 2.0);
		boolean guard$sample20put43 = false;
		if((var19 == 0)) {
			guard$sample20put43 = true;
			double var39;
			if((rawMu[0] < rawMu[1]))
				var39 = rawMu[0];
			else
				var39 = rawMu[1];
			mu[0] = var39;
		}
		if(((var19 == 1) && !guard$sample20put43)) {
			guard$sample20put43 = true;
			double var39;
			if((rawMu[0] < rawMu[1]))
				var39 = rawMu[0];
			else
				var39 = rawMu[1];
			mu[0] = var39;
		}
		if((((rawMu[0] < rawMu[1]) && (var19 == 0)) && !guard$sample20put43)) {
			guard$sample20put43 = true;
			mu[0] = rawMu[0];
		}
		if(((!(rawMu[0] < rawMu[1]) && (var19 == 1)) && !guard$sample20put43))
			mu[0] = rawMu[1];
		boolean guard$sample20put63 = false;
		if((var19 == 0)) {
			guard$sample20put63 = true;
			double var57;
			if((rawMu[0] < rawMu[1]))
				var57 = rawMu[1];
			else
				var57 = rawMu[0];
			mu[1] = var57;
		}
		if((var19 == 1)) {
			if(!guard$sample20put63) {
				guard$sample20put63 = true;
				double var57;
				if((rawMu[0] < rawMu[1]))
					var57 = rawMu[1];
				else
					var57 = rawMu[0];
				mu[1] = var57;
			}
			if(((rawMu[0] < rawMu[1]) && !guard$sample20put63)) {
				guard$sample20put63 = true;
				mu[1] = rawMu[1];
			}
		}
		if(((!(rawMu[0] < rawMu[1]) && (var19 == 0)) && !guard$sample20put63))
			mu[1] = rawMu[0];
	}

	private final void drawValueSample83(int var78) {
		sigma[var78] = (DistributionSampling.sampleTruncatedGaussian(RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
	}

	private final void drawValueSample88() {
		theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
	}

	private final void inferSample101(int var96) {
		{
			component[var96] = false;
			double cv$accumulatedProbabilities = (((0.0 <= theta) && (theta <= 1.0))?Math.log((1.0 - theta)):Double.NEGATIVE_INFINITY);
			if(component[var96]) {
				{
					double componentSigma = sigma[0];
					double var128 = (componentSigma * componentSigma);
					cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[var96] - mu[0]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				guard$sample20if124$global[0] = false;
				guard$sample20if124$global[1] = false;
				if((rawMu[0] < rawMu[1]))
					guard$sample20if124$global[0] = false;
				else
					guard$sample20if124$global[1] = false;
				if(!guard$sample20if124$global[0]) {
					guard$sample20if124$global[0] = true;
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				if(!guard$sample20if124$global[1]) {
					guard$sample20if124$global[1] = true;
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				if(((rawMu[0] < rawMu[1]) && !guard$sample20if124$global[0])) {
					guard$sample20if124$global[0] = true;
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				if((!(rawMu[0] < rawMu[1]) && !guard$sample20if124$global[1])) {
					guard$sample20if124$global[1] = true;
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				double traceTempVariable$componentSigma$58_1 = sigma[0];
				double var128 = (traceTempVariable$componentSigma$58_1 * traceTempVariable$componentSigma$58_1);
				cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[var96] - mu[0]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				cv$accumulatedProbabilities = ((((0.0 <= sigma[0]) && (sigma[0] <= 1.0E100))?DistributionSampling.logProbabilityGaussian((sigma[0] / 2.0)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			} else {
				{
					double componentSigma = sigma[1];
					double var128 = (componentSigma * componentSigma);
					cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[var96] - mu[1]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				guard$sample20if124$global[0] = false;
				guard$sample20if124$global[1] = false;
				if((rawMu[0] < rawMu[1]))
					guard$sample20if124$global[1] = false;
				else
					guard$sample20if124$global[0] = false;
				if(!guard$sample20if124$global[0]) {
					guard$sample20if124$global[0] = true;
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				if(!guard$sample20if124$global[1]) {
					guard$sample20if124$global[1] = true;
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				if(((rawMu[0] < rawMu[1]) && !guard$sample20if124$global[1])) {
					guard$sample20if124$global[1] = true;
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				if((!(rawMu[0] < rawMu[1]) && !guard$sample20if124$global[0])) {
					guard$sample20if124$global[0] = true;
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
				double traceTempVariable$componentSigma$63_1 = sigma[1];
				double var128 = (traceTempVariable$componentSigma$63_1 * traceTempVariable$componentSigma$63_1);
				cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[var96] - mu[1]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				cv$accumulatedProbabilities = ((((0.0 <= sigma[1]) && (sigma[1] <= 1.0E100))?DistributionSampling.logProbabilityGaussian((sigma[1] / 2.0)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$var97$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		component[var96] = true;
		double cv$accumulatedProbabilities = (((0.0 <= theta) && (theta <= 1.0))?Math.log(theta):Double.NEGATIVE_INFINITY);
		if(component[var96]) {
			{
				double componentSigma = sigma[0];
				double var128 = (componentSigma * componentSigma);
				cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[var96] - mu[0]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			guard$sample20if124$global[0] = false;
			guard$sample20if124$global[1] = false;
			if((rawMu[0] < rawMu[1]))
				guard$sample20if124$global[0] = false;
			else
				guard$sample20if124$global[1] = false;
			if(!guard$sample20if124$global[0]) {
				guard$sample20if124$global[0] = true;
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			if(!guard$sample20if124$global[1]) {
				guard$sample20if124$global[1] = true;
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			if(((rawMu[0] < rawMu[1]) && !guard$sample20if124$global[0])) {
				guard$sample20if124$global[0] = true;
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			if((!(rawMu[0] < rawMu[1]) && !guard$sample20if124$global[1])) {
				guard$sample20if124$global[1] = true;
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			double traceTempVariable$componentSigma$58_1 = sigma[0];
			constrainedFlag$sample101[var96] = true;
			double var128 = (traceTempVariable$componentSigma$58_1 * traceTempVariable$componentSigma$58_1);
			cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[var96] - mu[0]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			cv$accumulatedProbabilities = ((((0.0 <= sigma[0]) && (sigma[0] <= 1.0E100))?DistributionSampling.logProbabilityGaussian((sigma[0] / 2.0)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		} else {
			{
				double componentSigma = sigma[1];
				double var128 = (componentSigma * componentSigma);
				cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[var96] - mu[1]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			guard$sample20if124$global[0] = false;
			guard$sample20if124$global[1] = false;
			if((rawMu[0] < rawMu[1]))
				guard$sample20if124$global[1] = false;
			else
				guard$sample20if124$global[0] = false;
			if(!guard$sample20if124$global[0]) {
				guard$sample20if124$global[0] = true;
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			if(!guard$sample20if124$global[1]) {
				guard$sample20if124$global[1] = true;
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			if(((rawMu[0] < rawMu[1]) && !guard$sample20if124$global[1])) {
				guard$sample20if124$global[1] = true;
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			if((!(rawMu[0] < rawMu[1]) && !guard$sample20if124$global[0])) {
				guard$sample20if124$global[0] = true;
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
			}
			double traceTempVariable$componentSigma$63_1 = sigma[1];
			constrainedFlag$sample101[var96] = true;
			double var128 = (traceTempVariable$componentSigma$63_1 * traceTempVariable$componentSigma$63_1);
			cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[var96] - mu[1]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			cv$accumulatedProbabilities = ((((0.0 <= sigma[1]) && (sigma[1] <= 1.0E100))?DistributionSampling.logProbabilityGaussian((sigma[1] / 2.0)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		cv$var97$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(constrainedFlag$sample101[var96]) {
			double cv$logSum;
			double cv$lseMax = cv$var97$stateProbabilityGlobal[0];
			double cv$lseElementValue = cv$var97$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((cv$var97$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var97$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				cv$var97$stateProbabilityGlobal[0] = 0.5;
				cv$var97$stateProbabilityGlobal[1] = 0.5;
			} else {
				cv$var97$stateProbabilityGlobal[0] = Math.exp((cv$var97$stateProbabilityGlobal[0] - cv$logSum));
				cv$var97$stateProbabilityGlobal[1] = Math.exp((cv$var97$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < cv$var97$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var97$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			component[var96] = (DistributionSampling.sampleCategorical(RNG$, cv$var97$stateProbabilityGlobal, 2) == 1);
		}
	}

	private final void inferSample20(int var19) {
		constrainedFlag$sample20[var19] = false;
		double cv$originalValue = rawMu[var19];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 2.0)) - 0.6931471805599453);
			if(((rawMu[0] < rawMu[1]) && (var19 == 0))) {
				for(int n = 0; n < N; n += 1) {
					if(component[n]) {
						constrainedFlag$sample20[0] = true;
						double componentSigma = sigma[0];
						double var128 = (componentSigma * componentSigma);
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - cv$originalValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var19 == 1)) {
				if((rawMu[0] < rawMu[1])) {
					for(int n = 0; n < N; n += 1) {
						if(!component[n]) {
							constrainedFlag$sample20[1] = true;
							double componentSigma = sigma[1];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - cv$originalValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
				} else {
					for(int n = 0; n < N; n += 1) {
						if(component[n]) {
							constrainedFlag$sample20[1] = true;
							double componentSigma = sigma[0];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - cv$originalValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			if((!(rawMu[0] < rawMu[1]) && (var19 == 0))) {
				for(int n = 0; n < N; n += 1) {
					if(!component[n]) {
						constrainedFlag$sample20[0] = true;
						double componentSigma = sigma[1];
						double var128 = (componentSigma * componentSigma);
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - cv$originalValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			boolean guard$sample20if41 = false;
			if((var19 == 0)) {
				guard$sample20if41 = true;
				if((rawMu[0] < rawMu[1])) {
					double traceTempVariable$var115$36_2 = rawMu[0];
					for(int n = 0; n < N; n += 1) {
						if(component[n]) {
							constrainedFlag$sample20[0] = true;
							double componentSigma = sigma[0];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var115$36_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				} else {
					double traceTempVariable$var115$52_2 = rawMu[1];
					for(int n = 0; n < N; n += 1) {
						if(component[n]) {
							constrainedFlag$sample20[0] = true;
							double componentSigma = sigma[0];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var115$52_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			if(((var19 == 1) && !guard$sample20if41)) {
				if((rawMu[0] < rawMu[1])) {
					double traceTempVariable$var115$37_2 = rawMu[0];
					for(int n = 0; n < N; n += 1) {
						if(component[n]) {
							constrainedFlag$sample20[1] = true;
							double componentSigma = sigma[0];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var115$37_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				} else {
					double traceTempVariable$var115$53_2 = rawMu[1];
					for(int n = 0; n < N; n += 1) {
						if(component[n]) {
							constrainedFlag$sample20[1] = true;
							double componentSigma = sigma[0];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var115$53_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			boolean guard$sample20if61 = false;
			if((var19 == 0)) {
				guard$sample20if61 = true;
				if((rawMu[0] < rawMu[1])) {
					double traceTempVariable$var117$72_2 = rawMu[1];
					for(int n = 0; n < N; n += 1) {
						if(!component[n]) {
							constrainedFlag$sample20[0] = true;
							double componentSigma = sigma[1];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var117$72_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				} else {
					double traceTempVariable$var117$88_2 = rawMu[0];
					for(int n = 0; n < N; n += 1) {
						if(!component[n]) {
							constrainedFlag$sample20[0] = true;
							double componentSigma = sigma[1];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var117$88_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			if(((var19 == 1) && !guard$sample20if61)) {
				if((rawMu[0] < rawMu[1])) {
					double traceTempVariable$var117$73_2 = rawMu[1];
					for(int n = 0; n < N; n += 1) {
						if(!component[n]) {
							constrainedFlag$sample20[1] = true;
							double componentSigma = sigma[1];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var117$73_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				} else {
					double traceTempVariable$var117$89_2 = rawMu[0];
					for(int n = 0; n < N; n += 1) {
						if(!component[n]) {
							constrainedFlag$sample20[1] = true;
							double componentSigma = sigma[1];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var117$89_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample20[var19]) {
			{
				rawMu[var19] = cv$proposedValue;
				boolean guard$sample20put43 = false;
				if((var19 == 0)) {
					guard$sample20put43 = true;
					double var39;
					if((rawMu[0] < rawMu[1]))
						var39 = rawMu[0];
					else
						var39 = rawMu[1];
					mu[0] = var39;
				}
				if(((var19 == 1) && !guard$sample20put43)) {
					guard$sample20put43 = true;
					double var39;
					if((rawMu[0] < rawMu[1]))
						var39 = rawMu[0];
					else
						var39 = rawMu[1];
					mu[0] = var39;
				}
				if((((rawMu[0] < rawMu[1]) && (var19 == 0)) && !guard$sample20put43)) {
					guard$sample20put43 = true;
					mu[0] = rawMu[0];
				}
				if(((!(rawMu[0] < rawMu[1]) && (var19 == 1)) && !guard$sample20put43))
					mu[0] = rawMu[1];
				boolean guard$sample20put63 = false;
				if((var19 == 0)) {
					guard$sample20put63 = true;
					double var57;
					if((rawMu[0] < rawMu[1]))
						var57 = rawMu[1];
					else
						var57 = rawMu[0];
					mu[1] = var57;
				}
				if((var19 == 1)) {
					if(!guard$sample20put63) {
						guard$sample20put63 = true;
						double var57;
						if((rawMu[0] < rawMu[1]))
							var57 = rawMu[1];
						else
							var57 = rawMu[0];
						mu[1] = var57;
					}
					if(((rawMu[0] < rawMu[1]) && !guard$sample20put63)) {
						guard$sample20put63 = true;
						mu[1] = rawMu[1];
					}
				}
				if(((!(rawMu[0] < rawMu[1]) && (var19 == 0)) && !guard$sample20put63))
					mu[1] = rawMu[0];
			}
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 2.0)) - 0.6931471805599453);
			if(((rawMu[0] < rawMu[1]) && (var19 == 0))) {
				for(int n = 0; n < N; n += 1) {
					if(component[n]) {
						constrainedFlag$sample20[0] = true;
						double componentSigma = sigma[0];
						double var128 = (componentSigma * componentSigma);
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - cv$proposedValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var19 == 1)) {
				if((rawMu[0] < rawMu[1])) {
					for(int n = 0; n < N; n += 1) {
						if(!component[n]) {
							constrainedFlag$sample20[1] = true;
							double componentSigma = sigma[1];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - cv$proposedValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
				} else {
					for(int n = 0; n < N; n += 1) {
						if(component[n]) {
							constrainedFlag$sample20[1] = true;
							double componentSigma = sigma[0];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - cv$proposedValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
				}
			}
			if((!(rawMu[0] < rawMu[1]) && (var19 == 0))) {
				for(int n = 0; n < N; n += 1) {
					if(!component[n]) {
						constrainedFlag$sample20[0] = true;
						double componentSigma = sigma[1];
						double var128 = (componentSigma * componentSigma);
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - cv$proposedValue) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			boolean guard$sample20if41 = false;
			if((var19 == 0)) {
				guard$sample20if41 = true;
				if((rawMu[0] < rawMu[1])) {
					double traceTempVariable$var115$36_2 = rawMu[0];
					for(int n = 0; n < N; n += 1) {
						if(component[n]) {
							constrainedFlag$sample20[0] = true;
							double componentSigma = sigma[0];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var115$36_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				} else {
					double traceTempVariable$var115$52_2 = rawMu[1];
					for(int n = 0; n < N; n += 1) {
						if(component[n]) {
							constrainedFlag$sample20[0] = true;
							double componentSigma = sigma[0];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var115$52_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			if(((var19 == 1) && !guard$sample20if41)) {
				if((rawMu[0] < rawMu[1])) {
					double traceTempVariable$var115$37_2 = rawMu[0];
					for(int n = 0; n < N; n += 1) {
						if(component[n]) {
							constrainedFlag$sample20[1] = true;
							double componentSigma = sigma[0];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var115$37_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				} else {
					double traceTempVariable$var115$53_2 = rawMu[1];
					for(int n = 0; n < N; n += 1) {
						if(component[n]) {
							constrainedFlag$sample20[1] = true;
							double componentSigma = sigma[0];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var115$53_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			boolean guard$sample20if61 = false;
			if((var19 == 0)) {
				guard$sample20if61 = true;
				if((rawMu[0] < rawMu[1])) {
					double traceTempVariable$var117$72_2 = rawMu[1];
					for(int n = 0; n < N; n += 1) {
						if(!component[n]) {
							constrainedFlag$sample20[0] = true;
							double componentSigma = sigma[1];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var117$72_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				} else {
					double traceTempVariable$var117$88_2 = rawMu[0];
					for(int n = 0; n < N; n += 1) {
						if(!component[n]) {
							constrainedFlag$sample20[0] = true;
							double componentSigma = sigma[1];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var117$88_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			if(((var19 == 1) && !guard$sample20if61)) {
				if((rawMu[0] < rawMu[1])) {
					double traceTempVariable$var117$73_2 = rawMu[1];
					for(int n = 0; n < N; n += 1) {
						if(!component[n]) {
							constrainedFlag$sample20[1] = true;
							double componentSigma = sigma[1];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var117$73_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				} else {
					double traceTempVariable$var117$89_2 = rawMu[0];
					for(int n = 0; n < N; n += 1) {
						if(!component[n]) {
							constrainedFlag$sample20[1] = true;
							double componentSigma = sigma[1];
							double var128 = (componentSigma * componentSigma);
							cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - traceTempVariable$var117$89_2) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
					}
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) + cv$accumulatedProbabilities) - 0.6931471805599453);
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio))) {
				rawMu[var19] = cv$originalValue;
				boolean guard$sample20put43 = false;
				if((var19 == 0)) {
					guard$sample20put43 = true;
					double var39;
					if((rawMu[0] < rawMu[1]))
						var39 = rawMu[0];
					else
						var39 = rawMu[1];
					mu[0] = var39;
				}
				if(((var19 == 1) && !guard$sample20put43)) {
					guard$sample20put43 = true;
					double var39;
					if((rawMu[0] < rawMu[1]))
						var39 = rawMu[0];
					else
						var39 = rawMu[1];
					mu[0] = var39;
				}
				if((((rawMu[0] < rawMu[1]) && (var19 == 0)) && !guard$sample20put43)) {
					guard$sample20put43 = true;
					mu[0] = rawMu[0];
				}
				if(((!(rawMu[0] < rawMu[1]) && (var19 == 1)) && !guard$sample20put43))
					mu[0] = rawMu[1];
				boolean guard$sample20put63 = false;
				if((var19 == 0)) {
					guard$sample20put63 = true;
					double var57;
					if((rawMu[0] < rawMu[1]))
						var57 = rawMu[1];
					else
						var57 = rawMu[0];
					mu[1] = var57;
				}
				if((var19 == 1)) {
					if(!guard$sample20put63) {
						guard$sample20put63 = true;
						double var57;
						if((rawMu[0] < rawMu[1]))
							var57 = rawMu[1];
						else
							var57 = rawMu[0];
						mu[1] = var57;
					}
					if(((rawMu[0] < rawMu[1]) && !guard$sample20put63)) {
						guard$sample20put63 = true;
						mu[1] = rawMu[1];
					}
				}
				if(((!(rawMu[0] < rawMu[1]) && (var19 == 0)) && !guard$sample20put63))
					mu[1] = rawMu[0];
			}
		}
	}

	private final void inferSample83(int var78) {
		constrainedFlag$sample83[var78] = false;
		double cv$originalValue = sigma[var78];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue <= 1.0E100))?DistributionSampling.logProbabilityGaussian((cv$originalValue / 2.0)):Double.NEGATIVE_INFINITY);
			if((var78 == 0)) {
				for(int n = 0; n < N; n += 1) {
					if(component[n]) {
						constrainedFlag$sample83[0] = true;
						double var128 = (cv$originalValue * cv$originalValue);
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - mu[0]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var78 == 1)) {
				for(int n = 0; n < N; n += 1) {
					if(!component[n]) {
						constrainedFlag$sample83[1] = true;
						double var128 = (cv$originalValue * cv$originalValue);
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - mu[1]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample83[var78]) {
			sigma[var78] = cv$proposedValue;
			double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0E100))?DistributionSampling.logProbabilityGaussian((cv$proposedValue / 2.0)):Double.NEGATIVE_INFINITY);
			if((var78 == 0)) {
				for(int n = 0; n < N; n += 1) {
					if(component[n]) {
						constrainedFlag$sample83[0] = true;
						double var128 = (cv$proposedValue * cv$proposedValue);
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - mu[0]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((var78 == 1)) {
				for(int n = 0; n < N; n += 1) {
					if(!component[n]) {
						constrainedFlag$sample83[1] = true;
						double var128 = (cv$proposedValue * cv$proposedValue);
						cv$accumulatedProbabilities = (((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - mu[1]) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				sigma[var78] = cv$originalValue;
		}
	}

	private final void inferSample88() {
		constrainedFlag$sample88 = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int var96 = 0; var96 < N; var96 += 1) {
			if((fixedFlag$sample101 || constrainedFlag$sample101[var96])) {
				constrainedFlag$sample88 = true;
				cv$count = (cv$count + 1);
				if(component[var96])
					cv$sum = (cv$sum + 1);
			}
		}
		if(constrainedFlag$sample88)
			theta = Conjugates.sampleConjugateBetaBinomial(RNG$, 5.0, 5.0, cv$sum, cv$count);
	}

	private final void logProbabilityValue$sample101() {
		if(!fixedProbFlag$sample101) {
			double cv$sampleAccumulator = 0.0;
			for(int var96 = 0; var96 < N; var96 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= theta) && (theta <= 1.0))?Math.log((component[var96]?theta:(1.0 - theta))):Double.NEGATIVE_INFINITY));
			logProbability$componentDistribution = cv$sampleAccumulator;
			logProbability$var97 = cv$sampleAccumulator;
			logProbability$component = (logProbability$component + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample101)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample101 = (fixedFlag$sample101 && fixedFlag$sample88);
		} else {
			logProbability$componentDistribution = logProbability$var97;
			logProbability$component = (logProbability$component + logProbability$var97);
			logProbability$$model = (logProbability$$model + logProbability$var97);
			if(fixedFlag$sample101)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var97);
		}
	}

	private final void logProbabilityValue$sample138() {
		if(!fixedProbFlag$sample138) {
			double cv$accumulator = 0.0;
			for(int n = 0; n < N; n += 1) {
				double componentMu;
				if(component[n])
					componentMu = mu[0];
				else
					componentMu = mu[1];
				double componentSigma;
				if(component[n])
					componentSigma = sigma[0];
				else
					componentSigma = sigma[1];
				double var128 = (componentSigma * componentSigma);
				double cv$distributionAccumulator = ((0.0 < var128)?(DistributionSampling.logProbabilityGaussian(((y[n] - componentMu) / Math.sqrt(var128))) - (Math.log(var128) * 0.5)):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample138[n] = cv$distributionAccumulator;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample138 = ((fixedFlag$sample20 && fixedFlag$sample83) && fixedFlag$sample101);
		} else {
			double cv$accumulator = 0.0;
			for(int n = 0; n < N; n += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample138[n]);
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample20() {
		if(!fixedProbFlag$sample20) {
			double cv$sampleAccumulator;
			{
				double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian((rawMu[0] / 2.0)) - 0.6931471805599453);
				cv$sampleAccumulator = cv$weightedProbability;
				logProbability$sample20[0] = cv$weightedProbability;
				boolean cv$guard$mu = false;
				if((rawMu[0] < rawMu[1])) {
					cv$guard$mu = true;
					logProbability$mu = (logProbability$mu + cv$weightedProbability);
				}
				if((!(rawMu[0] < rawMu[1]) && !cv$guard$mu))
					logProbability$mu = (logProbability$mu + cv$weightedProbability);
			}
			double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian((rawMu[1] / 2.0)) - 0.6931471805599453);
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$weightedProbability);
			logProbability$sample20[1] = cv$weightedProbability;
			boolean cv$guard$mu = false;
			if(!(rawMu[0] < rawMu[1])) {
				cv$guard$mu = true;
				logProbability$mu = (logProbability$mu + cv$weightedProbability);
			}
			if(((rawMu[0] < rawMu[1]) && !cv$guard$mu))
				logProbability$mu = (logProbability$mu + cv$weightedProbability);
			logProbability$rawMu = (logProbability$rawMu + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample20 = fixedFlag$sample20;
		} else {
			double cv$rvAccumulator;
			{
				double cv$sampleValue = logProbability$sample20[0];
				cv$rvAccumulator = cv$sampleValue;
				boolean cv$guard$mu = false;
				if((rawMu[0] < rawMu[1])) {
					cv$guard$mu = true;
					logProbability$mu = (logProbability$mu + cv$sampleValue);
				}
				if((!(rawMu[0] < rawMu[1]) && !cv$guard$mu))
					logProbability$mu = (logProbability$mu + cv$sampleValue);
			}
			double cv$sampleValue = logProbability$sample20[1];
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			boolean cv$guard$mu = false;
			if(!(rawMu[0] < rawMu[1])) {
				cv$guard$mu = true;
				logProbability$mu = (logProbability$mu + cv$sampleValue);
			}
			if(((rawMu[0] < rawMu[1]) && !cv$guard$mu))
				logProbability$mu = (logProbability$mu + cv$sampleValue);
			logProbability$rawMu = (logProbability$rawMu + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample83() {
		if(!fixedProbFlag$sample83) {
			double cv$sampleAccumulator;
			{
				double cv$sampleValue = sigma[0];
				cv$sampleAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue <= 1.0E100))?DistributionSampling.logProbabilityGaussian((cv$sampleValue / 2.0)):Double.NEGATIVE_INFINITY);
			}
			double cv$sampleValue = sigma[1];
			cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue <= 1.0E100))?DistributionSampling.logProbabilityGaussian((cv$sampleValue / 2.0)):Double.NEGATIVE_INFINITY));
			logProbability$var79 = cv$sampleAccumulator;
			logProbability$sigma = (logProbability$sigma + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample83)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample83 = fixedFlag$sample83;
		} else {
			logProbability$sigma = (logProbability$sigma + logProbability$var79);
			logProbability$$model = (logProbability$$model + logProbability$var79);
			if(fixedFlag$sample83)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var79);
		}
	}

	private final void logProbabilityValue$sample88() {
		if(!fixedProbFlag$sample88) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(theta, 5.0, 5.0);
			logProbability$theta = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample88 = fixedFlag$sample88;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$theta);
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + logProbability$theta);
		}
	}

	@Override
	public final void allocateScratch() {
		cv$var97$stateProbabilityGlobal = new double[2];
		guard$sample20if124$global = new boolean[2];
	}

	@Override
	public final void allocator() {
		if(!fixedFlag$sample20)
			rawMu = new double[2];
		mu = new double[2];
		if(!fixedFlag$sample83)
			sigma = new double[2];
		if(!fixedFlag$sample101)
			component = new boolean[length$yObserved];
		y = new double[length$yObserved];
		constrainedFlag$sample101 = new boolean[length$yObserved];
		constrainedFlag$sample20 = new boolean[2];
		constrainedFlag$sample83 = new boolean[2];
		logProbability$sample20 = new double[2];
		logProbability$sample138 = new double[length$yObserved];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample20) {
			rawMu[0] = (DistributionSampling.sampleGaussian(RNG$) * 2.0);
			rawMu[1] = (DistributionSampling.sampleGaussian(RNG$) * 2.0);
			double var39;
			if((rawMu[0] < rawMu[1]))
				var39 = rawMu[0];
			else
				var39 = rawMu[1];
			mu[0] = var39;
			double var57;
			if((rawMu[0] < rawMu[1]))
				var57 = rawMu[1];
			else
				var57 = rawMu[0];
			mu[1] = var57;
		}
		if(!fixedFlag$sample83) {
			sigma[0] = (DistributionSampling.sampleTruncatedGaussian(RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
			sigma[1] = (DistributionSampling.sampleTruncatedGaussian(RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
		}
		if(!fixedFlag$sample88)
			theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
		if(!fixedFlag$sample101) {
			for(int var96 = 0; var96 < N; var96 += 1)
				component[var96] = DistributionSampling.sampleBernoulli(RNG$, theta);
		}
		for(int n = 0; n < N; n += 1) {
			double componentMu;
			if(component[n])
				componentMu = mu[0];
			else
				componentMu = mu[1];
			double componentSigma;
			if(component[n])
				componentSigma = sigma[0];
			else
				componentSigma = sigma[1];
			y[n] = ((componentSigma * DistributionSampling.sampleGaussian(RNG$)) + componentMu);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample20) {
			rawMu[0] = (DistributionSampling.sampleGaussian(RNG$) * 2.0);
			rawMu[1] = (DistributionSampling.sampleGaussian(RNG$) * 2.0);
		}
		double var39;
		if((rawMu[0] < rawMu[1]))
			var39 = rawMu[0];
		else
			var39 = rawMu[1];
		mu[0] = var39;
		double var57;
		if((rawMu[0] < rawMu[1]))
			var57 = rawMu[1];
		else
			var57 = rawMu[0];
		mu[1] = var57;
		if(!fixedFlag$sample83) {
			sigma[0] = (DistributionSampling.sampleTruncatedGaussian(RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
			sigma[1] = (DistributionSampling.sampleTruncatedGaussian(RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
		}
		if(!fixedFlag$sample88)
			theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
		if(!fixedFlag$sample101) {
			for(int var96 = 0; var96 < N; var96 += 1)
				component[var96] = DistributionSampling.sampleBernoulli(RNG$, theta);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample20) {
			rawMu[0] = (DistributionSampling.sampleGaussian(RNG$) * 2.0);
			rawMu[1] = (DistributionSampling.sampleGaussian(RNG$) * 2.0);
		}
		double var39;
		if((rawMu[0] < rawMu[1]))
			var39 = rawMu[0];
		else
			var39 = rawMu[1];
		mu[0] = var39;
		double var57;
		if((rawMu[0] < rawMu[1]))
			var57 = rawMu[1];
		else
			var57 = rawMu[0];
		mu[1] = var57;
		if(!fixedFlag$sample83) {
			sigma[0] = (DistributionSampling.sampleTruncatedGaussian(RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
			sigma[1] = (DistributionSampling.sampleTruncatedGaussian(RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
		}
		if(!fixedFlag$sample88)
			theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
		if(!fixedFlag$sample101) {
			for(int var96 = 0; var96 < N; var96 += 1)
				component[var96] = DistributionSampling.sampleBernoulli(RNG$, theta);
		}
		for(int n = 0; n < N; n += 1) {
			double componentMu;
			if(component[n])
				componentMu = mu[0];
			else
				componentMu = mu[1];
			double componentSigma;
			if(component[n])
				componentSigma = sigma[0];
			else
				componentSigma = sigma[1];
			y[n] = ((componentSigma * DistributionSampling.sampleGaussian(RNG$)) + componentMu);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample20) {
			rawMu[0] = (DistributionSampling.sampleGaussian(RNG$) * 2.0);
			rawMu[1] = (DistributionSampling.sampleGaussian(RNG$) * 2.0);
			double var39;
			if((rawMu[0] < rawMu[1]))
				var39 = rawMu[0];
			else
				var39 = rawMu[1];
			mu[0] = var39;
			double var57;
			if((rawMu[0] < rawMu[1]))
				var57 = rawMu[1];
			else
				var57 = rawMu[0];
			mu[1] = var57;
		}
		if(!fixedFlag$sample83) {
			sigma[0] = (DistributionSampling.sampleTruncatedGaussian(RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
			sigma[1] = (DistributionSampling.sampleTruncatedGaussian(RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
		}
		if(!fixedFlag$sample88)
			theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
		if(!fixedFlag$sample101) {
			for(int var96 = 0; var96 < N; var96 += 1)
				component[var96] = DistributionSampling.sampleBernoulli(RNG$, theta);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample20) {
			rawMu[0] = (DistributionSampling.sampleGaussian(RNG$) * 2.0);
			rawMu[1] = (DistributionSampling.sampleGaussian(RNG$) * 2.0);
		}
		double var39;
		if((rawMu[0] < rawMu[1]))
			var39 = rawMu[0];
		else
			var39 = rawMu[1];
		mu[0] = var39;
		double var57;
		if((rawMu[0] < rawMu[1]))
			var57 = rawMu[1];
		else
			var57 = rawMu[0];
		mu[1] = var57;
		if(!fixedFlag$sample83) {
			sigma[0] = (DistributionSampling.sampleTruncatedGaussian(RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
			sigma[1] = (DistributionSampling.sampleTruncatedGaussian(RNG$, 0.0, 0.5, 5.0E99, 1.0) * 2.0);
		}
		if(!fixedFlag$sample88)
			theta = DistributionSampling.sampleBeta(RNG$, 5.0, 5.0);
		if(!fixedFlag$sample101) {
			for(int var96 = 0; var96 < N; var96 += 1)
				component[var96] = DistributionSampling.sampleBernoulli(RNG$, theta);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample20) {
				inferSample20(0);
				inferSample20(1);
			}
			if(!fixedFlag$sample83) {
				inferSample83(0);
				inferSample83(1);
			}
			if(!fixedFlag$sample88)
				inferSample88();
			if(!fixedFlag$sample101) {
				for(int var96 = 0; var96 < N; var96 += 1)
					inferSample101(var96);
			}
		} else {
			if(!fixedFlag$sample101) {
				for(int var96 = (N - 1); var96 >= 0; var96 -= 1)
					inferSample101(var96);
			}
			if(!fixedFlag$sample88)
				inferSample88();
			if(!fixedFlag$sample83) {
				inferSample83(1);
				inferSample83(0);
			}
			if(!fixedFlag$sample20) {
				inferSample20(1);
				inferSample20(0);
			}
		}
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample20[0])
			drawValueSample20(0);
		if(!constrainedFlag$sample20[1])
			drawValueSample20(1);
		if(!constrainedFlag$sample83[0])
			drawValueSample83(0);
		if(!constrainedFlag$sample83[1])
			drawValueSample83(1);
		if(!constrainedFlag$sample88)
			drawValueSample88();
		for(int var96 = 0; var96 < N; var96 += 1) {
			if(!constrainedFlag$sample101[var96])
				drawValueSample101(var96);
		}
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$rawMu = 0.0;
		logProbability$mu = 0.0;
		if(!fixedProbFlag$sample20) {
			logProbability$sample20[0] = Double.NaN;
			logProbability$sample20[1] = Double.NaN;
		}
		logProbability$sigma = 0.0;
		if(!fixedProbFlag$sample83)
			logProbability$var79 = Double.NaN;
		if(!fixedProbFlag$sample88)
			logProbability$theta = Double.NaN;
		logProbability$componentDistribution = Double.NaN;
		logProbability$component = 0.0;
		if(!fixedProbFlag$sample101)
			logProbability$var97 = Double.NaN;
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample138) {
			for(int n = 0; n < N; n += 1)
				logProbability$sample138[n] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		N = length$yObserved;
		for(int index$constrainedFlag$sample101$1 = 0; index$constrainedFlag$sample101$1 < constrainedFlag$sample101.length; index$constrainedFlag$sample101$1 += 1)
			constrainedFlag$sample101[index$constrainedFlag$sample101$1] = true;
		for(int index$constrainedFlag$sample20$1 = 0; index$constrainedFlag$sample20$1 < constrainedFlag$sample20.length; index$constrainedFlag$sample20$1 += 1)
			constrainedFlag$sample20[index$constrainedFlag$sample20$1] = true;
		for(int index$constrainedFlag$sample83$1 = 0; index$constrainedFlag$sample83$1 < constrainedFlag$sample83.length; index$constrainedFlag$sample83$1 += 1)
			constrainedFlag$sample83[index$constrainedFlag$sample83$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		if(fixedFlag$sample83)
			logProbabilityValue$sample83();
		if(fixedFlag$sample88)
			logProbabilityValue$sample88();
		if(fixedFlag$sample101)
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
		int cv$length1 = y.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			y[cv$index1] = yObserved[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		double var39;
		if((rawMu[0] < rawMu[1]))
			var39 = rawMu[0];
		else
			var39 = rawMu[1];
		mu[0] = var39;
		double var57;
		if((rawMu[0] < rawMu[1]))
			var57 = rawMu[1];
		else
			var57 = rawMu[0];
		mu[1] = var57;
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