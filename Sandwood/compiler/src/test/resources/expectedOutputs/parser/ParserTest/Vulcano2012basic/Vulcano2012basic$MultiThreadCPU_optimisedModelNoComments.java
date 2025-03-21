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
	private boolean fixedFlag$sample22 = false;
	private boolean fixedFlag$sample67 = false;
	private boolean fixedFlag$sample82 = false;
	private boolean fixedProbFlag$sample127 = false;
	private boolean fixedProbFlag$sample22 = false;
	private boolean fixedProbFlag$sample67 = false;
	private boolean fixedProbFlag$sample82 = false;
	private boolean[][] guard$sample22gaussian126$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$Sales;
	private double logProbability$arrivals;
	private double logProbability$denom;
	private double logProbability$exped;
	private double logProbability$lambda;
	private double[][] logProbability$sample127;
	private double[] logProbability$sample22;
	private double[] logProbability$sample82;
	private double logProbability$sum;
	private double logProbability$ut;
	private double logProbability$var10;
	private double[][] logProbability$var124;
	private double logProbability$var54;
	private double logProbability$var66;
	private double[] logProbability$var80;
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
		fixedProbFlag$sample82 = false;
		fixedProbFlag$sample127 = false;
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
	public final boolean get$fixedFlag$sample22() {
		return fixedFlag$sample22;
	}

	@Override
	public final void set$fixedFlag$sample22(boolean cv$value) {
		fixedFlag$sample22 = cv$value;
		fixedProbFlag$sample22 = (cv$value && fixedProbFlag$sample22);
		fixedProbFlag$sample127 = (cv$value && fixedProbFlag$sample127);
	}

	@Override
	public final boolean get$fixedFlag$sample67() {
		return fixedFlag$sample67;
	}

	@Override
	public final void set$fixedFlag$sample67(boolean cv$value) {
		fixedFlag$sample67 = cv$value;
		fixedProbFlag$sample67 = (cv$value && fixedProbFlag$sample67);
		fixedProbFlag$sample82 = (cv$value && fixedProbFlag$sample82);
	}

	@Override
	public final boolean get$fixedFlag$sample82() {
		return fixedFlag$sample82;
	}

	@Override
	public final void set$fixedFlag$sample82(boolean cv$value) {
		fixedFlag$sample82 = cv$value;
		fixedProbFlag$sample82 = (cv$value && fixedProbFlag$sample82);
		fixedProbFlag$sample127 = (cv$value && fixedProbFlag$sample127);
	}

	@Override
	public final double[] get$lambda() {
		return lambda;
	}

	@Override
	public final void set$lambda(double[] cv$value) {
		lambda = cv$value;
		setFlag$lambda = true;
		fixedProbFlag$sample67 = false;
		fixedProbFlag$sample82 = false;
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
		fixedProbFlag$sample22 = false;
		fixedProbFlag$sample127 = false;
	}

	private final void logProbabilityValue$sample127() {
		if(!fixedProbFlag$sample127) {
			double cv$accumulator = 0.0;
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1) {
					double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - (((exped[j$var115] * Avail[t$var105][j$var115]) / denom) * arrivals[t$var105])) / 0.4472135954999579)) + 0.8047189562170501);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var124[t$var105][j$var115] = cv$distributionAccumulator;
					logProbability$sample127[t$var105][j$var115] = cv$distributionAccumulator;
				}
			}
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample127 = (fixedFlag$sample22 && fixedFlag$sample82);
		} else {
			double cv$accumulator = 0.0;
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1) {
					double cv$rvAccumulator = logProbability$sample127[t$var105][j$var115];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var124[t$var105][j$var115] = cv$rvAccumulator;
				}
			}
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
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
				logProbability$sum = (logProbability$sum + cv$distributionAccumulator);
				logProbability$denom = (logProbability$denom + cv$distributionAccumulator);
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
				logProbability$sum = (logProbability$sum + cv$sampleValue);
				logProbability$denom = (logProbability$denom + cv$sampleValue);
			}
			logProbability$var10 = cv$rvAccumulator;
			logProbability$ut = (logProbability$ut + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample67() {
		if(!fixedProbFlag$sample67) {
			double cv$sampleAccumulator = 0.0;
			for(int var65 = 0; var65 < T; var65 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityGamma(lambda[var65], 10.0, 10.0));
			logProbability$var54 = cv$sampleAccumulator;
			logProbability$var66 = cv$sampleAccumulator;
			logProbability$lambda = (logProbability$lambda + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample67)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample67 = fixedFlag$sample67;
		} else {
			logProbability$var54 = logProbability$var66;
			logProbability$lambda = (logProbability$lambda + logProbability$var66);
			logProbability$$model = (logProbability$$model + logProbability$var66);
			if(fixedFlag$sample67)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var66);
		}
	}

	private final void logProbabilityValue$sample82() {
		if(!fixedProbFlag$sample82) {
			double cv$accumulator = 0.0;
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityPoisson(arrivals[t$var78], lambda[t$var78]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var80[t$var78] = cv$distributionAccumulator;
				logProbability$sample82[t$var78] = cv$distributionAccumulator;
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample82)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample82 = (fixedFlag$sample82 && fixedFlag$sample67);
		} else {
			double cv$accumulator = 0.0;
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1) {
				double cv$rvAccumulator = logProbability$sample82[t$var78];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var80[t$var78] = cv$rvAccumulator;
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample82)
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
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1)
					guard$sample22gaussian126$global[t$var105][j$var115] = false;
			}
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
				guard$sample22gaussian126$global[t$var105][var21] = false;
			double reduceVar$sum$9 = 0.0;
			for(int cv$reduction815Index = 0; cv$reduction815Index < var21; cv$reduction815Index += 1)
				reduceVar$sum$9 = (reduceVar$sum$9 + exped[cv$reduction815Index]);
			for(int cv$reduction815Index = (var21 + 1); cv$reduction815Index < noProducts; cv$reduction815Index += 1)
				reduceVar$sum$9 = (reduceVar$sum$9 + exped[cv$reduction815Index]);
			reduceVar$sum$9 = (Math.exp(cv$originalValue) + reduceVar$sum$9);
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1) {
					if(!guard$sample22gaussian126$global[t$var105][j$var115]) {
						guard$sample22gaussian126$global[t$var105][j$var115] = true;
						cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - (((exped[j$var115] * Avail[t$var105][j$var115]) / (reduceVar$sum$9 / s)) * arrivals[t$var105])) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
					}
				}
			}
			double traceTempVariable$var116$7_3 = Math.exp(cv$originalValue);
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				if(!guard$sample22gaussian126$global[t$var105][var21])
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var105][var21] - (((traceTempVariable$var116$7_3 * Avail[t$var105][var21]) / denom) * arrivals[t$var105])) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		ut[var21] = cv$proposedValue;
		exped[var21] = Math.exp(ut[var21]);
		double reduceVar$sum$8 = 0.0;
		for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1)
			reduceVar$sum$8 = (reduceVar$sum$8 + exped[cv$reduction42Index]);
		sum = reduceVar$sum$8;
		denom = (reduceVar$sum$8 / s);
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1)
				guard$sample22gaussian126$global[t$var105][j$var115] = false;
		}
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
			guard$sample22gaussian126$global[t$var105][var21] = false;
		double reduceVar$sum$9 = 0.0;
		for(int cv$reduction815Index = 0; cv$reduction815Index < var21; cv$reduction815Index += 1)
			reduceVar$sum$9 = (reduceVar$sum$9 + exped[cv$reduction815Index]);
		for(int cv$reduction815Index = (var21 + 1); cv$reduction815Index < noProducts; cv$reduction815Index += 1)
			reduceVar$sum$9 = (reduceVar$sum$9 + exped[cv$reduction815Index]);
		reduceVar$sum$9 = (Math.exp(cv$proposedValue) + reduceVar$sum$9);
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1) {
				if(!guard$sample22gaussian126$global[t$var105][j$var115]) {
					guard$sample22gaussian126$global[t$var105][j$var115] = true;
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var105][j$var115] - (((exped[j$var115] * Avail[t$var105][j$var115]) / (reduceVar$sum$9 / s)) * arrivals[t$var105])) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
				}
			}
		}
		double traceTempVariable$var116$7_3 = Math.exp(cv$proposedValue);
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			if(!guard$sample22gaussian126$global[t$var105][var21]) {
				guard$sample22gaussian126$global[t$var105][var21] = true;
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var105][var21] - (((traceTempVariable$var116$7_3 * Avail[t$var105][var21]) / denom) * arrivals[t$var105])) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
			}
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			ut[var21] = cv$originalValue;
			exped[var21] = Math.exp(ut[var21]);
			double reduceVar$sum$10 = 0.0;
			for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1)
				reduceVar$sum$10 = (reduceVar$sum$10 + exped[cv$reduction42Index]);
			sum = reduceVar$sum$10;
			denom = (reduceVar$sum$10 / s);
		}
	}

	private final void sample67(int var65, int threadID$cv$var65, Rng RNG$) {
		lambda[var65] = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, arrivals[var65], 1);
	}

	private final void sample82(int t$var78, int threadID$cv$t$var78, Rng RNG$) {
		int cv$originalValue = arrivals[t$var78];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 1.0))
			cv$var = 1.0;
		double cv$offset = (Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$));
		cv$offset = ((cv$offset <= 0.0)?(cv$offset - 1):(cv$offset + 1));
		int cv$proposedValue = (cv$originalValue + (int)cv$offset);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityPoisson(cv$originalValue, lambda[t$var78]);
			for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1)
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var78][j$var115] - (((exped[j$var115] * Avail[t$var78][j$var115]) / denom) * cv$originalValue)) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		arrivals[t$var78] = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityPoisson(cv$proposedValue, lambda[t$var78]);
		for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1)
			cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((Sales[t$var78][j$var115] - (((exped[j$var115] * Avail[t$var78][j$var115]) / denom) * cv$proposedValue)) / 0.4472135954999579)) + cv$accumulatedProbabilities) + 0.8047189562170501);
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			arrivals[t$var78] = cv$originalValue;
	}

	@Override
	public final void allocateScratch() {
		int cv$max_j$var115 = 0;
		if((0 < T))
			cv$max_j$var115 = Math.max(0, noProducts);
		guard$sample22gaussian126$global = new boolean[Math.max(0, T)][cv$max_j$var115];
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
		Sales = new double[T][];
		for(int var93 = 0; var93 < T; var93 += 1)
			Sales[var93] = new double[noProducts];
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
			Sales[t$var105] = new double[noProducts];
		logProbability$sample22 = new double[noProducts];
		logProbability$var80 = new double[T];
		logProbability$sample82 = new double[T];
		logProbability$var124 = new double[T][];
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
			logProbability$var124[t$var105] = new double[noProducts];
		logProbability$sample127 = new double[T][];
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
			logProbability$sample127[t$var105] = new double[noProducts];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample22) {
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
							ut[var21] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var34, int forEnd$j$var34, int threadID$j$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var34 = forStart$j$var34; j$var34 < forEnd$j$var34; j$var34 += 1)
							exped[j$var34] = Math.exp(ut[j$var34]);
				}
			);
			double reduceVar$sum$11 = 0.0;
			for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1)
				reduceVar$sum$11 = (reduceVar$sum$11 + exped[cv$reduction42Index]);
			sum = reduceVar$sum$11;
			denom = (reduceVar$sum$11 / s);
		}
		if(!fixedFlag$sample67)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var65, int forEnd$var65, int threadID$var65, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var65 = forStart$var65; var65 < forEnd$var65; var65 += 1)
							lambda[var65] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		if(!fixedFlag$sample82)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var78, int forEnd$t$var78, int threadID$t$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var78 = forStart$t$var78; t$var78 < forEnd$t$var78; t$var78 += 1)
							arrivals[t$var78] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var78]);
				}
			);

		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var105, int forEnd$index$t$var105, int threadID$index$t$var105, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var105 = forStart$index$t$var105; index$t$var105 < forEnd$index$t$var105; index$t$var105 += 1) {
						int t$var105 = index$t$var105;
						int threadID$t$var105 = threadID$index$t$var105;
						double[] weekly_sales = Sales[t$var105];
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var115, int forEnd$j$var115, int threadID$j$var115, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var115 = forStart$j$var115; j$var115 < forEnd$j$var115; j$var115 += 1)
										weekly_sales[j$var115] = ((DistributionSampling.sampleGaussian(RNG$2) * 0.4472135954999579) + (((exped[j$var115] * Avail[t$var105][j$var115]) / denom) * arrivals[t$var105]));
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample22) {
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
							ut[var21] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var34, int forEnd$j$var34, int threadID$j$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var34 = forStart$j$var34; j$var34 < forEnd$j$var34; j$var34 += 1)
							exped[j$var34] = Math.exp(ut[j$var34]);
				}
			);
			double reduceVar$sum$13 = 0.0;
			for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1)
				reduceVar$sum$13 = (reduceVar$sum$13 + exped[cv$reduction42Index]);
			sum = reduceVar$sum$13;
			denom = (reduceVar$sum$13 / s);
		}
		if(!fixedFlag$sample67)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var65, int forEnd$var65, int threadID$var65, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var65 = forStart$var65; var65 < forEnd$var65; var65 += 1)
							lambda[var65] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		if(!fixedFlag$sample82)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var78, int forEnd$t$var78, int threadID$t$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var78 = forStart$t$var78; t$var78 < forEnd$t$var78; t$var78 += 1)
							arrivals[t$var78] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var78]);
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample22) {
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
							ut[var21] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var34, int forEnd$j$var34, int threadID$j$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var34 = forStart$j$var34; j$var34 < forEnd$j$var34; j$var34 += 1)
							exped[j$var34] = Math.exp(ut[j$var34]);
				}
			);
			double reduceVar$sum$12 = 0.0;
			for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1)
				reduceVar$sum$12 = (reduceVar$sum$12 + exped[cv$reduction42Index]);
			sum = reduceVar$sum$12;
			denom = (reduceVar$sum$12 / s);
		}
		if(!fixedFlag$sample67)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var65, int forEnd$var65, int threadID$var65, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var65 = forStart$var65; var65 < forEnd$var65; var65 += 1)
							lambda[var65] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		if(!fixedFlag$sample82)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var78, int forEnd$t$var78, int threadID$t$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var78 = forStart$t$var78; t$var78 < forEnd$t$var78; t$var78 += 1)
							arrivals[t$var78] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var78]);
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample22) {
				for(int var21 = 0; var21 < noProducts; var21 += 1)
					sample22(var21);
			}
			if(!fixedFlag$sample67)
				parallelFor(RNG$, 0, T, 1,
					(int forStart$var65, int forEnd$var65, int threadID$var65, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var65 = forStart$var65; var65 < forEnd$var65; var65 += 1)
								sample67(var65, threadID$var65, RNG$1);
					}
				);

			if(!fixedFlag$sample82)
				parallelFor(RNG$, 0, T, 1,
					(int forStart$t$var78, int forEnd$t$var78, int threadID$t$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int t$var78 = forStart$t$var78; t$var78 < forEnd$t$var78; t$var78 += 1)
								sample82(t$var78, threadID$t$var78, RNG$1);
					}
				);

		} else {
			if(!fixedFlag$sample82)
				parallelFor(RNG$, 0, T, 1,
					(int forStart$t$var78, int forEnd$t$var78, int threadID$t$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int t$var78 = forStart$t$var78; t$var78 < forEnd$t$var78; t$var78 += 1)
								sample82(t$var78, threadID$t$var78, RNG$1);
					}
				);

			if(!fixedFlag$sample67)
				parallelFor(RNG$, 0, T, 1,
					(int forStart$var65, int forEnd$var65, int threadID$var65, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var65 = forStart$var65; var65 < forEnd$var65; var65 += 1)
								sample67(var65, threadID$var65, RNG$1);
					}
				);

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
		logProbability$ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$sum = 0.0;
		logProbability$denom = 0.0;
		if(!fixedProbFlag$sample22) {
			for(int var21 = 0; var21 < noProducts; var21 += 1)
				logProbability$sample22[var21] = 0.0;
		}
		logProbability$var54 = 0.0;
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample67)
			logProbability$var66 = 0.0;
		for(int t$var78 = 0; t$var78 < T; t$var78 += 1)
			logProbability$var80[t$var78] = 0.0;
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample82) {
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1)
				logProbability$sample82[t$var78] = 0.0;
		}
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1)
				logProbability$var124[t$var105][j$var115] = 0.0;
		}
		logProbability$Sales = 0.0;
		if(!fixedProbFlag$sample127) {
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				for(int j$var115 = 0; j$var115 < noProducts; j$var115 += 1)
					logProbability$sample127[t$var105][j$var115] = 0.0;
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
		if(fixedFlag$sample22)
			logProbabilityValue$sample22();
		if(fixedFlag$sample67)
			logProbabilityValue$sample67();
		if(fixedFlag$sample82)
			logProbabilityValue$sample82();
		logProbabilityValue$sample127();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample22();
		logProbabilityValue$sample67();
		logProbabilityValue$sample82();
		logProbabilityValue$sample127();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample22();
		logProbabilityValue$sample67();
		logProbabilityValue$sample82();
		logProbabilityValue$sample127();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample22) {
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
							ut[var21] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			parallelFor(RNG$, 0, noProducts, 1,
				(int forStart$j$var34, int forEnd$j$var34, int threadID$j$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var34 = forStart$j$var34; j$var34 < forEnd$j$var34; j$var34 += 1)
							exped[j$var34] = Math.exp(ut[j$var34]);
				}
			);
			double reduceVar$sum$14 = 0.0;
			for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1)
				reduceVar$sum$14 = (reduceVar$sum$14 + exped[cv$reduction42Index]);
			sum = reduceVar$sum$14;
			denom = (reduceVar$sum$14 / s);
		}
		if(!fixedFlag$sample67)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var65, int forEnd$var65, int threadID$var65, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var65 = forStart$var65; var65 < forEnd$var65; var65 += 1)
							lambda[var65] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
				}
			);

		if(!fixedFlag$sample82)
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var78, int forEnd$t$var78, int threadID$t$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var78 = forStart$t$var78; t$var78 < forEnd$t$var78; t$var78 += 1)
							arrivals[t$var78] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var78]);
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
				(int forStart$j$var34, int forEnd$j$var34, int threadID$j$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var34 = forStart$j$var34; j$var34 < forEnd$j$var34; j$var34 += 1)
							exped[j$var34] = Math.exp(ut[j$var34]);
				}
			);
			double reduceVar$sum$15 = 0.0;
			for(int cv$reduction42Index = 0; cv$reduction42Index < noProducts; cv$reduction42Index += 1)
				reduceVar$sum$15 = (reduceVar$sum$15 + exped[cv$reduction42Index]);
			sum = reduceVar$sum$15;
			denom = (reduceVar$sum$15 / s);
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
		     + "\n"
		     + "model Vulcano2012basic(int noProducts, int T, int s, double[][] ObsSales, int[][] Avail) {\n"
		     + "    // Avail is the availability matrix, T-by-noProducts\n"
		     + "    // s is the normalization constant (market share), number between 0 and 1\n"
		     + "\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = gaussian(0, 10).sample(noProducts);\n"
		     + "\n"
		     + "    //exponentiate right here (in the non-basic models move to the for loop)\n"
		     + "    double[] exped = new double[noProducts];\n"
		     + "    for(int j : [0..noProducts)) {\n"
		     + "    exped[j] = exp(ut[j]);\n"
		     + "    }\n"
		     + "    double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n"
		     + "    double denom = sum/s;   // this is the sum of utilities plus normalized by s outside options\n"
		     + "\n"
		     + "    // priors for the distribution of lambdas (for arrivals). They can be supplied as a vector, or just use some priors\n"
		     + "    double[ ] lambda = gamma(10,10).sample(T);\n"
		     + "\n"
		     + "    // draw arrivals\n"
		     + "    int[] arrivals = new int[T];\n"
		     + "    for (int t : [0..T)){\n"
		     + "    arrivals[t]= poisson(lambda[t]).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    double[][] Sales = new double[T][noProducts];\n"
		     + "\n"
		     + "    for (int t:[0..T)){\n"
		     + "        // for each period t calculate choice probabilities\n"
		     + "        // (does it matter if choice probabilities or individual choices?)\n"
		     + "        // let's try aggregate first\n"
		     + "\n"
		     + "        double[] weekly_sales = new double[noProducts];\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            weekly_sales[j] = gaussian(exped[j]*Avail[t][j] /denom *arrivals[t], 0.2).sample();\n"
		     + "        }\n"
		     + "        // record sales for period t\n"
		     + "        Sales[t] = weekly_sales;\n"
		     + "                                }\n"
		     + "    // assert that generated sales match observed sales\n"
		     + "    Sales.observe(ObsSales);\n"
		     + "}";
	}
}