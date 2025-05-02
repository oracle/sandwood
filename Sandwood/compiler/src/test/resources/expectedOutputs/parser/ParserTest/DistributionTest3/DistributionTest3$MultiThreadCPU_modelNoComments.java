package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DistributionTest3$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DistributionTest3$CoreInterface {
	private double[] cv$var4$stateProbabilityGlobal;
	private double[] cv$var6$stateProbabilityGlobal;
	private double[] distribution$sample4;
	private double[] distribution$sample6;
	private boolean fixedFlag$sample4 = false;
	private boolean fixedFlag$sample6 = false;
	private boolean fixedProbFlag$sample12 = false;
	private boolean fixedProbFlag$sample4 = false;
	private boolean fixedProbFlag$sample6 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$v;
	private double logProbability$v1;
	private double logProbability$v2;
	private double logProbability$var11;
	private double logProbability$var3;
	private double logProbability$var5;
	private boolean system$gibbsForward = true;
	private boolean v;
	private int v1;
	private int v2;
	private boolean value;
	private double[] weightings;

	public DistributionTest3$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$distribution$sample4() {
		return distribution$sample4;
	}

	@Override
	public final void set$distribution$sample4(double[] cv$value) {
		distribution$sample4 = cv$value;
	}

	@Override
	public final double[] get$distribution$sample6() {
		return distribution$sample6;
	}

	@Override
	public final void set$distribution$sample6(double[] cv$value) {
		distribution$sample6 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample4() {
		return fixedFlag$sample4;
	}

	@Override
	public final void set$fixedFlag$sample4(boolean cv$value) {
		fixedFlag$sample4 = cv$value;
		fixedProbFlag$sample4 = (fixedFlag$sample4 && fixedProbFlag$sample4);
		fixedProbFlag$sample12 = (fixedFlag$sample4 && fixedProbFlag$sample12);
	}

	@Override
	public final boolean get$fixedFlag$sample6() {
		return fixedFlag$sample6;
	}

	@Override
	public final void set$fixedFlag$sample6(boolean cv$value) {
		fixedFlag$sample6 = cv$value;
		fixedProbFlag$sample6 = (fixedFlag$sample6 && fixedProbFlag$sample6);
		fixedProbFlag$sample12 = (fixedFlag$sample6 && fixedProbFlag$sample12);
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
	public final boolean get$v() {
		return v;
	}

	@Override
	public final int get$v1() {
		return v1;
	}

	@Override
	public final void set$v1(int cv$value) {
		v1 = cv$value;
		fixedProbFlag$sample4 = false;
		fixedProbFlag$sample12 = false;
	}

	@Override
	public final int get$v2() {
		return v2;
	}

	@Override
	public final void set$v2(int cv$value) {
		v2 = cv$value;
		fixedProbFlag$sample6 = false;
		fixedProbFlag$sample12 = false;
	}

	@Override
	public final boolean get$value() {
		return value;
	}

	@Override
	public final void set$value(boolean cv$value) {
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
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				boolean cv$sampleValue = v;
				if(fixedFlag$sample4) {
					if(fixedFlag$sample6) {
						{
							double var10 = (((1.0 * v1) + v1) / v2);
							double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var10:(1.0 - var10))));
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
					} else {
						if(true) {
							for(int index$sample6$14 = 0; index$sample6$14 < weightings.length; index$sample6$14 += 1) {
								int distributionTempVariable$v2$16 = index$sample6$14;
								double cv$probabilitySample6Value15 = (1.0 * distribution$sample6[index$sample6$14]);
								{
									double var10 = (((1.0 * v1) + v1) / distributionTempVariable$v2$16);
									double cv$weightedProbability = (Math.log(cv$probabilitySample6Value15) + Math.log((cv$sampleValue?var10:(1.0 - var10))));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample6Value15);
								}
							}
						}
					}
				} else {
					if(true) {
						for(int index$sample4$3 = 0; index$sample4$3 < weightings.length; index$sample4$3 += 1) {
							int distributionTempVariable$v1$5 = index$sample4$3;
							double cv$probabilitySample4Value4 = (1.0 * distribution$sample4[index$sample4$3]);
							if(!true) {
								for(int index$sample4$9 = 0; index$sample4$9 < weightings.length; index$sample4$9 += 1) {
									int distributionTempVariable$v1$11 = index$sample4$9;
									double cv$probabilitySample4Value10 = (cv$probabilitySample4Value4 * distribution$sample4[index$sample4$9]);
									if(fixedFlag$sample6) {
										{
											double var10 = (((1.0 * distributionTempVariable$v1$11) + distributionTempVariable$v1$11) / v2);
											double cv$weightedProbability = (Math.log(cv$probabilitySample4Value10) + Math.log((cv$sampleValue?var10:(1.0 - var10))));
											if((cv$weightedProbability < cv$distributionAccumulator))
												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
											else {
												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
													cv$distributionAccumulator = cv$weightedProbability;
												else
													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
											}
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample4Value10);
										}
									} else {
										if(true) {
											for(int index$sample6$24 = 0; index$sample6$24 < weightings.length; index$sample6$24 += 1) {
												int distributionTempVariable$v2$26 = index$sample6$24;
												double cv$probabilitySample6Value25 = (cv$probabilitySample4Value10 * distribution$sample6[index$sample6$24]);
												{
													double var10 = (((1.0 * distributionTempVariable$v1$11) + distributionTempVariable$v1$11) / distributionTempVariable$v2$26);
													double cv$weightedProbability = (Math.log(cv$probabilitySample6Value25) + Math.log((cv$sampleValue?var10:(1.0 - var10))));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample6Value25);
												}
											}
										}
									}
								}
							}
							if(fixedFlag$sample6) {
								{
									double var10 = (((1.0 * distributionTempVariable$v1$5) + distributionTempVariable$v1$5) / v2);
									double cv$weightedProbability = (Math.log(cv$probabilitySample4Value4) + Math.log((cv$sampleValue?var10:(1.0 - var10))));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample4Value4);
								}
							} else {
								if(true) {
									for(int index$sample6$19 = 0; index$sample6$19 < weightings.length; index$sample6$19 += 1) {
										int distributionTempVariable$v2$21 = index$sample6$19;
										double cv$probabilitySample6Value20 = (cv$probabilitySample4Value4 * distribution$sample6[index$sample6$19]);
										{
											double var10 = (((1.0 * distributionTempVariable$v1$5) + distributionTempVariable$v1$5) / distributionTempVariable$v2$21);
											double cv$weightedProbability = (Math.log(cv$probabilitySample6Value20) + Math.log((cv$sampleValue?var10:(1.0 - var10))));
											if((cv$weightedProbability < cv$distributionAccumulator))
												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
											else {
												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
													cv$distributionAccumulator = cv$weightedProbability;
												else
													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
											}
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample6Value20);
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
			logProbability$var11 = cv$sampleAccumulator;
			logProbability$v = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample12 = (fixedFlag$sample4 && fixedFlag$sample6);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$v;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var11 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample4() {
		if(!fixedProbFlag$sample4) {
			if(fixedFlag$sample4) {
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
				logProbability$var3 = cv$sampleAccumulator;
				logProbability$v1 = cv$sampleProbability;
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample4)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample4 = fixedFlag$sample4;
			}
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$v1;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var3 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample4)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample6() {
		if(!fixedProbFlag$sample6) {
			if(fixedFlag$sample6) {
				double cv$accumulator = 0.0;
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = v2;
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
				logProbability$var5 = cv$sampleAccumulator;
				logProbability$v2 = cv$sampleProbability;
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample6)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample6 = fixedFlag$sample6;
			}
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$v2;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var5 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample6)
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
				boolean cv$sampleValue = v;
				{
					{
						double var10 = (((1.0 * v1) + v1) / v2);
						double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var10:(1.0 - var10))));
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
			logProbability$var11 = cv$sampleAccumulator;
			logProbability$v = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample12 = (fixedFlag$sample4 && fixedFlag$sample6);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$v;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var11 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample4() {
		if(!fixedProbFlag$sample4) {
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
			logProbability$var3 = cv$sampleAccumulator;
			logProbability$v1 = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample4)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample4 = fixedFlag$sample4;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$v1;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var3 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample4)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample6() {
		if(!fixedProbFlag$sample6) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = v2;
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
			logProbability$var5 = cv$sampleAccumulator;
			logProbability$v2 = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample6 = fixedFlag$sample6;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$v2;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var5 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample4() {
		if(true) {
			int cv$numNumStates = 0;
			{
				cv$numNumStates = Math.max(cv$numNumStates, weightings.length);
			}
			double[] cv$stateProbabilityLocal = cv$var4$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
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
					int cv$temp$1$$var178;
					{
						cv$temp$1$$var178 = weightings.length;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var178))?Math.log(cv$temp$0$weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							int traceTempVariable$v1$1_1 = cv$currentValue;
							{
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								{
									if(fixedFlag$sample6) {
										{
											{
												double cv$temp$2$var10;
												{
													double var10 = (((1.0 * traceTempVariable$v1$1_1) + traceTempVariable$v1$1_1) / v2);
													cv$temp$2$var10 = var10;
												}
												if(((Math.log(1.0) + Math.log((v?cv$temp$2$var10:(1.0 - cv$temp$2$var10)))) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v?cv$temp$2$var10:(1.0 - cv$temp$2$var10)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v?cv$temp$2$var10:(1.0 - cv$temp$2$var10))));
													else
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v?cv$temp$2$var10:(1.0 - cv$temp$2$var10)))))) + 1)) + (Math.log(1.0) + Math.log((v?cv$temp$2$var10:(1.0 - cv$temp$2$var10)))));
												}
												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
											}
										}
									} else {
										if(true) {
											for(int index$sample6$4 = 0; index$sample6$4 < weightings.length; index$sample6$4 += 1) {
												int distributionTempVariable$v2$6 = index$sample6$4;
												double cv$probabilitySample6Value5 = (1.0 * distribution$sample6[index$sample6$4]);
												{
													{
														double cv$temp$3$var10;
														{
															double var10 = (((1.0 * traceTempVariable$v1$1_1) + traceTempVariable$v1$1_1) / distributionTempVariable$v2$6);
															cv$temp$3$var10 = var10;
														}
														if(((Math.log(cv$probabilitySample6Value5) + Math.log((v?cv$temp$3$var10:(1.0 - cv$temp$3$var10)))) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample6Value5) + Math.log((v?cv$temp$3$var10:(1.0 - cv$temp$3$var10)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample6Value5) + Math.log((v?cv$temp$3$var10:(1.0 - cv$temp$3$var10))));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample6Value5) + Math.log((v?cv$temp$3$var10:(1.0 - cv$temp$3$var10)))))) + 1)) + (Math.log(cv$probabilitySample6Value5) + Math.log((v?cv$temp$3$var10:(1.0 - cv$temp$3$var10)))));
														}
														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample6Value5);
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
			double[] cv$localProbability = distribution$sample4;
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
					cv$localProbability[cv$indexName] = (1.0 / cv$numNumStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void sample6() {
		if(true) {
			int cv$numNumStates = 0;
			{
				cv$numNumStates = Math.max(cv$numNumStates, weightings.length);
			}
			double[] cv$stateProbabilityLocal = cv$var6$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
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
					int cv$temp$1$$var198;
					{
						cv$temp$1$$var198 = weightings.length;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var198))?Math.log(cv$temp$0$weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							int traceTempVariable$v2$1_1 = cv$currentValue;
							{
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								{
									if(fixedFlag$sample4) {
										{
											{
												double cv$temp$2$var10;
												{
													double var10 = (((1.0 * v1) + v1) / traceTempVariable$v2$1_1);
													cv$temp$2$var10 = var10;
												}
												if(((Math.log(1.0) + Math.log((v?cv$temp$2$var10:(1.0 - cv$temp$2$var10)))) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((v?cv$temp$2$var10:(1.0 - cv$temp$2$var10)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((v?cv$temp$2$var10:(1.0 - cv$temp$2$var10))));
													else
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((v?cv$temp$2$var10:(1.0 - cv$temp$2$var10)))))) + 1)) + (Math.log(1.0) + Math.log((v?cv$temp$2$var10:(1.0 - cv$temp$2$var10)))));
												}
												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
											}
										}
									} else {
										if(true) {
											for(int index$sample4$4 = 0; index$sample4$4 < weightings.length; index$sample4$4 += 1) {
												int distributionTempVariable$v1$6 = index$sample4$4;
												double cv$probabilitySample4Value5 = (1.0 * distribution$sample4[index$sample4$4]);
												if(!true) {
													for(int index$sample4$10 = 0; index$sample4$10 < weightings.length; index$sample4$10 += 1) {
														int distributionTempVariable$v1$12 = index$sample4$10;
														double cv$probabilitySample4Value11 = (cv$probabilitySample4Value5 * distribution$sample4[index$sample4$10]);
														{
															{
																double cv$temp$4$var10;
																{
																	double var10 = (((1.0 * distributionTempVariable$v1$12) + distributionTempVariable$v1$12) / traceTempVariable$v2$1_1);
																	cv$temp$4$var10 = var10;
																}
																if(((Math.log(cv$probabilitySample4Value11) + Math.log((v?cv$temp$4$var10:(1.0 - cv$temp$4$var10)))) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample4Value11) + Math.log((v?cv$temp$4$var10:(1.0 - cv$temp$4$var10)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample4Value11) + Math.log((v?cv$temp$4$var10:(1.0 - cv$temp$4$var10))));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample4Value11) + Math.log((v?cv$temp$4$var10:(1.0 - cv$temp$4$var10)))))) + 1)) + (Math.log(cv$probabilitySample4Value11) + Math.log((v?cv$temp$4$var10:(1.0 - cv$temp$4$var10)))));
																}
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample4Value11);
															}
														}
													}
												}
												{
													{
														double cv$temp$3$var10;
														{
															double var10 = (((1.0 * distributionTempVariable$v1$6) + distributionTempVariable$v1$6) / traceTempVariable$v2$1_1);
															cv$temp$3$var10 = var10;
														}
														if(((Math.log(cv$probabilitySample4Value5) + Math.log((v?cv$temp$3$var10:(1.0 - cv$temp$3$var10)))) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample4Value5) + Math.log((v?cv$temp$3$var10:(1.0 - cv$temp$3$var10)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample4Value5) + Math.log((v?cv$temp$3$var10:(1.0 - cv$temp$3$var10))));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample4Value5) + Math.log((v?cv$temp$3$var10:(1.0 - cv$temp$3$var10)))))) + 1)) + (Math.log(cv$probabilitySample4Value5) + Math.log((v?cv$temp$3$var10:(1.0 - cv$temp$3$var10)))));
														}
														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample4Value5);
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
			double[] cv$localProbability = distribution$sample6;
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
					cv$localProbability[cv$indexName] = (1.0 / cv$numNumStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	@Override
	public final void allocateScratch() {
		{
			cv$var4$stateProbabilityGlobal = new double[weightings.length];
		}
		{
			cv$var6$stateProbabilityGlobal = new double[weightings.length];
		}
	}

	@Override
	public final void allocator() {
		{
			distribution$sample4 = new double[weightings.length];
		}
		{
			distribution$sample6 = new double[weightings.length];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample4)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample6)
			v2 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		v = DistributionSampling.sampleBernoulli(RNG$, (((1.0 * v1) + v1) / v2));
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		double[] cv$distribution$sample4 = distribution$sample4;
		for(int index$var3 = 0; index$var3 < weightings.length; index$var3 += 1) {
			double cv$value = (((0.0 <= index$var3) && (index$var3 < weightings.length))?weightings[index$var3]:0.0);
			if(!fixedFlag$sample4)
				cv$distribution$sample4[index$var3] = cv$value;
		}
		double[] cv$distribution$sample6 = distribution$sample6;
		for(int index$var5 = 0; index$var5 < weightings.length; index$var5 += 1) {
			double cv$value = (((0.0 <= index$var5) && (index$var5 < weightings.length))?weightings[index$var5]:0.0);
			if(!fixedFlag$sample6)
				cv$distribution$sample6[index$var5] = cv$value;
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample4)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample6)
			v2 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample4)
				sample4();
			if(!fixedFlag$sample6)
				sample6();
		} else {
			if(!fixedFlag$sample6)
				sample6();
			if(!fixedFlag$sample4)
				sample4();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var3 = 0.0;
		if(!fixedProbFlag$sample4)
			logProbability$v1 = Double.NaN;
		logProbability$var5 = 0.0;
		if(!fixedProbFlag$sample6)
			logProbability$v2 = Double.NaN;
		logProbability$var11 = 0.0;
		if(!fixedProbFlag$sample12)
			logProbability$v = Double.NaN;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample12();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample4();
		logProbabilityDistribution$sample6();
		logProbabilityDistribution$sample12();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample4();
		logProbabilityValue$sample6();
		logProbabilityValue$sample12();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample4)
			v1 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		if(!fixedFlag$sample6)
			v2 = DistributionSampling.sampleCategorical(RNG$, weightings, weightings.length);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		v = value;
	}

	@Override
	public final void setIntermediates() {}

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
		     + "model DistributionTest3(double[] weightings, boolean value) {\n"
		     + "    int v1 = categorical(weightings).sampleDistribution();\n"
		     + "    int v2 = categorical(weightings).sampleDistribution();\n"
		     + "    boolean v = bernoulli(((1.0*v1) + v1)/v2).sample();\n"
		     + "    v.observe(value);\n"
		     + "}\n"
		     + "";
	}
}