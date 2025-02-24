package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Vulcano2012notNormalized$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Vulcano2012notNormalized$CoreInterface {
	private int[][] Avail;
	private int[][] ObsSales;
	private int[][] Sales;
	private int T;
	private int[] arrivals;
	private double[] exped;
	private boolean fixedFlag$sample131 = false;
	private boolean fixedFlag$sample22 = false;
	private boolean fixedFlag$sample54 = false;
	private boolean fixedFlag$sample69 = false;
	private boolean fixedProbFlag$sample131 = false;
	private boolean fixedProbFlag$sample22 = false;
	private boolean fixedProbFlag$sample54 = false;
	private boolean fixedProbFlag$sample69 = false;
	private boolean[] guard$sample22multinomial130$global;
	private boolean[][] guard$sample22put128$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$Sales;
	private double logProbability$arrivals;
	private double logProbability$exped;
	private double logProbability$lambda;
	private double[] logProbability$sample131;
	private double[] logProbability$sample22;
	private double[] logProbability$sample69;
	private double logProbability$ut;
	private double logProbability$var10;
	private double[] logProbability$var129;
	private double logProbability$var42;
	private double logProbability$var54;
	private double[] logProbability$var68;
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

	public Vulcano2012notNormalized$SingleThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample69 = false;
		fixedProbFlag$sample131 = false;
	}

	@Override
	public final double[] get$exped() {
		return exped;
	}

	@Override
	public final boolean get$fixedFlag$sample131() {
		return fixedFlag$sample131;
	}

	@Override
	public final void set$fixedFlag$sample131(boolean cv$value) {
		fixedFlag$sample131 = cv$value;
		fixedProbFlag$sample131 = (cv$value && fixedProbFlag$sample131);
	}

	@Override
	public final boolean get$fixedFlag$sample22() {
		return fixedFlag$sample22;
	}

	@Override
	public final void set$fixedFlag$sample22(boolean cv$value) {
		fixedFlag$sample22 = cv$value;
		fixedProbFlag$sample22 = (cv$value && fixedProbFlag$sample22);
		fixedProbFlag$sample131 = (cv$value && fixedProbFlag$sample131);
	}

	@Override
	public final boolean get$fixedFlag$sample54() {
		return fixedFlag$sample54;
	}

	@Override
	public final void set$fixedFlag$sample54(boolean cv$value) {
		fixedFlag$sample54 = cv$value;
		fixedProbFlag$sample54 = (cv$value && fixedProbFlag$sample54);
		fixedProbFlag$sample69 = (cv$value && fixedProbFlag$sample69);
	}

	@Override
	public final boolean get$fixedFlag$sample69() {
		return fixedFlag$sample69;
	}

	@Override
	public final void set$fixedFlag$sample69(boolean cv$value) {
		fixedFlag$sample69 = cv$value;
		fixedProbFlag$sample69 = (cv$value && fixedProbFlag$sample69);
		fixedProbFlag$sample131 = (cv$value && fixedProbFlag$sample131);
	}

	@Override
	public final double[] get$lambda() {
		return lambda;
	}

	@Override
	public final void set$lambda(double[] cv$value) {
		lambda = cv$value;
		setFlag$lambda = true;
		fixedProbFlag$sample54 = false;
		fixedProbFlag$sample69 = false;
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
		fixedProbFlag$sample22 = false;
		fixedProbFlag$sample131 = false;
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

	private final void logProbabilityValue$sample131() {
		if(!fixedProbFlag$sample131) {
			double cv$accumulator = 0.0;
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var81], weekly_rates[t$var81], (noProducts + 1), arrivals[t$var81]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var129[t$var81] = cv$distributionAccumulator;
				logProbability$sample131[t$var81] = cv$distributionAccumulator;
				if((0 < noProducts))
					logProbability$Sales = (logProbability$Sales + cv$distributionAccumulator);
			}
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample131 = ((fixedFlag$sample131 && fixedFlag$sample22) && fixedFlag$sample69);
		} else {
			double cv$accumulator = 0.0;
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
				double cv$sampleValue = logProbability$sample131[t$var81];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var129[t$var81] = cv$sampleValue;
				if((0 < noProducts))
					logProbability$Sales = (logProbability$Sales + cv$sampleValue);
			}
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample22() {
		if(!fixedProbFlag$sample22) {
			double cv$sampleAccumulator = 0.0;
			for(int var21 = 0; var21 < noProducts; var21 += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((ut[var21] / 3.1622776601683795)) - 1.151292546497023);
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				logProbability$sample22[var21] = cv$distributionAccumulator;
				logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
				if((0 < T)) {
					logProbability$weekly_ut = (logProbability$weekly_ut + cv$distributionAccumulator);
					logProbability$weekly_rates = (logProbability$weekly_rates + cv$distributionAccumulator);
				}
			}
			logProbability$var10 = cv$sampleAccumulator;
			logProbability$ut = (logProbability$ut + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample22 = fixedFlag$sample22;
		} else {
			double cv$rvAccumulator = 0.0;
			for(int var21 = 0; var21 < noProducts; var21 += 1) {
				double cv$sampleValue = logProbability$sample22[var21];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				logProbability$exped = (logProbability$exped + cv$sampleValue);
				if((0 < T)) {
					logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleValue);
					logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
				}
			}
			logProbability$var10 = cv$rvAccumulator;
			logProbability$ut = (logProbability$ut + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample54() {
		if(!fixedProbFlag$sample54) {
			double cv$sampleAccumulator = 0.0;
			for(int var53 = 0; var53 < T; var53 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGamma(lambda[var53], 10.0, 10.0));
			logProbability$var42 = cv$sampleAccumulator;
			logProbability$var54 = cv$sampleAccumulator;
			logProbability$lambda = (logProbability$lambda + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample54)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample54 = fixedFlag$sample54;
		} else {
			logProbability$var42 = logProbability$var54;
			logProbability$lambda = (logProbability$lambda + logProbability$var54);
			logProbability$$model = (logProbability$$model + logProbability$var54);
			if(fixedFlag$sample54)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var54);
		}
	}

	private final void logProbabilityValue$sample69() {
		if(!fixedProbFlag$sample69) {
			double cv$accumulator = 0.0;
			for(int t$var66 = 0; t$var66 < T; t$var66 += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityPoisson(arrivals[t$var66], lambda[t$var66]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var68[t$var66] = cv$distributionAccumulator;
				logProbability$sample69[t$var66] = cv$distributionAccumulator;
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample69)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample69 = (fixedFlag$sample69 && fixedFlag$sample54);
		} else {
			double cv$accumulator = 0.0;
			for(int t$var66 = 0; t$var66 < T; t$var66 += 1) {
				double cv$rvAccumulator = logProbability$sample69[t$var66];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var68[t$var66] = cv$rvAccumulator;
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample69)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample22(int var21) {
		double cv$originalValue = ut[var21];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
				guard$sample22multinomial130$global[t$var81] = false;
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
				if(!guard$sample22multinomial130$global[t$var81]) {
					guard$sample22multinomial130$global[t$var81] = true;
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var81], weekly_rates[t$var81], (noProducts + 1), arrivals[t$var81]) + cv$accumulatedProbabilities);
				}
			}
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
				if(!guard$sample22multinomial130$global[t$var81])
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var81], weekly_rates[t$var81], (noProducts + 1), arrivals[t$var81]) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		ut[var21] = cv$proposedValue;
		exped[var21] = Math.exp(ut[var21]);
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
			weekly_ut[t$var81][var21] = (exped[var21] * Avail[t$var81][var21]);
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
			for(int j$var124 = 0; j$var124 <= noProducts; j$var124 += 1)
				guard$sample22put128$global[t$var81][j$var124] = false;
		}
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
			guard$sample22put128$global[t$var81][var21] = false;
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
			for(int j$var124 = 0; j$var124 <= noProducts; j$var124 += 1) {
				if(!guard$sample22put128$global[t$var81][j$var124]) {
					guard$sample22put128$global[t$var81][j$var124] = true;
					double reduceVar$denom$0 = 0.0;
					for(int cv$reduction108Index = 0; cv$reduction108Index <= noProducts; cv$reduction108Index += 1)
						reduceVar$denom$0 = (reduceVar$denom$0 + weekly_ut[t$var81][cv$reduction108Index]);
					weekly_rates[t$var81][j$var124] = (weekly_ut[t$var81][j$var124] / reduceVar$denom$0);
				}
			}
		}
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
			if(!guard$sample22put128$global[t$var81][var21]) {
				guard$sample22put128$global[t$var81][var21] = true;
				double reduceVar$denom$1 = 0.0;
				for(int cv$reduction108Index = 0; cv$reduction108Index <= noProducts; cv$reduction108Index += 1)
					reduceVar$denom$1 = (reduceVar$denom$1 + weekly_ut[t$var81][cv$reduction108Index]);
				weekly_rates[t$var81][var21] = (weekly_ut[t$var81][var21] / reduceVar$denom$1);
			}
		}
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
			guard$sample22multinomial130$global[t$var81] = false;
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
			if(!guard$sample22multinomial130$global[t$var81]) {
				guard$sample22multinomial130$global[t$var81] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var81], weekly_rates[t$var81], (noProducts + 1), arrivals[t$var81]) + cv$accumulatedProbabilities);
			}
		}
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
			if(!guard$sample22multinomial130$global[t$var81]) {
				guard$sample22multinomial130$global[t$var81] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var81], weekly_rates[t$var81], (noProducts + 1), arrivals[t$var81]) + cv$accumulatedProbabilities);
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			ut[var21] = cv$originalValue;
			exped[var21] = Math.exp(ut[var21]);
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
				weekly_ut[t$var81][var21] = (exped[var21] * Avail[t$var81][var21]);
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
				for(int j$var124 = 0; j$var124 <= noProducts; j$var124 += 1)
					guard$sample22put128$global[t$var81][j$var124] = false;
			}
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
				guard$sample22put128$global[t$var81][var21] = false;
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
				for(int j$var124 = 0; j$var124 <= noProducts; j$var124 += 1) {
					if(!guard$sample22put128$global[t$var81][j$var124]) {
						guard$sample22put128$global[t$var81][j$var124] = true;
						double reduceVar$denom$3 = 0.0;
						for(int cv$reduction108Index = 0; cv$reduction108Index <= noProducts; cv$reduction108Index += 1)
							reduceVar$denom$3 = (reduceVar$denom$3 + weekly_ut[t$var81][cv$reduction108Index]);
						weekly_rates[t$var81][j$var124] = (weekly_ut[t$var81][j$var124] / reduceVar$denom$3);
					}
				}
			}
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
				if(!guard$sample22put128$global[t$var81][var21]) {
					guard$sample22put128$global[t$var81][var21] = true;
					double reduceVar$denom$4 = 0.0;
					for(int cv$reduction108Index = 0; cv$reduction108Index <= noProducts; cv$reduction108Index += 1)
						reduceVar$denom$4 = (reduceVar$denom$4 + weekly_ut[t$var81][cv$reduction108Index]);
					weekly_rates[t$var81][var21] = (weekly_ut[t$var81][var21] / reduceVar$denom$4);
				}
			}
		}
	}

	private final void sample54(int var53) {
		lambda[var53] = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, arrivals[var53], 1);
	}

	private final void sample69(int t$var66) {
		int cv$originalValue = arrivals[t$var66];
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 1.0))
			cv$var = 1.0;
		double cv$offset = (Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$));
		cv$offset = ((cv$offset <= 0.0)?(cv$offset - 1):(cv$offset + 1));
		int cv$proposedValue = (cv$originalValue + (int)cv$offset);
		double cv$originalProbability = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var66], weekly_rates[t$var66], (noProducts + 1), cv$originalValue) + DistributionSampling.logProbabilityPoisson(cv$originalValue, lambda[t$var66]));
		arrivals[t$var66] = cv$proposedValue;
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityMultinomial(weekly_sales[t$var66], weekly_rates[t$var66], (noProducts + 1), cv$proposedValue) + DistributionSampling.logProbabilityPoisson(cv$proposedValue, lambda[t$var66]));
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			arrivals[t$var66] = cv$originalValue;
	}

	@Override
	public final void allocateScratch() {
		int cv$max_j$var124 = 0;
		if((0 < T))
			cv$max_j$var124 = Math.max(0, (noProducts + 1));
		guard$sample22put128$global = new boolean[Math.max(0, T)][cv$max_j$var124];
		guard$sample22multinomial130$global = new boolean[Math.max(0, T)];
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
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
			Sales[t$var81] = new int[noProducts];
		weekly_rates = new double[T][];
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
			weekly_rates[t$var81] = new double[(noProducts + 1)];
		weekly_ut = new double[T][];
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
			weekly_ut[t$var81] = new double[(noProducts + 1)];
		if(!setFlag$weekly_sales) {
			weekly_sales = new int[T][];
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
				weekly_sales[t$var81] = new int[(noProducts + 1)];
		}
		logProbability$sample22 = new double[noProducts];
		logProbability$var68 = new double[T];
		logProbability$sample69 = new double[T];
		logProbability$var129 = new double[T];
		logProbability$sample131 = new double[T];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample22) {
			for(int var21 = 0; var21 < noProducts; var21 += 1)
				ut[var21] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1)
				exped[j$var34] = Math.exp(ut[j$var34]);
		}
		if(!fixedFlag$sample54) {
			for(int var53 = 0; var53 < T; var53 += 1)
				lambda[var53] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		if(!fixedFlag$sample69) {
			for(int t$var66 = 0; t$var66 < T; t$var66 += 1)
				arrivals[t$var66] = DistributionSampling.samplePoisson(RNG$, lambda[t$var66]);
		}
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
			if(!fixedFlag$sample22) {
				for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1)
					weekly_ut[t$var81][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
			}
			weekly_ut[t$var81][noProducts] = 1.0;
			if(!fixedFlag$sample22) {
				double reduceVar$denom$5 = 0.0;
				for(int cv$reduction108Index = 0; cv$reduction108Index <= noProducts; cv$reduction108Index += 1)
					reduceVar$denom$5 = (reduceVar$denom$5 + weekly_ut[t$var81][cv$reduction108Index]);
				for(int j$var124 = 0; j$var124 <= noProducts; j$var124 += 1)
					weekly_rates[t$var81][j$var124] = (weekly_ut[t$var81][j$var124] / reduceVar$denom$5);
			}
			if(!fixedFlag$sample131) {
				DistributionSampling.sampleMultinomial(RNG$, weekly_rates[t$var81], (noProducts + 1), arrivals[t$var81], weekly_sales[t$var81]);
				int[] observed_weekly_sales = Sales[t$var81];
				for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1)
					observed_weekly_sales[j$var140] = weekly_sales[t$var81][j$var140];
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample22) {
			for(int var21 = 0; var21 < noProducts; var21 += 1)
				ut[var21] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1)
				exped[j$var34] = Math.exp(ut[j$var34]);
		}
		if(!fixedFlag$sample54) {
			for(int var53 = 0; var53 < T; var53 += 1)
				lambda[var53] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		if(!fixedFlag$sample69) {
			for(int t$var66 = 0; t$var66 < T; t$var66 += 1)
				arrivals[t$var66] = DistributionSampling.samplePoisson(RNG$, lambda[t$var66]);
		}
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
			if(!fixedFlag$sample22) {
				for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1)
					weekly_ut[t$var81][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
			}
			weekly_ut[t$var81][noProducts] = 1.0;
			if(!fixedFlag$sample22) {
				double reduceVar$denom$7 = 0.0;
				for(int cv$reduction108Index = 0; cv$reduction108Index <= noProducts; cv$reduction108Index += 1)
					reduceVar$denom$7 = (reduceVar$denom$7 + weekly_ut[t$var81][cv$reduction108Index]);
				for(int j$var124 = 0; j$var124 <= noProducts; j$var124 += 1)
					weekly_rates[t$var81][j$var124] = (weekly_ut[t$var81][j$var124] / reduceVar$denom$7);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample22) {
			for(int var21 = 0; var21 < noProducts; var21 += 1)
				ut[var21] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1)
				exped[j$var34] = Math.exp(ut[j$var34]);
		}
		if(!fixedFlag$sample54) {
			for(int var53 = 0; var53 < T; var53 += 1)
				lambda[var53] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		if(!fixedFlag$sample69) {
			for(int t$var66 = 0; t$var66 < T; t$var66 += 1)
				arrivals[t$var66] = DistributionSampling.samplePoisson(RNG$, lambda[t$var66]);
		}
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
			if(!fixedFlag$sample22) {
				for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1)
					weekly_ut[t$var81][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
			}
			weekly_ut[t$var81][noProducts] = 1.0;
			if(!fixedFlag$sample22) {
				double reduceVar$denom$6 = 0.0;
				for(int cv$reduction108Index = 0; cv$reduction108Index <= noProducts; cv$reduction108Index += 1)
					reduceVar$denom$6 = (reduceVar$denom$6 + weekly_ut[t$var81][cv$reduction108Index]);
				for(int j$var124 = 0; j$var124 <= noProducts; j$var124 += 1)
					weekly_rates[t$var81][j$var124] = (weekly_ut[t$var81][j$var124] / reduceVar$denom$6);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample22) {
				for(int var21 = 0; var21 < noProducts; var21 += 1)
					sample22(var21);
			}
			if(!fixedFlag$sample54) {
				for(int var53 = 0; var53 < T; var53 += 1)
					sample54(var53);
			}
			if(!fixedFlag$sample69) {
				for(int t$var66 = 0; t$var66 < T; t$var66 += 1)
					sample69(t$var66);
			}
		} else {
			if(!fixedFlag$sample69) {
				for(int t$var66 = (T - 1); t$var66 >= 0; t$var66 -= 1)
					sample69(t$var66);
			}
			if(!fixedFlag$sample54) {
				for(int var53 = (T - 1); var53 >= 0; var53 -= 1)
					sample54(var53);
			}
			if(!fixedFlag$sample22) {
				for(int var21 = (noProducts - 1); var21 >= 0; var21 -= 1)
					sample22(var21);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var10 = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$ut = 0.0;
		logProbability$weekly_rates = 0.0;
		if(!fixedProbFlag$sample22) {
			for(int var21 = 0; var21 < noProducts; var21 += 1)
				logProbability$sample22[var21] = 0.0;
		}
		logProbability$var42 = 0.0;
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample54)
			logProbability$var54 = 0.0;
		for(int t$var66 = 0; t$var66 < T; t$var66 += 1)
			logProbability$var68[t$var66] = 0.0;
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample69) {
			for(int t$var66 = 0; t$var66 < T; t$var66 += 1)
				logProbability$sample69[t$var66] = 0.0;
		}
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
			logProbability$var129[t$var81] = 0.0;
		logProbability$weekly_sales = 0.0;
		logProbability$Sales = 0.0;
		if(!fixedProbFlag$sample131) {
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
				logProbability$sample131[t$var81] = 0.0;
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
		if(fixedFlag$sample54)
			logProbabilityValue$sample54();
		if(fixedFlag$sample69)
			logProbabilityValue$sample69();
		logProbabilityValue$sample131();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample22();
		logProbabilityValue$sample54();
		logProbabilityValue$sample69();
		logProbabilityValue$sample131();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample22();
		logProbabilityValue$sample54();
		logProbabilityValue$sample69();
		logProbabilityValue$sample131();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample22) {
			for(int var21 = 0; var21 < noProducts; var21 += 1)
				ut[var21] = (DistributionSampling.sampleGaussian(RNG$) * 3.1622776601683795);
			for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1)
				exped[j$var34] = Math.exp(ut[j$var34]);
		}
		if(!fixedFlag$sample54) {
			for(int var53 = 0; var53 < T; var53 += 1)
				lambda[var53] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		if(!fixedFlag$sample69) {
			for(int t$var66 = 0; t$var66 < T; t$var66 += 1)
				arrivals[t$var66] = DistributionSampling.samplePoisson(RNG$, lambda[t$var66]);
		}
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
			if(!fixedFlag$sample22) {
				for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1)
					weekly_ut[t$var81][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
			}
			weekly_ut[t$var81][noProducts] = 1.0;
			if(!fixedFlag$sample22) {
				double reduceVar$denom$8 = 0.0;
				for(int cv$reduction108Index = 0; cv$reduction108Index <= noProducts; cv$reduction108Index += 1)
					reduceVar$denom$8 = (reduceVar$denom$8 + weekly_ut[t$var81][cv$reduction108Index]);
				for(int j$var124 = 0; j$var124 <= noProducts; j$var124 += 1)
					weekly_rates[t$var81][j$var124] = (weekly_ut[t$var81][j$var124] / reduceVar$denom$8);
			}
		}
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
		for(int t$var81 = (T - 1); t$var81 >= 0; t$var81 -= 1) {
			for(int j$var140 = (noProducts - 1); j$var140 >= 0; j$var140 -= 1)
				weekly_sales[t$var81][j$var140] = Sales[t$var81][j$var140];
		}
	}

	@Override
	public final void setIntermediates() {
		if(setFlag$ut) {
			for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1)
				exped[j$var34] = Math.exp(ut[j$var34]);
		}
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
			if(setFlag$ut) {
				for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1)
					weekly_ut[t$var81][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
			}
			double reduceVar$denom$9 = 0.0;
			for(int cv$reduction108Index = 0; cv$reduction108Index <= noProducts; cv$reduction108Index += 1)
				reduceVar$denom$9 = (reduceVar$denom$9 + weekly_ut[t$var81][cv$reduction108Index]);
			if(setFlag$ut) {
				for(int j$var124 = 0; j$var124 <= noProducts; j$var124 += 1)
					weekly_rates[t$var81][j$var124] = (weekly_ut[t$var81][j$var124] / reduceVar$denom$9);
			}
			if(setFlag$weekly_sales) {
				int[] observed_weekly_sales = Sales[t$var81];
				for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1)
					observed_weekly_sales[j$var140] = weekly_sales[t$var81][j$var140];
			}
		}
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "/*\n"
		     + " * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n"
		     + " * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n"
		     + " * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n"
		     + " */\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model Vulcano2012notNormalized(int noProducts, int T, int s, int[][] ObsSales, int[][] Avail) {\n"
		     + "    // Avail is the availability matrix, T-by-noProducts\n"
		     + "\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = gaussian(0, 10).sample(noProducts);\n"
		     + "\n"
		     + "    //exponentiate right here (in the non-basic models move to the for loop)\n"
		     + "    double[] exped = new double[noProducts];\n"
		     + "    for(int j : [0..noProducts)) {\n"
		     + "    exped[j] = exp(ut[j]);\n"
		     + "    }\n"
		     + "\n"
		     + "    // priors for the distribution of lambdas (for arrivals). They can be supplied as a vector if RGBU has some estimates, or just use some ad hoc priors\n"
		     + "    double[ ] lambda = gamma(10,10).sample(T);\n"
		     + "\n"
		     + "    // draw arrivals\n"
		     + "    int[] arrivals = new int[T];\n"
		     + "    for (int t : [0..T)){\n"
		     + "    arrivals[t]= poisson(lambda[t]).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    int[][] Sales = new int[T][];\n"
		     + "\n"
		     + "    for (int t:[0..T)){\n"
		     + "        // for each period t calculate choice probabilities and sales\n"
		     + "\n"
		     + "        double[] weekly_rates = new double[noProducts+1];\n"
		     + "        double[] weekly_ut = new double[noProducts+1];\n"
		     + "\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            weekly_ut[j] = exped[j]*Avail[t][j] ;\n"
		     + "        }\n"
		     + "        // add outside option value (which is always available)\n"
		     + "        weekly_ut[noProducts] = 1.0;\n"
		     + "        double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n"
		     + "\n"
		     + "        for (int j : [0..noProducts]) {\n"
		     + "            weekly_rates[j] = weekly_ut[j]/denom ;\n"
		     + "        }\n"
		     + "\n"
		     + "        int[] weekly_sales = multinomial(weekly_rates, arrivals[t]).sample();\n"
		     + "\n"
		     + "        //getting rid of the no purchase observation (last one in the vector of weekly_sales)\n"
		     + "        int[] observed_weekly_sales = new int[noProducts];\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            observed_weekly_sales[j] = weekly_sales[j] ;\n"
		     + "        }\n"
		     + "\n"
		     + "        // record sales for period t\n"
		     + "        Sales[t] = observed_weekly_sales;\n"
		     + "\n"
		     + "    }\n"
		     + "    // assert that generated sales match observed sales\n"
		     + "    Sales.observe(ObsSales);\n"
		     + "}";
	}
}