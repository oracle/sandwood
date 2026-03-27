package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DiscreteChoiceRandCoeff$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DiscreteChoiceRandCoeff$CoreInterface {
	private int[] ObsChoices;
	private int[][] Prices;
	private double b;
	private double[] beta;
	private int[] choices;
	private boolean[] constrainedFlag$sample21;
	private boolean constrainedFlag$sample28 = true;
	private boolean constrainedFlag$sample34 = true;
	private boolean[] constrainedFlag$sample47;
	private double[][] exped;
	private boolean fixedFlag$sample21 = false;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample47 = false;
	private boolean fixedProbFlag$sample103 = false;
	private boolean fixedProbFlag$sample21 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample47 = false;
	private boolean[] guard$sample21categorical102$global;
	private boolean[][] guard$sample21put101$global;
	private boolean[][] guard$sample47categorical102$global;
	private boolean[][][] guard$sample47put101$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b;
	private double logProbability$beta;
	private double logProbability$choices;
	private double logProbability$prob;
	private double[] logProbability$sample103;
	private double[] logProbability$sample21;
	private double[] logProbability$sample47;
	private double logProbability$sigma;
	private double logProbability$ut;
	private int noObs;
	private int noProducts;
	private double[][] prob;
	private double sigma;
	private boolean system$gibbsForward = true;
	private double[] ut;

	public DiscreteChoiceRandCoeff$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final int[] get$ObsChoices() {
		return ObsChoices;
	}

	@Override
	public final void set$ObsChoices(int[] cv$value, boolean allocated$) {
		ObsChoices = cv$value;
	}

	@Override
	public final int[][] get$Prices() {
		return Prices;
	}

	@Override
	public final void set$Prices(int[][] cv$value, boolean allocated$) {
		Prices = cv$value;
	}

	@Override
	public final double get$b() {
		return b;
	}

	@Override
	public final void set$b(double cv$value, boolean allocated$) {
		b = cv$value;
		fixedProbFlag$sample28 = false;
		fixedProbFlag$sample47 = false;
	}

	@Override
	public final double[] get$beta() {
		return beta;
	}

	@Override
	public final void set$beta(double[] cv$value, boolean allocated$) {
		beta = cv$value;
		fixedProbFlag$sample47 = false;
		fixedProbFlag$sample103 = false;
	}

	@Override
	public final int[] get$choices() {
		return choices;
	}

	@Override
	public final boolean get$fixedFlag$sample21() {
		return fixedFlag$sample21;
	}

	@Override
	public final void set$fixedFlag$sample21(boolean cv$value, boolean allocated$) {
		fixedFlag$sample21 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample21$1 = 0; index$constrainedFlag$sample21$1 < constrainedFlag$sample21.length; index$constrainedFlag$sample21$1 += 1)
				constrainedFlag$sample21[index$constrainedFlag$sample21$1] = true;
		}
		fixedProbFlag$sample21 = (cv$value && fixedProbFlag$sample21);
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value, boolean allocated$) {
		fixedFlag$sample28 = cv$value;
		constrainedFlag$sample28 = (cv$value || constrainedFlag$sample28);
		fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
		fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
	}

	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	@Override
	public final void set$fixedFlag$sample34(boolean cv$value, boolean allocated$) {
		fixedFlag$sample34 = cv$value;
		constrainedFlag$sample34 = (cv$value || constrainedFlag$sample34);
		fixedProbFlag$sample34 = (cv$value && fixedProbFlag$sample34);
		fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
	}

	@Override
	public final boolean get$fixedFlag$sample47() {
		return fixedFlag$sample47;
	}

	@Override
	public final void set$fixedFlag$sample47(boolean cv$value, boolean allocated$) {
		fixedFlag$sample47 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample47$1 = 0; index$constrainedFlag$sample47$1 < constrainedFlag$sample47.length; index$constrainedFlag$sample47$1 += 1)
				constrainedFlag$sample47[index$constrainedFlag$sample47$1] = true;
		}
		fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
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
	public final double get$logProbability$b() {
		return logProbability$b;
	}

	@Override
	public final double get$logProbability$beta() {
		return logProbability$beta;
	}

	@Override
	public final double get$logProbability$choices() {
		return logProbability$choices;
	}

	@Override
	public final double get$logProbability$prob() {
		return logProbability$prob;
	}

	@Override
	public final double get$logProbability$sigma() {
		return logProbability$sigma;
	}

	@Override
	public final double get$logProbability$ut() {
		return logProbability$ut;
	}

	@Override
	public final int get$noObs() {
		return noObs;
	}

	@Override
	public final void set$noObs(int cv$value, boolean allocated$) {
		noObs = cv$value;
	}

	@Override
	public final int get$noProducts() {
		return noProducts;
	}

	@Override
	public final void set$noProducts(int cv$value, boolean allocated$) {
		noProducts = cv$value;
	}

	@Override
	public final double[][] get$prob() {
		return prob;
	}

	@Override
	public final double get$sigma() {
		return sigma;
	}

	@Override
	public final void set$sigma(double cv$value, boolean allocated$) {
		sigma = cv$value;
		fixedProbFlag$sample34 = false;
		fixedProbFlag$sample47 = false;
	}

	@Override
	public final double[] get$ut() {
		return ut;
	}

	@Override
	public final void set$ut(double[] cv$value, boolean allocated$) {
		ut = cv$value;
		fixedProbFlag$sample21 = false;
		fixedProbFlag$sample103 = false;
	}

	private final void drawValueSample21(int var20) {
		ut[var20] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		for(int i = 0; i < noObs; i += 1)
			exped[i][var20] = Math.exp((ut[var20] - (beta[i] * Prices[i][var20])));
		for(int i = 0; i < noObs; i += 1) {
			for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
				guard$sample21put101$global[i][j$var97] = false;
		}
		for(int i = 0; i < noObs; i += 1)
			guard$sample21put101$global[i][var20] = false;
		for(int i = 0; i < noObs; i += 1) {
			for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
				if(!guard$sample21put101$global[i][j$var97]) {
					guard$sample21put101$global[i][j$var97] = true;
					double reduceVar$sum$30 = 0.0;
					for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
						reduceVar$sum$30 = (reduceVar$sum$30 + exped[i][cv$reduction82Index]);
					prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$30);
				}
			}
		}
		for(int i = 0; i < noObs; i += 1) {
			if(!guard$sample21put101$global[i][var20]) {
				guard$sample21put101$global[i][var20] = true;
				double reduceVar$sum$31 = 0.0;
				for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
					reduceVar$sum$31 = (reduceVar$sum$31 + exped[i][cv$reduction82Index]);
				prob[i][var20] = (exped[i][var20] / reduceVar$sum$31);
			}
		}
	}

	private final void drawValueSample28() {
		b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
	}

	private final void drawValueSample34() {
		sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
	}

	private final void drawValueSample47(int var46, int threadID$cv$var46, Rng RNG$) {
		beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$)) + b);
		for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
			exped[var46][j$var69] = Math.exp((ut[j$var69] - (beta[var46] * Prices[var46][j$var69])));
		boolean[][] guard$sample47put101 = guard$sample47put101$global[threadID$cv$var46];
		if((0 < noProducts)) {
			for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
				guard$sample47put101[var46][j$var97] = false;
		}
		for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
			guard$sample47put101[var46][j$var69] = false;
		if((0 < noProducts)) {
			for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
				if(!guard$sample47put101[var46][j$var97]) {
					guard$sample47put101[var46][j$var97] = true;
					double reduceVar$sum$32 = 0.0;
					for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
						reduceVar$sum$32 = (reduceVar$sum$32 + exped[var46][cv$reduction82Index]);
					prob[var46][j$var97] = (exped[var46][j$var97] / reduceVar$sum$32);
				}
			}
		}
		for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
			if(!guard$sample47put101[var46][j$var69]) {
				guard$sample47put101[var46][j$var69] = true;
				double reduceVar$sum$33 = 0.0;
				for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
					reduceVar$sum$33 = (reduceVar$sum$33 + exped[var46][cv$reduction82Index]);
				prob[var46][j$var69] = (exped[var46][j$var69] / reduceVar$sum$33);
			}
		}
	}

	private final void inferSample21(int var20) {
		constrainedFlag$sample21[var20] = false;
		double cv$originalValue = ut[var20];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			for(int i = 0; i < noObs; i += 1)
				guard$sample21categorical102$global[i] = false;
			for(int i = 0; i < noObs; i += 1) {
				if(!guard$sample21categorical102$global[i]) {
					guard$sample21categorical102$global[i] = true;
					constrainedFlag$sample21[var20] = true;
					cv$accumulatedProbabilities = ((((((0.0 <= choices[i]) && (choices[i] < noProducts)) && (0.0 <= prob[i][choices[i]])) && (prob[i][choices[i]] <= 1.0))?Math.log(prob[i][choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			for(int i = 0; i < noObs; i += 1) {
				if(!guard$sample21categorical102$global[i]) {
					guard$sample21categorical102$global[i] = true;
					constrainedFlag$sample21[var20] = true;
					cv$accumulatedProbabilities = ((((((0.0 <= choices[i]) && (choices[i] < noProducts)) && (0.0 <= prob[i][choices[i]])) && (prob[i][choices[i]] <= 1.0))?Math.log(prob[i][choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample21[var20]) {
			ut[var20] = cv$proposedValue;
			for(int i = 0; i < noObs; i += 1)
				exped[i][var20] = Math.exp((ut[var20] - (beta[i] * Prices[i][var20])));
			for(int i = 0; i < noObs; i += 1) {
				for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
					guard$sample21put101$global[i][j$var97] = false;
			}
			for(int i = 0; i < noObs; i += 1)
				guard$sample21put101$global[i][var20] = false;
			for(int i = 0; i < noObs; i += 1) {
				for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
					if(!guard$sample21put101$global[i][j$var97]) {
						guard$sample21put101$global[i][j$var97] = true;
						double reduceVar$sum$20 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
							reduceVar$sum$20 = (reduceVar$sum$20 + exped[i][cv$reduction82Index]);
						prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$20);
					}
				}
			}
			for(int i = 0; i < noObs; i += 1) {
				if(!guard$sample21put101$global[i][var20]) {
					guard$sample21put101$global[i][var20] = true;
					double reduceVar$sum$21 = 0.0;
					for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
						reduceVar$sum$21 = (reduceVar$sum$21 + exped[i][cv$reduction82Index]);
					prob[i][var20] = (exped[i][var20] / reduceVar$sum$21);
				}
			}
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
			for(int i = 0; i < noObs; i += 1)
				guard$sample21categorical102$global[i] = false;
			for(int i = 0; i < noObs; i += 1) {
				if(!guard$sample21categorical102$global[i]) {
					guard$sample21categorical102$global[i] = true;
					constrainedFlag$sample21[var20] = true;
					cv$accumulatedProbabilities = ((((((0.0 <= choices[i]) && (choices[i] < noProducts)) && (0.0 <= prob[i][choices[i]])) && (prob[i][choices[i]] <= 1.0))?Math.log(prob[i][choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			for(int i = 0; i < noObs; i += 1) {
				if(!guard$sample21categorical102$global[i]) {
					guard$sample21categorical102$global[i] = true;
					constrainedFlag$sample21[var20] = true;
					cv$accumulatedProbabilities = ((((((0.0 <= choices[i]) && (choices[i] < noProducts)) && (0.0 <= prob[i][choices[i]])) && (prob[i][choices[i]] <= 1.0))?Math.log(prob[i][choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio))) {
				ut[var20] = cv$originalValue;
				for(int i = 0; i < noObs; i += 1)
					exped[i][var20] = Math.exp((ut[var20] - (beta[i] * Prices[i][var20])));
				for(int i = 0; i < noObs; i += 1) {
					for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
						guard$sample21put101$global[i][j$var97] = false;
				}
				for(int i = 0; i < noObs; i += 1)
					guard$sample21put101$global[i][var20] = false;
				for(int i = 0; i < noObs; i += 1) {
					for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
						if(!guard$sample21put101$global[i][j$var97]) {
							guard$sample21put101$global[i][j$var97] = true;
							double reduceVar$sum$23 = 0.0;
							for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
								reduceVar$sum$23 = (reduceVar$sum$23 + exped[i][cv$reduction82Index]);
							prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$23);
						}
					}
				}
				for(int i = 0; i < noObs; i += 1) {
					if(!guard$sample21put101$global[i][var20]) {
						guard$sample21put101$global[i][var20] = true;
						double reduceVar$sum$24 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
							reduceVar$sum$24 = (reduceVar$sum$24 + exped[i][cv$reduction82Index]);
						prob[i][var20] = (exped[i][var20] / reduceVar$sum$24);
					}
				}
			}
		}
	}

	private final void inferSample28() {
		constrainedFlag$sample28 = false;
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int var46 = 0; var46 < noObs; var46 += 1) {
			if((fixedFlag$sample47 || constrainedFlag$sample47[var46])) {
				constrainedFlag$sample28 = true;
				cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
				cv$sum = (cv$sum + beta[var46]);
				if(cv$sigmaNotFound) {
					cv$sigmaValue = sigma;
					cv$sigmaNotFound = false;
				}
			}
		}
		if(constrainedFlag$sample28)
			b = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void inferSample34() {
		constrainedFlag$sample34 = false;
		double cv$sum = 0.0;
		int cv$count = 0;
		for(int var46 = 0; var46 < noObs; var46 += 1) {
			if((fixedFlag$sample47 || constrainedFlag$sample47[var46])) {
				constrainedFlag$sample34 = true;
				double cv$var35$diff = (b - beta[var46]);
				cv$sum = (cv$sum + (cv$var35$diff * cv$var35$diff));
				cv$count = (cv$count + 1);
			}
		}
		if(constrainedFlag$sample34)
			sigma = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 2.0, 2.0, cv$sum, cv$count);
	}

	private final void inferSample47(int var46, int threadID$cv$var46, Rng RNG$) {
		constrainedFlag$sample47[var46] = false;
		double cv$originalValue = beta[var46];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = ((0.0 < sigma)?(DistributionSampling.logProbabilityGaussian(((cv$originalValue - b) / Math.sqrt(sigma))) - (Math.log(sigma) * 0.5)):Double.NEGATIVE_INFINITY);
			if((0 < noProducts)) {
				boolean[] guard$sample47categorical102 = guard$sample47categorical102$global[threadID$cv$var46];
				guard$sample47categorical102[var46] = false;
				if(!guard$sample47categorical102[var46]) {
					guard$sample47categorical102[var46] = true;
					constrainedFlag$sample47[var46] = true;
					cv$accumulatedProbabilities = ((((((0.0 <= choices[var46]) && (choices[var46] < noProducts)) && (0.0 <= prob[var46][choices[var46]])) && (prob[var46][choices[var46]] <= 1.0))?Math.log(prob[var46][choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				if(!guard$sample47categorical102[var46]) {
					guard$sample47categorical102[var46] = true;
					constrainedFlag$sample47[var46] = true;
					cv$accumulatedProbabilities = ((((((0.0 <= choices[var46]) && (choices[var46] < noProducts)) && (0.0 <= prob[var46][choices[var46]])) && (prob[var46][choices[var46]] <= 1.0))?Math.log(prob[var46][choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample47[var46]) {
			{
				beta[var46] = cv$proposedValue;
				for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
					exped[var46][j$var69] = Math.exp((ut[j$var69] - (beta[var46] * Prices[var46][j$var69])));
				boolean[][] guard$sample47put101 = guard$sample47put101$global[threadID$cv$var46];
				if((0 < noProducts)) {
					for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
						guard$sample47put101[var46][j$var97] = false;
				}
				for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
					guard$sample47put101[var46][j$var69] = false;
				if((0 < noProducts)) {
					for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
						if(!guard$sample47put101[var46][j$var97]) {
							guard$sample47put101[var46][j$var97] = true;
							double reduceVar$sum$25 = 0.0;
							for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
								reduceVar$sum$25 = (reduceVar$sum$25 + exped[var46][cv$reduction82Index]);
							prob[var46][j$var97] = (exped[var46][j$var97] / reduceVar$sum$25);
						}
					}
				}
				for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
					if(!guard$sample47put101[var46][j$var69]) {
						guard$sample47put101[var46][j$var69] = true;
						double reduceVar$sum$26 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
							reduceVar$sum$26 = (reduceVar$sum$26 + exped[var46][cv$reduction82Index]);
						prob[var46][j$var69] = (exped[var46][j$var69] / reduceVar$sum$26);
					}
				}
			}
			double cv$accumulatedProbabilities = ((0.0 < sigma)?(DistributionSampling.logProbabilityGaussian(((cv$proposedValue - b) / Math.sqrt(sigma))) - (Math.log(sigma) * 0.5)):Double.NEGATIVE_INFINITY);
			if((0 < noProducts)) {
				boolean[] guard$sample47categorical102 = guard$sample47categorical102$global[threadID$cv$var46];
				guard$sample47categorical102[var46] = false;
				if(!guard$sample47categorical102[var46]) {
					guard$sample47categorical102[var46] = true;
					constrainedFlag$sample47[var46] = true;
					cv$accumulatedProbabilities = ((((((0.0 <= choices[var46]) && (choices[var46] < noProducts)) && (0.0 <= prob[var46][choices[var46]])) && (prob[var46][choices[var46]] <= 1.0))?Math.log(prob[var46][choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				if(!guard$sample47categorical102[var46]) {
					guard$sample47categorical102[var46] = true;
					constrainedFlag$sample47[var46] = true;
					cv$accumulatedProbabilities = ((((((0.0 <= choices[var46]) && (choices[var46] < noProducts)) && (0.0 <= prob[var46][choices[var46]])) && (prob[var46][choices[var46]] <= 1.0))?Math.log(prob[var46][choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio))) {
				beta[var46] = cv$originalValue;
				for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
					exped[var46][j$var69] = Math.exp((ut[j$var69] - (beta[var46] * Prices[var46][j$var69])));
				boolean[][] guard$sample47put101 = guard$sample47put101$global[threadID$cv$var46];
				if((0 < noProducts)) {
					for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
						guard$sample47put101[var46][j$var97] = false;
				}
				for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
					guard$sample47put101[var46][j$var69] = false;
				if((0 < noProducts)) {
					for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
						if(!guard$sample47put101[var46][j$var97]) {
							guard$sample47put101[var46][j$var97] = true;
							double reduceVar$sum$28 = 0.0;
							for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
								reduceVar$sum$28 = (reduceVar$sum$28 + exped[var46][cv$reduction82Index]);
							prob[var46][j$var97] = (exped[var46][j$var97] / reduceVar$sum$28);
						}
					}
				}
				for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
					if(!guard$sample47put101[var46][j$var69]) {
						guard$sample47put101[var46][j$var69] = true;
						double reduceVar$sum$29 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
							reduceVar$sum$29 = (reduceVar$sum$29 + exped[var46][cv$reduction82Index]);
						prob[var46][j$var69] = (exped[var46][j$var69] / reduceVar$sum$29);
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample103() {
		if(!fixedProbFlag$sample103) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noObs; i += 1) {
				int cv$sampleValue = choices[i];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noProducts)) && (0 < noProducts)) && (0.0 <= prob[i][cv$sampleValue])) && (prob[i][cv$sampleValue] <= 1.0))?Math.log(prob[i][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample103[i] = cv$distributionAccumulator;
			}
			logProbability$choices = (logProbability$choices + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample103 = (fixedFlag$sample21 && fixedFlag$sample47);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noObs; i += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample103[i]);
			logProbability$choices = (logProbability$choices + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample21() {
		if(!fixedProbFlag$sample21) {
			double cv$sampleAccumulator = 0.0;
			for(int var20 = 0; var20 < noProducts; var20 += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((ut[var20] / 3.1622776601683795)) - 1.151292546497023);
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				logProbability$sample21[var20] = cv$distributionAccumulator;
				if((0 < noObs))
					logProbability$prob = (logProbability$prob + cv$distributionAccumulator);
			}
			logProbability$ut = (logProbability$ut + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample21 = fixedFlag$sample21;
		} else {
			double cv$rvAccumulator = 0.0;
			for(int var20 = 0; var20 < noProducts; var20 += 1) {
				double cv$sampleValue = logProbability$sample21[var20];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				if((0 < noObs))
					logProbability$prob = (logProbability$prob + cv$sampleValue);
			}
			logProbability$ut = (logProbability$ut + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((b / 3.1622776601683795)) - 1.151292546497023);
			logProbability$b = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$b);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + logProbability$b);
		}
	}

	private final void logProbabilityValue$sample34() {
		if(!fixedProbFlag$sample34) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityInverseGamma(sigma, 2.0, 2.0);
			logProbability$sigma = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample34 = fixedFlag$sample34;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$sigma);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + logProbability$sigma);
		}
	}

	private final void logProbabilityValue$sample47() {
		if(!fixedProbFlag$sample47) {
			double cv$sampleAccumulator = 0.0;
			for(int var46 = 0; var46 < noObs; var46 += 1) {
				double cv$distributionAccumulator = ((0.0 < sigma)?(DistributionSampling.logProbabilityGaussian(((beta[var46] - b) / Math.sqrt(sigma))) - (Math.log(sigma) * 0.5)):Double.NEGATIVE_INFINITY);
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				logProbability$sample47[var46] = cv$distributionAccumulator;
				if((0 < noProducts))
					logProbability$prob = (logProbability$prob + cv$distributionAccumulator);
			}
			logProbability$beta = (logProbability$beta + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample47 = ((fixedFlag$sample47 && fixedFlag$sample28) && fixedFlag$sample34);
		} else {
			double cv$rvAccumulator = 0.0;
			for(int var46 = 0; var46 < noObs; var46 += 1) {
				double cv$sampleValue = logProbability$sample47[var46];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				if((0 < noProducts))
					logProbability$prob = (logProbability$prob + cv$sampleValue);
			}
			logProbability$beta = (logProbability$beta + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max_j$var97 = 0;
			if((0 < noObs))
				cv$max_j$var97 = Math.max(0, noProducts);
			guard$sample21put101$global = new boolean[Math.max(0, noObs)][cv$max_j$var97];
		}
		guard$sample21categorical102$global = new boolean[Math.max(0, noObs)];
		{
			int cv$max_j$var97 = 0;
			if((0 < noObs))
				cv$max_j$var97 = Math.max(0, noProducts);
			int cv$max_i = Math.max(0, noObs);
			int cv$threadCount = threadCount();
			guard$sample47put101$global = new boolean[cv$threadCount][][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				guard$sample47put101$global[cv$index] = new boolean[cv$max_i][cv$max_j$var97];
		}
		int cv$max_i = Math.max(0, noObs);
		int cv$threadCount = threadCount();
		guard$sample47categorical102$global = new boolean[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			guard$sample47categorical102$global[cv$index] = new boolean[cv$max_i];
	}

	@Override
	public final void allocator() {
		if(!fixedFlag$sample21)
			ut = new double[noProducts];
		if(!fixedFlag$sample47)
			beta = new double[noObs];
		choices = new int[noObs];
		exped = new double[noObs][];
		for(int i = 0; i < noObs; i += 1)
			exped[i] = new double[noProducts];
		prob = new double[noObs][];
		for(int i = 0; i < noObs; i += 1)
			prob[i] = new double[noProducts];
		constrainedFlag$sample47 = new boolean[noObs];
		constrainedFlag$sample21 = new boolean[noProducts];
		logProbability$sample21 = new double[noProducts];
		logProbability$sample47 = new double[noObs];
		logProbability$sample103 = new double[noObs];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample21)
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1)
							ut[var20] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!fixedFlag$sample28)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample34)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		if(!fixedFlag$sample47)
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
							beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
				}
			);

		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						if((!fixedFlag$sample21 || !fixedFlag$sample47)) {
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1)
											exped[i][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
								}
							);
							double reduceVar$sum$34 = 0.0;
							for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
								reduceVar$sum$34 = (reduceVar$sum$34 + exped[i][cv$reduction82Index]);
							double reduceVar$sum$34$1 = reduceVar$sum$34;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1)
											prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$34$1);
								}
							);
						}
						choices[i] = DistributionSampling.sampleCategorical(RNG$1, prob[i], noProducts);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample21)
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1)
							ut[var20] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!fixedFlag$sample28)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample34)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		if(!fixedFlag$sample47)
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
							beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
				}
			);

		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1)
										exped[i][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
							}
						);
						double reduceVar$sum$38 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
							reduceVar$sum$38 = (reduceVar$sum$38 + exped[i][cv$reduction82Index]);
						double reduceVar$sum$38$1 = reduceVar$sum$38;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1)
										prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$38$1);
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample21)
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1)
							ut[var20] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!fixedFlag$sample28)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample34)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		if(!fixedFlag$sample47)
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
							beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
				}
			);

		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1)
										exped[i][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
							}
						);
						double reduceVar$sum$35 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
							reduceVar$sum$35 = (reduceVar$sum$35 + exped[i][cv$reduction82Index]);
						double reduceVar$sum$35$1 = reduceVar$sum$35;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1)
										prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$35$1);
							}
						);
						choices[i] = DistributionSampling.sampleCategorical(RNG$1, prob[i], noProducts);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample21)
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1)
							ut[var20] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!fixedFlag$sample28)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample34)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		if(!fixedFlag$sample47)
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
							beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
				}
			);

		if((!fixedFlag$sample21 || !fixedFlag$sample47))
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
							int i = index$i;
							int threadID$i = threadID$index$i;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1)
											exped[i][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
								}
							);
							double reduceVar$sum$36 = 0.0;
							for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
								reduceVar$sum$36 = (reduceVar$sum$36 + exped[i][cv$reduction82Index]);
							double reduceVar$sum$36$1 = reduceVar$sum$36;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1)
											prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$36$1);
								}
							);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample21)
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1)
							ut[var20] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!fixedFlag$sample28)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample34)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		if(!fixedFlag$sample47)
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
							beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
				}
			);

		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1)
										exped[i][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
							}
						);
						double reduceVar$sum$37 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
							reduceVar$sum$37 = (reduceVar$sum$37 + exped[i][cv$reduction82Index]);
						double reduceVar$sum$37$1 = reduceVar$sum$37;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1)
										prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$37$1);
							}
						);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample21) {
				for(int var20 = 0; var20 < noProducts; var20 += 1)
					inferSample21(var20);
			}
			if(!fixedFlag$sample28)
				inferSample28();
			if(!fixedFlag$sample34)
				inferSample34();
			if(!fixedFlag$sample47)
				parallelFor(RNG$, 0, noObs, 1,
					(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
								inferSample47(var46, threadID$var46, RNG$1);
					}
				);

		} else {
			if(!fixedFlag$sample47)
				parallelFor(RNG$, 0, noObs, 1,
					(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
								inferSample47(var46, threadID$var46, RNG$1);
					}
				);

			if(!fixedFlag$sample34)
				inferSample34();
			if(!fixedFlag$sample28)
				inferSample28();
			if(!fixedFlag$sample21) {
				for(int var20 = (noProducts - 1); var20 >= 0; var20 -= 1)
					inferSample21(var20);
			}
		}
		system$gibbsForward = !system$gibbsForward;
		for(int var20 = 0; var20 < noProducts; var20 += 1) {
			if(!constrainedFlag$sample21[var20])
				drawValueSample21(var20);
		}
		if(!constrainedFlag$sample28)
			drawValueSample28();
		if(!constrainedFlag$sample34)
			drawValueSample34();
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!constrainedFlag$sample47[var46])
							drawValueSample47(var46, threadID$var46, RNG$1);
					}
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$ut = 0.0;
		logProbability$prob = 0.0;
		if(!fixedProbFlag$sample21) {
			for(int var20 = 0; var20 < noProducts; var20 += 1)
				logProbability$sample21[var20] = Double.NaN;
		}
		if(!fixedProbFlag$sample28)
			logProbability$b = Double.NaN;
		if(!fixedProbFlag$sample34)
			logProbability$sigma = Double.NaN;
		logProbability$beta = 0.0;
		if(!fixedProbFlag$sample47) {
			for(int var46 = 0; var46 < noObs; var46 += 1)
				logProbability$sample47[var46] = Double.NaN;
		}
		logProbability$choices = 0.0;
		if(!fixedProbFlag$sample103) {
			for(int i = 0; i < noObs; i += 1)
				logProbability$sample103[i] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		for(int index$constrainedFlag$sample47$1 = 0; index$constrainedFlag$sample47$1 < constrainedFlag$sample47.length; index$constrainedFlag$sample47$1 += 1)
			constrainedFlag$sample47[index$constrainedFlag$sample47$1] = true;
		for(int index$constrainedFlag$sample21$1 = 0; index$constrainedFlag$sample21$1 < constrainedFlag$sample21.length; index$constrainedFlag$sample21$1 += 1)
			constrainedFlag$sample21[index$constrainedFlag$sample21$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample21)
			logProbabilityValue$sample21();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(fixedFlag$sample47)
			logProbabilityValue$sample47();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample21();
		logProbabilityValue$sample28();
		logProbabilityValue$sample34();
		logProbabilityValue$sample47();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample21();
		logProbabilityValue$sample28();
		logProbabilityValue$sample34();
		logProbabilityValue$sample47();
		logProbabilityValue$sample103();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = choices.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			choices[cv$index1] = ObsChoices[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1)
										exped[i][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
							}
						);
						double reduceVar$sum$39 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
							reduceVar$sum$39 = (reduceVar$sum$39 + exped[i][cv$reduction82Index]);
						double reduceVar$sum$39$1 = reduceVar$sum$39;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1)
										prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$39$1);
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
		     + "\n"
		     + "model DiscreteChoiceRandCoeff(int noProducts, int noObs, int[] ObsChoices, int[][] Prices) {\n"
		     + "    // we just need an uninformative prior for utility intercepts\n"
		     + "\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = gaussian(0, 10).sample(noProducts);\n"
		     + "    //can we set the first element to 0? like ut[0] <~ 0\n"
		     + "\n"
		     + "    //priors for distribution of beta\n"
		     + "    double b = gaussian(0,10).sample();\n"
		     + "    double sigma =  inverseGamma(2,2).sample();\n"
		     + "\n"
		     + "    double[] beta = gaussian(b, sigma).sample(noObs);\n"
		     + "\n"
		     + "    int[] choices = new int[noObs];\n"
		     + "\n"
		     + "    for (int i:[0..noObs)){\n"
		     + "        // calculate choice probabilities for consumer i\n"
		     + "\n"
		     + "        double[] exped = new double[noProducts];\n"
		     + "        for(int j : [0..noProducts)) {\n"
		     + "            exped[j] = exp(ut[j]- beta[i]*Prices[i][j]);\n"
		     + "            }\n"
		     + "        double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n"
		     + "        public double[] prob = new double[noProducts];\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            prob[j] = exped[j] / sum;\n"
		     + "        }\n"
		     + "        // emit choices of consumer i\n"
		     + "        choices[i] = categorical(prob).sample();\n"
		     + "                                }\n"
		     + "\n"
		     + "    // assert that generated choices match observed choices\n"
		     + "    choices.observe(ObsChoices);\n"
		     + "}";
	}
}