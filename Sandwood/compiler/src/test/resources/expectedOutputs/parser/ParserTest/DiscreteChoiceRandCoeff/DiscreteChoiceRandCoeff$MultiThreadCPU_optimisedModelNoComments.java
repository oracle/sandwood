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
	private boolean fixedFlag$sample111 = false;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample36 = false;
	private boolean fixedFlag$sample42 = false;
	private boolean fixedFlag$sample55 = false;
	private boolean fixedProbFlag$sample111 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample36 = false;
	private boolean fixedProbFlag$sample42 = false;
	private boolean fixedProbFlag$sample55 = false;
	private boolean[] guard$sample29categorical110$global;
	private boolean[][] guard$sample29put109$global;
	private boolean[][] guard$sample55categorical110$global;
	private boolean[][][] guard$sample55put109$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b;
	private double logProbability$beta;
	private double logProbability$choices;
	private double logProbability$exped;
	private double logProbability$prob;
	private double[] logProbability$sample111;
	private double[] logProbability$sample29;
	private double[] logProbability$sample55;
	private double logProbability$sigma;
	private double logProbability$ut;
	private double[] logProbability$var107;
	private double logProbability$var15;
	private double logProbability$var33;
	private double logProbability$var39;
	private double logProbability$var41;
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
		fixedProbFlag$sample36 = false;
		fixedProbFlag$sample55 = false;
	}

	@Override
	public final double[] get$beta() {
		return beta;
	}

	@Override
	public final void set$beta(double[] cv$value) {
		beta = cv$value;
		setFlag$beta = true;
		fixedProbFlag$sample55 = false;
		fixedProbFlag$sample111 = false;
	}

	@Override
	public final int[] get$choices() {
		return choices;
	}

	@Override
	public final void set$choices(int[] cv$value) {
		choices = cv$value;
		setFlag$choices = true;
		fixedProbFlag$sample111 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample111() {
		return fixedFlag$sample111;
	}

	@Override
	public final void set$fixedFlag$sample111(boolean cv$value) {
		fixedFlag$sample111 = cv$value;
		fixedProbFlag$sample111 = (cv$value && fixedProbFlag$sample111);
	}

	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	@Override
	public final void set$fixedFlag$sample29(boolean cv$value) {
		fixedFlag$sample29 = cv$value;
		fixedProbFlag$sample29 = (cv$value && fixedProbFlag$sample29);
		fixedProbFlag$sample111 = (cv$value && fixedProbFlag$sample111);
	}

	@Override
	public final boolean get$fixedFlag$sample36() {
		return fixedFlag$sample36;
	}

	@Override
	public final void set$fixedFlag$sample36(boolean cv$value) {
		fixedFlag$sample36 = cv$value;
		fixedProbFlag$sample36 = (cv$value && fixedProbFlag$sample36);
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
	}

	@Override
	public final boolean get$fixedFlag$sample42() {
		return fixedFlag$sample42;
	}

	@Override
	public final void set$fixedFlag$sample42(boolean cv$value) {
		fixedFlag$sample42 = cv$value;
		fixedProbFlag$sample42 = (cv$value && fixedProbFlag$sample42);
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
	}

	@Override
	public final boolean get$fixedFlag$sample55() {
		return fixedFlag$sample55;
	}

	@Override
	public final void set$fixedFlag$sample55(boolean cv$value) {
		fixedFlag$sample55 = cv$value;
		fixedProbFlag$sample55 = (cv$value && fixedProbFlag$sample55);
		fixedProbFlag$sample111 = (cv$value && fixedProbFlag$sample111);
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
		fixedProbFlag$sample42 = false;
		fixedProbFlag$sample55 = false;
	}

	@Override
	public final double[] get$ut() {
		return ut;
	}

	@Override
	public final void set$ut(double[] cv$value) {
		ut = cv$value;
		setFlag$ut = true;
		fixedProbFlag$sample29 = false;
		fixedProbFlag$sample111 = false;
	}

	private final void logProbabilityValue$sample111() {
		if(!fixedProbFlag$sample111) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noObs; i += 1) {
				int cv$sampleValue = choices[i];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < prob[i].length))?Math.log(prob[i][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var107[i] = cv$distributionAccumulator;
				logProbability$sample111[i] = cv$distributionAccumulator;
			}
			logProbability$choices = (logProbability$choices + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample111 = ((fixedFlag$sample111 && fixedFlag$sample29) && fixedFlag$sample55);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < noObs; i += 1) {
				double cv$rvAccumulator = logProbability$sample111[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var107[i] = cv$rvAccumulator;
			}
			logProbability$choices = (logProbability$choices + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample29() {
		if(!fixedProbFlag$sample29) {
			double cv$sampleAccumulator = 0.0;
			for(int var26 = 0; var26 < noProducts; var26 += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((ut[var26] / 3.1622776601683795)) - 1.151292546497023);
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				logProbability$sample29[var26] = cv$distributionAccumulator;
				if((0 < noObs)) {
					logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
					logProbability$prob = (logProbability$prob + cv$distributionAccumulator);
				}
			}
			logProbability$var15 = cv$sampleAccumulator;
			logProbability$ut = (logProbability$ut + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample29 = fixedFlag$sample29;
		} else {
			double cv$rvAccumulator = 0.0;
			for(int var26 = 0; var26 < noProducts; var26 += 1) {
				double cv$sampleValue = logProbability$sample29[var26];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				if((0 < noObs)) {
					logProbability$exped = (logProbability$exped + cv$sampleValue);
					logProbability$prob = (logProbability$prob + cv$sampleValue);
				}
			}
			logProbability$var15 = cv$rvAccumulator;
			logProbability$ut = (logProbability$ut + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample36() {
		if(!fixedProbFlag$sample36) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((b / 3.1622776601683795)) - 1.151292546497023);
			logProbability$var33 = cv$distributionAccumulator;
			logProbability$b = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample36 = fixedFlag$sample36;
		} else {
			logProbability$var33 = logProbability$b;
			logProbability$$model = (logProbability$$model + logProbability$b);
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + logProbability$b);
		}
	}

	private final void logProbabilityValue$sample42() {
		if(!fixedProbFlag$sample42) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityInverseGamma(sigma, 2.0, 2.0);
			logProbability$var39 = cv$distributionAccumulator;
			logProbability$sigma = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample42 = fixedFlag$sample42;
		} else {
			logProbability$var39 = logProbability$sigma;
			logProbability$$model = (logProbability$$model + logProbability$sigma);
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + logProbability$sigma);
		}
	}

	private final void logProbabilityValue$sample55() {
		if(!fixedProbFlag$sample55) {
			double cv$sampleAccumulator = 0.0;
			for(int var52 = 0; var52 < noObs; var52 += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((beta[var52] - b) / Math.sqrt(sigma))) - (Math.log(sigma) * 0.5));
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				logProbability$sample55[var52] = cv$distributionAccumulator;
				if((0 < noProducts)) {
					logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
					logProbability$prob = (logProbability$prob + cv$distributionAccumulator);
				}
			}
			logProbability$var41 = cv$sampleAccumulator;
			logProbability$beta = (logProbability$beta + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample55 = ((fixedFlag$sample55 && fixedFlag$sample36) && fixedFlag$sample42);
		} else {
			double cv$rvAccumulator = 0.0;
			for(int var52 = 0; var52 < noObs; var52 += 1) {
				double cv$sampleValue = logProbability$sample55[var52];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				if((0 < noProducts)) {
					logProbability$exped = (logProbability$exped + cv$sampleValue);
					logProbability$prob = (logProbability$prob + cv$sampleValue);
				}
			}
			logProbability$var41 = cv$rvAccumulator;
			logProbability$beta = (logProbability$beta + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void sample29(int var26) {
		double cv$originalValue = ut[var26];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			for(int i = 0; i < noObs; i += 1)
				guard$sample29categorical110$global[i] = false;
			for(int i = 0; i < noObs; i += 1) {
				if(!guard$sample29categorical110$global[i]) {
					guard$sample29categorical110$global[i] = true;
					double[] cv$temp$2$prob = prob[i];
					cv$accumulatedProbabilities = ((((0.0 <= choices[i]) && (choices[i] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			for(int i = 0; i < noObs; i += 1) {
				if(!guard$sample29categorical110$global[i]) {
					double[] cv$temp$3$prob = prob[i];
					cv$accumulatedProbabilities = ((((0.0 <= choices[i]) && (choices[i] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		ut[var26] = cv$proposedValue;
		for(int i = 0; i < noObs; i += 1)
			exped[i][var26] = Math.exp((ut[var26] - (beta[i] * Prices[i][var26])));
		for(int i = 0; i < noObs; i += 1) {
			for(int j$var103 = 0; j$var103 < noProducts; j$var103 += 1)
				guard$sample29put109$global[i][j$var103] = false;
		}
		for(int i = 0; i < noObs; i += 1)
			guard$sample29put109$global[i][var26] = false;
		for(int i = 0; i < noObs; i += 1) {
			for(int j$var103 = 0; j$var103 < noProducts; j$var103 += 1) {
				if(!guard$sample29put109$global[i][j$var103]) {
					guard$sample29put109$global[i][j$var103] = true;
					double reduceVar$sum$15 = 0.0;
					for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
						reduceVar$sum$15 = (reduceVar$sum$15 + exped[i][cv$reduction90Index]);
					prob[i][j$var103] = (exped[i][j$var103] / reduceVar$sum$15);
				}
			}
		}
		for(int i = 0; i < noObs; i += 1) {
			if(!guard$sample29put109$global[i][var26]) {
				guard$sample29put109$global[i][var26] = true;
				double reduceVar$sum$16 = 0.0;
				for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
					reduceVar$sum$16 = (reduceVar$sum$16 + exped[i][cv$reduction90Index]);
				prob[i][var26] = (exped[i][var26] / reduceVar$sum$16);
			}
		}
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		for(int i = 0; i < noObs; i += 1)
			guard$sample29categorical110$global[i] = false;
		for(int i = 0; i < noObs; i += 1) {
			if(!guard$sample29categorical110$global[i]) {
				guard$sample29categorical110$global[i] = true;
				double[] cv$temp$2$prob = prob[i];
				cv$accumulatedProbabilities = ((((0.0 <= choices[i]) && (choices[i] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		for(int i = 0; i < noObs; i += 1) {
			if(!guard$sample29categorical110$global[i]) {
				guard$sample29categorical110$global[i] = true;
				double[] cv$temp$3$prob = prob[i];
				cv$accumulatedProbabilities = ((((0.0 <= choices[i]) && (choices[i] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			ut[var26] = cv$originalValue;
			for(int i = 0; i < noObs; i += 1)
				exped[i][var26] = Math.exp((ut[var26] - (beta[i] * Prices[i][var26])));
			for(int i = 0; i < noObs; i += 1) {
				for(int j$var103 = 0; j$var103 < noProducts; j$var103 += 1)
					guard$sample29put109$global[i][j$var103] = false;
			}
			for(int i = 0; i < noObs; i += 1)
				guard$sample29put109$global[i][var26] = false;
			for(int i = 0; i < noObs; i += 1) {
				for(int j$var103 = 0; j$var103 < noProducts; j$var103 += 1) {
					if(!guard$sample29put109$global[i][j$var103]) {
						guard$sample29put109$global[i][j$var103] = true;
						double reduceVar$sum$18 = 0.0;
						for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
							reduceVar$sum$18 = (reduceVar$sum$18 + exped[i][cv$reduction90Index]);
						prob[i][j$var103] = (exped[i][j$var103] / reduceVar$sum$18);
					}
				}
			}
			for(int i = 0; i < noObs; i += 1) {
				if(!guard$sample29put109$global[i][var26]) {
					guard$sample29put109$global[i][var26] = true;
					double reduceVar$sum$19 = 0.0;
					for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
						reduceVar$sum$19 = (reduceVar$sum$19 + exped[i][cv$reduction90Index]);
					prob[i][var26] = (exped[i][var26] / reduceVar$sum$19);
				}
			}
		}
	}

	private final void sample36() {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int var52 = 0; var52 < noObs; var52 += 1) {
			cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
			cv$sum = (cv$sum + beta[var52]);
			if(cv$sigmaNotFound) {
				cv$sigmaValue = sigma;
				cv$sigmaNotFound = false;
			}
		}
		b = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void sample42() {
		double cv$sum = 0.0;
		int cv$count = 0;
		for(int var52 = 0; var52 < noObs; var52 += 1) {
			double cv$var41$diff = (b - beta[var52]);
			cv$sum = (cv$sum + (cv$var41$diff * cv$var41$diff));
			cv$count = (cv$count + 1);
		}
		sigma = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 2.0, 2.0, cv$sum, cv$count);
	}

	private final void sample55(int var52, int threadID$cv$var52, Rng RNG$) {
		double cv$originalValue = beta[var52];
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
					boolean[] guard$sample55categorical110 = guard$sample55categorical110$global[threadID$cv$var52];
					guard$sample55categorical110[var52] = false;
					if(!guard$sample55categorical110[var52]) {
						guard$sample55categorical110[var52] = true;
						double[] cv$temp$2$prob = prob[var52];
						cv$accumulatedProbabilities = ((((0.0 <= choices[var52]) && (choices[var52] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[var52]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
					if(!guard$sample55categorical110[var52]) {
						guard$sample55categorical110[var52] = true;
						double[] cv$temp$3$prob = prob[var52];
						cv$accumulatedProbabilities = ((((0.0 <= choices[var52]) && (choices[var52] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[var52]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
				cv$originalProbability = cv$accumulatedProbabilities;
			}
			beta[var52] = cv$proposedValue;
			for(int j$var75 = 0; j$var75 < noProducts; j$var75 += 1)
				exped[var52][j$var75] = Math.exp((ut[j$var75] - (beta[var52] * Prices[var52][j$var75])));
			boolean[][] guard$sample55put109 = guard$sample55put109$global[threadID$cv$var52];
			if((0 < noProducts)) {
				for(int j$var103 = 0; j$var103 < noProducts; j$var103 += 1)
					guard$sample55put109[var52][j$var103] = false;
			}
			for(int j$var75 = 0; j$var75 < noProducts; j$var75 += 1)
				guard$sample55put109[var52][j$var75] = false;
			if((0 < noProducts)) {
				for(int j$var103 = 0; j$var103 < noProducts; j$var103 += 1) {
					if(!guard$sample55put109[var52][j$var103]) {
						guard$sample55put109[var52][j$var103] = true;
						double reduceVar$sum$20 = 0.0;
						for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
							reduceVar$sum$20 = (reduceVar$sum$20 + exped[var52][cv$reduction90Index]);
						prob[var52][j$var103] = (exped[var52][j$var103] / reduceVar$sum$20);
					}
				}
			}
			for(int j$var75 = 0; j$var75 < noProducts; j$var75 += 1) {
				if(!guard$sample55put109[var52][j$var75]) {
					guard$sample55put109[var52][j$var75] = true;
					double reduceVar$sum$21 = 0.0;
					for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
						reduceVar$sum$21 = (reduceVar$sum$21 + exped[var52][cv$reduction90Index]);
					prob[var52][j$var75] = (exped[var52][j$var75] / reduceVar$sum$21);
				}
			}
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(((cv$proposedValue - b) / Math.sqrt(sigma))) - (Math.log(sigma) * 0.5));
			if((0 < noProducts)) {
				boolean[] guard$sample55categorical110 = guard$sample55categorical110$global[threadID$cv$var52];
				guard$sample55categorical110[var52] = false;
				if(!guard$sample55categorical110[var52]) {
					guard$sample55categorical110[var52] = true;
					double[] cv$temp$2$prob = prob[var52];
					cv$accumulatedProbabilities = ((((0.0 <= choices[var52]) && (choices[var52] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[var52]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				if(!guard$sample55categorical110[var52]) {
					guard$sample55categorical110[var52] = true;
					double[] cv$temp$3$prob = prob[var52];
					cv$accumulatedProbabilities = ((((0.0 <= choices[var52]) && (choices[var52] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[var52]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			cv$proposedProbability = cv$accumulatedProbabilities;
		}
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$proposedProbability - cv$originalProbability)))) {
			beta[var52] = cv$originalValue;
			for(int j$var75 = 0; j$var75 < noProducts; j$var75 += 1)
				exped[var52][j$var75] = Math.exp((ut[j$var75] - (beta[var52] * Prices[var52][j$var75])));
			boolean[][] guard$sample55put109 = guard$sample55put109$global[threadID$cv$var52];
			if((0 < noProducts)) {
				for(int j$var103 = 0; j$var103 < noProducts; j$var103 += 1)
					guard$sample55put109[var52][j$var103] = false;
			}
			for(int j$var75 = 0; j$var75 < noProducts; j$var75 += 1)
				guard$sample55put109[var52][j$var75] = false;
			if((0 < noProducts)) {
				for(int j$var103 = 0; j$var103 < noProducts; j$var103 += 1) {
					if(!guard$sample55put109[var52][j$var103]) {
						guard$sample55put109[var52][j$var103] = true;
						double reduceVar$sum$23 = 0.0;
						for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
							reduceVar$sum$23 = (reduceVar$sum$23 + exped[var52][cv$reduction90Index]);
						prob[var52][j$var103] = (exped[var52][j$var103] / reduceVar$sum$23);
					}
				}
			}
			for(int j$var75 = 0; j$var75 < noProducts; j$var75 += 1) {
				if(!guard$sample55put109[var52][j$var75]) {
					guard$sample55put109[var52][j$var75] = true;
					double reduceVar$sum$24 = 0.0;
					for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
						reduceVar$sum$24 = (reduceVar$sum$24 + exped[var52][cv$reduction90Index]);
					prob[var52][j$var75] = (exped[var52][j$var75] / reduceVar$sum$24);
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max_j$var103 = 0;
			if((0 < noObs))
				cv$max_j$var103 = Math.max(0, noProducts);
			guard$sample29put109$global = new boolean[Math.max(0, noObs)][cv$max_j$var103];
		}
		guard$sample29categorical110$global = new boolean[Math.max(0, noObs)];
		{
			int cv$max_j$var103 = 0;
			if((0 < noObs))
				cv$max_j$var103 = Math.max(0, noProducts);
			int cv$max_i = Math.max(0, noObs);
			int cv$threadCount = threadCount();
			guard$sample55put109$global = new boolean[cv$threadCount][][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				guard$sample55put109$global[cv$index] = new boolean[cv$max_i][cv$max_j$var103];
		}
		int cv$max_i = Math.max(0, noObs);
		int cv$threadCount = threadCount();
		guard$sample55categorical110$global = new boolean[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			guard$sample55categorical110$global[cv$index] = new boolean[cv$max_i];
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
		logProbability$sample29 = new double[noProducts];
		logProbability$sample55 = new double[noObs];
		logProbability$var107 = new double[noObs];
		logProbability$sample111 = new double[noObs];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample29)
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							ut[var26] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!fixedFlag$sample36)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample42)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		if(!fixedFlag$sample55)
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var52, int forEnd$var52, int threadID$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var52 = forStart$var52; var52 < forEnd$var52; var52 += 1)
							beta[var52] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
				}
			);

		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						if((!fixedFlag$sample29 || !fixedFlag$sample55)) {
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var75, int forEnd$j$var75, int threadID$j$var75, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var75 = forStart$j$var75; j$var75 < forEnd$j$var75; j$var75 += 1)
											exped[i][j$var75] = Math.exp((ut[j$var75] - (beta[i] * Prices[i][j$var75])));
								}
							);
							double reduceVar$sum$25 = 0.0;
							for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
								reduceVar$sum$25 = (reduceVar$sum$25 + exped[i][cv$reduction90Index]);
							double reduceVar$sum$25$1 = reduceVar$sum$25;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var103, int forEnd$j$var103, int threadID$j$var103, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var103 = forStart$j$var103; j$var103 < forEnd$j$var103; j$var103 += 1)
											prob[i][j$var103] = (exped[i][j$var103] / reduceVar$sum$25$1);
								}
							);
						}
						if(!fixedFlag$sample111)
							choices[i] = DistributionSampling.sampleCategorical(RNG$1, prob[i]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample29)
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							ut[var26] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!fixedFlag$sample36)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample42)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		if(!fixedFlag$sample55)
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var52, int forEnd$var52, int threadID$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var52 = forStart$var52; var52 < forEnd$var52; var52 += 1)
							beta[var52] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
				}
			);

		if((!fixedFlag$sample29 || !fixedFlag$sample55))
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
							int i = index$i;
							int threadID$i = threadID$index$i;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var75, int forEnd$j$var75, int threadID$j$var75, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var75 = forStart$j$var75; j$var75 < forEnd$j$var75; j$var75 += 1)
											exped[i][j$var75] = Math.exp((ut[j$var75] - (beta[i] * Prices[i][j$var75])));
								}
							);
							double reduceVar$sum$27 = 0.0;
							for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
								reduceVar$sum$27 = (reduceVar$sum$27 + exped[i][cv$reduction90Index]);
							double reduceVar$sum$27$1 = reduceVar$sum$27;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var103, int forEnd$j$var103, int threadID$j$var103, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var103 = forStart$j$var103; j$var103 < forEnd$j$var103; j$var103 += 1)
											prob[i][j$var103] = (exped[i][j$var103] / reduceVar$sum$27$1);
								}
							);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample29)
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							ut[var26] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!fixedFlag$sample36)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample42)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		if(!fixedFlag$sample55)
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var52, int forEnd$var52, int threadID$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var52 = forStart$var52; var52 < forEnd$var52; var52 += 1)
							beta[var52] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
				}
			);

		if((!fixedFlag$sample29 || !fixedFlag$sample55))
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
							int i = index$i;
							int threadID$i = threadID$index$i;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var75, int forEnd$j$var75, int threadID$j$var75, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var75 = forStart$j$var75; j$var75 < forEnd$j$var75; j$var75 += 1)
											exped[i][j$var75] = Math.exp((ut[j$var75] - (beta[i] * Prices[i][j$var75])));
								}
							);
							double reduceVar$sum$26 = 0.0;
							for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
								reduceVar$sum$26 = (reduceVar$sum$26 + exped[i][cv$reduction90Index]);
							double reduceVar$sum$26$1 = reduceVar$sum$26;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var103, int forEnd$j$var103, int threadID$j$var103, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var103 = forStart$j$var103; j$var103 < forEnd$j$var103; j$var103 += 1)
											prob[i][j$var103] = (exped[i][j$var103] / reduceVar$sum$26$1);
								}
							);
						}
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample29) {
				for(int var26 = 0; var26 < noProducts; var26 += 1)
					sample29(var26);
			}
			if(!fixedFlag$sample36)
				sample36();
			if(!fixedFlag$sample42)
				sample42();
			if(!fixedFlag$sample55)
				parallelFor(RNG$, 0, noObs, 1,
					(int forStart$var52, int forEnd$var52, int threadID$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var52 = forStart$var52; var52 < forEnd$var52; var52 += 1)
								sample55(var52, threadID$var52, RNG$1);
					}
				);

		} else {
			if(!fixedFlag$sample55)
				parallelFor(RNG$, 0, noObs, 1,
					(int forStart$var52, int forEnd$var52, int threadID$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var52 = forStart$var52; var52 < forEnd$var52; var52 += 1)
								sample55(var52, threadID$var52, RNG$1);
					}
				);

			if(!fixedFlag$sample42)
				sample42();
			if(!fixedFlag$sample36)
				sample36();
			if(!fixedFlag$sample29) {
				for(int var26 = (noProducts - 1); var26 >= 0; var26 -= 1)
					sample29(var26);
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
		logProbability$exped = 0.0;
		logProbability$prob = 0.0;
		logProbability$ut = 0.0;
		if(!fixedProbFlag$sample29) {
			for(int var26 = 0; var26 < noProducts; var26 += 1)
				logProbability$sample29[var26] = 0.0;
		}
		logProbability$var33 = 0.0;
		if(!fixedProbFlag$sample36)
			logProbability$b = 0.0;
		logProbability$var39 = 0.0;
		if(!fixedProbFlag$sample42)
			logProbability$sigma = 0.0;
		logProbability$var41 = 0.0;
		logProbability$beta = 0.0;
		if(!fixedProbFlag$sample55) {
			for(int var52 = 0; var52 < noObs; var52 += 1)
				logProbability$sample55[var52] = 0.0;
		}
		for(int i = 0; i < noObs; i += 1)
			logProbability$var107[i] = 0.0;
		logProbability$choices = 0.0;
		if(!fixedProbFlag$sample111) {
			for(int i = 0; i < noObs; i += 1)
				logProbability$sample111[i] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample29)
			logProbabilityValue$sample29();
		if(fixedFlag$sample36)
			logProbabilityValue$sample36();
		if(fixedFlag$sample42)
			logProbabilityValue$sample42();
		if(fixedFlag$sample55)
			logProbabilityValue$sample55();
		logProbabilityValue$sample111();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample29();
		logProbabilityValue$sample36();
		logProbabilityValue$sample42();
		logProbabilityValue$sample55();
		logProbabilityValue$sample111();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample29();
		logProbabilityValue$sample36();
		logProbabilityValue$sample42();
		logProbabilityValue$sample55();
		logProbabilityValue$sample111();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample29)
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							ut[var26] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!fixedFlag$sample36)
			b = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
		if(!fixedFlag$sample42)
			sigma = DistributionSampling.sampleInverseGamma(RNG$, 2.0, 2.0);
		if(!fixedFlag$sample55)
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$var52, int forEnd$var52, int threadID$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var52 = forStart$var52; var52 < forEnd$var52; var52 += 1)
							beta[var52] = ((Math.sqrt(sigma) * DistributionSampling.sampleGaussian(RNG$1)) + b);
				}
			);

		if((!fixedFlag$sample29 || !fixedFlag$sample55))
			parallelFor(RNG$, 0, noObs, 1,
				(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
							int i = index$i;
							int threadID$i = threadID$index$i;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var75, int forEnd$j$var75, int threadID$j$var75, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var75 = forStart$j$var75; j$var75 < forEnd$j$var75; j$var75 += 1)
											exped[i][j$var75] = Math.exp((ut[j$var75] - (beta[i] * Prices[i][j$var75])));
								}
							);
							double reduceVar$sum$28 = 0.0;
							for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
								reduceVar$sum$28 = (reduceVar$sum$28 + exped[i][cv$reduction90Index]);
							double reduceVar$sum$28$1 = reduceVar$sum$28;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var103, int forEnd$j$var103, int threadID$j$var103, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var103 = forStart$j$var103; j$var103 < forEnd$j$var103; j$var103 += 1)
											prob[i][j$var103] = (exped[i][j$var103] / reduceVar$sum$28$1);
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
						int threadID$i = threadID$index$i;
						if((setFlag$ut && setFlag$beta))
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var75, int forEnd$j$var75, int threadID$j$var75, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var75 = forStart$j$var75; j$var75 < forEnd$j$var75; j$var75 += 1)
											exped[i][j$var75] = Math.exp((ut[j$var75] - (beta[i] * Prices[i][j$var75])));
								}
							);

						double reduceVar$sum$29 = 0.0;
						for(int cv$reduction90Index = 0; cv$reduction90Index < noProducts; cv$reduction90Index += 1)
							reduceVar$sum$29 = (reduceVar$sum$29 + exped[i][cv$reduction90Index]);
						if((setFlag$ut && setFlag$beta)) {
							double reduceVar$sum$29$1 = reduceVar$sum$29;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var103, int forEnd$j$var103, int threadID$j$var103, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var103 = forStart$j$var103; j$var103 < forEnd$j$var103; j$var103 += 1)
											prob[i][j$var103] = (exped[i][j$var103] / reduceVar$sum$29$1);
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