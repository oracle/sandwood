package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LogitRegressionTest$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements LogitRegressionTest$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample105 = false;
	private boolean fixedFlag$sample46 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedProbFlag$sample105 = false;
	private boolean fixedProbFlag$sample46 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean[][] guard$sample46bernoulli104$global;
	private boolean[][] guard$sample46put100$global;
	private double[][] indicator;
	private int k;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$indicator;
	private double logProbability$p;
	private double[][] logProbability$sample105;
	private double[] logProbability$sample46;
	private double[][] logProbability$var100;
	private double logProbability$var30;
	private double logProbability$var48;
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

	public LogitRegressionTest$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double cv$value) {
		bias = cv$value;
		fixedProbFlag$sample53 = false;
		fixedProbFlag$sample105 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample105() {
		return fixedFlag$sample105;
	}

	@Override
	public final void set$fixedFlag$sample105(boolean cv$value) {
		fixedFlag$sample105 = cv$value;
		fixedProbFlag$sample105 = (fixedFlag$sample105 && fixedProbFlag$sample105);
	}

	@Override
	public final boolean get$fixedFlag$sample46() {
		return fixedFlag$sample46;
	}

	@Override
	public final void set$fixedFlag$sample46(boolean cv$value) {
		fixedFlag$sample46 = cv$value;
		fixedProbFlag$sample46 = (fixedFlag$sample46 && fixedProbFlag$sample46);
		fixedProbFlag$sample105 = (fixedFlag$sample46 && fixedProbFlag$sample105);
	}

	@Override
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	@Override
	public final void set$fixedFlag$sample53(boolean cv$value) {
		fixedFlag$sample53 = cv$value;
		fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedProbFlag$sample53);
		fixedProbFlag$sample105 = (fixedFlag$sample53 && fixedProbFlag$sample105);
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
		fixedProbFlag$sample46 = false;
		fixedProbFlag$sample105 = false;
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
		fixedProbFlag$sample105 = false;
	}

	@Override
	public final boolean[][] get$yMeasured() {
		return yMeasured;
	}

	@Override
	public final void set$yMeasured(boolean[][] cv$value) {
		yMeasured = cv$value;
	}

	private final void logProbabilityValue$sample105() {
		if(!fixedProbFlag$sample105) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						boolean cv$sampleValue = y[i][j$var93];
						{
							{
								double var99 = (p[((i - 0) / 1)][j$var93] + bias);
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var99));
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
					logProbability$var100[((i - 0) / 1)][((j$var93 - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample105[((i - 0) / 1)][((j$var93 - 0) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample105 = ((fixedFlag$sample105 && fixedFlag$sample46) && fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample105[((i - 0) / 1)][((j$var93 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var100[((i - 0) / 1)][((j$var93 - 0) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample46() {
		if(!fixedProbFlag$sample46) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var41 = 0; var41 < k; var41 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = weights[var41];
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
				logProbability$sample46[((var41 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$indicator = false;
				boolean cv$guard$p = false;
				{
					for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
						if((var41 == j$var69)) {
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
					for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
						if((var41 == j$var69)) {
							if((j$var69 == 0)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
										if(!cv$guard$p) {
											cv$guard$p = true;
											logProbability$p = (logProbability$p + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
					for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
						if((var41 == j$var69)) {
							if((j$var69 == 1)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
										if(!cv$guard$p) {
											cv$guard$p = true;
											logProbability$p = (logProbability$p + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
					for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
						if((var41 == j$var69)) {
							if((j$var69 == 2)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
										if(!cv$guard$p) {
											cv$guard$p = true;
											logProbability$p = (logProbability$p + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
					for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
						if((var41 == j$var69)) {
							for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
								if((j$var69 == j$var93)) {
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
			logProbability$var30 = cv$sampleAccumulator;
			logProbability$weights = (logProbability$weights + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample46 = fixedFlag$sample46;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var41 = 0; var41 < k; var41 += 1) {
				double cv$sampleValue = logProbability$sample46[((var41 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				boolean cv$guard$indicator = false;
				boolean cv$guard$p = false;
				{
					for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
						if((var41 == j$var69)) {
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
					for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
						if((var41 == j$var69)) {
							if((j$var69 == 0)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
										if(!cv$guard$p) {
											cv$guard$p = true;
											logProbability$p = (logProbability$p + cv$sampleValue);
										}
									}
								}
							}
						}
					}
					for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
						if((var41 == j$var69)) {
							if((j$var69 == 1)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
										if(!cv$guard$p) {
											cv$guard$p = true;
											logProbability$p = (logProbability$p + cv$sampleValue);
										}
									}
								}
							}
						}
					}
					for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
						if((var41 == j$var69)) {
							if((j$var69 == 2)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
										if(!cv$guard$p) {
											cv$guard$p = true;
											logProbability$p = (logProbability$p + cv$sampleValue);
										}
									}
								}
							}
						}
					}
					for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
						if((var41 == j$var69)) {
							for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
								if((j$var69 == j$var93)) {
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
			logProbability$var30 = cv$rvAccumulator;
			logProbability$weights = (logProbability$weights + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample46)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = bias;
				{
					{
						double var46 = 0.0;
						double var47 = 10.0;
						double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var46) / Math.sqrt(var47))) - (0.5 * Math.log(var47))));
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
			logProbability$var48 = cv$sampleAccumulator;
			logProbability$bias = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample53 = fixedFlag$sample53;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$bias;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var48 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample46(int var41) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = weights[var41];
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
					double var42 = cv$proposedValue;
					weights[var41] = cv$currentValue;
					{
						for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
							if((var41 == j$var69)) {
								for(int i = 0; i < n; i += 1)
									indicator[((i - 0) / 1)][j$var69] = Math.exp((weights[j$var69] * x[i][j$var69]));
							}
						}
					}
					{
						boolean[][] guard$sample46put100 = guard$sample46put100$global;
						for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
							if((var41 == j$var69)) {
								if((j$var69 == 0)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var93 = 0; j$var93 < k; j$var93 += 1)
											guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
							if((var41 == j$var69)) {
								if((j$var69 == 1)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var93 = 0; j$var93 < k; j$var93 += 1)
											guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
							if((var41 == j$var69)) {
								if((j$var69 == 2)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var93 = 0; j$var93 < k; j$var93 += 1)
											guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
							if((var41 == j$var69)) {
								for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
									if((j$var69 == j$var93)) {
										for(int i = 0; i < n; i += 1)
											guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
							if((var41 == j$var69)) {
								if((j$var69 == 0)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
											if(!guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)]) {
												guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)] = true;
												{
													p[((i - 0) / 1)][j$var93] = (indicator[((i - 0) / 1)][j$var93] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
												}
											}
										}
									}
								}
							}
						}
						for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
							if((var41 == j$var69)) {
								if((j$var69 == 1)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
											if(!guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)]) {
												guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)] = true;
												{
													p[((i - 0) / 1)][j$var93] = (indicator[((i - 0) / 1)][j$var93] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
												}
											}
										}
									}
								}
							}
						}
						for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
							if((var41 == j$var69)) {
								if((j$var69 == 2)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
											if(!guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)]) {
												guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)] = true;
												{
													p[((i - 0) / 1)][j$var93] = (indicator[((i - 0) / 1)][j$var93] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
												}
											}
										}
									}
								}
							}
						}
						for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
							if((var41 == j$var69)) {
								for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
									if((j$var69 == j$var93)) {
										for(int i = 0; i < n; i += 1) {
											if(!guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)]) {
												guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)] = true;
												{
													p[((i - 0) / 1)][j$var93] = (indicator[((i - 0) / 1)][j$var93] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
												}
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
				double cv$temp$0$var28;
				{
					cv$temp$0$var28 = 0.0;
				}
				double cv$temp$1$var29;
				{
					cv$temp$1$var29 = 10.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var28) / Math.sqrt(cv$temp$1$var29))) - (0.5 * Math.log(cv$temp$1$var29))));
				{
					{
						boolean[][] guard$sample46bernoulli104 = guard$sample46bernoulli104$global;
						for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
							if((var41 == j$var69)) {
								if((j$var69 == 0)) {
									for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
										for(int index$j$10_3 = 0; index$j$10_3 < k; index$j$10_3 += 1) {
											if((j$var93 == index$j$10_3)) {
												for(int i = 0; i < n; i += 1)
													guard$sample46bernoulli104[((i - 0) / 1)][((j$var93 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
							if((var41 == j$var69)) {
								if((j$var69 == 1)) {
									for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
										for(int index$j$11_3 = 0; index$j$11_3 < k; index$j$11_3 += 1) {
											if((j$var93 == index$j$11_3)) {
												for(int i = 0; i < n; i += 1)
													guard$sample46bernoulli104[((i - 0) / 1)][((j$var93 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
							if((var41 == j$var69)) {
								if((j$var69 == 2)) {
									for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
										for(int index$j$12_3 = 0; index$j$12_3 < k; index$j$12_3 += 1) {
											if((j$var93 == index$j$12_3)) {
												for(int i = 0; i < n; i += 1)
													guard$sample46bernoulli104[((i - 0) / 1)][((j$var93 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
							if((var41 == j$var69)) {
								for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
									if((j$var69 == j$var93)) {
										for(int index$j$13_3 = 0; index$j$13_3 < k; index$j$13_3 += 1) {
											if((j$var93 == index$j$13_3)) {
												for(int i = 0; i < n; i += 1)
													guard$sample46bernoulli104[((i - 0) / 1)][((j$var93 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						double traceTempVariable$var70$14_1 = cv$currentValue;
						for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
							if((var41 == j$var69)) {
								for(int i = 0; i < n; i += 1) {
									double traceTempVariable$var77$14_4 = Math.exp((traceTempVariable$var70$14_1 * x[i][j$var69]));
									if((j$var69 == 0)) {
										for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
											double traceTempVariable$var98$14_6 = (indicator[((i - 0) / 1)][j$var93] / ((traceTempVariable$var77$14_4 + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
											for(int index$j$14_7 = 0; index$j$14_7 < k; index$j$14_7 += 1) {
												if((j$var93 == index$j$14_7)) {
													if(!guard$sample46bernoulli104[((i - 0) / 1)][((j$var93 - 0) / 1)]) {
														guard$sample46bernoulli104[((i - 0) / 1)][((j$var93 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			double cv$temp$2$var99;
																			{
																				double var99 = (traceTempVariable$var98$14_6 + bias);
																				cv$temp$2$var99 = var99;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$14_7], cv$temp$2$var99)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$14_7], cv$temp$2$var99)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$14_7], cv$temp$2$var99));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$14_7], cv$temp$2$var99)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$14_7], cv$temp$2$var99)));
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
						double traceTempVariable$var70$15_1 = cv$currentValue;
						for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
							if((var41 == j$var69)) {
								for(int i = 0; i < n; i += 1) {
									double traceTempVariable$var79$15_4 = Math.exp((traceTempVariable$var70$15_1 * x[i][j$var69]));
									if((j$var69 == 1)) {
										for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
											double traceTempVariable$var98$15_6 = (indicator[((i - 0) / 1)][j$var93] / ((indicator[((i - 0) / 1)][0] + traceTempVariable$var79$15_4) + indicator[((i - 0) / 1)][2]));
											for(int index$j$15_7 = 0; index$j$15_7 < k; index$j$15_7 += 1) {
												if((j$var93 == index$j$15_7)) {
													if(!guard$sample46bernoulli104[((i - 0) / 1)][((j$var93 - 0) / 1)]) {
														guard$sample46bernoulli104[((i - 0) / 1)][((j$var93 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			double cv$temp$3$var99;
																			{
																				double var99 = (traceTempVariable$var98$15_6 + bias);
																				cv$temp$3$var99 = var99;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$15_7], cv$temp$3$var99)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$15_7], cv$temp$3$var99)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$15_7], cv$temp$3$var99));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$15_7], cv$temp$3$var99)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$15_7], cv$temp$3$var99)));
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
						double traceTempVariable$var70$16_1 = cv$currentValue;
						for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
							if((var41 == j$var69)) {
								for(int i = 0; i < n; i += 1) {
									double traceTempVariable$var82$16_4 = Math.exp((traceTempVariable$var70$16_1 * x[i][j$var69]));
									if((j$var69 == 2)) {
										for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
											double traceTempVariable$var98$16_6 = (indicator[((i - 0) / 1)][j$var93] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + traceTempVariable$var82$16_4));
											for(int index$j$16_7 = 0; index$j$16_7 < k; index$j$16_7 += 1) {
												if((j$var93 == index$j$16_7)) {
													if(!guard$sample46bernoulli104[((i - 0) / 1)][((j$var93 - 0) / 1)]) {
														guard$sample46bernoulli104[((i - 0) / 1)][((j$var93 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			double cv$temp$4$var99;
																			{
																				double var99 = (traceTempVariable$var98$16_6 + bias);
																				cv$temp$4$var99 = var99;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$16_7], cv$temp$4$var99)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$16_7], cv$temp$4$var99)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$16_7], cv$temp$4$var99));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$16_7], cv$temp$4$var99)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$16_7], cv$temp$4$var99)));
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
						double traceTempVariable$var70$17_1 = cv$currentValue;
						for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
							if((var41 == j$var69)) {
								for(int i = 0; i < n; i += 1) {
									double traceTempVariable$var94$17_4 = Math.exp((traceTempVariable$var70$17_1 * x[i][j$var69]));
									for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
										if((j$var69 == j$var93)) {
											double traceTempVariable$var98$17_6 = (traceTempVariable$var94$17_4 / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
											for(int index$j$17_7 = 0; index$j$17_7 < k; index$j$17_7 += 1) {
												if((j$var93 == index$j$17_7)) {
													if(!guard$sample46bernoulli104[((i - 0) / 1)][((j$var93 - 0) / 1)]) {
														guard$sample46bernoulli104[((i - 0) / 1)][((j$var93 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			double cv$temp$5$var99;
																			{
																				double var99 = (traceTempVariable$var98$17_6 + bias);
																				cv$temp$5$var99 = var99;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$17_7], cv$temp$5$var99)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$17_7], cv$temp$5$var99)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$17_7], cv$temp$5$var99));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$17_7], cv$temp$5$var99)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$17_7], cv$temp$5$var99)));
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
			double var42 = cv$originalValue;
			weights[var41] = var42;
			{
				for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
					if((var41 == j$var69)) {
						for(int i = 0; i < n; i += 1)
							indicator[((i - 0) / 1)][j$var69] = Math.exp((weights[j$var69] * x[i][j$var69]));
					}
				}
			}
			{
				boolean[][] guard$sample46put100 = guard$sample46put100$global;
				for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
					if((var41 == j$var69)) {
						if((j$var69 == 0)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var93 = 0; j$var93 < k; j$var93 += 1)
									guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
					if((var41 == j$var69)) {
						if((j$var69 == 1)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var93 = 0; j$var93 < k; j$var93 += 1)
									guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
					if((var41 == j$var69)) {
						if((j$var69 == 2)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var93 = 0; j$var93 < k; j$var93 += 1)
									guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
					if((var41 == j$var69)) {
						for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
							if((j$var69 == j$var93)) {
								for(int i = 0; i < n; i += 1)
									guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
					if((var41 == j$var69)) {
						if((j$var69 == 0)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
									if(!guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)]) {
										guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)] = true;
										{
											p[((i - 0) / 1)][j$var93] = (indicator[((i - 0) / 1)][j$var93] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
										}
									}
								}
							}
						}
					}
				}
				for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
					if((var41 == j$var69)) {
						if((j$var69 == 1)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
									if(!guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)]) {
										guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)] = true;
										{
											p[((i - 0) / 1)][j$var93] = (indicator[((i - 0) / 1)][j$var93] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
										}
									}
								}
							}
						}
					}
				}
				for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
					if((var41 == j$var69)) {
						if((j$var69 == 2)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
									if(!guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)]) {
										guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)] = true;
										{
											p[((i - 0) / 1)][j$var93] = (indicator[((i - 0) / 1)][j$var93] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
										}
									}
								}
							}
						}
					}
				}
				for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
					if((var41 == j$var69)) {
						for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
							if((j$var69 == j$var93)) {
								for(int i = 0; i < n; i += 1) {
									if(!guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)]) {
										guard$sample46put100[((i - 0) / 1)][((j$var93 - 0) / 1)] = true;
										{
											p[((i - 0) / 1)][j$var93] = (indicator[((i - 0) / 1)][j$var93] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void sample53() {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = bias;
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
					bias = cv$proposedValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var46;
				{
					cv$temp$0$var46 = 0.0;
				}
				double cv$temp$1$var47;
				{
					cv$temp$1$var47 = 10.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var46) / Math.sqrt(cv$temp$1$var47))) - (0.5 * Math.log(cv$temp$1$var47))));
				{
					{
						for(int i = 0; i < n; i += 1) {
							for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
								double traceTempVariable$bias$1_3 = cv$currentValue;
								{
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double cv$temp$2$var99;
													{
														double var99 = (p[((i - 0) / 1)][j$var93] + traceTempVariable$bias$1_3);
														cv$temp$2$var99 = var99;
													}
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][j$var93], cv$temp$2$var99)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][j$var93], cv$temp$2$var99)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][j$var93], cv$temp$2$var99));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][j$var93], cv$temp$2$var99)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][j$var93], cv$temp$2$var99)));
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
			int cv$max_j$var93 = 0;
			for(int i = 0; i < x.length; i += 1)
				cv$max_j$var93 = Math.max(cv$max_j$var93, ((3 - 0) / 1));
			cv$max_i = Math.max(cv$max_i, ((x.length - 0) / 1));
			guard$sample46put100$global = new boolean[cv$max_i][cv$max_j$var93];
		}
		{
			int cv$max_i = 0;
			int cv$max_j$var93 = 0;
			for(int i = 0; i < x.length; i += 1)
				cv$max_j$var93 = Math.max(cv$max_j$var93, ((3 - 0) / 1));
			cv$max_i = Math.max(cv$max_i, ((x.length - 0) / 1));
			guard$sample46bernoulli104$global = new boolean[cv$max_i][cv$max_j$var93];
		}
	}

	@Override
	public final void allocator() {
		if(!setFlag$y) {
			{
				y = new boolean[x.length][];
				for(int var23 = 0; var23 < x.length; var23 += 1)
					y[var23] = new boolean[3];
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
			logProbability$sample46 = new double[((((3 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var100 = new double[((((x.length - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < x.length; i += 1)
				logProbability$var100[((i - 0) / 1)] = new double[((((3 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample105 = new double[((((x.length - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < x.length; i += 1)
				logProbability$sample105[((i - 0) / 1)] = new double[((((3 - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int var41 = 0; var41 < k; var41 += 1) {
			if(!fixedFlag$sample46)
				weights[var41] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		if(!fixedFlag$sample53)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		for(int i = 0; i < n; i += 1) {
			boolean[] var97 = y[i];
			for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
				if(!fixedFlag$sample46)
					indicator[((i - 0) / 1)][j$var69] = Math.exp((weights[j$var69] * x[i][j$var69]));
			}
			for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
				if(!fixedFlag$sample46)
					p[((i - 0) / 1)][j$var93] = (indicator[((i - 0) / 1)][j$var93] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
				if(!fixedFlag$sample105)
					var97[j$var93] = DistributionSampling.sampleBernoulli(RNG$, (p[((i - 0) / 1)][j$var93] + bias));
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var41 = 0; var41 < k; var41 += 1) {
			if(!fixedFlag$sample46)
				weights[var41] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		if(!fixedFlag$sample53)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		for(int i = 0; i < n; i += 1) {
			for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
				if(!fixedFlag$sample46)
					indicator[((i - 0) / 1)][j$var69] = Math.exp((weights[j$var69] * x[i][j$var69]));
			}
			for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
				if(!fixedFlag$sample46)
					p[((i - 0) / 1)][j$var93] = (indicator[((i - 0) / 1)][j$var93] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var41 = 0; var41 < k; var41 += 1) {
			if(!fixedFlag$sample46)
				weights[var41] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		if(!fixedFlag$sample53)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		for(int i = 0; i < n; i += 1) {
			for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
				if(!fixedFlag$sample46)
					indicator[((i - 0) / 1)][j$var69] = Math.exp((weights[j$var69] * x[i][j$var69]));
			}
			for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
				if(!fixedFlag$sample46)
					p[((i - 0) / 1)][j$var93] = (indicator[((i - 0) / 1)][j$var93] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var41 = 0; var41 < k; var41 += 1) {
				if(!fixedFlag$sample46)
					sample46(var41);
			}
			if(!fixedFlag$sample53)
				sample53();
		} else {
			if(!fixedFlag$sample53)
				sample53();
			for(int var41 = (k - ((((k - 1) - 0) % 1) + 1)); var41 >= ((0 - 1) + 1); var41 -= 1) {
				if(!fixedFlag$sample46)
					sample46(var41);
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
		logProbability$var30 = 0.0;
		logProbability$weights = 0.0;
		logProbability$indicator = 0.0;
		logProbability$p = 0.0;
		if(!fixedProbFlag$sample46) {
			for(int var41 = 0; var41 < k; var41 += 1)
				logProbability$sample46[((var41 - 0) / 1)] = 0.0;
		}
		logProbability$var48 = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$bias = 0.0;
		for(int i = 0; i < n; i += 1) {
			for(int j$var93 = 0; j$var93 < k; j$var93 += 1)
				logProbability$var100[((i - 0) / 1)][((j$var93 - 0) / 1)] = 0.0;
		}
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample105) {
			for(int i = 0; i < n; i += 1) {
				for(int j$var93 = 0; j$var93 < k; j$var93 += 1)
					logProbability$sample105[((i - 0) / 1)][((j$var93 - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample46)
			logProbabilityValue$sample46();
		if(fixedFlag$sample53)
			logProbabilityValue$sample53();
		logProbabilityValue$sample105();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample46();
		logProbabilityValue$sample53();
		logProbabilityValue$sample105();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample46();
		logProbabilityValue$sample53();
		logProbabilityValue$sample105();
	}

	@Override
	public final void logProbabilityGeneration() {
		for(int var41 = 0; var41 < k; var41 += 1) {
			if(!fixedFlag$sample46)
				weights[var41] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		if(!fixedFlag$sample53)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		for(int i = 0; i < n; i += 1) {
			for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
				if(!fixedFlag$sample46)
					indicator[((i - 0) / 1)][j$var69] = Math.exp((weights[j$var69] * x[i][j$var69]));
			}
			for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
				if(!fixedFlag$sample46)
					p[((i - 0) / 1)][j$var93] = (indicator[((i - 0) / 1)][j$var93] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
			}
		}
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
		for(int i = 0; i < n; i += 1) {
			for(int j$var69 = 0; j$var69 < k; j$var69 += 1) {
				if(setFlag$weights)
					indicator[((i - 0) / 1)][j$var69] = Math.exp((weights[j$var69] * x[i][j$var69]));
			}
			for(int j$var93 = 0; j$var93 < k; j$var93 += 1) {
				if(setFlag$weights)
					p[((i - 0) / 1)][j$var93] = (indicator[((i - 0) / 1)][j$var93] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
			}
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel LogitRegressionTest(double[][] x, boolean[][] yMeasured) {\n    int k = 3;\n\n    int n = x.length;\n    boolean[][] y = new boolean[n][k];\n\n    double[] weights = gaussian(0,10).sample(k);\n    //TODO, change this to a beta distribution.\n    double bias = gaussian(0,10).sample();\n\n    for(int i:[0 .. n)) {\n        double[] indicator = new double[k];\n        for(int j:[0 .. k)) {\n            indicator[j] = exp(weights[j] * x[i][j]);\n        }\n        \n        //Single assignment semantics means a for loop cannot be used here.\n        double sum = indicator[0] + indicator[1] + indicator[2];\n        double[] p = new double[k];\n\n        for(int j:[0 .. k)) {\n            p[j] = indicator[j]/sum;\n            //This really wants to be a Categorical, but for now y will have\n            //to be arrays with just a single value set.\n            y[i][j] = bernoulli(p[j] + bias).sample();\n        }    \n    }\n\n    y.observe(yMeasured);\n}\n";
	}
}