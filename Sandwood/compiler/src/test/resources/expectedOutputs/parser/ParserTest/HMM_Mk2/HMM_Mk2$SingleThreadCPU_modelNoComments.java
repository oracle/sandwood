package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMM_Mk2$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMM_Mk2$CoreInterface {
	private double[][] bias;
	private double[] cv$var25$countGlobal;
	private double[] cv$var32$countGlobal;
	private double[] cv$var45$countGlobal;
	private double[] cv$var47$stateProbabilityGlobal;
	private double[] cv$var55$stateProbabilityGlobal;
	private double[] cv$var74$stateProbabilityGlobal;
	private int[][] events;
	private int[][] eventsMeasured;
	private boolean fixedFlag$sample26 = false;
	private boolean fixedFlag$sample33 = false;
	private boolean fixedFlag$sample48 = false;
	private boolean fixedFlag$sample50 = false;
	private boolean fixedFlag$sample58 = false;
	private boolean fixedFlag$sample78 = false;
	private boolean fixedFlag$sample99 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean fixedProbFlag$sample33 = false;
	private boolean fixedProbFlag$sample48 = false;
	private boolean fixedProbFlag$sample50 = false;
	private boolean fixedProbFlag$sample58 = false;
	private boolean fixedProbFlag$sample78 = false;
	private boolean fixedProbFlag$sample99 = false;
	private int initialState;
	private int[] length$eventsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$events;
	private double logProbability$initialState;
	private double logProbability$m;
	private double[] logProbability$sample58;
	private double[][] logProbability$sample78;
	private double[][] logProbability$sample99;
	private double logProbability$st;
	private double logProbability$var20;
	private double logProbability$var25;
	private double logProbability$var27;
	private double logProbability$var32;
	private double logProbability$var44;
	private double logProbability$var46;
	private double[] logProbability$var54;
	private double[][] logProbability$var73;
	private double[][] logProbability$var93;
	private double logProbability$weights;
	private double[][] m;
	private int noEvents;
	private int noStates;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$events = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private boolean setFlag$weights = false;
	private int[][] st;
	private boolean system$gibbsForward = true;
	private double[] v;
	private double[] v2;
	private double[] weights;

	public HMM_Mk2$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[][] cv$value) {
		bias = cv$value;
		setFlag$bias = true;
	}

	@Override
	public final int[][] get$events() {
		return events;
	}

	@Override
	public final void set$events(int[][] cv$value) {
		events = cv$value;
		setFlag$events = true;
	}

	@Override
	public final int[][] get$eventsMeasured() {
		return eventsMeasured;
	}

	@Override
	public final void set$eventsMeasured(int[][] cv$value) {
		eventsMeasured = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	@Override
	public final void set$fixedFlag$sample26(boolean cv$value) {
		fixedFlag$sample26 = cv$value;
		fixedProbFlag$sample26 = (fixedFlag$sample26 && fixedProbFlag$sample26);
		fixedProbFlag$sample58 = (fixedFlag$sample26 && fixedProbFlag$sample58);
		fixedProbFlag$sample78 = (fixedFlag$sample26 && fixedProbFlag$sample78);
	}

	@Override
	public final boolean get$fixedFlag$sample33() {
		return fixedFlag$sample33;
	}

	@Override
	public final void set$fixedFlag$sample33(boolean cv$value) {
		fixedFlag$sample33 = cv$value;
		fixedProbFlag$sample33 = (fixedFlag$sample33 && fixedProbFlag$sample33);
		fixedProbFlag$sample99 = (fixedFlag$sample33 && fixedProbFlag$sample99);
	}

	@Override
	public final boolean get$fixedFlag$sample48() {
		return fixedFlag$sample48;
	}

	@Override
	public final void set$fixedFlag$sample48(boolean cv$value) {
		fixedFlag$sample48 = cv$value;
		fixedProbFlag$sample48 = (fixedFlag$sample48 && fixedProbFlag$sample48);
		fixedProbFlag$sample50 = (fixedFlag$sample48 && fixedProbFlag$sample50);
	}

	@Override
	public final boolean get$fixedFlag$sample50() {
		return fixedFlag$sample50;
	}

	@Override
	public final void set$fixedFlag$sample50(boolean cv$value) {
		fixedFlag$sample50 = cv$value;
		fixedProbFlag$sample50 = (fixedFlag$sample50 && fixedProbFlag$sample50);
		fixedProbFlag$sample58 = (fixedFlag$sample50 && fixedProbFlag$sample58);
	}

	@Override
	public final boolean get$fixedFlag$sample58() {
		return fixedFlag$sample58;
	}

	@Override
	public final void set$fixedFlag$sample58(boolean cv$value) {
		fixedFlag$sample58 = cv$value;
		fixedProbFlag$sample58 = (fixedFlag$sample58 && fixedProbFlag$sample58);
		fixedProbFlag$sample78 = (fixedFlag$sample58 && fixedProbFlag$sample78);
		fixedProbFlag$sample99 = (fixedFlag$sample58 && fixedProbFlag$sample99);
	}

	@Override
	public final boolean get$fixedFlag$sample78() {
		return fixedFlag$sample78;
	}

	@Override
	public final void set$fixedFlag$sample78(boolean cv$value) {
		fixedFlag$sample78 = cv$value;
		fixedProbFlag$sample78 = (fixedFlag$sample78 && fixedProbFlag$sample78);
		fixedProbFlag$sample99 = (fixedFlag$sample78 && fixedProbFlag$sample99);
	}

	@Override
	public final boolean get$fixedFlag$sample99() {
		return fixedFlag$sample99;
	}

	@Override
	public final void set$fixedFlag$sample99(boolean cv$value) {
		fixedFlag$sample99 = cv$value;
		fixedProbFlag$sample99 = (fixedFlag$sample99 && fixedProbFlag$sample99);
	}

	@Override
	public final int get$initialState() {
		return initialState;
	}

	@Override
	public final void set$initialState(int cv$value) {
		initialState = cv$value;
	}

	@Override
	public final int[] get$length$eventsMeasured() {
		return length$eventsMeasured;
	}

	@Override
	public final void set$length$eventsMeasured(int[] cv$value) {
		length$eventsMeasured = cv$value;
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
	public final double get$logProbability$events() {
		return logProbability$events;
	}

	@Override
	public final double get$logProbability$initialState() {
		return logProbability$initialState;
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
	public final double get$logProbability$weights() {
		return logProbability$weights;
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
	public final int get$noEvents() {
		return noEvents;
	}

	@Override
	public final void set$noEvents(int cv$value) {
		noEvents = cv$value;
	}

	@Override
	public final int get$noStates() {
		return noStates;
	}

	@Override
	public final void set$noStates(int cv$value) {
		noStates = cv$value;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	@Override
	public final int[][] get$st() {
		return st;
	}

	@Override
	public final void set$st(int[][] cv$value) {
		st = cv$value;
		setFlag$st = true;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	@Override
	public final double[] get$v2() {
		return v2;
	}

	@Override
	public final double[] get$weights() {
		return weights;
	}

	@Override
	public final void set$weights(double[] cv$value) {
		weights = cv$value;
		setFlag$weights = true;
	}

	private final void logProbabilityValue$sample26() {
		if(!fixedProbFlag$sample26) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var24 = 0; var24 < noStates; var24 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var24];
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
			logProbability$var20 = cv$sampleAccumulator;
			logProbability$var25 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample26 = fixedFlag$sample26;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var25;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var20 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample33() {
		if(!fixedProbFlag$sample33) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var31 = 0; var31 < noStates; var31 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = bias[var31];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v2));
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
			logProbability$var27 = cv$sampleAccumulator;
			logProbability$var32 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample33)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample33 = fixedFlag$sample33;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var32;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var27 = cv$rvAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample33)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample48() {
		if(!fixedProbFlag$sample48) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double[] cv$sampleValue = weights;
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
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var44 = cv$sampleAccumulator;
			logProbability$weights = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample48)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample48 = fixedFlag$sample48;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$weights;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var44 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample48)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample50() {
		if(!fixedProbFlag$sample50) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = initialState;
				{
					{
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weights.length))?Math.log(weights[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var46 = cv$sampleAccumulator;
			logProbability$initialState = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample50 = (fixedFlag$sample50 && fixedFlag$sample48);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$initialState;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var46 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample58() {
		if(!fixedProbFlag$sample58) {
			double cv$accumulator = 0.0;
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = st[i$var50][0];
					{
						{
							double[] var53 = m[initialState];
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
				logProbability$var54[((i$var50 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample58[((i$var50 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample58 = ((fixedFlag$sample58 && fixedFlag$sample26) && fixedFlag$sample50);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample58[((i$var50 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var54[((i$var50 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample58)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample78() {
		if(!fixedProbFlag$sample78) {
			double cv$accumulator = 0.0;
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						int cv$sampleValue = st[i$var60][j$var66];
						{
							{
								double[] var72 = m[st[i$var60][(j$var66 - 1)]];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var72.length))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var73[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample78[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample78)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample78 = ((fixedFlag$sample78 && fixedFlag$sample26) && fixedFlag$sample58);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample78[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var73[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample78)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample99() {
		if(!fixedProbFlag$sample99) {
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
				for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						int cv$sampleValue = (events[i$var80][j$var88] - 1);
						{
							{
								double[] var92 = bias[st[i$var80][j$var88]];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var92.length))?Math.log(var92[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var93[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample99[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample99 = (((fixedFlag$sample99 && fixedFlag$sample33) && fixedFlag$sample58) && fixedFlag$sample78);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
				for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample99[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var93[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample26(int var24) {
		double[] cv$targetLocal = m[var24];
		double[] cv$countLocal = cv$var25$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					if((var24 == initialState)) {
						for(int i$var50 = 0; i$var50 < samples; i$var50 += 1)
							cv$countLocal[st[i$var50][0]] = (cv$countLocal[st[i$var50][0]] + 1.0);
					}
				}
			}
			{
				{
					for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
						for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
							if((var24 == st[i$var60][(j$var66 - 1)])) {
								{
									{
										{
											{
												{
													cv$countLocal[st[i$var60][j$var66]] = (cv$countLocal[st[i$var60][j$var66]] + 1.0);
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
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	private final void sample33(int var31) {
		double[] cv$targetLocal = bias[var31];
		double[] cv$countLocal = cv$var32$countGlobal;
		int cv$arrayLength = noEvents;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
						for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
							if((var31 == st[i$var80][j$var88])) {
								{
									{
										{
											{
												{
													cv$countLocal[(events[i$var80][j$var88] - 1)] = (cv$countLocal[(events[i$var80][j$var88] - 1)] + 1.0);
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
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v2, cv$countLocal, cv$targetLocal);
	}

	private final void sample48() {
		double[] cv$targetLocal = weights;
		double[] cv$countLocal = cv$var45$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					{
						{
							{
								{
									{
										cv$countLocal[initialState] = (cv$countLocal[initialState] + 1.0);
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

	private final void sample50() {
		double[] cv$stateProbabilityLocal = cv$var47$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			initialState = cv$currentValue;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$weights;
				{
					cv$temp$0$weights = weights;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$weights.length))?Math.log(cv$temp$0$weights[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
							int traceTempVariable$initialState$1_2 = cv$currentValue;
							{
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								{
									{
										{
											{
												double[] cv$temp$1$var53;
												{
													double[] var53 = m[traceTempVariable$initialState$1_2];
													cv$temp$1$var53 = var53;
												}
												if(((Math.log(1.0) + (((0.0 <= st[i$var50][0]) && (st[i$var50][0] < cv$temp$1$var53.length))?Math.log(cv$temp$1$var53[st[i$var50][0]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var50][0]) && (st[i$var50][0] < cv$temp$1$var53.length))?Math.log(cv$temp$1$var53[st[i$var50][0]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var50][0]) && (st[i$var50][0] < cv$temp$1$var53.length))?Math.log(cv$temp$1$var53[st[i$var50][0]]):Double.NEGATIVE_INFINITY));
													else
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var50][0]) && (st[i$var50][0] < cv$temp$1$var53.length))?Math.log(cv$temp$1$var53[st[i$var50][0]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var50][0]) && (st[i$var50][0] < cv$temp$1$var53.length))?Math.log(cv$temp$1$var53[st[i$var50][0]]):Double.NEGATIVE_INFINITY)));
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
		initialState = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
	}

	private final void sample58(int i$var50) {
		double[] cv$stateProbabilityLocal = cv$var55$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			int var55 = cv$currentValue;
			int[] var51 = st[i$var50];
			var51[0] = cv$currentValue;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var53;
				{
					double[] var53 = m[initialState];
					cv$temp$0$var53 = var53;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var53.length))?Math.log(cv$temp$0$var53[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var71$1_1 = cv$currentValue;
						for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
							if((i$var50 == i$var60)) {
								for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
									if((0 == (j$var66 - 1))) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double[] cv$temp$1$var72;
															{
																double[] var72 = m[traceTempVariable$var71$1_1];
																cv$temp$1$var72 = var72;
															}
															if(((Math.log(1.0) + (((0.0 <= st[i$var60][j$var66]) && (st[i$var60][j$var66] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[i$var60][j$var66]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var60][j$var66]) && (st[i$var60][j$var66] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[i$var60][j$var66]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var60][j$var66]) && (st[i$var60][j$var66] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[i$var60][j$var66]]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var60][j$var66]) && (st[i$var60][j$var66] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[i$var60][j$var66]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var60][j$var66]) && (st[i$var60][j$var66] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[i$var60][j$var66]]):Double.NEGATIVE_INFINITY)));
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
						int traceTempVariable$var91$4_1 = cv$currentValue;
						for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
							if((i$var50 == i$var80)) {
								for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
									if((0 == j$var88)) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double[] cv$temp$2$var92;
															{
																double[] var92 = bias[traceTempVariable$var91$4_1];
																cv$temp$2$var92 = var92;
															}
															if(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$2$var92.length))?Math.log(cv$temp$2$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$2$var92.length))?Math.log(cv$temp$2$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$2$var92.length))?Math.log(cv$temp$2$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$2$var92.length))?Math.log(cv$temp$2$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$2$var92.length))?Math.log(cv$temp$2$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)));
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
		int var55 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
		int[] var51 = st[i$var50];
		var51[0] = var55;
	}

	private final void sample78(int i$var60, int j$var66) {
		double[] cv$stateProbabilityLocal = cv$var74$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			int var74 = cv$currentValue;
			int[] var67 = st[i$var60];
			var67[j$var66] = cv$currentValue;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var72;
				{
					double[] var72 = m[st[i$var60][(j$var66 - 1)]];
					cv$temp$0$var72 = var72;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var72.length))?Math.log(cv$temp$0$var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var71$1_1 = cv$currentValue;
						for(int index$i$1_2 = 0; index$i$1_2 < samples; index$i$1_2 += 1) {
							if((i$var60 == index$i$1_2)) {
								for(int index$j$1_3 = 1; index$j$1_3 < length$eventsMeasured[index$i$1_2]; index$j$1_3 += 1) {
									if((j$var66 == (index$j$1_3 - 1))) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double[] cv$temp$1$var72;
															{
																double[] var72 = m[traceTempVariable$var71$1_1];
																cv$temp$1$var72 = var72;
															}
															if(((Math.log(1.0) + (((0.0 <= st[index$i$1_2][index$j$1_3]) && (st[index$i$1_2][index$j$1_3] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[index$i$1_2][index$j$1_3]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[index$i$1_2][index$j$1_3]) && (st[index$i$1_2][index$j$1_3] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[index$i$1_2][index$j$1_3]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[index$i$1_2][index$j$1_3]) && (st[index$i$1_2][index$j$1_3] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[index$i$1_2][index$j$1_3]]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[index$i$1_2][index$j$1_3]) && (st[index$i$1_2][index$j$1_3] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[index$i$1_2][index$j$1_3]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[index$i$1_2][index$j$1_3]) && (st[index$i$1_2][index$j$1_3] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[index$i$1_2][index$j$1_3]]):Double.NEGATIVE_INFINITY)));
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
						int traceTempVariable$var91$4_1 = cv$currentValue;
						for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
							if((i$var60 == i$var80)) {
								for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
									if((j$var66 == j$var88)) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double[] cv$temp$2$var92;
															{
																double[] var92 = bias[traceTempVariable$var91$4_1];
																cv$temp$2$var92 = var92;
															}
															if(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$2$var92.length))?Math.log(cv$temp$2$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$2$var92.length))?Math.log(cv$temp$2$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$2$var92.length))?Math.log(cv$temp$2$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$2$var92.length))?Math.log(cv$temp$2$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$2$var92.length))?Math.log(cv$temp$2$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)));
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
		int var74 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
		int[] var67 = st[i$var60];
		var67[j$var66] = var74;
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			for(int var24 = 0; var24 < noStates; var24 += 1)
				cv$max = Math.max(cv$max, noStates);
			cv$var25$countGlobal = new double[cv$max];
		}
		{
			int cv$max = 0;
			for(int var31 = 0; var31 < noStates; var31 += 1)
				cv$max = Math.max(cv$max, noEvents);
			cv$var32$countGlobal = new double[cv$max];
		}
		{
			int cv$max = 0;
			cv$max = Math.max(cv$max, noStates);
			cv$var45$countGlobal = new double[cv$max];
		}
		{
			cv$var47$stateProbabilityGlobal = new double[noStates];
		}
		{
			int cv$var26$max = noStates;
			cv$var55$stateProbabilityGlobal = new double[cv$var26$max];
		}
		{
			int cv$var26$max = noStates;
			cv$var74$stateProbabilityGlobal = new double[cv$var26$max];
		}
	}

	@Override
	public final void allocator() {
		{
			v = new double[noStates];
		}
		{
			v2 = new double[noEvents];
		}
		if(!setFlag$m) {
			{
				m = new double[noStates][];
				for(int var24 = 0; var24 < noStates; var24 += 1)
					m[var24] = new double[noStates];
			}
		}
		if(!setFlag$bias) {
			{
				bias = new double[noStates][];
				for(int var31 = 0; var31 < noStates; var31 += 1)
					bias[var31] = new double[noEvents];
			}
		}
		if(!setFlag$st) {
			{
				st = new int[length$eventsMeasured.length][];
				for(int i$var38 = 0; i$var38 < length$eventsMeasured.length; i$var38 += 1)
					st[i$var38] = new int[length$eventsMeasured[i$var38]];
			}
		}
		if(!setFlag$weights) {
			{
				weights = new double[noStates];
			}
		}
		if(!setFlag$events) {
			{
				events = new int[length$eventsMeasured.length][];
				for(int i$var80 = 0; i$var80 < length$eventsMeasured.length; i$var80 += 1)
					events[i$var80] = new int[length$eventsMeasured[i$var80]];
			}
		}
		{
			logProbability$var54 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample58 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var73 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var60 = 0; i$var60 < length$eventsMeasured.length; i$var60 += 1)
				logProbability$var73[((i$var60 - 0) / 1)] = new double[((((length$eventsMeasured[i$var60] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample78 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var60 = 0; i$var60 < length$eventsMeasured.length; i$var60 += 1)
				logProbability$sample78[((i$var60 - 0) / 1)] = new double[((((length$eventsMeasured[i$var60] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var93 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var80 = 0; i$var80 < length$eventsMeasured.length; i$var80 += 1)
				logProbability$var93[((i$var80 - 0) / 1)] = new double[((((length$eventsMeasured[i$var80] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample99 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var80 = 0; i$var80 < length$eventsMeasured.length; i$var80 += 1)
				logProbability$sample99[((i$var80 - 0) / 1)] = new double[((((length$eventsMeasured[i$var80] - 1) - 1) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int var24 = 0; var24 < noStates; var24 += 1) {
			double[] var25 = m[var24];
			if(!fixedFlag$sample26)
				DistributionSampling.sampleDirichlet(RNG$, v, var25);
		}
		for(int var31 = 0; var31 < noStates; var31 += 1) {
			double[] var32 = bias[var31];
			if(!fixedFlag$sample33)
				DistributionSampling.sampleDirichlet(RNG$, v2, var32);
		}
		if(!fixedFlag$sample48)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample50)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
			int[] var51 = st[i$var50];
			if(!fixedFlag$sample58)
				var51[0] = DistributionSampling.sampleCategorical(RNG$, m[initialState]);
		}
		for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
			int[] var67 = st[i$var60];
			for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
				if(!fixedFlag$sample78)
					var67[j$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var60][(j$var66 - 1)]]);
			}
		}
		for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
			int[] var89 = events[i$var80];
			for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
				if(!fixedFlag$sample99)
					var89[j$var88] = (DistributionSampling.sampleCategorical(RNG$, bias[st[i$var80][j$var88]]) + 1);
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var24 = 0; var24 < noStates; var24 += 1) {
			double[] var25 = m[var24];
			if(!fixedFlag$sample26)
				DistributionSampling.sampleDirichlet(RNG$, v, var25);
		}
		for(int var31 = 0; var31 < noStates; var31 += 1) {
			double[] var32 = bias[var31];
			if(!fixedFlag$sample33)
				DistributionSampling.sampleDirichlet(RNG$, v2, var32);
		}
		if(!fixedFlag$sample48)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample50)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
			int[] var51 = st[i$var50];
			if(!fixedFlag$sample58)
				var51[0] = DistributionSampling.sampleCategorical(RNG$, m[initialState]);
		}
		for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
			int[] var67 = st[i$var60];
			for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
				if(!fixedFlag$sample78)
					var67[j$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var60][(j$var66 - 1)]]);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var24 = 0; var24 < noStates; var24 += 1) {
			double[] var25 = m[var24];
			if(!fixedFlag$sample26)
				DistributionSampling.sampleDirichlet(RNG$, v, var25);
		}
		for(int var31 = 0; var31 < noStates; var31 += 1) {
			double[] var32 = bias[var31];
			if(!fixedFlag$sample33)
				DistributionSampling.sampleDirichlet(RNG$, v2, var32);
		}
		if(!fixedFlag$sample48)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample50)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
			int[] var51 = st[i$var50];
			if(!fixedFlag$sample58)
				var51[0] = DistributionSampling.sampleCategorical(RNG$, m[initialState]);
		}
		for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
			int[] var67 = st[i$var60];
			for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
				if(!fixedFlag$sample78)
					var67[j$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var60][(j$var66 - 1)]]);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var24 = 0; var24 < noStates; var24 += 1) {
				if(!fixedFlag$sample26)
					sample26(var24);
			}
			for(int var31 = 0; var31 < noStates; var31 += 1) {
				if(!fixedFlag$sample33)
					sample33(var31);
			}
			if(!fixedFlag$sample48)
				sample48();
			if(!fixedFlag$sample50)
				sample50();
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
				if(!fixedFlag$sample58)
					sample58(i$var50);
			}
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
					if(!fixedFlag$sample78)
						sample78(i$var60, j$var66);
				}
			}
		} else {
			for(int i$var60 = (samples - ((((samples - 1) - 0) % 1) + 1)); i$var60 >= ((0 - 1) + 1); i$var60 -= 1) {
				for(int j$var66 = (length$eventsMeasured[i$var60] - ((((length$eventsMeasured[i$var60] - 1) - 1) % 1) + 1)); j$var66 >= ((1 - 1) + 1); j$var66 -= 1) {
					if(!fixedFlag$sample78)
						sample78(i$var60, j$var66);
				}
			}
			for(int i$var50 = (samples - ((((samples - 1) - 0) % 1) + 1)); i$var50 >= ((0 - 1) + 1); i$var50 -= 1) {
				if(!fixedFlag$sample58)
					sample58(i$var50);
			}
			if(!fixedFlag$sample50)
				sample50();
			if(!fixedFlag$sample48)
				sample48();
			for(int var31 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var31 >= ((0 - 1) + 1); var31 -= 1) {
				if(!fixedFlag$sample33)
					sample33(var31);
			}
			for(int var24 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var24 >= ((0 - 1) + 1); var24 -= 1) {
				if(!fixedFlag$sample26)
					sample26(var24);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		for(int var11 = 0; var11 < noStates; var11 += 1)
			v[var11] = 0.1;
		for(int var17 = 0; var17 < noEvents; var17 += 1)
			v2[var17] = 0.1;
		samples = length$eventsMeasured.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var20 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample26)
			logProbability$var25 = 0.0;
		logProbability$var27 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample33)
			logProbability$var32 = 0.0;
		logProbability$var44 = 0.0;
		if(!fixedProbFlag$sample48)
			logProbability$weights = 0.0;
		logProbability$var46 = 0.0;
		if(!fixedProbFlag$sample50)
			logProbability$initialState = 0.0;
		for(int i$var50 = 0; i$var50 < samples; i$var50 += 1)
			logProbability$var54[((i$var50 - 0) / 1)] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample58) {
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1)
				logProbability$sample58[((i$var50 - 0) / 1)] = 0.0;
		}
		for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
			for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
				logProbability$var73[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)] = 0.0;
		}
		if(!fixedProbFlag$sample78) {
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
					logProbability$sample78[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)] = 0.0;
			}
		}
		for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
			for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1)
				logProbability$var93[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)] = 0.0;
		}
		logProbability$events = 0.0;
		if(!fixedProbFlag$sample99) {
			for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
				for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1)
					logProbability$sample99[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)] = 0.0;
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
		if(fixedFlag$sample26)
			logProbabilityValue$sample26();
		if(fixedFlag$sample33)
			logProbabilityValue$sample33();
		if(fixedFlag$sample48)
			logProbabilityValue$sample48();
		if(fixedFlag$sample50)
			logProbabilityValue$sample50();
		if(fixedFlag$sample58)
			logProbabilityValue$sample58();
		if(fixedFlag$sample78)
			logProbabilityValue$sample78();
		logProbabilityValue$sample99();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample33();
		logProbabilityValue$sample48();
		logProbabilityValue$sample50();
		logProbabilityValue$sample58();
		logProbabilityValue$sample78();
		logProbabilityValue$sample99();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample33();
		logProbabilityValue$sample48();
		logProbabilityValue$sample50();
		logProbabilityValue$sample58();
		logProbabilityValue$sample78();
		logProbabilityValue$sample99();
	}

	@Override
	public final void logProbabilityGeneration() {
		for(int var24 = 0; var24 < noStates; var24 += 1) {
			double[] var25 = m[var24];
			if(!fixedFlag$sample26)
				DistributionSampling.sampleDirichlet(RNG$, v, var25);
		}
		for(int var31 = 0; var31 < noStates; var31 += 1) {
			double[] var32 = bias[var31];
			if(!fixedFlag$sample33)
				DistributionSampling.sampleDirichlet(RNG$, v2, var32);
		}
		if(!fixedFlag$sample48)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample50)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
			int[] var51 = st[i$var50];
			if(!fixedFlag$sample58)
				var51[0] = DistributionSampling.sampleCategorical(RNG$, m[initialState]);
		}
		for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
			int[] var67 = st[i$var60];
			for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
				if(!fixedFlag$sample78)
					var67[j$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var60][(j$var66 - 1)]]);
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int[][] cv$source1 = eventsMeasured;
		int[][] cv$target1 = events;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = cv$source1[cv$index1];
			int[] cv$target2 = cv$target1[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMM_Mk2(int[][] eventsMeasured, int noStates, int noEvents) {\n        \n        // Construct arrays describing the probability of a move from 1 state to another.\n        double[] v = new double[noStates] <~ 0.1;\n        double[] v2 = new double[noEvents] <~ 0.1;\n        double[][] m = dirichlet(v).sample(noStates);\n        \n        // Construct the bias for each webpage.\n        double[][] bias = dirichlet(v2).sample(noStates);\n\n        // Determine how many samples the model will need to produce.\n        int samples = eventsMeasured.length;\n        \n        // Allocate space for the state, i.e. which webpage we are going to trigger an event on.\n        int[][] st = new int[samples][];\n        for(int i:[0 .. samples)){\n            int streamLength = eventsMeasured[i].length;\n            st[i] = new int[streamLength];\n        }\n\n        // Set the initial state by sampling from a categorical with learnt weightings.\n        double[] weights = dirichlet(v).sample();\n        int initialState = categorical(weights).sample();\n        for(int i:[0..samples)) {\n            st[i][0] = categorical(m[initialState]).sample();\n        }\n\n        //Determine the remaining states based on the previous state.\n        for(int i:[0 .. samples)){\n            int streamLength = eventsMeasured[i].length;\n            for(int j:[1..streamLength)){\n                st[i][j] = categorical(m[st[i][j-1]]).sample();\n            }\n        }\n            \n        //Generate each event.\n        int[][] events = new int[samples][];\n        for(int i:[0 .. samples)) {\n            int streamLength = eventsMeasured[i].length;\n            events[i] = new int[streamLength];\n            for(int j:[1..streamLength)){\n                events[i][j] = categorical(bias[st[i][j]]).sample() + 1;\n            }\n        }\n\n        //Tie the values of the flips to the values we have measured.\n        events.observe(eventsMeasured);\n}\n";
	}
}