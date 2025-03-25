package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LogitRegressionTest$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements LogitRegressionTest$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample42 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample42 = false;
	private boolean fixedProbFlag$sample94 = false;
	private boolean[][] guard$sample35bernoulli93$global;
	private boolean[][] guard$sample35put89$global;
	private double[][] indicator;
	private int k;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$indicator;
	private double logProbability$p;
	private double[] logProbability$sample35;
	private double[][] logProbability$sample94;
	private double logProbability$var22;
	private double logProbability$var40;
	private double[][] logProbability$var92;
	private double logProbability$weights;
	private double logProbability$y;
	private int n;
	private double[][] p;
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
		fixedProbFlag$sample42 = false;
		fixedProbFlag$sample94 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		fixedFlag$sample35 = cv$value;
		fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedProbFlag$sample35);
		fixedProbFlag$sample94 = (fixedFlag$sample35 && fixedProbFlag$sample94);
	}

	@Override
	public final boolean get$fixedFlag$sample42() {
		return fixedFlag$sample42;
	}

	@Override
	public final void set$fixedFlag$sample42(boolean cv$value) {
		fixedFlag$sample42 = cv$value;
		fixedProbFlag$sample42 = (fixedFlag$sample42 && fixedProbFlag$sample42);
		fixedProbFlag$sample94 = (fixedFlag$sample42 && fixedProbFlag$sample94);
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
		fixedProbFlag$sample35 = false;
		fixedProbFlag$sample94 = false;
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
	public final boolean[][] get$yMeasured() {
		return yMeasured;
	}

	@Override
	public final void set$yMeasured(boolean[][] cv$value) {
		yMeasured = cv$value;
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var33 = 0; var33 < k; var33 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = weights[var33];
					{
						{
							double var20 = 0.0;
							double var21 = 10.0;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var20) / Math.sqrt(var21))) - (0.5 * Math.log(var21))));
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
				logProbability$sample35[((var33 - 0) / 1)] = cv$sampleProbability;
				boolean cv$guard$indicator = false;
				boolean cv$guard$p = false;
				{
					for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
						if((var33 == j$var61)) {
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
					for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
						if((var33 == j$var61)) {
							if((j$var61 == 0)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
										if(!cv$guard$p) {
											cv$guard$p = true;
											logProbability$p = (logProbability$p + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
					for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
						if((var33 == j$var61)) {
							if((j$var61 == 1)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
										if(!cv$guard$p) {
											cv$guard$p = true;
											logProbability$p = (logProbability$p + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
					for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
						if((var33 == j$var61)) {
							if((j$var61 == 2)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
										if(!cv$guard$p) {
											cv$guard$p = true;
											logProbability$p = (logProbability$p + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
					for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
						if((var33 == j$var61)) {
							for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
								if((j$var61 == j$var85)) {
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
			logProbability$var22 = cv$sampleAccumulator;
			logProbability$weights = (logProbability$weights + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample35 = fixedFlag$sample35;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			for(int var33 = 0; var33 < k; var33 += 1) {
				double cv$sampleValue = logProbability$sample35[((var33 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				boolean cv$guard$indicator = false;
				boolean cv$guard$p = false;
				{
					for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
						if((var33 == j$var61)) {
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
					for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
						if((var33 == j$var61)) {
							if((j$var61 == 0)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
										if(!cv$guard$p) {
											cv$guard$p = true;
											logProbability$p = (logProbability$p + cv$sampleValue);
										}
									}
								}
							}
						}
					}
					for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
						if((var33 == j$var61)) {
							if((j$var61 == 1)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
										if(!cv$guard$p) {
											cv$guard$p = true;
											logProbability$p = (logProbability$p + cv$sampleValue);
										}
									}
								}
							}
						}
					}
					for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
						if((var33 == j$var61)) {
							if((j$var61 == 2)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
										if(!cv$guard$p) {
											cv$guard$p = true;
											logProbability$p = (logProbability$p + cv$sampleValue);
										}
									}
								}
							}
						}
					}
					for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
						if((var33 == j$var61)) {
							for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
								if((j$var61 == j$var85)) {
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
			logProbability$var22 = cv$rvAccumulator;
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
						double var38 = 0.0;
						double var39 = 10.0;
						double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var38) / Math.sqrt(var39))) - (0.5 * Math.log(var39))));
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
			logProbability$var40 = cv$sampleAccumulator;
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
			logProbability$var40 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample94() {
		if(!fixedProbFlag$sample94) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						boolean cv$sampleValue = y[i][j$var85];
						{
							{
								double var91 = (p[((i - 0) / 1)][j$var85] + bias);
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var91));
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
					logProbability$var92[((i - 0) / 1)][((j$var85 - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample94[((i - 0) / 1)][((j$var85 - 0) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample94 = (fixedFlag$sample35 && fixedFlag$sample42);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample94[((i - 0) / 1)][((j$var85 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var92[((i - 0) / 1)][((j$var85 - 0) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$y = (logProbability$y + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample35(int var33) {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double cv$originalValue = weights[var33];
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
					double var34 = cv$proposedValue;
					{
						{
							weights[var33] = cv$currentValue;
						}
					}
					{
						for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
							if((var33 == j$var61)) {
								for(int i = 0; i < n; i += 1)
									indicator[((i - 0) / 1)][j$var61] = Math.exp((weights[j$var61] * x[i][j$var61]));
							}
						}
					}
					{
						boolean[][] guard$sample35put89 = guard$sample35put89$global;
						for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
							if((var33 == j$var61)) {
								if((j$var61 == 0)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var85 = 0; j$var85 < k; j$var85 += 1)
											guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
							if((var33 == j$var61)) {
								if((j$var61 == 1)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var85 = 0; j$var85 < k; j$var85 += 1)
											guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
							if((var33 == j$var61)) {
								if((j$var61 == 2)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var85 = 0; j$var85 < k; j$var85 += 1)
											guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
							if((var33 == j$var61)) {
								for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
									if((j$var61 == j$var85)) {
										for(int i = 0; i < n; i += 1)
											guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
							if((var33 == j$var61)) {
								if((j$var61 == 0)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
											if(!guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
												guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
												{
													p[((i - 0) / 1)][j$var85] = (indicator[((i - 0) / 1)][j$var85] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
												}
											}
										}
									}
								}
							}
						}
						for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
							if((var33 == j$var61)) {
								if((j$var61 == 1)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
											if(!guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
												guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
												{
													p[((i - 0) / 1)][j$var85] = (indicator[((i - 0) / 1)][j$var85] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
												}
											}
										}
									}
								}
							}
						}
						for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
							if((var33 == j$var61)) {
								if((j$var61 == 2)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
											if(!guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
												guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
												{
													p[((i - 0) / 1)][j$var85] = (indicator[((i - 0) / 1)][j$var85] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
												}
											}
										}
									}
								}
							}
						}
						for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
							if((var33 == j$var61)) {
								for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
									if((j$var61 == j$var85)) {
										for(int i = 0; i < n; i += 1) {
											if(!guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
												guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
												{
													p[((i - 0) / 1)][j$var85] = (indicator[((i - 0) / 1)][j$var85] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
												}
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
				double cv$temp$0$var20;
				{
					cv$temp$0$var20 = 0.0;
				}
				double cv$temp$1$var21;
				{
					cv$temp$1$var21 = 10.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var20) / Math.sqrt(cv$temp$1$var21))) - (0.5 * Math.log(cv$temp$1$var21))));
				{
					{
						boolean[][] guard$sample35bernoulli93 = guard$sample35bernoulli93$global;
						for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
							if((var33 == j$var61)) {
								if((j$var61 == 0)) {
									for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
										for(int index$j$11_3 = 0; index$j$11_3 < k; index$j$11_3 += 1) {
											if((j$var85 == index$j$11_3)) {
												for(int i = 0; i < n; i += 1)
													guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
							if((var33 == j$var61)) {
								if((j$var61 == 1)) {
									for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
										for(int index$j$12_3 = 0; index$j$12_3 < k; index$j$12_3 += 1) {
											if((j$var85 == index$j$12_3)) {
												for(int i = 0; i < n; i += 1)
													guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
							if((var33 == j$var61)) {
								if((j$var61 == 2)) {
									for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
										for(int index$j$13_3 = 0; index$j$13_3 < k; index$j$13_3 += 1) {
											if((j$var85 == index$j$13_3)) {
												for(int i = 0; i < n; i += 1)
													guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
							if((var33 == j$var61)) {
								for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
									if((j$var61 == j$var85)) {
										for(int index$j$14_3 = 0; index$j$14_3 < k; index$j$14_3 += 1) {
											if((j$var85 == index$j$14_3)) {
												for(int i = 0; i < n; i += 1)
													guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						double traceTempVariable$var62$15_1 = cv$currentValue;
						for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
							if((var33 == j$var61)) {
								for(int i = 0; i < n; i += 1) {
									double traceTempVariable$var69$15_4 = Math.exp((traceTempVariable$var62$15_1 * x[i][j$var61]));
									if((j$var61 == 0)) {
										for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
											double traceTempVariable$var90$15_6 = (indicator[((i - 0) / 1)][j$var85] / ((traceTempVariable$var69$15_4 + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
											for(int index$j$15_7 = 0; index$j$15_7 < k; index$j$15_7 += 1) {
												if((j$var85 == index$j$15_7)) {
													if(!guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
														guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			double cv$temp$2$var91;
																			{
																				double var91 = (traceTempVariable$var90$15_6 + bias);
																				cv$temp$2$var91 = var91;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$15_7], cv$temp$2$var91)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$15_7], cv$temp$2$var91)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$15_7], cv$temp$2$var91));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$15_7], cv$temp$2$var91)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$15_7], cv$temp$2$var91)));
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
						double traceTempVariable$var62$16_1 = cv$currentValue;
						for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
							if((var33 == j$var61)) {
								for(int i = 0; i < n; i += 1) {
									double traceTempVariable$var71$16_4 = Math.exp((traceTempVariable$var62$16_1 * x[i][j$var61]));
									if((j$var61 == 1)) {
										for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
											double traceTempVariable$var90$16_6 = (indicator[((i - 0) / 1)][j$var85] / ((indicator[((i - 0) / 1)][0] + traceTempVariable$var71$16_4) + indicator[((i - 0) / 1)][2]));
											for(int index$j$16_7 = 0; index$j$16_7 < k; index$j$16_7 += 1) {
												if((j$var85 == index$j$16_7)) {
													if(!guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
														guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			double cv$temp$3$var91;
																			{
																				double var91 = (traceTempVariable$var90$16_6 + bias);
																				cv$temp$3$var91 = var91;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$16_7], cv$temp$3$var91)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$16_7], cv$temp$3$var91)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$16_7], cv$temp$3$var91));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$16_7], cv$temp$3$var91)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$16_7], cv$temp$3$var91)));
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
						double traceTempVariable$var62$17_1 = cv$currentValue;
						for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
							if((var33 == j$var61)) {
								for(int i = 0; i < n; i += 1) {
									double traceTempVariable$var74$17_4 = Math.exp((traceTempVariable$var62$17_1 * x[i][j$var61]));
									if((j$var61 == 2)) {
										for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
											double traceTempVariable$var90$17_6 = (indicator[((i - 0) / 1)][j$var85] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + traceTempVariable$var74$17_4));
											for(int index$j$17_7 = 0; index$j$17_7 < k; index$j$17_7 += 1) {
												if((j$var85 == index$j$17_7)) {
													if(!guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
														guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			double cv$temp$4$var91;
																			{
																				double var91 = (traceTempVariable$var90$17_6 + bias);
																				cv$temp$4$var91 = var91;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$17_7], cv$temp$4$var91)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$17_7], cv$temp$4$var91)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$17_7], cv$temp$4$var91));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$17_7], cv$temp$4$var91)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$17_7], cv$temp$4$var91)));
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
						double traceTempVariable$var62$18_1 = cv$currentValue;
						for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
							if((var33 == j$var61)) {
								for(int i = 0; i < n; i += 1) {
									double traceTempVariable$var86$18_4 = Math.exp((traceTempVariable$var62$18_1 * x[i][j$var61]));
									for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
										if((j$var61 == j$var85)) {
											double traceTempVariable$var90$18_6 = (traceTempVariable$var86$18_4 / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
											for(int index$j$18_7 = 0; index$j$18_7 < k; index$j$18_7 += 1) {
												if((j$var85 == index$j$18_7)) {
													if(!guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
														guard$sample35bernoulli93[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			double cv$temp$5$var91;
																			{
																				double var91 = (traceTempVariable$var90$18_6 + bias);
																				cv$temp$5$var91 = var91;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$18_7], cv$temp$5$var91)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$18_7], cv$temp$5$var91)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$18_7], cv$temp$5$var91));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$18_7], cv$temp$5$var91)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$18_7], cv$temp$5$var91)));
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
			double var34 = cv$originalValue;
			{
				{
					weights[var33] = var34;
				}
			}
			{
				for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
					if((var33 == j$var61)) {
						for(int i = 0; i < n; i += 1)
							indicator[((i - 0) / 1)][j$var61] = Math.exp((weights[j$var61] * x[i][j$var61]));
					}
				}
			}
			{
				boolean[][] guard$sample35put89 = guard$sample35put89$global;
				for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
					if((var33 == j$var61)) {
						if((j$var61 == 0)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var85 = 0; j$var85 < k; j$var85 += 1)
									guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
					if((var33 == j$var61)) {
						if((j$var61 == 1)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var85 = 0; j$var85 < k; j$var85 += 1)
									guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
					if((var33 == j$var61)) {
						if((j$var61 == 2)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var85 = 0; j$var85 < k; j$var85 += 1)
									guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
					if((var33 == j$var61)) {
						for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
							if((j$var61 == j$var85)) {
								for(int i = 0; i < n; i += 1)
									guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
					if((var33 == j$var61)) {
						if((j$var61 == 0)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
									if(!guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
										guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
										{
											p[((i - 0) / 1)][j$var85] = (indicator[((i - 0) / 1)][j$var85] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
										}
									}
								}
							}
						}
					}
				}
				for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
					if((var33 == j$var61)) {
						if((j$var61 == 1)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
									if(!guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
										guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
										{
											p[((i - 0) / 1)][j$var85] = (indicator[((i - 0) / 1)][j$var85] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
										}
									}
								}
							}
						}
					}
				}
				for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
					if((var33 == j$var61)) {
						if((j$var61 == 2)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
									if(!guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
										guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
										{
											p[((i - 0) / 1)][j$var85] = (indicator[((i - 0) / 1)][j$var85] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
										}
									}
								}
							}
						}
					}
				}
				for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
					if((var33 == j$var61)) {
						for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
							if((j$var61 == j$var85)) {
								for(int i = 0; i < n; i += 1) {
									if(!guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)]) {
										guard$sample35put89[((i - 0) / 1)][((j$var85 - 0) / 1)] = true;
										{
											p[((i - 0) / 1)][j$var85] = (indicator[((i - 0) / 1)][j$var85] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void sample42() {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double cv$originalValue = bias;
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
					bias = cv$proposedValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var38;
				{
					cv$temp$0$var38 = 0.0;
				}
				double cv$temp$1$var39;
				{
					cv$temp$1$var39 = 10.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var38) / Math.sqrt(cv$temp$1$var39))) - (0.5 * Math.log(cv$temp$1$var39))));
				{
					{
						for(int i = 0; i < n; i += 1) {
							for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
								double traceTempVariable$bias$1_3 = cv$currentValue;
								{
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double cv$temp$2$var91;
													{
														double var91 = (p[((i - 0) / 1)][j$var85] + traceTempVariable$bias$1_3);
														cv$temp$2$var91 = var91;
													}
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][j$var85], cv$temp$2$var91)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][j$var85], cv$temp$2$var91)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][j$var85], cv$temp$2$var91));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][j$var85], cv$temp$2$var91)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][j$var85], cv$temp$2$var91)));
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
			int cv$max_j$var85 = 0;
			for(int i = 0; i < x.length; i += 1)
				cv$max_j$var85 = Math.max(cv$max_j$var85, ((3 - 0) / 1));
			cv$max_i = Math.max(cv$max_i, ((x.length - 0) / 1));
			guard$sample35put89$global = new boolean[cv$max_i][cv$max_j$var85];
		}
		{
			int cv$max_i = 0;
			int cv$max_j$var85 = 0;
			for(int i = 0; i < x.length; i += 1)
				cv$max_j$var85 = Math.max(cv$max_j$var85, ((3 - 0) / 1));
			cv$max_i = Math.max(cv$max_i, ((x.length - 0) / 1));
			guard$sample35bernoulli93$global = new boolean[cv$max_i][cv$max_j$var85];
		}
	}

	@Override
	public final void allocator() {
		{
			y = new boolean[x.length][];
			for(int var15 = 0; var15 < x.length; var15 += 1)
				y[var15] = new boolean[3];
		}
		if(!fixedFlag$sample35) {
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
			logProbability$sample35 = new double[((((3 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var92 = new double[((((x.length - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < x.length; i += 1)
				logProbability$var92[((i - 0) / 1)] = new double[((((3 - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample94 = new double[((((x.length - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < x.length; i += 1)
				logProbability$sample94[((i - 0) / 1)] = new double[((((3 - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int var33 = 0; var33 < k; var33 += 1) {
			if(!fixedFlag$sample35)
				weights[var33] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		if(!fixedFlag$sample42)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		for(int i = 0; i < n; i += 1) {
			boolean[] var89 = y[i];
			for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
				if(!fixedFlag$sample35)
					indicator[((i - 0) / 1)][j$var61] = Math.exp((weights[j$var61] * x[i][j$var61]));
			}
			for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
				if(!fixedFlag$sample35)
					p[((i - 0) / 1)][j$var85] = (indicator[((i - 0) / 1)][j$var85] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
				var89[j$var85] = DistributionSampling.sampleBernoulli(RNG$, (p[((i - 0) / 1)][j$var85] + bias));
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var33 = 0; var33 < k; var33 += 1) {
			if(!fixedFlag$sample35)
				weights[var33] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		if(!fixedFlag$sample42)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		for(int i = 0; i < n; i += 1) {
			for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
				if(!fixedFlag$sample35)
					indicator[((i - 0) / 1)][j$var61] = Math.exp((weights[j$var61] * x[i][j$var61]));
			}
			for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
				if(!fixedFlag$sample35)
					p[((i - 0) / 1)][j$var85] = (indicator[((i - 0) / 1)][j$var85] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var33 = 0; var33 < k; var33 += 1) {
			if(!fixedFlag$sample35)
				weights[var33] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		if(!fixedFlag$sample42)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		for(int i = 0; i < n; i += 1) {
			for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
				if(!fixedFlag$sample35)
					indicator[((i - 0) / 1)][j$var61] = Math.exp((weights[j$var61] * x[i][j$var61]));
			}
			for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
				if(!fixedFlag$sample35)
					p[((i - 0) / 1)][j$var85] = (indicator[((i - 0) / 1)][j$var85] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var33 = 0; var33 < k; var33 += 1) {
				if(!fixedFlag$sample35)
					sample35(var33);
			}
			if(!fixedFlag$sample42)
				sample42();
		} else {
			if(!fixedFlag$sample42)
				sample42();
			for(int var33 = (k - ((((k - 1) - 0) % 1) + 1)); var33 >= ((0 - 1) + 1); var33 -= 1) {
				if(!fixedFlag$sample35)
					sample35(var33);
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
		logProbability$var22 = 0.0;
		logProbability$weights = 0.0;
		logProbability$indicator = 0.0;
		logProbability$p = 0.0;
		if(!fixedProbFlag$sample35) {
			for(int var33 = 0; var33 < k; var33 += 1)
				logProbability$sample35[((var33 - 0) / 1)] = 0.0;
		}
		logProbability$var40 = 0.0;
		if(!fixedProbFlag$sample42)
			logProbability$bias = 0.0;
		for(int i = 0; i < n; i += 1) {
			for(int j$var85 = 0; j$var85 < k; j$var85 += 1)
				logProbability$var92[((i - 0) / 1)][((j$var85 - 0) / 1)] = 0.0;
		}
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample94) {
			for(int i = 0; i < n; i += 1) {
				for(int j$var85 = 0; j$var85 < k; j$var85 += 1)
					logProbability$sample94[((i - 0) / 1)][((j$var85 - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		if(fixedFlag$sample42)
			logProbabilityValue$sample42();
		logProbabilityValue$sample94();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample35();
		logProbabilityValue$sample42();
		logProbabilityValue$sample94();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample35();
		logProbabilityValue$sample42();
		logProbabilityValue$sample94();
	}

	@Override
	public final void logProbabilityGeneration() {
		for(int var33 = 0; var33 < k; var33 += 1) {
			if(!fixedFlag$sample35)
				weights[var33] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		if(!fixedFlag$sample42)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		for(int i = 0; i < n; i += 1) {
			for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
				if(!fixedFlag$sample35)
					indicator[((i - 0) / 1)][j$var61] = Math.exp((weights[j$var61] * x[i][j$var61]));
			}
			for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
				if(!fixedFlag$sample35)
					p[((i - 0) / 1)][j$var85] = (indicator[((i - 0) / 1)][j$var85] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
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
			for(int j$var61 = 0; j$var61 < k; j$var61 += 1) {
				if(fixedFlag$sample35)
					indicator[((i - 0) / 1)][j$var61] = Math.exp((weights[j$var61] * x[i][j$var61]));
			}
			for(int j$var85 = 0; j$var85 < k; j$var85 += 1) {
				if(fixedFlag$sample35)
					p[((i - 0) / 1)][j$var85] = (indicator[((i - 0) / 1)][j$var85] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
			}
		}
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
		     + "model LogitRegressionTest(double[][] x, boolean[][] yMeasured) {\n"
		     + "    int k = 3;\n"
		     + "\n"
		     + "    int n = x.length;\n"
		     + "    boolean[][] y = new boolean[n][k];\n"
		     + "\n"
		     + "    double[] weights = gaussian(0,10).sample(k);\n"
		     + "    //TODO, change this to a beta distribution.\n"
		     + "    double bias = gaussian(0,10).sample();\n"
		     + "\n"
		     + "    for(int i:[0 .. n)) {\n"
		     + "        double[] indicator = new double[k];\n"
		     + "        for(int j:[0 .. k)) {\n"
		     + "            indicator[j] = exp(weights[j] * x[i][j]);\n"
		     + "        }\n"
		     + "        \n"
		     + "        //Single assignment semantics means a for loop cannot be used here.\n"
		     + "        double sum = indicator[0] + indicator[1] + indicator[2];\n"
		     + "        double[] p = new double[k];\n"
		     + "\n"
		     + "        for(int j:[0 .. k)) {\n"
		     + "            p[j] = indicator[j]/sum;\n"
		     + "            //This really wants to be a Categorical, but for now y will have\n"
		     + "            //to be arrays with just a single value set.\n"
		     + "            y[i][j] = bernoulli(p[j] + bias).sample();\n"
		     + "        }    \n"
		     + "    }\n"
		     + "\n"
		     + "    y.observe(yMeasured);\n"
		     + "}\n"
		     + "";
	}
}