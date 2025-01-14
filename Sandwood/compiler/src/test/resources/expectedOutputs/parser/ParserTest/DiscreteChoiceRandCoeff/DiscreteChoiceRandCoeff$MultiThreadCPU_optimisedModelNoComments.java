package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DiscreteChoiceRandCoeff$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DiscreteChoiceRandCoeff$CoreInterface {
	private int[] ObsChoices;
	private int[][] Prices;
	private double b;
	private double[] beta;
	private int[] choices;
	private double[][] exped;
	private boolean fixedFlag$sample22 = false;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample41 = false;
	private boolean fixedFlag$sample76 = false;
	private boolean fixedProbFlag$sample22 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample41 = false;
	private boolean fixedProbFlag$sample76 = false;
	private boolean[] guard$sample22categorical75$global;
	private boolean[][] guard$sample22put74$global;
	private boolean[][] guard$sample41categorical75$global;
	private boolean[][][] guard$sample41put74$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b;
	private double logProbability$beta;
	private double logProbability$choices;
	private double logProbability$exped;
	private double logProbability$prob;
	private double[] logProbability$sample22;
	private double[] logProbability$sample41;
	private double[] logProbability$sample76;
	private double logProbability$sigma;
	private double logProbability$ut;
	private double logProbability$var15;
	private double logProbability$var26;
	private double logProbability$var32;
	private double logProbability$var34;
	private double[] logProbability$var72;
	private int noObs;
	private int noProducts;
	private double[][] prob;
	private boolean setFlag$beta = false;
	private boolean setFlag$choices = false;
	private boolean setFlag$ut = false;
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
	}

	@Override
	public final double[] get$beta() {
		return beta;
	}

	@Override
	public final void set$beta(double[] cv$value) {
		beta = cv$value;
		setFlag$beta = true;
	}

	@Override
	public final int[] get$choices() {
		return choices;
	}

	@Override
	public final void set$choices(int[] cv$value) {
		choices = cv$value;
		setFlag$choices = true;
	}

	@Override
	public final boolean get$fixedFlag$sample22() {
		return fixedFlag$sample22;
	}

	@Override
	public final void set$fixedFlag$sample22(boolean cv$value) {
		fixedFlag$sample22 = cv$value;
		fixedProbFlag$sample22 = (cv$value && fixedProbFlag$sample22);
		fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
	}

	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	@Override
	public final void set$fixedFlag$sample29(boolean cv$value) {
		fixedFlag$sample29 = cv$value;
		fixedProbFlag$sample29 = (cv$value && fixedProbFlag$sample29);
		fixedProbFlag$sample41 = (cv$value && fixedProbFlag$sample41);
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		fixedFlag$sample35 = cv$value;
		fixedProbFlag$sample35 = (cv$value && fixedProbFlag$sample35);
		fixedProbFlag$sample41 = (cv$value && fixedProbFlag$sample41);
	}

	@Override
	public final boolean get$fixedFlag$sample41() {
		return fixedFlag$sample41;
	}

	@Override
	public final void set$fixedFlag$sample41(boolean cv$value) {
		fixedFlag$sample41 = cv$value;
		fixedProbFlag$sample41 = (cv$value && fixedProbFlag$sample41);
		fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
	}

	@Override
	public final boolean get$fixedFlag$sample76() {
		return fixedFlag$sample76;
	}

	@Override
	public final void set$fixedFlag$sample76(boolean cv$value) {
		fixedFlag$sample76 = cv$value;
		fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
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
	}

	@Override
	public final double[] get$ut() {
		return ut;
	}

	@Override
	public final void set$ut(double[] cv$value) {
		ut = cv$value;
		setFlag$ut = true;
	}

	private final void logProbabilityValue$sample22() {
		if(!fixedProbFlag$sample22) {
			double cv$sampleAccumulator = 0.0;
			for(int var19 = 0; var19 < noProducts; var19 += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((ut[var19] / 3.1622776601683795)) - 1.151292546497023);
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				logProbability$sample22[var19] = cv$distributionAccumulator;
				if((0 < noObs)) {
					logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
					logProbability$prob = (logProbability$prob + cv$distributionAccumulator);
				}
			}
			logProbability$var15 = cv$sampleAccumulator;
			logProbability$ut = (logProbability$ut + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample22 = fixedFlag$sample22;
		} else {
			double cv$rvAccumulator = 0.0;
			for(int var19 = 0; var19 < noProducts; var19 += 1) {
				double cv$sampleValue = logProbability$sample22[var19];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				if((0 < noObs)) {
					logProbability$exped = (logProbability$exped + cv$sampleValue);
					logProbability$prob = (logProbability$prob + cv$sampleValue);
				}
			}
			logProbability$var15 = cv$rvAccumulator;
			logProbability$ut = (logProbability$ut + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample29() {
		if(!fixedProbFlag$sample29) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((b / 3.1622776601683795)) - 1.151292546497023);
			logProbability$var26 = cv$distributionAccumulator;
			logProbability$b = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample29 = fixedFlag$sample29;
		} else {
			logProbability$var26 = logProbability$b;
			logProbability$$model = (logProbability$$model + logProbability$b);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + logProbability$b);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityInverseGamma(sigma, 2.0, 2.0);
			logProbability$var32 = cv$distributionAccumulator;
			logProbability$sigma = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample35 = fixedFlag$sample35;
		} else {
			logProbability$var32 = logProbability$sigma;
			logProbability$$model = (logProbability$$model + logProbability$sigma);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + logProbability$sigma);
		}
	}

	private final void logProbabilityValue$sample41() {
		if(!fixedProbFlag$sample41) {
			double cv$sampleAccumulator = 0.0;
			for(int var38 = 0; var38 < noObs; var38 += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((beta[var38] - b) / Math.sqrt(sigma))) - (Math.log(sigma) * 0.5));
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				logProbability$sample41[var38] = cv$distributionAccumulator;
				if((0 < noProducts)) {
					logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
					logProbability$prob = (logProbability$prob + cv$distributionAccumulator);
				}
			}
			logProbability$var34 = cv$sampleAccumulator;
			logProbability$beta = (logProbability$beta + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample41)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample41 = ((fixedFlag$sample41 && fixedFlag$sample29) && fixedFlag$sample35);
		} else {
			double cv$rvAccumulator = 0.0;
			for(int var38 = 0; var38 < noObs; var38 += 1) {
				double cv$sampleValue = logProbability$sample41[var38];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				if((0 < noProducts)) {
					logProbability$exped = (logProbability$exped + cv$sampleValue);
					logProbability$prob = (logProbability$prob + cv$sampleValue);
				}
			}
			logProbability$var34 = cv$rvAccumulator;
			logProbability$beta = (logProbability$beta + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			if(fixedFlag$sample41)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample76() {
		if(!fixedProbFlag$sample76) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noObs; i += 1) {
				int cv$sampleValue = choices[i];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < prob[i].length))?Math.log(prob[i][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var72[i] = cv$distributionAccumulator;
				logProbability$sample76[i] = cv$distributionAccumulator;
			}
			logProbability$choices = (logProbability$choices + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample76 = ((fixedFlag$sample76 && fixedFlag$sample22) && fixedFlag$sample41);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noObs; i += 1) {
				double cv$rvAccumulator = logProbability$sample76[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var72[i] = cv$rvAccumulator;
			}
			logProbability$choices = (logProbability$choices + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample22(int var19) {
		double cv$originalValue = ut[var19];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			for(int i = 0; i < noObs; i += 1)
				guard$sample22categorical75$global[i] = false;
			for(int i = 0; i < noObs; i += 1) {
				if(!guard$sample22categorical75$global[i]) {
					guard$sample22categorical75$global[i] = true;
					double[] cv$temp$2$prob = prob[i];
					cv$accumulatedProbabilities = ((((0.0 <= choices[i]) && (choices[i] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			for(int i = 0; i < noObs; i += 1) {
				if(!guard$sample22categorical75$global[i]) {
					double[] cv$temp$3$prob = prob[i];
					cv$accumulatedProbabilities = ((((0.0 <= choices[i]) && (choices[i] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		ut[var19] = cv$proposedValue;
		for(int i = 0; i < noObs; i += 1)
			exped[i][var19] = Math.exp((ut[var19] - (beta[i] * Prices[i][var19])));
		for(int i = 0; i < noObs; i += 1) {
			for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1)
				guard$sample22put74$global[i][j$var68] = false;
		}
		for(int i = 0; i < noObs; i += 1)
			guard$sample22put74$global[i][var19] = false;
		for(int i = 0; i < noObs; i += 1) {
			for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
				if(!guard$sample22put74$global[i][j$var68]) {
					guard$sample22put74$global[i][j$var68] = true;
					double reduceVar$sum$15 = 0.0;
					for(int cv$reduction961Index = 0; cv$reduction961Index < noProducts; cv$reduction961Index += 1)
						reduceVar$sum$15 = (reduceVar$sum$15 + exped[i][cv$reduction961Index]);
					prob[i][j$var68] = (exped[i][j$var68] / reduceVar$sum$15);
				}
			}
		}
		for(int i = 0; i < noObs; i += 1) {
			if(!guard$sample22put74$global[i][var19]) {
				guard$sample22put74$global[i][var19] = true;
				double reduceVar$sum$16 = 0.0;
				for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1)
					reduceVar$sum$16 = (reduceVar$sum$16 + exped[i][cv$reduction63Index]);
				prob[i][var19] = (exped[i][var19] / reduceVar$sum$16);
			}
		}
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		for(int i = 0; i < noObs; i += 1)
			guard$sample22categorical75$global[i] = false;
		for(int i = 0; i < noObs; i += 1) {
			if(!guard$sample22categorical75$global[i]) {
				guard$sample22categorical75$global[i] = true;
				double[] cv$temp$2$prob = prob[i];
				cv$accumulatedProbabilities = ((((0.0 <= choices[i]) && (choices[i] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		for(int i = 0; i < noObs; i += 1) {
			if(!guard$sample22categorical75$global[i]) {
				guard$sample22categorical75$global[i] = true;
				double[] cv$temp$3$prob = prob[i];
				cv$accumulatedProbabilities = ((((0.0 <= choices[i]) && (choices[i] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			ut[var19] = cv$originalValue;
			for(int i = 0; i < noObs; i += 1)
				exped[i][var19] = Math.exp((ut[var19] - (beta[i] * Prices[i][var19])));
			for(int i = 0; i < noObs; i += 1) {
				for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1)
					guard$sample22put74$global[i][j$var68] = false;
			}
			for(int i = 0; i < noObs; i += 1)
				guard$sample22put74$global[i][var19] = false;
			for(int i = 0; i < noObs; i += 1) {
				for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
					if(!guard$sample22put74$global[i][j$var68]) {
						guard$sample22put74$global[i][j$var68] = true;
						double reduceVar$sum$18 = 0.0;
						for(int cv$reduction1125Index = 0; cv$reduction1125Index < noProducts; cv$reduction1125Index += 1)
							reduceVar$sum$18 = (reduceVar$sum$18 + exped[i][cv$reduction1125Index]);
						prob[i][j$var68] = (exped[i][j$var68] / reduceVar$sum$18);
					}
				}
			}
			for(int i = 0; i < noObs; i += 1) {
				if(!guard$sample22put74$global[i][var19]) {
					guard$sample22put74$global[i][var19] = true;
					double reduceVar$sum$19 = 0.0;
					for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1)
						reduceVar$sum$19 = (reduceVar$sum$19 + exped[i][cv$reduction63Index]);
					prob[i][var19] = (exped[i][var19] / reduceVar$sum$19);
				}
			}
		}
	}

	private final void sample29() {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int var38 = 0; var38 < noObs; var38 += 1) {
			cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
			cv$sum = (cv$sum + beta[var38]);
			if(cv$sigmaNotFound) {
				cv$sigmaValue = sigma;
				cv$sigmaNotFound = false;
			}
		}
		b = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void sample35() {
		double cv$sum = 0.0;
		int cv$count = 0;
		for(int var38 = 0; var38 < noObs; var38 += 1) {
			double cv$var34$diff = (b - beta[var38]);
			cv$sum = (cv$sum + (cv$var34$diff * cv$var34$diff));
			cv$count = (cv$count + 1);
		}
		sigma = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 2.0, 2.0, cv$sum, cv$count);
	}

	private final void sample41(int var38, int threadID$cv$var38, Rng RNG$) {
		double cv$originalValue = beta[var38];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability;
		{
			{
				double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$originalValue - b) / Math.sqrt(sigma))) - (Math.log(sigma) * 0.5));
				if((0 < noProducts)) {
					boolean[] guard$sample41categorical75 = guard$sample41categorical75$global[threadID$cv$var38];
					guard$sample41categorical75[var38] = false;
					if(!guard$sample41categorical75[var38]) {
						guard$sample41categorical75[var38] = true;
						double[] cv$temp$2$prob = prob[var38];
						cv$accumulatedProbabilities = ((((0.0 <= choices[var38]) && (choices[var38] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[var38]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!guard$sample41categorical75[var38]) {
						guard$sample41categorical75[var38] = true;
						double[] cv$temp$3$prob = prob[var38];
						cv$accumulatedProbabilities = ((((0.0 <= choices[var38]) && (choices[var38] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[var38]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
				cv$originalProbability = cv$accumulatedProbabilities;
			}
			beta[var38] = cv$proposedValue;
			for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1)
				exped[var38][j$var48] = Math.exp((ut[j$var48] - (beta[var38] * Prices[var38][j$var48])));
			boolean[][] guard$sample41put74 = guard$sample41put74$global[threadID$cv$var38];
			if((0 < noProducts)) {
				for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1)
					guard$sample41put74[var38][j$var68] = false;
			}
			for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1)
				guard$sample41put74[var38][j$var48] = false;
			if((0 < noProducts)) {
				for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
					if(!guard$sample41put74[var38][j$var68]) {
						guard$sample41put74[var38][j$var68] = true;
						double reduceVar$sum$20 = 0.0;
						for(int cv$reduction1240Index = 0; cv$reduction1240Index < noProducts; cv$reduction1240Index += 1)
							reduceVar$sum$20 = (reduceVar$sum$20 + exped[var38][cv$reduction1240Index]);
						prob[var38][j$var68] = (exped[var38][j$var68] / reduceVar$sum$20);
					}
				}
			}
			for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
				if(!guard$sample41put74[var38][j$var48]) {
					guard$sample41put74[var38][j$var48] = true;
					double reduceVar$sum$21 = 0.0;
					for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1)
						reduceVar$sum$21 = (reduceVar$sum$21 + exped[var38][cv$reduction63Index]);
					prob[var38][j$var48] = (exped[var38][j$var48] / reduceVar$sum$21);
				}
			}
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - b) / Math.sqrt(sigma))) - (Math.log(sigma) * 0.5));
			if((0 < noProducts)) {
				boolean[] guard$sample41categorical75 = guard$sample41categorical75$global[threadID$cv$var38];
				guard$sample41categorical75[var38] = false;
				if(!guard$sample41categorical75[var38]) {
					guard$sample41categorical75[var38] = true;
					double[] cv$temp$2$prob = prob[var38];
					cv$accumulatedProbabilities = ((((0.0 <= choices[var38]) && (choices[var38] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[var38]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				if(!guard$sample41categorical75[var38]) {
					guard$sample41categorical75[var38] = true;
					double[] cv$temp$3$prob = prob[var38];
					cv$accumulatedProbabilities = ((((0.0 <= choices[var38]) && (choices[var38] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[var38]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			cv$proposedProbability = cv$accumulatedProbabilities;
		}
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$proposedProbability - cv$originalProbability)))) {
			beta[var38] = cv$originalValue;
			for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1)
				exped[var38][j$var48] = Math.exp((ut[j$var48] - (beta[var38] * Prices[var38][j$var48])));
			boolean[][] guard$sample41put74 = guard$sample41put74$global[threadID$cv$var38];
			if((0 < noProducts)) {
				for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1)
					guard$sample41put74[var38][j$var68] = false;
			}
			for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1)
				guard$sample41put74[var38][j$var48] = false;
			if((0 < noProducts)) {
				for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
					if(!guard$sample41put74[var38][j$var68]) {
						guard$sample41put74[var38][j$var68] = true;
						double reduceVar$sum$23 = 0.0;
						for(int cv$reduction1404Index = 0; cv$reduction1404Index < noProducts; cv$reduction1404Index += 1)
							reduceVar$sum$23 = (reduceVar$sum$23 + exped[var38][cv$reduction1404Index]);
						prob[var38][j$var68] = (exped[var38][j$var68] / reduceVar$sum$23);
					}
				}
			}
			for(int j$var48 = 0; j$var48 < noProducts; j$var48 += 1) {
				if(!guard$sample41put74[var38][j$var48]) {
					guard$sample41put74[var38][j$var48] = true;
					double reduceVar$sum$24 = 0.0;
					for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1)
						reduceVar$sum$24 = (reduceVar$sum$24 + exped[var38][cv$reduction63Index]);
					prob[var38][j$var48] = (exped[var38][j$var48] / reduceVar$sum$24);
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max_j$var68 = 0;
			if((0 < noObs))
				cv$max_j$var68 = Math.max(0, noProducts);
			guard$sample22put74$global = new boolean[Math.max(0, noObs)][cv$max_j$var68];
		}
		guard$sample22categorical75$global = new boolean[Math.max(0, noObs)];
		{
			int cv$max_j$var68 = 0;
			if((0 < noObs))
				cv$max_j$var68 = Math.max(0, noProducts);
			int cv$max_i = Math.max(0, noObs);
			int cv$threadCount = threadCount();
			guard$sample41put74$global = new boolean[cv$threadCount][][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				guard$sample41put74$global[cv$index] = new boolean[cv$max_i][cv$max_j$var68];
		}
		int cv$max_i = Math.max(0, noObs);
		int cv$threadCount = threadCount();
		guard$sample41categorical75$global = new boolean[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			guard$sample41categorical75$global[cv$index] = new boolean[cv$max_i];
	}

	@Override
	public final void allocator() {
		if(!setFlag$ut)
			ut = new double[noProducts];
		if(!setFlag$beta)
			beta = new double[noObs];
		if(!setFlag$choices)
			choices = new int[noObs];
		exped = new double[noObs][];
		for(int i = 0; i < noObs; i += 1)
			exped[i] = new double[noProducts];
		prob = new double[noObs][];
		for(int i = 0; i < noObs; i += 1)
			prob[i] = new double[noProducts];
		logProbability$sample22 = new double[noProducts];
		logProbability$sample41 = new double[noObs];
		logProbability$var72 = new double[noObs];
		logProbability$sample76 = new double[noObs];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample22)
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var19, int forEnd$var19, int threadID$var19, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var19 = forStart$var19; var19 < forEnd$var19; var19 += 1)
							ut[var19] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!fixedFlag$sample29)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample35)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		if(!fixedFlag$sample41)
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1)
							beta[var38] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
				}
			);

		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						if((!fixedFlag$sample22 || !fixedFlag$sample41)) {
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var48, int forEnd$j$var48, int threadID$j$var48, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var48 = forStart$j$var48; j$var48 < forEnd$j$var48; j$var48 += 1)
											exped[i][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
								}
							);
							double reduceVar$sum$25 = 0.0;
							for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1)
								reduceVar$sum$25 = (reduceVar$sum$25 + exped[i][cv$reduction63Index]);
							double reduceVar$sum$25$1 = reduceVar$sum$25;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1)
											prob[i][j$var68] = (exped[i][j$var68] / reduceVar$sum$25$1);
								}
							);
						}
						if(!fixedFlag$sample76)
							choices[i] = DistributionSampling.sampleCategorical(RNG$1, prob[i]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample22)
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var19, int forEnd$var19, int threadID$var19, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var19 = forStart$var19; var19 < forEnd$var19; var19 += 1)
							ut[var19] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!fixedFlag$sample29)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample35)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		if(!fixedFlag$sample41)
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1)
							beta[var38] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
				}
			);

		if((!fixedFlag$sample22 || !fixedFlag$sample41))
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
							int i = index$i;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var48, int forEnd$j$var48, int threadID$j$var48, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var48 = forStart$j$var48; j$var48 < forEnd$j$var48; j$var48 += 1)
											exped[i][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
								}
							);
							double reduceVar$sum$27 = 0.0;
							for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1)
								reduceVar$sum$27 = (reduceVar$sum$27 + exped[i][cv$reduction63Index]);
							double reduceVar$sum$27$1 = reduceVar$sum$27;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1)
											prob[i][j$var68] = (exped[i][j$var68] / reduceVar$sum$27$1);
								}
							);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample22)
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var19, int forEnd$var19, int threadID$var19, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var19 = forStart$var19; var19 < forEnd$var19; var19 += 1)
							ut[var19] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!fixedFlag$sample29)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample35)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		if(!fixedFlag$sample41)
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1)
							beta[var38] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
				}
			);

		if((!fixedFlag$sample22 || !fixedFlag$sample41))
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
							int i = index$i;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var48, int forEnd$j$var48, int threadID$j$var48, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var48 = forStart$j$var48; j$var48 < forEnd$j$var48; j$var48 += 1)
											exped[i][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
								}
							);
							double reduceVar$sum$26 = 0.0;
							for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1)
								reduceVar$sum$26 = (reduceVar$sum$26 + exped[i][cv$reduction63Index]);
							double reduceVar$sum$26$1 = reduceVar$sum$26;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1)
											prob[i][j$var68] = (exped[i][j$var68] / reduceVar$sum$26$1);
								}
							);
						}
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample22) {
				for(int var19 = 0; var19 < noProducts; var19 += 1)
					sample22(var19);
			}
			if(!fixedFlag$sample29)
				sample29();
			if(!fixedFlag$sample35)
				sample35();
			if(!fixedFlag$sample41)
				parallelFor(RNG$, 0, noObs, 1,
					(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1)
								sample41(var38, threadID$var38, RNG$1);
					}
				);

		} else {
			if(!fixedFlag$sample41)
				parallelFor(RNG$, 0, noObs, 1,
					(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1)
								sample41(var38, threadID$var38, RNG$1);
					}
				);

			if(!fixedFlag$sample35)
				sample35();
			if(!fixedFlag$sample29)
				sample29();
			if(!fixedFlag$sample22) {
				for(int var19 = (noProducts - 1); var19 >= 0; var19 -= 1)
					sample22(var19);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var15 = 0.0;
		logProbability$ut = 0.0;
		logProbability$prob = 0.0;
		logProbability$exped = 0.0;
		if(!fixedProbFlag$sample22) {
			for(int var19 = 0; var19 < noProducts; var19 += 1)
				logProbability$sample22[var19] = 0.0;
		}
		logProbability$var26 = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$b = 0.0;
		logProbability$var32 = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$sigma = 0.0;
		logProbability$var34 = 0.0;
		logProbability$beta = 0.0;
		if(!fixedProbFlag$sample41) {
			for(int var38 = 0; var38 < noObs; var38 += 1)
				logProbability$sample41[var38] = 0.0;
		}
		for(int i = 0; i < noObs; i += 1)
			logProbability$var72[i] = 0.0;
		logProbability$choices = 0.0;
		if(!fixedProbFlag$sample76) {
			for(int i = 0; i < noObs; i += 1)
				logProbability$sample76[i] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample22)
			logProbabilityValue$sample22();
		if(fixedFlag$sample29)
			logProbabilityValue$sample29();
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		if(fixedFlag$sample41)
			logProbabilityValue$sample41();
		logProbabilityValue$sample76();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample22();
		logProbabilityValue$sample29();
		logProbabilityValue$sample35();
		logProbabilityValue$sample41();
		logProbabilityValue$sample76();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample22();
		logProbabilityValue$sample29();
		logProbabilityValue$sample35();
		logProbabilityValue$sample41();
		logProbabilityValue$sample76();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample22)
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var19, int forEnd$var19, int threadID$var19, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var19 = forStart$var19; var19 < forEnd$var19; var19 += 1)
							ut[var19] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!fixedFlag$sample29)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample35)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		if(!fixedFlag$sample41)
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var38, int forEnd$var38, int threadID$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var38 = forStart$var38; var38 < forEnd$var38; var38 += 1)
							beta[var38] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
				}
			);

		if((!fixedFlag$sample22 || !fixedFlag$sample41))
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
							int i = index$i;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var48, int forEnd$j$var48, int threadID$j$var48, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var48 = forStart$j$var48; j$var48 < forEnd$j$var48; j$var48 += 1)
											exped[i][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
								}
							);
							double reduceVar$sum$28 = 0.0;
							for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1)
								reduceVar$sum$28 = (reduceVar$sum$28 + exped[i][cv$reduction63Index]);
							double reduceVar$sum$28$1 = reduceVar$sum$28;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1)
											prob[i][j$var68] = (exped[i][j$var68] / reduceVar$sum$28$1);
								}
							);
						}
				}
			);

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
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						if((setFlag$ut && setFlag$beta))
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var48, int forEnd$j$var48, int threadID$j$var48, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var48 = forStart$j$var48; j$var48 < forEnd$j$var48; j$var48 += 1)
											exped[i][j$var48] = Math.exp((ut[j$var48] - (beta[i] * Prices[i][j$var48])));
								}
							);

						double reduceVar$sum$29 = 0.0;
						for(int cv$reduction63Index = 0; cv$reduction63Index < noProducts; cv$reduction63Index += 1)
							reduceVar$sum$29 = (reduceVar$sum$29 + exped[i][cv$reduction63Index]);
						if((setFlag$ut && setFlag$beta)) {
							double reduceVar$sum$29$1 = reduceVar$sum$29;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1)
											prob[i][j$var68] = (exped[i][j$var68] / reduceVar$sum$29$1);
								}
							);
						}
					}
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\n\nmodel DiscreteChoiceRandCoeff(int noProducts, int noObs, int[] ObsChoices, int[][] Prices) {\n    // we just need an uninformative prior for utility intercepts\n\n    // draw utilities\n    double[] ut = gaussian(0, 10).sample(noProducts);\n    //can we set the first element to 0? like ut[0] <~ 0\n\n    //priors for distribution of beta\n    double b = gaussian(0,10).sample();\n    double sigma =  inverseGamma(2,2).sample();\n\n    double[] beta = gaussian(b, sigma).sample(noObs);\n\n    int[] choices = new int[noObs];\n\n    for (int i:[0..noObs)){\n        // calculate choice probabilities for consumer i\n\n        double[] exped = new double[noProducts];\n        for(int j : [0..noProducts)) {\n            exped[j] = exp(ut[j]- beta[i]*Prices[i][j]);\n            }\n        double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n        public double[] prob = new double[noProducts];\n        for (int j : [0..noProducts)) {\n            prob[j] = exped[j] / sum;\n        }\n        // emit choices of consumer i\n        choices[i] = categorical(prob).sample();\n                                }\n\n    // assert that generated choices match observed choices\n    choices.observe(ObsChoices);\n}";
	}
}