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
		fixedProbFlag$sample141 = (fixedFlag$sample141 && fixedProbFlag$sample141);
	}

	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	@Override
	public final void set$fixedFlag$sample32(boolean cv$value) {
		fixedFlag$sample32 = cv$value;
		fixedProbFlag$sample32 = (fixedFlag$sample32 && fixedProbFlag$sample32);
		fixedProbFlag$sample141 = (fixedFlag$sample32 && fixedProbFlag$sample141);
	}

	@Override
	public final boolean get$fixedFlag$sample64() {
		return fixedFlag$sample64;
	}

	@Override
	public final void set$fixedFlag$sample64(boolean cv$value) {
		fixedFlag$sample64 = cv$value;
		fixedProbFlag$sample64 = (fixedFlag$sample64 && fixedProbFlag$sample64);
		fixedProbFlag$sample79 = (fixedFlag$sample64 && fixedProbFlag$sample79);
	}

	@Override
	public final boolean get$fixedFlag$sample79() {
		return fixedFlag$sample79;
	}

	@Override
	public final void set$fixedFlag$sample79(boolean cv$value) {
		fixedFlag$sample79 = cv$value;
		fixedProbFlag$sample79 = (fixedFlag$sample79 && fixedProbFlag$sample79);
		fixedProbFlag$sample141 = (fixedFlag$sample79 && fixedProbFlag$sample141);
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
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int[] cv$sampleValue = weekly_sales[((t$var89 - 0) / 1)];
					{
						{
							int var136 = arrivals[t$var89];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(cv$sampleValue, weekly_rates[((t$var89 - 0) / 1)], var136));
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
				logProbability$var137[((t$var89 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample141[((t$var89 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$Sales = false;
				{
					for(int j$var148 = 0; j$var148 < noProducts; j$var148 += 1) {
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
			fixedProbFlag$sample141 = ((fixedFlag$sample141 && fixedFlag$sample32) && fixedFlag$sample79);
		} else {
			double cv$accumulator = 0.0;
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample141[((t$var89 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var137[((t$var89 - 0) / 1)] = cv$rvAccumulator;
				boolean cv$guard$Sales = false;
				{
					for(int j$var148 = 0; j$var148 < noProducts; j$var148 += 1) {
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
				boolean cv$guard$weekly_ut = false;
				boolean cv$guard$weekly_rates = false;
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
							for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
								if((j$var42 == j$var104)) {
									for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
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
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
							for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
								if((j$var42 == j$var104)) {
									for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
										if(((0 <= j$var104) && (j$var104 < (noProducts + 1)))) {
											{
												for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
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
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
							for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
								if((j$var42 == j$var104)) {
									for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
										if((j$var104 == j$var132)) {
											for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
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
				boolean cv$guard$weekly_ut = false;
				boolean cv$guard$weekly_rates = false;
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
							for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
								if((j$var42 == j$var104)) {
									for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
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
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
							for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
								if((j$var42 == j$var104)) {
									for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
										if(((0 <= j$var104) && (j$var104 < (noProducts + 1)))) {
											{
												for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
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
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
							for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
								if((j$var42 == j$var104)) {
									for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
										if((j$var104 == j$var132)) {
											for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
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
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample64() {
		if(!fixedProbFlag$sample64) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var61 = 0; var61 < T; var61 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = lambda[var61];
					{
						{
							double var48 = 10.0;
							double var49 = 10.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGamma(cv$sampleValue, var48, var49));
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
			logProbability$var50 = cv$sampleAccumulator;
			logProbability$var62 = cv$sampleAccumulator;
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample64)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample64 = fixedFlag$sample64;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var62;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var50 = cv$rvAccumulator;
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample64)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample79() {
		if(!fixedProbFlag$sample79) {
			double cv$accumulator = 0.0;
			for(int t$var74 = 0; t$var74 < T; t$var74 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = arrivals[t$var74];
					{
						{
							double var75 = lambda[t$var74];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$sampleValue, var75));
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
				logProbability$var76[((t$var74 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample79[((t$var74 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample79)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample79 = (fixedFlag$sample79 && fixedFlag$sample64);
		} else {
			double cv$accumulator = 0.0;
			for(int t$var74 = 0; t$var74 < T; t$var74 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample79[((t$var74 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var76[((t$var74 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample79)
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
								for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
									if((j$var42 == j$var104)) {
										for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
											weekly_ut[((t$var89 - 0) / 1)][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
									}
								}
							}
						}
					}
					{
						boolean[][] guard$sample32put138 = guard$sample32put138$global;
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
									if((j$var42 == j$var104)) {
										for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
											if(((0 <= j$var104) && (j$var104 < (noProducts + 1)))) {
												{
													for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1)
														guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
									if((j$var42 == j$var104)) {
										for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
											if((j$var104 == j$var132)) {
												for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
													guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
									if((j$var42 == j$var104)) {
										for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
											if(((0 <= j$var104) && (j$var104 < (noProducts + 1)))) {
												{
													for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
														if(!guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)]) {
															guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)] = true;
															{
																double reduceVar$denom$0 = 0.0;
																for(int cv$reduction118Index = 0; cv$reduction118Index < (noProducts + 1); cv$reduction118Index += 1) {
																	double k = reduceVar$denom$0;
																	double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction118Index];
																	reduceVar$denom$0 = (k + l);
																}
																weekly_rates[((t$var89 - 0) / 1)][j$var132] = (weekly_ut[((t$var89 - 0) / 1)][j$var132] / reduceVar$denom$0);
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
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
									if((j$var42 == j$var104)) {
										for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
											if((j$var104 == j$var132)) {
												for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
													if(!guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)]) {
														guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)] = true;
														{
															double reduceVar$denom$1 = 0.0;
															for(int cv$reduction118Index = 0; cv$reduction118Index < (noProducts + 1); cv$reduction118Index += 1) {
																double k = reduceVar$denom$1;
																double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction118Index];
																reduceVar$denom$1 = (k + l);
															}
															weekly_rates[((t$var89 - 0) / 1)][j$var132] = (weekly_ut[((t$var89 - 0) / 1)][j$var132] / reduceVar$denom$1);
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
						boolean[] guard$sample32multinomial140 = guard$sample32multinomial140$global;
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
									if((j$var42 == j$var104)) {
										for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
											if(((0 <= j$var104) && (j$var104 < (noProducts + 1)))) {
												{
													guard$sample32multinomial140[((t$var89 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
									if((j$var42 == j$var104)) {
										for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
											if((j$var104 == j$var132)) {
												for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
													guard$sample32multinomial140[((t$var89 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						double traceTempVariable$var43$9_1 = cv$currentValue;
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								double traceTempVariable$var105$9_3 = Math.exp(traceTempVariable$var43$9_1);
								for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
									if((j$var42 == j$var104)) {
										for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
											double traceTempVariable$k$9_6 = (traceTempVariable$var105$9_3 * Avail[t$var89][j$var104]);
											if(((0 <= j$var104) && (j$var104 < (noProducts + 1)))) {
												{
													if(((0 < weekly_ut[((t$var89 - 0) / 1)].length) && (0 < (noProducts + 1)))) {
														double reduceVar$denom$2 = 0.0;
														for(int cv$reduction493Index = 0; cv$reduction493Index < j$var104; cv$reduction493Index += 1) {
															double k = reduceVar$denom$2;
															double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction493Index];
															reduceVar$denom$2 = (k + l);
														}
														for(int cv$reduction493Index = (j$var104 + 1); cv$reduction493Index < (noProducts + 1); cv$reduction493Index += 1) {
															double k = reduceVar$denom$2;
															double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction493Index];
															reduceVar$denom$2 = (k + l);
														}
														double cv$reduced118 = reduceVar$denom$2;
														reduceVar$denom$2 = (traceTempVariable$k$9_6 + cv$reduced118);
														double traceTempVariable$denom$9_7 = reduceVar$denom$2;
														if(!guard$sample32multinomial140[((t$var89 - 0) / 1)]) {
															guard$sample32multinomial140[((t$var89 - 0) / 1)] = true;
															{
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				double[] cv$temp$2$weekly_rates;
																				{
																					cv$temp$2$weekly_rates = weekly_rates[((t$var89 - 0) / 1)];
																				}
																				int cv$temp$3$var136;
																				{
																					int var136 = arrivals[t$var89];
																					cv$temp$3$var136 = var136;
																				}
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$var136)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$var136)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$var136));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$var136)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$var136)));
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
						double traceTempVariable$var43$10_1 = cv$currentValue;
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								double traceTempVariable$var105$10_3 = Math.exp(traceTempVariable$var43$10_1);
								for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
									if((j$var42 == j$var104)) {
										for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
											double traceTempVariable$var133$10_6 = (traceTempVariable$var105$10_3 * Avail[t$var89][j$var104]);
											for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
												if((j$var104 == j$var132)) {
													if(!guard$sample32multinomial140[((t$var89 - 0) / 1)]) {
														guard$sample32multinomial140[((t$var89 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			double[] cv$temp$4$weekly_rates;
																			{
																				cv$temp$4$weekly_rates = weekly_rates[((t$var89 - 0) / 1)];
																			}
																			int cv$temp$5$var136;
																			{
																				int var136 = arrivals[t$var89];
																				cv$temp$5$var136 = var136;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$var136)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$var136)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$var136));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$var136)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$var136)));
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
						for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
							if((j$var42 == j$var104)) {
								for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
									weekly_ut[((t$var89 - 0) / 1)][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
							}
						}
					}
				}
			}
			{
				boolean[][] guard$sample32put138 = guard$sample32put138$global;
				for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
					if((var29 == j$var42)) {
						for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
							if((j$var42 == j$var104)) {
								for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
									if(((0 <= j$var104) && (j$var104 < (noProducts + 1)))) {
										{
											for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1)
												guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)] = false;
										}
									}
								}
							}
						}
					}
				}
				for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
					if((var29 == j$var42)) {
						for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
							if((j$var42 == j$var104)) {
								for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
									if((j$var104 == j$var132)) {
										for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
											guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)] = false;
									}
								}
							}
						}
					}
				}
				for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
					if((var29 == j$var42)) {
						for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
							if((j$var42 == j$var104)) {
								for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
									if(((0 <= j$var104) && (j$var104 < (noProducts + 1)))) {
										{
											for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
												if(!guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)]) {
													guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)] = true;
													{
														double reduceVar$denom$3 = 0.0;
														for(int cv$reduction118Index = 0; cv$reduction118Index < (noProducts + 1); cv$reduction118Index += 1) {
															double k = reduceVar$denom$3;
															double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction118Index];
															reduceVar$denom$3 = (k + l);
														}
														weekly_rates[((t$var89 - 0) / 1)][j$var132] = (weekly_ut[((t$var89 - 0) / 1)][j$var132] / reduceVar$denom$3);
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
				for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
					if((var29 == j$var42)) {
						for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
							if((j$var42 == j$var104)) {
								for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
									if((j$var104 == j$var132)) {
										for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
											if(!guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)]) {
												guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)] = true;
												{
													double reduceVar$denom$4 = 0.0;
													for(int cv$reduction118Index = 0; cv$reduction118Index < (noProducts + 1); cv$reduction118Index += 1) {
														double k = reduceVar$denom$4;
														double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction118Index];
														reduceVar$denom$4 = (k + l);
													}
													weekly_rates[((t$var89 - 0) / 1)][j$var132] = (weekly_ut[((t$var89 - 0) / 1)][j$var132] / reduceVar$denom$4);
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

	private final void sample64(int var61) {
		double cv$sum = 0.0;
		int cv$count = 0;
		{
			{
				{
					for(int t$var74 = 0; t$var74 < T; t$var74 += 1) {
						if((var61 == t$var74)) {
							{
								{
									{
										{
											{
												cv$sum = (cv$sum + arrivals[t$var74]);
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
		double var62 = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, cv$sum, cv$count);
		lambda[var61] = var62;
	}

	private final void sample79(int t$var74) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		int cv$originalValue = arrivals[t$var74];
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
					int var77 = cv$proposedValue;
					arrivals[t$var74] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var75;
				{
					double var75 = lambda[t$var74];
					cv$temp$0$var75 = var75;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$currentValue, cv$temp$0$var75));
				{
					{
						int traceTempVariable$var136$1_1 = cv$currentValue;
						for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
							if((t$var74 == t$var89)) {
								{
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double[] cv$temp$1$weekly_rates;
													{
														cv$temp$1$weekly_rates = weekly_rates[((t$var89 - 0) / 1)];
													}
													int cv$temp$2$var136;
													{
														int var136 = traceTempVariable$var136$1_1;
														cv$temp$2$var136 = var136;
													}
													if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$var136)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$var136)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$var136));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$var136)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$var136)));
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
			int var77 = cv$originalValue;
			arrivals[t$var74] = var77;
		}
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max_t$var89 = 0;
			int cv$max_j$var132 = 0;
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
				cv$max_j$var132 = Math.max(cv$max_j$var132, (((noProducts + 1) - 0) / 1));
			cv$max_t$var89 = Math.max(cv$max_t$var89, ((T - 0) / 1));
			guard$sample32put138$global = new boolean[cv$max_t$var89][cv$max_j$var132];
		}
		{
			int cv$max_t$var89 = 0;
			cv$max_t$var89 = Math.max(cv$max_t$var89, ((T - 0) / 1));
			guard$sample32multinomial140$global = new boolean[cv$max_t$var89];
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
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
				Sales[t$var89] = new int[noProducts];
		}
		{
			weekly_rates = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
				weekly_rates[((t$var89 - 0) / 1)] = new double[(noProducts + 1)];
		}
		{
			weekly_ut = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
				weekly_ut[((t$var89 - 0) / 1)] = new double[(noProducts + 1)];
		}
		if(!setFlag$weekly_sales) {
			{
				weekly_sales = new int[((((T - 1) - 0) / 1) + 1)][];
				for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
					weekly_sales[((t$var89 - 0) / 1)] = new int[(noProducts + 1)];
			}
		}
		{
			logProbability$sample32 = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var76 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample79 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var137 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample141 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int var29 = 0; var29 < noProducts; var29 += 1) {
			if(!fixedFlag$sample32)
				ut[var29] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
			if(!fixedFlag$sample32)
				exped[j$var42] = Math.exp(ut[j$var42]);
		}
		for(int var61 = 0; var61 < T; var61 += 1) {
			if(!fixedFlag$sample64)
				lambda[var61] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var74 = 0; t$var74 < T; t$var74 += 1) {
			if(!fixedFlag$sample79)
				arrivals[t$var74] = DistributionSampling.samplePoisson(RNG$, lambda[t$var74]);
		}
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
			for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
				if(!fixedFlag$sample32)
					weekly_ut[((t$var89 - 0) / 1)][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
			}
			weekly_ut[((t$var89 - 0) / 1)][noProducts] = 1.0;
			double reduceVar$denom$5 = 0.0;
			for(int cv$reduction118Index = 0; cv$reduction118Index < (noProducts + 1); cv$reduction118Index += 1) {
				double k = reduceVar$denom$5;
				double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction118Index];
				if(!fixedFlag$sample32)
					reduceVar$denom$5 = (k + l);
			}
			for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
				if(!fixedFlag$sample32)
					weekly_rates[((t$var89 - 0) / 1)][j$var132] = (weekly_ut[((t$var89 - 0) / 1)][j$var132] / reduceVar$denom$5);
			}
			if(!fixedFlag$sample141)
				DistributionSampling.sampleMultinomial(RNG$, weekly_rates[((t$var89 - 0) / 1)], arrivals[t$var89], weekly_sales[((t$var89 - 0) / 1)]);
			int[] observed_weekly_sales = Sales[t$var89];
			for(int j$var148 = 0; j$var148 < noProducts; j$var148 += 1) {
				if(!fixedFlag$sample141)
					observed_weekly_sales[j$var148] = weekly_sales[((t$var89 - 0) / 1)][j$var148];
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var29 = 0; var29 < noProducts; var29 += 1) {
			if(!fixedFlag$sample32)
				ut[var29] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
			if(!fixedFlag$sample32)
				exped[j$var42] = Math.exp(ut[j$var42]);
		}
		for(int var61 = 0; var61 < T; var61 += 1) {
			if(!fixedFlag$sample64)
				lambda[var61] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var74 = 0; t$var74 < T; t$var74 += 1) {
			if(!fixedFlag$sample79)
				arrivals[t$var74] = DistributionSampling.samplePoisson(RNG$, lambda[t$var74]);
		}
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
			for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
				if(!fixedFlag$sample32)
					weekly_ut[((t$var89 - 0) / 1)][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
			}
			weekly_ut[((t$var89 - 0) / 1)][noProducts] = 1.0;
			double reduceVar$denom$7 = 0.0;
			for(int cv$reduction118Index = 0; cv$reduction118Index < (noProducts + 1); cv$reduction118Index += 1) {
				double k = reduceVar$denom$7;
				double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction118Index];
				if(!fixedFlag$sample32)
					reduceVar$denom$7 = (k + l);
			}
			for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
				if(!fixedFlag$sample32)
					weekly_rates[((t$var89 - 0) / 1)][j$var132] = (weekly_ut[((t$var89 - 0) / 1)][j$var132] / reduceVar$denom$7);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var29 = 0; var29 < noProducts; var29 += 1) {
			if(!fixedFlag$sample32)
				ut[var29] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
			if(!fixedFlag$sample32)
				exped[j$var42] = Math.exp(ut[j$var42]);
		}
		for(int var61 = 0; var61 < T; var61 += 1) {
			if(!fixedFlag$sample64)
				lambda[var61] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var74 = 0; t$var74 < T; t$var74 += 1) {
			if(!fixedFlag$sample79)
				arrivals[t$var74] = DistributionSampling.samplePoisson(RNG$, lambda[t$var74]);
		}
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
			for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
				if(!fixedFlag$sample32)
					weekly_ut[((t$var89 - 0) / 1)][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
			}
			weekly_ut[((t$var89 - 0) / 1)][noProducts] = 1.0;
			double reduceVar$denom$6 = 0.0;
			for(int cv$reduction118Index = 0; cv$reduction118Index < (noProducts + 1); cv$reduction118Index += 1) {
				double k = reduceVar$denom$6;
				double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction118Index];
				if(!fixedFlag$sample32)
					reduceVar$denom$6 = (k + l);
			}
			for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
				if(!fixedFlag$sample32)
					weekly_rates[((t$var89 - 0) / 1)][j$var132] = (weekly_ut[((t$var89 - 0) / 1)][j$var132] / reduceVar$denom$6);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var29 = 0; var29 < noProducts; var29 += 1) {
				if(!fixedFlag$sample32)
					sample32(var29);
			}
			for(int var61 = 0; var61 < T; var61 += 1) {
				if(!fixedFlag$sample64)
					sample64(var61);
			}
			for(int t$var74 = 0; t$var74 < T; t$var74 += 1) {
				if(!fixedFlag$sample79)
					sample79(t$var74);
			}
		} else {
			for(int t$var74 = (T - ((((T - 1) - 0) % 1) + 1)); t$var74 >= ((0 - 1) + 1); t$var74 -= 1) {
				if(!fixedFlag$sample79)
					sample79(t$var74);
			}
			for(int var61 = (T - ((((T - 1) - 0) % 1) + 1)); var61 >= ((0 - 1) + 1); var61 -= 1) {
				if(!fixedFlag$sample64)
					sample64(var61);
			}
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
		logProbability$weekly_rates = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$ut = 0.0;
		if(!fixedProbFlag$sample32) {
			for(int var29 = 0; var29 < noProducts; var29 += 1)
				logProbability$sample32[((var29 - 0) / 1)] = 0.0;
		}
		logProbability$var50 = 0.0;
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample64)
			logProbability$var62 = 0.0;
		for(int t$var74 = 0; t$var74 < T; t$var74 += 1)
			logProbability$var76[((t$var74 - 0) / 1)] = 0.0;
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample79) {
			for(int t$var74 = 0; t$var74 < T; t$var74 += 1)
				logProbability$sample79[((t$var74 - 0) / 1)] = 0.0;
		}
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
			logProbability$var137[((t$var89 - 0) / 1)] = 0.0;
		logProbability$Sales = 0.0;
		logProbability$weekly_sales = 0.0;
		if(!fixedProbFlag$sample141) {
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
				logProbability$sample141[((t$var89 - 0) / 1)] = 0.0;
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
		for(int var29 = 0; var29 < noProducts; var29 += 1) {
			if(!fixedFlag$sample32)
				ut[var29] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
			if(!fixedFlag$sample32)
				exped[j$var42] = Math.exp(ut[j$var42]);
		}
		for(int var61 = 0; var61 < T; var61 += 1) {
			if(!fixedFlag$sample64)
				lambda[var61] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var74 = 0; t$var74 < T; t$var74 += 1) {
			if(!fixedFlag$sample79)
				arrivals[t$var74] = DistributionSampling.samplePoisson(RNG$, lambda[t$var74]);
		}
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
			for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
				if(!fixedFlag$sample32)
					weekly_ut[((t$var89 - 0) / 1)][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
			}
			weekly_ut[((t$var89 - 0) / 1)][noProducts] = 1.0;
			double reduceVar$denom$8 = 0.0;
			for(int cv$reduction118Index = 0; cv$reduction118Index < (noProducts + 1); cv$reduction118Index += 1) {
				double k = reduceVar$denom$8;
				double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction118Index];
				if(!fixedFlag$sample32)
					reduceVar$denom$8 = (k + l);
			}
			for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
				if(!fixedFlag$sample32)
					weekly_rates[((t$var89 - 0) / 1)][j$var132] = (weekly_ut[((t$var89 - 0) / 1)][j$var132] / reduceVar$denom$8);
			}
		}
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
		for(int t$var89 = (T - ((((T - 1) - 0) % 1) + 1)); t$var89 >= ((0 - 1) + 1); t$var89 -= 1) {
			for(int j$var148 = (noProducts - ((((noProducts - 1) - 0) % 1) + 1)); j$var148 >= ((0 - 1) + 1); j$var148 -= 1) {
				int[] observed_weekly_sales;
				observed_weekly_sales = Sales[t$var89];
				weekly_sales[((t$var89 - 0) / 1)][j$var148] = observed_weekly_sales[j$var148];
			}
		}
	}

	@Override
	public final void setIntermediates() {
		for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
			if(setFlag$ut)
				exped[j$var42] = Math.exp(ut[j$var42]);
		}
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
			for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
				if(setFlag$ut)
					weekly_ut[((t$var89 - 0) / 1)][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
			}
			double reduceVar$denom$9 = 0.0;
			for(int cv$reduction118Index = 0; cv$reduction118Index < (noProducts + 1); cv$reduction118Index += 1) {
				double k = reduceVar$denom$9;
				double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction118Index];
				reduceVar$denom$9 = (k + l);
			}
			for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
				if(setFlag$ut)
					weekly_rates[((t$var89 - 0) / 1)][j$var132] = (weekly_ut[((t$var89 - 0) / 1)][j$var132] / reduceVar$denom$9);
			}
			if(setFlag$weekly_sales) {
				int[] observed_weekly_sales = Sales[t$var89];
				for(int j$var148 = 0; j$var148 < noProducts; j$var148 += 1)
					observed_weekly_sales[j$var148] = weekly_sales[((t$var89 - 0) / 1)][j$var148];
			}
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n/*\n * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n */\npackage org.sandwood.compiler.tests.parser;\n\nmodel Vulcano2012notNormalized(int noProducts, int T, int s, int[][] ObsSales, int[][] Avail) {\n    // Avail is the availability matrix, T-by-noProducts\n\n    // draw utilities\n    double[] ut = gaussian(0, 10).sample(noProducts);\n\n    //exponentiate right here (in the non-basic models move to the for loop)\n    double[] exped = new double[noProducts];\n    for(int j : [0..noProducts)) {\n    exped[j] = exp(ut[j]);\n    }\n\n    // priors for the distribution of lambdas (for arrivals). They can be supplied as a vector if RGBU has some estimates, or just use some ad hoc priors\n    double[ ] lambda = gamma(10,10).sample(T);\n\n    // draw arrivals\n    int[] arrivals = new int[T];\n    for (int t : [0..T)){\n    arrivals[t]= poisson(lambda[t]).sample();\n    }\n\n    int[][] Sales = new int[T][];\n\n    for (int t:[0..T)){\n        // for each period t calculate choice probabilities and sales\n\n        double[] weekly_rates = new double[noProducts+1];\n        double[] weekly_ut = new double[noProducts+1];\n\n        for (int j : [0..noProducts)) {\n            weekly_ut[j] = exped[j]*Avail[t][j] ;\n        }\n        // add outside option value (which is always available)\n        weekly_ut[noProducts] = 1.0;\n        double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n\n        for (int j : [0..noProducts]) {\n            weekly_rates[j] = weekly_ut[j]/denom ;\n        }\n\n        int[] weekly_sales = multinomial(weekly_rates, arrivals[t]).sample();\n\n        //getting rid of the no purchase observation (last one in the vector of weekly_sales)\n        int[] observed_weekly_sales = new int[noProducts];\n        for (int j : [0..noProducts)) {\n            observed_weekly_sales[j] = weekly_sales[j] ;\n        }\n\n        // record sales for period t\n        Sales[t] = observed_weekly_sales;\n\n    }\n    // assert that generated sales match observed sales\n    Sales.observe(ObsSales);\n}";
	}
}