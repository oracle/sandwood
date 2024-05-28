package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LinearRegressionTest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements LinearRegressionTest$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample42 = false;
	private boolean fixedFlag$sample46 = false;
	private boolean fixedFlag$sample85 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample42 = false;
	private boolean fixedProbFlag$sample46 = false;
	private boolean fixedProbFlag$sample85 = false;
	private int k;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$phi;
	private double[] logProbability$sample35;
	private double[] logProbability$sample85;
	private double logProbability$tau;
	private double logProbability$var19;
	private double logProbability$var37;
	private double logProbability$var41;
	private double[] logProbability$var79;
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
		fixedProbFlag$sample42 = false;
		fixedProbFlag$sample85 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		fixedFlag$sample35 = cv$value;
		fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedProbFlag$sample35);
		fixedProbFlag$sample85 = (fixedFlag$sample35 && fixedProbFlag$sample85);
	}

	@Override
	public final boolean get$fixedFlag$sample42() {
		return fixedFlag$sample42;
	}

	@Override
	public final void set$fixedFlag$sample42(boolean cv$value) {
		fixedFlag$sample42 = cv$value;
		fixedProbFlag$sample42 = (fixedFlag$sample42 && fixedProbFlag$sample42);
		fixedProbFlag$sample85 = (fixedFlag$sample42 && fixedProbFlag$sample85);
	}

	@Override
	public final boolean get$fixedFlag$sample46() {
		return fixedFlag$sample46;
	}

	@Override
	public final void set$fixedFlag$sample46(boolean cv$value) {
		fixedFlag$sample46 = cv$value;
		fixedProbFlag$sample46 = (fixedFlag$sample46 && fixedProbFlag$sample46);
		fixedProbFlag$sample85 = (fixedFlag$sample46 && fixedProbFlag$sample85);
	}

	@Override
	public final boolean get$fixedFlag$sample85() {
		return fixedFlag$sample85;
	}

	@Override
	public final void set$fixedFlag$sample85(boolean cv$value) {
		fixedFlag$sample85 = cv$value;
		fixedProbFlag$sample85 = (fixedFlag$sample85 && fixedProbFlag$sample85);
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
		fixedProbFlag$sample46 = false;
		fixedProbFlag$sample85 = false;
	}

	@Override
	public final double[] get$weights() {
		return weights;
	}

	@Override
	public final void set$weights(double[] cv$value) {
		weights = cv$value;
		setFlag$weights = true;
		fixedProbFlag$sample35 = false;
		fixedProbFlag$sample85 = false;
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
		fixedProbFlag$sample85 = false;
	}

	@Override
	public final double[] get$yMeasured() {
		return yMeasured;
	}

	@Override
	public final void set$yMeasured(double[] cv$value) {
		yMeasured = cv$value;
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var30 = 0; var30 < k; var30 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = weights[var30];
					{
						{
							double var17 = 0.0;
							double var18 = 10.0;
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				logProbability$sample35[((var30 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$phi = false;
				{
					for(int j$var62 = 0; j$var62 < k; j$var62 += 1) {
						if((var30 == j$var62)) {
							for(int i$var52 = 0; i$var52 < n; i$var52 += 1) {
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
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample35 = fixedFlag$sample35;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var30 = 0; var30 < k; var30 += 1) {
				double cv$sampleValue = logProbability$sample35[((var30 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				boolean cv$guard$phi = false;
				{
					for(int j$var62 = 0; j$var62 < k; j$var62 += 1) {
						if((var30 == j$var62)) {
							for(int i$var52 = 0; i$var52 < n; i$var52 += 1) {
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
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample42() {
		if(!fixedProbFlag$sample42) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = bias;
				{
					{
						double var35 = 0.0;
						double var36 = 10.0;
						double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var35) / Math.sqrt(var36))) - (0.5 * Math.log(var36))));
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
			logProbability$var37 = cv$sampleAccumulator;
			logProbability$bias = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample42 = fixedFlag$sample42;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$bias;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var37 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample46() {
		if(!fixedProbFlag$sample46) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = tau;
				{
					{
						double var39 = 3.0;
						double var40 = 1.0;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var39, var40));
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
			logProbability$var41 = cv$sampleAccumulator;
			logProbability$tau = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample46 = fixedFlag$sample46;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$tau;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var41 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample85() {
		if(!fixedProbFlag$sample85) {
			double cv$accumulator = 0.0;
			for(int i$var52 = 0; i$var52 < n; i$var52 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = y[i$var52];
					{
						{
							double reduceVar$var77$8 = 0.0;
							for(int cv$reduction76Index = 0; cv$reduction76Index < k; cv$reduction76Index += 1) {
								double i$var74 = reduceVar$var77$8;
								double j$var75 = phi[((i$var52 - 0) / 1)][cv$reduction76Index];
								reduceVar$var77$8 = (i$var74 + j$var75);
							}
							double var78 = (reduceVar$var77$8 + bias);
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var78) / Math.sqrt(tau))) - (0.5 * Math.log(tau))));
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
				logProbability$var79[((i$var52 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample85[((i$var52 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample85 = (((fixedFlag$sample85 && fixedFlag$sample35) && fixedFlag$sample42) && fixedFlag$sample46);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var52 = 0; i$var52 < n; i$var52 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample85[((i$var52 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var79[((i$var52 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample35(int var30) {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		{
			{
				{
					for(int j$var62 = 0; j$var62 < k; j$var62 += 1) {
						if((var30 == j$var62)) {
							for(int i$var52 = 0; i$var52 < n; i$var52 += 1) {
								if(((0 <= j$var62) && (j$var62 < k))) {
									{
										{
											{
												{
													{
														{
															double cv$denominator = 1.0;
															double cv$numerator = 0.0;
															cv$numerator = (cv$numerator * x[i$var52][j$var62]);
															cv$denominator = (cv$denominator * x[i$var52][j$var62]);
															if((0 < k)) {
																double reduceVar$var77$5 = 0.0;
																for(int cv$reduction393Index = 0; cv$reduction393Index < j$var62; cv$reduction393Index += 1) {
																	double i$var74 = reduceVar$var77$5;
																	double j$var75 = phi[((i$var52 - 0) / 1)][cv$reduction393Index];
																	reduceVar$var77$5 = (i$var74 + j$var75);
																}
																for(int cv$reduction393Index = (j$var62 + 1); cv$reduction393Index < k; cv$reduction393Index += 1) {
																	double i$var74 = reduceVar$var77$5;
																	double j$var75 = phi[((i$var52 - 0) / 1)][cv$reduction393Index];
																	reduceVar$var77$5 = (i$var74 + j$var75);
																}
																double cv$reduced76 = reduceVar$var77$5;
																cv$numerator = (cv$numerator + cv$reduced76);
															}
															cv$numerator = (cv$numerator + bias);
															cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
															cv$sum = (cv$sum + (cv$denominator * (y[i$var52] - cv$numerator)));
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
		double var31 = Conjugates.sampleConjugateGaussianGaussian(RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
		weights[var30] = var31;
		{
			for(int j$var62 = 0; j$var62 < k; j$var62 += 1) {
				if((var30 == j$var62)) {
					for(int i$var52 = 0; i$var52 < n; i$var52 += 1)
						phi[((i$var52 - 0) / 1)][j$var62] = (weights[j$var62] * x[i$var52][j$var62]);
				}
			}
		}
	}

	private final void sample42() {
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		{
			{
				{
					for(int i$var52 = 0; i$var52 < n; i$var52 += 1) {
						double cv$denominator = 1.0;
						double cv$numerator = 0.0;
						double reduceVar$var77$6 = 0.0;
						for(int cv$reduction76Index = 0; cv$reduction76Index < k; cv$reduction76Index += 1) {
							double i$var74 = reduceVar$var77$6;
							double j$var75 = phi[((i$var52 - 0) / 1)][cv$reduction76Index];
							reduceVar$var77$6 = (i$var74 + j$var75);
						}
						cv$numerator = (reduceVar$var77$6 + cv$numerator);
						cv$denominatorSquareSum = (cv$denominatorSquareSum + (cv$denominator * cv$denominator));
						cv$sum = (cv$sum + (cv$denominator * (y[i$var52] - cv$numerator)));
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

	private final void sample46() {
		double cv$sum = 0.0;
		int cv$count = 0;
		{
			{
				{
					for(int i$var52 = 0; i$var52 < n; i$var52 += 1) {
						double reduceVar$var77$7 = 0.0;
						for(int cv$reduction76Index = 0; cv$reduction76Index < k; cv$reduction76Index += 1) {
							double i$var74 = reduceVar$var77$7;
							double j$var75 = phi[((i$var52 - 0) / 1)][cv$reduction76Index];
							reduceVar$var77$7 = (i$var74 + j$var75);
						}
						double cv$var79$mu = (reduceVar$var77$7 + bias);
						double cv$var79$diff = (cv$var79$mu - y[i$var52]);
						cv$sum = (cv$sum + (cv$var79$diff * cv$var79$diff));
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
			for(int i$var52 = 0; i$var52 < x.length; i$var52 += 1)
				phi[((i$var52 - 0) / 1)] = new double[x[0].length];
		}
		{
			logProbability$sample35 = new double[((((x[0].length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var79 = new double[((((x.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample85 = new double[((((x.length - 1) - 0) / 1) + 1)];
		}
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var30, int forEnd$var30, int threadID$var30, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var30 = forStart$var30; var30 < forEnd$var30; var30 += 1) {
						if(!fixedFlag$sample35)
							weights[var30] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample42)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample46)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i$var52, int forEnd$index$i$var52, int threadID$index$i$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var52 = forStart$index$i$var52; index$i$var52 < forEnd$index$i$var52; index$i$var52 += 1) {
						int i$var52 = index$i$var52;
						int threadID$i$var52 = threadID$index$i$var52;
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var62, int forEnd$j$var62, int threadID$j$var62, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var62 = forStart$j$var62; j$var62 < forEnd$j$var62; j$var62 += 1) {
										if(!fixedFlag$sample35)
											phi[((i$var52 - 0) / 1)][j$var62] = (weights[j$var62] * x[i$var52][j$var62]);
									}
							}
						);
						double reduceVar$var77$9 = 0.0;
						for(int cv$reduction76Index = 0; cv$reduction76Index < k; cv$reduction76Index += 1) {
							double i$var74 = reduceVar$var77$9;
							double j$var75 = phi[((i$var52 - 0) / 1)][cv$reduction76Index];
							if(!fixedFlag$sample85)
								reduceVar$var77$9 = (i$var74 + j$var75);
						}
						if(!fixedFlag$sample85)
							y[i$var52] = ((Math.sqrt(tau) * DistributionSampling.sampleGaussian(RNG$1)) + (reduceVar$var77$9 + bias));
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var30, int forEnd$var30, int threadID$var30, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var30 = forStart$var30; var30 < forEnd$var30; var30 += 1) {
						if(!fixedFlag$sample35)
							weights[var30] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample42)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample46)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i$var52, int forEnd$index$i$var52, int threadID$index$i$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var52 = forStart$index$i$var52; index$i$var52 < forEnd$index$i$var52; index$i$var52 += 1) {
						int i$var52 = index$i$var52;
						int threadID$i$var52 = threadID$index$i$var52;
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var62, int forEnd$j$var62, int threadID$j$var62, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var62 = forStart$j$var62; j$var62 < forEnd$j$var62; j$var62 += 1) {
										if(!fixedFlag$sample35)
											phi[((i$var52 - 0) / 1)][j$var62] = (weights[j$var62] * x[i$var52][j$var62]);
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
			(int forStart$var30, int forEnd$var30, int threadID$var30, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var30 = forStart$var30; var30 < forEnd$var30; var30 += 1) {
						if(!fixedFlag$sample35)
							weights[var30] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample42)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample46)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i$var52, int forEnd$index$i$var52, int threadID$index$i$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var52 = forStart$index$i$var52; index$i$var52 < forEnd$index$i$var52; index$i$var52 += 1) {
						int i$var52 = index$i$var52;
						int threadID$i$var52 = threadID$index$i$var52;
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var62, int forEnd$j$var62, int threadID$j$var62, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var62 = forStart$j$var62; j$var62 < forEnd$j$var62; j$var62 += 1) {
										if(!fixedFlag$sample35)
											phi[((i$var52 - 0) / 1)][j$var62] = (weights[j$var62] * x[i$var52][j$var62]);
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
			for(int var30 = 0; var30 < k; var30 += 1) {
				if(!fixedFlag$sample35)
					sample35(var30);
			}
			if(!fixedFlag$sample42)
				sample42();
			if(!fixedFlag$sample46)
				sample46();
		} else {
			if(!fixedFlag$sample46)
				sample46();
			if(!fixedFlag$sample42)
				sample42();
			for(int var30 = (k - ((((k - 1) - 0) % 1) + 1)); var30 >= ((0 - 1) + 1); var30 -= 1) {
				if(!fixedFlag$sample35)
					sample35(var30);
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
		logProbability$phi = 0.0;
		logProbability$weights = 0.0;
		if(!fixedProbFlag$sample35) {
			for(int var30 = 0; var30 < k; var30 += 1)
				logProbability$sample35[((var30 - 0) / 1)] = 0.0;
		}
		logProbability$var37 = 0.0;
		if(!fixedProbFlag$sample42)
			logProbability$bias = 0.0;
		logProbability$var41 = 0.0;
		if(!fixedProbFlag$sample46)
			logProbability$tau = 0.0;
		for(int i$var52 = 0; i$var52 < n; i$var52 += 1)
			logProbability$var79[((i$var52 - 0) / 1)] = 0.0;
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample85) {
			for(int i$var52 = 0; i$var52 < n; i$var52 += 1)
				logProbability$sample85[((i$var52 - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		if(fixedFlag$sample42)
			logProbabilityValue$sample42();
		if(fixedFlag$sample46)
			logProbabilityValue$sample46();
		logProbabilityValue$sample85();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample35();
		logProbabilityValue$sample42();
		logProbabilityValue$sample46();
		logProbabilityValue$sample85();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample35();
		logProbabilityValue$sample42();
		logProbabilityValue$sample46();
		logProbabilityValue$sample85();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var30, int forEnd$var30, int threadID$var30, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var30 = forStart$var30; var30 < forEnd$var30; var30 += 1) {
						if(!fixedFlag$sample35)
							weights[var30] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample42)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		if(!fixedFlag$sample46)
			tau = DistributionSampling.sampleInverseGamma(RNG$, 3.0, 1.0);
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i$var52, int forEnd$index$i$var52, int threadID$index$i$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var52 = forStart$index$i$var52; index$i$var52 < forEnd$index$i$var52; index$i$var52 += 1) {
						int i$var52 = index$i$var52;
						int threadID$i$var52 = threadID$index$i$var52;
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var62, int forEnd$j$var62, int threadID$j$var62, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var62 = forStart$j$var62; j$var62 < forEnd$j$var62; j$var62 += 1) {
										if(!fixedFlag$sample35)
											phi[((i$var52 - 0) / 1)][j$var62] = (weights[j$var62] * x[i$var52][j$var62]);
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
			(int forStart$index$i$var52, int forEnd$index$i$var52, int threadID$index$i$var52, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var52 = forStart$index$i$var52; index$i$var52 < forEnd$index$i$var52; index$i$var52 += 1) {
						int i$var52 = index$i$var52;
						int threadID$i$var52 = threadID$index$i$var52;
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var62, int forEnd$j$var62, int threadID$j$var62, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var62 = forStart$j$var62; j$var62 < forEnd$j$var62; j$var62 += 1) {
										if(setFlag$weights)
											phi[((i$var52 - 0) / 1)][j$var62] = (weights[j$var62] * x[i$var52][j$var62]);
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