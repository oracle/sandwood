package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Vulcano2012basic2$MultiThreadCPU extends CoreModelMultiThreadCPU implements Vulcano2012basic2$CoreInterface {
int[][] Avail;
	int[][] ObsSales;
	int[][] Sales;
	int T;
	boolean[] constrainedFlag$sample26;
	double[] exped;
	double[] expedNorm;
	boolean fixedFlag$sample26 = false;
	boolean fixedFlag$sample82 = false;
	boolean fixedProbFlag$sample149 = false;
	boolean fixedProbFlag$sample26 = false;
	boolean fixedProbFlag$sample82 = false;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$Sales;
	double logProbability$exped;
	double logProbability$expedNorm;
	double logProbability$sales_sum;
	double[] logProbability$sample149;
	double[] logProbability$sample26;
	double[] logProbability$sample82;
	double logProbability$sum;
	double logProbability$ut;
	int noProducts;
	double r;
	int[] sales_sum;
	double sum;
	boolean system$gibbsForward = true;
	double[] ut;
	double[][] weekly_rates;
	double[][] weekly_ut;
	boolean[] guard$sample26multinomial148$global;
	boolean[][] guard$sample26put123$global;
	boolean[][] guard$sample26put146$global;
	boolean[] guard$sample26put68$global;

	public Vulcano2012basic2$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final int[][] get$Avail() {
		return Avail;
	}

	@Override
	public final void set$Avail(int[][] cv$value, boolean allocated$) {
		Avail = cv$value;
	}

	@Override
	public final int[][] get$ObsSales() {
		return ObsSales;
	}

	@Override
	public final void set$ObsSales(int[][] cv$value, boolean allocated$) {
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
	public final void set$T(int cv$value, boolean allocated$) {
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
	public final void set$fixedFlag$sample26(boolean cv$value, boolean allocated$) {
		fixedFlag$sample26 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample26$1 = 0; index$constrainedFlag$sample26$1 < constrainedFlag$sample26.length; index$constrainedFlag$sample26$1 += 1)
				constrainedFlag$sample26[index$constrainedFlag$sample26$1] = true;
		}
		fixedProbFlag$sample26 = (fixedFlag$sample26 && fixedProbFlag$sample26);
		fixedProbFlag$sample149 = (fixedFlag$sample26 && fixedProbFlag$sample149);
	}

	@Override
	public final boolean get$fixedFlag$sample82() {
		return fixedFlag$sample82;
	}

	@Override
	public final void set$fixedFlag$sample82(boolean cv$value, boolean allocated$) {
		fixedFlag$sample82 = cv$value;
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
	public final void set$noProducts(int cv$value, boolean allocated$) {
		noProducts = cv$value;
	}

	@Override
	public final double get$r() {
		return r;
	}

	@Override
	public final void set$r(double cv$value, boolean allocated$) {
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
	public final void set$ut(double[] cv$value, boolean allocated$) {
		ut = cv$value;
		fixedProbFlag$sample26 = false;
		fixedProbFlag$sample149 = false;
	}

	private final void drawValueSample26(int j$var20) {
		ut[j$var20] = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		{
			{
				for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						{
							exped[j$var38] = Math.exp(ut[j$var38]);
						}
					}
				}
			}
		}
		{
			{
				for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						if(((0 <= j$var38) && (j$var38 < noProducts))) {
							{
								double reduceVar$sum$15 = 0.0;
								for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1) {
									double k$var49 = reduceVar$sum$15;
									double l$var50 = exped[cv$reduction46Index];
									reduceVar$sum$15 = (k$var49 + l$var50);
								}
								sum = reduceVar$sum$15;
							}
						}
					}
				}
			}
		}
		{
			boolean[] guard$sample26put68 = guard$sample26put68$global;
			{
				for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						if(((0 <= j$var38) && (j$var38 < noProducts))) {
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1)
								guard$sample26put68[((j$var63 - 0) / 1)] = false;
						}
					}
				}
			}
			{
				for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
							if((j$var38 == j$var63))
								guard$sample26put68[((j$var63 - 0) / 1)] = false;
						}
					}
				}
			}
			{
				for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						if(((0 <= j$var38) && (j$var38 < noProducts))) {
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
			{
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
		}
		{
			boolean[][] guard$sample26put123 = guard$sample26put123$global;
			{
				for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						if(((0 <= j$var38) && (j$var38 < noProducts))) {
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
			{
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
			}
			{
				for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						if(((0 <= j$var38) && (j$var38 < noProducts))) {
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
			{
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
		}
		{
			boolean[][] guard$sample26put146 = guard$sample26put146$global;
			{
				for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						if(((0 <= j$var38) && (j$var38 < noProducts))) {
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
								for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
									if((j$var63 == j$var116)) {
										for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
											if(((0 <= j$var116) && (j$var116 < noProducts))) {
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
			{
				for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						if(((0 <= j$var38) && (j$var38 < noProducts))) {
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
			{
				for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
							if((j$var38 == j$var63)) {
								for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
									if((j$var63 == j$var116)) {
										for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
											if(((0 <= j$var116) && (j$var116 < noProducts))) {
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
			{
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
			}
			{
				for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						if(((0 <= j$var38) && (j$var38 < noProducts))) {
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
								for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
									if((j$var63 == j$var116)) {
										for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
											if(((0 <= j$var116) && (j$var116 < noProducts))) {
												for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
													if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
														guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
														{
															double reduceVar$denom$30 = 0.0;
															for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
																double k$var128 = reduceVar$denom$30;
																double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																reduceVar$denom$30 = (k$var128 + l$var129);
															}
															weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$30);
														}
													}
												}
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
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
								for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
									if((j$var63 == j$var116)) {
										for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
											if((j$var116 == j$var140)) {
												for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
													if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
														guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
														{
															double reduceVar$denom$31 = 0.0;
															for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
																double k$var128 = reduceVar$denom$31;
																double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																reduceVar$denom$31 = (k$var128 + l$var129);
															}
															weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$31);
														}
													}
												}
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
						for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
							if((j$var38 == j$var63)) {
								for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
									if((j$var63 == j$var116)) {
										for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
											if(((0 <= j$var116) && (j$var116 < noProducts))) {
												for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
													if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
														guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
														{
															double reduceVar$denom$32 = 0.0;
															for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
																double k$var128 = reduceVar$denom$32;
																double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																reduceVar$denom$32 = (k$var128 + l$var129);
															}
															weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$32);
														}
													}
												}
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
															double reduceVar$denom$33 = 0.0;
															for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
																double k$var128 = reduceVar$denom$33;
																double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																reduceVar$denom$33 = (k$var128 + l$var129);
															}
															weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$33);
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void inferSample26(int j$var20) {
		if(true) {
			constrainedFlag$sample26[((j$var20 - 1) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = ut[j$var20];
			double cv$originalProbability = 0.0;
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((constrainedFlag$sample26[((j$var20 - 1) / 1)] || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var26 = cv$proposedValue;
						{
							{
								{
									ut[j$var20] = cv$currentValue;
								}
							}
						}
						{
							{
								for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										{
											exped[j$var38] = Math.exp(ut[j$var38]);
										}
									}
								}
							}
						}
						{
							{
								for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										if(((0 <= j$var38) && (j$var38 < noProducts))) {
											{
												double reduceVar$sum$11 = 0.0;
												for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1) {
													double k$var49 = reduceVar$sum$11;
													double l$var50 = exped[cv$reduction46Index];
													reduceVar$sum$11 = (k$var49 + l$var50);
												}
												sum = reduceVar$sum$11;
											}
										}
									}
								}
							}
						}
						{
							boolean[] guard$sample26put68 = guard$sample26put68$global;
							{
								for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										if(((0 <= j$var38) && (j$var38 < noProducts))) {
											for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1)
												guard$sample26put68[((j$var63 - 0) / 1)] = false;
										}
									}
								}
							}
							{
								for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
											if((j$var38 == j$var63))
												guard$sample26put68[((j$var63 - 0) / 1)] = false;
										}
									}
								}
							}
							{
								for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										if(((0 <= j$var38) && (j$var38 < noProducts))) {
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
							{
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
						}
						{
							boolean[][] guard$sample26put123 = guard$sample26put123$global;
							{
								for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										if(((0 <= j$var38) && (j$var38 < noProducts))) {
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
							{
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
							}
							{
								for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										if(((0 <= j$var38) && (j$var38 < noProducts))) {
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
							{
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
						}
						{
							boolean[][] guard$sample26put146 = guard$sample26put146$global;
							{
								for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										if(((0 <= j$var38) && (j$var38 < noProducts))) {
											for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
												for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
															if(((0 <= j$var116) && (j$var116 < noProducts))) {
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
							{
								for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										if(((0 <= j$var38) && (j$var38 < noProducts))) {
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
							{
								for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
											if((j$var38 == j$var63)) {
												for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
															if(((0 <= j$var116) && (j$var116 < noProducts))) {
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
							{
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
							}
							{
								for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										if(((0 <= j$var38) && (j$var38 < noProducts))) {
											for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
												for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
															if(((0 <= j$var116) && (j$var116 < noProducts))) {
																for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
																	if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
																		guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
																		{
																			double reduceVar$denom$20 = 0.0;
																			for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
																				double k$var128 = reduceVar$denom$20;
																				double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																				reduceVar$denom$20 = (k$var128 + l$var129);
																			}
																			weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$20);
																		}
																	}
																}
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
											for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
												for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
															if((j$var116 == j$var140)) {
																for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
																	if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
																		guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
																		{
																			double reduceVar$denom$21 = 0.0;
																			for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
																				double k$var128 = reduceVar$denom$21;
																				double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																				reduceVar$denom$21 = (k$var128 + l$var129);
																			}
																			weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$21);
																		}
																	}
																}
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
										for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
											if((j$var38 == j$var63)) {
												for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
													if((j$var63 == j$var116)) {
														for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
															if(((0 <= j$var116) && (j$var116 < noProducts))) {
																for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
																	if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
																		guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
																		{
																			double reduceVar$denom$22 = 0.0;
																			for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
																				double k$var128 = reduceVar$denom$22;
																				double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																				reduceVar$denom$22 = (k$var128 + l$var129);
																			}
																			weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$22);
																		}
																	}
																}
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
																			double reduceVar$denom$23 = 0.0;
																			for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
																				double k$var128 = reduceVar$denom$23;
																				double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																				reduceVar$denom$23 = (k$var128 + l$var129);
																			}
																			weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$23);
																		}
																	}
																}
															}
														}
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
						double cv$accumulatedProbabilities = (Math.log(1.0) + ((0.0 < 2.0)?(DistributionSampling.logProbabilityGaussian(((cv$currentValue - 0.0) / Math.sqrt(2.0))) - (0.5 * Math.log(2.0))):Double.NEGATIVE_INFINITY));
						{
							{
								boolean[] guard$sample26multinomial148 = guard$sample26multinomial148$global;
								{
									for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < noProducts))) {
												for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
													for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
																if(((0 <= j$var116) && (j$var116 < noProducts)))
																	guard$sample26multinomial148[((t$var105 - 0) / 1)] = false;
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
								{
									for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
												if((j$var38 == j$var63)) {
													for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
																if(((0 <= j$var116) && (j$var116 < noProducts)))
																	guard$sample26multinomial148[((t$var105 - 0) / 1)] = false;
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
								}
								{
									double traceTempVariable$var39$24_1 = cv$currentValue;
									for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											double traceTempVariable$k$24_3 = Math.exp(traceTempVariable$var39$24_1);
											if(((0 <= j$var38) && (j$var38 < noProducts))) {
												if((0 < noProducts)) {
													double reduceVar$sum$12 = 0.0;
													for(int cv$reduction2550Index = 0; cv$reduction2550Index < j$var38; cv$reduction2550Index += 1) {
														double k$var49 = reduceVar$sum$12;
														double l$var50 = exped[cv$reduction2550Index];
														reduceVar$sum$12 = (k$var49 + l$var50);
													}
													for(int cv$reduction2550Index = (j$var38 + 1); cv$reduction2550Index < noProducts; cv$reduction2550Index += 1) {
														double k$var49 = reduceVar$sum$12;
														double l$var50 = exped[cv$reduction2550Index];
														reduceVar$sum$12 = (k$var49 + l$var50);
													}
													double cv$reduced46 = reduceVar$sum$12;
													reduceVar$sum$12 = (traceTempVariable$k$24_3 + cv$reduced46);
													double traceTempVariable$sum$24_4 = reduceVar$sum$12;
													for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
														double traceTempVariable$sum$24_6 = traceTempVariable$sum$24_4;
														double traceTempVariable$var117$24_7 = (exped[j$var63] / (r * traceTempVariable$sum$24_6));
														for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
															if((j$var63 == j$var116)) {
																for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
																	double traceTempVariable$k$24_10 = (traceTempVariable$var117$24_7 * Avail[t$var105][j$var116]);
																	if(((0 <= j$var116) && (j$var116 < noProducts))) {
																		if((0 < noProducts)) {
																			double reduceVar$denom$24 = 0.0;
																			for(int cv$reduction2584Index = 0; cv$reduction2584Index < j$var116; cv$reduction2584Index += 1) {
																				double k$var128 = reduceVar$denom$24;
																				double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction2584Index];
																				reduceVar$denom$24 = (k$var128 + l$var129);
																			}
																			for(int cv$reduction2584Index = (j$var116 + 1); cv$reduction2584Index < noProducts; cv$reduction2584Index += 1) {
																				double k$var128 = reduceVar$denom$24;
																				double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction2584Index];
																				reduceVar$denom$24 = (k$var128 + l$var129);
																			}
																			double cv$reduced128 = reduceVar$denom$24;
																			reduceVar$denom$24 = (traceTempVariable$k$24_10 + cv$reduced128);
																			double traceTempVariable$denom$24_11 = reduceVar$denom$24;
																			if(!guard$sample26multinomial148[((t$var105 - 0) / 1)]) {
																				guard$sample26multinomial148[((t$var105 - 0) / 1)] = true;
																				{
																					{
																						boolean cv$sampleConstrained = true;
																						if(cv$sampleConstrained) {
																							constrainedFlag$sample26[((j$var20 - 1) / 1)] = true;
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									{
																										{
																											{
																												int var144 = sales_sum[t$var105];
																												if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[((t$var105 - 0) / 1)], noProducts, var144)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[((t$var105 - 0) / 1)], noProducts, var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[((t$var105 - 0) / 1)], noProducts, var144));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[((t$var105 - 0) / 1)], noProducts, var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[((t$var105 - 0) / 1)], noProducts, var144)));
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
								{
									double traceTempVariable$var39$25_1 = cv$currentValue;
									for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											double traceTempVariable$k$25_3 = Math.exp(traceTempVariable$var39$25_1);
											if(((0 <= j$var38) && (j$var38 < noProducts))) {
												if((0 < noProducts)) {
													double reduceVar$sum$13 = 0.0;
													for(int cv$reduction2612Index = 0; cv$reduction2612Index < j$var38; cv$reduction2612Index += 1) {
														double k$var49 = reduceVar$sum$13;
														double l$var50 = exped[cv$reduction2612Index];
														reduceVar$sum$13 = (k$var49 + l$var50);
													}
													for(int cv$reduction2612Index = (j$var38 + 1); cv$reduction2612Index < noProducts; cv$reduction2612Index += 1) {
														double k$var49 = reduceVar$sum$13;
														double l$var50 = exped[cv$reduction2612Index];
														reduceVar$sum$13 = (k$var49 + l$var50);
													}
													double cv$reduced46 = reduceVar$sum$13;
													reduceVar$sum$13 = (traceTempVariable$k$25_3 + cv$reduced46);
													double traceTempVariable$sum$25_4 = reduceVar$sum$13;
													for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
														double traceTempVariable$sum$25_6 = traceTempVariable$sum$25_4;
														double traceTempVariable$var117$25_7 = (exped[j$var63] / (r * traceTempVariable$sum$25_6));
														for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
															if((j$var63 == j$var116)) {
																for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
																	double traceTempVariable$var141$25_10 = (traceTempVariable$var117$25_7 * Avail[t$var105][j$var116]);
																	for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
																		if((j$var116 == j$var140)) {
																			if(!guard$sample26multinomial148[((t$var105 - 0) / 1)]) {
																				guard$sample26multinomial148[((t$var105 - 0) / 1)] = true;
																				{
																					{
																						boolean cv$sampleConstrained = true;
																						if(cv$sampleConstrained) {
																							constrainedFlag$sample26[((j$var20 - 1) / 1)] = true;
																							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																							double cv$consumerDistributionProbabilityAccumulator = 1.0;
																							{
																								{
																									{
																										{
																											{
																												int var144 = sales_sum[t$var105];
																												if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[((t$var105 - 0) / 1)], noProducts, var144)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[((t$var105 - 0) / 1)], noProducts, var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[((t$var105 - 0) / 1)], noProducts, var144));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[((t$var105 - 0) / 1)], noProducts, var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[((t$var105 - 0) / 1)], noProducts, var144)));
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
								{
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
																	if((0 < noProducts)) {
																		double reduceVar$denom$25 = 0.0;
																		for(int cv$reduction2668Index = 0; cv$reduction2668Index < j$var116; cv$reduction2668Index += 1) {
																			double k$var128 = reduceVar$denom$25;
																			double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction2668Index];
																			reduceVar$denom$25 = (k$var128 + l$var129);
																		}
																		for(int cv$reduction2668Index = (j$var116 + 1); cv$reduction2668Index < noProducts; cv$reduction2668Index += 1) {
																			double k$var128 = reduceVar$denom$25;
																			double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction2668Index];
																			reduceVar$denom$25 = (k$var128 + l$var129);
																		}
																		double cv$reduced128 = reduceVar$denom$25;
																		reduceVar$denom$25 = (traceTempVariable$k$26_8 + cv$reduced128);
																		double traceTempVariable$denom$26_9 = reduceVar$denom$25;
																		if(!guard$sample26multinomial148[((t$var105 - 0) / 1)]) {
																			guard$sample26multinomial148[((t$var105 - 0) / 1)] = true;
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample26[((j$var20 - 1) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										{
																											int var144 = sales_sum[t$var105];
																											if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[((t$var105 - 0) / 1)], noProducts, var144)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[((t$var105 - 0) / 1)], noProducts, var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[((t$var105 - 0) / 1)], noProducts, var144));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[((t$var105 - 0) / 1)], noProducts, var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[((t$var105 - 0) / 1)], noProducts, var144)));
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
								{
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
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample26[((j$var20 - 1) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								{
																									{
																										{
																											int var144 = sales_sum[t$var105];
																											if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[((t$var105 - 0) / 1)], noProducts, var144)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[((t$var105 - 0) / 1)], noProducts, var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[((t$var105 - 0) / 1)], noProducts, var144));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[((t$var105 - 0) / 1)], noProducts, var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var105], weekly_rates[((t$var105 - 0) / 1)], noProducts, var144)));
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
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
							double var26 = cv$originalValue;
							{
								{
									{
										ut[j$var20] = var26;
									}
								}
							}
							{
								{
									for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											{
												exped[j$var38] = Math.exp(ut[j$var38]);
											}
										}
									}
								}
							}
							{
								{
									for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < noProducts))) {
												{
													double reduceVar$sum$14 = 0.0;
													for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1) {
														double k$var49 = reduceVar$sum$14;
														double l$var50 = exped[cv$reduction46Index];
														reduceVar$sum$14 = (k$var49 + l$var50);
													}
													sum = reduceVar$sum$14;
												}
											}
										}
									}
								}
							}
							{
								boolean[] guard$sample26put68 = guard$sample26put68$global;
								{
									for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < noProducts))) {
												for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1)
													guard$sample26put68[((j$var63 - 0) / 1)] = false;
											}
										}
									}
								}
								{
									for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
												if((j$var38 == j$var63))
													guard$sample26put68[((j$var63 - 0) / 1)] = false;
											}
										}
									}
								}
								{
									for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < noProducts))) {
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
								{
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
							}
							{
								boolean[][] guard$sample26put123 = guard$sample26put123$global;
								{
									for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < noProducts))) {
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
								{
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
								}
								{
									for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < noProducts))) {
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
								{
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
							}
							{
								boolean[][] guard$sample26put146 = guard$sample26put146$global;
								{
									for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < noProducts))) {
												for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
													for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
																if(((0 <= j$var116) && (j$var116 < noProducts))) {
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
								{
									for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < noProducts))) {
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
								{
									for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
												if((j$var38 == j$var63)) {
													for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
																if(((0 <= j$var116) && (j$var116 < noProducts))) {
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
								{
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
								}
								{
									for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < noProducts))) {
												for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
													for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
																if(((0 <= j$var116) && (j$var116 < noProducts))) {
																	for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
																		if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
																			guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
																			{
																				double reduceVar$denom$26 = 0.0;
																				for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
																					double k$var128 = reduceVar$denom$26;
																					double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																					reduceVar$denom$26 = (k$var128 + l$var129);
																				}
																				weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$26);
																			}
																		}
																	}
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
												for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
													for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
																if((j$var116 == j$var140)) {
																	for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
																		if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
																			guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
																			{
																				double reduceVar$denom$27 = 0.0;
																				for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
																					double k$var128 = reduceVar$denom$27;
																					double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																					reduceVar$denom$27 = (k$var128 + l$var129);
																				}
																				weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$27);
																			}
																		}
																	}
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
											for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
												if((j$var38 == j$var63)) {
													for(int j$var116 = 0; j$var116 < noProducts; j$var116 += 1) {
														if((j$var63 == j$var116)) {
															for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
																if(((0 <= j$var116) && (j$var116 < noProducts))) {
																	for(int j$var140 = 0; j$var140 < noProducts; j$var140 += 1) {
																		if(!guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)]) {
																			guard$sample26put146[((t$var105 - 0) / 1)][((j$var140 - 0) / 1)] = true;
																			{
																				double reduceVar$denom$28 = 0.0;
																				for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
																					double k$var128 = reduceVar$denom$28;
																					double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																					reduceVar$denom$28 = (k$var128 + l$var129);
																				}
																				weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$28);
																			}
																		}
																	}
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
																				double reduceVar$denom$29 = 0.0;
																				for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
																					double k$var128 = reduceVar$denom$29;
																					double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
																					reduceVar$denom$29 = (k$var128 + l$var129);
																				}
																				weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$29);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample149() {
		if(!fixedProbFlag$sample149) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
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
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample149[((t$var105 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample149 = fixedFlag$sample26;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample149[((t$var105 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample26() {
		if(!fixedProbFlag$sample26) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = ut[j$var20];
						{
							{
								double var23 = 0.0;
								double var24 = 2.0;
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var24)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var23) / Math.sqrt(var24))) - (0.5 * Math.log(var24))):Double.NEGATIVE_INFINITY));
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
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample26[((j$var20 - 1) / 1)] = cv$sampleProbability;
				boolean cv$guard$exped = false;
				boolean cv$guard$sum = false;
				boolean cv$guard$expedNorm = false;
				{
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
				}
				{
					{
						for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
							if((j$var20 == j$var38)) {
								if(((0 <= j$var38) && (j$var38 < noProducts))) {
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
					{
						for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
							if((j$var20 == j$var38)) {
								if(((0 <= j$var38) && (j$var38 < noProducts))) {
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
					{
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
				}
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample26 = fixedFlag$sample26;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample26[((j$var20 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				boolean cv$guard$exped = false;
				boolean cv$guard$sum = false;
				boolean cv$guard$expedNorm = false;
				{
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
				}
				{
					{
						for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
							if((j$var20 == j$var38)) {
								if(((0 <= j$var38) && (j$var38 < noProducts))) {
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
					{
						for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
							if((j$var20 == j$var38)) {
								if(((0 <= j$var38) && (j$var38 < noProducts))) {
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
					{
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
			boolean cv$sampleReached = false;
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
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
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample82[((t$var78 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$Sales = false;
				{
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
			}
			logProbability$sales_sum = (logProbability$sales_sum + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample82 = true;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample82[((t$var78 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				boolean cv$guard$Sales = false;
				{
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
			}
			logProbability$sales_sum = (logProbability$sales_sum + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void allocate() {
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
			constrainedFlag$sample26 = new boolean[((((noProducts - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample26 = new double[((((noProducts - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample82 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample149 = new double[((((T - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
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
	public final void forwardGeneration() {
		parallelFor(RNG$, 1, noProducts, 1,
			(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1) {
						if(!fixedFlag$sample26)
							ut[j$var20] = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1) {
						if(!fixedFlag$sample26)
							exped[j$var38] = Math.exp(ut[j$var38]);
					}
			}
		);
		double reduceVar$sum$16 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1) {
			double k$var49 = reduceVar$sum$16;
			double l$var50 = exped[cv$reduction46Index];
			if(!fixedFlag$sample26)
				reduceVar$sum$16 = (k$var49 + l$var50);
		}
		if(!fixedFlag$sample26)
			sum = reduceVar$sum$16;
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1) {
						if(!fixedFlag$sample26)
							expedNorm[j$var63] = (exped[j$var63] / (r * sum));
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var78, int forEnd$t$var78, int threadID$t$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int t$var78 = forStart$t$var78; t$var78 < forEnd$t$var78; t$var78 += 1) {
						if(!fixedFlag$sample82)
							sales_sum[t$var78] = DistributionSampling.samplePoisson(RNG$1, 0.5);
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var105, int forEnd$index$t$var105, int threadID$index$t$var105, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var105 = forStart$index$t$var105; index$t$var105 < forEnd$index$t$var105; index$t$var105 += 1) {
						int t$var105 = index$t$var105;
						int threadID$t$var105 = threadID$index$t$var105;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var116, int forEnd$j$var116, int threadID$j$var116, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var116 = forStart$j$var116; j$var116 < forEnd$j$var116; j$var116 += 1) {
										if(!fixedFlag$sample26)
											weekly_ut[((t$var105 - 0) / 1)][j$var116] = (expedNorm[j$var116] * Avail[t$var105][j$var116]);
									}
							}
						);
						double reduceVar$denom$34 = 0.0;
						for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
							double k$var128 = reduceVar$denom$34;
							double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
							if(!fixedFlag$sample26)
								reduceVar$denom$34 = (k$var128 + l$var129);
						}
						double reduceVar$denom$34$1 = reduceVar$denom$34;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var140, int forEnd$j$var140, int threadID$j$var140, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var140 = forStart$j$var140; j$var140 < forEnd$j$var140; j$var140 += 1) {
										if(!fixedFlag$sample26)
											weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$34$1);
									}
							}
						);
						int[] weekly_sales = Sales[t$var105];
						DistributionSampling.sampleMultinomial(RNG$1, weekly_rates[((t$var105 - 0) / 1)], noProducts, sales_sum[t$var105], weekly_sales);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		parallelFor(RNG$, 1, noProducts, 1,
			(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1) {
						if(!fixedFlag$sample26)
							ut[j$var20] = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
						exped[j$var38] = Math.exp(ut[j$var38]);
			}
		);
		double reduceVar$sum$20 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1) {
			double k$var49 = reduceVar$sum$20;
			double l$var50 = exped[cv$reduction46Index];
			reduceVar$sum$20 = (k$var49 + l$var50);
		}
		sum = reduceVar$sum$20;
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
						expedNorm[j$var63] = (exped[j$var63] / (r * sum));
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var105, int forEnd$index$t$var105, int threadID$index$t$var105, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var105 = forStart$index$t$var105; index$t$var105 < forEnd$index$t$var105; index$t$var105 += 1) {
						int t$var105 = index$t$var105;
						int threadID$t$var105 = threadID$index$t$var105;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var116, int forEnd$j$var116, int threadID$j$var116, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var116 = forStart$j$var116; j$var116 < forEnd$j$var116; j$var116 += 1)
										weekly_ut[((t$var105 - 0) / 1)][j$var116] = (expedNorm[j$var116] * Avail[t$var105][j$var116]);
							}
						);
						double reduceVar$denom$38 = 0.0;
						for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
							double k$var128 = reduceVar$denom$38;
							double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
							reduceVar$denom$38 = (k$var128 + l$var129);
						}
						double reduceVar$denom$38$1 = reduceVar$denom$38;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var140, int forEnd$j$var140, int threadID$j$var140, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var140 = forStart$j$var140; j$var140 < forEnd$j$var140; j$var140 += 1)
										weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$38$1);
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		parallelFor(RNG$, 1, noProducts, 1,
			(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1) {
						if(!fixedFlag$sample26)
							ut[j$var20] = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
						exped[j$var38] = Math.exp(ut[j$var38]);
			}
		);
		double reduceVar$sum$17 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1) {
			double k$var49 = reduceVar$sum$17;
			double l$var50 = exped[cv$reduction46Index];
			reduceVar$sum$17 = (k$var49 + l$var50);
		}
		sum = reduceVar$sum$17;
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
						expedNorm[j$var63] = (exped[j$var63] / (r * sum));
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$t$var78, int forEnd$t$var78, int threadID$t$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int t$var78 = forStart$t$var78; t$var78 < forEnd$t$var78; t$var78 += 1) {
						if(!fixedFlag$sample82)
							sales_sum[t$var78] = DistributionSampling.samplePoisson(RNG$1, 0.5);
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var105, int forEnd$index$t$var105, int threadID$index$t$var105, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var105 = forStart$index$t$var105; index$t$var105 < forEnd$index$t$var105; index$t$var105 += 1) {
						int t$var105 = index$t$var105;
						int threadID$t$var105 = threadID$index$t$var105;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var116, int forEnd$j$var116, int threadID$j$var116, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var116 = forStart$j$var116; j$var116 < forEnd$j$var116; j$var116 += 1)
										weekly_ut[((t$var105 - 0) / 1)][j$var116] = (expedNorm[j$var116] * Avail[t$var105][j$var116]);
							}
						);
						double reduceVar$denom$35 = 0.0;
						for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
							double k$var128 = reduceVar$denom$35;
							double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
							reduceVar$denom$35 = (k$var128 + l$var129);
						}
						double reduceVar$denom$35$1 = reduceVar$denom$35;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var140, int forEnd$j$var140, int threadID$j$var140, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var140 = forStart$j$var140; j$var140 < forEnd$j$var140; j$var140 += 1)
										weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$35$1);
							}
						);
						int[] weekly_sales = Sales[t$var105];
						DistributionSampling.sampleMultinomial(RNG$1, weekly_rates[((t$var105 - 0) / 1)], noProducts, sales_sum[t$var105], weekly_sales);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 1, noProducts, 1,
			(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1) {
						if(!fixedFlag$sample26)
							ut[j$var20] = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1) {
						if(!fixedFlag$sample26)
							exped[j$var38] = Math.exp(ut[j$var38]);
					}
			}
		);
		double reduceVar$sum$18 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1) {
			double k$var49 = reduceVar$sum$18;
			double l$var50 = exped[cv$reduction46Index];
			if(!fixedFlag$sample26)
				reduceVar$sum$18 = (k$var49 + l$var50);
		}
		if(!fixedFlag$sample26)
			sum = reduceVar$sum$18;
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1) {
						if(!fixedFlag$sample26)
							expedNorm[j$var63] = (exped[j$var63] / (r * sum));
					}
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var105, int forEnd$index$t$var105, int threadID$index$t$var105, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var105 = forStart$index$t$var105; index$t$var105 < forEnd$index$t$var105; index$t$var105 += 1) {
						int t$var105 = index$t$var105;
						int threadID$t$var105 = threadID$index$t$var105;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var116, int forEnd$j$var116, int threadID$j$var116, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var116 = forStart$j$var116; j$var116 < forEnd$j$var116; j$var116 += 1) {
										if(!fixedFlag$sample26)
											weekly_ut[((t$var105 - 0) / 1)][j$var116] = (expedNorm[j$var116] * Avail[t$var105][j$var116]);
									}
							}
						);
						double reduceVar$denom$36 = 0.0;
						for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
							double k$var128 = reduceVar$denom$36;
							double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
							if(!fixedFlag$sample26)
								reduceVar$denom$36 = (k$var128 + l$var129);
						}
						double reduceVar$denom$36$1 = reduceVar$denom$36;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var140, int forEnd$j$var140, int threadID$j$var140, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var140 = forStart$j$var140; j$var140 < forEnd$j$var140; j$var140 += 1) {
										if(!fixedFlag$sample26)
											weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$36$1);
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		parallelFor(RNG$, 1, noProducts, 1,
			(int forStart$j$var20, int forEnd$j$var20, int threadID$j$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var20 = forStart$j$var20; j$var20 < forEnd$j$var20; j$var20 += 1) {
						if(!fixedFlag$sample26)
							ut[j$var20] = ((Math.sqrt(2.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
						exped[j$var38] = Math.exp(ut[j$var38]);
			}
		);
		double reduceVar$sum$19 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1) {
			double k$var49 = reduceVar$sum$19;
			double l$var50 = exped[cv$reduction46Index];
			reduceVar$sum$19 = (k$var49 + l$var50);
		}
		sum = reduceVar$sum$19;
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
						expedNorm[j$var63] = (exped[j$var63] / (r * sum));
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var105, int forEnd$index$t$var105, int threadID$index$t$var105, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var105 = forStart$index$t$var105; index$t$var105 < forEnd$index$t$var105; index$t$var105 += 1) {
						int t$var105 = index$t$var105;
						int threadID$t$var105 = threadID$index$t$var105;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var116, int forEnd$j$var116, int threadID$j$var116, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var116 = forStart$j$var116; j$var116 < forEnd$j$var116; j$var116 += 1)
										weekly_ut[((t$var105 - 0) / 1)][j$var116] = (expedNorm[j$var116] * Avail[t$var105][j$var116]);
							}
						);
						double reduceVar$denom$37 = 0.0;
						for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
							double k$var128 = reduceVar$denom$37;
							double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
							reduceVar$denom$37 = (k$var128 + l$var129);
						}
						double reduceVar$denom$37$1 = reduceVar$denom$37;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var140, int forEnd$j$var140, int threadID$j$var140, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var140 = forStart$j$var140; j$var140 < forEnd$j$var140; j$var140 += 1)
										weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$37$1);
							}
						);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1) {
				if(!fixedFlag$sample26)
					inferSample26(j$var20);
			}
		} else {
			for(int j$var20 = (noProducts - ((((noProducts - 1) - 1) % 1) + 1)); j$var20 >= ((1 - 1) + 1); j$var20 -= 1) {
				if(!fixedFlag$sample26)
					inferSample26(j$var20);
			}
		}
		system$gibbsForward = !system$gibbsForward;
		for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1) {
			if(!constrainedFlag$sample26[((j$var20 - 1) / 1)])
				drawValueSample26(j$var20);
		}
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$sum = 0.0;
		logProbability$expedNorm = 0.0;
		if(!fixedProbFlag$sample26) {
			for(int j$var20 = 1; j$var20 < noProducts; j$var20 += 1)
				logProbability$sample26[((j$var20 - 1) / 1)] = Double.NaN;
		}
		logProbability$sales_sum = 0.0;
		logProbability$Sales = 0.0;
		if(!fixedProbFlag$sample82) {
			for(int t$var78 = 0; t$var78 < T; t$var78 += 1)
				logProbability$sample82[((t$var78 - 0) / 1)] = Double.NaN;
		}
		if(!fixedProbFlag$sample149) {
			for(int t$var105 = 0; t$var105 < T; t$var105 += 1)
				logProbability$sample149[((t$var105 - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		ut[0] = 0.0;
		for(int index$constrainedFlag$sample26$1 = 0; index$constrainedFlag$sample26$1 < constrainedFlag$sample26.length; index$constrainedFlag$sample26$1 += 1)
			constrainedFlag$sample26[index$constrainedFlag$sample26$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
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
	public final void propagateObservedValues() {
		fixedFlag$sample82 = false;
		{
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
	}

	@Override
	public final void setIntermediates() {
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var38, int forEnd$j$var38, int threadID$j$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var38 = forStart$j$var38; j$var38 < forEnd$j$var38; j$var38 += 1)
						exped[j$var38] = Math.exp(ut[j$var38]);
			}
		);
		double reduceVar$sum$21 = 0.0;
		for(int cv$reduction46Index = 0; cv$reduction46Index < noProducts; cv$reduction46Index += 1) {
			double k$var49 = reduceVar$sum$21;
			double l$var50 = exped[cv$reduction46Index];
			reduceVar$sum$21 = (k$var49 + l$var50);
		}
		sum = reduceVar$sum$21;
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$j$var63, int forEnd$j$var63, int threadID$j$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var63 = forStart$j$var63; j$var63 < forEnd$j$var63; j$var63 += 1)
						expedNorm[j$var63] = (exped[j$var63] / (r * sum));
			}
		);
		parallelFor(RNG$, 0, T, 1,
			(int forStart$index$t$var105, int forEnd$index$t$var105, int threadID$index$t$var105, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var105 = forStart$index$t$var105; index$t$var105 < forEnd$index$t$var105; index$t$var105 += 1) {
						int t$var105 = index$t$var105;
						int threadID$t$var105 = threadID$index$t$var105;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var116, int forEnd$j$var116, int threadID$j$var116, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var116 = forStart$j$var116; j$var116 < forEnd$j$var116; j$var116 += 1)
										weekly_ut[((t$var105 - 0) / 1)][j$var116] = (expedNorm[j$var116] * Avail[t$var105][j$var116]);
							}
						);
						double reduceVar$denom$39 = 0.0;
						for(int cv$reduction128Index = 0; cv$reduction128Index < noProducts; cv$reduction128Index += 1) {
							double k$var128 = reduceVar$denom$39;
							double l$var129 = weekly_ut[((t$var105 - 0) / 1)][cv$reduction128Index];
							reduceVar$denom$39 = (k$var128 + l$var129);
						}
						double reduceVar$denom$39$1 = reduceVar$denom$39;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var140, int forEnd$j$var140, int threadID$j$var140, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var140 = forStart$j$var140; j$var140 < forEnd$j$var140; j$var140 += 1)
										weekly_rates[((t$var105 - 0) / 1)][j$var140] = (weekly_ut[((t$var105 - 0) / 1)][j$var140] / reduceVar$denom$39$1);
							}
						);
					}
			}
		);
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