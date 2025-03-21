package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DiscreteChoiceRandCoeff$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements DiscreteChoiceRandCoeff$CoreInterface {
	private int[] ObsChoices;
	private int[][] Prices;
	private double b;
	private double[] beta;
	private int[] choices;
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
	private boolean[] guard$sample47categorical102$global;
	private boolean[][] guard$sample47put101$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b;
	private double logProbability$beta;
	private double logProbability$choices;
	private double logProbability$exped;
	private double logProbability$prob;
	private double[] logProbability$sample103;
	private double[] logProbability$sample21;
	private double[] logProbability$sample47;
	private double logProbability$sigma;
	private double logProbability$ut;
	private double[] logProbability$var101;
	private double logProbability$var27;
	private double logProbability$var33;
	private double logProbability$var35;
	private double logProbability$var9;
	private int noObs;
	private int noProducts;
	private double[][] prob;
	private boolean setFlag$beta = false;
	private boolean setFlag$choices = false;
	private boolean setFlag$ut = false;
	private double sigma;
	private boolean system$gibbsForward = true;
	private double[] ut;

	public DiscreteChoiceRandCoeff$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final int[] get$ObsChoices() {
		return ObsChoices;
	}

	@Override
	public final void set$ObsChoices(int[] cv$value) {
		ObsChoices = cv$value;
	}

	@Override
	public final int[][] get$Prices() {
		return Prices;
	}

	@Override
	public final void set$Prices(int[][] cv$value) {
		Prices = cv$value;
	}

	@Override
	public final double get$b() {
		return b;
	}

	@Override
	public final void set$b(double cv$value) {
		b = cv$value;
		fixedProbFlag$sample28 = false;
		fixedProbFlag$sample47 = false;
	}

	@Override
	public final double[] get$beta() {
		return beta;
	}

	@Override
	public final void set$beta(double[] cv$value) {
		beta = cv$value;
		setFlag$beta = true;
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
	public final void set$fixedFlag$sample21(boolean cv$value) {
		fixedFlag$sample21 = cv$value;
		fixedProbFlag$sample21 = (cv$value && fixedProbFlag$sample21);
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		fixedFlag$sample28 = cv$value;
		fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
		fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
	}

	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		fixedFlag$sample34 = cv$value;
		fixedProbFlag$sample34 = (cv$value && fixedProbFlag$sample34);
		fixedProbFlag$sample47 = (cv$value && fixedProbFlag$sample47);
	}

	@Override
	public final boolean get$fixedFlag$sample47() {
		return fixedFlag$sample47;
	}

	@Override
	public final void set$fixedFlag$sample47(boolean cv$value) {
		fixedFlag$sample47 = cv$value;
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
	public final void set$noObs(int cv$value) {
		noObs = cv$value;
	}

	@Override
	public final int get$noProducts() {
		return noProducts;
	}

	@Override
	public final void set$noProducts(int cv$value) {
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
	public final void set$sigma(double cv$value) {
		sigma = cv$value;
		fixedProbFlag$sample34 = false;
		fixedProbFlag$sample47 = false;
	}

	@Override
	public final double[] get$ut() {
		return ut;
	}

	@Override
	public final void set$ut(double[] cv$value) {
		ut = cv$value;
		setFlag$ut = true;
		fixedProbFlag$sample21 = false;
		fixedProbFlag$sample103 = false;
	}

	private final void logProbabilityValue$sample103() {
		if(!fixedProbFlag$sample103) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noObs; i += 1) {
				int cv$sampleValue = choices[i];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noProducts))?Math.log(prob[i][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var101[i] = cv$distributionAccumulator;
				logProbability$sample103[i] = cv$distributionAccumulator;
			}
			logProbability$choices = (logProbability$choices + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample103 = (fixedFlag$sample21 && fixedFlag$sample47);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noObs; i += 1) {
				double cv$rvAccumulator = logProbability$sample103[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var101[i] = cv$rvAccumulator;
			}
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
				if((0 < noObs)) {
					logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
					logProbability$prob = (logProbability$prob + cv$distributionAccumulator);
				}
			}
			logProbability$var9 = cv$sampleAccumulator;
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
				if((0 < noObs)) {
					logProbability$exped = (logProbability$exped + cv$sampleValue);
					logProbability$prob = (logProbability$prob + cv$sampleValue);
				}
			}
			logProbability$var9 = cv$rvAccumulator;
			logProbability$ut = (logProbability$ut + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((b / 3.1622776601683795)) - 1.151292546497023);
			logProbability$var27 = cv$distributionAccumulator;
			logProbability$b = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			logProbability$var27 = logProbability$b;
			logProbability$$model = (logProbability$$model + logProbability$b);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + logProbability$b);
		}
	}

	private final void logProbabilityValue$sample34() {
		if(!fixedProbFlag$sample34) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityInverseGamma(sigma, 2.0, 2.0);
			logProbability$var33 = cv$distributionAccumulator;
			logProbability$sigma = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample34 = fixedFlag$sample34;
		} else {
			logProbability$var33 = logProbability$sigma;
			logProbability$$model = (logProbability$$model + logProbability$sigma);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + logProbability$sigma);
		}
	}

	private final void logProbabilityValue$sample47() {
		if(!fixedProbFlag$sample47) {
			double cv$sampleAccumulator = 0.0;
			for(int var46 = 0; var46 < noObs; var46 += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((beta[var46] - b) / Math.sqrt(sigma))) - (Math.log(sigma) * 0.5));
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				logProbability$sample47[var46] = cv$distributionAccumulator;
				if((0 < noProducts)) {
					logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
					logProbability$prob = (logProbability$prob + cv$distributionAccumulator);
				}
			}
			logProbability$var35 = cv$sampleAccumulator;
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
				if((0 < noProducts)) {
					logProbability$exped = (logProbability$exped + cv$sampleValue);
					logProbability$prob = (logProbability$prob + cv$sampleValue);
				}
			}
			logProbability$var35 = cv$rvAccumulator;
			logProbability$beta = (logProbability$beta + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void sample21(int var20) {
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
					cv$accumulatedProbabilities = ((((0.0 <= choices[i]) && (choices[i] < noProducts))?Math.log(prob[i][choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			for(int i = 0; i < noObs; i += 1) {
				if(!guard$sample21categorical102$global[i])
					cv$accumulatedProbabilities = ((((0.0 <= choices[i]) && (choices[i] < noProducts))?Math.log(prob[i][choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
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
					double reduceVar$sum$0 = 0.0;
					for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
						reduceVar$sum$0 = (reduceVar$sum$0 + exped[i][cv$reduction82Index]);
					prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$0);
				}
			}
		}
		for(int i = 0; i < noObs; i += 1) {
			if(!guard$sample21put101$global[i][var20]) {
				guard$sample21put101$global[i][var20] = true;
				double reduceVar$sum$1 = 0.0;
				for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
					reduceVar$sum$1 = (reduceVar$sum$1 + exped[i][cv$reduction82Index]);
				prob[i][var20] = (exped[i][var20] / reduceVar$sum$1);
			}
		}
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		for(int i = 0; i < noObs; i += 1)
			guard$sample21categorical102$global[i] = false;
		for(int i = 0; i < noObs; i += 1) {
			if(!guard$sample21categorical102$global[i]) {
				guard$sample21categorical102$global[i] = true;
				cv$accumulatedProbabilities = ((((0.0 <= choices[i]) && (choices[i] < noProducts))?Math.log(prob[i][choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		for(int i = 0; i < noObs; i += 1) {
			if(!guard$sample21categorical102$global[i]) {
				guard$sample21categorical102$global[i] = true;
				cv$accumulatedProbabilities = ((((0.0 <= choices[i]) && (choices[i] < noProducts))?Math.log(prob[i][choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
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
						double reduceVar$sum$3 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
							reduceVar$sum$3 = (reduceVar$sum$3 + exped[i][cv$reduction82Index]);
						prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$3);
					}
				}
			}
			for(int i = 0; i < noObs; i += 1) {
				if(!guard$sample21put101$global[i][var20]) {
					guard$sample21put101$global[i][var20] = true;
					double reduceVar$sum$4 = 0.0;
					for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
						reduceVar$sum$4 = (reduceVar$sum$4 + exped[i][cv$reduction82Index]);
					prob[i][var20] = (exped[i][var20] / reduceVar$sum$4);
				}
			}
		}
	}

	private final void sample28() {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int var46 = 0; var46 < noObs; var46 += 1) {
			cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
			cv$sum = (cv$sum + beta[var46]);
			if(cv$sigmaNotFound) {
				cv$sigmaValue = sigma;
				cv$sigmaNotFound = false;
			}
		}
		b = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void sample34() {
		double cv$sum = 0.0;
		int cv$count = 0;
		for(int var46 = 0; var46 < noObs; var46 += 1) {
			double cv$var35$diff = (b - beta[var46]);
			cv$sum = (cv$sum + (cv$var35$diff * cv$var35$diff));
			cv$count = (cv$count + 1);
		}
		sigma = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 2.0, 2.0, cv$sum, cv$count);
	}

	private final void sample47(int var46) {
		double cv$originalValue = beta[var46];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$originalValue - b) / Math.sqrt(sigma))) - (Math.log(sigma) * 0.5));
			if((0 < noProducts)) {
				guard$sample47categorical102$global[var46] = false;
				if(!guard$sample47categorical102$global[var46]) {
					guard$sample47categorical102$global[var46] = true;
					cv$accumulatedProbabilities = ((((0.0 <= choices[var46]) && (choices[var46] < noProducts))?Math.log(prob[var46][choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				if(!guard$sample47categorical102$global[var46]) {
					guard$sample47categorical102$global[var46] = true;
					cv$accumulatedProbabilities = ((((0.0 <= choices[var46]) && (choices[var46] < noProducts))?Math.log(prob[var46][choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		beta[var46] = cv$proposedValue;
		for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
			exped[var46][j$var69] = Math.exp((ut[j$var69] - (beta[var46] * Prices[var46][j$var69])));
		if((0 < noProducts)) {
			for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
				guard$sample47put101$global[var46][j$var97] = false;
		}
		for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
			guard$sample47put101$global[var46][j$var69] = false;
		if((0 < noProducts)) {
			for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
				if(!guard$sample47put101$global[var46][j$var97]) {
					guard$sample47put101$global[var46][j$var97] = true;
					double reduceVar$sum$5 = 0.0;
					for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
						reduceVar$sum$5 = (reduceVar$sum$5 + exped[var46][cv$reduction82Index]);
					prob[var46][j$var97] = (exped[var46][j$var97] / reduceVar$sum$5);
				}
			}
		}
		for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
			if(!guard$sample47put101$global[var46][j$var69]) {
				guard$sample47put101$global[var46][j$var69] = true;
				double reduceVar$sum$6 = 0.0;
				for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
					reduceVar$sum$6 = (reduceVar$sum$6 + exped[var46][cv$reduction82Index]);
				prob[var46][j$var69] = (exped[var46][j$var69] / reduceVar$sum$6);
			}
		}
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - b) / Math.sqrt(sigma))) - (Math.log(sigma) * 0.5));
		if((0 < noProducts)) {
			guard$sample47categorical102$global[var46] = false;
			if(!guard$sample47categorical102$global[var46]) {
				guard$sample47categorical102$global[var46] = true;
				cv$accumulatedProbabilities = ((((0.0 <= choices[var46]) && (choices[var46] < noProducts))?Math.log(prob[var46][choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if(!guard$sample47categorical102$global[var46]) {
				guard$sample47categorical102$global[var46] = true;
				cv$accumulatedProbabilities = ((((0.0 <= choices[var46]) && (choices[var46] < noProducts))?Math.log(prob[var46][choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			beta[var46] = cv$originalValue;
			for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
				exped[var46][j$var69] = Math.exp((ut[j$var69] - (beta[var46] * Prices[var46][j$var69])));
			if((0 < noProducts)) {
				for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
					guard$sample47put101$global[var46][j$var97] = false;
			}
			for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
				guard$sample47put101$global[var46][j$var69] = false;
			if((0 < noProducts)) {
				for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1) {
					if(!guard$sample47put101$global[var46][j$var97]) {
						guard$sample47put101$global[var46][j$var97] = true;
						double reduceVar$sum$8 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
							reduceVar$sum$8 = (reduceVar$sum$8 + exped[var46][cv$reduction82Index]);
						prob[var46][j$var97] = (exped[var46][j$var97] / reduceVar$sum$8);
					}
				}
			}
			for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1) {
				if(!guard$sample47put101$global[var46][j$var69]) {
					guard$sample47put101$global[var46][j$var69] = true;
					double reduceVar$sum$9 = 0.0;
					for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
						reduceVar$sum$9 = (reduceVar$sum$9 + exped[var46][cv$reduction82Index]);
					prob[var46][j$var69] = (exped[var46][j$var69] / reduceVar$sum$9);
				}
			}
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
		int cv$max_j$var97 = 0;
		if((0 < noObs))
			cv$max_j$var97 = Math.max(0, noProducts);
		guard$sample47put101$global = new boolean[Math.max(0, noObs)][cv$max_j$var97];
		guard$sample47categorical102$global = new boolean[Math.max(0, noObs)];
	}

	@Override
	public final void allocator() {
		if(!setFlag$ut)
			ut = new double[noProducts];
		if(!setFlag$beta)
			beta = new double[noObs];
		choices = new int[noObs];
		exped = new double[noObs][];
		for(int i = 0; i < noObs; i += 1)
			exped[i] = new double[noProducts];
		prob = new double[noObs][];
		for(int i = 0; i < noObs; i += 1)
			prob[i] = new double[noProducts];
		logProbability$sample21 = new double[noProducts];
		logProbability$sample47 = new double[noObs];
		logProbability$var101 = new double[noObs];
		logProbability$sample103 = new double[noObs];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample21) {
			for(int var20 = 0; var20 < noProducts; var20 += 1)
				ut[var20] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample28)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample34)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		if(!fixedFlag$sample47) {
			for(int var46 = 0; var46 < noObs; var46 += 1)
				beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$)) + b);
		}
		for(int i = 0; i < noObs; i += 1) {
			if((!fixedFlag$sample21 || !fixedFlag$sample47)) {
				for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
					exped[i][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
				double reduceVar$sum$10 = 0.0;
				for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
					reduceVar$sum$10 = (reduceVar$sum$10 + exped[i][cv$reduction82Index]);
				for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
					prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$10);
			}
			choices[i] = DistributionSampling.sampleCategorical(RNG$, prob[i], noProducts);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample21) {
			for(int var20 = 0; var20 < noProducts; var20 += 1)
				ut[var20] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample28)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample34)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		if(!fixedFlag$sample47) {
			for(int var46 = 0; var46 < noObs; var46 += 1)
				beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$)) + b);
		}
		if((!fixedFlag$sample21 || !fixedFlag$sample47)) {
			for(int i = 0; i < noObs; i += 1) {
				for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
					exped[i][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
				double reduceVar$sum$12 = 0.0;
				for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
					reduceVar$sum$12 = (reduceVar$sum$12 + exped[i][cv$reduction82Index]);
				for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
					prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$12);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample21) {
			for(int var20 = 0; var20 < noProducts; var20 += 1)
				ut[var20] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample28)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample34)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		if(!fixedFlag$sample47) {
			for(int var46 = 0; var46 < noObs; var46 += 1)
				beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$)) + b);
		}
		if((!fixedFlag$sample21 || !fixedFlag$sample47)) {
			for(int i = 0; i < noObs; i += 1) {
				for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
					exped[i][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
				double reduceVar$sum$11 = 0.0;
				for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
					reduceVar$sum$11 = (reduceVar$sum$11 + exped[i][cv$reduction82Index]);
				for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
					prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$11);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample21) {
				for(int var20 = 0; var20 < noProducts; var20 += 1)
					sample21(var20);
			}
			if(!fixedFlag$sample28)
				sample28();
			if(!fixedFlag$sample34)
				sample34();
			if(!fixedFlag$sample47) {
				for(int var46 = 0; var46 < noObs; var46 += 1)
					sample47(var46);
			}
		} else {
			if(!fixedFlag$sample47) {
				for(int var46 = (noObs - 1); var46 >= 0; var46 -= 1)
					sample47(var46);
			}
			if(!fixedFlag$sample34)
				sample34();
			if(!fixedFlag$sample28)
				sample28();
			if(!fixedFlag$sample21) {
				for(int var20 = (noProducts - 1); var20 >= 0; var20 -= 1)
					sample21(var20);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var9 = 0.0;
		logProbability$ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$prob = 0.0;
		if(!fixedProbFlag$sample21) {
			for(int var20 = 0; var20 < noProducts; var20 += 1)
				logProbability$sample21[var20] = 0.0;
		}
		logProbability$var27 = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$b = 0.0;
		logProbability$var33 = 0.0;
		if(!fixedProbFlag$sample34)
			logProbability$sigma = 0.0;
		logProbability$var35 = 0.0;
		logProbability$beta = 0.0;
		if(!fixedProbFlag$sample47) {
			for(int var46 = 0; var46 < noObs; var46 += 1)
				logProbability$sample47[var46] = 0.0;
		}
		for(int i = 0; i < noObs; i += 1)
			logProbability$var101[i] = 0.0;
		logProbability$choices = 0.0;
		if(!fixedProbFlag$sample103) {
			for(int i = 0; i < noObs; i += 1)
				logProbability$sample103[i] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
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
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample21) {
			for(int var20 = 0; var20 < noProducts; var20 += 1)
				ut[var20] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		}
		if(!fixedFlag$sample28)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample34)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		if(!fixedFlag$sample47) {
			for(int var46 = 0; var46 < noObs; var46 += 1)
				beta[var46] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$)) + b);
		}
		if((!fixedFlag$sample21 || !fixedFlag$sample47)) {
			for(int i = 0; i < noObs; i += 1) {
				for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
					exped[i][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
				double reduceVar$sum$13 = 0.0;
				for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
					reduceVar$sum$13 = (reduceVar$sum$13 + exped[i][cv$reduction82Index]);
				for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
					prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$13);
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = choices.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			choices[cv$index1] = ObsChoices[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		for(int i = 0; i < noObs; i += 1) {
			if((setFlag$ut && setFlag$beta)) {
				for(int j$var69 = 0; j$var69 < noProducts; j$var69 += 1)
					exped[i][j$var69] = Math.exp((ut[j$var69] - (beta[i] * Prices[i][j$var69])));
			}
			double reduceVar$sum$14 = 0.0;
			for(int cv$reduction82Index = 0; cv$reduction82Index < noProducts; cv$reduction82Index += 1)
				reduceVar$sum$14 = (reduceVar$sum$14 + exped[i][cv$reduction82Index]);
			if((setFlag$ut && setFlag$beta)) {
				for(int j$var97 = 0; j$var97 < noProducts; j$var97 += 1)
					prob[i][j$var97] = (exped[i][j$var97] / reduceVar$sum$14);
			}
		}
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