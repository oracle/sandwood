package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Vulcano2012basic$MultiThreadCPU extends CoreModelMultiThreadCPU implements Vulcano2012basic$CoreInterface {
int[][] Avail;
	int[][] ObsSales;
	int[][] Sales;
	int T;
	boolean[] constrainedFlag$sample26;
	double[] exped;
	double[] expedNorm;
	boolean fixedFlag$sample26 = false;
	boolean fixedProbFlag$sample157 = false;
	boolean fixedProbFlag$sample26 = false;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$Sales;
	double logProbability$exped;
	double logProbability$expedNorm;
	double[] logProbability$sample157;
	double[] logProbability$sample26;
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
	boolean[] guard$sample26multinomial156$global;
	boolean[][] guard$sample26put131$global;
	boolean[][] guard$sample26put154$global;
	boolean[] guard$sample26put68$global;

	public Vulcano2012basic$MultiThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample157 = (fixedFlag$sample26 && fixedProbFlag$sample157);
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
		fixedProbFlag$sample157 = false;
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
			boolean[][] guard$sample26put131 = guard$sample26put131$global;
			{
				for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						if(((0 <= j$var38) && (j$var38 < noProducts))) {
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
								for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
									if((j$var63 == j$var123)) {
										for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
											guard$sample26put131[((t$var112 - 0) / 1)][((j$var123 - 0) / 1)] = false;
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
								for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
									if((j$var63 == j$var123)) {
										for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
											guard$sample26put131[((t$var112 - 0) / 1)][((j$var123 - 0) / 1)] = false;
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
								for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
									if((j$var63 == j$var123)) {
										for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
											if(!guard$sample26put131[((t$var112 - 0) / 1)][((j$var123 - 0) / 1)]) {
												guard$sample26put131[((t$var112 - 0) / 1)][((j$var123 - 0) / 1)] = true;
												{
													weekly_ut[((t$var112 - 0) / 1)][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
												}
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
								for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
									if((j$var63 == j$var123)) {
										for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
											if(!guard$sample26put131[((t$var112 - 0) / 1)][((j$var123 - 0) / 1)]) {
												guard$sample26put131[((t$var112 - 0) / 1)][((j$var123 - 0) / 1)] = true;
												{
													weekly_ut[((t$var112 - 0) / 1)][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
												}
											}
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
			boolean[][] guard$sample26put154 = guard$sample26put154$global;
			{
				for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
					if((j$var20 == j$var38)) {
						if(((0 <= j$var38) && (j$var38 < noProducts))) {
							for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
								for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
									if((j$var63 == j$var123)) {
										for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
											if(((0 <= j$var123) && (j$var123 < noProducts))) {
												for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1)
													guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = false;
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
								for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
									if((j$var63 == j$var123)) {
										for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
											if((j$var123 == j$var147)) {
												for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
													guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = false;
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
								for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
									if((j$var63 == j$var123)) {
										for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
											if(((0 <= j$var123) && (j$var123 < noProducts))) {
												for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1)
													guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = false;
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
								for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
									if((j$var63 == j$var123)) {
										for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
											if((j$var123 == j$var147)) {
												for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
													guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = false;
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
								for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
									if((j$var63 == j$var123)) {
										for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
											if(((0 <= j$var123) && (j$var123 < noProducts))) {
												for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
													if(!guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)]) {
														guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = true;
														{
															double reduceVar$denom$30 = 0.0;
															for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1) {
																double k$var135 = reduceVar$denom$30;
																double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction136Index];
																reduceVar$denom$30 = (k$var135 + l$var136);
															}
															weekly_rates[((t$var112 - 0) / 1)][j$var147] = (weekly_ut[((t$var112 - 0) / 1)][j$var147] / reduceVar$denom$30);
														}
													}
												}
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
								for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
									if((j$var63 == j$var123)) {
										for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
											if((j$var123 == j$var147)) {
												for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
													if(!guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)]) {
														guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = true;
														{
															double reduceVar$denom$31 = 0.0;
															for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1) {
																double k$var135 = reduceVar$denom$31;
																double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction136Index];
																reduceVar$denom$31 = (k$var135 + l$var136);
															}
															weekly_rates[((t$var112 - 0) / 1)][j$var147] = (weekly_ut[((t$var112 - 0) / 1)][j$var147] / reduceVar$denom$31);
														}
													}
												}
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
								for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
									if((j$var63 == j$var123)) {
										for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
											if(((0 <= j$var123) && (j$var123 < noProducts))) {
												for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
													if(!guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)]) {
														guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = true;
														{
															double reduceVar$denom$32 = 0.0;
															for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1) {
																double k$var135 = reduceVar$denom$32;
																double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction136Index];
																reduceVar$denom$32 = (k$var135 + l$var136);
															}
															weekly_rates[((t$var112 - 0) / 1)][j$var147] = (weekly_ut[((t$var112 - 0) / 1)][j$var147] / reduceVar$denom$32);
														}
													}
												}
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
								for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
									if((j$var63 == j$var123)) {
										for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
											if((j$var123 == j$var147)) {
												for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
													if(!guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)]) {
														guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = true;
														{
															double reduceVar$denom$33 = 0.0;
															for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1) {
																double k$var135 = reduceVar$denom$33;
																double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction136Index];
																reduceVar$denom$33 = (k$var135 + l$var136);
															}
															weekly_rates[((t$var112 - 0) / 1)][j$var147] = (weekly_ut[((t$var112 - 0) / 1)][j$var147] / reduceVar$denom$33);
														}
													}
												}
											}
										}
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
							boolean[][] guard$sample26put131 = guard$sample26put131$global;
							{
								for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										if(((0 <= j$var38) && (j$var38 < noProducts))) {
											for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
												for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
													if((j$var63 == j$var123)) {
														for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
															guard$sample26put131[((t$var112 - 0) / 1)][((j$var123 - 0) / 1)] = false;
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
												for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
													if((j$var63 == j$var123)) {
														for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
															guard$sample26put131[((t$var112 - 0) / 1)][((j$var123 - 0) / 1)] = false;
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
												for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
													if((j$var63 == j$var123)) {
														for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
															if(!guard$sample26put131[((t$var112 - 0) / 1)][((j$var123 - 0) / 1)]) {
																guard$sample26put131[((t$var112 - 0) / 1)][((j$var123 - 0) / 1)] = true;
																{
																	weekly_ut[((t$var112 - 0) / 1)][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
																}
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
												for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
													if((j$var63 == j$var123)) {
														for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
															if(!guard$sample26put131[((t$var112 - 0) / 1)][((j$var123 - 0) / 1)]) {
																guard$sample26put131[((t$var112 - 0) / 1)][((j$var123 - 0) / 1)] = true;
																{
																	weekly_ut[((t$var112 - 0) / 1)][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
																}
															}
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
							boolean[][] guard$sample26put154 = guard$sample26put154$global;
							{
								for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
									if((j$var20 == j$var38)) {
										if(((0 <= j$var38) && (j$var38 < noProducts))) {
											for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
												for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
													if((j$var63 == j$var123)) {
														for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
															if(((0 <= j$var123) && (j$var123 < noProducts))) {
																for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1)
																	guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = false;
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
												for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
													if((j$var63 == j$var123)) {
														for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
															if((j$var123 == j$var147)) {
																for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
																	guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = false;
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
												for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
													if((j$var63 == j$var123)) {
														for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
															if(((0 <= j$var123) && (j$var123 < noProducts))) {
																for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1)
																	guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = false;
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
												for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
													if((j$var63 == j$var123)) {
														for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
															if((j$var123 == j$var147)) {
																for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
																	guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = false;
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
												for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
													if((j$var63 == j$var123)) {
														for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
															if(((0 <= j$var123) && (j$var123 < noProducts))) {
																for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
																	if(!guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)]) {
																		guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = true;
																		{
																			double reduceVar$denom$20 = 0.0;
																			for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1) {
																				double k$var135 = reduceVar$denom$20;
																				double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction136Index];
																				reduceVar$denom$20 = (k$var135 + l$var136);
																			}
																			weekly_rates[((t$var112 - 0) / 1)][j$var147] = (weekly_ut[((t$var112 - 0) / 1)][j$var147] / reduceVar$denom$20);
																		}
																	}
																}
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
												for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
													if((j$var63 == j$var123)) {
														for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
															if((j$var123 == j$var147)) {
																for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
																	if(!guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)]) {
																		guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = true;
																		{
																			double reduceVar$denom$21 = 0.0;
																			for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1) {
																				double k$var135 = reduceVar$denom$21;
																				double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction136Index];
																				reduceVar$denom$21 = (k$var135 + l$var136);
																			}
																			weekly_rates[((t$var112 - 0) / 1)][j$var147] = (weekly_ut[((t$var112 - 0) / 1)][j$var147] / reduceVar$denom$21);
																		}
																	}
																}
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
												for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
													if((j$var63 == j$var123)) {
														for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
															if(((0 <= j$var123) && (j$var123 < noProducts))) {
																for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
																	if(!guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)]) {
																		guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = true;
																		{
																			double reduceVar$denom$22 = 0.0;
																			for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1) {
																				double k$var135 = reduceVar$denom$22;
																				double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction136Index];
																				reduceVar$denom$22 = (k$var135 + l$var136);
																			}
																			weekly_rates[((t$var112 - 0) / 1)][j$var147] = (weekly_ut[((t$var112 - 0) / 1)][j$var147] / reduceVar$denom$22);
																		}
																	}
																}
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
												for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
													if((j$var63 == j$var123)) {
														for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
															if((j$var123 == j$var147)) {
																for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
																	if(!guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)]) {
																		guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = true;
																		{
																			double reduceVar$denom$23 = 0.0;
																			for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1) {
																				double k$var135 = reduceVar$denom$23;
																				double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction136Index];
																				reduceVar$denom$23 = (k$var135 + l$var136);
																			}
																			weekly_rates[((t$var112 - 0) / 1)][j$var147] = (weekly_ut[((t$var112 - 0) / 1)][j$var147] / reduceVar$denom$23);
																		}
																	}
																}
															}
														}
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
								boolean[] guard$sample26multinomial156 = guard$sample26multinomial156$global;
								{
									for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < noProducts))) {
												for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
													for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
														if((j$var63 == j$var123)) {
															for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
																if(((0 <= j$var123) && (j$var123 < noProducts)))
																	guard$sample26multinomial156[((t$var112 - 0) / 1)] = false;
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
													for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
														if((j$var63 == j$var123)) {
															for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
																if((j$var123 == j$var147)) {
																	for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
																		guard$sample26multinomial156[((t$var112 - 0) / 1)] = false;
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
													for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
														if((j$var63 == j$var123)) {
															for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
																if(((0 <= j$var123) && (j$var123 < noProducts)))
																	guard$sample26multinomial156[((t$var112 - 0) / 1)] = false;
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
													for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
														if((j$var63 == j$var123)) {
															for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
																if((j$var123 == j$var147)) {
																	for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
																		guard$sample26multinomial156[((t$var112 - 0) / 1)] = false;
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
													for(int cv$reduction2508Index = 0; cv$reduction2508Index < j$var38; cv$reduction2508Index += 1) {
														double k$var49 = reduceVar$sum$12;
														double l$var50 = exped[cv$reduction2508Index];
														reduceVar$sum$12 = (k$var49 + l$var50);
													}
													for(int cv$reduction2508Index = (j$var38 + 1); cv$reduction2508Index < noProducts; cv$reduction2508Index += 1) {
														double k$var49 = reduceVar$sum$12;
														double l$var50 = exped[cv$reduction2508Index];
														reduceVar$sum$12 = (k$var49 + l$var50);
													}
													double cv$reduced46 = reduceVar$sum$12;
													reduceVar$sum$12 = (traceTempVariable$k$24_3 + cv$reduced46);
													double traceTempVariable$sum$24_4 = reduceVar$sum$12;
													for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
														double traceTempVariable$sum$24_6 = traceTempVariable$sum$24_4;
														double traceTempVariable$var124$24_7 = (exped[j$var63] / (r * traceTempVariable$sum$24_6));
														for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
															if((j$var63 == j$var123)) {
																for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
																	double traceTempVariable$k$24_10 = (traceTempVariable$var124$24_7 * Avail[t$var112][j$var123]);
																	if(((0 <= j$var123) && (j$var123 < noProducts))) {
																		if((0 < noProducts)) {
																			double reduceVar$denom$24 = 0.0;
																			for(int cv$reduction2542Index = 0; cv$reduction2542Index < j$var123; cv$reduction2542Index += 1) {
																				double k$var135 = reduceVar$denom$24;
																				double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction2542Index];
																				reduceVar$denom$24 = (k$var135 + l$var136);
																			}
																			for(int cv$reduction2542Index = (j$var123 + 1); cv$reduction2542Index < noProducts; cv$reduction2542Index += 1) {
																				double k$var135 = reduceVar$denom$24;
																				double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction2542Index];
																				reduceVar$denom$24 = (k$var135 + l$var136);
																			}
																			double cv$reduced136 = reduceVar$denom$24;
																			reduceVar$denom$24 = (traceTempVariable$k$24_10 + cv$reduced136);
																			double traceTempVariable$denom$24_11 = reduceVar$denom$24;
																			if(!guard$sample26multinomial156[((t$var112 - 0) / 1)]) {
																				guard$sample26multinomial156[((t$var112 - 0) / 1)] = true;
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
																												int var151 = sales_sum[t$var112];
																												if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[((t$var112 - 0) / 1)], noProducts, var151)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[((t$var112 - 0) / 1)], noProducts, var151)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[((t$var112 - 0) / 1)], noProducts, var151));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[((t$var112 - 0) / 1)], noProducts, var151)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[((t$var112 - 0) / 1)], noProducts, var151)));
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
													for(int cv$reduction2570Index = 0; cv$reduction2570Index < j$var38; cv$reduction2570Index += 1) {
														double k$var49 = reduceVar$sum$13;
														double l$var50 = exped[cv$reduction2570Index];
														reduceVar$sum$13 = (k$var49 + l$var50);
													}
													for(int cv$reduction2570Index = (j$var38 + 1); cv$reduction2570Index < noProducts; cv$reduction2570Index += 1) {
														double k$var49 = reduceVar$sum$13;
														double l$var50 = exped[cv$reduction2570Index];
														reduceVar$sum$13 = (k$var49 + l$var50);
													}
													double cv$reduced46 = reduceVar$sum$13;
													reduceVar$sum$13 = (traceTempVariable$k$25_3 + cv$reduced46);
													double traceTempVariable$sum$25_4 = reduceVar$sum$13;
													for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
														double traceTempVariable$sum$25_6 = traceTempVariable$sum$25_4;
														double traceTempVariable$var124$25_7 = (exped[j$var63] / (r * traceTempVariable$sum$25_6));
														for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
															if((j$var63 == j$var123)) {
																for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
																	double traceTempVariable$var148$25_10 = (traceTempVariable$var124$25_7 * Avail[t$var112][j$var123]);
																	for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
																		if((j$var123 == j$var147)) {
																			if(!guard$sample26multinomial156[((t$var112 - 0) / 1)]) {
																				guard$sample26multinomial156[((t$var112 - 0) / 1)] = true;
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
																												int var151 = sales_sum[t$var112];
																												if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[((t$var112 - 0) / 1)], noProducts, var151)) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[((t$var112 - 0) / 1)], noProducts, var151)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[((t$var112 - 0) / 1)], noProducts, var151));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[((t$var112 - 0) / 1)], noProducts, var151)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[((t$var112 - 0) / 1)], noProducts, var151)));
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
													double traceTempVariable$var124$26_5 = (traceTempVariable$var64$26_3 / (r * sum));
													for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
														if((j$var63 == j$var123)) {
															for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
																double traceTempVariable$k$26_8 = (traceTempVariable$var124$26_5 * Avail[t$var112][j$var123]);
																if(((0 <= j$var123) && (j$var123 < noProducts))) {
																	if((0 < noProducts)) {
																		double reduceVar$denom$25 = 0.0;
																		for(int cv$reduction2626Index = 0; cv$reduction2626Index < j$var123; cv$reduction2626Index += 1) {
																			double k$var135 = reduceVar$denom$25;
																			double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction2626Index];
																			reduceVar$denom$25 = (k$var135 + l$var136);
																		}
																		for(int cv$reduction2626Index = (j$var123 + 1); cv$reduction2626Index < noProducts; cv$reduction2626Index += 1) {
																			double k$var135 = reduceVar$denom$25;
																			double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction2626Index];
																			reduceVar$denom$25 = (k$var135 + l$var136);
																		}
																		double cv$reduced136 = reduceVar$denom$25;
																		reduceVar$denom$25 = (traceTempVariable$k$26_8 + cv$reduced136);
																		double traceTempVariable$denom$26_9 = reduceVar$denom$25;
																		if(!guard$sample26multinomial156[((t$var112 - 0) / 1)]) {
																			guard$sample26multinomial156[((t$var112 - 0) / 1)] = true;
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
																											int var151 = sales_sum[t$var112];
																											if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[((t$var112 - 0) / 1)], noProducts, var151)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[((t$var112 - 0) / 1)], noProducts, var151)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[((t$var112 - 0) / 1)], noProducts, var151));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[((t$var112 - 0) / 1)], noProducts, var151)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[((t$var112 - 0) / 1)], noProducts, var151)));
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
													double traceTempVariable$var124$27_5 = (traceTempVariable$var64$27_3 / (r * sum));
													for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
														if((j$var63 == j$var123)) {
															for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
																double traceTempVariable$var148$27_8 = (traceTempVariable$var124$27_5 * Avail[t$var112][j$var123]);
																for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
																	if((j$var123 == j$var147)) {
																		if(!guard$sample26multinomial156[((t$var112 - 0) / 1)]) {
																			guard$sample26multinomial156[((t$var112 - 0) / 1)] = true;
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
																											int var151 = sales_sum[t$var112];
																											if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[((t$var112 - 0) / 1)], noProducts, var151)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[((t$var112 - 0) / 1)], noProducts, var151)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[((t$var112 - 0) / 1)], noProducts, var151));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[((t$var112 - 0) / 1)], noProducts, var151)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(Sales[t$var112], weekly_rates[((t$var112 - 0) / 1)], noProducts, var151)));
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
								boolean[][] guard$sample26put131 = guard$sample26put131$global;
								{
									for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < noProducts))) {
												for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
													for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
														if((j$var63 == j$var123)) {
															for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
																guard$sample26put131[((t$var112 - 0) / 1)][((j$var123 - 0) / 1)] = false;
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
													for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
														if((j$var63 == j$var123)) {
															for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
																guard$sample26put131[((t$var112 - 0) / 1)][((j$var123 - 0) / 1)] = false;
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
													for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
														if((j$var63 == j$var123)) {
															for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
																if(!guard$sample26put131[((t$var112 - 0) / 1)][((j$var123 - 0) / 1)]) {
																	guard$sample26put131[((t$var112 - 0) / 1)][((j$var123 - 0) / 1)] = true;
																	{
																		weekly_ut[((t$var112 - 0) / 1)][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
																	}
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
													for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
														if((j$var63 == j$var123)) {
															for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
																if(!guard$sample26put131[((t$var112 - 0) / 1)][((j$var123 - 0) / 1)]) {
																	guard$sample26put131[((t$var112 - 0) / 1)][((j$var123 - 0) / 1)] = true;
																	{
																		weekly_ut[((t$var112 - 0) / 1)][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
																	}
																}
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
								boolean[][] guard$sample26put154 = guard$sample26put154$global;
								{
									for(int j$var38 = 0; j$var38 < noProducts; j$var38 += 1) {
										if((j$var20 == j$var38)) {
											if(((0 <= j$var38) && (j$var38 < noProducts))) {
												for(int j$var63 = 0; j$var63 < noProducts; j$var63 += 1) {
													for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
														if((j$var63 == j$var123)) {
															for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
																if(((0 <= j$var123) && (j$var123 < noProducts))) {
																	for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1)
																		guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = false;
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
													for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
														if((j$var63 == j$var123)) {
															for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
																if((j$var123 == j$var147)) {
																	for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
																		guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = false;
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
													for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
														if((j$var63 == j$var123)) {
															for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
																if(((0 <= j$var123) && (j$var123 < noProducts))) {
																	for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1)
																		guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = false;
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
													for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
														if((j$var63 == j$var123)) {
															for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
																if((j$var123 == j$var147)) {
																	for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
																		guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = false;
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
													for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
														if((j$var63 == j$var123)) {
															for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
																if(((0 <= j$var123) && (j$var123 < noProducts))) {
																	for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
																		if(!guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)]) {
																			guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = true;
																			{
																				double reduceVar$denom$26 = 0.0;
																				for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1) {
																					double k$var135 = reduceVar$denom$26;
																					double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction136Index];
																					reduceVar$denom$26 = (k$var135 + l$var136);
																				}
																				weekly_rates[((t$var112 - 0) / 1)][j$var147] = (weekly_ut[((t$var112 - 0) / 1)][j$var147] / reduceVar$denom$26);
																			}
																		}
																	}
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
													for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
														if((j$var63 == j$var123)) {
															for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
																if((j$var123 == j$var147)) {
																	for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
																		if(!guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)]) {
																			guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = true;
																			{
																				double reduceVar$denom$27 = 0.0;
																				for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1) {
																					double k$var135 = reduceVar$denom$27;
																					double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction136Index];
																					reduceVar$denom$27 = (k$var135 + l$var136);
																				}
																				weekly_rates[((t$var112 - 0) / 1)][j$var147] = (weekly_ut[((t$var112 - 0) / 1)][j$var147] / reduceVar$denom$27);
																			}
																		}
																	}
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
													for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
														if((j$var63 == j$var123)) {
															for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
																if(((0 <= j$var123) && (j$var123 < noProducts))) {
																	for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
																		if(!guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)]) {
																			guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = true;
																			{
																				double reduceVar$denom$28 = 0.0;
																				for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1) {
																					double k$var135 = reduceVar$denom$28;
																					double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction136Index];
																					reduceVar$denom$28 = (k$var135 + l$var136);
																				}
																				weekly_rates[((t$var112 - 0) / 1)][j$var147] = (weekly_ut[((t$var112 - 0) / 1)][j$var147] / reduceVar$denom$28);
																			}
																		}
																	}
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
													for(int j$var123 = 0; j$var123 < noProducts; j$var123 += 1) {
														if((j$var63 == j$var123)) {
															for(int j$var147 = 0; j$var147 < noProducts; j$var147 += 1) {
																if((j$var123 == j$var147)) {
																	for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
																		if(!guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)]) {
																			guard$sample26put154[((t$var112 - 0) / 1)][((j$var147 - 0) / 1)] = true;
																			{
																				double reduceVar$denom$29 = 0.0;
																				for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1) {
																					double k$var135 = reduceVar$denom$29;
																					double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction136Index];
																					reduceVar$denom$29 = (k$var135 + l$var136);
																				}
																				weekly_rates[((t$var112 - 0) / 1)][j$var147] = (weekly_ut[((t$var112 - 0) / 1)][j$var147] / reduceVar$denom$29);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample157() {
		if(!fixedProbFlag$sample157) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int[] cv$sampleValue = Sales[t$var112];
						{
							{
								int var151 = sales_sum[t$var112];
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(cv$sampleValue, weekly_rates[((t$var112 - 0) / 1)], noProducts, var151));
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
				logProbability$sample157[((t$var112 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$Sales = (logProbability$Sales + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample157 = fixedFlag$sample26;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample157[((t$var112 - 0) / 1)];
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
			for(int var100 = 0; var100 < T; var100 += 1)
				Sales[var100] = new int[noProducts];
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
				Sales[t$var112] = new int[noProducts];
		}
		{
			weekly_rates = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
				weekly_rates[((t$var112 - 0) / 1)] = new double[noProducts];
		}
		{
			weekly_ut = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
				weekly_ut[((t$var112 - 0) / 1)] = new double[noProducts];
		}
		{
			constrainedFlag$sample26 = new boolean[((((noProducts - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample26 = new double[((((noProducts - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample157 = new double[((((T - 1) - 0) / 1) + 1)];
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
			int cv$max_t$var112 = 0;
			int cv$max_j$var123 = 0;
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
				cv$max_j$var123 = Math.max(cv$max_j$var123, ((noProducts - 0) / 1));
			cv$max_t$var112 = Math.max(cv$max_t$var112, ((T - 0) / 1));
			guard$sample26put131$global = new boolean[cv$max_t$var112][cv$max_j$var123];
		}
		{
			int cv$max_t$var112 = 0;
			int cv$max_j$var147 = 0;
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
				cv$max_j$var147 = Math.max(cv$max_j$var147, ((noProducts - 0) / 1));
			cv$max_t$var112 = Math.max(cv$max_t$var112, ((T - 0) / 1));
			guard$sample26put154$global = new boolean[cv$max_t$var112][cv$max_j$var147];
		}
		{
			int cv$max_t$var112 = 0;
			cv$max_t$var112 = Math.max(cv$max_t$var112, ((T - 0) / 1));
			guard$sample26multinomial156$global = new boolean[cv$max_t$var112];
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
			(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
						int t$var112 = index$t$var112;
						int threadID$t$var112 = threadID$index$t$var112;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1) {
										if(!fixedFlag$sample26)
											weekly_ut[((t$var112 - 0) / 1)][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
									}
							}
						);
						double reduceVar$denom$34 = 0.0;
						for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1) {
							double k$var135 = reduceVar$denom$34;
							double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction136Index];
							if(!fixedFlag$sample26)
								reduceVar$denom$34 = (k$var135 + l$var136);
						}
						double reduceVar$denom$34$1 = reduceVar$denom$34;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1) {
										if(!fixedFlag$sample26)
											weekly_rates[((t$var112 - 0) / 1)][j$var147] = (weekly_ut[((t$var112 - 0) / 1)][j$var147] / reduceVar$denom$34$1);
									}
							}
						);
						int[] weekly_sales = Sales[t$var112];
						DistributionSampling.sampleMultinomial(RNG$1, weekly_rates[((t$var112 - 0) / 1)], noProducts, sales_sum[t$var112], weekly_sales);
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
			(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
						int t$var112 = index$t$var112;
						int threadID$t$var112 = threadID$index$t$var112;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1)
										weekly_ut[((t$var112 - 0) / 1)][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
							}
						);
						double reduceVar$denom$38 = 0.0;
						for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1) {
							double k$var135 = reduceVar$denom$38;
							double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction136Index];
							reduceVar$denom$38 = (k$var135 + l$var136);
						}
						double reduceVar$denom$38$1 = reduceVar$denom$38;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1)
										weekly_rates[((t$var112 - 0) / 1)][j$var147] = (weekly_ut[((t$var112 - 0) / 1)][j$var147] / reduceVar$denom$38$1);
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
			(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
						int t$var112 = index$t$var112;
						int threadID$t$var112 = threadID$index$t$var112;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1)
										weekly_ut[((t$var112 - 0) / 1)][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
							}
						);
						double reduceVar$denom$35 = 0.0;
						for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1) {
							double k$var135 = reduceVar$denom$35;
							double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction136Index];
							reduceVar$denom$35 = (k$var135 + l$var136);
						}
						double reduceVar$denom$35$1 = reduceVar$denom$35;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1)
										weekly_rates[((t$var112 - 0) / 1)][j$var147] = (weekly_ut[((t$var112 - 0) / 1)][j$var147] / reduceVar$denom$35$1);
							}
						);
						int[] weekly_sales = Sales[t$var112];
						DistributionSampling.sampleMultinomial(RNG$1, weekly_rates[((t$var112 - 0) / 1)], noProducts, sales_sum[t$var112], weekly_sales);
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
			(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
						int t$var112 = index$t$var112;
						int threadID$t$var112 = threadID$index$t$var112;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1) {
										if(!fixedFlag$sample26)
											weekly_ut[((t$var112 - 0) / 1)][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
									}
							}
						);
						double reduceVar$denom$36 = 0.0;
						for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1) {
							double k$var135 = reduceVar$denom$36;
							double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction136Index];
							if(!fixedFlag$sample26)
								reduceVar$denom$36 = (k$var135 + l$var136);
						}
						double reduceVar$denom$36$1 = reduceVar$denom$36;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1) {
										if(!fixedFlag$sample26)
											weekly_rates[((t$var112 - 0) / 1)][j$var147] = (weekly_ut[((t$var112 - 0) / 1)][j$var147] / reduceVar$denom$36$1);
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
			(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
						int t$var112 = index$t$var112;
						int threadID$t$var112 = threadID$index$t$var112;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1)
										weekly_ut[((t$var112 - 0) / 1)][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
							}
						);
						double reduceVar$denom$37 = 0.0;
						for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1) {
							double k$var135 = reduceVar$denom$37;
							double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction136Index];
							reduceVar$denom$37 = (k$var135 + l$var136);
						}
						double reduceVar$denom$37$1 = reduceVar$denom$37;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1)
										weekly_rates[((t$var112 - 0) / 1)][j$var147] = (weekly_ut[((t$var112 - 0) / 1)][j$var147] / reduceVar$denom$37$1);
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
		logProbability$Sales = 0.0;
		if(!fixedProbFlag$sample157) {
			for(int t$var112 = 0; t$var112 < T; t$var112 += 1)
				logProbability$sample157[((t$var112 - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		ut[0] = 0.0;
		for(int t$var78 = 0; t$var78 < T; t$var78 += 1) {
			int reduceVar$var88$1 = 0;
			for(int cv$reduction84Index = 0; cv$reduction84Index < ObsSales[t$var78].length; cv$reduction84Index += 1) {
				int k$var85 = reduceVar$var88$1;
				int l$var86 = ObsSales[t$var78][cv$reduction84Index];
				reduceVar$var88$1 = (k$var85 + l$var86);
			}
			sales_sum[t$var78] = reduceVar$var88$1;
		}
		for(int index$constrainedFlag$sample26$1 = 0; index$constrainedFlag$sample26$1 < constrainedFlag$sample26.length; index$constrainedFlag$sample26$1 += 1)
			constrainedFlag$sample26[index$constrainedFlag$sample26$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample26)
			logProbabilityValue$sample26();
		logProbabilityValue$sample157();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample157();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample157();
	}

	@Override
	public final void propagateObservedValues() {
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
			(int forStart$index$t$var112, int forEnd$index$t$var112, int threadID$index$t$var112, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$t$var112 = forStart$index$t$var112; index$t$var112 < forEnd$index$t$var112; index$t$var112 += 1) {
						int t$var112 = index$t$var112;
						int threadID$t$var112 = threadID$index$t$var112;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var123, int forEnd$j$var123, int threadID$j$var123, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var123 = forStart$j$var123; j$var123 < forEnd$j$var123; j$var123 += 1)
										weekly_ut[((t$var112 - 0) / 1)][j$var123] = (expedNorm[j$var123] * Avail[t$var112][j$var123]);
							}
						);
						double reduceVar$denom$39 = 0.0;
						for(int cv$reduction136Index = 0; cv$reduction136Index < noProducts; cv$reduction136Index += 1) {
							double k$var135 = reduceVar$denom$39;
							double l$var136 = weekly_ut[((t$var112 - 0) / 1)][cv$reduction136Index];
							reduceVar$denom$39 = (k$var135 + l$var136);
						}
						double reduceVar$denom$39$1 = reduceVar$denom$39;
						parallelFor(RNG$1, 0, noProducts, 1,
							(int forStart$j$var147, int forEnd$j$var147, int threadID$j$var147, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var147 = forStart$j$var147; j$var147 < forEnd$j$var147; j$var147 += 1)
										weekly_rates[((t$var112 - 0) / 1)][j$var147] = (weekly_ut[((t$var112 - 0) / 1)][j$var147] / reduceVar$denom$39$1);
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
		     + "model Vulcano2012basic(int noProducts, int T, int[][] ObsSales, int[][] Avail, double r) {\n"
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
		     + "        int[] week_sales = ObsSales[t];\n"
		     + "        sales_sum[t] = reduce(week_sales, 0, (k, l) -> { return k + l; });\n"
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
		     + "\n"
		     + "}";
	}
}