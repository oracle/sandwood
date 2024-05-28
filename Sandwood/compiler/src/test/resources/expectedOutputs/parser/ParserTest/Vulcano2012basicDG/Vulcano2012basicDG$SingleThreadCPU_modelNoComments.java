package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Vulcano2012basicDG$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Vulcano2012basicDG$CoreInterface {
	private int[][] ObsSales;
	private int[] arrivals;
	private boolean[][] avail;
	private double[] exped;
	private double[] expedNorm;
	private boolean fixedFlag$sample127 = false;
	private boolean fixedFlag$sample129 = false;
	private boolean fixedFlag$sample181 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedProbFlag$sample127 = false;
	private boolean fixedProbFlag$sample129 = false;
	private boolean fixedProbFlag$sample181 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean[] guard$sample45multinomial180$global;
	private boolean[][] guard$sample45put150$global;
	private boolean[][] guard$sample45put179$global;
	private boolean[] guard$sample45put86$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$arrivals;
	private double logProbability$exped;
	private double logProbability$expedNorm;
	private double logProbability$lambda;
	private double logProbability$sales;
	private double[] logProbability$sample127;
	private double[] logProbability$sample129;
	private double[] logProbability$sample181;
	private double[] logProbability$sample45;
	private double logProbability$ut;
	private double[] logProbability$var116;
	private double[] logProbability$var118;
	private double[] logProbability$var167;
	private double logProbability$var28;
	private double logProbability$weekly_rates;
	private double logProbability$weekly_sales;
	private double logProbability$weekly_ut;
	private int numTimeSteps;
	private double r;
	private int[][] sales;
	private boolean setFlag$arrivals = false;
	private boolean setFlag$lambda = false;
	private boolean setFlag$weekly_sales = false;
	private boolean system$gibbsForward = true;
	private double[] ut;
	private double[][] weekly_rates;
	private int[][] weekly_sales;
	private double[][] weekly_ut;

	public Vulcano2012basicDG$SingleThreadCPU(ExecutionTarget target) {
		super(target);
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
	public final int[] get$arrivals() {
		return arrivals;
	}

	@Override
	public final void set$arrivals(int[] cv$value) {
		arrivals = cv$value;
		setFlag$arrivals = true;
	}

	@Override
	public final boolean[][] get$avail() {
		return avail;
	}

	@Override
	public final void set$avail(boolean[][] cv$value) {
		avail = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample127() {
		return fixedFlag$sample127;
	}

	@Override
	public final void set$fixedFlag$sample127(boolean cv$value) {
		fixedFlag$sample127 = cv$value;
		fixedProbFlag$sample127 = (fixedFlag$sample127 && fixedProbFlag$sample127);
		fixedProbFlag$sample129 = (fixedFlag$sample127 && fixedProbFlag$sample129);
	}

	@Override
	public final boolean get$fixedFlag$sample129() {
		return fixedFlag$sample129;
	}

	@Override
	public final void set$fixedFlag$sample129(boolean cv$value) {
		fixedFlag$sample129 = cv$value;
		fixedProbFlag$sample129 = (fixedFlag$sample129 && fixedProbFlag$sample129);
		fixedProbFlag$sample181 = (fixedFlag$sample129 && fixedProbFlag$sample181);
	}

	@Override
	public final boolean get$fixedFlag$sample181() {
		return fixedFlag$sample181;
	}

	@Override
	public final void set$fixedFlag$sample181(boolean cv$value) {
		fixedFlag$sample181 = cv$value;
		fixedProbFlag$sample181 = (fixedFlag$sample181 && fixedProbFlag$sample181);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		fixedFlag$sample45 = cv$value;
		fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedProbFlag$sample45);
		fixedProbFlag$sample181 = (fixedFlag$sample45 && fixedProbFlag$sample181);
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
	public final double get$logProbability$arrivals() {
		return logProbability$arrivals;
	}

	@Override
	public final double get$logProbability$lambda() {
		return logProbability$lambda;
	}

	@Override
	public final double get$logProbability$weekly_sales() {
		return logProbability$weekly_sales;
	}

	@Override
	public final int get$numTimeSteps() {
		return numTimeSteps;
	}

	@Override
	public final double get$r() {
		return r;
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

	private final void logProbabilityValue$sample127() {
		if(!fixedProbFlag$sample127) {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						if((0 < numTimeSteps)) {
							double cv$sampleValue = lambda[((t - 0) / 1)];
							{
								{
									double var114 = 10.0;
									double var115 = 10.0;
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGamma(cv$sampleValue, var114, var115));
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
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$var116[((t - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample127[((t - 0) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample127)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample127 = fixedFlag$sample127;
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample127[((t - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var116[((t - 0) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample127)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample129() {
		if(!fixedProbFlag$sample129) {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						if((0 < numTimeSteps)) {
							int reduceVar$numSales$4 = 0;
							for(int cv$reduction115Index = 0; cv$reduction115Index < ObsSales[t].length; cv$reduction115Index += 1) {
								int k$var108 = reduceVar$numSales$4;
								int l$var109 = ObsSales[t][cv$reduction115Index];
								reduceVar$numSales$4 = (k$var108 + l$var109);
							}
							int cv$sampleValue = (arrivals[((t - 0) / 1)] - reduceVar$numSales$4);
							{
								{
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$sampleValue, lambda[((t - 0) / 1)]));
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
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$var118[((t - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample129[((t - 0) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample129)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample129 = (fixedFlag$sample129 && fixedFlag$sample127);
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample129[((t - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var118[((t - 0) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample129)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample181() {
		if(!fixedProbFlag$sample181) {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						if((0 < numTimeSteps)) {
							int[] cv$sampleValue = weekly_sales[((t - 0) / 1)];
							{
								{
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(cv$sampleValue, weekly_rates[((t - 0) / 1)], arrivals[((t - 0) / 1)]));
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
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$var167[((t - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample181[((t - 0) / 1)] = cv$sampleProbability;
					boolean cv$guard$sales = false;
					{
						if((0 < numTimeSteps)) {
							for(int j$var178 = 0; j$var178 < avail[0].length; j$var178 += 1) {
								if(!cv$guard$sales) {
									cv$guard$sales = true;
									logProbability$sales = (logProbability$sales + cv$sampleProbability);
								}
							}
						}
					}
				}
			}
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample181 = ((fixedFlag$sample181 && fixedFlag$sample45) && fixedFlag$sample129);
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample181[((t - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var167[((t - 0) / 1)] = cv$rvAccumulator;
					boolean cv$guard$sales = false;
					{
						if((0 < numTimeSteps)) {
							for(int j$var178 = 0; j$var178 < avail[0].length; j$var178 += 1) {
								if(!cv$guard$sales) {
									cv$guard$sales = true;
									logProbability$sales = (logProbability$sales + cv$sampleValue);
								}
							}
						}
					}
				}
			}
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!fixedProbFlag$sample45) {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				double cv$sampleAccumulator = 0.0;
				for(int var38 = 0; var38 < avail[0].length; var38 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						if((0 < numTimeSteps)) {
							double cv$sampleValue = ut[var38];
							{
								{
									double var26 = 0.0;
									double var27 = 1.0;
									double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var26) / Math.sqrt(var27))) - (0.5 * Math.log(var27))));
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
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					logProbability$sample45[((var38 - 0) / 1)] = cv$sampleProbability;
					boolean cv$guard$exped = false;
					boolean cv$guard$expedNorm = false;
					boolean cv$guard$weekly_ut = false;
					boolean cv$guard$weekly_rates = false;
					{
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									if(!cv$guard$exped) {
										cv$guard$exped = true;
										logProbability$exped = (logProbability$exped + cv$sampleProbability);
									}
								}
							}
						}
					}
					{
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
										{
											for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
												if(!cv$guard$expedNorm) {
													cv$guard$expedNorm = true;
													logProbability$expedNorm = (logProbability$expedNorm + cv$sampleProbability);
												}
											}
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										if((j$var50 == j$var75)) {
											if(!cv$guard$expedNorm) {
												cv$guard$expedNorm = true;
												logProbability$expedNorm = (logProbability$expedNorm + cv$sampleProbability);
											}
										}
									}
								}
							}
						}
					}
					{
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
										{
											for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
														if(avail[t][j$var131]) {
															if((j$var75 == j$var131)) {
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
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										if((j$var50 == j$var75)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
													if(avail[t][j$var131]) {
														if((j$var75 == j$var131)) {
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
								}
							}
						}
					}
					{
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
										{
											for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
														if(avail[t][j$var131]) {
															if((j$var75 == j$var131)) {
																if(((0 <= j$var131) && (j$var131 < (avail[0].length + 1)))) {
																	{
																		for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
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
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
										{
											for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
														if(avail[t][j$var131]) {
															if((j$var75 == j$var131)) {
																for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
																	if((j$var131 == j$var163)) {
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
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										if((j$var50 == j$var75)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
													if(avail[t][j$var131]) {
														if((j$var75 == j$var131)) {
															if(((0 <= j$var131) && (j$var131 < (avail[0].length + 1)))) {
																{
																	for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
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
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										if((j$var50 == j$var75)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
													if(avail[t][j$var131]) {
														if((j$var75 == j$var131)) {
															for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
																if((j$var131 == j$var163)) {
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
							}
						}
					}
				}
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var28 = cv$sampleAccumulator;
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample45 = fixedFlag$sample45;
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				double cv$rvAccumulator = 0.0;
				for(int var38 = 0; var38 < avail[0].length; var38 += 1) {
					double cv$sampleValue = logProbability$sample45[((var38 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					boolean cv$guard$exped = false;
					boolean cv$guard$expedNorm = false;
					boolean cv$guard$weekly_ut = false;
					boolean cv$guard$weekly_rates = false;
					{
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									if(!cv$guard$exped) {
										cv$guard$exped = true;
										logProbability$exped = (logProbability$exped + cv$sampleValue);
									}
								}
							}
						}
					}
					{
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
										{
											for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
												if(!cv$guard$expedNorm) {
													cv$guard$expedNorm = true;
													logProbability$expedNorm = (logProbability$expedNorm + cv$sampleValue);
												}
											}
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										if((j$var50 == j$var75)) {
											if(!cv$guard$expedNorm) {
												cv$guard$expedNorm = true;
												logProbability$expedNorm = (logProbability$expedNorm + cv$sampleValue);
											}
										}
									}
								}
							}
						}
					}
					{
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
										{
											for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
														if(avail[t][j$var131]) {
															if((j$var75 == j$var131)) {
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
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										if((j$var50 == j$var75)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
													if(avail[t][j$var131]) {
														if((j$var75 == j$var131)) {
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
								}
							}
						}
					}
					{
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
										{
											for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
														if(avail[t][j$var131]) {
															if((j$var75 == j$var131)) {
																if(((0 <= j$var131) && (j$var131 < (avail[0].length + 1)))) {
																	{
																		for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
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
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
										{
											for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
														if(avail[t][j$var131]) {
															if((j$var75 == j$var131)) {
																for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
																	if((j$var131 == j$var163)) {
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
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										if((j$var50 == j$var75)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
													if(avail[t][j$var131]) {
														if((j$var75 == j$var131)) {
															if(((0 <= j$var131) && (j$var131 < (avail[0].length + 1)))) {
																{
																	for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
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
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										if((j$var50 == j$var75)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
													if(avail[t][j$var131]) {
														if((j$var75 == j$var131)) {
															for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
																if((j$var131 == j$var163)) {
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
							}
						}
					}
				}
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var28 = cv$rvAccumulator;
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample127(int t) {
		double cv$sum = 0.0;
		int cv$count = 0;
		{
			{
				{
					if((0 < numTimeSteps)) {
						{
							if((0 < numTimeSteps)) {
								{
									{
										{
											{
												if((0 < numTimeSteps)) {
													int reduceVar$numSales$0 = 0;
													for(int cv$reduction115Index = 0; cv$reduction115Index < ObsSales[t].length; cv$reduction115Index += 1) {
														int k$var108 = reduceVar$numSales$0;
														int l$var109 = ObsSales[t][cv$reduction115Index];
														reduceVar$numSales$0 = (k$var108 + l$var109);
													}
													cv$sum = (cv$sum + (arrivals[((t - 0) / 1)] - reduceVar$numSales$0));
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
		}
		lambda[((t - 0) / 1)] = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, cv$sum, cv$count);
	}

	private final void sample129(int t) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		int reduceVar$numSales$1 = 0;
		for(int cv$reduction115Index = 0; cv$reduction115Index < ObsSales[t].length; cv$reduction115Index += 1) {
			int k$var108 = reduceVar$numSales$1;
			int l$var109 = ObsSales[t][cv$reduction115Index];
			reduceVar$numSales$1 = (k$var108 + l$var109);
		}
		int cv$originalValue = (arrivals[((t - 0) / 1)] - reduceVar$numSales$1);
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
					int var119 = cv$proposedValue;
					int reduceVar$numSales$2 = 0;
					for(int cv$reduction115Index = 0; cv$reduction115Index < ObsSales[t].length; cv$reduction115Index += 1) {
						int k$var108 = reduceVar$numSales$2;
						int l$var109 = ObsSales[t][cv$reduction115Index];
						reduceVar$numSales$2 = (k$var108 + l$var109);
					}
					arrivals[((t - 0) / 1)] = (reduceVar$numSales$2 + cv$currentValue);
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$lambda;
				{
					cv$temp$0$lambda = lambda[((t - 0) / 1)];
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$currentValue, cv$temp$0$lambda));
				{
					{
						if((0 < numTimeSteps)) {
							int traceTempVariable$arrivals$1_1 = arrivals[((t - 0) / 1)];
							{
								if((0 < numTimeSteps)) {
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													if((0 < numTimeSteps)) {
														double[] cv$temp$1$weekly_rates;
														{
															cv$temp$1$weekly_rates = weekly_rates[((t - 0) / 1)];
														}
														int cv$temp$2$arrivals;
														{
															cv$temp$2$arrivals = traceTempVariable$arrivals$1_1;
														}
														if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$arrivals)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$arrivals)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$arrivals));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$arrivals)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$arrivals)));
														}
														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
													}
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
			int var119 = cv$originalValue;
			int reduceVar$numSales$3 = 0;
			for(int cv$reduction115Index = 0; cv$reduction115Index < ObsSales[t].length; cv$reduction115Index += 1) {
				int k$var108 = reduceVar$numSales$3;
				int l$var109 = ObsSales[t][cv$reduction115Index];
				reduceVar$numSales$3 = (k$var108 + l$var109);
			}
			arrivals[((t - 0) / 1)] = (reduceVar$numSales$3 + var119);
		}
	}

	private final void sample45(int var38) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = ut[var38];
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
					double var39 = cv$proposedValue;
					ut[var38] = cv$currentValue;
					{
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									{
										exped[j$var50] = Math.exp(ut[j$var50]);
									}
								}
							}
						}
					}
					{
						boolean[] guard$sample45put86 = guard$sample45put86$global;
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
										{
											for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1)
												guard$sample45put86[((j$var75 - 0) / 1)] = false;
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										if((j$var50 == j$var75))
											guard$sample45put86[((j$var75 - 0) / 1)] = false;
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
										{
											for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
												if(!guard$sample45put86[((j$var75 - 0) / 1)]) {
													guard$sample45put86[((j$var75 - 0) / 1)] = true;
													{
														double reduceVar$sum$0 = 0.0;
														for(int cv$reduction67Index = 0; cv$reduction67Index < avail[0].length; cv$reduction67Index += 1) {
															double k$var63 = reduceVar$sum$0;
															double l$var64 = exped[cv$reduction67Index];
															reduceVar$sum$0 = (k$var63 + l$var64);
														}
														expedNorm[j$var75] = (exped[j$var75] / (r * reduceVar$sum$0));
													}
												}
											}
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										if((j$var50 == j$var75)) {
											if(!guard$sample45put86[((j$var75 - 0) / 1)]) {
												guard$sample45put86[((j$var75 - 0) / 1)] = true;
												{
													double reduceVar$sum$1 = 0.0;
													for(int cv$reduction67Index = 0; cv$reduction67Index < avail[0].length; cv$reduction67Index += 1) {
														double k$var63 = reduceVar$sum$1;
														double l$var64 = exped[cv$reduction67Index];
														reduceVar$sum$1 = (k$var63 + l$var64);
													}
													expedNorm[j$var75] = (exped[j$var75] / (r * reduceVar$sum$1));
												}
											}
										}
									}
								}
							}
						}
					}
					{
						boolean[][] guard$sample45put150 = guard$sample45put150$global;
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
										{
											for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
														if(avail[t][j$var131]) {
															if((j$var75 == j$var131))
																guard$sample45put150[((t - 0) / 1)][((j$var131 - 0) / 1)] = false;
														}
													}
												}
											}
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										if((j$var50 == j$var75)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
													if(avail[t][j$var131]) {
														if((j$var75 == j$var131))
															guard$sample45put150[((t - 0) / 1)][((j$var131 - 0) / 1)] = false;
													}
												}
											}
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
										{
											for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
														if(avail[t][j$var131]) {
															if((j$var75 == j$var131)) {
																if(!guard$sample45put150[((t - 0) / 1)][((j$var131 - 0) / 1)]) {
																	guard$sample45put150[((t - 0) / 1)][((j$var131 - 0) / 1)] = true;
																	{
																		weekly_ut[((t - 0) / 1)][j$var131] = expedNorm[j$var131];
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
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										if((j$var50 == j$var75)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
													if(avail[t][j$var131]) {
														if((j$var75 == j$var131)) {
															if(!guard$sample45put150[((t - 0) / 1)][((j$var131 - 0) / 1)]) {
																guard$sample45put150[((t - 0) / 1)][((j$var131 - 0) / 1)] = true;
																{
																	weekly_ut[((t - 0) / 1)][j$var131] = expedNorm[j$var131];
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
						boolean[][] guard$sample45put179 = guard$sample45put179$global;
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
										{
											for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
														if(avail[t][j$var131]) {
															if((j$var75 == j$var131)) {
																if(((0 <= j$var131) && (j$var131 < (avail[0].length + 1)))) {
																	{
																		for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1)
																			guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)] = false;
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
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
										{
											for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
														if(avail[t][j$var131]) {
															if((j$var75 == j$var131)) {
																for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
																	if((j$var131 == j$var163))
																		guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)] = false;
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
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										if((j$var50 == j$var75)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
													if(avail[t][j$var131]) {
														if((j$var75 == j$var131)) {
															if(((0 <= j$var131) && (j$var131 < (avail[0].length + 1)))) {
																{
																	for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1)
																		guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)] = false;
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
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										if((j$var50 == j$var75)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
													if(avail[t][j$var131]) {
														if((j$var75 == j$var131)) {
															for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
																if((j$var131 == j$var163))
																	guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)] = false;
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
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
										{
											for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
														if(avail[t][j$var131]) {
															if((j$var75 == j$var131)) {
																if(((0 <= j$var131) && (j$var131 < (avail[0].length + 1)))) {
																	{
																		for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
																			if(!guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)]) {
																				guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)] = true;
																				{
																					double reduceVar$denom$0 = 0.0;
																					for(int cv$reduction159Index = 0; cv$reduction159Index < (avail[0].length + 1); cv$reduction159Index += 1) {
																						double k$var149 = reduceVar$denom$0;
																						double l$var150 = weekly_ut[((t - 0) / 1)][cv$reduction159Index];
																						reduceVar$denom$0 = (k$var149 + l$var150);
																					}
																					weekly_rates[((t - 0) / 1)][j$var163] = (weekly_ut[((t - 0) / 1)][j$var163] / reduceVar$denom$0);
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
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
										{
											for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
														if(avail[t][j$var131]) {
															if((j$var75 == j$var131)) {
																for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
																	if((j$var131 == j$var163)) {
																		if(!guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)]) {
																			guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)] = true;
																			{
																				double reduceVar$denom$1 = 0.0;
																				for(int cv$reduction159Index = 0; cv$reduction159Index < (avail[0].length + 1); cv$reduction159Index += 1) {
																					double k$var149 = reduceVar$denom$1;
																					double l$var150 = weekly_ut[((t - 0) / 1)][cv$reduction159Index];
																					reduceVar$denom$1 = (k$var149 + l$var150);
																				}
																				weekly_rates[((t - 0) / 1)][j$var163] = (weekly_ut[((t - 0) / 1)][j$var163] / reduceVar$denom$1);
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
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										if((j$var50 == j$var75)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
													if(avail[t][j$var131]) {
														if((j$var75 == j$var131)) {
															if(((0 <= j$var131) && (j$var131 < (avail[0].length + 1)))) {
																{
																	for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
																		if(!guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)]) {
																			guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)] = true;
																			{
																				double reduceVar$denom$2 = 0.0;
																				for(int cv$reduction159Index = 0; cv$reduction159Index < (avail[0].length + 1); cv$reduction159Index += 1) {
																					double k$var149 = reduceVar$denom$2;
																					double l$var150 = weekly_ut[((t - 0) / 1)][cv$reduction159Index];
																					reduceVar$denom$2 = (k$var149 + l$var150);
																				}
																				weekly_rates[((t - 0) / 1)][j$var163] = (weekly_ut[((t - 0) / 1)][j$var163] / reduceVar$denom$2);
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
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										if((j$var50 == j$var75)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
													if(avail[t][j$var131]) {
														if((j$var75 == j$var131)) {
															for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
																if((j$var131 == j$var163)) {
																	if(!guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)]) {
																		guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)] = true;
																		{
																			double reduceVar$denom$3 = 0.0;
																			for(int cv$reduction159Index = 0; cv$reduction159Index < (avail[0].length + 1); cv$reduction159Index += 1) {
																				double k$var149 = reduceVar$denom$3;
																				double l$var150 = weekly_ut[((t - 0) / 1)][cv$reduction159Index];
																				reduceVar$denom$3 = (k$var149 + l$var150);
																			}
																			weekly_rates[((t - 0) / 1)][j$var163] = (weekly_ut[((t - 0) / 1)][j$var163] / reduceVar$denom$3);
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
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var26;
				{
					cv$temp$0$var26 = 0.0;
				}
				double cv$temp$1$var27;
				{
					cv$temp$1$var27 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var26) / Math.sqrt(cv$temp$1$var27))) - (0.5 * Math.log(cv$temp$1$var27))));
				{
					{
						boolean[] guard$sample45multinomial180 = guard$sample45multinomial180$global;
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
										{
											for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
														if(avail[t][j$var131]) {
															if((j$var75 == j$var131)) {
																if(((0 <= j$var131) && (j$var131 < (avail[0].length + 1)))) {
																	{
																		guard$sample45multinomial180[((t - 0) / 1)] = false;
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
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
										{
											for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
														if(avail[t][j$var131]) {
															if((j$var75 == j$var131)) {
																for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
																	if((j$var131 == j$var163))
																		guard$sample45multinomial180[((t - 0) / 1)] = false;
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
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										if((j$var50 == j$var75)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
													if(avail[t][j$var131]) {
														if((j$var75 == j$var131)) {
															if(((0 <= j$var131) && (j$var131 < (avail[0].length + 1)))) {
																{
																	guard$sample45multinomial180[((t - 0) / 1)] = false;
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
						if((0 < numTimeSteps)) {
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										if((j$var50 == j$var75)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
													if(avail[t][j$var131]) {
														if((j$var75 == j$var131)) {
															for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
																if((j$var131 == j$var163))
																	guard$sample45multinomial180[((t - 0) / 1)] = false;
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
						if((0 < numTimeSteps)) {
							double traceTempVariable$var51$22_1 = cv$currentValue;
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									double traceTempVariable$k$22_3 = Math.exp(traceTempVariable$var51$22_1);
									if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
										{
											if(((0 < exped.length) && (0 < avail[0].length))) {
												double reduceVar$sum$2 = 0.0;
												for(int cv$reduction798Index = 0; cv$reduction798Index < j$var50; cv$reduction798Index += 1) {
													double k$var63 = reduceVar$sum$2;
													double l$var64 = exped[cv$reduction798Index];
													reduceVar$sum$2 = (k$var63 + l$var64);
												}
												for(int cv$reduction798Index = (j$var50 + 1); cv$reduction798Index < avail[0].length; cv$reduction798Index += 1) {
													double k$var63 = reduceVar$sum$2;
													double l$var64 = exped[cv$reduction798Index];
													reduceVar$sum$2 = (k$var63 + l$var64);
												}
												double cv$reduced67 = reduceVar$sum$2;
												reduceVar$sum$2 = (traceTempVariable$k$22_3 + cv$reduced67);
												double traceTempVariable$sum$22_4 = reduceVar$sum$2;
												for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
													double traceTempVariable$var137$22_6 = (exped[j$var75] / (r * traceTempVariable$sum$22_4));
													for(int t = 0; t < numTimeSteps; t += 1) {
														for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
															if(avail[t][j$var131]) {
																if((j$var75 == j$var131)) {
																	double traceTempVariable$k$22_9 = traceTempVariable$var137$22_6;
																	if(((0 <= j$var131) && (j$var131 < (avail[0].length + 1)))) {
																		{
																			if(((0 < weekly_ut[((t - 0) / 1)].length) && (0 < (avail[0].length + 1)))) {
																				double reduceVar$denom$4 = 0.0;
																				for(int cv$reduction819Index = 0; cv$reduction819Index < j$var131; cv$reduction819Index += 1) {
																					double k$var149 = reduceVar$denom$4;
																					double l$var150 = weekly_ut[((t - 0) / 1)][cv$reduction819Index];
																					reduceVar$denom$4 = (k$var149 + l$var150);
																				}
																				for(int cv$reduction819Index = (j$var131 + 1); cv$reduction819Index < (avail[0].length + 1); cv$reduction819Index += 1) {
																					double k$var149 = reduceVar$denom$4;
																					double l$var150 = weekly_ut[((t - 0) / 1)][cv$reduction819Index];
																					reduceVar$denom$4 = (k$var149 + l$var150);
																				}
																				double cv$reduced159 = reduceVar$denom$4;
																				reduceVar$denom$4 = (traceTempVariable$k$22_9 + cv$reduced159);
																				double traceTempVariable$denom$22_10 = reduceVar$denom$4;
																				if(!guard$sample45multinomial180[((t - 0) / 1)]) {
																					guard$sample45multinomial180[((t - 0) / 1)] = true;
																					{
																						if((0 < numTimeSteps)) {
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									{
																										{
																											if((0 < numTimeSteps)) {
																												double[] cv$temp$2$weekly_rates;
																												{
																													cv$temp$2$weekly_rates = weekly_rates[((t - 0) / 1)];
																												}
																												int cv$temp$3$arrivals;
																												{
																													cv$temp$3$arrivals = arrivals[((t - 0) / 1)];
																												}
																												if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$arrivals)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$arrivals)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$arrivals));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$arrivals)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$arrivals)));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
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
											}
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							double traceTempVariable$var51$23_1 = cv$currentValue;
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									double traceTempVariable$k$23_3 = Math.exp(traceTempVariable$var51$23_1);
									if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
										{
											if(((0 < exped.length) && (0 < avail[0].length))) {
												double reduceVar$sum$3 = 0.0;
												for(int cv$reduction846Index = 0; cv$reduction846Index < j$var50; cv$reduction846Index += 1) {
													double k$var63 = reduceVar$sum$3;
													double l$var64 = exped[cv$reduction846Index];
													reduceVar$sum$3 = (k$var63 + l$var64);
												}
												for(int cv$reduction846Index = (j$var50 + 1); cv$reduction846Index < avail[0].length; cv$reduction846Index += 1) {
													double k$var63 = reduceVar$sum$3;
													double l$var64 = exped[cv$reduction846Index];
													reduceVar$sum$3 = (k$var63 + l$var64);
												}
												double cv$reduced67 = reduceVar$sum$3;
												reduceVar$sum$3 = (traceTempVariable$k$23_3 + cv$reduced67);
												double traceTempVariable$sum$23_4 = reduceVar$sum$3;
												for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
													double traceTempVariable$var137$23_6 = (exped[j$var75] / (r * traceTempVariable$sum$23_4));
													for(int t = 0; t < numTimeSteps; t += 1) {
														for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
															if(avail[t][j$var131]) {
																if((j$var75 == j$var131)) {
																	double traceTempVariable$var164$23_9 = traceTempVariable$var137$23_6;
																	for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
																		if((j$var131 == j$var163)) {
																			if(!guard$sample45multinomial180[((t - 0) / 1)]) {
																				guard$sample45multinomial180[((t - 0) / 1)] = true;
																				{
																					if((0 < numTimeSteps)) {
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										if((0 < numTimeSteps)) {
																											double[] cv$temp$4$weekly_rates;
																											{
																												cv$temp$4$weekly_rates = weekly_rates[((t - 0) / 1)];
																											}
																											int cv$temp$5$arrivals;
																											{
																												cv$temp$5$arrivals = arrivals[((t - 0) / 1)];
																											}
																											if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$arrivals)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$arrivals)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$arrivals));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$arrivals)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$arrivals)));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																										}
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
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							double traceTempVariable$var51$24_1 = cv$currentValue;
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									double traceTempVariable$var76$24_3 = Math.exp(traceTempVariable$var51$24_1);
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										if((j$var50 == j$var75)) {
											double reduceVar$sum$4 = 0.0;
											for(int cv$reduction67Index = 0; cv$reduction67Index < avail[0].length; cv$reduction67Index += 1) {
												double k$var63 = reduceVar$sum$4;
												double l$var64 = exped[cv$reduction67Index];
												reduceVar$sum$4 = (k$var63 + l$var64);
											}
											double traceTempVariable$var137$24_5 = (traceTempVariable$var76$24_3 / (r * reduceVar$sum$4));
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
													if(avail[t][j$var131]) {
														if((j$var75 == j$var131)) {
															double traceTempVariable$k$24_8 = traceTempVariable$var137$24_5;
															if(((0 <= j$var131) && (j$var131 < (avail[0].length + 1)))) {
																{
																	if(((0 < weekly_ut[((t - 0) / 1)].length) && (0 < (avail[0].length + 1)))) {
																		double reduceVar$denom$5 = 0.0;
																		for(int cv$reduction892Index = 0; cv$reduction892Index < j$var131; cv$reduction892Index += 1) {
																			double k$var149 = reduceVar$denom$5;
																			double l$var150 = weekly_ut[((t - 0) / 1)][cv$reduction892Index];
																			reduceVar$denom$5 = (k$var149 + l$var150);
																		}
																		for(int cv$reduction892Index = (j$var131 + 1); cv$reduction892Index < (avail[0].length + 1); cv$reduction892Index += 1) {
																			double k$var149 = reduceVar$denom$5;
																			double l$var150 = weekly_ut[((t - 0) / 1)][cv$reduction892Index];
																			reduceVar$denom$5 = (k$var149 + l$var150);
																		}
																		double cv$reduced159 = reduceVar$denom$5;
																		reduceVar$denom$5 = (traceTempVariable$k$24_8 + cv$reduced159);
																		double traceTempVariable$denom$24_9 = reduceVar$denom$5;
																		if(!guard$sample45multinomial180[((t - 0) / 1)]) {
																			guard$sample45multinomial180[((t - 0) / 1)] = true;
																			{
																				if((0 < numTimeSteps)) {
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							{
																								{
																									if((0 < numTimeSteps)) {
																										double[] cv$temp$6$weekly_rates;
																										{
																											cv$temp$6$weekly_rates = weekly_rates[((t - 0) / 1)];
																										}
																										int cv$temp$7$arrivals;
																										{
																											cv$temp$7$arrivals = arrivals[((t - 0) / 1)];
																										}
																										if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$6$weekly_rates, cv$temp$7$arrivals)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$6$weekly_rates, cv$temp$7$arrivals)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$6$weekly_rates, cv$temp$7$arrivals));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$6$weekly_rates, cv$temp$7$arrivals)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$6$weekly_rates, cv$temp$7$arrivals)));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																									}
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
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							double traceTempVariable$var51$25_1 = cv$currentValue;
							for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
								if((var38 == j$var50)) {
									double traceTempVariable$var76$25_3 = Math.exp(traceTempVariable$var51$25_1);
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										if((j$var50 == j$var75)) {
											double reduceVar$sum$5 = 0.0;
											for(int cv$reduction67Index = 0; cv$reduction67Index < avail[0].length; cv$reduction67Index += 1) {
												double k$var63 = reduceVar$sum$5;
												double l$var64 = exped[cv$reduction67Index];
												reduceVar$sum$5 = (k$var63 + l$var64);
											}
											double traceTempVariable$var137$25_5 = (traceTempVariable$var76$25_3 / (r * reduceVar$sum$5));
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
													if(avail[t][j$var131]) {
														if((j$var75 == j$var131)) {
															double traceTempVariable$var164$25_8 = traceTempVariable$var137$25_5;
															for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
																if((j$var131 == j$var163)) {
																	if(!guard$sample45multinomial180[((t - 0) / 1)]) {
																		guard$sample45multinomial180[((t - 0) / 1)] = true;
																		{
																			if((0 < numTimeSteps)) {
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						{
																							{
																								if((0 < numTimeSteps)) {
																									double[] cv$temp$8$weekly_rates;
																									{
																										cv$temp$8$weekly_rates = weekly_rates[((t - 0) / 1)];
																									}
																									int cv$temp$9$arrivals;
																									{
																										cv$temp$9$arrivals = arrivals[((t - 0) / 1)];
																									}
																									if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$8$weekly_rates, cv$temp$9$arrivals)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$8$weekly_rates, cv$temp$9$arrivals)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$8$weekly_rates, cv$temp$9$arrivals));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$8$weekly_rates, cv$temp$9$arrivals)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$8$weekly_rates, cv$temp$9$arrivals)));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																								}
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
			double var39 = cv$originalValue;
			ut[var38] = var39;
			{
				if((0 < numTimeSteps)) {
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						if((var38 == j$var50)) {
							{
								exped[j$var50] = Math.exp(ut[j$var50]);
							}
						}
					}
				}
			}
			{
				boolean[] guard$sample45put86 = guard$sample45put86$global;
				if((0 < numTimeSteps)) {
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						if((var38 == j$var50)) {
							if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
								{
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1)
										guard$sample45put86[((j$var75 - 0) / 1)] = false;
								}
							}
						}
					}
				}
				if((0 < numTimeSteps)) {
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						if((var38 == j$var50)) {
							for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
								if((j$var50 == j$var75))
									guard$sample45put86[((j$var75 - 0) / 1)] = false;
							}
						}
					}
				}
				if((0 < numTimeSteps)) {
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						if((var38 == j$var50)) {
							if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
								{
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										if(!guard$sample45put86[((j$var75 - 0) / 1)]) {
											guard$sample45put86[((j$var75 - 0) / 1)] = true;
											{
												double reduceVar$sum$6 = 0.0;
												for(int cv$reduction67Index = 0; cv$reduction67Index < avail[0].length; cv$reduction67Index += 1) {
													double k$var63 = reduceVar$sum$6;
													double l$var64 = exped[cv$reduction67Index];
													reduceVar$sum$6 = (k$var63 + l$var64);
												}
												expedNorm[j$var75] = (exped[j$var75] / (r * reduceVar$sum$6));
											}
										}
									}
								}
							}
						}
					}
				}
				if((0 < numTimeSteps)) {
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						if((var38 == j$var50)) {
							for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
								if((j$var50 == j$var75)) {
									if(!guard$sample45put86[((j$var75 - 0) / 1)]) {
										guard$sample45put86[((j$var75 - 0) / 1)] = true;
										{
											double reduceVar$sum$7 = 0.0;
											for(int cv$reduction67Index = 0; cv$reduction67Index < avail[0].length; cv$reduction67Index += 1) {
												double k$var63 = reduceVar$sum$7;
												double l$var64 = exped[cv$reduction67Index];
												reduceVar$sum$7 = (k$var63 + l$var64);
											}
											expedNorm[j$var75] = (exped[j$var75] / (r * reduceVar$sum$7));
										}
									}
								}
							}
						}
					}
				}
			}
			{
				boolean[][] guard$sample45put150 = guard$sample45put150$global;
				if((0 < numTimeSteps)) {
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						if((var38 == j$var50)) {
							if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
								{
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										for(int t = 0; t < numTimeSteps; t += 1) {
											for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
												if(avail[t][j$var131]) {
													if((j$var75 == j$var131))
														guard$sample45put150[((t - 0) / 1)][((j$var131 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if((0 < numTimeSteps)) {
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						if((var38 == j$var50)) {
							for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
								if((j$var50 == j$var75)) {
									for(int t = 0; t < numTimeSteps; t += 1) {
										for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
											if(avail[t][j$var131]) {
												if((j$var75 == j$var131))
													guard$sample45put150[((t - 0) / 1)][((j$var131 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
					}
				}
				if((0 < numTimeSteps)) {
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						if((var38 == j$var50)) {
							if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
								{
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										for(int t = 0; t < numTimeSteps; t += 1) {
											for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
												if(avail[t][j$var131]) {
													if((j$var75 == j$var131)) {
														if(!guard$sample45put150[((t - 0) / 1)][((j$var131 - 0) / 1)]) {
															guard$sample45put150[((t - 0) / 1)][((j$var131 - 0) / 1)] = true;
															{
																weekly_ut[((t - 0) / 1)][j$var131] = expedNorm[j$var131];
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
				if((0 < numTimeSteps)) {
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						if((var38 == j$var50)) {
							for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
								if((j$var50 == j$var75)) {
									for(int t = 0; t < numTimeSteps; t += 1) {
										for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
											if(avail[t][j$var131]) {
												if((j$var75 == j$var131)) {
													if(!guard$sample45put150[((t - 0) / 1)][((j$var131 - 0) / 1)]) {
														guard$sample45put150[((t - 0) / 1)][((j$var131 - 0) / 1)] = true;
														{
															weekly_ut[((t - 0) / 1)][j$var131] = expedNorm[j$var131];
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
				boolean[][] guard$sample45put179 = guard$sample45put179$global;
				if((0 < numTimeSteps)) {
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						if((var38 == j$var50)) {
							if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
								{
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										for(int t = 0; t < numTimeSteps; t += 1) {
											for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
												if(avail[t][j$var131]) {
													if((j$var75 == j$var131)) {
														if(((0 <= j$var131) && (j$var131 < (avail[0].length + 1)))) {
															{
																for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1)
																	guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)] = false;
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
				if((0 < numTimeSteps)) {
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						if((var38 == j$var50)) {
							if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
								{
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										for(int t = 0; t < numTimeSteps; t += 1) {
											for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
												if(avail[t][j$var131]) {
													if((j$var75 == j$var131)) {
														for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
															if((j$var131 == j$var163))
																guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)] = false;
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
				if((0 < numTimeSteps)) {
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						if((var38 == j$var50)) {
							for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
								if((j$var50 == j$var75)) {
									for(int t = 0; t < numTimeSteps; t += 1) {
										for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
											if(avail[t][j$var131]) {
												if((j$var75 == j$var131)) {
													if(((0 <= j$var131) && (j$var131 < (avail[0].length + 1)))) {
														{
															for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1)
																guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)] = false;
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
				if((0 < numTimeSteps)) {
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						if((var38 == j$var50)) {
							for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
								if((j$var50 == j$var75)) {
									for(int t = 0; t < numTimeSteps; t += 1) {
										for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
											if(avail[t][j$var131]) {
												if((j$var75 == j$var131)) {
													for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
														if((j$var131 == j$var163))
															guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)] = false;
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
				if((0 < numTimeSteps)) {
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						if((var38 == j$var50)) {
							if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
								{
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										for(int t = 0; t < numTimeSteps; t += 1) {
											for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
												if(avail[t][j$var131]) {
													if((j$var75 == j$var131)) {
														if(((0 <= j$var131) && (j$var131 < (avail[0].length + 1)))) {
															{
																for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
																	if(!guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)]) {
																		guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)] = true;
																		{
																			double reduceVar$denom$6 = 0.0;
																			for(int cv$reduction159Index = 0; cv$reduction159Index < (avail[0].length + 1); cv$reduction159Index += 1) {
																				double k$var149 = reduceVar$denom$6;
																				double l$var150 = weekly_ut[((t - 0) / 1)][cv$reduction159Index];
																				reduceVar$denom$6 = (k$var149 + l$var150);
																			}
																			weekly_rates[((t - 0) / 1)][j$var163] = (weekly_ut[((t - 0) / 1)][j$var163] / reduceVar$denom$6);
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
						}
					}
				}
				if((0 < numTimeSteps)) {
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						if((var38 == j$var50)) {
							if(((0 <= j$var50) && (j$var50 < avail[0].length))) {
								{
									for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
										for(int t = 0; t < numTimeSteps; t += 1) {
											for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
												if(avail[t][j$var131]) {
													if((j$var75 == j$var131)) {
														for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
															if((j$var131 == j$var163)) {
																if(!guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)]) {
																	guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)] = true;
																	{
																		double reduceVar$denom$7 = 0.0;
																		for(int cv$reduction159Index = 0; cv$reduction159Index < (avail[0].length + 1); cv$reduction159Index += 1) {
																			double k$var149 = reduceVar$denom$7;
																			double l$var150 = weekly_ut[((t - 0) / 1)][cv$reduction159Index];
																			reduceVar$denom$7 = (k$var149 + l$var150);
																		}
																		weekly_rates[((t - 0) / 1)][j$var163] = (weekly_ut[((t - 0) / 1)][j$var163] / reduceVar$denom$7);
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
					}
				}
				if((0 < numTimeSteps)) {
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						if((var38 == j$var50)) {
							for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
								if((j$var50 == j$var75)) {
									for(int t = 0; t < numTimeSteps; t += 1) {
										for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
											if(avail[t][j$var131]) {
												if((j$var75 == j$var131)) {
													if(((0 <= j$var131) && (j$var131 < (avail[0].length + 1)))) {
														{
															for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
																if(!guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)]) {
																	guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)] = true;
																	{
																		double reduceVar$denom$8 = 0.0;
																		for(int cv$reduction159Index = 0; cv$reduction159Index < (avail[0].length + 1); cv$reduction159Index += 1) {
																			double k$var149 = reduceVar$denom$8;
																			double l$var150 = weekly_ut[((t - 0) / 1)][cv$reduction159Index];
																			reduceVar$denom$8 = (k$var149 + l$var150);
																		}
																		weekly_rates[((t - 0) / 1)][j$var163] = (weekly_ut[((t - 0) / 1)][j$var163] / reduceVar$denom$8);
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
					}
				}
				if((0 < numTimeSteps)) {
					for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
						if((var38 == j$var50)) {
							for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
								if((j$var50 == j$var75)) {
									for(int t = 0; t < numTimeSteps; t += 1) {
										for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
											if(avail[t][j$var131]) {
												if((j$var75 == j$var131)) {
													for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
														if((j$var131 == j$var163)) {
															if(!guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)]) {
																guard$sample45put179[((t - 0) / 1)][((j$var163 - 0) / 1)] = true;
																{
																	double reduceVar$denom$9 = 0.0;
																	for(int cv$reduction159Index = 0; cv$reduction159Index < (avail[0].length + 1); cv$reduction159Index += 1) {
																		double k$var149 = reduceVar$denom$9;
																		double l$var150 = weekly_ut[((t - 0) / 1)][cv$reduction159Index];
																		reduceVar$denom$9 = (k$var149 + l$var150);
																	}
																	weekly_rates[((t - 0) / 1)][j$var163] = (weekly_ut[((t - 0) / 1)][j$var163] / reduceVar$denom$9);
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
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max_j$var75 = 0;
			if((0 < avail.length))
				cv$max_j$var75 = Math.max(cv$max_j$var75, ((avail[0].length - 0) / 1));
			guard$sample45put86$global = new boolean[cv$max_j$var75];
		}
		{
			int cv$max_t = 0;
			int cv$max_j$var131 = 0;
			if((0 < avail.length)) {
				for(int t = 0; t < avail.length; t += 1)
					cv$max_j$var131 = Math.max(cv$max_j$var131, ((avail[0].length - 0) / 1));
				cv$max_t = Math.max(cv$max_t, ((avail.length - 0) / 1));
			}
			guard$sample45put150$global = new boolean[cv$max_t][cv$max_j$var131];
		}
		{
			int cv$max_t = 0;
			int cv$max_j$var163 = 0;
			if((0 < avail.length)) {
				for(int t = 0; t < avail.length; t += 1)
					cv$max_j$var163 = Math.max(cv$max_j$var163, (((avail[0].length + 1) - 0) / 1));
				cv$max_t = Math.max(cv$max_t, ((avail.length - 0) / 1));
			}
			guard$sample45put179$global = new boolean[cv$max_t][cv$max_j$var163];
		}
		{
			int cv$max_t = 0;
			if((0 < avail.length))
				cv$max_t = Math.max(cv$max_t, ((avail.length - 0) / 1));
			guard$sample45multinomial180$global = new boolean[cv$max_t];
		}
	}

	@Override
	public final void allocator() {
		{
			if((0 < numTimeSteps))
				ut = new double[avail[0].length];
		}
		{
			if((0 < numTimeSteps))
				exped = new double[avail[0].length];
		}
		{
			if((0 < numTimeSteps))
				expedNorm = new double[avail[0].length];
		}
		{
			if((0 < numTimeSteps)) {
				sales = new int[avail.length][];
				for(int var89 = 0; var89 < avail.length; var89 += 1)
					sales[var89] = new int[avail[0].length];
			}
		}
		if(!setFlag$lambda) {
			{
				lambda = new double[((((avail.length - 1) - 0) / 1) + 1)];
			}
		}
		if(!setFlag$arrivals) {
			{
				arrivals = new int[((((avail.length - 1) - 0) / 1) + 1)];
			}
		}
		{
			if((0 < avail.length)) {
				for(int t = 0; t < avail.length; t += 1)
					weekly_rates[((t - 0) / 1)] = new double[(avail[0].length + 1)];
			}
			weekly_rates = new double[((((avail.length - 1) - 0) / 1) + 1)][];
		}
		{
			if((0 < avail.length)) {
				for(int t = 0; t < avail.length; t += 1)
					weekly_ut[((t - 0) / 1)] = new double[(avail[0].length + 1)];
			}
			weekly_ut = new double[((((avail.length - 1) - 0) / 1) + 1)][];
		}
		if(!setFlag$weekly_sales) {
			{
				if((0 < avail.length)) {
					for(int t = 0; t < avail.length; t += 1)
						weekly_sales[((t - 0) / 1)] = new int[(avail[0].length + 1)];
				}
				weekly_sales = new int[((((avail.length - 1) - 0) / 1) + 1)][];
			}
		}
		{
			logProbability$sample45 = new double[((((avail[0].length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var116 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample127 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var118 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample129 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var167 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample181 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if((0 < numTimeSteps)) {
			for(int var38 = 0; var38 < avail[0].length; var38 += 1) {
				if(!fixedFlag$sample45)
					ut[var38] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				if(!fixedFlag$sample45)
					exped[j$var50] = Math.exp(ut[j$var50]);
			}
			double reduceVar$sum$8 = 0.0;
			for(int cv$reduction67Index = 0; cv$reduction67Index < avail[0].length; cv$reduction67Index += 1) {
				double k$var63 = reduceVar$sum$8;
				double l$var64 = exped[cv$reduction67Index];
				if(!fixedFlag$sample45)
					reduceVar$sum$8 = (k$var63 + l$var64);
			}
			for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
				if(!fixedFlag$sample45)
					expedNorm[j$var75] = (exped[j$var75] / (r * reduceVar$sum$8));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(!fixedFlag$sample127)
					lambda[((t - 0) / 1)] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
				int reduceVar$numSales$5 = 0;
				for(int cv$reduction115Index = 0; cv$reduction115Index < ObsSales[t].length; cv$reduction115Index += 1) {
					int k$var108 = reduceVar$numSales$5;
					int l$var109 = ObsSales[t][cv$reduction115Index];
					if(!fixedFlag$sample129)
						reduceVar$numSales$5 = (k$var108 + l$var109);
				}
				if(!fixedFlag$sample129)
					arrivals[((t - 0) / 1)] = (reduceVar$numSales$5 + DistributionSampling.samplePoisson(RNG$, lambda[((t - 0) / 1)]));
				for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
					if(avail[t][j$var131]) {
						if(!fixedFlag$sample45)
							weekly_ut[((t - 0) / 1)][j$var131] = expedNorm[j$var131];
					} else
						weekly_ut[((t - 0) / 1)][j$var131] = 0.0;
				}
				weekly_ut[((t - 0) / 1)][avail[0].length] = 1.0;
				double reduceVar$denom$10 = 0.0;
				for(int cv$reduction159Index = 0; cv$reduction159Index < (avail[0].length + 1); cv$reduction159Index += 1) {
					double k$var149 = reduceVar$denom$10;
					double l$var150 = weekly_ut[((t - 0) / 1)][cv$reduction159Index];
					if(!fixedFlag$sample45)
						reduceVar$denom$10 = (k$var149 + l$var150);
				}
				for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
					if(!fixedFlag$sample45)
						weekly_rates[((t - 0) / 1)][j$var163] = (weekly_ut[((t - 0) / 1)][j$var163] / reduceVar$denom$10);
				}
				if(!fixedFlag$sample181)
					DistributionSampling.sampleMultinomial(RNG$, weekly_rates[((t - 0) / 1)], arrivals[((t - 0) / 1)], weekly_sales[((t - 0) / 1)]);
				int[] observed_weekly_sales = sales[t];
				for(int j$var178 = 0; j$var178 < avail[0].length; j$var178 += 1) {
					if(!fixedFlag$sample181)
						observed_weekly_sales[j$var178] = weekly_sales[((t - 0) / 1)][j$var178];
				}
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if((0 < numTimeSteps)) {
			for(int var38 = 0; var38 < avail[0].length; var38 += 1) {
				if(!fixedFlag$sample45)
					ut[var38] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				if(!fixedFlag$sample45)
					exped[j$var50] = Math.exp(ut[j$var50]);
			}
			double reduceVar$sum$10 = 0.0;
			for(int cv$reduction67Index = 0; cv$reduction67Index < avail[0].length; cv$reduction67Index += 1) {
				double k$var63 = reduceVar$sum$10;
				double l$var64 = exped[cv$reduction67Index];
				if(!fixedFlag$sample45)
					reduceVar$sum$10 = (k$var63 + l$var64);
			}
			for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
				if(!fixedFlag$sample45)
					expedNorm[j$var75] = (exped[j$var75] / (r * reduceVar$sum$10));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(!fixedFlag$sample127)
					lambda[((t - 0) / 1)] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
				int reduceVar$numSales$7 = 0;
				for(int cv$reduction115Index = 0; cv$reduction115Index < ObsSales[t].length; cv$reduction115Index += 1) {
					int k$var108 = reduceVar$numSales$7;
					int l$var109 = ObsSales[t][cv$reduction115Index];
					if(!fixedFlag$sample129)
						reduceVar$numSales$7 = (k$var108 + l$var109);
				}
				if(!fixedFlag$sample129)
					arrivals[((t - 0) / 1)] = (reduceVar$numSales$7 + DistributionSampling.samplePoisson(RNG$, lambda[((t - 0) / 1)]));
				for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
					if(avail[t][j$var131]) {
						if(!fixedFlag$sample45)
							weekly_ut[((t - 0) / 1)][j$var131] = expedNorm[j$var131];
					} else
						weekly_ut[((t - 0) / 1)][j$var131] = 0.0;
				}
				weekly_ut[((t - 0) / 1)][avail[0].length] = 1.0;
				double reduceVar$denom$12 = 0.0;
				for(int cv$reduction159Index = 0; cv$reduction159Index < (avail[0].length + 1); cv$reduction159Index += 1) {
					double k$var149 = reduceVar$denom$12;
					double l$var150 = weekly_ut[((t - 0) / 1)][cv$reduction159Index];
					if(!fixedFlag$sample45)
						reduceVar$denom$12 = (k$var149 + l$var150);
				}
				for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
					if(!fixedFlag$sample45)
						weekly_rates[((t - 0) / 1)][j$var163] = (weekly_ut[((t - 0) / 1)][j$var163] / reduceVar$denom$12);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if((0 < numTimeSteps)) {
			for(int var38 = 0; var38 < avail[0].length; var38 += 1) {
				if(!fixedFlag$sample45)
					ut[var38] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				if(!fixedFlag$sample45)
					exped[j$var50] = Math.exp(ut[j$var50]);
			}
			double reduceVar$sum$9 = 0.0;
			for(int cv$reduction67Index = 0; cv$reduction67Index < avail[0].length; cv$reduction67Index += 1) {
				double k$var63 = reduceVar$sum$9;
				double l$var64 = exped[cv$reduction67Index];
				if(!fixedFlag$sample45)
					reduceVar$sum$9 = (k$var63 + l$var64);
			}
			for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
				if(!fixedFlag$sample45)
					expedNorm[j$var75] = (exped[j$var75] / (r * reduceVar$sum$9));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(!fixedFlag$sample127)
					lambda[((t - 0) / 1)] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
				int reduceVar$numSales$6 = 0;
				for(int cv$reduction115Index = 0; cv$reduction115Index < ObsSales[t].length; cv$reduction115Index += 1) {
					int k$var108 = reduceVar$numSales$6;
					int l$var109 = ObsSales[t][cv$reduction115Index];
					if(!fixedFlag$sample129)
						reduceVar$numSales$6 = (k$var108 + l$var109);
				}
				if(!fixedFlag$sample129)
					arrivals[((t - 0) / 1)] = (reduceVar$numSales$6 + DistributionSampling.samplePoisson(RNG$, lambda[((t - 0) / 1)]));
				for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
					if(avail[t][j$var131]) {
						if(!fixedFlag$sample45)
							weekly_ut[((t - 0) / 1)][j$var131] = expedNorm[j$var131];
					} else
						weekly_ut[((t - 0) / 1)][j$var131] = 0.0;
				}
				weekly_ut[((t - 0) / 1)][avail[0].length] = 1.0;
				double reduceVar$denom$11 = 0.0;
				for(int cv$reduction159Index = 0; cv$reduction159Index < (avail[0].length + 1); cv$reduction159Index += 1) {
					double k$var149 = reduceVar$denom$11;
					double l$var150 = weekly_ut[((t - 0) / 1)][cv$reduction159Index];
					if(!fixedFlag$sample45)
						reduceVar$denom$11 = (k$var149 + l$var150);
				}
				for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
					if(!fixedFlag$sample45)
						weekly_rates[((t - 0) / 1)][j$var163] = (weekly_ut[((t - 0) / 1)][j$var163] / reduceVar$denom$11);
				}
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if((0 < numTimeSteps)) {
				for(int var38 = 0; var38 < avail[0].length; var38 += 1) {
					if(!fixedFlag$sample45)
						sample45(var38);
				}
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(!fixedFlag$sample127)
						sample127(t);
					if(!fixedFlag$sample129)
						sample129(t);
				}
			}
		} else {
			if((0 < numTimeSteps)) {
				for(int t = (numTimeSteps - ((((numTimeSteps - 1) - 0) % 1) + 1)); t >= ((0 - 1) + 1); t -= 1) {
					if(!fixedFlag$sample129)
						sample129(t);
					if(!fixedFlag$sample127)
						sample127(t);
				}
				for(int var38 = (avail[0].length - ((((avail[0].length - 1) - 0) % 1) + 1)); var38 >= ((0 - 1) + 1); var38 -= 1) {
					if(!fixedFlag$sample45)
						sample45(var38);
				}
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		r = 0.3;
		numTimeSteps = avail.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var28 = 0.0;
		logProbability$exped = 0.0;
		logProbability$weekly_rates = 0.0;
		logProbability$ut = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$expedNorm = 0.0;
		if(!fixedProbFlag$sample45) {
			if((0 < numTimeSteps)) {
				for(int var38 = 0; var38 < avail[0].length; var38 += 1)
					logProbability$sample45[((var38 - 0) / 1)] = 0.0;
			}
		}
		if((0 < numTimeSteps)) {
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var116[((t - 0) / 1)] = 0.0;
		}
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample127) {
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample127[((t - 0) / 1)] = 0.0;
			}
		}
		if((0 < numTimeSteps)) {
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var118[((t - 0) / 1)] = 0.0;
		}
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample129) {
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample129[((t - 0) / 1)] = 0.0;
			}
		}
		if((0 < numTimeSteps)) {
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var167[((t - 0) / 1)] = 0.0;
		}
		logProbability$sales = 0.0;
		logProbability$weekly_sales = 0.0;
		if(!fixedProbFlag$sample181) {
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample181[((t - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		if(fixedFlag$sample127)
			logProbabilityValue$sample127();
		if(fixedFlag$sample129)
			logProbabilityValue$sample129();
		logProbabilityValue$sample181();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample45();
		logProbabilityValue$sample127();
		logProbabilityValue$sample129();
		logProbabilityValue$sample181();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample45();
		logProbabilityValue$sample127();
		logProbabilityValue$sample129();
		logProbabilityValue$sample181();
	}

	@Override
	public final void logProbabilityGeneration() {
		if((0 < numTimeSteps)) {
			for(int var38 = 0; var38 < avail[0].length; var38 += 1) {
				if(!fixedFlag$sample45)
					ut[var38] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				if(!fixedFlag$sample45)
					exped[j$var50] = Math.exp(ut[j$var50]);
			}
			double reduceVar$sum$11 = 0.0;
			for(int cv$reduction67Index = 0; cv$reduction67Index < avail[0].length; cv$reduction67Index += 1) {
				double k$var63 = reduceVar$sum$11;
				double l$var64 = exped[cv$reduction67Index];
				if(!fixedFlag$sample45)
					reduceVar$sum$11 = (k$var63 + l$var64);
			}
			for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
				if(!fixedFlag$sample45)
					expedNorm[j$var75] = (exped[j$var75] / (r * reduceVar$sum$11));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(!fixedFlag$sample127)
					lambda[((t - 0) / 1)] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
				int reduceVar$numSales$8 = 0;
				for(int cv$reduction115Index = 0; cv$reduction115Index < ObsSales[t].length; cv$reduction115Index += 1) {
					int k$var108 = reduceVar$numSales$8;
					int l$var109 = ObsSales[t][cv$reduction115Index];
					if(!fixedFlag$sample129)
						reduceVar$numSales$8 = (k$var108 + l$var109);
				}
				if(!fixedFlag$sample129)
					arrivals[((t - 0) / 1)] = (reduceVar$numSales$8 + DistributionSampling.samplePoisson(RNG$, lambda[((t - 0) / 1)]));
				for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
					if(avail[t][j$var131]) {
						if(!fixedFlag$sample45)
							weekly_ut[((t - 0) / 1)][j$var131] = expedNorm[j$var131];
					} else
						weekly_ut[((t - 0) / 1)][j$var131] = 0.0;
				}
				weekly_ut[((t - 0) / 1)][avail[0].length] = 1.0;
				double reduceVar$denom$13 = 0.0;
				for(int cv$reduction159Index = 0; cv$reduction159Index < (avail[0].length + 1); cv$reduction159Index += 1) {
					double k$var149 = reduceVar$denom$13;
					double l$var150 = weekly_ut[((t - 0) / 1)][cv$reduction159Index];
					if(!fixedFlag$sample45)
						reduceVar$denom$13 = (k$var149 + l$var150);
				}
				for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
					if(!fixedFlag$sample45)
						weekly_rates[((t - 0) / 1)][j$var163] = (weekly_ut[((t - 0) / 1)][j$var163] / reduceVar$denom$13);
				}
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		if((0 < numTimeSteps)) {
			{
				int[][] cv$source1 = ObsSales;
				int[][] cv$target1 = sales;
				int cv$length1 = cv$target1.length;
				for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
					int[] cv$source2 = cv$source1[cv$index1];
					int[] cv$target2 = cv$target1[cv$index1];
					int cv$length2 = cv$target2.length;
					for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
						cv$target2[cv$index2] = cv$source2[cv$index2];
				}
			}
			for(int t = (numTimeSteps - ((((numTimeSteps - 1) - 0) % 1) + 1)); t >= ((0 - 1) + 1); t -= 1) {
				if((0 < numTimeSteps)) {
					for(int j$var178 = (avail[0].length - ((((avail[0].length - 1) - 0) % 1) + 1)); j$var178 >= ((0 - 1) + 1); j$var178 -= 1) {
						if((0 < numTimeSteps)) {
							int[] observed_weekly_sales;
							observed_weekly_sales = sales[t];
							weekly_sales[((t - 0) / 1)][j$var178] = observed_weekly_sales[j$var178];
						}
					}
				}
			}
		}
	}

	@Override
	public final void setIntermediates() {
		if((0 < numTimeSteps)) {
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				if(true)
					exped[j$var50] = Math.exp(ut[j$var50]);
			}
			double reduceVar$sum$12 = 0.0;
			for(int cv$reduction67Index = 0; cv$reduction67Index < avail[0].length; cv$reduction67Index += 1) {
				double k$var63 = reduceVar$sum$12;
				double l$var64 = exped[cv$reduction67Index];
				reduceVar$sum$12 = (k$var63 + l$var64);
			}
			for(int j$var75 = 0; j$var75 < avail[0].length; j$var75 += 1) {
				if(true)
					expedNorm[j$var75] = (exped[j$var75] / (r * reduceVar$sum$12));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				for(int j$var131 = 0; j$var131 < avail[0].length; j$var131 += 1) {
					if(avail[t][j$var131]) {
						if(true)
							weekly_ut[((t - 0) / 1)][j$var131] = expedNorm[j$var131];
					}
				}
				double reduceVar$denom$14 = 0.0;
				for(int cv$reduction159Index = 0; cv$reduction159Index < (avail[0].length + 1); cv$reduction159Index += 1) {
					double k$var149 = reduceVar$denom$14;
					double l$var150 = weekly_ut[((t - 0) / 1)][cv$reduction159Index];
					reduceVar$denom$14 = (k$var149 + l$var150);
				}
				for(int j$var163 = 0; j$var163 < (avail[0].length + 1); j$var163 += 1) {
					if(true)
						weekly_rates[((t - 0) / 1)][j$var163] = (weekly_ut[((t - 0) / 1)][j$var163] / reduceVar$denom$14);
				}
				if(setFlag$weekly_sales) {
					int[] observed_weekly_sales = sales[t];
					for(int j$var178 = 0; j$var178 < avail[0].length; j$var178 += 1)
						observed_weekly_sales[j$var178] = weekly_sales[((t - 0) / 1)][j$var178];
				}
			}
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n/*\n * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n */\npackage org.sandwood.compiler.tests.parser;\n\nmodel Vulcano2012basicDG(int[][] ObsSales, boolean[][] avail) {\n   // avail is the availability matrix, numTimeSteps-by-numProducts\n   // r is the normalization constant here, number between 0 and 1. \"Relative appeal of the outside option\"\n   double r = 0.3;\n    \n   int numTimeSteps = avail.length;\n   if(numTimeSteps > 0) {\n      int numProducts = avail[0].length;\n\n      // draw utilities\n      double[] ut = gaussian(0, 1).sample(numProducts);\n\n      //exponentiate right here (in the non-basic models move to the for loop)\n      double[] exped = new double[numProducts];\n      for(int j : [0..numProducts))\n         exped[j] = exp(ut[j]);\n\n      //Choices includes the choice to not buy anything.\n      int numChoices = numProducts + 1;\n\n      //now normalize\n      double[] expedNorm = new double[numProducts];\n      double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n      for(int j : [0..numProducts))\n         expedNorm[j] = exped[j]/(r*sum);\n\n      int[][] sales = new int[numTimeSteps][numProducts];\n\n      for (int t:[0..numTimeSteps)){\n         // Calculate the number of purchases made.\n         int numSales = reduce(ObsSales[t], 0, (k, l) -> { return k + l; });\n\n         // prior for the distribution of lambda for arrivals. These can be \n         // supplied as a vector if RGBU has some estimates, or just use some priors.\n         public double lambda = gamma(10,10).sample();\n         public int arrivals = numSales + poisson(lambda).sample();\n\n         // for each period t calculate choice probabilities and sales\n         double[] weekly_rates = new double[numChoices];\n         double[] weekly_ut = new double[numChoices];\n\n         for(int j : [0..numProducts)) {\n            if(avail[t][j])\n               weekly_ut[j] = expedNorm[j];\n            else\n               weekly_ut[j] = 0.0;\n         }\n         // Moved v_0 to the end of the array to keep indexing consistent everywhere else in \n         // the model and delayed the assignment of the value 1 to here. None of this is a \n         // sandwood requirement, I just thought it made the model eaiser to follow.\n         weekly_ut[numProducts] = 1.0;\n\n         double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n\n         for(int j : [0..numProducts]) \n            weekly_rates[j] = weekly_ut[j]/denom ;\n\n         int[] weekly_sales = multinomial(weekly_rates, arrivals).sample();\n\n         //getting rid of the no purchase observation (last one in the vector of weekly_sales)\n         int[] observed_weekly_sales = sales[t];\n         for (int j : [0..numProducts))\n            observed_weekly_sales[j] = weekly_sales[j] ;\n      }\n      // assert that generated sales match observed sales\n      sales.observe(ObsSales);\n   }\n}";
	}
}