package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LinearRegressionTest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements LinearRegressionTest$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample24 = false;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample74 = false;
	private boolean fixedProbFlag$sample24 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample74 = false;
	private int k;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$phi;
	private double[] logProbability$sample24;
	private double[] logProbability$sample74;
	private double logProbability$tau;
	private double logProbability$var12;
	private double logProbability$var30;
	private double logProbability$var34;
	private double[] logProbability$var72;
	private double logProbability$weights;
	private double logProbability$y;
	private int n;
	private double[][] phi;
	private boolean setFlag$weights = false;
	private boolean setFlag$y = false;
	private boolean system$gibbsForward = true;
	private double tau;
	private double[] weights;
	private double[][] x;
	private double[] y;
	private double[] yMeasured;

	public LinearRegressionTest$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double cv$value) {
		bias = cv$value;
		fixedProbFlag$sample31 = false;
		fixedProbFlag$sample74 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample24() {
		return fixedFlag$sample24;
	}

	@Override
	public final void set$fixedFlag$sample24(boolean cv$value) {
		fixedFlag$sample24 = cv$value;
		fixedProbFlag$sample24 = (fixedFlag$sample24 && fixedProbFlag$sample24);
		fixedProbFlag$sample74 = (fixedFlag$sample24 && fixedProbFlag$sample74);
	}

	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	@Override
	public final void set$fixedFlag$sample31(boolean cv$value) {
		fixedFlag$sample31 = cv$value;
		fixedProbFlag$sample31 = (fixedFlag$sample31 && fixedProbFlag$sample31);
		fixedProbFlag$sample74 = (fixedFlag$sample31 && fixedProbFlag$sample74);
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		fixedFlag$sample35 = cv$value;
		fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedProbFlag$sample35);
		fixedProbFlag$sample74 = (fixedFlag$sample35 && fixedProbFlag$sample74);
	}

	@Override
	public final boolean get$fixedFlag$sample74() {
		return fixedFlag$sample74;
	}

	@Override
	public final void set$fixedFlag$sample74(boolean cv$value) {
		fixedFlag$sample74 = cv$value;
		fixedProbFlag$sample74 = (fixedFlag$sample74 && fixedProbFlag$sample74);
	}

	@Override
	public final int get$k() {
		return k;
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
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$tau() {
		return logProbability$tau;
	}

	@Override
	public final double get$logProbability$weights() {
		return logProbability$weights;
	}

	@Override
	public final double get$logProbability$y() {
		return logProbability$y;
	}

	@Override
	public final int get$n() {
		return n;
	}

	@Override
	public final double get$tau() {
		return tau;
	}

	@Override
	public final void set$tau(double cv$value) {
		tau = cv$value;
		fixedProbFlag$sample35 = false;
		fixedProbFlag$sample74 = false;
	}

	@Override
	public final double[] get$weights() {
		return weights;
	}

	@Override
	public final void set$weights(double[] cv$value) {
		weights = cv$value;
		setFlag$weights = true;
		fixedProbFlag$sample24 = false;
		fixedProbFlag$sample74 = false;
	}

	@Override
	public final double[][] get$x() {
		return x;
	}

	@Override
	public final void set$x(double[][] cv$value) {
		x = cv$value;
	}

	@Override
	public final double[] get$y() {
		return y;
	}

	@Override
	public final void set$y(double[] cv$value) {
		y = cv$value;
		setFlag$y = true;
		fixedProbFlag$sample74 = false;
	}

	@Override
	public final double[] get$yMeasured() {
		return yMeasured;
	}

	@Override
	public final void set$yMeasured(double[] cv$value) {
		yMeasured = cv$value;
	}

	private final void logProbabilityValue$sample24() {
		if(!fixedProbFlag$sample24) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var23 = 0; var23 < k; var23 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = weights[var23];
					{
						{
							double var10 = 0.0;
							double var11 = 10.0;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var10) / Math.sqrt(var11))) - (0.5 * Math.log(var11))));
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
				logProbability$sample24[((var23 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$phi = false;
				{
					for(int j$var55 = 0; j$var55 < k; j$var55 += 1) {
						if((var23 == j$var55)) {
							for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
								if(!cv$guard$phi) {
									cv$guard$phi = true;
									logProbability$phi = (logProbability$phi + cv$sampleProbability);
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var12 = cv$sampleAccumulator;
			logProbability$weights = (logProbability$weights + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample24 = fixedFlag$sample24;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var23 = 0; var23 < k; var23 += 1) {
				double cv$sampleValue = logProbability$sample24[((var23 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				boolean cv$guard$phi = false;
				{
					for(int j$var55 = 0; j$var55 < k; j$var55 += 1) {
						if((var23 == j$var55)) {
							for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
								if(!cv$guard$phi) {
									cv$guard$phi = true;
									logProbability$phi = (logProbability$phi + cv$sampleValue);
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var12 = cv$rvAccumulator;
			logProbability$weights = (logProbability$weights + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!fixedProbFlag$sample31) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = bias;
				{
					{
						double var28 = 0.0;
						double var29 = 10.0;
						double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var28) / Math.sqrt(var29))) - (0.5 * Math.log(var29))));
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
			logProbability$var30 = cv$sampleAccumulator;
			logProbability$bias = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample31 = fixedFlag$sample31;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$bias;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var30 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = tau;
				{
					{
						double var32 = 3.0;
						double var33 = 1.0;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var32, var33));
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
			logProbability$var34 = cv$sampleAccumulator;
			logProbability$tau = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample35 = fixedFlag$sample35;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$tau;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var34 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample74() {
		if(!fixedProbFlag$sample74) {
			double cv$accumulator = 0.0;
			for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = y[i$var45];
					{
						{
							double reduceVar$var70$8 = 0.0;
							for(int cv$reduction65Index = 0; cv$reduction65Index < k; cv$reduction65Index += 1) {
								double i$var67 = reduceVar$var70$8;
								double j$var68 = phi[((i$var45 - 0) / 1)][cv$reduction65Index];
								reduceVar$var70$8 = (i$var67 + j$var68);
							}
							double var71 = (reduceVar$var70$8 + bias);
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var71) / Math.sqrt(tau))) - (0.5 * Math.log(tau))));
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
				logProbability$var72[((i$var45 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample74[((i$var45 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample74 = (((fixedFlag$sample74 && fixedFlag$sample24) && fixedFlag$sample31) && fixedFlag$sample35);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample74[((i$var45 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var72[((i$var45 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample24(int var23) {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		{
			{
				{
					for(int j$var55 = 0; j$var55 < k; j$var55 += 1) {
						if((var23 == j$var55)) {
							for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
								if(((0 <= j$var55) && (j$var55 < k))) {
									{
										{
											{
												{
													{
														{
															double cv$denominator = 1.0;
															double cv$numerator = 0.0;
															cv$numerator = (cv$numerator * x[i$var45][j$var55]);
															cv$denominator = (cv$denominator * x[i$var45][j$var55]);
															if((0 < k)) {
																double reduceVar$var70$5 = 0.0;
																for(int cv$reduction383Index = 0; cv$reduction383Index < j$var55; cv$reduction383Index += 1) {
																	double i$var67 = reduceVar$var70$5;
																	double j$var68 = phi[((i$var45 - 0) / 1)][cv$reduction383Index];
																	reduceVar$var70$5 = (i$var67 + j$var68);
																}
																for(int cv$reduction383Index = (j$var55 + 1); cv$reduction383Index < k; cv$reduction383Index += 1) {
																	double i$var67 = reduceVar$var70$5;
																	double j$var68 = phi[((i$var45 - 0) / 1)][cv$reduction383Index];
																	reduceVar$var70$5 = (i$var67 + j$var68);
																}
																double cv$reduced65 = reduceVar$var70$5;
																cv$numerator = (cv$numerator + cv$reduced65);
															}
															cv$numerator = (cv$numerator + bias);
															cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
															cv$sum = (cv$sum + (cv$denominator * (y[i$var45] - cv$numerator)));
															if(cv$sigmaNotFound) {
																cv$sigmaValue = tau;
																cv$sigmaNotFound = false;
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		double var24 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
		weights[var23] = var24;
		{
			for(int j$var55 = 0; j$var55 < k; j$var55 += 1) {
				if((var23 == j$var55)) {
					for(int i$var45 = 0; i$var45 < n; i$var45 += 1)
						phi[((i$var45 - 0) / 1)][j$var55] = (weights[j$var55] * x[i$var45][j$var55]);
				}
			}
		}
	}

	private final void sample31() {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		{
			{
				{
					for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
						double cv$denominator = 1.0;
						double cv$numerator = 0.0;
						double reduceVar$var70$6 = 0.0;
						for(int cv$reduction65Index = 0; cv$reduction65Index < k; cv$reduction65Index += 1) {
							double i$var67 = reduceVar$var70$6;
							double j$var68 = phi[((i$var45 - 0) / 1)][cv$reduction65Index];
							reduceVar$var70$6 = (i$var67 + j$var68);
						}
						cv$numerator = (reduceVar$var70$6 + cv$numerator);
						cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
						cv$sum = (cv$sum + (cv$denominator * (y[i$var45] - cv$numerator)));
						if(cv$sigmaNotFound) {
							cv$sigmaValue = tau;
							cv$sigmaNotFound = false;
						}
					}
				}
			}
		}
		bias = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void sample35() {
		double cv$sum = 0.0;
		int cv$count = 0;
		{
			{
				{
					for(int i$var45 = 0; i$var45 < n; i$var45 += 1) {
						double reduceVar$var70$7 = 0.0;
						for(int cv$reduction65Index = 0; cv$reduction65Index < k; cv$reduction65Index += 1) {
							double i$var67 = reduceVar$var70$7;
							double j$var68 = phi[((i$var45 - 0) / 1)][cv$reduction65Index];
							reduceVar$var70$7 = (i$var67 + j$var68);
						}
						double cv$var72$mu = (reduceVar$var70$7 + bias);
						double cv$var72$diff = (cv$var72$mu - y[i$var45]);
						cv$sum = (cv$sum + (cv$var72$diff * cv$var72$diff));
						cv$count = (cv$count + 1);
					}
				}
			}
		}
		tau = Conjugates.sampleConjugateInverseGammaGaussian(RNG$, 3.0, 1.0, cv$sum, cv$count);
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!setFlag$y) {
			{
				y = new double[x.length];
			}
		}
		if(!setFlag$weights) {
			{
				weights = new double[x[0].length];
			}
		}
		{
			phi = new double[((((x.length - 1) - 0) / 1) + 1)][];
			for(int i$var45 = 0; i$var45 < x.length; i$var45 += 1)
				phi[((i$var45 - 0) / 1)] = new double[x[0].length];
		}
		{
			logProbability$sample24 = new double[((((x[0].length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var72 = new double[((((x.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample74 = new double[((((x.length - 1) - 0) / 1) + 1)];
		}
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1) {
						if(!fixedFlag$sample24)
							weights[var23] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample31)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample35)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i$var45, int forEnd$index$i$var45, int threadID$index$i$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var45 = forStart$index$i$var45; index$i$var45 < forEnd$index$i$var45; index$i$var45 += 1) {
						int i$var45 = index$i$var45;
						int threadID$i$var45 = threadID$index$i$var45;
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var55, int forEnd$j$var55, int threadID$j$var55, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var55 = forStart$j$var55; j$var55 < forEnd$j$var55; j$var55 += 1) {
										if(!fixedFlag$sample24)
											phi[((i$var45 - 0) / 1)][j$var55] = (weights[j$var55] * x[i$var45][j$var55]);
									}
							}
						);
						double reduceVar$var70$9 = 0.0;
						for(int cv$reduction65Index = 0; cv$reduction65Index < k; cv$reduction65Index += 1) {
							double i$var67 = reduceVar$var70$9;
							double j$var68 = phi[((i$var45 - 0) / 1)][cv$reduction65Index];
							if(!fixedFlag$sample74)
								reduceVar$var70$9 = (i$var67 + j$var68);
						}
						if(!fixedFlag$sample74)
							y[i$var45] = ((Math.sqrt(tau) * DistributionSampling.sampleGaussian(RNG$1)) + (reduceVar$var70$9 + bias));
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1) {
						if(!fixedFlag$sample24)
							weights[var23] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample31)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample35)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i$var45, int forEnd$index$i$var45, int threadID$index$i$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var45 = forStart$index$i$var45; index$i$var45 < forEnd$index$i$var45; index$i$var45 += 1) {
						int i$var45 = index$i$var45;
						int threadID$i$var45 = threadID$index$i$var45;
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var55, int forEnd$j$var55, int threadID$j$var55, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var55 = forStart$j$var55; j$var55 < forEnd$j$var55; j$var55 += 1) {
										if(!fixedFlag$sample24)
											phi[((i$var45 - 0) / 1)][j$var55] = (weights[j$var55] * x[i$var45][j$var55]);
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1) {
						if(!fixedFlag$sample24)
							weights[var23] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample31)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample35)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i$var45, int forEnd$index$i$var45, int threadID$index$i$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var45 = forStart$index$i$var45; index$i$var45 < forEnd$index$i$var45; index$i$var45 += 1) {
						int i$var45 = index$i$var45;
						int threadID$i$var45 = threadID$index$i$var45;
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var55, int forEnd$j$var55, int threadID$j$var55, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var55 = forStart$j$var55; j$var55 < forEnd$j$var55; j$var55 += 1) {
										if(!fixedFlag$sample24)
											phi[((i$var45 - 0) / 1)][j$var55] = (weights[j$var55] * x[i$var45][j$var55]);
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
			for(int var23 = 0; var23 < k; var23 += 1) {
				if(!fixedFlag$sample24)
					sample24(var23);
			}
			if(!fixedFlag$sample31)
				sample31();
			if(!fixedFlag$sample35)
				sample35();
		} else {
			if(!fixedFlag$sample35)
				sample35();
			if(!fixedFlag$sample31)
				sample31();
			for(int var23 = (k - ((((k - 1) - 0) % 1) + 1)); var23 >= ((0 - 1) + 1); var23 -= 1) {
				if(!fixedFlag$sample24)
					sample24(var23);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		n = x.length;
		k = x[0].length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var12 = 0.0;
		logProbability$weights = 0.0;
		logProbability$phi = 0.0;
		if(!fixedProbFlag$sample24) {
			for(int var23 = 0; var23 < k; var23 += 1)
				logProbability$sample24[((var23 - 0) / 1)] = 0.0;
		}
		logProbability$var30 = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$bias = 0.0;
		logProbability$var34 = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$tau = 0.0;
		for(int i$var45 = 0; i$var45 < n; i$var45 += 1)
			logProbability$var72[((i$var45 - 0) / 1)] = 0.0;
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample74) {
			for(int i$var45 = 0; i$var45 < n; i$var45 += 1)
				logProbability$sample74[((i$var45 - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample24)
			logProbabilityValue$sample24();
		if(fixedFlag$sample31)
			logProbabilityValue$sample31();
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		logProbabilityValue$sample74();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample24();
		logProbabilityValue$sample31();
		logProbabilityValue$sample35();
		logProbabilityValue$sample74();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample24();
		logProbabilityValue$sample31();
		logProbabilityValue$sample35();
		logProbabilityValue$sample74();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1) {
						if(!fixedFlag$sample24)
							weights[var23] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample31)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample35)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i$var45, int forEnd$index$i$var45, int threadID$index$i$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var45 = forStart$index$i$var45; index$i$var45 < forEnd$index$i$var45; index$i$var45 += 1) {
						int i$var45 = index$i$var45;
						int threadID$i$var45 = threadID$index$i$var45;
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var55, int forEnd$j$var55, int threadID$j$var55, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var55 = forStart$j$var55; j$var55 < forEnd$j$var55; j$var55 += 1) {
										if(!fixedFlag$sample24)
											phi[((i$var45 - 0) / 1)][j$var55] = (weights[j$var55] * x[i$var45][j$var55]);
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
		double[] cv$source1 = yMeasured;
		double[] cv$target1 = y;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i$var45, int forEnd$index$i$var45, int threadID$index$i$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var45 = forStart$index$i$var45; index$i$var45 < forEnd$index$i$var45; index$i$var45 += 1) {
						int i$var45 = index$i$var45;
						int threadID$i$var45 = threadID$index$i$var45;
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var55, int forEnd$j$var55, int threadID$j$var55, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var55 = forStart$j$var55; j$var55 < forEnd$j$var55; j$var55 += 1) {
										if(setFlag$weights)
											phi[((i$var45 - 0) / 1)][j$var55] = (weights[j$var55] * x[i$var45][j$var55]);
									}
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
		     + " * Copyright (c) 2019-2023, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model LinearRegressionTest(double[][] x, double[] yMeasured) {\n"
		     + "\n"
		     + "        int n = x.length;\n"
		     + "        int k = x[0].length;\n"
		     + "\n"
		     + "        double[] y = new double[n];\n"
		     + "\n"
		     + "        double[] weights = gaussian(0,10).sample(k);\n"
		     + "        double bias = gaussian(0,10).sample();\n"
		     + "        double tau = inverseGamma(3.0,1.0).sample();\n"
		     + "\n"
		     + "        for(int i:[0..n)) {\n"
		     + "            double[] phi = new double[k];\n"
		     + "            for(int j:[0..k,1))\n"
		     + "                phi[j] = weights[j] * x[i][j];\n"
		     + "            \n"
		     + "            y[i] = gaussian(sum(phi) + bias, tau).sample();\n"
		     + "        }\n"
		     + "        \n"
		     + "        y.observe(yMeasured);\n"
		     + "\n"
		     + "    private double sum(double[] a) {\n"
		     + "        return reduce(a, 0, (i,j) -> {\n"
		     + "            return i + j;\n"
		     + "        });\n"
		     + "    }\n"
		     + "}\n"
		     + "";
	}
}