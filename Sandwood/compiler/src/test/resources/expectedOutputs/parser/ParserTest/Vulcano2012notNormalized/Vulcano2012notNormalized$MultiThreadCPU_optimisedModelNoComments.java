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
	private boolean fixedFlag$sample141 = false;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedFlag$sample64 = false;
	private boolean fixedFlag$sample79 = false;
	private boolean fixedProbFlag$sample141 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean fixedProbFlag$sample64 = false;
	private boolean fixedProbFlag$sample79 = false;
	private boolean[] guard$sample32multinomial140$global;
	private boolean[][] guard$sample32put138$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$Sales;
	private double logProbability$arrivals;
	private double logProbability$exped;
	private double logProbability$lambda;
	private double[] logProbability$sample141;
	private double[] logProbability$sample32;
	private double[] logProbability$sample79;
	private double logProbability$ut;
	private double[] logProbability$var137;
	private double logProbability$var18;
	private double logProbability$var50;
	private double logProbability$var62;
	private double[] logProbability$var76;
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
		fixedProbFlag$sample79 = false;
		fixedProbFlag$sample141 = false;
	}

	@Override
	public final double[] get$exped() {
		return exped;
	}

	@Override
	public final boolean get$fixedFlag$sample141() {
		return fixedFlag$sample141;
	}

	@Override
	public final void set$fixedFlag$sample141(boolean cv$value) {
		fixedFlag$sample141 = cv$value;
		fixedProbFlag$sample141 = (cv$value && fixedProbFlag$sample141);
	}

	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	@Override
	public final void set$fixedFlag$sample32(boolean cv$value) {
		fixedFlag$sample32 = cv$value;
		fixedProbFlag$sample32 = (cv$value && fixedProbFlag$sample32);
		fixedProbFlag$sample141 = (cv$value && fixedProbFlag$sample141);
	}

	@Override
	public final boolean get$fixedFlag$sample64() {
		return fixedFlag$sample64;
	}

	@Override
	public final void set$fixedFlag$sample64(boolean cv$value) {
		fixedFlag$sample64 = cv$value;
		fixedProbFlag$sample64 = (cv$value && fixedProbFlag$sample64);
		fixedProbFlag$sample79 = (cv$value && fixedProbFlag$sample79);
	}

	@Override
	public final boolean get$fixedFlag$sample79() {
		return fixedFlag$sample79;
	}

	@Override
	public final void set$fixedFlag$sample79(boolean cv$value) {
		fixedFlag$sample79 = cv$value;
		fixedProbFlag$sample79 = (cv$value && fixedProbFlag$sample79);
		fixedProbFlag$sample141 = (cv$value && fixedProbFlag$sample141);
	}

	@Override
	public final double[] get$lambda() {
		return lambda;
	}

	@Override
	public final void set$lambda(double[] cv$value) {
		lambda = cv$value;
		setFlag$lambda = true;
		fixedProbFlag$sample64 = false;
		fixedProbFlag$sample79 = false;
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
		fixedProbFlag$sample32 = false;
		fixedProbFlag$sample141 = false;
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

	private final void logProbabilityValue$sample141() {
		if(!fixedProbFlag$sample141) {
			double cv$accumulator = 0.0;
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var89], weekly_rates[t$var89], arrivals[t$var89]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var137[t$var89] = cv$distributionAccumulator;
				logProbability$sample141[t$var89] = cv$distributionAccumulator;
				if((0 < noProducts))
					logProbability$Sales = (logProbability$Sales + cv$distributionAccumulator);
			}
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample141 = ((fixedFlag$sample141 && fixedFlag$sample32) && fixedFlag$sample79);
		} else {
			double cv$accumulator = 0.0;
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
				double cv$sampleValue = logProbability$sample141[t$var89];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var137[t$var89] = cv$sampleValue;
				if((0 < noProducts))
					logProbability$Sales = (logProbability$Sales + cv$sampleValue);
			}
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample32() {
		if(!fixedProbFlag$sample32) {
			double cv$sampleAccumulator = 0.0;
			for(int var29 = 0; var29 < noProducts; var29 += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((ut[var29] / 3.1622776601683795)) - 1.151292546497023);
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				logProbability$sample32[var29] = cv$distributionAccumulator;
				logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
				if((0 < T)) {
					logProbability$weekly_ut = (logProbability$weekly_ut + cv$distributionAccumulator);
					logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
				}
			}
			logProbability$var18 = cv$sampleAccumulator;
			logProbability$ut = (logProbability$ut + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample32 = fixedFlag$sample32;
		} else {
			double cv$rvAccumulator = 0.0;
			for(int var29 = 0; var29 < noProducts; var29 += 1) {
				double cv$sampleValue = logProbability$sample32[var29];
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
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample64() {
		if(!fixedProbFlag$sample64) {
			double cv$sampleAccumulator = 0.0;
			for(int var61 = 0; var61 < T; var61 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGamma(lambda[var61], 10.0, 10.0));
			logProbability$var50 = cv$sampleAccumulator;
			logProbability$var62 = cv$sampleAccumulator;
			logProbability$lambda = (logProbability$lambda + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample64)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample64 = fixedFlag$sample64;
		} else {
			logProbability$var50 = logProbability$var62;
			logProbability$lambda = (logProbability$lambda + logProbability$var62);
			logProbability$$model = (logProbability$$model + logProbability$var62);
			if(fixedFlag$sample64)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var62);
		}
	}

	private final void logProbabilityValue$sample79() {
		if(!fixedProbFlag$sample79) {
			double cv$accumulator = 0.0;
			for(int t$var74 = 0; t$var74 < T; t$var74 += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityPoisson(arrivals[t$var74], lambda[t$var74]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var76[t$var74] = cv$distributionAccumulator;
				logProbability$sample79[t$var74] = cv$distributionAccumulator;
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample79)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample79 = (fixedFlag$sample79 && fixedFlag$sample64);
		} else {
			double cv$accumulator = 0.0;
			for(int t$var74 = 0; t$var74 < T; t$var74 += 1) {
				double cv$rvAccumulator = logProbability$sample79[t$var74];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var76[t$var74] = cv$rvAccumulator;
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample79)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample32(int var29) {
		double cv$originalValue = ut[var29];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
				guard$sample32multinomial140$global[t$var89] = false;
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
				if(((0 < weekly_ut[t$var89].length) && !guard$sample32multinomial140$global[t$var89])) {
					guard$sample32multinomial140$global[t$var89] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var89], weekly_rates[t$var89], arrivals[t$var89]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
				if(!guard$sample32multinomial140$global[t$var89])
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var89], weekly_rates[t$var89], arrivals[t$var89]) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		ut[var29] = cv$proposedValue;
		exped[var29] = Math.exp(ut[var29]);
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
			weekly_ut[t$var89][var29] = (exped[var29] * Avail[t$var89][var29]);
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
			for(int j$var132 = 0; j$var132 <= noProducts; j$var132 += 1)
				guard$sample32put138$global[t$var89][j$var132] = false;
		}
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
			guard$sample32put138$global[t$var89][var29] = false;
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
			for(int j$var132 = 0; j$var132 <= noProducts; j$var132 += 1) {
				if(!guard$sample32put138$global[t$var89][j$var132]) {
					guard$sample32put138$global[t$var89][j$var132] = true;
					double reduceVar$denom$10 = 0.0;
					for(int cv$reduction118Index = 0; cv$reduction118Index <= noProducts; cv$reduction118Index += 1)
						reduceVar$denom$10 = (reduceVar$denom$10 + weekly_ut[t$var89][cv$reduction118Index]);
					weekly_rates[t$var89][j$var132] = (weekly_ut[t$var89][j$var132] / reduceVar$denom$10);
				}
			}
		}
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
			if(!guard$sample32put138$global[t$var89][var29]) {
				guard$sample32put138$global[t$var89][var29] = true;
				double reduceVar$denom$11 = 0.0;
				for(int cv$reduction118Index = 0; cv$reduction118Index <= noProducts; cv$reduction118Index += 1)
					reduceVar$denom$11 = (reduceVar$denom$11 + weekly_ut[t$var89][cv$reduction118Index]);
				weekly_rates[t$var89][var29] = (weekly_ut[t$var89][var29] / reduceVar$denom$11);
			}
		}
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
			guard$sample32multinomial140$global[t$var89] = false;
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
			if(((0 < weekly_ut[t$var89].length) && !guard$sample32multinomial140$global[t$var89])) {
				guard$sample32multinomial140$global[t$var89] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var89], weekly_rates[t$var89], arrivals[t$var89]) + cv$accumulatedProbabilities);
			}
		}
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
			if(!guard$sample32multinomial140$global[t$var89]) {
				guard$sample32multinomial140$global[t$var89] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var89], weekly_rates[t$var89], arrivals[t$var89]) + cv$accumulatedProbabilities);
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			ut[var29] = cv$originalValue;
			exped[var29] = Math.exp(ut[var29]);
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
				weekly_ut[t$var89][var29] = (exped[var29] * Avail[t$var89][var29]);
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
				for(int j$var132 = 0; j$var132 <= noProducts; j$var132 += 1)
					guard$sample32put138$global[t$var89][j$var132] = false;
			}
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
				guard$sample32put138$global[t$var89][var29] = false;
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
				for(int j$var132 = 0; j$var132 <= noProducts; j$var132 += 1) {
					if(!guard$sample32put138$global[t$var89][j$var132]) {
						guard$sample32put138$global[t$var89][j$var132] = true;
						double reduceVar$denom$13 = 0.0;
						for(int cv$reduction118Index = 0; cv$reduction118Index <= noProducts; cv$reduction118Index += 1)
							reduceVar$denom$13 = (reduceVar$denom$13 + weekly_ut[t$var89][cv$reduction118Index]);
						weekly_rates[t$var89][j$var132] = (weekly_ut[t$var89][j$var132] / reduceVar$denom$13);
					}
				}
			}
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
				if(!guard$sample32put138$global[t$var89][var29]) {
					guard$sample32put138$global[t$var89][var29] = true;
					double reduceVar$denom$14 = 0.0;
					for(int cv$reduction118Index = 0; cv$reduction118Index <= noProducts; cv$reduction118Index += 1)
						reduceVar$denom$14 = (reduceVar$denom$14 + weekly_ut[t$var89][cv$reduction118Index]);
					weekly_rates[t$var89][var29] = (weekly_ut[t$var89][var29] / reduceVar$denom$14);
				}
			}
		}
	}

	private final void sample64(int var61, int threadID$cv$var61, Rng RNG$) {
		lambda[var61] = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, arrivals[var61], 1);
	}

	private final void sample79(int t$var74, int threadID$cv$t$var74, Rng RNG$) {
		int cv$originalValue = arrivals[t$var74];
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 1.0))
			cv$var = 1.0;
		double cv$offset = (Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$));
		cv$offset = ((cv$offset <= 0.0)?(cv$offset - 1):(cv$offset + 1));
		int cv$proposedValue = (cv$originalValue + (int)cv$offset);
		double cv$originalProbability = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var74], weekly_rates[t$var74], cv$originalValue) + DistributionSampling.logProbabilityPoisson(cv$originalValue, lambda[t$var74]));
		arrivals[t$var74] = cv$proposedValue;
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var74], weekly_rates[t$var74], cv$proposedValue) + DistributionSampling.logProbabilityPoisson(cv$proposedValue, lambda[t$var74]));
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			arrivals[t$var74] = cv$originalValue;
	}

	@Override
	public final void allocateScratch() {
		int cv$max_j$var132 = 0;
		if((0 < T))
			cv$max_j$var132 = Math.max(0, (noProducts + 1));
		guard$sample32put138$global = new boolean[Math.max(0, T)][cv$max_j$var132];
		guard$sample32multinomial140$global = new boolean[Math.max(0, T)];
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
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
			Sales[t$var89] = new int[noProducts];
		weekly_rates = new double[T][];
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
			weekly_rates[t$var89] = new double[(noProducts + 1)];
		weekly_ut = new double[T][];
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
			weekly_ut[t$var89] = new double[(noProducts + 1)];
		if(!setFlag$weekly_sales) {
			weekly_sales = new int[T][];
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
				weekly_sales[t$var89] = new int[(noProducts + 1)];
		}
		logProbability$sample32 = new double[noProducts];
		logProbability$var76 = new double[T];
		logProbability$sample79 = new double[T];
		logProbability$var137 = new double[T];
		logProbability$sample141 = new double[T];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample32) {
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							ut[var29] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1)
							exped[j$var42] = Math.exp(ut[j$var42]);
				}
			);
		}
		if(!fixedFlag$sample64)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var61, int forEnd$var61, int threadID$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var61 = forStart$var61; var61 < forEnd$var61; var61 += 1)
							lambda[var61] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		if(!fixedFlag$sample79)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var74, int forEnd$t$var74, int threadID$t$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var74 = forStart$t$var74; t$var74 < forEnd$t$var74; t$var74 += 1)
							arrivals[t$var74] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var74]);
				}
			);

		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var89, int forEnd$index$t$var89, int threadID$index$t$var89, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var89 = forStart$index$t$var89; index$t$var89 < forEnd$index$t$var89; index$t$var89 += 1) {
						int t$var89 = index$t$var89;
						int threadID$t$var89 = threadID$index$t$var89;
						if(!fixedFlag$sample32)
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var104, int forEnd$j$var104, int threadID$j$var104, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var104 = forStart$j$var104; j$var104 < forEnd$j$var104; j$var104 += 1)
											weekly_ut[t$var89][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
								}
							);

						weekly_ut[t$var89][noProducts] = 1.0;
						if(!fixedFlag$sample32) {
							double reduceVar$denom$15 = 0.0;
							for(int cv$reduction118Index = 0; cv$reduction118Index <= noProducts; cv$reduction118Index += 1)
								reduceVar$denom$15 = (reduceVar$denom$15 + weekly_ut[t$var89][cv$reduction118Index]);
							double reduceVar$denom$15$1 = reduceVar$denom$15;
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var132, int forEnd$j$var132, int threadID$j$var132, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var132 = forStart$j$var132; j$var132 < forEnd$j$var132; j$var132 += 1)
											weekly_rates[t$var89][j$var132] = (weekly_ut[t$var89][j$var132] / reduceVar$denom$15$1);
								}
							);
						}
						if(!fixedFlag$sample141) {
							DistributionSampling.sampleMultinomial(RNG$1, weekly_rates[t$var89], arrivals[t$var89], weekly_sales[t$var89]);
							int[] observed_weekly_sales = Sales[t$var89];
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var148, int forEnd$j$var148, int threadID$j$var148, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var148 = forStart$j$var148; j$var148 < forEnd$j$var148; j$var148 += 1)
											observed_weekly_sales[j$var148] = weekly_sales[t$var89][j$var148];
								}
							);
						}
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample32) {
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							ut[var29] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1)
							exped[j$var42] = Math.exp(ut[j$var42]);
				}
			);
		}
		if(!fixedFlag$sample64)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var61, int forEnd$var61, int threadID$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var61 = forStart$var61; var61 < forEnd$var61; var61 += 1)
							lambda[var61] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		if(!fixedFlag$sample79)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var74, int forEnd$t$var74, int threadID$t$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var74 = forStart$t$var74; t$var74 < forEnd$t$var74; t$var74 += 1)
							arrivals[t$var74] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var74]);
				}
			);

		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var89, int forEnd$index$t$var89, int threadID$index$t$var89, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var89 = forStart$index$t$var89; index$t$var89 < forEnd$index$t$var89; index$t$var89 += 1) {
						int t$var89 = index$t$var89;
						int threadID$t$var89 = threadID$index$t$var89;
						if(!fixedFlag$sample32)
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var104, int forEnd$j$var104, int threadID$j$var104, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var104 = forStart$j$var104; j$var104 < forEnd$j$var104; j$var104 += 1)
											weekly_ut[t$var89][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
								}
							);

						weekly_ut[t$var89][noProducts] = 1.0;
						if(!fixedFlag$sample32) {
							double reduceVar$denom$17 = 0.0;
							for(int cv$reduction118Index = 0; cv$reduction118Index <= noProducts; cv$reduction118Index += 1)
								reduceVar$denom$17 = (reduceVar$denom$17 + weekly_ut[t$var89][cv$reduction118Index]);
							double reduceVar$denom$17$1 = reduceVar$denom$17;
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var132, int forEnd$j$var132, int threadID$j$var132, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var132 = forStart$j$var132; j$var132 < forEnd$j$var132; j$var132 += 1)
											weekly_rates[t$var89][j$var132] = (weekly_ut[t$var89][j$var132] / reduceVar$denom$17$1);
								}
							);
						}
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample32) {
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							ut[var29] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1)
							exped[j$var42] = Math.exp(ut[j$var42]);
				}
			);
		}
		if(!fixedFlag$sample64)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var61, int forEnd$var61, int threadID$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var61 = forStart$var61; var61 < forEnd$var61; var61 += 1)
							lambda[var61] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		if(!fixedFlag$sample79)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var74, int forEnd$t$var74, int threadID$t$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var74 = forStart$t$var74; t$var74 < forEnd$t$var74; t$var74 += 1)
							arrivals[t$var74] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var74]);
				}
			);

		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var89, int forEnd$index$t$var89, int threadID$index$t$var89, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var89 = forStart$index$t$var89; index$t$var89 < forEnd$index$t$var89; index$t$var89 += 1) {
						int t$var89 = index$t$var89;
						int threadID$t$var89 = threadID$index$t$var89;
						if(!fixedFlag$sample32)
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var104, int forEnd$j$var104, int threadID$j$var104, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var104 = forStart$j$var104; j$var104 < forEnd$j$var104; j$var104 += 1)
											weekly_ut[t$var89][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
								}
							);

						weekly_ut[t$var89][noProducts] = 1.0;
						if(!fixedFlag$sample32) {
							double reduceVar$denom$16 = 0.0;
							for(int cv$reduction118Index = 0; cv$reduction118Index <= noProducts; cv$reduction118Index += 1)
								reduceVar$denom$16 = (reduceVar$denom$16 + weekly_ut[t$var89][cv$reduction118Index]);
							double reduceVar$denom$16$1 = reduceVar$denom$16;
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var132, int forEnd$j$var132, int threadID$j$var132, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var132 = forStart$j$var132; j$var132 < forEnd$j$var132; j$var132 += 1)
											weekly_rates[t$var89][j$var132] = (weekly_ut[t$var89][j$var132] / reduceVar$denom$16$1);
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
			if(!fixedFlag$sample32) {
				for(int var29 = 0; var29 < noProducts; var29 += 1)
					sample32(var29);
			}
			if(!fixedFlag$sample64)
				parallelFor(RNG$, 0, T, 1,
					(int forStart$var61, int forEnd$var61, int threadID$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var61 = forStart$var61; var61 < forEnd$var61; var61 += 1)
								sample64(var61, threadID$var61, RNG$1);
					}
				);

			if(!fixedFlag$sample79)
				parallelFor(RNG$, 0, T, 1,
					(int forStart$t$var74, int forEnd$t$var74, int threadID$t$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int t$var74 = forStart$t$var74; t$var74 < forEnd$t$var74; t$var74 += 1)
								sample79(t$var74, threadID$t$var74, RNG$1);
					}
				);

		} else {
			if(!fixedFlag$sample79)
				parallelFor(RNG$, 0, T, 1,
					(int forStart$t$var74, int forEnd$t$var74, int threadID$t$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int t$var74 = forStart$t$var74; t$var74 < forEnd$t$var74; t$var74 += 1)
								sample79(t$var74, threadID$t$var74, RNG$1);
					}
				);

			if(!fixedFlag$sample64)
				parallelFor(RNG$, 0, T, 1,
					(int forStart$var61, int forEnd$var61, int threadID$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var61 = forStart$var61; var61 < forEnd$var61; var61 += 1)
								sample64(var61, threadID$var61, RNG$1);
					}
				);

			if(!fixedFlag$sample32) {
				for(int var29 = (noProducts - 1); var29 >= 0; var29 -= 1)
					sample32(var29);
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
		logProbability$weekly_rates = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$ut = 0.0;
		if(!fixedProbFlag$sample32) {
			for(int var29 = 0; var29 < noProducts; var29 += 1)
				logProbability$sample32[var29] = 0.0;
		}
		logProbability$var50 = 0.0;
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample64)
			logProbability$var62 = 0.0;
		for(int t$var74 = 0; t$var74 < T; t$var74 += 1)
			logProbability$var76[t$var74] = 0.0;
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample79) {
			for(int t$var74 = 0; t$var74 < T; t$var74 += 1)
				logProbability$sample79[t$var74] = 0.0;
		}
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
			logProbability$var137[t$var89] = 0.0;
		logProbability$Sales = 0.0;
		logProbability$weekly_sales = 0.0;
		if(!fixedProbFlag$sample141) {
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
				logProbability$sample141[t$var89] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(fixedFlag$sample64)
			logProbabilityValue$sample64();
		if(fixedFlag$sample79)
			logProbabilityValue$sample79();
		logProbabilityValue$sample141();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample32();
		logProbabilityValue$sample64();
		logProbabilityValue$sample79();
		logProbabilityValue$sample141();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample32();
		logProbabilityValue$sample64();
		logProbabilityValue$sample79();
		logProbabilityValue$sample141();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample32) {
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
							ut[var29] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1)
							exped[j$var42] = Math.exp(ut[j$var42]);
				}
			);
		}
		if(!fixedFlag$sample64)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var61, int forEnd$var61, int threadID$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var61 = forStart$var61; var61 < forEnd$var61; var61 += 1)
							lambda[var61] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		if(!fixedFlag$sample79)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var74, int forEnd$t$var74, int threadID$t$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var74 = forStart$t$var74; t$var74 < forEnd$t$var74; t$var74 += 1)
							arrivals[t$var74] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var74]);
				}
			);

		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var89, int forEnd$index$t$var89, int threadID$index$t$var89, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var89 = forStart$index$t$var89; index$t$var89 < forEnd$index$t$var89; index$t$var89 += 1) {
						int t$var89 = index$t$var89;
						int threadID$t$var89 = threadID$index$t$var89;
						if(!fixedFlag$sample32)
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var104, int forEnd$j$var104, int threadID$j$var104, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var104 = forStart$j$var104; j$var104 < forEnd$j$var104; j$var104 += 1)
											weekly_ut[t$var89][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
								}
							);

						weekly_ut[t$var89][noProducts] = 1.0;
						if(!fixedFlag$sample32) {
							double reduceVar$denom$18 = 0.0;
							for(int cv$reduction118Index = 0; cv$reduction118Index <= noProducts; cv$reduction118Index += 1)
								reduceVar$denom$18 = (reduceVar$denom$18 + weekly_ut[t$var89][cv$reduction118Index]);
							double reduceVar$denom$18$1 = reduceVar$denom$18;
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var132, int forEnd$j$var132, int threadID$j$var132, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var132 = forStart$j$var132; j$var132 < forEnd$j$var132; j$var132 += 1)
											weekly_rates[t$var89][j$var132] = (weekly_ut[t$var89][j$var132] / reduceVar$denom$18$1);
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
		for(int t$var89 = (T - 1); t$var89 >= 0; t$var89 -= 1) {
			for(int j$var148 = (noProducts - 1); j$var148 >= 0; j$var148 -= 1)
				weekly_sales[t$var89][j$var148] = Sales[t$var89][j$var148];
		}
	}

	@Override
	public final void setIntermediates() {
		if(setFlag$ut)
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1)
							exped[j$var42] = Math.exp(ut[j$var42]);
				}
			);

		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var89, int forEnd$index$t$var89, int threadID$index$t$var89, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var89 = forStart$index$t$var89; index$t$var89 < forEnd$index$t$var89; index$t$var89 += 1) {
						int t$var89 = index$t$var89;
						int threadID$t$var89 = threadID$index$t$var89;
						if(setFlag$ut)
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var104, int forEnd$j$var104, int threadID$j$var104, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var104 = forStart$j$var104; j$var104 < forEnd$j$var104; j$var104 += 1)
											weekly_ut[t$var89][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
								}
							);

						double reduceVar$denom$19 = 0.0;
						for(int cv$reduction118Index = 0; cv$reduction118Index <= noProducts; cv$reduction118Index += 1)
							reduceVar$denom$19 = (reduceVar$denom$19 + weekly_ut[t$var89][cv$reduction118Index]);
						if(setFlag$ut) {
							double reduceVar$denom$19$1 = reduceVar$denom$19;
							parallelFor(RNG$1, 0, (noProducts + 1), 1,
								(int forStart$j$var132, int forEnd$j$var132, int threadID$j$var132, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var132 = forStart$j$var132; j$var132 < forEnd$j$var132; j$var132 += 1)
											weekly_rates[t$var89][j$var132] = (weekly_ut[t$var89][j$var132] / reduceVar$denom$19$1);
								}
							);
						}
						if(setFlag$weekly_sales) {
							int[] observed_weekly_sales = Sales[t$var89];
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var148, int forEnd$j$var148, int threadID$j$var148, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var148 = forStart$j$var148; j$var148 < forEnd$j$var148; j$var148 += 1)
											observed_weekly_sales[j$var148] = weekly_sales[t$var89][j$var148];
								}
							);
						}
					}
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n/*\n * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n */\npackage org.sandwood.compiler.tests.parser;\n\nmodel Vulcano2012notNormalized(int noProducts, int T, int s, int[][] ObsSales, int[][] Avail) {\n    // Avail is the availability matrix, T-by-noProducts\n\n    // draw utilities\n    double[] ut = gaussian(0, 10).sample(noProducts);\n\n    //exponentiate right here (in the non-basic models move to the for loop)\n    double[] exped = new double[noProducts];\n    for(int j : [0..noProducts)) {\n    exped[j] = exp(ut[j]);\n    }\n\n    // priors for the distribution of lambdas (for arrivals). They can be supplied as a vector if RGBU has some estimates, or just use some ad hoc priors\n    double[ ] lambda = gamma(10,10).sample(T);\n\n    // draw arrivals\n    int[] arrivals = new int[T];\n    for (int t : [0..T)){\n    arrivals[t]= poisson(lambda[t]).sample();\n    }\n\n    int[][] Sales = new int[T][];\n\n    for (int t:[0..T)){\n        // for each period t calculate choice probabilities and sales\n\n        double[] weekly_rates = new double[noProducts+1];\n        double[] weekly_ut = new double[noProducts+1];\n\n        for (int j : [0..noProducts)) {\n            weekly_ut[j] = exped[j]*Avail[t][j] ;\n        }\n        // add outside option value (which is always available)\n        weekly_ut[noProducts] = 1.0;\n        double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n\n        for (int j : [0..noProducts]) {\n            weekly_rates[j] = weekly_ut[j]/denom ;\n        }\n\n        int[] weekly_sales = multinomial(weekly_rates, arrivals[t]).sample();\n\n        //getting rid of the no purchase observation (last one in the vector of weekly_sales)\n        int[] observed_weekly_sales = new int[noProducts];\n        for (int j : [0..noProducts)) {\n            observed_weekly_sales[j] = weekly_sales[j] ;\n        }\n\n        // record sales for period t\n        Sales[t] = observed_weekly_sales;\n\n    }\n    // assert that generated sales match observed sales\n    Sales.observe(ObsSales);\n}";
	}
}