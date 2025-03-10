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
		fixedProbFlag$sample131 = (fixedFlag$sample131 && fixedProbFlag$sample131);
	}

	@Override
	public final boolean get$fixedFlag$sample22() {
		return fixedFlag$sample22;
	}

	@Override
	public final void set$fixedFlag$sample22(boolean cv$value) {
		fixedFlag$sample22 = cv$value;
		fixedProbFlag$sample22 = (fixedFlag$sample22 && fixedProbFlag$sample22);
		fixedProbFlag$sample131 = (fixedFlag$sample22 && fixedProbFlag$sample131);
	}

	@Override
	public final boolean get$fixedFlag$sample54() {
		return fixedFlag$sample54;
	}

	@Override
	public final void set$fixedFlag$sample54(boolean cv$value) {
		fixedFlag$sample54 = cv$value;
		fixedProbFlag$sample54 = (fixedFlag$sample54 && fixedProbFlag$sample54);
		fixedProbFlag$sample69 = (fixedFlag$sample54 && fixedProbFlag$sample69);
	}

	@Override
	public final boolean get$fixedFlag$sample69() {
		return fixedFlag$sample69;
	}

	@Override
	public final void set$fixedFlag$sample69(boolean cv$value) {
		fixedFlag$sample69 = cv$value;
		fixedProbFlag$sample69 = (fixedFlag$sample69 && fixedProbFlag$sample69);
		fixedProbFlag$sample131 = (fixedFlag$sample69 && fixedProbFlag$sample131);
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
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int[] cv$sampleValue = weekly_sales[((t$var81 - 0) / 1)];
					{
						{
							int var128 = arrivals[t$var81];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(cv$sampleValue, weekly_rates[((t$var81 - 0) / 1)], (noProducts + 1), var128));
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
				logProbability$var129[((t$var81 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample131[((t$var81 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$Sales = false;
				{
					for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
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
			fixedProbFlag$sample131 = ((fixedFlag$sample131 && fixedFlag$sample22) && fixedFlag$sample69);
		} else {
			double cv$accumulator = 0.0;
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample131[((t$var81 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var129[((t$var81 - 0) / 1)] = cv$rvAccumulator;
				boolean cv$guard$Sales = false;
				{
					for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
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

	private final void logProbabilityValue$sample22() {
		if(!fixedProbFlag$sample22) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var21 = 0; var21 < noProducts; var21 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = ut[var21];
					{
						{
							double var8 = 0.0;
							double var9 = 10.0;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var8) / Math.sqrt(var9))) - (0.5 * Math.log(var9))));
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
				logProbability$sample22[((var21 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$exped = false;
				boolean cv$guard$weekly_ut = false;
				boolean cv$guard$weekly_rates = false;
				{
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							if(!cv$guard$exped) {
								cv$guard$exped = true;
								logProbability$exped = (logProbability$exped + cv$sampleProbability);
							}
						}
					}
				}
				{
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
								if((j$var34 == j$var96)) {
									for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
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
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
								if((j$var34 == j$var96)) {
									for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
										if(((0 <= j$var96) && (j$var96 < (noProducts + 1)))) {
											{
												for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
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
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
								if((j$var34 == j$var96)) {
									for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
										if((j$var96 == j$var124)) {
											for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
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
			logProbability$var10 = cv$sampleAccumulator;
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample22 = fixedFlag$sample22;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var21 = 0; var21 < noProducts; var21 += 1) {
				double cv$sampleValue = logProbability$sample22[((var21 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				boolean cv$guard$exped = false;
				boolean cv$guard$weekly_ut = false;
				boolean cv$guard$weekly_rates = false;
				{
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							if(!cv$guard$exped) {
								cv$guard$exped = true;
								logProbability$exped = (logProbability$exped + cv$sampleValue);
							}
						}
					}
				}
				{
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
								if((j$var34 == j$var96)) {
									for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
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
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
								if((j$var34 == j$var96)) {
									for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
										if(((0 <= j$var96) && (j$var96 < (noProducts + 1)))) {
											{
												for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
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
					for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
						if((var21 == j$var34)) {
							for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
								if((j$var34 == j$var96)) {
									for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
										if((j$var96 == j$var124)) {
											for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
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
			logProbability$var10 = cv$rvAccumulator;
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample54() {
		if(!fixedProbFlag$sample54) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var53 = 0; var53 < T; var53 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = lambda[var53];
					{
						{
							double var40 = 10.0;
							double var41 = 10.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGamma(cv$sampleValue, var40, var41));
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
			logProbability$var42 = cv$sampleAccumulator;
			logProbability$var54 = cv$sampleAccumulator;
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample54)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample54 = fixedFlag$sample54;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var54;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var42 = cv$rvAccumulator;
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample54)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample69() {
		if(!fixedProbFlag$sample69) {
			double cv$accumulator = 0.0;
			for(int t$var66 = 0; t$var66 < T; t$var66 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = arrivals[t$var66];
					{
						{
							double var67 = lambda[t$var66];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$sampleValue, var67));
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
				logProbability$var68[((t$var66 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample69[((t$var66 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample69)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample69 = (fixedFlag$sample69 && fixedFlag$sample54);
		} else {
			double cv$accumulator = 0.0;
			for(int t$var66 = 0; t$var66 < T; t$var66 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample69[((t$var66 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var68[((t$var66 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample69)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample22(int var21) {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double cv$originalValue = ut[var21];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var22 = cv$proposedValue;
					ut[var21] = cv$currentValue;
					{
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								{
									exped[j$var34] = Math.exp(ut[j$var34]);
								}
							}
						}
					}
					{
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
									if((j$var34 == j$var96)) {
										for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
											weekly_ut[((t$var81 - 0) / 1)][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
									}
								}
							}
						}
					}
					{
						boolean[][] guard$sample22put128 = guard$sample22put128$global;
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
									if((j$var34 == j$var96)) {
										for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
											if(((0 <= j$var96) && (j$var96 < (noProducts + 1)))) {
												{
													for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1)
														guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
						}
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
									if((j$var34 == j$var96)) {
										for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
											if((j$var96 == j$var124)) {
												for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
													guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
									if((j$var34 == j$var96)) {
										for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
											if(((0 <= j$var96) && (j$var96 < (noProducts + 1)))) {
												{
													for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
														if(!guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)]) {
															guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)] = true;
															{
																double reduceVar$denom$0 = 0.0;
																for(int cv$reduction108Index = 0; cv$reduction108Index < (noProducts + 1); cv$reduction108Index += 1) {
																	double k = reduceVar$denom$0;
																	double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction108Index];
																	reduceVar$denom$0 = (k + l);
																}
																weekly_rates[((t$var81 - 0) / 1)][j$var124] = (weekly_ut[((t$var81 - 0) / 1)][j$var124] / reduceVar$denom$0);
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
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
									if((j$var34 == j$var96)) {
										for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
											if((j$var96 == j$var124)) {
												for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
													if(!guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)]) {
														guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)] = true;
														{
															double reduceVar$denom$1 = 0.0;
															for(int cv$reduction108Index = 0; cv$reduction108Index < (noProducts + 1); cv$reduction108Index += 1) {
																double k = reduceVar$denom$1;
																double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction108Index];
																reduceVar$denom$1 = (k + l);
															}
															weekly_rates[((t$var81 - 0) / 1)][j$var124] = (weekly_ut[((t$var81 - 0) / 1)][j$var124] / reduceVar$denom$1);
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
				double cv$temp$0$var8;
				{
					cv$temp$0$var8 = 0.0;
				}
				double cv$temp$1$var9;
				{
					cv$temp$1$var9 = 10.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var8) / Math.sqrt(cv$temp$1$var9))) - (0.5 * Math.log(cv$temp$1$var9))));
				{
					{
						boolean[] guard$sample22multinomial130 = guard$sample22multinomial130$global;
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
									if((j$var34 == j$var96)) {
										for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
											if(((0 <= j$var96) && (j$var96 < (noProducts + 1)))) {
												{
													guard$sample22multinomial130[((t$var81 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
						}
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
									if((j$var34 == j$var96)) {
										for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
											if((j$var96 == j$var124)) {
												for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
													guard$sample22multinomial130[((t$var81 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						double traceTempVariable$var35$9_1 = cv$currentValue;
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								double traceTempVariable$var97$9_3 = Math.exp(traceTempVariable$var35$9_1);
								for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
									if((j$var34 == j$var96)) {
										for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
											double traceTempVariable$k$9_6 = (traceTempVariable$var97$9_3 * Avail[t$var81][j$var96]);
											if(((0 <= j$var96) && (j$var96 < (noProducts + 1)))) {
												{
													if((0 < (noProducts + 1))) {
														double reduceVar$denom$2 = 0.0;
														for(int cv$reduction483Index = 0; cv$reduction483Index < j$var96; cv$reduction483Index += 1) {
															double k = reduceVar$denom$2;
															double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction483Index];
															reduceVar$denom$2 = (k + l);
														}
														for(int cv$reduction483Index = (j$var96 + 1); cv$reduction483Index < (noProducts + 1); cv$reduction483Index += 1) {
															double k = reduceVar$denom$2;
															double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction483Index];
															reduceVar$denom$2 = (k + l);
														}
														double cv$reduced108 = reduceVar$denom$2;
														reduceVar$denom$2 = (traceTempVariable$k$9_6 + cv$reduced108);
														double traceTempVariable$denom$9_7 = reduceVar$denom$2;
														if(!guard$sample22multinomial130[((t$var81 - 0) / 1)]) {
															guard$sample22multinomial130[((t$var81 - 0) / 1)] = true;
															{
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				double[] cv$temp$2$weekly_rates;
																				{
																					cv$temp$2$weekly_rates = weekly_rates[((t$var81 - 0) / 1)];
																				}
																				int cv$temp$3$$var429;
																				{
																					int $var429 = (noProducts + 1);
																					cv$temp$3$$var429 = $var429;
																				}
																				int cv$temp$4$var128;
																				{
																					int var128 = arrivals[t$var81];
																					cv$temp$4$var128 = var128;
																				}
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$$var429, cv$temp$4$var128)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$$var429, cv$temp$4$var128)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$$var429, cv$temp$4$var128));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$$var429, cv$temp$4$var128)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$$var429, cv$temp$4$var128)));
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
						double traceTempVariable$var35$10_1 = cv$currentValue;
						for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
							if((var21 == j$var34)) {
								double traceTempVariable$var97$10_3 = Math.exp(traceTempVariable$var35$10_1);
								for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
									if((j$var34 == j$var96)) {
										for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
											double traceTempVariable$var125$10_6 = (traceTempVariable$var97$10_3 * Avail[t$var81][j$var96]);
											for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
												if((j$var96 == j$var124)) {
													if(!guard$sample22multinomial130[((t$var81 - 0) / 1)]) {
														guard$sample22multinomial130[((t$var81 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			double[] cv$temp$5$weekly_rates;
																			{
																				cv$temp$5$weekly_rates = weekly_rates[((t$var81 - 0) / 1)];
																			}
																			int cv$temp$6$$var434;
																			{
																				int $var434 = (noProducts + 1);
																				cv$temp$6$$var434 = $var434;
																			}
																			int cv$temp$7$var128;
																			{
																				int var128 = arrivals[t$var81];
																				cv$temp$7$var128 = var128;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$5$weekly_rates, cv$temp$6$$var434, cv$temp$7$var128)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$5$weekly_rates, cv$temp$6$$var434, cv$temp$7$var128)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$5$weekly_rates, cv$temp$6$$var434, cv$temp$7$var128));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$5$weekly_rates, cv$temp$6$$var434, cv$temp$7$var128)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$5$weekly_rates, cv$temp$6$$var434, cv$temp$7$var128)));
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
			double var22 = cv$originalValue;
			ut[var21] = var22;
			{
				for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
					if((var21 == j$var34)) {
						{
							exped[j$var34] = Math.exp(ut[j$var34]);
						}
					}
				}
			}
			{
				for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
					if((var21 == j$var34)) {
						for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
							if((j$var34 == j$var96)) {
								for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
									weekly_ut[((t$var81 - 0) / 1)][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
							}
						}
					}
				}
			}
			{
				boolean[][] guard$sample22put128 = guard$sample22put128$global;
				for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
					if((var21 == j$var34)) {
						for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
							if((j$var34 == j$var96)) {
								for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
									if(((0 <= j$var96) && (j$var96 < (noProducts + 1)))) {
										{
											for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1)
												guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)] = false;
										}
									}
								}
							}
						}
					}
				}
				for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
					if((var21 == j$var34)) {
						for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
							if((j$var34 == j$var96)) {
								for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
									if((j$var96 == j$var124)) {
										for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
											guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)] = false;
									}
								}
							}
						}
					}
				}
				for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
					if((var21 == j$var34)) {
						for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
							if((j$var34 == j$var96)) {
								for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
									if(((0 <= j$var96) && (j$var96 < (noProducts + 1)))) {
										{
											for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
												if(!guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)]) {
													guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)] = true;
													{
														double reduceVar$denom$3 = 0.0;
														for(int cv$reduction108Index = 0; cv$reduction108Index < (noProducts + 1); cv$reduction108Index += 1) {
															double k = reduceVar$denom$3;
															double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction108Index];
															reduceVar$denom$3 = (k + l);
														}
														weekly_rates[((t$var81 - 0) / 1)][j$var124] = (weekly_ut[((t$var81 - 0) / 1)][j$var124] / reduceVar$denom$3);
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
				for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
					if((var21 == j$var34)) {
						for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
							if((j$var34 == j$var96)) {
								for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
									if((j$var96 == j$var124)) {
										for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
											if(!guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)]) {
												guard$sample22put128[((t$var81 - 0) / 1)][((j$var124 - 0) / 1)] = true;
												{
													double reduceVar$denom$4 = 0.0;
													for(int cv$reduction108Index = 0; cv$reduction108Index < (noProducts + 1); cv$reduction108Index += 1) {
														double k = reduceVar$denom$4;
														double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction108Index];
														reduceVar$denom$4 = (k + l);
													}
													weekly_rates[((t$var81 - 0) / 1)][j$var124] = (weekly_ut[((t$var81 - 0) / 1)][j$var124] / reduceVar$denom$4);
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

	private final void sample54(int var53) {
		double cv$sum = 0.0;
		int cv$count = 0;
		{
			{
				{
					for(int t$var66 = 0; t$var66 < T; t$var66 += 1) {
						if((var53 == t$var66)) {
							{
								{
									{
										{
											{
												cv$sum = (cv$sum + arrivals[t$var66]);
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
		double var54 = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, cv$sum, cv$count);
		lambda[var53] = var54;
	}

	private final void sample69(int t$var66) {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		int cv$originalValue = arrivals[t$var66];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < 1.0))
			cv$var = 1.0;
		double cv$offset = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		cv$offset = ((cv$offset <= 0.0)?(cv$offset - 1):(cv$offset + 1));
		int cv$proposedValue = (cv$originalValue + (int)cv$offset);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					int var69 = cv$proposedValue;
					arrivals[t$var66] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var67;
				{
					double var67 = lambda[t$var66];
					cv$temp$0$var67 = var67;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$currentValue, cv$temp$0$var67));
				{
					{
						int traceTempVariable$var128$1_1 = cv$currentValue;
						for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
							if((t$var66 == t$var81)) {
								{
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double[] cv$temp$1$weekly_rates;
													{
														cv$temp$1$weekly_rates = weekly_rates[((t$var81 - 0) / 1)];
													}
													int cv$temp$2$$var570;
													{
														int $var570 = (noProducts + 1);
														cv$temp$2$$var570 = $var570;
													}
													int cv$temp$3$var128;
													{
														int var128 = traceTempVariable$var128$1_1;
														cv$temp$3$var128 = var128;
													}
													if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$$var570, cv$temp$3$var128)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$$var570, cv$temp$3$var128)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$$var570, cv$temp$3$var128));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$$var570, cv$temp$3$var128)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var81 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$$var570, cv$temp$3$var128)));
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
			int var69 = cv$originalValue;
			arrivals[t$var66] = var69;
		}
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max_t$var81 = 0;
			int cv$max_j$var124 = 0;
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
				cv$max_j$var124 = Math.max(cv$max_j$var124, (((noProducts + 1) - 0) / 1));
			cv$max_t$var81 = Math.max(cv$max_t$var81, ((T - 0) / 1));
			guard$sample22put128$global = new boolean[cv$max_t$var81][cv$max_j$var124];
		}
		{
			int cv$max_t$var81 = 0;
			cv$max_t$var81 = Math.max(cv$max_t$var81, ((T - 0) / 1));
			guard$sample22multinomial130$global = new boolean[cv$max_t$var81];
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
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
				Sales[t$var81] = new int[noProducts];
		}
		{
			weekly_rates = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
				weekly_rates[((t$var81 - 0) / 1)] = new double[(noProducts + 1)];
		}
		{
			weekly_ut = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
				weekly_ut[((t$var81 - 0) / 1)] = new double[(noProducts + 1)];
		}
		if(!setFlag$weekly_sales) {
			{
				weekly_sales = new int[((((T - 1) - 0) / 1) + 1)][];
				for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
					weekly_sales[((t$var81 - 0) / 1)] = new int[(noProducts + 1)];
			}
		}
		{
			logProbability$sample22 = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var68 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample69 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var129 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample131 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int var21 = 0; var21 < noProducts; var21 += 1) {
			if(!fixedFlag$sample22)
				ut[var21] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
			if(!fixedFlag$sample22)
				exped[j$var34] = Math.exp(ut[j$var34]);
		}
		for(int var53 = 0; var53 < T; var53 += 1) {
			if(!fixedFlag$sample54)
				lambda[var53] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var66 = 0; t$var66 < T; t$var66 += 1) {
			if(!fixedFlag$sample69)
				arrivals[t$var66] = DistributionSampling.samplePoisson(RNG$, lambda[t$var66]);
		}
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
			for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
				if(!fixedFlag$sample22)
					weekly_ut[((t$var81 - 0) / 1)][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
			}
			weekly_ut[((t$var81 - 0) / 1)][noProducts] = 1.0;
			double reduceVar$denom$5 = 0.0;
			for(int cv$reduction108Index = 0; cv$reduction108Index < (noProducts + 1); cv$reduction108Index += 1) {
				double k = reduceVar$denom$5;
				double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction108Index];
				if(!fixedFlag$sample22)
					reduceVar$denom$5 = (k + l);
			}
			for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
				if(!fixedFlag$sample22)
					weekly_rates[((t$var81 - 0) / 1)][j$var124] = (weekly_ut[((t$var81 - 0) / 1)][j$var124] / reduceVar$denom$5);
			}
			if(!fixedFlag$sample131)
				DistributionSampling.sampleMultinomial(RNG$, weekly_rates[((t$var81 - 0) / 1)], (noProducts + 1), arrivals[t$var81], weekly_sales[((t$var81 - 0) / 1)]);
			int[] observed_weekly_sales = Sales[t$var81];
			for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
				if(!fixedFlag$sample131)
					observed_weekly_sales[j$var140] = weekly_sales[((t$var81 - 0) / 1)][j$var140];
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var21 = 0; var21 < noProducts; var21 += 1) {
			if(!fixedFlag$sample22)
				ut[var21] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
			if(!fixedFlag$sample22)
				exped[j$var34] = Math.exp(ut[j$var34]);
		}
		for(int var53 = 0; var53 < T; var53 += 1) {
			if(!fixedFlag$sample54)
				lambda[var53] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var66 = 0; t$var66 < T; t$var66 += 1) {
			if(!fixedFlag$sample69)
				arrivals[t$var66] = DistributionSampling.samplePoisson(RNG$, lambda[t$var66]);
		}
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
			for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
				if(!fixedFlag$sample22)
					weekly_ut[((t$var81 - 0) / 1)][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
			}
			weekly_ut[((t$var81 - 0) / 1)][noProducts] = 1.0;
			double reduceVar$denom$7 = 0.0;
			for(int cv$reduction108Index = 0; cv$reduction108Index < (noProducts + 1); cv$reduction108Index += 1) {
				double k = reduceVar$denom$7;
				double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction108Index];
				if(!fixedFlag$sample22)
					reduceVar$denom$7 = (k + l);
			}
			for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
				if(!fixedFlag$sample22)
					weekly_rates[((t$var81 - 0) / 1)][j$var124] = (weekly_ut[((t$var81 - 0) / 1)][j$var124] / reduceVar$denom$7);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var21 = 0; var21 < noProducts; var21 += 1) {
			if(!fixedFlag$sample22)
				ut[var21] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
			if(!fixedFlag$sample22)
				exped[j$var34] = Math.exp(ut[j$var34]);
		}
		for(int var53 = 0; var53 < T; var53 += 1) {
			if(!fixedFlag$sample54)
				lambda[var53] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var66 = 0; t$var66 < T; t$var66 += 1) {
			if(!fixedFlag$sample69)
				arrivals[t$var66] = DistributionSampling.samplePoisson(RNG$, lambda[t$var66]);
		}
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
			for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
				if(!fixedFlag$sample22)
					weekly_ut[((t$var81 - 0) / 1)][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
			}
			weekly_ut[((t$var81 - 0) / 1)][noProducts] = 1.0;
			double reduceVar$denom$6 = 0.0;
			for(int cv$reduction108Index = 0; cv$reduction108Index < (noProducts + 1); cv$reduction108Index += 1) {
				double k = reduceVar$denom$6;
				double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction108Index];
				if(!fixedFlag$sample22)
					reduceVar$denom$6 = (k + l);
			}
			for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
				if(!fixedFlag$sample22)
					weekly_rates[((t$var81 - 0) / 1)][j$var124] = (weekly_ut[((t$var81 - 0) / 1)][j$var124] / reduceVar$denom$6);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var21 = 0; var21 < noProducts; var21 += 1) {
				if(!fixedFlag$sample22)
					sample22(var21);
			}
			for(int var53 = 0; var53 < T; var53 += 1) {
				if(!fixedFlag$sample54)
					sample54(var53);
			}
			for(int t$var66 = 0; t$var66 < T; t$var66 += 1) {
				if(!fixedFlag$sample69)
					sample69(t$var66);
			}
		} else {
			for(int t$var66 = (T - ((((T - 1) - 0) % 1) + 1)); t$var66 >= ((0 - 1) + 1); t$var66 -= 1) {
				if(!fixedFlag$sample69)
					sample69(t$var66);
			}
			for(int var53 = (T - ((((T - 1) - 0) % 1) + 1)); var53 >= ((0 - 1) + 1); var53 -= 1) {
				if(!fixedFlag$sample54)
					sample54(var53);
			}
			for(int var21 = (noProducts - ((((noProducts - 1) - 0) % 1) + 1)); var21 >= ((0 - 1) + 1); var21 -= 1) {
				if(!fixedFlag$sample22)
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
				logProbability$sample22[((var21 - 0) / 1)] = 0.0;
		}
		logProbability$var42 = 0.0;
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample54)
			logProbability$var54 = 0.0;
		for(int t$var66 = 0; t$var66 < T; t$var66 += 1)
			logProbability$var68[((t$var66 - 0) / 1)] = 0.0;
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample69) {
			for(int t$var66 = 0; t$var66 < T; t$var66 += 1)
				logProbability$sample69[((t$var66 - 0) / 1)] = 0.0;
		}
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
			logProbability$var129[((t$var81 - 0) / 1)] = 0.0;
		logProbability$weekly_sales = 0.0;
		logProbability$Sales = 0.0;
		if(!fixedProbFlag$sample131) {
			for(int t$var81 = 0; t$var81 < T; t$var81 += 1)
				logProbability$sample131[((t$var81 - 0) / 1)] = 0.0;
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
		for(int var21 = 0; var21 < noProducts; var21 += 1) {
			if(!fixedFlag$sample22)
				ut[var21] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
			if(!fixedFlag$sample22)
				exped[j$var34] = Math.exp(ut[j$var34]);
		}
		for(int var53 = 0; var53 < T; var53 += 1) {
			if(!fixedFlag$sample54)
				lambda[var53] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var66 = 0; t$var66 < T; t$var66 += 1) {
			if(!fixedFlag$sample69)
				arrivals[t$var66] = DistributionSampling.samplePoisson(RNG$, lambda[t$var66]);
		}
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
			for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
				if(!fixedFlag$sample22)
					weekly_ut[((t$var81 - 0) / 1)][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
			}
			weekly_ut[((t$var81 - 0) / 1)][noProducts] = 1.0;
			double reduceVar$denom$8 = 0.0;
			for(int cv$reduction108Index = 0; cv$reduction108Index < (noProducts + 1); cv$reduction108Index += 1) {
				double k = reduceVar$denom$8;
				double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction108Index];
				if(!fixedFlag$sample22)
					reduceVar$denom$8 = (k + l);
			}
			for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
				if(!fixedFlag$sample22)
					weekly_rates[((t$var81 - 0) / 1)][j$var124] = (weekly_ut[((t$var81 - 0) / 1)][j$var124] / reduceVar$denom$8);
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
		for(int t$var81 = (T - ((((T - 1) - 0) % 1) + 1)); t$var81 >= ((0 - 1) + 1); t$var81 -= 1) {
			for(int j$var140 = (noProducts - ((((noProducts - 1) - 0) % 1) + 1)); j$var140 >= ((0 - 1) + 1); j$var140 -= 1) {
				int[] observed_weekly_sales;
				observed_weekly_sales = Sales[t$var81];
				weekly_sales[((t$var81 - 0) / 1)][j$var140] = observed_weekly_sales[j$var140];
			}
		}
	}

	@Override
	public final void setIntermediates() {
		for(int j$var34 = 0; j$var34 < noProducts; j$var34 += 1) {
			if(setFlag$ut)
				exped[j$var34] = Math.exp(ut[j$var34]);
		}
		for(int t$var81 = 0; t$var81 < T; t$var81 += 1) {
			for(int j$var96 = 0; j$var96 < noProducts; j$var96 += 1) {
				if(setFlag$ut)
					weekly_ut[((t$var81 - 0) / 1)][j$var96] = (exped[j$var96] * Avail[t$var81][j$var96]);
			}
			double reduceVar$denom$9 = 0.0;
			for(int cv$reduction108Index = 0; cv$reduction108Index < (noProducts + 1); cv$reduction108Index += 1) {
				double k = reduceVar$denom$9;
				double l = weekly_ut[((t$var81 - 0) / 1)][cv$reduction108Index];
				reduceVar$denom$9 = (k + l);
			}
			for(int j$var124 = 0; j$var124 < (noProducts + 1); j$var124 += 1) {
				if(setFlag$ut)
					weekly_rates[((t$var81 - 0) / 1)][j$var124] = (weekly_ut[((t$var81 - 0) / 1)][j$var124] / reduceVar$denom$9);
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