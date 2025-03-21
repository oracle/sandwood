package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Vulcano2012basicDG$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Vulcano2012basicDG$CoreInterface {
	private int[][] ObsSales;
	private int[] arrivals;
	private boolean[][] avail;
	private double[] exped;
	private double[] expedNorm;
	private boolean fixedFlag$sample112 = false;
	private boolean fixedFlag$sample114 = false;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedProbFlag$sample112 = false;
	private boolean fixedProbFlag$sample114 = false;
	private boolean fixedProbFlag$sample166 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean[] guard$sample32multinomial165$global;
	private boolean[][] guard$sample32put135$global;
	private boolean[][] guard$sample32put164$global;
	private boolean[] guard$sample32put73$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$arrivals;
	private double logProbability$exped;
	private double logProbability$expedNorm;
	private double logProbability$lambda;
	private double logProbability$sales;
	private double[] logProbability$sample112;
	private double[] logProbability$sample114;
	private double[] logProbability$sample166;
	private double[] logProbability$sample32;
	private double logProbability$ut;
	private double[] logProbability$var106;
	private double[] logProbability$var108;
	private double[] logProbability$var157;
	private double logProbability$var19;
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

	public Vulcano2012basicDG$MultiThreadCPU(ExecutionTarget target) {
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
	public final boolean get$fixedFlag$sample112() {
		return fixedFlag$sample112;
	}

	@Override
	public final void set$fixedFlag$sample112(boolean cv$value) {
		fixedFlag$sample112 = cv$value;
		fixedProbFlag$sample112 = (fixedFlag$sample112 && fixedProbFlag$sample112);
		fixedProbFlag$sample114 = (fixedFlag$sample112 && fixedProbFlag$sample114);
	}

	@Override
	public final boolean get$fixedFlag$sample114() {
		return fixedFlag$sample114;
	}

	@Override
	public final void set$fixedFlag$sample114(boolean cv$value) {
		fixedFlag$sample114 = cv$value;
		fixedProbFlag$sample114 = (fixedFlag$sample114 && fixedProbFlag$sample114);
		fixedProbFlag$sample166 = (fixedFlag$sample114 && fixedProbFlag$sample166);
	}

	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	@Override
	public final void set$fixedFlag$sample32(boolean cv$value) {
		fixedFlag$sample32 = cv$value;
		fixedProbFlag$sample32 = (fixedFlag$sample32 && fixedProbFlag$sample32);
		fixedProbFlag$sample166 = (fixedFlag$sample32 && fixedProbFlag$sample166);
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

	private final void logProbabilityValue$sample112() {
		if(!fixedProbFlag$sample112) {
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
									double var104 = 10.0;
									double var105 = 10.0;
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGamma(cv$sampleValue, var104, var105));
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
					logProbability$var106[((t - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample112[((t - 0) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample112)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample112 = fixedFlag$sample112;
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample112[((t - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var106[((t - 0) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample112)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample114() {
		if(!fixedProbFlag$sample114) {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						if((0 < numTimeSteps)) {
							int reduceVar$numSales$13 = 0;
							for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1) {
								int k$var98 = reduceVar$numSales$13;
								int l$var99 = ObsSales[t][cv$reduction100Index];
								reduceVar$numSales$13 = (k$var98 + l$var99);
							}
							int cv$sampleValue = (arrivals[((t - 0) / 1)] - reduceVar$numSales$13);
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
					logProbability$var108[((t - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample114[((t - 0) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample114)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample114 = (fixedFlag$sample114 && fixedFlag$sample112);
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample114[((t - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var108[((t - 0) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample114)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample166() {
		if(!fixedProbFlag$sample166) {
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
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(cv$sampleValue, weekly_rates[((t - 0) / 1)], (avail[0].length + 1), arrivals[((t - 0) / 1)]));
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
					logProbability$var157[((t - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample166[((t - 0) / 1)] = cv$sampleProbability;
					boolean cv$guard$sales = false;
					{
						if((0 < numTimeSteps)) {
							for(int j$var168 = 0; j$var168 < avail[0].length; j$var168 += 1) {
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
			fixedProbFlag$sample166 = (fixedFlag$sample32 && fixedFlag$sample114);
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample166[((t - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var157[((t - 0) / 1)] = cv$rvAccumulator;
					boolean cv$guard$sales = false;
					{
						if((0 < numTimeSteps)) {
							for(int j$var168 = 0; j$var168 < avail[0].length; j$var168 += 1) {
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

	private final void logProbabilityValue$sample32() {
		if(!fixedProbFlag$sample32) {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				double cv$sampleAccumulator = 0.0;
				for(int var29 = 0; var29 < avail[0].length; var29 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						if((0 < numTimeSteps)) {
							double cv$sampleValue = ut[var29];
							{
								{
									double var17 = 0.0;
									double var18 = 1.0;
									double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var17) / Math.sqrt(var18))) - (0.5 * Math.log(var18))));
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
					logProbability$sample32[((var29 - 0) / 1)] = cv$sampleProbability;
					boolean cv$guard$exped = false;
					boolean cv$guard$expedNorm = false;
					boolean cv$guard$weekly_ut = false;
					boolean cv$guard$weekly_rates = false;
					{
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																	{
																		for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																	if((j$var121 == j$var153)) {
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																{
																	for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																if((j$var121 == j$var153)) {
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
				logProbability$var19 = cv$sampleAccumulator;
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample32 = fixedFlag$sample32;
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				double cv$rvAccumulator = 0.0;
				for(int var29 = 0; var29 < avail[0].length; var29 += 1) {
					double cv$sampleValue = logProbability$sample32[((var29 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					boolean cv$guard$exped = false;
					boolean cv$guard$expedNorm = false;
					boolean cv$guard$weekly_ut = false;
					boolean cv$guard$weekly_rates = false;
					{
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																	{
																		for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																	if((j$var121 == j$var153)) {
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																{
																	for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																if((j$var121 == j$var153)) {
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
				logProbability$var19 = cv$rvAccumulator;
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample112(int t, int threadID$cv$t, Rng RNG$) {
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
													int reduceVar$numSales$9 = 0;
													for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1) {
														int k$var98 = reduceVar$numSales$9;
														int l$var99 = ObsSales[t][cv$reduction100Index];
														reduceVar$numSales$9 = (k$var98 + l$var99);
													}
													cv$sum = (cv$sum + (arrivals[((t - 0) / 1)] - reduceVar$numSales$9));
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

	private final void sample114(int t, int threadID$cv$t, Rng RNG$) {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		int reduceVar$numSales$10 = 0;
		for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1) {
			int k$var98 = reduceVar$numSales$10;
			int l$var99 = ObsSales[t][cv$reduction100Index];
			reduceVar$numSales$10 = (k$var98 + l$var99);
		}
		int cv$originalValue = (arrivals[((t - 0) / 1)] - reduceVar$numSales$10);
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
					int var109 = cv$proposedValue;
					int reduceVar$numSales$11 = 0;
					for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1) {
						int k$var98 = reduceVar$numSales$11;
						int l$var99 = ObsSales[t][cv$reduction100Index];
						reduceVar$numSales$11 = (k$var98 + l$var99);
					}
					arrivals[((t - 0) / 1)] = (reduceVar$numSales$11 + cv$currentValue);
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
														int cv$temp$2$$var1692;
														{
															int $var1692 = (avail[0].length + 1);
															cv$temp$2$$var1692 = $var1692;
														}
														int cv$temp$3$arrivals;
														{
															cv$temp$3$arrivals = traceTempVariable$arrivals$1_1;
														}
														if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$$var1692, cv$temp$3$arrivals)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$$var1692, cv$temp$3$arrivals)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$$var1692, cv$temp$3$arrivals));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$$var1692, cv$temp$3$arrivals)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$$var1692, cv$temp$3$arrivals)));
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
			int var109 = cv$originalValue;
			int reduceVar$numSales$12 = 0;
			for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1) {
				int k$var98 = reduceVar$numSales$12;
				int l$var99 = ObsSales[t][cv$reduction100Index];
				reduceVar$numSales$12 = (k$var98 + l$var99);
			}
			arrivals[((t - 0) / 1)] = (reduceVar$numSales$12 + var109);
		}
	}

	private final void sample32(int var29) {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double cv$originalValue = ut[var29];
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
					double var30 = cv$proposedValue;
					ut[var29] = cv$currentValue;
					{
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									{
										exped[j$var41] = Math.exp(ut[j$var41]);
									}
								}
							}
						}
					}
					{
						boolean[] guard$sample32put73 = guard$sample32put73$global;
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1)
												guard$sample32put73[((j$var66 - 0) / 1)] = false;
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66))
											guard$sample32put73[((j$var66 - 0) / 1)] = false;
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												if(!guard$sample32put73[((j$var66 - 0) / 1)]) {
													guard$sample32put73[((j$var66 - 0) / 1)] = true;
													{
														double reduceVar$sum$13 = 0.0;
														for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
															double k$var54 = reduceVar$sum$13;
															double l$var55 = exped[cv$reduction54Index];
															reduceVar$sum$13 = (k$var54 + l$var55);
														}
														expedNorm[j$var66] = (exped[j$var66] / (r * reduceVar$sum$13));
													}
												}
											}
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											if(!guard$sample32put73[((j$var66 - 0) / 1)]) {
												guard$sample32put73[((j$var66 - 0) / 1)] = true;
												{
													double reduceVar$sum$14 = 0.0;
													for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
														double k$var54 = reduceVar$sum$14;
														double l$var55 = exped[cv$reduction54Index];
														reduceVar$sum$14 = (k$var54 + l$var55);
													}
													expedNorm[j$var66] = (exped[j$var66] / (r * reduceVar$sum$14));
												}
											}
										}
									}
								}
							}
						}
					}
					{
						boolean[][] guard$sample32put135 = guard$sample32put135$global;
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121))
																guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)] = false;
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121))
															guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)] = false;
													}
												}
											}
										}
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																if(!guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)]) {
																	guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)] = true;
																	{
																		weekly_ut[((t - 0) / 1)][j$var121] = expedNorm[j$var121];
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															if(!guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)]) {
																guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)] = true;
																{
																	weekly_ut[((t - 0) / 1)][j$var121] = expedNorm[j$var121];
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
						boolean[][] guard$sample32put164 = guard$sample32put164$global;
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																	{
																		for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1)
																			guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = false;
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																	if((j$var121 == j$var153))
																		guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = false;
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																{
																	for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1)
																		guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = false;
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																if((j$var121 == j$var153))
																	guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = false;
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																	{
																		for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																			if(!guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)]) {
																				guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = true;
																				{
																					double reduceVar$denom$15 = 0.0;
																					for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
																						double k$var139 = reduceVar$denom$15;
																						double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
																						reduceVar$denom$15 = (k$var139 + l$var140);
																					}
																					weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$15);
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																	if((j$var121 == j$var153)) {
																		if(!guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)]) {
																			guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = true;
																			{
																				double reduceVar$denom$16 = 0.0;
																				for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
																					double k$var139 = reduceVar$denom$16;
																					double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
																					reduceVar$denom$16 = (k$var139 + l$var140);
																				}
																				weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$16);
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																{
																	for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																		if(!guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)]) {
																			guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = true;
																			{
																				double reduceVar$denom$17 = 0.0;
																				for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
																					double k$var139 = reduceVar$denom$17;
																					double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
																					reduceVar$denom$17 = (k$var139 + l$var140);
																				}
																				weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$17);
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																if((j$var121 == j$var153)) {
																	if(!guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)]) {
																		guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = true;
																		{
																			double reduceVar$denom$18 = 0.0;
																			for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
																				double k$var139 = reduceVar$denom$18;
																				double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
																				reduceVar$denom$18 = (k$var139 + l$var140);
																			}
																			weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$18);
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
				double cv$temp$0$var17;
				{
					cv$temp$0$var17 = 0.0;
				}
				double cv$temp$1$var18;
				{
					cv$temp$1$var18 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var17) / Math.sqrt(cv$temp$1$var18))) - (0.5 * Math.log(cv$temp$1$var18))));
				{
					{
						boolean[] guard$sample32multinomial165 = guard$sample32multinomial165$global;
						if((0 < numTimeSteps)) {
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																	{
																		guard$sample32multinomial165[((t - 0) / 1)] = false;
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
												for(int t = 0; t < numTimeSteps; t += 1) {
													for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
														if(avail[t][j$var121]) {
															if((j$var66 == j$var121)) {
																for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																	if((j$var121 == j$var153))
																		guard$sample32multinomial165[((t - 0) / 1)] = false;
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																{
																	guard$sample32multinomial165[((t - 0) / 1)] = false;
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
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																if((j$var121 == j$var153))
																	guard$sample32multinomial165[((t - 0) / 1)] = false;
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
							double traceTempVariable$var42$22_1 = cv$currentValue;
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									double traceTempVariable$k$22_3 = Math.exp(traceTempVariable$var42$22_1);
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											if((0 < avail[0].length)) {
												double reduceVar$sum$15 = 0.0;
												for(int cv$reduction2544Index = 0; cv$reduction2544Index < j$var41; cv$reduction2544Index += 1) {
													double k$var54 = reduceVar$sum$15;
													double l$var55 = exped[cv$reduction2544Index];
													reduceVar$sum$15 = (k$var54 + l$var55);
												}
												for(int cv$reduction2544Index = (j$var41 + 1); cv$reduction2544Index < avail[0].length; cv$reduction2544Index += 1) {
													double k$var54 = reduceVar$sum$15;
													double l$var55 = exped[cv$reduction2544Index];
													reduceVar$sum$15 = (k$var54 + l$var55);
												}
												double cv$reduced54 = reduceVar$sum$15;
												reduceVar$sum$15 = (traceTempVariable$k$22_3 + cv$reduced54);
												double traceTempVariable$sum$22_4 = reduceVar$sum$15;
												for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
													double traceTempVariable$var127$22_6 = (exped[j$var66] / (r * traceTempVariable$sum$22_4));
													for(int t = 0; t < numTimeSteps; t += 1) {
														for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
															if(avail[t][j$var121]) {
																if((j$var66 == j$var121)) {
																	double traceTempVariable$k$22_9 = traceTempVariable$var127$22_6;
																	if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																		{
																			if((0 < (avail[0].length + 1))) {
																				double reduceVar$denom$19 = 0.0;
																				for(int cv$reduction2565Index = 0; cv$reduction2565Index < j$var121; cv$reduction2565Index += 1) {
																					double k$var139 = reduceVar$denom$19;
																					double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction2565Index];
																					reduceVar$denom$19 = (k$var139 + l$var140);
																				}
																				for(int cv$reduction2565Index = (j$var121 + 1); cv$reduction2565Index < (avail[0].length + 1); cv$reduction2565Index += 1) {
																					double k$var139 = reduceVar$denom$19;
																					double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction2565Index];
																					reduceVar$denom$19 = (k$var139 + l$var140);
																				}
																				double cv$reduced144 = reduceVar$denom$19;
																				reduceVar$denom$19 = (traceTempVariable$k$22_9 + cv$reduced144);
																				double traceTempVariable$denom$22_10 = reduceVar$denom$19;
																				if(!guard$sample32multinomial165[((t - 0) / 1)]) {
																					guard$sample32multinomial165[((t - 0) / 1)] = true;
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
																												int cv$temp$3$$var1465;
																												{
																													int $var1465 = (avail[0].length + 1);
																													cv$temp$3$$var1465 = $var1465;
																												}
																												int cv$temp$4$arrivals;
																												{
																													cv$temp$4$arrivals = arrivals[((t - 0) / 1)];
																												}
																												if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$$var1465, cv$temp$4$arrivals)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$$var1465, cv$temp$4$arrivals)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$$var1465, cv$temp$4$arrivals));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$$var1465, cv$temp$4$arrivals)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$$var1465, cv$temp$4$arrivals)));
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
							double traceTempVariable$var42$23_1 = cv$currentValue;
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									double traceTempVariable$k$23_3 = Math.exp(traceTempVariable$var42$23_1);
									if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
										{
											if((0 < avail[0].length)) {
												double reduceVar$sum$16 = 0.0;
												for(int cv$reduction2590Index = 0; cv$reduction2590Index < j$var41; cv$reduction2590Index += 1) {
													double k$var54 = reduceVar$sum$16;
													double l$var55 = exped[cv$reduction2590Index];
													reduceVar$sum$16 = (k$var54 + l$var55);
												}
												for(int cv$reduction2590Index = (j$var41 + 1); cv$reduction2590Index < avail[0].length; cv$reduction2590Index += 1) {
													double k$var54 = reduceVar$sum$16;
													double l$var55 = exped[cv$reduction2590Index];
													reduceVar$sum$16 = (k$var54 + l$var55);
												}
												double cv$reduced54 = reduceVar$sum$16;
												reduceVar$sum$16 = (traceTempVariable$k$23_3 + cv$reduced54);
												double traceTempVariable$sum$23_4 = reduceVar$sum$16;
												for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
													double traceTempVariable$var127$23_6 = (exped[j$var66] / (r * traceTempVariable$sum$23_4));
													for(int t = 0; t < numTimeSteps; t += 1) {
														for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
															if(avail[t][j$var121]) {
																if((j$var66 == j$var121)) {
																	double traceTempVariable$var154$23_9 = traceTempVariable$var127$23_6;
																	for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																		if((j$var121 == j$var153)) {
																			if(!guard$sample32multinomial165[((t - 0) / 1)]) {
																				guard$sample32multinomial165[((t - 0) / 1)] = true;
																				{
																					if((0 < numTimeSteps)) {
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										if((0 < numTimeSteps)) {
																											double[] cv$temp$5$weekly_rates;
																											{
																												cv$temp$5$weekly_rates = weekly_rates[((t - 0) / 1)];
																											}
																											int cv$temp$6$$var1472;
																											{
																												int $var1472 = (avail[0].length + 1);
																												cv$temp$6$$var1472 = $var1472;
																											}
																											int cv$temp$7$arrivals;
																											{
																												cv$temp$7$arrivals = arrivals[((t - 0) / 1)];
																											}
																											if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$5$weekly_rates, cv$temp$6$$var1472, cv$temp$7$arrivals)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$5$weekly_rates, cv$temp$6$$var1472, cv$temp$7$arrivals)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$5$weekly_rates, cv$temp$6$$var1472, cv$temp$7$arrivals));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$5$weekly_rates, cv$temp$6$$var1472, cv$temp$7$arrivals)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$5$weekly_rates, cv$temp$6$$var1472, cv$temp$7$arrivals)));
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
							double traceTempVariable$var42$24_1 = cv$currentValue;
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									double traceTempVariable$var67$24_3 = Math.exp(traceTempVariable$var42$24_1);
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											double reduceVar$sum$17 = 0.0;
											for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
												double k$var54 = reduceVar$sum$17;
												double l$var55 = exped[cv$reduction54Index];
												reduceVar$sum$17 = (k$var54 + l$var55);
											}
											double traceTempVariable$var127$24_5 = (traceTempVariable$var67$24_3 / (r * reduceVar$sum$17));
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															double traceTempVariable$k$24_8 = traceTempVariable$var127$24_5;
															if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
																{
																	if((0 < (avail[0].length + 1))) {
																		double reduceVar$denom$20 = 0.0;
																		for(int cv$reduction2636Index = 0; cv$reduction2636Index < j$var121; cv$reduction2636Index += 1) {
																			double k$var139 = reduceVar$denom$20;
																			double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction2636Index];
																			reduceVar$denom$20 = (k$var139 + l$var140);
																		}
																		for(int cv$reduction2636Index = (j$var121 + 1); cv$reduction2636Index < (avail[0].length + 1); cv$reduction2636Index += 1) {
																			double k$var139 = reduceVar$denom$20;
																			double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction2636Index];
																			reduceVar$denom$20 = (k$var139 + l$var140);
																		}
																		double cv$reduced144 = reduceVar$denom$20;
																		reduceVar$denom$20 = (traceTempVariable$k$24_8 + cv$reduced144);
																		double traceTempVariable$denom$24_9 = reduceVar$denom$20;
																		if(!guard$sample32multinomial165[((t - 0) / 1)]) {
																			guard$sample32multinomial165[((t - 0) / 1)] = true;
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
																										int cv$temp$9$$var1479;
																										{
																											int $var1479 = (avail[0].length + 1);
																											cv$temp$9$$var1479 = $var1479;
																										}
																										int cv$temp$10$arrivals;
																										{
																											cv$temp$10$arrivals = arrivals[((t - 0) / 1)];
																										}
																										if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$8$weekly_rates, cv$temp$9$$var1479, cv$temp$10$arrivals)) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$8$weekly_rates, cv$temp$9$$var1479, cv$temp$10$arrivals)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$8$weekly_rates, cv$temp$9$$var1479, cv$temp$10$arrivals));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$8$weekly_rates, cv$temp$9$$var1479, cv$temp$10$arrivals)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$8$weekly_rates, cv$temp$9$$var1479, cv$temp$10$arrivals)));
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
							double traceTempVariable$var42$25_1 = cv$currentValue;
							for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
								if((var29 == j$var41)) {
									double traceTempVariable$var67$25_3 = Math.exp(traceTempVariable$var42$25_1);
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if((j$var41 == j$var66)) {
											double reduceVar$sum$18 = 0.0;
											for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
												double k$var54 = reduceVar$sum$18;
												double l$var55 = exped[cv$reduction54Index];
												reduceVar$sum$18 = (k$var54 + l$var55);
											}
											double traceTempVariable$var127$25_5 = (traceTempVariable$var67$25_3 / (r * reduceVar$sum$18));
											for(int t = 0; t < numTimeSteps; t += 1) {
												for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
													if(avail[t][j$var121]) {
														if((j$var66 == j$var121)) {
															double traceTempVariable$var154$25_8 = traceTempVariable$var127$25_5;
															for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																if((j$var121 == j$var153)) {
																	if(!guard$sample32multinomial165[((t - 0) / 1)]) {
																		guard$sample32multinomial165[((t - 0) / 1)] = true;
																		{
																			if((0 < numTimeSteps)) {
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						{
																							{
																								if((0 < numTimeSteps)) {
																									double[] cv$temp$11$weekly_rates;
																									{
																										cv$temp$11$weekly_rates = weekly_rates[((t - 0) / 1)];
																									}
																									int cv$temp$12$$var1486;
																									{
																										int $var1486 = (avail[0].length + 1);
																										cv$temp$12$$var1486 = $var1486;
																									}
																									int cv$temp$13$arrivals;
																									{
																										cv$temp$13$arrivals = arrivals[((t - 0) / 1)];
																									}
																									if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$11$weekly_rates, cv$temp$12$$var1486, cv$temp$13$arrivals)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$11$weekly_rates, cv$temp$12$$var1486, cv$temp$13$arrivals)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$11$weekly_rates, cv$temp$12$$var1486, cv$temp$13$arrivals));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$11$weekly_rates, cv$temp$12$$var1486, cv$temp$13$arrivals)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t - 0) / 1)], cv$temp$11$weekly_rates, cv$temp$12$$var1486, cv$temp$13$arrivals)));
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
			double var30 = cv$originalValue;
			ut[var29] = var30;
			{
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							{
								exped[j$var41] = Math.exp(ut[j$var41]);
							}
						}
					}
				}
			}
			{
				boolean[] guard$sample32put73 = guard$sample32put73$global;
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
								{
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1)
										guard$sample32put73[((j$var66 - 0) / 1)] = false;
								}
							}
						}
					}
				}
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
								if((j$var41 == j$var66))
									guard$sample32put73[((j$var66 - 0) / 1)] = false;
							}
						}
					}
				}
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
								{
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										if(!guard$sample32put73[((j$var66 - 0) / 1)]) {
											guard$sample32put73[((j$var66 - 0) / 1)] = true;
											{
												double reduceVar$sum$19 = 0.0;
												for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
													double k$var54 = reduceVar$sum$19;
													double l$var55 = exped[cv$reduction54Index];
													reduceVar$sum$19 = (k$var54 + l$var55);
												}
												expedNorm[j$var66] = (exped[j$var66] / (r * reduceVar$sum$19));
											}
										}
									}
								}
							}
						}
					}
				}
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
								if((j$var41 == j$var66)) {
									if(!guard$sample32put73[((j$var66 - 0) / 1)]) {
										guard$sample32put73[((j$var66 - 0) / 1)] = true;
										{
											double reduceVar$sum$20 = 0.0;
											for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
												double k$var54 = reduceVar$sum$20;
												double l$var55 = exped[cv$reduction54Index];
												reduceVar$sum$20 = (k$var54 + l$var55);
											}
											expedNorm[j$var66] = (exped[j$var66] / (r * reduceVar$sum$20));
										}
									}
								}
							}
						}
					}
				}
			}
			{
				boolean[][] guard$sample32put135 = guard$sample32put135$global;
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
								{
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										for(int t = 0; t < numTimeSteps; t += 1) {
											for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
												if(avail[t][j$var121]) {
													if((j$var66 == j$var121))
														guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)] = false;
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
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
								if((j$var41 == j$var66)) {
									for(int t = 0; t < numTimeSteps; t += 1) {
										for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if((j$var66 == j$var121))
													guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
					}
				}
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
								{
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										for(int t = 0; t < numTimeSteps; t += 1) {
											for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
												if(avail[t][j$var121]) {
													if((j$var66 == j$var121)) {
														if(!guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)]) {
															guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)] = true;
															{
																weekly_ut[((t - 0) / 1)][j$var121] = expedNorm[j$var121];
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
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
								if((j$var41 == j$var66)) {
									for(int t = 0; t < numTimeSteps; t += 1) {
										for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if((j$var66 == j$var121)) {
													if(!guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)]) {
														guard$sample32put135[((t - 0) / 1)][((j$var121 - 0) / 1)] = true;
														{
															weekly_ut[((t - 0) / 1)][j$var121] = expedNorm[j$var121];
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
				boolean[][] guard$sample32put164 = guard$sample32put164$global;
				if((0 < numTimeSteps)) {
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
								{
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										for(int t = 0; t < numTimeSteps; t += 1) {
											for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
												if(avail[t][j$var121]) {
													if((j$var66 == j$var121)) {
														if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
															{
																for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1)
																	guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = false;
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
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
								{
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										for(int t = 0; t < numTimeSteps; t += 1) {
											for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
												if(avail[t][j$var121]) {
													if((j$var66 == j$var121)) {
														for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
															if((j$var121 == j$var153))
																guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = false;
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
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
								if((j$var41 == j$var66)) {
									for(int t = 0; t < numTimeSteps; t += 1) {
										for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if((j$var66 == j$var121)) {
													if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
														{
															for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1)
																guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = false;
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
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
								if((j$var41 == j$var66)) {
									for(int t = 0; t < numTimeSteps; t += 1) {
										for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if((j$var66 == j$var121)) {
													for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
														if((j$var121 == j$var153))
															guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = false;
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
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
								{
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										for(int t = 0; t < numTimeSteps; t += 1) {
											for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
												if(avail[t][j$var121]) {
													if((j$var66 == j$var121)) {
														if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
															{
																for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																	if(!guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)]) {
																		guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = true;
																		{
																			double reduceVar$denom$21 = 0.0;
																			for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
																				double k$var139 = reduceVar$denom$21;
																				double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
																				reduceVar$denom$21 = (k$var139 + l$var140);
																			}
																			weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$21);
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
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							if(((0 <= j$var41) && (j$var41 < avail[0].length))) {
								{
									for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
										for(int t = 0; t < numTimeSteps; t += 1) {
											for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
												if(avail[t][j$var121]) {
													if((j$var66 == j$var121)) {
														for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
															if((j$var121 == j$var153)) {
																if(!guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)]) {
																	guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = true;
																	{
																		double reduceVar$denom$22 = 0.0;
																		for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
																			double k$var139 = reduceVar$denom$22;
																			double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
																			reduceVar$denom$22 = (k$var139 + l$var140);
																		}
																		weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$22);
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
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
								if((j$var41 == j$var66)) {
									for(int t = 0; t < numTimeSteps; t += 1) {
										for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if((j$var66 == j$var121)) {
													if(((0 <= j$var121) && (j$var121 < (avail[0].length + 1)))) {
														{
															for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
																if(!guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)]) {
																	guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = true;
																	{
																		double reduceVar$denom$23 = 0.0;
																		for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
																			double k$var139 = reduceVar$denom$23;
																			double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
																			reduceVar$denom$23 = (k$var139 + l$var140);
																		}
																		weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$23);
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
					for(int j$var41 = 0; j$var41 < avail[0].length; j$var41 += 1) {
						if((var29 == j$var41)) {
							for(int j$var66 = 0; j$var66 < avail[0].length; j$var66 += 1) {
								if((j$var41 == j$var66)) {
									for(int t = 0; t < numTimeSteps; t += 1) {
										for(int j$var121 = 0; j$var121 < avail[0].length; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if((j$var66 == j$var121)) {
													for(int j$var153 = 0; j$var153 < (avail[0].length + 1); j$var153 += 1) {
														if((j$var121 == j$var153)) {
															if(!guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)]) {
																guard$sample32put164[((t - 0) / 1)][((j$var153 - 0) / 1)] = true;
																{
																	double reduceVar$denom$24 = 0.0;
																	for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
																		double k$var139 = reduceVar$denom$24;
																		double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
																		reduceVar$denom$24 = (k$var139 + l$var140);
																	}
																	weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$24);
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
			int cv$max_j$var66 = 0;
			if((0 < avail.length))
				cv$max_j$var66 = Math.max(cv$max_j$var66, ((avail[0].length - 0) / 1));
			guard$sample32put73$global = new boolean[cv$max_j$var66];
		}
		{
			int cv$max_t = 0;
			int cv$max_j$var121 = 0;
			if((0 < avail.length)) {
				for(int t = 0; t < avail.length; t += 1)
					cv$max_j$var121 = Math.max(cv$max_j$var121, ((avail[0].length - 0) / 1));
				cv$max_t = Math.max(cv$max_t, ((avail.length - 0) / 1));
			}
			guard$sample32put135$global = new boolean[cv$max_t][cv$max_j$var121];
		}
		{
			int cv$max_t = 0;
			int cv$max_j$var153 = 0;
			if((0 < avail.length)) {
				for(int t = 0; t < avail.length; t += 1)
					cv$max_j$var153 = Math.max(cv$max_j$var153, (((avail[0].length + 1) - 0) / 1));
				cv$max_t = Math.max(cv$max_t, ((avail.length - 0) / 1));
			}
			guard$sample32put164$global = new boolean[cv$max_t][cv$max_j$var153];
		}
		{
			int cv$max_t = 0;
			if((0 < avail.length))
				cv$max_t = Math.max(cv$max_t, ((avail.length - 0) / 1));
			guard$sample32multinomial165$global = new boolean[cv$max_t];
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
				for(int var80 = 0; var80 < avail.length; var80 += 1)
					sales[var80] = new int[avail[0].length];
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
		{
			if((0 < avail.length)) {
				for(int t = 0; t < avail.length; t += 1)
					weekly_sales[((t - 0) / 1)] = new int[(avail[0].length + 1)];
			}
			weekly_sales = new int[((((avail.length - 1) - 0) / 1) + 1)][];
		}
		{
			logProbability$sample32 = new double[((((avail[0].length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var106 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample112 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var108 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample114 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var157 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample166 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if((0 < numTimeSteps)) {
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
							if(!fixedFlag$sample32)
								ut[var29] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
						}
				}
			);
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var41, int forEnd$j$var41, int threadID$j$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var41 = forStart$j$var41; j$var41 < forEnd$j$var41; j$var41 += 1) {
							if(!fixedFlag$sample32)
								exped[j$var41] = Math.exp(ut[j$var41]);
						}
				}
			);
			double reduceVar$sum$21 = 0.0;
			for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
				double k$var54 = reduceVar$sum$21;
				double l$var55 = exped[cv$reduction54Index];
				if(!fixedFlag$sample32)
					reduceVar$sum$21 = (k$var54 + l$var55);
			}
			double reduceVar$sum$21$1 = reduceVar$sum$21;
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var66, int forEnd$j$var66, int threadID$j$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var66 = forStart$j$var66; j$var66 < forEnd$j$var66; j$var66 += 1) {
							if(!fixedFlag$sample32)
								expedNorm[j$var66] = (exped[j$var66] / (r * reduceVar$sum$21$1));
						}
				}
			);
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							if(!fixedFlag$sample112)
								lambda[((t - 0) / 1)] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
							int reduceVar$numSales$14 = 0;
							for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1) {
								int k$var98 = reduceVar$numSales$14;
								int l$var99 = ObsSales[t][cv$reduction100Index];
								if(!fixedFlag$sample114)
									reduceVar$numSales$14 = (k$var98 + l$var99);
							}
							if(!fixedFlag$sample114)
								arrivals[((t - 0) / 1)] = (reduceVar$numSales$14 + DistributionSampling.samplePoisson(RNG$1, lambda[((t - 0) / 1)]));
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var121, int forEnd$j$var121, int threadID$j$var121, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var121 = forStart$j$var121; j$var121 < forEnd$j$var121; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if(!fixedFlag$sample32)
													weekly_ut[((t - 0) / 1)][j$var121] = expedNorm[j$var121];
											} else
												weekly_ut[((t - 0) / 1)][j$var121] = 0.0;
										}
								}
							);
							weekly_ut[((t - 0) / 1)][avail[0].length] = 1.0;
							double reduceVar$denom$25 = 0.0;
							for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
								double k$var139 = reduceVar$denom$25;
								double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
								if(!fixedFlag$sample32)
									reduceVar$denom$25 = (k$var139 + l$var140);
							}
							double reduceVar$denom$25$2 = reduceVar$denom$25;
							parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
								(int forStart$j$var153, int forEnd$j$var153, int threadID$j$var153, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var153 = forStart$j$var153; j$var153 < forEnd$j$var153; j$var153 += 1) {
											if(!fixedFlag$sample32)
												weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$25$2);
										}
								}
							);
							DistributionSampling.sampleMultinomial(RNG$1, weekly_rates[((t - 0) / 1)], (avail[0].length + 1), arrivals[((t - 0) / 1)], weekly_sales[((t - 0) / 1)]);
							int[] observed_weekly_sales = sales[t];
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var168, int forEnd$j$var168, int threadID$j$var168, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var168 = forStart$j$var168; j$var168 < forEnd$j$var168; j$var168 += 1)
											observed_weekly_sales[j$var168] = weekly_sales[((t - 0) / 1)][j$var168];
								}
							);
						}
				}
			);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if((0 < numTimeSteps)) {
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
							if(!fixedFlag$sample32)
								ut[var29] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
						}
				}
			);
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var41, int forEnd$j$var41, int threadID$j$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var41 = forStart$j$var41; j$var41 < forEnd$j$var41; j$var41 += 1) {
							if(!fixedFlag$sample32)
								exped[j$var41] = Math.exp(ut[j$var41]);
						}
				}
			);
			double reduceVar$sum$23 = 0.0;
			for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
				double k$var54 = reduceVar$sum$23;
				double l$var55 = exped[cv$reduction54Index];
				if(!fixedFlag$sample32)
					reduceVar$sum$23 = (k$var54 + l$var55);
			}
			double reduceVar$sum$23$1 = reduceVar$sum$23;
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var66, int forEnd$j$var66, int threadID$j$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var66 = forStart$j$var66; j$var66 < forEnd$j$var66; j$var66 += 1) {
							if(!fixedFlag$sample32)
								expedNorm[j$var66] = (exped[j$var66] / (r * reduceVar$sum$23$1));
						}
				}
			);
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							if(!fixedFlag$sample112)
								lambda[((t - 0) / 1)] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
							int reduceVar$numSales$16 = 0;
							for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1) {
								int k$var98 = reduceVar$numSales$16;
								int l$var99 = ObsSales[t][cv$reduction100Index];
								if(!fixedFlag$sample114)
									reduceVar$numSales$16 = (k$var98 + l$var99);
							}
							if(!fixedFlag$sample114)
								arrivals[((t - 0) / 1)] = (reduceVar$numSales$16 + DistributionSampling.samplePoisson(RNG$1, lambda[((t - 0) / 1)]));
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var121, int forEnd$j$var121, int threadID$j$var121, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var121 = forStart$j$var121; j$var121 < forEnd$j$var121; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if(!fixedFlag$sample32)
													weekly_ut[((t - 0) / 1)][j$var121] = expedNorm[j$var121];
											} else
												weekly_ut[((t - 0) / 1)][j$var121] = 0.0;
										}
								}
							);
							weekly_ut[((t - 0) / 1)][avail[0].length] = 1.0;
							double reduceVar$denom$27 = 0.0;
							for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
								double k$var139 = reduceVar$denom$27;
								double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
								if(!fixedFlag$sample32)
									reduceVar$denom$27 = (k$var139 + l$var140);
							}
							double reduceVar$denom$27$2 = reduceVar$denom$27;
							parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
								(int forStart$j$var153, int forEnd$j$var153, int threadID$j$var153, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var153 = forStart$j$var153; j$var153 < forEnd$j$var153; j$var153 += 1) {
											if(!fixedFlag$sample32)
												weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$27$2);
										}
								}
							);
						}
				}
			);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if((0 < numTimeSteps)) {
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
							if(!fixedFlag$sample32)
								ut[var29] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
						}
				}
			);
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var41, int forEnd$j$var41, int threadID$j$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var41 = forStart$j$var41; j$var41 < forEnd$j$var41; j$var41 += 1) {
							if(!fixedFlag$sample32)
								exped[j$var41] = Math.exp(ut[j$var41]);
						}
				}
			);
			double reduceVar$sum$22 = 0.0;
			for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
				double k$var54 = reduceVar$sum$22;
				double l$var55 = exped[cv$reduction54Index];
				if(!fixedFlag$sample32)
					reduceVar$sum$22 = (k$var54 + l$var55);
			}
			double reduceVar$sum$22$1 = reduceVar$sum$22;
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var66, int forEnd$j$var66, int threadID$j$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var66 = forStart$j$var66; j$var66 < forEnd$j$var66; j$var66 += 1) {
							if(!fixedFlag$sample32)
								expedNorm[j$var66] = (exped[j$var66] / (r * reduceVar$sum$22$1));
						}
				}
			);
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							if(!fixedFlag$sample112)
								lambda[((t - 0) / 1)] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
							int reduceVar$numSales$15 = 0;
							for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1) {
								int k$var98 = reduceVar$numSales$15;
								int l$var99 = ObsSales[t][cv$reduction100Index];
								if(!fixedFlag$sample114)
									reduceVar$numSales$15 = (k$var98 + l$var99);
							}
							if(!fixedFlag$sample114)
								arrivals[((t - 0) / 1)] = (reduceVar$numSales$15 + DistributionSampling.samplePoisson(RNG$1, lambda[((t - 0) / 1)]));
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var121, int forEnd$j$var121, int threadID$j$var121, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var121 = forStart$j$var121; j$var121 < forEnd$j$var121; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if(!fixedFlag$sample32)
													weekly_ut[((t - 0) / 1)][j$var121] = expedNorm[j$var121];
											} else
												weekly_ut[((t - 0) / 1)][j$var121] = 0.0;
										}
								}
							);
							weekly_ut[((t - 0) / 1)][avail[0].length] = 1.0;
							double reduceVar$denom$26 = 0.0;
							for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
								double k$var139 = reduceVar$denom$26;
								double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
								if(!fixedFlag$sample32)
									reduceVar$denom$26 = (k$var139 + l$var140);
							}
							double reduceVar$denom$26$2 = reduceVar$denom$26;
							parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
								(int forStart$j$var153, int forEnd$j$var153, int threadID$j$var153, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var153 = forStart$j$var153; j$var153 < forEnd$j$var153; j$var153 += 1) {
											if(!fixedFlag$sample32)
												weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$26$2);
										}
								}
							);
						}
				}
			);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if((0 < numTimeSteps)) {
				for(int var29 = 0; var29 < avail[0].length; var29 += 1) {
					if(!fixedFlag$sample32)
						sample32(var29);
				}
				parallelFor(RNG$, 0, numTimeSteps, 1,
					(int forStart$t, int forEnd$t, int threadID$t, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int t = forStart$t; t < forEnd$t; t += 1) {
								if(!fixedFlag$sample112)
									sample112(t, threadID$t, RNG$1);
								if(!fixedFlag$sample114)
									sample114(t, threadID$t, RNG$1);
							}
					}
				);
			}
		} else {
			if((0 < numTimeSteps)) {
				parallelFor(RNG$, 0, numTimeSteps, 1,
					(int forStart$t, int forEnd$t, int threadID$t, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int t = forStart$t; t < forEnd$t; t += 1) {
								if(!fixedFlag$sample114)
									sample114(t, threadID$t, RNG$1);
								if(!fixedFlag$sample112)
									sample112(t, threadID$t, RNG$1);
							}
					}
				);
				for(int var29 = (avail[0].length - ((((avail[0].length - 1) - 0) % 1) + 1)); var29 >= ((0 - 1) + 1); var29 -= 1) {
					if(!fixedFlag$sample32)
						sample32(var29);
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
		logProbability$var19 = 0.0;
		logProbability$ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$expedNorm = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$weekly_rates = 0.0;
		if(!fixedProbFlag$sample32) {
			if((0 < numTimeSteps)) {
				for(int var29 = 0; var29 < avail[0].length; var29 += 1)
					logProbability$sample32[((var29 - 0) / 1)] = 0.0;
			}
		}
		if((0 < numTimeSteps)) {
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var106[((t - 0) / 1)] = 0.0;
		}
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample112) {
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample112[((t - 0) / 1)] = 0.0;
			}
		}
		if((0 < numTimeSteps)) {
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var108[((t - 0) / 1)] = 0.0;
		}
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample114) {
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample114[((t - 0) / 1)] = 0.0;
			}
		}
		if((0 < numTimeSteps)) {
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var157[((t - 0) / 1)] = 0.0;
		}
		logProbability$weekly_sales = 0.0;
		logProbability$sales = 0.0;
		if(!fixedProbFlag$sample166) {
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample166[((t - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample112)
			logProbabilityValue$sample112();
		if(fixedFlag$sample114)
			logProbabilityValue$sample114();
		logProbabilityValue$sample166();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample32();
		logProbabilityValue$sample112();
		logProbabilityValue$sample114();
		logProbabilityValue$sample166();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample32();
		logProbabilityValue$sample112();
		logProbabilityValue$sample114();
		logProbabilityValue$sample166();
	}

	@Override
	public final void logProbabilityGeneration() {
		if((0 < numTimeSteps)) {
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
							if(!fixedFlag$sample32)
								ut[var29] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
						}
				}
			);
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var41, int forEnd$j$var41, int threadID$j$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var41 = forStart$j$var41; j$var41 < forEnd$j$var41; j$var41 += 1) {
							if(!fixedFlag$sample32)
								exped[j$var41] = Math.exp(ut[j$var41]);
						}
				}
			);
			double reduceVar$sum$24 = 0.0;
			for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
				double k$var54 = reduceVar$sum$24;
				double l$var55 = exped[cv$reduction54Index];
				if(!fixedFlag$sample32)
					reduceVar$sum$24 = (k$var54 + l$var55);
			}
			double reduceVar$sum$24$1 = reduceVar$sum$24;
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var66, int forEnd$j$var66, int threadID$j$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var66 = forStart$j$var66; j$var66 < forEnd$j$var66; j$var66 += 1) {
							if(!fixedFlag$sample32)
								expedNorm[j$var66] = (exped[j$var66] / (r * reduceVar$sum$24$1));
						}
				}
			);
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							if(!fixedFlag$sample112)
								lambda[((t - 0) / 1)] = DistributionSampling.sampleGamma(RNG$1, 10.0, 10.0);
							int reduceVar$numSales$17 = 0;
							for(int cv$reduction100Index = 0; cv$reduction100Index < ObsSales[t].length; cv$reduction100Index += 1) {
								int k$var98 = reduceVar$numSales$17;
								int l$var99 = ObsSales[t][cv$reduction100Index];
								if(!fixedFlag$sample114)
									reduceVar$numSales$17 = (k$var98 + l$var99);
							}
							if(!fixedFlag$sample114)
								arrivals[((t - 0) / 1)] = (reduceVar$numSales$17 + DistributionSampling.samplePoisson(RNG$1, lambda[((t - 0) / 1)]));
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var121, int forEnd$j$var121, int threadID$j$var121, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var121 = forStart$j$var121; j$var121 < forEnd$j$var121; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if(!fixedFlag$sample32)
													weekly_ut[((t - 0) / 1)][j$var121] = expedNorm[j$var121];
											} else
												weekly_ut[((t - 0) / 1)][j$var121] = 0.0;
										}
								}
							);
							weekly_ut[((t - 0) / 1)][avail[0].length] = 1.0;
							double reduceVar$denom$28 = 0.0;
							for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
								double k$var139 = reduceVar$denom$28;
								double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
								if(!fixedFlag$sample32)
									reduceVar$denom$28 = (k$var139 + l$var140);
							}
							double reduceVar$denom$28$2 = reduceVar$denom$28;
							parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
								(int forStart$j$var153, int forEnd$j$var153, int threadID$j$var153, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var153 = forStart$j$var153; j$var153 < forEnd$j$var153; j$var153 += 1) {
											if(!fixedFlag$sample32)
												weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$28$2);
										}
								}
							);
						}
				}
			);
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
					for(int j$var168 = (avail[0].length - ((((avail[0].length - 1) - 0) % 1) + 1)); j$var168 >= ((0 - 1) + 1); j$var168 -= 1) {
						if((0 < numTimeSteps)) {
							int[] observed_weekly_sales;
							observed_weekly_sales = sales[t];
							weekly_sales[((t - 0) / 1)][j$var168] = observed_weekly_sales[j$var168];
						}
					}
				}
			}
		}
	}

	@Override
	public final void setIntermediates() {
		if((0 < numTimeSteps)) {
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var41, int forEnd$j$var41, int threadID$j$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var41 = forStart$j$var41; j$var41 < forEnd$j$var41; j$var41 += 1) {
							if(true)
								exped[j$var41] = Math.exp(ut[j$var41]);
						}
				}
			);
			double reduceVar$sum$25 = 0.0;
			for(int cv$reduction54Index = 0; cv$reduction54Index < avail[0].length; cv$reduction54Index += 1) {
				double k$var54 = reduceVar$sum$25;
				double l$var55 = exped[cv$reduction54Index];
				reduceVar$sum$25 = (k$var54 + l$var55);
			}
			double reduceVar$sum$25$1 = reduceVar$sum$25;
			parallelFor(RNG$, 0, avail[0].length, 1,
				(int forStart$j$var66, int forEnd$j$var66, int threadID$j$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j$var66 = forStart$j$var66; j$var66 < forEnd$j$var66; j$var66 += 1) {
							if(true)
								expedNorm[j$var66] = (exped[j$var66] / (r * reduceVar$sum$25$1));
						}
				}
			);
			parallelFor(RNG$, 0, numTimeSteps, 1,
				(int forStart$index$t, int forEnd$index$t, int threadID$index$t, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$t = forStart$index$t; index$t < forEnd$index$t; index$t += 1) {
							int t = index$t;
							int threadID$t = threadID$index$t;
							parallelFor(RNG$1, 0, avail[0].length, 1,
								(int forStart$j$var121, int forEnd$j$var121, int threadID$j$var121, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var121 = forStart$j$var121; j$var121 < forEnd$j$var121; j$var121 += 1) {
											if(avail[t][j$var121]) {
												if(true)
													weekly_ut[((t - 0) / 1)][j$var121] = expedNorm[j$var121];
											}
										}
								}
							);
							double reduceVar$denom$29 = 0.0;
							for(int cv$reduction144Index = 0; cv$reduction144Index < (avail[0].length + 1); cv$reduction144Index += 1) {
								double k$var139 = reduceVar$denom$29;
								double l$var140 = weekly_ut[((t - 0) / 1)][cv$reduction144Index];
								reduceVar$denom$29 = (k$var139 + l$var140);
							}
							double reduceVar$denom$29$2 = reduceVar$denom$29;
							parallelFor(RNG$1, 0, (avail[0].length + 1), 1,
								(int forStart$j$var153, int forEnd$j$var153, int threadID$j$var153, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var153 = forStart$j$var153; j$var153 < forEnd$j$var153; j$var153 += 1) {
											if(true)
												weekly_rates[((t - 0) / 1)][j$var153] = (weekly_ut[((t - 0) / 1)][j$var153] / reduceVar$denom$29$2);
										}
								}
							);
						}
				}
			);
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
		     + "model Vulcano2012basicDG(int[][] ObsSales, boolean[][] avail) {\n"
		     + "   // avail is the availability matrix, numTimeSteps-by-numProducts\n"
		     + "   // r is the normalization constant here, number between 0 and 1. \"Relative appeal of the outside option\"\n"
		     + "   double r = 0.3;\n"
		     + "    \n"
		     + "   int numTimeSteps = avail.length;\n"
		     + "   if(numTimeSteps > 0) {\n"
		     + "      int numProducts = avail[0].length;\n"
		     + "\n"
		     + "      // draw utilities\n"
		     + "      double[] ut = gaussian(0, 1).sample(numProducts);\n"
		     + "\n"
		     + "      //exponentiate right here (in the non-basic models move to the for loop)\n"
		     + "      double[] exped = new double[numProducts];\n"
		     + "      for(int j : [0..numProducts))\n"
		     + "         exped[j] = exp(ut[j]);\n"
		     + "\n"
		     + "      //Choices includes the choice to not buy anything.\n"
		     + "      int numChoices = numProducts + 1;\n"
		     + "\n"
		     + "      //now normalize\n"
		     + "      double[] expedNorm = new double[numProducts];\n"
		     + "      double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n"
		     + "      for(int j : [0..numProducts))\n"
		     + "         expedNorm[j] = exped[j]/(r*sum);\n"
		     + "\n"
		     + "      int[][] sales = new int[numTimeSteps][numProducts];\n"
		     + "\n"
		     + "      for (int t:[0..numTimeSteps)){\n"
		     + "         // Calculate the number of purchases made.\n"
		     + "         int numSales = reduce(ObsSales[t], 0, (k, l) -> { return k + l; });\n"
		     + "\n"
		     + "         // prior for the distribution of lambda for arrivals. These can be \n"
		     + "         // supplied as a vector if RGBU has some estimates, or just use some priors.\n"
		     + "         public double lambda = gamma(10,10).sample();\n"
		     + "         public int arrivals = numSales + poisson(lambda).sample();\n"
		     + "\n"
		     + "         // for each period t calculate choice probabilities and sales\n"
		     + "         double[] weekly_rates = new double[numChoices];\n"
		     + "         double[] weekly_ut = new double[numChoices];\n"
		     + "\n"
		     + "         for(int j : [0..numProducts)) {\n"
		     + "            if(avail[t][j])\n"
		     + "               weekly_ut[j] = expedNorm[j];\n"
		     + "            else\n"
		     + "               weekly_ut[j] = 0.0;\n"
		     + "         }\n"
		     + "         // Moved v_0 to the end of the array to keep indexing consistent everywhere else in \n"
		     + "         // the model and delayed the assignment of the value 1 to here. None of this is a \n"
		     + "         // sandwood requirement, I just thought it made the model eaiser to follow.\n"
		     + "         weekly_ut[numProducts] = 1.0;\n"
		     + "\n"
		     + "         double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n"
		     + "\n"
		     + "         for(int j : [0..numProducts]) \n"
		     + "            weekly_rates[j] = weekly_ut[j]/denom ;\n"
		     + "\n"
		     + "         int[] weekly_sales = multinomial(weekly_rates, arrivals).sample();\n"
		     + "\n"
		     + "         //getting rid of the no purchase observation (last one in the vector of weekly_sales)\n"
		     + "         int[] observed_weekly_sales = sales[t];\n"
		     + "         for (int j : [0..numProducts))\n"
		     + "            observed_weekly_sales[j] = weekly_sales[j] ;\n"
		     + "      }\n"
		     + "      // assert that generated sales match observed sales\n"
		     + "      sales.observe(ObsSales);\n"
		     + "   }\n"
		     + "}";
	}
}