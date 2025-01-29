package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LogitRegressionTest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements LogitRegressionTest$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedFlag$sample39 = false;
	private boolean fixedFlag$sample72 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean fixedProbFlag$sample39 = false;
	private boolean fixedProbFlag$sample72 = false;
	private boolean[][] guard$sample32bernoulli71$global;
	private boolean[][] guard$sample32put67$global;
	private double[][] indicator;
	private int k;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$indicator;
	private double logProbability$p;
	private double[] logProbability$sample32;
	private double[][] logProbability$sample72;
	private double logProbability$var23;
	private double logProbability$var34;
	private double[][] logProbability$var67;
	private double logProbability$weights;
	private double logProbability$y;
	private int n;
	private double[][] p;
	private boolean setFlag$weights = false;
	private boolean setFlag$y = false;
	private boolean system$gibbsForward = true;
	private double[] weights;
	private double[][] x;
	private boolean[][] y;
	private boolean[][] yMeasured;

	public LogitRegressionTest$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double cv$value) {
		bias = cv$value;
		fixedProbFlag$sample39 = false;
		fixedProbFlag$sample72 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	@Override
	public final void set$fixedFlag$sample32(boolean cv$value) {
		fixedFlag$sample32 = cv$value;
		fixedProbFlag$sample32 = (fixedFlag$sample32 && fixedProbFlag$sample32);
		fixedProbFlag$sample72 = (fixedFlag$sample32 && fixedProbFlag$sample72);
	}

	@Override
	public final boolean get$fixedFlag$sample39() {
		return fixedFlag$sample39;
	}

	@Override
	public final void set$fixedFlag$sample39(boolean cv$value) {
		fixedFlag$sample39 = cv$value;
		fixedProbFlag$sample39 = (fixedFlag$sample39 && fixedProbFlag$sample39);
		fixedProbFlag$sample72 = (fixedFlag$sample39 && fixedProbFlag$sample72);
	}

	@Override
	public final boolean get$fixedFlag$sample72() {
		return fixedFlag$sample72;
	}

	@Override
	public final void set$fixedFlag$sample72(boolean cv$value) {
		fixedFlag$sample72 = cv$value;
		fixedProbFlag$sample72 = (fixedFlag$sample72 && fixedProbFlag$sample72);
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
	public final double[] get$weights() {
		return weights;
	}

	@Override
	public final void set$weights(double[] cv$value) {
		weights = cv$value;
		setFlag$weights = true;
		fixedProbFlag$sample32 = false;
		fixedProbFlag$sample72 = false;
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
	public final boolean[][] get$y() {
		return y;
	}

	@Override
	public final void set$y(boolean[][] cv$value) {
		y = cv$value;
		setFlag$y = true;
		fixedProbFlag$sample72 = false;
	}

	@Override
	public final boolean[][] get$yMeasured() {
		return yMeasured;
	}

	@Override
	public final void set$yMeasured(boolean[][] cv$value) {
		yMeasured = cv$value;
	}

	private final void logProbabilityValue$sample32() {
		if(!fixedProbFlag$sample32) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var27 = 0; var27 < k; var27 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = weights[var27];
					{
						{
							double var21 = 0.0;
							double var22 = 10.0;
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
				logProbability$sample32[((var27 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$indicator = false;
				boolean cv$guard$p = false;
				{
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var27 == j$var42)) {
							for(int i = 0; i < n; i += 1) {
								if(!cv$guard$indicator) {
									cv$guard$indicator = true;
									logProbability$indicator = (logProbability$indicator + cv$sampleProbability);
								}
							}
						}
					}
				}
				{
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var27 == j$var42)) {
							if((j$var42 == 0)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
										if(!cv$guard$p) {
											cv$guard$p = true;
											logProbability$p = (logProbability$p + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var27 == j$var42)) {
							if((j$var42 == 1)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
										if(!cv$guard$p) {
											cv$guard$p = true;
											logProbability$p = (logProbability$p + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var27 == j$var42)) {
							if((j$var42 == 2)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
										if(!cv$guard$p) {
											cv$guard$p = true;
											logProbability$p = (logProbability$p + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var27 == j$var42)) {
							for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
								if((j$var42 == j$var60)) {
									for(int i = 0; i < n; i += 1) {
										if(!cv$guard$p) {
											cv$guard$p = true;
											logProbability$p = (logProbability$p + cv$sampleProbability);
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
			logProbability$weights = (logProbability$weights + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample32 = fixedFlag$sample32;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var27 = 0; var27 < k; var27 += 1) {
				double cv$sampleValue = logProbability$sample32[((var27 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				boolean cv$guard$indicator = false;
				boolean cv$guard$p = false;
				{
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var27 == j$var42)) {
							for(int i = 0; i < n; i += 1) {
								if(!cv$guard$indicator) {
									cv$guard$indicator = true;
									logProbability$indicator = (logProbability$indicator + cv$sampleValue);
								}
							}
						}
					}
				}
				{
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var27 == j$var42)) {
							if((j$var42 == 0)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
										if(!cv$guard$p) {
											cv$guard$p = true;
											logProbability$p = (logProbability$p + cv$sampleValue);
										}
									}
								}
							}
						}
					}
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var27 == j$var42)) {
							if((j$var42 == 1)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
										if(!cv$guard$p) {
											cv$guard$p = true;
											logProbability$p = (logProbability$p + cv$sampleValue);
										}
									}
								}
							}
						}
					}
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var27 == j$var42)) {
							if((j$var42 == 2)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
										if(!cv$guard$p) {
											cv$guard$p = true;
											logProbability$p = (logProbability$p + cv$sampleValue);
										}
									}
								}
							}
						}
					}
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var27 == j$var42)) {
							for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
								if((j$var42 == j$var60)) {
									for(int i = 0; i < n; i += 1) {
										if(!cv$guard$p) {
											cv$guard$p = true;
											logProbability$p = (logProbability$p + cv$sampleValue);
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
			logProbability$weights = (logProbability$weights + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample32)
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
				double cv$sampleValue = bias;
				{
					{
						double var32 = 0.0;
						double var33 = 10.0;
						double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var32) / Math.sqrt(var33))) - (0.5 * Math.log(var33))));
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
			logProbability$bias = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample39 = fixedFlag$sample39;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$bias;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var34 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample72() {
		if(!fixedProbFlag$sample72) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						boolean cv$sampleValue = y[i][j$var60];
						{
							{
								double var66 = (p[((i - 0) / 1)][j$var60] + bias);
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var66));
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
					logProbability$var67[((i - 0) / 1)][((j$var60 - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample72[((i - 0) / 1)][((j$var60 - 0) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample72 = ((fixedFlag$sample72 && fixedFlag$sample32) && fixedFlag$sample39);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample72[((i - 0) / 1)][((j$var60 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var67[((i - 0) / 1)][((j$var60 - 0) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample32(int var27) {
		double cv$originalValue = weights[var27];
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
					weights[var27] = cv$currentValue;
					{
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								for(int i = 0; i < n; i += 1)
									indicator[((i - 0) / 1)][j$var42] = Math.exp((weights[j$var42] * x[i][j$var42]));
							}
						}
					}
					{
						boolean[][] guard$sample32put67 = guard$sample32put67$global;
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								if((j$var42 == 0)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var60 = 0; j$var60 < k; j$var60 += 1)
											guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								if((j$var42 == 1)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var60 = 0; j$var60 < k; j$var60 += 1)
											guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								if((j$var42 == 2)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var60 = 0; j$var60 < k; j$var60 += 1)
											guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
									if((j$var42 == j$var60)) {
										for(int i = 0; i < n; i += 1)
											guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								if((j$var42 == 0)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
											if(!guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
												guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
												{
													p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
												}
											}
										}
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								if((j$var42 == 1)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
											if(!guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
												guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
												{
													p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
												}
											}
										}
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								if((j$var42 == 2)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
											if(!guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
												guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
												{
													p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
												}
											}
										}
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
									if((j$var42 == j$var60)) {
										for(int i = 0; i < n; i += 1) {
											if(!guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
												guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
												{
													p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
												}
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
					cv$temp$1$var22 = 10.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var21) / Math.sqrt(cv$temp$1$var22))) - (0.5 * Math.log(cv$temp$1$var22))));
				{
					{
						boolean[][] guard$sample32bernoulli71 = guard$sample32bernoulli71$global;
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								if((j$var42 == 0)) {
									for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
										for(int index$j$10_3 = 0; index$j$10_3 < k; index$j$10_3 += 1) {
											if((j$var60 == index$j$10_3)) {
												for(int i = 0; i < n; i += 1)
													guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								if((j$var42 == 1)) {
									for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
										for(int index$j$11_3 = 0; index$j$11_3 < k; index$j$11_3 += 1) {
											if((j$var60 == index$j$11_3)) {
												for(int i = 0; i < n; i += 1)
													guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								if((j$var42 == 2)) {
									for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
										for(int index$j$12_3 = 0; index$j$12_3 < k; index$j$12_3 += 1) {
											if((j$var60 == index$j$12_3)) {
												for(int i = 0; i < n; i += 1)
													guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
									if((j$var42 == j$var60)) {
										for(int index$j$13_3 = 0; index$j$13_3 < k; index$j$13_3 += 1) {
											if((j$var60 == index$j$13_3)) {
												for(int i = 0; i < n; i += 1)
													guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						double traceTempVariable$var43$14_1 = cv$currentValue;
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								for(int i = 0; i < n; i += 1) {
									double traceTempVariable$var50$14_4 = Math.exp((traceTempVariable$var43$14_1 * x[i][j$var42]));
									if((j$var42 == 0)) {
										for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
											double traceTempVariable$var65$14_6 = (indicator[((i - 0) / 1)][j$var60] / ((traceTempVariable$var50$14_4 + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
											for(int index$j$14_7 = 0; index$j$14_7 < k; index$j$14_7 += 1) {
												if((j$var60 == index$j$14_7)) {
													if(!guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
														guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			double cv$temp$2$var66;
																			{
																				double var66 = (traceTempVariable$var65$14_6 + bias);
																				cv$temp$2$var66 = var66;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$14_7], cv$temp$2$var66)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$14_7], cv$temp$2$var66)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$14_7], cv$temp$2$var66));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$14_7], cv$temp$2$var66)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$14_7], cv$temp$2$var66)));
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
						double traceTempVariable$var43$15_1 = cv$currentValue;
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								for(int i = 0; i < n; i += 1) {
									double traceTempVariable$var52$15_4 = Math.exp((traceTempVariable$var43$15_1 * x[i][j$var42]));
									if((j$var42 == 1)) {
										for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
											double traceTempVariable$var65$15_6 = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + traceTempVariable$var52$15_4) + indicator[((i - 0) / 1)][2]));
											for(int index$j$15_7 = 0; index$j$15_7 < k; index$j$15_7 += 1) {
												if((j$var60 == index$j$15_7)) {
													if(!guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
														guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			double cv$temp$3$var66;
																			{
																				double var66 = (traceTempVariable$var65$15_6 + bias);
																				cv$temp$3$var66 = var66;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$15_7], cv$temp$3$var66)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$15_7], cv$temp$3$var66)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$15_7], cv$temp$3$var66));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$15_7], cv$temp$3$var66)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$15_7], cv$temp$3$var66)));
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
						double traceTempVariable$var43$16_1 = cv$currentValue;
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								for(int i = 0; i < n; i += 1) {
									double traceTempVariable$var55$16_4 = Math.exp((traceTempVariable$var43$16_1 * x[i][j$var42]));
									if((j$var42 == 2)) {
										for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
											double traceTempVariable$var65$16_6 = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + traceTempVariable$var55$16_4));
											for(int index$j$16_7 = 0; index$j$16_7 < k; index$j$16_7 += 1) {
												if((j$var60 == index$j$16_7)) {
													if(!guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
														guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			double cv$temp$4$var66;
																			{
																				double var66 = (traceTempVariable$var65$16_6 + bias);
																				cv$temp$4$var66 = var66;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$16_7], cv$temp$4$var66)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$16_7], cv$temp$4$var66)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$16_7], cv$temp$4$var66));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$16_7], cv$temp$4$var66)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$16_7], cv$temp$4$var66)));
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
						double traceTempVariable$var43$17_1 = cv$currentValue;
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								for(int i = 0; i < n; i += 1) {
									double traceTempVariable$var61$17_4 = Math.exp((traceTempVariable$var43$17_1 * x[i][j$var42]));
									for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
										if((j$var42 == j$var60)) {
											double traceTempVariable$var65$17_6 = (traceTempVariable$var61$17_4 / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
											for(int index$j$17_7 = 0; index$j$17_7 < k; index$j$17_7 += 1) {
												if((j$var60 == index$j$17_7)) {
													if(!guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
														guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			double cv$temp$5$var66;
																			{
																				double var66 = (traceTempVariable$var65$17_6 + bias);
																				cv$temp$5$var66 = var66;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$17_7], cv$temp$5$var66)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$17_7], cv$temp$5$var66)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$17_7], cv$temp$5$var66));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$17_7], cv$temp$5$var66)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$17_7], cv$temp$5$var66)));
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
			double var28 = cv$originalValue;
			weights[var27] = var28;
			{
				for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
					if((var27 == j$var42)) {
						for(int i = 0; i < n; i += 1)
							indicator[((i - 0) / 1)][j$var42] = Math.exp((weights[j$var42] * x[i][j$var42]));
					}
				}
			}
			{
				boolean[][] guard$sample32put67 = guard$sample32put67$global;
				for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
					if((var27 == j$var42)) {
						if((j$var42 == 0)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var60 = 0; j$var60 < k; j$var60 += 1)
									guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
					if((var27 == j$var42)) {
						if((j$var42 == 1)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var60 = 0; j$var60 < k; j$var60 += 1)
									guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
					if((var27 == j$var42)) {
						if((j$var42 == 2)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var60 = 0; j$var60 < k; j$var60 += 1)
									guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
					if((var27 == j$var42)) {
						for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
							if((j$var42 == j$var60)) {
								for(int i = 0; i < n; i += 1)
									guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
					if((var27 == j$var42)) {
						if((j$var42 == 0)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
									if(!guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
										guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
										{
											p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
										}
									}
								}
							}
						}
					}
				}
				for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
					if((var27 == j$var42)) {
						if((j$var42 == 1)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
									if(!guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
										guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
										{
											p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
										}
									}
								}
							}
						}
					}
				}
				for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
					if((var27 == j$var42)) {
						if((j$var42 == 2)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
									if(!guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
										guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
										{
											p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
										}
									}
								}
							}
						}
					}
				}
				for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
					if((var27 == j$var42)) {
						for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
							if((j$var42 == j$var60)) {
								for(int i = 0; i < n; i += 1) {
									if(!guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
										guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
										{
											p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void sample39() {
		double cv$originalValue = bias;
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
					bias = cv$proposedValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var32;
				{
					cv$temp$0$var32 = 0.0;
				}
				double cv$temp$1$var33;
				{
					cv$temp$1$var33 = 10.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var32) / Math.sqrt(cv$temp$1$var33))) - (0.5 * Math.log(cv$temp$1$var33))));
				{
					{
						for(int i = 0; i < n; i += 1) {
							for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
								double traceTempVariable$bias$1_3 = cv$currentValue;
								{
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double cv$temp$2$var66;
													{
														double var66 = (p[((i - 0) / 1)][j$var60] + traceTempVariable$bias$1_3);
														cv$temp$2$var66 = var66;
													}
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][j$var60], cv$temp$2$var66)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][j$var60], cv$temp$2$var66)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][j$var60], cv$temp$2$var66));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][j$var60], cv$temp$2$var66)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][j$var60], cv$temp$2$var66)));
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
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio)))
			bias = cv$originalValue;
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max_i = 0;
			int cv$max_j$var60 = 0;
			for(int i = 0; i < x.length; i += 1)
				cv$max_j$var60 = Math.max(cv$max_j$var60, ((3 - 0) / 1));
			cv$max_i = Math.max(cv$max_i, ((x.length - 0) / 1));
			guard$sample32put67$global = new boolean[cv$max_i][cv$max_j$var60];
		}
		{
			int cv$max_i = 0;
			int cv$max_j$var60 = 0;
			for(int i = 0; i < x.length; i += 1)
				cv$max_j$var60 = Math.max(cv$max_j$var60, ((3 - 0) / 1));
			cv$max_i = Math.max(cv$max_i, ((x.length - 0) / 1));
			guard$sample32bernoulli71$global = new boolean[cv$max_i][cv$max_j$var60];
		}
	}

	@Override
	public final void allocator() {
		if(!setFlag$y) {
			{
				y = new boolean[x.length][];
				for(int var16 = 0; var16 < x.length; var16 += 1)
					y[var16] = new boolean[3];
			}
		}
		if(!setFlag$weights) {
			{
				weights = new double[3];
			}
		}
		{
			indicator = new double[((((x.length - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < x.length; i += 1)
				indicator[((i - 0) / 1)] = new double[3];
		}
		{
			p = new double[((((x.length - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < x.length; i += 1)
				p[((i - 0) / 1)] = new double[3];
		}
		{
			logProbability$sample32 = new double[((((3 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var67 = new double[((((x.length - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < x.length; i += 1)
				logProbability$var67[((i - 0) / 1)] = new double[((((3 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample72 = new double[((((x.length - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < x.length; i += 1)
				logProbability$sample72[((i - 0) / 1)] = new double[((((3 - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						if(!fixedFlag$sample32)
							weights[var27] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample39)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						boolean[] var64 = y[i];
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1) {
										if(!fixedFlag$sample32)
											indicator[((i - 0) / 1)][j$var42] = Math.exp((weights[j$var42] * x[i][j$var42]));
									}
							}
						);
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var60, int forEnd$j$var60, int threadID$j$var60, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var60 = forStart$j$var60; j$var60 < forEnd$j$var60; j$var60 += 1) {
										if(!fixedFlag$sample32)
											p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
										if(!fixedFlag$sample72)
											var64[j$var60] = DistributionSampling.sampleBernoulli(RNG$2, (p[((i - 0) / 1)][j$var60] + bias));
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						if(!fixedFlag$sample32)
							weights[var27] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample39)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1) {
										if(!fixedFlag$sample32)
											indicator[((i - 0) / 1)][j$var42] = Math.exp((weights[j$var42] * x[i][j$var42]));
									}
							}
						);
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var60, int forEnd$j$var60, int threadID$j$var60, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var60 = forStart$j$var60; j$var60 < forEnd$j$var60; j$var60 += 1) {
										if(!fixedFlag$sample32)
											p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
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
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						if(!fixedFlag$sample32)
							weights[var27] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample39)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1) {
										if(!fixedFlag$sample32)
											indicator[((i - 0) / 1)][j$var42] = Math.exp((weights[j$var42] * x[i][j$var42]));
									}
							}
						);
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var60, int forEnd$j$var60, int threadID$j$var60, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var60 = forStart$j$var60; j$var60 < forEnd$j$var60; j$var60 += 1) {
										if(!fixedFlag$sample32)
											p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
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
			for(int var27 = 0; var27 < k; var27 += 1) {
				if(!fixedFlag$sample32)
					sample32(var27);
			}
			if(!fixedFlag$sample39)
				sample39();
		} else {
			if(!fixedFlag$sample39)
				sample39();
			for(int var27 = (k - ((((k - 1) - 0) % 1) + 1)); var27 >= ((0 - 1) + 1); var27 -= 1) {
				if(!fixedFlag$sample32)
					sample32(var27);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		k = 3;
		n = x.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var23 = 0.0;
		logProbability$weights = 0.0;
		logProbability$p = 0.0;
		logProbability$indicator = 0.0;
		if(!fixedProbFlag$sample32) {
			for(int var27 = 0; var27 < k; var27 += 1)
				logProbability$sample32[((var27 - 0) / 1)] = 0.0;
		}
		logProbability$var34 = 0.0;
		if(!fixedProbFlag$sample39)
			logProbability$bias = 0.0;
		for(int i = 0; i < n; i += 1) {
			for(int j$var60 = 0; j$var60 < k; j$var60 += 1)
				logProbability$var67[((i - 0) / 1)][((j$var60 - 0) / 1)] = 0.0;
		}
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample72) {
			for(int i = 0; i < n; i += 1) {
				for(int j$var60 = 0; j$var60 < k; j$var60 += 1)
					logProbability$sample72[((i - 0) / 1)][((j$var60 - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample39)
			logProbabilityValue$sample39();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample32();
		logProbabilityValue$sample39();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample32();
		logProbabilityValue$sample39();
		logProbabilityValue$sample72();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						if(!fixedFlag$sample32)
							weights[var27] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample39)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1) {
										if(!fixedFlag$sample32)
											indicator[((i - 0) / 1)][j$var42] = Math.exp((weights[j$var42] * x[i][j$var42]));
									}
							}
						);
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var60, int forEnd$j$var60, int threadID$j$var60, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var60 = forStart$j$var60; j$var60 < forEnd$j$var60; j$var60 += 1) {
										if(!fixedFlag$sample32)
											p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
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
		boolean[][] cv$source1 = yMeasured;
		boolean[][] cv$target1 = y;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[] cv$source2 = cv$source1[cv$index1];
			boolean[] cv$target2 = cv$target1[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	@Override
	public final void setIntermediates() {
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1) {
										if(setFlag$weights)
											indicator[((i - 0) / 1)][j$var42] = Math.exp((weights[j$var42] * x[i][j$var42]));
									}
							}
						);
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var60, int forEnd$j$var60, int threadID$j$var60, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var60 = forStart$j$var60; j$var60 < forEnd$j$var60; j$var60 += 1) {
										if(setFlag$weights)
											p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
									}
							}
						);
					}
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel LogitRegressionTest(double[][] x, boolean[][] yMeasured) {\n    int k = 3;\n\n    int n = x.length;\n    boolean[][] y = new boolean[n][k];\n\n    double[] weights = gaussian(0,10).sample(k);\n    //TODO, change this to a beta distribution.\n    double bias = gaussian(0,10).sample();\n\n    for(int i:[0 .. n)) {\n        double[] indicator = new double[k];\n        for(int j:[0 .. k)) {\n            indicator[j] = exp(weights[j] * x[i][j]);\n        }\n        \n        //Single assignment semantics means a for loop cannot be used here.\n        double sum = indicator[0] + indicator[1] + indicator[2];\n        double[] p = new double[k];\n\n        for(int j:[0 .. k)) {\n            p[j] = indicator[j]/sum;\n            //This really wants to be a Categorical, but for now y will have\n            //to be arrays with just a single value set.\n            y[i][j] = bernoulli(p[j] + bias).sample();\n        }    \n    }\n\n    y.observe(yMeasured);\n}\n";
	}
}