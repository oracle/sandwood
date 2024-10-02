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
	private boolean fixedFlag$sample137 = false;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedFlag$sample77 = false;
	private boolean fixedFlag$sample92 = false;
	private boolean fixedProbFlag$sample137 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean fixedProbFlag$sample77 = false;
	private boolean fixedProbFlag$sample92 = false;
	private boolean[][] guard$sample32gaussian136$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$Sales;
	private double logProbability$arrivals;
	private double logProbability$denom;
	private double logProbability$exped;
	private double logProbability$lambda;
	private double[][] logProbability$sample137;
	private double[] logProbability$sample32;
	private double[] logProbability$sample92;
	private double logProbability$sum;
	private double logProbability$ut;
	private double[][] logProbability$var132;
	private double logProbability$var18;
	private double logProbability$var62;
	private double logProbability$var74;
	private double[] logProbability$var88;
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
		fixedProbFlag$sample137 = false;
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
		fixedProbFlag$sample92 = false;
		fixedProbFlag$sample137 = false;
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
	public final boolean get$fixedFlag$sample137() {
		return fixedFlag$sample137;
	}

	@Override
	public final void set$fixedFlag$sample137(boolean cv$value) {
		fixedFlag$sample137 = cv$value;
		fixedProbFlag$sample137 = (fixedFlag$sample137 && fixedProbFlag$sample137);
	}

	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	@Override
	public final void set$fixedFlag$sample32(boolean cv$value) {
		fixedFlag$sample32 = cv$value;
		fixedProbFlag$sample32 = (fixedFlag$sample32 && fixedProbFlag$sample32);
		fixedProbFlag$sample137 = (fixedFlag$sample32 && fixedProbFlag$sample137);
	}

	@Override
	public final boolean get$fixedFlag$sample77() {
		return fixedFlag$sample77;
	}

	@Override
	public final void set$fixedFlag$sample77(boolean cv$value) {
		fixedFlag$sample77 = cv$value;
		fixedProbFlag$sample77 = (fixedFlag$sample77 && fixedProbFlag$sample77);
		fixedProbFlag$sample92 = (fixedFlag$sample77 && fixedProbFlag$sample92);
	}

	@Override
	public final boolean get$fixedFlag$sample92() {
		return fixedFlag$sample92;
	}

	@Override
	public final void set$fixedFlag$sample92(boolean cv$value) {
		fixedFlag$sample92 = cv$value;
		fixedProbFlag$sample92 = (fixedFlag$sample92 && fixedProbFlag$sample92);
		fixedProbFlag$sample137 = (fixedFlag$sample92 && fixedProbFlag$sample137);
	}

	@Override
	public final double[] get$lambda() {
		return lambda;
	}

	@Override
	public final void set$lambda(double[] cv$value) {
		lambda = cv$value;
		setFlag$lambda = true;
		fixedProbFlag$sample77 = false;
		fixedProbFlag$sample92 = false;
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
		fixedProbFlag$sample32 = false;
		fixedProbFlag$sample137 = false;
	}

	private final void logProbabilityValue$sample137() {
		if(!fixedProbFlag$sample137) {
			double cv$accumulator = 0.0;
			for(int t$var113 = 0; t$var113 < T; t$var113 += 1) {
				for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						double cv$sampleValue = Sales[t$var113][j$var123];
						{
							{
								double var130 = (((exped[j$var123] * Avail[t$var113][j$var123]) / denom) * arrivals[t$var113]);
								double var131 = 0.2;
								double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var130) / Math.sqrt(var131))) - (0.5 * Math.log(var131))));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$var132[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample137[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample137 = ((fixedFlag$sample137 && fixedFlag$sample32) && fixedFlag$sample92);
		} else {
			double cv$accumulator = 0.0;
			for(int t$var113 = 0; t$var113 < T; t$var113 += 1) {
				for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample137[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var132[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample32() {
		if(!fixedProbFlag$sample32) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var29 = 0; var29 < noProducts; var29 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = ut[var29];
					{
						{
							double var16 = 0.0;
							double var17 = 10.0;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var16) / Math.sqrt(var17))) - (0.5 * Math.log(var17))));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				logProbability$sample32[((var29 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$exped = false;
				boolean cv$guard$sum = false;
				boolean cv$guard$denom = false;
				{
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
							if(!cv$guard$exped) {
								cv$guard$exped = true;
								logProbability$exped = (logProbability$exped + cv$sampleProbability);
							}
						}
					}
				}
				{
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
							if(((0 <= j$var42) && (j$var42 < noProducts))) {
								{
									if(!cv$guard$sum) {
										cv$guard$sum = true;
										logProbability$sum = (logProbability$sum + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
				{
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
							if(((0 <= j$var42) && (j$var42 < noProducts))) {
								{
									if(!cv$guard$denom) {
										cv$guard$denom = true;
										logProbability$denom = (logProbability$denom + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var18 = cv$sampleAccumulator;
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample32 = fixedFlag$sample32;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var29 = 0; var29 < noProducts; var29 += 1) {
				double cv$sampleValue = logProbability$sample32[((var29 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				boolean cv$guard$exped = false;
				boolean cv$guard$sum = false;
				boolean cv$guard$denom = false;
				{
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
							if(!cv$guard$exped) {
								cv$guard$exped = true;
								logProbability$exped = (logProbability$exped + cv$sampleValue);
							}
						}
					}
				}
				{
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
							if(((0 <= j$var42) && (j$var42 < noProducts))) {
								{
									if(!cv$guard$sum) {
										cv$guard$sum = true;
										logProbability$sum = (logProbability$sum + cv$sampleValue);
									}
								}
							}
						}
					}
				}
				{
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
							if(((0 <= j$var42) && (j$var42 < noProducts))) {
								{
									if(!cv$guard$denom) {
										cv$guard$denom = true;
										logProbability$denom = (logProbability$denom + cv$sampleValue);
									}
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var18 = cv$rvAccumulator;
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample77() {
		if(!fixedProbFlag$sample77) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var73 = 0; var73 < T; var73 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = lambda[var73];
					{
						{
							double var60 = 10.0;
							double var61 = 10.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGamma(cv$sampleValue, var60, var61));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var62 = cv$sampleAccumulator;
			logProbability$var74 = cv$sampleAccumulator;
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample77)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample77 = fixedFlag$sample77;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var74;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var62 = cv$rvAccumulator;
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample77)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample92() {
		if(!fixedProbFlag$sample92) {
			double cv$accumulator = 0.0;
			for(int t$var86 = 0; t$var86 < T; t$var86 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = arrivals[t$var86];
					{
						{
							double var87 = lambda[t$var86];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$sampleValue, var87));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var88[((t$var86 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample92[((t$var86 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample92)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample92 = (fixedFlag$sample92 && fixedFlag$sample77);
		} else {
			double cv$accumulator = 0.0;
			for(int t$var86 = 0; t$var86 < T; t$var86 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample92[((t$var86 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var88[((t$var86 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample92)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample32(int var29) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = ut[var29];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var30 = cv$proposedValue;
					ut[var29] = cv$currentValue;
					{
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								{
									exped[j$var42] = Math.exp(ut[j$var42]);
								}
							}
						}
					}
					{
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								if(((0 <= j$var42) && (j$var42 < noProducts))) {
									{
										{
											double reduceVar$sum$8 = 0.0;
											for(int cv$reduction52Index = 0; cv$reduction52Index < noProducts; cv$reduction52Index += 1) {
												double k = reduceVar$sum$8;
												double l = exped[cv$reduction52Index];
												reduceVar$sum$8 = (k + l);
											}
											sum = reduceVar$sum$8;
										}
									}
								}
							}
						}
					}
					{
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								if(((0 <= j$var42) && (j$var42 < noProducts))) {
									{
										{
											denom = (sum / s);
										}
									}
								}
							}
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var16;
				{
					cv$temp$0$var16 = 0.0;
				}
				double cv$temp$1$var17;
				{
					cv$temp$1$var17 = 10.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var16) / Math.sqrt(cv$temp$1$var17))) - (0.5 * Math.log(cv$temp$1$var17))));
				{
					{
						boolean[][] guard$sample32gaussian136 = guard$sample32gaussian136$global;
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								if(((0 <= j$var42) && (j$var42 < noProducts))) {
									{
										for(int t$var113 = 0; t$var113 < T; t$var113 += 1) {
											for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1)
												guard$sample32gaussian136[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)] = false;
										}
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
									if((j$var42 == j$var123)) {
										for(int t$var113 = 0; t$var113 < T; t$var113 += 1)
											guard$sample32gaussian136[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)] = false;
									}
								}
							}
						}
						double traceTempVariable$var43$6_1 = cv$currentValue;
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								double traceTempVariable$k$6_3 = Math.exp(traceTempVariable$var43$6_1);
								if(((0 <= j$var42) && (j$var42 < noProducts))) {
									{
										if((0 < noProducts)) {
											double reduceVar$sum$9 = 0.0;
											for(int cv$reduction825Index = 0; cv$reduction825Index < j$var42; cv$reduction825Index += 1) {
												double k = reduceVar$sum$9;
												double l = exped[cv$reduction825Index];
												reduceVar$sum$9 = (k + l);
											}
											for(int cv$reduction825Index = (j$var42 + 1); cv$reduction825Index < noProducts; cv$reduction825Index += 1) {
												double k = reduceVar$sum$9;
												double l = exped[cv$reduction825Index];
												reduceVar$sum$9 = (k + l);
											}
											double cv$reduced52 = reduceVar$sum$9;
											reduceVar$sum$9 = (traceTempVariable$k$6_3 + cv$reduced52);
											double traceTempVariable$sum$6_4 = reduceVar$sum$9;
											for(int t$var113 = 0; t$var113 < T; t$var113 += 1) {
												for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
													double traceTempVariable$denom$6_7 = (traceTempVariable$sum$6_4 / s);
													if(!guard$sample32gaussian136[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)]) {
														guard$sample32gaussian136[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			double cv$temp$2$var130;
																			{
																				double var130 = (((exped[j$var123] * Avail[t$var113][j$var123]) / traceTempVariable$denom$6_7) * arrivals[t$var113]);
																				cv$temp$2$var130 = var130;
																			}
																			double cv$temp$3$var131;
																			{
																				cv$temp$3$var131 = 0.2;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$2$var130) / Math.sqrt(cv$temp$3$var131))) - (0.5 * Math.log(cv$temp$3$var131)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$2$var130) / Math.sqrt(cv$temp$3$var131))) - (0.5 * Math.log(cv$temp$3$var131)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$2$var130) / Math.sqrt(cv$temp$3$var131))) - (0.5 * Math.log(cv$temp$3$var131))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$2$var130) / Math.sqrt(cv$temp$3$var131))) - (0.5 * Math.log(cv$temp$3$var131)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$2$var130) / Math.sqrt(cv$temp$3$var131))) - (0.5 * Math.log(cv$temp$3$var131)))));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
															cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
															if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
																else
																	cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						double traceTempVariable$var43$7_1 = cv$currentValue;
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								double traceTempVariable$var124$7_3 = Math.exp(traceTempVariable$var43$7_1);
								for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
									if((j$var42 == j$var123)) {
										for(int t$var113 = 0; t$var113 < T; t$var113 += 1) {
											if(!guard$sample32gaussian136[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)]) {
												guard$sample32gaussian136[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	double cv$temp$4$var130;
																	{
																		double var130 = (((traceTempVariable$var124$7_3 * Avail[t$var113][j$var123]) / denom) * arrivals[t$var113]);
																		cv$temp$4$var130 = var130;
																	}
																	double cv$temp$5$var131;
																	{
																		cv$temp$5$var131 = 0.2;
																	}
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$4$var130) / Math.sqrt(cv$temp$5$var131))) - (0.5 * Math.log(cv$temp$5$var131)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$4$var130) / Math.sqrt(cv$temp$5$var131))) - (0.5 * Math.log(cv$temp$5$var131)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$4$var130) / Math.sqrt(cv$temp$5$var131))) - (0.5 * Math.log(cv$temp$5$var131))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$4$var130) / Math.sqrt(cv$temp$5$var131))) - (0.5 * Math.log(cv$temp$5$var131)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$4$var130) / Math.sqrt(cv$temp$5$var131))) - (0.5 * Math.log(cv$temp$5$var131)))));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
													cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
													if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
														else
															cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
					cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
				else {
					if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					else
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
				}
			}
			if((cv$valuePos == 0))
				cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			else
				cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		double cv$ratio = (cv$proposedProbability - cv$originalProbability);
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
			double var30 = cv$originalValue;
			ut[var29] = var30;
			{
				for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
					if((var29 == j$var42)) {
						{
							exped[j$var42] = Math.exp(ut[j$var42]);
						}
					}
				}
			}
			{
				for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
					if((var29 == j$var42)) {
						if(((0 <= j$var42) && (j$var42 < noProducts))) {
							{
								{
									double reduceVar$sum$10 = 0.0;
									for(int cv$reduction52Index = 0; cv$reduction52Index < noProducts; cv$reduction52Index += 1) {
										double k = reduceVar$sum$10;
										double l = exped[cv$reduction52Index];
										reduceVar$sum$10 = (k + l);
									}
									sum = reduceVar$sum$10;
								}
							}
						}
					}
				}
			}
			{
				for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
					if((var29 == j$var42)) {
						if(((0 <= j$var42) && (j$var42 < noProducts))) {
							{
								{
									denom = (sum / s);
								}
							}
						}
					}
				}
			}
		}
	}

	private final void sample77(int var73, int threadID$cv$var73, Rng RNG$) {
		double cv$sum = 0.0;
		int cv$count = 0;
		{
			{
				{
					for(int t$var86 = 0; t$var86 < T; t$var86 += 1) {
						if((var73 == t$var86)) {
							{
								{
									{
										{
											{
												cv$sum = (cv$sum + arrivals[t$var86]);
												cv$count = (cv$count + 1);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		double var74 = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, cv$sum, cv$count);
		lambda[var73] = var74;
	}

	private final void sample92(int t$var86, int threadID$cv$t$var86, Rng RNG$) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		int cv$originalValue = arrivals[t$var86];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < 1.0))
			cv$var = 1.0;
		double cv$offset = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		cv$offset = ((cv$offset <= 0.0)?(cv$offset - 1):(cv$offset + 1));
		int cv$proposedValue = (cv$originalValue + (int)cv$offset);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					int var89 = cv$proposedValue;
					arrivals[t$var86] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var87;
				{
					double var87 = lambda[t$var86];
					cv$temp$0$var87 = var87;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$currentValue, cv$temp$0$var87));
				{
					{
						int traceTempVariable$var129$1_1 = cv$currentValue;
						for(int t$var113 = 0; t$var113 < T; t$var113 += 1) {
							if((t$var86 == t$var113)) {
								for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double cv$temp$1$var130;
													{
														double var130 = (((exped[j$var123] * Avail[t$var113][j$var123]) / denom) * traceTempVariable$var129$1_1);
														cv$temp$1$var130 = var130;
													}
													double cv$temp$2$var131;
													{
														cv$temp$2$var131 = 0.2;
													}
													if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$1$var130) / Math.sqrt(cv$temp$2$var131))) - (0.5 * Math.log(cv$temp$2$var131)))) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$1$var130) / Math.sqrt(cv$temp$2$var131))) - (0.5 * Math.log(cv$temp$2$var131)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$1$var130) / Math.sqrt(cv$temp$2$var131))) - (0.5 * Math.log(cv$temp$2$var131))));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$1$var130) / Math.sqrt(cv$temp$2$var131))) - (0.5 * Math.log(cv$temp$2$var131)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var113][j$var123] - cv$temp$1$var130) / Math.sqrt(cv$temp$2$var131))) - (0.5 * Math.log(cv$temp$2$var131)))));
													}
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
												}
											}
										}
									}
									cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
									if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
										else
											cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
									}
								}
							}
						}
					}
				}
				if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
					cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
				else {
					if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					else
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
				}
			}
			if((cv$valuePos == 0))
				cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			else
				cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		double cv$ratio = (cv$proposedProbability - cv$originalProbability);
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
			int var89 = cv$originalValue;
			arrivals[t$var86] = var89;
		}
	}

	@Override
	public final void allocateScratch() {
		int cv$max_t$var113 = 0;
		int cv$max_j$var123 = 0;
		for(int t$var113 = 0; t$var113 < T; t$var113 += 1)
			cv$max_j$var123 = Math.max(cv$max_j$var123, ((noProducts - 0) / 1));
		cv$max_t$var113 = Math.max(cv$max_t$var113, ((T - 0) / 1));
		guard$sample32gaussian136$global = new boolean[cv$max_t$var113][cv$max_j$var123];
	}

	@Override
	public final void allocator() {
		if(!setFlag$ut) {
			{
				ut = new double[noProducts];
			}
		}
		{
			exped = new double[noProducts];
		}
		if(!setFlag$lambda) {
			{
				lambda = new double[T];
			}
		}
		if(!setFlag$arrivals) {
			{
				arrivals = new int[T];
			}
		}
		if(!setFlag$Sales) {
			{
				Sales = new double[T][];
				for(int var101 = 0; var101 < T; var101 += 1)
					Sales[var101] = new double[noProducts];
				for(int t$var113 = 0; t$var113 < T; t$var113 += 1)
					Sales[t$var113] = new double[noProducts];
			}
		}
		{
			logProbability$sample32 = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var88 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample92 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var132 = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var113 = 0; t$var113 < T; t$var113 += 1)
				logProbability$var132[((t$var113 - 0) / 1)] = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample137 = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var113 = 0; t$var113 < T; t$var113 += 1)
				logProbability$sample137[((t$var113 - 0) / 1)] = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						if(!fixedFlag$sample32)
							ut[var29] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1) {
						if(!fixedFlag$sample32)
							exped[j$var42] = Math.exp(ut[j$var42]);
					}
			}
		);
		double reduceVar$sum$11 = 0.0;
		for(int cv$reduction52Index = 0; cv$reduction52Index < noProducts; cv$reduction52Index += 1) {
			double k = reduceVar$sum$11;
			double l = exped[cv$reduction52Index];
			if(!fixedFlag$sample32)
				reduceVar$sum$11 = (k + l);
		}
		if(!fixedFlag$sample32)
			sum = reduceVar$sum$11;
		if(!fixedFlag$sample32)
			denom = (sum / s);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var73, int forEnd$var73, int threadID$var73, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var73 = forStart$var73; var73 < forEnd$var73; var73 += 1) {
						if(!fixedFlag$sample77)
							lambda[var73] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var86, int forEnd$t$var86, int threadID$t$var86, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int t$var86 = forStart$t$var86; t$var86 < forEnd$t$var86; t$var86 += 1) {
						if(!fixedFlag$sample92)
							arrivals[t$var86] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var86]);
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var113, int forEnd$index$t$var113, int threadID$index$t$var113, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var113 = forStart$index$t$var113; index$t$var113 < forEnd$index$t$var113; index$t$var113 += 1) {
						int t$var113 = index$t$var113;
						int threadID$t$var113 = threadID$index$t$var113;
						double[] weekly_sales = Sales[t$var113];
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1) {
										if(!fixedFlag$sample137)
											weekly_sales[j$var123] = ((Math.sqrt(0.2) * DistributionSampling.sampleGaussian(RNG$2)) + (((exped[j$var123] * Avail[t$var113][j$var123]) / denom) * arrivals[t$var113]));
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						if(!fixedFlag$sample32)
							ut[var29] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1) {
						if(!fixedFlag$sample32)
							exped[j$var42] = Math.exp(ut[j$var42]);
					}
			}
		);
		double reduceVar$sum$13 = 0.0;
		for(int cv$reduction52Index = 0; cv$reduction52Index < noProducts; cv$reduction52Index += 1) {
			double k = reduceVar$sum$13;
			double l = exped[cv$reduction52Index];
			if(!fixedFlag$sample32)
				reduceVar$sum$13 = (k + l);
		}
		if(!fixedFlag$sample32)
			sum = reduceVar$sum$13;
		if(!fixedFlag$sample32)
			denom = (sum / s);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var73, int forEnd$var73, int threadID$var73, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var73 = forStart$var73; var73 < forEnd$var73; var73 += 1) {
						if(!fixedFlag$sample77)
							lambda[var73] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var86, int forEnd$t$var86, int threadID$t$var86, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int t$var86 = forStart$t$var86; t$var86 < forEnd$t$var86; t$var86 += 1) {
						if(!fixedFlag$sample92)
							arrivals[t$var86] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var86]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						if(!fixedFlag$sample32)
							ut[var29] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1) {
						if(!fixedFlag$sample32)
							exped[j$var42] = Math.exp(ut[j$var42]);
					}
			}
		);
		double reduceVar$sum$12 = 0.0;
		for(int cv$reduction52Index = 0; cv$reduction52Index < noProducts; cv$reduction52Index += 1) {
			double k = reduceVar$sum$12;
			double l = exped[cv$reduction52Index];
			if(!fixedFlag$sample32)
				reduceVar$sum$12 = (k + l);
		}
		if(!fixedFlag$sample32)
			sum = reduceVar$sum$12;
		if(!fixedFlag$sample32)
			denom = (sum / s);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var73, int forEnd$var73, int threadID$var73, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var73 = forStart$var73; var73 < forEnd$var73; var73 += 1) {
						if(!fixedFlag$sample77)
							lambda[var73] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var86, int forEnd$t$var86, int threadID$t$var86, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int t$var86 = forStart$t$var86; t$var86 < forEnd$t$var86; t$var86 += 1) {
						if(!fixedFlag$sample92)
							arrivals[t$var86] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var86]);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var29 = 0; var29 < noProducts; var29 += 1) {
				if(!fixedFlag$sample32)
					sample32(var29);
			}
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var73, int forEnd$var73, int threadID$var73, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var73 = forStart$var73; var73 < forEnd$var73; var73 += 1) {
							if(!fixedFlag$sample77)
								sample77(var73, threadID$var73, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var86, int forEnd$t$var86, int threadID$t$var86, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var86 = forStart$t$var86; t$var86 < forEnd$t$var86; t$var86 += 1) {
							if(!fixedFlag$sample92)
								sample92(t$var86, threadID$t$var86, RNG$1);
						}
				}
			);
		} else {
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var86, int forEnd$t$var86, int threadID$t$var86, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var86 = forStart$t$var86; t$var86 < forEnd$t$var86; t$var86 += 1) {
							if(!fixedFlag$sample92)
								sample92(t$var86, threadID$t$var86, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var73, int forEnd$var73, int threadID$var73, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var73 = forStart$var73; var73 < forEnd$var73; var73 += 1) {
							if(!fixedFlag$sample77)
								sample77(var73, threadID$var73, RNG$1);
						}
				}
			);
			for(int var29 = (noProducts - ((((noProducts - 1) - 0) % 1) + 1)); var29 >= ((0 - 1) + 1); var29 -= 1) {
				if(!fixedFlag$sample32)
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
		logProbability$sum = 0.0;
		logProbability$denom = 0.0;
		logProbability$exped = 0.0;
		logProbability$ut = 0.0;
		if(!fixedProbFlag$sample32) {
			for(int var29 = 0; var29 < noProducts; var29 += 1)
				logProbability$sample32[((var29 - 0) / 1)] = 0.0;
		}
		logProbability$var62 = 0.0;
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample77)
			logProbability$var74 = 0.0;
		for(int t$var86 = 0; t$var86 < T; t$var86 += 1)
			logProbability$var88[((t$var86 - 0) / 1)] = 0.0;
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample92) {
			for(int t$var86 = 0; t$var86 < T; t$var86 += 1)
				logProbability$sample92[((t$var86 - 0) / 1)] = 0.0;
		}
		for(int t$var113 = 0; t$var113 < T; t$var113 += 1) {
			for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1)
				logProbability$var132[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)] = 0.0;
		}
		logProbability$Sales = 0.0;
		if(!fixedProbFlag$sample137) {
			for(int t$var113 = 0; t$var113 < T; t$var113 += 1) {
				for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1)
					logProbability$sample137[((t$var113 - 0) / 1)][((j$var123 - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(fixedFlag$sample77)
			logProbabilityValue$sample77();
		if(fixedFlag$sample92)
			logProbabilityValue$sample92();
		logProbabilityValue$sample137();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample32();
		logProbabilityValue$sample77();
		logProbabilityValue$sample92();
		logProbabilityValue$sample137();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample32();
		logProbabilityValue$sample77();
		logProbabilityValue$sample92();
		logProbabilityValue$sample137();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						if(!fixedFlag$sample32)
							ut[var29] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1) {
						if(!fixedFlag$sample32)
							exped[j$var42] = Math.exp(ut[j$var42]);
					}
			}
		);
		double reduceVar$sum$14 = 0.0;
		for(int cv$reduction52Index = 0; cv$reduction52Index < noProducts; cv$reduction52Index += 1) {
			double k = reduceVar$sum$14;
			double l = exped[cv$reduction52Index];
			if(!fixedFlag$sample32)
				reduceVar$sum$14 = (k + l);
		}
		if(!fixedFlag$sample32)
			sum = reduceVar$sum$14;
		if(!fixedFlag$sample32)
			denom = (sum / s);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var73, int forEnd$var73, int threadID$var73, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var73 = forStart$var73; var73 < forEnd$var73; var73 += 1) {
						if(!fixedFlag$sample77)
							lambda[var73] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var86, int forEnd$t$var86, int threadID$t$var86, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int t$var86 = forStart$t$var86; t$var86 < forEnd$t$var86; t$var86 += 1) {
						if(!fixedFlag$sample92)
							arrivals[t$var86] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var86]);
					}
			}
		);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		double[][] cv$source1 = ObsSales;
		double[][] cv$target1 = Sales;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			double[] cv$source2 = cv$source1[cv$index1];
			double[] cv$target2 = cv$target1[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	@Override
	public final void setIntermediates() {
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1) {
						if(setFlag$ut)
							exped[j$var42] = Math.exp(ut[j$var42]);
					}
			}
		);
		if(setFlag$ut) {
			double reduceVar$sum$15 = 0.0;
			for(int cv$reduction52Index = 0; cv$reduction52Index < noProducts; cv$reduction52Index += 1) {
				double k = reduceVar$sum$15;
				double l = exped[cv$reduction52Index];
				reduceVar$sum$15 = (k + l);
			}
			sum = reduceVar$sum$15;
		}
		if(setFlag$ut)
			denom = (sum / s);
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