package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Vulcano2012basic$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Vulcano2012basic$CoreInterface {
	private int[][] Avail;
	private int[][] ObsSales;
	private int[][] Sales;
	private int T;
	private double[] exped;
	private double[] expedNorm;
	private boolean fixedFlag$sample26 = false;
	private boolean fixedProbFlag$sample157 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean[] guard$sample26multinomial156$global;
	private boolean[][] guard$sample26put131$global;
	private boolean[][] guard$sample26put154$global;
	private boolean[] guard$sample26put68$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$Sales;
	private double logProbability$exped;
	private double logProbability$expedNorm;
	private double[] logProbability$sample157;
	private double[] logProbability$sample26;
	private double logProbability$sum;
	private double logProbability$ut;
	private double[] logProbability$var152;
	private double[] logProbability$var25;
	private double logProbability$weekly_rates;
	private double logProbability$weekly_ut;
	private int noProducts;
	private double r;
	private int[] sales_sum;
	private double sum;
	private boolean system$gibbsForward = true;
	private double[] ut;
	private double[][] weekly_rates;
	private double[][] weekly_ut;

	public Vulcano2012basic$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final int[][] get$Avail() {
		return Avail;
	}

	@Override
	public final void set$Avail(int[][] cv$value) {
		Avail = cv$value;
	}

	@Override
	public final int[][] get$ObsSales() {
		return ObsSales;
	}

	@Override
	public final void set$ObsSales(int[][] cv$value) {
		ObsSales = cv$value;
	}

	@Override
	public final int[][] get$Sales() {
		return Sales;
	}

	@Override
	public final int get$T() {
		return T;
	}

	@Override
	public final void set$T(int cv$value) {
		T = cv$value;
	}

	@Override
	public final double[] get$exped() {
		return exped;
	}

	@Override
	public final double[] get$expedNorm() {
		return expedNorm;
	}

	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	@Override
	public final void set$fixedFlag$sample26(boolean cv$value) {
		fixedFlag$sample26 = cv$value;
		fixedProbFlag$sample26 = (cv$value && fixedProbFlag$sample26);
		fixedProbFlag$sample157 = (cv$value && fixedProbFlag$sample157);
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
	public final double get$logProbability$Sales() {
		return logProbability$Sales;
	}

	@Override
	public final double get$logProbability$exped() {
		return logProbability$exped;
	}

	@Override
	public final double get$logProbability$expedNorm() {
		return logProbability$expedNorm;
	}

	@Override
	public final double get$logProbability$sum() {
		return logProbability$sum;
	}

	@Override
	public final double get$logProbability$ut() {
		return logProbability$ut;
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
	public final double get$r() {
		return r;
	}

	@Override
	public final void set$r(double cv$value) {
		r = cv$value;
	}

	@Override
	public final int[] get$sales_sum() {
		return sales_sum;
	}

	@Override
	public final double get$sum() {
		return sum;
	}

	@Override
	public final double[] get$ut() {
		return ut;
	}

	@Override
	public final void set$ut(double[] cv$value) {
		ut = cv$value;
		fixedProbFlag$sample26 = false;
		fixedProbFlag$sample157 = false;
	}

	private final void logProbabilityValue$sample157() {
		if(!fixedProbFlag$sample157) {
			double cv$accumulator = 0.0;
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[t$var112], noProducts, sales_sum[t$var112]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var152[t$var112] = cv$distributionAccumulator;
				logProbability$sample157[t$var112] = cv$distributionAccumulator;
			}
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample157 = fixedFlag$sample26;
		} else {
			double cv$accumulator = 0.0;
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				double cv$rvAccumulator = logProbability$sample157[t$var112];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var152[t$var112] = cv$rvAccumulator;
			}
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample26() {
		if(!fixedProbFlag$sample26) {
			double cv$accumulator = 0.0;
			for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((ut[j$var20] / 1.4142135623730951)) - 0.34657359027997264);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var25[(j$var20 - 1)] = cv$distributionAccumulator;
				logProbability$sample26[(j$var20 - 1)] = cv$distributionAccumulator;
				logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
				logProbability$sum = (logProbability$sum + cv$distributionAccumulator);
				logProbability$expedNorm = (logProbability$expedNorm + cv$distributionAccumulator);
				if((0 < T)) {
					logProbability$weekly_ut = (logProbability$weekly_ut + cv$distributionAccumulator);
					logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
				}
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample26 = fixedFlag$sample26;
		} else {
			double cv$accumulator = 0.0;
			for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1) {
				double cv$sampleValue = logProbability$sample26[(j$var20 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var25[(j$var20 - 1)] = cv$sampleValue;
				logProbability$exped = (logProbability$exped + cv$sampleValue);
				logProbability$sum = (logProbability$sum + cv$sampleValue);
				logProbability$expedNorm = (logProbability$expedNorm + cv$sampleValue);
				if((0 < T)) {
					logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleValue);
					logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
				}
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample26(int j$var20) {
		double cv$originalValue = ut[j$var20];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 1.4142135623730951)) - 0.34657359027997264);
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
				guard$sample26multinomial156$global[t$var112] = false;
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				if(!guard$sample26multinomial156$global[t$var112]) {
					guard$sample26multinomial156$global[t$var112] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[t$var112], noProducts, sales_sum[t$var112]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				if(!guard$sample26multinomial156$global[t$var112]) {
					guard$sample26multinomial156$global[t$var112] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[t$var112], noProducts, sales_sum[t$var112]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				if(!guard$sample26multinomial156$global[t$var112]) {
					guard$sample26multinomial156$global[t$var112] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[t$var112], noProducts, sales_sum[t$var112]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				if(!guard$sample26multinomial156$global[t$var112])
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[t$var112], noProducts, sales_sum[t$var112]) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		ut[j$var20] = cv$proposedValue;
		exped[j$var20] = Math.exp(ut[j$var20]);
		double reduceVar$sum$9 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1)
			reduceVar$sum$9 = (reduceVar$sum$9 + exped[cv$reduction46Index]);
		sum = reduceVar$sum$9;
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1)
			guard$sample26put68$global[j$var63] = false;
		guard$sample26put68$global[j$var20] = false;
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
			if(!guard$sample26put68$global[j$var63]) {
				guard$sample26put68$global[j$var63] = true;
				expedNorm[j$var63] = (exped[j$var63] / (r * reduceVar$sum$9));
			}
		}
		if(!guard$sample26put68$global[j$var20]) {
			guard$sample26put68$global[j$var20] = true;
			expedNorm[j$var20] = (exped[j$var20] / (r * reduceVar$sum$9));
		}
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
				guard$sample26put131$global[t$var112][j$var63] = false;
		}
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
			guard$sample26put131$global[t$var112][j$var20] = false;
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				if(!guard$sample26put131$global[t$var112][j$var63]) {
					guard$sample26put131$global[t$var112][j$var63] = true;
					weekly_ut[t$var112][j$var63] = (expedNorm[j$var63] * Avail[t$var112][j$var63]);
				}
			}
		}
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
			if(!guard$sample26put131$global[t$var112][j$var20]) {
				guard$sample26put131$global[t$var112][j$var20] = true;
				weekly_ut[t$var112][j$var20] = (expedNorm[j$var20] * Avail[t$var112][j$var20]);
			}
		}
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
				guard$sample26put154$global[t$var112][j$var63] = false;
		}
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
			for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1)
				guard$sample26put154$global[t$var112][j$var147] = false;
		}
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
			guard$sample26put154$global[t$var112][j$var20] = false;
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
			for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
				if(!guard$sample26put154$global[t$var112][j$var147]) {
					guard$sample26put154$global[t$var112][j$var147] = true;
					double reduceVar$denom$15 = 0.0;
					for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
						reduceVar$denom$15 = (reduceVar$denom$15 + weekly_ut[t$var112][cv$reduction136Index]);
					weekly_rates[t$var112][j$var147] = (weekly_ut[t$var112][j$var147] / reduceVar$denom$15);
				}
			}
		}
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				if(!guard$sample26put154$global[t$var112][j$var63]) {
					guard$sample26put154$global[t$var112][j$var63] = true;
					double reduceVar$denom$16 = 0.0;
					for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
						reduceVar$denom$16 = (reduceVar$denom$16 + weekly_ut[t$var112][cv$reduction136Index]);
					weekly_rates[t$var112][j$var63] = (weekly_ut[t$var112][j$var63] / reduceVar$denom$16);
				}
			}
		}
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
			for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
				if(!guard$sample26put154$global[t$var112][j$var147]) {
					guard$sample26put154$global[t$var112][j$var147] = true;
					double reduceVar$denom$17 = 0.0;
					for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
						reduceVar$denom$17 = (reduceVar$denom$17 + weekly_ut[t$var112][cv$reduction136Index]);
					weekly_rates[t$var112][j$var147] = (weekly_ut[t$var112][j$var147] / reduceVar$denom$17);
				}
			}
		}
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
			if(!guard$sample26put154$global[t$var112][j$var20]) {
				guard$sample26put154$global[t$var112][j$var20] = true;
				double reduceVar$denom$18 = 0.0;
				for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
					reduceVar$denom$18 = (reduceVar$denom$18 + weekly_ut[t$var112][cv$reduction136Index]);
				weekly_rates[t$var112][j$var20] = (weekly_ut[t$var112][j$var20] / reduceVar$denom$18);
			}
		}
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 1.4142135623730951)) - 0.34657359027997264);
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
			guard$sample26multinomial156$global[t$var112] = false;
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
			if(!guard$sample26multinomial156$global[t$var112]) {
				guard$sample26multinomial156$global[t$var112] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[t$var112], noProducts, sales_sum[t$var112]) + cv$accumulatedProbabilities);
			}
		}
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
			if(!guard$sample26multinomial156$global[t$var112]) {
				guard$sample26multinomial156$global[t$var112] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[t$var112], noProducts, sales_sum[t$var112]) + cv$accumulatedProbabilities);
			}
		}
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
			if(!guard$sample26multinomial156$global[t$var112]) {
				guard$sample26multinomial156$global[t$var112] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[t$var112], noProducts, sales_sum[t$var112]) + cv$accumulatedProbabilities);
			}
		}
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
			if(!guard$sample26multinomial156$global[t$var112]) {
				guard$sample26multinomial156$global[t$var112] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[t$var112], noProducts, sales_sum[t$var112]) + cv$accumulatedProbabilities);
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			ut[j$var20] = cv$originalValue;
			exped[j$var20] = Math.exp(ut[j$var20]);
			double reduceVar$sum$12 = 0.0;
			for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1)
				reduceVar$sum$12 = (reduceVar$sum$12 + exped[cv$reduction46Index]);
			sum = reduceVar$sum$12;
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1)
				guard$sample26put68$global[j$var63] = false;
			guard$sample26put68$global[j$var20] = false;
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
				if(!guard$sample26put68$global[j$var63]) {
					guard$sample26put68$global[j$var63] = true;
					expedNorm[j$var63] = (exped[j$var63] / (r * reduceVar$sum$12));
				}
			}
			if(!guard$sample26put68$global[j$var20]) {
				guard$sample26put68$global[j$var20] = true;
				expedNorm[j$var20] = (exped[j$var20] / (r * reduceVar$sum$12));
			}
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
				for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
					guard$sample26put131$global[t$var112][j$var63] = false;
			}
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
				guard$sample26put131$global[t$var112][j$var20] = false;
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
				for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
					if(!guard$sample26put131$global[t$var112][j$var63]) {
						guard$sample26put131$global[t$var112][j$var63] = true;
						weekly_ut[t$var112][j$var63] = (expedNorm[j$var63] * Avail[t$var112][j$var63]);
					}
				}
			}
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				if(!guard$sample26put131$global[t$var112][j$var20]) {
					guard$sample26put131$global[t$var112][j$var20] = true;
					weekly_ut[t$var112][j$var20] = (expedNorm[j$var20] * Avail[t$var112][j$var20]);
				}
			}
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
				for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
					guard$sample26put154$global[t$var112][j$var63] = false;
			}
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1)
					guard$sample26put154$global[t$var112][j$var147] = false;
			}
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
				guard$sample26put154$global[t$var112][j$var20] = false;
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
					if(!guard$sample26put154$global[t$var112][j$var147]) {
						guard$sample26put154$global[t$var112][j$var147] = true;
						double reduceVar$denom$21 = 0.0;
						for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
							reduceVar$denom$21 = (reduceVar$denom$21 + weekly_ut[t$var112][cv$reduction136Index]);
						weekly_rates[t$var112][j$var147] = (weekly_ut[t$var112][j$var147] / reduceVar$denom$21);
					}
				}
			}
			for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
				for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
					if(!guard$sample26put154$global[t$var112][j$var63]) {
						guard$sample26put154$global[t$var112][j$var63] = true;
						double reduceVar$denom$22 = 0.0;
						for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
							reduceVar$denom$22 = (reduceVar$denom$22 + weekly_ut[t$var112][cv$reduction136Index]);
						weekly_rates[t$var112][j$var63] = (weekly_ut[t$var112][j$var63] / reduceVar$denom$22);
					}
				}
			}
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
					if(!guard$sample26put154$global[t$var112][j$var147]) {
						guard$sample26put154$global[t$var112][j$var147] = true;
						double reduceVar$denom$23 = 0.0;
						for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
							reduceVar$denom$23 = (reduceVar$denom$23 + weekly_ut[t$var112][cv$reduction136Index]);
						weekly_rates[t$var112][j$var147] = (weekly_ut[t$var112][j$var147] / reduceVar$denom$23);
					}
				}
			}
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				if(!guard$sample26put154$global[t$var112][j$var20]) {
					guard$sample26put154$global[t$var112][j$var20] = true;
					double reduceVar$denom$24 = 0.0;
					for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
						reduceVar$denom$24 = (reduceVar$denom$24 + weekly_ut[t$var112][cv$reduction136Index]);
					weekly_rates[t$var112][j$var20] = (weekly_ut[t$var112][j$var20] / reduceVar$denom$24);
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {
		guard$sample26put68$global = new boolean[Math.max(0, noProducts)];
		int cv$max_j$var123 = 0;
		if((0 < T))
			cv$max_j$var123 = Math.max(0, noProducts);
		guard$sample26put131$global = new boolean[Math.max(0, T)][cv$max_j$var123];
		int cv$max_j$var147 = 0;
		if((0 < T))
			cv$max_j$var147 = Math.max(0, noProducts);
		guard$sample26put154$global = new boolean[Math.max(0, T)][cv$max_j$var147];
		guard$sample26multinomial156$global = new boolean[Math.max(0, T)];
	}

	@Override
	public final void allocator() {
		if(!fixedFlag$sample26)
			ut = new double[noProducts];
		exped = new double[noProducts];
		expedNorm = new double[noProducts];
		sales_sum = new int[T];
		Sales = new int[T][];
		for(int var100 = 0; var100 < T; var100 += 1)
			Sales[var100] = new int[noProducts];
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
			Sales[t$var112] = new int[noProducts];
		weekly_rates = new double[T][];
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
			weekly_rates[t$var112] = new double[noProducts];
		weekly_ut = new double[T][];
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
			weekly_ut[t$var112] = new double[noProducts];
		logProbability$var25 = new double[(noProducts - 1)];
		logProbability$sample26 = new double[(noProducts - 1)];
		logProbability$var152 = new double[T];
		logProbability$sample157 = new double[T];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample26) {
			parallelFor(RNG$, 1, noProducts, 1,
				(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1)
							ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$1) * 1.4142135623730951);
				}
			);
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
							exped[j$var38] = Math.exp(ut[j$var38]);
				}
			);
			double reduceVar$sum$13 = 0.0;
			for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1)
				reduceVar$sum$13 = (reduceVar$sum$13 + exped[cv$reduction46Index]);
			sum = reduceVar$sum$13;
			double reduceVar$sum$13$1 = reduceVar$sum$13;
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
							expedNorm[j$var63] = (exped[j$var63] / (r * reduceVar$sum$13$1));
				}
			);
		}
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
						int t$var112 = index$t$var112;
						int threadID$t$var112 = threadID$index$t$var112;
						if(!fixedFlag$sample26) {
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1)
											weekly_ut[t$var112][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
								}
							);
							double reduceVar$denom$25 = 0.0;
							for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
								reduceVar$denom$25 = (reduceVar$denom$25 + weekly_ut[t$var112][cv$reduction136Index]);
							double reduceVar$denom$25$2 = reduceVar$denom$25;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1)
											weekly_rates[t$var112][j$var147] = (weekly_ut[t$var112][j$var147] / reduceVar$denom$25$2);
								}
							);
						}
						DistributionSampling.sampleMultinomial(RNG$1, weekly_rates[t$var112], noProducts, sales_sum[t$var112], Sales[t$var112]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample26) {
			parallelFor(RNG$, 1, noProducts, 1,
				(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1)
							ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$1) * 1.4142135623730951);
				}
			);
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
							exped[j$var38] = Math.exp(ut[j$var38]);
				}
			);
			double reduceVar$sum$15 = 0.0;
			for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1)
				reduceVar$sum$15 = (reduceVar$sum$15 + exped[cv$reduction46Index]);
			sum = reduceVar$sum$15;
			double reduceVar$sum$15$1 = reduceVar$sum$15;
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
							expedNorm[j$var63] = (exped[j$var63] / (r * reduceVar$sum$15$1));
				}
			);
			parallelFor(RNG$, 0, T, 1,
				(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
							int t$var112 = index$t$var112;
							int threadID$t$var112 = threadID$index$t$var112;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1)
											weekly_ut[t$var112][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
								}
							);
							double reduceVar$denom$27 = 0.0;
							for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
								reduceVar$denom$27 = (reduceVar$denom$27 + weekly_ut[t$var112][cv$reduction136Index]);
							double reduceVar$denom$27$2 = reduceVar$denom$27;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1)
											weekly_rates[t$var112][j$var147] = (weekly_ut[t$var112][j$var147] / reduceVar$denom$27$2);
								}
							);
						}
				}
			);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample26) {
			parallelFor(RNG$, 1, noProducts, 1,
				(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1)
							ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$1) * 1.4142135623730951);
				}
			);
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
							exped[j$var38] = Math.exp(ut[j$var38]);
				}
			);
			double reduceVar$sum$14 = 0.0;
			for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1)
				reduceVar$sum$14 = (reduceVar$sum$14 + exped[cv$reduction46Index]);
			sum = reduceVar$sum$14;
			double reduceVar$sum$14$1 = reduceVar$sum$14;
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
							expedNorm[j$var63] = (exped[j$var63] / (r * reduceVar$sum$14$1));
				}
			);
			parallelFor(RNG$, 0, T, 1,
				(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
							int t$var112 = index$t$var112;
							int threadID$t$var112 = threadID$index$t$var112;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1)
											weekly_ut[t$var112][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
								}
							);
							double reduceVar$denom$26 = 0.0;
							for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
								reduceVar$denom$26 = (reduceVar$denom$26 + weekly_ut[t$var112][cv$reduction136Index]);
							double reduceVar$denom$26$2 = reduceVar$denom$26;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1)
											weekly_rates[t$var112][j$var147] = (weekly_ut[t$var112][j$var147] / reduceVar$denom$26$2);
								}
							);
						}
				}
			);
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample26) {
			if(system$gibbsForward) {
				for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1)
					sample26(j$var20);
			} else {
				for(int j$var20 = (noProducts - 1); j$var20 >= 1; j$var20 -= 1)
					sample26(j$var20);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		ut[0] = 0.0;
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var78, int forEnd$t$var78, int threadID$t$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int t$var78 = forStart$t$var78; t$var78 < forEnd$t$var78; t$var78 += 1) {
						int reduceVar$var88$1 = 0;
						for(int cv$reduction84Index = 0; cv$reduction84Index < ObsSales[t$var78].length; cv$reduction84Index += 1)
							reduceVar$var88$1 = (reduceVar$var88$1 + ObsSales[t$var78][cv$reduction84Index]);
						sales_sum[t$var78] = reduceVar$var88$1;
					}
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1)
			logProbability$var25[(j$var20 - 1)] = 0.0;
		logProbability$ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$sum = 0.0;
		logProbability$expedNorm = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$weekly_rates = 0.0;
		if(!fixedProbFlag$sample26) {
			for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1)
				logProbability$sample26[(j$var20 - 1)] = 0.0;
		}
		for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
			logProbability$var152[t$var112] = 0.0;
		logProbability$Sales = 0.0;
		if(!fixedProbFlag$sample157) {
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
				logProbability$sample157[t$var112] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample26)
			logProbabilityValue$sample26();
		logProbabilityValue$sample157();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample157();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample157();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample26) {
			parallelFor(RNG$, 1, noProducts, 1,
				(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1)
							ut[j$var20] = (DistributionSampling.sampleGaussian(RNG$1) * 1.4142135623730951);
				}
			);
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
							exped[j$var38] = Math.exp(ut[j$var38]);
				}
			);
			double reduceVar$sum$16 = 0.0;
			for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1)
				reduceVar$sum$16 = (reduceVar$sum$16 + exped[cv$reduction46Index]);
			sum = reduceVar$sum$16;
			double reduceVar$sum$16$1 = reduceVar$sum$16;
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
							expedNorm[j$var63] = (exped[j$var63] / (r * reduceVar$sum$16$1));
				}
			);
			parallelFor(RNG$, 0, T, 1,
				(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
							int t$var112 = index$t$var112;
							int threadID$t$var112 = threadID$index$t$var112;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1)
											weekly_ut[t$var112][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
								}
							);
							double reduceVar$denom$28 = 0.0;
							for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
								reduceVar$denom$28 = (reduceVar$denom$28 + weekly_ut[t$var112][cv$reduction136Index]);
							double reduceVar$denom$28$2 = reduceVar$denom$28;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1)
											weekly_rates[t$var112][j$var147] = (weekly_ut[t$var112][j$var147] / reduceVar$denom$28$2);
								}
							);
						}
				}
			);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = Sales.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = ObsSales[cv$index1];
			int[] cv$target2 = Sales[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	@Override
	public final void setIntermediates() {
		if(fixedFlag$sample26) {
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
							exped[j$var38] = Math.exp(ut[j$var38]);
				}
			);
			double reduceVar$sum$17 = 0.0;
			for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1)
				reduceVar$sum$17 = (reduceVar$sum$17 + exped[cv$reduction46Index]);
			sum = reduceVar$sum$17;
			double reduceVar$sum$17$1 = reduceVar$sum$17;
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
							expedNorm[j$var63] = (exped[j$var63] / (r * reduceVar$sum$17$1));
				}
			);
			parallelFor(RNG$, 0, T, 1,
				(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
							int t$var112 = index$t$var112;
							int threadID$t$var112 = threadID$index$t$var112;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1)
											weekly_ut[t$var112][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
								}
							);
							double reduceVar$denom$29 = 0.0;
							for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1)
								reduceVar$denom$29 = (reduceVar$denom$29 + weekly_ut[t$var112][cv$reduction136Index]);
							double reduceVar$denom$29$2 = reduceVar$denom$29;
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1)
											weekly_rates[t$var112][j$var147] = (weekly_ut[t$var112][j$var147] / reduceVar$denom$29$2);
								}
							);
						}
				}
			);
		}
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + "/*\n"
		     + " * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n"
		     + " * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n"
		     + " * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model Vulcano2012basic(int noProducts, int T, int[][] ObsSales, int[][] Avail, double r) {\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = new double[noProducts];\n"
		     + "    ut[0] = 0.0;  //the first one is fixed so that we can normalize the sum to be equal 1/r\n"
		     + "    for(int j : [1..noProducts)) {\n"
		     + "        ut[j] = gaussian(0, 2).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    //exponentiate right here (in the non-basic models move to the for loop)\n"
		     + "    double[] exped = new double[noProducts];\n"
		     + "    for(int j : [0..noProducts)) {\n"
		     + "        exped[j] = exp(ut[j]);\n"
		     + "    }\n"
		     + "\n"
		     + "    double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n"
		     + "\n"
		     + "    //now normalize\n"
		     + "    double[] expedNorm = new double[noProducts];\n"
		     + "    for(int j : [0..noProducts)) {\n"
		     + "        expedNorm[j] = exped[j]/(r*sum);\n"
		     + "    }\n"
		     + "\n"
		     + "    int[] sales_sum = new int[T];\n"
		     + "    for (int t : [0..T)){\n"
		     + "        int[] week_sales = ObsSales[t];\n"
		     + "        sales_sum[t] = reduce(week_sales, 0, (k, l) -> { return k + l; });\n"
		     + "    }\n"
		     + "\n"
		     + "    int[][] Sales = new int[T][noProducts];\n"
		     + "\n"
		     + "    for (int t:[0..T)){\n"
		     + "        // for each period t calculate choice probabilities and sales\n"
		     + "\n"
		     + "        double[] weekly_rates = new double[noProducts];\n"
		     + "        double[] weekly_ut = new double[noProducts];\n"
		     + "\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            weekly_ut[j] = expedNorm[j]*Avail[t][j] ;\n"
		     + "        }\n"
		     + "        double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n"
		     + "\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            weekly_rates[j] = weekly_ut[j]/denom ;\n"
		     + "        }\n"
		     + "\n"
		     + "        int[] weekly_sales = multinomial(weekly_rates, sales_sum[t]).sample();\n"
		     + "\n"
		     + "        // record sales for period t\n"
		     + "        Sales[t] = weekly_sales;\n"
		     + "\n"
		     + "                                }\n"
		     + "    // assert that generated sales match observed sales\n"
		     + "    Sales.observe(ObsSales);\n"
		     + "\n"
		     + "}";
	}
}