package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LinearRegressionTest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements LinearRegressionTest$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample39 = false;
	private boolean fixedFlag$sample63 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample39 = false;
	private boolean fixedProbFlag$sample63 = false;
	private int k;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$phi;
	private double[] logProbability$sample28;
	private double[] logProbability$sample63;
	private double logProbability$tau;
	private double logProbability$var19;
	private double logProbability$var30;
	private double logProbability$var34;
	private double[] logProbability$var57;
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
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		fixedFlag$sample28 = cv$value;
		fixedProbFlag$sample28 = (fixedFlag$sample28 && fixedProbFlag$sample28);
		fixedProbFlag$sample63 = (fixedFlag$sample28 && fixedProbFlag$sample63);
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		fixedFlag$sample35 = cv$value;
		fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedProbFlag$sample35);
		fixedProbFlag$sample63 = (fixedFlag$sample35 && fixedProbFlag$sample63);
	}

	@Override
	public final boolean get$fixedFlag$sample39() {
		return fixedFlag$sample39;
	}

	@Override
	public final void set$fixedFlag$sample39(boolean cv$value) {
		fixedFlag$sample39 = cv$value;
		fixedProbFlag$sample39 = (fixedFlag$sample39 && fixedProbFlag$sample39);
		fixedProbFlag$sample63 = (fixedFlag$sample39 && fixedProbFlag$sample63);
	}

	@Override
	public final boolean get$fixedFlag$sample63() {
		return fixedFlag$sample63;
	}

	@Override
	public final void set$fixedFlag$sample63(boolean cv$value) {
		fixedFlag$sample63 = cv$value;
		fixedProbFlag$sample63 = (fixedFlag$sample63 && fixedProbFlag$sample63);
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
	}

	@Override
	public final double[] get$weights() {
		return weights;
	}

	@Override
	public final void set$weights(double[] cv$value) {
		weights = cv$value;
		setFlag$weights = true;
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
	}

	@Override
	public final double[] get$yMeasured() {
		return yMeasured;
	}

	@Override
	public final void set$yMeasured(double[] cv$value) {
		yMeasured = cv$value;
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var23 = 0; var23 < k; var23 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = weights[var23];
					{
						{
							double var17 = 0.0;
							double var18 = 10.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var17, var18));
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
				logProbability$sample28[((var23 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$phi = false;
				{
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var23 == j$var42)) {
							for(int i$var38 = 0; i$var38 < n; i$var38 += 1) {
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
			logProbability$var19 = cv$sampleAccumulator;
			logProbability$weights = (logProbability$weights + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var23 = 0; var23 < k; var23 += 1) {
				double cv$sampleValue = logProbability$sample28[((var23 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				boolean cv$guard$phi = false;
				{
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var23 == j$var42)) {
							for(int i$var38 = 0; i$var38 < n; i$var38 += 1) {
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
			logProbability$var19 = cv$rvAccumulator;
			logProbability$weights = (logProbability$weights + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
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
				double cv$sampleValue = bias;
				{
					{
						double var28 = 0.0;
						double var29 = 10.0;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var28, var29));
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
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample35 = fixedFlag$sample35;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$bias;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var30 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample39() {
		if(!fixedProbFlag$sample39) {
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
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample39 = fixedFlag$sample39;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$tau;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var34 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample63() {
		if(!fixedProbFlag$sample63) {
			double cv$accumulator = 0.0;
			for(int i$var38 = 0; i$var38 < n; i$var38 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = y[i$var38];
					{
						{
							double reduceVar$var55$8 = 0.0;
							for(int cv$reduction56Index = 0; cv$reduction56Index < k; cv$reduction56Index += 1) {
								double i$var52 = reduceVar$var55$8;
								double j$var53 = phi[((i$var38 - 0) / 1)][cv$reduction56Index];
								reduceVar$var55$8 = (i$var52 + j$var53);
							}
							double var56 = (reduceVar$var55$8 + bias);
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var56, tau));
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
				logProbability$var57[((i$var38 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample63[((i$var38 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample63 = (((fixedFlag$sample63 && fixedFlag$sample28) && fixedFlag$sample35) && fixedFlag$sample39);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var38 = 0; i$var38 < n; i$var38 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample63[((i$var38 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var57[((i$var38 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample28(int var23) {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		{
			{
				{
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var23 == j$var42)) {
							for(int i$var38 = 0; i$var38 < n; i$var38 += 1) {
								if(((0 <= j$var42) && (j$var42 < k))) {
									{
										{
											{
												{
													{
														{
															double cv$denominator = 1.0;
															double cv$numerator = 0.0;
															cv$numerator = (cv$numerator * x[i$var38][j$var42]);
															cv$denominator = (cv$denominator * x[i$var38][j$var42]);
															if((0 < k)) {
																double reduceVar$var55$5 = 0.0;
																for(int cv$reduction259Index = 0; cv$reduction259Index < j$var42; cv$reduction259Index += 1) {
																	double i$var52 = reduceVar$var55$5;
																	double j$var53 = phi[((i$var38 - 0) / 1)][cv$reduction259Index];
																	reduceVar$var55$5 = (i$var52 + j$var53);
																}
																for(int cv$reduction259Index = (j$var42 + 1); cv$reduction259Index < k; cv$reduction259Index += 1) {
																	double i$var52 = reduceVar$var55$5;
																	double j$var53 = phi[((i$var38 - 0) / 1)][cv$reduction259Index];
																	reduceVar$var55$5 = (i$var52 + j$var53);
																}
																double cv$reduced56 = reduceVar$var55$5;
																cv$numerator = (cv$numerator + cv$reduced56);
															}
															cv$numerator = (cv$numerator + bias);
															cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
															cv$sum = (cv$sum + (cv$denominator * (y[i$var38] - cv$numerator)));
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
			for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
				if((var23 == j$var42)) {
					for(int i$var38 = 0; i$var38 < n; i$var38 += 1)
						phi[((i$var38 - 0) / 1)][j$var42] = (weights[j$var42] * x[i$var38][j$var42]);
				}
			}
		}
	}

	private final void sample35() {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		{
			{
				{
					for(int i$var38 = 0; i$var38 < n; i$var38 += 1) {
						double cv$denominator = 1.0;
						double cv$numerator = 0.0;
						double reduceVar$var55$6 = 0.0;
						for(int cv$reduction56Index = 0; cv$reduction56Index < k; cv$reduction56Index += 1) {
							double i$var52 = reduceVar$var55$6;
							double j$var53 = phi[((i$var38 - 0) / 1)][cv$reduction56Index];
							reduceVar$var55$6 = (i$var52 + j$var53);
						}
						cv$numerator = (reduceVar$var55$6 + cv$numerator);
						cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
						cv$sum = (cv$sum + (cv$denominator * (y[i$var38] - cv$numerator)));
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

	private final void sample39() {
		double cv$sum = 0.0;
		int cv$count = 0;
		{
			{
				{
					for(int i$var38 = 0; i$var38 < n; i$var38 += 1) {
						double reduceVar$var55$7 = 0.0;
						for(int cv$reduction56Index = 0; cv$reduction56Index < k; cv$reduction56Index += 1) {
							double i$var52 = reduceVar$var55$7;
							double j$var53 = phi[((i$var38 - 0) / 1)][cv$reduction56Index];
							reduceVar$var55$7 = (i$var52 + j$var53);
						}
						double cv$var57$mu = (reduceVar$var55$7 + bias);
						double cv$var57$diff = (cv$var57$mu - y[i$var38]);
						cv$sum = (cv$sum + (cv$var57$diff * cv$var57$diff));
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
			for(int i$var38 = 0; i$var38 < x.length; i$var38 += 1)
				phi[((i$var38 - 0) / 1)] = new double[x[0].length];
		}
		{
			logProbability$sample28 = new double[((((x[0].length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var57 = new double[((((x.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample63 = new double[((((x.length - 1) - 0) / 1) + 1)];
		}
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1) {
						if(!fixedFlag$sample28)
							weights[var23] = DistributionSampling.sampleGaussian(RNG$1, 0.0, 10.0);
					}
			}
		);
		if(!fixedFlag$sample35)
			bias = DistributionSampling.sampleGaussian(RNG$, 0.0, 10.0);
		if(!fixedFlag$sample39)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i$var38, int forEnd$index$i$var38, int threadID$index$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var38 = forStart$index$i$var38; index$i$var38 < forEnd$index$i$var38; index$i$var38 += 1) {
						int i$var38 = index$i$var38;
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1) {
										if(!fixedFlag$sample28)
											phi[((i$var38 - 0) / 1)][j$var42] = (weights[j$var42] * x[i$var38][j$var42]);
									}
							}
						);
						double reduceVar$var55$9 = 0.0;
						for(int cv$reduction56Index = 0; cv$reduction56Index < k; cv$reduction56Index += 1) {
							double i$var52 = reduceVar$var55$9;
							double j$var53 = phi[((i$var38 - 0) / 1)][cv$reduction56Index];
							if(!fixedFlag$sample63)
								reduceVar$var55$9 = (i$var52 + j$var53);
						}
						if(!fixedFlag$sample63)
							y[i$var38] = DistributionSampling.sampleGaussian(RNG$1, (reduceVar$var55$9 + bias), tau);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1) {
						if(!fixedFlag$sample28)
							weights[var23] = DistributionSampling.sampleGaussian(RNG$1, 0.0, 10.0);
					}
			}
		);
		if(!fixedFlag$sample35)
			bias = DistributionSampling.sampleGaussian(RNG$, 0.0, 10.0);
		if(!fixedFlag$sample39)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i$var38, int forEnd$index$i$var38, int threadID$index$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var38 = forStart$index$i$var38; index$i$var38 < forEnd$index$i$var38; index$i$var38 += 1) {
						int i$var38 = index$i$var38;
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1) {
										if(!fixedFlag$sample28)
											phi[((i$var38 - 0) / 1)][j$var42] = (weights[j$var42] * x[i$var38][j$var42]);
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
						if(!fixedFlag$sample28)
							weights[var23] = DistributionSampling.sampleGaussian(RNG$1, 0.0, 10.0);
					}
			}
		);
		if(!fixedFlag$sample35)
			bias = DistributionSampling.sampleGaussian(RNG$, 0.0, 10.0);
		if(!fixedFlag$sample39)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i$var38, int forEnd$index$i$var38, int threadID$index$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var38 = forStart$index$i$var38; index$i$var38 < forEnd$index$i$var38; index$i$var38 += 1) {
						int i$var38 = index$i$var38;
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1) {
										if(!fixedFlag$sample28)
											phi[((i$var38 - 0) / 1)][j$var42] = (weights[j$var42] * x[i$var38][j$var42]);
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
				if(!fixedFlag$sample28)
					sample28(var23);
			}
			if(!fixedFlag$sample35)
				sample35();
			if(!fixedFlag$sample39)
				sample39();
		} else {
			if(!fixedFlag$sample39)
				sample39();
			if(!fixedFlag$sample35)
				sample35();
			for(int var23 = (k - ((((k - 1) - 0) % 1) + 1)); var23 >= ((0 - 1) + 1); var23 -= 1) {
				if(!fixedFlag$sample28)
					sample28(var23);
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
		logProbability$var19 = 0.0;
		logProbability$weights = 0.0;
		logProbability$phi = 0.0;
		if(!fixedProbFlag$sample28) {
			for(int var23 = 0; var23 < k; var23 += 1)
				logProbability$sample28[((var23 - 0) / 1)] = 0.0;
		}
		logProbability$var30 = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$bias = 0.0;
		logProbability$var34 = 0.0;
		if(!fixedProbFlag$sample39)
			logProbability$tau = 0.0;
		for(int i$var38 = 0; i$var38 < n; i$var38 += 1)
			logProbability$var57[((i$var38 - 0) / 1)] = 0.0;
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample63) {
			for(int i$var38 = 0; i$var38 < n; i$var38 += 1)
				logProbability$sample63[((i$var38 - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		if(fixedFlag$sample39)
			logProbabilityValue$sample39();
		logProbabilityValue$sample63();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample35();
		logProbabilityValue$sample39();
		logProbabilityValue$sample63();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample35();
		logProbabilityValue$sample39();
		logProbabilityValue$sample63();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1) {
						if(!fixedFlag$sample28)
							weights[var23] = DistributionSampling.sampleGaussian(RNG$1, 0.0, 10.0);
					}
			}
		);
		if(!fixedFlag$sample35)
			bias = DistributionSampling.sampleGaussian(RNG$, 0.0, 10.0);
		if(!fixedFlag$sample39)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i$var38, int forEnd$index$i$var38, int threadID$index$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var38 = forStart$index$i$var38; index$i$var38 < forEnd$index$i$var38; index$i$var38 += 1) {
						int i$var38 = index$i$var38;
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1) {
										if(!fixedFlag$sample28)
											phi[((i$var38 - 0) / 1)][j$var42] = (weights[j$var42] * x[i$var38][j$var42]);
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
			(int forStart$index$i$var38, int forEnd$index$i$var38, int threadID$index$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var38 = forStart$index$i$var38; index$i$var38 < forEnd$index$i$var38; index$i$var38 += 1) {
						int i$var38 = index$i$var38;
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1) {
										if(setFlag$weights)
											phi[((i$var38 - 0) / 1)][j$var42] = (weights[j$var42] * x[i$var38][j$var42]);
									}
							}
						);
					}
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel LinearRegressionTest(double[][] x, double[] yMeasured) {\n\n        int n = x.length;\n        int k = x[0].length;\n\n        double[] y = new double[n];\n\n        double[] weights = gaussian(0,10).sample(k);\n        double bias = gaussian(0,10).sample();\n        double tau = inverseGamma(3.0,1.0).sample();\n\n        for(int i:[0..n)) {\n            double[] phi = new double[k];\n            for(int j:[0..k,1))\n                phi[j] = weights[j] * x[i][j];\n            \n            y[i] = gaussian(sum(phi) + bias, tau).sample();\n        }\n        \n        y.observe(yMeasured);\n\n    private double sum(double[] a) {\n        return reduce(a, 0, (i,j) -> {\n            return i + j;\n        });\n    }\n}\n";
	}
}