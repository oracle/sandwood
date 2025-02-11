package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Vulcano2012basic$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Vulcano2012basic$CoreInterface {
	private int[][] Avail;
	private double[][] ObsSales;
	private double[][] Sales;
	private int T;
	private int[] arrivals;
	private double denom;
	private double[] exped;
	private boolean fixedFlag$sample25 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedFlag$sample61 = false;
	private boolean fixedFlag$sample86 = false;
	private boolean fixedProbFlag$sample25 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean fixedProbFlag$sample61 = false;
	private boolean fixedProbFlag$sample86 = false;
	private boolean[][] guard$sample25gaussian85$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$Sales;
	private double logProbability$arrivals;
	private double logProbability$denom;
	private double logProbability$exped;
	private double logProbability$lambda;
	private double[] logProbability$sample25;
	private double[] logProbability$sample61;
	private double[][] logProbability$sample86;
	private double logProbability$sum;
	private double logProbability$ut;
	private double logProbability$var18;
	private double logProbability$var45;
	private double logProbability$var50;
	private double[] logProbability$var57;
	private double[][] logProbability$var81;
	private int noProducts;
	private int s;
	private boolean setFlag$Sales = false;
	private boolean setFlag$arrivals = false;
	private boolean setFlag$lambda = false;
	private boolean setFlag$ut = false;
	private double sum;
	private boolean system$gibbsForward = true;
	private double[] ut;

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
	public final double[][] get$ObsSales() {
		return ObsSales;
	}

	@Override
	public final void set$ObsSales(double[][] cv$value) {
		ObsSales = cv$value;
	}

	@Override
	public final double[][] get$Sales() {
		return Sales;
	}

	@Override
	public final void set$Sales(double[][] cv$value) {
		Sales = cv$value;
		setFlag$Sales = true;
		fixedProbFlag$sample86 = false;
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
		fixedProbFlag$sample61 = false;
		fixedProbFlag$sample86 = false;
	}

	@Override
	public final double get$denom() {
		return denom;
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
		fixedProbFlag$sample86 = (cv$value && fixedProbFlag$sample86);
	}

	@Override
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	@Override
	public final void set$fixedFlag$sample53(boolean cv$value) {
		fixedFlag$sample53 = cv$value;
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
		fixedProbFlag$sample61 = (cv$value && fixedProbFlag$sample61);
	}

	@Override
	public final boolean get$fixedFlag$sample61() {
		return fixedFlag$sample61;
	}

	@Override
	public final void set$fixedFlag$sample61(boolean cv$value) {
		fixedFlag$sample61 = cv$value;
		fixedProbFlag$sample61 = (cv$value && fixedProbFlag$sample61);
		fixedProbFlag$sample86 = (cv$value && fixedProbFlag$sample86);
	}

	@Override
	public final boolean get$fixedFlag$sample86() {
		return fixedFlag$sample86;
	}

	@Override
	public final void set$fixedFlag$sample86(boolean cv$value) {
		fixedFlag$sample86 = cv$value;
		fixedProbFlag$sample86 = (cv$value && fixedProbFlag$sample86);
	}

	@Override
	public final double[] get$lambda() {
		return lambda;
	}

	@Override
	public final void set$lambda(double[] cv$value) {
		lambda = cv$value;
		setFlag$lambda = true;
		fixedProbFlag$sample53 = false;
		fixedProbFlag$sample61 = false;
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
	public final double get$logProbability$denom() {
		return logProbability$denom;
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
	public final int get$s() {
		return s;
	}

	@Override
	public final void set$s(int cv$value) {
		s = cv$value;
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
		setFlag$ut = true;
		fixedProbFlag$sample25 = false;
		fixedProbFlag$sample86 = false;
	}

	private final void logProbabilityValue$sample25() {
		if(!fixedProbFlag$sample25) {
			double cv$sampleAccumulator = 0.0;
			for(int var22 = 0; var22 < noProducts; var22 += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((ut[var22] / 3.1622776601683795)) - 1.151292546497023);
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				logProbability$sample25[var22] = cv$distributionAccumulator;
				logProbability$exped = (logProbability$exped + cv$distributionAccumulator);
				logProbability$sum = (logProbability$sum + cv$distributionAccumulator);
				logProbability$denom = (logProbability$denom + cv$distributionAccumulator);
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
				logProbability$sum = (logProbability$sum + cv$sampleValue);
				logProbability$denom = (logProbability$denom + cv$sampleValue);
			}
			logProbability$var18 = cv$rvAccumulator;
			logProbability$ut = (logProbability$ut + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			if(fixedFlag$sample25)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$sampleAccumulator = 0.0;
			for(int var49 = 0; var49 < T; var49 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGamma(lambda[var49], 10.0, 10.0));
			logProbability$var45 = cv$sampleAccumulator;
			logProbability$var50 = cv$sampleAccumulator;
			logProbability$lambda = (logProbability$lambda + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample53 = fixedFlag$sample53;
		} else {
			logProbability$var45 = logProbability$var50;
			logProbability$lambda = (logProbability$lambda + logProbability$var50);
			logProbability$$model = (logProbability$$model + logProbability$var50);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var50);
		}
	}

	private final void logProbabilityValue$sample61() {
		if(!fixedProbFlag$sample61) {
			double cv$accumulator = 0.0;
			for(int t$var55 = 0; t$var55 < T; t$var55 += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityPoisson(arrivals[t$var55], lambda[t$var55]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var57[t$var55] = cv$distributionAccumulator;
				logProbability$sample61[t$var55] = cv$distributionAccumulator;
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample61 = (fixedFlag$sample61 && fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			for(int t$var55 = 0; t$var55 < T; t$var55 += 1) {
				double cv$rvAccumulator = logProbability$sample61[t$var55];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var57[t$var55] = cv$rvAccumulator;
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample86() {
		if(!fixedProbFlag$sample86) {
			double cv$accumulator = 0.0;
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
				for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1) {
					double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - (((exped[j$var72] * Avail[t$var68][j$var72]) / denom) * arrivals[t$var68])) / 0.4472135954999579)) + 0.8047189562170501);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var81[t$var68][j$var72] = cv$distributionAccumulator;
					logProbability$sample86[t$var68][j$var72] = cv$distributionAccumulator;
				}
			}
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample86 = ((fixedFlag$sample86 && fixedFlag$sample25) && fixedFlag$sample61);
		} else {
			double cv$accumulator = 0.0;
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
				for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1) {
					double cv$rvAccumulator = logProbability$sample86[t$var68][j$var72];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var81[t$var68][j$var72] = cv$rvAccumulator;
				}
			}
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
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
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
				for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1)
					guard$sample25gaussian85$global[t$var68][j$var72] = false;
			}
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1)
				guard$sample25gaussian85$global[t$var68][var22] = false;
			double reduceVar$sum$9 = 0.0;
			for(int cv$reduction518Index = 0; cv$reduction518Index < var22; cv$reduction518Index += 1)
				reduceVar$sum$9 = (reduceVar$sum$9 + exped[cv$reduction518Index]);
			for(int cv$reduction518Index = (var22 + 1); cv$reduction518Index < noProducts; cv$reduction518Index += 1)
				reduceVar$sum$9 = (reduceVar$sum$9 + exped[cv$reduction518Index]);
			reduceVar$sum$9 = (Math.exp(cv$originalValue) + reduceVar$sum$9);
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
				for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1) {
					if(!guard$sample25gaussian85$global[t$var68][j$var72]) {
						guard$sample25gaussian85$global[t$var68][j$var72] = true;
						cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - (((exped[j$var72] * Avail[t$var68][j$var72]) / (reduceVar$sum$9 / s)) * arrivals[t$var68])) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
					}
				}
			}
			double traceTempVariable$var73$7_3 = Math.exp(cv$originalValue);
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
				if(!guard$sample25gaussian85$global[t$var68][var22])
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var68][var22] - (((traceTempVariable$var73$7_3 * Avail[t$var68][var22]) / denom) * arrivals[t$var68])) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		ut[var22] = cv$proposedValue;
		exped[var22] = Math.exp(ut[var22]);
		double reduceVar$sum$8 = 0.0;
		for(int cv$reduction461Index = 0; cv$reduction461Index < noProducts; cv$reduction461Index += 1)
			reduceVar$sum$8 = (reduceVar$sum$8 + exped[cv$reduction461Index]);
		sum = reduceVar$sum$8;
		denom = (reduceVar$sum$8 / s);
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
			for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1)
				guard$sample25gaussian85$global[t$var68][j$var72] = false;
		}
		for(int t$var68 = 0; t$var68 < T; t$var68 += 1)
			guard$sample25gaussian85$global[t$var68][var22] = false;
		double reduceVar$sum$9 = 0.0;
		for(int cv$reduction518Index = 0; cv$reduction518Index < var22; cv$reduction518Index += 1)
			reduceVar$sum$9 = (reduceVar$sum$9 + exped[cv$reduction518Index]);
		for(int cv$reduction518Index = (var22 + 1); cv$reduction518Index < noProducts; cv$reduction518Index += 1)
			reduceVar$sum$9 = (reduceVar$sum$9 + exped[cv$reduction518Index]);
		reduceVar$sum$9 = (Math.exp(cv$proposedValue) + reduceVar$sum$9);
		for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
			for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1) {
				if(!guard$sample25gaussian85$global[t$var68][j$var72]) {
					guard$sample25gaussian85$global[t$var68][j$var72] = true;
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - (((exped[j$var72] * Avail[t$var68][j$var72]) / (reduceVar$sum$9 / s)) * arrivals[t$var68])) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
				}
			}
		}
		double traceTempVariable$var73$7_3 = Math.exp(cv$proposedValue);
		for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
			if(!guard$sample25gaussian85$global[t$var68][var22]) {
				guard$sample25gaussian85$global[t$var68][var22] = true;
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var68][var22] - (((traceTempVariable$var73$7_3 * Avail[t$var68][var22]) / denom) * arrivals[t$var68])) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			ut[var22] = cv$originalValue;
			exped[var22] = Math.exp(ut[var22]);
			double reduceVar$sum$10 = 0.0;
			for(int cv$reduction571Index = 0; cv$reduction571Index < noProducts; cv$reduction571Index += 1)
				reduceVar$sum$10 = (reduceVar$sum$10 + exped[cv$reduction571Index]);
			sum = reduceVar$sum$10;
			denom = (reduceVar$sum$10 / s);
		}
	}

	private final void sample53(int var49, int threadID$cv$var49, Rng RNG$) {
		lambda[var49] = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, arrivals[var49], 1);
	}

	private final void sample61(int t$var55, int threadID$cv$t$var55, Rng RNG$) {
		int cv$originalValue = arrivals[t$var55];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 1.0))
			cv$var = 1.0;
		double cv$offset = (Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$));
		cv$offset = ((cv$offset <= 0.0)?(cv$offset - 1):(cv$offset + 1));
		int cv$proposedValue = (cv$originalValue + (int)cv$offset);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityPoisson(cv$originalValue, lambda[t$var55]);
			for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1)
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var55][j$var72] - (((exped[j$var72] * Avail[t$var55][j$var72]) / denom) * cv$originalValue)) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		arrivals[t$var55] = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityPoisson(cv$proposedValue, lambda[t$var55]);
		for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1)
			cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var55][j$var72] - (((exped[j$var72] * Avail[t$var55][j$var72]) / denom) * cv$proposedValue)) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			arrivals[t$var55] = cv$originalValue;
	}

	@Override
	public final void allocateScratch() {
		int cv$max_j$var72 = 0;
		if((0 < T))
			cv$max_j$var72 = Math.max(0, noProducts);
		guard$sample25gaussian85$global = new boolean[Math.max(0, T)][cv$max_j$var72];
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
		if(!setFlag$Sales) {
			Sales = new double[T][];
			for(int var63 = 0; var63 < T; var63 += 1)
				Sales[var63] = new double[noProducts];
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1)
				Sales[t$var68] = new double[noProducts];
		}
		logProbability$sample25 = new double[noProducts];
		logProbability$var57 = new double[T];
		logProbability$sample61 = new double[T];
		logProbability$var81 = new double[T][];
		for(int t$var68 = 0; t$var68 < T; t$var68 += 1)
			logProbability$var81[t$var68] = new double[noProducts];
		logProbability$sample86 = new double[T][];
		for(int t$var68 = 0; t$var68 < T; t$var68 += 1)
			logProbability$sample86[t$var68] = new double[noProducts];
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
			double reduceVar$sum$11 = 0.0;
			for(int cv$reduction38Index = 0; cv$reduction38Index < noProducts; cv$reduction38Index += 1)
				reduceVar$sum$11 = (reduceVar$sum$11 + exped[cv$reduction38Index]);
			sum = reduceVar$sum$11;
			denom = (reduceVar$sum$11 / s);
		}
		if(!fixedFlag$sample53)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1)
							lambda[var49] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		if(!fixedFlag$sample61)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var55, int forEnd$t$var55, int threadID$t$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var55 = forStart$t$var55; t$var55 < forEnd$t$var55; t$var55 += 1)
							arrivals[t$var55] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var55]);
				}
			);

		if(!fixedFlag$sample86)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$index$t$var68, int forEnd$index$t$var68, int threadID$index$t$var68, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t$var68 = forStart$index$t$var68; index$t$var68 < forEnd$index$t$var68; index$t$var68 += 1) {
							int t$var68 = index$t$var68;
							double[] weekly_sales = Sales[t$var68];
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var72, int forEnd$j$var72, int threadID$j$var72, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var72 = forStart$j$var72; j$var72 < forEnd$j$var72; j$var72 += 1)
											weekly_sales[j$var72] = ((DistributionSampling.sampleGaussian(RNG$2) * 0.4472135954999579) + (((exped[j$var72] * Avail[t$var68][j$var72]) / denom) * arrivals[t$var68]));
								}
							);
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
			double reduceVar$sum$13 = 0.0;
			for(int cv$reduction38Index = 0; cv$reduction38Index < noProducts; cv$reduction38Index += 1)
				reduceVar$sum$13 = (reduceVar$sum$13 + exped[cv$reduction38Index]);
			sum = reduceVar$sum$13;
			denom = (reduceVar$sum$13 / s);
		}
		if(!fixedFlag$sample53)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1)
							lambda[var49] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		if(!fixedFlag$sample61)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var55, int forEnd$t$var55, int threadID$t$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var55 = forStart$t$var55; t$var55 < forEnd$t$var55; t$var55 += 1)
							arrivals[t$var55] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var55]);
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
			double reduceVar$sum$12 = 0.0;
			for(int cv$reduction38Index = 0; cv$reduction38Index < noProducts; cv$reduction38Index += 1)
				reduceVar$sum$12 = (reduceVar$sum$12 + exped[cv$reduction38Index]);
			sum = reduceVar$sum$12;
			denom = (reduceVar$sum$12 / s);
		}
		if(!fixedFlag$sample53)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1)
							lambda[var49] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		if(!fixedFlag$sample61)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var55, int forEnd$t$var55, int threadID$t$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var55 = forStart$t$var55; t$var55 < forEnd$t$var55; t$var55 += 1)
							arrivals[t$var55] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var55]);
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
			if(!fixedFlag$sample53)
				parallelFor(RNG$, 0, T, 1,
					(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1)
								sample53(var49, threadID$var49, RNG$1);
					}
				);

			if(!fixedFlag$sample61)
				parallelFor(RNG$, 0, T, 1,
					(int forStart$t$var55, int forEnd$t$var55, int threadID$t$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int t$var55 = forStart$t$var55; t$var55 < forEnd$t$var55; t$var55 += 1)
								sample61(t$var55, threadID$t$var55, RNG$1);
					}
				);

		} else {
			if(!fixedFlag$sample61)
				parallelFor(RNG$, 0, T, 1,
					(int forStart$t$var55, int forEnd$t$var55, int threadID$t$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int t$var55 = forStart$t$var55; t$var55 < forEnd$t$var55; t$var55 += 1)
								sample61(t$var55, threadID$t$var55, RNG$1);
					}
				);

			if(!fixedFlag$sample53)
				parallelFor(RNG$, 0, T, 1,
					(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1)
								sample53(var49, threadID$var49, RNG$1);
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
		logProbability$sum = 0.0;
		logProbability$ut = 0.0;
		logProbability$denom = 0.0;
		logProbability$exped = 0.0;
		if(!fixedProbFlag$sample25) {
			for(int var22 = 0; var22 < noProducts; var22 += 1)
				logProbability$sample25[var22] = 0.0;
		}
		logProbability$var45 = 0.0;
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$var50 = 0.0;
		for(int t$var55 = 0; t$var55 < T; t$var55 += 1)
			logProbability$var57[t$var55] = 0.0;
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample61) {
			for(int t$var55 = 0; t$var55 < T; t$var55 += 1)
				logProbability$sample61[t$var55] = 0.0;
		}
		for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
			for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1)
				logProbability$var81[t$var68][j$var72] = 0.0;
		}
		logProbability$Sales = 0.0;
		if(!fixedProbFlag$sample86) {
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
				for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1)
					logProbability$sample86[t$var68][j$var72] = 0.0;
			}
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
		if(fixedFlag$sample53)
			logProbabilityValue$sample53();
		if(fixedFlag$sample61)
			logProbabilityValue$sample61();
		logProbabilityValue$sample86();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample25();
		logProbabilityValue$sample53();
		logProbabilityValue$sample61();
		logProbabilityValue$sample86();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample25();
		logProbabilityValue$sample53();
		logProbabilityValue$sample61();
		logProbabilityValue$sample86();
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
			double reduceVar$sum$14 = 0.0;
			for(int cv$reduction38Index = 0; cv$reduction38Index < noProducts; cv$reduction38Index += 1)
				reduceVar$sum$14 = (reduceVar$sum$14 + exped[cv$reduction38Index]);
			sum = reduceVar$sum$14;
			denom = (reduceVar$sum$14 / s);
		}
		if(!fixedFlag$sample53)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1)
							lambda[var49] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		if(!fixedFlag$sample61)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var55, int forEnd$t$var55, int threadID$t$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var55 = forStart$t$var55; t$var55 < forEnd$t$var55; t$var55 += 1)
							arrivals[t$var55] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var55]);
				}
			);

		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = Sales.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			double[] cv$source2 = ObsSales[cv$index1];
			double[] cv$target2 = Sales[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	@Override
	public final void setIntermediates() {
		if(setFlag$ut) {
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1)
							exped[j$var28] = Math.exp(ut[j$var28]);
				}
			);
			double reduceVar$sum$15 = 0.0;
			for(int cv$reduction38Index = 0; cv$reduction38Index < noProducts; cv$reduction38Index += 1)
				reduceVar$sum$15 = (reduceVar$sum$15 + exped[cv$reduction38Index]);
			sum = reduceVar$sum$15;
			denom = (reduceVar$sum$15 / s);
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n/*\n * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n */\npackage org.sandwood.compiler.tests.parser;\n\n\nmodel Vulcano2012basic(int noProducts, int T, int s, double[][] ObsSales, int[][] Avail) {\n    // Avail is the availability matrix, T-by-noProducts\n    // s is the normalization constant (market share), number between 0 and 1\n\n    // draw utilities\n    double[] ut = gaussian(0, 10).sample(noProducts);\n\n    //exponentiate right here (in the non-basic models move to the for loop)\n    double[] exped = new double[noProducts];\n    for(int j : [0..noProducts)) {\n    exped[j] = exp(ut[j]);\n    }\n    double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n    double denom = sum/s;   // this is the sum of utilities plus normalized by s outside options\n\n    // priors for the distribution of lambdas (for arrivals). They can be supplied as a vector, or just use some priors\n    double[ ] lambda = gamma(10,10).sample(T);\n\n    // draw arrivals\n    int[] arrivals = new int[T];\n    for (int t : [0..T)){\n    arrivals[t]= poisson(lambda[t]).sample();\n    }\n\n    double[][] Sales = new double[T][noProducts];\n\n    for (int t:[0..T)){\n        // for each period t calculate choice probabilities\n        // (does it matter if choice probabilities or individual choices?)\n        // let's try aggregate first\n\n        double[] weekly_sales = new double[noProducts];\n        for (int j : [0..noProducts)) {\n            weekly_sales[j] = gaussian(exped[j]*Avail[t][j] /denom *arrivals[t], 0.2).sample();\n        }\n        // record sales for period t\n        Sales[t] = weekly_sales;\n                                }\n    // assert that generated sales match observed sales\n    Sales.observe(ObsSales);\n}";
	}
}