package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart3d$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart3d$CoreInterface {
	private double[] bias;
	private double[][] cv$var30$countGlobal;
	private double[] cv$var55$stateProbabilityGlobal;
	private double[] cv$var80$stateProbabilityGlobal;
	private boolean fixedFlag$sample123 = false;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample48 = false;
	private boolean fixedFlag$sample58 = false;
	private boolean fixedFlag$sample83 = false;
	private boolean fixedProbFlag$sample123 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample48 = false;
	private boolean fixedProbFlag$sample58 = false;
	private boolean fixedProbFlag$sample83 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int[][] indirection;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample123;
	private double[] logProbability$sample83;
	private double logProbability$st;
	private double logProbability$st2;
	private double[] logProbability$var119;
	private double logProbability$var18;
	private double logProbability$var30;
	private double logProbability$var34;
	private double logProbability$var46;
	private double logProbability$var54;
	private double logProbability$var55;
	private double[] logProbability$var79;
	private double[][] m;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
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
		fixedProbFlag$sample48 = false;
		fixedProbFlag$sample123 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample123() {
		return fixedFlag$sample123;
	}

	@Override
	public final void set$fixedFlag$sample123(boolean cv$value) {
		fixedFlag$sample123 = cv$value;
		fixedProbFlag$sample123 = (fixedFlag$sample123 && fixedProbFlag$sample123);
	}

	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	@Override
	public final void set$fixedFlag$sample31(boolean cv$value) {
		fixedFlag$sample31 = cv$value;
		fixedProbFlag$sample31 = (fixedFlag$sample31 && fixedProbFlag$sample31);
		fixedProbFlag$sample58 = (fixedFlag$sample31 && fixedProbFlag$sample58);
		fixedProbFlag$sample83 = (fixedFlag$sample31 && fixedProbFlag$sample83);
	}

	@Override
	public final boolean get$fixedFlag$sample48() {
		return fixedFlag$sample48;
	}

	@Override
	public final void set$fixedFlag$sample48(boolean cv$value) {
		fixedFlag$sample48 = cv$value;
		fixedProbFlag$sample48 = (fixedFlag$sample48 && fixedProbFlag$sample48);
		fixedProbFlag$sample123 = (fixedFlag$sample48 && fixedProbFlag$sample123);
	}

	@Override
	public final boolean get$fixedFlag$sample58() {
		return fixedFlag$sample58;
	}

	@Override
	public final void set$fixedFlag$sample58(boolean cv$value) {
		fixedFlag$sample58 = cv$value;
		fixedProbFlag$sample58 = (fixedFlag$sample58 && fixedProbFlag$sample58);
		fixedProbFlag$sample83 = (fixedFlag$sample58 && fixedProbFlag$sample83);
		fixedProbFlag$sample123 = (fixedFlag$sample58 && fixedProbFlag$sample123);
	}

	@Override
	public final boolean get$fixedFlag$sample83() {
		return fixedFlag$sample83;
	}

	@Override
	public final void set$fixedFlag$sample83(boolean cv$value) {
		fixedFlag$sample83 = cv$value;
		fixedProbFlag$sample83 = (fixedFlag$sample83 && fixedProbFlag$sample83);
		fixedProbFlag$sample123 = (fixedFlag$sample83 && fixedProbFlag$sample123);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample123 = false;
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
		fixedProbFlag$sample31 = false;
		fixedProbFlag$sample58 = false;
		fixedProbFlag$sample83 = false;
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
		fixedProbFlag$sample58 = false;
		fixedProbFlag$sample83 = false;
		fixedProbFlag$sample123 = false;
	}

	@Override
	public final int[] get$st2() {
		return st2;
	}

	@Override
	public final int get$states() {
		return states;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample123() {
		if(!fixedProbFlag$sample123) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[j];
					{
						{
							double var118 = bias[(samples - st2[j])];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var118));
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
				logProbability$var119[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample123[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample123 = (((fixedFlag$sample123 && fixedFlag$sample48) && fixedFlag$sample58) && fixedFlag$sample83);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample123[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var119[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!fixedProbFlag$sample31) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var29 = 0; var29 < states; var29 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var29];
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
			logProbability$var18 = cv$sampleAccumulator;
			logProbability$var30 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample31 = fixedFlag$sample31;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var30;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var18 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample48() {
		if(!fixedProbFlag$sample48) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var45 = 0; var45 < states; var45 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = bias[var45];
					{
						{
							double var32 = 1.0;
							double var33 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var32, var33));
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
			logProbability$var34 = cv$sampleAccumulator;
			logProbability$var46 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample48)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample48 = fixedFlag$sample48;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var46;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var34 = cv$rvAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample48)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample58() {
		if(!fixedProbFlag$sample58) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = st[0];
				{
					{
						double[] var53 = m[0];
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var53.length))?Math.log(var53[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var54 = cv$sampleAccumulator;
			logProbability$var55 = cv$sampleProbability;
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
				for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
					if((0 == (indirection[((i$var73 - 1) / 1)][i$var73] / i$var73))) {
						if(!cv$guard$st2) {
							cv$guard$st2 = true;
							logProbability$st2 = (logProbability$st2 + cv$accumulator);
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample58 = (fixedFlag$sample58 && fixedFlag$sample31);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var55;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var54 = cv$rvAccumulator;
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
				for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
					if((0 == (indirection[((i$var73 - 1) / 1)][i$var73] / i$var73))) {
						if(!cv$guard$st2) {
							cv$guard$st2 = true;
							logProbability$st2 = (logProbability$st2 + cv$accumulator);
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample83() {
		if(!fixedProbFlag$sample83) {
			double cv$accumulator = 0.0;
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = st[i$var73];
					{
						{
							double[] var78 = m[(samples - st2[(i$var73 - 1)])];
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var78.length))?Math.log(var78[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var79[((i$var73 - 1) / 1)] = cv$sampleAccumulator;
				logProbability$sample83[((i$var73 - 1) / 1)] = cv$sampleProbability;
				boolean cv$guard$st2 = false;
				{
					for(int index$i$2_1 = 1; index$i$2_1 < samples; index$i$2_1 += 1) {
						if((i$var73 == (indirection[((index$i$2_1 - 1) / 1)][index$i$2_1] / index$i$2_1))) {
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
			if(fixedFlag$sample83)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample83 = ((fixedFlag$sample83 && fixedFlag$sample31) && fixedFlag$sample58);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample83[((i$var73 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var79[((i$var73 - 1) / 1)] = cv$rvAccumulator;
				boolean cv$guard$st2 = false;
				{
					for(int index$i$3_1 = 1; index$i$3_1 < samples; index$i$3_1 += 1) {
						if((i$var73 == (indirection[((index$i$3_1 - 1) / 1)][index$i$3_1] / index$i$3_1))) {
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
			if(fixedFlag$sample83)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample31(int var29, int threadID$cv$var29, Rng RNG$) {
		double[] cv$targetLocal = m[var29];
		double[] cv$countLocal = cv$var30$countGlobal[threadID$cv$var29];
		int cv$arrayLength = states;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					if((var29 == 0)) {
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
					for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
						if((var29 == (samples - st2[(i$var73 - 1)]))) {
							{
								{
									{
										{
											{
												cv$countLocal[st[i$var73]] = (cv$countLocal[st[i$var73]] + 1.0);
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

	private final void sample48(int var45, int threadID$cv$var45, Rng RNG$) {
		int cv$sum = 0;
		int cv$count = 0;
		{
			{
				{
					for(int j = 0; j < samples; j += 1) {
						if((var45 == (samples - st2[j]))) {
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
		double var46 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[var45] = var46;
	}

	private final void sample58() {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, states);
		}
		double[] cv$stateProbabilityLocal = cv$var55$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			int var55 = cv$currentValue;
			st[0] = cv$currentValue;
			{
				if((0 == 0)) {
					{
						st2[0] = (samples - st[0]);
					}
				}
			}
			{
				for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
					if((0 == (indirection[((i$var73 - 1) / 1)][i$var73] / i$var73))) {
						{
							st2[(indirection[((i$var73 - 1) / 1)][i$var73] / i$var73)] = (samples - st[(indirection[((i$var73 - 1) / 1)][i$var73] / i$var73)]);
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var53;
				{
					double[] var53 = m[0];
					cv$temp$0$var53 = var53;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var53.length))?Math.log(cv$temp$0$var53[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var60$3_1 = cv$currentValue;
						if((0 == 0)) {
							int traceTempVariable$var76$3_2 = (samples - traceTempVariable$var60$3_1);
							for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
								if((0 == (i$var73 - 1))) {
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											{
												{
													{
														double[] cv$temp$1$var78;
														{
															double[] var78 = m[(samples - traceTempVariable$var76$3_2)];
															cv$temp$1$var78 = var78;
														}
														if(((Math.log(1.0) + (((0.0 <= st[i$var73]) && (st[i$var73] < cv$temp$1$var78.length))?Math.log(cv$temp$1$var78[st[i$var73]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var73]) && (st[i$var73] < cv$temp$1$var78.length))?Math.log(cv$temp$1$var78[st[i$var73]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var73]) && (st[i$var73] < cv$temp$1$var78.length))?Math.log(cv$temp$1$var78[st[i$var73]]):Double.NEGATIVE_INFINITY));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var73]) && (st[i$var73] < cv$temp$1$var78.length))?Math.log(cv$temp$1$var78[st[i$var73]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var73]) && (st[i$var73] < cv$temp$1$var78.length))?Math.log(cv$temp$1$var78[st[i$var73]]):Double.NEGATIVE_INFINITY)));
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
						int traceTempVariable$var102$4_1 = cv$currentValue;
						for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
							if((0 == (indirection[((i$var73 - 1) / 1)][i$var73] / i$var73))) {
								int traceTempVariable$var76$4_3 = (samples - traceTempVariable$var102$4_1);
								for(int index$i$4_4 = 1; index$i$4_4 < samples; index$i$4_4 += 1) {
									if(((indirection[((i$var73 - 1) / 1)][i$var73] / i$var73) == (index$i$4_4 - 1))) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double[] cv$temp$2$var78;
															{
																double[] var78 = m[(samples - traceTempVariable$var76$4_3)];
																cv$temp$2$var78 = var78;
															}
															if(((Math.log(1.0) + (((0.0 <= st[index$i$4_4]) && (st[index$i$4_4] < cv$temp$2$var78.length))?Math.log(cv$temp$2$var78[st[index$i$4_4]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[index$i$4_4]) && (st[index$i$4_4] < cv$temp$2$var78.length))?Math.log(cv$temp$2$var78[st[index$i$4_4]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[index$i$4_4]) && (st[index$i$4_4] < cv$temp$2$var78.length))?Math.log(cv$temp$2$var78[st[index$i$4_4]]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[index$i$4_4]) && (st[index$i$4_4] < cv$temp$2$var78.length))?Math.log(cv$temp$2$var78[st[index$i$4_4]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[index$i$4_4]) && (st[index$i$4_4] < cv$temp$2$var78.length))?Math.log(cv$temp$2$var78[st[index$i$4_4]]):Double.NEGATIVE_INFINITY)));
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
						int traceTempVariable$var60$9_1 = cv$currentValue;
						if((0 == 0)) {
							int traceTempVariable$var116$9_2 = (samples - traceTempVariable$var60$9_1);
							for(int j = 0; j < samples; j += 1) {
								if((0 == j)) {
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											{
												{
													{
														double cv$temp$3$var118;
														{
															double var118 = bias[(samples - traceTempVariable$var116$9_2)];
															cv$temp$3$var118 = var118;
														}
														if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var118)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var118)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var118));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var118)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var118)));
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
						int traceTempVariable$var102$10_1 = cv$currentValue;
						for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
							if((0 == (indirection[((i$var73 - 1) / 1)][i$var73] / i$var73))) {
								int traceTempVariable$var116$10_3 = (samples - traceTempVariable$var102$10_1);
								for(int j = 0; j < samples; j += 1) {
									if(((indirection[((i$var73 - 1) / 1)][i$var73] / i$var73) == j)) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$4$var118;
															{
																double var118 = bias[(samples - traceTempVariable$var116$10_3)];
																cv$temp$4$var118 = var118;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var118)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var118)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var118));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var118)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var118)));
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
		int var55 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
		st[0] = var55;
		{
			if((0 == 0)) {
				{
					st2[0] = (samples - st[0]);
				}
			}
		}
		{
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
				if((0 == (indirection[((i$var73 - 1) / 1)][i$var73] / i$var73))) {
					{
						st2[(indirection[((i$var73 - 1) / 1)][i$var73] / i$var73)] = (samples - st[(indirection[((i$var73 - 1) / 1)][i$var73] / i$var73)]);
					}
				}
			}
		}
	}

	private final void sample83(int i$var73) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, states);
		}
		double[] cv$stateProbabilityLocal = cv$var80$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			int var80 = cv$currentValue;
			st[i$var73] = cv$currentValue;
			{
				for(int index$i$1_1 = 1; index$i$1_1 < samples; index$i$1_1 += 1) {
					if((i$var73 == (indirection[((index$i$1_1 - 1) / 1)][index$i$1_1] / index$i$1_1))) {
						{
							st2[(indirection[((index$i$1_1 - 1) / 1)][index$i$1_1] / index$i$1_1)] = (samples - st[(indirection[((index$i$1_1 - 1) / 1)][index$i$1_1] / index$i$1_1)]);
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var78;
				{
					double[] var78 = m[(samples - st2[(i$var73 - 1)])];
					cv$temp$0$var78 = var78;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var78.length))?Math.log(cv$temp$0$var78[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var102$2_1 = cv$currentValue;
						for(int index$i$2_2 = 1; index$i$2_2 < samples; index$i$2_2 += 1) {
							if((i$var73 == (indirection[((index$i$2_2 - 1) / 1)][index$i$2_2] / index$i$2_2))) {
								int traceTempVariable$var76$2_3 = (samples - traceTempVariable$var102$2_1);
								for(int index$i$2_4 = 1; index$i$2_4 < samples; index$i$2_4 += 1) {
									if(((indirection[((index$i$2_2 - 1) / 1)][index$i$2_2] / index$i$2_2) == (index$i$2_4 - 1))) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double[] cv$temp$1$var78;
															{
																double[] var78 = m[(samples - traceTempVariable$var76$2_3)];
																cv$temp$1$var78 = var78;
															}
															if(((Math.log(1.0) + (((0.0 <= st[index$i$2_4]) && (st[index$i$2_4] < cv$temp$1$var78.length))?Math.log(cv$temp$1$var78[st[index$i$2_4]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[index$i$2_4]) && (st[index$i$2_4] < cv$temp$1$var78.length))?Math.log(cv$temp$1$var78[st[index$i$2_4]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[index$i$2_4]) && (st[index$i$2_4] < cv$temp$1$var78.length))?Math.log(cv$temp$1$var78[st[index$i$2_4]]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[index$i$2_4]) && (st[index$i$2_4] < cv$temp$1$var78.length))?Math.log(cv$temp$1$var78[st[index$i$2_4]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[index$i$2_4]) && (st[index$i$2_4] < cv$temp$1$var78.length))?Math.log(cv$temp$1$var78[st[index$i$2_4]]):Double.NEGATIVE_INFINITY)));
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
						int traceTempVariable$var102$5_1 = cv$currentValue;
						for(int index$i$5_2 = 1; index$i$5_2 < samples; index$i$5_2 += 1) {
							if((i$var73 == (indirection[((index$i$5_2 - 1) / 1)][index$i$5_2] / index$i$5_2))) {
								int traceTempVariable$var116$5_3 = (samples - traceTempVariable$var102$5_1);
								for(int j = 0; j < samples; j += 1) {
									if(((indirection[((index$i$5_2 - 1) / 1)][index$i$5_2] / index$i$5_2) == j)) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$2$var118;
															{
																double var118 = bias[(samples - traceTempVariable$var116$5_3)];
																cv$temp$2$var118 = var118;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var118)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var118)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var118));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var118)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var118)));
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
		int var80 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
		st[i$var73] = var80;
		{
			for(int index$i$8_1 = 1; index$i$8_1 < samples; index$i$8_1 += 1) {
				if((i$var73 == (indirection[((index$i$8_1 - 1) / 1)][index$i$8_1] / index$i$8_1))) {
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
			for(int var29 = 0; var29 < 2; var29 += 1)
				cv$max = Math.max(cv$max, 2);
			{
				int cv$threadCount = threadCount();
				cv$var30$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var30$countGlobal[cv$index] = new double[cv$max];
			}
		}
		{
			int cv$var31$max = 2;
			cv$var55$stateProbabilityGlobal = new double[cv$var31$max];
		}
		{
			int cv$var31$max = 2;
			cv$var80$stateProbabilityGlobal = new double[cv$var31$max];
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
				for(int var29 = 0; var29 < 2; var29 += 1)
					m[var29] = new double[2];
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
		{
			st2 = new int[length$flipsMeasured];
		}
		{
			indirection = new int[((((length$flipsMeasured - 1) - 1) / 1) + 1)][];
			for(int i$var73 = 1; i$var73 < length$flipsMeasured; i$var73 += 1)
				indirection[((i$var73 - 1) / 1)] = new int[(i$var73 + 1)];
		}
		if(!setFlag$flips) {
			{
				flips = new boolean[length$flipsMeasured];
			}
		}
		{
			logProbability$var79 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample83 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var119 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample123 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = m[var29];
						if(!fixedFlag$sample31)
							DistributionSampling.sampleDirichlet(RNG$1, v, var30);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!fixedFlag$sample48)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample58)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample58)
			st2[0] = (samples - st[0]);
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if(!fixedFlag$sample83)
				st[i$var73] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var73 - 1)])]);
			if(!(fixedFlag$sample58 && fixedFlag$sample83))
				st2[(indirection[((i$var73 - 1) / 1)][i$var73] / i$var73)] = (samples - st[(indirection[((i$var73 - 1) / 1)][i$var73] / i$var73)]);
		}
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1) {
						if(!fixedFlag$sample123)
							flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[(samples - st2[j])]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = m[var29];
						if(!fixedFlag$sample31)
							DistributionSampling.sampleDirichlet(RNG$1, v, var30);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!fixedFlag$sample48)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample58)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample58)
			st2[0] = (samples - st[0]);
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if(!fixedFlag$sample83)
				st[i$var73] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var73 - 1)])]);
			if(!(fixedFlag$sample58 && fixedFlag$sample83))
				st2[(indirection[((i$var73 - 1) / 1)][i$var73] / i$var73)] = (samples - st[(indirection[((i$var73 - 1) / 1)][i$var73] / i$var73)]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = m[var29];
						if(!fixedFlag$sample31)
							DistributionSampling.sampleDirichlet(RNG$1, v, var30);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!fixedFlag$sample48)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample58)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample58)
			st2[0] = (samples - st[0]);
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if(!fixedFlag$sample83)
				st[i$var73] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var73 - 1)])]);
			if(!(fixedFlag$sample58 && fixedFlag$sample83))
				st2[(indirection[((i$var73 - 1) / 1)][i$var73] / i$var73)] = (samples - st[(indirection[((i$var73 - 1) / 1)][i$var73] / i$var73)]);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
							if(!fixedFlag$sample31)
								sample31(var29, threadID$var29, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
							if(!fixedFlag$sample48)
								sample48(var45, threadID$var45, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample58)
				sample58();
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
				if(!fixedFlag$sample83)
					sample83(i$var73);
			}
		} else {
			for(int i$var73 = (samples - ((((samples - 1) - 1) % 1) + 1)); i$var73 >= ((1 - 1) + 1); i$var73 -= 1) {
				if(!fixedFlag$sample83)
					sample83(i$var73);
			}
			if(!fixedFlag$sample58)
				sample58();
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
							if(!fixedFlag$sample48)
								sample48(var45, threadID$var45, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
							if(!fixedFlag$sample31)
								sample31(var29, threadID$var29, RNG$1);
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
			(int forStart$i$var15, int forEnd$i$var15, int threadID$i$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var15 = forStart$i$var15; i$var15 < forEnd$i$var15; i$var15 += 1)
						v[i$var15] = 0.1;
			}
		);
		samples = length$flipsMeasured;
		for(int i$var73 = 1; i$var73 < length$flipsMeasured; i$var73 += 1) {
			int i$var73$1 = i$var73;
			parallelFor(RNG$, 0, (i$var73$1 + 1), 1,
				(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int k = forStart$k; k < forEnd$k; k += 1)
							indirection[((i$var73$1 - 1) / 1)][k] = (k * i$var73$1);
				}
			);
		}
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var18 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$var30 = 0.0;
		logProbability$var34 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample48)
			logProbability$var46 = 0.0;
		logProbability$var54 = 0.0;
		logProbability$st2 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample58)
			logProbability$var55 = 0.0;
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1)
			logProbability$var79[((i$var73 - 1) / 1)] = 0.0;
		if(!fixedProbFlag$sample83) {
			for(int i$var73 = 1; i$var73 < samples; i$var73 += 1)
				logProbability$sample83[((i$var73 - 1) / 1)] = 0.0;
		}
		for(int j = 0; j < samples; j += 1)
			logProbability$var119[((j - 0) / 1)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample123) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample123[((j - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample31)
			logProbabilityValue$sample31();
		if(fixedFlag$sample48)
			logProbabilityValue$sample48();
		if(fixedFlag$sample58)
			logProbabilityValue$sample58();
		if(fixedFlag$sample83)
			logProbabilityValue$sample83();
		logProbabilityValue$sample123();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample31();
		logProbabilityValue$sample48();
		logProbabilityValue$sample58();
		logProbabilityValue$sample83();
		logProbabilityValue$sample123();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample31();
		logProbabilityValue$sample48();
		logProbabilityValue$sample58();
		logProbabilityValue$sample83();
		logProbabilityValue$sample123();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = m[var29];
						if(!fixedFlag$sample31)
							DistributionSampling.sampleDirichlet(RNG$1, v, var30);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!fixedFlag$sample48)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample58)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample58)
			st2[0] = (samples - st[0]);
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if(!fixedFlag$sample83)
				st[i$var73] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var73 - 1)])]);
			if(!(fixedFlag$sample58 && fixedFlag$sample83))
				st2[(indirection[((i$var73 - 1) / 1)][i$var73] / i$var73)] = (samples - st[(indirection[((i$var73 - 1) / 1)][i$var73] / i$var73)]);
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
		for(int i$var73 = 1; i$var73 < samples; i$var73 += 1) {
			if(setFlag$st)
				st2[(indirection[((i$var73 - 1) / 1)][i$var73] / i$var73)] = (samples - st[(indirection[((i$var73 - 1) / 1)][i$var73] / i$var73)]);
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
		     + "model HMMTestPart3d(boolean[] flipsMeasured) {\n"
		     + "        int states = 2;\n"
		     + "\n"
		     + "        double[] v = new double[states];\n"
		     + "        for(int i:[0..states))\n"
		     + "            v[i] = 0.1;\n"
		     + "\n"
		     + "        double[][] m = dirichlet(v).sample(states);\n"
		     + "        double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "        int samples = flipsMeasured.length;\n"
		     + "        int[] st = new int[samples];\n"
		     + "        int[] st2 = new int[samples];\n"
		     + "\n"
		     + "        st[0] = categorical(m[0]).sample();\n"
		     + "        st2[0] = samples - st[0];\n"
		     + "\n"
		     + "        for(int i:[1..samples)) {\n"
		     + "            st[i] = categorical(m[samples - st2[i-1]]).sample();\n"
		     + "            \n"
		     + "            int[] indirection = new int[i+1];\n"
		     + "            for(int k:[0..i])\n"
		     + "                indirection[k] = k*i; \n"
		     + "                \n"
		     + "            st2[indirection[i]/i] = samples - st[indirection[i]/i];\n"
		     + "        }\n"
		     + "            \n"
		     + "        boolean[] flips = new boolean[samples];\n"
		     + "            \n"
		     + "        for(int j:[0..samples))\n"
		     + "            flips[j] = bernoulli(bias[samples - st2[j]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}