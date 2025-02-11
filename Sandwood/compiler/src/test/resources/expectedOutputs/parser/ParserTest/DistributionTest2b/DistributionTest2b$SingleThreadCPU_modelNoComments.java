package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DistributionTest2b$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements DistributionTest2b$CoreInterface {
	private double[] cv$var13$stateProbabilityGlobal;
	private double[] cv$var19$stateProbabilityGlobal;
	private double[] cv$var25$stateProbabilityGlobal;
	private double[] cv$var9$stateProbabilityGlobal;
	private double[] distribution$sample12;
	private double[] distribution$sample16;
	private double[][] distribution$sample22;
	private boolean fixedFlag$sample12 = false;
	private boolean fixedFlag$sample16 = false;
	private boolean fixedFlag$sample22 = false;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedProbFlag$sample12 = false;
	private boolean fixedProbFlag$sample16 = false;
	private boolean fixedProbFlag$sample22 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample35 = false;
	private int length$value;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$c;
	private double[] logProbability$sample22;
	private double[] logProbability$sample35;
	private double logProbability$v;
	private double logProbability$v1;
	private double logProbability$v2;
	private double logProbability$v3;
	private double logProbability$var12;
	private double logProbability$var13;
	private double[] logProbability$var18;
	private double[] logProbability$var31;
	private boolean setFlag$v = false;
	private boolean setFlag$v2 = false;
	private boolean setFlag$v3 = false;
	private int size;
	private boolean system$gibbsForward = true;
	private boolean[] v;
	private int v1;
	private int[] v2;
	private int[] v3;
	private boolean[] value;
	private double[] weightings;

	public DistributionTest2b$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$fixedFlag$sample12() {
		return fixedFlag$sample12;
	}

	@Override
	public final void set$fixedFlag$sample12(boolean cv$value) {
		fixedFlag$sample12 = cv$value;
		fixedProbFlag$sample12 = (fixedFlag$sample12 && fixedProbFlag$sample12);
		fixedProbFlag$sample35 = (fixedFlag$sample12 && fixedProbFlag$sample35);
	}

	@Override
	public final boolean get$fixedFlag$sample16() {
		return fixedFlag$sample16;
	}

	@Override
	public final void set$fixedFlag$sample16(boolean cv$value) {
		fixedFlag$sample16 = cv$value;
		fixedProbFlag$sample16 = (fixedFlag$sample16 && fixedProbFlag$sample16);
		fixedProbFlag$sample35 = (fixedFlag$sample16 && fixedProbFlag$sample35);
	}

	@Override
	public final boolean get$fixedFlag$sample22() {
		return fixedFlag$sample22;
	}

	@Override
	public final void set$fixedFlag$sample22(boolean cv$value) {
		fixedFlag$sample22 = cv$value;
		fixedProbFlag$sample22 = (fixedFlag$sample22 && fixedProbFlag$sample22);
		fixedProbFlag$sample35 = (fixedFlag$sample22 && fixedProbFlag$sample35);
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		fixedFlag$sample28 = cv$value;
		fixedProbFlag$sample28 = (fixedFlag$sample28 && fixedProbFlag$sample28);
		fixedProbFlag$sample35 = (fixedFlag$sample28 && fixedProbFlag$sample35);
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		fixedFlag$sample35 = cv$value;
		fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedProbFlag$sample35);
	}

	@Override
	public final int get$length$value() {
		return length$value;
	}

	@Override
	public final void set$length$value(int cv$value) {
		length$value = cv$value;
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
	public final double get$logProbability$c() {
		return logProbability$c;
	}

	@Override
	public final double get$logProbability$v() {
		return logProbability$v;
	}

	@Override
	public final double get$logProbability$v1() {
		return logProbability$v1;
	}

	@Override
	public final double get$logProbability$v2() {
		return logProbability$v2;
	}

	@Override
	public final double get$logProbability$v3() {
		return logProbability$v3;
	}

	@Override
	public final int get$size() {
		return size;
	}

	@Override
	public final boolean[] get$v() {
		return v;
	}

	@Override
	public final void set$v(boolean[] cv$value) {
		v = cv$value;
		setFlag$v = true;
		fixedProbFlag$sample35 = false;
	}

	@Override
	public final int get$v1() {
		return v1;
	}

	@Override
	public final void set$v1(int cv$value) {
		v1 = cv$value;
		fixedProbFlag$sample12 = false;
		fixedProbFlag$sample35 = false;
	}

	@Override
	public final int[] get$v2() {
		return v2;
	}

	@Override
	public final void set$v2(int[] cv$value) {
		v2 = cv$value;
		setFlag$v2 = true;
		fixedProbFlag$sample16 = false;
		fixedProbFlag$sample22 = false;
		fixedProbFlag$sample35 = false;
	}

	@Override
	public final int[] get$v3() {
		return v3;
	}

	@Override
	public final void set$v3(int[] cv$value) {
		v3 = cv$value;
		setFlag$v3 = true;
	}

	@Override
	public final boolean[] get$value() {
		return value;
	}

	@Override
	public final void set$value(boolean[] cv$value) {
		value = cv$value;
	}

	@Override
	public final double[] get$weightings() {
		return weightings;
	}

	@Override
	public final void set$weightings(double[] cv$value) {
		weightings = cv$value;
	}

	private final void logProbabilityDistribution$sample12() {
		if(!fixedProbFlag$sample12) {
			if(fixedFlag$sample12) {
				double cv$accumulator = 0.0;
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = v1;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$c = (logProbability$c + cv$sampleAccumulator);
				logProbability$v1 = cv$sampleProbability;
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample12)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample12 = fixedFlag$sample12;
			}
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$v1;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$c = (logProbability$c + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample16() {
		if(!fixedProbFlag$sample16) {
			if(fixedFlag$sample16) {
				double cv$accumulator = 0.0;
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = v2[0];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var12 = cv$sampleAccumulator;
				logProbability$var13 = cv$sampleProbability;
				if(fixedFlag$sample16)
					logProbability$v2 = (logProbability$v2 + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample16)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample16 = fixedFlag$sample16;
			}
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var13;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var12 = cv$rvAccumulator;
			if(fixedFlag$sample16)
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample22() {
		if(!fixedProbFlag$sample22) {
			if(fixedFlag$sample22) {
				double cv$accumulator = 0.0;
				for(int i = 1; i < size; i += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$i$1 = i;
					{
						int cv$sampleValue = v2[i];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var18[((i - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample22[((i - 1) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample22)
					logProbability$v2 = (logProbability$v2 + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample22)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample22 = fixedFlag$sample22;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i = 1; i < size; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample22[((i - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var18[((i - 1) / 1)] = cv$rvAccumulator;
			}
			if(fixedFlag$sample22)
				logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample35() {
		if(!fixedProbFlag$sample35) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = v[j];
					if(fixedFlag$sample12) {
						if(fixedFlag$sample16) {
							if((0 == j)) {
								{
									double var30 = ((1.0 * v1) / (v2[j] + v3[((j - 0) / 1)]));
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var30));
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
						} else {
							if(true) {
								for(int index$sample16$8 = 0; index$sample16$8 < weightings.length; index$sample16$8 += 1) {
									int distributionTempVariable$var13$10 = index$sample16$8;
									double cv$probabilitySample16Value9 = (1.0 * distribution$sample16[index$sample16$8]);
									int traceTempVariable$var28$11_1 = distributionTempVariable$var13$10;
									if((0 == j)) {
										{
											double var30 = ((1.0 * v1) / (traceTempVariable$var28$11_1 + v3[((j - 0) / 1)]));
											double cv$weightedProbability = (Math.log(cv$probabilitySample16Value9) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var30));
											if((cv$weightedProbability < cv$distributionAccumulator))
												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
											else {
												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
													cv$distributionAccumulator = cv$weightedProbability;
												else
													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
											}
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample16Value9);
										}
									}
								}
							}
						}
					} else {
						if(true) {
							for(int index$sample12$3 = 0; index$sample12$3 < weightings.length; index$sample12$3 += 1) {
								int distributionTempVariable$v1$5 = index$sample12$3;
								double cv$probabilitySample12Value4 = (1.0 * distribution$sample12[index$sample12$3]);
								if(fixedFlag$sample16) {
									if((0 == j)) {
										{
											double var30 = ((1.0 * distributionTempVariable$v1$5) / (v2[j] + v3[((j - 0) / 1)]));
											double cv$weightedProbability = (Math.log(cv$probabilitySample12Value4) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var30));
											if((cv$weightedProbability < cv$distributionAccumulator))
												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
											else {
												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
													cv$distributionAccumulator = cv$weightedProbability;
												else
													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
											}
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample12Value4);
										}
									}
								} else {
									if(true) {
										for(int index$sample16$13 = 0; index$sample16$13 < weightings.length; index$sample16$13 += 1) {
											int distributionTempVariable$var13$15 = index$sample16$13;
											double cv$probabilitySample16Value14 = (cv$probabilitySample12Value4 * distribution$sample16[index$sample16$13]);
											int traceTempVariable$var28$16_1 = distributionTempVariable$var13$15;
											if((0 == j)) {
												{
													double var30 = ((1.0 * distributionTempVariable$v1$5) / (traceTempVariable$var28$16_1 + v3[((j - 0) / 1)]));
													double cv$weightedProbability = (Math.log(cv$probabilitySample16Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var30));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample16Value14);
												}
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample12) {
						if(fixedFlag$sample22) {
							for(int i = 1; i < size; i += 1) {
								if((i == j)) {
									{
										double var30 = ((1.0 * v1) / (v2[j] + v3[((j - 0) / 1)]));
										double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var30));
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
							for(int i = 1; i < size; i += 1) {
								if(true) {
									for(int index$sample22$28 = 0; index$sample22$28 < weightings.length; index$sample22$28 += 1) {
										int distributionTempVariable$var19$30 = index$sample22$28;
										double cv$probabilitySample22Value29 = (1.0 * distribution$sample22[((i - 1) / 1)][index$sample22$28]);
										int traceTempVariable$var28$31_1 = distributionTempVariable$var19$30;
										if((i == j)) {
											{
												double var30 = ((1.0 * v1) / (traceTempVariable$var28$31_1 + v3[((j - 0) / 1)]));
												double cv$weightedProbability = (Math.log(cv$probabilitySample22Value29) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var30));
												if((cv$weightedProbability < cv$distributionAccumulator))
													cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
												else {
													if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
														cv$distributionAccumulator = cv$weightedProbability;
													else
														cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
												}
												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample22Value29);
											}
										}
									}
								}
							}
						}
					} else {
						if(true) {
							for(int index$sample12$22 = 0; index$sample12$22 < weightings.length; index$sample12$22 += 1) {
								int distributionTempVariable$v1$24 = index$sample12$22;
								double cv$probabilitySample12Value23 = (1.0 * distribution$sample12[index$sample12$22]);
								if(fixedFlag$sample22) {
									for(int i = 1; i < size; i += 1) {
										if((i == j)) {
											{
												double var30 = ((1.0 * distributionTempVariable$v1$24) / (v2[j] + v3[((j - 0) / 1)]));
												double cv$weightedProbability = (Math.log(cv$probabilitySample12Value23) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var30));
												if((cv$weightedProbability < cv$distributionAccumulator))
													cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
												else {
													if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
														cv$distributionAccumulator = cv$weightedProbability;
													else
														cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
												}
												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample12Value23);
											}
										}
									}
								} else {
									for(int i = 1; i < size; i += 1) {
										if(true) {
											for(int index$sample22$34 = 0; index$sample22$34 < weightings.length; index$sample22$34 += 1) {
												int distributionTempVariable$var19$36 = index$sample22$34;
												double cv$probabilitySample22Value35 = (cv$probabilitySample12Value23 * distribution$sample22[((i - 1) / 1)][index$sample22$34]);
												int traceTempVariable$var28$37_1 = distributionTempVariable$var19$36;
												if((i == j)) {
													{
														double var30 = ((1.0 * distributionTempVariable$v1$24) / (traceTempVariable$var28$37_1 + v3[((j - 0) / 1)]));
														double cv$weightedProbability = (Math.log(cv$probabilitySample22Value35) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var30));
														if((cv$weightedProbability < cv$distributionAccumulator))
															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
														else {
															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																cv$distributionAccumulator = cv$weightedProbability;
															else
																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
														}
														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample22Value35);
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var31[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample35[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample35 = ((((fixedFlag$sample35 && fixedFlag$sample12) && fixedFlag$sample16) && fixedFlag$sample22) && fixedFlag$sample28);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample35[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var31[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample12() {
		if(!fixedProbFlag$sample12) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = v1;
				{
					{
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$c = (logProbability$c + cv$sampleAccumulator);
			logProbability$v1 = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample12 = fixedFlag$sample12;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$v1;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$c = (logProbability$c + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample16() {
		if(!fixedProbFlag$sample16) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = v2[0];
				{
					{
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var12 = cv$sampleAccumulator;
			logProbability$var13 = cv$sampleProbability;
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample16 = fixedFlag$sample16;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var13;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var12 = cv$rvAccumulator;
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample22() {
		if(!fixedProbFlag$sample22) {
			double cv$accumulator = 0.0;
			for(int i = 1; i < size; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$i$1 = i;
				{
					int cv$sampleValue = v2[i];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var18[((i - 1) / 1)] = cv$sampleAccumulator;
				logProbability$sample22[((i - 1) / 1)] = cv$sampleProbability;
			}
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample22 = fixedFlag$sample22;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 1; i < size; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample22[((i - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var18[((i - 1) / 1)] = cv$rvAccumulator;
			}
			logProbability$v2 = (logProbability$v2 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample22)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = v3[((j - 0) / 1)];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weightings.length))?Math.log(weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$c = (logProbability$c + cv$sampleAccumulator);
			logProbability$v3 = cv$sampleAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$v3;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$c = (logProbability$c + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = v[j];
					{
						{
							double var30 = ((1.0 * v1) / (v2[j] + v3[((j - 0) / 1)]));
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var30));
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
				logProbability$var31[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample35[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample35 = ((((fixedFlag$sample35 && fixedFlag$sample12) && fixedFlag$sample16) && fixedFlag$sample22) && fixedFlag$sample28);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < size; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample35[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var31[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$v = (logProbability$v + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample12() {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, weightings.length);
		}
		double[] cv$stateProbabilityLocal = cv$var9$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$weightings;
				{
					cv$temp$0$weightings = weightings;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$weightings.length))?Math.log(cv$temp$0$weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						for(int j = 0; j < size; j += 1) {
							int traceTempVariable$v1$1_2 = cv$currentValue;
							{
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								{
									if(fixedFlag$sample16) {
										if((0 == j)) {
											{
												{
													double cv$temp$1$var30;
													{
														double var30 = ((1.0 * traceTempVariable$v1$1_2) / (v2[j] + v3[((j - 0) / 1)]));
														cv$temp$1$var30 = var30;
													}
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var30)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var30));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var30)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var30)));
													}
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
												}
											}
										}
									} else {
										if(true) {
											for(int index$sample16$4 = 0; index$sample16$4 < weightings.length; index$sample16$4 += 1) {
												int distributionTempVariable$var13$6 = index$sample16$4;
												double cv$probabilitySample16Value5 = (1.0 * distribution$sample16[index$sample16$4]);
												int traceTempVariable$var28$7_1 = distributionTempVariable$var13$6;
												if((0 == j)) {
													{
														{
															double cv$temp$2$var30;
															{
																double var30 = ((1.0 * traceTempVariable$v1$1_2) / (traceTempVariable$var28$7_1 + v3[((j - 0) / 1)]));
																cv$temp$2$var30 = var30;
															}
															if(((Math.log(cv$probabilitySample16Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var30)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample16Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample16Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var30));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample16Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var30)))) + 1)) + (Math.log(cv$probabilitySample16Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var30)));
															}
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample16Value5);
														}
													}
												}
											}
										}
									}
									if(fixedFlag$sample22) {
										for(int i = 1; i < size; i += 1) {
											if((i == j)) {
												{
													{
														double cv$temp$3$var30;
														{
															double var30 = ((1.0 * traceTempVariable$v1$1_2) / (v2[j] + v3[((j - 0) / 1)]));
															cv$temp$3$var30 = var30;
														}
														if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var30)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var30));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var30)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var30)));
														}
														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
													}
												}
											}
										}
									} else {
										for(int i = 1; i < size; i += 1) {
											if(true) {
												for(int index$sample22$12 = 0; index$sample22$12 < weightings.length; index$sample22$12 += 1) {
													int distributionTempVariable$var19$14 = index$sample22$12;
													double cv$probabilitySample22Value13 = (1.0 * distribution$sample22[((i - 1) / 1)][index$sample22$12]);
													int traceTempVariable$var28$15_1 = distributionTempVariable$var19$14;
													if((i == j)) {
														{
															{
																double cv$temp$4$var30;
																{
																	double var30 = ((1.0 * traceTempVariable$v1$1_2) / (traceTempVariable$var28$15_1 + v3[((j - 0) / 1)]));
																	cv$temp$4$var30 = var30;
																}
																if(((Math.log(cv$probabilitySample22Value13) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var30)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample22Value13) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample22Value13) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var30));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample22Value13) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var30)))) + 1)) + (Math.log(cv$probabilitySample22Value13) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var30)));
																}
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample22Value13);
															}
														}
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
		double[] cv$localProbability = distribution$sample12;
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

	private final void sample16() {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, weightings.length);
		}
		double[] cv$stateProbabilityLocal = cv$var13$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$weightings;
				{
					cv$temp$0$weightings = weightings;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$weightings.length))?Math.log(cv$temp$0$weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var28$1_1 = cv$currentValue;
						for(int j = 0; j < size; j += 1) {
							if((0 == j)) {
								{
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										if(fixedFlag$sample12) {
											{
												{
													double cv$temp$1$var30;
													{
														double var30 = ((1.0 * v1) / (traceTempVariable$var28$1_1 + v3[((j - 0) / 1)]));
														cv$temp$1$var30 = var30;
													}
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var30)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var30));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var30)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var30)));
													}
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
												}
											}
										} else {
											if(true) {
												for(int index$sample12$4 = 0; index$sample12$4 < weightings.length; index$sample12$4 += 1) {
													int distributionTempVariable$v1$6 = index$sample12$4;
													double cv$probabilitySample12Value5 = (1.0 * distribution$sample12[index$sample12$4]);
													{
														{
															double cv$temp$2$var30;
															{
																double var30 = ((1.0 * distributionTempVariable$v1$6) / (traceTempVariable$var28$1_1 + v3[((j - 0) / 1)]));
																cv$temp$2$var30 = var30;
															}
															if(((Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var30)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var30));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var30)))) + 1)) + (Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var30)));
															}
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value5);
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
		double[] cv$localProbability = distribution$sample16;
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

	private final void sample22(int i) {
		int cv$noStates = 0;
		int index$i$1 = i;
		{
			cv$noStates = Math.max(cv$noStates, weightings.length);
		}
		double[] cv$stateProbabilityLocal = cv$var19$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			int index$i$2 = i;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$weightings;
				{
					cv$temp$0$weightings = weightings;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$weightings.length))?Math.log(cv$temp$0$weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var28$3_1 = cv$currentValue;
						for(int j = 0; j < size; j += 1) {
							if((i == j)) {
								{
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										if(fixedFlag$sample12) {
											{
												{
													double cv$temp$1$var30;
													{
														double var30 = ((1.0 * v1) / (traceTempVariable$var28$3_1 + v3[((j - 0) / 1)]));
														cv$temp$1$var30 = var30;
													}
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var30)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var30));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var30)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var30)));
													}
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
												}
											}
										} else {
											if(true) {
												for(int index$sample12$6 = 0; index$sample12$6 < weightings.length; index$sample12$6 += 1) {
													int distributionTempVariable$v1$8 = index$sample12$6;
													double cv$probabilitySample12Value7 = (1.0 * distribution$sample12[index$sample12$6]);
													{
														{
															double cv$temp$2$var30;
															{
																double var30 = ((1.0 * distributionTempVariable$v1$8) / (traceTempVariable$var28$3_1 + v3[((j - 0) / 1)]));
																cv$temp$2$var30 = var30;
															}
															if(((Math.log(cv$probabilitySample12Value7) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var30)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value7) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value7) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var30));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value7) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var30)))) + 1)) + (Math.log(cv$probabilitySample12Value7) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var30)));
															}
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value7);
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
		double[] cv$localProbability = distribution$sample22[((i - 1) / 1)];
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

	private final void sample28(int j) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, weightings.length);
		}
		double[] cv$stateProbabilityLocal = cv$var25$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			v3[((j - 0) / 1)] = cv$currentValue;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$weightings;
				{
					cv$temp$0$weightings = weightings;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$weightings.length))?Math.log(cv$temp$0$weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						{
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							{
								if(fixedFlag$sample12) {
									if(fixedFlag$sample16) {
										if((0 == j)) {
											{
												{
													double cv$temp$1$var30;
													{
														double var30 = ((1.0 * v1) / (v2[j] + cv$currentValue));
														cv$temp$1$var30 = var30;
													}
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var30)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var30));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var30)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$1$var30)));
													}
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
												}
											}
										}
									} else {
										if(true) {
											for(int index$sample16$9 = 0; index$sample16$9 < weightings.length; index$sample16$9 += 1) {
												int distributionTempVariable$var13$11 = index$sample16$9;
												double cv$probabilitySample16Value10 = (1.0 * distribution$sample16[index$sample16$9]);
												int traceTempVariable$var28$12_1 = distributionTempVariable$var13$11;
												if((0 == j)) {
													{
														{
															double cv$temp$2$var30;
															{
																double var30 = ((1.0 * v1) / (traceTempVariable$var28$12_1 + cv$currentValue));
																cv$temp$2$var30 = var30;
															}
															if(((Math.log(cv$probabilitySample16Value10) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var30)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample16Value10) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample16Value10) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var30));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample16Value10) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var30)))) + 1)) + (Math.log(cv$probabilitySample16Value10) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$2$var30)));
															}
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample16Value10);
														}
													}
												}
											}
										}
									}
								} else {
									if(true) {
										for(int index$sample12$4 = 0; index$sample12$4 < weightings.length; index$sample12$4 += 1) {
											int distributionTempVariable$v1$6 = index$sample12$4;
											double cv$probabilitySample12Value5 = (1.0 * distribution$sample12[index$sample12$4]);
											if(fixedFlag$sample16) {
												if((0 == j)) {
													{
														{
															double cv$temp$3$var30;
															{
																double var30 = ((1.0 * distributionTempVariable$v1$6) / (v2[j] + cv$currentValue));
																cv$temp$3$var30 = var30;
															}
															if(((Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var30)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var30));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var30)))) + 1)) + (Math.log(cv$probabilitySample12Value5) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$3$var30)));
															}
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value5);
														}
													}
												}
											} else {
												if(true) {
													for(int index$sample16$14 = 0; index$sample16$14 < weightings.length; index$sample16$14 += 1) {
														int distributionTempVariable$var13$16 = index$sample16$14;
														double cv$probabilitySample16Value15 = (cv$probabilitySample12Value5 * distribution$sample16[index$sample16$14]);
														int traceTempVariable$var28$17_1 = distributionTempVariable$var13$16;
														if((0 == j)) {
															{
																{
																	double cv$temp$4$var30;
																	{
																		double var30 = ((1.0 * distributionTempVariable$v1$6) / (traceTempVariable$var28$17_1 + cv$currentValue));
																		cv$temp$4$var30 = var30;
																	}
																	if(((Math.log(cv$probabilitySample16Value15) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var30)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample16Value15) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample16Value15) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var30));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample16Value15) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var30)))) + 1)) + (Math.log(cv$probabilitySample16Value15) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$4$var30)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample16Value15);
																}
															}
														}
													}
												}
											}
										}
									}
								}
								if(fixedFlag$sample12) {
									if(fixedFlag$sample22) {
										for(int i = 1; i < size; i += 1) {
											if((i == j)) {
												{
													{
														double cv$temp$5$var30;
														{
															double var30 = ((1.0 * v1) / (v2[j] + cv$currentValue));
															cv$temp$5$var30 = var30;
														}
														if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var30)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var30));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var30)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$5$var30)));
														}
														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
													}
												}
											}
										}
									} else {
										for(int i = 1; i < size; i += 1) {
											if(true) {
												for(int index$sample22$25 = 0; index$sample22$25 < weightings.length; index$sample22$25 += 1) {
													int distributionTempVariable$var19$27 = index$sample22$25;
													double cv$probabilitySample22Value26 = (1.0 * distribution$sample22[((i - 1) / 1)][index$sample22$25]);
													int traceTempVariable$var28$28_1 = distributionTempVariable$var19$27;
													if((i == j)) {
														{
															{
																double cv$temp$6$var30;
																{
																	double var30 = ((1.0 * v1) / (traceTempVariable$var28$28_1 + cv$currentValue));
																	cv$temp$6$var30 = var30;
																}
																if(((Math.log(cv$probabilitySample22Value26) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var30)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample22Value26) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample22Value26) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var30));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample22Value26) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var30)))) + 1)) + (Math.log(cv$probabilitySample22Value26) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$6$var30)));
																}
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample22Value26);
															}
														}
													}
												}
											}
										}
									}
								} else {
									if(true) {
										for(int index$sample12$19 = 0; index$sample12$19 < weightings.length; index$sample12$19 += 1) {
											int distributionTempVariable$v1$21 = index$sample12$19;
											double cv$probabilitySample12Value20 = (1.0 * distribution$sample12[index$sample12$19]);
											if(fixedFlag$sample22) {
												for(int i = 1; i < size; i += 1) {
													if((i == j)) {
														{
															{
																double cv$temp$7$var30;
																{
																	double var30 = ((1.0 * distributionTempVariable$v1$21) / (v2[j] + cv$currentValue));
																	cv$temp$7$var30 = var30;
																}
																if(((Math.log(cv$probabilitySample12Value20) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var30)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample12Value20) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample12Value20) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var30));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample12Value20) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var30)))) + 1)) + (Math.log(cv$probabilitySample12Value20) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$7$var30)));
																}
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample12Value20);
															}
														}
													}
												}
											} else {
												for(int i = 1; i < size; i += 1) {
													if(true) {
														for(int index$sample22$31 = 0; index$sample22$31 < weightings.length; index$sample22$31 += 1) {
															int distributionTempVariable$var19$33 = index$sample22$31;
															double cv$probabilitySample22Value32 = (cv$probabilitySample12Value20 * distribution$sample22[((i - 1) / 1)][index$sample22$31]);
															int traceTempVariable$var28$34_1 = distributionTempVariable$var19$33;
															if((i == j)) {
																{
																	{
																		double cv$temp$8$var30;
																		{
																			double var30 = ((1.0 * distributionTempVariable$v1$21) / (traceTempVariable$var28$34_1 + cv$currentValue));
																			cv$temp$8$var30 = var30;
																		}
																		if(((Math.log(cv$probabilitySample22Value32) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var30)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample22Value32) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var30)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample22Value32) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var30));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample22Value32) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var30)))) + 1)) + (Math.log(cv$probabilitySample22Value32) + DistributionSampling.logProbabilityBernoulli(v[j], cv$temp$8$var30)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample22Value32);
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
		v3[((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
	}

	@Override
	public final void allocateScratch() {
		{
			cv$var9$stateProbabilityGlobal = new double[weightings.length];
		}
		{
			cv$var13$stateProbabilityGlobal = new double[weightings.length];
		}
		{
			cv$var19$stateProbabilityGlobal = new double[weightings.length];
		}
		{
			cv$var25$stateProbabilityGlobal = new double[weightings.length];
		}
	}

	@Override
	public final void allocator() {
		if(!setFlag$v2) {
			{
				v2 = new int[length$value];
			}
		}
		if(!setFlag$v) {
			{
				v = new boolean[length$value];
			}
		}
		if(!setFlag$v3) {
			{
				v3 = new int[((((length$value - 1) - 0) / 1) + 1)];
			}
		}
		{
			distribution$sample22 = new double[((((length$value - 1) - 1) / 1) + 1)][];
			for(int i = 1; i < length$value; i += 1)
				distribution$sample22[((i - 1) / 1)] = new double[weightings.length];
		}
		{
			distribution$sample12 = new double[weightings.length];
		}
		{
			distribution$sample16 = new double[weightings.length];
		}
		{
			logProbability$var18 = new double[((((length$value - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample22 = new double[((((length$value - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var31 = new double[((((length$value - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample35 = new double[((((length$value - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample16)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		for(int i = 1; i < size; i += 1) {
			if(!fixedFlag$sample22)
				v2[i] = DistributionSampling.sampleCategorical(RNG$, weightings);
		}
		for(int j = 0; j < size; j += 1) {
			if(!fixedFlag$sample28)
				v3[((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, weightings);
			if(!fixedFlag$sample35)
				v[j] = DistributionSampling.sampleBernoulli(RNG$, ((1.0 * v1) / (v2[j] + v3[((j - 0) / 1)])));
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		double[] cv$distribution$sample12 = distribution$sample12;
		for(int index$c = 0; index$c < weightings.length; index$c += 1) {
			double cv$value = (((0.0 <= index$c) && (index$c < weightings.length))?weightings[index$c]:0.0);
			if(!(fixedFlag$sample12 && fixedFlag$sample28))
				cv$distribution$sample12[index$c] = cv$value;
		}
		double[] cv$distribution$sample16 = distribution$sample16;
		for(int index$var12 = 0; index$var12 < weightings.length; index$var12 += 1) {
			double cv$value = (((0.0 <= index$var12) && (index$var12 < weightings.length))?weightings[index$var12]:0.0);
			if(!fixedFlag$sample16)
				cv$distribution$sample16[index$var12] = cv$value;
		}
		for(int i = 1; i < size; i += 1) {
			double[] cv$distribution$sample22 = distribution$sample22[((i - 1) / 1)];
			for(int index$var18 = 0; index$var18 < weightings.length; index$var18 += 1) {
				double cv$value = (((0.0 <= index$var18) && (index$var18 < weightings.length))?weightings[index$var18]:0.0);
				if(!fixedFlag$sample22)
					cv$distribution$sample22[index$var18] = cv$value;
			}
		}
		for(int j = 0; j < size; j += 1) {
			if(!fixedFlag$sample28)
				v3[((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, weightings);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample16)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		for(int i = 1; i < size; i += 1) {
			if(!fixedFlag$sample22)
				v2[i] = DistributionSampling.sampleCategorical(RNG$, weightings);
		}
		for(int j = 0; j < size; j += 1) {
			if(!fixedFlag$sample28)
				v3[((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, weightings);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample12)
				sample12();
			if(!fixedFlag$sample16)
				sample16();
			for(int i = 1; i < size; i += 1) {
				if(!fixedFlag$sample22)
					sample22(i);
			}
			for(int j = 0; j < size; j += 1) {
				if(!fixedFlag$sample28)
					sample28(j);
			}
		} else {
			for(int j = (size - ((((size - 1) - 0) % 1) + 1)); j >= ((0 - 1) + 1); j -= 1) {
				if(!fixedFlag$sample28)
					sample28(j);
			}
			for(int i = (size - ((((size - 1) - 1) % 1) + 1)); i >= ((1 - 1) + 1); i -= 1) {
				if(!fixedFlag$sample22)
					sample22(i);
			}
			if(!fixedFlag$sample16)
				sample16();
			if(!fixedFlag$sample12)
				sample12();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		size = length$value;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$c = 0.0;
		if(!fixedProbFlag$sample12)
			logProbability$v1 = 0.0;
		logProbability$var12 = 0.0;
		logProbability$v2 = 0.0;
		if(!fixedProbFlag$sample16)
			logProbability$var13 = 0.0;
		for(int i = 1; i < size; i += 1)
			logProbability$var18[((i - 1) / 1)] = 0.0;
		if(!fixedProbFlag$sample22) {
			for(int i = 1; i < size; i += 1)
				logProbability$sample22[((i - 1) / 1)] = 0.0;
		}
		if(!fixedProbFlag$sample28)
			logProbability$v3 = 0.0;
		for(int j = 0; j < size; j += 1)
			logProbability$var31[((j - 0) / 1)] = 0.0;
		logProbability$v = 0.0;
		if(!fixedProbFlag$sample35) {
			for(int j = 0; j < size; j += 1)
				logProbability$sample35[((j - 0) / 1)] = 0.0;
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
		logProbabilityValue$sample35();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample12();
		logProbabilityDistribution$sample16();
		logProbabilityDistribution$sample22();
		logProbabilityValue$sample28();
		logProbabilityDistribution$sample35();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample12();
		logProbabilityValue$sample16();
		logProbabilityValue$sample22();
		logProbabilityValue$sample28();
		logProbabilityValue$sample35();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample12)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings);
		if(!fixedFlag$sample16)
			v2[0] = DistributionSampling.sampleCategorical(RNG$, weightings);
		for(int i = 1; i < size; i += 1) {
			if(!fixedFlag$sample22)
				v2[i] = DistributionSampling.sampleCategorical(RNG$, weightings);
		}
		for(int j = 0; j < size; j += 1) {
			if(!fixedFlag$sample28)
				v3[((j - 0) / 1)] = DistributionSampling.sampleCategorical(RNG$, weightings);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		boolean[] cv$source1 = value;
		boolean[] cv$target1 = v;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel DistributionTest2b(double[] weightings, boolean[] value) {\n    int size = value.length;\n    \n    Categorical c = new Categorical(weightings);\n    int v1 = c.sampleDistribution();\n    \n    int[] v2 = new int[size];\n    v2[0] = categorical(weightings).sampleDistribution();\n    for(int i:[1..size))\n        v2[i] = categorical(weightings).sampleDistribution();\n        \n    boolean[] v = new boolean[size];\n    for(int j:[0..size)) {\n        int v3 = c.sample();\n        v[j] = bernoulli((1.0*v1)/(v2[j] + v3)).sample();\n    }\n        \n    v.observe(value);\n}\n";
	}
}