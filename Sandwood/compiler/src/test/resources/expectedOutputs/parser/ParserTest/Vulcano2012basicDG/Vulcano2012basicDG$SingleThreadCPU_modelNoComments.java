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
	private boolean fixedFlag$sample124 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample87 = false;
	private boolean fixedFlag$sample89 = false;
	private boolean fixedProbFlag$sample124 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample87 = false;
	private boolean fixedProbFlag$sample89 = false;
	private boolean[] guard$sample34multinomial123$global;
	private boolean[][] guard$sample34put101$global;
	private boolean[][] guard$sample34put122$global;
	private boolean[] guard$sample34put61$global;
	private double[] lambda;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$arrivals;
	private double logProbability$exped;
	private double logProbability$expedNorm;
	private double logProbability$lambda;
	private double logProbability$sales;
	private double[] logProbability$sample124;
	private double[] logProbability$sample34;
	private double[] logProbability$sample87;
	private double[] logProbability$sample89;
	private double logProbability$ut;
	private double[] logProbability$var111;
	private double logProbability$var23;
	private double[] logProbability$var77;
	private double[] logProbability$var79;
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
	public final boolean get$fixedFlag$sample124() {
		return fixedFlag$sample124;
	}

	@Override
	public final void set$fixedFlag$sample124(boolean cv$value) {
		fixedFlag$sample124 = cv$value;
		fixedProbFlag$sample124 = (fixedFlag$sample124 && fixedProbFlag$sample124);
	}

	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		fixedFlag$sample34 = cv$value;
		fixedProbFlag$sample34 = (fixedFlag$sample34 && fixedProbFlag$sample34);
		fixedProbFlag$sample124 = (fixedFlag$sample34 && fixedProbFlag$sample124);
	}

	@Override
	public final boolean get$fixedFlag$sample87() {
		return fixedFlag$sample87;
	}

	@Override
	public final void set$fixedFlag$sample87(boolean cv$value) {
		fixedFlag$sample87 = cv$value;
		fixedProbFlag$sample87 = (fixedFlag$sample87 && fixedProbFlag$sample87);
		fixedProbFlag$sample89 = (fixedFlag$sample87 && fixedProbFlag$sample89);
	}

	@Override
	public final boolean get$fixedFlag$sample89() {
		return fixedFlag$sample89;
	}

	@Override
	public final void set$fixedFlag$sample89(boolean cv$value) {
		fixedFlag$sample89 = cv$value;
		fixedProbFlag$sample89 = (fixedFlag$sample89 && fixedProbFlag$sample89);
		fixedProbFlag$sample124 = (fixedFlag$sample89 && fixedProbFlag$sample124);
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

	private final void logProbabilityValue$sample124() {
		if(!fixedProbFlag$sample124) {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
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
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$var111[((t - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample124[((t - 0) / 1)] = cv$sampleProbability;
					boolean cv$guard$sales = false;
					{
						if((0 < numTimeSteps)) {
							for(int j$var116 = 0; j$var116 < avail[0].length; j$var116 += 1) {
								if((0 < numTimeSteps)) {
									if(!cv$guard$sales) {
										cv$guard$sales = true;
										logProbability$sales = (logProbability$sales + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
			}
			logProbability$weekly_sales = (logProbability$weekly_sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample124 = ((fixedFlag$sample124 && fixedFlag$sample34) && fixedFlag$sample89);
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample124[((t - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var111[((t - 0) / 1)] = cv$rvAccumulator;
					boolean cv$guard$sales = false;
					{
						if((0 < numTimeSteps)) {
							for(int j$var116 = 0; j$var116 < avail[0].length; j$var116 += 1) {
								if((0 < numTimeSteps)) {
									if(!cv$guard$sales) {
										cv$guard$sales = true;
										logProbability$sales = (logProbability$sales + cv$sampleValue);
									}
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

	private final void logProbabilityValue$sample34() {
		if(!fixedProbFlag$sample34) {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				double cv$sampleAccumulator = 0.0;
				for(int var27 = 0; var27 < avail[0].length; var27 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						double cv$sampleValue = ut[var27];
						{
							{
								double var21 = 0.0;
								double var22 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var21) / Math.sqrt(var22))) - (0.5 * Math.log(var22))));
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
					logProbability$sample34[((var27 - 0) / 1)] = cv$sampleProbability;
					boolean cv$guard$exped = false;
					boolean cv$guard$expedNorm = false;
					boolean cv$guard$weekly_ut = false;
					boolean cv$guard$weekly_rates = false;
					{
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if(!cv$guard$exped) {
												cv$guard$exped = true;
												logProbability$exped = (logProbability$exped + cv$sampleProbability);
											}
										}
									}
								}
							}
						}
					}
					{
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
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
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
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
									}
								}
							}
						}
					}
					{
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if((0 < numTimeSteps)) {
																										if(avail[t][j$var86]) {
																											if((0 < numTimeSteps)) {
																												if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																													{
																														for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
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
																	}
																}
															}
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if((0 < numTimeSteps)) {
																										if(avail[t][j$var86]) {
																											if((0 < numTimeSteps)) {
																												for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																													if((0 < numTimeSteps)) {
																														if((j$var86 == j$var107)) {
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
																	}
																}
															}
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										if((0 < numTimeSteps)) {
																											if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																												{
																													for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
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
																}
															}
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										if((0 < numTimeSteps)) {
																											for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																												if((0 < numTimeSteps)) {
																													if((j$var86 == j$var107)) {
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
																}
															}
														}
													}
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
				logProbability$var23 = cv$sampleAccumulator;
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample34 = fixedFlag$sample34;
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				double cv$rvAccumulator = 0.0;
				for(int var27 = 0; var27 < avail[0].length; var27 += 1) {
					double cv$sampleValue = logProbability$sample34[((var27 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					boolean cv$guard$exped = false;
					boolean cv$guard$expedNorm = false;
					boolean cv$guard$weekly_ut = false;
					boolean cv$guard$weekly_rates = false;
					{
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if(!cv$guard$exped) {
												cv$guard$exped = true;
												logProbability$exped = (logProbability$exped + cv$sampleValue);
											}
										}
									}
								}
							}
						}
					}
					{
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
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
									}
								}
							}
						}
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
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
									}
								}
							}
						}
					}
					{
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if((0 < numTimeSteps)) {
																										if(avail[t][j$var86]) {
																											if((0 < numTimeSteps)) {
																												if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																													{
																														for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
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
																	}
																}
															}
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if((0 < numTimeSteps)) {
																										if(avail[t][j$var86]) {
																											if((0 < numTimeSteps)) {
																												for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																													if((0 < numTimeSteps)) {
																														if((j$var86 == j$var107)) {
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
																	}
																}
															}
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										if((0 < numTimeSteps)) {
																											if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																												{
																													for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
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
																}
															}
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										if((0 < numTimeSteps)) {
																											for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																												if((0 < numTimeSteps)) {
																													if((j$var86 == j$var107)) {
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
																}
															}
														}
													}
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
				logProbability$var23 = cv$rvAccumulator;
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample87() {
		if(!fixedProbFlag$sample87) {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						double cv$sampleValue = lambda[((t - 0) / 1)];
						{
							{
								double var75 = 10.0;
								double var76 = 10.0;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGamma(cv$sampleValue, var75, var76));
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
					logProbability$var77[((t - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample87[((t - 0) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample87)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample87 = fixedFlag$sample87;
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample87[((t - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var77[((t - 0) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample87)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample89() {
		if(!fixedProbFlag$sample89) {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						int reduceVar$numSales$4 = 0;
						for(int cv$reduction77Index = 0; cv$reduction77Index < ObsSales[t].length; cv$reduction77Index += 1) {
							int k$var69 = reduceVar$numSales$4;
							int l$var70 = ObsSales[t][cv$reduction77Index];
							reduceVar$numSales$4 = (k$var69 + l$var70);
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
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$var79[((t - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample89[((t - 0) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample89)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample89 = (fixedFlag$sample89 && fixedFlag$sample87);
		} else {
			double cv$accumulator = 0.0;
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample89[((t - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var79[((t - 0) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$arrivals = (logProbability$arrivals + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample89)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample34(int var27) {
		double cv$originalValue = ut[var27];
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
					double var28 = cv$proposedValue;
					ut[var27] = cv$currentValue;
					{
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											{
												exped[j$var33] = Math.exp(ut[j$var33]);
											}
										}
									}
								}
							}
						}
					}
					{
						boolean[] guard$sample34put61 = guard$sample34put61$global;
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1)
																guard$sample34put61[((j$var50 - 0) / 1)] = false;
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50))
																guard$sample34put61[((j$var50 - 0) / 1)] = false;
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																if(!guard$sample34put61[((j$var50 - 0) / 1)]) {
																	guard$sample34put61[((j$var50 - 0) / 1)] = true;
																	{
																		double reduceVar$sum$0 = 0.0;
																		for(int cv$reduction276Index = 0; cv$reduction276Index < avail[0].length; cv$reduction276Index += 1) {
																			double k$var44 = reduceVar$sum$0;
																			double l$var45 = exped[cv$reduction276Index];
																			reduceVar$sum$0 = (k$var44 + l$var45);
																		}
																		expedNorm[j$var50] = (exped[j$var50] / (r * reduceVar$sum$0));
																	}
																}
															}
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if(!guard$sample34put61[((j$var50 - 0) / 1)]) {
																	guard$sample34put61[((j$var50 - 0) / 1)] = true;
																	{
																		double reduceVar$sum$1 = 0.0;
																		for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1) {
																			double k$var44 = reduceVar$sum$1;
																			double l$var45 = exped[cv$reduction50Index];
																			reduceVar$sum$1 = (k$var44 + l$var45);
																		}
																		expedNorm[j$var50] = (exped[j$var50] / (r * reduceVar$sum$1));
																	}
																}
															}
														}
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
						boolean[][] guard$sample34put101 = guard$sample34put101$global;
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86))
																									guard$sample34put101[((t - 0) / 1)][((j$var86 - 0) / 1)] = false;
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86))
																								guard$sample34put101[((t - 0) / 1)][((j$var86 - 0) / 1)] = false;
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if(!guard$sample34put101[((t - 0) / 1)][((j$var86 - 0) / 1)]) {
																										guard$sample34put101[((t - 0) / 1)][((j$var86 - 0) / 1)] = true;
																										{
																											weekly_ut[((t - 0) / 1)][j$var86] = expedNorm[j$var86];
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if(!guard$sample34put101[((t - 0) / 1)][((j$var86 - 0) / 1)]) {
																									guard$sample34put101[((t - 0) / 1)][((j$var86 - 0) / 1)] = true;
																									{
																										weekly_ut[((t - 0) / 1)][j$var86] = expedNorm[j$var86];
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
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
						boolean[][] guard$sample34put122 = guard$sample34put122$global;
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if((0 < numTimeSteps)) {
																										if(avail[t][j$var86]) {
																											if((0 < numTimeSteps)) {
																												if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																													{
																														for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1)
																															guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)] = false;
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if((0 < numTimeSteps)) {
																										if(avail[t][j$var86]) {
																											if((0 < numTimeSteps)) {
																												for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																													if((0 < numTimeSteps)) {
																														if((j$var86 == j$var107))
																															guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)] = false;
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										if((0 < numTimeSteps)) {
																											if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																												{
																													for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1)
																														guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)] = false;
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										if((0 < numTimeSteps)) {
																											for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																												if((0 < numTimeSteps)) {
																													if((j$var86 == j$var107))
																														guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)] = false;
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if((0 < numTimeSteps)) {
																										if(avail[t][j$var86]) {
																											if((0 < numTimeSteps)) {
																												if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																													{
																														for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																															if(!guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)]) {
																																guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)] = true;
																																{
																																	double reduceVar$denom$0 = 0.0;
																																	for(int cv$reduction711Index = 0; cv$reduction711Index < (avail[0].length + 1); cv$reduction711Index += 1) {
																																		double k$var99 = reduceVar$denom$0;
																																		double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction711Index];
																																		reduceVar$denom$0 = (k$var99 + l$var100);
																																	}
																																	weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if((0 < numTimeSteps)) {
																										if(avail[t][j$var86]) {
																											if((0 < numTimeSteps)) {
																												for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																													if((0 < numTimeSteps)) {
																														if((j$var86 == j$var107)) {
																															if(!guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)]) {
																																guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)] = true;
																																{
																																	double reduceVar$denom$1 = 0.0;
																																	for(int cv$reduction110Index = 0; cv$reduction110Index < (avail[0].length + 1); cv$reduction110Index += 1) {
																																		double k$var99 = reduceVar$denom$1;
																																		double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction110Index];
																																		reduceVar$denom$1 = (k$var99 + l$var100);
																																	}
																																	weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$1);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										if((0 < numTimeSteps)) {
																											if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																												{
																													for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																														if(!guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)]) {
																															guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)] = true;
																															{
																																double reduceVar$denom$2 = 0.0;
																																for(int cv$reduction811Index = 0; cv$reduction811Index < (avail[0].length + 1); cv$reduction811Index += 1) {
																																	double k$var99 = reduceVar$denom$2;
																																	double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction811Index];
																																	reduceVar$denom$2 = (k$var99 + l$var100);
																																}
																																weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$2);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										if((0 < numTimeSteps)) {
																											for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																												if((0 < numTimeSteps)) {
																													if((j$var86 == j$var107)) {
																														if(!guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)]) {
																															guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)] = true;
																															{
																																double reduceVar$denom$3 = 0.0;
																																for(int cv$reduction110Index = 0; cv$reduction110Index < (avail[0].length + 1); cv$reduction110Index += 1) {
																																	double k$var99 = reduceVar$denom$3;
																																	double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction110Index];
																																	reduceVar$denom$3 = (k$var99 + l$var100);
																																}
																																weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$3);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
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
				double cv$temp$0$var21;
				{
					cv$temp$0$var21 = 0.0;
				}
				double cv$temp$1$var22;
				{
					cv$temp$1$var22 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var21) / Math.sqrt(cv$temp$1$var22))) - (0.5 * Math.log(cv$temp$1$var22))));
				{
					{
						boolean[] guard$sample34multinomial123 = guard$sample34multinomial123$global;
						if((0 < numTimeSteps)) {
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if((0 < numTimeSteps)) {
																										if(avail[t][j$var86]) {
																											if((0 < numTimeSteps)) {
																												if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																													{
																														guard$sample34multinomial123[((t - 0) / 1)] = false;
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if((0 < numTimeSteps)) {
																for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																	if((0 < numTimeSteps)) {
																		if((0 < numTimeSteps)) {
																			for(int t = 0; t < numTimeSteps; t += 1) {
																				if((0 < numTimeSteps)) {
																					for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((j$var50 == j$var86)) {
																									if((0 < numTimeSteps)) {
																										if(avail[t][j$var86]) {
																											if((0 < numTimeSteps)) {
																												for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																													if((0 < numTimeSteps)) {
																														if((j$var86 == j$var107))
																															guard$sample34multinomial123[((t - 0) / 1)] = false;
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										if((0 < numTimeSteps)) {
																											if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																												{
																													guard$sample34multinomial123[((t - 0) / 1)] = false;
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
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
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										if((0 < numTimeSteps)) {
																											for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																												if((0 < numTimeSteps)) {
																													if((j$var86 == j$var107))
																														guard$sample34multinomial123[((t - 0) / 1)] = false;
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
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
							double traceTempVariable$var34$22_1 = cv$currentValue;
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												double traceTempVariable$k$22_3 = Math.exp(traceTempVariable$var34$22_1);
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if(((0 < exped.length) && (0 < avail[0].length))) {
																double reduceVar$sum$2 = 0.0;
																for(int cv$reduction1107Index = 0; cv$reduction1107Index < j$var33; cv$reduction1107Index += 1) {
																	double k$var44 = reduceVar$sum$2;
																	double l$var45 = exped[cv$reduction1107Index];
																	reduceVar$sum$2 = (k$var44 + l$var45);
																}
																for(int cv$reduction1107Index = (j$var33 + 1); cv$reduction1107Index < avail[0].length; cv$reduction1107Index += 1) {
																	double k$var44 = reduceVar$sum$2;
																	double l$var45 = exped[cv$reduction1107Index];
																	reduceVar$sum$2 = (k$var44 + l$var45);
																}
																double cv$reduced50 = reduceVar$sum$2;
																reduceVar$sum$2 = (traceTempVariable$k$22_3 + cv$reduced50);
																double traceTempVariable$sum$22_4 = reduceVar$sum$2;
																if((0 < numTimeSteps)) {
																	for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																		if((0 < numTimeSteps)) {
																			double traceTempVariable$var89$22_6 = (exped[j$var50] / (r * traceTempVariable$sum$22_4));
																			if((0 < numTimeSteps)) {
																				for(int t = 0; t < numTimeSteps; t += 1) {
																					if((0 < numTimeSteps)) {
																						for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																							if((0 < numTimeSteps)) {
																								if(avail[t][j$var86]) {
																									if((j$var50 == j$var86)) {
																										if((0 < numTimeSteps)) {
																											if(avail[t][j$var86]) {
																												double traceTempVariable$k$22_9 = traceTempVariable$var89$22_6;
																												if((0 < numTimeSteps)) {
																													if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																														{
																															if(((0 < weekly_ut[((t - 0) / 1)].length) && (0 < (avail[0].length + 1)))) {
																																double reduceVar$denom$4 = 0.0;
																																for(int cv$reduction1144Index = 0; cv$reduction1144Index < j$var86; cv$reduction1144Index += 1) {
																																	double k$var99 = reduceVar$denom$4;
																																	double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction1144Index];
																																	reduceVar$denom$4 = (k$var99 + l$var100);
																																}
																																for(int cv$reduction1144Index = (j$var86 + 1); cv$reduction1144Index < (avail[0].length + 1); cv$reduction1144Index += 1) {
																																	double k$var99 = reduceVar$denom$4;
																																	double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction1144Index];
																																	reduceVar$denom$4 = (k$var99 + l$var100);
																																}
																																double cv$reduced110 = reduceVar$denom$4;
																																reduceVar$denom$4 = (traceTempVariable$k$22_9 + cv$reduced110);
																																double traceTempVariable$denom$22_10 = reduceVar$denom$4;
																																if(!guard$sample34multinomial123[((t - 0) / 1)]) {
																																	guard$sample34multinomial123[((t - 0) / 1)] = true;
																																	{
																																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																																		{
																																			{
																																				{
																																					{
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
																}
															}
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
							double traceTempVariable$var34$23_1 = cv$currentValue;
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												double traceTempVariable$k$23_3 = Math.exp(traceTempVariable$var34$23_1);
												if((0 < numTimeSteps)) {
													if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
														{
															if(((0 < exped.length) && (0 < avail[0].length))) {
																double reduceVar$sum$3 = 0.0;
																for(int cv$reduction1179Index = 0; cv$reduction1179Index < j$var33; cv$reduction1179Index += 1) {
																	double k$var44 = reduceVar$sum$3;
																	double l$var45 = exped[cv$reduction1179Index];
																	reduceVar$sum$3 = (k$var44 + l$var45);
																}
																for(int cv$reduction1179Index = (j$var33 + 1); cv$reduction1179Index < avail[0].length; cv$reduction1179Index += 1) {
																	double k$var44 = reduceVar$sum$3;
																	double l$var45 = exped[cv$reduction1179Index];
																	reduceVar$sum$3 = (k$var44 + l$var45);
																}
																double cv$reduced50 = reduceVar$sum$3;
																reduceVar$sum$3 = (traceTempVariable$k$23_3 + cv$reduced50);
																double traceTempVariable$sum$23_4 = reduceVar$sum$3;
																if((0 < numTimeSteps)) {
																	for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
																		if((0 < numTimeSteps)) {
																			double traceTempVariable$var89$23_6 = (exped[j$var50] / (r * traceTempVariable$sum$23_4));
																			if((0 < numTimeSteps)) {
																				for(int t = 0; t < numTimeSteps; t += 1) {
																					if((0 < numTimeSteps)) {
																						for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																							if((0 < numTimeSteps)) {
																								if(avail[t][j$var86]) {
																									if((j$var50 == j$var86)) {
																										if((0 < numTimeSteps)) {
																											if(avail[t][j$var86]) {
																												double traceTempVariable$var108$23_9 = traceTempVariable$var89$23_6;
																												if((0 < numTimeSteps)) {
																													for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																														if((0 < numTimeSteps)) {
																															if((j$var86 == j$var107)) {
																																if(!guard$sample34multinomial123[((t - 0) / 1)]) {
																																	guard$sample34multinomial123[((t - 0) / 1)] = true;
																																	{
																																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																																		{
																																			{
																																				{
																																					{
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
																}
															}
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
							double traceTempVariable$var34$24_1 = cv$currentValue;
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												double traceTempVariable$var51$24_3 = Math.exp(traceTempVariable$var34$24_1);
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	double reduceVar$sum$4 = 0.0;
																	for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1) {
																		double k$var44 = reduceVar$sum$4;
																		double l$var45 = exped[cv$reduction50Index];
																		reduceVar$sum$4 = (k$var44 + l$var45);
																	}
																	double traceTempVariable$var89$24_5 = (traceTempVariable$var51$24_3 / (r * reduceVar$sum$4));
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										double traceTempVariable$k$24_8 = traceTempVariable$var89$24_5;
																										if((0 < numTimeSteps)) {
																											if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																												{
																													if(((0 < weekly_ut[((t - 0) / 1)].length) && (0 < (avail[0].length + 1)))) {
																														double reduceVar$denom$5 = 0.0;
																														for(int cv$reduction1267Index = 0; cv$reduction1267Index < j$var86; cv$reduction1267Index += 1) {
																															double k$var99 = reduceVar$denom$5;
																															double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction1267Index];
																															reduceVar$denom$5 = (k$var99 + l$var100);
																														}
																														for(int cv$reduction1267Index = (j$var86 + 1); cv$reduction1267Index < (avail[0].length + 1); cv$reduction1267Index += 1) {
																															double k$var99 = reduceVar$denom$5;
																															double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction1267Index];
																															reduceVar$denom$5 = (k$var99 + l$var100);
																														}
																														double cv$reduced110 = reduceVar$denom$5;
																														reduceVar$denom$5 = (traceTempVariable$k$24_8 + cv$reduced110);
																														double traceTempVariable$denom$24_9 = reduceVar$denom$5;
																														if(!guard$sample34multinomial123[((t - 0) / 1)]) {
																															guard$sample34multinomial123[((t - 0) / 1)] = true;
																															{
																																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																																{
																																	{
																																		{
																																			{
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
							double traceTempVariable$var34$25_1 = cv$currentValue;
							if((0 < numTimeSteps)) {
								for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
									if((0 < numTimeSteps)) {
										if((var27 == j$var33)) {
											if((0 < numTimeSteps)) {
												double traceTempVariable$var51$25_3 = Math.exp(traceTempVariable$var34$25_1);
												if((0 < numTimeSteps)) {
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if((0 < numTimeSteps)) {
															if((j$var33 == j$var50)) {
																if((0 < numTimeSteps)) {
																	double reduceVar$sum$5 = 0.0;
																	for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1) {
																		double k$var44 = reduceVar$sum$5;
																		double l$var45 = exped[cv$reduction50Index];
																		reduceVar$sum$5 = (k$var44 + l$var45);
																	}
																	double traceTempVariable$var89$25_5 = (traceTempVariable$var51$25_3 / (r * reduceVar$sum$5));
																	if((0 < numTimeSteps)) {
																		for(int t = 0; t < numTimeSteps; t += 1) {
																			if((0 < numTimeSteps)) {
																				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																					if((0 < numTimeSteps)) {
																						if(avail[t][j$var86]) {
																							if((j$var50 == j$var86)) {
																								if((0 < numTimeSteps)) {
																									if(avail[t][j$var86]) {
																										double traceTempVariable$var108$25_8 = traceTempVariable$var89$25_5;
																										if((0 < numTimeSteps)) {
																											for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																												if((0 < numTimeSteps)) {
																													if((j$var86 == j$var107)) {
																														if(!guard$sample34multinomial123[((t - 0) / 1)]) {
																															guard$sample34multinomial123[((t - 0) / 1)] = true;
																															{
																																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																																{
																																	{
																																		{
																																			{
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
														}
													}
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
			double var28 = cv$originalValue;
			ut[var27] = var28;
			{
				if((0 < numTimeSteps)) {
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									{
										exped[j$var33] = Math.exp(ut[j$var33]);
									}
								}
							}
						}
					}
				}
			}
			{
				boolean[] guard$sample34put61 = guard$sample34put61$global;
				if((0 < numTimeSteps)) {
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
												{
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1)
														guard$sample34put61[((j$var50 - 0) / 1)] = false;
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
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
												if((0 < numTimeSteps)) {
													if((j$var33 == j$var50))
														guard$sample34put61[((j$var50 - 0) / 1)] = false;
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
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
												{
													for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
														if(!guard$sample34put61[((j$var50 - 0) / 1)]) {
															guard$sample34put61[((j$var50 - 0) / 1)] = true;
															{
																double reduceVar$sum$6 = 0.0;
																for(int cv$reduction1449Index = 0; cv$reduction1449Index < avail[0].length; cv$reduction1449Index += 1) {
																	double k$var44 = reduceVar$sum$6;
																	double l$var45 = exped[cv$reduction1449Index];
																	reduceVar$sum$6 = (k$var44 + l$var45);
																}
																expedNorm[j$var50] = (exped[j$var50] / (r * reduceVar$sum$6));
															}
														}
													}
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
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
												if((0 < numTimeSteps)) {
													if((j$var33 == j$var50)) {
														if(!guard$sample34put61[((j$var50 - 0) / 1)]) {
															guard$sample34put61[((j$var50 - 0) / 1)] = true;
															{
																double reduceVar$sum$7 = 0.0;
																for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1) {
																	double k$var44 = reduceVar$sum$7;
																	double l$var45 = exped[cv$reduction50Index];
																	reduceVar$sum$7 = (k$var44 + l$var45);
																}
																expedNorm[j$var50] = (exped[j$var50] / (r * reduceVar$sum$7));
															}
														}
													}
												}
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
				boolean[][] guard$sample34put101 = guard$sample34put101$global;
				if((0 < numTimeSteps)) {
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
												{
													if((0 < numTimeSteps)) {
														for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
															if((0 < numTimeSteps)) {
																if((0 < numTimeSteps)) {
																	for(int t = 0; t < numTimeSteps; t += 1) {
																		if((0 < numTimeSteps)) {
																			for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																				if((0 < numTimeSteps)) {
																					if(avail[t][j$var86]) {
																						if((j$var50 == j$var86))
																							guard$sample34put101[((t - 0) / 1)][((j$var86 - 0) / 1)] = false;
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
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
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
												if((0 < numTimeSteps)) {
													if((j$var33 == j$var50)) {
														if((0 < numTimeSteps)) {
															if((0 < numTimeSteps)) {
																for(int t = 0; t < numTimeSteps; t += 1) {
																	if((0 < numTimeSteps)) {
																		for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																			if((0 < numTimeSteps)) {
																				if(avail[t][j$var86]) {
																					if((j$var50 == j$var86))
																						guard$sample34put101[((t - 0) / 1)][((j$var86 - 0) / 1)] = false;
																				}
																			}
																		}
																	}
																}
															}
														}
													}
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
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
												{
													if((0 < numTimeSteps)) {
														for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
															if((0 < numTimeSteps)) {
																if((0 < numTimeSteps)) {
																	for(int t = 0; t < numTimeSteps; t += 1) {
																		if((0 < numTimeSteps)) {
																			for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																				if((0 < numTimeSteps)) {
																					if(avail[t][j$var86]) {
																						if((j$var50 == j$var86)) {
																							if(!guard$sample34put101[((t - 0) / 1)][((j$var86 - 0) / 1)]) {
																								guard$sample34put101[((t - 0) / 1)][((j$var86 - 0) / 1)] = true;
																								{
																									weekly_ut[((t - 0) / 1)][j$var86] = expedNorm[j$var86];
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
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
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
												if((0 < numTimeSteps)) {
													if((j$var33 == j$var50)) {
														if((0 < numTimeSteps)) {
															if((0 < numTimeSteps)) {
																for(int t = 0; t < numTimeSteps; t += 1) {
																	if((0 < numTimeSteps)) {
																		for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																			if((0 < numTimeSteps)) {
																				if(avail[t][j$var86]) {
																					if((j$var50 == j$var86)) {
																						if(!guard$sample34put101[((t - 0) / 1)][((j$var86 - 0) / 1)]) {
																							guard$sample34put101[((t - 0) / 1)][((j$var86 - 0) / 1)] = true;
																							{
																								weekly_ut[((t - 0) / 1)][j$var86] = expedNorm[j$var86];
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
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
				boolean[][] guard$sample34put122 = guard$sample34put122$global;
				if((0 < numTimeSteps)) {
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
												{
													if((0 < numTimeSteps)) {
														for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
															if((0 < numTimeSteps)) {
																if((0 < numTimeSteps)) {
																	for(int t = 0; t < numTimeSteps; t += 1) {
																		if((0 < numTimeSteps)) {
																			for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																				if((0 < numTimeSteps)) {
																					if(avail[t][j$var86]) {
																						if((j$var50 == j$var86)) {
																							if((0 < numTimeSteps)) {
																								if(avail[t][j$var86]) {
																									if((0 < numTimeSteps)) {
																										if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																											{
																												for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1)
																													guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)] = false;
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
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
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
												{
													if((0 < numTimeSteps)) {
														for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
															if((0 < numTimeSteps)) {
																if((0 < numTimeSteps)) {
																	for(int t = 0; t < numTimeSteps; t += 1) {
																		if((0 < numTimeSteps)) {
																			for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																				if((0 < numTimeSteps)) {
																					if(avail[t][j$var86]) {
																						if((j$var50 == j$var86)) {
																							if((0 < numTimeSteps)) {
																								if(avail[t][j$var86]) {
																									if((0 < numTimeSteps)) {
																										for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																											if((0 < numTimeSteps)) {
																												if((j$var86 == j$var107))
																													guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)] = false;
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
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
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
												if((0 < numTimeSteps)) {
													if((j$var33 == j$var50)) {
														if((0 < numTimeSteps)) {
															if((0 < numTimeSteps)) {
																for(int t = 0; t < numTimeSteps; t += 1) {
																	if((0 < numTimeSteps)) {
																		for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																			if((0 < numTimeSteps)) {
																				if(avail[t][j$var86]) {
																					if((j$var50 == j$var86)) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((0 < numTimeSteps)) {
																									if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																										{
																											for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1)
																												guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)] = false;
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
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
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
												if((0 < numTimeSteps)) {
													if((j$var33 == j$var50)) {
														if((0 < numTimeSteps)) {
															if((0 < numTimeSteps)) {
																for(int t = 0; t < numTimeSteps; t += 1) {
																	if((0 < numTimeSteps)) {
																		for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																			if((0 < numTimeSteps)) {
																				if(avail[t][j$var86]) {
																					if((j$var50 == j$var86)) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((0 < numTimeSteps)) {
																									for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																										if((0 < numTimeSteps)) {
																											if((j$var86 == j$var107))
																												guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)] = false;
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
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
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
												{
													if((0 < numTimeSteps)) {
														for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
															if((0 < numTimeSteps)) {
																if((0 < numTimeSteps)) {
																	for(int t = 0; t < numTimeSteps; t += 1) {
																		if((0 < numTimeSteps)) {
																			for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																				if((0 < numTimeSteps)) {
																					if(avail[t][j$var86]) {
																						if((j$var50 == j$var86)) {
																							if((0 < numTimeSteps)) {
																								if(avail[t][j$var86]) {
																									if((0 < numTimeSteps)) {
																										if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																											{
																												for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																													if(!guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)]) {
																														guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)] = true;
																														{
																															double reduceVar$denom$6 = 0.0;
																															for(int cv$reduction1884Index = 0; cv$reduction1884Index < (avail[0].length + 1); cv$reduction1884Index += 1) {
																																double k$var99 = reduceVar$denom$6;
																																double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction1884Index];
																																reduceVar$denom$6 = (k$var99 + l$var100);
																															}
																															weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$6);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
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
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											if(((0 <= j$var33) && (j$var33 < avail[0].length))) {
												{
													if((0 < numTimeSteps)) {
														for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
															if((0 < numTimeSteps)) {
																if((0 < numTimeSteps)) {
																	for(int t = 0; t < numTimeSteps; t += 1) {
																		if((0 < numTimeSteps)) {
																			for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																				if((0 < numTimeSteps)) {
																					if(avail[t][j$var86]) {
																						if((j$var50 == j$var86)) {
																							if((0 < numTimeSteps)) {
																								if(avail[t][j$var86]) {
																									if((0 < numTimeSteps)) {
																										for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																											if((0 < numTimeSteps)) {
																												if((j$var86 == j$var107)) {
																													if(!guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)]) {
																														guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)] = true;
																														{
																															double reduceVar$denom$7 = 0.0;
																															for(int cv$reduction110Index = 0; cv$reduction110Index < (avail[0].length + 1); cv$reduction110Index += 1) {
																																double k$var99 = reduceVar$denom$7;
																																double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction110Index];
																																reduceVar$denom$7 = (k$var99 + l$var100);
																															}
																															weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$7);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
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
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
												if((0 < numTimeSteps)) {
													if((j$var33 == j$var50)) {
														if((0 < numTimeSteps)) {
															if((0 < numTimeSteps)) {
																for(int t = 0; t < numTimeSteps; t += 1) {
																	if((0 < numTimeSteps)) {
																		for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																			if((0 < numTimeSteps)) {
																				if(avail[t][j$var86]) {
																					if((j$var50 == j$var86)) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((0 < numTimeSteps)) {
																									if(((0 <= j$var86) && (j$var86 < (avail[0].length + 1)))) {
																										{
																											for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																												if(!guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)]) {
																													guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)] = true;
																													{
																														double reduceVar$denom$8 = 0.0;
																														for(int cv$reduction1984Index = 0; cv$reduction1984Index < (avail[0].length + 1); cv$reduction1984Index += 1) {
																															double k$var99 = reduceVar$denom$8;
																															double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction1984Index];
																															reduceVar$denom$8 = (k$var99 + l$var100);
																														}
																														weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$8);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
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
					if((0 < numTimeSteps)) {
						for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
							if((0 < numTimeSteps)) {
								if((var27 == j$var33)) {
									if((0 < numTimeSteps)) {
										if((0 < numTimeSteps)) {
											for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
												if((0 < numTimeSteps)) {
													if((j$var33 == j$var50)) {
														if((0 < numTimeSteps)) {
															if((0 < numTimeSteps)) {
																for(int t = 0; t < numTimeSteps; t += 1) {
																	if((0 < numTimeSteps)) {
																		for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
																			if((0 < numTimeSteps)) {
																				if(avail[t][j$var86]) {
																					if((j$var50 == j$var86)) {
																						if((0 < numTimeSteps)) {
																							if(avail[t][j$var86]) {
																								if((0 < numTimeSteps)) {
																									for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
																										if((0 < numTimeSteps)) {
																											if((j$var86 == j$var107)) {
																												if(!guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)]) {
																													guard$sample34put122[((t - 0) / 1)][((j$var107 - 0) / 1)] = true;
																													{
																														double reduceVar$denom$9 = 0.0;
																														for(int cv$reduction110Index = 0; cv$reduction110Index < (avail[0].length + 1); cv$reduction110Index += 1) {
																															double k$var99 = reduceVar$denom$9;
																															double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction110Index];
																															reduceVar$denom$9 = (k$var99 + l$var100);
																														}
																														weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$9);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void sample87(int t) {
		double cv$sum = 0.0;
		int cv$count = 0;
		{
			{
				{
					{
						{
							{
								{
									{
										int reduceVar$numSales$0 = 0;
										for(int cv$reduction77Index = 0; cv$reduction77Index < ObsSales[t].length; cv$reduction77Index += 1) {
											int k$var69 = reduceVar$numSales$0;
											int l$var70 = ObsSales[t][cv$reduction77Index];
											reduceVar$numSales$0 = (k$var69 + l$var70);
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
		lambda[((t - 0) / 1)] = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, cv$sum, cv$count);
	}

	private final void sample89(int t) {
		int reduceVar$numSales$1 = 0;
		for(int cv$reduction77Index = 0; cv$reduction77Index < ObsSales[t].length; cv$reduction77Index += 1) {
			int k$var69 = reduceVar$numSales$1;
			int l$var70 = ObsSales[t][cv$reduction77Index];
			reduceVar$numSales$1 = (k$var69 + l$var70);
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
					int var80 = cv$proposedValue;
					int reduceVar$numSales$2 = 0;
					for(int cv$reduction77Index = 0; cv$reduction77Index < ObsSales[t].length; cv$reduction77Index += 1) {
						int k$var69 = reduceVar$numSales$2;
						int l$var70 = ObsSales[t][cv$reduction77Index];
						reduceVar$numSales$2 = (k$var69 + l$var70);
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
						int traceTempVariable$arrivals$1_1 = arrivals[((t - 0) / 1)];
						{
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							{
								{
									{
										{
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
			int var80 = cv$originalValue;
			int reduceVar$numSales$3 = 0;
			for(int cv$reduction77Index = 0; cv$reduction77Index < ObsSales[t].length; cv$reduction77Index += 1) {
				int k$var69 = reduceVar$numSales$3;
				int l$var70 = ObsSales[t][cv$reduction77Index];
				reduceVar$numSales$3 = (k$var69 + l$var70);
			}
			arrivals[((t - 0) / 1)] = (reduceVar$numSales$3 + var80);
		}
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max_j$var50 = 0;
			if((0 < avail.length))
				cv$max_j$var50 = Math.max(cv$max_j$var50, ((avail[0].length - 0) / 1));
			guard$sample34put61$global = new boolean[cv$max_j$var50];
		}
		{
			int cv$max_t = 0;
			int cv$max_j$var86 = 0;
			if((0 < avail.length)) {
				for(int t = 0; t < avail.length; t += 1)
					cv$max_j$var86 = Math.max(cv$max_j$var86, ((avail[0].length - 0) / 1));
				cv$max_t = Math.max(cv$max_t, ((avail.length - 0) / 1));
			}
			guard$sample34put101$global = new boolean[cv$max_t][cv$max_j$var86];
		}
		{
			int cv$max_t = 0;
			int cv$max_j$var107 = 0;
			if((0 < avail.length)) {
				for(int t = 0; t < avail.length; t += 1)
					cv$max_j$var107 = Math.max(cv$max_j$var107, (((avail[0].length + 1) - 0) / 1));
				cv$max_t = Math.max(cv$max_t, ((avail.length - 0) / 1));
			}
			guard$sample34put122$global = new boolean[cv$max_t][cv$max_j$var107];
		}
		{
			int cv$max_t = 0;
			if((0 < avail.length))
				cv$max_t = Math.max(cv$max_t, ((avail.length - 0) / 1));
			guard$sample34multinomial123$global = new boolean[cv$max_t];
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
				for(int var58 = 0; var58 < avail.length; var58 += 1)
					sales[var58] = new int[avail[0].length];
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
			logProbability$sample34 = new double[((((avail[0].length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var77 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample87 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var79 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample89 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var111 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample124 = new double[((((avail.length - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if((0 < numTimeSteps)) {
			for(int var27 = 0; var27 < avail[0].length; var27 += 1) {
				if(!fixedFlag$sample34)
					ut[var27] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
			}
			for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
				if(!fixedFlag$sample34)
					exped[j$var33] = Math.exp(ut[j$var33]);
			}
			double reduceVar$sum$8 = 0.0;
			for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1) {
				double k$var44 = reduceVar$sum$8;
				double l$var45 = exped[cv$reduction50Index];
				if(!fixedFlag$sample34)
					reduceVar$sum$8 = (k$var44 + l$var45);
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				if(!fixedFlag$sample34)
					expedNorm[j$var50] = (exped[j$var50] / (r * reduceVar$sum$8));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(!fixedFlag$sample87)
					lambda[((t - 0) / 1)] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
				int reduceVar$numSales$5 = 0;
				for(int cv$reduction77Index = 0; cv$reduction77Index < ObsSales[t].length; cv$reduction77Index += 1) {
					int k$var69 = reduceVar$numSales$5;
					int l$var70 = ObsSales[t][cv$reduction77Index];
					if(!fixedFlag$sample89)
						reduceVar$numSales$5 = (k$var69 + l$var70);
				}
				if(!fixedFlag$sample89)
					arrivals[((t - 0) / 1)] = (reduceVar$numSales$5 + DistributionSampling.samplePoisson(RNG$, lambda[((t - 0) / 1)]));
				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
					if(avail[t][j$var86]) {
						if(!fixedFlag$sample34)
							weekly_ut[((t - 0) / 1)][j$var86] = expedNorm[j$var86];
					} else
						weekly_ut[((t - 0) / 1)][j$var86] = 0.0;
				}
				weekly_ut[((t - 0) / 1)][avail[0].length] = 1.0;
				double reduceVar$denom$10 = 0.0;
				for(int cv$reduction110Index = 0; cv$reduction110Index < (avail[0].length + 1); cv$reduction110Index += 1) {
					double k$var99 = reduceVar$denom$10;
					double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction110Index];
					if(!fixedFlag$sample34)
						reduceVar$denom$10 = (k$var99 + l$var100);
				}
				for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
					if(!fixedFlag$sample34)
						weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$10);
				}
				if(!fixedFlag$sample124)
					DistributionSampling.sampleMultinomial(RNG$, weekly_rates[((t - 0) / 1)], arrivals[((t - 0) / 1)], weekly_sales[((t - 0) / 1)]);
				int[] observed_weekly_sales = sales[t];
				for(int j$var116 = 0; j$var116 < avail[0].length; j$var116 += 1) {
					if(!fixedFlag$sample124)
						observed_weekly_sales[j$var116] = weekly_sales[((t - 0) / 1)][j$var116];
				}
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if((0 < numTimeSteps)) {
			for(int var27 = 0; var27 < avail[0].length; var27 += 1) {
				if(!fixedFlag$sample34)
					ut[var27] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
			}
			for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
				if(!fixedFlag$sample34)
					exped[j$var33] = Math.exp(ut[j$var33]);
			}
			double reduceVar$sum$10 = 0.0;
			for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1) {
				double k$var44 = reduceVar$sum$10;
				double l$var45 = exped[cv$reduction50Index];
				if(!fixedFlag$sample34)
					reduceVar$sum$10 = (k$var44 + l$var45);
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				if(!fixedFlag$sample34)
					expedNorm[j$var50] = (exped[j$var50] / (r * reduceVar$sum$10));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(!fixedFlag$sample87)
					lambda[((t - 0) / 1)] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
				int reduceVar$numSales$7 = 0;
				for(int cv$reduction77Index = 0; cv$reduction77Index < ObsSales[t].length; cv$reduction77Index += 1) {
					int k$var69 = reduceVar$numSales$7;
					int l$var70 = ObsSales[t][cv$reduction77Index];
					if(!fixedFlag$sample89)
						reduceVar$numSales$7 = (k$var69 + l$var70);
				}
				if(!fixedFlag$sample89)
					arrivals[((t - 0) / 1)] = (reduceVar$numSales$7 + DistributionSampling.samplePoisson(RNG$, lambda[((t - 0) / 1)]));
				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
					if(avail[t][j$var86]) {
						if(!fixedFlag$sample34)
							weekly_ut[((t - 0) / 1)][j$var86] = expedNorm[j$var86];
					} else
						weekly_ut[((t - 0) / 1)][j$var86] = 0.0;
				}
				weekly_ut[((t - 0) / 1)][avail[0].length] = 1.0;
				double reduceVar$denom$12 = 0.0;
				for(int cv$reduction110Index = 0; cv$reduction110Index < (avail[0].length + 1); cv$reduction110Index += 1) {
					double k$var99 = reduceVar$denom$12;
					double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction110Index];
					if(!fixedFlag$sample34)
						reduceVar$denom$12 = (k$var99 + l$var100);
				}
				for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
					if(!fixedFlag$sample34)
						weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$12);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if((0 < numTimeSteps)) {
			for(int var27 = 0; var27 < avail[0].length; var27 += 1) {
				if(!fixedFlag$sample34)
					ut[var27] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
			}
			for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
				if(!fixedFlag$sample34)
					exped[j$var33] = Math.exp(ut[j$var33]);
			}
			double reduceVar$sum$9 = 0.0;
			for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1) {
				double k$var44 = reduceVar$sum$9;
				double l$var45 = exped[cv$reduction50Index];
				if(!fixedFlag$sample34)
					reduceVar$sum$9 = (k$var44 + l$var45);
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				if(!fixedFlag$sample34)
					expedNorm[j$var50] = (exped[j$var50] / (r * reduceVar$sum$9));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(!fixedFlag$sample87)
					lambda[((t - 0) / 1)] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
				int reduceVar$numSales$6 = 0;
				for(int cv$reduction77Index = 0; cv$reduction77Index < ObsSales[t].length; cv$reduction77Index += 1) {
					int k$var69 = reduceVar$numSales$6;
					int l$var70 = ObsSales[t][cv$reduction77Index];
					if(!fixedFlag$sample89)
						reduceVar$numSales$6 = (k$var69 + l$var70);
				}
				if(!fixedFlag$sample89)
					arrivals[((t - 0) / 1)] = (reduceVar$numSales$6 + DistributionSampling.samplePoisson(RNG$, lambda[((t - 0) / 1)]));
				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
					if(avail[t][j$var86]) {
						if(!fixedFlag$sample34)
							weekly_ut[((t - 0) / 1)][j$var86] = expedNorm[j$var86];
					} else
						weekly_ut[((t - 0) / 1)][j$var86] = 0.0;
				}
				weekly_ut[((t - 0) / 1)][avail[0].length] = 1.0;
				double reduceVar$denom$11 = 0.0;
				for(int cv$reduction110Index = 0; cv$reduction110Index < (avail[0].length + 1); cv$reduction110Index += 1) {
					double k$var99 = reduceVar$denom$11;
					double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction110Index];
					if(!fixedFlag$sample34)
						reduceVar$denom$11 = (k$var99 + l$var100);
				}
				for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
					if(!fixedFlag$sample34)
						weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$11);
				}
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if((0 < numTimeSteps)) {
				for(int var27 = 0; var27 < avail[0].length; var27 += 1) {
					if(!fixedFlag$sample34)
						sample34(var27);
				}
				for(int t = 0; t < numTimeSteps; t += 1) {
					if(!fixedFlag$sample87)
						sample87(t);
					if(!fixedFlag$sample89)
						sample89(t);
				}
			}
		} else {
			if((0 < numTimeSteps)) {
				for(int t = (numTimeSteps - ((((numTimeSteps - 1) - 0) % 1) + 1)); t >= ((0 - 1) + 1); t -= 1) {
					if(!fixedFlag$sample89)
						sample89(t);
					if(!fixedFlag$sample87)
						sample87(t);
				}
				for(int var27 = (avail[0].length - ((((avail[0].length - 1) - 0) % 1) + 1)); var27 >= ((0 - 1) + 1); var27 -= 1) {
					if(!fixedFlag$sample34)
						sample34(var27);
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
		logProbability$var23 = 0.0;
		logProbability$exped = 0.0;
		logProbability$expedNorm = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$ut = 0.0;
		logProbability$weekly_rates = 0.0;
		if(!fixedProbFlag$sample34) {
			if((0 < numTimeSteps)) {
				for(int var27 = 0; var27 < avail[0].length; var27 += 1)
					logProbability$sample34[((var27 - 0) / 1)] = 0.0;
			}
		}
		if((0 < numTimeSteps)) {
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var77[((t - 0) / 1)] = 0.0;
		}
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample87) {
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample87[((t - 0) / 1)] = 0.0;
			}
		}
		if((0 < numTimeSteps)) {
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var79[((t - 0) / 1)] = 0.0;
		}
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample89) {
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample89[((t - 0) / 1)] = 0.0;
			}
		}
		if((0 < numTimeSteps)) {
			for(int t = 0; t < numTimeSteps; t += 1)
				logProbability$var111[((t - 0) / 1)] = 0.0;
		}
		logProbability$sales = 0.0;
		logProbability$weekly_sales = 0.0;
		if(!fixedProbFlag$sample124) {
			if((0 < numTimeSteps)) {
				for(int t = 0; t < numTimeSteps; t += 1)
					logProbability$sample124[((t - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(fixedFlag$sample87)
			logProbabilityValue$sample87();
		if(fixedFlag$sample89)
			logProbabilityValue$sample89();
		logProbabilityValue$sample124();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample34();
		logProbabilityValue$sample87();
		logProbabilityValue$sample89();
		logProbabilityValue$sample124();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample34();
		logProbabilityValue$sample87();
		logProbabilityValue$sample89();
		logProbabilityValue$sample124();
	}

	@Override
	public final void logProbabilityGeneration() {
		if((0 < numTimeSteps)) {
			for(int var27 = 0; var27 < avail[0].length; var27 += 1) {
				if(!fixedFlag$sample34)
					ut[var27] = ((Math.sqrt(1.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
			}
			for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
				if(!fixedFlag$sample34)
					exped[j$var33] = Math.exp(ut[j$var33]);
			}
			double reduceVar$sum$11 = 0.0;
			for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1) {
				double k$var44 = reduceVar$sum$11;
				double l$var45 = exped[cv$reduction50Index];
				if(!fixedFlag$sample34)
					reduceVar$sum$11 = (k$var44 + l$var45);
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				if(!fixedFlag$sample34)
					expedNorm[j$var50] = (exped[j$var50] / (r * reduceVar$sum$11));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				if(!fixedFlag$sample87)
					lambda[((t - 0) / 1)] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
				int reduceVar$numSales$8 = 0;
				for(int cv$reduction77Index = 0; cv$reduction77Index < ObsSales[t].length; cv$reduction77Index += 1) {
					int k$var69 = reduceVar$numSales$8;
					int l$var70 = ObsSales[t][cv$reduction77Index];
					if(!fixedFlag$sample89)
						reduceVar$numSales$8 = (k$var69 + l$var70);
				}
				if(!fixedFlag$sample89)
					arrivals[((t - 0) / 1)] = (reduceVar$numSales$8 + DistributionSampling.samplePoisson(RNG$, lambda[((t - 0) / 1)]));
				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
					if(avail[t][j$var86]) {
						if(!fixedFlag$sample34)
							weekly_ut[((t - 0) / 1)][j$var86] = expedNorm[j$var86];
					} else
						weekly_ut[((t - 0) / 1)][j$var86] = 0.0;
				}
				weekly_ut[((t - 0) / 1)][avail[0].length] = 1.0;
				double reduceVar$denom$13 = 0.0;
				for(int cv$reduction110Index = 0; cv$reduction110Index < (avail[0].length + 1); cv$reduction110Index += 1) {
					double k$var99 = reduceVar$denom$13;
					double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction110Index];
					if(!fixedFlag$sample34)
						reduceVar$denom$13 = (k$var99 + l$var100);
				}
				for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
					if(!fixedFlag$sample34)
						weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$13);
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
				int[] observed_weekly_sales;
				observed_weekly_sales = sales[t];
				for(int j$var116 = (avail[0].length - ((((avail[0].length - 1) - 0) % 1) + 1)); j$var116 >= ((0 - 1) + 1); j$var116 -= 1)
					weekly_sales[((t - 0) / 1)][j$var116] = observed_weekly_sales[j$var116];
			}
		}
	}

	@Override
	public final void setIntermediates() {
		if((0 < numTimeSteps)) {
			for(int j$var33 = 0; j$var33 < avail[0].length; j$var33 += 1) {
				if(true)
					exped[j$var33] = Math.exp(ut[j$var33]);
			}
			double reduceVar$sum$12 = 0.0;
			for(int cv$reduction50Index = 0; cv$reduction50Index < avail[0].length; cv$reduction50Index += 1) {
				double k$var44 = reduceVar$sum$12;
				double l$var45 = exped[cv$reduction50Index];
				reduceVar$sum$12 = (k$var44 + l$var45);
			}
			for(int j$var50 = 0; j$var50 < avail[0].length; j$var50 += 1) {
				if(true)
					expedNorm[j$var50] = (exped[j$var50] / (r * reduceVar$sum$12));
			}
			for(int t = 0; t < numTimeSteps; t += 1) {
				for(int j$var86 = 0; j$var86 < avail[0].length; j$var86 += 1) {
					if(avail[t][j$var86]) {
						if(true)
							weekly_ut[((t - 0) / 1)][j$var86] = expedNorm[j$var86];
					}
				}
				double reduceVar$denom$14 = 0.0;
				for(int cv$reduction110Index = 0; cv$reduction110Index < (avail[0].length + 1); cv$reduction110Index += 1) {
					double k$var99 = reduceVar$denom$14;
					double l$var100 = weekly_ut[((t - 0) / 1)][cv$reduction110Index];
					reduceVar$denom$14 = (k$var99 + l$var100);
				}
				for(int j$var107 = 0; j$var107 < (avail[0].length + 1); j$var107 += 1) {
					if(true)
						weekly_rates[((t - 0) / 1)][j$var107] = (weekly_ut[((t - 0) / 1)][j$var107] / reduceVar$denom$14);
				}
				if(setFlag$weekly_sales) {
					int[] observed_weekly_sales = sales[t];
					for(int j$var116 = 0; j$var116 < avail[0].length; j$var116 += 1)
						observed_weekly_sales[j$var116] = weekly_sales[((t - 0) / 1)][j$var116];
				}
			}
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n/*\n * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n */\npackage org.sandwood.compiler.tests.parser;\n\nmodel Vulcano2012basicDG(int[][] ObsSales, boolean[][] avail) {\n   // avail is the availability matrix, numTimeSteps-by-numProducts\n   // r is the normalization constant here, number between 0 and 1. \"Relative appeal of the outside option\"\n   double r = 0.3;\n    \n   int numTimeSteps = avail.length;\n   if(numTimeSteps > 0) {\n      int numProducts = avail[0].length;\n\n      // draw utilities\n      double[] ut = gaussian(0, 1).sample(numProducts);\n\n      //exponentiate right here (in the non-basic models move to the for loop)\n      double[] exped = new double[numProducts];\n      for(int j : [0..numProducts))\n         exped[j] = exp(ut[j]);\n\n      //Choices includes the choice to not buy anything.\n      int numChoices = numProducts + 1;\n\n      //now normalize\n      double[] expedNorm = new double[numProducts];\n      double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n      for(int j : [0..numProducts))\n         expedNorm[j] = exped[j]/(r*sum);\n\n      int[][] sales = new int[numTimeSteps][numProducts];\n\n      for (int t:[0..numTimeSteps)){\n         // Calculate the number of purchases made.\n         int numSales = reduce(ObsSales[t], 0, (k, l) -> { return k + l; });\n\n         // prior for the distribution of lambda for arrivals. These can be \n         // supplied as a vector if RGBU has some estimates, or just use some priors.\n         public double lambda = gamma(10,10).sample();\n         public int arrivals = numSales + poisson(lambda).sample();\n\n         // for each period t calculate choice probabilities and sales\n         double[] weekly_rates = new double[numChoices];\n         double[] weekly_ut = new double[numChoices];\n\n         for(int j : [0..numProducts)) {\n            if(avail[t][j])\n               weekly_ut[j] = expedNorm[j];\n            else\n               weekly_ut[j] = 0.0;\n         }\n         // Moved v_0 to the end of the array to keep indexing consistent everywhere else in \n         // the model and delayed the assignment of the value 1 to here. None of this is a \n         // sandwood requirement, I just thought it made the model eaiser to follow.\n         weekly_ut[numProducts] = 1.0;\n\n         double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n\n         for(int j : [0..numProducts]) \n            weekly_rates[j] = weekly_ut[j]/denom ;\n\n         int[] weekly_sales = multinomial(weekly_rates, arrivals).sample();\n\n         //getting rid of the no purchase observation (last one in the vector of weekly_sales)\n         int[] observed_weekly_sales = sales[t];\n         for (int j : [0..numProducts))\n            observed_weekly_sales[j] = weekly_sales[j] ;\n      }\n      // assert that generated sales match observed sales\n      sales.observe(ObsSales);\n   }\n}";
	}
}