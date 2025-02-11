package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Vulcano2012notNormalized$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Vulcano2012notNormalized$CoreInterface {
	private int[][] Avail;
	private int[][] ObsSales;
	private int[][] Sales;
	private int T;
	private int[] arrivals;
	private double[] exped;
	private boolean fixedFlag$sample25 = false;
	private boolean fixedFlag$sample43 = false;
	private boolean fixedFlag$sample51 = false;
	private boolean fixedFlag$sample98 = false;
	private boolean fixedProbFlag$sample25 = false;
	private boolean fixedProbFlag$sample43 = false;
	private boolean fixedProbFlag$sample51 = false;
	private boolean fixedProbFlag$sample98 = false;
	private boolean[] guard$sample25multinomial97$global;
	private boolean[][] guard$sample25put95$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$Sales;
	private double logProbability$arrivals;
	private double logProbability$exped;
	private double logProbability$lambda;
	private double[] logProbability$sample25;
	private double[] logProbability$sample51;
	private double[] logProbability$sample98;
	private double logProbability$ut;
	private double logProbability$var18;
	private double logProbability$var36;
	private double logProbability$var41;
	private double[] logProbability$var48;
	private double[] logProbability$var93;
	private double logProbability$weekly_rates;
	private double logProbability$weekly_sales;
	private double logProbability$weekly_ut;
	private int noProducts;
	private int s;
	private boolean setFlag$arrivals = false;
	private boolean setFlag$lambda = false;
	private boolean setFlag$ut = false;
	private boolean setFlag$weekly_sales = false;
	private boolean system$gibbsForward = true;
	private double[] ut;
	private double[][] weekly_rates;
	private int[][] weekly_sales;
	private double[][] weekly_ut;

	public Vulcano2012notNormalized$MultiThreadCPU(ExecutionTarget target) {
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
	public final int[] get$arrivals() {
		return arrivals;
	}

	@Override
	public final void set$arrivals(int[] cv$value) {
		arrivals = cv$value;
		setFlag$arrivals = true;
		fixedProbFlag$sample51 = false;
		fixedProbFlag$sample98 = false;
	}

	@Override
	public final double[] get$exped() {
		return exped;
	}

	@Override
	public final boolean get$fixedFlag$sample25() {
		return fixedFlag$sample25;
	}

	@Override
	public final void set$fixedFlag$sample25(boolean cv$value) {
		fixedFlag$sample25 = cv$value;
		fixedProbFlag$sample25 = (cv$value && fixedProbFlag$sample25);
		fixedProbFlag$sample98 = (cv$value && fixedProbFlag$sample98);
	}

	@Override
	public final boolean get$fixedFlag$sample43() {
		return fixedFlag$sample43;
	}

	@Override
	public final void set$fixedFlag$sample43(boolean cv$value) {
		fixedFlag$sample43 = cv$value;
		fixedProbFlag$sample43 = (cv$value && fixedProbFlag$sample43);
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
	}

	@Override
	public final boolean get$fixedFlag$sample51() {
		return fixedFlag$sample51;
	}

	@Override
	public final void set$fixedFlag$sample51(boolean cv$value) {
		fixedFlag$sample51 = cv$value;
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
		fixedProbFlag$sample98 = (cv$value && fixedProbFlag$sample98);
	}

	@Override
	public final boolean get$fixedFlag$sample98() {
		return fixedFlag$sample98;
	}

	@Override
	public final void set$fixedFlag$sample98(boolean cv$value) {
		fixedFlag$sample98 = cv$value;
		fixedProbFlag$sample98 = (cv$value && fixedProbFlag$sample98);
	}

	@Override
	public final double[] get$lambda() {
		return lambda;
	}

	@Override
	public final void set$lambda(double[] cv$value) {
		lambda = cv$value;
		setFlag$lambda = true;
		fixedProbFlag$sample43 = false;
		fixedProbFlag$sample51 = false;
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
	public final double get$logProbability$arrivals() {
		return logProbability$arrivals;
	}

	@Override
	public final double get$logProbability$exped() {
		return logProbability$exped;
	}

	@Override
	public final double get$logProbability$lambda() {
		return logProbability$lambda;
	}

	@Override
	public final double get$logProbability$ut() {
		return logProbability$ut;
	}

	@Override
	public final double get$logProbability$weekly_sales() {
		return logProbability$weekly_sales;
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
	public final int get$s() {
		return s;
	}

	@Override
	public final void set$s(int cv$value) {
		s = cv$value;
	}

	@Override
	public final double[] get$ut() {
		return ut;
	}

	@Override
	public final void set$ut(double[] cv$value) {
		ut = cv$value;
		setFlag$ut = true;
		fixedProbFlag$sample25 = false;
		fixedProbFlag$sample98 = false;
	}

	@Override
	public final int[][] get$weekly_sales() {
		return weekly_sales;
	}

	@Override
	public final void set$weekly_sales(int[][] cv$value) {
		weekly_sales = cv$value;
		setFlag$weekly_sales = true;
	}

	private final void logProbabilityValue$sample25() {
		if(!fixedProbFlag$sample25) {
			double cv$sampleAccumulator = 0.0;
			for(int var22 = 0; var22 < noProducts; var22 += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((ut[var22] / 3.1622776601683795)) - 1.151292546497023);
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				logProbability$sample25[var22] = cv$distributionAccumulator;
				logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
				if((0 < T)) {
					logProbability$weekly_ut = (logProbability$weekly_ut + cv$distributionAccumulator);
					logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
				}
			}
			logProbability$var18 = cv$sampleAccumulator;
			logProbability$ut = (logProbability$ut + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample25)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample25 = fixedFlag$sample25;
		} else {
			double cv$rvAccumulator = 0.0;
			for(int var22 = 0; var22 < noProducts; var22 += 1) {
				double cv$sampleValue = logProbability$sample25[var22];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				logProbability$exped = (logProbability$exped + cv$sampleValue);
				if((0 < T)) {
					logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleValue);
					logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
				}
			}
			logProbability$var18 = cv$rvAccumulator;
			logProbability$ut = (logProbability$ut + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			if(fixedFlag$sample25)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample43() {
		if(!fixedProbFlag$sample43) {
			double cv$sampleAccumulator = 0.0;
			for(int var40 = 0; var40 < T; var40 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGamma(lambda[var40], 10.0, 10.0));
			logProbability$var36 = cv$sampleAccumulator;
			logProbability$var41 = cv$sampleAccumulator;
			logProbability$lambda = (logProbability$lambda + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample43)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample43 = fixedFlag$sample43;
		} else {
			logProbability$var36 = logProbability$var41;
			logProbability$lambda = (logProbability$lambda + logProbability$var41);
			logProbability$$model = (logProbability$$model + logProbability$var41);
			if(fixedFlag$sample43)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var41);
		}
	}

	private final void logProbabilityValue$sample51() {
		if(!fixedProbFlag$sample51) {
			double cv$accumulator = 0.0;
			for(int t$var46 = 0; t$var46 < T; t$var46 += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityPoisson(arrivals[t$var46], lambda[t$var46]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var48[t$var46] = cv$distributionAccumulator;
				logProbability$sample51[t$var46] = cv$distributionAccumulator;
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample51 = (fixedFlag$sample51 && fixedFlag$sample43);
		} else {
			double cv$accumulator = 0.0;
			for(int t$var46 = 0; t$var46 < T; t$var46 += 1) {
				double cv$rvAccumulator = logProbability$sample51[t$var46];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var48[t$var46] = cv$rvAccumulator;
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample98() {
		if(!fixedProbFlag$sample98) {
			double cv$accumulator = 0.0;
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var59], weekly_rates[t$var59], arrivals[t$var59]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var93[t$var59] = cv$distributionAccumulator;
				logProbability$sample98[t$var59] = cv$distributionAccumulator;
				if((0 < noProducts))
					logProbability$Sales = (logProbability$Sales + cv$distributionAccumulator);
			}
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample98 = ((fixedFlag$sample98 && fixedFlag$sample25) && fixedFlag$sample51);
		} else {
			double cv$accumulator = 0.0;
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
				double cv$sampleValue = logProbability$sample98[t$var59];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var93[t$var59] = cv$sampleValue;
				if((0 < noProducts))
					logProbability$Sales = (logProbability$Sales + cv$sampleValue);
			}
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample25(int var22) {
		double cv$originalValue = ut[var22];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
				guard$sample25multinomial97$global[t$var59] = false;
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
				if(((0 < weekly_ut[t$var59].length) && !guard$sample25multinomial97$global[t$var59])) {
					guard$sample25multinomial97$global[t$var59] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var59], weekly_rates[t$var59], arrivals[t$var59]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
				if(!guard$sample25multinomial97$global[t$var59])
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var59], weekly_rates[t$var59], arrivals[t$var59]) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		ut[var22] = cv$proposedValue;
		exped[var22] = Math.exp(ut[var22]);
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
			weekly_ut[t$var59][var22] = (exped[var22] * Avail[t$var59][var22]);
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
			for(int j$var88 = 0; j$var88 <= noProducts; j$var88 += 1)
				guard$sample25put95$global[t$var59][j$var88] = false;
		}
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
			guard$sample25put95$global[t$var59][var22] = false;
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
			for(int j$var88 = 0; j$var88 <= noProducts; j$var88 += 1) {
				if(!guard$sample25put95$global[t$var59][j$var88]) {
					guard$sample25put95$global[t$var59][j$var88] = true;
					double reduceVar$denom$10 = 0.0;
					for(int cv$reduction907Index = 0; cv$reduction907Index <= noProducts; cv$reduction907Index += 1)
						reduceVar$denom$10 = (reduceVar$denom$10 + weekly_ut[t$var59][cv$reduction907Index]);
					weekly_rates[t$var59][j$var88] = (weekly_ut[t$var59][j$var88] / reduceVar$denom$10);
				}
			}
		}
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
			if(!guard$sample25put95$global[t$var59][var22]) {
				guard$sample25put95$global[t$var59][var22] = true;
				double reduceVar$denom$11 = 0.0;
				for(int cv$reduction83Index = 0; cv$reduction83Index <= noProducts; cv$reduction83Index += 1)
					reduceVar$denom$11 = (reduceVar$denom$11 + weekly_ut[t$var59][cv$reduction83Index]);
				weekly_rates[t$var59][var22] = (weekly_ut[t$var59][var22] / reduceVar$denom$11);
			}
		}
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
			guard$sample25multinomial97$global[t$var59] = false;
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
			if(((0 < weekly_ut[t$var59].length) && !guard$sample25multinomial97$global[t$var59])) {
				guard$sample25multinomial97$global[t$var59] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var59], weekly_rates[t$var59], arrivals[t$var59]) + cv$accumulatedProbabilities);
			}
		}
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
			if(!guard$sample25multinomial97$global[t$var59]) {
				guard$sample25multinomial97$global[t$var59] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var59], weekly_rates[t$var59], arrivals[t$var59]) + cv$accumulatedProbabilities);
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			ut[var22] = cv$originalValue;
			exped[var22] = Math.exp(ut[var22]);
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
				weekly_ut[t$var59][var22] = (exped[var22] * Avail[t$var59][var22]);
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
				for(int j$var88 = 0; j$var88 <= noProducts; j$var88 += 1)
					guard$sample25put95$global[t$var59][j$var88] = false;
			}
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
				guard$sample25put95$global[t$var59][var22] = false;
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
				for(int j$var88 = 0; j$var88 <= noProducts; j$var88 += 1) {
					if(!guard$sample25put95$global[t$var59][j$var88]) {
						guard$sample25put95$global[t$var59][j$var88] = true;
						double reduceVar$denom$13 = 0.0;
						for(int cv$reduction1112Index = 0; cv$reduction1112Index <= noProducts; cv$reduction1112Index += 1)
							reduceVar$denom$13 = (reduceVar$denom$13 + weekly_ut[t$var59][cv$reduction1112Index]);
						weekly_rates[t$var59][j$var88] = (weekly_ut[t$var59][j$var88] / reduceVar$denom$13);
					}
				}
			}
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
				if(!guard$sample25put95$global[t$var59][var22]) {
					guard$sample25put95$global[t$var59][var22] = true;
					double reduceVar$denom$14 = 0.0;
					for(int cv$reduction83Index = 0; cv$reduction83Index <= noProducts; cv$reduction83Index += 1)
						reduceVar$denom$14 = (reduceVar$denom$14 + weekly_ut[t$var59][cv$reduction83Index]);
					weekly_rates[t$var59][var22] = (weekly_ut[t$var59][var22] / reduceVar$denom$14);
				}
			}
		}
	}

	private final void sample43(int var40, int threadID$cv$var40, Rng RNG$) {
		lambda[var40] = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, arrivals[var40], 1);
	}

	private final void sample51(int t$var46, int threadID$cv$t$var46, Rng RNG$) {
		int cv$originalValue = arrivals[t$var46];
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 1.0))
			cv$var = 1.0;
		double cv$offset = (Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$));
		cv$offset = ((cv$offset <= 0.0)?(cv$offset - 1):(cv$offset + 1));
		int cv$proposedValue = (cv$originalValue + (int)cv$offset);
		double cv$originalProbability = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var46], weekly_rates[t$var46], cv$originalValue) + DistributionSampling.logProbabilityPoisson(cv$originalValue, lambda[t$var46]));
		arrivals[t$var46] = cv$proposedValue;
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var46], weekly_rates[t$var46], cv$proposedValue) + DistributionSampling.logProbabilityPoisson(cv$proposedValue, lambda[t$var46]));
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			arrivals[t$var46] = cv$originalValue;
	}

	@Override
	public final void allocateScratch() {
		int cv$max_j$var88 = 0;
		if((0 < T))
			cv$max_j$var88 = Math.max(0, (noProducts + 1));
		guard$sample25put95$global = new boolean[Math.max(0, T)][cv$max_j$var88];
		guard$sample25multinomial97$global = new boolean[Math.max(0, T)];
	}

	@Override
	public final void allocator() {
		if(!setFlag$ut)
			ut = new double[noProducts];
		exped = new double[noProducts];
		if(!setFlag$lambda)
			lambda = new double[T];
		if(!setFlag$arrivals)
			arrivals = new int[T];
		Sales = new int[T][];
		for(int var54 = 0; var54 < T; var54 += 1)
			Sales[var54] = new int[noProducts];
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
			Sales[t$var59] = new int[noProducts];
		weekly_rates = new double[T][];
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
			weekly_rates[t$var59] = new double[(noProducts + 1)];
		weekly_ut = new double[T][];
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
			weekly_ut[t$var59] = new double[(noProducts + 1)];
		if(!setFlag$weekly_sales) {
			weekly_sales = new int[T][];
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
				weekly_sales[t$var59] = new int[(noProducts + 1)];
		}
		logProbability$sample25 = new double[noProducts];
		logProbability$var48 = new double[T];
		logProbability$sample51 = new double[T];
		logProbability$var93 = new double[T];
		logProbability$sample98 = new double[T];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample25) {
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1)
							ut[var22] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1)
							exped[j$var28] = Math.exp(ut[j$var28]);
				}
			);
		}
		if(!fixedFlag$sample43)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1)
							lambda[var40] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		if(!fixedFlag$sample51)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1)
							arrivals[t$var46] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var46]);
				}
			);

		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var59, int forEnd$index$t$var59, int threadID$index$t$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var59 = forStart$index$t$var59; index$t$var59 < forEnd$index$t$var59; index$t$var59 += 1) {
						int t$var59 = index$t$var59;
						if(!fixedFlag$sample25)
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1)
											weekly_ut[t$var59][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
								}
							);

						weekly_ut[t$var59][noProducts] = 1.0;
						if(!fixedFlag$sample25) {
							double reduceVar$denom$15 = 0.0;
							for(int cv$reduction83Index = 0; cv$reduction83Index <= noProducts; cv$reduction83Index += 1)
								reduceVar$denom$15 = (reduceVar$denom$15 + weekly_ut[t$var59][cv$reduction83Index]);
							double reduceVar$denom$15$1 = reduceVar$denom$15;
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1)
											weekly_rates[t$var59][j$var88] = (weekly_ut[t$var59][j$var88] / reduceVar$denom$15$1);
								}
							);
						}
						if(!fixedFlag$sample98) {
							DistributionSampling.sampleMultinomial(RNG$1, weekly_rates[t$var59], arrivals[t$var59], weekly_sales[t$var59]);
							int[] observed_weekly_sales = Sales[t$var59];
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var98, int forEnd$j$var98, int threadID$j$var98, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var98 = forStart$j$var98; j$var98 < forEnd$j$var98; j$var98 += 1)
											observed_weekly_sales[j$var98] = weekly_sales[t$var59][j$var98];
								}
							);
						}
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample25) {
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1)
							ut[var22] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1)
							exped[j$var28] = Math.exp(ut[j$var28]);
				}
			);
		}
		if(!fixedFlag$sample43)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1)
							lambda[var40] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		if(!fixedFlag$sample51)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1)
							arrivals[t$var46] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var46]);
				}
			);

		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var59, int forEnd$index$t$var59, int threadID$index$t$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var59 = forStart$index$t$var59; index$t$var59 < forEnd$index$t$var59; index$t$var59 += 1) {
						int t$var59 = index$t$var59;
						if(!fixedFlag$sample25)
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1)
											weekly_ut[t$var59][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
								}
							);

						weekly_ut[t$var59][noProducts] = 1.0;
						if(!fixedFlag$sample25) {
							double reduceVar$denom$17 = 0.0;
							for(int cv$reduction83Index = 0; cv$reduction83Index <= noProducts; cv$reduction83Index += 1)
								reduceVar$denom$17 = (reduceVar$denom$17 + weekly_ut[t$var59][cv$reduction83Index]);
							double reduceVar$denom$17$1 = reduceVar$denom$17;
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1)
											weekly_rates[t$var59][j$var88] = (weekly_ut[t$var59][j$var88] / reduceVar$denom$17$1);
								}
							);
						}
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample25) {
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1)
							ut[var22] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1)
							exped[j$var28] = Math.exp(ut[j$var28]);
				}
			);
		}
		if(!fixedFlag$sample43)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1)
							lambda[var40] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		if(!fixedFlag$sample51)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1)
							arrivals[t$var46] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var46]);
				}
			);

		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var59, int forEnd$index$t$var59, int threadID$index$t$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var59 = forStart$index$t$var59; index$t$var59 < forEnd$index$t$var59; index$t$var59 += 1) {
						int t$var59 = index$t$var59;
						if(!fixedFlag$sample25)
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1)
											weekly_ut[t$var59][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
								}
							);

						weekly_ut[t$var59][noProducts] = 1.0;
						if(!fixedFlag$sample25) {
							double reduceVar$denom$16 = 0.0;
							for(int cv$reduction83Index = 0; cv$reduction83Index <= noProducts; cv$reduction83Index += 1)
								reduceVar$denom$16 = (reduceVar$denom$16 + weekly_ut[t$var59][cv$reduction83Index]);
							double reduceVar$denom$16$1 = reduceVar$denom$16;
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1)
											weekly_rates[t$var59][j$var88] = (weekly_ut[t$var59][j$var88] / reduceVar$denom$16$1);
								}
							);
						}
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample25) {
				for(int var22 = 0; var22 < noProducts; var22 += 1)
					sample25(var22);
			}
			if(!fixedFlag$sample43)
				parallelFor(RNG$, 0, T, 1,
					(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1)
								sample43(var40, threadID$var40, RNG$1);
					}
				);

			if(!fixedFlag$sample51)
				parallelFor(RNG$, 0, T, 1,
					(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1)
								sample51(t$var46, threadID$t$var46, RNG$1);
					}
				);

		} else {
			if(!fixedFlag$sample51)
				parallelFor(RNG$, 0, T, 1,
					(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1)
								sample51(t$var46, threadID$t$var46, RNG$1);
					}
				);

			if(!fixedFlag$sample43)
				parallelFor(RNG$, 0, T, 1,
					(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1)
								sample43(var40, threadID$var40, RNG$1);
					}
				);

			if(!fixedFlag$sample25) {
				for(int var22 = (noProducts - 1); var22 >= 0; var22 -= 1)
					sample25(var22);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var18 = 0.0;
		logProbability$ut = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$weekly_rates = 0.0;
		logProbability$exped = 0.0;
		if(!fixedProbFlag$sample25) {
			for(int var22 = 0; var22 < noProducts; var22 += 1)
				logProbability$sample25[var22] = 0.0;
		}
		logProbability$var36 = 0.0;
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample43)
			logProbability$var41 = 0.0;
		for(int t$var46 = 0; t$var46 < T; t$var46 += 1)
			logProbability$var48[t$var46] = 0.0;
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample51) {
			for(int t$var46 = 0; t$var46 < T; t$var46 += 1)
				logProbability$sample51[t$var46] = 0.0;
		}
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
			logProbability$var93[t$var59] = 0.0;
		logProbability$Sales = 0.0;
		logProbability$weekly_sales = 0.0;
		if(!fixedProbFlag$sample98) {
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
				logProbability$sample98[t$var59] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample25)
			logProbabilityValue$sample25();
		if(fixedFlag$sample43)
			logProbabilityValue$sample43();
		if(fixedFlag$sample51)
			logProbabilityValue$sample51();
		logProbabilityValue$sample98();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample25();
		logProbabilityValue$sample43();
		logProbabilityValue$sample51();
		logProbabilityValue$sample98();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample25();
		logProbabilityValue$sample43();
		logProbabilityValue$sample51();
		logProbabilityValue$sample98();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample25) {
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1)
							ut[var22] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1)
							exped[j$var28] = Math.exp(ut[j$var28]);
				}
			);
		}
		if(!fixedFlag$sample43)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1)
							lambda[var40] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		if(!fixedFlag$sample51)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1)
							arrivals[t$var46] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var46]);
				}
			);

		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var59, int forEnd$index$t$var59, int threadID$index$t$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var59 = forStart$index$t$var59; index$t$var59 < forEnd$index$t$var59; index$t$var59 += 1) {
						int t$var59 = index$t$var59;
						if(!fixedFlag$sample25)
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1)
											weekly_ut[t$var59][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
								}
							);

						weekly_ut[t$var59][noProducts] = 1.0;
						if(!fixedFlag$sample25) {
							double reduceVar$denom$18 = 0.0;
							for(int cv$reduction83Index = 0; cv$reduction83Index <= noProducts; cv$reduction83Index += 1)
								reduceVar$denom$18 = (reduceVar$denom$18 + weekly_ut[t$var59][cv$reduction83Index]);
							double reduceVar$denom$18$1 = reduceVar$denom$18;
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1)
											weekly_rates[t$var59][j$var88] = (weekly_ut[t$var59][j$var88] / reduceVar$denom$18$1);
								}
							);
						}
					}
			}
		);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = Sales.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = ObsSales[cv$index1];
			int[] cv$target2 = Sales[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
		for(int t$var59 = (T - 1); t$var59 >= 0; t$var59 -= 1) {
			int[] observed_weekly_sales = Sales[t$var59];
			for(int j$var98 = (noProducts - 1); j$var98 >= 0; j$var98 -= 1)
				weekly_sales[t$var59][j$var98] = observed_weekly_sales[j$var98];
		}
	}

	@Override
	public final void setIntermediates() {
		if(setFlag$ut)
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1)
							exped[j$var28] = Math.exp(ut[j$var28]);
				}
			);

		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var59, int forEnd$index$t$var59, int threadID$index$t$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var59 = forStart$index$t$var59; index$t$var59 < forEnd$index$t$var59; index$t$var59 += 1) {
						int t$var59 = index$t$var59;
						if(setFlag$ut)
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1)
											weekly_ut[t$var59][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
								}
							);

						double reduceVar$denom$19 = 0.0;
						for(int cv$reduction83Index = 0; cv$reduction83Index <= noProducts; cv$reduction83Index += 1)
							reduceVar$denom$19 = (reduceVar$denom$19 + weekly_ut[t$var59][cv$reduction83Index]);
						if(setFlag$ut) {
							double reduceVar$denom$19$1 = reduceVar$denom$19;
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1)
											weekly_rates[t$var59][j$var88] = (weekly_ut[t$var59][j$var88] / reduceVar$denom$19$1);
								}
							);
						}
						if(setFlag$weekly_sales) {
							int[] observed_weekly_sales = Sales[t$var59];
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var98, int forEnd$j$var98, int threadID$j$var98, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var98 = forStart$j$var98; j$var98 < forEnd$j$var98; j$var98 += 1)
											observed_weekly_sales[j$var98] = weekly_sales[t$var59][j$var98];
								}
							);
						}
					}
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n/*\n * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n */\npackage org.sandwood.compiler.tests.parser;\n\nmodel Vulcano2012notNormalized(int noProducts, int T, int s, int[][] ObsSales, int[][] Avail) {\n    // Avail is the availability matrix, T-by-noProducts\n\n    // draw utilities\n    double[] ut = gaussian(0, 10).sample(noProducts);\n\n    //exponentiate right here (in the non-basic models move to the for loop)\n    double[] exped = new double[noProducts];\n    for(int j : [0..noProducts)) {\n    exped[j] = exp(ut[j]);\n    }\n\n    // priors for the distribution of lambdas (for arrivals). They can be supplied as a vector if RGBU has some estimates, or just use some ad hoc priors\n    double[ ] lambda = gamma(10,10).sample(T);\n\n    // draw arrivals\n    int[] arrivals = new int[T];\n    for (int t : [0..T)){\n    arrivals[t]= poisson(lambda[t]).sample();\n    }\n\n    int[][] Sales = new int[T][noProducts];\n\n    for (int t:[0..T)){\n        // for each period t calculate choice probabilities and sales\n\n        double[] weekly_rates = new double[noProducts+1];\n        double[] weekly_ut = new double[noProducts+1];\n\n        for (int j : [0..noProducts)) {\n            weekly_ut[j] = exped[j]*Avail[t][j] ;\n        }\n        // add outside option value (which is always available)\n        weekly_ut[noProducts] = 1.0;\n        double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n\n        for (int j : [0..noProducts]) {\n            weekly_rates[j] = weekly_ut[j]/denom ;\n        }\n\n        int[] weekly_sales = multinomial(weekly_rates, arrivals[t]).sample();\n\n        //getting rid of the no purchase observation (last one in the vector of weekly_sales)\n        int[] observed_weekly_sales = new int[noProducts];\n        for (int j : [0..noProducts)) {\n            observed_weekly_sales[j] = weekly_sales[j] ;\n        }\n\n        // record sales for period t\n        Sales[t] = observed_weekly_sales;\n\n    }\n    // assert that generated sales match observed sales\n    Sales.observe(ObsSales);\n}";
	}
}