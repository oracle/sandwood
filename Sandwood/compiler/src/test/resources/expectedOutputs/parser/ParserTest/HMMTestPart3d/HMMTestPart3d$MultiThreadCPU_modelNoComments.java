package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart3d$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart3d$CoreInterface {
	private double[] bias;
	private double[][] cv$var16$countGlobal;
	private double[] cv$var34$stateProbabilityGlobal;
	private double[] cv$var50$stateProbabilityGlobal;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample27 = false;
	private boolean fixedFlag$sample37 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedFlag$sample80 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample27 = false;
	private boolean fixedProbFlag$sample37 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean fixedProbFlag$sample80 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int[][] indirection;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample53;
	private double[] logProbability$sample80;
	private double logProbability$st;
	private double logProbability$st2;
	private double logProbability$var11;
	private double logProbability$var16;
	private double logProbability$var20;
	private double logProbability$var25;
	private double logProbability$var33;
	private double logProbability$var34;
	private double[] logProbability$var49;
	private double[] logProbability$var76;
	private double[][] m;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private boolean setFlag$st2 = false;
	private int[] st;
	private int[] st2;
	private int states;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart3d$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		setFlag$bias = true;
	}

	@Override
	public final boolean get$fixedFlag$sample17() {
		return fixedFlag$sample17;
	}

	@Override
	public final void set$fixedFlag$sample17(boolean cv$value) {
		fixedFlag$sample17 = cv$value;
		fixedProbFlag$sample17 = (fixedFlag$sample17 && fixedProbFlag$sample17);
		fixedProbFlag$sample37 = (fixedFlag$sample17 && fixedProbFlag$sample37);
		fixedProbFlag$sample53 = (fixedFlag$sample17 && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$fixedFlag$sample27() {
		return fixedFlag$sample27;
	}

	@Override
	public final void set$fixedFlag$sample27(boolean cv$value) {
		fixedFlag$sample27 = cv$value;
		fixedProbFlag$sample27 = (fixedFlag$sample27 && fixedProbFlag$sample27);
		fixedProbFlag$sample80 = (fixedFlag$sample27 && fixedProbFlag$sample80);
	}

	@Override
	public final boolean get$fixedFlag$sample37() {
		return fixedFlag$sample37;
	}

	@Override
	public final void set$fixedFlag$sample37(boolean cv$value) {
		fixedFlag$sample37 = cv$value;
		fixedProbFlag$sample37 = (fixedFlag$sample37 && fixedProbFlag$sample37);
		fixedProbFlag$sample53 = (fixedFlag$sample37 && fixedProbFlag$sample53);
		fixedProbFlag$sample80 = (fixedFlag$sample37 && fixedProbFlag$sample80);
	}

	@Override
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	@Override
	public final void set$fixedFlag$sample53(boolean cv$value) {
		fixedFlag$sample53 = cv$value;
		fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedProbFlag$sample53);
		fixedProbFlag$sample80 = (fixedFlag$sample53 && fixedProbFlag$sample80);
	}

	@Override
	public final boolean get$fixedFlag$sample80() {
		return fixedFlag$sample80;
	}

	@Override
	public final void set$fixedFlag$sample80(boolean cv$value) {
		fixedFlag$sample80 = cv$value;
		fixedProbFlag$sample80 = (fixedFlag$sample80 && fixedProbFlag$sample80);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
	}

	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	@Override
	public final void set$flipsMeasured(boolean[] cv$value) {
		flipsMeasured = cv$value;
	}

	@Override
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int cv$value) {
		length$flipsMeasured = cv$value;
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
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	@Override
	public final double get$logProbability$m() {
		return logProbability$m;
	}

	@Override
	public final double get$logProbability$st() {
		return logProbability$st;
	}

	@Override
	public final double get$logProbability$st2() {
		return logProbability$st2;
	}

	@Override
	public final double[][] get$m() {
		return m;
	}

	@Override
	public final void set$m(double[][] cv$value) {
		m = cv$value;
		setFlag$m = true;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	@Override
	public final int[] get$st() {
		return st;
	}

	@Override
	public final void set$st(int[] cv$value) {
		st = cv$value;
		setFlag$st = true;
	}

	@Override
	public final int[] get$st2() {
		return st2;
	}

	@Override
	public final void set$st2(int[] cv$value) {
		st2 = cv$value;
		setFlag$st2 = true;
	}

	@Override
	public final int get$states() {
		return states;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample17() {
		if(!fixedProbFlag$sample17) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var15 = 0; var15 < states; var15 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var15];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v));
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
			logProbability$var11 = cv$sampleAccumulator;
			logProbability$var16 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample17 = fixedFlag$sample17;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var16;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var11 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample27() {
		if(!fixedProbFlag$sample27) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var24 = 0; var24 < states; var24 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = bias[var24];
					{
						{
							double var18 = 1.0;
							double var19 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var18, var19));
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
			logProbability$var20 = cv$sampleAccumulator;
			logProbability$var25 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample27 = fixedFlag$sample27;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var25;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var20 = cv$rvAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample37() {
		if(!fixedProbFlag$sample37) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = st[0];
				{
					{
						double[] var32 = m[0];
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var32.length))?Math.log(var32[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var33 = cv$sampleAccumulator;
			logProbability$var34 = cv$sampleProbability;
			boolean cv$guard$st2 = false;
			logProbability$st = (logProbability$st + cv$accumulator);
			{
				if((0 == 0)) {
					if(!cv$guard$st2) {
						cv$guard$st2 = true;
						logProbability$st2 = (logProbability$st2 + cv$accumulator);
					}
				}
			}
			{
				for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
					if((0 == (indirection[((i$var43 - 1) / 1)][i$var43] / i$var43))) {
						if(!cv$guard$st2) {
							cv$guard$st2 = true;
							logProbability$st2 = (logProbability$st2 + cv$accumulator);
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample37)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample37 = (fixedFlag$sample37 && fixedFlag$sample17);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var34;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var33 = cv$rvAccumulator;
			boolean cv$guard$st2 = false;
			logProbability$st = (logProbability$st + cv$accumulator);
			{
				if((0 == 0)) {
					if(!cv$guard$st2) {
						cv$guard$st2 = true;
						logProbability$st2 = (logProbability$st2 + cv$accumulator);
					}
				}
			}
			{
				for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
					if((0 == (indirection[((i$var43 - 1) / 1)][i$var43] / i$var43))) {
						if(!cv$guard$st2) {
							cv$guard$st2 = true;
							logProbability$st2 = (logProbability$st2 + cv$accumulator);
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample37)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$accumulator = 0.0;
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = st[i$var43];
					{
						{
							double[] var48 = m[(samples - st2[(i$var43 - 1)])];
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var48.length))?Math.log(var48[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var49[((i$var43 - 1) / 1)] = cv$sampleAccumulator;
				logProbability$sample53[((i$var43 - 1) / 1)] = cv$sampleProbability;
				boolean cv$guard$st2 = false;
				{
					for(int index$i$2_1 = 1; index$i$2_1 < samples; index$i$2_1 += 1) {
						if((i$var43 == (indirection[((index$i$2_1 - 1) / 1)][index$i$2_1] / index$i$2_1))) {
							if(!cv$guard$st2) {
								cv$guard$st2 = true;
								logProbability$st2 = (logProbability$st2 + cv$sampleProbability);
							}
						}
					}
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample53 = ((fixedFlag$sample53 && fixedFlag$sample17) && fixedFlag$sample37);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample53[((i$var43 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var49[((i$var43 - 1) / 1)] = cv$rvAccumulator;
				boolean cv$guard$st2 = false;
				{
					for(int index$i$3_1 = 1; index$i$3_1 < samples; index$i$3_1 += 1) {
						if((i$var43 == (indirection[((index$i$3_1 - 1) / 1)][index$i$3_1] / index$i$3_1))) {
							if(!cv$guard$st2) {
								cv$guard$st2 = true;
								logProbability$st2 = (logProbability$st2 + cv$sampleValue);
							}
						}
					}
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample80() {
		if(!fixedProbFlag$sample80) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[j];
					{
						{
							double var75 = bias[(samples - st2[j])];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var75));
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
				logProbability$var76[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample80[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample80 = (((fixedFlag$sample80 && fixedFlag$sample27) && fixedFlag$sample37) && fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample80[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var76[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample17(int var15, int threadID$cv$var15, Rng RNG$) {
		double[] cv$targetLocal = m[var15];
		double[] cv$countLocal = cv$var16$countGlobal[threadID$cv$var15];
		int cv$arrayLength = states;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					if((var15 == 0)) {
						{
							{
								{
									{
										{
											cv$countLocal[st[0]] = (cv$countLocal[st[0]] + 1.0);
										}
									}
								}
							}
						}
					}
				}
			}
			{
				{
					for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
						if((var15 == (samples - st2[(i$var43 - 1)]))) {
							{
								{
									{
										{
											{
												cv$countLocal[st[i$var43]] = (cv$countLocal[st[i$var43]] + 1.0);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	private final void sample27(int var24, int threadID$cv$var24, Rng RNG$) {
		int cv$sum = 0;
		int cv$count = 0;
		{
			{
				{
					for(int j = 0; j < samples; j += 1) {
						if((var24 == (samples - st2[j]))) {
							{
								{
									{
										{
											{
												cv$count = (cv$count + 1);
												if(flips[j])
													cv$sum = (cv$sum + 1);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		double var25 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[var24] = var25;
	}

	private final void sample37() {
		double[] cv$stateProbabilityLocal = cv$var34$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < states; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			int var34 = cv$currentValue;
			st[0] = cv$currentValue;
			{
				if((0 == 0)) {
					{
						st2[0] = (samples - st[0]);
					}
				}
			}
			{
				for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
					if((0 == (indirection[((i$var43 - 1) / 1)][i$var43] / i$var43))) {
						{
							st2[(indirection[((i$var43 - 1) / 1)][i$var43] / i$var43)] = (samples - st[(indirection[((i$var43 - 1) / 1)][i$var43] / i$var43)]);
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var32;
				{
					double[] var32 = m[0];
					cv$temp$0$var32 = var32;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var32.length))?Math.log(cv$temp$0$var32[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var38$3_1 = cv$currentValue;
						if((0 == 0)) {
							int traceTempVariable$var46$3_2 = (samples - traceTempVariable$var38$3_1);
							for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
								if((0 == (i$var43 - 1))) {
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											{
												{
													{
														double[] cv$temp$1$var48;
														{
															double[] var48 = m[(samples - traceTempVariable$var46$3_2)];
															cv$temp$1$var48 = var48;
														}
														if(((Math.log(1.0) + (((0.0 <= st[i$var43]) && (st[i$var43] < cv$temp$1$var48.length))?Math.log(cv$temp$1$var48[st[i$var43]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var43]) && (st[i$var43] < cv$temp$1$var48.length))?Math.log(cv$temp$1$var48[st[i$var43]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var43]) && (st[i$var43] < cv$temp$1$var48.length))?Math.log(cv$temp$1$var48[st[i$var43]]):Double.NEGATIVE_INFINITY));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var43]) && (st[i$var43] < cv$temp$1$var48.length))?Math.log(cv$temp$1$var48[st[i$var43]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var43]) && (st[i$var43] < cv$temp$1$var48.length))?Math.log(cv$temp$1$var48[st[i$var43]]):Double.NEGATIVE_INFINITY)));
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
						int traceTempVariable$var66$4_1 = cv$currentValue;
						for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
							if((0 == (indirection[((i$var43 - 1) / 1)][i$var43] / i$var43))) {
								int traceTempVariable$var46$4_3 = (samples - traceTempVariable$var66$4_1);
								for(int index$i$4_4 = 1; index$i$4_4 < samples; index$i$4_4 += 1) {
									if(((indirection[((i$var43 - 1) / 1)][i$var43] / i$var43) == (index$i$4_4 - 1))) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double[] cv$temp$2$var48;
															{
																double[] var48 = m[(samples - traceTempVariable$var46$4_3)];
																cv$temp$2$var48 = var48;
															}
															if(((Math.log(1.0) + (((0.0 <= st[index$i$4_4]) && (st[index$i$4_4] < cv$temp$2$var48.length))?Math.log(cv$temp$2$var48[st[index$i$4_4]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[index$i$4_4]) && (st[index$i$4_4] < cv$temp$2$var48.length))?Math.log(cv$temp$2$var48[st[index$i$4_4]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[index$i$4_4]) && (st[index$i$4_4] < cv$temp$2$var48.length))?Math.log(cv$temp$2$var48[st[index$i$4_4]]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[index$i$4_4]) && (st[index$i$4_4] < cv$temp$2$var48.length))?Math.log(cv$temp$2$var48[st[index$i$4_4]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[index$i$4_4]) && (st[index$i$4_4] < cv$temp$2$var48.length))?Math.log(cv$temp$2$var48[st[index$i$4_4]]):Double.NEGATIVE_INFINITY)));
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
				{
					{
						int traceTempVariable$var38$9_1 = cv$currentValue;
						if((0 == 0)) {
							int traceTempVariable$var73$9_2 = (samples - traceTempVariable$var38$9_1);
							for(int j = 0; j < samples; j += 1) {
								if((0 == j)) {
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											{
												{
													{
														double cv$temp$3$var75;
														{
															double var75 = bias[(samples - traceTempVariable$var73$9_2)];
															cv$temp$3$var75 = var75;
														}
														if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var75)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var75)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var75));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var75)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var75)));
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
						int traceTempVariable$var66$10_1 = cv$currentValue;
						for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
							if((0 == (indirection[((i$var43 - 1) / 1)][i$var43] / i$var43))) {
								int traceTempVariable$var73$10_3 = (samples - traceTempVariable$var66$10_1);
								for(int j = 0; j < samples; j += 1) {
									if(((indirection[((i$var43 - 1) / 1)][i$var43] / i$var43) == j)) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$4$var75;
															{
																double var75 = bias[(samples - traceTempVariable$var73$10_3)];
																cv$temp$4$var75 = var75;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var75)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var75)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var75));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var75)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var75)));
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
		double cv$logSum = 0.0;
		{
			double cv$lseMax = cv$stateProbabilityLocal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$stateProbabilityLocal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		int var34 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
		st[0] = var34;
		{
			if((0 == 0)) {
				{
					st2[0] = (samples - st[0]);
				}
			}
		}
		{
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
				if((0 == (indirection[((i$var43 - 1) / 1)][i$var43] / i$var43))) {
					{
						st2[(indirection[((i$var43 - 1) / 1)][i$var43] / i$var43)] = (samples - st[(indirection[((i$var43 - 1) / 1)][i$var43] / i$var43)]);
					}
				}
			}
		}
	}

	private final void sample53(int i$var43) {
		double[] cv$stateProbabilityLocal = cv$var50$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < states; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			int var50 = cv$currentValue;
			st[i$var43] = cv$currentValue;
			{
				for(int index$i$1_1 = 1; index$i$1_1 < samples; index$i$1_1 += 1) {
					if((i$var43 == (indirection[((index$i$1_1 - 1) / 1)][index$i$1_1] / index$i$1_1))) {
						{
							st2[(indirection[((index$i$1_1 - 1) / 1)][index$i$1_1] / index$i$1_1)] = (samples - st[(indirection[((index$i$1_1 - 1) / 1)][index$i$1_1] / index$i$1_1)]);
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var48;
				{
					double[] var48 = m[(samples - st2[(i$var43 - 1)])];
					cv$temp$0$var48 = var48;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var48.length))?Math.log(cv$temp$0$var48[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var66$2_1 = cv$currentValue;
						for(int index$i$2_2 = 1; index$i$2_2 < samples; index$i$2_2 += 1) {
							if((i$var43 == (indirection[((index$i$2_2 - 1) / 1)][index$i$2_2] / index$i$2_2))) {
								int traceTempVariable$var46$2_3 = (samples - traceTempVariable$var66$2_1);
								for(int index$i$2_4 = 1; index$i$2_4 < samples; index$i$2_4 += 1) {
									if(((indirection[((index$i$2_2 - 1) / 1)][index$i$2_2] / index$i$2_2) == (index$i$2_4 - 1))) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double[] cv$temp$1$var48;
															{
																double[] var48 = m[(samples - traceTempVariable$var46$2_3)];
																cv$temp$1$var48 = var48;
															}
															if(((Math.log(1.0) + (((0.0 <= st[index$i$2_4]) && (st[index$i$2_4] < cv$temp$1$var48.length))?Math.log(cv$temp$1$var48[st[index$i$2_4]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[index$i$2_4]) && (st[index$i$2_4] < cv$temp$1$var48.length))?Math.log(cv$temp$1$var48[st[index$i$2_4]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[index$i$2_4]) && (st[index$i$2_4] < cv$temp$1$var48.length))?Math.log(cv$temp$1$var48[st[index$i$2_4]]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[index$i$2_4]) && (st[index$i$2_4] < cv$temp$1$var48.length))?Math.log(cv$temp$1$var48[st[index$i$2_4]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[index$i$2_4]) && (st[index$i$2_4] < cv$temp$1$var48.length))?Math.log(cv$temp$1$var48[st[index$i$2_4]]):Double.NEGATIVE_INFINITY)));
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
				{
					{
						int traceTempVariable$var66$5_1 = cv$currentValue;
						for(int index$i$5_2 = 1; index$i$5_2 < samples; index$i$5_2 += 1) {
							if((i$var43 == (indirection[((index$i$5_2 - 1) / 1)][index$i$5_2] / index$i$5_2))) {
								int traceTempVariable$var73$5_3 = (samples - traceTempVariable$var66$5_1);
								for(int j = 0; j < samples; j += 1) {
									if(((indirection[((index$i$5_2 - 1) / 1)][index$i$5_2] / index$i$5_2) == j)) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$2$var75;
															{
																double var75 = bias[(samples - traceTempVariable$var73$5_3)];
																cv$temp$2$var75 = var75;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var75)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var75)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var75));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var75)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var75)));
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
		double cv$logSum = 0.0;
		{
			double cv$lseMax = cv$stateProbabilityLocal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$stateProbabilityLocal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		int var50 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
		st[i$var43] = var50;
		{
			for(int index$i$8_1 = 1; index$i$8_1 < samples; index$i$8_1 += 1) {
				if((i$var43 == (indirection[((index$i$8_1 - 1) / 1)][index$i$8_1] / index$i$8_1))) {
					{
						st2[(indirection[((index$i$8_1 - 1) / 1)][index$i$8_1] / index$i$8_1)] = (samples - st[(indirection[((index$i$8_1 - 1) / 1)][index$i$8_1] / index$i$8_1)]);
					}
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			for(int var15 = 0; var15 < 2; var15 += 1)
				cv$max = Math.max(cv$max, 2);
			{
				int cv$threadCount = threadCount();
				cv$var16$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var16$countGlobal[cv$index] = new double[cv$max];
			}
		}
		{
			int cv$var17$max = 2;
			cv$var34$stateProbabilityGlobal = new double[cv$var17$max];
		}
		{
			int cv$var17$max = 2;
			cv$var50$stateProbabilityGlobal = new double[cv$var17$max];
		}
	}

	@Override
	public final void allocator() {
		{
			v = new double[2];
		}
		if(!setFlag$m) {
			{
				m = new double[2][];
				for(int var15 = 0; var15 < 2; var15 += 1)
					m[var15] = new double[2];
			}
		}
		if(!setFlag$bias) {
			{
				bias = new double[2];
			}
		}
		if(!setFlag$st) {
			{
				st = new int[length$flipsMeasured];
			}
		}
		if(!setFlag$st2) {
			{
				st2 = new int[length$flipsMeasured];
			}
		}
		{
			indirection = new int[((((length$flipsMeasured - 1) - 1) / 1) + 1)][];
			for(int i$var43 = 1; i$var43 < length$flipsMeasured; i$var43 += 1)
				indirection[((i$var43 - 1) / 1)] = new int[(i$var43 + 1)];
		}
		if(!setFlag$flips) {
			{
				flips = new boolean[length$flipsMeasured];
			}
		}
		{
			logProbability$var49 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample53 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var76 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample80 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1) {
						double[] var16 = m[var15];
						if(!fixedFlag$sample17)
							DistributionSampling.sampleDirichlet(RNG$1, v, var16);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
						if(!fixedFlag$sample27)
							bias[var24] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample37)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample37)
			st2[0] = (samples - st[0]);
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if(!fixedFlag$sample53)
				st[i$var43] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var43 - 1)])]);
			if(!(fixedFlag$sample37 && fixedFlag$sample53))
				st2[(indirection[((i$var43 - 1) / 1)][i$var43] / i$var43)] = (samples - st[(indirection[((i$var43 - 1) / 1)][i$var43] / i$var43)]);
		}
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1) {
						if(!fixedFlag$sample80)
							flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[(samples - st2[j])]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1) {
						double[] var16 = m[var15];
						if(!fixedFlag$sample17)
							DistributionSampling.sampleDirichlet(RNG$1, v, var16);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
						if(!fixedFlag$sample27)
							bias[var24] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample37)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample37)
			st2[0] = (samples - st[0]);
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if(!fixedFlag$sample53)
				st[i$var43] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var43 - 1)])]);
			if(!(fixedFlag$sample37 && fixedFlag$sample53))
				st2[(indirection[((i$var43 - 1) / 1)][i$var43] / i$var43)] = (samples - st[(indirection[((i$var43 - 1) / 1)][i$var43] / i$var43)]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1) {
						double[] var16 = m[var15];
						if(!fixedFlag$sample17)
							DistributionSampling.sampleDirichlet(RNG$1, v, var16);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
						if(!fixedFlag$sample27)
							bias[var24] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample37)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample37)
			st2[0] = (samples - st[0]);
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if(!fixedFlag$sample53)
				st[i$var43] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var43 - 1)])]);
			if(!(fixedFlag$sample37 && fixedFlag$sample53))
				st2[(indirection[((i$var43 - 1) / 1)][i$var43] / i$var43)] = (samples - st[(indirection[((i$var43 - 1) / 1)][i$var43] / i$var43)]);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1) {
							if(!fixedFlag$sample17)
								sample17(var15, threadID$var15, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
							if(!fixedFlag$sample27)
								sample27(var24, threadID$var24, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample37)
				sample37();
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
				if(!fixedFlag$sample53)
					sample53(i$var43);
			}
		} else {
			for(int i$var43 = (samples - ((((samples - 1) - 1) % 1) + 1)); i$var43 >= ((1 - 1) + 1); i$var43 -= 1) {
				if(!fixedFlag$sample53)
					sample53(i$var43);
			}
			if(!fixedFlag$sample37)
				sample37();
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
							if(!fixedFlag$sample27)
								sample27(var24, threadID$var24, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1) {
							if(!fixedFlag$sample17)
								sample17(var15, threadID$var15, RNG$1);
						}
				}
			);
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		states = 2;
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$i$var8, int forEnd$i$var8, int threadID$i$var8, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var8 = forStart$i$var8; i$var8 < forEnd$i$var8; i$var8 += 1)
						v[i$var8] = 0.1;
			}
		);
		samples = length$flipsMeasured;
		for(int index$i$var43 = 1; index$i$var43 < length$flipsMeasured; index$i$var43 += 1) {
			int i$var43 = index$i$var43;
			parallelFor(RNG$, 0, (i$var43 + 1), 1,
				(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int k = forStart$k; k < forEnd$k; k += 1)
							indirection[((i$var43 - 1) / 1)][k] = (k * i$var43);
				}
			);
		}
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var11 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$var16 = 0.0;
		logProbability$var20 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample27)
			logProbability$var25 = 0.0;
		logProbability$var33 = 0.0;
		logProbability$st = 0.0;
		logProbability$st2 = 0.0;
		if(!fixedProbFlag$sample37)
			logProbability$var34 = 0.0;
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1)
			logProbability$var49[((i$var43 - 1) / 1)] = 0.0;
		if(!fixedProbFlag$sample53) {
			for(int i$var43 = 1; i$var43 < samples; i$var43 += 1)
				logProbability$sample53[((i$var43 - 1) / 1)] = 0.0;
		}
		for(int j = 0; j < samples; j += 1)
			logProbability$var76[((j - 0) / 1)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample80) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample80[((j - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample17)
			logProbabilityValue$sample17();
		if(fixedFlag$sample27)
			logProbabilityValue$sample27();
		if(fixedFlag$sample37)
			logProbabilityValue$sample37();
		if(fixedFlag$sample53)
			logProbabilityValue$sample53();
		logProbabilityValue$sample80();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample27();
		logProbabilityValue$sample37();
		logProbabilityValue$sample53();
		logProbabilityValue$sample80();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample27();
		logProbabilityValue$sample37();
		logProbabilityValue$sample53();
		logProbabilityValue$sample80();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1) {
						double[] var16 = m[var15];
						if(!fixedFlag$sample17)
							DistributionSampling.sampleDirichlet(RNG$1, v, var16);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1) {
						if(!fixedFlag$sample27)
							bias[var24] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample37)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample37)
			st2[0] = (samples - st[0]);
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if(!fixedFlag$sample53)
				st[i$var43] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var43 - 1)])]);
			if(!(fixedFlag$sample37 && fixedFlag$sample53))
				st2[(indirection[((i$var43 - 1) / 1)][i$var43] / i$var43)] = (samples - st[(indirection[((i$var43 - 1) / 1)][i$var43] / i$var43)]);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		boolean[] cv$source1 = flipsMeasured;
		boolean[] cv$target1 = flips;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		if(setFlag$st)
			st2[0] = (samples - st[0]);
		for(int i$var43 = 1; i$var43 < samples; i$var43 += 1) {
			if(setFlag$st)
				st2[(indirection[((i$var43 - 1) / 1)][i$var43] / i$var43)] = (samples - st[(indirection[((i$var43 - 1) / 1)][i$var43] / i$var43)]);
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart3d(boolean[] flipsMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n\n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        int[] st = new int[samples];\n        int[] st2 = new int[samples];\n\n        st[0] = categorical(m[0]).sample();\n        st2[0] = samples - st[0];\n\n        for(int i:[1..samples)) {\n            st[i] = categorical(m[samples - st2[i-1]]).sample();\n            \n            int[] indirection = new int[i+1];\n            for(int k:[0..i])\n                indirection[k] = k*i; \n                \n            st2[indirection[i]/i] = samples - st[indirection[i]/i];\n        }\n            \n        boolean[] flips = new boolean[samples];\n            \n        for(int j:[0..samples))\n            flips[j] = bernoulli(bias[samples - st2[j]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
	}
}