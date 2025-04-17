package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Vulcano2012basic2$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Vulcano2012basic2$CoreInterface {
	private int[][] Avail;
	private int[][] ObsSales;
	private int[][] Sales;
	private int T;
	private double[] exped;
	private double[] expedNorm;
	private boolean fixedFlag$sample26 = false;
	private boolean fixedProbFlag$sample149 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean fixedProbFlag$sample82 = false;
	private boolean[] guard$sample26multinomial148$global;
	private boolean[][] guard$sample26put123$global;
	private boolean[][] guard$sample26put146$global;
	private boolean[] guard$sample26put68$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$Sales;
	private double logProbability$exped;
	private double logProbability$expedNorm;
	private double logProbability$sales_sum;
	private double[] logProbability$sample149;
	private double[] logProbability$sample26;
	private double[] logProbability$sample82;
	private double logProbability$sum;
	private double logProbability$ut;
	private double[] logProbability$var145;
	private double[] logProbability$var25;
	private double[] logProbability$var80;
	private double logProbability$weekly_rates;
	private double logProbability$weekly_ut;
	private int noProducts;
	private double r;
	private int[] sales_sum;
	private double sum;
	private boolean system$gibbsForward = true;
	private double[] ut;
	private double[][] weekly_rates;
	private double[][] weekly_ut;

	public Vulcano2012basic2$SingleThreadCPU(ExecutionTarget target) {
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
	public final double[] get$exped() {
		return exped;
	}

	@Override
	public final double[] get$expedNorm() {
		return expedNorm;
	}

	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	@Override
	public final void set$fixedFlag$sample26(boolean cv$value) {
		fixedFlag$sample26 = cv$value;
		fixedProbFlag$sample26 = (fixedFlag$sample26 && fixedProbFlag$sample26);
		fixedProbFlag$sample149 = (fixedFlag$sample26 && fixedProbFlag$sample149);
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
	public final double get$logProbability$exped() {
		return logProbability$exped;
	}

	@Override
	public final double get$logProbability$expedNorm() {
		return logProbability$expedNorm;
	}

	@Override
	public final double get$logProbability$sales_sum() {
		return logProbability$sales_sum;
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
	public final double get$r() {
		return r;
	}

	@Override
	public final void set$r(double cv$value) {
		r = cv$value;
	}

	@Override
	public final int[] get$sales_sum() {
		return sales_sum;
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
		fixedProbFlag$sample26 = false;
		fixedProbFlag$sample149 = false;
	}

	private final void logProbabilityValue$sample149() {
		if(!fixedProbFlag$sample149) {
			double cv$accumulator = 0.0;
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int[] cv$sampleValue = Sales[t$var105];
					{
						{
							int var144 = sales_sum[t$var105];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(cv$sampleValue, weekly_rates[((t$var105 - 0) / 1)], noProducts, var144));
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
				logProbability$var145[((t$var105 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample149[((t$var105 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample149 = fixedFlag$sample26;
		} else {
			double cv$accumulator = 0.0;
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample149[((t$var105 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var145[((t$var105 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample26() {
		if(!fixedProbFlag$sample26) {
			double cv$accumulator = 0.0;
			for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = ut[j$var20];
					{
						{
							double var23 = 0.0;
							double var24 = 2.0;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var23) / Math.sqrt(var24))) - (0.5 * Math.log(var24))));
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
				logProbability$var25[((j$var20 - 1) / 1)] = cv$sampleAccumulator;
				logProbability$sample26[((j$var20 - 1) / 1)] = cv$sampleProbability;
				boolean cv$guard$exped = false;
				boolean cv$guard$sum = false;
				boolean cv$guard$expedNorm = false;
				boolean cv$guard$weekly_ut = false;
				boolean cv$guard$weekly_rates = false;
				{
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							if(!cv$guard$exped) {
								cv$guard$exped = true;
								logProbability$exped = (logProbability$exped + cv$sampleProbability);
							}
						}
					}
				}
				{
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							if(((0 <= j$var38) && (j$var38 < noProducts))) {
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
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							if(((0 <= j$var38) && (j$var38 < noProducts))) {
								{
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										if(!cv$guard$expedNorm) {
											cv$guard$expedNorm = true;
											logProbability$expedNorm = (logProbability$expedNorm + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
								if((j$var38 == j$var63)) {
									if(!cv$guard$expedNorm) {
										cv$guard$expedNorm = true;
										logProbability$expedNorm = (logProbability$expedNorm + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
				{
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							if(((0 <= j$var38) && (j$var38 < noProducts))) {
								{
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
											if((j$var63 == j$var116)) {
												for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
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
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
								if((j$var38 == j$var63)) {
									for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
										if((j$var63 == j$var116)) {
											for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
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
				{
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							if(((0 <= j$var38) && (j$var38 < noProducts))) {
								{
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
											if((j$var63 == j$var116)) {
												for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
													if(((0 <= j$var116) && (j$var116 < noProducts))) {
														{
															for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
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
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							if(((0 <= j$var38) && (j$var38 < noProducts))) {
								{
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
											if((j$var63 == j$var116)) {
												for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
													if((j$var116 == j$var140)) {
														for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
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
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
								if((j$var38 == j$var63)) {
									for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
										if((j$var63 == j$var116)) {
											for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
												if(((0 <= j$var116) && (j$var116 < noProducts))) {
													{
														for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
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
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
								if((j$var38 == j$var63)) {
									for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
										if((j$var63 == j$var116)) {
											for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
												if((j$var116 == j$var140)) {
													for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
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
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample26 = fixedFlag$sample26;
		} else {
			double cv$accumulator = 0.0;
			for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample26[((j$var20 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var25[((j$var20 - 1) / 1)] = cv$rvAccumulator;
				boolean cv$guard$exped = false;
				boolean cv$guard$sum = false;
				boolean cv$guard$expedNorm = false;
				boolean cv$guard$weekly_ut = false;
				boolean cv$guard$weekly_rates = false;
				{
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							if(!cv$guard$exped) {
								cv$guard$exped = true;
								logProbability$exped = (logProbability$exped + cv$sampleValue);
							}
						}
					}
				}
				{
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							if(((0 <= j$var38) && (j$var38 < noProducts))) {
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
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							if(((0 <= j$var38) && (j$var38 < noProducts))) {
								{
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										if(!cv$guard$expedNorm) {
											cv$guard$expedNorm = true;
											logProbability$expedNorm = (logProbability$expedNorm + cv$sampleValue);
										}
									}
								}
							}
						}
					}
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
								if((j$var38 == j$var63)) {
									if(!cv$guard$expedNorm) {
										cv$guard$expedNorm = true;
										logProbability$expedNorm = (logProbability$expedNorm + cv$sampleValue);
									}
								}
							}
						}
					}
				}
				{
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							if(((0 <= j$var38) && (j$var38 < noProducts))) {
								{
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
											if((j$var63 == j$var116)) {
												for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
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
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
								if((j$var38 == j$var63)) {
									for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
										if((j$var63 == j$var116)) {
											for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
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
				{
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							if(((0 <= j$var38) && (j$var38 < noProducts))) {
								{
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
											if((j$var63 == j$var116)) {
												for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
													if(((0 <= j$var116) && (j$var116 < noProducts))) {
														{
															for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
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
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							if(((0 <= j$var38) && (j$var38 < noProducts))) {
								{
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
											if((j$var63 == j$var116)) {
												for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
													if((j$var116 == j$var140)) {
														for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
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
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
								if((j$var38 == j$var63)) {
									for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
										if((j$var63 == j$var116)) {
											for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
												if(((0 <= j$var116) && (j$var116 < noProducts))) {
													{
														for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
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
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
								if((j$var38 == j$var63)) {
									for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
										if((j$var63 == j$var116)) {
											for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
												if((j$var116 == j$var140)) {
													for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
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
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample82() {
		if(!fixedProbFlag$sample82) {
			double cv$accumulator = 0.0;
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = sales_sum[t$var78];
					{
						{
							double var79 = 0.5;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$sampleValue, var79));
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
				logProbability$var80[((t$var78 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample82[((t$var78 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$Sales = false;
				{
					for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
						if((t$var78 == t$var105)) {
							if(!cv$guard$Sales) {
								cv$guard$Sales = true;
								logProbability$Sales = (logProbability$Sales + cv$sampleProbability);
							}
						}
					}
				}
			}
			logProbability$sales_sum = (logProbability$sales_sum + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample82 = true;
		} else {
			double cv$accumulator = 0.0;
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample82[((t$var78 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var80[((t$var78 - 0) / 1)] = cv$rvAccumulator;
				boolean cv$guard$Sales = false;
				{
					for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
						if((t$var78 == t$var105)) {
							if(!cv$guard$Sales) {
								cv$guard$Sales = true;
								logProbability$Sales = (logProbability$Sales + cv$sampleValue);
							}
						}
					}
				}
			}
			logProbability$sales_sum = (logProbability$sales_sum + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample26(int j$var20) {
		if(true) {
			int cv$numNumStates = 0;
			{
				cv$numNumStates = Math.max(cv$numNumStates, 2);
			}
			double cv$originalValue = ut[j$var20];
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
						double var26 = cv$proposedValue;
						{
							{
								ut[j$var20] = cv$currentValue;
							}
						}
						{
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									{
										exped[j$var38] = Math.exp(ut[j$var38]);
									}
								}
							}
						}
						{
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									if(((0 <= j$var38) && (j$var38 < noProducts))) {
										{
											{
												double reduceVar$sum$0 = 0.0;
												for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1) {
													double k$var49 = reduceVar$sum$0;
													double l$var50 = exped[cv$reduction46Index];
													reduceVar$sum$0 = (k$var49 + l$var50);
												}
												sum = reduceVar$sum$0;
											}
										}
									}
								}
							}
						}
						{
							boolean[] guard$sample26put68 = guard$sample26put68$global;
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									if(((0 <= j$var38) && (j$var38 < noProducts))) {
										{
											for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1)
												guard$sample26put68[((j$var63 - 0) / 1)] = false;
										}
									}
								}
							}
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										if((j$var38 == j$var63))
											guard$sample26put68[((j$var63 - 0) / 1)] = false;
									}
								}
							}
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									if(((0 <= j$var38) && (j$var38 < noProducts))) {
										{
											for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
												if(!guard$sample26put68[((j$var63 - 0) / 1)]) {
													guard$sample26put68[((j$var63 - 0) / 1)] = true;
													{
														expedNorm[j$var63] = (exped[j$var63] / (r * sum));
													}
												}
											}
										}
									}
								}
							}
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										if((j$var38 == j$var63)) {
											if(!guard$sample26put68[((j$var63 - 0) / 1)]) {
												guard$sample26put68[((j$var63 - 0) / 1)] = true;
												{
													expedNorm[j$var63] = (exped[j$var63] / (r * sum));
												}
											}
										}
									}
								}
							}
						}
						{
							boolean[][] guard$sample26put123 = guard$sample26put123$global;
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									if(((0 <= j$var38) && (j$var38 < noProducts))) {
										{
											for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
												for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
															guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)] = false;
													}
												}
											}
										}
									}
								}
							}
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										if((j$var38 == j$var63)) {
											for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
												if((j$var63 == j$var116)) {
													for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
														guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									if(((0 <= j$var38) && (j$var38 < noProducts))) {
										{
											for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
												for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
															if(!guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)]) {
																guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)] = true;
																{
																	weekly_ut[((t$var105 - 0) / 1)][j$var116] = (expedNorm[j$var116] * Avail[t$var105][j$var116]);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										if((j$var38 == j$var63)) {
											for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
												if((j$var63 == j$var116)) {
													for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
														if(!guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)]) {
															guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)] = true;
															{
																weekly_ut[((t$var105 - 0) / 1)][j$var116] = (expedNorm[j$var116] * Avail[t$var105][j$var116]);
															}
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
							boolean[][] guard$sample26put146 = guard$sample26put146$global;
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									if(((0 <= j$var38) && (j$var38 < noProducts))) {
										{
											for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
												for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
															if(((0 <= j$var116) && (j$var116 < noProducts))) {
																{
																	for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1)
																		guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = false;
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									if(((0 <= j$var38) && (j$var38 < noProducts))) {
										{
											for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
												for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
															if((j$var116 == j$var140)) {
																for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
																	guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = false;
															}
														}
													}
												}
											}
										}
									}
								}
							}
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										if((j$var38 == j$var63)) {
											for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
												if((j$var63 == j$var116)) {
													for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
														if(((0 <= j$var116) && (j$var116 < noProducts))) {
															{
																for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1)
																	guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = false;
															}
														}
													}
												}
											}
										}
									}
								}
							}
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										if((j$var38 == j$var63)) {
											for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
												if((j$var63 == j$var116)) {
													for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
														if((j$var116 == j$var140)) {
															for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
																guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = false;
														}
													}
												}
											}
										}
									}
								}
							}
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									if(((0 <= j$var38) && (j$var38 < noProducts))) {
										{
											for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
												for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
															if(((0 <= j$var116) && (j$var116 < noProducts))) {
																{
																	for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
																		if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
																			guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
																			{
																				double reduceVar$denom$0 = 0.0;
																				for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
																					double k$var128 = reduceVar$denom$0;
																					double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																					reduceVar$denom$0 = (k$var128 + l$var129);
																				}
																				weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$0);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									if(((0 <= j$var38) && (j$var38 < noProducts))) {
										{
											for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
												for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
															if((j$var116 == j$var140)) {
																for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
																	if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
																		guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
																		{
																			double reduceVar$denom$1 = 0.0;
																			for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
																				double k$var128 = reduceVar$denom$1;
																				double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																				reduceVar$denom$1 = (k$var128 + l$var129);
																			}
																			weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$1);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										if((j$var38 == j$var63)) {
											for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
												if((j$var63 == j$var116)) {
													for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
														if(((0 <= j$var116) && (j$var116 < noProducts))) {
															{
																for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
																	if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
																		guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
																		{
																			double reduceVar$denom$2 = 0.0;
																			for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
																				double k$var128 = reduceVar$denom$2;
																				double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																				reduceVar$denom$2 = (k$var128 + l$var129);
																			}
																			weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$2);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										if((j$var38 == j$var63)) {
											for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
												if((j$var63 == j$var116)) {
													for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
														if((j$var116 == j$var140)) {
															for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
																if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
																	guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
																	{
																		double reduceVar$denom$3 = 0.0;
																		for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
																			double k$var128 = reduceVar$denom$3;
																			double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																			reduceVar$denom$3 = (k$var128 + l$var129);
																		}
																		weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$3);
																	}
																}
															}
														}
													}
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
					double cv$temp$0$var23;
					{
						cv$temp$0$var23 = 0.0;
					}
					double cv$temp$1$var24;
					{
						cv$temp$1$var24 = 2.0;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var23) / Math.sqrt(cv$temp$1$var24))) - (0.5 * Math.log(cv$temp$1$var24))));
					{
						{
							boolean[] guard$sample26multinomial148 = guard$sample26multinomial148$global;
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									if(((0 <= j$var38) && (j$var38 < noProducts))) {
										{
											for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
												for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
															if(((0 <= j$var116) && (j$var116 < noProducts))) {
																{
																	guard$sample26multinomial148[((t$var105 - 0) / 1)] = false;
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									if(((0 <= j$var38) && (j$var38 < noProducts))) {
										{
											for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
												for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
															if((j$var116 == j$var140)) {
																for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
																	guard$sample26multinomial148[((t$var105 - 0) / 1)] = false;
															}
														}
													}
												}
											}
										}
									}
								}
							}
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										if((j$var38 == j$var63)) {
											for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
												if((j$var63 == j$var116)) {
													for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
														if(((0 <= j$var116) && (j$var116 < noProducts))) {
															{
																guard$sample26multinomial148[((t$var105 - 0) / 1)] = false;
															}
														}
													}
												}
											}
										}
									}
								}
							}
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										if((j$var38 == j$var63)) {
											for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
												if((j$var63 == j$var116)) {
													for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
														if((j$var116 == j$var140)) {
															for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
																guard$sample26multinomial148[((t$var105 - 0) / 1)] = false;
														}
													}
												}
											}
										}
									}
								}
							}
							double traceTempVariable$var39$24_1 = cv$currentValue;
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									double traceTempVariable$k$24_3 = Math.exp(traceTempVariable$var39$24_1);
									if(((0 <= j$var38) && (j$var38 < noProducts))) {
										{
											if((0 < noProducts)) {
												double reduceVar$sum$1 = 0.0;
												for(int cv$reduction841Index = 0; cv$reduction841Index < j$var38; cv$reduction841Index += 1) {
													double k$var49 = reduceVar$sum$1;
													double l$var50 = exped[cv$reduction841Index];
													reduceVar$sum$1 = (k$var49 + l$var50);
												}
												for(int cv$reduction841Index = (j$var38 + 1); cv$reduction841Index < noProducts; cv$reduction841Index += 1) {
													double k$var49 = reduceVar$sum$1;
													double l$var50 = exped[cv$reduction841Index];
													reduceVar$sum$1 = (k$var49 + l$var50);
												}
												double cv$reduced46 = reduceVar$sum$1;
												reduceVar$sum$1 = (traceTempVariable$k$24_3 + cv$reduced46);
												double traceTempVariable$sum$24_4 = reduceVar$sum$1;
												for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
													double traceTempVariable$var117$24_6 = (exped[j$var63] / (r * traceTempVariable$sum$24_4));
													for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
																double traceTempVariable$k$24_9 = (traceTempVariable$var117$24_6 * Avail[t$var105][j$var116]);
																if(((0 <= j$var116) && (j$var116 < noProducts))) {
																	{
																		if((0 < noProducts)) {
																			double reduceVar$denom$4 = 0.0;
																			for(int cv$reduction860Index = 0; cv$reduction860Index < j$var116; cv$reduction860Index += 1) {
																				double k$var128 = reduceVar$denom$4;
																				double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction860Index];
																				reduceVar$denom$4 = (k$var128 + l$var129);
																			}
																			for(int cv$reduction860Index = (j$var116 + 1); cv$reduction860Index < noProducts; cv$reduction860Index += 1) {
																				double k$var128 = reduceVar$denom$4;
																				double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction860Index];
																				reduceVar$denom$4 = (k$var128 + l$var129);
																			}
																			double cv$reduced128 = reduceVar$denom$4;
																			reduceVar$denom$4 = (traceTempVariable$k$24_9 + cv$reduced128);
																			double traceTempVariable$denom$24_10 = reduceVar$denom$4;
																			if(!guard$sample26multinomial148[((t$var105 - 0) / 1)]) {
																				guard$sample26multinomial148[((t$var105 - 0) / 1)] = true;
																				{
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						{
																							{
																								{
																									double[] cv$temp$2$weekly_rates;
																									{
																										cv$temp$2$weekly_rates = weekly_rates[((t$var105 - 0) / 1)];
																									}
																									int cv$temp$3$$var706;
																									{
																										int $var706 = noProducts;
																										cv$temp$3$$var706 = $var706;
																									}
																									int cv$temp$4$var144;
																									{
																										int var144 = sales_sum[t$var105];
																										cv$temp$4$var144 = var144;
																									}
																									if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], cv$temp$2$weekly_rates, cv$temp$3$$var706, cv$temp$4$var144)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], cv$temp$2$weekly_rates, cv$temp$3$$var706, cv$temp$4$var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], cv$temp$2$weekly_rates, cv$temp$3$$var706, cv$temp$4$var144));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], cv$temp$2$weekly_rates, cv$temp$3$$var706, cv$temp$4$var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], cv$temp$2$weekly_rates, cv$temp$3$$var706, cv$temp$4$var144)));
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
							double traceTempVariable$var39$25_1 = cv$currentValue;
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									double traceTempVariable$k$25_3 = Math.exp(traceTempVariable$var39$25_1);
									if(((0 <= j$var38) && (j$var38 < noProducts))) {
										{
											if((0 < noProducts)) {
												double reduceVar$sum$2 = 0.0;
												for(int cv$reduction889Index = 0; cv$reduction889Index < j$var38; cv$reduction889Index += 1) {
													double k$var49 = reduceVar$sum$2;
													double l$var50 = exped[cv$reduction889Index];
													reduceVar$sum$2 = (k$var49 + l$var50);
												}
												for(int cv$reduction889Index = (j$var38 + 1); cv$reduction889Index < noProducts; cv$reduction889Index += 1) {
													double k$var49 = reduceVar$sum$2;
													double l$var50 = exped[cv$reduction889Index];
													reduceVar$sum$2 = (k$var49 + l$var50);
												}
												double cv$reduced46 = reduceVar$sum$2;
												reduceVar$sum$2 = (traceTempVariable$k$25_3 + cv$reduced46);
												double traceTempVariable$sum$25_4 = reduceVar$sum$2;
												for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
													double traceTempVariable$var117$25_6 = (exped[j$var63] / (r * traceTempVariable$sum$25_4));
													for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
																double traceTempVariable$var141$25_9 = (traceTempVariable$var117$25_6 * Avail[t$var105][j$var116]);
																for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
																	if((j$var116 == j$var140)) {
																		if(!guard$sample26multinomial148[((t$var105 - 0) / 1)]) {
																			guard$sample26multinomial148[((t$var105 - 0) / 1)] = true;
																			{
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						{
																							{
																								double[] cv$temp$5$weekly_rates;
																								{
																									cv$temp$5$weekly_rates = weekly_rates[((t$var105 - 0) / 1)];
																								}
																								int cv$temp$6$$var709;
																								{
																									int $var709 = noProducts;
																									cv$temp$6$$var709 = $var709;
																								}
																								int cv$temp$7$var144;
																								{
																									int var144 = sales_sum[t$var105];
																									cv$temp$7$var144 = var144;
																								}
																								if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], cv$temp$5$weekly_rates, cv$temp$6$$var709, cv$temp$7$var144)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], cv$temp$5$weekly_rates, cv$temp$6$$var709, cv$temp$7$var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], cv$temp$5$weekly_rates, cv$temp$6$$var709, cv$temp$7$var144));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], cv$temp$5$weekly_rates, cv$temp$6$$var709, cv$temp$7$var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], cv$temp$5$weekly_rates, cv$temp$6$$var709, cv$temp$7$var144)));
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
							double traceTempVariable$var39$26_1 = cv$currentValue;
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									double traceTempVariable$var64$26_3 = Math.exp(traceTempVariable$var39$26_1);
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										if((j$var38 == j$var63)) {
											double traceTempVariable$var117$26_5 = (traceTempVariable$var64$26_3 / (r * sum));
											for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
												if((j$var63 == j$var116)) {
													for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
														double traceTempVariable$k$26_8 = (traceTempVariable$var117$26_5 * Avail[t$var105][j$var116]);
														if(((0 <= j$var116) && (j$var116 < noProducts))) {
															{
																if((0 < noProducts)) {
																	double reduceVar$denom$5 = 0.0;
																	for(int cv$reduction935Index = 0; cv$reduction935Index < j$var116; cv$reduction935Index += 1) {
																		double k$var128 = reduceVar$denom$5;
																		double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction935Index];
																		reduceVar$denom$5 = (k$var128 + l$var129);
																	}
																	for(int cv$reduction935Index = (j$var116 + 1); cv$reduction935Index < noProducts; cv$reduction935Index += 1) {
																		double k$var128 = reduceVar$denom$5;
																		double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction935Index];
																		reduceVar$denom$5 = (k$var128 + l$var129);
																	}
																	double cv$reduced128 = reduceVar$denom$5;
																	reduceVar$denom$5 = (traceTempVariable$k$26_8 + cv$reduced128);
																	double traceTempVariable$denom$26_9 = reduceVar$denom$5;
																	if(!guard$sample26multinomial148[((t$var105 - 0) / 1)]) {
																		guard$sample26multinomial148[((t$var105 - 0) / 1)] = true;
																		{
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				{
																					{
																						{
																							double[] cv$temp$8$weekly_rates;
																							{
																								cv$temp$8$weekly_rates = weekly_rates[((t$var105 - 0) / 1)];
																							}
																							int cv$temp$9$$var712;
																							{
																								int $var712 = noProducts;
																								cv$temp$9$$var712 = $var712;
																							}
																							int cv$temp$10$var144;
																							{
																								int var144 = sales_sum[t$var105];
																								cv$temp$10$var144 = var144;
																							}
																							if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], cv$temp$8$weekly_rates, cv$temp$9$$var712, cv$temp$10$var144)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], cv$temp$8$weekly_rates, cv$temp$9$$var712, cv$temp$10$var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], cv$temp$8$weekly_rates, cv$temp$9$$var712, cv$temp$10$var144));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], cv$temp$8$weekly_rates, cv$temp$9$$var712, cv$temp$10$var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], cv$temp$8$weekly_rates, cv$temp$9$$var712, cv$temp$10$var144)));
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
							double traceTempVariable$var39$27_1 = cv$currentValue;
							for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
								if((j$var20 == j$var38)) {
									double traceTempVariable$var64$27_3 = Math.exp(traceTempVariable$var39$27_1);
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										if((j$var38 == j$var63)) {
											double traceTempVariable$var117$27_5 = (traceTempVariable$var64$27_3 / (r * sum));
											for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
												if((j$var63 == j$var116)) {
													for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
														double traceTempVariable$var141$27_8 = (traceTempVariable$var117$27_5 * Avail[t$var105][j$var116]);
														for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
															if((j$var116 == j$var140)) {
																if(!guard$sample26multinomial148[((t$var105 - 0) / 1)]) {
																	guard$sample26multinomial148[((t$var105 - 0) / 1)] = true;
																	{
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			{
																				{
																					{
																						double[] cv$temp$11$weekly_rates;
																						{
																							cv$temp$11$weekly_rates = weekly_rates[((t$var105 - 0) / 1)];
																						}
																						int cv$temp$12$$var715;
																						{
																							int $var715 = noProducts;
																							cv$temp$12$$var715 = $var715;
																						}
																						int cv$temp$13$var144;
																						{
																							int var144 = sales_sum[t$var105];
																							cv$temp$13$var144 = var144;
																						}
																						if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], cv$temp$11$weekly_rates, cv$temp$12$$var715, cv$temp$13$var144)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], cv$temp$11$weekly_rates, cv$temp$12$$var715, cv$temp$13$var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], cv$temp$11$weekly_rates, cv$temp$12$$var715, cv$temp$13$var144));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], cv$temp$11$weekly_rates, cv$temp$12$$var715, cv$temp$13$var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], cv$temp$11$weekly_rates, cv$temp$12$$var715, cv$temp$13$var144)));
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
				double var26 = cv$originalValue;
				{
					{
						ut[j$var20] = var26;
					}
				}
				{
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							{
								exped[j$var38] = Math.exp(ut[j$var38]);
							}
						}
					}
				}
				{
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							if(((0 <= j$var38) && (j$var38 < noProducts))) {
								{
									{
										double reduceVar$sum$3 = 0.0;
										for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1) {
											double k$var49 = reduceVar$sum$3;
											double l$var50 = exped[cv$reduction46Index];
											reduceVar$sum$3 = (k$var49 + l$var50);
										}
										sum = reduceVar$sum$3;
									}
								}
							}
						}
					}
				}
				{
					boolean[] guard$sample26put68 = guard$sample26put68$global;
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							if(((0 <= j$var38) && (j$var38 < noProducts))) {
								{
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1)
										guard$sample26put68[((j$var63 - 0) / 1)] = false;
								}
							}
						}
					}
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
								if((j$var38 == j$var63))
									guard$sample26put68[((j$var63 - 0) / 1)] = false;
							}
						}
					}
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							if(((0 <= j$var38) && (j$var38 < noProducts))) {
								{
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										if(!guard$sample26put68[((j$var63 - 0) / 1)]) {
											guard$sample26put68[((j$var63 - 0) / 1)] = true;
											{
												expedNorm[j$var63] = (exped[j$var63] / (r * sum));
											}
										}
									}
								}
							}
						}
					}
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
								if((j$var38 == j$var63)) {
									if(!guard$sample26put68[((j$var63 - 0) / 1)]) {
										guard$sample26put68[((j$var63 - 0) / 1)] = true;
										{
											expedNorm[j$var63] = (exped[j$var63] / (r * sum));
										}
									}
								}
							}
						}
					}
				}
				{
					boolean[][] guard$sample26put123 = guard$sample26put123$global;
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							if(((0 <= j$var38) && (j$var38 < noProducts))) {
								{
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
											if((j$var63 == j$var116)) {
												for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
													guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
					}
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
								if((j$var38 == j$var63)) {
									for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
										if((j$var63 == j$var116)) {
											for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
												guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)] = false;
										}
									}
								}
							}
						}
					}
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							if(((0 <= j$var38) && (j$var38 < noProducts))) {
								{
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
											if((j$var63 == j$var116)) {
												for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
													if(!guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)]) {
														guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)] = true;
														{
															weekly_ut[((t$var105 - 0) / 1)][j$var116] = (expedNorm[j$var116] * Avail[t$var105][j$var116]);
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
								if((j$var38 == j$var63)) {
									for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
										if((j$var63 == j$var116)) {
											for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
												if(!guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)]) {
													guard$sample26put123[((t$var105 - 0) / 1)][((j$var116 - 0) / 1)] = true;
													{
														weekly_ut[((t$var105 - 0) / 1)][j$var116] = (expedNorm[j$var116] * Avail[t$var105][j$var116]);
													}
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
					boolean[][] guard$sample26put146 = guard$sample26put146$global;
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							if(((0 <= j$var38) && (j$var38 < noProducts))) {
								{
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
											if((j$var63 == j$var116)) {
												for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
													if(((0 <= j$var116) && (j$var116 < noProducts))) {
														{
															for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1)
																guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = false;
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							if(((0 <= j$var38) && (j$var38 < noProducts))) {
								{
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
											if((j$var63 == j$var116)) {
												for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
													if((j$var116 == j$var140)) {
														for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
															guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = false;
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
								if((j$var38 == j$var63)) {
									for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
										if((j$var63 == j$var116)) {
											for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
												if(((0 <= j$var116) && (j$var116 < noProducts))) {
													{
														for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1)
															guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = false;
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
								if((j$var38 == j$var63)) {
									for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
										if((j$var63 == j$var116)) {
											for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
												if((j$var116 == j$var140)) {
													for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
														guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
						}
					}
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							if(((0 <= j$var38) && (j$var38 < noProducts))) {
								{
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
											if((j$var63 == j$var116)) {
												for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
													if(((0 <= j$var116) && (j$var116 < noProducts))) {
														{
															for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
																if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
																	guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
																	{
																		double reduceVar$denom$6 = 0.0;
																		for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
																			double k$var128 = reduceVar$denom$6;
																			double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																			reduceVar$denom$6 = (k$var128 + l$var129);
																		}
																		weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$6);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							if(((0 <= j$var38) && (j$var38 < noProducts))) {
								{
									for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
										for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
											if((j$var63 == j$var116)) {
												for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
													if((j$var116 == j$var140)) {
														for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
															if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
																guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
																{
																	double reduceVar$denom$7 = 0.0;
																	for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
																		double k$var128 = reduceVar$denom$7;
																		double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																		reduceVar$denom$7 = (k$var128 + l$var129);
																	}
																	weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$7);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
								if((j$var38 == j$var63)) {
									for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
										if((j$var63 == j$var116)) {
											for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
												if(((0 <= j$var116) && (j$var116 < noProducts))) {
													{
														for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
															if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
																guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
																{
																	double reduceVar$denom$8 = 0.0;
																	for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
																		double k$var128 = reduceVar$denom$8;
																		double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																		reduceVar$denom$8 = (k$var128 + l$var129);
																	}
																	weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$8);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
						if((j$var20 == j$var38)) {
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
								if((j$var38 == j$var63)) {
									for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
										if((j$var63 == j$var116)) {
											for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
												if((j$var116 == j$var140)) {
													for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
														if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
															guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
															{
																double reduceVar$denom$9 = 0.0;
																for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
																	double k$var128 = reduceVar$denom$9;
																	double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																	reduceVar$denom$9 = (k$var128 + l$var129);
																}
																weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$9);
															}
														}
													}
												}
											}
										}
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
			int cv$max_j$var63 = 0;
			cv$max_j$var63 = Math.max(cv$max_j$var63, ((noProducts - 0) / 1));
			guard$sample26put68$global = new boolean[cv$max_j$var63];
		}
		{
			int cv$max_t$var105 = 0;
			int cv$max_j$var116 = 0;
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
				cv$max_j$var116 = Math.max(cv$max_j$var116, ((noProducts - 0) / 1));
			cv$max_t$var105 = Math.max(cv$max_t$var105, ((T - 0) / 1));
			guard$sample26put123$global = new boolean[cv$max_t$var105][cv$max_j$var116];
		}
		{
			int cv$max_t$var105 = 0;
			int cv$max_j$var140 = 0;
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
				cv$max_j$var140 = Math.max(cv$max_j$var140, ((noProducts - 0) / 1));
			cv$max_t$var105 = Math.max(cv$max_t$var105, ((T - 0) / 1));
			guard$sample26put146$global = new boolean[cv$max_t$var105][cv$max_j$var140];
		}
		{
			int cv$max_t$var105 = 0;
			cv$max_t$var105 = Math.max(cv$max_t$var105, ((T - 0) / 1));
			guard$sample26multinomial148$global = new boolean[cv$max_t$var105];
		}
	}

	@Override
	public final void allocator() {
		if(!fixedFlag$sample26) {
			{
				ut = new double[noProducts];
			}
		}
		{
			exped = new double[noProducts];
		}
		{
			expedNorm = new double[noProducts];
		}
		{
			sales_sum = new int[T];
		}
		{
			Sales = new int[T][];
			for(int var93 = 0; var93 < T; var93 += 1)
				Sales[var93] = new int[noProducts];
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
				Sales[t$var105] = new int[noProducts];
		}
		{
			weekly_rates = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
				weekly_rates[((t$var105 - 0) / 1)] = new double[noProducts];
		}
		{
			weekly_ut = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
				weekly_ut[((t$var105 - 0) / 1)] = new double[noProducts];
		}
		{
			logProbability$var25 = new double[((((noProducts - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample26 = new double[((((noProducts - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var80 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample82 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var145 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample149 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1) {
			if(!fixedFlag$sample26)
				ut[j$var20] = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
			if(!fixedFlag$sample26)
				exped[j$var38] = Math.exp(ut[j$var38]);
		}
		double reduceVar$sum$4 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1) {
			double k$var49 = reduceVar$sum$4;
			double l$var50 = exped[cv$reduction46Index];
			if(!fixedFlag$sample26)
				reduceVar$sum$4 = (k$var49 + l$var50);
		}
		if(!fixedFlag$sample26)
			sum = reduceVar$sum$4;
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
			if(!fixedFlag$sample26)
				expedNorm[j$var63] = (exped[j$var63] / (r * sum));
		}
		for(int t$var78 = 0; t$var78 < T; t$var78 += 1)
			sales_sum[t$var78] = DistributionSampling.samplePoisson(RNG$, 0.5);
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
				if(!fixedFlag$sample26)
					weekly_ut[((t$var105 - 0) / 1)][j$var116] = (expedNorm[j$var116] * Avail[t$var105][j$var116]);
			}
			double reduceVar$denom$10 = 0.0;
			for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
				double k$var128 = reduceVar$denom$10;
				double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
				if(!fixedFlag$sample26)
					reduceVar$denom$10 = (k$var128 + l$var129);
			}
			for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
				if(!fixedFlag$sample26)
					weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$10);
			}
			int[] weekly_sales = Sales[t$var105];
			DistributionSampling.sampleMultinomial(RNG$, weekly_rates[((t$var105 - 0) / 1)], noProducts, sales_sum[t$var105], weekly_sales);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1) {
			if(!fixedFlag$sample26)
				ut[j$var20] = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
			if(!fixedFlag$sample26)
				exped[j$var38] = Math.exp(ut[j$var38]);
		}
		double reduceVar$sum$6 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1) {
			double k$var49 = reduceVar$sum$6;
			double l$var50 = exped[cv$reduction46Index];
			if(!fixedFlag$sample26)
				reduceVar$sum$6 = (k$var49 + l$var50);
		}
		if(!fixedFlag$sample26)
			sum = reduceVar$sum$6;
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
			if(!fixedFlag$sample26)
				expedNorm[j$var63] = (exped[j$var63] / (r * sum));
		}
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
				if(!fixedFlag$sample26)
					weekly_ut[((t$var105 - 0) / 1)][j$var116] = (expedNorm[j$var116] * Avail[t$var105][j$var116]);
			}
			double reduceVar$denom$12 = 0.0;
			for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
				double k$var128 = reduceVar$denom$12;
				double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
				if(!fixedFlag$sample26)
					reduceVar$denom$12 = (k$var128 + l$var129);
			}
			for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
				if(!fixedFlag$sample26)
					weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$12);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1) {
			if(!fixedFlag$sample26)
				ut[j$var20] = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
			if(!fixedFlag$sample26)
				exped[j$var38] = Math.exp(ut[j$var38]);
		}
		double reduceVar$sum$5 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1) {
			double k$var49 = reduceVar$sum$5;
			double l$var50 = exped[cv$reduction46Index];
			if(!fixedFlag$sample26)
				reduceVar$sum$5 = (k$var49 + l$var50);
		}
		if(!fixedFlag$sample26)
			sum = reduceVar$sum$5;
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
			if(!fixedFlag$sample26)
				expedNorm[j$var63] = (exped[j$var63] / (r * sum));
		}
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
				if(!fixedFlag$sample26)
					weekly_ut[((t$var105 - 0) / 1)][j$var116] = (expedNorm[j$var116] * Avail[t$var105][j$var116]);
			}
			double reduceVar$denom$11 = 0.0;
			for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
				double k$var128 = reduceVar$denom$11;
				double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
				if(!fixedFlag$sample26)
					reduceVar$denom$11 = (k$var128 + l$var129);
			}
			for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
				if(!fixedFlag$sample26)
					weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$11);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1) {
				if(!fixedFlag$sample26)
					sample26(j$var20);
			}
		} else {
			for(int j$var20 = (noProducts - ((((noProducts - 1) - 1) % 1) + 1)); j$var20 >= ((1 - 1) + 1); j$var20 -= 1) {
				if(!fixedFlag$sample26)
					sample26(j$var20);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		ut[0] = 0.0;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1)
			logProbability$var25[((j$var20 - 1) / 1)] = 0.0;
		logProbability$ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$sum = 0.0;
		logProbability$expedNorm = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$weekly_rates = 0.0;
		if(!fixedProbFlag$sample26) {
			for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1)
				logProbability$sample26[((j$var20 - 1) / 1)] = 0.0;
		}
		for(int t$var78 = 0; t$var78 < T; t$var78 += 1)
			logProbability$var80[((t$var78 - 0) / 1)] = 0.0;
		logProbability$sales_sum = 0.0;
		logProbability$Sales = 0.0;
		if(!fixedProbFlag$sample82) {
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1)
				logProbability$sample82[((t$var78 - 0) / 1)] = 0.0;
		}
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
			logProbability$var145[((t$var105 - 0) / 1)] = 0.0;
		if(!fixedProbFlag$sample149) {
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
				logProbability$sample149[((t$var105 - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample26)
			logProbabilityValue$sample26();
		logProbabilityValue$sample82();
		logProbabilityValue$sample149();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample82();
		logProbabilityValue$sample149();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample82();
		logProbabilityValue$sample149();
	}

	@Override
	public final void logProbabilityGeneration() {
		for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1) {
			if(!fixedFlag$sample26)
				ut[j$var20] = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
			if(!fixedFlag$sample26)
				exped[j$var38] = Math.exp(ut[j$var38]);
		}
		double reduceVar$sum$7 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1) {
			double k$var49 = reduceVar$sum$7;
			double l$var50 = exped[cv$reduction46Index];
			if(!fixedFlag$sample26)
				reduceVar$sum$7 = (k$var49 + l$var50);
		}
		if(!fixedFlag$sample26)
			sum = reduceVar$sum$7;
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
			if(!fixedFlag$sample26)
				expedNorm[j$var63] = (exped[j$var63] / (r * sum));
		}
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
				if(!fixedFlag$sample26)
					weekly_ut[((t$var105 - 0) / 1)][j$var116] = (expedNorm[j$var116] * Avail[t$var105][j$var116]);
			}
			double reduceVar$denom$13 = 0.0;
			for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
				double k$var128 = reduceVar$denom$13;
				double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
				if(!fixedFlag$sample26)
					reduceVar$denom$13 = (k$var128 + l$var129);
			}
			for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
				if(!fixedFlag$sample26)
					weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$13);
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
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
		for(int t$var105 = (T - ((((T - 1) - 0) % 1) + 1)); t$var105 >= ((0 - 1) + 1); t$var105 -= 1) {
			int[] weekly_sales;
			weekly_sales = Sales[t$var105];
			int cv$multinomialSum148 = 0;
			for(int cv$multinomialIndex148 = 0; cv$multinomialIndex148 < weekly_sales.length; cv$multinomialIndex148 += 1)
				cv$multinomialSum148 = (weekly_sales[cv$multinomialIndex148] + cv$multinomialSum148);
			sales_sum[t$var105] = cv$multinomialSum148;
		}
	}

	@Override
	public final void setIntermediates() {
		for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
			if(fixedFlag$sample26)
				exped[j$var38] = Math.exp(ut[j$var38]);
		}
		double reduceVar$sum$8 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1) {
			double k$var49 = reduceVar$sum$8;
			double l$var50 = exped[cv$reduction46Index];
			if(fixedFlag$sample26)
				reduceVar$sum$8 = (k$var49 + l$var50);
		}
		if(fixedFlag$sample26)
			sum = reduceVar$sum$8;
		for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
			if(fixedFlag$sample26)
				expedNorm[j$var63] = (exped[j$var63] / (r * sum));
		}
		for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
			for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
				if(fixedFlag$sample26)
					weekly_ut[((t$var105 - 0) / 1)][j$var116] = (expedNorm[j$var116] * Avail[t$var105][j$var116]);
			}
			double reduceVar$denom$14 = 0.0;
			for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
				double k$var128 = reduceVar$denom$14;
				double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
				if(fixedFlag$sample26)
					reduceVar$denom$14 = (k$var128 + l$var129);
			}
			for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
				if(fixedFlag$sample26)
					weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$14);
			}
		}
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + "/*\n"
		     + " * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n"
		     + " * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n"
		     + " * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model Vulcano2012basic2(int noProducts, int T, int[][] ObsSales, int[][] Avail, double r) {\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = new double[noProducts];\n"
		     + "    ut[0] = 0.0;  //the first one is fixed so that we can normalize the sum to be equal 1/r\n"
		     + "    for(int j : [1..noProducts)) {\n"
		     + "        ut[j] = gaussian(0, 2).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    //exponentiate right here (in the non-basic models move to the for loop)\n"
		     + "    double[] exped = new double[noProducts];\n"
		     + "    for(int j : [0..noProducts)) {\n"
		     + "        exped[j] = exp(ut[j]);\n"
		     + "    }\n"
		     + "\n"
		     + "    double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n"
		     + "\n"
		     + "    //now normalize\n"
		     + "    double[] expedNorm = new double[noProducts];\n"
		     + "    for(int j : [0..noProducts)) {\n"
		     + "        expedNorm[j] = exped[j]/(r*sum);\n"
		     + "    }\n"
		     + "\n"
		     + "    int[] sales_sum = new int[T];\n"
		     + "    for (int t : [0..T)){\n"
		     + "        sales_sum[t] = poisson(0.5).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    int[][] Sales = new int[T][noProducts];\n"
		     + "\n"
		     + "    for (int t:[0..T)){\n"
		     + "        // for each period t calculate choice probabilities and sales\n"
		     + "\n"
		     + "        double[] weekly_rates = new double[noProducts];\n"
		     + "        double[] weekly_ut = new double[noProducts];\n"
		     + "\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            weekly_ut[j] = expedNorm[j]*Avail[t][j] ;\n"
		     + "        }\n"
		     + "        double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n"
		     + "\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            weekly_rates[j] = weekly_ut[j]/denom ;\n"
		     + "        }\n"
		     + "\n"
		     + "        int[] weekly_sales = multinomial(weekly_rates, sales_sum[t]).sample();\n"
		     + "\n"
		     + "        // record sales for period t\n"
		     + "        Sales[t] = weekly_sales;\n"
		     + "\n"
		     + "                                }\n"
		     + "    // assert that generated sales match observed sales\n"
		     + "    Sales.observe(ObsSales);\n"
		     + "}";
	}
}