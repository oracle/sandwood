package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart3d$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart3d$CoreInterface {
	private double[] bias;
	private double[][] cv$var28$countGlobal;
	private double[] cv$var53$stateProbabilityGlobal;
	private double[] cv$var78$stateProbabilityGlobal;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample54 = false;
	private boolean fixedFlag$sample79 = false;
	private boolean fixedProbFlag$sample119 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample54 = false;
	private boolean fixedProbFlag$sample79 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int[][] indirection;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample119;
	private double[] logProbability$sample79;
	private double logProbability$st;
	private double logProbability$st2;
	private double[] logProbability$var117;
	private double logProbability$var16;
	private double logProbability$var28;
	private double logProbability$var32;
	private double logProbability$var44;
	private double logProbability$var52;
	private double logProbability$var53;
	private double[] logProbability$var77;
	private double[][] m;
	private int samples;
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
		fixedProbFlag$sample45 = false;
		fixedProbFlag$sample119 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		fixedFlag$sample28 = cv$value;
		fixedProbFlag$sample28 = (fixedFlag$sample28 && fixedProbFlag$sample28);
		fixedProbFlag$sample54 = (fixedFlag$sample28 && fixedProbFlag$sample54);
		fixedProbFlag$sample79 = (fixedFlag$sample28 && fixedProbFlag$sample79);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		fixedFlag$sample45 = cv$value;
		fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedProbFlag$sample45);
		fixedProbFlag$sample119 = (fixedFlag$sample45 && fixedProbFlag$sample119);
	}

	@Override
	public final boolean get$fixedFlag$sample54() {
		return fixedFlag$sample54;
	}

	@Override
	public final void set$fixedFlag$sample54(boolean cv$value) {
		fixedFlag$sample54 = cv$value;
		fixedProbFlag$sample54 = (fixedFlag$sample54 && fixedProbFlag$sample54);
		fixedProbFlag$sample79 = (fixedFlag$sample54 && fixedProbFlag$sample79);
		fixedProbFlag$sample119 = (fixedFlag$sample54 && fixedProbFlag$sample119);
	}

	@Override
	public final boolean get$fixedFlag$sample79() {
		return fixedFlag$sample79;
	}

	@Override
	public final void set$fixedFlag$sample79(boolean cv$value) {
		fixedFlag$sample79 = cv$value;
		fixedProbFlag$sample79 = (fixedFlag$sample79 && fixedProbFlag$sample79);
		fixedProbFlag$sample119 = (fixedFlag$sample79 && fixedProbFlag$sample119);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
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
		fixedProbFlag$sample28 = false;
		fixedProbFlag$sample54 = false;
		fixedProbFlag$sample79 = false;
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
		fixedProbFlag$sample54 = false;
		fixedProbFlag$sample79 = false;
		fixedProbFlag$sample119 = false;
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

	private final void logProbabilityValue$sample119() {
		if(!fixedProbFlag$sample119) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[j];
					{
						{
							double var116 = bias[(samples - st2[j])];
							double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var116:(1.0 - var116))));
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
				logProbability$var117[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample119[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample119 = ((fixedFlag$sample45 && fixedFlag$sample54) && fixedFlag$sample79);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample119[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var117[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var27 = 0; var27 < states; var27 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var27];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, states));
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
			logProbability$var16 = cv$sampleAccumulator;
			logProbability$var28 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var28;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var16 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!fixedProbFlag$sample45) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var43 = 0; var43 < states; var43 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = bias[var43];
					{
						{
							double var30 = 1.0;
							double var31 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var30, var31));
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
			logProbability$var32 = cv$sampleAccumulator;
			logProbability$var44 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample45 = fixedFlag$sample45;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var44;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var32 = cv$rvAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample54() {
		if(!fixedProbFlag$sample54) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = st[0];
				{
					{
						double[] var51 = m[0];
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var51[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var52 = cv$sampleAccumulator;
			logProbability$var53 = cv$sampleProbability;
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
				for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
					if((0 == (indirection[((i$var71 - 1) / 1)][i$var71] / i$var71))) {
						if(!cv$guard$st2) {
							cv$guard$st2 = true;
							logProbability$st2 = (logProbability$st2 + cv$accumulator);
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample54)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample54 = (fixedFlag$sample54 && fixedFlag$sample28);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var53;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var52 = cv$rvAccumulator;
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
				for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
					if((0 == (indirection[((i$var71 - 1) / 1)][i$var71] / i$var71))) {
						if(!cv$guard$st2) {
							cv$guard$st2 = true;
							logProbability$st2 = (logProbability$st2 + cv$accumulator);
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample54)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample79() {
		if(!fixedProbFlag$sample79) {
			double cv$accumulator = 0.0;
			for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = st[i$var71];
					{
						{
							double[] var76 = m[(samples - st2[(i$var71 - 1)])];
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var76[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var77[((i$var71 - 1) / 1)] = cv$sampleAccumulator;
				logProbability$sample79[((i$var71 - 1) / 1)] = cv$sampleProbability;
				boolean cv$guard$st2 = false;
				{
					for(int index$i$2_1 = 1; index$i$2_1 < samples; index$i$2_1 += 1) {
						if((i$var71 == (indirection[((index$i$2_1 - 1) / 1)][index$i$2_1] / index$i$2_1))) {
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
			if(fixedFlag$sample79)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample79 = ((fixedFlag$sample79 && fixedFlag$sample28) && fixedFlag$sample54);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample79[((i$var71 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var77[((i$var71 - 1) / 1)] = cv$rvAccumulator;
				boolean cv$guard$st2 = false;
				{
					for(int index$i$3_1 = 1; index$i$3_1 < samples; index$i$3_1 += 1) {
						if((i$var71 == (indirection[((index$i$3_1 - 1) / 1)][index$i$3_1] / index$i$3_1))) {
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
			if(fixedFlag$sample79)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample28(int var27, int threadID$cv$var27, Rng RNG$) {
		if(true) {
			double[] cv$targetLocal = m[var27];
			double[] cv$countLocal = cv$var28$countGlobal[threadID$cv$var27];
			int cv$arrayLength = states;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						if((var27 == 0)) {
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
						for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
							if((var27 == (samples - st2[(i$var71 - 1)]))) {
								{
									{
										{
											{
												{
													cv$countLocal[st[i$var71]] = (cv$countLocal[st[i$var71]] + 1.0);
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
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, states);
		}
	}

	private final void sample45(int var43, int threadID$cv$var43, Rng RNG$) {
		if(true) {
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						for(int j = 0; j < samples; j += 1) {
							if((var43 == (samples - st2[j]))) {
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
			double var44 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
			{
				{
					bias[var43] = var44;
				}
			}
		}
	}

	private final void sample54() {
		if(true) {
			int cv$numNumStates = 0;
			{
				cv$numNumStates = Math.max(cv$numNumStates, states);
			}
			double[] cv$stateProbabilityLocal = cv$var53$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				int var53 = cv$currentValue;
				{
					{
						st[0] = cv$currentValue;
					}
				}
				{
					if((0 == 0)) {
						{
							st2[0] = (samples - st[0]);
						}
					}
				}
				{
					for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
						if((0 == (indirection[((i$var71 - 1) / 1)][i$var71] / i$var71))) {
							{
								st2[(indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)] = (samples - st[(indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)]);
							}
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] cv$temp$0$var51;
					{
						double[] var51 = m[0];
						cv$temp$0$var51 = var51;
					}
					int cv$temp$1$$var590;
					{
						int $var590 = states;
						cv$temp$1$$var590 = $var590;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var590))?Math.log(cv$temp$0$var51[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							int traceTempVariable$var58$4_1 = cv$currentValue;
							if((0 == 0)) {
								int traceTempVariable$var74$4_2 = (samples - traceTempVariable$var58$4_1);
								for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
									if((0 == (i$var71 - 1))) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double[] cv$temp$2$var76;
															{
																double[] var76 = m[(samples - traceTempVariable$var74$4_2)];
																cv$temp$2$var76 = var76;
															}
															int cv$temp$3$$var608;
															{
																int $var608 = states;
																cv$temp$3$$var608 = $var608;
															}
															if(((Math.log(1.0) + (((0.0 <= st[i$var71]) && (st[i$var71] < cv$temp$3$$var608))?Math.log(cv$temp$2$var76[st[i$var71]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var71]) && (st[i$var71] < cv$temp$3$$var608))?Math.log(cv$temp$2$var76[st[i$var71]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var71]) && (st[i$var71] < cv$temp$3$$var608))?Math.log(cv$temp$2$var76[st[i$var71]]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var71]) && (st[i$var71] < cv$temp$3$$var608))?Math.log(cv$temp$2$var76[st[i$var71]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var71]) && (st[i$var71] < cv$temp$3$$var608))?Math.log(cv$temp$2$var76[st[i$var71]]):Double.NEGATIVE_INFINITY)));
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
							int traceTempVariable$var100$5_1 = cv$currentValue;
							for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
								if((0 == (indirection[((i$var71 - 1) / 1)][i$var71] / i$var71))) {
									int traceTempVariable$var74$5_3 = (samples - traceTempVariable$var100$5_1);
									for(int index$i$5_4 = 1; index$i$5_4 < samples; index$i$5_4 += 1) {
										if(((indirection[((i$var71 - 1) / 1)][i$var71] / i$var71) == (index$i$5_4 - 1))) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													{
														{
															{
																double[] cv$temp$4$var76;
																{
																	double[] var76 = m[(samples - traceTempVariable$var74$5_3)];
																	cv$temp$4$var76 = var76;
																}
																int cv$temp$5$$var609;
																{
																	int $var609 = states;
																	cv$temp$5$$var609 = $var609;
																}
																if(((Math.log(1.0) + (((0.0 <= st[index$i$5_4]) && (st[index$i$5_4] < cv$temp$5$$var609))?Math.log(cv$temp$4$var76[st[index$i$5_4]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[index$i$5_4]) && (st[index$i$5_4] < cv$temp$5$$var609))?Math.log(cv$temp$4$var76[st[index$i$5_4]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[index$i$5_4]) && (st[index$i$5_4] < cv$temp$5$$var609))?Math.log(cv$temp$4$var76[st[index$i$5_4]]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[index$i$5_4]) && (st[index$i$5_4] < cv$temp$5$$var609))?Math.log(cv$temp$4$var76[st[index$i$5_4]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[index$i$5_4]) && (st[index$i$5_4] < cv$temp$5$$var609))?Math.log(cv$temp$4$var76[st[index$i$5_4]]):Double.NEGATIVE_INFINITY)));
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
							int traceTempVariable$var58$10_1 = cv$currentValue;
							if((0 == 0)) {
								int traceTempVariable$var114$10_2 = (samples - traceTempVariable$var58$10_1);
								for(int j = 0; j < samples; j += 1) {
									if((0 == j)) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$6$var116;
															{
																double var116 = bias[(samples - traceTempVariable$var114$10_2)];
																cv$temp$6$var116 = var116;
															}
															if(((Math.log(1.0) + Math.log((flips[j]?cv$temp$6$var116:(1.0 - cv$temp$6$var116)))) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((flips[j]?cv$temp$6$var116:(1.0 - cv$temp$6$var116)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((flips[j]?cv$temp$6$var116:(1.0 - cv$temp$6$var116))));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((flips[j]?cv$temp$6$var116:(1.0 - cv$temp$6$var116)))))) + 1)) + (Math.log(1.0) + Math.log((flips[j]?cv$temp$6$var116:(1.0 - cv$temp$6$var116)))));
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
							int traceTempVariable$var100$11_1 = cv$currentValue;
							for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
								if((0 == (indirection[((i$var71 - 1) / 1)][i$var71] / i$var71))) {
									int traceTempVariable$var114$11_3 = (samples - traceTempVariable$var100$11_1);
									for(int j = 0; j < samples; j += 1) {
										if(((indirection[((i$var71 - 1) / 1)][i$var71] / i$var71) == j)) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													{
														{
															{
																double cv$temp$7$var116;
																{
																	double var116 = bias[(samples - traceTempVariable$var114$11_3)];
																	cv$temp$7$var116 = var116;
																}
																if(((Math.log(1.0) + Math.log((flips[j]?cv$temp$7$var116:(1.0 - cv$temp$7$var116)))) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((flips[j]?cv$temp$7$var116:(1.0 - cv$temp$7$var116)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((flips[j]?cv$temp$7$var116:(1.0 - cv$temp$7$var116))));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((flips[j]?cv$temp$7$var116:(1.0 - cv$temp$7$var116)))))) + 1)) + (Math.log(1.0) + Math.log((flips[j]?cv$temp$7$var116:(1.0 - cv$temp$7$var116)))));
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
				for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
					double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
					if((cv$lseMax < cv$lseElementValue))
						cv$lseMax = cv$lseElementValue;
				}
				if((cv$lseMax == Double.NEGATIVE_INFINITY))
					cv$logSum = Double.NEGATIVE_INFINITY;
				else {
					double cv$lseSum = 0.0;
					for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
						cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
					cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
				}
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numNumStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
			int var53 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates);
			{
				{
					st[0] = var53;
				}
			}
			{
				if((0 == 0)) {
					{
						st2[0] = (samples - st[0]);
					}
				}
			}
			{
				for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
					if((0 == (indirection[((i$var71 - 1) / 1)][i$var71] / i$var71))) {
						{
							st2[(indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)] = (samples - st[(indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)]);
						}
					}
				}
			}
		}
	}

	private final void sample79(int i$var71) {
		if(true) {
			int cv$numNumStates = 0;
			{
				cv$numNumStates = Math.max(cv$numNumStates, states);
			}
			double[] cv$stateProbabilityLocal = cv$var78$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				int var78 = cv$currentValue;
				{
					{
						st[i$var71] = cv$currentValue;
					}
				}
				{
					for(int index$i$2_1 = 1; index$i$2_1 < samples; index$i$2_1 += 1) {
						if((i$var71 == (indirection[((index$i$2_1 - 1) / 1)][index$i$2_1] / index$i$2_1))) {
							{
								st2[(indirection[((index$i$2_1 - 1) / 1)][index$i$2_1] / index$i$2_1)] = (samples - st[(indirection[((index$i$2_1 - 1) / 1)][index$i$2_1] / index$i$2_1)]);
							}
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] cv$temp$0$var76;
					{
						double[] var76 = m[(samples - st2[(i$var71 - 1)])];
						cv$temp$0$var76 = var76;
					}
					int cv$temp$1$$var668;
					{
						int $var668 = states;
						cv$temp$1$$var668 = $var668;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var668))?Math.log(cv$temp$0$var76[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							int traceTempVariable$var100$3_1 = cv$currentValue;
							for(int index$i$3_2 = 1; index$i$3_2 < samples; index$i$3_2 += 1) {
								if((i$var71 == (indirection[((index$i$3_2 - 1) / 1)][index$i$3_2] / index$i$3_2))) {
									int traceTempVariable$var74$3_3 = (samples - traceTempVariable$var100$3_1);
									for(int index$i$3_4 = 1; index$i$3_4 < samples; index$i$3_4 += 1) {
										if(((indirection[((index$i$3_2 - 1) / 1)][index$i$3_2] / index$i$3_2) == (index$i$3_4 - 1))) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													{
														{
															{
																double[] cv$temp$2$var76;
																{
																	double[] var76 = m[(samples - traceTempVariable$var74$3_3)];
																	cv$temp$2$var76 = var76;
																}
																int cv$temp$3$$var683;
																{
																	int $var683 = states;
																	cv$temp$3$$var683 = $var683;
																}
																if(((Math.log(1.0) + (((0.0 <= st[index$i$3_4]) && (st[index$i$3_4] < cv$temp$3$$var683))?Math.log(cv$temp$2$var76[st[index$i$3_4]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[index$i$3_4]) && (st[index$i$3_4] < cv$temp$3$$var683))?Math.log(cv$temp$2$var76[st[index$i$3_4]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[index$i$3_4]) && (st[index$i$3_4] < cv$temp$3$$var683))?Math.log(cv$temp$2$var76[st[index$i$3_4]]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[index$i$3_4]) && (st[index$i$3_4] < cv$temp$3$$var683))?Math.log(cv$temp$2$var76[st[index$i$3_4]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[index$i$3_4]) && (st[index$i$3_4] < cv$temp$3$$var683))?Math.log(cv$temp$2$var76[st[index$i$3_4]]):Double.NEGATIVE_INFINITY)));
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
							int traceTempVariable$var100$6_1 = cv$currentValue;
							for(int index$i$6_2 = 1; index$i$6_2 < samples; index$i$6_2 += 1) {
								if((i$var71 == (indirection[((index$i$6_2 - 1) / 1)][index$i$6_2] / index$i$6_2))) {
									int traceTempVariable$var114$6_3 = (samples - traceTempVariable$var100$6_1);
									for(int j = 0; j < samples; j += 1) {
										if(((indirection[((index$i$6_2 - 1) / 1)][index$i$6_2] / index$i$6_2) == j)) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													{
														{
															{
																double cv$temp$4$var116;
																{
																	double var116 = bias[(samples - traceTempVariable$var114$6_3)];
																	cv$temp$4$var116 = var116;
																}
																if(((Math.log(1.0) + Math.log((flips[j]?cv$temp$4$var116:(1.0 - cv$temp$4$var116)))) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((flips[j]?cv$temp$4$var116:(1.0 - cv$temp$4$var116)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((flips[j]?cv$temp$4$var116:(1.0 - cv$temp$4$var116))));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((flips[j]?cv$temp$4$var116:(1.0 - cv$temp$4$var116)))))) + 1)) + (Math.log(1.0) + Math.log((flips[j]?cv$temp$4$var116:(1.0 - cv$temp$4$var116)))));
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
				for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
					double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
					if((cv$lseMax < cv$lseElementValue))
						cv$lseMax = cv$lseElementValue;
				}
				if((cv$lseMax == Double.NEGATIVE_INFINITY))
					cv$logSum = Double.NEGATIVE_INFINITY;
				else {
					double cv$lseSum = 0.0;
					for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
						cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
					cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
				}
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numNumStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
			int var78 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates);
			{
				{
					st[i$var71] = var78;
				}
			}
			{
				for(int index$i$10_1 = 1; index$i$10_1 < samples; index$i$10_1 += 1) {
					if((i$var71 == (indirection[((index$i$10_1 - 1) / 1)][index$i$10_1] / index$i$10_1))) {
						{
							st2[(indirection[((index$i$10_1 - 1) / 1)][index$i$10_1] / index$i$10_1)] = (samples - st[(indirection[((index$i$10_1 - 1) / 1)][index$i$10_1] / index$i$10_1)]);
						}
					}
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {
		{
			{
				int cv$threadCount = threadCount();
				cv$var28$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var28$countGlobal[cv$index] = new double[2];
			}
		}
		{
			int cv$var29$max = 2;
			cv$var53$stateProbabilityGlobal = new double[cv$var29$max];
		}
		{
			int cv$var29$max = 2;
			cv$var78$stateProbabilityGlobal = new double[cv$var29$max];
		}
	}

	@Override
	public final void allocator() {
		{
			v = new double[2];
		}
		if(!fixedFlag$sample28) {
			{
				m = new double[2][];
				for(int var27 = 0; var27 < 2; var27 += 1)
					m[var27] = new double[2];
			}
		}
		if(!fixedFlag$sample45) {
			{
				bias = new double[2];
			}
		}
		if((!fixedFlag$sample54 || !fixedFlag$sample79)) {
			{
				st = new int[length$flipsMeasured];
			}
		}
		{
			st2 = new int[length$flipsMeasured];
		}
		{
			indirection = new int[((((length$flipsMeasured - 1) - 1) / 1) + 1)][];
			for(int i$var71 = 1; i$var71 < length$flipsMeasured; i$var71 += 1)
				indirection[((i$var71 - 1) / 1)] = new int[(i$var71 + 1)];
		}
		{
			flips = new boolean[length$flipsMeasured];
		}
		{
			logProbability$var77 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample79 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var117 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample119 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = m[var27];
						if(!fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, v, states, var28);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!fixedFlag$sample45)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample54)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		if(!fixedFlag$sample54)
			st2[0] = (samples - st[0]);
		for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
			if(!fixedFlag$sample79)
				st[i$var71] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var71 - 1)])], states);
			if(!(fixedFlag$sample54 && fixedFlag$sample79))
				st2[(indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)] = (samples - st[(indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)]);
		}
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[(samples - st2[j])]);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = m[var27];
						if(!fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, v, states, var28);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!fixedFlag$sample45)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample54)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		if(!fixedFlag$sample54)
			st2[0] = (samples - st[0]);
		for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
			if(!fixedFlag$sample79)
				st[i$var71] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var71 - 1)])], states);
			if(!(fixedFlag$sample54 && fixedFlag$sample79))
				st2[(indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)] = (samples - st[(indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = m[var27];
						if(!fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, v, states, var28);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!fixedFlag$sample45)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample54)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		if(!fixedFlag$sample54)
			st2[0] = (samples - st[0]);
		for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
			if(!fixedFlag$sample79)
				st[i$var71] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var71 - 1)])], states);
			if(!(fixedFlag$sample54 && fixedFlag$sample79))
				st2[(indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)] = (samples - st[(indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)]);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
							if(!fixedFlag$sample28)
								sample28(var27, threadID$var27, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
							if(!fixedFlag$sample45)
								sample45(var43, threadID$var43, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample54)
				sample54();
			for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
				if(!fixedFlag$sample79)
					sample79(i$var71);
			}
		} else {
			for(int i$var71 = (samples - ((((samples - 1) - 1) % 1) + 1)); i$var71 >= ((1 - 1) + 1); i$var71 -= 1) {
				if(!fixedFlag$sample79)
					sample79(i$var71);
			}
			if(!fixedFlag$sample54)
				sample54();
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
							if(!fixedFlag$sample45)
								sample45(var43, threadID$var43, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
							if(!fixedFlag$sample28)
								sample28(var27, threadID$var27, RNG$1);
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
			(int forStart$i$var13, int forEnd$i$var13, int threadID$i$var13, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var13 = forStart$i$var13; i$var13 < forEnd$i$var13; i$var13 += 1)
						v[i$var13] = 0.1;
			}
		);
		samples = length$flipsMeasured;
		for(int i$var71 = 1; i$var71 < length$flipsMeasured; i$var71 += 1) {
			int i$var71$1 = i$var71;
			parallelFor(RNG$, 0, (i$var71$1 + 1), 1,
				(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int k = forStart$k; k < forEnd$k; k += 1)
							indirection[((i$var71$1 - 1) / 1)][k] = (k * i$var71$1);
				}
			);
		}
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var16 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var28 = 0.0;
		logProbability$var32 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$var44 = 0.0;
		logProbability$var52 = 0.0;
		logProbability$st = 0.0;
		logProbability$st2 = 0.0;
		if(!fixedProbFlag$sample54)
			logProbability$var53 = 0.0;
		for(int i$var71 = 1; i$var71 < samples; i$var71 += 1)
			logProbability$var77[((i$var71 - 1) / 1)] = 0.0;
		if(!fixedProbFlag$sample79) {
			for(int i$var71 = 1; i$var71 < samples; i$var71 += 1)
				logProbability$sample79[((i$var71 - 1) / 1)] = 0.0;
		}
		for(int j = 0; j < samples; j += 1)
			logProbability$var117[((j - 0) / 1)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample119) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample119[((j - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		if(fixedFlag$sample54)
			logProbabilityValue$sample54();
		if(fixedFlag$sample79)
			logProbabilityValue$sample79();
		logProbabilityValue$sample119();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample54();
		logProbabilityValue$sample79();
		logProbabilityValue$sample119();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample54();
		logProbabilityValue$sample79();
		logProbabilityValue$sample119();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = m[var27];
						if(!fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, v, states, var28);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!fixedFlag$sample45)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample54)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		if(!fixedFlag$sample54)
			st2[0] = (samples - st[0]);
		for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
			if(!fixedFlag$sample79)
				st[i$var71] = DistributionSampling.sampleCategorical(RNG$, m[(samples - st2[(i$var71 - 1)])], states);
			if(!(fixedFlag$sample54 && fixedFlag$sample79))
				st2[(indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)] = (samples - st[(indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)]);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		boolean[] cv$source1 = flipsMeasured;
		boolean[] cv$target1 = flips;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		if(fixedFlag$sample54)
			st2[0] = (samples - st[0]);
		for(int i$var71 = 1; i$var71 < samples; i$var71 += 1) {
			if((fixedFlag$sample54 && fixedFlag$sample79))
				st2[(indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)] = (samples - st[(indirection[((i$var71 - 1) / 1)][i$var71] / i$var71)]);
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