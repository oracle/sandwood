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
		fixedProbFlag$sample25 = (fixedFlag$sample25 && fixedProbFlag$sample25);
		fixedProbFlag$sample98 = (fixedFlag$sample25 && fixedProbFlag$sample98);
	}

	@Override
	public final boolean get$fixedFlag$sample43() {
		return fixedFlag$sample43;
	}

	@Override
	public final void set$fixedFlag$sample43(boolean cv$value) {
		fixedFlag$sample43 = cv$value;
		fixedProbFlag$sample43 = (fixedFlag$sample43 && fixedProbFlag$sample43);
		fixedProbFlag$sample51 = (fixedFlag$sample43 && fixedProbFlag$sample51);
	}

	@Override
	public final boolean get$fixedFlag$sample51() {
		return fixedFlag$sample51;
	}

	@Override
	public final void set$fixedFlag$sample51(boolean cv$value) {
		fixedFlag$sample51 = cv$value;
		fixedProbFlag$sample51 = (fixedFlag$sample51 && fixedProbFlag$sample51);
		fixedProbFlag$sample98 = (fixedFlag$sample51 && fixedProbFlag$sample98);
	}

	@Override
	public final boolean get$fixedFlag$sample98() {
		return fixedFlag$sample98;
	}

	@Override
	public final void set$fixedFlag$sample98(boolean cv$value) {
		fixedFlag$sample98 = cv$value;
		fixedProbFlag$sample98 = (fixedFlag$sample98 && fixedProbFlag$sample98);
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
				boolean cv$guard$weekly_ut = false;
				boolean cv$guard$weekly_rates = false;
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
							for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
								if((j$var28 == j$var68)) {
									for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
										if(!cv$guard$weekly_ut) {
											cv$guard$weekly_ut = true;
											logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
				}
				{
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
								if((j$var28 == j$var68)) {
									for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
										if(((0 <= j$var68) && (j$var68 < (noProducts + 1)))) {
											{
												for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
													if(!cv$guard$weekly_rates) {
														cv$guard$weekly_rates = true;
														logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleProbability);
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
								if((j$var28 == j$var68)) {
									for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
										if((j$var68 == j$var88)) {
											for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
												if(!cv$guard$weekly_rates) {
													cv$guard$weekly_rates = true;
													logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleProbability);
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
				boolean cv$guard$weekly_ut = false;
				boolean cv$guard$weekly_rates = false;
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
							for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
								if((j$var28 == j$var68)) {
									for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
										if(!cv$guard$weekly_ut) {
											cv$guard$weekly_ut = true;
											logProbability$weekly_ut = (logProbability$weekly_ut + cv$sampleValue);
										}
									}
								}
							}
						}
					}
				}
				{
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
								if((j$var28 == j$var68)) {
									for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
										if(((0 <= j$var68) && (j$var68 < (noProducts + 1)))) {
											{
												for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
													if(!cv$guard$weekly_rates) {
														cv$guard$weekly_rates = true;
														logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
						if((var22 == j$var28)) {
							for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
								if((j$var28 == j$var68)) {
									for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
										if((j$var68 == j$var88)) {
											for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
												if(!cv$guard$weekly_rates) {
													cv$guard$weekly_rates = true;
													logProbability$weekly_rates = (logProbability$weekly_rates + cv$sampleValue);
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
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var18 = cv$rvAccumulator;
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample25)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample43() {
		if(!fixedProbFlag$sample43) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var40 = 0; var40 < T; var40 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = lambda[var40];
					{
						{
							double var34 = 10.0;
							double var35 = 10.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGamma(cv$sampleValue, var34, var35));
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
			logProbability$var36 = cv$sampleAccumulator;
			logProbability$var41 = cv$sampleAccumulator;
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample43)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample43 = fixedFlag$sample43;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var41;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var36 = cv$rvAccumulator;
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample43)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample51() {
		if(!fixedProbFlag$sample51) {
			double cv$accumulator = 0.0;
			for(int t$var46 = 0; t$var46 < T; t$var46 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = arrivals[t$var46];
					{
						{
							double var47 = lambda[t$var46];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$sampleValue, var47));
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
				logProbability$var48[((t$var46 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample51[((t$var46 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample51 = (fixedFlag$sample51 && fixedFlag$sample43);
		} else {
			double cv$accumulator = 0.0;
			for(int t$var46 = 0; t$var46 < T; t$var46 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample51[((t$var46 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var48[((t$var46 - 0) / 1)] = cv$rvAccumulator;
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
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int[] cv$sampleValue = weekly_sales[((t$var59 - 0) / 1)];
					{
						{
							int var92 = arrivals[t$var59];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(cv$sampleValue, weekly_rates[((t$var59 - 0) / 1)], var92));
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
				logProbability$var93[((t$var59 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample98[((t$var59 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$Sales = false;
				{
					for(int j$var98 = 0; j$var98 < noProducts; j$var98 += 1) {
						if(!cv$guard$Sales) {
							cv$guard$Sales = true;
							logProbability$Sales = (logProbability$Sales + cv$sampleProbability);
						}
					}
				}
			}
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample98 = ((fixedFlag$sample98 && fixedFlag$sample25) && fixedFlag$sample51);
		} else {
			double cv$accumulator = 0.0;
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample98[((t$var59 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var93[((t$var59 - 0) / 1)] = cv$rvAccumulator;
				boolean cv$guard$Sales = false;
				{
					for(int j$var98 = 0; j$var98 < noProducts; j$var98 += 1) {
						if(!cv$guard$Sales) {
							cv$guard$Sales = true;
							logProbability$Sales = (logProbability$Sales + cv$sampleValue);
						}
					}
				}
			}
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample25(int var22) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = ut[var22];
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
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var28 == j$var68)) {
										for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
											weekly_ut[((t$var59 - 0) / 1)][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
									}
								}
							}
						}
					}
					{
						boolean[][] guard$sample25put95 = guard$sample25put95$global;
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var28 == j$var68)) {
										for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
											if(((0 <= j$var68) && (j$var68 < (noProducts + 1)))) {
												{
													for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1)
														guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
						}
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var28 == j$var68)) {
										for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
											if((j$var68 == j$var88)) {
												for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
													guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var28 == j$var68)) {
										for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
											if(((0 <= j$var68) && (j$var68 < (noProducts + 1)))) {
												{
													for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
														if(!guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)]) {
															guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)] = true;
															{
																double reduceVar$denom$10 = 0.0;
																for(int cv$reduction907Index = 0; cv$reduction907Index < (noProducts + 1); cv$reduction907Index += 1) {
																	double k = reduceVar$denom$10;
																	double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction907Index];
																	reduceVar$denom$10 = (k + l);
																}
																weekly_rates[((t$var59 - 0) / 1)][j$var88] = (weekly_ut[((t$var59 - 0) / 1)][j$var88] / reduceVar$denom$10);
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
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var28 == j$var68)) {
										for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
											if((j$var68 == j$var88)) {
												for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
													if(!guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)]) {
														guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)] = true;
														{
															double reduceVar$denom$11 = 0.0;
															for(int cv$reduction83Index = 0; cv$reduction83Index < (noProducts + 1); cv$reduction83Index += 1) {
																double k = reduceVar$denom$11;
																double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction83Index];
																reduceVar$denom$11 = (k + l);
															}
															weekly_rates[((t$var59 - 0) / 1)][j$var88] = (weekly_ut[((t$var59 - 0) / 1)][j$var88] / reduceVar$denom$11);
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
						boolean[] guard$sample25multinomial97 = guard$sample25multinomial97$global;
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var28 == j$var68)) {
										for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
											if(((0 <= j$var68) && (j$var68 < (noProducts + 1)))) {
												{
													guard$sample25multinomial97[((t$var59 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
						}
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var28 == j$var68)) {
										for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
											if((j$var68 == j$var88)) {
												for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
													guard$sample25multinomial97[((t$var59 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						double traceTempVariable$var29$9_1 = cv$currentValue;
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								double traceTempVariable$var69$9_3 = Math.exp(traceTempVariable$var29$9_1);
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var28 == j$var68)) {
										for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
											double traceTempVariable$k$9_6 = (traceTempVariable$var69$9_3 * Avail[t$var59][j$var68]);
											if(((0 <= j$var68) && (j$var68 < (noProducts + 1)))) {
												{
													if(((0 < weekly_ut[((t$var59 - 0) / 1)].length) && (0 < (noProducts + 1)))) {
														double reduceVar$denom$12 = 0.0;
														for(int cv$reduction998Index = 0; cv$reduction998Index < j$var68; cv$reduction998Index += 1) {
															double k = reduceVar$denom$12;
															double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction998Index];
															reduceVar$denom$12 = (k + l);
														}
														for(int cv$reduction998Index = (j$var68 + 1); cv$reduction998Index < (noProducts + 1); cv$reduction998Index += 1) {
															double k = reduceVar$denom$12;
															double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction998Index];
															reduceVar$denom$12 = (k + l);
														}
														double cv$reduced83 = reduceVar$denom$12;
														reduceVar$denom$12 = (traceTempVariable$k$9_6 + cv$reduced83);
														double traceTempVariable$denom$9_7 = reduceVar$denom$12;
														if(!guard$sample25multinomial97[((t$var59 - 0) / 1)]) {
															guard$sample25multinomial97[((t$var59 - 0) / 1)] = true;
															{
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				double[] cv$temp$2$weekly_rates;
																				{
																					cv$temp$2$weekly_rates = weekly_rates[((t$var59 - 0) / 1)];
																				}
																				int cv$temp$3$var92;
																				{
																					int var92 = arrivals[t$var59];
																					cv$temp$3$var92 = var92;
																				}
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$var92)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$var92)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$var92));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$var92)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$var92)));
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
						}
						double traceTempVariable$var29$10_1 = cv$currentValue;
						for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
							if((var22 == j$var28)) {
								double traceTempVariable$var69$10_3 = Math.exp(traceTempVariable$var29$10_1);
								for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
									if((j$var28 == j$var68)) {
										for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
											double traceTempVariable$var89$10_6 = (traceTempVariable$var69$10_3 * Avail[t$var59][j$var68]);
											for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
												if((j$var68 == j$var88)) {
													if(!guard$sample25multinomial97[((t$var59 - 0) / 1)]) {
														guard$sample25multinomial97[((t$var59 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			double[] cv$temp$4$weekly_rates;
																			{
																				cv$temp$4$weekly_rates = weekly_rates[((t$var59 - 0) / 1)];
																			}
																			int cv$temp$5$var92;
																			{
																				int var92 = arrivals[t$var59];
																				cv$temp$5$var92 = var92;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$var92)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$var92)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$var92));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$var92)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$var92)));
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
						for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
							if((j$var28 == j$var68)) {
								for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
									weekly_ut[((t$var59 - 0) / 1)][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
							}
						}
					}
				}
			}
			{
				boolean[][] guard$sample25put95 = guard$sample25put95$global;
				for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
					if((var22 == j$var28)) {
						for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
							if((j$var28 == j$var68)) {
								for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
									if(((0 <= j$var68) && (j$var68 < (noProducts + 1)))) {
										{
											for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1)
												guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)] = false;
										}
									}
								}
							}
						}
					}
				}
				for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
					if((var22 == j$var28)) {
						for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
							if((j$var28 == j$var68)) {
								for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
									if((j$var68 == j$var88)) {
										for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
											guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)] = false;
									}
								}
							}
						}
					}
				}
				for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
					if((var22 == j$var28)) {
						for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
							if((j$var28 == j$var68)) {
								for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
									if(((0 <= j$var68) && (j$var68 < (noProducts + 1)))) {
										{
											for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
												if(!guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)]) {
													guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)] = true;
													{
														double reduceVar$denom$13 = 0.0;
														for(int cv$reduction1112Index = 0; cv$reduction1112Index < (noProducts + 1); cv$reduction1112Index += 1) {
															double k = reduceVar$denom$13;
															double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction1112Index];
															reduceVar$denom$13 = (k + l);
														}
														weekly_rates[((t$var59 - 0) / 1)][j$var88] = (weekly_ut[((t$var59 - 0) / 1)][j$var88] / reduceVar$denom$13);
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
				for(int j$var28 = 0; j$var28 < noProducts; j$var28 += 1) {
					if((var22 == j$var28)) {
						for(int j$var68 = 0; j$var68 < noProducts; j$var68 += 1) {
							if((j$var28 == j$var68)) {
								for(int j$var88 = 0; j$var88 < (noProducts + 1); j$var88 += 1) {
									if((j$var68 == j$var88)) {
										for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
											if(!guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)]) {
												guard$sample25put95[((t$var59 - 0) / 1)][((j$var88 - 0) / 1)] = true;
												{
													double reduceVar$denom$14 = 0.0;
													for(int cv$reduction83Index = 0; cv$reduction83Index < (noProducts + 1); cv$reduction83Index += 1) {
														double k = reduceVar$denom$14;
														double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction83Index];
														reduceVar$denom$14 = (k + l);
													}
													weekly_rates[((t$var59 - 0) / 1)][j$var88] = (weekly_ut[((t$var59 - 0) / 1)][j$var88] / reduceVar$denom$14);
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
		}
	}

	private final void sample43(int var40, int threadID$cv$var40, Rng RNG$) {
		double cv$sum = 0.0;
		int cv$count = 0;
		{
			{
				{
					for(int t$var46 = 0; t$var46 < T; t$var46 += 1) {
						if((var40 == t$var46)) {
							{
								{
									{
										{
											{
												cv$sum = (cv$sum + arrivals[t$var46]);
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
		double var41 = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, cv$sum, cv$count);
		lambda[var40] = var41;
	}

	private final void sample51(int t$var46, int threadID$cv$t$var46, Rng RNG$) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		int cv$originalValue = arrivals[t$var46];
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
					int var49 = cv$proposedValue;
					arrivals[t$var46] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var47;
				{
					double var47 = lambda[t$var46];
					cv$temp$0$var47 = var47;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$currentValue, cv$temp$0$var47));
				{
					{
						int traceTempVariable$var92$1_1 = cv$currentValue;
						for(int t$var59 = 0; t$var59 < T; t$var59 += 1) {
							if((t$var46 == t$var59)) {
								{
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double[] cv$temp$1$weekly_rates;
													{
														cv$temp$1$weekly_rates = weekly_rates[((t$var59 - 0) / 1)];
													}
													int cv$temp$2$var92;
													{
														int var92 = traceTempVariable$var92$1_1;
														cv$temp$2$var92 = var92;
													}
													if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$var92)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$var92)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$var92));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$var92)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var59 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$var92)));
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
			int var49 = cv$originalValue;
			arrivals[t$var46] = var49;
		}
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max_t$var59 = 0;
			int cv$max_j$var88 = 0;
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
				cv$max_j$var88 = Math.max(cv$max_j$var88, (((noProducts + 1) - 0) / 1));
			cv$max_t$var59 = Math.max(cv$max_t$var59, ((T - 0) / 1));
			guard$sample25put95$global = new boolean[cv$max_t$var59][cv$max_j$var88];
		}
		{
			int cv$max_t$var59 = 0;
			cv$max_t$var59 = Math.max(cv$max_t$var59, ((T - 0) / 1));
			guard$sample25multinomial97$global = new boolean[cv$max_t$var59];
		}
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
		{
			Sales = new int[T][];
			for(int var54 = 0; var54 < T; var54 += 1)
				Sales[var54] = new int[noProducts];
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
				Sales[t$var59] = new int[noProducts];
		}
		{
			weekly_rates = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
				weekly_rates[((t$var59 - 0) / 1)] = new double[(noProducts + 1)];
		}
		{
			weekly_ut = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
				weekly_ut[((t$var59 - 0) / 1)] = new double[(noProducts + 1)];
		}
		if(!setFlag$weekly_sales) {
			{
				weekly_sales = new int[((((T - 1) - 0) / 1) + 1)][];
				for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
					weekly_sales[((t$var59 - 0) / 1)] = new int[(noProducts + 1)];
			}
		}
		{
			logProbability$sample25 = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var48 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample51 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var93 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample98 = new double[((((T - 1) - 0) / 1) + 1)];
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
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1) {
						if(!fixedFlag$sample43)
							lambda[var40] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1) {
						if(!fixedFlag$sample51)
							arrivals[t$var46] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var46]);
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var59, int forEnd$index$t$var59, int threadID$index$t$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var59 = forStart$index$t$var59; index$t$var59 < forEnd$index$t$var59; index$t$var59 += 1) {
						int t$var59 = index$t$var59;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1) {
										if(!fixedFlag$sample25)
											weekly_ut[((t$var59 - 0) / 1)][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
									}
							}
						);
						weekly_ut[((t$var59 - 0) / 1)][noProducts] = 1.0;
						double reduceVar$denom$15 = 0.0;
						for(int cv$reduction83Index = 0; cv$reduction83Index < (noProducts + 1); cv$reduction83Index += 1) {
							double k = reduceVar$denom$15;
							double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction83Index];
							if(!fixedFlag$sample25)
								reduceVar$denom$15 = (k + l);
						}
						double reduceVar$denom$15$1 = reduceVar$denom$15;
						parallelFor(RNG$1, 0, (noProducts + 1), 1,
							(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1) {
										if(!fixedFlag$sample25)
											weekly_rates[((t$var59 - 0) / 1)][j$var88] = (weekly_ut[((t$var59 - 0) / 1)][j$var88] / reduceVar$denom$15$1);
									}
							}
						);
						if(!fixedFlag$sample98)
							DistributionSampling.sampleMultinomial(RNG$1, weekly_rates[((t$var59 - 0) / 1)], arrivals[t$var59], weekly_sales[((t$var59 - 0) / 1)]);
						int[] observed_weekly_sales = Sales[t$var59];
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var98, int forEnd$j$var98, int threadID$j$var98, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var98 = forStart$j$var98; j$var98 < forEnd$j$var98; j$var98 += 1) {
										if(!fixedFlag$sample98)
											observed_weekly_sales[j$var98] = weekly_sales[((t$var59 - 0) / 1)][j$var98];
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
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1) {
						if(!fixedFlag$sample43)
							lambda[var40] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1) {
						if(!fixedFlag$sample51)
							arrivals[t$var46] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var46]);
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var59, int forEnd$index$t$var59, int threadID$index$t$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var59 = forStart$index$t$var59; index$t$var59 < forEnd$index$t$var59; index$t$var59 += 1) {
						int t$var59 = index$t$var59;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1) {
										if(!fixedFlag$sample25)
											weekly_ut[((t$var59 - 0) / 1)][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
									}
							}
						);
						weekly_ut[((t$var59 - 0) / 1)][noProducts] = 1.0;
						double reduceVar$denom$17 = 0.0;
						for(int cv$reduction83Index = 0; cv$reduction83Index < (noProducts + 1); cv$reduction83Index += 1) {
							double k = reduceVar$denom$17;
							double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction83Index];
							if(!fixedFlag$sample25)
								reduceVar$denom$17 = (k + l);
						}
						double reduceVar$denom$17$1 = reduceVar$denom$17;
						parallelFor(RNG$1, 0, (noProducts + 1), 1,
							(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1) {
										if(!fixedFlag$sample25)
											weekly_rates[((t$var59 - 0) / 1)][j$var88] = (weekly_ut[((t$var59 - 0) / 1)][j$var88] / reduceVar$denom$17$1);
									}
							}
						);
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
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1) {
						if(!fixedFlag$sample43)
							lambda[var40] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1) {
						if(!fixedFlag$sample51)
							arrivals[t$var46] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var46]);
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var59, int forEnd$index$t$var59, int threadID$index$t$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var59 = forStart$index$t$var59; index$t$var59 < forEnd$index$t$var59; index$t$var59 += 1) {
						int t$var59 = index$t$var59;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1) {
										if(!fixedFlag$sample25)
											weekly_ut[((t$var59 - 0) / 1)][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
									}
							}
						);
						weekly_ut[((t$var59 - 0) / 1)][noProducts] = 1.0;
						double reduceVar$denom$16 = 0.0;
						for(int cv$reduction83Index = 0; cv$reduction83Index < (noProducts + 1); cv$reduction83Index += 1) {
							double k = reduceVar$denom$16;
							double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction83Index];
							if(!fixedFlag$sample25)
								reduceVar$denom$16 = (k + l);
						}
						double reduceVar$denom$16$1 = reduceVar$denom$16;
						parallelFor(RNG$1, 0, (noProducts + 1), 1,
							(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1) {
										if(!fixedFlag$sample25)
											weekly_rates[((t$var59 - 0) / 1)][j$var88] = (weekly_ut[((t$var59 - 0) / 1)][j$var88] / reduceVar$denom$16$1);
									}
							}
						);
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
				(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1) {
							if(!fixedFlag$sample43)
								sample43(var40, threadID$var40, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1) {
							if(!fixedFlag$sample51)
								sample51(t$var46, threadID$t$var46, RNG$1);
						}
				}
			);
		} else {
			parallelFor(RNG$, 0, T, 1,
				(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1) {
							if(!fixedFlag$sample51)
								sample51(t$var46, threadID$t$var46, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, T, 1,
				(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1) {
							if(!fixedFlag$sample43)
								sample43(var40, threadID$var40, RNG$1);
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
		logProbability$ut = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$weekly_rates = 0.0;
		logProbability$exped = 0.0;
		if(!fixedProbFlag$sample25) {
			for(int var22 = 0; var22 < noProducts; var22 += 1)
				logProbability$sample25[((var22 - 0) / 1)] = 0.0;
		}
		logProbability$var36 = 0.0;
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample43)
			logProbability$var41 = 0.0;
		for(int t$var46 = 0; t$var46 < T; t$var46 += 1)
			logProbability$var48[((t$var46 - 0) / 1)] = 0.0;
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample51) {
			for(int t$var46 = 0; t$var46 < T; t$var46 += 1)
				logProbability$sample51[((t$var46 - 0) / 1)] = 0.0;
		}
		for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
			logProbability$var93[((t$var59 - 0) / 1)] = 0.0;
		logProbability$Sales = 0.0;
		logProbability$weekly_sales = 0.0;
		if(!fixedProbFlag$sample98) {
			for(int t$var59 = 0; t$var59 < T; t$var59 += 1)
				logProbability$sample98[((t$var59 - 0) / 1)] = 0.0;
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
		parallelFor(RNG$, 0, T, 1,
			(int forStart$var40, int forEnd$var40, int threadID$var40, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var40 = forStart$var40; var40 < forEnd$var40; var40 += 1) {
						if(!fixedFlag$sample43)
							lambda[var40] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var46, int forEnd$t$var46, int threadID$t$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int t$var46 = forStart$t$var46; t$var46 < forEnd$t$var46; t$var46 += 1) {
						if(!fixedFlag$sample51)
							arrivals[t$var46] = DistributionSampling.samplePoisson(RNG$1, lambda[t$var46]);
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var59, int forEnd$index$t$var59, int threadID$index$t$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var59 = forStart$index$t$var59; index$t$var59 < forEnd$index$t$var59; index$t$var59 += 1) {
						int t$var59 = index$t$var59;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1) {
										if(!fixedFlag$sample25)
											weekly_ut[((t$var59 - 0) / 1)][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
									}
							}
						);
						weekly_ut[((t$var59 - 0) / 1)][noProducts] = 1.0;
						double reduceVar$denom$18 = 0.0;
						for(int cv$reduction83Index = 0; cv$reduction83Index < (noProducts + 1); cv$reduction83Index += 1) {
							double k = reduceVar$denom$18;
							double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction83Index];
							if(!fixedFlag$sample25)
								reduceVar$denom$18 = (k + l);
						}
						double reduceVar$denom$18$1 = reduceVar$denom$18;
						parallelFor(RNG$1, 0, (noProducts + 1), 1,
							(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1) {
										if(!fixedFlag$sample25)
											weekly_rates[((t$var59 - 0) / 1)][j$var88] = (weekly_ut[((t$var59 - 0) / 1)][j$var88] / reduceVar$denom$18$1);
									}
							}
						);
					}
			}
		);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		{
			int[][] cv$source1 = ObsSales;
			int[][] cv$target1 = Sales;
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
				int[] cv$source2 = cv$source1[cv$index1];
				int[] cv$target2 = cv$target1[cv$index1];
				int cv$length2 = cv$target2.length;
				for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
					cv$target2[cv$index2] = cv$source2[cv$index2];
			}
		}
		for(int t$var59 = (T - ((((T - 1) - 0) % 1) + 1)); t$var59 >= ((0 - 1) + 1); t$var59 -= 1) {
			int[] observed_weekly_sales;
			observed_weekly_sales = Sales[t$var59];
			for(int j$var98 = (noProducts - ((((noProducts - 1) - 0) % 1) + 1)); j$var98 >= ((0 - 1) + 1); j$var98 -= 1)
				weekly_sales[((t$var59 - 0) / 1)][j$var98] = observed_weekly_sales[j$var98];
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
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var59, int forEnd$index$t$var59, int threadID$index$t$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var59 = forStart$index$t$var59; index$t$var59 < forEnd$index$t$var59; index$t$var59 += 1) {
						int t$var59 = index$t$var59;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var68, int forEnd$j$var68, int threadID$j$var68, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var68 = forStart$j$var68; j$var68 < forEnd$j$var68; j$var68 += 1) {
										if(setFlag$ut)
											weekly_ut[((t$var59 - 0) / 1)][j$var68] = (exped[j$var68] * Avail[t$var59][j$var68]);
									}
							}
						);
						double reduceVar$denom$19 = 0.0;
						for(int cv$reduction83Index = 0; cv$reduction83Index < (noProducts + 1); cv$reduction83Index += 1) {
							double k = reduceVar$denom$19;
							double l = weekly_ut[((t$var59 - 0) / 1)][cv$reduction83Index];
							reduceVar$denom$19 = (k + l);
						}
						double reduceVar$denom$19$1 = reduceVar$denom$19;
						parallelFor(RNG$1, 0, (noProducts + 1), 1,
							(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1) {
										if(setFlag$ut)
											weekly_rates[((t$var59 - 0) / 1)][j$var88] = (weekly_ut[((t$var59 - 0) / 1)][j$var88] / reduceVar$denom$19$1);
									}
							}
						);
						if(setFlag$weekly_sales) {
							int[] observed_weekly_sales = Sales[t$var59];
							parallelFor(RNG$1, 0, noProducts, 1,
								(int forStart$j$var98, int forEnd$j$var98, int threadID$j$var98, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var98 = forStart$j$var98; j$var98 < forEnd$j$var98; j$var98 += 1)
											observed_weekly_sales[j$var98] = weekly_sales[((t$var59 - 0) / 1)][j$var98];
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