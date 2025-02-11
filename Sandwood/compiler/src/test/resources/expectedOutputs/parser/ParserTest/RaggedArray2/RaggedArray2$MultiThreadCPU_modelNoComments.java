package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements RaggedArray2$CoreInterface {
	private double[][] a;
	private double[][] b;
	private double[] c;
	private double[] cv$var63$stateProbabilityGlobal;
	private double[] cv$var66$stateProbabilityGlobal;
	private double[] distribution$sample68;
	private boolean fixedFlag$sample68 = false;
	private boolean fixedFlag$sample71 = false;
	private boolean fixedFlag$sample81 = false;
	private boolean fixedProbFlag$sample68 = false;
	private boolean fixedProbFlag$sample71 = false;
	private boolean fixedProbFlag$sample81 = false;
	private int i;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$i;
	private double logProbability$obs;
	private double logProbability$p;
	private double logProbability$var62;
	private double logProbability$var65;
	private double logProbability$var69;
	private double logProbability$var75;
	private double logProbability$y;
	private boolean[] obs;
	private boolean[] obs_measured;
	private double p;
	private boolean setFlag$obs = false;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray2$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$a() {
		return a;
	}

	@Override
	public final double[][] get$b() {
		return b;
	}

	@Override
	public final double[] get$c() {
		return c;
	}

	@Override
	public final boolean get$fixedFlag$sample68() {
		return fixedFlag$sample68;
	}

	@Override
	public final void set$fixedFlag$sample68(boolean cv$value) {
		fixedFlag$sample68 = cv$value;
		fixedProbFlag$sample68 = (fixedFlag$sample68 && fixedProbFlag$sample68);
		fixedProbFlag$sample71 = (fixedFlag$sample68 && fixedProbFlag$sample71);
		fixedProbFlag$sample81 = (fixedFlag$sample68 && fixedProbFlag$sample81);
	}

	@Override
	public final boolean get$fixedFlag$sample71() {
		return fixedFlag$sample71;
	}

	@Override
	public final void set$fixedFlag$sample71(boolean cv$value) {
		fixedFlag$sample71 = cv$value;
		fixedProbFlag$sample71 = (fixedFlag$sample71 && fixedProbFlag$sample71);
		fixedProbFlag$sample81 = (fixedFlag$sample71 && fixedProbFlag$sample81);
	}

	@Override
	public final boolean get$fixedFlag$sample81() {
		return fixedFlag$sample81;
	}

	@Override
	public final void set$fixedFlag$sample81(boolean cv$value) {
		fixedFlag$sample81 = cv$value;
		fixedProbFlag$sample81 = (fixedFlag$sample81 && fixedProbFlag$sample81);
	}

	@Override
	public final int get$i() {
		return i;
	}

	@Override
	public final void set$i(int cv$value) {
		i = cv$value;
		fixedProbFlag$sample71 = false;
		fixedProbFlag$sample81 = false;
	}

	@Override
	public final int get$length$obs_measured() {
		return length$obs_measured;
	}

	@Override
	public final void set$length$obs_measured(int cv$value) {
		length$obs_measured = cv$value;
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
	public final double get$logProbability$i() {
		return logProbability$i;
	}

	@Override
	public final double get$logProbability$obs() {
		return logProbability$obs;
	}

	@Override
	public final double get$logProbability$p() {
		return logProbability$p;
	}

	@Override
	public final double get$logProbability$y() {
		return logProbability$y;
	}

	@Override
	public final boolean[] get$obs() {
		return obs;
	}

	@Override
	public final void set$obs(boolean[] cv$value) {
		obs = cv$value;
		setFlag$obs = true;
		fixedProbFlag$sample81 = false;
	}

	@Override
	public final boolean[] get$obs_measured() {
		return obs_measured;
	}

	@Override
	public final void set$obs_measured(boolean[] cv$value) {
		obs_measured = cv$value;
	}

	@Override
	public final double get$p() {
		return p;
	}

	@Override
	public final int get$y() {
		return y;
	}

	@Override
	public final void set$y(int cv$value) {
		y = cv$value;
		fixedProbFlag$sample68 = false;
		fixedProbFlag$sample71 = false;
		fixedProbFlag$sample81 = false;
	}

	private final void logProbabilityDistribution$sample68() {
		if(!fixedProbFlag$sample68) {
			if(fixedFlag$sample68) {
				double cv$accumulator = 0.0;
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = y;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < c.length))?Math.log(c[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var62 = cv$sampleAccumulator;
				logProbability$y = cv$sampleProbability;
				boolean cv$guard$p = false;
				{
					if((fixedFlag$sample68 && fixedFlag$sample71)) {
						if(!cv$guard$p) {
							cv$guard$p = true;
							logProbability$p = (logProbability$p + cv$accumulator);
						}
					}
				}
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample68)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample68 = fixedFlag$sample68;
			}
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$y;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var62 = cv$rvAccumulator;
			boolean cv$guard$p = false;
			{
				if((fixedFlag$sample68 && fixedFlag$sample71)) {
					if(!cv$guard$p) {
						cv$guard$p = true;
						logProbability$p = (logProbability$p + cv$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample68)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample71() {
		if(!fixedProbFlag$sample71) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = i;
				if(fixedFlag$sample68) {
					if((0 == y)) {
						if((0 == y)) {
							{
								double[] var64 = a[y];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var64.length))?Math.log(var64[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				} else {
					if(true) {
						for(int index$sample68$3 = 0; index$sample68$3 < 2; index$sample68$3 += 1) {
							int distributionTempVariable$y$5 = index$sample68$3;
							double cv$probabilitySample68Value4 = (1.0 * distribution$sample68[index$sample68$3]);
							if((0 == distributionTempVariable$y$5)) {
								if((0 == distributionTempVariable$y$5)) {
									{
										double[] var64 = a[distributionTempVariable$y$5];
										double cv$weightedProbability = (Math.log(cv$probabilitySample68Value4) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var64.length))?Math.log(var64[cv$sampleValue]):Double.NEGATIVE_INFINITY));
										if((cv$weightedProbability < cv$distributionAccumulator))
											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
										else {
											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
												cv$distributionAccumulator = cv$weightedProbability;
											else
												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
										}
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample68Value4);
									}
								}
							}
						}
					}
				}
				if(fixedFlag$sample68) {
					if((1 == y)) {
						if((1 == y)) {
							if((1 == y)) {
								{
									double[] var64 = a[y];
									double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var64.length))?Math.log(var64[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				} else {
					if(true) {
						for(int index$sample68$12 = 0; index$sample68$12 < 2; index$sample68$12 += 1) {
							int distributionTempVariable$y$14 = index$sample68$12;
							double cv$probabilitySample68Value13 = (1.0 * distribution$sample68[index$sample68$12]);
							if((1 == distributionTempVariable$y$14)) {
								if((1 == distributionTempVariable$y$14)) {
									if((1 == distributionTempVariable$y$14)) {
										{
											double[] var64 = a[distributionTempVariable$y$14];
											double cv$weightedProbability = (Math.log(cv$probabilitySample68Value13) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var64.length))?Math.log(var64[cv$sampleValue]):Double.NEGATIVE_INFINITY));
											if((cv$weightedProbability < cv$distributionAccumulator))
												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
											else {
												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
													cv$distributionAccumulator = cv$weightedProbability;
												else
													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
											}
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample68Value13);
										}
									}
								}
							}
						}
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
			logProbability$var65 = cv$sampleAccumulator;
			logProbability$i = cv$sampleProbability;
			boolean cv$guard$p = false;
			{
				if((fixedFlag$sample68 && fixedFlag$sample71)) {
					if(!cv$guard$p) {
						cv$guard$p = true;
						logProbability$p = (logProbability$p + cv$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample71 = (fixedFlag$sample71 && fixedFlag$sample68);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$i;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var65 = cv$rvAccumulator;
			boolean cv$guard$p = false;
			{
				if((fixedFlag$sample68 && fixedFlag$sample71)) {
					if(!cv$guard$p) {
						cv$guard$p = true;
						logProbability$p = (logProbability$p + cv$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample81() {
		if(!fixedProbFlag$sample81) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var74 = 0; var74 < length$obs_measured; var74 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = obs[var74];
					if(fixedFlag$sample68) {
						double traceTempVariable$p$8_1 = 0.2;
						if((0 == y)) {
							if((0 == i)) {
								{
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, traceTempVariable$p$8_1));
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
					} else {
						if(true) {
							for(int index$sample68$4 = 0; index$sample68$4 < 2; index$sample68$4 += 1) {
								int distributionTempVariable$y$6 = index$sample68$4;
								double cv$probabilitySample68Value5 = (1.0 * distribution$sample68[index$sample68$4]);
								double traceTempVariable$p$9_1 = 0.2;
								if((0 == distributionTempVariable$y$6)) {
									if((0 == i)) {
										{
											double cv$weightedProbability = (Math.log(cv$probabilitySample68Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, traceTempVariable$p$9_1));
											if((cv$weightedProbability < cv$distributionAccumulator))
												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
											else {
												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
													cv$distributionAccumulator = cv$weightedProbability;
												else
													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
											}
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample68Value5);
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample68) {
						double traceTempVariable$p$16_1 = 0.8;
						if((0 == y)) {
							if((1 == i)) {
								{
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, traceTempVariable$p$16_1));
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
					} else {
						if(true) {
							for(int index$sample68$12 = 0; index$sample68$12 < 2; index$sample68$12 += 1) {
								int distributionTempVariable$y$14 = index$sample68$12;
								double cv$probabilitySample68Value13 = (1.0 * distribution$sample68[index$sample68$12]);
								double traceTempVariable$p$17_1 = 0.8;
								if((0 == distributionTempVariable$y$14)) {
									if((1 == i)) {
										{
											double cv$weightedProbability = (Math.log(cv$probabilitySample68Value13) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, traceTempVariable$p$17_1));
											if((cv$weightedProbability < cv$distributionAccumulator))
												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
											else {
												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
													cv$distributionAccumulator = cv$weightedProbability;
												else
													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
											}
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample68Value13);
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample68) {
						double traceTempVariable$p$24_1 = 0.4;
						if((1 == y)) {
							if((0 == i)) {
								{
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, traceTempVariable$p$24_1));
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
					} else {
						if(true) {
							for(int index$sample68$20 = 0; index$sample68$20 < 2; index$sample68$20 += 1) {
								int distributionTempVariable$y$22 = index$sample68$20;
								double cv$probabilitySample68Value21 = (1.0 * distribution$sample68[index$sample68$20]);
								double traceTempVariable$p$25_1 = 0.4;
								if((1 == distributionTempVariable$y$22)) {
									if((0 == i)) {
										{
											double cv$weightedProbability = (Math.log(cv$probabilitySample68Value21) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, traceTempVariable$p$25_1));
											if((cv$weightedProbability < cv$distributionAccumulator))
												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
											else {
												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
													cv$distributionAccumulator = cv$weightedProbability;
												else
													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
											}
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample68Value21);
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample68) {
						double traceTempVariable$p$32_1 = 0.2;
						if((1 == y)) {
							if((1 == i)) {
								{
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, traceTempVariable$p$32_1));
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
					} else {
						if(true) {
							for(int index$sample68$28 = 0; index$sample68$28 < 2; index$sample68$28 += 1) {
								int distributionTempVariable$y$30 = index$sample68$28;
								double cv$probabilitySample68Value29 = (1.0 * distribution$sample68[index$sample68$28]);
								double traceTempVariable$p$33_1 = 0.2;
								if((1 == distributionTempVariable$y$30)) {
									if((1 == i)) {
										{
											double cv$weightedProbability = (Math.log(cv$probabilitySample68Value29) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, traceTempVariable$p$33_1));
											if((cv$weightedProbability < cv$distributionAccumulator))
												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
											else {
												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
													cv$distributionAccumulator = cv$weightedProbability;
												else
													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
											}
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample68Value29);
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample68) {
						double traceTempVariable$p$40_1 = 0.6;
						if((1 == y)) {
							if((2 == i)) {
								{
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, traceTempVariable$p$40_1));
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
					} else {
						if(true) {
							for(int index$sample68$36 = 0; index$sample68$36 < 2; index$sample68$36 += 1) {
								int distributionTempVariable$y$38 = index$sample68$36;
								double cv$probabilitySample68Value37 = (1.0 * distribution$sample68[index$sample68$36]);
								double traceTempVariable$p$41_1 = 0.6;
								if((1 == distributionTempVariable$y$38)) {
									if((2 == i)) {
										{
											double cv$weightedProbability = (Math.log(cv$probabilitySample68Value37) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, traceTempVariable$p$41_1));
											if((cv$weightedProbability < cv$distributionAccumulator))
												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
											else {
												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
													cv$distributionAccumulator = cv$weightedProbability;
												else
													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
											}
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample68Value37);
										}
									}
								}
							}
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var69 = cv$sampleAccumulator;
			logProbability$var75 = cv$sampleAccumulator;
			logProbability$obs = (logProbability$obs + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample81 = ((fixedFlag$sample81 && fixedFlag$sample68) && fixedFlag$sample71);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var75;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var69 = cv$rvAccumulator;
			logProbability$obs = (logProbability$obs + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample68() {
		if(!fixedProbFlag$sample68) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = y;
				{
					{
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < c.length))?Math.log(c[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var62 = cv$sampleAccumulator;
			logProbability$y = cv$sampleProbability;
			boolean cv$guard$p = false;
			{
				if(!cv$guard$p) {
					cv$guard$p = true;
					logProbability$p = (logProbability$p + cv$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample68)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample68 = fixedFlag$sample68;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$y;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var62 = cv$rvAccumulator;
			boolean cv$guard$p = false;
			{
				if(!cv$guard$p) {
					cv$guard$p = true;
					logProbability$p = (logProbability$p + cv$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample68)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample71() {
		if(!fixedProbFlag$sample71) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = i;
				{
					{
						double[] var64 = a[y];
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var64.length))?Math.log(var64[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var65 = cv$sampleAccumulator;
			logProbability$i = cv$sampleProbability;
			boolean cv$guard$p = false;
			{
				if(!cv$guard$p) {
					cv$guard$p = true;
					logProbability$p = (logProbability$p + cv$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample71 = (fixedFlag$sample71 && fixedFlag$sample68);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$i;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var65 = cv$rvAccumulator;
			boolean cv$guard$p = false;
			{
				if(!cv$guard$p) {
					cv$guard$p = true;
					logProbability$p = (logProbability$p + cv$accumulator);
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample81() {
		if(!fixedProbFlag$sample81) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var74 = 0; var74 < length$obs_measured; var74 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = obs[var74];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, p));
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
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var69 = cv$sampleAccumulator;
			logProbability$var75 = cv$sampleAccumulator;
			logProbability$obs = (logProbability$obs + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample81 = ((fixedFlag$sample81 && fixedFlag$sample68) && fixedFlag$sample71);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var75;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var69 = cv$rvAccumulator;
			logProbability$obs = (logProbability$obs + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample68() {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double[] cv$stateProbabilityLocal = cv$var63$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$c;
				{
					cv$temp$0$c = c;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$c.length))?Math.log(cv$temp$0$c[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$y$1_1 = cv$currentValue;
						{
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							{
								if((0 == traceTempVariable$y$1_1)) {
									if((0 == traceTempVariable$y$1_1)) {
										{
											{
												double[] cv$temp$1$var64;
												{
													double[] var64 = a[traceTempVariable$y$1_1];
													cv$temp$1$var64 = var64;
												}
												if(((Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$1$var64.length))?Math.log(cv$temp$1$var64[i]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$1$var64.length))?Math.log(cv$temp$1$var64[i]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$1$var64.length))?Math.log(cv$temp$1$var64[i]):Double.NEGATIVE_INFINITY));
													else
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$1$var64.length))?Math.log(cv$temp$1$var64[i]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$1$var64.length))?Math.log(cv$temp$1$var64[i]):Double.NEGATIVE_INFINITY)));
												}
												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
											}
										}
									}
								}
								if((1 == traceTempVariable$y$1_1)) {
									if((1 == traceTempVariable$y$1_1)) {
										if((1 == traceTempVariable$y$1_1)) {
											{
												{
													double[] cv$temp$2$var64;
													{
														double[] var64 = a[traceTempVariable$y$1_1];
														cv$temp$2$var64 = var64;
													}
													if(((Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$2$var64.length))?Math.log(cv$temp$2$var64[i]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$2$var64.length))?Math.log(cv$temp$2$var64[i]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$2$var64.length))?Math.log(cv$temp$2$var64[i]):Double.NEGATIVE_INFINITY));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$2$var64.length))?Math.log(cv$temp$2$var64[i]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$2$var64.length))?Math.log(cv$temp$2$var64[i]):Double.NEGATIVE_INFINITY)));
													}
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
												}
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
				{
					{
						int traceTempVariable$y$10_1 = cv$currentValue;
						{
							for(int var74 = 0; var74 < length$obs_measured; var74 += 1) {
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								{
									double traceTempVariable$p$13_1 = 0.2;
									if((0 == traceTempVariable$y$10_1)) {
										if((0 == i)) {
											{
												{
													double cv$temp$3$p;
													{
														cv$temp$3$p = traceTempVariable$p$13_1;
													}
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$3$p)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$3$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$3$p));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$3$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$3$p)));
													}
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
												}
											}
										}
									}
									double traceTempVariable$p$15_1 = 0.8;
									if((0 == traceTempVariable$y$10_1)) {
										if((1 == i)) {
											{
												{
													double cv$temp$4$p;
													{
														cv$temp$4$p = traceTempVariable$p$15_1;
													}
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$4$p)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$4$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$4$p));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$4$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$4$p)));
													}
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
												}
											}
										}
									}
									double traceTempVariable$p$17_1 = 0.4;
									if((1 == traceTempVariable$y$10_1)) {
										if((0 == i)) {
											{
												{
													double cv$temp$5$p;
													{
														cv$temp$5$p = traceTempVariable$p$17_1;
													}
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$5$p)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$5$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$5$p));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$5$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$5$p)));
													}
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
												}
											}
										}
									}
									double traceTempVariable$p$19_1 = 0.2;
									if((1 == traceTempVariable$y$10_1)) {
										if((1 == i)) {
											{
												{
													double cv$temp$6$p;
													{
														cv$temp$6$p = traceTempVariable$p$19_1;
													}
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$6$p)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$6$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$6$p));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$6$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$6$p)));
													}
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
												}
											}
										}
									}
									double traceTempVariable$p$21_1 = 0.6;
									if((1 == traceTempVariable$y$10_1)) {
										if((2 == i)) {
											{
												{
													double cv$temp$7$p;
													{
														cv$temp$7$p = traceTempVariable$p$21_1;
													}
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$7$p)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$7$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$7$p));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$7$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$7$p)));
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
				if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
					cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
				else {
					if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					else
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
				}
			}
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		double[] cv$localProbability = distribution$sample68;
		double cv$logSum = 0.0;
		{
			double cv$lseMax = cv$stateProbabilityLocal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample71() {
		int cv$noStates = 0;
		if(fixedFlag$sample68) {
			if((0 == y)) {
				if((0 == y))
					cv$noStates = Math.max(cv$noStates, a[y].length);
			}
		} else {
			if(true) {
				for(int index$sample68$2 = 0; index$sample68$2 < 2; index$sample68$2 += 1) {
					int distributionTempVariable$y$4 = index$sample68$2;
					double cv$probabilitySample68Value3 = (1.0 * distribution$sample68[index$sample68$2]);
					if((0 == distributionTempVariable$y$4)) {
						if((0 == distributionTempVariable$y$4))
							cv$noStates = Math.max(cv$noStates, a[distributionTempVariable$y$4].length);
					}
				}
			}
		}
		if(fixedFlag$sample68) {
			if((1 == y)) {
				if((1 == y)) {
					if((1 == y))
						cv$noStates = Math.max(cv$noStates, a[y].length);
				}
			}
		} else {
			if(true) {
				for(int index$sample68$11 = 0; index$sample68$11 < 2; index$sample68$11 += 1) {
					int distributionTempVariable$y$13 = index$sample68$11;
					double cv$probabilitySample68Value12 = (1.0 * distribution$sample68[index$sample68$11]);
					if((1 == distributionTempVariable$y$13)) {
						if((1 == distributionTempVariable$y$13)) {
							if((1 == distributionTempVariable$y$13))
								cv$noStates = Math.max(cv$noStates, a[distributionTempVariable$y$13].length);
						}
					}
				}
			}
		}
		double[] cv$stateProbabilityLocal = cv$var66$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			i = cv$currentValue;
			{
				{
					p = b[y][cv$currentValue];
				}
			}
			if(fixedFlag$sample68) {
				if((0 == y)) {
					if((0 == y)) {
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double[] cv$temp$0$var64;
						{
							double[] var64 = a[y];
							cv$temp$0$var64 = var64;
						}
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var64.length))?Math.log(cv$temp$0$var64[cv$currentValue]):Double.NEGATIVE_INFINITY));
						{
							{
								int traceTempVariable$i$42_1 = cv$currentValue;
								{
									for(int var74 = 0; var74 < length$obs_measured; var74 += 1) {
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											double traceTempVariable$p$51_1 = 0.2;
											if((0 == y)) {
												if((0 == traceTempVariable$i$42_1)) {
													{
														{
															double cv$temp$4$p;
															{
																cv$temp$4$p = traceTempVariable$p$51_1;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$4$p)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$4$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$4$p));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$4$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$4$p)));
															}
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
														}
													}
												}
											}
											double traceTempVariable$p$53_1 = 0.8;
											if((0 == y)) {
												if((1 == traceTempVariable$i$42_1)) {
													{
														{
															double cv$temp$5$p;
															{
																cv$temp$5$p = traceTempVariable$p$53_1;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$5$p)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$5$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$5$p));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$5$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$5$p)));
															}
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
														}
													}
												}
											}
											double traceTempVariable$p$55_1 = 0.4;
											if((1 == y)) {
												if((0 == traceTempVariable$i$42_1)) {
													{
														{
															double cv$temp$6$p;
															{
																cv$temp$6$p = traceTempVariable$p$55_1;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$6$p)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$6$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$6$p));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$6$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$6$p)));
															}
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
														}
													}
												}
											}
											double traceTempVariable$p$57_1 = 0.2;
											if((1 == y)) {
												if((1 == traceTempVariable$i$42_1)) {
													{
														{
															double cv$temp$7$p;
															{
																cv$temp$7$p = traceTempVariable$p$57_1;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$7$p)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$7$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$7$p));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$7$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$7$p)));
															}
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
														}
													}
												}
											}
											double traceTempVariable$p$59_1 = 0.6;
											if((1 == y)) {
												if((2 == traceTempVariable$i$42_1)) {
													{
														{
															double cv$temp$8$p;
															{
																cv$temp$8$p = traceTempVariable$p$59_1;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$8$p)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$8$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$8$p));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$8$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$8$p)));
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
						if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
						else {
							if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
								cv$stateProbabilityValue = cv$accumulatedProbabilities;
							else
								cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
						}
					}
				}
			} else {
				if(true) {
					for(int index$sample68$23 = 0; index$sample68$23 < 2; index$sample68$23 += 1) {
						int distributionTempVariable$y$25 = index$sample68$23;
						double cv$probabilitySample68Value24 = (1.0 * distribution$sample68[index$sample68$23]);
						if((0 == distributionTempVariable$y$25)) {
							if((0 == distributionTempVariable$y$25)) {
								cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample68Value24);
								double[] cv$temp$1$var64;
								{
									double[] var64 = a[distributionTempVariable$y$25];
									cv$temp$1$var64 = var64;
								}
								double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample68Value24) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var64.length))?Math.log(cv$temp$1$var64[cv$currentValue]):Double.NEGATIVE_INFINITY));
								{
									{
										int traceTempVariable$i$43_1 = cv$currentValue;
										{
											for(int var74 = 0; var74 < length$obs_measured; var74 += 1) {
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(!true) {
														for(int index$sample68$61 = 0; index$sample68$61 < 2; index$sample68$61 += 1) {
															int distributionTempVariable$y$63 = index$sample68$61;
															double cv$probabilitySample68Value62 = (1.0 * distribution$sample68[index$sample68$61]);
															double traceTempVariable$p$66_1 = 0.2;
															if((0 == distributionTempVariable$y$63)) {
																if((0 == traceTempVariable$i$43_1)) {
																	{
																		{
																			double cv$temp$10$p;
																			{
																				cv$temp$10$p = traceTempVariable$p$66_1;
																			}
																			if(((Math.log(cv$probabilitySample68Value62) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$10$p)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample68Value62) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$10$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample68Value62) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$10$p));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample68Value62) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$10$p)))) + 1)) + (Math.log(cv$probabilitySample68Value62) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$10$p)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample68Value62);
																		}
																	}
																}
															}
														}
													}
													double traceTempVariable$p$65_1 = 0.2;
													if((0 == distributionTempVariable$y$25)) {
														if((0 == traceTempVariable$i$43_1)) {
															{
																{
																	double cv$temp$9$p;
																	{
																		cv$temp$9$p = traceTempVariable$p$65_1;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$9$p)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$9$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$9$p));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$9$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$9$p)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
													if(!true) {
														for(int index$sample68$68 = 0; index$sample68$68 < 2; index$sample68$68 += 1) {
															int distributionTempVariable$y$70 = index$sample68$68;
															double cv$probabilitySample68Value69 = (1.0 * distribution$sample68[index$sample68$68]);
															double traceTempVariable$p$73_1 = 0.8;
															if((0 == distributionTempVariable$y$70)) {
																if((1 == traceTempVariable$i$43_1)) {
																	{
																		{
																			double cv$temp$12$p;
																			{
																				cv$temp$12$p = traceTempVariable$p$73_1;
																			}
																			if(((Math.log(cv$probabilitySample68Value69) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$12$p)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample68Value69) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$12$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample68Value69) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$12$p));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample68Value69) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$12$p)))) + 1)) + (Math.log(cv$probabilitySample68Value69) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$12$p)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample68Value69);
																		}
																	}
																}
															}
														}
													}
													double traceTempVariable$p$72_1 = 0.8;
													if((0 == distributionTempVariable$y$25)) {
														if((1 == traceTempVariable$i$43_1)) {
															{
																{
																	double cv$temp$11$p;
																	{
																		cv$temp$11$p = traceTempVariable$p$72_1;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$11$p)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$11$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$11$p));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$11$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$11$p)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
													if(!true) {
														for(int index$sample68$75 = 0; index$sample68$75 < 2; index$sample68$75 += 1) {
															int distributionTempVariable$y$77 = index$sample68$75;
															double cv$probabilitySample68Value76 = (1.0 * distribution$sample68[index$sample68$75]);
															double traceTempVariable$p$80_1 = 0.4;
															if((1 == distributionTempVariable$y$77)) {
																if((0 == traceTempVariable$i$43_1)) {
																	{
																		{
																			double cv$temp$14$p;
																			{
																				cv$temp$14$p = traceTempVariable$p$80_1;
																			}
																			if(((Math.log(cv$probabilitySample68Value76) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$14$p)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample68Value76) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$14$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample68Value76) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$14$p));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample68Value76) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$14$p)))) + 1)) + (Math.log(cv$probabilitySample68Value76) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$14$p)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample68Value76);
																		}
																	}
																}
															}
														}
													}
													double traceTempVariable$p$79_1 = 0.4;
													if((1 == distributionTempVariable$y$25)) {
														if((0 == traceTempVariable$i$43_1)) {
															{
																{
																	double cv$temp$13$p;
																	{
																		cv$temp$13$p = traceTempVariable$p$79_1;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$13$p)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$13$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$13$p));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$13$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$13$p)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
													if(!true) {
														for(int index$sample68$82 = 0; index$sample68$82 < 2; index$sample68$82 += 1) {
															int distributionTempVariable$y$84 = index$sample68$82;
															double cv$probabilitySample68Value83 = (1.0 * distribution$sample68[index$sample68$82]);
															double traceTempVariable$p$87_1 = 0.2;
															if((1 == distributionTempVariable$y$84)) {
																if((1 == traceTempVariable$i$43_1)) {
																	{
																		{
																			double cv$temp$16$p;
																			{
																				cv$temp$16$p = traceTempVariable$p$87_1;
																			}
																			if(((Math.log(cv$probabilitySample68Value83) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$16$p)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample68Value83) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$16$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample68Value83) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$16$p));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample68Value83) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$16$p)))) + 1)) + (Math.log(cv$probabilitySample68Value83) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$16$p)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample68Value83);
																		}
																	}
																}
															}
														}
													}
													double traceTempVariable$p$86_1 = 0.2;
													if((1 == distributionTempVariable$y$25)) {
														if((1 == traceTempVariable$i$43_1)) {
															{
																{
																	double cv$temp$15$p;
																	{
																		cv$temp$15$p = traceTempVariable$p$86_1;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$15$p)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$15$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$15$p));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$15$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$15$p)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
													if(!true) {
														for(int index$sample68$89 = 0; index$sample68$89 < 2; index$sample68$89 += 1) {
															int distributionTempVariable$y$91 = index$sample68$89;
															double cv$probabilitySample68Value90 = (1.0 * distribution$sample68[index$sample68$89]);
															double traceTempVariable$p$94_1 = 0.6;
															if((1 == distributionTempVariable$y$91)) {
																if((2 == traceTempVariable$i$43_1)) {
																	{
																		{
																			double cv$temp$18$p;
																			{
																				cv$temp$18$p = traceTempVariable$p$94_1;
																			}
																			if(((Math.log(cv$probabilitySample68Value90) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$18$p)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample68Value90) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$18$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample68Value90) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$18$p));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample68Value90) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$18$p)))) + 1)) + (Math.log(cv$probabilitySample68Value90) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$18$p)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample68Value90);
																		}
																	}
																}
															}
														}
													}
													double traceTempVariable$p$93_1 = 0.6;
													if((1 == distributionTempVariable$y$25)) {
														if((2 == traceTempVariable$i$43_1)) {
															{
																{
																	double cv$temp$17$p;
																	{
																		cv$temp$17$p = traceTempVariable$p$93_1;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$17$p)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$17$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$17$p));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$17$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$17$p)));
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
								if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
									cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
								else {
									if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
										cv$stateProbabilityValue = cv$accumulatedProbabilities;
									else
										cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
								}
							}
						}
					}
				}
			}
			if(fixedFlag$sample68) {
				if((1 == y)) {
					if((1 == y)) {
						if((1 == y)) {
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$2$var64;
							{
								double[] var64 = a[y];
								cv$temp$2$var64 = var64;
							}
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var64.length))?Math.log(cv$temp$2$var64[cv$currentValue]):Double.NEGATIVE_INFINITY));
							{
								{
									int traceTempVariable$i$44_1 = cv$currentValue;
									{
										for(int var74 = 0; var74 < length$obs_measured; var74 += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												double traceTempVariable$p$96_1 = 0.2;
												if((0 == y)) {
													if((0 == traceTempVariable$i$44_1)) {
														{
															{
																double cv$temp$19$p;
																{
																	cv$temp$19$p = traceTempVariable$p$96_1;
																}
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$19$p)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$19$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$19$p));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$19$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$19$p)));
																}
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
															}
														}
													}
												}
												double traceTempVariable$p$98_1 = 0.8;
												if((0 == y)) {
													if((1 == traceTempVariable$i$44_1)) {
														{
															{
																double cv$temp$20$p;
																{
																	cv$temp$20$p = traceTempVariable$p$98_1;
																}
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$20$p)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$20$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$20$p));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$20$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$20$p)));
																}
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
															}
														}
													}
												}
												double traceTempVariable$p$100_1 = 0.4;
												if((1 == y)) {
													if((0 == traceTempVariable$i$44_1)) {
														{
															{
																double cv$temp$21$p;
																{
																	cv$temp$21$p = traceTempVariable$p$100_1;
																}
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$21$p)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$21$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$21$p));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$21$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$21$p)));
																}
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
															}
														}
													}
												}
												double traceTempVariable$p$102_1 = 0.2;
												if((1 == y)) {
													if((1 == traceTempVariable$i$44_1)) {
														{
															{
																double cv$temp$22$p;
																{
																	cv$temp$22$p = traceTempVariable$p$102_1;
																}
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$22$p)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$22$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$22$p));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$22$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$22$p)));
																}
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
															}
														}
													}
												}
												double traceTempVariable$p$104_1 = 0.6;
												if((1 == y)) {
													if((2 == traceTempVariable$i$44_1)) {
														{
															{
																double cv$temp$23$p;
																{
																	cv$temp$23$p = traceTempVariable$p$104_1;
																}
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$23$p)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$23$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$23$p));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$23$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$23$p)));
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
							if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
								cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
							else {
								if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
									cv$stateProbabilityValue = cv$accumulatedProbabilities;
								else
									cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
							}
						}
					}
				}
			} else {
				if(true) {
					for(int index$sample68$32 = 0; index$sample68$32 < 2; index$sample68$32 += 1) {
						int distributionTempVariable$y$34 = index$sample68$32;
						double cv$probabilitySample68Value33 = (1.0 * distribution$sample68[index$sample68$32]);
						if((1 == distributionTempVariable$y$34)) {
							if((1 == distributionTempVariable$y$34)) {
								if((1 == distributionTempVariable$y$34)) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample68Value33);
									double[] cv$temp$3$var64;
									{
										double[] var64 = a[distributionTempVariable$y$34];
										cv$temp$3$var64 = var64;
									}
									double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample68Value33) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$var64.length))?Math.log(cv$temp$3$var64[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											int traceTempVariable$i$45_1 = cv$currentValue;
											{
												for(int var74 = 0; var74 < length$obs_measured; var74 += 1) {
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if(!true) {
															for(int index$sample68$106 = 0; index$sample68$106 < 2; index$sample68$106 += 1) {
																int distributionTempVariable$y$108 = index$sample68$106;
																double cv$probabilitySample68Value107 = (1.0 * distribution$sample68[index$sample68$106]);
																double traceTempVariable$p$111_1 = 0.2;
																if((0 == distributionTempVariable$y$108)) {
																	if((0 == traceTempVariable$i$45_1)) {
																		{
																			{
																				double cv$temp$25$p;
																				{
																					cv$temp$25$p = traceTempVariable$p$111_1;
																				}
																				if(((Math.log(cv$probabilitySample68Value107) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$25$p)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample68Value107) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$25$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample68Value107) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$25$p));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample68Value107) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$25$p)))) + 1)) + (Math.log(cv$probabilitySample68Value107) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$25$p)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample68Value107);
																			}
																		}
																	}
																}
															}
														}
														double traceTempVariable$p$110_1 = 0.2;
														if((0 == distributionTempVariable$y$34)) {
															if((0 == traceTempVariable$i$45_1)) {
																{
																	{
																		double cv$temp$24$p;
																		{
																			cv$temp$24$p = traceTempVariable$p$110_1;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$24$p)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$24$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$24$p));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$24$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$24$p)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample68$113 = 0; index$sample68$113 < 2; index$sample68$113 += 1) {
																int distributionTempVariable$y$115 = index$sample68$113;
																double cv$probabilitySample68Value114 = (1.0 * distribution$sample68[index$sample68$113]);
																double traceTempVariable$p$118_1 = 0.8;
																if((0 == distributionTempVariable$y$115)) {
																	if((1 == traceTempVariable$i$45_1)) {
																		{
																			{
																				double cv$temp$27$p;
																				{
																					cv$temp$27$p = traceTempVariable$p$118_1;
																				}
																				if(((Math.log(cv$probabilitySample68Value114) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$27$p)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample68Value114) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$27$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample68Value114) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$27$p));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample68Value114) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$27$p)))) + 1)) + (Math.log(cv$probabilitySample68Value114) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$27$p)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample68Value114);
																			}
																		}
																	}
																}
															}
														}
														double traceTempVariable$p$117_1 = 0.8;
														if((0 == distributionTempVariable$y$34)) {
															if((1 == traceTempVariable$i$45_1)) {
																{
																	{
																		double cv$temp$26$p;
																		{
																			cv$temp$26$p = traceTempVariable$p$117_1;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$26$p)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$26$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$26$p));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$26$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$26$p)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample68$120 = 0; index$sample68$120 < 2; index$sample68$120 += 1) {
																int distributionTempVariable$y$122 = index$sample68$120;
																double cv$probabilitySample68Value121 = (1.0 * distribution$sample68[index$sample68$120]);
																double traceTempVariable$p$125_1 = 0.4;
																if((1 == distributionTempVariable$y$122)) {
																	if((0 == traceTempVariable$i$45_1)) {
																		{
																			{
																				double cv$temp$29$p;
																				{
																					cv$temp$29$p = traceTempVariable$p$125_1;
																				}
																				if(((Math.log(cv$probabilitySample68Value121) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$29$p)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample68Value121) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$29$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample68Value121) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$29$p));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample68Value121) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$29$p)))) + 1)) + (Math.log(cv$probabilitySample68Value121) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$29$p)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample68Value121);
																			}
																		}
																	}
																}
															}
														}
														double traceTempVariable$p$124_1 = 0.4;
														if((1 == distributionTempVariable$y$34)) {
															if((0 == traceTempVariable$i$45_1)) {
																{
																	{
																		double cv$temp$28$p;
																		{
																			cv$temp$28$p = traceTempVariable$p$124_1;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$28$p)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$28$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$28$p));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$28$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$28$p)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample68$127 = 0; index$sample68$127 < 2; index$sample68$127 += 1) {
																int distributionTempVariable$y$129 = index$sample68$127;
																double cv$probabilitySample68Value128 = (1.0 * distribution$sample68[index$sample68$127]);
																double traceTempVariable$p$132_1 = 0.2;
																if((1 == distributionTempVariable$y$129)) {
																	if((1 == traceTempVariable$i$45_1)) {
																		{
																			{
																				double cv$temp$31$p;
																				{
																					cv$temp$31$p = traceTempVariable$p$132_1;
																				}
																				if(((Math.log(cv$probabilitySample68Value128) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$31$p)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample68Value128) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$31$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample68Value128) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$31$p));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample68Value128) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$31$p)))) + 1)) + (Math.log(cv$probabilitySample68Value128) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$31$p)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample68Value128);
																			}
																		}
																	}
																}
															}
														}
														double traceTempVariable$p$131_1 = 0.2;
														if((1 == distributionTempVariable$y$34)) {
															if((1 == traceTempVariable$i$45_1)) {
																{
																	{
																		double cv$temp$30$p;
																		{
																			cv$temp$30$p = traceTempVariable$p$131_1;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$30$p)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$30$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$30$p));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$30$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$30$p)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
														if(!true) {
															for(int index$sample68$134 = 0; index$sample68$134 < 2; index$sample68$134 += 1) {
																int distributionTempVariable$y$136 = index$sample68$134;
																double cv$probabilitySample68Value135 = (1.0 * distribution$sample68[index$sample68$134]);
																double traceTempVariable$p$139_1 = 0.6;
																if((1 == distributionTempVariable$y$136)) {
																	if((2 == traceTempVariable$i$45_1)) {
																		{
																			{
																				double cv$temp$33$p;
																				{
																					cv$temp$33$p = traceTempVariable$p$139_1;
																				}
																				if(((Math.log(cv$probabilitySample68Value135) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$33$p)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample68Value135) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$33$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample68Value135) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$33$p));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample68Value135) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$33$p)))) + 1)) + (Math.log(cv$probabilitySample68Value135) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$33$p)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample68Value135);
																			}
																		}
																	}
																}
															}
														}
														double traceTempVariable$p$138_1 = 0.6;
														if((1 == distributionTempVariable$y$34)) {
															if((2 == traceTempVariable$i$45_1)) {
																{
																	{
																		double cv$temp$32$p;
																		{
																			cv$temp$32$p = traceTempVariable$p$138_1;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$32$p)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$32$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$32$p));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$32$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var74], cv$temp$32$p)));
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
									if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
										cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
									else {
										if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
											cv$stateProbabilityValue = cv$accumulatedProbabilities;
										else
											cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
									}
								}
							}
						}
					}
				}
			}
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		double cv$logSum = 0.0;
		{
			double cv$lseMax = cv$stateProbabilityLocal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
		i = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
		{
			{
				p = b[y][i];
			}
		}
	}

	@Override
	public final void allocateScratch() {
		{
			cv$var63$stateProbabilityGlobal = new double[2];
		}
		{
			int cv$var28$max = 2;
			cv$var28$max = Math.max(cv$var28$max, 3);
			cv$var66$stateProbabilityGlobal = new double[cv$var28$max];
		}
	}

	@Override
	public final void allocator() {
		{
			a = new double[2][];
			a[0] = new double[2];
			a[1] = new double[3];
		}
		{
			b = new double[2][];
			b[0] = new double[2];
			b[1] = new double[3];
		}
		{
			c = new double[2];
		}
		if(!setFlag$obs) {
			{
				obs = new boolean[length$obs_measured];
			}
		}
		{
			distribution$sample68 = new double[2];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample68)
			y = DistributionSampling.sampleCategorical(RNG$, c);
		if(!fixedFlag$sample71)
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
		if(!(fixedFlag$sample68 && fixedFlag$sample71))
			p = b[y][i];
		parallelFor(RNG$, 0, length$obs_measured, 1,
			(int forStart$var74, int forEnd$var74, int threadID$var74, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var74 = forStart$var74; var74 < forEnd$var74; var74 += 1) {
						if(!fixedFlag$sample81)
							obs[var74] = DistributionSampling.sampleBernoulli(RNG$1, p);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		double[] cv$distribution$sample68 = distribution$sample68;
		for(int index$var62 = 0; index$var62 < 2; index$var62 += 1) {
			double cv$value = (((0.0 <= index$var62) && (index$var62 < c.length))?c[index$var62]:0.0);
			if(!fixedFlag$sample68)
				cv$distribution$sample68[index$var62] = cv$value;
		}
		if(!fixedFlag$sample71)
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample68)
			y = DistributionSampling.sampleCategorical(RNG$, c);
		if(!fixedFlag$sample71)
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
		if(!(fixedFlag$sample68 && fixedFlag$sample71))
			p = b[y][i];
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample68)
				sample68();
			if(!fixedFlag$sample71)
				sample71();
		} else {
			if(!fixedFlag$sample71)
				sample71();
			if(!fixedFlag$sample68)
				sample68();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		double[] var7 = a[0];
		var7[0] = 0.4;
		var7[1] = 0.6;
		double[] var17 = a[1];
		var17[0] = 0.2;
		var17[1] = 0.3;
		var17[2] = 0.5;
		double[] var32 = b[0];
		var32[0] = 0.2;
		var32[1] = 0.8;
		double[] var42 = b[1];
		var42[0] = 0.4;
		var42[1] = 0.2;
		var42[2] = 0.6;
		c[0] = 0.35;
		c[1] = 0.65;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var62 = 0.0;
		logProbability$p = 0.0;
		if(!fixedProbFlag$sample68)
			logProbability$y = 0.0;
		logProbability$var65 = 0.0;
		if(!fixedProbFlag$sample71)
			logProbability$i = 0.0;
		logProbability$var69 = 0.0;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample81)
			logProbability$var75 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample71)
			logProbabilityValue$sample71();
		logProbabilityValue$sample81();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample68();
		logProbabilityDistribution$sample71();
		logProbabilityDistribution$sample81();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample68();
		logProbabilityValue$sample71();
		logProbabilityValue$sample81();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample68)
			y = DistributionSampling.sampleCategorical(RNG$, c);
		if(!fixedFlag$sample71)
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
		if(!(fixedFlag$sample68 && fixedFlag$sample71))
			p = b[y][i];
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		boolean[] cv$source1 = obs_measured;
		boolean[] cv$target1 = obs;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		if(true)
			p = b[y][i];
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2025, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n \n package org.sandwood.compiler.tests.parser;\n\npublic model RaggedArray2(boolean[] obs_measured) {\n    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n    double[][] b = {{0.2, 0.8}, {0.4, 0.2, 0.6}};\n    double[] c = { 0.35, 0.65 };\n    int y = categorical(c).sampleDistribution();\n    int i = categorical(a[y]).sample();\n    double p = b[y][i];\n    boolean[] obs = bernoulli(p).sample(obs_measured.length);\n    obs.observe(obs_measured);\n}";
	}
}