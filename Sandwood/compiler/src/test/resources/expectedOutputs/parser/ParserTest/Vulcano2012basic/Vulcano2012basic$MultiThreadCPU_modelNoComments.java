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
		fixedProbFlag$sample25 = (fixedFlag$sample25 && fixedProbFlag$sample25);
		fixedProbFlag$sample86 = (fixedFlag$sample25 && fixedProbFlag$sample86);
	}

	@Override
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	@Override
	public final void set$fixedFlag$sample53(boolean cv$value) {
		fixedFlag$sample53 = cv$value;
		fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedProbFlag$sample53);
		fixedProbFlag$sample61 = (fixedFlag$sample53 && fixedProbFlag$sample61);
	}

	@Override
	public final boolean get$fixedFlag$sample61() {
		return fixedFlag$sample61;
	}

	@Override
	public final void set$fixedFlag$sample61(boolean cv$value) {
		fixedFlag$sample61 = cv$value;
		fixedProbFlag$sample61 = (fixedFlag$sample61 && fixedProbFlag$sample61);
		fixedProbFlag$sample86 = (fixedFlag$sample61 && fixedProbFlag$sample86);
	}

	@Override
	public final boolean get$fixedFlag$sample86() {
		return fixedFlag$sample86;
	}

	@Override
	public final void set$fixedFlag$sample86(boolean cv$value) {
		fixedFlag$sample86 = cv$value;
		fixedProbFlag$sample86 = (fixedFlag$sample86 && fixedProbFlag$sample86);
	}

	@Override
	public final double[] get$lambda() {
		return lambda;
	}

	@Override
	public final void set$lambda(double[] cv$value) {
		lambda = cv$value;
		setFlag$lambda = true;
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
	}

	private final void logProbabilityValue$sample25() {
		if(!fixedProbFlag$sample25) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var22 = 0; var22 < noProducts; var22 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = ut[var22];
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
				logProbability$sample25[((var22 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$exped = false;
				boolean cv$guard$sum = false;
				boolean cv$guard$denom = false;
				{
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							if(!cv$guard$exped) {
								cv$guard$exped = true;
								logProbability$exped = (logProbability$exped + cv$sampleProbability);
							}
						}
					}
				}
				{
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							if(((0 <= j$var28) && (j$var28 < noProducts))) {
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
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							if(((0 <= j$var28) && (j$var28 < noProducts))) {
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
			if(fixedFlag$sample25)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample25 = fixedFlag$sample25;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var22 = 0; var22 < noProducts; var22 += 1) {
				double cv$sampleValue = logProbability$sample25[((var22 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				boolean cv$guard$exped = false;
				boolean cv$guard$sum = false;
				boolean cv$guard$denom = false;
				{
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							if(!cv$guard$exped) {
								cv$guard$exped = true;
								logProbability$exped = (logProbability$exped + cv$sampleValue);
							}
						}
					}
				}
				{
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							if(((0 <= j$var28) && (j$var28 < noProducts))) {
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
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							if(((0 <= j$var28) && (j$var28 < noProducts))) {
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
			if(fixedFlag$sample25)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var49 = 0; var49 < T; var49 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = lambda[var49];
					{
						{
							double var43 = 10.0;
							double var44 = 10.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGamma(cv$sampleValue, var43, var44));
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
			logProbability$var45 = cv$sampleAccumulator;
			logProbability$var50 = cv$sampleAccumulator;
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample53 = fixedFlag$sample53;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var50;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var45 = cv$rvAccumulator;
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample61() {
		if(!fixedProbFlag$sample61) {
			double cv$accumulator = 0.0;
			for(int t$var55 = 0; t$var55 < T; t$var55 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = arrivals[t$var55];
					{
						{
							double var56 = lambda[t$var55];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$sampleValue, var56));
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
				logProbability$var57[((t$var55 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample61[((t$var55 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample61 = (fixedFlag$sample61 && fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			for(int t$var55 = 0; t$var55 < T; t$var55 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample61[((t$var55 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var57[((t$var55 - 0) / 1)] = cv$rvAccumulator;
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
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						double cv$sampleValue = Sales[t$var68][j$var72];
						{
							{
								double var79 = (((exped[j$var72] * Avail[t$var68][j$var72]) / denom) * arrivals[t$var68]);
								double var80 = 0.2;
								double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var79) / Math.sqrt(var80))) - (0.5 * Math.log(var80))));
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
					logProbability$var81[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample86[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)] = cv$sampleProbability;
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
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample86[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var81[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample25(int var22) {
		double cv$originalValue = ut[var22];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var23 = cv$proposedValue;
					ut[var22] = cv$currentValue;
					{
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								{
									exped[j$var28] = Math.exp(ut[j$var28]);
								}
							}
						}
					}
					{
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								if(((0 <= j$var28) && (j$var28 < noProducts))) {
									{
										{
											double reduceVar$sum$8 = 0.0;
											for(int cv$reduction452Index = 0; cv$reduction452Index < noProducts; cv$reduction452Index += 1) {
												double k = reduceVar$sum$8;
												double l = exped[cv$reduction452Index];
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
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								if(((0 <= j$var28) && (j$var28 < noProducts))) {
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
						boolean[][] guard$sample25gaussian85 = guard$sample25gaussian85$global;
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								if(((0 <= j$var28) && (j$var28 < noProducts))) {
									{
										for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
											for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1)
												guard$sample25gaussian85[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)] = false;
										}
									}
								}
							}
						}
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1) {
									if((j$var28 == j$var72)) {
										for(int t$var68 = 0; t$var68 < T; t$var68 += 1)
											guard$sample25gaussian85[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)] = false;
									}
								}
							}
						}
						double traceTempVariable$var29$6_1 = cv$currentValue;
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								double traceTempVariable$k$6_3 = Math.exp(traceTempVariable$var29$6_1);
								if(((0 <= j$var28) && (j$var28 < noProducts))) {
									{
										if((0 < noProducts)) {
											double reduceVar$sum$9 = 0.0;
											for(int cv$reduction509Index = 0; cv$reduction509Index < j$var28; cv$reduction509Index += 1) {
												double k = reduceVar$sum$9;
												double l = exped[cv$reduction509Index];
												reduceVar$sum$9 = (k + l);
											}
											for(int cv$reduction509Index = (j$var28 + 1); cv$reduction509Index < noProducts; cv$reduction509Index += 1) {
												double k = reduceVar$sum$9;
												double l = exped[cv$reduction509Index];
												reduceVar$sum$9 = (k + l);
											}
											double cv$reduced38 = reduceVar$sum$9;
											reduceVar$sum$9 = (traceTempVariable$k$6_3 + cv$reduced38);
											double traceTempVariable$sum$6_4 = reduceVar$sum$9;
											for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
												for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1) {
													double traceTempVariable$denom$6_7 = (traceTempVariable$sum$6_4 / s);
													if(!guard$sample25gaussian85[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)]) {
														guard$sample25gaussian85[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			double cv$temp$2$var79;
																			{
																				double var79 = (((exped[j$var72] * Avail[t$var68][j$var72]) / traceTempVariable$denom$6_7) * arrivals[t$var68]);
																				cv$temp$2$var79 = var79;
																			}
																			double cv$temp$3$var80;
																			{
																				cv$temp$3$var80 = 0.2;
																			}
																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$2$var79) / Math.sqrt(cv$temp$3$var80))) - (0.5 * Math.log(cv$temp$3$var80)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$2$var79) / Math.sqrt(cv$temp$3$var80))) - (0.5 * Math.log(cv$temp$3$var80)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$2$var79) / Math.sqrt(cv$temp$3$var80))) - (0.5 * Math.log(cv$temp$3$var80))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$2$var79) / Math.sqrt(cv$temp$3$var80))) - (0.5 * Math.log(cv$temp$3$var80)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$2$var79) / Math.sqrt(cv$temp$3$var80))) - (0.5 * Math.log(cv$temp$3$var80)))));
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
						double traceTempVariable$var29$7_1 = cv$currentValue;
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								double traceTempVariable$var73$7_3 = Math.exp(traceTempVariable$var29$7_1);
								for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1) {
									if((j$var28 == j$var72)) {
										for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
											if(!guard$sample25gaussian85[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)]) {
												guard$sample25gaussian85[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	double cv$temp$4$var79;
																	{
																		double var79 = (((traceTempVariable$var73$7_3 * Avail[t$var68][j$var72]) / denom) * arrivals[t$var68]);
																		cv$temp$4$var79 = var79;
																	}
																	double cv$temp$5$var80;
																	{
																		cv$temp$5$var80 = 0.2;
																	}
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$4$var79) / Math.sqrt(cv$temp$5$var80))) - (0.5 * Math.log(cv$temp$5$var80)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$4$var79) / Math.sqrt(cv$temp$5$var80))) - (0.5 * Math.log(cv$temp$5$var80)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$4$var79) / Math.sqrt(cv$temp$5$var80))) - (0.5 * Math.log(cv$temp$5$var80))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$4$var79) / Math.sqrt(cv$temp$5$var80))) - (0.5 * Math.log(cv$temp$5$var80)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$4$var79) / Math.sqrt(cv$temp$5$var80))) - (0.5 * Math.log(cv$temp$5$var80)))));
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
			double var23 = cv$originalValue;
			ut[var22] = var23;
			{
				for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
					if((var22 == j$var28)) {
						{
							exped[j$var28] = Math.exp(ut[j$var28]);
						}
					}
				}
			}
			{
				for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
					if((var22 == j$var28)) {
						if(((0 <= j$var28) && (j$var28 < noProducts))) {
							{
								{
									double reduceVar$sum$10 = 0.0;
									for(int cv$reduction562Index = 0; cv$reduction562Index < noProducts; cv$reduction562Index += 1) {
										double k = reduceVar$sum$10;
										double l = exped[cv$reduction562Index];
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
				for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
					if((var22 == j$var28)) {
						if(((0 <= j$var28) && (j$var28 < noProducts))) {
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

	private final void sample53(int var49, int threadID$cv$var49, Rng RNG$) {
		double cv$sum = 0.0;
		int cv$count = 0;
		{
			{
				{
					for(int t$var55 = 0; t$var55 < T; t$var55 += 1) {
						if((var49 == t$var55)) {
							{
								{
									{
										{
											{
												cv$sum = (cv$sum + arrivals[t$var55]);
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
		double var50 = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, cv$sum, cv$count);
		lambda[var49] = var50;
	}

	private final void sample61(int t$var55, int threadID$cv$t$var55, Rng RNG$) {
		int cv$originalValue = arrivals[t$var55];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < 1.0))
			cv$var = 1.0;
		double cv$offset = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		cv$offset = ((cv$offset <= 0.0)?(cv$offset - 1):(cv$offset + 1));
		int cv$proposedValue = (cv$originalValue + (int)cv$offset);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					int var58 = cv$proposedValue;
					arrivals[t$var55] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var56;
				{
					double var56 = lambda[t$var55];
					cv$temp$0$var56 = var56;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$currentValue, cv$temp$0$var56));
				{
					{
						int traceTempVariable$var78$1_1 = cv$currentValue;
						for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
							if((t$var55 == t$var68)) {
								for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1) {
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double cv$temp$1$var79;
													{
														double var79 = (((exped[j$var72] * Avail[t$var68][j$var72]) / denom) * traceTempVariable$var78$1_1);
														cv$temp$1$var79 = var79;
													}
													double cv$temp$2$var80;
													{
														cv$temp$2$var80 = 0.2;
													}
													if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$1$var79) / Math.sqrt(cv$temp$2$var80))) - (0.5 * Math.log(cv$temp$2$var80)))) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$1$var79) / Math.sqrt(cv$temp$2$var80))) - (0.5 * Math.log(cv$temp$2$var80)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$1$var79) / Math.sqrt(cv$temp$2$var80))) - (0.5 * Math.log(cv$temp$2$var80))));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$1$var79) / Math.sqrt(cv$temp$2$var80))) - (0.5 * Math.log(cv$temp$2$var80)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((Sales[t$var68][j$var72] - cv$temp$1$var79) / Math.sqrt(cv$temp$2$var80))) - (0.5 * Math.log(cv$temp$2$var80)))));
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
			int var58 = cv$originalValue;
			arrivals[t$var55] = var58;
		}
	}

	@Override
	public final void allocateScratch() {
		int cv$max_t$var68 = 0;
		int cv$max_j$var72 = 0;
		for(int t$var68 = 0; t$var68 < T; t$var68 += 1)
			cv$max_j$var72 = Math.max(cv$max_j$var72, ((noProducts - 0) / 1));
		cv$max_t$var68 = Math.max(cv$max_t$var68, ((T - 0) / 1));
		guard$sample25gaussian85$global = new boolean[cv$max_t$var68][cv$max_j$var72];
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
				for(int var63 = 0; var63 < T; var63 += 1)
					Sales[var63] = new double[noProducts];
				for(int t$var68 = 0; t$var68 < T; t$var68 += 1)
					Sales[t$var68] = new double[noProducts];
			}
		}
		{
			logProbability$sample25 = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var57 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample61 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var81 = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1)
				logProbability$var81[((t$var68 - 0) / 1)] = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample86 = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1)
				logProbability$sample86[((t$var68 - 0) / 1)] = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1) {
						if(!fixedFlag$sample25)
							ut[var22] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1) {
						if(!fixedFlag$sample25)
							exped[j$var28] = Math.exp(ut[j$var28]);
					}
			}
		);
		double reduceVar$sum$11 = 0.0;
		for(int cv$reduction38Index = 0; cv$reduction38Index < noProducts; cv$reduction38Index += 1) {
			double k = reduceVar$sum$11;
			double l = exped[cv$reduction38Index];
			if(!fixedFlag$sample25)
				reduceVar$sum$11 = (k + l);
		}
		if(!fixedFlag$sample25)
			sum = reduceVar$sum$11;
		if(!fixedFlag$sample25)
			denom = (sum / s);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1) {
						if(!fixedFlag$sample53)
							lambda[var49] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var55, int forEnd$t$var55, int threadID$t$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int t$var55 = forStart$t$var55; t$var55 < forEnd$t$var55; t$var55 += 1) {
						if(!fixedFlag$sample61)
							arrivals[t$var55] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var55]);
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var68, int forEnd$index$t$var68, int threadID$index$t$var68, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var68 = forStart$index$t$var68; index$t$var68 < forEnd$index$t$var68; index$t$var68 += 1) {
						int t$var68 = index$t$var68;
						double[] weekly_sales = Sales[t$var68];
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var72, int forEnd$j$var72, int threadID$j$var72, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var72 = forStart$j$var72; j$var72 < forEnd$j$var72; j$var72 += 1) {
										if(!fixedFlag$sample86)
											weekly_sales[j$var72] = ((Math.sqrt(0.2) * DistributionSampling.sampleGaussian(RNG$2)) + (((exped[j$var72] * Avail[t$var68][j$var72]) / denom) * arrivals[t$var68]));
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
			(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1) {
						if(!fixedFlag$sample25)
							ut[var22] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1) {
						if(!fixedFlag$sample25)
							exped[j$var28] = Math.exp(ut[j$var28]);
					}
			}
		);
		double reduceVar$sum$13 = 0.0;
		for(int cv$reduction38Index = 0; cv$reduction38Index < noProducts; cv$reduction38Index += 1) {
			double k = reduceVar$sum$13;
			double l = exped[cv$reduction38Index];
			if(!fixedFlag$sample25)
				reduceVar$sum$13 = (k + l);
		}
		if(!fixedFlag$sample25)
			sum = reduceVar$sum$13;
		if(!fixedFlag$sample25)
			denom = (sum / s);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1) {
						if(!fixedFlag$sample53)
							lambda[var49] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var55, int forEnd$t$var55, int threadID$t$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int t$var55 = forStart$t$var55; t$var55 < forEnd$t$var55; t$var55 += 1) {
						if(!fixedFlag$sample61)
							arrivals[t$var55] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var55]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1) {
						if(!fixedFlag$sample25)
							ut[var22] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1) {
						if(!fixedFlag$sample25)
							exped[j$var28] = Math.exp(ut[j$var28]);
					}
			}
		);
		double reduceVar$sum$12 = 0.0;
		for(int cv$reduction38Index = 0; cv$reduction38Index < noProducts; cv$reduction38Index += 1) {
			double k = reduceVar$sum$12;
			double l = exped[cv$reduction38Index];
			if(!fixedFlag$sample25)
				reduceVar$sum$12 = (k + l);
		}
		if(!fixedFlag$sample25)
			sum = reduceVar$sum$12;
		if(!fixedFlag$sample25)
			denom = (sum / s);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1) {
						if(!fixedFlag$sample53)
							lambda[var49] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var55, int forEnd$t$var55, int threadID$t$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int t$var55 = forStart$t$var55; t$var55 < forEnd$t$var55; t$var55 += 1) {
						if(!fixedFlag$sample61)
							arrivals[t$var55] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var55]);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var22 = 0; var22 < noProducts; var22 += 1) {
				if(!fixedFlag$sample25)
					sample25(var22);
			}
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1) {
							if(!fixedFlag$sample53)
								sample53(var49, threadID$var49, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var55, int forEnd$t$var55, int threadID$t$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var55 = forStart$t$var55; t$var55 < forEnd$t$var55; t$var55 += 1) {
							if(!fixedFlag$sample61)
								sample61(t$var55, threadID$t$var55, RNG$1);
						}
				}
			);
		} else {
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var55, int forEnd$t$var55, int threadID$t$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var55 = forStart$t$var55; t$var55 < forEnd$t$var55; t$var55 += 1) {
							if(!fixedFlag$sample61)
								sample61(t$var55, threadID$t$var55, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1) {
							if(!fixedFlag$sample53)
								sample53(var49, threadID$var49, RNG$1);
						}
				}
			);
			for(int var22 = (noProducts - ((((noProducts - 1) - 0) % 1) + 1)); var22 >= ((0 - 1) + 1); var22 -= 1) {
				if(!fixedFlag$sample25)
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
				logProbability$sample25[((var22 - 0) / 1)] = 0.0;
		}
		logProbability$var45 = 0.0;
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$var50 = 0.0;
		for(int t$var55 = 0; t$var55 < T; t$var55 += 1)
			logProbability$var57[((t$var55 - 0) / 1)] = 0.0;
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample61) {
			for(int t$var55 = 0; t$var55 < T; t$var55 += 1)
				logProbability$sample61[((t$var55 - 0) / 1)] = 0.0;
		}
		for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
			for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1)
				logProbability$var81[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)] = 0.0;
		}
		logProbability$Sales = 0.0;
		if(!fixedProbFlag$sample86) {
			for(int t$var68 = 0; t$var68 < T; t$var68 += 1) {
				for(int j$var72 = 0; j$var72 < noProducts; j$var72 += 1)
					logProbability$sample86[((t$var68 - 0) / 1)][((j$var72 - 0) / 1)] = 0.0;
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
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$var22, int forEnd$var22, int threadID$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var22 = forStart$var22; var22 < forEnd$var22; var22 += 1) {
						if(!fixedFlag$sample25)
							ut[var22] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1) {
						if(!fixedFlag$sample25)
							exped[j$var28] = Math.exp(ut[j$var28]);
					}
			}
		);
		double reduceVar$sum$14 = 0.0;
		for(int cv$reduction38Index = 0; cv$reduction38Index < noProducts; cv$reduction38Index += 1) {
			double k = reduceVar$sum$14;
			double l = exped[cv$reduction38Index];
			if(!fixedFlag$sample25)
				reduceVar$sum$14 = (k + l);
		}
		if(!fixedFlag$sample25)
			sum = reduceVar$sum$14;
		if(!fixedFlag$sample25)
			denom = (sum / s);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1) {
						if(!fixedFlag$sample53)
							lambda[var49] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var55, int forEnd$t$var55, int threadID$t$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int t$var55 = forStart$t$var55; t$var55 < forEnd$t$var55; t$var55 += 1) {
						if(!fixedFlag$sample61)
							arrivals[t$var55] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var55]);
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
			(int forStart$j$var28, int forEnd$j$var28, int threadID$j$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var28 = forStart$j$var28; j$var28 < forEnd$j$var28; j$var28 += 1) {
						if(setFlag$ut)
							exped[j$var28] = Math.exp(ut[j$var28]);
					}
			}
		);
		if(setFlag$ut) {
			double reduceVar$sum$15 = 0.0;
			for(int cv$reduction38Index = 0; cv$reduction38Index < noProducts; cv$reduction38Index += 1) {
				double k = reduceVar$sum$15;
				double l = exped[cv$reduction38Index];
				reduceVar$sum$15 = (k + l);
			}
			sum = reduceVar$sum$15;
		}
		if(setFlag$ut)
			denom = (sum / s);
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n/*\n * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n */\npackage org.sandwood.compiler.tests.parser;\n\n\nmodel Vulcano2012basic(int noProducts, int T, int s, double[][] ObsSales, int[][] Avail) {\n    // Avail is the availability matrix, T-by-noProducts\n    // s is the normalization constant (market share), number between 0 and 1\n\n    // draw utilities\n    double[] ut = gaussian(0, 10).sample(noProducts);\n\n    //exponentiate right here (in the non-basic models move to the for loop)\n    double[] exped = new double[noProducts];\n    for(int j : [0..noProducts)) {\n    exped[j] = exp(ut[j]);\n    }\n    double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n    double denom = sum/s;   // this is the sum of utilities plus normalized by s outside options\n\n    // priors for the distribution of lambdas (for arrivals). They can be supplied as a vector, or just use some priors\n    double[ ] lambda = gamma(10,10).sample(T);\n\n    // draw arrivals\n    int[] arrivals = new int[T];\n    for (int t : [0..T)){\n    arrivals[t]= poisson(lambda[t]).sample();\n    }\n\n    double[][] Sales = new double[T][noProducts];\n\n    for (int t:[0..T)){\n        // for each period t calculate choice probabilities\n        // (does it matter if choice probabilities or individual choices?)\n        // let's try aggregate first\n\n        double[] weekly_sales = new double[noProducts];\n        for (int j : [0..noProducts)) {\n            weekly_sales[j] = gaussian(exped[j]*Avail[t][j] /denom *arrivals[t], 0.2).sample();\n        }\n        // record sales for period t\n        Sales[t] = weekly_sales;\n                                }\n    // assert that generated sales match observed sales\n    Sales.observe(ObsSales);\n}";
	}
}